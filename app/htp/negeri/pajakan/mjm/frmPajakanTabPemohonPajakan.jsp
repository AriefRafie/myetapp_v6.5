<!-- frmPajakanTabPemohonPajakan.jsp -->
<!-- CL-02-025 -->
<table width="100%" border="0">
	<tr>
    	<td >
    		
			<fieldset><legend><strong>MAKLUMAT PEMOHON</strong></legend>
				<table width="100%" border="0" >
			#if($BeanMaklumatPemohon.isEmpty()==false)

				#foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
			  		<input name="idPemohon" type="hidden" id="idPemohon" value="$beanMaklumatPemohon.idPemohon"/>
			     	<tr>
			            <td width="1%"></td>
			            <td width="15%">MyID/MyCoID</td>
			            <td width="1%">:</td>
			            <td width="83%"><input type="text" name="txtnorujukan" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80" value="$!beanMaklumatPemohon.noPemohon" $readOnly class="$classDis" /></td>
			      	</tr>			          
			      	<tr>
			            <td width="1%">#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
			            <td width="15%">Nama</td>
			            <td width="1%">:</td>
			            <td width="83%"><input type="text" name="txtNama" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80" value="$beanMaklumatPemohon.nama" $readOnly class="$classDis" /></td>
			      	</tr>
			          <tr>
			            <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
			            <td>Alamat </div></td>
			            <td>:</td>
			            <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="60" value="$beanMaklumatPemohon.alamat1" $readOnly class="$classDis" /></td>
			            </tr>
			          <tr>
			            <td></td>
			            <td></td>
			            <td>&nbsp;</td>
			            <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="60" value="$beanMaklumatPemohon.alamat2" $readOnly class="$classDis" /></td>
			            </tr>
			          <tr>
			            <td></td>
			            <td></td>
			            <td>&nbsp;</td>
			            <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="60" value="$beanMaklumatPemohon.alamat3" $readOnly class="$classDis" /></td>
			            </tr>
			          <tr>
			            <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
			            <td>Poskod </td>
			            <td>:</td>
			            <td><input type="text" name="txtPoskod" size="5" maxlength="5" onBlur="validatePoskod(this,this.value);" value="$beanMaklumatPemohon.poskod" $readOnly class="$classDis" /></td>
			            </tr>
			          <tr>
			            <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
			            <td>Negeri</td>
			            <td>:</td>
			            <td>$selectNegeri</td>
			            </tr>
			          <tr>
			            <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
			            <td>Bandar</td>
			            <td>:</td>
			            <td>$selectDaerah</td>
			            </tr>
			          <tr>
			            <td>&nbsp;</td>
			            <td>No. Telefon</td>
			            <td>:</td>
			            <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$beanMaklumatPemohon.tel" $readOnly class="$classDis" /></td>
			            </tr>
			          <tr>
			            <td>&nbsp;</td>
			            <td>No. Fax</td>
			            <td>:</td>
			            <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$beanMaklumatPemohon.fax" $readOnly class="$classDis" /></td>
			            </tr>
			    	<tr>
			            <td width="1%"></td>
			            <td width="15%">Emel</td>
			            <td width="1%">:</td>
			            <td width="83%"><input type="text" name="txtemail" size="20" maxlength="80" value="$!beanMaklumatPemohon.emel" $readOnly class="$classDis" onBlur="checkEmail(this.value)"/></td>
			      	</tr>        
				#end
			
			#else
			     	<tr>
			            <td width="1%"></td>
			            <td width="15%">MyID/MyCoID</td>
			            <td width="1%">:</td>
			            <td width="83%"><input type="text" name="txtnorujukan" value="$!pemohon.getNoPemohon()" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80" /></td>
			      	</tr>			          
			      	<tr>
			            <td width="1%"><font color="#FF0000">*</font></td>
			            <td width="15%">Nama</td>
			            <td width="1%">:</td>
			            <td width="83%"><input type="text" name="txtNama" value="$!pemohon.getNama()" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80" /></td>
			      	</tr>
			          <tr>
			            <td><font color="#FF0000">*</font></td>
			            <td>Alamat </div></td>
			            <td>:</td>
			            <td><input type="text" name="txtAlamat1" value="$!pemohon.getAlamat1()"  onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="60" /></td>
			            </tr>
			          <tr>
			            <td></td>
			            <td></td>
			            <td>&nbsp;</td>
			            <td><input type="text" name="txtAlamat2" value="$!pemohon.getAlamat2()"  onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="60" /></td>
			            </tr>
			          <tr>
			            <td></td>
			            <td></td>
			            <td>&nbsp;</td>
			            <td><input type="text" name="txtAlamat3" value="$!pemohon.getAlamat3()"  onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="60" /></td>
			            </tr>
			          <tr>
			            <td><font color="#FF0000">*</font></td>
			            <td>Poskod </td>
			            <td>:</td>
			            <td><input type="text" name="txtPoskod" value="$!pemohon.getPoskod()" size="5" maxlength="5" onBlur="validatePoskod(this,this.value);" /></td>
			            </tr>
			          <tr>
			            <td><font color="#FF0000">*</font></td>
			            <td>Negeri</td>
			            <td>:</td>
			            <td>$!selectNegeri</td>
			            </tr>
			          <tr>
			            <td><font color="#FF0000">*</font></td>
			            <td>Bandar</td>
			            <td>:</td>
			            <td>$!selectDaerah</td>
			            </tr>
			          <tr>
			            <td>&nbsp;</td>
			            <td>No. Telefon</td>
			            <td>:</td>
			            <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" /></td>
			            </tr>
			          <tr>
			            <td>&nbsp;</td>
			            <td>No. Fax</td>
			            <td>:</td>
			            <td><input type="text" name="txtNoFax" size="20" maxlength="14" /></td>
			            </tr>
			    	<tr>
			            <td width="1%"></td>
			            <td width="15%">Emel</td>
			            <td width="1%">:</td>
			            <td width="83%"><input type="text" name="txtemail" size="20" maxlength="80" onBlur="checkEmail(this.value)"/></td>
			      	</tr>  
			
			#end
			    	<tr>
			        	<td>&nbsp;</td>
			            <td>&nbsp;</td>
			            <td>&nbsp;</td>
			            <td>&nbsp;</td>
			      	</tr>
			       	<tr>
			            <td>&nbsp;</td>
			            <td>&nbsp;</td>
			            <td colspan="2">
			            </td>
			      	</tr>
			      	
				</table>
			</fieldset>

    	</td>
	</tr>

        	#if ($mode == 'view')

				#if($BeanMaklumatPemohon.isEmpty())
			<tr>
	  			<td>
	        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	        	</td>
	     	</tr>
            
                #end
            
            #elseif ($mode == 'update')
			<tr>
	  			<td>
	        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	        	</td>
	     	</tr>
	     	
            #end 
	
	<tr>
    	<td align="center">
    	#if($mode == 'view')
    		#if($!BeanMaklumatPemohon.isEmpty())
	        	<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Simpan" onclick="javascript:SimpanPemohon()" />            
	     	#else
        		#if (!$!jenisAkses.equals('Readonly'))
        		<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniPemohon()" />        
				#end
			#end
		
		#elseif ($mode == 'update')
        	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanPemohon()" />
        	<input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
        	<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalPemohon()" />
		#end  
   		</td>
	</tr>
</table>