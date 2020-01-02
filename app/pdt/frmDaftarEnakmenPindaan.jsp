<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table width="100%">
<tr>
	<td width="5"></td>
	<td align="left"> <strong>ENAKMEN PINDAAN</strong></td>
</tr>
</table>
<input name="idEnakmen" type="hidden" id="idEnakmen" value="$!idEnakmen"/>
<input name="idEnakmenPinda" type="hidden" id="idEnakmenPinda" value="$!idEnakmenPinda"/>
  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td width="29%" align="right" scope="row">No. Enakmen Asal  </td>
      <td width="1%" scope="row">:</td>
      <td width="70%"><label>
        <input name="txtNoEnakmenAsal" type="text" id="txtNoEnakmenAsal" value="$!txtNoEnakmenAsal" />
      </label></td>
    </tr>
    <tr>
      <td width="29%" align="right" scope="row">No. Enakmen Pindaan  </td>
      <td width="1%" scope="row">:</td>
      <td width="70%"><label>
        <input name="txtNoEnakmenC" type="text" id="txtNoEnakmenC" value="$!txtNoEnakmenP" />
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Nama Enakmen Pindaan</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtNamaEnakmenP" type="text" id="txtNamaEnakmenP" value="$!txtNamaEnakmenP" />
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Kuatkuasa</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtTarikhKuatkuasaP" type="text" id="txtTarikhKuatkuasaP" value="$!txtTarikhKuatkuasaP" size="10" />
      </label>
      <a href="javascript:displayDatePicker('txtTarikhKuatkuasaC',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>      </td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><i>Tag</i> Dokumen</td>
      <td scope="row" valign="top">:</td>
      <td>
      	<textarea name="tag_dokumen" cols="41" rows="3" onblur="this.value=this.value.toUpperCase()" >$!tag_Dokumen</textarea>
        <input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/>
      </td>
    </tr>
        
    <tr>
      <td align="right" scope="row">&nbsp;</td>
      <td scope="row">&nbsp;</td>
      <td><label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="cari()" />
        </label>
          <label>
          <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosong()" />
        </label></td>
    </tr>
  </table>
</fieldset>
<p>
  <fieldset>
  <!--<div id="mydiv">-->
  <legend><strong>Senarai Enakmen Pindaan</strong></legend>
   <input type="button" value="Tambah" onclick="tambahEnakmen()"/>
          #parse("app/utils/record_paging.jsp")
  <!--</div>--> 
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr>
      <td colspan="3">          </td>
       <td colspan="4" align="right">&nbsp;</td>
    </tr>
    <tr class="table_header">
      <td width="3%" scope="row"><strong>Bil.</strong></td>
      <td width="20%"><strong>No. Enakmen Pindaan</strong></td>
      <td width="40%"><strong>Nama Enakmen Pindaan</strong></td>
      <td width="20%"><strong>No. Enakmen Asal</strong></td>
      <td width="12%"><strong>Tarikh Kuatkuasa</strong></td>
      <td width="5%">&nbsp;</td>
    </tr>
    #set ($enakmaen = '')
    #foreach ($enakmen in $SenaraiFail)
    #if ($enakmen.no == '') 
    #set ($row = 'row1')
    #elseif ($enakmen.no % 2 != 0)
    #set ($row = 'row1')
    #else 
    #set ($row = 'row2')
    #end
  <tr>
    <td height="20" class="$row">$enakmen.no.</td>
    <td class="$row">
     #if ($enakmen.no != '') 
    <a href="javascript:edit_item('$enakmen.id_enakmen')" class="style1">$enakmen.no_enakmen</a>
    #else
    $enakmen.no_enakmen
    #end    </td>
    <td class="$row">$enakmen.nama_enakmen</td>
    <td class="$row">$enakmen.no_enakmenAsal</td>
    <td class="$row">$enakmen.tarikh_kuatkuasa</td>
    <td class="$row"><a href = "javascript:viewEnakmenPindaBlob('$enakmen.id_enakmen')">
    <img src="../img/pdf-small.png" alt="" border="0" /></a>
     #if($current_role=="(PDT)HQPengguna")
     <a alt="Hapus Enakmen Pindaan" href = "javascript:deleteEnakmenPindaan('$enakmen.id_enakmen')">
      &nbsp;&nbsp;<img border="0" src="../img/delete.gif" /></a> 
      #else
      &nbsp;&nbsp; 
       #end 
    </td>
  </tr>
    #end
  </table>
</fieldset>
   <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right">&nbsp;</td>
  	</tr>
  </table>
<script type="text/javascript" src="../app/pdt/enakmenpinda.js"></script>
<script>
//alert("ssssss");
//alert("sssssss"+'$current_role');

$jquery(document).ready(function () {

	if('$current_role'=="(PDT)HQPengguna")
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		//$jquery("input[type=button]").hide();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		$jquery("input[type=button]").show();
	}
	else
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		//$jquery("input[type=button]").show();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		$jquery("input[type=button]").hide();
	}
});

</script>
  