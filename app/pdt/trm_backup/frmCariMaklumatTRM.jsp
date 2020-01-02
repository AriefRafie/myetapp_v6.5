<table width="100%">
<tr>
	<td width="5"></td>
	<td align="left"> <strong>Maklumat Tanah Rizab Melayu</strong></td>
</tr>
</table>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="hitButton">
	<input type="hidden" name="idWarta">
	<input type="hidden" name="idPelan">
	<input type="hidden" id="selectedTab" name="selectedTab" value="$!selectedTab" />
	<input type="hidden" id="warta" name="warta" value="$!warta" />
</p>
<fieldset>
	<legend>
		<strong>Carian Warta</strong>
	</legend>
	<table width="100%">
	<p style="padding: 2pt 4pt 3pt 0%;"><font color="red">*Sila masukkan maklumat carian</font></p>
		<tr>
			<td width="29%" align="right" scope="row">Negeri</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">$selectNegeri</td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row">Daerah</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">$selectDaerah</td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row">Bandar/Pekan/Mukim</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">$selectMukim</td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row">No. Warta</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">
				<input name="paramNoWarta" type="text" id="paramNoWarta" value="$!paramNoWarta" />
			</td>
		</tr>
		<tr>
			<td align="right" scope="row">No. Pelan</td>
			<td scope="row">:</td>
			<td>
				<input name="paramNoPelan" type="text" id="paramNoPelan" value="$!paramNoPelan" />
			</td>
		</tr>
		<tr>
			<td align="right" scope="row">Jenis Warta</td>
			<td scope="row">:</td>
			<td>
				<select id="paramJenis" name="paramJenis">
					<option value="">SILA PILIH</option>
					<option value="W">Pewujudan/Penggantian</option>
					<option value="B">Pembatalan</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" scope="row">Status</td>
			<td scope="row">:</td>
			<td>
				<select id="paramStatus" name="paramStatus">
					<option value="">SILA PILIH</option>
					<option value="0">Tidak Kuatkuasa</option>
					<option value="1">Kuatkuasa</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" scope="row">&nbsp;</td>
			<td scope="row">&nbsp;</td>
			<td>
				<input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
				<input type="reset" name="cmdKosongkanAkta" value="Kosongkan" onclick="kosongCarianAkta()" />
			</td>
		</tr>
	</table>
</fieldset>
<fieldset>
	<legend>
		<strong>Senarai Tanah Rizab Melayu</strong>
	</legend>
	#set ($Senaraifail = $session.getAttribute("_portal_moduleVectorInternalFail")) #set ($startno =
	$startnoInt.intValue()) #set ($i = $startno) #set ($total = $totalInt.intValue())
	<table width="100%">
		<p>
			<font color="red">*Sila klik hyperlink No.Warta untuk kemaskini maklumat warta</font><br /> <font color="red"></font>
		</p>
		<tr>
			<td>
			#parse("app/utils/record_paging.jsp") 
				<input type="button" name="cmdMuatNaik" id="cmdMuatNaik"value="Muatnaik Fail Excel" onclick="javascript:muatnaik()" /> 
				<input type="button" name="cmdTambah" id="cmdTambah"value="Warta Perwujudan" onclick="javascript:tambah()" />
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr class="table_header">
						<td width="3%" align="center" scope="row">
							<strong>Bil.</strong>
						</td>
						<td width="15%" align="center">
							<Strong>No. Warta</strong>
						</td>
						<td width="13%" align="center">
							<strong>Negeri</strong>
						</td>
						<td width="15%" align="center">
							<Strong>Daerah/Jajahan</strong>
						</td>
						<td width="14%" align="center">
							<Strong>Bandar/Pekan/Mukim</strong>
						</td>
                        <td width="14%" align="center">
							<Strong>Kawasan</strong>
						</td>
						
                        <td width="10%" align="center">
							<Strong>Tarikh</strong>
						</td>
						<td width="10%" align="center">
							<Strong>No.Pelan</strong>
						</td>
						<td width="10%" align="center">
							<Strong>Luas(hektar)</strong>
						</td>
						<td width="10%" align="center">
							<Strong>Jenis Warta</strong>
						</td>
						<td width="10%" align="center">
							<Strong>Status</strong>
						</td>
						<td width="10%" align="center">
						</td>
					</tr>
					#if($SenaraiFail.size() > 0) 
					#foreach ($result in $SenaraiFail ) 
					#set($counter = $velocityCount ) 
					#if ( ($counter % 2) == 0 ) 
						#set( $row ="row2" ) 
							#else 
								#set( $row = "row1" ) 
					#end
					<tr>
						<td height="20" align="center" class="$row">$result.Bil</td>
						
						<td class="$row" align="center">
							#if($result.jenisWarta == 'Pewujudan')
							#if($result.idTblPdtWartaganti == '')
								#set($tab = '0')
							#else
								#set($tab = '2')
							#end
						#else
							#set($tab = '1')
						#end
						
						#if($result.jenisWarta == 'Pewujudan')
							#if($result.idTblPdtWartaganti == '')
								<a onclick="javascript:paparWujud('$result.IdWarta','$result.idPelan',$tab)" href="#" style="color: #0000FF">$!result.NoWarta</a>
							#else
								<a onclick="javascript:paparGanti('$result.IdWarta','$result.idPelan',$tab)" href="#" style="color: #0000FF">$!result.NoWarta</a>
							#end
						#else
							<a onclick="javascript:paparBatal('$result.IdWarta','$result.idPelan',$tab)" href="#" style="color: #0000FF">$!result.NoWarta</a>
						#end
						
						<br>
							#if($result.KANDUNGAN_WARTA == 'true')<br><a href="javascript:onClick=paparWarta('$result.IdWarta');" style="color: #0000FF">[Papar Warta]</a>#end
						</td>
						
						<td class="$row" align="center">$result.Negeri</td>
						<td class="$row" align="center">$result.Daerah</td>
						<td class="$row" align="center">$!result.Mukim</td>
                        <td class="$row" align="center"> $!result.Kawasan</td>
						
						
							
                        <td class="$row" align="center"> $!result.TarikhWarta</td>
						<td class="$row" align="center">
						 $result.NoPelan
						#if($result.KANDUNGAN_PELAN == 'true')<a href="javascript:onClick=paparPelan('$result.idPelan');" style="color: #0000FF">[Papar Pelan]</a>#end
						</td>
						<td class="$row" align="center">$result.Luas</td>
						#if($result.jenisWarta == 'Pewujudan')
							#if($result.idTblPdtWartaganti == '')
								<td class="$row" align="center">Pewujudan</td>
							#else
								<td class="$row" align="center">
								Penggantian <br>
								$result.noWartaAsalganti
								</td>
							#end
						#else
							<td class="$row" align="center">
							$result.jenisWarta <br> $result.noWartaAsalbatal
							</td>
						#end	
						<td class="$row" align="center">$result.Status</td>
						<td class="$row" align="center"><a alt="Hapus" href = "javascript:delete('$result.idTblPdtTanahRizabMelayu')">
	&nbsp;&nbsp;<img border="0" src="../img/delete.gif"/></a> </td>
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
	<div align="right">
		<input type="button" name="cmdCetakLaporanAll" id="cmdCetakLaporanAll" value="Cetak Laporan Keseluruhan" onclick="viewCetakAll()" />
		<input type="button" name="cmdCetakLaporan" id="cmdCetakLaporan" value="Cetak Laporan" onclick="viewCetak()" />
	</div>
</fieldset>
<div style="visibility: hidden;">
	<input type="hidden" name="mode" />
</div>
<!-- <input type="hidden" id="paramNegeri" name="paramNegeri" value="$!paramNegeri">
<input type="hidden" id="paramDaerah" name="paramDaerah" value="$!paramDaerah">
<input type="hidden" id="paramMukim" name="paramMukim" value="$!paramMukim">
<input type="hidden" id="paramKawasan" name="paramKawasan" value="$!paramKawasan">
<input type="hidden" id="paramNoWarta" name="paramNoWarta" value="$!paramNoWarta">
<input type="hidden" id="paramNoPelan" name="paramNoPelan" value="$!paramNoPelan">

 -->
 <input type="hidden" id="pageDoChange" name="pageDoChange" value="$!pageDoChange">
<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>
<script type="text/javascript">

function paparWarta(id){
	var url = "../servlet/ekptg.view.pdt.TblPdtWartaViewBlob?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function paparPelan(id){
	var url = "../servlet/ekptg.view.pdt.TblPdtPelanViewBlob?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function uploadImej(step){
	document.${formName}.step.value=step;
	document.${formName}.hitButton.value="openPageUpload";
	document.${formName}.submit();
}


function doChangeNegeri(){
	document.${formName}.pageDoChange.value = "carian";
	doAjaxCall${formName}("doFilter");	
}

function doChangeDaerah(){
	document.${formName}.pageDoChange.value = "carian";
	doAjaxCall${formName}("doFilter");
}

function carian(){
	document.${formName}.hitButton.value = "cari";
	doAjaxCall${formName}("doCarian");
}

function tambah(){
	document.${formName}.hitButton.value = "tambah";
	 document.${formName}.submit();
}
function muatnaik(){
	document.${formName}.hitButton.value = "muatnaik";
	/* doAjaxCall${formName}("doCarian"); */
	 document.${formName}.submit();
}

/*
function uploadExcel() {
	
	var url = "../x/${securityToken}/ekptg.view.pdt.FrmTRMUploadExcel";
    var hWnd = window.open(url,'printuser','width=500,height=250, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
*/

function paparWujud(idWarta,idPelan,tab) {
	document.${formName}.idWarta.value = idWarta;
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "papar";
	document.${formName}.selectedTab.value = tab;
	document.${formName}.warta.value = "wujud";
	document.${formName}.submit();
}

function paparGanti(idWarta,idPelan,tab) {
	document.${formName}.idWarta.value = idWarta;
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "papar";
	document.${formName}.selectedTab.value = tab;
	document.${formName}.warta.value = "ganti";
	document.${formName}.submit();
}

function paparBatal(idWarta,idPelan,tab) {
	document.${formName}.idWarta.value = idWarta;
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "papar";
	document.${formName}.selectedTab.value = tab;
	document.${formName}.warta.value = "batal";
	document.${formName}.submit();
}


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

function view() {
	
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

function viewCetak(){

var reportfile = "MaklumatTanahRizabMelayu";
var params = new Array();
params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
params[1] = "ID_NEGERI="+document.${formName}.paramNegeri.value;
params[2] = "paramDaerah="+document.${formName}.paramDaerah.value;
params[3] = "paramMukim="+document.${formName}.paramMukim.value;
params[4] = "paramNoPelan="+document.${formName}.paramNoWarta.value;
params[5] = "paramNoWarta="+document.${formName}.paramNoPelan.value;
printReport(reportfile,params);
}

function viewCetak(){

	var reportfile = "MaklumatTanahRizabMelayu";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "ID_NEGERI="+document.${formName}.paramNegeri.value;
	params[2] = "paramDaerah="+document.${formName}.paramDaerah.value;
	params[3] = "paramMukim="+document.${formName}.paramMukim.value;
	params[4] = "paramNoPelan="+document.${formName}.paramNoWarta.value;
	params[5] = "paramNoWarta="+document.${formName}.paramNoPelan.value;
	printReport(reportfile,params);
	}
	
function viewCetakAll(){

	var reportfile = "LaporanKeseluruhanTanahRizabMelayu";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
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