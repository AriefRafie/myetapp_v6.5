<fieldset>
<legend>CARIAN</legend>
	
	<table width="100%" border="0" >

  		<tr>
    		<td width="1%">&nbsp</td>
    		<td width="20%" align="left">Negeri</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectNegeri</td>
    	</tr>
    	
    	<tr>
    		<td>&nbsp</td>
    		<td align="left">Daerah</td>
    		<td align="center">:</td>
    		<td>$!selectDaerah</td>
    	</tr>
        
        <tr>
    		<td>&nbsp</td>
    		<td align="left">Mukim</td>
    		<td align="center">:</td>
    		<td>$!selectMukim</td>
    	</tr>
    	
    	<tr>
    		<td>&nbsp;</td>
    		<td align="left">Kementerian</td>
    		<td align="center">:</td>
    		<td>$!selectKementerian</td>
    	</tr>
    	
    	<tr>
    		<td>&nbsp;</td>
    		<td align="left">Jabatan/Agensi</td>
    		<td align="center">:</td>
    		<td>$!selectAgensi</td>
    	</tr>
    	
        <tr>
    		<td></td>
    		<td align="left">No. Hakmilik</td>
    		<td align="center">:</td>
    		<td>
            	<input type="text" id="txtNoHakmilik" name="txtNoHakmilik" value="$!txtNoHakmilik" size="20" maxlength="30" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
            </td>
    	</tr>
 
         <tr>
    		<td></td>
    		<td align="left">No. Warta</td>
    		<td align="center">:</td>
    		<td>
            	<input type="text" id="txtnowarta" name="txtnowarta" value="$!txtNoWarta" size="20" maxlength="30" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
            </td>
    	</tr>   	
    	<tr>
    		<td></td>
    		<td align="left">No. LOT</td>
    		<td align="center">:</td>
    		<td>
            	<input type="text" id="txtNoLot" name="txtNoLot" value="$!txtNoLot" size="20" maxlength="30"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
            </td>
    	</tr>
    	
    </table>	
	
</fieldset>

<fieldset>
	<table width="90%" border="0" >
		<tr>
  			<td align="center">
            <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="doSearch()" /> 
      			<input type="button" name="cmdIsiSemula" id="cmdIsiSemula" value="Kosongkan" onClick="kosongkan()"> 
    		</td>
  		</tr>
	</table>
</fieldset>

<br/><br/>

#if($!displayResult.equalsIgnoreCase("true"))
<input type="hidden" name="idHakmilik">


	#parse("app/utils/record_paging.jsp")

	<table style="width:100%;" border="0"> 
		<tr class="table_header">
			<td align="center">BIL</td>
			<td>NO.HAKMILIK/ WARTA</td>
			<td>NO.LOT</td>
			<td>MUKIM</td>
			<td>DAERAH</td>
			<td>KJP</td>
			<td>MILIK/RIZAB</td>
			<td>KEGUNAAN TANAH</td>
			<td>KELUASAN (ha)</td>
		</tr>
		
		#if($listResult.size()!=0)
			#foreach($list in $listResult)
				#set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end	
         		
				<tr>
					<td class="$row" align="center">$!list.bil</td>
					<td class="$row">$!list.no_hakmilikrizab</td>
					<td class="$row">
						<a href="javascript:doViewLot('$!list.id_hakmilik')"><font color="blue">
							$!list.jenis_lot $!list.no_lot
						</font></a>
					</td>
					<td class="$row">$!list.nama_mukim</td>
					<td class="$row">$!list.nama_daerah</td>
					<td class="$row">$!list.nama_kementerian</td>
					<td class="$row">$!list.jenis_tanah</td>
					<td class="$row">$!list.kegunaan_tanah</td>
					<td class="$row" align="right">$!list.luasF</td>
				</tr>
			#end
		#else
			<tr>
	        	<td colspan="4">Tiada rekod</td>
	        <tr>
		#end
		
	</table>
#end


<script>
	function doSearch(){
		document.${formName}.command.value = "%";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KJPRekodCarianController";
		getActionCarian();
		document.${formName}.submit();
	}
	
	function doFilterDropdown() {
		document.${formName}.command.value = "filterDropdown";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KjpRekodCarianController";
		getActionCarian();
		document.${formName}.submit();
	}

	function kosongkan() {
		document.${formName}.command.value = "clear";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KjpRekodCarianController";
		getActionCarian();
		document.${formName}.submit();
	}	
	
	function doViewLot(idHakmilik){
		document.${formName}.command.value = "viewLot";
		document.${formName}.idHakmilik.value = idHakmilik;
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KjpRekodController";
		getAction();
		document.${formName}.submit();
		
	}
	
	function getAction(){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KJPRekodController";
	}
	
	function getActionCarian(){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KJPRekodCarianController";
	}
</script>
