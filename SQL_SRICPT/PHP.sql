
--------------------------------20200206------------------------------------------------

DROP TRIGGER PHPRUJJENISTUJUAN_BI;

CREATE OR REPLACE TRIGGER PHPRUJJENISTUJUAN_BI    
before insert on     TBLPHPRUJJENISTUJUAN    for each row
begin    if :new.ID_JENISTUJUAN   is null    then SELECT (SELECT kod_negeri FROM tbllookupstate) || TO_CHAR (SYSDATE, 'YY') || PHPRUJJENISTUJUAN_SEQ.NEXTVAL into :new.ID_JENISTUJUAN          from dual;    end if;    end;
/

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
DROP SEQUENCE PHPRUJJENISTUJUAN_SEQ;

CREATE SEQUENCE PHPRUJJENISTUJUAN_SEQ
  START WITH 1
  MAXVALUE 999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;
