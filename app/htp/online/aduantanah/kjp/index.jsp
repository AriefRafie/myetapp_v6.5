<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<body onLoad="refreshPage('$!test_ajax')">
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td><fieldset><legend><b>SENARAI ADUAN</b></legend>
			#parse("app/utils/record_paging.jsp")
			<table align="center" width="100%">
				<tr>
				<td colspan="6" scope="row">&nbsp;</td>
				</tr>
				<tr class="table_header">
					<td scope="row" width="3%" align="center"><strong>No. </strong></td>
			        <td width="20%"><strong>Nama Pengadu</strong></td>
			       	<td width="10%"><strong>Tarikh Aduan</strong></td>
			       	<td width="47%"  align="center"><strong>Keterangan</strong></td>         
			       	<!-- <td width="10%"  align="center"><strong>No. Hakmilik</strong></td> -->
			      	<td width="10%"  align="center"><strong>Status</strong></td>
								
				</tr>
				#set ($count = 0)
				#foreach ( $fail in $SenaraiFail )
				#set ($count = $count+1)
				#set( $i = $velocityCount )
				#if ( ($i % 2) != 1 )
				#set( $row = "row2" )
				#else
				#set( $row = "row1" )
				#end
				<tr>
					<!-- <td class="$row" align="center">$!count</td>
					<td class="$row"><a href="javascript:detail('$fail.idAduan')" class="style1">$fail.id</a></td>
					<td class="$row">$!fail.arahan</td> -->
					<td class="$row">$!count</td>
        	<td class="$row"><a href="javascript:detail('$fail.idAduan','$!fail.id')" class="style1">$fail.pengadu</a></td>
        	<td class="$row">$!fail.tarikhMasuk </td>
        	<td class="$row">$!fail.aduan 
        		<br> $!fail.arahan
        		</td>
	       	<!-- <td class="$row">$!fail.tanah</td> -->
        	<td class="$row">$!fail.status</td>
				</tr>
				#end
			</table>
		</fieldset></td>
	</tr>
</table>
<input type="hidden" name="command01" value="">
<input type="hidden" name="uploadFiles" value="$!upload_file">
<script>
		function refreshPage(m){
			//alert('$!test_ajax' + ' FUNCTION REFRESH PAGE DAH MASUK');
			if ( m != '' ){
				//alert('$!test_ajax' + ' DALAM FUNCTION REFRESH PAGE PUN DAH MASUK');

				//document.${formName}.enctype="";
				//document.${formName}.encoding ="";
				//document.${formName}.action='?_portal_module=ekptg.view.online.aduan.OnlineComplaintManagerModule&command=viewComplaint&idComplaint=' + "$!noAduan";
				//alert('$!upload_file');
				//document.${formName}.submit();
				doAjaxCall${formName}("viewComplaint","idComplaint="+"$!noAduan");
				//doAjaxCall${formName}("viewComplaint","idComplaint=" + "$!noAduan");
			}
			document.${formName}.action='';
		}

		//document.${formName}.action="";
		if ( document.${formName}.responseStatus.value=="" ) {
			document.${formName}.command01.value = "";
		} else {
			document.${formName}.command01.value = "cariAduan";
		}
   		function detail(id,idPengadu){
		//function detail(id){
   	   		doAjaxCall${formName}("viewComplaint","idComplaint="+id);
   		}
   		function simpan(){
   	   		/* if(document.${formName}.idTindakan.value==""){
   	   			alert('Sila Pilih Seksyen / Bahagian');
	   	   		return;
   	   		} */
			if(document.${formName}.ulasan.value==""){
   	   	   		alert('Sila Isi Arahan');
   	   	   		return;
   	   		}
   	   		else{
   				doAjaxCall${formName}("simpanAgih");
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
		function papar_LampiranJawapan(id) {
		    var url = "../servlet/ekptg.engine.servlet.DownloadFile?table=TBLONLINELAMPIRAN&key="+id+"&keyField=ID_ONLINELAMPIRAN&mimeField=JENIS_MIME";
		    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		    hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		}
		function notAllowed(){
			 alert("Proses tidak dibenarkan. Status aduan telah Selesai");
		}
		function daftarAgih(){
   			doAjaxCall${formName}("daftarAgih");
   	   	}
		function kembaliAgih(){
   			doAjaxCall${formName}("viewComplaint");
   	   	}
	   	function doChangeTabUpper(tabId) {
			document.${formName}.selectedTabUpper.value = tabId;
			doAjaxCall${formName}("viewComplaint");
	   	}
	   	function doChangeTabUpper1(tabId) {
			document.${formName}.selectedTabUpper.value = tabId;
			doAjaxCall${formName}("daftarAgih");
	   	}
	   	function detailAgihan(id){
   	   		doAjaxCall${formName}("viewAgihan","idResponse="+id);
   		}
		function doChangeTindakan() {
			doAjaxCall${formName}("doChangeTindakan");
		}
		function doChangePegawai() {
			doAjaxCall${formName}("doChangePegawai");
		}
		function tutupAduan() {
			doAjaxCall${formName}("tutupAduan");
		}
		function aduanPalsu() {

			doAjaxCall${formName}("aduanPalsu");
		}
		function updateRespon(){
			doAjaxCall${formName}("updateResponPTG");
		}

		function cariAduan(){
			//document.${formName}.command01.value = "cariAduan";
			doAjaxCall${formName}("cariAduan");
		}
		function simpanLampiran(x) {
			document.${formName}.enctype="multipart/form-data";
			document.${formName}.encoding ="multipart/form-data";
			//document.${formName}.target='upload_dokumen';
			document.${formName}.action='?_portal_module=ekptg.view.online.aduan.OnlineComplaintManagerModule&command=simpanLampiran&noAduan=' + x;
			document.${formName}.submit();
			//doAjaxCall${formName}("simpanLampiran");
		}
		function deleteLampiran(j) {
			//document.${formName}.action='?command=simpanLampiran&noAduan=' + x;
			//alert("Testing ID LAMPIRAN : " + j);

			//document.${formName}.action='';
			//document.${formName}.command.value = 'deleteLampiran';
			document.${formName}.idLampiran.value = j;
			//document.${formName}.action='?_portal_module=ekptg.view.online.aduan.OnlineComplaintManagerModule&command=deleteLampiran&idLampiran=' + j;
			//document.${formName}.submit();
			doAjaxCall${formName}("deleteLampiran");
		}
		function mainPage(){
   			doAjaxCall${formName}("mainpage");
		}
</script>