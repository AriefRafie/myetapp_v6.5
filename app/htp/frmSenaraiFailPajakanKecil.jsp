<fieldset>
  <legend>Carian</legend>
   <form name=cari method=post>
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr class="table_header"><td>No Fail</td><td><input type=text name=nofail></td></tr>
  <tr>
  <td></td><td>
  <input class=button type=button value="Cari" onClick="javascript:carian();">
  <input class=button type=button value = "Kosongkan" onClick="javascript:cancel();">
  </td>
  </tr>
  </table>
     <input type="hidden" name="command">
  
  </form>
</fieldset>

<fieldset>
  <legend>Senarai Fail Pajakan Kecil Tanah/Bangunan</legend>
  <!--<form name="tbh" method="post">-->
  <form name=f2 method="post">
  	<input   class=button value="Tambah" type="button" onClick="javascript:tambahPermohonan()">
  <!--    <input type="hidden" name="command" >
  	
  </form>-->
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr class="table_header">
  	<td width="3%">No.</td>
  	<td width="23%">No Fail</td>
  	<td width="51%">Nama Fail</td>
  	<td width="23%">Status Pergerakan Fail</td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraiList )
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
  	<a href="javascript:senaraiPermohonan('$senarai.id')">
	$senarai.no
	</a>
  </td>
  <td class="$row">$senarai.tajuk</td>
  <td class="$row">$senarai.keterangan</td>
  </tr>
  #end
  </table>
    <input type="hidden" name="command" >
   	<input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
   	<input type="hidden" name="pagemode" >
  </form>
</fieldset>

<script>
function cancel() {
	document.cari.reset();
}

function carian() {
	//doAjaxCall${formName}("pksenaraifailcari");
	document.cari.command.value = "pkfailsearch";
	document.cari.action = "";
	document.cari.submit();
}

function senaraiPermohonan(id) {
	document.f2.command.value = "pkpermohonan";
	document.f2.fail.value = id;
	document.f2.action = "";
	document.f2.submit();
}

function tambahPermohonan() {
	//document.tbh.command.value = "pkfailbaru";
	//document.tbh.action = "";
	//document.tbh.submit();
	//alert('x jadi');
	document.f2.method = "post";
	document.f2.pagemode.value = "0";
	document.f2.command.value = "pkfailbaru";
	//document.f2.action = "?ekptg.view.htp.FrmPajakanKecil";
	document.f2.action = "";
	document.f2.submit();
	
}
</script>

<script language="JavaScript"> 
//document.forms[0].no_fail.focus(); 
</script>