package com.smartpants.artwork.dao.jpa;

import java.util.Map;
import java.util.Properties;

import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class ExtendedHibernateJpaVendorAdapter extends
      HibernateJpaVendorAdapter
{

   private Properties additionalProperties;
   
   @SuppressWarnings("unchecked")
   @Override
   public Map getJpaPropertyMap()
   {
      Map jpaPropertyMap = super.getJpaPropertyMap();
      if( additionalProperties != null){
         jpaPropertyMap.putAll(additionalProperties);
      }
      return jpaPropertyMap;
   }

   public Properties getAdditionalProperties()
   {
      return additionalProperties;
   }

   public void setAdditionalProperties(Properties additionalProperties)
   {
      this.additionalProperties = additionalProperties;
   }
   
   
}
