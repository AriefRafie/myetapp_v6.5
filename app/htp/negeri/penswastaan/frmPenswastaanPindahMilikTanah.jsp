<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>

<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>  
  <input name="actionPenswastaan" type="hidden" id="actionPenswastaan" value="$actionPenswastaan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idHakmilikurusan" type="hidden" id="idHakmilikurusan" value="$idHakmilikurusan"/>
  <input name="idPihakKepentingan" type="hidden" id="idPihakKepentingan" value="$idPihakKepentingan" />
</p>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
#if ($idFail != '')

	<tr>
    	<td>
    		#parse("app/htp/frmPenswastaan2Header.jsp")
    	</td>
  	</tr>  
 	
	<tr>
    	<td>
			<div id="TabbedPanels1" class="TabbedPanels">          
			              <ul class="TabbedPanelsTabGroup"> 
			                   <li class="TabbedPanelsTab" title="Urusan Pindah Milik" tabindex="0" 
			                   	onclick="javascript:setSelected(0,'pindahMilik','ptambahanview',0)">
			                   		URUSAN PINDAH MILIK</li>
			                   <li class="TabbedPanelsTab" title="Maklumat Pindah Milik" tabindex="0" 
			                   	onclick="javascript:setSelected(1,'maklumatview','maklumatview',0)">
			                   		MAKLUMAT PINDAH MILIK</li>
      					</ul>              
				<div class="TabbedPanelsContentGroup">
		    
			                <div class="TabbedPanelsContent">
							
			          		#if($selectedTab == '0')	                	
			                    	#parse("app/htp/negeri/penswastaan/frmPindahMilik.jsp")
			               	#end
			
			                </div> 
		                
			                <div class="TabbedPanelsContent">					
			          		#if($selectedTab == '1')
			                    	#parse("app/htp/negeri/penswastaan/frmMaklumatPindahMilik.jsp")
			               	#end
			
			                </div> 
		                
			                              	
				</div>
		              
			</div> 
    
    	</td>
  	</tr>	
	
  
#else

	<tr>
    	<td>
    		<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
    	</td>
	</tr>
  	
#end
</table>


<script language="javascript" >
	
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

/*function daftarBaruPemilikLama(idHakmilikurusan){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "newPemilik";
	document.${formName}.idHakmilikurusan.value = idHakmilikurusan;
	doAjaxCall${formName}("");
}*/
// 20/07/2010
function daftarBaruPemilik(idHakmilikurusan){
	//alert(daftarBaruPemilik);
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "newPemilik";
	document.${formName}.idHakmilikurusan.value = idHakmilikurusan;
	doAjaxCall${formName}("maklumatview");
}

function doChangeNegeri(){
	doAjaxCall${formName}('doChangeNegeri');
}

function batalPemilik(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	//doAjaxCall${formName}("");
	doAjaxCall${formName}("maklumatview");
}

function SimpanPemilik(){
	
	if(document.${formName}.txtNama.value=="0" || document.${formName}.txtNama.value==""){
		alert('Sila Masukkan Maklumat Nama.');
  		document.${formName}.txtNama.focus(); 
		return; 	
	}
	if(document.${formName}.txtAlamat1.value=="0" || document.${formName}.txtAlamat1.value==""){
		alert('Sila Masukkan Maklumat Alamat.');
  		document.${formName}.txtAlamat1.focus(); 
		return; 	
	}
	if(document.${formName}.txtAlamat2.value=="0" || document.${formName}.txtAlamat2.value==""){
		alert('Sila Masukkan Maklumat Alamat .');
  		document.${formName}.txtAlamat2.focus(); 
		return; 	
	}
	if(document.${formName}.txtPoskod.value=="0" || document.${formName}.txtPoskod.value==""){
		alert('Sila Masukkan Maklumat Poskod.');
  		document.${formName}.txtPoskod.focus(); 
		return; 	
	}	
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih Bandar.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}

	document.${formName}.mode.value = "newPemilik";
	document.${formName}.hitButton.value = "savePemilik";
	document.${formName}.actionPenswastaan.value = "papar";
	//doAjaxCall${formName}("");
	doAjaxCall${formName}("maklumatview");
	
}

function paparPemilik(idPihakKepentingan){
	document.${formName}.idPihakKepentingan.value = idPihakKepentingan;
	document.${formName}.mode.value = "viewPemilik";
	document.${formName}.actionPenswastaan.value = "papar";
	//doAjaxCall${formName}("");
	doAjaxCall${formName}("maklumatview");

}

function hapusPemilik(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "hapusPemilik";
	//doAjaxCall${formName}("");
	doAjaxCall${formName}("maklumatview");
}

function kemaskiniPemilik(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "updatePemilik";
	//doAjaxCall${formName}("");
	doAjaxCall${formName}("maklumatview");

}

function SimpanUpdatePemilik(){
	
	if(document.${formName}.txtNama.value=="0" || document.${formName}.txtNama.value==""){
		alert('Sila Masukkan Maklumat Nama.');
  		document.${formName}.txtNama.focus(); 
		return; 	
	}
	if(document.${formName}.txtAlamat1.value=="0" || document.${formName}.txtAlamat1.value==""){
		alert('Sila Masukkan Maklumat Alamat.');
  		document.${formName}.txtAlamat1.focus(); 
		return; 	
	}
	if(document.${formName}.txtAlamat2.value=="0" || document.${formName}.txtAlamat2.value==""){
		alert('Sila Masukkan Maklumat Alamat .');
  		document.${formName}.txtAlamat2.focus(); 
		return; 	
	}
	if(document.${formName}.txtPoskod.value=="0" || document.${formName}.txtPoskod.value==""){
		alert('Sila Masukkan Maklumat Poskod.');
  		document.${formName}.txtPoskod.focus(); 
		return; 	
	}
	
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih Bandar.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}	
	document.${formName}.mode.value = "viewPemilik";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "saveUpdatePemilik";
	//doAjaxCall${formName}("");
	doAjaxCall${formName}("maklumatview");
	
}



function checkDate() {
	var today = new Date();	
	dari_bulan = document.${formName}.txdTarikhDaftar.value.substring(3,5);
	dari_hari = document.${formName}.txdTarikhDaftar.value.substring(0,2);
	dari_tahun = document.${formName}.txdTarikhDaftar.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var myDate = Date.parse(daritemp);
	
	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		//document.${formName}.txdTarikhDaftar.value= ""; 
  		document.${formName}.txdTarikhDaftar.focus(); 
 		return;
 	}

}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
    if (isNaN(content)) {
    	elmnt.value = RemoveNonNumeric(content);
        return;
   	}
}
//20/07/2010
function setSelected(tabId,command,mode,tabmode) {
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
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

function kemaskiniSemakMilik(){
	doAjaxCall${formName}("senaraisemakpmilikkemaskini");
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
