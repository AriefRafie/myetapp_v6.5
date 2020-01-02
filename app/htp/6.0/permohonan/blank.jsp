#if($SuccessMesej!="")   
<div class="info" id="success_listPerbicaraan" align="left">
    #if($SuccessMesej=="PermohonTukarPegawai")	    
    Permohonan Tukar Pegawai Berjaya Didaftarkan  
    <script >
	$jquery("#icon_tukarpegawai_multiple").html(">> ");
	document.getElementById("flag_tukarpegawai_multiple").value = "close";
	//$jquery("#view_tukarpegawai_multiple").html("");
	showHideCheckBox('listID_PERBICARAAN');
	//$jquery("#success_listPerbicaraan").show().delay(3000).fadeOut();
	</script>  
    #elseif($SuccessMesej=="KelulusanTukarPegawai")	    
    Kelulusan Permohonan Tukar Pegawai Berjaya Didaftarkan  
    <script >
	$jquery("#icon_tukarpegawaiKPP_multiple").html(">> ");
	document.getElementById("flag_tukarpegawaiKPP_multiple").value = "close";
	//$jquery("#view_tukarpegawai_multiple").html("");
	showHideCheckBoxKPP('listkID_TUKARPEGAWAI');
	//$jquery("#success_listPerbicaraan").show().delay(3000).fadeOut();
	</script>  
    #end
    <script >
	$jquery("#success_listPerbicaraan").show().delay(3000).fadeOut();
	</script>
</div>
#end