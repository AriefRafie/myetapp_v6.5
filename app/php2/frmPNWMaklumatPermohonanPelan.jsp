<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>

#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Fail berjaya dimuatnaik.</div>";
</script>
#end
#if ($limitExceed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Saiz fail yang dimuatnaik lebih besar daripada yang dibenarkan.</div>";
</script>
#end

<input name="idStatus" type="hidden" id="idStatus"/>
<!-- <input name="idDokumen" type="text" id="idDokumen" value="$idDokumen"/> -->
<input name="idDokumen" type="hidden" id="idDokumen" value="$BeanMaklumatImejan.idDokumen"/>
<input name="peganganHakmilik" type="hidden" id="peganganHakmilik" value="$!peganganHakmilik"/>
<input name="idHakmilikPelan" type="hidden" id="idHakmilikPelan" value="$!idHakmilikPelan"/>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupDokumen')
  <tr>
    <td>
		<table width="100%" border="0" cellspacing="2" cellpadding="2">
		  <tr>
		    <td ><fieldset>
		      <legend>MAKLUMAT IMEJ</legend>
		      <table width="100%" border="0" cellspacing="2" cellpadding="2">
		        #if ($BeanMaklumatImejan.idDokumen != 'none')
		        <tr>
		          <td width="1%">&nbsp;</td>
		          <td width="28%">&nbsp;</td>
		          <td width="1%">&nbsp;</td>
		          <td width="70%"><img src="../servlet/ekptg.view.php2.FrmDisplayImage?id=$BeanMaklumatImejan.idDokumen" alt="Imej Pelan" border="1" width="250" height="250" onclick="cetakImej($BeanMaklumatImejan.idDokumen)"/></td>
		        </tr>
		        <tr>
		          <td width="1%">&nbsp;</td>
		          <td width="28%">&nbsp;</td>
		          <td width="1%">&nbsp;</td>
		          <td width="70%">&nbsp;</td>
		        </tr>
		        #end
		        <tr>
		          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
		          <td width="28%">Nama Imej</td>
		          <td width="1%">:</td>
		          <td width="70%"><input name="txtNamaImej" type="text" id="txtNamaImej" value="$peganganHakmilik" size="43" maxlength="100" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();"></td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td valign="top">Catatan</td>
		          <td valign="top">:</td>
		          <td valign="top"><textarea name="txtCatatanImej" cols="50" rows="5" class="$inputTextClassPopup" id="txtCatatanImej" $readonlyPopup>$!BeanMaklumatImejan.catatanImej</textarea></td>
		        </tr>
		        #if ($modePopup != 'view')
		        <tr>
		          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
		          <td>Direktori Imej</td>
		          <td>:</td>
		          <td><input id="fileupload" name="fileupload" type="file" size="40" $readonlyPopup2  class="$inputTextClassPopup" /></td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		          <td><span class="style4"><i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik imej adalah tidak melebihi 500MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz imej yang lebih kecil.</span></span></td>
		        </tr>
		        #end
		        #if ($modePopup != 'view')
		        <tr>
		          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
		        </tr>
		        #end
		        <tr>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		          <td> 
		          	#if ($modePopup == 'new')
			            <input name="cmdSimpan" type="button" value="Simpan" onClick="simpanDokumenP('$idPermohonan')" />
			            <input name="cmdBatal" type="button" value="Batal" onClick="batalDokumenP()" />
		            #end
		            #if ($modePopup == 'view')
			            <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniDokumenP()" />
			            <input name="cmdHapus" type="button" value="Hapus" onClick="hapusDokumenP('$BeanMaklumatImejan.idDokumen')" >
		            #end
		            #if ($modePopup == 'update')
			            <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskiniDokumenP('$BeanMaklumatImejan.idDokumen')" />
			            <input name="cmdBatal" type="button" value="Batal" onClick="batalKemaskiniDokumenP()" />
		            #end 
		          </td>
		        </tr>
		      </table>
		      </fieldset></td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		</table>    
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI IMEJ </strong></legend>
      <table align="center" width="100%">
        <!-- kmie, 20100812 (MacGDI) -->
<!--         <tr> -->
<!--           <td colspan="2" scope="row"><a href="http://g4nre.mygeoportal.gov.my" target="_blank" style="color:#0000FF">MacGDI</a></td> -->
<!--         </tr> -->
        <!-- end edit (kmie) -->
        <tr>
          <td colspan="2" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarDokumenP()"/></td>
        </tr>
      
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td><strong>Pegangan Hakmilik</strong></td>
        </tr>
        #set ($senaraiImejan = "")
        #if ($SenaraiImejan.size() > 0)
        #foreach ($senaraiImejan in $SenaraiImejan)
        #if ($senaraiImejan.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiImejan.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiImejan.bil</td>
          <td class="$row"><a href="javascript:paparDokumenP('$senaraiImejan.idHakmilik', '$senaraiImejan.peganganHakmilik')" class="style2">$senaraiImejan.peganganHakmilik</a></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>

<script>

function cetakImej(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
  
  function daftarDokumenP() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPNWMaklumatPermohonanView";
	document.${formName}.method="POST";
	document.${formName}.mode.value = "view";	
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}

function simpanDokumenP(idPermohonan) {
	
	if(document.${formName}.txtNamaImej.value == ""){
		alert('Sila masukkan Nama Imej.');
  		document.${formName}.txtNamaImej.focus(); 
		return; 
	}
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Imej yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

// 	alert(document.${formName}.idHakmilik.value);
	var idHakmilikPelan = document.${formName}.idHakmilik.value;
	var namaImej = document.${formName}.txtNamaImej.value;
 	var catatanImej = document.${formName}.txtCatatanImej.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPNWMaklumatPermohonanView&hitButton=simpanDokumen&txtNamaImej="+namaImej+"&txtCatatanImej="+catatanImej+"&selectedTabUpper=3"+dopost+"&mode=view&flagPopup=openPopupDokumen&modePopup=new&idPermohonan="+idPermohonan+"&idHakmilikPelan="+idHakmilikPelan;
// 	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPNWMaklumatPermohonanView&hitButton=simpanKemaskiniDokumen&txtNamaImej="+namaImej+"&txtCatatanImej="+catatanImej+"&selectedTabUpper=3"+dopost+"&mode=view&flagPopup=openPopupDokumen&modePopup=view&idPermohonan="+idPermohonan+"&idDokumen="+idDokumen+"&idHakmilikPelan="+idHakmilikPelan;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function batalDokumenP(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniDokumenP(){
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniDokumenP(idDokumen) {

// 	if(document.${formName}.txtNamaImej.value == ""){
// 		alert('Sila masukkan Nama Imej.');
//   		document.${formName}.txtNamaImej.focus(); 
// 		return; 
// 	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

// 	document.${formName}.idDokumen.value = idDokumen;
// 	document.${formName}.flagPopup.value = "openPopupDokumen";
// 	document.${formName}.modePopup.value = "view";
// 	document.${formName}.hitButton.value = "simpanKemaskiniDokumen";
// 	doAjaxCall${formName}("");
	
	var namaImej = document.${formName}.txtNamaImej.value;
 	var catatanImej = document.${formName}.txtCatatanImej.value ;
	var idHakmilikPelan = document.${formName}.idHakmilikPelan.value;
	var idPermohonan = document.${formName}.idPermohonan.value;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPNWMaklumatPermohonanView&hitButton=simpanKemaskiniDokumen&txtNamaImej="+namaImej+"&txtCatatanImej="+catatanImej+"&selectedTabUpper=3"+dopost+"&mode=view&flagPopup=openPopupDokumen&modePopup=view&idPermohonan="+idPermohonan+"&idDokumen="+idDokumen+"&idHakmilikPelan="+idHakmilikPelan;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function paparDokumenP(idHakmilik, peganganHakmilik){
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPNWMaklumatPermohonanView";
	document.${formName}.method="POST";
// 	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.idHakmilikPelan.value = idHakmilik;
	document.${formName}.peganganHakmilik.value = peganganHakmilik;
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}
function hapusDokumenP(idDokumen){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.hitButton.value = "hapusDokumen";
	document.${formName}.submit();
}

</script>
