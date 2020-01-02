<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>	

	#set ($inputstyle = "class=disabled" )
	#set ($inputstyleread = "readonly class=disabled" )
	#set ($selectstyle = "disabled class=disabled" )


<tbody>
  <tr>
    <td align="center" valign="top">
    <fieldset>
      <legend><strong>Maklumat Penerimaan Permohonan</strong></legend>
      <table border="0">
        <tr>
          <td>&nbsp;</td>
        <td><strong>Negeri </strong></td>
        <td><strong>:</strong></td>
        <td>$lblNegeri</td>
        <td>&nbsp;</td>
        <td><strong>No. Fail Seksyen </strong></td>
        <td><strong>:</strong></td>
        <td>$lblNoFail</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><strong>Kementerian  </strong>&nbsp;</td>
        <td><strong>:</strong></td>
        <td>$lblKementerian</td>
        <td>&nbsp;</td>
        <td>
          
           <strong>Tarikh Surat KJP </strong>       </td>
        <td><strong>:</strong></td>
        <td><input name="txdtarikhSuratKJP" type="text" id="txdTarikhSuratKJP" value="$lblTrkhSurat" $selectstyle/><!-- &nbsp;<a href="javascript:displayDatePicker('txdTarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>&nbsp; --></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><strong>Agensi </strong></td>
        <td><strong>:</strong></td>
        <td>$lblidAgensi</td>
        <td>&nbsp;</td>
        <td><strong>No. Fail KJP </strong></td>
        <td><strong>:</strong></td>
        <td><input name="txtnoFailKJP" type="text" id="txtnoFailKJP" value="$lblNoRujKJP" $selectstyle />&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><strong>Urusan</strong></td>
        <td><strong>:</strong></td>
        <td>$lblidUrusan</td>
        <td>&nbsp;</td>
        <td><strong>No. Fail UPT </strong></td>
        <td><strong>:</strong></td>
        <td><input name="txtnoFailUPT" type="text" id="txtnoFailUPT" value="$lblNoRujLain" $selectstyle />&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><strong>Sub Urusan</strong></td>
        <td><strong>:</strong></td>
        <td>$lblidSubUrusan</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><strong>Status Tanah</strong></td>
        <td><strong>:</strong></td>
        <td>$lblidKodJTanah</td>
        <td>&nbsp;</td>
        <td><strong>Tajuk</strong></td>
        <td><strong>:</strong></td>
        <td rowspan="3"><textarea name="textarea2" cols="30" rows="5" $selectstyle >$lblTujuan </textarea></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><strong>Jenis Fail</strong></td>
        <td><strong>:</strong></td>
        <td>$lblTanahKeselamatan</td>
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
        <td>&nbsp;</td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td><strong>Didaftar Oleh</strong></td>
        <td><strong>:</strong></td>
        <td>$diDaftarOleh</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><strong>Didaftar Pada</strong></td>
        <td><strong>:</strong></td>
        <td>$diDaftarPada</td>
        <td>&nbsp;</td>
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
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><input type="button" onclick="javascript:cetakKulitFail('$lblIdPermohonan');" value="Cetak Kulit Fail" /></td>
        <td>&nbsp;</td>
        <td><input type="button" onclick="javascript:cetakDoket('$lblIdPermohonan');" value="Cetak Doket" /></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      </table>
    </fieldset>
    
    <p>
      <input type="text" name="" value="$lblIdPermohonan" />
      <input type="text" value="$idfail" name="idfail"/>
	</p>   

<script>
	function KemaskiniInfo(id){
		doAjaxCall${formName}("kemaskinipermohonan","mode=InfoKemaskini");
	
	}

</script>

    