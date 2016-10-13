package com.smartpants.artwork.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smartpants.artwork.dao.GenericDao;
import com.smartpants.artwork.domain.DomainObject;

@SuppressWarnings("unchecked")
public class GenericDaoHibernate<T extends DomainObject> extends HibernateDaoSupport implements
      GenericDao<T> {

   private Class<T> type;
   
   public GenericDaoHibernate(Class<T> type)
   {
      super();
      this.type = type;
   }

   @Override
   public T get(Long id)
   {
      return (T) getHibernateTemplate().get(type, id);
   }

   @Override
   public List<T> getAll()
   {
      return getHibernateTemplate().loadAll(type);
   }

   @Override
   public void save(T object)
   {
      getHibernateTemplate().save(object);  
   }

   
   @Autowired
   public void setupSessionFactory(SessionFactory sessionFactory) {
       this.setSessionFactory(sessionFactory);
   }
}
