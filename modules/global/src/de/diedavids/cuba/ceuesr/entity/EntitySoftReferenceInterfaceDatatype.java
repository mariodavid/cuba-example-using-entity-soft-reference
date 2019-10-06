package de.diedavids.cuba.ceuesr.entity;

import com.google.common.base.Strings;
import com.haulmont.chile.core.datatypes.Datatype;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.ParseException;
import java.util.Locale;

public abstract class EntitySoftReferenceInterfaceDatatype<T extends Entity> implements Datatype<T> {


    @Nonnull
    @Override
    public String format(@Nullable Object value) {

        if (value == null) {
            return "";
        }

        EntityLoadInfoBuilder builder = getEntityLoadInfoBuilder();
        EntityLoadInfo entityLoadInfo = builder.create((Entity) value);
        return entityLoadInfo.toString();

    }

    private EntityLoadInfoBuilder getEntityLoadInfoBuilder() {
        return AppBeans.get(EntityLoadInfoBuilder.NAME);
    }

    @Nonnull
    @Override
    public String format(@Nullable Object value, Locale locale) {
        return format(value);
    }

    @Nullable
    @Override
    public T parse(@Nullable String value) throws ParseException {


        if (Strings.isNullOrEmpty(value))
            return null;


        EntityLoadInfoBuilder builder = getEntityLoadInfoBuilder();
        EntityLoadInfo entityLoadInfo = builder.parse(value);

        T entity = loadEntity(entityLoadInfo);

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

    @Nullable
    @Override
    public T parse(@Nullable String value, Locale locale) throws ParseException {
        return parse(value);
    }

}
