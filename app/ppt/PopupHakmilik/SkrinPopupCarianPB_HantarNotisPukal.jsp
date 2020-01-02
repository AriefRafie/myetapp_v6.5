<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<script>var $jquery = jQuery.noConflict();</script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPT.css">
<!--
POPUP HAKMILIK
<br />
id_permohonan : $id_permohonan
<br />
-->




<div style="display:none">
id_mohon_selected : <input type="hidden" id="id_mohon_selected" name="id_mohon_selected" value="$id_mohon_selected">
<br />
id_permohonan : <input type="hidden" id="id_permohonan" name="id_permohonan" value="$id_permohonan">
</div>




<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
<fieldset>
<legend>CARIAN PB
</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">


        
<tr >
          <td width="1%">&nbsp;</td>
          <td width="25%" align="left" valign="top">NAMA PB</td>
          <td width="1%" valign="top">:</td>
          <td width="73%" valign="top">          
          <input name="NAMA_PB" type="text" id="NAMA_PB" value="$NAMA_PB" size="80" maxlength="1000"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />         
          </td>
        </tr> 
        
        
        <tr >
          <td>&nbsp;</td>
          <td align="left" valign="top">MYID PB</td>
          <td valign="top">:</td>
          <td valign="top">          
          <input name="NO_PB" type="text" id="NO_PB" value="$NO_PB" size="80" maxlength="1000"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />         
          </td>
        </tr>        
        

<tr>
  <td  ></td>
  <td align="left" valign="top" ></td>
  <td valign="top" ></td>
  <td valign="top" >
   
   
    <input type="button" id="cariHakmilik" name="cariHakmilik" value="CARI" onclick="carian()"/>
    <input type="button" id="kosongHariHakmilik" name="kosongHariHakmilik" value="RESET" onclick="kosongkan()"/>
    
    </td>
  
  
</tr>
</table>
</fieldset>
</td>
  </tr>
</table>






<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <fieldset>
    <legend>PENYAMPAIAN NOTIS BORANG E</legend>
    
    <table width="100%" border="0">
		<tr>
			<td width="1%"><font color="red">*</font></td>
			<td width="24%">Nama Penghantar</td>
			<td width="1%">:</td>
			<td width="74%">
            <select name="selectPenghantarNotis" class="autoselect" id="selectPenghantarNotis"  onchange="setNamaPenghantar(this.value)" >
                                        <option value="" >SILA PILIH</option>
                                  #foreach($ln in $listPenghantarNotis)                                 
                              <option value="$ln.NAMA_PEGAWAI">$ln.NAMA_PEGAWAI</option>
                             #end
                                        </select>
            <input type="text" name="txtNamaHantar" id="txtNamaHantar" value="$!txtNamaHantar" size="47" maxlength="80"   ></td>
		</tr>
		
	
		<tr>
			<td>&nbsp;</td>
			<td>Jenis Serahan</td>
			<td>:</td>
			<td><select name="socJenisSerah" style="width:160px" onchange="onChangeStatus('$id_hakmilik','$flag_skrin','$id_borangg')">
      		
      			#if($socJenisSerah=="1")
      			<option value="1">PERKHIDMATAN POS</option>				
      			<option value="2">SERAHAN TANGAN</option>	
      			<option value="3">FAKSIMILI</option>
      			<option value="4">FOLIO KOSONG</option>		
      			<option value="">SILA PILIH</option>   	
      			#elseif($socJenisSerah=="2")
      			<option value="2">SERAHAN TANGAN</option>	
      			<option value="1">PERKHIDMATAN POS</option>	
      			<option value="3">FAKSIMILI</option>
      			<option value="4">FOLIO KOSONG</option>		
      			<option value="">SILA PILIH</option>  
      			#elseif($socJenisSerah=="3")
      			<option value="3">FAKSIMILI</option>
      			<option value="1">PERKHIDMATAN POS</option>	
      			<option value="2">SERAHAN TANGAN</option>
      			<option value="4">FOLIO KOSONG</option>		
      			<option value="">SILA PILIH</option>    
      			#elseif($socJenisSerah=="4")
      			<option value="4">FOLIO KOSONG</option>	
      			<option value="1">PERKHIDMATAN POS</option>	
      			<option value="2">SERAHAN TANGAN</option>
      			<option value="3">FAKSIMILI</option>	
      			<option value="">SILA PILIH</option> 
      			#else
      			<option value="">SILA PILIH</option>    			
      			<option value="1">PERKHIDMATAN POS</option>	
      			<option value="2">SERAHAN TANGAN</option>	
      			<option value="3">FAKSIMILI</option>	
      			<option value="4">FOLIO KOSONG</option>	
      			#end
      			
			</select></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Status Serahan</td>
			<td>:</td>
			<td><select name="socStatusSerah" style="width:auto" onchange="onChangeStatus('$id_hakmilik','$flag_skrin','$id_borangg')">
      		
      			#if($socStatusSerah=="1")
      			<option value="1">DISERAHKAN</option>
      			<option value="2">TIDAK DISERAHKAN</option>	
      			<option value="">SILA PILIH</option> 
      			#elseif($socStatusSerah=="2")
      			<option value="2">TIDAK DISERAHKAN</option>	
      			<option value="1">DISERAHKAN</option>	
      			<option value="">SILA PILIH</option>
      			#else
      			<option value="">SILA PILIH</option>    
      			<option value="1">DISERAHKAN</option>	
      			<option value="2">TIDAK DISERAHKAN</option>				
      			#end
      			
			</select></td>
		</tr>		
		
			<tr>
			<td>
            <font color="red">*</font>
            </td>
            <td>
			#if($hideItem=="no")
			Tarikh Serahan
			#else
			Tarikh Tampal
			#end
            </td>
			<td>:</td>
			<td><input name="txdTarikhSerah"  id="txdTarikhSerah" size="11" type="text" value="$!txdTarikhSerah" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerah',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr>
		
		#if($hideItem=="yes")
		<tr>
			<td><font color="red">*</font></td>
			<td>Masa Tampal</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtMasaTampal" id="txtMasaTampal" value="$!txtMasaTampal" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
				<select $disability1 $disabilityx name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

				#if($socJenisWaktu=="1")
				<option value="1">PAGI</option>								
                <option value="2">TENGAHARI</option>
                <option value="3">PETANG</option>
                <option value="0">SILA PILIH</option>
				#elseif($socJenisWaktu=="2")
				<option value="2">TENGAHARI</option>
                <option value="1">PAGI</option>
                <option value="3">PETANG</option>
                <option value="0">SILA PILIH</option>
				#elseif($socJenisWaktu=="3")
				<option value="3">PETANG</option>
                <option value="1">PAGI</option>
                <option value="2">TENGAHARI</option>
                <option value="0">SILA PILIH</option>
				#else
				<option value="0">SILA PILIH</option>
                <option value="1">PAGI</option>
                <option value="2">TENGAHARI</option>
                <option value="3">PETANG</option>
				#end
                    				
                    	
             	</select>&nbsp;<font color="blue" style="font-size:10px">#if($isEdit=="yes")<i>format 12 jam (HHMM)</i>#end</font></td>
		</tr>
		#end
	


	</table>
    
    </fieldset>
    
    
    
    
    
    
    <fieldset>
      <legend><b>SENARAI PIHAK BERKEPENTINGAN</b> <input type="button" name="cmdSimpanPilihanNotis" value ="Simpan Pilihan" onClick="javascript:simpanPilihanNotis('$id_hakmilik','$flag_skrin','$!hideItem','$id_borangg')"></legend>          
		#parse("app/utils/record_pagingPopup.jsp")
        #set ($count = 0) 
   
        
        
        <table width="100%" border="0">
  			
             
  			<tr class="table_header">
  				<td  align="center"><b><font color="white">NO</font></b></td>
    			<td ><b><font color="white">NAMA PB</font></b></td>
    			<td  ><b><font color="white">JENIS PB</font></b></td>
    			<td  align="center"><b><font color="white">BAHAGIAN</font></b></td>
                <td  align="left"><b><font color="white">NAMA PENERIMA</font></b></td>
                <td  align="left"><b><font color="white">JENIS KP/NO. KP</font></b></td>
                <td  align="left"><b><font color="white">HUBUNGAN</font></b></td>  
                <td  align="left"><b><font color="white">TEMPAT TAMPAL</font></b></td>                  				
                <td  align="left"><b><font color="white">CATATAN</font></b></td>
      
      
		        <td  align="center"> 
		          <b><font color="white">PILIHAN</font></b>
		      	  <br />
		      	  	<input type="checkbox" name="allNotis" id="allNotis" onClick="doCheckAllNotis('$SenaraiFail.size()');" title="Semak untuk memilih semua PB" />
		      		
		      	</td>
      
     

      		</tr>
  
  		
  
  
   			#if($SenaraiFail.size() > 0)
  				#foreach($list in $SenaraiFail)        
           			#set( $i = $velocityCount )
         			
         			#if ( ($i % 2) != 1 )
              		 	#set( $rowx = "row2" )
         			#else
               			#set( $rowx = "row1" )
         			#end
 
   			<tr>
    			<td colspan="5">
                
   		<tr >
    		<td class="$rowx" valign="top"><center>
    		$list.bil
    		</center></td>
 			<td class="$rowx" id="$list.NO_PB" valign="top">
            
            
            
             #set($id_hakmilikpb = $list.ID_PIHAKBERKEPENTINGAN +"id_hakmilikpb")            
             <input type="hidden" name="$id_hakmilikpb" id="$id_hakmilikpb" value="$list.ID_HAKMILIKPB"   >
            
            
    			<div align="left">
                <!--
                #if($flag_skrin == "senarai_borangF_inbulk")
                $list.NAMA_PB
                #elseif($flag_skrin == "skrin_sementara_pb_sek8")
                <a href="javascript:paparpbpopup('$list.ID_PIHAKBERKEPENTINGAN','$flag_skrin');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">$list.NAMA_PB</font></a>
                
                #else
                <a href="javascript:paparpbpopup('$list.ID_HAKMILIKPB','$flag_skrin');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">$list.NAMA_PB</font></a>
                #end
                -->
                $list.NAMA_PB
    			
                <br />
                #if($list.JENISNOPB != "")
                $list.JENISNOPB :&nbsp; 
                #end
                #if($list.NO_PB != "")
                $list.NO_PB
                #end
                <div>$list.KETERANGAN_JENIS_PB</div>
    			</div>
			</td>
   
    		<td class="$rowx" valign="top">
    		<div align="left">$list.JENISPB</div>
    		
    	
    		</td>
    		
		    
		    <td class="$rowx" valign="top">
				<div align="center">
		    	#if($list.SYER_ATAS != "" && $list.SYER_BAWAH != "")
		    		$list.SYER_ATAS / $list.SYER_BAWAH    
		    	#end
		    	</div>
  
    
                      #set($count = 0) 
                      #foreach($list1 in $senarai_pihak_penting_bahagian)
                      #if($list1.ID_BAHAGIANPB == $list.ID_BAHAGIANPB && $list1.ID_HAKMILIKPB != $list.ID_HAKMILIKPB ) 
                      #set($count=$count + 1) 
                      #end
                      #end
                      
                      #if($count > 0)
                     
                      <div>
                      #set($count_total = 0) 
                      #foreach($list1 in $senarai_pihak_penting_bahagian)
                      #if($list1.ID_BAHAGIANPB == $list.ID_BAHAGIANPB && $list1.ID_HAKMILIKPB != $list.ID_HAKMILIKPB)
                      #set($count_total=$count_total + 1)                      
                      #if($count > 1 && $count != $count_total && $count_total != $count - 1 ) 
                      $list1.NAMA_PB,
                      #elseif($count > 1 && $count != $count_total && $count_total == $count - 1) 
                      $list1.NAMA_PB &
                      #elseif($count > 1 && $count == $count_total)
                      $list1.NAMA_PB
                      #elseif($count == 1)
                      $list1.NAMA_PB
                      #end                                          
                      #end
                      #end 
                      </div>
                      #end
    
    		</td>
            
         
         
         	<td class="$rowx" valign="top">
            
            #set($txtNamaTerima = $list.ID_PIHAKBERKEPENTINGAN +"txtNamaTerima") 
            
            #if($list.NAMA_PENERIMA != "")
            #set($val_txtNamaTerima = $list.NAMA_PENERIMA)
            #else
            #set($val_txtNamaTerima = $!list.NAMA_PB)
            #end
                    
         	<input type="text" name="$txtNamaTerima" id="$txtNamaTerima" value="$val_txtNamaTerima" size="30" maxlength="80"   >
		       
         
         	</td>
            <td class="$rowx" valign="top">
            
            
            #if($list.ID_JENISNOPB_BUKTI != "")
            #set($val_socJnsKP = $list.ID_JENISNOPB_BUKTI)
            #else
            #set($val_socJnsKP = $!list.ID_JENISNOPB)
            #end
            
         
         	#set($socJnsKP = $list.ID_PIHAKBERKEPENTINGAN +"socJnsKP")
		    <select name="$socJnsKP" class="autoselect"  >
            <option value="" >SILA PILIH</option>
            #foreach($ja in $selectJenisNoKP) 
            <option value="$!ja.ID_JENISNOPB" #if($ja.ID_JENISNOPB==$!val_socJnsKP) selected="selected" #end >$!ja.KETERANGAN</option>
            #end 
            </select>
            
            
            
            #if($list.NO_KP_PENERIMA != "")
            #set($val_no_kp = $list.NO_KP_PENERIMA)
            #else
            #set($val_no_kp = $!list.NO_PB)
            #end
            
            #set($no_kp = $list.ID_PIHAKBERKEPENTINGAN +"no_kp")         
         	<input type="text" name="$no_kp" id="$no_kp" value="$!val_no_kp" size="30" maxlength="80"   >
         
         	</td>
            <td class="$rowx" valign="top">
         	
            #if($list.HUBUNGAN != "")
            #set($val_txtHubungan = $list.HUBUNGAN)
            #else
            #set($val_txtHubungan = "")
            #end
            
		    #set($txtHubungan = $list.ID_PIHAKBERKEPENTINGAN +"txtHubungan")         
         	<input type="text" name="$txtHubungan" id="$txtHubungan" value="$!val_txtHubungan" size="30" maxlength="80"   >
         
         	</td>
             <td class="$rowx" valign="top">
             
              #if($list.TEMPAT_TAMPAL != "")
            #set($val_txtTempatTampal = $list.TEMPAT_TAMPAL)
            #else
            #set($val_txtTempatTampal = "")
            #end
         	
		    #set($txtTempatTampal = $list.ID_PIHAKBERKEPENTINGAN +"txtTempatTampal")         
         	<input type="text" name="$txtTempatTampal" id="$txtTempatTampal" value="$val_txtTempatTampal" size="30" maxlength="80"   >
         
         	</td>
            <td class="$rowx" valign="top">
            
            #if($list.CATATAN_BUKTI != "")
            #set($val_txtCatatan = $list.CATATAN_BUKTI)
            #else
            #set($val_txtCatatan = "")
            #end
         	 #set($txtCatatan = $list.ID_PIHAKBERKEPENTINGAN +"txtCatatan")         
           
            <textarea name="$txtCatatan" id="$txtCatatan" rows="2" cols="20"  >$val_txtCatatan</textarea>
		       
         
         	</td>
    		
            
         
         	<td class="$rowx" valign="top">
         	    #set($boxnOTIS = "")	    		
		    	#if($list.ID_BUKTIPENYAMPAIAN != "" )    
		    	#end 
          
          		<div align="center">
        			<input type="checkbox" name="checkN" id="checkN" onClick="doUpdateCheckAllNotis('$SenaraiFail.size()')" title="Semak untuk menunjukan '$list.NAMA_PB' dipilih" $boxnOTIS value="$list.ID_PIHAKBERKEPENTINGAN" >
        		</div>
         
         	</td>
         	
            #end
         	<!-- <td>&nbsp;</td> -->
         	
   		</tr>
  	
  		
  	#else
  	
  		<tr>  
    		<td colspan="5">Tiada Rekod</td>    
  		</tr>
  	#end
  	
	</table>
      
      
      </fieldset>
      
      </td>
  </tr>
</table>







#if($berjayasimpan == "yes")
<script>
$jquery(document).ready(function()
{
displaySelesai('$id_borangg','$id_hakmilik','$flag_skrin');
});
</script>


#end





<script>

function setNamaPenghantar(val)
{
	//alert("VAL :"+val);
	document.${formName}.txtNamaHantar.value = val;
}


function displaySelesai(id_borangg,id_hakmilik,flag_skrin)
{
	//alert("test");
	try
	{
		if(flag_skrin=="skrin_kemasukan_notisH_secara_pukal")
		{			
			window.opener.penerimaanBorangH_displaySelesai(id_borangg);
		}
		else if(flag_skrin=="skrin_kemasukan_notisK_secara_pukal")
		{			
			window.opener.viewListPenyampaianNotis_displaySelesai(id_hakmilik);
		}
		else if(flag_skrin=="skrin_kemasukan_notis_secara_pukal")
		{			
			window.opener.viewHM_displaySelesai(id_hakmilik);
		}
		
		
		
	}
	catch (err) {}
    window.close();
    return false;
}



function onChangeStatus(id_hakmilik,flag_skrin,id_borangg){
	document.${formName}.command.value = "checkStatus";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupNotisSecaraPukal?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&id_borangg="+id_borangg;
	document.${formName}.submit();
}

function simpanPilihanNotis(id_hakmilik,flag_skrin,valHide,id_borangg){
	
	if(document.${formName}.txtNamaHantar.value == ""){
		alert("Sila masukkan \"Nama Penghantar\" terlebih dahulu.");
  		document.${formName}.txtNamaHantar.focus(); 
		return;
	}	
	else
	if(document.${formName}.txdTarikhSerah.value == ""){
		if(valHide=="no"){
			alert("Sila masukkan \"Tarikh Serahan\" terlebih dahulu.");
		}else{
			alert("Sila masukkan \"Tarikh Tampal\" terlebih dahulu.");
		}
		document.${formName}.txdTarikhSerah.focus(); 
		return;
	}
	else
	if(valHide=="yes" && document.${formName}.txtMasaTampal.value == ""){
		alert("Sila masukkan \"Masa Tampal\" terlebih dahulu.");
		document.${formName}.txtMasaTampal.focus(); 
		return;
	}
	else
	{	
	document.${formName}.command.value = "simpanMaklumatNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupNotisSecaraPukal?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&id_borangg="+id_borangg;
	document.${formName}.submit();
	}
}

function refresh_Popup(id_hakmilik,flag_skrin)
{

	
	$jquery(document).ready(function()
		{
			var refreshId = setInterval( function()
			{
			
				document.${formName}.command.value = "refresh_Popup";	
				document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianPB?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin;
				document.${formName}.submit();
			
			}, 3000);
		});
		
}

function Simpan_Borang_Popup(id_hakmilik,flag_skrin,id_hakmilikpb)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {	
	document.${formName}.command.value = "Simpan_Borang";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianPB?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&id_hakmilikpb="+id_hakmilikpb;
	document.${formName}.submit();
	
	
		if(flag_skrin=="skrin_hakmilik_pb_sek8" && (id_hakmilik!="" && id_hakmilik!=null))
		{
			window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_pb_sek8" && (id_hakmilikpb!="" && id_hakmilikpb!=null) )
		{
			window.opener.paparpb(id_hakmilikpb);
		}
		else if(flag_skrin=="skrin_pb_siasatan_sek8"  && (id_hakmilikpb!="" && id_hakmilikpb!=null))
		{
			window.opener.paparpb(id_hakmilikpb);
		}		
	}
}


if('$tutup_skrin_popup' == "yes")
{
	kembaliKeSkrinUtama('$id_permohonan');
}

function paparpbpopup(id_hakmilikpb,flag_skrin)
{
	try
	{
		if(flag_skrin=="skrin_hakmilik_pb_sek8")
		{			
			window.opener.paparpb(id_hakmilikpb);
		}
		else if(flag_skrin=="skrin_pb_sek8")
		{
			window.opener.paparpb(id_hakmilikpb);
		}		
		else if(flag_skrin=="skrin_pb_siasatan_sek8")
		{
			window.opener.paparpb(id_hakmilikpb);
		}
		else if(flag_skrin=="skrin_sementara_pb_sek8")
		{
			
			window.opener.viewPB(id_hakmilikpb);
		}
	}
	catch (err) {}
    window.close();
    return false;
}

function transferHakmilik() 
 {	
 	document.${formName}.command.value = "transfer";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupHakmilik?&id_negeri="+'$id_negeri'+"&id_daerah="+'$id_daerah';
	document.${formName}.submit();			
				
 }	

function paparHakmilik(id_permohonan) 
 {	
 	document.${formName}.id_mohon_selected.value = id_permohonan;
	document.${formName}.command.value = "paparHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupHakmilik?&id_negeri="+'$id_negeri'+"&id_daerah="+'$id_daerah';
	document.${formName}.submit();			
				
 }	

function carian() 
 {	
	document.${formName}.command.value = "cari";
	document.${formName}.submit();			
				
 }	
 
 function kosongkan() 
 {
	document.${formName}.NO_PB.value = "";
	document.${formName}.NAMA_PB.value = "";
	document.${formName}.command.value = "cari";
	document.${formName}.submit();							
 }	



function paparHakmilik(id_hakmilik,flag_skrin){
	try {
		if(flag_skrin=="daftar_sek8")
		{
		window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_hakmilik_sek8")
		{
		window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_list_hakmilik_pb_sek8")
		{
		window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_hakmilik_pb_sek8")
		{
		window.opener.viewHM(id_hakmilik);	
		}
	}
	catch (err) {}
    window.close();
    return false;
}

function tambahPbPopup(id_hakmilik,flag_skrin)
{
	try {
		if(flag_skrin=="skrin_list_hakmilik_pb_sek8")
		{
			window.opener.tambahWakil(id_hakmilik);
		}
	}
	catch (err) {}
    window.close();
    return false;
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




function doCheckAll1(sudahbatal){  
var cc = 0;  
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
		cc++;
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = false;

        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}


function doCheckAllBorangG(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangG.checked == true){
        if (document.${formName}.borangG.length == null){
		cc++;
            document.${formName}.borangG.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangG.length; i++){
                document.${formName}.borangG[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangG.length == null){
            document.${formName}.borangG.checked = false;


        } else {
            for (i = 0; i < document.${formName}.borangG.length; i++){
                document.${formName}.borangG[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}

function doCheckAllBorangE(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangE.checked == true){
        if (document.${formName}.borangE.length == null){
		cc++;
            document.${formName}.borangE.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangE.length; i++){
                document.${formName}.borangE[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangE.length == null){
            document.${formName}.borangE.checked = false;

        } else {
            for (i = 0; i < document.${formName}.borangE.length; i++){
                document.${formName}.borangE[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}



function doCheckAllNotis(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allNotis.checked == true){
        if (document.${formName}.checkN.length == null){
		cc++;
            document.${formName}.checkN.checked = true;
        } else {
            for (i = 0; i < document.${formName}.checkN.length; i++){
                document.${formName}.checkN[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.checkN.length == null){
            document.${formName}.checkN.checked = false;

        } else {
            for (i = 0; i < document.${formName}.checkN.length; i++){
                document.${formName}.checkN[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}





function doUpdateCheckAll1(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all1.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1.checked = true;
	  }
	  
	  
	 
	
}



function doUpdateCheckAllBorangG(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangG.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangG.length; i++)
	  {
      if (document.${formName}.borangG[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangG.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangG.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangG.checked = true;
	  }
	  
	  
	  
	  	
	
}

function doUpdateCheckAllBorangE(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangE.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangE.length; i++)
	  {
      if (document.${formName}.borangE[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangE.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangE.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangE.checked = true;
	  }
	  
	  
	
}



function doUpdateCheckAllNotis(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.checkN.length > 1)
{     
	  for (i = 0; i < document.${formName}.checkN.length; i++)
	  {
      if (document.${formName}.checkN[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.checkN.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allNotis.checked = false;
	  }
	  else
	  {
	  document.${formName}.allNotis.checked = true;
	  }
	  
	  
	
}


function popupPemegangAmanah_popupPB(id_hakmilik,id_hakmilikpb,flag_skrin){

	var w = "600";
	var h = "400";
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	var url = "../${securityToken}/ekptg.view.ppt.FrmPopupPemegangAmanah_PopupPB?id_hakmilik="+id_hakmilik+"&id_hakmilikpb="+id_hakmilikpb+"&flag_skrin="+flag_skrin;
		
	var hWnd = window.open(url, "Daftar Pemegang Amanah", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}



</script>



