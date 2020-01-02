#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")
#parse("app/kpippk/frmKPILabel.jsp")

<br/>
<center>


<!------------------ MULA BAHAGIAN PILIHAN FAIL ----------------------->
<fieldset id="top">
<legend>PILIHAN</legend>
	
	<table width="100%" border="0">
		<tr>
			<td width="11%">&nbsp;</td>
			<td width="1%">&nbsp;</td>
			<td width="20%">Urusan</td>
			<td width="1%">:</td>
			<td width="67"><b>PERMOHONAN PUSAKA KECIL</b></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td valign="top" align="right"><font color="red">*</font></td>
			<td>Sub Urusan</td>
			<td>:</td>
			<td>$!selectSuburusan</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td valign="top" align="right"><font color="red">*</font></td>
			<td>Negeri</td>
			<td>:</td>
			<td>$!selectNegeri</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td valign="top" align="right"><font color="red">*</font></td>
			<td>Pejabat / Unit</td>
			<td>:</td>
			<td>$!selectUnit</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td valign="top" align="right"><font color="red">*</font></td>
			<td>Tarikh Mula (Permohonan)</td>
			<td>:</td>
			<td><input name="txdTarikhMula" id="txdTarikhMula" size="11" type="text" value="$!txdTarikhMula" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMula',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td valign="top" align="right"><font color="red">*</font></td>
			<td>Tarikh Akhir (Permohonan)</td>
			<td>:</td>
			<td><input name="txdTarikhAkhir" id="txdTarikhAkhir" size="11" type="text" value="$!txdTarikhAkhir" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhAkhir',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
			<td>
				<input name="cmdPapar" value="Papar" type="button" onclick="javascript:paparMaklumatKPI()" />
				<input name="cmdKosong" value="Kosongkan" type="button"  onClick="javascript:clearData();" />
			</td>
		</tr>
	</table>

</fieldset>
<!------------------ TAMAT BAHAGIAN PILIHAN FAIL ----------------------->


#if($showSetupKPI=="yes" || $showFormKPI=="yes")

	#if($dataSetup.size()!=0)

	#foreach($ds in $dataSetup)	
	#set($txtF1=$ds.F1)
	#set($txtF2=$ds.F2)
	#set($txtF3=$ds.F3)
	#set($txtF4=$ds.F4)
	#set($txtF5=$ds.F5)
	#set($txtF6=$ds.F6)
	#set($txtF7=$ds.F7)
	#set($txtF8=$ds.F8)
	#set($txtF9=$ds.F9)
	#set($txtF10=$ds.F10)
	#set($txtF11=$ds.F11)
	#set($txtF12=$ds.F12)
	#set($txtF13=$ds.F13)
	#set($txtF14=$ds.F14)
	#set($txtF15=$ds.F15)
	#set($txtT1=$Utils.parseDouble($ds.T1))
	#set($txtT2=$Utils.parseDouble($ds.T2))
	#set($txtT3=$Utils.parseDouble($ds.T3))
	#set($txtT4=$Utils.parseDouble($ds.T4))
	#set($txtT5=$Utils.parseDouble($ds.T5))
	#set($txtT6=$Utils.parseDouble($ds.T6))
	#set($txtT7=$Utils.parseDouble($ds.T7))
	#set($txtT8=$Utils.parseDouble($ds.T8))
	#set($txtT9=$Utils.parseDouble($ds.T9))
	#set($txtT10=$Utils.parseDouble($ds.T10))
	#set($txtT11=$Utils.parseDouble($ds.T11))
	#set($txtT12=$Utils.parseDouble($ds.T12))	
	#set($lblT1=$Utils.parseDouble($ds.T1)+1)
	#set($lblT2=$Utils.parseDouble($ds.T2)+1)
	#set($lblT3=$Utils.parseDouble($ds.T3)+1)
	#set($lblT4=$Utils.parseDouble($ds.T4)+1)
	#set($lblT5=$Utils.parseDouble($ds.T5)+1)
	#set($lblT6=$Utils.parseDouble($ds.T6)+1)
	#set($lblT7=$Utils.parseDouble($ds.T7)+1)
	#set($lblT8=$Utils.parseDouble($ds.T8)+1)
	#set($lblT9=$Utils.parseDouble($ds.T9)+1)
	#set($lblT10=$Utils.parseDouble($ds.T10)+1)
	#set($lblT11=$Utils.parseDouble($ds.T11)+1)
	#set($lblT12=$Utils.parseDouble($ds.T12)+1)
	#end
	
	#else

	#set($txtF1=0)
	#set($txtF2=0)
	#set($txtF3=0)
	#set($txtF4=0)
	#set($txtF5=0)
	#set($txtF6=0)
	#set($txtF7=0)
	#set($txtF8=0)
	#set($txtF9=0)
	#set($txtF10=0)
	#set($txtF11=0)
	#set($txtF12=0)
	#set($txtF13=0)
	#set($txtF14=0)
	#set($txtF15=0)
	#set($txtT1=0)
	#set($txtT2=0)
	#set($txtT3=0)
	#set($txtT4=0)
	#set($txtT5=0)
	#set($txtT6=0)
	#set($txtT7=0)
	#set($txtT8=0)
	#set($txtT9=0)
	#set($txtT10=0)
	#set($txtT11=0)
	#set($txtT12=0)	
	#set($lblT1=0)
	#set($lblT2=0)
	#set($lblT3=0)
	#set($lblT4=0)
	#set($lblT5=0)
	#set($lblT6=0)
	#set($lblT7=0)
	#set($lblT8=0)
	#set($lblT9=0)
	#set($lblT10=0)
	#set($lblT11=0)
	#set($lblT12=0)	
	
	#end
	
#end




#if($showSetupKPI=="yes")
<!---------------------- MULA SETUP FORM KPI --------------------------->

#if($mode=="view")
	#if($isEdit=="yes")
		#set($fieldMode="")
		#set($fieldCSS="")	
	#else
		#set($fieldMode="readonly")
		#set($fieldCSS="class=disabled")
	#end
#else
	#set($fieldMode="")
	#set($fieldCSS="")
#end


<table width="100%" border="0">
	<tr>
		<td><div id="middle"><a href="javascript:paparMaklumatKPI()"><font color="blue"><i><b>&laquo;</b> Tutup Maklumat Penetapan Sasaran KPI</i></font></a></div></td>
	</tr>
</table>

<br/>

<fieldset>
<legend>MAKLUMAT PENETAPAN SASARAN KPI</legend>
	
	<br/>
	
	<table width="100%" border="0">
		<tr>
			<td width="10%">&nbsp;</td>
			<td width="2%">&nbsp;</td>
			<td width="52%" valign="top">PEJABAT / UNIT</td>
			<td width="2%" valign="top" align="center"><b>:</b></td>
			<td width="29%" valign="top">$!lblPejabatJKPTG</td>
			<td width="5%">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
			<td>URUSAN</td>
			<td align="center"><b>:</b></td>
			<td>PERMOHONAN PUSAKA KECIL</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
			<td>SUB URUSAN</td>
			<td align="center"><b>:</b></td>
			<td>$!lblSuburusan</td>
			<td>&nbsp;</td>
		</tr>
		
		<tr><td colspan="6">&nbsp;</td></tr>
		
		
		#if($!id_suburusan=="59")
			#parse("app/kpippk/frmKPISetupSek8.jsp")
		#else
			#parse("app/kpippk/frmKPISetupSek17.jsp")
		#end
		
		<tr><td colspan="6">&nbsp;</td></tr>
		
		<tr>
			<td colspan="6" align="center">
				
				#if($mode=="new")
				<input name="cmdSimpan" value="Simpan" type="button"  onClick="javascript:simpanSetupKPI('$!mode');" />
				<input name="cmdClear" value="Kosongkan" type="button"  onClick="javascript:clearField('$!id_suburusan');" />
				#end
				
				#if($mode=="view")
					#if($isEdit=="no")
					<input name="cmdKemaskini" value="Kemaskini" type="button"  onClick="javascript:kemaskiniSetupKPI('$!id_kpisasaran');" />
					#else
					<input name="cmdClear" value="Kosongkan" type="button"  onClick="javascript:clearField('$!id_suburusan');" />
					<input name="cmdUpdate" value="Simpan" type="button"  onClick="javascript:simpanSetupKPI('$!mode');" />
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:paparSetupKPI('2')">
					#end
				#end
			
			</td>
		</tr>
		
	</table>

</fieldset>



<!--------------------- TAMAT SETUP FORM KPI --------------------------->
#end



#if($showFormKPI=="yes")
<!------------------------- MULA FORM MAKLUMAT KPI ------------------------------>
#if($showSetupKPI=="no" && $portal_role == "adminppk")
<table width="100%" border="0">
	<tr>
		<td><a href="javascript:paparSetupKPI('1')"><font color="blue"><i><b>&raquo;</b> Buka Maklumat Penetapan Sasaran KPI</i></font></a></td>
	</tr>
</table>
#end

<br/>
<table width="70%" border="0">
	<tr>
		<td align="center"><div id="bottom"><b>SEKSYEN PEMBAHAGIAN PUSAKA KECIL : PETUNJUK PRESTASI UTAMA (LEADING KPI)</b></div></td>
	</tr>
	<tr>
		<td align="center"><b>SUB URUSAN : $!lblSuburusan</b></td>
	</tr>
	<tr>
		<td align="center"><b>PEJABAT/UNIT : $!lblPejabatJKPTG</b></td>
	</tr>
	<tr>
		<td align="center"><b>TARIKH MULA : $!txdTarikhMula &nbsp;&nbsp;&nbsp; TARIKH AKHIR : $!txdTarikhAkhir</b></td>
	</tr>
</table>

<br/>

<fieldset>
<legend>MAKLUMAT KPI</legend>

	#if($!id_suburusan=="59")
		#parse("app/kpippk/frmKPIMaklumatSek8.jsp")
	#else
		#parse("app/kpippk/frmKPIMaklumatSek17.jsp")
	#end

</fieldset>


<!------------------------- TAMAT FORM MAKLUMAT KPI ------------------------------>
#end




</center>


<!-- START HIDDEN VALUE -->

<!-- Main Id -->
<input type="hidden" name="id_suburusan" value="$!id_suburusan">
<input type="hidden" name="id_kpisasaran" value="$!id_kpisasaran">


<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- Do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<!-- Others -->
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">
<!-- END HIDDEN VALUE -->

<input type="hidden" name="idpermohonan">
<input type="hidden" name="id_status">
<input type="hidden" name="id_Simati">
<input type="hidden" name="id_permohonan">
<input type="hidden" name="id_Simati">

<!-- START MAIN JAVASCRIPT -->
<script>
function gotoPage(id_permohonan,id_fail,id_status,seksyen,id_simati) {

	if (id_status == '172'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_kolateral";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_kolateral";
		}
			document.${formName}.idpermohonan.value = id_permohonan;
			document.${formName}.id_status.value = id_status;
			document.${formName}.id_Simati.value = id_simati;

	}else if (id_status == '173' || id_status == '175' || id_status == '177' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=tukarNotis";
		}
			document.${formName}.id_permohonan.value = id_permohonan;
			document.${formName}.id_status.value = id_status;

	}else if (id_status == '53' || id_status == '151' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakNoData";
		}
			document.${formName}.id_permohonan.value = id_permohonan;
			document.${formName}.id_status.value = id_status;

	}else if (id_status == '174'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanMT";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanMT";
		}
			document.${formName}.idpermohonan.value = id_permohonan;
			document.${formName}.id_status.value = id_status;
			document.${formName}.id_Simati.value = id_simati;

	}else if (id_status == '176'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanROTS";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanROTS";
		}
			document.${formName}.idpermohonan.value = id_permohonan;
			document.${formName}.id_status.value = id_status;
			document.${formName}.id_Simati.value = id_simati;

	}else {
		alert("Sila Hubungi Sistem Admin")
	} 

	document.${formName}.submit();
	
}
function openPopupMenunggu(tajuk,jumlahFail,jenisProsesTunggu,bilHari1,bilHari2,level){

	var id_pejabatjkptg = document.${formName}.socUnit.value;
	var dateStart = document.${formName}.txdTarikhMula.value;
	var dateEnd = document.${formName}.txdTarikhAkhir.value;
	var id_suburusan = document.${formName}.socSuburusan.value;
	
	if(jumlahFail=="0"){
		alert("Tiada fail di dalam rekod");
		return;
	}else if(document.${formName}.socUnit.value == ""){
		alert("Sila pilih \"Pejabat / Unit\" terlebih dahulu. ");
		document.${formName}.socUnit.focus();
		return;
	}else if(document.${formName}.socSuburusan.value == ""){
		alert("Sila pilih \"Sub Urusan\" terlebih dahulu. ");
		document.${formName}.socSuburusan.focus();
		return;
	}else if(document.${formName}.txdTarikhMula.value == ""){
		alert("Sila masukkan \"Tarikh Mula\" terlebih dahulu. ");
		document.${formName}.txdTarikhMula.focus();
		return;
	}else if(document.${formName}.txdTarikhAkhir.value == ""){
		alert("Sila masukkan \"Tarikh Akhir\" terlebih dahulu. ");
		document.${formName}.txdTarikhAkhir.focus();
		return;
	}else{

		var mainurl = "../x/${securityToken}/ekptg.ppk.kpi.FrmKPIPopupMenunggu";
		var mainItem = "&id_pejabatjkptg="+id_pejabatjkptg+"&dateStart="+dateStart+"&dateEnd="+dateEnd+"&id_suburusan="+id_suburusan;
		var url = mainurl+"?title="+tajuk+"&type="+jenisProsesTunggu+"&date1="+bilHari1+"&date2="+bilHari2+"&level="+level+""+mainItem;
		var hWnd = window.open(url,'displayfile','width=1200,height=300,align=center, resizable=yes,scrollbars=yes');	
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus(); 
	}
	
}

function kemaskiniSetupKPI(id_kpisasaran){
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.command.value = "paparMaklumatKPI";
	document.${formName}.command2.value = "paparSetupKPI";
	document.${formName}.command3.value = "kemaskiniSetupKPI";
	document.${formName}.action = "?_portal_module=ekptg.ppk.kpi.FrmKPIUtamaView";
	document.${formName}.submit();
}

function simpanSetupKPI(mode){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "middle";
	if(mode=="view"){
		document.${formName}.command.value = "paparMaklumatKPI";
		document.${formName}.command2.value = "paparSetupKPI";
		document.${formName}.command3.value = "kemaskiniSetupKPI";
		document.${formName}.command4.value = "updateSetupKPI";
	}else{
		document.${formName}.command.value = "paparMaklumatKPI";
		document.${formName}.command2.value = "paparSetupKPI";
		document.${formName}.command3.value = "simpanSetupKPI";
	}
	document.${formName}.action = "?_portal_module=ekptg.ppk.kpi.FrmKPIUtamaView";
	document.${formName}.submit();
}

if('$showSetupKPI' == "yes"){
	valueTetapan('$id_suburusan');
}

function valueTetapan(jenisUrusan) {

	 var txtT1 = document.${formName}.txtT1.value;
	 var txtT2 = document.${formName}.txtT2.value;
	 var txtT3 = document.${formName}.txtT3.value;
	 var txtT4 = document.${formName}.txtT4.value; 
	 var txtT5 = document.${formName}.txtT5.value;
	 var txtT6 = document.${formName}.txtT6.value;
	 var txtT7 = document.${formName}.txtT7.value;
	 var txtT8 = document.${formName}.txtT8.value; 
	 var txtT9 = document.${formName}.txtT9.value;
	 var txtT10 = document.${formName}.txtT10.value; 

	 if(jenisUrusan=="59"){
	 var txtT11 = document.${formName}.txtT11.value;
	 var txtT12 = document.${formName}.txtT12.value;
	 }
	 
	 var lblT1 = 0;
	 var lblT2 = 0;
	 var lblT3 = 0;
	 var lblT4 = 0; 
	 var lblT5 = 0;
	 var lblT6 = 0;
	 var lblT7 = 0;
	 var lblT8 = 0; 
	 var lblT9 = 0;
	 var lblT10 = 0;
	 
	 if(jenisUrusan=="59"){
	 var lblT11 = 0;
	 var lblT12 = 0;  
	 }
	 
	 if(txtT1!=""){lblT1 = parseFloat(txtT1)+1;} 
	 if(txtT2!=""){lblT2 = parseFloat(txtT2)+1;} 
	 if(txtT3!=""){lblT3 = parseFloat(txtT3)+1;} 
	 if(txtT4!=""){lblT4 = parseFloat(txtT4)+1;} 
	 if(txtT5!=""){lblT5 = parseFloat(txtT5)+1;} 
	 if(txtT6!=""){lblT6 = parseFloat(txtT6)+1;} 
	 if(txtT7!=""){lblT7 = parseFloat(txtT7)+1;} 
	 if(txtT8!=""){lblT8 = parseFloat(txtT8)+1;} 
	 if(txtT9!=""){lblT9 = parseFloat(txtT9)+1;} 
	 if(txtT10!=""){lblT10 = parseFloat(txtT10)+1;} 

	 if(jenisUrusan=="59"){
	 if(txtT11!=""){lblT11 = parseFloat(txtT11)+1;} 
	 if(txtT12!=""){lblT12 = parseFloat(txtT12)+1;} 
	 }
	 
	 $jquery("#lblT1").html("<font color='blue' style='text-align:center' ><strong>"+lblT1+"</strong></font>");
	 $jquery("#lblT2").html("<font color='blue' style='text-align:center'><strong>"+lblT2+"</strong></font>");
	 $jquery("#lblT3").html("<font color='blue' style='text-align:center'><strong>"+lblT3+"</strong></font>");
	 $jquery("#lblT4").html("<font color='blue' style='text-align:center'><strong>"+lblT4+"</strong></font>");
	 $jquery("#lblT5").html("<font color='blue' style='text-align:center' ><strong>"+lblT5+"</strong></font>");
	 $jquery("#lblT6").html("<font color='blue' style='text-align:center'><strong>"+lblT6+"</strong></font>");
	 $jquery("#lblT7").html("<font color='blue' style='text-align:center'><strong>"+lblT7+"</strong></font>");
	 $jquery("#lblT8").html("<font color='blue' style='text-align:center'><strong>"+lblT8+"</strong></font>");
	 $jquery("#lblT9").html("<font color='blue' style='text-align:center' ><strong>"+lblT9+"</strong></font>");
	 $jquery("#lblT10").html("<font color='blue' style='text-align:center'><strong>"+lblT10+"</strong></font>");

	 if(jenisUrusan=="59"){
	 $jquery("#lblT11").html("<font color='blue' style='text-align:center'><strong>"+lblT11+"</strong></font>");
	 $jquery("#lblT12").html("<font color='blue' style='text-align:center'><strong>"+lblT12+"</strong></font>");
	 }
}

function clearField(jenisUrusan){
	if ( !window.confirm("Adakah Anda Pasti Untuk Set Semula Nilai?") ) return;

	document.getElementById("txtF1").value = "0" ;
	document.getElementById("txtF2").value = "0" ;
	document.getElementById("txtF3").value = "0" ;
	document.getElementById("txtF4").value = "0" ;
	document.getElementById("txtF5").value = "0" ;
	document.getElementById("txtF6").value = "0" ;
	document.getElementById("txtF7").value = "0" ;
	document.getElementById("txtF8").value = "0" ;
	document.getElementById("txtF9").value = "0" ;
	document.getElementById("txtF10").value = "0" ;
	document.getElementById("txtF11").value = "0" ;
	document.getElementById("txtF12").value = "0" ;
	document.getElementById("txtF13").value = "0" ;
	document.getElementById("txtF14").value = "0" ;

	if(jenisUrusan=="59"){
	document.getElementById("txtF15").value = "0" ;
	}
	
	document.getElementById("txtT1").value = "0" ;
	document.getElementById("txtT2").value = "0" ;
	document.getElementById("txtT3").value = "0" ;
	document.getElementById("txtT4").value = "0" ;
	document.getElementById("txtT5").value = "0" ;
	document.getElementById("txtT6").value = "0" ;
	document.getElementById("txtT7").value = "0" ;
	document.getElementById("txtT8").value = "0" ;
	document.getElementById("txtT9").value = "0" ;
	document.getElementById("txtT10").value = "0" ;

	if(jenisUrusan=="59"){
	document.getElementById("txtT11").value = "0" ;
	document.getElementById("txtT12").value = "0" ;
	}

	$jquery("#lblT1").html("<font color='blue' style='text-align:center' ><strong>1</strong></font>");
	$jquery("#lblT2").html("<font color='blue' style='text-align:center'><strong>1</strong></font>");
	$jquery("#lblT3").html("<font color='blue' style='text-align:center'><strong>1</strong></font>");
	$jquery("#lblT4").html("<font color='blue' style='text-align:center'><strong>1</strong></font>");
	$jquery("#lblT5").html("<font color='blue' style='text-align:center' ><strong>1</strong></font>");
	$jquery("#lblT6").html("<font color='blue' style='text-align:center'><strong>1</strong></font>");
	$jquery("#lblT7").html("<font color='blue' style='text-align:center'><strong>1</strong></font>");
	$jquery("#lblT8").html("<font color='blue' style='text-align:center'><strong>1</strong></font>");
	$jquery("#lblT9").html("<font color='blue' style='text-align:center' ><strong>1</strong></font>");
	$jquery("#lblT10").html("<font color='blue' style='text-align:center'><strong>1</strong></font>");

	if(jenisUrusan=="59"){
	$jquery("#lblT11").html("<font color='blue' style='text-align:center'><strong>1</strong></font>");
	$jquery("#lblT12").html("<font color='blue' style='text-align:center'><strong>1</strong></font>");
	}
	
}

function onchangeNegeri() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "onchangeNegeri";
	document.${formName}.action = "?_portal_module=ekptg.ppk.kpi.FrmKPIUtamaView";
	document.${formName}.submit();
}
function clearData() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.ppk.kpi.FrmKPIUtamaView";
	document.${formName}.submit();
}
function paparMaklumatKPI() {

	var dat1=document.${formName}.txdTarikhMula;
	var dat2=document.${formName}.txdTarikhAkhir;

	//tarikh mula
	var tMula  = document.getElementById("txdTarikhMula").value;
	var dt1   = parseInt(tMula.substring(0,2),10);
   	var mon1  = parseInt(tMula.substring(3,5),10);
   	var yr1   = parseInt(tMula.substring(6,10),10);
   	var dateMula = new Date(yr1, mon1, dt1);

   	//tarikh akhir
   	var tAkhir  = document.getElementById("txdTarikhAkhir").value;
	var dt2   = parseInt(tAkhir.substring(0,2),10);
   	var mon2  = parseInt(tAkhir.substring(3,5),10);
   	var yr2   = parseInt(tAkhir.substring(6,10),10);
   	var dateAkhir = new Date(yr2, mon2, dt2);
	
	if(document.${formName}.socSuburusan.value == ""){
		alert("Sila pilih \"Sub Urusan\" terlebih dahulu. ");
		return;
	}else if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu. ");
		return;
	}else if(document.${formName}.socUnit.value == ""){
		alert("Sila pilih \"Pejabat / Unit\" terlebih dahulu. ");
		return;
	}else if(document.${formName}.txdTarikhMula.value == ""){
		alert("Sila masukkan \"Tarikh Mula\" terlebih dahulu. ");
		return;
	}else if (dat1.value!="" && isDate(dat1.value)==false){
		dat1.focus()
		return;
	}else if(document.${formName}.txdTarikhAkhir.value == ""){
		alert("Sila masukkan \"Tarikh Akhir\" terlebih dahulu. ");
		return;
	}else if (dat2.value!="" && isDate(dat2.value)==false){
		dat2.focus()
		return;
	}else if(dateAkhir < dateMula){
   		alert("Sila pastikan \"Tarikh Akhir\" tidak kurang dari \"Tarikh Mula\".");
	 	document.${formName}.txdTarikhAkhir.focus();
	 	return;
	}else{
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.command.value = "paparMaklumatKPI";
		document.${formName}.action = "?_portal_module=ekptg.ppk.kpi.FrmKPIUtamaView";
		document.${formName}.submit();
	}
	
}
function paparSetupKPI(mode) {
	if(mode=="2"){
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
	}
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.command.value = "paparMaklumatKPI";
	document.${formName}.command2.value = "paparSetupKPI";
	document.${formName}.action = "?_portal_module=ekptg.ppk.kpi.FrmKPIUtamaView";
	document.${formName}.submit();
}

</script>
<!-- END MAIN JAVASCRIPT -->


<!-- START OTHERS JAVASCRIPT -->
<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
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
      var strValidCharacters = "1234567890.";
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
<!-- END OTHERS JAVASCRIPT -->