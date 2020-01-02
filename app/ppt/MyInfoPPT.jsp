<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #0000FF}
-->
.pic-container {
    width: 100%;
    height: 200px;
    overflow-y: scroll;
    overflow-x:hidden;
}
</style>
   <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<!-- <div class="info">
    Hanya Pengarah yang boleh mengesahkan permohonan, membuat agihan tugas serta semakan MMK.
</div> -->

#set($portal_role = "${session.getAttribute('myrole')}")

#if($showEmelAlert=="yes")
<div class="warning">
	Sila kemaskini emel anda terlebih dahulu sebelum meneruskan sistem ini.
	<a href="javascript:kemaskiniProfil()"><font color="blue"><b>Klik sini</b></font></a>
	untuk kemaskini emel.      
</div>
#end

<!-- Show list online yang dah/nak expired 
#if($UserRole=="adminppt" || $UserRole=="(PPT)PengarahUnit" || $UserRole=="(PPT)KetuaPenolongPengarahUnit") #end -->
#parse("app/ppt/ListFailOnlineExpired.jsp")   

 
   
<table width="100%" border="0" cellspacing="2" cellpadding="2">
#if($listing_online_penarikan.size()>0 || $listing_online_pembatalan.size()>0 || $listing_online_permohonan.size()>0
 || $listing_online_bantahan_pb.size()>0 || $listing_online_bantahan_agensi.size()>0)

<tr>
    <td>
    <fieldset>
    <legend><b>Senarai Permohonan Online  
    <font style="cursor:help" align="left" class="font2" title="info" onMouseOut="close_window()"  onMouseOver="open_new_window('2');this.style.cursor='help'" >

#parse("app/ppt/infoblink_biru.jsp")
</font>
    </b></legend>
    
     #if($listing_online_permohonan.size()>0)
      <div #if($listing_online_permohonan.size()>10) class="pic-container" #end> 
    <table width="100%">
   
   
   <tr>
   <td colspan="7"> <strong>Permohonan Seksyen 8, Seksyen 4 dan Pendudukan Sementara</strong></td>
   </tr>
   
   <tr class="table_header">
   
          
              <td scope="row" width="3%" align="center">BIL</td>
              <td width="23%">NO RUJUKAN ONLINE</td>
              <td width="15%">NO FAIL PTG</td>
              <td width="15%">NO FAIL PTD</td>
              <td width="15%">NO FAIL UPT</td>             
              <td width="15%"><div align="center">TARIKH PERMOHONAN KJP</div></td>
              <td width="15%">URUSAN</td>
             
              
            </tr>
   
           
           #foreach($list in $listing_online_permohonan)
            #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
          <tr>
            <td class="$row" align="center">
     $i
			
            </td>
            
            #set($flagStatusOnline="")
            #if($list.FLAG_STATUS_ONLINE=="1")
            	 #set($flagStatusOnline="<br/><font style='font-size:10px'><b><i>(PERMOHONAN TELAH DIKEMBALIKAN)</i></b></font>")
            #else
            	 #set($flagStatusOnline="")
            #end
            
            <td class="$row">
            #if($list.NO_PERMOHONAN_ONLINE == '')
            	<a href="javascript:papar('$list.ID_PERMOHONAN','138','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">TIADA NO FAIL ONLINE </a>
            #else
            	<a href="javascript:papar('$list.ID_PERMOHONAN','138','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$!list.NO_PERMOHONAN_ONLINE.toUpperCase() $!flagStatusOnline</a>
            #end
             </td>
            <td class="$row">
            <a href="javascript:papar('$list.ID_PERMOHONAN','138','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','138','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','138','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_UPT.toUpperCase()</a>
            </td>
            <td class="$row"><div align="center">$list.TARIKH_PERMOHONAN_KJP</div></td>
           
            <td class="$row">$list.URUSAN.toUpperCase()</td>
           
            
            </tr>
            
            #end
            
    </table>
    </div>
    
    <br />
 
 #end
    
    #if($listing_online_bantahan_agensi.size()>0)
     <div #if($listing_online_bantahan_agensi.size()>10) class="pic-container" #end> 
    <table width="100%">
   
   
   <tr>
   <td colspan="7"> <strong>Bantahan Mahkamah dari Agensi Pemohon</strong></td>
   </tr>
   
   <tr class="table_header">
   
          
              <td scope="row" width="3%" align="center">BIL</td>
              <td width="23%">NO FAIL JKPTG</td>
              <td width="15%">NO FAIL PTG</td>
              <td width="15%">NO FAIL PTD</td>
              <td width="15%">NO FAIL UPT</td>             
              <td width="15%"><div align="center">TARIKH PERMOHONAN DITERIMA</div></td>
              <td width="15%">URUSAN</td>
             
              
            </tr>
   
           
           #foreach($list in $listing_online_bantahan_agensi)
            #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
          <tr>
            <td class="$row" align="center">
     $i
			
            </td>
            
            <td class="$row">
            #if($list.NO_JKPTG == '')
            	<a href="javascript:papar('$list.ID_PERMOHONAN','199','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">TIADA NO FAIL JKPTG </a>
            #else
            	<a href="javascript:papar('$list.ID_PERMOHONAN','199','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_JKPTG.toUpperCase()</a>
            #end
             </td>
            <td class="$row">
            <a href="javascript:papar('$list.ID_PERMOHONAN','199','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','199','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','199','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_UPT.toUpperCase()</a>
            </td>
            <td class="$row"><div align="center">$list.TARIKH_PERMOHONAN</div></td>
           
            <td class="$row">$list.URUSAN.toUpperCase()</td>
           
            
            </tr>
            
            #end
            
    </table>
    </div>
    
    <br />
 
 #end
 
 #if($listing_online_bantahan_pb.size()>0)
  <div #if($listing_online_bantahan_pb.size()>10) class="pic-container" #end> 
     <table width="100%">
   
    <tr>
   <td colspan="7"> <strong>Bantahan Mahkamah dari Pihak Berkepentingan</strong></td>
   </tr>
   
   
   <tr class="table_header">
          
              <td scope="row" width="3%" align="center">BIL</td>
              <td width="23%">NO FAIL JKPTG</td>
              <td width="15%">NO FAIL PTG</td>
              <td width="15%">NO FAIL PTD</td>
              <td width="15%">NO FAIL UPT</td>             
              <td width="15%"><div align="center">TARIKH PERMOHONAN DITERIMA</div></td>
              <td width="15%">URUSAN</td>
             
              
            </tr>
   
           
           #foreach($list in $listing_online_bantahan_pb)
            #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
          <tr>
            <td class="$row" align="center">
     $i
			
            </td>
            
            <td class="$row">
            #if($list.NO_JKPTG == '')
            	<a href="javascript:papar('$list.ID_PERMOHONAN','181','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">TIADA NO FAIL JKPTG </a>
            #else
            	<a href="javascript:papar('$list.ID_PERMOHONAN','181','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_JKPTG.toUpperCase()</a>
            #end
             </td>
            <td class="$row">
            <a href="javascript:papar('$list.ID_PERMOHONAN','181','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','181','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','181','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_UPT.toUpperCase()</a>
            </td>
            <td class="$row"><div align="center">$list.TARIKH_PERMOHONAN</div></td>
           
            <td class="$row">$list.URUSAN.toUpperCase()</td>
           
            
            </tr>
            
            #end
            
    </table>
    </div>
    <br />
    #end
    
    
     #if($listing_online_penarikan.size()>0)
  <div #if($listing_online_penarikan.size()>10) class="pic-container" #end> 
     <table width="100%">
   
    <tr>
   <td colspan="7"> <strong>Permohonan Penarikan Balik Hakmilik</strong></td>
   </tr>
   
   
   <tr class="table_header">
          
              <td scope="row" width="3%" align="center">BIL</td>
              <td width="23%">NO FAIL JKPTG</td>
              <td width="15%">NO FAIL PTG</td>
              <td width="15%">NO FAIL PTD</td>
              <td width="15%">NO FAIL UPT</td>             
              <td width="15%"><div align="center">TARIKH PERMOHONAN DITERIMA</div></td>
              <td width="15%">URUSAN</td>
             
              
            </tr>
   
           
           #foreach($list in $listing_online_penarikan)
            #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
          <tr>
            <td class="$row" align="center">
     $i
			
            </td>
            
            <td class="$row">
            #if($list.NO_JKPTG == '')
            	<a href="javascript:papar('$list.ID_PERMOHONAN','74','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">TIADA NO FAIL JKPTG </a>
            #else
            	<a href="javascript:papar('$list.ID_PERMOHONAN','74','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_JKPTG.toUpperCase()</a>
            #end
             </td>
            <td class="$row">
            <a href="javascript:papar('$list.ID_PERMOHONAN','74','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','74','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','74','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_UPT.toUpperCase()</a>
            </td>
            <td class="$row"><div align="center">$list.TARIKH_PERMOHONAN</div></td>
           
            <td class="$row">$list.URUSAN.toUpperCase()</td>
           
            
            </tr>
            
            #end
            
    </table>
    </div>
    <br />
    #end
    
    
    #if($listing_online_pembatalan.size()>0)
 <div #if($listing_online_pembatalan.size()>10) class="pic-container" #end> 
     <table width="100%">
   
    <tr>
   <td colspan="7"> <strong>Permohonan Pembatalan Hakmilik</strong></td>
   </tr>
   
   
   <tr class="table_header">
          
              <td scope="row" width="3%" align="center">BIL</td>
              <td width="23%">NO FAIL JKPTG</td>
              <td width="15%">NO FAIL PTG</td>
              <td width="15%">NO FAIL PTD</td>
              <td width="15%">NO FAIL UPT</td>             
              <td width="15%"><div align="center">TARIKH PERMOHONAN DITERIMA</div></td>
              <td width="15%">URUSAN</td>
             
              
            </tr>
   
           
           #foreach($list in $listing_online_pembatalan)
            #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
          <tr>
            <td class="$row" align="center">
     $i
			
            </td>
            
            <td class="$row">
            #if($list.NO_JKPTG == '')
            	<a href="javascript:papar('$list.ID_PERMOHONAN','235','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">TIADA NO FAIL JKPTG </a>
            #else
            	<a href="javascript:papar('$list.ID_PERMOHONAN','235','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_JKPTG.toUpperCase()</a>
            #end
             </td>
            <td class="$row">
            <a href="javascript:papar('$list.ID_PERMOHONAN','235','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','235','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','235','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_UPT.toUpperCase()</a>
            </td>
            <td class="$row"><div align="center">$list.TARIKH_PERMOHONAN</div></td>
           
            <td class="$row">$list.URUSAN.toUpperCase()</td>
           
            
            </tr>
            
            #end
            
    </table>
    </div>
    <br />
    #end
    
    
    </fieldset>
    </td>
    </tr>
  
    #end
  <tr>
    <td>
      <br>
    <fieldset><legend><b>Carian</b></legend>
        <table width="100%" align="center" border="0">
          <tbody>
          
            <tr>
            
             <td width="30%"  scope="row" align="right">Tajuk : </td>
              <td width="70%" colspan="2"><input name="namaprojek" id="namaprojek" type="text" value="$namaprojek" size="50" maxlength="50" style="text-transform:uppercase;" > 
              </td></tr>
         <tr>
              <td width="30%"  scope="row" align="right">No Fail : </td>
              <td width="70%" colspan="2"><input name="no_fail" id="no_fail" type="text" value="$no_fail" size="50" maxlength="50" style="text-transform:uppercase;" > 
         
             <!-- shaz -->
            
             <!-- elly -->
             <input type="hidden" name="id_fail" />
             
             <!-- razman -->
			 <input type="hidden" name="id_permohonan" />			
             <input type="hidden" name="flagAdvSearch" value="$flagAdvSearch"/>
             <input type="hidden" name="flag_MyInfoPPT"/></td>
            </tr>
            <tr>
            
            
                 
            
              <td width="30%" height="24" scope="row" align="right">Tarikh Permohonan Diterima : </td>
              <td width="70%" colspan="2"><input type="text" name="tarikh_permohonan" id="tarikh_permohonan" onblur="check_date(this)" value="$tarikh_permohonan" size="9"/>
             <a href="javascript:displayDatePicker('tarikh_permohonan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>   
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>               </td>
            </tr>
         
    	<tr>

    		  <td width="30%" height="24" scope="row" align="right">Tahun Permohonan : </td>
    		<td>$!selectTahun
    		  <input type="hidden" name="id_tahun" id="id_tahun" value="$!id_tahun"></td>
    		
    		
    	</tr>
           
            
            <tr>
              <td width="30%" height="24" scope="row" align="right">Status Permohonan : </td>
              <td width="70%" colspan="2">
             
              <select name="id_status_permohonan" class="autoselect" id="id_status_permohonan"  >  
   
   
    #if($!id_status_permohonan != "")
    
    #foreach($ln in $list_status)   
    #if($!id_status_permohonan==$ln.ID_STATUS)
    <option value="$ln.ID_STATUS">$ln.KOD_STATUS - $ln.KETERANGAN</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_status)   
    #if($!id_status_permohonan!=$ln.ID_STATUS)
    <option value="$ln.ID_STATUS">$ln.KOD_STATUS - $ln.KETERANGAN</option>                                     
    #end      
    #end
   <option value="">SILA PILIH STATUS TERKINI PERMOHONAN</option>    
    
    #else
   
    <option value="">SILA PILIH STATUS TERKINI PERMOHONAN</option>        
    #foreach($ln in $list_status)   
    <option value="$ln.ID_STATUS">$ln.KOD_STATUS - $ln.KETERANGAN</option>   
    #end
    
    #end
    
    </select>
              
              
               &nbsp;&nbsp;
              #if ($flagAdvSearch == '')
                <a href="#" title="More" class="style2" onclick="javascript:more()">Buka Carian Terperinci</a> 
              #end
              #if ($flagAdvSearch == 'open') <a href="#" title="Less" class="style2" onclick="javascript:less()">Tutup Carian Terperinci</a> 
              #end              </td>
            </tr>
            
            
            
            
            #if ($flagAdvSearch == 'open')
            
            <tr>
              	<td width="30%" height="24" scope="row" align="right">Menunggu keputusan pengarah : </td>
              	<td width="70%" colspan="2">
              			<select name="socJenisKeputusan" id="socJenisKeputusan" style="width:200px">
              				
              				#if($socJenisKeputusan=="1")
                    		<option value="1">PENGESAHAN PERMOHONAN</option>
                    		<option value="2">AGIHAN TUGAS</option>
                    		<option value="3">SEMAKAN MMK</option>
                    		<option value="0">SILA PILIH</option>
              				#elseif($socJenisKeputusan=="2")
              				<option value="2">AGIHAN TUGAS</option>
              				<option value="1">PENGESAHAN PERMOHONAN</option>
                    		<option value="3">SEMAKAN MMK</option>
                    		<option value="0">SILA PILIH</option>
              				#elseif($socJenisKeputusan=="3")
              				<option value="3">SEMAKAN MMK</option>
              				<option value="1">PENGESAHAN PERMOHONAN</option>
                    		<option value="2">AGIHAN TUGAS</option>
                    		<option value="0">SILA PILIH</option>
              				#else
              				<option value="0">SILA PILIH</option>
                    		<option value="1">PENGESAHAN PERMOHONAN</option>
                    		<option value="2">AGIHAN TUGAS</option>
                    		<option value="3">SEMAKAN MMK</option>
              				#end
							
                    	</select>
				</td>
            </tr>
            
            <tr>
              <td width="30%" height="24" scope="row" align="right">No. / Bil. Permohonan : </td>
              <td width="70%" colspan="2">
                <input name="no_permohonan" id="no_permohonan" type="text" value="$no_permohonan" size="20" maxlength="25" style="text-transform:uppercase;" ></td>
            </tr>
        
            <tr>
              <td width="30%" height="24" scope="row" align="right">Negeri Permohonan : </td>
              <td width="70%" colspan="2">$!selectNegeri
   
   <!-- <select name="id_negeri_permohonan" class="autoselect" id="id_negeri_permohonan"  >  
   
   
    #if($!id_negeri_permohonan != "")
    
    #foreach($ln in $list_negeri)   
    #if($!id_negeri_permohonan==$ln.ID_NEGERI)
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_negeri)   
    #if($!id_negeri_permohonan!=$ln.ID_NEGERI)
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>                                     
    #end      
    #end
   <option value="">SILA PILIH NEGERI PERMOHONAN</option>    
    
    #else
   
    <option value="">SILA PILIH NEGERI PERMOHONAN</option>        
    #foreach($ln in $list_negeri)   
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>   
    #end
    
    #end
    
    </select> -->        
              
              
                      </td>
            </tr>
            
             <tr>
              <td width="30%" height="24" scope="row" align="right">Nama Kementerian : </td>
              <td width="70%" colspan="2">
               <select name="id_kementerian" class="autoselect" id="id_kementerian"  >     
               #if($!id_kementerian != "")
    
    #foreach($ln in $list_kementerian)   
    #if($!id_kementerian==$ln.ID_KEMENTERIAN)
    <option value="$ln.ID_KEMENTERIAN">$ln.KOD_KEMENTERIAN - $ln.NAMA_KEMENTERIAN</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_kementerian)   
    #if($!id_kementerian!=$ln.ID_KEMENTERIAN)
    <option value="$ln.ID_KEMENTERIAN">$ln.KOD_KEMENTERIAN - $ln.NAMA_KEMENTERIAN</option>                                     
    #end      
    #end
   <option value="">SILA PILIH KEMENTERIAN</option>    
    
    #else
   
    <option value="">SILA PILIH KEMENTERIAN</option>        
    #foreach($ln in $list_kementerian)   
    <option value="$ln.ID_KEMENTERIAN">$ln.KOD_KEMENTERIAN - $ln.NAMA_KEMENTERIAN</option>   
    #end
    
    #end
    
    </select>              </td>
            </tr>
           
    
            <tr>
              <td width="30%" height="24" scope="row" align="right">Nama Pihak Berkepentingan : </td>
              <td width="70%" colspan="2"><input name="nama_pb" id="nama_pb" type="text" value="$nama_pb" size="50" maxlength="300" style="text-transform:uppercase;" ></td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right">No. Pihak Bekepentingan : </td>
              <td width="70%" colspan="2">
                <input name="no_pb" id="no_pb" type="text" value="$no_pb" size="20" maxlength="25" style="text-transform:uppercase;" >
                 <i><font color='red' style='font-size:10px'> cth: 700121034429/I88820</font></i></td>
            </tr>
            
            <tr>
              <td width="30%" height="24" scope="row" align="right">No. Akaun Bank Pihak Bekepentingan : </td>
              <td width="70%" colspan="2">
                <input name="no_akaun" id="no_akaun" type="text" value="$no_akaun" size="20" maxlength="30" style="text-transform:uppercase;" ></td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right">Jenis Hakmilik : </td>
              <td width="70%" colspan="2">$!selectJenisHakmilik
              
              <!-- <select name="id_jenishakmilik" class="autoselect" id="id_jenishakmilik"  >     
            #if($!id_jenishakmilik != "")
    
    #foreach($ln in $jenis_hakmilik)   
    #if($!id_jenishakmilik==$ln.ID_JENISHAKMILIK)
    <option value="$ln.ID_JENISHAKMILIK">$ln.KOD_JENIS_HAKMILIK - $ln.KETERANGAN</option>                                     
    #end 
    #end 
   
    #foreach($ln in $jenis_hakmilik)   
    #if($!id_jenishakmilik!=$ln.ID_JENISHAKMILIK)
    <option value="$ln.ID_JENISHAKMILIK">$ln.KOD_JENIS_HAKMILIK - $ln.KETERANGAN</option>                                     
    #end      
    #end
   <option value="">SILA PILIH JENIS HAKMILIK</option>    
    
    #else
   
    <option value="">SILA PILIH JENIS HAKMILIK</option>        
    #foreach($ln in $jenis_hakmilik)   
    <option value="$ln.ID_JENISHAKMILIK">$ln.KOD_JENIS_HAKMILIK - $ln.KETERANGAN</option>   
    #end
    
    #end
    
    </select> -->
                                         </td>
            </tr>
            
     
            
            <tr>
              <td width="30%" height="24" scope="row" align="right">No. Hakmilik : </td>
              <td width="70%" colspan="2">
                <input name="no_hakmilik" id="no_hakmilik" type="text" value="$no_hakmilik" size="20" maxlength="50" style="text-transform:uppercase;" >
                <i><font color='red' style='font-size:10px'>(Tidak termasuk kod hakmilik)</font></i></td>
            </tr>
          
		    <tr>
		      <td height="24" scope="row" align="right">No. Lot/PT : </td>
		      <td colspan="2"><input name="no_lot_pt" id="no_lot_pt" type="text" value="$no_lot_pt" size="50" maxlength="50" style="text-transform:uppercase;" /></td>
	      </tr>
          
	      <tr>
		  <td width="30%" height="24" scope="row" align="right">No. Perbicaraan/Siasatan : </td>
		  <td width="70%" colspan="2"><input name="no_siasatan" id="no_siasatan" type="text" value="$!no_siasatan" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
            </tr>
             
             
           <tr>
           		<td scope="row" align="right">Pampasan Kepada : </td>
           		<td colspan="2">
		           <select id="socflagJenisPampasan" class="autoselect" name="socflagJenisPampasan">  
		           	
		           	#if($flagJenisPampasan=="1")
		           	<option value="1">PIHAK BERKEPENTINGAN YANG BERKENAAN</option>
		    		<option value="2">MAHKAMAH</option>
		    		<option value="3">AMANAH RAYA</option> 
		    		<option value="">SILA PILIH</option>
		           	#elseif($flagJenisPampasan=="2")
		           	<option value="2">MAHKAMAH</option>
		            <option value="1">PIHAK BERKEPENTINGAN YANG BERKENAAN</option>
		    		<option value="3">AMANAH RAYA</option> 
		    		<option value="">SILA PILIH</option>
		           	#elseif($flagJenisPampasan=="3")
		           	<option value="3">AMANAH RAYA</option> 
		            <option value="1">PIHAK BERKEPENTINGAN YANG BERKENAAN</option>
		    		<option value="2">MAHKAMAH</option>
		    		<option value="">SILA PILIH</option>
		           	#else
		           	<option value="">SILA PILIH</option>
		            <option value="1">PIHAK BERKEPENTINGAN YANG BERKENAAN</option>
		    		<option value="2">MAHKAMAH</option>
		    		<option value="3">AMANAH RAYA</option> 
		           	#end
		           	
		           </select>
    			</td>
           </tr>  
             
            <tr>
             <td width="30%" height="24" scope="row" align="right">No. Kes : </td>
		  <td width="70%" colspan="2"><input name="no_kes" id="no_kes" type="text" value="$!no_kes" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
            </tr>
            
             <tr>
      <td width="30%" height="24" scope="row" align="right">No. Ruj. Prosiding Tanah : </td>
<td width="70%" colspan="2"><input name="no_prosiding" id="no_prosiding" type="text" value="$!no_prosiding" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
            </tr>
            
	
        
            <tr>
              <td scope="row" valign="top"><div align="right">Nama Projek :</div></td>
              <td colspan="2">
              
              
               <textarea name="namaprojek" id="namaprojek" cols="80"   rows="8"          
         onBlur="check_length(this,'4000','namaprojek_check','namaprojek_num','normal','no','nama projek');"  
         onKeyup="check_length(this,'4000','namaprojek_check','namaprojek_num','normal','no','nama projek');" 
         onKeydown="check_length(this,'4000','namaprojek_check','namaprojek_num','normal','no','nama projek');" >$!namaprojek</textarea>
              
        <div><span id="namaprojek_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
        
  <div id="namaprojek_check" class="alert_msg" ></div> 
              
              
              </td>
            </tr>
            
            
             <tr>
              <td width="30%" height="24" scope="row" align="right">Nama Pendaftar Permohonan : </td>
              <td width="70%" colspan="2">$!selectPegawai
   
   <!-- <select name="id_pegawai_pendaftar" class="autoselect" id="id_pegawai_pendaftar"  >  
   
   
    #if($!id_pegawai_pendaftar != "")
    
    #foreach($ln in $list_pegawai)   
    #if($!id_pegawai_pendaftar==$ln.ID_PEGAWAI)
    <option value="$ln.ID_PEGAWAI">$ln.USER_NAME</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_pegawai)   
    #if($!id_pegawai_pendaftar!=$ln.ID_PEGAWAI)
    <option value="$ln.ID_PEGAWAI">$ln.USER_NAME</option>                                     
    #end      
    #end
   <option value="">SILA PEGAWAI PENDAFTAR</option>    
    
    #else
   
    <option value="">SILA PEGAWAI PENDAFTAR</option>        
    #foreach($ln in $list_pegawai)   
    <option value="$ln.ID_PEGAWAI">$ln.USER_NAME</option>   
    #end
    
    #end
    
    </select> -->        
              
              
                      </td>
            </tr>
            
            
               #end
            <tr>
              <td scope="row"></td>
              <td colspan="2"><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan('$flagAdvSearch')"></td>
            </tr>
            <tr>
              <td scope="row">&nbsp;</td>
              <td colspan="2">&nbsp;</td>
            </tr>
          </tbody>
        </table>
	  </fieldset>
    </td>
  </tr>
  <tr>
    <td><fieldset>
		<legend><b>Senarai Fail</b></legend>
		&nbsp;Jumlah Keseluruhan Fail : $JumlahFail<br>
		#set ($pagingTitle = "Jumlah Carian")
		#parse("app/utils/record_paging.jsp")
        
        
        
        <table align="center" width="100%"> 
          <tbody>
          <tr>
          <td colspan="10">
          #if($portal_role == "adminppt" ) 
<input name="" type="button" value="Hapus Fail" onClick="hapus_beramai()">

 <font style="cursor:help" align="left" class="font2" title="info" onMouseOut="close_window()"  onMouseOver="open_new_window('1');this.style.cursor='help'" >

#parse("app/ppt/infoblink_biru.jsp")
</font>

#end
          </td>
          </tr>
          
            <tr class="table_header">
          
              <td scope="row" width="3%" align="center">BIL</td>
              <td>NO FAIL JKPTG</td>
              <td>NO FAIL PTG</td>
              <td>NO FAIL PTD</td>
              <td>NO FAIL UPT</td>
              <td>KEMENTERIAN DAN NAMA PROJEK</td>
              <td>TARIKH PERMOHONAN DITERIMA</td>
              <td>STATUS</td>
              <td>URUSAN</td>
              <td>DIDAFTAR OLEH</td>
              <td>PENTADBIR</td>
              
   #if($portal_role == "adminppt")  <td  width="5%"> 
      <div align="center">
      <input type="checkbox" name="all1" id="all1" onClick="doCheckAll1();" title="Semak untuk hapuskan semua fail" />
      </div></td>
      #end
              
            </tr>
          #set ($list = "")
          #set ($counter = 1)
          #foreach ($list in $SenaraiFail)
          #set( $counter = $velocityCount - 1 )
          	#if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
           
          <tr>
            <td class="$row" align="center">
     		
			#set ($cnt = ($page - 1) * $itemsPerPage + $counter + 1)
            $cnt
            
            </td>
            
          	#set($status_fail = "$list.STATUS.toUpperCase()")
         	
         	#if($list.ID_STATUS=="11" && $list.FLAG_SEMAK=="1")
         	    #if($list.ID_SUBURUSAN=="51")
         	    	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b><i>&nbsp;<blink>TUNGGU PENGESAHAN DAN AGIHAN</blink></i></b>")
         	    #else
         	    	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b><i>&nbsp;<blink>TUNGGU PENGESAHAN</blink></i></b>")
         	    #end
         	#elseif($list.ID_STATUS=="127" && $list.FLAG_SEMAK=="2")
            	#set($status_fail = "<b><i><blink>TELAH DISAHKAN</blink></i></b>")
            #elseif($list.ID_STATUS=="16")
            	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b><i>&nbsp;<blink>TUNGGU UNTUK DIAGIHAN</blink></i></b>")	
            #elseif($list.ID_STATUS=="1612198")
            	#set($status_fail = "<b><i><blink>TINDAKAN PENTADBIR TANAH</blink></i></b>")	
            #elseif($list.ID_STATUS=="148")
            	#set($status_fail = "<b><i><blink>TELAH DIAGIHKAN</blink></i></b>")	
            #elseif(($list.ID_STATUS=="132" && $list.FLAG_SEMAKAN_PENGARAH=="1") || ($list.ID_STATUS=="26" && $list.FLAG_SEMAKAN_PENGARAH=="1"))
            	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b>&nbsp;<i><blink>TUNGGU SEMAKAN MMK</blink></i></b>")	
            #elseif(($list.ID_STATUS=="133" && $list.FLAG_SEMAKAN_PENGARAH=="2") || ($list.ID_STATUS=="26" && $list.FLAG_SEMAKAN_PENGARAH=="2"))
            	#set($status_fail = "<b><i><blink>TELAH DISEMAK</blink></i></b>")	
            	
            #elseif($list.ID_STATUS=="74" && $list.FLAG_MMK_PENARIKAN=="1")	
            	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b>&nbsp;<i><blink>TUNGGU SEMAKAN MMK (PENARIKAN BALIK)</blink></i></b>")		
            #elseif($list.ID_STATUS=="74" && $list.FLAG_MMK_PENARIKAN=="2" && $list.STATUS_KEPUTUSAN == "")	
            	#set($status_fail = "<b><i><blink>TELAH DISEMAK (PENARIKAN BALIK)</blink></i></b>")	
            #else
            	#set($status_fail = "$list.STATUS")
            #end
            
            
            
            
            <td class="$row">
            #if($list.NO_JKPTG == '')
            	<a href="javascript:papar('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('myrole')}')" class="style1">TIADA NO FAIL JKPTG </a>
            #else
            	<a href="javascript:papar('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('myrole')}')" class="style1">$list.NO_JKPTG.toUpperCase()</a>
            #end
             </td>
            <td class="$row">
            <a href="javascript:papar('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('myrole')}')" class="style1">$list.NO_RUJUKAN_PTG.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('myrole')}')" class="style1">$list.NO_RUJUKAN_PTD.toUpperCase()</a>
            </td>
            <td class="$row">
             <a href="javascript:papar('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('myrole')}')" class="style1">$list.NO_RUJUKAN_UPT.toUpperCase()</a>
            </td>
            <td class="$row"><font  color="blue"><b>$!list.NAMA_KEMENTERIAN</b></font><br/>$list.TUJUAN.toUpperCase()</td>
            <td class="$row">$list.TARIKH_PERMOHONAN.toUpperCase()</td>
            <td class="$row">$status_fail</td>
            <td class="$row">$list.URUSAN.toUpperCase()</td>
            <td class="$row">$list.USER_NAME.toUpperCase()</td>
            <td class="$row" align="center">
            #if($list.BIL_PEGAWAI_BERTUGAS!="0" || $list.BIL_PEGAWAI_BERTUGAS_BARU!="0") 
            <a href="javascript:popupPegawaiBertugas('$list.ID_PERMOHONAN')" class="style1">PAPAR</a>
            #else 
            TIADA
            #end
            </td>
            
            
#if($portal_role == "adminppt")
    <td class="$row"><div align="center">
       <input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1()" value="$list.ID_FAIL" title="Semak untuk hapuskan fail ini" >
     </div></td>
     #end 
            </tr>
          #end
         
           #if( $SenaraiFail.size() == 0)
            <tr>
            
            <td  colspan="10">
           Tiada rekod           </td>
           </tr>
           #end 
          
          </tbody>
        </table>
		</fieldset>
	</td>
  </tr>
</table>

<input type="hidden" name="id_penarikanbalik" />
<input type="hidden" name="id_pembatalan" />

<script>
function popupPegawaiBertugas(id_permohonan){
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmMyInfoPopupPegawaiBertugas?id_permohonan="+id_permohonan;
	var hWnd = window.open(url,'Senarai Pegawai Bertugas','width=800,height=400, resizable=yes,scrollbars=yes');	
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function hapusPembatalan(id_pembatalan){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_pembatalan.value=id_pembatalan;
	document.${formName}.command.value = "hapus_fail_online_pembatalan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.MyInfoPPT";	
	document.${formName}.submit();
}
function hapusKJP(id_fail){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_fail.value=id_fail;
	document.${formName}.command.value = "hapus_fail_online";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.MyInfoPPT";	
	document.${formName}.submit();
}
function hapusPenarikan(id_penarikanbalik){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_penarikanbalik.value=id_penarikanbalik;
	document.${formName}.command.value = "hapus_fail_online_penarikan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.MyInfoPPT";	
	document.${formName}.submit();
}

   if('$flagAdvSearch' == "open")
   {
   check_length(document.${formName}.namaprojek,'4000','namaprojek_check','namaprojek_num','normal','no','nama projek');
   }


   
function kemaskiniProfil(){
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=lebah_app_UpdateUserProfileModule";
	document.${formName}.submit();
}

function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{

	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (my_form.value.length >= maxLen) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
my_form.value = my_form.value.substring(0, maxLen);
 maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");
}



function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function more(){
	document.${formName}.flagAdvSearch.value = "open";
	document.${formName}.submit();
}
function less(){
	document.${formName}.flagAdvSearch.value = "";
	document.${formName}.submit();
}
function carian(){
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppt.MyInfoPPT";
	document.${formName}.submit();
}
function kosongkan(flag) {



	document.${formName}.reset();
	document.${formName}.no_fail.value = "";
	document.${formName}.namaprojek.value = "";
	document.${formName}.tarikh_permohonan.value = "";
	document.${formName}.id_status_permohonan.value = "";
	document.${formName}.socTahun.value = "";
	if (flag != ''){
		
		document.${formName}.no_hakmilik.value = "";
		//document.${formName}.id_jenishakmilik.value = "";
		document.${formName}.no_lot_pt.value = "";
		document.${formName}.nama_pb.value = "";
		document.${formName}.no_pb.value = "";
		document.${formName}.no_akaun.value = "";
		document.${formName}.no_kes.value = "";
		document.${formName}.no_siasatan.value = "";
		document.${formName}.tarikh_permohonan.value = "";
		document.${formName}.no_prosiding.value = "";
		document.${formName}.id_kementerian.value = "";
		document.${formName}.namaprojek.value = "";
		document.${formName}.no_permohonan.value = "";
		document.${formName}.socflagJenisPampasan.value = "";
		
		document.${formName}.socPegawai.value = "";
		document.${formName}.socJenisKeputusan.value = "0";
		document.${formName}.socNegeri.value = "";
		document.${formName}.socJenisHakmilik.value = "";
		
	}
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppt.MyInfoPPT";
	document.${formName}.submit();
}


function doCheckAll1(){    
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
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
}
function doUpdateCheckAll1(){  
var c = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
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


function hapus_beramai()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "hapus_fail";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.MyInfoPPT";	
	document.${formName}.submit();
	}
}

function open_new_window(jenis_popup) 
{
 var width  = 0;
 var height = 0;
if(jenis_popup == "1")
{
  width  = 300;
  height = 300;
}
if(jenis_popup == "2")
{
  width  = 300;
  height = 300;
}
if(jenis_popup == "3" || jenis_popup == "4")
{
  width  = 300;
  height = 200;
}

 var left   = '';
 var top    = '';
 var params = '';

if(jenis_popup == "4")
{
 left   = (screen.width  - width)/4;
 top    = (screen.height - height)/4;
 params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=yes';
 params += ', menubar=yes';
 params += ', toolbar=yes';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
 new_window = open("","title",params);
 new_window.document.open();
 new_window.document.write("<html><title>JavaScript New Window</title>");
 new_window.document.write("<body bgcolor=\"#FFFFFF\">");
}
else
{
 left   = (screen.width  - width)/2;
 top    = (screen.height - height)/2;
 params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=yes';
 params += ', menubar=yes';
 params += ', toolbar=yes';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
 new_window = open("","title",params);
 new_window.document.open();
 new_window.document.write("<html><title>JavaScript New Window</title>");
 new_window.document.write("<body bgcolor=\"#FFFFFF\">");
} 

if(jenis_popup == "2")
{
new_window.document.write("Senarai di bawah akan memaparkan segala permohonan online yang telah dibuat oleh KJP dan Pihak Berkepentingan (bagi urusan Bantahan Mahkamah). Urusan permohonan online melibatkan beberapa urusan didalam modul Pengambilan Tanah seperti permohonan Seksyen 8, Seksyen 4, Permohonan Pengunaan Sementara, Bantahan Mahkamah dan Pembatalan/Penarikan Balik.");
}

if(jenis_popup == "1")
{
new_window.document.write("Sila klik pada kotak pilihan yang disediakan untuk hapuskan fail-fail yang dikehendaki. Fungsi penghapusan fail ini hanya dibenarkan untuk penguna yang mempunyai peranan sebagai 'Administration of PPT'. Sila pastikan fail-fail yang hendak dihapuskan adalah telah mendapat persetujuan oleh pihak atasan. Setelah fail-fail tersebut dihapuskan, segala maklumat berkenaan fail tersebut tidak dapat dicapai lagi.");
}

if(jenis_popup == "4")
{
new_window.document.write("Fungsi 'Pengiraan Jumlah Pampasan Tanah' bertujuan untuk membuat pengiraan pampasan tanah secara automatik merujuk kepada bahagian/syer pihak berkepentingan, harga seunit dan luas tanah yang dikehendaki.");
}


if(jenis_popup == "x")
{
new_window.document.write("<table width = '100%' >")
new_window.document.write("<tr>")
new_window.document.write("<td colspan = '3'>")
new_window.document.write("Tujuan medan 'Keterangan Jenis PB' adalah untuk memudahkan pengguna untuk menyatakan sebarang keterangan merujuk kepada jenis pihak berkepentingan yang telah dipilih. Contoh : ");
new_window.document.write("</td>")
new_window.document.write("</tr>")
new_window.document.write("</table>")
new_window.document.write("<table width = '100%'  >")
new_window.document.write("<tr>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Jenis Pihak Berkepentingan");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write(":");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Pemegang Amanah");
new_window.document.write("</td>")
new_window.document.write("</tr>")
new_window.document.write("<tr>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Keterangan Jenis PB");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write(":");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Pemegang Amanah Kepada Ali bin Abu");
new_window.document.write("</td>")
new_window.document.write("</tr>")

new_window.document.write("</table>")
}

if(jenis_popup == "3")
{
new_window.document.write("Skrin 'Pihak Berkepentingan' adalah skrin baru yang ditempatkan di Urusan Siasatan & Perintah Seksyen 8. Tujuan skrin ini adalah untuk memudahkan pengguna untuk menambah, menghapus, mengemaskini dan menetapkan pilihan borang pada mana-mana  pihak bekepentingan.");
}

new_window.document.write("<br>");

new_window.document.write("</body></html>");
new_window.document.close(); 

}

function close_window() 
{
new_window.close();
}


</script>

#parse("app/ppt/MyInfoPPT_script.jsp")  