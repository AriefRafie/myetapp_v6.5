

<tr>
<td align="center" valign="top" class="$rowCss">
$BIL
</td>

<td class="$rowCss"  align="left" valign="top"> 
#set($spanmainviewfield = "editMainDirField_"+$viewMainFoler.ID_MESYUARATUTAMA)
#set($spanvalidateMaindir = "validate_maindir_"+$viewMainFoler.ID_MESYUARATUTAMA)
#set($fieldvalidateMainDir = "fieldvalidateMainDir_"+$viewMainFoler.ID_MESYUARATUTAMA)
#set($spanMain = "span1listFolderUtama"+$BIL)
<font color="red" >*</font>	
<input type="text" id="$spanmainviewfield" name="$spanmainviewfield" style="text-transform:uppercase; width: 95%; box-sizing: border-box;" 
value="$viewMainFoler.TAJUK_MESYUARAT" 
onKeyUp="doDivAjaxCall3$formname('$spanvalidateMaindir','validateMainDir','ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA');"
>

  <input type="hidden" id="countFolder_$viewMainFoler.ID_MESYUARATUTAMA" name="countFolder_$viewMainFoler.ID_MESYUARATUTAMA" value = "0"  >
			   <input type="hidden" id="countActFolder_$viewMainFoler.ID_MESYUARATUTAMA" name="countActFolder_$viewMainFoler.ID_MESYUARATUTAMA" value = "$viewMainFoler.TOTAL_FOLDER"  >
			   <input type="hidden" id="countTindakan_$viewMainFoler.ID_MESYUARATUTAMA" name="countTindakan_$viewMainFoler.ID_MESYUARATUTAMA" value = "0"  >
			   <input type="hidden" id="countActLampiran_$viewMainFoler.ID_MESYUARATUTAMA" name="countActLampiran_$viewMainFoler.ID_MESYUARATUTAMA" value = "$viewMainFoler.TOTAL_TINDAKAN"  >
			   <input type="hidden" id="HID_OPENCLOSE_$viewMainFoler.ID_MESYUARATUTAMA" name="HID_OPENCLOSE_$viewMainFoler.ID_MESYUARATUTAMA" value = "$HID_OPENCLOSE"  >
			   <input type="hidden" id="HID_MAXLAYER_$viewMainFoler.ID_MESYUARATUTAMA" name="HID_MAXLAYER_$viewMainFoler.ID_MESYUARATUTAMA" value = "$viewMainFoler.MAX_LAYER"  >

<span id="$spanvalidateMaindir">
<input type="hidden" id="$fieldvalidateMainDir" name="$fieldvalidateMainDir" value="N" >
</span>
</td>
<td class="$rowCss"  align="center" valign="top"> 
#set($spanmainviewfield = "editTahunField_"+$viewMainFoler.ID_MESYUARATUTAMA)
<font color="red" >*</font>	
<input type="text" size="10" id="$spanmainviewfield" name="$spanmainviewfield" style="text-transform:uppercase; width: 75%; box-sizing: border-box;" 
value="$viewMainFoler.TAHUN" maxLength="4"
onKeyUp="numberOnly(this,this.value);doDivAjaxCall3$formname('$spanvalidateMaindir','validateMainDir','ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA');"
>
</td>
<td class="$rowCss"  align="center" valign="top"> 
#set($spanmainviewfield = "editBilanganField_"+$viewMainFoler.ID_MESYUARATUTAMA)
<font color="red" >*</font>	
<input type="text" size="10" id="$spanmainviewfield" name="$spanmainviewfield" style="text-transform:uppercase; width: 75%; box-sizing: border-box;" 
value="$viewMainFoler.BILANGAN" 
onKeyUp="numberOnly(this,this.value);doDivAjaxCall3$formname('$spanvalidateMaindir','validateMainDir','ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA');"
>
</td>

<td class="$rowCss"  align="left" valign="top"> 
 #set($editRadioStatusMesyuarat = "editRadioStatusMesyuarat_"+$viewMainFoler.ID_MESYUARATUTAMA)
                #set($editStatusMesyuarat = "editStatusMesyuarat_"+$viewMainFoler.ID_MESYUARATUTAMA)
                 
                <input type="radio" id="$editRadioStatusMesyuarat" 
				name="$editRadioStatusMesyuarat" 
				onClick="fromRadioToText(this,this.value,'$editStatusMesyuarat')" 
				value="A" #if($viewMainFoler.FLAG_AKTIF=='A')checked="checked"#end>Aktif
				<BR />
				<input type="radio" id="$editRadioStatusMesyuarat" 
				name="$editRadioStatusMesyuarat" 
				onClick="fromRadioToText(this,this.value,'$editStatusMesyuarat')" 
				value="T" #if($viewMainFoler.FLAG_AKTIF=='T')checked="checked"#end>Tangguh
                      
<input type="hidden" size="50" id="$editStatusMesyuarat" name="$editStatusMesyuarat" 
value="$viewMainFoler.FLAG_AKTIF" >
</td>
<td  class="$rowCss" align="left" valign="top" >
<table width="100%" cellpadding="0" cellspacing="0" >
<tr>
<td width="1%" align="center" valign="top"><font color="red" >*</font></td>
<td width="5%" align="left" valign="top">PIC</td>
<td width="1%" align="center" valign="top">:</td>
<td width="93%" align="left" valign="top">
<datalist id="dropPIC_$viewMainFoler.ID_MESYUARATUTAMA" ></datalist>  
<input style="text-transform:uppercase; width: 100%; box-sizing: border-box; margin-bottom:2px;"  type="text" list="dropPIC_$viewMainFoler.ID_MESYUARATUTAMA" id="editPIC_$viewMainFoler.ID_MESYUARATUTAMA" 
				name="editPIC_$viewMainFoler.ID_MESYUARATUTAMA" 
				value="$viewMainFoler.PIC" 
                 onKeyUp="doDivAjaxCall3$formname('dropPIC_$viewMainFoler.ID_MESYUARATUTAMA','dropPIC','ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&SEARCH='+this.value);"
onFocus="doDivAjaxCall3$formname('dropPIC_$viewMainFoler.ID_MESYUARATUTAMA','dropPIC','ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&SEARCH='+this.value);" 
				>

</td>
</tr>
<tr>
<td align="center" valign="top"><font color="red" >*</font></td>
<td  align="left" valign="top">Tel</td>
<td  align="center" valign="top">:</td>
<td align="left" valign="top">
<datalist id="dropTelPIC_$viewMainFoler.ID_MESYUARATUTAMA" >                 
                </datalist> 
<input style="text-transform:uppercase;  margin-bottom:2px;" list="dropTelPIC_$viewMainFoler.ID_MESYUARATUTAMA" width="100%"  type="text" id="editTEL_PIC_$viewMainFoler.ID_MESYUARATUTAMA" 
				name="editTEL_PIC_$viewMainFoler.ID_MESYUARATUTAMA" 
				value="$viewMainFoler.TEL_PIC" 
                  onKeyUp="RemoveNonNumeric_V3(this,this.value);doDivAjaxCall3$formname('dropTelPIC_$viewMainFoler.ID_MESYUARATUTAMA','dropTelPIC','ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&SEARCH='+this.value);"
onFocus="doDivAjaxCall3$formname('dropTelPIC_$viewMainFoler.ID_MESYUARATUTAMA','dropTelPIC','ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&SEARCH='+this.value);"  
				>
</td>
</tr>
<tr>
<td align="center" valign="top"><font color="red" >*</font></td>
<td  align="left" valign="top">Emel</td>
<td  align="center" valign="top">:</td>
<td align="left" valign="top">

	#set($id_pk = $viewMainFoler.ID_MESYUARATUTAMA)
 				<datalist id="dropEmelPIC_$id_pk" >                 
                </datalist>   
                <div  style="width:100%; background-color:white">  
                          
                <span id="listEMELPIC$id_pk"  style="width:100%"  >   
                $viewMainFoler.EMEL_PIC              
                </span>
                <textarea style="display:none"  id="editEMEL_PIC_$id_pk" name="editEMEL_PIC_$id_pk" >$viewMainFoler.EMEL_PIC</textarea>       
                        
                <input  size="30" type="text" list="dropEmelPIC_$id_pk"     id="temp_editEMEL_PIC_$id_pk"   
                name="temp_editEMEL_PIC_$id_pk" placeholder="Masukkan Emel..."
				value="" 
onKeyUp="doDivAjaxCall3$formname('dropEmelPIC_$id_pk','dropEmelPIC','ID_MESYUARATUTAMA=$id_pk&SEARCH='+this.value);showListEmel(event,this,'listEMELPIC$id_pk','$id_pk','editEMEL_PIC_$id_pk');"
onFocus="doDivAjaxCall3$formname('dropEmelPIC_$id_pk','dropEmelPIC','ID_MESYUARATUTAMA=$id_pk&SEARCH='+this.value);"  
oninput="showListEmelOninput(event,this,'listEMELPIC$id_pk','$id_pk','dropEmelPIC_$id_pk','editEMEL_PIC_$id_pk');"   >
</div>	
<div class="blink"><font color="blue">Sila taip ';' atau tekan 'Enter' selepas kemasukan maklumat emel dan sila tekan butang 'Simpan' jika ada perubahan maklumat!</font></div>

</td>
</tr>

</table>

</td>
<td class="$rowCss"  align="center" valign="top"> 
<input type="hidden" id="BIL_MAIN_$viewMainFoler.ID_MESYUARATUTAMA" name="BIL_MAIN_$viewMainFoler.ID_MESYUARATUTAMA" value = "$BIL"  >
<input type="hidden" id="rowCss_MAIN_$viewMainFoler.ID_MESYUARATUTAMA" name="rowCss_MAIN_$viewMainFoler.ID_MESYUARATUTAMA" value = "$rowCss"  >
            
<input type="button" id="cmdSimpanMain" name="cmdSimpanMain" value="Simpan" onClick="if(validateCheckMainDir('$viewMainFoler.ID_MESYUARATUTAMA')==true){doDivAjaxCall$formname('div_rowFolderUtama$ID_MESYUARATUTAMA','SimpanMainDir','ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&BIL=$BIL&rowCss=$rowCss');}" >
<input type="button" id="cmdBatalSimpanMain" name="cmdBatalSimpanMain" value="Batal" onClick="doDivAjaxCall$formname('div_rowFolderUtama$ID_MESYUARATUTAMA','editMainDir','ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&BIL=$BIL&rowCss=$rowCss');" >

</td>
</tr>