<style type="text/css">
<!--
.style1 {color: #0033FF}
.row1_ {
	color: #F00;
}
.e {
	color: #F00;
}
-->
.expired {
	color:red
}
.blink { animation: blink 1s steps(5, start) infinite; }
@keyframes blink {
    to {
        visibility: hidden;
    }
}

</style>
#set($txtCatatan_='')
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newPajakan' || $mode == 'updatePajakan' || $mode == 'viewPajakan')
  <tr>
    <td>

    	<fieldset>
    	<legend><strong>MAKLUMAT PAJAKAN</strong></legend>

            <table width="100%" border="0" cellspacing="2" cellpadding="2">
            #foreach ($beanPajakan in $BeanPajakan)
            <tr>
                <td class="e">&nbsp;#if ($mode == 'newPajakan' || $mode == 'updatePajakan')* #end</td>
                <td>Maklumat Tanah</td>
                <td>:</td>
                <td>$selectTanah<!-- <select name="socTanah" id="socTanah" class="$classDis" $classDis>
                <option>SILA PILIH</option>

                </select> --></td>
        	</tr>
              <tr>
                <td width="1%" class="e">#if ($mode == 'newPajakan' || $mode == 'updatePajakan')* #end</td>
                <td width="28%">Tarikh Tandatangan</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="txdTarikhTandatangan" id="txdTarikhTandatangan" size="11" value="$beanPajakan.tarikhTandatangan" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newPajakan' || $mode == 'updatePajakan')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhTandatangan',false,'dmy');" />
                #end
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Tarikh Mula Pajakan</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhMulaPajakan" id="txdTarikhMulaPajakan" size="11" value="$beanPajakan.tarikhMula" onblur="check_date(this);cal_tarikh_luput()" class="$classDis" $readOnly/>
                #if ($mode == 'newPajakan' || $mode == 'updatePajakan')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhMulaPajakan',false,'dmy');" />
                #end                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Tempoh Pajakan</td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtTempoh" id="txtTempoh" size="2" value="$beanPajakan.tempoh" class="$classDis" $readOnly onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value);calcDate()"/>
                  Tahun &nbsp; &nbsp;
                  <input type="text" name="txtTempohBulan" id="txtTempohBulan" size="2" value="$beanPajakan.tempoh" class="$classDis" $readOnly onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value);calcDate()"/>
                  Bulan &nbsp; &nbsp;
                  <input type="text" name="txtTempohHari" id="txtTempohHari" size="2" value="$beanPajakan.tempoh" class="$classDis" $readOnly onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value);calcDate()"/>
                  Hari</td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td>Tarikh Tamat Pajakan</td>
                <td>:</td>
                <td>
                <input type="text" name="txdTarikhTamatPajakan" id="txdTarikhTamatPajakan" size="11" value="$beanPajakan.tarikhTamat" onblur="check_date(this)" class="$classDis" $readOnly/>
                  #if ($mode == 'newPajakan' || $mode == 'updatePajakan') <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhTamatPajakan',false,'dmy');" /> #end </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Kadar Pajakan Setahun (RM)</td>
                <td>:</td>
                <td>
                  <input name="txtKadarPajakan" type="text" class="$classDis" id="txtKadarPajakan" value="$beanPajakan.kadarPajakan" size="10" $readOnly onblur="validateCurrency(this,this.value,'')"   /></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
		        <td>&nbsp;</td>
		        <td>Kadar Cukai Setahun (RM)</td>
		        <td>:</td>
		        <td>
		          <input name="txtKadarCukai" type="text" class="$classDis" id="txtKadarCukai" value="$beanPajakan.kadar" size="10" $readOnly onblur="validateCurrency(this,this.value,'')"   />
		          <input name="txtKadarCukaitemp" type="hidden" value="$pajakanTemp.getKadarCukaiString()" />
		          	#if ($mode == 'newPajakan' || $mode == 'updatePajakan')
						<input type="button" name="cmdkira" value="Kira" onclick="javascript:kiraCukai()" />
					#end
		       	</td>
		        <td>&nbsp;</td>
		   	</tr>
			<tr>
                <td>&nbsp;</td>
                <td valign="top">Tarikh Patut Bayar</td>
                <td valign="top">:</td>
                <td valign="top">
                	<input type="text" name="txdtarikhpatut" id="txdtarikhpatut" size="11" value="$!beanPajakan.tarikhPatut" onblur="check_date(this)" class="$classDis" $readOnly/>
                  	#if ($mode == 'newPajakan' || $mode == 'updatePajakan') <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdtarikhpatut',false,'dmy');" /> #end
              	</td>
                  </td>
    		</tr>
        	<tr>
	            <td>&nbsp;</td>
	            <td>Denda</td>
	            <td>:</td>
                <td><input type="text" name="txtdenda" class="$classDis" id="txtdenda" value="$!beanPajakan.denda" size="2" $readOnly onblur="validateNumber(this,this.value);"/>%</td>
	            <td>&nbsp;</td>
            </tr>
          	<tr>
	            <td>&nbsp;</td>
	            <td>Cara Bayaran</td>
	            <td>:</td>
	            <td>$selectCaraBayar</td>
	            <td>&nbsp;</td>
            </tr>
			<tr>
                <td>&nbsp;</td>
                <td>Kategori Cukai</td>
                <td>:</td>
                <td><select name="socKategoriCukai" id="socKategoriCukai" class="$classDis" $classDis>

                  #if($beanPajakan.katCukai == 'PT' || $beanPajakan.katCukai == "" )

                  <!-- PT refers to PTP -->
                  <option value="PT" selected="selected">PTP</option>
                  <option value="P">PEMAJAK</option>

                  #else

                  <option value="PT" >PTP</option>
                <option value="P" selected="selected" >PEMAJAK</option>

                  #end

                </select></td>
        	</tr>
 			<tr>
                <td>&nbsp;</td>
                <td>Status</td>
                <td>:</td>
                <td><select name="socaktif" id="socaktif" class="$classDis" $classDis>

                  #if($!beanPajakan.status == 'Y' || $!beanPajakan.status == "" )
					<option value="Y" selected="selected">Aktif</option>
                  	<option value="N">Tidak Aktif</option>

                  #else
					<option value="Y" >Aktif</option>
                	<option value="N" selected="selected" >Tidak Aktif</option>

                  #end

                </select></td>
              </tr>              
        	<tr>
	            <td>&nbsp;</td>
	            <td valign="top">Terma Bayaran</td>
	            <td valign="top">:</td>
	            <td>
					<textarea name="txtermabayaran" id="txtermabayaran" cols="50" rows="5"
            				onBlur=""
            				onkeyup="textCounter(this.form.txtermabayaran,this.form.remtxtermapajakan,1500);"
            				onkeydown="textCounter(this.form.txtermabayaran,this.form.remtxtermapajakan,1500);"
            				class="$classDis" $readOnly>$!beanPajakan.termaBayaran</textarea>
	            </td>
	            <td>&nbsp;</td>
            </tr>
      	#if ($mode != 'viewPajakan')
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td valign="top">&nbsp;</td>
				<td><input type="text" readonly class="disabled" name="remtxtermapajakan" size="3" maxlength="4" value="1500"> Baki Aksara </td>
			</tr>
		#end
           	<tr>
	            <td>&nbsp;</td>
	            <td valign="top">Terma Denda</td>
	            <td valign="top">:</td>
	            <td>
					<textarea name="txtermadenda" id="txtermadenda" cols="50" rows="5"
            				onBlur=""
            				onkeyup="textCounter(this.form.txtermadenda,this.form.remtxtermadenda,1500);"
            				onkeydown="textCounter(this.form.txtermadenda,this.form.remtxtermadenda,1500);"
            				class="$classDis" $readOnly>$!beanPajakan.termaDenda</textarea>
	            </td>
	            <td>&nbsp;</td>
            </tr>
      	#if ($mode != 'viewPajakan')
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td valign="top">&nbsp;</td>
				<td><input type="text" readonly class="disabled" name="remtxtermadenda" size="3" maxlength="4" value="1500"> Baki Aksara </td>
			</tr>
		#end
		
			#end
			##set($txtCatatan_=$beanPajakan.kadarPajakan)
          	<tr>
	            <td>&nbsp;</td>
	            <td valign="top">Catatan</td>
	            <td valign="top">:</td>
	            <td>
					<textarea name="txtcatatan" id="txtcatatan" cols="50" rows="5"
            				onBlur=""
            				onkeyup="textCounter(this.form.txtcatatan,this.form.remtxtcatatan,1500);"
            				onkeydown="textCounter(this.form.txtcatatan,this.form.remtxtcatatan,1500);"
            				class="$classDis" $readOnly>$!catatan</textarea>
	            </td>
	            <td>&nbsp;</td>
            </tr>

			#if ($mode != 'viewPajakan')
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td valign="top">&nbsp;</td>
				<td><input type="text" readonly class="disabled" name="remtxtcatatan" size="3" maxlength="4" value="1500"> Baki Aksara </td>
			</tr>
			#end
              <tr>
                <td>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
              </tr>


              <!-- PENAMBAHBAIKAN FASA3. 27112014. SYAZ. POPUP/NOTIFIKASI PERINGATAN SEMULA / LUPUT -->
              <tr>
              	<td colspan="4">
              		<fieldset>
              			<legend><strong>PERINGATAN PENILAIAN SEMULA / PAJAKAN HAMPIR LUPUT</strong></legend>

              			<table style="width:100%">
	              			<tr>
	              				<td width="1%" class="e">&nbsp;</td>
	                			<td width="28%">Papar Peringatan (Sebelum Tamat Pajakan)</td>
	                			<td width="1%">:</td>
	                			<td width="70%">$!selectNumberRange Tahun</td>
	              			</tr>
	              		</table>

              		</fieldset>
              	</td>
              </tr>

              <tr><td colspan="4">&nbsp;</td></tr>

              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td colspan="2">
                #if ($mode == 'newPajakan')
                	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanPajakan()" />
                    <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalPajakan()" />
                #elseif ($mode == 'viewPajakan')
                    <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniPajakan()" />
                    <input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:hapusPajakan()" />
                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalPajakan()" />

                #elseif ($mode == 'updatePajakan')
                  <input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanUpdatePajakan()" />
                  <!--  <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/> -->
                  <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdatePajakan()" />
                #end
                </td>
              </tr>
            </table>
        </fieldset>

    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td>

    <fieldset>
    <legend><strong>SENARAI PAJAKAN</strong></legend>

    <table align="center" width="100%">
            #if ($mode == 'view')
            <tr>
              	<td colspan="8" scope="row">
              		<input class="stylobutton100" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruPajakan()"/>
            	</td>
            </tr>
            #end
            <tr class="table_header">
              <td scope="row" width="4%" align="center"><strong>Bil.</strong></td>
              <td width="16%"><strong>Tarikh Tandatangan</strong></td>
              <td width="10%" align="center"><strong>Tarikh Mula</strong></td>
              <td width="11%" align="center"><strong>Tarikh Tamat</strong></td>
              <td width="12%" ><strong>Tempoh Pajakan</strong></td>
              <td width="18%" ><strong>Cara Bayaran</strong></td>
              <td width="16%" align="center"><strong>Kadar Pajakan Setahun (RM)</strong></td>
              <td width="13%" align="center"><strong>Kadar Cukai Setahun (RM)</strong></td>
        </tr>
          #set ($list = "")
          #if ($SenaraiPajakan.size() > 0)
          #foreach ($list in $SenaraiPajakan)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else
                #set( $row = "row2" )
            #end
      <tr>
            <td class="$row" align="center">$list.bil.</td>
            <td class="$row"><a href="javascript:paparPajakan('$!list.idPajakan')" class="style1">$list.tarikhTandatangan</a></td>
            <td class="$row" align="center">$list.tarikhMula</td>
            <td class="$row" align="center">
            	<div #if($list.papar_notifikasi.equalsIgnoreCase("Y")) class="expired blink" title="Penilaian Semula / Pajakan Hampir Luput" #end >
            		$list.tarikhTamat
            	</div>
            </td>
          	<td class="$row" >$list.tempoh Tahun</td>
            <td class="$row" >$list.caraBayar</td>
            <td class="$row" align="right">$list.kadarPajakan</td>
            <td class="$row" align="right">$list.kadar</td>
      </tr>
          #end
          #else
          <tr>
            <td class="row1">&nbsp;</td>
            <td class="row1">Tiada Rekod</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
      </tr>
          #end
        </table>

    </fieldset>


    </td>
  </tr>
</table>
#parse("app/htp/utiliti/javascript/javaScriptPajakanMaklumat.jsp")
