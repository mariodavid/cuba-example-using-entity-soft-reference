package de.diedavids.cuba.ceuesr.entity;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class DocumentableConverter extends EntitySoftReferenceInterfaceConverter<Documentable> {

}
