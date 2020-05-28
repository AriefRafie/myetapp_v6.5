<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>



<table width="98%" border="0">
	<tr>
		<td>&nbsp;
			
		</td>
	</tr>
	<tr>
		<td>
			<fieldset><legend>CARIAN</legend>
				<table border="0" width="100%">
			  		<tr>
					    <td width="29%" align="right"><div align="right">Jenis Tanah </div></td>
					    <td width="1%"><div align="center">:</div></td>
					    <td width="70%">
					        #set($checkedMilik = "")
					        #set($checkedRizab = "")
							#if($idJenisTanah == "1")
					          	#set($checkedMilik = "checked")
					         	#set($checkedRizab = "")
					            
					        #elseif($idJenisTanah == "2")
					         	#set($checkedRizab ="checked")
					         	#set($checkedMilik ="")
					
					        #end
					    	  <!--	<select name="socJenisTanah" id="socJenisTanah" onchange="cari()">     
					        
					        #set ($listJenisTanah = ["SILA PILIH","TANAH MILIK PERSEKUTUAN","TANAH RIZAB PERSEKUTUAN"])
					        #set( $counter = 0 )
					        #foreach ($i in $listJenisTanah)
					        #if ($idJenisTanah == $counter) 
					            <option selected value="$counter">$i</option>
					        #else
					            <option value="$counter">$i</option>
					        #end
					        #set ($counter = $counter+1)
					        #end	
					      </select>   --> 
					      <!-- Midified by rosli 28/07/2010 -->
					    	<input type="radio" name="socJenisTanahtemp" value="1" $checkedMilik  onclick="cari()"/>TANAH MILIK PERSEKUTUAN
					 		<input type="radio" name="socJenisTanahtemp" value="2" $checkedRizab  onclick="cari()"/>TANAH RIZAB PERSEKUTUAN
					    	<input type="hidden" name="socJenisTanah" value="$idJenisTanah">
					    </td>
			  		</tr>
			      
				#if ($flagAdvSearch == 'open')			      
			      	<tr>       
				        <td><div align="right">Negeri</div></td>
				        <td><div align="center">:</div></td>
				        <td>$selectNegeri</td>
			      	</tr>
			      	<tr>
				        <td align="right"><div align="right">
				        	#if ($idNegeri == '13' )
				        		Bahagian
				        	#else
				        		Daerah
				        	#end
				        	</div></td>
				        <td><div align="center">:</div></td>
				        <td>$selectDaerah</td>
			      	</tr>
			      	<tr>
				        <td align="right"><div align="right">Bandar/Pekan/Mukim</div></td>
				        <td><div align="center">:</div></td>
				        <td>$selectMukim</td>
			      	</tr>
			       	<tr>
				        <td><div align="right">Kementerian</div></td>
				        <td><div align="center">:</div></td>
				        <td>$selectKementerian</td>
			      	</tr>
			      	<tr>
				        <td><div align="right">Agensi</div></td>
				        <td><div align="center">:</div></td>
				        <td>$selectAgensi</td>
			      	</tr>
			      	<tr>
				        <td><div align="right">Status</div></td>
				        <td><div align="center">:</div></td>
				        <td>
				        <select name="socStatus" id="socStatus">
				        <!--
				        <option $selectedStatus value="0">SILA PILIH</option>
				        <option $selectedStatus1 value="1">AKTIF</option>
				        <option $selectedStatus2 value="2">BATAL</option>
				        -->
				        #set ($listJenisStatus = ["SILA PILIH","AKTIF","TIDAK AKTIF"])
				        #set( $counter2 = 0 )
				        #foreach ($i in $listJenisStatus)
				        #if ($idStatus == $counter2) 
				            <option selected value="$counter2">$i</option>
				        #else
				            <option value="$counter2">$i</option>
				        #end
				        #set ($counter2 = $counter2+1)
				        #end
				      </select></td>
			   		</tr>
				#end
				
			    #if ($idJenisTanah == '1')
					<tr >
				     	<td align="right"><div align="right">Jenis Hakmilik</div></td>
				         <td><div align="center">:</div></td>
				         <td>$selectJenisHakmilik</td>
	     			</tr>    
				    <tr>
				        <td align="right" ><div align="right">No. Hakmilik</div></td>
				        <td><div align="center">:</div></td>
				        <td><label>
				          <input name="txtNoHakmilikC" type="text" id="txtNoHakmilikC" value="$txtNoHakmilikC" />
				        </label></td>		      
				   	</tr>
			 	#end
			   	#if ($idJenisTanah == '2')
				      <tr>
				        <td><div align="right">No. Warta</div></td>
				        <td><div align="center">:</div></td>
				        <td><input name="txtNoWartaC" type="text" id="txtNoWartaC" value="$txtNoWartaC" /></td>
				      </tr>
			  	#end
			      	<tr >
			       		<td align="right"><div align="right">Jenis Lot</div></td>
			          	<td><div align="center">:</div></td>
			          	<td>$selectJenisLot</td>
			        </tr>
			       	<tr >
			        	<td align="right"><div align="right">No. Lot / PT</div></td>
			        	<td><div align="center">:</div></td>
				        <td>
				        	<input name="txtNoLotC" type="text" id="txtNoLotC" value="$txtNoLotC" />
							 
				        </td>
			      	</tr>    
				
				  	<tr>
					    <td align="right"><div align="right">No. Fail</div></td>
					    <td><div align="center">:</div></td>
					   	<td><input name="txtNoFailC" type="text" id="txtNoFailC" value="$txtNoFailC" size="43" />
					     #if ($flagAdvSearch == '')
				                <a href="#" title="More" class="style1" onclick="javascript:more()">Buka Carian Terperinci</a> 
				            #end
				            #if ($flagAdvSearch == 'open') <a href="#" title="Less" class="style1" onclick="javascript:less()">Tutup Carian Terperinci</a> 
				            #end
					     </td>
				  	<tr/>	
				  	#if ($flagAdvSearch == 'open')	
				  	<tr >
			        	<td valign="top" align="right"><div align="right">Kegunaan Tanah</div></td>
			        	<td valign="top"><div align="center">:</div></td>
				        <td>
								<textarea name="txtkegunaantanahc" cols="40" rows="2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();">$txtKegunaanTanahC</textarea>
							 
				        </td>
			      	</tr>  		      
				  	#end		      
			      	<tr>
				        <td></td>
				        <td>&nbsp;</td>
				        <td>
				        	<input type="button" class="stylobutton100" name="btnCari" id="btnCari" value="Cari" onclick="cari()"/>
				        	<input type="button" class="stylobutton100" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongCarian('$idJenisTanah')" />        
				        </td>
			      	</tr>
			      	<!-- <tr>			  
						<td colspan="3" align="center">&nbsp;</td>
					</tr> -->
			  	</table>  
			
			</fieldset>
			<br>
			<fieldset><legend>SENARAI HAKMILIK/RIZAB</legend>
				<table border="0" width="100%">
				    <tr>
				    	<td colspan="8">#parse("app/utils/record_paging.jsp") </td>
				    </tr>
					<tr class="table_header">
					  <td width="3%"><strong>Bil.</strong></td>
				   	  <td width="15%"><div align="left"><strong>No. Fail</strong></div></td>
				   	  <td width="12%"><div align="left"><strong>
				   	  #if ($idJenisTanah==1)
				   	  	No. Hakmilik
				   	  #elseif ($idJenisTanah==2)
				   	   	No. Warta
				   	  #else
				   	  	No. Hakmilik/Warta
				      #end
				      </strong></div></td>
				  	  <td width="10%"><div align="left"><strong>
				  	  #if ($idJenisTanah==2)
				   	   	No. Lot
				   	  #else
				  	  	No. Lot/PT
				      #end </strong></div></td>
				   	  <td width="15%"><div align="center"><strong>Status</strong><br />
				   	  </div></td>
				  	  <td width="30%"><div align="left"><strong>Kegunaan Tanah</strong></div></td>
				  	  <td width="10%"><div align="center"><strong>Tindakan</strong></div></td>
				  </tr>
				
				##Kemaskini pd 21/09/2011 oleh Mohamad Rosli (Penyelesaian kpd page number apabila Vector kosong) 
				##foreach ($senarai in $SenaraiTanah) 
				#if ($!SenaraiFail.size()>0)
				#foreach ($senarai in $SenaraiFail)
					#set($maklumatGis ="nofail="+$!senarai.noFail+"&kod="+$!senarai.gisStatus+"&upi="+$!senarai.gisHantar)
					
					       
				  #set( $i = $velocityCount )
				    #if ( ($i % 2) != 1 )
				       #set( $row = "row2" )
				    #else
				       #set( $row = "row1" )
				    #end
				    <tr class="$row">
				    <td width="1%">$senarai.bil</td>
				    #if($senarai.bil != '')
				      #if($senarai.jenisTanah == 'M')     	
				       <td width="18%"><a href="javascript:hakmilikDetail('$senarai.idHakmilik','$senarai.statusSah');" class="style1">$senarai.noFail</a></td>
				      #elseif($senarai.jenisTanah == 'R')
				   	    <td width="18%"><a href="javascript:rizabDetail('$senarai.idHakmilik','$senarai.statusSah');" class="style1">$senarai.noFail</a></td>
				      #else
				   	    <td width="18%">$!senarai.noFail</td>
				      #end    
				    #else
				    	<td width="18%">$!senarai.noFail</td>
				    #end
				   	<td width="8%"><div align="left">$senarai.kodJenis $senarai.noHakmilik $senarai.noWarta</div></td>
				    <td width="8%"><div align="left">$senarai.kodLot$senarai.noLot</div></td>
				    <td width="5%"><div align="center">$senarai.statusSah</div></td>
				    <td width="23%">
				    	$senarai.kegunaanTanah <font color="red"><b>$!senarai.ceroboh</b></font> 
				    	#if($senarai.isImej.equals('IMEJ'))
				    		<!-- <a href="javascript:maklumatImej('$senarai.idHakmilik')" class="style1">Tetingkap Baru(Imej)</a> -->	    				
	    				#end
				    	</td>
				 		<td><div align="center">
				 		<!-- <a href="javascript:maklumatGIS('$!maklumatGis')" class="style1">GIS</a> -->
				 			#if($senarai.isImej.equals('IMEJ'))
				 				<a alt="Lampiran" href = "javascript:maklumatImej('$senarai.idHakmilik');">
						      		<img border="0" src="../img/main.png" width="20" height="15"/>
						      	</a>
	    					#end
  								#if ($senarai.gisCharting != 'N')			      				 									      				
						      	<a alt="GIS" href = "javascript:gisWindow('TIADA','$!maklumatGis');">
						      		<img border="0" src="../img/online/map.png" width="20" height="15"/>
						      	</a>
						      	#end  
  							#if ($!gisPage == 'GIS')	
  								#if ($senarai.gisHantar == 'N')			      				 									      				
			      				<a alt="GIS" href = "javascript:tambahGISRekod('$!senarai.noFail','$!senarai.gisStatus','$senarai.idHakmilik');">
						      		<img border="0" src="../img/plus.gif" width="20" height="15"/>
						      	</a> 
						      	#end  
						     #end 				
				 		</div></td>
					</tr> 
				 #end
				 
			    #else
				  <tr>
				    <td colspan="7" class="row1"><font color="#FF0000">Tiada Rekod.</font></td>
				  </tr>
			    #end
				</table>
			</fieldset>

		</td>
	</tr>
</table>
				        	
<input type="hidden" id="flagAdvSearch" name="flagAdvSearch" value="$!flagAdvSearch" />
<input type="hidden" id="isimpangis" name="isimpangis" value="$!notifikasiSimpan"/>
<input type="hidden" id="firstAction" name="firstAction" value="$!firstAction" />

<script>

	if(document.${formName}.isimpangis.value == 'true'){
		alert("Maklumat untuk GIS telah dihantar.");
	}

	//frmRekodSenaraiHakmilikRizabIndex.jsp -5
	function tambahGISRekod(file,perolehan,idHakmilik){
		document.${formName}.firstAction.value = "carianHakmilikRizab";
		tambahGISC("",file,perolehan,idHakmilik,"firstAction=carianHakmilikRizab&");
	}
	//frmRekodSenaraiHakmilikRizabIndex.jsp -1
	function cari(){   
		field_ = document.${formName}.socJenisTanahtemp;
		for (i = 0; i < field_.length; i++){
			if(field_[i].checked==true){
				document.${formName}.socJenisTanah.value = field_[i].value;
			}
		}
		document.${formName}.firstAction.value = "carianHakmilikRizab";
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab");
		
	}
	
	function XkosongCarian(idJenisTanah){
		if (idJenisTanah == '1'){
			document.${formName}.txtNoHakmilikC.value = "";
		} else if (idJenisTanah == '2'){
			document.${formName}.txtNoWartaC.value = "";
		} 
		document.${formName}.socJenisTanah.value = "0";
		document.${formName}.socStatus.value = "0";
		document.${formName}.socNegeri.value = "";
		document.${formName}.socDaerah.value = "";
		document.${formName}.socMukim.value = "";	
		document.${formName}.txtNoLotC.value = "";
		document.${formName}.txtNoFailC.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";
		//document.${formName}.submit();
	 	doAjaxCall${formName}("","firstAction=carianHakmilikRizab");
	 	
	}
	
	//frmRekodSenaraiHakmilikRizabIndex.jsp -2
	function kosongCarian(idJenisTanah){
		document.${formName}.flagAdvSearch.value = "N";
		document.${formName}.reset();
		//2020/04/12
		document.${formName}.firstAction.value = ""; 
	 	doAjaxCall${formName}("","nextAction=reset");
	 	
	}
	
	function doChangeState() {
	  doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeState");
	}
	
	function doChangeDaerah() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeDaerah");
	}
	
	function doChangeMukim() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeMukim");
	}
	
	function doChangeKementerian() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeKementerian");
	}
	
	function deleteHakmilikBaru(id,id2){
		if ( !window.confirm("Adakah Anda Pasti?")) return;
			document.${formName}.command.value = "";
		
		doAjaxCall${formName}("","firstAction=deleteHakmilikBaru&idHakmilik="+id+"&idHakmilikBaru="+id2);
		
	}

	function doChangeLuasRizab(id_) { 	
		//doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailRizab&lastAction=doChange&idHakmilik=");			
	}

	function more(){
		document.${formName}.flagAdvSearch.value = "open";
		//document.${formName}.submit();
		//doAjaxCall${formName}("","flagAdvSearch=open");
		document.${formName}.firstAction.value = ""; 		
		doAjaxCall${formName}("");
		
	}
	
	function less(){
		document.${formName}.flagAdvSearch.value = "";
		//document.${formName}.submit();
		document.${formName}.firstAction.value = ""; 		
		doAjaxCall${formName}("");
		
	}	
	//HAKMILIK
	//frmRekodSenaraiHakmilikRizabIndex.jsp -3 [link No. Hakmilik]	
	function hakmilikDetail(id_,status){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id+"&statusSah="+status;
		//document.${formName}.submit();
		//2020/04/12
		document.${formName}.firstAction.value = "PendaftaranHakmilik"; 
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id_+"&statusSah="+status);
	}
	function hakmilik_detail(id_,status){
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id_+"&statusSah="+status);
	}
	
	function doChangeStateHR() {
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 
	  	doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeStateHR");
	}
	
	function doChangeDaerahHR() {
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeDaerahHR");
	}
	
	function doChangeMukimHR() {
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeMukimHR");
	}
	//[Kembali]
	function kembaliHakmilik(){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";	
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab");
	}
	//[Kemaskini]
	function kemaskini_detailHakmilik(id_){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&idHakmilik="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&idHakmilik="+id_);
	}
	
	function updateKegunaanTanah(idHakmilik) {
		var url = "../x/${securityToken}/ekptg.view.htp.FrmPopupIntergrasiEtanahView?idHakmilik="+idHakmilik;
		var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		   hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}
	//[Simpan]
	function update_detailHakmilik(id){
		
		//VALIDATAION
		 if(document.${formName}.socNegeriHR.value == 99999){ 
			alert('Sila masukkan " Negeri " terlebih dahulu.'); 
			document.${formName}.socNegeriHR.focus();
			return; 
		 }
		 
		 if(document.${formName}.socDaerahHR.value == ""){ 
			alert('Sila masukkan " Daerah " terlebih dahulu.'); 
			document.${formName}.socDaerahHR.focus();
			return; 
		 }
		 if(document.${formName}.socMukimHR.value == ""){ 
			alert('Sila masukkan " Bandar/Pekan/Mukim " terlebih dahulu.'); 
			document.${formName}.socMukimHR.focus();
			return; 
		 }
		 if(document.${formName}.socJenisHakmilikHR.value == ""){ 
			alert('Sila masukkan " Jenis Hakmilik " terlebih dahulu.'); 
			document.${formName}.socJenisHakmilikHR.focus();
			return; 
		 }
		 if(document.${formName}.txtNoHakmilik.value == ""){ 
				alert('Sila masukkan " Nombor Hakmilik " terlebih dahulu.'); 
				document.${formName}.txtNoHakmilik.focus();
				return; 
		 }
		 
		 if(document.${formName}.socLotHR.value == ""){ 
				alert('Sila masukkan " Kod LOT/PT " terlebih dahulu.'); 
				document.${formName}.socLotHR.focus();
				return; 
		 }
		 if(document.${formName}.txtNoLot.value == ""){ 
				alert('Sila masukkan " No LOT/PT " terlebih dahulu.'); 
				document.${formName}.txtNoLot.focus();
				return; 
		 }
		 if(document.${formName}.socTaraf.value == ""){ 
				alert('Sila masukkan " Taraf Hakmilik " terlebih dahulu.'); 
				document.${formName}.socTaraf.focus(); 
				return; 
		 }	
		 if(document.${formName}.socTaraf.value == "P" && document.${formName}.txtTempoh.value == ""){ 
				alert('Sila masukkan " Tempoh " terlebih dahulu.'); 
				document.${formName}.txtTempoh.focus();
				return; 
		 }
		 if(document.${formName}.txtCukaiTerkini.value == ""){ 
				alert('Sila masukkan " Tempoh " terlebih dahulu.'); 
				document.${formName}.txtCukaiTerkini.focus();
				return; 
		 }
		 
		 if(document.${formName}.socLuas.value == ""){ 
				alert('Sila masukkan " Jenis Luas " terlebih dahulu.'); 
				document.${formName}.socLuas.focus();
				return; 
	     }   
		 if(document.${formName}.txtLuas1.value == "" &&  document.${formName}.txtLuas2.value == "" && 
			document.${formName}.txtLuas3.value == "" &&  document.${formName}.txtLuas4.value == "" &&
			document.${formName}.txtLuas5.value == "" &&  document.${formName}.txtLuas6.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				//document.${formName}.txtLuas.focus();
				return; 
		 }
		 if(document.${formName}.socRizab.value == ""){ 
				alert('Sila masukkan " Rizab " terlebih dahulu.'); 
				document.${formName}.socRizab.focus();
				return; 
	     } 
		 if(document.${formName}.txdTarikhTerima.value == ""){ 
			alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
			document.${formName}.txdTarikhTerima.focus();
			return; 
		 }
		 
		 if(document.${formName}.txdTarikhDaftar.value == ""){ 
			alert('Sila masukkan " Tarikh Daftar " terlebih dahulu.'); 
			document.${formName}.txdTarikhDaftar.focus();
			return; 
		 }
		 
		 if(document.${formName}.txtCukaiTahun.value == ""){ 
			alert('Sila masukkan " Cukai Tahun Pertama " terlebih dahulu.'); 
			document.${formName}.txtCukaiTahun.focus();
			return; 
		 }
		 if(document.${formName}.txtLokasi.value == ""){ 
			alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
			document.${formName}.txtLokasi.focus();
			return; 
		 }
		 if(document.${formName}.txtKegunaanTanah.value == ""){ 
			alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
			document.${formName}.txtKegunaanTanah.focus();
			return; 
		 }		
		 
		 if(document.${formName}.socKategori.value == ""){ 
			alert('Sila masukkan " Kategori " terlebih dahulu.'); 
			document.${formName}.socKategori.focus();
			return; 
		 }
		 if(document.${formName}.socStatusDaftar.value == ""){ 
			alert('Sila masukkan " Status Sah " terlebih dahulu.'); 
			document.${formName}.socStatusDaftar.focus();
			return; 
		 }        
		 
		  var str1 = document.${formName}.txdTarikhTerima.value; 
		  var dt1 = parseInt(str1.substring(0,2),10); 
		  var mon1 = parseInt(str1.substring(3,5),10)-1; 
		  var yr1 = parseInt(str1.substring(6,10),10);
		  var tarikhTerima = new Date(yr1, mon1, dt1);
		  var str2 = document.${formName}.txdTarikhDaftar.value; 
		  var dt2 = parseInt(str2.substring(0,2),10); 
		  var mon2 = parseInt(str2.substring(3,5),10)-1; 
		  var yr2 = parseInt(str2.substring(6,10),10); 
		  var tarikhDaftar = new Date(yr2, mon2, dt2); 
		  var currentDate = new Date(); 
		  if (tarikhTerima > currentDate){ 
		  	 alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.'); 
		   	 document.${formName}.txdTarikhTerima.focus(); return; 
		  } 
		  if (tarikhDaftar > currentDate){ 
		   	  alert('Tarikh Daftar tidak boleh melebihi dari tarikh hari ini.'); 
		   	  document.${formName}.txdTarikhDaftar.focus(); return; 
		  } 
		  if (tarikhDaftar > tarikhTerima){ 
		      alert('Tarikh Daftar tidak boleh melebihi dari Tarikh Terima.'); 
		     document.${formName}.txdTarikhTerima.focus(); return; 
		  }
		  
			if ( !window.confirm("Adakah Anda Pasti ?") ){
			   return;
		 	}
	  
		//END OF VALIDATION
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=updateDetailHakmilik&idHakmilik="+id;	
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=updateDetailHakmilik&idHakmilik="+id);
		
	}
	//[Link] Skrin Hakmilik Sambungan, No. Hakmilik
	//27/06/2012
	function hakmilikDetailSamb(id,status){
		document.${formName}.idHakmilik.value = id;
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&statusSah="+status);
	
	}

	//20/10/2010
	//05/02/2013 
	//[Link] Skrin Hakmilik Sambungan, No. Hakmilik (Selepas simpan)
	function hakmilikDetailSambungan(id,status){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=papardetailhakmiliksamb&idHakmilik="+id+"&statusSah="+status;
		//document.${formName}.submit();
		hakmilikDetailSamb(id,status);

	}		
	//[Cetak]
	//01/06/2010
	function cetakMaklumatHakmilik(idhakmilik){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatHakmilik&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
	// START PAGING
	function screen1(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=";
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = ""; 
		doAjaxCall${formName}("","firstAction=");
		
	}
	
	function screen2(id,jenis){
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 
		
	   	//if(jenis == 'Y' || jenis == 'T'){
		if(jenis == 'M'){
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id;
		 	doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id);
		}else{
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
			doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id);
		}
		//document.${formName}.submit();
	}
	
	function screen3(id){
		//document.${formName}.command.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&idHakmilik="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranPembangunan"; 				
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&idHakmilik="+id);
	}
	
	function screen4(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&idHakmilik="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranImej"; 				
		doAjaxCall${formName}("","firstAction=PendaftaranImej&idHakmilik="+id);
	}
	
	function screen5(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=paparDetailHakmilik&idHakmilik="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "paparDetailHakmilik"; 				
		doAjaxCall${formName}("","firstAction=paparDetailHakmilik&idHakmilik="+id);
	}
	//END PAGING	
	
	//TAB	
	function selectTab2(tabId,command,mode,tabmode){
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	}
	//[Tab]
	function selectTab(id){
		//alert('selectTab');
		//document.${formName}.command.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&tabId="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 				
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&tabId="+id);
		
	}	
	// SELECTED ITEM STATUS SAH (UPDATE) 
	function doChangeTaraf() {
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 				
		if(document.${formName}.socStatusDaftar.value=='S'){
			doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskinidetailhakmiliksamb&lastAction=doChange");	
		}else{
			doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChange");		
		}
		
	}
	
	//[Simpan]- Hakmilik Sambungan
	function updateDetailHakmilikSamb(id,status){
		//updateHakmilikVerifikasi(id);
		
		if(document.${formName}.socNegeriHR.value == 99999){ 
			alert('Sila masukkan " Negeri " terlebih dahulu.'); 
			document.${formName}.socNegeriHR.focus();
			return; 
	 	}
	 	if(document.${formName}.socDaerahHR.value == ""){ 
			alert('Sila masukkan " Daerah " terlebih dahulu.'); 
			document.${formName}.socDaerahHR.focus();
			return; 
		}
	 	if(document.${formName}.socMukimHR.value == ""){ 
			alert('Sila masukkan " Bandar/Pekan/Mukim " terlebih dahulu.'); 
			document.${formName}.socMukimHR.focus();
			return; 
		}
	 	if(document.${formName}.socJenisHakmilikHR.value == ""){ 
			alert('Sila masukkan " Jenis Hakmilik " terlebih dahulu.'); 
			document.${formName}.socJenisHakmilikHR.focus();
			return; 
		 }
		 if(document.${formName}.txtNoHakmilik.value == ""){ 
				alert('Sila masukkan " Nombor Hakmilik " terlebih dahulu.'); 
				document.${formName}.txtNoHakmilik.focus();
				return; 
		 }
		 
		 if(document.${formName}.socLotHR.value == ""){ 
				alert('Sila masukkan " Kod LOT/PT " terlebih dahulu.'); 
				document.${formName}.socLotHR.focus();
				return; 
		 }
		 if(document.${formName}.txtNoLot.value == ""){ 
				alert('Sila masukkan " No LOT/PT " terlebih dahulu.'); 
				document.${formName}.txtNoLot.focus();
				return; 
		 }
	 	if(document.${formName}.txdTarikhTerima.value == ""){ 
			alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
			document.${formName}.txdTarikhTerima.focus();
			return; 
	 	}
		if(document.${formName}.txdTarikhDaftar.value == ""){ 
			alert('Sila masukkan " Tarikh Daftar " terlebih dahulu.'); 
			document.${formName}.txdTarikhDaftar.focus();
			return; 
	 	}
	 	if(document.${formName}.socTaraf.value == ""){ 
			alert('Sila masukkan " Taraf Hakmilik " terlebih dahulu.'); 
			document.${formName}.socTaraf.focus(); 
			return; 
	 	}	
	 	if(document.${formName}.txtCukaiTahun.value == ""){ 
			alert('Sila masukkan " Cukai Tahun Pertama " terlebih dahulu.'); 
			document.${formName}.txtCukaiTahun.focus();
			return; 
	 	}
	  	if(document.${formName}.txtCukaiTerkini.value == ""){ 
			alert('Sila masukkan " Cukai Tahun Semasa " terlebih dahulu.'); 
			document.${formName}.txtCukaiTerkini.focus();
			return; 
		}
	 	if(document.${formName}.txtLokasi.value == ""){ 
			alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
			document.${formName}.txtLokasi.focus();
			return; 
	 	}
	 	if(document.${formName}.txtKegunaanTanah.value == ""){ 
			alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
			document.${formName}.txtKegunaanTanah.focus();
			return; 
	 	}			
	 	/*if(document.${formName}.socLuas.value == ""){ 
			alert('Sila masukkan " Jenis Luas " terlebih dahulu.'); 
			document.${formName}.socLuas.focus();
			return; 
	 	}   
	 	if(document.${formName}.txtLuas.value == ""){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
			document.${formName}.txtLuas.focus();
			return; 
	 	}*/
	 	if(document.${formName}.socLuas.value == "" || document.${formName}.socLuas.value == "0"){ 
			alert('Sila masukkan " Unit Luas " terlebih dahulu.'); 
			document.${formName}.socLuas.focus();
			return false; 
	 	}   
	 	if(document.${formName}.socLuas.value == '7'){
	 		if(document.${formName}.txtLuas5.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas5.focus();
				return false; 
	 		}
			if(document.${formName}.txtLuas6.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas6.focus();
				return false; 
	 		} 
	 	}else if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
	
	 		if(document.${formName}.txtLuas2.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas2.focus();
				return false; 
	 		}
			if(document.${formName}.txtLuas3.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas3.focus();
				return false; 
	 		}
	 		if(document.${formName}.txtLuas3.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas3.focus();
				return false; 
	 		}
	 	
	 	}else{
	 		if(document.${formName}.txtLuas1.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas1.focus();
				return false;
			}
			 
	 	} 	
	 	if(document.${formName}.socRizab.value == ""){ 
			alert('Sila masukkan " Rizab " terlebih dahulu.'); 
			document.${formName}.socRizab.focus();
			return; 
     	} 
	 	if(document.${formName}.socKategori.value == ""){ 
			alert('Sila masukkan " Kategori " terlebih dahulu.'); 
			document.${formName}.socKategori.focus();
			return; 
	 	}
	 	if(document.${formName}.socStatusDaftar.value == ""){ 
			alert('Sila masukkan " Status Sah " terlebih dahulu.'); 
			document.${formName}.socStatusDaftar.focus();
			return; 
	 	}        
	
	  	var str1 = document.${formName}.txdTarikhTerima.value; 
	  	var dt1 = parseInt(str1.substring(0,2),10); 
	  	var mon1 = parseInt(str1.substring(3,5),10)-1; 
	  	var yr1 = parseInt(str1.substring(6,10),10);
	  	var tarikhTerima = new Date(yr1, mon1, dt1);
	 	var str2 = document.${formName}.txdTarikhDaftar.value; 
	  	var dt2 = parseInt(str2.substring(0,2),10); 
	  	var mon2 = parseInt(str2.substring(3,5),10)-1; 
	  	var yr2 = parseInt(str2.substring(6,10),10); 
	  	var tarikhDaftar = new Date(yr2, mon2, dt2); 
	  	var currentDate = new Date(); 
	  	if (tarikhTerima > currentDate){ 
	  		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.'); 
	   	 	document.${formName}.txdTarikhTerima.focus(); 
	   	 	return; 
	  	} 
	  	if (tarikhDaftar > currentDate){ 
	   		alert('Tarikh Daftar tidak boleh melebihi dari tarikh hari ini.'); 
	   	  	document.${formName}.txdTarikhDaftar.focus(); 
	   	  	return; 
	  	} 
	  	if (tarikhDaftar > tarikhTerima){ 
	  		alert('Tarikh Daftar tidak boleh melebihi dari Tarikh Terima.'); 
	  		document.${formName}.txdTarikhTerima.focus(); return; 
	  	}
	  
	  	if(status == 'S'){
	 		if(document.${formName}.socJenisHakmilikBaru.value == ""){ 
				alert('Sila masukkan " Jenis Hakmilik Sambungan " terlebih dahulu.'); 
				document.${formName}.socJenisHakmilikBaru.focus();
				return;
		 	}
		 	if(document.${formName}.txtHakmilikBerikut.value == ""){ 
				alert('Sila masukkan " No Hakmilik Sambungan " terlebih dahulu.'); 
				document.${formName}.txtHakmilikBerikut.focus();
				return; 
		 	}
	  	}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
		   return;
		}
		
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=updateDetailHakmilik&idHakmilik="+id;	
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 				
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=updateDetailHakmilik&tabId="+id);
	
	}

	//semakan Tarikh semasa
	function validateTarikhSemasa(inputfield) {
		var today = new Date();	
		dari_bulan = inputfield.value.substring(3,5);
		dari_hari = inputfield.value.substring(0,2);
		dari_tahun = inputfield.value.substring(6,10);
		var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;	
		var myDate = Date.parse(daritemp);
	
		if (myDate>today) {
	  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
	  		inputfield.value="";
	  		inputfield.focus();
	 		return;
	 	}
	
	}
	//semakan Tarikh semasa null
	function validateTarikhSemasaIsNull(inputfield) {
		var today = new Date();	
		if(inputfield.value != ''){
			dari_bulan = inputfield.value.substring(3,5);
			dari_hari = inputfield.value.substring(0,2);
			dari_tahun = inputfield.value.substring(6,10);
			var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;	
			var myDate = Date.parse(daritemp);
		
			if (myDate>today) {
		  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
		  		inputfield.value="";
		  		inputfield.focus();
		 		return;
	
		 	}
		}
		
	}	
	//[link] Maklumat Pergerakan
	function pergerakanhakmilik_detail(id){
		//2020/04/12
		//document.${formName}.firstAction.value = "paparDetailPergerakanHakmilik"; 				
		document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodPergerakanHakmilik&firstAction=paparDetailPergerakanHakmilik&idHakmilik="+id;	
		document.${formName}.submit();
		//doAjaxCall${formName}("","firstAction=paparDetailPergerakanHakmilik&idHakmilik="+id);
	}	
	//	pembangunan/frmRekodPembangunanPentadbiranIndex 
	 //[Simpan]
	 function tambahPerihalHakmilik(id,luasAsal,luasCurrent,luasJumlahGuna){
		var urljava = "";
		
		if(document.${formName}.negeri.value=="SARAWAK" || document.${formName}.negeri.value=="SABAH"){ 			
			urljava = "ekptg.view.htp.negeri.rekod.FrmRekodTanahNegeri";			
		}else{			
			urljava = "ekptg.view.htp.rekod.FrmRekodTanah";			
		}
		
	     var diff1;var diff2;var combine;
		 
		 combine = eval(luasJumlahGuna) + eval(document.${formName}.txtLuas.value);
		 diff1 = eval(luasAsal) - eval(document.${formName}.txtLuas.value);
		 diff2 = luasAsal - combine;
		 
		 //VALIDATAION
		if(document.${formName}.socJenisBinaan.value == ""){ 
			alert('Sila masukkan " Jenis Binaan " terlebih dahulu.'); 
		 	document.${formName}.socJenisBinaan.focus();
		 	return; 
		
		}
		if(document.${formName}.socLuas.value == ""){ 
		 	alert('Sila masukkan "Unit Luas " terlebih dahulu.'); 
		 	document.${formName}.socLuas.focus();
		 	return; 
		
		}
		if(document.${formName}.txtLuas.value == "" || document.${formName}.txtLuas.value == "0"){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
		 	//document.${formName}.txtLuas.focus(); 
		 	return; 
		
		}	
		//if(document.${formName}.txtLuas.value>luasAsal){
		if(diff1 != 0 && diff1<0){
			alert("Jumlah luas guna melebihi luas asal.");
		 	//alert(luasCurrent);
		 	return;
		
		}
		//if(luasCurrent!=""){
		if (diff2 != 0 && diff2 < 0) {
		   		//if(document.${formName}.txtLuas.value>luasCurrent){
		   		//if(document.${formName}.txtLuas.value.toFixed(4)>luasCurrent.toFixed(4)){
			alert("Jumlah luas guna melebihi baki luas yang ada.");
		 	return;
		
		}
		//aeda 
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		var socJenisBinaan = document.${formName}.socJenisBinaan.value;
		var txtNoJKR = document.${formName}.txtNoJKR.value;
		var txdTarikhBina = document.${formName}.txdTarikhBina.value;
		var txtHarga = document.${formName}.txtHarga.value;
		var socLuas = document.${formName}.socLuas.value;
		var txtLuas1 = document.${formName}.txtLuas1.value;
		var txtCatatan = document.${formName}.txtCatatan.value;
		var perihal = document.${formName}.txtPerihalImej.value;
		
		/* if (document.${formName}.fileupload.value == ""){ 
			
    		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=tambahDetailKeluasanNoAttachement&idHakmilik="+id);
			
		}else{*/
			//2020/04/12
			//document.${formName}.firstAction.value = "PendaftaranPembangunan"; 				
		
    		document.${formName}.action = "?_portal_module="+urljava+"&firstAction=PendaftaranPembangunan&nextAction=tambahDetailKeluasan&idHakmilik="+id+"&socJenisBinaan="+socJenisBinaan+"&txtNoJKR="+txtNoJKR+"&txdTarikhBina="+txdTarikhBina+"&txtHarga="+txtHarga+"&socLuas="+socLuas+"&txtLuas1="+txtLuas1+"&txtCatatan="+txtCatatan+"&txtPerihalImej="+perihal;
    		document.${formName}.method="post";
    		document.${formName}.enctype="multipart/form-data";
    	    document.${formName}.encoding="multipart/form-data";
    		document.${formName}.submit();
    	
    	//}
	
	}	
		
	 function XviewDetailPerihal(id){
	 	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=viewDetailKeluasan&idHakmilikPerihal="+id;
	 	//document.${formName}.submit();
	 	document.${formName}.idHakmilikPerihal.value = id;
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranPembangunan"; 				
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=viewDetailKeluasan");
	 }
	 
	 function XkemaskiniDetailPerihal(id,idGambar){
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranPembangunan"; 				
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=kemaskiniDetailKeluasan&idHakmilikPerihal="+id+"&idGambar="+idGambar);
	 }
 
 	function XupdatePerihalHakmilik(id,luasAsal,luasCurrent,luasJumlahGuna,luasYgDiUpdate){
 		var diff1;var diff2;var combine;
		combine = eval(luasJumlahGuna) + eval(document.${formName}.txtLuas.value) - eval(luasYgDiUpdate);
		diff1 = eval(luasAsal) - eval(document.${formName}.txtLuas.value);
		diff2 = luasAsal - combine;
 
	 //VALIDATAION
	  if(document.${formName}.socJenisBinaan.value == ""){ 
	 	alert('Sila masukkan " Jenis Binaan " terlebih dahulu.'); 
	 	document.${formName}.socJenisBinaan.focus();
	 	return; 
	  }
	  if(document.${formName}.socLuas.value == ""){ 
	 	alert('Sila masukkan "Unit Luas " terlebih dahulu.'); 
	 	document.${formName}.socLuas.focus();
	 	return; 
	  }
	  if(document.${formName}.txtLuas.value == ""){ 
	 	alert('Sila masukkan " Luas " terlebih dahulu.'); 
	 	document.${formName}.txtLuas.focus(); 
	 	return; 
	  }	
	  if(diff1 != 0 && diff1<0){
	   	alert("Jumlah luas guna melebihi luas asal.");
	 	return;
	  }
	  
	 if (diff2 != 0 && diff2 < 0) {
	 	alert("Jumlah luas guna melebihi baki luas yang ada.");
	 	return;
	 }
 
 	//END OF VALIDATAION
 	document.${formName}.idHakmilikPerihal.value = id;
 	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=updateDetailKeluasan&idHakmilikPerihal="+id;
 	//document.${formName}.submit();
 		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranPembangunan"; 				
 	doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=updateDetailKeluasan&idHakmilikPerihal="+id);
 	
 }
 
	function kembali(id,jenis){
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 				

    	if(jenis == 'Y' || jenis == 'T'){
 			document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id;
 		}else{
 			document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
 		}
 		document.${formName}.submit();
 	
 	}
 	
 	function dariPembangunan(id,jenis){
  		screen2(id,jenis);
 	}
 
	 function validateNumber(elmnt,content) {
	 	//if it is character, then remove it..
	 	if (isNaN(content)) {
	 		elmnt.value = RemoveNonNumeric(content);
	 		return;
	 	}
	 }
	 
	function calculate(valueMohon,valueBaki){
  		var luasPelepasan = document.${formName}.txtLuasPelepasan.value * 1;
  		var luasAsal = document.${formName}.txtLuasAsal.value * 1; 
  		if (luasPelepasan != ""){
   			if (luasPelepasan > luasAsal){
    			//alert('Luas yang dimasukkan telah melebihi luas asal.'); 
    			document.${formName}.txtLuasPelepasan.value = valueMohon; 
    			document.${formName}.txtBakiLuas.value = valueBaki;
    			return;
   			
   			}else {
    			var luasBaki = (luasAsal - luasPelepasan) * 1; 
    			document.${formName}.txtBakiLuas.value = luasBaki.toFixed(5); 
    		}
   		}
 	} 
 	
	 function XdeleteDetailPembangunan(id){
	 	if ( !window.confirm("Adakah Anda Pasti?")) return;
	 	//document.${formName}.command.value = "";
	 	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=deleteDetailPembangunan&idHakmilikPerihal="+id;
	 	//document.${formName}.submit();
	 	document.${formName}.idHakmilikPerihal.value = id;
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranPembangunan"; 				
	 	doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=deleteDetailPembangunan");
	 	
	 }
	 
 	//function x3baru(id){
 	function XbaruPembangunan(id){
 		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&idHakmilik="+id;
 		//document.${formName}.submit();
 		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranPembangunan"; 				
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&idHakmilik="+id);
 	}
 
 	function x3doChangeTaraf() {
 		//2020/04/12
		document.${formName}.firstAction.value = "PendaftaranPembangunan"; 				
 		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=doChangeLuas");
 	}
 
 	function XdoChangeTaraf2(mode) {
 		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranPembangunan"; 				
 		doAjaxCall${formName}("","mode="+mode+"&firstAction=PendaftaranPembangunan&nextAction=doChangeLuas");
 	}
 
 	function kiraLuas(idLuas){
   		var jenisLuas = idLuas;
   		// KILOMETER PERSEGI
   		if(jenisLuas == "1"){
   			var luasK = (document.${formName}.txtLuas1.value);
 			var luasH = luasK*100;
   			document.${formName}.txtLuas.value = luasH;
    	
    	}else
    	//HEKTER
     		if(jenisLuas == "2"){
   				var luasH = (document.${formName}.txtLuas1.value);
   				document.${formName}.txtLuas.value = luasH;
    		
    		}else
    	// METER PERSEGI
    	if(jenisLuas == "3"){
   	 	var luasM = document.${formName}.txtLuas1.value;
   	  	var luasH = (luasM*0.0001);
 	  	document.${formName}.txtLuas.value = luasH;
    	
    	}else
    	//EKAR, ROOD, POLE
    	if(jenisLuas == "4"){
   	  		var luasE = document.${formName}.txtLuas2.value;
 	  		var luasR = document.${formName}.txtLuas3.value;
 	  		var luasP = document.${formName}.txtLuas4.value;
 	  		var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
   	  		document.${formName}.txtLuas.value = luasH;
    	}else
    	//KAKI PERSEGI
    	if(jenisLuas == "5"){
   	  		var luasAsal = document.${formName}.txtLuas1.value;
 	  		var luasK = luasAsal*0.0000092;
   	  		document.${formName}.txtLuas.value = luasK;
   	  
    	}else if(jenisLuas == "6"){	//EKAR PERPULUHAN
   	  		var luasAsal = document.${formName}.txtLuas1.value;
 	  		/* AZAM */
 	  		var luasK = luasAsal*0.405;
   	  		document.${formName}.txtLuas.value = luasK.toFixed(4);
   	  
    	}
    else
    	//EKAR,DEPA
    	if(jenisLuas == "7"){
   	  		var luasE = document.${formName}.txtLuas5.value;
 	  		var luasD = document.${formName}.txtLuas6.value;
 	  
 	  		var luasH = (luasE*0.4046864)+(luasD*0.00040469);
   	  		document.${formName}.txtLuas.value = luasH;
    
    }
     else
    //RELONG,JEMBA,KAKI PERSEGI
    	if(jenisLuas == "8"){
   	  	var luasR = document.${formName}.txtLuas2.value;
 	  	var luasJ = document.${formName}.txtLuas3.value;
 	  	var luasK = document.${formName}.txtLuas4.value;
 	  
 	  	var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
   	 	 document.${formName}.txtLuas.value = luasH;
    
    }
 
 
 }
	
//	imej/frmRekodPembangunanImejIndex

	//[]
	function baru(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&idHakmilik="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranImej"; 				
		doAjaxCall${formName}("","firstAction=PendaftaranImej&idHakmilik="+id);
	}
	
	//[Simpan]
	function XsimpanDetailImej(id){
		if(document.${formName}.fileupload.value == ""){
			alert("Sila pilih fail terlebih dahulu");
			return;
		}
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		var perihal = document.${formName}.txtPerihalImej.value;
		var ringkas = document.${formName}.txtRingkas.value ;
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranImej"; 				

		document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=tambahDetailImej&idHakmilik="+id+"&txtPerihalImej="+perihal+"&txtRingkas="+ringkas;
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
	    document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();
		
	}

	// 2013/01/08
	function simpanDetailImej(id){
		
		/*if(document.${formName}.fileupload.value == ""){
			alert("Sila pilih fail terlebih dahulu");
			return;
		}*/

		
		var size = document.${formName}.fileupload.length;
		var pass=true;
	
		if(size>1){
			for(var i=0 ; i < document.${formName}.fileupload.length; i++) { 
	    		if (document.${formName}.fileupload[i].value==""){
	    			pass=false;
	  			}
			}
		}else{
			if (document.${formName}.fileupload.value==""){
				pass=false; 
	    	}
		}
	
		if(!pass){
			alert("Sila pilih fail terlebih dahulu");
	  		document.${formName}.fileupload.focus(); 
			return;
		}
		
		var urljava = "";
		
		if(document.${formName}.negeri.value=="SARAWAK" || document.${formName}.negeri.value=="SABAH"){ 
			
			urljava = "ekptg.view.htp.negeri.rekod.FrmRekodTanahNegeri";
			
		}else{
			
			urljava = "ekptg.view.htp.rekod.FrmRekodTanah";
			
		}
		
		
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		var perihal = document.${formName}.txtPerihalImej.value;
		var ringkas = document.${formName}.txtRingkas.value ;
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranImej"; 				

		document.${formName}.action = "?_portal_module="+urljava+"&firstAction=PendaftaranImej&nextAction=tambahDetailImej&idHakmilik="+id+"&txtPerihalImej="+perihal+"&txtRingkas="+ringkas;
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
	    document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();
	
	}
	
	function viewDetailImej(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=viewDetailImej&idGambar="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranImej"; 				
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=viewDetailImej&idGambar="+id);
	}
	
	function viewDetailInfo(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=viewDetailInfo&idGambar="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranImej"; 				
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=viewDetailInfo&idGambar="+id);
	}
	
	function kemaskininDetailImej(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=kemaskiniDetailImej&idGambar="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranImej"; 				
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=kemaskiniDetailImej&idGambar="+id);
	}
	
	function updateDetailImej(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=updateDetailImej&idGambar="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranImej"; 		
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=updateDetailImej&idGambar="+id);
	}
	
	function cetakImej(id){
		var url = "../servlet/ekptg.view.htp.FrmRekodDisplayImej?id="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function deleteDetailImej(id){	
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		//document.${formName}.command.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=deleteDetailImej&idGambar="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranImej"; 		
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=deleteDetailImej&idGambar="+id);
	}

	function doChangeJumlahLampiran(IDJadualLampiran,j,a) {		
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}//&nextAction=viewDetailImej
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranImej"; 		
		doAjaxCall${formName}("imej","firstAction=PendaftaranImej&X="+a+"&action=changeLampiran&IDJadualLampiran="+IDJadualLampiran);
	
	}
		
	function doChangeJumlahLampiran1(IDJadualLampiran,j,a) {
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}//&nextAction=viewDetailImej
		//2020/04/12
		//document.${formName}.firstAction.value = "ImejPembangunan"; 		
	 	doDivAjaxCall${formName}("imejId","ImejPembangunan","firstAction=ImejPembangunan&X="+a+"&action=changeLampiran1&IDJadualLampiran="+IDJadualLampiran);
	
	}
	
	//12/07/2012
	function maklumatImej(idhakmilik){
		//var url = "../servlet/ekptg.view.htp.utiliti.FrmSenaraiImej?template=MaklumatHakmilik&idHakmilik="+idhakmilik;
		var url = "../x/${securityToken}/ekptg.view.htp.utiliti.FrmSenaraiImej?idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
	//06/05/2017
	function maklumatGIS(idhakmilik){
		//var url = "../servlet/ekptg.view.htp.utiliti.FrmSenaraiImej?template=MaklumatHakmilik&idHakmilik="+idhakmilik;
		var params = "&nofail=&kod=";
		var url = "http://10.19.127.155/viewmap.php?upi=0108020000002153";
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}	
	function cetakImejSenarai(id){
		doAjaxCall${formName}("view","idGambar="+id);
	}
	
	function cetakImejSenaraiFull(id){
		doAjaxCall${formName}("viewFull","idGambar="+id);
	}
	
	//RIZAB
	
	//frmRekodSenaraiHakmilikRizabIndex.jsp -4 [link No. Warta]	
	function rizabD_detail(id_,status){
		//2020/04/12
		document.${formName}.firstAction.value = "PendaftaranHakmilik"; 		
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id_+"&statusSah="+status);
	}

	function rizabDetail(id_,status){
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id_+"&statusSah="+status);
	}
	function rizab_detail(id_,status){
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id_+"&statusSah="+status);
	}
	
	//[Tab Rizab]
	function selectTabRizab(id){
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 	
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&tabId="+id);		
	}
	
	function kemaskini_detailRizab(id_){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&/PendaftaranHakmilik&nextAction=kemaskiniDetailRizab&idHakmilik="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 	
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailRizab&idHakmilik="+id_);		
	}
	
	
	//01/06/2010
	function cetakMaklumatRizab(idhakmilik){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatRizab&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
		
	function update_detailRizab(id){
		//VALIDATAION
		 if(document.${formName}.socNegeriHR.value == 99999){ 
			alert('Sila masukkan " Negeri " terlebih dahulu.'); 
			document.${formName}.socNegeriHR.focus();
			return; 
		 }
		 if(document.${formName}.txdTarikhTerima.value == ""){ 
			alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
			document.${formName}.txdTarikhTerima.focus();
			return; 
		 }
		 if(document.${formName}.txtNoWarta.value == ""){ 
			alert('Sila masukkan " No Warta " terlebih dahulu.'); 
			document.${formName}.txtNoWarta.focus();
			return; 
		 }
		 if(document.${formName}.txdTarikhWarta.value == ""){ 
			alert('Sila masukkan " Tarikh Warta " terlebih dahulu.'); 
			document.${formName}.txdTarikhWarta.focus();
			return; 
		 }
		 if(document.${formName}.txtLokasi.value == ""){ 
			alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
			document.${formName}.txtLokasi.focus();
			return; 
		 }
		 if(document.${formName}.txtKegunaanTanah.value == ""){ 
			alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
			document.${formName}.txtKegunaanTanah.focus();
			return; 
		 }			
		 if(document.${formName}.socLuas.value == ""){ 
			alert('Sila masukkan " Jenis Luas " terlebih dahulu.'); 
			document.${formName}.socLuas.focus();
			return; 
		 }   
		 if(document.${formName}.txtKegunaanTanah.value == ""){ 
			alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
			document.${formName}.txtKegunaanTanah.focus();
			return; 
		 }
		 if(document.${formName}.txtLuas.value == ""){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
			document.${formName}.txtLuas.focus();
			return; 
		 }
		 if(document.${formName}.socStatusDaftar.value == ""){ 
			alert('Sila masukkan " Status Sah " terlebih dahulu.'); 
			document.${formName}.socStatusDaftar.focus();
			return; 
		 }     
		var str1 = document.${formName}.txdTarikhTerima.value; 
	  	var dt1 = parseInt(str1.substring(0,2),10); 
	  	var mon1 = parseInt(str1.substring(3,5),10)-1; 
	  	var yr1 = parseInt(str1.substring(6,10),10);
	  	var tarikhTerima = new Date(yr1, mon1, dt1);
	  	var str2 = document.${formName}.txdTarikhWarta.value; 
	  	var dt2 = parseInt(str2.substring(0,2),10); 
	  	var mon2 = parseInt(str2.substring(3,5),10)-1; 
	  	var yr2 = parseInt(str2.substring(6,10),10); 
	  	var tarikhDaftar = new Date(yr2, mon2, dt2); 
	  	var currentDate = new Date(); 
	  	if (tarikhTerima > currentDate){ 
	  		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.'); 
	   	 	document.${formName}.txdTarikhTerima.focus(); return; 
	  	} 
	  	if (tarikhDaftar > currentDate){ 
	   		alert('Tarikh Daftar tidak boleh melebihi dari tarikh hari ini.'); 
	   	  	document.${formName}.txdTarikhWarta.focus(); return; 
	  	} 
	  	if (tarikhDaftar > tarikhTerima){ 
	    	alert('Tarikh Daftar tidak boleh melebihi dari Tarikh Terima.'); 
	     	document.${formName}.txdTarikhTerima.focus(); return; 
	  	}
	    
	 	if ( !window.confirm("Adakah Anda Pasti ?") ){
		   return;
	 	}
		//END OF VALIDATION
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=updateDetailRizab&idHakmilik="+id;
		//document.${formName}.submit();
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranHakmilik"; 	
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=updateDetailRizab&tabId="+id);
	
	}	
	

	//END OF RIZAB
	
	//START LAIN2
	
	function viewTransaksiCukai(id) {
		var url = "../x/${securityToken}/ekptg.view.htp.FrmRekodTransaksiCukai?idHakmilik="+id;
	    var hWnd = window.open(url,'printuser','width=300,height=300, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}
	
	function viewTransaksiAgensi(id_) {
		var url = "../x/${securityToken}/ekptg.view.htp.rekod.FrmRekodTransaksiHakmilikAgensi?idHakmilik="+id_;
	    var hWnd = window.open(url,'printuser','width=500,height=250, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}
	
		//[Cetak]
	//15/08/2012
	function XcetakPembangunan(idhakmilik,template){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template="+template+"&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
	
	function doPengesahanHq(idHakmilikPerihal) {
	 	document.${formName}.idHakmilikPerihal.value = idHakmilikPerihal; 	
		//2020/04/12
		//document.${formName}.firstAction.value = "PendaftaranPembangunan"; 	
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=pengesahanHq");
	}
	
</script>
#parse("app/htp/rekod/utiliti/javaScriptRekodPembangunan.jsp")
#parse("app/htp/rekod/utiliti/pindahfail/javaScriptRekodFailBaru.jsp")
#parse("app/htp/utiliti/javaScriptUmum.jsp")

<script>

</script>

<script>
function viewDetailSewa(){
	//alert('frmRekodSenaraiHakmilikRizabIndexV03');
	doDivAjaxCall$formname('div_maklumatPenyewaan','doViewDetailSewa','');
}

function terperinciPajakan(idPer,idTanah){
	//alert('frmRekodSenaraiHakmilikRizabIndexV03:terperinciPajakan');
	doDivAjaxCall$formname('divmaklumatpajakan','pajakanterperinci','');
}
</script>


<!-- LINK FROM DASHBOARD! -->
#if($!fromDashboard.equalsIgnoreCase("yes"))
	<script>
		window.onload = function () {
			var idhm = '$!idHakmilikDashboard';
			var stt = '$!statusSahDashboard';
			var noHakmilikDashboard = '$!noHakmilikDashboard';
			
			if(noHakmilikDashboard!="" && noHakmilikDashboard!=null){
				doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+idhm+"&statusSah="+stt);
			}else{
				doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+idhm+"&statusSah="+stt);
			}
		}
	</script>	
#end


