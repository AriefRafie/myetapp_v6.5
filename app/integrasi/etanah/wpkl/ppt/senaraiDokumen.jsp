<input type="hidden" name="idDokumen" id="idDokumen">

<table border="0" width="100%"  class="nav">
  <tr >
    <td valign="top" ><strong>Senarai Dokumen Permohonan</strong> </td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
  <tr class="table_header">
    <td width="5%" scope="row"  align="center"><strong><font color="white">Bil.</font></strong></td>
    <td ><strong><font color="white">Jenis Dokumen</font></strong></td>
    #if ($!maklumatPermohonan.flagHantar == 'T')
    <td width="5%" align="center" ><b><font color="white"></font></b></td>
    #end
  </tr>
  #set ($listD = "")
  #set ($counter = 0)
  #if ($listDokumen.size() > 0)
  #foreach ($listD in $listDokumen)
  #set( $counter = $counter + 1 )
  #if ($counter == '')
  #set( $row = "row1" )
  #elseif (($counter % 2) != 0)
  #set( $row = "row1" )
  #else 
  #set( $row = "row2" )
  #end
  <tr>
    <td class="$row" align="center"> $counter </td>
    <td class="$row"> <a href="javascript:paparDokumen($!listD.idDokumenPermohonan)" class="style2"><strong>$!listD.jenisDokumen</strong></a></td>
    #if ($!maklumatPermohonan.flagHantar == 'T')
    <td class="$row" align="center"><span id="hapusDoc"> <a href="javascript:void()" onClick="deleteDokumen($!listD.idDokumenPermohonan)" ><img src="../../img/delete.gif" border="0"></a> </span></td>
    #end
  </tr>
  #end  
  #else
  <tr>
    <td colspan="3" class="row"> Tiada rekod </td>
  </tr>
  #end
</table>

<script>
function simpanDokumen() {

	var idFail = document.${formName}.idFail.value;
	var idPermohonan = document.${formName}.idPermohonan.value;
	var jenisSkrin = document.${formName}.jenisSkrin.value;
	var idPermohonanIntegrasi = document.${formName}.idPermohonanIntegrasi.value;
	var idPPTWarta = document.${formName}.idPPTWarta.value;
	var idPPTHakmilik = document.${formName}.idPPTHakmilik.value;
	var idPPTPenarikanBalik = document.${formName}.idPPTPenarikanBalik.value;

	if(document.${formName}.jenisDokumen.value == ""){
		alert('Sila pilih Jenis Dokumen.');
  		document.${formName}.jenisDokumen.focus(); 
		return; 
	}
	if(document.${formName}.dokumen.value == ""){
		alert('Sila pilih Dokumen yang Ingin Dimuatnaik.');
  		document.${formName}.dokumen.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	var jenisDokumen = document.${formName}.jenisDokumen.value;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL&command=muatNaikDokumen&idFail="+idFail+"&idPermohonan="+idPermohonan+"&idPPTWarta="+idPPTWarta+"&idPPTHakmilik="+idPPTHakmilik+"&idPPTPenarikanBalik="+idPPTPenarikanBalik+"&jenisSkrin="+jenisSkrin+"&idPermohonanIntegrasi="+idPermohonanIntegrasi+"&jenisDokumen="+jenisDokumen+dopost;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}

function paparDokumen(id){
	var url = "../../servlet/ekptg.intergration.eTanah.pengambilan.FrmDisplayDokumenIntegrasiPPT?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function paparDokumenEndorsan(id){
	var url = "../../servlet/ekptg.intergration.eTanah.pengambilan.FrmDisplayDokumenEndorsanIntegrasiPPT?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function deleteDokumen(id){		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL";
	document.${formName}.method="POST";
	document.${formName}.idDokumen.value = id;
	document.${formName}.command.value = "deleteDokumen";
	document.${formName}.submit();
}
</script>
