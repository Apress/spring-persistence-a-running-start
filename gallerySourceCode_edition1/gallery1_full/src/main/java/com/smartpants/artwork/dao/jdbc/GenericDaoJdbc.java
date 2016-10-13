package com.smartpants.artwork.dao.jdbc;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.smartpants.artwork.dao.GenericDao;
import com.smartpants.artwork.domain.DomainObject;

public abstract class GenericDaoJdbc<T extends DomainObject> extends SimpleJdbcDaoSupport 
      implements GenericDao<T>
{
   private Class<T> type;
   private String tableName;
   private SimpleJdbcInsert insertActor;
   private ParameterizedBeanPropertyRowMapper<T> rowMapper;

   public GenericDaoJdbc(Class<T> type, String tableName)
   {
      super();
      this.type = type;
      this.tableName = tableName;
   }

   @Override
   protected void initTemplateConfig()
   {
      super.initTemplateConfig();
      this.insertActor = new SimpleJdbcInsert(getDataSource());
      this.rowMapper = new ParameterizedBeanPropertyRowMapper<T>();
      rowMapper.setMappedClass(type);
   }
   @Override
   public T get(Long id) {
      return this.getSimpleJdbcTemplate().queryForObject("select * from " + tableName
            + " where id = ?", this.type, id);
   }

   public void save(T object) throws DataAccessException {
       if(object.getId() != null){
          getSimpleJdbcTemplate().update(getUpdateSql(), getSqlParameterSource(object));
       } else {
          Number id = insertActor.executeAndReturnKey(getSqlParameterSource(object));
          object.setId(id.longValue());
       }
    }

   public List<T> getAll() {
      return queryByType("select * from " + tableName);
   }

   protected List<T> queryByType(String sql, Object... args){
      return getSimpleJdbcTemplate().query(sql, getRowMapper(), args);
   }

   protected T gueryForInstance(String sql, Object... args){
      return getSimpleJdbcTemplate().queryForObject(sql, getRowMapper(), args);
   }
   
   protected abstract String getUpdateSql();

   protected SqlParameterSource getSqlParameterSource(T object) {
       return new BeanPropertySqlParameterSource(object);
   } 
   
   protected ParameterizedRowMapper<T> getRowMapper(){
      return this.rowMapper;
   }

   public String getTableName(){
      return tableName;
   }
   
   public Class<T> getType() {
      return type;
   }
}
