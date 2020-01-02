

<style type="text/css">
<!--
.mandatory {color: #FF0000}
-->
</style>

<!--<input type="text" id="mode" name="mode" value="$mode" />-->

<input name="idAkta" type="hidden" id="idAkta" value="$idAkta"/>
<input name="idAktaPindaan" type="hidden" id="idAktaPindaan" value="$!idAktaPindaan"/>
<input name="tabId" type="hidden" id="tabId" value="$tabId"/>
<input name="hitbutton" type="hidden" value="$hitbutton" />
<input name="noAktaAsal" type="hidden" value="$noAkta" />
<fieldset>
          <legend><strong>Maklumat Akta</strong></legend>
          <table width="100%">
          <tr>
            <td width="29%" scope="row">No. Akta Asal</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>$noAkta</label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta Asal</td>
            <td scope="row">:</td>
            <td>$namaAkta</td>
          </tr>
          <tr>
            <td scope="row">No. Fail</td>
            <td scope="row">:</td>
            <td>$noFail</td>
          </tr>
        </table>
        </fieldset>
        
        <p>
        
        
        
 <div id="mydiv">
        <fieldset>
        <!--<input type="text" id="action" name="action" value="$action" />-->
        <legend><strong>Maklumat Akta Pindaan</strong></legend>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="29%" scope="row"><span class="mandatory">*</span>No. Akta Pindaan</td>
    <td width="1%" scope="row">:</td>
    <td width="70%"><label>
      <input value="$!txtNoAktaPindaan" name="txtNoAktaPindaan" 
      type="text" id="txtNoAktaPindaan" 
      onblur="this.value=this.value.toUpperCase()" $readonly/>
    </label></td>
  </tr>
  <tr>
    <td scope="row"><span class="mandatory">*</span>Nama Akta Pindaan</td>
    <td scope="row">:</td>
    <td><label>
      <input name="txtNamaAktaPindaan" type="text" value="$!txtNamaAktaPindaan" size="45" maxlength="100" />
    </label></td>
  </tr>
  <tr>
    <td scope="row">Tarikh Kuatkuasa Pindaan</td>
    <td scope="row">:</td>
    <td><label>
      <input name="txtTarikhKuatkuasa" type="text" id="txtTarikhKuatkuasa" value="$txtTarikhKuatkuasa" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
      <a href="javascript:displayDatePicker('txtTarikhKuatkuasa',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </label></td>
  </tr>
  <tr>
    <td scope="row">No. Warta</td>
    <td scope="row">:</td>
    <td><label>
      <input name="txtNoWarta" type="text" id="txtNoWarta" value="$txtNoWarta" />
    </label></td>
  </tr>
  <tr>
    <td scope="row">Tarikh Warta</td>
    <td scope="row">:</td>
    <td><label>
      <input name="txtTarikhWarta" type="text" id="txtTarikhWarta" value="$!txtTarikhWarta" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
      <a href="javascript:displayDatePicker('txtTarikhWarta',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </label></td>
  </tr>
  <tr>
    <td scope="row">Tarikh Perkenaan Di Raja</td>
    <td scope="row">:</td>
    <td><label>
      <input name="txtTarikhPerkenaanDiraja" type="text" id="txtTarikhPerkenaanDiraja" value="$!txtTarikhPerkenaanDiraja" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
      <a href="javascript:displayDatePicker('txtTarikhPerkenaanDiraja',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </label></td>
  </tr>
  <tr>
    <td scope="row">Tarikh Penyiaran Dalam Warta</td>
    <td scope="row">:</td>
    <td><label>
      <input name="txtTarikhPenyiaranDlmWarta" type="text" id="txtTarikhPenyiaranDlmWarta" value="$!txtTarikhPenyiaranDlmWarta" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
      <a href="javascript:displayDatePicker('txtTarikhPenyiaranDlmWarta',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </label></td>
  </tr>
  
  <tr>
    <td height="20" scope="row">No. Fail</td>
    <td scope="row">:</td>
    <td><label>
        <input name="txtNoFail" type="text" id="txtNoFail" value="$!txtNoFail" size="45" maxlength="45" />
    </label></td>
  </tr>
  <tr>
    <td scope="row">No. Memorandum Jemaah Menteri</td>
    <td scope="row">:</td>
    <td><label>
      <input name="txtNoMemorandumMenteri" type="text" id="txtNoMemorandumMenteri" value="$!txtNoMemorandumMenteri" />
    </label></td>
  </tr>
  <tr>
    <td scope="row">Keputusan Jemaah Menteri</td>
    <td scope="row">:</td>
    <td><label>
      <input name="txtKptsnJemaahMenteri" type="text" id="txtKptsnJemaahMenteri" value="$!txtKptsnJemaahMenteri" />
    </label></td>
  </tr>
  <tr>
    <td scope="row">Keputusan Majlis Tanah Negara</td>
    <td scope="row">:</td>
    <td><label>
      <input name="txtKptsnMajlisTanah" type="text" id="txtKptsnMajlisTanah" value="$!txtKptsnMajlisTanah" />
    </label></td>
  </tr>
  <tr>
    <td scope="row" valign="top">Catatan</td>
    <td scope="row" valign="top">:</td>
    <td><label>
      <input name="XtxtCatatan" type="hidden" value="$!txtCatatan" />
	#if ($action == 'view')
		$!txtCatatan
	#else	
	      <textarea name="txtCatatan_" cols="41" onkeyup="this.value=this.value.toUpperCase();" $readOnly>$!txtCatatan</textarea>
	 	<script> 
	 		
			var area1 = new FCKeditor("txtCatatan_");
			area1.ToolbarSet = 'PFD';
			area1.BasePath = '/${appname}/library/fck/' ;
			area1.Height = 130;
			area1.Width = 530;
			area1.ReplaceTextarea();    
			area1.uiColor = '#000';
			         	
		</script>
	#end      
    </label></td>
  </tr>
  <tr>
    <td scope="row">Lampiran</td>
    <td scope="row">:</td>
    <td><label>
    <input id="txfMuatNaikDokumen" name="txfMuatNaikDokumen" type="file" size="40" />
    </label></td>
  </tr>
 
 	<tr>
            <td scope="row" valign="top"><i>Tag</i> Dokumen</td>
            <td scope="row" valign="top">:</td> 
            <td scope="row" valign="top">
             	<textarea name="tag_dokumen" cols="82" rows="5" onblur="this.value=this.value.toUpperCase()">$!tag_Dokumen</textarea>
            	<input name="id_tagdokumen" type="hidden" value="$!id_tagdokumen"/>
            </td>
	</tr>
  
  <tr>
    <td scope="row">Tarikh Daftar</td>
    <td scope="row">:</td>
    <td><label>
      <input name="txtTarikhDaftar" type="text" id="txtTarikhDaftar" value="$!txtTarikhDaftar" size="11" maxlength="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
      <a href="javascript:displayDatePicker('txtTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </label></td>
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
 	#if ($action == 'view')
 		<button type="button" value="Kemaskini" onclick="kemaskini()"><font size = "1">Kemaskini</font></button>
	#elseif ($action == 'doUpdate')
		<input type="button" value="Simpan" onclick="doUpdate()"/>

	#elseif ($action == 'update')
		<button type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSave()"><font size = "1">Simpan</font></button>
	#else

	#end 
		<button type="submit" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doGetSenaraiAktaPindaan()"><font size = "1">Kembali</font></button></td>
  </tr>
</table>
</fieldset>
</div>
<script type="text/javascript" src="../app/pdt/akta.js"></script>


<!--#if($action=="view")-->
<script>

 $jquery(document).ready(function () {
			  getDisableFieldDiv('mydiv','$action');  
		  });
</script>
<!--#end-->


<script>

	function kemaskini() {
		//doAjaxCall${formName}("kemaskini");
		document.${formName}.command.value = "kemaskini";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan";	
		document.${formName}.submit();	
	
	}
	
	function doGetSenaraiAktaPindaan() {
		//doAjaxCall${formName}("paparsenaraipindaan","");
		document.${formName}.command.value = "paparsenaraipindaan";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan";	
		document.${formName}.submit();
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
		var editorInstance = FCKeditorAPI.GetInstance('txtCatatan_');   
	    var tajuk_Dokumen = editorInstance.GetHTML(true);
        var textlength = tajuk_Dokumen.length;                           
			
		var x = create_request_string(document.${formName});
		//var x = "0";
		//alert(x);
		//return;
		document.${formName}.method="post";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan&command=simpan&"+x+"&txtCatatan="+tajuk_Dokumen;
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
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
		
		var editorInstance = FCKeditorAPI.GetInstance('txtCatatan_');   
	    var tajuk_Dokumen = editorInstance.GetHTML(true);
        var textlength = tajuk_Dokumen.length;                           
		/*
		if(textlength==0){
	   		alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
	        oEditor.EditorDocument.body.focus();
	        return;
	   	} */	
		var x = create_request_string(document.${formName});
		//var x = "0";
		//return;
		//document.${formName}.method="post";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan&command=simpan&mode=doUpdate&"+x+"&txtCatatan="+tajuk_Dokumen;
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();
			
			//doAjaxCall${formName}("simpan","mode=doUpdate");
	}
	
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


function view_akta_pindaan(id) {
	document.${formName}.idAkta.value = id;
	doAjaxCall${formName}("paparsenaraipindaan","");
}


	function view_akta_pindaan_detail(id) {
		document.${formName}.idAktaPindaan.value = id;
		//doAjaxCall${formName}("paparsenaraipindaan_detail","");
		document.${formName}.command.value = "paparsenaraipindaan_detail";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan";	
		document.${formName}.submit();
		
	}


function tambahAktaPindaan() {
	//document.${formName}.idAkta.value = id;
	doAjaxCall${formName}("tambahAktaPindaan","");
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

	function doUpdate_() {
		if (document.Fekptg_view_pdt_PendaftaranAktaPindaan.txtNoAktaPindaan.value == "") {
			alert("Sila Masukkan No Akta Pindaan");
			document.Fekptg_view_pdt_PendaftaranAktaPindaan.txtNoAktaPindaan.focus();
			return;
		}
		if (document.Fekptg_view_pdt_PendaftaranAktaPindaan.txtNamaAktaPindaan.value == "") {
			alert("Sila Masukkan Nama Akta Pindaan");
			document.Fekptg_view_pdt_PendaftaranAktaPindaan.txtNamaAktaPindaan.focus();
			return;
		}
		
		var editorInstance = FCKeditorAPI.GetInstance('txtCatatan_');   
	    var tajuk_Dokumen = editorInstance.GetHTML(true);
        var textlength = tajuk_Dokumen.length;                           
		if(textlength==0){
	   		alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
	        oEditor.EditorDocument.body.focus();
	        return;
	   	}	
		alert("."+tajuk_Dokumen);
		alert("."+document.Fekptg_view_pdt_PendaftaranAktaPindaan.txtCatatan.value);
	}
	
</script>
<script>
//alert("ssssss");
//alert("sssssss"+'$current_role');
/*
$jquery(document).ready(function () {

	//if('$current_role'=="(PDT)HQPengguna" || '$current_role'=="(PDT) Pengguna Bahagian Strata")
	if('$current_role'=="(PDT)HQPengguna" || '$current_role'=="(PDT) Pengguna Bahagian Strata" || '$current_role'=="(PDT) Pengguna Bahagian Pengambilan Tanah" || '$current_role'=="(PDT) Pengguna Bahagian Penguatkuasa dan Hasil Persekutuan" || '$current_role'=="(PDT) Pengguna Bahagian Pembahagian Pusaka" )
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		//$jquery("input[type=button]").hide();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		$jquery("input[type=button]").show();
	}
	else
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		//$jquery("input[type=button]").show();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		$jquery("input[type=button]").hide();
	}
});
*/
</script>

