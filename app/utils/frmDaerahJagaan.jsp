<style type="text/css">
<!--
.senaraiBold {
	font-weight: bold;
}
.alamat {
	font-weight: bold;
}
.daerahFont {
	font-weight: bold;
}
-->
</style>
<p></p>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="99%">Jumlah Daerah Urus : $totalDaerah</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="alamat">$details.alamat </td>
  </tr>
</table>
<p>&nbsp;</p>
<fieldset>
  <legend>DAERAH JAGAAN</legend>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>

      <td>
      #if ($totalDaerah > 0)
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr class="table_row">
          <td width="10%" class="daerahFont">NO.</td>
          <td width="64%" class="daerahFont">DAERAH URUS</td>
          <td width="23%">&nbsp;</td>
          <td width="3%">&nbsp;</td>
        </tr>
        <!-- Table Content -->
#foreach ( $daerah in $listDaerah )
#set( $counter = $velocityCount )
#if ( ($counter % 2) == 0 )
    #set( $row = "row2" )
#else
    #set( $row = "row1" )
#end
        <tr class="$row" onMouseOver="this.className='highlight'" onMouseOut="this.className='$row'">
          <td width="10%">#set ($cnt = ($page - 1) * $itemsPerPage + $counter )
$cnt </td>
          <td width="64%">$daerah.nama_daerahurus</td>
          <td width="23%">&nbsp;</td>
          <td width="3%" align="right"><a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('deleteDearah','id=$daerah.id_pejabatjkptg&id_pejabatUrusan=$daerah.id_pejabaturusan') } "><img src="../img/delete.gif" border="0"></a>
</td>
        </tr>
#end
<input type=hidden name=page value=$page>	
      </table>   
#else
      <div class="info">Tiada Rekod Daerah Jagaan</div>
#end</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><input class="stylobutton"  type="button" 
value="Tambah Daerah Urus" onclick="javascript:doAjaxCall${formName}('addDaerahJagaan', 'id=$daerah.id_pejabaturusan')" /></td>
    </tr>
  </table>
  <p>&nbsp;</p>
</fieldset>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="99%">Jumlah Pegawai : $totalPegawai</td>
  </tr>
</table>
<br>
<!-- Pegawai JKPTG -->

<fieldset>
  <legend>SENARAI PEGAWAI
  </legend><table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>
      #if ($totalPegawai > 0)
      <table width="100%" cellpadding="1" cellspacing="0" border="0">
      

      
        <tr class="table_row">
          <td width="10%" class="senaraiBold">NO.</td>
          <td width="41%" class="senaraiBold">NAMA</td>
          <!--<td>Nama Pejabat</td>-->
          <td width="40%" align="center" class="senaraiBold">JAWATAN</td>
          <td width="9%"></td>
        </tr>
      <!-- Table Content -->
#foreach ( $pegawai in $listPegawai )
#set( $counter = $velocityCount )
#if ( ($counter % 2) == 0 )
    #set( $row = "row2" )
#else
    #set( $row = "row1" )
#end
        <tr class="$row" onmouseover="this.className='highlight'" onmouseout="this.className='$row'">
          <td>#set ($cnt = ($page - 1) * $itemsPerPage + $counter )
$cnt </td>
          <td>$pegawai.nama_pegawai</td>

          <td>$pegawai.jawatan</td>
          <td align="right"><a href="javascript:doAjaxCall${formName}('editPegawai','id=$pegawai.id_pejabatjkptg&idUnit=$pegawai.id_unitpsk')"><img src="../img/edit.gif" border="0"></a>
<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('deletePegawai','id=$pegawai.id_pejabatjkptg&idUnit=$pegawai.id_unitpsk') } "><img src="../img/delete.gif" border="0"></a></td>
        </tr>
 #end             
  <input type="hidden" name="page2" value="$page" />
      </table>
      #else
  <div class="info">Tiada Rekod Daerah Jagaan</div>
  #end
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><input type="submit" name="button" id="button" value="Tambah Pegawai" onclick="javascript:doAjaxCall${formName}('addNewPegawai','id_pejabat=$$daerah.id_pejabatjkptg')"/></td>
    </tr>
  </table>
</fieldset>
<p>
  <input name="id_pejabat" type="hidden" id="id_pejabat" value="$id_pejabat" />
</p>
<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack')">



<!-- End Senarai -->

<script>
function submitRegister(s) {
  	    
  if ( document.${formName}.id_seksyen.value == "" ) { 
	    document.getElementById('error_box').style.display = '';
	    document.getElementById('error_box_text').innerHTML = "Sila pilih Seksyen";
	    document.${formName}.id_seksyen.focus(); return; 
    } 
  
    if ( document.${formName}.id_negeri.value == "" ) { 
  	    document.getElementById('error_box').style.display = '';
  	    document.getElementById('error_box_text').innerHTML = "Sila pilih Negeri";
  	    document.${formName}.id_negeri.focus(); return; 
    }

    if ( document.${formName}.id_daerah.value == "" ) { 
  	    document.getElementById('error_box').style.display = '';
  	    document.getElementById('error_box_text').innerHTML = "Sila pilih Daerah";
  	    document.${formName}.id_daerah.focus(); return; 
    }
    if ( document.${formName}.id_pejabatjkptg.value == "" ) { 
      	    document.getElementById('error_box').style.display = '';
      	    document.getElementById('error_box_text').innerHTML = "Maklumat Pejabat tiada dalam Pangkalan Data.<br>Sila masukkan maklumat pejabat terlebih dahulu <a href=\"../c/1242196199970?_portal_module=ekptg.view.utils.FrmCodeSetup\">disini</a>";
      	    document.${formName}.id_daerah.focus(); return; 
    }
    

    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
}   

function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function doFilter() {
	doAjaxCall${formName}("edit","action=filterDaerah");
}


function showhide(layer_ref,displayType) {

if (displayType =="show") displayType = "display:inline";
else displayType = "display:none";

if (document.all) { //IS IE 4 or 5 (or 6 beta)
eval( "document.all." + layer_ref + ".style.display = "+displayType);
}
if (document.layers) { //IS NETSCAPE 4 or below
document.layers[layer_ref].display = displayType ;
}
if (document.getElementById &&!document.all) {
hza = document.getElementById(layer_ref);
hza.style.display = displayType;
}
} 

</script>

