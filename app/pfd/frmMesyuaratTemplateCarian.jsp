<input type="hidden" id="idNegeri" name="idNegeri" value="$idNegeri" />
<input type="hidden" id="SOC_UNIT" name="SOC_UNIT" value="$SOC_UNIT" />
<input type="hidden" id="Mesyuarat_Unit" name="Mesyuarat_Unit" value="$Mesyuarat_Unit" />
<fieldset>
  <legend><strong>CARIAN MESYUARAT</strong></legend>
  <table width="100%">
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">No Fail / No Rujukan Mesyuarat</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoRujukanDokumen" type="text" id="Carian_NoRujukanDokumen" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoRujukanDokumen" size="50" /></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><div align="left">Tajuk Mesyuarat</div></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><input name="Carian_Tajuk" type="text" id="Carian_Tajuk" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_Tajuk" size="50" /></td>
    </tr>
    #if($idNegeri == '16')
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">Urusetia / Seksyen</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selectSeksyen</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">Lokasi</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selectLokasi</td>
    </tr>
    #else
        <tr>
      <td align="right" valign="top" scope="row"><div align="left">Urusetia / Unit</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selectUnit</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">Lokasi</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selectLokasi</td>
    </tr>
    #end
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">Tarikh Mesyuarat</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_Tarikh" type="text" id="Carian_Tarikh" value="$!Carian_Tarikh" size="15" maxlength="10" /><a href="javascript:displayDatePicker('Carian_Tarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
    </tr>
    <tr>
      <td colspan="2"><div align="left"></div></td>
      <td valign="top" scope="row"><input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchMesyuarat();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyMesyuarat();" /></td>
    </tr> 
    <tr>
      <td colspan="3" align="center" valign="top" scope="row"><div align="left"></div></td>
    </tr>
  </table>
</fieldset>
<br />

<fieldset>
  <legend><strong>SENARAI MESYUARAT YANG TELAH DIDAFTARKAN</strong></legend>
 #parse("app/utils/record_paging.jsp") 
  <table width="100%" >
    <tr>
      <td colspan="6"><input id="cmdBaru" name="cmdBaru" type="button" value="Tambah" onclick="newMesyuarat();" />
      <input name="" type="button" value="Hapus Template Mesyuarat" onClick="hapus_beramai()">
      <!--&nbsp;<input id="cmdCetakSenarai" name="cmdCetakSenarai" type="button" value="Cetak Senarai" onclick="printList();" />--></td>
    </tr>
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      #if($idNegeri == '16')
      <td width="20%"><strong>URUSETIA/SEKSYEN</strong></td>
      #else
      <td width="20%"><strong>URUSETIA/UNIT</strong></td>
      #end
      <td width="25%"><strong>TAJUK</strong></td>
      <!--td width="15%" align="center"><strong>MASA</strong></td-->
      <td width="15%">&nbsp;</td>
       <td width="5%">  <div align="center">
      <input type="checkbox" name="all1" id="all1" onClick="doCheckAll1();" title="Semak untuk hapuskan semua fail" />
      </div></td>
    </tr>
#foreach ($fail in $ListMesyuarat)
	#if ($fail.ListNo == '') 
    	#set ($row = 'row1')
    #elseif ($fail.ListNo % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    
     #if ($fail.ListNo != '') 
    <tr>
   
      <td height="21" class="$row" valign="top" align="center">$fail.ListNo</td>
      <td class="$row" valign="top"><a href="javascript:viewMesyuarat('$fail.ListIDMesyuarat')" style="color:#0000FF">$fail.ListNoFail</a></td>
      #if($idNegeri == '16')
      <td class="$row" valign="top">$fail.ListUrusetiaSeksyen</td>
      #else
       <td class="$row" valign="top">$fail.ListUrusetiaUnit</td>
      #end
      <td class="$row" valign="top">$fail.ListTajuk</td>
      <!--td class="$row" valign="top" align="center">$fail.ListMasa</td-->
      <td class="$row" valign="top" align="center"><input type="button" id="cmdTambahMesyuarat" name="cmdTambahMesyuarat" value="Panggil Mesyuarat" onclick="janaMesyuarat('$fail.ListIDMesyuarat')" /></td>
       <td width="5%" class="$row">
       <div align="center">
       <input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1()" value="$fail.ListIDMesyuarat" title="Semak untuk hapuskan fail ini" >
     </div>
       </td>
       </tr>
    #else
    <tr>
      <td colspan="8" class="$row" style="text-align:center">Tiada Rekod</td>
      </tr>
    #end
   
#end
    <tr>
      <td colspan="8">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<input type="hidden" id="ID_MESYUARAT" name="ID_MESYUARAT" value="$ID_MESYUARAT" />
<input type="hidden" id="ID_TEMPLATE" name="ID_TEMPLATE" value="$ID_TEMPLATE" />
<input type="hidden" id="selectedTab" name="selectedTab" value="$selectedTab" />
<input type="hidden" id="mode" name="mode" value="$mode" />
<input name="actionx" id="actionx" type="hidden" value="$actionx" />

<script>
function viewMesyuarat(ID_MESYUARAT) {	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTemplate&action=daftarMesyuarat&ID_MESYUARAT=" + ID_MESYUARAT;
	document.${formName}.submit();
}
function searchMesyuarat() {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTemplateCarian&actionx=carianMesyuarat";
	document.${formName}.submit();
}
function emptyMesyuarat() {
	document.${formName}.reset();
}
function newMesyuarat() {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTemplate&actionx=daftarMesyuarat&ID_MESYUARAT=";
	document.${formName}.submit();
}
function janaMesyuarat(ID_TEMPLATE) {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=daftarMesyuarat&ID_MESYUARAT=&ID_TEMPLATE=" + ID_TEMPLATE;
	document.${formName}.submit();
}
function printList() {
		var url = "../servlet/ekptg.report.pfd.PFDMesyuarat?reportType=PDF";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}
function doCheckAll1(){    
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = false;
            }
        }
    }
}
function doUpdateCheckAll1(){  
var c = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all1.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1.checked = true;
	  }
	  
}


function hapus_beramai()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTemplateCarian&actionx=delete&ID_MESYUARAT=";
	document.${formName}.submit();
	
	
	}
}


</script>