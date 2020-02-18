
--------------------------------20200206------------------------------------------------



06022020--------------------------------------------------------------------------------

DROP TRIGGER PPKDOKUMENSIMATI_BI;

CREATE OR REPLACE TRIGGER PPKDOKUMENSIMATI_BI    
before insert on     TBLPPKDOKUMENSIMATI    for each row
begin    if :new.ID_DOKUMEN   is null    then SELECT (SELECT kod_negeri FROM tbllookupstate) || TO_CHAR (SYSDATE, 'YY') || PPKDOKUMENSIMATI_SEQ.NEXTVAL into :new.ID_DOKUMEN          from dual;    end if;    end;
/

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--06/02/2020 
DROP SEQUENCE PPKDOKUMENSIMATI_SEQ;


CREATE SEQUENCE PPKDOKUMENSIMATI_SEQ
  START WITH 1
  MAXVALUE 999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;
  
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 
CREATE TABLE TBLPPKDOKUMENSIMATI (
	ID_DOKUMEN NUMBER(16,0),
	ID_SIMATI NUMBER(16,0),
	ID_JENISDOKUMEN NUMBER(16,0),
	NAMA_DOKUMEN VARCHAR2(200),
	FORMAT VARCHAR2(100),
	SAIZ NUMBER(16,0),
	KANDUNGAN BLOB,
	TARIKH_MASUK DATE,
	ID_MASUK NUMBER(16,0),
	TARIKH_KEMASKINI DATE,
	ID_KEMASKINI NUMBER(16,0)
);