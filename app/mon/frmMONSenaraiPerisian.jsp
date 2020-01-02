<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
.style2 {
	color: #FF0000;
	font-weight: bold;
}
.style3 {
	color: #FF0000
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="idPerisian" />
  <input type="hidden" name="actionMonitoring" />
  <input type="hidden" name="mode" />
  <input type="hidden" name="modePopup" />
  <input name="hitButton" type="hidden" id="hitButton"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">Kategori : </td>
          <td width="70%">$selectKategori</td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama Lesen : </td>
          <td width="70%"><input name="txtNamaPerisian" id="txtNamaPerisian" type="text" value="$txtNamaPerisian" size="50" maxlength="50" style="text-transform:uppercase;" >
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama Pengeluar : </td>
          <td width="70%"><input name="txtNamaPengeluar" id="txtNamaPengeluar" type="text" value="$txtNamaPengeluar" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nombor Siri : </td>
          <td width="70%"><input name="txtNoSiri" id="txtNoSiri" type="text" value="$txtNoSiri" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Aktif : </td>
          <td width="70%"><input type="text" name="txdTarikhAktif" id="txdTarikhAktif" value="$txdTarikhAktif" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhAktif',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Luput : </td>
          <td width="70%"><input type="text" name="txdTarikhLuput" id="txdTarikhLuput" value="$txdTarikhLuput" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhLuput',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Status : </td>
          <td width="70%">$selectStatus</td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Negeri : </td>
          <td width="70%">$selectNegeri</td>
        </tr>
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
      <legend><b>SENARAI LESEN</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="7" scope="row"><input name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaru()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>Nama Lesen</strong></td>
          <td width="15%"><strong>Nama Pengeluar</strong></td>
          <td width="10%"><strong>Nombor Siri</strong></td>
          <td width="10%" align="center"><strong>Tarikh Aktif</strong></td>
          <td width="10%" align="center"><strong>Tarikh Luput</strong></td>
          <td width="10%"><strong>Kategori</strong></td>
          <td width="15%"><strong>Negeri</strong></td>
          <td width="5%"><strong>Status</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiPerisian.size() > 0)
        #foreach ($list in $SenaraiPerisian)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idPerisian')" class="style1">$list.namaPerisian</a>&nbsp;&nbsp; #if ($list.bilHari != '') <span class="style2"><blink>$list.bilHari HARI LAGI</blink></span> #end</td>
          <td class="$row">$list.namaPengeluar</td>
          <td class="$row">$list.noSiri</td>
          <td class="$row" align="center">$list.tarikhAktif</td>
          <td class="$row" align="center">$list.tarikhLuput</td>
          <td class="$row">$list.kategori</td>
          <td class="$row">$list.negeri</td>
          #if ($list.flagAktif == 'T')
          <td class="$row style3"><strong>$list.status</strong></td>
          #else
          <td class="$row"><strong>$list.status</strong></td>
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
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function carian(){
	document.${formName}.actionMonitoring.value = "";
	document.${formName}.hitButton.value = "cari";
	doAjaxCall${formName}("");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNamaPerisian.value = "";
	document.${formName}.txtNamaPengeluar.value = "";
	document.${formName}.txtNoSiri.value = "";
	document.${formName}.txdTarikhAktif.value = "";
	document.${formName}.txdTarikhLuput.value = "";	
	document.${formName}.socKategori.value = "";
	document.${formName}.socStatus.value = "";
	document.${formName}.socNegeri.value = "";
	
	doAjaxCall${formName}("");
}
function papar(idPerisian) {

	document.${formName}.idPerisian.value = idPerisian;
	document.${formName}.actionMonitoring.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function daftarBaru(){
	document.${formName}.actionMonitoring.value = "daftarBaru";
	document.${formName}.submit();
}
</script>
