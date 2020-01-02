<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
<!--
.stylelabel{color: #0000FF}
-->
</style>
<input name="idpermohonan" type="hidden" value="$!idpermohonan" /> 
<input name="idfail" type="hidden" value="$!idfail" /> 
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
#parse ("app/htp/permohonan/paging_permohonan.jsp") 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td>
    	
		<fieldset><legend>MAKLUMAT PERMOHONAN</legend>
				<table width="100%">
			   		<tr>
						<td width="50%" align="center" valign="top">
							<table width="100%" border="0">
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Negeri</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socNegeri</td>	 -->
					                <td width="35%" style="text-transform:uppercase;"><div align="right">Negeri</div></td>
					                <td width="1%">:</td>            
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!socNegeri</div>               	
					                </td>					        						
			              		</tr>
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Kementerian</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectKementerian</td>	 -->
					                <td width="35%" valign="top" style="text-transform:uppercase;"><div align="right">Kementerian</div></td>
					                <td width="1%" valign="top">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!socKementerian</div>               	
									</td>						        						
			              		</tr>
			              		<tr>
						      		<!--<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Agensi</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectAgensi</td>		-->
					               	<td width="35%" valign="top" style="text-transform:uppercase;"><div align="right">Agensi</div></td>
					                <td width="1%" valign="top">:</td>
					                <td width="64%"><div align="left" class="stylelabel">$!socAgensi</div>               	
									</td>					        		
					        					
			              		</tr>
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Urusan</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectSuburusan</td>	
					        		<input type="hidden" name="idSuburusan" value="$idSuburusan"> -->
					               	<td width="35%" style="text-transform:uppercase;"><div align="right">Urusan</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!socUrusan</div>               	
									</td>					        						
			              		</tr>
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Urusan</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectSuburusan</td>	
					        		<input type="hidden" name="idSuburusan" value="$idSuburusan"> -->
					               	<td width="35%" style="text-transform:uppercase;"><div align="right">Sub Urusan</div></td>
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
						      		<!-- <td width="1%" valign="top"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%" valign="top"><div align="left">Tajuk</div></td>
					        		<td width="1%" valign="top"><div align="center">:</div></td>
					        		<td width="68%">
										<textarea name="txtTajuk" id="txtTajuk" cols="43" rows="5" onkeyup="this.value=this.value.toUpperCase();"  $inputstyleread >$!htpPermohonan.permohonan.getTujuan()</textarea>					        		
					        		</td>	-->
					               	<td width="35%" style="text-transform:uppercase;" valign="top"><div align="right">Tajuk</div></td>
					                <td width="1%" valign="top">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!txtTajuk</div>               	
									</td>					        						
			              		</tr>		

				       		</table>		
						</td>
        				<td width="50%" align="center" valign="top">
        					<table width="100%" border="0">
        						<tr> <!--
						      		<td width="1%"><span class="labelmandatory">##if($pageMode!="update")*#end
						      		</span></td>
					        		<td width="30%"><div align="left">No. Fail Seksyen</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
					        		<input type="text" name="txtNoFailSek" size="28" value="$!htpPermohonan.permohonan.pfdFail.noFail" $inputstyleread>
					        		-->
               						<td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail Seksyen</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!noFail</div>               	
									</td>					        		
					        		</td>					
			              		</tr>
			              		<tr> <!--
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail KJP</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" onkeyup="this.value=this.value.toUpperCase();" size="28" maxlength="50" value="$!htpPermohonan.getNoRujukanKJP()"  $inputstyleread/></td>
					        		-->
					            	<td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail KJP</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!htpPermohonan.noRujukanKJP</div>               	
									</td>						        									              			
		              			</tr>	
				             	<tr>
					               <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Surat KJP</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!txdTarikhSuratKJP</div>               	
									</td>		
				              	</tr> 
			              		<tr>
						      		<!-- <td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail Lain</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$!htpPermohonan.getNoRujukanLain()"  $inputstyleread/></td>	
					        		-->
               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail UPT</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!txtnoFailUPT</div>               	
				</td>						        								              			
		              			</tr>			              			        			
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
			              		<!--<tr>
						      		<td width="1%">
						      			<span class="labelmandatory">#if($pageMode!="update")*#end </span>
						      		</td>
					        		<td width="30%"><div align="left">Tarikh Permohonan</div></td>
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
		              			</tr>	-->
								<tr>
					               <td width="35%" style="text-transform:uppercase;"><div align="right">Status Tanah</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!socStatustanah</div>               	
									</td>		
				              	</tr>
								<tr>
					               <td width="35%" style="text-transform:uppercase;"><div align="right">Jenis Fail</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!socjenisFail</div>               	
									</td>		
				              	</tr>
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
	 						
#if($!readOnly == "")
	<tr>
        <td>
   			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
        </td>
	<tr>
		<td align="center"><p >
			<input class="stylobutton" name="cmdSimpan" type="button" id="cmdSimpan" value="Simpan" onclick="Simpan('Simpan')" style="display:none"/>
			<input class="stylobutton100" name="cmdKembali" type="button" id="cmdKembali" value="Kembali" onclick="kembali()"/>
		</p></td>
	</tr>
#else
	<tr>
		<td align="center"><p>
			<input class="stylobutton" type="button" onclick="javascript:cetakKulitFail('$idPermohonan');" value="Cetak Kulit Fail" style="display:none" />
			<input class="stylobutton" type="button" onclick="javascript:cetakDoket('$idPermohonan');" value="Cetak Doket" style="display:none"/>
            <input class="stylobutton100" name="cmdKembali" type="button" id="cmdKembali" value="Kembali" onclick="kembali()"/>
		<!--
		#if ($showSahkanButton)
			<input class="stylobutton" type="button" onclick="javascript:sahkanPermohonan('$idPermohonan');" value="Sahkan" style="display:none"/>
		#end
			$!sahkanresult	-->
		</p></td>
	</tr>
#end
	
</table>
