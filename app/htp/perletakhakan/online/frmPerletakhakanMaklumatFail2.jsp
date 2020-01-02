<!-- frmPPerletakhakanMaklumatFail.jsp -->
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

<style type="text/css">
<!--
.pautanms {color: #0033FF}
-->
</style>
#set($hide='style="display:none"')
<p>
<input name="idPermohonan" type="hidden" value="$idPermohonan" id="idPermohonan" /> 
<input name="idFail" type="hidden" value="$idFail" id="idFail" /> 
<input name="idHtpPermohonan" type="hidden" value="$idHtpPermohonan" id="idHtpPermohonan" />
<input name="idSuburusanStatusFail" type="hidden" value="$idSuburusanStatusFail" id="idSuburusanStatusFail" />
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>
<table width="100%">
	<tr>
		<td>
			#parse ("app/htp/perletakhakan/paging2.jsp")
		</td>
	</tr>
	<tr>
		<td>
			#parse("app/htp/perletakhakan/online/frmPerletakhakanInfoOnline.jsp") 
		</td>
	</tr>
	<tr>
    <td align=left><fieldset>
    <legend>SENARAI SEMAK</legend>
    <table width="100%" border="0" align="center">
    
		#foreach ( $semak in $senaraiSemakan )
			#set( $i = $velocityCount )
		#if ( ($i % 2) == 0 )
			#set( $row = "row2" )
		#else
			#set( $row = "row1" )
		#end

      <tr>
      <td width="30%" >
      	#if ( $semakclass.isSemakan("$idPermohonan", "$semak.id" ))
			#set ( $checked = "checked" )
		#else
			#set ( $checked = "" )
		#end

        <input type="checkbox" name="sbcSemakan" id="sbcSemakan" value="$semak.id" $checked $inputTextClass class="$inputTextClass" />
	
    	$semak.keterangan</td>
      </tr>
      #end
    </table>
    </fieldset>
    </td>
  </tr>
</table>
<script>
	//frmPerletakhakanMaklumatFail2
function edit(idA,idB,idC,idE){
if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeri.focus();
		return;
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih "Kementerian" terlebih dahulu.');
		document.${formName}.socKementerian.focus();
		return;
	}
		if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih "Agensi" terlebih dahulu.');
		document.${formName}.socAgensi.focus();
		return;
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih "Urusan" terlebih dahulu.');
		document.${formName}.socSuburusan.focus();
		return;
	}
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila pilih "Tujuan" terlebih dahulu.');
		document.${formName}.txtTajuk.focus();
		return;
	}
	if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila pilih "No Fail KJP" terlebih dahulu.');
		document.${formName}.txtNoFailKJP.focus();
		return;
	}
		if(document.${formName}.txdTarikhSurKJP.value == ""){
		alert('Sila pilih "Tarikh Surat KJP" terlebih dahulu.');
		document.${formName}.txdTarikhSurKJP.focus();
		return;
	}
	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=edit&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
	document.${formName}.submit();
	
}
function sahkan(idFail,idPermohohan){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=sahkan&idPermohonan="+idPermohohan+"&idFail="+idFail;
	document.${formName}.submit();
	
}
function batal(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=papar";
	document.${formName}.submit();
}
function simpan(){
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeri.focus();
		return;
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih "Kementerian" terlebih dahulu.');
		document.${formName}.socKementerian.focus();
		return;
	}
		if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih "Agensi" terlebih dahulu.');
		document.${formName}.socAgensi.focus();
		return;
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih "Urusan" terlebih dahulu.');
		document.${formName}.socSuburusan.focus();
		return;
	}
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila pilih "Tujuan" terlebih dahulu.');
		document.${formName}.txtTajuk.focus();
		return;
	}
	if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila pilih "No Fail KJP" terlebih dahulu.');
		document.${formName}.txtNoFailKJP.focus();
		return;
	}
		if(document.${formName}.txdTarikhSurKJP.value == ""){
		alert('Sila pilih "Tarikh Surat KJP" terlebih dahulu.');
		document.${formName}.txdTarikhSurKJP.focus();
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=simpanBaru";
	document.${formName}.submit();
}
function kemaskini(idA,idB,idC,idE) {
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=kemaskini&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
	document.${formName}.submit();
}
function kembali() {	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=";
	document.${formName}.submit();
}

function doChangeNegeri(){
	doAjaxCall${formName}("doChangeNegeri","actionPerletakhakan=tambah");
}
function doChangeNegeriEdit(){
	doAjaxCall${formName}("doChangeNegeri","actionPerletakhakan=kemaskini");
}
function doChangeKementerian(){
	doAjaxCall${formName}("doChangeKementerian","actionPerletakhakan=tambah");
}
function doChangeKementerianEdit(){
	doAjaxCall${formName}("doChangeKementerian","actionPerletakhakan=kemaskini");
}

// 01/06/2010 -
function senaraiDokumenSurat(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

//2010/04/09 bertujuan mencetak doket atau Fail
// Dibuat oleh  : Rosli
// Dimodifikasi oleh :
function cetakFailDoket(id,temp,urlserv) {
	var param ="";
    var url = "../servlet/"+urlserv+"?"+id+temp;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function maklumatHakmilik(id){
	//document.${formName}.actionPerletakhakan.value = "papar";
	//document.${formName}.idFail.value = id;
	//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=maklumathakmilik&idFail="+id;
	//document.${formName}.submit();
	doAjaxCall${formName}("","actionPerletakhakan=maklumathakmilik&idFail="+id);
}
function paparHakmilik(id){	
	document.${formName}.actionPerletakhakan.value = "maklumathakmilik";
	document.${formName}.mode.value ="viewHakmilik";
	document.${formName}.idHakmilikurusan.value = id;
	doAjaxCall${formName}(""); 
}
	//Skrin Senarai Hakmilik, Paging 1 
	function senaraiFail(id){	
		document.${formName}.actionPerletakhakan.value = "";
		//document.${formName}.mode.value ="viewHakmilik";
		//document.${formName}.idHakmilikurusan.value = id;
		doAjaxCall${formName}(""); 
	}
	//Skrin Senarai Fail, selepas Senarai Hakmilik (Pautan No fail) 
	function viewFailById(idA,idB,idC,idE){
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPerletakhakanPendaftaranOnline&actionPerletakhakan=papar&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
		document.${formName}.submit();
	}

</script>
