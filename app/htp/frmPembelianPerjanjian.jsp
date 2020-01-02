<script src="../../../testing/SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="../../../testing/SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<strong> Maklumat Gadaian </strong>
<br>
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
<legend>Maklumat Gadaian</legend>
<table width="100%">
	<tr>
		<td>
			#parse ("app/htp/frmPembelianInfo.jsp")
		</td>
    </tr>
    <tr>
    	<td>
        	<hr size="2" width="100%" align="center" />
        </td>
    </tr>
    <tr>
    	<td>
            <div id="TabbedPanels1" class="TabbedPanels">
              <ul class="TabbedPanelsTabGroup">
                <li class="TabbedPanelsTab" title="Semak" onclick="setSelected(0);HakmilikView()"><strong><font size="1">Senarai Semak</font></strong></li>
                <li class="TabbedPanelsTab" title="Draf" onclick="setSelected(1);PeguamView()"><strong><font size="1">Perjanjian Pembelian</font></strong></li>
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
                <form name="peguam" method="post">
                <table width="100%" border="0">
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<legend></legend>
                            	<table width="100%" border="0">
  									<tr>
                            			<td width="20%" align="right"><input type="checkbox" name="checkbox" id="checkbox" /></td>
                            			<td width="80%">Nama pemindah milik sama dengan nama pemilik tanah seperti perjanjian jual beli dan dokumen hakmilik.</td>
                          			</tr>
                          			<tr>
                          			  <td align="right"><input type="checkbox" name="checkbox" id="checkbox" /></td>
                          			  <td>Borang ditandatangani oleh pemindahmilik dan dimeteri cap mohor sekiranya pemindahmilik adalah perbadanan.</td>
                       			  </tr>
                          			<tr>
                          			  <td align="right"><input type="checkbox" name="checkbox" id="checkbox" /></td>
                          			  <td>Buti-butir penerimaan pindah milik adalah bertepatan dengan akta PTP.</td>
                       			  </tr>
                          			<tr>
                          			  <td align="right"><input type="checkbox" name="checkbox" id="checkbox" /></td>
                          			  <td>Maklumat tanah adalah sama dengan butir-butir dokumen hakmilik.</td>
                       			  </tr>
                          			<tr>
                          			  <td align="right"><input type="checkbox" name="checkbox" id="checkbox" /></td>
                          			  <td>Tandatangan pindahmilik disaksikan sekiranya pemindahmilik adalah pemilik individu.</td>
                       			  </tr>
                          			<tr>
                          			  <td align="right"><input type="checkbox" name="checkbox" id="checkbox" /></td>
                          			  <td>Borang 14A bertaip atau bertulis dakwat hitam.</td>
                       			  </tr>
                          			<tr>
                          			  <td align="right"><input type="checkbox" name="checkbox" id="checkbox" /></td>
                          			  <td>Sebarang pembetulan atau maklumat di atas Borang 14A di tandatangan ringkas.</td>
                       			  </tr>
                          			<tr>
                          			  <td align="right"><input type="checkbox" name="checkbox" id="checkbox" /></td>
                          			  <td>Hakmilik telah dikategori &quot;Bangunan&quot; atau &quot;Tiada&quot;.</td>
                       			  </tr>
                          			<tr>
                          			  <td align="right"><input type="checkbox" name="checkbox" id="checkbox" /></td>
                          			  <td>Kelulusan Pihak Berkuasa Negeri diperolehi sekiranya terdapat sekatan kepentingan.</td>
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
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">
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
                <form name="peguam" method="post">
                <table width="100%" border="0">
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<legend>Perjanjian Jual Beli</legend>
                            	<table width="100%" border="0">
  									<tr>
                            			<td width="50%" height="188" valign="top"><table width="100%" border="0">
                                          <tr>
                                            <th width="40%" align="right" scope="row">No Kontrak :</th>
                                            <td width="60%"><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
                                          </tr>
                                          <tr>
                                            <th align="right" scope="row">Tarikh perjanjian :</th>
                                            <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="15" value="$tSurat" readonly="readonly" />
                                            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
                                          </tr>
                                          <tr>
                                            <th align="right" scope="row">Tempoh Bulan :</th>
                                            <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
                                          </tr>
                                          <tr>
                                            <th align="right" scope="row">Tarikh Penyempurnaan 14A :</th>
                                            <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
                                          </tr>
                                          <tr>
                                            <th align="right" scope="row">Harga Tambahan :</th>
                                            <td><input type="text" name="txtNoFailUPT3" id="txtNoFailUPT3" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
                                          </tr>
                                          <tr>
                                            <th align="right" scope="row">Harga Beli RM :</th>
                                            <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
                                          </tr>
                                        </table></td>
                            			<td width="50%" valign="top"><table width="100%" border="0">
                                          <tr>
                                            <th width="40%" align="right" scope="row">Tarikh Tandatangan PTP :</th>
                                            <td width="60%"><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="15" value="$tSurat" readonly="readonly" />
                                            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
                                          </tr>
                                          <tr>
                                            <th align="right" scope="row">Tarikh Serah Bangunan :</th>
                                            <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="15" value="$tSurat" readonly="readonly" />
                                            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
                                          </tr>
                                          <tr>
                                            <th align="right" scope="row">Jumlah Hakmilik Tanah :</th>
                                            <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
                                          </tr>
                                          <tr>
                                            <th align="right" scope="row">Bil. Unit Bangunan :</th>
                                            <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
                                          </tr>
                                          <tr>
                                            <th align="right" scope="row">Nilai Tanah RM :</th>
                                            <td><input type="text" name="txtNoFailUPT2" id="txtNoFailUPT2" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
                                          </tr>
                                          <tr>
                                            <th align="right" scope="row">Nilai Bangunan RM :</th>
                                            <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
                                          </tr>
                                        </table></td>
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
                            <legend>Perjanjian Tambahan</legend>
                            <table width="100%" border="0">
  									<tr>
                           			  <td width="50%" height="122" valign="top"><table width="100%" border="0">
                                          
                                          <tr>
                                            <th width="40%" align="right" scope="row">Tarikh Perjanjian Tambahan :</th>
                                            <td width="60%"><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="15" value="$tSurat" readonly="readonly" />
                                            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
                                          </tr>
                                          <tr>
                                            <th align="right" scope="row">Sebab-sebab :</th>
                                            <td>$selectSebab</td>
                                          </tr>
                                          <tr>
                                            <th valign="top" align="right" scope="row">Alasan :</th>
                                            <td><label>
                                                  <textarea name="Tajuk" id="Tajuk" cols="30" rows="3" disabled="disabled">$tajuk</textarea>
                                            </label></td>
                                          </tr>
                                          
                                        </table>
                           			    </td>
                       			    <td width="50%" valign="top"><table width="100%" border="0">
                                          <tr>
                                            <th width="40%" align="right" scope="row">Tempoh Serah :</th>
                                            <td width="60%"><input type="text" name="txdTarikhSurKJP2" id="txdTarikhSurKJP2" value="$tSurat" /></td>
                                      </tr>
                                          <tr>
                                            <th align="right" scope="row">Tarikh Serah :</th>
                                            <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="15" value="$tSurat" readonly="readonly" />
                                            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
                                          </tr>                                          
                                      </table></td>
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
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">
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

function KemaskiniHakmilik() {
	document.hakmilik.action = "";
	document.hakmilik.mode.value = "kemaskiniHakmilik";
	document.hakmilik.command.value = "Hakmilik";
	document.hakmilik.submit();
}
function BatalHakmilik() {
	document.hakmilik.action = "";
	document.hakmilik.mode.value = "hakmilikview";
	document.hakmilik.command.value = "Hakmilik";
	document.hakmilik.submit();
}
function SimpanHakmilik() {
	if(document.hakmilik.txtNama.value == ""){
		alert('Sila masukkan " Nama " terlebih dahulu.');
  		document.hakmilik.txtNama.focus(); 
		return; 
	}
	if(document.hakmilik.txtAlamat1.value == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
  		document.hakmilik.txtAlamat1.focus(); 
		return; 
	}
	if(document.hakmilik.txtPoskod.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');
  		document.hakmilik.txtPoskod.focus(); 
		return; 
	}
	if(document.hakmilik.socADaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.hakmilik.socADaerah.focus(); 
		return; 
	}
	if(document.hakmilik.socANegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.hakmilik.socANegeri.focus(); 
		return; 
	}
	if(document.hakmilik.txtNoPerserahan.value == ""){
		alert('Sila masukkan " Gadaian Pendua No. Perserahan " terlebih dahulu.');
  		document.hakmilik.txtNoPerserahan.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	
	document.hakmilik.action = "";
	document.hakmilik.mode.value = "simpanHakmilik";
	document.hakmilik.command.value = "Hakmilik";
	document.hakmilik.submit();
}
function KembaliHakmilik() {
	document.hakmilik.action = "";
	document.hakmilik.command.value = "SenaraiPermohonan";
	document.hakmilik.submit();
}
function HakmilikView() {
	document.hakmilik.action = "";
	document.hakmilik.mode.value = "hakmilikview";
	document.hakmilik.command.value = "Hakmilik";
	document.hakmilik.submit();
}
function KemaskiniPeguam() {
	document.peguam.action = "";
	document.peguam.mode.value = "kemaskiniPeguam";
	document.peguam.command.value = "Hakmilik";
	document.peguam.submit();
}
function BatalPeguam() {
	document.peguam.action = "";
	document.peguam.mode.value = "peguamview";
	document.peguam.command.value = "Hakmilik";
	document.peguam.submit();
}
function SimpanPeguam() {
	if(document.peguam.txtNama.value == ""){
		alert('Sila masukkan " Nama " terlebih dahulu.');
  		document.hakmilik.txtNama.focus(); 
		return; 
	}
	if(document.peguam.txtAlamat1.value == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
  		document.hakmilik.txtAlamat1.focus(); 
		return; 
	}
	if(document.peguam.txtPoskod2.value == ""){
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
	document.peguam.mode.value = "simpanPeguam";
	document.peguam.command.value = "Hakmilik";
	document.peguam.submit();
}
function KembaliPeguam() {
	document.peguam.action = "";
	document.peguam.command.value = "SenaraiPermohonan";
	document.peguam.submit();
}

function PeguamView() {
	document.peguam.action = "";
	document.peguam.mode.value = "peguamview";
	document.peguam.command.value = "Hakmilik";
	document.peguam.submit();
}
function setSelected(tabId) {
    document.peguam.tabId.value = tabId;
	document.hakmilik.tabId.value = tabId;
}

document.peguam.cmdKemaskini.style.display = document.peguam.style1.value;
document.peguam.cmdSimpan.style.display = document.peguam.style2.value;
document.peguam.cmdBatal.style.display = document.peguam.style2.value;
document.peguam.cmdKembali.style.display = document.peguam.style1.value;

document.hakmilik.cmdKemaskini.style.display = document.hakmilik.style1.value;
document.hakmilik.cmdSimpan.style.display = document.hakmilik.style2.value;
document.hakmilik.cmdBatal.style.display = document.hakmilik.style2.value;
document.hakmilik.cmdKembali.style.display = document.hakmilik.style1.value;

</script>
