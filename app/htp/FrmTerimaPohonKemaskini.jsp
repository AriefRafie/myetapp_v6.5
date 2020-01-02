<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>	
#set ($pagemode = $pageMode)	
#set ( $luas = "0.00")
	#set ( $tarikhmula = "" )
	#set ( $tarikhtamat = "")
	#set ( $alamat1 ="")
	#set ( $alamat2 = "")
	#set ( $alamat3 = "")
	#set ( $poskod  = "")

#if ( $pagemode == "3"  )
	##set ( $sewa = $util.formatDecimal($hakmilikbangunaninfo.sewabulanan) )
	#set ( $sewa = $hakmilikbangunaninfo.sewabulanan)
	#set ( $alamat1 = $hakmilikbangunaninfo.alamat1)
	#set ( $alamat2 = $hakmilikbangunaninfo.alamat2)
	#set ( $alamat3 = $hakmilikbangunaninfo.alamat3)
	#set ( $poskod  = $hakmilikbangunaninfo.poskod)
	#set ( $luas = $hakmilikbangunaninfo.luas )
	#set ( $tarikhmula = $hakmilikbangunaninfo.tmula )
	#set ( $tarikhtamat = $hakmilikbangunaninfo.ttamat )
#elseif( $pagemode == "4"  )
	#set ( $sewa = $hakmilikbangunaninfo.sewabulanan)
	#set ( $alamat1 = $hakmilikbangunaninfo.alamat1)
	#set ( $alamat2 = $hakmilikbangunaninfo.alamat2)
	#set ( $alamat3 = $hakmilikbangunaninfo.alamat3)
	#set ( $poskod  = $hakmilikbangunaninfo.poskod)
	#set ( $luas = $hakmilikbangunaninfo.luas )
	#set ( $tarikhmula = $hakmilikbangunaninfo.tmula )
	#set ( $tarikhtamat = $hakmilikbangunaninfo.ttamat )
#end
<fieldset>
  <legend><strong>Maklumat Penerimaan Permohonan </strong></legend>
  <table border="1" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top">
  <table width="100%" border="0">
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td width="11%"><div align="left"><strong><span class="style1">*</span>Negeri </strong></div></td>
      <td width="1%"><strong>:</strong></td>
      <td width="30%">$socNegeri</td>
      <td width="18%"><div align="left"><strong>No. Fail Seksyen  </strong></div></td>
      <td width="1%"><strong>:</strong></td>
      <td width="39%">
        <label>
          <input name="txtnoFailSek" type="text" id="txtnoFailSek" />
        </label>           </td>
    </tr>
    <tr>
      <td><div align="left"><span class="style1"><strong>*</strong></span><strong>Kementerian  </strong></div></td>
      <td><strong>:</strong></td>
      <td>$socKementerian</td>
      <td><div align="left"><strong>Tarikh Surat KJP </strong></div></td>
      <td><strong>:</strong></td>
      <td><input name="txdTarikhSuratKJP" type="text" id="txdTarikhSuratKJP" />
        &nbsp;<a href="javascript:displayDatePicker('txdTarikhSuratKJP',false,'dmy');"><img src="../img/calendar.gif" alt="calendar" border="0"/></a></td>
    </tr>
    <tr>
      <td><div align="left"><span class="style1"><strong>*</strong></span><strong>Agensi </strong></div></td>
      <td><strong>:</strong></td>
      <td>$socAgensi</td>
      <td><div align="left"><strong>No. Fail KJP </strong></div></td>
      <td><strong>:</strong></td>
      <td><input name="txtnoFailKJP" type="text" id="txtnoFailKJP" /></td>
    </tr>
    <tr>
      <td><div align="left"><span class="style1"><strong>*</strong></span><strong>Urusan </strong></div></td>
      <td><strong>:</strong></td>
      <td>$socUrusan</td>
      <td><div align="left"><strong>No. Fail UPT </strong></div></td>
      <td><strong>:</strong></td>
      <td><input name="txtnoFailUPT" type="text" id="txtnoFailUPT" /></td>
    </tr>
    <tr>
      <td><div align="left"><span class="style1"><strong>*</strong></span><strong>Sub Urusan </strong></div></td>
      <td><strong>:</strong></td>
      <td>$socSubUrusan</td>
      <td><div align="left"><strong>No. Fail Lain </strong></div></td>
      <td><strong>:</strong></td>
      <td><input name="txtnoFailLain" type="text" id="txtnoFailLain" /></td>
    </tr>
    <tr>
      <td><div align="left"><span class="style1"><strong>*</strong></span><strong>Status Tanah  </strong></div></td>
      <td><strong>:</strong></td>
      <td>$socStatustanah</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><div align="left"><span class="style1"><strong>*</strong></span><strong>Jenis Fail </strong></div></td>
      <td><strong>:</strong></td>
      <td>$socjenisFail</td>
      <td><div align="left"><strong><!--  Tarikh Assign--> </strong></div></td>
      <td><strong><!--  :--></strong></td>
      <td><!--  <input name="txdtarikhTugasan" type="text" id="txdtarikhTugasan" />&nbsp;<a href="javascript:displayDatePicker('txdtarikhTugasan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>--></td>
    </tr>
    <tr>
      <td><div align="left"></div></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><div align="left"><strong>Tajuk</strong></div></td>
      <td><strong>:</strong></td>
      <td>
        <textarea name="txtTajuk" id="txtTajuk" rows="5" onkeyup="this.value=this.value.toUpperCase();"></textarea>      </td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="6"><table width="100%" border="1">
            <tr>
              <td><p>
                <input type="checkbox" name="checkbox" value="checkbox" />
Syarat Utama : Permohonan melalui Kementerian . 
                </p>
                <p>
                  <input type="checkbox" name="checkbox2" value="checkbox" />
                  8 Salinan pelan lengkap(mengikut skala) yang telah disahkan oleh Pentabir Tanah Daerah. </p>
                <input type="checkbox" name="checkbox3" value="checkbox" />
Borang permohonan tanah mengikut kaedah-kaedah Tanah Negeri. </td>
            </tr>
          </table>          </td>
    </tr>
  </table>
  </td>
  </tr>
  </tbody>
</table>
  <p><strong></strong></p>
  
    <input class="button" name="cmdKemaskini" type="button" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini()"/>
    <input class="button" name="cmdHapus" type="button" id="cmdHapus" value="Hapus" onclick="Hapus()"/>
    <input class="button" name="cmdSimpan" type="button" id="cmdSimpan" value="Simpan" onclick="Simpan('Simpan')" />
    <input class="button" name="cmdBatal" type="button" id="cmdBatal" value="Batal" onclick="Batal()"/>
    <input class="button" name="cmdKembali" type="button" id="cmdKembali" value="Kembali" onclick="kembali()"/>
    
    <input type="hidden" name="socNegeri" value="$socNegeri"> idurusan
    <input type="hidden" name="idsuburusan" id="idsuburusan" value="$idsuburusan">
    <input type="hidden" name="idurusan" id="idurusan" value="$idurusan">
	
</fieldset>
<script>

</script>


