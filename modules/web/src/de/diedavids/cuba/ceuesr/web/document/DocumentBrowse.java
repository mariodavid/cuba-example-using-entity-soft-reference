package de.diedavids.cuba.ceuesr.web.document;

import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuesr.entity.Document;
import de.diedavids.cuba.entitysoftreference.web.SoftReferenceInstanceNameTableColumnGenerator;

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
        documentsTable.addGeneratedColumn("refersToInstanceName",
                new SoftReferenceInstanceNameTableColumnGenerator(
                        "refersTo",
                        uiComponents,
                        metadataTools,
                        screenBuilders,
                        this
                        ));
    }
}
