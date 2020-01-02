<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td>&nbsp;</td>
	</tr>	    
	<tr>
    	<td>

<fieldset>
  <legend>SENARAI PERINGATAN</legend>
  <table width="100%" cellspacing="2" cellpadding="1" border="0">
  <tr class="table_header">
  	<td width="3%"><b>BIL.</b></td>
  	<td width="20%"><b>NO. FAIL</b></td>
  	<td width="50%"><b>NAMA FAIL</b></td>
  	<td width="27%"><b>TINDAKAN</b></td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraiPeringatan )
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
  	<!--<a  href="javascript:maklumatTerperinci('$senarai.tabid','$senarai.modul')" class="style1"> -->
	$!senarai.permohonan.pfdFail.getNoFail()
	<!--</a> -->
  </td>
  <td class="$row">$!senarai.permohonan.getTujuan()</td>
<!--
100  - Surat Peringatan
1137 -
1138 -
-->  
  <td class="$row">
  	<img src='../img/alert.gif' alt='' border='0'/><b><i>&nbsp;<blink><a href="#">Peringatan Susulan Ulasan JPPH</a></blink></i></b>
  </td>
  </tr>
  
  #end
	#if ($cnt == 0)
	<tr> 
		<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
	</tr>
	#end
  </table>
    <input type="hidden" name="command1" >
   	<input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
   	<input type="hidden" name="pagemode" >
   	<input type="hidden" name="langkah" value="0" >

</fieldset>

		</td>
	</tr>	
</table>

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
	
	function maklumatTerperinci(id,modul) {
		//document.${formName}.command.value = "senaraiterperinci";
		//document.${formName}.langkah.value = '0->-1';
		//document.${formName}.fail.value = "";
		//alert("maklumatTerperinci:"+id);
		
		//document.${formName}.method = "post";
		//../x/${securityToken}
		//document.${formName}.action = "../c/${securityToken}?_portal_module=ekptg.view.htp.FrmCukai";
		document.${formName}.action = "../c/"+id+"?_portal_module="+modul;
		document.${formName}.submit();
		
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmSewaanSemak";
		//document.${formName}.submit();
		
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
