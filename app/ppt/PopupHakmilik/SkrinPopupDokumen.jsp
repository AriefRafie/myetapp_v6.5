

#if($flag_skrin == "salin_hakmilik_sek8_KJP" )
<link rel="stylesheet" type="text/css" href="../../css/eTapp_KJP.css">
#else
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPT.css">
#end







<div style="display:none">
id_mohon_selected : <input type="text" id="id_mohon_selected" name="id_mohon_selected" value="$id_mohon_selected">
<br />
id_permohonan : <input type="text" id="id_permohonan" name="id_permohonan" value="$id_permohonan">

<input type="text" name="form_token_pop" value='$!{session.getAttribute("form_token_pop")}'>

</div>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    
    	<fieldset id="top">
	<legend><strong>MAKLUMAT DOKUMEN $tajuk_skrin_doc</strong></legend>
 <span style="display:none"> #parse("app/ppt/LinkMacGDI.jsp")  </span>

	
    	 <table width="100%" cellpadding="0" border="0">  
        	
        	<tr>
        	  <td width="1%" valign="top"><font color="red">*</font></td>
              <td width="28%" valign="top">
              #if($flag_skrin=="LaporanTanahTerperinciImej")
              KETERANGAN GAMBAR TAPAK
              #else              
              NAMA DOKUMEN
              #end
              
              </td>
              <td width="1%" valign="top">:</td>
              <td width="68%" valign="top">
              
              #if($flag_skrin=="LaporanTanahTerperinciImej")
              <textarea name="NAMA_DOKUMEN_ADD" id="NAMA_DOKUMEN_ADD"   cols="60" rows="3"></textarea>
              #else
              <input type="text" name="NAMA_DOKUMEN_ADD" value="" size="80" id="NAMA_DOKUMEN_ADD"  />
              #end
              

              </td>
          	</tr>
            
            <tr style="display:none;">
            	<td>&nbsp;</td>
            	<td valign="top">KETERANGAN</td>
            	<td valign="top">:</td>
                <td><textarea name="DETAIL_DOKUMEN_ADD" id="DETAIL_DOKUMEN_ADD"   cols="60" rows="3"></textarea></td>
            </tr>
            
            <tr>
            	<td valign="top"><font color="red">*</font></td>
            	<td valign="top">DOKUMEN</td>
            	<td valign="top">:</td>
  				<td><input id="fileupload" name="fileupload" type=file size=40 /></td><br/>
  			</tr>
            <tr >
            <td valign="top"></td>
            	<td valign="top"></td>
            	<td valign="top"></td>
        	<td>
                <input type="button" value="Simpan" onClick="uploadFail('$!id_permohonan','$!id_pembatalan','$!id_penarikanbalik','$!id_bantahan','$!id_permintaanukur','$!id_award','$!id_hakmilik','$flag_skrin','$id_tanah','$id_notisawam','$id_buktipenyampaian','$id_borangh')">
                #if($flag_skrin=="LaporanTanahTerperinciImej")
               <font color="blue">**Sila Pastikan format fail yang dimuatnaik adalah imej [".jpg", ".jpeg", ".bmp", ".gif", ".png" ] </font>
               #end
                 
            </td>
        </tr>
            
        </table>
        
        <table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian2</td>
        	</tr>
        </table>
        
    </fieldset>
    
    

</td>
  </tr>
</table>





<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI DOKUMEN $tajuk_skrin_doc</b></legend> 
      
      
      <table width="100%" border="0" cellspacing="2" cellpadding="2">

<tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" align="left" valign="top">
           #if($flag_skrin=="LaporanTanahTerperinciImej")
              KETERANGAN GAMBAR TAPAK
              #else              
              NAMA DOKUMEN
              #end
              
          
          </td>
          <td width="1%" valign="top">:</td>
          <td width="68%" valign="top">
          
          <input name="TAJUK" type="text" id="TAJUK"  value="$TAJUK" size="100" maxlength="500"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />

          
           </td>
        </tr>
        
<tr style="display:none;" >
          <td>&nbsp;</td>
          <td align="left" valign="top">KETERANGAN DOKUMEN</td>
          <td valign="top">:</td>
          <td valign="top">
          
          <input name="NAMA_DOKUMEN" type="text" id="NAMA_DOKUMEN" value="$NAMA_DOKUMEN" size="100" maxlength="1000"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />

          
           </td>
        </tr>        
        

<tr>
  <td  ></td>
  <td align="left" valign="top" ></td>
  <td valign="top" ></td>
  <td valign="top" >
   
   
    <input type="button" id="cariAduan" name="cariAduan" value="CARI" onclick="carian()"/>
    <input type="button" id="kosongHariHakmilik" name="kosongHariHakmilik" value="RESET" onclick="kosongkan()"/>
    
    </td>
  
  
</tr>
</table>   
     
         
		#parse("app/utils/record_pagingPopup.jsp")
     
        
        
        #set ($count = 0) 
        
        #if($SenaraiFail.size()>0)
            #if($flag_skrin=="LaporanTanahTerperinciImej")
            <input type="button" name="cmdSimpan" value="Simpan Keterangan Gambar Tapak" onClick="javascript:simpanKeterangan('$!id_permohonan','$!id_pembatalan','$!id_penarikanbalik','$!id_bantahan','$!id_permintaanukur','$!id_award','$!id_hakmilik','$flag_skrin','$id_tanah','$id_notisawam','$id_buktipenyampaian','$id_borangh')">
            #end
        #end
        
        <table align="center" width="100%" cellspacing="1" cellpadding="0">
       
        
        <tr class="table_header">
          <td scope="row"  valign="top" align="center"><strong><font color="white">BIL.</font></strong></td>
          <td   valign="top"><strong><font color="white">
          
          
           	  #if($flag_skrin=="LaporanTanahTerperinciImej")
              GAMBAR TAPAK
              #else              
              FAIL
              #end
              
          
          
          
          </font></strong></td> 
          <td   valign="top"><strong><font color="white">
          
           #if($flag_skrin=="LaporanTanahTerperinciImej")
             KETERANGAN GAMBAR TAPAK
              #else              
              NAMA DOKUMEN
              #end
              
          
          </font></strong></td>               
          <td valign="top" style="display:NONE;"><strong><font color="white">KETERANGAN</font></strong></td>    
          
          <td  align="center" valign="top"><strong><font color="white">HAPUS</font></strong></td> 
          
        </tr>
        #if($SenaraiFail.size()>0)
        #foreach ( $fail in $SenaraiFail )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2" )
        #else
        #set( $row = "row1" )
        #end
        
        
        
         <tr>
                    	<td class="$row" valign="top" align="center">$fail.bil</td>
                        <td class="$row" valign="top">
                        #if($flag_skrin=="LaporanTanahTerperinciImej")
                        <img src="../../servlet/ekptg.view.ppt.FrmDisplayImage?id=$fail.id_Dokumen" alt="Imejan" border="1" width="300" height="300" onclick="papar_Lampiran('$!fail.id_Dokumen')"/>
                        #else
                        <a href="javascript:papar_Lampiran('$!fail.id_Dokumen')"><font color="blue">$fail.nama_Fail</font></a>
                        #end
                        
                        </td>
                    	<td class="$row" valign="top">
                        
                        
                        
                        
                       #if($flag_skrin=="LaporanTanahTerperinciImej")
                       #set($input_gamabar = $fail.id_Dokumen + "Gambar")
                       
                     	<textarea name="$input_gamabar" id="$input_gamabar"   cols="60" rows="3">$fail.tajuk</textarea>
                       #else
                   		 $fail.tajuk
                       #end
                        
                        
                        </td>
                        <td class="$row" valign="top" style="display:NONE;">$fail.keterangan</td>
                       	
                    	<td class="$row" valign="top" align="center"><input type="button" name="cmdHapusDoc" value ="Hapus" onClick="hapusDokumen('$!fail.id_Dokumen','$!id_permohonan','$!id_pembatalan','$!id_penarikanbalik','$!id_bantahan','$!id_permintaanukur','$!id_award','$!id_hakmilik','$flag_skrin','$id_tanah','$id_notisawam','$id_buktipenyampaian','$id_borangh')"></td>	
                   		
                    </tr>
        
        
        
         
          
          
          
         #end
        
         #else
          <tr>  
            <td colspan="8"><font color="red">TIADA REKOD</font></td>    
          </tr>
          #end
      </table>
      
      
      </fieldset>
      
      </td>
  </tr>
</table>

<!-- PPT-26(i) -->
#if($flag_skrin=="siasatan_afidavit")
<div>
	<input type="button" name="cmdKembali" value ="Kembali" onClick="popupCarianAfidavit('$id_permohonan','$id_hakmilikpb','$!id_siasatan','$id_award','siasatan_afidavit')">
</div>
#end



<script>

if('$tutup_skrin_popup' == "yes")
{
	kembaliKeSkrinUtama('$id_permohonan');
}

function simpanKeterangan(id_permohonan,id_pembatalan,id_penarikanbalik,id_bantahan,id_permintaanukur,id_award,id_hakmilik,flag_skrin,id_tanah,id_notisawam,id_buktipenyampaian,id_borangh) {
	
	//document.${formName}.ScreenLocation.value = "top";
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupUploadDokumen&command=simpanKeterangan&id_permohonan="+id_permohonan+"&id_pembatalan="+id_pembatalan+"&id_penarikanbalik="+id_penarikanbalik+"&id_bantahan="+id_bantahan+"&id_permintaanukur="+id_permintaanukur+"&id_award="+id_award+"&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&id_tanah="+id_tanah+"&id_notisawam="+id_notisawam+"&id_buktipenyampaian="+id_buktipenyampaian+"&id_borangh="+id_borangh;
	document.${formName}.submit();
}

function hapusDokumen(iddokumen,id_permohonan,id_pembatalan,id_penarikanbalik,id_bantahan,id_permintaanukur,id_award,id_hakmilik,flag_skrin,id_tanah,id_notisawam,id_buktipenyampaian,id_borangh) {
	
	//document.${formName}.ScreenLocation.value = "top";
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupUploadDokumen&command=hapusDokumen&id_dokumen="+iddokumen+"&id_permohonan="+id_permohonan+"&id_pembatalan="+id_pembatalan+"&id_penarikanbalik="+id_penarikanbalik+"&id_bantahan="+id_bantahan+"&id_permintaanukur="+id_permintaanukur+"&id_award="+id_award+"&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&id_tanah="+id_tanah+"&id_notisawam="+id_notisawam+"&id_buktipenyampaian="+id_buktipenyampaian+"&id_borangh="+id_borangh;
	document.${formName}.submit();
}

function uploadFail(id_permohonan,id_pembatalan,id_penarikanbalik,id_bantahan,id_permintaanukur,id_award,id_hakmilik,flag_skrin,id_tanah,id_notisawam,id_buktipenyampaian,id_borangh){	
//alert("CECK :::"+Validate());
	var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
	if(document.${formName}.NAMA_DOKUMEN_ADD.value == ""){
		alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu.");
  		document.${formName}.NAMA_DOKUMEN_ADD.focus(); 
		return;
	}
	if(document.${formName}.fileupload.value == ""){
		alert("Sila pilih \"Dokumen\" yang hendak dimuat naik terlebih dahulu.");
  		document.${formName}.fileupload.focus(); 
		return;
	}
	if(Validate() == false && flag_skrin=="LaporanTanahTerperinciImej")
	{
		alert("Format fail yang dimuatnaik adalah tidak dibenarkan, Sila muatnaik fail berformat imej  seperti " + _validFileExtensions.join(", ") + " sahaja.");
        return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;

		//token for dopost
		var ft = document.${formName}.form_token_pop.value;
		var token = "&form_token_pop="+ft;

		//data
		var name = document.${formName}.NAMA_DOKUMEN_ADD.value;
		var keterangan = document.${formName}.DETAIL_DOKUMEN_ADD.value;

		var command = "&command=uploadFile";
		
		var data = "&id_permohonan="+id_permohonan+"&id_pembatalan="+id_pembatalan+"&id_penarikanbalik="+id_penarikanbalik+"&id_bantahan="+id_bantahan+"&id_permintaanukur="+id_permintaanukur+"&id_award="+id_award+"&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&nama_dokumen="+name+"&keterangan="+keterangan+"&id_tanah="+id_tanah+"&id_notisawam="+id_notisawam+"&id_buktipenyampaian="+id_buktipenyampaian+"&id_borangh="+id_borangh;

		var actionItem = (command+data+token);
		
		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.encoding = "multipart/form-data";

		document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupUploadDokumen"+actionItem;
		document.${formName}.submit();		
	}
}


function Validate() {
	var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
    var arrInputs = document.${formName}.getElementsByTagName("input");
    for (var i = 0; i < arrInputs.length; i++) {
        var oInput = arrInputs[i];
        if (oInput.type == "file") {
            var sFileName = oInput.value;
            if (sFileName.length > 0) {
                var blnValid = false;
                for (var j = 0; j < _validFileExtensions.length; j++) {
                    var sCurExtension = _validFileExtensions[j];
                    if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                        blnValid = true;
                        break;
                    }
                }
            }
        }
    }

    return blnValid;
}


function papar_Lampiran(id_dokumen) {
    var url = "../../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function transferHakmilik() 
 {	
 	document.${formName}.command.value = "transfer";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupHakmilik?&id_negeri="+'$id_negeri'+"&id_daerah="+'$id_daerah'+"&jenis_seksyen="+'$jenis_seksyen';
	document.${formName}.submit();			
				
 }	

function paparHakmilik(id_permohonan) 
 {	
 	document.${formName}.id_mohon_selected.value = id_permohonan;
	document.${formName}.command.value = "paparHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupHakmilik?&id_negeri="+'$id_negeri'+"&id_daerah="+'$id_daerah'+"&jenis_seksyen="+'$jenis_seksyen';
	document.${formName}.submit();			
				
 }	

function carian() 
 {	
	document.${formName}.command.value = "cari";
	document.${formName}.submit();			
				
 }	
 
  function kosongkan() 
 {
	document.${formName}.NAMA_DOKUMEN.value = "";	
	document.${formName}.TAJUK.value = "";
	document.${formName}.command.value = "cari";
	document.${formName}.submit();							
 }

function kembaliKeSkrinUtama(id_permohonan) {
	
	try {
		//simpanDisemak(ID_PLA);
        window.opener.HandlePopup_from_copy_hakmilik(id_permohonan);		
    }
    catch (err) {}
    window.close();
    return false;
	document.${formName}.cmdKembaliSkrinUtama.value = "Sila Tunggu....";		
}

//PPT-26(i)
function popupCarianAfidavit(id_permohonan,id_hakmilikpb,id_siasatan,id_award,flag_skrin)
{
	
	//alert(id_permohonan);
	
	var url = "ekptg.view.ppt.SkrinPopupAfidavit?&id_permohonan="+id_permohonan+"&id_hakmilikpb="+id_hakmilikpb+"&flag_skrin="+flag_skrin+"&id_siasatan="+id_siasatan+"&id_award="+id_award;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
}

</script>



