<style type="text/css">
<!--
.style1 {color: #0000FF}
.style2 {color: #FF0000}
.style3 {
	color: #FFFFFF;
	font-weight: bold;
}
.style5 {color: #0000FF; font-weight: bold; font-style: italic; }
-->
</style>
#parse("app/ppt/paging_penarikanbalik.jsp")







#set($id_tanahumum = "")
#set($txtLokasiTanah = "")
#set($txtKeadaanLot = "")
#set($txtJenisTanaman = "")
#set($txtBerhampiran = "")
#set($txtKeadaanTanaman = "")
#set($txtUlasan = "")
#set($txtKeseluruhanLot = "")
#set($txtKawasan = "")
#set($sorBangunan = "")
#set($txtBilBangunan = "")
#set($txtKawasanTerlibat = "")

#set($txtnorujukan = "")
#set($txtnamanegeri = "")
#set($txtnamadaerah = "")
#set($txtnamajajahan = "")
#set($STATUS_LAPORAN = "")

#foreach($maklumatam in $maklumat_am_tanah_permohonan)
#set($txtLokasiTanah = $maklumatam.LOKASI_TANAH)
#set($txtKeadaanLot = $maklumatam.LOT_SELURUH_LOT)
#set($txtJenisTanaman = $maklumatam.LOT_JENIS_TANAMAN)
#set($txtBerhampiran = $maklumatam.LOT_BERHAMPIRAN)
#set($txtKeadaanTanaman = $maklumatam.LOT_KEADAAN_TANAMAN)
#set($txtUlasan = $maklumatam.ULASAN)
#set($txtKeseluruhanLot = $maklumatam.RUPABUMI_SELURUH_LOT)
#set($txtKawasan = $maklumatam.RUPABUMI_KWSN_TERLIBAT)
#set($sorBangunan = $maklumatam.MELIBATKAN_BANGUNAN)
#set($txtBilBangunan = $maklumatam.BILANGAN_BANGUNAN)
#set($txtKawasanTerlibat = $maklumatam.LOT_KWSN_TERLIBAT)
#end


#foreach($listmaklumat in $maklumat_hakmilik)
#set($id_hakmilik = $listmaklumat.ID_HAKMILIK)
#set($txtnorujukan = $listmaklumat.NO_PT)
#set($txtnamanegeri = $listmaklumat.NAMA_NEGERI)
#set($txtnamadaerah = $listmaklumat.NAMA_DAERAH)
#set($txtnamajajahan = $listmaklumat.NAMA_MUKIM)
#set($STATUS_LAPORAN = $listmaklumat.STATUS_LAPORAN)
#end


#foreach($maklumatam in $maklumat_am_tanah)
#set($id_tanahumum = $maklumatam.ID_TANAHUMUM)
#set($txtKawasanTerlibat = $maklumatam.LOT_KWSN_TERLIBAT)
#set($txtLokasiTanah = $maklumatam.LOKASI_TANAH)
#set($txtKeadaanLot = $maklumatam.LOT_SELURUH_LOT)
#set($txtJenisTanaman = $maklumatam.LOT_JENIS_TANAMAN)
#set($txtBerhampiran = $maklumatam.LOT_BERHAMPIRAN)
#set($txtKeadaanTanaman = $maklumatam.LOT_KEADAAN_TANAMAN)
#set($txtUlasan = $maklumatam.ULASAN)
#set($txtKeseluruhanLot = $maklumatam.RUPABUMI_SELURUH_LOT)
#set($txtKawasan = $maklumatam.RUPABUMI_KWSN_TERLIBAT)
#set($sorBangunan = $maklumatam.MELIBATKAN_BANGUNAN)
#set($txtBilBangunan = $maklumatam.BILANGAN_BANGUNAN)
#end

#if($txtKawasanTerlibat == "" && $txtLokasiTanah == "" && $txtKeadaanLot == "" && $txtJenisTanaman == "" && $txtBerhampiran == "" && $txtKeadaanTanaman == ""
&& $txtUlasan == "" && $txtKeseluruhanLot == "" && $txtKawasan == "")
#foreach($mak in $senarai_laporan_tanah)
#set($txtKeadaanLot = $mak.KEADAAN_TANAH)
#set($txtJenisTanaman = $mak.TANAMAN)
#set($txtKeseluruhanLot = $mak.RUPABUMI)
#end
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
    <td> #parse("app/ppt/header_ppt.jsp")</td>
  </tr>
  <tr>
    <td>
    
     <fieldset>
    
    
    <table width="100%">
    
    <tr>
    <td>
   
    <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
         <li class="TabbedPanelsTab" tabindex="0" onClick="screen2('$id_permohonan','$id_pembatalan')" >Senarai Lot Penarikan</li>
        <li class="TabbedPanelsTab" tabindex="0" id="maklumat_am" onClick="LaporanTanah('$!id_hakmilik','$!id_pembatalan')">Maklumat Am Tanah</li>
        <li class="TabbedPanelsTab" tabindex="0" onclick="PerihalTanah('$!id_hakmilik','$!id_pembatalan')">Perihal Tanah</li>
         <li class="TabbedPanelsTab" tabindex="0" onclick="LaporanKerosakan('$!id_hakmilik','$!id_pembatalan')">Laporan Kerosakan</li>
      </ul>
      <div class="TabbedPanelsContentGroup">
       <div class="TabbedPanelsContent">
       </div>
        <div class="TabbedPanelsContent">     
        </div>
        <div class="TabbedPanelsContent"> 
        
        
        <table width="100%">
  <tr>
    <td>
    <fieldset>
    <table width="100%" >
    <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No. Lot</td>
    <td width="1%">:</td>
    <td width="70%">$txtnorujukan</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Negeri</td>
    <td>:</td>
    <td>$txtnamanegeri</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Daerah/Jajahan</td>
    <td>:</td>
    <td>$txtnamadaerah</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Bandar/Pekan/Mukim</td>
    <td>:</td>
    <td>$txtnamajajahan</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Status Laporan Tanah</td>
    <td>:</td>
    <td>$STATUS_LAPORAN</td>
  </tr>
    <tr>
              <td width="1%" valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
              <td width="28%" valign="top">Lokasi Tanah</td>
              <td width="1%" valign="top">:</td>
              <td width="70%">   
              <!--           
               <textarea name="txtLokasiTanah" id="txtLokasiTanah" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtLokasiTanah_check','yes','lokasi tanah','normal')"  onKeyUp="checking_validation(this,'txtLokasiTanah_check','yes','lokasi tanah','normal')" $readonlymode class = "$disabledmode" >$txtLokasiTanah</textarea>     
         <div id="txtLokasiTanah_check" style="color:red" ></div>     
         -->
         
           <textarea name="txtLokasiTanah" id="txtLokasiTanah" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtLokasiTanah_check','txtLokasiTanah_num','normal','yes','lokasi tanah');"  
         onKeyup="check_length(this,'4000','txtLokasiTanah_check','txtLokasiTanah_num','normal','yes','lokasi tanah');" 
         onKeydown="check_length(this,'4000','txtLokasiTanah_check','txtLokasiTanah_num','normal','yes','lokasi tanah');"                    
          $readonlymode class = "$disabledmode" 
        >$txtLokasiTanah</textarea>
       #if($readmode == "edit")           
        <div><span id="txtLokasiTanah_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtLokasiTanah_num" id="txtSebabPembatalan_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtLokasiTanah_check" class="alert_msg" ></div>                 </td>
            </tr>
            <tr style="display:none">
              <td width="1%" valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
              <td width="28%" valign="top">Keadaan Lot</td>
              <td width="1%" valign="top">:</td>
              <td width="70%"> 
              <!--             
               <textarea name="txtKeadaanLot" id="txtKeadaanLot" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKeadaanLot_check','yes','keadaan lot','normal')"  onKeyUp="checking_validation(this,'txtKeadaanLot_check','yes','keadaan lot','normal')" $readonlymode class = "$disabledmode" >$txtKeadaanLot</textarea>     
         <div id="txtKeadaanLot_check" style="color:red" ></div>       
         -->
         
            <textarea name="txtKeadaanLot" id="txtKeadaanLot" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtKeadaanLot_check','txtKeadaanLot_num','normal','yes','keadaan lot');"  
         onKeyup="check_length(this,'4000','txtKeadaanLot_check','txtKeadaanLot_num','normal','yes','keadaan lot');" 
         onKeydown="check_length(this,'4000','txtKeadaanLot_check','txtKeadaanLot_num','normal','yes','keadaan lot');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeadaanLot</textarea>
       #if($readmode == "edit")           
        <div><span id="txtKeadaanLot_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKeadaanLot_num" id="txtKeadaanLot_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtKeadaanLot_check" class="alert_msg" ></div>                </td>
            </tr>
            <tr style="display:none">
              <td width="1%" valign="top">&nbsp;</td>
              <td width="28%" valign="top">Jenis Tanaman</td>
              <td width="1%" valign="top">:</td>
              <td width="70%">  
              
              <!--            
               <textarea name="txtJenisTanaman" id="txtJenisTanaman" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtJenisTanaman_check','yes','jenis tanaman','normal')"  onKeyUp="checking_validation(this,'txtJenisTanaman_check','yes','jenis tanaman','normal')" $readonlymode class = "$disabledmode" >$txtJenisTanaman</textarea>     
         <div id="txtJenisTanaman_check" style="color:red" ></div>      
         -->
         
          <textarea name="txtJenisTanaman" id="txtJenisTanaman" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtJenisTanaman_check','txtJenisTanaman_num','normal','no','jenis tanaman');"  
         onKeyup="check_length(this,'4000','txtJenisTanaman_check','txtJenisTanaman_num','normal','no','jenis tanaman');" 
         onKeydown="check_length(this,'4000','txtJenisTanaman_check','txtJenisTanaman_num','normal','no','jenis tanaman');"                    
          $readonlymode class = "$disabledmode" 
        >$txtJenisTanaman</textarea>
       #if($readmode == "edit")           
        <div><span id="txtJenisTanaman_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtJenisTanaman_num" id="txtJenisTanaman_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtJenisTanaman_check" class="alert_msg" ></div>                 </td>
            </tr>
            <tr style="display:none">
              <td width="1%" valign="top">&nbsp;</td>
              <td width="28%" valign="top">Berhampiran Dengan</td>
              <td width="1%" valign="top">:</td>
              <td width="70%"> 
              <!--             
               <textarea name="txtBerhampiran" id="txtBerhampiran" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtBerhampiran_check','yes','maklumat berhampiran','normal')"  onKeyUp="checking_validation(this,'txtBerhampiran_check','yes','maklumat berhampiran','normal')" $readonlymode class = "$disabledmode" >$txtBerhampiran</textarea>     
         <div id="txtBerhampiran_check" style="color:red" ></div>   -->
         
          <textarea name="txtBerhampiran" id="txtBerhampiran" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtBerhampiran_check','txtBerhampiran_num','normal','no','maklumat berhampiran');"  
         onKeyup="check_length(this,'4000','txtBerhampiran_check','txtBerhampiran_num','normal','no','maklumat berhampiran');" 
         onKeydown="check_length(this,'4000','txtBerhampiran_check','txtBerhampiran_num','normal','no','maklumat berhampiran');"                    
          $readonlymode class = "$disabledmode" 
        >$txtBerhampiran</textarea>
       #if($readmode == "edit")           
        <div><span id="txtBerhampiran_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtBerhampiran_num" id="txtBerhampiran_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtBerhampiran_check" class="alert_msg" ></div>                    </td>
            </tr>
            <tr style="display:none">
              <td width="1%" valign="top">&nbsp;</td>
              <td width="28%" valign="top">Keadaan Tanaman / Umur</td>
              <td width="1%" valign="top">:</td>
              <td width="70%"> 
              <!--             
               <textarea name="txtKeadaanTanaman" id="txtKeadaanTanaman" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKeadaanTanaman_check','yes','keadaan tanaman','normal')"  onKeyUp="checking_validation(this,'txtKeadaanTanaman_check','yes','keadaan tanaman','normal')" $readonlymode class = "$disabledmode" >$txtKeadaanTanaman</textarea>     
         <div id="txtKeadaanTanaman_check" style="color:red" ></div>     
         -->
         
          <textarea name="txtKeadaanTanaman" id="txtKeadaanTanaman" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtKeadaanTanaman_check','txtKeadaanTanaman_num','normal','no','keadaan tanaman');"  
         onKeyup="check_length(this,'4000','txtKeadaanTanaman_check','txtKeadaanTanaman_num','normal','no','keadaan tanaman');" 
         onKeydown="check_length(this,'4000','txtKeadaanTanaman_check','txtKeadaanTanaman_num','normal','no','keadaan tanaman');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeadaanTanaman</textarea>
       #if($readmode == "edit")           
        <div><span id="txtKeadaanTanaman_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKeadaanTanaman_num" id="txtKeadaanTanaman_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtKeadaanTanaman_check" class="alert_msg" ></div>                  </td>
            </tr>
            
            <tr style="display:none">
              <td width="1%" valign="top">&nbsp;</td>
              <td width="28%" valign="top">Kawasan Terlibat</td>
              <td width="1%" valign="top">:</td>
              <td width="70%"> 
              <!--             
               <textarea name="txtKeadaanTanaman" id="txtKeadaanTanaman" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKeadaanTanaman_check','yes','keadaan tanaman','normal')"  onKeyUp="checking_validation(this,'txtKeadaanTanaman_check','yes','keadaan tanaman','normal')" $readonlymode class = "$disabledmode" >$txtKeadaanTanaman</textarea>     
         <div id="txtKeadaanTanaman_check" style="color:red" ></div>     
         -->
         
          <textarea name="txtKawasanTerlibat" id="txtKawasanTerlibat" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtKawasanTerlibat_check','txtKawasanTerlibat_num','normal','no','lot kawasan terlibat');"  
         onKeyup="check_length(this,'4000','txtKawasanTerlibat_check','txtKawasanTerlibat_num','normal','no','lot kawasan terlibat');" 
         onKeydown="check_length(this,'4000','txtKawasanTerlibat_check','txtKawasanTerlibat_num','normal','no','lot kawasan terlibat');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKawasanTerlibat</textarea>
       #if($readmode == "edit")           
        <div><span id="txtKawasanTerlibat_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKawasanTerlibat_num" id="txtKawasanTerlibat_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtKawasanTerlibat_check" class="alert_msg" ></div>                  </td>
            </tr>
            
            
            <tr>
              <td width="1%" valign="top">&nbsp;</td>
              <td width="28%" valign="top">Ulasan Keseluruhan</td>
              <td width="1%" valign="top">:</td>
              <td width="70%"> 
              
            <!--            
               <textarea name="txtUlasan" id="txtUlasan" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtUlasan_check','yes','ulasan / pandangan','normal')"  onKeyUp="checking_validation(this,'txtUlasan_check','yes','ulasan / pandangan','normal')" $readonlymode class = "$disabledmode" >$txtUlasan</textarea>     
         <div id="txtUlasan_check" style="color:red" ></div>  
         
         -->
         
         <textarea name="txtUlasan" id="txtUlasan" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtUlasan_check','txtUlasan_num','normal','no','ulasan / pandangan');"  
         onKeyup="check_length(this,'4000','txtUlasan_check','txtUlasan_num','normal','no','ulasan / pandangan');" 
         onKeydown="check_length(this,'4000','txtUlasan_check','txtUlasan_num','normal','no','ulasan / pandangan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtUlasan</textarea>
       #if($readmode == "edit")           
        <div><span id="txtUlasan_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtUlasan_num" id="txtUlasan_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtUlasan_check" class="alert_msg" ></div>                     </td>
            </tr>
    </table>
    </fieldset>
    </td>
  </tr>
  <tr style="display:none">
    <td>
    <fieldset>
    <legend>Keadaan Rupa Bumi</legend>
    <table width="100%" >
     <tr>
              <td width="1%" valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
              <td width="28%" valign="top">Keseluruhan Lot</td>
              <td width="1%" valign="top">:</td>
              <td width="70%"> 
              <!--             
               <textarea name="txtKeseluruhanLot" id="txtKeseluruhanLot" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKeseluruhanLot_check','yes','maklumat keseluruhan lot','normal')"  onKeyUp="checking_validation(this,'txtKeseluruhanLot_check','yes','maklumat keseluruhan lot','normal')" $readonlymode class = "$disabledmode" >$txtKeseluruhanLot</textarea>     
         <div id="txtKeseluruhanLot_check" style="color:red" ></div>       
         -->
         
          <textarea name="txtKeseluruhanLot" id="txtKeseluruhanLot" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtKeseluruhanLot_check','txtKeseluruhanLot_num','normal','yes','maklumat keseluruhan lot');"  
         onKeyup="check_length(this,'4000','txtKeseluruhanLot_check','txtKeseluruhanLot_num','normal','yes','maklumat keseluruhan lot');" 
         onKeydown="check_length(this,'4000','txtKeseluruhanLot_check','txtKeseluruhanLot_num','normal','yes','maklumat keseluruhan lot');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeseluruhanLot</textarea>
       #if($readmode == "edit")           
        <div><span id="txtKeseluruhanLot_num" style="color:blue;" ></span><span> Baki Aksara</span>      
         </div>
         #else
         <input name="txtKeseluruhanLot_num" id="txtKeseluruhanLot_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtKeseluruhanLot_check" class="alert_msg" ></div>
         
                </td>
            </tr>
            <tr>
              <td width="1%" valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
              <td width="28%" valign="top">Kawasan Yang Terlibat Dengan Pengambilan</td>
              <td width="1%" valign="top">:</td>
              <td width="70%">    
              <!--          
               <textarea name="txtKawasan" id="txtKawasan" cols="80"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtKawasan_check','yes','maklumat kawasan yang terlibat dengan pengambilan','normal')"  onKeyUp="checking_validation(this,'txtKawasan_check','yes','maklumat kawasan yang terlibat dengan pengambilan','normal')" $readonlymode class = "$disabledmode" >$txtKawasan</textarea>     
         <div id="txtKawasan_check" style="color:red" ></div>    -->
         
          <textarea name="txtKawasan" id="txtKawasan" cols="80"   rows="6"         
         onBlur="check_length(this,'4000','txtKawasan_check','txtKawasan_num','normal','yes','maklumat kawasan yang terlibat dengan pengambilan');"  
         onKeyup="check_length(this,'4000','txtKawasan_check','txtKawasan_num','normal','yes','maklumat kawasan yang terlibat dengan pengambilan');" 
         onKeydown="check_length(this,'4000','txtKawasan_check','txtKawasan_num','normal','yes','maklumat kawasan yang terlibat dengan pengambilan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKawasan</textarea>
       #if($readmode == "edit")           
        <div><span id="txtKawasan_num" style="color:blue;" ></span><span> Baki Aksara</span>      
         </div>
         #else
         <input name="txtKawasan_num" id="txtKawasan_num" size="3" value="4000"  style=" display:none" > 
         #end
  		<div id="txtKawasan_check" class="alert_msg" ></div>
                   </td>
            </tr>
    </table>
    </fieldset>
    
    </td>
  </tr>
  <tr style="display:none">
    <td>
    <fieldset>
    <legend>Maklumat Bangunan
    </legend>
    <table width="100%">
     
     <tr>
    <td width="1%"></td>
    <td width="28%">Melibatkan Bangunan</td>
    <td width="1%">:</td>
    <td width="70%">
    
          
              #if($senarai_bangunan.size() > 0)
              #set($Bangunan = "ADA")            
              #elseif($senarai_bangunan.size() == 0)
              #set($Bangunan = "TIADA")                                        
              #else
              #set($Bangunan = "")
              #end
              <input name="Bangunan" type="text" class = "disabled" id="Bangunan" value="$Bangunan" size="10"  readonly >  
              <input type="hidden" name="sorBangunan" id="sorBangunan" value="$sorBangunan" >            
          </td>
  </tr>
  <tr>
       <td></td>
       <td>Bilangan Bangunan</td>
       <td>:</td>
       <td>
      
       
       <input name="txtBilBangunan" type="text" class = "disabled" id="txtBilBangunan" value="$senarai_bangunan.size()" size="10"  readonly >
       </td>
     </tr>
    </table>
    
    <br>
    <fieldset>
    <legend>Senarai Bangunan
    </legend>
     <table width="100%">
                    <tr class="table_header">
                      <td width="3%">Bil</td>
                      <td width="17%">No. Bangunan</td>
                      <td width="20%">Jenis Bangunan</td>
                      <td width="45%">Pemilik</td>
                      <td width="15%">Saiz Bangunan (mp)</td>
                          </tr>
                  
                   #if($senarai_bangunan.size()!=0)
             #foreach($list in $senarai_bangunan)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
                               <tr >  
                <td colspan="7"> 
              

 <table width="100%" id="$list.BIL"  class="$row"   style="visibility:collapse; display:none;"> 
 
 
 
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No. Bangunan</td>
    <td width="1%">:</td>
    <td width="70%">$list.NO_BANGUNAN</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Jenis Bangunan</td>
    <td width="1%">:</td>
    <td width="70%">
    
                     #set($JB1 = "") 
                     #if($list.JENIS_BANGUNAN == "1")
                      #set($JB1 = "KEKAL") 
                     #elseif($list.JENIS_BANGUNAN == "2")
                      #set($JB1 = "SEPARUH KEKAL") 
                     #elseif($list.JENIS_BANGUNAN == "3")
                      #set($JB1 = "SEMENTARA") 
                     #else
                      #set($JB1 = "") 
                     #end                     
                     $JB1    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Alamat Bangunan</td>
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
  <tr>
    <td>&nbsp;</td>
    <td>Saiz Bangunan</td>
    <td>:</td>
    <td>$list.SAIZ_BANGUNAN</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Nilai Bangunan</td>
    <td>:</td>
    <td>    
    #if($list.NILAI_BANGUNAN != "")
    #set($nilai = $Util.formatDecimal($list.NILAI_BANGUNAN))
    #else
    #set($nilai = "")
    #end   
    $nilai   
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Pemilik Bangunan</td>
    <td>:</td>
    <td>
    
    
        #set($count = 0) 
                      #foreach($list1 in $senarai_pihak_penting_bangunan)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK && $list1.ID_JENISPB == "30" && $list1.ID_BANGUNAN == $list.ID_BANGUNAN  )     
                      #set($count=$count + 1) 
                      #end
                      #end
                      
                      #set($count_total = 0) 
                      
                      #if($count == 1)
                      #foreach($list1 in $senarai_pihak_penting_bangunan)
                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK && $list1.ID_JENISPB == "30" && $list1.ID_BANGUNAN == $list.ID_BANGUNAN)
                      $list1.NAMA_PB
                      #end
                      #end
                      #elseif($count > 1)
                      
                      
                      
                      <select name=""  class="autoselect" >
                      #foreach($list1 in $senarai_pihak_penting_bangunan)                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK && $list1.ID_JENISPB == "30" && $list1.ID_BANGUNAN == $list.ID_BANGUNAN)  
                      #set( $ix = $velocityCount )
                      #if ( ($ix % 2) != 1 )
              		  #set( $rowx = "row2" )
         		      #else
               		  #set( $rowx = "row1" )
         		      #end                        
                      <option  >
                      $list1.NAMA_PB  
                      </option>
                      #end
                      #end
                      </select>
                      #else
                      
                      #end    </td>
  </tr>
</table>                </td> 
                </tr>
                
                
                    <tr id="$list.BIL_DUM" class="$row" >
                      <td  >$list.BIL</td>
                      <td id="$list.NO_PT" >
                         <a class="style1" id="hoverover" style="cursor:default; color:#0000FF" onClick="ShowPopup(this,$list.BIL);" title="Klik untuk maklumat lengkap">$list.NO_BANGUNAN</a>                      </td>
                      <td >
                     #set($JB = "") 
                     #if($list.JENIS_BANGUNAN == "1")
                      #set($JB = "KEKAL") 
                     #elseif($list.JENIS_BANGUNAN == "2")
                      #set($JB = "SEPARUH KEKAL") 
                     #elseif($list.JENIS_BANGUNAN == "3")
                      #set($JB = "SEMENTARA") 
                     #else
                      #set($JB = "") 
                     #end
                     
                     $JB
                     
                      
                      </td>
                      <td >
                    #set($count = 0) 
                      #foreach($list1 in $senarai_pihak_penting_bangunan)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK && $list1.ID_JENISPB == "30" && $list1.ID_BANGUNAN == $list.ID_BANGUNAN)     
                      #set($count=$count + 1) 
                      #end
                      #end
                      
                      #set($count_total = 0) 
                      
                      #if($count == 1)
                      #foreach($list1 in $senarai_pihak_penting_bangunan)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK && $list1.ID_JENISPB == "30" && $list1.ID_BANGUNAN == $list.ID_BANGUNAN )
                      $list1.NAMA_PB
                      #end
                      #end
                      #elseif($count > 1)
                      <select name=""   class="autoselect" >
                      #foreach($list1 in $senarai_pihak_penting_bangunan)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK && $list1.ID_JENISPB == "30" && $list1.ID_BANGUNAN == $list.ID_BANGUNAN)
                      
                      #set( $ix = $velocityCount )
                      #if ( ($ix % 2) != 1 )
              		  #set( $rowx = "row2" )
         		      #else
               		  #set( $rowx = "row1" )
         		      #end
                      <option   >
                      $list1.NAMA_PB
                      </option>
                      #end
                      #end
                      </select>
                      #else
                      
                      #end
                          </td>
                      <td >$list.SAIZ_BANGUNAN</td>
                            </tr>
              #end
              
              #else
              <tr>
              <td colspan="8">
              Tiada Rekod              </td>
              </tr>
              #end
                  </table>
    </fieldset>
    </fieldset>
    </td>
  </tr>
  <tr>
    <td>
    
    
    </td>
  </tr>
</table>

        
        
</div>
        <div class="TabbedPanelsContent"></div>
      </div>
    </div>
    
  
    </td>
    </tr>
    <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini()">
      </label>
      <label>
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan()">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal()">
      </label>
    #end  
     
       #if($id_pembatalan != "")
         #if($readmode == "view")
           #if($STATUS_LAPORAN != "SELESAI")
              <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Selesai Laporan Tanah"  onClick="selesai()">
      </label> 
      #end   
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  
      </label>   
      #end 
      #end    
        </div>
         <div class="TabbedPanelsContent">     
        </div>
        
              </td>
  </tr>
  
  #if($readmode == "edit")
  <tr>
    <td>#parse("app/ppt/PenarikanBalik_Alert.jsp")</td>
  </tr>
  #else
   <tr>
    <td></td>
  </tr>  
  #end
  
  
    </table>
    </fieldset>
    </td>
  </tr>
  
</table>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="laporantanah('$id_tanahumum','$user_name')"><font color="blue">Laporan Tanah</font></a></td>
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

  <script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});

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

check_length(document.${formName}.txtLokasiTanah,'4000','txtLokasiTanah_check','txtLokasiTanah_num','normal','yes','lokasi tanah');
check_length(document.${formName}.txtKeadaanLot,'4000','txtKeadaanLot_check','txtKeadaanLot_num','normal','no','keadaan lot');
check_length(document.${formName}.txtJenisTanaman,'4000','txtJenisTanaman_check','txtJenisTanaman_num','normal','no','jenis tanaman');
check_length(document.${formName}.txtBerhampiran,'4000','txtBerhampiran_check','txtBerhampiran_num','normal','no','maklumat berhampiran');
check_length(document.${formName}.txtKeadaanTanaman,'4000','txtKeadaanTanaman_check','txtKeadaanTanaman_num','normal','no','keadaan tanaman');
check_length(document.${formName}.txtUlasan,'4000','txtUlasan_check','txtUlasan_num','normal','no','ulasan / pandangan');
check_length(document.${formName}.txtKeseluruhanLot,'4000','txtKeseluruhanLot_check','txtKeseluruhanLot_num','normal','no','maklumat keseluruhan lot');
check_length(document.${formName}.txtKawasan,'4000','txtKawasan_check','txtKawasan_num','normal','no','maklumat kawasan yang terlibat dengan pengambilan');
check_length(document.${formName}.txtKawasanTerlibat,'4000','txtKawasanTerlibat_check','txtKawasanTerlibat_num','normal','no','lot kawasan terlibat');
/*
checking_validation(document.${formName}.txtLokasiTanah,'txtLokasiTanah_check','yes','lokasi tanah','normal')
checking_validation(document.${formName}.txtKeadaanLot,'txtKeadaanLot_check','yes','keadaan lot','normal')
checking_validation(document.${formName}.txtJenisTanaman,'txtJenisTanaman_check','yes','jenis tanaman','normal')
checking_validation(document.${formName}.txtBerhampiran,'txtBerhampiran_check','yes','maklumat berhampiran','normal')
checking_validation(document.${formName}.txtKeadaanTanaman,'txtKeadaanTanaman_check','yes','keadaan tanaman','normal')
checking_validation(document.${formName}.txtUlasan,'txtUlasan_check','yes','ulasan / pandangan','normal')
checking_validation(document.${formName}.txtKawasan,'txtKawasan_check','yes','maklumat kawasan yang terlibat dengan pengambilan','normal')
checking_validation(document.${formName}.txtKeseluruhanLot,'txtKeseluruhanLot_check','yes','maklumat keseluruhan lot','normal')
*/
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
	   {/*	   
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
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+"");	
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
	 $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+" dengan betul");	
	 
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
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");	
		
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
	   {/*
	   document.${formName}.alert_message.value  = "Sila pilih "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila pilih "+value_field+"");	
			
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
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		   */
		    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan dokumen terlebih dahulu");	
		
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
	   
	   
	   
	
}




function PembatalanAmbilTanahLotUnit()
{
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahLotUnit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "lot_dibatal";
	document.${formName}.point.value = "lot_dibatal";
	document.${formName}.submit();

}



function LaporanTanah(id_hakmilik,id_pembatalan)
{
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Maklumat_Am";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
}


function PerihalTanah(id_hakmilik,id_pembatalan)
{
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Perihal_Tanah";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
}




function simpan()
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
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Perihal_Tanah";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();
	}
	}
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Perihal_Tanah";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();
	}
}


function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Perihal_Tanah";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();

	}
}

function kemaskini()
{
    document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Perihal_Tanah";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "txtLokasiTanah";
	document.${formName}.submit();


}


function ShowPopup(hoveritem,tab)
{
//alert("hp.style.display :"+hp.style.display+";hp.style.visibility :"+hp.style.visibility);
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
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
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




	
	function selesai()
{
   document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Perihal_Tanah";
	document.${formName}.subminor_command.value = "UpdateSuburusan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();
}


function laporantanah(id_tanah,nama_pegawai)
{

	


var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_tanah="+id_tanah+"&report=laporan_tanah&id_permohonan="+document.${formName}.id_permohonan.value;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}

function open_new_window(jenis_popup) 
{
var width  = 300;
 var height = 200;
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=front';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
//new_window = open("","hoverwindow",params);
new_window = open("","title",params);

new_window.document.open();

new_window.document.write("<html><title>JavaScript New Window</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");
if(jenis_popup == "1")
{
new_window.document.write("Sila masukkan 'Tiada' jika tiada maklumat.");
}/*
if(jenis_popup == "2")
{
new_window.document.write("Sila semak untuk memilih penarikan balik bagi lot ini,");
new_window.document.write("dan sila masukkan jumlah luas lust lot yang hendak ");
new_window.document.write("ditarik balik.");

}

if(jenis_popup == "3")
{
new_window.document.write("Sila pilih jenis luas yang lain jika unit luas yang dikehendaki adalah tidak sama dengan unit luas yang ditetapkan.");
new_window.document.write("Seterusnya sila masukkan luas lot awal, pengiraan luas pertukaran akan berlaku secara automatik selepas kotak terakhir selesai diisi.");


}*/

new_window.document.write("<br>");
new_window.document.write("</body></html>");
new_window.document.close(); 







}

function LaporanKerosakan(id_hakmilik,id_pembatalan)
{
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Laporan_Kerosakan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
}



function close_window() 
{
new_window.close();
}
  </script>
