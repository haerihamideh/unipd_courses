package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Drug;
import com.unipd.semicolon.core.entity.Supplier;
import com.unipd.semicolon.core.entity.enums.AgeGroup;
import com.unipd.semicolon.core.entity.enums.Country;
import com.unipd.semicolon.core.repository.entity.DrugRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class DrugRepositoryImp extends CustomRepository implements DrugRepository {
    @Transactional
    @Override
    public Drug save(Drug drug) {
        return save(Drug.class,drug);
    }

//    @Override
//    public List<Drug> getAll(){
//        return listQueryWrapper(entityManager.createQuery(
//                "SELECT g FROM Drug g ORDER BY g.id desc ",
//                Drug.class
//        ));
//    }

//    @Override
//    public List<Drug> searchDrug(String name, Supplier supplier, Date expirationDate, String shape, AgeGroup ageGroup, Country countryOf
//    Production)
//    {
//
////        return listQueryWrapper(entityManager.createQuery(
////                "SELECT g FROM Drug g where g.name="+name+" and g.supplier="+supplierId+
////                        " and g.expirationDate= "+expirationDate+" and g.shape= "+shape+
////                        " and g.ageGroup = "+ageGroup.ordinal()+" and g.countryOfProduction= "+countryOfProduction.ordinal()+
////                        " ORDER BY g.id desc ",Drug.class
////        ));
////        return listQueryWrapper(entityManager.createQuery(
////                "SELECT g FROM Drug g"+" where g.name="+name//+" and g.supplier="+supplierId+
////                        //" and g.expirationDate= "+expirationDate+" and g.shape= "+shape+
////                        //" and g.ageGroup = "+ageGroup.ordinal()+" and g.countryOfProduction= "+countryOfProduction.ordinal()+
////                        +" ORDER BY g.id desc ",Drug.class
////        ));
//        return listQueryWrapper(entityManager.createQuery(
//                        "SELECT g FROM Drug g WHERE g.name = :name and g.supplier = :supplier and g.expirationDate= :expirationDate and g.shape= :shape and g.ageGroup = :ageGroup and g.countryOfProduction= :countryOfProduction ORDER BY g.id DESC", Drug.class)
//                .setParameter("name", name)
//                .setParameter("supplier", supplier)
//                .setParameter("expirationDate", expirationDate)
//                .setParameter("shape", shape)
//                .setParameter("ageGroup", ageGroup)
//                .setParameter("countryOfProduction", countryOfProduction)
//        );
//    }

//    public Optional<Drug> findById(Long id) {
//        return findById(id);
//    }
}
