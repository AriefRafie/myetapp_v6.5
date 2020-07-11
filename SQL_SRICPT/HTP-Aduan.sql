====================================================

INSERT INTO USER_MODULE_TEMPLATE
(TAB_ID, USER_LOGIN, MODULE_ID, "SEQUENCE", MODULE_CUSTOM_TITLE, COLUMN_NUMBER)
VALUES('1594262361517', '(HTP)HQPengguna', 'ekptg.view.htp.online.aduan.AduanTanah', 1, 'Agihan Aduan', 0);

INSERT INTO USER_MODULE_TEMPLATE
(TAB_ID, USER_LOGIN, MODULE_ID, "SEQUENCE", MODULE_CUSTOM_TITLE, COLUMN_NUMBER)
VALUES('1594262361517', '(HTP)AdminHTP', 'ekptg.view.htp.online.aduan.AduanTanah', 1, 'Agihan Aduan', 0);


SELECT * FROM USER_MODULE_TEMPLATE
WHERE 
--USER_LOGIN LIKE '%htp-online-user%'
MODULE_ID LIKE '%AduanTanah%'


INSERT INTO ROLE_MODULE
(MODULE_ID, USER_ROLE)
VALUES('ekptg.view.htp.online.aduan.PengurusanAduanTanah', '(HTP)HQPegawai');
INSERT INTO ROLE_MODULE
(MODULE_ID, USER_ROLE)
VALUES('ekptg.view.htp.online.aduan.PengurusanAduanTanah', '(HTP)HQPegawai1');
INSERT INTO ROLE_MODULE
(MODULE_ID, USER_ROLE)
VALUES('ekptg.view.htp.online.aduan.PengurusanAduanTanah', '(HTP)HQPegawaiPerolehan');
INSERT INTO ROLE_MODULE
(MODULE_ID, USER_ROLE)
VALUES('ekptg.view.htp.online.aduan.PengurusanAduanTanah', '(HTP)HQPengarah');
INSERT INTO ROLE_MODULE
(MODULE_ID, USER_ROLE)
VALUES('ekptg.view.htp.online.aduan.PengurusanAduanTanah', '(HTP)HQPengguna');
INSERT INTO ROLE_MODULE
(MODULE_ID, USER_ROLE)
VALUES('ekptg.view.htp.online.aduan.AduanTanah', '(HTP)HQPegawai');
INSERT INTO ROLE_MODULE
(MODULE_ID, USER_ROLE)
VALUES('ekptg.view.htp.online.aduan.AduanTanah', '(HTP)HQPegawai1');
INSERT INTO ROLE_MODULE
(MODULE_ID, USER_ROLE)
VALUES('ekptg.view.htp.online.aduan.AduanTanah', '(HTP)HQPegawaiPerolehan');
INSERT INTO ROLE_MODULE
(MODULE_ID, USER_ROLE)
VALUES('ekptg.view.htp.online.aduan.AduanTanah', '(HTP)HQPengarah');
INSERT INTO ROLE_MODULE
(MODULE_ID, USER_ROLE)
VALUES('ekptg.view.htp.online.aduan.AduanTanah', '(HTP)HQPengguna');

SELECT * FROM ROLE_MODULE
WHERE MODULE_ID LIKE '%AduanTanah%'

INSERT INTO MODULE
(MODULE_ID, MODULE_TITLE, MODULE_CLASS, MODULE_GROUP, MODULE_DESCRIPTION, MODULE_VERSION, ID_MASUK, ID_KEMASKINI, TARIKH_MASUK, TARIKH_KEMASKINI)
VALUES('ekptg.view.htp.online.aduan.AduanTanah', 'Pengurusan Aduan Manual', 'ekptg.view.htp.online.aduan.AduanTanah', 'EKPTG - HTP', NULL, '1.0', 1, 1, TIMESTAMP '2020-04-27 00:00:00.000000', TIMESTAMP '2020-04-27 00:00:00.000000');

SELECT * FROM MODULE
WHERE MODULE_ID LIKE '%AduanTanah%'