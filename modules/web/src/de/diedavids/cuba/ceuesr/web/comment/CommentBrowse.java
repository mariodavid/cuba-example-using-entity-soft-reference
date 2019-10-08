package de.diedavids.cuba.ceuesr.web.comment;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.app.core.inputdialog.InputDialog;
import com.haulmont.cuba.gui.app.core.inputdialog.InputParameter;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuesr.entity.Comment;
import de.diedavids.cuba.ceuesr.entity.Commentable;

import javax.inject.Inject;
import java.util.Map;

@UiController("ceuesr_Comment.browse")
@UiDescriptor("comment-browse.xml")
@LookupComponent("commentsTable")
@LoadDataBeforeShow
public class CommentBrowse extends StandardLookup<Comment> {

    @Inject
    protected ScreenBuilders screenBuilders;
    @Inject
    protected Notifications notifications;
    @Inject
    protected Dialogs dialogs;
    @Inject
    protected UiComponents uiComponents;

    @Inject
    protected MetadataSelector metadataSelector;

    @Subscribe("commentsTable.selectEntity")
    protected void onCommentsTableSelectEntity(Action.ActionPerformedEvent event) {

        Class<Commentable> entityClass = Commentable.class;

        dialogs.createInputDialog(this)
                .withParameter(
                        InputParameter.parameter("entityClass")
                        .withField(() -> {
                            LookupField field = uiComponents.create(LookupField.class);

                            field.setOptionsMap(getEntityClasses(entityClass));
                            field.setCaption("Entity Class");
                            return field;
                        })
                                .withRequired(true)
                )
                .withCaption("Select Entity Class")
                .withCloseListener(closeEvent -> {
                    if (closeEvent.getCloseAction().equals(InputDialog.INPUT_DIALOG_OK_ACTION)) {
                        notifications.create(Notifications.NotificationType.TRAY)
                                .withCaption(closeEvent.getValue("entityClass"))
                                .show();
                    }
                })
                .show();
    }

    private Map<String, Object> getEntityClasses(Class<? extends Entity> entityClass) {
        return metadataSelector.getEntitiesLookupFieldOptions(entityClass);
    }


}