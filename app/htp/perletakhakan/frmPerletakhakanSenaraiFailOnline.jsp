<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<p></p><fieldset>
<legend>SENARAI FAIL</legend>
<table width="100%" border="0">
  <tr>
    <td colspan="5" align="left"><input class="stylobutton100" name="cmdTambah" id="cmdTambah" type="button" value="Tambah" onclick="javascript:tambah()"/></td>
  </tr>
  <tr class="table_header">
    <td width="3%" align="center"><b>Bil.</b></td>
    <td width="20%"><b>No Permohonan</b></td>
    <td width="50%"><b>Tajuk Fail</b></td>
    <td width="12%"><b>Negeri</b></td>
    <td width="15%"><b>Status</b></td>
  </tr>
  
 #if ($SenaraiFail.size() > 0) 
 #foreach ($list in $SenaraiFail)  
  #if ($list.bil == '') 
  	#set ($row = 'row1')
  #elseif ($list.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr class="$row">
    <td align="center">$list.bil.</td>
    <td >
    #if($list.noFail!='TIADA')
    	<a href="javascript:viewFailById('$list.idFail','$list.idPermohonan','$list.idHtpPermohonan','$list.idSuburusanStatusFail')"><font color="blue">$list.noFail</font></a>
    #end
    <a href="javascript:viewFailById('$list.idFail','$list.idPermohonan','$list.idHtpPermohonan','$list.idSuburusanStatusFail')"><font color="blue">$list.noPermohonan</font></a>    </td>
    <td >$list.tujuan</td>
    <td >$list.negeri</td>
    <td >$list.keterangan</td>
  </tr>
  #end
  #else
   <tr>
   	<td class="row1" align="center">&nbsp;</td>
    <td class="row1">Tiada Rekod</td>
    <td class="row1">&nbsp;</td>
    <td class="row1" align="center">&nbsp;</td>
    <td class="row1">&nbsp;</td>
   </tr>
  #end
</table>
<input name="txtcarian" type="hidden" value="$!iscarian" >

</fieldset>

<script>
	
	function tambah(){
		//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=tambah";
		//document.${formName}.submit();
		doAjaxCall${formName}("","actionPerletakhakan=tambah");
		
	}
	
	function viewFailById(idA,idB,idC,idE){
		//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=papar&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
		//document.${formName}.submit();
		doAjaxCall${formName}("","actionPerletakhakan=papar&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE);
		
	}
	
	function carian(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=";
		//document.${formName}.submit();
		document.${formName}.txtcarian.value="ya";
		doAjaxCall${formName}("","");
		
	}
	
	function kosongkan(){
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=";
		document.${formName}.txtNoFail.value="";
		document.${formName}.txtTajukFail.value="";
		document.${formName}.socKementerianC.value="";
		document.${formName}.socAgensiC.value="";
		document.${formName}.socNegeriC.value="";
		document.${formName}.socDaerahC.value="";
		document.${formName}.socMukimC.value="";
		document.${formName}.submit();
	}

	function doChangeNegeriCarian(){
		doAjaxCall${formName}("doChangeNegeri","");
	}
	
	function doChangeDaerahCarian(){
		doAjaxCall${formName}("doChangeDaerah","");
	}
	
	function doChangeKementerianCarian(){
		doAjaxCall${formName}("doChangeKementerian","");
	}
	
	function doChangeNegeri(){
		doAjaxCall${formName}("doChangeNegeri","actionPerletakhakan=tambah");
	}
	
	function doChangeNegeriEdit(){
		doAjaxCall${formName}("doChangeNegeri","actionPerletakhakan=kemaskini");
	}

	function doChangeKementerian(){
		doAjaxCall${formName}("doChangeKementerian","actionPerletakhakan=tambah");
	}
	
	function doChangeKementerianEdit(){
		doAjaxCall${formName}("doChangeKementerian","actionPerletakhakan=kemaskini");
	}

	// 01/06/2010 -
	function senaraiDokumenSurat(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}

	//2010/04/09 bertujuan mencetak doket atau Fail
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

	function edit(idA,idB,idC,idE){
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih "Negeri" terlebih dahulu.');
			document.${formName}.socNegeri.focus();
			return;
		}
		if(document.${formName}.socKementerian.value == ""){
			alert('Sila pilih "Kementerian" terlebih dahulu.');
			document.${formName}.socKementerian.focus();
			return;
		}
			if(document.${formName}.socAgensi.value == ""){
			alert('Sila pilih "Agensi" terlebih dahulu.');
			document.${formName}.socAgensi.focus();
			return;
		}
		if(document.${formName}.socSuburusan.value == ""){
			alert('Sila pilih "Urusan" terlebih dahulu.');
			document.${formName}.socSuburusan.focus();
			return;
		}
		if(document.${formName}.txtTajuk.value == ""){
			alert('Sila pilih "Tujuan" terlebih dahulu.');
			document.${formName}.txtTajuk.focus();
			return;
		}
		if(document.${formName}.txtNoFailKJP.value == ""){
			alert('Sila pilih "No Fail KJP" terlebih dahulu.');
			document.${formName}.txtNoFailKJP.focus();
			return;
		}
			if(document.${formName}.txdTarikhSurKJP.value == ""){
			alert('Sila pilih "Tarikh Surat KJP" terlebih dahulu.');
			document.${formName}.txdTarikhSurKJP.focus();
			return;
		}
	
		doAjaxCall${formName}("","actionPerletakhakan=edit&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE);
			
	}

	function sahkan(idFail,idPermohohan){
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=sahkan&idPermohonan="+idPermohohan+"&idFail="+idFail;
		document.${formName}.submit();
		
	}
	
	function batal(){
		//doAjaxCall${formName}("","actionPerletakhakan=papar");	
	}
	
	function maklumatHakmilikBatal(id){
		document.${formName}.mode.value = "";
		doAjaxCall${formName}("","idFail="+id);
	}

	function simpan(){
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih "Negeri" terlebih dahulu.');
			document.${formName}.socNegeri.focus();
			return;
		}
		if(document.${formName}.socKementerian.value == ""){
			alert('Sila pilih "Kementerian" terlebih dahulu.');
			document.${formName}.socKementerian.focus();
			return;
		}
			if(document.${formName}.socAgensi.value == ""){
			alert('Sila pilih "Agensi" terlebih dahulu.');
			document.${formName}.socAgensi.focus();
			return;
		}
		if(document.${formName}.socSuburusan.value == ""){
			alert('Sila pilih "Urusan" terlebih dahulu.');
			document.${formName}.socSuburusan.focus();
			return;
		}
		if(document.${formName}.txtTajuk.value == ""){
			alert('Sila pilih "Tujuan" terlebih dahulu.');
			document.${formName}.txtTajuk.focus();
			return;
		}
		if(document.${formName}.txtNoFailKJP.value == ""){
			alert('Sila pilih "No Fail KJP" terlebih dahulu.');
			document.${formName}.txtNoFailKJP.focus();
			return;
		}
			if(document.${formName}.txdTarikhSurKJP.value == ""){
			alert('Sila pilih "Tarikh Surat KJP" terlebih dahulu.');
			document.${formName}.txdTarikhSurKJP.focus();
			return;
		}
		
		doAjaxCall${formName}("","actionPerletakhakan=simpanBaru");
	
	}
	
	function kemaskini(idA,idB,idC,idE) {
		doAjaxCall${formName}("","actionPerletakhakan=kemaskini&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE);
		
	}
	
	function kembali() {	
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=maklumathakmilik";
		document.${formName}.mode.value = "";
		doAjaxCall${formName}("");
		
	}
	
	function kembali2() {	
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=";
		document.${formName}.mode.value = "";
		doAjaxCall${formName}("");
		
	}

	//Fungsi2 untuk page frmPerletakhakanTabHakmilik.jsp
	function maklumatHakmilik(id){
		//document.${formName}.actionPerletakhakan.value = "papar";
		//document.${formName}.idFail.value = id;
		//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=maklumathakmilik&idFail="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","actionPerletakhakan=maklumathakmilik&idFail="+id);
	}
	
	//Fungsi2 untuk page frmPerletakhakanTabHakmilikOnline.jsp
	function tambahHakmilik(){
		document.${formName}.actionPerletakhakan.value = "maklumathakmilik";
		document.${formName}.mode.value = "newHakmilik";
		//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=papar&mode=newHakmilik";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		//doAjaxCall${formName}("papar");
	}

	function doChangeState(){
		document.${formName}.actionPerletakhakan.value = "maklumathakmilik";
		document.${formName}.mode.value = "doChangeState";
		doAjaxCall${formName}("");
	}
	
	function simpanHakmilik(){
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih "Negeri" terlebih dahulu.');
			document.${formName}.socNegeri.focus();
			return;
		}
		if(document.${formName}.socDaerah.value == ""){
			alert('Sila pilih "Daerah" terlebih dahulu.');
			document.${formName}.socDaerah.focus();
			return;
		}
		if(document.${formName}.socMukim.value == ""){
			alert('Sila pilih "Bandar/Pekan/Mukim" terlebih dahulu.');
			document.${formName}.socMukim.focus();
			return;
		}
		if(document.${formName}.socJenisHakmilik.value == ""){
			alert('Sila pilih "Jenis Hakmilik" terlebih dahulu.');
			document.${formName}.socJenisHakmilik.focus();
			return;
		}
		if(document.${formName}.txtNoHakmilik.value == ""){
			alert('Sila pilih "No Hakmilik" terlebih dahulu.');
			document.${formName}.txtNoHakmilik.focus();
			return;
		}
		if(document.${formName}.txtLot.value == ""){
			alert('Sila pilih "No Lot" terlebih dahulu.');
			document.${formName}.txtLot.focus();
			return;
		}
		if(document.${formName}.socLuas.value == ""){
			alert('Sila pilih "Luas" terlebih dahulu.');
			document.${formName}.socLuas.focus();
			return;
		}
		if(document.${formName}.txtLokasi.value == ""){
			alert('Sila pilih "Lokasi" terlebih dahulu.');
			document.${formName}.txtLokasi.focus();
			return;
		}
		if(document.${formName}.socLot.value == ""){
			alert('Sila pilih "Lot" terlebih dahulu.');
			document.${formName}.socLot.focus();
			return;
		}
		if(document.${formName}.socKategori.value == ""){
			alert('Sila pilih "Kategori" terlebih dahulu.');
			document.${formName}.socKategori.focus();
			return;
		}	
		if(document.${formName}.txtCukaiSemasa.value == ""){
			alert('Sila pilih "Cukai Semasa" terlebih dahulu.');
			document.${formName}.txtCukaiSemasa.focus();
			return;
		}
		
		document.${formName}.mode.value ="viewHakmilik";
		document.${formName}.hitButton.value ="simpanHakmilik";
		doAjaxCall${formName}(""); 
	} 
	
	function paparHakmilik(id){	
		document.${formName}.actionPerletakhakan.value = "maklumathakmilik";
		document.${formName}.mode.value ="viewHakmilik";
		document.${formName}.idHakmilikurusan.value = id;
		doAjaxCall${formName}(""); 
	}
	
	function kemaskiniHakmilik(){
		//document.${formName}.actionPerletakhakan.value = "maklumathakmilik";
		document.${formName}.mode.value ="updateHakmilik";
		document.${formName}.hitButton.value ="simpanUpdateHakmilik";
		doAjaxCall${formName}(""); 
	}
	
	function simpanUpdateHakmilik(){
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih "Negeri" terlebih dahulu.');
			document.${formName}.socNegeri.focus();
			return;
		}
		if(document.${formName}.socDaerah.value == ""){
			alert('Sila pilih "Daerah" terlebih dahulu.');
			document.${formName}.socDaerah.focus();
			return;
		}
		if(document.${formName}.socMukim.value == ""){
			alert('Sila pilih "Bandar/Pekan/Mukim" terlebih dahulu.');
			document.${formName}.socMukim.focus();
			return;
		}
		if(document.${formName}.socJenisHakmilik.value == ""){
			alert('Sila pilih "Jenis Hakmilik" terlebih dahulu.');
			document.${formName}.socJenisHakmilik.focus();
			return;
		}
		if(document.${formName}.txtNoHakmilik.value == ""){
			alert('Sila pilih "No Hakmilik" terlebih dahulu.');
			document.${formName}.txtNoHakmilik.focus();
			return;
		}
		if(document.${formName}.txtLot.value == ""){
			alert('Sila pilih "No Lot" terlebih dahulu.');
			document.${formName}.txtLot.focus();
			return;
		}
		if(document.${formName}.socLuas.value == ""){
			alert('Sila pilih "Luas" terlebih dahulu.');
			document.${formName}.socLuas.focus();
			return;
		}
		if(document.${formName}.txtLokasi.value == ""){
			alert('Sila pilih "Lokasi" terlebih dahulu.');
			document.${formName}.txtLokasi.focus();
			return;
		}
		if(document.${formName}.socLot.value == ""){
			alert('Sila pilih "Lot" terlebih dahulu.');
			document.${formName}.socLot.focus();
			return;
		}
		if(document.${formName}.txtCukaiSemasa.value == ""){
			alert('Sila pilih "Cukai Semasa" terlebih dahulu.');
			document.${formName}.txtCukaiSemasa.focus();
			return;
		}

		if(document.${formName}.socKategori.value == ""){
			alert('Sila pilih "Kategori" terlebih dahulu.');
			document.${formName}.socKategori.focus();
			return;
		}
				
		document.${formName}.mode.value ="viewHakmilik";
		document.${formName}.hitButton.value ="simpanUpdateHakmilik";
		doAjaxCall${formName}("");  
	} 
	
	function batalUpdateHakmilik(){
		document.${formName}.actionPerletakhakan.value = "papar";
		document.${formName}.mode.value = "viewHakmilik";
		doAjaxCall${formName}(""); 
	}
	
	function formatCurrencySemasa(num) { 
		num = num.toString().replace(/\$|\,/g,''); 
		if(isNaN(num)) num = "0"; 
		sign = (num == (num = Math.abs(num)));
	 	num = Math.floor(num*100+0.50000000001); 
	 	cents = num%100; num = Math.floor(num/100).toString();
	  	if(cents<10) cents = "0" + cents; 
	 	 for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		  num = num.substring(0,num.length-(4*i+3))+','+ num.substring(num.length-(4*i+3)); 
	    document.${formName}.txtCukaiSemasa.value = num + '.' + cents; 
	}
	
	//
	function paparSkrinUtama(idA,idB,idC,idE){
		document.${formName}.idFail.value = idA;
		document.${formName}.idPermohonan.value = idB;
		//document.${formName}.mode.value = "viewHakmilik";
		
		//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=papar&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
		//document.${formName}.submit();
		doAjaxCall${formName}("","actionPerletakhakan=papar&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE);
		
	}
	
	//
	function paparSkrin2(idA,idB){
		document.${formName}.idFail.value = idA;
		document.${formName}.idPermohonan.value = idB;
		document.${formName}.actionPerletakhakan.value = "papar";
		//document.${formName}.mode.value = "viewHakmilik";
		
		//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=papar&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}
	
	function kiraLuas(idLuas){
  var jenisLuas = idLuas;
  // KILOMETER PERSEGI
  if(jenisLuas == "1"){
  	var luasK = (document.${formName}.txtLuas1.value);
	var luasH = luasK*100;
  	document.${formName}.Luas.value = luasH;
   }
   else
   //HEKTER
    if(jenisLuas == "2"){
  	var luasH = (document.${formName}.txtLuas1.value);
  	document.${formName}.Luas.value = luasH;
   }
   else
   // METER PERSEGI
   if(jenisLuas == "3"){
  	  var luasM = document.${formName}.txtLuas1.value;
  	  var luasH = (luasM*0.0001);
	  document.${formName}.Luas.value = luasH;
   }
   else
   //EKAR, ROOD, POLE
   if(jenisLuas == "4"){
  	  var luasE = document.${formName}.txtLuas2.value;
	  var luasR = document.${formName}.txtLuas3.value;
	  var luasP = document.${formName}.txtLuas4.value;
	  var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  document.${formName}.Luas.value = luasH;
   }
   else
   //KAKI PERSEGI
   if(jenisLuas == "5"){
  	  var luasAsal = document.${formName}.txtLuas1.value;
	  var luasK = luasAsal*0.0000092;
  	  document.${formName}.Luas.value = luasK;
   }
   else
   //EKAR,DEPA
   if(jenisLuas == "7"){
  	  var luasE = document.${formName}.txtLuas5.value;
	  var luasD = document.${formName}.txtLuas6.value;
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
  	  document.${formName}.Luas.value = luasH;
   }
    else
   //RELONG,JEMBA,KAKI PERSEGI
   if(jenisLuas == "8"){
  	  var luasR = document.${formName}.txtLuas2.value;
	  var luasJ = document.${formName}.txtLuas3.value;
	  var luasK = document.${formName}.txtLuas4.value;
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
  	  document.${formName}.Luas.value = luasH;
   }
}

	//25/10/2010
	function hapusHakmilik(){
		document.${formName}.actionPerletakhakan.value = "maklumathakmilik";
		document.${formName}.mode.value ="hapusHakmilik";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
	}
	
	function doChangeKodLuas(t) {
		var command = 'kemaskinipermohonan';
		var actionPerletakhakan = "maklumathakmilik";
		var mode = t;
		var tabId = 0;
		//var tabmode = t;
		var dochange = "doChangeKodLuas";
		doAjaxCall${formName}(command,'actionPerletakhakan='+actionPerletakhakan+'&mode='+mode+'&tabId='+tabId);

	}

</script>