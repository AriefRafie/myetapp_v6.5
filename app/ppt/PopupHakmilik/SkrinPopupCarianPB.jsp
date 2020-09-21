 <script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<script>var $jquery = jQuery.noConflict();</script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPT.css">


<input type="hidden" name="flag_tuntutan" id="flag_tuntutan" value="$flag_tuntutan" >


<div style="display:none">
id_mohon_selected : <input type="text" id="id_mohon_selected" name="id_mohon_selected" value="$id_mohon_selected">
<br />
id_permohonan : <input type="text" id="id_permohonan" name="id_permohonan" value="$id_permohonan">
</div>

<!-- DARI ILAUNCH -->
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
<!-- TUTUP DARI ILAUNCH -->

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI PIHAK BERKEPENTINGAN</b></legend>          
		<!-- #parse("app/utils/record_pagingPopup.jsp") -->
        #set ($count = 0) 
        
        #if($SenaraiFail.size() > 0)
            #if($flag_skrin == "senarai_borangF_inbulk" || $flag_skrin == "skrin_sementara_pb_sek8" || $flag_skrin == "bantahan_mahkamah")
            
            #else  
            	 #set($nama_button_simpan = "Simpan Pilihan Borang")
            	 #if($flag_tuntutan=="Y")
            	 	 #set($nama_button_simpan = "Simpan Catatan Tuntutan")
            	 #end
                
                  <input type="button" value="$nama_button_simpan" onClick="Simpan_Borang_Popup('$id_hakmilik','$flag_skrin','$id_hakmilikpb')">
            #end      
        #end
        
        
        
        
        <table width="100%" border="0">
  			
             
  			<tr class="table_header">
  				<td  align="center"><b><font color="white">NO</font></b></td>
    			<td ><b><font color="white">NAMA PB</font></b></td>
    			<td ><b><font color="white">JENIS PB</font></b></td>
    			
    			<td  align="center"><b><font color="white">BAHAGIAN / SYER & BERKONGSI BAHAGIAN (JIKA ADA)</font></b></td>
   				#if($flag_skrin == "senarai_borangF_inbulk" || $flag_skrin == "skrin_sementara_pb_sek8")
                
                #elseif($flag_skrin == "bantahan_mahkamah")
                <td  align="center"><b><font color="white">NO. LOT / PT</font></b></td>
                <td  align="center"><b><font color="white">STATUS BANTAHAN</font></b></td>
                                
               #else
               
                 #if($flag_tuntutan=="Y")
        	 <td align ="center" ><b><font color="white">CATATAN</font></b></td>

      		#end
    			<td  align="center"> 
     				<b><font color="white">BORANG C & D</font></b>
      				<br />
		     		<input type="checkbox" name="all1" id="all1" onClick="doCheckAll1('$list_kehadiran.size()');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang C & D" />
      				
      			</td>
     
       			<td  align="center"> 
          			<b><font color="white">BORANG E & F</font></b>
      				<br />
      				<input type="checkbox" name="allborangE" id="allborangE" onClick="doCheckAllBorangE('$list_kehadiran.size()');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang E & F" />
      			
      			</td>
      
      
        		<td align="center"> 
         			<b><font color="white">BORANG G & H</font></b>
				    <br />
				    <input type="checkbox" name="allborangG" id="allborangG" onClick="doCheckAllBorangG('$list_kehadiran.size()');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang G & H" />
      				
      			</td>
      
      
		        <td  align="center"> 
		          <b><font color="white">BORANG K</font></b>
		      	  <br />
		      	  	<input type="checkbox" name="allborangK" id="allborangK" onClick="doCheckAllBorangK('$list_kehadiran.size()');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang K" />
		      		
		      	</td>
      
      		#end

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
    		<td class="$rowx"><center>$list.BIL</center></td>
 			<td class="$rowx" id="$list.NO_PB">
    			<div align="left">
                 
                #if($flag_skrin == "bantahan_mahkamah")
                <a href="javascript:paparpbbantahan('$!list.ID_HAKMILIK','$!list.ID_HAKMILIKPB','$!list.ID_PIHAKBERKEPENTINGAN','$!list.status_bantahan','$!list.ID_PERMOHONAN','$flag_skrin');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">$list.NAMA_PB</font></a>
                #elseif($flag_skrin == "senarai_borangF_inbulk")
                $list.NAMA_PB
                #elseif($flag_skrin == "skrin_sementara_pb_sek8")
                <a href="javascript:paparpbpopup('$list.ID_PIHAKBERKEPENTINGAN','$flag_skrin');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">$list.NAMA_PB</font></a>
                
                #else
	                #if($flag_tuntutan=="Y")
	                $list.NAMA_PB
	                #else
	                <a href="javascript:paparpbpopup('$list.ID_HAKMILIKPB','$flag_skrin');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">$list.NAMA_PB</font></a>
	                #end
                #end
    			
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
   
    		<td class="$rowx">
    		
    		#set($lblPA = "Daftar Pemegang Amanah")
    		<!-- #* #if($list.ID_PA1 != "")
    			#set($lblPA = $list.NAMA_PA1+" (Pemegang Amanah)")
    		#end 
            
            *# -->
    		<div align="left">$list.JENISPB</div>
    		
            #if($flag_skrin == "bantahan_mahkamah")
            
            #else
            
    		#if($list.UMUR != 0 && $list.UMUR < 18)
    		<div><a href="javascript:popupPemegangAmanah_popupPB('$!list.ID_HAKMILIK','$list.ID_HAKMILIKPB','&flag_skrin');">
    		<font color="blue" style='font-size:11px'><i>$!lblPA</i></font></a></div>
    		#end
            
            #end
            
    		</td>

		    <td class="$rowx">
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
                      $list1.NAMA_PB
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
            
            
            #if($flag_skrin == "senarai_borangF_inbulk" || $flag_skrin == "skrin_sementara_pb_sek8")
                
            #elseif($flag_skrin == "bantahan_mahkamah")
             <td class="$rowx" align="center">$list.no_lotpt</td>
                <td class="$rowx">
                
                
                  #if ($list.flag_online == '2')
    	<font color="red"> $senarai.keteranganStatusBantahan (Permohonan Online) </font>
          #else
          $list.keteranganStatusBantahan <br> <b>$list.noKes</b>
          #end
             </td>   
                 
            #else
          
            #if($flag_tuntutan=="Y")
          
            <td class="$rowx">  
		    
      			<div align="center">
        	<textarea name="catatan" id="catatan" >$list.CATATAN2</textarea>
        		</div>
        			
        	</td>
        	
        	#end
     
    
    		<td class="$rowx">
  
		    	#set($boxC = "")    
		    
		    	#if($list.FLAG_BORANGC == "1" )    
		    		#set($boxC = "checked")   
		   		#end    

      			<div align="center">
        			<input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1('$list_kehadiran.size()')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang C & D" $boxC value="$list.ID_HAKMILIKPB" >
        		</div>
        			<input type="hidden" name="idPB" id="idPB" value="$list.ID_HAKMILIKPB" >
        			
        	</td>
        
         	<td class="$rowx">
         
		        #set($boxE = "")    
		    	
		    	#if($list.FLAG_BORANGE == "1" )    
		    		#set($boxE = "checked")   
		    	#end  
         
          		<div align="center">
        			<input type="checkbox" name="borangE" id="borangE" onClick="doUpdateCheckAllBorangE('$list_kehadiran.size()')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang E & F" $boxE value="$list.ID_HAKMILIKPB" >
        		</div>
        		
         	</td>
    
	        <td class="$rowx">
         
			    #set($boxG= "")    
			    
			    #if($list.FLAG_BORANGG == "1" )    
			    	#set($boxG = "checked")   
			    #end  
         
         		<div align="center">
        			<input type="checkbox" name="borangG" id="borangG" onClick="doUpdateCheckAllBorangG('$list_kehadiran.size()')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang G & H" $boxG value="$list.ID_HAKMILIKPB" >
        		</div>
         
         	</td>
         
         	<td class="$rowx">
         	
		         #set($boxK = "")    
		    		
		    	#if($list.FLAG_BORANGK == "1" )    
		    		#set($boxK = "checked")   
		    	#end 
          
          		<div align="center">
        			<input type="checkbox" name="borangK" id="borangK" onClick="doUpdateCheckAllBorangK('$list_kehadiran.size()')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang K" $boxK value="$list.ID_HAKMILIKPB" >
        		</div>
         
         	</td>
         	
            #end
         	<!-- <td>&nbsp;</td> -->
         	
   		</tr>
  		#end
  		
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
 



<script>

function paparpbbantahan(id_hakmilik,id_hakmilikpb,id_pihakberkepentingan,status_bantahan,id_permohonan,flag_skrin)
{
	try
	{
		if(flag_skrin=="bantahan_mahkamah")
		{			
			window.opener.papar(id_hakmilik,id_hakmilikpb,id_pihakberkepentingan,status_bantahan);
		}		
	}
	catch (err) {}
    window.close();
    return false;
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
			//window.opener.viewHM(id_hakmilik);
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
		else if(flag_skrin=="skrin_pb_sek8" || flag_skrin=="skrin_pb_PU")
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



function doCheckAllBorangK(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangK.checked == true){
        if (document.${formName}.borangK.length == null){
		cc++;
            document.${formName}.borangK.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangK.length; i++){
                document.${formName}.borangK[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangK.length == null){
            document.${formName}.borangK.checked = false;

        } else {
            for (i = 0; i < document.${formName}.borangK.length; i++){
                document.${formName}.borangK[i].checked = false;
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



function doUpdateCheckAllBorangK(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangK.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangK.length; i++)
	  {
      if (document.${formName}.borangK[i].checked == false)
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
if (document.${formName}.borangK.checked == false)
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
	  document.${formName}.allborangK.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangK.checked = true;
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



