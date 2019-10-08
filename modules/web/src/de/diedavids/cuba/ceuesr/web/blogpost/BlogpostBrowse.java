package de.diedavids.cuba.ceuesr.web.blogpost;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuesr.entity.Blogpost;

@UiController("ceuesr_Blogpost.browse")
@UiDescriptor("blogpost-browse.xml")
@LookupComponent("blogpostsTable")
@LoadDataBeforeShow
public class BlogpostBrowse extends StandardLookup<Blogpost> {
}