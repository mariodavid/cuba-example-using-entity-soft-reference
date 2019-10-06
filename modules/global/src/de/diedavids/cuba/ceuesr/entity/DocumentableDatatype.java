package de.diedavids.cuba.ceuesr.entity;

public class DocumentableDatatype extends EntitySoftReferenceInterfaceDatatype<Documentable> {

    @Override
    public Class getJavaClass() {
        return Documentable.class;
    }

}
