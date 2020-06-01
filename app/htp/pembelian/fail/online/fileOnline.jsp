
#set($frmtdate = "&nbsp;<i><font color=blue style='font-size:10px'>dd/mm/yyyy</font></i>")

<table width="100%" border="0" cellspacing="2" cellpadding="2">

	#if($pageMode!="edit")	
	<tr>
		<td>
			##parse('app/htp/pembelian/fail/online/pagingOnline.jsp')
		</td>
    </tr>
	#else

	#end
	<tr>
		<td>&nbsp;</td>
    </tr>
	<tr>
		<td>
			<fieldset><legend>MAKLUMAT PERMOHONAN</legend>
				<!--<table>	-->
				<table width="100%">
			   		<tr>
						<td width="50%" align="center" valign="top">
							<table width="100%" border="0">
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Negeri</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socNegeri</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><!-- <span class="labelmandatory">#if($pageMode!="update")*#end</span> --></td>
					        		<td width="30%"><div align="left">Kementerian</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectKementerian</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Agensi</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectAgensi</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Urusan</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectSuburusan</td>	
					        		<input type="hidden" name="idSuburusan" value="$idSuburusan">				
			              		</tr>
			              		<tr>
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
			              		</tr>
			              		<tr>
						      		<td width="1%" valign="top"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%" valign="top"><div align="left">Tajuk</div></td>
					        		<td width="1%" valign="top"><div align="center">:</div></td>
					        		<td width="68%">
										<textarea name="txtTajuk" id="txtTajuk" cols="40" rows="5" onkeyup="this.value=this.value.toUpperCase();"  $inputstyleread >$!htpPermohonan.permohonan.getTujuan()</textarea>					        		
					        		</td>					
			              		</tr>		

				       		</table>		
						</td>
        				<td width="50%" align="center" valign="top">
        					<table width="100%" border="0">
        						<tr>
						      		<td width="1%"><span class="labelmandatory">##if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">No. Rujukan <i>Online</i></div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
					        			<!-- <input type="text" name="txtNoFailSek" size="28" value="$!htpPermohonan.permohonan.pfdFail.noFail" readonly="readonly" $inputstyleread>
					        		-->
					        		<input type="text" name="txtNoFailSek" size="28" value="$!htpPermohonan.permohonan.getNoPermohonan()" $inputstyleread readonly class="disabled">
					        		
					        		</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail KJP</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" onkeyup="this.value=this.value.toUpperCase();" size="28" maxlength="50" value="$!htpPermohonan.getNoRujukanKJP()"  $inputstyleread/></td>							              			
		              			</tr>		
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">*</span></td> -->
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail Lain</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$!htpPermohonan.getNoRujukanLain()"  $inputstyleread/></td>							              			
		              			</tr>			              			        			
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">*</span></td> -->
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
		              			</tr>
		              			<!-- <tr>
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
		              			</tr> -->		
			              		<tr>
						      		<td width="1%">
						      			<span class="labelmandatory">#if($pageMode!="update")*#end </span>
						      		</td>
					        		<td width="30%"><div align="left">Tarikh Permohonan</div></td>
					        		<td width="1%"><div align="center">:</div></td>
						        		<td width="68%">
						        		<!-- <input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="10" value="$!htpPermohonan.permohonan.getTarikhTerima()" $inputstyleread/> -->
						        		<input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" readonly="readonly" size="10" value="$!dateToday" onblur="check_date(this)" $inputstyleread/>$!frmtdate</td>
						                
						                <!-- #if($pageMode=="edit")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
						                #elseif($pageMode=="kemas")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
						                #else
						                #end -->
            
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
				<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanFail()">
				<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('indexPage')">
			#elseif($pageMode=="update")
				<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="doAjaxCall${formName}('update')">
				<input class="stylobutton100" type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="doAjaxCall${formName}('maklumatanahonline','&idPermohonan='+$!htpPermohonan.permohonan.getIdPermohonan())">
				<!-- <input class="stylobutton100" type="button" onclick="javascript:cetakPengesahan('$!htpPermohonan.permohonan.getIdPermohonan()');" value="Cetak " />	-->
				<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('indexPage')">
            #else
                <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Simpan" onclick="doAjaxCall${formName}('simpanupdate')">
				<input class="stylobutton100" type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="doAjaxCall${formName}('maklumatanahonline','&idPermohonan='+$!htpPermohonan.permohonan.getIdPermohonan())">
				<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('indexPage')">
			#end    
    	</td>
    </tr>
</table>



<input type="hidden" name="XsocNegeri" value="$!htpPermohonan.fail.getIdNegeri()"/>
<input type="hidden" name="XXsocNegeri" value="$!htpPermohonan.permohonan.getIdKementerian()"/>
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