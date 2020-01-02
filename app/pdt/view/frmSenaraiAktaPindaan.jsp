
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<input name="action" type="hidden" value="$action" />
<input name="idAkta" type="hidden" id="idAkta" value="$idAkta"/>
<input name="idAktaPindaan" type="hidden" id="idAktaPindaan" value="$idAktaPindaan"/>
&nbsp;
<fieldset>
          <legend><strong>Maklumat Akta</strong></legend>
          <table width="100%">
          <tr>
            <td width="29%" scope="row">No Akta Asal</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>$noAkta</label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta Asal</td>
            <td scope="row">:</td>
            <td>$namaAkta</td>
          </tr>
          <tr>
            <td scope="row">No Fail</td>
            <td scope="row">:</td>
            <td>$noFail</td>
          </tr>
            <tr>
            <td colspan=3>
                    </td>
          </tr>
          <tr>
            <td colspan=3>
             <a href="javascript:onClick=viewAktaBlob('$!idAkta');" style="color:#0000FF">[Lihat Lampiran]</a>
      <a href="javascript:onClick=paparAkta('$!idAkta');" style="color:#0000FF">[Papar Akta]</a>	
            </td>
          </tr>
        </table>
</fieldset>
        <p>
  <fieldset>
  <legend><strong>Senarai Akta Pindaan</strong></legend>

  <input type="button" value="Kembali" onclick="kembaliAktaPindaan()"/>  
  #parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="6%" height="25" scope="row">No</td>
      <td width="24%">Nama Akta Pindaan</td>
      <td width="16%">No Akta Pindaan</td>
      <td width="26%">Tarikh Daftar</td>
      <td width="28%">&nbsp;</td>
    </tr>
    #set ($akta = '')
    #set ($counter=0)
    #foreach ($akta in $SenaraiFail)
    #set ($counter = $counter + 1)
    #if ($akta.no == '') 
    #set ($row = 'row1')
    #elseif ($akta.no % 2 != 0)
    #set ($row = 'row1')
    #else 
    #set ($row = 'row2')
    #end
    <tr>
    <td height="20" class="$row">$counter</td>
    <td class="$row"><a href="javascript:view_akta_pindaan_detail('$akta.idAktaPinda')" class="style1">$akta.namaAktaPindaan</a></td>
    <td class="$row">$akta.noAktaPindaan</td>
    <td><span class="$row">$akta.tarikhAktaPindaan</span></td>
    <td><a href = "javascript:viewAktaPindaanBlob('$akta.idAktaPinda')">
    <img src="../img/pdf-small.png" alt="" border="0" /></a></td>
    </tr>
    #end
  </table>
</fieldset>

    <script>
function edit_item(id){
	//document.${formName}.command.value="papar";
	document.${formName}.idAkta.value = id;
	doAjaxCall${formName}("papar","");
	//document.${formName}.submit();
}
function viewAktaBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=akta&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function doSave() {
	if (document.${formName}.txtNoAktaPindaan.value == "") {
		alert("Sila Masukkan No Akta Pindaan");
		document.${formName}.txtNoAktaPindaan.focus();
		return;
	}
	doAjaxCall${formName}("simpan","");
}

function doUpdate() {
	if (document.${formName}.txtNoAktaPindaan.value == "") {
		alert("Sila Masukkan No Akta Pindaan");
		document.${formName}.txtNoAktaPindaan.focus();
		return;
	}
		if (document.${formName}.txtNamaAktaPindaan.value == "") {
		alert("Sila Masukkan Nama Akta Pindaan");
		document.${formName}.txtNamaAktaPindaan.focus();
		return;
	}
	doAjaxCall${formName}("simpan","mode=doUpdate");
}

function kemaskini() {
doAjaxCall${formName}("kemaskini");
}

function view_akta_pindaan(id) {
	document.${formName}.idAkta.value = id;
	doAjaxCall${formName}("paparsenaraipindaan","");
}

function view_akta_pindaan_detail(id) {
	document.${formName}.idAktaPindaan.value = id;
	doAjaxCall${formName}("paparsenaraipindaan_detail","");
}

function tambahAktaPindaan() {
doAjaxCall${formName}("tambahAktaPindaan","");
}

function kembaliAktaPindaan() {
doAjaxCall${formName}("","");
}

function paparAkta(idAkta) {
    var url = "../x/${securityToken}/ekptg.view.pdt.FrmViewAkta2?idAkta="+idAkta;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
    </script>
