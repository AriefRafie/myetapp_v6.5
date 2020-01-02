<div id="divBhgnUnitLengkap" class="page-break" >

<body>
<h2>Maklumat Bahagian </h2>
<table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
  <tr>
 
  <td width="5%"  bgcolor="#666666">
   </td>
    
    <td width="95%"> 
    <p align="right"><h3>$viewBahagianHQ.NAMA_SEKSYEN </h3>
         <br>
    </p>
    </td> 
    
  </tr>
 
</table>


<p>Statistik Ringkas :</p>

<table width="80%" id="t01" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
  <tr>
 
  <th width="5%" colspan bgcolor="#666666" "5"> </th>
    
    <th width="95%"> 
    <p align="left">Jawatan:</p>
<p align="left">Jumlah Pengguna Aktif : $totalRecords</p>
<table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
  
  <!--<tr>
  #set($rowStrech = $listStatsJawatan.length)
    <td colspan bgcolor="#666666" "5" width="5%" rowspan="$rowStrech">&nbsp;</td>
    <td colspan bgcolor="#666666" width="52%">Jawatan</td>
    <td colspan bgcolor="#666666" width="39%">Jumlah Pengguna</td>
  </tr>-->
  
  <tr colspan="14">
				
				<td valign="top" >
				Nama Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.NAMA_SEKSYEN
				</td>
			</tr>
           <!-- <tr>
            <td>
           <input type="text" id="ID_SEKSYEN" 
				name="ID_SEKSYEN" 
				value="$viewBahagianHQ.ID_SEKSYEN" >
        
            </td></tr>-->
            <tr colspan="14">
				
				<td valign="top" >
				Nama Lain Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.KOD_SEKSYEN
				</td>
			</tr>
			
			 <tr>
						
				<td valign="top" >
				Alamat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.ALAMAT_1
				</td>
			</tr>
		
			<tr>
						
				<td valign="top" >
				
				</td>
				<td valign="top" >
				
				</td>
				<td valign="top" >
				$viewBahagianHQ.ALAMAT_2
				</td>
			</tr>
			
			<tr>
							
				<td valign="top" >
				
				</td>
				<td valign="top" >
				
				</td>
				<td valign="top" >
				$viewBahagianHQ.ALAMAT_3
				</td>
			</tr>
			<tr>
							
				<td valign="top" >
				Poskod
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.POSKOD
				</td>
			</tr>
           <!-- <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Bandar
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.BANDAR
				</td>
			</tr>-->
            
            <tr>
						
				<td valign="top" >
				Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.NAMA_NEGERI
				</td>
			</tr>
			<tr>
							
				<td valign="top" >
				No.Telefon
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.NO_TEL
				</td>
			</tr>
			
			<tr>
						
				<td valign="top" >
				Faks</td>
				<td valign="top" >
				:</td>
				<td valign="top" >
				$viewBahagianHQ.NO_FAKS
				</td>
			</tr> 
            
            <tr>
						
				<td valign="top" >
				Emel</td>
				<td valign="top" >
				:</td>
				<td valign="top" >
				$viewBahagianHQ.EMEL
				</td>
			</tr> 
			
			<tr colspan="14">
						
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<textarea row="5" col="10" readonly>$viewBahagianHQ.CATATAN</textarea>
				</td>
			</tr>
			
			
			<tr colspan="14">
						
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.FLAG_AKTIF
				</td>
			</tr>
            
            <tr colspan="14">
							
				<td valign="top" >
				Dikemaskini oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.USER_NAME
				</td>
			</tr>
            
            <tr colspan="14">
						
				<td valign="top" >
				Dikemaskini pada
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.TARIKH_KEMASKINI
				</td>
			</tr>
 
</table>
   <br>
    </th> 
    
  </tr>
 
</table>



<br>
Senarai Unit bagi Bahagian ini :
<br>

<table width="80%" id="senaraiUser" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >

 <th width="5%" height="311" colspan bgcolor="#666666" "5"> </th>
  
  <th width="95%"> 
  <table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" align="left">
  <tr> 
  #set($rowStrech = $listUnitHQ.length)
   
    <td colspan bgcolor="#666666" width="6%">Bil</td>
    <td colspan bgcolor="#666666" width="43%">Nama Pengguna</td>
   <!-- <td bgcolor="#666666" width="43%">Catatan</td>-->
  </tr>
  
    #if($listUnitHQ.size()>0)
	#foreach($PejUrus in $listUnitHQ)
  	<tr>
  
 	<td align="center" valign="top" >$PejUrus.BIL.</td>
	<td  align="left" valign="top">$PejUrus.NAMA_UNIT 
    <br>Alamat : $PejUrus.ALAMAT1, $PejUrus.ALAMAT2, $PejUrus.ALAMAT3
    <br>$PejUrus.POSKOD, $PejUrus.NEGERI
    <br>No tel : $PejUrus.NO_TEL
    </td>
   <!-- <td   align="center" valign="top" >$PejUrus.KEAKTIFAN </td>-->
  </tr>
  #end
  #end
  </table>
  </th>
</table>


<br>
<h3>Catatan* : </h3>
*sekiranya terdapat sebarang perubahan pada Maklumat Bahagian atau sebarang perkara lain.
<br>


<table width="80%" height="180" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
  <tr>
 
  <th width="5%" colspan bgcolor="#666666" "5">
   </th>
    
    <th width="95%"> 
  
    </th> 
    
  </tr>
 
</table>


<p>&nbsp;</p>
<p>&nbsp;</p>
</body>
</div>

<div id="dummy"></div>

<script type="text/javascript">
	printHideDivAll('divBhgnUnitLengkap','dummy');
	</script>