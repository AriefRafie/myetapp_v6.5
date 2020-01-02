<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {
	color: #FF0000
}
-->
</style>

#if($carianfail == 'true')
<fieldset>
	<legend>CARIAN FAIL</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td align="right" scope="row">Jenis Senarai Yang Diperlukan</td>
    <td >:</td>
    <td><select name="socJenisSenarai" id="socJenisSenarai"/>
    	#if ($jenis_Senarai == '1')
        
        <option value="0">SILA PILIH</option>
        <option value="1" selected="selected">SENARAI LOG DOKUMEN</option>
        <option value="2">SENARAI AGIHAN TUGASAN PENGARAH/KPP</option>
        <option value="3">SENARAI PENERIMA AGIHAN TUGASAN</option>
        <option value="4">SENARAI PERGERAKAN FAIL</option>
        <option value="5">SENARAI PANGGILAN MESYUARAT</option>

        #elseif ($jenis_Senarai == '2')
        
        <option value="0">SILA PILIH</option>
        <option value="1">SENARAI LOG DOKUMEN</option>
        <option value="2" selected="selected">SENARAI AGIHAN TUGASAN PENGARAH/KPP</option>
        <option value="3">SENARAI PENERIMA AGIHAN TUGASAN</option>
        <option value="4">SENARAI PERGERAKAN FAIL</option>
        <option value="5">SENARAI PANGGILAN MESYUARAT</option>
        
        #elseif ($jenis_Senarai == '3')
        
        <option value="0">SILA PILIH</option>
        <option value="1">SENARAI LOG DOKUMEN</option>
        <option value="2">SENARAI AGIHAN TUGASAN PENGARAH/KPP</option>
        <option value="3" selected="selected">SENARAI PENERIMA AGIHAN TUGASAN</option>
        <option value="4">SENARAI PERGERAKAN FAIL</option>
        <option value="5">SENARAI PANGGILAN MESYUARAT</option>
        
        #elseif ($jenis_Senarai == '4')
        
        <option value="0">SILA PILIH</option>
        <option value="1">SENARAI LOG DOKUMEN</option>
        <option value="2">SENARAI AGIHAN TUGASAN PENGARAH/KPP</option>
        <option value="3">SENARAI PENERIMA AGIHAN TUGASAN</option>
        <option value="4" selected="selected">SENARAI PERGERAKAN FAIL</option>
        <option value="5">SENARAI PANGGILAN MESYUARAT</option>
        
        #elseif ($jenis_Senarai == '5')
        
        <option value="0">SILA PILIH</option>
        <option value="1">SENARAI LOG DOKUMEN</option>
        <option value="2">SENARAI AGIHAN TUGASAN PENGARAH/KPP</option>
        <option value="3">SENARAI PENERIMA AGIHAN TUGASAN</option>
        <option value="4">SENARAI PERGERAKAN FAIL</option>
        <option value="5" selected="selected">SENARAI PANGGILAN MESYUARAT</option>
        
        #else
        
        <option value="0" selected="selected">SILA PILIH</option>
        <option value="1">SENARAI LOG DOKUMEN</option>
        <option value="2">SENARAI AGIHAN TUGASAN PENGARAH/KPP</option>
        <option value="3">SENARAI PENERIMA AGIHAN TUGASAN</option>
        <option value="4">SENARAI PERGERAKAN FAIL</option>
        <option value="5">SENARAI PANGGILAN MESYUARAT</option>
        
        #end
      
      
      </select>

    </td>
    <td>&nbsp;</td>
  </tr>
    <tr>
    <td width="29%" align="right" scope="row"><div align="right">No Fail / No Rujukan Dokumen</div></td>
    <td width="1%" ><div align="right" >:</div></td>
    <td width="31%">
      <label>
      <input name="txtNoFail" type="text" id="txtNoFail" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$!txtNoFail" size="44" />
      </label>
       <input type="hidden" name="idFail" id="idFail" />       </td>
    <td width="39%">&nbsp;</td>
  </tr>
  <tr>
    <td valign="top" align="right" scope="row"><div align="right">Tajuk Fail / Tajuk Dokumen / Tajuk Mesyuarat</div></td>
    <td width="1%" valign="top"><div align="right" >:</div></td>
    <td>
      <label>
      <textarea name="txtTajukFail" cols="41" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" id="txtTajukFail">$!txtTajukFail</textarea>
        </label>       </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Tarikh </div></td>
    <td width="1%" ><div align="right" >:</div></td>
    <td>
      <label>
      <input name="txdTarikh" type="text" id="txdTarikh" value="$!txdTarikh" size="10" />
      </label>
        <a href="javascript:displayDatePicker('txdTarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>      </td>
    <td>&nbsp;</td>
  </tr>
    <tr>
    <td align="right" scope="row">&nbsp;</td>
    <td >&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="right" scope="row">
      <label></label>        </td>
    <td align="right" scope="row">&nbsp;</td>
    <td>
      <label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
      </label>        </td>
    <td>&nbsp;</td>
  </tr>
</table>
 </fieldset>
 #end
</br>

#if ($pagemode == "1")
#parse("app/pfd/frmKemaskiniPengesahanPinjamanFail.jsp")
#elseif ($pagemode == "2")
#parse("app/pfd/frmLogDokumenSelesai.jsp")
#elseif ($pagemode == "3")
#parse("app/pfd/frmLogAgihanTugasanTindakan.jsp")
#elseif ($pagemode == "4")
#parse("app/pfd/frmLogPenerimaTugasanTindakan.jsp")
#elseif ($pagemode == "5")
#parse("app/pfd/frmLogTugasanSelesai.jsp")
#else
#if($viewListLogDokumen == 'true')
<fieldset>
  <legend>Senarai Log Dokumen</legend>
  <table width="100%" >
  <tr class="table_header">
    <td>NO</td>
    <td>TAJUK DOKUMEN</td>
    <td>NO RUJUKAN DOKUMEN</td>
    <td>TARIKH DOKUMEN</td>
    <td>TARIKH TERIMA DOKUMEN</td>
    <td>STATUS DOKUMEN</td>
    <td>JENIS DOKUMEN</td>
  </tr>
  #foreach ($listLogDokumenByUserId in $SenaraiDokumenByUserId)
  #if ($listLogDokumenByUserId.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listLogDokumenByUserId.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listLogDokumenByUserId.bil</td>
    #if($listLogDokumenByUserId.flag_Logdokumen == '1')
    	#if ($listLogDokumenByUserId.bil != '') 
    		<td class="$row"><a href="javascript:edit_logDokumen('$listLogDokumenByUserId.idLogDokumen','$listLogDokumenByUserId.flag_Logdokumen','$listLogDokumenByUserId.idFail')" class="style1">$listLogDokumenByUserId.tajuk_Dokumen</a></td>
    	#else
      		<td class="$row">$!listLogDokumenByUserId.tajuk_Dokumen</td>   
    	#end
    #elseif($listLogDokumenByUserId.flag_Logdokumen == '2')
     	#if ($listLogDokumenByUserId.bil != '') 
    		<td class="$row"><a href="javascript:edit_logDokumenKeluar('$listLogDokumenByUserId.idLogDokumen','$listLogDokumenByUserId.flag_Logdokumen','$listLogDokumenByUserId.idDokumen')" class="style1">$listLogDokumenByUserId.tajuk_Dokumen</a></td>
    	#else
        	<td class="$row">$!listLogDokumenByUserId.tajuk_Dokumen</td>   
    	#end
     #elseif($listLogDokumenByUserId.flag_Logdokumen == '3')
        #if ($listLogDokumenByUserId.bil != '') 
    		<td class="$row"><a href="javascript:edit_logDokumenSeksyenLain('$listLogDokumenByUserId.idLogDokumen','$listLogDokumenByUserId.flag_Logdokumen','$listLogDokumenByUserId.idDokumen')" class="style1">$listLogDokumenByUserId.tajuk_Dokumen</a></td>
    	#else
        	<td class="$row">$!listLogDokumenByUserId.tajuk_Dokumen</td>   
    	#end
    #elseif($listLogDokumenByUserId.flag_Logdokumen == '5')
     	#if ($listLogDokumenByUserId.bil != '') 
    		<td class="$row"><a href="javascript:edit_logDokumenDalaman('$listLogDokumenByUserId.idLogDokumen','$listLogDokumenByUserId.flag_Logdokumen','$listLogDokumenByUserId.idDokumen')" class="style1">$listLogDokumenByUserId.tajuk_Dokumen</a></td>
    	#else
        	<td class="$row">$!listLogDokumenByUserId.tajuk_Dokumen</td>   
    	#end
    #else
        #if ($listLogDokumenByUserId.bil != '') 
    		<td class="$row"><a href="javascript:edit_logDokumenSeksyenKPTG('$listLogDokumenByUserId.idLogDokumen','$listLogDokumenByUserId.flag_Logdokumen','$listLogDokumenByUserId.idLogDokumenKPTG')" class="style1">$listLogDokumenByUserId.tajuk_Dokumen</a></td>
    	#else
        	<td class="$row">$!listLogDokumenByUserId.tajuk_Dokumen</td> 
    	#end
    #end
   	<td class="$row">$listLogDokumenByUserId.no_Rujukan_Lain</td>   
	<td class="$row">$listLogDokumenByUserId.tarikh_Dokumen</td>
    <td class="$row">$listLogDokumenByUserId.tarikh_DokumenDiterima</td>
    #if ($listLogDokumenByUserId.status_Logdokumen == '1') 
     <td class="$row"><blink>DALAM TINDAKAN<blink></td>
    #elseif($listLogDokumenByUserId.status_Logdokumen == '2') 
     <td class="$row">TELAH DILULUSKAN</td>
    #elseif($listLogDokumenByUserId.status_Logdokumen == '3') 
     <td class="$row">TELAH DIPULANGKAN</td>
    #else 
     <td class="$row"></td>
  	#end
     #if($listLogDokumenByUserId.flag_Logdokumen == '1')
    <td class="$row">DOKUMEN MASUK</td>
     #elseif($listLogDokumenByUserId.flag_Logdokumen == '2')
      <td class="$row">DOKUMEN KELUAR</td>
      #elseif($listLogDokumenByUserId.flag_Logdokumen == '3')
       <td class="$row">DOKUMEN DARI SEKSYEN LAIN</td>
       #elseif($listLogDokumenByUserId.flag_Logdokumen == '5')
      <td class="$row">DOKUMEN DALAMAN</td>
     #else
      <td class="$row"></td>
     #end
  </tr>
  #end 
</table>
<table>
  	<tr>
    	<td><a href ="javascript:selesai_logDokumen()" class="style2"><u>Log Dokumen Yang Telah Diambil Tindakan</u></a></td>
   	</tr>
  </table>
</fieldset>
#end
&nbsp;
#if($viewListAgihanTugasan == 'true')
<fieldset>
  <legend>Senarai Agihan Tugasan  Pengarah/KPP/Pegawai Berkenaan</legend>
<table width="100%" >
  <tr class="table_header">
    <td>NO</td>
    <td>NO RUJUKAN DOKUMEN</td>
    <td>TAJUK DOKUMEN</td>
    <td>PENGIRIM DOKUMEN</td>
    <td>TARIKH DOKUMEN</td>
  </tr>
  #foreach ($listAgihanTugasanByUserId in $SenaraiAgihanTugasanByUserId)
  
  #if ($listAgihanTugasanByUserId.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listAgihanTugasanByUserId.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listAgihanTugasanByUserId.bil</td>
    #if ($listAgihanTugasanByUserId.bil != '') 
    <td class="$row">
    <a href="javascript:edit_agihanTugasan('$listAgihanTugasanByUserId.idDokumen','$listAgihanTugasanByUserId.idFail')" class="style1">$listAgihanTugasanByUserId.no_Rujukan_Dokumen</a>    </td>
    #else
        <td class="$row">$listAgihanTugasanByUserId.no_Rujukan_Dokumen</td>   
    #end
	<td class="$row">$listAgihanTugasanByUserId.tajuk_Dokumen</td>
    <td class="$row">$listAgihanTugasanByUserId.nama_Pengirim</td>
    <td class="$row">$listAgihanTugasanByUserId.tarikh_Dokumen</td>
  </tr>
  #end
</table>
 <table>
    	<tr>
    	<td colspan="5">
       <a href ="javascript:agihan_logAgihanTugasan()" class="style2"><u>Log Tugasan Yang Telah Diagihkan</u></a> || <a href ="javascript:agihan_logAgihanTugasanSelesai()" class="style2"><u>Log Tugasan Selesai</u></a>
 </td>
    </tr>
</table>
</fieldset>
#end
&nbsp;
#if($viewListPenerimaTugasan == 'true')
<fieldset>
  <legend>Senarai Penerima Agihan Tugasan</legend>
<table width="100%" >
  <tr class="table_header">
    <td>NO</td>
    <td>NO RUJUKAN DOKUMEN</td>
    <td>TAJUK DOKUMEN</td>
    <td>PENGIRIM DOKUMEN</td>
    <td>TARIKH DOKUMEN</td>
  </tr>
  #foreach ($listPenerimaTugasanByUserId in $SenaraiPenerimaTugasanByUserId)
  
  #if ($listPenerimaTugasanByUserId.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listPenerimaTugasanByUserId.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listPenerimaTugasanByUserId.bil</td>
    #if ($listPenerimaTugasanByUserId.bil != '') 
    <td class="$row">
    <a href="javascript:edit_penerimaTugasan('$listPenerimaTugasanByUserId.idMinitArahan','$listPenerimaTugasanByUserId.idDokumen','$listPenerimaTugasanByUserId.idFail')" class="style1">$listPenerimaTugasanByUserId.no_Rujukan_Dokumen</a>    </td>
    #else
        <td class="$row">$listPenerimaTugasanByUserId.no_Rujukan_Dokumen</td>   
    #end
	<td class="$row">$listPenerimaTugasanByUserId.tajuk_Dokumen</td>
    <td class="$row">$listPenerimaTugasanByUserId.nama_Pengirim</td>
    <td class="$row">$listPenerimaTugasanByUserId.tarikh_Dokumen</td>
  </tr>
  #end
</table>
   <table>
    	<tr>
    	<td colspan="5">
      <a href ="javascript:agihan_logPenerimaTugasan()" class="style2" /><u>Log Penerima Yang Telah Diagihkan</u></a> || <a href ="javascript:agihan_logAgihanTugasanSelesai()" class="style2"><u>Log Tugasan Selesai</u></a>
      </td>
</tr>
  </table>
</fieldset>
#end
&nbsp;
#if($viewListPergerakanFail == 'true')
<fieldset>
  <legend>Senarai Pergerakan Fail</legend>
    <table width="100%">
    <tr class="table_header">
      <td>NO</td>
      <td>NO FAIL</td>
      <td>TAJUK FAIL</td>
      <td>DIPOHON OLEH</td>
      <td>TARIKH PINJAMAN DILULUSKAN</td>
      <td>TARIKH PEMULANGAN DILULUSKAN</td>
      <td>DILULUSKAN OLEH</td>
      <td>STATUS PERGERAKAN FAIL</td>
    </tr>
	#foreach ($senarai in $senaraiPergerakanFail)
  
  	#if ($senarai.bil == '') 
  	#set ($row = 'row1')
  	#elseif ($senarai.bil % 2 != 0)
  	#set ($row = 'row1')
  	#else 
  	#set ($row = 'row2')
  	#end  
  <tr>
    <td class="$row">$senarai.bil</td>
    <td class="$row">
    #if($senarai.status_pergerakan_fail == "1" )
    <a href="javascript:edit_item1('$senarai.idPergerakanfail','$senarai.idFail','$senarai.status_pergerakan_fail')" class="style1">$!senarai.no_Fail</a>
     #elseif($senarai.status_pergerakan_fail == "2" )
    <a href="javascript:edit_item2('$senarai.idPergerakanfail','$senarai.idFail','$senarai.status_pergerakan_fail')" class="style1">$!senarai.no_Fail</a>
      #elseif($senarai.status_pergerakan_fail == "3" )
    <a href="javascript:edit_item3('$senarai.idPergerakanfail','$senarai.idFail','$senarai.status_pergerakan_fail')" class="style1">$!senarai.no_Fail</a>
    #elseif($senarai.status_pergerakan_fail == "4" )
    <a href="javascript:edit_item4('$senarai.idPergerakanfail','$senarai.idFail','$senarai.status_pergerakan_fail')" class="style1">$!senarai.no_Fail</a>
    #elseif($senarai.status_pergerakan_fail == "0" )
    <a href="javascript:edit_item('$senarai.idPergerakanfail','$senarai.idFail','$senarai.status_pergerakan_fail')" class="style1">$!senarai.no_Fail</a>    </td>
    #else
    <td class="$row">$!senarai.no_Fail</td>
    #end
    <td class="$row">$!senarai.nama_fail</td>
    <td class="$row">$!senarai.peminjam_fail</td>
    <td class="$row"> $!senarai.tarikh_sah_pinjamfail    </td>
    <td class="$row">$!senarai.tarikh_sah_pulangfail    </td>
    <td class="$row"> $!senarai.nama_pt_fail</td>
      <td class="$row">
    #if($senarai.status_pergerakan_fail == "1" )
   	<blink>DALAM TINDAKAN</blink>
    #elseif($senarai.status_pergerakan_fail == "2" )
	TELAH DILULUSKAN
    #elseif($senarai.status_pergerakan_fail == "3" )
   	TELAH DIPULANGKAN
    #elseif($senarai.status_pergerakan_fail == "4" )
    SEDANG DIPINJAM
    #elseif($senarai.status_pergerakan_fail == "0" )
    TIADA MAKLUMBALAS</td>
    #else
    <td class="$row">$!senarai.status_pergerakan_fail</td>
    #end
  </tr>
  #end
  </table> 
   <table>
  	<tr>
    	<td><a href ="javascript:selesai_logPengesahanFail()" class="style2"><u>Log Pergerakan Fail</u></a></td>
   	</tr>
  </table>
</fieldset>
#end
#end
<!-- 
<br />
#if($!viewListMesyuarat)
<fieldset>
  <legend>Senarai Panggilan Mesyuarat</legend>
 
  <table width="100%" >
   
    <tr class="table_header">
      <td width="5%" scope="row" align="center">No</td>
      <td width="15%">No Fail</td>
      <td width="25%">Tajuk</td>
      <td width="10%" align="center">Tarikh</td>
      <td width="15%" align="center">Masa</td>
    </tr>
    
    #if($ListMesyuarat.size()>0)
    
#foreach ($fail in $ListMesyuarat)
	#if ($fail.ListNo == '') 
    	#set ($row = 'row1')
    #elseif ($fail.ListNo % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
   
      <td class="$row" valign="top" align="center">$fail.ListNo</td>
      <td class="$row" valign="top"><a href="javascript:viewMesyuarat('$fail.ListIDMesyuarat')" style="color:#0000FF">$fail.ListNoFail</a></td>
      <td class="$row" valign="top">$fail.ListTajuk</td>
      <td class="$row" valign="top" align="center">$fail.ListTarikh</td>
      <td class="$row" valign="top" align="center">$fail.ListMasa</td>   
    </tr>
    #end
    #else    
     <tr>
      <td colspan="8">Tiada Rekod</td>
    </tr>
     #end 
  </table>
     <table>
  	<tr>
    	<td><a href ="javascript:selesai_LogMesyuarat()" class="style2"><u>Log Panggilan Mesyuarat</u></a></td>
   	</tr>
  </table>
</fieldset>
#end
 -->

<input name="idFail" type="hidden" value="$idFail" />
<input name="idMasuk" type="hidden" value="$idMasuk" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<input name="idPergerakanfail" type="hidden" value="$idPergerakanfail" />
<input name="viewListMesyuarat" type="hidden" value="$viewListMesyuarat" />
<input name="carianfail" id="carianfail" type="hidden" value="$!carianfail"/>

<script>
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan";
	document.${formName}.submit();
}
function edit_item1(idPergerakan,idFail){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=pengesahanFail&statusPergerakan=1&idPergerakanfail="+idPergerakan+"&idFail="+idFail;
	document.${formName}.submit();

}
function edit_item2(idPergerakan,idFail){

document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=pengesahanFail&statusPergerakan=2&idPergerakanfail="+idPergerakan+"&idFail="+idFail;
	document.${formName}.submit();

}
function edit_item3(idPergerakan,idFail){

document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=pengesahanFail&statusPergerakan=3&idPergerakanfail="+idPergerakan+"&idFail="+idFail;
	document.${formName}.submit();

}
function edit_item4(idPergerakan,idFail){

document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=pengesahanFail&statusPergerakan=4&idPergerakanfail="+idPergerakan+"&idFail="+idFail;
	document.${formName}.submit();

}
function edit_logDokumen(idLogDokumen,flag_Logdokumen,idFail){
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk&pagemode=1&idLogDokumen="+idLogDokumen+"&id_Fail="+idFail;
	document.${formName}.submit();
}
function edit_logDokumenKeluar(idLogDokumen,flag_Logdokumen,idDokumen){
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenKeluar&idLogDokumen="+idLogDokumen+"&pagemode=1&idDokumen="+idDokumen;
	document.${formName}.submit();

}
function edit_logDokumenDalaman(idLogDokumen,flag_Logdokumen,idDokumen){
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenDalaman&idLogDokumen="+idLogDokumen+"&pagemode=1&idDokumen="+idDokumen;
	document.${formName}.submit();

}

function edit_logDokumenSeksyenLain(idLogDokumen,flag_Logdokumen,idDokumen){

	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenDariSeksyenLain&idLogDokumen="+idLogDokumen+"&pagemode=1&idDokumen="+idDokumen;
	document.${formName}.submit();

}

function edit_logDokumenSeksyenKPTG(idLogDokumen,flag_Logdokumen,idLogDokumenKPTG){

	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenDariSeksyenLain&idLogDokumen="+idLogDokumen+"&idLogDokumenKPTG="+idLogDokumenKPTG+"&pagemode=2";
	document.${formName}.submit();

}

function viewMesyuarat(ID_MESYUARAT) {	

document.${formName}.action = "$EkptgUtil.getTabID("Mesyuarat",$portal_role)?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=daftarMinit&ID_MESYUARAT=" + ID_MESYUARAT;
	document.${formName}.submit();
	
}

function edit_agihanTugasan(idDokumen,idFail){
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar&idDokumen="+idDokumen+"&idFail="+idFail+"&paparArahan=true";
	document.${formName}.submit();
}

function edit_penerimaTugasan(idMinitArahan,idDokumen,idFail){
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar&idMinitArahan="+idMinitArahan+"&idDokumen="+idDokumen+"&idFail="+idFail+"&paparArahan=true";
	document.${formName}.submit();
}
function selesai_logDokumen(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=logDokumenSelesai";
	document.${formName}.submit();
}
function agihan_logAgihanTugasan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=agihanTugasanTindakan";
	document.${formName}.submit();
}
function selesai_logAgihanTugasan(id){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=agihanTugasanSelesai&idDokumen="+id;
	document.${formName}.submit();
}
function selesai_logPengesahanFail(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=pengesahanFailSelesai";
	document.${formName}.submit();
}
function agihan_logPenerimaTugasan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=penerimatugasanTindakan";
	document.${formName}.submit();
}
function selesai_logPenerimaTugasan(id){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=penerimatugasanSelesai&idDokumen="+id;
	document.${formName}.submit();
}
function carian() {
		if (document.${formName}.socJenisSenarai.value == ""){
			alert('Sila masukkan " Jenis Senarai Tugasan " terlebih dahulu.');
			document.${formName}.socJenisSenarai.focus();
			return;
		}

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=cariSenaraifail&jenisSenarai="+document.${formName}.socJenisSenarai.value;
	document.${formName}.submit();
}

function kosongkan(){
	
	document.${formName}.reset();
	document.${formName}.socJenisSenarai.value = "0";
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtTajukFail.value = "";
	document.${formName}.txdTarikh.value = "";	
	return;
}
function selesai_LogMesyuarat(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=cariSenaraifail&jenisSenarai="+document.${formName}.socJenisSenarai.value;
	document.${formName}.submit();
}
function agihan_logAgihanTugasanSelesai(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=agihanTugasanSelesai";
	document.${formName}.submit();
}
</script>
