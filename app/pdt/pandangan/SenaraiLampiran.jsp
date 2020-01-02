
<script>	

document.getElementById('divSubLampiranTambah$ID_PANDANGANUNDANG').innerHTML='';

if('$ID_PANDANGANUNDANG'!="" && '$AUTOLOAD'=="N")
{
	//alert('x1 : '+'$ID_PANDANGANUNDANG');
	document.getElementById('HID_OPENCLOSE_LAMP_'+'$ID_PANDANGANUNDANG').value = '$FLAG_LAMP_OPENCLOSE';
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
	    document.getElementById('div_statsUtama$LFL.ID_PANDANGANUNDANGUTAMA').style.display = "";
		var current_count_load = document.getElementById('countLampiran_$LFL.ID_PANDANGANUNDANGUTAMA').value;
		current_count_load = parseInt(current_count_load)+1;
		document.getElementById('countLampiran_$LFL.ID_PANDANGANUNDANGUTAMA').value = current_count_load;	
		document.getElementById('div_totalLampiranUtama$LFL.ID_PANDANGANUNDANGUTAMA').innerHTML = current_count_load;
	</script>

	
	
	<tr>
		
	<td width="100%" align="left">
	<input type="hidden" id="tempFieldLampiran$LFL.ID_PANDANGANUNDANGUTAMA" name="tempFieldLampiran$LFL.ID_PANDANGANUNDANGUTAMA" >
	
	#set($span2 = "span2listFolderLampiran_"+$LFL.ID_PANDANGANLAMPIRAN+"_"+$LFL.BIL)
	<a href="javascript:doDivAjaxCall$formname('$span2','editLampiran','ID_PANDANGANLAMPIRAN=$LFL.ID_PANDANGANLAMPIRAN&ID_PANDANGANUNDANG=$LFL.ID_PANDANGANUNDANG&ID_PANDANGANUNDANGUTAMA=$LFL.ID_PANDANGANUNDANGUTAMA&BIL=$LFL.BIL');"><img title="Kemaskini Nama Sub Direktori" src="../img/edit.gif" border="0"></a>
	<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('divSubLampiran$LFL.ID_PANDANGANUNDANG','deleteLampiran','NAMA_DOC=$LFL.NAMA_DOC&ID_PANDANGANLAMPIRAN=$LFL.ID_PANDANGANLAMPIRAN&FLAG_LAMP_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$LFL.ID_PANDANGANUNDANGUTAMA&ID_REFER=$LFL.ID_PANDANGANUNDANG&ID_PANDANGANUNDANG=$LFL.ID_PANDANGANUNDANG&LAYER=&AUTOLOAD=N');}"><img title="Hapus Lampiran"  src="../img/hapus.gif" border="0"></a>
	
	<span class="font_tajuk_sub" id="$span2" >
	<u onClick="paparDoc($LFL.ID_PANDANGANLAMPIRAN);">$LFL.NAMA_DOC</u>
	</span>
	
	
	
	</td>
	</tr>
	
	#if(($AUTOLOAD=="Y" || $FlagCari=="Y"))		
	<script>
	highlight_item($jquery('#carianTerperinciLampiran').val(),'span2listFolderLampiran'+'_'+'$LFL.ID_PANDANGANLAMPIRAN'+'_'+'$LFL.BIL'); 
	</script>			
    #end
	
	<script>	
		if(('$AUTOLOAD'=="Y" || '$FlagCari'=="Y"))
		{
			
			if('$LFL.ID_PANDANGANUNDANG'!="")
			{
				//alert('x1 : '+'$ID_PANDANGANUNDANG');
				document.getElementById('HID_OPENCLOSE_LAMP_'+'$LFL.ID_PANDANGANUNDANG').value = '$FLAG_LAMP_OPENCLOSE';
				//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_PANDANGANUNDANG').value);
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
getTotalLampiran("$ID_PANDANGANUNDANGUTAMA");
if('$AUTOLOAD'!="Y")
{
	$jquery(document).ready(function () {
		  doDivAjaxCall$formname('countLampiran$ID_PANDANGANUNDANG','countLampiran','FLAG_LAMP_OPENCLOSE='+$jquery('#HID_OPENCLOSE_LAMP_$ID_PANDANGANUNDANG').val()+'&ID_PANDANGANUNDANGUTAMA=$ID_PANDANGANUNDANGUTAMA&ID_PANDANGANUNDANG=$ID_PANDANGANUNDANG&LAYER=$LAYER&AUTOLOAD=N&TOTAL_LAMPIRAN=$listFolderLampiran.size()');
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

