#parse("app/ppt/Sek8Paging.jsp") #set($frmtdate = "&nbsp;
<i><font color='blue' style='font-size: 10px'>dd/mm/yyyy</font></i>
") #if($mode=="new") #if($saiz_listHakmilikBorangEInBulk != 0) #set($M =
"*") #set($disability = "") #set($disabilityx = "") #set($disability1 =
"") #else #set($M = "") #set($disability = "readonly") #set($disabilityx
= "class=disabled") #set($disability1 = "disabled") #end #else

#if($isEdit=="yes") #set($M = "*") #set($disability = "")
#set($disabilityx = "") #set($disability1 = "") #else #set($M = "")
#set($disability = "readonly") #set($disabilityx = "class=disabled")
#set($disability1 = "disabled") #end #if($onchangeEdit=="no")

#foreach($data in $dataBorangEInBulk)
#set($txdTarikhBorangF=$data.tarikh_borangf)
#set($txdTarikhBorangE=$data.tarikh_borange)
#set($txdTarikhSiasatan=$data.tarikh_siasatan)
#set($txtMasaSiasatan=$data.masa_siasatan)
#set($socJenisWaktu=$data.jenis_waktu)
#set($txtAlamat1=$data.alamat1) #set($txtAlamat2=$data.alamat2)
#set($txtAlamat3=$data.alamat3) #set($txtPoskod=$data.poskod)
#set($tarikh_cetak=$data.tarikh_cetak) #set($txdTarikhTampal=$data.tarikh_akhir_tampal)
#end #end #end


<center>

	<br />

	<fieldset id="top">
		<legend>Maklumat Borang E</legend>

		<table width="100%" border="0">
			<tr>
				<td width="1%"><font color="red">*</font></td>
				<td width="18%">Tarikh Borang E</td>
				<td width="1%">:</td>
				<td width="80%"><input $disability $disabilityx
					name="txdTarikhBorangE" id="txdTarikhBorangE" size="11" type="text"
					value="$!txdTarikhBorangE"
					onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
					#if(($mode=="new" && $saiz_listHakmilikBorangEInBulk != 0) ||
					($mode=="view" && $isEdit=="yes")) <img src="../img/calendar.gif"
					onclick="displayDatePicker('txdTarikhBorangE',false,'dmy');">&nbsp;$!frmtdate
					#end</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Borang F</td>
				<td>:</td>
				<td><input $disability $disabilityx name="txdTarikhBorangF"
					id="txdTarikhBorangF" size="11" type="text"
					value="$!txdTarikhBorangF"
					onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
					#if(($mode=="new" && $saiz_listHakmilikBorangEInBulk != 0) ||
					($mode=="view" && $isEdit=="yes")) <img src="../img/calendar.gif"
					onclick="displayDatePicker('txdTarikhBorangF',false,'dmy');">&nbsp;$!frmtdate
					#end</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Akhir Tampal</td>
				<td>:</td>
				<td><input $disability $disabilityx name="txdTarikhTampal"
					id="txdTarikhTampal" size="11" type="text"
					value="$!txdTarikhTampal" onkeyup="validateTarikh(this,this.value)"
					onblur="check_date(this);getTarikhSiasatan()">
					#if(($mode=="new" && $saiz_listHakmilikBorangEInBulk != 0) ||
					($mode=="view" && $isEdit=="yes")) <img src="../img/calendar.gif"
					onclick="displayDatePicker('txdTarikhTampal',false,'dmy');">&nbsp;$!frmtdate
					#end</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Siasatan</td>
				<td>:</td>
				<td><input $disability $disabilityx name="txdTarikhSiasatan"
					id="txdTarikhSiasatan" size="11" type="text"
					value="$!txdTarikhSiasatan"
					onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
					#if(($mode=="new" && $saiz_listHakmilikBorangEInBulk != 0) ||
					($mode=="view" && $isEdit=="yes")) <img src="../img/calendar.gif"
					onclick="displayDatePicker('txdTarikhSiasatan',false,'dmy');">&nbsp;$!frmtdate
					#end</td>
			</tr>

			<!-- tr>
				<td>&nbsp;</td>
				<td>Masa Siasatan yang asala wehhhh</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text"
					name="txtMasaSiasatan" id="txtMasaSiasatan"
					value="$!txtMasaSiasatan"
					onblur="validateNumber(this,this.value);checkDigit()"
					onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)"
					maxlength="4" size="4" /> <select $disability1 $disabilityx
					name="socJenisWaktu" id="socJenisWaktu" style="width: 105px">

						<option value="0" #if($socJenisWaktu== "" || $socJenisWaktu=="0" ) selected=selected #end>SILA PILIH</option>
						<option value="1" #if($socJenisWaktu== '1') selected=selected #end>PAGI</option>
						<option value="2" #if($socJenisWaktu== '2') selected=selected #end>TENGAH HARI</option>
						<option value="3" #if($socJenisWaktu== '3') selected=selected #end>PETANG</option>

				</select>&nbsp;#if(($mode=="new" && $saiz_listHakmilikBorangEInBulk != 0) || ($mode=="view" && $isEdit=="yes")) <font color="blue" style="font-size: 10px"><i>format 12 jam (HHMM)</i></font>#end</td>
			</tr -->

			<tr>
				<td>&nbsp;</td>
				<td>Alamat</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text"
					name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="45"
					maxlength="80"></td>
			</tr>

			<tr>
				<td colspan="3">&nbsp;</td>
				<td><input $disability $disabilityx type="text"
					name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="45"
					maxlength="80"></td>
			</tr>

			<tr>
				<td colspan="3">&nbsp;</td>
				<td><input $disability $disabilityx type="text"
					name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="45"
					maxlength="80"></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>Poskod</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text"
					name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5"
					maxlength="5" onkeyup="validateNumber(this,this.value)"
					onblur="validateNumber(this,this.value)"></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>Negeri</td>
				<td>:</td>
				<td>$!selectNegeri</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>Bandar</td>
				<td>:</td>
				<td>$!selectBandar</td>
			</tr>
		</table>

		<br />

	<fieldset id="center">
			<legend>
				<strong><font color="red">$M</font>Senarai Pilihan	Hakmilik</strong>
			</legend>

			#if($saiz_listHakmilikBorangEInBulk > 10)
			<div style="width: 100%; height: 285; overflow: auto">
				#end

				<table width="100%" cellpadding="0" cellspacing="2" border="0">

					<tr class="table_header">
						#if($saiz_listHakmilikBorangEInBulk != 0)
						<td align="center" width="5%"><b><input $disability1
							type="checkbox" title="Sila Semak Untuk Pilih Semua"
							name="checkall" id="checkall" onclick="checkALL()"></b></td> #end
						<td align="center" width="15%"><b>No (Tarikh Borang E)</b></td>
						<td width="20%"><b>No.Hakmilik</b></td>
						<td width="15%"><b>No.LOT/No.PT</b></td>
						<td width="20%"><b>Mukim/Pekan/Bandar</b></td>
						#if($!flag_subjaket!="")
						<td width="10%"><b>No.Subjaket</b></td>#end
						<td width="20%"><b>Masa Siasatan</b></td>
					</tr>
					
					#if($saiz_listHakmilikBorangEInBulk!=0)
					#foreach($listTanah in $listHakmilikBorangEInBulk)
					#set( $i = $velocityCount ) #if ( ($i % 2) != 1 )
					#set( $row = "row2" ) #else #set( $row = "row1" ) #end
					
					#if($listTanah.selectedcb > 0) #set($checkCB = "checked") #else
					#set($checkCB = "") #end #set($checkedCB = "") #if($mode=="new")
					#foreach ($foo in $selectedItem) #if($foo == $!listTanah.id_hakmilik) #set($checkedCB = "checked") #end #end
					#else #if($onchangeEdit=="no") #if($listTanah.selectedcb > 0)
					#set($checkedCB = "checked") #else #set($checkedCB = "") #end #else
					#foreach ($foo in $selectedItem) #if($foo == $!listTanah.id_hakmilik) #set($checkedCB = "checked") #end #end
					#end #end
					
					<tr>
						#if($saiz_listHakmilikBorangEInBulk != 0)
						<td class="$row" align="center"><input type="checkbox"
							$disability1 $checkedCB name="cbsemaks" id="cbsemaks"
							onclick="doUpdateCheckAll()" value="$!listTanah.id_hakmilik"></td>
						#end
						<td class="$row" align="center"><b>$!listTanah.bil</b>
							#if($!listTanah.TARIKH_BORANGE != "")
							&nbsp;($!listTanah.TARIKH_BORANGE) #end</td>
						<td class="$row">$!listTanah.kod_jenis_hakmilik
							$!listTanah.no_hakmilik</td>
						<td class="$row">$!listTanah.no_lotpt</td>
						<td class="$row">$!listTanah.nama_mukim
							#if($listTanah.seksyen!="")<font style="font-size: 10px">Seksyen $listTanah.seksyen</font>#end
						</td> 
						#if($!flag_subjaket!="")
							<td class="$row">Sj.$!listTanah.no_subjaket</td>
						#end
						
						<td class="$row">
					
						<!--	<input $disability $disabilityx type="text" name="txtMasaSiasatan" id="txtMasaSiasatan" value="$!listTanah.masa_siasatan" 
							onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" 
							maxlength="4" size="4" />
							-->
						#if($isEdit=="yes")
						#set($dateName = "txtMasaSiasatan"+$!listTanah.bil)
                   		<input name="$!dateName" id="$!dateName" size="11" type="text" value="$!listTanah.MASA_SIASATAN"  onblur="validateNumber(this,this.value);checkDigit() ">            	  	
            	  		#set($idBL = "id_borangehakmilik"+$!listTanah.bil)
            	  		<input type="hidden" name="$!idBL" value="$!listTanah.id_borangehakmilik">
            	  		<input type="hidden" name="id_hakmilik" value="$!listTanah.id_hakmilik">
            			#else
            			$!listTanah.MASA_SIASATAN
            			#end
            			<!--  
							<select $disability1 $disabilityx name="socJenisWaktu" id="socJenisWaktu" style="width: 105px">
							
								<option value="0" #if($listTanah.jenis_waktu== "" || $listTanah.jenis_waktu=="0" ) selected=selected #end>SILA PILIH</option>
								<option value="1" #if($listTanah.jenis_waktu== '1') selected=selected #end>PAGI</option>
								<option value="2" #if($listTanah.jenis_waktu== '2') selected=selected #end>TENGAH HARI</option>
								<option value="3" #if($listTanah.jenis_waktu== '3') selected=selected #end>PETANG</option>

							</select>&nbsp;#if(($mode=="new" && $saiz_listHakmilikBorangEInBulk != 0) || ($mode=="view" && $isEdit=="yes")) <font color="blue" style="font-size: 10px"><i>format 12 jam (HHMM)</i></font>#end
							-->
							
								
						#if($isEdit=="yes")
                		#set($statusBL = "socJenisWaktu"+$!listTanah.bil)
                		<select name="$!statusBL" style="width:100px">
      						<option value="" #if($!listTanah.jenis_pilih=="") selected=selected #end >Sila Pilih</option>	
			      			<option value="1" #if($!listTanah.jenis_pilih=="1") selected=selected #end>PAGI</option>
			      			<option value="2" #if($!listTanah.jenis_pilih=="2") selected=selected #end>TENGAH HARI</option>	
			      			<option value="3" #if($!listTanah.jenis_pilih=="3") selected=selected #end>PETANG</option>	
			      		</select>      
			      		#else
			      		$!listTanah.JENIS_WAKTU
			      		#end     			
                		</td>  

					</tr>
					#end #else
					<tr>
						<td colspan="2">Tiada rekod</td>
					</tr>
					#end

			</table>
			
			#if($saiz_listHakmilikBorangEInBulk > 10)
			</div>
			#end

		</fieldset>

	</fieldset>


	<table width="100%" border="0">
		<tr align="center">
			<td>#if($saiz_listHakmilikBorangEInBulk != 0) #if($mode=="new")
				<input type="button" name="cmdSimpan" value="Simpan"
				onClick="javascript:simpanBorangEInBulk('$!id_permohonan','$!saiz_listHakmilikBorangEInBulk','$!mode','$!id_borange')">
				#end #if($mode=="view") #if($isEdit=="no") <input type="button"
				name="cmdKemaskini" value="Kemaskini"
				onClick="javascript:kemaskiniBorangEInBulk('$!id_borange')">
				
				<input type="button" name="button" id="button" value="Cetak"
				onClick="javascript:setTable('tableReport1')" /> #else <input
				type="button" name="cmdUpdate" value="Simpan"
				onClick="javascript:simpanBorangEInBulk('$!id_permohonan','$!saiz_listHakmilikBorangEInBulk','$!mode','$!id_borange')">
				<input type="button" name="cmdBatal" value="Batal"
				onClick="javascript:viewBorangEInBulk('$!id_borange','batal')">
				#end #end #end

				<input type="button" name="cmdKembali"
				value="Kembali" onClick="javascript:viewListHM('$!id_permohonan');">
			
			</td>
		</tr>
	</table>


	<br />

	<fieldset>
		<legend>
			<strong>Senarai Rekod Maklumat Borang E</strong> #if($mode=="view")<input
				type="button" name="cmdMainscreen" value="Kemasukan Maklumat Borang E"
				onClick="javascript:daftarMaklumatBorangEInBulk();">#end
		</legend>

		<table width="100%" border="0">
			<tr>
				<td align="left"><a
					href="javascript:popupCarianHakmilik('$id_permohonan','senarai_borangE_inbulk')">
					<font color="blue">>> SKRIN CAPAIAN MAKLUMAT BORANG E</font></a></td>
			</tr>
		</table>
		<!--

    <table width="100%" border="0">
		#if($mode=="view")
		<tr align="left">
			<td colspan="3">
				<input type="button" name="cmdMainscreen" value="Kemasukan Maklumat Borang E" onClick="javascript:daftarMaklumatBorangEInBulk();">
			</td>
             
            <td colspan = "2"  align="right">
				Carian No.LOT<b>/</b>No.PT<b>/</b>Nama Pihak Berkepentingan :
			</td>
            
            <td colspan="4" align="left">
				<input type="text" style="width:60%" name="carianLotHakmilik" id="carianLotHakmilik" value="$carianLotHakmilik" >
                <a href="javascript:cariLOT('$!id_permohonan','$!id_borange')">&nbsp;<u>CARI</u></a>
                &nbsp;
                <a href="javascript:kosongkanLOT('$!id_permohonan','$!id_borange')">&nbsp;<u>KOSONGKAN</u></a>
			</td>
		</tr>
		#end
		
		<tr class="table_header">
       		<td align="center"><b>No</b></td>
            <td><b>Senarai Rekod/Tarikh Daftar</b></td>
           	<td><b>Tarikh Borang E</b></td>              
            <td><b>Tarikh Siasatan</b></td>
            <td align="center"><b>Senarai Lot</b></td>
            <td><b>&nbsp;</b></td>
        </tr>
                    
   		#if($saiz_listBorangEInBulk!=0)
      		#foreach($listN in $listBorangEInBulk)
            #set( $i = $velocityCount )
         	#if ( ($i % 2) != 1 )
           		#set( $row = "row2" )
	        #else
	            #set( $row = "row1" )
	       	#end
         	
       	<tr>
           	<td class="$row" align="center">$!listN.bil</td>
            <td class="$row">
            <a href="javascript:viewBorangEInBulk('$!listN.ID_BORANGE','')"><font color="blue">Rekod $!listN.bil - $!listN.TARIKH_MASUK</font></a></td>
			<td class="$row">$!listN.TARIKH_BORANGE</td>  
			<td class="$row">$!listN.TARIKH_SIASATAN</td> 
			<td align="center" class="$row"><a href="javascript:viewPopupLot('$!listN.ID_BORANGE','$!id_permohonan')"><font color="blue"><b>$!listN.TOTALHM</b></font></a></td>   
        	<td align="center" class="$row"><input type="button" name="cmdBorangF" value="Kemasukan Maklumat Borang F" onClick="javascript:maklumatBorangF('$!listN.ID_BORANGE');">
            <input type="button" name="cmdHaspusBorangE" value="Hapus Borang E" onClick="javascript:hapusBorangE('$!listN.ID_BORANGE');">
            </td>
        </tr>
        	#end
        #else
        <tr>
           	<td colspan="2">Tiada rekod</td>
       	</tr>
        #end 
		
	</table>
    -->
	</fieldset>

</center>

<br />



<fieldset id="tableReport1" style="display: none;">
	<legend>
		<strong>SENARAI LAPORAN</strong>
	</legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td><a href="#"
				onClick="javascript:cetakBorangE('$!id_permohonan','$!id_borange','$!tarikh_cetak')"><font
					color="blue">Borang E</font></a></td>
		</tr>
		<tr>
			<td><a href="#"
				onClick="javascript:cetakSiasatanJPPH('$!id_permohonan','$!id_borange')"><font
					color="blue">Surat Kepada JPPH Jemputan Hadir Siasatan</font></a></td>
		</tr>
		<tr>
			<td><a href="#"
				onClick="javascript:cetakSiasatanAP('$!id_permohonan','$!id_borange','projek')"><font
					color="blue">Surat Kepada AP Supaya Hadir Untuk Siasatan (1
						Projek)</font></a></td>
		</tr>
		<tr>
			<td><a href="#"
				onClick="javascript:cetakSiasatanAP('$!id_permohonan','$!id_borange','lot')"><font
					color="blue">Surat Kepada AP Supaya Hadir Untuk Siasatan
						(Mengikut Rekod)</font></a></td>
		</tr>

		#if($userIdNeg=='10')
		<tr>
			<td><a href="#"
				onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik','$!id_borange')"><font
					color="blue">Bukti Penyampaian Borang E dan F</font></a></td>
		</tr>
		#else
		<tr>
			<td><a href="#"
				onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik','$!id_borange')"><font
					color="blue">Bukti Penyampaian Borang E dan F - I</font></a></td>
		</tr>


		<tr>
			<td><a href="#" onClick="javascript:cetakBuktiPenyampaianRamai('$!id_permohonan','$!id_hakmilik','$!id_borange')">
			<font color="blue">Bukti Penyampaian Borang E dan F - II</font></a></td>
		</tr>
		
		#end
		
		<!-- PPT-08 -->
		<tr>
			<td><a href="#" onClick="javascript:NotisBorangE('$!id_borange')"><font color="blue">Notis Borang E</font></a></td>
		</tr>

	</table>
</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_borange" value="$!id_borange">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="isEdit">
<input type="hidden" name="mode" value="$!mode">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token"
	value='$!{session.getAttribute("form_token")}'>



<script>

function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik_BorangE_F?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function cariLOT(idpermohonan,id_borange) {
	
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "viewMaklumatBorangEInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}

function kosongkanLOT(idpermohonan,id_borange) {
	document.${formName}.carianLotHakmilik.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "viewMaklumatBorangEInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}

function maklumatBorangF(id_borange){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "maklumatBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}

function hapusBorangE(id_borange){	
	{if ( !window.confirm("Adakah Anda Pasti?") ) return;}
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "deleteBorangEInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}


function kemaskiniBorangEInBulk(id_borange){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.isEdit.value = "yes";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "viewMaklumatBorangEInBulk";
	document.${formName}.command2.value = "kemaskiniBorangEInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function viewBorangEInBulk(id_borange,mode){	
	if(mode=="batal"){if ( !window.confirm("Adakah Anda Pasti?") ) return;}
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "viewMaklumatBorangEInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function viewPopupLot(id_borange,id_permohonan){
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmMyInfoPopupPegawaiBertugas?id_borange="+id_borange+"&type=borange&id_permohonan="+id_permohonan;
	var hWnd = window.open(url,'Senarai Lot','width=800,height=400, resizable=yes,scrollbars=yes');	
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function daftarMaklumatBorangEInBulk(){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.isEdit.value = "yes";
	document.${formName}.command.value = "daftarMaklumatBorangEInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function onchangeDropdown(){
	var mode = document.${formName}.mode.value;
	
	document.${formName}.ScreenLocation.value = "top";
	if(mode=="new"){
		document.${formName}.command.value = "daftarMaklumatBorangEInBulk";
	}else{
		document.${formName}.command.value = "viewMaklumatBorangEInBulk";
	}	
	document.${formName}.command2.value = "onchangeDropdown";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function cetakSiasatanJPPH(idpermohonan,id_borange) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_borange="+id_borange+"&report=SiasatanJPPH&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
  
}
function getTarikhSiasatan() {

	var TB  = document.getElementById("txdTarikhTampal").value;

	var a = TB.substring(0,2);
	var b = TB.substring(3,5);
   	var c = TB.substring(6,10);
   	
	var dt1   = parseInt(TB.substring(0,2),10)+24;
   	var mon1  = parseInt(TB.substring(3,5),10)-1;
   	var yr1   = parseInt(TB.substring(6,10),10);

	var myDate=new Date(yr1, mon1, dt1);

	var day = myDate.getDate();
	var month = myDate.getMonth()+1;
	var year = myDate.getFullYear();

	var tarikhSiasatan = "";
	if(month>=10){
		if(day>=10){
			tarikhSiasatan = day + "/" + month + "/" + year;	
		}else{
			tarikhSiasatan = "0"+ day + "/" + month + "/" + year;	
		}
			
	}else{
		if(day>=10){
			tarikhSiasatan = day + "/0" + month + "/" + year;	
		}else{
			tarikhSiasatan = "0"+ day + "/0" + month + "/" + year;	
		}
	}
	
	if((a!="" && a.length=="2") && (b!="" && b.length=="2") && (c!="" && c.length=="4")){
		document.getElementById("txdTarikhSiasatan").value = tarikhSiasatan ;
	}else{
		document.getElementById("txdTarikhSiasatan").value = "" ;
	}
}
function cetakBuktiPenyampaian(idpermohonan,idhakmilik,id_borange) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_borange="+id_borange+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=E&report=BuktiPenyampaian&selectNoFail=yes";
	//var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBuktiPenyampaianRamai(idpermohonan,idhakmilik,id_borange) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_borange="+id_borange+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=E Dan F&report=BuktiPenyampaianRamai&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSiasatanAP(idpermohonan,id_borange,flagCetakSiasatan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_borange="+id_borange+"&report=SuratSiasatanKpdAP&selectNoFail=yes&flagShowTarikhCetak=yes&flagCetakSiasatan="+flagCetakSiasatan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function viewListHM(id_permohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}

function simpanBorangEInBulk(id_permohonan,size,mode,id_borange){

	var checkSelected=false;
	if(size>1){
		for(var i=0 ; i < document.${formName}.cbsemaks.length; i++) { 
    		if (document.${formName}.cbsemaks[i].checked)	{
  				checkSelected=true; 
  			}
		}
	}	else	{
		if (document.${formName}.cbsemaks.checked) 	{
			checkSelected=true; 
    	}
	}
	
	var TB  = document.getElementById("txdTarikhTampal").value;
	
	var dt1   = parseInt(TB.substring(0,2),10)+20;
   	var mon1  = parseInt(TB.substring(3,5),10)-1;
   	var yr1   = parseInt(TB.substring(6,10),10);

   	var dateValSiasatan = new Date(yr1, mon1, dt1);

	var tarikhSiasatan  = document.getElementById("txdTarikhSiasatan").value;

	var dt2   = parseInt(tarikhSiasatan.substring(0,2),10);
   	var mon2  = parseInt(tarikhSiasatan.substring(3,5),10)-1;
   	var yr2   = parseInt(tarikhSiasatan.substring(6,10),10);
	
   	var dateSiasatan = new Date(yr2, mon2, dt2);
   	
	var dat1 = document.${formName}.txdTarikhBorangF
	var dat2 = document.${formName}.txdTarikhBorangE
	var dat3 = document.${formName}.txdTarikhSiasatan
	var dat4 = document.${formName}.txdTarikhTampal
	
	if (dat2.value=="")	{
	
		alert("Sila Masukkan Tarikh Borang E")
		dat2.focus()
		return;
		
	}	
	else if (dat1.value!="" && isDate(dat1.value)==false)	{
		dat1.focus()
		return;
	}	
	else if (dat2.value!="" && isDate(dat2.value)==false)	{
		dat2.focus()
		return;
	}
	else if (dat4.value!="" && isDate(dat4.value)==false)	{
		dat4.focus()
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)	{
		dat3.focus()
		return;
	}
	else if((dat4.value!="" && dat3.value!="") && (dateSiasatan < dateValSiasatan))	{
		alert("Sila pastikan \"Tarikh Siasatan\" tidak kurang 21 hari dari \"Tarikh Akhir Tampal\"");
	 	document.${formName}.txdTarikhSiasatan.focus();
	 	return;
	}
	else if(!checkSelected)	{
		alert("Sila pilih \"Hakmilik\" terlebih dahulu.");
		return;
		
	}	else	{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_permohonan.value = id_permohonan;
		if(mode=="new"){
			document.${formName}.command.value = "daftarMaklumatBorangEInBulk";
			document.${formName}.command2.value = "simpanMaklumatBorangEInBulk";
		}	else	{
			document.${formName}.id_borange.value = id_borange;
			document.${formName}.command.value = "viewMaklumatBorangEInBulk";
			document.${formName}.command2.value = "kemaskiniBorangEInBulk";
			document.${formName}.command3.value = "updateBorangEInBulk";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
		document.${formName}.submit();
	}
}

function cetakBorangE(idpermohonan,id_borange,tarikh_cetak) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_borange="+id_borange+"&report=BorangE&selectNoFail=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
</script>


<script>
function checkDigit() {
	if(document.getElementById("txtMasaSiasatan").value != "" && document.getElementById("txtMasaSiasatan").value.length < 4){
		alert("Sila Pastikan Format Masa Siasatan Adalah \"HHMM\"");
		document.${formName}.txtMasaSiasatan.focus(); 
		return;	
	}
}

function validateJenisWaktu(elmnt,content) {

	var length = parseInt(document.getElementById("txtMasaSiasatan").value.length);
	
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

function checkALL() {

	 if (document.${formName}.checkall.checked == true){
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = true;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = true;
	            }
	        }
	    } else {
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = false;
	     	} else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = false;	                
	        }
		}
	}
}

function doUpdateCheckAll(){  

	var c = 0;
	if(document.${formName}.cbsemaks.length > 1){     
		
		for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	      if (document.${formName}.cbsemaks[i].checked == false){	 
		  	c++
	      }
		}  

	}else{
		
		if (document.${formName}.cbsemaks.checked == false){				 
			c++;
		}	 	
	}	 
	 
	if(c>0){
			  
		document.${formName}.checkall.checked = false;
	}
	else{
		document.${formName}.checkall.checked = true;
	}       
}
</script>


<script>
window.onload = submitForm;
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$!selectedTab});

function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}

function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
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

function NotisBorangE(id_borange){

	var url = "../servlet/ekptg.report.ppt.NotisBrgE?id_borange="+id_borange;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>