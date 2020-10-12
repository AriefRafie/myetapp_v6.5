<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI ADUAN</b></legend>
       <table align="center" width="100%">
       
        <tr>
          <td colspan="7" scope="row">Pilih Status :
          		<select name="responseStatus">
          			#foreach($statuses in $statuses)
          				<option value="$statuses" #if($statuses.toString() == "BARU") selected #end>$statuses.desc</option>
          			
          			#end
          			<input type="button" value="Cari" onclick="cariRespon()"/>
          		</select>
          </td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>No.</strong></td>
          <td width="25%"><strong>Nama Pengadu</strong></td>
          <td width="20%"><strong>No. Aduan</strong></td>
          <td width="10%"  align="center"><strong>Tarikh Agihan Aduan</strong></td>
          <td width="10%"><strong>Status Agihan</strong></td>
        
        </tr>
           #set ($count = 0)
      #foreach ( $fail in $lists )
      #set ($count = $count+1)
          #set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
        <tr>
       		<td class="$row">$!count</td>
        	<td class="$row"><a href="javascript:detail('$fail.id')" class="style1">$fail.complaint.namaPengadu</a></td>
        	<td class="$row">$!fail.complaint.id </td>
        	<td class="$row">$!fail.tarikhMasuk</td>
        	<td class="$row">$!fail.responseStatus.desc</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
  <SCRIPT>
  
  function doChangeTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	doAjaxCall${formName}("viewComplaint");
	}
   		function detail(id){
   	   		doAjaxCall${formName}("viewComplaint","idRespon="+id);
   		}
   		function simpan(){
   			if(document.${formName}.tindakan.value==""){
   	   	   		alert('Sila Pilih Tindakan');
   	   	   		return;
   	   		}
   	   		else if(document.${formName}.ulasanBalas.value==""){
   	   			alert('Sila Isi Ulasan');
	   	   		return;
   	   		}
   	   		else{
   				doAjaxCall${formName}("simpanComplaint");
   	   		}
   		}
   		function kembali(){
   			doAjaxCall${formName}("back");
   		}

   	 function papar_Lampiran(id) {
		    var url = "../servlet/ekptg.engine.servlet.DownloadFile?table=TBLONLINELAMPIRANADUAN&key="+id+"&keyField=ID_LAMPIRANADUAN&mimeField=JENIS_MIME";
		    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		    hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
}
	function tidakLengkap() {
	
		if(document.${formName}.ulasanBalas.value==""){
   	   	   		alert('Sila Isi Ulasan');
   	   	   		return;
   	   		}
			
	doAjaxCall${formName}("tidakLengkap");
	
	}
	
	function tidakRelevan() {
	
	if(document.${formName}.ulasanBalas.value==""){
   	   alert('Sila Isi Ulasan');
   	   return;
   	  }
	  
	doAjaxCall${formName}("tidakRelevan");
	
	}
	
	function selesai() {
	
	if(document.${formName}.ulasanBalas.value==""){
   	   alert('Sila Isi Ulasan');
   	   return;
   	 }
	 
	doAjaxCall${formName}("selesai");
	
	}
	
	function simpan() {
		
		if(document.${formName}.ulasanBalas.value==""){
	   	   alert('Sila Isi Ulasan');
	   	   return;
	   	 }
		 
		doAjaxCall${formName}("simpanDraf");
		
		}
	

 	 function cariRespon(){ 
 		doAjaxCall${formName}("cariRespon");
 	 }		

function notAllowed(){
	alert("Proses tidak dibenarkan. Agihan telah diselesaikan");

}
   </SCRIPT>
