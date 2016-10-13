package com.smartpants.artwork.dao;

import java.util.List;

import com.smartpants.artwork.domain.DomainObject;

public interface GenericDao<T extends DomainObject>
{
   public T get(Long id) ;
   public void save(T object) ;
   public List<T> getAll() ;

}
