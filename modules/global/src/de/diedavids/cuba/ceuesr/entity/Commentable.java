package de.diedavids.cuba.ceuesr.entity;

import com.haulmont.cuba.core.entity.Entity;

import java.io.Serializable;

/**
 * marks all entity instances that can be commented
 * @param <T>
 */
public interface Commentable<T> extends Entity<T> {
}
