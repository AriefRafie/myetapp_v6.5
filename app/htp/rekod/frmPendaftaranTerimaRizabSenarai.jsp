<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td >
			#parse("app/htp/paging_penerimaanrizab.jsp")
					</td>
	</tr>
	<tr>
    	<td>
			<fieldset><legend>SENARAI RIZAB</legend>
			<table width="100%">
				<tr>
					<td align="left">
						<div align="left">
							<input type="button" class="stylobutton100" value="Tambah" onclick="javascript:TambahRizabByIdPermohonan('$idPermohonan')" />  
							<input type="button" class="stylobutton100" value="Kembali" onclick="javascript:doAjaxCall${formName}('');" />  
						</div>
					</td>
				</tr>
			</table>
			
			<table border="0" width="100%">
				<tr>
	    			<td colspan="6">
	    				##parse("app/utils/record_paging.jsp")
	    			</td>
    			</tr>
				<tr class="table_header">
					<td width="3%">Bil.</td>
			   		<td width="15%"><div align="left">No Fail</div></td>
			   	  	<td width="12%"><div align="left">No. Warta</div></td>
			  	  	<td width="10%"><div align="left">No. Lot</div></td>
			   	  	<td width="15%"><div align="left">Status </div></td>
			  	  	<td width="40%"><div align="left">Kegunaan Tanah</div></td>
			 	</tr>
			 	
			#foreach ($senarai in $SenaraiTanah)
  				#set( $i = $velocityCount )
			    #if ( ($i % 2) != 1 )
			       #set( $row = "row2" )
			    #else
			       #set( $row = "row1" )
			    #end
    			<tr class="$row">
    				<td width="1%">
        				<a href="javascript:viewRizab('$!senarai.idPermohonan','$!senarai.idHakmilik');" >
    					$!senarai.bil
       					</a>
    				</td>
    			#if($!senarai.bil != '')
       				<td width="18%">
        				<a href="javascript:viewRizab('$!senarai.idPermohonan','$!senarai.idHakmilik');" class="style1">
       					$!senarai.noFail
       					</a>
       				</td>
     			#else
    				<td width="13%">$!senarai.noFail</td>
    			#end
   					<td width="8%"><div align="left">$!senarai.noWarta</div></td>
    				<td width="8%"><div align="left">$!senarai.labelNolot</div></td>
    				<td width="5%"><div align="center">$!senarai.statusSah</div></td>
    				<td width="23%">$!senarai.kegunaanTanah</td>
				</tr> 
 			#end
 			
			</table>
			</fieldset>
		</td>
	</tr>
</table>

	<input name="INS_UPD" type="hidden"/>
	<input name="mode" type="hidden"/>
	<input name="idPermohonan" type="hidden" value="$!idPermohonan"/>
	<input name="idHakmilik" type="hidden"/>

