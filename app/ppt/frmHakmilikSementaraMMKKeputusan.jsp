<style type="text/css">
<!--
.style1 {color: #FF0000}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>


#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp") 

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabPenyediaan('$idFail','$idPermohonan')">Penyediaan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabSemakan('$idFail','$idPermohonan')">Semakan</li>
    <li class="TabbedPanelsTab" tabindex="0">Keputusan PBN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">  
    <fieldset>
    <legend><strong>Maklumat MMK/MB/KM/LC</strong></legend>
    <table width="100%">

      <tr>
        <td width="1%" align="left">&nbsp;</td>
        <td width="20%" align="left">No. Rujukan MMK</td>
        <td width="1%">:</td>
        <td width="29%"><label>
          <input name="txtNoRuj" type="text" class="$disabledKptsn" id="txtNoRuj" value="$NORUJUKAN" $readonlyKptsn style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" />
        </label></td>
        <td width="1%" align="left"><span class="style1">*</span></td>
        <td width="20%" align="left">Tarikh Rujukan MMK</td>
        <td width="1%">:</td>
        <td width="29%">
<input name="txdTarikhMMK" type="text" class="$disabledKptsn" id="txdTarikhMMK" value="$TARIKH" size="10" $readonlykptsn onblur="checking_validation(this,'tarikh_mmk_check','yes','mmk','tarikh');" />         
#if($readonlyKptsn != 'readonly') <a href="javascript:displayDatePicker('txdTarikhMMK',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end
         <span class="style52">dd/mm/yyyy</span>         </td>
      </tr>
      <tr>
        <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="20%" align="left">Tarikh Hantar</td>
    <td width="1%">:</td>
    <td width="29%"><label>
      <input name="txdTarikhHantar" type="text" class="$disabledKptsn" id="txdTarikhHantar" value="$TARIKHHANTAR" size="10" $readonlyKptsn onblur="checking_validation(this,'tarikh_hantar_check','yes','hantar','tarikh');" >
      #if($readonlyKptsn != 'readonly') <a href="javascript:displayDatePicker('txdTarikhHantar',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>#end<span class="style52">dd/mm/yyyy</span></label></td>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="20%" align="left">Keputusan</td>
    <td width="1%">:</td>
    <td width="29%">
    #if($STATUSKEPUTUSAN == '1')
      <input type="radio" name="sorKptsn" id="sorKptsn" value="1" $readonlyKptsn class="$disabledKptsn" $disabledKptsn checked="checked" >
    Lulus 
    #else
     <input type="radio" name="sorKptsn" id="sorKptsn" value="1" $readonlyKptsn class="$disabledKptsn" $disabledKptsn>
    Lulus 
    #end
    #if($STATUSKEPUTUSAN == '2')
    <input type="radio" name="sorKptsn" id="sorKptsn" value="2" $readonlyKptsn class="$disabledKptsn" $disabledKptsn checked="checked" >
    Tidak Lulus/Tolak
    #else
    <input type="radio" name="sorKptsn" id="sorKptsn" value="2" $readonlyKptsn class="$disabledKptsn" $disabledKptsn >
     Tidak Lulus/Tolak
    #end   
     #if($STATUSKEPUTUSAN == '3')
    <input type="radio" name="sorKptsn" id="sorKptsn" value="3" $readonlyKptsn class="$disabledKptsn" $disabledKptsn checked="checked" >
    Tangguh
    #else
    <input type="radio" name="sorKptsn" id="sorKptsn" value="3" $readonlyKptsn class="$disabledKptsn" $disabledKptsn >
    Tangguh
    #end   
    
    
    </td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">*</span></td>
    <td align="left" valign="top">Tarikh Keputusan</td>
    <td valign="top">:</td>
    <td valign="top"><label>
      <input name="txdTarikhKeputusan" type="text" class="$disabledKptsn" id="txdTarikhKeputusan" value="$TARIKHKEPUTUSAN" size="10" $readonlyKptsn onblur="checking_validation(this,'tarikh_keputusan_check','yes','keputusan','tarikh');">
        #if($readonlyKptsn != 'readonly') <a href="javascript:displayDatePicker('txdTarikhKeputusan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>#end
        <span class="style52">dd/mm/yyyy</span></label></td>
    <td width="1%" rowspan="2" align="left" valign="top"><span class="style1">*</span></td>
    <td rowspan="2" align="left" valign="top">Ulasan</td>
    <td valign="top">:</td>
    <td rowspan="2"><textarea name="txtUlasan" id="txtUlasan" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyKptsn class="$disabledKptsn" >$ULASANKEPUTUSAN</textarea></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">*</span></td>
    <td align="left" valign="top">Tarikh Terima Keputusan</td>
    <td valign="top">:</td>
    <td valign="top"><label>
      <input name="txdTarikhTerima" type="text" class="$disabledKptsn" id="txdTarikhTerima" value="$TARIKHTERIMA" size="10" $readonlyKptsn onblur="checking_validation(this,'tarikh_terima_check','yes','terima keputusan','tarikh');">
        #if($readonlyKptsn != 'readonly') <a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end
        <span class="style52">dd/mm/yyyy</span></label></td>
    <td valign="top">&nbsp;</td>
    </tr>
    </table>
    </fieldset>  
    </div>
  </div>
</div>
<table width="100%">
  <tr>
    <td colspan="6" align="center">
    #if($modeKptsn == 'newMMK')
       <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanKeputusan('$idMMK')" />
       <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalKeputusanNew()" />

    #end
    #if($modeKptsn == 'paparMMK')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniKeputusan()" />
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')"/>
     <!-- <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusSemakan()" />-->
    #end
    #if($modeKptsn == 'kemaskiniMMK')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateKeputusan()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalKeputusanUpdate()" />

    #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
    </td>
    </tr>

</table>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td>
        
        #if($negeriMMK=="2")
		 
		 #elseif($negeriMMK=="3")
		 	
		 #elseif($negeriMMK=="4")
		 	
		 #elseif($negeriMMK=="5")
		 	<a href="#" class="style2" onClick="javascript:cetakMMKNS('$idFail')"><font color="blue"> Kertas MMK/KM/MB/LC </font></a>
		 #elseif($negeriMMK=="6")
		 
		 #elseif($negeriMMK=="7")
		 	
		 #elseif($negeriMMK=="8")
		 	
		 #elseif($negeriMMK=="9")
		 	
		 #elseif($negeriMMK=="10")
		 	<a href="#" class="style2" onClick="javascript:cetakMMKSelangor('$idFail')"><font color="blue"> Kertas MMK/KM/MB/LC </font></a>
		 #elseif($negeriMMK=="11")
		 	
		 #elseif($negeriMMK=="14")
		 	<a href="#" class="style2" onClick="javascript:cetakMMKKL('$idFail')"><font color="blue"> Kertas MMK/KM/MB/LC </font></a>
		 #else
		 	
		 #end
        
        
        
        
        
        
        </td>
      </tr>           
    </table>
</fieldset>
<input name="id_fail" type="hidden" value="$idFail" />
<input name="id_permohonan" type="hidden" value="$idPermohonan" />
<input name="idMMK" type="hidden" value="$idMMK" />
<input name="idMMKKptsn" type="hidden" value="$idMMKKptsn" />
<input name="modeKptsn" type="hidden" value="$modeKptsn" />
<input name="tarikh_semakan" id="tarikh_semakan" type="hidden" value="$TARIKHSEMAKAN" />



<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});
//-->
</script>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakMMKNS(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.MMKSementara?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function cetakMMKSelangor(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.SementaraMMKSelangor?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function cetakMMKKL(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.SementaraMMKKL?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function tabPenyediaan(id_fail,id_permohonan){
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=newMMK";
	document.${formName}.submit();
}
function tabSemakan(id_fail,id_permohonan){
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=tabSemakan";
	document.${formName}.submit();
}
function simpanKeputusan(idMMK){
	
	  var mmk  = document.${formName}.txdTarikhMMK.value;
	  
	  var dt1MMK   = parseInt(mmk.substring(0,2),10);
	  var mon1MMK  = parseInt(mmk.substring(3,5),10)-1;
	  var yr1MMK   = parseInt(mmk.substring(6,10),10);
		   
	  var dateMMK = new Date(yr1MMK, mon1MMK, dt1MMK);
	  
	  var semak  = document.${formName}.tarikh_semakan.value;
	  
	  var dt1Semak   = parseInt(semak.substring(0,2),10);
	  var mon1Semak  = parseInt(semak.substring(3,5),10)-1;
	  var yr1Semak   = parseInt(semak.substring(6,10),10);
		   
	  var dateSemak = new Date(yr1Semak, mon1Semak, dt1Semak);
	  
	   var hantar  = document.${formName}.txdTarikhHantar.value;
	  
	  var dt1Hantar  = parseInt(hantar.substring(0,2),10);
	  var mon1Hantar  = parseInt(hantar.substring(3,5),10)-1;
	  var yr1Hantar   = parseInt(hantar.substring(6,10),10);
		   
	  var dateHantar = new Date(yr1Hantar, mon1Hantar, dt1Hantar);
	  
	  var keputusan  = document.${formName}.txdTarikhKeputusan.value;
	  
	  var dt1Keputusan   = parseInt(keputusan.substring(0,2),10);
	  var mon1Keputusan  = parseInt(keputusan.substring(3,5),10)-1;
	  var yr1Keputusan   = parseInt(keputusan.substring(6,10),10);
		   
	  var dateKeputusan = new Date(yr1Keputusan, mon1Keputusan, dt1Keputusan);
	  
	  var terimaKptsn  = document.${formName}.txdTarikhTerima.value;
	  
	  var dt1TerimaKptsn   = parseInt(terimaKptsn.substring(0,2),10);
	  var mon1TerimaKptsn  = parseInt(terimaKptsn.substring(3,5),10)-1;
	  var yr1TerimaKptsn   = parseInt(terimaKptsn.substring(6,10),10);
		   
	  var dateTerimaKptsn = new Date(yr1TerimaKptsn, mon1TerimaKptsn, dt1TerimaKptsn);
	  
	  
	  
	
	if(dateMMK < dateSemak){
		alert("Tarikh Rujukan MMK tidak boleh kurang dari Tarikh Semakan MMK. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTarikhMMK.focus(); 
		return;
 	}
	
	if(dateHantar > dateKeputusan){
		alert("Tarikh Hantar tidak boleh melebihi dari Tarikh Keputusan. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTarikhHantar.focus(); 
		return;
 	}
	if(dateKeputusan > dateTerimaKptsn){
		alert("Tarikh Keputusan tidak boleh melebihi dari Tarikh Terima Keputusan. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTarikhKeputusan.focus(); 
		return;
 	}
	
	

	if(document.${formName}.txdTarikhHantar.value == ""){
		alert("Sila masukkan \"Tarikh Hantar\" terlebih dahulu.");
  		document.${formName}.txdTarikhHantar.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhKeputusan.value == ""){
		alert("Sila masukkan \"Tarikh Keputusan\" terlebih dahulu.");
  		document.${formName}.txdTarikhKeputusan.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhTerima.value == ""){
		alert("Sila masukkan \"Tarikh Terima Keputusan\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerima.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhMMK.value == ""){
		alert("Sila masukkan \"Tarikh MMK\" terlebih dahulu.");
  		document.${formName}.txdTarikhMMK.focus(); 
		return;
	}
	else if(document.${formName}.sorKptsn.value == ""){
		alert("Sila pilih \"Status Keputusan\" terlebih dahulu.");
  		document.${formName}.sorKptsn.focus(); 
		return;
	}
	else if(document.${formName}.txtUlasan.value == ""){
		alert("Sila masukkan \"Ulasan\" terlebih dahulu.");
  		document.${formName}.txtUlasan.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idMMK.value = idMMK;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=simpanKeputusan";
	document.${formName}.submit();
}
function kemaskiniKeputusan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=kemaskiniKeputusan";
	document.${formName}.submit();
}
function hapusKeputusan(){
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=hapusKeputusan";
	document.${formName}.submit();
}
function updateKeputusan(){

	  var mmk  = document.${formName}.txdTarikhMMK.value;
	  
	  var dt1MMK   = parseInt(mmk.substring(0,2),10);
	  var mon1MMK  = parseInt(mmk.substring(3,5),10)-1;
	  var yr1MMK   = parseInt(mmk.substring(6,10),10);
		   
	  var dateMMK = new Date(yr1MMK, mon1MMK, dt1MMK);
	  
	  var semak  = document.${formName}.tarikh_semakan.value;
	  
	  var dt1Semak   = parseInt(semak.substring(0,2),10);
	  var mon1Semak  = parseInt(semak.substring(3,5),10)-1;
	  var yr1Semak   = parseInt(semak.substring(6,10),10);
		   
	  var dateSemak = new Date(yr1Semak, mon1Semak, dt1Semak);
	  
	   var hantar  = document.${formName}.txdTarikhHantar.value;
	  
	  var dt1Hantar  = parseInt(hantar.substring(0,2),10);
	  var mon1Hantar  = parseInt(hantar.substring(3,5),10)-1;
	  var yr1Hantar   = parseInt(hantar.substring(6,10),10);
		   
	  var dateHantar = new Date(yr1Hantar, mon1Hantar, dt1Hantar);
	  
	  var keputusan  = document.${formName}.txdTarikhKeputusan.value;
	  
	  var dt1Keputusan   = parseInt(keputusan.substring(0,2),10);
	  var mon1Keputusan  = parseInt(keputusan.substring(3,5),10)-1;
	  var yr1Keputusan   = parseInt(keputusan.substring(6,10),10);
		   
	  var dateKeputusan = new Date(yr1Keputusan, mon1Keputusan, dt1Keputusan);
	  
	  var terimaKptsn  = document.${formName}.txdTarikhTerima.value;
	  
	  var dt1TerimaKptsn   = parseInt(terimaKptsn.substring(0,2),10);
	  var mon1TerimaKptsn  = parseInt(terimaKptsn.substring(3,5),10)-1;
	  var yr1TerimaKptsn   = parseInt(terimaKptsn.substring(6,10),10);
		   
	  var dateTerimaKptsn = new Date(yr1TerimaKptsn, mon1TerimaKptsn, dt1TerimaKptsn);
	  
	  
	  
	
	if(dateMMK < dateSemak){
		alert("Tarikh Rujukan MMK tidak boleh kurang dari Tarikh Semakan MMK. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTarikhMMK.focus(); 
		return;
 	}
	
	if(dateHantar > dateKeputusan){
		alert("Tarikh Hantar tidak boleh melebihi dari Tarikh Keputusan. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTarikhHantar.focus(); 
		return;
 	}
	if(dateKeputusan > dateTerimaKptsn){
		alert("Tarikh Keputusan tidak boleh melebihi dari Tarikh Terima Keputusan. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTarikhKeputusan.focus(); 
		return;
 	}
	

	if(document.${formName}.txdTarikhHantar.value == ""){
		alert("Sila masukkan \"Tarikh Hantar\" terlebih dahulu.");
  		document.${formName}.txdTarikhHantar.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhKeputusan.value == ""){
		alert("Sila masukkan \"Tarikh Keputusan\" terlebih dahulu.");
  		document.${formName}.txdTarikhKeputusan.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhTerima.value == ""){
		alert("Sila masukkan \"Tarikh Terima Keputusan\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerima.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhMMK.value == ""){
		alert("Sila masukkan \"Tarikh MMK\" terlebih dahulu.");
  		document.${formName}.txdTarikhMMK.focus(); 
		return;
	}
	else if(document.${formName}.sorKptsn.value == ""){
		alert("Sila pilih \"Status Keputusan\" terlebih dahulu.");
  		document.${formName}.sorKptsn.focus(); 
		return;
	}
	else if(document.${formName}.txtUlasan.value == ""){
		alert("Sila masukkan \"Ulasan\" terlebih dahulu.");
  		document.${formName}.txtUlasan.focus(); 
		return;
	}
	
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=updateKeputusan";
	document.${formName}.submit();
}
function batalKeputusanNew(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=batalKeputusanNew";
	document.${formName}.submit();
}
function batalKeputusanUpdate(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=batalKeputusanUpdate";
	document.${formName}.submit();
}
function kembali(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=";
	document.${formName}.submit();
}

function checking_validation(field,point,mandatory,value_field,jenis_field){	
    	
	var lepas_or_xlepas = 2;	
	//alert(jenis_field)
	
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	      DateField.select();
		 // DateField.focus();
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	   //alert(lepas_or_xlepas);
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   }
	   else
	   {
	  
	   
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.focus();
	   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.select();
	//   DateField.focus();
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	//   DateField.select();
	//   DateField.focus();
	   
	   }
	   
	   }
	   
	   
	   if(jenis_field == "normal")
	   {
	   
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	
	   }
	   else
	   {
	    document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   
	   	   
	   }
	   
	   
	   
	
	}





</script>
