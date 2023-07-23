package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.repository.entity.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImp extends CustomRepository implements UserRepository {

    @Transactional
    @Override
    public User save(User user) {
        return save(User.class, user);
    }

    @Override
    public User findUserById(Long id) {
        return findById(User.class, id);
    }

    @Transactional
    @Override
    public Boolean deleteByPharmacyId(Long id) {
        List<User> userList = findAllByPharmacyId(id);
        if(userList != null) {
            for (User user : userList) {
                delete(User.class, user);
            }
        }
        return true;
        /*
         * Query query = entityManager.createQuery(
         * "DELETE FROM User g WHERE g.pharmacy.id = :id",
         * User.class).setParameter("id", id);
         * return deleteQueryWrapper(query);
         */

    }

    @Override
    public List<User> getAll() {

        return listQueryWrapper(entityManager.createQuery(
                "select g from User g order by g.id desc ",
                User.class));
    }

    @Override
    public List<User> findAllByLastName(String lastName) {
        return listQueryWrapper(entityManager.createQuery(
                "SELECT g FROM User g WHERE g.lastName = :lastName ORDER BY g.id DESC",
                User.class).setParameter("lastName", lastName));
    }

    public List<User> findAllByPharmacyId(Long id) {
        return listQueryWrapper(entityManager.createQuery(
                "SELECT g FROM User g WHERE g.pharmacy.id = :id ORDER BY g.id DESC",
                User.class).setParameter("id", id));
    }

    @Override
    public List<User> findByRoles(Role role) {
        String roleName = role.getRole();
        return listQueryWrapper(
                entityManager.createQuery("SELECT u FROM User u WHERE u.role.role =: roleName", User.class));
    }

    public boolean delete(User user) {
        delete(User.class, user);
        return true;
    }
}
