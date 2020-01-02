<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="idFail" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" align="right">No Fail : </td>
          <td width="70%"><input name="txtCarian" id="txtCarian" type="text" size="70" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
         <td scope="row" align="right"> No Hakmilik :</td>
          <td>
				<select id="kodLot" name="kodLot">
					<option value="">SILA PILIH</option>
					<option value="TIDAK">TIDAK DI KENALPASTI</option>
					<option value="PLOT">PLOT</option>
					<option value="LOT">LOT</option>
					<option value="PT">PT</option>
					<option value="MLO">MLO</option>
					<option value="PTB">PTB</option>
					<option value="PTD">PTD</option>
				</select>
			<input name="txtNoHakmilikC" id="txtNoHakmilikC" type="text" value="$txtNoHakmilikC" size="24" maxlength="50" /></td>
        </tr>
         <tr>
          <td scope="row" align="right">Negeri :</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td scope="row" align="right">Daerah :</td>
          <td>$selectDaerah</td>
        </tr>
        <tr>
          <td scope="row" align="right">Mukim :</td>
          <td>$selectMukim</td>
        </tr>
        <tr>
          <td scope="row" align="right">Kementerian :</td>
          <td>$selectKementerian</td>
        </tr>
         
        <tr>
        	<td></td>
        	<td align="left">
	          	<input type="button"  value="Cari" onClick="cari()"  />
				<input type="button"  value="Kosongkan" onClick="kosong()"  />
        	</td>
        </tr>
       </table>
     </fieldset>
    </td>
   </tr>
   
   <tr>
    <td><fieldset>
      <legend><b>SENARAI FAIL</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="10%"><strong>No Hakmilik</strong></td>
          <td width="15%"><strong>No Fail JKPTG</strong></td>
          <td width="30%"><strong>Tajuk Fail</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="8%"><strong>Urusan</strong></td>
          <td width="8%"><strong>Keputusan</strong></td>
          <td width="8%" align="center"><strong>Tarikh Terima</strong></td>
          <td width="12%"><strong>Laporan Tanah</strong></td>
        </tr>
        #set ($list = "")
        #if ($BeanMaklumatTanahDanLaporanTanah.size() > 0)
        #foreach ($list in $BeanMaklumatTanahDanLaporanTanah)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row">$list.kodLot $list.noLot</td>
          <td class="$row"><a href="javascript:papar('$list.idFail','$list.moduleClassDaftarpmhn','$list.namaUrusan')" class="style1">$list.noFail</a></td>
          <td class="$row">$list.tajukFail</td>
		  <td class="$row">$list.status</td>
          <td class="$row">$list.namaUrusan</td>		  
          <td class="$row"><a href="javascript:papar('$list.idFail','$list.moduleClassKeputusan','$list.namaUrusan')" class="style1">$list.keputusan</a></td>
          <td class="$row" align="center">$list.tarikhKeputusan</td>
		  <td class="$row">$list.idLaporantanah</td>
        </tr>
        #end
        #else
        <tr>
		  <td class="$row" align="center">&nbsp;</td>
          <td class="$row">Tiada Rekod</td>
          <td class="$row">&nbsp;</td>
          <td class="$row">&nbsp;</td>
		  <td class="$row">&nbsp;</td>
          <td class="$row">&nbsp;</td>		  
          <td class="$row">&nbsp;</td>
          <td class="$row" align="center">&nbsp;</td>
		  <td class="$row">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>

<script>

function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}

function papar(idFail,moduleClass,namaUrusan) {
	document.${formName}.idFail.value = idFail;
	var tabId = setTabId(namaUrusan);
	var namaAction = tabId+"?_portal_module="+moduleClass+"&flagFrom=01";
	document.${formName}.action = namaAction;
	document.${formName}.submit();
}

// function paparKeputusan(idFail,moduleClassKeputusan,namaUrusan) {
// 	document.${formName}.idFail.value = idFail;
// 	var tabId = setTabId(namaUrusan);
// 	var namaAction = tabId+"?_portal_module="+moduleClassKeputusan+"&flagFrom=01";
// 	document.${formName}.action = namaAction;
// 	document.${formName}.submit();
// }

function cari(){
// 	if(document.getElementById('txtCarian').value=="")	{
// 		alert("Sila Masukkan Maklumat Carian");
// 		document.getElementById('txtCarian').focus();
// 	} else {
	document.${formName}.submit();
// 	}
}

function kosong(){	
	document.getElementById('txtCarian').value="";
	document.${formName}.kodLot.value="";
	document.getElementById('txtNoHakmilikC').value="";
	document.${formName}.socNegeriC.value="";
	document.${formName}.socDaerahC.value="";
	document.${formName}.socMukimC.value="";
	document.${formName}.socKementerianC.value="";
	
	document.${formName}.submit();
}

function setTabId(namaUrusan){
	var tabId = "";
    if(namaUrusan == 'PENAWARAN')
    	tabId = "$EkptgUtil.getTabID("Penawaran",$portal_role)";
    else if(namaUrusan == 'TUKARGUNA')
		tabId = "$EkptgUtil.getTabID("Tukarguna",$portal_role)";
 	else if(namaUrusan == 'PELEPASAN / PENYERAHANBALIK')
		tabId = "$EkptgUtil.getTabID("Pelepasan",$portal_role)";
    return tabId;
}

</script>