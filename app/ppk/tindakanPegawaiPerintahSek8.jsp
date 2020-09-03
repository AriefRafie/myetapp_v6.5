<fieldset>
          <legend><strong>Pengesahan Pegawai Pengendali</strong></legend>
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
          

          <input type="hidden" name="enabledPegawai" id="enabledPegawai" value="$enabledPegawai">
          
            <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
            
            <tr>
              <td width="10%" valign="top">&nbsp;</td>
              <td width="20%">Nama Pegawai</td>
              <td width="1%">:&nbsp;</td>
              <td width="69%">$!nama_peg_pengendali</td>
            </tr>
            
            <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
            
            <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
            
             <tr>
             <td width="10%" valign="top">&nbsp;</td>
             <td colspan=3 align=left>
             
            
             #if($!id_perintah!="")
           
             	#if($!enabledPegawai=="yes" && $statusPNB!="yes")
              		<input type="button" name="cmdHPNB1" id="cmdHPNB1" value="Pengesahan Tandatangan" onClick="sendDGcertPerintah('$!NO_FAIL','$!id_perintah','$!id_fail','$id_permohonan','$idpermohonansimati')" />
              	
              	#end

              #end
              </td></tr>
              
              <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
            
            <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
           </table>
</fieldset>

