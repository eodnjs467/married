package com.daewon.married.entity;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
public class Rating {

    @Temporal(TemporalType.DATE)
    Date addDate;

}
