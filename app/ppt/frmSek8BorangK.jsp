
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset id="top">

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>




		
		#if($mode == "new")
		
		<fieldset style="width:60%">
		<legend><strong>Maklumat Borang K</strong></legend>
	
		<table width="100%" border="0">   
                <tr>
                	<td width="1%"><font color="red">*</font></td>
                	<td width="34%">Tarikh Borang K</td>
                	<td width="1%">:</td>
                	<td width="64%"><input name="txdTarikhBorangK" id="txdTarikhBorangK" size="11" type="text" value="$!tarikh_borangk" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangK',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr>
    			<!-- <tr>
    				<td>&nbsp;</td>
    				<td>Sumber Borang K</td>
    				<td>:</td>
    				<td><select name="sorSumberBorangK" style="width:150px">
      		
      					<option value="">SILA PILIH</option>		
		
					</select></td>
    			</tr> -->	
    			<tr>
    				<td>&nbsp;</td>
    				<td valign="top">Catatan</td>
    				<td valign="top">:</td>
    				<td valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="3" cols="30%" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,1500);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,1500);" ></textarea></td>
    			</tr>	
    	</table>
	
		</fieldset>
		
		<br/>
		
		<fieldset style="width:60%">
		<legend><strong>Maklumat Rekod Endosan Borang K</strong></legend>
	
		<table width="100%" border="0">
		
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="34%">No. Perserahan</td>
				<td width="1%">:</td>
				<td width="64%"><input type="text" name="txtPerserahan" id="txtPerserahan" value="" size="21" maxlength="30"   ></td>
			</tr>
			
			<!-- <tr>
				<td>&nbsp;</td>
				<td>Diterima Daripada</td>
				<td>:</td>
				<td><select name="sorTerima" style="width:150px">
      		
      				<option value="">SILA PILIH</option>		
		
				</select></td>
			</tr> -->
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Catatan Dibuat</td>
				<td>:</td>
				<td>
                
                
                
               
                	#set($tarikh_endorsan_default = "")
                    #if($!hash_maklumatEndorsan_from_etanah.TARIKH_ENDORSAN != "")   
                    #set($tarikh_endorsan_default = $!hash_maklumatEndorsan_from_etanah.TARIKH_ENDORSAN) 
                    #end  
                
                	
                
                
                <input name="txdTarikhCatatan" id="txdTarikhCatatan" size="11" type="text" value="$tarikh_endorsan_default" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhCatatan',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Masa Catatan Dibuat</td>
				<td>:</td>
				<td><input type="text" name="txtMasaCatatan" id="txtMasaCatatan" value="" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
					<select name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

                    	<option value="0">SILA PILIH</option>
                    	<option value="1">PAGI</option>
                    	<option value="2">TENGAHARI</option>
                    	<option value="3">PETANG</option>	
                    	
                    </select>&nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font></td>
			</tr>	
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Terima</td>
				<td>:</td>
				<td><input name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr>	
		</table>
		
		</fieldset>
		#end
		
		
		
		#if($mode == "view")
		
		#foreach($data in $dataBorangK)
			#set($txdTarikhBorangK = $data.tarikh_borangk)
			#set($txdTarikhTerima = $data.tarikh_terima)
			#set($txdTarikhCatatan = $data.tarikh_catatan)
			#set($txtMasaCatatan = $data.masa_catatan)
			#set($socJenisWaktu = $data.jenis_masa)
			#set($txtPerserahan = $data.no_perserahan)
			#set($txtCatatan = $data.catatan)
		#end
		
		#if($isEdit=="no")
			#set($disability = "readonly")
			#set($disabilityx = "class=disabled")
			#set($disability1 = "disabled")
			#set($M = "")
		#else
			#set($M = "*")
			#set($disability = "")
			#set($disabilityx = "")
			#set($disability1 = "")
		#end
		
<!-- PPT-30 (i) -->	
	
		<fieldset style="width:60%">
		<legend><strong>Maklumat Borang K</strong></legend>
		
		<table width="100%" border="0">   
                <tr>
                	<td width="1%"><font color="red">$M</font></td>
                	<td width="34%">Tarikh Borang K</td>
                	<td width="1%">:</td>
                	<td width="64%"><input $disability $disabilityx name="txdTarikhBorangK" id="txdTarikhBorangK" size="11" type="text" value="$!txdTarikhBorangK" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangK',false,'dmy');">&nbsp;$!frmtdate#end</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td valign="top">Catatan</td>
    				<td valign="top">:</td>
    				<td valign="top"><textarea $disability $disabilityx name="txtCatatan" id="txtCatatan" rows="3" cols="30%" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,1500);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,1500);" >$!txtCatatan</textarea></td>
    			</tr>	
    	</table>
    	
    	</fieldset>
    	
    	<br/>
		
		<fieldset style="width:60%">
		<legend><strong>Maklumat Rekod Endosan Borang K</strong></legend>
			
			<table width="100%" border="0">
			
				<tr>
					<td width="1%">&nbsp;</td>
					<td width="34%">No. Perserahan</td>
					<td width="1%">:</td>
					<td width="64%"><input $disability $disabilityx type="text" name="txtPerserahan" id="txtPerserahan" value="$!txtPerserahan" size="21" maxlength="30"   ></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Catatan Dibuat</td>
					<td>:</td>
					<td>
                    
                    #set($tarikh_endorsan_default = "")
                    #if($!hash_maklumatEndorsan_from_etanah.TARIKH_ENDORSAN != "")   
                    #set($tarikh_endorsan_default = $!hash_maklumatEndorsan_from_etanah.TARIKH_ENDORSAN) 
                    #end  
                     
                    <input $disability $disabilityx name="txdTarikhCatatan" id="txdTarikhCatatan" size="11" type="text" value="$!txdTarikhCatatan" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhCatatan',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Masa Catatan Dibuat</td>
					<td>:</td>
					<td><input $disability $disabilityx type="text" name="txtMasaCatatan" id="txtMasaCatatan" value="$!txtMasaCatatan" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
					<select $disability1 $disabilityx name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

						#if($socJenisWaktu=="1")
						<option value="1">PAGI</option>
                    	<option value="2">TENGAHARI</option>
                    	<option value="3">PETANG</option>	
                    	<option value="0">SILA PILIH</option>
						#elseif($socJenisWaktu=="2")
						<option value="2">TENGAHARI</option>
                    	<option value="1">PAGI</option>
                    	<option value="3">PETANG</option>	
                    	<option value="0">SILA PILIH</option>
						#elseif($socJenisWaktu=="3")
						<option value="3">PETANG</option>	
                    	<option value="1">PAGI</option>
                    	<option value="2">TENGAHARI</option>
                    	<option value="0">SILA PILIH</option>
						#else
						<option value="0">SILA PILIH</option>
                    	<option value="1">PAGI</option>
                    	<option value="2">TENGAHARI</option>
                    	<option value="3">PETANG</option>	
						#end
                    	
                    	
                    </select>&nbsp;#if($isEdit=="yes")<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font>#end</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Terima</td>
					<td>:</td>
					<td><input $disability $disabilityx name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
			</table>
		
		</fieldset>
    	
		#end
		
	
	<table width="58%" border="0">
		<tr align="center">
			<td>
			
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanBorangK('$!id_permohonan','$!id_hakmilik','$!id_borangk','$!mode')">
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
                
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBorangK('$!id_permohonan','$!id_hakmilik','$!id_borangk')">
                     
                     
                        #if($ID_NEGERIPROJEK == "4" || $ID_NEGERIPROJEK == "5")
                        <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Borang K)" onClick="popupEtanah('$id_fail','$id_permohonan','$id_hakmilik','BorangK','')">
                        #end
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBorangK('$!id_permohonan','$!id_hakmilik','$!id_borangk','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_hakmilik')">
				#end
			#end
			
			<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />	
			<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:viewListHM('$!id_permohonan');">
			</td>
		</tr>
	</table>
	
 <br />
    
    
    <fieldset id="bottom">
	<legend><strong>&nbsp;MUATNAIK BORANG K YANG DITANDATANGANI</strong></legend>
                
               
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
     <a href="javascript:popupCarianDokumen('$id_hakmilik','BorangK')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
    </td>
    </tr>
    </table>
    
    	
	<!--
            #parse("app/ppt/frmCarianListHMSek8.jsp")
            #parse("app/ppt/frmSeksyen8ListHM.jsp")
      -->      
	</fieldset>	

	<!-- <fieldset id="bottom">
	<legend><strong>Endosan Borang K</strong></legend>
	 	
	 	#if($mode=="view" && $saiz_endosan < 1 )
		<table width="100%" border="0">   
              <tr>
                 <td width="30%" align="left"><input type="button" name="cmdTambah" value="Maklumat Endosan" onClick="javascript:tambahEndosanBorangK('$!id_hakmilik','$!id_borangk');"></td>
              </tr>
    	</table>
		#end
	
		#if($saiz_endosan > 5)
                <div style="width:100%;height:100;overflow:auto"> 
        #end
	
		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td><b>No. Perserahan</b></td>
        			<td><b>Tarikh Terima</b></td>
        			<td><b>Tarikh Catatan Dibuat</b></td>
        			<td><b>Masa Catatan Dibuat</b></td>		
        		</tr>
        		
        		#if($saiz_endosan!=0)
                    #foreach($list in $listEndosanBorangK)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
               	   <td class="$row"><a href="javascript:viewEndosan('$!list.id_endosanborangk')"><font color="blue">$!list.no_perserahan</font></a></td>
                   <td class="$row">$!list.tarikh_terima</td>
                   <td class="$row">$!list.tarikh_catatan</td>
                   <td class="$row">$!list.masa_catatan&nbsp;$!list.masa</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		</table>
			
		#if($saiz_endosan > 5)
        	</div>
        #end	
		
	</fieldset> -->

</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	
	  #if($mode=="view")
      <tr>
		 <td><a href="#" onClick="javascript:cetakBorangK('$!id_permohonan','$!id_hakmilik')"><font color="blue">Borang K</font></a></td>
	  </tr>
	  #end
	  <!-- PPT 30 (i) -->
	  <tr>
      	<td><a href="#" onClick="javascript:cetakSuratPengosonganTanah('$!id_permohonan','$!id_hakmilik')"><font color="blue">Surat Pemberitahuan Pengosongan Tanah</font></a></td>
	  </tr>
	  <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaianBrgL('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang L</font></a></td>
	  </tr>	  
	  <!-- PPT 30 (i) -->
	  #if($userIdNeg=='10')
	  <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang K</font></a></td>
	  </tr>
	  #else
	  <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang K - I</font></a></td>
	  </tr>
	  <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaianRamai('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang K - II</font></a></td>
	  </tr>
	  #end
	  
	  	  <!--  <tr>
		 <td><a href="#" onClick="javascript:cetakEndorsanK('$!id_fail','$!nama_pengarah')"><font color="blue">Endorsan Borang K</font></a></td>
	  </tr> -->
    </table>
</fieldset>	

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_borangk" value="$!id_borangk">
<input type="hidden" name="id_hakmilikborangk" value="$!id_hakmilikborangk">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="id_endosanborangk">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

#if($showAlertPaging=="yes")
<script>
alert("Sila Pastikan Kesemua Hakmilik Telah Diisi Maklumat Borang K dan Klik 'Paging' No.19 Untuk Maklumat Borang L");
</script>
#end

#if($showAlertPagingSegera=="yes")
<script>
alert("Sila Klik 'Paging' No.15 / No.19 Untuk Meneruskan Proses Seksyen 8");
</script>
#end



<script>


function popupCarianDokumen(id_hakmilik,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function popupEtanah(id_fail,id_permohonan,id_hakmilik,jenis_skrin,command) {

	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&id_hakmilik="+id_hakmilik+"&jenis_skrin="+jenis_skrin+"&command="+command;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}

/*
function cetakBuktiPenyampaianRamai(idpermohonan,idhakmilik) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=K&report=BuktiPenyampaianRamai&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBuktiPenyampaian(idpermohonan,idhakmilik) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=K&report=BuktiPenyampaian&selectNoFail=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}*/

function cetakBuktiPenyampaian(idpermohonan,idhakmilik) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=K&report=BuktiPenyampaianK&selectNoFail=yes";
	//var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBuktiPenyampaianRamai(idpermohonan,idhakmilik) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=K&report=BuktiPenyampaianRamaiK&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
//PPT-30(i)
function cetakBuktiPenyampaianBrgL(idpermohonan,idhakmilik) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=L&report=BuktiPenyampaianL&selectNoFail=yes";
	//var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSuratPengosonganTanah(idpermohonan,idhakmilik) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=SuratPengosonganTanah&selectNoFail=yes";
	//var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
//PPT-30(i)
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function cetakEndorsanK(idfail,nama_pengarah) {

	var url = "../servlet/ekptg.report.ppt.EndorsanK?id_fail="+idfail+"&nama_pegawai="+nama_pengarah;
	
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangK(idpermohonan,idhakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=BorangK&selectNoFail=yes";
	//var url = "../servlet/ekptg.report.ppt.BorangK?id_hakmilik="+idhakmilik+"&id_Fail="+idfail+"&namaPegawai="+namaPengarah;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function viewEndosan(id_endosanborangk) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_endosanborangk.value = id_endosanborangk;
	document.${formName}.command.value = "viewEndosanBorangK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function batalKemaskini(idHakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "maklumatBorangK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function kemaskiniBorangK(idPermohonan,idHakmilik,idBorangK) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.id_permohonan.value = idPermohonan;
	document.${formName}.id_borangk.value = idBorangK;
	document.${formName}.command.value = "maklumatBorangK";
	document.${formName}.command2.value = "kemaskiniBorangK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function simpanBorangK(idPermohonan,idHakmilik,idBorangK,mode) {

	var dat1=document.${formName}.txdTarikhBorangK;

	var dat2=document.${formName}.txdTarikhCatatan;
	var dat3=document.${formName}.txdTarikhTerima;

	//tarikh Terima
	var TS  = document.getElementById("txdTarikhTerima").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateTerima = new Date(yr1, mon1, dt1);
	
	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh borang k 
	var TS  = document.getElementById("txdTarikhBorangK").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateBorangK = new Date(yr1, mon1, dt1);

   	if(document.${formName}.txdTarikhBorangK.value == ""){
		alert("Sila masukkan \"Tarikh Borang K\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangK.focus(); 
		return;
	}	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if(dat1.value!="" && (dateBorangK < currentDate)){
	   	alert("Sila pastikan \"Tarikh Borang K\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhBorangK.focus();
		return;
	}
	else if(document.${formName}.txtPerserahan.value == ""){
		alert("Sila masukkan \"No. Perserahan\" terlebih dahulu.");
  		document.${formName}.txtPerserahan.focus(); 
		return;
	}	
*/	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
/*	else if(dat3.value!="" && (dateTerima < currentDate)){
	   	alert("Sila pastikan \"Tarikh Terima\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTerima.focus();
		return;
	}
*/	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_hakmilik.value = idHakmilik;
		document.${formName}.id_permohonan.value = idPermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "maklumatBorangK";
			document.${formName}.command2.value = "simpanBorangK";
		}else{
			document.${formName}.id_borangk.value = idBorangK;
			document.${formName}.command.value = "maklumatBorangK";
			document.${formName}.command2.value = "kemaskiniBorangK";
			document.${formName}.command3.value = "updateBorangK";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
		document.${formName}.submit();
		
	}
}
function tambahEndosanBorangK(idHakmilik,idBorangK) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.id_borangk.value = idBorangK;
	document.${formName}.command.value = "tambahEndosanBorangK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function updateTarikhBorangF(idpermohonan){	

	var dat1 = document.${formName}.txdTarikhBorangF

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat
	var TS  = document.getElementById("txdTarikhBorangF").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateBorangF = new Date(yr1, mon1, dt1);
   	
	if(document.${formName}.txdTarikhBorangF.value == ""){
		alert("Sila masukkan \"Tarikh Borang F\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangF.focus(); 
		return;
	}	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if(dat1.value!="" && (dateBorangF < currentDate)){
	   	alert("Sila pastikan \"Tarikh Borang F\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhBorangF.focus();
		return;
	}
	else{
		
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.command2.value = "updateTarikhBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
	}
}
function viewListHM(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
</script>





<script>
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>


<script>
function checkDigit() {
	if(document.getElementById("txtMasaCatatan").value != "" && document.getElementById("txtMasaCatatan").value.length < 4){
		alert("Sila Pastikan Format Masa Catatan Adalah \"HHMM\"");
		document.${formName}.txtMasaCatatan.focus(); 
		return;	
	}
}
function validateJenisWaktu(elmnt,content) {

	var length = parseInt(document.getElementById("txtMasaCatatan").value.length);
	
	if(length>=2){

		var ValJam = content.substring(0,2);

		if(ValJam!=""){	
		
			if(ValJam >= 07 && ValJam <= 11){
				document.getElementById("socJenisWaktu").value = "1" ;
			}else if(ValJam == 12){
				document.getElementById("socJenisWaktu").value = "2" ;
			}else if(ValJam == 01 || ValJam == 02 || ValJam == 03 || ValJam == 04 || ValJam == 05 || ValJam == 06){
				document.getElementById("socJenisWaktu").value = "3" ;
			}else{
				document.getElementById("socJenisWaktu").value = "0" ;
			}
		
		}else{
			document.getElementById("socJenisWaktu").value = "0" ;
		}	
		return;

	}else{
		document.getElementById("socJenisWaktu").value = "0";
	}
	
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
      var strValidCharacters = "1234567890/.";
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
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
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
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yang sah")
		return false
	}
return true
}

function isIc(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
}
</script>