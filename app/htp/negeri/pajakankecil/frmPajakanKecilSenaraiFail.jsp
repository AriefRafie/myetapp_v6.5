<!-- frmPajakanKecilSenaraiFail.jsp -->
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
<input name="idNegeri" type="hidden" value="$!idNegeri" >
	<tr>
   		<td>&nbsp;</td>
	</tr>

	<tr>
    	<td>    	

			#parse("app/htp/utiliti/frmIndexCarianHeader.jsp")
			
			#parse("app/htp/utiliti/frmIndexCarianFooter.jsp")

		</td>
	</tr>

	<tr>
    	<td>   		

			<fieldset>
				<legend>SENARAI FAIL PAJAKAN KECIL TANAH/BANGUNAN</legend>
			
			   #if (!$!jenisAkses.equals('Readonly'))
				<input class="stylobutton100" value="Tambah" type="button" onClick="javascript:tambahPermohonan()">
				
			   #end
						 
				#parse("app/utils/record_paging.jsp")
			  	<table width="100%" cellspacing="2" cellpadding="1" border="0">
			  		<tr class="table_header">
					  	<!-- <td><b>NO</b></td>
					  	<td><b>NO FAIL</b></td>
					  	<td><b>NAMA FAIL</b></td>
					  	<td><b>STATUS</b></td> -->
						
						<td width="3%"><b>BIL.</b></td>
						<td width="20%"><b>NO. FAIL</b></td>
						<td width="37%"><b>TAJUK FAIL</b></td>
						<td width="17%"><b>NEGERI</b></td>
						<td width="23%"><b>STATUS</b></td>		  	
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
				  	<tr>
				  		<td class="$row"><a href="javascript:tajukPermohonan('$senarai.id')">$cnt.</a></td>
				  		<td class="$row">
						  	<a  href="javascript:senaraiPermohonan('$senarai.id')" class="style1">
							$senarai.no
							</a>
				  		</td>
					  	<td class="$row">$senarai.tajuk</td>
						<td class="$row">$senarai.negeri</td>
					  	<td class="$row">$senarai.keterangan</td>
				  	</tr>
			  
			  		#end
					#if ($cnt == 0)
					<tr> 
						<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
					</tr> 
			  		#end
				
				</table>
			    
			    <input type="hidden" name="command1" >
			   	<!-- <input type="hidden" name="id_kemaskini" > -->
			   	<input type="hidden" name="fail" >
			   	<input type="hidden" name="pagemode" >
			   	<input type="hidden" name="langkah" value="0" >
			   	<input type="hidden" name="socDaerah">			   	
			</fieldset>

		</td>
	</tr>
</table>
<input name="txtcarian" type="hidden" value="$!iscarian" >

<script>
	
//frmPajakanKecilSenaraiFail.jsp 

//CARIAN
	function cmdChangeDaerahCarian() {
		doAjaxCall${formName}("","mode=changeDaerah");
	}

	function Xcarian() {
		document.${formName}.langkah.value = '0->-0';
		doAjaxCall${formName}("pksenaraifailcari");
		
	}
	function carianFail() {
		document.${formName}.langkah.value = '0->-0';
		doAjaxCall${formName}("pksenaraifailcari");
		
	}
	
	function cancel() {
		document.${formName}.reset();
	}

function tambahPermohonan() {
	document.${formName}.langkah.value = '0->1';
	document.${formName}.pagemode.value = "0";
	doAjaxCall${formName}("pkfailbaru");		
}

function senaraiPermohonan(id) {
	document.${formName}.langkah.value = '0->-1';
	document.${formName}.fail.value = id;
	doAjaxCall${formName}("pksenaraipermohonan");	

}

function tajukPermohonan(id){
	document.${formName}.fail.value = id;
	doAjaxCall${formName}("tajukpermohonan");	
	}
	
function simpanTajukPermohonan(id){
	//document.${formName}.fail.value = id;
	doAjaxCall${formName}("simpantajukpermohonan","fail="+id);	
	}
	
function kemaskiniTajukPermohonan(){
	//document.${formName}.fail.value = id;
	doAjaxCall${formName}("kemaskinitajukpermohonan");	
	}
// end of frmPajakanKecilSenaraiFail.jsp 	

// frmPajakanKecilPendaftaran.jsp
//end of frmPajakanKecilPendaftaran.jsp


function updateSewaan(){
	doAjaxCall${formName}("viewSewaan");
}

function kemaskiniMaklumatSewaan(){
	doAjaxCall${formName}("KemaskiniSewaan");
}

function updateMaklumatSewaan(){
	if ( document.${formName}.txtsewa.value == "" ) { 
  		alert('Sila masukkan maklumat sewa terlebih dahulu.');
  		document.${formName}.txtsewa.focus(); return; 
  	}
    if ( document.${formName}.txddari.value == "" ) { 
  		alert('Sila masukkan tarikh dari terlebih dahulu.');
    	//document.freg.txddari.focus(); 
    	return; 
    }
   	if ( document.${formName}.txdhingga.value == "" ) { 
  		alert('Sila masukkan tarikh hingga terlebih dahulu.');
    	return; 
    } 
    if ( document.${formName}.txtluas.value == "" ) { 
    	alert('Sila masukkan maklumat luas terlebih dahulu.');
    	document.${formName}.txtluas.focus(); 
    	return; 
    }
    if ( document.${formName}.socLuas.value == "" ) { 
    	alert('Sila masukkan maklumat lot terlebih dahulu.');
    	document.${formName}.socLuas.focus(); 
    	return; 
    }
     if ( document.${formName}.socLuas.value == "" ) { 
    	alert('Sila masukkan maklumat lot terlebih dahulu.');
    	document.${formName}.socLuas.focus(); 
    	return; 
    }    
    if ( document.${formName}.txtalamat1.value == "" ) { 
    	alert('Sila masukkan alamat 1 terlebih dahulu.');
    	document.${formName}.txtalamat1.focus(); 
    	return; 
    }
	
	/*
    if ( document.${formName}.txtalamat2.value == "" ) { 
    	alert('Sila masukkan alamat 2 lot terlebih dahulu.');
    	document.${formName}.txtalamat2.focus(); 
    	return; 
    }
	
    if ( document.${formName}.txtposkod.value == "" ) { 
    	alert('Sila masukkan maklumat poskod terlebih dahulu.');
    	document.${formName}.txtposkod.focus(); 
    	return; 
    } 
	*/
   	if ( document.${formName}.socDaerah.value == "" ) { 
    	alert('Sila pilih maklumat daerah terlebih dahulu.');
    	document.${formName}.socDaerah.focus(); 
    	return; 
    }
	if ( !window.confirm("Adakah Anda Pasti ?") )
		   return;
	doAjaxCall${formName}("updateSewaanSewaan");
}


function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}

function tambahPermohonan2(id) {
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.command.value = "pkpermohonantambah";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pkpermohonantambah");	
}

function simpanFail(){
	
  	if ( document.${formName}.socNegeri.value == "" ) { 
  		alert('Sila pilih negeri terlebih dahulu.');
  		document.${formName}.socNegeri.focus(); return; 
  	} 
    if ( document.${formName}.sockementerian.value == "" ) { 
  		alert('Sila pilih kementerian terlebih dahulu.');
    	document.${formName}.sockementerian.focus(); 
    	return; 
    } 
   	if ( document.${formName}.socAgensi.value == "" ) { 
  		alert('Sila pilih agensi terlebih dahulu.');
    	document.${formName}.socAgensi.focus(); 
    	return; 
    }
    if ( document.${formName}.txttajuk.value == "" ) { 
    	alert('Sila masukkan maklumat tajuk terlebih dahulu.');
    	document.${formName}.txttajuk.focus(); 
    	return; 
    }
  	/* if ( document.${formName}.txdTarikhSuratKJP.value == "" ) { 
    	alert('Sila masukkan tarikh surat KJP terlebih dahulu.');
    	document.${formName}.txdTarikhSuratKJP.focus(); 
    	return; 
    } */
   /*  if ( document.${formName}.txtNoFailLain.value == "" ) { 
    	alert('Sila masukkan nombor rujukan surat terlebih dahulu.');
    	document.${formName}.txtNoFailLain.focus(); 
    	return; 
    }    */ 
    
    //document.${formName}.command.value = "pkfailbaru";
	if(document.${formName}.pagemode.value == "0"){
      	document.${formName}.pagemode.value = "1";
  	}else if(document.${formName}.pagemode.value == "2"){
      	//document.${formName}.command.value = "pkpermohonanbaru";
      	//document.${formName}.command.value = "pkpermohonankemaskini";
		
		//firzan disabled this based on Saiful bakhtiar Advice
      	//document.${formName}.id_kemaskini.value = "$permohonanInfo.idpermohonan";
      	document.${formName}.pagemode.value = "3";
  	}else{
		//firzan disabled this based on Saiful bakhtiar Advice
  	      	//document.${formName}.id_kemaskini.value = "$permohonanInfo.idpermohonan";
  	
  	}
    //document.${formName}.method = "post";
    if ( !window.confirm("Adakah Anda Pasti ?") )
		   return;
    doAjaxCall${formName}("pkfailbaru");
	//document.${formName}.action = "";
	//document.${formName}.submit();

}


function kemaskiniPermohonan(id) {
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.command.value = "pkpermohonankemaskini";
	//document.${formName}.action = "";
	//document.${formName}.submit();
 	//document.${formName}.pagemode.value="3";
    //doAjaxCall${formName}("pkpermohonankemaskini","pagemode=3");
    doAjaxCall${formName}("pkpermohonankemaskini","pagemode=0");
}

//sama dengan kemaskiniPermohonan
function skrinKemaskiniPermohonan(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.fail.value = id;
    doAjaxCall${formName}("pkpermohonankemaskini","pagemode=0");
}

// 11/06/2010 [Kembali] untuk frmPajakanKecilSemakanPermohonan.jsp
function skrinSemakanPermohonan(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 0;
    doAjaxCall${formName}("pkpermohonankemaskini");
}

function semakanPermohonanTambah(id) {
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.pagemode.value="simpan";
	document.${formName}.pagemode.value="kemaskini";
	doAjaxCall${formName}("pkpermohonankemaskini");
}

function semakanPermohonanTambah1(id,f) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value=f;
	doAjaxCall${formName}("pkpermohonankemaskini");
}

/*function semakanPTambah(id) {

	if ( document.${formName}.cmdSimpan.value == "Kemaskini" ) {
		document.${formName}.pagemode.value="3";
	}else {
		document.${formName}.pagemode.value="4";
	}
	doAjaxCall${formName}("pkpermohonankemaskini");
}*/

function semakanPTambah(id) {
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.pagemode.value="simpan";
	document.${formName}.pagemode.value="kemaskini";
	doAjaxCall${formName}("semakanPKP");
}

function semakanPTPSimpan(id) {
	
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.pagemode.value="simpan";
	//document.${formName}.pagemode.value="kemaskini";
	
	doAjaxCall${formName}("semakanPKP","pagemode=simpan");
//	doAjaxCall${formName}('semakanPKP','pagemode=simpan&id_kemaskini='+id);
}

function semakanPTPKemaskini(id) {
	document.${formName}.id_kemaskini.value = id;
		//document.${formName}.pagemode.value="kemaskini";
	doAjaxCall${formName}('semakanPKP','pagemode=kemaskini&idpermohonan='+id);
	//doAjaxCall${formName}('semakanPKP','idpermohonan='+id);
}

function nexti(id) {
	//document.${formName}.command.value = "pksemakanseterus" pkpemilikseterus;
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.method="post";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pksemakanseterus");
}

/*function nexti2(id) {
	//document.${formName}.command.value = "pksemakanseterus" pkpemilikseterus;
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.method="post";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pkpemilikseterus");
}*/

function nexti3(id) {
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.command.value = "pkmaklumatseterus";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pkmaklumatseterus");
}

function nexti4(id) {
	//document.${formName}.method="post";
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.command.value = "pksemakanpkpseterus";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pksemakanpkpseterus");
}

function nexti5(id) {
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.command.value = "pkderafseterus";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pkderafseterus");
}

function nexti6(id) {
	//document.${formName}.command.value = "pksemakanseterus";
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.method="post";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pksemakanseterus");
}

function nexti7(id) {
	//document.${formName}.command.value = "pksemakanseterus";
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.method="post";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pksemakanseterus");
}


function tambahHakmilik(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 0;
	doAjaxCall${formName}("pkpemiliktambah");
}

function skrinTambahHakmilikPemilik(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 0;
	doAjaxCall${formName}("tambahhakmilikpemilik");
}

//fir test


function doChangeNegeriHakmilik() {
	//document.${formName}.pagemode.value = 'doChangeNegeriHakmilik';
	document.${formName}.pagemode.value = 'dochangedaerah';
	doAjaxCall${formName}("tambahhakmilikpemilik");
}

function doChangeDaerahHakmilik() {
	document.${formName}.pagemode.value = 'dochangedaerah';
	doAjaxCall${formName}("tambahhakmilikpemilik");
}

//fir test
function doChangeDaerahHakmilik(id) {
	document.${formName}.pagemode.value = 'dochangedaerah';
	document.${formName}.id_kemaskini.value = id;
	doAjaxCall${formName}("tambahhakmilikpemilik");
}

function doChangeNegeri1() {
	document.${formName}.pagemode.value = 'doChangeNegeri1';
	doAjaxCall${formName}("pkpemilikkemaskini");
}

function doChangeNegeri2() {
	//document.${formName}.mode.value = 'doChangeNegeri2';
	document.${formName}.pagemode.value = 0;
	doAjaxCall${formName}("pkkemaskinipemilik",'mode=doChangeNegeri2');
}

function tambahHakmilik2(idhmu,s){
	//frmPajakanKecilSenaraiPemilik.jsp
	document.${formName}.fail.value = s;
	document.${formName}.id_kemaskini.value = idhmu;
	//document.${formName}.nohakmilik.value = j;
	document.${formName}.pagemode.value = 0;
	//document.${formName}.command.value = "pkpemiliktambah";
	//document.${formName}.command.value = "pkpemilikkemaskini";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pkpemilikkemaskini");
}

function kemaskiniHakmilik(idh,id) {
	//frmPajakanKecilSenaraiPemilik.jsp
	document.${formName}.fail.value = idh;
	document.${formName}.pagemode.value = 1;
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.command.value = "pkpemiliktambah";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pkpemiliktambah");
}

function back(){
	doAjaxCall${formName}("pkpemilikseterus");
}

function backToSenaraiPemilik(){
	doAjaxCall${formName}("pksemakanseterus");
}

function backPre(id,idpermohonan) {
	
	//document.${formName}.command.value = "pksenaraipermohonan";
	document.${formName}.langkah.value = '0->-1';
	document.${formName}.fail.value = id;
	document.${formName}.id_kemaskini.value = idpermohonan;
	//document.${formName}.action = "";
	//document.${formName}.submit();
	//doAjaxCall${formName}("pksenaraipermohonan");
	doAjaxCall${formName}("pksemakanseterus");
	//doAjaxCall${formName}("pkpemilikseterus");
	
}

function backPrePage2(id,idpermohonan) {
	
	//document.${formName}.command.value = "pksenaraipermohonan";
	document.${formName}.langkah.value = '0->-1';
	document.${formName}.fail.value = id;
	document.${formName}.id_kemaskini.value = idpermohonan;
	//document.${formName}.action = "";
	//document.${formName}.submit();
	//doAjaxCall${formName}("pksenaraipermohonan");
	//doAjaxCall${formName}("pksemakanseterus");
	doAjaxCall${formName}("pkpemilikseterus");
	
}

function backPrePage3(id,idpermohonan) {

	//document.${formName}.command.value = "pksenaraipermohonan";
	document.${formName}.langkah.value = '0->-1';
	document.${formName}.fail.value = id;
	document.${formName}.id_kemaskini.value = idpermohonan;
	//document.${formName}.action = "";
	//document.${formName}.submit();
	//doAjaxCall${formName}("pksenaraipermohonan");
	//doAjaxCall${formName}("pksemakanseterus");
	doAjaxCall${formName}("semakanPKP");
	
}

function backPreSenaraiPermohonan(id,idpermohonan) {

	//document.${formName}.command.value = "pksenaraipermohonan";
	document.${formName}.langkah.value = '0->-1';
	document.${formName}.fail.value = id;
	document.${formName}.id_kemaskini.value = idpermohonan;
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pksenaraipermohonan");
	//doAjaxCall${formName}("pksemakanseterus");
	//doAjaxCall${formName}("pkpemilikseterus");
	
}

//Senarai Fail
function backMain() {
	doAjaxCall${formName}("");
}

function tambahMaklumat(id) {
	//frmpajakankecilPemilikKemaskiniN.jsp 
	if(document.${formName}.sochakmilik.value == "" || document.${formName}.sochakmilik.value == 0){
		if(document.${formName}.txtnohakmilik.value == "" || document.${formName}.txtnohakmilik.value == 0){
			alert('Sila pilih No Hakmilik terlebih dahulu @\n Lain-lain bagi kemasukan baru');
			return;
		}else if(document.${formName}.txtnolot.value == ""){	
			alert('Sila masukkan No Lot terlebih dahulu');
			return;
		}	
	}


	if ( document.${formName}.txtnama.value == "" ) { 
    	alert('Sila masukkan nama terlebih dahulu.');
    	document.${formName}.txtnama.focus(); 
    	return; 
    	}    
	
	if ( document.${formName}.txtalamat1.value == "" ) { 
    	alert('Sila masukkan alamat 1 terlebih dahulu.');
    	document.${formName}.txtalamat1.focus(); 
    	return; 
    }    
    if ( document.${formName}.txtalamat2.value == "" ) { 
    	alert('Sila masukkan alamat 2 lot terlebih dahulu.');
    	document.${formName}.txtalamat2.focus(); 
    	return; 
    }
    if ( document.${formName}.txtposkod.value == "" ) { 
    	alert('Sila masukkan maklumat poskod terlebih dahulu.');
    	document.${formName}.txtposkod.focus(); 
    	return; 
    } 
   	if ( document.${formName}.socDaerah.value == "" ) { 
    	alert('Sila pilih maklumat daerah terlebih dahulu.');
    	document.${formName}.socDaerah.focus(); 
    	return; 
    }
    
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 1;

	doAjaxCall${formName}("pkpemiliktambah");
	
}
function kemaskiniHakmilik(idh,id) {
	//frmPAjakanKecilSEnaraiPemilik.jsp
	document.${formName}.fail.value = idh;
	document.${formName}.pagemode.value = 1;
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.command.value = "pkpemiliktambah";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pkpemiliktambah");
}



function tambahMaklumatsewaan(id) {
	/*var hingga = Date.parse(document.${formName}.txdhingga.value);
	var dari = Date.parse(document.${formName}.txddari.value);
	var diff_date =  hingga - dari;
	alert (new Date());
	var num_years = diff_date/31536000000;
	var num_months = (diff_date % 31536000000)/2628000000;
	var num_days = ((diff_date % 31536000000) % 2628000000)/86400000;
	alert("this is not a number 1:"+Math.floor(num_years));
	alert("this is not a number 2:"+Math.floor(num_months));
	alert("this is not a number 3:"+Math.floor(num_days));
	*/
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 3;
	//document.${formName}.command.value = "pkmaklumat";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pkmaklumat");
}

function tambahMaklumatsewaansimpan() {

	var dari_bulan;
	var dari_hari;
	var dari_tahun;
	var hingga_bulan;
	var hingga_hari;
	var hingga_tahun;
  	if ( document.${formName}.txtsewa.value == "" ) { 
  		alert('Sila masukkan maklumat sewa terlebih dahulu.');
  		document.${formName}.txtsewa.focus(); return; 
  	}
    if ( document.${formName}.txddari.value == "" ) { 
  		alert('Sila masukkan tarikh dari terlebih dahulu.');
    	//document.freg.txddari.focus(); 
    	return; 
    }
   	if ( document.${formName}.txdhingga.value == "" ) { 
  		alert('Sila masukkan tarikh hingga terlebih dahulu.');
    	return; 
    } 
    if ( document.${formName}.txtluas.value == "" ) { 
    	alert('Sila masukkan maklumat luas terlebih dahulu.');
    	document.${formName}.txtluas.focus(); 
    	return; 
    }
    if ( document.${formName}.socLuas.value == "" ) { 
    	alert('Sila masukkan maklumat lot terlebih dahulu.');
    	document.${formName}.socLuas.focus(); 
    	return; 
    }
     if ( document.${formName}.socLuas.value == "" ) { 
    	alert('Sila masukkan maklumat kod luas terlebih dahulu.');
    	document.${formName}.socLuas.focus(); 
    	return; 
    }    
    if ( document.${formName}.txtalamat1.value == "" ) { 
    	alert('Sila masukkan alamat 1 terlebih dahulu.');
    	document.${formName}.txtalamat1.focus(); 
    	return; 
    } 
	/*
    if ( document.${formName}.txtalamat2.value == "" ) { 
    	alert('Sila masukkan alamat 2 lot terlebih dahulu.');
    	document.${formName}.txtalamat2.focus(); 
    	return; 
    }
	
    if ( document.${formName}.txtposkod.value == "" ) { 
    	alert('Sila masukkan maklumat poskod terlebih dahulu.');
    	document.${formName}.txtposkod.focus(); 
    	return; 
    }
	*/
   	if ( document.${formName}.socDaerah.value == "" ) { 
   		
   		if ( document.${formName}.idNegeri.value == "13" ) { 
    		alert('Sila pilih maklumat bahagian terlebih dahulu.');
   		}else{
   			alert('Sila pilih maklumat daerah terlebih dahulu.');
   		}
    	document.${formName}.socDaerah.focus(); 
    	return; 
    }
    
      
    
	hingga_bulan = document.${formName}.txdhingga.value.substring(3,5);
	hingga_hari = document.${formName}.txdhingga.value.substring(0,2);
	hingga_tahun = document.${formName}.txdhingga.value.substring(6,10);
	var hinggatemp = hingga_bulan+"/"+hingga_hari+"/"+hingga_tahun;
	dari_bulan = document.${formName}.txddari.value.substring(3,5);
	dari_hari = document.${formName}.txddari.value.substring(0,2);
	dari_tahun = document.${formName}.txddari.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var dari = Date.parse(daritemp);
	var hingga = Date.parse(hinggatemp);

	var diff_date =  hingga - dari;
	
	var num_years = diff_date/31536000000;
	var num_months = (diff_date % 31536000000)/2628000000;
	var num_days = ((diff_date % 31536000000) % 2628000000)/86400000;
	//alert("this is not a number 1:"+Math.floor(num_years));
	//alert("bulan:"+num_months+"cxvcxvcxv:"+Math.floor(num_months));
	//alert("this is not a number 3:"+Math.floor(num_days));
	document.${formName}.txtbulan.value = Math.floor(num_months);

	/*var my_string = document.${formName}.txtposkod.value;
	if(isNaN(my_string)){
		alert("this is not a number ");
	}else{
		alert("this is a number ");
	}*/
	//isxNumber(this.txtposkod);
	//return true ;
	//var newMode=0;
	if(document.${formName}.pagemode.value ==3){
		//document.${formName}.pagemode.value=4;
		document.${formName}.pagemode.value=0;
	} 
	//document.${formName}.command.value = "pkmaklumat";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	//alert(newMode);
	if ( !window.confirm("Adakah Anda Pasti ?") )
		   return;
	doAjaxCall${formName}("pkmaklumat");
}


function tambahMaklumatPemilik(id) {
	
	if(document.${formName}.socHakmilik.value == "" || document.${formName}.socHakmilik.value == 0){
		if(document.${formName}.txtnohakmilik.value == "" || document.${formName}.txtnohakmilik.value == 0){
			alert('Sila pilih No Hakmilik terlebih dahulu @\n Lain-lain bagi kemasukan baru');
			return;
		}else if(document.${formName}.txtnolot.value == ""){	
			alert('Sila masukkan No Lot terlebih dahulu');
			return;
		}	
	}

	if ( document.${formName}.idnegeri.value == "13" ){
		
		if ( document.${formName}.socDaerah1.value == "" ) { 
	    	alert('Sila pilih maklumat bahagian Hakmilik terlebih dahulu.');
	    	document.${formName}.socDaerah1.focus(); 
	    	return; 
	    }
		
	}else{
		
		if ( document.${formName}.socDaerah1.value == "" ) { 
	    	alert('Sila pilih maklumat daerah Hakmilik terlebih dahulu.');
	    	document.${formName}.socDaerah1.focus(); 
	    	return; 
	    }
		
	}
	
	
	if ( document.${formName}.socMukim.value == "" ) { 
    	alert('Sila masukkan nama Mukim terlebih dahulu.');
    	document.${formName}.socMukim.focus(); 
    	return; 
    	}    
	
	if ( document.${formName}.socHakmilik.value == "" ) { 
    	alert('Sila masukkan Jenis Hakmilik terlebih dahulu.');
    	document.${formName}.socHakmilik.focus(); 
    	return; 
    }    
    if ( document.${formName}.txtnohakmilik.value == "" ) { 
    	alert('Sila masukkan Nombor Hakmilik terlebih dahulu.');
    	document.${formName}.txtnohakmilik.focus(); 
    	return; 
    }
    if ( document.${formName}.noLot.value == "" ) { 
    	alert('Sila masukkan Jenis Lot terlebih dahulu.');
    	document.${formName}.noLot.focus(); 
    	return; 
    } 

    if(document.${formName}.txtnolot.value == ""){	
		alert('Sila masukkan No Lot terlebih dahulu');
    	document.${formName}.txtnolot.focus(); 
		return;
	}

	if ( document.${formName}.txtnama.value == "" ) { 
    	alert('Sila masukkan nama terlebih dahulu.');
    	document.${formName}.txtnama.focus(); 
    	return; 
    	}    
	
	if ( document.${formName}.txtalamat1.value == "" ) { 
    	alert('Sila masukkan alamat 1 terlebih dahulu.');
    	document.${formName}.txtalamat1.focus(); 
    	return; 
    }  
	
	/*
    if ( document.${formName}.txtalamat2.value == "" ) { 
    	alert('Sila masukkan alamat 2 lot terlebih dahulu.');
    	document.${formName}.txtalamat2.focus(); 
    	return; 
    }
	*/
    /*
    if ( document.${formName}.txtposkod.value == "" ) { 
    	alert('Sila masukkan maklumat poskod terlebih dahulu.');
    	document.${formName}.txtposkod.focus(); 
    	return; 
    }
    */ 
    if ( document.${formName}.idnegeri.value == "13" ){
    	
    	if ( document.${formName}.socDaerah.value == "" ) { 
        	alert('Sila pilih maklumat bahagian terlebih dahulu.');
        	document.${formName}.socDaerah.focus(); 
        	return; 
        }
    	
    }else{
    	if ( document.${formName}.socDaerah.value == "" ) { 
        	alert('Sila pilih maklumat daerah terlebih dahulu.');
        	document.${formName}.socDaerah.focus(); 
        	return; 
        }
    }
   
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 1;
	//document.${formName}.method = "post";
	//document.${formName}.command.value = "pemiliksimpan";
	//document.${formName}.command.value = "pkpemiliktambah";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pkpemiliktambah");
	
}


function tambahMaklumatPemilik2(id) {
	//N1
	if ( document.${formName}.txtnama.value == "" ) { 
    	alert('Sila masukkan nama terlebih dahulu.');
    	document.${formName}.txtnama.focus(); 
    	return; 
    	}    
	
	if ( document.${formName}.txtalamat1.value == "" ) { 
    	alert('Sila masukkan alamat 1 terlebih dahulu.');
    	document.${formName}.txtalamat1.focus(); 
    	return; 
    }  
	/*
    if ( document.${formName}.txtalamat2.value == "" ) { 
    	alert('Sila masukkan alamat 2 lot terlebih dahulu.');
    	document.${formName}.txtalamat2.focus(); 
    	return; 
    }
    
    if ( document.${formName}.txtposkod.value == "" ) { 
    	alert('Sila masukkan maklumat poskod terlebih dahulu.');
    	document.${formName}.txtposkod.focus(); 
    	return; 
    } 
    */
   	if ( document.${formName}.socDaerah.value == "" ) { 
   		if ( document.${formName}.idNegeri.value == "13" ) { 
    		alert('Sila pilih maklumat bahagian terlebih dahulu.');
   		}else{
   			alert('Sila pilih maklumat daerah terlebih dahulu.');
   		}
    	document.${formName}.socDaerah.focus(); 
    	return; 
    }
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 1;
	//document.${formName}.method = "post";
	//document.${formName}.command.value = "pemiliksimpan";
	//document.${formName}.command.value = "pkpemiliktambah";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pkpemiliktambah2");
	
}

// Skrin Senarai Hakmilik

function skrinSenaraiHakmilikPemilik(id) {
	document.${formName}.id_kemaskini.value = id;
	doAjaxCall${formName}("pksemakanseterus");
}

function nexti2(id) {
	//document.${formName}.command.value = "pksemakanseterus";
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.method="post";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("pkpemilikseterus");
}

// Skrin Kemasukan Hakmilik
// Tidak Digunakan
 function enableField(){
 	document.${formName}.txtnohakmilik.value = document.${formName}.sochakmilik.value;
}

// Skrin Maklumat Penyewaan
//Fungsi date kemasukan manual
function trans_date2(t_d){
	var inputTo = document.${formName}.txdhingga.value;

	var myDateParts = t_d.split("/");
	var myDateParts2 = inputTo.split("/");
	
	var begDate = new Date();
	begDate.setFullYear(myDateParts[2]);
	begDate.setMonth(myDateParts[1]-1);
	begDate.setDate(myDateParts[0]);
	var endDate = new Date(inputTo);
	endDate.setFullYear(myDateParts2[2]);
	endDate.setMonth(myDateParts2[1]-1);
	endDate.setDate(myDateParts2[0]);
	if(inputTo !=""){
		if(endDate > begDate){
			DateDiff(begDate, endDate);
		}else{
			document.${formName}.txttahun.value="0";
			document.${formName}.txtbulan.value="0";
		}
	}
	

}



function trans_date21(t_d){
	
	var inputFrom= document.${formName}.txddari.value;
	var myDateParts = inputFrom.split("/");
	var myDateParts2 = t_d.split("/");
	
	var begDate = new Date(inputFrom);
	begDate.setFullYear(myDateParts[2]);
	begDate.setMonth(myDateParts[1]-1);
	begDate.setDate(myDateParts[0]);
	begDate.setHours(0);
	begDate.setMinutes(0);
	/*befDate.setMinutes(0);*/
	var endDate = new Date(t_d);
	endDate.setFullYear(myDateParts2[2]);
	endDate.setMonth(myDateParts2[1]-1);
	endDate.setDate(myDateParts2[0]);
	endDate.setHours(23);
	endDate.setMinutes(59);
	if(inputFrom !=""){
		if(endDate > begDate){
			DateDiff(begDate, endDate);
		}else{
			document.${formName}.txttahun.value="0";
			document.${formName}.txtbulan.value="0";
		}
	}
	


}

function DateDiff(fromDate, toDate)
{

    var increment = 0;
    var day = 0, month = 0, year = 0;
    if( fromDate.getDate() > toDate.getDate() )
    {
        increment  = monthDay[fromDate.getMonth()];
    }  
   
    if( increment == -1 )
    {
        if(IsLeapYear(fromDate.getFullYear()))
        {
            increment = 29;
        }
        else
        {
            increment = 28;
        }
    }    
    if( increment != 0 )
    {
        day = (toDate.getDate() + increment) - fromDate.getDate();
        increment = 1;
    }
    else
    {
        day = toDate.getDate() - fromDate.getDate() ;
    }  
    if ((fromDate.getMonth() + increment) > toDate.getMonth())
    {
        month = (toDate.getMonth() + 12) - (fromDate.getMonth() + increment);
        increment = 1;
    }
    else
    {
        month = (toDate.getMonth()) - (fromDate.getMonth() + increment);
        increment = 0;
    }
    year = toDate.getFullYear() - (fromDate.getFullYear() + increment);
    document.${formName}.txttahun.value=year;
	document.${formName}.txtbulan.value=month;
    //return diff;
}
function IsLeapYear(utc)
{
    var y = utc.getFullYear();
    return !(y % 4) && (y % 100) || !(y % 400) ? true : false;
}


function haha() {

	var dari_bulan;
	var dari_hari;
	var dari_tahun;
	var hingga_bulan;
	var hingga_hari;
	var hingga_tahun;
  	    
	hingga_bulan = document.${formName}.txdhingga.value.substring(3,5);
	hingga_hari = document.${formName}.txdhingga.value.substring(0,2);
	hingga_tahun = document.${formName}.txdhingga.value.substring(6,10);
	var hinggatemp = hingga_bulan+"/"+hingga_hari+"/"+hingga_tahun;
	dari_bulan = document.${formName}.txddari.value.substring(3,5);
	dari_hari = document.${formName}.txddari.value.substring(0,2);
	dari_tahun = document.${formName}.txddari.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var dari = Date.parse(daritemp);
	var hingga = Date.parse(hinggatemp);

	var diff_date =  hingga - dari;
	
	var num_years = diff_date/31536000000;
	var num_months = (diff_date % 31536000000)/2628000000;
	var num_days = ((diff_date % 31536000000) % 2628000000)/86400000;

	document.${formName}.txtbulan.value = Math.floor(num_months);

}

//modified by Rosli on 2010/02/11
function nexti8(id) {
	//doAjaxCall${formName}("pkmaklumatseterus","id_kemaskini="+id);	
	document.${formName}.pagemode.value = 0;	
	
	doAjaxCall${formName}('semakanPKP','idpermohonan='+id);
}


function simpanPermohonan(idfail){
	
  	/*if ( document.${formName}.socNegeri.value == "" ) { 
  		alert('Sila pilih negeri terlebih dahulu.');
  		document.${formName}.socNegeri.focus(); return; 
  	} 
    if ( document.${formName}.sockementerian.value == "" ) { 
  		alert('Sila pilih kementerian terlebih dahulu.');
    	document.${formName}.sockementerian.focus(); 
    	return; 
    } 
   	if ( document.${formName}.socAgensi.value == "" ) { 
  		alert('Sila pilih agensi terlebih dahulu.');
    	document.${formName}.socAgensi.focus(); 
    	return; 
    }*/
    if ( document.${formName}.txttajuk.value == "" ) { 
    	alert('Sila masukkan maklumat tajuk terlebih dahulu.');
    	document.${formName}.txttajuk.focus(); 
    	return; 
    }
    /*
  	if ( document.${formName}.txdTarikhSuratKJP.value == "" ) { 
    	alert('Sila masukkan tarikh surat KJP terlebih dahulu.');
    	document.${formName}.txdTarikhSuratKJP.focus(); 
    	return; 
    }
    if ( document.${formName}.txtNoFailLain.value == "" ) { 
    	alert('Sila masukkan nombor rujukan surat terlebih dahulu.');
    	document.${formName}.txtNoFailLain.focus(); 
    	return; 
    } 
    */   
    
   	document.${formName}.id_kemaskini.value = idfail;
   	document.${formName}.pagemode.value = "3";
    doAjaxCall${formName}("simpanpermohonan");

}

//fungsi untuk compare Tarikh

function checkDate() {
	var today = new Date();
	
	dari_bulan = document.${formName}.txdTarikhSuratKJP.value.substring(3,5);
	dari_hari = document.${formName}.txdTarikhSuratKJP.value.substring(0,2);
	dari_tahun = document.${formName}.txdTarikhSuratKJP.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var myDate = Date.parse(daritemp);
	

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		document.${formName}.txdTarikhSuratKJP.value = "";
  		document.${formName}.txdTarikhSuratKJP.focus();
 		return;
 	}

}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
    if (isNaN(content)) {
    	elmnt.value = RemoveNonNumeric(content);
        return;
   	}
}
function tambahHakmiliktoPemilik(idPermohonan,idPemilik){
	  doAjaxCall${formName}("addHakmilikToPemilik","id_permohonan="+idPermohonan+"&id_pemilik="+idPemilik);
}


//cetak kulit fail
function cetakKulitFail(idpermohonan) {

    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=NoFailTajukFail&idpermohonan="+idpermohonan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();


}

//cetak doket
function cetakDoket(idpermohonan) {

    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=rptNoFailTajukFail&idpermohonan="+idpermohonan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();


}

<!-- for paging -->
	function screen1(id){
		//document.${formName}.id_kemaskini.value = id;
			//document.${formName}.method="post";
			//document.${formName}.action = "";
			//document.${formName}.submit();
		//doAjaxCall${formName}("pksemakanseterus");
		kemaskiniPermohonan(id);
	}

	function screen2(id){
		//alert('Page 2 : ' + id);		
		document.${formName}.id_kemaskini.value = id;
		//document.${formName}.method="post";
		//document.${formName}.action = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("pksemakanseterus");
			
	}

	function screen3(id){
		//alert('Page 3 : ' + id);	
		document.${formName}.id_kemaskini.value = id;
		//document.${formName}.method="post";
		//document.${formName}.action = "";
		//document.${formName}.submit();
		//doAjaxCall${formName}("semakanPKP");
		doAjaxCall${formName}("pkpemilikseterus");
		
	}

	function screen4(id){
		document.${formName}.id_kemaskini.value = id;
			//document.${formName}.method="post";
			//document.${formName}.action = "";
			//document.${formName}.submit();
		//doAjaxCall${formName}("pksemakanpkpseterus");
		doAjaxCall${formName}("semakanPKP");

	}

	function screen5(id){
		//alert('Page 5');
		document.${formName}.id_kemaskini.value = id;
		//doAjaxCall${formName}("pkpemilikseterus");			
		doAjaxCall${formName}("pksemakanpkpseterus");
	}

	function screen6(id){
		//alert('Page 6');
		document.${formName}.id_kemaskini.value = id;
		//doAjaxCall${formName}("semakanPKP");
		
		doAjaxCall${formName}('tindakan',"");
		
	}

	function screen7(id){
		//alert('Page 7');
		document.${formName}.id_kemaskini.value = id;
		doAjaxCall${formName}("pksemakanpkpseterus");
		
	}

	function screen8(id){
		//alert('Page 8');
		document.${formName}.id_kemaskini.value = id;
		//doAjaxCall${formName}('tindakan',"id_kemaskini="+id);
		doAjaxCall${formName}('tindakan',"");
	}

function deletePemilik(idPermohonan,idPemilikPB){
document.${formName}.id_kemaskini.value = idPermohonan;
document.${formName}.pagemode.value = 'hapusPemilik';
if ( !window.confirm("Maklumat akan dihapuskan. Adakah Anda Pasti?") ) return;	
	doAjaxCall${formName}('pksemakanseterus',"idPemilikPB="+idPemilikPB);
}

function deleteHakmilik(idPermohonan,idHakmilikUrusan){
	document.${formName}.pagemode.value = 'hapusHakmilik';
	document.${formName}.id_kemaskini.value = idPermohonan;
	//doAjaxCall${formName}('deleteHakmilik',"id_permohonan="+idPermohonan+"&idHakmilik="+idHakmilikUrusan);
	if ( !window.confirm("Maklumat akan dihapuskan. Adakah Anda Pasti?") ) return;	
	doAjaxCall${formName}('pksemakanseterus',"idHakmilik="+idHakmilikUrusan);
}

function viewTanah(idHakmilikurusan,idPermohonan){
	doAjaxCall${formName}('viewhakmilik',"id_permohonan="+idPermohonan+"&idHakmilik="+idHakmilikurusan);
	
}

function simpanUpdateHakmilik(idPermohonan,idHakmilikurusan)
{
		doAjaxCall${formName}('kemaskinihakmilik',"id_permohonan="+idPermohonan+"&idHakmilik="+idHakmilikurusan);
}

function kemaskiniHakmilik(idPermohonan,idHakmilikurusan)
{		
		doAjaxCall${formName}('kemaskinihakmilik',"id_permohonan="+idPermohonan+"&idHakmilik="+idHakmilikurusan);
}
function viewPemilik(idPermohonan,idPihakBerkepentingan){
	doAjaxCall${formName}('viewpemilik',"id_permohonan="+idPermohonan+"&idPihakBerkepentingan="+idPihakBerkepentingan);
}

function kemaskiniPemilik(idPermohonan,idPihakBerkepentingan){
	doAjaxCall${formName}('pkkemaskinipemilik',"id_permohonan="+idPermohonan+"&idPihakBerkepentingan="+idPihakBerkepentingan);
}
function simpanUpdatePemilik(idPermohonan,idPihakBerkepentingan){
	doAjaxCall${formName}('pkkemaskinipemilik',"id_permohonan="+idPermohonan+"&idPihakBerkepentingan="+idPihakBerkepentingan);
}

// 01/06/2010 -Pilih Pegawai untuk tandatangan surat
function openSuratPegawai(urli,param,laporan,tem){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

// 16/06/2010 -Pilih Pegawai untuk tandatangan surat dan simpan status
function openSuratPegawaiSimpan(urli,param,laporan,tem){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawaisimpan&urli="+urli+"&"+param+tem+"&flagReport=S";
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
 
// 01/06/2010 -
function senaraiSuratSkrinSemakan2(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
 
// 01/06/2010 -
function senaraiSuratSkrinSemakan(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function copyAlamat(strValue){
	document.${formName}.txtnama.value = document.${formName}.txtTempPBNama.value;
	document.${formName}.txtnorujukan.value = document.${formName}.txtTempPBNoRuj.value;
  	document.${formName}.txtalamat1.value = document.${formName}.txtTempPBAlamat1.value;
  	document.${formName}.txtalamat2.value = document.${formName}.txtTempPBAlamat2.value;
  	document.${formName}.txtalamat3.value = document.${formName}.txtTempPBAlamat3.value;
  	document.${formName}.txtposkod.value = document.${formName}.txtTempPBPoskod.value;
  	document.${formName}.socNegeri.value = document.${formName}.txtTempPBSocNegeri.value;
  	document.${formName}.socDaerah.value = document.${formName}.txtTempPBSocDaerah.value;
  	//document.${formName}.socDaerah.value = document.${formName}.txtTempPBNoTelefon.value;
  	//document.${formName}.socDaerah.value == document.${formName}.txtTempPBNoFax.value;
  	
}

function doChangeNegeriCarian() {
	doAjaxCall${formName}("pksenaraifailcari","mode=changeNegeri");
}

function doChangeKementerianCarian() {
	doAjaxCall${formName}("pksenaraifailcari","mode=changeKementerian");
}

	function carianFailDariPermohonan(noFail) {
		//document.${formName}.langkah.value = '0->-0';
		doAjaxCall${formName}("pksenaraifailcari","nofail="+noFail+"&langkah=0->-0");
		
	}
	// Skrin Tindakan	
	function skrinTindakanTambah(id_) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'tambah';
		doAjaxCall${formName}('tindakan',"");
	
	}
	
	function simpanTindakan(id_) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'simpan';
		doAjaxCall${formName}('tindakan',"");
	
	}
	
	function simpanTindakanRole(id_,idSusulan) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'simpanpengesahan';
		doAjaxCall${formName}('tindakan','&roleparam=$portal_role&idsusulan='+idSusulan);
	
	}
	
	//function simpanTindakanSemak(id_) {
	//	document.${formName}.id_kemaskini.value = id_;
	//	document.${formName}.pagemode.value = 'simpan';
	//	doAjaxCall${formName}('tindakan',"");
	
	//}
	
	//function simpanTindakanLulus(id_) {
	//	document.${formName}.id_kemaskini.value = id_;
	//	document.${formName}.pagemode.value = 'simpanpengesahan';
	//	doAjaxCall${formName}('tindakan',"");
	
	//}
	
	function batalTindakan(id_,idSusulanStatus,idSusulan) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'view';
		doAjaxCall${formName}('tindakan','idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}
	
	function viewTindakan(idSusulanStatus,idStatusFail,idSusulan,id_) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'view';
		doAjaxCall${formName}('tindakan','idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}
	
	function kemaskiniTindakan(id_,idSusulanStatus,idSusulan) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'kemaskini';
		doAjaxCall${formName}('tindakan','idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}
	
	function doKemaskiniTindakan(id_,idSusulanStatus,idSusulan) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'dokemaskini';
		doAjaxCall${formName}('tindakan','idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}	
			
	function hapusTindakan(id_,idSusulan) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'hapus';
		if ( !window.confirm("Maklumat akan dihapuskan. Adakah Anda Pasti?") ) return;	
		
		doAjaxCall${formName}('tindakan','&idsusulan='+idSusulan);
	
	}
</script>
#parse("app/htp/utiliti/javaScriptUmum.jsp")
