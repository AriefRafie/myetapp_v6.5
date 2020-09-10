<fieldset>
          <legend><strong>Pengesahan Pegawai</strong></legend>
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
          

          <input type="hidden" name="enabledPegawai" id="enabledPegawai" value="$enabledPegawai">
           
           
          
            <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
            
            <tr>
              <td width="10%" valign="top">&nbsp;</td>
              <td width="20%">Nama Pegawai PPT</td>
              <td width="1%">:&nbsp;</td>
              <td width="69%">$!username</td>
            </tr>
            <tr>
            	<td>&nbsp;</td>
            </tr>
            <tr>
                	<td>&nbsp;</td>
                	<td>Pengesahan Token </td>
                	<td>:</td>
                	<td><input type="text" id="token" name="token" value="1111" maxlength="30" size="50" /></td>
            </tr>    
            
            <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
            
            <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
            
             <tr>
             <td width="10%" valign="top">&nbsp;</td>
             <td colspan=3 align=justify>
             
            
             ##if($!id_perintah!="")
           
             	##if($!enabledPegawai=="yes" && $statusPNB!="yes")
              		<input type="button" name="cmdHPNB1" id="cmdHPNB1" value="Pengesahan Tandatangan" onClick="sendDGcertPPT('$!id_fail','$id_permohonan','$!token')" />
              	
              	##end

              ##end
              </td></tr>
              
              <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
            
            <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
           </table>
</fieldset>


<script>

function sendDGcertPPT(id_fail,id_permohonan,token)
{
	alert("xxxxxxx");
	alert(id_fail);
	alert(id_permohonan);
	alert(token);
	document.${formName}.token.value = token;
	alert(document.${formName}.token.value);
	}
</script>

