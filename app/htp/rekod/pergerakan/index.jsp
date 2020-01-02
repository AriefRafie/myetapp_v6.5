<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<table width="98%" border="0">
	<tr>	
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			<fieldset><legend>CARIAN</legend>
				<table border="0" width="100%">
					<tr>       
				        <td width="29%" align="right">Negeri</td>
				        <td width="1%">:</td>
				        <td width="70%">$selectNegeri</td>
				    </tr>
				    <tr>
				        <td align="right"><div align="right">Daerah</div></td>
				        <td><div align="center">:</div></td>
				        <td>$selectDaerah</td>
				    </tr>
				    <tr>
				        <td align="right"><div align="right">Bandar/Pekan/Mukim</div></td>
				        <td><div align="center">:</div></td>
				        <td>$selectMukim</td>
				    </tr>		  
					<tr>
			         	<td align="right"><div align="right">Jenis Hakmilik</div></td>
			         	<td><div align="center">:</div></td>
			         	<td>$selectJenisHakmilik</td>
			      	</tr>
			      	<tr>
			        	<td align="right" ><div align="right">No. Hakmilik</div></td>
			        	<td><div align="center">:</div></td>
			        	<td><label>
			          		<input name="txtNoHakmilik" type="text" id="txtNoHakmilik" value="$txtNoHakmilik" />
			        	</label></td>
			 		</tr>     
			       	<tr >
			        	<td align="right"><div align="right">No. Lot / PT</div></td>
			        	<td><div align="center">:</div></td>
			        	<td><input name="txtNoLot" type="text" id="txtNoLot" value="$txtNoLot" /></td>
			   		<tr>
			    		<td align="right"><div align="right">No. Fail</div></td>
			    		<td width="1%"><div align="center">:</div></td>
			     		<td width="59%"><input name="txtNoFail" type="text" id="txtNoFail" value="$txtNoFail" size="43" /></td>
			  		<tr>
			      	<tr>
			        	<td></td>
			        	<td>&nbsp;</td>
			        	<td>
			        		<input type="button" class="stylobutton100" name="btnCari" id="btnCari" value="Cari" onclick="cari()" />
			        		<input type="reset" class="stylobutton100" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongCarian()"  />        </td>
			    	</tr>
			      		<!-- <tr>			  
							<td colspan="3" align="center">&nbsp;</td>
						</tr> -->
			  	</table>  
			</fieldset>
			
			<fieldset>
			<legend>SENARAI FAIL PERGERAKAN</legend>
			<table border="0" width="100%">
			    <tr>
			    	<td colspan="8">#parse("app/utils/record_paging.jsp") </td>
			    </tr>
				<tr class="table_header">
				  <td width="3%"><strong>Bil.<strong></td>
			   	  <td width="12%"><div align="left"><strong>No. Hakmilik</strong></div></td>
			   	  <td width="10%"><div align="left">
			   	    <div align="center"><strong>No. Lot / PT</strong></div>
			   	  </div></td>
			  	  <!--<td width="15%"><div align="left">Status<br /> 
			  	  </div></td>-->
			   	  <td width="60%"><div align="left"><strong>Kegunaan Tanah</strong></div></td>
			  </tr>
			#foreach ($senarai in $SenaraiHakmilik)
			  #set( $i = $velocityCount )
			    #if ( ($i % 2) != 1 )
			       #set( $row = "row2" )
			    #else
			       #set( $row = "row1" )
			    #end
			    <tr class="$row">
			    <td >
			    <a href="javascript:pergerakanhakmilik_detail('$senarai.idHakmilik')" class="style1">
			    $senarai.bil
			    </a></td>
			    #if($senarai.bil != '')
				  <td ><a href="javascript:pergerakanhakmilik_detail('$senarai.idHakmilik')" class="style1">$senarai.kodJenis $senarai.noHakmilik</a></td>
			    #else
			    	<td >$senarai.noHakmilik</td>
			    #end
			    <td ><div align="left">$senarai.kodLot$senarai.noLot</div></td>
			    <!--<td width="5%"><div align="center">$senarai.statusSah</div></td> -->
			    <td >$senarai.kegunaanTanah</td>
				</tr> 
			 #end
			</table>
			</fieldset>

		</td>
	</tr>
	<tr>	
		<td>
			&nbsp;
   			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila membuat pautan pada <b>Bil.</b> jika maklumat No. Hakmilik tiada.</em></span>
		</td>
	</tr>
	
</table>

<script>
	function cari(){   
		doAjaxCall${formName}("view","firstAction=carianHakmilikRizab");
	}
	
	function kosongCarian(){
	}
	
	function doChangeState() {
	   doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeState");
	}
	
	function doChangeDaerah() {
		doAjaxCall${formName}("doChangeDaerah");
	}
	
	function doChangeMukim() {
		doAjaxCall${formName}("doChangeMukim");
	}
	
	function pergerakanhakmilik_detail(id){
		doAjaxCall${formName}("","firstAction=paparDetailPergerakanHakmilik&idHakmilik="+id);
	}	
	
	function tambahPergerakan(){
		doAjaxCall${formName}("","firstAction=tambahPergerakanHakmilik");
	}
	
	function viewDetailPergerakan(id){
		doAjaxCall${formName}("","firstAction=paparPergerakanHakmilikDetail&idPergerakan="+id);
	}
	
	function kembaliSenarai(){
		doAjaxCall${formName}("","firstAction=");
	}
	
	function kembaliSenaraiPergerakan(){
		doAjaxCall${formName}("","firstAction=paparDetailPergerakanHakmilik");
	}
	
	function tambahPergerakanDetail(idhakmilik){
	//VALIDATAION
	 if(document.${formName}.sorDokumen.checked == false){ 
		alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.'); 
		document.${formName}.sorDokumen.focus();
		return; 
	 }
	 if(document.${formName}.txtKeterangan.value == ""){ 
		alert('Sila masukkan " Urusan " terlebih dahulu.'); 
		document.${formName}.txtKeterangan.focus();
		return; 
	 }
	 if(document.${formName}.txtKepada.value == ""){ 
		alert('Sila masukkan " Kepada " terlebih dahulu.'); 
		document.${formName}.txtKepada.focus();
		return; 
	 }
	 if(document.${formName}.txdTarikhSerah.value == ""){ 
		alert('Sila masukkan " Tarikh Serah" terlebih dahulu.'); 
		document.${formName}.txdTarikhSerah.focus(); 
		return; 
	 }	
	 if(document.${formName}.socStatusS.value == ""){
		alert('Sila masukkan " Status Pinjaman " terlebih dahulu.'); 
		document.${formName}.socStatusS.focus(); 
		return; 
	 }
	  var str1 = document.${formName}.txdTarikhSerah.value; 
	  var dt1 = parseInt(str1.substring(0,2),10); 
	  var mon1 = parseInt(str1.substring(3,5),10)-1; 
	  var yr1 = parseInt(str1.substring(6,10),10);
	  var tarikhSerah = new Date(yr1, mon1, dt1);
	  var str2 = document.${formName}.txdTarikhKembali.value; 
	  var dt2 = parseInt(str2.substring(0,2),10); 
	  var mon2 = parseInt(str2.substring(3,5),10)-1; 
	  var yr2 = parseInt(str2.substring(6,10),10); 
	  var tarikhKembali = new Date(yr2, mon2, dt2); 
	  var currentDate = new Date(); 
	  if (tarikhSerah > currentDate){ 
	  	 alert('Tarikh Serah tidak boleh melebihi dari tarikh hari ini.'); 
	   	 document.${formName}.txdTarikhSerah.focus(); return; 
	  } 
	  if (tarikhKembali > currentDate){ 
	   	  alert('Tarikh Kembali tidak boleh melebihi dari tarikh hari ini.'); 
	   	  document.${formName}.txdTarikhKembali.focus(); return; 
	  } 
	  if (tarikhSerah > tarikhKembali){ 
	      alert('Tarikh Kembali mestilah melebihi dari Tarikh Serah.'); 
	     document.${formName}.txdTarikhKembali.focus(); return; 
	  }
	 //END OF VALIDATION
		doAjaxCall${formName}("","firstAction=tambahPergerakanHakmilikDetail");
		
	}
	
	function kemaskiniPergerakanDetail(id){
		doAjaxCall${formName}("","firstAction=kemaskiniPergerakanHakmilikDetail&idPergerakan="+id);
	}
	
	function cetakPergerakan(id){
		var url = "../servlet/ekptg.report.htp.BorangPergerakanHakmilik?template=TIADA&idPergerakan="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function updatePergerakanDetail(id){
	//VALIDATAION
	 if(document.${formName}.sorDokumen.checked == false){ 
		alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.'); 
		document.${formName}.sorDokumen.focus();
		return; 
	 }
	 if(document.${formName}.txtKeterangan.value == ""){ 
		alert('Sila masukkan " Urusan " terlebih dahulu.'); 
		document.${formName}.txtKeterangan.focus();
		return; 
	 }
	 if(document.${formName}.txtKepada.value == ""){ 
		alert('Sila masukkan " Kepada " terlebih dahulu.'); 
		document.${formName}.txtKepada.focus();
		return; 
	 }
	 if(document.${formName}.txdTarikhSerah.value == ""){ 
		alert('Sila masukkan " Tarikh Serah" terlebih dahulu.'); 
		document.${formName}.txdTarikhSerah.focus(); 
		return; 
	 }	
	 if(document.${formName}.socStatusS.value == ""){
		alert('Sila masukkan " Status Pinjaman " terlebih dahulu.'); 
		document.${formName}.socStatusS.focus(); 
		return; 
	 }
	  var str1 = document.${formName}.txdTarikhSerah.value; 
	  var dt1 = parseInt(str1.substring(0,2),10); 
	  var mon1 = parseInt(str1.substring(3,5),10)-1; 
	  var yr1 = parseInt(str1.substring(6,10),10);
	  var tarikhSerah = new Date(yr1, mon1, dt1);
	  var str2 = document.${formName}.txdTarikhKembali.value; 
	  var dt2 = parseInt(str2.substring(0,2),10); 
	  var mon2 = parseInt(str2.substring(3,5),10)-1; 
	  var yr2 = parseInt(str2.substring(6,10),10); 
	  var tarikhKembali = new Date(yr2, mon2, dt2); 
	  var currentDate = new Date(); 
	  if (tarikhSerah > currentDate){ 
	  	 alert('Tarikh Serah tidak boleh melebihi dari tarikh hari ini.'); 
	   	 document.${formName}.txdTarikhSerah.focus(); return; 
	  } 
	  if (tarikhKembali > currentDate){ 
	   	  alert('Tarikh Kembali tidak boleh melebihi dari tarikh hari ini.'); 
	   	  document.${formName}.tarikhKembali.focus(); return; 
	  } 
	  if (tarikhSerah > tarikhKembali){ 
	      alert('Tarikh Kembali mestilah melebihi dari Tarikh Serah.'); 
	     document.${formName}.tarikhKembali.focus(); return; 
	  }
	 //END OF VALIDATION	
		doAjaxCall${formName}("","firstAction=updatePergerakanHakmilikDetail&idPergerakan="+id);

	}
		
	function batalKemaskini(id){
		doAjaxCall${formName}("","firstAction=paparPergerakanHakmilik&idPergerakan="+id);
	}
	
	function hapus(id_) {
	    if ( !window.confirm("Maklumat akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	    doAjaxCall${formName}("hapus","firstAction=hapus&idPergerakan="+id_);
	    
	}

</script>
#parse("app/htp/utiliti/javaScriptUmum.jsp")
