<style type="text/css">
<!--
.style1 {
	color: #FF0000;
	font-weight: bold;
}
-->
</style>
<p>
  <input name="idOB" type="hidden" id="idOB" value="$idOB"/>
  <input name="idPerintahHTAOBMST" type="hidden" id="idPerintahHTAOBMST" value="$idPerintahHTAOBMST"/>
  <input name="idPermohonanSimati" type="hidden" id="idPermohonanSimati" value="$idPermohonanSimati"/>
  <input name="idSimati" type="hidden" id="idSimati" value="$idSimati"/>
  <input name="statusWaris" type="hidden" id="statusWaris" value="$statusWaris"/>
  <input name="idPerintah" type="hidden" id="idPerintah" value="$idPerintah"/>
  <input name="flag_kemaskini_selesai" type="hidden" id="flag_kemaskini_selesai" value="$flag_kemaskini_selesai"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="listSize" type="hidden" id="listSize"/>
  <input name="hitButt" type="hidden" id="hitButt"/>
  <input name="cetakBorangHH" type="hidden" id="cetakBorangHH" value="$cetakBorangHH" />
  <input name="idFail" type="hidden" id="idFail" value="$idFail" />
</p>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      #if($statusWaris == 'HILANG')
      <legend><strong>SENARAI PENTADBIR</strong></legend>
      #else
      <legend><strong>SENARAI PEMEGANG AMANAH</strong></legend>
      #end
      <p></p>
      #if ($flagRowWaris == 'Y')
      <table align="center" width="100%">
        <tr class="table_header"> #if ($listSize != '0')
          <td scope="row" width="1%">&nbsp;</td>
          #end
          <td scope="row" width="4%" align="center">BIL</td>
          <td width="95%">NAMA WARIS</td>
        </tr>
        #set ($list = "")
        #foreach ($list in $Senarai)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr> #if ($listSize != '0')
          #if($list.flag == 'Y')
          #set($checked = 'checked')
          #else
          #set($checked = '')
          #end
          <td class="$row">
          #if ($list.statusHidup == '1')
          <input style="display:none" type="checkbox" value="$list.idOB" name="ids" $checked onclick="doUpdateCheck('$list.bil','$statusWaris')" $disabled/>
          #else
          <input type="checkbox" value="$list.idOB" name="ids" $checked onclick="doUpdateCheck('$list.bil','$statusWaris')" $disabled/>        
          #end
          </td>
          #end
          <td class="$row" align="center">$list.bil</td>
          #if ($list.statusHidup == '1')
          <td class="$row">$list.namaOB &nbsp;&nbsp;<span class="style1">(MENINGGAL DUNIA)</span></td>
          #else
          <td class="$row">$list.namaOB</td>
          #end </tr>
        #end
      </table>
      #else
      
      &nbsp;&nbsp;<font color="black">SILA SIMPAN PEMBAHAGIAN BAGI WARIS INI TERLEBIH DAHULU</font> <br>
      <br>
      #end
      </fieldset></td>
  </tr>
  <tr>
    <td align="center"> #if ($mode == 'update' && $flagRowWaris == 'Y')
      <input id="cmdSimpan" name="cmdSimpan" type="button" value="Simpan" onclick="javascript:simpan()"/>
      #if($flag_kemaskini_selesai != "yes")
      <script>
                                        document.getElementById('cmdSimpan').style.display = "none";
                                        </script>
      #end
      #end
      <input name="cmdTutup" type="button" value="Tutup" onclick="javascript:tutup()"/>
    
    #if($cetakBorangHH == "1")
 <input type="button" value="Cetak Borang HH" name="cetakBorangHH2" onclick="javascript:cetakBorangHH3('$idFail')"/>  
<script>
alert("Sila cetak Borang HH.");
</script>

#end
</td>
  </tr>
</table>
<!--
::::::::: $close_window
-->

#if($close_window == "yes")
<script>
window.close();
</script>
#end
<script>
function doUpdateCheck(bil,statusWaris){  
	var b = parseInt(bil)-1;
	var c = 0;
	var j = 0;
	if (document.${formName}.ids.length > 1){
		for (i = 0; i < document.${formName}.ids.length; i++){
			if (document.${formName}.ids[i].checked == true){
				c++;
			}
		}
		if (c > 4){
		
			if (statusWaris == 'Y'){
				alert("Hanya 4 Pentadbir yang boleh dilantik.")
			} else {
				alert("Hanya 4 Pemegang Amanah yang boleh dilantik.")
			}
			
			for (j = 0; j < document.${formName}.ids.length; j++){
				if (b == j){
					document.${formName}.ids[j].checked = false;
				}
			}
		}
	}			         
}
function simpan() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		
		return;
	}
	
	document.${formName}.cetakBorangHH.value = "1";	
	document.${formName}.hitButt.value = "simpan";	
	document.${formName}.submit();
	
	//window.close();
	//alert("Sila cetak Borang HH.");
	
}

function cetakBorangHH3(idFail){
	
	
	var url = "../../servlet/ekptg.report.ppk.BorangHH?idfail="+idFail;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}

function tutup() {
	window.close();
}
</script>
