<style type="text/css">
<!--
.mandatory {color: #FF0000}
-->
</style>
<input name="idAkta" type="hidden" id="idAkta" value="$idAkta"/>
<input name="idAktaPindaan" type="hidden" id="idAktaPindaan" value="$!idAktaPindaan"/>
<input name="tabId" type="hidden" id="tabId" value="$tabId"/>
<input name="hitbutton" type="hidden" value="$hitbutton" />
<input name="noAktaAsal" type="hidden" value="$noAkta" />
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
        </table>
        </fieldset>
        <p>
        <fieldset>
        <legend><strong>Maklumat Akta Pindaan</strong></legend>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="29%" scope="row"><span class="mandatory">*</span>No Akta Pindaan</td>
    <td width="1%" scope="row">:</td>
    <td width="70%"><label>
      <input $readOnly value="$!txtNoAktaPindaan" name="txtNoAktaPindaan" 
      type="text" id="txtNoAktaPindaan" 
      onblur="this.value=this.value.toUpperCase()" readonly="readonly" class="disabled"/>
    </label></td>
  </tr>
  <tr>
    <td scope="row"><span class="mandatory">*</span>Nama Akta Pindaan</td>
    <td scope="row">:</td>
    <td><label>
      <input $readOnly  name="txtNamaAktaPindaan" type="text" value="$!txtNamaAktaPindaan" size="45" maxlength="100" readonly="readonly" class="disabled"/>
    </label></td>
  </tr>
  <tr>
    <td scope="row">Tarikh Kuatkuasa Pindaan</td>
    <td scope="row">:</td>
    <td><label>
      <input $readOnly name="txtTarikhKuatkuasa" type="text" id="txtTarikhKuatkuasa" value="$txtTarikhKuatkuasa" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" readonly="readonly" class="disabled"/>
     </td>
  </tr>
  <tr>
    <td scope="row">No Warta</td>
    <td scope="row">:</td>
    <td><label>
      <input $readOnly name="txtNoWarta" type="text" id="txtNoWarta" value="$txtNoWarta" readonly="readonly" class="disabled"/>
    </label></td>
  </tr>
  <tr>
    <td scope="row">Tarikh Warta</td>
    <td scope="row">:</td>
    <td><label>
      <input $readOnly name="txtTarikhWarta" type="text" id="txtTarikhWarta" value="$!txtTarikhWarta" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" readonly="readonly" class="disabled"/>
      </td>
  </tr>
  <tr>
    <td scope="row">Tarikh Perkenaan Di Raja</td>
    <td scope="row">:</td>
    <td><label>
      <input $readOnly name="txtTarikhPerkenaanDiraja" type="text" id="txtTarikhPerkenaanDiraja" value="$!txtTarikhPerkenaanDiraja" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" readonly="readonly" class="disabled"/>
      </td>
  </tr>
  <tr>
    <td scope="row">Tarikh Penyiaran Dalam Warta</td>
    <td scope="row">:</td>
    <td><label>
      <input $readOnly name="txtTarikhPenyiaranDlmWarta" type="text" id="txtTarikhPenyiaranDlmWarta" value="$!txtTarikhPenyiaranDlmWarta" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" readonly="readonly" class="disabled"/>
      </td>
  </tr>
  
  <tr>
    <td height="20" scope="row">No Fail</td>
    <td scope="row">:</td>
    <td><label>
        <input $readOnly name="txtNoFail" type="text" id="txtNoFail" value="$!txtNoFail" size="45" maxlength="45" readonly="readonly" class="disabled"/>
    </label></td>
  </tr>
  <tr>
    <td scope="row">No Memorandum Jemaah Menteri</td>
    <td scope="row">:</td>
    <td><label>
      <input $readOnly name="txtNoMemorandumMenteri" type="text" id="txtNoMemorandumMenteri" value="$!txtNoMemorandumMenteri" readonly="readonly" class="disabled"/>
    </label></td>
  </tr>
  <tr>
    <td scope="row">Keputusan Jemaah Menteri</td>
    <td scope="row">:</td>
    <td><label>
      <input $readOnly name="txtKptsnJemaahMenteri" type="text" id="txtKptsnJemaahMenteri" value="$!txtKptsnJemaahMenteri" readonly="readonly" class="disabled" />
    </label></td>
  </tr>
  <tr>
    <td scope="row">Keputusan Majlis Tanah Negara</td>
    <td scope="row">:</td>
    <td><label>
      <input $readOnly name="txtKptsnMajlisTanah" type="text" id="txtKptsnMajlisTanah" value="$!txtKptsnMajlisTanah" readonly="readonly" class="disabled"/>
    </label></td>
  </tr>
  <tr>
    <td scope="row">Catatan</td>
    <td scope="row">:</td>
    <td><label>
      <input $readOnly name="txtCatatan" type="text" id="txtCatatan" value="$!txtCatatan" readonly="readonly" class="disabled"/>
    </label></td>
  </tr>

  <tr>
    <td scope="row">Tarikh Daftar</td>
    <td scope="row">:</td>
    <td><label>
      <input $readOnly name="txtTarikhDaftar" type="text" id="txtTarikhDaftar" value="$!txtTarikhDaftar" size="11" maxlength="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" readonly="readonly" class="disabled"/>
      </td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>
<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doGetSenaraiAktaPindaan()"/></td>
  </tr>
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

function viewAktaPindaanBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=aktapindaan&id="+id;
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
			if (document.${formName}.txtNamaAktaPindaan.value == "") {
		alert("Sila Masukkan Nama Akta Pindaan");
		document.${formName}.txtNamaAktaPindaan.focus();
		return;
	}
	
	
var x = create_request_string(document.${formName});
//alert(x);
//return;
document.${formName}.method="post";
document.${formName}.enctype="multipart/form-data";
document.${formName}.encoding="multipart/form-data";
document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan&command=simpan&"+x;
document.${formName}.submit();
	//doAjaxCall${formName}("simpan","");
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
	
	
var x = create_request_string(document.${formName});
//alert(x);
//return;
document.${formName}.method="post";
document.${formName}.enctype="multipart/form-data";
document.${formName}.encoding="multipart/form-data";
document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan&command=simpan&mode=doUpdate&"+x;
document.${formName}.submit();
	
	//doAjaxCall${formName}("simpan","mode=doUpdate");
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
	//document.${formName}.idAkta.value = id;
	doAjaxCall${formName}("tambahAktaPindaan","");
}

function doGetSenaraiAktaPindaan() {
doAjaxCall${formName}("paparsenaraipindaan","");
}

function kembaliAktaPindaan() {
doAjaxCall${formName}("","");
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}


function paparAkta(idAkta) {
    var url = "../x/${securityToken}/ekptg.view.pdt.FrmViewAkta2?idAkta="+idAkta;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


</script>
