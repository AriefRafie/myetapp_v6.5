
<script>
if( $jquery('#'+'div_rowFolderUtama$viewTajukUtama.ID_MESYUARATUTAMA').length ) 
{
	window.scrollTo(0, $jquery('#'+'div_rowFolderUtama$viewTajukUtama.ID_MESYUARATUTAMA').offset().top);
}
</script>

<tr >
<td align="left" valign="top" >
	<fieldset>
	<legend>
	
	#if($viewTajukUtama.ID_MESYUARATUTAMA!= "")
	Kemaskini Maklumat Mesyuarat
	#else
	Kemasukan Maklumat Mesyuarat
	#end
	</legend>
		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Tajuk Mesyuarat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				#set($spanvalidateMaindir = "validate_maindir_")
				#set($fieldvalidateMainDir = "fieldvalidateMainDir_")                
                #set($spanmainviewfield = "editMainDirField_")
               
				
				<input style="text-transform:uppercase;" size="50" type="text" id="$spanmainviewfield" 
				name="$spanmainviewfield" 
				value="$viewTajukUtama.TAJUK_MESYUARAT" 
				onKeyUp="doDivAjaxCall3$formname('$spanvalidateMaindir','validateMainDir','ID_MESYUARATUTAMA=');"

				>				
				<span id="$spanvalidateMaindir"><input type="hidden" id="$fieldvalidateMainDir" name="$fieldvalidateMainDir" value="N" ></span>
					
				</td>
				
			</tr>
            
            <tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Tahun
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
                #set($spanmainviewfield = "editTahunField_")
<input type="text" size="10" id="$spanmainviewfield" name="$spanmainviewfield" style="text-transform:uppercase;" 
value="$viewTajukUtama.TAHUN" maxLength="4"
onKeyUp="numberOnly(this,this.value);doDivAjaxCall3$formname('$spanvalidateMaindir','validateMainDir','ID_MESYUARATUTAMA=');"
>		
				</td>				
			</tr>
            
            <tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Bilangan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#set($spanmainviewfield = "editBilanganField_")
<input type="text" size="10" id="$spanmainviewfield" name="$spanmainviewfield" style="text-transform:uppercase;" 
value="$viewTajukUtama.BILANGAN" 
onKeyUp="numberOnly(this,this.value);doDivAjaxCall3$formname('$spanvalidateMaindir','validateMainDir','ID_MESYUARATUTAMA=');"
>
				</td>				
			</tr>
            
            
            
<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Status Mesyuarat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($editRadioStatusMesyuarat = "editRadioStatusMesyuarat_")
                #set($editStatusMesyuarat = "editStatusMesyuarat_")
                 
                <input type="radio" id="$editRadioStatusMesyuarat" 
				name="$editRadioStatusMesyuarat" 
				onClick="fromRadioToText(this,this.value,'$editStatusMesyuarat')" 
				value="A" checked="checked">Aktif
				&nbsp;&nbsp;
				<input type="radio" id="$editRadioStatusMesyuarat" 
				name="$editRadioStatusMesyuarat" 
				onClick="fromRadioToText(this,this.value,'$editStatusMesyuarat')" 
				value="T">Tangguh
                      
<input type="hidden" size="50" id="$editStatusMesyuarat" name="$editStatusMesyuarat" 
value="A" >
                </td>
</tr>




<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				PIC
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                <datalist id="dropPIC_" > 
                </datalist>  
               <input style="text-transform:uppercase;" list="dropPIC_"   size="50" type="text" id="editPIC_" 
				name="editPIC_" 
				value="$viewTajukUtama.PIC" 
                 onKeyUp="doDivAjaxCall3$formname('dropPIC_','dropPIC','ID_MESYUARATUTAMA=&SEARCH='+this.value);"
onFocus="doDivAjaxCall3$formname('dropPIC_','dropPIC','ID_MESYUARATUTAMA=&SEARCH='+this.value);" 
				>				
               
                </td>
</tr>

<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Tel PIC
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                <datalist id="dropTelPIC_" >                 
                </datalist> 
               <input style="text-transform:uppercase;"  list="dropTelPIC_" size="50" type="text" id="editTEL_PIC_" 
				name="editTEL_PIC_" value="$viewTajukUtama.TEL_PIC"                 
                onKeyUp="RemoveNonNumeric_V3(this,this.value);doDivAjaxCall3$formname('dropTelPIC_','dropTelPIC','ID_MESYUARATUTAMA=&SEARCH='+this.value);"
				onFocus="doDivAjaxCall3$formname('dropTelPIC_','dropTelPIC','ID_MESYUARATUTAMA=&SEARCH='+this.value);"                
				>				
               
                </td>
</tr>


<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Emel PIC
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" > 
                #set($id_pk = "X000000X")               
                <datalist id="dropEmelPIC_$id_pk" >                 
                </datalist>   
                <div  style="width:60%; background-color:white">  
                          
                <span id="listEMELPIC$id_pk"  style="width:100%"  >   
                </span>
                <textarea style="display:none"  id="editEMEL_PIC_$id_pk" name="editEMEL_PIC_$id_pk" ></textarea>       
                        
                <input  size="30" type="text" list="dropEmelPIC_$id_pk" id="temp_editEMEL_PIC_$id_pk"   
                name="temp_editEMEL_PIC_$id_pk" placeholder="Masukkan Emel..."
				value="" 
onKeyUp="doDivAjaxCall3$formname('dropEmelPIC_$id_pk','dropEmelPIC','ID_MESYUARATUTAMA=&SEARCH='+this.value);showListEmel(event,this,'listEMELPIC$id_pk','$id_pk','editEMEL_PIC_$id_pk');"
onFocus="doDivAjaxCall3$formname('dropEmelPIC_$id_pk','dropEmelPIC','ID_MESYUARATUTAMA=$&SEARCH='+this.value);"  
oninput="showListEmelOninput(event,this,'listEMELPIC$id_pk','$id_pk','dropEmelPIC_$id_pk','editEMEL_PIC_$id_pk');"   >
</div>	
<div class="blink"><font color="blue">Sila taip ';' atau tekan 'Enter' selepas kemasukan maklumat emel dan sila tekan butang 'Simpan' jika ada perubahan maklumat!</font></div>

	
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
				<input type="button" id="BTNSAVE$viewTajukUtama.ID_MESYUARATUTAMA" name="BTNSAVE$viewTajukUtama.ID_MESYUARATUTAMA" 
				onClick="if(validateCheckMainDir('')==true){doDivAjaxCall$formname('div_senaraiUtama','saveTajukUtama','ID_MESYUARATUTAMA=');}" value="Simpan" > 
	   			
	   			<input type="button" id="BTNBTL$viewTajukUtama.ID_MESYUARATUTAMA" name="BTNBTL$viewTajukUtama.ID_MESYUARATUTAMA" 
				onClick="doDivAjaxCall$formname('div_rowFolderUtama','batalTajukUtama','ID_MESYUARATUTAMA=$viewTajukUtama.ID_MESYUARATUTAMA');" value="Batal" > 
	   			
	   			<input type="button" id="BTNCLS$viewTajukUtama.ID_MESYUARATUTAMA" name="BTNCLS$viewTajukUtama.ID_MESYUARATUTAMA" 
				onClick="doDivAjaxCall$formname('div_rowFolderUtama','closeTajukUtama','ID_MESYUARATUTAMA=$viewTajukUtama.ID_MESYUARATUTAMA');" value="Tutup" > 
	   			
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>
</td>		
</tr>
