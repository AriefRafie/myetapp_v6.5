  <div align="left">
    <a href="http://g4nre.mygeoportal.gov.my" target="_blank" style="color:#0000FF">MacGDI</a> |
    <a href="http://g4nre.mygeoportal.gov.my" target="_blank" style="color:#0000FF">JUPEM</a><!-- | <a style="color:#0000FF; cursor:pointer" onclick="javascript:openPU();">Skrin Pengesahan Borang PU</a> -->
  </div>
<script type="text/javascript">
  function openPU(IDFail) {	
      document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewEKadaster&action2=";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>