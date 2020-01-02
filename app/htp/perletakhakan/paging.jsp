<style type="text/css">
<!--
.style40 {color: #0000FF}
-->
</style>
<table width="100%" id="paging">
<tr>
<td width="100%">
<div align="right">
##$page2||$page3||$page3||idfail=$!idfail
#if($page2)<img src="../img/1enable.png" alt="" border="0" title="Senarai Fail" 
onclick="javascript:doAjaxCall${formName}('');" onmouseover="this.style.cursor='pointer';"/><img src="../img/arrowgaris.png" 
alt="" border="0"/><img src="../img/2current.png" alt="" border="0" title="Penerimaan Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/3enable2.png" alt="" border="0" title="Maklumat Tanah " onclick="kemaskiniterimapermohonan($!idfail);" onmouseover="this.style.cursor='pointer';"/>
#elseif ($page3)
<img src="../img/1enable.png" alt="" border="0" title="Senarai Fail" 
onclick="javascript:doAjaxCall${formName}('');" onMouseOver="this.style.cursor='pointer';"/><img src="../img/arrowgaris.png" 
alt="" border="0"/><img src="../img/2current.png" alt="" border="0" title="Maklumat Permohonan"
 /><img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/3enable.png" alt="" border="0" title="Maklumat Hakmilik" onclick="maklumatHakmilik($!idfail);" onmouseover="this.style.cursor='pointer';"/>
#end</div></td>
</tr>
</table>
