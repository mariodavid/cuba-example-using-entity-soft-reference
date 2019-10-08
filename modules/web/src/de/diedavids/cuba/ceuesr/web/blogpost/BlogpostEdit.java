package de.diedavids.cuba.ceuesr.web.blogpost;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuesr.entity.Blogpost;

@UiController("ceuesr_Blogpost.edit")
@UiDescriptor("blogpost-edit.xml")
@EditedEntityContainer("blogpostDc")
@LoadDataBeforeShow
public class BlogpostEdit extends StandardEditor<Blogpost> {
}