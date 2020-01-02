  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
 
 <!--<strong>[CL-02-0408] Pajakan Kecil Tanah/Bangunan</strong>
<br><br>

	##parse ("app/htp/frmSewaanInfo.jsp")			
-->
<fieldset>
  <legend><strong>Senarai Surat Pajakan Kecil Tanah/Bangunan</strong></legend>
  <!--<form name="tbh" method="post">
  	<input name="add" value="Tambah" type="button" onClick="tambahPermohonan()">
  </form>
  <form name=${formName} method="post">-->
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <p></p>
	<tr> 
		<td>1).<a href="javascript:showWindow('ekptg.view.htp.FrmPKSuratPerjanjianTLengkap?id_kemaskini=$permohonanInfo.idpermohonan')" class="style1">
		Surat Makluman Perjanjian Tidak Lengkap</a>
		</td>
	</tr>
	<tr> 
		<td>2).<a href="javascript:showWindow('ekptg.view.htp.FrmPKSuratAdaBebanan?id_kemaskini=$permohonanInfo.idpermohonan')"  class="style1">
		Surat Makluman Perjanjian Ada Bebanan</a>
		</td>
	</tr>
	<tr> 
		<td>3).<a href="javascript:showWindow('ekptg.view.htp.FrmPKSuratTandatanganBL?id_kemaskini=$permohonanInfo.idpermohonan')"  class="style1">
		Surat Makluman Tandatangan Borang L</a></td>
	</tr> 
	<tr> 
		<td>4).<a href="javascript:showWindow('ekptg.view.htp.FrmPKSuratKembaliBL?id_kemaskini=$permohonanInfo.idpermohonan')"  class="style1">
		Surat Makluman Mengembalikan Borang L</a></td>
	</tr>
	<tr> 
		<td>5).<a href="javascript:showWindow('ekptg.view.htp.FrmPKMemo?id_kemaskini=$permohonanInfo.idpermohonan')"  class="style1">
		Memo Kepada PKP</a></td>
	</tr>
	<tr> 
		<td>6).<a href="javascript:showWindow('ekptg.view.htp.FrmPKMinitPKP?id_kemaskini=$permohonanInfo.idpermohonan')"  class="style1">
		Minit Bebas</a></td>
	</tr> 	 
  <!--<tr class="table_header">
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
   --> 
  </table>
  </fieldset>
   
        <tr>
		<td colspan="2" align="center">
			<!--<input type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti()">&nbsp;
			-->
			<!-- <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="backPre('$permohonanInfo.idfail')">&nbsp;
			<input type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="backMain()">&nbsp;
		-->
		</td>
      </tr>
	<input type="hidden" name="id_kemaskini">
	<input type="hidden" name="langkah" value="0" >
	<input type="hidden" name="fail" >
         <!-- <input type="hidden" name="command">
</form>-->

<script>

function showWindow(servlet){

          //var url = "../x/${securityToken}/ekptg.view.utils.FrmNegeri";
          var url = "../x/${securityToken}/"+servlet;
          var hWnd = window.open(url,'printuser','width=800,height=500, resizable=yes,scrollbars=yes');
          if ((document.window != null) && (!hWnd.opener))
            hWnd.opener = document.window;
          if (hWnd.focus != null) hWnd.focus();
}

function showWindowy(servlet){

          //var url = "../x/${securityToken}/ekptg.view.utils.FrmNegeri";
          var url = "../y/${securityToken}/"+servlet;
          var hWnd = window.open(url,'printuser','width=800,height=500, resizable=yes,scrollbars=yes');
          if ((document.window != null) && (!hWnd.opener))
            hWnd.opener = document.window;
          if (hWnd.focus != null) hWnd.focus();
}	
var url = "../servlet/"+urli+"?"+param+pnegeri+ptahun+ptem+pbulanmula+pbulantamat+punit+pdaerah;

function showWindowxy(servlet){

          var url = "../"+servlet;
          var hWnd = window.open(url,'printuser','width=800,height=500, resizable=yes,scrollbars=yes');
          if ((document.window != null) && (!hWnd.opener))
            hWnd.opener = document.window;
          if (hWnd.focus != null) hWnd.focus();
}
function nexti(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.command.value = "derafseterus";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function cancel() {
	//document.setup.buttonsubmit.value = "ADD";
	document.cari.reset();
	//document.forms[0].kod_agensi.focus();
}

function senaraiPermohonan(id) {
	document.${formName}.action = "?_portal_module=HTP-00&fail="+id;
	document.${formName}.submit();
}

function tambahPermohonan() {
	document.tbh.action = "?_portal_module=ekptg.view.htp.FrmSewaanSemak";
	document.tbh.submit();
}

function backPre(id) {
	document.${formName}.command.value = "pksenaraipermohonan";
	document.${formName}.langkah.value = '0->-1';
	document.${formName}.fail.value = id;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function backMain() {
  	document.${formName}.method = "post";
	document.${formName}.action = "";
	document.${formName}.submit();
}
</script>

<script language="JavaScript"> document.forms[0].no_fail.focus(); </script>