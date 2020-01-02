<fieldset>
	<legend>
		<strong>Carian</strong>
	</legend>
	<table width="100%">
		<tr>
			<td width="29%" align="right" scope="row">Negeri</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">$selectNegeri</td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row">Daerah</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">$selectDaerah</td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row">No. Warta</td>
			<td width="1%" scope="row">:</td>
			<td width="70%"><input name="paramNoWarta" type="text"
				id="paramNoWarta" value="$!paramNoWarta" /></td>
		</tr>
		<tr>
			<td align="right" scope="row">No. Pelan</td>
			<td scope="row">:</td>
			<td><input name="paramNoPelan" type="text" id="paramNoPelan"
				value="$!paramNoPelan" /></td>
		</tr>
		<tr>
			<td align="right" scope="row">Status</td>
			<td scope="row">:</td>
			<td><select id="paramStatus" name="paramStatus">
					<option value="">SILA PILIH</option>
					<option value="0">Tidak Kuatkuasa</option>
					<option value="1">Kuatkuasa</option>
			</select></td>
		</tr>
		<tr>
			<td align="right" scope="row">&nbsp;</td>
			<td scope="row">&nbsp;</td>
			<td><input type="submit" name="cmdCari" id="cmdCari"
				value="Cari" onclick="searchAkta()" /> <input type="reset"
				name="cmdKosongkanAkta" value="Kosongkan"
				onclick="kosongCarianAkta()" /></td>
		</tr>
	</table>
</fieldset>
<fieldset>
	<legend>
		<strong>Perwujudan Tanah Rizab Melayu</strong>	
	</legend>
	<input value="MuatnaikTRM" onclick="tambahAkta()" type="button">
	#set ($Senaraifail = $session.getAttribute("_portal_moduleVectorInternalFail"))
	#set ($startno = $startnoInt.intValue())
	#set ($i = $startno)
	#set ($total = $totalInt.intValue())
	
	#parse("app/utils/record_paging.jsp") 
	
	<table width="100%">

		<tr class="table_header">
			<td width="3%" align="center" scope="row"><strong>Bil.</strong></td>
			<td width="13%" align="center" ><strong>Negeri</strong></td>
			<td width="15%"  align="center">Daerah/Jajahan</strong></td>
			<td width="14%"  align="center">Mukim/Kawasan(locality)</strong></td>
			<td width="10%"  align="center">No.Pelan</strong></td>
			<td width="15%" align="center">No. Warta&Tarikh</strong></td>
			<td width="10%"  align="center">Luas(hektar)</strong></td>
			<td width="10%"  align="center">Status</strong></td>
			<td width="10%" align="center"></strong></td>
		</tr>
		#if($ListResult.size() > 0) 
		#foreach ($result in $ListResult ) 
		#set($counter = $velocityCount ) 
		#if ( ($counter % 2) == 0 ) 
			#set( $row ="row2" ) 
		#else 
			#set( $row = "row1" ) 
		#end
		<tr>
			<td height="20"  align="center" class="$row">$result.Bil</td>
			<td class="$row"  align="center">$result.Negeri</td>
			<td class="$row"  align="center">$result.Daerah</td>
			<td class="$row" align="center">$!result.Mukim <br />
				$!result.Kawasan <br />
			</td>
			<td class="$row"  align="center">$result.NoPelan</td>
			<td class="$row" align="center">$!result.NoWarta <br />
				$!result.TarikhWarta <br />
			</td>
			<td class="$row"  align="center">$result.Luas</td>
			<td class="$row"  align="center">$result.Status</td>
		      <td colspan="3">
		      <a href="javascript:onClick=viewAktaBlob('16103');"style="color:#0000FF"  align="center"><center>[Kemaskini]</center></a></td>
	
		</tr>
		#end #else
		<tr>
			<td colspan="6">Rekod Tidak Dijumpai</td>
		</tr>
		#end
	</table>
</fieldset>
<fieldset>
	<legend>
		<strong>Perwujudan Tanah Rizab Melayu</strong>
		
		
	</legend>
	#set ($Senaraifail = $session.getAttribute("_portal_moduleVectorInternalFail"))
	#set ($startno = $startnoInt.intValue())
	#set ($i = $startno)
	#set ($total = $totalInt.intValue())
	
	#parse("app/utils/record_paging.jsp") 

	<table width="100%">

		<tr class="table_header">
			<td width="3%" align="center" scope="row"><strong>Bil.</strong></td>
			<td width="25%" align="center">No. Warta&Tarikh</strong></td>
			<td width="24%"  align="center">No.Pelan</strong></td>
			<td width="24%"  align="center">Mukim/Kawasan(locality)</strong></td>
			<td width="24%"  align="center">Luas(hektar)</strong></td>
		</tr>
		#if($ListResult.size() > 0) 
		#foreach ($result in $ListResult ) 
		#set($counter = $velocityCount ) 
		#if ( ($counter % 2) == 0 ) 
			#set( $row ="row2" ) 
		#else 
			#set( $row = "row1" ) 
		#end
		<tr>
			<td height="20"  align="center" class="$row">$result.Bil</td>
			<td class="$row" align="center">$!result.NoWarta <br />
				$!result.TarikhWarta <br />
			<td class="$row"  align="center">$result.NoPelan</td>
			<td class="$row" align="center">$!result.Mukim <br />
				$!result.Kawasan <br />
			</td>
			<td class="$row"  align="center">$result.Luas</td>
		</tr>
		#end #else
		<tr>
			<td colspan="6">Rekod Tidak Dijumpai</td>
		</tr>
		#end
	</table>
</fieldset>
<fieldset>
	<table>
		<input type="button" value="Cetak" style="float: right;"/>
	</table>
</fieldset>
