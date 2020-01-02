
#if($TOTAL_SUB != "0" && $TOTAL_SUB != "")
		<span onClick="doDivAjaxCall$formname('divSubFolder$ID_MESYUARATCONTENT','showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$ID_MESYUARATCONTENT').val()+'&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_REFER=$ID_MESYUARATCONTENT&ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&LAYER=$LAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val()+'&JUMLAH_SUB=$TOTAL_SUB');" >
			<span class="font_tajuk_sub" >
			&nbsp;<!--<span id="iconFolderOpenClose_$ID_MESYUARATCONTENT"><b>></b></span>--><u>[$TOTAL_SUB Sub Dir]</u>
		    </span>
		</span>
#else		
			<span class="font_tajuk_sub_wc" >
			&nbsp;<!--<span id="iconFolderOpenClose_$ID_MESYUARATCONTENT"></span>-->[$TOTAL_SUB Sub Dir]
		    </span>		   
#end

<script>
//getTotalFolder("$ID_MESYUARATUTAMA");
//getTotalTindakan("$ID_MESYUARATUTAMA");

/*
if('$ID_MESYUARATCONTENT'!="")
{
	document.getElementById('HID_OPENCLOSE_SUB_$ID_MESYUARATCONTENT').value = '$FLAG_SUB_OPENCLOSE';
}
*/
</script>

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