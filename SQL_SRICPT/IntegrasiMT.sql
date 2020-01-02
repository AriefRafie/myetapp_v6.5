--08022018 kak ecah
ALTER TABLE TBLINTMTKEPUTUSAN
 ADD (FLAG_BUKA  VARCHAR2(1 BYTE)                   DEFAULT 'T');


--2017/04/21 Run Staging inviroment, db staging
ALTER TABLE tblintmtkeputusan
ADD (catatan VARCHAR2(4000));

ALTER TABLE tblintmtkeputusan
ADD (jeniskp VARCHAR2(3));

--25042017 kak ecah
ALTER TABLE tblintmtperintah
ADD (tarikh_hantar DATE);

COMMENT ON COLUMN
tblintmtperintah.tarikh_hantar IS
'TARIKH HANTAR KE INT MAHKAMAH';
--25042017 end add kak ecah

--tbltransaksi
CREATE TABLE tbllogtransaksimtppk
(
  id_log            NUMBER                      NOT NULL,
  id_permohonan     NUMBER,
  jenis_data        NUMBER,
  tarikh_transaksi  DATE,
  id_status         VARCHAR2(10 BYTE),
  error_msg         VARCHAR2(100 BYTE),
  id_masuk          NUMBER
)
TABLESPACE etapp01
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64 k
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;

CREATE SEQUENCE tbllogtransaksimtppk_seq
  START WITH 14
  MAXVALUE 999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;

-- ADD BY PEJE 20062017 - TO CAPTURE TARIKH HANTAR BORANG B KE MT
ALTER TABLE tblintmtpermohonan
 ADD (tarikh_hantar  DATE);
ALTER TABLE tblintmtpermohonan
ADD (jeniskp VARCHAR2(3));

ALTER TABLE tblintmtpermohonan
 DROP PRIMARY KEY CASCADE
 KEEP INDEX;
DROP SEQUENCE tblintmtpermohonan_seq;
DROP TRIGGER tblintmtpermohonan_bi;
DROP INDEX tblintmtpermohonan_pk;

-- ADD BY PEJE 04072017 - TO CAPTURE CONTENT BORANG C
ALTER TABLE tblintmtkeputusan
 ADD (docid  INTEGER);

ALTER TABLE tblintmtkeputusan
 ADD (doctype  VARCHAR2(150 BYTE));

ALTER TABLE tblintmtkeputusan
 ADD (content  BLOB);

ALTER TABLE tblintmtkeputusan
MODIFY(docid VARCHAR2(100 BYTE));

ALTER TABLE tblintmtkeputusan
 ADD (doc_encoded  CLOB);

 --ADD BY AISHAH 28072017
ALTER  TABLE tblintmtpermohonan
 ADD (flag_aktif  VARCHAR2(1 BYTE) DEFAULT 'Y');

ALTER  TABLE tblintmtkeputusan
 ADD (flag_aktif  VARCHAR2(1 BYTE) DEFAULT 'Y');
 ---END

ALTER  TABLE tblintmtkeputusan DROP COLUMN doc_encoded;
ALTER TABLE TBLINTMTKEPUTUSAN
 ADD (TARIKH_TERIMA  DATE);
