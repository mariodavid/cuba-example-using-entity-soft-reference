package de.diedavids.cuba.ceuesr.web.comment;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuesr.entity.Comment;

@UiController("ceuesr_Comment.edit")
@UiDescriptor("comment-edit.xml")
@EditedEntityContainer("commentDc")
@LoadDataBeforeShow
public class CommentEdit extends StandardEditor<Comment> {

}