<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>
<!--<br><br>-->
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
    </tr>
	<tr>
		<td>			

			#parse("app/htp/utiliti/frmIndexCarianHeader.jsp")
			
		            <tr>
		            	<td  width="29%"  scope="row" align="right">Nama Pemohon</td>
		              	<td width="1%">:</td>		              
		              	<td><input name="txtNamaPemohon" id="txtNamaPemohon" type="text" value="$!permohonan.getNoRujukanLain()" size="43" maxlength="50" style="text-transform:uppercase;" /></td>
		            </tr>            
            		<tr>
              			<td width="30%" height="24" scope="row" align="right">Tarikh Terima</td>
		        		<td width="1%">:</td>		              
              			<td width="70%">
              				<input size="11" maxlength="10" type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$!permohonan.getPermohonan().getTarikhTerima()" onblur="check_date(this)">
      						<a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
      					</td>
            		</tr>
			
			#parse("app/htp/utiliti/frmIndexCarianFooter.jsp")
		</td>
	</tr>

	<tr>
		<td>
			<fieldset><legend><strong>SENARAI FAIL</strong></legend>
				<input type="button" class="stylobutton100" name="add" value="Tambah"  onClick="fSFGA_Tambah()" >
				#parse("app/utils/record_paging.jsp")

  <table border="0" cellpadding="2" cellspacing="1" width="100%">
      <tr class="table_header">
        <td width="3%"><b>Bil.</b></td>
        <td width="20%"><b>No. Fail</b></td>
        <td width="37%"><b>Tajuk Fail</b></td>
        <td width="17%"><b>Negeri</b></td>
        <td width="23%"><b>Status</b></td>
      </tr>	
      #set ($count = 0)
      #foreach ( $fail in $SenaraiFail )
      ##foreach ( $fail in $lists )
      	
      #set ($count = $count+1)
          #set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
      <tr>
        <td class="$row">$fail.bil.</td>
        <td class="$row"><a href="javascript:fSFGA_seterusnya('$fail.id', '$fail.no')" class="pautanms">$fail.no</a></td>
        <td class="$row">$fail.tajuk</td>
        <td class="$row">$fail.negeri</td>
        <td class="$row">$fail.keterangan</td>
      </tr>
      #end
      #if ($count == 0)
      <tr>
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
  </table>
  <!--
  <input type="hidden" name="idFail">
  <input type="hidden" name="noFail">
-->
 
  
</fieldset>

		</td>
	</tr>
</table>
  
<script>
	
/** SKRIN SENARAI FAIL */
	// [Cari] 
	function carianFail(){
		document.${formName}.flagAdvSearch.value = "Y";
		doAjaxCall${formName}("");
		
	}
	//[SelectedItem]
	function cmdChangeNegeriCarian(){
		carianFail();
	}

	//[SelectedItem]
	function cmdChangeDaerahCarian(){
		carianFail();
	}	
	
	//[SelectedItem]
	function cmdChangeKementerianCarian(){
		carianFail();
	}
	
	// [Kosongkan] 
	function cancel() {
		document.${formName}.reset();
	}
	
	// [Tambah] 
	function fSFGA_Tambah() {
		doAjaxCall${formName}('FailBaru');
	}
		// Skrin Fail Baru
		
		//[SelectedItem]
		function doChangeKementerian() {
			doAjaxCall${formName}('doChangeKementerian');
		}
	
		//[SelectedItem]
		function doChangeUrusan(){
			doAjaxCall${formName}('FailBaru','mode=doChangeUrusan');
		
		}
		// [Simpan] 
		function fGS2A_Simpan() {
			if(document.${formName}.socNegeri.value == ""){
				alert('Sila pilih " Negeri " terlebih dahulu.');
		  		document.${formName}.socNegeri.focus(); 
				return; 
			}
			
			if(document.${formName}.socSuburusan.value == ""){
				alert('Sila pilih " Urusan " terlebih dahulu.');
		  		document.${formName}.socSuburusan.focus(); 
				return; 
			}
			if(document.${formName}.socTajuk.value == ""){
				alert('Sila pilih " Tajuk " terlebih dahulu.');
		  		document.${formName}.txtTajuk.focus(); 
				return; 
			}
			
			if(document.${formName}.txtNoFailSek.value == ""){
				alert('Sila masukkan No. Fail terlebih dahulu.');
		  		document.${formName}.txtNoFailSek.focus(); 
				return; 
			}
			
			
			if ( !window.confirm("Anda Pasti?") ) return;
				doAjaxCall${formName}('FailBaru','mode=simpan');
		}
		// [Kosongkan] 
		// [Kembali] 
		function fGS2A_Kembali() {
			document.${formName}.action = "";
			document.${formName}.command.value = "";
			document.${formName}.submit();
	
		}
	
		function fGS2A_Seterusnya() {
			doAjaxCall${formName}('Semakan','mode=baru');
		}
	
		function fGS2A_Kemaskini() {
			doAjaxCall${formName}("FailBaru","mode=kemaskini");
		}
	
		function fGS2A_Batal() {
		//fir	document.f2.action = "";
			if(document.${formName}.idFail.value == ""){
				//fir document.f2.mode.value = "";
		//		doAjaxCall${formName}("mode=kemaskini");
			
			}else{
				doAjaxCall${formName}("FailBaru","mode=view");
			}
		}


	// [link] Senarai Fail
	function fSFGA_seterusnya(id, no) {
		doAjaxCall${formName}('SenaraiPermohonan','idFail='+id+'&noFail='+no);
	}


/** SKRIN SENARAI PERMOHONAN */

	// [Cari] 
	// [Kosongkan] 
	// [Tambah] 
	
	// [Kembali] 
	function fGSPA_Kembali() {
		document.${formName}.namaskrin.value = "";
		doAjaxCall${formName}("");
		
	}
	// [link] Senarai Permohonan
	
	// [Seterusnya] 
	function cmdSenaraiHakmilik(id) {
		doAjaxCall${formName}("PenHakmilik","idPermohonan="+id);
	}
	
	//function senaraiSuratDGHO(id){
	function senaraiSuratDGHO(id,idPermohonan){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}
	
	// 2010/05/31 -Pilih Pegawai untuk tandatangan surat
	function openSuratPegawai(urli,param,laporan,tem){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
	    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();	
	}

	function fGPHA_seterusnya(idHakmilik) {
		document.${formName}.idHakmilikurusan.value = idHakmilik;
		doAjaxCall${formName}("Hakmilik","mode=hakmilikview");
		
	}
	
	

/** SKRIN SENARAI HAKMILIK */

	// SKRIN SENARAI HAKMILIK, [Tambah] 
	function cmdDaftarHakmilik() {
		doAjaxCall${formName}("PendaftaranHakmilik","mode=baru");		
	}
		
		// SKRIN SENARAI HAKMILIK, Skrin Hakmilik, [Simpan] 
		function fGPHA_Simpan() {
			if(document.${formName}.socDaerah.value == ""){
				alert('Sila pilih " Daerah " terlebih dahulu.');
		  		document.${formName}.socDaerah.focus(); 
				return; 
			}
			if(document.${formName}.socMukim.value == ""){
				alert('Sila pilih " Bandar/Pekan/Mukim " terlebih dahulu.');
		  		document.${formName}.socMukim.focus(); 
				return; 
			}
			if(document.${formName}.socJenisHakmilik.value == ""){
				alert('Sila pilih " Jenis Hakmilik " terlebih dahulu.');
		  		document.${formName}.socJenisHakmilik.focus(); 
				return; 
			}
			if(document.${formName}.txtNoHakmilik.value == ""){
				alert('Sila masukkan " No. Hakmilik " terlebih dahulu.');
		  		document.${formName}.txtNoHakmilik.focus(); 
				return; 
			}
			if(document.${formName}.socLot.value == ""){
				alert('Sila pilih " Kod Lot/No. " terlebih dahulu.');
		  		document.${formName}.socLot.focus(); 
				return; 
			}	
			/*if(document.${formName}.txtKodLot.value == ""){
				alert('Sila masukkan " Kod Lot/No. " terlebih dahulu.');
		  		document.${formName}.txtKodLot.focus(); 
				return; 
			}*/
			if(document.${formName}.txtNoLot.value == ""){
				alert('Sila masukkan " No. Lot " terlebih dahulu.');
		  		document.${formName}.txtNoLot.focus(); 
				return; 
			}
		
			if(document.${formName}.txdTarikhTerima.value == ""){
				alert('Sila masukkan " Tarikh Terima Hakmilik " terlebih dahulu.');
		  		document.${formName}.txdTarikhTerima.focus(); 
				return; 
			}
			/*
			if(document.${formName}.socLuas.value == ""){
				alert('Sila pilih " Unit Luas " terlebih dahulu.');
		  		document.${formName}.socLuas.focus(); 
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
			if(document.${formName}.txdTarikhLuput.value == ""){
				alert('Sila masukkan " Tarikh Luput " terlebih dahulu.');
		  		document.${formName}.txdTarikhLuput.focus(); 
				return; 
			}
			if(document.${formName}.txtLuas.value == ""){
				alert('Sila masukkan " Luas " terlebih dahulu.');
		  		document.${formName}.txtLuas.focus(); 
				return; 
			}
			if(document.${formName}.txdTarikhWarta.value == ""){
				alert('Sila masukkan " Tarikh Warta " terlebih dahulu.');
		  		document.${formName}.txdTarikhWarta.focus(); 
				return; 
			}
			if(document.${formName}.txtNoWarta.value == ""){
				alert('Sila masukkan " No. Warta " terlebih dahulu.');
		  		document.${formName}.txtNoWarta.focus(); 
				return; 
			}
			*/
		
			 if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
				if(document.${formName}.socLuas.value == "4"){
					document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"E,"+document.${formName}.txtLuas3.value+"R,"+document.${formName}.txtLuas4.value+"P";
				}else if(document.${formName}.socLuas.value == "7"){
					document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas5.value +"E,"+document.${formName}.txtLuas6.value+"D";
				}else if(document.${formName}.socLuas.value == "8"){
					document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"R,"+document.${formName}.txtLuas3.value+"J,"+document.${formName}.txtLuas4.value+"K";
				}
			 }else{
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value;
			 }
			
			if ( !window.confirm("Anda Pasti?") ) return;
			//doAjaxCall${formName}("PenHakmilik","mode=simpan");
			doAjaxCall${formName}("PendaftaranHakmilik","mode=simpan");
			
		}

		//[SelectedItem]
		function doChangeDaerah(){
			//doAjaxCall${formName}("PenHakmilik","mode=doChangeDaerah");
			doAjaxCall${formName}("PendaftaranHakmilik","mode=doChangeDaerah");
		}
	
		//[SelectedItem]
		function doChangeDaerahKemaskini(){
			//doAjaxCall${formName}("PenHakmilik","mode=doChangeDaerahKemaskini");
			doAjaxCall${formName}("PendaftaranHakmilik","mode=doChangeDaerahKemaskini");
		}
		
		//SKRIN SENARAI HAKMILIK, Skrin Hakmilik, [Kosongkan] 
		function fGPHA_Batal() {
			document.${formName}.reset();		
		}
		
		//SKRIN SENARAI HAKMILIK, Skrin Hakmilik, [Kembali] 
		function senaraiHakmilik() {
			doAjaxCall${formName}("PenHakmilik");
		}
		

	// SKRIN SENARAI HAKMILIK, [Kembali] 
		function kembaliPermohonan(id, no) {
			doAjaxCall${formName}('SenaraiPermohonan','idFail='+id+'&noFail='+no);
		}
		
	// SKRIN SENARAI HAKMILIK, [link] 
	function viewHakmilik(id) {
		document.${formName}.idHakmilikurusan.value = id;
		doAjaxCall${formName}("PendaftaranHakmilik");
	
	}
	
		// SKRIN SENARAI HAKMILIK, Skrin Hakmilik, [Kemaskini] 
		function kemaskiniHakmilik() {
			doAjaxCall${formName}("PendaftaranHakmilik","mode=kemaskini");
		}
			// SKRIN SENARAI HAKMILIK, Skrin Hakmilik, selepas [Kemaskini], [Simpan] 		
			// SKRIN SENARAI HAKMILIK, Skrin Hakmilik, selepas [Kemaskini], [Batal] 
			// guna fungsi viewHakmilik(id)
			
		// SKRIN SENARAI HAKMILIK, Skrin Hakmilik, [Hapus] 
		function hapusHakmilik() {
			//document.${formName}.mode.value = id;
			doAjaxCall${formName}("PendaftaranHakmilik","mode=hapus");
		
		}	

		// SKRIN SENARAI HAKMILIK, Skrin Hakmilik, [Kembali] 
		// Senarai Hakmilik, guna fungsi senaraiHakmilik()
		
	// SKRIN SENARAI HAKMILIK, [Seterusnya] 

	//LAIN
	function kiraLuas(idLuas){
	  var jenisLuas = idLuas;
	  // KILOMETER PERSEGI
	  if(jenisLuas == "1"){
	  	var luasK = (document.${formName}.txtLuas1.value);
		var luasH = luasK*100;
	  	document.${formName}.txtLuas.value = luasH;
	   }
	   else
	   //HEKTER
	    if(jenisLuas == "2"){
	  	var luasH = (document.${formName}.txtLuas1.value);
	  	document.${formName}.txtLuas.value = luasH;
	   }
	   else
	   // METER PERSEGI
	   if(jenisLuas == "3"){
	  	  var luasM = document.${formName}.txtLuas1.value;
	  	  var luasH = (luasM*0.0001);
		  document.${formName}.txtLuas.value = luasH.toFixed(5);
	   }
	   else
	   //EKAR, ROOD, POLE
	   if(jenisLuas == "4"){
	  	  var luasE = document.${formName}.txtLuas2.value;
		  var luasR = document.${formName}.txtLuas3.value;
		  var luasP = document.${formName}.txtLuas4.value;
		  var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
	  	  document.${formName}.txtLuas.value = luasH.toFixed(5);
	   }
	   else
	   //KAKI PERSEGI
	   if(jenisLuas == "5"){
	  	  var luasAsal = document.${formName}.txtLuas1.value;
		  var luasK = luasAsal*0.0000092;
	  	  document.${formName}.txtLuas.value = luasK.toFixed(5);
	   }
	   else
	   //EKAR,DEPA
	   if(jenisLuas == "7"){
	  	  var luasE = document.${formName}.txtLuas5.value;
		  var luasD = document.${formName}.txtLuas6.value;
		  
		  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
	  	  document.${formName}.txtLuas.value = luasH.toFixed(5);
	   }
	    else
	   //RELONG,JEMBA,KAKI PERSEGI
	   if(jenisLuas == "8"){
	  	  var luasR = document.${formName}.txtLuas2.value;
		  var luasJ = document.${formName}.txtLuas3.value;
		  var luasK = document.${formName}.txtLuas4.value;
		  
		  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
	  	  document.${formName}.txtLuas.value = luasH.toFixed(5);
	   }
	}
	
	function doChangeKodLuas(t) {
		var command = 'PendaftaranHakmilik';
		var mode = '';
		var tabId = 0;
		var tabmode = t;
		var dochange = "doChangeKodLuas";
		doAjaxCall${formName}(command,'mode=$pagemode'+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&doChange='+dochange);
	
	}
	
	function doChangeKodLuasUpdate(t) {
		var command = 'PendaftaranHakmilik';
		var mode = 'kemaskini';
		var tabId = 0;
		var tabmode = t;
		var dochange = "doChangeKodLuas";
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&doChange='+dochange);
	
	}
	
	function validateCurrency(elmnt,content) {
		//if it is character, then remove it..
		if (isNaN(content)) {
			elmnt.value = RemoveNonNumeric(content);
			return;
		}
	}
	
	function RemoveNonNumeric( strString )
	{
	      var strValidCharacters = "1234567890.,";
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
	
	//semakan Tarikh semasa
	function checkDate(inputfield) {
		var today = new Date();
		
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
	
	function fGHA_SimpanHakmilik() {
		if(document.${formName}.txtNama.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.${formName}.txtNama.focus(); 
			return; 
		}
		if(document.${formName}.txtAlamat1.value == ""){
			alert('Sila masukkan " Alamat " terlebih dahulu.');
	  		document.${formName}.txtAlamat1.focus(); 
			return; 
		}
		if(document.${formName}.txtPoskod.value == ""){
			alert('Sila masukkan " Poskod " terlebih dahulu.');
	  		document.${formName}.txtPoskod.focus(); 
			return; 
		}
		if(document.${formName}.socADaerah.value == ""){
			alert('Sila pilih " Daerah " terlebih dahulu.');
	  		document.${formName}.socADaerah.focus(); 
			return; 
		}
		if(document.${formName}.socANegeri.value == ""){
			alert('Sila pilih " Negeri " terlebih dahulu.');
	  		document.${formName}.socANegeri.focus(); 
			return; 
		}
		if(document.${formName}.txtNoPerserahan.value == ""){
			alert('Sila masukkan " Gadaian Pendua No. Perserahan " terlebih dahulu.');
	  		document.${formName}.txtNoPerserahan.focus(); 
			return; 
		}
		if(document.${formName}.txtjilid.value == ""){
			alert('Sila masukkan " Gadaian Pendua No. Jilid " terlebih dahulu.');
	  		document.${formName}.txtjilid.focus(); 
			return; 
		}
		if(document.${formName}.txtfolio.value == ""){
			alert('Sila masukkan " Gadaian Pendua No. Folio " terlebih dahulu.');
	  		document.${formName}.txtfolio.focus(); 
			return; 
		}	
	
		if ( !window.confirm("Anda Pasti?") ) return;
			doAjaxCall${formName}("Hakmilik","mode=simpanHakmilik");
	
	}
	
	function fGHA_KemaskiniHakmilik() {
		doAjaxCall${formName}("Hakmilik","mode=kemaskiniHakmilik");
	}

	function fGHA_BatalHakmilik() {
		doAjaxCall${formName}("Hakmilik","mode=hakmilikview");
	}
	
</script> 

