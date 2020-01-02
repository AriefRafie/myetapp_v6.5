#if($JUMLAH_SUB!="")
<script>
/*
document.getElementById('div_statsUtama$ID_MESYUARATUTAMA').style.display = "";
var current_count_total = document.getElementById('countFolder_$ID_MESYUARATUTAMA').value
current_count_total = current_count_total - parseInt('$JUMLAH_SUB');
document.getElementById('countFolder_$ID_MESYUARATUTAMA').value = current_count_total;	
document.getElementById('div_totalDirUtama$ID_MESYUARATUTAMA').innerHTML = current_count_total;
*/
</script>
#end

#if($TOTAL_SUB != "0")
<script>
/*
	if('$LAYER'!="1")
	{
		if('$FLAG_SUB_OPENCLOSE'=="OPEN" && '$ID_MESYUARATCONTENT'!="")
		{
			document.getElementById('iconFolderOpenClose_$ID_MESYUARATCONTENT').innerHTML = "<b><</b>";
		}
		else if('$FLAG_SUB_OPENCLOSE'=="CLOSE" && '$ID_MESYUARATCONTENT'!="")
		{
			document.getElementById('iconFolderOpenClose_$ID_MESYUARATCONTENT').innerHTML = "<b>></b>";
		}
		else
		{
			document.getElementById('iconFolderOpenClose_$ID_MESYUARATCONTENT').innerHTML = "";
		}
	}
*/
</script>
#else
<script>
/*
	if('$LAYER'!="1")
	{		
		document.getElementById('iconFolderOpenClose_$ID_MESYUARATCONTENT').innerHTML = "";		
	}
*/
</script>
#end	

<script>

document.getElementById('HID_OPENCLOSE_$ID_MESYUARATUTAMA').value = '$FLAG_OPENCLOSE';
if('$ID_MESYUARATCONTENT'!="")
{
	document.getElementById('HID_OPENCLOSE_SUB_$ID_MESYUARATCONTENT').value = '$FLAG_SUB_OPENCLOSE';
}

getTotalFolder("$ID_MESYUARATUTAMA");
getTotalTindakan("$ID_MESYUARATUTAMA");
</script>

<script>
/*
var current_load_folder = document.getElementById('countFolder_$ID_MESYUARATUTAMA').value;
var total_load_folder = document.getElementById('countActFolder_$ID_MESYUARATUTAMA').value;
var current_load_lampiran = document.getElementById('countTindakan_$ID_MESYUARATUTAMA').value;
var total_load_lampiran = document.getElementById('countActLampiran_$ID_MESYUARATUTAMA').value;
if((current_load_folder == total_load_folder) && (current_load_lampiran==total_load_lampiran))
{
	
	if( $jquery('#'+'div_rowFolderUtama$ID_MESYUARATUTAMA').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_rowFolderUtama$ID_MESYUARATUTAMA').offset().top);
		document.getElementById('countFolder_$ID_MESYUARATUTAMA').value = 0;
		document.getElementById('countTindakan_$ID_MESYUARATUTAMA').value = 0;
	}
	
}
*/
</script>