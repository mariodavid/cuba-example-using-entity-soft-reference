package de.diedavids.cuba.ceuesr.entity;

import com.google.common.base.Strings;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.*;

import javax.persistence.AttributeConverter;

public abstract class EntitySoftReferenceInterfaceConverter<T extends Entity> implements AttributeConverter<T, String> {

    @Override
    public String convertToDatabaseColumn(T softReference) {

        if (softReference == null)
            return "";

        EntityLoadInfoBuilder builder = getEntityLoadInfoBuilder();

        EntityLoadInfo entityLoadInfo = builder.create(softReference);


        return entityLoadInfo.toString();
    }

    @Override
    public T convertToEntityAttribute(String value) {


        if (Strings.isNullOrEmpty(value))
            return null;

        EntityLoadInfoBuilder builder = getEntityLoadInfoBuilder();
        EntityLoadInfo entityLoadInfo = builder.parse(value);

        T entity = null;

        if (entityLoadInfo != null) {
            entity = loadEntity(entityLoadInfo);
        }

        return entity;
    }

    private T loadEntity(EntityLoadInfo entityLoadInfo) {
        DataManager dataManager = getDataManager();
        return (T) dataManager.load(getLoadContextForForEntityLoadInfo(entityLoadInfo.getMetaClass(), entityLoadInfo.getId()));
    }

    private DataManager getDataManager() {
        return AppBeans.get(DataManager.NAME);
    }


    protected LoadContext getLoadContextForForEntityLoadInfo(MetaClass metaClass, Object entityId) {
        LoadContext loadContext = LoadContext.create(metaClass.getJavaClass());
        loadContext
                .setId(entityId);
        return loadContext;
    }



    private EntityLoadInfoBuilder getEntityLoadInfoBuilder() {
        return AppBeans.get(EntityLoadInfoBuilder.NAME);
    }

}
