package com.andyle.familycalender;

import java.util.Date;


import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public abstract class CalenderItem extends PanacheEntity{
    public boolean allDayLong;
    public String title;
    public Date from;

}
