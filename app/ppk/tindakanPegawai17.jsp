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
              <td width="69%">$nama_peg_pengendali</td>
            </tr>
            
            <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
            
            <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
            <!-- arief add -->
              <tr>
      			<td colspan="3" width="100%"><div align="center">
                #if($!id_perbicaraan!="")
                	#if($!enabledPegawai=="yes" && $statusPNB!="yes")
              		<input type="button" name="cmdHPNB1" id="cmdHPNB1" value="Pengesahan Tandatangan Digital" onClick="sendDGcert('$!NO_FAIL','$!id_perbicaraan','$!id_fail','$id_permohonan','$idpermohonansimati')" />
              		#else
              		<input type="button" name="tandatanganDigital" id="tandatanganDigital" value="Pengesahan Tandatangan Digital" onclick="">
              		#end
                	#if($statusPNB=="yes")
              		<input type="button" name="cmdHPNB1" id="cmdHPNB1" value="Semak Status PNB" onClick="cetakBorangS2('$!NO_FAIL','$!id_perbicaraan','$!id_fail')" />
              		#end
                	<input name="cmdKembali" type="button" value="Kembali" onclick="history.back();">
       		 		</div>
       		 	#end
       		 	</td>
    		</tr>
    		<!-- arief close -->
              <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
            
            <tr>
              <td width="10%" valign="top" colspan=4>&nbsp;</td>
            </tr>
           </table>
</fieldset>
