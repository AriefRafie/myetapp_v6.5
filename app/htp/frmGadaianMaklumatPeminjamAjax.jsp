
                #foreach ( $hakmilik in $Hakmilik )
                	#set ($IdPihakberkepentingan = $hakmilik.IdPihakberkepentingan)
                    #set ($IdBebanan = $hakmilik.IdBebanan)
                	#set ($Nama = $hakmilik.Nama)
                    #set ($Alamat1 = $hakmilik.Alamat1)
                    #set ($Alamat2 = $hakmilik.Alamat2)
                    #set ($Alamat3 = $hakmilik.Alamat3)
                    #set ($Poskod = $hakmilik.Poskod)
                    #set ($NoTel = $hakmilik.NoTel)
                    #set ($NoFax = $hakmilik.NoFax)
                    #set ($NoPerserahan = $hakmilik.NoPerserahan)
                #end
                
                #set ($idNegeri = "")
                #foreach ($negeri in $Info)
                	#set ($idNegeri = $negeri.idNegeri)
                #end
                
                #set ($btnNamePemilik = "value='Kosongkan'")
				#if ($IdPihakberkepentingan != "")
					#set ($btnNamePemilik = "value='Batal'")
				#end
         	<fieldset>
            	<legend>MAKLUMAT PEMINJAM</legend>
                <table width="100%" border="0">
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left"> Nama  </div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txtNama" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80" value="$Nama" $mode $classDis />
						</td>
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left"> Alamat  </div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat1" $mode $classDis />
						</td>
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory"></span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left"> </div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat2" $mode $classDis />
						</td>
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory"> </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">  </div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat3" $mode $classDis />
						</td>
					</tr>	
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left"> Poskod  </div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txtPoskod" size="5" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$Poskod" $mode $classDis />
						</td>	
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'readonly') * #end</span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Negeri</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							$selectANegeri
						</td>
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'readonly') * #end</span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Daerah</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							$selectADaerah
						</td>
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory"></span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left"> No. Telefon  </div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
                        	<input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$NoTel" $mode $classDis />
                       	</td>
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory"></span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">No. Fax</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
                        	<input type="text" name="txtNoFax" size="20" maxlength="14"  value="$NoFax" $mode $classDis />
						</td>
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'readonly') * #end</span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Gadaian Pendua No. Perserahan</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
                            <input type="text" name="txtNoPerserahan" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="50" value="$NoPerserahan" $mode $classDis />
						</td>
					</tr>						
					
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                    </table> 
	<table width="100%">
		<tr>
        		<td>
        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
        		</td>
           	</tr>

	</table>
           </fieldset>         
                    <p>
					<p align="center">
                       #if($pagemode == 'baru' || $pagemode == 'kemaskini')
                            
                            <div align="center">
                            	<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGHA_SimpanHakmilik()">
                            	<input type="reset" class="stylobutton" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="javascript:fGHA_BatalHakmilik()">
                    		</div>
                            
                            #elseif($pagemode == 'simpan')
                            
                             <div align="center">
                             	<input type="button" class="stylobutton" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGHA_KemaskiniHakmilik()">
                      			<input type="button" class="stylobutton" name="Cetakp" id="Cetakp" value="Cetak" onclick="javascript:senaraiSuratPeminjam('tablesuratp');" />
                           		<input type="button" class="stylobutton" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGHA_KembaliHakmilik()">
                             </div>
                            
                            #end
                    </p>
<input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
<input type="hidden" name="idFail" value="$IdFail">
<input type="hidden" name="noFail" value="$NoFail">
<input type="hidden" name="idPermohonan" value="$IdPermohonan">  
<input type="hidden" name="idPihakberkepentingan" value="$IdPihakberkepentingan">  
<input type="hidden" name="idBebanan" value="$IdBebanan">  
<input type="hidden" name="idHTPGadaian" value="$idHTPGadaian"> 

<fieldset id="tablesuratp" style="display:none;">
<legend>SENARAI SURAT</legend>
<table width="100%" border="0">
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSuratDOCPeminjam')" class="pautanms">SURAT TINDAKAN DAN PENGHANTARAN PENDUA KEPADA PEMINJAM (DOC)</a></td>
  </tr>
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSuratSOCPeminjam')" class="pautanms">SURAT TINDAKAN DAN PENGHANTARAN PENDUA KEPADA PEMINJAM (SOC)</a></td>
  </tr>  
   <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSurat16A')" class="pautanms">SURAT IRINGAN 16A</a></td>
  </tr>   
</table>
</fieldset>                          