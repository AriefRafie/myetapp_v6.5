<script type="text/javascript" src="../../library/js/CalendarPopup.js" ></script>
<link rel="stylesheet" type="text/css" href="../../library/js/jquery.timepickr.css" />
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPT.css" />
 
#set($noPengenalanPemohon = "")

#set($jenisPengenalan = "")

#foreach ( $senarai in $getMaklumatBantahan )
	#set ($txtNamaPembantah=$senarai.nama_kementerian)
   	#set ($txtNamaAgensi=$senarai.nama_agensi)
    #set ($txtAlamat1=$senarai.alamat1)
   	#set ($txtAlamat2=$senarai.alamat2)
   	#set ($txtAlamat3=$senarai.alamat3)
   	#set ($txtPoskod=$senarai.poskod)
   	#set ($id_negeri=$senarai.id_negeri)
    #set ($txtNamaNegeri=$senarai.nama_negeri)

        #set ($id_bantahan=$senarai.id_bantahan)
        #set ($txtNoBantahan=$senarai.no_bantahan)
        #set ($jenis_pembantah=$senarai.jenis_pembantah)
        #set ($txdTkhTerimaBrgN=$senarai.tarikh_terima)
        #set ($txdBrgN=$senarai.tarikh_borangn)
        #set ($id_pihakberkepentingan=$senarai.id_pihakberkepentingan)
        #set ($status_bantahan=$senarai.status_bantahan)
        #set ($id_hakmilik=$senarai.id_hakmilik)
        #set ($txtAlasanBantahan=$senarai.alasan)
        #set ($txtKptgnAtasTnh=$senarai.kepentingankeatas)
        #set ($id_jenispb=$senarai.id_jenispb)
        #set ($keteranganjenispb=$senarai.keterangan)
        #set ($id_bandar=$senarai.id_bandar)
        #set ($no_hakmilik=$senarai.no_hakmilik)
        #set ($txtNoPt=$senarai.no_pt)
        #set ($no_lot=$senarai.no_lot)
        #set ($flag_syarat=$senarai.flag_syarat)
        #set ($ukuran_luas=$senarai.flag_penerima_pampasan)
        #set ($amaun_pampasan=$senarai.flag_bahagian_pampasan)
        #set ($terima_pampasan=$senarai.flag_ukur_luas)
        #set ($umpuk_pampasan=$senarai.flag_pampasan)
        #set ($txdTkhAward=$senarai.tarikh_terima_award)
        #set ($txtPengambilanNo=$senarai.no_siasatan)
        #set ($txtAmaunTuntutan=$senarai.amaun_tuntutan)
        #set ($id_siasatan=$senarai.id_siasatan) 
        #set ($desc_status_bantahan_ap=$senarai.desc_status_bantahan_ap)    
        #set ($txtMaklumatBantahanTamat=$senarai.maklumat_bantahan_tamat_tempoh) 
    #end

<body>
	<form name="f1">
		<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BUTIRAN TERPERINCI PERMOHONAN</font></legend>			
			<table border="0" cellpadding="1" cellspacing="1" align="center">
				<tr>
					<td colspan="">
					<fieldset>
					<legend>Maklumat Pembantah</legend>
					    <table width="100%" border="0"> 
					        <tr>
					          <td width="1%"></td>
					          <td width="29%">Nama Kementerian</td>
					          <td>:</td>
					          <td colspan="2"><input type="text" name="txtNamaPembantah" id="txtNamaPembantah" value="$!txtNamaPembantah" size="70" class="disabled" tabindex="12" />
					          <input type="hidden" name="txtIdKementerian" id="txtIdKementerian" value="$!txtIdKementerian" /></td>
					        </tr>
					        <tr>
					          <td width="1%"></td>
					          <td>Nama Agensi</td>
					          <td>&nbsp;</td>
					          <td colspan="2"><input type="text" name="txtNamaAgensi" id="txtNamaAgensi" value="$!txtNamaAgensi" size="70" tabindex="12" class="disabled" readonly /></td>
					        </tr>
					        <tr>
					          <td width="1%"></td>
					          <td>Alamat</td>
					          <td>:</td>
					          <td colspan="2"><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="70" class="disabled" readonly tabindex="12" /></td>
					        </tr>  
					        <tr>
					            <td width="1%"></td>
					            <td>&nbsp;</td>
					            <td></td>
					            <td colspan="2"><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="70" class="disabled" readonly tabindex="13" /></td>
					        </tr> 
					        <tr>
					            <td width="1%"></td>
					            <td>&nbsp;</td>
					            <td></td>
					            <td colspan="2"><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="70" class="disabled" readonly tabindex="14" /></td>
					        </tr> 
					        <tr>
					          <td width="1%"></td>
					          <td>Poskod</td>
					          <td>:</td>
					          <td colspan="2"><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" class="disabled" readonly tabindex="15" /></td>
					        </tr>
					        <tr>
					          <td width="1%"></td>
					          <td>Negeri</td>
					          <td>:</td>
					          <td colspan="2"><input type="text" name="txtNamaNegeri" id="txtNamaNegeri" value="$!txtNamaNegeri" size="50" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly="readonly" tabindex="14" />
					          <input type="hidden" name="txtIdNegeri" id="txtIdNegeri" value="$!txtIdNegeri" /></td>
					        </tr>
        				</table>
					</fieldset>				
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>	
				<tr>
					<td colspan="">				
					<fieldset id="senarai_dokumen" >
					<legend>Senarai Dokumen Yang Disertakan</legend>
					    <table width="100%">
					  <tr class="table_header">
					    <td width="5%">Bil</td>
					    <td width="30%">Nama Dokumen</td>
					    <td width="30%">Keterangan</td>
					    <td width="30%">Dokumen Sokongan</td>
					     #if($listDokumen_size > 0)
<!-- 					      <td width="5%">					     
					      <div align="center">
					      <input type="checkbox" name="all1" id="all1" onclick="doCheckAll1()" title="Semak untuk pilih semua" />
					      </div>
					      
					      </td> -->
					      #end
					  </tr>
					 					  
					 #if($listDokumen_size > 0)
					  #foreach($list1 in $listDokumen)        
					           
					             #set( $i = $velocityCount )
					         		#if ( ($i % 2) != 1 )
					              		 #set( $row = "row2" )
					         		#else
					               		 #set( $row = "row1" )
					         		#end
					  <tr>  
					    <td class="$row" >$list1.BIL</td>
					    <td class="$row" >$list1.TAJUK</td>
					    <td class="$row" >$list1.KETERANGAN</td>
					    <td class="$row" >$list1.NAMA_FAIL</td>   
					  </tr>
					  #end
					  #else
					  <tr>  
					    <td colspan="4">Tiada Rekod</td>    
					  </tr>
					  #end
						</table>
					  
					</fieldset>				
					</td>
				</tr>	
				
				<tr>
					<td></td>
				</tr>
				
				<tr>
					<td>
					<fieldset><legend>MAKLUMAT MAHKAMAH</legend>
						<table width="100%" border="0">
    						<tr>
      							<td width="1%"></td>
					          	<td width="29%">Nama Mahkhamah</td>
					          	<td>:</td>
					          	<td>$!socmt</td>
							</tr>
						</table>
					</fieldset>
					</td>
				</tr>
				
<!-- 				<tr>
					<td align="right">No. Petisyen</td>
					<td>:</td>
					<td colspan="2">
						<input type="hidden" name="idFail" value="$idFail">
						<input type="text" name="noPetisyen" value="$!noPetisyen" readonly="readonly" size="40" style="text-transform:uppercase;" />
						#if($!semakPermohonan=='ada')
							<br /><font color="#FF0000" size="1"><i>Permohonan semakan telah dihantar.</font>
						#end
					</td>
				</tr>
				
				<tr>
					<td align="right">Dokumen Sokongan</td>
					<td>:</td>
					
					
                    <td colspan="2"><input type="text" name="namaDokumen" value="$!namaDokumen" size="25" readonly="readonly" /></td>
					
					
					
				</tr>

				
				<tr>
					<td align="right">Tarikh Jana Borang B</td>
					<td>:</td>
					<td><input type="text" name="tarikhJanaBorangB" value="$!tarikhJanaBorangB" size="19" maxlength="10" readonly="readonly" /></td>
					<td align="left">
						<img border="0" width="16" height="18" src="../../img/calendar.gif" />
					</td>
				</tr> -->
				<tr>
					<td align="center">
						<input type="button" name="cetak" id="cetak" value="Hantar" align="center" onClick="javascript:hantarPermohonan('$idFail','$kodPejabat')" />
						<input type="button" name="tutup" id="tutup" value="Tutup" align="center" onClick="tutupTetingkap()" />
					</td>
				</tr>		

			</table>
		</fieldset>
<!-- 		<table border="0" cellpadding="1" cellspacing="1" width="100%">
			
			<tr>
				<td align="center">
				<input type="button" name="cetak" id="cetak" value="Cetak Maklumat Borang B" align="center" onClick="javascript:cetakPPKBorangB('$idFail','$kodPejabat')" />
				<input type="button" name="tutup" id="tutup" value="Tutup" align="center" onClick="tutupTetingkap()" />
				#set($applicatiType = "")
					#if ($!sendI == 'ada' && $!semakPermohonan == 'tiada' && $!Errormsg != 'Error1')
						<input type="button" name="hantar2" id="hantar2" value="Hantar" align="left" onClick="javascript:hantarPermohonan()" />
		      			#set($applicatiType = "I")
					#elseif ( $!semakPermohonan == 'ada' )
						#if ( $!sendI == 'tiada' ) 
							<input type="button" name="hantar" id="hantar" value="Hantar" align="left" onClick="javascript:hantarPermohonanPetioner()" />
							#set($applicatiType = "P")
						#end
					#end
					#if ($Errormsg == 'Error1')
					
					<input type="button" name="hantar2" id="hantar2" disabled value="Hantar" align="left" onClick="javascript:hantarPermohonan()" />
					<p align="center">
					<b><font color="red">Permohonan tidak boleh dihantar. Sila muatnaik dokumen sokongan terlebih dahulu.</font></b></p>
					#end
					<input type="hidden" name="applicationType" id="applicationType" value="$applicatiType">
					
					</td>
			</tr>
		</table> -->
		
    	<input type="hidden" name="namaPejabat" id="namaPejabat" value="$!nP">
		<input type="hidden" name="jenisKepentingan" id="jenisKepentingan" value="$!jenisKepentingan">
		<input type="hidden" name="idnegeri" id="idnegeri" value="$!idnegeri">
		<input type="hidden" name="jeniskp" id="jeniskp" value="$!jeniskp">
		<input type="hidden" name="successSend" id="successSend" value="$!successSend">
        <input type="hidden" name="idPermohonan" id="idPermohonan" value="$!idPermohonan">
		<input type="hidden" name="userid" id="userid" value='$!{session.getAttribute("_ekptg_user_id")}'/> 
		
	</form>
</body>
<script>
function hantarPermohonan() {
	
	var tarikhMati = document.f1.tarikhMati.value;
	

	if(document.f1.noPetisyen.value==""){
		alert('Sila pastikan No. Petisyen telah diisi');
		return;
	} else if(document.f1.namaSimati.value==""){
		alert('Sila pastikan Nama Simati telah diisi');
		return;
	} //else if(document.f1.noKPSimatiBaru.value==""){
		//alert('Sila pastikan MyId Simati telah diisi');
		//return;
	//} 
	else if(document.f1.tarikhMati.value==""){
		alert('Sila pastikan Tarikh Mati telah diisi');
		return;
	} else if(document.f1.namaPemohon.value==""){
		alert('Sila pastikan Nama Pemohon telah diisi');
		return;
	} else if(document.f1.noKPPemohon.value==""){
		alert('Sila pastikan Nama Pengenalan Pemohon telah diisi');
		return;
	} else if(document.f1.kodPejabat.value==""){
		alert('Kod Pejabat tiada data. Sila pastikan unit pejabat anda ditetapkan dengan betul supaya maklumat dihantar adalah tepat');
		return;
	} else {
		input_box = confirm("Sila pastikan butiran yang dihantar adalah tepat!");
		if (input_box == true) {
			var data = "&tarikhMati="+document.f1.tarikhMati.value+"&tarikhJanaBorangB="+document.f1.tarikhJanaBorangB.value+"&noPetisyen="+document.f1.noPetisyen.value;
			data = data+"&namaSimati="+document.f1.namaSimati.value+"&namaSimatiLain="+document.f1.namaSimatiLain.value;
			data = data+"&noKPSimatiBaru="+document.f1.noKPSimatiBaru.value+"&noKPSimatiLama="+document.f1.noKPSimatiLama.value;
			data = data+"&noKPSimatiLain="+document.f1.noKPSimatiLain.value+"&namaPemohon="+document.f1.namaPemohon.value;
			data = data+"&noKPPemohon="+document.f1.noKPPemohon.value+"&hubSimatiPemohon="+document.f1.hubSimatiPemohon.value;
			data = data+"&kodPejabat="+document.f1.kodPejabat.value+"&applicationType="+applicationType;
			data = data+"&idnegeri="+document.f1.idnegeri.value+"&jeniskp="+document.f1.jeniskp.value;
			data = data+"&idFail="+document.f1.idFail.value;
			//data = data+"&docContent="+document.f1.docContent.value;
			//alert("data ="+data);
			document.f1.method="post";
			document.f1.action="ekptg.view.ppk.FrmIntegrasiMT?command=hantarPermohonan";
			
			document.f1.submit();			
		}
	}
}

function hantarPermohonanPetioner() {
	if(document.f1.noPetisyen.value==""){
		alert('Sila pastikan No. Petisyen telah diisi');
		return;
	} else if(document.f1.namaSimati.value==""){
		alert('Sila pastikan Nama Simati telah diisi');
		return;
	} else if(document.f1.noKPSimatiBaru.value==""){
		alert('Sila pastikan MyId Simati telah diisi');
		return;
	} else if(document.f1.tarikhMati.value==""){
		alert('Sila pastikan Tarikh Mati telah diisi');
		return;
	} else if(document.f1.namaPemohon.value==""){
		alert('Sila pastikan Nama Pemohon telah diisi');
		return;
	} else if(document.f1.noKPPemohon.value==""){
		alert('Sila pastikan No. Pengenalan Pemohon telah diisi');
		return;
	} else if(document.f1.hubSimatiPemohon.value==""){
		alert('Sila pastikan Hubungan Pemohon dengan Simati telah diisi');
		return;
	} else if(document.f1.kodPejabat.value==""){
		alert('Kod Pejabat tiada data. Sila pastikan unit pejabat anda ditetapkan dengan betul supaya maklumat dihantar adalah tepat');
		return;
	} else {
		input_box = confirm("Sila pastikan butiran yang dihantar adalah tepat!");
		if (input_box == true) {
			document.f1.method="post";
			document.f1.action="ekptg.view.ppk.FrmIntegrasiMT?command=hantarPermohonanPetioner";
			document.f1.submit();			
		}
	}
}

function cetakPPKBorangB(idfail,kodPejabat) {
	

	var hantar = "0";
	
	if(document.f1.successSend.value=="ya"){
		hantar = "0";
	
	}else{
		hantar = "1";
	}
	
	   var param = "?template=PPKBorangB&hantar="+hantar+"&idfail="+idfail+"&kodpejabat="+kodPejabat+"&userlogin="+$!{session.getAttribute("_ekptg_user_id")};
		//var url = "../x/${securityToken}/ekptg.report.ppk.PPKBorangB?idfail="+idfail+"&kodPejabat="+kodPejabat;   
	    var url = "../../servlet/ekptg.report.ppk.PPKBorangB"+param; 
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
	function cetakBorangB2_A4(idFail) {
	    var url = "../../servlet/ekptg.report.ppk.PPKBorangB?template=BorangB2_A4&idfail="+idFail;  
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		
	}
	
function tutupTetingkap() {
	window.close();
}
</script>