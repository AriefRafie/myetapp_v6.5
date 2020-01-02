<!-- <strong>[CL-02-0407] Pajakan Kecil Tanah/Bangunan</strong>
<br><br>
-->
<br/>
#parse('app/htp/frmPajakanKecilPaging.jsp')
<!-- <br/> -->

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td>
			#parse ("app/htp/frmPajakanKecilInfo.jsp")			
		</td>
	</tr>	
	
  	<tr>
    	<td>	

<fieldset>
  <legend><strong>KEDUDUKAN FAIL TERKINI</strong></legend>

  	<table width="100%" >        
 		<tr>
        	<td width="100%">
	  			<table width="100%" >
			  <tr class="table_header">
				<td width="3%">BIL.</td>
			  	<td width="33%">TARIKH TERIMA</td>
			  	<td width="33%">TARIKH HANTAR</td>
			  	<td width="32%">ULASAN</td>
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
        	</td>
 		<tr>
	</table>        
  </fieldset>
   
        <tr>
		<td colspan="2" align="center">
			<!--
			<input class="stylobutton" type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti5('$permohonanInfo.idpermohonan')">&nbsp;
			-->
			<!-- Komen pada 11/06/2010
			<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Sebelumnya" onclick="backPrePage3('$permohonanInfo.idfail','$permohonanInfo.idpermohonan')">&nbsp;
			-->
			<input class="stylobutton" type="button" name="cmdFail" id="cmdKembali" value="Senarai Permohonan" onclick="senaraiPermohonan('$permohonanInfo.idfail')">&nbsp;
		</td>
      </tr>
   		<input type="hidden" name="id_kemaskini">
   		   	    <input type="hidden" name="langkah" value="0" >
   		   	<input type="hidden" name="fail" >
   		
		</td>
	</tr>
</table>
<script>

/*
function nexti5(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.command.value = "pkderafseterus";
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
*/
</script>

