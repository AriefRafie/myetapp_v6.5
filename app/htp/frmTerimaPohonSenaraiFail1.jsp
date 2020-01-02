<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<fieldset>
  <legend>Carian 2</legend>
  
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  	<tr class="table_header">
  		<td align="right" width="40%">No Fail&nbsp;&nbsp;:&nbsp;&nbsp;</td>
  		<td><input type=text name=nofail></td></tr>
  <tr>
  <td></td><td>
  <input class="stylobutton" type=button value="Cari " onClick="javascript:carianFail();">
  <input class="stylobutton" type=button value = "Kosongkan" onClick="javascript:cancel();">
  </td>
  </tr>
  </table>
    
  
</fieldset>

<fieldset>
  <legend>Senarai Fail Permohonan</legend>
  	<input class="stylobutton" value="Tambah" type="button" onClick="javascript:tambahPermohonan()">
  #parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="2" cellpadding="1" border="0">
  <tr class="table_header">
  	<td>No</td>
  	<td>No Fail</td>
  	<td>Nama Fail</td>
  	<td>Status Pergerakan Fail</td>
  </tr>
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraiList1 )
  	#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
                <input type="hidden" name="senarai_id" value="$senarai.id">
  <tr>
  <td class="$row">$cnt.</td>
  <td class="$row">
  	<a href="javascript:kemaskiniterimapermohonan('$senarai.id')" class="style1">
	$senarai.no
	</a>
  </td>
  <td class="$row">$senarai.tujuan</td>
  <td class="$row">$senarai.keterangan</td>
  </tr>
   #end
	#if ($cnt == 0)
	<tr> 
		<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
	</tr> 
	<input type="hidden" value="$senarai.id" name="id_senarai">
	#end
    </table>
</fieldset>


<script>

function carianFail(){

	doAjaxCall${formName}("terimapohoncarian");
	
}
function kemaskiniterimapermohonan(id){
	//alert(id);
	//document.${formName}.idF.value = id;	
	//document.${formName}.id_senarai.value = id;
	doAjaxCall${formName}("kemaskinipermohonan","idfail="+id);

}
function tambahPermohonan(){
	doAjaxCall${formName}("pohonfailbaru1");
}

function selectTab(tabId,command,mode,tabmode){
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	}

function doChangeDaerah(){
	doAjaxCall${formName}("doChangeDaerah");
}

function doChangeMukim(){
	doAjaxCall${formName}("doChangeMukim");
}


function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian","action2=doChangeKementerian");
}

//****************function kembali********************************************

function kembali(){
	doAjaxCall${formName}("");
}

function Kemaskini(){
	doAjaxCall${formName}("");
}

function Hapus(){
	doAjaxCall${formName}("");
}

function Simpan(i){	
	//alert(document.${formName}.test.value);
		if ( document.${formName}.socNegeri.value == "" ) { 
  		alert('Sila pilih negeri terlebih dahulu.');
  		document.${formName}.socNegeri.focus(); 
  		return; 
  	}
	  /*
		if ( document.${formName}.socKementerian.value == "" ) { 
	  		alert('Sila pilih Kementerian terlebih dahulu.');
	  		document.${formName}.socKementerian.focus(); 
	  		return; 
	  	}*/
		if ( document.${formName}.socAgensi.value == "" ) { 
	  		alert('Sila pilih Agensi terlebih dahulu.');
	  		document.${formName}.socAgensi.focus(); 
	  		return; 
	  	}

		if ( document.${formName}.socUrusan.value == "" ) { 
	  		alert('Sila pilih Urusan terlebih dahulu.');
	  		document.${formName}.socUrusan.focus(); 
	  		return; 
	  	}
		if ( document.${formName}.socStatustanah.value == "" ) { 
	  		alert('Sila pilih Status Tanah terlebih dahulu.');
	  		document.${formName}.socStatustanah.focus(); 
	  		return; 
	  	}
		if ( document.${formName}.socjenisFail.value == "" ) { 
	  		alert('Sila pilih Jenis Fail terlebih dahulu.');
	  		document.${formName}.socjenisFail.focus(); 
	  		return; 
	  	}
		if ( document.${formName}.txtnoFailKJP.value == "" ) { 
	  		alert('Sila pilih Fail KJP terlebih dahulu.');
	  		document.${formName}.txtnoFailKJP.focus(); 
	  		return; 
	  	}
	  	if ( document.${formName}.txdTarikhSuratKJP.value == "" ) { 
	  		alert('Sila pilih Tarikh Surat KJP terlebih dahulu.');
	  		document.${formName}.txdTarikhSuratKJP.focus(); 
	  		return; 
	  	}
	  	if ( document.${formName}.txtnoFailUPT.value == "" ) { 
	  		alert('Sila pilih No Fail UPT terlebih dahulu.');
	  		document.${formName}.txtnoFailUPT.focus(); 
	  		return; 
	  	}
	  	/*
	  	if ( document.${formName}.txtnoFailLain.value == "" ) { 
	  		alert('Sila pilih No Fail Lain terlebih dahulu.');
	  		document.${formName}.txtnoFailLain.focus(); 
	  		return; 
	  	}*/
	  	
	  	if ( document.${formName}.txtTajuk.value == "" ) { 
	  		alert('Sila pilih Tajuk terlebih dahulu.');
	  		document.${formName}.txtTajuk.focus(); 
	  		return; 
	  	}
	//document.${formName}.cmdSimpan.value = i;
	//document.${formName}.mode.value = "SimpanPermohonan";
	//document.${formName}.method = "post";
	
	doAjaxCall${formName}("pohonfailbaru");
}

function Batal(){
	doAjaxCall${formName}("");
}
</script>