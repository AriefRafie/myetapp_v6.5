
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<input name="idAkta" type="hidden" id="idAkta" value="$!idAkta"/>
<input name="idAktaPindaan" type="hidden" id="idAktaPindaan" value="$!idAktaPindaan"/>
&nbsp;

<table width="100%">
<tr>
	<td width="5"></td>
	<td align="left"> <strong>AKTA PINDAAN</strong> </td>
</tr>
</table>

  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td width="29%" align="right" scope="row">No. Akta Asal</td>
      <td width="1%" scope="row">:</td>
      <td width="70%"><label>
        <input name="txtNoAkta" type="text" id="txtNoAkta" value="$!txtNoAkta" />
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Nama Akta</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtNamaAkta" type="text" id="txtNamaAkta" value="$!txtNamaAkta" />
      </label></td>
    </tr>
    
    <tr>
      <td width="29%" align="right" scope="row">No. Akta Pindaan  </td>
      <td width="1%" scope="row">:</td>
      <td width="70%"><label>
        <input name="txtNoAktaPindaan" type="text" id="txtNoAktaPindaan" value="$!txtNoAktaPindaan" />
      </label></td>
    </tr>
    
    <tr>
      <td align="right" scope="row">Nama Akta Pindaan</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtNamaAktaPindaan1" type="text" id="txtNamaAktaPindaan1" value="$!txtNamaAktaPindaan1" />
      </label></td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Kuatkuasa</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txtTarikhKuatkuasa1" type="text" id="txtTarikhKuatkuasa1" value="$!txtTarikhKuatkuasa1" size="10" />
      </label>
      <a href="javascript:displayDatePicker('txtTarikhKuatkuasa1',false,'dmy');">
      <img border="0" src="../img/calendar.gif"/></a>      </td>
    </tr>
     <tr>
      <td align="right" valign="top" scope="row"><i>Tag</i> Dokumen</td>
      <td scope="row" valign="top">:</td>
      <td>
      	<textarea name="tag_dokumen" cols="41" rows="3" onblur="this.value=this.value.toUpperCase()" >$!tag_Dokumen</textarea>
        <input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/>
      </td>
    </tr>
    
    <tr>
      <td align="right" scope="row">&nbsp;</td>
      <td scope="row">&nbsp;</td>
      <td><label>
        <input onClick="cari()" type="button" name="cmdCari" id="cmdCari" value="Cari" />
        </label>
          <label>
          <input type="reset" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="Kosong()"/>
        </label></td>
    </tr>
  </table>
</fieldset>
  <fieldset>
  <legend><strong>Senarai Akta</strong></legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%" >
    <tr class="table_header">
      <td width="3%" scope="row"><strong>Bil.</strong></td>
      <td width="20%"><strong>No. Akta</strong></td>
      <td width="60%"><strong>Nama Akta</strong></td>
      <td width="12%"><strong>Tarikh Kuatkuasa</strong></td>
      <td width="5%">&nbsp;</td>
    </tr>
    #set ($akta = '')
    #foreach ($akta in $SenaraiFail)
    #if ($akta.no == '') 
    #set ($row = 'row1')
    #elseif ($akta.no % 2 != 0)
    #set ($row = 'row1')
    #else 
    #set ($row = 'row2')
    #end
    <tr>
    <td height="20" class="$row">$akta.no.</td>
    <td class="$row">
     #if ($akta.no != '') 
    <a href="javascript:view_akta_pindaan('$akta.id_Akta')" class="style1">$akta.no_Akta</a>
    #else
    <div align="left">$akta.no_Akta</div>
    #end
    </td>
    <td class="$row">$akta.nama_Akta</td>
    <td class="$row">$akta.tarikh_kuatkuasa</td>
    <td class="$row"><a href = "javascript:viewAktaBlob('$akta.id_Akta')">
    <img src="../img/pdf-small.png" alt="" border="0" /></a>
    
              <a alt="Hapus Akta" href = "javascript:deleteAktaPindaanByIdAkta('$akta.id_Akta')">
      &nbsp;&nbsp;<img border="0" src="../img/delete.gif" /></a> 
    
    </td>
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
		var editorInstance = FCKeditorAPI.GetInstance('txtCatatan_');   
	    var tajuk_Dokumen = editorInstance.GetHTML(true);
        var textlength = tajuk_Dokumen.length;                           
			
		var x = create_request_string(document.${formName});
		//alert(x);
		//return;
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan&command=simpan&mode=doUpdate&"+x+"&txtCatatan="+tajuk_Dokumen;
		document.${formName}.submit();
			
		//doAjaxCall${formName}("simpan","mode=doUpdate");
	}

function kemaskini() {
doAjaxCall${formName}("kemaskini");
}

function view_akta_pindaan(id) {
	document.${formName}.idAkta.value = id;
	//doAjaxCall${formName}("paparsenaraipindaan","");
	document.${formName}.command.value = "paparsenaraipindaan";
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan";	
	document.${formName}.submit();
}


function view_akta_pindaan_detail_(id) {
	document.${formName}.idAktaPindaan.value = id;
	doAjaxCall${formName}("paparsenaraipindaan_detail","");
}


	function tambahAktaPindaan() {
		//document.${formName}.idAkta.value = id;
		//doAjaxCall${formName}("tambahAktaPindaan","");
		document.${formName}.command.value = "tambahAktaPindaan";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan";	
		document.${formName}.submit();			
		
	}

function doGetSenaraiAktaPindaan_() {
	doAjaxCall${formName}("paparsenaraipindaan","");
}

function kembaliAktaPindaan() {
doAjaxCall${formName}("","");
}

function cari() {
doAjaxCall${formName}("cari","");
}

function paparAkta(idAkta) {
    var url = "../x/${securityToken}/ekptg.view.pdt.FrmViewAkta2?idAkta="+idAkta;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function deleteAktaPindaan(idAktaPindaan) {
    if ( !window.confirm("Akta Pindaan akan dihapuskan. Adakah Anda Pasti?") ) return;	
    document.${formName}.idAktaPindaan.value = idAktaPindaan;
    doAjaxCall${formName}("delete","idAktaPindaan="+idAktaPindaan);
}

function deleteAktaPindaanByIdAkta(idAkta) {
    if ( !window.confirm("Akta dan juga semua Akta Pindaan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
    document.${formName}.idAkta.value = idAkta;
    doAjaxCall${formName}("deleteAktaPindaanByIdAkta","idAkta="+idAkta);
}


function Kosong() {
	doAjaxCallFekptg_view_pdt_PendaftaranAktaPindaan("");
    document.Fekptg_view_pdt_PendaftaranAktaPindaan.reset();
    /* $jquery('#txtNoAkta').val('');
    $jquery('#txtNamaAkta').val('');
    $jquery('#txtNamaAktaPindaan1').val('');
	document.Fekptg_view_pdt_PendaftaranAktaPindaan.txtTarikhKuatkuasa1.value = "";
	document.Fekptg_view_pdt_PendaftaranAktaPindaan.tag_dokumen.value = ""; */
}



    </script>
