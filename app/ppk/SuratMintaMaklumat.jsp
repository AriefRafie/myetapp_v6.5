<style type="text/css">
<!--
.styleJJ {color: #0000FF}

-->
</style>
<a href="#" class="styleJJ" onclick="Effect.toggle('toggle_appear', 'appear'); return false;">Surat Minta Maklumat Tambahan </a>
<div id="toggle_appear" style="display:none;">
<br/>
#if ($flagSeksyen17 == "yes")
#set ($noFail = $nfb)
#end

<fieldset style="width:600px"> 

#set($rownum = 0)
#foreach ( $x in $EkptgUtil.getSenaraiMaklumatTambahan($IdPermohonan) )
#if($x.id_semakan != '1612168')
    #set($rownum = $rownum + 1)
    #if($rownum%2!=0)
    #set($row_color = "row2")
    #else
    #set($row_color = "row1")
    #end

#if ($x.flag == "1")
#set ($flag = "checked")
#else
#set ($flag = "")
#end
<table width="100%">
<tr  class="$row_color">
<td width="5%" valign="top" >
<!-- onClick="checkMaklumatTambahan(this.name,'$IdPermohonan')" -->
<input $flag type="checkbox" id="check_maklumat" name="check_maklumat" value="$x.id_semakan"  /> 
</td>
<td  width="45" valign="top">
$x.perihal 
</td>
<td width="10%" valign="top">
Catatan : 
</td>
<td width="40%" valign="top">
#set($txtcatatantambahan = "txtcatatan" + $x.id_semakan)
<textarea name="$txtcatatantambahan" id="$txtcatatantambahan"  cols="30" rows="1" >$x.catatan</textarea>
</td>
</tr>
</table>
#end
#end


#set($rownum = 0)
#foreach ( $x in $EkptgUtil.getSenaraiMaklumatTambahan($IdPermohonan) )
#if($x.id_semakan == '1612168')
    #set($rownum = $rownum + 1)
    #if($rownum%2!=0)
    #set($row_color = "row2")
    #else
    #set($row_color = "row1")
    #end

#if ($x.flag == "1")
#set ($flag = "checked")
#else
#set ($flag = "")
#end
<table width="100%">
<tr  class="$row_color">
<td width="5%" valign="top" >
<!-- onClick="checkMaklumatTambahan(this.name,'$IdPermohonan')" -->
<input $flag type="checkbox" id="check_maklumat" name="check_maklumat" value="$x.id_semakan"  /> 
</td>
<td  width="45" valign="top">
$x.perihal 
</td>
<td width="10%" valign="top">
Catatan : 
</td>
<td width="40%" valign="top">
#set($txtcatatantambahan = "txtcatatan" + $x.id_semakan)
<textarea name="$txtcatatantambahan" id="$txtcatatantambahan"  cols="30" rows="1" >$x.catatan</textarea>
</td>
</tr>
</table>
#end
#end

<!--
Catatan :
<br />
-->
<textarea name="txtcatatan" id="txtcatatan"   cols="31" rows="3"  style="display:none" >$EkptgUtil.getCatatan($IdPermohonan).CATATAN_MAKLUMAT_TAMBAHAN</textarea>

<br/>
<input type=button onClick="simpanCatatanMaklumatTambahan('','$IdPermohonan');cetakMaklumatTambahan('$noFail')" value="Cetak">
</fieldset>
<br/>

</div>
<div id="results"></div>
<script>
function checkMaklumatTambahan(id_semakan,id_permohonan) {
//alert(id_semakan+"|"+id_permohonan);
//Save to DB
	url = "../servlet/ekptg.view.ppk.UtilsServlet?id_semakan="+id_semakan+"&id_permohonan="+id_permohonan+" ";
	actionName = "doMaklumatTambahan";
	target = "results";
	doAjaxUpdater(document.f1, url, target, actionName);
}

function simpanCatatanMaklumatTambahan(id_semakan,id_permohonan) {
//alert(id_semakan+"|"+id_permohonan);
//Save to DB
	url = "../servlet/ekptg.view.ppk.UtilsServlet?id_permohonan="+id_permohonan+"&txtcatatan="+document.getElementById("txtcatatan").value+" ";
	actionName = "doSimpanCatatanMaklumatTambahan";
	target = "results";
	doAjaxUpdater(document.f1, url, target, actionName);
}
</script>