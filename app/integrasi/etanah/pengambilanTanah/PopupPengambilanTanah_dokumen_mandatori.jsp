




#set($check_dokumen_complete = "")

#if($!jenis_skrin == "WartaS4" || $!jenis_skrin == "WartaS8" || $!jenis_skrin == "BorangA" || $!jenis_skrin == "BorangC"  || $!jenis_skrin == "BorangI" || $!jenis_skrin == "BorangK"  || $!jenis_skrin == "PU" || $!jenis_skrin == "SijilUkur")

        #set($check_upload_borangA = "X")
        #set($check_upload_borangB = "X")
        #set($check_upload_surat_iringan_A = "X")
        #set($check_upload_surat_iringan_D = "X")
        #set($check_upload_derafMMK = "X")
        #set($check_upload_pelan = "X")        
        #set($check_upload_borangC = "X")
        #set($check_upload_borangD = "X")
        #set($check_upload_borangI = "X")
        #set($check_upload_borangK = "X")
        #set($check_upload_borangPelanB1 = "X")
        #set($check_upload_sijilukur = "X")
        
        #set($check_upload_laporanawaltanah = "X")
        #set($check_upload_laporantanahterperinci = "X")
        
        #foreach($listDokumen in $listSenaraiDokumen) 
            #if($!jenis_skrin == "BorangA")
                #if($listDokumen.KATEGORI == "2")
                    #set($check_upload_borangA = "Y")
                #end        
                #if($listDokumen.KATEGORI == "8")
                    #set($check_upload_surat_iringan_A = "Y")
                #end        
                #if($listDokumen.KATEGORI == "12")
                    #set($check_upload_derafMMK = "Y")
                #end
                #if($listDokumen.KATEGORI == "9" || $listDokumen.KATEGORI == "10")
                    #set($check_upload_pelan = "Y")
                #end                                
                #if($listDokumen.KATEGORI == "15")
                    #set($check_upload_laporanawaltanah = "Y")
                #end
                
            #end
            
            #if($!jenis_skrin == "SijilUkur")
                #if($listDokumen.KATEGORI == "14")
                    #set($check_upload_sijilukur = "Y")
                #end                   
            #end
            
            
            #if($!jenis_skrin == "WartaS4")
                #if($listDokumen.KATEGORI == "3")
                    #set($check_upload_borangB = "Y")
                #end                   
            #end
            
            #if($!jenis_skrin == "WartaS8")
                #if($listDokumen.KATEGORI == "5")
                    #set($check_upload_borangD = "Y")
                #end 
                #if($listDokumen.KATEGORI == "8")
                    #set($check_upload_surat_iringan_D = "Y")
                #end                   
            #end
            
            #if($!jenis_skrin == "WartaS4")
                #if($listDokumen.KATEGORI == "3")
                    #set($check_upload_borangB = "Y")
                #end                   
            #end
            
           
            
            #if($!jenis_skrin == "BorangC")
                #if($listDokumen.KATEGORI == "4")
                    #set($check_upload_borangC = "Y")
                #end                  
                #if($listDokumen.KATEGORI == "12")
                    #set($check_upload_derafMMK = "Y")
                #end
                #if($listDokumen.KATEGORI == "9" || $listDokumen.KATEGORI == "10")
                    #set($check_upload_pelan = "Y")
                #end
                
                #if($listDokumen.KATEGORI == "15")
                    #set($check_upload_laporanawaltanah = "Y")
                #end
                
                #if($listDokumen.KATEGORI == "16")
                    #set($check_upload_laporantanahterperinci = "Y")
                #end
                
            #end
            
            #if($!jenis_skrin == "BorangI")
            	#if($listDokumen.KATEGORI == "6")
                    #set($check_upload_borangI = "Y")
                #end   
            #end
            
            #if($!jenis_skrin == "BorangK")
            	#if($listDokumen.KATEGORI == "7")
                    #set($check_upload_borangK = "Y")
                #end   
            #end
            
            #if($!jenis_skrin == "PU")
            	#if($listDokumen.KATEGORI == "13")
                    #set($check_upload_borangPelanB1 = "Y")
                #end   
            #end
            
            
            
        #end
        
        #set($check_dokumen_complete = "X")
        
        #if($!jenis_skrin == "BorangA"  && $check_upload_borangA == "Y" && $check_upload_surat_iringan_A == "Y" && $check_upload_derafMMK == "Y" && $check_upload_pelan == "Y" && $check_upload_laporanawaltanah == "Y")
        	#set($check_dokumen_complete = "Y")
        #end
        
        #if($!jenis_skrin == "WartaS4"  && $check_upload_borangB == "Y" )
        	#set($check_dokumen_complete = "Y")
        #end
        
        #if($!jenis_skrin == "WartaS8"  && $check_upload_borangD == "Y"  && $check_upload_surat_iringan_D == "Y")
        	#set($check_dokumen_complete = "Y")
        #end
        
        
        #if($!jenis_skrin == "BorangC"  && $check_upload_borangC == "Y" && $check_upload_derafMMK == "Y" && $check_upload_pelan == "Y" && $check_upload_laporanawaltanah == "Y" && $check_upload_laporantanahterperinci == "Y")
        	#set($check_dokumen_complete = "Y")
        #end
        
        #if($!jenis_skrin == "BorangI"  && $check_upload_borangI == "Y")
        	#set($check_dokumen_complete = "Y")
        #end
        
        #if($!jenis_skrin == "BorangK"  && $check_upload_borangK == "Y")
        	#set($check_dokumen_complete = "Y")
        #end
        
        #if($!jenis_skrin == "PU"  && $check_upload_borangPelanB1 == "Y")
        	#set($check_dokumen_complete = "Y")
        #end
        
        #if($!jenis_skrin == "SijilUkur"  && $check_upload_sijilukur == "Y")
        	#set($check_dokumen_complete = "Y")
        #end
        
        
        <!-- open all -->
		#set($check_dokumen_complete = "Y")       
       
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr><td width="5%"></td><td width="10%"></td><td width="80%"></td></tr>
        <tr>
        <td colspan="3" valign="top">
        <font color="red"><b>** Dokumen Yang Perlu Disertakan</b></font>
        </td>
        </tr>
        #if($!jenis_skrin == "BorangA")
            <tr><td ></td><td  valign="top" align="center">1</td><td  valign="top" align="left">
            #if($check_upload_borangA == "Y")
            <font color="blue">Borang A</font>
            #else
            <blink><font color="red">Borang A</font></blink>
            #end
            </td></tr>
            <tr><td ></td><td  valign="top" align="center">2</td><td  valign="top" align="left">
            #if($check_upload_surat_iringan_A == "Y")
            <font color="blue">Surat Iringan Borang A</font>
            #else
            <blink><font color="red">Surat Iringan Borang A</font></blink>
            #end
            </td></tr>
            <tr><td ></td><td  valign="top" align="center">3</td><td  valign="top" align="left">
            #if($check_upload_derafMMK == "Y")
            <font color="blue">Deraf MMK Seksyen 4</font>
            #else
            <blink><font color="red">Deraf MMK Seksyen 4</font></blink>
            #end    
            </td></tr>            
            <tr><td ></td><td  valign="top" align="center">4</td><td  valign="top" align="left">
            #if($check_upload_pelan == "Y")
            <font color="blue">Pelan Cadangan Pengambilan</font>
            #else
            <blink><font color="red">Pelan Cadangan Pengambilan</font></blink>
            #end    
            </td></tr>
            
            <tr><td ></td><td  valign="top" align="center">5</td><td  valign="top" align="left">
            #if($check_upload_laporanawaltanah == "Y")
            <font color="blue">Laporan Awal Tanah</font>
            #else
            <blink><font color="red">Laporan Awal Tanah</font></blink>
            #end    
            </td></tr>
            
         #elseif($!jenis_skrin == "WartaS4")
            <tr><td ></td><td  valign="top" align="center">1</td><td  valign="top" align="left">
            #if($check_upload_borangB == "Y")
            <font color="blue">Borang B</font>
            #else
            <blink><font color="red">Borang B</font></blink>
            #end
            </td></tr>
            
            
         #elseif($!jenis_skrin == "WartaS8")
            <tr><td ></td><td  valign="top" align="center">1</td><td  valign="top" align="left">
            #if($check_upload_borangD == "Y")
            <font color="blue">Borang D</font>
            #else
            <blink><font color="red">Borang D</font></blink>
            #end
            </td></tr>
            <tr><td ></td><td  valign="top" align="center">2</td><td  valign="top" align="left">
            #if($check_upload_surat_iringan_D == "Y")
            <font color="blue">Surat Iringan Borang D</font>
            #else
            <blink><font color="red">Surat Iringan Borang D</font></blink>
            #end
            </td></tr>
                
            
      
        #elseif($!jenis_skrin == "BorangC")
       		 <tr><td ></td><td  valign="top" align="center">1</td><td  valign="top" align="left">
            #if($check_upload_borangC == "Y")
            <font color="blue">Borang C</font>
            #else
            <blink><font color="red">Borang C</font></blink>
            #end
            </td></tr>
            <tr><td ></td><td  valign="top" align="center">2</td><td  valign="top" align="left">
            #if($check_upload_derafMMK == "Y")
            <font color="blue">Deraf MMK Seksyen 8</font>
            #else
            <blink><font color="red">Deraf MMK Seksyen 8</font></blink>
            #end    
            </td></tr>            
            <tr><td ></td><td  valign="top" align="center">3</td><td  valign="top" align="left">
            #if($check_upload_pelan == "Y")
            <font color="blue">Pelan Pengambilan</font>
            #else
            <blink><font color="red">Pelan Pengambilan</font></blink>
            #end    
            </td></tr>
            
            <tr><td ></td><td  valign="top" align="center">4</td><td  valign="top" align="left">
            #if($check_upload_laporanawaltanah == "Y")
            <font color="blue">Laporan Awal Tanah</font>
            #else
            <blink><font color="red">Laporan Awal Tanah</font></blink>
            #end    
            </td></tr>
            
            <tr><td ></td><td  valign="top" align="center">5</td><td  valign="top" align="left">
            #if($check_upload_laporantanahterperinci == "Y")
            <font color="blue">Laporan Tanah Terperinci</font>
            #else
            <blink><font color="red">Laporan Tanah Terperinci</font></blink>
            #end    
            </td></tr>
            
            
        #elseif($!jenis_skrin == "BorangI")
        	<tr><td ></td><td  valign="top" align="center">1</td><td  valign="top" align="left">
            #if($check_upload_borangI == "Y")
            <font color="blue">Borang I</font>
            #else
            <blink><font color="red">Borang I</font></blink>
            #end
            </td></tr>
        #elseif($!jenis_skrin == "BorangK")
        	<tr><td ></td><td  valign="top" align="center">1</td><td  valign="top" align="left">
            #if($check_upload_borangK == "Y")
            <font color="blue">Borang K</font>
            #else
            <blink><font color="red">Borang K</font></blink>
            #end
            </td></tr>
        #elseif($!jenis_skrin == "PU")
        	<tr><td ></td><td  valign="top" align="center">1</td><td  valign="top" align="left">
            #if($check_upload_borangPelanB1 == "Y")
            <font color="blue">Pelan B1 & SA/PA</font>
            #else
            <blink><font color="red">Pelan B1 & SA/PA</font></blink>
            #end
            </td></tr>
        #elseif($!jenis_skrin == "SijilUkur")
        	<tr><td ></td><td  valign="top" align="center">1</td><td  valign="top" align="left">
            #if($check_upload_sijilukur == "Y")
            <font color="blue">Sijil Pembebasan Ukur</font>
            #else
            <blink><font color="red">Sijil Pembebasan Ukur</font></blink>
            #end
            </td></tr>
        #end
        </table>
#end

