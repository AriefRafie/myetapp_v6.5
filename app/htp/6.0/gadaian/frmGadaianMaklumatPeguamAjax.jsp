                #set ($pAlamat1 = "PEGUAMBELA DAN PEGUAMCARA")
                #foreach ( $peguam in $Peguam )
                	#set ($IdPeguam = $peguam.IdPeguam)
                	#set ($pNama = $peguam.Nama)
                    #set ($pAlamat1 = $peguam.Alamat1)
                    #set ($pAlamat2 = $peguam.Alamat2)
                    #set ($pAlamat3 = $peguam.Alamat3)
                    #set ($pPoskod = $peguam.Poskod)
                    #set ($pNoTel = $peguam.NoTel)
                    #set ($pNoFax = $peguam.NoFax)
                #end
                
                #set ($btnNamePeguam = "value='Kosongkan'")
				#if ($IdPeguam != "")
					#set ($btnNamePeguam = "value='Batal'")
				#end
        	<fieldset>
            	<legend>MAKLUMAT PEGUAM</legend>
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
                            <input type="text" name="txtNamaPeguam" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80"  value="$pNama" $mode $classDis />
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
							<input type="text" name="txtAlamat1Peguam" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$pAlamat1" $mode $classDis />
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
							<input type="text" name="txtAlamat2Peguam" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$pAlamat2" $mode $classDis />
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
							<input type="text" name="txtAlamat3Peguam" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$pAlamat3" $mode $classDis />
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
							<input type="text" name="txtPoskodPeguam" size="5" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$pPoskod" $mode $classDis />
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
							$selectBNegeri
						</td>
					</tr>
               		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'readonly') * #end</span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Bandar</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							$selectBDaerah
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
							<input type="text" name="txtNoTelefonPeguam" size="20" maxlength="14" value="$pNoTel" $mode $classDis />
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
							<input type="text" name="txtNoFaxPeguam" size="20" maxlength="14" value="$pNoFax" $mode $classDis />
						</td>                
					</tr>
                    <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                 	</tr>
    		</table> 

           </fieldset>         
		#if($pagemode == 'baru' || $pagemode == 'kemaskini')                            
		<table width="100%">
			<tr>
	        		<td>
	        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	        		</td>
	           	</tr>
	
		</table>                    
		#end	
			<p>
					<p align="center">
                       #if($pagemode == 'baru' || $pagemode == 'kemaskini')                            
                            <div align="center">
                            	<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGHA_SimpanPeguam()">
                            	<input type="reset" class="stylobutton100" name="cmdBatal" id="cmdBatal" $btnNamePeguam onclick="javascript:fGHA_BatalPeguam()">
                            </div>
                     	#elseif($pagemode == 'simpan')                            
                             <div align="center">
                             	<input type="button" class="stylobutton100" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javacript:fGHA_KemaskiniPeguam()">
                             	<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGHA_KembaliPeguam()">
                     		  	<input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:senaraiSuratPeguam('tablesurat');" />
                     		 </div>
                      	#end
                    </p>
           

<input type="hidden" name="idPeguam" value="$IdPeguam">
<input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
<input type="hidden" name="idFail" value="$IdFail">
<input type="hidden" name="noFail" value="$NoFail">
<input type="hidden" name="idPermohonan" value="$IdPermohonan">
<input type="hidden" name="style1" value="$Style1">
<input type="hidden" name="style2" value="$Style2">
<input type="hidden" name="idBebanan" value="$IdBebanan">
<input type="hidden" name="idPihakberkepentingan" value="$IdPihakberkepentingan">
<input type="hidden" name="idHTPGadaian" value="$idHTPGadaian"> 

<fieldset id="tablesurat" style="display:none;">
<legend>SENARAI SURAT</legend>
<table width="100%" border="0">
	#if($htpPermohonan.permohonan.pfdFail.tajukFail.contains('DISCHARGE OF CHARGE')) 
	<tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSuratDOCPeguam')" class="pautanms">SURAT TINDAKAN DAN PENGHANTARAN PENDUA KEPADA PEGUAM (DOC)</a></td>
  </tr>
	#elseif($htpPermohonan.permohonan.pfdFail.tajukFail.contains('SATISFACTION OF CHARGE')) 
		
	<tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSuratSOCPeguam')" class="pautanms">SURAT TINDAKAN DAN PENGHANTARAN PENDUA KEPADA PEGUAM (SOC)</a></td>
  </tr>
   #elseif($htpPermohonan.permohonan.pfdFail.tajukFail.contains('MEMORANDUM OF CHARGE'))
    <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSuratMOCPutrajaya')" class="pautanms">SURAT TINDAKAN DAN PENGHANTARAN PERJANJIAN YANG TELAH DITANDATANGAN (MOC-PUTRAJAYA)</a></td>
  </tr>
 
    <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSuratMOCSarawak')" class="pautanms">SURAT TINDAKAN DAN PENGHANTARAN PERJANJIAN YANG TELAH DITANDATANGAN (MOC-SARAWAK)</a></td>
  </tr>
   #elseif($htpPermohonan.permohonan.pfdFail.tajukFail.contains('MENANDATANGANI BORANG GADAIAN 16A'))
   <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSurat16AKPKTPeguam')" class="pautanms">SURAT IRINGAN 16A</a></td>
  </tr> 
   <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSurat16AKoperasiPeguam')" class="pautanms">SURAT IRINGAN 16A(TMP-JPK)</a></td>
  </tr> 
   #elseif($htpPermohonan.permohonan.pfdFail.tajukFail.contains('PELEPASAN GADAIAN 16N'))
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSurat16N')" class="pautanms">SURAT IRINGAN 16N</a></td>
  </tr> 
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSurat16NKPKTPeguam')" class="pautanms">SURAT IRINGAN 16N (KPKT)</a></td>
  </tr> 
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSurat16NKoperasi')" class="pautanms">SURAT IRINGAN 16N(TMP-JPK) </a></td>
  </tr>
   #end
   </table>
</fieldset>   

        