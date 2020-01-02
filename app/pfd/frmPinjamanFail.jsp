<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<fieldset>
<legend>Maklumat Fail</legend>
<table width="100%">
  <tr>
    <td width="29%">No Fail</td>
    <td width="1%">:</td>
    <td width="70%">$noFail</td>
  </tr>
  <tr>
    <td width="29%">Tajuk Fail</td>
    <td width="1%">:</td>
    <td width="70%">$tajukFail</td>
  </tr>
  <tr>
    <td>Tarikh Daftar Fail</td>
    <td>:</td>
    <td>$tarikhDaftar</td>
  </tr>
  <tr>
    <td>Status Fail</td>
    <td>:</td>
    <td>$statusFail</td>
  </tr>
</table>
</fieldset>
<fieldset><legend>Maklumat Pinjaman Fail</legend>
<table width="100%">
      <tr>
        <td width="2%">&nbsp;</td>
        <td width="27%">Nama Peminjam</td>
        <td width="1%">:</td>
        <td width="70%">$namaPeminjam.toUpperCase()</td>
      </tr>
      <tr>
        <td >&nbsp;</td>
        <td >Tarikh Permohonan Pinjaman Fail</td>
        <td >:</td>
        <td >$tarikhPrmhnnPinjaman</td>
      </tr>
      <tr>
        <td ><span class="style1">*</span></td>
        <td>Pinjaman Fail</td>
        <td>:</td>
        <td>
        #if($flagPinjamanFail == "1")
          <input name="sorPinjamanFail" type="radio" id="sorPinjamanFail" onclick="pinjamanFail('tr_biasa','tr_sdp')" value="1" checked="checked"/>Pinjaman Biasa 
        #else
         <input name="sorPinjamanFail" type="radio" id="sorPinjamanFail" onclick="pinjamanFail('tr_biasa','tr_sdp')" value="1"/>Pinjaman Biasa 
         #end
         #if($flagPinjamanFail == "2")
          <input name="sorPinjamanFail" type="radio" id="sorPinjamanFail" onclick="pinjamanFail('tr_biasa','tr_sdp')" value="2" checked="checked"/>Pinjaman SDP
         #else
          <input name="sorPinjamanFail" type="radio" id="sorPinjamanFail" onclick="pinjamanFail('tr_biasa','tr_sdp')" value="2"/>Pinjaman SDP
          #end
          </td>
      </tr>
     
      <tr id="tr_biasa">
        <td ><span class="style1">*</span></td>
        <td>Tempoh Pinjaman</td>
        <td>:</td>
        <td>Dari 
          <input name="txdDariTkhPinjaman" type="text" id="txdDariTkhPinjaman" value="$tempohBiasaDari" size="10"> 
          <a href="javascript:displayDatePicker('txdDariTkhPinjaman',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
          Hingga 
          <input name="txdHinggaTkhPinjaman" type="text" id="txdHinggaTkhPinjaman" value="$tempohBiasaHingga" size="10">
          <a href="javascript:displayDatePicker('txdHinggaTkhPinjaman',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>          </td>
      </tr>
      <tr id="tr_sdp">
        <td ><span class="style1">*</span></td>
        <td>Tempoh Pinjaman SDP</td>
        <td>:</td>
        <td>Dari 
          <input name="txdDariTkhSDP" type="text" id="txdDariTkhSDP" value="$tempohSdpDari" size="10">
          <a href="javascript:displayDatePicker('txdDariTkhSDP',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
 
          Hingga 
          <input name="txdHinggaTkhSDP" type="text" id="txdHinggaTkhSDP" value="$tempohSdpHingga" size="10">
                    <a href="javascript:displayDatePicker('txdHinggaTkhSDP',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>          </td>
      </tr>
      <tr>
        <td  valign="top"><span class="style1">*</span></td>
        <td valign="top">Tujuan Pinjaman</td>
        <td valign="top">:</td>
        <td><textarea name="txtTujuanPinjaman" id="txtTujuanPinjaman" cols="45" rows="5" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$tujuan_Pinjaman</textarea></td>
      </tr>
      <tr>
        <td >&nbsp;</td>
        <td>Tempoh Dilanjutkan</td>
        <td>:</td>
        <td>
        #if($flagLanjutTempoh == "1")
        <input name="sbcTempohDilanjutkan" type="checkbox" id="sbcTempohDilanjutkan" onclick="tempohDilanjutkan('tr_lanjutan',this)" value="1" checked="checked"/>
          Ya
         #else
         <input name="sbcTempohDilanjutkan" type="checkbox" id="sbcTempohDilanjutkan" onclick="tempohDilanjutkan('tr_lanjutan',this)" value="1"/>
          Ya
          #end
         </td>
  </tr>
      <tr id="tr_lanjutan">
        <td ><span class="style1">*</span></td>
        <td>Tempoh Lanjutan</td>
        <td>:</td>
        <td>Dari 
        <input name="txdDariTkhLanjutan" type="text" id="txdDariTkhLanjutan" value="$tempohLanjutDari" size="10" />
        <a href="javascript:displayDatePicker('txdDariTkhLanjutan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
 
        Hingga 
        <input name="txdHinggaTkhLanjutan" type="text" id="txdHinggaTkhLanjutan" value="$tempohLanjutHingga" size="10" />
        <a href="javascript:displayDatePicker('txdHinggaTkhLanjutan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>        </td>
      </tr>
   
      <tr>
        <td >&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td >&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>
        #if($mode == "baru")
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanPinjaman()">
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalBaru()">
        #elseif($mode == "papar")
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniPinjaman()" >
        <input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onclick="hantar()" />
        #elseif($mode == "kemaskini")
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updatePinjaman()">
        <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onclick="batalKemaskini()">
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()">
        #end
        </td>
      </tr>
    </table>


</fieldset>
<input name="idFail" type="hidden" value="$idFail" />
<input name="idPergerakanfail" type="hidden" value="$idPergerakanfail" />
<script>

window.onload = onload_sreen;

function onload_sreen()
{
	tempohDilanjutkan('tr_lanjutan',document.${formName}.sbcTempohDilanjutkan);
	pinjamanFail('tr_biasa','tr_sdp');

}


function pinjamanFail(id_biasa,id_sdp){
		if(document.${formName}.sorPinjamanFail[0].checked== true && document.${formName}.sorPinjamanFail[1].checked == false)
		{		
		document.getElementById(id_biasa).style.display="";
		document.getElementById(id_sdp).style.display="none";
		}
		else if(document.${formName}.sorPinjamanFail[1].checked == true && document.${formName}.sorPinjamanFail[0].checked == false)
		{		
		document.getElementById(id_biasa).style.display="none";
		document.getElementById(id_sdp).style.display="";
		}	
		
		else
		{
		document.getElementById(id_biasa).style.display="none";	
		document.getElementById(id_sdp).style.display="none";
		}
	
}

function tempohDilanjutkan(id_tr,cb){
       if(cb.checked == false)
		{
		
		document.getElementById(id_tr).style.display="none";
		}
		else
		{
		document.getElementById(id_tr).style.display="";	
		
		}

}
function simpanPinjaman(){

	if ( !window.confirm("Anda Pasti?") ) return;
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PinjamanFail&action=simpanPinjaman";
	document.${formName}.submit();
	
}
function kemaskiniPinjaman(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PinjamanFail&action=kemaskiniPinjaman";
	document.${formName}.submit();
	
}
function updatePinjaman(){

	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PinjamanFail&action=updatePinjaman";
	document.${formName}.submit();
	
}
function batalBaru(){

	document.getElementById('tr_biasa').style.display="none";
	document.getElementById('tr_sdp').style.display="none";
	document.${formName}.sorPinjamanFail[0].checked = "";
	document.${formName}.sorPinjamanFail[1].checked = "";
	document.${formName}.txdDariTkhPinjaman.value = "";
	document.${formName}.txdHinggaTkhPinjaman.value = "";
	document.${formName}.txdDariTkhSDP.value = "";
	document.${formName}.txdHinggaTkhSDP.value = "";
	document.${formName}.txtTujuanPinjaman.value = "";
	document.${formName}.sbcTempohDilanjutkan.checked = "";
	document.getElementById("tr_lanjutan").style.display="none";
	document.${formName}.txdDariTkhLanjutan.value = "";
	document.${formName}.txdHinggaTkhLanjutan.value = "";
	
	
	
}
function batalKemaskini(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PinjamanFail&action=tambahPinjaman";
	document.${formName}.submit();
	
}
function hantar(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PinjamanFail&action=hantar";
	document.${formName}.submit();
	
}
function kembali(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PinjamanFail&action= ";
	document.${formName}.submit();
	
}

</script>