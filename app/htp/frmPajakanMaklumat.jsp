#if ( $SimpanStatus == "success" )
    <table width="100%" border="0">
        <tr>
            <td>
            <font color="blue">
            <b>
            #if ( $ResultSimpan == "baru" )
                Fail anda telah berjaya disimpan.
            #elseif ($ResultSimpan == "kemaskini" )
                Fail anda telah berjaya dikemaskini.
            #end
            </b>
            </font>
            </td>
        </tr>
    </table>
#end
<br>
<table width="100%">
<tr>
		<td>
			#parse ("app/htp/frmPajakanInfo.jsp")
		</td>
    </tr>
    <tr>
    	<td>
            <div id="TabbedPanels1" class="TabbedPanels">
              <ul class="TabbedPanelsTabGroup">
                <li class="TabbedPanelsTab" title="Pemohon" onclick="setSelected(0);PemohonView();"><strong><font size="1">Pemohon Pajakan</font></strong></li>
                <li class="TabbedPanelsTab" title="Ulasan" onclick="setSelected(1);UlasanView();"><strong><font size="1">Ulasan</font></strong></li>
                <li class="TabbedPanelsTab" title="Draf" onclick="setSelected(2);DrafView();"><strong><font size="1">Draf</font></strong></li>
                <li class="TabbedPanelsTab" title="Memorandum" onclick="setSelected(3);MJMView();"><strong><font size="1">Memorandum Jemaah Menteri</font></strong></li>
              </ul>
              <div class="TabbedPanelsContentGroup">
              <div class="TabbedPanelsContent">
              	#set ($IdPemohon = "")
               	#set ($NamaPemohon = "")
                #set ($NoPemohon = "")
                #set ($Alamat1 = "")
                #set ($Alamat2 = "")
                #set ($Alamat3 = "")
                #set ($Poskod = "")
                #set ($NoTel = "")
                #set ($NoFax = "")
                #foreach ( $pemohon in $Pemohon )
                	#set ($IdPemohon = $pemohon.IdPemohon)
                	#set ($NamaPemohon = $pemohon.NamaPemohon)
                	#set ($NoPemohon = $pemohon.NoPemohon)
                    #set ($Alamat1 = $pemohon.Alamat1)
                    #set ($Alamat2 = $pemohon.Alamat2)
                    #set ($Alamat3 = $pemohon.Alamat3)
                    #set ($Poskod = $pemohon.Poskod)
                    #set ($NoTel = $pemohon.NoTel)
                    #set ($NoFax = $pemohon.NoFax)
                #end
                
                #set ($idNegeri = "")
                #foreach ($negeri in $Info)
                	#set ($idNegeri = $negeri.idNegeri)
                #end
                
                #set ($btnNamePemohon = "value='Kosongkan'")
				#if ($NamaPemohon != "")
					#set ($btnNamePemohon = "value='Batal'")
				#end
                <fieldset>
					<legend>Maklumat Pemohon</legend>
                <table width="100%" border="0">
                          <tr>
                            <td width="20%"><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
                            <td width="15%"><div align="left"><strong>Nama / Syarikat</strong></div></td>
                            <td width="1%"><strong>:</strong></td>
                            <td width="64%"><input type="text" name="txtNamaPemohon" onkeyup="this.value=this.value.toUpperCase();" size="45" maxlength="80" value="$NamaPemohon" $mode/></td>
                          </tr>
                          <tr>
                            <td width="20%"><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
                            <td width="15%"><div align="left"><strong>No. KP / Syarikat</strong></div></td>
                            <td width="1%"><strong>:</strong></td>
                            <td width="64%"><input type="text" name="txtNoPemohon" onkeyup="this.value=this.value.toUpperCase();" size="45" maxlength="80" value="$NoPemohon" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
                            <td><div align="left"><strong>Alamat</strong></div></td>
                            <td><strong>:</strong></td>
                            <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="45" maxlength="60" value="$Alamat1" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><div align="left"><strong></strong></div></td>
                            <td>&nbsp;</td>
                            <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="45" maxlength="60" value="$Alamat2" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><div align="left"><strong></strong></div></td>
                            <td><strong></strong></td>
                            <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="45" maxlength="60" value="$Alamat3" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
                            <td><div align="left"><strong>Poskod</strong></div></td>
                            <td><strong>:</strong></td>
                            <td><input type="text" name="txtPoskod" size="15" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$Poskod" $mode /></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
                            <td><div align="left"><strong>Daerah</strong></div></td>
                            <td><strong>:</strong></td>
                            <td>$selectADaerah</td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
                            <td><div align="left"><strong>Negeri</strong></div></td>
                            <td><strong>:</strong></td>
                            <td>$selectANegeri</td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><div align="left"><strong>No. Telefon</strong></div></td>
                            <td><strong>:</strong></td>
                            <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$NoTel" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><div align="left"><strong>No. Fax</strong></div></td>
                            <td><strong>:</strong></td>
                            <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$NoFax" $mode/></td>
                          </tr>
                          
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><div align="left"><strong></strong></div></td>
                            <td><strong></strong></td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="4">
                              <div align="center"><strong>
                              <input type="button" name="cmdKemaskini" id="cmdKemaskini" style="display:$Style1" value="Kemaskini" onclick="KemaskiniPemohon()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdSimpan" id="cmdSimpan" style="display:$Style2" value="Simpan" onclick="SimpanPemohon()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdBatal" id="cmdBatal" style="display:$Style2" $btnNamePemohon onclick="BatalPemohon()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()">
                            </strong></div></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><div align="left"></div></td>
                            <td><strong></strong></td>
                            <td>&nbsp;</td>
                          </tr>                          
                    </table>
                  </fieldset>
				#set ($idUlasanKJP = "")
               	#set ($tarikhHantar = "")
                #set ($tarikhTerima = "")
                #set ($ulasanKJP = "")
                #foreach ( $ulasankjp in $UlasanKJP )
                	#set ($idUlasanKJP = $ulasankjp.idUlasanKJP)
                    #set ($tarikhHantar = $ulasankjp.tarikhHantarKJP)
                    #set ($tarikhTerima = $ulasankjp.tarikhTerimaKJP)
                    #set ($ulasanKJP = $ulasankjp.ulasanKJP)
                #end
                </div>
                <div class="TabbedPanelsContent">
                <table width="100%" border="0">
          <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
                            <legend>Ulasan KJP</legend>
                            <table width="100%" border="0">
                              <tr>
                                <td width="15%" scope="row" align="right"><strong><font color="#FF0000">*</font></strong></td>
                                <td width="15%" scope="row"><div align="left"><strong>Tarikh Hantar</strong></div></td>
                                <td width="1%"><strong>:</strong></td>
                                <td width="69%"><input type="text" name="txdTarikhHantarKJP" id="txdTarikhHantarKJP" size="10" value="$tarikhHantar" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantarKJP',false,'dmy');" style="display:$Style2"></td>
                              </tr>
                              <tr>
                                <td scope="row" align="right"><strong><font color="#FF0000">*</font></strong></td>
                                <td scope="row"><div align="left"><strong>Tarikh Terima</strong></div></td>
                                <td><strong>:</strong></td>
                                <td><input type="text" name="txdTarikhTerimaKJP" id="txdTarikhTerimaKJP" size="10" value="$tarikhTerima" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhTerimaKJP',false,'dmy');" style="display:$Style2"></td>
                              </tr>
                              <tr>
                                <td scope="row" align="right" valign="top">&nbsp;</td>
                                <td scope="row" valign="top"><div align="left"><strong>Ulasan</strong></div></td>
                                <td valign="top"><strong>:</strong></td>
                                <td><label>
                                      <textarea name="txtUlasanKJP" id="txtUlasanKJP" cols="30" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc>$ulasanKJP</textarea>
                                    </label></td>
                              </tr>
                              <tr>
                                <td colspan="4">
                                  <div align="center">
  							  <input type="button" name="cmdKemaskini" id="cmdKemaskini" style="display:$Style1" value="Kemaskini" onclick="KemaskiniUlasanKJP()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdSimpan" id="cmdSimpan" style="display:$Style2" value="Simpan" onclick="SimpanUlasanKJP()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdBatal" id="cmdBatal" style="display:$Style2" $btnNamePemohon onclick="BatalUlasanKJP()">
  &nbsp;&nbsp;
                             <!-- <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()"> -->
                                  </div></td>
                              </tr>
                            </table>

                            </fieldset>
							</div></td>
                          </tr>                          
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="2"><div align="left">
                            #set ($btnNameTanah = "value='Kosongkan'")
                            #if ($IdUlasankptg != "")
                                #set ($btnNameTanah = "value='Batal'")
                            #end
                            <fieldset>
                            <legend>Ulasan JPPH</legend>
                            <table width="100%" border="0">
                              <tr class="table_header">
                                <td width="5%"><strong>Bil.</strong></td>
                                <td width="15%"><strong>Tahun Nilaian</strong></td>
                                <td width="20%"><strong>Nilai Tanah</strong></td>
                                <td width="15%"><strong>Saranan Bayaran</strong></td>
                                <td width="40"><strong>Ulasan</strong></td>
                                <td width="5%"><strong></strong></td>
                              </tr>
                              #set ($countJPPH = 0)
                              #foreach ( $listjpph in $ListJPPH )
                                #set ($countJPPH = $countJPPH+1)
                                  #set( $i = $velocityCount )
                                  #if ( ($i % 2) != 1 )
                                       #set( $row = "row2" )
                                  #else
                                       #set( $row = "row1" )
                                  #end
                              <tr>
                                <td class="$row">$countJPPH</td>
                                <td class="$row">$listjpph.tahunNilaian</td>
                                <td class="$row">$listjpph.nilaiTanah</td>
                                <td class="$row">$listjpph.syorBayaran</td>
                                <td class="$row">$listjpph.keterangan</td>
                                <td class="$row"><a href="javascript:KemaskiniUlasanJPPH('$listjpph.idUlasannilai', '$listjpph.tahunNilaian', '$listjpph.nilaiTanah', '$listjpph.syorBayaran', '$listjpph.keterangan')" class="style1"><img src="../img/edit.gif" border="0"></a></td>
                              </tr>
                              #end
                              #set ($show = "")
                              #if ($countJPPH != 0)
                                #set ($show = "none")
                              #else
                              	<tr> 
                                   <td colspan="6" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                                </tr>
                              #end
                              <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                              </tr>
                            </table>
                            <table width="100%" border="0">
                              <tr style="display:$show">
                                <td width="15%" scope="row"><div align="right"><font color="#FF0000">*</font></div></td>
                                <td width="15%" scope="row"><div align="left"><strong>No. Ruj. JPPH</strong></div></td>
                                <td width="1%"><strong>:</strong></td>
                                <td width="69%"><input type="text" name="txtNoRujJPPH" id="txdNoRujJPPH" onkeyup="this.value=this.value.toUpperCase();" value=""/></td>
                              </tr>
                              <tr style="display:$show">
                                <td scope="row"><div align="right"><font color="#FF0000">*</font></div></td>
                                <td scope="row"><div align="left"><strong>Tarikh Hantar</strong></div></td>
                                <td><strong>:</strong></td>
                                <td><input type="text" name="txdTarikhHantarJPPH" id="txdTarikhHantarJPPH" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantarJPPH',false,'dmy');"></td>
                              </tr>
                              <tr style="display:$show">
                                <td scope="row"><div align="right"><font color="#FF0000">*</font></div></td>
                                <td scope="row"><div align="left"><strong>Tarikh Terima</strong></div></td>
                                <td><strong>:</strong></td>
                                <td><input type="text" name="txdTarikhTerimaJPPH" id="txdTarikhTerimaJPPH" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhTerimaJPPH',false,'dmy');"></td>
                              </tr>
                              <tr style="display:$show">
                                <td scope="row"><div align="right"><font color="#FF0000">*</font></div></td>
                                <td scope="row" valign="top"><div align="left"><strong>Nama Pegawai</strong></div></td>
                                <td><strong>:</strong></td>
                                <td>$senaraiPegawai</td>
                              </tr>
                              <tr>
                                <td scope="row"><div align="right"><font color="#FF0000">*</font></div></td>
                                <td scope="row" valign="top"><div align="left"><strong>Tahun Nilaian</strong></div></td>
                                <td><strong>:</strong></td>
                                <td><select name="socTahunNilaian" > <option value=>Sila Pilih Tahun Nilaian</option>
                                        <option  value="5">05 Tahun</option>
                                        <option  value="10">10 Tahun</option>
                                        <option  value="15">15 Tahun</option>
                                        <option  value="20">20 Tahun</option>                                        
                                        <option  value="25">25 Tahun</option>
                                        <option  value="30">30 Tahun</option>
                                       <!-- <option  value="7">Pukal</option>
                                        <option  value="8">Tahunan</option> -->
                                        </select></td>
                              </tr>
                              <tr>
                                <td scope="row"><div align="right"><font color="#FF0000">*</font></div></td>
                                <td scope="row" valign="top"><div align="left"><strong>Nilaian Tanah</strong></div></td>
                                <td><strong>:</strong></td>
                                <td><strong>RM </strong><input type="text" name="txtNilaiTanah" id="txtNilaiTanah" onkeyup="validateCurrency(this,this.value);" value=""/></td>
                              </tr>
                              <tr>
                                <td scope="row" valign="top"><div align="right"></div></td>
                                <td scope="row" valign="top"><div align="left"><strong>Syor Bayaran</strong></div></td>
                                <td><strong>:</strong></td>
                                <td><strong>RM </strong><input type="text" name="txtSyorBayaran" id="txtSyorBayaran" onkeyup="validateCurrency(this,this.value);" value="" /></td>
                              </tr>
                              <tr>
                                <td scope="row" valign="top"><div align="right"></div></td>
                                <td scope="row" valign="top"><div align="left"><strong>Keterangan</strong></div></td>
                                <td valign="top"><strong>:</strong></td>
                                <td><label>
                                  <textarea name="txtKeteranganJPPH" id="txtKeteranganJPPH" cols="41" rows="3" onkeyup="this.value=this.value.toUpperCase();"></textarea>
                                </label></td>
                              </tr>
                              <tr>
                                <td colspan="4">
                                  <div align="center">
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="SimpanUlasanJPPH()" />
  &nbsp;&nbsp;
  <input type="button" name="cmdBatal" id="cmdBatal" value="Kosongkan" onclick="BatalUlasanJPPH()" />
  &nbsp;&nbsp;
  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()" />
                                  </div></td>
                              </tr>
                            </table>

                            </fieldset>
                            </div></td>
                          </tr>
                    </table>

                </div>
                <div class="TabbedPanelsContent">
                <table width="100%" border="0">
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<legend>Maklumat Draf</legend>
                            	<table width="100%" border="0">
							  <tr>
							    <td colspan="4"><table width="100%" border="0">
                                  <tr class="table_header">
                                    <td width="10%"><strong>Bil.</strong></td>
                                    <td width="25%"><strong>Tarikh Hantar Draf</strong></td>
                                    <td width="25%"><strong>Tarikh Terima Draf</strong></td>
                                    <td width="35%"><strong>Ulasan</strong></td>
                                    <td width="5%"></td>
                                  </tr>
                                  #set ($countDraf = 0)
                                  #foreach ( $listdraf in $ListDraf )
                                    #set ($countDraf = $countDraf+1)
                                      #set( $i = $velocityCount )
                                      #if ( ($i % 2) != 1 )
                                           #set( $row = "row2" )
                                      #else
                                           #set( $row = "row1" )
                                      #end
                                  <tr>
                                    <td class="$row">$countDraf</td>
                                    <td class="$row">$listdraf.tarikhHantarDraf</td>
                                    <td class="$row">$listdraf.tarikhTerimaDraf</td>
                                    <td class="$row">$listdraf.ulasanLA</td>
                                    <td class="$row" align="center"><a href="javascript:KemaskiniDraf('$listdraf.idDokumenperjanjian', '$listdraf.tarikhHantarDraf', '$listdraf.tarikhTerimaDraf','$listdraf.ulasanLA')" class="style1"><img src="../img/edit.gif" border="0"></a></td>
                                  </tr>
                                  #end
                                  #if ($countDraf == 0)
                                  <tr> 
                                    <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                                  </tr>
                                  #end
                                </table></td>
							    </tr>
                                <tr>
							    <td width="24%">&nbsp;</td>
                            			<td width="15%"></td>
                            			<td width="1%"></td>
                            			<td width="60%"><div align="right"><strong>Bil. Draf&nbsp;:&nbsp;&nbsp;&nbsp;$countDraf</strong></div></td>
                       			  </tr>
							  <!-- <tr>
							    <td width="24%">&nbsp;</td>
                            			<td width="15%"><div align="left"><strong>Bil. Draf</strong></div></td>
                            			<td width="1%"><strong>:</strong></td>
                            			<td width="60%"><input type="text" name="txtCount" id="txtCount" size="15" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$countDraf" readonly="readonly"/></td>
                       			  </tr> -->
                          			
                          			
                                          <tr style="display:none">
                                            <td colspan="3"><table width="100%" border="0">
                                              <tr>
                                                <td width="35%">&nbsp;</td>
                                                <td width="34%"><strong>Tarikh Hantar Draf</strong></td>
                                                <td width="1%"><strong>:</strong></td>
                                                <td width="30%"><input type="text" name="txdTarikhHantarDraf1" id="txdTarikhHantarDraf1" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantarDraf',false,'dmy');" style="display:$Style2"></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><strong>Tarikh Hantar LA</strong></td>
                                                <td><strong>:</strong></td>
                                                <td><input type="text" name="txdTarikhHantar" id="txdTarikhHantar" size="10" value="$TarikhHantar" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantar',false,'dmy');" style="display:$Style2"></td>
                                              </tr>
                                            </table></td>
                                            <td>
                                            <table width="100%" border="0">
                                              <tr>  
                                              	<td width="5%">&nbsp;</td>                                              
                                                <td width="38%"><strong>Tarikh Terima Draf</strong></td>
                                                <td width="1%"><strong>:</strong></td>
                                                <td width="30%"><input type="text" name="txdTarikhHantarDraf1" id="txdTarikhHantarDraf1" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantarDraf',false,'dmy');"></td>
                                				<td width="26%">&nbsp;</td>
                                              </tr>
                                              <tr style="display:none">     
                                              	<td>&nbsp;</td>                                           
                                                <td><strong>Tarikh Terima Kembali LA</strong></td>
                                                <td><strong>:</strong></td>
                                                <td><input type="text" name="txdTarikhHantar" id="txdTarikhHantar" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantar',false,'dmy');"></td>
                                				<td>&nbsp;</td>
                                              </tr>
                                            </table>                                            </td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td valign="top"><strong>Tarikh Hantar Draf</strong></td>
                                            <td valign="top"><strong>:</strong></td>
                                            <td><input type="text" name="txdTarikhHantarDraf" id="txdTarikhHantarDraf" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantarDraf',false,'dmy');"></td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td valign="top"><strong>Tarikh Terima Draf</strong></td>
                                            <td valign="top"><strong>:</strong></td>
                                            <td><input type="text" name="txdTarikhTerimaDraf" id="txdTarikhTerimaDraf" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhTerimaDraf',false,'dmy');"></td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td valign="top"><div align="left"><strong>Ulasan LA</strong></div></td>
                                            <td valign="top"><strong>:</strong></td>
                                            <td><label>
                                      <textarea name="txtUlasanDraf" id="txtUlasanDraf" cols="30" rows="3" onkeyup="this.value=this.value.toUpperCase();"></textarea>
                                    </label></td>
                                  </tr>
                                  <tr>
                                <td colspan="4">
                                  <div align="center">
  							  <input type="button" name="cmdSimpanDraf" id="cmdSimpanDraf" value="Simpan" onclick="SimpanDraf()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdBatalDraf" id="cmdBatalDraf" value="Kosongkan" onclick="BatalDraf()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdKembaliDraf" id="cmdKembaliDraf" value="Kembali" onClick="Kembali()">
                                  </div></td>
                              </tr>
								</table>
                              </fieldset>
							</div></td>
                          </tr>                          
                    </table>

                </div>
                <div class="TabbedPanelsContent">
                #set ($IdJemaahmenteri = "")
               	#set ($THKSU = "")
                #set ($TTKSU = "")
                #set ($THP = "")
                #set ($TK = "")
                #set ($TMJ = "")
                #set ($statusKeputusan = "")
                #set ($tindakanLanjut = "")
                #set ($noMemorandum = "")
                #foreach ( $listmjm in $ListMJM )
                    #set ($IdJemaahmenteri = $listmjm.idJemaahmenteri)
                	#set ($THKSU = $listmjm.tarikhHantarksu)
                    #set ($TTKSU = $listmjm.tarikhTerimaksu)
                    #set ($THP = $listmjm.tarikhHantarPemohon)
                    #set ($TK = $listmjm.tarikhKeputusan)
                    #set ($TMJ = $listmjm.tarikhMsyrtJemaah)
                    #set ($statusKeputusan = $listmjm.statusKeputusan)
                    #set ($tindakanLanjut = $listmjm.tindakanLanjut)
                    #set ($noMemorandum = $listmjm.noMemorandum)
                #end
                
                #set ($btnNameMJM = "value='Kosongkan'")
				#if ($IdJemaahmenteri != "")
					#set ($btnNameMJM = "value='Batal'")
				#end
                <table width="100%" border="0">                          
                          <tr>
                            <td colspan="2"><div align="left">
                            <fieldset>
							<legend>Memorandum Jemaah Menteri</legend>
                              <table width="100%" border="0">
                                <tr>
                                  <td width="20%"><div align="right"><strong></strong></div></td>
                                    <td width="29%"><div align="left"><strong>Tarikh Hantar PTP</strong></div></td>
                                    <td width="1%"><strong>:</strong></td>
                                    <td width="50%"><input type="text" name="txdTHPTP" id="txdTHPTP" size="10" value="$THKSU" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTHPTP',false,'dmy');" style="display:$Style2"></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"><strong>Tarikh Terima PTP</strong></div></td>
                                    <td><strong>:</strong></td>
                                    <td><input type="text" name="txdTTPTP" id="txdTTPTP" size="10" value="$TTKSU" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTTPTP',false,'dmy');" style="display:$Style2"></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"><strong>Tarikh Hantar ke Bahagian TUP</strong></div></td>
                                    <td><strong>:</strong></td>
                                    <td><input type="text" name="txdTHTUP" id="txdTHTUP" size="10" value="$THP" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTHTUP',false,'dmy');" style="display:$Style2"></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"><strong>Tarikh Mesyuarat Jemaah Menteri</strong></div></td>
                                    <td><strong>:</strong></td>
                                    <td><input type="text" name="txdTMJM" id="txdTMJM" size="10" value="$TMJ" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTMJM',false,'dmy');" style="display:$Style2"></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"><strong>Tarikh Terima Keputusan</strong></div></td>
                                    <td><strong>:</strong></td>
                                    <td><input type="text" name="txdTTK" id="txdTTK" size="10" value="$TK" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTTK',false,'dmy');" style="display:$Style2"></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"><strong>No. Memorandum</strong></div></td>
                                    <td><strong>:</strong></td>
                                    <td><input type="text" name="txtNoMemorandum" value="$noMemorandum" onkeyup="this.value=this.value.toUpperCase();" $mode /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"><strong>Keputusan</strong></div></td>
                                    <td><strong>:</strong></td>
                                    <td><input type="text" name="txtKeputusan" value="$statusKeputusan" onkeyup="this.value=this.value.toUpperCase();" $mode /></td>
                                  </tr>
                                  <tr>
                                    <td valign="top"><div align="right"><strong></strong></div></td>
                                    <td valign="top"><div align="left"><strong>Keterangan Kelulusan</strong></div></td>
                                    <td valign="top"><strong>:</strong></td>
                                    <td><label>
                  <textarea name="txtKeterangan" id="txtKeterangan" cols="41" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc >$tindakanLanjut</textarea>
                </label></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right">&nbsp;</div></td>
                                    <td><div align="left">&nbsp;</div></td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                  </tr>
                                  <tr>
                            <td colspan="4"><div align="center">
                              <input type="button" name="cmdKemaskini" id="cmdKemaskini" style="display:$Style1" value="Kemaskini" onclick="KemaskiniMJM()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdSimpan" id="cmdSimpan" style="display:$Style2" value="Simpan" onclick="SimpanMJM()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdBatal" id="cmdBatal" style="display:$Style2" $btnNameMJM onclick="BatalMJM()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()"></div></td>
                          </tr>
                              </table>
                              </fieldset>
                            </div></td>
                          </tr>                          
                    </table>
                    <input name="tabId" type="hidden" id="tabId" value="$selectedTab" />
                    <input type="hidden" name="idFail" value="$IdFail" />
                    <input type="hidden" name="idJemaahMenteri" value="$IdJemaahmenteri" />
                    <input type="hidden" name="idDokumenPerjanjian" value="" />
                    <input type="hidden" name="idUlasannilai" value="" />
                    <input type="hidden" name="idUlasanteknikal" value="$idUlasanteknikal" />
                    <input type="hidden" name="idUlasanKJP" value="$idUlasanKJP" />
                    <input type="hidden" name="idPemohon" value="$IdPemohon" />
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan" />
  					<input type="hidden" name="commander" value="$commander" />
                  	<input type="hidden" name="mode" value="$moded" />
                  	<input type="hidden" name="style1" value="$Style1" />
  					<input type="hidden" name="style2" value="$Style2" />
                    <input type="hidden" name="style3" value="$Style3" />
                </div>
              </div>
            </div>
         </td>
      </tr>
</table>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
//-->
function validatePoskod(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

function validateCurrency(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumericC(content);
		return;
	}
}

function RemoveNonNumericC( strString )
{
      var strValidCharacters = "1234567890.,";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

function Kembali() {
	document.${formName}.action = "";
	document.${formName}.commander.value = "";
	document.${formName}.submit();
}
/*pemohon javascript controller*/
function KemaskiniPemohon() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "pemohonkemaskini";
	document.${formName}.commander.value = "Pemohon";
	document.${formName}.submit();
}
function BatalPemohon() {
	document.${formName}.action = "";
	if(document.${formName}.idPemohon.value == ""){
		document.${formName}.txtNamaPemohon.value = "";
		document.${formName}.txtNoPemohon.value = "";
		document.${formName}.txtAlamat1.value = "";
		document.${formName}.txtAlamat2.value = "";
		document.${formName}.txtAlamat3.value = "";
		document.${formName}.txtPoskod.value = "";
		document.${formName}.socADaerah.value = "";
		document.${formName}.socANegeri.value = "";
		document.${formName}.txtNoTelefon.value = "";
		document.${formName}.txtNoFax.value = "";
	}
	document.${formName}.mode.value = "pemohonview";
	document.${formName}.commander.value = "Pemohon";
	document.${formName}.submit();
}
function SimpanPemohon() {
	if(document.${formName}.txtNamaPemohon.value == ""){
		alert('Sila masukkan " Nama / Syarikat " terlebih dahulu.');
  		document.${formName}.txtNamaPemohon.focus(); 
		return; 
	}
	if(document.${formName}.txtNoPemohon.value == ""){
		alert('Sila masukkan " No. KP / Syarikat " terlebih dahulu.');
  		document.${formName}.v.focus(); 
		return; 
	}
	if(document.${formName}.txtAlamat1.value == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
  		document.${formName}.txtAlamat1.focus(); 
		return; 
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');
  		document.${formName}.txtPoskod.focus(); 
		return; 
	}
	if(document.${formName}.socADaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.socADaerah.focus(); 
		return; 
	}
	if(document.${formName}.socANegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socANegeri.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	
	document.${formName}.action = "";
	document.${formName}.mode.value = "pemohonsimpan";
	document.${formName}.commander.value = "Pemohon";
	document.${formName}.submit();
}
function PemohonView() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "pemohonview";
	document.${formName}.commander.value = "Pemohon";
	document.${formName}.submit();
}
function doChangeNegeri() {
 	doAjaxCall${formName}("doChangeNegeri");
}

/*pemohon javascript controller end*/
/*ulasan javascript controller start*/
function KemaskiniUlasanKJP() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "ulasankjpkemaskini";
	document.${formName}.commander.value = "Ulasan";
	document.${formName}.submit();
}
function BatalUlasanKJP() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "ulasankjpview";
	document.${formName}.commander.value = "Ulasan";
	document.${formName}.submit();
}
function SimpanUlasanKJP() {
	if(document.${formName}.txdTarikhHantarKJP.value == ""){
		alert('Sila masukkan " Tarikh Hantar " terlebih dahulu.');
  		document.${formName}.txdTarikhHantarKJP.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhTerimaKJP.value == ""){
		alert('Sila masukkan " Tarikh Terima " terlebih dahulu.');
  		document.${formName}.txdTarikhTerimaKJP.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	
	document.${formName}.action = "";
	document.${formName}.mode.value = "ulasankjpsimpan";
	document.${formName}.commander.value = "Ulasan";
	document.${formName}.submit();
}
function UlasanView() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "ulasankjpview";
	document.${formName}.commander.value = "Ulasan";
	document.${formName}.submit();
	//UlasanJPPHView();
}
function UlasanJPPHView() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "ulasanjpphview";
	document.${formName}.commander.value = "Ulasan";
	document.${formName}.submit();
}
function BatalUlasanJPPH() {
	if(document.${formName}.idUlasannilai.value == ""){	
		document.${formName}.txtNoRujJPPH.value = "";
		document.${formName}.txdTarikhHantarJPPH.value = "";
		document.${formName}.txdTarikhTerimaJPPH.value = "";
		document.${formName}.socPegawai.value = "";
	}
	document.${formName}.socTahunNilaian.value = "";
	document.${formName}.txtNilaiTanah.value = "";
	document.${formName}.txtSyorBayaran.value = "";
	document.${formName}.txtKeteranganJPPH.value = "";
	document.${formName}.idUlasannilai.value = "";
	document.${formName}.cmdBatal.value = "Kosongkan";
	alert('testing Batal=');
}
function KemaskiniUlasanJPPH(idUlasannilai, idTN, NT, Syor, Keterangan) {
	document.${formName}.idUlasannilai.value = idUlasannilai;
	//document.${formName}.txtNoRujJPPH.value = "";
	//document.${formName}.txdTarikhHantarJPPH.value = "";
	//document.${formName}.txdTarikhTerimaJPPH.value = "";
	//document.${formName}.socPegawai.value = "";
	document.${formName}.socTahunNilaian.value = idTN;
	document.${formName}.txtNilaiTanah.value = NT;
	document.${formName}.txtSyorBayaran.value = Syor;
	document.${formName}.txtKeteranganJPPH.value = Keterangan;
	document.${formName}.cmdBatal.value = "Batal";
	alert('testing idUlasannilai='+document.${formName}.idUlasannilai.value);
}
function SimpanUlasanJPPH() {
	if(document.${formName}.idUlasanteknikal.value == ""){
		if(document.${formName}.txtNoRujJPPH.value == ""){
			alert('Sila masukkan " No. Ruj. JPPH " terlebih dahulu.');
			document.${formName}.txtNoRujJPPH.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhHantarJPPH.value == ""){
			alert('Sila masukkan " Tarikh Hantar " terlebih dahulu.');
			document.${formName}.txdTarikhHantarJPPH.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhTerimaJPPH.value == ""){
			alert('Sila masukkan " Tarikh Terima " terlebih dahulu.');
			document.${formName}.txdTarikhTerimaJPPH.focus(); 
			return; 
		}
		if(document.${formName}.socPegawai.value == ""){
			alert('Sila pilih " Pegawai " terlebih dahulu.');
			document.${formName}.socPegawai.focus(); 
			return; 
		}
	}
	if(document.${formName}.socTahunNilaian.value == ""){
		alert('Sila pilih " Tahun Nilaian " terlebih dahulu.');
  		document.${formName}.socTahunNilaian.focus(); 
		return; 
	}
	if(document.${formName}.txtNilaiTanah.value == ""){
		alert('Sila masukkan " Nilaian Tanah " terlebih dahulu.');
  		document.${formName}.txtNilaiTanah.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	
	document.${formName}.action = "";
	document.${formName}.mode.value = "ulasanjpphsimpan";
	document.${formName}.commander.value = "Ulasan";
	document.${formName}.submit();
}
/*ulasan javascript controller end*/
/*draf javascript controller start*/
function DrafView() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "drafview";
	document.${formName}.commander.value = "Draf";
	document.${formName}.submit();
}
function KemaskiniDraf(idDokumenPerjanjian,tHantar, tTerima, ulasan) {
	document.${formName}.idDokumenPerjanjian.value = idDokumenPerjanjian;
	document.${formName}.txdTarikhHantarDraf.value = tHantar;
	document.${formName}.txdTarikhTerimaDraf.value = tTerima;
	document.${formName}.txtUlasanDraf.value = ulasan;
	document.${formName}.cmdBatalDraf.value = "Batal";
}
function BatalDraf() {
	document.${formName}.idDokumenPerjanjian.value = "";
	document.${formName}.txdTarikhHantarDraf.value = "";
	document.${formName}.txdTarikhTerimaDraf.value = "";
	document.${formName}.txtUlasanDraf.value = "";
	document.${formName}.cmdBatalDraf.value = "Kosongkan";
	document.${formName}.idDokumenPerjanjian.focus();
}
function SimpanDraf() {
	if(document.${formName}.txdTarikhHantarDraf.value == ""){
		alert('Sila masukkan " Tarikh Hantar Draf " terlebih dahulu.');
  		document.${formName}.txdTarikhHantarDraf.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhTerimaDraf.value == ""){
		alert('Sila masukkan " Tarikh Terima Draf " terlebih dahulu.');
  		document.${formName}.txdTarikhTerimaDraf.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	
	document.${formName}.action = "";
	document.${formName}.mode.value = "drafsimpan";
	document.${formName}.commander.value = "Draf";
	document.${formName}.submit();
}
/*draf javascript controller end*/
/*MJM javascript controller start*/
function SimpanMJM() {
	/*if(document.${formName}.txdTHPTP.value == ""){
		alert('Sila masukkan " Tarikh Hantar PTP " terlebih dahulu.');
  		document.${formName}.txdTHPTP.focus(); 
		return; 
	}
	if(document.${formName}.txdTTPTP.value == ""){
		alert('Sila masukkan " Tarikh Terima PTP " terlebih dahulu.');
  		document.${formName}.txdTTPTP.focus(); 
		return; 
	}
	if(document.${formName}.txdTHTUP.value == ""){
		alert('Sila masukkan " Tarikh Hantar ke Bahagian TUP " terlebih dahulu.');
  		document.${formName}.txdTHTUP.focus(); 
		return; 
	}
	if(document.${formName}.txdTMJM.value == ""){
		alert('Sila masukkan " Tarikh Mesyuarat Jemaah Menteri " terlebih dahulu.');
  		document.${formName}.txdTMJM.focus(); 
		return; 
	}
	if(document.${formName}.txdTTK.value == ""){
		alert('Sila masukkan " Tarikh Terima Keputusan " terlebih dahulu.');
  		document.${formName}.txdTTK.focus(); 
		return; 
	}
	if(document.${formName}.txtNoMemorandum.value == ""){
		alert('Sila masukkan " No. Memorandum " terlebih dahulu.');
  		document.${formName}.txtNoMemorandum.focus(); 
		return; 
	}
	if(document.${formName}.txtKeputusan.value == ""){
		alert('Sila masukkan " Keputusan " terlebih dahulu.');
  		document.${formName}.txtKeputusan.focus(); 
		return; 
	}
	if(document.${formName}.txtKeterangan.value == ""){
		alert('Sila masukkan " Keterangan Kelulusan " terlebih dahulu.');
  		document.${formName}.txtKeterangan.focus(); 
		return; 
	}*/
	if ( !window.confirm("Anda Pasti?") ) return;
	
	document.${formName}.action = "";
	document.${formName}.mode.value = "mjmsimpan";
	document.${formName}.commander.value = "MJM";
	document.${formName}.submit();
}
function KemaskiniMJM() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "mjmkemaskini";
	document.${formName}.commander.value = "MJM";
	document.${formName}.submit();
}
function BatalMJM() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "mjmview";
	document.${formName}.commander.value = "MJM";
	document.${formName}.submit();
}
function MJMView() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "mjmview";
	document.${formName}.commander.value = "MJM";
	document.${formName}.submit();
}
/*MJM javascript controller end*/

function setSelected(tabId) {
    document.${formName}.tabId.value = tabId;
}

//document.${formName}.cmdKemaskini.style.display = document.${formName}.style1.value;
//document.${formName}.cmdSimpan.style.display = document.${formName}.style2.value;
//document.${formName}.cmdBatal.style.display = document.${formName}.style2.value;
//document.${formName}.cmdKembali.style.display = document.${formName}.style1.value;

</script>
