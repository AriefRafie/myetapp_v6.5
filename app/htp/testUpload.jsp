<table width="100%" border="0">
  <tr>
    <td width="43%"><div align="right">Direktori</div></td>
    <td width="2%"><div align="center">:</div></td>
    <td width="55%"><label>
      <input id="fileupload" name="fileupload" type="file" size="40" />
      </label>
    </td>
  </tr>
  <tr>
    <td width="43%"><div align="right">Perihal Imej</div></td>
    <td width="2%"><div align="center">:</div></td>
    <td width="55%" rowspan="5"><label>
      <textarea name="txtPerihalImej" cols="30" rows="6" class="$disabled" id="txtPerihalImej" $readonly="$readonly"  style="text-transform:uppercase;">$txtPerihalImej</textarea>
      </label>
        <label></label>
    </td>
  </tr>
  <tr>
    <td width="43%">&nbsp;</td>
    <td width="2%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input type="button" name="btnSave" id="btnSave" value="Simpan"  onClick="simpanDetailImej()" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<script>
function simpanDetailImej(id){
if ( !window.confirm("Adakah Anda Pasti?")) return;
	var perihal = document.${formName}.txtPerihalImej.value ;
	alert(perihal);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmUploadFile&nextAction=tambahDetailImej&idHakmilik="+53+"&txtPerihalImej="+perihal;
	//document.${formName}.nextAction = "?_portal_module=ekptg.view.ppt.FrmUploadFile&nextAction=tambahDetailImej&idHakmilik="+53;
	//doAjaxCall${formName}("PendaftaranImej","nextAction=tambahDetailImej&idHakmilik="+53);
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();
}
</script>
