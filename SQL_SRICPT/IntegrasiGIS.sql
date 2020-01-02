
--2017/09/27
-- 2017/10/02 - Run POC inviroment, db ekptghq  
DROP VIEW VGIS_SENARAI_CHARTING;

/* Formatted on 2017/09/25 16:25 (Formatter Plus v4.8.8) */
CREATE OR REPLACE FORCE VIEW vgis_senarai_charting (no_fail,
                                                            tujuan,
                                                            tarikh_daftar,
                                                            kod_negeri,
                                                            nama_negeri,
                                                            kod_daerah,
                                                            kod_mukim,
                                                            sek,
                                                            luas,
                                                            keterangan_luas,
                                                            kod_lot,
                                                            no_lot,
                                                            kod_hakmilik,
                                                            no_hakmilik,
                                                            latitude,
                                                            longitude,
                                                            id_masuk,
                                                            upi,
                                                            kod_kementerian,
                                                            kod_agensi,
                                                            status_tanah,
                                                            keterangan_statustanah
                                                           )
AS
SELECT f.no_fail, p.tujuan, f.tarikh_daftar_fail tarikh_daftar,
          rn.kod_negeri, rn.nama_negeri, NVL (rd.kod_daerah, '0') kod_daerah,
          NVL (rm.kod_mukim, '0') kod_mukim, '000' sek,
          NVL (mt.luas_bersamaan, 0) luas,
          NVL (rl.keterangan, 'TIADA MAKLUMAT') keterangan_luas,
          NVL (lot.keterangan, 'TIADA') kod_lot, NVL (mt.no_lot, '0') no_lot,
          NVL (rjh.keterangan, '0') kod_hakmilik,
          NVL (mt.no_hakmilik, '0') no_hakmilik,
          NVL (gis.latitude, 'TIADA') latitude,
          NVL (gis.longitude, 'TIADA') longitude,
          NVL (gis.id_charter, 0) id_masuk, NVL (gis.upi, 'TIADA') upi,
          rk.kod_kementerian, ra.kod_agensi, irs.kod_status status_tanah,
          irs.keterangan keterangan_statustanah
     FROM tblintgis gis,
          tblpfdfail f,
          tblpermohonan p,
          tblhtppermohonan pp,
          tblrujnegeri rn,
          tblrujdaerah rd,
          tblrujkementerian rk,
          tblrujagensi ra,
          tblhtphakmilikurusan mt,
          tblrujmukim rm,
          tblrujluas rl,
          tblrujlot lot,
          tblrujjenishakmilik rjh,
          tblintrujstatus irs
    WHERE gis.no_fail = f.no_fail
      AND gis.status_tanah = GETGISTATUSPERMOHONAN(f.ID_URUSAN)
      AND gis.status_tanah = irs.kod_status
      AND f.id_fail = p.id_fail
      AND f.id_negeri = rn.id_negeri
      AND pp.id_daerah = rd.id_daerah
      AND p.id_permohonan = pp.id_permohonan
      AND f.id_kementerian = rk.id_kementerian
      AND pp.id_agensi = ra.id_agensi
      AND p.id_permohonan = mt.id_permohonan(+)
      AND mt.id_mukim = rm.id_mukim(+)
      AND mt.id_luas_bersamaan = rl.id_luas(+)
      AND mt.id_lot = lot.id_lot(+)
      AND mt.id_jenishakmilik = rjh.id_jenishakmilik(+)
      AND gis.status_tanah IN (2, 3)
      AND P.ID_STATUS <> 8
   UNION
   SELECT gis.no_fail, mt.kegunaan_tanah tujuan,
          mt.tarikh_terima tarikh_daftar, rn.kod_negeri, rn.nama_negeri,
          NVL (rd.kod_daerah, '0') kod_daerah,
          NVL (rm.kod_mukim, '0') kod_mukim, '000' sek,
          NVL (mt.luas_bersamaan, 0) luas,
          NVL (rl.keterangan, 'TIADA MAKLUMAT') keterangan_luas,
          NVL (lot.keterangan, 'TIADA') kod_lot, NVL (mt.no_lot, '0') no_lot,
          NVL (rjh.keterangan, '0') kod_hakmilik,
          NVL (mt.no_hakmilik, '0') no_hakmilik,
          NVL (gis.latitude, 'TIADA') latitude,
          NVL (gis.longitude, 'TIADA') longitude,
          NVL (gis.id_charter, 0) id_masuk, NVL (gis.upi, 'TIADA') upi,
          rk.kod_kementerian, ra.kod_agensi, irs.kod_status status_tanah,
          irs.keterangan keterangan_statustanah
     FROM tblintgis gis,
          tblpfdfail f,
          tblpermohonan p,
          tblhtppermohonan pp,
          tblrujnegeri rn,
          tblrujdaerah rd,
          tblrujkementerian rk,
          tblrujagensi ra,
          tblhtphakmilik mt,
          tblrujmukim rm,
          tblrujluas rl,
          tblrujlot lot,
          tblrujjenishakmilik rjh,
          tblintrujstatus irs
    WHERE gis.no_fail = f.no_fail
      AND gis.status_tanah = irs.kod_status
      AND gis.UPI = GETUPI(RN.KOD_NEGERI,RD.KOD_DAERAH_UPI,RM.KOD_MUKIM_UPI,'000',RJH.STATUS_HAKMILIK,MT.NO_LOT,MT.NO_HAKMILIK,RJH.KOD_JENIS_HAKMILIK)
      AND f.id_fail = p.id_fail
      AND f.id_negeri = rn.id_negeri
      AND pp.id_daerah = rd.id_daerah
      AND p.id_permohonan = pp.id_permohonan
      AND f.id_kementerian = rk.id_kementerian
      AND pp.id_agensi = ra.id_agensi
      AND p.id_permohonan = mt.id_permohonan(+)
      AND mt.id_mukim = rm.id_mukim(+)
      AND mt.id_luas_bersamaan = rl.id_luas(+)
      AND mt.id_lot = lot.id_lot(+)
      AND mt.id_jenishakmilik = rjh.id_jenishakmilik(+)
      AND gis.status_tanah IN (4, 5, 6);


--2017/05/09 Run Staging inviroment, db staging
--2017/05/09 Run POC inviroment, db ekptg   
--2017/10/02 Run POC inviroment, db ekptghq -  update UPI kepada VARCHAR2(45 BYTE)

--table integrasi GIS
ALTER TABLE TBLINTGIS
 DROP PRIMARY KEY CASCADE;
DROP TABLE TBLINTGIS CASCADE CONSTRAINTS;

CREATE TABLE TBLINTGIS
(
  ID                NUMBER                      NOT NULL,
  TARIKH_HANTAR     DATE,
  NO_FAIL           VARCHAR2(50 BYTE),
  ID_NEGERI         NUMBER,
  ID_DAERAH         NUMBER,
  ID_MUKIM          NUMBER,
  ID_SEKSYEN        NUMBER,
  LUAS_BERSAMAAN    NUMBER,
  KOD_LOT           NUMBER,
  NO_LOT            VARCHAR2(8 BYTE),
  JENIS_HAKMILIK    VARCHAR2(20 BYTE),
  NO_HAKMILIK       VARCHAR2(15 BYTE),
  NO_WARTA          VARCHAR2(15 BYTE),
  NAMA_KEMENTERIAN  VARCHAR2(50 BYTE),
  NAMA_AGENSI       VARCHAR2(50 BYTE),
  TARIKH_TERIMA     DATE,
  TARIKH_TERIMA_HAKMILIK     DATE,
  TARIKH_DAFTAR_HAKMILIK     DATE,
  STATUS_TANAH      NUMBER,
  LONGITUDE         VARCHAR2(20 BYTE),
  LATITUDE          VARCHAR2(20 BYTE),
  UPI               VARCHAR2(45 BYTE),
  ID_CHARTER		NUMBER,
  ID_MASUK          NUMBER,
  TARIKH_MASUK     DATE,
  ID_KEMASKINI      NUMBER,
  TARIKH_KEMASKINI     DATE

)
TABLESPACE ETAPP01
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
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

CREATE SEQUENCE TBLINTGIS_SEQ
  START WITH 0
  MAXVALUE 9999999999999999999999999999
  MINVALUE 0
  NOCYCLE
  NOCACHE
  NOORDER;
  
  CREATE UNIQUE INDEX TBLINTGIS_PK ON TBLINTGIS
(ID)
LOGGING
TABLESPACE ETAPP01
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE OR REPLACE TRIGGER "TBLINTGIS_BI" 
before insert on     TBLINTGIS    for each row
begin    if :new.ID   is null    then SELECT (SELECT kod_negeri FROM tbllookupstate) || TO_CHAR (SYSDATE, 'YY') || TBLINTGIS_SEQ.NEXTVAL into :new.ID           from dual;    end if;    end;
/


ALTER TABLE TBLINTGIS ADD (
  CONSTRAINT TBLINTGIS_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE ETAPP01
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));