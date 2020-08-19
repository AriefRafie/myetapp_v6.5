<!-- *
* AUTHOR : zufazdliabuas@gmail.com
* CREATE NEW FOR ONLINE BUAT PERMOHONAN
* Date : 26/7/2017 
* -->
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
<!--
.pautanms {color: #0033FF}
-->
</style>

<input name="idpermohonan" type="hidden" value="$!idpermohonan" /> 
<input name="idfail" type="hidden" value="$!idfail" /> 
<input name="form_token" type="hidden" value='$!{session.getAttribute("form_token")}'>
## #parse ("app/htp/permohonan/paging_permohonan.jsp") 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
   		<td>&nbsp;</td>
	</tr>
	<tr>
    	<td>    	
			<fieldset><legend>MAKLUMAT PERMOHONAN</legend>
				<table width="100%">
			   		<tr>
			   			<!-- TABLE SEBELAH KIRI -->
						<td width="50%" align="center" valign="top">
							<table width="100%" border="0">
			              		<tr>
			              			<td width="1%"><span class="style1">
			              				#if($!readOnly == "")
									    	*
								        #end
								        </span>        
					        		</td>
					                <td width="35%" align="left">NEGERI</td>
					                <td width="1%">:</td>            
					                <td width="63%">
					                	<div align="left" class="stylelabel">$!socNegeri</div>               	
					                </td>					        						
			              		</tr>
			              		<tr>
			              			<td width="1%">
			              				#if($!readOnly == "")
									    	<span class="style1">*</span>
								        #end        
					        		</td>
					               <!--  <td width="35%" align="left">DAERAH</td> -->
					                <td width="35%" align="left">$!labelDaerahUP</td>
					                <td width="1%">:</td>            
					                <td width="63%">
					                	<div align="left" class="stylelabel">$!socDaerah</div>               	
					                </td>					        						
			              		</tr>
			              		<tr>
			              			<td width="1%">
			              				#if($!readOnly == "")
									    	<span class="style1">*</span>
								        #end        
					        		</td>
					                <td width="35%" valign="top" align="left">KEMENTERIAN</td>
					                <td width="1%" valign="top">:</td>
					                <td width="63%">
					                	<div align="left" class="stylelabel">$!socKementerian</div>               	
									</td>						        						
			              		</tr>
			              		<tr>
	              					<td width="1%">
			              				#if($!readOnly == "")
									    	<span class="style1">*</span>
								        #end        
					        		</td>
					               	<td width="35%" valign="top" align="left">AGENSI</td>
					                <td width="1%" valign="top">:</td>
					                <td width="63%"><div align="left" class="stylelabel">$!socAgensi</div>               	
									</td>					        		
					        					
			              		</tr>
			              		<tr>
									<td width="1%">
			              				#if($!readOnly == "")
									    	<span class="style1">*</span>
								        #end        
					        		</td>
					               	<td width="35%" align="left">URUSAN</td>
					                <td width="1%">:</td>
					                <td width="63%">
					                	<div align="left" class="stylelabel">$!socUrusan</div>               	
									</td>					        						
			              		</tr>
			              		<tr>
									<td width="1%">
			              				#if($!readOnly == "")
									    	<span class="style1">*</span>
								        #end        
					        		</td>
					               	<td width="35%" align="left">SUB URUSAN </td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!socSubUrusan</div>               	
									</td>					        						
			              		</tr>
			              		<!--<tr>
						      		 <td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Status Tanah</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$statusTanah</td>	
					        					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Jenis Fail</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socTarafKeselamatan</td>	
					        		<input type="hidden" name="idSuburusan" value="$idSuburusan">				
			              		</tr>-->	
			              		<tr>
									<td width="1%" valign="top">
			              				#if($!readOnly == "")
									    	<span class="style1">*</span>
								        #end        
					        		</td>
					               	<td width="35%" valign="top" align="left">TAJUK</td>
					                <td width="1%" valign="top">:</td>
					                <td width="63%">
					                	<textarea $readOnly class="$disabled" name="txtTajuk" id="txtTajuk" cols="41" rows="5" onkeyup="this.value=this.value.toUpperCase();" onblur="this.value=this.value.toUpperCase();">$!txtTajuk</textarea>               	
									</td>					        						
			              		</tr>		

				       		</table>		
						</td>
						
						<!-- TABLE SEBELAH KAKAN -->
        				<td width="50%" align="center" valign="top">
        					<table width="100%" border="0">
        						<tr> 
						      		<td width="1%" valign="top"></td>
					        		<td width="30%" valign="top" align="left">NO. RUJUKAN ONLINE</td>
					        		<td width="1%" valign="top"><div align="center">:</div></td>
					        		<td width="68%">
					        			<input name="noPermohonanOnline" type="text" id="noPermohonanOnline" value="$!noPermohonanOnline" size="43" readonly="readonly" class="disabled"/>
					        		</td>				
			              		</tr>
			              		<tr> 
						      		<td width="1%">
						      		#if($!readOnly == "" && $!statusTanah == "1")
					        			<span class="style1">*</span> 
					        		#end
						      		</td>
					        		<td width="30%" align="left">NO. FAIL KJP</td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
										<input type="text" name="txtnoFailKJP" value="$!txtnoFailKJP" size="43" maxlength="50" $readOnly class="$disabled" onblur="this.value=this.value.toUpperCase();"  />
					          		</td>
					        		<!--
					            	<td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail KJP</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!htpPermohonan.noRujukanKJP</div>               	
									</td>	-->					        									              			
		              			</tr>	
				             	<tr>
				             		<td width="1%">
						      		#if($!readOnly == "" && $!statusTanah == "1")
					        			<span class="style1">*</span> 
					        		#end
						      		</td>				             		
					               <td width="30%" align="left">TARIKH SURAT KJP</td>
					                <td width="1%">:</td>
					                <td width="68%" valign="center">
					                	<input type="text" name="txdTarikhSuratKJP" value="$!txdTarikhSuratKJP" size="11" maxlength="10" $readOnly class="$disabled" onblur="check_date(this)" />
					            		#if($!readOnly == "")
					          				<a href="javascript:displayDatePicker('txdTarikhSuratKJP',false,'dmy');"> <img src="../img/calendar.gif" alt="" border="0"/></a>
					          			#end              	
									</td>		
				              	</tr> 
			              		<!-- <tr>
						      		 <td width="1%"></td>
					        		<td width="30%" align="left">NO. FAIL UPT</td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
										<input  type="text" name="txtnoFailUPT"  value="$!txtnoFailUPT" size="43" $readOnly class="$disabled" onblur="this.value=this.value.toUpperCase();"/>
					          		</td>	
					        		
					               	<td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail UPT</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!txtnoFailUPT</div>               	
									</td>							        								              			
		              			</tr> -->		
			              		<!-- <tr>
						      		 <td width="1%"></td>
					        		<td width="30%" align="left">NO. FAIL NEGERI</td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
										<input  type="text" name="txtnofailnegeri"  value="$!txtnoFailNegeri" size="43" $!disability onblur="this.value=this.value.toUpperCase();"/>
					          		</td>					        								              			
		              			</tr> -->		              			
			              		<!-- <tr>
						      		 <td width="1%"></td>
					        		<td width="30%" align="left">
				 						#parse ("app/htp/permohonan/utiliti/frmPejabatTanahLabelScript.jsp")
					        			</td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
										<input  type="text" name="txtnofailptg"  value="$!txtnoFailPTG" size="43" $!disability onblur="this.value=this.value.toUpperCase();"/>
					          		</td>					        								              			
		              			</tr>		 -->              					              			        			
								<!-- #if($!idNegeriNotis=="13" || $!idNegeriNotis=="12")
								
								#else
				              		<tr>
							      		 <td width="1%"></td>
						        		<td width="30%" align="left">NO. FAIL PTD</td>
						        		<td width="1%"><div align="center">:</div></td>
						        		<td width="68%">
											<input  type="text" name="txtnofailptd"  value="$!txtnoFailPTD" size="43" $!disability onblur="this.value=this.value.toUpperCase();"/>
						          		</td>					        								              			
			              			</tr>
								#end -->		              				              			        			
			              		<!-- <tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">Tarikh Surat KJP</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
					        			<input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="10" value="$!htpPermohonan.permohonan.getTarikhSurat()" $inputstyleread >
						                #if($pageMode=="edit")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
						                #elseif($pageMode=="kemas")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
						                #else
						                </td>
						                #end
					        		</td>							              			
		              			</tr>	-->	
			              		<!-- <tr>
						      		<td width="1%">
						      			<span class="labelmandatory">#if($pageMode!="update")*#end </span>
						      		</td>
					        		<td width="30%"><div align="left">TARIKH PERMOHONAN</div></td>
					        		<td width="1%"><div align="center">:</div></td>
						        		<td width="68%">
						        		<input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="10" value="$!htpPermohonan.permohonan.getTarikhTerima()" $inputstyleread/>
						                #if($pageMode=="edit")
						                	<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
						                #elseif($pageMode=="kemas")
						                	<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
						                #else
						                
						                #end
					                </td>							              			
		              			</tr> -->
		              			<tr>
				             		<td width="1%">
						      		#if($!readOnly == "")
					        			<span class="style1">*</span> 
					        		#end
						      		</td>				             		
					               <td width="30%" align="left">TARIKH PERMOHONAN</td>
					                <td width="1%">:</td>
					                <td width="68%" valign="center">
					                	<input type="text" name="txtTarikhPermohonan" value="$!txtTarikhPermohonan" size="11" maxlength="10" $readOnly class="$disabled" onblur="check_date(this)" />
					            		#if($!readOnly == "")
					          				<a href="javascript:displayDatePicker('txtTarikhPermohonan',false,'dmy');"> <img src="../img/calendar.gif" alt="" border="0"/></a>
					          			#end              	
									</td>		
				              	</tr> 
				              	
								<!-- <tr>
             						<td width="1%">
						      		#if($!readOnly == "" && $!statusTanah == "1")
					        			<span class="style1">*</span> 
					        		#end
						      		</td>				             		
					               <td width="30%" align="left">STATUS TANAH</td>
					                <td width="1%">:</td>
					                <td width="68%">
					                	<div align="left" class="stylelabel">$!socStatustanah</div>               	
									</td>		
				              	</tr> -->
				              	
								<!-- <tr>
             						<td width="1%">
						      		#if($!readOnly == "")
					        			<span class="style1">*</span> 
					        		#end
						      		</td>										
					               <td width="30%" align="left">JENIS FAIL</td>
					                <td width="1%">:</td>
					                <td width="68%">
					                	<div align="left" class="stylelabel">$!socjenisFail</div>               	
									</td>		
				              	</tr> -->
				              	
					         	#if($!readOnly != "")
					        		<tr>
	             						<td width="1%">
						        			<span class="style1">&nbsp;</span> 
							      		</td>										
						               <td width="30%" align="left">DIDAFTAR OLEH</td>
						                <td width="1%">:</td>
						                <td width="68%">
						                	<div align="left" class="stylelabel">$!diDaftarOleh</div>               	
										</td>		
					              	</tr>
					        		<tr>
	             						<td width="1%">
						        			<span class="style1">&nbsp;</span> 
							      		</td>										
						               <td width="30%" align="left">DIDAFTAR PADA</td>
						                <td width="1%">:</td>
						                <td width="68%">
						                	<div align="left" class="stylelabel">$!diDaftarPada</div>               	
										</td>		
					              	</tr>					        	
					        	#end
				              	<!--<tr>
					               <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Permohonan</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.tarikhTerima</div>               	
									</td>		
				              	</tr>
								<tr>
					               <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Buka Fail</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$htpPermohonan.permohonan.pfdFail.tarikhDaftarFail</div>               	
									</td>		
				            	</tr> -->				              	
				       		</table>	        				
    					</td>
    				</tr>
				</table>
			</fieldset>		
    	</td>
	</tr>
 						
<!-- BUTTON -->
#if($!readOnly == "")
	<tr>
        <td>
   			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
        </td>
	<tr>
	#if($pageMode == "kemaskini")		
		<td align="center">
			<input class="stylobutton100" name="cmdSimpan" type="button" id="cmdSimpan" value="Simpan" onclick="failKemaskiniSimpan()" />
			<input class="stylobutton100" name="cmdKembali" type="button" id="cmdKembali" value="Batal" onclick="viewMaklumatPermohonan($!idfail)"/>
			<!-- <input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/> -->
		</td>
	#else		
		<td align="center">
			<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan('Simpan')" />
 			<input type="reset" class="stylobutton100" name="cmdReset" value="Kosongkan" onclick = "javascript:kosongkanPendaftaran()">
			<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
		</td>
	#end		
	</tr>
#else
	<tr>
		<td align="center"><!--<p>-->
			<input class="stylobutton100" type="button" onclick="javascript:failKemaskini();" value="Kemaskini" />
			<!-- <input class="stylobutton100" type="button" onclick="javascript:cetakKulitFail('$idPermohonan');" value="Cetak Kulit Fail" />
			<input class="stylobutton100" type="button" onclick="javascript:cetakDoket('$idPermohonan');" value="Cetak Doket" /> -->
			<input class="stylobutton100" type="button" onclick="javascript:senaraiDokumen('tabledokumen');" value="Cetak" />
		#if ($showSahkanButton)
			<input class="stylobutton100" type="button" onclick="javascript:sahkanPermohonan('$idPermohonan');" value="Sahkan" />
		#end
			$!sahkanresult
		<!--</p>--></td>
	</tr>
#end
<!-- END BUTTON -->
	
</table>
<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch">
<input type="hidden" name="txtTajukFail" value="$!txtTajukCarian">