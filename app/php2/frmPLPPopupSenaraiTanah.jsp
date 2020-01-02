<style type="text/css">
<!--
.style1 {
	color: #0000FF
}
#parse("css/eTapp_PHP.css")
-->
</style>
<input type="hidden" name="actionPopup"/>
<input type="hidden" name="idHakmilikAgensi"/>
<input type="hidden" name="idKategoriPemohon" value="$idKategoriPemohon"/>
<input type="hidden" name="idNegeriPemohon" value="$idNegeriPemohon"/>
<input type="hidden" name="idKementerianPemohon" value="$idKementerianPemohon"/>
<input type="hidden" name="idHakmilikSementara"/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>CARIAN TANAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="30%">Jenis Tanah</td>
          <td width="70%">:
            <select name="socJenisTanah" id="socJenisTanah" onchange="doChangeJenisTanah()">
              <option $selected value="0">SILA PILIH</option>
              <option $selected1 value="1">TANAH MILIK PERSEKUTUAN</option>
              <option $selected2 value="2">TANAH RIZAB PERSEKUTUAN</option>
            </select></td>
        </tr>
        <tr>
          <td>Pegangan Hakmilik</td>
          <td>:
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$txtPeganganHakmilik"></td>
        </tr>
        #if ($idJenisTanah == '0' || $idJenisTanah == '1')
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
        #end
        #if ($idJenisTanah == '0' || $idJenisTanah == '1' || $idJenisTanah == '2')
        <tr>
          <td>No. Warta</td>
          <td>:
            <input type="text" name="txtNoWarta" id="txtNoWarta" value="$txtNoWarta"></td>
        </tr>
        <tr>
          <td>Tarikh Warta</td>
          <td>:
            <input type="text" name="tarikhWarta" id="tarikhWarta" value="$tarikhWarta" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('tarikhWarta',false,'dmy');"><img border="0" src="../../img/calendar.gif"/></td>
        </tr>
        #end
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
        <tr>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="doCarian()"/>
            <input type="button" name="cmdKosong" id="cmdKosong" value="Kosongkan" onclick="kosongkan('$idNegeriPemohon')"/></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI TANAH</b></legend>
      #parse("app/utils/record_paging_popup.jsp")
      <table align="center" width="100%">
      <tr>
          <td colspan="6" scope="row"><input name="cmdDaftar" type="button" value="Daftar Hakmilik" onclick="javascript:daftarHakmilik()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>Pegangan Hakmilik</strong></td>
          <td width="10%"><strong>Lot</strong></td>
          <td width="15%"><strong>No. Hakmilik</strong></td>
          <td width="10%"><strong>No. Warta</strong></td>
          <td width="25%"><strong>Mukim, Daerah, Negeri</strong></td>
          <td width="20%"><strong>Agensi</strong></td>
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
          <td class="$row"><a href="javascript:papar('$list.idHakmilikAgensi','$list.idHakmilikSementara')" class="style1">$list.peganganHakmilik</a></td>
          <td class="$row">$list.lot</td>
          <td class="$row">$list.noHakmilik</td>
          <td class="$row">$list.noWarta</td>
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
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doChangeJenisTanah() {
	doAjaxCall${formName}("doChangeJenisTanah");
}
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
function kosongkan(idNegeriPemohon) {
	document.${formName}.reset();
	if (document.${formName}.socJenisTanah.value == '0'){
		document.${formName}.socJenisHakmilik.value = "";
		document.${formName}.txtNoHakmilik.value = "";
		document.${formName}.socJenisLot.value = "";
		document.${formName}.txtNoLot.value = "";
		document.${formName}.txtNoWarta.value = "";
		document.${formName}.tarikhWarta.value = "";
	} else if (document.${formName}.socJenisTanah.value == '1'){		
		document.${formName}.socJenisHakmilik.value = "";
		document.${formName}.txtNoHakmilik.value = "";
		document.${formName}.socJenisLot.value = "";
		document.${formName}.txtNoLot.value = "";
		document.${formName}.txtNoWarta.value = "";
		document.${formName}.tarikhWarta.value = "";
	} else if (document.${formName}.socJenisTanah.value == '2'){
		document.${formName}.txtNoWarta.value = "";
		document.${formName}.tarikhWarta.value = "";
	}
	document.${formName}.txtPeganganHakmilik.value = "";
	document.${formName}.socJenisTanah.value = "0";
	document.${formName}.socNegeri.value = "";
	document.${formName}.socDaerah.value = "";
	document.${formName}.socMukim.value = "";
	document.${formName}.socKementerian.value = "";
	document.${formName}.socAgensi.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPLPPopupSenaraiTanahView";
	document.${formName}.submit();
}
function papar(idHakmilikAgensi, idHakmilikSementara) {	
	document.${formName}.idHakmilikAgensi.value = idHakmilikAgensi;
	document.${formName}.idHakmilikSementara.value = idHakmilikSementara;
	document.${formName}.actionPopup.value = "papar";
	document.${formName}.submit();
}
function daftarHakmilik() {	
	document.${formName}.actionPopup.value = "daftar";
	document.${formName}.submit();
}
</script>
