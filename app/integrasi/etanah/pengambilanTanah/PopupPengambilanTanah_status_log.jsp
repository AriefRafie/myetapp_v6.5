<div id="pinnedFooter">

<table border="0" width="100%"  class="nav" > 
<tr  >
<td valign="top" >
<strong>Status Penghantaran Fail ke e-Tanah</strong>
 </td>
</tr>
</table>



<table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
<tr>
#if($!jenis_skrin == "WartaS4")
#else
<td width="33%" valign="top">




      <table width="100%" border="0" cellspacing="2" cellpadding="2" >
      	<tr>
		<td width="1%"></td>
		<td width="80%">Jumlah Keseluruhan Hakmilik</td>
		<td width="1%">:</td>
		<td width="18%"><b>$!count_hakmilik</b></td>
		</tr>
                        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Jumlah Hakmilik Berjaya Dihantar</td>
          <td   valign="top">:</td>
          <td  valign="top"><font color="blue"><b>$!countlog_hakmilik</b></font></td>
        </tr>
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Jumlah Hakmilik Belum Dihantar</td>
          <td   valign="top">:</td>
          <td  valign="top">
          
          	#set($totHakmilik = $!count_hakmilik)
            #set($totHakmilikLog = $countlog_hakmilik)
			
          #set($countHakmilikBelumHantar = $totHakmilik - $totHakmilikLog)
          <font color="red"><b>$!countHakmilikBelumHantar</b></font>
          
          </td>
        </tr>
      </table>
      
      
</td>
#end


<td width="33%" valign="top">
      
      <table width="100%" border="0" cellspacing="2" cellpadding="2" >
      	<tr>
		<td width="1%"></td>
		<td width="80%">Jumlah Keseluruhan Dokumen</td>
		<td width="1%">:</td>
		<td width="18%"><b>$!count_dokumen</b>
         </td>
		</tr>
                        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Jumlah Dokumen Berjaya Dihantar</td>
          <td   valign="top">:</td>
          <td  valign="top"><font color="blue"><b>$!countLog_dokumen</b></font></td>
        </tr>
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Jumlah Dokumen Belum Dihantar</td>
          <td   valign="top">:</td>
          <td  valign="top">
          
          	#set($totcount_dokumen = $!count_dokumen)
            #set($totcountlog_dokumen = $countLog_dokumen)
			
          #set($countHakmilikBelumHantarDoc = $totcount_dokumen - $totcountlog_dokumen)
          <font color="red"><b>$!countHakmilikBelumHantarDoc</b></font>
          
          </td>
        </tr>
      </table>
</td>


#if($!jenis_skrin == "WartaS8" || $!jenis_skrin == "WartaS4" || $!jenis_skrin == "BorangC" || $!jenis_skrin == "BorangA" || $!jenis_skrin == "TarikBalik")
<td width="33%" valign="top">
	 <table width="100%" border="0" cellspacing="2" cellpadding="2" >
     	
        
     	#if($!jenis_skrin == "WartaS8" || $!jenis_skrin == "WartaS4")
      	<tr>
		<td ></td>
		<td valign="top">Status Penghantaran Maklumat Warta</td>
		<td  valign="top" >:</td>
		<td  valign="top"><b>
        #if($!countlog_warta > 0)
        	<font color="blue">Berjaya Dihantar!</font>
        #else
        	<font color="red">Belum Dihantar!</font>
        #end
        </b>
         </td>
		</tr>
        #end     
        
        #if($!jenis_skrin == "BorangC" || $!jenis_skrin == "BorangA")
        <tr>
		<td ></td>
		<td  valign="top">Status Penghantaran Data Deraf MB/MMK</td>
		<td  valign="top" >:</td>
		<td valign="top"><b>
        #if($!countlog_mmk > 0)
        	<font color="blue">Berjaya Dihantar!</font>
        #else
        	<font color="red">Belum Dihantar!</font>
        #end
        </b>
         </td>
		</tr>
        
        
        <tr>
		<td ></td>
		<td  valign="top">Status Penghantaran Maklumat Pengambilan</td>
		<td  valign="top" >:</td>
		<td valign="top"><b>
        #if($!countlog_maklumatpengambilan > 0)
        	<font color="blue">Berjaya Dihantar!</font>
        #else
        	<font color="red">Belum Dihantar!</font>
        #end
        </b>
         </td>
		</tr>
        
        #end  
        
        
        #if($!jenis_skrin == "TarikBalik")        
        <tr>
		<td ></td>
		<td  valign="top">Status Penghantaran Data Deraf MB/MMK Penarikan Balik</td>

		<td  valign="top" >:</td>
		<td  valign="top"><b>
        #if($!countlog_mmkPB > 0)
        	<font color="blue">Berjaya Dihantar!</font>
        #else
        	<font color="red">Belum Dihantar!</font>
        #end
        </b>
         </td>
		</tr> 
        #end       
        
        <tr>
		<td width="1%"></td>
		<td width="60%" valign="top"></td>
		<td width="1%" valign="top" ></td>
		<td width="38%" valign="top"></td>
        </tr>
      
      </table>
</td>
#end
</tr>
</table>

 

     
      
      #if($!statusSend != "")
      <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
      	<tr>
		<td width="1%"></td>
		<td width="20%">Status Penghantaran Data</td>
		<td width="1%">:</td>
		<td width="78%">$!statusMesej
         </td>
		</tr>   
        
        #if($!statusSend == "no")
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Error Mesej</td>
          <td   valign="top">:</td>
          <td  valign="top">$!errorMesej</td>
        </tr>
        
        #end
        
         
      </table>
      #end
      
       #if($!statusSend_doc != "")
      <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
      	<tr>
		<td width="1%"></td>
		<td width="20%">Status Muat Naik </td>
		<td width="1%">:</td>
		<td width="78%">$!statusMesej_doc
         </td>
		</tr>   
        
        #if($!statusSend_doc == "no")
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Error Mesej</td>
          <td   valign="top">:</td>
          <td  valign="top">$!errorMesej_doc</td>
        </tr>
        
        #end
        
         
      </table>
      #end
      
      
       #set($display_status_hantar_hakmilik_selesai = "")
       #set($display_status_hantar_warta_selesai = "")
       #set($display_button_hantar = "")
      
       #if($!jenis_skrin == "WartaS8" || $!jenis_skrin == "WartaS4")
       
       			   #if($!countlog_warta > 0)
                   		#set($display_status_hantar_warta_selesai = "Y")
                   #else
                        #set($display_button_hantar = "Y")
                   #end
       
       #else
                     
               #if($!count_hakmilik == $!countlog_hakmilik && $!count_hakmilik > 0)
                    #set($display_status_hantar_hakmilik_selesai = "Y")
               #else
                   #if($!count_hakmilik > 0)
                        #set($display_button_hantar = "Y")
                   #end
               #end
       
       #end
       
       
       #if($!display_status_hantar_hakmilik_selesai == "Y" || $!display_status_hantar_warta_selesai == "Y")
       <table width="100%" border="0" align="center"	 cellspacing="2" cellpadding="2" class="dashboard_sub">
       <tr>
       <td  valign="top" align="left">  
       #if($!display_status_hantar_hakmilik_selesai == "Y" ) 
              <font color="blue"><b>Keseluruhan Rekod Hakmilik Bagi Urusan ini Telah Berjaya Dihantar ke e-Tanah</b></font>
       #elseif($!display_status_hantar_warta_selesai == "Y" ) 
        	  #if($!jenis_skrin == "WartaS4")
              <font color="blue"><b>Maklumat Warta Telah Berjaya Dihantar ke e-Tanah</b></font>
              #elseif($!jenis_skrin == "WartaS8")
              <font color="blue"><b>Keseluruhan Rekod Hakmilik & Maklumat Warta Telah Berjaya Dihantar ke e-Tanah</b></font>
              #end
       #end
       </td>
       </tr>
       </table>
       #end
       
      
       
       


 <br>
 
</div>
