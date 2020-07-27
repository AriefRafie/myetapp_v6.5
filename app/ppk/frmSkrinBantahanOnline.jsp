<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--bubuh kat sini-->
<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {color: #0000FF}
.style3 {font-size: 9px}
.style36 {font-size: 12}
.style42 {color: #0000FF}
.style51 {color: #0000FF; font-size: 9px; }
-->
</style>
</head>

<body onload="submitForm();" >
<form id="form1" name="f1" method="post" action="">

<input type="hidden" name="id_pemohon" id="id_pemohon" value="$!pemohon.iduser" />
<input type="hidden" name="id_fail" id="id_fail" value="$!idfail" />
<input type="hidden" name="nama_fail" id="nama_fail" value="$!nofail" />
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$!idPermohonan" />

<input type="hidden" name="id_bicara" id="id_bicara" value="$!id_bicara" />

<input name="nowpast" type="hidden" id="nowpast" value="$nowpast"/>
<br>

<!-- <table width="100%" border="0"> -->
<!-- 	<tr> -->
<!-- 		<td> -->
<!-- 			<fieldset> -->
<!-- 				<legend>SENARAI BANTAHAN DAHULU</legend> -->
<!-- 				#parse("app/utils/record_paging.jsp") -->
<!-- 				<table width="100%" border="0"> -->
<!-- 					<tr class="table_header"> -->
<!-- 					  	<td width="2%" align="center">No</td> -->
<!-- 					  	<td width="5%" align="center">Tarikh Hantar</td> -->
<!-- 					  	<td width="15%" align="center">No Fail</td> -->
<!-- 					  	<td width="15%" align="center">Nama Pembantah</td> -->
<!-- 					  	<td width="20%" align="center">Sebab Bantah</td> -->
<!-- 					  	<td width="10%" align="center">Dokumen Sokongan</td> -->
<!-- 				  	</tr> -->
<!-- 					#foreach ($senarai in $senaraibantahan) -->
<!-- 						#set( $counter = $velocityCount ) -->
<!-- 						#if ( ($counter % 2) == 0 ) -->
<!-- 							#set( $row = "row2" ) -->
<!-- 						#else -->
<!-- 							#set( $row = "row1" ) -->
<!-- 						#end -->
<!-- 				  	<tr> -->
<!-- 						<td class="$row" align="center"> -->
<!-- 						  #set ($cnt = ($page - 1) * $itemsPerPage + $counter ) -->
<!-- 						  $!cnt -->
<!-- 						 </td> -->
<!-- 						 <td class="$row" align="center">$!senarai.tarikh_hantar</td> -->
<!-- 						 <td class="$row" align="center">$!senarai.no_fail</td> -->
<!-- 						 <td class="$row" align="center">$!senarai.nama_pembantah</td> -->
<!-- 						 <td class="$row" align="center">$!senarai.sebab</td> -->
<!-- 						 <td class="$row" align="center"></td> -->
<!-- 					</tr> -->
<!-- 					#end -->
<!-- 					#if ($cnt == 0) -->
<!-- 					<tr>  -->
<!-- 						<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod</font></td> -->
<!-- 					</tr> -->
<!-- 					#end -->
<!-- 				</table>	 -->
<!-- 			</fieldset> -->
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->

<!-- <br> -->





<!-- PEMOHON ADALAH PEMBANTAH -->
#if($nowpast == "now")
<table width="100%" border="0">
	<tr>
		<td>
			<fieldset>
				<legend>PERMOHONAN BANTAHAN</legend>
				<table width="100%" border="0">
					<tr>
						<td width="1%" valign="top">&nbsp;</td>
		             	<td width="17%">No. Fail</td>
		             	<td width="1%">:</td>
						<td><b>$!nofail</b></td>
					</tr>
					<tr>
						<td width="1%" valign="top">&nbsp;</td>
		             	<td width="17%">Nama Simati</td>
		             	<td width="1%">:</td>
						<td><b>$!nama_simati</b></td>
					</tr>
					<tr>
						<td width="1%" valign="top">&nbsp;</td>
		             	<td width="17%">No. K/P Simati</td>
		             	<td width="1%">:</td>
						<td><b>$!ic_simati</b></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					
	             	<tr>
	             	  <td width="1%" valign="top"><span class="style1">*</span></td>
		              <td width="17%">Nama</td>
		              <td width="1%">:</td>
		              <td><input name="txtNama" type="text"
							id="txtNama" size="43"
							maxlength="80" 
							onblur="this.value=this.value.toUpperCase();tukarInput()" /></td>
		           </tr>
		           <tr>
		           	  <td width="1%" valign="top"><span class="style1">*</span></td>
		             <td width="17%">No. K/P</td>
		              <td width="1%">:</td>
		              <td><input name="txtNoKPLamaPemohon" type="text" 
							id="txtNoKPLamaPemohon" size="43"
							maxlength="80" 
							onblur="this.value=this.value.toUpperCase();tukarInput()" /></td>
		          </tr>
		          <tr>
		          	  <td width="1%" valign="top"><span class="style1">*</span></td>
		              <td width="17%">Alamat</td>
		              <td width="1%">:</td>
		              <td width="80%"><input name="txtAlamat1" type="text" id="txtAlamat1" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()"  size="50"  maxlength="200"/></td>
		          </tr>
		          <tr>
		          	<td width="1%">&nbsp;</td>
		          	<td width="17%">&nbsp;</td>
		          	<td width="1%">&nbsp;</td>
          			<td width="80%"><input name="" type="text" id="" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()"  size="50"  maxlength="200" /></td>
		          </tr>
		          <tr>
		          	<td width="1%">&nbsp;</td>
		          	<td width="17%">&nbsp;</td>
		          	<td width="1%">&nbsp;</td>
          			<td width="80%"><input name="" type="text" id="" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()"  size="50"  maxlength="200" /></td>
		          </tr>
		          <tr>
		          	  <td width="1%" valign="top"><span class="style1">*</span></td>
		              <td width="17%">Poskod</td>
		              <td width="1%">:</td>
		              <td width="80%"><input name="txtPoskod" type="text" id="txtPoskod" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" size="50"  maxlength="200"/></td>
		          </tr>
		           <tr>
		           	  <td width="1%" valign="top"><span class="style1">*</span></td>
		              <td width="17%">Bandar</td>
		              <td width="1%">:</td>
		              <td width="80%"><input name="txtBandar" type="text" id="txtBandar" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" size="50"  maxlength="200"/></td>
		          </tr>
		           <tr>
		           	  <td width="1%" valign="top"><span class="style1">*</span></td>
		             <td width="17%">Negeri</td>
		              <td width="1%">:</td>
		              <td width="80%"><input name="txtNegeri" type="text" id="txtNegeri" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()"  size="50"  maxlength="200" /></td>
		          </tr>
		           <tr>
		           	  <td width="1%" valign="top"><span class="style1">*</span></td>
		              <td width="17%">No. Telefon</td>
		              <td width="1%">:</td>
		              <td width="80%"><input name="txtNoTel" type="text" id="txtNoTel" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()" size="50"  maxlength="200" /></td>
		          </tr>
		           <tr>
		           	  <td width="1%">&nbsp;</td>
		              <td width="17%">Emel</td>
		              <td width="1%">:</td>
		              <td width="80%"><input name="txtEmel" type="text" id="txtEmel" style=""  onblur="" size="50"  maxlength="200" /></td>
		          </tr>
		           <tr>
		           	  <td width="1%" valign="top"><span class="style1">*</span></td>
		              <td width="17%" valign="top">Sebab Bantah</td>
		              <td width="1%" valign="top">:</td>
		              <td width="80%"><textarea cols="60" rows="5" name="catatan" id="catatan"></textarea></td>
		          </tr>
		           <tr>
		           	  <td width="1%">&nbsp;</td>
		              <td width="17%">Dokumen Sokongan</td>
		              <td width="1%">:</td>
		              <td width="80%"><input id="docSokongan" name="docSokongan" type="file" value="Lampiran" size="40"  onClick="lampiran('$!idPermohonan','dokumenS')"></td>
		          </tr>
	            </table>
	            <table width="100%" border="0">
	            	<tr>&nbsp;</tr>
	            	<tr>&nbsp;</tr>
	            	<tr>
	            		<td width="1%" valign="top"></td>
<!-- 	    				<td colspan="3"><input type="checkbox" name='cbDisclaimer' id='cbDisclaimer' class=disabled> &nbsp;Saya $!pemohon.namaPemohon No.K/P $!pemohon.noPengenalan dengan ini mengakui bahawa maklumat yang diberikan dalam borang ini adalah benar, betul dan lengkap.</td> -->
	      				<td colspan="3"><input type="checkbox" name='cbDisclaimer' id='cbDisclaimer' class=disabled> Saya <input style="border:none;background:transparent;" name="nameD" id="nameD"/> No.K/P <input style="border:none;background:transparent;" name="no_kpD" id="no_kpD"/> dengan ini mengakui bahawa maklumat yang diberikan dalam borang ini adalah benar, betul dan lengkap.</td>
	      			</tr>
	            </table>
			</fieldset>
		</td>
	</tr>
</table>
#end

<!-- PEMOHON BUKAN PEMBANTAH -->
#if($nowpast == "past")
<table width="100%" border="0">
	<tr>
		<td>
			<fieldset>
				<legend>PERMOHONAN BANTAHAN BARU</legend>
				<table width="100%" border="0">
				<tr>
					<td width="1%" valign="top">&nbsp;</td>
					<td width="17%">No. Fail</td>
	             	<td width="1%">:</td>
					<td><b>$!nofail</b></td>
				</tr>
				<tr>
					<td width="1%" valign="top">&nbsp;</td>
					<td width="17%">Nama Simati</td>
	             	<td width="1%">:</td>
					<td><b>$!nama_simati</b></td>
				</tr>
				<tr>
						<td width="1%" valign="top">&nbsp;</td>
		             	<td width="17%">No. K/P Simati</td>
		             	<td width="1%">:</td>
						<td><b>$!ic_simati</b></td>
					</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
             	  <td width="1%" valign="top"><span class="style1">*</span></td>
	              <td width="17%">Nama</td>
	              <td width="1%">:</td>
	              <td><input name="txtNama" type="text"
						id="txtNama" size="43"
						maxlength="80" 
						onblur="this.value=this.value.toUpperCase();tukarInput()" /></td>
	           </tr>
	           <tr>
	           	  <td width="1%" valign="top"><span class="style1">*</span></td>
	              <td width="17%">MyID</td>
	              <td width="1%">:</td>
	              <td><input name="txtNoKPLamaPemohon" type="text"
						id="txtNoKPLamaPemohon" size="43"
						maxlength="80"
						onblur="this.value=this.value.toUpperCase();tukarInput()" /></td>
				</tr> 
				<tr>
					<td width="1%" valign="top"><span class="style1">*</span></td>
				    <td width="17%">Alamat</td>
				    <td width="1%">:</td>
				    <td width="80%"><input name="txtAlamat1" type="text" id="txtAlamat1" style="text-transform:uppercase;" value="$!txtAlamat1" onblur="this.value=this.value.toUpperCase()" size="50"  maxlength="200" /></td>
				</tr>
				<tr>
					<td width="1%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
					<td width="1%">&nbsp;</td>
					<td width="80%"><input name="txtAlamat2" type="text" id="txtAlamat2" style="text-transform:uppercase;"  value="$!txtAlamat2" onblur="this.value=this.value.toUpperCase()" size="50"  maxlength="200" /></td>
				</tr>
				<tr>
					<td width="1%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
					<td width="1%">&nbsp;</td>
					<td width="80%"><input name="txtAlamat3" type="text" id="txtAlamat3" style="text-transform:uppercase;"  value="$!txtAlamat3" onblur="this.value=this.value.toUpperCase()" size="50"  maxlength="200" /></td>
				</tr>
				<tr>
					<td width="1%" valign="top"><span class="style1">*</span></td>
				    <td width="17%">Poskod</td>
				    <td width="1%">:</td>
				    <td width="80%"><input name="txtPoskod" type="text" id="txtPoskod" style="text-transform:uppercase;"  value="$!txtPoskod" onblur="this.value=this.value.toUpperCase()" size="50"  maxlength="200"/></td>
				</tr>
				 <tr>
				 	<td width="1%" valign="top"><span class="style1">*</span></td>
				    <td width="17%">Bandar</td>
				    <td width="1%">:</td>
				    <td width="80%"><input name="txtBandar" type="text" id="txtBandar" style="text-transform:uppercase;"  value="$!txtBandar" onblur="this.value=this.value.toUpperCase()" size="50"  maxlength="200" /></td>
				</tr>
				 <tr>
				 	<td width="1%" valign="top"><span class="style1">*</span></td>
				    <td width="17%">Negeri</td>
				    <td width="1%">:</td>
				    <td width="80%"><input name="txtNegeri" type="text" id="txtNegeri" style="text-transform:uppercase;"  value="$!txtNegeri" onblur="this.value=this.value.toUpperCase()" size="50"  maxlength="200" /></td>
				</tr>
				 <tr>
				 	<td width="1%" valign="top"><span class="style1">*</span></td>
				    <td width="17%">No. Telefon</td>
				    <td width="1%">:</td>
				    <td width="80%"><input name="txtNoTel" type="text" id="txtNoTel" style="text-transform:uppercase;" value="$!txtNoTel" onblur="this.value=this.value.toUpperCase()" size="50"  maxlength="200"/></td>
				</tr>
				 <tr>
				 	<td width="1%">&nbsp;</td>
				    <td width="17%">Emel</td>
				    <td width="1%">:</td>
				    <td width="80%"><input name="txtEmel" type="text" id="txtEmel" style="" value="$!txtEmel" onblur="" size="50"  maxlength="200" /></td>
				</tr>
				 <tr>
				 	<td width="1%" valign="top"><span class="style1">*</span></td>
				    <td width="17%">Sebab Bantah</td>
				    <td width="1%">:</td>
				    <td width="80%"><textarea cols="60" rows="5" name="catatan" id="catatan" value="$!catatan"></textarea></td>
				</tr>
				 <tr>
				 	<td width="1%">&nbsp;</td>
				    <td width="17%">Muatnaik Dokumen Sokongan</td>
				    <td width="1%">:</td>
				    <td width="80%"><input id="fileupload" name="fileupload" type="file" value="Lampiran" size="40" onClick="lampiran('$!idPermohonan','dokumenS')"></td>
				</tr>
	            </table>
	            
	            <br>
				
	            <table width="100%" border="0" name="viewDisclaimer" id="viewDisclaimer">
	            	<tr>&nbsp;</tr>
	            	<tr>&nbsp;</tr>
	            	<tr>
	            		<td width="1%" valign="top"></td>
	    				
<!-- 	      				<td width="89%">Saya $!name No.K/P $!no_kp dengan ini mengakui bahawa maklumat yang diberikan dalam borang ini adalah benar, betul dan lengkap.</td> -->
	      				<td colspan="3"><input type="checkbox" name='cbDisclaimer' id='cbDisclaimer' class=disabled> Saya <input style="border:none;background:transparent;" name="nameD" id="nameD"/> No.K/P <input style="border:none;background:transparent;" name="no_kpD" id="no_kpD"/> dengan ini mengakui bahawa maklumat yang diberikan dalam borang ini adalah benar, betul dan lengkap.</td>
	            	</tr>
	            </table>
			</fieldset>
		</td>
	</tr>
</table>
#end

<table width="100%">
	<tr><td align="center"><input type="button" name="btnHantar" id="btnHantar" value="Hantar" onclick="Simpan()"/></td></tr>
</table>

</form>
</body>
</html>

<script>

function lampiran(idPermohonan,jenisUpload) {	
	// console.log("syafiqah :"+idPermohonan);
	jenisUpload = "paparlampiran";
	var url = "../x/${securityToken}/ekptg.view.ppk.util.FrmUploadDokumen?actionrefresh=dokumenS&actionPopup="+jenisUpload+"&rujukan="+idPermohonan+"&flagOnline=$!flagOnline";
    url +="&jenisdokumen=99204";
		
	//
    var hWnd = window.open(url,'printuser','width=400,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus(); /**/
    //
    var title = 'Lampiran';
	var w =1024;
	var h = 800;
    var left = (screen.width/2)-(w/2);

}

function Simpan(){
	if (document.${formName}.txtNama.value == "0" || document.${formName}.txtNama.value == "") {
	      alert("Sila masukkan Nama Pembantah");
	      document.f1.catatan.focus();
	}
	else if (document.${formName}.txtNoKPLamaPemohon.value == "0" || document.${formName}.txtNoKPLamaPemohon.value == "") {
	      alert("Sila masukkan No MyID");
	      document.f1.catatan.focus();
	}
	else if (document.${formName}.txtAlamat1.value == "0" || document.${formName}.txtAlamat1.value == "") {
	      alert("Sila masukkan Alamat Pembantah");
	      document.f1.catatan.focus();
	}
	else if (document.${formName}.txtPoskod.value == "0" || document.${formName}.txtPoskod.value == "") {
	      alert("Sila masukkan Poskod");
	      document.f1.catatan.focus();
	}
	else if (document.${formName}.txtBandar.value == "0" || document.${formName}.txtBandar.value == "") {
	      alert("Sila masukkan Bandar");
	      document.f1.catatan.focus();
	}
	else if (document.${formName}.txtNegeri.value == "0" || document.${formName}.txtNegeri.value == "") {
	      alert("Sila masukkan Negeri");
	      document.f1.catatan.focus();
	}
	else if (document.${formName}.txtNoTel.value == "0" || document.${formName}.txtNoTel.value == "") {
	      alert("Sila masukkan No. Telefon");
	      document.f1.catatan.focus();
	}
	else if (document.${formName}.txtEmel.value == "0" || document.${formName}.txtEmel.value == "") {
	      alert("Sila masukkan Emel");
	      document.f1.catatan.focus();
	}
	else if (document.${formName}.catatan.value == "0" || document.${formName}.catatan.value == "") {
	      alert("Sila masukkan Sebab Bantah");
	      document.f1.catatan.focus();
	      return;
	}
	else if (cbDisclaimer.checked == false) {
	 	alert('Sila tanda pada checkbox untuk teruskan permohonan');
	 	return;
 	}
	else {
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBantahanOnline&command=hantarBantahan";
			document.${formName}.method="POST";
			// document.f1.command.value="hantarBantahan";
			document.${formName}.submit();
			// alert("SILA HADIR PADA HARI PERBICARAAN");
		}	
	}
}

function tukarInput(){
	var name = document.getElementById("txtNama").value;
	var no_kp = document.getElementById("txtNoKPLamaPemohon").value;
	
	document.${formName}.nameD.value = name;
	document.${formName}.no_kpD.value = no_kp;
}


function changeHantar(){
	if (document.${formName}.txtNama.value == "0" || document.${formName}.txtNama.value == "") {
	      alert("Sila masukkan Nama Pembantah");
	      document.f1.catatan.focus();
	}
	else if (document.${formName}.txtNoKPLamaPemohon.value == "0" || document.${formName}.txtNoKPLamaPemohon.value == "") {
	      alert("Sila masukkan No MyID");
	      document.f1.catatan.focus();
	}
	else {
		var name = document.getElementById("txtNama").value;
		var no_kp = document.getElementById("txtNoKPLamaPemohon").value;
		// showDisclaimer(name, no_kp);
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBantahanOnline&command=paparDcl&name="+name+"&no_kp="+no_kp;
		document.${formName}.method="POST";
	  	document.${formName}.submit();
	}
}

/* if( document.f1.btnSimpan.value == "Simpan"){
	console.log('test');
}  */

function showDisclaimer(name,no_kp){
	
	alert(name+no_kp); 
}


function NowView(idfail,nofail,simati,ic) {
// 	alert(idfail+"&"+nofail)
// 	document.${formname}.method="post";
// 	// document.${formName}.mode.value ="Nowview";
// 	document.${formname}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnBantahanOnline";
// 	doAjaxCall${formName}("skrinBantahNow");
// 	document.${formName}.submit();
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBantahanOnline&command=skrinBantahNow&idFail="+idfail+"&noFail="+nofail+"&nama_simati="+simati+"&icSimati="+ic;
	document.${formName}.method="POST";
  	document.${formName}.submit();
}

function PastView(idfail,nofail,simati,ic) {
// 	alert(idfail+"&"+nofail)
// 	document.${formname}.method="post";
// 	// document.${formName}.mode.value ="Nowview";
// 	document.${formname}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnBantahanOnline";
// 	doAjaxCall${formName}("skrinBantahPast");
// 	document.${formName}.submit();
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBantahanOnline&command=skrinBantahPast&idFail="+idfail+"&nofail="+nofail+"&nama_simati="+simati+"&icSimati="+ic;
	document.${formName}.method="POST";
  	document.${formName}.submit();
}

</script>

