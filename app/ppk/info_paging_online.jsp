
<input type="hidden" id="flag_buka_info_paging" name="flag_buka_info_paging" value="$!flag_buka_info_paging" />

<table width="100%" border="0">
<tr>
<td align="right">
<input type="button" name="cdmPetunjuk" id="cdmPetunjuk" value="Papar Petunjuk" onclick="javascript:setTablePetunjuk('table_petunjuk','flag_buka_info_paging')"/>

</td>
</tr>
</table>
                                       
<fieldset  id="table_petunjuk" style="display:none;" >
<div id="info_warna"></div>
<div id="info_nombor"></div>
<script>
parent.document.getElementById("info_warna").innerHTML="<div class=\"warning_online_ppk\"><b>Maksud Pada Warna Butang :<br><blink><img src='../img/2disable.png' alt='' border='0' title='Pendaftaran Permohonan' /></blink> : Merujuk kepada skrin yang masih belum boleh dicapai oleh pengguna.<br /><blink><img src='../img/2enable.png' alt='' border='0' title='Pendaftaran Permohonan' /></blink> : Merujuk kepada skrin yang sudah boleh dicapai oleh pengguna. <br /><blink><img src='../img/2current.png' alt='' border='0' title='Pendaftaran Permohonan' /></blink> : Merujuk kepada skrin semasa.</b></div>";
</script>
<script>
parent.document.getElementById("info_nombor").innerHTML="<div class=\"warning_online_ppk\"><b>Maksud Pada Nombor Butang :<br><blink><img src='../img/1current.png' alt='' border='0' title='Pendaftaran Permohonan' /></blink> : Merujuk kepada skrin Senarai Semak Pilihan Tujuan Permohonan.<br /><blink><img src='../img/2current.png' alt='' border='0' title='Pendaftaran Permohonan' /></blink> : Merujuk kepada skrin Pendaftaran Maklumat Asas Permohonan. <br /><blink><img src='../img/3current.png' alt='' border='0' title='Pendaftaran Permohonan' /></blink> : Merujuk kepada skrin Borang P.</b></div>";
</script>
</fieldset>



<script>
function setTablePetunjuk(id,id_hid){
document.getElementById(id_hid).value="tutup";
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		document.getElementById(id_hid).value="buka";	
		document.getElementById('cdmPetunjuk').value="Tutup Petunjuk";	
		
			
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
		document.getElementById(id_hid).value="tutup";
		document.getElementById('cdmPetunjuk').value="Papar Petunjuk";	
	}
}
</script>

<script>

if(document.getElementById('flag_buka_info_paging').value == "buka")
{
document.getElementById('table_petunjuk').style.display="block";
document.getElementById('flag_buka_info_paging').value="buka";
document.getElementById('cdmPetunjuk').value="Tutup Petunjuk";	
}
else if(document.getElementById(id_hid).value == "tutup")
{
document.getElementById('table_petunjuk').style.display="none";
document.getElementById('flag_buka_info_paging').value="tutup";
document.getElementById('cdmPetunjuk').value="Papar Petunjuk";	
}
else
{
document.getElementById('table_petunjuk').style.display="none";
document.getElementById('flag_buka_info_paging').value="tutup";
document.getElementById('cdmPetunjuk').value="Papar Petunjuk";	
}


</script>