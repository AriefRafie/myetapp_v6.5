
<script>	

document.getElementById('divSubLampiranTambah$ID_DOCTRM').innerHTML='';

if('$ID_DOCTRM'!="" && '$AUTOLOAD'=="N")
{
	//alert('x1 : '+'$ID_PANDANGANUNDANG');
	document.getElementById('HID_OPENCLOSE_LAMP_'+'$ID_DOCTRM').value = '$FLAG_LAMP_OPENCLOSE';
	//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_PANDANGANUNDANG').value);
}
</script>


#if($listFolderLampiran.size()>0)	
<table width="98%" align="center" border="0" class="classFade"  cellspacing="0" cellpadding="0">
<tr>
<td>
<fieldset>
<legend>Lampiran/Dokumen</legend>


<table width="100%" align="center" border="0" cellspacing="1" cellpadding="">

	#foreach($LFL in $listFolderLampiran)
	<script>
	    document.getElementById('div_statsUtama$LFL.ID_DOCTRMUTAMA').style.display = "";
		var current_count_load = document.getElementById('countLampiran_$LFL.ID_DOCTRMUTAMA').value;
		current_count_load = parseInt(current_count_load)+1;
		document.getElementById('countLampiran_$LFL.ID_DOCTRMUTAMA').value = current_count_load;	
		document.getElementById('div_totalLampiranUtama$LFL.ID_DOCTRMUTAMA').innerHTML = current_count_load;
	</script>

	
	
	<tr>
		
	<td width="100%" align="left">
	<input type="hidden" id="tempFieldLampiran$LFL.ID_DOCTRMUTAMA" name="tempFieldLampiran$LFL.ID_DOCTRMUTAMA" >
	
	#set($span2 = "span2listFolderLampiran_"+$LFL.ID_DOCTRMLAMPIRAN+"_"+$LFL.BIL)
	#if($editable=="Y")
	<a href="javascript:doDivAjaxCall$formname('$span2','editLampiran','ID_DOCTRMLAMPIRAN=$LFL.ID_DOCTRMLAMPIRAN&ID_DOCTRM=$LFL.ID_DOCTRM&ID_DOCTRMUTAMA=$LFL.ID_DOCTRMUTAMA&BIL=$LFL.BIL');"><img title="Kemaskini Nama Sub Direktori" src="../img/edit.gif" border="0"></a>
	<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('divSubLampiran$LFL.ID_DOCTRM','deleteLampiran','NAMA_DOC=$LFL.NAMA_DOC&ID_DOCTRMLAMPIRAN=$LFL.ID_DOCTRMLAMPIRAN&FLAG_LAMP_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_DOCTRMUTAMA=$LFL.ID_DOCTRMUTAMA&ID_REFER=$LFL.ID_DOCTRM&ID_DOCTRM=$LFL.ID_DOCTRM&LAYER=&AUTOLOAD=N');}"><img title="Hapus Lampiran"  src="../img/hapus.gif" border="0"></a>
	#end
	<!-- PENAMBAHAN PAPAR TARIKH -->
	<span class="font_tajuk_sub" id="$span2" >$LFL.TARIKH_DAFTAR
	<u onClick="paparDoc($LFL.ID_DOCTRMLAMPIRAN);">$LFL.NAMA_DOC</u> 
	</span>
	
	
	
	</td>
	</tr>
	
	#if(($AUTOLOAD=="Y" || $FlagCari=="Y"))		
	<script>
	highlight_item($jquery('#carianTerperinciLampiran').val(),'span2listFolderLampiran'+'_'+'$LFL.ID_DOCTRMLAMPIRAN'+'_'+'$LFL.BIL'); 
	</script>			
    #end
	
	<script>	
		if(('$AUTOLOAD'=="Y" || '$FlagCari'=="Y"))
		{
			
			if('$LFL.ID_DOCTRM'!="")
			{
				//alert('x1 : '+'$ID_DOCTRM');
				document.getElementById('HID_OPENCLOSE_LAMP_'+'$LFL.ID_DOCTRM').value = '$FLAG_LAMP_OPENCLOSE';
				//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_DOCTRM').value);
			}
			
		}
	</script>
	
	
	#end
	
</table>
</fieldset>
</td>
</tr>
</table>
#end


<script>
getTotalLampiran("$ID_DOCTRMUTAMA");
if('$AUTOLOAD'!="Y")
{
	$jquery(document).ready(function () {
		  doDivAjaxCall$formname('countLampiran$ID_DOCTRM','countLampiran','FLAG_LAMP_OPENCLOSE='+$jquery('#HID_OPENCLOSE_LAMP_$ID_DOCTRM').val()+'&ID_DOCTRMUTAMA=$ID_DOCTRMUTAMA&ID_DOCTRM=$ID_DOCTRM&LAYER=$LAYER&AUTOLOAD=N&TOTAL_LAMPIRAN=$listFolderLampiran.size()');
	});
}
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

