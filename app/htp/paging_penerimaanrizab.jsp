<!-- paging_penerimaanhakmilikrizab.jsp -->
<style type="text/css">
<!--
.style40 {color: #0000FF}
-->
</style>
<table width="100%" id="paging">
	<tr>
		<td width="100%">
		<div align="right">
		<!-- SENARAI FAIL HAKMLIK DAN RIZAB -->
		
		<!-- SENARAI HAKMILIK -->
		#if($page2)
		<img src="../img/1enable.png" alt="" border="0" title="Senarai Fail Hakmilik/Rizab" 
		onclick="javascript:doAjaxCall${formName}('');" onMouseOver="this.style.cursor='pointer';"/><img src="../img/arrowgaris.png" 
		alt="" border="0"/><img src="../img/2current.png" alt="" border="0" title="Senarai Hakmilik"/><img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/3disable2.png" alt="" border="0" title="Detail Hakmilik/Rizab"/>
		#elseif ($page3)
		<img src="../img/1enable.png" alt="" border="0" title="Senarai Fail Hakmilik/Rizab" 
		onclick="javascript:doAjaxCall${formName}('');" onMouseOver="this.style.cursor='pointer';"/><img src="../img/arrowgaris.png" 
		alt="" border="0"/><img src="../img/2enable.png" alt="" border="0" title="Senarai Hakmilik"
		onclick="javascript:doHakmilikPage2();" onMouseOver="this.style.cursor='pointer';"/><img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/3current2.png" alt="" border="0" title="Detail Hakmilik/Rizab"/>
		#end
		</div>
		</td>
	</tr>

</table>
<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch">
