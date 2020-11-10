<div id="checking_progress"></div>
#if ($completed)
<script>
parent.document.getElementById("checking_progress").innerHTML="<div class=\"status\">TIADA MAKLUMAT BORANG O DIDAFTARKAN.</div>";
</script>
#end

#if ($clearForm == "yes")
	#set ($txdTkhBrgO = "")
    #set ($txtAlamatMahkamah1 = "")
    #set ($txtAlamatMahkamah2 = "")
    #set ($txtAlamatMahkamah3 = "")
    #set ($txtPoskodMahkamah = "")
    #set ($idPejabatMahkamah = "")
    #set ($txtNoTelMahkamah = "")
    #set ($txtNoFaxMahkamah = "")
    #set ($id_negeriMahkamah = "")
    #set ($txdTkhHantarBorangO = "")
    #set ($txtNamaPenghantarBorangO = "")
    #set ($txtNamaPenerimaBorangO = "")    
#end

#foreach ( $senarai in $Header )
	#set ($nama_kementerian=$senarai.nama_kementerian)
    #set ($no_fail=$senarai.no_fail)
    #set ($no_permohonan=$senarai.no_permohonan)
    #set ($tarikh_terima=$senarai.tarikh_terima)
    #set ($projek_negeri=$senarai.projek_negeri)
    #set ($nama_daerah=$senarai.nama_daerah)
    #set ($tujuan=$senarai.tujuan)
    #set ($tarikh_kehendaki=$senarai.tarikh_kehendaki)
    #set ($no_rujukan_surat=$senarai.no_rujukan_surat)
    #set ($no_rujukan_ptd=$senarai.no_rujukan_ptd)
    #set ($no_rujukan_ptg=$senarai.no_rujukan_ptg)
    #set ($no_rujukan_upt=$senarai.no_rujukan_upt)
    #set ($keterangan=$senarai.keterangan)
    #set ($nama_agensi=$senarai.nama_agensi)
    #set ($id_negeriMhn=$senarai.id_negeri)
    #set ($tarikh_permohonan=$senarai.tarikh_permohonan)    
#end 

#foreach ( $senarai in $getMaklumatBantahan )
	#set ($id_bantahan=$senarai.id_bantahan)
	#set ($txtNamaPembantah=$senarai.nama_kementerian)
    #set ($id_siasatan=$senarai.id_siasatan) 
    #set ($id_warta=$senarai.id_warta) 
    #set ($desc_status_bantahan_ap=$senarai.desc_status_bantahan_ap) 
#end

#if($flag == "semak")
	#foreach ( $senarai in $getDataBorangO )
		#set ($txdTkhBrgO=$senarai.tarikh_borango)
        #set ($txtAlamatMahkamah1=$senarai.alamat1)
        #set ($txtAlamatMahkamah2=$senarai.alamat2)
        #set ($txtAlamatMahkamah3=$senarai.alamat3)
        #set ($txtPoskodMahkamah=$senarai.poskod)
        #set ($txtNoTelMahkamah=$senarai.no_tel)
        #set ($txtNoFaxMahkamah=$senarai.no_fax)
        #set ($idPejabatMahkamah=$senarai.id_mahkamah)
        #set ($txdTkhHantarBorangO=$senarai.tarikh_hantar_borango)
        #set ($txtNamaPenghantarBorangO=$senarai.nama_penghantar_borango)
        #set ($txtNamaPenerimaBorangO=$senarai.nama_penerima_borango)        
	#end
#end

<fieldset>
	<legend>PERMOHONAN BANTAHAN KE MAHKAMAH</legend>

	<!-- Header -->
	#parse("app/ppt/frmPPTHeader.jsp")
	
     </br>


<!------------------------------------------ TAB BORANG 0 -------------------------------------------->

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
     <li class="TabbedPanelsTab" tabindex="0" onclick="setSelectedTab(0);bantahan()">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="1" onclick="setSelectedTab(1);deposit()">Deposit</li>
     #if($!idBorangO_check != "")
    <li class="TabbedPanelsTab" tabindex="2" onclick="setSelectedTab(2);borangO()">Borang O</li>
    <li class="TabbedPanelsTab" tabindex="3" onclick="setSelectedTab(3);lanjutanTempoh()">Lanjutan Tempoh</li>
    <li class="TabbedPanelsTab" tabindex="4" onclick="setSelectedTab(4);susulanBantahan()">Perintah</li>
    <li class="TabbedPanelsTab" tabindex="5" onclick="setSelectedTab(5);pemulanganDeposit()">Pemulangan Deposit</li>
    <li class="TabbedPanelsTab" tabindex="6" onclick="setSelectedTab(6);tarikBalikBantahan()">Tarik Balik Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="7" onclick="setSelectedTab(7);batalBantahan()">Pembatalan Bantahan oleh MT</li>
    #end
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    
    
    <div class="TabbedPanelsContent">
<fieldset>
<legend>Maklumat Borang O</legend>
	<table width="100%" border="0">
    	<tr>
          <td width="1%"> #if ($mode=="")<font color="red">*</font>#end </td>
    	  <td>
          
          Tarikh Borang O
          
          </td>
    	  <td>:</td>
    	  <td width="77%">
          #if ($mode=="disabled")
          <input type="text" name="txdTkhBrgOx" id="txdTkhBrgOx" value="$!txdTkhBrgO" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" class="disabled" readonly />
           #else
          <input type="text" name="txdTkhBrgO" id="txdTkhBrgO" value="$!txdTkhBrgO" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="1" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhBrgO',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>           
           #end 
            </td>
   	    </tr>
    	<tr>
            <td width="1%"></td>
            <td width="22%">Nama Pihak Membantah</td>
          	<td width="1%">:</td>
          	<td><input type="text" name="txtNamaPembantah" id="txtNamaPembantah" value="$!txtNamaPembantah" size="60" class="disabled" readonly /></td>
   	    </tr>
        
        #if($id_negeriMhn == "16")  
    	<tr>
           <td width="1%"></td>
    	  <td>Negeri</td>
    	  <td>:</td>
    	  <td>$selectNegeriMahkamah<input type="hidden" name="id_negeriMahkamah" id="id_negeriMahkamah" value="$!id_negeriMahkamah" /></td>
  	    </tr>
        #end        
          
    	<tr>
         <td width="1%"></td>
    	  <td>Mahkamah Tinggi</td>
    	  <td>:</td>
    	  <td>$selectMahkamahTinggi<input type="hidden" name="idPejabatMahkamah" id="idPejabatMahkamah" value="$!idPejabatMahkamah" /></td>
  	  </tr>
    	<tr>
         <td width="1%"></td>
    	  <td>Alamat</td>
    	  <td>:</td>
    	  <td><input type="text" name="txtAlamatMahkamah1" id="txtAlamatMahkamah1" value="$!txtAlamatMahkamah1" size="60" class="disabled" readonly /></td>
  	  </tr>
    	<tr>
         <td width="1%"></td>
    	  <td>&nbsp;</td>
    	  <td>&nbsp;</td>
    	  <td><input type="text" name="txtAlamatMahkamah2" id="txtAlamatMahkamah2" value="$!txtAlamatMahkamah2" size="60" class="disabled" readonly  /></td>
  	  </tr>
    	<tr>
         <td width="1%"></td>
    	  <td>&nbsp;</td>
    	  <td>&nbsp;</td>
    	  <td><input type="text" name="txtAlamatMahkamah3" id="txtAlamatMahkamah3" value="$!txtAlamatMahkamah3" size="60" class="disabled" readonly /></td>
  	  </tr>
    	<tr>
         <td width="1%"></td>
    	  <td>Poskod</td>
    	  <td>:</td>
    	  <td><input type="text" name="txtPoskodMahkamah" id="txtPoskodMahkamah" value="$!txtPoskodMahkamah" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" class="disabled" readonly /></td>
  	  </tr>
      
      #if($id_negeriMhn != "16")
      <tr>
       <td width="1%"></td>
    	  <td>Negeri</td>
    	  <td>:</td>
    	  <td>$selectNegeriMahkamah</td>
  	  </tr>      
      #end
    	
    	<tr>
         <td width="1%"></td>
            <td width="22%">Bandar</td>
          	<td width="1%">:</td>
          	<td>$selectBandarMahkamah</td>
       	</tr> 
    	<tr>
         <td width="1%"></td>
            <td width="22%">No Tel</td>
          	<td width="1%">:</td>
          	<td><input type="text" name="txtNoTelMahkamah" id="txtNoTelMahkamah" value="$!txtNoTelMahkamah" maxlength="14" size="14" class="disabled" readonly /></td>
       	</tr>
    	<tr>
         <td width="1%"></td>
            <td width="22%">No Fax</td>
          	<td width="1%">:</td>
          	<td><input type="text" name="txtNoFaxMahkamah" id="txtNoFaxMahkamah" value="$!txtNoFaxMahkamah" maxlength="14" size="14" class="disabled" readonly /></td>
       	</tr>                             
    </table>
</fieldset>

<br/>

<fieldset>
<legend>Maklumat Penghantar Borang O</legend>
	<table width="100%" border="0">
    	<tr>
            <td width="1%"></td>
        	<td width="22%">Tarikh Hantar</td>
            <td width="1%">:</td>
            <td width="77%">
		
        	#if ($mode=="disabled")            
            <input type="text" name="txdTkhHantarBorangO" id="txdTkhHantarBorangO" value="$!txdTkhHantarBorangO" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" class="disabled" readonly />
            #else
            <input type="text" name="txdTkhHantarBorangO" id="txdTkhHantarBorangO" value="$!txdTkhHantarBorangO" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="3" />
          <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhHantarBorangO',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
          	#end            
            </td>
        </tr>  
        <tr>
            <td width="1%"></td>
        	<td>Nama Penghantar</td>
            <td>:</td>
            <td>
            #if ($mode=="disabled")
            <input type="text" name="txtNamaPenghantarBorangO" id="txtNamaPenghantarBorangO" value="$!txtNamaPenghantarBorangO" size="35" class="disabled" readonly />
            #else
            <input type="text" name="txtNamaPenghantarBorangO" id="txtNamaPenghantarBorangO" value="$!txtNamaPenghantarBorangO" size="35" tabindex="4" />
            #end            
            </td>
        </tr>  
        <tr>
            <td width="1%"></td>
        	<td>Nama Penerima</td>
            <td>:</td>
            <td>
            #if ($mode=="disabled")
            <input type="text" name="txtNamaPenerimaBorangO" id="txtNamaPenerimaBorangO" value="$!txtNamaPenerimaBorangO" size="35" class="disabled" readonly />
            #else
            <input type="text" name="txtNamaPenerimaBorangO" id="txtNamaPenerimaBorangO" value="$!txtNamaPenerimaBorangO" size="35" tabindex="4" />
            #end          
            </td>
        </tr>          
    </table>
</fieldset>

<!----------------------------------------- SENARAI DOKUMEN YANG DISERTAKAN --------------------------------------------->
<br/>

<fieldset id="senarai_dokumen" >
<legend>Senarai Dokumen</legend>    
    <input name="cmdTambahDokumen" type="button" value="Tambah" onClick="tambahDokumen()" title="Sila klik untuk tambah dokumen" >    
    #if($listDokumen_size > 0)
     <input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumenMaster('$!readmode')" title="Sila tick untuk hapus dokumen" >
    #end
    <table width="100%">
	  	<tr class="table_header">
	    <td width="3%">Bil</td>
	    <td width="30%">Nama Dokumen</td>
	    <td width="30%">Keterangan</td>
	    <td width="30%">Dokumen Sokongan (Papar)</td>
	     #if($listDokumen_size > 0)
	      <td width="7%">
	     
	      <div align="center">
	      <input type="checkbox" name="all1" id="all1" onclick="doCheckAll1()" title="Semak untuk pilih semua" />
	      </div>
	      
	      </td>
	      #end
	  	</tr>
 #if($listDokumen_size > 0)
 	#foreach($list1 in $listDokumen)                   
    	#set( $i = $velocityCount )
      	#if ( ($i % 2) != 1 )
      		#set( $row = "row2" )
    	#else
        	#set( $row = "row1" )
      	#end
		<tr>  
		    <td class="$row" >$list1.BIL</td>
		    <td class="$row" ><a href="javascript:view_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.TAJUK</font></a></td>
		    <td class="$row" >$list1.KETERANGAN</td>
		    <td class="$row"><a href="javascript:paparLampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.NAMA_FAIL</font></a></td>   
		    <td class="$row" ><div align="center">
		       <input type="checkbox" name="ids1" id="ids1" onclick="doUpdateCheckAll1()" value="$list1.ID_DOKUMEN" >
		     </div></td>
		</tr>
  	#end
  
  #else
	  	<tr>  
	    	<td colspan="5">Tiada Rekod</td>    
	  	</tr>
  #end
	</table>
  
</fieldset> 

<!------------------------------------ END MAKLUMAT BANTAHAN ------------------------------------------> 
        <div align="center">  
      	#if ($button=="view")       
          	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniBorangO()" /> 
      		<input type="button" name="cmdintegrasimt" id="cmdintegrasimt" value="Integrasi MT" onclick="javascript:hantarBantahan()" /> 
          	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />
     	#end
          
          #if ($button=="edit")
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanBorangO()" /> 
          <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="" /> 
          #end
          
          <input type="button" name="cmdkembali" id="cmdkembali" value="Kembali" onclick="javascript:kembaliList()" />
          
               
        </div>     
    </div>
    
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    
  </div>
</div>
<input type=hidden name=selectedtab />
<!--------------------------------------- END TAB BANTAHAN ------------------------------------------->
</fieldset>

<!------------------------------------------ OUTPUT LAPORAN/SURAT ----------------------------------------------->
<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

      <tr>
        <td>
        <a href="#" class="style2" onClick="javascript:cetakCoveringBantahan('$id_hakmilikpb')"><font color="blue"> Covering Bantahan </font></a>
        </td>
      </tr>  
    
      <tr>
        <td>
        <a href="#" class="style2" onClick="javascript:cetakLampiranBorangO('$id_fail')"><font color="blue"> Lampiran Borang O - Senarai Lampiran </font></a>
        </td>
      </tr>     
    
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakBorangO_AP('$id_fail','$id_bantahan','$id_siasatan','$id_warta','$id_permohonan')"><font color="blue"> Borang O - Rujukan Kepada Mahkamah </font></a></td>
      </tr> 
      
      <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakSuratIringanBorangO_AP('$id_fail','$id_bantahan','$id_hakmilik','$id_permohonan')"><font color="blue"> Surat Iringan Borang O - Rujukan Kepada Mahkamah </font></a>
        </td>
      </tr>
      
      <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakBorangE('$id_hakmilik','$!tarikh_cetak')"><font color="blue"> Borang E - Pengambilan yang Dicadangkan Pemberitahu Siasatan </font></a>
        </td>
      </tr>  
      
      <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakBorangG('$id_fail','$id_hakmilik')"><font color="blue"> Borang G - Pemberian Bertulis Pampasan </font></a>
        </td>
      </tr> 
        
      <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakBorangH('$id_fail','$id_hakmilik')"><font color="blue"> Borang H - Pemberitahu Pemberian dan Tawaran Pampasan </font></a>
        </td>
      </tr>
      
      <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakBorangK('$id_fail','$id_hakmilik')"><font color="blue"> Borang K - Pemberitahu Bahawa Tanah Telah Diambil </font></a>
        </td>
      </tr>
      
      <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakNotaSiasatan('$!id_siasatan','$!id_permohonan')"><font color="blue"> Nota Perbicaraan </font></a>
        </td>
      </tr> 
                                                                   
    </table>
</fieldset>
<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input type="hidden" name="id_bantahan" id="id_bantahan" value="$id_bantahan" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$id_hakmilikpb" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />
<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$id_pihakberkepentingan" />
<input type="hidden" name="status_bantahan" id="status_bantahan" value="$status_bantahan" />
<input type="hidden" name="id_siasatan" id="id_siasatan" value="$id_siasatan" />
<input type="hidden" name="id_warta" id="id_warta" value="$id_warta" />
<input type="hidden" name="nama_pengarah" id="nama_pengarah" value="$nama_pengarah" />
<input type="hidden" name="nama_skrin" id="nama_skrin" value="pptbantahan"  />

<script type="text/javascript">
	
	// Mula integrasi MT
	/**
	Fungsi hantar maklumat bantahan ke MT
	*/
	function hantarBantahan_bak() {
		var idBantahan = "&idbantahan="+$jquery('#id_bantahan').val();
		var idFail = "&idfail=$!id_fail";
		var idHarta  = "&idharta="+$jquery('#id_hakmilik').val();
		var idPermohonan  = "&idpermohonan="+$jquery('#id_permohonan').val();
		var idSiasatan  = "&idsiasatan="+$jquery('#id_siasatan').val();
		var idWarta  = "&idwarta=$!idWarta";
		var param = idHarta+idPermohonan+idSiasatan+idWarta+idFail+idBantahan;
		
		document.${formName}.command.value = "bantahanap";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian"+param;
		document.${formName}.submit();	
		
	}
	function hantarBantahan() {	
		var idBantahan = "&idbantahan="+$jquery('#id_bantahan').val();
		var idFail = "&idfail=$!id_fail";
		var idHarta  = "&idharta="+$jquery('#id_hakmilik').val();
		var idPermohonan  = "&idpermohonan="+$jquery('#id_permohonan').val();
		var idSiasatan  = "&idsiasatan="+$jquery('#id_siasatan').val();
		var idWarta  = "&idwarta=$!idWarta";
		var mahkamah  = "&idmt=$!idMT";
		var param = idHarta+idPermohonan+idSiasatan+idWarta+idFail+idBantahan+mahkamah;
		//alert(param);
		var url = "../x/${securityToken}/ekptg.view.ppt.bantahan.IntegrasiMT?"+param+"&command=bantahanap&frmFrom=FrmBantahanSenaraiCarian";
		var hWnd = window.open(url,'Cetak','width=625,height=575, resizable=no,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();	
	
	} 
	function viewLampiran(id_dokumen) {
	    var id_bantahan = document.${formName}.id_bantahan.value ;
		var id_permohonan = document.${formName}.id_permohonan.value ;		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=view_Dokumen_Details&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
		document.${formName}.submit();
	}
	
	function paparLampiran(id_dokumen) {
	    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
	    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	// Tamat integrasi MT

function open_header(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function cetakCoveringBantahan(id_hakmilikpb) {
	var url = "../servlet/ekptg.report.ppt.CoveringBantahan?idHakmilikpb="+id_hakmilikpb;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function cetakLampiranBorangO(id_fail,id_bantahan,id_siasatan,id_warta) {
	var url = "../servlet/ekptg.report.ppt.LampiranBorangO?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function cetakBorangO_AP(id_fail,id_bantahan,id_siasatan,id_warta,id_permohonan) {

	/*var url = "../servlet/ekptg.report.ppt.BorangO_AP?idFail="+id_fail+"&id_bantahan="+id_bantahan+"&idSiasatan="+id_siasatan+"&idWarta="+id_warta;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();*/
    
    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?idFail="+id_fail+"&id_bantahan="+id_bantahan+"&idSiasatan="+id_siasatan+"&idWarta="+id_warta+"&id_permohonan="+id_permohonan+"&report=BorangO_AP&selectNoFail=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function cetakSuratIringanBorangO_AP(id_fail,id_bantahan,id_hakmilik,id_permohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_fail="+id_fail+"&id_bantahan="+id_bantahan+"&id_hakmilik="+id_hakmilik+"&id_permohonan="+id_permohonan+"&report=SuratIringanBorangO_AP&flagReport=S";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();


/*	var url = "../servlet/ekptg.report.ppt.SuratIringanBorangO_AP?idFail="+id_fail+"&id_bantahan="+id_bantahan+"&id_hakmilik="+id_hakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	*/
	
}

function cetakBorangE(id_hakmilik) {
    var url = "../servlet/ekptg.report.ppt.BorangE?id_hakmilik="+id_hakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangG(id_fail,id_hakmilik) {
    var url = "../servlet/ekptg.report.ppt.BorangG?id_Fail="+id_fail+"&id_hakmilik="+id_hakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}	

function cetakBorangH(id_fail,id_hakmilik) {
    var url = "../servlet/ekptg.report.ppt.BorangH?id_Fail="+id_fail+"&id_hakmilik="+id_hakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangK(id_fail,id_hakmilik) {
    var url = "../servlet/ekptg.report.ppt.BorangK?id_Fail="+id_fail+"&id_hakmilik="+id_hakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakNotaSiasatan(id_siasatan,id_permohonan) {
    var url = "../servlet/ekptg.report.ppt.NotaSiasatanSek8?id_siasatan="+id_siasatan+"&id_permohonan="+id_permohonan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function setSelectedTab(tabid) {
	document.${formName}.selectedtab.value = tabid;
}

function bantahan(){
	document.${formName}.command.value = "bantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function deposit(){
	document.${formName}.command.value = "deposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function borangO(){
	document.${formName}.command.value = "borangO";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function lanjutanTempoh(){
	document.${formName}.command.value = "lanjutanTempoh";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function susulanBantahan(){
	document.${formName}.command.value = "susulanBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function pemulanganDeposit(){
	document.${formName}.command.value = "pemulanganDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function tarikBalikBantahan(){
	document.${formName}.command.value = "tarikBalikBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function batalBantahan(){
	document.${formName}.command.value = "batalBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function doChangeAlamatMahkamah() {
	document.${formName}.command.value = "doChangeAlamatMahkamah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();		
}
function doChangeNegeriMahkamah() {
	document.${formName}.command.value = "doChangeNegeriMahkamah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();		
}
function simpanBorangO() {
	if(document.${formName}.txdTkhBrgO.value == ""){
		alert("Sila masukkan \"Tarikh Borang O\" terlebih dahulu.");
  		document.${formName}.txdTkhBrgO.focus(); 
		return;		
	}	
	if(document.${formName}.socMahkamahTinggi.value == ""){
		alert("Sila pilih \"Mahkamah Tinggi\" terlebih dahulu.");
  		document.${formName}.socMahkamahTinggi.focus(); 
		return;		
	}				
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "simpanBorangO";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
		document.${formName}.submit();	
	}
}
function kemaskiniBorangO() {
	document.${formName}.command.value = "kemaskiniBorangO";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();	
}

function kembaliList(){
	document.${formName}.command.value = "kembaliList";	
	document.${formName}.submit();
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}


//:::UPLOAD DOCUMENT FUNCTION
function tambahDokumen(id_permohonan,id_bantahan,id_hakmilik) {
	var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;
	var id_hakmilik = document.${formName}.id_hakmilik.value ;	
	dokumen ="&nama_skrin=pptbantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=tambah_dokumen&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&id_hakmilik="+id_hakmilik+"&location=maklumat_dokumen&point=txtnamadokumen"+dokumen;	
	document.${formName}.submit();
	
}
	
function hapusDokumenMaster(r){
	input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			var id_bantahan = document.${formName}.id_bantahan.value ;
			var id_permohonan = document.${formName}.id_permohonan.value ;		
			document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=hapusDokumenborango&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&readmode="+r;	
			document.${formName}.submit();
		}
		
	}
	
function view_Lampiran(id_dokumen) {
    var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=view_Dokumen_Details&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}


<!--UTK DEFAULTKAN TAB KEPADA TAB BANTAHAN
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedtab});
//-->
</script>
