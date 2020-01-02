

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr> #foreach($beanHeader in $BeanHeader)
    #set($idJadualKedua = $beanHeader.idJadualKedua)
    #set($namaPelesen = $beanHeader.namaPelesen)
    #set($noLesen = $beanHeader.noLesen)
    #set($alamat1 = $beanHeader.alamat1)             
    #set($alamat2 = $beanHeader.alamat2)
    #set($alamat3 = $beanHeader.alamat3)
    #set($noTel = $beanHeader.noTel)  
    #end
	<td width="50%" valign="top"><fieldset>
      <legend><strong>MAKLUMAT PELESEN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
  		<tr>
    		<td width="24%" align="right">NAMA PEMEGANG LESEN</td>
    		<td width="1%">:</td>
    		<td width="24%"><font color="blue">$namaPelesen</font></td>
    		<td width="17%" align="right">NO. LESEN</td>
    		<td width="1%"> :</td>
   		 	<td width="33%"><font color="blue" valign="top">$noLesen</font></td>
 	 	</tr>
  		<tr>
    		<td align="right">ALAMAT</td>
    		<td>:</td>
    		<td><font color="blue" valign="top">$alamat1</font></td>
    		<td align="right">NO. TEL</td>
    		<td> :</td>
    		<td><font color="blue">$noTel</font></td>
  		</tr>
  		<tr>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td><font color="blue" valign="top">$alamat2</font></td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
  		</tr>
  		<tr>
   			<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td><font color="blue" valign="top">$alamat3</font></td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
  		</tr>
	</table>
    </fieldset>
    </td>
</tr>
</table>

