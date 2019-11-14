package com.andyle.familycalender;

import java.util.Date;

import javax.persistence.Entity;


@Entity
public class Appointment extends CalenderItem{
    public Date to;
}
