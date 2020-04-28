
--------------------------------20200206------------------------------------------------

28042020--------------------------------------------------------------------------------
ALTER TABLE TBLPPKDOKUMENSIMATI RENAME COLUMN ID_RUJUKAN TO NO_RUJUKAN;


15042020--------------------------------------------------------------------------------

ALTER TABLE TBLPPKDOKUMENSIMATI
ADD (NO_RUJUKAN VARCHAR2(30));

06022020--------------------------------------------------------------------------------

--DROP TRIGGER PPKDOKUMENSIMATI_BI;
-- 2020/04/13 (ekptghqfat)

CREATE OR REPLACE TRIGGER PPKDOKUMENSIMATI_BI    
before insert on     TBLPPKDOKUMENSIMATI    for each row
begin    if :new.ID_DOKUMEN   is null    then SELECT (SELECT kod_negeri FROM tbllookupstate) || TO_CHAR (SYSDATE, 'YY') || PPKDOKUMENSIMATI_SEQ.NEXTVAL into :new.ID_DOKUMEN          from dual;    end if;    end;
/

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--06/02/2020 
--DROP SEQUENCE PPKDOKUMENSIMATI_SEQ;

-- 2020/04/13 (ekptghqfat)
CREATE SEQUENCE PPKDOKUMENSIMATI_SEQ
  START WITH 1
  MAXVALUE 999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;
  
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 
--ALTER TABLE TBLPPKDOKUMENSIMATI ADD ID_RUJUKAN VARCHAR2(30);
-- 2020/04/13 (ekptghqfat)
ALTER TABLE TBLPPKDOKUMENSIMATI ADD CONSTRAINT PPKDOKUMENSIMATI_PK PRIMARY KEY (ID_DOKUMEN) ENABLE;

CREATE TABLE TBLPPKDOKUMENSIMATI (
	ID_DOKUMEN NUMBER(16,0),
	ID_SIMATI NUMBER(16,0),
	ID_JENISDOKUMEN NUMBER(16,0),
	NO_RUJUKAN VARCHAR2(30),
	NAMA_DOKUMEN VARCHAR2(200),
	FORMAT VARCHAR2(100),
	SAIZ NUMBER(16,0),
	KANDUNGAN BLOB,
	TARIKH_MASUK DATE,
	ID_MASUK NUMBER(16,0),
	TARIKH_KEMASKINI DATE,
	ID_KEMASKINI NUMBER(16,0)
);

SELECT * FROM TBLPPKDOKUMENSIMATI
