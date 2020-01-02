




<tr id="$trTindakan">
	<td class="$rowCss"  align="center" valign="top">$BIL</td>
    
	<td class="$rowCss" align="left" valign="top">
	<input type="hidden" id="tempFieldTindakan$ID_MESYUARATUTAMA" name="tempFieldTindakan$ID_MESYUARATUTAMA" >
	#set($trTindakan = "trTindakan_"+$viewTindakan.ID_MESYUARATTINDAKAN)
	  #set($spantindakanaddfield = "editBahagian_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
    #set($spanvalidateTindakan = "validate_tindakan_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
    #set($fieldvalidateTindakan = "fieldvalidateTindakan_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
    
    
    
                 #set($dropBahagian = "dropBahagian_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                 <datalist id="$dropBahagian">                 
                 </datalist> 
    
    <input type="text" list="$dropBahagian" id="$spantindakanaddfield" name="$spantindakanaddfield" style="width:100%;text-transform:uppercase;" 
    onKeyUp="doDivAjaxCall3$formname('$dropBahagian','dropBahagian','SEARCH='+this.value);doDivAjaxCall3$formname('$spanvalidateTindakan','validateTindakan','BAHAGIAN='+this.value+'&ID_REFER=&ID_MESYUARATTINDAKAN=$ID_MESYUARATTINDAKAN&ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&BIL=$BIL&rowCss=$rowCss');"
    
     onBlur="doDivAjaxCall3$formname('$spanvalidateTindakan','validateTindakan','BAHAGIAN='+this.value+'&ID_REFER=&ID_MESYUARATTINDAKAN=$ID_MESYUARATTINDAKAN&ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&BIL=$BIL&rowCss=$rowCss');"
     
    value="$viewTindakan.BAHAGIAN" >
    <span id="$spanvalidateTindakan"><input type="hidden" id="$fieldvalidateTindakan" name="$fieldvalidateTindakan" value="N" ></span>
	
	
	
	</td>
    <td class="$rowCss"  align="left" valign="top">   
    
    
    <table width="100%" cellpadding="0" cellspacing="0" >
    <tr>
    <td width="1%" align="center" valign="top"><font color="red" >*</font></td>
    <td width="5%" align="left" valign="top">To</td>
    <td width="1%" align="center" valign="top">:</td>
    <td width="93%" align="left" valign="top">
   #set($dropEmel = "dropEmel_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                 #set($id_pk = $ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                 <datalist id="$dropEmel">                 
                 </datalist> 
                 
                <div  style="width:100%; background-color:white">  
                #set($listEMELTINDAKAN_TO = "listEMELTINDAKAN_TO_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)             
                <span id="$listEMELTINDAKAN_TO"  style="width:100%" >  
                $viewTindakan.EMEL                     
                </span>
                #set($spantindakanEmelfield = "editEmelBahagian_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                #set($spantindakanEmelfield_temp = "temp_editEmelBahagian_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                <textarea  style="display:none"  id="$spantindakanEmelfield" name="$spantindakanEmelfield" >$viewTindakan.EMEL</textarea>
               
                
                <input  size="30" type="text" list="$dropEmel" id="$spantindakanEmelfield_temp"   
                name="$spantindakanEmelfield_temp"  placeholder="Masukkan Emel..."
				value="" 
onKeyUp="doDivAjaxCall3$formname('$dropEmel','dropEmel','ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATTINDAKAN=$ID_MESYUARATTINDAKAN&SEARCH='+this.value);showListEmel(event,this,'$listEMELTINDAKAN_TO','$id_pk','$spantindakanEmelfield');"
onFocus="doDivAjaxCall3$formname('$dropEmel','dropEmel','ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATTINDAKAN=$ID_MESYUARATTINDAKAN&SEARCH='+this.value);"  
onBlur="doDivAjaxCall3$formname('$dropEmel','dropEmel','ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATTINDAKAN=$ID_MESYUARATTINDAKAN&SEARCH='+this.value);"  
oninput="showListEmelOninput(event,this,'$listEMELTINDAKAN_TO','$id_pk','$dropEmel','$spantindakanEmelfield');"
                >
                </div>
                
    </td>
    </tr>
    <tr>
    <td>
    <td align="left" valign="top">Cc</td>
    <td align="center" valign="top">:</td>
    <td align="left" valign="top">
     #set($dropEmelCC = "dropEmelCC_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                 #set($id_pk = $ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                 <datalist id="$dropEmelCC">                 
                 </datalist> 
                 
                <div  style="width:100%; background-color:white">  
                #set($listEMELTINDAKAN_CC = "listEMELTINDAKAN_CC_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)             
                <span id="$listEMELTINDAKAN_CC"  style="width:100%" >  
                $viewTindakan.EMEL_CC                     
                </span>
                #set($spantindakanEmelCCfield = "editEmelCCBahagian_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                #set($spantindakanEmelCCfield_temp = "temp_editEmelCCBahagian_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                <textarea  style="display:none"  id="$spantindakanEmelCCfield" name="$spantindakanEmelCCfield" >$viewTindakan.EMEL_CC</textarea>
               
                
                <input  size="30" type="text" list="$dropEmelCC" id="$spantindakanEmelCCfield_temp"   
                name="$spantindakanEmelCCfield_temp"  placeholder="Masukkan Emel..."
				value="" 
onKeyUp="doDivAjaxCall3$formname('$dropEmelCC','dropEmelCC','ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATTINDAKAN=$ID_MESYUARATTINDAKAN&SEARCH='+this.value);showListEmel(event,this,'$listEMELTINDAKAN_CC','$id_pk','$spantindakanEmelCCfield');"
onFocus="doDivAjaxCall3$formname('$dropEmelCC','dropEmelCC','ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATTINDAKAN=$ID_MESYUARATTINDAKAN&SEARCH='+this.value);"  
onBlur="doDivAjaxCall3$formname('$dropEmelCC','dropEmelCC','ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATTINDAKAN=$ID_MESYUARATTINDAKAN&SEARCH='+this.value);"  
oninput="showListEmelOninput(event,this,'$listEMELTINDAKAN_CC','$id_pk','$dropEmelCC','$spantindakanEmelfield');"
                >
	
    
    </td>
    </tr>
    <tr>
    <td>
    <td align="left" valign="top"></td>
    <td align="center" valign="top"></td>
    <td align="left" valign="top">
    <div class="blink"><font color="blue">Sila taip ';' atau tekan 'Enter' selepas kemasukan maklumat emel dan sila tekan butang 'Simpan' jika ada perubahan maklumat!</font></div>

    </td>
    </tr>    
    </table>
    
    
     
                 
    
    

    </td>
    <td class="$rowCss" align="left" valign="top">
    
    
    #set($spancatatan_COUNT = "editCatatanBahagianCount_"+$viewTindakan.ID_MESYUARATCONTENT+"_"+$viewTindakan.ID_MESYUARATTINDAKAN)
                #set($spantindakanCatatanfield = "editCatatanBahagian_"+$viewTindakan.ID_MESYUARATCONTENT+"_"+$viewTindakan.ID_MESYUARATTINDAKAN)

  <textarea style="width:100%;text-transform:uppercase;" name="$spantindakanCatatanfield" 
				 id="$spantindakanCatatanfield"  
		 onKeyup="check_length(this,4000,'$spancatatan_COUNT');" >$viewTindakan.CATATAN</textarea>
         <span id="$spancatatan_COUNT"  ></span>
    
    
    </td>
    <td class="$rowCss" align="left" valign="top">
    
     #set($spantindakanStatusfield = "editStatusBahagian_"+$viewTindakan.ID_MESYUARATCONTENT+"_"+$viewTindakan.ID_MESYUARATTINDAKAN)
     #set($spantindakanRadioStatusfield = "editRadioStatusBahagian_"+$viewTindakan.ID_MESYUARATCONTENT+"_"+$viewTindakan.ID_MESYUARATTINDAKAN)
                
                #set($radioStatusCheck1="")
                #set($radioStatusCheck2="")
                
                #if($viewTindakan.ID_STATUS=="1")
                    #set($radioStatusCheck1="checked")
                    #set($radioStatusCheck2="")
                #elseif($viewTindakan.ID_STATUS=="2")
                	#set($radioStatusCheck1="")
                    #set($radioStatusCheck2="checked")
                #end
                
                <input type="radio" id="$spantindakanRadioStatusfield" 
				name="$spantindakanRadioStatusfield" 
				onClick="fromRadioToText(this,this.value,'$spantindakanStatusfield')" 
				value="1" $radioStatusCheck1>Dalam Tindakan
				<br />
				<input type="radio" id="$spantindakanRadioStatusfield" 
				name="$spantindakanRadioStatusfield" 
				onClick="fromRadioToText(this,this.value,'$spantindakanStatusfield')" 
				value="2" $radioStatusCheck2>Selesai
                
                
                
                
                
                
<input type="hidden" id="$spantindakanStatusfield" name="$spantindakanStatusfield" 
value="$viewTindakan.ID_STATUS" >
    
    
    </td>
    <td class="$rowCss" align="center" valign="top">
    
  <input type="button" id="cmdSimpanTindakan" name="cmdSimpanTindakan" value="Simpan" onClick="if(validateCheckTindakan('$viewTindakan.ID_MESYUARATUTAMA','$viewTindakan.ID_MESYUARATCONTENT','$viewTindakan.ID_MESYUARATTINDAKAN')==true){this.value='Sila Tunggu!'; this.disabled = true; doDivAjaxCall$formname('$trTindakan','SimpanTindakan','ID_MESYUARATTINDAKAN=$viewTindakan.ID_MESYUARATTINDAKAN&FLAG_SUB_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$viewTindakan.ID_MESYUARATUTAMA&ID_REFER=&ID_MESYUARATCONTENT=$viewTindakan.ID_MESYUARATCONTENT&LAYER=$LAYER&AUTOLOAD=N&carianTerperinci=&carianBahagian=&BIL=$BIL&rowCss=$rowCss');   }" >
<input type="button" id="cmdBatalSimpanSub" name="cmdBatalTindakan" value="Batal" onClick="doDivAjaxCall$formname('$trTindakan','editTindakan','ID_MESYUARATTINDAKAN=$viewTindakan.ID_MESYUARATTINDAKAN&ID_MESYUARATCONTENT=$viewTindakan.ID_MESYUARATCONTENT&ID_MESYUARATUTAMA=$viewTindakan.ID_MESYUARATUTAMA&BIL=$BIL&rowCss=$rowCss');" >
    </td>
	</tr>




