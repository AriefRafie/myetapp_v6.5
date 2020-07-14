<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {
	font-size: x-large;
	font-weight: bold;
}
-->
</style>
<body  onload="submitForm();" >

<div align="center" ><font  class="style2">PERMOHONAN BANTAHAN PEMBAHAGIAN PUSAKA KECIL</font></div>

<input type="hidden" name="v_tab" id="v_tab" value="" />
<input type="hidden" name="id_pemohon" id="id_pemohon" value="$!id_pemohon" />
<table width="100%" cellspacing="2" cellpadding="1" border="0">
	<tr>
		<td colspan="6">
			<!--<input type="button" value="Semakkan Status Berdasarkan MyID/No. KP Pemohon" onclick="searchbyIc()">
			<input type="button" value="Semakkan Status Berdasarkan MyID/No. KP Si Mati" onclick="searchBySiMati()">-->
            
            <fieldset>
				<legend><strong>Carian</strong></legend>
				<table width="100%" border="0">
<!-- 				  <tr> -->
<!-- 				    <td width="29%"><div align="right">MyID Pemohon</div></td> -->
<!-- 				    <td width="1%">:</td> -->
<!-- 				    <td width="70%"><input type='text' name="kppemohon" value="$!kppemohon"></td> -->
<!-- 				  </tr> -->
					<tr>
		      			<td scope="row" align="right">MyID Simati / No Fail : </td>
		      			<td width="70%"><input name="txtNoFailSub" id="txtNoFailSub" type="text" value="$txtNoFailSub" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /> </td>
		    		</tr>
		    		<tr>
		      			<td scope="row"></td>
		      			<td><input name="cmdSemakSub" id="cmdSemakSub" value="Semak" type="button" onClick="test()">
		        		<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onClick="javascript:kosongkan_sub()"></td>
		    		</tr>
<!-- 				  <tr> -->
<!-- 				    <td><div align="right">MyID Simati</div></td> -->
<!-- 				    <td>:</td> -->
<!-- 				    <td><input type='text' name="kpsimati"  value="$!kpsimati"></td> -->
<!-- 				  </tr> -->
<!-- 				  <tr> -->
<!-- 				    <td><div align="right">No Fail</div></td> -->
<!-- 				    <td>:</td> -->
<!-- 				    <td> -->
<!-- 				    <input type='hidden' name="nofail"  value="$!nofail" /> -->
<!-- 				    </td> -->
<!-- 				  </tr> -->
<!-- 				  <tr> -->
<!-- 				    <td>&nbsp;</td> -->
<!-- 				    <td>&nbsp;</td> -->
<!-- 				    <td><input type="submit" name="cmdCari" id="cmdCari" value="Semak" onclick="searchFail('$!kpsimati')"> -->
<!-- 				   </td> -->
<!-- 				  </tr> -->
				</table>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td>
			#if($view_list_fail == "yes")
			<fieldset>
		        <table width="97%"  cellpadding="1" cellspacing="2" border="0">
					<tr>
		                <td class="table_header" width="0%" style="display:none"><b></b></td>
		      		    <td class="table_header" width="0%" style="display:none"><b>ID FAIL</b></td>
		                <td class="table_header" width="15%" align="center">No. Fail</td>
		                <td class="table_header" width="15%" align="center">Nama Simati</td>
		                <td class="table_header" width="15%" align="center">Nama Pemohon</td>
		                <td class="table_header" width="15%" align="center">Status Semasa</td>
		                <td class="table_header" width="5%" align="center">Tindakan</td>
		    		</tr>
		    		
		    		#if($list_fail.size() > 0)
		    		
		    		#foreach($list in $list_fail)
		    		 #set( $i = $velocityCount )
		         		#if ( ($i % 2) != 1 )
		              		 #set( $row = "row2" )
		         		#else
		               		 #set( $row = "row1" )
		         		#end
		                
		               
		               
		               #set($tr_id = "tr_id"+$i)
		               
		              <!-- onMouseOver="tr_id_up('$tr_id','$row')" onMouseOut="tr_id_out('$tr_id','$row')" -->
		         
		         #if($list.ID_FAIL  == $id_fail_carian)
		         #set( $row = "tr_class" )
		         #end
		         
		         <input type="hidden" name="idFail" id="idFail" value="$!list.ID_FAIL" />
		         <input type="hidden" name="noFail" id="noFail" value="$!list.NO_FAIL" />
		         
		         <input type="hidden" name="icSimati" id="icSimati" value="$!list.NO_KP_BARU" />
		         
		         
		            <tr id="$tr_id" class="$row"    >
		                <td  style="display:none" align="center">  
		         <a href="javascript:paparFail('$list.ID_FAIL')"  > 
		         #if($list.ID_FAIL  == $id_fail_carian)
		         <font color="white"> Papar  </font>
		         #else
		         <font color="blue"> Papar  </font>
		         #end  
		         
		              </a>   
		                </td>
		      			
		      		    <td  style="display:none" align="center">  
		                  $list.ID_FAIL   
		                </td>
		                
		                <td  align="center"> 
		                 <a href="javascript:paparFail('$list.ID_FAIL')"  > 
		         #if($list.ID_FAIL  == $id_fail_carian)
		         <font color="black"> $list.NO_FAIL    </font>
		         #else
		         <font color="black"> $list.NO_FAIL    </font>
		         #end  
		         
		              </a>   
		                
		                </td>
		                
		                <td align="center" >
		               $list.NAMA_SIMATI
		                </td>
		                
		                <td align="center">
		                $list.NAMA_PEMOHON 
		                </td>
		                
		                 <td align="center">
		               $list.NAMA_STATUS
		                </td>
		                
		                <td align="center">
		                	<input type="button" name="btnBantah" id="btnBantah" value="Bantah" onclick="skrinBantah('$!list.ID_PERMOHONAN','$!list.ID_FAIL','$!list.NO_FAIL','$!list.NAMA_SIMATI')"/>
		                </td>
		                          
		    		</tr>
		         
		            #end
		            #else
		            
		            <tr class="row">
		      			
		      		    <td class="row" colspan="10" > 
		                Tiada Rekod              
		                </td>
		               
		                          
		    		</tr>
		            
		            #end
		    	</table>	
		    </fieldset>
			#end
		</td>
	</tr>
</table>

<br>
<br>

<table width="100%" border="0">
	<tr>
		<td>
			<fieldset>
				<legend>Senarai Bantahan Dahulu</legend>
				#parse("app/utils/record_paging.jsp")
				<table width="100%" border="0">
					<tr class="table_header">
					  	<td width="2%" align="center">No.</td>
					  	<td width="5%" align="center">Tarikh Hantar</td>
					  	<td width="15%" align="center">No. Fail</td>
					  	<td width="15%" align="center">Nama Pembantah</td>
					  	<td width="20%" align="center">Sebab Bantah</td>
					  	<td width="10%" align="center">Dokumen Sokongan</td>
				  	</tr>
					#foreach ($senarai in $senaraibantahan)
						#set( $counter = $velocityCount )
						#if ( ($counter % 2) == 0 )
							#set( $row = "row2" )
						#else
							#set( $row = "row1" )
						#end
				  	<tr>
						<td class="$row" align="center">
						  #set ($cnt = ($page - 1) * $itemsPerPage + $counter )
						  $!cnt
						 </td>
						 <td class="$row" align="center">$!senarai.tarikh_hantar</td>
						 <td class="$row" align="center">$!senarai.no_fail</td>
						 <td class="$row" align="center">$!senarai.nama_pembantah</td>
						 <td class="$row" align="center">$!senarai.sebab</td>
						 <td class="$row" align="center"></td>
					</tr>
					#end
					#if ($cnt == 0)
					<tr> 
						<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod</font></td>
					</tr>
					#end
				</table>	
			</fieldset>
		</td>
	</tr>
</table>


<!-- <fieldset> -->
<!--   <legend>Senarai Permohonan Online</legend> -->
<!--   #parse("app/utils/record_paging.jsp") -->
<!-- <table width="100%" cellspacing="2" cellpadding="1" border="0" id="header_carian"> -->
	
<!--   <tr class="table_header"> -->
<!--   	<td width="2%" align="center">No</td> -->
<!--   	<td width="25%" align="center">No Fail</td> -->
<!--   	<td width="15%" align="center">Nama Simati</td> -->
<!--   	<td width="15%" align="center">Status Fail</td> -->
<!--   	<td width="5%" align="center">Tindakan</td> -->
<!--   </tr>	 -->

<!-- #if($!status != 'SELESAI') -->
<!-- 	#foreach ($senarai in $senaraitugasan) -->
<!-- 		#set( $counter = $velocityCount ) -->
<!-- 			#if ( ($counter % 2) == 0 ) -->
<!-- 				#set( $row = "row2" ) -->
<!-- 			#else -->
<!-- 				#set( $row = "row1" ) -->
<!-- 			#end -->
	
	
	<!-- if status notis perbicaraan, tarikh sama dengan atau selepas tarikh perbicaraan or status selesai --> 
<!-- 	<tr> -->
<!-- 		<td class="$row" align="center"> -->
<!-- 		  #set ($cnt = ($page - 1) * $itemsPerPage + $counter ) -->
<!-- 		  $!cnt -->
<!-- 		 </td> -->
		 
<!-- 		 <td class="$row" align="center"> -->
<!-- 		 #if($senarai.nofail!="") -->
<!-- 			#if($!senarai.id_pemohon != "") -->
<!-- 				<a href="javascript:papar('$!senarai.id_Permohonan','$!senarai.id_simati','$!senarai.seksyen','$!senarai.id_pemohon','$!senarai.no_subjaket')" class="style1"><font color="blue"><b>$!senarai.nofail.toUpperCase()</b></font> -->
<!-- 			#else -->
<!-- 				$!senarai.nofail.toUpperCase() -->
<!-- 			#end -->
<!-- 		#else -->
<!-- 			- -->
<!-- 		#end -->
<!-- 		 </td> -->
		 
<!-- 		 <td class="$row" align="center">$!senarai.nama_simati.toUpperCase()</td> -->
<!-- 		 <td class="$row" align="center">$!senarai.status</td> -->
<!-- 		 <td class="$row" align="center"><input type="button" name="btnBantah" id="btnBantah" value="Bantah" onclick="skrinBantah('$!senarai.id_Permohonan','$!senarai.id_pemohon','$!senarai.idFail','$!senarai.nofail','$!senarai.nama_simati')"/></td> -->
<!-- 	</tr> -->
<!-- 	#end -->
<!-- 	#if ($cnt == 0) -->
<!-- 	<tr>  -->
<!-- 		<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod</font></td> -->
<!-- 	</tr> -->
<!-- 	#end -->
<!-- #end -->
<!--   </table> -->
  
<!--     <input type="hidden" name="hitButt" > -->
<!--    	<input type="hidden" name="idkemaskini" > -->
<!--    	<input type="hidden" name="fail" > -->
<!-- 	<input type="hidden" name="nopermohonan" > -->
<!-- 	<input type="hidden" name="typez" > -->
<!--    	<input type="hidden" name="pagemode" > -->
<!--    	<input type="hidden" name="langkah" value="0" > -->
<!-- 	<input type="hidden" name="page" value="$page"> -->
    
<!--     <input type="hidden" name="idpermohonan" id="idpermohonan">     -->
<!--     <input type="hidden" name="idPermohonan" id="idPermohonan">   -->
<!--     <input type="hidden" name="idSimati" id="idSimati"> -->
<!--     <input type="hidden" name="idPemohon" id="idPemohon"> -->
    
<!--     <input type="hidden" name="v_tab" id="v_tab"  /> -->
<!--     <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$!flagFromSenaraiFailSek8"/> -->
<!--     <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$!flagFromSenaraiPermohonanSek8"/> -->
<!--     <input name="flagForView" type="hidden" id="flagForView" value="$!flagForView"/> -->
<!--     <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'> -->
<!--     <input type="hidden" name="id_Permohonansimati" id="id_Permohonansimati" value="$!id_Permohonansimati" > -->
    
<!--      <input name="tabIdatas" type="hidden" id="tabIdatas" value="$!selectedTabatas"/> -->
<!-- 	 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$!selectedTabtengah"/> -->
<!-- 	 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$!selectedTabbawah"/> -->
<!-- 	 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$!selectedTabtepi"/> -->
 
<!--  	 <input name="no_subjaket" type="hidden" id="no_subjaket" value="$!no_subjaket"/> -->
    
<!--   	 <input type="hidden" name="idFail">  -->
    
<!-- </fieldset> -->

</body>

<script>

// function skrinBantah(idpemohon){
// 	console.log(idpemohon);
// 	doAjaxCall${formName}("skrinBantah");
// }

function search_main_data_sub(){
	SaveScrollXY();        
	document.${formName}.command.value = "cariMainSub";
	//document.${formName}.action = "";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnBantahanOnline";
	
	//doAjaxCall${formName}("cariMainSub");
	document.${formName}.cmdSemakSub.value = "Sila Tunggu...";	
	document.${formName}.submit();
}
function kosongkan_sub() {
	SaveScrollXY();        
	//document.${formName}.action = "";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnBantahanOnline";
	document.${formName}.command.value = "kosongkan";
	document.${formName}.txtNoFailSub.focus();
	//doAjaxCall${formName}("kosongkan");
	document.${formName}.submit();
}
function SaveScrollXY() {
    document.${formName}.ScrollX.value = document.body.scrollLeft;
    document.${formName}.ScrollY.value = document.body.scrollTop;
}

function test(){
	if (document.${formName}.txtNoFailSub.value == "0" || document.${formName}.txtNoFailSub.value == "") {
		alert('Sila masukkan MyID Simati / No Fail');
	}
	else {
		document.${formName}.command.value = "cariMainSub";
		//document.${formName}.action = "";
		document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnBantahanOnline";
		
		//doAjaxCall${formName}("cariMainSub");
		document.${formName}.cmdSemakSub.value = "Sila Tunggu...";	
		document.${formName}.submit();
	}
}

function skrinBantah(idpermohonan,idfail,nofail,simati) {
	// alert(idpermohonan+' '+idfail+' '+nofail+' '+simati);
	document.${formname}.method="post";
	// document.${formName}.mode.value ="Nowview";
	document.${formname}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnBantahanOnline&idPermohonan="+idpermohonan+"&nama_simati="+simati;
	doAjaxCall${formName}("skrinBantahNow");
	// document.${formName}.idFail.value = idfail;
	// document.${formName}.nofail.value = nofail;
	document.${formName}.submit();
}


function papar(idPermohonan,idSimati,seksyen,idpemohon,no_subjaket) {		
	if (seksyen == '8') {
		//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";
		document.${formName}.action = "$EkptgUtil.getTabID('Pusaka Kecil',$myrole)?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon&command=Simati&mode=Simatiview";
	}
	else {
		//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
		//document.${formName}.action = "?_portal_module=FrmPrmhnnSek17Senarai&command=tab";
		document.${formName}.action = "$EkptgUtil.getTabID('Pusaka Kecil',$myrole)?_portal_module=ekptg.view.ppk.FrmBorangPSek17Online&command=Simati&mode=Simatiview";
		
		}
	
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.idPemohon.value = idpemohon;
		document.${formName}.idSimati.value = idSimati;
		document.${formName}.no_subjaket.value = parseInt(no_subjaket) - 1;
				
		document.${formName}.method="POST";
        document.${formName}.submit();
}

function searchbyIc(){
	document.${formname}.command.value="searchByIc";
	doAjaxCall${formName}("searchByIc");
}
function searchBySiMati(){
	doAjaxCall${formName}("searchByIcSiMati");
}
function cariICSiMati(){
	doAjaxCall${formName}("cariSiMati");
}
function cariIC(){
	doAjaxCall${formName}("cariIc");
}
function menuUtama(){
	doAjaxCall${formName}("menuUtama");
}
function doChanges() {
	doAjaxCall${formName}("doChanges");
}

</script>
