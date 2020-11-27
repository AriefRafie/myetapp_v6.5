<!-- saiz text -->
<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	font-weight: bold
}
-->
</style>

#set($saizTxtTujuanPengambilan="500")
#set($saizTxtRingkasanPengalaman="900")
#set($saizTxtJenisPerniagaan="500")
#set($saizTxtUndangUndang="500")

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	#if ($mode == 'view' || $mode == 'update')
	<tr>
    	<td colspan="2"><fieldset>
      		<legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      		<table width="100%" border="0" cellspacing="2" cellpadding="2">
      		#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
		        <tr>
		          	<td>&nbsp;</td>
		          	<td>No. Rujukan Surat Rasmi</td>
		          	<td>:
		            	<input name="txtNoRujukanSurat" type="text" class="$inputTextClass" id="txtNoRujukanSurat"  value="$beanMaklumatPermohonan.noRujSurat" size="43" maxlength="250" $readonly />
		            </td>
		        </tr>
		        <tr>
		          	<td>&nbsp;</td>
		          	<td>Tarikh Surat Rasmi</td>
		          	<td>:
		            	<input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat" onblur="check_date(this);cekTarikhSurat(this)" size="11" $readonly class="$inputTextClass"/>
			            <a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</a>
			        </td>
		        </tr>
		        <tr>
		        	<td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
		          	<td width="28%">Jenis Lesen</td>
		          	<td width="1%">:</td>
		          	<td width="70%">$selectJenisLesen</td>
		          	<td><input type="hidden" name="idJenisLesen" id="idJenisLesen" value="$beanMaklumatPermohonan.idJenisLesen"/></td>
		        </tr>
		        <tr>
		          	<td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
		          	<td width="28%">Jenis Tujuan</td>
		          	<td width="1%">:</td>
		          	#if($beanMaklumatPermohonan.idJenisLesen == 2)
			       		<td width="70%">MENGOREK</td>
			       	#elseif($beanMaklumatPermohonan.idJenisLesen == 3 || $beanMaklumatPermohonan.idJenisLesen == 4)
			       		<td width="70%">MENCARI GALI/MENJELAJAH</td>
			       	#else
			       		<td></td>
			       	#end
		        </tr>
		        <tr>
		          	<td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
		          	<td>Kaitan Tujuan</td>
		          	<td>:</td>
		          	<td>$selectTujuanKaitan
		          	#if ($mode == 'update')
			          	<a href="javascript:open_info()" class="help" title="info">
						<b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
						</a>
					#end
					</td>
		        </tr>
		        <tr>
		          	<td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
		          	<td valign="top">Tujuan </td>
		          	<td valign="top">:</td>
		          	<td valign="top"><textarea name="txtTujuanPengambilan" id="txtTujuanPengambilan" cols="43" rows="5" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtTujuanPengambilan,this.form.remLen1,$!saizTxtTujuanPengambilan);" onKeyDown="textCounter(this.form.txtTujuanPengambilan,this.form.remLen1,$!saizTxtTujuanPengambilan);" >$beanMaklumatPermohonan.tujuanPengambilan</textarea></td>
		        </tr>
		        #if ($mode == 'update')
		        <tr>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">Baki Aksara :&nbsp;
		            	<input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtTujuanPengambilan" />
		            </td>
		        </tr>
		        #end
		        <tr>
	          		<td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
	          		<td>Tempoh Lesen Dipohon</td>
	          		<td>:</td>
	          		<td>
	          			<select name="socTempoh" id="socTempoh" style="width:90px;" $readonly class="$disabled" $disabled >
          				$beanMaklumatPermohonan.socTempoh
          					#if ($beanMaklumatPermohonan.tempoh == '1')
                			<option>SILA PILIH</option>
                			<option value="1" selected="selected">1</option>
			                <option value="2">2</option>
			                <option value="3">3</option>
			                <option value="4">4</option>
			                <option value="5">5</option>
			           	 	#elseif ($beanMaklumatPermohonan.tempoh == '2')
			                <option>SILA PILIH</option>
			                <option value="1">1</option>
			                <option value="2" selected="selected">2</option>
			                <option value="3">3</option>
			                <option value="4">4</option>
			                <option value="5">5</option>
			            	#elseif ($beanMaklumatPermohonan.tempoh == '3')
			                <option>SILA PILIH</option>
			                <option value="1">1</option>
			                <option value="2">2</option>
			                <option value="3" selected="selected">3</option>
			                <option value="4">4</option>
			                <option value="5">5</option>
			                #elseif ($beanMaklumatPermohonan.tempoh == '4')
			                <option>SILA PILIH</option>
			                <option value="1">1</option>
			                <option value="2">2</option>
			                <option value="3">3</option>
			                <option value="4" selected="selected">4</option>
			                <option value="5">5</option>
			                #elseif ($beanMaklumatPermohonan.tempoh == '5')
			                <option>SILA PILIH</option>
			                <option value="1">1</option>
			                <option value="2">2</option>
			                <option value="3">3</option>
			                <option value="4">4</option>
			                <option value="5" selected="selected">5</option>
			            	#else
			                <option>SILA PILIH</option>
			                <option value="1">1</option>
			                <option value="2">2</option>
			                <option value="3">3</option>
			                <option value="4">4</option>
			                <option value="5">5</option>
			            	#end
			            </select>
			            TAHUN
			        </td>
			    </tr>
			    #if ($mode == 'update')
		        <tr>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top"><i><font color="#ff0000">* </font>Maksimum Tempoh Lesen Dipohon adalah 5 tahun.</i></td>
		        </tr>
		        #end
		        <tr>
		          	<td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
		          	<td valign="top">Ringkasan Pengalaman Pemohon</td>
		          	<td valign="top">:</td>
		          	<td valign="top"><textarea name="txtRingkasanPengalaman" id="txtRingkasanPengalaman" cols="43" rows="5" onblur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtRingkasanPengalaman,this.form.remLen2,$!saizTxtRingkasanPengalaman);" onKeyDown="textCounter(this.form.txtRingkasanPengalaman,this.form.remLen2,$!saizTxtRingkasanPengalaman);" $readonly class="$inputTextClass">$beanMaklumatPermohonan.pengalaman</textarea></td>
		        </tr>
		        #if ($mode == 'update')
		        <tr>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">Baki Aksara :&nbsp;
		            	<input type="text" readonly="readonly" class="disabled" name="remLen2" size="3" maxlength="3" value="$!saizTxtRingkasanPengalaman" />
		            </td>
		        </tr>
		        #end
		        <tr>
			  	   	<td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			  	   	<td valign="top">Undang-Undang Diperbadankan</td>
			  	   	<td valign="top">:</td>
			  	   	<td valign="top">
		           	  <textarea name="txtUndangUndang" id="txtUndangUndang" cols="43" rows="5" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtUndangUndang,this.form.remLen3,$!saizTxtUndangUndang);" onKeyDown="textCounter(this.form.txtUndangUndang,this.form.remLen3,$!saizTxtUndangUndang);" >$beanMaklumatPermohonan.undangUndang</textarea>
		           	</td>
		    	</tr>
		    	#if ($mode == 'update')
		        <tr>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">&nbsp;</td>
		          	<td width="28%">Baki Aksara :&nbsp;
		          		<input type="text" readonly="readonly" class="disabled" name="remLen3" size="3" maxlength="3" value="$!saizTxtUndangUndang" />
		          	</td>
		        </tr>
        		#end
        		<tr>
				  	<td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
				    <td valign="top">Jenis Perniagaan</td>
				    <td valign="top">:</td>
				    <td valign="top">
				       <textarea name="txtJenisPerniagaan" id="txtJenisPerniagaan" cols="43" rows="5" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtJenisPerniagaan,this.form.remLen4,$!saizTxtJenisPerniagaan);" onKeyDown="textCounter(this.form.txtJenisPerniagaan,this.form.remLen4,$!saizTxtJenisPerniagaan);" >$beanMaklumatPermohonan.jenisPerniagaan</textarea>
				    	#if ($mode != 'view')
				    	<a href="javascript:open_info1()" class="help" title="info">
						<b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
						</a>
						#end
				    </td>
				</tr>
				#if ($mode == 'update')
		        <tr>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">&nbsp;</td>
		          	<td valign="top">&nbsp;</td>
		          	<td width="28%">Baki Aksara :&nbsp;
		          		<input type="text" readonly="readonly" class="disabled" name="remLen4" size="3" maxlength="3" value="$!saizTxtJenisPerniagaan" />
		          	</td>
		        </tr>
		        #end
		     #end
      		</table>
      	</fieldset></td>
	</tr>
	#end
	<tr>
    	<td colspan="2">
    		<table width="100%" border="0" cellspacing="2" cellpadding="2">
        		#if ($mode == 'newProjek' || $mode == 'viewProjek' || $mode == 'updateProjek')
        		<tr>
		        	<td>#parse("app/php2/online/frmAPBMaklumatProjek.jsp")</td>
		        </tr>
		        <tr>
		          	<td>&nbsp;</td>
		        </tr>
		        #end
		        #if ($mode == 'view' || $mode == 'newProjek' || $mode == 'viewProjek' || $mode == 'updateProjek')
		        <tr>
          			<td><fieldset>
            			<legend><strong>SENARAI PROJEK</strong></legend>
            			<table align="center" width="100%">
			            	#if ($idStatus == '')
			              	#if ($mode == 'view')
			              	<tr>
			                	<td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:tambahProjek()"/></td>
			              	</tr>
			              	#end
			              	#end
			              	<tr class="table_header">
			                	<td scope="row" width="5%" align="center"><strong>Bil</strong></td>
			                	<td width="95%"><strong>Nama Projek</strong></td>
			              	</tr>
			              	#set ($listProjek = "")
			              	#if ($SenaraiProjek.size() > 0)
			              	#foreach ($listProjek in $SenaraiProjek)
			              	#if ($listProjek.bil == '')
			              	#set( $row = "row1" )
			              	#elseif (($listProjek.bil % 2) != 0)
			              	#set( $row = "row1" )
			              	#else
			              	#set( $row = "row2" )
			              	#end
			              	<tr>
			                	<td class="$row" align="center">$listProjek.bil</td>
			                	<td class="$row"><a href="javascript:paparProjek('$listProjek.idProjek')" class="style2">$listProjek.namaProjek</a></td>
			              	</tr>
			              	#end
			              	#else
			              	<tr>
			                	<td class="row1" align="center">&nbsp;</td>
			                	<td class="row1">Tiada Rekod</td>
			              	</tr>
			              	#end
			            </table>
			        </fieldset></td>
			    </tr>
		        #end
        	</table>
        </td>
     </tr>
     #if ($mode == 'view' || $mode == 'update')
  	<tr>
  		<td colspan="2"><fieldset>
      		<legend><strong>KAWASAN PERMOHONAN</strong></legend>
      		<table width="100%" border="0" cellspacing="2" cellpadding="2">
        	#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        		<tr>
		        	<td width="1%">&nbsp;</td>
		          	<td width="29%">Luar Perairan Negeri</td>
		          	<td width="70%">: $selectFlagLuar</td>
		        </tr>
		        <tr>
		          	<td>&nbsp;</td>
		          	<td>Negeri</td>
		          	<td>: $selectNegeri
		            </td>
		        </tr>
		        <tr>
		          	<td>&nbsp;</td>
		          	<td>Lokasi</td>
		          	<td>:
		            	<input name="txtLokasi" type="text" class="$inputTextClass" id="txtLokasi" onblur="this.value=this.value.toUpperCase();" value="$beanMaklumatPermohonan.lokasi" size="43" maxlength="250" $readonly />
		            </td>
		        </tr>
		        <tr>
		          	<td>&nbsp;</td>
		          	<td>Luas Dipohon</td>
		          	<td>:
		            	<input type="text" name="txtLuas" id="txtLuas" size="10" onkeyup="validateNumber(this,this.value);" maxlength="10" value="$beanMaklumatPermohonan.luas" $readonly class="$inputTextClass"/>
		            	$selectLuas
		            </td>
		        </tr>
        	#end
        	</table>
        </fieldset></td>
  	</tr>
  	<tr>
    	<td colspan="2"><fieldset>
      		<legend><strong>KEUPAYAAN KEWANGAN</strong></legend>
      		<table width="100%" border="0" cellspacing="2" cellpadding="2">
      			#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
      			<tr>
          			<td width="50%">
          			  <table width="100%" border="0" cellspacing="2" cellpadding="2">
              			<tr>
              				<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                			<td width="29%">Modal Dibenarkan</td>
                			<td width="70%">: RM
                  				<input name="txtModalBenar" type="text" class="$inputTextClass" id="txtModalBenar" value="$beanMaklumatPermohonan.modalBenar" size="20" $readonly />
                  			</td>
              			</tr>
              		  </table>
              		</td>
              		<td width="50%">
              		  <table width="100%" border="0" cellspacing="2" cellpadding="2">
              			<tr>
		                	<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			                <td width="29%">Modal Jelas</td>
			                <td width="70%">: RM
		   				   		<input name="txtModalJelas" type="text" class="$inputTextClass" id="txtModalJelas" value="$beanMaklumatPermohonan.modalJelas" size="20" $readonly />
		   				   	</td>
              			</tr>
            		  </table>
            		</td>
              	</tr>
      			#end
      		</table>
      	</fieldset></td>
    </tr>
    <tr>
    	<td colspan="2"><fieldset>
    		<legend><strong>JUMLAH MODAL UNTUK OPERASI</strong></legend>
    		<table width="100%" border="0" cellspacing="2" cellpadding="2">
      			#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
      			<tr>
          			<td width="50%">
          			  <table width="100%" border="0" cellspacing="2" cellpadding="2">
              			<tr>
			            	<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			                <td width="29%">Yang Terdapat Pada Masa Ini</td>
					        <td width="1%">:</td>
			          		<td width="70%">
			          			<input name="txtJumlahModal" type="text" class="$inputTextClass" id="txtJumlahModal" value="$beanMaklumatPermohonan.jumlahModal" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/>
          					</td>
          				</tr>
          			  </table>
          			</td>
          			<td width="50%">
          			  <table width="100%" border="0" cellspacing="2" cellpadding="2">
              			<tr>
			             	<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			             	<td width="29%">Yang Boleh Diadakan Oleh Pemohon dan Sumbernya</td>
			             	<td width="1%">:</td>
		          		 	<td width="70%">
		          		 		<input name="txtJumlahModal1" type="text" class="$inputTextClass" id="txtJumlahModal1" value="$beanMaklumatPermohonan.jumlahModal1" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/>
		          		 	</td>
              			</tr>
            		  </table>
            		</td>
          	  </tr>
          	  #end
    		</table>
    	</fieldset></td>
    </tr>
  	#end
  	<tr>
    	<td colspan="2">
    		<table width="100%" border="0" cellspacing="2" cellpadding="2">
        		#if ($mode == 'newKoordinat' || $mode == 'viewKoordinat' || $mode == 'updateKoordinat')
        		<tr>
		        	<td>#parse("app/php2/online/frmAPBMaklumatKoordinat.jsp")</td>
		        </tr>
		        <tr>
		          	<td>&nbsp;</td>
		        </tr>
        		#end
        		#if ($mode == 'view' || $mode == 'newKoordinat' || $mode == 'viewKoordinat' || $mode == 'updateKoordinat')
        		<tr>
          			<td><fieldset>
            			<legend><strong>SENARAI KOORDINAT</strong></legend>
            			<table align="center" width="100%">
            				#if ($idStatus == '')
			              	#if ($mode == 'view')
			              	<tr>
			                	<td colspan="3" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:tambahKoordinat()"/></td>
			              	</tr>
			              	#end
			              	#end
			              	<tr class="table_header">
			                	<td scope="row" width="5%" align="center"><strong>Bil</strong></td>
			                	<td width="35%"><strong>Label Koordinat</strong></td>
			                	<td align="center"><strong>Latitud (U)</strong></td>
			                	<td align="center"><strong>Longitud (T)</strong></td>
			              	</tr>
			              	#set ($listKoordinat = "")
			                #if ($SenaraiKoordinat.size() > 0)
			                #foreach ($listKoordinat in $SenaraiKoordinat)
			                #if ($listKoordinat.bil == '')
			                #set( $row = "row1" )
			                #elseif (($listKoordinat.bil % 2) != 0)
			                #set( $row = "row1" )
			                #else
			                #set( $row = "row2" )
			                #end
			                <tr>
			               		<td class="$row" align="center">$listKoordinat.bil</td>
			                	<td class="$row"><a href="javascript:paparKoordinat('$listKoordinat.idKoordinat')" class="style2">$listKoordinat.labelTitik</a></td>
			                	<td align="center" class="$row">$listKoordinat.darjahU&deg; &nbsp; $listKoordinat.minitU&prime; &nbsp; #if($!listKoordinat.saatU != '') $listKoordinat.saatU&Prime; #end</td>
			                	<td align="center" class="$row">$listKoordinat.darjahT&deg; &nbsp; $listKoordinat.minitT&prime; &nbsp; #if($!listKoordinat.saatT != '') $listKoordinat.saatT&Prime; #end</td>
			              	</tr>
			              	#end
			              	#else
			              	<tr>
			                	<td class="row1" align="center">&nbsp;</td>
			                	<td class="row1">Tiada Rekod</td>
			                	<td align="center" class="row1">&nbsp;</td>
			                	<td align="center" class="row1">&nbsp;</td>
			              	</tr>
			              	#end
            			</table>
            		</fieldset></td>
            	</tr>
            	#end
        	</table>
        </td>
    </tr>
    <tr>
	    <td colspan="2">
	      <table width="100%" border="0" cellspacing="2" cellpadding="2">
	        #if ($mode == 'newPakar' || $mode == 'viewPakar' || $mode == 'updatePakar')
	        <tr>
	        	<td>#parse("app/php2/online/frmAPBMaklumatPakar.jsp")</td>
	        </tr>
	        <tr>
	        	<td>&nbsp;</td>
	        </tr>
	        #end
	        #if ($mode == 'view' || $mode == 'newPakar' || $mode == 'viewPakar' || $mode == 'updatePakar')
	        	<tr>
          			<td><fieldset>
            			<legend><strong>SENARAI PAKAR</strong></legend>
            			<table align="center" width="100%">
            				#if ($idStatus == '')
			              	#if ($mode == 'view')
			              	<tr>
			                	<td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:tambahPakar()"/></td>
			              	</tr>
			              	#end
			              	#end
			              	<tr class="table_header">
			                	<td scope="row" width="5%" align="center"><strong>Bil</strong></td>
			                	<td width="95%"><strong>Nama Pakar</strong></td>
			             	</tr>
			              	#set ($listPakar = "")
			              	#if ($SenaraiPakar.size() > 0)
			              	#foreach ($listPakar in $SenaraiPakar)
			              	#if ($listPakar.bil == '')
			              	#set( $row = "row1" )
			              	#elseif (($listPakar.bil % 2) != 0)
			              	#set( $row = "row1" )
			              	#else
			              	#set( $row = "row2" )
			              	#end
			              	<tr>
			                	<td class="$row" align="center">$listPakar.bil</td>
			                	<td class="$row"><a href="javascript:paparPakar('$listPakar.idPakar')" class="style2">$listPakar.nama</a></td>
			              	</tr>
			              	#end
			              	#else
			              	<tr>
			                	<td class="row1" align="center">&nbsp;</td>
			                	<td class="row1">Tiada Rekod</td>
			              	</tr>
			              	#end
            			</table>
            		</fieldset></td>
            	</tr>
	        #end
	      </table>
	    </td>
	</tr>
	<tr>
    	<td colspan="2">
    		<table width="100%" border="0" cellspacing="2" cellpadding="2">
	        	#if ($mode == 'newPengarah' || $mode == 'updatePengarah' || $mode == 'viewPengarah')
	        	<tr>
		          <td> #parse("app/php2/online/frmAPBMaklumatPengarah.jsp") </td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		        </tr>
	        	#end
	        	#if ($mode == 'view' || $mode == 'newPengarah' || $mode == 'updatePengarah' || $mode == 'viewPengarah')
        		<tr>
          			<td><fieldset>
            			<legend><strong>SENARAI PENGARAH</strong></legend>
            			<table align="center" width="100%">
            				#if ($idStatus == '')
			              	#if ($mode == 'view')
			              	<tr>
			                	<td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:tambahPengarah()"/></td>
			              	</tr>
			              	#end
			              	#end
			              	<tr class="table_header">
			                	<td scope="row" width="5%" align="center"><strong>Bil</strong></td>
			                	<td width="40%"><strong>Nama Pengarah</strong></td>
			                	<td width="20%"><strong>No Pengenalan</strong></td>
			                	<td width="25%"><strong>Warganegara</strong></td>
			                	<td width="10%" align="center"><strong>Pegangan Saham</strong></td>
			              	</tr>
			              	#set ($listPengarah = "")
			              	#if ($SenaraiPengarah.size() > 0)
			              	#foreach ($listPengarah in $SenaraiPengarah)
			              	#if ($listPengarah.bil == '')
			              	#set( $row = "row1" )
			              	#elseif (($listPengarah.bil % 2) != 0)
			              	#set( $row = "row1" )
			              	#else
			              	#set( $row = "row2" )
			             	#end
			             	<tr>
			                	<td class="$row" align="center">$listPengarah.bil</td>
				                <td class="$row"><a href="javascript:paparPengarah('$listPengarah.idPengarah')" class="style2">$listPengarah.nama</a></td>
				                <td class="$row">$listPengarah.noPengenalan</td>
				                <td class="$row">$listPengarah.warganegara</td>
				                <td class="$row" align="center">RM $listPengarah.peganganSaham</td>
			              	</tr>
			              	#end
			              	#else
			              	<tr>
				                <td class="row1" align="center">&nbsp;</td>
				                <td class="row1">Tiada Rekod</td>
				                <td class="row1">&nbsp;</td>
				                <td class="row1">&nbsp;</td>
				                <td class="row1" align="center">&nbsp;</td>
			              	</tr>
			              	#end
    					</table>
    				</fieldset></td>
    			</tr>
    			#end
    		</table>
    	</td>
    </tr>
    #if ($mode == 'update')
  	<tr>
    	<td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  	</tr>
  	#end
  	<tr>
    	<td colspan="2" align="center">
    	#if ($idStatus == '')
      	#if ($mode == 'view')
	      <input type="button" name="cdmCetak" id="cdmCetakBorang" value="Cetak Borang Permohonan" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"/>
	      <input type="button" name="cmdKemaskiniPermohonan" id="cmdKemaskiniPermohonan" value="Kemaskini" onclick="kemaskiniPermohonan()"/>
	      <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onclick="doBacklist()"/>
      	#elseif ($mode == 'update')
	      <input type="button" name="cmdSimpanKemaskiniPermohonan" id="cmdSimpanKemaskiniPermohonan" value="Simpan" onclick="simpanKemaskiniPermohonan()"/>
	      <input type="button" name="cmdBatalKemaskiniPermohonan" id="cmdSimpanKemaskiniPermohonan" value="Batal" onclick="batalKemaskiniPermohonan()"/>
      	#end
     	#else
	      <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
	  	#end
      	</td>
	</tr>
</table>
<script>
function validateCurrency(elmnt,content,content2) {
	content = content.replace(/,/g,''); content2 = content2.replace(/,/g,'');
	if (isNaN(content)) {
		elmnt.value = content2.toFixed(2);
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function formatNumber(input){
	var String = input.value;
	var result = new Intl.NumberFormat().format(String.replace(/, /g, ''));
	input.value = result;
}
</script>