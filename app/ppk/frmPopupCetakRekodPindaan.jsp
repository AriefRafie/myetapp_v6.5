<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>


<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
#parse("css/eTapp_PPK.css")
</style>

<p>
 
   <input name="userId" type="hidden" id="userId" value="$userId" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    	<fieldset><legend><strong>Cetakan Laporan</strong></legend>
        	<table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="30%">&nbsp;</td>
                <td width="1%">&nbsp;</td>
                <td width="69%" align=left>&nbsp;</td>
              </tr>
             
              <tr>
                <td width="30%"> <font color="red">*</font> Tarikh Dari</td>
                <td width="1%">: </td>
                <td width="69%" align=left> 
                	<input type="text"  name="txtTarikhDari" id="txtTarikhDari" style="text-transform:uppercase;" value="" size="15" maxlength="15" >
                    <a href="javascript:displayDatePicker('txtTarikhDari',false,'dmy');"><img src="../../img/calendar.gif" alt="" border="0"/></a> </td>
              </tr>
         
             <tr>
                <td width="30%"> <font color="red">*</font> Tarikh Hingga</td>
                <td width="1%">: </td>
                <td width="69%" align=left><input type="text"  name="txtTarikhHingga" id="txtTarikhHingga" style="text-transform:uppercase;" value="" size="15" maxlength="15" >
                    <a href="javascript:displayDatePicker('txtTarikhHingga',false,'dmy');"><img src="../../img/calendar.gif" alt="" border="0"/></a> </td>
              </tr> 
               <tr>
				<td colspan=3 align=left>
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSurat()">
                	<input type="button" name="cmdKeluar" id="cmdKeluar" value="Keluar" onclick="javascript:keluar()">             
             	</td>
              </tr>
            </table>
      </fieldset>
    </td>
  </tr>
    <tr>
 	<td>&nbsp;</td>
  </tr>

</table>


<script>

function keluar() {
	window.close();
}


function cetakSurat(noFail,idfail,flagReport) {
	
	 if(document.${formName}.txtTarikhDari.value == ""){
		alert('Sila pilih tarikh dari.');
  		document.${formName}.txtTarikhDari.focus(); 
		return; 
	} else if(document.${formName}.txtTarikhHingga.value == ""){
		alert('Sila pilih tarikh hingga.');
  		document.${formName}.txtTarikhHingga.focus(); 
		return; 
	}
	
	
		var url = "../../servlet/ekptg.report.ppk.RekodPindaan?userId="+$userId+"&txtTarikhDari="+document.${formName}.txtTarikhDari.value+"&txtTarikhHingga="+document.${formName}.txtTarikhHingga.value;
	
    var hWnd = window.open(url,'Cetak','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>


