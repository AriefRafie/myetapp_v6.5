<table width="100%" border="0">
	<tr>
    	<td >


<fieldset><legend>PERAKUAN JAWATANKUASA PAJAKAN</legend>

#if($senaraiPerakuanJawatankuasa == [] && $mode == 'view')
      <table width="100%" border="0">
      <tr>
        <td colspan="2">

            <table width="100%" border="0">
	              <tr>
	                <td>#if (($mode == 'view' && $!readOnly.equals('')) ||$mode == 'update')<font color="#FF0000">*</font>#end</td>
	                <td>Syor Perakuan</td>
	                <td>:</td>
	                <td>
	            	<select name="txtKeputusan" id="txtKeputusan" class="$classDis" $readOnly style="width:200">
                   			<option value="">SILA PILIH</option>
	  						<option value="L">DIPERAKUKAN KE KABINET</option>
	                  		<option value="TL">TIDAK DIPERAKUKAN</option>
	                </select>
	                </td>
	              </tr>
	              <tr>
	                <td valign="top">#if (($mode == 'view' && $!readOnly.equals('')) ||$mode == 'update')<font color="#FF0000">*</font>#end</td>
	                <td valign="top">Keterangan Perakuan</td>
	                <td valign="top">:</td>
	                <td valign="top">
	                	<textarea name="txtKeterangan" id="txtKeterangan" cols="50" rows="5"
						onkeyup="textCounter(this.form.txtKeterangan,this.form.remtxtcatatan,1500);"
						onkeydown="textCounter(this.form.txtKeterangan,this.form.remtxtcatatan,1500);"
						class="$classDis" $readOnly ></textarea>
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
	                <td valign="top">Tarikh Perakuan</td>
	                <td valign="top">:</td>
	                <td valign="top">
	                	<input type="text" name="txdTPerakuan" size="11" id="txdTPerakuan" value="" class="$classDis" $readOnly onblur="check_date(this)" maxlength="10" />
						<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTPerakuan',false,'dmy');" />
					</td>
	              </tr>

            </table>

        </td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>

    </table>
   #elseif ($senaraiPerakuanJawatankuasa == [] && $mode == 'update')
      <table width="100%" border="0">
      <tr>
        <td colspan="2">

            <table width="100%" border="0">
	              <tr>
	                <td><font color="#FF0000">*</font></td>
	                <td>Syor Perakuan</td>
	                <td>:</td>
	                <td>
	            	<select name="txtKeputusan" id="txtKeputusan" style="width:200">
                   			<option value="">SILA PILIH</option>
	  						<option value="L">DIPERAKUKAN KE KABINET</option>
	                  		<option value="TL">TIDAK DIPERAKUKAN</option>
	                </select>
	                </td>
	              </tr>
	              <tr>
	                <td valign="top"><font color="#FF0000">*</font></td>
	                <td valign="top">Keterangan Perakuan</td>
	                <td valign="top">:</td>
	                <td valign="top">
	                	<textarea name="txtKeterangan" id="txtKeterangan" cols="50" rows="5"
						onkeyup="textCounter(this.form.txtKeterangan,this.form.remtxtcatatan,1500);"
						onkeydown="textCounter(this.form.txtKeterangan,this.form.remtxtcatatan,1500);"></textarea>
	              	</td>
	              </tr>
				  <tr>
				        <td>&nbsp;</td>
				        <td>&nbsp;</td>
				        <td valign="top">&nbsp;</td>
				        <td><input type="text" readonly class="disabled" name="remtxtcatatan" size="4" maxlength="4" value="1500"> Baki Aksara </td>
				  </tr>
	              <tr>
	                <td valign="top"><font color="#FF0000">*</font></td>
	                <td valign="top">Tarikh Perakuan</td>
	                <td valign="top">:</td>
	                <td valign="top">
	                	<input type="text" name="txdTPerakuan" size="11" id="txdTPerakuan" value="" onblur="check_date(this)" maxlength="10" />
						<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTPerakuan',false,'dmy');" />
					</td>
	              </tr>

            </table>

        </td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>

    </table>
  #else
#foreach ($perakuanJawatankuasa in $senaraiPerakuanJawatankuasa)

  <input type="hidden" value="$!perakuanJawatankuasa.idPerakuan" name="idPerakuan" />
    <table width="100%" border="0">
      <tr>
        <td colspan="2">

            <table width="100%" border="0">
	              <tr>
	                <td>#if (($mode == 'view' && $!readOnly.equals('')) ||$mode == 'update')<font color="#FF0000">*</font>#end</td>
	                <td>Syor Perakuan</td>
	                <td>:</td>
	                <td>
	            	<select name="txtKeputusan" id="txtKeputusan" class="$classDis" $readOnly style="width:200">
	                	#if($!perakuanJawatankuasa.keputusan == 'L' )
	                    	<option value="">SILA PILIH</option>
	                  		<option value="L" selected="selected">DIPERAKUKAN KE KABINET</option>
	                  		<option value="TL">TIDAK DIPERAKUKAN</option>
	                  	#elseif($!perakuanJawatankuasa.keputusan == 'TL' )
	                    	<option value="">SILA PILIH</option>
	  						<option value="L">DIPERAKUKAN KE KABINET</option>
	                  		<option value="TL" selected="selected">TIDAK DIPERAKUKAN</option>
	                  	#else
                   			<option value="">SILA PILIH</option>
	  						<option value="L">DIPERAKUKAN KE KABINET</option>
	                  		<option value="TL">TIDAK DIPERAKUKAN</option>
	                  	#end
	                </select>
	                </td>
	              </tr>
	              <tr>
	                <td valign="top">#if (($mode == 'view' && $!readOnly.equals('')) ||$mode == 'update')<font color="#FF0000">*</font>#end</td>
	                <td valign="top">Keterangan Perakuan</td>
	                <td valign="top">:</td>
	                <td valign="top">
	                	<textarea name="txtKeterangan" id="txtKeterangan" cols="50" rows="5"
						onkeyup="textCounter(this.form.txtKeterangan,this.form.remtxtcatatan,1500);"
						onkeydown="textCounter(this.form.txtKeterangan,this.form.remtxtcatatan,1500);"
						class="$classDis" $readOnly >$!perakuanJawatankuasa.ulasan</textarea>
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
	                <td valign="top">Tarikh Perakuan</td>
	                <td valign="top">:</td>
	                <td valign="top">
	                	<input type="text" name="txdTPerakuan" size="11" id="txdTPerakuan" value="$!perakuanJawatankuasa.tarikhMaklumanKeputusan" class="$classDis" $readOnly onblur="check_date(this)" maxlength="10" />
						<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTPerakuan',false,'dmy');" />
					</td>
	              </tr>

            </table>

        </td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>

    </table>

  #end
  #end

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
	                <input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanPerakuan()" />
	            #else
	                <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniPerakuan()" />
	            #end
            #elseif ($mode == 'update')
                <input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanPerakuan()" />
                <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalPerakuan()" />
            #end
   		</td>
	</tr>
</table>