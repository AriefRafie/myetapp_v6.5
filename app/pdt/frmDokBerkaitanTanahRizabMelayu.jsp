<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<br />

<table width="100%">
<tr>
	<td width="5"></td>
	<td align="left"> <strong>DOKUMEN BERKAITAN TANAH RIZAB MELAYU</strong></td>
</tr>
</table>
<input name="mode" type="hidden" value="" />
  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
     <!-- <tr>
      <td align="right" scope="row">Jenis Dokumen</td>
      <td scope="row">:</td>
     <td>
	      <label>
	        <input name="txtNoRujDokuman" type="text" id="txtNoRujDokuman" size="44" maxlength="50"/>
	      </label>
      </td> -->
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row">Tajuk Dokumen</td>
      <td width="1%" scope="row" valign="top" >:</td>
      <td width="70%"><label>
        <textarea name="txtTajukDokumen" cols="41" id="txtTajukDokumen">$tajukDokumen</textarea>
      </label></td>
    </tr>
    <!-- <tr>
      <td align="right" scope="row">Seksyen / Urusetia</td>
      <td scope="row">:</td>
      <td>$selectSeksyen</td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Dokumen</td>
      <td scope="row">:</td>
      <td><label>
        <input name="txdTarikhDokumen" type="text" id="txdTarikhDokumen" value="$tarikhDokumen" size="10" />
      <a href="javascript:displayDatePicker('txtTarikhDokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </label></td>
    </tr> -->
    <tr>
      <td colspan="2" align="right" scope="row">&nbsp;</td>
      <td><label>
        <input type="submit" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()" />
        </label>
          <label>
          <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label></td>
    </tr>
  </table>
</fieldset>
  <fieldset>

  <legend><strong>Senarai Dokumen</strong></legend>
  <p>
    <label>
      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()" />
    </label>
     #parse("app/utils/record_paging.jsp") 
  </p>
  <table width="100%" >
    <tr class="table_header">
      <td width="1%" scope="row"><strong>Bil.</strong></td>
      <td width="10%"><strong>Jenis Dokumen</strong></td>
      <td width="34%"><strong>Tajuk Dokumen</strong></td>
      <td width="10%"><strong>Tarikh Dokumen</strong></td>
      <td></td>
    </tr>
    #if($SenaraiFail.size() > 0)
    #foreach ($dokumen in $SenaraiFail)
    #if ($dokumen.bil == '') 
  		#set ($row = 'row1')
    #elseif ($dokumen.bil % 2 != 0)
  	   	#set ($row = 'row1')
  	#else 
  		#set ($row = 'row2')
  	#end 
    <tr>
      <td class="$row">$dokumen.bil.</td>
      <td class="$row">
       #if ($dokumen.bil != '') 
      	<a href="javascript:edit_item('$dokumen.idDokumen')" class="style1">$dokumen.jenisDokumen</a>
       #end      
      </td>
      <td class="$row"><a href="javascript:edit_item('$dokumen.idDokumen')" class="style1">$dokumen.tajukDokumen</a></td>
      <td class="$row" width="10%">$dokumen.tarikhDokumen</td>
      <td class="$row" width="2%"><input type="button" name="cmdDelete" id="cmdDelete" value="Hapus" onclick="javascript:deleteDokumen('$dokumen.idDokumen')" />
	</td>
	<!--       <a alt="Hapus Dokumen" href = "javascript:deleteDokumen('$dokumen.idDokumen')">
	&nbsp;&nbsp;<img border="0" src="../img/delete.gif" /></a> 
 -->
    </tr>
  	#end 
  	#else
	<tr>
		<td colspan="6">Rekod Tidak Dijumpai</td>
	</tr>
  	#end
  </table>

</fieldset>
 <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-12</strong></td>
  	</tr>
  </table>
<script>

function tambah(){
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.command.value = "tambahDataBaru";
		document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "tambahDataBaru";
		document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.submit();

}
	function carian(){
		doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("","action=");
	}
	
function kosongkan(){
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.reset();
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.txtNoRujDokuman.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.txtTajukDokumen.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.txdTarikhDokumen.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.socSeksyen.value = "";
	
}
function edit_item(id){
	//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "papar";
	//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.mode.value = "";
	//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.idDokumen.value = id;
	//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.submit();
	doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("papar","action=papar&idDokumen="+id);

}
function viewDokumenBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=akta&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function kembali(){

	//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "SenaraiDokumen";
	//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.mode.value = "";
	//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.submit();
	doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("SenaraiDokumen","action=SenaraiDokumen");
}
function hapus(id){
if ( !window.confirm("Anda Pasti?") ) return;
	/*
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "hapus";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.idLampiran.value = id;
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.mode.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.submit();
	*/
	//alert("idLampiran:"+id);
	doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("hapus","action=hapus&idLampiran="+id);
}
function batal(){
	
	if (document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.mode.value == "Update"){
	
		//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "papar";
		doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("papar","action=papar");
	}
	else if (document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.mode.value == "New" || document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.mode.value == "View"){
		//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "SenaraiDokumen";
		doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("SenaraiDokumen","action=SenaraiDokumen");
	}
	
	//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.submit();
}
function doChangeSeksyen() {
 	doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("doChangeSeksyen");
}
function Xtambah(){
	
	/*
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "tambahDataLain";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.idDokumen.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.txtNoRujDokumen.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.txtTajukDokumen.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.socJenisDokumen.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.txdTarikhDokumen.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.socSeksyen.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.socFail.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.txtCatatan.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.mode.value = "";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.submit();	
	*/
	doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("","action=tambahDataLain&mode=");
}
	function kemaskini(){	
		document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "kemaskini";
		document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.mode.value = "";
		document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.command.value = "kemaskini";
		document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action = "?_portal_module=ekptg.view.pdt.FrmDokBerkaitanTanahRizabMelayu";
		document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.submit();
		//doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("","action=kemaskini&mode=");
		
	}
	
function papar_Lampiran(id) {
	    var url = "../servlet/ekptg.view.pdt.DisplayBlob?id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}
function refreshValue() {
    //document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "papar";
    //doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("doRefresh","action=papar");
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action = "?_portal_module=ekptg.view.pdt.FrmDokBerkaitanTanahRizabMelayu&action=papar";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.submit();	
}


function tambahLampiran(id,sendCommand){
	var url = "../x/${securityToken}/ekptg.view.pdt.FrmTambahLampiran?idDokumen="+id+"&sendCommand="+sendCommand;
	var hWnd = window.open(url,'printuser','width=800,height=400, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}
function deleteDokumen(idDokumen) {
    if ( !window.confirm("Dokumen akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
    doAjaxCall${formName}("Delete","action=Delete&idDokumen="+idDokumen);
}

</script>
<script>
//alert("ssssss");
//alert("sssssss"+'$current_role');

$jquery(document).ready(function () {

	//if('$current_role'=="(PDT)HQPengguna" || '$current_role'=="(PDT) Pengguna Bahagian Strata")
	if('$current_role'=="(PDT)HQPengguna")
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		//$jquery("input[type=button]").hide();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		$jquery("input[type=button]").show();
	}
	else
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		//$jquery("input[type=button]").show();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		$jquery("input[type=button]").hide();
	}
});
</script>
