package com.cristian.external.database;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class PrivacyEntity extends PanacheEntity  {
    public String name;
}
