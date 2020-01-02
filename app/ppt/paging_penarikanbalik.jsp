

#foreach($nper in $maklumat_pembatalan)
#set($id_fail = $nper.ID_FAIL)
#end



#foreach($nama_user in $nama_user)
#set($user_name = $nama_user.USER_NAME)
#set($user_name_cap = $nama_user.USER_NAME_CAP)
#end



#set($tempat1 = "")
#set($tempat2 = "")
#set($tempat3 = "")

#set($tarikh1 = "")
#set($tarikh2 = "")
#set($tarikh3 = "")

#foreach($tempat_tampal in $tempat_tampal)
#if($tempat_tampal.JENIS_TEMPAT_TAMPAL == "1")
#set($tempat1 = $tempat_tampal.TEMPAT)
#set($tarikh1 = $tempat_tampal.TARIKH_TAMPAL)
#end
#if($tempat_tampal.JENIS_TEMPAT_TAMPAL == "2")
#set($tempat2 = $tempat_tampal.TEMPAT)
#set($tarikh2 = $tempat_tampal.TARIKH_TAMPAL)
#end
#if($tempat_tampal.JENIS_TEMPAT_TAMPAL == "3")
#set($tempat3 = $tempat_tampal.TEMPAT)
#set($tarikh3 = $tempat_tampal.TARIKH_TAMPAL)
#end
#end


					#set($nama_pb_pb_siasatan = "" )
                      #set($count_total_pb1=0)
                      #set($count_totalX_pb1=$maklumat_hakmilik_mmk_pb_siasatan.size())
                      #set($count_totalXX_pb1=$count_totalX_pb1 - 1)                      
                      #foreach($list1 in $maklumat_hakmilik_mmk_pb_siasatan)                     
                      #set($count_total_pb1=$count_total_pb1 + 1)                      
                      #if($maklumat_hakmilik_mmk_pb_siasatan.size() > 1 && $maklumat_hakmilik_mmk_pb_siasatan.size() != $count_total_pb1 && $count_totalXX_pb1 != $count_total_pb1) 
                      #set($nama_pb_pb_siasatan = $nama_pb_pb_siasatan +" $list1.NAMA_PB,")
                      #elseif($maklumat_hakmilik_mmk_pb_siasatan.size() > 1 && $count_totalXX_pb1 == $count_total_pb1)
                      #set($nama_pb_pb_siasatan = $nama_pb_pb_siasatan +" $list1.NAMA_PB")
                      #elseif($maklumat_hakmilik_mmk_pb_siasatan.size() > 1 && $maklumat_hakmilik_mmk_pb_siasatan.size() == $count_total_pb1)
                      #set($nama_pb_pb_siasatan = $nama_pb_pb_siasatan + " dan $list1.NAMA_PB")
                      #elseif($maklumat_hakmilik_mmk_pb_siasatan.size() == 1)
                      #set($nama_pb_pb_siasatan = $nama_pb_pb_siasatan + "$list1.NAMA_PB")                      
                      #end
                      #end
                      <!-- $nama_pb_pb_siasatan  -->

<!--
user_name :: $user_name
user_name_cap :: $user_name_cap -->
<table width="100%" id="paging">
<tr>
<td width="100%">

 #if($jenis_permohonan == "4" )
 <div align="right">
<img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan" onclick="senaraipermohonan()" onMouseOver="this.style.cursor='pointer';"  /><img src="../img/arrowgaris.png" alt="" border="0"/>#if($jenis_button == "1")<img src="../img/2current.png" alt="" border="0" title="Penarikan Balik Pengambilan Tanah Di Unit Pengambilan Tanah" />#else<img src="../img/2enable.png" alt="" border="0" title="Penarikan Balik Pengambilan Tanah Di Unit Pengambilan Tanah" onclick="screen1('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';"  />#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($id_pembatalan != "")#if($jenis_button == "2")<img src="../img/3current.png" alt="" border="0" title="Laporan Tanah" />#else<img src="../img/3enable.png" alt="" border="0" title="Laporan Tanah" onclick="screen2('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';" />#end#else<img src="../img/3disable.png" alt="" border="0" title="Laporan Tanah"/>#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($id_pembatalan != "" && $senarai_hakmilik_batal.size() == $senarai_hakmilik_batal_penarikan_status.size())#if($jenis_button == "3")<img src="../img/4current.png" alt="" border="0" title="Urusan Kertas MMK/KM/MB/LC Penarikan Balik"/>#else<img src="../img/4enable.png" alt="" border="0" title="Urusan Kertas MMK/KM/MB/LC Penarikan Balik" onclick="screen3('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';" />#end#else<img src="../img/4disable2.png" alt="" border="0" title="Urusan Kertas MMK/KM/MB/LC Penarikan Balik"/>#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($id_pembatalan != "" && $maklumat_keputusan.size()>0)#if($jenis_button == "4")<img src="../img/5current2.png" alt="" border="0" title="Maklumat Warta" />#else<img src="../img/5enable2.png" alt="" border="0" title="Maklumat Warta" onclick="screen4('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';"/>#end#else<img src="../img/5disable2.png" alt="" border="0" title="Maklumat Warta"/>#end
<img src="../img/arrowgaris.png" alt="" border="0"/>#if($id_pembatalan != "" && $maklumat_warta.size()>0)#if($jenis_button == "5")<img src="../img/6current2.png" alt="" border="0" title="Urusan Siasatan"  />#else<img src="../img/6enable.png" alt="" border="0" title="Urusan Siasatan" onclick="screen5('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';" />#end#else<img src="../img/6disable2.png" alt="" border="0" title="Urusan Siasatan"/>#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($id_pembatalan != "" && $maklumat_warta.size()>0)#if($jenis_button == "6")<img src="../img/7current.png" alt="" border="0" title="Urusan Pembayaran Pampasan"  />#else<img src="../img/7enable2.png" alt="" border="0" title="Urusan Pembayaran Pampasan" onclick="screen6('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';" />#end#else<img src="../img/7disable.png" alt="" border="0" title="Urusan Pembayaran Pampasan"/>#end</div>
#else
 <div align="right" style="display:none">
<img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan" onclick="senaraipermohonan()" onMouseOver="this.style.cursor='pointer';"  /><img src="../img/arrowgaris.png" alt="" border="0"/>#if($jenis_button == "1")<img src="../img/2current.png" alt="" border="0" title="Penarikan Balik Pengambilan Tanah Di Unit Pengambilan Tanah" />#else<img src="../img/2enable.png" alt="" border="0" title="Penarikan Balik Pengambilan Tanah Di Unit Pengambilan Tanah" onclick="screen1('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';"  />#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($id_pembatalan != "")#if($jenis_button == "2")<img src="../img/3current.png" alt="" border="0" title="Laporan Tanah" />#else<img src="../img/3enable.png" alt="" border="0" title="Laporan Tanah" onclick="screen2('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';" />#end#else<img src="../img/3disable.png" alt="" border="0" title="Laporan Tanah"/>#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($id_pembatalan != "")#if($jenis_button == "3")<img src="../img/4current.png" alt="" border="0" title="Urusan Kertas MMK/KM/MB/LC Penarikan Balik"/>#else<img src="../img/4enable.png" alt="" border="0" title="Urusan Kertas MMK/KM/MB/LC Penarikan Balik" onclick="screen3('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';" />#end#else<img src="../img/4disable.png" alt="" border="0" title="Laporan Tanah Penarikan Balik"/>#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($id_pembatalan != "" && $maklumat_keputusan.size()>0)#if($jenis_button == "4")<img src="../img/5current2.png" alt="" border="0" title="Maklumat Warta" />#else<img src="../img/5enable2.png" alt="" border="0" title="Maklumat Warta" onclick="screen4('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';"/>#end#else<img src="../img/5disable2.png" alt="" border="0" title="Maklumat Warta"/>#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($id_pembatalan != "" && $maklumat_warta.size()>0)#if($jenis_button == "5")<img src="../img/6current2.png" alt="" border="0" title="Urusan Siasatan"  />#else<img src="../img/6enable.png" alt="" border="0" title="Urusan Siasatan" onclick="screen5('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';" />#end#else<img src="../img/6disable2.png" alt="" border="0" title="Urusan Siasatan"/>#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($id_pembatalan != "" && $maklumat_warta.size()>0)#if($jenis_button == "6")<img src="../img/7current.png" alt="" border="0" title="Urusan Pembayaran Pampasan"  />#else<img src="../img/7enable2.png" alt="" border="0" title="Urusan Pembayaran Pampasan" onclick="screen6('$id_permohonan','$id_pembatalan')" onMouseOver="this.style.cursor='pointer';" />#end#else<img src="../img/7disable.png" alt="" border="0" title="Urusan Pembayaran Pampasan"/>#end</div>
 
 #end


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
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}

function screen2(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "LaporanTanah";
	document.${formName}.sub_command.value = "view";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
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
function screen3(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Penyediaan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();

}

function screen4(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "Warta";
	document.${formName}.sub_command.value = "Warta";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();

}

function screen6(id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}


function screen5(id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}


function senaraipermohonan()
{

if('$flag_MyInfoPPT' == "yes")
{
document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.ppt.MyInfoPPT&no_fail=$no_fail";
document.${formName}.method="POST";
}
else if('$flag_UtilitiKemaskiniFail' == "yes")
{
document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.UtilitiKemaskiniFail&txtNoFail=$no_fail";
document.${formName}.method="POST";
}
else
{

	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
}
	
	document.${formName}.submit();
}
</script>


