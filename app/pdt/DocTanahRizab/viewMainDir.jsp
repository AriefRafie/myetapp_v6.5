#set($spanMain = "span1listFolderUtama"+$BIL)
<span id="$spanMain" > 
			   <span onClick="doDivAjaxCall$formname('div_viewFolderUtama$viewMainFoler.ID_DOCTRMUTAMA','showAllFolder','LAYER=1&NAMA_FOLDER=$viewMainFoler.TAJUK&FLAG_OPENCLOSE='+$jquery('#HID_OPENCLOSE_$viewMainFoler.ID_DOCTRMUTAMA').val()+'&ID_DOCTRMUTAMA=$viewMainFoler.ID_DOCTRMUTAMA&FlagCari=N&AUTOLOAD=N');" >
			   <span class="font_tajuk_utama" ><u>$viewMainFoler.TAJUK</u></span>
			   </span>
			   
</span>