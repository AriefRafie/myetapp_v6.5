
	<!-- Carian-->

			<fieldset><legend>CARIAN</legend>
				<table border="0" width="100%">
			  	<!---->	<tr>
					    <td width="29%"align="right"><div align="right">Urusan</div></td>
					    <td width="1%"><div align="center">:</div></td>
					    <td width="70%">
					        #set($checkedMilik = "")
					        #set($checkedRizab = "")
					        #set($checkedMilikRizab = "")
							#if($idJenisTanah == "1")
					          	#set($checkedMilik = "checked")
					         	#set($checkedRizab = "")
					  			#set($checkedMilikRizab = "")
					            
					        #elseif($idJenisTanah == "2")
					         	#set($checkedRizab = "checked")
					         	#set($checkedMilik = "")
								#set($checkedMilikRizab = "")
								
							#elseif($idJenisTanah == "3")
							 	#set($checkedRizab = "")
					         	#set($checkedMilik = "")
								#set($checkedMilikRizab = "checked")
							#else
								#set($checkedRizab = "")
					         	#set($checkedMilik = "")
								#set($checkedMilikRizab = "")								
					        #end
					    	<input type="radio" name="socJenisTanahtemp" value="1" $checkedMilik  onclick="cari()"/>PENYEWAAN
					 		<input type="radio" name="socJenisTanahtemp" value="2" $checkedRizab  onclick="cari()"/>PAJAKAN
					 		<input type="radio" name="socJenisTanahtemp" value="3" $checkedMilikRizab  onclick="cari()"/>PELEPASAN
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
			      	<!-- <tr>
				        <td><div align="right">Status</div></td>
				        <td><div align="center">:</div></td>
				        <td>
				        <select name="socStatus" id="socStatus">
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
			   		</tr> -->
				#end
				
			    ##if ($idJenisTanah == '1')
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
			 	##end
			   	##if ($idJenisTanah == '2')
				      <tr>
				        <td><div align="right">No. Warta</div></td>
				        <td><div align="center">:</div></td>
				        <td><input name="txtNoWartaC" type="text" id="txtNoWartaC" value="$txtNoWartaC" /></td>
				      </tr>
			  	##end
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
			      	<tr>
				        <td></td>
				        <td>&nbsp;</td>
				        <td>
				        	<input type="button" class="stylobutton100" name="btnCari" id="btnCari" value="Cari" onclick="cari()"/>
				        	<input type="button" class="stylobutton100" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongCarian('$idJenisTanah')" />        
				        </td>
			      	</tr>

			  	</table>  
			
			</fieldset>
