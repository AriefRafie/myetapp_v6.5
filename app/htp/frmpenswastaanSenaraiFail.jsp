<input name="selectedTabUpper" type="hidden" value="$selectedTabUpper" />
$selectedTabUpper
<fieldset>
<legend><strong>Carian</strong></legend>
<table width="100%" border="0">
  <tr>
    <td width="29%"><div align="right">No. Fail</div></td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input type="text" name="txtNoFail" id="txtNoFail" value="$!NoFail"size="50" maxlength="50" style="text-transform:uppercase;" >
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Nama Fail</div></td>
    <td>:</td>
    <td><label>
      <input type="text" name="txtNamaFail" id="txtNamaFail" value="$!NamaFail">
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Negeri</div></td>
    <td>:</td>
    <td>$selectNegeri</td>
  </tr>
  <tr>
    <td><div align="right"></div></td>
    <td>&nbsp;</td>
    <td><label>
      <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="cariFail();">
      <input type="reset" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick= "kosongkan()"/>
    </label></td>
  </tr>
</table>
</fieldset>
<fieldset><legend><strong>Senarai Fail</strong></legend>
<p><strong>
  <input type="button" name="cmdtambah" id="cmdtambah" value="Tambah" onclick="tambah_senarai_fail();" />
  <label></label>
</strong></p>

#parse("app/utils/record_paging.jsp")

<table width="100%" border="0">
 <tr class="table_header">
   <td width="18%"><div align="center"><strong>No</strong></div></td>
    <td width="18%"><div align="center"><strong>No Fail</strong></div></td>
    <td width="18%"><div align="center"><strong>Negeri</strong></div></td>
    <td width="17%"><div align="center"><strong>Nama Fail</strong></div></td>
    <td width="29%"><div align="center"><strong>Status</strong></div></td>
  </tr>
  
        #if($list_size!=0)   
            #set ( $cnt = 0 )			
          	#set ($list = "")
            #set ($counter = 0)
                 
            #foreach ($list in $senaraiFail)
            #set ( $cnt = $cnt + 1 )     
            #set( $counter = $velocityCount - 1 )
          	#if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
     
  <tr>
    <td class="$row"> <div align="center">$cnt</div></td>
    <td class="$row"><a href="javascript:viewSyarikat('$list.idpermohonan')"><font color="blue">$!list.nofail</font></td> 
    <!--<td class="$row"><a href="javascript:view('$!list.id_permohonan')"><font color="blue">$!list.no_fail</font></td> -->
    <td class="$row"> $!list.negeri</td>
    <td class="$row">  $!list.tajuk</td>
    <td class="$row">  $!list.keterangan</td>
     
  </tr>
  
   #end
   
    #else  
   		  <tr>
        	<td colspan="5">Tiada rekod</td>
          <tr>  
   	  #end  
  
</table>
</fieldset>

<br>
<input type="hidden" name="id_fail" value="$id_fail"/>
<input type="hidden" name="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="id_pemaju" value="$idPemaju"/>

<script>


function cariFail() {
	doAjaxCall${formName}("cariFail");
	
}

function tambah_senarai_fail() {
	doAjaxCall${formName}("tambah_senarai_fail");			
}

function kosongkan() {
	document.${formName}.reset();
}
function backListSenarai(id_permohonan) {
alert ('back');
	doAjaxCall${formName}("backListSenarai");	
}

function doChangeKementerian() {
	doAjaxCall${formName}("tambah_senarai_fail");		
}

function simpanPendaftaranPermohonan() {
	alert('simpanPendaftaranPermohonan');
	doAjaxCall${formName}("simpanPendaftaranPermohonan");		
}

function viewSyarikat(id) {
	doAjaxCall${formName}("viewSyarikat",'action=papar&id_permohonan1='+id);
}

function viewSyarikatSimpan(id) {
	doAjaxCall${formName}("viewSyarikat",'action=simpan&id_permohonan1='+id);		
}

function viewSyarikatUpdate(id) {
	doAjaxCall${formName}("viewSyarikat",'action=kemaskini&id_permohonan1='+id);		
}

function viewSyarikatSimpanUpdate(id) {
	doAjaxCall${formName}("viewSyarikat",'action=simpanKemaskini&id_permohonan1='+id);		
}

function tambahPengarah(id) {
alert ('id');
	doAjaxCall${formName}("viewSyarikat",'action=tambahPengarah&id_permohonan1='+id);		
}

function simpanPengarah(id) {
	doAjaxCall${formName}("viewSyarikat",'action=simpanPengarah&id_permohonan1='+id);		
}

//papar pengarah for edit
function paparPengarah(id,id_pengarah) {
	//doAjaxCall${formName}("paparPengarah",'action=papar1&id_permohonan1='+id);
	doAjaxCall${formName}("viewSyarikat",'action=paparPengarah&id_permohonan1='+id+'&id_pengarah='+id_pengarah);	
		
}
function viewKemaskiniPengarah(id,id_pengarah) {
	doAjaxCall${formName}("viewSyarikat",'action=viewPengarah&id_permohonan1='+id+'&id_pengarah='+id_pengarah);		
}

function simpanKemaskiniPengarah(id,id_pengarah) {
	doAjaxCall${formName}("viewSyarikat",'action=simpanKemaskiniPengarah&id_permohonan1='+id+'&id_pengarah='+id_pengarah);		
}
function kembaliListPengarah(id,id_pengarah) {
alert ('listPengarah');
	doAjaxCall${formName}("kembaliListPengarah");	
}


//seterusnya
function screenMklmtSyrk(id) {
	doAjaxCall${formName}("viewSyarikat",'action=nextscreen&id_permohonan1='+id);		
}

// screen pengarah to list fail
function backList(id) {
	doAjaxCall${formName}("viewSyarikat",'action=backToList&id_permohonan1='+id);		
}

function backMklmtSyrk(id) {
	doAjaxCall${formName}("viewSyarikat",'action=backToMklmatSykt&id_permohonan1='+id);		
}
function screenMesyuarat(id) {
	doAjaxCall${formName}("viewMesyuarat",'action=nextscreen&id_permohonan1='+id);		
}

//function backToPageMaklumatSykt(id) {
//alert('geram');
//	document.${formName}.action = "?_portal_module=ekptg.view.htp.frmpenswastaanMaklumatSyarikat.jsp&command=viewMesyuaratKembali&id_permohonan1="+id;
//	document.${formName}.submit();	
//}
function screenPindahMilik(id) {
	document.${formName}.action = "?_portal_module=ekptg.view.htp.frmpenswastaanSenaraiFail&command=viewPindahMilik&id_permohonan1="+id;
	document.${formName}.submit();
}
function doChangeTab(tabId) {
//	alert(tabId)
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.submit();
}
</script>
