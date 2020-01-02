<style type="text/css">
<!--
.style40 {color: #0000FF}
-->
</style>

<div align="right">

<!-- -->
#if($page == "1" )<img src="../img/1current.png" alt="" border="0" title="Maklumat Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/>#else<img src="../img/1enable.png" alt="" border="0" title="Maklumat Permohonan" onclick="step1($!htpPermohonan.permohonan.getIdPermohonan(),$!htpPermohonan.getIdHtpPermohonan())" onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($page == "2")<img src="../img/2current2.png" alt="" border="0" title="Maklumat Pembelian"/>#else<img src="../img/2enable2.png" alt="" border="0" title="Maklumat Pembelian" onclick="step2($!htpPermohonan.permohonan.getIdPermohonan())" onMouseOver="this.style.cursor='pointer';"/>#end</div>


