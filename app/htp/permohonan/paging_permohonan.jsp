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
<!-- SENARAI HAKMILIK-->
#if($page2)<img src="../img/1enable.png" alt="" border="0" title="Senarai Fail" 
onclick="carianFail()" onmouseover="this.style.cursor='pointer';"/><img src="../img/arrowgaris.png" 
alt="" border="0"/><img src="../img/2current.png" alt="" border="0" title="Penerimaan Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/3enable2.png" alt="" border="0" title="Maklumat Tanah Lanjutan" onclick="kemaskiniterimapermohonan($!idfail);" onmouseover="this.style.cursor='pointer';"/>
#elseif ($page3)
<img src="../img/1enable.png" alt="" border="0" title="Senarai Fail" 
onclick="javascript:doAjaxCall${formName}('');" onMouseOver="this.style.cursor='pointer';"/><img src="../img/arrowgaris.png" 
alt="" border="0"/><img src="../img/2enable.png" alt="" border="0" title="Penerimaan Permohonan"
onclick="javascript:viewMaklumatPermohonan($!idfail);" onMouseOver="this.style.cursor='pointer';"/><img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/3current2.png" alt="" border="0" title="Detail Hakmilik/Rizab"/>
#end</div></td>
</tr>
  <input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch">
  <input type="hidden" name="selectedTab" value="$!selectedTab">
  <input type="hidden" name="selectedTab2" value="$!selectedTab2">
  <input type="hidden" name="selectedTab3" value="$!selectedTab3">
  <input type="hidden" name="socNegeri" value="$!idnegeri">
  <input type="hidden" name="tabmode" value="$!tabmode">
  <input type="hidden" name="mode" value="$!mode">
  <input type="hidden" name="submit" value="$!submit">
  <input type="hidden" name="txdtarikhsuratfail" value="$!txdtarikhsuratfail">
  <input type="hidden" name="txdtarikhantarfail" value="$!txdtarikhantarfail">
  <input type="hidden" name="txdtarikhkeputusanfail" value="$!txdtarikhkeputusanfail">
  <input type="hidden" name="txtTajukCarian" value="$!txtTajukCarian"> 
</table>
