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
<legend>SENARAI PERMOHONAN</legend>
<table width="100%" class="header">
    <tr>
      <td colspan="5"><input name="add" value="Daftar Baru" type="button" onclick="doAjaxCall${formName}('tambahFail')" /></td>
    </tr> 
    <tr class="table_header">
        <td width="3%"><b>Bil.</b></td>
        <td width="20%"><b>No. Permohonan/Fail</b></td>
        <td width="37%"><b>Tajuk Fail</b></td>
        <td width="17%"><b>Negeri</b></td>
        <td width="23%"><b>Status</b></td>      
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
    #set($s=$!senarai.bil)
    #set($a=$!senarai.permohonan.getNamaNegeri())
    <tr>
      <td class="$row">$cnt.</td>
      <td class="$row"><a href="javascript:papar('$!senarai.permohonan.getIdPermohonan()','$!senarai.getIdHtpPermohonan()')" class="style1">$!senarai.permohonan.getNoPermohonan()</a> </td>
      <td class="$row">$!senarai.permohonan.getTujuan()</td>
      <td class="$row">$a</td>
      <td class="$row">$!senarai.statusPermohonan</td>
    </tr>
    #end
    #else
  <tr>
    <td colspan="4" class="row1">Tiada rekod.</td>
  </tr>
    #end
  </table>
</fieldset>

		</td>
	</tr>
</table>	

<input type="hidden" name="txtidPermohonan1" />
<input type="hidden" name="txtidHtpPermohona1" value="$!htpPermohonan.getIdHtpPermohonan()"/>
<input type="hidden" name="command1" />

<script>
function setSelected(tabId,command,mode,tabmode) {
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
}
function findFail(){
	doAjaxCall${formName}("searchFail");
}
function paparTanah(idhtp){
	doAjaxCall${formName}("detailTanah",'idHakmilikUrusan='+idhtp);
}
function deleteTanah(idhtp,id){
	if ( !window.confirm("Adakah Anda Pasti?")) 
	return;
	doAjaxCall${formName}("deleteMaklumatTanah",'idHakmilikUrusan='+idhtp+'&idPermohonan='+id);
}
function papar(id,idhtp){
	doAjaxCall${formName}("detail",'idPermohonan='+id+'&idHtpPermohonan='+idhtp);
}

	function simpanFail(){
		if(document.${formName}.socNegeri.value == "" || document.${formName}.socNegeri.value == 0){
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
		if(document.${formName}.socStatusTanah.value == ""){
			alert('Sila pilih " Sttaus Tanah " terlebih dahulu.');
	  		document.${formName}.socStatusTanah.focus(); 
			return; 
		}
		if(document.${formName}.socTarafKeselamatan.value == ""){
			alert('Sila pilih " Jenis Fail " terlebih dahulu.');
	  		document.${formName}.socTarafKeselamatan.focus(); 
			return; 
		}
		if(document.${formName}.txtTajuk.value == ""){
			alert('Sila masukkan maklumat " Tajuk " terlebih dahulu.');
	  		document.${formName}.txtTajuk.focus(); 
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
function doChangePenjualNegeri(){
		doAjaxCall${formName}("doChangePenjualNegeri");
}
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
	
		doAjaxCall${formName}("simpanMaklumatTanah");

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
function step1(idPermohonan,idhtp){
	doAjaxCall${formName}("detail",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
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
	doAjaxCall${formName}("simpanPemilik");
}

//STEP 4

//STEP 5

function cetakPengesahan(idpermohonan) {

    //var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=PengesahanPembelianOnline&idpermohonan="+idpermohonan;
    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=PengesahanPermohonanOnline&idpermohonan="+idpermohonan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();


}

</script>