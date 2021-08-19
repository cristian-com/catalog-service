package com.cristian.external.database;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PostEntity extends PanacheEntity {

    public String content;
    public String reactions;
    public String tags;

    @OneToOne
    public StatusEntity status;

    @OneToOne
    public PrivacyEntity privacy;

}
