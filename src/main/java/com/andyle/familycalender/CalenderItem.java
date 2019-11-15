package com.andyle.familycalender;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CalenderItem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    public boolean allDayLong;
    public String title;


    public Date fromDate;

}
