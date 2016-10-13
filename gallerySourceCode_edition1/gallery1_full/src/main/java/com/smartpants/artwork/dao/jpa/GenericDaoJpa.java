package com.smartpants.artwork.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;

import com.smartpants.artwork.dao.GenericDao;
import com.smartpants.artwork.domain.DomainObject;

@SuppressWarnings("unchecked")
public class GenericDaoJpa<T extends DomainObject> implements GenericDao<T>
{
   protected EntityManager entityManager;
   private Class<T> type;

   public GenericDaoJpa(Class<T> type)
   {
      super();
      this.type = type;
   }

   @PersistenceContext
   public void setEntityManager(EntityManager entityManager) {
       this.entityManager = entityManager;
   }

   @Override
   public T get(Long id){
      return (T) entityManager.find(type, id);
   }

    public void save(T object) throws DataAccessException {
        entityManager.persist(object);
    }
     
    public List<T> getAll() {
       return entityManager.createQuery("select obj from " + type.getName() + " obj")
            .getResultList();
    }
}
