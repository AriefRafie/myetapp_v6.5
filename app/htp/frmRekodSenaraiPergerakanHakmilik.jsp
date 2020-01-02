<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<table width="98%" border="0">
	<tr>
		<td>
			<fieldset><legend>CARIAN</legend>
				<table border="0" width="100%">
					</tr>       
				        <td align="right"><div align="right">Negeri</div></td>
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
			         	<td align="right"><div align="right">Jenis Hakmilik</div></td>
			         	<td><div align="center">:</div></td>
			         	<td>$selectJenisHakmilik</td>
			      	</tr>
			      	<tr>
			        	<td align="right" width="40%"><div align="right">No. Hakmilik</div></td>
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
			    		<td align="right"><div align="right">No Fail</div></td>
			    		<td width="1%"><div align="center">:</div></td>
			     		<td width="59%"><input name="txtNoFail" type="text" id="txtNoFail" value="$txtNoFail" size="43" /></td>
			  		<tr>
			      	<tr>
			        	<td></td>
			        	<td>&nbsp;</td>
			        	<td>
			        		<input type="button" name="btnCari" id="btnCari" value="Cari" onclick="cari()" class="stylobutton100"/>
			        		<input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongCarian()" class="stylobutton100" />        
			        	</td>
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
				  <td width="3%">Bil.</td>
			   	  <td width="12%"><div align="left">No. Hakmilik</div></td>
			   	  <td width="10%"><div align="left">
			   	    <div align="center">No. Lot / PT</div>
			   	  </div></td>
			  	  <!--<td width="15%"><div align="left">Status<br /> 
			  	  </div></td>-->
			   	  <td width="60%"><div align="left">Kegunaan Tanah</div></td>
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
			    <a href="javascript:pergerakanhakmilik_detail('$senarai.idHakmilik')" class="">
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
</table>

<script>
function pergerakanhakmilik_detail(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=paparDetailPergerakanHakmilik&idHakmilik="+id;	
	document.${formName}.submit();
}
function cari(){   
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=carianHakmilikRizab";
	document.${formName}.submit();
}
function kosongCarian(){

	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoHakmilik.value = "";
	document.${formName}.txtNoLot.value = "";
	document.${formName}.socNegeri.value = "";
    document.${formName}.socDaerah.value = "";
	document.${formName}.socMukim.value = "";	
	document.${formName}.socJenisHakmilik.value = "";	
	document.${formName}.socAgensi.value = "";	
	document.${formName}.socJenisLot.value = "";	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=";
	document.${formName}.submit();
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
</script>

</script>
