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
<link rel="stylesheet" type="text/css" href="../../css/eTapp_HTP.css" />
<input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    	<fieldset><legend><strong>CARIAN TANAH</strong></legend>
        	<table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="30%">Jenis Tanah</td>
                <td width="70%">:
                <select name="socJenisTanah" id="socJenisTanah" onchange="doChangeJenisTanah()">
                  <option $selected value="0">SILA PILIH</option>
                  <option $selected1 value="1">TANAH MILIK PERSEKUTUAN</option>
                  <option $selected2 value="2">TANAH RIZAB PERSEKUTUAN</option>
                </select>
                <input type="hidden" name="actionPopup"/>
                <input type="hidden" name="idHakmilik"/></td>
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
                $selectNegeri</td>
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
                <td>&nbsp;</td>
                <td><input class="stylobutton100" type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="doCarian()"/>
                <input class="stylobutton100" type="button" name="cmdKosong" id="cmdKosong" value="Kosongkan" onclick="kosongkan()"/></td>
              </tr>
            </table>

        </fieldset>
    </td>
  </tr>
  <tr>
    <td>
    	<fieldset>
		<legend><b>SENARAI TANAH</b></legend>
        #parse("app/utils/record_paging_popup.jsp")

        <table align="center" width="100%">
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil.</strong></td>
              <td width="15%"><strong>Negeri</strong></td>
              <td width="15%"><strong>Daerah</strong></td>
              <td width="15%"><strong>Bandar/Pekan/Mukim</strong></td>
              <td width="15%"><strong>Seksyen</strong></td>
              <td width="15%"><strong>No. Hakmilik</strong></td>
              <td width="15%"><strong>No. Warta</strong></td>
              <td width="15%"><strong>No.PT/ Lot/</strong></td>
              <!--<td width="15%"><strong>Pegangan Hakmilik</strong></td>-->
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
            <td class="$row" align="center">$list.bil.</td>
            <!--<td class="$row"><a href="javascript:papar('$list.idHakmilik')" class="style1">$list.peganganHakmilik</a></td>-->
             <td class="$row"><a href="javascript:papar('$list.idHakmilik')" class="style1">$list.negeri</a></td>
             <td class="$row">$list.daerah</td>
             <td class="$row">$list.mukim</td>
             <td class="$row">$list.namaSeksyen</td>
            <td class="$row">$list.noHakmilik</td>
            <td class="$row">$list.noWarta</td>
         	<td class="$row">$list.lot</td>
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
            <!--<td class="row1">&nbsp;</td>-->
          </tr>
          #end
        </table>
		</fieldset>
    </td>
  </tr>
</table>
<input name="mode" type="hidden" value="$mode" />

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
	function doCarian() {
		doAjaxCall${formName}("doCarian");
	}
	function kosongkan() {
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
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPajakanPopupSenaraiTanahView";
		document.${formName}.submit();
	}
	function papar(idHakmilik) {
		document.${formName}.idHakmilik.value = idHakmilik;
		document.${formName}.mode.value = "baru";
		document.${formName}.actionPopup.value = "papar";
		document.${formName}.submit();
	}

</script>
