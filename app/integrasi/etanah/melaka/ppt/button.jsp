#if ($errorMsg)
<table width="100%" border="0" align="center"	 cellspacing="2" cellpadding="2" >
  <tr>
    <td valign="top" align="center"><font color="red"><b>$!errorMsg</b></font></td>
  </tr>
</table>
<br />
#end
<table width="100%" border="0" align="center" cellspacing="2" cellpadding="2" >
  <tr>
    <td  valign="top" align="center"> 
      #if ($!maklumatPermohonan.flagHantar == 'T')
          <!-- <input type="button" name="cmdGeneratePermohonan" id="cmdGeneratePermohonan" value="Hantar Permohonan" onClick="janaPermohonan()">
          -->
          #if($!jenisSkrin == "Sekyen8")
          <input type="button" name="cmdHantarRekod" id="cmdHantarRekod" value="Hantar Permohonan" onClick="hantarPermohonan">
          #end
          #if($!jenisSkrin == "WartaS8")
          <input type="button" name="cmdHantarRekod" id="cmdHantarRekod" value="Hantar Permohonan" onClick="hantarBorangD()">
          #end 
          
       	  #if($!jenisSkrin == "BorangC")
          <input type="button" name="cmdHantarRekod" id="cmdHantarRekod" value="Hantar Permohonan" onClick="hantarBorangC()">
          #end 
          
          #if($!jenisSkrin == "BorangK")
          <input type="button" name="cmdHantarRekod" id="cmdHantarRekod" value="Hantar Rekod ke e-Tanah" onClick="hantarBorangK()">
          #end 
          
          #if($!jenisSkrin == "TarikBalik")
          <input type="button" name="cmdHantarRekod" id="cmdHantarRekod" value="Hantar Rekod ke e-Tanah" onClick="hantarTarikBalik()">
          #end
           
      #elseif ($!maklumatPermohonan.flagHantar == 'Y')
      	  #if($!jenisSkrin == "BorangK")
	          #if ($!flagEndorsanHakmilikBorangK != 'Y')
	          <input type="button" name="cmdGeneratePermohonan" id="cmdGeneratePermohonan" value="Jana Permohonan Baru" onClick="janaPermohonanBaruK()">
	          #end
          #end
      #end 
      </td>
  </tr>
</table>
<script>
	var urlInt = "?_portal_module=ekptg.view.integrasi.etanah.PermohonanPengambilan";

	function simpan(id_permohonan,jenis_skrin) {
		
		if(document.${formName}.tajuk.value == ""){
			alert('Sila pastikan tajuk dokumen diisi.');
	  		document.${formName}.tajuk.focus(); 
			return; 
		}
		
		if(document.${formName}.fileupload.value == ""){
			alert('Sila pilih Lampiran yang Ingin Dimuatnaik.');
	  		document.${formName}.fileupload.focus(); 
			return; 
		}
		
		var dp = document.${formName}.form_token.value ;
		var dopost = "&form_token="+dp;
		var id_permohonan_set =  document.${formName}.id_permohonan.value;
		var id_fail_set =  document.${formName}.id_fail.value;
		var jenis_skrin_set =  document.${formName}.jenis_skrin.value;
		var tajuk_set =  document.${formName}.tajuk.value;
		var id_hakmilik_set =  document.${formName}.id_hakmilik.value;
		
		//		var url = "../x/${securityToken}/ekptg.view.integrasi.etanah.PermohonanPengambilan?id_fail="+id_fail+"&idPermohonan="+id_permohonan+"&jenis_skrin="+jenis_skrin+"&command="+command;	
		var param = "&command=muatNaikDokumen&hitButton=simpanDokumen&id_permohonan="+id_permohonan_set+"&jenis_skrin="+jenis_skrin_set+""+dopost+"&id_fail="+id_fail_set+"&tajuk="+tajuk_set+"&id_hakmilik="+id_hakmilik_set;
		alert(param);
		document.${formName}.action = "?_portal_module=ekptg.view.integrasi.etanah.PermohonanPengambilan"+param;
		
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
	    document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();
		
	}
	
	function janaPermohonan() {	
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		document.${formName}.action = urlInt;
		//document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL";
		document.${formName}.method="POST";
		document.${formName}.command.value = "generatePermohonanIntegrasi";
		document.${formName}.submit();
	}

function janaPermohonanBaruK() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL";
	document.${formName}.method="POST";
	document.${formName}.command.value = "generatePermohonanBaruK";
	document.${formName}.submit();
}
	//02
	function hantarBorangC() {	
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		document.${formName}.action = urlInt;
		//document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL";
		document.${formName}.method="POST";
		document.${formName}.command.value = "hantarBorangC";
		document.${formName}.submit();
	}
	//01
	function hantarPermohonan() {	
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		document.${formName}.action = urlInt;
		//document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL";
		document.${formName}.method="POST";
		document.${formName}.command.value = "hantar"+${jenisSkrin};
		document.${formName}.submit();
		
	}
	
	function hantarBorangD() {	
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		document.${formName}.action = urlInt;
		//document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL";
		document.${formName}.method="POST";
		document.${formName}.command.value = "hantarBorangD";
		document.${formName}.submit();
	}

function hantarBorangK() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL";
	document.${formName}.method="POST";
	document.${formName}.command.value = "hantarBorangK";
	document.${formName}.submit();
}

function hantarTarikBalik() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL";
	document.${formName}.method="POST";
	document.${formName}.command.value = "hantarTarikBalik";
	document.${formName}.submit();
}
</script>
