package com.smartpants.artwork.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 1, 2006
 * Time: 7:22:59 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@SuppressWarnings("serial")
@Entity
public class Person implements DomainObject {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Integer roleLevel;

    private Integer version;


    public Person() {
    }

    public Person(String firstName, String lastName, String username, String password, Integer roleLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roleLevel = roleLevel;
    }

    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public enum RoleLevel {
        ADMIN(1), GUEST(2), PUBLIC(3);
        private final int level;

        RoleLevel(int value) {
            this.level = value;
        }

        public static RoleLevel getLevel(String roleName) {
            return RoleLevel.valueOf(roleName);
        }

        public int getLevel() {
            return this.level;
        }
    }


}
