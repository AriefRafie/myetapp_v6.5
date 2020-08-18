<!--frmTerimaPohon.jsp-->
<!-- CL-02-001 -->
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<input type="hidden" name="idpermohonan" value="$!idpermohonan" /> 
<input type="hidden" name="idfail" value="$!idFail" /> 
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
##parse ("app/htp/permohonan/paging_permohonan.jsp") 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td>
    	
		<fieldset><legend><strong>MAKLUMAT PENERIMAAN PERMOHONAN</strong></legend>
			<table width="100%" border="0" cellspacing="2" cellpadding="2">
  				<tr>
    				<td>
    					<table width="100%" border="0" align="left">    
					      <tr>
					        <td width="1%">
					        #if($!readOnly == "")
					        <span class="style1">*</span>
					        #end        </td>
					        <td width="11%"><div align="left">Negeri</div></td>
					        <td width="1%"><div align="center">:</div></td>
					        <td width="36%">$socNegeri          </td>
					        <td>&nbsp;</td>
					        <td width="20%"><div align="left">No. Fail Seksyen</div></td>
					        <td width="1%"><div align="center">:</div></td>
					        <td width="36%"><label>
					          <input name="noFail" type="text" id="noFail" value="$!noFail" size="30" $inputstyleread/>
					        </label></td>
					      </tr>
					      <tr>
					        <td width="1%">
					        #if($!readOnly == "")
					        <span class="style1">*</span>
					        #end        </td>
					        <td width="11%"><div align="left">Daerah</div></td>
					        <td width="1%"><div align="center">:</div></td>
					        <td width="36%">$socDaerah          </td>
					               <td width="1%">
					        #if($!readOnly == "")
					        <span class="style1">*</span>
					        #end        </td>
					        <td><div align="left">No. Fail KJP</div></td>
					        <td><div align="center">:</div></td>
					        <td>
					          <!-- <input $readOnly class="$disabled" name="txtnoFailKJP" type="text" 
					          onblur="this.value=this.value.toUpperCase();" value="$!txtnoFailKJP" size="30" />	-->
					           <input type="text" name="txtnoFailKJP"  
					          onblur="this.value=this.value.toUpperCase();" value="$!txtnoFailKJP" size="30" $inputstyleread/>	 
					          
					          </td>
					      </tr>
					      <tr>
					               <td width="1%">
					        #if($!readOnly == "")
					        <span class="style1">*</span>
					        #end        </td>
					        <td width="11%"><div align="left">Kementerian</div></td>
					        <td width="1%"><div align="center">:</div></td>
					        <td width="36%"><label>$socKementerian</label></td>
					               <td width="1%">
					        #if($!readOnly == "")
					        <span class="style1">*</span>
					        #end        </td>
					        <td><div align="left"> Tarikh Surat KJP</div></td>
					        <td><div align="center">:</div></td>
					        <td><label>
					          <!-- <input $readOnly class="$disabled" name="txdTarikhSuratKJP" type="text" onblur="check_date(this)" value="$!txdTarikhSuratKJP" size="11" maxlength="10"/> -->
					          <input type="text" name="txdTarikhSuratKJP" onblur="check_date(this)" value="$!txdTarikhSuratKJP" size="11" maxlength="10" $inputstyleread/>
					          </label>
					            #if($!readOnly == "")
					          <a href="javascript:displayDatePicker('txdTarikhSuratKJP',false,'dmy');"> <img src="../img/calendar.gif" alt="" border="0"/></a>
					          #end</td>
					      </tr>
					      <tr>
					               <td width="1%">
					        #if($!readOnly == "")
					        <span class="style1">*</span>
					        #end        </td>
					        <td width="11%"><div align="left">Agensi</div></td>
					        <td width="1%"><div align="center">:</div></td>
					        <td width="36%"><label>$socAgensi</label></td>
					               <td width="1%">&nbsp;</td>
					        <td><div align="left">No. Fail UPT</div></td>
					        <td><div align="center">:</div></td>
					        <td><label>
					          <!-- <input $readOnly class="$disabled" name="txtnoFailUPT" type="text" 
					          onblur="this.value=this.value.toUpperCase();" value="$!txtnoFailUPT" size="30" id="txtnoFailUPT"/> -->
					          <input  type="text" name="txtnoFailUPT" 
					          onblur="this.value=this.value.toUpperCase();" value="$!txtnoFailUPT" size="30" id="txtnoFailUPT" $inputstyleread/>
					          </label>        </td>
					      </tr>
					      <tr>
					      	<td width="1%">
					        #if($!readOnly == "")
					        	<span class="style1">*</span>
					        #end
					        </td>
					        <td>Urusan</td>
					        <td>:</td>
					        <td>$socUrusan</td>
					       	<td width="1%">
					        #if($!readOnly == "")
					        	<span class="style1">*</span>
					        #end       
					        </td>
					        <td width="20%">Status Tanah</td>
					        <td width="1%">:</td>
					        <td width="36%">$socStatustanah</td>
					      </tr>
					      <tr>
					        <td> #if($!readOnly == "") <span class="style1">*</span> #end </td>
					        <td><div align="left">Sub Urusan</div></td>
					        <td><div align="center">:</div></td>
					        <td>$socSubUrusan</td>
					        <td> #if($!readOnly == "") <span class="style1">*</span> #end </td>
					        <td>Jenis Fail</td>
					        <td>:</td>
					        <td>$socjenisFail</td>
					      </tr>
					      <tr>
					        <td valign="top"> #if($!readOnly == "") <span class="style1">*</span> #end </td>
					        <td valign="top"><div align="left">Tajuk</div></td>
					        <td valign="top"><div align="center">:</div></td>
					        <td>
					        	<!-- <textarea $readOnly class="$disabled" name="txtTajuk" id="txtTajuk" cols="41" rows="5" 
					        onblur="this.value=this.value.toUpperCase();">$!txtTajuk</textarea> -->
					        	<textarea name="txtTajuk" id="txtTajuk" cols="41" rows="5" 
					        	onblur="this.value=this.value.toUpperCase();" $inputstyleread>$!txtTajuk</textarea>
					        </td>
					        <td>&nbsp;</td>
					        <td>
					         #if($!readOnly != "")
					         <p>Didaftar Oleh</p>
					         <p>Didaftar Pada</p>
					         #end
					          </td>
					        <td>&nbsp;</td>
					        <td>
					         #if($!readOnly != "")
					         <p><a href="_" title="_">$!diDaftarOleh</a></p>
					         <p>$!diDaftarPada</p>
					          #end
					         </td>
					      </tr>
					      <!--<tr>
					        <td valign="top">&nbsp;</td>
					        <td valign="top">&nbsp;</td>
					        <td valign="top">&nbsp;</td>
					          <td>&nbsp;</td>
					              <td width="1%">&nbsp;</td>
					        <td valign="top">&nbsp;</td>
					        <td valign="top">&nbsp;</td>
					        <td valign="top">&nbsp;</td>
					      </tr> -->
  						</table> ##$!readOnly 
    				</td>
  				</tr>
			</table>
		</fieldset>
		
    	</td>
	</tr>
	 						
#if($!readOnly == "")
	<!-- <tr>
        <td>
   			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
        </td>
	<tr>
	#if($pageMode == "kemaskini")		
		<td align="center">
			<input class="stylobutton" name="cmdSimpan" type="button" id="cmdSimpan" value="Simpan" onclick="failKemaskiniSimpan()" />
			<input class="stylobutton" name="cmdKembali" type="button" id="cmdKembali" value="Batal" onclick="viewMaklumatPermohonan($!idfail)"/>
		</td>
	#else		
		<td align="center">
			<input class="stylobutton" name="cmdSimpan" type="button" id="cmdSimpan" value="Simpan" onclick="Simpan('Simpan')" />
			<input class="stylobutton" name="cmdKembali" type="button" id="cmdKembali" value="Kembali" onclick="kembali()"/><img src="../img/emel.gif" title="Pemberitahuan melalui emel" >
		</td>
	#end		
	</tr> -->
	<td align="center">
			<!-- <input class="stylobutton" type="button" onclick="javascript:permohonanTerima($!idfail);" value="Terima" /> -->
			<input class="stylobutton100" type="button" onclick="javascript:viewMaklumatPermohonan($!idfail);" value="Seterusnya" />
		</td>
	</tr>
#else
	<tr>
		<td align="center">
			<input class="stylobutton100" type="button" onclick="javascript:permohonanTerima($!idfail);" value="Simpan & Email" />
			<!-- <input class="stylobutton" type="button" onclick="javascript:permohonanTolak($!idfail);" value="Tolak" /> -->
			
			<!-- <input class="stylobutton" type="button" onclick="javascript:cetakKulitFail('$idPermohonan');" value="Cetak Kulit Fail" />
			<input class="stylobutton" type="button" onclick="javascript:cetakDoket('$idPermohonan');" value="Cetak Doket" /> --> 
			<!-- <img src="../img/emel.gif" title="Pemberitahuan melalui emel" > -->
		</td>
	</tr>
#end
	
</table>
