<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<input name="mode" type="hidden" value="" />
&nbsp;
  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td align="right" scope="row">No Rujukan Dokumen</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtNoRujDokuman" type="text" id="txtNoRujDokuman" size="44" />
      </label></td>
    </tr>
    <tr>
      <td width="29%" align="right" scope="row">Tajuk Dokumen</td>
      <td width="1%" scope="row">:</td>
      <td width="70%"><label>
        <textarea name="txtTajukDokumen" cols="41" id="txtTajukDokumen">$tajukDokumen</textarea>
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Seksyen / Urusetia</td>
      <td scope="row">:</td>
      <td>$selectSeksyen</td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Dokumen</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txdTarikhDokumen" type="text" id="txdTarikhDokumen" value="$tarikhDokumen" size="10" />
      <a href="javascript:displayDatePicker('txtTarikhDokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </label></td>
    </tr>
    <tr>
      <td colspan="2" align="right" scope="row">&nbsp;</td>
      <td><label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()" />
        </label>
          <label>
          <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label></td>
    </tr>
  </table>
</fieldset>
  <fieldset>
  <legend><strong>Senarai Dokumen</strong></legend>
  <p>
    <label>
      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()" />
    </label>
  </p>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="1%" scope="row"><strong>No</strong></td>
      <td width="20%"><strong>No Rujukan Dokumen</strong></td>
      <td width="34%"><strong>Tajuk Dokumen</strong></td>
      <td width="24%"><strong>Tarikh Dokumen</strong></td>
      <td width="27%"><strong>Seksyen / Urusetia</strong></td>
    </tr>
    #foreach ($dokumen in $SenaraiDokumen)
    #if ($dokumen.bil == '') 
  		#set ($row = 'row1')
    #elseif ($dokumen.bil % 2 != 0)
  	   	#set ($row = 'row1')
  	#else 
  		#set ($row = 'row2')
  	#end 
    <tr>
      <td class="$row">$dokumen.bil</td>
      <td class="$row">
       #if ($dokumen.bil != '') 
      <a href="javascript:edit_item('$dokumen.idDokumen')" class="style1">$dokumen.noRujukanDokumen</a>
      #else
      $dokumen.noRujukanDokumen
      #end      </td>
      <td class="$row">$dokumen.tajukDokumen</td>
      <td class="$row">$dokumen.tarikhDokumen</td>
      <td class="$row">$dokumen.kodSeksyen</td>
    </tr>
    #end
  </table>
</fieldset>
 <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-12</strong></td>
  	</tr>
  </table>
  
<script type="text/javascript" src="../app/pdt/dokumenlain.js"></script>
