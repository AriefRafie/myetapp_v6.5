<style type="text/css">
<!--
.style1 {
	color: #0000FF;
	font-weight: bold;
}
-->
 #if ($!flagModul == 'PPK')  #parse("css/eTapp_PPK.css") #end 
 #if ($!flagModul == 'PPT')  #parse("css/eTapp_PPT.css") #end
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($!flagMsg == 'T')
  <tr>
    <td colspan="3">&nbsp;
      <div class="error">$!outputMsg</div></td>
  </tr>
  #end
  
  #if ($hakmilik)
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>#parse("app/integrasi/etanah/wpkl/frmMaklumatHakmilik.jsp")</td>
  </tr>
  
  #if ($hakmilik.pihakBerkepentinganList.size() > 0)
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>#parse("app/integrasi/etanah/wpkl/frmSenaraiPemilik.jsp")</td>
  </tr>
  #end
  
  #if ($hakmilik.urusanList.size() > 0)
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>#parse("app/integrasi/etanah/wpkl/frmSenaraiUrusan.jsp")</td>
  </tr>
  #end
  
  #end  
  
  <tr>
    <td align="center"><input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()">
    </td>
  </tr>
</table>
<script>
function kembali() {		
	window.close();
}
</script>
