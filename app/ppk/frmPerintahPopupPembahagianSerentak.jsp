<p>
  <input name="idPerintah" type="hidden" id="idPerintah" value="$idPerintah"/>
  <input name="idPerintahHTAOBMST" type="hidden" id="idPerintahHTAOBMST" value="$idPerintahHTAOBMST"/>
  <input name="idPerintahHAOBMST" type="hidden" id="idPerintahHAOBMST" value="$idPerintahHAOBMST"/>
  <input name="hitButt" type="hidden" id="hitButt"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI HARTA TIDAK ALIH</strong></legend>
      <table align="center" width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr class="table_header">
          <td width="1%" align="center">&nbsp;</td>
          <td width="4%"><strong>BIL</strong></td>
          <td width="95%"><strong>KETERANGAN HAKMILIK</strong></td>
        </tr>
        #set ($listHTA = "")
        #if ($SenaraiHTA.size() > 0)
        #foreach ($listHTA in $SenaraiHTA)
        #if ($listHTA.bil == '')
        #set( $row = "row1" )
        #elseif (($listHTA.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td><input type="checkbox" value="$listHTA.idPerintahHTAOBMST" name="idsHTA"/></td>
          <td class="$row" align="center">$listHTA.bil</td>
          <td class="$row">$listHTA.keteranganHakmilik</td>
        </tr>
        #end
        #else
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI HARTA ALIH</strong></legend>
      <table align="center" width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr class="table_header">
          <td width="1%" align="center">&nbsp;</td>
          <td width="4%"><strong>BIL</strong></td>
          <td width="95%"><strong>JENIS HARTA ALIH</strong></td>
        </tr>
        #set ($listHA = "")
        #if ($SenaraiHA.size() > 0)
        #foreach ($listHA in $SenaraiHA)
        #if ($listHA.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td><input type="checkbox" value="$listHA.idPerintahHAOBMST" name="idsHA"/></td>
          <td class="$row" align="center">$listHA.bil</td>
          <td class="$row">$listHA.jenisHA $listHA.keterangan</td>
        </tr>
        #end
        #else
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
  	<td align="center"><input id="cmdSimpan" name="cmdSimpan" type="button" value="Simpan" onclick="javascript:simpan()"/><input name="cmdTutup" type="button" value="Tutup" onclick="javascript:tutup()"/></td>
  <tr>
</table>

#if($close_window == "yes")
<script>
window.close();
</script>
#end

<script>
function simpan() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButt.value = "simpan";
	document.${formName}.submit();
}
function tutup() {
	window.close();
}
</script>
