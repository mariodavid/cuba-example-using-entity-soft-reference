package de.diedavids.cuba.ceuesr.web.comment;

import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.data.value.ContainerValueSource;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuesr.entity.Comment;
import de.diedavids.cuba.ceuesr.entity.Commentable;
import de.diedavids.cuba.ceuesr.web.EntitySoftReferenceLookupAction;
import de.diedavids.cuba.entitysoftreference.web.SoftReferenceFormFieldGenerator;

import javax.inject.Inject;
import javax.inject.Named;

@UiController("ceuesr_Comment.edit")
@UiDescriptor("comment-edit.xml")
@EditedEntityContainer("commentDc")
@LoadDataBeforeShow
public class CommentEdit extends StandardEditor<Comment> {


    @Inject
    protected InstanceContainer<Comment> commentDc;
    @Inject
    protected PickerField<Commentable> commentsField;
    @Inject
    private Form form;


    @Subscribe
    protected void onInit(InitEvent event) {
        initSoftReferenceFormField(form, commentDc, "comments");
    }


    @Inject
    protected UiComponentsGenerator uiComponentsGenerator;

    @Inject
    protected Messages messages;


    /**
     * initialized the soft reference as a form field
     * @param form the destination form component instance
     * @param container the instance container
     * @param property the soft reference property
     */
    public void initSoftReferenceFormField(Form form, InstanceContainer container, String property) {

        Field field = generateSoftReferenceField(property, container);

        setCaption(property, container, field);
        setValueSource(property, container, field);

        form.add(field);


    }

    private void setValueSource(String property, InstanceContainer container, Field field) {
        field.setValueSource(new ContainerValueSource<>(container, property));
    }

    private void setCaption(String property, InstanceContainer container, Field field) {
        String propertyCaption = messages.getTools().getPropertyCaption(container.getEntityMetaClass(), property);
        field.setCaption(propertyCaption);
    }

    private Field generateSoftReferenceField(String property, InstanceContainer container) {
        return (Field) uiComponentsGenerator.generate(new ComponentGenerationContext(container.getEntityMetaClass(), property));
    }
}