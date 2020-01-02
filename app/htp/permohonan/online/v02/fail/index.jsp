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
<legend><strong>CARIAN</strong></legend>
<table width="100%" border="0">
  <tr>
    <td width="29%"><div align="right">Negeri</div></td>
    <td width="1%">:</td>
    <td width="70%">$socNegeri</td>
  </tr>
			      	<tr>
				        <td align="right"><div align="right">
				        	#if ($idnegeri == '13' )
				        		Bahagian
				        	#else
				        		Daerah
				        	#end
				        	</div></td>
				        <td><div align="center">:</div></td>
				        <td>$socDaerah</td>
			      	</tr>
  <tr>
    <td width="29%"><div align="right">
      <div align="right">Bandar/Pekan/Mukim</div>
    </div></td>
    <td width="1%">:</td>
    <td width="70%">$socMukim</td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Kementerian</div></td>
    <td width="1%">:</td>
    <td width="70%">$socKementerian</td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Agensi</div></td>
    <td width="1%">:</td>
    <td width="70%">$socAgensi</td>
  </tr>
    <tr>
    <td width="29%"><div align="right">No. Fail</div></td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNoFail" type="text" id="txtNoFail" size="43" value="$!txtNoFail"></td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Tajuk Fail</div></td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtTajukFail" type="text" id="txtTajukFail" size="43" value="$!txtTajukCarian"></td>
  </tr>
    <tr>
    <td width="29%"><div align="right">Urusan</div></td>
    <td width="1%">:</td>
    <td width="70%">$socUrusan</td>
  </tr>

  <tr>
    <td width="29%"><div align="right">Tarikh Daftar Fail</div></td>
    <td width="1%">:</td>
    <td width="70%">
    	<input $readOnly class="$disabled" name="txdtarikhdaftarfail" type="text" onblur="check_date(this)" value="$!tarikhBukaFail" size="11" maxlength="10"/>
		<a href="javascript:displayDatePicker('txdtarikhdaftarfail',false,'dmy');"> <img src="../img/calendar.gif" alt="" border="0"/></a>
					          
    </td>
  </tr>
  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%">
    	<input class="stylobutton100"  name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carianFail()" />
      	<input class="stylobutton100"  name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onclick="javascript:cancel()" /></td>
  </tr>
  <input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch">
  
</table>
</fieldset>
		</td>
	<tr/>
	
	<tr>
		<td>
			

<fieldset>
<legend><strong>SENARAI FAIL</strong></legend>
<input class="stylobutton100" value="Tambah" type="button" onClick="javascript:tambahPermohonan()">

#parse("app/utils/record_paging.jsp")
<table width="100%" border="0">
  <tr class="table_header">
    <td width="5%" align="center"><b>Bil.</b></td>
    <td width="14%"><b>No. Fail</b></td>
    <td width="45%"><b>Tajuk Fail</b></td>
    <td width="14%"><b>Negeri</b></td>
    <td width="12%" align="center"><b>Tarikh Daftar Fail</b></td>
    <td width="10%" align="center"><b>Status</b></td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $SenaraiFail )
  				#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
  <tr class="$row">
    <td align="center">$!senarai.bil.</td>
    <td ><a href="javascript:viewMaklumatPermohonan('$!senarai.id')" class="style1">$!senarai.no </a><a href="javascript:viewFailById('$list.idFail','$list.idPermohonan','$list.idHtpPermohonan','$list.idSuburusanStatusFail')"></a></td>
    <td >$!senarai.tujuan</td>
    <td >$!senarai.negeri</td>
    <td align="center">$!senarai.tarikhDaftar</td>
    <td align="center">$!senarai.keterangan</td>
  </tr>
  #end
  #if ($cnt == 0)
   <tr>
   	<td class="row1" align="center">&nbsp;</td>
    <td class="row1">Tiada Rekod</td>
    <td class="row1">&nbsp;</td>
    <td class="row1" align="center">&nbsp;</td>
    <td class="row1">&nbsp;</td>
   </tr>
  #end
</table>

</fieldset>
		</td>
	<tr/>
</table>

<script>

	function doChangeKodLuas(t) {
		var command = 'kemaskinipermohonan';
		var mode = 'MakAsasTanah';
		var tabId = 0;
		var tabmode = t;
		var dochange = "doChangeKodLuas";
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&doChange='+dochange);
	
		//doAjaxCall${formName}("kemaskinipermohonan","mode=MakAsasTanah&doChange=KodLuas");
	}

function DateAdd(ItemType, DateToWorkOn, ValueToBeAdded)
{
    switch (ItemType)
    {
        //date portion        
        case 'd': //add days
            DateToWorkOn.setDate(DateToWorkOn.getDate() + ValueToBeAdded)
            break;
        case 'm': //add months
            DateToWorkOn.setMonth(DateToWorkOn.getMonth() + ValueToBeAdded)
            break;
        case 'y': //add years
            DateToWorkOn.setYear(DateToWorkOn.getFullYear() + ValueToBeAdded)
            break;
        //time portion        
        case 'h': //add days
            DateToWorkOn.setHours(DateToWorkOn.getHours() + ValueToBeAdded)
            break;
        case 'n': //add minutes
            DateToWorkOn.setMinutes(DateToWorkOn.getMinutes() + ValueToBeAdded)
            break;
        case 's': //add seconds
            DateToWorkOn.setSeconds(DateToWorkOn.getSeconds() + ValueToBeAdded)
            break;
    }
    return DateToWorkOn;
}

function getDate(strDate) {
	return new Date(strDate.substring(6,10),strDate.substring(3,5),strDate.substring(0,2));
}

function convertDate(myDate) {
	var day = myDate.getDate();
	var month = myDate.getMonth()+1;
	var year = myDate.getFullYear();
	var myTarikh = "";
	if(month>=10){
		if(day>=10){
			myTarikh = day + "/" + month + "/" + year;	
		}else{
			myTarikh = "0"+ day + "/" + month + "/" + year;	
		}

	}else{
		if(day>=10){
			myTarikh = day + "/0" + month + "/" + year;	
		}else{
			myTarikh = "0"+ day + "/0" + month + "/" + year;	
		}
	}
	return myTarikh;
}

	function doCalculateTarikh() {
		var TB  = document.${formName}.txtTarikhNotis5A.value;
		var date = getDate(TB);
	
	    //var date1 = DateAdd('m',date,2);
	    // Dikemskini pada 13/09/2012
	    var date1 = DateAdd('m',date,6);
	   	document.${formName}.txtTarikhLuputPertama.value = convertDate(date1) ;
	
	   	var date2 = DateAdd('m',date1,3);
	   	document.${formName}.txtTarikhLuputNotisKedua.value = convertDate(date2) ;
	    	
	   	var date3 = DateAdd('m',date2,3);
	   	document.${formName}.txtTarikhLuputNotisKetiga.value = convertDate(date3) ;
	
	}

function cetakKulitFail(idpermohonan) {

    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=NoFailTajukFail&idpermohonan="+idpermohonan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();


}

function cetakDoket(idpermohonan) {

    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=rptNoFailTajukFail&idpermohonan="+idpermohonan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();


}

	function cetakPermohonan(idpermohonan) {	
	    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=HTPPermohonanTanahMilikTanahRizabMaklumat&idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		
	}

function cancel() {
	doAjaxCall${formName}("","mode=cancel");
}

function doChangeNegeriX() {
	doAjaxCall${formName}("","mode=changeNegeri");
}

function doChangeDaerahX() {
	doAjaxCall${formName}("","mode=changeDaerah");
}

function doChangeKementerianX() {
	doAjaxCall${formName}("","mode=changeKementerian");
}

function LokasiTanah(tabId,tabmode,command,mode,idhakmilikurusan){
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+idhakmilikurusan);
}
	
	function DetailTanah(tabId,tabmode,command,mode,button,idhakmilikurusan){
		document.${formName}.idhakmilikurusan.value=idhakmilikurusan; 
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button+'&idhakmilikurusan='+idhakmilikurusan);
	}

	function TambahAsasTanah(tabId,tabmode,command,mode){
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	}

function carianFail(){
	var command = 'terimapohoncarian';
	doAjaxCall${formName}(command);	
}

	// Skrin index
	function viewMaklumatPermohonan(id){
		var mode = 'viewMaklumatPermohonan';
		//doAjaxCall${formName}("viewMaklumatPermohonan","mode="+mode+"&idfail="+id+"&pagemode=0");
		doAjaxCall${formName}("maklumatpermohonan","mode="+mode+"&idfail="+id+"&pagemode=0&hittButton=view");
	
	}

	function kemaskiniterimapermohonan(id){
		var mode = 'MakAsasTanah';
			//doAjaxCall${formName}("kemaskinipermohonan","mode="+mode+"&idfail="+id+"&pagemode=0");
		//doAjaxCall${formName}("maklumatasastanah","mode="+mode+"&idfail="+id+"&pagemode=0");
		doAjaxCall${formName}("maklumatanah","mode="+mode+"&idfail="+id+"&pagemode=0");
		
	}

function xkemaskiniterimapermohonan(id,ip){
	var mode = 'MakAsasTanah';
	doAjaxCall${formName}("kemaskinipermohonan","mode="+mode+"&idfail="+id+"&pagemode=0&idpermohonan="+ip);
}


function KemaskiniInfo(id){
	doAjaxCall${formName}("kemaskinipermohonan","idfail="+id+"&pagemode=1");
}
	
function tambahPermohonan(){
	doAjaxCall${formName}("pohonfailbaru");
}

function selectTab2(tabId,command,mode,tabmode){
	
	//if ( document.${formName}.socStatustanah.value == "" ) { 
	//	  		alert('Sila pilih Status Tanah terlebih dahulu.');
	//	  		document.${formName}.socStatustanah.focus(); 
	//	  		return; 
	//	  	}
		
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	}
	
	function selectTab(tabId,command,mode,tabmode,id){
		document.${formName}.detailMode.value="View"; 
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&idpermohonan='+id);

	}

function selectTab2(tabId2,command,mode,button,id){
	doAjaxCall${formName}(command,'mode='+mode+'&tabId2='+tabId2+'&button='+button+'&idpermohonan='+id);
}

function selectTab3(tabId3,command,mode,button,id){
	//alert(selectTab3);
	//document.${formName}.selectedTab.value="4"; 
	doAjaxCall${formName}(command,'selectedTab=4&mode='+mode+'&tabId3='+tabId3+'&button='+button+'&idpermohonan='+id);
}

function KembaliAsas(tabId,command,mode,tabmode){
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
}

	function SimpanMaklumatAsasTanah(){
	
		if ( document.${formName}.socdaerah2.value == "" ) { 
			alert('Sila pilih daerah terlebih dahulu.');
			document.${formName}.socdaerah2.focus(); 
			return; 
		}
		if ( document.${formName}.socMukim2.value == "" ) { 
			alert('Sila pilih mukim terlebih dahulu.');
			document.${formName}.socMukim2.focus(); 
			return; 
		}
		if ( document.${formName}.socLot.value == "" ) { 
			alert('Sila masukkan kod lot terlebih dahulu.');
			document.${formName}.socLot.focus(); 
			return; 
		}
		if ( document.${formName}.txtNoLot.value == "" ) { 
			alert('Sila masukkan nombor lot terlebih dahulu.');
			document.${formName}.txtNoLot.focus(); 
			return; 
		}
		/*if ( document.${formName}.noSyit.value == "" ) { 
			alert('Sila masukkan no syit terlebih dahulu.');
			document.${formName}.noSyit.focus(); 
			return; 
		}
		
		if ( document.${formName}.kodluas.value == "" ) { 
			alert('Sila pilih kod luas terlebih dahulu.');
			document.${formName}.kodluas.focus(); 
			return; 
		}	
		*/
		/*
		if ( document.${formName}.Luas.value == "" ) { 
			alert('Sila masukkan luas terlebih dahulu.');
			document.${formName}.Luas.focus(); 
			return; 
		}
		
		if ( document.${formName}.Lokasi.value == "" ) { 
			alert('Sila masukkan lokasi terlebih dahulu.');
			document.${formName}.Lokasi.focus(); 
			return; 
		}*/
		
		if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
			if(document.${formName}.socLuas.value == "4"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"E,"+document.${formName}.txtLuas3.value+"R,"+document.${formName}.txtLuas4.value+"P";
			}else if(document.${formName}.socLuas.value == "7"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas5.value +"E,"+document.${formName}.txtLuas6.value+"D";
			}else if(document.${formName}.socLuas.value == "8"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"R,"+document.${formName}.txtLuas3.value+"J,"+document.${formName}.txtLuas4.value+"K";
			}
		 }else{
		 	if(document.${formName}.socLuas.value == "1"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"KM";
		 	}else if(document.${formName}.socLuas.value == "2"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"H";
		 	}else if(document.${formName}.socLuas.value == "3"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"MP";
		 	}else{
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value;
		 	}
		 }
		
		var command = 'kemaskinipermohonan';
		var mode = 'MakAsasTanah';
		var button = "SimpanMaklumatAsasTanah";
		var tabId = 0;
		var tabmode = 1;
		doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
	}

function doKemaskiniMaklumatAsasTanah(){

	if ( document.${formName}.socdaerah2.value == "" ) { 
		alert('Sila pilih daerah terlebih dahulu.');
		document.${formName}.socdaerah2.focus(); 
		return; 
	}
	if ( document.${formName}.socMukim2.value == "" ) { 
		alert('Sila pilih mukim terlebih dahulu.');
		document.${formName}.socMukim2.focus(); 
		return; 
	}
	if ( document.${formName}.socLot.value == "" ) { 
		alert('Sila masukkan kod lot terlebih dahulu.');
		document.${formName}.socLot.focus(); 
		return; 
	}
	if ( document.${formName}.txtNoLot.value == "" ) { 
		alert('Sila masukkan nombor lot terlebih dahulu.');
		document.${formName}.txtNoLot.focus(); 
		return; 
	}
	/*if ( document.${formName}.noSyit.value == "" ) { 
		alert('Sila masukkan no syit terlebih dahulu.');
		document.${formName}.noSyit.focus(); 
		return; 
	}
	
	if ( document.${formName}.kodluas.value == "" ) { 
		alert('Sila pilih kod luas terlebih dahulu.');
		document.${formName}.kodluas.focus(); 
		return; 
	}	
	*/
	/*
	if ( document.${formName}.Luas.value == "" ) { 
		alert('Sila masukkan luas terlebih dahulu.');
		document.${formName}.Luas.focus(); 
		return; 
	}
	
	if ( document.${formName}.Lokasi.value == "" ) { 
		alert('Sila masukkan lokasi terlebih dahulu.');
		document.${formName}.Lokasi.focus(); 
		return; 
	}*/
	
	if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
		if(document.${formName}.socLuas.value == "4"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"E,"+document.${formName}.txtLuas3.value+"R,"+document.${formName}.txtLuas4.value+"P";
		}else if(document.${formName}.socLuas.value == "7"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas5.value +"E,"+document.${formName}.txtLuas6.value+"D";
		}else if(document.${formName}.socLuas.value == "8"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"R,"+document.${formName}.txtLuas3.value+"J,"+document.${formName}.txtLuas4.value+"K";
		}
	 }else{
	 	if(document.${formName}.socLuas.value == "1"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"KM";
	 	}else if(document.${formName}.socLuas.value == "2"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"H";
	 	}else if(document.${formName}.socLuas.value == "3"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"MP";
	 	}else{
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value;
	 	}
	 }
	
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var button = "doKemaskiniMaklumatAsasTanah";
	var tabId = 0;
	var tabmode = 1;
	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
}


function SimpanDetailLot(){
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var button = "SimpanDetailLot";
	var tabId = 0;
	var tabmode = 3;
	KemaskiniDetailLot
	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
}

function SimpanCharting(){
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var button = "SimpanCharting";
	var tabId = 0;
	var tabmode = 3;
	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
}

function doKemaskiniCharting(t){
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var button = "doKemaskiniCharting";
	var tabId = 0;
	var tabmode = t;
	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
}

function KemaskiniDetailLot(){
	
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var button = 'KemaskiniDetailLot';
	var tabId = 0;
	var tabmode = 3;
	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
}

function doKemaskiniDetailLot(t){
	
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var button = 'doKemaskiniDetailLot';
	var tabId = 0;
	var tabmode = t;
	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
}

function doViewForKemaskiniDetailLot(t){
	
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var button = 'doViewForKemaskiniDetailLot';
	var tabId = 0;
	var tabmode = t;
	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
}
function doViewForKemaskiniCharting(t){
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var button = 'doViewForKemaskiniCharting';
	var tabId = 0;
	var tabmode = t;
	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
}


function doKemaskiniBuktiBayaran(t) {
	var command = 'kemaskinipermohonan';
	var mode = 'Pembayaran';
	var button = 'KemaskiniPembayaran';
	var tabId = 4;
	var tabmode = 1;
	doAjaxCall${formName}(command,'button='+button+'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
}

	function TambahPembayaran(){
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var button = 'TambahPembayaran';
		var tabId = 4;
		var tabmode = 1;
		doAjaxCall${formName}(command,'button='+button+'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	
	}

	function doViewForKemaskiniBuktiBayaran(t){
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var button = 'doViewForKemaskiniBuktiBayaran';
		var tabId = 4;
		var tabmode = t;
		doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
	}

	// SKRIN KEPUTUSAN
	function TambahKeputusan(){
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var button = 'TambahKeputusan';
		var tabId = 4;
		var tabmode = 1;
		doAjaxCall${formName}(command,'tabId3=2&button='+button+'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	
	}
	
	function doKemaskiniKeputusan(t){
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var button = "KemaskiniKeputusan";
		var tabId = 4;
		var tabmode = t;
		doAjaxCall${formName}(command,'tabId3=2&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);

	}
	
	function doViewForKemaskiniKeputusan(t){
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var button = 'doViewForKemaskiniKeputusan';
		var tabId = 4;
		var tabmode = t;
		doAjaxCall${formName}(command,'tabId3=2&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
	}

	// Dibuat oleh Mohamad Rosli (11/01/2013) 
	// Hapus maklumat keputusan permohonan
	function hapusKeputusan(t){
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var button = 'hapuskeputusan';
		var tabId = 4;
		var tabmode = t;
		doAjaxCall${formName}(command,'tabId3=2&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);

	}
	
function UpdateDetailLot(id){
	
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var button = 'UpdateDetailLot';
	var tabId = 0;
	var tabmode = 3;
	
	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+id);
}
function KemaskiniPelan(){
	
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var button = 'KemaskiniPelan';
	var tabId = 0;
	var tabmode = 3;
	
	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
}
/*
AZAM REMARK
function SimpanCharting(){
	
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var button = 'SimpanPelan';
	var tabId = 0;
	var tabmode = 3;
	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
}
*/

	function KemaskiniAsasTanah(tabId,tabmode,command,mode,idhakmilikurusan){
		var button = "KemaskiniMaklumatInfo";
		document.${formName}.idhakmilikurusan.value = idhakmilikurusan;
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+idhakmilikurusan+'&button='+button);
	
	}
	
	function hapusMaklumatAsasTanah(tabId,tabmode,command,mode){
		if ( !window.confirm("Anda Pasti?") ) return;
	
		var button = "deletemaklumatanah";
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button);
	
	}
		
	
	function UpdateMaklumatAsasTanah(tabId,tabmode,command,mode,idhakmilikurusan){
		var button = "UpdateMaklumatAsasTanah";
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+idhakmilikurusan+'&button='+button);
	
	}

	function SimpanLokasi(id){	
		var command = 'kemaskinipermohonan';
		var mode = 'LokasiTanah';
		//var button = "SimpanLokasiTanah";'&button='+button+
		var tabId = 2;
		var tabmode = 1;
		var id = id;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+id);
	
	}
	
	function SimpanLokasi2(id){	
		var command = 'kemaskinipermohonan';
		var mode = 'LokasiTanah';
		var button = 'SimpanLokasiTanah';
		//var button2 = 'simpanLokasitanah2';'&button2='+button2+
		var tabId = 2;
		var tabmode = 0;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+id);
	
	}
	
	function SeterusLakaran(tabId,tabmode,command,mode,idhakmilikurusan){//
		
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmUploadFile&nextAction=tambahDetailImej&idHakmilik="+53;
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+idhakmilikurusan);
	}

function SimpanLakaranPelan(id){
	//alert(id);
		var command = 'kemaskinipermohonan';
		var mode = 'LakaranPelan';
		//var button = "SimpanLokasiTanah";'&button='+button+
		var tabId = 3;
		var tabmode = 1;
		var id = id;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+id);
	
	}
	
function SimpanPohonPelan(id){
	
		var command = 'kemaskinipermohonan';
		var mode = 'LakaranPelan';
		var button = 'SimpanLakaranPelan';
		//var button2 = 'simpanLokasitanah2';'&button2='+button2+
		var tabId = 3;
		var tabmode = 1;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+id);
	
}
function SimpanPembayaran(){
	if ( document.${formName}.txtBayaranProses.value == "" ) { 
		alert('Sila masukkan bayaran proses terlebih dahulu.');
		document.${formName}.txtBayaranProses.focus(); 
		return; 
	}
	if ( document.${formName}.txtTarikhResit.value == "" ) { 
		alert('Sila masukkan tarikh resit terlebih dahulu.');
		document.${formName}.txtTarikhResit.focus(); 
		return; 
	}
	if ( document.${formName}.txtNoBaucerCekDraft.value == "" ) { 
		alert('Sila masukkan No Baucer/Cek/Bank Draft terlebih dahulu.');
		document.${formName}.txtNoBaucerCekDraft.focus(); 
		return; 
	}
	if ( document.${formName}.txtTarikhBaucerCek.value == "" ) { 
		alert('Sila masukkan Tarikh Baucer / Cek terlebih dahulu.');
		document.${formName}.txtTarikhBaucerCek.focus(); 
		return; 
	}
	if ( document.${formName}.txttempatBayaran.value == "" ) { 
		alert('Sila masukkan bayaran proses terlebih dahulu.');
		document.${formName}.txttempatBayaran.focus(); 
		return; 
	}	
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var button = 'SimpanPembayaran';
		//var button2 = 'simpanLokasitanah2';'&button2='+button2+
		var tabId = 4;
		var tabmode = 1;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);//+'&idhakmilikurusan='+id

	}
function KemaskiniPembayaran(){
	
	var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var button = 'KemaskiniPembayaran';
		//var button2 = 'simpanLokasitanah2';'&button2='+button2+
		var tabId = 4;
		var tabmode = 0;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode)
	
	}
function UpdatePembayaran(){
	
	
	if ( document.${formName}.txtBayaranProses.value == "" ) { 
		alert('Sila masukkan bayaran proses terlebih dahulu.');
		document.${formName}.txtBayaranProses.focus(); 
		return; 
	}
	if ( document.${formName}.txtTarikhResit.value == "" ) { 
		alert('Sila masukkan tarikh resit terlebih dahulu.');
		document.${formName}.txtTarikhResit.focus(); 
		return; 
	}
	if ( document.${formName}.txtNoBaucerCekDraft.value == "" ) { 
		alert('Sila masukkan No Baucer/Cek/Bank Draft terlebih dahulu.');
		document.${formName}.txtNoBaucerCekDraft.focus(); 
		return; 
	}
	if ( document.${formName}.txtTarikhBaucerCek.value == "" ) { 
		alert('Sila masukkan Tarikh Baucer / Cek terlebih dahulu.');
		document.${formName}.txtTarikhBaucerCek.focus(); 
		return; 
	}
	if ( document.${formName}.txttempatBayaran.value == "" ) { 
		alert('Sila masukkan penerima bayaranterlebih dahulu.');
		document.${formName}.txttempatBayaran.focus(); 
		return; 
	}
	
	var command = 'kemaskinipermohonan';
	var mode = 'Pembayaran';
	var button = 'UpdatePembayaran';
	//var button2 = 'simpanLokasitanah2';'&button2='+button2+
	var tabId = 4;
	var tabmode = 0;

	doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode)
	
}



function SimpanKeputusanPTD(){

		//alert(document.${formName}.PilihKeputusan.value);
		if ( document.${formName}.PilihKeputusan.value == "00" ) { 
			alert('Sila masukkan status keputusan terlebih dahulu.');
			document.${formName}.PilihKeputusan.focus(); 
			return; 
		}

		if ( document.${formName}.txtTarikhKeputusan.value == "" ) { 
			alert('Sila masukkan tarikh keputusan terlebih dahulu.');
			document.${formName}.txtTarikhKeputusan.focus(); 
			return; 
		}
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var button = 'SimpanKeputusanPTD';
		//var button2 = 'simpanLokasitanah2';'&button2='+button2+
		var tabId = 4;
		var tabmode = 0;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);//+'&idhakmilikurusan='+id
	
	}	
	function KemaskiniKeputusanPTD(){
	
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var button = 'KemaskiniKeputusanPTD';
		//var button2 = 'simpanLokasitanah2';'&button2='+button2+
		var tabId = 4;
		var tabmode = 0;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);//+'&idhakmilikurusan='+id
	
	}	
	
function doKemaskini(id){
		//alert(id)
		var command = 'kemaskinipermohonan';
		var mode = 'LokasiTanah';
		var button = 'doKemaskini';
		//var button2 = 'simpanLokasitanah2';'&button2='+button2+ +'&idhakmilikurusan='+id
		var tabId = 2;
		var tabmode = 0;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+id);
		}
		
function SimpanUpdate(id){
		//alert(id)
		var command = 'kemaskinipermohonan';
		var mode = 'LokasiTanah';
		var button = 'SimpanUpdate';
		//var button2 = 'simpanLokasitanah2';'&button2='+button2+ +'&idhakmilikurusan='+id
		var tabId = 2;
		var tabmode = 0;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+id);
}

	//frmNotisSenarai
	function TambahNotis(){
		var mode = 'Notis5A';
		//var command = 'kemaskinipermohonan';
		var command = mode;
		var button = 'TambahNotis';
		var tabId = 5;
		var tabmode = 1;
		doAjaxCall${formName}(command,'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button);
		
	}
	
	function SimpanNotis(){
		if ( document.${formName}.txtPremium.value == "" ) { 
			alert('Sila Masukan Jumlah Premium terlebih dahulu.');
			document.${formName}.txtPremium.focus(); 
			return; 
		}
		var mode = 'Notis5A';
		//var command = 'kemaskinipermohonan';
		var command = mode;
		var button = 'SimpanNotis';
		var tabId = 5;
		var tabmode = 0;		
		doAjaxCall${formName}(command,'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button);
		
	}
	
	
	
	function KemaskiniNotis(){
		
		/*if ( document.${formName}.txtTarikhNotis5A.value == "" ) { 
			alert('Sila Masukan Tarikh Notis 5A terlebih dahulu.');
		  		document.${formName}.txtTarikhNotis5A.focus(); 
		  		return; 
		}
	  	if ( document.${formName}.txtCukaiTahunPertama.value == "" ) { 
			alert('Sila Masukan Cukai Tahun Pertama terlebih dahulu.');
			document.${formName}.txtCukaiTahunPertama.focus(); 
			return; 
		}*/
		if ( document.${formName}.txtPremium.value == "" ) { 
			alert('Sila Masukan Jumlah Premium terlebih dahulu.');
			document.${formName}.txtPremium.focus(); 
			return; 
		}
		/*if ( document.${formName}.txtBayarUkur.value == "" ) { 
	  		alert('Sila Masukan Bayaran Ukur terlebih dahulu.');
	  		document.${formName}.txtBayarUkur.focus(); 
	  		return; 
	  	}
	  	if ( document.${formName}.txtTandaSempadan.value == "" ) { 
			alert('Sila Masukan Bayaran Tanda Sempadan terlebih dahulu.');
			document.${formName}.txtTandaSempadan.focus(); 
			return; 
		}
		if ( document.${formName}.txtPendaftaranHakmilik.value == "" ) { 
			alert('Sila Masukan Jumlah Bayaran Pendaftaran terlebih dahulu.');
			document.${formName}.txtPendaftaranHakmilik.focus(); 
			return; 
		} */
			/*if ( document.${formName}.txtBayaranNotisF.value == "" ) { 
				alert('Sila Masukan  Bayaran Notis 5F terlebih dahulu.');
				document.${formName}.txtBayaranNotisF.focus(); 
				return; 
			}*/
		  	
			//var command = 'kemaskinipermohonan';
			var mode = 'Notis5A';
			var command = mode;
			var button = 'KemaskiniNotis';
			var tabId = 5;
			var tabmode = 0;
		
			doAjaxCall${formName}(command,'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button);
		
		}	
	
	function Notis5AView(id){
		//var command = 'kemaskinipermohonan';
		var command = 'Notis5A';
		var mode = 'Notis5A';
		var button = 'ViewNotis';
		var tabId = 5;
		var tabmode = 2;
		doAjaxCall${formName}(command,'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button+'&idNotis='+id);
	
	}
	
function KemaskiniN5PaparNotis(id){
		var command = 'kemaskinipermohonan';
		var mode = 'Notis5A';
		var button = 'KemaskiniN5PaparNotis';
		//var button2 = 'simpanLokasitanah2';'&button2='+button2+'&button='+button+
		var tabId = 5;
		var tabmode = 2;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button+'&idNotis='+id);
	}
function UpdateN5PaparNotis(id){
		var command = 'kemaskinipermohonan';
		var mode = 'Notis5A';
		var button = 'UpdateN5PaparNotis';
		//var button2 = 'simpanLokasitanah2';'&button2='+button2+'&button='+button+
		var tabId = 5;
		var tabmode = 2;
	
		doAjaxCall${formName}(command,'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button+'&idNotis='+id);
	}
	
	function BayaranNotis(id){
		var mode = 'Notis5A';
		//var command = 'kemaskinipermohonan';
		var command = mode;
		var button = 'BuktiBayaranNotis';
		//var button2 = 'simpanLokasitanah2';'&button2='+button2+'&button='+button+ +'&idNotis='+id
		var tabId = 5;
		var tabmode = 2;
		doAjaxCall${formName}(command,'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button+'&idNotis='+id);
	
	}

function BayaranNotisHapus(id){
	if ( !window.confirm("Adakah Anda Pasti?")) return;

		var command = 'kemaskinipermohonan';
		var mode = 'Notis5A';
		var button = 'BayaranNotisHapus';
		var tabId = 5;
		var tabmode = 2;
		doAjaxCall${formName}(command,'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button+'&idBayaran='+id);

}	

function doChangeMukimMAT(){
	
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var tabId = 0;
	var tabmode = 1;
	var dochange = "doChangeMukimMAT";
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&doChange='+dochange);
}

function doChangeMukimMATkemaskini(id){
	
	var command = 'kemaskinipermohonan';
	var mode = 'MakAsasTanah';
	var tabId = 0;
	var tabmode = 2;
	var button = 'KemaskiniMaklumatInfo';
	var dochange = "doChangeMukimMATkemaskini";
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&doChange='+dochange+'&button='+button+'&idhakmilikurusan='+id);
}


function doChangeKementerian() {
	//doAjaxCall${formName}("doChangeKementerian","action2=doChangeKementerian");
	document.${formName}.cmdSimpan.value = "doChangeKementerian";
	doAjaxCall${formName}("pohonfailbaru");
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
		/*  alert("MASUK");*/
		if ( document.${formName}.socNegeri.value == "" ) { 
  			alert('Sila pilih negeri terlebih dahulu.');
  			document.${formName}.socNegeri.focus(); 
  			return; 
  		}
	
		if ( document.${formName}.socDaerah.value == "" ) { 
			alert('Sila pilih daerah terlebih dahulu.');
			document.${formName}.socDaerah.focus(); 
			return; 
		}
	 
		if ( document.${formName}.socKementerian.value == "" ) { 
	  		alert('Sila pilih Kementerian terlebih dahulu.');
	  		document.${formName}.socKementerian.focus(); 
	  		return; 
	  	}
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


	  	/*	if ( document.${formName}.txtnoFailUPT.value == "" ) { 
	  		alert('Sila pilih No Fail UPT terlebih dahulu.');
	  		document.${formName}.txtnoFailUPT.focus(); 
	  		return; 
	  	}
	  
	  	if ( document.${formName}.txtnoFailLain.value == "" ) { 
	  		alert('Sila pilih No Fail Lain terlebih dahulu.');
	  		document.${formName}.txtnoFailLain.focus(); 
	  		return; 
	  	}	
	  	*/
	  	if(document.${formName}.socUrusan.value == "1"){
	  	
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
	  		
		  	if ( document.${formName}.socStatustanah.value == "" ) { 
		  		alert('Sila pilih Status Tanah terlebih dahulu.');
		  		document.${formName}.socStatustanah.focus(); 
		  		return; 
		  	}
	  	}
		if ( document.${formName}.socjenisFail.value == "" ) { 
	  		alert('Sila pilih Jenis Fail terlebih dahulu.');
	  		document.${formName}.socjenisFail.focus(); 
	  		return; 
	  	}
	  	if ( document.${formName}.txtTajuk.value == "" ) { 
	  		alert('Sila pilih Tajuk terlebih dahulu.');
	  		document.${formName}.txtTajuk.focus(); 
	  		return; 
	  	}
	  	
	
		var mode = 'MakAsasTanah';
		doAjaxCall${formName}("kemaskinipermohonan","mode="+mode+"&pagemode=0&hittButton=Simpan");
		document.${formName}.cmdSimpan.value = i;

	}

	//Skrin frmMaklumatFail, frmMaklumatFailEdit
	function failKemaskini(){
		//doAjaxCall${formName}("kemaskinipermohonan","mode=MakAsasTanah&pagemode=0&hittButton=kemaskini");
		// 20121006
		doAjaxCall${formName}("maklumatpermohonan","mode=MakAsasTanah&pagemode=0&hittButton=kemaskini");
	
	}
	
	function failKemaskiniSimpan(){
		//doAjaxCall${formName}("kemaskinipermohonan","mode=MakAsasTanah&pagemode=0&hittButton=kemaskinisimpan");
		// 20121006
		doAjaxCall${formName}("maklumatpermohonan","mode=MakAsasTanah&pagemode=0&hittButton=kemaskinisimpan");
	
	}

function Batal(){
	doAjaxCall${formName}("");
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}

	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}

function calculate(){
	var Cukai1 = document.${formname}.txtCukaiTahunPertama.value * 1;
	
	//removed
	//var BayaranNotis = document.${formname}.txtBayaranNotis.value * 1;
	
	var BayaranNotisF = document.${formname}.txtBayaranNotisF.value * 1;
	var BayaranFail = document.${formname}.txtBayaranFail.value * 1;
	var Premium = document.${formname}.txtPremium.value * 1;
	var phakmilik = document.${formname}.txtPendaftaranHakmilik.value * 1;
	var bayarukur = document.${formname}.txtBayarUkur.value * 1;
	var bayarperenggan = document.${formname}.txtBayaranPerenggan.value * 1;
	var tandasempadan = document.${formname}.txtTandaSempadan.value * 1;
	var lain = document.${formname}.txtBayaranLain.value * 1;
	var lain2 = document.${formname}.txtBayaranLain2.value * 1;
	var lain3 = document.${formname}.txtBayaranLain3.value * 1;
	var RayuanPremium = document.${formname}.txtRayuanPremium.value * 1;
	
	//var Lebihan = document.${formname}.txtLebihan.value * 1;
	var jumBayaran1 = bayarukur + bayarperenggan + tandasempadan + 
			  lain + phakmilik + Cukai1 + 
			  BayaranNotisF + Premium + BayaranFail + lain2 + lain3;
	var jumBayaran = jumBayaran1 - RayuanPremium;

	document.${formname}.txtJumBayaran.value = jumBayaran.toFixed(2);
}
function simpanDetailImej(id){
if ( !window.confirm("Adakah Anda Pasti?")) return;
	var perihal = document.${formName}.txtPerihalImej.value ;
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmUploadFile&command=kemaskinipermohonan&mode=LakaranPelan&nextAction=tambahDetailImej&idHakmilik="+id+"&txtPerihalImej="+perihal;
	//document.${formName}.nextAction = "?_portal_module=ekptg.view.ppt.FrmUploadFile&nextAction=tambahDetailImej&idHakmilik="+53;
	//doAjaxCall${formName}("PendaftaranImej","nextAction=tambahDetailImej&idHakmilik="+53);
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();
}

function doChanges() {
	//document.${formName}.cmdSimpan.value = "doChangeSubUrusan";
	doAjaxCall${formName}("pohonfailbaru","mode=doChanges");
}

	function doChanges2(t) {
		//var command = 'kemaskinipermohonan';
		var command = 'maklumatanah';
		var mode = 'MakAsasTanah';
		var tabId = 0;
		var tabmode = t;
		var dochange = "doChanges";
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&doChange='+dochange);
	
	}

	function doViewForKemaskini() {
		var command = 'kemaskinipermohonan';
		var mode = 'MakAsasTanah';
		var tabId = 0;
		var tabmode = 2;
		var button = "doViewForKemaskini";
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button);
	}

	function doChangeSubUrusan() {
		//document.${formName}.cmdSimpan.value = "doChangeSubUrusan";
		doAjaxCall${formName}("pohonfailbaru","mode=doChanges");
	}

function kiraLuas(idLuas){
  var jenisLuas = idLuas;
  // KILOMETER PERSEGI
  if(jenisLuas == "1"){
  	var luasK = (document.${formName}.txtLuas1.value);
	var luasH = luasK*100;
  	document.${formName}.Luas.value = luasH.toFixed(5);
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
	  document.${formName}.Luas.value = luasH.toFixed(5);
   }
   else
   //EKAR, ROOD, POLE
   if(jenisLuas == "4"){
  	  var luasE = document.${formName}.txtLuas2.value;
	  var luasR = document.${formName}.txtLuas3.value;
	  var luasP = document.${formName}.txtLuas4.value;
	  var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  document.${formName}.Luas.value = luasH.toFixed(5);
   }
   else
   //KAKI PERSEGI
   if(jenisLuas == "5"){
  	  var luasAsal = document.${formName}.txtLuas1.value;
	  var luasK = luasAsal*0.0000092;
  	  document.${formName}.Luas.value = luasK.toFixed(5);
  	  
   }else if(jenisLuas == "6"){	//EKAR PERPULUHAN
	  	  var luasAsal = document.${formName}.txtLuas1.value;
		  /* AZAM */
		  var luasK = luasAsal*0.405;
	  	  document.${formName}.Luas.value = luasK.toFixed(5);
	  	  
	} else
   //EKAR,DEPA
   if(jenisLuas == "7"){
  	  var luasE = document.${formName}.txtLuas5.value;
	  var luasD = document.${formName}.txtLuas6.value;
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
  	  document.${formName}.Luas.value = luasH.toFixed(5);
   }
    else
   //RELONG,JEMBA,KAKI PERSEGI
   if(jenisLuas == "8"){
  	  var luasR = document.${formName}.txtLuas2.value;
	  var luasJ = document.${formName}.txtLuas3.value;
	  var luasK = document.${formName}.txtLuas4.value;
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
  	  document.${formName}.Luas.value = luasH.toFixed(5);
   }
}

	function simpanHakmilikPPT(idpermohonanPPT,command,mode,tabId,tabmode){
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&idpermohonanPPT='+idpermohonanPPT);
	}
	function viewListHakmilik(idpermohonanPPT,command,mode,tabId,tabmode){
	
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&idpermohonanPPT='+idpermohonanPPT);
	
	}

	function sahkanPermohonan(id) {
		if ( !window.confirm("Data Permohonan akan dimasukkan ke dalam maklumat Rekod!\nAdakah Anda Pasti?") ) return;
		var command = 'SahkanPermohonan';
		var mode = '';
		var tabId = 0;
		var tabmode = 2;
		var button = "doSahkan";
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button);
	}
	
	// 02/06/2010 -Pilih Pegawai untuk tandatangan surat
	function openSuratPegawai(urli,param,laporan,tem){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
	    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();	
	}

	function viewBorangK(){
		doAjaxCall${formName}("viewBorangK");
	}

	function cariFailPPT(){
		doAjaxCall${formName}("searchBorangK");
	}

	function simpanBorangK(idHakmilikPPT){
		doAjaxCall${formName}("simpanBorangK","idHakmilikPPT="+idHakmilikPPT);
		
	}

	function kembaliBorangK(){
		
	}
	
	function CetakSenaraiHakmilikRizab(id,temp,urlserv) {
		var param ="";
   	 	var url = "../servlet/"+urlserv+"?"+id+temp;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
	
	// TAB HANTAR PERMOHONAN
	function tambahHantarPTGPTD(){
		if(document.${formName}.txttarikhsuratPTGPTD.value == ""){
			alert('Sila masukkan " Tarikh " terlebih dahulu.');
	  		document.${formName}.txttarikhsuratPTGPTD.focus(); 
			return; 
		}
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var tabId = 4;
		var tabmode = 1;
		var button = "hantarptgptdsimpan";
		doAjaxCall${formName}(command,'tabId3=1&button='+button+'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
		
	}
	
	function kemaskiniSimpanPTGPTD(){
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var tabId = 4;
		var tabmode = 1;
		var button = "kemaskiniptgptd";
		doAjaxCall${formName}(command,'tabId3=1&button='+button+'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
		
	}
	
	function kemaskiniHantarPTGPTD(i){
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var tabId = 4;
		var tabmode = 4;
		var button = "paparanptgptd";
		doAjaxCall${formName}(command,'tabId3=1&button='+button+'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
		
	}
	
	function hapusHantarPTGPTD(i){
		var command = 'kemaskinipermohonan';
		var mode = 'Pembayaran';
		var tabId = 4;
		var tabmode = 4;
		var button = "hapusptgptd";
		doAjaxCall${formName}(command,'tabId3=1&button='+button+'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
		
	}

	function kosongkanPendaftaran() {
		document.${formName}.reset();
		doAjaxCall${formName}("pohonfailbaru","mode=reset");
		
	}
	
	//[Cetak]
	//31/07/2012
	function cetakMaklumatRizab(idhakmilik){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatRizab&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
		//[Cetak]
	//31/07/2012
	function cetakMaklumatHakmilik(idhakmilik){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatHakmilik&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
	
	// SKRIN TINDAKAN, TAB [TINDAKAN]
	function skrinTindakanTambah(id_) {
		//String param = '3','kemaskinipermohonan','Pembayaran','tindakanpermohonan',
		var command = 'kemaskinipermohonan' ;
		var mode = 'Pembayaran';
		var tabId3 = '3';
		//var button = 'tindakanpermohonan';
		var button = 'tambah';
		document.${formName}.pagemode.value = 'tambah';
		
		//document.${formName}.id_kemaskini.value = id_;
		//doAjaxCall${formName}('tindakan',"");
		doAjaxCall${formName}(command,'selectedTab=4&mode='+mode+'&tabId3='+tabId3+'&button='+button+'&idpermohonan='+id_);
	
	}
	
	function simpanTindakan(id_) {
		var command = 'kemaskinipermohonan' ;
		var mode = 'Pembayaran';
		var tabId3 = '3';
		var button = 'simpan';
		document.${formName}.pagemode.value = 'simpan';
		doAjaxCall${formName}(command,'selectedTab=4&mode='+mode+'&tabId3='+tabId3+'&button='+button+'&idpermohonan='+id_);
	
	}
	
	function skrinDariTindakan(id_) {
		//String param = '3','kemaskinipermohonan','Pembayaran','tindakanpermohonan',
		var command = 'kemaskinipermohonan' ;
		var mode = 'Pembayaran';
		var tabId3 = '3';
		//var button = 'tindakanpermohonan';
		var button = 'tambah_';
		document.${formName}.pagemode.value = 'tambah';
		
		//document.${formName}.id_kemaskini.value = id_;
		//doAjaxCall${formName}('tindakan',"");
		doAjaxCall${formName}(command,'selectedTab=4&mode='+mode+'&tabId3='+tabId3+'&button='+button+'&idpermohonan='+id_);
	
	}

	function viewTindakan(idSusulanStatus,idStatusFail,idSusulan,id_) {
		var command = 'kemaskinipermohonan' ;
		var mode = 'Pembayaran';
		var tabId3 = '3';
		var button = 'view';
		document.${formName}.pagemode.value = 'view';
		doAjaxCall${formName}(command,'selectedTab=4&mode='+mode+'&tabId3='+tabId3+'&button='+button+'&idpermohonan='+id_+'idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}
	//										idStatusFail,
	function kemaskiniTindakan(idSusulanStatus,idSusulan,id_) {
		var command = 'kemaskinipermohonan' ;
		var mode = 'Pembayaran';
		var tabId3 = '3';
		var button = 'kemaskini';
		document.${formName}.pagemode.value = 'kemaskini';
		doAjaxCall${formName}(command,'selectedTab=4&mode='+mode+'&tabId3='+tabId3+'&button='+button+'&idpermohonan='+id_+'idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}
	
	//										idStatusFail,
	function doKemaskiniTindakan(idSusulanStatus,idSusulan,id_) {
		var command = 'kemaskinipermohonan' ;
		var mode = 'Pembayaran';
		var tabId3 = '3';
		var button = 'dokemaskini';
		document.${formName}.pagemode.value = 'dokemaskini';
		doAjaxCall${formName}(command,'selectedTab=4&mode='+mode+'&tabId3='+tabId3+'&button='+button+'&idpermohonan='+id_+'idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}

	function hapusTindakan(idSusulanStatus,idSusulan,id_) {
		var command = 'kemaskinipermohonan' ;
		var mode = 'Pembayaran';
		var tabId3 = '3';
		var button = 'hapus';		
		//document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'hapus';
		if ( !window.confirm("Maklumat akan dihapuskan. Adakah Anda Pasti?") ) return;	
		
		//doAjaxCall${formName}('tindakan','&idsusulan='+idSusulan);
		doAjaxCall${formName}(command,'selectedTab=4&mode='+mode+'&tabId3='+tabId3+'&button='+button+'&idpermohonan='+id_+'idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}
	
	
	function XkemaskiniTindakan(id_,idSusulanStatus,idSusulan) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'kemaskini';
		doAjaxCall${formName}('tindakan','idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}
	
	function XdoKemaskiniTindakan(id_,idSusulanStatus,idSusulan) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'dokemaskini';
		doAjaxCall${formName}('tindakan','idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}
	 

	
//selectTab3('3','kemaskinipermohonan','Pembayaran','tindakanpermohonan','')
function XselectTab3(tabId3,command,mode,button,id){
	//alert(selectTab3);
	//document.${formName}.selectedTab.value="4"; 
	doAjaxCall${formName}(command,'selectedTab=4&mode='+mode+'&tabId3='+tabId3+'&button='+button+'&idpermohonan='+id);
}

        
	
</script>
#parse("app/htp/permohonan/utiliti/javaScriptMaklumatPermohonan.jsp")
#parse("app/htp/utiliti/javaScriptUmum.jsp")
