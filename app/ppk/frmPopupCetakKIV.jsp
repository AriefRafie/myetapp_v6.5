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
                <td width="30%"> <font color="red"></font> Pegawai Pengendali</td>
                <td width="1%">: </td>
                <td width="69%" align=left> 
                	$!selectPegawai </td>
              </tr>
             
              <tr>
                <td width="30%"> <font color="red"></font> Tarikh Matang</td>
                <td width="1%">: </td>
                <td width="69%" align=left> 
                	<input type="text"  name="txtTarikhMatang" id="txtTarikhMatang" style="text-transform:uppercase;" value="" size="15" maxlength="15" >
                    <a href="javascript:displayDatePicker('txtTarikhMatang',false,'dmy');"><img src="../../img/calendar.gif" alt="" border="0"/></a> </td>
              </tr>
         
             <tr>
                <td width="30%"> <font color="red"></font> Tarikh Perintah</td>
                <td width="1%">: </td>
                <td width="69%" align=left><input type="text"  name="txtTarikhPerintah" id="txtTarikhPerintah" style="text-transform:uppercase;" value="" size="15" maxlength="15" >
                    <a href="javascript:displayDatePicker('txtTarikhPerintah',false,'dmy');"><img src="../../img/calendar.gif" alt="" border="0"/></a> </td>
              </tr> 
              
              <tr>
                <td width="30%">Seksyen</td>
                <td width="1%">: </td>
                <td width="69%" align=left> 
                	<select name=seksyen id=seksyen>
                		<option value="0">Semua</option>
                		<option value="8">Seksyen 8</option>
                		<option value="17">Seksyen 17</option>
                	</select>


				 </td>
              </tr>
              
               <tr>
				<td colspan=3 align=left>
                	
                	<input type="button" name="cmdKeluar" id="cmdKeluar" value="Cetak" onclick="javascript:cetakLaporan()">             
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


function cetakLaporan() {
	
	var idPegPengendali = document.${formName}.socPegawai.value;
	var tarikhMatang = document.${formName}.txtTarikhMatang.value;
	var tarikhPerintah = document.${formName}.txtTarikhPerintah.value;
	var seksyen = document.${formName}.seksyen.value;
	
	
	if(idPegPengendali)
	
	
		var url = "../../servlet/ekptg.report.ppk.SenaraiFailKIV?userId="+$userId+"&idPegPengendali="+document.${formName}.socPegawai.value+"&txtTarikhMatang="+document.${formName}.txtTarikhMatang.value+"&txtTarikhPerintah="+document.${formName}.txtTarikhPerintah.value+"&seksyen="+document.${formName}.seksyen.value;
	
    var hWnd = window.open(url,'Cetak','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>


