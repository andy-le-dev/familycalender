package com.andyle.familycalender;

import java.util.Date;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;


@Entity
public class Appointment extends CalenderItem{
    public Date to;
}
