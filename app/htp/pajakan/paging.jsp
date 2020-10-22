<style type="text/css">
<!--
.style40 {color: #0000FF}
-->
</style>

<div align="right">

<!-- -->

#if($page == "1" )<img src="../img/1current.png" alt="" border="0" title="Senarai Fail"/><img src="../img/arrowgaris.png" alt="" border="0"/>#else<img src="../img/1enable.png" alt="" border="0" title="Senarai Fail" onclick="langkah1()" onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>#end
#if($page == "2")<img src="../img/2current.png" alt="" border="0" title="Maklumat Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/>#else<img src="../img/2enable.png" alt="" border="0" title="Maklumat Permohonan" onclick="langkah2($!htpPermohonan.permohonan.getIdPermohonan(),'$!htpPermohonan.permohonan.pfdFail.getIdFail()')" onMouseOver="this.style.cursor='pointer';"/><img src="../img/arrowgaris.png" alt="" border="0"/>#end
#if($page == "3")<img src="../img/3current.png" alt="" border="0" title="Maklumat Pemohon"/><img src="../img/arrowgaris.png" alt="" border="0"/>#else<img src="../img/3enable.png" alt="" border="0" title="Maklumat Pemohon" onclick="langkah3($!htpPermohonan.permohonan.getIdPermohonan(),'$!htpPermohonan.permohonan.pfdFail.getIdFail()')" onMouseOver="this.style.cursor='pointer';"/><img src="../img/arrowgaris.png" alt="" border="0"/>#end
#if($!valFlagMohonFail.equalsIgnoreCase("display"))
	#if($page == "4")<img src="../img/4current.png" alt="" border="0" title="Ulasan"/><img src="../img/arrowgaris.png" alt="" border="0"/>
	#else<img src="../img/4enable.png" alt="" border="0" title="Ulasan" onclick="langkah4($!htpPermohonan.permohonan.getIdPermohonan(),'$!htpPermohonan.permohonan.pfdFail.getIdFail()')" onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>#end
	#if($page == "5")<img src="../img/5current.png" alt="" border="0" title="Maklumat MJM"/>
	#else<img src="../img/5enable.png" alt="" border="0" title="Maklumat MJM" onclick="langkah5($!htpPermohonan.permohonan.getIdPermohonan(),'$!htpPermohonan.permohonan.pfdFail.getIdFail()')" onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>#end
	#if($page == "6")<img src="../img/6current.png" alt="" border="0" title="Deraf Perjanjian"/>
	#else<img src="../img/6enable.png" alt="" border="0" title="Deraf Perjanjian" onclick="langkah6($!htpPermohonan.permohonan.getIdPermohonan(),'$!htpPermohonan.permohonan.pfdFail.getIdFail()')" onMouseOver="this.style.cursor='pointer'"/>#end
<!-- 	#if($page == "7")<img src="../img/7current.png" alt="" border="0" title="Perjanjian"/> -->
<!-- 	#else<img src="../img/7enable.png" alt="" border="0" title="Perjanjian" onclick="langkah7($!htpPermohonan.permohonan.getIdPermohonan(),'$!htpPermohonan.permohonan.pfdFail.getIdFail()')" onMouseOver="this.style.cursor='pointer'"/>#end -->
	#if($page == "7")<img src="../img/7current.png" alt="" border="0" title="Pajakan"/>
	#else<img src="../img/7enable.png" alt="" border="0" title="Pajakan" onclick="langkah7($!htpPermohonan.permohonan.getIdPermohonan(),'$!htpPermohonan.permohonan.pfdFail.getIdFail()')" onMouseOver="this.style.cursor='pointer'"/>#end
	#if($page == "8")<img src="../img/8current.png" alt="" border="0" title="Bayaran"/>
	#else<img src="../img/8enable.png" alt="" border="0" title="Bayaran" onclick="langkah8($!htpPermohonan.permohonan.getIdPermohonan(),'$!htpPermohonan.permohonan.pfdFail.getIdFail()')" onMouseOver="this.style.cursor='pointer'"/>#end
	#if($page == "9")<img src="../img/9current.png" alt="" border="0" title="Penamatan"/>
	#else<img src="../img/9enable.png" alt="" border="0" title="Penamatan" onclick="langkah9($!htpPermohonan.permohonan.getIdPermohonan(),'$!htpPermohonan.permohonan.pfdFail.getIdFail()')" onMouseOver="this.style.cursor='pointer'"/>#end
	#if($page == "10")<img src="../img/10current.png" alt="" border="0" title="Tindakan"/>
	#else<img src="../img/10enable.png" alt="" border="0" title="Tindakan" onclick="langkah10($!htpPermohonan.permohonan.getIdPermohonan(),'$!htpPermohonan.permohonan.pfdFail.getIdFail()')" onMouseOver="this.style.cursor='pointer'"/>#end
#else
	<img src="../img/4disable.png" alt="" border="0" title="Ulasan"  onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>
	<img src="../img/5disable.png" alt="" border="0" title="Maklumat MJM"  onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>
	<img src="../img/6disable.png" alt="" border="0" title="Deraf Perjanjian"  onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>
<!-- 	<img src="../img/7disable.png" alt="" border="0" title="Perjanjian"  onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/> -->
	<img src="../img/7disable.png" alt="" border="0" title="Pajakan"  onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>
	<img src="../img/8disable.png" alt="" border="0" title="Bayaran"  onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>
	<img src="../img/9disable.png" alt="" border="0" title="Penamatan"  onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>
	<img src="../img/10disable.png" alt="" border="0" title="Tindakan"  onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>
#end


</div>
##parse("app/htp/utiliti/javascript/javaScriptPajakanPaging.jsp")
##parse("app/htp/utiliti/javascript/javaScriptPajakan.jsp")


