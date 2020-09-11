--Query lookup Daerah:
SELECT * FROM TBLRUJPEJABAT WHERE ID_JENISPEJABAT IN (2, 81) AND ID_NEGERI = 2

-- 2: Kedah
-- 5: Negeri Sembilan
-- 7: Pulau Pinang
-- 10: Selangor
-- 11: Terengganu
-- Daerah Ada/Dah Masuk: Kota Setar, Tampin, Kuala Selangor, Seberang Perai Selatan dan Kuala Terengganu

-- Negeri Kedah
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(99202, 20, 327, 'b0eab9c7-69bc-4c3c-8a73-01f01c20d182', 'aaea5021-9985-45ed-817e-44d7c20d0d67', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Kota Setar');

-- Pejabat Penasihat Undang-Undang Negeri Sembilan|
-- Negeri Sembilan
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(99205, 267, 330, '888cf963-1226-40f1-92c0-1ae94bf60d5f', 'b46e0723-9bf3-46c3-95f6-625e7db92981', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Tampin');

--Pejabat Penasihat Undang-Undang Negeri Selangor|
-- Negeri Selangor
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(99204, 247, 331, '3c25fc26-e9b9-4d8d-9979-36936ffa9cc4', 'f05d9e40-2045-423c-8abf-73f46afdc78d', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Kuala Selangor');

--Pejabat Penasihat Undang-Undang Pulu Pinang|	Pentadbir Tanah Daerah Seberang Perai Selatan
-- Pulau Pinang
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(99201, 1610432, 332, 'b9a877cf-07f4-46b1-bd86-00fe3a0c40d6', '6015e6a5-9c18-4bba-9745-bc1a496f7967', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Seberang Perai Selatan');

--Pejabat Penasihat Undang-Undang Negeri Terengganu|	Pentadbir Tanah Kuala Terengganu
-- Terengganu
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(99203, 123, 334, '97f37703-35aa-4bee-9d35-0c29692dffae', 'c5c40654-a22f-4204-adf8-b65b954290de', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Kuala Terengganu');


------------------- MULA DARI SINI ----------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Wilayah Labuan 17
            </PartyAgency>
            	</PartyAgencyIDNo>4447f6a5-5411-46d1-83e6-1471ddc3250b</PartyAgencyIDNo>
            	<PartyAgencyName>Pentadbir Tanah Wilayah Persekutuan Labuan</PartyAgencyName>
            	<Status>A</Status>
            </PartyAgency>


-- Selangor 18	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>f07b1ba4-8064-41b8-bba4-14f1ddd68786</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Sabak Bernam</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992018, 260, 331, 'f07b1ba4-8064-41b8-bba4-14f1ddd68786', 'f05d9e40-2045-423c-8abf-73f46afdc78d', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Sabak Bernam');


-- Kelantan 19
            <PartyAgency>
               <PartyAgencyIDNo>8e2c8477-846f-4355-afe2-19ff7a0aed3c</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Jajahan Bachok</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Perak    20        
            <PartyAgency>
               <PartyAgencyIDNo>405d9741-dce5-46c8-bf12-1a54afc9b2a4</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Perak Tengah</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Negeri Sembilan    21        (DAH MASUK)
            <PartyAgency>
               <PartyAgencyIDNo>888cf963-1226-40f1-92c0-1ae94bf60d5f</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Tampin</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(99205, 267, 330, '888cf963-1226-40f1-92c0-1ae94bf60d5f', 'b46e0723-9bf3-46c3-95f6-625e7db92981', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Tampin');

-- Pahang 22
-- Baru betulkan
            <PartyAgency>
               <PartyAgencyIDNo>bb3224ee-24a9-4908-a6ed-1c516f1e63f1</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Bentong</PartyAgencyName>
               <Status>?</Status>
            </PartyAgency>
            
-- Johor 23
-- Baru betulkan
           	<PartyAgency>
				<PartyAgencyIDNo>a55deacd-304b-4d0d-acd8-1f5f9a4b2343</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Muar</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Melaka	24
            <PartyAgency>
               <PartyAgencyIDNo>5b097ca4-aed5-4b16-ba05-204b44867335</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Alor Gajah</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Pahang	25
            <PartyAgency>
               <PartyAgencyIDNo>ddde1393-0d10-4fd0-91ed-231ceb1ae53c</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Maran</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Pahang	26
            <PartyAgency>
               <PartyAgencyIDNo>ffa74729-7806-400d-b48e-26cbf13d1d61</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Bera</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Kedah	27	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>9e738f6b-9273-4034-a7ef-279b02d8db99</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kuala Muda</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992027, 281, 327, '9e738f6b-9273-4034-a7ef-279b02d8db99', 'aaea5021-9985-45ed-817e-44d7c20d0d67', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Kuala Muda');

-- Kelantan	28
            <PartyAgency>
               <PartyAgencyIDNo>2d5a5d66-ba5a-4cf7-b895-27af1e41faa5</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Jajahan Pasir Puteh</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Kelantan	29
            <PartyAgency>
               <PartyAgencyIDNo>446aed95-405c-467b-a534-304210060429</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Jajahan Jeli</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Kedah	30	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>bbdfbf05-dbf3-4ce1-97fe-34e2d311f89f</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Pendang</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
            --Ambil ni
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992030, 239, 327, 'bbdfbf05-dbf3-4ce1-97fe-34e2d311f89f', 'aaea5021-9985-45ed-817e-44d7c20d0d67', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Pendang');

            --Contoh
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992017, 239, 327, 'bbdfbf05-dbf3-4ce1-97fe-34e2d311f89f', 'aaea5021-9985-45ed-817e-44d7c20d0d67', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Pendang');

-- Selangor	31 (DAH MASUK)
            <PartyAgency>
               <PartyAgencyIDNo>3c25fc26-e9b9-4d8d-9979-36936ffa9cc4</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kuala Selangor</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(99204, 247, 331, '3c25fc26-e9b9-4d8d-9979-36936ffa9cc4', 'f05d9e40-2045-423c-8abf-73f46afdc78d', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Kuala Selangor');

-- Perak	32
            <PartyAgency>
               <PartyAgencyIDNo>2927f280-9022-44be-bca8-36e47957902d</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Hulu Perak</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Perak	33
            <PartyAgency>
               <PartyAgencyIDNo>f9bf0324-10c8-4d41-ba75-37128f72d4b7</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Muallim</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Kelantan	34
            <PartyAgency>
               <PartyAgencyIDNo>a6aeba93-3d6a-44e8-9e77-3932ae1a70fe</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Jajahan Gua Musang</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Perak	35
            <PartyAgency>
               <PartyAgencyIDNo>e0d6961f-a5a1-4b04-9db3-39d1f531bd5b</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Manjung</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Pahang	36
            <PartyAgency>
               <PartyAgencyIDNo>83c8d843-b2f3-4e23-8d74-3aac4cf3111c</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Cameron Highlands</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Johor	37
            <PartyAgency>
               <PartyAgencyIDNo>9d415186-737a-423e-a68a-3c27f3e95c9c</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kluang</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Selangor	38	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>be9d8d45-c91d-4e9b-acf8-3eee601f430a</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Petaling</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992038, 270, 331, 'be9d8d45-c91d-4e9b-acf8-3eee601f430a', 'f05d9e40-2045-423c-8abf-73f46afdc78d', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Petaling');

-- Pahang	39
            <PartyAgency>
               <PartyAgencyIDNo>042efe94-15b4-496d-a7d2-42ee7634169c</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Lipis</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Kedah	40	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>aa6e1dc0-fcea-4e73-8143-43021957678b</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Yan</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992040, 287, 327, 'aa6e1dc0-fcea-4e73-8143-43021957678b', 'aaea5021-9985-45ed-817e-44d7c20d0d67', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Yan');

-- Negeri Sembilan	41	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>24e36136-2236-4954-9f5c-43ea4563eccd</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kuala Pilah</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992041, 241, 330, '24e36136-2236-4954-9f5c-43ea4563eccd', 'b46e0723-9bf3-46c3-95f6-625e7db92981', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Kuala Pilah');

-- Perak	42
            <PartyAgency>
               <PartyAgencyIDNo>514fe7a1-2a3d-4ffb-b55b-4b8b3d033eb7</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Batang Padang</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Terengganu	43	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>fef73686-8077-435e-9ba9-51da0df12950</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Kemaman</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992043, 9919918, 334, 'fef73686-8077-435e-9ba9-51da0df12950', 'c5c40654-a22f-4204-adf8-b65b954290de', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Kemaman');

-- Johor	44
            <PartyAgency>
               <PartyAgencyIDNo>189ede0b-0aa5-4e81-ad0a-558822e1507a</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Pengerang</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Selangor	45	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>52c1603c-00f6-43ef-a4bd-5804ccc7c69d</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Gombak</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992045, 279, 331, '52c1603c-00f6-43ef-a4bd-5804ccc7c69d', 'f05d9e40-2045-423c-8abf-73f46afdc78d', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Gombak');

-- Negeri Sembilan	46	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>735cd2bc-4e7b-4c77-9f64-5e48ea41a3c4</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Port Dickson</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992046, 267, 330, '735cd2bc-4e7b-4c77-9f64-5e48ea41a3c4', 'b46e0723-9bf3-46c3-95f6-625e7db92981', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Port Dickson');

-- Johor	47
            <PartyAgency>
               <PartyAgencyIDNo>8e3dc4ff-e937-45fd-bf82-5f1c153c2d7c</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Johor Bahru</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Perlis	48
            <PartyAgency>
               <PartyAgencyIDNo>cfda4ba1-0d73-45e1-9132-5f68fcfec9c6</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Negeri Perlis</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Terengganu	49	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>f5ecad62-f136-4943-9a94-6305b4a4da94</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Setiu</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992049, 271, 334, 'f5ecad62-f136-4943-9a94-6305b4a4da94', 'c5c40654-a22f-4204-adf8-b65b954290de', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Setiu');

-- Kelantan	50
            <PartyAgency>
               <PartyAgencyIDNo>0b07fbf0-6279-4d7c-b1ab-643f79c58304</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Jajahan Tumpat</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Kelantan	51
            <PartyAgency>
               <PartyAgencyIDNo>8cc36c3e-b71e-4ac2-a319-67edc33902b5</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Jajahan Kuala Krai</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Pulau Pinang	52	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>656b9f5b-e6fe-4b28-82fb-6a1cbc2d92b3</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Timur Laut</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992052, 1610433, 332, '656b9f5b-e6fe-4b28-82fb-6a1cbc2d92b3', '6015e6a5-9c18-4bba-9745-bc1a496f7967', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Timur Laut');

-- Negeri Sembilan 	53	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>cb5e2f21-8e9f-485d-af02-6af5a922ca4b</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Jempol</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992053, 255, 330, 'cb5e2f21-8e9f-485d-af02-6af5a922ca4b', 'b46e0723-9bf3-46c3-95f6-625e7db92981', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Jempol');

-- Kedah	54	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>014cecaa-cbe3-4174-a949-6cf7dc405008</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kulim</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992054, 20, 327, '014cecaa-cbe3-4174-a949-6cf7dc405008', 'aaea5021-9985-45ed-817e-44d7c20d0d67', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Kulim');

-- Kedah	55	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>d45dc591-4409-4b25-aadb-6d5c3eb47560</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Padang Terap</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992055, 265, 327, 'd45dc591-4409-4b25-aadb-6d5c3eb47560', 'aaea5021-9985-45ed-817e-44d7c20d0d67', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Padang Terap');

-- Perak	56	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>eef82dbc-c7f1-46e0-9aa5-732c8186854f</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kinta</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>

-- Selangor	57	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>d766daff-da5e-4a90-982a-73a4bba5ce28</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kuala Langat</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992057, 103, 331, 'd766daff-da5e-4a90-982a-73a4bba5ce28', 'f05d9e40-2045-423c-8abf-73f46afdc78d', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Kuala Langat');

-- Johor	58
            <PartyAgency>
               <PartyAgencyIDNo>5223935c-154c-4600-a868-79b66ff7d6e5</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Rengit</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Melaka	59
            <PartyAgency>
               <PartyAgencyIDNo>33718ff1-b882-4dac-a04f-7de3cfb85777</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Jasin</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Kuala Lumpur	60
            <PartyAgency>
               <PartyAgencyIDNo>e26da38a-79f7-4a82-90e5-8061e1ef38d0</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Wilayah Persekutuan Kuala Lumpur</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Pulau Pinang	61	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>7df3d1ec-ac85-422f-a11a-84ed2fa04152</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Seberang Perai Utara</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992061, 1610431, 332, '7df3d1ec-ac85-422f-a11a-84ed2fa04152', '6015e6a5-9c18-4bba-9745-bc1a496f7967', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Seberang Perai Utara');

-- Pahang	62
            <PartyAgency>
               <PartyAgencyIDNo>2bf78866-6a43-4909-a5f0-8c468bd2696d</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Jerantut</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Johor	63
            <PartyAgency>
               <PartyAgencyIDNo>96140317-a3a2-4f1e-8ae2-8e790f6114ec</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Segamat</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Negeri Sembilan	64	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>904b2c3a-c15e-40c1-8511-906b45a6bca6</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Rembau</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992064, 267, 330, '904b2c3a-c15e-40c1-8511-906b45a6bca6', 'b46e0723-9bf3-46c3-95f6-625e7db92981', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Rembau');

-- Kedah	65	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>dbc295a2-36e4-46da-847c-918940a0de64</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Bandar Baharu</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992065, 20, 327, 'dbc295a2-36e4-46da-847c-918940a0de64', 'aaea5021-9985-45ed-817e-44d7c20d0d67', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Bandar Baharu');

-- Johor	66
            <PartyAgency>
               <PartyAgencyIDNo>26f430f8-6b4a-4203-83ac-921e93ee4cc2</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kulai</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Johor	67
            <PartyAgency>
               <PartyAgencyIDNo>3c06079e-d9d9-48d2-b920-9ab91bbd1ed7</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Labis</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Kedah	68	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>f59f7a05-a5f4-43d9-94f7-9c167a0c4d11</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Sik</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992068, 292, 327, 'f59f7a05-a5f4-43d9-94f7-9c167a0c4d11', 'aaea5021-9985-45ed-817e-44d7c20d0d67', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Sik');

-- Terengganu	69 (DAH MASUK) [KENAPA LAIN?]
            <PartyAgency>
               <PartyAgencyIDNo>7633303f-8277-46d9-8328-9d99c0a6ddcc</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Hulu Terengganu</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992069, 213, 334, '7633303f-8277-46d9-8328-9d99c0a6ddcc', 'c5c40654-a22f-4204-adf8-b65b954290de', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Hulu Terengganu');

-- Perak	70
            <PartyAgency>
               <PartyAgencyIDNo>aa193b2a-d5dc-4bbe-b540-9e36e4c0f891</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Hilir Perak</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Perak	71
            <PartyAgency>
               <PartyAgencyIDNo>f9b492c3-a851-4962-9f71-a39191d8d21c</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kuala Kangsar</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Terengganu	72	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>e8cf8298-006e-422c-87e5-a55e2e65b41e</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Kuala Nerus</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992072, 9919911, 334, 'e8cf8298-006e-422c-87e5-a55e2e65b41e', 'c5c40654-a22f-4204-adf8-b65b954290de', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Kuala Nerus');

-- Kedah	73	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>b368e436-0500-4471-86cc-ab39d4c343f6</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Pokok Sena</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992073, 296, 327, 'b368e436-0500-4471-86cc-ab39d4c343f6', 'aaea5021-9985-45ed-817e-44d7c20d0d67', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Pokok Sena');

-- Pahang	74
            <PartyAgency>
               <PartyAgencyIDNo>c6c9d78a-d729-4f46-b08e-ae38fa43137b</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Rompin</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Selangor	75	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>db519a70-c8f0-4b35-90de-b3783ebdc1c1</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Hulu Langat</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992075, 185, 331, 'db519a70-c8f0-4b35-90de-b3783ebdc1c1', 'f05d9e40-2045-423c-8abf-73f46afdc78d', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Hulu Langat');

-- Melaka	76
            <PartyAgency>
               <PartyAgencyIDNo>045cc0c1-045c-4420-b351-c3d576ee6a7b</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Melaka Tengah</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Kelantan	77
            <PartyAgency>
               <PartyAgencyIDNo>fa7874b6-2583-48d5-b25c-c5fc928ad712</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Jajahan Machang</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Wilayah Putrajaya	78
            <PartyAgency>
               <PartyAgencyIDNo>e88e5cd9-2302-462c-95c0-c66347e482f5</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Wilayah Persekutuan Putrajaya</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Johor	79
            <PartyAgency>
               <PartyAgencyIDNo>9d5cf228-ce25-43ab-a28d-ca01141985a8</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Batu Pahat</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Pulau Pinang	80	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>523f5b88-21eb-4c12-a4c9-ca7178e2e0e8</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Seberang Perai Tengah</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992080, 710428, 332, '523f5b88-21eb-4c12-a4c9-ca7178e2e0e8', '6015e6a5-9c18-4bba-9745-bc1a496f7967', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Seberang Perai Tengah');

-- Pahang	81
            <PartyAgency>
               <PartyAgencyIDNo>52aab3ef-5896-4707-915b-ca934f618678</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Pekan</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Johor	82
            <PartyAgency>
               <PartyAgencyIDNo>c0ffa173-dc9c-4414-b9bc-cbf44e22ccb8</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Tangkak</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Selangor	83	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>ee36d046-6a1b-4854-9ed3-cc7513107fb6</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Sepang</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992083, 212, 331, 'ee36d046-6a1b-4854-9ed3-cc7513107fb6', 'f05d9e40-2045-423c-8abf-73f46afdc78d', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Sepang');

-- Selangor	84	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>ec244620-ba1b-48a5-bd9b-dab7550c504d</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Klang</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992084, 285, 331, 'ec244620-ba1b-48a5-bd9b-dab7550c504d', 'f05d9e40-2045-423c-8abf-73f46afdc78d', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Klang');

-- Kelantan	85
            <PartyAgency>
               <PartyAgencyIDNo>6f849cae-ff82-4e1e-8188-db48b43b6eab</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Jajahan Kota Bharu</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Pulau Pinang	86	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>5f0da921-7450-4a1f-be96-ea6fea5b837a</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Barat Daya</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992086, 1610434, 332, '5f0da921-7450-4a1f-be96-ea6fea5b837a', '6015e6a5-9c18-4bba-9745-bc1a496f7967', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Barat Daya');

-- Johor	87
            <PartyAgency>
               <PartyAgencyIDNo>d0d932d7-e208-4aec-b237-ec36a6dd5111</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Pontian</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Pahang	88
            <PartyAgency>
               <PartyAgencyIDNo>6c347441-1c16-483d-90f5-ec7c141edf8a</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kuantan</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Negeri Sembilan	89	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>f283594e-6298-4dcf-85f8-ecc0789457d0</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Seremban</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992089, 267, 330, 'f283594e-6298-4dcf-85f8-ecc0789457d0', 'b46e0723-9bf3-46c3-95f6-625e7db92981', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Seremban');

-- Pahang	90
            <PartyAgency>
               <PartyAgencyIDNo>0eaca528-381f-4764-a156-ee6078ced85d</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Temerloh</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Negeri Sembilan	91	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>efc51ff4-e54b-4a66-909c-eedd422d3c8f</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Jelebu</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992091, 223, 330, 'efc51ff4-e54b-4a66-909c-eedd422d3c8f', 'b46e0723-9bf3-46c3-95f6-625e7db92981', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Jelebu');

-- Kelantan	92
            <PartyAgency>
               <PartyAgencyIDNo>38a32f94-8070-4536-bf34-ef15bd0d25cb</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Jajahan Pasir Mas</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Pahang	93
            <PartyAgency>
               <PartyAgencyIDNo>420fc7d1-d13e-4cd4-a077-f02a75766633</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Raub</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Perak	94
			--check balik nama
            <PartyAgency>
               <PartyAgencyIDNo>c73b8322-48d2-4601-92f3-f0d6a6ceab30</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Larut Matang &amp; Selama</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
-- Terengganu	95	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>b0991f1e-58e4-4d69-ae4b-f1f78bc6ba3a</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Besut</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992095, 233, 334, 'b0991f1e-58e4-4d69-ae4b-f1f78bc6ba3a', 'c5c40654-a22f-4204-adf8-b65b954290de', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Besut');

-- Terengganu	96	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>24aff222-b910-4449-84e6-f68c7e3aaa17</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Marang</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992096, 188, 334, '24aff222-b910-4449-84e6-f68c7e3aaa17', 'c5c40654-a22f-4204-adf8-b65b954290de', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Marang');

-- Kedah	97	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>9ea97c49-5897-4dd8-a5df-f6cb7d2a1292</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kubang Pasu</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            
INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992097, 274, 327, '9ea97c49-5897-4dd8-a5df-f6cb7d2a1292', 'aaea5021-9985-45ed-817e-44d7c20d0d67', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Kubang Pasu');

-- Selangor	98 	LEPAS
            <PartyAgency>
               <PartyAgencyIDNo>686e7499-61fc-4680-a78a-fa9476a10bf9</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Hulu Selangor</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>

INSERT INTO TBLINTMTPEJABATMAP
(ID_PEJABATMAP, ID_PEJABATPEN, ID_PEJABATU, KOD_PEJABATPEN, KOD_PEJABATU, STATUS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, JAWATAN)
VALUES(992098, 231, 331, '686e7499-61fc-4680-a78a-fa9476a10bf9', 'f05d9e40-2045-423c-8abf-73f46afdc78d', 'Y', NULL, NULL, NULL, NULL, 'Pentadbir Tanah Daerah Hulu Selangor');

-- Kelantan	99
            <PartyAgency>
               <PartyAgencyIDNo>9bafda78-ccf3-4cfa-9548-fcde391916ed</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Jajahan Tanah Merah</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>
            

-- Johor	100
            <PartyAgency>
               <PartyAgencyIDNo>82763f05-24d3-4b6f-af26-fde895a75437</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kota Tinggi</PartyAgencyName>
               <Status>A</Status>
            </PartyAgency>

-- Perak	101
            <PartyAgency>
               <PartyAgencyIDNo>811161b9-d880-4770-8fa4-fe6cfaa2148f</PartyAgencyIDNo>
               <PartyAgencyName>Pentadbir Tanah Daerah Kerian</PartyAgencyName>
               <Status>A</Status>
			</PartyAgency>
            
			
-- Tanya yang number last lepas 99 letak terus atau padamkan kosong
-- Camne nak search code untuk setiap daerah 
-- Dan JAWATAN punya part