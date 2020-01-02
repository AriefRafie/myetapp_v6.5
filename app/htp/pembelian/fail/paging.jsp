<style type="text/css">
<!--
.style40 {color: #0000FF}
-->
</style>

<div align="right">

<!-- -->

#if($page == "1" )<img src="../img/1current.png" alt="" border="0" title="Maklumat Permohonan"/><img src="../img/arrowgaris.png" alt="" border="0"/>#else<img src="../img/1enable.png" alt="" border="0" title="Maklumat Permohonan" onclick="step1($!htpPermohonan.permohonan.getIdPermohonan(),$!htpPermohonan.getIdHtpPermohonan())" onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($page == "2")<img src="../img/2current.png" alt="" border="0" title="Maklumat Pembelian"/><img src="../img/arrowgaris.png" alt="" border="0"/>#else<img src="../img/2enable.png" alt="" border="0" title="Maklumat Pembelian" onclick="step2($!htpPermohonan.permohonan.getIdPermohonan())" onMouseOver="this.style.cursor='pointer';"/><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($page == "3")<img src="../img/3current.png" alt="" border="0" title="Semakan Perakuan Pembelian"/><img src="../img/arrowgaris.png" alt="" border="0"/>#else<img src="../img/3enable.png" alt="" border="0" title="Semakan Perakuan Pembelian" onclick="step3($!htpPermohonan.permohonan.getIdPermohonan())"onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>#end
#if($page == "4")<img src="../img/4current.png" alt="" border="0" title="Rundingan Harga"/><img src="../img/arrowgaris.png" alt="" border="0"/>#else<img src="../img/4enable.png" alt="" border="0" title="Rundingan Harga" onclick="step4($!htpPermohonan.permohonan.getIdPermohonan())" onMouseOver="this.style.cursor='pointer'"/><img src="../img/arrowgaris.png" alt="" border="0"/>#end
#if($page == "5")<img src="../img/5current.png" alt="" border="0" title="Perjanjian"/>#else<img src="../img/5enable.png" alt="" border="0" title="Perjanjian" onclick="step5($!htpPermohonan.permohonan.getIdPermohonan())" onMouseOver="this.style.cursor='pointer'"/>#end



</div>


