
#set($trTindakan = "trTindakan_"+$viewTindakan.ID_MESYUARATTINDAKAN)
	
	<tr id="$trTindakan">
	<td class="$rowCss"  align="center" valign="top">$BIL</td>
    
	<td class="$rowCss" align="left" valign="top">
	<input type="hidden" id="tempFieldTindakan$viewTindakan.ID_MESYUARATUTAMA" name="tempFieldTindakan$viewTindakan.ID_MESYUARATUTAMA" >
	
	#set($span2 = "span2listFolderTindakan_"+$viewTindakan.ID_MESYUARATTINDAKAN+"_"+$BIL)

	<span class="font_tajuk_sub" id="$span2" >
	$viewTindakan.BAHAGIAN
    
   
	</span>
	
	
	
	</td>
    <td class="$rowCss"  align="left" valign="top">
    
    <table width="100%" cellpadding="0" cellspacing="0" >
    <tr>
    <td width="5%" align="left" valign="top">To</td>
    <td width="1%" align="center" valign="top">:</td>
    <td width="94%" align="left" valign="top">
    #set($listEMELTINDAKAN_TO = "listEMELTINDAKAN_TO_"+$viewTindakan.ID_MESYUARATCONTENT+"_"+$viewTindakan.ID_MESYUARATTINDAKAN)  
    <span id="$listEMELTINDAKAN_TO"  style="width:100%" >  
    $viewTindakan.EMEL
    </span>
    <script>
		   	 hideByTag('$listEMELTINDAKAN_TO','strong');
	</script>
    </td>
    </tr>
    #if($viewTindakan.EMEL_CC!="")
    <tr>
    <td align="left" valign="top">Cc</td>
    <td align="center" valign="top">:</td>
    <td align="left" valign="top">
    #set($listEMELTINDAKAN_CC = "listEMELTINDAKAN_CC_"+$viewTindakan.ID_MESYUARATCONTENT+"_"+$viewTindakan.ID_MESYUARATTINDAKAN)  
    <span id="$listEMELTINDAKAN_CC"  style="width:100%" >  
    $viewTindakan.EMEL_CC
    </span>
    <script>
		   	 hideByTag('$listEMELTINDAKAN_CC','strong');
	</script>
    </td>
    </tr>
    #end    
    </table>
    
   
    
    
    
    
    </td>
    <td class="$rowCss" align="left" valign="top">$viewTindakan.CATATAN</td>
    <td class="$rowCss" align="left" valign="top"> 
    #if($viewTindakan.ID_STATUS == "1")
    <font color='red'>$viewTindakan.STATUS</font>
        <br />
        Emel Dihantar : $viewTindakan.COUNT_EMEL
        #if($viewTindakan.EMEL_ERROR!="")
        <br />
        Ralat Emel :<br />
        <font color='red'>$viewTindakan.EMEL_ERROR</font>
        #end
    #else
    <font color='blue'>$viewTindakan.STATUS</font>
    #end
    
    
    </td>
    <td class="$rowCss" align="center" valign="top">
    	<a href="javascript:doDivAjaxCall$formname('$trTindakan','editTindakan','ID_MESYUARATTINDAKAN=$viewTindakan.ID_MESYUARATTINDAKAN&ID_MESYUARATCONTENT=$viewTindakan.ID_MESYUARATCONTENT&ID_MESYUARATUTAMA=$viewTindakan.ID_MESYUARATUTAMA&BIL=$BIL&rowCss=$rowCss');"><img title="Kemaskini Nama Sub Direktori" src="../img/edit.gif" border="0"></a>
	<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('divTindakan$viewTindakan.ID_MESYUARATCONTENT','deleteTindakan','NAMA_DOC=$viewTindakan.NAMA_DOC&ID_MESYUARATTINDAKAN=$viewTindakan.ID_MESYUARATTINDAKAN&FLAG_TINDAKAN_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$viewTindakan.ID_MESYUARATUTAMA&ID_REFER=$viewTindakan.ID_MESYUARATCONTENT&ID_MESYUARATCONTENT=$viewTindakan.ID_MESYUARATCONTENT&LAYER=&AUTOLOAD=N');}"><img title="Hapus Lampiran"  src="../img/hapus.gif" border="0"></a>
	#if($viewTindakan.ID_STATUS == "1")
    <a href="javascript:if(confirm('Emel akan dihantar. Adakah Anda Pasti?')){ doDivAjaxCall$formname('trTindakan_$viewTindakan.ID_MESYUARATTINDAKAN','emelTindakan','ID_MESYUARATTINDAKAN=$viewTindakan.ID_MESYUARATTINDAKAN&FLAG_SUB_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$viewTindakan.ID_MESYUARATUTAMA&ID_REFER=&ID_MESYUARATCONTENT=$viewTindakan.ID_MESYUARATCONTENT&LAYER=&AUTOLOAD=N&carianTerperinci=&carianBahagian=&BIL=$BIL&rowCss=$rowCss');}"><img title="Emel"  src="../img/emel.gif" border="0"></a>
	#end
    </td>
	</tr>
    
   <script>
   doDivAjaxCall$formname('ShowDalamTindakanContent_$viewTindakan.ID_MESYUARATCONTENT','ShowDalamTindakanContent','ID_MESYUARATCONTENT=$viewTindakan.ID_MESYUARATCONTENT&ID_MESYUARATUTAMA=$viewTindakan.ID_MESYUARATUTAMA');
   </script>
			   
