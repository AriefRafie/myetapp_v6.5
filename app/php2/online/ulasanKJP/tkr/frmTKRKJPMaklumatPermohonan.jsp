<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>

#set($saizTxtTujuan="500")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPermohonanSewa" type="hidden" id="idPermohonanSewa" value="$idPermohonanSewa"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idHakmilikAgensi" type="hidden" id="idHakmilikAgensi" value="$idHakmilikAgensi"/>
  <input name="idHakmilikAgensiPopup" type="hidden" id="idHakmilikAgensiPopup"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idPengarah" type="hidden" id="idPengarah" value="$idPengarah"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="kategori" type="hidden" id="kategori" value="$!pemohon.get("kategoriPemohon")/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$!idDokumen"/>
  <input name="submit2" type="hidden" id="submit2" />
  <input name="userJawatan" type="hidden" id="userJawatan" />

</p>
<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/online/ulasanKJP/tkr/frmTKRKJPHeader.jsp") </td>
  </tr>

  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if ($idFail != '')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH YANG DIPOHON</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TUKARGUNA</li>
          <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">SENARAI SEMAK</li>
          <!-- <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">LAMPIRAN</li> -->
          <li onClick="doChangeTabUpper(4);" class="TabbedPanelsTab" tabindex="0">PENGESAHAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">

        <!-- START MAKLUMAT TANAH -->
          <div class="TabbedPanelsContent">

#if($BeanMaklumatTanah == "")

<table width="100%" border="0" cellspacing="2" cellpadding="2">
			<tr>
				<td width="50%" valign="top">

				<table width="100%"  cellpadding="2" cellspacing="2" border="0">
					<tr>
			          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			          <td width="28%">Negeri</td>
			          <td width="1%">:</td>
			          <td width="70%">$selectNegeri</td>
			        </tr>
				  <tr>
							<td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
							<td>No. Lot</td>
							<td>:</td>
							<td><input type="text" name="txtnoLot" id="txtnoLot" value=""></td>
						</tr>

						<tr>
							<td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
							<td>No. Hakmilik</td>
							<td>:</td>
							<td><input type="text" name="txtnoHakmilik" id="txtnoHakmilik" value="" onblur="doChangePeganganHakmilik();"><span class="style1">$errorPeganganHakmilik</span> </td>
						</tr>
						<tr>
							<td width="1%"></td>
							<td width="28%">Pegangan Hakmilik</td>
							<td width="1%">:</td>
							<td width="70%"></td>
						</tr>

							<tr>
								<td>&nbsp;</td>
								<td>Luas Lot</td>
								<td>:</td>
								<td></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>No Warta</td>
								<td>:</td>
								<td></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>Tarikh Warta</td>
								<td>:</td>
								<td></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>Mukim</td>
								<td>:</td>
								<td></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>Daerah</td>
								<td>:</td>
								<td></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>Kategori Tanah</td>
								<td>:</td>
								<td></td>
					        </tr>
							<tr>
								<td>&nbsp;</td>
								<td>SubKategori Tanah</td>
								<td>:</td>
								<td></td>
					        </tr>
				</table>
			</td>
			<td width="50%" valign="top">
			<table width="100%"  cellpadding="2" cellspacing="2" border="0">
				<tr>
					<td>&nbsp;</td>
					<td>Syarat Nyata</td>
					<td>:</td>
					<td></td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>Sekatan Kepentingan</td>
					<td>:</td>
					<td></td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>Kegunaan Tanah</td>
					<td>:</td>
					<td></td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>Kementerian</td>
					<td>:</td>
					<td></td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>Agensi</td>
					<td>:</td>
					<td></td>
		        </tr>
			</table>
			</td>
			</tr>
				  <tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				  </tr>
				  <tr>
					<td>&nbsp;</td>
					<td>
					#if ($mode == 'view')
					  #if ($!statussemasa.equals("1") || $!statussemasa.equals("2"))
					  <input type="button" name="cmdKmskiniTnh" id="cmdKmskiniTnh" value="Kemaskini" onClick="doKemaskini()"/>
					  #end
					  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
					  ##end
					  #if ($mode == 'update')
					  <input type="button" name="cmdSimpanKemaskiniTnh" id="cmdSimpanKemaskiniTnh" value="Simpan" onClick="doSimpanKemaskiniMaklumatTnh()"/>
					  #end
					  #else
					  <input type="button" name="cmdSimpanKemaskiniTnh" id="cmdSimpanKemaskiniTnh" value="Simpan" onClick="doSimpanKemaskiniMaklumatTnh()"/>
					  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
					 #end
					 </td>



				  </tr>
            </table>
#else
		#foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
			<tr>
				<td width="50%" valign="top">

				<table width="100%"  cellpadding="2" cellspacing="2" border="0">
					<tr>
			          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			          <td width="28%">Negeri</td>
			          <td width="1%">:</td>
			          <td width="70%">$selectNegeri</td>
			        </tr>
				  <tr>
							<td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
							<td>No. Lot</td>
							<td>:</td>
							<td>
								#if ($mode == 'update')
									<input type="text" name="txtnoLot" id="txtnoLot" value="$beanMaklumatTanah.noLot">
								#else
									<input type="text" name="txtnoLot" id="txtnoLot" value="$beanMaklumatTanah.noLot" readonly="readonly" class="disabled">
								#end
									<input type="hidden" name="noLotTanah" id="noLotTanah" value="$beanMaklumatTanah.lot" /></td>
						</tr>

						<tr>
							<td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
							<td>No. Hakmilik</td>
							<td>:</td>
							<td>
							#if ($mode == 'update')
									<input type="text" name="txtnoHakmilik" id="txtnoHakmilik" value="$beanMaklumatTanah.noHakmilik" onblur="doChangePeganganHakmilik();">
								#else
									<input type="text" name="txtnoHakmilik" id="txtnoHakmilik" value="$beanMaklumatTanah.noHakmilik" readonly="readonly" class="disabled">
								#end

								<input type="hidden" name="noMilikTanah" id="noMilikTanah" value="$beanMaklumatTanah.hakmilik" /><span class="style1">$errorPeganganHakmilik</span> </td>
						</tr>
						<tr>
							<td width="1%"></td>
							<td width="28%">Pegangan Hakmilik</td>
							<td width="1%">:</td>
							<td width="70%"> $beanMaklumatTanah.peganganHakmilik
									<!-- #if ($mode == 'new')
									<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onblur="doChangePeganganHakmilik();">
									<input class="stylobutton100" name="cmdDaftar" type="button" value="Carian Tanah" onClick="pilihTanah('$idPermohonan')">
									#else
									<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled">
									#end
									<input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi">
									<span class="style1">$errorPeganganHakmilik</span>
									<span class="style4"><i><font color="#ff0000">Contoh</font> : </i><span class="style5">160140GRN00000576</span></span>
								-->
								</td>
						</tr>

							<tr>
								<td>&nbsp;</td>
								<td>Luas Lot</td>
								<td>:</td>
								<td>$beanMaklumatTanah.luas
									<input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$beanMaklumatTanah.idLuas" />
									<input type="hidden" name="luasTanah" id="luasTanah" value="$beanMaklumatTanah.luasBersamaan" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>No Warta</td>
								<td>:</td>
								<td>$beanMaklumatTanah.noWarta</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>Tarikh Warta</td>
								<td>:</td>
								<td>$beanMaklumatTanah.tarikhWarta</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>Mukim</td>
								<td>:</td>
								<td>$beanMaklumatTanah.mukim
									<input type="hidden" name="namaMukimTanah" id="namaMukimTanah" value="$beanMaklumatTanah.mukim" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>Daerah</td>
								<td>:</td>
								<td>$beanMaklumatTanah.daerah
									<input type="hidden" name="namaDerahTanah" id="namaDerahTanah" value="$beanMaklumatTanah.daerah" /></td>
							</tr><!--
							<tr>
								<td>&nbsp;</td>
								<td>Negeri</td>
								<td>:</td>
								<td>$beanMaklumatTanah.negeri

								</td>
							</tr> -->
							<tr>
								<td>&nbsp;</td>
								<td>Kategori Tanah</td>
								<td>:</td>
								<td>
									$beanMaklumatTanah.kategoriTanah
									<input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$beanMaklumatTanah.idNegeriTanah">
									<input type="hidden" name="namaNegeriTanah" id="namaNegeriTanah" value="$$beanMaklumatTanah.negeri">
								</td>
					        </tr>
							<tr>
								<td>&nbsp;</td>
								<td>SubKategori Tanah</td>
								<td>:</td>
								<td>
									$beanMaklumatTanah.subKategoriTanah
								</td>
					        </tr>
							<!-- <tr>
								<td>&nbsp;</td>
								<td>Kementerian</td>
								<td>:</td>
								<td>$beanMaklumatTanah.kementerian
									<input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatTanah.idKementerian">
									<input type="hidden" name="kodKementerian" id="kodKementerian" value="$beanMaklumatTanah.kodKementerian">
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>Agensi</td>
								<td>:</td>
								<td>$beanMaklumatTanah.agensi
									<input type="hidden" name="idAgensiTanah" id="idAgensiTanah" value="$beanMaklumatTanah.idAgensi">
							</tr> -->
				</table>
			</td>
			<td width="50%" valign="top">
			<table width="100%"  cellpadding="2" cellspacing="2" border="0">
				<tr>
					<td>&nbsp;</td>
					<td>Syarat Nyata</td>
					<td>:</td>
					<td>
						$beanMaklumatTanah.syarat
					</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>Sekatan Kepentingan</td>
					<td>:</td>
					<td>
						$beanMaklumatTanah.sekatan
					</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>Kegunaan Tanah</td>
					<td>:</td>
					<td>
						$beanMaklumatTanah.kegunaanTanah
					</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>Kementerian</td>
					<td>:</td>
					<td>
						$beanMaklumatTanah.kementerian
					</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>Agensi</td>
					<td>:</td>
					<td>
						$beanMaklumatTanah.agensi
					</td>
		        </tr>
			</table>
			</td>
			</tr>
				  <tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				  </tr>
				  <tr>
					<td>&nbsp;</td>
					<td>
					#if ($mode == 'view')
					  #if ($!statussemasa.equals("1") || $!statussemasa.equals("2"))
					  <input type="button" name="cmdKmskiniTnh" id="cmdKmskiniTnh" value="Kemaskini" onClick="doKemaskini()"/>
					  #end
					  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
					  ##end
					  #if ($mode == 'update')
					  <input type="button" name="cmdSimpanKemaskiniTnh" id="cmdSimpanKemaskiniTnh" value="Simpan" onClick="doSimpanKemaskiniMaklumatTnh()"/>
					  #end
					  #else
					  <input type="button" name="cmdSimpanKemaskiniTnh" id="cmdSimpanKemaskiniTnh" value="Simpan" onClick="doSimpanKemaskiniMaklumatTnh()"/>
					  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
					 #end
					 </td>



				  </tr>
            </table>
				  #end
#end
          </div>
          <!-- END MAKLUMAT TANAH -->

          <!-- START MAKLUMAT TUKARGUNA -->


          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
            #foreach ($beanMaklumatTukarguna in $beanMaklumatTukarguna)
              <tr>
                <td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td width="28%" valign="top">Tarikh Mohon</td>
                <td width="1%">:</td>
                <td width="70%">
                  #if ($mode == 'update')
                  <input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatTukarguna.tarikhTerima" onBlur="check_date(this);cekTarikhTerima(this)" size="9"/>
                  #else
                  <input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatTukarguna.tarikhTerima" onBlur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass"/>
                  #end
                  #if ($mode == 'update') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
              </tr>
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td valign="top">Tarikh Surat</td>
                <td>:</td>
                <td>
                  #if ($mode == 'update')
                  <input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatTukarguna.tarikhSurat" onBlur="check_date(this);cekTarikhSurat(this)" size="9"/>
                  #else
                  <input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatTukarguna.tarikhSurat" onBlur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
                  #end
                  #if ($mode == 'update')<a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Cadangan Kegunaan</td>
                <td valign="top">:</td>
                <td valign="top">
                	#if ($mode == 'update')
                	<textarea name="txtTujuanKegunaan" id="txtTujuanKegunaan" rows="5" cols="50">$beanMaklumatTukarguna.cadanganKegunaan</textarea>
                	#else
                	<textarea name="txtTujuanKegunaan" id="txtTujuanKegunaan" rows="5" cols="50" $readonly class="$inputTextClass">$beanMaklumatTukarguna.cadanganKegunaan</textarea>
                	#end
                </td>
              </tr>
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td>Luas Kegunaan</td>
                <td>:</td>
                <td >$selectLuasKegunaan</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Keluasan Asal</td>
                <td>:</td>
                <td>$beanMaklumatTukarguna.luasAsal HEKTAR
                  <input type="hidden" name="txtLuasAsal" id="txtLuasAsal" value="$beanMaklumatTukarguna.luasAsal"/></td>
              </tr>
        	  #if ($idLuasKegunaan == '2')
              	#if ($mode == 'update')
	              <tr>
	                <td><span class="style1">*</span></td>
	                <td>Unit Luas</td>
	                <td>:</td>
	                <td>#parse("app/php2/unit_luas.jsp") </td>
	              </tr>
	            #else
	              <tr>
	                <td><span class="style1"></span></td>
	                <td>Unit Luas</td>
	                <td>:</td>
	                <td><select name="socLuas" id="socLuas" $inputTextClass class="$inputTextClass">
	                    <option $selected value="0">SILA PILIH</option>
	                    <option $selectedL1 value="1">KM - KILOMETER PERSEGI</option>
	                    <option $selectedL2 value="2">H - HEKTAR</option>
	                    <option $selectedL3 value="3">M - METER PERSEGI</option>
	                    <option $selectedL4 value="4">E - EKAR,ROOD,POLE</option>
	                    <option $selectedL5 value="5">K - KAKI PERSEGI</option>
	                    <option $selectedL6 value="6">P - EKAR PERPULUHAN</option>
	                    <option $selectedL7 value="7">D - EKAR,DEPA</option>
	                    <option $selectedL8 value="8">R - RELONG,JEMBA,KAKI PERSEGI</option>
	                    <option $selectedL9 value="9">BN - BATU NAUTIKA</option>
	                  </select></td>
	              </tr>
	            #end
              #if ($idLuas != '99999' && $idLuas != '')
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td>Luas Mohon</td>
                <td>:</td>
                <td>
                  #if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' || $idLuas == '5' || $idLuas == '6' || $idLuas == '9')
	                  #if ($mode == 'update')
	                  	<input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" />
	                  #else
	                  	<input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" size="6"  $readonly class="$inputTextClass" />
	                  #end
                  #elseif ($idLuas == '7')
	                  #if ($mode == 'update')
	                  	<input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" onBlur="kiraLuas('$idLuas')"/>
	                  	<input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatTukarguna.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" />
	                  #else
	                  	<input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" size="6" $readonly class="$inputTextClass" />
	                  	<input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatTukarguna.luas2" style="text-align:right" size="6" $readonly class="$inputTextClass" />
	                  #end
                  #elseif ($idLuas == '8' || $idLuas == '4')
	                  #if ($mode == 'update')
	                  	<input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly onBlur="kiraLuas('$idLuas')" />
                  		<input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatTukarguna.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly onBlur="kiraLuas('$idLuas')" />
                  		<input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$beanMaklumatTukarguna.luas3" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" />
	                  #else
	                  	<input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" size="6" $readonly class="$inputTextClass" />
                  		<input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatTukarguna.luas2" style="text-align:right" size="6" $readonly class="$inputTextClass" />
                  		<input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$beanMaklumatTukarguna.luas3" style="text-align:right" size="6"  $readonly class="$inputTextClass"/>
	                  #end
                  #end </td>
              </tr>
              #end
              <tr>
                <td>&nbsp;</td>
                <td>Luas Bersamaan</td>
                <td>:</td>
                <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" value="$beanMaklumatTukarguna.luasBersamaan"  style="text-align:right" readonly class="disabled"/>
                  HEKTAR</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Baki Luas</td>
                <td>:</td>
                <td><input type="text" name="txtBakiLuas" id="txtBakiLuas" value="$beanMaklumatTukarguna.luasBaki" readonly class="disabled" style="text-align:right"/>
                  HEKTAR</td>
              </tr>

          #end
              <tr>

                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>
                #if ($mode == 'view')
                  #if ($!statussemasa.equals("1") || $!statussemasa.equals("2"))
                  <input type="button" name="cmdKmskiniTnh" id="cmdKmskiniTnh" value="Kemaskini" onClick="doKemaskini()"/>
                  #end
                  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                  ##end
                  #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskiniTnh" id="cmdSimpanKemaskiniTnh" value="Simpan" onClick="doSimpanKemaskiniMaklumatPermohonan()"/>
                  #end
                  #else
                  <input type="button" name="cmdSimpanKemaskiniTnh" id="cmdSimpanKemaskiniTnh" value="Simpan" onClick="doSimpanKemaskiniMaklumatPermohonan()"/>
                  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                 #end</td>
              </tr>
              #if ($mode == 'update')
              <tr>
                <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
              </tr>
              #end
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              #end
            </table>
          </div>
          <!-- END MAKLUMAT TUKARGUNA -->

          <div class="TabbedPanelsContent">
           	<table width="100%" border="0" cellspacing="2" cellpadding="2">
           	<tr>
  				<td> #parse("app/php2/online/ulasanKJP/tkr/frmTKRSenaraiSemak.jsp") </td>
              </tr>
           	</table>
         </div>

         <!-- <div class="TabbedPanelsContent">
         <table width="100%" border="0" cellspacing="2" cellpadding="2">
           	<tr>
           	<td>#parse("app/php2/online/ulasanKJP/tkr/frmTKRSenaraiLampiran.jsp")</td>
           	</tr>
           	</table>
         </div> -->
         <div class="TabbedPanelsContent">

		<input type="hidden" name="idjawatan" value="$idjawatan" />
		<input type="hidden" name="statussemasa" value="$statussemasa" />
		<input type="hidden" name="semakMode" value="$semakMode" />
		<input type="hidden" name="langkah" value="0" />
		<input type="hidden" name="buttonSend"  value="$buttonSend"/>
           	<table width="100%" border="0" cellspacing="2" cellpadding="2">
           	<td valign="top">
           	<tr>
           	<td colspan=2 align="center">
           	#if($semakMode == "update")
   			#if(($!idjawatan.equals("20")||$!idjawatan.equals("24"))&& $!statussemasa.equals("1"))
           	<td>
           	<input type="checkbox" name="pengesahan" id="pengesahan">&nbsp&nbsp
        	Saya, <b>$!namaPemohon</b>, $!kadPengenalanPemohon dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   			<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp tanpa sebarang keraguan dan paksaan dari mana-mana pihak.
   			<p align="center"> <input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Semakan" onclick="doAjaxCall${formName}('simpanpengesahan2')" /></p>
			</td>

			#elseif ($!idjawatan.equals("9") && $!statussemasa.equals("2"))
           	<td>
			<input type="checkbox" name="pengesahan" id="pengesahan">&nbsp&nbsp
        	Saya, <b>$!namaPemohon</b>, $!kadPengenalanPemohon dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   			<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp tanpa sebarang keraguan dan paksaan dari mana-mana pihak.
   			<p align="center"><input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Pengesahan" onclick="doAjaxCall${formName}('simpanpengesahan2')" /></p>
      		</td>
			#elseif ($!idjawatan.equals("4")&& $!statussemasa.equals("3"))
           	<td>
			<input type="checkbox" name="pengesahan" id="pengesahan">&nbsp&nbsp
        	Saya, <b>$!namaPemohon</b>, $!kadPengenalanPemohon dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   			<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp tanpa sebarang keraguan dan paksaan dari mana-mana pihak.
   			<br/>
   			<b><font color="BLUE" size="2"><span class="blink">Ambil Perhatian: Sila pastikan maklumat yang diisi pada permohonan adalah TEPAT dan MUKTAMAD.
	Permohonan yang telah <br>dihantar TIDAK DIBENARKAN untuk dipinda/dikemaskini.</span></font></b>
   			<p align="center"><input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Permohonan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
   			<input type="button" name="cmdKembalikanFail" id="cmdKembalikanFail" value="Kembalikan kepada penyedia" onclick="kembalikanFailOnline('$idPermohonan', 'yes')"/>
      		</td>


             	<!-- <input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doBacklist()" /> -->
            #else

           	<td>
                <!-- <input type="button" name="cdmCetak" id="cdmCetakBorang" value="Cetak Borang Permohonan" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"/> -->
                <input type="checkbox" name="pengesahan" id="pengesahan" checked disabled>&nbsp&nbsp

        	Saya, <b>$!namaPemohon</b>, $!kadPengenalanPemohon dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   			<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp tanpa sebarang keraguan dan paksaan dari mana-mana pihak.
      		<p align="center">
           		<input type="button" name="cdmCetak" id="cdmCetakPengesahan" value="Cetak Pengesahan Permohonan" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"/>
            	<input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
            	</p>
            	<!-- <input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doBacklist()" /> -->
   			#end

   			#else
   			<td>
                <!-- <input type="button" name="cdmCetak" id="cdmCetakBorang" value="Cetak Borang Permohonan" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"/> -->
                <input type="checkbox" name="pengesahan" id="pengesahan" checked disabled>&nbsp&nbsp

        	Saya, <b>$!namaPemohon</b>, $!kadPengenalanPemohon dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   			<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp tanpa sebarang keraguan dan paksaan dari mana-mana pihak.
      		<p align="center">
           		<input type="button" name="cdmCetak" id="cdmCetakPengesahan" value="Cetak Pengesahan Permohonan" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"/>
            	<input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
            	</p>
            	<!-- <input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doBacklist()" /> -->
            </td>
   			#end
   			#end
            </td>
           	</tr>
           	</table>
         </div>
        </div>
      </div></td>
  </tr>
</table>
<!-- <div id="calculateTotalPercentPengarah_result"></div>
<fieldset id="tableReport" style="display:none;"-->
<!--<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"> Borang Permohonan </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"> Pengesahan Permohonan </a></td>
  </tr>
</table>
</fieldset> -->
<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
var checker = document.getElementById('pengesahan');
var sendbtn = document.getElementById('cmdSimpan');
var pindaanbtn = document.getElementById('cmdPindaan',true);
// when unchecked or checked, run the function
checker.onchange = function(){
sendbtn.disabled = true;
if(pindaanbtn !=null){
pindaanbtn.disabled = true;
}

if(this.checked){
   sendbtn.disabled = false;
   if(pindaanbtn !=null){
   	pindaanbtn.disabled = false;
   }
} else {
   sendbtn.disabled = true;
   if(pindaanbtn !=null){
   	pindaanbtn.disabled = true;
   }
}

}

function kembali() {
	document.${formName}.submit2.value = "";
	document.${formName}.submit();
}

function doChangeTabUpper(tabId) {
	document.${formName}.submit2.value = "seterusnya";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function pilihTanahPYW() {
	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmPYWOnlinePopupSenaraiTanahView";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensi) {
	document.${formName}.idHakmilikAgensiPopup.value = idHakmilikAgensi;
	doAjaxCall${formName}("doChangeMaklumatTanah");
}
function doSimpanKemaskiniMaklumatTnh() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.submit2.value = "seterusnya";
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 0;
	document.${formName}.submit2.value = "seterusnya";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatTnh";
	document.${formName}.submit();
}
function doSimpanKemaskiniMaklumatPermohonan() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.submit2.value = "seterusnya";
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 0;
	document.${formName}.submit2.value = "seterusnya";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPermohonan";
	document.${formName}.submit();
}
function doChangePeganganHakmilik() {
	document.${formName}.submit2.value = "seterusnya";
	doAjaxCall${formName}("doChangePeganganHakmilik");
}
function doBacklist() {
	document.${formName}.submit2.value = "";
	document.${formName}.submit();
}
function validateLuas(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}

	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(5);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}

function calculate(valueMohon,valueBaki){
	var luasPenyewaan = document.${formName}.txtLuasPenyewaan.value * 1;
	var luasAsal = document.${formName}.txtLuasAsal.value * 1;

	if (luasPenyewaan != ""){
		if (luasPenyewaan > luasAsal){
			alert('Luas yang dimasukkan telah melebihi luas asal.');
			document.${formName}.txtLuasPenyewaan.value = valueMohon;
			document.${formName}.txtBakiLuas.value = valueBaki;
			return;
		} else {
			var luasBaki = (luasAsal - luasPenyewaan) * 1;
			document.${formName}.txtBakiLuas.value = luasBaki.toFixed(5);
		}
	}
}
function doKemaskini() {
	document.${formName}.submit2.value = "seterusnya";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatPenyewaan(idLuas) {

	if(document.${formName}.socTempohSewa.value == "SILA PILIH"){
		alert('Sila pilih Tempoh Sewa.');
  		document.${formName}.socTempohSewa.focus();
		return;
	}
	if(document.${formName}.socLuasKegunaan.value == ""){
		alert('Sila pilih Luas Kegunaan.');
  		document.${formName}.socLuasKegunaan.focus();
		return;
	}
	if(document.${formName}.socLuasKegunaan.value == "2"){
		if(document.${formName}.socLuas.value == "0"){
			alert('Sila pilih Unit Luas.');
			document.${formName}.socLuas.focus();
			return;
		}

		if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Tukarguna .');
				document.${formName}.txtLuasMohon1.focus();
				return;
			}
		}
		else
		if(idLuas == '4' || idLuas == '8'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Tukarguna.');
				document.${formName}.txtLuasMohon1.focus();
				return;
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Tukarguna.');
				document.${formName}.txtLuasMohon2.focus();
				return;
			}
			if(document.${formName}.txtLuasMohon3.value == ""){
				alert('Sila masukkan Luas Tukarguna.');
				document.${formName}.txtLuasMohon3.focus();
				return;
			}
		}
		else
		if(idLuas == '7'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Tukarguna.');
				document.${formName}.txtLuasMohon1.focus();
				return;
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Tukarguna.');
				document.${formName}.txtLuasMohon2.focus();
				return;
			}
		}
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPenyewaan";
	document.${formName}.submit();
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doSimpanKemaskiniMaklumatPemohon(type) {
	if(type == "syarikat"){
		if(document.${formName}.txtModalBenar.value == ""){
			alert('Sila masukan Modal Dibenarkan.');
  			document.${formName}.txtModalBenar.focus();
			return;
		}
		if(document.${formName}.txtModalJelas.value == ""){
			alert('Sila masukan Modal Jelas.');
  			document.${formName}.txtModalJelas.focus();
			return;
		}
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPemohon";
	document.${formName}.submit();
}
function doDaftarPengarah(){
	document.${formName}.flagPopup.value = "openPopupPengarah";
	document.${formName}.modePopup.value = "new";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doBatalPengarah(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doPaparPengarah(idPengarah){
	document.${formName}.idPengarah.value = idPengarah;
	document.${formName}.flagPopup.value = "openPopupPengarah";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doKemaskiniPengarah(){
	document.${formName}.flagPopup.value = "openPopupPengarah";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniPengarah(){
	document.${formName}.flagPopup.value = "openPopupPengarah";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniPengarah(){
	if(document.${formName}.socWargaPengarah.value == ""){
		alert('Sila pilih Warganegara.');
  		document.${formName}.socWargaPengarah.focus();
		return;
	}
	if(document.${formName}.txtNamaPengarah.value == ""){
		alert('Sila masukkan Nama Pengarah.');
  		document.${formName}.txtNamaPengarah.focus();
		return;
	}
	if(document.${formName}.txtSaham.value == ""){
		alert('Sila masukan Pegangan Saham.');
  		document.${formName}.txtSaham.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupPengarah";
		document.${formName}.modePopup.value = "new";
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.flagPopup.value = "openPopupPengarah";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPengarah";
	doAjaxCall${formName}("");
}
function doSimpanPengarah(){
	if(document.${formName}.socWargaPengarah.value == ""){
		alert('Sila pilih Warganegara.');
  		document.${formName}.socWargaPengarah.focus();
		return;
	}
	if(document.${formName}.txtNamaPengarah.value == ""){
		alert('Sila masukkan Nama Pengarah.');
  		document.${formName}.txtNamaPengarah.focus();
		return;
	}
	if(document.${formName}.txtSaham.value == ""){
		alert('Sila masukan Pegangan Saham.');
  		document.${formName}.txtSaham.focus();
		return;
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupPengarah";
		document.${formName}.modePopup.value = "new";
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "doSimpanPengarah";
	document.${formName}.submit();
}

function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function echeck(str) {

		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   alert("Sila Masukan Email Dengan Betul.")
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		   alert("Sila Masukan Email Dengan Betul.")
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }

 		 return true
	}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function validateLength(str) {

	if (str.length < 5) {
 		alert("Sila Masukan Poskod Dengan Betul.")
		 return false
	}
}
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}

	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function checkPercentage(x){;
	if(parseInt(x) > 100){
		alert('Sila Nilai Peratusan Pegangan Saham dengan Betul.');
  		document.${formName}.txtSaham.focus();
		return;
	}
}
function doHapusMaklumatPengarah(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusMaklumatPengarah";
	document.${formName}.submit();
}
function validateTempohSewa() {
	//if it is character, then remove it..
	if (document.${formName}.txtTempohSewa.value > 36 ){
		alert('Tempoh sewa anda melebihi had yang dibenarkan. Sila isi tempoh yang betul.');
		document.${formName}.txtTempohSewa.focus();
		return;
	}
}
function kiraLuas(idLuas){
  var jenisLuas = idLuas;

  // KILOMETER PERSEGI
  if(jenisLuas == "1"){

		var luasK = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasK = document.${formName}.txtLuasMohon1.value*1;
		}
		var luasH = luasK*100;

		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "2"){ //HEKTER
		var luasH = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasH = document.${formName}.txtLuasMohon1.value*1;
		}
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "3"){ // METER PERSEGI

		var luasM = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasM = document.${formName}.txtLuasMohon1.value*1;
		}
  	  	var luasH = (luasM*0.0001);
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "4"){  //EKAR, ROOD, POLE

	  	var luasE = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasE = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasR = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasR = document.${formName}.txtLuasMohon2.value*1;
		}
	  	var luasP = 0;
		if (document.${formName}.txtLuasMohon3.value != ''){
			luasP = document.${formName}.txtLuasMohon3.value*1;
		}
	  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);

		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasMohon3.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "5"){ //KAKI PERSEGI

	  var luasAsal = 0;
	  if (document.${formName}.txtLuasMohon1.value != ''){
	  	  luasAsal = document.${formName}.txtLuasMohon1.value*1;
	  }
	  var luasH = luasAsal*0.0000092;

	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		 	document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "6"){	//EKAR PERPULUHAN

	  var luasAsal = 0;
	  if (document.${formName}.txtLuasMohon1.value != ''){
	  	  luasAsal = document.${formName}.txtLuasMohon1.value*1;
	  }
	  var luasH = luasAsal*0.405;

	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "7"){ //EKAR,DEPA

	  	var luasE = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasE = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasD = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasD = document.${formName}.txtLuasMohon2.value*1;
		}

	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);

	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		  	document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI

	 	var luasR = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasR = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasJ = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasJ = document.${formName}.txtLuasMohon2.value*1;
		}
	  	var luasK = 0;
		if (document.${formName}.txtLuasMohon3.value != ''){
			luasK = document.${formName}.txtLuasMohon3.value*1;
		}

	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);

	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasMohon3.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		  	document.${formName}.txtBakiLuas.value = bakiLuas;
		}
	}
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function calculateTotalPercentPengarah() {
	url = "../servlet/ekptg.view.php2.FrmPYWServlet?command=calculateTotalPercentPengarah";
	actionName = "calculateTotalPercentPengarah";
	target = "calculateTotalPercentPengarah_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function checkPercentage(){;
	if(parseInt(document.${formName}.txtSaham.value) > 100){
		alert('Sila masukkan nilai peratusan saham dengan betul.');
		document.${formName}.txtSaham.value = "";
  		document.${formName}.txtSaham.focus();
		return;
	}
}
function popupMsg(){
	alert('Jumlah peratusan saham yang dimasukkan telah melebihi 100%');
	document.${formName}.txtSaham.value = "";
}
function doHantarEmel(){
	if(pengesahan.checked != true){
		alert('Sila tanda pada checkbox untuk teruskan permohonan. ');
		return;
	}else{}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarEmel";
	document.${formName}.submit();
}
function doHapus(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapus";
	document.${formName}.actionPenyewaan.value = "";
	document.${formName}.submit();
}
</script>

<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakBorangPermohonan(idPermohonan) {
	var url = "../servlet/ekptg.report.php2.online.PYWBorangPermohonan?ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPengesahanPermohonan(idPermohonan) {
	var url = "../servlet/ekptg.report.php2.online.TKRPengesahanPermohonanOnline?ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>

<script>
<!-- MAKLUMAT LAMPIRAN -->
function daftarLampiran() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineKJPSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit2 = "seterusnya";
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "openPopupLampiran";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}

<!-- SENARAI SEMAK -->
function doSimpanKemaskiniSenaraiSemak() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniSenaraiSemak";
	document.${formName}.submit();
}

function tutup() {
	//
	window.close();
	refresh('Y');
}

function papar(idFail,idStatus) {
	document.${formName}.idFail.value = idFail;
	document.${formName}.idStatus.value = idStatus;
	document.${formName}.submit2.value = "seterusnya";
	document.${formName}.submit();
}

function doChangeLuasKegunaan() {
	document.${formName}.submit2.value = "seterusnya";
	doAjaxCall${formName}("doChangeLuasKegunaan");
}

function doChangeNegeri() {
	document.${formName}.submit2.value = "seterusnya";
	doAjaxCall${formName}("doChangeNegeri");
}

function doChangeLuas() {
	document.${formName}.submit2.value = "seterusnya";
	doAjaxCall${formName}("doChangeLuas");
}

function kembalikanFailOnline(id_permohonan,formnew) {
	var w = "400";
	var h = "200";
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	var url = "../x/${securityToken}/FrmPopupTolakPermohonan?id_permohonan="+id_permohonan+"&formnew="+formnew+"&modul=php&jenisTolak=pelulus";

	var hWnd = window.open(url, "Permohonan Online Dikembalikan", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>
$javascriptLampiran