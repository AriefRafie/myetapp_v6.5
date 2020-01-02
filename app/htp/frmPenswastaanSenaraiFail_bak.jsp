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
    <td class="$row"><a href="javascript:viewSyarikat('$!list.idpermohonan')"><font color="blue">$!list.nofail</font></td> 
    <!--<td class="$row"><a href="javascript:view('$!list.id_permohonan')"><font color="blue">$!list.no_fail</font></td> -->
    <td class="$row"> $!list.nama_negeri</td>
    <td class="$row">  $!list.tajuk_fail</td>
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

function doChangeKementerian() {
	doAjaxCall${formName}("tambah_senarai_fail");		
}

function view(id_permohonan) {
	
	document.${formName}.id_permohonan.value = id_permohonan;
//document.${formName}.action.value = "simpanPendaftaranPermohonan";
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pendaftaran_permohonan &action=paparPendaftaran";
	document.${formName}.submit();
	
	//doAjaxCall${formName}("simpanPendaftaranPermohonan");	
	
}

function simpanPendaftaranPermohonan() {
	alert('simpanPendaftaranPermohonan');
	doAjaxCall${formName}("simpanPendaftaranPermohonan");		
}

function viewSyarikat(id) {
	doAjaxCall${formName}("viewSyarikat",'id_permohonan1='+id);		
}

function viewSyarikatSimpan(id) {
	doAjaxCall${formName}("viewSyarikat",'action=simpan&id_permohonan='+id);		
}


</script>
