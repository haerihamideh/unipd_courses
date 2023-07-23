package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.enums.OrderReport;
import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.business.exception.EntityNotFoundException;
import com.unipd.semicolon.business.exception.InvalidParameterException;
import com.unipd.semicolon.business.exception.PermissionException;
import com.unipd.semicolon.business.mapper.OrderMapper;
import com.unipd.semicolon.business.service.*;
import com.unipd.semicolon.core.domain.OrderResponse;
import com.unipd.semicolon.core.entity.*;
import com.unipd.semicolon.core.entity.enums.OrderStatus;
import com.unipd.semicolon.core.repository.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private OrderProductsRepository orderProductsRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private LocalTimeService localTimeService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private LogSystem logSystem;

    @Override
    public Order save(
            String token,
            LocalDate orderDate,
            Map<Long, Integer> orderDrugs,
            Map<Long, Integer> orderMaterials,
            OrderStatus status,
            float price,
            boolean isActive,
            Pharmacy pharmacy) throws CustomException {

        if (securityService.getRoleFromToken(token).contains("admin")) {
            Pharmacy pharmacyExist = pharmacyRepository.findById(pharmacy.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Pharmacy does not exist"));

            if (orderDrugs.isEmpty() && orderMaterials.isEmpty()) {
                logSystem.logUtil("Invalid Parameter Exception");
                throw new InvalidParameterException();
            }

            Order order = new Order(
                    localTimeService.getLocalDate(),
                    status,
                    price,
                    isActive,
                    pharmacyExist);
            Order save = orderRepository.save(order);

            if (!orderDrugs.isEmpty()) {
                Map<Drug, Integer> drugIntegerMap = drugListCheck(orderDrugs);
                for (Drug drug : drugIntegerMap.keySet()) {
                    orderProductsRepository.save(new OrderProduct(
                            save,
                            drug,
                            null,
                            drugIntegerMap.get(drug)
                    ));
                }
            } else if (!orderMaterials.isEmpty()) {
                Map<Material, Integer> materialIntegerMap = materialListCheck(orderMaterials);
                for (Material material : materialIntegerMap.keySet()) {
                    orderProductsRepository.save(new OrderProduct(
                            save,
                            null,
                            material,
                            materialIntegerMap.get(material)
                    ));
                }
            }
            return save;
        } else {
            logSystem.logUtil("permission dined , token : " + token);
            throw new PermissionException(token);
        }

    }


    @Override
    public void delete(String token, Long id) {
        try {
            Order order = this.getById(token, id);
            if (order == null) {
                throw new IllegalArgumentException("Can not delete null storage!");
            } else {
                orderRepository.delete(order);
            }
        } catch (PermissionException e) {
            logSystem.logUtil("permission dined , token : " + token);
            throw new PermissionException(token);
        }
    }

    @Override
    public Order getById(String token, Long id) throws CustomException {
        if (securityService.getRoleFromToken(token).contains("admin")) {
            Order order = orderRepository.findOrderById(id);
            if (order != null) {
                return order;
            } else {
                logSystem.logUtil("No order with this id exist , id : " + id);
                throw new EntityNotFoundException("Order Not Found with id" + id);
            }
        } else {
            logSystem.logUtil("permission dined , token : " + token);
            throw new PermissionException(token);
        }
    }

    @Override
    public List<OrderResponse> getAll() {
        return OrderMapper.orderResponse(orderRepository.getAll());
    }

    @Override
    public Order status(
            String token,
            Long orderId,
            OrderStatus orderStatus
    ) throws CustomException {
        if (securityService.getRoleFromToken(token).contains("admin")) {
                Order order = orderRepository.findOrderById(orderId);
                if (order != null) {
                    order.setStatus(orderStatus);
                    if (orderStatus.equals(OrderStatus.DELIVERED)) {
                        for (OrderProduct orderProduct : order.getOrderProducts()) {
                            Storage storage = storageService.storageExist(
                                    order.getPharmacy(),
                                    orderProduct.getDrug(),
                                    orderProduct.getMaterial()
                            );
                            if (storage != null) {
                                storageService.updateStorage(
                                        storage,
                                        orderProduct.getQuantity() + storage.getAmount()
                                );
                            } else {
                                //TODO: validation to use the correct token
                                List<Long> drugList = new ArrayList<>();
                                drugList.add(orderProduct.getDrug().getId());
                                List<Long> materialList = new ArrayList<>();
                                materialList.add(orderProduct.getMaterial().getId());
                                storageService.save(
                                        order.getPharmacy().getId(),
                                        drugList,
                                        materialList,
                                        orderProduct.getQuantity(),
                                        1,
                                        1,
                                        ""
                                );
                            }
                        }

                    }
                    return orderRepository.save(order);
                } else {
                    logSystem.logUtil("No order with this id exist , id : " + orderId);
                    throw new EntityNotFoundException("No order with this id exist");
                }
            } else{
                logSystem.logUtil("permission denied , token : " + token);
                throw new PermissionException(token);
            }
        }

    @Override
    public List<OrderResponse> reportBaseDate(
            String token,
            OrderReport orderReport,
            Short num) throws CustomException {

        if (securityService.getRoleFromToken(token).contains("admin")) {
            switch (orderReport) {
                case THIS_WEEK:
                    return OrderMapper.orderResponse(orderRepository.getAllBetweenDate(
                            localTimeService.firstDayOfThisWeek(),
                            localTimeService.getLocalDate().plusDays(1) // We need the last day.
                    ));
                case LAST_WEEK:
                    Map<String, LocalDate> dayOfWeek = localTimeService.firstAndLastDayOfWeek(num);
                    return OrderMapper.orderResponse(orderRepository.getAllBetweenDate(
                            dayOfWeek.get("start"),
                            dayOfWeek.get("end").plusDays(1)
                    ));
                case THIS_MONTH:
                    return OrderMapper.orderResponse(orderRepository.getAllBetweenDate(
                            localTimeService.firstDayOfThisMonth(),
                            localTimeService.getLocalDate().plusDays(1)
                    ));
                case LAST_MONTH:
                    Map<String, LocalDate> dayOfMonth = localTimeService.firstAndLastDayOfMonth(num);
                    return OrderMapper.orderResponse(orderRepository.getAllBetweenDate(
                            dayOfMonth.get("start"),
                            dayOfMonth.get("end").plusDays(1)
                    ));
                case THIS_YEAR:
                    return OrderMapper.orderResponse(orderRepository.getAllBetweenDate(
                            localTimeService.firstDayOfYear(),
                            localTimeService.getLocalDate().plusDays(1)
                    ));
                default:
                    return getAll();

            }
                } else {
            logSystem.logUtil("permission denied , token : " + token);
            throw new PermissionException(token);
                }
            }

    /* -----  private classes  ----- */

    private Map<Drug, Integer> drugListCheck(Map<Long, Integer> orderDrugList) {

        Map<Drug, Integer> newMapDrug = new HashMap<>();
        for (Long drugId : orderDrugList.keySet()) {
            Drug drug = drugRepository.findById(drugId)
                    .orElseThrow(() -> new EntityNotFoundException("Drug not found - " + drugId));
            newMapDrug.put(drug, orderDrugList.get(drugId));
        }
        return newMapDrug;
    }

    private Map<Material, Integer> materialListCheck(Map<Long, Integer> materialList) {
        Map<Material, Integer> newMaterialMap = new HashMap<>();
        for (Long materialId : materialList.keySet()) {
            Material material = materialRepository.findById(materialId)
                    .orElseThrow(() -> new EntityNotFoundException("Material not found - " + materialId));
            newMaterialMap.put(material, materialList.get(materialId));
        }
        return newMaterialMap;
    }
}
