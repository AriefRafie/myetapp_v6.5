<script>
//getTotalFolder("$ID_PANDANGANUNDANGUTAMA");
//getTotalLampiran("$ID_PANDANGANUNDANGUTAMA");
</script>

#if($TOTAL_LAMPIRAN != "0" && $TOTAL_LAMPIRAN!= "")
<span onClick="doDivAjaxCall$formname('divSubLampiran$ID_DOCTRM','showLampiran','FLAG_LAMP_OPENCLOSE='+$jquery('#HID_OPENCLOSE_LAMP_$ID_DOCTRM').val()+'&TAJUK=$TAJUK&FLAG_OPENCLOSE=&ID_DOCTRMUTAMA=$ID_DOCTRMUTAMA&ID_REFER=$ID_REFER&ID_DOCTRM=$ID_DOCTRM&LAYER=$LAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val()+'&JUMLAH_LAMP=$TOTAL_LAMPIRAN');" >
	<span class="font_tajuk_sub" >
	&nbsp;<!--<span id="iconLampiranOpenClose_$LFS.ID_PANDANGANUNDANG"><b>></b></span>--><u>[$TOTAL_LAMPIRAN Dokumen]</u>
    </span>
</span>
#else				
	<span class="font_tajuk_sub_wc" >
	&nbsp;<!--<span id="iconLampiranOpenClose_$LFS.ID_PANDANGANUNDANG"></span>-->[$TOTAL_LAMPIRAN Dokumen]
    </span>		   
#end
			