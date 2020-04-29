<script type="text/javascript" src="../../library/js/CalendarPopup.js" ></script>
<link rel="stylesheet" type="text/css" href="../../library/js/jquery.timepickr.css" />
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPK.css" />

<style type="text/css">
<!-- 
input[readonly] {
	background-color:#AAD198;
	border:#546F0E 1px solid;
	color:#000;
}
-->
</style>

#set($courtLocation="")
#set($noPengenalanPemohon = "")

#set($jenisPengenalan = "")
#foreach($listMaklumat in $listMaklumatMahkamah)
    
    #set($nama_pejabat = $listMaklumat.nama_pejabat)
    #set($alamat1 = $listMaklumat.alamat1)
    #set($alamat2 = $listMaklumat.alamat2)
    #set($alamat3 = $listMaklumat.alamat3)
    #set($poskod = $listMaklumat.poskod)
    #set($no_tel = $listMaklumat.no_tel)
    #set($no_fax = $listMaklumat.no_fax)
    #set($id_Daerah_Mahkamah = $listMaklumat.daerah)
    #set($id_Negerimahkamah = $listMaklumat.negeri)
    
#end

#if ($nama_pejabat == 'Mahkamah Tinggi Muar')
	#set($courtLocation= '64')
#elseif ($nama_pejabat == 'Mahkamah Tinggi Alor Setar')
	#set($courtLocation= '2')
#elseif ($nama_pejabat == 'Mahkamah Tinggi Kota Bharu')
	#set($courtLocation= '91')
#elseif ($nama_pejabat == 'Mahkamah Tinggi Melaka')
	#set($courtLocation= '60')	
#elseif ($nama_pejabat == 'Mahkamah Tinggi Taiping')
	#set($courtLocation= '19')	
#elseif ($nama_pejabat == 'MAHKAMAH TINGGI KUALA LUMPUR')
	#set($courtLocation= '51')	
#elseif ($nama_pejabat == 'Mahkamah Tinggi Kangar')
	#set($courtLocation= '1')	
#elseif ($nama_pejabat == 'Mahkamah Tinggi Shah Alam')
	#set($courtLocation= '33')
#elseif ($nama_pejabat == 'Mahkamah Tinggi Kuala Terengganu')
	#set($courtLocation= '84')
#elseif ($nama_pejabat == 'Mahkamah Tinggi Kuantan')
	#set($courtLocation= '74')
#elseif ($nama_pejabat == 'Mahkamah Tinggi Pulau Pinang')
	#set($courtLocation= '13')
#elseif ($nama_pejabat == 'Mahkamah Tinggi Ipoh')
	#set($courtLocation= '18')
#elseif ($nama_pejabat == 'Mahkamah Tinggi Seremban')
	#set($courtLocation= '52')
#elseif ($nama_pejabat == 'Mahkamah Tinggi Temerloh')
	#set($courtLocation= '75')
#end

<body>
	<form name="f1">
		<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BUTIRAN TERPERINCI PERMOHONAN</font></legend>
			<table border="0" cellpadding="1" cellspacing="1" align="center">
				<tr>
					<td align="right">No Petisyen</td>
					<td>:</td>
					<td colspan="2">
						<input type="hidden" name="idFail" value="$idFail">
						<input type="text" name="noPetisyen" value="$!noPetisyen" readonly="readonly" size="40" style="text-transform:uppercase;" />
						<!-- #if($!semakPermohonan=='ada') -->
							<!-- <br /><font color="#FF0000" size="1"><i>Permohonan semakan telah dihantar.</font> -->
						<!-- #end -->
					</td>
				</tr>
				<tr>
					<td align="right">Nama Simati</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="namaSimati" value="$!namaSimati" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">Nama Lain Simati</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="namaSimatiLain" value="$!namaSimatiLain" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">MyID Simati</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="noKPSimatiBaru" value="$!noKPSimatiBaru" size="25" maxlength="14" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">I.C. Lama Simati</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="noKPSimatiLama" value="$!noKPSimatiLama" size="25" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">No. Pengenalan Lain Simati</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="noKPSimatiLain" value="$!noKPSimatiLain" size="25" readonly="readonly" /></td>
				</tr>
				
				<!-- Tambah supporting documents -->	
				<tr>
					<td align="right">Jenis Pengenalan Simati</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="jenisPengenalanSimati" value="$!jeniskp" size="25" readonly="readonly" /></td>
				</tr>
				
				<tr>
					<td align="right">Dokumen Sokongan</td>
					<td>:</td>
					
					
                    <td colspan="2"><input type="text" name="namaDokumen" value="$!namaDokumen" size="25" readonly="readonly" /></td>
					
					
					
				</tr>
				
				<!--  -->
				
						
				<tr>
					<td align="right">Tarikh Mati</td>
					<td>:</td>
					<td width="20px"><input type="text" name="tarikhMati" value="$!tarikhMati" size="30" maxlength="30" readonly="readonly" /></td>
					<td align="left">
					<!-- 
					#if ($!semakPermohonan == 'tiada')
						<a href="javascript:displayDatePicker('tarikhMati',false,'dmy');" /><img border="0" width="16" height="18" src="../../img/calendar.gif" /></a>
					#else
					
					#end
					 -->
						<!-- <img border="0" width="16" height="18" src="../../img/calendar.gif" /> -->
					</td>
				</tr>
				<tr>
					<td align="right">Nama Perayu</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="namaPerayu" value="$!namaPerayu" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">No. Pengenalan Perayu</td>
					<td>:</td>
					<td colspan="2">
					#if ($noKPBaruPemohon != "")
						#set($noPengenalanPerayu = $noKPBaruPerayu)
						#set($jenisPengenalan = "( MyID )")
					#elseif ($noKPLamaPerayu != "")
						#set($noPengenalanPerayu = $noKPLamaPerayu)
						#set($jenisPengenalan = "( I.C. Lama )")
					#elseif ($noKPLainPerayu != "")
						#set($noPengenalanPerayu = $noKPLainPemohon)
						#set($jenisPengenalan = "( Lain-Lain )")
								
					#end
					<input type="text" name="noKPBaruPerayu" value="$!noPengenalanPerayu" size="25" readonly="readonly" />&nbsp;&nbsp;$!jenisPengenalan</td>
					
					<!-- 
					#if ($!noKPBaruPemohon != "")
						<input type="text" name="noKPPemohon" value="$!noKPBaruPemohon" size="25" maxlength="14" readonly="readonly" />&nbsp;&nbsp;(MyID)</td>
					#elseif ($!noKPLamaPemohon != "")
						<input type="text" name="noKPPemohon" value="$!noKPLamaPemohon" size="25" readonly="readonly" />&nbsp;&nbsp;(I.C. Lama)</td>
					#elseif ($!noKPLainPemohon != "")
						<input type="text" name="noKPPemohon" value="$!noKPLainPemohon" size="25" readonly="readonly" />&nbsp;&nbsp;(Lain-lain)</td>	
					#end
					 -->	
				</tr>
				<tr>
					<td align="right">Alamat Perayu</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="alamat1Perayu" value="$!alamat1Perayu" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right"></td>
					<td>:</td>
					<td colspan="2"><input type="text" name="alamat2Perayu" value="$!alamat2Perayu" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right"></td>
					<td>:</td>
					<td colspan="2"><input type="text" name="alamat3Perayu" value="$!alamat3Perayu" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">Poskod</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="poskodPerayu" value="$!poskodPerayu" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">Bandar</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="bandarPerayu" value="$!bandarPerayu" size="40" style="text-transform:uppercase;" readonly="readonly" />
					<input type="hidden" name="idbandarPerayu" value="$!idbandarPerayu" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">Negeri</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="negeriPerayu" value="$!negeriPerayu" size="40" style="text-transform:uppercase;" readonly="readonly" />
					<input type="hidden" name="idnegeriPerayu" value="$!idnegeriPerayu" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				
				
				<tr>
					<td align="right">Mahkamah</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="namaMahkamah" value="$!namaMahkamah" size="40" style="text-transform:uppercase;" readonly="readonly" />
				
				<input type="hidden" name="idMahkamah" value="$!idMahkamah" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				
				
				</tr>		
				
				
			</table>
		</fieldset>
		<table border="0" cellpadding="1" cellspacing="1" width="100%">
		
		 
			<!-- <tr>
				<td align="left">
					<font color="#FF0000" size="1"><i>Perhatian</font>
					<font size="1"> : Keputusan semakan yang dihantar ke Mahkamah Tinggi akan mengambil masa sekurang-kurangnya 2 hari.</i></font>
				</td>
			</tr> -->
			   <input type="hidden" name="userid" id="userid" value='$!{session.getAttribute("_ekptg_user_id")}'/> 
			
			<tr>
				<td align="center">
				<!-- #if ( $!successSend.equals("ya")){
				<input type="button" name="cetak" id="cetak" value="Cetak" align="center" onclick="javascript:cetakPPKBorangB('$idFail','$kodPejabat')" />
				#else
				<input type="button" name="cetak" id="cetak" value="Cetak" align="center" onclick="javascript:cetakPPKBorangB('$idFail','$kodPejabat')" />
				#end -->
				
				<!-- <input type="button" name="cetak" id="cetak" value="Cetak" align="center" onClick="javascript:cetakBorangB2_A4('$idFail')" /> 
				<input type="button" name="cetak" id="cetak" value="Cetak Maklumat Borang B" align="center" onClick="javascript:cetakPPKBorangB('$idFail','$kodPejabat')" /> -->
				<input type="button" name="tutup" id="tutup" value="Tutup" align="center" onClick="tutupTetingkap()" />
					
               		<!--  
                    #if ($!semakPermohonan == 'tiada' )
                    #if ( $!isExistPetioner == 'tiada' )
						<input type="button" name="hantar2" id="hantar2" value="Hantar" align="left" onClick="javascript:hantarPermohonan()" />
		      			<input type="hidden" name="applicationType" id="applicationType" value="I">
					#end
                    #end
					
					#if ( $!semakPermohonan == 'ada' ) 
					#if ( $!isExistPetioner ==  'ada' )
						<input type="button" name="hantar" id="hantar" value="HantarPetioner" align="left" onClick="javascript:hantarPermohonanPetioner()" />
						<input type="hidden" name="applicationType" id="applicationType" value="P">
					#end
					#end				
					-->
					
						<input type="button" name="hantar2" id="hantar2" value="Hantar" align="left" onClick="javascript:hantarPermohonanRayuan()" />
		      			
					#if ($Errormsg == 'Error1')
					
					<input type="button" name="hantar2" id="hantar2" disabled value="Hantar" align="left" onClick="javascript:hantarPermohonan()" />
					<p align="center">
					<b><font color="red">Permohonan tidak boleh dihantar. Sila muatnaik dokumen sokongan terlebih dahulu.</font></b></p>
					#end
					<input type="hidden" name="applicationType" id="applicationType" value="$applicatiType">
					<!-- <input type="text" name="docContent" id="docContent" value="$docContent"> -->
					
					</td>
			</tr>
		</table>
		
    <input type="hidden" name="namaPejabat" id="namaPejabat" value="$!nP">
		<input type="hidden" name="jenisKepentingan" id="jenisKepentingan" value="$!jenisKepentingan">
		<input type="hidden" name="idnegeri" id="idnegeri" value="$!idnegeri">
		<input type="hidden" name="jeniskp" id="jeniskp" value="$!jeniskp">
		<input type="hidden" name="successSend" id="successSend" value="$!successSend">
        <input type="hidden" name="idPermohonan" id="idPermohonan" value="$!idPermohonan">
		
	</form>
</body>
<script>
function hantarPermohonanRayuan() {
	
	var tarikhMati = document.f1.tarikhMati.value;
	

	if(document.f1.noPetisyen.value==""){
		alert('Sila pastikan No. Petisyen telah diisi');
		return;
	} else if(document.f1.namaSimati.value==""){
		alert('Sila pastikan Nama Simati telah diisi');
		return;
	} 
	else if(document.f1.tarikhMati.value==""){
		alert('Sila pastikan Tarikh Mati telah diisi');
		return;
	} else {
		input_box = confirm("Sila pastikan butiran yang dihantar adalah tepat!");
		if (input_box == true) {

			document.f1.method="post";
			document.f1.action="ekptg.view.ppk.FrmIntegrasiMT?command=hantarPermohonanRayuan2";
			
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