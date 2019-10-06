package de.diedavids.cuba.ceuesr.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import de.diedavids.cuba.entitysoftreference.EntitySoftReferenceConverter;

import javax.persistence.*;

@Table(name = "CEUESR_DOCUMENT")
@Entity(name = "ceuesr_Document")
public class Document extends StandardEntity {
    @Column(name = "NAME")
    protected String name;

    @MetaProperty(datatype = "EntitySoftReference")
    @Column(name = "REFERS_TO")
    @Convert(converter = EntitySoftReferenceConverter.class)
    protected com.haulmont.cuba.core.entity.Entity refersTo;

    @MetaProperty(datatype = "Documentable")
    @Column(name = "DOCUMENTS")
    @Convert(converter = DocumentableConverter.class)
    protected Documentable documents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID")
    protected FileDescriptor file;

    public Documentable getDocuments() {
        return documents;
    }

    public void setDocuments(Documentable documents) {
        this.documents = documents;
    }

    public FileDescriptor getFile() {
        return file;
    }

    public void setFile(FileDescriptor file) {
        this.file = file;
    }

    public com.haulmont.cuba.core.entity.Entity getRefersTo() {
        return refersTo;
    }

    public void setRefersTo(com.haulmont.cuba.core.entity.Entity refersTo) {
        this.refersTo = refersTo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}