xxx
<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<script>var $jquery = jQuery.noConflict();</script>

<style type="text/css">
<!--
#parse("css/eTapp_PPT.css")
-->


</style>

<!--
#mainContainer {
display: block;
position: relative;
width:100%;
overflow:auto;
}
#pinnedFooter {
display: block;
position: fixed;
width:100%;
bottom: 0px;
}
-->


<div style="display:none;" >  

<input type="text" name="form_token_Pop" value='$!{session.getAttribute("form_token_Pop")}'>

id_fail :
<input type="text" name="id_fail" value="$!id_fail">
<br>
id_permohonan :
<input type="text" name="id_permohonan" value="$!id_permohonan">
<br>
id_hakmilik :
<input type="text" name="id_hakmilik" value="$!id_hakmilik">
<br>
jenis_skrin :
<input type="text" name="jenis_skrin" value="$!jenis_skrin">



<br>
id_penarikan :
<input type="text" name="id_penarikan" value="$!id_penarikan">
</div>

<!--
JKPTG(S).NSN/04/881/24/2010/1
untuk test data negeri 9 kat local






JKPTG(S).MLK/01/881/05/2013/1
BORANG B

JKPTG(S).MLK/01/881/18/2010/10
BORANG D,C,K,PU


JKPTG(S).MLK/01/881/01/2013/2 
BORANG I

penarikan balik melaka
JKPTG(S).MLK/02/881/04/2010/9-->
 
 
  #set($tajuk_popup = "")
  #if($!jenis_skrin == "BorangC")         
  	#set($tajuk_popup = "Skrin Integrasi Borang C")
  #elseif($!jenis_skrin == "BorangA")         
  	#set($tajuk_popup = "Skrin Integrasi Borang A")
  #elseif($!jenis_skrin == "MMK_S8")         
  	#set($tajuk_popup = "Skrin Integrasi Deraf MMK (Syor Pentadbir Tanah)")
  #elseif($!jenis_skrin == "MMK_S4")         
  	#set($tajuk_popup = "Skrin Integrasi Deraf MMK (Syor Pentadbir Tanah)")
  #elseif($!jenis_skrin == "BorangK")         
  	#set($tajuk_popup = "Skrin Integrasi Borang K")
  #elseif($!jenis_skrin == "hantarPelanChartingS8")         
  	#set($tajuk_popup = "Skrin Muat Naik Pelan Charting (Seksyen 8)")
  #elseif($!jenis_skrin == "hantarPelanChartingS4")         
  	#set($tajuk_popup = "Skrin Muat Naik Pelan Charting (Seksyen 4)")
  #elseif($!jenis_skrin == "PU")         
  	#set($tajuk_popup = "Skrin Integrasi Pelan PA & B1")  
  #elseif($!jenis_skrin == "SijilUkur")         
  	#set($tajuk_popup = "Skrin Hantar Sijil Pembebasan Ukur")  
  #elseif($!jenis_skrin == "BorangI")         
  	#set($tajuk_popup = "Skrin Integrasi Borang I")  
  #elseif($!jenis_skrin == "TarikBalik")         
  	#set($tajuk_popup = "Skrin Integrasi Penarikan Balik")  
  #elseif($!jenis_skrin == "WartaS4")         
  	#set($tajuk_popup = "Skrin Integrasi Hantar Borang B & Maklumat Warta")
  #elseif($!jenis_skrin == "WartaS8")         
  	#set($tajuk_popup = "Skrin Integrasi Hantar Borang D & Maklumat Warta")  
  #end
 
 
 

<div id="divAllPopup">
 <fieldset>
      <legend><strong><font color="white">$tajuk_popup</font></strong></legend>
<div id="mainContainer">
#parse("app/integrasi/etanah/pengambilanTanah/PopupPengambilanTanah_maklumbalas.jsp") 
#parse("app/integrasi/etanah/pengambilanTanah/PopupPengambilanTanah_status_log.jsp")      



<table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<strong>Maklumat Permohonan</strong>
 </td>
</tr>
</table>

<table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
<tr>
<td width="1%"></td>
<td width="20%">No. Fail JKPTG</td>
<td width="1%">:</td>
<td width="78%">$!hash_maklumatprojek.NO_FAIL</td>
</tr>
<tr>
<td  valign="top"></td>
<td  valign="top">Kementerian</td>
<td   valign="top">:</td>
<td  valign="top">$!hash_maklumatprojek.NAMA_KEMENTERIAN</td>
</tr>
<tr>
<td  valign="top"></td>
<td  valign="top">Projek</td>
<td   valign="top">:</td>
<td  valign="top">$!hash_maklumatprojek.NAMA_PROJEK</td>
</tr>
</tr>
<tr>
<td  valign="top"></td>
<td  valign="top">No Permohonan E-TANAH</td>
<td   valign="top">:</td>
<td  valign="top">$!hash_maklumatintPermohonan.NO_PERMOHONAN</td>
</tr>
#if($!jenis_skrin == "BorangC" || $!jenis_skrin == "BorangK" || $!jenis_skrin == "PU" || $!jenis_skrin == "SijilUkur")   
#if($!hash_maklumatWarta.size() >0)           
<tr>
<td  valign="top"></td>
<td  valign="top">No. Warta</td>
<td   valign="top">:</td>
<td  valign="top">$!hash_maklumatWarta.NO_WARTA</td>
</tr>
<tr>
<td  valign="top"></td>
<td  valign="top">Tarikh Warta</td>
<td   valign="top">:</td>
<td  valign="top">$!hash_maklumatWarta.TARIKH_WARTA</td>
</tr>
#end 
#elseif($!jenis_skrin == "WartaS4" || $!jenis_skrin == "WartaS8")   
#set($check_maklumat_warta = "Y") 
<tr>
<td  valign="top"></td>
<td  valign="top"><font color="blue"><b>No. Warta</b></font></td>
<td   valign="top">:</td>
<td  valign="top">
#if($!hash_maklumatWarta.NO_WARTA != "")                          
    <font color="blue"><b>$!hash_maklumatWarta.NO_WARTA</b></font>
#else
    <font color="red">Sila Pastikan Maklumat Ini Diisi!</font>
    #set($check_maklumat_warta = "X") 
#end  
</td>
</tr>
<tr>
<td  valign="top"></td>
<td  valign="top"><font color="blue"><b>Tarikh Warta</b></font></td>
<td   valign="top">:</td>
<td  valign="top">
#if($!hash_maklumatWarta.TARIKH_WARTA != "")                          
     <font color="blue"><b>$!hash_maklumatWarta.TARIKH_WARTA</b></font>
#else
     <font color="red">Sila Pastikan Maklumat Ini Diisi!</font>
     #set($check_maklumat_warta = "X") 
#end
</td>
</tr>
#end
</table>
<br />
 
 

 
#if($!jenis_skrin == "BorangK" || $!jenis_skrin == "SijilUkur")
<table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<strong>Maklumat Hakmilik</strong>
 </td>
</tr>
</table>
<table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
<tr>
<td width="1%"></td>
<td width="20%">No. Hakmilik</td>
<td width="1%">:</td>
<td width="78%"><span >$!hash_maklumatBorangK.KOD_JENIS_HAKMILIK $hash_maklumatBorangK.NO_HAKMILIK</span></td>
</tr>
<tr>
<td  valign="top"></td>
          <td  valign="top">No. Lot</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$!hash_maklumatBorangK.NO_LOT</span></td>
        </tr>
        
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Luas Asal</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$!hash_maklumatBorangK.LUAS_ASAL_DISPLAY</span></td>
        </tr>
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Luas Ambil</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$!hash_maklumatBorangK.LUAS_AMBIL_DISPLAY</span></td>
        </tr>
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Tarikh Borang K</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$!hash_maklumatBorangK.TARIKH_BORANGK</span></td>
        </tr>
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Jenis Pengambilan</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >#if($!hash_maklumatBorangK.STATUS_AMBIL == "P")
                           KESELURUHAN
                           #elseif($!hash_maklumatBorangK.STATUS_AMBIL == "S")
                           SEBAHAGIAN
                           #else
                           
           #end </span></td>
        </tr>
        
       
        
     
      </table>
    
 
 <br />

#end
 
 
 
#if($!jenis_skrin == "PU")
 
  <table border="0" width="100%"  class="dashboard_sub" cellspacing="0" cellpadding="0" >
  <tr>
  <td width="50%" valign="top">
  
 <table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<strong>Maklumat Hakmilik</strong>
 </td>
</tr>
</table>

      <table width="100%" border="0" cellspacing="2" cellpadding="2" >
      
      
			        	<tr>
			        	  <td width="1%"></td>
			              <td width="40%">No. Hakmilik</td>
			              <td width="1%">:</td>
			              <td width="58%"><span >$!hash_maklumatBorangK.KOD_JENIS_HAKMILIK $!hash_maklumatBorangK.NO_HAKMILIK</span></td>
			          	</tr>
                        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">No. Lot</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$!hash_maklumatBorangK.NO_LOT</span></td>
        </tr>
        
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Luas Asal</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$!hash_maklumatBorangK.LUAS_ASAL_DISPLAY</span></td>
        </tr>
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Luas Ambil</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$!hash_maklumatBorangK.LUAS_AMBIL_DISPLAY</span></td>
        </tr>
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Tarikh Borang K</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$!hash_maklumatBorangK.TARIKH_BORANGK</span></td>
        </tr>
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Jenis Pengambilan</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >#if($!hash_maklumatBorangK.STATUS_AMBIL == "P")
                           KESELURUHAN
                           #elseif($!hash_maklumatBorangK.STATUS_AMBIL == "S")
                           SEBAHAGIAN
                           #else
                           
           #end </span></td>
        </tr>
        
       
        
     
      </table>
  
  
  </td> 
  <td width="50%" valign="top">
 
#set($check_maklumat_PU = "Y") 
<table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<strong>Maklumat PU</strong></td>
</tr>
</table>

      <table width="100%" border="0" cellspacing="2" cellpadding="2" >
      
      
			        	<tr>
			        	  <td width="1%"></td>
			              <td width="40%">No. PU</td>
			              <td width="1%">:</td>
			              <td width="58%">
                         
                          #if($!hash_maklumatPU.NO_PU != "")                          
                          	$!hash_maklumatPU.NO_PU
                          #else
                          <font color="red">Sila Pastikan Maklumat Ini Diisi!</font>
                          #set($check_maklumat_PU = "X") 
                          #end                     
                         
                          </td>
			          	</tr>
                        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">No. PA</td>
          <td   valign="top">:</td>
          <td  valign="top">
          
           #if($!hash_maklumatPU.NO_PA != "")                          
                          	$!hash_maklumatPU.NO_PA
                          #else
                          <font color="red">Sila Pastikan Maklumat Ini Diisi!</font>
                          #set($check_maklumat_PU = "X") 
                          #end 
      
          </td>
        </tr>
        
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">No. Syit</td>
          <td   valign="top">:</td>
          <td  valign="top">
         
           #if($!hash_maklumatPU.NO_SYIT != "")                          
                          	$!hash_maklumatPU.NO_SYIT
                          #else
                          <font color="red">Sila Pastikan Maklumat Ini Diisi!</font>
                          #set($check_maklumat_PU = "X") 
                          #end 
          
          </td>
        </tr>
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">No. Lot Baru</td>
          <td   valign="top">:</td>
          <td  valign="top">
          
          
           #if($!hash_maklumatPU.NO_LOT_BARU != "")                          
                          	$!hash_maklumatPU.NO_LOT_BARU
                          #else
                          <font color="red">Sila Pastikan Maklumat Ini Diisi!</font>
                          #set($check_maklumat_PU = "X") 
                          #end 
          
          </td>
        </tr>
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Luas PA</td>
          <td   valign="top">:</td>
          <td  valign="top">
          
          
           #if($!hash_maklumatPU.LUAS_PA != "")                          
                          	$!hash_maklumatPU.LUAS_PA
                          #else
                          <font color="red">Sila Pastikan Maklumat Ini Diisi!</font>
                          #set($check_maklumat_PU = "X") 
                          #end 
                          
                          
          
          </td>
        </tr>
        
       
        
     
      </table>
      </td> 
  </tr>
  </table>
   
 
 <br />

#end



#if($!jenis_skrin == "TarikBalik")
 <table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<strong>Maklumat Penarikan Balik</strong>
 </td>
</tr>
</table>

      <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
      
      
			        	<tr>
			        	  <td width="1%"></td>
			              <td width="20%">No. Rujukan Penarikan Balik</td>
			              <td width="1%">:</td>
			              <td width="78%">
                          
                          $!hash_maklumatTarikBalik.NO_PENARIKANBALIK
                          
                        
                          </td>
			          	</tr>
                        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Tarikh Penarikan Balik</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatTarikBalik.TARIKH_PENARIKANBALIK</td>
        </tr>
        
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Jenis Penarikan Balik</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatTarikBalik.JENIS_PB_KETERANGAN</td>
        </tr>
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Sebab Penarikan Balik</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatTarikBalik.SEBAB_PENARIKANBALIK</td>
        </tr>
        <!--
        
        #if($!hash_maklumatTarikBalik.NO_WARTA != "")
        <tr>
          <td  valign="top"></td>
          <td  valign="top">No. Warta Penarikan Balik</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatTarikBalik.NO_WARTA</td>
        </tr>
        
        #end
        
        
        #if($!hash_maklumatTarikBalik.TARIKH_WARTA != "")
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Tarikh Warta</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatTarikBalik.TARIKH_WARTA</td>
        </tr>
        #end
       -->
        
     
      </table>
    
 
 <br />

#end

#if($!jenis_skrin == "BorangC" || $!jenis_skrin == "BorangA"  || $!jenis_skrin == "TarikBalik"  )
$!maklumat_mmk
#end
 


#if($!jenis_skrin == "TarikBalik" || $!jenis_skrin == "BorangI" || $!jenis_skrin == "BorangC" || $!jenis_skrin == "BorangA" || $!jenis_skrin == "hantarPelanChartingS8" || $!jenis_skrin == "hantarPelanChartingS4" || $!jenis_skrin == "WartaS8" )
		<table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >


#set($senarai_lot_terlibat = "")
#if($!jenis_skrin == "TarikBalik")
	#set($senarai_lot_terlibat = "Senarai Lot Yang DiTarik Balik")
#elseif($!jenis_skrin == "BorangI")
	#set($senarai_lot_terlibat = "Senarai Lot Yang Terlibat untuk Pengambilan Segera")
#else
	#set($senarai_lot_terlibat = "Senarai Lot Yang Terlibat")
#end

		<b>$senarai_lot_terlibat</b>
 		</td>
	</tr>
</table>
         #parse("app/utils/record_paging_popup.jsp")
        
<table align="center" width="100%" cellspacing="2" cellpadding="2" class="dashboard_sub"> 
	<tr class="table_header">
              <td scope="row"  align="center"><strong><font color="white">Bil.</font></strong></td>
              <td ><strong><font color="white">No. Hakmilik</font></strong></td>
              <td ><strong><font color="white">No. Lot</font></strong></td>
              <td align="right" ><strong><font color="white">Luas Asal</font></strong></td>
              <td align="right" ><strong ><font color="white">Luas Ambil</font></strong></td>
               #if($!jenis_skrin == "BorangI")  
                            
                            <td align="center" ><b><font color="white">Tarikh Borang I</font></b></td>
                           
                #end
                #if($!jenis_skrin == "BorangK")  
                            
                            <td align="center" ><b><font color="white">Tarikh Borang K</font></b></td>
                            <td align="center" ><b><font color="white">Jenis Pengambilan</font></b></td>
                #end
  	</tr>
          #set ($list = "")
          #set ($counter = 0)
          #foreach ($list in $SenaraiFail)
          #set( $counter = $counter + 1 )
          	#if ($list.BIL == '')
                #set( $row = "row1" )
            #elseif (($list.BIL % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end           
           
	<tr>
            <td class="$row" align="center">
			$list.BIL
			</td>
           
            <td class="$row">$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
            
            <td class="$row">$list.NO_LOT</td>
            <td class="$row" align="right">$list.LUAS_ASAL</td>
            <td class="$row" align="right">$list.LUAS_AMBIL</td>
             #if($!jenis_skrin == "BorangI")  
                            
                            <td class="$row" align="center">$list.TARIKH_BORANGI</td>
                            #end
              #if($!jenis_skrin == "BorangK")  
                            
                            <td class="$row" align="center">$list.TARIKH_BORANGK</td>
                           <td class="$row" align="center"><!--$list.STATUS_AMBIL-->
                           
                           #if($list.STATUS_AMBIL == "P")
                           KESELURUHAN
                           #elseif($list.STATUS_AMBIL == "S")
                           SEBAHAGIAN
                           #else
                           
                           #end
                                                  
                           </td>
                            
                            #end
	</tr>
          #end
          
           #if($counter == 0)
 	<tr>
           
            <td colspan="10" class="row">
           Tiada rekod
           </td>
	</tr>
           #end 
          
</table>
		        
<br />

#end

#parse("app/integrasi/etanah/pengambilanTanah/PopupPengambilanTanah_muat_naik.jsp")
      
<table border="0" width="100%"  class="nav"> 
<tr >
<td valign="top" >
<b>Senarai Dokumen Yang Terlibat</b>
 </td>
</tr>
</table>
    		       	<table width="100%"  cellpadding="0" cellspacing="2" border="0" class="dashboard_sub">   
                   
			        	<tr class="table_header" >
			           		<td  align="center" width="5%"><b><font color="white">Bil.</font></b></td>
			                <td width="20%" ><b><font color="white">Tajuk</font></b></td>			            
			                <td width="35%"><b><font color="white">Dokumen</font></b></td>
                            <td width="20%"><b><font color="white">Jenis Fail</font></b></td>
                            <td width="20%" ><b><font color="white">Kategori Lampiran</font></b></td>
 			                <td  align="center" style="display:none"></td>			                
			            </tr>
			              
			         #if($listSenaraiDokumen_size!=0)
			          
			             #foreach($listDokumen in $listSenaraiDokumen)  
			                   
			                #set( $i = $velocityCount )       	
			         		#if ( ($i % 2) != 1 )
			              		#set( $row = "row2" )
			         		#else
			               		#set( $row = "row1" )
			         		#end
			         		      
			          	<tr>
			                <td class="$row" align="center" valign="top">$listDokumen.BIL</td>
			                <td class="$row" valign="top">$listDokumen.TAJUK</td>
			                <td class="$row" valign="top"><a href="javascript:paparLampiran('$!listDokumen.ID_DOKUMEN')"><font color="blue">$listDokumen.NAMA_FAIL</font></a></td>
                            <td class="$row" valign="top">$listDokumen.JENIS_MIME</td>
                            <td class="$row" valign="top">$listDokumen.KETERANGAN_LAMPIRANETANAH</td>
                            
                            
                            #set($buttonHapusDoc = "cmdHapusDoc"+$listDokumen.BIL)
                           
			                
			                <td class="$row" align="center" valign="top" style="display:none"><input type="button" id="$buttonHapusDoc" name="$buttonHapusDoc" value ="Hapus" onClick="hapusDokumen('$!listDokumen.ID_DOKUMEN','$!buttonHapusDoc')"></td>	
			                
			            </tr>
			             #end  
			              		 
			         #else
			            <tr>
			                <td colspan="5">Tiada rekod</td>
			            </tr>
			         #end
			                    
			       </table>        	
			   
 
<BR>
</div>


       
</fieldset>


##if($!display_button_hantar == "Y")       
       	  <table width="100%" border="0" align="center"	 cellspacing="2" cellpadding="2" >
           <tr>
           <td  valign="top" align="center"> 
           
           	   #set($disable_button_hantar = "")

               #if($!jenis_skrin == "PU" && $check_maklumat_PU == "X")
               <font color="red">Sila Pastikan Maklumat <b>Hakmilik Sambungan</b> Lengkap Diisi!</font>
               #set($disable_button_hantar = "disabled")                
               #elseif(($!jenis_skrin == "WartaS4" || $!jenis_skrin == "WartaS8") && $check_maklumat_warta == "X")                 
               <font color="red">Sila Pastikan Maklumat <b>Warta</b> Lengkap Diisi!</font>
               #set($disable_button_hantar = "disabled")
               #else 
               #set($disable_button_hantar = "")
               #end 
               
               
               #if($check_dokumen_complete == "X")  
               <font color="red">Sila Pastikan Dokumen Wajib Lengkap Dimuat Naik!</font>
               #set($disable_button_hantar = "disabled")
               #end           
               
               <input type="button" name="cmdHantarRekod" id="cmdHantarRekod" value="Hantar Rekod ke e-Tanah" $disable_button_hantar onClick="hantar('$id_permohonan','$jenis_skrin','$id_penarikan')">
           </td>
           </tr>
           </table>       
       ##end
       <br />

 
<div style="display:none">	
::::BUTTON SEMENTARA::::::
<input type="button" name="cmdHantarRekodXXX" id="cmdHantarRekodXXX" value="PaparLampiranTest" onClick="paparLampiranTest()">
</div>




  </div> 
     
<script>
	 
    function simpan(id_penarikan,id_permohonan,jenis_skrin) {
	
		if(document.${formName}.nama_dokumen.value == ""){
			alert('Sila pastikan tajuk dokumen diisi.');
	  		document.${formName}.nama_dokumen.focus(); 
			return; 
		}
		
		if(document.${formName}.kategori_lampiran.value == ""){
			alert('Sila pastikan kategori lampiran diisi.');
	  		document.${formName}.kategori_lampiran.focus(); 
			return; 
		}
		
		if(document.${formName}.fileupload.value == ""){
			alert('Sila pilih Lampiran yang Ingin Dimuatnaik.');
	  		document.${formName}.fileupload.focus(); 
			return; 
		}
	
		var dp = document.${formName}.form_token_Pop.value ;
		var dopost = "&form_token_Pop="+dp;
		var id_permohonan_set =  document.${formName}.id_permohonan.value;
		var id_fail_set =  document.${formName}.id_fail.value;
		var jenis_skrin_set =  document.${formName}.jenis_skrin.value;
		var tajuk_set =  document.${formName}.nama_dokumen.value;
		var id_hakmilik_set =  document.${formName}.id_hakmilik.value;
		var kategori_lampiran_set =  document.${formName}.kategori_lampiran.value;
		
		document.${formName}.action = "?_portal_module=etanah.ppt.sek4&hitButton=simpanDokumen&id_permohonan="+id_permohonan_set+"&id_penarikan="+id_penarikan+"&jenis_skrin="+jenis_skrin_set+""+dopost+"&id_fail="+id_fail_set+"&nama_dokumen="+tajuk_set+"&id_hakmilik="+id_hakmilik_set+"&kategori_lampiran="+kategori_lampiran_set;
		//document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah&hitButton=simpanDokumen&id_permohonan="+id_permohonan_set+"&id_penarikan="+id_penarikan+"&jenis_skrin="+jenis_skrin_set+""+dopost+"&id_fail="+id_fail_set+"&tajuk="+tajuk_set+"&id_hakmilik="+id_hakmilik_set+"&kategori_lampiran="+kategori_lampiran_set;
		
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
	    document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();

		disableALLbutton('cmdUploadDokumen');
	
    }

function hapusDokumen(id_dokumen,id_button) {
	
	

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	//alert("1");
	var dp = document.${formName}.form_token_Pop.value ;
	//alert("token:"+dp);
	var dopost = "&form_token_Pop="+dp;
	var id_permohonan_set =  document.${formName}.id_permohonan.value;
	var id_fail_set =  document.${formName}.id_fail.value;
	var jenis_skrin_set =  document.${formName}.jenis_skrin.value;
	var id_hakmilik_set =  document.${formName}.id_hakmilik.value;
	//alert("2");
	document.${formName}.action = "?_portal_module=etanah.ppt.sek4&hitButton=hapusDokumen&id_dokumen="+id_dokumen+""+dopost+"&id_permohonan="+id_permohonan_set+"&jenis_skrin="+jenis_skrin_set+"&id_fail="+id_fail_set+"&id_hakmilik="+id_hakmilik_set;
	//document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah&hitButton=hapusDokumen&id_dokumen="+id_dokumen+""+dopost+"&id_permohonan="+id_permohonan_set+"&jenis_skrin="+jenis_skrin_set+"&id_fail="+id_fail_set+"&id_hakmilik="+id_hakmilik_set;
	
	document.${formName}.method="post";
	//document.${formName}.enctype="multipart/form-data";
    //document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
	
	disableALLbutton(id_button);
}


function hapusDokumenEtanah(id_dokumen,id_button) {
	
	

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	//alert("1");
	var dp = document.${formName}.form_token_Pop.value ;
	//alert("token:"+dp);
	var dopost = "&form_token_Pop="+dp;
	var id_permohonan_set =  document.${formName}.id_permohonan.value;
	var id_fail_set =  document.${formName}.id_fail.value;
	var jenis_skrin_set =  document.${formName}.jenis_skrin.value;
	var id_hakmilik_set =  document.${formName}.id_hakmilik.value;
	//alert("2");
	document.${formName}.action = "?_portal_module=etanah.ppt.sek4&hitButton=hapusDokumenEtanah&id_dokumen="+id_dokumen+""+dopost+"&id_permohonan="+id_permohonan_set+"&jenis_skrin="+jenis_skrin_set+"&id_fail="+id_fail_set+"&id_hakmilik="+id_hakmilik_set;
	//document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah&hitButton=hapusDokumenEtanah&id_dokumen="+id_dokumen+""+dopost+"&id_permohonan="+id_permohonan_set+"&jenis_skrin="+jenis_skrin_set+"&id_fail="+id_fail_set+"&id_hakmilik="+id_hakmilik_set;

	document.${formName}.method="post";
	//document.${formName}.enctype="multipart/form-data";
    //document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
	
	
	disableALLbutton(id_button);
}


	function hantar(id_permohonan,jenis_skrin,id_penarikan) {		
		//alert("masuk sini hantar");

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
	
		var dp = document.${formName}.form_token_Pop.value ;
		var dopost = "&form_token_Pop="+dp;
		var id_permohonan_set =  document.${formName}.id_permohonan.value;
		var id_fail_set =  document.${formName}.id_fail.value;
		var jenis_skrin_set =  document.${formName}.jenis_skrin.value;
		var id_hakmilik_set =  document.${formName}.id_hakmilik.value;
		
		document.${formName}.action = "?_portal_module=etanah.ppt.sek4&hitButton=hantarData"+dopost+"&id_permohonan="+id_permohonan_set+"&id_penarikan="+id_penarikan+"&jenis_skrin="+jenis_skrin_set+"&id_fail="+id_fail_set+"&id_hakmilik="+id_hakmilik_set;
		//document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah&hitButton=hantarData"+dopost+"&id_permohonan="+id_permohonan_set+"&id_penarikan="+id_penarikan+"&jenis_skrin="+jenis_skrin_set+"&id_fail="+id_fail_set+"&id_hakmilik="+id_hakmilik_set;
	
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
	    document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();
		
		disableALLbutton('cmdHantarRekod');
		
	}



function paparLampiran(id_dokumen) {
    var url = "../../servlet/ekptg.view.ppt.DisplayBlobInt?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function paparLampiranTest() {
    var url = "../../servlet/ekptg.view.ppt.DisplayBlobIntTest?id=11";
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function paparLampiranEtanah(id_row) {
    var url = "../../servlet/ekptg.view.ppt.DisplayBlobIntFromEtanah?id="+id_row;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function disableALLbutton(id_button)
{
	
	document.getElementById(id_button).value = "Sila Tunggu....";
	//disable all button & field
	var nodes = document.getElementById("divAllPopup").getElementsByTagName('*');
	for(var i = 0; i < nodes.length; i++)
	{
		 nodes[i].disabled = true;
	}
}


</script>
     
     
     
  
