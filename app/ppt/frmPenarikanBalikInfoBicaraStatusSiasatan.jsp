<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>


#set($socPegawaiSiasatan = "")
#set($socStatusSiasatan = "")
#set($txtUlasanSiasatan = "")


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
#parse("app/ppt/paging_penarikanbalik.jsp")
<table width="100%">
  
   <tr>
<td>#parse("app/ppt/header_ppt.jsp") </td>
  </tr>
  <tr>
<td>#parse("app/ppt/header_penarikanbalik_siasatan.jsp") </td>



  </tr>
<tr>
<td>





<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0"  onClick="screen5('$id_permohonan','$id_pembatalan')">Kembali</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuantanah('$id_siasatan')" id="tuan_tanah" style="display:none" >Keterangan Tuan Tanah</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="agensi('$id_siasatan')"  id="Agensi" >Agensi / Jurunilai</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuntutan('$id_siasatan')">Tuntutan</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="bantahan('$id_siasatan')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="nilaian('$id_siasatan')" >Kerosakan</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="status('$id_siasatan')"  >Status Siasatan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="keputusan('$id_siasatan')" id="perintah_keputusan" >Perintah</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
 
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
   <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
     <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
   <fieldset>
   <table width="100%">
  <tr>
    <td  valign="top"><table width="100%">
      <tr>
        <td width="1%" valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%" valign="top">Pegawai Siasatan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
         #if($readmode == "view" ) 
     #if($socPegawaiSiasatan=="")
     #set($PegawaiSiasatan="")                             
     #else                             
     #foreach($ln in $list_pegawai)      
     #if($socPegawaiSiasatan==$ln.ID_PEGAWAI)                                      
     #set($PegawaiSiasatan=$ln.NAMA_PEGAWAI)
     #end
     #end                            
     #end    
     $PegawaiSiasatan  
     <input name="PegawaiSiasatan" type="hidden" id="PegawaiSiasatan" value="$PegawaiSiasatan" size="50" readonly class="disabled">              
    #else    
    <select name="socPegawaiSiasatan" class="autoselect" id="socPegawaiSiasatan"  onchange="checking_validation(this,'socPegawaiSiasatan_check','yes','pegawai','normal');" >  
    #if($socPegawaiSiasatan != "")
    
    #foreach($ln in $list_pegawai) 
    #if($socPegawaiSiasatan==$ln.ID_PEGAWAI)
    <option value="$ln.ID_PEGAWAI">$ln.NAMA_PEGAWAI</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_pegawai)
    #if($socPegawaiSiasatan!=$ln.ID_PEGAWAI)
    <option value="$ln.ID_PEGAWAI">$ln.NAMA_PEGAWAI</option>                                     
    #end      
    #end
    <option value="">SILA PILIH PEGAWAI SIASATAN</option> 
    
    #else
   
    <option value="">SILA PILIH PEGAWAI SIASATAN</option>        
    #foreach($ln in $list_pegawai)   
    <option value="$ln.ID_PEGAWAI">$ln.NAMA_PEGAWAI</option>
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socPegawaiSiasatan_check" class="alert_msg" ></span>    
              </td>
      </tr>
      <tr  >
        <td width="1%" valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%" valign="top">Status Siasatan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        #if($readmode == "view" ) 
     #if($socStatusSiasatan=="")
     #set($StatusSiasatan="")                             
     #else
     #if($socStatusSiasatan=="1")
     #set($StatusSiasatan="SIASATAN PERMULAAN")    
     #elseif($socStatusSiasatan=="2") 
     #set($StatusSiasatan="DITUNDA SEBELUM SIASATAN")    
     #elseif($socStatusSiasatan=="3") 
     #set($StatusSiasatan="DIBATALKAN")    
     #elseif($socStatusSiasatan=="4") 
     #set($StatusSiasatan="SIASATAN ULANGAN")    
     #elseif($socStatusSiasatan=="5") 
     #set($StatusSiasatan="SIASATAN SAMBUNGAN")   
     #elseif($socStatusSiasatan=="7") 
     #set($StatusSiasatan="TANGGUH SIASATAN") 
     #elseif($socStatusSiasatan=="6")                             
     #set($StatusSiasatan="SELESAI")                
     #end 
     #end
          
    <input name="StatusSiasatan" type="text" id="StatusSiasatan" value="$StatusSiasatan" size="50" readonly class="disabled">              
    #else    
    <select name="socStatusSiasatan" class="autoselect" id="socStatusSiasatan"  onchange="checking_validation(this,'socStatusSiasatan_check','yes','status siasatan','normal');checking_validation_tangguh(this.value);check_stat()" >  
    #if($socStatusSiasatan != "")
     
   #if($socStatusSiasatan == "1")
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #elseif($socStatusSiasatan == "2")
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option>
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #elseif($socStatusSiasatan == "3")
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option>
    <option value="5">SIASATAN SAMBUNGAN</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #elseif($socStatusSiasatan == "4")
    <option value="4">SIASATAN ULANGAN</option>
    <option value="5">SIASATAN SAMBUNGAN</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option>     
    <option value="3">DIBATALKAN</option>
     <option value="">SILA PILIH STATUS SIASATAN</option>  
    #elseif($socStatusSiasatan == "5")
    <option value="5">SIASATAN SAMBUNGAN</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option> 
    #elseif($socStatusSiasatan == "7")
    <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>   
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    
    #elseif($socStatusSiasatan == "6")
    <option value="6">SELESAI</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #end
    
    #else
   
    <option value="">SILA PILIH STATUS SIASATAN</option> 
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>       
   
    
    #end
    
    </select>  
             
    #end
    
    
     <span id="socStatusSiasatan_check" class="alert_msg" ></span>
        
              </td>
      </tr>
      
      
      
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Ulasan Siasatan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
       
         <textarea name="txtUlasanSiasatan" id="txtUlasanSiasatan" cols="60"   rows="6"          
         onBlur="check_length(this,'4000','txtUlasanSiasatan_check','txtUlasanSiasatan_num','normal','no','ulasan siasatan');"  
         onKeyup="check_length(this,'4000','txtUlasanSiasatan_check','txtUlasanSiasatan_num','normal','no','ulasan siasatan');" 
         onKeydown="check_length(this,'4000','txtUlasanSiasatan_check','txtUlasanSiasatan_num','normal','no','ulasan siasatan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtUlasanSiasatan</textarea> 
          
        
         #if($readmode == "edit")           
        <div><span id="txtUlasanSiasatan_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtUlasanSiasatan_num" id="txtUlasanSiasatan_num" size="3" value="4000"  style=" display:none"  > 
         #end
  <div id="txtUlasanSiasatan_check" class="alert_msg" ></div> 
         
               </td>
      </tr>
      
      
      
     
      <tr id="id_tangguh" >
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Ulasan Tangguh Siasatan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
      
    
#if($list_subsiasatan.size() > 0) 
#foreach($list in $list_subsiasatan)
<input type='hidden' name='temp_temp1'  id='temp_temp1' value='$list.KETERANGAN_SUBSIASATAN' > 
#end
#end

       
           
            
              
               <span id="textlocation">
              
             
               </span>  
      
        
         <div id="textlocation_temp"></div>   
           
        </td>
      </tr>
    
      
      
#if($maklumat_siasatanX.size() == 0 && $readmode == "view")
#set($txtNoKesX = "")
#set($txtNoSiasatanX = "")
#set($txtTempatSiasatanX = "")
#set($txtPoskodX = "")
#set($txtAlamat1X = "")
#set($txtAlamat2X = "")
#set($txtAlamat3X = "")
#set($socNegeriSiasatanX = "")
#set($socBandarSiasatanX = "")
#set($socStatusSiasatanX = "")
#set($txdTarikhSiasatanX = "")
#set($txtMasaSiasatanX = "")
#set($txtTkhAkhirNotisX = "")
#set($jeniswaktuX = "")
#set($id_siasatanX = "")
#set($bil_siasatanX = "")
#end
      
      <tr id="id_tangguh1">
<td colspan="4">

  <fieldset id="maklumat_siasatan_ulang">
  <legend>MAKLUMAT SIASATAN ULANGAN</legend>
  <table width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No. Kes</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNoKes" type="text" id="txtNoKes" value="$txtNoKesX" size="25" maxlength="25"  onBlur="checking_validation(this,'txtNoKes_check','no','no kes','normal')" onKeyUp="checking_validation(this,'txtNoKes_check','no','no kes','normal')"  $readonlymode class = "$disabledmode">
     <span id="txtNoKes_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Siasatan</td>
    <td>:</td>
    <td><input name="txtNoSiasatan" type="text" id="txtNoSiasatan" value="$txtNoSiasatanX" size="25" maxlength="25"  onBlur="checking_validation(this,'txtNoSiasatan_check','no','no siasatan','normal')" onKeyUp="checking_validation(this,'txtNoSiasatan_check','no','no siasatan','normal')"  $readonlymode class = "$disabledmode">
     <span id="txtNoSiasatan_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tempat Siasatan</td>
    <td>:</td>
    <td><input name="txtTempatSiasatan" type="text" id="txtTempatSiasatan" value="$txtTempatSiasatanX" size="50" onBlur="checking_validation(this,'txtTempatSiasatan_check','no','tempat siasatan','normal')" onKeyUp="checking_validation(this,'txtTempatSiasatan_check','no','tempat siasatan','normal')"  $readonlymode class = "$disabledmode">
    <span id="txtTempatSiasatan_check" class="alert_msg"></span>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Alamat</td>
    <td>:</td>
    <td><input name="txtAlamat1" type="text" id="txtAlamat1" value="$txtAlamat1X" size="50" onBlur="checking_validation(this,'txtAlamat1_check','no','alamat','normal')" onKeyUp="checking_validation(this,'txtAlamat1_check','no','alamat','normal')"  $readonlymode class = "$disabledmode">
    <span id="txtAlamat1_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td><input name="txtAlamat2" type="text" id="txtAlamat2" value="$txtAlamat2X" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td><input name="txtAlamat3" type="text" id="txtAlamat3" value="$txtAlamat3X" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Poskod</td>
    <td>:</td>
    <td><input name="txtPoskod" type="text" id="txtPoskod" onBlur="checking_validation(this,'txtPoskod_check','no','','poskod');validateTarikh(this,this.value)" onKeyUp="checking_validation(this,'txtPoskod_check','no','','poskod');validateTarikh(this,this.value)" value="$txtPoskodX" size="6" maxlength="5"  $readonlymode class = "$disabledmode">
     <span id="txtPoskod_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Negeri</td>
    <td>:</td>
    <td>
   
     #if($readmode == "view" ) 
     #if($socNegeriSiasatanX=="")
     #set($NegeriSiasatan="")                             
     #else                             
     #foreach($ln in $list_negeri)      
     #if($socNegeriSiasatanX==$ln.ID_NEGERI)                                      
     #set($NegeriSiasatan="$ln.KOD_NEGERI - $ln.NAMA_NEGERI")
     #end
     #end                            
     #end      
     <input name="NegeriSiasatan" type="text" id="NegeriSiasatan" value="$NegeriSiasatan" size="50" readonly class="disabled">
     <input name="socNegeriSiasatan"  id="socNegeriSiasatan"  type="hidden" value="$socNegeriSiasatanX" >                
    #else    
    <select name="socNegeriSiasatan" class="autoselect" id="socNegeriSiasatan"  onchange="checking_validation(this,'socNegeriSiasatan_check','no','negeri siasatan','normal');getBandarSiasatan('$id_hakmilik','$id_pembatalan')" >  
    #if($socNegeriSiasatanX != "")
    
    #foreach($ln in $list_negeri) 
    #if($socNegeriSiasatanX==$ln.ID_NEGERI)
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_negeri)
    #if($socNegeriSiasatanX!=$ln.ID_NEGERI)
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
          <span id="socNegeriSiasatan_check" class="alert_msg" ></span>          </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Bandar</td>
    <td>:</td>
    <td>
    
     #if($readmode == "view" )
    
     #if($socBandarSiasatanX=="")
     #set($BandarSiasatan="")                             
     #else                             
     #foreach($lb in $list_bandar)      
     #if($socBandarSiasatanX==$lb.ID_BANDAR)                                      
     #set($BandarSiasatan="$lb.KOD_BANDAR - $lb.NAMA_BANDAR")
     #end
     #end                            
     #end      
    <input name="BandarSiasatan" type="text" id="BandarSiasatan" value="$BandarSiasatan" size="50" readonly class="disabled">
    <input name="socBandarSiasatan"  id="socBandarSiasatan"  type="hidden" value="$socBandarSiasatanX" >                     
    #else    
    <select name="socBandarSiasatan" class="autoselect" id="socBandarSiasatan"  onchange="checking_validation(this,'socBandarSiasatan_check','no','bandar siasatan','normal');" >  
    #if($socBandarSiasatanX != "")
    
    #foreach($lb in $list_bandar) 
    #if($socBandarSiasatanX==$lb.ID_BANDAR)
    <option value="$lb.ID_BANDAR">$lb.KOD_BANDAR - $lb.NAMA_BANDAR</option>                                     
    #end 
    #end 
   
    #foreach($lb in $list_bandar)
    #if($socBandarSiasatanX!=$lb.ID_BANDAR)
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
          <span id="socBandarSiasatan_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Status Siasatan</td>
    <td>:</td>
    <td>
    
    #if($readmode == "view" ) 
     #if($socStatusSiasatanX=="")
     #set($StatusSiasatanX="")                             
     #else
     #if($socStatusSiasatanX=="1")
     #set($StatusSiasatanX="SIASATAN PERMULAAN")    
     #elseif($socStatusSiasatanX=="2") 
     #set($StatusSiasatan="DITUNDA SEBELUM SIASATAN")    
     #elseif($socStatusSiasatanX=="3") 
     #set($StatusSiasatanX="DIBATALKAN")    
     #elseif($socStatusSiasatanX=="4") 
     #set($StatusSiasatanX="SIASATAN ULANGAN")    
     #elseif($socStatusSiasatanX=="5") 
     #set($StatusSiasatanX="SIASATAN SAMBUNGAN") 
     #elseif($socStatusSiasatanX=="7")                             
     #set($StatusSiasatanX="TANGGUH SIASATAN")   
     #elseif($socStatusSiasatanX=="6")                             
     #set($StatusSiasatanX="SELESAI")                
     #end 
     #end
        <input name="StatusSiasatanX" type="textX" id="StatusSiasatanX" value="$StatusSiasatanX" size="50" readonly class="disabled" />   
                  
    #else    
    <select name="socStatusSiasatanX" class="autoselect" id="socStatusSiasatanX"  onchange="checking_validation(this,'socStatusSiasatan_checkX','no','status siasatan','normal');" >  
    #if($socStatusSiasatanX != "")
     
   #if($socStatusSiasatanX == "1")
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #elseif($socStatusSiasatanX == "2")
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option>
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #elseif($socStatusSiasatanX == "3")
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option>
    <option value="5">SIASATAN SAMBUNGAN</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #elseif($socStatusSiasatanX == "4")
    <option value="4">SIASATAN ULANGAN</option>
    <option value="5">SIASATAN SAMBUNGAN</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option>     
    <option value="3">DIBATALKAN</option>
     <option value="">SILA PILIH STATUS SIASATAN</option>  
    #elseif($socStatusSiasatanX == "5")
    <option value="5">SIASATAN SAMBUNGAN</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option> 
    #elseif($socStatusSiasatanX == "7")
    <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>   
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    
    #elseif($socStatusSiasatanX == "6")
    <option value="6">SELESAI</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
    <option value="">SILA PILIH STATUS SIASATAN</option>
    #end
    #else
   
    <option value="">SILA PILIH STATUS SIASATAN</option> 
    <option value="1">SIASATAN PERMULAAN</option> 
    <option value="2">DITUNDA SEBELUM SIASATAN</option> 
    <option value="3">DIBATALKAN</option> 
    <option value="4">SIASATAN ULANGAN</option> 
    <option value="5">SIASATAN SAMBUNGAN</option>
     <option value="7">TANGGUH SIASATAN</option>
    <option value="6">SELESAI</option>       
   
    
    #end
    
    </select>  
             
    #end
    
    <span id="socStatusSiasatan_checkX" class="alert_msg" ></span>    </td>
  </tr>
   <tr>
    <td>&nbsp;</td>
    <td>Bil. Siasatan</td>
    <td>:</td>
    <td>
    
     <input name="bil_siasatan" type="text" id="bil_siasatan" size="10" maxlength="10"   value="$!bil_siasatanX" onblur="validateTarikh(this,this.value);checking_validation(this,'bil_siasatan_check','no','bilangan siasatan','normal');" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'bil_siasatan_check','no','bilangan siasatan','normal');" $readonlymode class = "$disabledmode" />   
 
       <span id="bil_siasatan_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Siasatan</td>
    <td>:</td>
    <td>
    
     <input name="txdTarikhSiasatan" type="text" id="txdTarikhSiasatan" size="10" maxlength="10"   value="$!txdTarikhSiasatanX" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhSiasatan_check','no','siasatan','tarikh2');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />   
    #if($readmode == "edit")    
      <a href="javascript:displayDatePicker('txdTarikhSiasatan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>        
      #end
       <span id="txdTarikhSiasatan_check" class="alert_msg" ></span>   </td>
  </tr>
  <tr>
    <td></td>
    <td>Masa Siasatan</td>
    <td>:</td>
    <td><input name="txtMasaSiasatan" type="text" id="txtMasaSiasatan" value="$!txtMasaSiasatanX"  size="4" maxlength="4"   $readonlymode class = "$disabledmode"  onBlur="checking_validation(this,'txtMasaSiasatan_check','no','siasatan','waktu');jeniswaktu1(this,'jeniswaktu')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtMasaSiasatan_check','no','siasatan','waktu');" /> 
                                  <label>
                                  #if($readmode=="view")
                                  #set($waktu = "")
                                  #if( $jeniswaktuX == 1)
                                  #set($waktu = "PAGI")  
                                  #elseif($jeniswaktuX == 2)
                                  #set($waktu = "TENGAHARI")                                   
                                  #elseif($jeniswaktuX == 3)
                                  #set($waktu = "PETANG")      
                                  #elseif($jeniswaktuX == 4)
                                  #set($waktu = "MALAM")                                   
                                  #else
                                  #set($waktu = "")
                                  #end             
                                  
                  <input name="waktu" type="text" id="waktu"    value="$waktu" size="4" maxlength="3" style="width:100"   readonly class = "disabled" />
                  <input name="jeniswaktu" type="hidden" id="jeniswaktu" value="$!jeniswaktuX" />
                                  #else
                                  <select name="jeniswaktu" id="jeniswaktu" style="width:100"  onFocus="jeniswaktu2('txtMasaSiasatan')" >                                  
                                  #if( $jeniswaktuX == 1)                                  
                                  <option value="1" id="op_pagi" >PAGI</option>
                                  <option value="2" id="op_tengahari">TENGAHARI</option>
                                  <option value="3" id="op_petang">PETANG</option>
                                  <option value="4" id="op_malam">MALAM</option>                                  
                                  <option value="">SILA PILIH</option>                                    
                                  #elseif($jeniswaktuX == 2)
                                  <option value="2" id="op_tengahari">TENGAHARI</option>
                                  <option value="1" id="op_pagi">PAGI</option>                                 
                                  <option value="3" id="op_petang">PETANG</option>
                                  <option value="4" id="op_malam">MALAM</option>                                  
                                  <option value="">SILA PILIH</option>
                                  #elseif($jeniswaktuX == 3)
                                  <option value="3" id="op_petang">PETANG</option>
                                  <option value="1" id="op_pagi">PAGI</option>
                                  <option value="2" id="op_tengahari">TENGAHARI</option>                                   
                                  <option value="4" id="op_malam">MALAM</option>
                                  <option value="">SILA PILIH</option>
                                  #elseif($jeniswaktuX == 4)
                                  <option value="4" id="op_malam">MALAM</option>
                                  <option value="1" id="op_pagi">PAGI</option>
                                  <option value="2" id="op_tengahari">TENGAHARI</option>
                                  <option value="3" id="op_petang">PETANG</option>  
                                  <option value="">SILA PILIH</option>
                                  #else
                                  <option value="">SILA PILIH</option>                                 
                                  <option value="1" id="op_pagi" >PAGI</option>                                 
                                  <option value="2" id="op_tengahari" >TENGAHARI</option>
                                  <option value="3"  id="op_petang" >PETANG</option>
                                  <option value="4" id="op_malam" >MALAM</option>                                    
                                  #end 
                                  </select>
                                  #end                                  </label>
                                  #if($readmode == "edit" )
                                   <span style="font-size:9px;color:blue;font-style:italic">format 12 jam (HHMM)</span>       
                                 
                                  #end   
                                  <span id="txtMasaSiasatan_check" class="alert_msg" ></span>                                  </td>
  </tr>
  <tr style="display:none">
    <td valign="top">&nbsp;</td>
    <td>Tarikh Akhir Tampal Notis Awam</td>
    <td valign="top">:</td>
    <td>    
     <input name="txtTkhAkhirNotis" type="text" id="txtTkhAkhirNotis" size="10" maxlength="10"   value="$!txtTkhAkhirNotisX" onblur="validateTarikh(this,this.value);checking_validation(this,'txtTkhAkhirNotis_check','no','akhir tampal notis awam','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />   
    #if($readmode == "edit")    
      <a href="javascript:displayDatePicker('txtTkhAkhirNotis',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> 
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>       
      #end
       <span id="txtTkhAkhirNotis_check" class="alert_msg" ></span>    </td>
  </tr>
  
  <tr >
    <td colspan="4" > 
    #if($readmode == "edit")
      <label>
      <div align="center">
        <input type="button" name="cmdKosong" id="cmdKosong " value="Kosongkan" onClick="Kosongkan()">
      </div>
      </label>
      #end</td>
    
  </tr>
    #if($readmode == "edit")
  #else
   <tr>
    <td colspan="4"></td>
  </tr>  
  #end
</table>
   <input type="hidden" name="id_siasatanX" id="id_siasatanX" value="$!id_siasatanX" />
  </fieldset>
  </td>
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
      
    </table></td>
  </tr>
  <tr style="display:none">
  <td>
  <fieldset>
  <legend>PERINTAH AWARD</legend>
  
  <table width="100%">
    
     
        
   
  
    <tr  >
                  <td >
                 
                  <table width="100%">
                    <tr class="table_header">
                      <td width="2%">Bil</td>
                      <td width="25%">Nama PB</td>
                      <td width="15%">No. PB</td>
                      
                      <td width="15%">No. Lot/PT</td>
                      <td width="18%">Bandar/Pekan/Mukim</td>
                      
                      <td width="10%">Bahagian</td>
                      <td width="15%"  align="right">Jumlah Pampasan</td>
                    </tr>
                  
              #if($senarai_pihak_penting_pampasan_perintah.size()!=0)
             #foreach($list in $senarai_pihak_penting_pampasan_perintah)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
 
                
                
                    <tr >
                      <td class="$row">$list.BIL</td>
                      <td class="$row">
                      <a href="javascript:tuntutan('$list.ID_HAKMILIKPB','$id_permohonan','$id_pembatalan')" title="Maklumat Pampasan"><font color="blue">$list.NAMA_PB</font></a>                      </td>
                      <td class="$row">$list.NO_PB</td>
                      
                      <td class="$row">$list.NO_LOT</td>
                      <td class="$row">$list.NAMA_MUKIM</td>
                      
                      <td class="$row">
                      #if($list.SYER_ATAS!="" && $list.SYER_BAWAH!="")
                      $list.SYER_ATAS / $list.SYER_BAWAH
                      #else
                      
                      #end                      </td>
                      <td class="$row" align="right">
                      <input type="hidden" name="id_award" id = "id_award" value="$list.ID_AWARD" />
                      <input type="hidden" name="id_hakmilik_pb" id = "id_hakmilik_pb" value="$list.ID_HAKMILIKPB" />
                      #if($readmode == "view")
                      #if($list.BAYAR_PAMPASAN != "")
                      RM $Util.formatDecimal($list.BAYAR_PAMPASAN)
                      #end   
                      #else
                      #set($txtpampasan_check = "txtpampasan_check"+$!list.BIL)
                       <input name="pampasan" type="text" class="$disabledmode" id="pampasan" style="width:100%" onblur="validateTarikh(this,this.value);validateModal(this,this.value,'$list.BAYAR_PAMPASAN');" onkeyup="validateTarikh(this,this.value);" value="$!list.BAYAR_PAMPASAN" size="15" $readonlymode="$readonlymode" />
  <span id="$pampasan_check"  class="alert_msg" ></span>  
                      #end
                      
                            </td>
                    </tr>
              #end
              
              #else
              <tr>
              <td colspan="8">
              Tiada Rekod              </td>
              </tr>
              #end
                  </table></td>
                </tr>
    </table>
  
  </fieldset>
  </td>
  </tr>
  <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini('$id_siasatan')">
      </label>
      <label>
      
       <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus('$id_siasatan')">
      
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan('$id_siasatan')">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal('$id_siasatan')">
      </label>
    #end  
     
       #if($id_pembatalan != "")
        <label></label>
        #if($readmode == "view")
        <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  
      </label> 
      #end   
      #end
       <label></label>
     </div>      </td>
  </tr>
</table>

   </fieldset>
    </div>
   
   
    
  </div>
</div>


  <div class="TabbedPanelsContent"></div>

</td>
</tr>
</table>
  
  <fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    
    <tr>
        <td><a href="#" class="style2" onClick="nota('$id_siasatan')"><font color="blue">Nota Siasatan Tarik Balik</font></a></td>
      </tr>  
    <tr>
        <td><a href="#" class="style2" onClick="BorangLB('$id_pembatalan')"><font color="blue">Borang LB</font></a></td>
      </tr>         
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
  
  
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:6});
var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm,textareaxx('tambahtolak','umum','');

function submitForm() 
{

if('$socStatusSiasatan' == "6" )
{
document.getElementById('perintah_keputusan').style.display="";  
}
else
{
document.getElementById('perintah_keputusan').style.display="none"; 
}






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
checking_validation_tangguh('$socStatusSiasatan');
checking_validation(document.${formName}.socPegawaiSiasatan,'socPegawaiSiasatan_check','yes','pegawai','normal');
checking_validation(document.${formName}.socStatusSiasatan,'socStatusSiasatan_check','yes','status siasatan','normal');
//checking_validation(document.${formName}.txtUlasanSiasatan,'txtUlasanSiasatan_check','yes','ulasan siasatan','normal');
check_length(document.${formName}.txtUlasanSiasatan,'4000','txtUlasanSiasatan_check','txtUlasanSiasatan_num','normal','no','ulasan siasatan');

if(document.${formName}.socStatusSiasatan.value == "7")
{
jeniswaktu2('txtMasaSiasatan');
checking_validation(document.${formName}.txtNoKes,'txtNoKes_check','no','no kes','normal');
checking_validation(document.${formName}.txtNoSiasatan,'txtNoSiasatan_check','no','no siasatan','normal');
checking_validation(document.${formName}.txtTempatSiasatan,'txtTempatSiasatan_check','no','tempat siasatan','normal');
checking_validation(document.${formName}.txtPoskod,'txtPoskod_check','no','','poskod');
checking_validation(document.${formName}.txtAlamat1,'txtAlamat1_check','no','alamat','normal');
checking_validation(document.${formName}.socNegeriSiasatan,'socNegeriSiasatan_check','no','negeri siasatan','normal');
checking_validation(document.${formName}.socBandarSiasatan,'socBandarSiasatan_check','no','bandar siasatan','normal');
checking_validation(document.${formName}.socStatusSiasatanX,'socStatusSiasatan_checkX','no','status siasatan','normal');
checking_validation(document.${formName}.txdTarikhSiasatan,'txdTarikhSiasatan_check','no','siasatan','tarikh2');
checking_validation(document.${formName}.txtMasaSiasatan,'txtMasaSiasatan_check','no','siasatan','waktu');
checking_validation(document.${formName}.bil_siasatan,'bil_siasatan_check','no','bilangan siasatan','normal');
}
}

function checking_validation(field,point,mandatory,value_field,jenis_field){	



   	var lepas_or_xlepas = 1;
	if(jenis_field == "tarikh" || jenis_field == "tarikh1" || jenis_field == "tarikh2")
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
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	      DateField.select();		
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
		      var date1 = new Date(year, month-1, day);
		 
		 if(jenis_field == "tarikh")
		 {
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
		 }
	   
	   if(jenis_field == "tarikh1")
		 {
		 
	
		  if (date < date1)
		  {			  
		  tarikh_valid = "xvalid1";			
		  }
		  else
		  {
		  tarikh_valid = "valid";	
		  }
		 
	    }
	   
	   
	   if(tarikh_valid == "valid")
	   {	/*   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
	   }
	   else if(tarikh_valid == "xvalid1")
	   {/*	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" melebihi dari tarikh hari ini");	
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
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
		
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
		
	   }
	   else
	   {
	   /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
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
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		   */
		    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan dokumen terlebih dahulu");
		
	    }
		else
		{/*
		   document.${formName}.alert_message.value  = "";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		*/
		$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
		
		
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
		
	   }
	   else if(lepas_or_xlepas == "3")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan waktu "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan waktu "+value_field+" dengan format yang betul");
		
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
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
	   
	   }
	   else if(lepas_or_xlepas == "3")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan poskod "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan poskod "+value_field+" dengan format yang betul");
	   	
	   }
	   else
	   {/*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
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
	document.${formName}.sub_command.value = "Status_Siasatan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "Agensi";	
	document.${formName}.submit();
	}
}
}


function kemaskini(id_siasatan)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Status_Siasatan";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "socPegawaiSiasatan";
	document.${formName}.submit();
	
}


function batal(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Status_Siasatan";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "socPegawaiSiasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
}


function hapus(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Status_Siasatan";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "socPegawaiSiasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
}


function validateModal(elmnt,content,content2) {
 //   alert(content)	
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != "")
	{
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	}
	else
	{
	elmnt.value ="";
	return;
	}
	
}


function tuantanah(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "TuanTanah";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function agensi(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Agensi";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function tuntutan(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}


function bantahan(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Bantahan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function nilaian(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kerosakan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function keputusan(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}


function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		window.location.hash=id;
        goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}



function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{

//alert("test");
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

function status(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Status_Siasatan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function nota(id_siasatan)
{
    var url = "../servlet/ekptg.report.ppt.NotaSiasatanPB?id_siasatan="+id_siasatan;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function BorangLB(id_pembatalan)
{
    var url = "../servlet/ekptg.report.ppt.BorangLB?id_penarikan="+id_pembatalan;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


function FCKeditor_OnComplete(editorInstance){
	if (editorInstance.Name == "txtUlasanSiasatan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtUlasanSiasatan);				
	}	
	
	}
	
function check_length_txtUlasanSiasatan(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtUlasanSiasatan');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtUlasanSiasatan').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtUlasanSiasatan';
var maxLen = 30000;
var charlimit = maxLen-1 ;
var alert_field = 'txtUlasanSiasatan_check';
var text_num = 'txtUlasanSiasatan_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'ulasan siasatan';




	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(count  == 0 && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   	   
	   if(count  == 0)
	   {
	   document.getElementById(text_num).value = maxLen;
	   } 
	   
	     
	   if(lepas_or_xlepas == "21")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
	   }
	   else
	   {	  
	   if (count >= maxLen ) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan"); 

      maxLen = 0;
   var text = editorInstance.GetHTML(true);
   editorInstance.SetHTML(text.substr(0,charlimit));
 
    
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - count;
       }		
	   }
	   
	   	   
	   }
	   
	  

$jquery("#"+text_num).html(maxLen+" <span style='color:black;' > Baki Aksara </span>");


}


function textareaxx(tambahtolak,jenis,index_tolak)
{


var temp1_length=0;

if(document.${formName}.temp_temp1 != null)
{

if(document.${formName}.temp_temp1.length>0)
{
temp1_length = document.${formName}.temp_temp1.length;
}
else
{
temp1_length = 1;
}

}


//alert("::::"+temp1_length);

var code_temp = "";
if(document.${formName}.txtUlasanTangguh!=null)
{


if(document.${formName}.txtUlasanTangguh.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanTangguh.length; i++)
 {
 code_temp += "<tr><td><input type='hidden' id='temp_temp' name='temp_temp' value= '"+document.${formName}.txtUlasanTangguh[i].value+"'></td></tr>"; 
 } 
}
else
{
code_temp += "<tr><td><input type='hidden' id='temp_temp' name='temp_temp' value= '"+document.${formName}.txtUlasanTangguh.value+"'></td></tr>";
}
}

$jquery("#textlocation_temp").html(""+code_temp); 

var codes = "";






if(jenis == "umum")
{
if(temp1_length>0)
{
tt = temp1_length;
}
else
{
tt = 1;
}
}
if(jenis == "tambah")
{
tt = tt + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
tt = tt + parseInt(tambahtolak);
}




  for (i = 0; i < tt; i++)
  {	
  if(tt==1)
  {
  
  
  var temp_value = "";
	
	
	
	
	if(i==0 && jenis == "tolak")
	{	
	//temp_value = document.${formName}.temp_temp[0].value
	 if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.temp_temp[1].value

    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.temp_temp[0].value
	
    }  
	}		
  
 
  
	codes += "<table width='100%'><tr>"+
	"<td > "+
	     " <textarea name=\"txtUlasanTangguh\" id=\"txtUlasanTangguh\" cols=\"60\"   rows=\"6\""+          
         "onBlur=\"check_length(this,'400','txtUlasanTangguh_check','txtUlasanTangguh_num','normal','no','ulasan tangguh');textareaxx1()\" "+  
         "onKeyup=\"check_length(this,400,'txtUlasanTangguh_check','txtUlasanTangguh_num','normal','no','ulasan tangguh');textareaxx1()\" "+
         "onKeydown=\"check_length(this,'400','txtUlasanTangguh_check','txtUlasanTangguh_num','normal','no','ulasan tangguh');textareaxx1()\" "+                    
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanTangguh_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<input name=\"txtUlasanTangguh_num\" id=\"txtUlasanTangguh_num\" size=\"3\" value=\"400\"  style=\"display:none\" > "+
         "#end"+
         "<span id=\"txtUlasanTangguh_check\" class=\"alert_msg\" ></span> ";
		   codes +=  "#if($readmode == 'edit' )";
		
		   
    codes += " <span >"+	
	" <input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+'  onClick=\"textareaxx(1,'tambah','')\" title='Menambah ulasan tangguh'> "+
	" </span>"; 

	     if(tt>1) {      
	codes += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onClick=\"textareaxx(-1,'tolak','')\" title='Mengurangkan ulasan tangguh' > "+
	"</span> ";
	}
	codes +="#end";	
	codes +=" </td>"+
	"</tr></table>";
	

	}
	else
	{
	
	
	
	var temp_value = "";
	
	 if(tt==2 && i==0 && jenis == "tambah")
	{	
	//alert("1");
	
	temp_value = document.${formName}.temp_temp.value
	
	
	}	
	else if(tt>2 && i!=(tt-1) && jenis == "tambah")
	{

	temp_value = document.${formName}.temp_temp[i].value
	
	
	}	
	else if(tt>1 && i!=(tt+1) && jenis == "tolak")
	{	
	//temp_value = document.${formName}.temp_temp[i].value
	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.temp_temp[parseInt(index_tolak)+1].value

   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.temp_temp[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.temp_temp[i+1].value	
   }	
	
	}
	else if(tt==1 && i==1 && jenis == "tolak")
	{	
	//temp_value = document.${formName}.temp_temp[0].value
	 if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.temp_temp[1].value
	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.temp_temp[0].value
	
   }
	}		


		
	codes += "<table width='100%'><tr>"+
	"<td >"+
	     "<textarea name=\"txtUlasanTangguh\" id=\"txtUlasanTangguh\" cols=\"60\"   rows=\"6\""+          
         "onBlur=\"check_length(this,'400','txtUlasanTangguh_check"+i+"','txtUlasanTangguh_num"+i+"','normal','no','ulasan tangguh');textareaxx1()\" "+  
         "onKeyup=\"check_length(this,400,'txtUlasanTangguh_check"+i+"','txtUlasanTangguh_num"+i+"','normal','no','ulasan tangguh');textareaxx1()\" "+
         "onKeydown=\"check_length(this,'400','txtUlasanTangguh_check"+i+"','txtUlasanTangguh_num"+i+"','normal','no','ulasan tangguh');textareaxx1()\" "+                    
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanTangguh_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<input name=\"txtUlasanTangguh_num"+i+"\" id=\"txtUlasanTangguh_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\" > "+
         "#end"+
         "<span id=\"txtUlasanTangguh_check"+i+"\" class=\"alert_msg\" ></span> ";
    codes +=  "#if($readmode == 'edit' ) ";
	if(tt>1 && tt==(i+1)) {   
    codes += " <span >"+	
	" <input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+'  onClick=\"textareaxx(1,'tambah','')\" title='Menambah ulasan tangguh'> "+
	" </span>"; 
	}
	     if(tt>1) {      
	codes += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onClick=\"textareaxx(-1,'tolak','"+i+"')\" title='Mengurangkan ulasan tangguh' > "+
	"</span> ";
	}
	codes +="#end";	  
	 codes +="</td>"+
	"</tr> </table>";	
	}	
	}
	//alert(tt);

	
		 
	$jquery("#textlocation").html(codes);
	
	
		
	
	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(temp1_length > 1 && document.${formName}.txtUlasanTangguh.length > 1 )
	{
	for (t = 0; t < temp1_length; t++)
    {	
    document.${formName}.txtUlasanTangguh[t].value = document.${formName}.temp_temp1[t].value;
    }
	}
	else if(temp1_length > 1 && document.${formName}.txtUlasanTangguh.length < 1 )
	{
	for (t = 0; t < temp1_length; t++)
    {	
    document.${formName}.txtUlasanTangguh.value = document.${formName}.temp_temp1[0].value;
    }
	}
	else if(temp1_length == 1)
	{
	document.${formName}.txtUlasanTangguh.value = document.${formName}.temp_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	

	if(temp1_length > 1)
	{
	for (t = 0; t < temp1_length; t++)
    {
	 
	if(index_tolak==t)
	{  
	document.${formName}.temp_temp1[index_tolak].value = "";	
	
	 var element = document.${formName}.temp_temp1[index_tolak];
     element.parentNode.removeChild(element);

	
	//document.${formName}.temp_temp1[index_tolak].removed = true;	
	}
    }	
	}
	else if(temp1_length == 1)
	{	
	document.${formName}.temp_temp1.value = "";		
	//document.${formName}.temp_temp1.disabled = "disabled";
	 var element = document.${formName}.temp_temp1;
     element.parentNode.removeChild(element);	
	}
	}	
		
		
	
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{
	
	for (i = 0; i < tt; i++)
    {
		
    if(tt==1)
    {	
	check_length(document.${formName}.txtUlasanTangguh,'400','txtUlasanTangguh_check','txtUlasanTangguh_num','normal','no','ulasan tangguh');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanTangguh[i],'400','txtUlasanTangguh_check'+i,'txtUlasanTangguh_num'+i,'normal','no','ulasan tangguh');	
	}
		 
	}
	
	}
	
}


function textareaxx1()
{

var temp1_length=0;

if(document.${formName}.temp_temp1 != null)
{

if(document.${formName}.temp_temp1.length>0)
{
temp1_length = document.${formName}.temp_temp1.length;
}
else
{
temp1_length = 1;
}

}




    if(temp1_length > 1 && document.${formName}.txtUlasanTangguh.length > 1 )
	{
	for (t = 0; t < temp1_length; t++)
    {	
    document.${formName}.temp_temp1[t].value = document.${formName}.txtUlasanTangguh[t].value;
    }
	}
	else if(temp1_length > 1 && document.${formName}.txtUlasanTangguh.length < 1 )
	{
	for (t = 0; t < temp1_length; t++)
    {	
    document.${formName}.temp_temp1[0].value = document.${formName}.txtUlasanTangguh.value;
    }
	}
	else if(temp1_length == 1)
	{
	document.${formName}.temp_temp1.value = document.${formName}.txtUlasanTangguh.value;
	}
}

function checking_validation_tangguh(status)
{

if(status == 7)
{

document.getElementById('id_tangguh').style.display="";
document.getElementById('id_tangguh1').style.display="";
}
else
{

document.getElementById('id_tangguh').style.display="none";
document.getElementById('id_tangguh1').style.display="none";
}


}


function getBandarSiasatan(id_hakmilik,id_pembatalan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Status_Siasatan";
	document.${formName}.subminor_command.value = "getBandar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_siasatan_ulang";
	document.${formName}.point.value = "socBandarSiasatan";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
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

function check_stat()
{
if(document.${formName}.socStatusSiasatan.value == "7")
{
/*
jeniswaktu2('txtMasaSiasatan');
checking_validation(document.${formName}.txtNoKes,'txtNoKes_check','yes','no kes','normal');
checking_validation(document.${formName}.txtNoSiasatan,'txtNoSiasatan_check','yes','no siasatan','normal');
checking_validation(document.${formName}.txtTempatSiasatan,'txtTempatSiasatan_check','yes','tempat siasatan','normal');
checking_validation(document.${formName}.txtPoskod,'txtPoskod_check','yes','','poskod');
checking_validation(document.${formName}.txtAlamat1,'txtAlamat1_check','yes','alamat','normal');
checking_validation(document.${formName}.socNegeriSiasatan,'socNegeriSiasatan_check','yes','negeri siasatan','normal');
checking_validation(document.${formName}.socBandarSiasatan,'socBandarSiasatan_check','yes','bandar siasatan','normal');
checking_validation(document.${formName}.socStatusSiasatanX,'socStatusSiasatan_checkX','no','status siasatan','normal');
checking_validation(document.${formName}.txdTarikhSiasatan,'txdTarikhSiasatan_check','yes','siasatan','tarikh2');
checking_validation(document.${formName}.txtMasaSiasatan,'txtMasaSiasatan_check','no','siasatan','waktu');
checking_validation(document.${formName}.bil_siasatan,'bil_siasatan_check','yes','bilangan siasatan','normal');*/
jeniswaktu2('txtMasaSiasatan');
checking_validation(document.${formName}.txtNoKes,'txtNoKes_check','no','no kes','normal');
checking_validation(document.${formName}.txtNoSiasatan,'txtNoSiasatan_check','no','no siasatan','normal');
checking_validation(document.${formName}.txtTempatSiasatan,'txtTempatSiasatan_check','no','tempat siasatan','normal');
checking_validation(document.${formName}.txtPoskod,'txtPoskod_check','no','','poskod');
checking_validation(document.${formName}.txtAlamat1,'txtAlamat1_check','no','alamat','normal');
checking_validation(document.${formName}.socNegeriSiasatan,'socNegeriSiasatan_check','no','negeri siasatan','normal');
checking_validation(document.${formName}.socBandarSiasatan,'socBandarSiasatan_check','no','bandar siasatan','normal');
checking_validation(document.${formName}.socStatusSiasatanX,'socStatusSiasatan_checkX','no','status siasatan','normal');
checking_validation(document.${formName}.txdTarikhSiasatan,'txdTarikhSiasatan_check','no','siasatan','tarikh2');
checking_validation(document.${formName}.txtMasaSiasatan,'txtMasaSiasatan_check','no','siasatan','waktu');
checking_validation(document.${formName}.bil_siasatan,'bil_siasatan_check','no','bilangan siasatan','normal');
}
else
{
jeniswaktu2('txtMasaSiasatan');
checking_validation(document.${formName}.txtNoKes,'txtNoKes_check','no','no kes','normal');
checking_validation(document.${formName}.txtNoSiasatan,'txtNoSiasatan_check','no','no siasatan','normal');
checking_validation(document.${formName}.txtTempatSiasatan,'txtTempatSiasatan_check','no','tempat siasatan','normal');
checking_validation(document.${formName}.txtPoskod,'txtPoskod_check','no','','poskod');
checking_validation(document.${formName}.txtAlamat1,'txtAlamat1_check','no','alamat','normal');
checking_validation(document.${formName}.socNegeriSiasatan,'socNegeriSiasatan_check','no','negeri siasatan','normal');
checking_validation(document.${formName}.socBandarSiasatan,'socBandarSiasatan_check','no','bandar siasatan','normal');
checking_validation(document.${formName}.socStatusSiasatanX,'socStatusSiasatan_checkX','no','status siasatan','normal');
checking_validation(document.${formName}.txdTarikhSiasatan,'txdTarikhSiasatan_check','no','siasatan','tarikh2');
checking_validation(document.${formName}.txtMasaSiasatan,'txtMasaSiasatan_check','no','siasatan','waktu');
checking_validation(document.${formName}.bil_siasatan,'bil_siasatan_check','no','bilangan siasatan','normal');
}
}

function Kosongkan()
{
document.${formName}.txtNoKes.value = "";
document.${formName}.txtNoSiasatan.value = "";
document.${formName}.txtTempatSiasatan.value = "";
document.${formName}.txtPoskod.value = "";
document.${formName}.txtAlamat1.value = "";
document.${formName}.socNegeriSiasatan.value = "";
document.${formName}.socBandarSiasatan.value = "";
document.${formName}.socStatusSiasatanX.value = "";
document.${formName}.txdTarikhSiasatan.value = "";
document.${formName}.txtMasaSiasatan.value = "";
document.${formName}.bil_siasatan.value = "";

}

</script>
  
