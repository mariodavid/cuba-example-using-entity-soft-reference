package de.diedavids.cuba.ceuesr.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.swing.text.Document;
import java.util.Date;
import java.util.UUID;

@NamePattern("%s|orderDate")
@Table(name = "CEUESR_ORDER")
@Entity(name = "ceuesr_Order")
public class Order extends StandardEntity implements Documentable<UUID> {

    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE")
    protected Date orderDate;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}