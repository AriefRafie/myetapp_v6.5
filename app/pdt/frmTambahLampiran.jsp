<link rel="stylesheet" type="text/css" href="../../css/eTapp_PDT.css" />
<!-- ::::::::: $idDokumen -->
#if ($isComplete)
<center>
<input type=button value="Tutup" onClick="doClose('$idDokumen');" />
</center>
#else
&nbsp;
<fieldset>
<legend><strong>Maklumat Lampiran</strong></legend>
<table width="100%">
#foreach ($dokumen in $Dokumen)
<input name="idDokumen" type="hidden" value="$dokumen.id_Dokumen" />
  <tr>
    <td width="29%" scope="row">No. Rujukan Dokumen</td>
    <td width="1%" scope="row">:</td>
    <td width="70%">
    <input name="txtNoRujDokumen" type="text" value="$dokumen.no_Rujukan_Dokumen" size="44" readonly="true" />
    </td>
  </tr>
  <tr>
    <td scope="row" valign="top">Jenis Dokumen</td>
    <td scope="row" valign="top">:</td>
    <td><textarea name="txtJenisDokumen" cols="41" readonly="true">$dokumen.keterangan</textarea></td>
  </tr>

  <tr>
    <td scope="row" valign="top">Lampiran</td>
    <td scope="row" valign="top">:</td>
    
    <td>
    #foreach( $i in [1..$num_files] )
      <input name="txtLampiran" type="file" id="txtLampiran" size="44" />
    #end
    </td>
    
  </tr>
  <tr>
    <td colspan="3" scope="row">&nbsp;</td>
  </tr>

  <tr>
    <td colspan="3" align="center" scope="row">
      <label>
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanLampiran()" />
      </label>
      <label>
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
        </label>    </td>
  </tr>
 #end
</table>
</fieldset>
 <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-14</strong></td>
  	</tr>
  </table>

#end

<script>
function kembali(){

	window.close();
}
function simpanLampiran(){
	
	var id = document.${formName}.idDokumen.value ;
	
	document.${formName}.enctype = "multipart/form-data";
	document.${formName}.action = "?popupCommand=simpan&idDokumen="+id;
	document.${formName}.submit();
	//window.close();
	//window.opener.refreshValue(); 
}

function doClose(id){
	//alert(" id  : "+id);
	//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "papar";
	//alert("tutup?");
	//window.opener.refreshValue();
	window.opener.edit_item(id);

	window.close();
	
	
	
	}
</script>

