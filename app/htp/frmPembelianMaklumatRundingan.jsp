<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">

  <tr>
    <td>
    <fieldset><legend>MAKLUMAT RUNDING HARGA</legend>
        <table width="100%" align="center" border="0">
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Harga JPPH</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
				        	<select name="unit_bersamaan" id="socLuas" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeJenisLuas('$changeLuas')">
								      #if($socLuas == 'MP')
								      	<option value="">SILA PILIH</option>
									  	<option value="MP" selected="selected"> MP - METER PERSEGI</option>
								        <option value="KP"> KP - KAKI PERSEGI</option>
								      
								      #elseif($socLuas == 'KP')
								        
								      	<option value="">SILA PILIH</option>
				  						<option value="MP"> MP - METER PERSEGI</option>
								        <option value="KP" selected="selected"> KP - KAKI PERSEGI</option>
								      						         
								      #elseif($socLuas == '')
								        
								      	<option value="" selected="selected">SILA PILIH</option>
				  						<option value="MP"> MP - METER PERSEGI</option>
								        <option value="KP"> KP - KAKI PERSEGI</option>
								                                            
								 	  #end
								</select>
							<input type="text" name="harga_bersamaan" id="harga_bersamaan" size="11" 
    						value="$!pembayaran.jumlahbayaran" onBlur="validateCurrency(this,this.value,'')"  $style>
						</td>
					</tr>
					
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Nilai Tanah Oleh JPPH</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="nilai_tnh" id="nilai_tnh" size="11" 
    						value="$!pembayaran.jumlahbayaran" onBlur="validateCurrency(this,this.value,'')"  $style>
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Nilai Bangunan Oleh JPPH</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="nilai_bgn" id="nilai_bgn" size="11" 
    						value="$!pembayaran.jumlahbayaran" onBlur="validateCurrency(this,this.value,'')"  $style>
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Harga Tawaran</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="harga_beli" id="harga_beli" size="11" 
    						value="$!pembayaran.jumlahbayaran" onBlur="validateCurrency(this,this.value,'')"  $style>
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Harga Dipersetujui</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="harga_setuju" id="harga_setuju" size="11" 
    						value="$!pembayaran.jumlahbayaran" onBlur="validateCurrency(this,this.value,'')"  $style>
						</td>
					</tr>					            
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Harga DipersetujuiTempoh Serahan
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="tempoh_serah" id="harga_setuju" size="11" 
    						value="$!pembayaran.jumlahbayaran" onBlur="validateCurrency(this,this.value,'')"  $style> Bulan
						</td>
					</tr>				
					
           <!-- <tr>
              	<td scope="row"></td>
              	<td>
              		<input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
                	<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()">
           		</td>
            </tr> -->
            <tr>
              <td scope="row">&nbsp;</td>
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
    </td>
  </tr>
  					<p align="center">
                       #if($pagemode == 'baru' || $pagemode == 'kemaskini')
                            
                            <div align="center">
                            	<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGHA_SimpanHakmilik()">
                            	<input type="reset" class="stylobutton" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="javascript:fGHA_BatalHakmilik()">
                    		</div>
                            
                            #elseif($pagemode == 'simpan')
                            
                             <div align="center">
                             	<input type="button" class="stylobutton" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGHA_KemaskiniHakmilik()">
                      			<input type="button" class="stylobutton" name="Cetakp" id="Cetakp" value="Cetak" onclick="javascript:senaraiSuratPeminjam('tablesurat');" />
                           		<input type="button" class="stylobutton" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGHA_KembaliHakmilik()">
                             </div>
                            
                            #end
                    </p>
</table>


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