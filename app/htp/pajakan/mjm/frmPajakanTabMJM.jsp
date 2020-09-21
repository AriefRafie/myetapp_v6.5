<table width="100%" border="0">
	<tr>
    	<td >


<fieldset><legend>MEMORANDUM JEMAAH MENTERI</legend>
    <table width="100%" border="0">
      <tr>
        <td colspan="2">

            <table width="100%" border="0">
	              <tr>
	                <td width="1%">#if ($mode == 'Xview'||$mode == 'Xupdate')<font color="#FF0000">*</font>#end</td>
	                <td width="28%">Tarikh Hantar Kepada PTP</td>
	                <td width="1%">:</td>
	                <td width="70%">
					##if($!readOnly.equals(''))
	                	<input type="text" name="txdTHPTP" id="txdTHPTP" size="11" value="$!tarikhHantarPTP" class="$classDis" $readOnly onBlur="check_date(this)" />
	                	<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTHPTP',false,'dmy');">
	              	##else
	              		##$!tarikhHantarPTP
	              	##end
	              	</td>
	              </tr>
	              <tr>
	                <td align="right">#if ($mode == 'Xview'||$mode == 'Xupdate')<font color="#FF0000">*</font>#end</td>
	                <td>Tarikh Terima Dari PTP</td>
	                <td>:</td>
	                <td>
					##if($!readOnly.equals(''))
	                	<input type="text" name="txdTTPTP" id="txdTTPTP" size="11" value="$!tarikhTerimaPTP" class="$classDis" $readOnly onBlur="check_date(this)" />
	                	<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTTPTP',false,'dmy');">
	               	##else
	              	##	$!tarikhTerimaPTP
	              	##end
	                </td>
	              </tr>
	              <tr>
	                <td>#if ($mode == 'Xview'||$mode == 'Xupdate')<font color="#FF0000">*</font>#end</td>
	                <td>Tarikh Hantar Kepada Bahagian TUP</td>
	                <td>:</td>
	                <td>
					##if($!readOnly.equals(''))
	                	<input type="text" name="txdTHTUP" id="txdTHTUP" size="11" value="$!tarikhHantarKSU" class="$classDis" $readOnly onBlur="check_date(this)" />
	                  	<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTHTUP',false,'dmy');">
	              	##else
	              	##	$!tarikhHantarKSU
	              	##end
	                </td>
	              </tr>
	              <tr>
	                <td>#if (($mode == 'view' && $!readOnly.equals('')) || $mode == 'update' )<font color="#FF0000">*</font>#end</td>
	                <td>Tarikh Mesyuarat Jemaah Menteri</td>
	                <td>:</td>
	                <td>
					##if($!readOnly.equals(''))
	                	<input type="text" name="txdTMJM" id="txdTMJM" size="11" value="$!tarikhMesyuaratMJM" class="$classDis" $readOnly onBlur="check_date(this)" />
	                  	<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTMJM',false,'dmy');">
	              	##else
	              	##	$!tarikhMesyuaratMJM
	              	##end
	              	</td>
	              </tr>
	              <tr>
	                <td>#if (($mode == 'view' && $!readOnly.equals('')) ||$mode == 'update')<font color="#FF0000">*</font>#end</td>
	                <td>Tarikh Terima Keputusan</td>
	                <td>:</td>
	                <td>
					##if($!readOnly.equals(''))
	                	<input type="text" name="txdTTK" id="txdTTK" size="11" value="$!tarikhTerimaKeputusan" class="$classDis" $readOnly onBlur="check_date(this)" />
						<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTTK',false,'dmy');">
	              	##else
	              	##	$!tarikhTerimaKeputusan
	              	##end
	              	</td>
	              </tr>
	              <tr>
	                <td>#if ($($mode == 'view' && $!readOnly.equals('')) ||$mode == 'update')<font color="#FF0000">*</font>#end</td>
	                <td>No. Memorandum</td>
	                <td>:</td>
	                <td>
					##if($!readOnly.equals(''))
	                	<input type="text" name="txtNoMemorandum" size="30" value="$!noMemorandum" class="$classDis" $readOnly onBlur="this.value=this.value.toUpperCase();" /></td>
	              	##else
	              	##	$!noMemorandum
	              	##end
	              </tr>
	              <tr>
	                <td>#if (($mode == 'view' && $!readOnly.equals('')) ||$mode == 'update')<font color="#FF0000">*</font>#end</td>
	                <td>Keputusan</td>
	                <td>:</td>
	                <td>
	          	##if ($mode == 'view')

                	##if ($!readOnly.equals(''))
                	<!--
	            		<select name="txtKeputusan" id="txtKeputusan" class="$classDis" $readOnly style="width:200">
	                    	<option value="">SILA PILIH</option>
	                  		<option value="L" >LULUS</option>
	                  		<option value="TL">TIDAK LULUS</option>
	                	</select>  -->
	                ##else
 						##if($!keputusan == 'L')
						##	LULUS
						##elseif($!keputusan == 'TL' )
						##	TIDAK LULUS
						##end

	                ##end

	            ##else
	            	<select name="txtKeputusan" id="txtKeputusan" class="$classDis" $readOnly style="width:200">
	                	#if($!keputusan == 'L' )
	                    	<option value="">SILA PILIH</option>
	                  		<option value="L" selected="selected">LULUS</option>
	                  		<option value="TL">TIDAK LULUS</option>
	                  	#elseif($!keputusan == 'TL' )
	                    	<option value="">SILA PILIH</option>
	  						<option value="L">LULUS</option>
	                  		<option value="TL" selected="selected">TIDAK LULUS</option>
	                  	#else
                   			<option value="">SILA PILIH</option>
	  						<option value="L">LULUS</option>
	                  		<option value="TL">TIDAK LULUS</option>
	                  	#end
	                </select>
	         	##end
	                </td>
	              </tr>
	              <tr>
	                <td valign="top">#if (($mode == 'view' && $!readOnly.equals('')) ||$mode == 'update')<font color="#FF0000">*</font>#end</td>
	                <td valign="top">Keterangan Kelulusan</td>
	                <td valign="top">:</td>
	                <td valign="top">
					##if($!readOnly.equals(''))
	                	<textarea name="txtKeterangan" id="txtKeterangan" cols="50" rows="5"
						onkeyup="textCounter(this.form.txtKeterangan,this.form.remtxtcatatan,1500);"
						onkeydown="textCounter(this.form.txtKeterangan,this.form.remtxtcatatan,1500);"
						class="$classDis" $readOnly >$!tindakanLanjut</textarea>
	              	##else
	              	##	$!tindakanLanjut
	              	##end
	              	</td>
	              </tr>
	            	#if (($mode == 'view' && $!readOnly.equals('')) ||$mode == 'update')
									<tr>
								        <td>&nbsp;</td>
								        <td>&nbsp;</td>
								        <td valign="top">&nbsp;</td>
								        <td><input type="text" readonly class="disabled" name="remtxtcatatan" size="4" maxlength="4" value="1500"> Baki Aksara </td>
								    </tr>
					#end
	              <tr>
	                <td valign="top">#if ($mode == 'Xview'||$mode == 'Xupdate')<font color="#FF0000">*</font>#end</td>
	                <td valign="top">Tarikh Makluman Keputusan Kepada Pemohon</td>
	                <td valign="top">:</td>
	                <td valign="top">
					##if($!readOnly.equals(''))
	                	<input type="text" name="txdTMKPemohon" size="11" id="txdTMKPemohon" value="$!tarikhMaklumanKeputusan" class="$classDis" $readOnly onblur="check_date(this)" maxlength="10" />
						<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTMKPemohon',false,'dmy');" />
					##else
	              	##	$!tarikhMaklumanKeputusan
	              	##end
					</td>
	              </tr>
					#if($!readOnly.equals(''))
	              <tr>
	                <td valign="top"></td>
	                <td valign="top">Bil. Lampiran</td>
	                <td valign="top">:</td>
	                <td valign="top">
							<input type=text size=2 name=jumlahlampiran value=$!num_files $readOnly onBlur="doChangeJumlahLampiran('3',this,'$!action');"> (<font size=1 color=red>Sila masukkan jumlah lampiran</font>)
					</td>
	              </tr>

					#end

	             	#if($!readOnly.equals(''))

						#if ($mode != 'update' )
	              		<tr>
			                <td valign="top"></td>
			                <td valign="top">Lampiran</td>
			                <td valign="top">:</td>
			                <td valign="top">
	                		#foreach( $i in [1..$num_files] )
							<input id="fileupload" name="fileupload" type="file" size="54" $readOnly  class="$disabled" /></br>
							#end
							</td>
	              		</tr>
						#else
						<tr>
			                <td valign="top"></td>
			                <td valign="top"></td>
			                <td valign="top"></td>
			                <td valign="top">
							$!namafail
							#set ( $cnt = 0 )
							#foreach($mo in $senaraidokumen)
							    #set ( $cnt = $cnt + 1 )
								<!--	<tr>
									  <td class="$row"><a href="javascript:XdeleteDetailImej($mo.idDokumen,$mo.idLampiran)">$cnt.</a></td>
									  <td class="$row"><a href="javascript:viewDetailImej($mo.idLampiran)" class="stylelabel">$!mo.perihal</a></td>
									  <td class="$row">$!mo.keterangan</td>
									  <td class="$row"><a href="javascript:cetakImej($mo.idLampiran)" class="stylelabel">$!mo.namaFail</a></td>
							 		  <td class="$row">
							 		  	<input type="button" name="cmdHapusDoc" value ="Hapus" onClick="deleteDetailImej($mo.idDokumen,$mo.idLampiran)"></
							 		  </td>
							    	</tr> -->
							    	$!mo.namaFail
							    	<a class="opener" href="javascript:cetakImej($mo.idLampiran)"
										onclick="cetakImej($mo.idLampiran); return false;"
										onkeypress="window.open(this.href); return false;">
										<!-- <img src="../img/icons/opener-new-window-up.gif" alt="new window" title="new window"
										onmouseover="this.src='../img/icons/opener-new-window-down.gif'"
										onmouseout="this.src='../img/icons/opener-new-window-up.gif'" /> -->
										<img src="../img/main.png" alt="new window" title="new window" width="20" height="15"/>
									</a>
									<a class="opener" href="javascript:deleteDetailImej($mo.idDokumen,$mo.idLampiran)"
										onclick="deleteDetailImej($mo.idDokumen,$mo.idLampiran); return false;">
										<img src="../img/online/x.gif" alt="hapus" width="20" height="15"/>
									</a>
									<br>
							#end
							</td>
	              		</tr>

	              		<tr>
			                <td valign="top"></td>
			                <td valign="top">Lampiran</td>
			                <td valign="top">:</td>
			                <td valign="top">
	                		#foreach( $i in [1..$num_files] )
							<input id="fileupload" name="fileupload" type="file" size="54" $readOnly  class="$disabled" /></br>
							#end
							</td>
	              		</tr>
	              			              		<tr>
			                <td>&nbsp;</td>
			                <td>&nbsp;</td>
			                <td>&nbsp;</td>
			                <td><font size=1 color=red>**Pastikan fail yang ingin di <i>upload</i> menggunakan format &quot;.doc&quot; atau &quot;.pdf&quot;</font></td>
			              </tr>

						#end

					#else
	              		<tr>
			                <td valign="top"></td>
			                <td valign="top">Lampiran</td>
			                <td valign="top">:</td>
			                <td valign="top">

					##if ($mode == 'view' || $mode == 'kemaskini')
						$!namafail
						#set ( $cnt = 0 )
						#foreach($mo in $senaraidokumen)
						    #set ( $cnt = $cnt + 1 )
						    	$!mo.namaFail
						    	<a class="opener" href="javascript:cetakImej($mo.idLampiran)"
									onclick="cetakImej($mo.idLampiran); return false;"
									onkeypress="window.open(this.href); return false;">
									<!-- <img src="../img/icons/opener-new-window-up.gif" alt="new window" title="new window"
									onmouseover="this.src='../img/icons/opener-new-window-down.gif'"
									onmouseout="this.src='../img/icons/opener-new-window-up.gif'" /> -->
									<img src="../img/main.png" alt="new window" title="new window" width="20" height="15"/>
								</a><br>
						#end
							</td>
	              		</tr>


					#end


            </table>

        </td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>

    </table>
</fieldset>


    	</td>
	</tr>
        	#if ($mode == 'view')

				#if($!readOnly.equals(''))
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
        	#if ($mode == 'view')
				#if($!readOnly.equals(''))
	                <input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanMemo()" />
	            #else
	                <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniMemo()" />
	                <input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:hapusMJM()" />
	                <!-- <input class="stylobutton100" name="cmdcetak" type="button" value="Previu" onclick="javascript:cetakPemajak('$!idPermohonan');"/> -->
	                <input class="stylobutton100" type="button" name="cmdCetak" id="cmdCetak" value="Surat" onclick="javascript:setTable('tableReport4')" />
	            #end

                #if ($idStatus == '65' || $idStatus == '86') <!-- 65=MJM,86=Diluluskan  -->
                <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Selesai Memorandum" onclick="javascript:seterusnyaSelesaiMJM()" />
                #end
            #elseif ($mode == 'update')
                <input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanMemo()" />
                <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalMemo()" />
            #end
   		</td>
	</tr>
</table>

<fieldset id="tableReport4" style="display:none;">
<legend><strong>SENARAI CETAKAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
      	<td><a href="#" onClick="javascript:cetakSuratTawaran('$!idPermohonan')"><font color="blue">SURAT TAWARAN & SURAT SETUJU TERIMA</font></a></td>
	  </tr>
	  <tr>
      	<td><a href="#" onClick="javascript:cetakPemajak('$!idPermohonan')"><font color="blue">DERAF PERJANJIAN PAJAKAN</font></a></td>
	  </tr>
	</table>
</fieldset>
