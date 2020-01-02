

#set($temp_maklumat_permohonan = "Maklumat Permohonan")


#foreach($list in $View)
#set($noFail = $list.noFail)
#set($idPemohon = $list.idPemohon)
#set($id_Status = $list.id_Status)
#end


#if($!skrin_online != "yes")


#if($!skrin_online_17 == "yes" and $!id_Status != "160")

#else

#parse("app/ppk/syarat_kemaskini.jsp")


#set($temp_maklumat_permohonan = "Maklumat Borang P")

#if($pendaftaran == "")
<table width="100%">
<tr>
<td width="100%"><div align="right">#if($flagFromSenaraiFailSek8 == '' && $flagForView  == '' && $flagFromSenaraiPermohonanSek8 == '' && $pendaftaran == "")<a href="javascript:javascript:Kembali()" class="style40"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if ($flagFromSenaraiFailSek8 == 'yes')<a href="javascript:javascript:kembaliSenaraiFail('$noFail')" class="style40"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagFromSenaraiPermohonanSek8 == 'yes')<a href="javascript:kembaliSenaraiPermohonan('$noFail')" class="style40"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagForView  == 'yes')<a href="javascript:ForView('$noFail')" class="style40"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($pendaftaran == "yes")<a href="javascript:kembalidaftar()" class="style40" ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#else<a href="javascript:kembalix()" class="style40" ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/><a href="javascript:kembali_simati()" class="style40" ><img src="../img/3enable.png" alt="" border="0" title="Pendaftaran Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/4current2.png" alt="" border="0" title="$temp_maklumat_permohonan"/></div></td>
</tr>
<!-- <input name="eventStatus" id="eventStatus" type="hidden" /> -->
</table>
#else
<table width="100%">
<tr>
<td width="100%"><div align="right">#if($flagFromSenaraiPermohonanSek8 == 'yes')<a href="javascript:kembaliSenaraiPermohonan('$noFail')" class="style40"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>
#end#if($pendaftaran == "yes")<a href="javascript:kembalidaftar()" class="style40" ><img src="../img/1enable.png" alt="" border="0" title="Senarai Semak"/></a>#else<a href="javascript:kembalix()" class="style40" ><img src="../img/1enable.png" alt="" border="0" title="Senarai Semak"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/><a href="javascript:kembali_simati()" class="style40" ><img src="../img/2enable.png" alt="" border="0" title="Pendaftaran Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/3current2.png" alt="" border="0" title="$temp_maklumat_permohonan"/></div></td>
</tr>
<!-- <input name="eventStatus" id="eventStatus" type="hidden" /> -->
</table>

#end


#end
#end





<input name="flagForView" type="hidden" id="flagForView" value="$!flagForView"/>




