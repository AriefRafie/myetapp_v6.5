  #set ( $nofail = "" )			
  #set ( $namafail = "" )
  #if($noFail != "")
  	  #set ( $nofail = $noFail)			
  #end

  #if($namaFail != "")
	#set ( $namafail = $namaFail)			
  
  #end
  
  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>

<fieldset>
  <legend>MAKLUMAT CARIAN</legend>
  <table width="100%" cellspacing="2" cellpadding="2" border="0">
  
  <!--
  	<tr class="table_header">
  	<td align="right">No Fail : &nbsp;&nbsp;</td>
  	<td><input type=text  size="43" name=nofail value="$nofail"></td>
  </tr>
  <tr class="table_header">
  	<td height="25" align="right">Nama Fail : &nbsp;&nbsp;</td>
  	<td><input name="namafail" type="text" size="43" value="$namafail" maxlength="300"  onkeyup="this.value=this.value.toUpperCase();"></td>
  </tr>
  <tr class="table_header">
          <td height="25"  align="right">Negeri : &nbsp;&nbsp;</td>
          <td>$socNegeri</td>
  </tr>
    <tr class="table_header">

          <td  align="right">Status : &nbsp;&nbsp;</td>
          <td>$socStatus&nbsp;</td>
  </tr>
  <td></td><td>
  <input type=button value="Cari" onClick="javascript:carian();">
  <input type=button value = "Kosongkan" onClick="javascript:cancel();">
  </td>
  </tr>
  </table>
  -->
  
    	<tbody>
    
		  <table width="100%" border="0">
		<tr >			  
			<td colspan="2" align="center">&nbsp;</td>
		</tr>
		  <tr >
			<td align="right" width="40%">No Fail&nbsp;&nbsp;:&nbsp;&nbsp;</td>
			<td><input type=text  size="43" name=nofail value="$nofail"></td>
		  </tr>
		  <tr >
			<td align="right" width="40%">Nama Fail&nbsp;&nbsp;:&nbsp;&nbsp;</td>
			<td><input name="namafail" type="text" size="43" value="$namafail" maxlength="300"  onkeyup="this.value=this.value.toUpperCase();"></td>
		  </tr>
		  <tr >
			  <td align="right" width="40%">Negeri&nbsp;&nbsp;:&nbsp;&nbsp;</td>
			  <td>$socNegeri</td>
		  </tr>
		<tr >

			<td  align="right" width="40%">Status&nbsp;&nbsp;:&nbsp;&nbsp;</td>
			<td >$socStatus&nbsp;</td>
		</tr>
		<tr ">			  
			<td colspan="2" align="center">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" width="100%" align="center" valign="top"> 
				<input class="stylobutton" type=button value="Cari" onClick="javascript:carian();">
				<input class="stylobutton" type=button value = "Kosongkan" onClick="javascript:cancel();"></td>     		
		</tr>				  

		  </table>
  
    	</tbody>
  </table>

</fieldset>

<fieldset>
  <legend>SENARAI FAIL PAJAKAN KECIL TANAH/BANGUNAN</legend>
  <!--	
  	<input value="Tambah" type="button" onClick="javascript:tambahPermohonan()">
  -->
  #parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="2" cellpadding="1" border="0">
  <tr class="table_header">
  	<td width="3%" ><b>BIL.</b></td>
  	<td width="20%" ><b>NO FAIL</b></td>
  	<td width="34%" ><b>NAMA FAIL</b></td>
  	<td width="23%" ><b>STATUS PERGERAKAN FAIL</b></td>
    <td width="20%" ><b>DIDAFTAR OLEH</b></td>
</tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraiCarian )
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
  	<!--<a class="style1" href="javascript:senaraiPermohonan('$senarai.id')"> -->
	$senarai.no
	<!--</a> -->
  </td>
  <td class="$row">$senarai.tajuk</td>
  <td class="$row">$senarai.keterangan</td>
  <td class="$row">$senarai.namapembuat</td>
 </tr>
  #end
  	#if ($cnt == 0)
  	<tr> 
  		<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  	</tr>
	#end
  </table>
   	<input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
   	<input type="hidden" name="pagemode" >
   	<input type="hidden" name="langkah" value="0" >
</fieldset>

<script>
function cancel() {
	document.${formName}.reset();
}

function carian() {
	document.${formName}.command.value = "pksenaraifailcari";
	document.${formName}.langkah.value = '0->0';
	document.${formName}.action = "";
	document.${formName}.submit();
}

function senaraiPermohonan(id) {
	document.${formName}.command.value = "pksenaraipermohonan";
	document.${formName}.fail.value = id;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function tambahPermohonan() {
	document.${formName}.command.value = "pkfailbaru";
	document.${formName}.langkah.value = '0->1';
	//document.${formName}.method = "post";
	document.${formName}.pagemode.value = "0";
	document.${formName}.action = "";
	document.${formName}.submit();
	
}
</script>

<script language="JavaScript"> 
//document.forms[0].no_fail.focus(); 
</script>