<fieldset><legend>Pembelian</legend>
<fieldset><legend>Maklumat Fail</legend>#parse ("app/htp/pembelian/fail/fileInfo.jsp")
</fieldset>

<fieldset>
	<legend>Maklumat Tanah</legend>
	<TABLE WIDTH="100%">
		<TR>
			<TD width="50%" valign="top">
				<table WIDTH="100%">
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Negeri</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$socNegeri
  							</td>
                		</tr>
                     	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($button == 'simpan') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Daerah</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$socDaerah
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($button == 'simpan') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Bandar/Pekan/Mukim</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$socMukim
  							</td>
                		</tr>
                		               	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($button == 'simpan') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Seksyen</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								<select id=seksyen>
 								<option>SILA PILIH</option>
 								<option>SEKSYEN 01</option>>
 								<option>SEKSYEN 02</option>>
 								
 								</select>
  							</td>
                		</tr>
                  	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Taraf Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
	                   		#if ($mode == 'view')
	                   				#set( $staraf = '' )
	                   				#if($socTaraf == 'P')
										#set( $staraf = 'P - PAJAKAN')
									#elseif($socTaraf == 'S')	
										#set( $staraf = 'S - PEGANGAN BEBAS/FREE HOLD')																			
									#end
									$!staraf
								#else							
								<select name="socTaraf" id="socTaraf" style="width:200px;" $readonly class="$disabled" $disabled >								             
								#if($!urusan.getStatusTanah() == 'P')
								          
								    <option value="">SILA PILIH</option>
									<option value="P" selected="selected">P - PAJAKAN</option>
								    <option value="S">S - PEGANGAN BEBAS/FREE HOLD</option>
								      
								#elseif($!urusan.getStatusTanah() == 'S')
								          
									<option value="">SILA PILIH</option>
								    <option value="P">P - PAJAKAN</option>
								   	<option value="S" selected="selected">S - PEGANGAN BEBAS/FREE HOLD</option>
								      
								#else
								          
									<option value="" selected="selected">SILA PILIH</option>
								    <option value="P">P - PAJAKAN</option>
								   	<option value="S">S - PEGANGAN BEBAS/FREE HOLD</option>
								      
								#end								        
								</select>
							#end	
      						</td>
                		</tr>
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Mula</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
						    <input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="10" value="$!urusan.getTarikhMula()" $classDis $mode  onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasaIsNull(document.${formName}.txdTarikhTerima);"/>
		              <img src="../img/calendar.gif" border="0" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');" />      						
      						</td>
                		</tr>  
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Luput</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
						    <input type="text" name="txdTarikhLuput" id="txdTarikhLuput" size="10" value="$!urusan.getTarikhLuput()" $classDis $mode onkeyup="validateNumber(this,this.value);" onblur="check_date(this);kiraTahun();"/>
		              <img src="../img/calendar.gif" border="0" onclick="displayDatePicker('txdTarikhLuput',false,'dmy');" />      						
      						</td>
                		</tr>    
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tempoh</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
      							<input name="txtTempoh" type="text" id="txtTempoh" value="" size="4" onkeyup="validateNumber(this,this.value);" />
      						<span class="labelinput">Tahun</span>  			
      						</td>
                		</tr>                		            			
                		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tempoh Baki</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
                   			<input name="txtTarikhPermohonan" type="hidden" id="txtTarikhPermohonan" value="$!htpPermohonan.permohonan.tarikhTerima">
      							<input name="txtTempohbaki" type="text" id="txtTempohbaki" size="4" onkeyup="validateNumber(this,this.value);" />
      						<span class="labelinput">Tahun</span>    			
      						</td>
                		</tr> 
<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($button == 'simpan') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Unit Luas</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
	             				$socLuas
						   	</td>
                		</tr> 
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($button == 'simpan') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Luas</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
							<input type="text" name="txtLuas" id="txtLuas" maxlength="40"  value="$!urusan.getLuas()" $mode $classDis />
						   	</td>
                		</tr>                 		
                	
                	</table>
			
			</TD>
			
			<TD width="50%" valign="top">
				<table WIDTH="100%">
                 	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($button == 'simpan' || $button == 'kemaskini') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Jenis Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$socJenisHakmilik
  							</td>
                		</tr>
              			
            			<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($button == 'simpan' || $button == 'kemaskini') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
                   				<input type="text" name="txtNoHakmilik" id="txtNoHakmilik" size="20" maxlength="21" value="$!urusan.getNohakmilik()" $mode $classdis onkeyup="this.value=this.value.toUpperCase();"  />
  							</td>
                		</tr>

                    	<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Strata</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
      							<span class="labelinput">No.Bang</span>&nbsp;<input name="noBangunan" type="text" class="$disabled" id="noBangunan"  value="$!urusan.getNoBangunan()" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
								<span class="labelinput">No.Ting</span>&nbsp;<input name="noTingkat" type="text" class="$disabled" id="noTingkat"  value="$!urusan.getNoTingkat()" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
								<span class="labelinput">No.Petak</span>&nbsp;<input name="noPetak" type="text" class="$disabled" id="noPetak"  value="$!urusan.getNoPetak()" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
								
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($button == 'simpan') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kod</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$socLot
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($button == 'simpan') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No Lot/PT</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
      							<input type="text" name="txtNoLot" id="txtKodLot" maxlength="20" size="20" value="$!urusan.getNolot()" $mode $classDis onkeyup="this.value=this.value.toUpperCase();" >
  							</td>
                		</tr> 					
                   		<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Jenis Rizab</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
								$socRizab
        					</td>
                		</tr>
                		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($button == 'simpan') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kategori</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
								$socKategori
        					</td>
                		</tr>  
	                   	<tr>
	  						<td width="1%"  >
					        </td>				        
	                        <td width="30%">
	                           	<div align="left">
	                           		<span class="labelinput">No. Pelan Akui</span>
	                           	</div>
	                        </td>
	                  		<td width="1%" class="labelinput" >:</td>
	                   		<td width="68%" class="labeldisplay" >
								<input type="text" name="txtNoPelan" id="txtNoPelan" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$!urusan.getNoPlan()" $mode $classDis />
	        				</td>
	                	</tr>
	
				</table>	
			</TD>
		</TR>

	
	</TABLE>

</fieldset>
	<TABLE WIDTH="100%">
		<TR >
			<td align="center">
			#if($button == "simpan")
            	<input class="stylobutton" name="Simpan" value="Simpan" type="button" onclick="simpanMaklumatTanah();"/>
                <input class="stylobutton" name="Kembali" value="Kembali" type="button" onclick="doAjaxCall${formName}('maklumatTanah','idPermohonan='+$!htpPermohonan.permohonan.getIdPermohonan())"/> 
           	#else
                <!-- <input class="stylobutton" name="Simpan" value="Kemaskini" type="button" onclick="doAjaxCall${formName}('kemaskiniMaklumatTanah')"/> -->
                <input class="stylobutton" name="Simpan" value="Kemaskini" type="button" onclick="doAjaxCall${formName}('kemaskiniMaklumatTanah')"/>
                <input class="stylobutton" name="Kembali" value="Kembali" type="button" onclick="doAjaxCall${formName}('maklumatTanah','idPermohonan='+$!htpPermohonan.permohonan.getIdPermohonan())"/>
          	#end
            </td>
		</TR>
	
	
	</TABLE>
</fieldset>
<input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>
<input type="hidden" name="txtidPermohonan2" value="$!urusan.getIdPermohonan()"/>
<input type="hidden" name="txtIdHakmilikUrusan" value="$!urusan.getIdhakmilikurusan()"/>

<script type="text/javascript">
	var dari_bulan;
	var dari_hari;
	var dari_tahun;
	var hingga_bulan;
	var hingga_hari;
	var hingga_tahun;
	var daftar_bulan;
	var daftar_hari;
	var daftar_tahun;
	if(document.${formName}.socTaraf.value == 'P'){
		if(document.${formName}.txdTarikhLuput.value == ''){
			alert('Sila masukkan Tarikh Luput Hakmilik');
			document.${formName}.txdTarikhLuput.focus();
			//return;
		
		}else{
			hingga_bulan = document.${formName}.txdTarikhLuput.value.substring(3,5);
			hingga_hari = document.${formName}.txdTarikhLuput.value.substring(0,2);
			hingga_tahun = document.${formName}.txdTarikhLuput.value.substring(6,10);
			var hinggatemp = hingga_bulan+"/"+hingga_hari+"/"+hingga_tahun;
	
			daftar_bulan = document.${formName}.txdTarikhTerima.value.substring(3,5);
			daftar_hari = document.${formName}.txdTarikhTerima.value.substring(0,2);
			daftar_tahun = document.${formName}.txdTarikhTerima.value.substring(6,10);
			var daftartemp = daftar_bulan+"/"+daftar_hari+"/"+daftar_tahun;
	
			dari_bulan = document.${formName}.txtTarikhPermohonan.value.substring(3,5);
			dari_hari = document.${formName}.txtTarikhPermohonan.value.substring(0,2);
			dari_tahun = document.${formName}.txtTarikhPermohonan.value.substring(6,10);
			var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
			
			var dari = Date.parse(daritemp);
			var hingga = Date.parse(hinggatemp);
		
			var diff_date =  hingga - dari;
			
			var num_years = diff_date/31536000000;
			var num_months = (diff_date % 31536000000)/2628000000;
			var num_days = ((diff_date % 31536000000) % 2628000000)/86400000;
			
			var daftar = Date.parse(daftartemp);
			var baki =  hingga - daftar;
			var bakiTahun = baki/31536000000;
			
			document.${formName}.txtTempoh.value = Math.floor(bakiTahun);
		  	document.${formName}.txtTempohbaki.value = Math.floor(num_years);
		}
	}
	
</script>