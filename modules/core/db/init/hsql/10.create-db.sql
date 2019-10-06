-- begin CEUESR_CUSTOMER
create table CEUESR_CUSTOMER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end CEUESR_CUSTOMER
-- begin CEUESR_ORDER
create table CEUESR_ORDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ORDER_DATE date,
    --
    primary key (ID)
)^
-- end CEUESR_ORDER
-- begin CEUESR_DOCUMENT
create table CEUESR_DOCUMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    REFERS_TO varchar(255),
    DOCUMENTS varchar(255),
    FILE_ID varchar(36),
    --
    primary key (ID)
)^
-- end CEUESR_DOCUMENT
