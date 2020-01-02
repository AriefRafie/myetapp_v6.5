<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
<!--
.pautanms {color: #0000FF}
-->
</style>         
          
            <table width="100%" border="0"  cellspacing="2" cellpadding="2">
             #if ($mode == 'newSPHP' || $mode == 'updateSPHP' || $mode == 'viewSPHP')
              <tr>
                <td>
				  <fieldset>
				    <legend><strong>ULASAN SPHP</strong></legend>
				    <table width="100%" border="0">
                #foreach ($beanUlasanSPHP in $BeanUlasanSPHP)
                  <tr>
                    <td width="1%" >##if ($mode == 'newSPHP' || $mode == 'updateSPHP')<font color="#FF0000">*</font>#end</td>
                    <td width="18%">No. Rujukan SPHP</td>
                    <td width="1%">:</td>
                    <td width="80%"><input name="txtNoRujukanSPHP" type="text" id="txtNoRujukanSPHP" value="$beanUlasanSPHP.noRujukan" size="30" class="$classDis" $readOnly onBlur="this.value=this.value.toUpperCase();" /></td>
                  </tr>
                  <tr>
                    <td >#if ($mode == 'newSPHP' || $mode == 'updateSPHP')<font color="#FF0000">*</font>#end</td>
                    <td >Tarikh Hantar</td>
                    <td >:</td>
                    <td ><input type="text" name="txdTarikhHantarSPHP" id="txdTarikhHantarSPHP" size="10" value="$beanUlasanSPHP.tarikhHantar" onblur="check_date(this)" class="$classDis" $readOnly />
                      #if ($mode == 'newSPHP' || $mode == 'updateSPHP')<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhHantarSPHP',false,'dmy');">
                    #end          
                     </td>
                  </tr>
                  <tr>
                    <td align="right">##if ($mode == 'newSPHP' || $mode == 'updateSPHP')<font color="#FF0000">*</font>#end</td>
                    <td>Tarikh Terima</td>
                    <td>:</td>
                    <td><input type="text" name="txdTarikhTerimaSPHP" id="txdTarikhTerimaSPHP" size="10" value="$beanUlasanSPHP.tarikhTerima" onblur="check_date(this)" class="$classDis" $readOnly />
                      #if ($mode == 'newSPHP' || $mode == 'updateSPHP')<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhTerimaSPHP',false,'dmy');">
                    #end          
                    </td>
                  </tr>
                  <tr>
                    <td align="right">##if ($mode == 'newSPHP' || $mode == 'updateSPHP')<font color="#FF0000">*</font>#end</td>
                    <td valign="middle">Keputusan</td>
                    <td valign="middle">:</td>
                    <td valign="middle">
                    <select name="socKeputusan" id="socKeputusan" class="$classDis" $classDis style="width:200">

                	#if($beanUlasanSPHP.status == 'S')						
                      <option value="" >SILA PILIH</option>
                      <option value="S" selected="selected">SETUJU</option>
                      <option value="TS">TIDAK SETUJU</option>
              
                  	#elseif ($beanUlasanSPHP.status == 'TS')                        
                    	<option value="" >SILA PILIH</option>
                     	<option value="S" >SETUJU</option>
                      	<option value="TS" selected="selected" >TIDAK SETUJU</option>
              		
              		#else
                    	<option value="" >SILA PILIH</option>
                     	<option value="S" >SETUJU</option>
                      	<option value="TS" >TIDAK SETUJU</option>    		
              		
                  	#end
  
                    </select>
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">&nbsp;</td>
                    <td valign="top">Ulasan</td>
                    <td valign="top">:</td>
                    <td valign="top"><textarea name="txtUlasanSPHP" id="txtUlasanSPHP" cols="50" rows="5" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly>$beanUlasanSPHP.ulasan</textarea></td>

                  </tr>
                  #end
                  <!-- <tr>
                  	<td colspan="4">&nbsp;</td>
                  <tr>
                  	<td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>
                     
					</td> -->
              </table>
           		</fieldset>
                </td>
              </tr>
  
         	#if ($mode == 'newSPHP' || $mode == 'updateSPHP')

			<tr>
	  			<td>
	        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	        	</td>
	     	</tr>
	     	
            #end  
              
              <tr>
                <td align="center">
				#if ($mode == 'newSPHP')
                      <input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUlasanSPHP()" />
                      <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                      <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUlasanSPHP()" />
					#elseif ($mode == 'viewSPHP')
						<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniUlasanSPHP()" />
						<input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusSPHP()" />
						<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalUlasanSPHP()" />
					#elseif ($mode == 'updateSPHP')
						<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdateSPHP()" />
						<input class="stylobutton100"  type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
						<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateSPHP()" />
					#end
                
                </td>
              </tr>
              
              #end
              
	<tr>
   		<td>
      		<fieldset>
    		<legend><strong>SENARAI ULASAN SPHP</strong>    </legend>
    			<table align="center" width="100%">
			    #if ($mode == 'view')
					#if (!$!jenisAkses.equals('Readonly'))	
				    <tr>
				    	<td colspan="6" scope="row">
				        	<input class="stylobutton100" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruSPHP()"/>
				        	<input class="stylobutton100" name="cmdcetak" type="button" value="Cetak" onclick="javascript:senaraiDokumenSPHP('tablesurat');"/>
				        </td>
				   	</tr>
					#end			  	
				#end
					<tr class="table_header">
					    <td width="3%" scope="row" align="center">Bil.</td>
					    <td width="15%">No. Rujukan SPHP</td>
					    <td width="12%" align="center">Tarikh Hantar</td>
					    <td width="12%" align="center">Tarikh Terima</td>
					    <td width="15%" align="center">Keputusan</td>
					    <td width="43%" align="left">Ulasan</td>
	    			</tr>
      			#set ($list = "")
      			#if ($SenaraiUlasanSPHP.size() > 0)
      				#foreach ($list in $SenaraiUlasanSPHP)
      					#if ($list.bil == '')
      						#set( $row = "row1" )
      					#elseif (($list.bil % 2) != 0)
      						#set( $row = "row1")
      					#else 
      						#set( $row = "row2" )
      					#end
				      <tr>
				        <td class="$row" align="center" ><a href="javascript:paparSPHP('$!list.idUlasanSPHP')" >$!list.bil.</a></td>
				        <td class="$row" ><a href="javascript:paparSPHP('$!list.idUlasanSPHP')" class="style1">$!list.noRujukan</a></td>
				        <td class="$row" align="center" >$!list.tarikhHantar</td>
				        <td class="$row" align="center" >$!list.tarikhTerima</td>
				        <td class="$row" align="center" >$!list.statusName</td>
				        <td class="$row" align="left" >$!list.ulasan</td>
				       </tr>
      				#end
      			#else
				  <tr>
				    <td class="row1">&nbsp;</td>
				    <td class="row1" colspan="5">Tiada Rekod</td>
				    <!-- <td class="row1">&nbsp;</td>
				    <td class="row1">&nbsp;</td>
				    <td width="33%" class="row1">&nbsp;</td>
				    <td width="15%" class="row1">&nbsp;</td> -->
				  </tr>
      			#end
    			</table>
  
        	</fieldset>               
		</td>
	</tr>

	<tr>
    	<td>&nbsp;</td>
	</tr>
</table>
   			<fieldset id="tablesurat" style="display:none;">
				<legend>SENARAI SURAT/DOKUMEN</legend>
				<table width="100%" border="0">
				  <tr>
				    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=7&idpermohonan=$idPermohonan','urusan','&template=HTPPajakanMemoPenawaran')" class="pautanms">MEMO PENAWARAN KEPADA SPHP</a></td>
				  </tr>
				  <!--<tr>
				    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSuratSOCPeminjam')" class="pautanms">SURAT TINDAKAN DAN PENGHANTARAN PENDUA KEPADA PEMINJAM (SOC)</a></td>
				  </tr>  
				   <tr>
				    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$IdPermohonan','urusan','&template=HTPGadaianSurat16A')" class="pautanms">SURAT IRINGAN 16A</a></td>
				  </tr>   -->
				</table>
				</fieldset>   	
<p>&nbsp;</p>
<!-- </fieldset> -->

