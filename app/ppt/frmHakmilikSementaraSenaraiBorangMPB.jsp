#parse("app/ppt/SementaraPaging.jsp")

<fieldset id="top">
<center>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

##parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
<p>
<fieldset>
<legend><strong>Senarai Pihak Berkepentingan</strong></legend>
<table width="100%">
  <tr class="table_header">
    <td><strong>Bil</strong></td>
    <td><strong>Nama PB</strong></td>
    <td><strong>No. PB</strong></td>
    <td><strong>No. Lot/PT</strong></td>
    <td><strong>Mukim</strong></td>
    <td><strong>Bahagian</strong></td>
  </tr>
  #foreach ($pb in $SenaraiPB)
  
  #if ($pb.bil == '') 
  #set ($row = 'row1')
  #elseif ($pb.bil % 2 != 0)
  #set ($row = 'row1')
  #else 
  #set ($row = 'row2')
  #end
  <tr>
    <td>$pb.bil</td>
    #if ($pb.bil != '')
    <td><a href="javascript:borangM('$pb.ID_HAKMILIKPB')"><font color="blue">$pb.NAMA_PB</font></a></td>
    #else
    <td>$pb.NAMA_PB</td>
    #end
    <td>$pb.NO_PB</td>
    <td>$pb.NO_LOT</td>
    <td>$pb.NAMA_MUKIM</td>
    <td>$pb.SYER_ATAS</td>
  </tr>
  #end
</table>
</fieldset>
<input type="hidden" name="id_fail" value="$idFail" />
<input type="hidden" name="id_permohonan" value="$idPermohonan" />
<input name="idHakmilikPB" type="hidden" value="$idHakmilikPB" />
<input type="hidden" name="id_status" value="$id_status" />

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<script>

function borangM(idHakmilikPB){

	document.${formName}.idHakmilikPB.value = idHakmilikPB;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=tabBorangM";
	document.${formName}.submit();


}
</script>
