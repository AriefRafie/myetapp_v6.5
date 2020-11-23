<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
    </tr>
    <tr>
    	<td>
<fieldset> 
	<legend>CARIAN</legend>
		<table border="0" width="100%">
			<tr>
			    <td width="29%"><div align="right">Negeri</div></td>
			    <td width="1%">:</td>
			    <td width="70%">$!socNegeri</td>
			</tr>
			<tr>
			    <td width="29%"><div align="right">Agensi</div></td>
			    <td width="1%">:</td>
			    <td width="70%">$!socAgensi</td>
			</tr>
			<tr>
			    <td width="29%"><div align="right">No Fail</div></td>
			    <td width="1%">:</td>
			    <td width="70%"><input name="noFail" type="text" id="noFail" size="43" maxlength="400" value="$!carianNoFail" onkeyup="this.value=this.value.toUpperCase();"></td>
			</tr>
		  <tr>
		    <td width="29%"><div align="right">Tajuk Fail</div></td>
		    <td width="1%">:</td>
		    <td width="70%"><input name="namaFail" type="text" id="namaFail" size="43" maxlength="300" value="$!carian" onkeyup="this.value=this.value.toUpperCase();"></td>
		  </tr>
		  <tr>
			<td width="29%"><div align="right">Urusan</div></td>
			<td width="1%">:</td>
			<td width="70%">$!socsuburusan</td>
		  </tr>
		  <tr>
			<td width="29%"><div align="right">Status Permohonan</div></td>
			<td width="1%">:</td>
			<td width="70%">$!socstatus</td>
		  </tr>
		  <tr>
		    <td width="29%">&nbsp;</td>
		    <td width="1%">&nbsp;</td>
		    <td width="70%">&nbsp;</td>
		  </tr>			  
			  
			<!--  
			<tr>
		        <td height="25" align="right"><strong>No. Fail : &nbsp;&nbsp;</strong></td>
		        <td><input name="noFail" type="text" size="48" maxlength="400" value="$!carianNoFail" onkeyup="this.value=this.value.toUpperCase();" /></td>
		    </tr>
		    <tr>
		        <td height="25" align="right"><strong>Nama Fail : &nbsp;&nbsp;</strong></td>
		        <td><input name="namaFail" type="text" size="48" maxlength="300" value="$!carian" onkeyup="this.value=this.value.toUpperCase();"></td>
		   </tr>
	      <tr>
	        <td height="25"  align="right"><strong>Negeri : &nbsp;&nbsp;</strong></td>
	        <td>$socNegeri</td>
	      </tr> 
	      <tr>
       			<td></td>
		        <td>
		        <input class="stylobutton100" name="cari" value="Cari" type="button" onclick="findFail()">
		        <input class="stylobutton100" value="Kosongkan" onclick="" type="button">
		        </td>
      		</tr> -->
      	  <tr>
		    <td width="29%">&nbsp;</td>
		    <td width="1%">&nbsp;</td>
		    <td width="70%">
		    	<input class="stylobutton100"  name="cari" id="cari" value="Cari" type="button" onclick="javascript:findFail()" />
		      	<!-- <input class="stylobutton100"  name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" /></td> -->
		      	<input class="stylobutton100" type=button value = "Kosongkan" onClick="javascript:cancel();">
		  </tr>	
		</table>
</fieldset>

   		</td>	
   	</tr>

	<tr>
    	<td>
    		

<fieldset>
<legend>SENARAI FAIL</legend>
<table width="100%" class="header">
<tr>
  <td><table width="100%" class="header">
    <tr>
      <td colspan="6"><input name="add" value="Daftar Permohonan" type="button" onclick="doAjaxCall${formName}('tambahFail')" /></td>
    </tr> <!---->
    <tr>
      <td colspan="6">#parse("app/utils/record_paging.jsp")</td>
       </tr>
    <tr class="table_header">
        <td width="3%"><b>Bil.</b></td>
        <td width="15%"><b>No. Rujukan <i>Online</i></b></td>
        <td width="15%"><b>No. Fail</b></td>
        <td width="32%"><b>Tajuk Fail</b></td>
        <td width="15%"><b>Negeri</b></td>
        <td width="20%"><b>Status</b></td>      
    </tr>
    #set ($list = "")
    #set ( $cnt = 0 )	
    #if ($lists.size() > 0)
    #foreach ($senarai in $lists)
    #set ( $cnt = $cnt + 1 )
    
    #set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
    	#set( $row = "row2" )
    #else
    	#set( $row = "row1" )
    #end
    #set($s=$!senarai.getBil())
    #set($a=$!senarai.permohonan.getNamaNegeri())
    <tr>
      <td class="$row">$!s.</td>
      <td class="$row">
      	#if(!$!senarai.permohonan.pfdFail.getNoFail().equals('TIADA'))
			$!senarai.permohonan.getNoPermohonan()  	
	  	#else
			<a href="javascript:paparOnline('$!senarai.permohonan.getIdPermohonan()','$!senarai.getIdHtpPermohonan()')" class="style1">$!senarai.permohonan.getNoPermohonan()</a>   
		#end
    	</td>
      <td class="$row">
      	#if(!$!senarai.permohonan.pfdFail.getNoFail().equals('TIADA'))
			<a href="javascript:papar('$!senarai.permohonan.getIdPermohonan()','$!senarai.getIdHtpPermohonan()')" class="style1">$!senarai.permohonan.pfdFail.getNoFail()</a>   
	  	#else
			$!senarai.permohonan.pfdFail.getNoFail()  
		#end      	
      </td>
      <td class="$row">$!senarai.permohonan.getTujuan()</td>
      <td class="$row">$!a</td>
      <td class="$row">$!senarai.getStatusPermohonan()</td>
    </tr>
    #end
    #else
  <tr>
    <td colspan="6" class="row1">Tiada rekod.</td>
  </tr>
    #end
  </table></td>
</tr>
</table>
</fieldset>


		        </td>
      		</tr>
		</table>

<input type="hidden" name="txtidPermohonan1" />
<input type="hidden" name="txtidHtpPermohona1" value="$!htpPermohonan.getIdHtpPermohonan()"/>
<input type="hidden" name="command1" />

<script>
	//index_jsp
	function setSelected(tabId,command,mode,tabmode) {
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	}
function findFail(){
	doAjaxCall${formName}("searchFail");
}
function cancel() {
	doAjaxCall${formName}("kosong");	
}
function doChangeNegeriX() {
	doAjaxCall${formName}("","mode=changeNegeri");
	}
function paparTanah(idhtp){
	doAjaxCall${formName}("detailTanah",'idHakmilikUrusan='+idhtp);
}

	function paparTanahOnline(idhtp){
		doAjaxCall${formName}("detailtanahonline",'idHakmilikUrusan='+idhtp);
	}

function deleteTanah(idhtp,id){
	if ( !window.confirm("Adakah Anda Pasti?")) 
	return;
	doAjaxCall${formName}("deleteMaklumatTanah",'idHakmilikUrusan='+idhtp+'&idPermohonan='+id);
}

	function papar(id,idhtp){
		doAjaxCall${formName}("detail",'idPermohonan='+id+'&idHtpPermohonan='+idhtp);
	}
	
	function paparOnline(id,idhtp){
		doAjaxCall${formName}("detailonline",'idPermohonan='+id+'&idHtpPermohonan='+idhtp);
	}
	
//tanahInfoOnline.jsp		
	function simpanMaklumatTanah(){
	
		if(document.${formName}.socDaerah.value == ""){
			alert('Sila pilih " Daerah " terlebih dahulu.');
	  		document.${formName}.socDaerah.focus(); 
			return; 
		}
		if(document.${formName}.socMukim.value == ""){
			alert('Sila pilih " Mukim " terlebih dahulu.');
	  		document.${formName}.socMukim.focus(); 
			return; 
		}
		if(document.${formName}.socLuas.value == ""){
			alert('Sila pilih " Unit Luas " terlebih dahulu.');
	  		document.${formName}.socLuas.focus(); 
			return; 
		}
		if(document.${formName}.txtLuas.value == ""){
			alert('Sila pilih " Luas " terlebih dahulu.');
	  		document.${formName}.txtLuas.focus(); 
			return; 
		}
		
		if(document.${formName}.socJenisHakmilik.value == ""){
			alert('Sila pilih " Jenis Hakmilik " terlebih dahulu.');
	  		document.${formName}.socJenisHakmilik.focus(); 
			return; 
		}
		if(document.${formName}.txtNoHakmilik.value == ""){
			alert('Sila pilih " No. Hakmilik " terlebih dahulu.');
	  		document.${formName}.txtNoHakmilik.focus(); 
			return; 
		}
		if(document.${formName}.socLot.value == ""){
			alert('Sila pilih " Kod " terlebih dahulu.');
	  		document.${formName}.socLot.focus(); 
			return; 
		}
		if(document.${formName}.txtNoLot.value == ""){
			alert('Sila pilih " No Lot/PT " terlebih dahulu.');
	  		document.${formName}.txtNoLot.focus(); 
			return; 
		}
		if(document.${formName}.socKategori.value == ""){
			alert('Sila pilih " Kategori " terlebih dahulu.');
	  		document.${formName}.socKategori.focus(); 
			return; 
		}		
		doAjaxCall${formName}('simpanMaklumatTanah');
	}
	
	function kemaskiniMaklumatTanah(){
		
		if(document.${formName}.socDaerah.value == ""){
			alert('Sila pilih " Daerah " terlebih dahulu.');
	  		document.${formName}.socDaerah.focus(); 
			return; 
		}
			if(document.${formName}.socMukim.value == ""){
			alert('Sila pilih " Mukim " terlebih dahulu.');
	  		document.${formName}.socMukim.focus(); 
			return; 
		}
			if(document.${formName}.txdTarikhTerima.value == ""){
			alert('Sila masukan " Tarikh Mula " terlebih dahulu.');
	  		document.${formName}.txdTarikhTerima.focus(); 
			return; 
		}
			if(document.${formName}.txdTarikhLuput.value == ""){
			alert('Sila masukan " Tarikh Luput " terlebih dahulu.');
	  		document.${formName}.txdTarikhLuput.focus(); 
			return; 
		}
			if(document.${formName}.socLuas.value == ""){
			alert('Sila pilih " Unit Luas " terlebih dahulu.');
	  		document.${formName}.socLuas.focus(); 
			return; 
		}
			if(document.${formName}.txtLuas.value == ""){
			alert('Sila masukan " Luas " terlebih dahulu.');
	  		document.${formName}.txtLuas.focus(); 
			return; 
		}
				if(document.${formName}.socJenisHakmilik.value == ""){
			alert('Sila pilih " Jenis Hakmilik " terlebih dahulu.');
	  		document.${formName}.socJenisHakmilik.focus(); 
			return; 
		}
			if(document.${formName}.txtNoHakmilik.value == ""){
			alert('Sila masukan " No Hakmilik " terlebih dahulu.');
	  		document.${formName}.txtNoHakmilik.focus(); 
			return; 
		}
		
			if(document.${formName}.socLot.value == ""){
			alert('Sila pilih " Lot " terlebih dahulu.');
	  		document.${formName}.socLot.focus(); 
			return; 
		}
		
			if(document.${formName}.txtNoLot.value == ""){
			alert('Sila masukan " No Lot " terlebih dahulu.');
	  		document.${formName}.txtNoLot.focus(); 
			return; 
		}
		if(document.${formName}.socRizab.value == ""){
			alert('Sila pilih " Rizab " terlebih dahulu.');
	  		document.${formName}.socRizab.focus(); 
			return; 
		}
		if(document.${formName}.socKategori.value == ""){
			alert('Sila pilih " Kategori " terlebih dahulu.');
	  		document.${formName}.socKategori.focus(); 
			return; 
		}	
		
		doAjaxCall${formName}("kemaskiniMaklumatTanah");
	
	}	

	function simpanFail(){
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih " Negeri " terlebih dahulu.');
	  		document.${formName}.socNegeri.focus(); 
			return; 
		}
		if(document.${formName}.socKementerian.value == ""){
			alert('Sila pilih " Kementerian " terlebih dahulu.');
	  		document.${formName}.socKementerian.focus(); 
			return; 
		}
		if(document.${formName}.socAgensi.value == ""){
			alert('Sila pilih " Agensi " terlebih dahulu.');
	  		document.${formName}.socAgensi.focus(); 
			return; 
		}
		if(document.${formName}.socSuburusan.value == ""){
			alert('Sila pilih " Urusan " terlebih dahulu.');
	  		document.${formName}.socSuburusan.focus(); 
			return; 
		}
		/*if(document.${formName}.txtNoFailKJP.value == ""){
			alert('Sila masukkan " No. Fail KJP " terlebih dahulu.');
	  		document.${formName}.txtNoFailKJP.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhSurKJP.value == ""){
			alert('Sila masukkan " Tarikh Surat KJP " terlebih dahulu.');
	  		document.${formName}.txdTarikhSurKJP.focus(); 
			return; 
		}
		if(document.${formName}.txtNoFailLain.value == ""){
			alert('Sila masukkan " No. Fail Lain " terlebih dahulu.');
	  		document.${formName}.txtNoFailLain.focus(); 
			return; 
		}*/
		if(document.${formName}.txdTarikhPermohonan.value == ""){
			alert('Sila masukkan " Tarikh Permohonan " terlebih dahulu.');
	  		document.${formName}.txdTarikhPermohonan.focus(); 
			return; 
		}
		if ( !window.confirm("Anda Pasti?") ) return;
	
		doAjaxCall${formName}('simpan');
	}

function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function doChangeDaerah2() {
	doAjaxCall${formName}("doChangeDaerah2");
}

	function detailPemilik(id) {
		doAjaxCall${formName}("detailPemilik",'Idpihakberkepentingan='+id);
	}

	function detailPemilikOnline(id) {
		doAjaxCall${formName}("detailpemilikonline",'Idpihakberkepentingan='+id);
	}
		
function validatePoskod(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}


function simpanPenjual() {

	if(document.${formName}.selectDaerahP.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.selectDaerahP.focus(); 
		return; 
	}
	if(document.${formName}.selectNegeriP.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.selectNegeriP.focus(); 
		return; 
	}
	doAjaxCall${formName}("simpanPenjual");
}

	function kemaskiniPenjual(){
		doAjaxCall${formName}("kemaskiniPenjual");
	}

function updatePenjual(){
	if(document.${formName}.selectDaerahP.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.selectDaerahP.focus(); 
		return; 
	}
	if(document.${formName}.selectNegeriP.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.selectNegeriP.focus(); 
		return; 
	}
	doAjaxCall${formName}("updatePenjual");
}
	function penjualPembeli(){
		if(document.${formName}.PenjualSama.value != ""){
			doAjaxCall${formName}("assignPenjual");
		}
		
	}
	
	function hapusPenjual(){
		if ( !window.confirm("Anda Pasti?") ) return;
		doAjaxCall${formName}("hapuspenjual");
		
	}
	
function step4(idPermohonan){
	//doAjaxCall${formName}('perakuanPembelian','&idPermohonan='+idPermohonan);
	
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.RundinganPembelianModule&command=detail&idPermohonan="+idPermohonan;
	//document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.RundinganPembelianModule";
	//document.${formName}.mode.value = "view";
	//document.${formName}.command.value = "detail";
	//document.${formName}.idPermohonan.value = idPermohonan;
	document.${formName}.submit();
}

function step5(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}

function step3(idPermohonan){
	doAjaxCall${formName}('perakuanPembelian','&idPermohonan='+idPermohonan);
}
function step2(idPermohonan){
	doAjaxCall${formName}('maklumatTanah','&idPermohonan='+idPermohonan);
}

	function step2Online(idPermohonan){
		doAjaxCall${formName}('maklumatanahonline','&idPermohonan='+idPermohonan);
	}
function step1(idPermohonan,idhtp){
	doAjaxCall${formName}("detail",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
}

	function step1Online(idPermohonan,idhtp){
		doAjaxCall${formName}("detailonline",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
	}
	
function kembaliBangunan(){
	doAjaxCall${formName}("maklumatBangunan");
}
function simpanBangunan(){
	if(document.${formName}.idHakmilikUrusan.value == ""){
		alert('Sila pilih " No Hakmilik " terlebih dahulu.');
		return;
	}
	else if(document.${formName}.noPetak.value == ""){
		alert('Sila masukkan" No Petak " terlebih dahulu.');
		return;
	}
	doAjaxCall${formName}("simpanBangunan");
}

function updateBangunan(){
	if(document.${formName}.idHakmilikUrusan.value == ""){
		alert('Sila pilih " No Hakmilik " terlebih dahulu.');
		return;
	}
	else if(document.${formName}.noPetak.value == ""){
		alert('Sila masukkan" No Petak " terlebih dahulu.');
		return;
	}
	doAjaxCall${formName}("updateBangunan");
}

function paparBangunan(idBangunan){
	doAjaxCall${formName}("paparBangunan",'&idBangunan='+idBangunan);
}
function deleteBangunan(idBangunan){
	if ( !window.confirm("Anda Pasti?") ) return;
	doAjaxCall${formName}("deleteBangunan",'&idBangunan='+idBangunan);
}


// Senarai Dokumen/Cetakan

function senaraiDokumenSurat(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

// 2010/07/02 -Pilih Pegawai untuk tandatangan surat
function openSuratPegawai(urli,param,laporan,tem){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function simpanPemilik(){
	if(document.${formName}.ddownHakmilik.value == ""){
		alert('Sila pilih " No Hakmilik " terlebih dahulu.');
  		document.${formName}.ddownHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPemaju.value == ""){
		alert('Sila pilih " Nama Pemilik " terlebih dahulu.');
  		document.${formName}.txtNamaPemaju.focus(); 
		return; 
	}
		if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
		if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}
		if(document.${formName}.txtNoTelefon.value == ""){
		alert('Sila masukkan " No. Telefon " terlebih dahulu.');
	  	document.${formName}.txtNoTelefon.focus(); 
		return; 
	}
	doAjaxCall${formName}("simpanPemilik");
}

//STEP 4

//STEP 5

function kiraTahun() {

	var dari_bulan;
	var dari_hari;
	var dari_tahun;
	var hingga_bulan;
	var hingga_hari;
	var hingga_tahun;
	var daftar_bulan;
	var daftar_hari;
	var daftar_tahun;
	if(document.${formName}.socTaraf.value == 'P'){
		if(document.${formName}.txdTarikhLuput.value == ''){
			alert('Sila masukkan Tarikh Luput Hakmilik');
			document.${formName}.txdTarikhLuput.focus();
			return;
		
		}else{
			hingga_bulan = document.${formName}.txdTarikhLuput.value.substring(3,5);
			hingga_hari = document.${formName}.txdTarikhLuput.value.substring(0,2);
			hingga_tahun = document.${formName}.txdTarikhLuput.value.substring(6,10);
			var hinggatemp = hingga_bulan+"/"+hingga_hari+"/"+hingga_tahun;
	
			daftar_bulan = document.${formName}.txdTarikhTerima.value.substring(3,5);
			daftar_hari = document.${formName}.txdTarikhTerima.value.substring(0,2);
			daftar_tahun = document.${formName}.txdTarikhTerima.value.substring(6,10);
			var daftartemp = daftar_bulan+"/"+daftar_hari+"/"+daftar_tahun;
	
			dari_bulan = document.${formName}.txtTarikhPermohonan.value.substring(3,5);
			dari_hari = document.${formName}.txtTarikhPermohonan.value.substring(0,2);
			dari_tahun = document.${formName}.txtTarikhPermohonan.value.substring(6,10);
			var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
			
			var dari = Date.parse(daritemp);
			var hingga = Date.parse(hinggatemp);
		
			var diff_date =  hingga - dari;
			
			var num_years = diff_date/31536000000;
			var num_months = (diff_date % 31536000000)/2628000000;
			var num_days = ((diff_date % 31536000000) % 2628000000)/86400000;
			
			var daftar = Date.parse(daftartemp);
			var baki =  hingga - daftar;
			var bakiTahun = baki/31536000000;
			
			document.${formName}.txtTempoh.value = Math.floor(bakiTahun);
		  	document.${formName}.txtTempohbaki.value = Math.floor(num_years);
		}
	}
}

// 18/08/2010 -
function senaraiDokumenSurat(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

//18/08/2010 bertujuan mencetak doket atau Fail
// Dibuat oleh  : Rosli
// Dimodifikasi oleh :
function cetakFailDoket(id,temp,urlserv) {
	var param ="";
    var url = "../servlet/"+urlserv+"?"+id+temp;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

//semakan Tarikh semasa null
function validateTarikhSemasaIsNull(inputfield) {
	var today = new Date();	
	if(inputfield.value != ''){
		dari_bulan = inputfield.value.substring(3,5);
		dari_hari = inputfield.value.substring(0,2);
		dari_tahun = inputfield.value.substring(6,10);
		var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;	
		var myDate = Date.parse(daritemp);
	
		if (myDate>today) {
	  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
	  		inputfield.value="";
	  		inputfield.focus();
	 		return;
	 	}
	}
}

	function doChangePenjualNegeri(){
			doAjaxCall${formName}("doChangePenjualNegeri");
	}

	function kemaskiniPenjual(){
		doAjaxCall${formName}("kemaskiniPenjual");
	}
	
	function updatePenjual(){
		if(document.${formName}.selectDaerahP.value == ""){
			alert('Sila pilih " Daerah " terlebih dahulu.');
	  		document.${formName}.selectDaerahP.focus(); 
			return; 
		}
		if(document.${formName}.selectNegeriP.value == ""){
			alert('Sila pilih " Negeri " terlebih dahulu.');
	  		document.${formName}.selectNegeriP.focus(); 
			return; 
		}
		doAjaxCall${formName}("updatePenjual");
	}
	
	//STEP 5
	function cetakPengesahan(idpermohonan) {
	
	    //var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=PengesahanPembelianOnline&idpermohonan="+idpermohonan;
	    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=PengesahanPermohonanOnline&idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	
	
	}
	
	function XcetakImej(id){
		var url = "../servlet/ekptg.view.htp.FrmRekodDisplayImej?id="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function cetakImej(id){
		//var url = "../servlet/ekptg.view.htp.FrmRekodDisplayImej?id="+id;
	    var url = "../servlet/ekptg.view.pfd.DisplayBlob?id="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function deleteDetailImej(id,id1){
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		//document.${formName}.command.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=deleteDetailImej&idGambar="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("lampiran","&nextAction=hapus&idGambar="+id1+"&iddokumen="+id);
	}
	
	// dokumenOnline.jsp
		
	function tambahLampiran() {
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.pembelian.SenaraiFailModuleOnline";
		document.${formName}.method="POST";
		document.${formName}.command.value="tambahlampiran";
		document.${formName}.submit();
		
	}
	
	function viewDetailImej(id){
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.pembelian.SenaraiFailModuleOnline&nextAction=viewDetailImej&idGambar="+id;
		document.${formName}.method="POST";
		document.${formName}.command.value="lampiran";
		document.${formName}.submit();
		//doAjaxCall${formName}("lampiran","&nextAction=viewDetailImej&idGambar="+id);
		
	}
	
	//function paparOnline(id,idhtp){
	//}
	
	function doChangeJumlahLampiran(IDJadualLampiran,j,a) {
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}
	 	//doAjaxCall${formName}("imej","firstAction=PendaftaranImej);
		doAjaxCall${formName}("update","&idPermohonan="+document.${formName}.txtidPermohonan.value+"&idHtpPermohonan="+document.${formName}.txtidHtpPermohonan.value+"&X="+a+"&action=changeLampiran&IDJadualLampiran="+IDJadualLampiran);
	}
	
	function XsimpanLampiran(id){
		if(document.${formName}.fileupload.value == ""){
			alert("Sila pilih fail terlebih dahulu");
			return;
		}
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		var perihal = document.${formName}.txtPerihalImej.value;
		var ringkas = document.${formName}.txtRingkas.value ;
		//document.${formName}.command.value="simpanlampiran";
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.pembelian.SenaraiFailModuleOnline&command=simpanlampiran&idPermohonan="+document.${formName}.txtidPermohonan.value+"&idHtpPermohonan="+document.${formName}.txtidHtpPermohonan.value+"&txtPerihalImej="+perihal+"&txtRingkas="+ringkas;
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
	    document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();
		//doAjaxCall${formName}("simpanlampiran");
		
	}

	
	function Pengesahan(){
		doAjaxCall${formName}("pengesahan");
		
	}

	
</script>