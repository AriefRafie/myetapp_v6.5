<script type="text/javascript" src="../../library/js/report.js" ></script>
<strong><center><font size="3pt">Maklumat Strata(Skim Bangunan Khas)</font></center></strong>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="hitButton">
  <input type="hidden" name="idStrata">
  <input type="hidden" name="paramNegeri">
  <input type="hidden" name="paramKodLot">
  <input type="hidden" name="paramNoLot">
  <input type="hidden" name="paramNoCF">
  <input type="hidden" name="paramNamaPemaju">
  <input type="hidden" name="paramNamaSkim">
  <input type="hidden" name="paramOrderBy">
</p>
<fieldset>
<legend>
<strong>Senarai Laporan Strata(Skim Bangunan Khas)</strong></legend>
	<table align="center" width="100%">
		<tbody>
			<tr class="table_header">
				<td width="10%" align="center" scope="row"><strong>Bil</strong></td>
				<td width="80%" align="center" scope="row"><strong>Nama Laporan</strong></td>
			</tr>
			<tr>
				<td class="row1" align="center">1</td>
				<td class="row1" align="left"><a href="#" onclick="viewCetakStrata()"><font color='blue'>Senarai Skim Strata</font></a></td>
			</tr>
            <tr>
				<td class="row1" align="center">2</td>
				<td class="row1" align="left"><a href="#" onclick="viewCetak()"><font color='blue'>Senarai Skim Strata (Bangunan Khas)</font></a></td>
			</tr>
            <tr>
				<td class="row1" align="center">3</td>
				<td class="row1" align="left"><a href="#" onclick="cetakStatistikStrata()"><font color='blue'>Statistik Maklumat Strata</font></a></td>
			</tr>
			<tr>
				<td class="row1" align="center">4</td>
				<td class="row1" align="left"><a href="#" onclick="cetakStatistik()"><font color='blue'>Statistik Maklumat Strata (Skim Bangunan Khas)</font></a></td>
			</tr>
            <tr>
				<td class="row1" align="center">5</td>
				<td class="row1" align="left"><a href="#" onclick="cetakMengikutStatusStrata()"><font color='blue'>Statistik Maklumat Strata Mengikut Status</font></a></td>
			</tr>
			<tr>
				<td class="row1" align="center">6</td>
				<td class="row1" align="left"><a href="#" onclick="cetakMengikutStatus()"><font color='blue'>Statistik Maklumat Strata (Skim Bangunan Khas) Mengikut Status</font></a></td>
			</tr>
          <tr>
				<td class="row1" align="center">7</td>
				<td class="row1" align="left"><a href="#" onclick="cetakMengikutDaerahStrata()"><font color='blue'>Statistik Maklumat Strata Mengikut Daerah dan Status</font></a></td>
			</tr>
			<tr>
				<td class="row1" align="center">8</td>
				<td class="row1" align="left"><a href="#" onclick="cetakMengikutDaerah()"><font color='blue'>Statistik Maklumat Strata (Skim Bangunan Khas) Mengikut Daerah dan Status</font></a></td>
			</tr>
		
		</tbody>
	</table>
<input type="hidden" id="paramUserLogin" name="paramUserLogin" value='$!{session.getAttribute("_portal_login")}' >
<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>
	
</fieldset>

<script type="text/javascript">


function kosongkan() {
	document.${formName}.reset();
	document.${formName}.paramNoLot.value = "";
	document.${formName}.paramNoCF.value = "";
	document.${formName}.paramNamaPemaju.value = "";
	document.${formName}.paramNamaSkim.value = "";
	document.${formName}.submit();
}

function viewCetakStrata() {

	var paramNegeri = document.${formName}.paramNegeri.value;
	var paramKodLot = document.${formName}.paramKodLot.value;
	var paramNoLot = document.${formName}.paramNoLot.value;
	var paramNoCF = document.${formName}.paramNoCF.value;
	var paramNamaPemaju = document.${formName}.paramNamaPemaju.value;
	var paramNamaSkim = document.${formName}.paramNamaSkim.value;
	var paramOrderBy = document.${formName}.paramOrderBy.value;
	
	var url = "../x/${securityToken}/ekptg.view.str.FrmPopupSenaraiStrata" ;//?paramNegeri="+paramNegeri+"&paramKodLot="+paramKodLot+"&paramNoLot="+paramNoLot+"&paramNoCF="+paramNoCF+"&paramNamaPemaju="+paramNamaPemaju+"&paramNamaSkim="+paramNamaSkim;
    var hWnd = window.open(url,'printuser','width=600,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function viewCetak() {

	var paramNegeri = document.${formName}.paramNegeri.value;
	var paramKodLot = document.${formName}.paramKodLot.value;
	var paramNoLot = document.${formName}.paramNoLot.value;
	var paramNoCF = document.${formName}.paramNoCF.value;
	var paramNamaPemaju = document.${formName}.paramNamaPemaju.value;
	var paramNamaSkim = document.${formName}.paramNamaSkim.value;
	var paramOrderBy = document.${formName}.paramOrderBy.value;
	
	var url = "../x/${securityToken}/ekptg.view.str.FrmPopupSenaraiSkimBangunanKhas" ;//?paramNegeri="+paramNegeri+"&paramKodLot="+paramKodLot+"&paramNoLot="+paramNoLot+"&paramNoCF="+paramNoCF+"&paramNamaPemaju="+paramNamaPemaju+"&paramNamaSkim="+paramNamaSkim;
    var hWnd = window.open(url,'printuser','width=600,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakStatistikStrata() {

	//alert("test");
	var txt_userLogin = document.${formName}.paramUserLogin.value;
	
	var url = "../servlet/ekptg.report.strata.statistikMaklumatStrata?txt_userLogin="+txt_userLogin;		
	
	//alert("sini===="+url);
	 var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}


function cetakStatistik() {

	//alert("test");
	var txt_userLogin = document.${formName}.paramUserLogin.value;
	
	var url = "../servlet/ekptg.report.strata.statistikMaklumatStrataBangunanKhas?txt_userLogin="+txt_userLogin;		
	
	//alert("sini===="+url);
	 var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakMengikutStatusStrata() {

/*	var reportfile = "strStatistikMaklumatStrataIkutStatus";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "userLogin="+document.${formName}.paramUserLogin.value;
	
	printReport(reportfile,params);*/
	//alert("test");
	var txt_userLogin = document.${formName}.paramUserLogin.value;
	var url = "../servlet/ekptg.report.strata.statistikStatus?txt_userLogin="+txt_userLogin;;		
	
	//alert("sini===="+url);
	 var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakMengikutStatus() {

/*	var reportfile = "strStatistikMaklumatStrataIkutStatus";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "userLogin="+document.${formName}.paramUserLogin.value;
	
	printReport(reportfile,params);*/
	//alert("test");
	var txt_userLogin = document.${formName}.paramUserLogin.value;
	var url = "../servlet/ekptg.report.strata.statistikStatusBangunanKhas?txt_userLogin="+txt_userLogin;;		
	
	//alert("sini===="+url);
	 var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakMengikutDaerahStrata() {

	var paramNegeri = document.${formName}.paramNegeri.value;
	
	var url = "../x/${securityToken}/ekptg.view.str.FrmPopupStatistikStrataDaerah?paramNegeri="+paramNegeri;
    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakMengikutDaerah() {

	var paramNegeri = document.${formName}.paramNegeri.value;
	
	var url = "../x/${securityToken}/ekptg.view.str.FrmPopupStatistikSkimStrataDaerah?paramNegeri="+paramNegeri;
    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

</script>