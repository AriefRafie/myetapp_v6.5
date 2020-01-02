<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newJPPH' || $mode == 'updateJPPH' || $mode == 'viewJPPH')
  <tr>
    <td>
    
    	<fieldset><legend><strong>ULASAN JPPH</strong></legend>
        
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
            #foreach ($beanUlasanJPPH in $BeanUlasanJPPH)
              <tr>
                <td width="1%">#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td width="15%">No Ruj. JPPH</td>
                <td width="1%">:</td>
                <td width="83%"><input type="text" name="txtNoRujJPPH" size="30" id="txtNoRujJPPH" onBlur="this.value=this.value.toUpperCase();" value="$beanUlasanJPPH.noRujukan" class="$classDis" $readOnly/></td>
              </tr>
              <tr>
                <td>#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td>Tarikh Hantar</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhHantarJPPH" id="txdTarikhHantarJPPH" size="10" value="$beanUlasanJPPH.tarikhHantar" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newJPPH' || $mode == 'updateJPPH')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhHantarJPPH',false,'dmy');" />
                #end                </td>
              </tr>
              <tr>
                <td>#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td>Tarikh Terima</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhTerimaJPPH" id="txdTarikhTerimaJPPH" size="10" value="$beanUlasanJPPH.tarikhTerima" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newJPPH' || $mode == 'updateJPPH')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhTerimaJPPH',false,'dmy');" />
                #end                </td>
              </tr>
              <tr>
                <td>#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td>Tempoh Nilaian (Tahun)</td>
                <td>:</td>
                <td><input type="text" name="txtTahunNilaian" id="txtTahunNilaian" size="2" value="$beanUlasanJPPH.tahunNilaian" class="$classDis" $readOnly/> Tahun</td>
              </tr>
              <tr>
                <td style="visibility:hidden">#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td>Nilaian Tanah (RM)</td>
                <td>:</td>
                <td> 
                  <input name="txtNilaiTanah" type="text" class="$classDis" id="txtNilaiTanah" onblur="validateCurrency(this,this.value,'')" value="$beanUlasanJPPH.nilaian" size="30" maxlength="25" $readOnly /></td>
              </tr>
              <tr style="display:none">
                <td>#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td>Syor Bayaran</td>
                <td>:</td>
                <td>RM 
                  <input name="txtSyorBayaran" type="text" class="$classDis" id="txtSyorBayaran" onblur="validateCurrency(this,this.value,'')" value="$beanUlasanJPPH.syor" size="25" maxlength="25" $readOnly /></td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Ulasan</td>
                <td valign="top">:</td>
                <td valign="top">
                	<textarea name="txtKeteranganJPPH" cols="50" rows="5" 
						onkeyup="textCounter(this.form.txtKeteranganJPPH,this.form.remtxtcatatan,1500);" 
						onkeydown="textCounter(this.form.txtKeteranganJPPH,this.form.remtxtcatatan,1500);"	                		
						class="$classDis" $readOnly>$beanUlasanJPPH.ulasan</textarea>
                </td>
              </tr>
              							     #if ($mode != 'viewJPPH')
									<tr>
								        <td>&nbsp;</td>
								        <td>&nbsp;</td>
								        <td valign="top">&nbsp;</td>
								        <td><input type="text" readonly class="disabled" name="remtxtcatatan" size="3" maxlength="4" value="1500"> Baki Aksara </td>
								    </tr>	
								#end	
              #end
              <!-- <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td colspan="2">

                </td>
              </tr> -->
            </table>
        </fieldset>
        
    </td>
  </tr>
  
         	#if ($mode == 'newJPPH' || $mode == 'updateJPPH')

			<tr>
	  			<td>
	        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	        	</td>
	     	</tr>
	     	
            #end  
  
    <tr>
    <td align="center">
                #if ($mode == 'newJPPH')
                	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanJPPH()" />
                    <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalJPPH()" />
                #elseif ($mode == 'viewJPPH')
                    <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniJPPH()" />
                    <input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusJPPH()" />
                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalJPPH()" />
                    
                #elseif ($mode == 'updateJPPH')
                  	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdateJPPH()" />
                    <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateJPPH()" />
                #end     
    </td>
  </tr>
  
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td>
    
    <fieldset>
    <legend><strong>SENARAI ULASAN</strong></legend>
    
    	<table align="center" width="100%"> 
            #if ($mode == 'view')
            <tr>
              <td colspan="8" scope="row">
              	<input class="stylobutton100" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruJPPH()"/>
              	<input class="stylobutton100" type="button" name="cmdCetak" id="cmdCetak" value="Surat" onclick="javascript:setTable('tableReport4')" />
              </td>
            </tr>
            #end
            <tr class="table_header">
              <td scope="row" width="3%" align="center">Bil.</td>
              <td width="17%">No. Rujukan</td>
              <td width="15%" align="center">Tarikh Hantar</td>
              <td width="15%" align="center">Tarikh Terima</td>
              <td width="15%" align="center">Tempoh Nilaian (Tahun)</td>
              <td width="15%" colspan="2" align="right">Nilaian Tanah (RM)</td>
              <td width="20%" align="center" style="display:none">Syor Bayaran (RM)</td>
        </tr>
          #set ($list = "")
          #if ($SenaraiUlasanJPPH.size() > 0)
          #foreach ($list in $SenaraiUlasanJPPH)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
      <tr>
            <td class="$row" align="center"><a href="javascript:paparJPPH('$list.idUlasanTeknikal','$list.idUlasanNilai')" class="style1">$list.bil.</a></td>
            <td class="$row"><a href="javascript:paparJPPH('$list.idUlasanTeknikal','$list.idUlasanNilai')" class="style1">$list.noRujukan</a></td>
            <td class="$row" align="center">$list.tarikhHantar</td>
          	<td class="$row" align="center">$list.tarikhTerima</td>
            <td class="$row" align="center">$list.tahunNilaian</td>
            <td class="$row" colspan="2" align="right" class="$row">$list.nilaian</td>
            <td class="$row" align="right" style="display:none">$list.syor</td>
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
            <td class="row1" style="display:none"></td>
      </tr>
          #end
        </table>
        
    </fieldset>
    
    <!-- Tambahan Integrasi JPPH Online -->
    <!--  20180221 Dikomen oleh Mohamad Rosli
    <br /><fieldset>
      <legend><strong>SENARAI ULASAN <i>ONLINE</i></strong></legend>
      <table align="center" width="100%">
      #if ($mode == "view")
        <tr>
          <td colspan="5" scope="row">
            <input name="cmdHantarJPPH" type="button" value="Hantar Permohonan Nilaian ke JPPH" onclick="javascript:sendJPPH($idPermohonan)"/>
          </td>
        </tr>
      #end
        <tr class="table_header">
          <td align="center" width="5%">Bil.</td>
          <td align="center" width="15%">Tarikh Kemaskini</td>
          <td align="center" width="25%">Status</td>
          <td align="center" width="30%">Tempoh Nilaian Tahun</td>
          <td align="center" width="30%">Jenis Nilaian Tanah</td>
        </tr>
      #set ($list = "")
      #if ($SenaraiUlasanJPPHOnline.size() > 0)
        #foreach ($list in $SenaraiUlasanJPPHOnline)
          #if ($list.bil == '')
            #set( $row = "row1" )
          #elseif (($list.bil % 2) != 0)
            #set( $row = "row1" )
          #else 
            #set( $row = "row2" )
          #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparJPPHOnline('$idPermohonan')" class="style1">$list.tarikhKemaskini</a></td>
          <td class="$row" align="center">$list.status</td>
          <td class="$row" align="center">$list.tempohNilaian</td>
          <td class="$row" align="center">$list.jenisNilaian</td>
        </tr>
        #end
      #else
        <tr>
          <td colspan="5" class="row1" align="center">Tiada Rekod</td>
        </tr>
      #end
      </table>
    </fieldset>
    -->
    <!-- end add kmie JPPH Online -->
    
    </td>
  </tr>
</table>


<fieldset id="tableReport4" style="display:none;">
<legend><strong>SENARAI CETAKAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
      	<td><a href="#" onClick="javascript:cetakSuratUlasanJPPH('$!idPermohonan')"><font color="blue">SURAT KEPADA JPPH (PENILAIAN)</font></a></td>
	  </tr>	  
    </table>
</fieldset>	