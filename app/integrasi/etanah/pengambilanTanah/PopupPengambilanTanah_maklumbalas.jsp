



<div style=" background-color:#F3ECF3;">



#if(($!jenis_skrin == "BorangK" || $!jenis_skrin == "WartaS8") && $!hash_maklumatEndorsan_size != 0)    
                    <table border="0" width="100%"  class="nav"> 
                    <tr  >
                    <td valign="top" >
                    #set($tajuk_maklumbalas = "")
                      #if($!jenis_skrin == "hantarPelanChartingS8" )         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Pelan Charting & Salinan Warta Borang D)")
                      #elseif($!jenis_skrin == "WartaS8")         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Maklumat Endorsan : Borang D)")
                      #elseif($!jenis_skrin == "BorangC")         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Keputusan Penimbangan Deraf MB/MMK)")
                      #elseif($!jenis_skrin == "BorangA")         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Keputusan Penimbangan Deraf MB/MMK)")
                      #elseif($!jenis_skrin == "BorangK")         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Maklumat Endorsan : Borang K)")
                      #end                   
                  	<b><font color="blue">$tajuk_maklumbalas</font></b>
                     </td>
                    </tr>
                    </table>


                    #if($!hash_maklumatEndorsan_size != 0)                    
                    <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
                    <tr>
                    <td width="1%"></td>
                    <td width="20%">No. Jilid</td>
                    <td width="1%">:</td>
                    <td width="78%">$!hash_maklumatEndorsan.NO_JILID</td>
                    </tr>
                    <tr>
                    <td  valign="top"></td>
                    <td  valign="top">Tarikh Endorsan</td>
                    <td   valign="top">:</td>
                    <td  valign="top">$!hash_maklumatEndorsan.TARIKH_ENDORSAN</td>
                    </tr>
                    </table>
                    #end
                    
                    <br />
                    
#end  


#if(($!jenis_skrin == "BorangA" || $!jenis_skrin == "BorangC") && $!hash_maklumatKeputusanMmk_size != 0)    
                    <table border="0" width="100%"  class="nav"> 
                    <tr  >
                    <td valign="top" >
                      #set($tajuk_maklumbalas = "")
                      #if($!jenis_skrin == "BorangA" || $!jenis_skrin == "BorangC")         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Keputusan Penimbangan Deraf MB/MMK)")
                      #end                   
                  	<b><font color="blue">$tajuk_maklumbalas</font></b>
                     </td>
                    </tr>
                    </table>


                    #if($!hash_maklumatKeputusanMmk_size != 0)                    
                    <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
                    <tr>
                    <td width="1%"></td>
                    <td width="20%">Keputusan</td>
                    <td width="1%">:</td>
                    <td width="78%">
                    <b>
                    #if($!hash_maklumatKeputusanMmk.FLAG_KEPUTUSAN_MMK == "Y")
                    <font color="blue">$!hash_maklumatKeputusanMmk.KEPUTUSAN_MMK</font>
                    #elseif($!hash_maklumatKeputusanMmk.FLAG_KEPUTUSAN_MMK == "T")
                    <font color="red">$!hash_maklumatKeputusanMmk.KEPUTUSAN_MMK</font>
                    #end
                    </b>
                    
                    </td>
                    </tr>
                    <tr>
                    <td  valign="top"></td>
                    <td  valign="top">Keterangan</td>
                    <td   valign="top">:</td>
                    <td  valign="top">$!hash_maklumatKeputusanMmk.KETERANGAN_KEPUTUSAN_MMK</td>
                    </tr>
                    </table>
                    #end
                    
                    <br />
                    
#end  



#if($!jenis_skrin == "PU" && $!hash_maklumatHMS_size != 0)    
                    <table border="0" width="100%"  class="nav"> 
                    <tr  >
                    <td valign="top" >
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Maklumat Hakmilik Sambungan)")
                    <b><font color="blue">$tajuk_maklumbalas</font></b>
                     </td>
                    </tr>
                    </table>


                    #if($!hash_maklumatHMS_size != 0)                    
                    <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub" >
                    <!--
                    <tr>
                    <td width="1%"></td>
                    <td width="20%">No. Lot Baru</td>
                    <td width="1%">:</td>
                    <td width="78%">$!hash_maklumatHMS.NO_NOT_BARU</td>
                    </tr>
                    <tr>
                    <td  valign="top"></td>
                    <td  valign="top">No. Syit</td>
                    <td   valign="top">:</td>
                    <td  valign="top">$!hash_maklumatHMS.NO_SYIT</td>
                    </tr>
                    <tr>
                    <td  valign="top"></td>
                    <td  valign="top">No. PA</td>
                    <td   valign="top">:</td>
                    <td  valign="top">$!hash_maklumatHMS.NO_PA</td>
                    </tr>
                    <tr>
                    <td  valign="top"></td>
                    <td  valign="top">No. PU</td>
                    <td   valign="top">:</td>
                    <td  valign="top">$!hash_maklumatHMS.NO_PU</td>
                    </tr>
                    <tr>
                    <td  valign="top"></td>
                    <td  valign="top">Luas PA</td>
                    <td   valign="top">:</td>
                    <td  valign="top">$!hash_maklumatHMS.LUAS_PA</td>
                    </tr>
                    -->
                    <tr>
                    <td width="1%" valign="top"></td>
                    <td width="20%" valign="top">Status Penyediaan Hakmilik Sambungan</td>
                    <td width="1%"  valign="top">:</td>
                    <td width="78%" valign="top"><b>
                    #if($!hash_maklumatHMS.FLAG_HAKMILIK_DIDAFTARKAN == "Y")
                    <font color="blue">$!hash_maklumatHMS.STATUS_PENDAFTARAN_HAKMILIK</font>
                    #else
                    <font color="red">$!hash_maklumatHMS.STATUS_PENDAFTARAN_HAKMILIK</font>
                    #end
                    </b></td>
                    </tr>
                    </table>
                    #end
                    
                    <br />
                    
#end  

<!--
#if(($!jenis_skrin == "BorangD" || $!jenis_skrin == "BorangA" || $!jenis_skrin == "TarikBalik") && $!hash_maklumatWarta_size != 0)    
                    <table border="0" width="100%"  class="nav"> 
                    <tr  >
                    <td valign="top" >
                    #set($tajuk_maklumbalas = "")
                      #if($!jenis_skrin == "hantarPelanChartingS8" )         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Pelan Charting & Salinan Warta Borang D)")
                      #elseif($!jenis_skrin == "hantarPelanChartingS4")         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Maklumat Endorsan : Surat, Borang A & B)")
                      #elseif($!jenis_skrin == "BorangD")         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Keputusan Penimbangan Deraf MB/MMK)")
                      #elseif($!jenis_skrin == "BorangA")         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Keputusan Penimbangan Deraf MB/MMK)")
                      #elseif($!jenis_skrin == "TarikBalik")         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Maklumat Warta : Penarikan Balik)")
                      #elseif($!jenis_skrin == "BorangK")         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Maklumat Endorsan : Borang K)")
                      #end                    
                    <b><font color="blue">$tajuk_maklumbalas</font></b>
                     </td>
                    </tr>
                    </table>



					#if($!hash_maklumatWarta_size != 0)                    
                    <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
                    <tr>
                    <td width="1%"></td>
                    <td width="20%">No. Warta</td>
                    <td width="1%">:</td>
                    <td width="78%">$!hash_maklumatWarta.NO_WARTA</td>
                    </tr>
                    <tr>
                    <td  valign="top"></td>
                    <td  valign="top">Tarikh Warta</td>
                    <td   valign="top">:</td>
                    <td  valign="top">$!hash_maklumatWarta.TARIKH_WARTA</td>
                    </tr>
                    #if($!hash_maklumatWarta.NO_PERSERAHAN != "")
                    <tr>
                    <td  valign="top"></td>
                    <td  valign="top">No. Perserahan</td>
                    <td   valign="top">:</td>
                    <td  valign="top">$!hash_maklumatWarta.NO_PERSERAHAN</td>
                    </tr>
                    #end
                    </table>                    
                    #end  
                    <br />
#end 
-->
#if($listSenaraiDokumen_fromEtanah_size!=0)
					<table border="0" width="100%"  class="nav"> 
                    <tr  >
                    <td valign="top" >
                    
                    
                    #set($tajuk_maklumbalas = "")
                      #if($!jenis_skrin == "WartaS4" )         
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Borang B Yang Ditandatangani)")
                     #else        
                        #set($tajuk_maklumbalas = "Maklumbalas Daripada e-Tanah (Senarai Dokumen Yang Berkaitan)")
                      #end                    
                    <b><font color="blue">$tajuk_maklumbalas</font></b>
                    
                  
                     </td>
                    </tr>
                    </table>
                    
					<table width="100%"  cellpadding="0" cellspacing="2" border="0" class="dashboard_sub">   
                    <tr class="table_header" >
			        <td  width="5%" align="center"><b><font color="white">Bil.</font></b></td>
			        <td width="25%" ><b><font color="white">Tajuk</font></b></td>			            
			        <td width="35%" ><b><font color="white">Dokumen</font></b></td>
                    <td  width="25%"><b><font color="white">Jenis Fail</font></b></td>
                    <td  width="10%" align="center" ></td>
			        </tr>			              
			        #if($listSenaraiDokumen_fromEtanah_size!=0)
			        #foreach($listDokumenEtanah in $listSenaraiDokumen_fromEtanah)  
                        #set( $i = $velocityCount )       	
                        #if ( ($i % 2) != 1 )
                        #set( $row = "row2" )
                        #else
                        #set( $row = "row1" )
                        #end
			         <tr>
			         <td class="$row" align="center">$listDokumenEtanah.BIL</td>
			         <td class="$row">$listDokumenEtanah.TAJUK_DOKUMEN</td>
			         <td class="$row"><a href="javascript:paparLampiranEtanah('$!listDokumenEtanah.ROW_ID')"><font color="blue">$listDokumenEtanah.NAMA_DOKUMEN</font></a></td>
                     <td class="$row">$listDokumenEtanah.JENIS_FAIL</td> 
                     #set($buttonHapusDocEtanah = "cmdHapusDocEtanah"+$listDokumenEtanah.BIL)
                     <td class="$row" align="center" ><input type="button"  id="$!buttonHapusDocEtanah" name="$!buttonHapusDocEtanah" value ="Hapus" onClick="hapusDokumenEtanah('$!listDokumenEtanah.ROW_ID','$!buttonHapusDocEtanah')"></td>
			         </tr>
			         #end  
			              		 
			         #else
			            <tr><td colspan="5">Tiada rekod</td></tr>
			         #end			                    
			       </table> 
                   <br />                   
#end



</div>


