package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.service.LocalTimeService;
import com.unipd.semicolon.business.service.ReceiptService;
import com.unipd.semicolon.business.service.ValidationService;
import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Material;
import com.unipd.semicolon.core.entity.Receipt;
import com.unipd.semicolon.core.entity.enums.PaymentMethod;
import com.unipd.semicolon.core.repository.entity.DrugRepository;
import com.unipd.semicolon.core.repository.entity.MaterialRepository;
import com.unipd.semicolon.core.repository.entity.ReceiptRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReceiptServiceImp implements ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private LocalTimeService localTimeService;

    @Override
    public Receipt save(List<Long> drugId,
                        List<Long> materialId,
                        String image,
                        PaymentMethod paymentMethod,
                        Long totalAmount) {
        try {
            validationService.validatePaymentMethod(paymentMethod);

            if (drugId != null) {
                for (Long id : drugId) {
                    if (drugRepository.findById(id) == null) {
                        throw new EntityNotFoundException("Drug with ID " + id + " not found.");
                    }
                }
            }
            if (materialId != null) {
                for (Long id : materialId) {
                    if (materialRepository.findById(id) == null) {
                        throw new EntityNotFoundException("Material with ID " + id + " not found.");
                    }
                }
            }

            List<Drug> drugList = new ArrayList<>();
            List<Material> materialList = new ArrayList<>();
            if (drugId != null) {
                for (Long id : drugId) {
                    drugList.add(drugRepository.findById(id).get());
                }
            }
            if (materialId != null) {
                for (Long id : materialId) {
                    materialList.add(materialRepository.findById(id).get());
                }
            }

            if (drugList == null && materialList == null) {
                throw new EntityNotFoundException("Drug list and material list are empty");
            }

            Receipt receipt = new Receipt();
            receipt.setReceiptDrugs(drugList);
            receipt.setReceiptMaterials(materialList);
            receipt.setImage(image);
            receipt.setDate(localTimeService.getLocalDate());
            receipt.setPaymentMethod(paymentMethod);
            receipt.setTotalAmount(totalAmount);
            return receiptRepository.save(receipt);

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("An error occurred while saving the receipt: " + e.getMessage(), e);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("An error occurred while saving the receipt: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while saving the receipt.", e);
        }
    }

    @Override
    public Boolean edit(Long id,
                        List<Long> drugId,
                        List<Long> materialId,
                        String image,
                        PaymentMethod paymentMethod) {
        try {
            if (paymentMethod != null) {
                validationService.validatePaymentMethod(paymentMethod);
            }

            Receipt receipt = receiptRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Receipt with ID " + id + " not found."));

            if (drugId != null) {
                for (Long i : drugId) {
                    if (drugRepository.findById(i) == null) {
                        throw new EntityNotFoundException("Drug with ID " + i + " not found.");
                    }
                }
            }
            if (materialId != null) {
                for (Long i : materialId) {
                    if (materialRepository.findById(i) == null) {
                        throw new EntityNotFoundException("Material with ID " + i + " not found.");
                    }
                }
            }

            List<Drug> drugList = new ArrayList<>();
            List<Material> materialList = new ArrayList<>();
            if (drugId != null) {
                for (Long i : drugId) {
                    drugList.add(drugRepository.findById(i).get());
                }
            }
            if (materialId != null) {
                for (Long i : materialId) {
                    materialList.add(materialRepository.findById(i).get());
                }
            }

            if (receipt != null) {
                if (drugList != null) {
                    receipt.setReceiptDrugs(drugList);
                }
                if (materialList != null) {
                    receipt.setReceiptMaterials(materialList);
                }
                if (image != null) {
                    receipt.setImage(image);
                }
                receipt.setDate(localTimeService.getLocalDate());
                if (paymentMethod != null) {
                    receipt.setPaymentMethod(paymentMethod);
                }
                receiptRepository.save(receipt);
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("An error occurred while editing the receipt: " + e.getMessage(), e);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("An error occurred while editing the receipt: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while editing the receipt.", e);
        }
    }

    @Override
    public List<Receipt> getList() {
        return receiptRepository.findAll();
    }

    public Receipt getById(Long id) {
        return receiptRepository.findById(id).get();
    }

}
