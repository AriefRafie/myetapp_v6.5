<style type="text/css">
<!--
.q {
	color: #F00;
}
-->
</style>
<table width="100%" border="0">
 	<tr>
    	<td>
    	<fieldset><legend>PERJANJIAN JUAL BELI</legend>
		   	<table width="100%" border="0">
	      		<tr>
	            	<td width="50%" valign="top">
	                	<table width="100%" border="0">
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory"></span>
					        	</td>				        
	                        	<td width="40%">
	                            	<div align="left">
	                            		<span class="labelinput">No Kontrak</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="58%" class="labeldisplay" >
	 								<input type="text" name="txtNoKontrak" id="txtNoKontrak" size="30" maxlength="50" value="$!perjanjian.getHtpPerjanjian().getNoRujukanPerjanjian()" $mode $classDis onkeyup="this.value=this.value.toUpperCase();" />
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory"></span>
					        	</td>				        
	                        	<td width="40%">
	                            	<div align="left">
	                            		<span class="labelinput">Jumlah Hakmilik Tanah</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="58%" class="labeldisplay" >
	 								<input type="text" name="txtjumlahHakmilik" id="txtjumlahHakmilik" size="3" maxlength="3" value="$!perjanjian.getBilHakmilikBeli()" $mode $classDis onkeyup="validateNumber(this,this.value)" />
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory"></span>
					        	</td>				        
	                        	<td width="40%">
	                            	<div align="left">
	                            		<span class="labelinput">Nilai Tanah (RM)</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="58%" class="labeldisplay" >
	 								<input type="text" name="txtnilaiTanah" id="txtnilaiTanah" size="30" maxlength="50"  $mode $classDis value="$!perjanjian.getNilaiTanah()" onkeyup="validateNumber(this,this.value)" onblur="validateCurrency(this,this.value,'')"/>
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory"></span>
					        	</td>				        
	                        	<td width="40%">
	                            	<div align="left">
	                            		<span class="labelinput">Nilai Bangunan (RM)</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="58%" class="labeldisplay" >
	 								<input type="text" name="txtnilaiBangunan" id="txtnilaiBangunan" size="30" maxlength="50" value="$!perjanjian.getNilaiBangunan()" $mode $classDis onkeyup="validateNumber(this,this.value)" onblur="validateCurrency(this,this.value,'')" />
	  							</td>
	                		</tr>
	                		
                		</table>
           			</td>
                        	
	                <td valign="top">
	               		<table width="100%">
	                   		<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">*</span>
					        	</td>				        
	                        	<td width="40%">
	                            	<div align="left">
	                            		<span class="labelinput">Tarikh perjanjian</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="58%" class="labeldisplay" >
	 								<input name="txtTarikhPerjanjian" type="text" id="txtTarikhPerjanjian" value="$!perjanjian.getHtpPerjanjian().getTarikhPerjanjian()" size="11" maxlength="10" $mode $classDis onblur="check_date(this);checkDate(document.${formName}.txtTarikhPerjanjian);" />                  
                						<img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txtTarikhPerjanjian',false,'dmy');">
	  							</td>
	                		</tr>
	                   		<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory"></span>
					        	</td>				        
	                        	<td width="40%">
	                            	<div align="left">
	                            		<span class="labelinput">Bil. Unit Bangunan</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="58%" class="labeldisplay" >
	 								<input type="text" name="txtbilUnitBangunan" id="txtbilUnitBangunan" size="3" maxlength="3" value="$!perjanjian.getBilUnitBeli()" $mode $classDis onkeyup="validateNumber(this,this.value)" />
	 							</td>
	                		</tr>
	                   		<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory"></span>
					        	</td>				        
	                        	<td width="40%">
	                            	<div align="left">
	                            		<span class="labelinput">Harga Tambahan (RM)</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="58%" class="labeldisplay" >
	 								<input type="text" name="txtHargaTambahan" id="txtHargaTambahan" size="30" maxlength="50" value="$!perjanjian.getHargaTambahan()" $mode $classDis onkeyup="validateNumber(this,this.value)" onblur="validateCurrency(this,this.value,'')" />
	 							</td>
	                		</tr>
	                   		<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory"></span>
					        	</td>				        
	                        	<td width="40%">
	                            	<div align="left">
	                            		<span class="labelinput">Harga Beli (RM)</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="58%" class="labeldisplay" >
	 								<input type="text" name="txtHargaBeli" id="txtHargaBeli" size="30" maxlength="50" value="$!perjanjian.getHargaBeli()" $mode $classDis onkeyup="validateNumber(this,this.value)"  onblur="validateCurrency(this,this.value,'')" />
	 							</td>
	                		</tr>
	                      		
	                	</table>
	                </td>
       			</tr> 
        	</table>
	   	
	   	
	   	</td>
 	</tr>
	 	
	<tr>
 		<td align="center">
          		#if($perjanjianMode == "new")
           			 <input class="stylobutton100" type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick="simpanPerjanjian()" />
           			 
           		#elseif($perjanjianMode == "update")
           			<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Simpan" onclick="updatePerjanjian()">
             		 <!--<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Batal" onClick="kembaliDraft()">-->
             		<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Batal" onClick="setSelected(1,'pembelianview','pembelianview',0)">
         		#else           		
           			 <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniPerjanjian()">
           		#end
           		
           		
 			
   		</td>
 	</tr>
	
</table>
<input type="hidden" value="$!perjanjian.getHtpPerjanjian().idPerjanjian" name="idPerjanjian">
<input type="hidden" value="$!perjanjian.idPerjanjianPembelian" name="id_perjanjianpembelian">

<script>
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}}
</script>