/*
 * Copyright (c) 2008-2018 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.diedavids.cuba.ceuesr.web;

import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.actions.picker.LookupAction;
import com.haulmont.cuba.gui.app.core.inputdialog.InputDialog;
import com.haulmont.cuba.gui.app.core.inputdialog.InputParameter;
import com.haulmont.cuba.gui.components.ActionType;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuesr.entity.Commentable;
import de.diedavids.cuba.ceuesr.web.comment.MetadataSelector;

import javax.inject.Inject;
import java.util.Map;

@ActionType(EntitySoftReferenceLookupAction.ID)
public class EntitySoftReferenceLookupAction extends LookupAction {


    public static final String ID = "entitySoftReferencePicker_lookup";

    private final String ENTITY_CLASS_PARAMETER_ID = "entityClass";

    @Inject
    protected UiComponents uiComponents;

    @Inject
    protected MetadataSelector metadataSelector;

    @Inject
    private Metadata metadata;


    public EntitySoftReferenceLookupAction() {
        super(EntitySoftReferenceLookupAction.ID);
    }

    public EntitySoftReferenceLookupAction(String id) {
        super(id);
    }


    @SuppressWarnings("unchecked")
    @Override
    public void actionPerform(Component component) {


        // if standard behaviour
        if (!hasSubscriptions(ActionPerformedEvent.class)) {


            Class entityClass = Commentable.class;
            Dialogs.InputDialogBuilder inputDialog = inputDialog();

            inputDialog
                    .withParameter(entityClassParameterForInterface(entityClass))
                    .withCaption("Select Entity Class")
                    .withCloseListener(closeEvent -> {
                        if (okWasClicked(closeEvent)) {

                            String entityClassValue = closeEvent.getValue(ENTITY_CLASS_PARAMETER_ID);

                            MetaClass metaClass = metadata.getClass(entityClassValue);

                            /*
                            the meta class is fetched from dialog and passed into the picker field. Afterwards the super method is called,
                            which in case the meta class is set will not treat it as a regular lookup with valueSource
                             */
                            pickerField.setMetaClass(metaClass);

                            super.actionPerform(component);

                        }
                    })
                    .show();

        } else {
            // call action perform handlers from super, delegate execution
            super.actionPerform(component);
        }
    }

    private Dialogs.InputDialogBuilder inputDialog() {
        FrameOwner frameOwner = pickerField.getFrame().getFrameOwner();

        ScreenContext screenContext = UiControllerUtils.getScreenContext(pickerField.getFrame().getFrameOwner());

        return screenContext
                .getDialogs().createInputDialog(frameOwner);
    }

    private boolean okWasClicked(InputDialog.InputDialogCloseEvent closeEvent) {
        return closeEvent.getCloseAction().equals(InputDialog.INPUT_DIALOG_OK_ACTION);
    }

    private InputParameter entityClassParameterForInterface(Class<? extends Entity> entityClass) {
        return InputParameter.parameter(ENTITY_CLASS_PARAMETER_ID)
                .withField(() -> {
                    LookupField field = uiComponents.create(LookupField.class);

                    field.setOptionsMap(getEntityClasses(entityClass));
                    field.setWidthFull();
                    field.setCaption("Entity Class");
                    return field;
                })
                .withRequired(true);
    }

    private Map<String, Object> getEntityClasses(Class<? extends Entity> entityClass) {
        return metadataSelector.getEntitiesLookupFieldOptions(entityClass);
    }

}