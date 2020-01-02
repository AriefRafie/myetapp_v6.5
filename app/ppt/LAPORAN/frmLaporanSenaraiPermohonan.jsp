<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>		
    	<td>

<fieldset>
<legend>CARIAN</legend>
	
	<table width="100%" border="0" >
		 
  		<tr>
    		<td width="1%">&nbsp;</td>
    		<td width="20%" align="left">Negeri</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectNegeri</td>
    	</tr>
    	
    	<tr>
    		<td>&nbsp;</td>
    		<td align="left">Daerah</td>
    		<td align="center">:</td>
    		<td>$!selectDaerah</td>
    	</tr>
       
        <tr>
    		<td>&nbsp;</td>
    		<td align="left">Kementerian</td>
    		<td align="center">:</td>
    		<td>$!selectKementerian</td>
    	</tr> 
       
        <tr>
    		<td>&nbsp;</td>
    		<td align="left">Pengambilan</td>
    		<td align="center">:</td>
    		<td>
				#set($checkedMilik = "")
				#set($checkedRizab = "")
				#set($checkedMilikRizab = "")
				#if($idUrusan == "51")
					#set($checkedMilik = "checked")
					#set($checkedRizab = "")
					#set($checkedMilikRizab = "")
					            
				#elseif($idUrusan == "52")
					#set($checkedRizab ="checked")
					#set($checkedMilik ="")
					#set($checkedMilikRizab = "")
				
				#elseif($idUrusan == "1,10")
					#set($checkedRizab ="")
					#set($checkedMilik ="")
					#set($checkedMilikRizab = "checked")
				
				#else
					#set($checkedRizab ="")
					#set($checkedMilik ="")
					#set($checkedMilikRizab = "")
									
				#end
			    	<input type="radio" name="socJenisTanahtemp" value="51" $checkedMilik  onclick="senaraiLaporan()"/>SEKSYEN 4
					<input type="radio" name="socJenisTanahtemp" value="52" $checkedRizab  onclick="senaraiLaporan()"/>SEKSYEN 8
					<!-- 
						<input type="radio" name="socJenisTanahtemp" value="1,10" $checkedMilikRizab  onclick="senaraiLaporan()"/>PERMOHONAN/ PERIZAPAN TANAH
					 -->
					<input type="hidden" name="socJenisTanah" value="$!jenisTanah">    		
    		</td>
    	</tr> 
       <!--
        <tr>
    		<td>&nbsp;</td>
    		<td align="left">Kementerian</td>
    		<td align="center">:</td>
    		<td>$!selectKementerian</td>
    	</tr> 
    	 -->
    	
    	#if($jenisTempoh=="sehingga" || $jenisTempoh=="seluruh")
    		#set($radioA="checked")
    		#set($radioB="")
    		#set($radioC="")
    	#elseif($jenisTempoh=="julat")
    		#set($radioA="")
    		#set($radioB="checked")
    		#set($radioC="")
    	#else
    		#set($radioA="")
    		#set($radioB="")
    		#set($radioC="checked")
    	#end
    	
    	<tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Jenis Tempoh Tarikh Permohonan</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">
    			<input type="radio" $radioA name="sorJenisTempoh" id="sorJenisTempoh" value="seluruh" onChange="doChange()" >Keseluruhan
    			<input type="radio" $radioB name="sorJenisTempoh" id="sorJenisTempoh" value="julat" onChange="doChange()" >Julat Masa
    			<!--   <input type="radio" $radioC name="sorJenisTempoh" id="sorJenisTempoh" value="semasa" onChange="doChange()" >Tahun Semasa -->
    		</td>
    	</tr>
    
    </table>	
    
    
    <!-- SEHINGGA -->
    #if($!jenisTempoh=="sehingga")
    <!--  <table width="100%" border="0" >	
    	<tr>
    		<td width="1%">&nbsp;</td>
    		<td width="20%" align="left">&nbsp;</td>
    		<td width="1%" align="center">&nbsp;</td>
    		<td width="7%">Bulan</td>
    		<td width="71%">$!selectBulan</td>
    	</tr>
    
    	<tr>
    		<td>&nbsp;</td>
    		<td align="left">&nbsp;</td>
    		<td align="center">&nbsp;</td>
    		<td>Tahun</td>
    		<td>$!selectTahun</td>
    	</tr>
	</table>
	-->
	#elseif($!jenisTempoh=="julat")
	<table width="100%" border="0" >	
    	<tr>
    		<td width="1%">&nbsp;</td>
    		<td width="20%" align="left">&nbsp;</td>
    		<td width="1%" align="center">&nbsp;</td>
    		<td width="7%">Bulan</td>
    		<td width="22%">$!selectBulan</td>
    		<td width="8%" rowspan="2"><b>Sehingga</b></td>
    		<td width="7%">Bulan</td>
    		<td width="34%">$!selectBulanSehingga</td>
    	</tr>
    
    	<tr>
    		<td>&nbsp;</td>
    		<td align="left">&nbsp;</td>
    		<td align="center">&nbsp;</td>
    		<td>Tahun</td>
    		<td>$!selectTahun</td>
    		<td>Tahun</td>
    		<td>$!selectTahunSehingga</td>
    	</tr>
	</table>
	
	#else
	<input type="hidden" name="socBulan" value="$!bulan">
	<input type="hidden" name="socTahun" value="$!tahun">
	#end
    
    
	
</fieldset>
<!--
<fieldset>
	<table width="90%" border="0" >
		<tr>
  			<td align="center">
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" /> 
      			<input type="button" name="cmdIsiSemula" id="cmdIsiSemula" value="Kosongkan" onClick="kosongkan()"> 
    		</td>
  		</tr>
	</table>
</fieldset>
-->

		<fieldset id="tableReport1" style="display:yes;">
		<legend><strong>Senarai Laporan</strong></legend>
			<table width="100%" border="0" cellspacing="2" cellpadding="2"> 
			##if($!jenisTempoh=="seluruh")
    			<tr>
		      		<td><a href="javascript:cetakSenarai('$!jenisTempoh','PPTPermohonanOnlineMengikutNegeri')" class="style2" ><font color="blue">Laporan Senarai Permohonan <i>Online</i> Mengikut Negeri</font></a></td>
		      	</tr>      
    			<tr>
		      		<td><a href="javascript:cetakSenarai('$!jenisTempoh','PPTPermohonanOnlineMengikutDaerah')" class="style2" ><font color="blue">Laporan Senarai Permohonan <i>Online</i> Mengikut Daerah</font></a></td>
		      	</tr> 
		      	<tr>
		        	<td><a href="javascript:cetakSenarai('$!jenisTempoh','PPTPermohonanOnlineMengikutKementerian')" class="style2" ><font color="blue">Laporan Senarai Permohonan <i>Online</i> Mengikut Kementerian</font></a></td>
		      	</tr> 
		      	<!--
		    	<tr>
		      		<td><a href="javascript:cetakRingkasan('$!jenisTempoh','PPTRingkasanPermohonanOnlineMengikutNegeri')" class="style2" ><font color="blue">Laporan Ringkasan Permohonan <i>Online</i> Mengikut Negeri</font></a></td>
		      	</tr>      
		      	<tr>
		        	<td><a href="javascript:cetakRingkasan('$!jenisTempoh','PPTRingkasanPermohonanOnlineMengikutKementerian')" class="style2" ><font color="blue">Laporan Ringkasan Permohonan <i>Online</i> Mengikut Kementerian</font></a></td>
		      	</tr>   
		  	##else
    			<tr>
		      		<td><a href="javascript:cetakRingkasan('$!jenisTempoh','PPTRingkasanPermohonanMengikutNegeriSelang')" class="style2" ><font color="blue">Laporan Ringkasan Permohonan Mengikut Negeri</font></a></td>
		      	</tr>      
		      	<tr>
		        	<td><a href="javascript:cetakRingkasan('$!jenisTempoh','PPTRingkasanPermohonanMengikutKementerianSelang')" class="style2" ><font color="blue">Laporan Ringkasan Permohonan Mengikut Kementerian</font></a></td>
		      	</tr>       		      
		    	<tr>
		      		<td><a href="javascript:cetakRingkasan('$!jenisTempoh','PPTRingkasanPermohonanOnlineMengikutNegeriSelang')" class="style2" ><font color="blue">Laporan Ringkasan Permohonan <i>Online</i> Mengikut Negeri</font></a></td>
		      	</tr>      
		      	<tr>
		        	<td><a href="javascript:cetakRingkasan('$!jenisTempoh','PPTRingkasanPermohonanOnlineMengikutKementerianSelang')" class="style2" ><font color="blue">Laporan Ringkasan Permohonan <i>Online</i> Mengikut Kementerian</font></a></td>
		      	</tr>		  			      	
			-->
		  	##end    	 		      
		    </table>
		</fieldset>

<!-- Hidden -->
<input type="hidden" name="id_pejabat" value="$!id_pejabat">
<input type="hidden" name="id_daerah" value="$!id_daerah">
<input type="hidden" name="tahun" value="$!tahun">
<input type="hidden" name="bulan" value="$!bulan">
<input type="hidden" name="jenisTempoh" value="$!jenisTempoh">
<input type="hidden" name="tahunSE" value="$!tahunSE">
<input type="hidden" name="bulanSE" value="$!bulanSE">

	    </td>
	</tr>	
</table>

<script>
	function doChange() {
		document.${formName}.command.value = "doChange";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.laporan.FrmLaporanSenaraiPermohonan";
		document.${formName}.submit();
		//doAjaxCall${formName}("doChange");

	}

	function doChangeNegeri() {
		if(document.${formName}.socNegeri.value=="0")
			return;
		doAjaxCall${formName}("PilihNegeri");
	}	

function kosongkan() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.LaporanBulananProjek";
	document.${formName}.submit();
}

	function cetakSenarai(jenisTempoh,tem){
		var ptem = "&template="+tem;
		var pengambilan = "ID_SUBURUSAN=51,52";
		var bulan = "00";
		var tahun = "0000";
		var psehingga = "&";

		if((jenisTempoh=="sehingga" || jenisTempoh=="julat" && document.${formName}.socBulan.value == "") ){
			alert("Sila pilih \"Bulan\" terlebih dahulu.");
			document.${formName}.socBulan.focus();
			return;
			
		}else if((jenisTempoh=="sehingga" || jenisTempoh=="julat") && document.${formName}.socTahun.value == ""){
			alert("Sila pilih \"Tahun\" terlebih dahulu.");
			document.${formName}.socTahun.focus();
			return;

			//julat
		}else if(jenisTempoh=="julat" && document.${formName}.socBulanSehingga.value == ""){
			alert("Sila pilih \"Bulan Sehingga\" terlebih dahulu.");
			document.${formName}.socBulanSehingga.focus();
			return;
			
		}else if(jenisTempoh=="julat" && document.${formName}.socTahunSehingga.value == ""){
			alert("Sila pilih \"Tahun Sehingga\" terlebih dahulu.");
			document.${formName}.socTahunSehingga.focus();
			return;

		}else{		
			var bulanSE = "";
			var bulanSE2 = "";
			var tahunSE = "";
			var bulantahunSE = "";
			var type = "";
			//alert('jenisTempoh='+jenisTempoh);
			if(jenisTempoh=="julat"){
				bulanSE = parseInt(document.${formName}.socBulanSehingga.value);
				bulanSE2 = parseInt(document.${formName}.socBulanSehingga.value);
				tahunSE = document.${formName}.socTahunSehingga.value;
				bulantahunSE = bulanSE+"/"+tahunSE
				type = "2";

				var bulan = "0";
				if(document.${formName}.bulan.value.length==1) {
					bulan += document.${formName}.bulan.value;	
				}else{
					bulan = document.${formName}.bulan.value;	
				}
				var bulanSehingga = "0";
				if(document.${formName}.socBulanSehingga.value.length==1) {
					bulanSehingga += document.${formName}.socBulanSehingga.value;	
				}else{
					bulanSehingga = document.${formName}.socBulanSehingga.value;	
				}
					
				var tahun = document.${formName}.tahun.value;
				psehingga += "BULAN_TAMAT="+bulanSehingga+"&TAHUN_TAMAT="+tahunSE;
				
			}else{
				type = "3";
				psehingga += "BULAN_TAMAT=00&TAHUN_TAMAT=2013";
				
			}

			if(document.${formName}.socJenisTanah.value!=""){
				pengambilan = "ID_SUBURUSAN="+document.${formName}.socJenisTanah.value;
			}
			
			if((tem =="PPTPermohonanOnlineMengikutNegeri" && document.${formName}.socNegeri.value=="0")
				){	
				ptem = "&template="+tem+"Semua";
				
			}
			ptem +="&ID_NEGERI="+document.${formName}.socNegeri.value;
			
			if((tem =="PPTPermohonanOnlineMengikutDaerah" && document.${formName}.socNegeri.value=="0" )
			){	
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus();
				return;
								
			}
			if((tem =="PPTPermohonanOnlineMengikutDaerah" && (document.${formName}.socDaerah.value=="0" || document.${formName}.socDaerah.value==""))
			){	
				ptem = "&template="+tem+"Semua&ID_NEGERI="+document.${formName}.socNegeri.value;
				
			}
			//ptem +="&ID_NEGERI="+document.${formName}.socNegeri.value+"&ID="+document.${formName}.socDaerah.value;
			ptem +="&ID="+document.${formName}.socDaerah.value;
			
			if((tem =="PPTPermohonanOnlineMengikutKementerian" && document.${formName}.socKementerian.value=="")
			){	
				ptem = "&template="+tem+"Semua";
				
			}
			ptem +="&ID_KEMENTERIAN="+document.${formName}.socKementerian.value;			
			//alert(bulan+","+tahun);
			
			var item = "&BULAN="+bulan+"&TAHUN="+tahun+"&type="+type;
		 	var url = "../servlet/ekptg.report.ppt.LaporanRingkasanProjek?"+pengambilan+item+psehingga+ptem; 
			//var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
			var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		    
		}
	
	}


function showDaerah(){
 document.${formName}.command.value = "daerah";
 document.${formName}.submit();

}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		//window.location.hash=id;
        //goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function setTable(id,idselang){
	if(document.getElementById(idselang).style.display=="none"){
		document.getElementById(idselang).style.display="block";

	}else if(document.getElementById(idselang).style.display=="block"){
		document.getElementById(idselang).style.display="none";
	}

	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";

	}else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
	
}

	function senaraiLaporan(){   
		field_ = document.${formName}.socJenisTanahtemp;
		for (i = 0; i < field_.length; i++){
			if(field_[i].checked==true){
				document.${formName}.socJenisTanah.value = field_[i].value;
			}
		}
		//doAjaxCall${formName}("senarailaporan");
		
	}


</script>
