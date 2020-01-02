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
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Fail : </td>
          <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" >
            <input type="hidden" name="idFail" />
            <input type="hidden" name="idPermohonan" />
            <input type="hidden" name="idStatus" />
            #if($flagDetail == '') <a href="javascript:bukaCarian()" class="style1">Buka Carian Terperinci #else </a>
            &nbsp;  &nbsp;<a href="javascript:tutupCarian()" class="style1">Tutup Carian Terperinci #end</a>            </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama Pengadu : </td>
          <td width="70%"><input name="txtPemohon" id="txtPemohon" type="text" value="$txtPemohon" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td scope="row" align="right">Status :</td>
          <td>$selectStatus</td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Kementerian Tanah : </td>
          <td width="70%">$selectKementerian
            <input name="idKementerian" type="hidden" value="$idKementerian"/>          </td>
        </tr>
         <tr>
          <td width="30%" height="24" scope="row" align="right">Agensi Tanah : </td>
          <td width="70%">$selectAgensi
          <input name="idAgensi" type="hidden" value="$idAgensi" /></td>
        </tr>
        #if($flagDetail == 'buka')
        <tr>
          <td align="right">No Pegangan Hakmilik : </td>
          <td>
            <input type="text" name="txtNoPegangan" id="txtNoPegangan" value="$txtNoPegangan"></td>
        </tr>
        <tr>
          <td align="right">Jenis Hakmilik : </td>
          <td> $selectJenisHakmilik</td>
        </tr>
        <tr>
          <td align="right">No. Hakmilik : </td>
          <td><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$txtNoHakmilik"></td>
        </tr>
        <tr>
          <td align="right">No. Warta : </td>
          <td><input type="text" name="txtNoWarta" id="txtNoWarta" value="$txtNoWarta" /></td>
        </tr>
        <tr>
          <td align="right">Jenis Lot : </td>
          <td>
            $selectLot</td>
        </tr>
        <tr>
          <td align="right">No. Lot : </td>
          <td> <input type="text" name="txtNoLot" id="txtNoLot" value="$txtNoLot"></td>
        </tr>
        <tr>
          <td align="right">Negeri : </td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td align="right">Daerah : </td>
          <td>$selectDaerah</td>
        </tr>
        <tr>
          <td align="right">Bandar/Pekan/Mukim : </td>
          <td>$selectMukim</td>
        </tr>
        <tr>
          <td align="right">Kegunaan Tanah : </td>
          <td><textarea name="txtKegunaanTnh" id="txtKegunaanTnh" rows="3" cols="50" style="text-transform:uppercase;">$txtKegunaanTnh</textarea>          </td>
        </tr>
		#end
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onClick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan('$flagDetail')"></td>
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
      <legend><b>SENARAI PERMOHONAN</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
          <tr class="table_header">
          <td scope="row" width="4%" align="center"><strong>Bil</strong></td>
          <td width="28%"><strong>No Fail</strong></td>
          <td width="28%" align="center"><strong>KJP</strong></td>
          <td width="30%" align="center"><strong>Keterangan Hakmilik</strong></td>
          <td width="10%"><strong>Status</strong></td>
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
          <td class="$row"><a href="javascript:papar('$list.idFail','$list.idStatus')" class="style1">$list.noFail</a></td>
          <td class="$row">$list.namaKementerian</td>
          <td class="$row">$list.kodHakmilik  $list.noHakmilik$list.koma $list.keteranganLot $list.noLot$list.koma
          		$list.namaMukim$list.koma $list.namaDaerah$list.koma $list.namaNegeri
          </td>
          <td class="$row">$list.status</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<div id="setSessionIdFail_result"></div>
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
function doChangeKementerianTanah() {
	doAjaxCall${formName}("doChangeKementerianTanah");
}
function bukaCarian(){
	document.${formName}.flagDetail.value = "buka";
	doAjaxCall${formName}("");
}
function tutupCarian(){
	document.${formName}.flagDetail.value = "";
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtPemohon.value = "";
	document.${formName}.socKementerianTanah.value = "";
	document.${formName}.socAgensiTanah.value = "";	
	document.${formName}.txtNoPegangan.value = "";
	document.${formName}.socJenisHakmilikC.value = "";
	document.${formName}.txtNoHakmilik.value = "";	
	document.${formName}.txtNoWarta.value = "";
	document.${formName}.txtNoLot.value = "";		
	document.${formName}.socNegeriC.value = "";
	document.${formName}.socDaerahC.value = "";
	document.${formName}.socMukimC.value = "";	
	document.${formName}.txtKegunaanTnh.value = "";	
	document.${formName}.socStatusC.value = "";
	document.${formName}.socLotC.value = "";
	doAjaxCall${formName}("");
}
function carian(){
	document.${formName}.submit();
}
function kosongkan(flagDetail) {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtPemohon.value = "";
	document.${formName}.socStatusC.value = "";
	document.${formName}.socKementerianTanah.value = "";
	document.${formName}.socAgensiTanah.value = "";	
	if (flagDetail == 'buka'){
		document.${formName}.txtNoPegangan.value = "";
		document.${formName}.socJenisHakmilikC.value = "";
		document.${formName}.txtNoHakmilik.value = "";	
		document.${formName}.txtNoWarta.value = "";
		document.${formName}.socLotC.value = "";
		document.${formName}.txtNoLot.value = "";		
		document.${formName}.socNegeriC.value = "";
		document.${formName}.socDaerahC.value = "";
		document.${formName}.socMukimC.value = "";		
		document.${formName}.txtKegunaanTnh.value = "";	
	}
	doAjaxCall${formName}("");
}
function papar(idFail,idStatus) {

	document.${formName}.idFail.value = idFail;

	if (idStatus == '1610198'){
	document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBMaklumatPermohonanView";
	} else if (idStatus == '1610199'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBJabatanTeknikalView";
	} else if (idStatus == '1610200'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBLawatanTapakView";
	}  else if (idStatus == '1610201'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBMesyuaratView";
	}	else if (idStatus == '1610215'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBSuratPencerobohView";
	}  else if (idStatus == '1610216'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBSuratTindakanView";
	}	else if (idStatus == '1610217'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBOperasiPenguatkuasaanView";
	}
	
	document.${formName}.submit();
}
</script>
