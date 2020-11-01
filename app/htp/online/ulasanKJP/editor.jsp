 <!--frmPajakanTabUlasan.jsp-->
<!-- CL-02-026 -->
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>

            <table width="100%" border="0"  cellspacing="2" cellpadding="2">
             #if ($mode == 'newKJP' || $mode == 'updateKJP' || $mode == 'viewKJP')
              <tr>
                <td>
				  <fieldset><legend><strong>ULASAN KJP</strong></legend>
                	<table width="100%" border="0">
                ##foreach ($beanUlasanKJP in $BeanUlasanKJP)
                	<tr>
			            <td width="1%">##if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
			            <td width="18%">No Hakmilik</td>
			            <td width="1%">:</td>
			            <td width="80%"><input type="text" name="txtNoRujukanKJP" size="30" maxlength="39" value="$!ulasan.getNoHakmilik()" readOnly class="disabled" onBlur="this.value=this.value.toUpperCase();"/></td>
			      	</tr>
                	<tr>
			            <td width="1%">##if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
			            <td width="18%">No. Rujukan KJP</td>
			            <td width="1%">:</td>
			            <td width="80%"><input type="text" name="txtNoRujukanKJP" size="30" maxlength="39" value="$!ulasan.getNo()" $readOnly class="$classDis" onBlur="this.value=this.value.toUpperCase();"/></td>
			      	</tr>
                	<tr>
			            <td width="1%">##if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
			            <td width="18%">Tarikh Terima Dari KJP</td>
			            <td width="1%">:</td>
			            <td width="80%">
			            	<input type="text" name="txdTarikhTerimaKJP" size="10" maxlength="30" value="$!ulasan.getTarikhTerimaTxt()" $readOnly class="$classDis" onBlur="this.value=this.value.toUpperCase();check_date(this);"/>
			                #if ($mode == 'newKJP' || $mode == 'updateKJP')
			                	<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhTerimaKJP',false,'dmy');">
                    		#end
			            </td>
			      	</tr>
                	<tr>
			            <td width="1%">##if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
			            <td width="18%">Keputusan</td>
			            <td width="1%">:</td>
			            <td width="80%">
                    <select name="socKeputusan" id="socKeputusan" class="$classDis" $classDis style="width:200">

                        #if($ulasan.getKeputusan() == 'S')
							<option value="">SILA PILIH</option>
							<option value="S" selected="selected">SETUJU</option>
							<option value="TS">TIDAK SETUJU</option>

                        #elseif($ulasan.getKeputusan() == 'TS')
 							<option value="">SILA PILIH</option>
                        	<option value="S" >SETUJU</option>
							<option value="TS" selected="selected" >TIDAK SETUJU</option>

                        #else
   							<option value="">SILA PILIH</option>
                        	<option value="S">SETUJU</option>
							<option value="TS">TIDAK SETUJU</option>

                        #end

                    </select>
			            </td>
			      	</tr>
                	<tr>
			            <td width="1%"></td>
			            <td width="18%" valign="top">Ulasan</td>
			            <td width="1%" valign="top">:</td>
			            <td width="80%" >
            				<textarea name="txtUlasanKJP" id="txtUlasanKJP" cols="50" rows="5"
            					onkeyup="textCounter(this.form.txtUlasanKJP,this.form.remtxtcatatan,1500);" onkeydown="textCounter(this.form.txtUlasanKJP,this.form.remtxtcatatan,1500);"
            					class="$classDis" $readOnly>$!ulasan.getUlasan()</textarea>
			            </td>
			      	</tr>
							     #if ($mode != 'viewKJP')
									<tr>
								        <td>&nbsp;</td>
								        <td>&nbsp;</td>
								        <td valign="top">&nbsp;</td>
								        <td><input type="text" readonly class="disabled" name="remtxtcatatan" size="3" maxlength="4" value="1500"> Baki Aksara </td>
								    </tr>
								#end
			      	                  <!-- <tr>
                    <td width="8%" align="right">#if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
                    <td>No. Rujukan KJP</td>
                    <td>:</td>
                    <td><input name="txtNoRujukanKJP" type="text" id="txtNoRujukanKJP" value="$beanUlasanKJP.noRujukan" size="40" class="$classDis" $readOnly onBlur="this.value=this.value.toUpperCase();" /></td>
                  </tr>
                  <tr>
                    <td width="8%" align="right">#if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
                    <td width="24%">Tarikh Hantar Ke KJP</td>
                    <td width="1%">:</td>
                    <td width="67%"><input type="text" name="txdTarikhHantarKJP" id="txdTarikhHantarKJP" size="10" value="$beanUlasanKJP.tarikhHantar" onblur="check_date(this)" class="$classDis" $readOnly />
                      #if ($mode == 'newKJP' || $mode == 'updateKJP')<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhHantarKJP',false,'dmy');">
                    #end
                     </td>
                  </tr>
                  <tr>
                    <td align="right">#if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
                    <td>Tarikh Terima Dari KJP</td>
                    <td>:</td>
                    <td><input type="text" name="txdTarikhTerimaKJP" id="txdTarikhTerimaKJP" size="10" value="$beanUlasanKJP.tarikhTerima" onblur="check_date(this)" class="$classDis" $readOnly />
                      #if ($mode == 'newKJP' || $mode == 'updateKJP')<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhTerimaKJP',false,'dmy');">
                    #end
                    </td>
                  </tr>
                  <tr>
                    <td align="right">#if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
                    <td valign="middle"></td>
                    <td valign="middle">:</td>
                    <td valign="middle">

                    <select name="socKeputusan" id="socKeputusan" class="$classDis" $classDis>

                        #if($beanUlasanKJP.status == 'S')
						<option value="S" selected="selected">SETUJU</option>
						<option value="TS">TIDAK SETUJU</option>

                        #else
                        <option value="S" >SETUJU</option>
						<option value="TS" selected="selected" >TIDAK SETUJU</option>

                        #end

                    </select>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">&nbsp;</td>
                    <td valign="top">Ulasan</td>
                    <td valign="top">:</td>
                    <td valign="top"><textarea name="txtUlasanKJP" id="txtUlasanKJP" cols="50" rows="5" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly>$beanUlasanKJP.ulasanKJP</textarea></td>

                  </tr>-->

                  ##end
                  <tr>
                  	<td colspan="4">&nbsp;</td>
                  <tr>
                  	<td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>
         </td>
              </table>
           		</fieldset>
                </td>
              </tr>

        	#if ($mode == 'newKJP' || $mode == 'updateKJP')

			<tr>
	  			<td>
	        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	        	</td>
	     	</tr>

            #end

              <tr align="center">
                <td>
           	#if ($mode == 'newKJP')
            		<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUlasanKJP2()" />
                	<input class="stylobutton100" type="reset" name="cmdKosong" id="cmdKosong" value="Kosongkan"/>
                 	<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUlasanKJP()" />
			#elseif ($mode == 'viewKJP')
				<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniUlasanKJP()" />
				<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalUlasanKJP2()" />
			#elseif ($mode == 'updateKJP')
				<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdateKJP2()" />
				<input class="stylobutton100" type="reset" name="cmdKosong" id="cmdKosong" value="Kosongkan"/>
				<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateKJP()" />
			#end
                </td>
              </tr>
              <!--<tr>
                <td>&nbsp;</td>
              </tr> -->

              #end
              <tr>
                <td>&nbsp;</td>
              </tr>
            </table>
            <p>&nbsp;</p>
<!-- </fieldset> -->


<fieldset id="tableReport3" style="display:none;">
<legend><strong>SENARAI CETAKAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
      	<td><a href="#" onClick="javascript:cetakSuratUlasanKJP('$!idPermohonan')"><font color="blue">SURAT KEPADA KJP (ULASAN)</font></a></td>
	  </tr>
    </table>
</fieldset>

