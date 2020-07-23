 <!--frmCukaiPeringkatBayaran.jsp-->
<!-- CL-02-016 -->
<style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>	&nbsp;
		</td>						
	</tr>

	<tr>
		<td>
			#parse("app/utils/record_paging.jsp")			
		</td>						
	</tr>

	<tr>
		<td>
  
			  <fieldset><legend>PERINGKAT BAYARAN CUKAI</legend>
			
			  <table width="100%" cellspacing="1" cellpadding="2" border="0">
			  <tr class="table_header">
			  	<td>Bil.</td>
			  	<td>Negeri</td>
			  	<td>Peringkat Bayaran</td>
			  	<td>No. Fail</td>
			  	<td>Status Fail</td>
			  </tr>
			  
			  #set ( $cnt = 0 )			
			  ##foreach ( $senarai in $senaraiList )
			  #foreach ( $senarai in $SenaraiFail)
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
			  	
				$senarai.negeri
				
			  </td>
			  <td class="$row">
			  	#set ( $statusPeringkat1 = "Peringkat Negeri" ) 
			  	#set ( $statusPeringkat2 = "Peringkat Daerah" ) 
			  	#set ( $statusDisplay = "" ) 
			  	#if( $senarai.peringkatBayaran == "1" )
			  		#set ($statusDisplay = $statusPeringkat1)
			  	#else
			    	#set ($statusDisplay = $statusPeringkat2)
			  	#end
			  				
			  		#if( $senarai.peringkatBayaran == "TIADA" )
			
			    <select name="socbayaran" onchange="selectBayaran('$senarai.negeri','$senarai.idNegeri','$senarai.idPermohonan')" >
			        <option value="0">Sila Pilih Peringkat Bayaran</option>
			        <option value="1">$statusPeringkat1</option>
			        <option value="2">$statusPeringkat2</option>
			    </select>
			           
			  </td>
			    <td class="$row">$senarai.noFail</td>
			    	#else
			         	$statusDisplay
			   </td>
			    <td class="$row"><a href="javascript:senaraiCukai('$senarai.negeri','$senarai.idNegeri','$senarai.idPermohonan','$senarai.idFail','$senarai.peringkatBayaran')" class="style1" name=>$senarai.noFail</a></td>
			        #end
			    <td class="$row">$senarai.keterangan</td>
			  </tr>
			  #end
			  
			#if ($cnt == 0)
			  	<tr> 
			  		<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
			  	</tr>
			#end
			  </table>

			
			</fieldset>

		</td> 		
	</tr>
</table>
			    <input type="hidden" name="command1" >
			   	<input type="hidden" name="idFail" >
			    <input type="hidden" name="idPermohonan" >
			    <input type="hidden" name="negeri" >
			    <input type="hidden" name="idNegeri" >
			   	<input type="hidden" name="fail" >
			   	<input type="hidden" name="pagemode" >
			   	<input type="hidden" name="langkah" value="0" >
			    <input type="hidden" name="peringkat_bayaran" >

	<input name="1XtabId" type="hidden" id="tabId" value="$selectedTab"/>
			    
<script>
	//Mula frmCukaiPeringkatBayaran
	
	function cancel() {
		document.${formName}.reset();
	}
	
	function selectBayaran(negeri,idNegeri,idPermohonan) {
		//document.${formName}.command.value = "cukaiperingkatbayar";
		document.${formName}.command.value = "penyata";
		document.${formName}.pagemode.value = "penyataview";
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.negeri.value = negeri;
		document.${formName}.idNegeri.value = idNegeri;
		
		var strString = document.${formName}.socbayaran;
		for( intIndex = 0; intIndex < strString.length; intIndex++ ){
	 		if(strString[intIndex].selected=true && strString[intIndex].value !=0){
	 			document.${formName}.peringkat_bayaran.value = strString[intIndex].value; 			
	 		}
	 		
	    }
		document.${formName}.action = "";
		document.${formName}.submit();		
		//doAjaxCall${formName}("cukaiperingkatbayar");			
		
	}
	
	function senaraiCukai(negeri,idNegeri,idPermohonan,idFail,peringkatBayaran) {
		//document.${formName}.command.value = "cukaiperingkatbayar";
		document.${formName}.command.value = "penyata";
		document.${formName}.pagemode.value = "penyataview";
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.negeri.value = negeri;
		document.${formName}.idNegeri.value = idNegeri;
		document.${formName}.fail.value = idFail;
		document.${formName}.peringkat_bayaran.value = peringkatBayaran;
		document.${formName}.action = "";
		document.${formName}.submit();
		//doAjaxCall${formName}("cukaiperingkatbayar");			
		
	}
	//Tamat frmCukaiPeringkatBayaran
	


	//rosli 2010/04/19
	function CetakBayaran(tahun,idnegeri){
		var url = "../servlet/ekptg.report.htp.cukai.LaporanCukai?TAHUN="+tahun+"&ID_NEGERI="+idnegeri+"&template=HTPCukaiMemoSenaraiBaucer";
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
</script>