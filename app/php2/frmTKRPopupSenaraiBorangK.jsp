<style type="text/css">
<!--
.style1 {
	color: #0000FF
}
#parse("css/eTapp_PHP.css")
-->
</style>
<input type="hidden" name="actionPopup"/>
<input type="hidden" name="idPPTBorangK"/>
<input type="hidden" name="idHakmilikUrusan"/>
<input type="hidden" name="idPHPBorangK"/>

<input type="hidden" name="idKategoriPemohon" value="$idKategoriPemohon"/>
<input type="hidden" name="idAgensiPemohon" value="$idAgensiPemohon"/> 
<input type="hidden" name="idNegeriJKPTG" value="$idNegeriJKPTG"/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatAgensi in $BeanMaklumatAgensi)
        <tr>
          <td width="30%">KEMENTERIAN</td>
          <td width="70%">: <strong>$beanMaklumatAgensi.kementerian</strong></td>
        </tr>
        <tr>
          <td>AGENSI</td>
          <td>: <strong>$beanMaklumatAgensi.agensi</strong></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>CARIAN TANAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="30%">Pegangan Hakmilik</td>
          <td width="70%">:
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$txtPeganganHakmilik"></td>
        </tr>
        <tr>
          <td>Jenis Hakmilik</td>
          <td>:
            $selectJenisHakmilik</td>
        </tr>
        <tr>
          <td>No. Hakmilik</td>
          <td>:
            <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$txtNoHakmilik"></td>
        </tr>
        <tr>
          <td>Jenis Lot</td>
          <td>:
            $selectJenisLot</td>
        </tr>
        <tr>
          <td>No. Lot</td>
          <td>:
            <input type="text" name="txtNoLot" id="txtNoLot" value="$txtNoLot"></td>
        </tr>
        <tr>
          <td>Negeri</td>
          <td>:
            $selectNegeri
            <input type="hidden" name="idNegeriPemohon" value="$idNegeriPemohon"/></td>
        </tr>
        <tr>
          <td>Daerah</td>
          <td>:
            $selectDaerah</td>
        </tr>
        <tr>
          <td>Bandar/Pekan/Mukim</td>
          <td>:
            $selectMukim</td>
        </tr>
        #if ($idKategoriPemohon == '8')
        <tr>
          <td>Kementerian</td>
          <td>:
            $selectKementerian</td>
        </tr>
        <tr>
          <td>Agensi</td>
          <td>:
            $selectAgensi</td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="doCarian()"/>
            <input type="button" name="cmdKosong" id="cmdKosong" value="Kosongkan" onclick="kosongkan('$idKategoriPemohon')"/></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI BORANG K</b></legend>
      #parse("app/utils/record_paging_popup.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="6" scope="row"><input name="cmdDaftar" type="button" value="Daftar Borang K" onclick="javascript:daftarBorangK()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>Pegangan Hakmilik</strong></td>
          <td width="10%"><strong>Lot</strong></td>
          <td width="15%"><strong>No. Hakmilik</strong></td>
          <td width="30%"><strong>Mukim, Daerah, Negeri</strong></td>
          <td width="25%"><strong>Agensi</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiTanah.size() > 0)
        #foreach ($list in $SenaraiTanah)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idPPTBorangK','$list.idHakmilikUrusan','$list.idPHPBorangK')" class="style1">$list.peganganHakmilik</a></td>
          <td class="$row"><a href="javascript:papar('$list.idPPTBorangK','$list.idHakmilikUrusan','$list.idPHPBorangK')" class="style1">$list.lot</a></td>
          <td class="$row"><a href="javascript:papar('$list.idPPTBorangK','$list.idHakmilikUrusan','$list.idPHPBorangK')" class="style1">$list.hakmilik</a></td>
          <td class="$row">$list.mukim, $list.daerah, $list.negeri </td>
          #if ($list.agensi != "")
          <td class="$row">$list.agensi, $list.kementerian</td>
          #else
          <td class="$row">&nbsp;</td>
          #end </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doCarian() {
	doAjaxCall${formName}("doCarian");
}
function kosongkan(idKategoriPemohon) {
	document.${formName}.reset();
	document.${formName}.txtPeganganHakmilik.value = "";
	document.${formName}.socJenisHakmilik.value = "";
	document.${formName}.txtNoHakmilik.value = "";
	document.${formName}.socJenisLot.value = "";
	document.${formName}.txtNoLot.value = "";
	document.${formName}.socDaerah.value = "";
	document.${formName}.socMukim.value = "";
	if (idKategoriPemohon == '3'){
		document.${formName}.socKementerian.value = "";
		document.${formName}.socAgensi.value = "";
	}
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmTKRPopupSenaraiBorangKView";
	document.${formName}.submit();
}
function papar(idPPTBorangK,idHakmilikUrusan,idPHPBorangK) {	
	document.${formName}.idPPTBorangK.value = idPPTBorangK;
	document.${formName}.idHakmilikUrusan.value = idHakmilikUrusan;
	document.${formName}.idPHPBorangK.value = idPHPBorangK;
	document.${formName}.actionPopup.value = "papar";
	document.${formName}.submit();
}
function daftarBorangK() {	
	document.${formName}.actionPopup.value = "daftar";
	document.${formName}.submit();
}
</script>
