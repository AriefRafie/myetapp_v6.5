<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  	<input name="actionPelepasan" type="hidden" id="actionPelepasan" value="$actionPelepasan"/>
  	<input name="mode" type="hidden" id="mode" value="$mode"/>
	<input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  	<input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  	<input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  	<input name="hitButton" type="hidden" id="hitButton"/>
  	<input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  	<input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  	<input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  	<input name="idHakmilikPermohonan" type="hidden" id="idHakmilikPermohonan" value="$idHakmilikPermohonan"/>
  	<input name="idTanahGanti" type="hidden" id="idTanahGanti" value="$idTanahGanti"/>
  	<input name="idNegeriTanah" type="hidden" id="idNegeriTanah" value="$idNegeriTanah"/>
  	<input name="flagJenisTanah" type="hidden" id="flagJenisTanah" value="$flagJenisTanah"/>
  	<input name="step" type="hidden" id="step" value="$step"/>
  	<input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus"/>
	<input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
    <input name="changeDropdown" type="hidden" id="changeDropdown" value="$changeDropdown"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPLPHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '' && $idStatus != '1610198')
  <tr>
    <td><fieldset>
      <legend>TANAH DIPOHON</legend>
      <table align="center" width="100%">

        #set ($list = "")
        #if ($SenaraiTanahPohon.size() > 0)
        #foreach ($list in $SenaraiTanahPohon)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else
        #set( $row = "row2" )
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          #if($list.noWarta != "")
          <td width="10%"><strong>No Warta</strong></td>
          #else
          <td width="10%"><strong>No Hakmilik</strong></td>
          <td width="15%"><strong>Pegangan Hakmilik</strong></td>
          #end
          <td width="10%"><strong>Lot</strong></td>
          <td width="20%"><strong>Mukim</strong></td>
          <td width="15%"><strong>Daerah</strong></td>
          <td width="15%"><strong>Negeri</strong></td>
        </tr>
        <tr>
          <td class="$row" align="center">$list.bil</td>
          #if($list.noWarta != "")
          <td class="$row"><a href="javascript:paparTanahPohon($list.idHakmilikPermohonan)" class="style2">$list.noWarta</a></td>
          #else
          <td class="$row"><a href="javascript:paparTanahPohon($list.idHakmilikPermohonan)" class="style2">$list.hakmilik</a></td>
          <td class="$row">$list.peganganHakmilik</td>
          #end
          <td class="$row">$list.lot</td>
          <td class="$row" >$list.mukim</td>
          <td class="$row">$list.daerah</td>
          <td class="$row">$list.negeri</td>
        </tr>
        #end
        #else

        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>Pegangan Hakmilik</strong></td>
          <td width="10%"><strong>Lot</strong></td>
          <td width="10%"><strong>No Hakmilik</strong></td>
          <td width="10%"><strong>No Warta</strong></td>
          <td width="20%"><strong>Mukim</strong></td>
          <td width="15%"><strong>Daerah</strong></td>
          <td width="15%"><strong>Negeri</strong></td>
        </tr>
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
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
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend>TANAH GANTI</legend>
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>Pegangan Hakmilik</strong></td>
          <td width="10%"><strong>Lot</strong></td>
          <td width="10%"><strong>No Hakmilik</strong></td>
          <td width="10%"><strong>No Warta</strong></td>
          <td width="20%"><strong>Mukim</strong></td>
          <td width="15%"><strong>Daerah</strong></td>
          <td width="15%"><strong>Negeri</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiTanahGanti.size() > 0)
        #foreach ($list in $SenaraiTanahGanti)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparTanahGanti($list.idTanahGanti)" class="style2">$list.peganganHakmilik</a></td>
          <td class="$row">$list.lot</td>
          <td class="$row">$list.hakmilik</td>
          <td class="$row">$list.noWarta</td>
          <td class="$row" >$list.mukim</td>
          <td class="$row">$list.daerah</td>
          <td class="$row">$list.negeri</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
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
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
	<!--     <td>("app/php2/frmPLPLawatanTapakDokumen.jsp") </td> -->
  </tr>
  <tr>
    <td align="center">
    	#if($idStatus == '1610200')
    	<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/>
    	<input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
    	#end
    	#if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
        #end
    </td>
  </tr>
   #end
</table>

<script>
function paparTanahPohon(id){
	document.${formName}.flagJenisTanah.value = "P";
	document.${formName}.idHakmilikPermohonan.value = id;
	document.${formName}.actionPelepasan.value = "papar";
	document.${formName}.selectedTabUpper.value = 0;
	document.${formName}.submit();
}
function paparTanahGanti(id){
	document.${formName}.flagJenisTanah.value = "G";
	document.${formName}.idTanahGanti.value = id;
	document.${formName}.actionPelepasan.value = "papar";
	document.${formName}.selectedTabUpper.value = 0;
	document.${formName}.submit();
}
function seterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
function gotoSenaraiFailKeseluruhan() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPSenaraiFailKeseluruhanView";
	document.${formName}.submit();
}
</script>
