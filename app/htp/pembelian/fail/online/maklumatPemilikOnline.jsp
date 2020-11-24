<fieldset>
<legend><strong>MAKLUMAT PEMILIK</strong></legend><table width="100%" border="0">
#if($buttonMode == "0")
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td>No. Hakmilik</td>
              <td>:</td>
              <td>
                  <select name="ddownHakmilik" id="ddownHakmilik">
                   <option value="" selected >Sila Pilih</option>
                  #foreach($mo in $mt)
                  	#if($select == "$!mo.getIdhakmilikurusan()")
                    <option value="$!mo.getIdhakmilikurusan()" selected >$!mo.getJenisKeterangan() - $!mo.nohakmilik </option>
                    #else
                    <option value="$!mo.getIdhakmilikurusan()">$!mo.getJenisKeterangan() - $!mo.nohakmilik </option>
                    #end
				  #end     
                  </select>
           </td>
            </tr>
            <tr>
              <td width="20%"><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td width="20%"><div align="left">Nama Pemilik</div></td>
              <td width="1%">:</td>
              <td width="59%"><input type="text" name="txtNamaPemaju" id="txtNamaPemaju" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="80" value="$!pemilik.getNama()" $inputstyleread /></td>
            </tr>
            <tr>
              <td></td>
              <td><div align="left">Jenis Pengenalan</div></td>
              <td>:</td>
              <td>$selectJenisNoPB</td>
            </tr>
            <tr>
              <td></td>
              <td><div align="left">No.</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoRuj" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="80" value="$!pemilik.getNoRujukan()" $inputstyleread /></td>
            </tr>
            <tr>
              <td><div align="right"></div></td>
              <td><div align="left">Alamat</div></td>
              <td>:</td>
              <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat1()" $inputstyleread/></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"></div></td>
              <td>&nbsp;</td>
              <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat2()" $inputstyleread/></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"></div></td>
              <td>&nbsp;</td>
              <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat3()" $inputstyleread/></td>
            </tr>
            <tr>
              <td><div align="right"></div></td>
              <td><div align="left">Poskod</div></td>
              <td>:</td>
              <td><input type="text" name="txtPoskod" size="5" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$!pemilik.getPoskod()"  $inputstyleread/></td>
            </tr>
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td><div align="left">Negeri</div></td>
              <td>:</td>
              <td>$!socNegeri</td>
            </tr>
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td><div align="left">Daerah</div></td>
              <td>:</td>
              <td>$!socDaerah</td>
            </tr>
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td><div align="left">No. Telefon</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$!pemilik.getTel()"  onblur="validateNumber(this,this.value)" $inputstyleread/></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left">No. Fax</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$!pemilik.getFax()"  onblur="validateNumber(this,this.value)" $inputstyleread/></td>
            </tr>
            <tr>
             <td><div align="right"><strong></strong></div></td>
              <td><div align="left">Emel</div></td>
              <td>:</td>
              <td><input type="text" name="txtEmel" size="20" maxlength="65"  value="$!pemilik.getEmel()"  $inputstyleread/></td>
            </tr>

            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"><strong></strong></div></td>
              <td><strong></strong></td>
              <td>&nbsp;</td>
            </tr>
            <tr style="display:$Style3">
              <td colspan="4">
                <div align="center"><strong>
                #if (($!idjawatan.equals("20")|| $!idjawatan.equals("24")) || $!idjawatan.equals("9"))
                <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:detailPemilikOnline()">
                #end
                <input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('viewpemilikonline')">
           		 </div>
           		</td>
           	</tr>
            
		#elseif($buttonMode == "1")
		<tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td>No. Hakmilik</td>
              <td>:</td>
              <td>
                  <select name="ddownHakmilik" id="ddownHakmilik">
                   <option value="" selected>Sila Pilih</option>
                  #foreach($mo in $mt)
                  	#if($select == "$!mo.getIdhakmilikurusan()")
                    <option value="$!mo.getIdhakmilikurusan()" selected>$!mo.getJenisKeterangan() - $!mo.nohakmilik </option>
                    #else
                    <option value="$!mo.getIdhakmilikurusan()">$!mo.getJenisKeterangan() - $!mo.nohakmilik </option>
                    #end
				  #end     
                  </select>
           </td>
            </tr>
            <tr>
              <td width="20%"><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td width="20%"><div align="left">Nama Pemilik</div></td>
              <td width="1%">:</td>
              <td width="59%"><input type="text" name="txtNamaPemaju" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="80" value="$!pemilik.getNama()"  /></td>
            </tr>
            <tr>
              <td></td>
              <td><div align="left">Jenis Pengenalan</div></td>
              <td>:</td>
              <td>$selectJenisNoPB</td>
            </tr>
            <tr>
              <td></td>
              <td><div align="left">No.</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoRuj" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="80" value="$!pemilik.getNoRujukan()" /></td>
            </tr>
            <tr>
              <td><div align="right"></div></td>
              <td><div align="left">Alamat</div></td>
              <td>:</td>
              <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat1()" /></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"></div></td>
              <td>&nbsp;</td>
              <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat2()"/></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"></div></td>
              <td>&nbsp;</td>
              <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat3()" /></td>
            </tr>
            <tr>
              <td><div align="right"></div></td>
              <td><div align="left">Poskod</div></td>
              <td>:</td>
              <td><input type="text" name="txtPoskod" size="5" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$!pemilik.getPoskod()"  /></td>
            </tr>
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td><div align="left">Negeri</div></td>
              <td>:</td>
              <td>$!socNegeri</td>
            </tr>
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td><div align="left">Daerah</div></td>
              <td>:</td>
              <td>$!socDaerah</td>
            </tr>
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td><div align="left">No. Telefon</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$!pemilik.getTel()"  onblur="validateNumber(this,this.value)"/></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left">No. Fax</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$!pemilik.getFax()"  onblur="validateNumber(this,this.value)" /></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left">Emel</div></td>
              <td>:</td>
              <td><input type="text" name="txtEmel" size="20" maxlength="65"  value="$!pemilik.getEmel()"  /></td>
            </tr>

            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"><strong></strong></div></td>
              <td><strong></strong></td>
              <td>&nbsp;</td>
            </tr>
		<tr style="display:$Style3">
              <td colspan="4">
                <div align="center"><strong>
                #if (($!idjawatan.equals("20")|| $!idjawatan.equals("24")) || $!idjawatan.equals("9"))
                <input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanPemilik()">
                #end
                <input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('viewpemilikonline')">
       			 </div>
           	</td>
         </tr>
        #else
        
        <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td>No. Hakmilik</td>
              <td>:</td>
              <td>
                  <select name="ddownHakmilik" id="ddownHakmilik">
                   <option value="" selected>Sila Pilih</option>
                  #foreach($mo in $mt)
                  	#if($select == "$!mo.getIdhakmilikurusan()")
                    <option value="$!mo.getIdhakmilikurusan()" selected>$!mo.getJenisKeterangan() - $!mo.nohakmilik </option>
                    #else
                    <option value="$!mo.getIdhakmilikurusan()">$!mo.getJenisKeterangan() - $!mo.nohakmilik </option>
                    #end
				  #end     
                  </select>
           </td>
            </tr>
            <tr>
              <td width="20%"><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td width="20%"><div align="left">Nama Pemilik</div></td>
              <td width="1%">:</td>
              <td width="59%"><input type="text" name="txtNamaPemaju" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="80" value="$!pemilik.getNama()"  /></td>
            </tr>
            <tr>
              <td></td>
              <td><div align="left">Jenis Pengenalan</div></td>
              <td>:</td>
              <td>$selectJenisNoPB</td>
            </tr>
            <tr>
              <td></td>
              <td><div align="left">No.</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoRuj" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="80" value="$!pemilik.getNoRujukan()" /></td>
            </tr>
            <tr>
              <td><div align="right"></div></td>
              <td><div align="left">Alamat</div></td>
              <td>:</td>
              <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat1()" /></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"></div></td>
              <td>&nbsp;</td>
              <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat2()"/></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"></div></td>
              <td>&nbsp;</td>
              <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat3()" /></td>
            </tr>
            <tr>
              <td><div align="right"></div></td>
              <td><div align="left">Poskod</div></td>
              <td>:</td>
              <td><input type="text" name="txtPoskod" size="5" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$!pemilik.getPoskod()"  /></td>
            </tr>
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td><div align="left">Negeri</div></td>
              <td>:</td>
              <td>$!socNegeri</td>
            </tr>
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td><div align="left">Daerah</div></td>
              <td>:</td>
              <td>$!socDaerah</td>
            </tr>
            <tr>
              <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
              <td><div align="left">No. Telefon</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$!pemilik.getTel()"  onblur="validateNumber(this,this.value)"/></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left">No. Fax</div></td>
              <td>:</td>
              <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$!pemilik.getFax()"  onblur="validateNumber(this,this.value)" /></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left">Emel</div></td>
              <td>:</td>
              <td><input type="text" name="txtEmel" size="20" maxlength="65"  value="$!pemilik.getEmel()"  /></td>
            </tr>

            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"><strong></strong></div></td>
              <td><strong></strong></td>
              <td>&nbsp;</td>
            </tr>
            
        <tr style="display:$Style3">
              <td colspan="4">
                <div align="center"><strong>
        
					#set($portal_role = "${session.getAttribute('myrole')}")
					#if ($portal_role!='online_kjpagensi')
                <input class="stylobutton100" type="button" name="cmdkemaskini" id="cmdkemaskini" value="Simpan" onclick="doAjaxCall${formName}('updatePemilik')">
                	#end
                <input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('viewpemilikonline')">
                #end
              </strong></div></td>
            </tr>
            <tr>
              <td><div align="right"><strong></strong></div></td>
              <td><div align="left"></div></td>
              <td><strong></strong></td>
              <td>&nbsp;</td>
            </tr>
    </table>
  </fieldset>
  <input type="hidden" name="txtIdHakmilikUrusan" value="$!pemilik.getIdHakmilikurusanPB()" />
	 <input type="hidden" name="Idpihakberkepentingan" value="$!pemilik.getIdpihakberkepentingan()" />
     <input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>