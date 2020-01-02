
#if($TOTAL_SUB != "0" && $TOTAL_SUB != "")
		<span onClick="doDivAjaxCall$formname('divSubFolder$ID_PANDANGANUNDANG','showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$ID_PANDANGANUNDANG').val()+'&TAJUK=&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$ID_PANDANGANUNDANGUTAMA&ID_REFER=$ID_PANDANGANUNDANG&ID_PANDANGANUNDANG=$ID_PANDANGANUNDANG&LAYER=$LAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val()+'&JUMLAH_SUB=$TOTAL_SUB');" >
			<span class="font_tajuk_sub" >
			&nbsp;<!--<span id="iconFolderOpenClose_$ID_PANDANGANUNDANG"><b>></b></span>--><u>[$TOTAL_SUB Sub Dir]</u>
		    </span>
		</span>
#else		
			<span class="font_tajuk_sub_wc" >
			&nbsp;<!--<span id="iconFolderOpenClose_$ID_PANDANGANUNDANG"></span>-->[$TOTAL_SUB Sub Dir]
		    </span>		   
#end

<script>
//getTotalFolder("$ID_PANDANGANUNDANGUTAMA");
//getTotalLampiran("$ID_PANDANGANUNDANGUTAMA");

/*
if('$ID_PANDANGANUNDANG'!="")
{
	document.getElementById('HID_OPENCLOSE_SUB_$ID_PANDANGANUNDANG').value = '$FLAG_SUB_OPENCLOSE';
}
*/
</script>

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