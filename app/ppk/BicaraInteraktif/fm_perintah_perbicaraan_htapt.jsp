fm perintah perbicaraan htapt

<table width="100%" border="0" cellspacing="2" cellpadding="2">

	#set ($headerDetail = "")
	#foreach($headerDetail in $BeanHeaderDetail)

	<tr>
		<td width="30%" align="right">KETERANGAN HAKMILIK :</td>
       	<td width="70%" align="left"><font color="blue">$headerDetail.keteranganHakmilik</font></td>
	</tr>
	<tr>
		<td width="30%" align="right">STATUS PEMILIKAN :</td>
		<td width="70%" align="left"><font color="blue">$headerDetail.jenisPB</font></td>
	</tr>
	<tr>
		<td width="30%" align="right">JENIS TANAH :</td>
		<td width="70%" align="left">
			<font color="blue">$headerDetail.jenisTanah</font>
			<input name="idJenisTanah" type="hidden" value="$headerDetail.idJenisTanah" />
		</td>
	</tr>
	<tr>
		<td width="30%" align="right">BAHAGIAN SIMATI :</td>
		<td width="70%" align="left">
			<font color="blue">$headerDetail.bahagianSimati</font>
			<input type="hidden" name="bahagianSimatiAtas" value="$headerDetail.bahagianSimatiAtas" class="$inputTextClass">
			<input type="hidden" name="bahagianSimatiBawah" value="$headerDetail.bahagianSimatiBawah" class="$inputTextClass">
		</td>
	</tr>
	<tr>
		<td width="30%" align="right" valign="top">CATATAN :</td>
		<td width="70%" align="left">
			<textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea>
		</td>
	</tr>

	#end

	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2">
		#if ($SenaraiHTAPTDTL.size() > 10)
			<div style="width:100%;height:225;overflow:auto">
				#set($sizeUp="100%")
                #set($alignUp="left")
                #set($sizeDown="99%")
                #set($alignDown="left")
                #else
                #set($sizeUp="95%")
                #set($alignUp="center")
                #set($sizeDown="95%")
                #set($alignDown="center")
                #end

				<table width="$sizeUp" border="0" cellspacing="2" cellpadding="2" align="$alignUp">
					<tr>
					  	<td colspan="4" align="right"><div id="calculateTotal_result" style="color:#FF0000;font-weight:bold"></div></td>
					</tr>
                   	<tr class="table_header">
						<td align="center" width="5%">BIL</td>
						<td width="40%">NAMA WARIS</td>
						<td width="20%">STATUS WARIS</td>
						<td align="center" width="35%">BAHAGIAN WARIS</td>
 					</tr>

                    #set ($listHTAPTDTL = "")
                    #set ($cnt = 0)
                    #foreach ($listHTAPTDTL in $SenaraiHTAPTDTL)
                    #set ($cnt = $cnt+1)

					 <tr>
                     	<td align="center">
	                        <input name="idspentadbir" type="hidden" value="$listHTAPTDTL.idOB">
	                        <input name="status" type="hidden" value="$listHTAPTDTL.status">

                            $listHTAPTDTL.bil
                        </td>

                        #if ($listHTAPTDTL.status == '')
                        #if ($listHTAPTDTL.statusHidup == '1')

                        <td>
                        	$listHTAPTDTL.namaWaris &nbsp;&nbsp;<span class="style1"><strong>(MENINGGAL DUNIA)</strong></span>
                        </td>
                        #else
                        <td>$listHTAPTDTL.namaWaris</td>
                        #end
                        #else
                        <td>
                        	<a href="javascript:updatePAPTHTAPT('$listHTAPTDTL.idOB','$idPermohonanSimati','$idSimati','$listHTAPTDTL.status','$idPerintah','$mode','$idHTA','$flag_kemaskini_selesai','$idFail')">
                        		<font color="#0000FF">$listHTAPTDTL.namaWaris</font>
                        	</a>
                       	</td>
                        #end
                        <td>$listHTAPTDTL.status</td>
                        <td align="center">
                        	<input name="txtBA" type="text" size="15" maxlength="15" style="text-align:center" value="$listHTAPTDTL.BA" onBlur="validateBahagianWaris(this,this.value,$listHTAPTDTL.BA);calculateTotal();checkPembahagianGSA($cnt);" $readonly class="$inputTextClass">
                                  /
                            <input name="txtBB" type="text" size="15" maxlength="15" style="text-align:center" value="$listHTAPTDTL.BB" onBlur="validateBahagianWaris(this,this.value,$listHTAPTDTL.BB);calculateTotal();" $readonly class="$inputTextClass">
                        </td>
					</tr>
                    #end
				</table>

                #if ($SenaraiHTAPTDTL.size() > 10)
                </div>
                #else
                <br/>
                #end

				<table width="$sizeDown" border="0" cellspacing="2" cellpadding="2" align="$alignDown">
                            <!-- COMMENT BY PEJE - TIDAK DIPERLUKAN LAGI DAH
                          <tr>
                            <td colspan="4"><hr color="#000000"></td>
                          </tr>
                          <tr>
                            <td align="center" width="5%"><input type="checkbox" name="chkWarisHilang" id="chkWarisHilang" $checked onClick="test();calculateTotal();" value="1" $disabled>
                            </td>
                            <td width="45%">&nbsp;
                              <input type="button" name="cmdLantikPT" id="cmdLantikPT" value="Lantik Pentadbir" onClick="javascript:updatePAPTHTAPT('','$idPermohonanSimati','$idSimati','HILANG','$idPerintah','$mode','$idHTA','$flag_kemaskini_selesai')" $disabledHilang/>
                            </td>
                            <td width="15%">&nbsp;</td>
                            <td align="center" width="35%"><input name="txtBAHilang" type="text" size="14" maxlength="14" style="text-align:center" value="$BAHilang" onBlur="validateBahagianWaris(this,this.value,$BAHilang);calculateTotal();" $disabledHilang $readonly class="$inputTextClass">
                              /
                              <input name="txtBBHilang" type="text" size="14" maxlength="14" style="text-align:center" value="$BBHilang" onBlur="validateBahagianWaris(this,this.value,$BBHilang);calculateTotal();" $disabledHilang $readonly class="$inputTextClass">
                            </td>
                          </tr>
                          -->
					<tr class="table_header">
	                    <td width="5%">&nbsp;</td>
	                    <td colspan="2" align="left"><strong>JUMLAH</strong></td>
	                    <td align="center" width="35%"><input name="txtJumlahBA" id="txtJumlahBA" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBA" readonly class="disabled">
	                      /
	                      <input name="txtJumlahBB" id="txtJumlahBB" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBB" readonly class="disabled">
	                    </td>
					</tr>
					<tr>
						<td colspan="3">
							<i><font color="#ff0000">Perhatian</font> : Sila simpan pembahagian harta terlebih dahulu dan klik pada nama waris untuk melantik pemegang amanah / pentadbir bagi waris yang menerima bahagian.</i>
						</td>
                        <td align="center">
                        	#if ($mode == 'update')
                            <input type="button" name="cmdSamakanPembawah" id="cmdSamakanPembawah" value="Samakan Pembawah" onClick="javascript:samakanPembawah()"/>
                        	#if($flag_kemaskini_selesai != "yes")

                            <script>
                            	document.getElementById('cmdSamakanPembawah').style.display = "none";
                            </script>
                            #end
                            #end
                         </td>
					</tr>
					<tr>
                  		<td valign="bottom" colspan="4">&nbsp;</td>
                    </tr>
				</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			#if ($mode == 'update')
			<input type="button" name="cmdSimpanHTAPT" id="cmdSimpanHTAPT" value="Simpan" onClick="javascript:simpanKemaskiniHTAPT()"/>
			#if ($flag_kemaskini_selesai != "yes")

			<script>
			document.getElementById('cmdSimpanHTAPT').style.display = "none";
			</script>
			#end

			<input type="button" name="cmdPembahagianRata" id="cmdPembahagianRata" value="Pembahagian Separa" onClick="javascript:pembahagianSepara()"/>

			#if($flag_kemaskini_selesai != "yes")
			<script>
			document.getElementById('cmdPembahagianRata').style.display = "none";
            </script>
            #end

            <input type="button" name="cmdKosongkanPembahagian" id="cmdKosongkanPembahagian" value="Kosongkan" onClick="javascript:kosongkanPembahagian()"/>

            #if($flag_kemaskini_selesai != "yes")
            <script>
            document.getElementById('cmdKosongkanPembahagian').style.display = "none";
            </script>
            #end

			<input type="button" name="cmdPembahagianHartaLain" id="cmdPembahagianHartaLain" value="Pembahagian Harta Lain" onClick="javascript:pembahagianHartaLainHTA('$idPerintah','$idHTA')"/>

            #if($flag_kemaskini_selesai != "yes")
            <script>
            document.getElementById('cmdPembahagianHartaLain').style.display = "none";
            </script>
            #end

            <input type="button" name="cmdBatalHTAPT" id="cmdBatalHTAPT" value="Kembali" onClick="javascript:batalHTAPT()"/>
			#end

            #if ($mode == 'view')
            <input type="button" name="cmdBatalHTAPT" id="cmdBatalHTAPT" value="Kembali" onClick="javascript:batalHTAPT()"/>
			#end
		</td>
	</tr>
</table>