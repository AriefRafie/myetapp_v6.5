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
<fieldset>
<legend>MAKLUMAT UPLOAD CUKAI NEGERI</legend>

<table width="100%">
	<!--<tr>
		<td>
			#parse ("app/htp/frmPembelianInfo.jsp")
		</td>
    </tr>-->
    <tr>
    	<td>
        	<hr size="2" width="100%" align="center" />
        </td>
    </tr>
    <tr>
    	<td>
            <div id="TabbedPanels1" class="TabbedPanels">
              <ul class="TabbedPanelsTabGroup">
                <li class="TabbedPanelsTab" title="MaklumatT" onclick="setSelected(0);MTView()"><strong><font size="1">UPLOAD</font></strong></li>
                <!-- 
                <li class="TabbedPanelsTab" title="MaklumatTB" onclick="setSelected(1);MTBView()"><strong><font size="1">Maklumat Tanah & Bangunan</font></strong></li>
                <li class="TabbedPanelsTab" title="Pemilik" onclick="setSelected(2);PemilikView()"><strong><font size="1">Nama Pemilik</font></strong></li>
                <li class="TabbedPanelsTab" title="Peguam" onclick="setSelected(3);PeguamView()"><strong><font size="1">Peguam Yang Dilantik</font></strong></li>
              	-->
              </ul>
              <div class="TabbedPanelsContentGroup">
                <div class="TabbedPanelsContent">
                #set ($IdPeguam = "")
                #set ($pNama = "")
                #set ($pAlamat1 = "")
                #set ($pAlamat2 = "")
                #set ($pAlamat3 = "")
                #set ($pPoskod = "")
                #set ($pNoTel = "")
                #set ($pNoFax = "")
                #foreach ( $peguam in $Peguam )
                	#set ($IdPeguam = $peguam.IdPeguam)
                	#set ($pNama = $peguam.Nama)
                    #set ($pAlamat1 = $peguam.Alamat1)
                    #set ($pAlamat2 = $peguam.Alamat2)
                    #set ($pAlamat3 = $peguam.Alamat3)
                    #set ($pPoskod = $peguam.Poskod)
                    #set ($pNoTel = $peguam.NoTel)
                    #set ($pNoFax = $peguam.NoFax)
                #end
                
                #set ($btnNamePeguam = "value='Kosongkan'")
				#if ($IdPeguam != "")
					#set ($btnNamePeguam = "value='Batal'")
				#end
               <!-- <form name="tanah" method="post">-->
                <table width="100%" border="0">
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<!--<legend>Maklumat Asas Tanah Jika Ada</legend>-->
                            	<table width="100%" border="0">
  									<tr>
                            			<td width="30%"><div align="right"><strong>Daerah :</strong></div></td>
                            			<td width="70%">$selectDaerah</td>
                          			</tr>
                          			<input type="hidden" name="idpermohonan" value="$IdPermohonan">
                          			
                          			<!--<tr>
                            			<td><div align="right"><strong>Bandar / Pekan / Mukim :</strong></div></td>
                            			<td>$selectMukim</td>
                          			</tr>-->
                          			<tr>
                            			<td><div align="right"><strong>Nama Fail:</strong></div></td>
                            			<td><input type="file" name="filecukai"> &nbsp;</td>
                  					</tr>
                          			<tr>
                            			<td><div align="right"></div></td>
                            			<td>&nbsp;</td>
                  					</tr>                          			
                          			<tr>
                            			<td><div align="right"><strong></strong></div></td>
                            			<td>&nbsp;</td>
                  					</tr>
                                    <tr>
                            			<td colspan="2"><div align="center">
                            			  <input type="button" name="cmdTambah" id="cmdTambah" value="Simpan" onclick="simpan('$IdPermohonan')"/>
                            			  <input type="reset" name="cmdsimpanfail" id="cmdsimpanfail" value="Batal" />
                            			</div></td>
                  					</tr>
                          			<tr>
                            			<td colspan="2"><div align="left">
                              				<table width="100%" border="0">
                 
                                		<tr class="table_header">
						      			    <td width="7%" align="center"><strong>Negeri</strong></td>
						        			<td width="6%" align="center"><strong>Daerah</strong></td>
						       				<td width="15%" align="center"><strong>No Hakmilik</strong></td>
						       				<td width="10%" align="center"><strong>No Lot</strong></td>
						        			<td width="14%" align="center"><strong>Lebihan</strong></td>
						        			<td width="17%" align="center"><strong>Denda</strong></td>
						        			<td width="14%" align="center"><b>Lain-lain</b></td>
						      			    <td width="17%" align="center"><strong>Cukai Semasa</strong></td>
						    			</tr>
                                                #set ($count = 0)
                                                #foreach ( $tanah in $Tanah )
                                                #set ($count = $count+1)
                                                  #set( $i = $velocityCount )
                                                  #if ( ($i % 2) != 1 )
                                                       #set( $row = "row2" )
                                                  #else
                                                       #set( $row = "row1" )
                                                  #end
                                				<tr>
                                  				  <td class="$row" scope="row">$tanah.HakKeterangan</td>
                                                  <td class="$row">$tanah.NoHakmilik</td>
                                                  <td class="$row">$tanah.LotKeterangan</td>
                                                  <td class="$row">$tanah.NoLot</td>
                                                  <td class="$row">$tanah.LuasKeterangan</td>
                                                  <td class="$row">$tanah.Luas</td>
                                                  <td class="$row">$tanah.NoPelan</td>
                                                  <td class="$row">$tanah.StatusTanah</td>
                                  				</tr>
                                                #end
                                              	#if ($count == 0)
                                              	<tr> 
                                                	<td colspan="11" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                                              	</tr>
                                              	#end
                                              </table>
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
                           <!-- <fieldset>
                            <legend>Ulasan KPTG</legend>
                            <table width="100%" border="0">
                              <tr>
                                <td width="30%" scope="row" align="right"><strong>Tarikh Hantar :</strong></td>
                                <td width="70%"><input type="text" name="txdTarikhSurKJP2" id="txdTarikhSurKJP2" size="15" value="$tSurat" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
                              </tr>
                              <tr style="display:none">
                                <td scope="row" align="right"><strong>Ulasan JKPTG :</strong></td>
                                <td>&nbsp;</td>
                              </tr>
                              <tr>
                                <td scope="row" align="right" valign="top"><strong>Catatan :</strong></td>
<td><label>
                                      <textarea name="Tajuk" id="Tajuk" cols="30" rows="3" disabled="disabled">$tajuk</textarea>
                                    </label></td>
                              </tr>
                            </table>

                            </fieldset> -->
                            </div></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td>&nbsp;</td>
                          </tr>
                          <!--<tr>
                            <td colspan="2"><div align="center">
                            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="KemaskiniPeguam()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="SimpanPeguam()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePeguam onclick="BatalPeguam()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="KembaliPeguam()"></div></td>
                          </tr> -->
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                    </table>
<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
                    <input type="hidden" name="idPeguam" value="$IdPeguam">
                  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
                	<input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="noFail" value="$NoFail">
  					<!--<input type="hidden" name="idPermohonan" value="$IdPermohonan">-->
  					<input type="hidden" name="command">
                    <input type="hidden" name="mode">
                    <input type="hidden" name="style1" value="$Style1">
  					<input type="hidden" name="style2" value="$Style2">
                  </form>
                </div>
                <div class="TabbedPanelsContent">
                #set ($IdPeguam = "")
                #set ($pNama = "")
                #set ($pAlamat1 = "")
                #set ($pAlamat2 = "")
                #set ($pAlamat3 = "")
                #set ($pPoskod = "")
                #set ($pNoTel = "")
                #set ($pNoFax = "")
                #foreach ( $peguam in $Peguam )
                	#set ($IdPeguam = $peguam.IdPeguam)
                	#set ($pNama = $peguam.Nama)
                    #set ($pAlamat1 = $peguam.Alamat1)
                    #set ($pAlamat2 = $peguam.Alamat2)
                    #set ($pAlamat3 = $peguam.Alamat3)
                    #set ($pPoskod = $peguam.Poskod)
                    #set ($pNoTel = $peguam.NoTel)
                    #set ($pNoFax = $peguam.NoFax)
                #end
                
                #set ($btnNamePeguam = "value='Kosongkan'")
				#if ($IdPeguam != "")
					#set ($btnNamePeguam = "value='Batal'")
				#end
                <form name="tbangun" method="post">
                <table width="100%" border="0">
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<legend>Maklumat Asas Tanah Jika Ada</legend>
                            	<table width="100%" border="0">
  									<tr>
                            			<td width="30%"><div align="right"><strong>Daerah :</strong></div></td>
                            			<td width="70%">$selectDaerah</td>
                          			</tr>
                          			<tr>
                            			<td><div align="right"><strong>Bandar / Pekan / Mukim :</strong></div></td>
                            			<td>$selectMukim</td>
                          			</tr>
                          			<tr>
                            			<td><div align="right"><strong></strong></div></td>
                            			<td>&nbsp;</td>
                  					</tr>
                                    <tr>
                            			<td colspan="2"><div align="left"><input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="" /></div></td>
                  					</tr>
                          			<tr>
                            			<td colspan="2"><div align="left"><strong></strong>
                              				<table width="100%" border="0">
                                				<tr class="table_header">
                                  					<td scope="col">Jenis Hakmilik</td>
                                 					<td scope="col">No. Hakmilik</td>
                                  					<td scope="col">Kod Lot</td>
                                  					<td scope="col">No Lot</td>
                                  					<td scope="col">Kod Luas</td>
                                  					<td scope="col">Luas</td>
                                  					<td scope="col">Luas (H)</td>
                                  					<td scope="col">No Pelan</td>
                                  					<td scope="col">Status</td>
                                  					<td scope="col">Tarikh Mula</td>
                                  					<td scope="col">Tarikh Luput</td>
                                				</tr>
                                                #set ($count = 0)
                                                #foreach ( $tanah in $Tanah )
                                                #set ($count = $count+1)
                                                  #set( $i = $velocityCount )
                                                  #if ( ($i % 2) != 1 )
                                                       #set( $row = "row2" )
                                                  #else
                                                       #set( $row = "row1" )
                                                  #end
                                				<tr>
                                  				  <td class="$row" scope="row">$tanah.HakKeterangan</td>
                                                  <td class="$row">$tanah.NoHakmilik</td>
                                                  <td class="$row">$tanah.LotKeterangan</td>
                                                  <td class="$row">$tanah.NoLot</td>
                                                  <td class="$row">$tanah.LuasKeterangan</td>
                                                  <td class="$row">$tanah.Luas</td>
                                                  <td class="$row">$tanah.</td>
                                                  <td class="$row">$tanah.NoPelan</td>
                                                  <td class="$row">$tanah.StatusTanah</td>
                                                  <td class="$row">$tanah.TarikhMula</td>
                                                  <td class="$row">$tanah.TarikhLuput</td>
                                				</tr>
                                                #end
                                              	#if ($count == 0)
                                              	<tr> 
                                                	<td colspan="11" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                                              	</tr>
                                              	#end
                                              </table>
                                            </div></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right"><strong>Jumlah Bangunan :</strong></div></td>
                                            <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right"><strong>Jumlah Petak :</strong></div></td>
                                            <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
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
                            <fieldset>
                            <legend>Ulasan KPTG</legend>
                            <table width="100%" border="0">
                              <tr>
                                <td width="30%" scope="row" align="right"><strong>Tarikh Hantar :</strong></td>
                                <td width="70%"><input type="text" name="txdTarikhSurKJP2" id="txdTarikhSurKJP2" size="15" value="$tSurat" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
                              </tr>
                              <tr style="display:none">
                                <td scope="row" align="right"><strong>Ulasan JKPTG :</strong></td>
                                <td>&nbsp;</td>
                              </tr>
                              <tr>
                                <td scope="row" align="right" valign="top"><strong>Catatan :</strong></td>
<td><label>
                                      <textarea name="Tajuk" id="Tajuk" cols="30" rows="3" disabled="disabled">$tajuk</textarea>
                                    </label></td>
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
                            <td colspan="2"><div align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="KemaskiniPeguam()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="SimpanPeguam()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePeguam onclick="BatalPeguam()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="KembaliPeguam()"></div></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                    </table>
<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
                    <input type="hidden" name="idPeguam" value="$IdPeguam">
                  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
                	<input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="noFail" value="$NoFail">
  					<!--<input type="hidden" name="idPermohonan" value="$IdPermohonan">-->
  					<input type="hidden" name="command">
                    <input type="hidden" name="mode">
                    <input type="hidden" name="style1" value="$Style1">
  					<input type="hidden" name="style2" value="$Style2">
                  </form>
                </div>
                <div class="TabbedPanelsContent">
                #set ($IdPihakberkepentingan = "")
                #set ($IdBebanan = "")
               	#set ($NamaPemaju = "")
                #set ($NoRuj = "")
                #set ($Alamat1 = "")
                #set ($Alamat2 = "")
                #set ($Alamat3 = "")
                #set ($Poskod = "")
                #set ($NoTel = "")
                #set ($NoFax = "")
                #foreach ( $pemilik in $Pemilik )
                	#set ($IdPihakberkepentingan = $pemilik.IdPihakberkepentingan)
                    #set ($IdBebanan = $pemilik.IdBebanan)
                	#set ($NamaPemaju = $pemilik.Nama)
                    #set ($NoRuj = $pemilik.noRujukan)
                    #set ($Alamat1 = $pemilik.Alamat1)
                    #set ($Alamat2 = $pemilik.Alamat2)
                    #set ($Alamat3 = $pemilik.Alamat3)
                    #set ($Poskod = $pemilik.Poskod)
                    #set ($NoTel = $pemilik.NoTel)
                    #set ($NoFax = $pemilik.NoFax)
                #end
                
                #set ($idNegeri = "")
                #foreach ($negeri in $Info)
                	#set ($idNegeri = $negeri.idNegeri)
                #end
                
                #set ($btnNamePemilik = "value='Kosongkan'")
				#if ($IdPihakberkepentingan != "")
					#set ($btnNamePemilik = "value='Batal'")
				#end
                <form name="pemilik" method="post">
                <fieldset>
					<legend>Maklumat Pemilik</legend>
                <table width="100%" border="0">
                          <tr>
                            <td width="35%"><div align="right"><strong><font color="#FF0000">*</font>Nama Pemaju / Pemilik :</strong></div></td>
                            <td width="65%"><input type="text" name="txtNamaPemaju" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80" value="$NamaPemaju" $mode/></td>
                          </tr>
                          <tr>
                            <td width="35%"><div align="right"><strong><font color="#FF0000">*</font>Jenis Nopb :</strong></div></td>
                            <td width="65%">$selectJenisNoPB</td>
                          </tr>
                          <tr style="display:none">
                            <td width="35%"><div align="right"><strong><font color="#FF0000">*</font>Nopb :</strong></div></td>
                            <td width="65%">$selectNoPB</td>
                          </tr>
                          <tr>
                            <td width="35%"><div align="right"><strong><font color="#FF0000">*</font>No. Ruj Pemaju :</strong></div></td>
                            <td width="65%"><input type="text" name="txtNoRuj" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="80" value="$NoRuj" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong><font color="#FF0000">*</font>Alamat :</strong></div></td>
                            <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat1" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat2" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat3" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong><font color="#FF0000">*</font>Poskod :</strong></div></td>
                            <td><input type="text" name="txtPoskod" size="15" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$Poskod" $mode /></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong><font color="#FF0000">*</font>Daerah :</strong></div></td>
                            <td>$selectADaerah</td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong><font color="#FF0000">*</font>Negeri :</strong></div></td>
                            <td>$selectANegeri</td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong>No. Telefon :</strong></div></td>
                            <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$NoTel" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong>No. Fax :</strong></div></td>
                            <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$NoFax" $mode/></td>
                          </tr>
                          
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="2"><div align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="KemaskiniHakmilik()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="SimpanHakmilik()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="BatalHakmilik()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="KembaliHakmilik()"></div></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>                          
                    </table>
                    </fieldset>
              <input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
                    <input type="hidden" name="idBebanan" value="$IdBebanan">
                    <input type="hidden" name="idPihakberkepentingan" value="$IdPihakberkepentingan">
                    <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
                    <input type="hidden" name="idFail" value="$IdFail">
  					<!--<input type="hidden" name="idPermohonan" value="$IdPermohonan">-->
  					<input type="hidden" name="command">
                  	<input type="hidden" name="mode">
                  	<input type="hidden" name="style1" value="$Style1">
  					<input type="hidden" name="style2" value="$Style2">
                  </form>
                </div>
                <div class="TabbedPanelsContent">
                #set ($IdPeguam = "")
               	#set ($NamaPeguam = "")
                #set ($KodPeguam = "")
                #set ($Alamat1 = "")
                #set ($Alamat2 = "")
                #set ($Alamat3 = "")
                #set ($Poskod = "")
                #set ($NoTel = "")
                #set ($NoFax = "")
                #foreach ( $peguam in $Peguam )
                    #set ($IdPeguam = $peguam.IdPeguam)
                	#set ($NamaPeguam = $peguam.NamaPeguam)
                    #set ($KodPeguam = $peguam.noRujukan)
                    #set ($Alamat1 = $peguam.Alamat1)
                    #set ($Alamat2 = $peguam.Alamat2)
                    #set ($Alamat3 = $peguam.Alamat3)
                    #set ($Poskod = $peguam.Poskod)
                    #set ($NoTel = $peguam.NoTel)
                    #set ($NoFax = $peguam.NoFax)
                #end
                
                #set ($idNegeri = "")
                #foreach ($negeri in $Info)
                	#set ($idNegeri = $negeri.idNegeri)
                #end
                
                #set ($btnNamePemilik = "value='Kosongkan'")
				#if ($IdPeguam != "")
					#set ($btnNamePemilik = "value='Batal'")
				#end
                <form name="peguam" method="post">
                <table width="100%" border="0">                          
                          <tr>
                            <td colspan="2"><div align="left">
                            <fieldset>
							<legend>Maklumat Peguam Yang Dilantik</legend>
                              <table width="100%" border="0">
                                <tr>
                                    <td width="35%"><div align="right"><strong><font color="#FF0000">*</font>Nama Peguam :</strong></div></td>
                                    <td width="65%"><input type="text" name="txtNamaPeguam" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80" value="$NamaPeguam" $mode/></td>
                                  </tr>
                                  <tr>
                                    <td width="35%"><div align="right"><strong>Kod Peguam :</strong></div></td>
                                    <td width="65%"><input type="text" name="txtKodPeguam" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="50" value="$KodPeguam" $mode/></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong><font color="#FF0000">*</font>Alamat :</strong></div></td>
                                    <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat1" $mode/></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat2" $mode/></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat3" $mode/></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong><font color="#FF0000">*</font>Poskod :</strong></div></td>
                                    <td><input type="text" name="txtPoskod" size="15" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$Poskod" $mode /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong><font color="#FF0000">*</font>Daerah :</strong></div></td>
                                    <td>$selectBDaerah</td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong><font color="#FF0000">*</font>Negeri :</strong></div></td>
                                    <td>$selectBNegeri</td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong>No. Telefon :</strong></div></td>
                                    <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$NoTel" $mode/></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong>No. Fax :</strong></div></td>
                                    <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$NoFax" $mode/></td>
                                </tr>
                              </table>
                              </fieldset>
                            </div></td>
                          </tr>
                          <tr>
                            <td colspan="2"><div align="center">
                            	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="KemaskiniHakmilik()">&nbsp;&nbsp;
                            	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="SimpanHakmilik()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="BatalHakmilik()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="KembaliHakmilik()"></div></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                    </table>
                    <input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
                    <input type="hidden" name="idPeguam" value="$IdPeguam">
                    <input type="hidden" name="idFail" value="$IdFail">
  					<!--<input type="hidden" name="idpermohonan" value="$IdPermohonan">-->
  					<input type="hidden" name="command">
                  	<input type="hidden" name="mode">
                  	<input type="hidden" name="style1" value="$Style1">
  					<input type="hidden" name="style2" value="$Style2">
                  </form>
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

/*pemilik javascript controller*/
function KemaskiniPemilik() {
	document.pemilik.action = "";
	document.pemilik.mode.value = "kemaskinipemilik";
	document.pemilik.command.value = "Maklumat";
	document.pemilik.submit();
}
function BatalPemilik() {
	document.pemilik.action = "";
	document.pemilik.mode.value = "pemilikview";
	document.pemilik.command.value = "Maklumat";
	document.pemilik.submit();
}
function SimpanPemilik() {
	if(document.pemilik.txtNamaPemaju.value == ""){
		alert('Sila masukkan " Nama Pemaju / Pemilik " terlebih dahulu.');
  		document.pemilik.txtNamaPemaju.focus(); 
		return; 
	}
	if(document.pemilik.socJenisNoPB.value == ""){
		alert('Sila pilih " Jenis Nopb " terlebih dahulu.');
  		document.pemilik.socJenisNoPB.focus(); 
		return; 
	}
	if(document.pemilik.txtNoRuj.value == ""){
		alert('Sila masukkan " No. Rujukan Pemaju " terlebih dahulu.');
  		document.pemilik.txtNoRuj.focus(); 
		return; 
	}
	if(document.pemilik.txtAlamat1.value == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
  		document.pemilik.txtAlamat1.focus(); 
		return; 
	}
	if(document.pemilik.txtPoskod.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');
  		document.pemilik.txtPoskod.focus(); 
		return; 
	}
	if(document.pemilik.socADaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.pemilik.socADaerah.focus(); 
		return; 
	}
	if(document.pemilik.socANegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.pemilik.socANegeri.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	
	document.pemilik.action = "";
	document.pemilik.mode.value = "simpanpemilik";
	document.pemilik.command.value = "Maklumat";
	document.pemilik.submit();
}
function KembaliPemilik() {
	document.pemilik.action = "";
	document.pemilik.command.value = "SenaraiPermohonan";
	document.pemilik.submit();
}
function PemilikView() {
	document.pemilik.action = "";
	document.pemilik.mode.value = "pemilikview";
	document.pemilik.command.value = "Maklumat";
	document.pemilik.submit();
}
/*pemilik javascript controller end*/
/*peguam javascript controller*/
function KemaskiniPeguam() {
	document.peguam.action = "";
	document.peguam.mode.value = "kemaskinipeguam";
	document.peguam.command.value = "Maklumat";
	document.peguam.submit();
}
function BatalPeguam() {
	document.peguam.action = "";
	document.peguam.mode.value = "peguamview";
	document.peguam.command.value = "Maklumat";
	document.peguam.submit();
}
function SimpanPeguam() {
	if(document.peguam.txtNamaPeguam.value == ""){
		alert('Sila masukkan " Nama Peguam " terlebih dahulu.');
  		document.hakmilik.txtNamaPeguam.focus(); 
		return; 
	}
	if(document.peguam.txtKodPeguam.value == ""){
		alert('Sila masukkan " Kod Peguam " terlebih dahulu.');
  		document.hakmilik.txtKodPeguam.focus(); 
		return; 
	}
	if(document.peguam.txtAlamat1.value == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
  		document.hakmilik.txtAlamat1.focus(); 
		return; 
	}
	if(document.peguam.txtPoskod.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');
  		document.hakmilik.txtPoskod2.focus(); 
		return; 
	}
	if(document.peguam.socBDaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.hakmilik.socBDaerah.focus(); 
		return; 
	}
	if(document.peguam.socBNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.hakmilik.socBNegeri.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	
	document.peguam.action = "";
	document.peguam.mode.value = "simpanpeguam";
	document.peguam.command.value = "Maklumat";
	document.peguam.submit();
}
function KembaliPeguam() {
	document.peguam.action = "";
	document.peguam.command.value = "";
	document.peguam.submit();
}

function PeguamView() {
	document.peguam.action = "";
	document.peguam.mode.value = "peguamview";
	document.peguam.command.value = "Maklumat";
	document.peguam.submit();
}
/*peguam javascript controller end*/
/*tanah javascript controller*/
function KemaskiniTanah() {
	document.tanah.action = "";
	document.tanah.mode.value = "tanahkemaskini";
	document.tanah.command.value = "Maklumat";
	document.tanah.submit();
}
function BatalTanah() {
	document.tanah.action = "";
	document.tanah.mode.value = "tanahview";
	document.tanah.command.value = "Maklumat";
	document.tanah.submit();
}

function simpan(i) {
	if(document.${formName}.iddaerah.value == ""){
		alert('Sila pilih " Daerah" terlebih dahulu.');
  		document.${formName}.iddaerah.focus(); 
		return; 
	}	
		
	document.${formName}.action = "";
	document.${formName}.idpermohonan.value = i;
	document.${formName}.command.value = "simpancukainegeri";
	document.${formName}.submit();
}

function KembaliTanah() {
	document.tanah.action = "";
	document.tanah.command.value = "SenaraiPermohonan";
	document.tanah.submit();
}
function TanahView() {
	document.tanah.action = "";
	document.tanah.mode.value = "tanahview";
	document.tanah.command.value = "Maklumat";
	document.tanah.submit();
}
function TanahTambah(idPermohonan,command,mode) {
	var url = "../c/${securityToken}/ekptg.view.htp.PembelianPopup?idPermohonan="+idPermohonan+"&command="+command+"&mode="+mode;
	var hWnd = window.open(url,'printuser','width=1024,height=600, resizable=yes,scrollbars=yes');
}
/*tanah javascript controller end*/

function setSelected(tabId) {
    document.peguam.tabId.value = tabId;
	document.pemilik.tabId.value = tabId;
	document.tanah.tabId.value = tabId;
}


function simpan(tabId) {
	document.${formName}.command.value = "uploadFile";
	document.${formName}.submit();		
}

document.peguam.cmdKemaskini.style.display = document.peguam.style1.value;
document.peguam.cmdSimpan.style.display = document.peguam.style2.value;
document.peguam.cmdBatal.style.display = document.peguam.style2.value;
document.peguam.cmdKembali.style.display = document.peguam.style1.value;

document.pemilik.cmdKemaskini.style.display = document.pemilik.style1.value;
document.pemilik.cmdSimpan.style.display = document.pemilik.style2.value;
document.pemilik.cmdBatal.style.display = document.pemilik.style2.value;
document.pemilik.cmdKembali.style.display = document.pemilik.style1.value;

document.tanah.cmdKemaskini.style.display = document.tanah.style1.value;
document.tanah.cmdSimpan.style.display = document.tanah.style2.value;
document.tanah.cmdBatal.style.display = document.tanah.style2.value;
document.tanah.cmdKembali.style.display = document.tanah.style1.value;

</script>
