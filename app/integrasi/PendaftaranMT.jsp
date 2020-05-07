<script type="text/javascript" src="../../library/js/CalendarPopup.js" ></script>
<script type="text/javascript" src="../../library/js/jquery-1.7.2.min.js"></script>
<!-- <script type="text/javascript" src="../../library/js/jquery.pstrength-min.1.2.js"></script> -->
<link rel="stylesheet" type="text/css" href="../../library/js/jquery.timepickr.css" />
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPT.css" />
 
#set($noPengenalanPemohon = "")

#set($jenisPengenalan = "")

#foreach ( $senarai in $getMaklumatBantahan )

	#set ($labelNoRef=$senarai.jenisPB)
	#set ($noRef=$senarai.noPB)
	#set ($jenisRef=$senarai.idJenisNoPB)
	#set ($txtNamaPembantah=$senarai.nama)
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
        #set ($txdTkhAward=$senarai.tarikh_terima_award)
        #set ($txtPengambilanNo=$senarai.no_siasatan)
        #set ($txtAmaunTuntutan=$senarai.amaun_tuntutan)
        #set ($id_siasatan=$senarai.id_siasatan) 
    #end
    
#set($labelPem = "Pembantah")
#set($labelResp = "Responden")

<body>
	<!-- <form name="f1"> -->
		<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BUTIRAN TERPERINCI PERMOHONAN</font></legend>			
			<table border="0" cellpadding="1" cellspacing="1" align="center">
				#if($!noKes != '')
				<tr>
					<td align="center"><font size="2"><b>Permohonan telah dihantar</b></font></td>
				</tr>
			 	#end
			 	<tr>
					<td align="left">No. Petisyen : $!noPetisyen </td>
				</tr>
				<tr>
					<td align="left">No. Kes : $!noKes </td>
				</tr>
				<tr>
					<td colspan="">
					<fieldset>
					<legend>Maklumat $!labelPem</legend>
					    <table width="100%" border="0"> 
					        <tr>
					          <td width="1%"></td>
					          <td width="29%">Nama</td>
					          <td>:</td>
					          <td colspan="2"><input type="text" name="txtNamaPembantah" id="txtNamaPembantah" value="$!txtNamaPembantah" size="50" class="disabled" tabindex="12" />
					          <input type="hidden" name="txtIdKementerian" id="txtIdKementerian" value="$!txtIdKementerian" /></td>
					        </tr>
 					        
 					        <tr>
					          <td width="1%"></td>
					          <td>$!labelNoRef</td>
					          <td>&nbsp;</td>
					          <td colspan="2"><input type="text" name="noRef" id="noRef" value="$!noRef" size="20" tabindex="12" class="disabled" readonly /></td>
					        </tr> 
					#if($jenisRef.equals("1") 
						|| $jenisRef.equals("11")
						|| $jenisRef.equals("3")
						|| $jenisRef.equals("4")
						|| $jenisRef.equals("5")
						|| $jenisRef.equals("6")) 
		       			 <tr>
					          <td width="1%"></td>
					          <td>Jantina</td>
					          <td>:</td>
					          <td colspan="2">
					          	<select name="jantina" id="jantina" class="mediumselect" style="text-transform:uppercase;" onblur="uppercase()">
                               		<option value="U" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jantina</option>
                                   	<option value="M" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                 	<option value="F" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                </select>
					          </td>
					        </tr>  
		       			 	<tr>
					          <td width="1%"></td>
					          <td>Umur</td>
					          <td>:</td>
					          <td colspan="2">
					          	<input type="number" name="umur" id="umur" value="$!txtUmur" size="3" maxlength="2" tabindex="12" />
					          </td>
					        </tr>         		
			 		#end
					        
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
					 <!--        <tr>
					            <td width="1%"></td>
					            <td>&nbsp;</td>
					            <td></td>
					            <td colspan="2"><input type="text" name="txtBandar" id="txtBandar" value="$!txtAlamat3" size="70" class="disabled" readonly tabindex="14" /></td>
					        </tr> --> 
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
					          <input type="hidden" name="txtIdNegeri" id="txtIdNegeri" value="$!id_negeri" /></td>
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
					<fieldset>
					<legend>Maklumat $!labelResp</legend>
					    <table width="100%" border="0"> 

					        <tr>
					          <td width="1%"></td>
					          <td width="29%">Nama</td>
					          <td>:</td>
					          <td colspan="2"><input type="text" name="txtNamaResp" id="txtNamaPembantah" value="$!txtNamaResp" size="50" class="disabled" tabindex="12" />
					          <input type="hidden" name="txtIdResp" id="txtIdResp" value="$!txtIdResp" /></td>
					        </tr>
					        <tr>
      							<td width="1%"></td>
					          	<td width="29%">PTG/PTD</td>
					          	<td>:</td>
					          	<td>$!socPejabat</td>
							</tr>					        
							<tr>
					          <td width="1%"></td>
					          <td>Alamat</td>
					          <td>:</td>
					          <td colspan="2"><input type="text" name="txtAlamatResp1" id="txtAlamatResp1" value="$!txtAlamatResp1" size="70" class="disabled" readonly tabindex="12" /></td>
					        </tr>  
					        <tr>
					            <td width="1%"></td>
					            <td>&nbsp;</td>
					            <td></td>
					            <td colspan="2"><input type="text" name="txtAlamatResp2" id="txtAlamatResp2" value="$!txtAlamatResp2" size="70" class="disabled" readonly tabindex="13" /></td>
					        </tr> 
					        <tr>
					            <td width="1%"></td>
					            <td>&nbsp;</td>
					            <td></td>
					            <td colspan="2"><input type="text" name="txtAlamatResp3" id="txtAlamatResp3" value="$!txtAlamat3Resp" size="70" class="disabled" readonly tabindex="14" /></td>
					        </tr> 
					        <tr>
					          <td width="1%"></td>
					          <td>Poskod</td>
					          <td>:</td>
					          <td colspan="2"><input type="text" name="txtPoskodResp" id="txtPoskodResp" value="$!txtPoskodResp" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" class="disabled" readonly tabindex="15" /></td>
					        </tr>
					        <tr>
					          <td width="1%"></td>
					          <td>Negeri</td>
					          <td>:</td>
					          <td colspan="2"><input type="text" name="txtNamaNegeriResp" id="txtNamaNegeriResp" value="$!txtNamaNegeriResp" size="50" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly="readonly" tabindex="14" />
					          <input type="hidden" name="txtIdNegeriResp" id="txtIdNegeriResp" value="$!id_negeriResp" /></td>
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
 					      <td width="5%">					     
					     #if($listDokumen_size > 0)
<!--					      <div align="center">
					      <input type="checkbox" name="all1" id="all1" onclick="doCheckAll1()" title="Semak untuk pilih semua" />
					      </div>
					      -->
					      #end
					      </td> 
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
					    <td class="$row" >
					    	<div align="center">
				       			<input type="checkbox" name="cb" id="ids1" onclick="" value="$list1.ID_DOKUMEN" >
				     		</div>
				     	</td>
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
					<fieldset><legend>Maklumat Mahkhamah</legend>
						<table width="100%" border="0">
    						<tr>
      							<td width="1%"></td>
					          	<td width="29%">Nama Mahkamah</td>
					          	<td>:</td>
					          	<td>$!socMT</td>
							</tr>				
							<div id="div_viewPerbicaraan"></div>
							
							<tr>
							<div id="divmahkhamah">
      							<td width="1%"></td>
					          	<td width="29%">Kod Mahkamah</td>
					          	<td>:</td>
					          	<td><input type="text" name="kodmt" id="kodmt" value="$!kodmt" class="disabled" readonly></td>
							</div>
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
					##if($!noKes == '')
						<input type="button" name="cetak" id="cetak" value="Hantar" onClick="javascript:hantarPermohonan()" />
					##end	
						<input type="button" name="tutup" id="tutup" value="Tutup" onClick="tutupTetingkap()" />
					</td>
				</tr>		

			</table>
		</fieldset>
		
    	<input type="hidden" name="namaPejabat" id="namaPejabat" value="$!nP">
		<input type="hidden" name="jenisKepentingan" id="jenisKepentingan" value="$!jenisKepentingan">
		<input type="hidden" name="idnegeri" id="idnegeri" value="$!idnegeri">
		<input type="text" name="jeniskp" id="jeniskp" value="$!jenisRef">
		<input type="hidden" name="successSend" id="successSend" value="$!successSend">
        <input type="hidden" name="idPermohonan" id="idPermohonan" value="$!idPermohonan">
		<input type="hidden" name="userid" id="userid" value='$!{session.getAttribute("_ekptg_user_id")}'/> 
		
	<!-- </form> -->
</body>
<script>
	
	var idBantahan = "&idbantahan=$!idBantahan";
	var idFail = "&idfail=$!idFail";
	var idHarta  = "&idharta=$!idHarta";
	var idPermohonan  = "&idpermohonan=$!idPermohonan";
	var idSiasatan  = "&idsiasatan=$!idSiasatan";
	var idWarta  = "&idwarta=$!idWarta";
	var params = idHarta+idPermohonan+idSiasatan+idWarta+idFail+idBantahan;


	function pilihMT(){
		//alert('pilihMT='+param);
		document.${formName}.method="post";
		document.${formName}.action="?"+params+"&mode=getmahkamah";	
		//document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
		//
		document.${formName}.command.value = "getmahkamah";	
		//
		document.${formName}.submit();
		//doDivAjaxCall${formName}('getmahkamah','divmahkhamah','mode=terimapohoncarian');		
		
	}

	function pilihPejabat(){
		//alert('pilihMT='+param);
		document.${formName}.method="post";
		document.${formName}.action="?"+params+"&mode=getpejabat";	
		document.${formName}.command.value = "getpejabat";	
		document.${formName}.submit();
		
	}

	function hantarPermohonan() {	
		var bilangan = 0; 
		
		if(document.${formName}.cb.length > 0){
			bilangan = document.${formName}.cb.length;
		}else{
			if(document.${formName}.cb.checked==true){
				bilangan = 1; 
			}
		}
		//alert(bilangan);
		if(document.${formName}.jeniskp.value == ""){
			alert('Sila pastikan maklumat Pembantah diisi.');
	  		//document.${formName}.umur.focus(); 
			return;	
			
		}
		
		if('$jenisRef' == '1' 
			||'$jenisRef' == '3' 
			||'$jenisRef' == '4' 
			||'$jenisRef' == '5' 
			||'$jenisRef' == '6' 
			||'$jenisRef' == '11' ){
			if(document.${formName}.jantina.value == "U"){
				alert('Sila pastikan maklumat Jantina $!labelPem telah dipilih.');
		  		document.${formName}.jantina.focus(); 
				return;			
			}else if(document.${formName}.umur.value == ""){
				alert('Sila pastikan maklumat Umur $!labelPem diisi.');
		  		document.${formName}.umur.focus(); 
				return;			
			}
			
		}
		
		if(document.${formName}.txtAlamat1.value == ""){
			alert('Sila pastikan maklumat Alamat $!labelPem diisi.');
	  		document.${formName}.txtAlamat1.focus(); 
			return; 
		}else if(document.${formName}.txtAlamat2.value == ""){
			alert('Sila pastikan maklumat Alamat $!labelPem  diisi.');
	  		document.${formName}.txtAlamat2.focus(); 
			return; 
		}else if(document.${formName}.txtPoskod.value == ""){
			alert('Sila pastikan maklumat Poskod $!labelPem diisi.');
	  		document.${formName}.txtPoskod.focus(); 
			return; 
		}else if(document.${formName}.txtNamaNegeri.value == ""){
			alert('Sila pastikan maklumat Negeri $!labelPem diisi.');
	  		document.${formName}.socNegeri.focus(); 
			return; 
		
		}else if(document.${formName}.txtAlamatResp1.value == ""){
			alert('Sila pastikan maklumat Alamat $!labelResp diisi.');
	  		document.${formName}.txtAlamatResp1.focus(); 
	  		return; 
		
		}else if(document.${formName}.txtAlamatResp2.value == ""){
			alert('Sila pastikan maklumat Alamat $!labelResp diisi.');
	  		document.${formName}.txtAlamatResp2.focus(); 
			return; 

		}else if(document.${formName}.txtPoskodResp.value == ""){
			alert('Sila pastikan maklumat Poskod $!labelResp diisi.');
	  		document.${formName}.txtPoskodResp.focus(); 
			return; 
		
		}else if(document.${formName}.txtNamaNegeriResp.value == ""){
			alert('Sila pastikan maklumat Negeri $!labelResp diisi.');
	  		document.${formName}.socNegeriResp.focus(); 
	  		return;			
		
		}else if(document.${formName}.kodmt.value == ""){
			alert('Sila pastikan Maklumat Mahkamah telah dipilih.');
			return;
		
		} else if(bilangan == 0){
			alert('Sila pilih Lampiran terlebih dahulu.');
			return; 
		
		} else {

			input_box = confirm("Sila pastikan maklumat yang dihantar adalah tepat!");
			if (input_box == true) {
/* 				var data = "&tarikhMati="+document.f1.tarikhMati.value+"&tarikhJanaBorangB="+document.f1.tarikhJanaBorangB.value+"&noPetisyen="+document.f1.noPetisyen.value;
				data = data+"&namaSimati="+document.f1.namaSimati.value+"&namaSimatiLain="+document.f1.namaSimatiLain.value;
				data = data+"&noKPSimatiBaru="+document.f1.noKPSimatiBaru.value+"&noKPSimatiLama="+document.f1.noKPSimatiLama.value;
				data = data+"&noKPSimatiLain="+document.f1.noKPSimatiLain.value+"&namaPemohon="+document.f1.namaPemohon.value;
				data = data+"&noKPPemohon="+document.f1.noKPPemohon.value+"&hubSimatiPemohon="+document.f1.hubSimatiPemohon.value;
				data = data+"&kodPejabat="+document.f1.kodPejabat.value+"&applicationType="+applicationType;
				data = data+"&idnegeri="+document.f1.idnegeri.value+"&jeniskp="+document.f1.jeniskp.value;
				data = data+"&idFail="+document.f1.idFail.value; */
				//data = data+"&docContent="+document.f1.docContent.value;
				//alert("data ="+data);
				document.${formName}.method="post";
				//document.${formName}.action="ekptg.view.ppk.FrmIntegrasiMT?command=hantarPermohonan";
				document.${formName}.command.value = "hantarpermohonan";	
				document.${formName}.action="?"+params+"&mode=getmahkamah";	
				document.${formName}.submit();			
				
			}
			
		}
	
	}
	
	function tutupTetingkap() {
		window.close();
	}
	
</script>