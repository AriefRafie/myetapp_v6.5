
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<input name="action" type="hidden" value="$action" />
<input name="idAkta" type="hidden" id="idAkta" value="$idAkta"/>
<input name="idAktaPindaan" type="hidden" id="idAktaPinda" value="$idAktaPinda"/>
&nbsp;




<fieldset>
          <legend><strong>Maklumat Akta</strong></legend>
          <table width="100%">
          <tr>
            <td width="29%" scope="row">No. Akta Asal</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>$noAkta</label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta Asal</td>
            <td scope="row">:</td>
            <td>$namaAkta</td>
          </tr>
          <tr>
            <td scope="row">No. Fail</td>
            <td scope="row">:</td>
            <td>$noFail</td>
          </tr>
            <tr>
            <td colspan=3>
                    </td>
          </tr>
          <tr>
            <td colspan=3>
             <a href="javascript:onClick=viewAktaBlob('$!idAkta');" style="color:#0000FF">[Lihat Lampiran]</a>
      <a href="javascript:onClick=paparAkta('$!idAkta');" style="color:#0000FF">[Papar Akta]</a>	
            </td>
          </tr>
        </table>
</fieldset>
        <p>
  <fieldset>
  <legend><strong>Senarai Akta Pindaan</strong></legend>
  <input type="button" value="Tambah" onclick="tambahAktaPindaan()"/>  
  <input type="submit" value="Kembali" onclick="kembaliAktaPindaan()"/>  
  #parse("app/utils/record_paging.jsp")
  <table width="100%">
    <tr class="table_header">
      <td width="3%" scope="row"><strong>Bil.</strong></td>
      <td width="60%"><strong>Nama Akta Pindaan</strong></td>
      <td width="20%"><strong>No. Akta Pindaan</strong></td>
      <td width="12%"><strong>Tarikh Daftar</strong></td>
      <td width="5%">&nbsp;</td>
    </tr>
    #set ($akta = '')
    #set ($counter=0)
    #foreach ($akta in $SenaraiFail)
    #set ($counter = $counter + 1)
    #if ($counter == '') 
    #set ($row = 'row1')
    #elseif ($counter % 2 != 0)
    #set ($row = 'row1')
    #else 
    #set ($row = 'row2')
    #end
    <tr>
    <td height="20" class="$row">$counter.</td>
    <td class="$row"><a href="javascript:view_akta_pindaan_detail('$akta.idAktaPinda')" class="style1">$akta.namaAktaPindaan</a></td>
    <td class="$row">$akta.noAktaPindaan</td>
    <td class="$row"><span>$akta.tarikhAktaPindaan</span></td>
    <td class="$row" align=right><a href = "javascript:viewAktaPindaanBlob('$akta.idAktaPinda')">
    <img src="../img/pdf-small.png" alt="" border="0" /></a>
    
     <a alt="Hapus Akta Pindaan" href = "javascript:deleteAktaPindaan('$akta.idAktaPinda')">
      &nbsp;&nbsp;<img border="0" src="../img/delete.gif" /></a>  
      
    </td>
    </tr>
    #end
  </table>
</fieldset>

    <script>
function edit_item(id){
	//document.${formName}.command.value="papar";
	document.${formName}.idAkta.value = id;
	doAjaxCall${formName}("papar","");
	//document.${formName}.submit();
}
function viewAktaBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=akta&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function doSave() {
	if (document.${formName}.txtNoAktaPindaan.value == "") {
		alert("Sila Masukkan No Akta Pindaan");
		document.${formName}.txtNoAktaPindaan.focus();
		return;
	}
	doAjaxCall${formName}("simpan","");
}

function doUpdate() {
	if (document.${formName}.txtNoAktaPindaan.value == "") {
		alert("Sila Masukkan No Akta Pindaan");
		document.${formName}.txtNoAktaPindaan.focus();
		return;
	}
		if (document.${formName}.txtNamaAktaPindaan.value == "") {
		alert("Sila Masukkan Nama Akta Pindaan");
		document.${formName}.txtNamaAktaPindaan.focus();
		return;
	}
	doAjaxCall${formName}("simpan","mode=doUpdate");
}

function kemaskini() {
doAjaxCall${formName}("kemaskini");
}
//26/09/2012
function doGetSenaraiAktaPindaan() {
	//doAjaxCall${formName}("paparsenaraipindaan","");
	document.${formName}.command.value = "paparsenaraipindaan";
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan";	
	document.${formName}.submit();
	
}

function view_akta_pindaan(id) {
	document.${formName}.idAkta.value = id;
	//doAjaxCall${formName}("paparsenaraipindaan","");
	document.${formName}.command.value = "paparsenaraipindaan";
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan";	
	document.${formName}.submit();
}



	function view_akta_pindaan_detail(id) {
		document.${formName}.idAktaPindaan.value = id;
		//doAjaxCall${formName}("paparsenaraipindaan_detail","");
		document.${formName}.command.value = "paparsenaraipindaan_detail";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan";	
		document.${formName}.submit();
		
	}

	function tambahAktaPindaan() {
		//doAjaxCall${formName}("tambahAktaPindaan","");
		document.${formName}.command.value = "tambahAktaPindaan";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan";	
		document.${formName}.submit();
		
	}

function kembaliAktaPindaan() {
doAjaxCall${formName}("","");
}

function paparAkta(idAkta) {
    var url = "../x/${securityToken}/ekptg.view.pdt.FrmViewAkta2?idAkta="+idAkta;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function deleteAktaPindaan(idAktaPinda) {
    if ( !window.confirm("Akta Pindaan akan dihapuskan. Adakah Anda Pasti?") ) return;	
    document.${formName}.idAktaPinda.value = idAktaPinda;
    doAjaxCall${formName}("delete","idAktaPinda="+idAktaPinda);
}

    </script>
<script>
//alert("ssssss");
//alert("sssssss"+'$current_role');

$jquery(document).ready(function () {

	//if('$current_role'=="(PDT)HQPengguna" || '$current_role'=="(PDT) Pengguna Bahagian Strata")
	if('$current_role'=="(PDT)HQPengguna" || '$current_role'=="(PDT) Pengguna Bahagian Strata" || '$current_role'=="(PDT) Pengguna Bahagian Pengambilan Tanah" || '$current_role'=="(PDT) Pengguna Bahagian Penguatkuasa dan Hasil Persekutuan" || '$current_role'=="(PDT) Pengguna Bahagian Pembahagian Pusaka" )
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		//$jquery("input[type=button]").hide();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		$jquery("input[type=button]").show();
	}
	else
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		//$jquery("input[type=button]").show();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		$jquery("input[type=button]").hide();
	}
});

</script>
