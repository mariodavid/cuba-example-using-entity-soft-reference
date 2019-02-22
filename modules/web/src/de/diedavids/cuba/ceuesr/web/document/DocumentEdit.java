package de.diedavids.cuba.ceuesr.web.document;

import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.builders.LookupBuilder;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuesr.entity.Customer;
import de.diedavids.cuba.ceuesr.entity.Document;
import de.diedavids.cuba.ceuesr.entity.Order;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@UiController("ceuesr_Document.edit")
@UiDescriptor("document-edit.xml")
@EditedEntityContainer("documentDc")
@LoadDataBeforeShow
public class DocumentEdit extends StandardEditor<Document> {

    @Inject
    protected InstanceContainer<Document> documentDc;

    @Inject
    protected ScreenBuilders screenBuilders;

    @Subscribe("assignToCustomer")
    protected void onAssignToCustomer(Action.ActionPerformedEvent event) {
        screenBuilders.lookup(Customer.class, this).withSelectHandler(customers -> {
            Customer customer = new LinkedList<>(customers).getFirst();
            documentDc.getItem().setRefersTo(customer);
        }).build().show();
    }

    @Subscribe("assignToOrder")
    protected void onAssignToOrder(Action.ActionPerformedEvent event) {
        screenBuilders.lookup(Order.class, this).withSelectHandler(orders -> {
            Order order = new LinkedList<>(orders).getFirst();
            documentDc.getItem().setRefersTo(order);
        }).build().show();
    }

    
    
}