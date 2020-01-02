<style type="text/css">
<!--
  .mandatory {color: #FF0000}
-->
</style>
<fieldset>
  <legend>MAKLUMAT FAIL AKTA</legend>
  <table width="100%" border="0">      
    
    <tr>
      <td width="29%" align="right" valign="top"><strong>No Akta</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="70%" valign="top">$Akta_NoAkta</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Nama Akta</strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top">$Akta_NamaAkta</td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top"><strong>No Fail</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="70%" valign="top">$Akta_NoFail</td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3">
      <a href="javascript:onClick=viewAktaBlob('$!Akta_IDAkta');" style="color:#0000FF">[Lihat Lampiran]</a>
      <a href="javascript:onClick=paparAkta('$!Akta_IDAkta');" style="color:#0000FF">[Papar Akta]</a>	 </td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend>$MAIN_LEGEND </legend>
<div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab" tabindex="0" onclick="doChangeTab(0)">AKTA</li>
      <li class="TabbedPanelsTab" tabindex="1" onclick="doChangeTab(1)">PENGGAL</li>
      <li class="TabbedPanelsTab" tabindex="2" onclick="doChangeTab(2)">BAHAGIAN</li>
      <li class="TabbedPanelsTab" tabindex="3" onclick="doChangeTab(3)">BAB</li>
      <li class="TabbedPanelsTab" tabindex="4" onclick="doChangeTab(4)">SEKSYEN</li>
      <li class="TabbedPanelsTab" tabindex="5" onclick="doChangeTab(5)">SUBSEKSYEN</li>
      <li class="TabbedPanelsTab" tabindex="6" onclick="doChangeTab(6)">PARA</li>
      <li class="TabbedPanelsTab" tabindex="7" onclick="doChangeTab(7)">SUBPARA</li>
      <li class="TabbedPanelsTab" tabindex="8" onclick="doChangeTab(8)">JADUAL(PARA)</li>
      <li class="TabbedPanelsTab" tabindex="9" onclick="doChangeTab(9)">JADUAL(SUBPARA)</li>
      <li class="TabbedPanelsTab" tabindex="10" onclick="doChangeTab(10)">JADUAL(SUB-SUBPARA)</li>
    </ul>
    <div class="TabbedPanelsContentGroup">
      <div class="TabbedPanelsContent">
#if ($selectedTab == '0')
    #parse ("app/pdt/frmAktaTabAkta.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '1')
    #parse ("app/pdt/frmAktaTabPenggal.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '2')
    #parse ("app/pdt/frmAktaTabBahagian.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '3')
    #parse ("app/pdt/frmAktaTabBab.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '4')
    #parse ("app/pdt/frmAktaTabSeksyen.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '5')
    #parse ("app/pdt/frmAktaTabSubSeksyen.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '6')
    #parse ("app/pdt/frmAktaTabPara.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '7')
    #parse ("app/pdt/frmAktaTabSubPara.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '8')
    #parse ("app/pdt/frmAktaTabJadualPara.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '9')
    #parse ("app/pdt/frmAktaTabJadualSubPara.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '10')
    #parse ("app/pdt/frmAktaTabJadualSubSubPara.jsp")
#end
      </div>
    </div>
  </div>
</fieldset>
<p>
  <input type="hidden" id="actionX" name="actionX" value="$actionX" />
  <input type="hidden" id="selectedTab" name="selectedTab" value="$!selectedTab" />
  <input type="hidden" id="Akta_IDAkta" name="Akta_IDAkta" value="$!Akta_IDAkta" />
  <input type="hidden" id="Penggal_IDPenggal" name="Penggal_IDPenggal" value="$!Penggal_IDPenggal" />
  <input type="hidden" id="Bahagian_IDBahagian" name="Bahagian_IDBahagian" value="$!Bahagian_IDBahagian" />
  <input type="hidden" id="Bab_IDBab" name="Bab_IDBab" value="$!Bab_IDBab" />
</p>
<script type="text/javascript">
<!--
  var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1", {defaultTab:$selectedTab});
//-->
</script>
<script type="text/javascript">

function doCancel() {
 doAjaxCall${formName}("","action=$action");
}

function doChangeTab(TabID) {
    //document.${formName}.action.value = '$action';
    document.${formName}.selectedTab.value = TabID;
    //document.${formName}.submit();
    //doAjaxCall${formName}("","action=$action");
    doAjaxCall${formName}("","action=view");
}

function doChangeSOC(submitType) {
    //document.${formName}.action.value = '$action';
    //doAjaxCall${formName}(submitType);
    //alert("XXX"+$action);
    doAjaxCall${formName}(submitType,"action=view");
}

function doAdd() {
    doAjaxCall${formName}("","action=update");
}

function doUpdate() {
if (document.${formName}.txtNoAkta.value == "") {
	alert("Sila masukkan no akta");
	document.${formName}.txtNoAkta.focus();
	return;
}
else if (document.${formName}.txtNamaAkta.value == "") {
	alert("Sila masukkan nama akta");
	document.${formName}.txtNamaAkta.focus();
	return;
}
var x = create_request_string(document.${formName});
//alert(x);
//return;
document.${formName}.method="post";
document.${formName}.enctype="multipart/form-data";
document.${formName}.encoding="multipart/form-data";
document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&mode=update&action=save&"+x;
document.${formName}.submit();
}

function doSave() {
    //document.${formName}.action.value = 'save';
    //document.${formName}.submit();
    doAjaxCall${formName}("","action=save");
}

function doSaveAkta() {
alert("dalam save akta baru");
}

function doBack() {
    //document.${formName}.action.value = 'view';
    //document.${formName}.submit();
    doAjaxCall${formName}("","action=view");
}
function doDelete() {
    //if ( !window.confirm("Akta [$Akta_NoAkta] akan dihapuskan. Adakah Anda Pasti?") ) return;	
	if ( !window.confirm("Akta akan dihapuskan. Adakah Anda Pasti?") ) return;	
    document.${formName}.action.value = 'delete';
    document.${formName}.submit();
}
function doPrint() {
    document.${formName}.action.value = 'print';
    document.${formName}.submit();
}
function addPegawaiSulit() {
}

function doBack() {
	doAjaxCall${formName}("");
}

///PENGGAL
function doSavePenggal() {
	if (document.${formName}.txtNoPenggal.value == "") {
		alert("Sila masukkan no penggal");
		document.${formName}.txtNoPenggal.focus();
		return;
	}
	doAjaxCall${formName}("","action=save&mode=add");
}

function viewPenggal(ID_Penggal) {
	  document.${formName}.Penggal_IDPenggal.value = ID_Penggal;
	  doAjaxCall${formName}("","action=viewDetail&Penggal_IDPenggal="+ID_Penggal);
}

function doKemaskiniPenggal() {
	doAjaxCall${formName}("","action=save&mode=update");
}

function doHapusPenggal(ID_Penggal) {
	  if ( !window.confirm("Penggal dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.${formName}.Penggal_IDPenggal.value = ID_Penggal;
	  doAjaxCall${formName}("","action=delete&Penggal_IDPenggal="+ID_Penggal);
}

//BAHAGIAN
function doSaveBahagian() {
	
	/*
	if (document.${formName}.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.${formName}.SOC_Penggal.focus();
		return;
	}
	*/
	if (document.${formName}.txtNoBahagian.value == "") {
		alert("Sila masukkan no bahagian");
		document.${formName}.txtNoBahagian.focus();
		return;
	}
	doAjaxCall${formName}("","action=save&mode=add");
}

function doKemaskiniBahagian() {
	doAjaxCall${formName}("","action=save&mode=update");
}

function viewBahagian(ID_Bahagian) {
	  document.${formName}.Bahagian_IDBahagian.value = ID_Bahagian;
	  doAjaxCall${formName}("","action=viewDetail&Bahagian_IDBahagian="+ID_Bahagian);
}

function doHapusBahagian(ID_Bahagian) {
	  if ( !window.confirm("Bahagian dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.${formName}.Bahagian_IDBahagian.value = ID_Bahagian;
	  doAjaxCall${formName}("","action=delete&Bahagian_IDBahagian="+ID_Bahagian);
}

//BAB
function doSaveBab() {
	/*
	if (document.${formName}.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.${formName}.SOC_Penggal.focus();
		return;
	}
	*/
	if (document.${formName}.txtNoBab.value == "") {
		alert("Sila masukkan no bab");
		document.${formName}.txtNoBab.focus();
		return;
	}
	doAjaxCall${formName}("","action=save&mode=add");
}

function doKemaskiniBab() {
	doAjaxCall${formName}("","action=save&mode=update");
}

function viewBab(ID_Bab) {
	  document.${formName}.Bab_IDBab.value = ID_Bab;
	  doAjaxCall${formName}("","action=viewDetail&Bab_IDBab="+ID_Bab);
}

function doHapusBab(ID_Bab) {
	  if ( !window.confirm("Bab dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.${formName}.Bab_IDBab.value = ID_Bab;
	  doAjaxCall${formName}("","action=delete&Bab_IDBab="+ID_Bab);
}

//SEKSYEN
function doSaveSeksyen() {
	/*
	if (document.${formName}.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.${formName}.SOC_Penggal.focus();
		return;
	}
	else 
	*/
	if (document.${formName}.txtSeksyen.value == "") {
		alert("Sila masukkan no seksyen");
		document.${formName}.txtSeksyen.focus();
		return;
	}
	doAjaxCall${formName}("","action=save&mode=add");
}

function doKemaskiniSeksyen() {
	doAjaxCall${formName}("","action=save&mode=update");
}

function viewSeksyen(ID_Seksyen) {
	  document.${formName}.Seksyen_IDSeksyen.value = ID_Seksyen;
	  doAjaxCall${formName}("","action=viewDetail&Seksyen_IDSeksyen="+ID_Seksyen);
}

function doHapusSeksyen(ID_Seksyen) {
	  if ( !window.confirm("Seksyen dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.${formName}.Seksyen_IDSeksyen.value = ID_Seksyen;
	  doAjaxCall${formName}("","action=delete&Seksyen_IDSeksyen="+ID_Seksyen);
}
/// SUB SEKSYEN
function doSaveSubSeksyen() {
	/*
	if (document.${formName}.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.${formName}.SOC_Penggal.focus();
		return;
	}
	else 
	*/
	if (document.${formName}.txtSubSeksyen.value == "") {
		alert("Sila masukkan no seksyen");
		document.${formName}.txtSubSeksyen.focus();
		return;
	}
	doAjaxCall${formName}("","action=save&mode=add");
}

function doKemaskiniSubSeksyen(ID_SubSeksyen) {
	doAjaxCall${formName}("","action=save&mode=update&SubSeksyen_IDSubSeksyen="+ID_SubSeksyen);
}

function viewSubSeksyen(ID_SubSeksyen) {
	  document.${formName}.SubSeksyen_IDSubSeksyen.value = ID_SubSeksyen;
	  doAjaxCall${formName}("","action=viewDetail&SubSeksyen_IDSubSeksyen="+ID_SubSeksyen);
}

function doHapusSubSeksyen(ID_SubSeksyen) {
	  if ( !window.confirm("Sub Seksyen dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.${formName}.SubSeksyen_IDSubSeksyen.value = ID_SubSeksyen;
	  doAjaxCall${formName}("","action=delete&SubSeksyen_IDSubSeksyen="+ID_SubSeksyen);
}
/// PARA
function doSavePara() {
	if (document.${formName}.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.${formName}.SOC_Penggal.focus();
		return;
	}
	else if (document.${formName}.txtPara.value == "") {
		alert("Sila masukkan maklumat para");
		document.${formName}.txtPara.focus();
		return;
	}
	doAjaxCall${formName}("","action=save&mode=add");
}

function doKemaskiniPara() {
	doAjaxCall${formName}("","action=save&mode=update");
}

function viewPara(ID_Para) {
	  document.${formName}.Para_IDPara.value = ID_Para;
	  doAjaxCall${formName}("","action=viewDetail&Para_IDPara="+ID_Para);
}

function doHapusPara(ID_Para) {
	  if ( !window.confirm("Para dan kesemua maklumat yang berkaitan akan dihapuskan.\Adakah Anda Pasti?") ) return;	
	  document.${formName}.Para_IDPara.value = ID_Para;
	  doAjaxCall${formName}("","action=delete&Para_IDPara="+ID_Para);
}

/// SUB PARA
function doSaveSubPara() {
	if (document.${formName}.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.${formName}.SOC_Penggal.focus();
		return;
	}
	else if (document.${formName}.txtSubPara.value == "") {
		alert("Sila masukkan maklumat para");
		document.${formName}.txtSubPara.focus();
		return;
	}
	doAjaxCall${formName}("","action=save&mode=add");
}

function doKemaskiniSubPara() {
	doAjaxCall${formName}("","action=save&mode=update");
}

function viewSubPara(ID_subPara) {
	  document.${formName}.SubPara_IDSubPara.value = ID_subPara;
	  doAjaxCall${formName}("","action=viewDetail&SubPara_IDSubPara="+ID_subPara);
}

function doHapusSubPara(ID_subPara) {
	  if ( !window.confirm("Sub para dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.${formName}.SubPara_IDSubPara.value = ID_subPara;
	  doAjaxCall${formName}("","action=delete&SubPara_IDSubPara="+ID_subPara);
}

function paparAkta(idAkta) {
    var url = "../x/${securityToken}/ekptg.view.pdt.FrmViewAkta2?idAkta="+idAkta;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function viewAktaBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=akta&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>