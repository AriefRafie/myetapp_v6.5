<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
        <legend><b>CARIAN</b></legend>
        <table width="100%" align="center" border="0">
          <tr>
            <td width="30%" height="24" scope="row" align="right">No. Rujukan <i>Online</i> : </td>
            <td width="70%"><input name="findNoFail" id="findNoFail" type="text" value="$!findNoFail" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <tr>
            <td width="30%" height="24" scope="row" align="right">Tajuk Fail : </td>
            <td width="70%"><input name="findTajukFail" id="findTajukFail" type="text" value="$!findTajukFail" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <tr>
            <td width="30%" height="24" scope="row" align="right">Nama Pemohon : </td>
            <td width="70%"><input name="findPemohon" id="findPemohon" type="text" value="$!findPemohon" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <tr>
            <td width="30%" height="24" scope="row" align="right"><em>MyID / MyCoID</em> : </td>
            <td width="70%"><input name="findNoPengenalan" id="findNoPengenalan" type="text" value="$!findNoPengenalan" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <tr>
            <td width="30%" height="24" scope="row" align="right">Tarikh Terima : </td>
            <td width="70%"><input type="text" name="findTarikhTerima" id="findTarikhTerima" value="$!findTarikhTerima" onblur="check_date(this)" size="9"/>
              <a href="javascript:displayDatePicker('findTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
          </tr>
          <tr>
            <td scope="row" align="right"> No Hakmilik :</td>
            <td><input name="findNoHakmilik" id="findNoHakmilik" type="text" value="$!findNoHakmilik" size="30" maxlength="50" /></td>
          </tr>
          <tr>
            <td scope="row" align="right"> No Warta :</td>
            <td><input name="findNoWarta" id="findNoWarta" type="text" value="$!findNoWarta" size="30" maxlength="50"/></td>
          </tr>
          <tr>
            <td scope="row" align="right">No Pegangan Hakmilik :</td>
            <td><input name="findNoPegangan" id="findNoPegangan" type="text" value="$!findNoPegangan" size="30" maxlength="50"/></td>
          </tr>
          <tr>
            <td scope="row" align="right"> Jenis Hakmilik :</td>
            <td>$selectJenisHakmilik</td>
          </tr>
          <tr>
            <td scope="row" align="right">Jenis Lot :</td>
            <td>$selectLot</td>
          </tr>
          <tr>
            <td scope="row" align="right">No Lot :</td>
            <td><input name="findNoLot" id="findNoLot" type="text" value="$!findNoLot" size="30" maxlength="50"/></td>
          </tr>
          <tr>
            <td scope="row" align="right">Negeri :</td>
            <td>$selectNegeri</td>
          </tr>
          <tr>
            <td scope="row" align="right">Daerah :</td>
            <td>$selectDaerah</td>
          </tr>
          <tr>
            <td scope="row" align="right">Mukim :</td>
            <td>$selectMukim</td>
          </tr>
          <tr>
            <td scope="row"></td>
            <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="doDivAjaxCall$formname('divMainForm','carian','');">
              <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onclick="doDivAjaxCall$formname('divMainForm','','');"></td>
          </tr>
        </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
        <legend><b>SENARAI PERMOHONAN</b></legend>
        #parse("app/utils/record_paging.jsp")
        <table align="center" width="100%">
         <td><input name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar Permohonan Baru" type="button" onclick="doDivAjaxCall$formname('divMainForm','daftarBaru','');">
          <tr class="table_header">
            <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
           	<td width="19%"><strong>No. Rujukan <i>Online</i></strong></td>
           	<td width="19%"><strong>No. Fail</i></strong></td>
            <td width="32%"><strong>Tajuk Fail</strong></td>
            <td width="10%" align="center"><strong>Tarikh Mohon</strong></td>
            <td width="15%"><strong>Status</strong></td>

          </tr>
        #set ($list = "")
        #set ( $count = $startNumber )
        #if ($SenaraiFail.size() > 0)
        #foreach ($list in $SenaraiFail)
        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
          <tr>
            <td class="$row" align="center">$count</td>
            <td class="$row"><a href="javascript:paparFail('$!list.ID_FAIL', '$!list.ID_ULASANTEKNIKAL')" class="style2">$list.NO_PERMOHONAN</a></td>
            <td class="$row"><a href="javascript:paparFail('$!list.ID_FAIL', '$!list.ID_ULASANTEKNIKAL')" class="style2">$list.NO_FAIL</a></td>
            <td class="$row">$list.TAJUK_FAIL</td>
            <td class="$row" align="center">$list.TARIKH_TERIMA</td>
            <td class="$row" align="center">$list.STATUS</td>
          </tr>
          #end
          #else
          <tr>
            <td class="row1" align="center">&nbsp;</td>
            <td class="row1" colspan="4">Tiada Rekod</td>
          </tr>
          #end
        </table>
      </fieldset></td>
  </tr>
</table>

