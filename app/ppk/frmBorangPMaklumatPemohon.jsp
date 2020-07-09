<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<!--#if ($IdAlert == 1)
<script type="text/javascript">
	alert("Maaf, permohonan anda tidak dapat diteruskan. Anda dikehendaki mengisi 'Permohonan Online Borang A Sek 8' terlebih dahulu.")
</script>
#end
-->
<form name="f1" method="post">




#if ($duplicate == "yes")
<!--
:::::::::::::::::::::::::::::::::::::
<br>
POPSimati :: $!POPSimati <br>
POPNofail ::	$!POPNofail <br>
POPNamaPejabat :: 	$!POPNamaPejabat <br>
POPDaerahMohon ::	$!POPDaerahMohon <br>
POPNoOnline ::	$!POPNoOnline <br>
id_status_daftar ::	$!id_status_daftar <br>
-->

<script type="text/javascript">

		var simati = '$!POPSimati';
		var nofail = '$!POPNofail';
		var pejabat = '$!POPNamaPejabat';
		var daerah = '$!POPDaerahMohon';
		var nofailonline = '$!POPNoOnline';
		var id_status_daftar = '$!id_status_daftar';
		var flag_error = '$!error_level';
		var selesai = "";
		
		//alert(nofail);
		//alert(id_status_daftar);
		if(id_status_daftar == "21")
		{
		selesai = "yes";
		}
		
		if(flag_error == "1")
		{
		alert("Harap Maaf, Tiada maklumat permohonan awal bagi si mati atau no fail yang dimasukkan. Tuan/Puan tidak boleh meneruskan permohonan Seksyen 17. Sila muat turun Borang P untuk permohonan secara manual")
		}
		else if(flag_error == "2")
		{
		
		if(nofail != null && nofail != "" && id_status_daftar != "150" && id_status_daftar != "160" && id_status_daftar != "171" && selesai != "yes"){
		alert("Permohonan untuk simati "+simati+" telah wujud dengan No. fail Permohonan "+nofail+". Permohonan telah dibuat di "+pejabat+"("+daerah+"). Tuan/Puan tidak dapat meneruskan Permohonan Seksyen 17 Online kerana fail awal belum selesai sepenuhnya. Sila berhubung dengan Pejabat Pusaka berkenaan.");
		}		
		else if((nofail != "" || nofail != null) && selesai == "yes"){		
		alert("Permohonan untuk simati  "+simati+" telah wujud dengan No. fail Permohonan "+nofail+". Permohonan telah dibuat di "+pejabat+"("+daerah+").  Tuan/Puan tidak dapat meneruskan Permohonan Seksyen 17 Online kerana status masih belum selesai. Sila berhubung dengan Pejabat Pusaka berkenaan.");
		}
		else if(id_status_daftar == "171"){
		alert("Permohonan untuk simati "+simati+" telah wujud dengan No. Rujukan Online "+nofailonline+". Permohonan telah dihantar ke "+pejabat+"("+daerah+").  Tuan/Puan tidak dapat meneruskan Permohonan Seksyen 17 Online. Sila berhubung dengan Pejabat Pusaka berkenaan.");		
		}		
		else if(id_status_daftar == "160"){
		alert("Permohonan untuk simati "+simati+" telah wujud di Permohonan Online Seksyen 17. Sila semak Maklumat Permohonan di bawah menu Deraf Permohonan PPK dan lengkapkan maklumat permohonan berkenaan.");		
		}
		else if(id_status_daftar == "150"){
		alert("Permohonan untuk simati "+simati+" telah wujud di Permohonan Online Seksyen 8. Sila semak Maklumat Permohonan di bawah menu Deraf Permohonan PPK dan lengkapkan maklumat permohonan berkenaan.");		
		}
		}
	
</script>


#end



<fieldset>
<legend>Maklumat Simati</legend>
<div id="cetakan_borang_p"></div>
<script>
parent.document.getElementById("cetakan_borang_p").innerHTML="<div class=\"warning_online_ppk\"><b><blink>*</blink> Sila Masukkan Salah Satu MyID Simati atau No. Fail Permohonan Awal untuk Tujuan<br>&nbsp;&nbsp;&nbsp;Semakan bagi Membenarkan Pemohon Membuat Permohonan Baru Sekyen 17.</b></div>";
</script>
<table border="0" align="center" width="100%">
    <tbody>
        <tr> 
        <td width="25%" height="24" scope="row" align="right">MyID Baru : </td>
        <td width="75%"><input name="txtNoKPBaru1a" id="txtNoKPBaru1a" style="width: 50px;" 
        type="text" value="$!txtNoKPBaru1a" size="7" maxlength="6" 
        onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2a')"/>-<input name="txtNoKPBaru2a" id="txtNoKPBaru2a" style="width: 20px;" type="text" value="$!txtNoKPBaru2a" size="3" maxlength="2"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3a')"/>-<input name="txtNoKPBaru3a" id="txtNoKPBaru3a"  style="width: 40px;" type="text" value="$!txtNoKPBaru3a"  size="5" maxlength="4"/></td>
      </tr>
      <tr> 
        <td scope="row" align="right">&nbsp;MyID Lama : </td>
        <td ><input name="txtNoKPLamaa" type="textbox" id="txtNoKPLamaa" maxlength="8" size="10" value="$!noKpLama" style="width: 120px; text-transform:uppercase;" >&nbsp;&nbsp;(Sila kosongkan ruangan ini jika tidak berkaitan)</td>
      </tr>
      <tr> 
        <td scope="row" align="right">&nbsp;Lain-lain MyID : </td>
        <td ><select name="socJenisKPLaina" style="width: 125px;" >
            #set ($id = "")
            #set ($keterangan = "")
            #set ($selected = "")
	            #foreach($Listkp in $listkp)
	            #set ($id = $Listkp.id)
	            #set ($keterangan = $Listkp.keterangan)
	            	#if ($id == $JenisKpLain)
	            		#set ($selected = "selected")
	            	<option value="$id" $selected/>$keterangan.toUpperCase()</option>
	            	#end
	            #end
            	<option value="0"/>Sila Pilih KP</option>
            	#foreach($Listkp in $listkp)
            	#set ($id = $Listkp.id)
           		#set ($keterangan = $Listkp.keterangan)
	            	<option value="$id"/>$keterangan.toUpperCase()</option>
            	#end
          </select> <input name="txtNoKPLaina" id="txtNoKPLaina" style="width: 97px; text-transform:uppercase;" type="text" value="$!noKpLain"  maxlength="9" />&nbsp;&nbsp;(Sila kosongkan ruangan ini jika tidak berkaitan)</td>
      </tr>
      <tr> 
        <td scope="row" align="right">&nbsp;No. Fail Permohonan :</td>
        <td ><input name="txtNoPermohonan" type="text" id="txtNoPermohonan" value="$!noPermohonan" size="50" maxlength="50" style="width: 227px; text-transform:uppercase;" ></td>
      </tr>
      
      <tr> 
		<td></td>
        <td scope="row" align="left" height="30px" valign="bottom">
				<input type="button" name="cmdSemak" value="Semak" onClick="Semak()">
                <!--
				<input type="reset" name="cmdKosongkan" value="Batal">
				<input type="reset" value="Kembali ke Menu Utama" onclick="javascript:menuUtama()"/>
                -->
                		</td>
      </tr>
     
      
	<tr>
		<td height="21" colspan="5" valign="bottom">        	
        	         </td>
	</tr>      
      </tbody>
      </table>
</fieldset>
	<input type="hidden" name="hitButt" value="check_kp"/>
	<input type="hidden" name="action"/>
    <input type="hidden" name="command" />
	<input type="hidden" name="idpermohonan"/>
    <input type="hidden" name="idSimati" />
    <input type="hidden" name="idFlag" />
    <input type="hidden" name="flagno" />
    
    
    </form>
#set ($portal_role = "online")    
<script> 

function seterusnya() {
	document.f1.method="post";
	//document.f1.mode.value ="CetakPengesahanView";
	document.f1.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
	doAjaxCallf1("CetakPengesahanView");
	document.f1.submit();
}

function seterusnyaP() {
	document.f1.method="post";
	document.f1.mode.value ="Htaamview";
	document.f1.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
	doAjaxCallf1("Htaam");
	document.f1.submit();
}

function hartaYes() {
	document.f1.method="post";
	document.f1.hartaYa.value = "Ya";
	document.f1.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
	//doAjaxCallf1("Htaam");
	document.f1.submit();
}

//--------------frmBorangPMaklumatPemohon.jsp
function Semak() {
	
	if (document.f1.txtNoKPBaru1a.value == "" && document.f1.txtNoKPBaru2a.value == "" && document.f1.txtNoKPBaru3a.value == "" && document.f1.txtNoKPLamaa.value=="" && document.f1.txtNoKPLaina.value ==""&& document.f1.txtNoPermohonan.value ==""){
		alert("Sila salah satu maklumat permohonan diisi");
		document.f1.txtNoKPBaru1a.focus();
	}
	else if (document.f1.txtNoKPBaru1a.value != "" && document.f1.txtNoKPBaru1a.value.length < 6){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.f1.txtNoKPBaru1a.focus();
	}
	else if (document.f1.txtNoKPBaru2a.value != "" && document.f1.txtNoKPBaru2a.value.length < 2){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.f1.txtNoKPBaru2a.focus();
	}
	else if (document.f1.txtNoKPBaru3a.value != "" && document.f1.txtNoKPBaru3a.value.length < 4){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.f1.txtNoKPBaru3a.focus();
	}
	else if (document.f1.txtNoKPBaru1a.value != "" && document.f1.txtNoKPBaru2a.value == "" && document.f1.txtNoKPBaru3a.value == ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.f1.txtNoKPBaru2a.focus();
	}
	else if (document.f1.txtNoKPBaru1a.value != "" && document.f1.txtNoKPBaru2a.value != "" && document.f1.txtNoKPBaru3a.value == ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		document.f1.txtNoKPBaru3a.focus();
	}
	else if (document.f1.txtNoKPLamaa.value != "" && document.f1.txtNoKPLamaa.value.length < 7){
		alert("Sila masukkan NO KP Lama Sepenuhnya");
		document.f1.txtNoKPLamaa.focus();
	}
	else if (document.f1.txtNoKPLaina.value != "" && document.f1.socJenisKPLaina.value == "0"){
		alert("Sila pilih jenis No KP");
		document.f1.socJenisKPLaina.focus();
	}
	else if (document.f1.socJenisKPLaina.value != "0" && document.f1.txtNoKPLaina.value == ""){
		alert("Sila masukkan No KP Lain");
		document.f1.txtNoKPLaina.focus();
	}
	else{
	//alert("XXXXXXXXXXXx");
		//document.f1.method = "post";
		//document.f1.hitButt.value = "check_kp";
		//document.f1.action.value = "";
		//document.f1.submit();
		//doAjaxCallf1("check_kp");
		//document.f1.submit();
		
    document.f1.command.value = "check_kp";
	document.f1.action = "";
	document.f1.submit();
	}
}

//---------------frmBorangPMaklumatPermohonan.jsp
function simpan() {
	var radioSelected = false;	
	if( (document.f1.cb1.checked == true) || (document.f1.cb2.checked == true) || (document.f1.cb3.checked == true) || (document.f1.cb4.checked == true) || (document.f1.cb5.checked == true) || (document.f1.cb6.checked == true) || (document.f1.cb7.checked == true) ){
		radioSelected = true;
	}	
	if (!radioSelected){
		alert("Sila pilih \"Tujuan Permohonan\" terlebih dahulu.");
		return (false);
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return; 
		document.f1.method="post";
		document.f1.action="?_portal_module=ekptg.view.ppk.FrmBorangPSek17Online";	
		doAjaxCallf1("SimpanSemak");
		document.f1.submit();	
	}
}


/*function seterusnya() {
	document.f1.method="post";
	document.f1.mode.value ="Htaamview";
	document.f1.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
	doAjaxCallf1("Htaam");
	document.f1.submit();
}*/

function seterusnya() {
	document.f1.method="post";
	//document.f1.mode.value ="CetakPengesahanView";
	document.f1.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
	doAjaxCallf1("CetakPengesahanView");
	document.f1.submit();
}

function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'Baki Aksara' counter
	else 
		countfield.value = maxlimit - field.value.length;
}

function getCbA(){
	if (document.f1.cb2.checked == true){
		document.f1.cb3.checked = true;
	}
	
	if (document.f1.cb2.checked == false){
		document.f1.cb3.checked = false;
		document.f1.cb4.checked = false;
	} 
}

function getCbB(){
	if (document.f1.cb3.checked == true){
		document.f1.cb2.checked = true
	}
	
	if (document.f1.cb3.checked == false){
		if (document.f1.cb4.checked == false){
			document.f1.cb2.checked = false;
		}
	} 
}

function getCbC(){
	if (document.f1.cb4.checked == true){
		document.f1.cb2.checked = true
	}
	
	if (document.f1.cb4.checked == false){
		if (document.f1.cb3.checked == false){
			document.f1.cb2.checked = false;
		}
	} 
}
function getCbD(){
	if (document.f1.cb5.checked == true){
		document.f1.cb6.checked = true;
	}
	
	if (document.f1.cb5.checked == false){
		document.f1.cb6.checked = false;
		document.f1.cb7.checked = false;
	} 
}

function getCbF(){
	if (document.f1.cb6.checked == true){
		document.f1.cb5.checked = true;
	}
	
	if (document.f1.cb6.checked == false){
		if (document.f1.cb7.checked == false){
			document.f1.cb5.checked = false;
		}
	} 
}

function getCbG(){
	if (document.f1.cb7.checked == true){
		document.f1.cb5.checked = true;
	}
	
	if (document.f1.cb7.checked == false){
		if (document.f1.cb6.checked == false){
			document.f1.cb5.checked = false;
		}
	} 
}

	
function menuUtama(){
	document.f1.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.online.FrmOnlineMenuUtama";
	document.f1.submit();
}
function gotoSemakStatus(){
	document.f1.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
	document.f1.submit();
}
function kemaskiniBorangP(){
	//alert(input);
	document.f1.method="post";
	//document.f1.nopermohonan.value=id;
	//document.f1.typez.value="online";		
	//doAjaxCallf1("check_kp17","typez=online&nopermohonan="+id);
	doAjaxCallf1("check_kp17");
	document.f1.action="$EkptgUtil.getTabID('Menu','online')?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
	document.f1.submit();
}
</script>

