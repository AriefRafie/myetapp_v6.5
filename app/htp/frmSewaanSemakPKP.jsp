	#parse ("app/htp/frmSewaanInfo.jsp")			

<fieldset>
  <legend><strong>Semakan Perjanjian</strong></legend>
  <!--<form name="tbh" method="post">
  	<input name="add" value="Tambah" type="button" onClick="tambahPermohonan()">
  </form>
  -->
  <form name=f2 method="post">
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
             #set ( $checked = "" )
            #foreach ( $semak in $senaraiSemakan )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
                <tr>
                    <td class="$row" width="10">
                        #if ( $semakclass.isSemakan("$permohonanInfo.idpermohonan", "$semak.id" ))
                            #set ( $checked = "checked" )
                        #else
                            #set ( $checked = "" )
                        #end
                        <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked>
                    </td>
                    <td class="$row">
                        $semak.keterangan
                    </td>

                </tr>       
            #end
  
  <!-- <tr class="table_header">
  	<td>No</td>
  	<td>No Fail</td>
  	<td>Nama Fail</td>
  	<td>Status Pergerakan Fail</td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraiList )
  	#set ( $cnt = $cnt + 1 )

  <tr>
  <td>$cnt.</td>
  <td>
  	<a href="javascript:senaraiPermohonan('$senarai.id')">
	$senarai.no
	</a>
  </td>
  <td>$senarai.tajuk</td>
  <td>$senarai.keterangan</td>
  </tr>
  #end
  </table>
  </fieldset>
  <fieldset>
  <legend>Senarai Pemilik</legend>
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr class="table_header">
  	<td>No</td>
  	<td>No Fail</td>
  	<td>Nama Fail</td>
  	<td>Status Pergerakan Fail</td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraiList )
  	#set ( $cnt = $cnt + 1 )

  <tr>
  <td>$cnt.</td>
  <td>
  	<a href="javascript:senaraiPermohonan('$senarai.id')">
	$senarai.no
	</a>
  </td>
  <td>$senarai.tajuk</td>
  <td>$senarai.keterangan</td>
  </tr>
  #end -->
  </table>
  
        <tr>
		<td colspan="2" align="center">
			<input type="submit" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="semakanPTambah('$permohonanInfo.idpermohonan')">&nbsp
			<input type="reset" name="cmdBatal" id="cmdBatal" value="Batal">&nbsp;
			<input type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti('$permohonanInfo.idpermohonan')">&nbsp;
			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="back()">&nbsp;
			<input type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="back(-2)">&nbsp;
		</td>
      </tr>
	<input type="hidden" name="command">
   	<input type="hidden" name="idkemaskini">
   	<input type="hidden" name="fail" >
   		
	</form>
</fieldset>

<script>
function semakanPTambah(id) {
	document.f2.command.value = "semakanPKPkemaskini";
	document.f2.idkemaskini.value = id;
	document.f2.action = "";
	document.f2.submit();
}

function nexti(id) {
	//document.f2.method="post";
	document.f2.idkemaskini.value = id;
	document.f2.command.value = "semakanpkpseterus";
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