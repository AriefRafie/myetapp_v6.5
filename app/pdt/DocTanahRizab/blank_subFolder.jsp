#if($JUMLAH_SUB!="")
<script>
/*
document.getElementById('div_statsUtama$ID_PANDANGANUNDANGUTAMA').style.display = "";
var current_count_total = document.getElementById('countFolder_$ID_PANDANGANUNDANGUTAMA').value
current_count_total = current_count_total - parseInt('$JUMLAH_SUB');
document.getElementById('countFolder_$ID_PANDANGANUNDANGUTAMA').value = current_count_total;	
document.getElementById('div_totalDirUtama$ID_PANDANGANUNDANGUTAMA').innerHTML = current_count_total;
*/
</script>
#end

#if($TOTAL_SUB != "0")
<script>
/*
	if('$LAYER'!="1")
	{
		if('$FLAG_SUB_OPENCLOSE'=="OPEN" && '$ID_PANDANGANUNDANG'!="")
		{
			document.getElementById('iconFolderOpenClose_$ID_PANDANGANUNDANG').innerHTML = "<b><</b>";
		}
		else if('$FLAG_SUB_OPENCLOSE'=="CLOSE" && '$ID_PANDANGANUNDANG'!="")
		{
			document.getElementById('iconFolderOpenClose_$ID_PANDANGANUNDANG').innerHTML = "<b>></b>";
		}
		else
		{
			document.getElementById('iconFolderOpenClose_$ID_PANDANGANUNDANG').innerHTML = "";
		}
	}
*/
</script>
#else
<script>
/*
	if('$LAYER'!="1")
	{		
		document.getElementById('iconFolderOpenClose_$ID_PANDANGANUNDANG').innerHTML = "";		
	}
*/
</script>
#end	

<script>

document.getElementById('HID_OPENCLOSE_$ID_DOCTRMUTAMA').value = '$FLAG_OPENCLOSE';
if('$ID_DOCTRM'!="")
{
	document.getElementById('HID_OPENCLOSE_SUB_$ID_DOCTRM').value = '$FLAG_SUB_OPENCLOSE';
}

getTotalFolder("$ID_DOCTRMUTAMA");
getTotalLampiran("$ID_DOCTRMUTAMA");
</script>

<script>
/*
var current_load_folder = document.getElementById('countFolder_$ID_PANDANGANUNDANGUTAMA').value;
var total_load_folder = document.getElementById('countActFolder_$ID_PANDANGANUNDANGUTAMA').value;
var current_load_lampiran = document.getElementById('countLampiran_$ID_PANDANGANUNDANGUTAMA').value;
var total_load_lampiran = document.getElementById('countActLampiran_$ID_PANDANGANUNDANGUTAMA').value;
if((current_load_folder == total_load_folder) && (current_load_lampiran==total_load_lampiran))
{
	
	if( $jquery('#'+'div_rowFolderUtama$ID_PANDANGANUNDANGUTAMA').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_rowFolderUtama$ID_PANDANGANUNDANGUTAMA').offset().top);
		document.getElementById('countFolder_$ID_PANDANGANUNDANGUTAMA').value = 0;
		document.getElementById('countLampiran_$ID_PANDANGANUNDANGUTAMA').value = 0;
	}
	
}
*/
</script>