package de.diedavids.cuba.ceuesr.web.document;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Form;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuesr.entity.Customer;
import de.diedavids.cuba.ceuesr.entity.Document;
import de.diedavids.cuba.ceuesr.entity.Order;
import de.diedavids.cuba.entitysoftreference.web.SoftReferenceFormFieldGenerator;

import javax.inject.Inject;
import java.util.LinkedList;

@UiController("ceuesr_Document.edit")
@UiDescriptor("document-edit.xml")
@EditedEntityContainer("documentDc")
@LoadDataBeforeShow
public class DocumentEdit extends StandardEditor<Document> {

    @Inject
    protected InstanceContainer<Document> documentDc;

    @Inject
    protected ScreenBuilders screenBuilders;

    @Inject
    private Form form;

    @Inject
    private SoftReferenceFormFieldGenerator softReferenceFormFieldGenerator;


    @Subscribe
    protected void onInit(InitEvent event) {
        softReferenceFormFieldGenerator.initSoftReferenceFormField(form, documentDc, "refersTo");
    }

    @Subscribe("assignToCustomer")
    protected void onAssignToCustomer(Action.ActionPerformedEvent event) {
        screenBuilders.lookup(Customer.class, this).withSelectHandler(customers -> {
            Customer customer = new LinkedList<>(customers).getFirst();
            setSoftReferenceValue(customer);
        }).build().show();
    }

    @Subscribe("assignToOrder")
    protected void onAssignToOrder(Action.ActionPerformedEvent event) {
        screenBuilders.lookup(Order.class, this).withSelectHandler(orders -> {
            Order order = new LinkedList<>(orders).getFirst();
            setSoftReferenceValue(order);
        }).build().show();
    }

    private void setSoftReferenceValue(Entity softReferenceValue) {
        documentDc.getItem().setRefersTo(softReferenceValue);
    }
    
    
}