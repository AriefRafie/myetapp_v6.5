
<fieldset><legend><strong>TARIKH TEMUJANJI</strong></legend>
	<table width="100%" border="0">
  
	##foreach ($geran in $selesaiBean)  
		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($pagemode == "baru" || $pagemode == "kemaskini") * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left"> Tarikh  </div>
				        	</div>
				        </td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
				        	<input name="txdTarikhTerima" type="text" id="txdTarikhTerima" onblur="check_date(this);checkDate(this);" value="$!selesaiBean.tarikhSelesai" size="10" maxlength="10" $classDis $mode /> 
                        
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
                	  <td>
                	  	<textarea name="txtKeterangan" id="txtKeterangan" cols="45" rows="3" maxlength="49" onkeyup="this.value=this.value.toUpperCase();" $mode $classDis >$!selesaiBean.catatan</textarea>
                	  </td>
 		</tr>
                  
	##end                
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
     		<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanSelesai()">
            <!--<input type="reset" class="stylobutton" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="javascript:fGTG_BatalGeran()"> -->
            <input type="reset" class="stylobutton100" name="cmdBatal" value="Kosongkan" >
		</div>
	#elseif($pagemode == "kemaskini")
		<div align="center">
        	<input type="button" class="stylobutton100" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick="javascript:kemaskiniSimpanSelesai()"/>
            <!-- <input type="reset" class="stylobutton" name="cmdBatal2" id="cmdBatal2" $btnNamePemilik onclick="javascript:fGTG_BatalGeran()"/> -->
		</div>
    #elseif($pagemode == 'view')
		<div align="center">
        	<input type="button" class="stylobutton100" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniSelesai()">
            <!-- <input type="button" class="stylobutton" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGTG_KembaliGeran()"> -->
      	</div>                          
	#end
	</p>
                        