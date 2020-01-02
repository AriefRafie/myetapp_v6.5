<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  
  <input name="actionPenswastaan" type="hidden" id="actionPenswastaan" value="$actionPenswastaan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idTukarGanti" type="hidden" id="idTukarGanti" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td>#parse("app/htp/frmPenswastaan2Header.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
    
    <fieldset>
    <legend><strong>SENARAI HAKMILIK</strong></legend>
    
    	<table align="center" width="100%">
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil.</strong></td>              
              <td width="15%"><strong>Daerah</strong></td>
              <td width="15%"><strong>Negeri</strong></td>
              <td width="15%"><strong>Bandar/Pekan/Mukim</strong></td>
              <td width="10%"><strong>No. Hakmilik</strong></td>
              <td width="10%"><strong>No. Warta</strong></td>
              <td width="10%"><strong>No Lot/PT</strong></td>
             <td width="15%"><strong>Tindakan Lanjut</strong></td>
            </tr>
          #set ($list = "")
          #if ($SenaraiHakmilik.size() > 0)
          #foreach ($list in $SenaraiHakmilik)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
          <tr>
            <td class="$row" align="center">$list.bil.</td>            
             <td class="$row">$list.negeri</td>
             <td class="$row">$list.daerah</td>
             <td class="$row">$list.mukim</td>
          	<td class="$row">$list.noHakmilik</td>
            <td class="$row">$list.noWarta</td>
       		<td class="$row">$list.lot</td>
             <td class="$row">$list.tindakanLanjut</td>
          </tr>
          #end
          #else
          <tr>
            <td class="row1" align="center">&nbsp;</td>
            <td class="row1">Tiada Rekod</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
          </tr>
          #end
        </table>
        
    </fieldset>
    
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
    
    <fieldset>
    <legend><strong>SENARAI HAKMILIK TUKAR GANTI (MILIK PTP)</strong></legend>
    
    	<table align="center" width="100%">
            <tr>
              <td colspan="8" scope="row">
              <input class="stylobutton"name="cmdDaftar" type="button" value="Pilih Tanah" onClick="pilihTanah('$idPermohonan')">
              </td>
            </tr>
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil.</strong></td>              
              <td width="15%"><strong>Negeri</strong></td>
              <td width="15%"><strong>Daerah</strong></td>
              <td width="15%"><strong>Bandar/Pekan/Mukim</strong></td>
             <td width="15%"><strong>No. Hakmilik</strong></td>
              <td width="15%"><strong>No. Warta</strong></td>
              <td width="10%"><strong>No Lot/PT</strong></td>
               <td width="5%"></td>
            </tr>
          #set ($list = "")
          #if ($SenaraiHakmilikTukarGanti.size() > 0)
          #foreach ($list in $SenaraiHakmilikTukarGanti)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
          <tr>
            <td class="$row" align="center">$list.bil.</td>            
            <td class="$row">$list.negeri</td>
            <td class="$row">$list.daerah</td>
            <td class="$row">$list.mukim</td>
            <td class="$row">$list.noHakmilik</td>
            <td class="$row">$list.noWarta</td>
            <td class="$row">$list.lot</td>
            <td class="$row"><input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="hapusHakmilikTukarGanti('$list.idTukarGanti')"></td>
          </tr>
          #end
          #else
          <tr>
            <td class="row1" align="center">&nbsp;</td>
            <td class="row1">Tiada Rekod</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
          </tr>
          #end
        </table>
        
    </fieldset>
    
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #else
  <tr>
    <td>
    	&nbsp;<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
    </td>
  </tr>
  #end
</table>

<script>
function pilihTanah(idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.htp.FrmPenswastaanPopupSenaraiTanahTukarGantiView?idPermohonan="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah() {
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.submit();
	refreshAgain()
}
function refreshAgain() {
	document.${formName}.submit();
}
function hapusHakmilikTukarGanti(idTukarGanti){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.idTukarGanti.value = idTukarGanti;
	document.${formName}.hitButton.value = "hapusHakmilikTukarGanti";
	doAjaxCall${formName}("");
}
</script>
