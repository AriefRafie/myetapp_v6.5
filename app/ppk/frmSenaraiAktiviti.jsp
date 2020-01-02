<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="actionAktiviti">
  <input type="hidden" name="idAktiviti">
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama Aktiviti : </td>
          <td width="70%"><input name="txtNamaAktivitiC" id="txtNamaAktivitiC" type="text" value="$txtNamaAktivitiC" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Mula : </td>
          <td width="70%"><input type="text" name="txdTarikhMulaDariC" id="txdTarikhMulaDariC" value="$txdTarikhMulaDariC" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhMulaDariC',false,'dmy');"><img border="0" src="../img/calendar.gif"/> &nbsp;&nbsp; Hingga &nbsp;&nbsp;
            <input type="text" name="txdTarikhMulaHinggaC" id="txdTarikhMulaHinggaC" value="$txdTarikhMulaHinggaC" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhMulaHinggaC',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Bulan : </td>
          <td width="70%">$selectBulan</td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tahun : </td>
          <td width="70%">$selectTahun</td>
        </tr>
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()"></td>
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
      <legend><b>SENARAI AKTIVITI</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="6" scope="row"><input name="cmdDaftar" type="button" value="Daftar Aktiviti Baru" onclick="javascript:daftarBaru()"/>
            <input name="cmdCetak" type="button" value="Cetak" onclick="javascript:cetak('$userId')"/>
            &nbsp;&nbsp;</td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="35%"><strong>Nama Aktiviti</strong></td>
          <td width="30%"><strong>Lokasi Aktiviti</strong></td>
          <td width="10%" align="center"><strong>Tarikh Mula</strong></td>
          <td width="10%" align="center"><strong>Tarikh Tamat</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiAktiviti.size() > 0)
        #foreach ($list in $SenaraiAktiviti)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idAktiviti')" class="style1">$list.namaAktiviti</a></td>
          <td class="$row">$list.lokasiAktiviti</td>
          <td class="$row" align="center">$list.tarikhMula</td>
          <td class="$row" align="center">$list.tarikhTamat</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function carian(){
	document.${formName}.actionAktiviti.value = "";
	doAjaxCall${formName}("");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNamaAktivitiC.value = "";
	document.${formName}.txdTarikhMulaDariC.value = "";
	document.${formName}.txdTarikhMulaHinggaC.value = "";
	document.${formName}.socBulan.value = "";
	document.${formName}.socTahun.value = "";
	doAjaxCall${formName}("");
}
function papar(idAktiviti) {
	document.${formName}.idAktiviti.value = idAktiviti;
	document.${formName}.actionAktiviti.value = "papar";
	document.${formName}.submit();
}
function daftarBaru(){
	document.${formName}.actionAktiviti.value = "daftarBaru";
	document.${formName}.submit();
}
function cetak(userId){

	if(document.${formName}.socBulan.value == ""){
		alert('Sila pilih Bulan dan Tahun terlebih dahulu.');
  		document.${formName}.socBulan.focus(); 
		return; 
	}
	if(document.${formName}.socTahun.value == ""){
		alert('Sila pilih Bulan dan Tahun terlebih dahulu.');
  		document.${formName}.socTahun.focus(); 
		return; 
	}
	
	var url = "../servlet/ekptg.report.ppk.LaporanAktiviti?userid="+userId+"&bulan="+document.${formName}.socBulan.value+"&tahun="+document.${formName}.socTahun.value;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
}
</script>
