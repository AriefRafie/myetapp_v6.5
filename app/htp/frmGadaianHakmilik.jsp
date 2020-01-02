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
			#parse ("app/htp/frmGadaianInfo.jsp")
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
                <li class="TabbedPanelsTab" tabindex="1" title="Pemilik" onclick="setSelected(0);HakmilikView()"><strong><font size="1">Maklumat Pemilik</font></strong></li>
                <li class="TabbedPanelsTab" tabindex="0" title="Peguam" onclick="setSelected(1);PeguamView()"><strong><font size="1">Maklumat Peguam</font></strong></li>
              </ul>
              <div class="TabbedPanelsContentGroup">
                <div class="TabbedPanelsContent">
                #set ($IdPihakberkepentingan = "")
                #set ($IdBebanan = "")
               	#set ($Nama = "")
                #set ($Alamat1 = "")
                #set ($Alamat2 = "")
                #set ($Alamat3 = "")
                #set ($Poskod = "")
                #set ($NoTel = "")
                #set ($NoFax = "")
                #set ($NoPerserahan = "")
                #foreach ( $hakmilik in $Hakmilik )
                	#set ($IdPihakberkepentingan = $hakmilik.IdPihakberkepentingan)
                    #set ($IdBebanan = $hakmilik.IdBebanan)
                	#set ($Nama = $hakmilik.Nama)
                    #set ($Alamat1 = $hakmilik.Alamat1)
                    #set ($Alamat2 = $hakmilik.Alamat2)
                    #set ($Alamat3 = $hakmilik.Alamat3)
                    #set ($Poskod = $hakmilik.Poskod)
                    #set ($NoTel = $hakmilik.NoTel)
                    #set ($NoFax = $hakmilik.NoFax)
                    #set ($NoPerserahan = $hakmilik.NoPerserahan)
                #end
                
                #set ($idNegeri = "")
                #foreach ($negeri in $Info)
                	#set ($idNegeri = $negeri.idNegeri)
                #end
                
                #set ($btnNamePemilik = "value='Kosongkan'")
				#if ($IdPihakberkepentingan != "")
					#set ($btnNamePemilik = "value='Batal'")
				#end
                <form name="hakmilik" method="post">
                <table width="100%" border="0">
                          <tr>
                            <td width="35%"><div align="right"><strong><font color="#FF0000">*</font>Nama :</strong></div></td>
                            <td width="65%"><input type="text" name="txtNama" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80" value="$Nama" $mode/></td>
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
                            <td><div align="right"><strong><font color="#FF0000">*</font>Gadaian Pendua No. Perserahan :</strong></div></td>
                            <td><input type="text" name="txtNoPerserahan" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="50" value="$NoPerserahan" $mode/></td>
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
                    <input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
                    <input type="hidden" name="idBebanan" value="$IdBebanan">
                    <input type="hidden" name="idPihakberkepentingan" value="$IdPihakberkepentingan">
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
                            <td width="35%"><div align="right"><strong><font color="#FF0000">*</font>Nama :</strong></div></td>
                            <td width="65%"><input type="text" name="txtNama" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80"  value="$pNama" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong><font color="#FF0000">*</font>Alamat :</strong></div></td>
                            <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$pAlamat1" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$pAlamat2" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$pAlamat3" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong><font color="#FF0000">*</font>Poskod :</strong></div></td>
                            <td><input type="text" name="txtPoskod2" size="15" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$pPoskod" $mode/></td>
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
                            <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$pNoTel" $mode/></td>
                          </tr>
                          <tr>
                            <td><div align="right"><strong>No. Fax :</strong></div></td>
                            <td><input type="text" name="txtNoFax2" size="20" maxlength="14" value="$pNoFax" $mode/></td>
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
