<fieldset>
<legend>CARIAN</legend>
	
	<table width="100%" border="0" >

  		<tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Tahun</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectTahun</td>
    	</tr>
    	
    </table>
    
</fieldset>

<fieldset>
	<table width="60%" border="0" >
		<tr>
  			<td align="center">
 				<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport1')">
      			<input type="button" name="cmdIsiSemula" id="cmdIsiSemula" value="Kosongkan" onClick="kosongkan()"> 
    		</td>
  		</tr>
	</table>
</fieldset>


<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

        <tr>
        <td><a href="#" class="style2" onClick="cetak('1')"><font color="blue">Senarai Agensi Persekutuan Yang Belum Selesai Membayar Pampasan </font></a></td>
      </tr>  
       <tr>
        <td><a href="#" class="style2"  onClick="cetak('3')"><font color="blue">Caj Bayaran Lewat Mengikut Negeri</font></a></td>
      </tr> 
      <tr>
        <td><a href="#" class="style2" onClick="cetak('2')"><font color="blue">Caj Bayaran Lewat Mengikut Agensi</font></a></td>
      </tr>  
        <tr>
        <td><a href="#" class="style2" onClick="cetak('4')"><font color="blue">Jumlah Tanah Rizab Melayu Yang Terlibat Dengan Pengambilan Tanah Mengikut Negeri</font></a></td>
      </tr>  
       <tr>
        <td><a href="#" class="style2"  onClick="cetak('5')"><font color="blue">Jumlah Pampasan Mengikut Agensi</font></a></td>
      </tr> 
      <tr>
        <td><a href="#" class="style2" onClick="cetak('6')"><font color="blue">Jumlah Pampasan Mengikut Negeri</font></a></td>
      </tr>  

    </table>
</fieldset>


<!-- Hidden -->
<input type="hidden" name="id_pejabat" value="$!id_pejabat">
<input type="hidden" name="id_daerah" value="$!id_daerah">
<input type="hidden" name="tahun" value="$!tahun">
<input type="hidden" name="bulan" value="$!bulan">
<input type="hidden" name="jenisTempoh" value="$!jenisTempoh">
<input type="hidden" name="tahunSE" value="$!tahunSE">
<input type="hidden" name="bulanSE" value="$!bulanSE">

<script>
function doChange() {
	document.${formName}.command.value = "doChange";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.LaporanPampasan";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.LaporanPampasan";
	document.${formName}.submit();
}
function cetak(mode){

	if(document.${formName}.socTahun.value == ""){
	   	alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahun.focus();
		return;
	}
	else{		

		var tahun = document.${formName}.socTahun.value;
		
		if(mode=="1"){
			var url = "../servlet/ekptg.report.ppt.Agensi_belum_selesai_pembayaran?tahun="+tahun;		    
		}else if(mode=="2"){
			var url = "../servlet/ekptg.report.ppt.Caj_bayaran_lewat_agensi?tahun="+tahun;		
		}else if(mode=="3"){
			var url = "../servlet/ekptg.report.ppt.Caj_bayaran_lewat_negeri?tahun="+tahun;		
		}else if(mode=="4"){
			var url = "../servlet/ekptg.report.ppt.Luas_tanah_rizab?tahun="+tahun;		
		}else if(mode=="5"){
			var url = "../servlet/ekptg.report.ppt.Pampasan_agensi?tahun="+tahun;		
		}else if(mode=="6"){
			var url = "../servlet/ekptg.report.ppt.Pampasan_negeri?tahun="+tahun;		
		}
		
		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}

}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		window.location.hash=id;
        goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
</script>