<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>

#if($nk_add == "yes")
#set($txtNoKes = "")
#set($txtNoSiasatan = "")
#set($txtTempatSiasatan = "")
#set($txtPoskod = "")
#set($txtAlamat1 = "")
#set($txtAlamat2 = "")
#set($txtAlamat3 = "")
#set($socNegeriSiasatan = "")
#set($socBandarSiasatan = "")
#set($socStatusSiasatan = "")
#set($txdTarikhSiasatan = "")
#set($txtMasaSiasatan = "")
#set($txtTkhAkhirNotis = "")
#set($txtAlasan = "")
#set($jeniswaktu = "")
#set($id_siasatan = "")
#set($txtKeteranganPB = "")
#set($bil_siasatan = $list_siasatan.size()+1)
#end




<!-- maklumat_hakmilik_mmk_pemilik_siasatan -->

 #set($pemilik = "" )
                      #set($count_total_pb=0)
                      #set($count_totalX_pb=$maklumat_hakmilik_mmk_pemilik_siasatan.size())
                      #set($count_totalXX_pb=$count_totalX_pb - 1)                      
                      #foreach($list1 in $maklumat_hakmilik_mmk_pemilik_siasatan)                     
                      #set($count_total_pb=$count_total_pb + 1)                      
                      #if($maklumat_hakmilik_mmk_pemilik_siasatan.size() > 1 && $maklumat_hakmilik_mmk_pemilik_siasatan.size() != $count_total_pb && $count_totalXX_pb != $count_total_pb) 
                      #set($pemilik = $pemilik +" $list1.NAMA_PB,")
                      #elseif($maklumat_hakmilik_mmk_pemilik_siasatan.size() > 1 && $count_totalXX_pb == $count_total_pb)
                      #set($pemilik = $pemilik +" $list1.NAMA_PB")
                      #elseif($maklumat_hakmilik_mmk_pemilik_siasatan.size() > 1 && $maklumat_hakmilik_mmk_pemilik_siasatan.size() == $count_total_pb)
                      #set($pemilik = $pemilik + " dan $list1.NAMA_PB")
                      #elseif($maklumat_hakmilik_mmk_pemilik_siasatan.size() == 1)
                      #set($pemilik = $pemilik + "$list1.NAMA_PB")                      
                      #end
                      #end 






#if($tambah_kehadiran == "yes" || $tambah_kehadiran_wakil == "yes")
#set($id_hakmilikpb="")
#set($id_pb="")
#set($socJenisPB="")
#set($txtNamaPB = "")  
#set($txtNamaPBKP = "")    
#set($socJenisNOPB="")  
#set($txtNoPB = "")   
#set($txtNoAkaun = "")   
#set($socJenisBank="") 
#set($txtAlamat1PB = "")     
#set($txtAlamat2PB = "")     
#set($txtAlamat3PB = "")        
#set($txtPoskodPB = "")
#set($socNegeri = "")
#set($socBandar = "")
#set($txtNoHP = "")
#set($txtNoTel = "")
#set($txtNamaBank = "")
#set($txtKeteranganPB = "")

#set($hadir = "")   
#set($id_kehadiran = "")  
#set($txtPBhadir = "") 

#end 


#foreach($list_siasatan in $maklumat_siasatan)
#set($txtNoKes = $list_siasatan.NO_KES)
#set($txtNoSiasatan = $list_siasatan.NO_SIASATAN)
#set($txtTempatSiasatan = $list_siasatan.TEMPAT)
#set($txtPoskod = $list_siasatan.POSKOD)
#set($txtAlamat1 = $list_siasatan.ALAMAT1)
#set($txtAlamat2 = $list_siasatan.ALAMAT2)
#set($txtAlamat3 = $list_siasatan.ALAMAT3)
#set($socNegeriSiasatan = $list_siasatan.ID_NEGERI)
#set($socBandarSiasatan = $list_siasatan.ID_BANDAR)
#set($socStatusSiasatan = $list_siasatan.STATUS_SIASATAN)
#set($txdTarikhSiasatan = $list_siasatan.TARIKH_SIASATAN)
#set($txtMasaSiasatan = $list_siasatan.MASA_SIASATAN)
#set($txtTkhAkhirNotis = $list_siasatan.TARIKH_AKHIR_TAMPAL)
#set($txtAlasan = $list_siasatan.ALASAN_TANGGUH)
#set($jeniswaktu = $list_siasatan.JENIS_WAKTU_SIASATAN)
#set($id_siasatan = $list_siasatan.ID_SIASATAN)
#end


#if($readmode == "edit")
#set($disabledmode = "")
#set($readonlymode = "")
#elseif($readmode == "view")
#set($disabledmode = "disabled")
#set($readonlymode = "readonly")
#else
#set($disabledmode = "")
#set($readonlymode = "")
#end
<table width="100%">
  
   <tr>
<td>#parse("app/ppt/header_ppt.jsp") </td>
  </tr>
  <tr>
<td>#parse("app/ppt/header_rundingan.jsp") </td>
  </tr>
  
  
  #if($view_pb == "yes")
  
  
  <tr>
<td><fieldset id="maklumat_pb">


#if($maklumat_kehadiran.size() > 0)

#foreach($list in $maklumat_kehadiran)
#set($id_hakmilikpb=$list.ID_HAKMILIKPB)
#set($id_pb=$list.ID_PIHAKBERKEPENTINGAN)
#set($socJenisPB=$list.ID_JENISPB)
#set($txtNamaPB = $list.NAMA_PB)  
#set($txtNamaPBKP = $list.NAMA_KP)    
#set($socJenisNOPB=$list.ID_JENISNOPB)  
#set($txtNoPB = $list.NO_PB)   
#set($txtNoAkaun = $list.NO_AKAUN)   
#set($socJenisBank=$list.ID_BANK) 
#set($txtAlamat1PB = $list.ALAMAT1)     
#set($txtAlamat2PB = $list.ALAMAT2)     
#set($txtAlamat3PB = $list.ALAMAT3)        
#set($txtPoskodPB = $list.POSKOD)
#set($socNegeri = $list.ID_NEGERI)
#set($socBandar = $list.ID_BANDAR)
#set($txtNoHP = $list.NO_HANDPHONE)
#set($txtNoTel = $list.NO_TEL_RUMAH)
#set($txtNamaBank = $list.NAMA_BANK_TXT)

#set($txtKeteranganPB = $list.KETERANGAN_JENIS_PB)

#end

#set($hadir = "TIDAK HADIR")   
#set($id_kehadiran = "")  
#set($txtPBhadir = "2")  
#if($list_check_kehadiran.size()>0)
#foreach($ch in $list_check_kehadiran)    
#if($ch.ID_HAKMILIKPB == $id_hakmilikpb && $ch.FLAG_HADIR == "1")    
#set($hadir = "HADIR")
#set($txtPBhadir = "1")
#set($id_kehadiran = $ch.ID_KEHADIRAN)
#end    
#end    
#end

#end


<legend>

 #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" ) || (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes") && ($socJenisPB == "")))
 MAKLUMAT TURUT HADIR
    #elseif(($socJenisPB == "6"  ) || (($tambah_kehadiran_wakil == "yes" || $tambah_kehadiran_negeri_wakil == "yes") &&( $socJenisPB == "")))
MAKLUMAT WAKIL
  #else
MAKLUMAT ORANG BERKEPENTINGAN
    #end


</legend>


<table width="100%"   > 
  <tr>
    <td valign="top"><table width="100%">
  
      #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" ) || (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes") && ($socJenisPB == "")))
      
      <tr>
        <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%">Jenis Pihak Turut Hadir </td>
        <td width="1%" >:</td>
        <td width="70%">
        
        <!-- $list.JENISPB -->
        
    
        
     #if($readmode == "view" ) 
     #if($socJenisPB=="")
     #set($JenisPB="")                             
     #else                             
     #foreach($ln in $list_jenispb)      
     #if($socJenisPB==$ln.ID_JENISPB)                                      
     #set($JenisPB="$ln.KOD_JENIS_PB - $ln.KETERANGAN")
     #end
     #end                            
     #end  
     $JenisPB    
     <input name="JenisPB" type="hidden" id="JenisPB" value="$JenisPB" size="50" readonly class="disabled">              
    #else    
    <select name="socJenisPB" class="autoselect" id="socJenisPB"  onchange="checking_validation(this,'socJenisPB_check','yes','jenis pihak berkepentingan','normal');" >  
    #if($socJenisPB != "")
    
    #foreach($ln in $list_jenispb) 
    #if($socJenisPB==$ln.ID_JENISPB)
    
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>  
                                     
    #end 
    #end 
   
    #foreach($ln in $list_jenispb)
    #if($socJenisPB!=$ln.ID_JENISPB && ($ln.ID_JENISPB=="42" || $ln.ID_JENISPB=="40" || $ln.ID_JENISPB=="41" || $ln.ID_JENISPB=="27"))
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>                                     
    #end      
    #end
    <option value="">SILA PILIH JENIS TURUT HADIR</option> 
    
    #else
   
    <option value="">SILA PILIH JENIS TURUT HADIR</option>        
    #foreach($ln in $list_jenispb)  
    #if($ln.ID_JENISPB=="42" || $ln.ID_JENISPB=="40" || $ln.ID_JENISPB=="41" || $ln.ID_JENISPB=="27")
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>                                     
    #end    
    
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socJenisPB_check" class = "alert_msg" ></span>    
          
     
        
        </td>
      </tr>
     
      
       #elseif(($socJenisPB == "6"  ) || (($tambah_kehadiran_wakil == "yes" || $tambah_kehadiran_negeri_wakil == "yes") &&( $socJenisPB == "")))
      
      <tr>
        <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%">Jenis Pihak Berkepentingan</td>
        <td width="1%" >:</td>
        <td width="70%">
        
        <!-- $list.JENISPB -->
        
    
        
     #if($readmode == "view" ) 
     #if($socJenisPB=="")
     #set($JenisPB="")                             
     #else                             
     #foreach($ln in $list_jenispb)      
     #if($socJenisPB==$ln.ID_JENISPB)                                      
     #set($JenisPB="$ln.KOD_JENIS_PB - $ln.KETERANGAN")
     #end
     #end                            
     #end  
     $JenisPB    
     <input name="JenisPB" type="hidden" id="JenisPB" value="$JenisPB" size="50" readonly class="disabled">              
    #else    
    <select name="socJenisPB" class="autoselect" id="socJenisPB"  onchange="checking_validation(this,'socJenisPB_check','yes','jenis pihak berkepentingan','normal');" >  
    #if($socJenisPB != "")
    
    #foreach($ln in $list_jenispb) 
    #if($socJenisPB==$ln.ID_JENISPB)
    
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>  
                                     
    #end 
    #end 
   
    #foreach($ln in $list_jenispb)
    #if($socJenisPB!=$ln.ID_JENISPB && ($ln.ID_JENISPB=="6"))
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>                                     
    #end      
    #end
    
    #else
    
    #foreach($ln in $list_jenispb)  
    #if($ln.ID_JENISPB=="6" )
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>                                     
    #end    
    
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socJenisPB_check" class = "alert_msg" ></span>    
          
     
        
        </td>
      </tr>
      
      
      #else
      
      <tr>
        <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%">Jenis Pihak Berkepentingan </td>
        <td width="1%" >:</td>
        <td width="70%">
        
        <!-- $list.JENISPB -->
        
    
        
     #if($readmode == "view" ) 
     #if($socJenisPB=="")
     #set($JenisPB="")                             
     #else                             
     #foreach($ln in $list_jenispb)      
     #if($socJenisPB==$ln.ID_JENISPB)                                      
     #set($JenisPB="$ln.KOD_JENIS_PB - $ln.KETERANGAN")
     #end
     #end                            
     #end    
     $!JenisPB  
     <input name="JenisPB" type="hidden" id="JenisPB" value="$JenisPB" size="50" readonly class="disabled">              
    #else    
    <select name="socJenisPB" class="autoselect" id="socJenisPB"  onchange="checking_validation(this,'socJenisPB_check','yes','jenis pihak berkepentingan','normal');" >  
    #if($socJenisPB != "")
    
    #foreach($ln in $list_jenispb) 
    #if($socJenisPB==$ln.ID_JENISPB)
    
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>  
                                     
    #end 
    #end 
   
    #foreach($ln in $list_jenispb)
    #if($socJenisPB!=$ln.ID_JENISPB && $ln.ID_JENISPB!="42" && $ln.ID_JENISPB!="40" && $ln.ID_JENISPB!="41" && $ln.ID_JENISPB!="27")
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>                                     
    #end      
    #end
    <option value="">SILA PILIH JENIS PB</option> 
    
    #else
   
    <option value="">SILA PILIH JENIS PB</option>        
    #foreach($ln in $list_jenispb)  
    #if($ln.ID_JENISPB!="42" && $ln.ID_JENISPB!="40" && $ln.ID_JENISPB!="41" && $ln.ID_JENISPB!="27")
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>                                     
    #end    
    
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socJenisPB_check" class = "alert_msg" ></span>    
          

        
        
        
        
        </td>
      </tr>
      #end
      
   <tr>
        <td></td>
        <td valign="top">
        
    #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" ) || (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes") && ($socJenisPB == "")))
    Keterangan Jenis Pihak Turut Hadir
 
    #elseif(($socJenisPB == "6"  ) || (($tambah_kehadiran_wakil == "yes" || $tambah_kehadiran_negeri_wakil == "yes") &&( $socJenisPB == "")))
    Keterangan Jenis Wakil
    
    #else
     Keterangan Jenis PB
  
    #end
        
        </td>
        <td valign="top">:</td>
        <td>
        
        #if($readmode == "view")
$txtKeteranganPB
#else

        
         <textarea name="txtKeteranganPB" id="txtKeteranganPB" cols="50"   rows="3" style=""         
         onBlur="check_length(this,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');"  
         onKeyup="check_length(this,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');" 
         onKeydown="check_length(this,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganPB</textarea> 
          
        
         #if($readmode == "edit")           
         <div><span id="txtKeteranganPB_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKeteranganPB_num" id="txtKeteranganPB_num" size="3" value="4000"  style=" display:none" > 
         #end
         <div id="txtKeteranganPB_check" class="alert_msg" ></div>     
         
    
#end
        
        </td>
      </tr>
      
      
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Kehadiran</td>
        <td>:</td>
        <td>
        
          
   
       
  <!--  $hadir -->
    
    
    
    
             #if($readmode == "view" )       
              #if($txtPBhadir == "1")
              #set($PBhadir = "HADIR")           
              #else
              #set($PBhadir = "TIDAK HADIR")
              #end
              
              $!PBhadir              
              <input name="PBhadir" type="hidden" class = "disabled" id="PBhadir" value="$PBhadir" size="15"  readonly $check1>              
              #else
              <table width="100%">
              <tr>
              <td width="5%" >                    
              #if($txtPBhadir == "1")
              #set($check1 = "checked")
              #set($check2 = "")             
              #elseif($txtPBhadir == "2")
              #set($check2 = "checked")
              #set($check1 = "")
              #else
              #set($check1 = "")
              #set($check2 = "")
              #end 
<input type="radio" name="txtPBhadir" id="txtPBhadir" value="1" $check1 onClick="checking_validation(this,'txtPBhadir_check','yes','kehadiran','radio');">
</td>
<td width="15%">
Hadir</td>
              <td width="5%">
 <input type="radio" name="txtPBhadir" id="txtPBhadir" value="2" $check2 onClick="checking_validation(this,'txtPBhadir_check','yes','kehadiran','radio');">
 </td>
 <td width="75%">
Tidak Hadir &nbsp; &nbsp;<span id="txtPBhadir_check" class = "alert_msg" >        </span>  
</td>                 
</tr>
                </table>
          #end
         
        
    
    
    
        </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>
         #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" ) || (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes") && ($socJenisPB == "")))
  Nama Turut Hadir
 
    #elseif(($socJenisPB == "6"  ) || (($tambah_kehadiran_wakil == "yes" || $tambah_kehadiran_negeri_wakil == "yes") &&( $socJenisPB == "")))
  Nama Wakil
    
  #else
  Nama PB
    #end
        
        
        
        
        
        </td>
        <td>:</td>
        <td>
       #if($readmode == "view")
       $!txtNamaPB
        <input name="txtNamaPB" type="hidden" class = "$disabledmode" id="txtNamaPB" onBlur="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')" onKeyUp="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')" value="$txtNamaPB" size="50" maxlength="80"  $readonlymode>
    <span id="txtNamaPB_check" class = "alert_msg" ></span>
    #else
    
      <input name="txtNamaPB" type="text" class = "$disabledmode" id="txtNamaPB" onBlur="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')" onKeyUp="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')" value="$txtNamaPB" size="50" maxlength="80"  $readonlymode>
    <span id="txtNamaPB_check" class = "alert_msg" ></span>
    
    #end
        
        
        </td>
      </tr>
      <tr>
        <td></td>
        <td>
        
        
          #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" ) || (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes") && ($socJenisPB == "")))
 Nama Turut Hadir (KP)
 
    #elseif(($socJenisPB == "6"  ) || (($tambah_kehadiran_wakil == "yes" || $tambah_kehadiran_negeri_wakil == "yes") &&( $socJenisPB == "")))
  Nama Wakil (KP)
    
  #else
 Nama PB (KP)
    #end
        
        
      
        
        </td>
        <td>:</td>
        <td>
        
        <!-- $list.NAMA_KP  -->
        
        #if($readmode == "view")
        $!txtNamaPBKP
        <input name="txtNamaPBKP" type="hidden" class = "$disabledmode" id="txtNamaPBKP" onBlur="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')" onKeyUp="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')" value="$txtNamaPBKP" size="50" maxlength="80"  $readonlymode>
    <span id="txtNamaPBKP_check" class = "alert_msg" ></span>
    #else
      <input name="txtNamaPBKP" type="text" class = "$disabledmode" id="txtNamaPBKP" onBlur="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')" onKeyUp="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')" value="$txtNamaPBKP" size="50" maxlength="80"  $readonlymode>
    <span id="txtNamaPBKP_check" class = "alert_msg" ></span>
    
    #end
        
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>
        
         #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" ) || (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes") && ($socJenisPB == "")))
 Jenis No. Pengenalan Turut Hadir
 
    #elseif(($socJenisPB == "6"  ) || (($tambah_kehadiran_wakil == "yes" || $tambah_kehadiran_negeri_wakil == "yes") &&( $socJenisPB == "")))
 Jenis No. Pengenalan Wakil
  #else
 Jenis No. PB
    #end
        
        
        
   
        
        </td>
        <td>:</td>
        <td>
        
        <!-- $list.JENISNOPB -->
      
      
        
     #if($readmode == "view" ) 
     #if($socJenisNOPB=="")
     #set($JenisNOPB="")                             
     #else   
                        
     #foreach($ln in $list_jenisnopb)      
     #if($socJenisNOPB==$ln.ID_JENISNOPB)                                      
     #set($JenisNOPB="$ln.KOD_JENIS_NOPB - $ln.KETERANGAN")
     #end
     #end                            
     #end  
     $!JenisNOPB    
     <input name="JenisNOPB" type="hidden" id="JenisNOPB" value="$JenisNOPB" size="50" readonly class="disabled">              
    #else    
    <select name="socJenisNOPB" class="autoselect" id="socJenisNOPB"  onchange="checking_validation(this,'socJenisNOPB_check','no','jenis no. pihak berkepentingan','normal_j');" onblur="checking_validation(this,'socJenisNOPB_check','no','jenis no. pihak berkepentingan','normal_j');" >  
    #if($socJenisNOPB != "")
    
    #foreach($ln in $list_jenisnopb) 
    #if($socJenisNOPB==$ln.ID_JENISNOPB)
    <option value="$ln.ID_JENISNOPB">$ln.KOD_JENIS_NOPB - $ln.KETERANGAN</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_jenisnopb)
    #if($socJenisNOPB!=$ln.ID_JENISNOPB)
    <option value="$ln.ID_JENISNOPB">$ln.KOD_JENIS_NOPB - $ln.KETERANGAN</option>                                     
    #end      
    #end
    <option value="">
    

 #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" ) || (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes") && ($socJenisPB == "")))
 SILA PILIH JENIS PENGENALAN TURUT HADIR
 
    #elseif(($socJenisPB == "6"  ) || (($tambah_kehadiran_wakil == "yes" || $tambah_kehadiran_negeri_wakil == "yes") &&( $socJenisPB == "")))
 SILA PILIH JENIS PENGENALAN WAKIL
  #else
 SILA PILIH JENIS NO. PB
    #end
     
   
    
    </option> 
    
    #else
   
    <option value="">
    
   #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" ) || (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes") && ($socJenisPB == "")))
 SILA PILIH JENIS PENGENALAN TURUT HADIR
 
    #elseif(($socJenisPB == "6"  ) || (($tambah_kehadiran_wakil == "yes" || $tambah_kehadiran_negeri_wakil == "yes") &&( $socJenisPB == "")))
 SILA PILIH JENIS PENGENALAN WAKIL
  #else
 SILA PILIH JENIS NO. PB
    #end
     

</option>        
    #foreach($ln in $list_jenisnopb)   
    <option value="$ln.ID_JENISNOPB">$ln.KOD_JENIS_NOPB - $ln.KETERANGAN</option>
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socJenisNOPB_check" class = "alert_msg" ></span>    
          
        
        
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>
        
        
         
         #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" ) || (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes") && ($socJenisPB == "")))
 No. Pengenalan Turut Hadir
 
    #elseif(($socJenisPB == "6"  ) || (($tambah_kehadiran_wakil == "yes" || $tambah_kehadiran_negeri_wakil == "yes") &&( $socJenisPB == "")))
 No. Pengenalan Wakil
  #else
 No. PB
    #end
        
        
       
        
       </td>
        <td>:</td>
        <td>
        
          #if($readmode == "view")
          $!txtNoPB
        <input name="txtNoPB" type="hidden" class = "$disabledmode" id="txtNoPB" onBlur="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')" onKeyUp="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')" value="$txtNoPB" size="50" maxlength="15"  $readonlymode>
    <span id="txtNoPB_check" class = "alert_msg"></span>
    #else
    <input name="txtNoPB" type="text" class = "$disabledmode" id="txtNoPB" onBlur="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')" onKeyUp="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')" value="$txtNoPB" size="50" maxlength="15"  $readonlymode>
    <span id="txtNoPB_check" class = "alert_msg"></span>
    
    #end
        </td>
      </tr>
      
       <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">No. HP</td>
        <td width="1%">:</td>
        <td width="70%">
        <!-- $list.NO_AKAUN -->
        
         #if($readmode == "view")
         $!txtNoHP
        <input name="txtNoHP" type="hidden" class = "$disabledmode" id="txtNoHP" onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')" value="$txtNoHP" size="50" maxlength="15"  $readonlymode>
    <span id="txtNoHP_check" class = "alert_msg" ></span>
        #else
          <input name="txtNoHP" type="text" class = "$disabledmode" id="txtNoHP" onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')" value="$txtNoHP" size="50" maxlength="12"  $readonlymode>
    <span id="txtNoHP_check" class = "alert_msg" ></span>
        #end
        </td>
      </tr>
       <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">No. Tel (R/P)</td>
        <td width="1%">:</td>
        <td width="70%">
        <!-- $list.NO_AKAUN -->
        
         #if($readmode == "view")
         $!txtNoTel
        <input name="txtNoTel" type="hidden" class = "$disabledmode" id="txtNoTel" onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')" value="$txtNoTel" size="50" maxlength="15"  $readonlymode>
    <span id="txtNoTel_check" class = "alert_msg"></span>
        #else
          <input name="txtNoTel" type="text" class = "$disabledmode" id="txtNoTel" onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')" value="$txtNoTel" size="50" maxlength="12"  $readonlymode>
    <span id="txtNoTel_check" class = "alert_msg"></span>
        #end
        </td>
      </tr>
      
       #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" || $socJenisPB == "") && (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes")|| $readmode == "view" || $readmode == "edit"))
      
       <tr style="display:none">
        <td>&nbsp;</td>
        <td>Nama Bank</td>
        <td>:</td>
        <td>
       #if($readmode == "view")
       $!txtNamaBank
        <input name="txtNamaBank" type="hidden" class = "$disabledmode" id="txtNamaBank" onBlur="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" onKeyUp="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" value="$txtNamaBank" size="50" maxlength="350"  $readonlymode>
    <span id="txtNamaPB_check" class = "alert_msg" ></span>
    #else
    
      <input name="txtNamaBank" type="text" class = "$disabledmode" id="txtNamaBank" onBlur="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" onKeyUp="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" value="$txtNamaBank" size="50" maxlength="350"  $readonlymode>
    <span id="txtNamaBank_check" class = "alert_msg" ></span>
    
    #end
        
        
        </td>
      </tr>
      #else
      
           <tr>
        <td>&nbsp;</td>
        <td>Nama Bank</td>
        <td>:</td>
        <td>
       #if($readmode == "view")
       $!txtNamaBank
        <input name="txtNamaBank" type="hidden" class = "$disabledmode" id="txtNamaBank" onBlur="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" onKeyUp="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" value="$txtNamaBank" size="50" maxlength="350"  $readonlymode>
    <span id="txtNamaPB_check" class = "alert_msg" ></span>
    #else
    
      <input name="txtNamaBank" type="text" class = "$disabledmode" id="txtNamaBank" onBlur="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" onKeyUp="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" value="$txtNamaBank" size="50" maxlength="350"  $readonlymode>
    <span id="txtNamaBank_check" class = "alert_msg" ></span>
    
    #end
        
        
        </td>
      </tr>
      #end
      
    <tr  style="display:none">
        <td width="1%">&nbsp;</td>
        <td width="28%">Nama Bank</td>
        <td width="1%">:</td>
        <td width="70%">
        
        <!-- $list.NAMA_BANK -->
        
        
            
     #if($readmode == "view" ) 
     #if($socJenisBank=="")
     #set($JenisBank="")                             
     #else                             
     #foreach($ln in $list_bank)      
     #if($socJenisBank==$ln.ID_BANK)                                      
     #set($JenisBank="$ln.KOD_BANK - $ln.NAMA_BANK")
     #end
     #end                            
     #end      
     <input name="JenisBank" type="text" id="JenisBank" value="$JenisBank" size="50" readonly class="disabled">              
    #else    
    <select name="socJenisBank" class="autoselect" id="socJenisBank"  onchange="checking_validation(this,'socJenisBank_check','no','bank','normal');" >  
    #if($socJenisBank != "")    
    #foreach($ln in $list_bank) 
    #if($socJenisBank==$ln.ID_BANK)
    <option value="$ln.ID_BANK">$ln.KOD_BANK - $ln.NAMA_BANK</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_bank)
    #if($socJenisBank!=$ln.ID_BANK)
    <option value="$ln.ID_BANK">$ln.KOD_BANK - $ln.NAMA_BANK</option>                                     
    #end      
    #end
    <option value="">SILA PILIH BANK</option> 
    
    #else
   
    <option value="">SILA PILIH BANK</option>        
    #foreach($ln in $list_bank)   
    <option value="$ln.ID_BANK">$ln.KOD_BANK - $ln.NAMA_BANK</option>
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socJenisBank_check" class = "alert_msg" ></span>    
        
        
        </td>
      </tr>
   #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" || $socJenisPB == "") && (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes")|| $readmode == "view" || $readmode == "edit"))  <tr style="display:none">
        <td width="1%">&nbsp;</td>
        <td width="28%">No. Akaun</td>
        <td width="1%">:</td>
        <td width="70%">
        <!-- $list.NO_AKAUN -->
        
         #if($readmode == "view")
         $txtNoAkaun
        <input name="txtNoAkaun" type="hidden" class = "$disabledmode" id="txtNoAkaun" onBlur="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" onKeyUp="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" value="$txtNoAkaun" size="50" maxlength="20"  $readonlymode>
    <span id="txtNoAkaun_check" class = "alert_msg" ></span>
        #else
         <input name="txtNoAkaun" type="text" class = "$disabledmode" id="txtNoAkaun" onBlur="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" onKeyUp="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" value="$txtNoAkaun" size="50" maxlength="20"  $readonlymode>
    <span id="txtNoAkaun_check" class = "alert_msg" ></span>
        #end
        </td>
      </tr>
      
      #else
         <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">No. Akaun</td>
        <td width="1%">:</td>
        <td width="70%">
        <!-- $list.NO_AKAUN -->
        
         #if($readmode == "view")
         $txtNoAkaun
        <input name="txtNoAkaun" type="hidden" class = "$disabledmode" id="txtNoAkaun" onBlur="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" onKeyUp="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" value="$txtNoAkaun" size="50" maxlength="20"  $readonlymode>
    <span id="txtNoAkaun_check" class = "alert_msg" ></span>
        #else
         <input name="txtNoAkaun" type="text" class = "$disabledmode" id="txtNoAkaun" onBlur="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" onKeyUp="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" value="$txtNoAkaun" size="50" maxlength="20"  $readonlymode>
    <span id="txtNoAkaun_check" class = "alert_msg" ></span>
        #end
        </td>
      </tr>
      
      #end
      
      <tr>
        <td>&nbsp;</td>
        <td>
        
   
          
         #if(($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" ) || (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes") && ($socJenisPB == "")))
   Alamat Turut Hadir
 
    #elseif(($socJenisPB == "6"  ) || (($tambah_kehadiran_wakil == "yes" || $tambah_kehadiran_negeri_wakil == "yes") &&( $socJenisPB == "")))
 Alamat Wakil
   #else
 Alamat PB
    #end
        
        </td>
        <td>:</td>
        <td><!-- $list.ALAMAT1 -->
        
          #if($readmode == "view")  
          $txtAlamat1PB
        <input name="txtAlamat1PB" type="hidden" id="txtAlamat1PB" value="$txtAlamat1PB" size="50" onBlur="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')" onKeyUp="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')"  $readonlymode class = "$disabledmode">
    <span id="txtAlamat1PB_check" class = "alert_msg" ></span>
    #else
     <input name="txtAlamat1PB" type="text" id="txtAlamat1PB" value="$txtAlamat1PB" size="50" onBlur="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')" onKeyUp="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')"  $readonlymode class = "$disabledmode">
    <span id="txtAlamat1PB_check" class = "alert_msg" ></span>
    #end
        
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>
        <!-- $list.ALAMAT2 -->
        
        #if($readmode == "view")
        $txtAlamat2PB    
        <input name="txtAlamat2PB" type="hidden" id="txtAlamat2PB" value="$txtAlamat2PB" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode">
  #else
   <input name="txtAlamat2PB" type="text" id="txtAlamat2PB" value="$txtAlamat2PB" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode">
  #end
        
        
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>
        
        <!-- $list.ALAMAT3 -->
        #if($readmode == "view")
         $txtAlamat3PB
        <input name="txtAlamat3PB" type="hidden" id="txtAlamat3PB" value="$txtAlamat3PB" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode">
        
        #else
         <input name="txtAlamat3PB" type="text" id="txtAlamat3PB" value="$txtAlamat3PB" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode">
        #end
        
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Poskod</td>
        <td>:</td>
        <td><!-- $list.POSKOD -->
       #if($readmode == "view")
       $txtPoskodPB
        <input name="txtPoskodPB" type="hidden" id="txtPoskodPB" onBlur="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)" onKeyUp="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)" value="$txtPoskodPB" size="6" maxlength="5"  $readonlymode class = "$disabledmode">
     <span id="txtPoskodPB_check" class = "alert_msg" ></span>
     #else
      <input name="txtPoskodPB" type="text" id="txtPoskodPB" onBlur="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)" onKeyUp="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)" value="$txtPoskodPB" size="6" maxlength="5"  $readonlymode class = "$disabledmode">
     <span id="txtPoskodPB_check" class = "alert_msg" ></span>
     #end
        
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Negeri</td>
        <td>:</td>
        <td><!-- $list.NAMA_NEGERI -->
       
        
        #if($readmode == "view" ) 
     #if($socNegeri=="")
     #set($Negeri="")                             
     #else                             
     #foreach($ln in $list_negeri)      
     #if($socNegeri==$ln.ID_NEGERI)                                      
     #set($Negeri="$ln.KOD_NEGERI - $ln.NAMA_NEGERI")
     #end
     #end                            
     #end      
     $!Negeri
     <input name="Negeri" type="hidden" id="Negeri" value="$Negeri" size="50" readonly class="disabled">              
    #else    
    <select name="socNegeri" class="autoselect" id="socNegeri"  onchange="checking_validation(this,'socNegeri_check','no','negeri','normal');getBandar('$!id_hakmilikpb','$!id_pembatalan')"  onblur="checking_validation(this,'socNegeri_check','no','negeri','normal');getBandar('$!id_hakmilikpb','$!id_pembatalan')" >  
    #if($socNegeri != "")
    
    #foreach($ln in $list_negeri) 
    #if($socNegeri==$ln.ID_NEGERI)
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_negeri)
    #if($socNegeri!=$ln.ID_NEGERI)
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>                                     
    #end      
    #end
    <option value="">SILA PILIH NEGERI</option> 
    
    #else
   
    <option value="">SILA PILIH NEGERI</option>        
    #foreach($ln in $list_negeri)   
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socNegeri_check" class = "alert_msg" ></span>   
        
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Bandar</td>
        <td>:</td>
        <td><!-- $list.NAMA_BANDAR -->
       
         
     #if($readmode == "view" )
    
     #if($socBandar=="")
     #set($Bandar="")                             
     #else                             
     #foreach($lb in $list_bandar)      
     #if($socBandar==$lb.ID_BANDAR)                                      
     #set($Bandar="$lb.KOD_BANDAR - $lb.NAMA_BANDAR")
     #end
     #end                            
     #end   
     $!Bandar   
    <input name="Bandar" type="hidden" id="Bandar" value="$Bandar" size="50" readonly class="disabled">              
    #else    
    <select name="socBandar" class="autoselect" id="socBandar"  onchange="checking_validation(this,'socBandar_check','no','bandar','normal');" >  
    #if($socBandar != "")
    
    #foreach($lb in $list_bandar) 
    #if($socBandar==$lb.ID_BANDAR)
    <option value="$lb.ID_BANDAR">$lb.KOD_BANDAR - $lb.NAMA_BANDAR</option>                                     
    #end 
    #end 
   
    #foreach($lb in $list_bandar)
    #if($socBandar!=$lb.ID_BANDAR)
    <option value="$lb.ID_BANDAR">$lb.KOD_BANDAR - $lb.NAMA_BANDAR</option>                                     
    #end      
    #end
    <option value="">SILA PILIH BANDAR</option> 
    
    #else
   
    <option value="">SILA PILIH BANDAR</option>        
    #foreach($lb in $list_bandar)   
    <option value="$lb.ID_BANDAR">$lb.KOD_BANDAR - $lb.NAMA_BANDAR</option>
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socBandar_check" class = "alert_msg" ></span> 
        
        </td>
      </tr>
       
    </table></td>
  </tr>
  <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskinipb('$id_hakmilikpb')">
      </label>
      <label>
         #if($socJenisPB == "40"  || $socJenisPB == "41" || $socJenisPB == "27" || $socJenisPB == "42" || $socJenisPB == "6")
      <label>    
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapusTurutHadir('$id_hakmilikpb','$id_pb','$id_kehadiran')">    
      </label>
      #end
      <!--
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">
      -->
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpanPB('$id_siasatan','$id_hakmilikpb','$id_kehadiran','$id_pb')">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal('$id_hakmilikpb')">
      </label>
    #end  
     
       #if($id_pembatalan != "")
      <label>
      <!-- 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="" >
      --> 
      </label>    
      #end     </div>  
     <div class="TabbedPanelsContent">        </div>         </td>
  </tr>
  #if($readmode == "edit")
  <tr>
    <td colspan="4">#parse("app/ppt/PenarikanBalik_Alert.jsp")</td>
  </tr>
  #else
   <tr>
    <td colspan="4"></td>
  </tr>  
  #end
</table>
#end
</fieldset>
</td>
  </tr>

  
  



<tr>
<td>

<fieldset id="senarai_siasatan">
<legend>SENARAI KEHADIRAN</legend>


<table width="100%" >
  <tr>              
              <td colspan="8">
              
              <table width="100%" >
              <tr>
             
              <td align="left" width="56%" valign="top">
              

<input type="button" value="Kembali" onClick="kembaliKehadiran('$id_permohonan','$id_pembatalan')">
<input type="button" value="Simpan Kehadiran" onClick="Update_kehadiran()">
<input type="button" value="Tambah Kehadiran (Wakil)" onClick="tambahWakil()">

<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
#if($list_kehadiran.size()!=0)
<!-- <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" /> -->  
#end
<br /><br />
<fieldset id="tableReport1" style="display:none;">

	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    <tr>
        <td>
               
        <a href="#" onClick="javascript:cetakSenarai('$!id_permohonan','$id_siasatan')"><font color="blue"> Senarai Kehadiran
 </font></a></td>
      </tr>  
	 <tr>
        <td><a href="#" onClick="javascript:cetakSuratPanggilanPerundinganSementara('$id_fail','$id_permohonan','$id_siasatan','$id_hakmilik')"><font color="blue"> Surat Panggil Perundingan (JPPH)
 </font></a></td>
      </tr>  
       <tr>
        <td><a href="#" onClick="javascript:cetakSuratPanggilanPerundinganSementaraAgensi('$id_fail','$id_permohonan','$id_siasatan','$id_hakmilik')"><font color="blue"> Surat Panggil Perundingan (Agensi)
 </font></a></td>
      </tr> 
       <tr>
        <td><a href="#" onClick="javascript:cetakSuratPanggilanPerundinganSementaraPB('$id_fail','$id_permohonan','$id_siasatan','$id_hakmilik')"><font color="blue"> Surat Panggil Perundingan (Pihak Berkepentingan)
 </font></a></td>
      </tr>  
      
       <tr>
        <td><a href="#" onClick="javascript:cetakSuratPanggilanPerundinganSambunganSementara('$id_fail','$id_permohonan','$id_siasatan','$id_hakmilik')"><font color="blue"> Surat Panggil Perundingan Sambungan (JPPH)
 </font></a></td>
      </tr>  
      
        <tr>
        <td><a href="#" onClick="javascript:cetakSuratPanggilanPerundinganSementaraSambunganAgensi('$id_fail','$id_permohonan','$id_siasatan','$id_hakmilik')"><font color="blue"> Surat Panggil Perundingan Sambungan (Agensi)
 </font></a></td>
      </tr> 
       <tr>
        <td><a href="#" onClick="javascript:cetakSuratPanggilanPerundinganSementaraSambunganPB('$id_fail','$id_permohonan','$id_siasatan','$id_hakmilik')"><font color="blue"> Surat Panggil Perundingan Sambungan (Pihak Berkepentingan)
 </font></a></td>
      </tr>  

      
      
    </table>
</fieldset>	

              </td>
              <td width="44%" align="right" valign="top">
                #if($list_kehadiran.size()!=0)
              No. KP PB : 
              
                      <label>
                    <input type="text" name="CariLot" id="CariLot"  />
                    </label>
                 
                      <label>
                      <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Cari" onClick="cari_lot(CariLot.value)" />
                      </label>
                      <label>
                      <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Kosongkan" onClick="cari_lot1(CariLot.value)"  />
                      </label>
                    
          <div> Kehadiran PB : <span id="jumlahlot"  ></span> </div>
                      
                  #end
                </td>
              </tr>
              </table>
              
              
             </td>
             </tr>
  <tr class="table_header">
  	<td width="5%">Bil</td>
    
    <td width="40%">Nama Pihak Berkepentingan</td>
    <td width="20%">No. Pihak Berkepentingan</td>
    
    
    <td width="25%"><div align="left">Jenis Pihak Berkepentingan</div></td>
   
      #if($list_kehadiran.size() > 0)
    <td  width="10%"> 
      <div align="center">
      <input type="checkbox" name="all1" id="all1" onClick="doCheckAll1('$list_kehadiran.size()');" title="Semak untuk menunjukkan semua PB hadir" />
      </div></td>
      #end  </tr>
  
   #if($list_kehadiran.size() > 0)
  #foreach($list in $list_kehadiran)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
 
   <tr>
    <td colspan="5">
 
    <fieldset id="$list.BILTEMP" class="$row" style="visibility:collapse; display:none;">
     <table width="100%"   >
     <tr>
        <td width="1%">&nbsp;</td>
       
        <td width="99%"><div align="left"><a href="javascript:paparpb('$list.ID_HAKMILIKPB');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">KEMASKINI MAKLUMAT PB</font></a></div></td>
        </tr>
     </table>
    <table width="100%"   >
  <tr>
    <td width="50%" valign="top"><table width="100%">
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">Jenis Pihak Berkepentingan</td>
        <td width="1%" >:</td>
        <td width="70%">$list.JENISPB</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Kehadiran</td>
        <td>:</td>
        <td>
        
          
    #set($hadir = "TIDAK HADIR")
    #if($list_check_kehadiran.size()>0)
    #foreach($ch in $list_check_kehadiran)    
    #if($ch.ID_HAKMILIKPB == $list.ID_HAKMILIKPB && $ch.FLAG_HADIR == "1")    
    #set($hadir = "HADIR")   
    #end    
    #end    
    #end
        
    
        $hadir
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Nama PB</td>
        <td>:</td>
        <td>$list.NAMA_PB</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Nama PB (KP)</td>
        <td>:</td>
        <td>$list.NAMA_KP</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Jenis No. PB</td>
        <td>:</td>
        <td>$list.JENISNOPB</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>No. PB</td>
        <td>:</td>
        <td>$list.NO_PB</td>
      </tr>
    </table></td>
    <td width="50%" valign="top"><table width="100%">
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">No. Akaun</td>
        <td width="1%">:</td>
        <td width="70%">$list.NO_AKAUN</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Nama Bank</td>
        <td>:</td>
        <td>$list.NAMA_BANK</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Alamat PB</td>
        <td>:</td>
        <td>$list.ALAMAT1</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>$list.ALAMAT2</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>$list.ALAMAT3</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Poskod</td>
        <td>:</td>
        <td>$list.POSKOD</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Bandar</td>
        <td>:</td>
        <td>$list.NAMA_BANDAR</td>
      </tr>
       <tr>
        <td>&nbsp;</td>
        <td>Negeri</td>
        <td>:</td>
        <td>$list.NAMA_NEGERI</td>
      </tr>
    </table></td>
  </tr>
</table>
    </fieldset>

    </td>
   </tr>
   <tr  class="$row" >
    <td >$list.BIL</td>
    
    <td id="$list.NO_PB">
    <div align="left"><a href="javascript:paparpb('$list.ID_HAKMILIKPB');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">$list.NAMA_PB</font></a>
    
    <!--
      <div align="left"><a href="javascript:ShowPopupKehadiran('$list.BILTEMP');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">$list.NAMA_PB</font></a></div>
   -->
    
    </td>
    <td>$list.NO_PB</td>
    <td>$list.JENISPB</td>
    <td>
  
    
    #set($check_hadir = "")
    #if($list_check_kehadiran.size()>0)
    #foreach($ch in $list_check_kehadiran)    
    #if($ch.ID_HAKMILIKPB == $list.ID_HAKMILIKPB && $ch.FLAG_HADIR == "1")    
    #set($check_hadir = "checked")   
    #end    
    #end    
    #end
  
	 
    
      <div align="center">
        <input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1('$list_kehadiran.size()')" title="Semak untuk menunjukan '$list.NAMA_PB' hadir" $check_hadir value="$list.ID_HAKMILIKPB" >
        
        </div></td>
    
   
  </tr>
  #end
  #else
  <tr>  
    <td colspan="5">Tiada Rekod</td>    
  </tr>
  #end
</table>
</fieldset></td>

</tr>










<tr>
<td>

<fieldset id="senarai_siasatan_th">
<legend>TURUT HADIR</legend>


<table width="100%" >
 
<tr>

<td align="right" colspan="5">

<table width = "100%">
<tr>
<td>
<div align="left">
    <input type="button" value="Tambah Kehadiran (Turut Hadir)" onClick="tambahPB()">
  </div>
</td>
<td>
<div> 
#if($list_kehadiran_th.size() > 0)
  <div align="right">Kehadiran Lain-Lain : <span id="jumlahlot_th"  ></span> </div>
  #end
</div>
</td>
</tr>

</table>
  


</td>
</tr>

  <tr class="table_header">
  	<td width="5%">Bil</td>
    
    <td width="40%">Nama </td>
    <td width="20%">No. Pihak Berkepentingan</td>
    
    
    <td width="25%"><div align="left">Jenis Pihak Berkepentingan</div></td>
   
      #if($list_kehadiran_th.size() > 0)
    <td  width="10%"> 
      <div align="center">
      <input type="checkbox" name="all2" id="all2" onClick="doCheckAll2('$list_kehadiran_th.size()');" title="Semak untuk pilih semua kehadiran lain-lain" />
      </div></td>
      #end  </tr>
  
   #if($list_kehadiran_th.size() > 0)
  #foreach($list in $list_kehadiran_th)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
 
   <tr>
    <td colspan="5">
 
    <fieldset id="$list.BILTEMP" class="$row" style="visibility:collapse; display:none;">
     <table width="100%"   >
     <tr>
        <td width="1%">&nbsp;</td>
       
        <td width="99%"><div align="left"><a href="javascript:paparpb('$list.ID_HAKMILIKPB');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">KEMASKINI MAKLUMAT PB</font></a></div></td>
        </tr>
     </table>
    <table width="100%"   >
  <tr>
    <td width="50%" valign="top"><table width="100%">
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">Jenis Pihak Berkepentingan</td>
        <td width="1%" >:</td>
        <td width="70%">$list.JENISPB</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Kehadiran</td>
        <td>:</td>
        <td>
        
          
    #set($hadir = "TIDAK HADIR")
    #if($list_check_kehadiran.size()>0)
    #foreach($ch in $list_check_kehadiran)    
    #if($ch.ID_HAKMILIKPB == $list.ID_HAKMILIKPB && $ch.FLAG_HADIR == "1")    
    #set($hadir = "HADIR")   
    #end    
    #end    
    #end
        
    
        $hadir        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Nama PB</td>
        <td>:</td>
        <td>$list.NAMA_PB</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Nama PB (KP)</td>
        <td>:</td>
        <td>$list.NAMA_KP</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Jenis No. PB</td>
        <td>:</td>
        <td>$list.JENISNOPB</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>No. PB</td>
        <td>:</td>
        <td>$list.NO_PB</td>
      </tr>
    </table></td>
    <td width="50%" valign="top"><table width="100%">
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">No. Akaun</td>
        <td width="1%">:</td>
        <td width="70%">$list.NO_AKAUN</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Nama Bank</td>
        <td>:</td>
        <td>$list.NAMA_BANK</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Alamat PB</td>
        <td>:</td>
        <td>$list.ALAMAT1</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>$list.ALAMAT2</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>$list.ALAMAT3</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Poskod</td>
        <td>:</td>
        <td>$list.POSKOD</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Bandar</td>
        <td>:</td>
        <td>$list.NAMA_BANDAR</td>
      </tr>
       <tr>
        <td>&nbsp;</td>
        <td>Negeri</td>
        <td>:</td>
        <td>$list.NAMA_NEGERI</td>
      </tr>
    </table></td>
  </tr>
</table>
    </fieldset>    </td>
   </tr>
   <tr  class="$row" >
    <td >$list.BIL</td>
    
    <td id="$list.NO_PB">
    <div align="left"><a href="javascript:paparpb('$list.ID_HAKMILIKPB');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">$list.NAMA_PB</font></a>
    
       </td>
    <td>$list.NO_PB</td>
    <td>$list.JENISPB</td>
    <td>
  
    
    #set($check_hadir = "")
    #if($list_check_kehadiran.size()>0)
    #foreach($ch in $list_check_kehadiran)    
    #if($ch.ID_HAKMILIKPB == $list.ID_HAKMILIKPB && $ch.FLAG_HADIR == "1")    
    #set($check_hadir = "checked")   
    #end    
    #end    
    #end
  
	 
    
      <div align="center">
        <input type="checkbox" name="ids2" id="ids2" onClick="doUpdateCheckAll2('$list_kehadiran_th.size()')" title="Semak untuk menunjukan '$list.NAMA_PB' hadir"  $check_hadir value="$list.ID_HAKMILIKPB" >
        </div></td>
  </tr>
  #end
  #else
  <tr>  
    <td colspan="5">Tiada Rekod</td>    
  </tr>
  #end
</table>
</fieldset></td>
</tr>


</table>

#set($nama_pengarah= "HJ. CHE ROSLAN B. CHE DAUD")

<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
     #if($socStatusSiasatan!="") 
<!--
       <tr>
        <td><a href="#" class="style2" onClick="surat_pangil_AP('$id_fail','$id_siasatan','$id_pembatalan','$user_name')"><font color="blue">Surat Makluman Kepada Agensi Pemohon - Maklumat Supaya Hadir Untuk Siasatan</font></a></td>
      </tr>  
      -->
        <tr>
        <td><a href="#" class="style2" onClick="surat_pangil_AP1('$id_siasatan','$nama_pengarah','$!pemilik')"><font color="blue">Surat Makluman Kepada Agensi Pemohon - Maklumat Supaya Hadir Untuk Siasatan</font></a></td>
      </tr>  
       <tr>
        <td><a href="#" class="style2" onClick="surat_pangil_PB('$id_fail','$id_siasatan','$id_pembatalan','$nama_pengarah','$!pemilik')"><font color="blue">Surat Makluman Kepada Pihak Berkepentingan - Maklumat Supaya Hadir Untuk Siasatan</font></a></td>
      </tr> 
      <tr>
        <td><a href="#" class="style2" onClick="surat_pangil_JPPH('$id_siasatan','$nama_pengarah','$!pemilik')"><font color="blue">Surat Makluman Kepada JPPH - Maklumat Supaya Hadir Untuk Siasatan</font></a></td>
      </tr>  
      
     <tr>
        <td><a href="#" class="style2" onClick="surat_pangil_PEMILIK('$id_fail','$id_siasatan','$id_pembatalan','$nama_pengarah','$!pemilik')"><font color="blue">Surat Panggil Siasatan Penarikan - Sek. 35</font></a></td>
      </tr> 
      
       <tr>
        <td><a href="#" class="style2" onClick="surat_pangil_PTG('$id_siasatan','$nama_pengarah','$!pemilik')"><font color="blue">Surat - PTG Catatan Penarikan Balik Dalam DHD</font></a></td>
      </tr> 
      
        <tr>
        <td><a href="#" class="style2" onClick=" mmkSelangor3('$id_pembatalan','$nama_pengarah')"><font color="blue">Jadual Penarikan Balik Lot</font></a></td>
      </tr> 
      
      
     
        #end 
        <!--
        #if($socStatusSiasatan=="4") 
        <tr>
        <td><a href="#" class="style2" onClick="AP_HadirUlangSiasatan_PB('$id_fail','$id_siasatan','$id_pembatalan','$user_name')"><font color="blue">Surat Makluman Kepada Agensi Pemohon - Maklumat Supaya Hadir Untuk Ulang Siasatan</font></a></td>
      </tr>  
       <tr>
        <td><a href="#" class="style2" onClick="PB_HadirUlangSiasatan_PB('$id_fail','$id_siasatan','$id_pembatalan','$user_name')"><font color="blue">Surat Makluman Kepada Pihak Berkepentingan - Maklumat Supaya Hadir Untuk Ulang Siasatan</font></a></td>
      </tr>     
      #end
      -->    
    </table>
</fieldset>

  <input type="hidden" name="sub_command" id="sub_command" />
  <input type="hidden" name="subminor_command" id="subminor_command" />
  <input type="hidden" name="location" id="location" />
  <input type="hidden" name="point" id="point" />
  <input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$!id_pembatalan" />
  <input type="hidden" name="screen_name" id="screen_name" value="s2" />
  <input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
  <input type="hidden" name="alert_message" id="alert_message" />
  <input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
  <input type="hidden" name="jumlah_semua" id="jumlah_semua" />
  <input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" />
  <input type="hidden" name="id_tanahumum" id="id_tanahumum" value="$!id_tanahumum" />
  <input type="hidden" name="id_siasatan" id="id_siasatan" value="$!id_siasatan" />
  <input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$!id_hakmilikpb" />
  <input type="hidden" name="id_pb" id="id_pb" value="$!id_pb" />
  <input type="hidden" name="id_kehadiran" id="id_kehadiran" value="$!id_kehadiran" />
  
  <script>
  
  
function kembaliKehadiran(id_permohonan,id_pembatalan)
{
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.submit();
}
  
function cetakSenarai(idpermohonan,id_siasatan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=senaraiKehadiran&selectNoFail=yes&id_siasatan="+id_siasatan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratPanggilanPerundinganSementara(id_fail,id_permohonan,id_siasatan,id_hakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?idFail="+id_fail+"&id_permohonan="+id_permohonan+"&report=SuratPanggilanPerundinganSementara&flagReport=S&id_siasatan="+id_siasatan+"&id_hakmilik="+id_hakmilik;		
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function cetakSuratPanggilanPerundinganSementaraAgensi(id_fail,id_permohonan,id_siasatan,id_hakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?idFail="+id_fail+"&id_permohonan="+id_permohonan+"&report=SuratPanggilanPerundinganSementaraAgensi&flagReport=S&id_siasatan="+id_siasatan+"&id_hakmilik="+id_hakmilik;		
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function cetakSuratPanggilanPerundinganSementaraSambunganAgensi(id_fail,id_permohonan,id_siasatan,id_hakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?idFail="+id_fail+"&id_permohonan="+id_permohonan+"&report=SuratPanggilanPerundinganSementaraSambunganAgensi&flagReport=S&id_siasatan="+id_siasatan+"&id_hakmilik="+id_hakmilik;		
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function cetakSuratPanggilanPerundinganSementaraSambunganPB(id_fail,id_permohonan,id_siasatan,id_hakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?idFail="+id_fail+"&id_permohonan="+id_permohonan+"&report=SuratPanggilanPerundinganSementaraSambunganPB&flagReport=S&id_siasatan="+id_siasatan+"&id_hakmilik="+id_hakmilik;		
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function cetakSuratPanggilanPerundinganSementaraPB(id_fail,id_permohonan,id_siasatan,id_hakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?idFail="+id_fail+"&id_permohonan="+id_permohonan+"&report=SuratPanggilanPerundinganSementaraPB&flagReport=S&id_siasatan="+id_siasatan+"&id_hakmilik="+id_hakmilik;		
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}



function cetakSuratPanggilanPerundinganSambunganSementara(id_fail,id_permohonan,id_siasatan,id_hakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?idFail="+id_fail+"&id_permohonan="+id_permohonan+"&report=SuratPanggilanPerundinganSambunganSementara&flagReport=S&id_siasatan="+id_siasatan+"&id_hakmilik="+id_hakmilik;		
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	

	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
/*	var url = "../servlet/ekptg.report.ppt.SuratKepadaJPPHSupayaMenghadiriPerundingan?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();*/	
}

</script>
  
  
  
 #if((($socJenisPB == "42" || $socJenisPB == "40" || $socJenisPB == "41" || $socJenisPB == "27" ) || (($tambah_kehadiran == "yes" || $tambah_kehadiran_negeri == "yes") && ($socJenisPB == "")))|| (($socJenisPB == "6"  ) || (($tambah_kehadiran_wakil == "yes" || $tambah_kehadiran_negeri_wakil == "yes") &&( $socJenisPB == ""))))
  
  <script type="text/javascript">
  var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
  window.onload = submitForm1;
  
  function submitForm1() 
{


if('$location' != "" && '$location' != null && '$point' != "" && '$point' != null)
{
window.location.hash='$location';
goTo('$point');
}
else
{
window.location.hash='paging';
goTo('paging');
}


var lk = '$list_kehadiran.size()';
var lkh = '$list_kehadiran_th.size()';

if(lk > 0)
{
doUpdateCheckAll1('$list_kehadiran.size()');
}
if(lkh > 0)
{
doUpdateCheckAll2('$list_kehadiran_th.size()');
//alert('$list_kehadiran_th.size()')
}

checking_validation(document.${formName}.socJenisPB,'socJenisPB_check','yes','jenis pihak berkepentingan','normal');
checking_validation(document.${formName}.txtPBhadir,'txtPBhadir_check','yes','kehadiran','radio');
checking_validation(document.${formName}.txtNamaPB,'txtNamaPB_check','yes','nama pihak berkepentingan','normal');
checking_validation(document.${formName}.txtNamaPBKP,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal');
checking_validation(document.${formName}.socJenisNOPB,'socJenisNOPB_check','no','jenis no. pihak berkepentingan','normal_j');
checking_validation(document.${formName}.txtNoPB,'txtNoPB_check','no','no PB','normal_kp');
checking_validation(document.${formName}.txtNoAkaun,'txtNoAkaun_check','no','no akaun','normal');
checking_validation(document.${formName}.socJenisBank,'socJenisBank_check','no','bank','normal');
checking_validation(document.${formName}.txtAlamat1PB,'txtAlamat1PB_check','no','alamat PB','normal');
checking_validation(document.${formName}.txtPoskodPB,'txtPoskodPB_check','no','','poskod');
checking_validation(document.${formName}.socNegeri,'socNegeri_check','no','negeri','normal');
checking_validation(document.${formName}.socBandar,'socBandar_check','no','bandar','normal');
checking_validation(document.${formName}.txtNoHP,'txtNoHP_check','no','no telefon bimbit','normal')
checking_validation(document.${formName}.txtNoTel,'txtNoTel_check','no','no telefon rumah / pejabat','normal')
checking_validation(document.${formName}.txtNamaBank,'txtNamaBank_check','no','nama bank','normal')
check_length(document.${formName}.txtKeteranganPB,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');



}
  </script>
  
  #else
  
   <script type="text/javascript">
   var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
  window.onload = submitForm;
  
  function submitForm() 
{


if('$location' != "" && '$location' != null && '$point' != "" && '$point' != null)
{
window.location.hash='$location';
goTo('$point');
}
else
{
window.location.hash='paging';
goTo('paging');
}


var lk = '$list_kehadiran.size()';
var lkh = '$list_kehadiran_th.size()';

if(lk > 0)
{
doUpdateCheckAll1('$list_kehadiran.size()');
}
if(lkh > 0)
{
doUpdateCheckAll2('$list_kehadiran_th.size()');
//alert('$list_kehadiran_th.size()')
}

checking_validation(document.${formName}.socJenisPB,'socJenisPB_check','yes','jenis pihak berkepentingan','normal');
checking_validation(document.${formName}.txtPBhadir,'txtPBhadir_check','yes','kehadiran','radio');
checking_validation(document.${formName}.txtNamaPB,'txtNamaPB_check','yes','nama pihak berkepentingan','normal');
checking_validation(document.${formName}.txtNamaPBKP,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal');
checking_validation(document.${formName}.socJenisNOPB,'socJenisNOPB_check','no','jenis no. pihak berkepentingan','normal_j');
checking_validation(document.${formName}.txtNoPB,'txtNoPB_check','no','no PB','normal_kp');
checking_validation(document.${formName}.txtNoAkaun,'txtNoAkaun_check','no','no akaun','normal');
checking_validation(document.${formName}.socJenisBank,'socJenisBank_check','no','bank','normal');
checking_validation(document.${formName}.txtAlamat1PB,'txtAlamat1PB_check','no','alamat PB','normal');
checking_validation(document.${formName}.txtPoskodPB,'txtPoskodPB_check','no','','poskod');
checking_validation(document.${formName}.socNegeri,'socNegeri_check','no','negeri','normal');
checking_validation(document.${formName}.socBandar,'socBandar_check','no','bandar','normal');
checking_validation(document.${formName}.txtNoHP,'txtNoHP_check','no','no telefon bimbit','normal')
checking_validation(document.${formName}.txtNoTel,'txtNoTel_check','no','no telefon rumah / pejabat','normal')
checking_validation(document.${formName}.txtNamaBank,'txtNamaBank_check','no','nama bank','normal')
check_length(document.${formName}.txtKeteranganPB,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');



}


  </script>
  #end
  
<script type="text/javascript">
  









/*
function cari_lot(field)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'bolder';
hp.style.fontStyle = 'italic';
window.location.hash=field;
goTo(field);
}
}
function cari_lot1(field)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'normal';
hp.style.fontStyle = 'normal';
}
document.${formName}.CariLot.value="";
}
*/



function doCheckAll2(sudahbatal){  
var cc = 0;  
    if (document.${formName}.all2.checked == true){
        if (document.${formName}.ids2.length == null){
		cc++;
            document.${formName}.ids2.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids2.length; i++){
                document.${formName}.ids2[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.ids2.length == null){
            document.${formName}.ids2.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids2.length; i++){
                document.${formName}.ids2[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	/*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot_th";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       
       $jquery("#jumlahlot_th").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
}
function doUpdateCheckAll2(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.ids2.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids2.length; i++)
	  {
      if (document.${formName}.ids2[i].checked == false)
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
if (document.${formName}.ids2.checked == false)
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
	  document.${formName}.all2.checked = false;
	  }
	  else
	  {
	  document.${formName}.all2.checked = true;
	  }
	  
	  
	  
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot_th";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot_th").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
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
	  
	  
	  
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
       
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
}



function checking_validation(field,point,mandatory,value_field,jenis_field){	



   	var lepas_or_xlepas = 1;
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 4000 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   	
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	 
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
       /*	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   else
	   {
	  
	   /*
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini");
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;

	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName); */
	//   DateField.focus();
    
     $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+" dengan betul");	
	 
	   
	   }
	   
	   }
	   
	// 
	   if(jenis_field == "normal")
	   {
	   
	  
		
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
	 	
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
	 		
	   }
	   
	   	   
	   }
	   
       
       
       
       
       if(jenis_field == "normal_kp")
	   {
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   if(lepas_or_xlepas == "2")
	   {      
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	 	
	   }
	   else
	   {
        
       if(document.${formName}.socJenisNOPB.value == "1" )
       {
       
        field.value = field.value.substring(0,12);	       
        if (isNaN(field.value)) {
            field.value = RemoveNonNumeric2(field.value);
            
        }  
             
       if(field.value.length != 12 )
       {
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila lengkapkan kad pengenalan baru pb");         
       }
       else
       {
       
       
    if(field.value.charAt(0)<2)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}

	
var tt = field.value.charAt(4)+""+field.value.charAt(5)+"/"+field.value.charAt(2)+""+field.value.charAt(3)+"/"+dum+field.value.charAt(0)+""+field.value.charAt(1);
       
       
    var checkstr = "0123456789";
	var DateField = tt;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
        err = 0;
	
	
	   DateValue = tt;	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField = day + seperator + month + seperator + year;
		   
	   }
	  
	   else {	   	
		  lepas_or_xlepas = "3";	   
	   }
	  



	 
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = tt;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
       
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   else
	   {	  
	  
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh lahir "+value_field+" tidak melebihi dari tarikh hari ini");
	   }
	   
	   
	   }   
	   else if(lepas_or_xlepas == "3")
	   {    
     $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh lahir "+value_field+" dengan format betul");	
	 	   
	   }
	   else
	{
      $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
    }
     	
       }
       
       }
       else
       {
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
       }      
        		
	   }
	   
	   	   
	   }
	   
       
       if(jenis_field == "normal_j")
	   {
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   if(lepas_or_xlepas == "2")
	   {      
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	 	
	   }
	   else
	   {
        
       if(field.value == "1" )
       {
       
        document.${formName}.txtNoPB.value = document.${formName}.txtNoPB.value.substring(0,12);	       
        if (isNaN(document.${formName}.txtNoPB.value)) {
            document.${formName}.txtNoPB.value = RemoveNonNumeric2(document.${formName}.txtNoPB.value);
          
        }  
         
         
       if(document.${formName}.txtNoPB.value.length != 12 )
       {
       $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila lengkapkan kad pengenalan baru pb");         
       }
       else
       {
       
   		
  if(document.${formName}.txtNoPB.value.charAt(0)<2)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}

	
var tt = document.${formName}.txtNoPB.value.charAt(4)+""+document.${formName}.txtNoPB.value.charAt(5)+"/"+document.${formName}.txtNoPB.value.charAt(2)+""+document.${formName}.txtNoPB.value.charAt(3)+"/"+dum+document.${formName}.txtNoPB.value.charAt(0)+""+document.${formName}.txtNoPB.value.charAt(1);
       
       
    var checkstr = "0123456789";
	var DateField = tt;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
        err = 0;
	
	
	   DateValue = tt;	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField = day + seperator + month + seperator + year;
		   
	   }
	  
	   else {	   	
		  lepas_or_xlepas = "3";	   
	   }
	  
       
		
        
        
	   if(lepas_or_xlepas == "1")
	   {
	      
          
           var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = tt;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
       
        $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   else
	   {	  
	  
      $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh lahir "+value_field+" tidak melebihi dari tarikh hari ini");
	   }          
	   } 
        else if(lepas_or_xlepas == "3")
	   {    
     $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh lahir "+value_field+" dengan format betul");	
	 	   
	   }
	   else
	{
      $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
    }
     	
        


     
   
       }
       }
       else
       {
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
       }      
        		
	   }
	   
	   	   
	   }
	   
	   
	   	   if(jenis_field == "radio")
	   {
	  
	  
	    var r = 0;
		if(field.length > 1)
		{     
			  for (i = 0; i < field.length; i++)
			  {
			  if (field[i].checked == true)
			  {	 
			  r++
			  }
			  }  
		}
		else
		{
		if (field.checked == true)
		{	 
		r++;
		}	 	
		}  
	   
	     
	   if((r == 0) && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	  
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila pilih "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
	 	
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	 
	   }
	   
	   	   
	   }
	   
	   
	   
	    if(jenis_field == "file") 
	    {
	   	var allBlank = true;
		if(document.${formName}.fileupload!=null)
		{
		
		for( var i=0,e=document.${formName}.fileupload;i<e.length; i++ )
		{			
		if( e[i].type=='file' )
		{	
		if( e[i].value.length  )
		{
		
		allBlank=false;	
		}	
		}	
		}
		
		}
		else
		{
		allBlank=false;	
		}
		
		
		if(allBlank == true)
	    {
        /*
		   document.${formName}.alert_message.value  = "Sila masukkan dokumen terlebih dahulu";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);*/
           $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");	
	 	
	    }
		else
		{
		  /* document.${formName}.alert_message.value  = "";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		
		*/
         $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	 	
		}
		
		
	   
	   }
	   
	   
	   
	   if(jenis_field == "waktu")
	   {
	   
		
	if(field.value == "" && mandatory == "yes")
	{
	lepas_or_xlepas = 2;
    }  
	else if(field.value != "" && (mandatory == "yes" || mandatory == "no"))
	{	   
	if (field.value.length < 4) {
	lepas_or_xlepas = 3;
	}
	else if (field.value.charAt(0) > 1)  {
	lepas_or_xlepas = 3;
	}	
	else if ((field.value.charAt(0) == 1) && (field.value.charAt(1) >2 )) {
	lepas_or_xlepas = 3;
	}
	else if ((field.value.charAt(2) > 5) ) {
	lepas_or_xlepas = 3;
	}
	else if ((field.value.charAt(3) > 9) ) {
	lepas_or_xlepas = 3;
	}
	}  
	  
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan waktu "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");	
	 		
	   }
	   else if(lepas_or_xlepas == "3")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan waktu "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan waktu "+value_field+" dengan format yang betul");	
	 	
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
         $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   
	   	    
	   }
	   
	   
	   
	   
	   if(jenis_field == "poskod")
	   {
	   
		
	if(field.value == "" && mandatory == "yes")
	{
	lepas_or_xlepas = 2;
    }  
	else if(field.value != "" && (mandatory == "yes" || mandatory == "no"))
	{	   
	if (field.value.length < 5) {
	lepas_or_xlepas = 3;
	}	
	}  
	  
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan poskod "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");		
	   }
	   else if(lepas_or_xlepas == "3")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan poskod "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan poskod "+value_field+" dengan format yang betul");		
		
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   
	   	    
	   }
	   
	 
	   
	
}


function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
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




function jeniswaktu1(time_field,jenis_time)
{
document.getElementById(jenis_time).value = "";
var vm = time_field.value;
if(vm != "" && vm.length == 4)
{
var vm_m = 0;

if(vm.charAt(0) == "0")
{
   var vm_c = vm.charAt(1) + vm.charAt(2) +vm.charAt(3);
   vm_m = parseInt(vm_c)
}
else
{
   vm_m = parseInt(vm)
}

if(vm_m >= 1200 && vm_m <= 1259 )
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";

}
else if(vm_m >= 100 && vm_m <= 659 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="none";
}
else if(vm_m >= 700 && vm_m <= 1159 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";
}
else
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="none";
}


}
else
{

document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="";
}




}





function jeniswaktu2(time_field)
{


var vm = document.getElementById(time_field).value;


if(vm != "" && vm.length == 4)
{
var vm_m = 0;


if(vm.charAt(0) == "0")
{
   var vm_c = vm.charAt(1) + vm.charAt(2) +vm.charAt(3);
   vm_m = parseInt(vm_c)
}
else
{
   vm_m = parseInt(vm)
}

if(vm_m >= 1200 && vm_m <= 1259 )
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";

}
else if(vm_m >= 100 && vm_m <= 659 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="none";
}
else if(vm_m >= 700 && vm_m <= 1159 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";
}
else
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="none";
}



}
else
{


document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="";
}




}

function getBandarSiasatan(id_hakmilik,id_pembatalan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "getBandar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "socBandarSiasatan";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}


function tambah(id_hakmilik,id_pembatalan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "tambah";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}


function simpan(id_siasatan)
{
var c = 0;

if(document.${formName}.validation_field != null  )
{

   if (document.${formName}.validation_field.length == null)
   {	
    
   if(document.${formName}.validation_field.value == "invalid")
   {  
   
   c++;	
   } 
   	
   } 
   
   else 
   {
   
        for (i = 0; i < document.${formName}.validation_field.length; i++)
		{		
			if(document.${formName}.validation_field[i].value == "invalid")
			{
			
               c++;	 
			}  	
        }
    }	
}


if(c>0){
alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
return;
}
else
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	
	if(id_siasatan == "")
	{
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
	}
	else
	{
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	}
	
	document.${formName}.submit();
	}
	}
}


function papar(id_siasatan)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();
	
}


function kemaskini(id_siasatan)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.submit();
	
}



function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";	
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.submit();
	}
}

function hapus_beramai()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Hapus_beramai";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";	
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.submit();
	}
}




function ShowPopupSiasatan(tab)
{

hp = document.getElementById(tab);
//hp.style.display=="none" &&
	if( hp.style.visibility=="collapse" && hp.style.display=="none"){
		hp.style.display = "block";
		hp.style.visibility = "visible";
	}
	//hp.style.display=="block" &&
	else if( hp.style.visibility=="visible" && hp.style.display=="block"){
		hp.style.display = "none";
		hp.style.visibility = "collapse";
	}
	

}


function ShowPopupKehadiran(tab)
{

hp = document.getElementById(tab);
//hp.style.display=="none" &&
	if( hp.style.visibility=="collapse" && hp.style.display=="none"){
		hp.style.display = "block";
		hp.style.visibility = "visible";
	}
	//hp.style.display=="block" &&
	else if( hp.style.visibility=="visible" && hp.style.display=="block"){
		hp.style.display = "none";
		hp.style.visibility = "collapse";
	}
	

}


function cari_lot(field)
{


hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'bolder';
hp.style.fontStyle = 'italic';
window.location.hash=field;
goTo(field);
}
else
{

if(field=="")
{
alert("Pastikan no. pb dimasukkan");
}
else 
{
alert("Tiada rekod untuk pb bernombor "+field);
}
}
}
function cari_lot1(field)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'normal';
hp.style.fontStyle = 'normal';
}
document.${formName}.CariLot.value="";
}


function Update_kehadiran()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "Update_kehadiran";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
	document.${formName}.submit();
	}
}

function paparpb(id_hakmilikpb)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.submit();
}


function kemaskinipb(id_hakmilikpb)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.submit();
}

function getBandar(id_hakmilikpb,id_pembatalan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "getBandar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socBandar";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}

function batal(id_hakmilikpb)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;	
	document.${formName}.submit();
	}
}

function tambahPB()
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "tambah";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();
	
}
//simpanPB('$id_siasatan','$id_hakmilikpb','$id_kehadiran','$id_pb')

function simpanPB(id_siasatan,id_hakmilikpb,id_kehadiran,id_pb)
{
var c = 0;

if(document.${formName}.validation_field != null  )
{

   if (document.${formName}.validation_field.length == null)
   {	
    
   if(document.${formName}.validation_field.value == "invalid")
   {  
   
   c++;	
   } 
   	
   } 
   
   else 
   {
   
        for (i = 0; i < document.${formName}.validation_field.length; i++)
		{		
			if(document.${formName}.validation_field[i].value == "invalid")
			{
			
               c++;	 
			}  	
        }
    }	
}


if(c>0){
alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
return;
}
else
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	if(id_hakmilikpb!="")	
	{
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	}
	else
	{
	document.${formName}.location.value = "senarai_siasatan_th";
	document.${formName}.point.value = "senarai_siasatan_th";
	}
	
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.id_kehadiran.value = id_kehadiran;
	document.${formName}.id_pb.value = id_pb;	
	
	document.${formName}.submit();
	
	}
}
}



function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		//window.location.hash=id;
        //goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}


function hapusTurutHadir(id_hakmilikpb,id_pb,id_kehadiran)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "DeleteTurutHadir";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "senarai_siasatan_th";
	document.${formName}.point.value = "senarai_siasatan_th";	
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.id_kehadiran.value = id_kehadiran;
	document.${formName}.id_pb.value = id_pb;
	document.${formName}.submit();
    }
}

function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
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



function surat_pangil_AP(id_fail,id_siasatan,id_penarikan,nama_pegawai)
{
    var url = "../servlet/ekptg.report.ppt.AP_HadirSiasatan_PB?id_fail="+id_fail+"&id_siasatan="+id_siasatan+"&id_penarikan="+id_penarikan+"&nama_pegawai="+nama_pegawai;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function surat_pangil_AP1(id_siasatan,nama_pegawai,pemilik)
{
    var url = "../servlet/ekptg.report.ppt.AP_HadirSiasatan_PB?id_siasatan="+id_siasatan+"&nama_pegawai="+nama_pegawai+"&pemilik="+pemilik;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function surat_pangil_JPPH(id_siasatan,nama_pegawai,pemilik)
{
    var url = "../servlet/ekptg.report.ppt.JPPH_HadirSiasatan_PB?id_siasatan="+id_siasatan+"&nama_pegawai="+nama_pegawai+"&pemilik="+pemilik;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function surat_pangil_PTG(id_siasatan,nama_pegawai,pemilik)
{
    var url = "../servlet/ekptg.report.ppt.PTG_HadirSiasatan_PB?id_siasatan="+id_siasatan+"&nama_pegawai="+nama_pegawai+"&pemilik="+pemilik;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


function surat_pangil_PB(id_fail,id_siasatan,id_penarikan,nama_pegawai,pemilik)
{
    var url = "../servlet/ekptg.report.ppt.PB_HadirSiasatan_PB?id_fail="+id_fail+"&id_siasatan="+id_siasatan+"&id_penarikan="+id_penarikan+"&nama_pegawai="+nama_pegawai+"&pemilik="+pemilik;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function surat_pangil_PEMILIK(id_fail,id_siasatan,id_penarikan,nama_pegawai,pemilik)
{
    var url = "../servlet/ekptg.report.ppt.PEMILIK_HadirSiasatan_PB?id_fail="+id_fail+"&id_siasatan="+id_siasatan+"&id_penarikan="+id_penarikan+"&nama_pegawai="+nama_pegawai+"&pemilik="+pemilik;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}




function AP_HadirUlangSiasatan_PB(id_fail,id_siasatan,id_penarikan,nama_pegawai)
{
    var url = "../servlet/ekptg.report.ppt.AP_HadirUlangSiasatan_PB?id_fail="+id_fail+"&id_siasatan="+id_siasatan+"&id_penarikan="+id_penarikan+"&nama_pegawai="+nama_pegawai;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function PB_HadirUlangSiasatan_PB(id_fail,id_siasatan,id_penarikan,nama_pegawai)
{
    var url = "../servlet/ekptg.report.ppt.PB_HadirUlangSiasatan_PB?id_fail="+id_fail+"&id_siasatan="+id_siasatan+"&id_penarikan="+id_penarikan+"&nama_pegawai="+nama_pegawai;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


function mmkSelangor3(id_penarikan,nama_pengarah)
{
           var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
		   
    var url = "../servlet/ekptg.report.ppt.MmkPenarikanBalikSelangor3?id_penarikan="+id_penarikan+"&nama_pengarah="+nama_pengarah; 
 
  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();


}



function screen5(id_permohonan,id_pembatalan)
{
	
	
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
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
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
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




function tambahWakil()
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "tambah_wakil";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();
	
}

</script>
  
