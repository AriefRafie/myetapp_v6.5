<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>LAPORAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="30%" align="right"><span class="style1">*</span> Kategori Laporan : </td>
          <td width="70%"><select name="idKategori" id="idKategori" onChange="javascript:doChangeKategori()">
              <option value="">SILA PILIH</option>
              <option value="1" #if($!idKategori == '1') selected #end >HARIAN</option>
              <option value="2" #if($!idKategori == '2') selected #end >BULANAN</option>
              <option value="3" #if($!idKategori == '3') selected #end >TAHUNAN</option>
            </select></td>
        </tr>
        #if($!idKategori == '1')
        <tr>
          <td align="right"><span class="style1">*</span> Tarikh : </td>
          <td><input type="text" name="tarikhMula" id="tarikhMula" value="$!tarikhMula" onblur="check_date(this);" size="9"/>
            <a href="javascript:displayDatePicker('tarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
            &nbsp; HINGGA &nbsp;
            <input type="text" name="tarikhHingga" id="tarikhHingga" value="$!tarikhHingga" onblur="check_date(this);" size="9"/>
            <a href="javascript:displayDatePicker('tarikhHingga',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
          </td>
        </tr>
        #end
        #if($!idKategori == '2')
        <tr>
          <td align="right"><span class="style1">*</span> Bulan : </td>
          <td>$!selectBulan</td>
        </tr>
        <tr>
          <td align="right"><span class="style1">*</span> Tahun : </td>
          <td><input type="text" name="tahun" id="tahun" value="$tahun" onBlur="validateNumber(this,this.value);" size="4" maxlength="4"></td>
        </tr>
        #end
        #if($!idKategori == '3')
        <tr>
          <td align="right"><span class="style1">*</span> Tahun : </td>
          <td><input type="text" name="tahun" id="tahun" value="$tahun" onBlur="validateNumber(this,this.value);" size="4" maxlength="4"></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdJana" id="button" value="Jana Laporan" onclick="janaLaporan()">
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doChangeKategori() {
	doAjaxCall${formName}("doChangeKategori");
}
</script>
<script>
function janaLaporan() {
	var idKategori = document.${formName}.idKategori.value;
	var tarikhMula = "";
	var tarikhHingga = "";
	var bulan = "";
	var tahun = "";

	if(idKategori == ""){
		alert('Sila pilih Kategori Laporan.');
  		document.${formName}.idKategori.focus();
		return;
	}

	else if (idKategori == '1') {
		tarikhMula = document.${formName}.tarikhMula.value;
		if(tarikhMula == ""){
			alert('Sila masukkan Tarikh Mula.');
  			document.${formName}.tarikhMula.focus();
			return;
		}
		tarikhHingga = document.${formName}.tarikhHingga.value;
		if(tarikhHingga == ""){
			alert('Sila masukkan Tarikh Hingga.');
  			document.${formName}.tarikhHingga.focus();
			return;
		}
		var url = "../servlet/ekptg.report.php2.REVLaporanPerjanjianPenyewaanHarian?TARIKH_MULA="+tarikhMula+"&TARIKH_HINGGA="+tarikhHingga;

	}
	else if (idKategori == '2') {
		bulan = document.${formName}.socBulan.value;
		tahun = document.${formName}.tahun.value;

		if(bulan == ""){
			alert('Sila pilih Bulan.');
  			document.${formName}.socBulan.focus();
			return;
		}

		if(tahun == ""){
			alert('Sila pilih Tahun.');
  			document.${formName}.tahun.focus();
			return;
		}

		var url = "../servlet/ekptg.report.php2.REVLaporanPerjanjianPenyewaanBulanan?BULAN="+bulan+"&TAHUN="+tahun;

	} else if (idKategori == '3') {
		tahun = document.${formName}.tahun.value;

		if(tahun == ""){
			alert('Sila pilih Tahun.');
  			document.${formName}.socTahun.focus();
			return;
		}

		var url = "../servlet/ekptg.report.php2.REVLaporanPerjanjianPenyewaanTahunan?TAHUN="+tahun;
	}

    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
