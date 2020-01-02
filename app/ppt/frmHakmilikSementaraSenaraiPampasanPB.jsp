#parse("app/ppt/SementaraPaging.jsp")

#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp") 
<p>
<fieldset><legend><strong>Senarai Pihak Berkepentingan</strong></legend>
<table width="100%">
  <tr class="table_header">
    <td><strong>Bil</strong></td>
    <td><strong>Nama PB</strong></td>
    <td><strong>No. PB</strong></td>
    <td><strong>No. Lot/PT</strong></td>
    <td><strong>Mukim</strong></td>
    <td><strong>Bahagian</strong></td>
    <td><strong>Jumlah Pampasan</strong></td>
  </tr>
   #foreach ($pb in $SenaraiPB)
  
  #if ($pb.bil == '') 
  #set ($row = 'row1')
  #elseif ($pb.bil % 2 != 0)
  #set ($row = 'row1')
  #else 
  #set ($row = 'row2')
  #end
  <tr>
    <td class="$row">$pb.bil</td>
    #if ($pb.bil != '') 
    <td class="$row"><a href="javascript:maklumatPampasan('$pb.ID_PIHAKBERKEPENTINGAN','$pb.ID_HAKMILIK','$pb.ID_HAKMILIKPB','$pb.ID_PERMOHONAN')"><font color="blue">$pb.NAMA_PB</font></a></td>
    #else
     <td class="$row">$pb.NAMA_PB</td>
    #end
    <td class="$row">$pb.NO_PB</td>
    <td class="$row">$pb.NO_LOT</td>
    <td class="$row">$pb.NAMA_MUKIM</td>
    <td class="$row">$pb.SYER_ATAS</td>
    <td class="$row">$pb.BAYAR_PAMPASAN</td>
  </tr>
  #end
</table>
</fieldset>
<fieldset id="bottom">
	<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
     <table width="100%" border="0"> 
    <tr >
    <td align="left">
    <a href="javascript:popupCarianHakmilik('$id_permohonan','senarai_pampasan_sementara')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
    </td>
    </tr>
    </table>
    </fieldset>
    
<!--<table width="100%">
	<tr>
   	  <td align="center"><input name="cmdCetak" type="button" id="cmdCetak" value="Cetak"  onclick="setTable('tableReport1')" /></td>
    </tr>
</table>
<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakSuratHadirSerahPampasan()"><font color="blue">Surat Kepada Pihak Berkepentingan – Pampasan </font></a></td>
      </tr>           
    </table>
</fieldset>-->
<!--<input type="hidden" name="id_fail" value="$idFail" />-->
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input name="idPB" type="hidden" value="$idPihakBerkepentingan" />
<input name="id_hakmilik" type="hidden" value="$id_hakmilik" />
<input name="idHakmilikPB" type="hidden" value="$idHakmilikPB" />
<input name="idSiasatan" type="hidden" value="$ID_SIASATAN" />

<!-- ADDED BY ELLY 15 JUNE 2010 -->
<input type="hidden" name="id_status" id="id_status" value="$!id_status" />
<input type="hidden" name="ScreenLocation" id="ScreenLocation"  > 
<input type="hidden" name="id_permohonan" value="$id_permohonan" />


<script>
function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function maklumatPampasan(idPb,id_hakmilik,idHakmilikPB){

	document.${formName}.idPB.value = idPb;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.idHakmilikPB.value = idHakmilikPB;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabTuntutan";
	document.${formName}.submit();


}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakSuratHadirSerahPampasan(id_fail,id_bayaran) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.suratUtkPanggilanTerimaPampasanKpdPB?idFail="+id_fail+"&idBayaran="+id_bayaran;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
</script>