package de.diedavids.cuba.ceuesr.web.comment;

import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Notifications;
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


    @Subscribe
    protected void onInit(InitEvent event) {

        commentsField.addValueChangeListener(valueChangeEvent -> {
            Commentable value = valueChangeEvent.getValue();
            commentDc.getItem().setComments(value);
        });
    }

    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {
        Commentable comments = commentDc.getItem().getComments();

        if (comments != null) {
            /*
            set meta class of the field to the target meta class
             */
            commentsField.setMetaClass(comments.getMetaClass());
            commentsField.setValue(comments);
        }

    }
    

}