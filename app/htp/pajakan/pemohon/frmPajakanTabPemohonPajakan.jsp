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
			            <td><input type="text" name="txtNoTelefon" size="30" maxlength="14" value="$beanMaklumatPemohon.tel" $readOnly class="$classDis" /></td>
			            </tr>
			          <tr>
			            <td>&nbsp;</td>
			            <td>No. Fax</td>
			            <td>:</td>
			            <td><input type="text" name="txtNoFax" size="30" maxlength="14"  value="$beanMaklumatPemohon.fax" $readOnly class="$classDis" /></td>
			            </tr>
			    	<tr>
			            <td width="1%"></td>
			            <td width="15%">Emel</td>
			            <td width="1%">:</td>
			            <td width="83%">
			            	<input type="text" name="txtemail" size="30" maxlength="80" value="$!beanMaklumatPemohon.emel" $readOnly class="$classDis" onKeyup="checkEmail(this.value)"/>
			            </td>
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
			            <td><input type="text" name="txtNoTelefon" size="30" maxlength="14" /></td>
			            </tr>
			          <tr>
			            <td>&nbsp;</td>
			            <td>No. Fax</td>
			            <td>:</td>
			            <td><input type="text" name="txtNoFax" size="30" maxlength="14" /></td>
			            </tr>
			    	<tr>
			            <td width="1%"></td>
			            <td width="15%">Emel</td>
			            <td width="1%">:</td>
			            <td width="83%">
			            	<input type="text" name="txtemail" size="30" maxlength="80" onBlur="checkEmail(this.value)"/>
			            </td>
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
	        	<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Simpan" onclick="javascript:simpanPemohon()" />
	     	#else
        		<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniPemohon()" />
        		<input class="stylobutton100" type="button" name="cmdCetak" id="cmdCetak" value="Surat" onclick="javascript:setTable('tableReport2')" />
			#end

		#elseif ($mode == 'update')
        	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanPemohon()" />
        	<!-- <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/> -->
        	<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalPemohon()" />
		#end
		#if ( $idStatus == '12' )
		#if($valFlagMohonFail.equalsIgnoreCase("hide"))
				#if($!statusSemasa.equals("penyedia"))
				<input type="button" class="stylobutton100" style="width:auto !important" name="cmdHantarSahkan" id="cmdHantarSahkan" value="Hantar Semakan" onclick="javascript:doHantarPenyemak()" />
				#end
			#end
		#end
		#if ( $idStatus == '148' )
			#if($valFlagMohonFail.equalsIgnoreCase("hide"))
				#if ($!statusSemasa.equals("penyemak"))
				<input type="button" class="stylobutton100" style="width:auto !important" name="cmdHantarSahkan" id="cmdHantarSahkan" value="Hantar Pengesahan" onclick="javascript:doHantarPengesahan()" />
				#end
			#end
		#end

		#if ( $idStatus == '240' )
			#if($valFlagMohonFail.equalsIgnoreCase("hide"))
				#if ($!statusSemasa.equals("pelulus"))
				<input type="button" class="stylobutton100" style="width:auto !important" name="cmdSahkan" id="cmdSahkan" value="Sahkan Permohonan" onclick="javascript:doSahkan()" />
				#end
			#end
		#end
   		</td>
	</tr>
</table>

<fieldset id="tableReport2" style="display:none;">
<legend><strong>SENARAI CETAKAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
      	<td><a href="#" onClick="javascript:cetakSuratPemohon('$!idPermohonan')"><font color="blue">SURAT KEPADA PEMOHON (SETELAH TERIMA PERMOHONAN)</font></a></td>
	  </tr>
	  <tr>
      	<td><a href="#" onClick="javascript:cetakSuratJKPTGNegeri('$!idPermohonan')"><font color="blue">SURAT KEPADA JKPTG NEGERI (PERMOHONAN LAPORAN TANAH)</font></a></td>
	  </tr>
	  <tr>
      	<td><a href="#" onClick="javascript:cetakSuratUlasanKJP('$!idPermohonan')"><font color="blue">SURAT KEPADA KJP (ULASAN)</font></a></td>
	  </tr>
	  <tr>
      	<td><a href="#" onClick="javascript:cetakSuratUlasanJPPH('$!idPermohonan')"><font color="blue">SURAT KEPADA JPPH (PENILAIAN)</font></a></td>
	  </tr>
	</table>
</fieldset>
##parse("app/htp/utiliti/javascript/javaScriptPajakanPemohon.jsp")

