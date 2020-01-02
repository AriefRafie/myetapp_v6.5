

<!--<table width="100%" border="0" cellspacing="2" cellpadding="2" id="table_UM">
	<tr>	
    	<td></td>
  </tr>
	<tr>	
    	<td>
    		
		<fieldset>	
        <legend><strong>Manual Pengguna </strong><input size="50" id="tambahDocUser" name="tambahDocUser"  type="file" onChange="uploadDoc2(this,'manual','table_UM');">></legend>
  <br />     
 <table border="0" cellspacing="0" cellpadding="0" width="100%" id="tableDokumen" >
 <tr>
  <td valign="top" colspan="5"> </td>
  </tr>
  <tr  >
<td width="20%"   align="left" valign="top">Nama Dokumen</td>
<td width="2%"   align="center" valign="top">:</td>
<td width="78%"   align="left" valign="top"><input size="50" type="text" id="NAMA_DOKUMEN" name="NAMA_DOKUMEN">	</td>
</tr>
 <tr  >
<td width="20%"   align="left" valign="top">Keterangan Dokumen</td>
<td width="2%"   align="center" valign="top">:</td>
<td width="78%"   align="left" valign="top"><textarea rows="4" cols="50" id="KETERANGAN" name="KETERANGAN"></textarea></td>
</tr>
<tr  >
<td width="20%"   align="left" valign="top">Muat Naik</td>
<td width="2%"   align="center" valign="top">: </td>
<td width="78%"   align="left" valign="top"> <input size="50" id="tambahDocUser" name="tambahDocUser"  type="file"></td>
</tr>


 <tr  >
<td width="20%"   align="left" valign="top"></td>
<td width="2%"   align="center" valign="top"></td>
<td width="78%"   align="left" valign="top"> <input size="50" id="buttonDocUser" name="buttonDocUser"  type="button" onClick="uploadDoc2(this,'manual','tableDokumen2');" value="Simpan"></td>
</tr>
</table>
<br />
-->
<div id="divTable">
 <table border="0" cellspacing="1" cellpadding="1" width="100%" id="tableDokumen2" >
 <tr>
  <td valign="top"  width="1%" colspan="5">
 </td>
  </tr>
  <tr class="table_header" >
<td width="16%"   align="center" valign="top">Bil.</td>
<td width="46%"   align="center" valign="top">Nama Dokumen </td>
<td width="46%"   align="center" valign="top">Keterangan Dokumen </td>
<td width="20%"   align="center" valign="top">Tindakan</td>

		   
	</tr>
	#if($listDokumen.size()>0)
	<!--gred = userHQ-->
#foreach($list in $listDokumen)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td  align="center" valign="top" class="$list.rowCss" >$list.BIL</td>
<td  align="left" valign="top" class="$list.rowCss" ><a href="javascript:paparDoc('$list.ID_DOKUMENADMIN');"><font color="blue"><u>$list.NAMA_DOKUMEN</u></font></a></td>
<td  align="left" valign="top" class="$list.rowCss" ><textarea rows="3" cols="50" id="KETERANGAN$list.ID_DOKUMENADMIN" name="KETERANGAN$list.ID_DOKUMENADMIN" >$list.KETERANGAN</textarea><br /><input size="50" id="buttonDocUser" name="buttonDocUser"  type="button" onClick="doDivAjaxCall$formname('table_UM','simpanDetails','ID_DOKUMENADMIN=$list.ID_DOKUMENADMIN&CSS_ID=$list.ID_REF');" value="Simpan"></td>
<td  align="center" valign="top" class="$list.rowCss" ><a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('tableDokumen2','showDokumen','ID_DOKUMENADMIN=$list.ID_DOKUMENADMIN&FLAG_DELETE=Y&CSS_ID=$list.ID_REF');}"><img src="../img/delete.gif" border="0"></a>
</td>
</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>
		
</div>