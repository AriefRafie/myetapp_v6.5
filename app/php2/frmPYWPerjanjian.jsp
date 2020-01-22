<script type="text/javascript" src="../../library/js/report.js" ></script>
<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idUrusan" type="hidden" id="idUrusan" value="$idUrusan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
  <input name="idPerjanjian" type="hidden" id="idPerjanjian" value="$idPerjanjian"/>
  <input name="idMaklumbalas" type="hidden" id="idMaklumbalas" value="$idMaklumbalas"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idKeputusan" type="hidden" id="idKeputusan" value="$idKeputusan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="modeT" type="hidden" id="modeT" value="$modeT"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="actionPenyewaan" type="hidden" id="actionPenyewaan" value="$actionPenyewaan"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>
  <input name="noSambungan" type="hidden" id="noSambungan" value="$noSambungan"/>
  <input name="step" type="hidden" id="step" value="$step"/>
  <input name="afterTamatSewa" type="hidden" id="afterTamatSewa" value="$afterTamatSewa"/>
  <input name="idPermohonanSewa" type="hidden" id="idPermohonanSewa" value="$idPermohonanSewa"/>
  <input name="flagSebabTamat" type="hidden" id="flagSebabTamat"/>
  <input type="hidden" name="savePeringatan" value="$!savePeringatan">
</p>
<body onLoad = $onload >

#if ($errMsg != "")
<div class="info"><strong>$errMsg</strong></div>
#end

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '' && $idKeputusan != 'T' && ($idStatus == '1610214' || $idStatus == '1610195' || $idStatus == '1610221' || $idStatus == '1610222'))
  <tr>
    <td> #parse("app/php2/frmPYWHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;<div class="warning">SILA PILIH FAIL DI SENARAI TUGASAN TERLEBIH DAHULU</div></td>
  </tr>
  #else
  #foreach($beanHeader in $BeanHeader)
  <tr>
    <td>&nbsp;<div class="warning">FAIL INI MASIH DI STATUS <strong>$beanHeader.status</strong></div></td>
  </tr>
  #end
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if ($idFail != '' && $idKeputusan != 'T' && ($idStatus == '1610214' || $idStatus == '1610195' || $idStatus == '1610221' || $idStatus == '1610222'))
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PERJANJIAN</li>
        <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMBALAS</li>
      </ul>
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
          <table width="100%" border="0" cellspacing="2" cellpadding="2">
            #foreach ($beanMaklumatPerjanjian in $BeanMaklumatPerjanjian)
            <tr>
              <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
              <td width="28%">No. Siri Perjanjian</td>
              <td width="1%">:</td>
              <td width="70%"><input type="text" name="txtNoSiri" id="txtNoSiri" value="$beanMaklumatPerjanjian.noSiri" onBlur="this.value=this.value.toUpperCase();" $readonly class="$inputTextClass"/>
                &nbsp;
                #if ($idStatus == '1610214')
                #if ($beanMaklumatPerjanjian.noSiri == '') <span class="style1"><strong><blink> Sila Kemaskini Nombor Siri Perjanjian.</blink></strong></span> #else <span class="style1"><strong><blink> Sila Tekan Butang Seterusnya Untuk Mengaktifkan Perjanjian.</blink></strong></span> #end
                #end 
              </td>
            </tr>
            <tr>
              <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
              <td>Tarikh Mula Perjanjian</td>
              <td>:</td>
              <td><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this);calcDate()" value="$beanMaklumatPerjanjian.tarikhMula" size="9" maxlength="10" $readonly />
                #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
            </tr>
            <tr>
              <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
              <td>Tempoh</td>
              <td>:</td>
              <td><input type="text" name="txtTempoh" id="txtTempoh" size="1" maxlength="2" value="$beanMaklumatPerjanjian.tempoh" onBlur="validateNumber(this,this.value,'$beanMaklumatPerjanjian.tempoh');calcDate()" $readonly class="$inputTextClass">
                Bulan</td>
            </tr>
            <tr>
              <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
              <td>Tarikh Tamat Perjanjian</td>
              <td>:</td>
              <td><input name="txtTarikhTamat" type="text" class="$inputTextClass" id="txtTarikhTamat" onBlur="check_date(this);calcDate()" value="$beanMaklumatPerjanjian.tarikhTamat" size="9" maxlength="10" $readonly />
                #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
            </tr>
            #if($idSuburusan != '27')
            <tr>
              <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
              <td>Kadar Sewa (RM)</td>
              <td>:</td>
              <td><input name="txtKadarSewa" type="text" value="$beanMaklumatPerjanjian.kadarSewa" $readonly class="$inputTextClass" onBlur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.kadarSewa');calcCagaran()" /></td>
            </tr>
            #elseif($idSuburusan == '27')
            <tr>
              <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
              <td>Royalti  (RM)</td>
              <td>:</td>
              <td><input name="txtRoyalti" type="text" value="$beanMaklumatPerjanjian.royalti" $readonly class="$inputTextClass" onBlur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.royalti');calcRoyalti()" id="txtRoyalti" /></td>
            </tr>
            #end
            <tr>
              <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
              <td>Cagaran (RM)</td>
              <td>:</td>
              <td><input name="txtCagaran" type="text" value="$beanMaklumatPerjanjian.cagaran" $readonly class="$inputTextClass" onBlur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.cagaran');" /></td>
            </tr>
            #end
            #if ($mode != 'view')
            <tr>
              <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
            </tr>
            #end
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table>
          
          <table width="100%" border="0" cellspacing="2" cellpadding="2">
      	 	<tr>
              <td width="1%"></td>
              <td width="28%"></td>
          	  <td width="1%"></td>
          	  <td width="70%">
				<!-- <input value="$session.getAttribute('FLAG_FROM')" />  -->
				<!-- <input value="$idStatus" /> -->
				<!-- <input value="$flagAktif" />  -->
				<!-- <input value="$flagSebabTamat" />  -->
				<!-- <input value="$afterTamatSewa" />  -->
              	#if ($mode == 'view')
	            #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan')
	            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini()"/>
	            <input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat" onClick="javascript:setTable('tableSuratPeringatan')"/>
	            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
		        #if ($flagSebabTamat == '')
                <input type="button" name="cmdHantar" id="cmdHantar" value="Aktifkan Perjanjian" onClick="doHantarProses()" title="Untuk Aktifkan Perjanjian "/>
			    #end 
                #end
				#if ($session.getAttribute('FLAG_FROM') == 'failHQ')
	            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini()"/>
	            <input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat" onClick="javascript:setTable('tableSuratPeringatan')"/>
	          	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
	          	#if ($idStatus == '1610214') <!-- perjanjian -->
                <input type="button" name="cmdHantar" id="cmdHantar" value="Aktifkan Perjanjian" onClick="doHantarProses()" title="Untuk Aktifkan Perjanjian "/>
			    <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
			    #elseif ($idStatus == '1610195') <!-- perjanjian aktif -->
                #if($flagSebabTamat == '')
                <input type="button" name="cmdTamatSewa" value="Penamatan Penyewaan" onClick="javascript:setTable('tableTamatSewa')"/>
                #elseif ($flagSebabTamat == '')
                <input type="button" name="cmdSeterusnya" value="Ke Mesyuarat" onClick="gotoMesyuarat()" title="Untuk Ke Proses Mesyuarat"/>
                #end
                #end
				<!--#if ($session.getAttribute('FLAG_FROM') != 'failNegeri') -->
				<!--<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/> -->
				<!--#if ($flagSebabTamat == '')  -->
				<!--<input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya " onClick="doHantarProses()" title="Untuk Aktifkan Perjanjian "/> -->
				<!--#end  -->
				<!--#end -->
                #end
                #end 
                #if ($mode == 'update')
	            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniPerjanjian('$idSuburusan')"/>
	            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/>
                #end
                #if ($idStatus == '1610195' && $flagAktif == 'Y')
	            #if ($userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')
	            <input type="button" name="cmdSambung" id="cmdSambung" value="Daftar Sambungan" onClick="doDaftarSambung()" />
	            #end
                #end
                #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
                <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                #end 
                #if ($session.getAttribute('FLAG_FROM') == 'failNegeri')
	          	<input type="button" name="cmdKembali" value="Kembali" onClick="gotoSenaraiFailNegeri()"/>
	          	<input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat" onClick="javascript:setTable('tableSuratPeringatan')"/>
 	          	#if ($flagSebabTamat != 'Y')
 	          	<input type="button" name="cmdTamatSewa" value="Penamatan Penyewaan" onClick="javascript:setTable('tableTamatSewa')"/> 
                #end	 		
				#if ($idStatus == '1610214')
				<input type="button" name="cmdHantar" id="cmdHantar" value="Aktifkan Perjanjian" onClick="doHantarProses()" title="Untuk Aktifkan Perjanjian "/> 
			    #end    
	            <!-- #if ($userRole == '(PHP)PYWPenolongPegawaiTanahHQ' || $userRole == '(PHP)PYWPenolongPegawaiHQ' || $userRole == '(PHP)PYWPenolongPegawaiTanahNegeri') -->
				<!--#if ($flagSebabTamat == '') -->
				<!--<input type="button" name="cmdTamatSewa" value="Penamatan Penyewaan" onClick="javascript:setTable('tableTamatSewa')"/> -->
				<!--#end -->
				<!--#if($afterTamatSewa == 'afterTamatSewa') -->
				<!--<input type="button" name="cmdSeterusnya" value="Seterusnya" onClick="gotoMesyuarat()"/> -->
				<!--#end-->
	            <!-- #end -->	          		
	          	#end
	          </td>
              <td colspan="3">&nbsp;</td>          	
            </tr>
           	<tr hidden="true">
             <td width="1%"></td>
          	 <td width="28%"></td>
          	 <td width="1%"></td>
          	 <td width="70%">
               <input value="$session.getAttribute('FLAG_FROM')" />
               <input value="$idStatus" />
           	   <input value="$flagAktif" />
           	   <input value="$userRole" />
           	   <input value="$afterTamatSewa" />
               #if ($mode == 'view')
	           #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
	           <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini " onClick="kemaskini()"/>
	           <input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat" onClick="javascript:setTable('tableSuratPeringatan')"/>
		       #if ($idStatus == '1610214')
			   <input type="button" name="cmdHantar2" id="cmdHantar2" value="Seterusnya" onClick="doHantarProses()"/>
			   <input type="button" name="cmdBatalPermohonan2" id="cmdBatalPermohonan2" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
		       #end                  
	           #end
	           #if ($session.getAttribute('FLAG_FROM') != 'failNegeri')
               <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
               #end
               #if ($session.getAttribute('FLAG_FROM') == 'failHQ')
               #if ($idStatus == '1610195')
               #if ($flagSebabTamat == '')
               <input type="button" name="cmdTamatSewa" value="Penamatan Penyewaan" onClick="javascript:setTable('tableTamatSewa')"/>
               #end
               #end
               #end
               #end
               #if ($mode == 'update')
	           <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniMaklumbalas()"/>
	           <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/>
               #end
               #if ($idStatus == '1610195' && $flagAktif == 'Y')
	           #if ($userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')
	           <input type="button" name="cmdSambung" id="cmdSambung" value="Daftar Sambungan" onClick="doDaftarSambung()" />
	           #end
               #end
               #if ($idStatus == '1610195' && $flagAktif == 'Y')
	           #if ($!{session.getAttribute("FLAG_FROM")} == 'failHQ')
	           <input type="button" name="cmdSambung" id="cmdSambung" value="Daftar Sambungan" onClick="doDaftarSambung()" />
	           #end
               #end
               #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
               <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
               #end 
               #if ($session.getAttribute('FLAG_FROM') == 'failNegeri')
		       <input type="button" name="cmdKembali" value="Kembali" onClick="gotoSenaraiFailNegeri()"/>
		       <input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat" onClick="javascript:setTable('tableSuratPeringatan')"/>
		       #if ($userRole == '(PHP)PYWPenolongPegawaiTanahHQ' || $userRole == '(PHP)PYWPenolongPegawaiHQ' || $userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')
		       #if($afterTamatSewa == 'afterTamatSewa')
			   <!--<input type="button" name="cmdTamatSewa" value="Penamatan Penyewaan " onClick="javascript:setTable('tableTamatSewa')"/> -->
		       <input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat" onClick="javascript:setTable('tableSuratPeringatan')"/>
		       <input type="button" name="cmdSeterusnya" value="Seterusnya" onClick="()"/>
		       #end
		       #end
		       #end
             </td>
           </tr>
      	</table>
          
        <fieldset id="tableTamatSewa" style="display:none;">
		<legend><strong>MAKLUMAT PENAMATAN PERJANJIAN</strong></legend>
		<table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td width="1%"></td>
            <td width="28%">Sebab Tamat</td>
            <td width="1%">:</td>
          	<td width="70%">
          	  <select name="socSebabTamat" id="socSebabTamat" style="width:270px;" >
            	<option value="">SILA PILIH</option>
                <option value="01">Penyewa melanggar syarat penyewaan</option>
                <option value="02">KJP memerlukan tapak</option>
                <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
                <option value="04">Persetujuan bersama</option>
                <option value="05">Tukarguna</option>
                <option value="06">Lain-lain keadaan</option>
          	  </select>
            </td>
          </tr>
		  <!--#if ($flagSebabTamat != 'Y' && $userRole != '(PHP)PYWPenolongPegawaiTanahHQ') -->
		  <!--<tr> -->
		  <!--<td width="1%"></td> -->
		  <!--<td width="28%">Tarikh Surat Tamat</td> -->
		  <!--<td width="1%">:</td> -->
		  <!--<td width="70%"> -->
		  <!--<input type="text" name="txtTarikhSuratTamat" id="txtTarikhSuratTamat" size="9" onBlur="check_date(this)" /> -->
		  <!--<a href="javascript:displayDatePicker('txtTarikhSuratTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> -->
		  <!--</td>-->
		  <!--</tr>-->
		  <!--#end -->
          <tr>
            <td width="1%"></td>
          	<td width="28%">Catatan</td>
          	<td width="1%">:</td>
          	<td width="70%">
          		<textarea name="txtCatatanTamat" id="txtCatatanTamat" rows="3" cols="50" onblur="this.value=this.value.toUpperCase();" style="width: 270px;" ></textarea>
          	</td>
          </tr>        
          <tr>  
          	<td width="1%"></td>
          	<td width="28%"></td>
          	<td width="1%"></td>
          	<td width="70%">
  		       <input type="button" name="cmdSimpanTamatSewa" value="Simpan" onClick="doSimpanTamatSewa()"/>
    		   <input type="button" name="cmdBatalTamatSewa" value="Batal" onClick="javascript:setTable('tableTamatSewa')"/>
		    </td>
          </tr>
	    </table>
		</fieldset>

		#if ($flagSebabTamat == 'Y')
		<fieldset id="tableTamatSewa" >
		<legend><strong>MAKLUMAT PENAMATAN PERJANJIAN</strong></legend>
		<table width="100%" border="0" cellspacing="2" cellpadding="2">
  		#foreach ($beanMaklumatTamatSewa in $BeanMaklumatTamatSewa)
        <tr>
          <td width="1%"></td>
          <td width="28%">Sebab Tamat</td>
          <td width="1%">:</td>
          <td width="70%">
            <select name="socSebabTamat" id="socSebabTamat" style="width:270px;" $readonlyT class="$inputTextClassT">
          	#if ($beanMaklumatTamatSewa.idSebabTamat == '1')
          		<option value="">SILA PILIH</option>
                <option value="01" selected="selected">Penyewa melanggar syarat penyewaan</option>
                <option value="02">KJP memerlukan tapak</option>
                <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
                <option value="04">Persetujuan bersama</option>
                <option value="05">Tukarguna</option>
                <option value="06">Lain-lain keadaan</option>
            #elseif ($beanMaklumatTamatSewa.idSebabTamat == '2')
          		<option value="">SILA PILIH</option>
                <option value="01">Penyewa melanggar syarat penyewaan</option>
                <option value="02" selected="selected">KJP memerlukan tapak</option>
                <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
                <option value="04">Persetujuan bersama</option>
                <option value="05">Tukarguna</option>
                <option value="06">Lain-lain keadaan</option>
            #elseif ($beanMaklumatTamatSewa.idSebabTamat == '3')
          		<option value="">SILA PILIH</option>
                <option value="01">Penyewa melanggar syarat penyewaan</option>
                <option value="02">KJP memerlukan tapak</option>
                <option value="03" selected="selected">Tanah pelepasan (Tanah bukan milik KJP)</option>
                <option value="04">Persetujuan bersama</option>
                <option value="05">Tukarguna</option>
                <option value="06">Lain-lain keadaan</option>
            #elseif ($beanMaklumatTamatSewa.idSebabTamat == '4')
          		<option value="">SILA PILIH</option>
                <option value="01">Penyewa melanggar syarat penyewaan</option>
                <option value="02">KJP memerlukan tapak</option>
                <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
                <option value="04" selected="selected" >Persetujuan bersama</option>
                <option value="05">Tukarguna</option>
                <option value="06">Lain-lain keadaan</option>
            #elseif ($beanMaklumatTamatSewa.idSebabTamat == '5')
                <option value="">SILA PILIH</option>
                <option value="01">Penyewa melanggar syarat penyewaan</option>
                <option value="02">KJP memerlukan tapak</option>
                <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
                <option value="04">Persetujuan bersama</option>
                <option value="05" selected="selected">Tukarguna</option>
                <option value="06">Lain-lain keadaan</option>
            #elseif ($beanMaklumatTamatSewa.idSebabTamat == '6')
               	<option value="">SILA PILIH</option>
                <option value="01">Penyewa melanggar syarat penyewaan</option>
                <option value="02">KJP memerlukan tapak</option>
                <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
                <option value="04">Persetujuan bersama</option>
                <option value="05">Tukarguna</option>
                <option value="06" selected="selected" >Lain-lain keadaan</option>
            #else
              	<option value="" selected="selected">SILA PILIH</option>
                <option value="01">Penyewa melanggar syarat penyewaan</option>
                <option value="02">KJP memerlukan tapak</option>
                <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
                <option value="04">Persetujuan bersama</option>
                <option value="05">Tukarguna</option>
                <option value="06">Lain-lain keadaan</option>
            #end
          	</select>
          </td>
        </tr>
        #if ($flagSebabTamat != 'Y' && $userRole != '(PHP)PYWPenolongPegawaiTanahHQ')
        <tr>
          <td width="1%"></td>
          <td width="28%">Tarikh Surat Tamat</td>
          <td width="1%">:</td>
          <td width="70%">
          	<input type="text" name="txtTarikhSuratTamat" id="txtTarikhSuratTamat" size="9" onBlur="check_date(this)" value="$beanMaklumatTamatSewa.tarikhSuratTamat" $readonlyT class="$inputTextClassT"/>
            #if ($modeT != 'viewT') <a href="javascript:displayDatePicker('txtTarikhSuratTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end
          </td>
        </tr>        
        #end
        <tr>
          <td width="1%"></td>
          <td width="28%">Catatan</td>
          <td width="1%">:</td>
          <td width="70%">
          	<textarea name="txtCatatanTamat" id="txtCatatanTamat"  rows="3" cols="50" onblur="this.value=this.value.toUpperCase();" style="width: 270px;" $readonlyT class="$inputTextClassT">$beanMaklumatTamatSewa.catatanTamat</textarea>
          </td>
        </tr>        
        <tr>  
          <td width="1%"></td>
          <td width="28%"></td>
          <td width="1%"></td>
          <td width="70%">
           #if ($modeT != 'viewT')
  		   <input type="button" name="cmdSimpanTamatSewa" value="Simpan" onClick="doSimpanTamatSewa()"/>
  		   <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalT()"/>
		   #else
    	   <input type="button" name="cmdKemaskiniT" id="cmdKemaskiniT" value="Kemaskini " onClick="kemaskiniT()"/>
      	   #if ($flagSebabTamat == 'Y')
	       <input type="button" name="cmdSeterusnya" value="Ke Mesyuarat" onClick="gotoMesyuarat()"/>
	       #end 		
    	   #end     	 			
		  </td>
        </tr>
        #end
		</table>
		</fieldset>
		#end
        </div>
        
        <div class="TabbedPanelsContent">
          <table width="100%" border="0" cellspacing="2" cellpadding="2">
            #foreach($beanMaklumatMaklumbalas in $BeanMaklumatMaklumbalas)
            <tr>
              <td colspan="2"><fieldset>
              <legend>PENERIMAAN CAGARAN</legend>
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
              	<tr>
                  <td width="30%">Tarikh Terima</td>
                  <td width="70%">:
                    <input name="txtTarikhTerimaCagaran" type="text" class="$inputTextClass" id="txtTarikhTerimaCagaran" onBlur="check_date(this);" value="$beanMaklumatMaklumbalas.tarikhTerimaCagaran" size="9" maxlength="10" $readonly />
                    #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTerimaCagaran',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
                </tr>
                <tr>
                  <td>No Rujukan</td>
                  <td>:
                    <input type="text" name="txtNoRujukanCagaran" id="txtNoRujukanCagaran" value="$beanMaklumatMaklumbalas.noRujukanCagaran" onBlur="this.value=this.value.toUpperCase();" $readonly class="$inputTextClass"/></td>
                </tr>
                <tr>
                  <td>Status</td>
                  <td>:
                    <select name="socCagaran" id="socCagaran" style="width:140px;" $readonly class="$disabled" $disabled>
                    	#if ($beanMaklumatMaklumbalas.flagCagaran == 'L')
                        <option value="">SILA PILIH</option>
                        <option value="L" selected="selected">TERIMA</option>
                        <option value="T">TOLAK</option>
                        #elseif ($beanMaklumatMaklumbalas.flagCagaran == 'T')
                        <option value="">SILA PILIH</option>
                        <option value="L">TERIMA</option>
                        <option value="T" selected="selected">TOLAK</option>
                        #else
                        <option value="" selected="selected">SILA PILIH</option>
                        <option value="L">TERIMA</option>
                        <option value="T">TOLAK</option>
                       	#end
                    </select>
                  </td>
                </tr>
              </table>
              </fieldset></td>
            </tr>
            <tr>
              <td colspan="2"><fieldset>
                <legend>TANDATANGAN PERJANJIAN</legend>
                <table width="100%" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="30%">Tarikh Terima</td>
                    <td width="70%">:
                      <input name="txtTarikhTerimaTandatangan" type="text" class="$inputTextClass" id="txtTarikhTerimaTandatangan" onBlur="check_date(this);" value="$beanMaklumatMaklumbalas.tarikhTerimaTandatangan" size="9" maxlength="10" $readonly />
                      #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTerimaTandatangan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
                  </tr>
                  <tr>
                    <td>No Rujukan</td>
                    <td>:
                      <input type="text" name="txtNoRujukanTandatangan" id="txtNoRujukanTandatangan" value="$beanMaklumatMaklumbalas.noRujukanTandatangan" onBlur="this.value=this.value.toUpperCase();" $readonly class="$inputTextClass"/></td>
                  </tr>
                  <tr>
                    <td>Status</td>
                    <td>:
                      <select name="socTandatangan" id="socTandatangan" style="width:140px;" $readonly class="$disabled" $disabled>
                      	#if ($beanMaklumatMaklumbalas.flagTandatangan == 'L')
                        <option value="">SILA PILIH</option>
                        <option value="L" selected="selected">TERIMA</option>
                        <option value="T">TOLAK</option>
                        #elseif ($beanMaklumatMaklumbalas.flagTandatangan == 'T')
                        <option value="">SILA PILIH</option>
                        <option value="L">TERIMA</option>
                        <option value="T" selected="selected">TOLAK</option>
                        #else
                        <option value="" selected="selected">SILA PILIH</option>
                        <option value="L">TERIMA</option>
                        <option value="T">TOLAK</option>
                        #end
                      </select>
                    </td>
                  </tr>
                </table>
              </fieldset></td>
            </tr>
            <tr>
              <td colspan="2"><fieldset>
                <legend>SETEM HASIL</legend>
                <table width="100%" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="30%">Tarikh Terima</td>
                    <td width="70%">:
                      <input name="txtTarikhTerimaMatiSetem" type="text" class="$inputTextClass" id="txtTarikhTerimaMatiSetem" onBlur="check_date(this);" value="$beanMaklumatMaklumbalas.tarikhTerimaMatiSetem" size="9" maxlength="10" $readonly />
                      #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTerimaMatiSetem',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
                  </tr>
                  <tr>
                    <td>No Rujukan</td>
                    <td>:
                      <input type="text" name="txtNoRujukanMatiSetem" id="txtNoRujukanMatiSetem" value="$beanMaklumatMaklumbalas.noRujukanMatiSetem" onBlur="this.value=this.value.toUpperCase();" $readonly class="$inputTextClass"/></td>
                  </tr>
                  <tr>
                    <td>Status</td>
                    <td>:
                      <select name="socMatiSetem" id="socMatiSetem" style="width:140px;" $readonly class="$disabled" $disabled>
                      	#if ($beanMaklumatMaklumbalas.flagMatiSetem == 'L')
                        <option>SILA PILIH</option>
                        <option value="" >SILA PILIH</option>
                        <option value="L" selected="selected">TERIMA</option>
                        <option value="T">TOLAK</option>
                        #elseif ($beanMaklumatMaklumbalas.flagMatiSetem == 'T')
                        <option value="" >SILA PILIH</option>
                        <option value="L">TERIMA</option>
                        <option value="T" selected="selected">TOLAK</option>
                        #else
                        <option value="" selected="selected">SILA PILIH</option>
                        <option value="L">TERIMA</option>
                        <option value="T">TOLAK</option>
                        #end
                      </select>
                    </td>
                  </tr>
                </table>
              </fieldset></td>
            </tr>
            #end
            <tr>
              <td colspan="2">&nbsp;</td>
            </tr>
			<!--<tr> -->
			<!--#if ($mode == 'update') -->
			<!--<input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniMaklumbalas()"/> -->
			<!--<input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/> -->
			<!--#end</tr> -->
          </table>
          <table width="100%" border="0" cellspacing="2" cellpadding="2">
      		<tr>
			  <!--<td>&nbsp;</td> -->
              <td width="1%"></td>
              <td width="28%"></td>
              <td width="1%"></td>
              <td width="70%">
				<!-- <input value="$session.getAttribute('FLAG_FROM')" /> -->
				<!-- <input value="$idStatus" /> -->
				<!-- <input value="$flagAktif" /> -->
				<!-- <input value="$flagSebabTamat" /> -->
				<!-- <input value="$afterTamatSewa" /> -->
              	#if ($mode == 'view')
	            #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan')
	            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini " onClick="kemaskini()"/>
	            <input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat" onClick="javascript:setTable('tableSuratPeringatan')"/>
		        <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
		        <!-- #if ($flagSebabTamat == '')
                <input type="button" name="cmdSeterusnya" value="Seterusnya" onClick="gotoMesyuarat()" title="Untuk Ke Proses Mesyuarat"/>
                #end -->
                #end
                #if ($session.getAttribute('FLAG_FROM') == 'failHQ')
	            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini()"/>
	            <input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat " onClick="javascript:setTable('tableSuratPeringatan')"/>
	          	#if ($idStatus == '1610214') <!-- perjanjian -->
                <input type="button" name="cmdHantar" id="cmdHantar" value="Aktifkan Perjanjian" onClick="doHantarProses()" title="Untuk Aktifkan Perjanjian"/>
			    <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
			    #if ($flagSebabTamat == '')
               	<input type="button" name="cmdTamatSewa" value="Penamatan Penyewaan" onClick="javascript:setTable('tableTamatSewa')"/>
               	#end
                #elseif ($idStatus == '1610195') <!-- perjanjian aktif -->
                #if($flagSebabTamat == '')
                <input type="button" name="cmdTamatSewa" value="Penamatan Penyewaan" onClick="javascript:setTable('tableTamatSewa')"/>
                #elseif ($flagSebabTamat == '')
                <input type="button" name="cmdSeterusnya" value="Ke Mesyuarat" onClick="gotoMesyuarat()" title="Untuk Ke Proses Mesyuarat"/>
                #end
                #end
                #if ($session.getAttribute('FLAG_FROM') != 'failNegeri')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                #if ($flagSebabTamat == '')
                <input type="button" name="cmdHantar" id="cmdHantar" value="Aktifkan Perjanjian" onClick="doHantarProses()" title="Untuk Aktifkan Perjanjian"/>
			    #end 
                #end
                #end
                #end
                #if ($mode == 'update')
	            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniPerjanjian('$idSuburusan')"/>
	            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/>
                #end
                #if ($idStatus == '1610195' && $flagAktif == 'Y')
	            #if ($userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')
	            <input type="button" name="cmdSambung" id="cmdSambung" value="Daftar Sambungan" onClick="doDaftarSambung()" />
	            #end
                #end
                #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
                <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
                #end 
                #if ($session.getAttribute('FLAG_FROM') == 'failNegeri')
	          	<input type="button" name="cmdKembali" value="Kembali " onClick="gotoSenaraiFailNegeri()"/>
	          	<input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat" onClick="javascript:setTable('tableSuratPeringatan')"/>
	          	#if ($userRole == '(PHP)PYWPenolongPegawaiTanahHQ' || $userRole == '(PHP)PYWPenolongPegawaiHQ' || $userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')
				<!-- <input type="button" name="cmdTamatSewa" value="Penamatan Penyewaan" onClick="javascript:setTable('tableTamatSewa')"/> -->
				<!-- #if($afterTamatSewa == 'afterTamatSewa') -->
				<!-- <input type="button" name="cmdSeterusnya" value="Seterusnya" onClick="gotoMesyuarat()"/> -->
				<!-- #end -->
	            #end	          		
	          	#end
          	  </td>
              <td colspan="3">&nbsp;</td>          	
            </tr>
            <tr hidden="true">
              <td width="1%"></td>
              <td width="28%"></td>
              <td width="1%"></td>
              <td width="70%">
              	<input value="$session.getAttribute('FLAG_FROM')" />
              	<input value="$idStatus" />
              	<input value="$flagAktif" />
              	<input value="$userRole" />
              	<input value="$afterTamatSewa" />
                #if ($mode == 'view')
	            #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
	            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini " onClick="kemaskini()"/>
	            <input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat" onClick="javascript:setTable('tableSuratPeringatan')"/>
		        #if ($idStatus == '1610214')
			    <input type="button" name="cmdHantar2" id="cmdHantar2" value="Seterusnya" onClick="doHantarProses()"/>
			    <input type="button" name="cmdBatalPermohonan2" id="cmdBatalPermohonan2" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
		        #end                  
	            #end
	            #if ($session.getAttribute('FLAG_FROM') != 'failNegeri')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                #end
                #if ($session.getAttribute('FLAG_FROM') == 'failHQ')
                <input type="button" name="cmdTamatSewa" value="Penamatan Penyewaan" onClick="javascript:setTable('tableTamatSewa')"/>
                #end
                #end
                #if ($mode == 'update')
	            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniMaklumbalas()"/>
	            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/>
                #end
                #if ($idStatus == '1610195' && $flagAktif == 'Y')
	            #if ($userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')
	            <input type="button" name="cmdSambung" id="cmdSambung" value="Daftar Sambungan" onClick="doDaftarSambung()" />
	            #end
                #end
                #if ($idStatus == '1610195' && $flagAktif == 'Y')
	            #if ($!{session.getAttribute("FLAG_FROM")} == 'failHQ')
	            <input type="button" name="cmdSambung" id="cmdSambung" value="Daftar Sambungan" onClick="doDaftarSambung()" />
	            #end
                #end
                #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
                <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
                #end 
                #if ($session.getAttribute('FLAG_FROM') == 'failNegeri')
		        <input type="button" name="cmdKembali" value="Kembali" onClick="gotoSenaraiFailNegeri()"/>
		        <input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat" onClick="javascript:setTable('tableSuratPeringatan')"/>
		        #if ($userRole == '(PHP)PYWPenolongPegawaiTanahHQ' || $userRole == '(PHP)PYWPenolongPegawaiHQ' || $userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')
		        #if($afterTamatSewa == 'afterTamatSewa')
				<!-- <input type="button" name="cmdTamatSewa" value="Penamatan Penyewaan" onClick="javascript:setTable('tableTamatSewa')"/> -->
		        <input type="button" name="cmdHnatarPeringatan" value="Hantar Peringatan Tempoh Tamat" onClick="javascript:setTable('tableSuratPeringatan')"/>
		        <input type="button" name="cmdSeterusnya" value="Seterusnya" onClick="()"/>
		        #end
		        #end
		        #end
              </td>
            </tr>	
      	  </table>
        </div>
      </div>
    </td>
  </tr>
  #end
</table>

<fieldset id="tableSuratPeringatan" style="display:none;">
<legend><strong>SURAT PERINGATAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"></td>
          <td width="28%">Tarikh Hantar</td>
          <td width="1%">:</td>
          <td width="70%">
          	<input type="text" name="txtTarikhHantar" id="txtTarikhHantar" size="9" onBlur="check_date(this)" >
            <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
          </td>
        </tr>
        <tr> 
        <td></td>
        <td></td>
        <td></td>
         
          <td>
  		        <input type="button" name="cmdSimpanSuratPeringatan" value="Simpan" onClick="doSimpanSuratPeringatan()"/>
    			<input type="button" name="cmdBatalSuratPeringatan" value="Batal" onClick="javascript:setTable('tableSuratPeringatan')"/>
		   </td>
        </tr>
</table>

<table align="center" width="100%">
  <tr class="table_header">
    <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
    <td width="60%"><strong>Nama Dokumen</strong></td>
    <td width="10%" align="center"><strong>Tarikh Hantar</strong></td>
    <td width="15%"><strong>Status</strong></td>
    <td width="15%"></td>
  </tr>
  #set ($senaraiDokumen = "")
  #if ($SenaraiDokumen.size() > 0)
  #foreach ($senaraiDokumen in $SenaraiDokumen)
  #if ($senaraiDokumen.bil == '')
  #set( $row = "row1" )
  #elseif (($senaraiDokumen.bil % 2) != 0)
  #set( $row = "row1" )
  #else 
  #set( $row = "row2" )
  #end
  <tr>
    <td class="$row" align="center">$senaraiDokumen.bil</td>
<!--<td class="$row"><a href="javascript:paparDokumen($senaraiDokumen.idPerjanjian)" class="style1">Surat Peringatan Tamat Tempoh</a></td> -->
    <td class="$row">Surat Peringatan Tamat Tempoh</td>
    <td class="$row" align="center">$senaraiDokumen.tarikhHantar</td>
    <td class="$row">Telah Dihantar</td>
    <td class="$row"><a href="#" class="style2" onClick="javascript:cetakPYWNotisTamatTempoh('$idFail')"><img border="0" src="../img/print.gif"/></a></td>
  </tr>
  #end
  #else
  <tr>
    <td class="row1" align="center">&nbsp;</td>
    <td class="row1">Tiada Rekod</td>
    <td class="row1">&nbsp;</td>
    <td class="row1">&nbsp;</td>
    <td class="row1">&nbsp;</td>
  </tr>
  #end
</table>
</fieldset>



<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if($noSambungan == '' || $noSambungan == '0')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWKulitPerjanjianUtama('$idFail')">Kulit Perjanjian</a></td>
  </tr>
  #else
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWKulitPerjanjianSambungan('$idFail')">Kulit Perjanjian</a></td>
  </tr>
  #end  
  #if($idSuburusan == '36' || $idSuburusan == '26' || $idSuburusan == '28' || $idSuburusan == '29')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWPerjanjianTanah('$idFail','$idPerjanjian')">Perjanjian</a></td>
  </tr>
  #elseif($idSuburusan == '30')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWPerjanjianBahanBatuan('$idFail','$idPerjanjian')">Perjanjian</a></td>
  </tr>
  #elseif($idSuburusan == '27')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWPerjanjianKayuKayuan('$idFail','$idPerjanjian')">Perjanjian</a></td>
  </tr>
  #end
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWSuratPanggilTandatanganPerjanjian('$idFail','$idPermohonan')">Surat Tandatangan Perjanjian</a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWSuratMatiSetem('$idFail')">Surat Mati Setem</a></td>
  </tr>
</table>
</fieldset>

<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>
<script type="text/javascript">
#if ($idFail != '' && $idKeputusan != 'T' && ($idStatus == '1610214' || $idStatus == '1610195' || $idStatus == '1610221' || $idStatus == '1610222'))
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end

if(document.${formName}.savePeringatan.value == "true"){
	document.getElementById("tableSuratPeringatan").style.display="block";
}

</script>
<script>
function kemaskiniT() {
	document.${formName}.modeT.value = "updateT";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function cetakLaporan() {

	var reportfile = "PYWNotisTamatTempoh";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "ID_FAIL="+document.${formName}.idFail.value;
	
	printReport(reportfile,params);
}

function doSimpanSuratPeringatan() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.hitButton.value = "doSimpanSuratPeringatan";
// 	document.${formName}.submit();
	doAjaxCall${formName}("");
}

function gotoMaklumatPermohonan(){
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWMaklumatPermohonanView";
	document.${formName}.submit();
}
function gotoMesyuarat(){
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWMesyuaratView&hitButton=next&doPost=tru";
	document.${formName}.submit();
}

function doSimpanTamatSewa() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.modeT.value = "viewT";
	document.${formName}.hitButton.value = "doSimpanTamatSewa";
	doAjaxCall${formName}("");
}

function gotoSenaraiFailNegeri(){
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailNegeriView";
	document.${formName}.submit();
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function doChangeTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.modeT.value = "viewT";
	doAjaxCall${formName}("");
}

function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	content = content.replace(/,/g,'');
	content2 = content2.replace(/,/g,'');
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
function simpanKemaskiniPerjanjian(idSuburusan) {
	if(document.${formName}.txtNoSiri.value == ""){
		alert('Sila masukkan No Siri Perjanjian');
  		document.${formName}.txtNoSiri.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula Perjanjian');
		document.${formName}.txtTarikhMula.focus(); 
		return; 
	}
	if(document.${formName}.txtTempoh.value == ""){
		alert('Sila masukkan Tempoh Perjanjian');
		document.${formName}.txtTempoh.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTamat.value == ""){
		alert('Sila masukkan Tarikh Tamat Perjanjian');
		document.${formName}.txtTarikhTamat.focus(); 
		return; 
	}
	if(idSuburusan != '27'){
		if(document.${formName}.txtKadarSewa.value == ""){
			alert('Sila masukkan Kadar Sewa');
			document.${formName}.txtKadarSewa.focus(); 
			return; 
		}
	}
	else{
		if(document.${formName}.txtRoyalti.value == ""){
			alert('Sila masukkan Royalti');
			document.${formName}.txtRoyalti.focus(); 
			return; 
		}
	}
	if(document.${formName}.txtCagaran.value == ""){
		alert('Sila masukkan Cagaran');
		document.${formName}.txtCagaran.focus(); 
		return; 
	}	
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniPerjanjian";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMaklumbalas() {
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumbalas";
	doAjaxCall${formName}("");
}
function kemaskini() {
	document.${formName}.mode.value = "update";
	document.${formName}.modeT.value = "viewT";
	doAjaxCall${formName}("");
}
function batalT() {
	document.${formName}.modeT.value = "viewT";
	doAjaxCall${formName}("");
}

function batal() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doHantarProses(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarProses";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
function calcDate(){
	if (document.${formName}.txtTarikhMula.value != "" && document.${formName}.txtTempoh.value != ""){
		
		var tarikhMula  = document.${formName}.txtTarikhMula.value;
		var month  = parseInt(document.${formName}.txtTempoh.value);
		
		var dt1   = parseInt(tarikhMula.substring(0,2),10);
		var mon1  = parseInt(tarikhMula.substring(3,5),10)-1 + month;
		var yr1   = parseInt(tarikhMula.substring(6,10),10);
	 
		var myDate = new Date(yr1, mon1, dt1);
		myDate.setDate(myDate.getDate()-1);
		
		var day = myDate.getDate();
		var month = myDate.getMonth()+1;
		var year = myDate.getFullYear();
		
		var tarikhTamat = "";
		if(month>=10){
			if(day>=10){
				tarikhTamat = day + "/" + month + "/" + year;	
			} else {
				tarikhTamat = "0"+ day + "/" + month + "/" + year;	
			}				
		} else {
			if(day>=10){
				tarikhTamat = day + "/0" + month + "/" + year;	
			} else {
				tarikhTamat = "0"+ day + "/0" + month + "/" + year;	
			}
		}
		document.${formName}.txtTarikhTamat.value = tarikhTamat;
	
	} else {
		document.${formName}.txtTarikhTamat.value = "";
	}
}
function calcCagaran(){
	if (document.${formName}.txtKadarSewa.value != ""){
		
		var kadarSewa  = document.${formName}.txtKadarSewa.value*1;
		var cagaran  = 0;
		
		cagaran = kadarSewa * 3;
		
		document.${formName}.txtCagaran.value = cagaran.toFixed(2);
	
	} else {
		document.${formName}.txtCagaran.value = "";
	}
}
function calcRoyalti(){
	if (document.${formName}.txtRoyalti.value != ""){
		
		var royalti  = document.${formName}.txtRoyalti.value*1;
		var cagaran  = 0;
		
		cagaran = royalti * 10/100;
		
		document.${formName}.txtCagaran.value = cagaran.toFixed(2);
	
	} else {
		document.${formName}.txtCagaran.value = "";
	}
}
function doHantarProses(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarProses";
	document.${formName}.submit();
}
function doDaftarSambung(){
	document.${formName}.mode.value = "new";
	document.${formName}.actionPenyewaan.value = "daftarSambungan";
	document.${formName}.submit();
}
function cetakPYWKulitPerjanjianUtama(idFail) {
	var url = "../servlet/ekptg.report.php2.PYWKulitPerjanjianUtama?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWKulitPerjanjianSambungan(idFail) {
	var url = "../servlet/ekptg.report.php2.PYWKulitPerjanjianSambungan?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWPerjanjianTanah(idFail,idPerjanjian) {
	var url = "../servlet/ekptg.report.php2.PYWPerjanjianTanah?ID_FAIL="+idFail+"&ID_PERJANJIAN="+idPerjanjian;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWPerjanjianBahanBatuan(idFail,idPerjanjian) {
	var url = "../servlet/ekptg.report.php2.PYWPerjanjianBahanBatuan?ID_FAIL="+idFail+"&ID_PERJANJIAN="+idPerjanjian;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWPerjanjianKayuKayuan(idFail,idPerjanjian) {
	var url = "../servlet/ekptg.report.php2.PYWPerjanjianKayuKayuan?ID_FAIL="+idFail+"&ID_PERJANJIAN="+idPerjanjian;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratPanggilTandatanganPerjanjian(idFail,idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&idPermohonan="+idPermohonan+"&report=PYWSuratTandatanganPerjanjian";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWNotisTamatTempoh(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWNotisTamatTempoh";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratMatiSetem(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratMatiSetem";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function gotoSenaraiFail(){
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailView";
	document.${formName}.submit();
}

function gotoSenaraiFailKeseluruhan() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailKeseluruhanView";
	document.${formName}.submit();
}
</script>
