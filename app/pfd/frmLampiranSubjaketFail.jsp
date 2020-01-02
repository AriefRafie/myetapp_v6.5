<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
.style3 {font-size: 10px}
.style4 {
	font-style: italic;
	font-size: 9px;
}
.style5 {font-size: 9px}
.style6 {color: #0000FF}
.style7 {color: #000000}
-->
</style>

#set ($idSubjaket = "") 
#set ($tarikhMasukFail = "")
#set ($tarikhSubjaketFail = "")
#set ($noFailSubjaket = "")

#foreach ($subjaket in $Subjaket)

#set ($idSubjaket = $subjaket.id_Subjaket)
#set ($tarikhMasukFail = $subjaket.tarikh_Masuk_Fail)
#set ($tarikhSubjaketFail = $subjaket.tarikh_Subjaket_Fail)
#set ($noFailSubjaket = $subjaket.no_Fail_Subjaket)

#end
&nbsp;
<fieldset>
<legend>MAKLUMAT FAIL</legend>
<input name="command" type="hidden" value="" />
<input name="mode" type="hidden" value="$mode" />
<input name="idSubjaket" type="hidden" value="$idSubjaket" />
<input name="totalFail" type="hidden" value="$totalFail" />
<input type="hidden" name="idDokumen" id="idDokumen" />
<table width="100%">
   #foreach ($fail in $MaklumatFail)
   <input name="idFail" type="hidden" value="$fail.idFail" />
   <input name="noFail" type="hidden" value="$fail.noFail" />
  <tr>
    <td width="29%" scope="row"><div align="left">NO FAIL</div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <span class="style6">
      $fail.noFail.toUpperCase()</span></td>
  </tr>
  <tr>
    <td scope="row"><div align="left">TAJUK FAIL</div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td><span class="style6">$fail.tajukFail.toUpperCase()</span></td>
  </tr>
</table>
</fieldset>
&nbsp;
<fieldset>
<legend>MAKLUMAT LAMPIRAN</legend>
<table width="100%">
  <tr>
    <td width="29%" scope="row">No Rujukan Subjaket Fail</td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">$no_Rujukan_SubjaketFail</td>
  </tr>
  <tr>
    <td scope="row" valign="top">Jenis Dokumen</td>
    <td scope="row" valign="top"><div align="right">:</div></td>
    <td>$jenis_Dokumen</td>
  </tr>

  <tr>
    <td scope="row">Lampiran</td>
    <td scope="row"><div align="right">:</div></td>
    <td><label>
      <input name="txtLampiran" type="file" id="txtLampiran" size="50" />
    </label></td>
  </tr>
  <tr>
    <td colspan="3" scope="row">&nbsp;</td>
  </tr>

  <tr>
    <td colspan="3" align="center" scope="row">
      <label>
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
      </label>
      <label>
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliLampiran('$id_Dokumen')" />
      </label>    </td>
  </tr>
</table>
</fieldset>
&nbsp;
<fieldset>
  <legend>SENARAI LAMPIRAN</legend>
<table width="100%" border="0" >
  <tr class="table_header">
    <td width="1%" scope="row"><strong>No</strong></td>
    <td width="40%"><strong>Nama Fail</strong></td>
    <td width="20%"><strong>Jenis Fail</strong></td>
    <td width="2%">&nbsp;</td>
  </tr>
  #foreach ($lampiran in $SenaraiLampiran)
   #if ($lampiran.bil == '') 
  	#set ($row = 'row1')
  #elseif ($lampiran.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$lampiran.bil</td>
    #if ($lampiran.bil != '') 
    <td class="$row"><a href="javascript:papar_Lampiran('$lampiran.id_Lampiran')"><font color="blue">$lampiran.nama_Fail</font></a></td>
    #else
    <td class="$row">$lampiran.nama_Fail</td>
    #end
    <td class="$row">$lampiran.jenis_Mime</td>
    <td class="$row"><input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusLampiran('$lampiran.id_Lampiran')" /></td>
  </tr>
 #end
</table>
</fieldset>    
#end
<table width="108%" >
  <tr>
    <td align="right"><strong>CL-05-12</strong></td>
  </tr>
</table>

<script>
function batal(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action=SenaraiFail";
	document.${formName}.submit();
}
function kembali(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action=SenaraiFail";
	document.${formName}.submit();
}
function simpan(){
	
	if (document.${formName}.socPegawai.value == ""){
		alert('Sila masukkan " Nama Pegawai " terlebih dahulu.');
		document.${formName}.socPegawai.focus();
		return;
	} 
	if (document.${formName}.txtTarikhSubjaketFail.value == ""){
		alert('Sila masukkan " Tarikh Subjaket Fail " terlebih dahulu.');
		document.${formName}.txtTarikhSubjaketFail.focus();
		return;
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action=simpan";
	if (document.${formName}.idSubjaket.value == ""){
		document.${formName}.mode.value = "tambahBaru";
	}
	else{
		document.${formName}.mode.value = "kemaskiniSubjaket";
	}
	document.${formName}.submit();

}
function kemaskini(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action=kemaskini";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function papar_dokumen(id){
	
	
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmPaparDokumen?idDokumen="+id;
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();


}
function papar_subjaket(id){
	
	document.${formName}.idSubjaket.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action=paparSubjaket";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}

function tambah(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action=tambahSubjaket";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
	
	
}

function tambahLampiran(id){
	  
	document.${formName}.id_Subjaket.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action=tambahLampiran";
	document.${formName}.submit();

}
</script>