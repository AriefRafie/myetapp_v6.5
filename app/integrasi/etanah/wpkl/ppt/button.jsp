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
          <input type="button" name="cmdGeneratePermohonan" id="cmdGeneratePermohonan" value="Jana Semula Permohonan" onClick="janaPermohonan()">
          #if($!jenisSkrin == "WartaS8")
          <input type="button" name="cmdHantarRekod" id="cmdHantarRekod" value="Hantar Rekod ke e-Tanah" onClick="hantarBorangD()">
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
function janaPermohonan() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL";
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
function hantarBorangD() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL";
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
