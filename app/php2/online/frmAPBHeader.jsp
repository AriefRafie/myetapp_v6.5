<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr> #foreach($beanHeader in $BeanHeader)
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
          <td width="30%">No Permohonan </td>
          <td width="1%">:</td>
          <td ><font color="blue">$noPermohonan</font></td>
        </tr>
        #if($status == 'LULUS')
         <tr>
          <td width="30%">No Fail Lama </td>
          <td width="1%">:</td>
          <td ><font color="blue">$noFailLama</font></td>
        </tr>#end
        <tr>
          <td width="30%">No Fail </td>
          <td width="1%">:</td>
          <td ><font color="blue">$noFail</font></td>
        </tr>
        <tr>
          <td width="30%">Tarikh Permohonan </td>
          <td width="1%">:</td>
          <td><font color="blue">$tarikhTerima</font></td>
        </tr>
        <tr>
        <td width="30%">Tujuan </td>
        <td width="1%">:</td>
        <td><font color="blue">$tujuanPengambilan</font></td>
        </tr>
        <tr>
          <td width="30%">Status Permohonan </td>
          <td width="1%">:</td>
          <td><font color="green">$status</font></td>
        </tr>
         <tr>
          <td width="30%">No.Lesen </td>
          <td width="1%">:</td>
          <td><font color="blue">$noLesen</font></td>
        </tr>
      </table>
      </fieldset></td>
      
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
      <td >$!pemohon.get("alamat1") $!pemohon.get("alamat2")<br>$!pemohon.get("alamat3")<br/>
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
      <tr>
      <td width="30%">Emel</td>
      <td width="1%">:</td>
      <td >$!pemohon.get("emel")</td>
      </tr>
      </table>
      </fieldset>
      </td>
  </tr>
</table>
