<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>

#set($saizTxtPerkara="1000")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idNegeriPemohon" type="hidden" id="idNegeriPemohon" value="$idNegeriPemohon"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan"/>
  <input name="idSuburusanPmhn" type="hidden" id="idSuburusanPmhn" />
  <input name="hitButton" type="hidden" id="hitButton"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">No. Rujukan <em>Online</em></td>
          <td width="1%" >:</td>
          <td width="70%"><strong>$beanMaklumatPermohonan.noPermohonan</strong>
            <input name="idPermohonan" type="hidden" value="$beanMaklumatPermohonan.idPermohonan" />
            <input name="idPemohon" type="hidden" value="$beanMaklumatPermohonan.idPemohon" /></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Urusan</td>
          <td>:</td>
          <td> PENAWARAN </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT TANAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Pegangan Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%"> #if ($mode == 'new')
            <!--penawaran-->
           
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onBlur="doChangePeganganHakmilik();" />
             <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onClick="pilihTanah('$idKementerianPmhn','$idAgensiPmhn')">   
      
            #else
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled">
            #end
            <input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi" />
            <span class="style1">$errorPeganganHakmilik</span> </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noLot</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.luasLot</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Hakmilik</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noHakmilik</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Warta</td>
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
          <td>$beanMaklumatTanah.mukim</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Daerah</td>
          <td>:</td>
          <td>$beanMaklumatTanah.daerah</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatTanah.negeri
            <input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$beanMaklumatTanah.idNegeriTanah" />
            <input type="hidden" name="kodNegeriTanah" id="kodNegeriTanah" value="$beanMaklumatTanah.kodNegeriTanah" />          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$beanMaklumatTanah.kementerian
           
            <input type="hidden" name="kodKementerian" id="kodKementerian" value="$beanMaklumatTanah.kodKementerian" />          </td>
        </tr>
        <tr>
          <td height="31">&nbsp;</td>
          <td>Agensi</td>
          <td>:</td>
          <td>$beanMaklumatTanah.agensi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kegunaan Tanah</td>
          <td>:</td>
          <td>$beanMaklumatTanah.kegunaanTanah</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'new')
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Seterusnya" onClick="daftarBaru()"/>
      <input type="button" name="cmdKembali" id="cmdKembali" value="Batal" onClick="kembali()"/>
      #end
       </td>
  </tr>
</table>
<script>
function pilihTanah(idKementerian,idAgensi) {
	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmPNWPopupSenaraiTanahOnlineView?idKementerian="+idKementerian+"&idAgensi="+idAgensi;
	
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensi) {
	document.${formName}.idHakmilikAgensi.value = idHakmilikAgensi;
	doAjaxCall${formName}("doChangeMaklumatTanah");
}
function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}
	function daftarBaru() {
		
		if(document.${formName}.idHakmilikAgensi.value == ""){
			alert('Sila pilih Pegangan Hakmilik.');
			return; 
		}
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionOnline.value = "daftarBaru";
			return;
		}
		
		document.${formName}.actionOnline.value = "seterusnya";
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "doDaftarBaru";
		document.${formName}.submit();
	}
function kembali() {	
	document.${formName}.actionOnline.value = "";
	document.${formName}.submit();
}
</script>
