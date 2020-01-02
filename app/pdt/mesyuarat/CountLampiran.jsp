
#if($TOTAL_LAMPIRAN != "0" && $TOTAL_LAMPIRAN!= "")
<span onClick="doDivAjaxCall$formname('divSubLampiran$ID_MESYUARATUTAMA','showLampiran','FLAG_LAMP_OPENCLOSE='+$jquery('#HID_OPENCLOSE_LAMP_$ID_MESYUARATUTAMA').val()+'&TAJUK=$TAJUK&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_REFER=&LAYER=$LAYER&AUTOLOAD=N&carianLampiran=&JUMLAH_LAMP=$TOTAL_LAMPIRAN');" >
	<span class="font_tajuk_sub" >
	&nbsp;<!--<span id="iconLampiranOpenClose_$LFS.ID_PANDANGANUNDANG"><b>></b></span>--><u>[$TOTAL_LAMPIRAN Dokumen]</u>
    </span>
</span>
#else				
	<span class="font_tajuk_sub_wc" >
	&nbsp;<!--<span id="iconLampiranOpenClose_$LFS.ID_PANDANGANUNDANG"></span>-->[$TOTAL_LAMPIRAN Dokumen]
    </span>		   
#end
			