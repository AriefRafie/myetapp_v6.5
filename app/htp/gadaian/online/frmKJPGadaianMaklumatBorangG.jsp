
<fieldset><legend><strong>MAKLUMAT BORANG G</strong></legend>
	<table width="100%" border="0">
  
	#foreach ($borangG in $borangGBean)  
		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($pagemode == "baru" || $pagemode == "kemaskini") * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left"> Tarikh Terima  </div>
				        	</div>
				        </td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%"><input name="txdTarikhTerima" type="text" id="txdTarikhTerima" onblur="check_date(this)" value="$borangG.TarikhTerima" size="10" maxlength="10" $classDis $mode /> 
                        
                    #if ($pagemode == "baru" || $pagemode == "kemaskini") 
                        <img src="../img/calendar.gif" alt="calendar" border="0" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');" />
                    
                     #end   
                        
                        
                    </td>
		</tr>
        
        <tr>
                	  <td valign="top">&nbsp;</td>
                	  <td valign="top">	
                	  	<div align="right" class="labelinput">
				        	<div align="left">Keterangan</div>
				        	</div>
				        </td>
                	  <td valign="top">:</td>
                	  <td><textarea name="txtKeterangan" id="txtKeterangan" cols="45" rows="3" onkeyup="this.value=this.value.toUpperCase();" $mode $classDis >$borangG.keterangan</textarea></td>
 		</tr>
                  
	#end                
		<tr>
        	<td valign="top">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
     	</tr>						
	</table>

	
	<input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
	<input type="hidden" name="idFail" value="$IdFail">
	<input type="hidden" name="noFail" value="$NoFail">
	<input type="hidden" name="idPermohonan" value="$IdPermohonan">  
	<input type="hidden" name="idPihakberkepentingan" value="$IdPihakberkepentingan">  
	<input type="hidden" name="idBebanan" value="$IdBebanan">  
	<input type="hidden" name="idHTPGadaian" value="$idHTPGadaian">  
</fieldset>

	#if($pagemode == 'baru' || $pagemode == "kemaskini")	
  	<table width="100%">
    	<tr>
       		<td><span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span></td>
   	    </tr>
  	</table>
	#end
	
	<p>	
	#if($pagemode == 'baru')
		<div align="center">
     		<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGTG_SimpanBorangG()">
            <input type="reset" class="stylobutton100" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="javascript:fGTG_BatalBorangG()">
		</div>
	#elseif($pagemode == "kemaskini")
		<div align="center">
        	<input type="button" class="stylobutton100" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick="javascript:fGTG_SimpanUpdateBorangG()"/>
            
		</div>
    #elseif($pagemode == 'view')
		<div align="center">
        	<input type="button" class="stylobutton100" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGTG_KemaskiniBorangG()">
            
      	</div>                          
	#end
	</p>
                        