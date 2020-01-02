<strong><center><font size="3pt">Maklumat Strata</font></center></strong>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="hitButton">
  <input type="hidden" name="idStrata">
</p>
<fieldset>
<legend>
<strong>Carian</strong></legend>
<table width="100%">
	<tr>
		<td>
			<p style="padding: 2pt 4pt 3pt 0%;"><font color="red">Sila masukkan maklumat carian</font></p>
			<table width="100%" border="0" align="center">
				<tr>
				   <td scope="row" align="right">Negeri</td>
				   <td width="1%" scope="row">:</td>
				   <td>$selectNegeriD dan</td>
				</tr>
                <tr>
				 	<td width="29%" scope="row" align="right">No Hakmilik Strata</td>
				 	<td width="1%" scope="row">:</td>
				 	<td><input name="paramNoStrata" type="text" id="paramNoStrata" value="$!paramNoStrata" size="40" /> atau</td>
				 </tr>
				<tr>
				 	<td width="29%" scope="row" align="right">No Lot</td>
				 	<td width="1%" scope="row">:</td>
				 	<td>$selectKodLot <input name="paramNoLot" type="text" id="paramNoLot" value="$!paramNoLot" size="10" /> atau</td>
				 </tr>
			  	 <tr>
				 	<td width="29%" scope="row" align="right">No CF</td>
				 	<td width="1%" scope="row">:</td>
				 	<td><input name="paramNoCF" type="text" id="paramNoCF" value="$!paramNoCF" size="40" /> atau</td>
				 </tr>
				 <tr>
				 	<td width="29%" scope="row" align="right">Nama Pemaju</td>
				 	<td width="1%" scope="row">:</td>
				 	<td><input name="paramNamaPemaju" type="text" id="paramNamaPemaju" value="$!paramNamaPemaju" size="40" /> atau</td>
				 </tr>
				 <tr>
				 	<td width="29%" scope="row" align="right">Nama Skim</td>
				 	<td width="1%" scope="row">:</td>
				 	<td><input name="paramNamaSkim" type="text" id="paramNamaSkim" value="$!paramNamaSkim" size="40" /></td>
				 </tr>
			  	<tr>
				    <td colspan="2" scope="row">
				      <label></label></td>
				    <td>
				      <label>
				        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
				        <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
				        </label>    
				    </td>
				  </tr>
			</table>
		</td>
	</tr>
</table>
</fieldset>
<fieldset>
<legend><strong>Senarai Pembangunan Strata</strong></legend>
	#set ($Senaraifail = $session.getAttribute("_portal_moduleVectorInternalFail"))
	#set ($startno = $startnoInt.intValue())
	#set ($i = $startno)
	#set ($total = $totalInt.intValue())
<table width="100%">
	<p><font color="red">Klik pada pautan Nama Pemilik untuk melihat maklumat terperinci.</font></p>
	<tr>
		<td>#parse("app/utils/record_paging.jsp")
	     </td>		  
	</tr>
	<tr>
		<td>
			<table width="100%" cellspacing="2" cellpadding="1" border="0">
				  <tr class="table_header">
				  	<td width="2%" align="center">No</td>
				  	<td width="5%"align="center">Negeri</td>	
				  	<td width="10%"align="center">No dan Tarikh CF/CCC/No Kelulusan Khas</td>	
					<td width="5%" align="center">No. Hakmilik</td>
					<td width="15%" align="center">Nama Pemaju</td>
				  	<td width="15%" align="center">Nama Skim</td>
				  	<td width="5%" align="center">No.PT/Lot</td>
				  	<td width="5%" align="center">Status Permohonan Strata</td>
				  </tr>
				  #if($SenaraiFail.size() > 0)
					#foreach ($result in $SenaraiFail )
					#set( $counter = $velocityCount )
					#if ( ($counter % 2) == 0 )
						#set( $row = "row2" )
					#else
						#set( $row = "row1" )
					#end
					
				<tr>
					  <td class="$row" align="center">
					  #set ($cnt = ($page - 1) * $itemsPerPage + $counter )
					  $!cnt</td>
					 #if($result.Negeri == '')
					  <td class="$row" align="center" style="text-transform:uppercase;">$!result.NegeriHm</td>
                    	#else <!--if($result.NegeriHm == '')-->
					  <td class="$row" align="center" style="text-transform:uppercase;">$!result.Negeri</td>
					  #end
					  <td class="$row" align="center">
					    #if($result.flagCf=='Y')
							$!result.cf
						#else
							$!result.khas
						#end
					  </td>
					  
					  #if($result.noHakmilik == '')
                      <td class="$row" align="center">-</td>
                      #else
					  <td class="$row" align="center" style="text-transform:uppercase;">$result.noHakmilik</td>
                      #end
                      #if($result.NamaPemaju == '')
                      <td class="$row" align="center"><a href="javascript:papar('$result.Id')" class="style1"><font color='blue'>-</font></a></td>
                      #else
					  <td class="$row" style="text-transform:uppercase;"><a href="javascript:papar('$result.Id')" class="style1"><font color='blue'>$result.NamaPemaju</font></a></td>
                      #end
					  <td class="$row" style="text-transform:uppercase;">$result.NamaSkim</td>
					  <td class="$row" style="text-transform:uppercase;">$result.KodLot $result.NoLot</td>
					  <td class="$row" style="text-transform:uppercase;">$result.Status</td>
				</tr>
				#end
				#else
					<tr>
						<td colspan="6">Rekod Tidak Dijumpai</td>
					</tr>
				#end
			</table>
		</td>
	</tr>
	
</table>
</fieldset>
<input type="hidden" name="mode" />


<script type="text/javascript">
function carian(){
	document.${formName}.hitButton.value = "cari";
	doAjaxCall${formName}("doCarian");
}

function papar(idStrata) {
	document.${formName}.idStrata.value = idStrata;
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "papar";
	document.${formName}.submit();
}

function kosongkan() {

	document.${formName}.reset();
	document.${formName}.paramNoLot.value = "";
	document.${formName}.paramNoCF.value = "";
	document.${formName}.paramNamaPemilik.value = "";
	document.${formName}.paramNamaSkim.value = "";
	document.${formName}.hitButton.value = "kosongkan";
	document.${formName}.submit();

}
</script>