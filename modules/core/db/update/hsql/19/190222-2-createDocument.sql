alter table CEUESR_DOCUMENT add constraint FK_CEUESR_DOCUMENT_ON_FILE foreign key (FILE_ID) references SYS_FILE(ID);
create index IDX_CEUESR_DOCUMENT_ON_FILE on CEUESR_DOCUMENT (FILE_ID);
