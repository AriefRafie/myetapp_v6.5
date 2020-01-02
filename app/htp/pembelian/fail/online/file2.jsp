<style type="text/css">
<!--
.pautanms {color: #0033FF}
-->
<!--
.stylelabel{color: #0000FF}
-->
</style>
#set($hide='style="display:none"')
<table width="100%" border="0" cellspacing="2" cellpadding="2">
#if($!pageMode != 'edit')
	<tr>
		<td>
		##parse('app/htp/pembelian/fail/paging2.jsp')
		</td>
    </tr>
#end
	<tr>
		<td>&nbsp;</td>
    </tr>
	<tr>
		<td>
			<fieldset ><legend>MAKLUMAT PERMOHONAN</legend>
				<!--<table>	-->
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
					                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.namaNegeri</div>               	
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
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.pfdFail.namaKementerian</div>               	
				</td>						        						
			              		</tr>
			              		<tr>
						      		<!--<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Agensi</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectAgensi</td>		-->
               <td width="35%" valign="top" style="text-transform:uppercase;"><div align="right">Agensi</div></td>
                <td width="1%" valign="top">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.namaAgensi</div>               	
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
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.pfdFail.namaSuburusan</div>               	
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
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.tujuan</div>               	
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
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.pfdFail.noFail</div>               	
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
					                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.tarikhSurat</div>               	
									</td>		
				              	</tr> 
			              		<tr>
						      		<!-- <td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail Lain</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$!htpPermohonan.getNoRujukanLain()"  $inputstyleread/></td>	
					        		-->
               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail Lain</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.noRujukanLain</div>               	
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
					                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.pfdFail.tarikhDaftarFail</div>               	
									</td>		
				            	</tr>				              	
				       		</table>	        				
    					</td>
    				</tr>
				</table>
			</fieldset>
		</td>
    </tr>
			#if($pageMode!="update")
			  <tr>
			    <td colspan="2">
		        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
			    	</td>
			  </tr>
			 #end
	<tr>
		<td align=center>
			#if($pageMode=="edit")
				<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanFail()" $hide>
				<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('indexPage')" $hide>
			#elseif($pageMode=="update")
				<input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="doAjaxCall${formName}('update')" $hide>
                <input class="stylobutton" type="button"  name="Cetak" id="Cetak" value="Cetak" onclick="javascript:senaraiDokumenSurat('tabledokumensurat');" $hide/>
				<input class="stylobutton100" type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="doAjaxCall${formName}('maklumatTanah','&idPermohonan='+$!htpPermohonan.permohonan.getIdPermohonan())">
				<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('indexPage')" >
          	#else
                <input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Simpan" onclick="doAjaxCall${formName}('simpanupdate')" $hide>
				<input class="stylobutton" type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="doAjaxCall${formName}('maklumatTanah','&idPermohonan='+$!htpPermohonan.permohonan.getIdPermohonan())" $hide>
				<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('indexPage')" $hide>
			#end    
    	</td>
    </tr>
        <tr>
  	<td colspan=2>
  		 <fieldset id="tabledokumensurat" style="display:none;">
			<legend>SENARAI SURAT/DOKUMEN</legend>
			<table width="100%" border="0">
			  <tr>
			    <td><a href="javascript:cetakFailDoket('&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','&template=NoFailTajukFail','ekptg.report.htp.NoFailTajukFail')" class="pautanms">KULIT FAIL</a></td>
			  </tr>
			  <tr>
			    <td><a href="javascript:cetakFailDoket('&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','&template=rptNoFailTajukFail','ekptg.report.htp.NoFailTajukFail')" class="pautanms">DOKET</a></td>
			  </tr>  
   
			  </table>
			</fieldset>
	  </td>
  </tr>
</table>



<input type="hidden" name="socNegeri" value="$!htpPermohonan.fail.getIdNegeri()"/>
<input type="hidden" name="socNegeri" value="$!htpPermohonan.permohonan.getIdKementerian()"/>
<input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>
<input type="hidden" name="pageMode" value="$!pageMode">

<script>
function step5(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}

function step4(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.RundinganPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step3(idPermohonan){
	doAjaxCall${formName}('perakuanPembelian','&idPermohonan='+idPermohonan);
}
function step2(idPermohonan){
	doAjaxCall${formName}('maklumatTanah','&idPermohonan='+idPermohonan);
}
function step1(idPermohonan,idhtp){
	doAjaxCall${formName}("detail",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
	}
</script>