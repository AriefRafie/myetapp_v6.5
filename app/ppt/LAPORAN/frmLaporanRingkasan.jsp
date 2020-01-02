<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>		
    	<td>

<fieldset>
<legend>CARIAN</legend>
	
	<table width="100%" border="0" >
		<!-- 
  		<tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Nama Pejabat JKPTG</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectPejabat</td>
    	</tr>
    	
    	<tr>
    		<td><font color="red">*</font></td>
    		<td align="left">Daerah</td>
    		<td align="center">:</td>
    		<td>$!selectDaerah</td>
    	</tr>
        
        <tr>
    		<td></td>
    		<td align="left">Nama Projek</td>
    		<td align="center">:</td>
    		<td>
            <input type = "text" id = "projek" name = "projek" size="60" maxlength="4000"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />

            </td>
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
			#if($!jenisTempoh=="seluruh")
    			<tr>
		      		<td><a href="javascript:cetakRingkasan('$!jenisTempoh','PPTRingkasanPermohonanMengikutNegeri')" class="style2" ><font color="blue">Laporan Ringkasan Permohonan Mengikut Negeri</font></a></td>
		      	</tr>      
		      	<tr>
		        	<td><a href="javascript:cetakRingkasan('$!jenisTempoh','PPTRingkasanPermohonanMengikutKementerian')" class="style2" ><font color="blue">Laporan Ringkasan Permohonan Mengikut Kementerian</font></a></td>
		      	</tr>       		      
		    	<tr>
		      		<td><a href="javascript:cetakRingkasan('$!jenisTempoh','PPTRingkasanPermohonanOnlineMengikutNegeri')" class="style2" ><font color="blue">Laporan Ringkasan Permohonan <i>Online</i> Mengikut Negeri</font></a></td>
		      	</tr>      
		      	<tr>
		        	<td><a href="javascript:cetakRingkasan('$!jenisTempoh','PPTRingkasanPermohonanOnlineMengikutKementerian')" class="style2" ><font color="blue">Laporan Ringkasan Permohonan <i>Online</i> Mengikut Kementerian</font></a></td>
		      	</tr>      
		  	#else
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
		  	#end    	 		      
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
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.laporan.FrmLaporanRingkasanPermohonan";
		document.${formName}.submit();
		//doAjaxCall${formName}("doChange");

	}

function kosongkan() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.LaporanBulananProjek";
	document.${formName}.submit();
}

	function cetakRingkasan(jenisTempoh,tem){
		var ptem = "&template="+tem;
		var bulan = "";
		var tahun = "";
		var psehingga = "&";
		/*if(document.${formName}.socPejabat.value == ""){
	   		alert("Sila pilih \"Pejabat JKPTG\" terlebih dahulu.");
			document.${formName}.socPejabat.focus();
			return;
			
		}	
		//Sehingga	
		else */
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
			var template = "LaporanRingkasanProjekMain";
			if(jenisTempoh=="julat"){
				bulanSE = parseInt(document.${formName}.socBulanSehingga.value);
				bulanSE2 = parseInt(document.${formName}.socBulanSehingga.value);
				tahunSE = document.${formName}.socTahunSehingga.value;
				bulantahunSE = bulanSE+"/"+tahunSE
				type = "2";
				template = "LaporanRingkasanProjekMainSelang";

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
				
			}else if(jenisTempoh=="sehingga"){
				type = "1";
				template = "LaporanRingkasanProjekMainSemasa";
					
			}else{
				type = "3";
				var bulan = 00;
				var tahun = 0000;
				//setTable('tableReport1','tableReport2')
				
			}
		/*
			var id_pejabat = document.${formName}.id_pejabat.value;
			var id_daerah = document.${formName}.id_daerah.value;
			var bulan = document.${formName}.bulan.value;
			var tahun = document.${formName}.tahun.value;
			var projek = document.${formName}.projek.value;		
		
			if(projek!=""){
				projek = document.${formName}.projek.value;
			}else{		
				projek = "NONE";	
			}	
			var bulantahun = bulan+"/"+tahun;
			*/
			//var item = "ID_PEJABAT='"+id_pejabat+"'&BULANSE='"+bulanSE2+"'&TAHUNSE='"+tahunSE+"'&BULAN='"+bulan+"'&type="+type+"&TAHUN='"+tahun+"'&bulantahun='"+bulantahun+"'&ID_DAERAH='"+id_daerah+"'&jenisTempoh='"+jenisTempoh+"'&bulantahunSE='"+bulantahunSE+"'"+"&projek="+projek+"";		
			var item = "&BULAN="+bulan+"&type="+type+"&TAHUN="+tahun;
		 	var url = "../servlet/ekptg.report.ppt.LaporanRingkasanProjek?"+item+ptem+psehingga 
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


</script>
