 <style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>

#if($semakSubUrusan == false)
<table width="100%" border="0" cellspacing="2" cellpadding="2">
<tr>
<td>
<div class=warning>[MODUL HTP REKOD] SILA KEMASKINI MAKLUMAT SUB URUSAN BAGI FAIL YANG DIPILIH TERLEBIH DAHULU</div>
</td>
</tr>
</table>
#else
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td >
		#parse("app/htp/paging_penerimaanhakmilikrizab.jsp")
		</td>
	</tr>
	<tr>
    	<td>
			<fieldset><legend>SENARAI HAKMILIK</legend>
			<table width="100%">
				<tr>
					<td align="left">
						<div align="left">
							<input type="button" class="stylobutton100" value="Tambah" onclick="javascript:TambahHakmilikByIdPermohonan('$idPermohonan')" />  
							<input type="button" class="stylobutton100" value="Kembali" onclick="javascript:doAjaxCall${formName}('');" />  
						</div>
					</td>
				</tr>
			</table>
			<table width="100%">
				<tr>
			    	<td colspan="6">
			    		##parse("app/utils/record_paging.jsp")
	    			</td>
			    </tr> 
				<tr class="table_header">
				  <td width="3%"><strong>Bil.</strong></td>
			   	  <td width="15%"><div align="left"><strong>No. Fail</strong></div></td>
			   	  <td width="12%"><div align="left"><strong>No. Hakmilik</strong></div></td>
			  	  <td width="10%"><div align="left"><strong>No. Lot/PT</strong></div></td>
			   	  <td width="15%"><div align="left"><strong>Status Hakmilik</strong></div></td>
			  	  <td width="40%"><div align="left"><strong>Kegunaan Tanah</strong></div></td>
				</tr>
				
			#foreach ($senarai in $SenaraiTanah)
				#set( $i = $velocityCount )
			    #if ( ($i % 2) != 1 )
			       #set( $row = "row2" )
			    #else
			       #set( $row = "row1" )
			    #end
			    <tr class="$row">
			    	<td width="1%">$senarai.bil</td>
			    #if($senarai.bil != '')
			       <td width="18%">
			        <a href="javascript:kemaskiniHakmilik('$senarai.idPermohonan','$senarai.idHakmilik');" class="style1" onMouseOver="this.style.cursor='pointer';" alt="" title="Kemaskini Hakmilik" >
			       $senarai.noFail
			       </a></td>
			   	#else
			    	<td width="13%">$senarai.noFail</td>
			    #end
			   		<td width="8%"><div align="left">$senarai.kodJenis $senarai.noHakmilik $senarai.noStrata $senarai.noWarta</div></td>
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
#end
<input name="INS_UPD" type="hidden"/>
<input name="mode" type="hidden"/>
<input name="idPermohonan" type="hidden" value="$!idPermohonan"/>
<input name="idHakmilik" type="hidden"/>	
