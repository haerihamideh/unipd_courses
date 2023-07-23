package com.unipd.semicolon.core.repository.entity.Imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomRepository {

  @PersistenceContext
  protected EntityManager entityManager;

  public <T> T findQueryWrapper(TypedQuery<T> typedQuery) {
    List<T> resultList = typedQuery.getResultList();
    if (!resultList.isEmpty()) {
      return resultList.get(0);
    } else {
      return null;
    }
  }

  public <T> List<T> listQueryWrapper(TypedQuery<T> typedQuery) {
    List<T> resultList = typedQuery.getResultList();
    if (resultList.isEmpty()) {
      return null;
    }
    return resultList;
  }

  public Boolean updateQueryWrapper(Query typedQuery) {
    int i = typedQuery.executeUpdate();
    return i > 0;
  }

  public Boolean deleteQueryWrapper(Query query) {
    int i = query.executeUpdate();
    return i > 0;
  }

  public <T> void delete(Class<T> type, T entity) {
    entityManager.remove(entity);
  }

  public <T> T save(Class<T> type, T entity) {
    entityManager.persist(entity);
    return entity;
  }

  public <T> T findById(Class<T> type, Object id) {
    try {
      return entityManager.find(type, id);
    } catch (Exception e) {
      return null;
    }
  }
}

