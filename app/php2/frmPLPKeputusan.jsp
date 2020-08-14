<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {color: #0000FF}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idKeputusan" type="hidden" id="idKeputusan" value="$idKeputusan"/>
  <input name="idKeputusanPTD" type="hidden" id="idKeputusanPTD" value="$idKeputusanPTD"/>
  <input name="flagGuna" type="hidden" id="idHakmilik" value="$flagGuna"/>
  <input name="statusRizab" type="hidden" id="statusRizab" value="$statusRizab"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>  
  <input name="step" type="hidden" id="step" value="$step"/>
  <input name="flagPampasan" type="hidden" id="flagPampasan" value="$flagPampasan"/>
  <input name="pampasan" type="hidden" id="pampasan" value="$pampasan"/>
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
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201' && $idStatus != '1610202' && $idStatus != '1610203' && $idStatus !='1610204' && $idStatus !='1610205')
  <tr>
    <td><fieldset>
      <legend>KEPUTUSAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach($beanMaklumatKeputusan in $BeanMaklumatKeputusan)
		<!--         
        <input name="socGanti" type="hidden" id="socGanti" value="$beanMaklumatKeputusan.jenis"/>
        <input name="txtPampasan" type="hidden" id="txtPampasan" value="$beanMaklumatKeputusan.pampasan"/>
        <input name="txtCatatan" type="hidden" id="txtCatatan" value="$beanMaklumatKeputusan.catatan"/>
		-->        
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Tarikh Terima</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhTerima" type="text" class="$inputTextClass" id="txtTarikhTerima" onBlur="check_date(this)" value="$beanMaklumatKeputusan.tarikhTerima" size="9" maxlength="10" $readonly>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
		  </td>
        </tr>
        
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Keputusan</td>
          <td width="1%">:</td>
          <td width="70%">
          <select name="socKeputusan" id="socKeputusan" style="width:120px;" $readonly class="$disabled" $disabled>
          #if($beanMaklumatKeputusan.keputusan == 'S')
                <option value="">SILA PILIH</option>
                <option value="S" selected="selected"> SETUJU </option>
                <option value="TS"> TIDAK SETUJU </option>
        	#elseif($beanMaklumatKeputusan.keputusan == 'TS')
        		<option value="">SILA PILIH</option>
              	<option value="S"> SETUJU </option>
              	<option value="TS" selected="selected"> TIDAK SETUJU </option>
            #else  
              	<option value="" selected="selected">SILA PILIH</option>
              	<option value="S"> SETUJU </option>
              	<option value="TS"> TIDAK SETUJU </option>
            #end
          </select>
          </td>
        </tr>
        
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Ulasan</td>
          <td width="1%">:</td>
          <td valign="top"><textarea name="txtUlasan" id="txtUlasan" cols="50" rows="5" $readonly class="$inputTextClass" >$beanMaklumatKeputusan.ulasan</textarea></td>
        </tr>
        
        <!-- <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Keputusan</td>
          <td width="1%">:</td>
          <td width="70%"> #if ($idKeputusan == 'L')
            LULUS
            #elseif ($idKeputusan == 'B')
            LULUS BERSYARAT
            #elseif ($idKeputusan == 'T')
            TOLAK
            #end</td>
        </tr> -->
        
        #if ($idPampasan == '99999')
        	#set ($idPampasan = $beanMaklumatKeputusan.jenis)
        #end
		#if ($idKeputusanPTD == 'S')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="27%" align="top">Jenis Pampasan</td>
          <td width="1%">:</td>
		  <td width="71%">
          		#if ($idPampasan == '1')
                TANAH GANTI
                #elseif($idPampasan == '2')
                WANG PAMPASAN
               	#elseif($idPampasan == '3')
               	TANAH GANTI DAN WANG PAMPASAN
               	#elseif($idPampasan == '4')
               	LAIN-LAIN
                #else
                TIADA PAMPASAN
                #end
			</td>
        </tr>
        
        #if($idPampasan == '2' || $idPampasan == '3')
        <tr>
          <td width="QQ1%">&nbsp;</td>
          <td align="top">Nilai Pampasan(RM)</td>
          <td>:</td>
          <td>$beanMaklumatKeputusan.pampasan</td>
        </tr>
        #end
        #if($idPampasan == '4')
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="top">Catatan</td>
          <td>:</td>
          <td>$beanMaklumatKeputusan.catatan</td>
        </tr>
        #end
        #end
        
        #if ($idKeputusanPTD == 'TS')
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Tarikh Hantar Surat</td>
          <td>:</td>
          <td><input name="txtTarikhKeputusan" type="text" class="$inputTextClass" id="txtTarikhKeputusan" onBlur="check_date(this)" value="$beanMaklumatKeputusan.tarikhKeputusan" size="9" maxlength="10" $readonly>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhKeputusan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end
          </td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
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
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
            #if ($idStatus == '1617200')
              <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doHantarProses()"/>
              <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
             #end
             #if($idKeputusanPTD == 'TS')
              <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
             #end
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKeputusan('$idPampasan','$idKeputusanPTD')"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/>
            #end </td>
        </tr>
        #end
      </table>
      </fieldset>
    </td>
  </tr>
  <tr>
  <td><fieldset id="tableReport" style="display:none;">
	<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		#if ($idKeputusanPTD == 'TS')
  		<tr>
   	    <td><a href="#" class="style2" onClick="javascript:cetakSuratTolak('$idFail')">Surat Tolak</a></td>
  		</tr>  
        #end
    </table>
  </fieldset></td>
  </tr>
  #end
</table>

#set($flag_keputusanMenteriNRE = "")
#foreach($beanMK in $BeanKelulusanMenteri)
#set($flag_keputusanMenteriNRE = $beanMK.keputusan)
#end


#if($redirectToSkrinBorang=="Y")
<script>
document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPBorang12ABView&idPermohonan=$idPermohonan";
document.${formName}.method="POST";
document.${formName}.submit();
</script>
#end

<script>

//alert('Status Rizab : '+'$statusRizab'+'Flag Keputusan Menteri : '+'$flag_keputusanMenteriNRE');
if('$statusRizab'=="MILIK" && '$flag_keputusanMenteriNRE'=="L") {
	document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPBorang12ABView&idPermohonan=$idPermohonan";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function doChangePampasan() {
	doAjaxCall${formName}("doChangePampasan");
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
function simpanKeputusan(idPampasan,idKeputusanPTD) {

	if(document.${formName}.txtTarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima');
  		document.${formName}.txtTarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.socKeputusan.value == ""){
		alert('Sila masukkan Keputusan');
  		document.${formName}.socKeputusan.focus(); 
		return; 
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
	document.${formName}.hitButton.value = "doSimpan";
	doAjaxCall${formName}("");
}
function kemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batal() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doHantarProses(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarProses";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
function cetakSuratTolak(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratTolak";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakSuratKelulusanSeluruh(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratLulusSeluruh";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratKelulusanSebahagian(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratLulusSebahagian";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
