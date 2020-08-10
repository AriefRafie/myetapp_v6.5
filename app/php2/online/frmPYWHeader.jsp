<table width="100%" border="0" cellspacing="2" cellpadding="2">
  	#foreach($beanHeader in $BeanHeader)
    #set($idFail = $beanHeader.idFail)
    #set($subUrusan = $beanHeader.subUrusan)
    #set($idPermohonan = $beanHeader.idPermohonan)
    #set($noPermohonan = $beanHeader.noPermohonan)
    #set($jenisPermohonan = $beanHeader.jenisPermohonan)
    #set($tarikhTerima = $beanHeader.tarikhTerima)
    #set($idStatus = $beanHeader.idStatus) 
    #set($status = $beanHeader.status)    
    #set($idHakmilik = $beanHeader.idHakmilik)
    #set($noFail = $beanHeader.noFail)   
    #set($perkara = $beanHeader.perkara)       
    #end
  <tr>
  	<td><fieldset>
	  <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      		<tr>
      			<td width="30%">Nama Pemohon</td>
	      		<td width="1%">:</td>
	      		<td >$!pemohon.get("namaPemohon")</td>
	      	</tr>
	      	<tr>
	  			<td>
  				#if($!pemohon.get("kategoriPemohon") == "INDIVIDU") 
  					No. Kad Pengenalan/MyID 
		  		#end
		  		#if($!pemohon.get("kategoriPemohon") != "INDIVIDU")
		  			No. Pendaftaran Syarikat/MyCoid
		  		#end
  				</td>
  				<td>:</td>
  				<td>$!pemohon.get("noPengenalan")</td>
  			</tr>
      		<tr>
		      	<td width="30%" valign="top">Alamat</td>
		      	<td width="1%" valign="top">:</td>
		      	<td>$!pemohon.get("alamat1") $!pemohon.get("alamat2")<br>$!pemohon.get("alamat3")<br/>
		      		$!pemohon.get("poskod") $!pemohon.get("bandar")<br/>$!pemohon.get("negeri")
		     	</td>
	      	</tr>  
	      	<tr>
		      	<td width="30%">No. Tel</td>
		      	<td width="1%">:</td>
		      	<td >$!pemohon.get("noTel")</td>
      		</tr>  
      		<tr>
		      	<td width="30%">No. Faks</td>
		      	<td width="1%">:</td>
		      	<td >$!pemohon.get("noFax")</td>
      		</tr>
      	</table>
      </fieldset>
     </td>
  </tr>
  <tr>
    <td width="50%" valign="top" ><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	      	<tr>
	          <td width="30%">Jenis Permohonan</td>
	          <td width="1%">:</td>
	          <td><font color="blue">$jenisPermohonan</font></td>
	        </tr>
	        <tr>
	          <td width="30%" >No. Rujukan Online</td>
	          <td width="1%">:</td>
	          <td><font color="blue">$noPermohonan</font></td>
	        </tr>
	        <tr>
	          <td width="30%">No. Fail </td>
	          <td width="1%">:</td>
	          <td><font color="blue">$noFail</font></td>
	        </tr>
	        <tr>
	          <td>Tarikh Permohonan </td>
	          <td>:</td>
	          <td><font color="blue">$tarikhTerima</font></td>
	        </tr>
	        <tr>
	          <td>Status Permohonan </td>
	          <td>:</td>
	          <td><font color="green">$status</font></td>
	        </tr>
	        <tr>
	          <td valign="top">Perkara</td>
	          <td valign="top">:</td>
	          <td>$perkara</td>
	        </tr>
      </table>
    </fieldset></td>
  </tr>
</table>