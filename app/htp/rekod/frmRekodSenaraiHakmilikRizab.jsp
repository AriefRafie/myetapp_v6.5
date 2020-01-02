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
					    <td align="right"><div align="right">Jenis Tanah </div></td>
					    <td width="1%"><div align="center">:</div></td>
					    <td width="59%">
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
				        #set ($listJenisStatus = ["SILA PILIH","AKTIF","BATAL"])
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
				        <td align="right" width="40%"><div align="right">No. Hakmilik</div></td>
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
					    <td align="right"><div align="right">No Fail</div></td>
					    <td><div align="center">:</div></td>
					   	<td><input name="txtNoFailC" type="text" id="txtNoFailC" value="$txtNoFailC" size="43" />
					     #if ($flagAdvSearch == '')
				                <a href="#" title="More" class="style1" onclick="javascript:more()">Buka Carian Terperinci</a> 
				            #end
				            #if ($flagAdvSearch == 'open') <a href="#" title="Less" class="style1" onclick="javascript:less()">Tutup Carian Terperinci</a> 
				            #end
					     </td>
				  	<tr/>			      
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
					  <td width="3%">Bil.</td>
				   	  <td width="15%"><div align="left">No Fail</div></td>
				   	  <td width="12%"><div align="left">
				   	  #if ($idJenisTanah==1)
				   	  	No. Hakmilik
				   	  #elseif ($idJenisTanah==2)
				   	   	No. Warta
				   	  #else
				   	  	No. Hakmilik/Warta
				      #end
				      </div></td>
				  	  <td width="10%"><div align="left">
				  	  #if ($idJenisTanah==2)
				   	   	No. Lot
				   	  #else
				  	  	No. Lot/PT
				      #end </div></td>
				   	  <td width="15%"><div align="left">Status<br />
				   	  </div></td>
				  	  <td width="40%"><div align="left">Kegunaan Tanah</div></td>
				  </tr>
				##Kemaskini pd 21/09/2011 oleh Mohamad Rosli (Penyelesaian kpd page number apabila Vector kosong) 
				##foreach ($senarai in $SenaraiTanah)
				#foreach ($senarai in $SenaraiFail)
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
				       <td width="18%"><a href="javascript:hakmilik_detail('$senarai.idHakmilik','$senarai.statusSah');" class="style1">$senarai.noFail</a></td>
				      #elseif($senarai.jenisTanah == 'R')
				   	    <td width="18%"><a href="javascript:rizab_detail('$senarai.idHakmilik','$senarai.statusSah');" class="style1">$senarai.noFail</a></td>
				      #else
				   	    <td width="18%">$senarai.noFail</td>
				      #end    
				    #else
				    	<td width="18%">$senarai.noFail</td>
				    #end
				   	<td width="8%"><div align="left">$senarai.kodJenis $senarai.noHakmilik $senarai.noWarta</div></td>
				    <td width="8%"><div align="left">$senarai.kodLot$senarai.noLot</div></td>
				    <td width="5%"><div align="center">$senarai.statusSah</div></td>
				    <td width="23%">$senarai.kegunaanTanah</td>
					</tr> 
				 #end
				</table>
			</fieldset>

		</td>
	</tr>
</table>
				        	
<input name="flagAdvSearch" type="hidden" value="$!flagAdvSearch" />

<script>
	
	function hakmilik_detail(id,status){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id+"&statusSah="+status;
		document.${formName}.submit();
	}
	
	function rizab_detail(id,status){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
		document.${formName}.submit();
	}
	
	function cari(){   
		field_ = document.${formName}.socJenisTanahtemp;
		for (i = 0; i < field_.length; i++){
			if(field_[i].checked==true){
				document.${formName}.socJenisTanah.value = field_[i].value;
			}
		}
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab");
		
	}
	
	function kosongCarian(idJenisTanah){
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
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";
		document.${formName}.submit();
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

	function doChangeTarafRizab(x) { 
		alert("XXXX");
	}

	function more(){
		document.${formName}.flagAdvSearch.value = "open";
		//document.${formName}.submit();
		//doAjaxCall${formName}("","flagAdvSearch=open");
		doAjaxCall${formName}("");
		
	}
	
	function less(){
		document.${formName}.flagAdvSearch.value = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}

</script>
