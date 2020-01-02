<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
-->
</style>

<table width="100%">
  <tr>
    <td><fieldset>
<legend>
CARIAN</legend>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!user_negeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
<table width="100%" border="0" align="center">
  <tr>
    <td width="29%" scope="row" align="right">No. Fail</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td width="70%">
      <label>
      <input name="txtNoFail" type="text" id="txtNoFail" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$!txtNoFail" size="44" />
        </label>
      <input type="hidden" name="idFail" />
      <input type="hidden" name="mode" /></td>
  </tr>
  <tr>
    <td width="29%" scope="row" align="right">No. Fail Lama</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td width="70%">
      <label>
      <input name="txtNoFailLama" type="text" id="txtNoFailLama" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$!txtNoFailLama" size="44" />
        </label></td>
  </tr>
  <tr>
    <td scope="row" align="right" valign="top">Tajuk Fail</td>
    <td width="1%" scope="row" valign="top"><span class="style2">:</span></td>
    <td>
      <label>
      <textarea name="txtTajukFail" cols="41" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" id="txtTajukFail">$!txtTajukFail</textarea>
        </label>    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Negeri</td>
   <td width="1%" scope="row"><span class="style2">:</span></td>
<td>
        $selectNegeriD    </td>
  </tr>
  <tr>
  <!--#if($user_negeri == '16')#else Unit #end-->
    <td scope="row" align="right">Seksyen </td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td>
    <!--#if($user_negeri == '16')  #else $selectUnit #end -->
     $selectSeksyenD</td>
  </tr>
  <tr>
    <td scope="row" align="right">Status</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td>
     
    $selectStatusD</td>
  </tr>
  <tr>
    <td scope="row" align="right">Tarikh Daftar</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td>
      <input name="txtTarikhDaftar" type="text" id="txtTarikhDaftar" value="$!txtTarikhDaftar" size="10" />
      <a href="javascript:displayDatePicker('txtTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>   
    </td>
  </tr>
  <tr>
    <td colspan="2" scope="row">
      <label></label>    </td>
    <td>
     
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
            </td>
  </tr>
</table>
</fieldset></td>
  </tr>
  <tr>
    <td>
    
    <fieldset>
<legend>SENARAI FAIL</legend>
 Jumlah Keseluruhan Fail : $JumlahFail
#set ($pagingTitle = "Jumlah Carian") #parse("app/utils/record_paging.jsp") 
<table width="100%" align="center" >
  #if($myrole == '(PFD)Pembantu Tadbir' || $myrole == 'adminpfd' )
  <tr>
    <td colspan="2" scope="row">
    #if($idNegeri == '16')
    <input type="button" name="cmdTambah" id="cmdTambah" value="Daftar Fail" onclick="tambahFailSeksyen()"/>
    <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Senarai" onclick="cetak(document.${formName}.txtNoFail.value,document.${formName}.txtTajukFail.value,document.${formName}.socNegeriD.value,document.${formName}.socSeksyenD.value,document.${formName}.socStatusD.value,document.${formName}.txtTarikhDaftar.value)" />
   	#else
     <input type="button" name="cmdTambah" id="cmdTambah" value="Daftar Fail" onclick="tambahFailNegeri()"/>
    <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Senarai" onclick="cetak(document.${formName}.txtNoFail.value,document.${formName}.txtTajukFail.value,document.${formName}.socNegeriD.value,document.${formName}.socSeksyenD.value,document.${formName}.socStatusD.value,document.${formName}.txtTarikhDaftar.value)" />
    #end
    </td>
    <td colspan="2" align="right">&nbsp;</td>
  </tr>
   #end
  <tr class="table_header">
    <td width="1%" scope="row">NO</td>
    <td width="20%">NO. FAIL</td>
    <td width="20%">NO. FAIL LAMA</td>
    
    <td width="41%">TAJUK FAIL</td>
    <td width="20%">STATUS FAIL</td>
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
        #if ($fail.bil != '') 
            <td class="$row"><a href="javascript:edit('$fail.id_Fail','$fail.keterangan1')" class="style1">$fail.no_Fail</a></td>
        #else
            <td class="$row">$fail.no_Fail</td>
        #end
    <td class="$row">$fail.no_Fail_Asal</td>
    <td class="$row"><a href="javascript:edit('$fail.id_Fail','$fail.keterangan1')" class="style1">$Util.subString($fail.tajuk_Fail,0,50)</a></td>
    <td class="$row">$fail.keterangan1</td>
  </tr>
  #end
</table>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right">
     
    </td>
  </tr>
</table>


 	
</fieldset>
    </td>
  </tr>
</table>




<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-01</strong></td>
  </tr>
</table>

<script>
function carian(){
	document.${formName}.submit();
}
function tambahFailSeksyen() {
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailSeksyenLama";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
	
}
function tambahFailNegeri() {
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailNegeriLama";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
	
}

function edit(a){
	document.${formName}.idFail.value = a;
	document.${formName}.action ="?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=papar";
	document.${formName}.submit();

}
function edit_item(a,b){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailSubjaket&idFail="+a+"&flagsubjaket="+b;
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
	
}

function cetak(nofail,tajukfail,idnegeri,idseksyen,idstatus,tarikhdaftar) {

		var url = "../servlet/ekptg.report.pfd.SenaraiFail?nofail="+nofail+"&tajukfail="+tajukfail+"&idnegeri="+idnegeri+"&idseksyen="+idseksyen+"&idstatus="+idstatus+"&tarikhdaftar="+tarikhdaftar;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

}
function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function setReload() {
	document.${formName}.action1.value = "tabFailSeksyenLama";
 	doAjaxCall${formName}("setReload");
}
</script>
<script language="JavaScript"> document.${formName}.txtNoFail.focus(); </script>