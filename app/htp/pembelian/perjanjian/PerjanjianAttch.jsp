<style type="text/css">
<!--
.w {
	color: #000;
}
.q {
	color: #000;
}
.r {
	color: #00C;
}
#fileupload {
	text-align: center;
}
-->
</style>
<fieldset><legend><strong>MUAT NAIK FAIL SOKONGAN</strong></legend>
<fieldset><legend><strong>FAIL SOKONGAN TERDAHULU</strong></legend>
	#if($hapus=='0')
  
  #else
  <div class="info">FAIL TELAH DIHAPUSKAN</div>
  #end
  	#if($berjaya=='1')
  <div class="success">BERJAYA MUAT NAIK</div>
  #else
  #end
  <table  width="100%" border="0">
  <tr class="table_header">
    <td>Bil</td>
    <td>Fail Perjanjian</td>
    <td>Jenis Fail</td>
    <td>&nbsp;</td>
  </tr>
    	#set ( $cnt = 0 )	
    #if ($uPerjanjian.size() > 0)
    #foreach ($senarai in $uPerjanjian)
    	#set ( $cnt = $cnt + 1 )
    	#set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
    	#set( $row = "row2" )
    #else
    	#set( $row = "row1" )
    #end
  <tr class="table_header">
    <td width="3%" class="$row"><span class="q">$!cnt.</span></td>
    <td width="97%" class="$row"><img src="../img/paper_clip.png" align="left" " /><a href="javascript:downloadPemilikAttch('$!senarai.getPerjanjianAttch().getIdDokumenPerjanjian()',' $!senarai.getPerjanjianAttch().getIdDokumenPerjanjianAttach()')" class="r">$!senarai.getPerjanjianAttch().getNamaFail()</a></td>
    <td width="97%" class="$row"><span class="w">$!senarai.getPerjanjianAttch().getMIMEType()</span></td>
    <td width="97%" class="$row"><input class="stylobutton" type="button" value="Hapus" onclick="hapusDokumenPerjanjian('$!senarai.getPerjanjianAttch().getIdDokumenPerjanjianAttach()')" /></td>
  </tr>
     #end
    #else
  <tr>
    <td colspan="6" class="row1">Tiada rekod.</td>
  </tr>
    #end
  <tr>
    <td colspan="4">&nbsp;</td>
    </tr>
  <tr>
    <td colspan="4"><fieldset>
      <legend><strong>MUAT NAIK</strong></legend><input id="fileupload" name="fileupload" type="file" size="40"/>
      <input type="button" class="stylobutton" name="cmdUploadAttch" value="Muat Naik" onclick="simpanPemilikAttch('$!htpPermohonan.permohonan.getIdPermohonan()','$!pindahMilik.getIdDokumenPerjanjian()')"/>
       <input type="button" class="stylobutton" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="setSelected(2,'senaraisemakview','senaraisemakview',0);" />
      	</fieldset>
      </td>
  </tr>
</table>
</fieldset>

<input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="idDokumenPerjanjian" value="$!pindahMilik.getIdDokumenPerjanjian()"/>
<input type="hidden" name="idHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>
<input type="hidden" name="idPerjanjian" value="$!htpPermohonan.getIdPerjanjian()" />

<script>
	function simpanPemilikAttch(idP,idD){
	/*doAjaxCall${formName}("simpanPemilik");*/
		if ( document.${formName}.fileupload.value == "" ) { 
		alert('Sila pilih dokumen lampiran terlebih dahulu.');
		document.${formName}.fileupload.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule&command=AttchPemilik&idDokumenPerjanjian="+idD+"&idPermohonan="+idP;//
	
	document.${formName}.method="POST";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();	
	
	}
	function setSelected(tabId,command,mode,tabmode) {
		var idP = document.${formName}.idPermohonan.value;
		document.${formName}.action = "?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule&command=senaraisemakview&mode=senaraisemakview&tabId=0&tabmode=0&idPermohonan="+idP;//
	//document.${formName}.command = "AttchPemilik";setSelected(2,'senaraisemakview','senaraisemakview',0);
	document.${formName}.method="POST";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();	
}
	function downloadPemilikAttch(idDokumenPerjanjian,idDokumenPerjanjianAttach){
	var url = "../servlet/ekptg.view.htp.pembelian.DisplayBlobPerjanjianAttch?idDokumenPerjanjian="+idDokumenPerjanjian+"&idDokumenPerjanjianAttach="+idDokumenPerjanjianAttach;
   var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	}
		function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}

function findFail(){
	doAjaxCall${formName}("searchFail");
}
function setSelected(tabId,command,mode,tabmode) {
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
}

function tambahDraft(){
	doAjaxCall${formName}("addDraft");
}

function kembaliDraft(){
	doAjaxCall${formName}("kembaliDraft");
	
}
function papar(id,idhtp){
	doAjaxCall${formName}("detail",'idPermohonan='+id+'&idHtpPermohonan='+idhtp);
}
function simpanDraft(){
	if ( document.${formName}.txdTarikhTerima.value == "" ) { 
		alert('Sila masukkan Tarikh Terima terlebih dahulu.');
		document.${formName}.txdTarikhTerima.focus(); 
		return; 
	}
	doAjaxCall${formName}("simpanDraft");
}

function simpanPeguam(){
	if ( document.${formName}.txtNamaPeguam.value == "" ) { 
		alert('Sila masukkan Nama Peguam terlebih dahulu.');
		document.${formName}.txtNamaPeguam.focus(); 
		return; 
	}
	doAjaxCall${formName}("savePeguam");
}
function paparDraft(id){
	doAjaxCall${formName}("paparDraft","idDraf="+id);
}
function updateDraft(){
	if ( document.${formName}.txdTarikhTerima.value == "" ) { 
		alert('Sila masukkan Tarikh Terima terlebih dahulu.');
		document.${formName}.txdTarikhTerima.focus(); 
		return; 
	}
	doAjaxCall${formName}("updateDraft");
}
function deleteDraft(id){
	if ( !window.confirm("Adakah Anda Pasti?")) 
		return;
	doAjaxCall${formName}("deleteDraft","idDraf="+id);
	
}
function simpanPerjanjian(){
	if ( document.${formName}.txtTarikhPerjanjian.value == "" ) { 
		alert('Sila masukkan Tarikh Perjanjian terlebih dahulu.');
		document.${formName}.txtTarikhPerjanjian.focus(); 
		return; 
	}
	if(document.${formName}.txtbilUnitBangunan.value == ""){
		document.${formName}.txtbilUnitBangunan.value = 0;
	}
	doAjaxCall${formName}("simpanPerjanjian");
}
function kemaskiniPerjanjian(){

	doAjaxCall${formName}("kemaskiniPerjanjian");
}
function updatePerjanjian(){
	if ( document.${formName}.txtTarikhPerjanjian.value == "" ) { 
		alert('Sila masukkan Tarikh Perjanjian terlebih dahulu.');
		document.${formName}.txtTarikhPerjanjian.focus(); 
		return; 
	}
	if(document.${formName}.txtbilUnitBangunan.value == ""){
		document.${formName}.txtbilUnitBangunan.value = 0;
	}
	doAjaxCall${formName}("updatePerjanjian");
}
function updatePeguam(){
	if ( document.${formName}.txtNamaPeguam.value == "" ) { 
		alert('Sila masukkan Nama Peguam terlebih dahulu.');
		document.${formName}.txtNamaPeguam.focus(); 
		return; 
	}
	doAjaxCall${formName}("updatePeguam");
}
function kemaskiniPeguam(){
	doAjaxCall${formName}("kemaskiniPeguam");
}
function doChangeNegeriPeguam(){
	doAjaxCall${formName}("doChangeNegeriPeguam");
}

function validatePoskod(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function kemaskiniSemak(){
	doAjaxCall${formName}("kemaskiniSemak");
}
function simpanPindahMilik(){
	doAjaxCall${formName}("simpanPindahMilik");
}
function updatePindahMilik(){
	doAjaxCall${formName}("updatePindahMilik");
}
function kemaskiniPindahMilik(idPindahMilik){
	doAjaxCall${formName}("viewPindahMilik","idPindahMilik="+idPindahMilik);
}

// 2010/07/01
function kemaskiniSemakPerjanjian(){
	doAjaxCall${formName}("senaraisemakperjanjiankemaskini");
}

function kemaskiniSemakMilik(){
	doAjaxCall${formName}("senaraisemakpmilikkemaskini");
}
	// 2010/07/01 -Pilih Pegawai semakan
function openSenaraiSemak(urli,tajuk,idpermohonan,tem){
	var url = "../x/${securityToken}/ekptg.view.htp.pembelian.PerjanjianPembelianModule?command=senaraisemak&tajuk="+tajuk+"&idPermohonan="+idpermohonan;
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
//setSelected(tabId,command,mode,tabmode) {
//2,'senaraisemakview','senaraisemakview',0
//doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);

// 2010/07/02 -Pilih Pegawai untuk tandatangan surat
function openSuratPegawai(urli,param,laporan,tem){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function simpanPemilik(){
	doAjaxCall${formName}("simpanPemilik");
}
	
function updatePemilik(){
	doAjaxCall${formName}("updatePemilik");
}

//Kira char textarea
function textCounter(field, countfield, maxlimit) {
		if (field.value.length > maxlimit) // if too long...trim it!
			field.value = field.value.substring(0, maxlimit);
			// otherwise, update 'Baki Aksara' counter
		else 
			countfield.value = maxlimit - field.value.length;
}
	
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	
}

//semakan Tarikh semasa
function checkDate(inputfield) {
	var today = new Date();
	
	dari_bulan = inputfield.value.substring(3,5);
	dari_hari = inputfield.value.substring(0,2);
	dari_tahun = inputfield.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var myDate = Date.parse(daritemp);
	

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		inputfield.value="";
  		inputfield.focus();
 		return;
 	}

}

//paging number

function step4(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.RundinganPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}

function step3(idPermohonan){
	//doAjaxCall${formName}('perakuanPembelian','&idPermohonan='+idPermohonan);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=perakuanPembelian&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step2(idPermohonan){
	//doAjaxCall${formName}('maklumatTanah','&idPermohonan='+idPermohonan);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=maklumatTanah&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step1(idPermohonan,idhtp){
	//doAjaxCall${formName}("detail",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
	function simpanPemilikAttch(idP,idD){
	/*doAjaxCall${formName}("simpanPemilik");*/
		if ( document.${formName}.fileupload.value == "" ) { 
		alert('Sila pilih dokumen lampiran terlebih dahulu.');
		document.${formName}.fileupload.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule&command=AttchPemilik&idDokumenPerjanjian="+idD+"&idPermohonan="+idP;//
	//document.${formName}.command = "AttchPemilik";
	document.${formName}.method="POST";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();	
	
	}
	function downloadPemilikAttch(idDokumenPerjanjian,idDokumenPerjanjianAttach){
	var url = "../servlet/ekptg.view.htp.pembelian.DisplayBlobPerjanjianAttch?idDokumenPerjanjian="+idDokumenPerjanjian+"&idDokumenPerjanjianAttach="+idDokumenPerjanjianAttach;
   var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	}

function hapusDokumenPerjanjian(id){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doAjaxCall${formName}("hapusDokumenPerjanjian","idDokumenPerjanjianAttach="+id);
	}
	
function tambahAttachFail(idP){	
	doAjaxCall${formName}('tambahAttachFail','idPermohonan='+idP);
	//document.${formName}.action = "?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule?command=tambahAttachFail&idPermohonan="+idP;
	//document.${formName}.submit();	
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
	
	function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}

function senaraiDokumenSurat(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

// 2010/07/02 -Pilih Pegawai untuk tandatangan surat
function openSuratPegawai(urli,param,laporan,tem){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
</script>
