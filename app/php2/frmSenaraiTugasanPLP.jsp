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
            #if($flagDetail == '') <a href="javascript:bukaCarian();" class="style1">Buka Carian Terperinci </a> #else <a href="javascript:tutupCarian();" class="style1">Tutup Carian Terperinci </a> #end </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tajuk Fail : </td>
          <td width="70%"><input name="txtTajukFail" id="txtTajukFail" type="text" value="$txtTajukFail" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Terima : </td>
          <td width="70%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$txdTarikhTerima" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td scope="row" align="right">Suburusan :</td>
          <td>$selectSubUrusanPelepasanMain</td>
        </tr>
        #if($idSuburusan != '99999')
        <tr>
          <td scope="row" align="right">Status :</td>
          <td>$selectStatus</td>
        </tr>
        #end
        <tr>
          <td scope="row" align="right">Jenis Fail :</td>
          <td>
          	<select name="socJenisFailC" id="socJenisFailC" style="width:260px;">
              <option $selected value="">SILA PILIH</option>
              <option $selected1 value="K">ULASAN KJP TAMAT TEMPOH</option>
              <option $selected2 value="G">ULASAN JKPTG TAMAT TEMPOH</option>
            </select>
          </td>
        </tr>
        #if($flagDetail == 'buka')
        <tr>
          <td scope="row" align="right">No. Pegangan Hakmilik :</td>
          <td><input name="txtNoPegangan" id="txtNoPegangan" type="text" value="$txtNoPegangan" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right"> Jenis Hakmilik :</td>
          <td>$selectJenisHakmilik</td>
        </tr>
        <tr>
          <td scope="row" align="right"> No. Hakmilik :</td>
          <td><input name="txtNoHakmilik" id="txtNoHakmilik" type="text" value="$txtNoHakmilik" size="30" maxlength="50" /></td>
        </tr>
        <tr>
          <td scope="row" align="right"> No. Warta :</td>
          <td><input name="txtNoWarta" id="txtNoWarta" type="text" value="$txtNoWarta" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right">Jenis Lot :</td>
          <td>$selectLot</td>
        </tr>
        <tr>
          <td scope="row" align="right">No. Lot :</td>
          <td><input name="txtNoLot" id="txtNoLot" type="text" value="$txtNoLot" size="30" maxlength="50"/></td>
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
          <td scope="row" align="right">Kementerian :</td>
          <td>$selectKementerian</td>
        </tr>
        <tr>
          <td scope="row" align="right">Agensi :</td>
          <td>$selectAgensi</td>
        </tr>
        #end
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
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
        <tr>
          <td colspan="7" scope="row"></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>No Fail</strong></td>
          <td width="35%"><strong>Tajuk Fail</strong></td>
          <td width="10%"><strong>Negeri</strong></td>
          <td width="8%" align="center"><strong>Tarikh Terima</strong></td>
          <td width="8%" align="center"><strong>Tarikh Buka Fail</strong></td>
          <td width="10%"><strong>Sub Urusan</strong></td>
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
          <td class="$row"><a href="javascript:papar('$list.idFail','$list.idStatus','$list.idSubUrusan')" class="style1">$list.noFail</a></td>
          <td class="$row">$list.tajukFail</td>
          <td class="$row">$list.namaNegeri</td>
          <td class="$row" align="center">$list.tarikhTerima</td>
          <td class="$row" align="center">$list.tarikhBukaFail</td>
          <td class="$row">$list.namaSubUrusan</td>
          <td class="$row">$list.status</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
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
function doChangeSubUrusan() {
	doAjaxCall${formName}("doChangeSubUrusan");
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function bukaCarian(){
	document.${formName}.flagDetail.value = "buka";
	doAjaxCall${formName}("");
}
function tutupCarian(){
	document.${formName}.flagDetail.value = "";
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtTajukFail.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.txtNoPegangan.value = "";
	document.${formName}.socJenisHakmilikC.value = "";
	document.${formName}.txtNoHakmilik.value = "";	
	document.${formName}.txtNoWarta.value = "";
	document.${formName}.socLotC.value = "";
	document.${formName}.txtNoLot.value = "";		
	document.${formName}.socNegeriC.value = "";
	document.${formName}.socDaerahC.value = "";
	document.${formName}.socMukimC.value = "";	
	document.${formName}.socSubUrusan.value = "";
	document.${formName}.socKementerianC.value = "";	
	document.${formName}.socAgensiC.value = "";		
	doAjaxCall${formName}("");
}
function carian(){
	doAjaxCall${formName}("");
}
function kosongkan(flagDetail) {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtTajukFail.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.socSubUrusan.value = "";
	document.${formName}.socJenisFailC.value = "";
	if (flagDetail == 'buka'){
		document.${formName}.socStatusC.value = "";
		document.${formName}.txtNoPegangan.value = "";
		document.${formName}.socJenisHakmilikC.value = "";
		document.${formName}.txtNoHakmilik.value = "";	
		document.${formName}.txtNoWarta.value = "";
		document.${formName}.socLotC.value = "";
		document.${formName}.txtNoLot.value = "";		
		document.${formName}.socNegeriC.value = "";
		document.${formName}.socDaerahC.value = "";
		document.${formName}.socMukimC.value = "";	
		document.${formName}.socKementerianC.value = "";	
		document.${formName}.socAgensiC.value = "";			
	}
	doAjaxCall${formName}("");
}
function papar(idFail,idStatus,idSubUrusan) {

	document.${formName}.idFail.value = idFail;

	if(idSubUrusan == "34") {
		if (idStatus == '1610198'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPMaklumatPermohonanView";
		} else if (idStatus == '1610199'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPJabatanTeknikalView";
		}else if (idStatus == '1610200'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPLawatanTapakView";
		}else if (idStatus == '1610201'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPMesyuaratView";
		}else if (idStatus == '1610202'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPCetakanMinitKewanganView";
		}else if (idStatus == '1610203'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPKemasukanMinitKewanganView";
		}else if (idStatus == '1610204'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPCetakanMinitCeraianView";
		}else if (idStatus == '1610205'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPKelulusanMenteriView";
		}else if (idStatus == '1610206'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPKeputusanView";
		}else if (idStatus == '1610207'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPKeputusanView";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPMaklumatPermohonanView";
		} 
	} else if(idSubUrusan == "32") {
		
		if (idStatus == '1610198'){
			document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWMaklumatPermohonanView";
		} else if (idStatus == '1610199'){
			document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWJabatanTeknikalView";
		}else if (idStatus == '1610200'){
			document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWLawatanTapakView";
		}else if (idStatus == '1610210'){
			document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWTawaranView";
		}else if (idStatus == '1610201'){
			document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWMesyuaratView";
		}else if (idStatus == '1610206'){
			document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWKeputusanView";
		} else if (idStatus == '1610206'){
			document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWMaklumatPermohonanView";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.FrmPNWMaklumatPermohonanView";
		} 
	} else if(idSubUrusan == "33") {
	
		if (idStatus == '1610198'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRMaklumatPermohonanView";
		} else if (idStatus == '1610199'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRJabatanTeknikalView";
		}else if (idStatus == '1610200'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRLawatanTapakView";
		}else if (idStatus == '1610201'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRMesyuaratView";
		}else if (idStatus == '1610202'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRCetakanMinitKewanganView";
		}else if (idStatus == '1610203'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRKemasukanMinitKewanganView";
		}else if (idStatus == '1610204'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRCetakanMinitCeraianView";
		}else if (idStatus == '1610205'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRKelulusanMenteriView";
		}else if (idStatus == '1610206'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRKeputusanView";
		}else if (idStatus == '1610208'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRKeputusanView";
		}else if (idStatus == '1610191'){
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRMesyuaratEPUView";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRMaklumatPermohonanView";
		} 
	}
	document.${formName}.submit();
}
</script>
