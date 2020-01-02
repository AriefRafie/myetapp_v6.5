<fieldset>
<legend>CARIAN</legend>
	
	<table width="100%" border="0" >
	    <tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Nama Kementerian</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectKementerian</td>
    	</tr>
    	
    	<tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Status</td>
    		<td width="1%" align="center">:</td>
    		<td>
    		 <select id="socStatus" name="socStatus" onChange="showhidereport()">
    		  <option value=" ">SILA PILIH</option>
			  <option value="3">DILULUSKAN</option>
			  <option value="2">DALAM PROSES</option>
			  <option value="1">DITOLAK</option>
			</select> 
			</td>
    	</tr>

  		<tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Nama Pejabat JKPTG</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectPejabat</td>
    	</tr>
    	      
        <tr>
        <td ><font color="red">*</font></td>
    		<td align="left">Julat Tahun</td>
    		<td align="center">:</td>
        <td>
        <table width="100%" border="0" >	
    	<tr>
    		
    		<td>Dari Tahun</td>
    		<td>$!selectTahun</td>
    		<td>Sehingga Tahun</td>
    		<td>$!selectTahunSehingga</td>
    	</tr>
        </table>
        </td>
        </tr>
        </table>
    	
    	
	
</fieldset>

<fieldset>
	<table width="90%" border="0" >
		<tr>
  			<td align="center">
 			<!-- <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />   -->	
      			<input type="button" name="cmdIsiSemula" id="cmdIsiSemula" value="Kosongkan" onClick="kosongkan()"> 
    		</td>
  		</tr>
  		
  		
	</table>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

    <tr id="cetakOnlineKJP" >
        <td><a href="javascript:cetakOnlineKJP()" class="style2" ><font color="blue">Laporan Online KJP</font></a></td>
     </tr>
    <tr id="cetakStatusOnlineKJP">
        <td><a href="javascript:cetakStatusOnlineKJP()" class="style2" ><font color="blue">Laporan Status Online KJP</font></a></td>
      </tr>

    </table>
</fieldset>

<!-- Hidden -->
<input type="hidden" name="id_pejabat" value="$!id_pejabat">
<input type="hidden" name="id_daerah" value="$!id_daerah">
<input type="hidden" name="id_kementerian" id="$!id_kementerian">
<input type="hidden" name="tahun" value="$!tahun">
<input type="hidden" name="bulan" value="$!bulan">
<input type="hidden" name="jenisTempoh" value="$!jenisTempoh">
<input type="hidden" name="tahunSE" value="$!tahunSE">
<input type="hidden" name="bulanSE" value="$!bulanSE">

<script>

document.${formName}.socPejabat.value = "";
showhidereport();
function kosongkan() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.LaporanOnlineKJP";
	document.${formName}.submit();
}

function cetakOnlineKJP(){

	if(document.${formName}.socPejabat.value == ""){
	   	alert("Sila pilih \"Pejabat JKPTG\" terlebih dahulu.");
		document.${formName}.socPejabat.focus();
		return;
	
	}else if(document.${formName}.socTahun.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahun.focus();
		return;
	
	}else if(document.${formName}.socTahunSehingga.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahunSehingga.focus();
		return;
	}
	else{
		var id_negeri = document.${formName}.socPejabat.value;
		var mula_tahun = document.${formName}.socTahun.value;
		var akhir_tahun = document.${formName}.socTahunSehingga.value;
	
		var item = "ID_NEGERI="+id_negeri+"&MULA_TAHUN="+mula_tahun+"&AKHIR_TAHUN="+akhir_tahun;		
	 	
			var url = "../servlet/ekptg.report.ppt.LaporanOnlineKJP?"+item 
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		    hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();	    
	}
}

function cetakStatusOnlineKJP(){

	if(document.${formName}.selectKementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
		document.${formName}.selectKementerian.focus();
		return;
	}
	if(document.${formName}.socStatus.value == ""){
		/* alert("Sila pilih \"Status\" terlebih dahulu."); */
		document.${formName}.socStatus.focus();
		return;
	}
	else if(document.${formName}.socTahun.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahun.focus();
		return;
	
	}else if(document.${formName}.socTahunSehingga.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahunSehingga.focus();
		return;
	}
	else{
		var id_kementerian = document.${formName}.selectKementerian.value;
		var socStatus = document.${formName}.socStatus.value;
		var mula_tahun = document.${formName}.socTahun.value;
		if(mula_tahun=="")
		{
			mula_tahun = " ";
		}
		var akhir_tahun = document.${formName}.socTahunSehingga.value;
		if(akhir_tahun=="")
		{
			akhir_tahun = " ";
		}
		var element_socKementerian = document.getElementById("selectKementerian");
		var selectedTextKJP = element_socKementerian.options[element_socKementerian.selectedIndex].text;
		if(id_kementerian=="")
		{
			selectedTextKJP = " ";
		}
		
		var element_socStatus = document.getElementById("socStatus");
		var selectedTextStatus = element_socStatus.options[element_socStatus.selectedIndex].text;
		if(socStatus=="")
		{
			selectedTextStatus = " ";
		}
		
		
		var item = "ID_KEMENTERIAN="+id_kementerian+"&ID_STATUS="+socStatus+"&NAMA_STATUS="+selectedTextStatus+"&NAMA_KEMENTERIAN="+selectedTextKJP+"&MULA_TAHUN="+mula_tahun+"&AKHIR_TAHUN="+akhir_tahun;		
	 	
			var url = "../servlet/ekptg.report.ppt.LaporanStatusOnlineKJP?"+item 
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		    hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();	    
	}
}

function showDaerah(){
	 document.${formName}.command.value = "daerah";
	 document.${formName}.submit();
	}
function showhidereport()
{
	var kem = document.getElementById('selectKementerian').value;
	var stat = document.getElementById('socStatus').value;
	var pej = document.getElementById('socPejabat').value;
	var cetakOnlineKJP = false;
	var cetakStatusOnlineKJP = false;
	
	if(kem != "" && stat != "")
	{
		cetakStatusOnlineKJP = true;
	}	
	if(pej != "")
	{
		cetakOnlineKJP = true;
	}
	
	document.getElementById('cetakOnlineKJP').style.display = "none";
	document.getElementById('cetakStatusOnlineKJP').style.display = "none";
	//alert(" cetakOnlineKJP :: "+cetakOnlineKJP+" cetakStatusOnlineKJP :: "+cetakStatusOnlineKJP)
	
	if(cetakOnlineKJP == true)
	{
		document.getElementById('cetakOnlineKJP').style.display = "";
	}
	if(cetakStatusOnlineKJP == true)
	{
		document.getElementById('cetakStatusOnlineKJP').style.display = "";
	}
	
	if(cetakOnlineKJP == false && cetakStatusOnlineKJP == false)
	{
		document.getElementById('tableReport1').style.display = "none";
	}
	else
	{
		document.getElementById('tableReport1').style.display = "";
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