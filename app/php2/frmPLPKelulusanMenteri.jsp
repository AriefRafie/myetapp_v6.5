<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>  
  <input name="step" type="hidden" id="step" value="$step"/>
  <input name="statusRizab" type="hidden" id="statusRizab" value="$statusRizab"/>
</p>
<table width="100%">
  #if ($idFail != '')
  <tr>
    <td>#parse("app/php2/frmPLPHeader.jsp")</td>
  </tr>
  #else
  <tr>
    <td><div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201' && $idStatus != '1610202')
  <tr>
    <td><fieldset>
      <legend>KEPUTUSAN MENTERI</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanKelulusanMenteri in $BeanKelulusanMenteri)
        #set ($keputusanMenteri = $!beanKelulusanMenteri.keputusan)
       <input name="kelulusan" type="hidden" id="kelulusan" value="$beanKelulusanMenteri.keputusan"/>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Tarikh Terima</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhTerima" type="text" class="$inputTextClass" id="txtTarikhTerima" onBlur="check_date(this)" value="$beanKelulusanMenteri.tarikhTerima" size="9" maxlength="10" $readonly>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Keputusan</td>
          <td>:</td>
          <td>
          <select name="socKeputusan" id="socKeputusan" style="width:auto;" $readonly class="$disabled" onchange="javascript:doChangeKeputusanMenteri(this.value)" $disabled>
     		#if($beanKelulusanMenteri.keputusan == 'L')
                <option value="">SILA PILIH</option>
                <option value="L" selected="selected"> LULUS </option>
                <option value="B"> LULUS BERSYARAT </option>
                <option value="T"> TOLAK </option>
        	#elseif($beanKelulusanMenteri.keputusan == 'B')
        		<option value="">SILA PILIH</option>
              	<option value="L"> LULUS </option>
              	<option value="B" selected="selected"> LULUS BERSYARAT </option>
              	<option value="T"> TOLAK </option>
			#elseif($beanKelulusanMenteri.keputusan == 'T')
        		<option value="">SILA PILIH</option>
              	<option value="L"> LULUS </option>
              	<option value="B"> LULUS BERSYARAT </option>
              	<option value="T" selected="selected"> TOLAK </option>
            #else  
              	<option value="" selected="selected">SILA PILIH</option>
              	<option value="L"> LULUS </option>
              	<option value="B"> LULUS BERSYARAT </option>
              	<option value="T"> TOLAK </option>
            #end
          </select></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Ulasan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasan" id="txtUlasan" cols="50" rows="5" $readonly class="$inputTextClass" >$beanKelulusanMenteri.ulasan</textarea></td>
        </tr>
        #if ($keputusanMenteri == 'B')
	    <tr>
	      <td colspan="4"><fieldset>
	      	<legend>MAKLUMBALAS PEMOHON</legend>
	        <table width="100%" border="0" cellspacing="2" cellpadding="2">
             	<tr>
			      <td width="1%">&nbsp;</td>
			      <td width="28%">Keputusan Pemohon</td>
			      <td width="1%">:</td>
			      <td><select name="socKeputusanPemohon" id="socKeputusanPemohon" style="width:140px;" $readonly class="$inputTextClass" $disabled>
			      #if ($beanKelulusanMenteri.flagKeputusanPemohon == 'S')
			      	<option>SILA PILIH</option>
			        <option value="S" selected="selected">SETUJU</option>
			        <option value="TS">TIDAK SETUJU</option>
		          #elseif ($beanKelulusanMenteri.flagKeputusanPemohon == 'TS')
			        <option>SILA PILIH</option>
			        <option value="S">SETUJU</option>
			        <option value="TS" selected="selected">TIDAK SETUJU</option>
			      #else
			        <option selected="selected">SILA PILIH</option>
			        <option value="S">SETUJU</option>
			        <option value="TS">TIDAK SETUJU</option>
			      #end
			     </select></td>
			   </tr>
               <tr>
			     <td width="1%">&nbsp;</td>
			     <td width="28%">Ulasan Pemohon</td>
			     <td width="1%">:</td>
			     <td width="70%"><textarea name="txtUlasanPemohon" id="txtUlasanPemohon" rows="5" cols="50" $readonly class="$inputTextClass">$beanKelulusanMenteri.ulasanPemohon</textarea></td>
               </tr>
	        </table>
	      </fieldset></td>
	    </tr>
	    #end
        #if ($mode != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end

        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>
          	#if ($mode == 'view')
          	#if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniKelulusanMenteri()"/>
            #if($idStatus == '1610205')
            <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
            #end
            #end
            #if ($keputusanMenteri == 'B')
      		<input name="cmdCetak" type="button" onClick="cetakPLPSuratLulusBersyarat('$idFail')" value="Cetak Surat Lulus Bersyarat">
      		#elseif ($keputusanMenteri == 'T')
      		<input name="cmdCetak" type="button" onClick="javascript:cetakPLPSuratTolak('$idFail')" value="Cetak Surat Tolak">
      		#end
            #end
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
        	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
        	#end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniKelulusanMenteri('$idStatus')"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalKelulusanMenteri()"/>
            #end 
            
            <!-- START SPECIAL CASE - TO CATER JIKA KEPUTUSAN MENTERI BERUBAH EVEN SETELAH SELESAI -->
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
            #set ($allowPindaanKeputusan = 'Y')
            #if ($idStatus == '1614197' && $keputusanMenteri == 'L')
            	#set ($allowPindaanKeputusan = 'T')
            #end
            #if ($idStatus == '1617200' && $keputusanMenteri == 'B')
            	#set ($allowPindaanKeputusan = 'T')
            #end
            #if ($idStatus == '1610208' && $keputusanMenteri == 'T')
            	#set ($allowPindaanKeputusan = 'T')
            #end
            #if ($allowPindaanKeputusan == 'Y')
	            #if ($mode == 'view')
	            	<input type="button" name="cmdHantar" id="cmdHantar" value="Pinda Keputusan" onClick="doPindaKeputusan()"/>
	            #end
            #end
            #end
            <!-- END SPECIAL CASE - TO CATER JIKA KEPUTUSAN MENTERI BERUBAH EVEN SETELAH SELESAI -->
          </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
</table>

<script>
function doChangeKeputusanMenteri() {
	doAjaxCall${formName}("doChangeKeputusanMenteri");
}
function kemaskiniKelulusanMenteri() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniKelulusanMenteri(idStatus){

	if(document.${formName}.txtTarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima');
  		document.${formName}.txtTarikhTerima.focus(); 
		return; 
	}
	if (idStatus != '1610206'){
		if(document.${formName}.socKeputusan.value == ""){
			alert('Sila pilih Keputusan');
			document.${formName}.socSyor.focus(); 
			return; 
		}
	}
	if(document.${formName}.txtUlasan.value == ""){
		alert('Sila masukkan Ulasan');
  		document.${formName}.txtUlasan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniKelulusanMenteri";
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
function doPindaKeputusan(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doPindaKeputusan";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
function batalKelulusanMenteri(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function cetakPLPSuratLulusBersyarat(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=PLPSuratLulusBersyarat";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPLPSuratTolak(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratTolak";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function gotoSenaraiFailKeseluruhan() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPSenaraiFailKeseluruhanView";
	document.${formName}.submit();
}
</script>