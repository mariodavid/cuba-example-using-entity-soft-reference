package de.diedavids.cuba.ceuesr.web.comment;

import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuesr.entity.Comment;
import de.diedavids.cuba.entitysoftreference.web.SoftReferenceInstanceNameTableColumnGenerator;

import javax.inject.Inject;

@UiController("ceuesr_Comment.browse")
@UiDescriptor("comment-browse.xml")
@LookupComponent("commentsTable")
@LoadDataBeforeShow
public class CommentBrowse extends StandardLookup<Comment> {

    @Inject
    protected GroupTable<Comment> commentsTable;

    @Inject
    protected UiComponents uiComponents;

    @Inject
    protected MetadataTools metadataTools;

    @Inject
    protected ScreenBuilders screenBuilders;

    @Subscribe
    protected void onInit(InitEvent event) {
        commentsTable.addGeneratedColumn("comments",
                new SoftReferenceInstanceNameTableColumnGenerator(
                        "comments",
                        uiComponents,
                        metadataTools,
                        screenBuilders,
                        this
                )
        );
    }
}