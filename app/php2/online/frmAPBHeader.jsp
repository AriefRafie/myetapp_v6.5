<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr> 
		#foreach($beanHeader in $BeanHeader)
	    #set($idFail = $beanHeader.idFail)
	    #set($subUrusan = $beanHeader.subUrusan)
	    #set($idPermohonan = $beanHeader.idPermohonan)
	    #set($noPermohonan = $beanHeader.noPermohonan)
	    #set($tarikhTerima = $beanHeader.tarikhTerima)
	    #set($idStatus = $beanHeader.idStatus) 
	    #set($status = $beanHeader.status)    
	    #set($noFail = $beanHeader.noFail)
	    #set($noFailLama = $beanHeader.noFailLama)
	    #set($tujuanPengambilan = $beanHeader.tujuanPengambilan)
	    #set($noLesen = $beanHeader.noLesen)
	    #end
        
    	<td width="50%" valign="top" ><fieldset>
      		<legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      		<table width="100%" border="0" cellspacing="2" cellpadding="2">
        		<tr>
			    	<td width="30%" align="right">NO. RUJUKAN <i>ONLINE</i> </td>
			        <td width="1%">:</td>
			        <td><font color="blue">$noPermohonan</font></td>
			    </tr>
			    ##if($status == 'LULUS')
			    ##<tr>
			    ##	<td width="30%" align="right">NO. FAIL SEBELUM</td>
			    ##    <td width="1%">:</td>
			    ##    <td ><font color="blue">$noFailLama</font></td>
			 	##</tr>
			    ##end
			    <tr>
			    	<td width="30%" align="right">NO. FAIL </td>
			        <td width="1%">:</td>
			        <td ><font color="blue">$noFail</font></td>
			    </tr>
			    <tr>
			        <td width="30%" align="right">TARIKH PERMOHONAN </td>
			        <td width="1%">:</td>
			        <td><font color="blue">$tarikhTerima</font></td>
			    </tr>   
	  		    <tr>
			        <td width="30%" align="right">TUJUAN</td>
		    	  	<td width="1%">:</td>
			        <td><font color="blue">$tujuanPengambilan.toUpperCase()</font></td>
			    </tr>
			    <tr>
			        <td width="30%" align="right">STATUS PERMOHONAN </td>
			        <td width="1%">:</td>
			        <td><font color="green">$status</font></td>
			    </tr>
			    <tr>
			        <td width="30%" align="right">NO. LESEN </td>
			        <td width="1%">:</td>
			        <td><font color="blue">$noLesen</font></td>
			    </tr>
      		</table>
      	</fieldset></td>
      
    	<td><fieldset>
	  		<legend><strong>MAKLUMAT PEMOHON</strong></legend>
      		<table width="100%" border="0" cellspacing="2" cellpadding="2">
      			<tr>
			      	<td width="30%" align="right">NAMA PEMOHON</td>
			      	<td width="1%">:</td>
			      	<td>$!pemohon.get("namaPemohon")</td>
			    </tr>
      			<tr>
  					<td width="30%" align="right" valign="top">
				  		#if($!pemohon.get("kategoriPemohon") == "INDIVIDU") 
				  			NO. KAD PENGENALAN/MyID 
				  		#end
				  		#if($!pemohon.get("kategoriPemohon") != "INDIVIDU")
				  			NO. PENDAFTARAN SYARIKAT/MyCOID
				  		#end
  					</td>
			  		<td width="1%" valign="top">:</td>
			  		<td valign="top">$!pemohon.get("noPengenalan")</td>
  				</tr>
      			<tr>
			      	<td width="30%" align="right" valign="top">ALAMAT</td>
			      	<td width="1%" valign="top">:</td>
			      	<td>$!pemohon.get("alamat1") $!pemohon.get("alamat2")<br>$!pemohon.get("alamat3")<br/>
      					$!pemohon.get("poskod") $!pemohon.get("bandar")<br/>$!pemohon.get("negeri")
     				</td>
      			</tr>  
		      	<tr>
		      		<td width="30%" align="right">NO. TEL</td>
		      		<td width="1%">:</td>
		      		<td>$!pemohon.get("noTel")</td>
		      	</tr>  
		      	<tr>
		      		<td width="30%" align="right">NO. FAKS</td>
		      		<td width="1%">:</td>
		      		<td >$!pemohon.get("noFax")</td>
		      	</tr>
		      	<tr>
			      	<td width="30%" align="right">EMEL</td>
			      	<td width="1%">:</td>
			      	<td >$!pemohon.get("emel")</td>  
			    </tr>
      		</table>
      	</fieldset></td>
	</tr>
</table>
