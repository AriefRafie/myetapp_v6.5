#parse("app/ppt/SementaraPaging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset id="top">
<center>
<legend><strong>Maklumat JPPH</strong></legend>

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>

	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>

	<div id="TabbedPanels1" class="TabbedPanels">
  		<ul class="TabbedPanelsTabGroup">
    		<!--<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);viewMaklumat('$!id_permohonan')" tabindex="1">Jabatan Perancangan Bandar dan Desa (JPBD)</li>-->
   			<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);viewMaklumat('$!id_permohonan')" tabindex="1">Jabatan Penilaian dan Perkhidmatan Harta (JPPH)</li>
  		</ul>
  		
  		<div class="TabbedPanelsContentGroup">
  		
  			<!-- START TAB 1 -->

			

    		<!-- END TAB 1 -->
    
    
    
    		<!-- START TAB 2 -->
    		<div class="TabbedPanelsContent">
    
    			<br/>
    			
    			#if($modeJPPH=="new")
    			
    			<!-- <fieldset>
    				<table width="100%" border="0">
    					<tr>
    						<td width="20%">Kod Pejabat JPPH</td>
    						<td width="1%">:</td>
    						<td width="79%"><input type="text" name="txtKodPejabatJPPH" id="txtKodPejabatJPPH" value="" size="20" maxlength="30"   /></td>
    					</tr>
    				</table>
    			</fieldset> -->
    			
    			
    			<table width="100%" border="0">
    				<tr>
    				
    				<!-- TABLE KIRI -->
    				<td width="50%" valign="top">
    					
    					<fieldset>
    					<legend><strong>Dihantar</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan</td>
    							<td width="1%">:</td>
    							<td width="54%"><input type="text" name="txtBilSuratH" id="txtBilSuratH" value="" size="30" maxlength="50"   /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Surat</td>
    							<td>:</td>
    							<td><input name="txdTarikhSuratH" id="txdTarikhSuratH" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratH',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr>
    						
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				
    				<!-- TABLE KANAN -->
    				<td width="50%" valign="top">
    				
    					<fieldset>
    					<legend><strong>Diterima</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan Surat JPPH</td>
    							<td width="1%">:</td>
    							<td width="54%"><input type="text" name="txtNoRujSuratH" id="txtNoRujSuratH" value="" size="25" maxlength="50"   /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Jawapan</td>
    							<td>:</td>
    							<td><input name="txdTarikhTerimaH" id="txdTarikhTerimaH" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaH',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr>
    						<!-- <tr>
    							<td>Tarikh Terima</td>
    							<td>:</td>
    							<td><input name="txdTarikhTerimaJTH" id="txdTarikhTerimaJTH" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaJTH',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr> -->
    						
    						<tr>
    							<td>Tarikh Surat JPPH</td>
    							<td>:</td>
    							<td><input name="txdTarikhSuratJTH" id="txdTarikhSuratJTH" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratJTH',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr>
    						<tr>
    							<td valign="top">Ulasan</td>
    							<td valign="top">:</td>
    							<td><textarea name="txtUlasanJPPH" id="txtUlasanJPPH" rows="3" cols="25%"  onKeyUp="textCounter(this.form.txtUlasanJPPH,this.form.remLen9,1500);" onKeyDown="textCounter(this.form.txtUlasanJPPH,this.form.remLen9,1500);" ></textarea></td>
    						</tr>
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				</tr>
    			</table>
    			#end
    			
    			
    			
    			#if($modeJPPH=="view")
    			
    				#foreach($dataH in $dataMaklumatJPPH)
    					#set($txtKodPejabatJPPH=$dataH.kod_pejabat_jpph)
    					#set($txtBilSuratH=$dataH.bil_surat)
    					#set($txdTarikhSuratH=$dataH.tarikh_surat)
    					#set($txdTarikhTerimaH=$dataH.tarikh_akhir)
    					#set($txtNoRujSuratH=$dataH.no_rujukansuratjt)
    					#set($txdTarikhTerimaJTH=$dataH.tarikh_terimajt)
    					#set($txdTarikhSuratJTH=$dataH.tarikh_suratjt)
    					#set($txtUlasanJPPH=$dataH.ulasanjt)
    				#end
    			
    			
    				#if($isEditJPPH=="no")
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
	
				<!--  <fieldset>
    				<table width="100%" border="0">
    					<tr>
    						<td width="20%">Kod Pejabat JPPH</td>
    						<td width="1%">:</td>
    						<td width="79%"><input $disability $disabilityx type="text" name="txtKodPejabatJPPH" id="txtKodPejabatJPPH" value="$!txtKodPejabatJPPH" size="20" maxlength="30"   /></td>
    					</tr>
    				</table>
    			</fieldset> -->
    			
    			
    			<table width="100%" border="0">
    				<tr>
    				
    				<!-- TABLE KIRI -->
    				<td width="50%" valign="top">
    					
    					<fieldset>
    					<legend><strong>Dihantar</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan</td>
    							<td width="1%">:</td>
    							<td width="54%"><input $disability $disabilityx type="text" name="txtBilSuratH" id="txtBilSuratH" value="$!txtBilSuratH" size="30" maxlength="50"  /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Surat</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhSuratH" id="txdTarikhSuratH" size="11" type="text" value="$!txdTarikhSuratH" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEditJPPH=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratH',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr>
    						
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				
    				<!-- TABLE KANAN -->
    				<td width="50%" valign="top">
    				
    					<fieldset>
    					<legend><strong>Diterima</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan Surat JPPH</td>
    							<td width="1%">:</td>
    							<td width="54%"><input $disability $disabilityx type="text" name="txtNoRujSuratH" id="txtNoRujSuratH" value="$!txtNoRujSuratH" size="25" maxlength="50"   /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Jawapan</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhTerimaH" id="txdTarikhTerimaH" size="11" type="text" value="$!txdTarikhTerimaH" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEditJPPH=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaH',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr>
    						<!-- <tr>
    							<td>Tarikh Terima</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhTerimaJTH" id="txdTarikhTerimaJTH" size="11" type="text" value="$!txdTarikhTerimaJTH" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEditJPPH=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaH',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr> -->
    						
    						<tr>
    							<td>Tarikh Surat JPPH</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhSuratJTH" id="txdTarikhSuratJTH" size="11" type="text" value="$!txdTarikhSuratJTH" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEditJPPH=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratJTH',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr>
    						<tr>
    							<td valign="top">Ulasan</td>
    							<td valign="top">:</td>
    							<td><textarea $disability $disabilityx name="txtUlasanJPPH" id="txtUlasanJPPH" rows="3" cols="25%" onKeyUp="textCounter(this.form.txtUlasanJPPH,this.form.remLen9,1500);" onKeyDown="textCounter(this.form.txtUlasanJPPH,this.form.remLen9,1500);" >$!txtUlasanJPPH</textarea></td>
    						</tr>
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				</tr>
    			</table>
    			#end
    			
    
    		<table width="100%" border="0">
				<tr align="center">
					<td>
					#if($modeJPPH=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanMaklumatJPPH('$!id_permohonan','$!id_jpph','$!modeJPPH')">
					#end
				
					<!--<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport2')" />-->
				
					#if($modeJPPH=="view")
						#if($isEditJPPH=="no")
						<!--  <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  -->
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniJPPH('$!id_permohonan')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanMaklumatJPPH('$!id_permohonan','$!id_jpph','$!modeJPPH')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalJPPH('$!id_permohonan')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali('$!id_permohonan')">
					</td>
				</tr>
			</table>
    
    		</div>
  			<!-- END TAB 1 -->
  
  		</div>
	</div>


</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<!-- <tr>
    	 	<td><a href="#" onClick="javascript:cetakPerkara1JPBD('$!id_permohonan')"><font color="blue">Maklumat Perancang JPBD</font></a></td>
      </tr>	
      <tr>
        	<td ><a href="#" class="style2" onClick="javascript:cetakSuratJPBD('$!id_hakmilik','$!nama2Mukim')"><font color="blue">Lampiran A1</font></a></td>
      </tr>  -->
	   
	  <!--  <tr> 
      <td><a href="#" onClick="javascript:cetakBorangQ('$id_fail','$id_hakmilik')"><font color="blue"> Borang Q - Pemberitahu Menduduki Atau Menggunakan Tanah Bagi Sementara</font></a></td> 
      </tr> --> 
     
    </table>
</fieldset>

<fieldset id="tableReport2" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
    	 	<td><a href="#" onClick="javascript:cetakPerancangJPPH('$!id_permohonan','$!id_hakmilik')"><font color="blue">Maklumat Perancang JPPH</font></a></td>
      </tr>	

    </table>
</fieldset>

<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_jpbd" value="$!id_jpbd">
<input type="hidden" name="id_jpph" value="$!id_jpph">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>




<script>
function cetakBorangQ(id_fail,id_hakmilik) {
	//var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;
	var url = "../servlet/ekptg.report.ppt.BorangQ?idfail="+id_fail+"&idhakmilik="+id_hakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function cetakPerancangJPPH(idpermohonan,id_hakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+id_hakmilik+"&report=PerancangJPPH&flagReport=S";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
  
}
function cetakPerkara1JPBD(idpermohonan) {

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=Perkara1JPBD&flagReport=S";
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
function cetakSuratJPBD(idhakmilik,mukim) {
	var url = "../servlet/ekptg.report.ppt.LampiranA1?idHakmilik="+idhakmilik+"&mukim="+mukim;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function batalJPPH(idpermohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH";
	document.${formName}.submit();
}
function kemaskiniJPPH(idpermohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.command2.value = "kemaskiniJPPH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH";
	document.${formName}.submit();
}
function simpanMaklumatJPPH(idpermohonan,idjpph,mode) {

	var dat1=document.${formName}.txdTarikhSuratH
	//var dat2=document.${formName}.txdTarikhTerimaH
	//var dat3=document.${formName}.txdTarikhTerimaJTH
	var dat4=document.${formName}.txdTarikhSuratJTH

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat
	var TS  = document.getElementById("txdTarikhSuratH").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);

   	var dateSurat = new Date(yr1, mon1, dt1);

   	
  	//tarikh terima jawapan
	//var TT  = document.getElementById("txdTarikhTerimaH").value;
	//var dt2   = parseInt(TT.substring(0,2),10);
   	//var mon2  = parseInt(TT.substring(3,5),10);
   	//var yr2   = parseInt(TT.substring(6,10),10);
   	//var dateTerima = new Date(yr2, mon2, dt2);

    //tarikh terima JT
	//var TTJT  = document.getElementById("txdTarikhTerimaJTH").value;
	//var dt3   = parseInt(TTJT.substring(0,2),10);
   	//var mon3  = parseInt(TTJT.substring(3,5),10);
   	//var yr3   = parseInt(TTJT.substring(6,10),10);
   	//var dateTerimaJT = new Date(yr3, mon3, dt3);

 	//tarikh surat JT
	var TSJT  = document.getElementById("txdTarikhSuratJTH").value;
	var dt4   = parseInt(TSJT.substring(0,2),10);
   	var mon4  = parseInt(TSJT.substring(3,5),10);
   	var yr4   = parseInt(TSJT.substring(6,10),10);
   	var dateSuratJT = new Date(yr4, mon4, dt4);

	
	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if(dat1.value!="" && (dateSurat < currentDate)){
	   	alert("Sila pastikan \"Tarikh Surat\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhSuratH.focus();
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if(dat2.value!="" && (dateTerima < currentDate)){
	   	alert("Sila pastikan \"Tarikh Jawapan Diterima\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTerimaH.focus();
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
	else if(dat3.value!="" && (dateTerimaJT < currentDate)){
	   	alert("Sila pastikan \"Tarikh Terima\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTerimaJTH.focus();
		return;
	}
*/	else if (dat4.value!="" && isDate(dat4.value)==false)
	{
		dat4.focus()
		return;
	}
/*	else if(dat4.value!="" && (dateSuratJT < currentDate)){
	   	alert("Sila pastikan \"Tarikh Surat JPPH\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhSuratJTH.focus();
		return;
	}
*/	else{
	
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.tabId.value = "1";
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "viewMaklumat";
			document.${formName}.command2.value = "simpanMaklumatJPPH";
		}else{
			document.${formName}.id_jpph.value = idjpph;
			document.${formName}.command.value = "viewMaklumat";
			document.${formName}.command2.value = "kemaskiniJPPH";
			document.${formName}.command3.value = "updateJPPH";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH";
		document.${formName}.submit();
	}
}
function batalJPBD(idpermohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH";
	document.${formName}.submit();
}
function kemaskiniJPBD(idpermohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.command2.value = "kemaskiniJPBD";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH";
	document.${formName}.submit();
}
function simpanMaklumatJPBD(idpermohonan,idjpbd,mode) {

	var dat1=document.${formName}.txdTarikhSurat
	var dat2=document.${formName}.txdTarikhTerima
	//var dat3=document.${formName}.txdTarikhTerimaJT
	var dat4=document.${formName}.txdTarikhSuratJT

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat
	var TS  = document.getElementById("txdTarikhSurat").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateSurat = new Date(yr1, mon1, dt1);

  	//tarikh terima jawapan
	var TT  = document.getElementById("txdTarikhTerima").value;
	var dt2   = parseInt(TT.substring(0,2),10);
   	var mon2  = parseInt(TT.substring(3,5),10);
   	var yr2   = parseInt(TT.substring(6,10),10);
   	var dateTerima = new Date(yr2, mon2, dt2);

    //tarikh terima JT
	//var TTJT  = document.getElementById("txdTarikhTerimaJT").value;
	//var dt3   = parseInt(TTJT.substring(0,2),10);
    //var mon3  = parseInt(TTJT.substring(3,5),10);
    //var yr3   = parseInt(TTJT.substring(6,10),10);
   	//var dateTerimaJT = new Date(yr3, mon3, dt3);

 	//tarikh surat JT
	var TSJT  = document.getElementById("txdTarikhSuratJT").value;
	var dt4   = parseInt(TSJT.substring(0,2),10);
   	var mon4  = parseInt(TSJT.substring(3,5),10);
   	var yr4   = parseInt(TSJT.substring(6,10),10);
   	var dateSuratJT = new Date(yr4, mon4, dt4);

	
	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if(dat1.value!="" && (dateSurat < currentDate)){
	   	alert("Sila pastikan \"Tarikh Surat\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhSurat.focus();
		return;
	}
*/	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
/*	else if(dat2.value!="" && (dateTerima < currentDate)){
	   	alert("Sila pastikan \"Tarikh Jawapan Diterima\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTerima.focus();
		return;
	}
*/	/*else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
	else if(dat3.value!="" && (dateTerimaJT < currentDate)){
	   	alert("Sila pastikan \"Tarikh Terima\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTerimaJT.focus();
		return;
	}*/
	else if (dat4.value!="" && isDate(dat4.value)==false)
	{
		dat4.focus()
		return;
	}
/*	else if(dat4.value!="" && (dateSuratJT < currentDate)){
	   	alert("Sila pastikan \"Tarikh Surat JPBD\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhSuratJT.focus();
		return;
	}
*/	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.tabId.value = "0";
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "viewMaklumat";
			document.${formName}.command2.value = "simpanMaklumatJPBD";
		}else{
			document.${formName}.id_jpbd.value = idjpbd;
			document.${formName}.command.value = "viewMaklumat";
			document.${formName}.command2.value = "kemaskiniJPBD";
			document.${formName}.command3.value = "updateJPBD";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH";
		document.${formName}.submit();
	}
}
function kembali(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH";
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
function viewMaklumat(id_permohonan) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH";
	document.${formName}.submit();
}
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
      var strValidCharacters = "1234567890./";
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
</script>

<script>
//Declaring valid date character, minimum year and maximum year
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
</script>