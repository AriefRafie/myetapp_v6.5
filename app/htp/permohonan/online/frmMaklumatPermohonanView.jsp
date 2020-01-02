<!-- *
* AUTHOR : zufazdliabuas@gmail.com
* CREATE NEW FOR ONLINE VIEW SAHAJA MAKLUMAT
* Date : 26/7/2017 
* -->

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
##parse ("app/htp/permohonan/online/pagingPermohonanOnline.jsp") 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td>
			<fieldset>
				<legend>MAKLUMAT PERMOHONAN</legend>
				<table width="100%">
			   		<tr>
						<td width="50%" align="center" valign="top">
							<table width="100%" border="0">
			              		<tr>
					                <td width="35%" style="text-transform:uppercase;"><div align="right">Negeri</div></td>
					                <td width="1%">:</td>            
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!socNegeri</div>               	
					                </td>					        						
			              		</tr>
			              		 <tr>
						            <td width="36%" align="right">DAERAH</td>
						            <td width="1%">:</td>
						            <td width="63%">
						            	<div align="left" class="stylelabel">$!socDaerah</div>
						            </td>
						          </tr>
			              		<tr>
					                <td width="35%" valign="top" style="text-transform:uppercase;"><div align="right">Kementerian</div></td>
					                <td width="1%" valign="top">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!socKementerian</div>               	
									</td>						        						
			              		</tr>
			              		<tr>
					               	<td width="35%" valign="top" style="text-transform:uppercase;"><div align="right">Agensi</div></td>
					                <td width="1%" valign="top">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!socAgensi</div>               	
									</td>					        		
					        					
			              		</tr>
			              		<tr>
					               	<td width="35%" style="text-transform:uppercase;"><div align="right">Urusan</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!socUrusan</div>               	
									</td>					        						
			              		</tr>
			              		<tr>
					               	<td width="35%" style="text-transform:uppercase;"><div align="right">Sub Urusan</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!socSubUrusan</div>               	
									</td>					        						
			              		</tr>	
			              		<tr>
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
	       						<tr> 
					        		<td width="35%" style="text-transform:uppercase;"><div align="right">NO. RUJUKAN ONLINE</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!noPermohonanOnline</div>               	
									</td>					        							
			              		</tr>
	       						<tr> 
	              						<td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail Seksyen</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!noFail</div>               	
									</td>
			              		</tr>
			              		<tr>
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
					               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail UPT</div></td>
					                <td width="1%">:</td>
					                <td width="64%">
					                	<div align="left" class="stylelabel">$!txtnoFailUPT</div>               	
									</td>						        								              			
		              			</tr>
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
	   	</tr>
		<tr>
		#if($pageMode == "kemaskini")		
			<td align="center">
				<input class="stylobutton100" name="cmdSimpan" type="button" id="cmdSimpan" value="Simpan" onclick="failKemaskiniSimpan()" />
				<input class="stylobutton100" name="cmdKembali" type="button" id="cmdKembali" value="Batal" onclick="viewMaklumatPermohonan($!idfail)"/>
			</td>
		#else		
			<td align="center">
				<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan('Simpan')" />
				<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
			</td>
		#end		
		</tr>
	#else
		#if($!showButton == "show")
		<tr>
			<td align="center">
				<!-- ($!idjawatan.equals("9") || $!idjawatan.equals("24")) -->  <!-- $!idjawatan.equals("9") && $!idjawatan.equals("4") -->
				#if ($!idjawatan.equals("9") && $!idjawatan.equals("4"))
					<input class="stylobutton100" type="button" onclick="javascript:failKemaskini();" value="Kemaskini" />
				#end
				<input class="stylobutton100" type="button" onclick="javascritp:seterusnya();" value="Seterusnya" />
				<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
				<!-- <input class="stylobutton100" type="button" onclick="javascript:cetakKulitFail('$idPermohonan');" value="Cetak Kulit Fail" />
				<input class="stylobutton100" type="button" onclick="javascript:cetakDoket('$idPermohonan');" value="Cetak Doket" /> -->
				<!-- <input class="stylobutton100" type="button" onclick="javascript:senaraiDokumen('tabledokumen');" value="Cetak" /> -->
			</td>
		</tr>
		#end
	#end
<!-- END BUTTON -->
</table>

