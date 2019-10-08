package de.diedavids.cuba.ceuesr.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import de.diedavids.cuba.entitysoftreference.EntitySoftReferenceConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "CEUESR_COMMENT")
@Entity(name = "ceuesr_Comment")
public class Comment extends StandardEntity {
    private static final long serialVersionUID = -9012667801470279750L;

    @NotNull
    @Column(name = "TEXT", nullable = false)
    protected String text;

    @Column(name = "USERNAME")
    protected String username;

    @Basic
    @NotNull
    @MetaProperty(datatype = "EntitySoftReference", mandatory = true)
    @Column(name = "COMMENTS", nullable = false)
    @Convert(converter = EntitySoftReferenceConverter.class)
    protected Commentable comments;

    public Commentable getComments() {
        return comments;
    }

    public void setComments(Commentable comments) {
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}