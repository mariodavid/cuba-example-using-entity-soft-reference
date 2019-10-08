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
import com.haulmont.cuba.client.ClientConfig;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.DevelopmentException;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.actions.picker.LookupAction;
import com.haulmont.cuba.gui.components.ActionType;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.icons.CubaIcon;
import com.haulmont.cuba.gui.icons.Icons;
import com.haulmont.cuba.gui.screen.FrameOwner;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.gui.screen.Screen;
import de.diedavids.cuba.ceuesr.entity.Blogpost;
import org.springframework.beans.factory.InitializingBean;

import javax.inject.Inject;

@ActionType(EntitySoftReferenceLookupAction.ID)
public class EntitySoftReferenceLookupAction extends LookupAction {


    public static final String ID = "entitySoftReferencePicker_lookup";


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
            FrameOwner frameOwner = pickerField.getFrame().getFrameOwner();


            screenBuilders.screen(frameOwner)
                    .withScreenClass(EntitySoftReferenceInterfaceSelection.class)
                    .withOpenMode(OpenMode.DIALOG)
                    .withAfterCloseListener(entitySoftReferenceInterfaceSelectionAfterScreenCloseEvent -> {
                        String selectedEntityClass = entitySoftReferenceInterfaceSelectionAfterScreenCloseEvent.getScreen().getSelectedEntityClass();

                        System.out.println(selectedEntityClass);
                    })
                    .show();



            Screen lookupScreen = screenBuilders.lookup(Blogpost.class, frameOwner)
                    .withSelectHandler(blogposts -> {
                        System.out.println(blogposts);
                    })
                    .build();
            lookupScreen.show();
        } else {
            // call action perform handlers from super, delegate execution
            super.actionPerform(component);
        }
    }
}