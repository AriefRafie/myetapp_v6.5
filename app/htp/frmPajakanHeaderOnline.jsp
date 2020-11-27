<table width="100%" border="0" cellspacing="2" cellpadding="2">
    #foreach($beanHeader in $BeanHeader)
    #set($negeri = $beanHeader.negeri)
    #set($kementerian = $beanHeader.kementerian)
    #set($agensi = $beanHeader.agensi)
  	#set($idFail = $beanHeader.idFail) 	
    #set($idNegeriTanah = $beanHeader.idNegeriTanah)
    #set($idKementerian = $beanHeader.idKementerian)
    #set($idPermohonan = $beanHeader.idPermohonan)
    #set($noPermohonan = $beanHeader.noPermohonan)
    #set($status = $beanHeader.status)    
    #set($idHakmilik = $beanHeader.idHakmilik)
    #set($noFail = $beanHeader.noFail)
    #set($statusTanah = $beanHeader.statusTanah)
    #set($tajuk = $beanHeader.tajuk)    
    #end
    
    <tr>
    <td>
    <fieldset>
    <legend><strong>MAKLUMAT PEMOHON</strong></legend>   
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td width="50%" valign="top">
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
         <tr>
    		<td width="1%"></td>
            <td width="28%">Kategori Pemohon</td>
            <td width="1%">:</td>
            <td width="70%">$!namaPemohon.get("kategoriPemohon")</td>
		 </tr>
         <tr>
    		<td width="1%"></td>
            <td width="28%">Nama</td>
            <td width="1%">:</td>
            <td width="70%">$!namaPemohon.get("namaPemohon")</td>
		 </tr>
         <tr>
    		<td width="1%"></td>
            <td width="28%">MyID/MyCoID</td>
            <td width="1%">:</td>
            <td width="70%">$!namaPemohon.get("noPengenalan")</td>
		 </tr>
         <tr>
    		<td width="1%"></td>
            <td width="28%" valign="top">Alamat</td>
            <td width="1%" valign="top">:</td>
            <td width="70%">$!namaPemohon.get("alamat1")<br/>$!namaPemohon.get("alamat2")<br/>$!namaPemohon.get("alamat3")</td>
		 </tr>          
         <tr>
    		<td width="1%"></td>
            <td width="28%">Poskod</td>
            <td width="1%">:</td>
            <td width="70%">$!namaPemohon.get("poskod")</td>
		 </tr>
        </table></td>
        <td width="50%" valign="top">
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
         <tr>
    		<td width="1%"></td>
            <td width="28%">Negeri</td>
            <td width="1%">:</td>
            <td width="70%">$!namaPemohon.get("negeri")</td>
		 </tr>
          <tr>
    		<td width="1%"></td>
            <td width="28%">Bandar</td>
            <td width="1%">:</td>
            <td width="70%">$!namaPemohon.get("bandar")</td>
		 </tr>
          <tr>
    		<td width="1%"></td>
            <td width="28%">No. Telefon</td>
            <td width="1%">:</td>
            <td width="70%">$!namaPemohon.get("noTel")</td>
		 </tr>
          <tr>
    		<td width="1%"></td>
            <td width="28%">No. Fax</td>
            <td width="1%">:</td>
            <td width="70%">$!namaPemohon.get("noFax")</td>
		 </tr>
		 <tr>
    		<td width="1%"></td>
            <td width="28%">Emel</td>
            <td width="1%">:</td>
            <td width="70%">$!namaPemohon.get("emel")</td>
		 </tr>
          
        </table>
        </td>
      </tr>
    </table>
    </fieldset>
    </td>
  </tr>
        
	<tr>
	 <td>
	 <fieldset>
    <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>   
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td width="50%" valign="top">
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
          	<td width="1%"></td>
            <td width="28%">NEGERI</td>
            <td width="1%">:</td>
            <td width="70%"><font color="blue">$negeri</font></td>
          </tr>
          
		  <tr>
		  <td width="1%"></td>
            <td width="28%">KEMENTERIAN</td>
            <td width="1%">:</td>
            <td width="70%"><font color="blue">$kementerian</font></td>
          </tr>
          <tr>
          	<td width="1%"></td>
            <td width="28%">AGENSI</td>
            <td width="1%">:</td>
             <td width="70%"><font color="blue">$agensi</font></td>
          </tr>
          <tr>
          	<td width="1%"></td>
            <td width="28%">STATUS TANAH</td>
            <td width="1%">:</td>
             <td width="70%"><font color="blue">$statusTanah</font></td>
          </tr> 
		 <tr>
		 	<td width="1%"></td>
            <td width="28%" valign="top">TUJUAN PAJAKAN</td>
            <td width="1%">:</td>
            <td width="70%"><font color="blue">$tajuk</font></td>
          </tr>
        </table></td>
		<td width="50%" valign="top">
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
         <tr>
    		<td width="1%"></td>
            <td width="28%">NO. FAIL ONLINE</td>
            <td width="1%">:</td>
            <td width="70%"><font color="blue">$noFailOnline</font></td>
		 </tr>
          <tr>
    		<td width="1%"></td>
            <td width="28%">TARIKH SURAT PEMOHON</td>
            <td width="1%">:</td>
            <td width="70%"><fonT color="blue">$tarikhSuratPemohon</font></td>
		 </tr>
          <tr>
    		<td width="1%"></td>
            <td width="28%">JENIS FAIL</td>
            <td width="1%">:</td>
            <td width="70%"><font color="blue">$jenisFail</font></td>
		 </tr>
          <tr>
    		<td width="1%"></td>
            <td width="28%">STATUS</td>
            <td width="1%">:</td>
            <td width="70%"><font color="blue">$statusPermohonan</font></td>
		 </tr>
          
        </table>
        </td>
		</tr>
		</table>
		</fieldset>
		</td>
		</tr>
	</table>
