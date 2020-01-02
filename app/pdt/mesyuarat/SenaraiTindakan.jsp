
<script>	

document.getElementById('divTindakanTambah$ID_MESYUARATCONTENT').innerHTML='';

if('$ID_MESYUARATCONTENT'!="" && '$AUTOLOAD'=="N")
{
	//alert('x1 : '+'$ID_MESYUARATCONTENT');
	document.getElementById('HID_OPENCLOSE_TINDAKAN_'+'$ID_MESYUARATCONTENT').value = '$FLAG_TINDAKAN_OPENCLOSE';
	//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_MESYUARATCONTENT').value);
}
</script>


#if($listFolderTindakan.size()>0)	
<table width="95%" align="center" border="0" class="classFade"  cellspacing="2" cellpadding="2">
<tr>
<td>
<fieldset>

<table width="100%" align="center" border="0" cellspacing="1" cellpadding="">
<tr class="table_header" >
           <td   align="center" valign="top" width="3%" >No.</td> 
		   <td   align="left" valign="top" width="15%" >Bahagian</td> 
           <td   align="left" valign="top" width="32%">Emel</td>
           <td   align="left" valign="top" width="20%">Maklumbalas</td>
           <td   align="left" valign="top" width="20%">Status</td> 
           <td   align="center" valign="top" width="10%">Tindakan</td> 
	</tr>

	#foreach($LFL in $listFolderTindakan)
	<script>
	    document.getElementById('div_statsUtama$LFL.ID_MESYUARATUTAMA').style.display = "";
		var current_count_load = document.getElementById('countTindakan_$LFL.ID_MESYUARATUTAMA').value;
		current_count_load = parseInt(current_count_load)+1;
		document.getElementById('countTindakan_$LFL.ID_MESYUARATUTAMA').value = current_count_load;	
		document.getElementById('div_totalTindakanUtama$LFL.ID_MESYUARATUTAMA').innerHTML = current_count_load;
	</script>

	#set($trTindakan = "trTindakan_"+$LFL.ID_MESYUARATTINDAKAN)
	
	<tr id="$trTindakan">
	<td class="$LFL.rowCss"  align="center" valign="top">$LFL.BIL</td>
    
	<td class="$LFL.rowCss" align="left" valign="top">
	<input type="hidden" id="tempFieldTindakan$LFL.ID_MESYUARATUTAMA" name="tempFieldTindakan$LFL.ID_MESYUARATUTAMA" >
	
	#set($span2 = "span2listFolderTindakan_"+$LFL.ID_MESYUARATTINDAKAN+"_"+$LFL.BIL)

	<span class="font_tajuk_sub" id="$span2" >
	$LFL.BAHAGIAN
	</span>
	
	
	
	</td>
    <td class="$LFL.rowCss"  align="left" valign="top">
    
    <table width="100%" cellpadding="0" cellspacing="0" >
    <tr>
    <td width="5%" align="left" valign="top">To</td>
    <td width="1%" align="center" valign="top">:</td>
    <td width="94%" align="left" valign="top">
    #set($listEMELTINDAKAN_TO = "listEMELTINDAKAN_TO_"+$LFL.ID_MESYUARATCONTENT+"_"+$LFL.ID_MESYUARATTINDAKAN)  
    <span id="$listEMELTINDAKAN_TO"  style="width:100%" >  
    $LFL.EMEL
    </span>
    <script>
		   	 hideByTag('$listEMELTINDAKAN_TO','strong');
	</script>
    </td>
    </tr>
    #if($LFL.EMEL_CC!="")
    <tr>
    <td align="left" valign="top">Cc</td>
    <td align="center" valign="top">:</td>
    <td align="left" valign="top">
    #set($listEMELTINDAKAN_CC = "listEMELTINDAKAN_CC_"+$LFL.ID_MESYUARATCONTENT+"_"+$LFL.ID_MESYUARATTINDAKAN)  
    <span id="$listEMELTINDAKAN_CC"  style="width:100%" >  
    $LFL.EMEL_CC
    </span>
    <script>
		   	 hideByTag('$listEMELTINDAKAN_CC','strong');
	</script>
    
    </td>
    </tr>
    #end
    
    </table>
    
    
   
    </td>
    <td class="$LFL.rowCss" align="left" valign="top">$LFL.CATATAN</td>
    <td class="$LFL.rowCss" align="left" valign="top">
    #set($span3 = "span3listFolderTindakan_"+$LFL.ID_MESYUARATTINDAKAN+"_"+$LFL.BIL)
    <span id="$span3">
    
    #if($LFL.ID_STATUS == "1")
    <font color='red'>$LFL.STATUS</font>
        <br />
        Emel Dihantar : $LFL.COUNT_EMEL
        #if($LFL.EMEL_ERROR!="")
        <br />
        Ralat Emel :<br />
        <font color='red'><i>$LFL.EMEL_ERROR</i></font>
        #end
        #else
        <font color='blue'>$LFL.STATUS</font>
    #end   
    </span>
    
   
    </td>
    <td class="$LFL.rowCss" align="center" valign="top">
    	<a href="javascript:doDivAjaxCall$formname('$trTindakan','editTindakan','ID_MESYUARATTINDAKAN=$LFL.ID_MESYUARATTINDAKAN&ID_MESYUARATCONTENT=$LFL.ID_MESYUARATCONTENT&ID_MESYUARATUTAMA=$LFL.ID_MESYUARATUTAMA&BIL=$LFL.BIL&rowCss=$LFL.rowCss');"><img title="Kemaskini Nama Sub Direktori" src="../img/edit.gif" border="0"></a>
	<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('divTindakan$LFL.ID_MESYUARATCONTENT','deleteTindakan','NAMA_DOC=$LFL.NAMA_DOC&ID_MESYUARATTINDAKAN=$LFL.ID_MESYUARATTINDAKAN&FLAG_TINDAKAN_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$LFL.ID_MESYUARATUTAMA&ID_REFER=$LFL.ID_MESYUARATCONTENT&ID_MESYUARATCONTENT=$LFL.ID_MESYUARATCONTENT&LAYER=&AUTOLOAD=N');}"><img title="Hapus Lampiran"  src="../img/hapus.gif" border="0"></a>
    #if($LFL.ID_STATUS == "1")
    <a href="javascript:if(confirm('Emel akan dihantar. Adakah Anda Pasti?')){ doDivAjaxCall$formname('trTindakan_$LFL.ID_MESYUARATTINDAKAN','emelTindakan','ID_MESYUARATTINDAKAN=$LFL.ID_MESYUARATTINDAKAN&FLAG_SUB_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$LFL.ID_MESYUARATUTAMA&ID_REFER=&ID_MESYUARATCONTENT=$LFL.ID_MESYUARATCONTENT&LAYER=$LAYER&AUTOLOAD=N&carianTerperinci=&carianBahagian=&BIL=$LFL.BIL&rowCss=$LFL.rowCss');}"><img title="Emel"  src="../img/emel.gif" border="0"></a>
	#end
   
    </td>
	</tr>
	
	#if(($AUTOLOAD=="Y" || $FlagCari=="Y"))		
	<script>
	highlight_item($jquery('#carianBahagian').val(),'span2listFolderTindakan'+'_'+'$LFL.ID_MESYUARATTINDAKAN'+'_'+'$LFL.BIL'); 
	
	var statcarian = "";
	if($jquery('#carianStatus').val()=="1")
	{
		statcarian = "DALAM TINDAKAN";
	}
	else if($jquery('#carianStatus').val()=="2")
	{
		statcarian = "SELESAI";
	}
	highlight_item(statcarian,'span3listFolderTindakan'+'_'+'$LFL.ID_MESYUARATTINDAKAN'+'_'+'$LFL.BIL'); 
	</script>			
    #end
	
	<script>	
		if(('$AUTOLOAD'=="Y" || '$FlagCari'=="Y"))
		{
			
			if('$LFL.ID_MESYUARATCONTENT'!="")
			{
				//alert('x1 : '+'$ID_MESYUARATCONTENT');
				document.getElementById('HID_OPENCLOSE_TINDAKAN_'+'$LFL.ID_MESYUARATCONTENT').value = '$FLAG_TINDAKAN_OPENCLOSE';
				//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_MESYUARATCONTENT').value);
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

getTotalTindakan("$ID_MESYUARATUTAMA");

if('$AUTOLOAD'!="Y")
{
	$jquery(document).ready(function () {
		  doDivAjaxCall$formname('countTindakan$ID_MESYUARATCONTENT','countTindakan','FLAG_TINDAKAN_OPENCLOSE='+$jquery('#HID_OPENCLOSE_TINDAKAN_$ID_MESYUARATCONTENT').val()+'&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&LAYER=$LAYER&AUTOLOAD=N&TOTAL_TINDAKAN=$listFolderTindakan.size()');
		  
		   doDivAjaxCall$formname('ShowDalamTindakanContent_$ID_MESYUARATCONTENT','ShowDalamTindakanContent','ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA');
		  
	});
	
  
  
}
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

