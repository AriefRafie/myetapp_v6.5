<script>
//getTotalFolder("$ID_MESYUARATUTAMA");
//getTotalTindakan("$ID_MESYUARATUTAMA");
</script>

#if($TOTAL_TINDAKAN != "0" && $TOTAL_TINDAKAN!= "")
<span onClick="doDivAjaxCall$formname('divTindakan$ID_MESYUARATCONTENT','showTindakan','FLAG_TINDAKAN_OPENCLOSE='+$jquery('#HID_OPENCLOSE_TINDAKAN_$ID_MESYUARATCONTENT').val()+'&TAJUK=$TAJUK&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_REFER=$ID_REFER&ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&LAYER=$LAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val()+'&JUMLAH_TINDAKAN=$TOTAL_TINDAKAN');" >
	<span class="font_tajuk_sub" style="cursor:pointer" >
	&nbsp;<!--<span id="iconLampiranOpenClose_$LFS.ID_MESYUARATCONTENT"><b>></b></span>--><u>[$TOTAL_TINDAKAN Tindakan Keseluruhan]</u>
    </span>
</span>
#else				
	<span class="font_tajuk_sub_wc" >
	&nbsp;<!--<span id="iconLampiranOpenClose_$LFS.ID_MESYUARATCONTENT"></span>-->[$TOTAL_TINDAKAN Tindakan Keseluruhan]
    </span>		   
#end
			