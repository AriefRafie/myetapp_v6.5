<!--<strong> Senarai Fail Gadaian </strong>
<br><br>-->
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>

<fieldset> <legend>CARIAN</legend>

  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td height="25" align="right"><strong>No. Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NoFail" type="text" size="55" maxlength="400" value="$carianNoFail" onkeyup="this.value=this.value.toUpperCase();" /></td>
      </tr>
      <tr>
        <td height="25" align="right"><strong>Nama Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NamaFail" type="text" size="55" maxlength="300" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td height="25"  align="right"><strong>Negeri : &nbsp;&nbsp;</strong></td>
        <td>$selectNegeri</td>
      </tr>
      <tr style="display:none">
        <td  align="right"><strong>Status : &nbsp;&nbsp;</strong></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td></td>
        <td>
        	<input class="stylobutton" name="cari" value="Cari" type="button" onclick="javascript:fSFGSA_search_data()">
        <input class="stylobutton" value="Kosongkan" onclick="javascript:fSFGSA_cancel()" type="button"></td>
      </tr>
    </tbody>
  </table>


</fieldset>
<fieldset>
<legend>SENARAI FAIL</legend>

  <table border="0" cellpadding="2" cellspacing="1" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="5%"><b>NO</b></td>
        <td width="20%"><b>NO FAIL</b></td>
        <td width="17%"><b>NEGERI</b></td>
        <td width="35%"><b>NAMA FAIL</b></td>
        <td width="23%"><b>STATUS</b></td>
      </tr>	
      #set ($count = 0)
      #foreach ( $fail in $Senaraifail )
      #set ($count = $count+1)
      	#set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
      <tr>
        <td class="$row">$fail.bil</td>
        <td class="$row"><a href="javascript:fSFGSA_seterusnya('$fail.id', '$fail.no')" class="style1">$fail.no</a></td>
        <td class="$row">$fail.negeri</td>
        <td class="$row">$fail.tajuk</td>
        <td class="$row">$fail.keterangan</td>
      </tr>
      #end
      #if ($count == 0)
      <tr>
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
    </tbody>
  </table>
  <!--
  <input type="hidden" name="idFail">
  <input type="hidden" name="noFail">
  -->


</fieldset>
		</td>
	</tr>
	<tr>
                <td>
                <p align="right">CL - 04 - 0201</p>
                </td>
	</tr>  
</table>
<script language="javascript" type="text/javascript">

<!-- template name frmSenaraiFailGadaianSuratAjax.jsp -->

function fSFGSA_cancel() {
document.${formName}.reset();
document.${formName}.NamaFail.value = "";
document.${formName}.NoFail.value = "";
document.${formName}.socNegeri.value = "";
document.${formName}.NamaFail.focus();
}
function fSFGSA_search_data(){
	/*
	document.${formName}.command.value = "";
	//document.${formName}.nama_fail.value = key;
	document.${formName}.action = "";
	document.${formName}.submit();
	*/
	doAjaxCall${formName}("");
	
}
function fSFGSA_seterusnya(id, no) {
	/*
	document.${formName}.idFail.value = id;
	document.${formName}.noFail.value = no;
	document.${formName}.command.value = "SenaraiPermohonan";
	//document.${formName}.action = "?_portal_module=ekptg.htp.FrmGadaianSemakan";
	document.${formName}.action = "";
	document.${formName}.submit();
	*/
	doAjaxCall${formName}("SenaraiPermohonan","idFail="+id+"&noFail="+no);
}

<!-- end of script-->

<!-- template name frmGadaianSenaraiPermohonanSuratAjax.jsp -->

function fGSPSA_cancel() {
document.${formName}.reset();
document.${formName}.NamaPemohon.value = "";
document.forms[0].NamaPemohon.focus();
}

function fGSPSA_search_data(){
	/*
	document.${formName}.command.value = "SenaraiPermohonan";
	document.${formName}.action = "";
	document.${formName}.submit();
	*/
	doAjaxCall${formName}("SenaraiPermohonan");
	
}

function fGSPSA_Papar(idPermohonan, idNegeri) {
	doAjaxCall${formName}("PaparMenu","idNegeri="+idNegeri+"&idPermohonan="+idPermohonan);	

}

function fGSPSA_Kembali() {
	/*
	document.${formName}.command.value = "";
	document.${formName}.action = "";
	document.${formName}.submit();
	*/
	doAjaxCall${formName}("");
	
}

//document.forms[0].NamaPemohon.focus();

<!-- end of script -->


<!-- template name frmGadaianMenuSuratAjax.jsp -->

function fGMSA_PaparSurat(idPermohonan, mode, setting) {
	//document.srt.idPermohonan.value = idPermohonan;
	//document.srt.command.value = "Surat";
	//document.srt.setting.value = setting;
	//document.srt.mode.value = mode;
	//document.srt.action = "";
	//document.srt.submit();
	
	var url = "../x/${securityToken}/ekptg.view.htp.GadaianPaparSuratAjax?idPermohonan="+idPermohonan+"&mode="+mode+"&setting="+setting;
	var hWnd = window.open(url,'printuser','width=1024,height=600, resizable=yes,scrollbars=yes');
}

// 2010/02/07 -Pilih Pegawai untuk tandatangan surat
function openSuratPegawai(urli,param,laporan,tem){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

//2010/04/12 bertujuan mencetak doket atau Fail
// Dibuat oleh  : Rosli
// Dimodifikasi oleh :
function cetakFailDoket(id,temp,urlserv) {
	var param ="";
    var url = "../servlet/"+urlserv+"?"+id+temp;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>

<script language="JavaScript"> 
//	document.forms[0].NamaFail.focus(); 
	#if ($Negeri == 20)
	//	document.forms[0].socNegeri.value = "";
	#end
</script>