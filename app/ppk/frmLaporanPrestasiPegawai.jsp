<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    	<fieldset><legend><strong>Laporan Prestasi Pegawai</strong></legend>
        	<table width="100%" border="0" cellspacing="2" cellpadding="2">
   <!--   		<tr>
	                <td width="30%" align="right"><span class="style1">Negeri</span></td>
	                <td width="70%">: $selectNegeri</td>
              	</tr>
              	<tr>
	                <td align="right"><span class="style1">Unit</span></td>
	                <td>: $selectUnit</td>
              	</tr> -->
              $selectNegeri
              $selectUnit
              <tr>
                <td align="right"><span class="style1">Tahun</span></td>
                <td>: $selectTahun</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="javascript:cetak()"/></td>
              </tr>
              <tr>
                <td colspan="2" align="left" valign="bottom"><i>Label berwarna <font color="#ff0000">merah</font> adalah mandatori.</i></td>
              </tr>
            </table>
      </fieldset>
    </td>
  </tr>
</table>


<script>
	function doChangeNegeri() {
		doAjaxCall${formName}("doChangeNegeri");
	}
	function cetak() {
       	
	#if($reportRole != "user_ppk")
		
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih Negeri.');
	  		document.${formName}.socNegeri.focus(); 
			return; 
		}
		
   	#end
		
		if(document.${formName}.socUnit.value == ""){
			alert('Sila pilih Unit.');
	  		document.${formName}.socUnit.focus(); 
			return; 
		}
		if(document.${formName}.socTahun.value == ""){
			alert('Sila pilih Tahun.');
	  		document.${formName}.socTahun.focus(); 
			return; 
		}
		
/* 		var url = "../servlet/ekptg.report.ppk.LaporanPrestasiPegawai?idNegeri="+document.${formName}.socNegeri.value+"&idUnit="+document.${formName}.socUnit.value+"&tahun="+document.${formName}.socTahun.value;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus(); */
	    //ekptg.view.ppk.laporan.LaporanPrestasiPegawaiPPK
	    var windowObjectReference;
		var strWindowFeatures = "menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes";
    	var url = "../x/${securityToken}/ekptg.view.ppk.laporan.LaporanPrestasiPegawaiPPK?command=pegawai&urli=tiada&flagReport=S";
		var params = "&idNegeri="+document.${formName}.socNegeri.value;
		params += "&idUnit="+document.${formName}.socUnit.value;
		params += "&tahun="+document.${formName}.socTahun.value;
  		windowObjectReference = window.open(url+params, "Laporan", strWindowFeatures);

/* 	        var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
	        if ((document.window != null) && (!hWnd.opener))
	    	hWnd.opener = document.window;
	        if (hWnd.focus != null) hWnd.focus();	 */
	    
	}
	
</script>
