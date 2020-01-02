<div>
<body>
<h2>Lampiran A</h2>
<p>Senarai Peranan yang akan diberikan kepada Pengguna ini :</p>

<table width="80%" id="t01" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
  <tr>
 
  <th width="5%" colspan bgcolor="#666666" "5"> </th>
    
    <th width="95%"> 
<table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" align="left">
<tr class="table_header" bgcolor="#999999">
    <td align="center" valign="top"><strong>Pilih</strong></td>
		<td align="center" valign="top"><strong>Nama Peranan</strong></td>
        <td align="center" valign="top" ><strong>Penerangan</strong></td>
        </tr>
	#foreach($rl in $listRoleByUserLogin)
		#if($rl.LAYER=="1")
		<tr class="table_header" bgcolor="#CCCCCC">
		<td align="center" valign="top">
		<input type="hidden" id="TEMP_GROUP_CHECKLIST_$internalType$USER_ID" name="TEMP_GROUP_CHECKLIST_$internalType$USER_ID" value="$rl.KOD" />
		</td>
		<td  colspan="2">
		<b>$rl.KOD</b>
		#if($rl.TOTAL>0)
		($rl.TOTAL Peranan/Role Dipilih)
		#end
		</td>
		</tr>
		#end
		#if($rl.LAYER=="2")
		<tr class="row2" >
		<td align="center" valign="top" >
		
		</td>
		<td align="left" valign="top">$rl.ID</td><td> $rl.KETERANGAN</td>
		</tr>
		#end						
	#end	
</table>
   <br>
    </th> 
    
  </tr>
 
</table>

<br>
<h3>Catatan* : </h3>
*sekiranya Pengguna ini memerlukan Peranan selain dari senarai di atas
<br>


<table width="80%" height="180" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
  <tr>
 
  <th width="5%" colspan bgcolor="#666666" "5">
   </th>
    
    <th width="95%"> 
  
    </th> 
    
  </tr>
 
</table>

</body>
</div>
