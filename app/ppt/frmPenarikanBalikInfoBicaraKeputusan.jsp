<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>





#if($flag_open_award != "yes")



#set($txtUlasanPerintah = "")
#set($socAwardKepada = "")
#set($nilai_seunit = "")
#set($socUnit = "")

#end
#set($socPegawaiSiasatan = "")
#set($socStatusSiasatan = "")

#set($txtUlasanTidakHadir = "")
#set($txdTarikhPerintah = "")
#set($txtUlasanCatatanPerintah = "")

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

#foreach($list in $senarai_perintah_award)  
#set($socAwardKepada = $list.STATUS_PENERIMA)
#set($txtUlasanTidakHadir = $list.ULASAN_TIDAK_HADIR)
#set($txtUlasanCatatanPerintah = $list.PERINTAH)
#end


  </tr>
<tr>
<td><div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0"  onClick="screen5('$id_permohonan','$id_pembatalan')">Kembali</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuantanah('$id_siasatan')" id="tuan_tanah"  style="display:none">Keterangan Tuan Tanah</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="agensi('$id_siasatan')"  id="Agensi">Agensi / Jurunilai</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuntutan('$id_siasatan')">Tuntutan</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="bantahan('$id_siasatan')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="nilaian('$id_siasatan')" >Kerosakan</li>
      <li class="TabbedPanelsTab" tabindex="0" onClick="status('$id_siasatan')" >Status Siasatan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="keputusan('$id_siasatan')" id="perintah_keputusan" >Perintah</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
   <div class="TabbedPanelsContent"></div>
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
        <td width="28%" valign="top">Pegawai Siasatan </td>
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
     $!PegawaiSiasatan  
     <input name="PegawaiSiasatan" type="hidden" id="PegawaiSiasatan" value="$PegawaiSiasatan" size="50" readonly class="disabled"> 
     <input name="socPegawaiSiasatan" type="hidden" id="socPegawaiSiasatan" value="$socPegawaiSiasatan" size="50" >              
    #else  
      
    <select name="socPegawaiSiasatan" class="autoselect" id="socPegawaiSiasatan"  onchange="checking_validation(this,'socPegawaiSiasatan_check','yes','pegawai','normal');"  style="display:none">  
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
    
     #if($socPegawaiSiasatan=="")
     #set($PegawaiSiasatan="")                             
     #else                             
     #foreach($ln in $list_pegawai)      
     #if($socPegawaiSiasatan==$ln.ID_PEGAWAI)                                      
     #set($PegawaiSiasatan=$ln.NAMA_PEGAWAI)
     #end
     #end                            
     #end    
     $!PegawaiSiasatan  
    
          <input name="PegawaiSiasatan" type="hidden" id="PegawaiSiasatan" value="$PegawaiSiasatan" size="50" readonly class="disabled">
                    
    #end
          <span id="socPegawaiSiasatan_check" class="alert_msg" ></span>              </td>
      </tr>
      <tr style="display:none">
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
    <select name="socStatusSiasatan" class="autoselect" id="socStatusSiasatan"  onchange="checking_validation(this,'socStatusSiasatan_check','yes','status siasatan','normal');" >  
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
    
    
     <span id="socStatusSiasatan_check" class="alert_msg" ></span>              </td>
      </tr>
      
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Tarikh Perintah</td>
        <td>:</td>
        <td>
        <input name="txdTarikhPerintah" type="text" id="txdTarikhPerintah" size="10" maxlength="10" value="$txdTarikhPerintah" onBlur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhPerintah_check','yes','perintah','tarikh')" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />
      #if($readmode == "edit")
      <a href="javascript:displayDatePicker('txdTarikhPerintah',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
        <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
      #end
      <span id="txdTarikhPerintah_check" class="alert_msg" ></span>      </td>
      </tr>
      
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Ulasan Keseluruhan Perintah Siasatan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtUlasanSiasatan" id="txtUlasanSiasatan" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtUlasanSiasatan_check','yes','ulasan siasatan','normal')"  onKeyUp="checking_validation(this,'txtUlasanSiasatan_check','yes','ulasan siasatan','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtUlasanSiasatan</textarea>     
         <div id="txtUlasanSiasatan_check" style="color:red" > </div> 
         -->
            <textarea name="txtUlasanPerintah" id="txtUlasanPerintah" cols="70"   rows="10"          
         onBlur="check_length(this,'4000','txtUlasanPerintah_check','txtUlasanPerintah_num','normal','no','ulasan perintah');"  
         onKeyup="check_length(this,'4000','txtUlasanPerintah_check','txtUlasanPerintah_num','normal','no','ulasan perintah');" 
         onKeydown="check_length(this,'4000','txtUlasanPerintah_check','txtUlasanPerintah_num','normal','no','ulasan perintah');"                    
          $readonlymode class = "$disabledmode" 
        >$txtUlasanPerintah</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtUlasanPerintah_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtUlasanPerintah_num" id="txtUlasanSiasatan_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtUlasanPerintah_check" class="alert_msg" ></div>               </td>
      </tr>
      
      <tr style="display:none">
    <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td  width="28%">Nilai Seunit (RM)</td>
    <td width="1%"><div align="center">:</div></td>
    <td  width="70%">
     #if($readmode == "view" ) 
     #if($nilai_seunit != "")
     #set($nilai_seunit = $Util.formatDecimal($nilai_seunit))
                      #end   
                     
                      #end
    
    
    <input name="nilai_seunit" type="text" class="$disabledmode" id="nilai_seunit"  style="text-align:right" onBlur="validateTarikh(this,this.value);validateModal(this,this.value,'$nilai_seunit');checking_validation(this,'nilai_seunit_check','no','nilai seunit','normal');" onKeyUp="validateTarikh(this,this.value);" value="$!nilai_seunit" size="20" maxlength="25" $readonlymode="$readonlymode" />
    <span id="nilai_seunit_check" class="alert_msg" ></span>
  
    
     #if($readmode == "view" ) 
     #if($socUnit=="")
     #set($Unit="")                             
     #else
     
     
     #if($socUnit=="2")
     #set($Unit="METER PERSEGI")    
     #elseif($socUnit=="1") 
     #set($Unit="HEKTAR")    
     #elseif($socUnit=="3") 
     #set($Unit="EKAR")    
     #elseif($socUnit=="4") 
     #set($Unit="KAKI PERSEGI")                  
     #end 
     
     
     #end         
     <input name="Unit" type="text" class="disabled" id="Unit" value="$Unit" size="20" maxlength="25" readonly >              
    #else    
    <select name="socUnit" class="autoselect" id="socUnit"  onchange="checking_validation(this,'socUnit_check','no','unit','normal');" >  
                          
     #if($socUnit=="2")
     <option value="2">METER PERSEGI</option>		
      					<option value="1">HEKTAR</option>	
      					<option value="3">EKAR</option>
      					<option value="4">KAKI PERSEGI</option>  
                         <option value="">SILA PILIH</option>             
     
     #elseif($socUnit=="1") 
     <option value="1">HEKTAR</option>	
      					<option value="3">EKAR</option>
      					<option value="4">KAKI PERSEGI</option>
                        <option value="2">METER PERSEGI</option>
                         <option value="">SILA PILIH</option> 
     #elseif($socUnit=="3") 
     <option value="3">EKAR</option>
      					<option value="4">KAKI PERSEGI</option> 
                        <option value="2">METER PERSEGI</option>		
      					<option value="1">HEKTAR</option>
                        <option value="">SILA PILIH</option>	   
     #elseif($socUnit=="4") 
      
      					<option value="4">KAKI PERSEGI</option> 
                        <option value="3">EKAR</option>
                        <option value="2">METER PERSEGI</option>		
      					<option value="1">HEKTAR</option>
                        <option value="">SILA PILIH</option>	   
     #else
       <option value="">SILA PILIH</option>    			
      					<option value="2">METER PERSEGI</option>		
      					<option value="1">HEKTAR</option>	
      					<option value="3">EKAR</option>
      					<option value="4">KAKI PERSEGI</option>             
     #end 
                        
                        
    </select>              
    #end
    
    
     <span id="socUnit_check" class="alert_msg" ></span>    </td>
    </tr>
      
      <tr id="id_open_award">
    <td colspan="4">
    
    
    <fieldset id="maklumat_award"><legend>MAKLUMAT AWARD</legend>
    
   
    
    <table width="100%"  cellspacing = "0">
      <tr>
    <td width="1%">&nbsp;</td>
    <td  width="28%">Nama PB</td>
    <td width="1%"><div align="center">:</div></td>
    <td  width="70%">$!nama_pb    </td>
    </tr>
    <tr>
    <td width="1%">&nbsp;</td>
    <td  width="28%">Jenis No PB</td>
    <td width="1%"><div align="center">:</div></td>
    <td  width="70%">$!jenis_nopb    </td>
    </tr>
    <tr>
    <td width="1%">&nbsp;</td>
    <td  width="28%">No PB</td>
    <td width="1%"><div align="center">:</div></td>
    <td  width="70%">$!nopb    </td>
    </tr>
     <tr>
    <td width="1%">&nbsp;</td>
    <td  width="28%">Bahagian / Syer</td>
    <td width="1%"><div align="center">:</div></td>
    <td  width="70%">
    
    #if($syer_atas !="" && $syer_bawah !="")
    
    $syer_atas / $syer_bawah
    
    #end
    
        </td>
    </tr>
     <tr>
    <td width="1%">&nbsp;</td>
    <td  width="28%">No Lot / No PT</td>
    <td width="1%"><div align="center">:</div></td>
    <td  width="70%">$!no_lot    </td>
    </tr>
    <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Catatan Perintah PB</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
       
<textarea name="txtUlasanCatatanPerintah" id="txtUlasanCatatanPerintah" cols="70"   rows="5"          
         onblur="check_length(this,'4000','txtUlasanCatatanPerintah_check','txtUlasanCatatanPerintah_num','normal','no','ulasan catatan perintah');status_penerima()"  
         onkeyup="check_length(this,'4000','txtUlasanCatatanPerintah_check','txtUlasanCatatanPerintah_num','normal','no','ulasan catatan perintah');status_penerima()" 
         onkeydown="check_length(this,'4000','txtUlasanCatatanPerintah_check','txtUlasanCatatanPerintah_num','normal','no','ulasan catatan perintah');status_penerima()"                    
          $readonlymode class = "$disabledmode" 
        >$!txtUlasanCatatanPerintah</textarea>       
#if($readmode == "edit")           
        <div><span id="txtUlasanCatatanPerintah_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtUlasanCatatanPerintah_num" id="txtUlasanCatatanPerintah_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtUlasanCatatanPerintah_check" class="alert_msg" ></div>               </td>
      </tr>
    
    <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Ulasan (Jika PB tidak hadir)</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
       
<textarea name="txtUlasanTidakHadir" id="txtUlasanTidakHadir" cols="70"   rows="5"          
         onblur="check_length(this,'4000','txtUlasanTidakHadir_check','txtUlasanTidakHadir_num','normal','no','ulasan tidak hadir');status_penerima()"  
         onkeyup="check_length(this,'4000','txtUlasanTidakHadir_check','txtUlasanTidakHadir_num','normal','no','ulasan tidak hadir');status_penerima()" 
         onkeydown="check_length(this,'4000','txtUlasanTidakHadir_check','txtUlasanTidakHadir_num','normal','no','ulasan tidak hadir');status_penerima()"                    
          $readonlymode class = "$disabledmode" 
        >$!txtUlasanTidakHadir</textarea>       
#if($readmode == "edit")           
        <div><span id="txtUlasanTidakHadir_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtUlasanTidakHadir_num" id="txtUlasanTidakHadir_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtUlasanTidakHadir_check" class="alert_msg" ></div>               </td>
      </tr>
    
    
    
    <tr>
        <td width="1%" valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%" valign="top">Pampasan Kepada</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
     #if($readmode == "view" ) 
     #if($socAwardKepada=="")
                               
     #else 
     
     #if($socAwardKepada == "1") 
     PIHAK BERKEPENTINGAN YANG BERKENAAN
     #elseif($socAwardKepada == "2") 
     MAHKAMAH
     #elseif($socAwardKepada == "3") 
     AMANAH RAYA
     #else
     
     #end
         
                                 
     #end    
      
     <input name="socAwardKepada" type="hidden" id="socAwardKepada" value="$socAwardKepada" size="50" readonly class="disabled">              
    #else  
      
    <select name="socAwardKepada" class="autoselect" id="socAwardKepada"  onchange="checking_validation(this,'socAwardKepada_check','yes','pampasan kepada','normal');status_penerima()"  >  
    #if($socAwardKepada != "")    
    #if($socAwardKepada == "1")
    <option value="1">PIHAK BERKEPENTINGAN YANG BERKENAAN</option>
    <option value="2">MAHKAMAH</option>
    <option value="3">AMANAH RAYA</option>
    #elseif($socAwardKepada == "2")
    <option value="2">MAHKAMAH</option>
    <option value="1">PIHAK BERKEPENTINGAN YANG BERKENAAN</option>
    <option value="3">AMANAH RAYA</option>
    #elseif($socAwardKepada == "3")
    <option value="3">AMANAH RAYA</option>
    <option value="1">PIHAK BERKEPENTINGAN YANG BERKENAAN</option>
    <option value="2">MAHKAMAH</option>  
    #end    
    <option value="">SILA PILIH PAMPASAN KEPADA</option>  
    #else   
    <option value="">SILA PILIH PAMPASAN KEPADA</option>  
    <option value="1">PIHAK BERKEPENTINGAN YANG BERKENAAN</option>
    <option value="2">MAHKAMAH</option>
    <option value="3">AMANAH RAYA</option>
    #end 
       
  
    
    </select>  
            
    #end
          <span id="socAwardKepada_check" class="alert_msg" ></span>              </td>
      </tr>
    <tr>
    <td colspan="4">&nbsp;</td>
    </tr>
    
   <tr class="row2">
    <td width="1%">&nbsp;</td>
    <td  width="28%" valign="top">Kerosakan Ke Atas Tanah</td>
    <td width="1%" valign="top"><div align="center">:</div></td>
    <td  width="70%">
   
    #if($senarai_perintah_award.size() > 0)
    #foreach($list in $senarai_perintah_award)   
    #if($list.FLAG_JENIS_AWARD == "BAYAR_KEROSAKAN")         
    <input type="hidden" name="kerosakan_temp1"  id="kerosakan_temp1" value="$list.KETERANGAN_SUBAWARD" >         
    #if($readmode == "view")
    #if($list.JUMLAH_SUBAWARD != "")
    #set($txtAmaunCek = $Util.formatDecimal($list.JUMLAH_SUBAWARD))
    #else
    #set($txtAmaunCek = "")
    #end
    #else
    #set($txtAmaunCek = $list.JUMLAH_SUBAWARD) 
    #end
    
     
    <input type="hidden" name="kerosakan_temp2"  id="kerosakan_temp2" value="$txtAmaunCek" >
    #end
    #end
    #end
            
   <span id="kerosakan"></span>           
   <div id="kerosakan_temp"></div>    </td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top"> 	
Kos- Kos Yang Ditanggung Akibat Prosiding</td>
    <td valign="top"><div align="center">:</div></td>
    <td valign="top">
    
     #if($senarai_perintah_award.size() > 0)
    #foreach($list in $senarai_perintah_award)   
    #if($list.FLAG_JENIS_AWARD == "BAYAR_PROSIDING")         
    <input type="hidden" name="prosiding_temp1"  id="prosiding_temp1" value="$list.KETERANGAN_SUBAWARD" >         
    #if($readmode == "view")
    #if($list.JUMLAH_SUBAWARD != "")
    #set($txtprosidingCek = $Util.formatDecimal($list.JUMLAH_SUBAWARD))
    #else
    #set($txtprosidingCek = "")
    #end
    #else
    #set($txtprosidingCek = $list.JUMLAH_SUBAWARD) 
    #end
    
     
    <input type="hidden" name="prosiding_temp2"  id="prosiding_temp2" value="$txtprosidingCek" >
    #end
    #end
    #end
            
   <span id="prosiding"></span>           
   <div id="prosiding_temp"></div>    </td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
    </tr>
  <tr  class="row2">
    <td>&nbsp;</td>
    <td valign="top"> 	
Lain - Lain Pampasan</td>
    <td valign="top"><div align="center">:</div></td>
    <td valign="top">
    
     #if($senarai_perintah_award.size() > 0)
    #foreach($list in $senarai_perintah_award)   
    #if($list.FLAG_JENIS_AWARD == "BAYAR_LAINPAMPASAN")         
    <input type="hidden" name="lainpampasan_temp1"  id="lainpampasan_temp1" value="$list.KETERANGAN_SUBAWARD" >         
    #if($readmode == "view")
    #if($list.JUMLAH_SUBAWARD != "")
    #set($txtlainpampasanCek = $Util.formatDecimal($list.JUMLAH_SUBAWARD))
    #else
    #set($txtlainpampasanCek = "")
    #end
    #else
    #set($txtlainpampasanCek = $list.JUMLAH_SUBAWARD) 
    #end
    
     
    <input type="hidden" name="lainpampasan_temp2"  id="lainpampasan_temp2" value="$txtlainpampasanCek" >
    #end
    #end
    #end
            
   <span id="lainpampasan"></span>           
   <div id="lainpampasan_temp"></div>    </td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
    </tr>
</table>

    
     <input type="hidden" name="id_siasatan_award" id="id_siasatan_award" value="$!id_siasatan_award" />
     <input type="hidden" name="id_siasatan_hakmilikpb" id="id_siasatan_hakmilikpb" value="$!id_siasatan_hakmilikpb" />
    </fieldset>
    </br>
    <fieldset>
    <legend>LAIN-LAIN KOS</legend>
    <table width="100%"  cellspacing = "0">
    
    <tr >
    <td width="1%">&nbsp;</td>
    <td  width="28%%" valign="top"> 	
Lain - Lain Kos</td>
    <td valign="top" width="1%" ><div align="center">:</div></td>
    <td valign="top" width="70%">
    
     #if($senarai_perintah_award.size() > 0)
    #foreach($list in $senarai_perintah_award)   
    #if($list.FLAG_JENIS_AWARD == "BAYAR_LAIN")         
    <input type="hidden" name="Lain_temp1"  id="Lain_temp1" value="$list.KETERANGAN_SUBAWARD" >         
    #if($readmode == "view")
    #if($list.JUMLAH_SUBAWARD != "")
    #set($txtLainCek = $Util.formatDecimal($list.JUMLAH_SUBAWARD))
    #else
    #set($txtLainCek = "")
    #end
    #else
    #set($txtLainCek = $list.JUMLAH_SUBAWARD) 
    #end
    
     
    <input type="hidden" name="Lain_temp2"  id="Lain_temp2" value="$txtLainCek" >
    #end
    #end
    #end
            
   <span id="Lain"></span>           
   <div id="Lain_temp"></div>    </td>
  </tr>
    </table>
    </fieldset>    </td>
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
  
  <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini('$id_siasatan')">
      </label>
      <label>
      #if($!flag_open_award == "yes" && $!id_siasatan_award!="")
       <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Hapus Maklumat Award" onClick="hapus_award('$!id_siasatan_award','$id_siasatan','$!id_siasatan_hakmilikpb')">
      </label>      
      #end
      <!--
       <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus('$id_siasatan')">
       -->
      
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
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport1')" />  
      </label> 
      #end   
      #end
       <label></label>
     </div>      </td>
  </tr>
  
  <tr>
  <td>
  <fieldset><legend>Perintah Award</legend>
 
   #if($senarai_pihak_penting_pampasan_perintah.size() > 10)
        <div style="width:100%;height:100;overflow:auto"> #end
  
  <table width="100%">
    
 
     
     <tr>
                <td colspan="8"><table width="100%" >
                    <tr>
                      <td align="left" width="50%" valign="top">&nbsp;</td>
                      <td width="50%" align="right" valign="top"> #if($list_kehadiran.size()!=0)Nama /
                        No. KP PB :
                        <label>
                          <input name="CariPB" type="text" id="CariPB"  value="$!CariPB" size="20" maxlength="150" />
                          </label>
                          <label>
                          <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Cari" onClick="PB('$id_siasatan')" />
                          </label>
                          <label>
                          <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Kosongkan" onClick="PB_Kosong('$id_siasatan')"  />
                          </label>
                          <div style="display:none"> Kehadiran PB : <span id="jumlahlot"  ></span> </div>
                        #end </td>
                    </tr>
                </table></td>
              </tr>
        
 
  
    <tr  >
                  <td >
                 
                  <table width="100%">
                    <tr class="table_header">
                      <td width="2%">Bil</td>
                      <td width="25%">Nama PB</td>
                      <td width="15%">No. PB</td>
                      
                      
                      <td width="18%">Jenis Pihak Berkepentingan</td>
                      
                      <td width="25%">
                      
                      <div>Bahagian / Syer </div>
                      
                        <div align="left"> & </div>
    <div align="left">Berkongsi Bahagian (Jika Ada)</div></td>
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
                
                
                
                  #set($listID_HAKMILIKPB = "")
                       #set($listBAYAR_PAMPASAN = "")
                       #set($listID_AWARD = "")                       
                        #set($listPERINTAH = "")
                       #set($listULASAN_TIDAK_HADIR = "")
                       #set($listSTATUS_PENERIMA = "")
                       
                       
                     
                      
                         #foreach($lt in $senarai_pihak_penting_pampasan_perintah_award)        
                         #if($lt.ID_HAKMILIKPB == $!list.ID_HAKMILIKPB)
                         #set($listID_AWARD = $!lt.ID_AWARD)
                         #set($id_siasatan_award = $!lt.ID_AWARD)
                         
                         
                         
                         #set($listID_HAKMILIKPB = $!list.ID_HAKMILIKPB)
                         #set($listBAYAR_PAMPASAN = $!lt.BAYAR_PAMPASAN) 
                         #set($listPERINTAH = $list.PERINTAH)
                         #set($listULASAN_TIDAK_HADIR = $list.ULASAN_TIDAK_HADIR)
                         #set($listSTATUS_PENERIMA = $list.STATUS_PENERIMA)                        
                         #end
                         #end
 
    
                    <tr >
                      <td class="$row">$list.BIL</td>
                      <td class="$row">
                      <a href="javascript:open_award('$list.ID_HAKMILIKPB','$!id_siasatan','$listID_AWARD')" title="Maklumat Pampasan"><font color="blue">$list.NAMA_PB</font></a>  
                      
                      <div>
                     $list.KETERANGAN_JENIS_PB
                      </div>
                      
                              </td>
                      <td class="$row">$list.NO_PB</td>
                      
                     
                      <td class="$row">$list.JENIS_PB</td>
                      
                      <td class="$row">
                 <div>     #if($list.SYER_ATAS!="" && $list.SYER_BAWAH!="")
                      $list.SYER_ATAS / $list.SYER_BAWAH
                      #else
                      
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
                    
                      <td class="$row" align="right">
                    
                    
                    
                    
                      <input type="hidden" name="temp_perintah" id = "temp_perintah" value="$listPERINTAH" />
                      <input type="hidden" name="temp_xhadir" id = "temp_xhadir" value="$listULASAN_TIDAK_HADIR" />
                      <input type="hidden" name="id_status_penerima" id = "id_status_penerima" value="$listSTATUS_PENERIMA" />                       
                      <input type="hidden" name="id_award" id = "id_award" value="$listID_AWARD" />                     
                      <input type="hidden" name="id_hakmilik_pb" id = "id_hakmilik_pb" value="$list.ID_HAKMILIKPB" />
                      
                      #if($readmode == "view")
                      #if($listBAYAR_PAMPASAN != "")
                      RM $Util.formatDecimal($listBAYAR_PAMPASAN)
                      #end   
                      #else
                      #set($txtpampasan_check = "txtpampasan_check"+$!list.BIL)
                       <input name="pampasan" type="text" class="$disabledmode" id="pampasan" style="width:100%" onblur="validateTarikh(this,this.value);validateModal(this,this.value,'$listBAYAR_PAMPASAN');" onkeyup="validateTarikh(this,this.value);" value="$!listBAYAR_PAMPASAN" size="15" $readonlymode="$readonlymode" />
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
  
   #if($senarai_pihak_penting_pampasan_perintah.size()  > 10) </div>
        #end
  </fieldset>
  </td>
  </tr>
  
  
  
</table>

   </fieldset>
    </div>
   
   
    
  </div>
</div></td>
</tr>
</table>
  
  <fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    
    <tr>
        <td><a href="#" class="style2" onClick="nota('$id_siasatan')"><font color="blue">Nota Siasatan Tarik Balik</font></a></td>
      </tr>  
       
      <tr>
        <td><a href="#" class="style2" onClick="SuratSuruhAPBayar('$id_fail','$id_siasatan')" >
        <font color="blue">Surat Mohon Agensi Pemohon Bayar Pampasan</font></a></td>
      </tr>    
       
       <!-- Tambah Borang LC -->
      <tr>
      	<td><a href="#" class="style2" onClick="CetakBorangLC('$id_hakmilik')"><font color="blue">Borang LC</font></a></td>
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
  
  <input type="hidden" name="flag_open_award" id="flag_open_award" value="$!flag_open_award" />
  
  
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:7});
var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm,textarea_kerosakan('tambahtolak','umum',''),textarea_prosiding('tambahtolak','umum',''),textarea_Lain('tambahtolak','umum',''),textarea_lainpampasan('tambahtolak','umum','');
//textarea_kerosakan('tambahtolak','umum',''),textarea_prosiding('tambahtolak','umum',''),textarea_Lain('tambahtolak','umum','')

function submitForm() 
{
flag_open_award('$flag_open_award');


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

if('$point' == "socPegawaiSiasatan" )
{
goTo('$location');
}
else
{
goTo('$point');
}

}
else
{
window.location.hash='paging';
goTo('paging');
}

if('$flag_open_award' == "yes")
{
checking_validation(document.${formName}.socAwardKepada,'socAwardKepada_check','yes','pampasan kepada','normal');
}


checking_validation(document.${formName}.socPegawaiSiasatan,'socPegawaiSiasatan_check','no','pegawai','normal');
checking_validation(document.${formName}.socStatusSiasatan,'socStatusSiasatan_check','no','status siasatan','normal');
//checking_validation(document.${formName}.txtUlasanSiasatan,'txtUlasanSiasatan_check','yes','ulasan siasatan','normal');
check_length(document.${formName}.txtUlasanPerintah,'4000','txtUlasanPerintah_check','txtUlasanPerintah_num','normal','no','ulasan perintah');
checking_validation(document.${formName}.socUnit,'socUnit_check','no','unit tanah','normal');
checking_validation(document.${formName}.nilai_seunit,'nilai_seunit_check','no','nilai seunit','normal');
check_length(document.${formName}.txtUlasanTidakHadir,'4000','txtUlasanTidakHadir_check','txtUlasanTidakHadir_num','normal','no','ulasan tidak hadir');
check_length(document.${formName}.txtUlasanCatatanPerintah,'4000','txtUlasanCatatanPerintah_check','txtUlasanCatatanPerintah_num','normal','no','catatan perintah');
checking_validation(document.${formName}.txdTarikhPerintah,'txdTarikhPerintah_check','yes','perintah','tarikh');

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
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   /*	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
	   }
	   else
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini");
	   /*
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   */

	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	 $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan tarikh "+value_field+" dengan betul");
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	//   DateField.focus();
	   
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
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
	/*
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/	
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
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
		/*
	   document.${formName}.alert_message.value  = "Sila pilih "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
	   }
	   else
	   {
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
/*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
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
	document.${formName}.sub_command.value = "Keputusan";
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
	document.${formName}.sub_command.value = "Keputusan";
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
	document.${formName}.sub_command.value = "Keputusan";
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
	document.${formName}.sub_command.value = "Keputusan";
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



function flag_open_award(flag_open_award)
{

if(flag_open_award == "yes")
{
document.getElementById('id_open_award').style.display="";
}
else
{
document.getElementById('id_open_award').style.display="none";
}
}

function open_award(id_hakmilikpb,id_siasatan,id_award)
{

    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "Open_Award";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.id_siasatan_hakmilikpb.value = id_hakmilikpb;	
	document.${formName}.id_siasatan_award.value = id_award;	
	document.${formName}.location.value = "maklumat_award";
	document.${formName}.point.value = "maklumat_award";
	document.${formName}.submit();


}


function textarea_prosiding(tambahtolak,jenis,index_tolak)
{

var prosiding_temp1_length=0;

if(document.${formName}.prosiding_temp1 != null)
{

if(document.${formName}.prosiding_temp1.length>0)
{
prosiding_temp1_length = document.${formName}.prosiding_temp1.length;
}
else
{
prosiding_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanprosiding!=null)
{

if(document.${formName}.txtUlasanprosiding.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanprosiding.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='prosiding_tempX1' name='prosiding_tempX1' value= '"+document.${formName}.txtUlasanprosiding[i].value+"'></td></tr>";
 code_temp1 += "<tr><td><input type='hidden' id='prosiding_tempX2' name='prosiding_tempX2' value= '"+document.${formName}.txtUlasanprosidingAward[i].value+"'></td></tr>"; 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='prosiding_tempX1' name='prosiding_tempX1' value= '"+document.${formName}.txtUlasanprosiding.value+"'></td></tr>";
code_temp1 += "<tr><td><input type='hidden' id='prosiding_tempX2' name='prosiding_tempX2' value= '"+document.${formName}.txtUlasanprosidingAward.value+"'></td></tr>"; 
}
}

$jquery("#prosiding_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(prosiding_temp1_length>0)
{
ttprosiding = prosiding_temp1_length;
}
else
{
ttprosiding = 1;
}
}
if(jenis == "tambah")
{
ttprosiding = ttprosiding + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttprosiding = ttprosiding + parseInt(tambahtolak);
}




  for (i = 0; i < ttprosiding; i++)
  {	
  if(ttprosiding==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.prosiding_tempX1[1].value
	temp_amaunt = document.${formName}.prosiding_tempX2[1].value
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.prosiding_tempX1[0].value
	temp_amaunt = document.${formName}.prosiding_tempX2[0].value	
    } 	
	}		
   
	codes1 += "<table width='100%'><tr>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanprosiding\" id=\"txtUlasanprosiding\" cols=\"40\"   rows=\"4\""+          
         "onBlur=\"check_length(this,'400','txtUlasanprosiding_check','txtUlasanprosiding_num','normal','no','ulasan kos-kos akibat prosiding');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,400,'txtUlasanprosiding_check','txtUlasanprosiding_num','normal','no','ulasan kos-kos akibat prosiding');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'400','txtUlasanprosiding_check','txtUlasanprosiding_num','normal','no','ulasan kos-kos akibat prosiding');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanprosiding_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanprosiding_num\" id=\"txtUlasanprosiding_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanprosiding_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr>"+	
	"<td align='left' valign='top' > "+
	     "JUMLAH RM :  <input name=\"txtUlasanprosidingAward\" id=\"txtUlasanprosidingAward\" style=\"text-align:right\"  size=\"15\" maxlength=\"15\" "+          
    "onBlur=\"validateTarikh(this,this.value);validateModal_X(this,this.value);"+
	"checking_validation(this,'txtUlasanprosidingAward_check','no','amaun pampasan kos-kos akibat prosiding','normal');textarea_kerosakan1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);"+
	"checking_validation(this,'txtUlasanprosidingAward_check','no','amaun pampasan kos-kos akibat prosiding','normal');textarea_kerosakan1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);"+
		 "checking_validation(this,'txtUlasanprosidingAward_check','no','amaun pampasan kos-kos akibat prosiding','normal');textarea_kerosakan1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"txtUlasanprosidingAward_check\" class=\"alert_msg\" ></span> ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_prosiding(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_prosiding(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat prosiding'> "+
	      " </span>"; 	
	     if(ttprosiding>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_prosiding(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_prosiding(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat prosiding' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1+=" </td>"+	
	"</tr></table>";
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttprosiding==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.prosiding_tempX1.value
	temp_amaunt = document.${formName}.prosiding_tempX2.value
	}	
	else if(ttprosiding>2 && i!=(ttprosiding-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.prosiding_tempX1[i].value
	temp_amaunt = document.${formName}.prosiding_tempX2[i].value
	}	
	else if(ttprosiding>1 && i!=(ttprosiding+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.prosiding_tempX1[parseInt(index_tolak)+1].value
	temp_amaunt = document.${formName}.prosiding_tempX2[parseInt(index_tolak)+1].value
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.prosiding_tempX1[i].value
	temp_amaunt = document.${formName}.prosiding_tempX2[i].value	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.prosiding_tempX1[i+1].value	
	temp_amaunt = document.${formName}.prosiding_tempX2[i+1].value
   }	

	}
	else if(ttprosiding==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.prosiding_tempX1[1].value;
	temp_amaunt = document.${formName}.prosiding_tempX2[1].value;
	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.prosiding_tempX1[0].value;
	temp_amaunt = document.${formName}.prosiding_tempX2[0].value;
	
   }
   }		
	
	codes1 += "<table width='100%'><tr>"+
	"<td >"+
	     "<textarea name=\"txtUlasanprosiding\" id=\"txtUlasanprosiding\" cols=\"40\"   rows=\"4\""+          
         "onBlur=\"check_length(this,'400','txtUlasanprosiding_check"+i+"','txtUlasanprosiding_num"+i+"','normal','no','ulasan kos-kos akibat prosiding');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,400,'txtUlasanprosiding_check"+i+"','txtUlasanprosiding_num"+i+"','normal','no','ulasan kos-kos akibat prosiding');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'400','txtUlasanprosiding_check"+i+"','txtUlasanprosiding_num"+i+"','normal','no','ulasan kos-kos akibat prosiding');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanprosiding_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanprosiding_num"+i+"\" id=\"txtUlasanprosiding_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanprosiding_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td align='left' valign='top' > "+
	     "JUMLAH RM : <input name=\"txtUlasanprosidingAward\" id=\"txtUlasanprosidingAward\" size=\"15\" style=\"text-align:right\"  maxlength=\"15\" "+          
         "onBlur=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanprosidingAward_check"+i+"','no','amaun pampasan kos-kos akibat prosiding','normal');validateModal_X(this,this.value);textarea_kerosakan1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanprosidingAward_check"+i+"','no','amaun pampasan kos-kos akibat prosiding','normal');textarea_kerosakan1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanprosidingAward_check"+i+"','no','amaun pampasan kos-kos akibat prosiding','normal');textarea_kerosakan1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"txtUlasanprosidingAward_check"+i+"\" class=\"alert_msg\" ></span> ";		
		 codes1 +=  "#if($readmode == 'edit' ) ";
		 
    if(ttprosiding>1 && ttprosiding==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_prosiding(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_prosiding(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat prosiding'> "+
	" </span>"; 
	}
	if(ttprosiding>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_prosiding(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_prosiding(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat prosiding' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#prosiding").html(codes1);
	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(prosiding_temp1_length > 1 && document.${formName}.txtUlasanprosiding.length > 1 )
	{
	for (t = 0; t < prosiding_temp1_length; t++)
    {	
    document.${formName}.txtUlasanprosiding[t].value = document.${formName}.prosiding_temp1[t].value;
	document.${formName}.txtUlasanprosidingAward[t].value = document.${formName}.prosiding_temp2[t].value;
    }
	}
	else if(prosiding_temp1_length > 1 && document.${formName}.txtUlasanprosiding.length < 1 )
	{
	for (t = 0; t < prosiding_temp1_length; t++)
    {	
    document.${formName}.txtUlasanprosiding.value = document.${formName}.prosiding_temp1[0].value;
	document.${formName}.txtUlasanprosidingAward.value = document.${formName}.prosiding_temp2[0].value;
    }
	}
	else if(prosiding_temp1_length == 1)
	{
	document.${formName}.txtUlasanprosiding.value = document.${formName}.prosiding_temp1.value;
	document.${formName}.txtUlasanprosidingAward.value = document.${formName}.prosiding_temp2.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(prosiding_temp1_length > 1)
	{
	for (t = 0; t < prosiding_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.prosiding_temp1[index_tolak].value = "";	
	var element = document.${formName}.prosiding_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	document.${formName}.prosiding_temp2[index_tolak].value = "";	
	var element2 = document.${formName}.prosiding_temp2[index_tolak];
    element2.parentNode.removeChild(element2);
	}
    }	
	}
	else if(prosiding_temp1_length == 1)
	{	
	 document.${formName}.prosiding_temp1.value = "";			
	 var element = document.${formName}.prosiding_temp1;
     element.parentNode.removeChild(element);	 
	 document.${formName}.prosiding_temp2.value = "";			
	 var element2 = document.${formName}.prosiding_temp2;
     element2.parentNode.removeChild(element2);	
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttprosiding; i++)
    {		
    if(ttprosiding==1)
    {	
	check_length(document.${formName}.txtUlasanprosiding,'400','txtUlasanprosiding_check','txtUlasanprosiding_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanprosiding[i],'400','txtUlasanprosiding_check'+i,'txtUlasanprosiding_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}



function textarea_lainpampasan(tambahtolak,jenis,index_tolak)
{

var lainpampasan_temp1_length=0;

if(document.${formName}.lainpampasan_temp1 != null)
{

if(document.${formName}.lainpampasan_temp1.length>0)
{
lainpampasan_temp1_length = document.${formName}.lainpampasan_temp1.length;
}
else
{
lainpampasan_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanlainpampasan!=null)
{

if(document.${formName}.txtUlasanlainpampasan.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanlainpampasan.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='lainpampasan_tempX1' name='lainpampasan_tempX1' value= '"+document.${formName}.txtUlasanlainpampasan[i].value+"'></td></tr>";
 code_temp1 += "<tr><td><input type='hidden' id='lainpampasan_tempX2' name='lainpampasan_tempX2' value= '"+document.${formName}.txtUlasanlainpampasanAward[i].value+"'></td></tr>"; 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='lainpampasan_tempX1' name='lainpampasan_tempX1' value= '"+document.${formName}.txtUlasanlainpampasan.value+"'></td></tr>";
code_temp1 += "<tr><td><input type='hidden' id='lainpampasan_tempX2' name='lainpampasan_tempX2' value= '"+document.${formName}.txtUlasanlainpampasanAward.value+"'></td></tr>"; 
}
}

$jquery("#lainpampasan_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(lainpampasan_temp1_length>0)
{
ttlainpampasan = lainpampasan_temp1_length;
}
else
{
ttlainpampasan = 1;
}
}
if(jenis == "tambah")
{
ttlainpampasan = ttlainpampasan + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttlainpampasan = ttlainpampasan + parseInt(tambahtolak);
}




  for (i = 0; i < ttlainpampasan; i++)
  {	
  if(ttlainpampasan==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.lainpampasan_tempX1[1].value
	temp_amaunt = document.${formName}.lainpampasan_tempX2[1].value
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.lainpampasan_tempX1[0].value
	temp_amaunt = document.${formName}.lainpampasan_tempX2[0].value	
    } 	
	}		
   
	codes1 += "<table width='100%'><tr>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanlainpampasan\" id=\"txtUlasanlainpampasan\" cols=\"40\"   rows=\"4\""+          
         "onBlur=\"check_length(this,'400','txtUlasanlainpampasan_check','txtUlasanlainpampasan_num','normal','no','ulasan kos-kos akibat lainpampasan');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,400,'txtUlasanlainpampasan_check','txtUlasanlainpampasan_num','normal','no','ulasan kos-kos akibat lainpampasan');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'400','txtUlasanlainpampasan_check','txtUlasanlainpampasan_num','normal','no','ulasan kos-kos akibat lainpampasan');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanlainpampasan_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanlainpampasan_num\" id=\"txtUlasanlainpampasan_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanlainpampasan_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr>"+	
	"<td align='left' valign='top' > "+
	     "JUMLAH RM :  <input name=\"txtUlasanlainpampasanAward\" id=\"txtUlasanlainpampasanAward\" style=\"text-align:right\"  size=\"15\" maxlength=\"15\" "+          
    "onBlur=\"validateTarikh(this,this.value);validateModal_X(this,this.value);"+
	"checking_validation(this,'txtUlasanlainpampasanAward_check','no','amaun pampasan kos-kos akibat lainpampasan','normal');textarea_kerosakan1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);"+
	"checking_validation(this,'txtUlasanlainpampasanAward_check','no','amaun pampasan kos-kos akibat lainpampasan','normal');textarea_kerosakan1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);"+
		 "checking_validation(this,'txtUlasanlainpampasanAward_check','no','amaun pampasan kos-kos akibat lainpampasan','normal');textarea_kerosakan1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"txtUlasanlainpampasanAward_check\" class=\"alert_msg\" ></span> ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_lainpampasan(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_lainpampasan(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat lainpampasan'> "+
	      " </span>"; 	
	     if(ttlainpampasan>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_lainpampasan(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_lainpampasan(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat lainpampasan' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1+=" </td>"+	
	"</tr></table>";
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttlainpampasan==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.lainpampasan_tempX1.value
	temp_amaunt = document.${formName}.lainpampasan_tempX2.value
	}	
	else if(ttlainpampasan>2 && i!=(ttlainpampasan-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.lainpampasan_tempX1[i].value
	temp_amaunt = document.${formName}.lainpampasan_tempX2[i].value
	}	
	else if(ttlainpampasan>1 && i!=(ttlainpampasan+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.lainpampasan_tempX1[parseInt(index_tolak)+1].value
	temp_amaunt = document.${formName}.lainpampasan_tempX2[parseInt(index_tolak)+1].value
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.lainpampasan_tempX1[i].value
	temp_amaunt = document.${formName}.lainpampasan_tempX2[i].value	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.lainpampasan_tempX1[i+1].value	
	temp_amaunt = document.${formName}.lainpampasan_tempX2[i+1].value
   }	

	}
	else if(ttlainpampasan==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.lainpampasan_tempX1[1].value;
	temp_amaunt = document.${formName}.lainpampasan_tempX2[1].value;
	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.lainpampasan_tempX1[0].value;
	temp_amaunt = document.${formName}.lainpampasan_tempX2[0].value;
	
   }
   }		
	
	codes1 += "<table width='100%'><tr>"+
	"<td >"+
	     "<textarea name=\"txtUlasanlainpampasan\" id=\"txtUlasanlainpampasan\" cols=\"40\"   rows=\"4\""+          
         "onBlur=\"check_length(this,'400','txtUlasanlainpampasan_check"+i+"','txtUlasanlainpampasan_num"+i+"','normal','no','ulasan kos-kos akibat lainpampasan');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,400,'txtUlasanlainpampasan_check"+i+"','txtUlasanlainpampasan_num"+i+"','normal','no','ulasan kos-kos akibat lainpampasan');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'400','txtUlasanlainpampasan_check"+i+"','txtUlasanlainpampasan_num"+i+"','normal','no','ulasan kos-kos akibat lainpampasan');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanlainpampasan_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanlainpampasan_num"+i+"\" id=\"txtUlasanlainpampasan_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanlainpampasan_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td align='left' valign='top' > "+
	     "JUMLAH RM : <input name=\"txtUlasanlainpampasanAward\" id=\"txtUlasanlainpampasanAward\" size=\"15\" style=\"text-align:right\"  maxlength=\"15\" "+          
         "onBlur=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanlainpampasanAward_check"+i+"','no','amaun pampasan kos-kos akibat lainpampasan','normal');validateModal_X(this,this.value);textarea_kerosakan1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanlainpampasanAward_check"+i+"','no','amaun pampasan kos-kos akibat lainpampasan','normal');textarea_kerosakan1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanlainpampasanAward_check"+i+"','no','amaun pampasan kos-kos akibat lainpampasan','normal');textarea_kerosakan1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"txtUlasanlainpampasanAward_check"+i+"\" class=\"alert_msg\" ></span> ";		
		 codes1 +=  "#if($readmode == 'edit' ) ";
		 
    if(ttlainpampasan>1 && ttlainpampasan==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_lainpampasan(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_lainpampasan(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat lainpampasan'> "+
	" </span>"; 
	}
	if(ttlainpampasan>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_lainpampasan(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_lainpampasan(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat lainpampasan' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#lainpampasan").html(codes1);
	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(lainpampasan_temp1_length > 1 && document.${formName}.txtUlasanlainpampasan.length > 1 )
	{
	for (t = 0; t < lainpampasan_temp1_length; t++)
    {	
    document.${formName}.txtUlasanlainpampasan[t].value = document.${formName}.lainpampasan_temp1[t].value;
	document.${formName}.txtUlasanlainpampasanAward[t].value = document.${formName}.lainpampasan_temp2[t].value;
    }
	}
	else if(lainpampasan_temp1_length > 1 && document.${formName}.txtUlasanlainpampasan.length < 1 )
	{
	for (t = 0; t < lainpampasan_temp1_length; t++)
    {	
    document.${formName}.txtUlasanlainpampasan.value = document.${formName}.lainpampasan_temp1[0].value;
	document.${formName}.txtUlasanlainpampasanAward.value = document.${formName}.lainpampasan_temp2[0].value;
    }
	}
	else if(lainpampasan_temp1_length == 1)
	{
	document.${formName}.txtUlasanlainpampasan.value = document.${formName}.lainpampasan_temp1.value;
	document.${formName}.txtUlasanlainpampasanAward.value = document.${formName}.lainpampasan_temp2.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(lainpampasan_temp1_length > 1)
	{
	for (t = 0; t < lainpampasan_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.lainpampasan_temp1[index_tolak].value = "";	
	var element = document.${formName}.lainpampasan_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	document.${formName}.lainpampasan_temp2[index_tolak].value = "";	
	var element2 = document.${formName}.lainpampasan_temp2[index_tolak];
    element2.parentNode.removeChild(element2);
	}
    }	
	}
	else if(lainpampasan_temp1_length == 1)
	{	
	 document.${formName}.lainpampasan_temp1.value = "";			
	 var element = document.${formName}.lainpampasan_temp1;
     element.parentNode.removeChild(element);	 
	 document.${formName}.lainpampasan_temp2.value = "";			
	 var element2 = document.${formName}.lainpampasan_temp2;
     element2.parentNode.removeChild(element2);	
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttlainpampasan; i++)
    {		
    if(ttlainpampasan==1)
    {	
	check_length(document.${formName}.txtUlasanlainpampasan,'400','txtUlasanlainpampasan_check','txtUlasanlainpampasan_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanlainpampasan[i],'400','txtUlasanlainpampasan_check'+i,'txtUlasanlainpampasan_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}




function textarea_kerosakan(tambahtolak,jenis,index_tolak)
{



var kerosakan_temp1_length=0;

if(document.${formName}.kerosakan_temp1 != null)
{

if(document.${formName}.kerosakan_temp1.length>0)
{
kerosakan_temp1_length = document.${formName}.kerosakan_temp1.length;
}
else
{
kerosakan_temp1_length = 1;
}
}

var code_temp = "";
if(document.${formName}.txtUlasanKerosakan!=null)
{


if(document.${formName}.txtUlasanKerosakan.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanKerosakan.length; i++)
 {
 code_temp += "<tr><td><input type='hidden' id='temp_temp' name='temp_temp' value= '"+document.${formName}.txtUlasanKerosakan[i].value+"'></td></tr>";
 code_temp += "<tr><td><input type='hidden' id='temp_temp1' name='temp_temp1' value= '"+document.${formName}.txtUlasanKerosakanAward[i].value+"'></td></tr>"; 
 } 
}
else
{
code_temp += "<tr><td><input type='hidden' id='temp_temp' name='temp_temp' value= '"+document.${formName}.txtUlasanKerosakan.value+"'></td></tr>";
code_temp += "<tr><td><input type='hidden' id='temp_temp1' name='temp_temp1' value= '"+document.${formName}.txtUlasanKerosakanAward.value+"'></td></tr>"; 
}
}

$jquery("#kerosakan_temp").html(""+code_temp); 
var codes = "";

if(jenis == "umum")
{
if(kerosakan_temp1_length>0)
{
tt = kerosakan_temp1_length;
}
else
{
tt = 1;
}
}

//alert("sebelum::"+tt);

if(jenis == "tambah")
{
tt = tt + parseInt(tambahtolak);


}
if(jenis == "tolak")
{
tt = tt + parseInt(tambahtolak);
}



//alert("selepas::"+tt);
  for (i = 0; i < tt; i++)
  {	
  if(tt==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.temp_temp[1].value
	temp_amaunt = document.${formName}.temp_temp1[1].value
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.temp_temp[0].value
	temp_amaunt = document.${formName}.temp_temp1[0].value	
    } 	
	}		
   
	codes += "<table width='100%'><tr>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanKerosakan\" id=\"txtUlasanKerosakan\" cols=\"40\"   rows=\"4\""+          
         "onBlur=\"check_length(this,'400','txtUlasanKerosakan_check','txtUlasanKerosakan_num','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,400,'txtUlasanKerosakan_check','txtUlasanKerosakan_num','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'400','txtUlasanKerosakan_check','txtUlasanKerosakan_num','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanKerosakan_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanKerosakan_num\" id=\"txtUlasanKerosakan_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanKerosakan_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr>"+	
	"<td align='left' valign='top' > "+
	     "JUMLAH RM :  <input name=\"txtUlasanKerosakanAward\" id=\"txtUlasanKerosakanAward\" style=\"text-align:right\"  size=\"15\" maxlength=\"15\" "+          
    "onBlur=\"validateTarikh(this,this.value);validateModal_X(this,this.value);"+
	"checking_validation(this,'txtUlasanKerosakanAward_check','no','amaun pampasan kerosakan','normal');textarea_kerosakan1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);"+
	"checking_validation(this,'txtUlasanKerosakanAward_check','no','amaun pampasan kerosakan','normal');textarea_kerosakan1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);"+
		 "checking_validation(this,'txtUlasanKerosakanAward_check','no','amaun pampasan kerosakan','normal');textarea_kerosakan1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"txtUlasanKerosakanAward_check\" class=\"alert_msg\" ></span> ";	 	
		 codes +=  "#if($readmode == 'edit' ) ";
	     codes += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+'  onkeypress=\"textarea_kerosakan(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_kerosakan(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kerosakan'> "+
	      " </span>"; 	
	     if(tt>1) {      
	     codes += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_kerosakan(-1,'tolak','');textarea_kerosakan1()\"  onClick=\"textarea_kerosakan(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kerosakan' > "+
	"</span> ";
	}
	codes +="#end";		 
	codes+=" </td>"+	
	"</tr></table>";
	}
	else
	{
		
	var temp_value = "";
	var temp_amaunt = "";
	
	if(tt==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.temp_temp.value
	temp_amaunt = document.${formName}.temp_temp1.value
	}	
	else if(tt>2 && i!=(tt-1) && jenis == "tambah")
	{
			
	temp_value = document.${formName}.temp_temp[i].value
	temp_amaunt = document.${formName}.temp_temp1[i].value
//	alert("111:::"+tt);
	}	
	else if(tt>1 && i!=(tt+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.temp_temp[parseInt(index_tolak)+1].value
	temp_amaunt = document.${formName}.temp_temp1[parseInt(index_tolak)+1].value
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.temp_temp[i].value
	temp_amaunt = document.${formName}.temp_temp1[i].value	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.temp_temp[i+1].value	
	temp_amaunt = document.${formName}.temp_temp1[i+1].value
   }	

	}
	else if(tt==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.temp_temp[1].value;
	temp_amaunt = document.${formName}.temp_temp1[1].value;
	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.temp_temp[0].value;
	temp_amaunt = document.${formName}.temp_temp1[0].value;
	
   }
   }		
	
	
	
	
	codes += "<table width='100%'><tr>"+
	"<td >"+
	     "<textarea name=\"txtUlasanKerosakan\" id=\"txtUlasanKerosakan\" cols=\"40\"   rows=\"4\""+          
         "onBlur=\"check_length(this,'400','txtUlasanKerosakan_check"+i+"','txtUlasanKerosakan_num"+i+"','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,400,'txtUlasanKerosakan_check"+i+"','txtUlasanKerosakan_num"+i+"','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'400','txtUlasanKerosakan_check"+i+"','txtUlasanKerosakan_num"+i+"','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanKerosakan_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanKerosakan_num"+i+"\" id=\"txtUlasanKerosakan_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanKerosakan_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td align='left' valign='top' > "+
	     "JUMLAH RM : <input name=\"txtUlasanKerosakanAward\" id=\"txtUlasanKerosakanAward\" size=\"15\" style=\"text-align:right\"  maxlength=\"15\" "+          
         "onBlur=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanKerosakanAward_check"+i+"','no','amaun pampasan kerosakan','normal');validateModal_X(this,this.value);textarea_kerosakan1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanKerosakanAward_check"+i+"','no','amaun pampasan kerosakan','normal');textarea_kerosakan1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanKerosakanAward_check"+i+"','no','amaun pampasan kerosakan','normal');textarea_kerosakan1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"txtUlasanKerosakanAward_check"+i+"\" class=\"alert_msg\" ></span> ";		
		 codes +=  "#if($readmode == 'edit' ) ";
		 
    if(tt>1 && tt==(i+1)) {  	
	codes += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_kerosakan(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_kerosakan(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kerosakan'> "+
	" </span>"; 
	}
	if(tt>1) {      
	codes += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_kerosakan(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_kerosakan(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kerosakan' > "+
	"</span> ";
	}
	codes +="#end";		 
	codes +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
//	alert("CODES ::"+codes);
	
	$jquery("#kerosakan").html(codes);
	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(kerosakan_temp1_length > 1 && document.${formName}.txtUlasanKerosakan.length > 1 )
	{
	for (t = 0; t < kerosakan_temp1_length; t++)
    {	
    document.${formName}.txtUlasanKerosakan[t].value = document.${formName}.kerosakan_temp1[t].value;
	document.${formName}.txtUlasanKerosakanAward[t].value = document.${formName}.kerosakan_temp2[t].value;
    }
	}
	else if(kerosakan_temp1_length > 1 && document.${formName}.txtUlasanKerosakan.length < 1 )
	{
	for (t = 0; t < kerosakan_temp1_length; t++)
    {	
    document.${formName}.txtUlasanKerosakan.value = document.${formName}.kerosakan_temp1[0].value;
	document.${formName}.txtUlasanKerosakanAward.value = document.${formName}.kerosakan_temp2[0].value;
    }
	}
	else if(kerosakan_temp1_length == 1)
	{
	document.${formName}.txtUlasanKerosakan.value = document.${formName}.kerosakan_temp1.value;
	document.${formName}.txtUlasanKerosakanAward.value = document.${formName}.kerosakan_temp2.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(kerosakan_temp1_length > 1)
	{
	for (t = 0; t < kerosakan_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.kerosakan_temp1[index_tolak].value = "";	
	var element = document.${formName}.kerosakan_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	document.${formName}.kerosakan_temp2[index_tolak].value = "";	
	var element2 = document.${formName}.kerosakan_temp2[index_tolak];
    element2.parentNode.removeChild(element2);
	}
    }	
	}
	else if(kerosakan_temp1_length == 1)
	{	
	 document.${formName}.kerosakan_temp1.value = "";			
	 var element = document.${formName}.kerosakan_temp1;
     element.parentNode.removeChild(element);	 
	 document.${formName}.kerosakan_temp2.value = "";			
	 var element2 = document.${formName}.kerosakan_temp2;
     element2.parentNode.removeChild(element2);	
	}
	}			
	
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < tt; i++)
    {		
    if(tt==1)
    {	
	check_length(document.${formName}.txtUlasanKerosakan,'400','txtUlasanKerosakan_check','txtUlasanKerosakan_num','normal','no','ulasan kerosakan');	
	}
	else
	{
	
	check_length(document.${formName}.txtUlasanKerosakan[i],'400','txtUlasanKerosakan_check'+i,'txtUlasanKerosakan_num'+i,'normal','no','ulasan kerosakan');
	//alert(tt);		
	}		 
	}	
	}
	
	
}













function textarea_Lain(tambahtolak,jenis,index_tolak)
{
//alert("test");
var Lain_temp1_length=0;

if(document.${formName}.Lain_temp1 != null)
{

if(document.${formName}.Lain_temp1.length>0)
{
Lain_temp1_length = document.${formName}.Lain_temp1.length;
}
else
{
Lain_temp1_length = 1;
}
}

var code_temp = "";
if(document.${formName}.txtUlasanLain!=null)
{

if(document.${formName}.txtUlasanLain.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanLain.length; i++)
 {
 code_temp += "<tr><td><input type='hidden' id='Lain_tempX1' name='Lain_tempX1' value= '"+document.${formName}.txtUlasanLain[i].value+"'></td></tr>";
 code_temp += "<tr><td><input type='hidden' id='Lain_tempX2' name='Lain_tempX2' value= '"+document.${formName}.txtUlasanLainAward[i].value+"'></td></tr>"; 
 } 
}
else
{
code_temp += "<tr><td><input type='hidden' id='Lain_tempX1' name='Lain_tempX1' value= '"+document.${formName}.txtUlasanLain.value+"'></td></tr>";
code_temp += "<tr><td><input type='hidden' id='Lain_tempX2' name='Lain_tempX2' value= '"+document.${formName}.txtUlasanLainAward.value+"'></td></tr>"; 
}
}

$jquery("#Lain_temp").html(""+code_temp); 
var codes = "";

if(jenis == "umum")
{
if(Lain_temp1_length>0)
{
ttlain = Lain_temp1_length;
}
else
{
ttlain = 1;
}
}
if(jenis == "tambah")
{
ttlain = ttlain + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttlain = ttlain + parseInt(tambahtolak);
}




  for (i = 0; i < ttlain; i++)
  {	
  if(ttlain==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.Lain_tempX1[1].value
	temp_amaunt = document.${formName}.Lain_tempX2[1].value
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.Lain_tempX1[0].value
	temp_amaunt = document.${formName}.Lain_tempX2[0].value	
    } 	
	}		
   
	codes += "<table width='100%'><tr>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanLain\" id=\"txtUlasanLain\" cols=\"40\"   rows=\"4\""+          
         "onBlur=\"check_length(this,'400','txtUlasanLain_check','txtUlasanLain_num','normal','no','ulasan lain - lain kos ');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,400,'txtUlasanLain_check','txtUlasanLain_num','normal','no','ulasan lain - lain kos ');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'400','txtUlasanLain_check','txtUlasanLain_num','normal','no','ulasan lain - lain kos ');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanLain_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanLain_num\" id=\"txtUlasanLain_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanLain_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr>"+	
	"<td align='left' valign='top' > "+
	     "JUMLAH RM :  <input name=\"txtUlasanLainAward\" id=\"txtUlasanLainAward\" style=\"text-align:right\"  size=\"15\" maxlength=\"15\" "+          
    "onBlur=\"validateTarikh(this,this.value);validateModal_X(this,this.value);"+
	"checking_validation(this,'txtUlasanLainAward_check','no','amaun pampasan lain - lain kos','normal');textarea_kerosakan1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);"+
	"checking_validation(this,'txtUlasanLainAward_check','no','amaun pampasan lain - lain kos','normal');textarea_kerosakan1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);"+
		 "checking_validation(this,'txtUlasanLainAward_check','no','amaun pampasan lain - lain kos','normal');textarea_kerosakan1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"txtUlasanLainAward_check\" class=\"alert_msg\" ></span> ";	 	
		 codes +=  "#if($readmode == 'edit' ) ";
	     codes += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+'  onkeypress=\"textarea_Lain(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_Lain(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan lain - lain kos'> "+
	      " </span>"; 	
	     if(ttlain>1) {      
	     codes += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_Lain(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_Lain(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan lain - lain kos' > "+
	"</span> ";
	}
	codes +="#end";		 
	codes+=" </td>"+	
	"</tr></table>";
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttlain==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.Lain_tempX1.value
	temp_amaunt = document.${formName}.Lain_tempX2.value
	}	
	else if(ttlain>2 && i!=(ttlain-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.Lain_tempX1[i].value
	temp_amaunt = document.${formName}.Lain_tempX2[i].value
	}	
	else if(ttlain>1 && i!=(ttlain+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.Lain_tempX1[parseInt(index_tolak)+1].value
	temp_amaunt = document.${formName}.Lain_tempX2[parseInt(index_tolak)+1].value
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.Lain_tempX1[i].value
	temp_amaunt = document.${formName}.Lain_tempX2[i].value	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.Lain_tempX1[i+1].value	
	temp_amaunt = document.${formName}.Lain_tempX2[i+1].value
   }	

	}
	else if(ttlain==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.Lain_tempX1[1].value;
	temp_amaunt = document.${formName}.Lain_tempX2[1].value;
	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.Lain_tempX1[0].value;
	temp_amaunt = document.${formName}.Lain_tempX2[0].value;
	
   }
   }		
	
	codes += "<table width='100%'><tr>"+
	"<td >"+
	     "<textarea name=\"txtUlasanLain\" id=\"txtUlasanLain\" cols=\"40\"   rows=\"4\""+          
         "onBlur=\"check_length(this,'400','txtUlasanLain_check"+i+"','txtUlasanLain_num"+i+"','normal','no','ulasan lain - lain kos');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,400,'txtUlasanLain_check"+i+"','txtUlasanLain_num"+i+"','normal','no','ulasan lain - lain kos');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'400','txtUlasanLain_check"+i+"','txtUlasanLain_num"+i+"','normal','no','ulasan lain - lain kos');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanLain_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanLain_num"+i+"\" id=\"txtUlasanLain_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanLain_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td align='left' valign='top' > "+
	     "JUMLAH RM : <input name=\"txtUlasanLainAward\" id=\"txtUlasanLainAward\" size=\"15\" style=\"text-align:right\"  maxlength=\"15\" "+          
         "onBlur=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanLainAward_check"+i+"','no','amaun pampasan lain - lain kos','normal');validateModal_X(this,this.value);textarea_kerosakan1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanLainAward_check"+i+"','no','amaun pampasan lain - lain kos','normal');textarea_kerosakan1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);checking_validation(this,'txtUlasanLainAward_check"+i+"','no','amaun pampasan lain - lain kos','normal');textarea_kerosakan1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"txtUlasanLainAward_check"+i+"\" class=\"alert_msg\" ></span> ";		
		 codes +=  "#if($readmode == 'edit' ) ";
		 
    if(ttlain>1 && ttlain==(i+1)) {  	
	codes += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+'  onkeypress=\"textarea_Lain(1,'tambah','');textarea_kerosakan1()\" onClick=\"textarea_Lain(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan  lain - lain kos'> "+
	" </span>"; 
	}
	if(ttlain>1) {      
	codes += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_Lain(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_Lain(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan lain - lain kos' > "+
	"</span> ";
	}
	codes +="#end";		 
	codes +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#Lain").html(codes);
	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(Lain_temp1_length > 1 && document.${formName}.txtUlasanLain.length > 1 )
	{
	for (t = 0; t < Lain_temp1_length; t++)
    {	
    document.${formName}.txtUlasanLain[t].value = document.${formName}.Lain_temp1[t].value;
	document.${formName}.txtUlasanLainAward[t].value = document.${formName}.Lain_temp2[t].value;
    }
	}
	else if(Lain_temp1_length > 1 && document.${formName}.txtUlasanLain.length < 1 )
	{
	for (t = 0; t < Lain_temp1_length; t++)
    {	
    document.${formName}.txtUlasanLain.value = document.${formName}.Lain_temp1[0].value;
	document.${formName}.txtUlasanLainAward.value = document.${formName}.Lain_temp2[0].value;
    }
	}
	else if(Lain_temp1_length == 1)
	{
	document.${formName}.txtUlasanLain.value = document.${formName}.Lain_temp1.value;
	document.${formName}.txtUlasanLainAward.value = document.${formName}.Lain_temp2.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(Lain_temp1_length > 1)
	{
	for (t = 0; t < Lain_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.Lain_temp1[index_tolak].value = "";	
	var element = document.${formName}.Lain_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	document.${formName}.Lain_temp2[index_tolak].value = "";	
	var element2 = document.${formName}.Lain_temp2[index_tolak];
    element2.parentNode.removeChild(element2);
	}
    }	
	}
	else if(Lain_temp1_length == 1)
	{	
	 document.${formName}.Lain_temp1.value = "";			
	 var element = document.${formName}.Lain_temp1;

     element.parentNode.removeChild(element);	 
	 document.${formName}.Lain_temp2.value = "";			
	 var element2 = document.${formName}.Lain_temp2;
     element2.parentNode.removeChild(element2);	
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttlain; i++)
    {		
    if(ttlain==1)
    {	
	check_length(document.${formName}.txtUlasanLain,'400','txtUlasanLain_check','txtUlasanLain_num','normal','no','ulasan lain - lain kos');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanLain[i],'400','txtUlasanLain_check'+i,'txtUlasanLain_num'+i,'normal','no','ulasan  lain - lain kos');	
	}		 
	}	
	}
	
	
}








function validateModal_X(elmnt,content) {
 
	
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



function textarea_kerosakan1()
{

	var kerosakan_temp1_length=0;
	
	if(document.${formName}.kerosakan_temp1 != null)
	{
	if(document.${formName}.kerosakan_temp1.length>0)
	{
	kerosakan_temp1_length = document.${formName}.kerosakan_temp1.length;
	}
	else
	{
	kerosakan_temp1_length = 1;
	}
	}

    if(kerosakan_temp1_length > 1 && document.${formName}.txtUlasanKerosakan.length > 1 )
	{
	for (t = 0; t < kerosakan_temp1_length; t++)
    {	
    document.${formName}.kerosakan_temp1[t].value = document.${formName}.txtUlasanKerosakan[t].value;
	document.${formName}.kerosakan_temp2[t].value = document.${formName}.txtUlasanKerosakanAward[t].value;
    }
	}
	else if(kerosakan_temp1_length > 1 && document.${formName}.txtUlasanKerosakan.length < 1 )
	{
	for (t = 0; t < kerosakan_temp1_length; t++)
    {	
    document.${formName}.kerosakan_temp1[0].value = document.${formName}.txtUlasanKerosakan.value;
	document.${formName}.kerosakan_temp2[0].value = document.${formName}.txtUlasanKerosakanAward.value;
    }
	}
	else if(kerosakan_temp1_length == 1)
	{
	document.${formName}.kerosakan_temp1.value = document.${formName}.txtUlasanKerosakan.value;
	document.${formName}.kerosakan_temp2.value = document.${formName}.txtUlasanKerosakanAward.value;
	}
	
	
	
	
	
	
	var prosiding_temp1_length=0;
	if(document.${formName}.prosiding_temp1 != null)
	{
	if(document.${formName}.prosiding_temp1.length>0)
	{
	prosiding_temp1_length = document.${formName}.prosiding_temp1.length;
	}
	else
	{
	prosiding_temp1_length = 1;
	}
	}

    if(prosiding_temp1_length > 1 && document.${formName}.txtUlasanprosiding.length > 1 )
	{
	for (t = 0; t < prosiding_temp1_length; t++)
    {	
    document.${formName}.prosiding_temp1[t].value = document.${formName}.txtUlasanprosiding[t].value;
	document.${formName}.prosiding_temp2[t].value = document.${formName}.txtUlasanprosidingAward[t].value;
    }
	}
	else if(prosiding_temp1_length > 1 && document.${formName}.txtUlasanprosiding.length < 1 )
	{
	for (t = 0; t < prosiding_temp1_length; t++)
    {	
    document.${formName}.prosiding_temp1[0].value = document.${formName}.txtUlasanprosiding.value;
	document.${formName}.prosiding_temp2[0].value = document.${formName}.txtUlasanprosidingAward.value;
    }
	}
	else if(prosiding_temp1_length == 1)
	{
	document.${formName}.prosiding_temp1.value = document.${formName}.txtUlasanprosiding.value;
	document.${formName}.prosiding_temp2.value = document.${formName}.txtUlasanprosidingAward.value;
	}
	
	
	var lainpampasan_temp1_length=0;
	if(document.${formName}.lainpampasan_temp1 != null)
	{
	if(document.${formName}.lainpampasan_temp1.length>0)
	{
	lainpampasan_temp1_length = document.${formName}.lainpampasan_temp1.length;
	}
	else
	{
	lainpampasan_temp1_length = 1;
	}
	}

    if(lainpampasan_temp1_length > 1 && document.${formName}.txtUlasanlainpampasan.length > 1 )
	{
	for (t = 0; t < lainpampasan_temp1_length; t++)
    {	
    document.${formName}.lainpampasan_temp1[t].value = document.${formName}.txtUlasanlainpampasan[t].value;
	document.${formName}.lainpampasan_temp2[t].value = document.${formName}.txtUlasanlainpampasanAward[t].value;
    }
	}
	else if(lainpampasan_temp1_length > 1 && document.${formName}.txtUlasanlainpampasan.length < 1 )
	{
	for (t = 0; t < lainpampasan_temp1_length; t++)
    {	
    document.${formName}.lainpampasan_temp1[0].value = document.${formName}.txtUlasanlainpampasan.value;
	document.${formName}.lainpampasan_temp2[0].value = document.${formName}.txtUlasanlainpampasanAward.value;
    }
	}
	else if(lainpampasan_temp1_length == 1)
	{
	document.${formName}.lainpampasan_temp1.value = document.${formName}.txtUlasanlainpampasan.value;
	document.${formName}.lainpampasan_temp2.value = document.${formName}.txtUlasanlainpampasanAward.value;
	}
	
	
	
	var Lain_temp1_length=0;
	if(document.${formName}.Lain_temp1 != null)
	{
	if(document.${formName}.Lain_temp1.length>0)
	{
	Lain_temp1_length = document.${formName}.Lain_temp1.length;
	}
	else
	{
	Lain_temp1_length = 1;
	}
	}

    if(Lain_temp1_length > 1 && document.${formName}.txtUlasanLain.length > 1 )
	{
	for (t = 0; t < Lain_temp1_length; t++)
    {	
    document.${formName}.Lain_temp1[t].value = document.${formName}.txtUlasanLain[t].value;
	document.${formName}.Lain_temp2[t].value = document.${formName}.txtUlasanLainAward[t].value;
    }
	}
	else if(Lain_temp1_length > 1 && document.${formName}.txtUlasanLain.length < 1 )
	{
	for (t = 0; t < Lain_temp1_length; t++)
    {	
    document.${formName}.Lain_temp1[0].value = document.${formName}.txtUlasanLain.value;
	document.${formName}.Lain_temp2[0].value = document.${formName}.txtUlasanLainAward.value;
    }
	}
	else if(Lain_temp1_length == 1)
	{
	document.${formName}.Lain_temp1.value = document.${formName}.txtUlasanLain.value;
	document.${formName}.Lain_temp2.value = document.${formName}.txtUlasanLainAward.value;
	}
	
	
	
	
	
		
	    var total_nilai = 0;
        if (document.${formName}.txtUlasanKerosakanAward.length == null)
		{
				if(document.${formName}.txtUlasanKerosakanAward.value != "")
				{				   
				   total_nilai = total_nilai + parseFloat(document.${formName}.txtUlasanKerosakanAward.value) ;					   	   
				}
				else
				{
				   total_nilai = total_nilai+0;
				}		
        } 
		else {
            for (i = 0; i < document.${formName}.txtUlasanKerosakanAward.length; i++)
			{
               if(document.${formName}.txtUlasanKerosakanAward[i].value != "")
				{			  
				   total_nilai = total_nilai + parseFloat(document.${formName}.txtUlasanKerosakanAward[i].value) ;			   	   	   
				}
				else
				{
				   total_nilai = total_nilai + 0;
				}
            }
        }		
		
		
		
		
		if (document.${formName}.txtUlasanprosidingAward.length == null)
		{
				if(document.${formName}.txtUlasanprosidingAward.value != "")
				{				   
				   total_nilai = total_nilai + parseFloat(document.${formName}.txtUlasanprosidingAward.value) ;					   	   
				}
				else
				{
				   total_nilai = total_nilai+0;
				}		
        } 
		else {
            for (i = 0; i < document.${formName}.txtUlasanprosidingAward.length; i++)
			{
               if(document.${formName}.txtUlasanprosidingAward[i].value != "")
				{			  
				   total_nilai = total_nilai + parseFloat(document.${formName}.txtUlasanprosidingAward[i].value) ;			   	   	   
				}
				else
				{
				   total_nilai = total_nilai + 0;
				}
            }
        }		
		
		
		
		
		if (document.${formName}.txtUlasanlainpampasanAward.length == null)
		{
				if(document.${formName}.txtUlasanlainpampasanAward.value != "")
				{				   
				   total_nilai = total_nilai + parseFloat(document.${formName}.txtUlasanlainpampasanAward.value) ;					   	   
				}
				else
				{
				   total_nilai = total_nilai+0;
				}		
        } 
		else {
            for (i = 0; i < document.${formName}.txtUlasanlainpampasanAward.length; i++)
			{
               if(document.${formName}.txtUlasanlainpampasanAward[i].value != "")
				{			  
				   total_nilai = total_nilai + parseFloat(document.${formName}.txtUlasanlainpampasanAward[i].value) ;			   	   	   
				}

				else
				{
				   total_nilai = total_nilai + 0;
				}
            }
        }		
		
		
		
		
		/*
		
		if (document.${formName}.txtUlasanLainAward.length == null)
		{
				if(document.${formName}.txtUlasanLainAward.value != "")
				{				   
				   total_nilai = total_nilai + parseFloat(document.${formName}.txtUlasanLainAward.value) ;					   	   
				}
				else
				{
				   total_nilai = total_nilai+0;
				}		
        } 
		else {
            for (i = 0; i < document.${formName}.txtUlasanLainAward.length; i++)
			{
               if(document.${formName}.txtUlasanLainAward[i].value != "")
				{			  
				   total_nilai = total_nilai + parseFloat(document.${formName}.txtUlasanLainAward[i].value) ;			   	   	   
				}
				else
				{
				   total_nilai = total_nilai + 0;
				}
            }
        }		
		
		*/
		
		
		if (document.${formName}.id_hakmilik_pb.length == null)
		{
				if(document.${formName}.id_hakmilik_pb.value == document.${formName}.id_siasatan_hakmilikpb.value)
				{
				document.${formName}.pampasan.value  = total_nilai;
				}		
        } 
		else {
            for (i = 0; i < document.${formName}.id_hakmilik_pb.length; i++)
			{
               if(document.${formName}.id_hakmilik_pb[i].value == document.${formName}.id_siasatan_hakmilikpb.value)
				{
				document.${formName}.pampasan[i].value  = total_nilai;
							
				}
            }
        }
		
		
		
		
}




function SuratSuruhAPBayar(id_fail,id_siasatan)
	{
	
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_fail="+id_fail+"&id_siasatan="+id_siasatan+"&report=surat_suruh_AP_Bayar"; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
	
	}
	
	
	function status_penerima()
{


        if (document.${formName}.id_hakmilik_pb.length == null)
		{
				if(document.${formName}.id_hakmilik_pb.value == document.${formName}.id_siasatan_hakmilikpb.value)
				{
				document.${formName}.id_status_penerima.value  = document.${formName}.socAwardKepada.value;				
				document.${formName}.temp_xhadir.value  = document.${formName}.txtUlasanTidakHadir.value;
				document.${formName}.temp_perintah.value  = document.${formName}.txtUlasanCatatanPerintah.value;			
				
				}		
        } 
		else {
            for (i = 0; i < document.${formName}.id_hakmilik_pb.length; i++)
			{
               if(document.${formName}.id_hakmilik_pb[i].value == document.${formName}.id_siasatan_hakmilikpb.value)
				{
				//alert("lalalalla");
				document.${formName}.id_status_penerima[i].value  = document.${formName}.socAwardKepada.value;				
				document.${formName}.temp_xhadir[i].value  = document.${formName}.txtUlasanTidakHadir.value;
				document.${formName}.temp_perintah[i].value  = document.${formName}.txtUlasanCatatanPerintah.value;			
				}
            }
        }
		

}

function PB(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}


function PB_Kosong(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.CariPB.value = "";	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}


function hapus_award(id_siasatan_award,id_siasatan,id_hakmilikpb)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "Hapus_Award";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.id_siasatan_award.value = id_siasatan_award;	
	document.${formName}.id_siasatan_hakmilikpb.value = id_hakmilikpb;	
	document.${formName}.location.value = "maklumat_award";
	document.${formName}.point.value = "maklumat_award";
	document.${formName}.submit();
	}
}

function CetakBorangLC(id_hakmilik)
{
   
	/*var url = "../servlet/ekptg.report.ppt.BorangLC?id_hakmilik="+id_hakmilik;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();*/
    
    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_hakmilik="+id_hakmilik+"&report=BorangLC&selectNoFail=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>
  
