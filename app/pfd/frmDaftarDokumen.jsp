<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
.style4 {color: #0000FF}
-->
</style>
<input name="idLogDokumen" type="hidden" value="$idLogDokumen" />
<input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
<input name="carianfail" id="carianfail" type="hidden" value="$!carianfail"/>
&nbsp;



<table width="100%">
  #if($carianfail == 'false')
  <tr>
    <td>
    <fieldset>
	<legend>CARIAN DOKUMEN DALAM FAIL</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td width="29%" align="right" scope="row"><div align="right">No Rujukan Lain</div></td>
    <td width="1%" ><div align="right" class="style2">:</div></td>
    <td width="31%">
      <label>
      <input name="noRujukanLain" type="text" id="noRujukanLain" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$!noRujukanLain" size="44" />
        </label>
       <input type="hidden" name="idFail" id="idFail" />       </td>
    <td width="39%">&nbsp;</td>
  </tr>
  <tr>
    <td align="right" scope="row">No Rujukan Dokumen</td>
    <td >:</td>
    <td><input name="noRujukanDokumen" type="text" id="noRujukanDokumen" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$!noRujukanDokumen" size="44" /></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td valign="top" align="right" scope="row"><div align="right">Tajuk Dokumen</div></td>
    <td width="1%" valign="top"><div align="right" class="style2">:</div></td>
    <td>
      <label>
      <textarea name="tajukDokumen" cols="41" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" id="tajukDokumen">$!tajukDokumen</textarea>
        </label>       </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Tarikh Daftar Dokumen</div></td>
    <td width="1%" ><div align="right" class="style2">:</div></td>
    <td>
      <label>
      <input name="tarikhDaftarDokumen" type="text" id="tarikhDaftarDokumen" value="$!tarikhDaftarDokumen" size="10" />
        </label>
        <a href="javascript:displayDatePicker('tarikhDaftarDokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
    <td><a href="javascript:kembalicarianfail()" class="style4">Tutup Carian Dokumen</a></td>
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
      </label></td>
    <td>&nbsp;</td>
  </tr>
</table>
	</fieldset>
	</td>
  </tr>
  #end
  #if($carianfail == 'true')
  <tr>
    <td>
    <fieldset>
	<legend>CARIAN FAIL</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td width="29%" align="right" scope="row"><div align="right">No Fail</div></td>
    <td width="1%" ><div align="right" class="style2">:</div></td>
    <td width="31%">
      <label>
      <input name="txtNoFail" type="text" id="txtNoFail" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$txtNoFail" size="44" />
        </label>
       <input type="hidden" name="idFail" id="idFail" />       </td>
    <td width="39%">&nbsp;</td>
  </tr>
   <tr>
    <td scope="row" align="right">No Fail Lama</td>
    <td scope="row"><span class="style2">:</span></td>
    <td>
      <label>
      <input name="txtNoFailLama" type="text" id="txtNoFailLama" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$!txtNoFailLama" size="44" />
        </label></td>
  </tr>
  <tr>
    <td valign="top" align="right" scope="row"><div align="right">Tajuk Fail</div></td>
    <td width="1%" valign="top"><div align="right" class="style2">:</div></td>
    <td>
      <label>
      <textarea name="txtTajukFail" cols="41" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" id="txtTajukFail">$txtTajukFail</textarea>
        </label>       </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Negeri</div></td>
    <td width="1%" ><div align="right" class="style2">:</div></td>
    <td>
   $selectNegeriD   </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Seksyen</div></td>
    <td width="1%" ><div align="right" class="style2">:</div></td>
    <td>
      <label></label> 
      $selectSeksyenD       </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Status</div></td>
    <td width="1%"><div align="right" class="style2">:</div></td>
    <td>
      <label></label> 
      $selectStatusD     </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Tarikh Daftar</div></td>
    <td width="1%" ><div align="right" class="style2">:</div></td>
    <td>
      <label>
      <input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$!txdTarikhDaftar" size="10" />
        </label>
        <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>      </td>
    <td><a href="javascript:cariandokumen()" class="style4">Buka Carian Dokumen Dalam Fail</a></td>
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
      
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
              </td>
    <td>&nbsp;</td>
  </tr>
</table>
	</fieldset>
	</td>
  </tr>
  #end
  <tr>
    <td>
    <fieldset>
	<legend>SENARAI FAIL</legend>
	Jumlah Keseluruhan Fail : $JumlahFail
	#set ($pagingTitle = "Jumlah Carian") 
	#parse("app/utils/record_paging.jsp") 
<table width="100%" align="center" >
    <tr class="table_header">
    <td width="1%" scope="row"><strong>NO</strong></td>
    <td width="20%"><strong>NO FAIL</strong></td>
     <td width="20%"><strong>NO FAIL LAMA</strong></td>
    <td width="29%"><strong>TAJUK FAIL</strong></td>
    <td width="20%"><strong>STATUS FAIL</strong></td>
    <!--<td width="10%">NO RUJUKAN DOKUMEN</td>
    <td width="20%">JENIS DOKUMEN</td>
    <td width="20%">TAJUK DOKUMEN</td>-->
  </tr>
   #foreach ($fail in $SenaraiFail)
   #if ($fail.bil == '') 
  	#set ($row = 'row1')
  #elseif ($fail.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$fail.bil</td>
    #if($idLogDokumen != '')
    	#if($fail.keterangan1 == 'HILANG')
         	#if ($fail.bil != '') 
            <td class="$row">$!fail.no_Fail</td>
        	#else
            <td class="$row">$fail.no_Fail</td>
    		#end
		#else
        	#if ($fail.bil != '') 
            <td class="$row"><a href="javascript:edit_item1('$fail.id_Fail','$idLogDokumen')" class="style1">$!fail.no_Fail</a></td>
        	#else
            <td class="$row">$fail.no_Fail</td>
    		#end
        #end
    #else
    	#if($fail.keterangan1 == 'HILANG')
             #if ($fail.bil != '') 
            <td class="$row">$!fail.no_Fail</td>
            #else
            <td class="$row">$fail.no_Fail</td>
            #end
        #else
            #if ($fail.bil != '') 
            <td class="$row"><a href="javascript:edit_item2('$fail.id_Fail','$idLogDokumen')" class="style1">$!fail.no_Fail</a></td>
            #else
            <td class="$row">$fail.no_Fail</td>
            #end
        #end
    #end
     <td class="$row">$fail.no_Fail_Asal</td>
    <td class="$row">$Util.subString($fail.tajuk_Fail,0,50)</td>
    <td class="$row">$fail.keterangan1</td>
    <!--<td class="$row">$fail.no_Ruj_Dok</td>
    <td class="$row">$fail.jns_Dok</td>
    <td class="$row">$fail.tajuk_Dokumen</td>-->
  </tr>
  #end
</table>
	</fieldset>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-05</strong></td>
  </tr>
</table>

<script>
function kembalicarianfail(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&carianfail=true";
	document.${formName}.submit();
}
function cariandokumen(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&carianfail=false";
	document.${formName}.submit();
}
function edit_item1(a,b){
	
	document.${formName}.action =  "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&pagemode=1&action1=tabDokumenMasuk&paparArahan=false&idFail="+a+"&paparArahan=false&idLogdokumen="+b;
	//document.${formName}.idFail.value = id;
	document.${formName}.submit();
}
function edit_item2(a,b){
	
	document.${formName}.action =  "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk&paparArahan=false&idFail="+a+"&idLogdokumen="+b;
	//document.${formName}.idFail.value = id;
	document.${formName}.submit();
}
function carian(){
	document.${formName}.submit();
}
function kosongkan(){
	document.${formName}.reset();
	
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoFailLama.value = "";
	document.${formName}.txtTajukFail.value = "";
	document.${formName}.socNegeriD.value = "";
	document.${formName}.socSeksyenD.value = "";
	document.${formName}.socStatusD.value = "";
	document.${formName}.txdTarikhDaftar.value = "";
	document.${formName}.socJenisDokumenD.value = "";
	document.${formName}.txtTajukDokumen.value = "";
	document.${formName}.noRujukanLain.value = "";
	document.${formName}.noRujukanDokumen.value = "";
	document.${formName}.tajukDokumen.value = "";
	document.${formName}.tarikhDaftarDokumen.value = "";
	
}
function doChanges() {
	doAjaxCall${formName}("doChanges");
}

</script>
<script language="JavaScript"> document.forms[0].txtNoRujukanDok.focus(); </script>