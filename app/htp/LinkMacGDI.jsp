        <tr>
            <td valign="top" width="1%">&nbsp;</td>                        
            <td valign="top" colspan="3" align="left">
                <table width="100%" style="border:1px solid #000000">
                  <tr class="table_header">
                    <td>Senarai Pautan ke Agensi Luar</td>
                  </tr>
                  <tr>
                    <td>
                      <a href="http://g4nre.mygeoportal.gov.my" target="_blank" style="color:#0000FF">Laman web MacGDI</a><br />
                      <a href="http://10.19.137.13/flms_mappingsystem" target="_blank" style="color:#0000FF">Laman web FLMS</a><br />
                      <a href="http://jupem2u.jupp.gov.my/j2u/jupem2u.html" target="_blank" style="color:#0000FF">Laman web JUPEM</a> | <a style="color:#0000FF; cursor:pointer" onclick="javascript:openPU();">Skrin Pengesahan Borang PU</a><br />
                    </td>
                  </tr>
                </table>
            </td>                          
        </tr>
        <tr>
            <td>&nbsp;</td>                          
        </tr>
<script type="text/javascript">
  function openPU(IDFail) {	
      document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewEKadaster&action2=";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>        