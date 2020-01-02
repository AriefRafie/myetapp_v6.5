	#parse ("app/htp/frmSewaanInfo.jsp")			

<fieldset>
  <legend><strong>Senarai Hakmilik</strong></legend>
  <!--<form name="tbh" method="post">
  	<input name="add" value="Tambah" type="button" onClick="tambahPermohonan()">
  </form>-->
  <form name="f2" method="post">
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr class="table_header">
  	<td>No</td>
  	<td>No. Hakmilik</td>
  	<td>No. Lot</td>
  	<td></td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraihakmilik )
  	#set ( $cnt = $cnt + 1 )

  <tr>
  <td>$cnt.</td>
  <td>
  	<a href="javascript:kemaskiniHakmilik('$senarai.idhakmilikurusan','$permohonanInfo.idpermohonan')">
	$senarai.nohakmilik
	</a>
  </td>
  <td>$senarai.nolot</td>
  <td></td>
  </tr>
  #end
  </table>
  </fieldset>
  <fieldset>
  <legend><strong>Senarai Pemilik</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr class="table_header">
  	<td>No</td>
  	<td>Nama Pemilik</td>
  	<td>No. KP/Polis/Tentera/Syarikat</td>
  	<td></td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraipemilik )
  	#set ( $cnt = $cnt + 1 )

  <tr>
  <td>$cnt.</td>
  <td>
  	<a href="javascript:senaraiPermohonan('$senarai.id')">
	$senarai.norujukan
	</a>
  </td>
  <td>$senarai.nama</td>
  <td></td>
  </tr>
  #end
  </table>
  
        <tr>
		<td colspan="2" align="center">
			<input type="submit" name="cmdSimpan" id="cmdSimpan" value="Tambah" onclick="tambahHakmilik('$permohonanInfo.idpermohonan')">&nbsp
			<!--<input type="reset" name="cmdBatal" id="cmdBatal" value="Batal">&nbsp;-->
			<input type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti('$permohonanInfo.idpermohonan')">&nbsp;
			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="backPre('$permohonanInfo.idfail')">&nbsp;
			<input type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="backMain()">&nbsp;
		</td>
      </tr>
    <input type="hidden" name="command">
   	<input type="hidden" name="id_kemaskini">
    <input type="hidden" name="fail" >
  </form>
</fieldset>

<script>
function nexti(i) {
	document.f2.id_kemaskini.value = i;
	document.f2.command.value = "pemilikseterus";
	document.f2.action = "";
	document.f2.submit();
}

function cancel() {
	//document.setup.buttonsubmit.value = "ADD";
	document.cari.reset();
	//document.forms[0].kod_agensi.focus();
}

function kemaskiniHakmilik(idh,id) {
	document.f2.fail.value = idh;
	document.f2.id_kemaskini.value = id;
	document.f2.command.value = "pemiliktambah";
	document.f2.action = "";
	document.f2.submit();
}

function tambahHakmilik(id) {
	document.f2.id_kemaskini.value = id;
	document.f2.command.value = "pemiliktambah";
	document.f2.action = "";
	document.f2.submit();
}

function backPre(id) {
	document.f2.fail.value = id;
	document.f2.command.value = "pkpermohonan";
	document.f2.action = "";
	document.f2.submit();
}

function backMain() {
	document.f2.action = "";
	document.f2.submit();
}
</script>

<script language="JavaScript"> document.forms[0].no_fail.focus(); </script>