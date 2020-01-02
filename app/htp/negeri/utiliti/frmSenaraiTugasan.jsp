<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table border="0" width="98%">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<!-- Start isMenuOnline -->
	#if ($!isMenuOnline) 
	##if ($!EkptgUtil.getTabID("<i>Online</i>",$portal_role) != "")
	
#if (!$portal_role.contains('PenggunaNegeri') && !$portal_role.contains('PegawaiNegeri') && !$portal_role.contains('PengarahNegeri'))
	

#if ($!senaraionline.size()!= 0)

		<tr>
			<td>
		##if($listFailExpired.size()!=0 && (($typeList=="online" && $ModuleName=="ekptg.view.ppt.FrmPermohonanUPTOnline") || ($typeList=="internal" && $ModuleName=="ekptg.view.ppt.MyInfoPPT")))
			<div class="warning" align="left">
				Terdapat <b>$!senaraionline.size()</b> fail permohonan yang hampir/telah tamat tempoh pendaftaran di kaunter. 
				<a href="javascript:setTable('paparanonline')"><font color="blue"><b>Klik sini</b></font></a>
				untuk papar fail.     
			</div>  
		##end
			</td>
		</tr>
		<tr>
			<td >
				<fieldset id="paparanonline" style="display:none;"><legend>Senarai Permohonan <i>Online</i></legend>
	  				<table width="100%" cellspacing="2" cellpadding="1" border="0">
					  <tr class="table_header">
					  	<td width="3%">Bil</td>
					  	<td width="22">No. Rujukan <i>Online</i></td>
					  	<td width="55%">Tajuk Fail</td>
					  	<td width="20%">Status Permohonan</td>
					  </tr>
	  
					#set ( $cnt = 0 )			
					#foreach ( $senarai in $senaraionline )
						#set ( $cnt = $cnt + 1 )
					                #set( $i = $velocityCount )
					                #if ( ($i % 2) == 0 )
					                    #set( $row = "row2" )
					                #else
					                    #set( $row = "row1" )
					                #end
					  <tr>
						  <td class="$row">$cnt.</td>
						  <td class="$row">
						  	<a  href="javascript:maklumatPermohonan('$session.getAttribute('myrole')','ekptg.view.online.htp.FrmPermohonanPengesahan')" class="style1">
							$senarai.permohonan.getNoPermohonan()
							</a>
						  </td>
						  <td class="$row">$!senarai.permohonan.getTujuan()</td>
						  <td class="$row">$!senarai.getStatusPermohonan()</td>
					  </tr>
					  
					#end
					</table>																						
			</td>
		</tr>
	#end	
		
#end
	
	#end
	<!-- End isMenuOnline -->
	
	<!-- Start isCukai -->
	#if ($!EkptgUtil.getTabID("Cukai",$portal_role) != "")

		#if (!$portal_role.contains('PenggunaNegeri') && !$portal_role.contains('PegawaiNegeri') && !$portal_role.contains('PengarahNegeri'))
		
			#if($!cukaiSenaraiPenyata.size()!=0 )
			<tr>
				<td>
				<div class="warning" align="left">
					Terdapat <b>$!vecSenaraiFailXPenyata.size()</b> negeri yang masih belum membuat penyata cukai. 
					<a href="javascript:setTable('cukaiSenaraiPenyata')"><font color="blue"><b>Klik sini</b></font></a>
					untuk papar negeri.     
				</div>  
				</td>
			</tr>
			<tr>
				<td >
					<fieldset id="cukaiSenaraiPenyata" style="display:none;"><legend>Urusan Cukai</legend>
		  				<table width="100%" cellspacing="2" cellpadding="1" border="0">
		 
		 			  	<tr class="table_header">
						  	<td width="3%">Bil</td>
						  	<td width="22">No. Fail</td>
						  	<td width="55%">Tajuk Fail</td>
						  	<td width="20%">Status Permohonan</td>
						  </tr>
		  
						#set ( $cnt = 0 )			
						#foreach ( $senarai in $vecSenaraiFailXPenyata )
							#set ( $cnt = $cnt + 1 )
						                #set( $i = $velocityCount )
						                #if ( ($i % 2) == 0 )
						                    #set( $row = "row2" )
						                #else
						                    #set( $row = "row1" )
						                #end
						  <tr>
							  <td class="$row">$cnt.</td>
							  <td class="$row">
							  	<a  href="javascript:maklumatPermohonan('$session.getAttribute('myrole')','ekptg.view.online.htp.FrmPermohonanPengesahan')" class="style1">
								$!senarai.noFail
								</a>
							  </td>
							  <td class="$row">$!senarai.tajuk</td>
							  <td class="$row">$!senarai.keterangan</td>
						  </tr>
						  
						#end 					
		  					
						</table>																						
				</td>
			</tr>
			#end
			
		#end
		
		#if($!vecCukaiHantar.size()!=0 )
			<tr>
				<td>
				<div class="warning" align="left">
					Terdapat <b>$!vecCukaiHantar.size()</b> negeri yang telah membuat penghantaran penyata cukai. 
					<a href="javascript:setTable('cukaiSenaraiHantar')"><font color="blue"><b>Klik sini</b></font></a>
					untuk papar negeri.     
				</div>  
				</td>
			</tr>
			<tr>
				<td >
					<fieldset id="cukaiSenaraiHantar" style="display:none;"><legend>Urusan Cukai</legend>
		  				<table width="100%" cellspacing="2" cellpadding="1" border="0">
		 
		 			  	<tr class="table_header">
						  	<td width="3%">Bil</td>
						  	<td width="22">No. Fail</td>
						  	<td width="55%">Tajuk Fail</td>
						  	<td width="20%">Status Permohonan</td>
						  </tr>
		  
						#set ( $cnt = 0 )			
						#foreach ( $senarai in $vecCukaiHantar )
							#set ( $cnt = $cnt + 1 )
						                #set( $i = $velocityCount )
						                #if ( ($i % 2) == 0 )
						                    #set( $row = "row2" )
						                #else
						                    #set( $row = "row1" )
						                #end
						  <tr>
							  <td class="$row">$cnt.</td>
							  <td class="$row">
							  	<a  href="javascript:maklumatCukaiHantar('$session.getAttribute('myrole')','ekptg.view.htp.FrmCukaiKemaskiniDataExcel')" class="style1">
								$!senarai.no
								</a>
							  </td>
							  <td class="$row">$!senarai.tajuk</td>
							  <td class="$row">$!senarai.keterangan</td>
						  </tr>
						  
						#end 					
		  					
						</table>																						
				</td>
			</tr>
			#end

	#end
	<!-- End isCukai -->
#if ($!EkptgUtil.getTabID("Rekod",$portal_role) != "")
	<!-- <tr>
		<td >
			<fieldset><legend>Urusan Rekod</legend>
  				<table width="100%" cellspacing="2" cellpadding="1" border="0">
  					
				</table>																						
		</td>
	</tr> -->
			#if (!$portal_role.contains('PenggunaNegeri') || !$portal_role.contains('PegawaiNegeri') || !$portal_role.contains('PengarahNegeri'))
		
			#if($!bilTugasanRekodInt!=0 )
			<tr>
				<td>
				<div class="warning" align="left">
					Terdapat <b>$!vecRekodMaklumatPembangunan.size()</b> Hakmilik/Rizab menunggu pengesahan Maklumat Pembangunan. 
					<a href="javascript:setTable('rekodMaklumatPembangunan')"><font color="blue"><b>Klik sini</b></font></a>
					untuk papar Hakmilik/Rizab.     
				</div>  
				</td>
			</tr>
			<tr>
				<td >
					<fieldset id="rekodMaklumatPembangunan" style="display:none;"><legend>Urusan Rekod</legend>
		  				<table width="100%" cellspacing="2" cellpadding="1" border="0">
		 
		 			  	<tr class="table_header">
						  	<td width="3%">Bil</td>
						  	<td width="22">No. Fail</td>
						  	<td width="55%">No. Hakmilik/ Warta </td>
						  	<td width="20%">Kegunaan Tanah</td>
						  </tr>
		  
						#set ( $cnt = 0 )			
						#foreach ( $senarai in $vecRekodMaklumatPembangunan )
							#set ( $cnt = $cnt + 1 )
						                #set( $i = $velocityCount )
						                #if ( ($i % 2) == 0 )
						                    #set( $row = "row2" )
						                #else
						                    #set( $row = "row1" )
						                #end
						  <tr>
							  <td class="$row">$cnt.</td>
							  <td class="$row">
							  	<a  href="javascript:maklumatRekodHantar('$session.getAttribute('myrole')','ekptg.view.htp.negeri.rekod.FrmRekodTanahNegeri&firstAction=carianHakmilikRizab&txtNoHakmilikC=$!senarai.noHakmilik&txtNoWartaC=$!senarai.noWarta&txtNoFailC=$!senarai.noFail')" class="style1">
								$!senarai.noFail
								</a>
							  </td>
							  <td class="$row">$!senarai.noTanah</td>
							  <td class="$row">$!senarai.kegunaan</td>
						  </tr>
						  
						#end 					
		  					
						</table>																						
				</td>
			</tr>
			#end
			
		#end
	
#end
	<tr>
		<td>
	

<!--<fieldset>
 <legend>Carian</legend>
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  	<tr class="table_header">
  		<td align="right" width="40%">No Fail&nbsp;&nbsp;:&nbsp;&nbsp;</td>
  		<td><input type=text name=nofail></td></tr>
  <tr>
  <td></td><td>				
  <input class="button" type=button value="Cari" onClick="javascript:carian();">
  <input class="button" type=button value = "Kosongkan" onClick="javascript:cancel();">
  </td>
  </tr>
  </table>
   
  
</fieldset>-->



<fieldset>
  <legend>Senarai Tugasan Individu</legend>
  <table width="100%" cellspacing="2" cellpadding="1" border="0">
  <tr class="table_header">
  	<td width="3%">Bil</td>
	<td width="22">No. Fail</td>
  	<td width="55%">Nama Fail</td>
  	<td width="20%">Status Pergerakan Fail</td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraitugasan )
  	#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
  <tr>
  <td class="$row">$cnt.</td>
  <td class="$row">
  	<a  href="javascript:maklumatTerperinci('$senarai.tabid','$senarai.modul')" class="style1">
	$senarai.no
	</a>
  </td>
  <td class="$row">$senarai.tajuk</td>
  <td class="$row">$senarai.keterangan</td>
  </tr>
  
  #end
	#if ($cnt == 0)
	<tr> 
		<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
	</tr>
	#end
  </table>
    <input type="hidden" name="command1" >
   	<input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
   	<input type="hidden" name="pagemode" >
   	<input type="hidden" name="langkah" value="0" >
</fieldset>
		</td>
	</tr>
	
	<tr>		
		<td> 
<!-- tugasan staff sepejabat -->
<fieldset>
  <legend>Senarai Lain-lain Tugasan </legend>
  <table width="100%" cellspacing="2" cellpadding="1" border="0">
  <tr class="table_header">
  	<td width="3%">Bil</td>
	<td width="22">No. Fail</td>
  	<td width="45%">Nama Fail</td>
  	<td width="15%">Status Pergerakan Fail</td>
  	<td width="15%">Didaftar Oleh</td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraitugasan )
  	#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
  <tr>
  <td class="$row">$cnt.</td>
  <td class="$row">
  	<a  href="javascript:maklumatTerperinci('$senarai.tabid','$senarai.modul')" class="style1">
	$senarai.no
	</a>
  </td>
  <td class="$row">$senarai.tajuk</td>
  <td class="$row">$senarai.keterangan</td>
  <td class="$row">$senarai.daftaroleh</td>
  </tr>
  
  #end
	#if ($cnt == 0)
	<tr> 
		<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
	</tr>
	#end
  </table>
    <input type="hidden" name="command1" >
   	<input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
   	<input type="hidden" name="pagemode" >
   	<input type="hidden" name="langkah" value="0" >
</fieldset>

		</td>
	</tr>
</table> 

<script>

	function cancel() {
		document.${formName}.reset();
	}

	function setTable(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}
function carian() {
	document.${formName}.command.value = "pksenaraifailcari";
	document.${formName}.langkah.value = '0->0';
	document.${formName}.action = "";
	document.${formName}.submit();
}

function maklumatTerperinci(id,modul) {
	//document.${formName}.command.value = "senaraiterperinci";
	//document.${formName}.langkah.value = '0->-1';
	//document.${formName}.fail.value = "";
	//alert("maklumatTerperinci:"+id);
	
	//document.${formName}.method = "post";
	//../x/${securityToken}
	//document.${formName}.action = "../c/${securityToken}?_portal_module=ekptg.view.htp.FrmCukai";
	document.${formName}.action = "../c/"+id+"?_portal_module="+modul;
	document.${formName}.submit();
	
	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmSewaanSemak";
	//document.${formName}.submit();
	
}

function tambahPermohonan() {
	document.${formName}.command.value = "pkfailbaru";
	document.${formName}.langkah.value = '0->1';
	//document.${formName}.method = "post";
	document.${formName}.pagemode.value = "0";
	document.${formName}.action = "";
	document.${formName}.submit();
	
}

	function maklumatPermohonan(id,modul) {
		//id = '$EkptgUtil.getTabID("Permohonan Online",$portal_role)';
		//id = '$EkptgUtil.getTabID("'Online'",$portal_role)';
		if(id=='adminhtp'||id=='htpperletakhakanpembelianrekod_pajakan'||id=='htprekod_perolehan'){
			id = '$EkptgUtil.getTabID("<i>Online</i>",$session.getAttribute('myrole'))';
			
		}else if(id=='htppp_1'||id=='htppp_2'||id=='htppembelian'||id=='htpperletakhakanpembelianrekod'){
			id = '$EkptgUtil.getTabID("<i>Online</i>",$session.getAttribute('myrole'))';

		}else if(id=='htppp_1'||id=='htppp_2'||id=='htppembelian'||id=='htpperletakhakanpembelianrekod'){
			id = '$EkptgUtil.getTabID("<i>Online</i>",$session.getAttribute('myrole'))';
			
		}else{
			id = '$EkptgUtil.getTabID("'Online'",$session.getAttribute('myrole'))';	
				
		}
		document.${formName}.action = "../c/"+id+"?_portal_module="+modul;
		document.${formName}.submit();	
		
	}

	function maklumatCukaiHantar(id,modul) {
		var id_ = '$EkptgUtil.getTabID("Cukai",$session.getAttribute('myrole'))';	
		
		document.${formName}.action = "../c/"+id_+"?_portal_module="+modul;
		document.${formName}.submit();	

	}

	function maklumatRekodHantar(id,modul) {
		var id_ = '$EkptgUtil.getTabID("Rekod",$session.getAttribute('myrole'))';	
		
		document.${formName}.action = "../c/"+id_+"?_portal_module="+modul;
		document.${formName}.submit();	

	}

		
</script>

<script language="JavaScript"> 
//document.forms[0].no_fail.focus(); 


</script>