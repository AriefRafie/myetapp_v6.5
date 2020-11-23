<style type="text/css">
<!--
.style1 {
	color: #0000FF
}
#parse("css/eTapp_PHP.css")
-->
</style>
<input type="hidden" name="actionPopup"/>
<input type="hidden" name="idHakmilikAgensi"/>
<input type="hidden" name="idPemohon"/>
<input type="hidden" name="idFail"/>
<input type="hidden" name="paparDetail"/>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>CARIAN MAKLUMAT LESEN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td>No Lesen</td>
          <td>:</td>
          <td><input type="text" name="txtCarianNoLesen" id="txtCarianNoLesen" value=""></td>
        </tr>
        <!-- <tr>
          <td>No Fail</td>
          <td>:</td>
          <td>
            <input type="text" name="txtCarianNoFail" id="txtCarianNoFail" value=""></td>
        </tr> -->
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="doCarian()"/>
            <input type="button" name="cmdKosong" id="cmdKosong" value="Kosongkan" onclick="kosongkan()"/></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI MAKLUMAT LESEN</b></legend>
      #parse("app/utils/record_paging_popup.jsp")
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No Fail</strong></td>
          <td width="30%"><strong>Nama Syarikat</strong></td>
          <td width="10%"><strong>No Lesen</strong></td>
          <td width="10%"><strong>Kawasan Dipohon</strong></td>
          <td width="8%" align="center"><strong>Tarikh Terima</strong></td>
          <td width="10%"><strong>Status</strong></td>
        </tr>
        #set ($list = "")
	   	#if ($SenaraiFailSyarikat.size() > 0)
	  		#foreach ($list in $SenaraiFailSyarikat)
			  	#set( $i = $velocityCount )
			    #if ( ($i % 2) != 1 )
			    	#set( $row = "row2" )
			    #else
			    	#set( $row = "row1" )
			    #end
	        <tr>
	          <td class="$row" align="center">$list.bil</td>
	          ##<td class="$row"><a href="javascript:papar('$list.idFail','$list.idStatus')" class="style1">$list.noFail</a>
	          ##<br />
	          ##<font class="blink" ><span class="style2">$!list.statusLesen</span></font>
	          ##<font class="blink" ><span class="style2">$!list.statusKelulusanDasar</span></font></td>
	          <td class="$row"><a href="javascript:doPaparMaklumatDetail('$list.idFail')" class="style1">$list.noFail</a></td>
	          <td class="$row">$list.namaPemohon</td>
	          <td class="$row">$list.noLesen</td>
	          <td class="$row">$list.kawasanDipohon</td>
	          <td class="$row" align="center">$list.tarikhTerima</td>
	          <td class="$row">$list.status</td>
	        </tr>
	   		#end
	 	
	 	#else
	        <tr>
	          <td class="row1" align="center">&nbsp;</td>
	          <td class="row1">Tiada Rekod</td>
	          <td class="row1" align="center">&nbsp;</td>
	          ##<td class="row1">&nbsp;</td>
	          <td class="row1" align="center">&nbsp;</td>
	          <td class="row1">&nbsp;</td>
	        </tr>
	 	#end
      </table>
      </fieldset></td>
  </tr>
  #if ($paparDetail == 'true')
  <tr>
  <td>
 	<fieldset id="tableDetail" style="display:block;">
   		<legend><b>MAKLUMAT BERKAITAN</b></legend>
   		
     	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		 #if ($mode == 'view')
		 <tr>
		   <td colspan="2"><fieldset>
		     <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
		     <table width="100%" border="0" cellspacing="2" cellpadding="2">
		       #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
		       <tr>
		         <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
		         <td valign="top">Tarikh Terima</td>
		         <td>:</td>
		         <td><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" onblur="check_date(this);cekTarikhTerima(this)" size="11" $readonly class="$inputTextClass"/>
		           #if ($mode != 'view') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
		       </tr>
		       <tr>
		         <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
		         <td valign="top">Tarikh Surat</td>
		         <td>:</td>
		         <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat" onblur="check_date(this);cekTarikhSurat(this)" size="11" $readonly class="$inputTextClass"/>
		           <a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</td>
		       </tr>
		       <tr>
		         <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
		         <td valign="top">No. Rujukan Surat</td>
		         <td valign="top">:</td>
		         <td><input name="txtNoRujukanSurat" type="text" class="$inputTextClass" id="txtNoRujukanSurat"  value="$beanMaklumatPermohonan.noRujSurat" size="43" maxlength="250" $readonly />
		         </td>
		       </tr>
		       <tr>
		         <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
		         <td valign="top">Perkara</td>
		         <td valign="top">:</td>
		         <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass"  onkeyup="textCounter(this.form.sebabTamat,this.form.remLen1,$!saizPerkara);" onkeydown="textCounter(this.form.sebabTamat,this.form.remLen1,$!saizPerkara);">$beanMaklumatPermohonan.perkara</textarea></td>
		       </tr>
		       <tr>
		         <td width="1%">&nbsp;</td>
		         <td width="28%">Jenis Tujuan</td>
		         <td width="1%">:</td>
		         <td width="70%">MENGOREK</td>
		       </tr>
		      ## <tr>
		      ##   <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
		      ##   <td>Kaitan Tujuan</td>
		      ##   <td>:</td>
		      ##   <td>$selectTujuanKaitan</td>
		       ##</tr>
		     ##  <tr>
		     ##    <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
		     ##    <td valign="top">Tujuan Pengambilan</td>
		     ##    <td valign="top">:</td>
		      ##   <td valign="top"><textarea name="txtTujuanPengambilan" id="txtTujuanPengambilan" cols="43" rows="5" $readonly class="$inputTextClass"  onKeyUp="textCounter(this.form.txtTujuanPengambilan,this.form.remLen1,$!saizTxtTujuanPengambilan);" onKeyDown="textCounter(this.form.txtTujuanPengambilan,this.form.remLen1,$!saizTxtTujuanPengambilan);" >$beanMaklumatPermohonan.tujuanPengambilan</textarea></td>
		      ## </tr>
		       #if ($mode == 'update')
		       <tr>
		         <td valign="top">&nbsp;</td>
		         <td valign="top">&nbsp;</td>
		         <td valign="top">&nbsp;</td>
		         <td valign="top">Baki Aksara :&nbsp;
		           <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtTujuanPengambilan" /></td>
		       </tr>
		       #end
		        #end
		      </table>
		      </fieldset>
		      
			  <fieldset>
				<legend><strong>MAKLUMAT LESEN</strong></legend>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
		  		#if ($paparDetail == 'true')
			  		#foreach ($beanMaklumatSuratKelulusanLesenKepadaPemohon in $BeanMaklumatSuratKelulusanLesenKepadaPemohon)
			  		<tr>
				      <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
				      <td width="28%" valign="top">No Lesen</td>
				      <td width="1%" valign="top">:</td>
				      <td width="70%" valign="top"><input name="txtNoLesen" type="text" class="$inputTextClass" id="txtNoLesen" value="$beanMaklumatSuratKelulusanLesenKepadaPemohon.txtNoLesen" size="30" maxlength="100" $readonly/></td>
				    </tr>
			    	<tr>
				      <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
				      <td valign="top">Tarikh Mula</td>
				      <td valign="top">:</td>
				      <td valign="top"><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this);calcDate();" value="$beanMaklumatSuratKelulusanLesenKepadaPemohon.txtTarikhMula" size="9" maxlength="10" $readonly />
				    #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
					</tr>
					<tr>
						<td valign="top">&nbsp;</td>
						<td valign="top">Tempoh</td>
						<td valign="top">:</td>
						<td>$beanMaklumatSuratKelulusanLesenKepadaPemohon.txtTempohDiluluskan #if($beanMaklumatSuratKelulusanLesenKepadaPemohon.idTempoh=='1') Bulan #else Tahun #end
						   <input name="idTempoh" type="hidden" id="idTempoh" value="$!beanMaklumatSuratKelulusanLesenKepadaPemohon.idTempoh"/>
						   <input name="tempoh" type="hidden" id="tempoh" value="$!beanMaklumatSuratKelulusanLesenKepadaPemohon.txtTempohDiluluskan"/>
						</td>
				 	</tr>
					<tr>
					  <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
					  <td valign="top">Tarikh Tamat</td>
					  <td valign="top">:</td>
					  <td valign="top"><input name="txtTarikhTamat" type="text" class="$inputTextClass" id="txtTarikhTamat" onBlur="check_date(this)" value="$beanMaklumatSuratKelulusanLesenKepadaPemohon.txtTarikhTamat" size="9" maxlength="10" $readonly/>
					    #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end 
					  </td>
					</tr>
					<tr>
					  <td>&nbsp;</td>
					  <td>Fee Lesen</td>
					  <td>:</td>
					  <td>RM $beanMaklumatSuratKelulusanLesenKepadaPemohon.kadarFeeLesen          bagi setiap
					    $beanMaklumatSuratKelulusanLesenKepadaPemohon.kmPersegi
					    km persegi</td>
					</tr>
					<tr>
					  <td>&nbsp;</td>
					  <td>Jumlah Fee Lesen</td>
					  <td>:</td>
					  <td>RM
					    $beanMaklumatSuratKelulusanLesenKepadaPemohon.jumlahFeeLesen </td>
					</tr>
					<tr>
					  <td>&nbsp;</td>
					  <td>Kadar Royalti Pasir</td>
					  <td>:</td>
					  <td>RM
					    $beanMaklumatSuratKelulusanLesenKepadaPemohon.kadarRoyaltiPasir / meter padu</td>
					</tr>
					<tr>
					  <td>&nbsp;</td>
					  <td>Jumlah Royalti Keseluruhan</td>
					  <td>:</td>
					  <td>RM $beanMaklumatSuratKelulusanLesenKepadaPemohon.jumlahRoyaltiSeluruh</td>
					</tr>
					<tr>
					  <td>&nbsp;</td>
					  <td>Jumlah Pendahuluan Royalti</td>
					  <td>:</td>
					  <td>RM          $beanMaklumatSuratKelulusanLesenKepadaPemohon.jumDahuluRoyalti</td>
					</tr>
					<tr>
					  <td>&nbsp;</td>
					  <td>Luar Perairan Negeri</td>
					  <td>: </td>
					  <td>#if($beanMaklumatSuratKelulusanLesenKepadaPemohon.flagLuar=='1') Ya #else Tidak #end</td>
					</tr>
					<tr>
					  <td>&nbsp;</td>
					  <td>Negeri</td>
					  <td>: </td>
					  <td>$beanMaklumatSuratKelulusanLesenKepadaPemohon.namaNegeri</td>
					</tr>
					<tr>
					  <td>&nbsp;</td>
					  <td>Lokasi</td>
					  <td>: </td>
					  <td>$beanMaklumatSuratKelulusanLesenKepadaPemohon.lokasi</td>
					</tr>
					<tr>
					  <td>&nbsp;</td>
					  <td>Luas Kawasan</td>
					  <td>: </td>
					  <td>$beanMaklumatSuratKelulusanLesenKepadaPemohon.luas $beanMaklumatSuratKelulusanLesenKepadaPemohon.jenisLuas</td>
					</tr>
					#end
				
		  		#end
				</table>
				</fieldset>
				</td>
				</tr>
		  #end
		</table>
      	</fieldset>
  	</td>
  </tr>
  #end
</table>
<script>
function doCarian() {
	doAjaxCall${formName}("doCarian");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtCarianNoLesen.value = "";
	document.${formName}.txtCarianNoFail.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBPopupSejarahMaklumatLesen";
	document.${formName}.submit();
}

function doPaparMaklumatDetail(idFail) {
	document.${formName}.idFail.value = idFail;
	document.${formName}.paparDetail.value = "true";
	document.${formName}.actionPopup.value = "doPaparMaklumatDetail";
	document.${formName}.submit();
}
</script>
