<!-- arief add tukar pegawai 2 -->
<fieldset>
	<div id="view_tukarpegawai2">



		<table style="margin-bottom: 5px" class="classFade box_shadow"
			width="100%" cellspacing="0" cellpadding="0" border="0"
			align="center">
			<tbody>
				<tr class="table_header">
					<td class="underline_td_sub" width="2%"></td>
					<td class="underline_td_sub" width="58%"><strong>MAKLUMAT
							TUKAR PEGAWAI BICARA</strong></td>
					<td class="underline_td_sub" width="38%" valign="top" align="right">
					</td>
					<td class="underline_td_sub" width="2%"></td>
				</tr>
			</tbody>
		</table>


		<table style="margin-top: 5px; margin-bottom: 5px" class="box_shadow"
			width="100%" cellspacing="0" cellpadding="0" border="0"
			align="center">
			<tbody>
				<tr>
					<td width="2%"></td>
					<td width="96%"><br>
						<div>
							<i><font color="red">Perhatian</font> : Pastikan label
								bertanda <font color="red">*</font> diisi.</i>
						</div>
						<div>
							<i><font color="blue">Info</font> : Pegawai boleh membuat
								permohonan tukar pegawai perbicaraan bagi tujuan menyambung
								pengendalian penyelesaian keputusan melibatkan pegawai
								pengendalian yang <b>bersara / bertukar / meninggal dunia</b>.
								Permohonan penukaran akan dipanjangkan kepada KPP negeri atau HQ
								untuk kelulusan.</i>
						</div>
						<div>
							<i><font color="blue">Info</font> : KPP negeri boleh menukar
								pegawai perbicaraan dikalangan negeri sendiri sahaja.</i>
						</div>
						<div>
							<i><font color="blue">Info</font> : KPP dan Pegarah Bahagian
								Pusaka di HQ boleh menukar pegawai perbicaraan meliputi seluruh
								negara.</i>
						</div> <br>
						<fieldset>
							<table class="classFade" width="100%" cellspacing="1"
								cellpadding="2" border="0" align="center">
								<tbody>
									<tr>
										<td width="1%"></td>
										<td width="28%"></td>
										<td width="1%"></td>
										<td width="70%"></td>
									</tr>
									<tr id="tukarpegawai_tarikhPermohonan" style="">
										<td valign="top" align="center"></td>
										<td valign="top" align="left"><span><font color="red">*</font>&nbsp;Tarikh
											Permohonan Tukar Pegawai</span></td>
										<td><div align="right">:</div></td>
										<td><input name="txdTarikhBayaranPerintah" value=""
											size="15" id="txdTarikhPermohonanTukarPegawai" type="text"
											onblur="check_date(this);"
											onkeyup="validateNumber(this,this.value);" /> <img
											src="../img/calendar.gif" alt=""
											onclick="displayDatePicker('txdTarikhPermohonanTukarPegawai',false,'dmy');" /></td>
									</tr>
									<tr id="tukarpegawai_STATUS_TUKARPEGAWAI" style="">
										<td valign="top" align="center"></td>
										<td valign="top" align="left"><span
											id="labeltukarpegawai_STATUS_TUKARPEGAWAI">Status
												Tukar Pegawai</span></td>
										<td valign="top" align="center">:</td>
										<td valign="top" align="left"><div
												id="divtukarpegawai_multipleSTATUS_TUKARPEGAWAI">
												PERMOHONAN BARU<input type="hidden"
													name="tukarpegawai_multipleSTATUS_TUKARPEGAWAI"
													id="tukarpegawai_multipleSTATUS_TUKARPEGAWAI" value="1">
											</div></td>
									</tr>
									<input type="hidden"
										name="tukarpegawai_multipleSTATUS_TUKARPEGAWAI_turutan"
										id="tukarpegawai_multipleSTATUS_TUKARPEGAWAI_turutan"
										value="0.0">
									<input type="hidden"
										name="tukarpegawai_multipleSTATUS_TUKARPEGAWAI_label"
										id="tukarpegawai_multipleSTATUS_TUKARPEGAWAI_label"
										value="Status Tukar Pegawai">
									<tr id="rowtukarpegawai_multipleID_NEGERIPEGAWAIBARU" style="">
										<td valign="top" align="center"><font color="red">*</font></td>
										<td valign="top" align="left"><span
											id="labeltukarpegawai_multipleID_NEGERIPEGAWAIBARU">Negeri
												Pegawai Ganti</span></td>
										<td valign="top" align="center">:</td>
										<td valign="top" align="left"><div
												id="divtukarpegawai_multipleID_NEGERIPEGAWAIBARU">
												<select id="tukarpegawai_multipleID_NEGERIPEGAWAIBARU"
													name="tukarpegawai_multipleID_NEGERIPEGAWAIBARU"
													class="fullwidth_input"
													onchange="doDivAjaxCallFekptg_view_ppk_BicaraInteraktif('divtukarpegawai_multipleID_PEGAWAIBARU','ajaxCallDropDown','refTable=TBLPPKRUJUNIT&amp;PK_refTable=ID_UNITPSK&amp;field_KOD_refTable=&amp;field_VALUE_refTable=NAMA_PEGAWAI&amp;field_FK_refTable=ID_NEGERI&amp;VALUE_FK_refTable=tukarpegawai_multipleID_NEGERIPEGAWAIBARU&amp;column_name=ID_PEGAWAIBARU&amp;mainTable=TBLPPKTUKARPEGAWAI&amp;ID_PERBICARAAN=&amp;skrinName=tukarpegawai_multiple');">
													<option value="">SILA PILIH</option>
													<option value="0">00 - TIADA MAKLUMAT</option>
													<option value="1">01 - JOHOR</option>
													<option value="2">02 - KEDAH</option>
													<option value="3">03 - KELANTAN</option>
													<option value="4">04 - MELAKA</option>
													<option value="5">05 - NEGERI SEMBILAN</option>
													<option value="6">06 - PAHANG</option>
													<option value="7">07 - PULAU PINANG</option>
													<option value="8">08 - PERAK</option>
													<option value="9">09 - PERLIS</option>
													<option value="10">10 - SELANGOR</option>
													<option value="11">11 - TERENGGANU</option>
													<option value="12">12 - SABAH</option>
													<option value="13">13 - SARAWAK</option>
													<option value="14">14 - WILAYAH PERSEKUTUAN KUALA
														LUMPUR</option>
													<option value="15">15 - WILAYAH PERSEKUTUAN LABUAN</option>
													<option value="16">16 - WILAYAH PERSEKUTUAN
														PUTRAJAYA</option>
													<option value="17">98 - LUAR NEGARA/PELBAGAI
														NEGERI</option>
												</select>
											</div></td>
									</tr>
									<input type="hidden"
										name="tukarpegawai_multipleID_NEGERIPEGAWAIBARU_turutan"
										id="tukarpegawai_multipleID_NEGERIPEGAWAIBARU_turutan"
										value="0.0">
									<input type="hidden"
										name="tukarpegawai_multipleID_NEGERIPEGAWAIBARU_label"
										id="tukarpegawai_multipleID_NEGERIPEGAWAIBARU_label"
										value="Negeri Pegawai Ganti">
									<tr id="rowtukarpegawai_multipleID_PEGAWAIBARU" style="">
										<td valign="top" align="center"><font color="red">*</font></td>
										<td valign="top" align="left"><span
											id="labeltukarpegawai_multipleID_PEGAWAIBARU">Pegawai
												Ganti</span></td>
										<td valign="top" align="center">:</td>
										<td valign="top" align="left"><div
												id="divtukarpegawai_multipleID_PEGAWAIBARU">
												<select id="tukarpegawai_multipleID_PEGAWAIBARU"
													name="tukarpegawai_multipleID_PEGAWAIBARU"
													class="fullwidth_input" onchange="">
													<option value="">SILA PILIH</option>
													<option value="99191153">MOHAMAD ROSLI (TIDAK
														AKTIF)</option>
													<option value="1111203">ABD SHUKOR BIN AZIZ
														(AKTIF)</option>
													<option value="116383">ABD SHUKOR BIN AZIZ (AKTIF)</option>
													<option value="99201505">ABD SHUKOR BIN AZIZ
														(AKTIF)</option>
													<option value="99191226">ABDUL AZIZ BIN ALI
														(AKTIF)</option>
													<option value="1112243">ABDUL AZIZ BIN ALI (AKTIF)</option>
													<option value="816403">ABDUL RAZAK BIN MAT HASAN
														(AKTIF)</option>
													<option value="28">AHMAD FARID BIN ESA (AKTIF)</option>
													<option value="99191245">AMIR SAUFI BIN NOZIN
														(AKTIF)</option>
													<option value="9917525">AMIR SAUFI BIN NOZIN
														(AKTIF)</option>
													<option value="99191007">AMIR SAUFI BIN NOZIN
														(AKTIF)</option>
													<option value="99191485">AMSYARI BIN MUSA (AKTIF)</option>
													<option value="717344">ASMARINA BINTI CHE OMAR
														(AKTIF)</option>
													<option value="57">ASMARINA BINTI CHE OMAR (AKTIF)</option>
													<option value="1016422">AZELINA BINTI MESELAN
														(AKTIF)</option>
													<option value="99191365">AZELINA BINTI MESELAN
														(AKTIF)</option>
													<option value="99201646">AZFAR NAUFAL BIN SAIFUL
														NIZZAM (AKTIF)</option>
													<option value="99201507">ABD SHUKOR BIN AZIZ
														(AKTIF)</option>
													<option value="99201508">ABD SHUKOR BIN AZIZ
														(AKTIF)</option>
													<option value="99201506">ABD SHUKOR BIN AZIZ
														(AKTIF)</option>
													<option value="99191146">BAHARUDDIN BIN AZIZ
														(AKTIF)</option>
													<option value="99191227">BAHARUDDIN BIN AZIZ
														(AKTIF)</option>
													<option value="99201565">DAYANG SUHANA BINTI OMAR
														(AKTIF)</option>
													<option value="99201585">DAYANG SUHANA BINTI OMAR
														(AKTIF)</option>
													<option value="64">DZULKIFLI BIN ZAKARIA (AKTIF)</option>
													<option value="413223">DZULKIFLI BIN ZAKARIA
														(AKTIF)</option>
													<option value="217304">DAYANG SUHANA BINTI OMAR
														(AKTIF)</option>
													<option value="9918725">FARAH NAJIHAH BT MAZLAN
														(AKTIF)</option>
													<option value="99201667">FARHAN ILYAS BIN HASHIM
														(AKTIF)</option>
													<option value="1611325">FARHANA LINA BINTI MORSIT
														(AKTIF)</option>
													<option value="99201668">HAIQAL ISMADI BIN ISMAIL
														(AKTIF)</option>
													<option value="161">HAJI AHMAD BIN EMBONG (AKTIF)</option>
													<option value="99201605">HANNAN BIN ABDUL RAZAK
														(AKTIF)</option>
													<option value="9918646">HASIAH BINTI KASAH (AKTIF)</option>
													<option value="1611308">HJ WAN MOHD BAHARUDIN BIN
														WAN NAWANG (AKTIF)</option>
													<option value="1116323">HJ. AMRE BIN MALEK (AKTIF)</option>
													<option value="35">HJ. MOHD FAUZI BIN HJ. MOHD
														YUSOFF (AKTIF)</option>
													<option value="99201666">HUSAINI BIN HUSSIN
														(AKTIF)</option>
													<option value="99191106">HAJI ABDUL LATIF OMAR
														(AKTIF)</option>
													<option value="99191105">HAJI ABDUL LATIF OMAR
														(AKTIF)</option>
													<option value="1612384">HASIAH BINTI KASAH (AKTIF)</option>
													<option value="1611285">KAMAL MIHAD B MD. DAI
														(AKTIF)</option>
													<option value="99191145">KAMARIAH DZAFRUN BT
														KAMARUL BAHARIN (AKTIF)</option>
													<option value="315523">KHAIRIAH BINTI AWANG LAH
														(AKTIF)</option>
													<option value="1610204">MASANIZA BINTI MUSTAPAH
														(AKTIF)</option>
													<option value="9918625">MASRIYANA BINTI REKKEMAN
														(AKTIF)</option>
													<option value="1113283">MASRIYANA BINTI REKKEMAN
														(AKTIF)</option>
													<option value="1016402">MASZURA BINTI MOHAMAD
														(AKTIF)</option>
													<option value="99191126">MASZURA BINTI MOHAMAD
														(AKTIF)</option>
													<option value="99191425">MAWARDI BIN AHMAD (AKTIF)</option>
													<option value="9918645">MOHAMAD QAIYUM BIN ROZALI
														(AKTIF)</option>
													<option value="99191165">MOHAMAD RASHIDI BIN
														MOHAMED NOH (AKTIF)</option>
													<option value="99191148">MOHAMAD RASHIDI BIN
														MOHAMED NOH (AKTIF)</option>
													<option value="99191445">MOHAMAD ZAIM BIN JOHARI
														(AKTIF)</option>
													<option value="47">MOHD AZRI B AZMI (AKTIF)</option>
													<option value="99191006">MOHD AZRI BIN AZMI
														(AKTIF)</option>
													<option value="99191285">MOHD HAIKAL BIN ARIS
														(AKTIF)</option>
													<option value="811283">MOHD HAIKAL BIN ARIS
														(AKTIF)</option>
													<option value="9918785">MOHD SHAFIQ IRSYADUDDIN
														BIN ISMAIL (AKTIF)</option>
													<option value="1611311">MOHD SHAFIQ IRSYADUDDIN
														BIN ISMAIL (AKTIF)</option>
													<option value="61">MOHD ZAKI BIN MD NOH (AKTIF)</option>
													<option value="99201527">MOHD ZAKI BIN MD NOH
														(AKTIF)</option>
													<option value="99201528">MOHD ZAKI BIN MD NOH
														(AKTIF)</option>
													<option value="1612364">MOHD ZAMRI BIN MOHD ESA
														(AKTIF)</option>
													<option value="99191065">MOHIDDIN BIN MD. OMAR
														(AKTIF)</option>
													<option value="99191127">MUHAIRA BINTI MOHAMED
														(AKTIF)</option>
													<option value="69">MUHAMAD ASHRAF BIN FAIZAL
														(AKTIF)</option>
													<option value="99191325">MUHAMAD ASHRAF BIN FAIZAL
														(AKTIF)</option>
													<option value="99191125">MUHAMAD ASHRAF BIN FAIZAL
														(AKTIF)</option>
													<option value="9917567">MUHAMMAD AFFIQ FARHAN BIN
														MOHD ROSLI (AKTIF)</option>
													<option value="99201625">MUHAMMAD AFFIQ FARHAN BIN
														MOHD ROSLI (AKTIF)</option>
													<option value="9918825">MUHAMMAD LUTFI BIN MD
														LATIB (AKTIF)</option>
													<option value="99191229">MUHAMMAD MUHAIZAN BIN
														ABDULLAH (AKTIF)</option>
													<option value="717343">MUHAMMAD MUHAIZAN BIN
														ABDULLAH (AKTIF)</option>
													<option value="9918965">MUHAMMAD SHAHROL AZMI BIN
														AHMAD (AKTIF)</option>
													<option value="99201645">MUHAMMAD SYAHID BIN
														SUHAIMI (AKTIF)</option>
													<option value="9918686">MUHAMMAD UZAIR BIN AZIZAN
														(AKTIF)</option>
													<option value="99201526">MUHD HAMERI B HUSING
														(AKTIF)</option>
													<option value="99191085">MUHD HAMERI B HUSING
														(AKTIF)</option>
													<option value="99191230">MUHD HAMERI BIN HUSING
														(AKTIF)</option>
													<option value="514303">MUHD ROSHASANUL ISYRAF BIN
														ABDUL WAHID (AKTIF)</option>
													<option value="99191326">MUHD ROSHASANUL ISYRAF
														BIN ABDUL WAHID (AKTIF)</option>
													<option value="99191185">MOHD RASHIDI BIN MOHAMED
														NOH (AKTIF)</option>
													<option value="15">NADIAH BINTI HJ ISMAIL (AKTIF)</option>
													<option value="9917465">NAZLI BIN ZAINAL (AKTIF)</option>
													<option value="99191385">NAZLI BIN ZAINAL (AKTIF)</option>
													<option value="99191386">NAZLI BIN ZAINAL (AKTIF)</option>
													<option value="99201545">NOOR RAFIDAH BINTI
														MOHAMED BURHANUDIN (AKTIF)</option>
													<option value="1015382">NOOR RAFIDAH BINTI MOHAMED
														BURHANUDIN (AKTIF)</option>
													<option value="1610184">NOOR RASHIDAH BINTI RAMLI
														(AKTIF)</option>
													<option value="9918689">NOR HANAN BINTI ABU BAKAR
														(AKTIF)</option>
													<option value="9917585">NOR HANAN BT. ABU BAKAR
														(AKTIF)</option>
													<option value="9918688">NOR HANAN BT. ABU BAKAR
														(AKTIF)</option>
													<option value="66">NOR HANAN BT. ABU BAKAR (AKTIF)</option>
													<option value="99191025">NOR HASLINA BINTI HUSSAIN
														(AKTIF)</option>
													<option value="99191026">NOR HASLINA BINTI HUSSAIN
														(AKTIF)</option>
													<option value="1610181">NORAZURA BT SUDIN (AKTIF)</option>
													<option value="99201525">NORAZURA BT SUDIN (AKTIF)</option>
													<option value="1413303">NORAZURA BT SUDIN (AKTIF)</option>
													<option value="54">NORHASYIMAH BINTI MUSTAPA
														(AKTIF)</option>
													<option value="56">NORHIDAYATI BINTI PAINO (AKTIF)</option>
													<option value="99201529">NORHIDAYATI BINTI PAINO
														(AKTIF)</option>
													<option value="9918705">NORLIYANI BINTI MOHD AKIL
														(AKTIF)</option>
													<option value="45">NORLIYANI BINTI MOHD AKIL
														(AKTIF)</option>
													<option value="9918706">NORLIYANI BINTI MOHD AKIL
														(AKTIF)</option>
													<option value="9918765">NORZITA BINTI MAT AUSAF
														(AKTIF)</option>
													<option value="816445">NUR ASMANIZA BINTI MOHAMMAD
														(AKTIF)</option>
													<option value="99191265">NUR IZREEN BINTI MD RASID
														(AKTIF)</option>
													<option value="99191027">NUR IZREEN BINTI MD RASID
														(AKTIF)</option>
													<option value="99191405">NUR IZREEN BINTI MD RASID
														(AKTIF)</option>
													<option value="99201665">NUR NILAM SARI BINTI
														ISMAIL (AKTIF)</option>
													<option value="99191149">NUR RAFINI BINTI AMAN
														(AKTIF)</option>
													<option value="1013322">NUR SYAIDA BINTI MOHAMED
														(AKTIF)</option>
													<option value="1616424">NUR SYAIDA BINTI MOHAMED
														(AKTIF)</option>
													<option value="316583">NORZITA BINTI MAT AUSAF
														(AKTIF)</option>
													<option value="210203">RADZIYUDDIN BIN HJ. SAID
														(AKTIF)</option>
													<option value="99191045">RAHIM BIN MAT KHAZI @
														GHAZI (AKTIF)</option>
													<option value="99191345">ROSLI BIN NORDIN (AKTIF)</option>
													<option value="1615404">ROSLI BIN NORDIN (AKTIF)</option>
													<option value="1612385">ROSLI BIN NORDIN (AKTIF)</option>
													<option value="317665">SAADAH BINTI HJ IBRAHIM
														(AKTIF)</option>
													<option value="14">SAIDATUL NADIAH BINTI MOHD
														SABRI (AKTIF)</option>
													<option value="99191305">SAIDATUL NADIAH BINTI
														MOHD SABRI (AKTIF)</option>
													<option value="1017482">SAIFULDIN BIN AZIZ @ ABDUL
														AZIZ (AKTIF)</option>
													<option value="114323">SAMSURI BIN SELAMAT (AKTIF)</option>
													<option value="99191150">SARIFAH BINTI CHE
														ABDULLAH (AKTIF)</option>
													<option value="99191205">SEH NILAMUDDIN B. HASSAN
														(AKTIF)</option>
													<option value="99191152">SHABUDDIN BIN YAACOB
														(AKTIF)</option>
													<option value="917343">SHAFINIE BINTI ABD AZIZ
														(AKTIF)</option>
													<option value="9918925">SHAFINIE BINTI ABD AZIZ
														(AKTIF)</option>
													<option value="9919985">SHAHIDA BT AB SAMAD
														(AKTIF)</option>
													<option value="1611326">SHARI BIN HAJI OMAR ALI
														(AKTIF)</option>
													<option value="31">SHAZALI BIN HJ. AHMAD (AKTIF)</option>
													<option value="516323">SITI HAJAR BINTI MAZLAN
														(AKTIF)</option>
													<option value="99191151">SITI RAHAYU BINTI
														MISWARDI (AKTIF)</option>
													<option value="613381">SUKRI BIN ISMAIL (AKTIF)</option>
													<option value="816443">SURIADY BIN SUAIT (AKTIF)</option>
													<option value="1611307">SURIADY BIN SUAIT (AKTIF)</option>
													<option value="99191228">SYAZWANI BINTI MAMAT
														(AKTIF)</option>
													<option value="9918665">SYAZWANI BINTI MAMAT
														(AKTIF)</option>
													<option value="99191225">SYAZWANI BINTI MAMAT
														(AKTIF)</option>
													<option value="16">WAHAB BIN AWANG (AKTIF)</option>
													<option value="815383">ZAIDAN BIN IBRAHIM (AKTIF)</option>
													<option value="46">ABD SHUKOR BIN AZIZ (TIDAK
														AKTIF)</option>
													<option value="6">ABDUL AZIZ BIN ALI (TIDAK AKTIF)</option>
													<option value="160">ABDUL AZIZ BIN ALI (TIDAK
														AKTIF)</option>
													<option value="612301">ABDUL AZIZ BIN ALI (TIDAK
														AKTIF)</option>
													<option value="1414383">ABDUL RAHMAN BIN SALLEH
														(TIDAK AKTIF)</option>
													<option value="78">ABDUL RAZAK BIN MAT HASAN
														(TIDAK AKTIF)</option>
													<option value="24">ABDUL RAZAK BIN MAT HASSAN
														(TIDAK AKTIF)</option>
													<option value="167">ABDULLAH BIN MUHAMMAD (TIDAK
														AKTIF)</option>
													<option value="20">ABU BAKAR BIN HJ. IBRAHIM
														(TIDAK AKTIF)</option>
													<option value="711223">AHMAD NASIR BIN ABDULLAH
														(TIDAK AKTIF)</option>
													<option value="112263">AHMAD NASIR BIN ABDULLAH
														(TIDAK AKTIF)</option>
													<option value="83">AMRE BIN MALEK (TIDAK AKTIF)</option>
													<option value="812323">AMRE BIN MALEK (TIDAK
														AKTIF)</option>
													<option value="1415427">AMRE BIN MALEK (TIDAK
														AKTIF)</option>
													<option value="311323">ARSHAD BIN MOHD YASSIN
														(TIDAK AKTIF)</option>
													<option value="911223">ASMARINA BINTI CHE OMAR
														(TIDAK AKTIF)</option>
													<option value="60">AZELINA BINTI MESELAN (TIDAK
														AKTIF)</option>
													<option value="811205">AZELINA BINTI MESELAN
														(TIDAK AKTIF)</option>
													<option value="811263">AZELINA BINTI MESELAN
														(TIDAK AKTIF)</option>
													<option value="59">AZHARUDDIN BIN ABU BAKAR (TIDAK
														AKTIF)</option>
													<option value="611283">AZHARUDDIN BIN ABU BAKAR
														(TIDAK AKTIF)</option>
													<option value="610201">AZHARUDDIN BIN ABU BAKAR
														(TIDAK AKTIF)</option>
													<option value="811203">AZHARUDDIN BIN ABU BAKAR
														(TIDAK AKTIF)</option>
													<option value="314423">ABD SHUKOR BIN AZIZ (TIDAK
														AKTIF)</option>
													<option value="314483">ARISUCMAN BIN MUSA (TIDAK
														AKTIF)</option>
													<option value="113303">BAHARUDDIN BIN AZIZ (TIDAK
														AKTIF)</option>
													<option value="75">BAHARUDDIN BIN AZIZ (TIDAK
														AKTIF)</option>
													<option value="211223">BAHARUDDIN BIN AZIZ (TIDAK
														AKTIF)</option>
													<option value="617441">BAHARUDDIN BIN AZIZ (TIDAK
														AKTIF)</option>
													<option value="29">BAHARUDDIN BIN AZIZ (TIDAK
														AKTIF)</option>
													<option value="712243">BAHARUDDIN BIN AZIZ (TIDAK
														AKTIF)</option>
													<option value="716324">BAHRIN BIN MAHAT (TIDAK
														AKTIF)</option>
													<option value="612321">CHE MOHD ZULFAKAINEE
														AIMULLAH BIN CHE SIDEK (TIDAK AKTIF)</option>
													<option value="311283">CHE MOHD ZULFAKAINEE
														AIMULLAH BIN CHE SIDEK (TIDAK AKTIF)</option>
													<option value="70">CHE MOHD ZULFAKAINEE AIMULLAH
														BIN CHE SIDEK (TIDAK AKTIF)</option>
													<option value="612322">CHE MOHD ZULFAKAINEE
														AIMULLAH BIN CHE SIDEK (TIDAK AKTIF)</option>
													<option value="311263">CHE JOHARI BIN CHE SOH
														(TIDAK AKTIF)</option>
													<option value="713263">DAYANG SUHANA BINTI OMAR
														(TIDAK AKTIF)</option>
													<option value="916323">FARAH NAJIHAH BINTI MAZLAN
														(TIDAK AKTIF)</option>
													<option value="217283">FARAH NAJIHAH BINTI MAZLAN
														(TIDAK AKTIF)</option>
													<option value="4">HAJI AHMAD BIN EMBONG (TIDAK
														AKTIF)</option>
													<option value="1611324">HAJI MOHAMAD BIN HUSIN
														(TIDAK AKTIF)</option>
													<option value="1611284">HAJI SHAZALI BIN AHMAD
														(TIDAK AKTIF)</option>
													<option value="96">HAJJAH SALEHAH BINTI AWANG
														(TIDAK AKTIF)</option>
													<option value="2">HASIAH BINTI KASAH (TIDAK AKTIF)</option>
													<option value="1411204">HASIAH BINTI KASAH (TIDAK
														AKTIF)</option>
													<option value="38">HJ ABDUL HAMID BIN KASSIM
														(TIDAK AKTIF)</option>
													<option value="1611304">HJ ABDULLAH BIN MUHAMAD
														(TIDAK AKTIF)</option>
													<option value="1">HJ ABDULLAH BIN MUHAMAD (TIDAK
														AKTIF)</option>
													<option value="21">HJ. ABDUL WAHAB BIN ABDUL RAWI
														(TIDAK AKTIF)</option>
													<option value="42">HJ. ABDULILAH BIN MOKHTAR
														(TIDAK AKTIF)</option>
													<option value="1610194">HJ. ABDULLAH BIN MUHAMAD
														(TIDAK AKTIF)</option>
													<option value="44">HJ. AMRE BIN HJ. MALEK (TIDAK
														AKTIF)</option>
													<option value="25">HJ. MASRIDDIN BIN HANAN (TIDAK
														AKTIF)</option>
													<option value="10">HJ. MOHD ZIN BIN DOLLAH (TIDAK
														AKTIF)</option>
													<option value="30">HJ. WAN ABD. HALIM BIN WAN
														HARUN (TIDAK AKTIF)</option>
													<option value="156">HJ. WAN ABDUL HALIM BIN WAN
														HARUN (TIDAK AKTIF)</option>
													<option value="311223">HJH KARIMAH BT OTHMAN
														(TIDAK AKTIF)</option>
													<option value="312343">HJH KARIMAH BT OTHMAN
														(TIDAK AKTIF)</option>
													<option value="312344">HJH KARIMAH BT OTHMAN
														(TIDAK AKTIF)</option>
													<option value="34">HJH. JAMALIAH BINTI MOHD NOOR
														(TIDAK AKTIF)</option>
													<option value="11">HJH. KARIMAH BINTI OTHMAN
														(TIDAK AKTIF)</option>
													<option value="32">HJH. SAADAH BINTI HJ. IBARAHIM
														(TIDAK AKTIF)</option>
													<option value="12">HJH. SALEHAH BINTI AWANG (TIDAK
														AKTIF)</option>
													<option value="1414363">ISMAIL BIN ABDULLAH (TIDAK
														AKTIF)</option>
													<option value="37">ISMAIL BIN ABDULLAH @ DOLLAH
														(TIDAK AKTIF)</option>
													<option value="97">ISMAIL BIN DOLLAH @ ABDULLAH
														(TIDAK AKTIF)</option>
													<option value="1611309">ISMAIL BIN DOLLAH @
														ABDULLAH (TIDAK AKTIF)</option>
													<option value="63">JUSOH @ MOHD. YUSOFF BIN DAUD
														(TIDAK AKTIF)</option>
													<option value="48">KAMAL MIHAD B MD. DAI (TIDAK
														AKTIF)</option>
													<option value="41">KAMARIAH DZAFRUN BINTI KAMARUL
														BAHARIN (TIDAK AKTIF)</option>
													<option value="1411223">KAMARIAH DZAFRUN BINTI
														KAMARUL BAHARIN (TIDAK AKTIF)</option>
													<option value="1011223">KAMARIAH DZAFRUN BT
														KAMARUL BAHARIN (TIDAK AKTIF)</option>
													<option value="714305">KAMARUL HAIZAL BIN KODERAT
														(TIDAK AKTIF)</option>
													<option value="116">KARIMAH BINTI OTHMAN (TIDAK
														AKTIF)</option>
													<option value="314444">KHAIRIAH BINTI AWANG LAH
														(TIDAK AKTIF)</option>
													<option value="313403">KHAIRIAH BINTI AWANG LAH
														(TIDAK AKTIF)</option>
													<option value="316564">KARIMAH BINTI OTHMAN (TIDAK
														AKTIF)</option>
													<option value="23">MARIYAM BINTI LAMRI (TIDAK
														AKTIF)</option>
													<option value="76">MARIYAM BINTI LAMRI (TIDAK
														AKTIF)</option>
													<option value="112283">MARIYAM BINTI LAMRI (TIDAK
														AKTIF)</option>
													<option value="73">MASANIZA BINTI MUSTAPAH (TIDAK
														AKTIF)</option>
													<option value="1610264">MASANIZA BINTI MUSTAPAH
														(TIDAK AKTIF)</option>
													<option value="616421">MASRIYANA BT REKKEMAN
														(TIDAK AKTIF)</option>
													<option value="111203">MASZURA BINTI MOHAMAD
														(TIDAK AKTIF)</option>
													<option value="68">MASZURA BINTI MOHAMAD (TIDAK
														AKTIF)</option>
													<option value="612341">MASZURA BINTI MOHAMAD
														(TIDAK AKTIF)</option>
													<option value="316603">MOHAMAD RASHIDI BIN MOHAMED
														NOH (TIDAK AKTIF)</option>
													<option value="816446">MOHAMAD ZAIM BIN JOHARI
														(TIDAK AKTIF)</option>
													<option value="62">MOHD AZLI BIN MAT JUSOH (TIDAK
														AKTIF)</option>
													<option value="611221">MOHD AZRI BIN AZMI (TIDAK
														AKTIF)</option>
													<option value="36">MOHD FITRI BIN ABD RAHMAN
														(TIDAK AKTIF)</option>
													<option value="158">MOHD FITRI BIN ABDUL RAHMAN
														(TIDAK AKTIF)</option>
													<option value="1610201">MOHD HAIKAL BIN ARIS
														(TIDAK AKTIF)</option>
													<option value="816423">MOHD HAIKAL BIN ARIS (TIDAK
														AKTIF)</option>
													<option value="714307">MOHD KHAIRUDDIN BIN NUSI
														(TIDAK AKTIF)</option>
													<option value="1412284">MOHD SABRI BIN AHMAD
														(TIDAK AKTIF)</option>
													<option value="1412283">MOHD SABRI BIN AHMAD
														(TIDAK AKTIF)</option>
													<option value="79">MOHD YUSOF BIN MD TAHAR (TIDAK
														AKTIF)</option>
													<option value="716323">MOHD ZAKI BIN CHE NOK
														(TIDAK AKTIF)</option>
													<option value="210204">MOHD ZIN BIN DOLLAH (TIDAK
														AKTIF)</option>
													<option value="3">MOHD. YUSOF BIN MOHD TAHAR
														(TIDAK AKTIF)</option>
													<option value="164">MOHIDDIN BIN MD OMAR (TIDAK
														AKTIF)</option>
													<option value="9918946">MOHIDDIN BIN MD OMAR
														(TIDAK AKTIF)</option>
													<option value="512263">MOHIDDIN BIN MD OMAR (TIDAK
														AKTIF)</option>
													<option value="311244">MOHIDDIN BIN MD OMAR (TIDAK
														AKTIF)</option>
													<option value="27">MOHIDIN BIN OMAR (TIDAK AKTIF)</option>
													<option value="1415423">MUHAIRA BINTI MOHAMED
														(TIDAK AKTIF)</option>
													<option value="1015381">MUHAIRA BINTI MOHAMED
														(TIDAK AKTIF)</option>
													<option value="513283">MUHAIRA BINTI MOHAMED
														(TIDAK AKTIF)</option>
													<option value="513285">MUHAIRA BINTI MOHAMED
														(TIDAK AKTIF)</option>
													<option value="513284">MUHAIRA BINTI MOHAMED
														(TIDAK AKTIF)</option>
													<option value="117">MUHAMAD ASHRAF BIN FAIZAL
														(TIDAK AKTIF)</option>
													<option value="511203">MUHAMAD ASHRAF BIN FAIZAL
														(TIDAK AKTIF)</option>
													<option value="315543">MUHAMMAD MUHAIZAN BIN
														ABDULLAH (TIDAK AKTIF)</option>
													<option value="165">MUHAMMAD MUHAIZAN BIN ABDULLAH
														(TIDAK AKTIF)</option>
													<option value="157">MUHAMMAD MUHAIZAN BIN ABDULLAH
														(TIDAK AKTIF)</option>
													<option value="52">MUHAMMAD MUHAIZAN BIN ABDULLAH
														(TIDAK AKTIF)</option>
													<option value="1016442">MUHAMMAD MUHAIZAN BIN
														ABDULLAH (TIDAK AKTIF)</option>
													<option value="611243">MUHAMMAD MUHAIZAN BIN
														ABDULLAH (TIDAK AKTIF)</option>
													<option value="314443">MUHD HAMERI B HUSING (TIDAK
														AKTIF)</option>
													<option value="316604">MUHD HAMERI B HUSING (TIDAK
														AKTIF)</option>
													<option value="317644">MUHD HAMERI BIN HUSING
														(TIDAK AKTIF)</option>
													<option value="614401">MUHD HAMERI BIN HUSING
														(TIDAK AKTIF)</option>
													<option value="613361">MUHD HAMERI BIN HUSING
														(TIDAK AKTIF)</option>
													<option value="317643">MUHD HAMERI BIN HUSING
														(TIDAK AKTIF)</option>
													<option value="81">MUHD ROSHASANUL ISYRAF BIN
														ABDUL WAHID (TIDAK AKTIF)</option>
													<option value="67">MUHD. ROSHASANUL ISYRAF BIN
														ABD.WAHID (TIDAK AKTIF)</option>
													<option value="40">MUSTAPA BIN GHAZALI (TIDAK
														AKTIF)</option>
													<option value="39">NADIAH BINTI HAJI ISMAIL (TIDAK
														AKTIF)</option>
													<option value="8">NADIAH BINTI HJ ISMAIL (TIDAK
														AKTIF)</option>
													<option value="53">NAZLI BIN ZAINAL (TIDAK AKTIF)</option>
													<option value="159">NIK SANIAH BINTI NIK MOHAMED
														(TIDAK AKTIF)</option>
													<option value="7">NIK SANIAH BINTI NIK MOHAMED
														(TIDAK AKTIF)</option>
													<option value="911203">NIK SANIAH BINTI NIK
														MOHAMED (TIDAK AKTIF)</option>
													<option value="813343">NOOR RAFIDAH BINTI MOHAMED
														BURHANUDIN (TIDAK AKTIF)</option>
													<option value="1012261">NOOR RASHIDAH BINTI RAMLI
														(TIDAK AKTIF)</option>
													<option value="50">NOOR RASHIDAH BINTI RAMLI
														(TIDAK AKTIF)</option>
													<option value="1610202">NOR HANAN BT. ABU BAKAR
														(TIDAK AKTIF)</option>
													<option value="216263">NOR HANAN BT. ABU BAKAR
														(TIDAK AKTIF)</option>
													<option value="310204">NOR HASLINA BINTI HUSSAIN
														(TIDAK AKTIF)</option>
													<option value="99191465">NOR HASLINA BINTI HUSSAIN
														(TIDAK AKTIF)</option>
													<option value="1017462">NOR HASLINA BINTI HUSSAIN
														(TIDAK AKTIF)</option>
													<option value="713283">NOR HASLINA BINTI HUSSAIN
														(TIDAK AKTIF)</option>
													<option value="163">NOR HASLINA BINTI HUSSIN
														(TIDAK AKTIF)</option>
													<option value="1611305">NOR HASLINA BINTI HUSSIN
														(TIDAK AKTIF)</option>
													<option value="72">NORAIDAH BINTI YAAKOP (TIDAK
														AKTIF)</option>
													<option value="714304">NORAINI BINTI HARON (TIDAK
														AKTIF)</option>
													<option value="1414344">NORAINI BINTI ISMAIL
														(TIDAK AKTIF)</option>
													<option value="1015361">NORAZURA BT SUDIN (TIDAK
														AKTIF)</option>
													<option value="511223">NORAZURA BT SUDIN (TIDAK
														AKTIF)</option>
													<option value="1013321">NORAZURA BT SUDIN (TIDAK
														AKTIF)</option>
													<option value="211">NORHASYIMAH BINTI MUSTAPA
														(TIDAK AKTIF)</option>
													<option value="1013301">NORHASYIMAH BINTI MUSTAPA
														(TIDAK AKTIF)</option>
													<option value="71">NORLIYANI BINTI MOHD. AKIL
														(TIDAK AKTIF)</option>
													<option value="811223">NORLIYANI BINTI MOHD. AKIL
														(TIDAK AKTIF)</option>
													<option value="80">NORZITA BINTI MAT AUSAF (TIDAK
														AKTIF)</option>
													<option value="9917485">NORZITA BINTI MAT AUSAF
														(TIDAK AKTIF)</option>
													<option value="9918726">NUR IZREEN BINTI MD RASID
														(TIDAK AKTIF)</option>
													<option value="714303">NUR KHAIRUNNISA BINTI AHMAD
														KAMAL (TIDAK AKTIF)</option>
													<option value="1013341">NUR SYAIDA BINTI MOHAMED
														(TIDAK AKTIF)</option>
													<option value="1411263">NUR SYAIDA BINTI MOHAMED
														(TIDAK AKTIF)</option>
													<option value="315503">NUR SYAIDA BINTI MOHAMED
														(TIDAK AKTIF)</option>
													<option value="714306">NURUL ASMAA BINTI ISMAIL
														(TIDAK AKTIF)</option>
													<option value="9918868">NUR RAFINI AMAN (TIDAK
														AKTIF)</option>
													<option value="9918867">NUR RAFINI AMAN (TIDAK
														AKTIF)</option>
													<option value="9918865">NUR RAFINI AMAN (TIDAK
														AKTIF)</option>
													<option value="33">RADZIYUDDIN BIN HJ SAID (TIDAK
														AKTIF)</option>
													<option value="55">RAHIM BIN MAT KHAZI @ GHAZI
														(TIDAK AKTIF)</option>
													<option value="814363">RAHIM BIN MAT KHAZI @ GHAZI
														(TIDAK AKTIF)</option>
													<option value="313383">RAHIM BIN MAT KHAZI @ GHAZI
														(TIDAK AKTIF)</option>
													<option value="1116343">RAHIM BIN MAT KHAZI @
														GHAZI (TIDAK AKTIF)</option>
													<option value="9917486">ROSLI BIN NORDIN (TIDAK
														AKTIF)</option>
													<option value="1011221">SAADAH BINTI HAJI IBRAHIM
														(TIDAK AKTIF)</option>
													<option value="317666">SAADAH BINTI HJ IBRAHIM
														(TIDAK AKTIF)</option>
													<option value="317664">SAADAH BINTI HJ IBRAHIM
														(TIDAK AKTIF)</option>
													<option value="1411243">SAADAH BINTI HJ IBRAHIM
														(TIDAK AKTIF)</option>
													<option value="317663">SAADAH BINTI IBRAHIM (TIDAK
														AKTIF)</option>
													<option value="98">SAADAH BT HJ IBRAHIM (TIDAK
														AKTIF)</option>
													<option value="1016403">SAIDATUL NADIAH BINTI MOHD
														SABRI (TIDAK AKTIF)</option>
													<option value="9917545">SAIDATUL NADIAH BINTI MOHD
														SABRI (TIDAK AKTIF)</option>
													<option value="811243">SAIDATUL NADIAH BINTI MOHD.
														SABRI (TIDAK AKTIF)</option>
													<option value="58">SAIDATUL NADIAH BINTI
														MOHD.SABRI (TIDAK AKTIF)</option>
													<option value="1611306">SAIFULDIN BIN AZIZ @ ABDUL
														AZIZ (TIDAK AKTIF)</option>
													<option value="169">SAIFULDIN BIN AZIZ @ ABDUL
														AZIZ (TIDAK AKTIF)</option>
													<option value="1414323">SAIFULDIN BIN AZIZ @ ABDUL
														AZIZ (TIDAK AKTIF)</option>
													<option value="22">SAIFULDIN BIN AZIZ @ ABDUL AZIZ
														(TIDAK AKTIF)</option>
													<option value="913263">SAIFULDIN BIN AZIZ@ABDUL
														AZIZ (TIDAK AKTIF)</option>
													<option value="114343">SALEHAH BT AWANG (TIDAK
														AKTIF)</option>
													<option value="317623">SALLEH BIN DAUD (TIDAK
														AKTIF)</option>
													<option value="26">SAMSURI BIN SELAMAT (TIDAK
														AKTIF)</option>
													<option value="612302">SAMSURI BIN SELAMAT (TIDAK
														AKTIF)</option>
													<option value="9918806">SARIFAH BINTI CHE ABDULLAH
														(TIDAK AKTIF)</option>
													<option value="99191147">SHABUDDIN BIN YAACOB
														(TIDAK AKTIF)</option>
													<option value="1610183">SHABUDDIN BIN YAACOB
														(TIDAK AKTIF)</option>
													<option value="1117383">SHABUDDIN BIN YAACOB
														(TIDAK AKTIF)</option>
													<option value="1610245">SHABUDDIN BIN YAACOB
														(TIDAK AKTIF)</option>
													<option value="1610224">SHABUDDIN BIN YAACOB
														(TIDAK AKTIF)</option>
													<option value="19">SHABUDIN BIN YAACOB (TIDAK
														AKTIF)</option>
													<option value="313384">SHAFINIE BINTI ABD AZIZ
														(TIDAK AKTIF)</option>
													<option value="312363">SHAFINIE BINTI ABD AZIZ
														(TIDAK AKTIF)</option>
													<option value="162">SHAFINIE BINTI ABD AZIZ (TIDAK
														AKTIF)</option>
													<option value="9917505">SHAFINIE BINTI ABD AZIZ
														(TIDAK AKTIF)</option>
													<option value="1116363">SHAHIDA BINTI AB SAMAD
														(TIDAK AKTIF)</option>
													<option value="9917605">SHAHIDA BINTI AB SAMAD
														(TIDAK AKTIF)</option>
													<option value="82">SHAZALI BIN AHMAD (TIDAK AKTIF)</option>
													<option value="916303">SHAZALI BIN AHMAD (TIDAK
														AKTIF)</option>
													<option value="112243">SITI MARIAM BINTI CHE MOHD
														YUSOFF (TIDAK AKTIF)</option>
													<option value="5">SITI MARIAM BINTI CHE MOHD
														YUSOFF (TIDAK AKTIF)</option>
													<option value="611281">SITI MARIAM BINTI CHE MOHD
														YUSOFF (TIDAK AKTIF)</option>
													<option value="913283">SITI RAHAYU BINTI MISWARDI
														(TIDAK AKTIF)</option>
													<option value="168">SITI RAHAYU BINTI MISWARDI
														(TIDAK AKTIF)</option>
													<option value="115363">SITI RAHAYU BINTI MISWARDI
														(TIDAK AKTIF)</option>
													<option value="17">SUKRI BIN ARIFFIN (TIDAK AKTIF)</option>
													<option value="43">SUKRI BIN ISMAIL (TIDAK AKTIF)</option>
													<option value="1610203">SULAIMAN @ SALIMUAN BIN
														KADIMI (TIDAK AKTIF)</option>
													<option value="18">SULAIMAN @ SALIMUAN BIN KADIMI
														(TIDAK AKTIF)</option>
													<option value="1414384">SULAIMAN @ SULIMIN BIN
														KADIMIN (TIDAK AKTIF)</option>
													<option value="116403">SULAIMAN BIN HJ ABD AZIZ
														(TIDAK AKTIF)</option>
													<option value="166">SURIADY BIN SUAIT (TIDAK
														AKTIF)</option>
													<option value="65">SYAZWANI BINTI MAMAT (TIDAK
														AKTIF)</option>
													<option value="611242">SYAZWANI BINTI MAMAT (TIDAK
														AKTIF)</option>
													<option value="611282">SYAZWANI BINTI MAMAT (TIDAK
														AKTIF)</option>
													<option value="316563">SHAFINIE BINTI ABD AZIZ
														(TIDAK AKTIF)</option>
													<option value="1414403">TDK (TIDAK AKTIF)</option>
													<option value="74">TIADA (TIDAK AKTIF)</option>
													<option value="1611310">TT (TIDAK AKTIF)</option>
													<option value="511243">TT (TIDAK AKTIF)</option>
													<option value="611261">TT (TIDAK AKTIF)</option>
													<option value="411203">TT (TIDAK AKTIF)</option>
													<option value="111223">TT (TIDAK AKTIF)</option>
													<option value="211243">TT (TIDAK AKTIF)</option>
													<option value="311303">TT (TIDAK AKTIF)</option>
													<option value="1011241">TT (TIDAK AKTIF)</option>
													<option value="711203">TT (TIDAK AKTIF)</option>
													<option value="1611344">TT (TIDAK AKTIF)</option>
													<option value="911243">TT (TIDAK AKTIF)</option>
													<option value="811303">TT (TIDAK AKTIF)</option>
													<option value="51">TUAN HAJI ABDUL WAHAB BIN ABD
														RANI (TIDAK AKTIF)</option>
													<option value="13">TUAN HJ IBRAHIM BIN SALLEH
														(TIDAK AKTIF)</option>
													<option value="314463">TUAN HAJI MOHD ZIN BIN
														DOLLAH (TIDAK AKTIF)</option>
													<option value="9">ZAIDAN BIN IBRAHIM (TIDAK AKTIF)</option>
													<option value="49">``` (TIDAK AKTIF)</option>
												</select>
											</div></td>
									</tr>
									<input type="hidden"
										name="tukarpegawai_multipleID_PEGAWAIBARU_turutan"
										id="tukarpegawai_multipleID_PEGAWAIBARU_turutan" value="0.0">
									<input type="hidden"
										name="tukarpegawai_multipleID_PEGAWAIBARU_label"
										id="tukarpegawai_multipleID_PEGAWAIBARU_label"
										value="Pegawai Ganti">
									<input type="hidden"
										name="tukarpegawai_multipleFLAG_DAFTAR_TERUS"
										id="tukarpegawai_multipleFLAG_DAFTAR_TERUS" value="">
									<input type="hidden"
										name="tukarpegawai_multipleFLAG_DAFTAR_TERUS_turutan"
										id="tukarpegawai_multipleFLAG_DAFTAR_TERUS_turutan"
										value="0.0">
									<input type="hidden"
										name="tukarpegawai_multipleFLAG_DAFTAR_TERUS_label"
										id="tukarpegawai_multipleFLAG_DAFTAR_TERUS_label" value="">
									<tr id="rowtukarpegawai_multipleCATATAN_PEMOHON">
										<td valign="top" align="center"></td>
										<td valign="top" align="left"><span
											id="labeltukarpegawai_multipleCATATAN_PEMOHON">Catatan
												Pemohon</span></td>
										<td valign="top" align="center">:</td>
										<td valign="top" align="left"><textarea rows="4"
												style="text-transform: uppercase" maxlength="4000"
												spellcheck="false"
												name="tukarpegawai_multipleCATATAN_PEMOHON"
												id="tukarpegawai_multipleCATATAN_PEMOHON"
												class="fullwidth_input"></textarea>
											<div id="locationreSetuptukarpegawai_multipleCATATAN_PEMOHON"></div>
											<div id="dummyDivResetuptukarpegawai_multipleCATATAN_PEMOHON"
												style="display: none;"></div>
											<div id="timer_tukarpegawai_multipleCATATAN_PEMOHON"
												align="right"></div></td>
									</tr>
									<input type="hidden"
										name="tukarpegawai_multipleCATATAN_PEMOHON_turutan"
										id="tukarpegawai_multipleCATATAN_PEMOHON_turutan" value="0.0">
									<input type="hidden"
										name="tukarpegawai_multipleCATATAN_PEMOHON_label"
										id="tukarpegawai_multipleCATATAN_PEMOHON_label"
										value="Catatan Pemohon">
									<tr>
										<td></td>
										<td valign="top" align="left">Jumlah Perbicaraan Dipilih</td>
										<td valign="top" align="center">:</td>
										<td valign="top" align="left"><div
												id="divtukarpegawai_multipleDisplayJumlahPerbicaraan">0</div>
											<input type="hidden"
											id="tukarpegawai_multipleJumlahPerbicaraan"
											name="tukarpegawai_multipleJumlahPerbicaraan" value="0"></td>
									</tr>
								</tbody>
							</table>
						</fieldset>
						<table style="margin-top: 5px" id="buttontukarpegawai_multiple"
							width="100%" cellspacing="1" cellpadding="1" border="0"
							align="center">
							<tbody>
								<tr>
									<td width="100%" valign="top" align="center"><input
										type="button" id="cmdSimpantukarpegawai_multiple"
										name="cmdSimpantukarpegawai_multiple" value="Hantar"
										onclick="if(valSimpanTukarPegawaiMultiple('tukarpegawai_multiple') == true){doDivAjaxCallFekptg_view_ppk_BicaraInteraktif('view_tukarpegawai_multiple','saveTukarPegawaiMultiple','NAMA_TABLE=TBLPPKTUKARPEGAWAI&amp;FIELD_PK=ID_TUKARPEGAWAI&amp;mode=edit&amp;skrinName=tukarpegawai_multiple&amp;div=view_tukarpegawai_multiple&amp;SELECTED_NAMA_NEGERI='+getValueFromDrop('tukarpegawai_multipleID_NEGERIPEGAWAIBARU')+'&amp;SELECTED_NAMA_PEGAWAI='+getValueFromDrop('tukarpegawai_multipleID_PEGAWAIBARU')+'&amp;scrolPosition='+getPageLocation());}">
										<input type="button" id="cmdKemaskinitukarpegawai_multiple"
										name="cmdKemaskinitukarpegawai_multiple" value="Batal"
										onclick="doDivAjaxCallFekptg_view_ppk_BicaraInteraktif('view_tukarpegawai_multiple','resetTukarPegawaiMultiple','NAMA_TABLE=TBLPPKTUKARPEGAWAI&amp;FIELD_PK=ID_TUKARPEGAWAI&amp;mode=edit&amp;skrinName=tukarpegawai_multiple&amp;div=view_tukarpegawai_multiple&amp;scrolPosition='+getPageLocation());">
										<input type="button" id="cmdTutuptukarpegawai_multiple"
										name="cmdTutuptukarpegawai_multiple" value="Tutup"
										onclick="tutupSkrinPegawaiMultiple('tukarpegawai_multiple');"></td>
								</tr>
							</tbody>
						</table></td>
					<td width="2%"></td>
				</tr>
			</tbody>
		</table>





	</div>
</fieldset>