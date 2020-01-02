<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table width="100%">
<tr>
	<td width="5"></td>
	<td align="left"> <strong>DOKUMEN</strong></td>
</tr>
</table>
<input name="mode" type="hidden" value="" />
&nbsp;
  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td align="right" scope="row">Jenis Dokumen</td>
      <td scope="row">:</td>
      <td>
	      <label>
	        <input name="txtNoRujDokuman" type="text" id="txtNoRujDokuman" size="44" maxlength="100"/>
	      </label>
      </td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row">Tajuk Dokumen</td>
      <td width="1%" scope="row" valign="top" >:</td>
      <td width="70%"><label>
        <textarea name="txtTajukDokumen" cols="41" id="txtTajukDokumen">$tajukDokumen</textarea>
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Bahagian / Urusetia</td>
      <td scope="row">:</td>
      <td>$selectSeksyen</td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Dokumen</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txdTarikhDokumen" type="text" id="txdTarikhDokumen" value="$tarikhDokumen" size="10" />
      <a href="javascript:displayDatePicker('txdTarikhDokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </label></td>
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
     #parse("app/utils/record_paging.jsp") 
  </p>
  <table width="100%" >
    <tr class="table_header">
      <td width="1%" scope="row"><strong>Bil.</strong></td>
      <td width="20%"><strong>Jenis Dokumen</strong></td>
      <td width="34%"><strong>Tajuk Dokumen</strong></td>
      <td width="24%"><strong>Tarikh Dokumen</strong></td>
      <td width="27%"><strong>Bahagian / Urusetia</strong></td>
      <td></td>
    </tr>
    #if($SenaraiFail.size() > 0)
    #foreach ($dokumen in $SenaraiFail)
    #if ($dokumen.bil == '') 
  		#set ($row = 'row1')
    #elseif ($dokumen.bil % 2 != 0)
  	   	#set ($row = 'row1')
  	#else 
  		#set ($row = 'row2')
  	#end 
    <tr>
      <td class="$row">$dokumen.bil.</td>
      <td class="$row">
       #if ($dokumen.bil != '') 
      	<a href="javascript:edit_item('$dokumen.idDokumen')" class="style1">$dokumen.jenisDokumen</a>
       #end      
      </td>
      <td class="$row"><a href="javascript:edit_item('$dokumen.idDokumen')" class="style1">$dokumen.tajukDokumen</a></td>
      <td class="$row">$dokumen.tarikhDokumen</td>
      <td class="$row">$dokumen.kodSeksyen</td>
      <td class="$row">
      <a alt="Hapus Dokumen" href = "javascript:deleteDokumen('$dokumen.idDokumen')">
	&nbsp;&nbsp;<img border="0" src="../img/delete.gif" /></a> 
	</td>
    </tr>
  	#end 
  	#else
	<tr>
		<td colspan="6">Rekod Tidak Dijumpai</td>
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

<script>
function tambahLampiran(id,sendCommand){
	//alert();
	var url = "../x/${securityToken}/ekptg.view.pdt.FrmTambahLampiran?idDokumen="+id+"&sendCommand="+sendCommand;
	var hWnd = window.open(url,'printuser','width=800,height=400, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}
function deleteDokumen(idDokumen) {
    if ( !window.confirm("Dokumen akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
    doAjaxCall${formName}("Delete","action=Delete&idDokumen="+idDokumen);
}

</script>