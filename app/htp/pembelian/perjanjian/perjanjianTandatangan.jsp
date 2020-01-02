<style type="text/css">
<!--
	.pautanms{color: #0000FF}
.q {
	color: #00C;
}
.w {
	color: #000;
}
-->
</style>
<table width="100%" border="0">
  <tr>
		<td colspan="2">
			<fieldset>
			<legend><strong>PERJANJIAN</strong> </legend>
			
			<table width="100%">
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Terima daripada KJP</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
                            <input type="text" name="tarikhTerimaKJP" value="$!pindahMilik.getTarikhTerima()" $mode $classDis onblur="check_date(this);checkDate(document.${formName}.tarikhTerimaKJP);">
							<img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('tarikhTerimaKJP',false,'dmy');" />
							<!--<a href="javascript:openSenaraiSemak('txdTarikhTerima','PERJANJIAN PEMBELIAN','$!htpPermohonan.permohonan.getIdPermohonan()');">	<span class="pautanms">Semakan</span></a>-->
							<a href="javascript:onclick=setSelected(2,'senaraisemakperjanjian','senaraisemakperjanjian',0);">	<span class="pautanms">Semakan</span></a>
                        </td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Tandatangan PTP</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
                        		<input type="text" name="tarikhTandatanganPTP" value="$!pindahMilik.getTarikhTandatangan()" $mode $classDis onblur="check_date(this);checkDate(document.${formName}.tarikhTandatanganPTP);">
		 						<img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('tarikhTandatanganPTP',false,'dmy');" />
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Hantar ke KJP</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
                        	<input type="text" name="tarikhHantarKJP" value="$!pindahMilik.tarikhHantar" $mode $classDis onblur="check_date(this);checkDate(document.${formName}.tarikhHantarKJP);">
							<img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('tarikhHantarKJP',false,'dmy');" />
						</td>
					</tr>
			</table>
			</fieldset>
		</td>
	</tr>

	<tr>
    
		    	<td align="center" colspan="2">
                #if($semakMode == "new")

	    		 	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanPemilik()" />
                   	<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />
	    		#elseif($semakMode == "update")
    		 		<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:updatePemilik()" />
                   	<!--<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" /> -->
             		<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Batal" onClick="setSelected(2,'senaraisemakview','senaraisemakview',0)">
		   		#else		    		
		    		<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="kemaskiniSemak()" />
                	<!--<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />-->
		    	#end
		    		
		    	
		    	</td>
		    
	 </tr>

</table>

<fieldset><legend><strong>FAIL SOKONGAN</strong></legend>
  #if($hapus=='1')
  <div class="success">FAIL TELAH DIHAPUSKAN</div>
  #end
  <table  width="100%" border="0">
  <tr class="table_header">
    <td colspan="4">
    #if($buttonPerjanjian=='ada')
    <input class="stylobutton" type="button" value="Tambah Lampiran" onclick="tambahAttachFail('$!htpPermohonan.permohonan.getIdPermohonan()')" />
    #else
    #end
    </td>
    </tr>
  <tr class="table_header">
    <td width="3%">Bil.</td>
    <td width="60%">Fail Perjanjian</td>
    <td width="27%">Jenis Fail</td>
    <td width="10%">&nbsp;</td>
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
    <td width="3%" class="$row"><span class="w">$!cnt.</span></td>
    <td width="59%" class="$row"><img src="../img/paper_clip.png" align="left" " /><a href="javascript:downloadPemilikAttch('$!senarai.getPerjanjianAttch().getIdDokumenPerjanjian()',' $!senarai.getPerjanjianAttch().getIdDokumenPerjanjianAttach()')" class="q">$!senarai.getPerjanjianAttch().getNamaFail()</a></td>
    <td width="28%" class="$row"><span class="w">$!senarai.getPerjanjianAttch().getMIMEType()</span></td>
    <td width="10%" class="$row"><input type="button"class="stylobutton"  value="Hapus" onclick="hapusDokumenPerjanjian('$!senarai.getPerjanjianAttch().getIdDokumenPerjanjianAttach()')" /></td>
  </tr>
     #end
    #else
  <tr>
    <td colspan="6" class="row1">Tiada rekod.</td>
  </tr>
    #end
  <tr>
    <td colspan="4">
      </td>
  </tr>
</table>
</fieldset>
<input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="idDokumenPerjanjian" value="$!pindahMilik.getIdDokumenPerjanjian()"/>

<script type="text/javascript">
	var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
	
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
	doAjaxCall${formName}("simpanDraft");
}

function simpanPeguam(){
	doAjaxCall${formName}("savePeguam");
}
function paparDraft(id){
	doAjaxCall${formName}("paparDraft","idDraf="+id);
}
function updateDraft(){
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

function simpanPemilik(){
	doAjaxCall${formName}("simpanPemilik");
/*
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule?command=simpanPemilik";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();	*/
	
	}
	
	function updatePemilik(){
	doAjaxCall${formName}("updatePemilik");
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
}

// melalui page number	
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

function simpanPemilik(idP,idH){
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule";
	/* &idPermohonan="+id+"&txtKeteranganDraf="+document.${formName}.txtKeteranganDraf.value+"&txdTarikhTerimaDraf="+document.${formName}.txdTarikhTerimaDraf.value+"&txdTarikhHantarDraf="+document.${formName}.txdTarikhHantarDraf.value+"&idPermohonan="+document.${formName}.txtidPermohonan.value; */
	document.${formName}.method="POST";
	document.${formName}.command.value="simpanSemak";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
/*	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "sDraf";*/
	document.${formName}.submit();	
	//doAjaxCall${formName}("");
	
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
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

</script>