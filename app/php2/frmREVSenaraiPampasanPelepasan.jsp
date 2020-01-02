<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="flagDetail" type="hidden" id="flagDetail" value="$flagDetail"/>
  <input name="mode" type="hidden" id="mode" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Fail : </td>
          <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;">
          	#if($flagDetail == '') 
            <a href="javascript:bukaCarian();" class="style1">Buka Carian Terperinci </a>
            #else
            <a href="javascript:tutupCarian();" class="style1">Tutup Carian Terperinci </a>
            #end          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama Pemohon : </td>
          <td width="70%"><input name="txtNamaPemohon" id="txtNamaPemohon" type="text" value="$txtNamaPemohon" size="50" maxlength="50" style="text-transform:uppercase;">          </td>
        </tr>
        #if($flagDetail == 'buka')
        <tr>
          <td scope="row" align="right">No Pegangan Hakmilik :</td>
          <td><input name="txtNoPegangan" id="txtNoPegangan" type="text" value="$txtNoPegangan" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right"> Jenis Hakmilik :</td>
          <td>$selectJenisHakmilik</td>
        </tr>
        <tr>
          <td scope="row" align="right"> No Hakmilik :</td>
          <td><input name="txtNoHakmilik" id="txtNoHakmilik" type="text" value="$txtNoHakmilik" size="30" maxlength="50" /></td>
        </tr>
        <tr>
          <td scope="row" align="right"> No Warta :</td>
          <td><input name="txtNoWarta" id="txtNoWarta" type="text" value="$txtNoWarta" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right">Jenis Lot :</td>
          <td>$selectJenisLot</td>
        </tr>
        <tr>
          <td scope="row" align="right">No Lot :</td>
          <td><input name="txtNoLot" id="txtNoLot" type="text" value="$txtNoLot" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right">Negeri :</td>
          <td>$selectNegeriC</td>
        </tr>
        <tr>
          <td scope="row" align="right">Daerah :</td>
          <td>$selectDaerahC</td>
        </tr>
        <tr>
          <td scope="row" align="right">Mukim :</td>
          <td>$selectMukimC</td>
        </tr>
		#end
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan('$flagDetail')">
            <input type="hidden" name="idHasil" />
            <input type="hidden" name="actionHasil"/>
            <input type="hidden" name="idFail" />
                      </td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI FAIL</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No Fail</strong></td>
          <td width="20%"><strong>Urusan</strong></td>
          <td width="50%"><strong>Nama Pemohon</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiFail.size() > 0)
        #foreach ($list in $SenaraiFail)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idHasil','$list.idFail')" class="style1">$list.noFail</a></td>
          <td class="$row">$list.urusan</td>
          <td class="$row">$list.namaPemohon</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
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
function carian(){
	document.${formName}.actionHasil.value = "";
	doAjaxCall${formName}("");
}
function kosongkan(flagDetail) {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNamaPemohon.value = "";
	if (flagDetail == 'buka'){
		document.${formName}.txtNoPegangan.value = "";
		document.${formName}.socJenisHakmilik.value = "";
		document.${formName}.txtNoHakmilik.value = "";	
		document.${formName}.txtNoWarta.value = "";
		document.${formName}.socJenisLot.value = "";
		document.${formName}.txtNoLot.value = "";		
		document.${formName}.socNegeriC.value = "";
		document.${formName}.socDaerahC.value = "";
		document.${formName}.socMukimC.value = "";		
	}
	doAjaxCall${formName}("");
}
function papar(idHasil,idFail) {
	document.${formName}.actionHasil.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.idHasil.value = idHasil;
	document.${formName}.idFail.value = idFail;
	document.${formName}.submit();
}

function bukaCarian(){
	document.${formName}.flagDetail.value = "buka";
	document.${formName}.actionHasil.value = "";
	doAjaxCall${formName}("");
}
function tutupCarian(){
	document.${formName}.flagDetail.value = "";
	document.${formName}.actionHasil.value = "";
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNamaPemohon.value = "";
	document.${formName}.txtNoPegangan.value = "";
	document.${formName}.socJenisHakmilik.value = "";
	document.${formName}.txtNoHakmilik.value = "";	
	document.${formName}.txtNoWarta.value = "";
	document.${formName}.socJenisLot.value = "";
	document.${formName}.txtNoLot.value = "";		
	document.${formName}.socNegeriC.value = "";
	document.${formName}.socDaerahC.value = "";
	document.${formName}.socMukimC.value = "";		
	doAjaxCall${formName}("");
}
</script>
