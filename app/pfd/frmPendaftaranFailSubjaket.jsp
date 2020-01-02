<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {
	font-size: 9px;
	font-style: italic;
}
.style42 {color: #0000FF}
.style43 {color: #000000}
.style46 {font-size: 9px; font-style: italic; color: #FF0000; }
-->
</style>
<fieldset>
<legend>MAKLUMAT FAIL</legend>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabFailLama()">FAIL LAMA</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabFailBaru()">FAIL BARU</li>
    <li class="TabbedPanelsTab" tabindex="0" >FAIL SUBJAKET</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
     <div class="TabbedPanelsContent"></div>
     <div class="TabbedPanelsContent"></div>
     <div class="TabbedPanelsContent">
          		<fieldset>
<legend>MAKLUMAT FAIL</legend>
<table width="100%">
   <tr>
    <td width="29%" scope="row">NO FAIL</td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <span class="style42 style6">
      <label>$noFail.toUpperCase()</label>
      </span> </td>
  </tr>
  <tr>
    <td scope="row">TAJUK FAIL</td>
    <td scope="row"><div align="right">:</div></td>
    <td class="style42 style6">$tajukFail.toUpperCase()</td>
  </tr>
</table>
</fieldset>
     </div>   
  </div>
</div>
</fieldset>

<input type="hidden" name="action1" id="action1" />
<input type="hidden" name="idFail" id="idFail" value="$idFail"/>
<input type="hidden" name="modeAktiviti" id="modeAktiviti" value="$modeAktiviti"/>
<input type="hidden" name="aktivitiValue" id="aktivitiValue" value="$aktivitiValue"/>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});
//-->
</script>
<script>


function batal(){

	document.${formName}.reset();
	document.${formName}.txtTajukFailB.value = "";
	document.${formName}.socSeksyenB.value = "";
	document.${formName}.socUrusanB.value = "";
	//document.${formName}.socSuburusanB.value = "";
	//document.${formName}.socTarafB.value = "";
	//document.${formName}.socStatusB.value = "";
	document.${formName}.socLokasiFailB.value = "";
	document.${formName}.socFaharasatB.value = "";

	
	return;
	
}
function cetak(idFail) {
        var url = "../servlet/ekptg.report.pfd.PFDFail?reportType=PDF&KULITFAIL_ID="+idFail;
        var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
        if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
        if (hWnd.focus != null) hWnd.focus();

}
function update1(idFail){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=updateFailBaruSeksyen";
	document.${formName}.idFail.value = idFail;
	document.${formName}.submit();

}

function kemaskini(idFail){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=kemaskiniFailBaruSeksyen";
	document.${formName}.idFail.value = idFail;

	document.${formName}.submit();

}
function kembali(){

	document.${formName}.action ="?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=SenaraiFail";
	document.${formName}.submit();
}
function simpan(){
	
	var BDate = document.${formName}.txtTarikhDaftarB.value;
		
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth();
		curr_month++
		var curr_year = d.getFullYear();
	
		var today = curr_date +'/' + curr_month +'/' + curr_year;
	
		if( BDate > today)
		{
			alert('Sila pastikan " Tarikh Daftar Fail " tidak melebihi tarikh hari ini.');
			document.${formName}.txtTarikhDaftarB.focus();
			return false;
		}
		
//		if (document.${formName}.socNegeriB.value == ""){
//				alert('Sila masukkan " Negeri " terlebih dahulu.');
//				document.${formName}.socNegeriB.focus();
//				return;
//		}

		if (document.${formName}.txtTajukFailB.value == ""){
				alert('Sila masukkan " Tajuk Fail " terlebih dahulu.');
				document.${formName}.txtTajukFailB.focus();
				return;
		}
		
		if (document.${formName}.socSeksyenB.value == ""){
				alert('Sila masukkan " Seksyen " terlebih dahulu.');
				document.${formName}.socSeksyenB.focus();
				return;
		}
		if (document.${formName}.socUrusanB.value == ""){
				alert('Sila masukkan " Urusan " terlebih dahulu.');
				document.${formName}.socUrusanB.focus();
				return;
		}  
		
		if (document.${formName}.socTarafB.value == ""){
				alert('Sila masukkan " Taraf Keselamatan " terlebih dahulu.');
				document.${formName}.socTarafB.focus();
				return;
		}  
		

		
		if (document.${formName}.socStatusB.value == ""){
				alert('Sila masukkan " Status Fail " terlebih dahulu.');
				document.${formName}.socStatusB.focus();
				return;
		}  
		
//		if (document.${formName}.socLokasiFailB.value == ""){
//				alert('Sila masukkan " Lokasi Fail " terlebih dahulu.');
//				document.${formName}.socLokasiFailB.focus();
//				return;
//		} 
//		
//		if (document.${formName}.socFaharasatB.value == ""){
//				alert('Sila masukkan " Faharasat " terlebih dahulu.');
//				document.${formName}.socFaharasatB.focus();
//				return;
//		}  	

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=simpanFailBaruSeksyen";
	document.${formName}.submit();
}
function tabFailLama(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailLamaSeksyen";
	document.${formName}.submit();
}
function tabFailBaru(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailBaruSeksyen&modeAktiviti=tiada";
	document.${formName}.submit();
}
function doChangeSeksyenB() {
	document.${formName}.action.value = "tabFailBaruSeksyen";
 	doAjaxCall${formName}("doChangeSeksyenB");
}
function doChangeUrusanB() {
	document.${formName}.action.value = "tabFailBaruSeksyen";
 	doAjaxCall${formName}("doChangeUrusanB");
}
function aktivitiTiada(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailBaruSeksyen&modeAktiviti=tiada";
	document.${formName}.submit();

}
function aktivitiAda(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailBaruSeksyen&modeAktiviti=ada";
	document.${formName}.submit();
} 

function doChangeNegeri() {
	document.${formName}.action.value = "tabFailBaruSeksyen";
 	doAjaxCall${formName}("doChangeNegeri");
}

</script>
