
  <input name="id_perbicaraan" type="hidden" id="id_perbicaraan" value="$id_perbicaraan"/>
  <input name="listSize" type="hidden" id="listSize"/>
  <input name="hitButt" type="hidden" id="hitButt"/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <fieldset>
	<legend><strong>Senarai Waris</strong></legend>
	<p></p>
    #if ($listSize != '0')
    <input name="cmdPilih" type="button" value="Pilih Waris" onclick="javascript:pilih()"/>
    #end
	<input type="button" name="cmdExit" value="kembali" onclick="self.close();return false;" />    
	<table align="center" width="100%"> 
         <tbody>
           <tr class="table_header">
           	  #if ($listSize != '0')
              <td scope="row" width="3%"></td>
             #end
              <td scope="row" width="7%" align="center">BIL</td>
             <td width="36%">NAMA</td>
             <td width="22%">NO KP BARU</td>
             <td width="32%" align="center">TALIAN DENGAN SIMATI</td>
    	</tr>
          #set ($list = "")
          #foreach ($list in $dataListWaris)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
         <tr>
          	#if ($listSize != '0')
            <td class="$row"><div align="center">
              <input type="checkbox" value="$list.id_ob" name="cbsemaks"/>
           </div></td>
            #end
            <td class="$row" align="center">$list.bil</td>           
            <td class="$row">$list.nama_ob.toUpperCase()</td>        
            <td class="$row">$list.no_kp_baru</td>            
            <td class="$row">$list.keterangan.toUpperCase()</td>          
    	 </tr>
          #end
          </tbody>
        </table>
    
</fieldset>
    </td>
  </tr>
</table>

<script>
function pilih() {

	var radioSelected = false;
	
	for (j = 0;  j < ${formName}.cbsemaks.length;  j++){
	if (${formName}.cbsemaks[j].checked)
	radioSelected = true;
	}
	if (!radioSelected){
	alert("Sila pilih \"Waris\" terlebih dahulu.");
	return (false);
	}
	
	window.close();
	document.${formName}.hitButt.value = "pilih";
	document.${formName}.submit();
	window.opener.refreshHA();
}
</script>

