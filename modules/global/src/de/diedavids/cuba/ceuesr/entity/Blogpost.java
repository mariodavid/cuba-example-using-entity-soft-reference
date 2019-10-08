package de.diedavids.cuba.ceuesr.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;

@NamePattern("%s|title")
@Table(name = "CEUESR_BLOGPOST")
@Entity(name = "ceuesr_Blogpost")
public class Blogpost extends StandardEntity {
    private static final long serialVersionUID = 8588926903533626069L;

    @Column(name = "TITLE")
    protected String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WRITTEN_BY_ID")
    protected User writtenBy;

    @Lob
    @Column(name = "TEXT")
    protected String text;

    public User getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(User writtenBy) {
        this.writtenBy = writtenBy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}