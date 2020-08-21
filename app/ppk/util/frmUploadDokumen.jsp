<style type="text/css">
<!--

body {
text-align:center;
font-family:serif;
background:#FAF8CC;
}

.style1 {color: #0000FF}

.table_header {
color:#FFF;
background-color:#960;
font-weight:400;
border-style:10px solid #FFF;
}

.module_content,td.row1,.row1 {
background-color:#FAF8CC;
}

.table_row2,.module_content_bg {
background-color:#F2E3E9;
}

td.row2,td.selected,.row2 {
background-color:#ebbc5d;
}

.stylobutton {
width:140px;
}

.stylobutton100 {
width:100px;
}

input.button {
border:0.05em solid;
cursor:pointer;
font-weight:700 !important;
overflow:visible;
width:103px;
padding:0 0.25em;
}
-->
</style>
#if(!$!flagOnline.equals(''))
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPK.css" />
#else
<link rel="stylesheet" type="text/css" href="../../css/online.css" />
#end
  	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  	<input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan"/>
	<input type="hidden" name="idHarta" value="$!idHarta"/>

	<input type="hidden" name="actionPopup" value="$!actionPopup"/>
  	<input type="hidden" name="hitButton" id="hitButton" value="$!hitButton"/>
	<input type="hidden" name="mode" value="$mode"/>
	<input type="hidden" name="mode_" value="$mode_"/>
	<input type="hidden" name="flagOnline" value="$!flagOnline"/>
	<input type="hidden" name="actionrefresh" value="$!actionRefresh"/>
	<!-- lampiran simati -->
	<input type="hidden" name="rujukan" value="$!idRujukan"/>
	<input type="hidden" name="jenisdokumen" value="$!jenisdokumen"/>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    	<fieldset><legend><strong>MAKLUMAT FAIL</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
				<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">##if ($mode != 'readonly') * #end </span></td>				        
						<td width="28%">
							<div align="right" class="labelinput">
							<div align="left">Bil. Lampiran</div>
							</div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="70%">
							<input type="text" size="2" name="jumlahlampiran" $!RO_General value="$!num_files"
							onBlur="doChangeJumlahLampiranHTAAH('$!IDJadualLampiran',this,'$!action');" > (<font size="1" color="red">Sila masukkan jumlah lampiran</font>)
						</td>
				</tr> 
				<tr>
				    <td valign="top" width="1%"><span class="style1">&nbsp;</span></td>
				    <td valign="top">Muatnaik Lampiran</td>
				    <td valign="top">:</td>
				    <td><!-- accept="image/x-png,image/gif,image/jpeg"-->
				    #foreach( $i in [1..$num_files] )							
						<input type="file" id="dokumen" name="dokumen" size="40" accept=".pdf" class="texts" $!readOnly  /></br>
					#end
					</td/>
				</tr>
				<tr>
			   		<td valign="top"></td>
			       	<td valign="top"></td>
			       	<td valign="top"></td>
			     	<td valign="top">
				#set ( $cnt = 0 )
				#foreach($mo in $senaraidokumen)
					#set ( $cnt = $cnt + 1 )
					$!mo.namaFail
						<a class="opener" href="javascript:deleteDetailImej('$!mo.idDokumen','$!mo.idLampiran')" 
							onclick="deleteDetailImej('$!mo.idDokumen','$!mo.idLampiran'); return false;">
							<img src="../../img/online/x.gif" alt="hapus" width="20" height="15"/>
						</a>
					<br>
				#end
					</td>
	  			</tr>
	        
            </table>        
        </fieldset>
    </td>
  </tr>
  <tr>
  	<td align="center">
  		#if(!$!disability.equals('disabled'))
    	<input type="button" class="stylobutton100" name="cmdPilih" id="cmdPilih" value="Simpan" onClick="simpanLampiran()">
    	#end
    	<!-- <input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Senarai Tanah" onClick="kembali()">-->
    	<input type="button" class="stylobutton100" name="cmdtutup" value="Tutup" onClick="tutup()">
    	<!--<input type="button" class="stylobutton100" name="cmdrefrehs" value="Refresh" onClick="refresh()"> --> 
    </td>
  </tr>
</table>


<script>
	//Hapus dokumen pada senarai harta
	function deleteDetailImej(iDokumen,lampiran){	
		if ( !window.confirm("Adakah Anda Pasti?")) return;
	/* 	document.${formName}.actionPopup.value = "papar";
		document.${formName}.hitButton.value = "hapus"; */
		//document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumenHarta&actionPopup=papar&hitButton=hapus&iDokumen="+iDokumen;
		if('myid'=='$!jenisdokumen'||'cod'=='$!jenisdokumen'){ 
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumenHarta&actionPopup=paparHA&hitButton=hapusmyid&iDokumen="+iDokumen;
		}else if('$!jenisdokumen' == '99203'){ 
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumen&actionPopup=paparHA&hitButton=hapusmyid&iDokumen="+iDokumen;  
		}else if('$!jenisdokumen' == '99204'){
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumen&actionPopup=paparHA&hitButton=hapusmyid&iDokumen="+iDokumen;  
		}else if('$!jenisdokumen' == '99210'){
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumen&actionPopup=paparHA&hitButton=hapusmyid&iDokumen="+iDokumen;  
		}else if('$!jenisdokumen' == '99211'){
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumen&actionPopup=paparHA&hitButton=hapusmyid&iDokumen="+iDokumen;  
		}
		
		else{
			if('paparHA'=='$!actionRefresh'){
				document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumenHarta&actionPopup=paparHA&hitButton=hapusHA&iDokumen="+iDokumen;
			}else{
				document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumenHarta&actionPopup=papar&hitButton=hapus&iDokumen="+iDokumen;
			}
		}
		document.${formName}.enctype="multipart/form-data";
	    document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();
		refresh('$!flagOnline');
	
	}
	function simpanLampiran() {
		document.${formName}.hitButton.value = "simpan";
		//document.${formName}.method="post";
		if('paparHA'=='$!actionRefresh'){
			document.${formName}.hitButton.value = "simpanHA";
			document.${formName}.actionPopup.value = "paparHA";
		}else{
			document.${formName}.hitButton.value = "simpan";
			document.${formName}.actionPopup.value = "papar";
		}
		//alert('simpanLampiran:$!jenisdokumen');
		if('myid'=='$!jenisdokumen'||'cod'=='$!jenisdokumen'){
			if('myid'=='$!jenisdokumen')
				actExt ="&actionPopup=MyID"+"&hitButton=simpanMyID";
			else if ('cod'=='$!jenisdokumen')
				actExt ="&actionPopup=cod"+"&hitButton=simpancod";
			
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumen"
									+"&rujukan=$!idRujukan"
									+"&actionrefresh=$!actionRefresh"+actExt;
		}else if('$!jenisdokumen' == '99203'){
			actExt ="&jenisdokumen=$!jenisdokumen";
			actExt +="&actionPopup=$!actionPopup&hitButton=simpanlampiran&rujukan=$!idRujukan&actionrefresh=$!actionRefresh";
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumen"+actExt;

		}else if('$!jenisdokumen' == '99204'){
			actExt ="&jenisdokumen=$!jenisdokumen";
			actExt +="&actionPopup=$!actionPopup&hitButton=simpanlampiran&rujukan=$!idRujukan&actionrefresh=$!actionRefresh";
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumen"+actExt;

		}
		else if('$!jenisdokumen' == '99210'){
			actExt ="&jenisdokumen=$!jenisdokumen";
			actExt +="&actionPopup=$!actionPopup&hitButton=simpanlampiran&rujukan=$!idRujukan&actionrefresh=$!actionRefresh";
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumen"+actExt;

		}
		else if('$!jenisdokumen' == '99211'){
			actExt ="&jenisdokumen=$!jenisdokumen";
			actExt +="&actionPopup=$!actionPopup&hitButton=simpanboranga&rujukan=$!idRujukan&actionrefresh=$!actionRefresh";
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumen"+actExt;

		}
		else{
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.util.FrmUploadDokumenHarta&actionPopup="+document.${formName}.actionPopup.value
									+"&hitButton="+document.${formName}.hitButton.value
									+"&idHarta=$!idHarta"
									+"&actionrefresh=$!actionRefresh";
		
		}
		//alert(document.${formName}.action.value);								
		document.${formName}.enctype="multipart/form-data";
	    document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit(); 
		
			//window.opener.refreshFromPilihTanah();
			//window.close();
	
	}
	//Lampiran
	function doChangeJumlahLampiranHTAAH(IDJadualLampiran,j,a) {		
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}
		
		//document.${formName}.actionPopup.value = "";
		document.${formName}.mode.value="bilampiran";	
		document.${formName}.submit();
		
	} 

	function kembali() {	
		document.${formName}.actionPopup.value = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}
	
	// function semua kongsi
	function validateNumber(elmnt,content) {
		//if it is character, then remove it..
		if (isNaN(content)) {
			elmnt.value = RemoveNonNumeric(content);
			return;
		}
	}	
		
	function tutup() {	
		//
		window.close();
		refresh('$!flagOnline');
	}
	
	function cetakImej(id_){
		//var url = "../servlet/ekptg.view.ppk.util.DisplayBlobHarta?iDokumen="+id_;
		var url = "../${securityToken}/ekptg.view.ppk.util.DisplayBlobHarta?iDokumen="+id_;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function refresh(flagOnline) {
		//alert('$!actionRefresh');
		if('paparHA'=='$!actionRefresh'){
			window.opener.setSelected(2,0,0,0);
			window.opener.HAview(flagOnline);
			
		}else if('paparHTA'=='$!actionRefresh'){
			window.opener.setSelected(1,0,0,0);
			window.opener.HtaamView();
			
		}else if('paparHTATH'=='$!actionRefresh'){
			window.opener.setSelected(1,0,0,1);
			window.opener.HtaamViewX(flagOnline);
		
		}else if('lampiransimati'=='$!actionRefresh'){
			window.opener.semakLampiran('socBandar');
			
		}else if('borangP' == '$!actionRefresh'){
			window.opener.semakLampiran();
		
		}else if('dokumenA' == '$!actionRefresh'){
			window.opener.semakLampiran();
		
		}
		
		
	}
	//setSelected(1,0,0,1);HtaamViewX('$paramOnline')
	
</script>
#parse("app/htp/utiliti/javaScriptUmum.jsp")

