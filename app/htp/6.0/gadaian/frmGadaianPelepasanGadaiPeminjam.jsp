<table width="99%" border="0">
  <tr>
    <td>

		<fieldset><legend>MAKLUMAT PEMINJAM</legend>
		
		<table width="100%" border="0">
		#foreach ( $peminjam in $pp ) 
			 <tr>
		    <td width="33%"><div align="left"><strong>Nama </strong></div></td>
		    <td width="1%"><strong>:</strong></td>
		    <td width="66%">$!peminjam.getNama() 
		    
		
		    </td>
		    </tr>
		  <tr>
		    <td><div align="left"><strong>Alamat </strong></div></td>
		    <td><strong>:</strong></td>
		    <td>$!peminjam.getAlamat1()</td>
		    </tr>
		  <tr>
		    <td><div align="left"><strong></strong></div></td>
		    <td>&nbsp;</td>
		    <td>$!peminjam.getAlamat2()</td>
		    </tr>
		  <tr>
		    <td><div align="left"><strong></strong></div></td>
		    <td>&nbsp;</td>
		    <td>$!peminjam.getAlamat3()</td>
		    </tr>
		  <tr>
		    <td><div align="left"><strong>Poskod </strong></div></td>
		    <td><strong>:</strong></td>
		    <td>$!peminjam.getPoskod()</td>
		    </tr>
		  <tr>
		    <td><div align="left"><strong>Daerah </strong></div></td>
		    <td><strong>:</strong></td>
		    <td>$peminjam.getDaerah().getNamaDaerah()</td>
		    </tr>
		  <tr>  
		  	<tr>
		    <td><div align="left"><strong>Negeri </strong></div></td>
		    <td><strong>:</strong></td>
		    <td>$!peminjam.getNegeri().getNamaNegeri()</td>
		    </tr>
		
		    <td><div align="left"><strong>No. Telefon </strong></div></td>
		    <td><strong>:</strong></td>
		    <td>$!peminjam.getTel()</td>
		    </tr>
		  <tr>
		    <td><div align="left"><strong>No. Fax </strong></div></td>
		    <td><strong>:</strong></td>
		    <td>$!peminjam.getFax()</td>
		    </tr>
		  <tr>
		    <td><div align="left"></div></td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    </tr>
		#end
		</table>
		
		<table	width="100%">
			<tr>
				<td>
			
			<fieldset><legend>Maklumat Hakmilik</legend>
			<table width="100%" border="0">
			  <tr>
			    <td><strong>Gadaian Pendua No. Perserahan </strong></td>
			    <td><strong>:</strong></td>
			    <td>$!peminjam.noPerserahan</td>
			  </tr>
				#foreach($mo in $mt)
			    
			   
			  <tr>
			    <td width="34%"><strong>No. Hakmilik</strong></td>
			    <td width="1%"><strong>:</strong></td>
			    <td width="65%">$!mo.getKodjenishakmilik()$!mo.nohakmilik</td>
			  </tr>
			  <!-- <tr>
			    <td width="34%"><strong>Jenis Hakmilik</strong></td>
			    <td><strong>:</strong></td>
			    <td>$peminjam.jenisHakmilik</td>
			  </tr> -->
			  <tr>
			    <td><strong>Kod / No. Lot</strong></td>
			    <td><strong>:</strong></td>
			    <td>$!mo.getKeteranganLot()$!mo.getNolot()</td>
			  </tr>
				#end
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			</table>
			</fieldset>
				</td>
			</tr>
		</table>

		<table width="100%">
			<tr>
				<td>						
						<fieldset>
						<legend>Pelepasan Gadaian</legend>
						<table width="100%" border="0">
						  <tr>
						    <td width="34%"><span class="labelmandatory">*</span><strong>Tarikh Pelepasan Gadaian</strong></td>
						    <td width="1%"><strong>:</strong></td>
						    <td width="65%"><input type="text" name="txTarikhLepasGadai" id="txTarikhLepasGadai" size="11" maxlength="10" value="$!TarikhLepasGadai" $classDis $modeDis onblur="check_date(this);semakTarikhHariIni(document.${formName}.txTarikhLepasGadai); onkeyup="validateNumber(this,this.value);"/>
						    
						    #if($pagemode == 'new' || $pagemode == 'update')
						      <img src="../img/calendar.gif" alt="calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txTarikhLepasGadai',false,'dmy');" />
						      #end
						      
						      </td>
						    </tr>
						  <tr>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						  </tr>
						  <tr>
						    <td colspan="3" align="center"></td>
						    </tr>

						</fieldset>

				</td>
			</tr>
		</table>

				<table width="100%">
					<tr>
						<td>		
						    
						    #if($pagemode == 'new')
						     	<div align="center">
						        	<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGPGP_SimpanPelepasan()">
						        	<input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan" onclick="javascript:fGPGP_BatalPelepasan()">
						        </div>
							#elseif($pagemode == 'view')
								<div align="center">
						 			<input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGPGP_KemaskiniPelepasan()">
						  			<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fSFGA_seterusnya('$!idFail','')">
								</div>
							#elseif ($pagemode == 'update')
								<div align="center">
						  		<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGPGP_SimpanPelepasan()">
							  	<input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan" onclick="javascript:fGPGP__BatalPelepasan()">
						 		 </div>
							#end
						
						</td>
					</tr>
				</table>

		</fieldset>
		
		</td>
	</tr>
</table>	

<input type="hidden" name="idFail" value="$idFail" />
<input type="hidden" name="idPermohonan" value="$idPermohonan" />
