<!--
.pautancalendar {
	font-size: x-small;
	font-style: italic;
	color: #0000FF;
}
-->
<table width="100%" border="0" cellspacing="2" cellpadding="2">
		
	<tr>
		<td>
#parse('app/htp/pembelian/fail/paging.jsp')

		</td>
    </tr>
	<tr>
		<td>
			<fieldset><legend><strong>MAKLUMAT PERMOHONAN</strong> </legend>	
				#parse ("app/htp/pembelian/fail/fileInfo.jsp")			
			</fieldset>
		</td>
    </tr>		
		
    <tr>
		<td>
        	<div id="TabbedPanels1" class="TabbedPanels">          
	              <ul class="TabbedPanelsTabGroup"> 
	                   <li class="TabbedPanelsTab" title="Draf Pembelian" tabindex="0" onclick="javascript:setSelected(0,'kembaliDraft','drafview',0)"><strong><font size="1">DRAF PERJANJIAN</font></strong></li>
	                   <li class="TabbedPanelsTab" title="Maklumat Perjanjian" tabindex="0" onclick="javascript:setSelected(1,'pembelianview','pembelianview',0)"><strong><font size="1">MAKLUMAT PERJANJIAN</font></strong></li>
	                   <li class="TabbedPanelsTab" title="Perjanjian" tabindex="0" onclick="javascript:setSelected(2,'senaraisemakview','senaraisemakview',0)"><strong><font size="1">PERJANJIAN</font></strong></li> 	 
	              	   <li class="TabbedPanelsTab" title="Urusan Pindah Milik" tabindex="0" onclick="javascript:setSelected(3,'pindahMilik','ptambahanview',0)"><strong><font size="1">URUSAN PINDAH MILIK</font></strong></li>
	               	   <li class="TabbedPanelsTab" title="Maklumat Peguam" tabindex="0" onclick="javascript:setSelected(4,'Peguam','peguamview',0)"><strong><font size="1">MAKLUMAT PEGUAM</font></strong></li>
	              </ul>              
				<div class="TabbedPanelsContentGroup">
    
	                <div class="TabbedPanelsContent">
					
	          		#if($selectedTab == '0')	                	
	                    	#parse("app/htp/pembelian/perjanjian/draftPerjanjian.jsp")
	               	#end
	
	                </div> 
                
	                <div class="TabbedPanelsContent">					
	          		#if($selectedTab == '1')
	                    	#parse("app/htp/pembelian/perjanjian/perjanjianPembelian.jsp")
	               	#end
	
	                </div> 
                
	                <div class="TabbedPanelsContent">			
	          		#if($selectedTab == '2')
	                    	#parse("app/htp/pembelian/perjanjian/perjanjianTandatangan.jsp")
	               	#end
	
	                </div> 
                 
	                 <div class="TabbedPanelsContent">
					 <!-- Perjanjian tambahan -->
	          		#if($selectedTab == '3')
	                    	#parse("app/htp/pembelian/perjanjian/pindahMilik.jsp")
	               	#end
	
	                </div> <!-- close content perjanjian pembelian -->
                              
                  	<div class="TabbedPanelsContent">
					 <!-- Perjanjian tambahan -->
	          		#if($selectedTab == '4')
	                    	#parse("app/htp/pembelian/perjanjian/peguam.jsp")
	               	#end

                	</div>                
              	
              	</div>
              
        	</div>
        	
    	</td>
	</tr>

</table>

<input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="idHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>
<input type="hidden" name="idPerjanjian" value="$!htpPermohonan.getIdPerjanjian()" />
<input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>

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
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule?command=tambahAttachFail&idPermohonan="+idP;
	//document.${formName}.submit();	
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
	function simpanPerjanjian_(){
		doAjaxCall${formName}("simpanPerjanjian");
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
	
function kemaskiniPerjanjian(){
	doAjaxCall${formName}("kemaskiniPerjanjian");
}
function updatePerjanjian(){
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
	}}
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
</script>
