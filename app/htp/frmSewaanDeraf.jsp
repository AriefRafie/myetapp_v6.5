	#parse ("app/htp/frmSewaanInfo.jsp")			

<fieldset>
  <legend><strong>Kedudukan Fail Terkini</strong></legend>
  <!--<form name="tbh" method="post">
  	<input name="add" value="Tambah" type="button" onClick="tambahPermohonan()">
  </form>-->
  <form name=f2 method="post">
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr class="table_header">
  	<td>No</td>
  	<td>Tarikh Terima</td>
  	<td>Tarikh Hantar</td>
  	<td>Ulasan</td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraideraf )
  	#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
  <tr>
  <td class="$row">$cnt.</td>
  <td class="$row">
  	<a href="javascript:senaraiPermohonan('$senarai.no')">
	$util.getDateTime($senarai.tm , "dd/MM/yyyy")
	</a>
  </td>
  <td class="$row">$util.getDateTime($senarai.tk, "dd/MM/yyyy")</td>
  <td class="$row">$senarai.keterangan</td>
  </tr>
  #end
  </table>
  </fieldset>
   
        <tr>
		<td colspan="2" align="center">
			<input type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti()">&nbsp;
			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="back()">&nbsp;
			<input type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="back(-2)">&nbsp;
		</td>
      </tr>
        <input type="hidden" name="command">
   		<input type="hidden" name="id_kemaskini">
  </form>

<script>
function nexti(id) {
	document.f2.id_kemaskini.value = id;
	document.f2.command.value = "derafseterus";
	document.f2.action = "";
	document.f2.submit();
}

function cancel() {
	//document.setup.buttonsubmit.value = "ADD";
	document.cari.reset();
	//document.forms[0].kod_agensi.focus();
}

function senaraiPermohonan(id) {
	document.f2.action = "?_portal_module=HTP-00&fail="+id;
	document.f2.submit();
}

function tambahPermohonan() {
	document.tbh.action = "?_portal_module=ekptg.view.htp.FrmSewaanSemak";
	document.tbh.submit();
}
</script>

<script language="JavaScript"> document.forms[0].no_fail.focus(); </script>