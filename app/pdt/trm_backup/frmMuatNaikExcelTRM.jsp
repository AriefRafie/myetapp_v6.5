<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="hitButton">
	<input type="hidden" name="idWarta">
</p>
<fieldset>
	<legend>
		<strong>Muatnaik data Tanah Rizab Melayu</strong>
	</legend>
	<table width="100%">
		<tr>
			<td width="29%" align="right" scope="row">Negeri</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">$selectNegeriUpload</td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row">Fail</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">
				<!--<input name="paramFail" type="text" id="paramFail" value="$!paramFail" /> -->
				<input id="txfMuatNaikDokumen" name="txfMuatNaikDokumen" type="file" size="40" />
				<!--<input type="button" name="cmdUpload" id="cmdUpload" value="Browse" onclick="javascript:upload()" /> -->
				
			</td>
		</tr>
		<tr>
			<td align="right" scope="row">&nbsp;</td>
			<td scope="row">&nbsp;</td>
			<td>
				<input type="submit" name="cmdUpload" id="cmdUpload" value="Muatnaik" onclick="muatnaik()" />
				<input type="button" name="cmdListcarian" id="cmdListcarian" value="Kembali" onclick="javascript:listcarian()" />

			</td>
		</tr>
	</table>
</fieldset>
<fieldset>
	<legend>
		<strong>Senarai Muatnaik</strong>
	</legend>
	#set ($Senaraifail = $session.getAttribute("_portal_moduleVectorInternalFail")) #set ($startno =
	$startnoInt.intValue()) #set ($i = $startno) #set ($total = $totalInt.intValue())
	<table width="100%">
		<tr>
			<td>#parse("app/utils/record_paging.jsp")</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr class="table_header">
						<td width="3%" align="center" scope="row">
							<strong>Bil.</strong>
						</td>
						<td width="22%" align="center">
							<strong>Negeri</strong>
						</td>
						<td width="25%" align="center">
							Fail Muatnaik</strong>
						</td>
						<td width="25%" align="center">
							Tarikh Muatnaik</strong>
						</td>
						<td width="25%" align="center">
							</strong>
						</td>
					</tr>
					#if($ListResult.size() > 0) #foreach ($result in $ListResult ) #set($counter = $velocityCount ) #if ( ($counter %
					2) == 0 ) #set( $row ="row2" ) #else #set( $row = "row1" ) #end
					<tr>
						<td height="20" align="center" class="$row">$result.Bil</td>
						<td class="$row" align="center">$result.namaNegeri</td>
						<td class="$row" align="center">$result.namaFail</td>
						<td class="$row" align="center">$result.tarikhMasuk</td>
						<td class="$row" align="center">
							<a onclick="javascript:paparexcel('$result.idNegeri')" href="#" style="color: #0000FF">[Papar Data Excel]</a>
					</tr>
					#end #else
					<tr>
						<td colspan="6">Rekod Tidak Dijumpai</td>
					</tr>
					#end
				</table>
			</td>
		</tr>

		<!-- <tr>
  		<td colspan="5" scope="row" align="center">
  			 #if ( $i >= $Senaraifail.size() && $i < $total )
    			 <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
    			 #end
  			 #if (($i < $total && $Senaraifail.size() != $total && $i <= $Senaraifail.size()))
    			 <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
    			 #end
  		</td>
  	</tr> -->
	</table>
</fieldset>
<div style="visibility: hidden;">
	<input type="hidden" name="mode" />
	<input type="hidden" name="action" />
</div>
<input type="hidden" id="paramNegeri" name="paramNegeri" value="$!paramNegeri">
<input type="hidden" id="paramDaerah" name="paramDaerah" value="">
<input type="hidden" id="paramMukim" name="paramMukim" value="">
<input type="hidden" id="paramKawasan" name="paramKawasan" value="$paramKawasan">
<input type="hidden" id="paramNoWarta" name="paramNoWarta" value="$paramNoWarta">
<input type="hidden" id="paramNoPelan" name="paramNoPelan" value="$paramNoPelan">

<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>

<script type="text/javascript" src="../app/pdt/warta.js"></script>
<script type="text/javascript">
function tambah(){
	document.${formName}.hitButton.value = "tambah";
	 document.${formName}.submit();
}
function muatnaik(){
// 	document.${formName}.hitButton.value = "muatnaikSave";
// 	document.${formName}.method="post";
 var idNegeri = document.${formName}.paramNegeriUpload.value;
	if(idNegeri==''){
		alert("Sila pilih negeri");
	}else{
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.action = "?command=muatnaikSave&paramNegeriUpload="+idNegeri;
		document.${formName}.submit();
	}
}

/*
function Upload(){

	var ext = document.f1.fileupload11.value;
	var nama_dokumen = document.f1.nama_dokumen.value;
	var keterangan = document.f1.keterangan.value;
	var fileupload11 = document.f1.fileupload11.value;
	var lblNegeri = "";
	var lblDaerah = "";
	var tahunCukai = document.f1.tahun_cukai.value; 

	if(nama_dokumen ==""){
		alert("Sila Masukkan Nama Dokumen");
		return;
		
	}else if(tahunCukai == ""){
		alert("Sila Masukkan Tahun");
		return;
		
	}else if(ext ==""){
		alert("Sila Pilih Dokumen Untuk Dimuat Naik");
		document.f1.fileupload11.focus();
		return;
		
	}


	document.f1.enctype="multipart/form-data";
	if ( !window.confirm("Adakah Anda Pasti?")) 
		return;
	document.f1.action = "?command=uploadFile&extension="+ext+"&nama_dokumen="+nama_dokumen+"&fileupload11="+fileupload11+"&keterangan="+keterangan+"&lblNegeri="+lblNegeri+"&lblDaerah="+lblDaerah+"&tahun_cukai="+document.f1.tahun_cukai.value;
	document.f1.submit();
	document.f1.innerHTML ='<table width="100%" bgcolor="#FF1C2E"><tr><td><img src="/${session.getAttribute("_portal_appname")}/img/indicator.gif"><b>Sila tunggu...</b></td></tr></table>';
	
	
}
*/
function paparexcel(idNegeri){
	document.${formName}.hitButton.value = "paparexcel";
	document.${formName}.paramNegeri.value = idNegeri;
	 document.${formName}.submit();
}
function listcarian(){
	document.${formName}.hitButton.value = "listcarian";
	document.${formName}.action = "?command=";
	
	/* doAjaxCall${formName}("doCarian"); */
	 document.${formName}.submit();
}

function papar(idStrata) {
	document.${formName}.idStrata.value = idWarta;
	document.${formName}.hitButton.value = "papar";
	 document.${formName}.submit(); 
}
/* function hapus(){
	document.${formName}.hitButton.value = "hapus";
	document.${formName}.submit();
}

function papar(idStrata) {
	document.${formName}.idStrata.value = idStrata;
	document.${formName}.hitButton.value = "papar";
	document.${formName}.submit();
} */

function kosongkan() {
	
	document.${formName}.reset();
	document.${formName}.paramNegeri.value = "";
	document.${formName}.paramDaerah.value = "";
	document.${formName}.paramMukim.value = "";
	document.${formName}.paramNoWarta.value = "";
	document.${formName}.paramNoPelan.value = "";
	document.${formName}.paramStatus.value = "";
	document.${formName}.hitButton.value = "kosongkan";
	document.${formName}.submit();
}

function cetak(){

var reportfile = "";
var params = new Array();
params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
params[1] = "idNegeri="+document.${formName}.paramNegeri.value;
params[2] = "idDaerah="+document.${formName}.paramDaerah.value;
params[3] = "idMukim="+document.${formName}.paramMukim.value;
params[1] = "noWarta="+document.${formName}.paramNoWarta.value;
params[2] = "noPelan="+document.${formName}.paramNoPelan.value;


printReport(reportfile,params);
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function RemoveNonNumeric( trmString )
{
      var trmValidCharacters = "1234567890";
      var trmReturn = "";
      var trmBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < trmString.length; intIndex++ )
      {
    	  trmBuffer = trmString.substr( intIndex, 1 );
            // Is this a number
            if( trmValidCharacters.indexOf( trmBuffer ) > -1 )
            {
            	trmReturn += trmBuffer;
            }
      }
      return strReturn;
}
</script>