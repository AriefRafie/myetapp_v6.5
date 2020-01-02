

<table width="100%" id="paging">
<tr>
<td width="100%">
<div align="right">
#if($jenis_button == "1")
<img src="../img/1current.png" alt="" border="0" title="Penarikan Balik Pengambilan Tanah Di Unit Pengambilan Tanah" />
#else
<img src="../img/1enable.png" alt="" border="0" title="Penarikan Balik Pengambilan Tanah Di Unit Pengambilan Tanah" onclick="screen1('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';"  />
#end
<img src="../img/arrowgaris.png" alt="" border="0"/>
#if($id_pembatalan != "")
#if($jenis_button == "2")
<img src="../img/2current.png" alt="" border="0" title="Urusan Kertas MMK/KM/MB/LC Penarikan Balik"/>
#else
<img src="../img/2enable.png" alt="" border="0" title="Urusan Kertas MMK/KM/MB/LC Penarikan Balik" onclick="screen2('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';" />
#end
#else
<img src="../img/2disable.png" alt="" border="0" title="Laporan Tanah Penarikan Balik"/>
#end
<img src="../img/arrowgaris.png" alt="" border="0"/>
#if($id_pembatalan != "" && $maklumat_keputusan.size()>0)
#if($jenis_button == "3")
<img src="../img/3current.png" alt="" border="0" title="Maklumat Warta" />
#else
<img src="../img/3enable.png" alt="" border="0" title="Maklumat Warta" onclick="screen3('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';"/>
#end
#else
<img src="../img/3disable2.png" alt="" border="0" title="Maklumat Warta"/>
#end

<img src="../img/arrowgaris.png" alt="" border="0"/>
#if($id_pembatalan != "")
#if($jenis_button == "4")
<img src="../img/4current.png" alt="" border="0" title="Urusan Siasatan"  />
#else  
<img src="../img/4enable.png" alt="" border="0" title="Urusan Siasatan" onclick="screen4('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';" />
#end
#else
<img src="../img/4disable2.png" alt="" border="0" title="Urusan Siasatan"/>
#end

<img src="../img/arrowgaris.png" alt="" border="0"/>
#if($id_pembatalan != "")
#if($jenis_button == "5")
<img src="../img/5current.png" alt="" border="0" title="Urusan Pampasan"  />
#else  
<img src="../img/5enable.png" alt="" border="0" title="Urusan Pampasan" onclick="screen5('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';" />
#end
#else
<img src="../img/5disable.png" alt="" border="0" title="Urusan Pampasan"/>
#end

</div>
</td>
</tr>
</table>

<!--
<table width="100%">
<tr>
<td width="100%"><div align="right">#if($flagFromSenaraiFailSek8 == '' && $flagForView  == '' && $flagFromSenaraiPermohonanSek8 == '' && $pendaftaran == "")<a href="javascript:javascript:Kembali()" class="style40"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if ($flagFromSenaraiFailSek8 == 'yes')<a href="javascript:javascript:kembaliSenaraiFail('$noFail')" class="style40"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagFromSenaraiPermohonanSek8 == 'yes')<a href="javascript:kembaliSenaraiPermohonan('$noFail')" class="style40"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($flagForView  == 'yes')<a href="javascript:ForView('$noFail')" class="style40"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/>#end#if($pendaftaran == "yes")<a href="javascript:kembalidaftar()" class="style40" ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#else<a href="javascript:kembalix()" class="style40" ><img src="../img/2enable.png" alt="" border="0" title="Senarai Semak"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/><a href="javascript:kembali_simati()" class="style40" ><img src="../img/3enable.png" alt="" border="0" title="Pendaftaran Permohonan"/></a><img src="../img/arrowgaris.png" alt="" border="0"/><img src="../img/4current2.png" alt="" border="0" title="Maklumat Permohonan"/></div></td>
</tr>
</table>
<!-- <input name="eventStatus" id="eventStatus" type="hidden" /> -->

<script>
function screen1(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "senarai";
	document.${formName}.sub_command.value = "papar";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.submit();
}
/*
function screen2(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Maklumat_Am";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();

}
*/
function screen2(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Penyediaan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.submit();

}

function screen3(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "Warta";
	document.${formName}.sub_command.value = "Warta";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.submit();

}

function screen5(id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.submit();
}


function screen4(id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.submit();
}
</script>


