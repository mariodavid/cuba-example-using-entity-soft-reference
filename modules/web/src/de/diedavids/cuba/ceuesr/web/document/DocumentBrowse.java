package de.diedavids.cuba.ceuesr.web.document;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.LinkButton;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuesr.entity.Document;

import javax.inject.Inject;

@UiController("ceuesr_Document.browse")
@UiDescriptor("document-browse.xml")
@LookupComponent("documentsTable")
@LoadDataBeforeShow
public class DocumentBrowse extends StandardLookup<Document> {

    @Inject
    protected GroupTable<Document> documentsTable;

    @Inject
    protected UiComponents uiComponents;

    @Inject
    protected MetadataTools metadataTools;

    @Inject
    protected ScreenBuilders screenBuilders;

    @Subscribe
    protected void onInit(InitEvent event) {
        documentsTable.addGeneratedColumn("refersToInstanceName", new Table.ColumnGenerator<Document>() {
            @Override
            public Component generateCell(Document entity) {
                LinkButton linkButton = uiComponents.create(LinkButton.class);

                Entity refersTo = entity.getRefersTo();
                linkButton.setCaption(metadataTools.getInstanceName(refersTo));

                linkButton.addClickListener(clickEvent -> {
                    screenBuilders.editor((Class<Entity>) refersTo.getClass(), DocumentBrowse.this)
                            .editEntity(refersTo)
                            .build().show();
                });

                return linkButton;
            }
        });
    }
    
    
}