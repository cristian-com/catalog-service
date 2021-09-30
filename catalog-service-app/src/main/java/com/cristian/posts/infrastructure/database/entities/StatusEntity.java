package com.cristian.posts.infrastructure.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class StatusEntity extends PanacheEntity {
    public String name;
}
