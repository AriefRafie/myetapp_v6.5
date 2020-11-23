<fieldset>
<legend><strong>SENARAI PEMILIK</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr class="table_header">
    <td width="5%" scope="row" align="center"><strong><font color="white">Bil.</font></strong></td>
    <td><strong><font color="white">Nama Pemilik</font></strong></td>
    <td ><strong><font color="white">Jenis Pengenalan</font></strong></td>
    <td ><strong><font color="white">No. Pengenalan</font></strong></td>
    <td align="center" ><strong ><font color="white">Bahagian Pemilik</font></strong></td>
     <td width="5%" scope="row" align="center"></td>
  </tr>
  #set ($list = "")
  #set ($counter = 0)
  #if ($listPemilik.size() > 0)
  #foreach ($list in $listPemilik)
  #set( $counter = $counter + 1 )
  #if ($counter == '')
  #set( $row = "row1" )
  #elseif (($counter % 2) != 0)
  #set( $row = "row1" )
  #else 
  #set( $row = "row2" )
  #end
  <tr>
    <td class="$row" align="center"> $counter </td>
    <td class="$row">$!list.namaPB.toUpperCase()</td>
    <td class="$row">$!list.idJenisPengenalanPB.toUpperCase()</td>
    <td class="$row">$!list.noPengenalanPB</td>
    <td class="$row" align="center">$!list.BA / $!list.BB</td>
    <td class="$row" align="center"> 
    	<input type="checkbox" value="$!list.namaPB.toUpperCase()|$!list.noPengenalanPB|$!list.BA|$!list.BB|$!list.idJenisPengenalanPB" name="idsPemilik"/>  
    </td>
  </tr>
  #end  
  #else
  <tr>
    <td colspan="6" class="row"> Tiada rekod </td>
  </tr>
  #end
  <tr>
    <td colspan="6" class="row"></td>
  </tr>

</table>
</fieldset>

<script>
	function simpanPemilik() {
		document.${formName}.idPPKHTA.value = document.${formName}.idPPKHTA_.value;
		var c = 0;
		if (document.${formName}.idsPemilik.length > 1){
			for (i = 0; i < document.${formName}.idsPemilik.length; i++){
				if (document.${formName}.idsPemilik[i].checked == true){
					c++;
				}
			}
		} else {
			if (document.${formName}.idsPemilik.checked == true){
				c++;
			}
		}
		if (c == 0){
			alert("Sila Pilih Nama Pemilik.")
		} else {
			if ( !window.confirm("Adakah Anda Pasti ?") ){		
				return;
			}
			document.${formName}.hitButt.value = "simpanPemilik";
			document.${formName}.command.value = "simpanPemilik";	
			document.${formName}.actionPopup.value = "papar";
			document.${formName}.submit();
			
		}	
		
	}
</script>
