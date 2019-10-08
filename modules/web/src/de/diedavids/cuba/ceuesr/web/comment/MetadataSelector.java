package de.diedavids.cuba.ceuesr.web.comment;

import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.app.dynamicattributes.DynamicAttributes;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.EntityOp;
import de.diedavids.cuba.ceuesr.entity.Commentable;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;
import java.util.TreeMap;

@Component("ceuesr_MetadataSelector")
public class MetadataSelector {

    @Inject
    private Metadata metadata;

    @Inject
    private Messages messages;

    @Inject
    private Security security;

    public Map<String, Object> getEntitiesLookupFieldOptions(Class<? extends Entity> entityClass) {
        TreeMap<String, Object> options = new TreeMap<>();

        for (MetaClass metaClass : getMetadataTools().getAllPersistentMetaClasses()) {
            if (readPermitted(metaClass)) {
                Class javaClass = metaClass.getJavaClass();
                if (entityClass.isAssignableFrom(javaClass)) {
                    options.put(getMessageTools().getEntityCaption(metaClass), metaClass.getName());
                }
            }
        }

        return options;
    }

    protected boolean readPermitted(MetaClass metaClass) {
        return entityOpPermitted(metaClass, EntityOp.READ);
    }

    protected boolean entityOpPermitted(MetaClass metaClass, EntityOp entityOp) {
        return security.isEntityOpPermitted(metaClass, entityOp);
    }

    private MessageTools getMessageTools() {
        return messages.getTools();
    }

    private MetadataTools getMetadataTools() {
        return metadata.getTools();
    }
}
