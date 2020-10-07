<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
.style2 {
	color: #FF0000;
	font-weight: bold;
}
.blink {
	animation: blink 1s steps(5, start) infinite;
}
@keyframes blink {
 to {
 visibility: hidden;
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="flagDetail" type="hidden" id="flagDetail" value="$flagDetail"/>
  <input name="mode" type="hidden" id="mode" />
  <input name="hit" type="hidden" id="hit"/>
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
            #end 
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama Penyewa : </td>
          <td width="70%"><input name="txtNamaPemohon" id="txtNamaPemohon" type="text" value="$txtNamaPemohon" size="50" maxlength="50" style="text-transform:uppercase;">
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">No. Siri Perjanjian : </td>
          <td width="70%"><input name="txtNoRujukan" id="txtNoRujukan" type="text" value="$txtNoRujukan" size="50" maxlength="50" style="text-transform:uppercase;">
          </td>
        </tr>
        <tr>
          <td scope="row" align="right"> Nama Bank :</td>
          <td>$selectBankC</td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">No. Cek : </td>
          <td width="70%"><input name="txtNoCek" id="txtNoCek" type="text" value="$txtNoCek" size="50" maxlength="50" style="text-transform:uppercase;">
          </td>
        </tr>
        <tr>
          <td scope="row" align="right">No. Resit :</td>
          <td><input name="txtNoResit" id="txtNoResit" type="text" value="$!txtNoResit" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right"> Jenis Fail :</td>
          <td><select name="socJenisFailC" id="socJenisFailC" style="width:260px;">
              <option $selected value="">SILA PILIH</option>
              <option $selectedS value="S">SEWA TERTUNGGAK</option>
              <option $selectedD value="D">DEPOSIT TIDAK DITUNTUT</option>
            </select></td>
        </tr>
        <tr>
          <td scope="row" align="right"> Status Perjanjian :</td>
          <td>$selectStatusPerjanjian</td>
        </tr>
        <tr>
          <td scope="row" align="right" valign="top"> Tujuan Penyewaan :</td>
          <td>
		  	<textarea name="txtTujuan" id="txtTujuan" rows="5" cols="50" onBlur="this.value=this.value.toUpperCase();" 
		  		onKeyUp="textCounter(this.form.txtTujuan,this.form.remLen,$!saizTxtTujuan);" 
		  		onKeyDown="textCounter(this.form.txtTujuan,this.form.remLen,$!saizTxtTujuan);" >$!txtTujuan</textarea>
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
          <td>$selectJenisLot</td>
        </tr>
        <tr>
          <td scope="row" align="right">No. Lot / No. PT:</td>
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
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan('$flagDetail')">
            <input type="hidden" name="idHasil" />
            <input type="hidden" name="actionHasil"/>
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
        <tr>
          <td colspan="8" scope="row"><input name="cmdJanaPenerimaan" type="button" value="Jana Borang Penerimaan Bayaran" onclick="javascript:janaBorangDaftarMelSewa()"/>
            <input name="cmdJana" type="button" value="Jana Borang Penyerahan" onclick="javascript:janaBorangPenyerahan()"/>
            <input name="cmdCetak" type="button" value="Cetak Senarai Fail" onclick="javascript:cetakSenaraiFail('$flagDetail')"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%" align="center"><strong>No Fail</strong></td>
          <td width="6%" align="center"><strong>Jenis Penyewaan</strong></td>
          <td width="20%" align="center"><strong>Nama Penyewa</strong></td>
          <td width="18%" align="center"><strong>Maklumat Lot</strong></td>         
          <td width="5%" align="center"><strong>Tarikh Mula</strong></td>
          <td width="5%" align="center"><strong>Tarikh Tamat</strong></td>
          <td width="7%" align="center"><strong>Kadar Sewa (RM)</strong></td>
          <td width="7%" align="center"><strong>Tunggakan (RM)</strong></td>
          <td width="10%" align="center"><strong>Status Perjanjian</strong></td>
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
          <td class="$row" align="center">$list.bil </td>
          <td class="$row"><a href="javascript:papar('$list.idHasil')" class="style1">$list.noFail</a> &nbsp;&nbsp;
            #if ($!list.statusTunggakan != "") <font class="blink" ><span class="style2">$list.statusTunggakan</span></font> <br/>
            #end
            #if ($!list.status != "") <font class="blink" ><span class="style2">$list.status</span></font> <br/>
            #end
            #if ($!list.statusDeposit != "") <font class="blink" ><span class="style2">$!list.statusDeposit</span></font> <br />
            #end </td>
          <td class="$row" align="center">$list.jenisPenyewaan</td>
          <td class="$row">$list.namaPemohon</td>
          <td class="$row">$list.maklumatLot</td>           
          <td class="$row" align="center">$list.tarikhMula</td>
          <td class="$row" align="center">$list.tarikhTamat</td>
          <td class="$row" align="right">$!list.kadarSewa</td>
          <td class="$row" align="right">$!list.tunggakan</td>          
          #if ($!list.flagStatusPerjanjian == '1')
          <td align="center" class="$row"><strong><span class="style1">$list.statusPerjanjian</span></strong></td>
          #else
          <td align="center" class="$row"><strong><span class="style2">$list.statusPerjanjian</span></strong></td>
          #end
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td colspan="7" class="row1">Tiada Rekod</td>
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
function carian(){
	document.${formName}.actionHasil.value = "";
	doAjaxCall${formName}("");
}
function kosongkan(flagDetail) {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNamaPemohon.value = "";
	document.${formName}.txtNoRujukan.value = "";
	document.${formName}.socBankC.value = "";
	document.${formName}.txtNoCek.value = "";
	document.${formName}.txtNoResit.value = "";	
	document.${formName}.socJenisFailC.value = "";	
	document.${formName}.socStatusPerjanjianC.value = "";	
	document.${formName}.txtTujuan.value = "";	
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
		document.${formName}.socKementerianC.value = "";
		document.${formName}.socAgensiC.value = "";	
	}
	doAjaxCall${formName}("");
}
function papar(idHasil) {
	document.${formName}.actionHasil.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.idHasil.value = idHasil;
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
	document.${formName}.txtNoRujukan.value = "";
	document.${formName}.socBankC.value = "";
	document.${formName}.txtNoCek.value = "";
	document.${formName}.txtNoResit.value = "";	
	document.${formName}.socJenisFailC.value = "";	
	document.${formName}.socStatusPerjanjianC.value = "";	
	document.${formName}.txtTujuan.value = "";	
	document.${formName}.txtNoPegangan.value = "";
	document.${formName}.socJenisHakmilik.value = "";
	document.${formName}.txtNoHakmilik.value = "";	
	document.${formName}.txtNoWarta.value = "";
	document.${formName}.socJenisLot.value = "";
	document.${formName}.txtNoLot.value = "";		
	document.${formName}.socNegeriC.value = "";
	document.${formName}.socDaerahC.value = "";
	document.${formName}.socMukimC.value = "";	
	document.${formName}.socKementerianC.value = "";
	document.${formName}.socAgensiC.value = "";	
	doAjaxCall${formName}("");
}

function janaBorangPenyerahan() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?report=BorangPenyerahanSewa";
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function janaBorangDaftarMelSewa() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?report=BorangDaftarMelSewa";
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSenaraiFail(flagDetail){	
	
var url = "../servlet/ekptg.report.php2.REVSenaraiFail?noFail="+document.${formName}.txtNoFail.value+"&namaPemohon="+document.${formName}.txtNamaPemohon.value+"&noRujukan="+document.${formName}.txtNoRujukan.value+"&idBank="+document.${formName}.socBankC.value+"&noCek="+document.${formName}.txtNoCek.value+"&jenisFail="+document.${formName}.socJenisFailC.value+"&statusPerjanjian="+document.${formName}.socStatusPerjanjianC.value+"&noResit="+document.${formName}.txtNoResit.value;
	
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
