<table width="100%" id="paging">
  <tr>
    <td width="100%" align="right"><!-- MAKLUMAT PERMOHONAN -->
      #if($idSkrin == '1') <img src="../img/1current.png" alt="" border="0" title="Maklumat Permohonan"/> #else <img src="../img/1enable.png" alt="" border="0" title="Maklumat Permohonan" onclick="papar1('$idFail')" onMouseOver="this.style.cursor='pointer';"/> #end
      <!-- JABATAN TEKNIKAL -->
      #if ($idStatus != '1610197' || $idStatus != '1610198' || $idStatus != '1610199')
      #if($idSkrin == '2') <img src="../img/2current.png" alt="" border="0" title="Jabatan Teknikal"/> #else <img src="../img/2enable.png" alt="" border="0" title="Jabatan Teknikal" onclick="papar2('$idFail')" onMouseOver="this.style.cursor='pointer';"/> #end
      #else <img src="../img/2disable2.png" alt="" border="0" title="Jabatan Teknikal"/> #end
      <!-- LAWATAN TAPAK -->
      #if ($idStatus != '1610197' || $idStatus != '1610198' || $idStatus != '1610199' || $idStatus != '1610200')
      #if($idSkrin == '3') <img src="../img/3current.png" alt="" border="0" title="Lawatan Tapak"/> #else <img src="../img/3enable.png" alt="" border="0" title="Lawatan Tapak" onclick="papar3('$idFail')" onMouseOver="this.style.cursor='pointer';"/> #end
      #else <img src="../img/3disable2.png" alt="" border="0" title="Lawatan Tapak"/> #end
      <!-- CETAKAN KERTAS RINGKASAN -->
      #if ($idStatus != '1610197' || $idStatus != '1610198' || $idStatus != '1610199' || $idStatus != '1610200' || $idStatus != '1610213')
      #if($idSkrin == '4') <img src="../img/4current.png" alt="" border="0" title="Cetakan Kertas Ringkasan"/> #else <img src="../img/4enable.png" alt="" border="0" title="Cetakan Kertas Ringkasan" onclick="papar4('$idFail')" onMouseOver="this.style.cursor='pointer';"/> #end
      #else <img src="../img/4disable2.png" alt="" border="0" title="Cetakan Kertas Ringkasan"/> #end
      <!-- MESYUARAT -->
      #if ($idStatus != '1610197' || $idStatus != '1610198' || $idStatus != '1610199' || $idStatus != '1610200' || $idStatus != '1610213' ||  $idStatus != '1610201' ||  $idStatus != '1610208')
      #if($idSkrin == '5') <img src="../img/5current.png" alt="" border="0" title="Mesyuarat"/> #else <img src="../img/5enable.png" alt="" border="0" title="Mesyuarat" onclick="papa5('$idFail')" onMouseOver="this.style.cursor='pointer';"/> #end
      #else <img src="../img/5disable2.png" alt="" border="0" title="Mesyuarat"/> #end
      <!-- CETAKAN SURAT TAWARAN -->
      #if ($idStatus != '1610197' || $idStatus != '1610198' || $idStatus != '1610199' || $idStatus != '1610200' || $idStatus != '1610213' ||  $idStatus != '1610201' ||  $idStatus != '1610210')
      #if($idSkrin == '6') <img src="../img/6current.png" alt="" border="0" title="Cetak Surat Tawaran"/> #else <img src="../img/6enable.png" alt="" border="0" title="Cetak Surat Tawaran" onclick="papar6('$idFail')" onMouseOver="this.style.cursor='pointer';"/> #end
      #else <img src="../img/6disable2.png" alt="" border="0" title="Cetakan Surat Tawaran"/> #end
      <!-- PERJANJIAN -->
      #if ($idStatus != '1610197' || $idStatus != '1610198' || $idStatus != '1610199' || $idStatus != '1610200' || $idStatus != '1610213' ||  $idStatus != '1610201' ||  $idStatus != '1610210' || $idStatus != '1610214' ||  $idStatus != '1610218')
      #if($idSkrin == '7') <img src="../img/7current.png" alt="" border="0" title="Perjanjian"/> #else <img src="../img/7enable.png" alt="" border="0" title="Perjanjian" onclick="papar6('$idFail')" onMouseOver="this.style.cursor='pointer';"/> #end
      #else <img src="../img/7disable.png" alt="" border="0" title="Perjanjian"/> #end </td>
  </tr>
</table>
<script>
function papar1(idFail){
	url = "../servlet/ekptg.view.php2.FrmPYWServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWMaklumatPermohonanView";
	document.${formName}.submit();
}
function papar2(idFail){
	url = "../servlet/ekptg.view.php2.FrmPYWServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWJabatanTeknikalView";
	document.${formName}.submit();
}
function papar3(idFail){
	url = "../servlet/ekptg.view.php2.FrmPYWServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWLawatanTapakView";
	document.${formName}.submit();
}
function papar4(idFail){
	url = "../servlet/ekptg.view.php2.FrmPYWServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWKertasRingkasanView";
	document.${formName}.submit();
}
function papar5(idFail){
	url = "../servlet/ekptg.view.php2.FrmPYWServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWMesyuaratView";
	document.${formName}.submit();
}
function papar6(idFail){
	url = "../servlet/ekptg.view.php2.FrmPYWServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWCetakSuratTawaran";
	document.${formName}.submit();
}
function papar7(idFail){
	url = "../servlet/ekptg.view.php2.FrmPYWServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWPerjanjianView";
	document.${formName}.submit();
}
function papar8(idFail){
	url = "../servlet/ekptg.view.php2.FrmPYWServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWPerjanjianSambungan";
	document.${formName}.submit();
}
</script>
