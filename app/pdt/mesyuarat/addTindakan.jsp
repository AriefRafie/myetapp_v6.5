<table width="95%" align="center" border="0"  cellspacing="2" cellpadding="2">
<tr>
<td>
<fieldset>
<table width="100%" align="center" border="0" cellspacing="1" cellpadding="">
<tr>
<td valign="top"  width="1%"></td>
<td valign="top"  width="28%"></td>
<td valign="top"  width="1%"></td>
<td valign="top"  width="70%"></td>
</tr>
<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                
                 #set($dropBahagian = "dropBahagian_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                 <datalist id="$dropBahagian">                 
                 </datalist> 
                                
                
                #set($spantindakanaddfield = "editBahagian_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
#set($spanvalidateTindakan = "validate_tindakan_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
#set($fieldvalidateTindakan = "fieldvalidateTindakan_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
<input list="$dropBahagian" type="text" size="50" id="$spantindakanaddfield" name="$spantindakanaddfield" style="text-transform:uppercase;" 
onKeyUp="doDivAjaxCall3$formname('$dropBahagian','dropBahagian','SEARCH='+this.value);doDivAjaxCall3$formname('$spanvalidateTindakan','validateTindakan','BAHAGIAN='+this.value+'&ID_REFER=&ID_MESYUARATTINDAKAN=&ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&BIL=$BIL');"
onBlur="doDivAjaxCall3$formname('$spanvalidateTindakan','validateTindakan','BAHAGIAN='+this.value+'&ID_REFER=&ID_MESYUARATTINDAKAN=&ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&BIL=$BIL');"
value="" >
<span id="$spanvalidateTindakan"><input type="hidden" id="$fieldvalidateTindakan" name="$fieldvalidateTindakan" value="N" ></span>
                </td>
</tr>
<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Emel Bahagian (To)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                
                
                 #set($id_pk = $ID_MESYUARATCONTENT+"_X000000X")
                 #set($dropEmel = "dropEmel_"+$id_pk)
                 <datalist id="$dropEmel">                 
                 </datalist> 
                 
                 
                <div  style="width:50%; background-color:white">  
                #set($listEMELTINDAKAN_TO = "listEMELTINDAKAN_TO_"+$id_pk)             
                <span id="$listEMELTINDAKAN_TO"  style="width:100%" >                       
                </span>
                #set($spantindakanEmelfield = "editEmelBahagian_"+$id_pk)
                #set($spantindakanEmelfield_temp = "temp_editEmelBahagian_"+$id_pk)
                <textarea  style="display:none"  id="$spantindakanEmelfield" name="$spantindakanEmelfield" ></textarea>
               
                
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
				<td valign="top" >
				</td>			
				<td valign="top" >
				Emel Bahagian (Cc)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                
                
                 #set($id_pk = $ID_MESYUARATCONTENT+"_X000000X")
                 #set($dropEmelCC = "dropEmelCC_"+$id_pk)
                 <datalist id="$dropEmelCC">                 
                 </datalist> 
                 
                 
                <div  style="width:50%; background-color:white">  
                #set($listEMELTINDAKAN_CC = "listEMELTINDAKAN_CC_"+$id_pk)             
                <span id="$listEMELTINDAKAN_CC"  style="width:100%" >                       
                </span>
                #set($spantindakanEmelCCfield = "editEmelCCBahagian_"+$id_pk)
                #set($spantindakanEmelCCfield_temp = "temp_editEmelCCBahagian_"+$id_pk)
                <textarea  style="display:none"  id="$spantindakanEmelCCfield" name="$spantindakanEmelCCfield" ></textarea>
               
                
                <input  size="30" type="text" list="$dropEmelCC" id="$spantindakanEmelCCfield_temp"   
                name="$spantindakanEmelCCfield_temp"  placeholder="Masukkan Emel..."
				value="" 
onKeyUp="doDivAjaxCall3$formname('$dropEmelCC','dropEmelCC','ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATTINDAKAN=$ID_MESYUARATTINDAKAN&SEARCH='+this.value);showListEmel(event,this,'$listEMELTINDAKAN_CC','$id_pk','$spantindakanEmelCCfield');"
onFocus="doDivAjaxCall3$formname('$dropEmelCC','dropEmelCC','ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATTINDAKAN=$ID_MESYUARATTINDAKAN&SEARCH='+this.value);"  
onBlur="doDivAjaxCall3$formname('$dropEmelCC','dropEmelCC','ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&ID_MESYUARATTINDAKAN=$ID_MESYUARATTINDAKAN&SEARCH='+this.value);"  
oninput="showListEmelOninput(event,this,'$listEMELTINDAKAN_CC','$id_pk','$dropEmelCC','$spantindakanEmelCCfield');"
                >
                </div>
                <div class="blink"><font color="blue">Sila taip ';' atau tekan 'Enter' selepas kemasukan maklumat emel!</font></div>
	
                
                
                </td>
</tr>



<tr>
<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Status Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($spantindakanRadioStatusfield = "editRadioStatusBahagian_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                #set($spantindakanStatusfield = "editStatusBahagian_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                 
                <input type="radio" id="$spantindakanRadioStatusfield" 
				name="$spantindakanRadioStatusfield" 
				onClick="fromRadioToText(this,this.value,'$spantindakanStatusfield')" 
				value="1" checked="checked">Dalam Tindakan
				&nbsp;&nbsp;
				<input type="radio" id="$spantindakanRadioStatusfield" 
				name="$spantindakanRadioStatusfield" 
				onClick="fromRadioToText(this,this.value,'$spantindakanStatusfield')" 
				value="2">Selesai
                
                
                
                
                
               
<input type="hidden" size="50" id="$spantindakanStatusfield" name="$spantindakanStatusfield" 
value="1" >
                </td>
</tr>
<tr>
				<td valign="top" >
							
				</td>			
				<td valign="top" >
				Maklumbalas
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($spancatatan_COUNT = "editCatatanBahagianCount_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
                #set($spantindakanCatatanfield = "editCatatanBahagian_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)

  <textarea style="text-transform:uppercase;" name="$spantindakanCatatanfield" 
				 id="$spantindakanCatatanfield" cols="50" rows="5" 
		 onKeyup="check_length(this,4000,'$spancatatan_COUNT');" ></textarea>
         <span id="$spancatatan_COUNT"  ></span>
                </td>
</tr>
<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
                
#set($divLoadList = "")
#set($divFolderTambah = "")
#if($ID_MESYUARATCONTENT!="")
	#set($divLoadList = "divTindakan"+$ID_MESYUARATCONTENT)
	#set($divFolderTambah = "divTindakanTambah"+$ID_MESYUARATCONTENT)
#else
	#set($divLoadList = "divTindakan"+$ID_MESYUARATCONTENT)
	#set($divFolderTambah = "divTindakanTambah"+$ID_MESYUARATCONTENT)
#end

<input type="button" id="cmdSimpanTindakan" name="cmdSimpanTindakan" value="Simpan" onClick="if(validateCheckTindakan('$ID_MESYUARATUTAMA','$ID_MESYUARATCONTENT','$ID_MESYUARATTINDAKAN')==true){this.value='Sila Tunggu!'; this.disabled = true; doDivAjaxCall$formname('$divLoadList','SimpanTindakan','FLAG_SUB_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_REFER=&ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&LAYER=$LAYER&AUTOLOAD=N&carianTerperinci=&carianBahagian=');   }" >
<input type="button" id="cmdBatalSimpanSub" name="cmdBatalTindakan" value="Batal" onClick="document.getElementById('$divFolderTambah').innerHTML=''" >


                </td>
                </tr>
                
</table>





</fieldset>
</td>
</tr>
</table>  			