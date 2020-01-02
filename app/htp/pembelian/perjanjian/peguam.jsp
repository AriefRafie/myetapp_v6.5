<fieldset>
	<legend>
	
	Maklumat Peguam
	</legend>
	<table width="100%" border="0">
            <tr>
            	<td width="20%"><div align="right"><strong><font color="#FF0000">#if($peguamMode == "new" || $peguamMode == "update")* #end</font></strong></div></td>
              	<td width="14%"><div align="left">Nama Peguam</div></td>
              	<td width="1%">:</td>
              	<td width="65%"><input type="text" name="txtNamaPeguam" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="80" value="$!peguam.nama" $mode $classDis /></td>
            </tr>
            <tr>
               <td><div align="right"><strong></strong></div></td>
               <td><div align="left">Kod Peguam</div></td>
               <td>:</td>
               <td><input type="text" name="txtKodPeguam" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="50" value="$!peguam.noRujukan" $mode $classDis /></td>
             </tr>
                 <tr>
               <td><div align="right"></div></td>
               <td><div align="left">Alamat</div></td>
               <td>:</td>
               <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!peguam.alamat1" $mode $classDis /></td>
             </tr>
             <tr>
               <td><div align="right"><strong></strong></div></td>
               <td><div align="left"></div></td>
               <td>&nbsp;</td>
               <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!peguam.alamat2" $mode $classDis /></td>
             </tr>
             <tr>
               <td><div align="right"><strong></strong></div></td>
               <td><div align="left"></div></td>
               <td>&nbsp;</td>
               <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!peguam.alamat3" $mode $classDis /></td>
             </tr>
             <tr>
               <td><div align="right"></div></td>
               <td><div align="left">Poskod</div></td>
               <td>:</td>
               <td><input type="text" name="txtPoskod" size="5" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$!peguam.poskod" $mode $classDis /></td>
             </tr>
             <tr>
               <td><div align="right"></div></td>
               <td><div align="left">Negeri</div></td>
               <td>:</td>
               <td>$!selectBNegeri</td>
             </tr>
             <tr>
               <td><div align="right"></div></td>
               <td><div align="left">Daerah</div></td>
               <td>:</td>
               <td>$!selectBDaerah</td>
             </tr>
             <tr>
               <td><div align="right"><strong></strong></div></td>
               <td><div align="left">No. Telefon</div></td>
               <td>:</td>
               <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$!peguam.noTel" onblur="validateNumber(this,this.value)" $mode $classDis /></td>
             </tr>
             <tr>
               <td><div align="right"><strong></strong></div></td>
               <td><div align="left">No. Fax</div></td>
               <td>:</td>
               <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$!peguam.noFax" onblur="validateNumber(this,this.value)" $mode $classDis /></td>
           	</tr>
           	<tr>
    
		    	<td align="center" colspan="4">
		    		#if($peguamMode == "new")
		    		 	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanPeguam();" />
                      	<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />
		    		#elseif($peguamMode == "update")
		    		 	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updatePeguam();" />
                      	<!--<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />-->
             		  	<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Batal" onClick="setSelected(4,'Peguam','peguamview',0)">
		    		#else 		
		    			<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="kemaskiniPeguam()" />
                         <!--<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />-->
		    		#end
		    		
		    	
		    	</td>
		    
		    </tr>
	
	</table>

</fieldset>
<input type="hidden" name="idPeguam" value="$!peguam.idpeguam">