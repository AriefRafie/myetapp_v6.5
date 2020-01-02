
#set($id_mmk = "")
#set($STATUS_SEMAKAN = "")


#set($jumlah_tanah = "")
#if($!maklumat_hakmilik_mmk_lot.size()>1)
#set($jumlah_tanah = $!maklumat_hakmilik_mmk_lot.size()+ " keping")
#elseif($!maklumat_hakmilik_mmk_lot.size()==1)
#set($jumlah_tanah = "sekeping")
#else

#end


#set($jumlah_tanahC = "")
#if($!maklumat_hakmilik_mmk_lot.size()>1)
#set($jumlah_tanahC = $!maklumat_hakmilik_mmk_lot.size()+ " Keping")
#elseif($!maklumat_hakmilik_mmk_lot.size()==1)
#set($jumlah_tanahC = "Sekeping")
#else

#end






<!--
#set($txtTujuan = "Tujuan  kertas ringkasan ini ialah untuk mendapat kelulusan Y.A.B Dato Menteri Besar bagi menarik balik pengambilan tanah dibawah Seksyen 35 Akta Pengambilan Tanah 1960 bagi lot "+$no_lot_mmk+" di "+$nama_mukim_mukim+", Daerah "+ $nama_daerah1 +" seluas "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar) bagi "+ $tujuan +" di "+ $nama_daerah1 +", Selangor Darul Ehsan yang sebelum ini telah diwartakan di bawah Seksyen 8 Akta yang sama dan telah diisytiharkan melalui Warta Kerajaan Negeri Selangor "+ $no_warta +"  bertarikh "+ $tarikh_warta +". ")-->


#set($txtTujuan = "Kertas kerja ini disediakan adalah untuk melaporkan dan selanjutnya memohon pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Kedah Darul Aman berhubung dengan permohonan untuk menarik balik perisytiharan pengambilan "+$jumlah_tanah+" tanah bermilik di bawah Seksyen 35 Akta Pengambilan Tanah 1960 (Akta 486) bagi maksud "+$!tujuan+" di "+ $nama_daerah1 +", Kedah Darul Aman.")


#set($txtTajuk = "Kertas Kerja Berhubung Dengan Permohonan Untuk Menarik Balik Perisytiharan Pengambilan "+$jumlah_tanahC+" Tanah Bermilik Di Bawah Seksyen 35 Akta Pengambilan Tanah 1960 (Akta 486) bagi maksud "+$!tujuan+" di "+ $nama_daerah1 +", Kedah Darul Aman.")


#set($txtPerihalTanah = "3.1   Butir-butir tanah <br/><br/>
Butir-butir tanah yang terlibat adalah...... 
<br/><br/>
3.2   Laporan Tanah
<br/><br/>
Tanah-tanah terlibat terletak ........"
)

#set($txtSebab = "")
#set($txtImplikasi = "")
#set($txtUlasan = "")
#set($txtSyor = "Pentadbir Tanah "+ $nama_daerah1 +"  mengesyorkan:- <br/><br/>
3.1	Pengambilan Tanah bagi lot "+$no_lot_mmk+" di Mukim "+$nama_mukim_mukim+", Daerah "+ $nama_daerah1 +" seluas "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar)  yang telah dibuat perisytiharan di bawah Seksyen 8 Akta Pengambilan Tanah 1960 melalui Warta Kerajaan Negeri Selangor "+ $no_warta +"  bertarikh "+ $tarikh_warta +" ditarik balik di bawah Seksyen 35 Akta yang sama.
<br/><br/>
3.2	Penarikan balik pengambilan tanah ini hendaklah disiarkan dalam Warta Kerajaan Negeri Selangor menurut seksyen 35(1A) Akta Pengambilan Tanah.
")


#set($txtSyor1 = "Pengambilan Tanah bagi lot "+$no_lot_mmk+" di "+$nama_mukim_mukim+", Daerah "+ $nama_daerah1 +" seluas "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar)  yang telah dibuat perisytiharan di bawah Seksyen 8 Akta Pengambilan Tanah 1960 melalui Warta Kerajaan Negeri Selangor "+ $no_warta +"  bertarikh "+ $tarikh_warta +" ditarik balik di bawah Seksyen 35 Akta yang sama.")

#set($txtSyor2 = "Penarikan balik pengambilan tanah ini hendaklah disiarkan dalam Warta Kerajaan Negeri Selangor menurut seksyen 35(1A) Akta Pengambilan Tanah.")


#set($peruntukan = "Peruntukan bagi membiayai pengambilan tanah untuk projek ini telah disediakan oleh "+$nama_kementerian1+". ")



#set($txtAsasPertimbangan = "")
#set($txtNilaiAtasTanah = "Adalah disyorkan supaya nilaian ke atas tanah yang akan diambil ini 
dirujuk kepada penilaian kompeten.")
#set($txtSebabPenarikan= "Sebab-sebab penarikan balik adalah "+$!maklumat_penarikanmmk)
#set($txtLatarbelakang= "Pentadbir Tanah "+ $nama_daerah1 +" telah menerima arahan dari Pihak Berkuasa Negeri supaya menarik balik perisytiharan Warta Kerajaan Negeri Selangor "+ $no_warta +"  bertarikh "+ $tarikh_warta +" di bawah Seksyen 35, Akta Pengambilan Tanah 1960 ke atas lot "+$no_lot_mmk+" di "+$nama_mukim_mukim+", Daerah "+ $nama_daerah1 +" seluas "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar) bagi maksud seperti di perenggan (1). Tanah yang terlibat adalah seperti bertanda jingga di atas pelan bil ( ) dalam Fail No. "+$no_ptg+".")
#set($txtKesimpulan= "")
#set($txtUlasanPengarah= "")

#set($txtKeputusan= "")
#set($txtPerihalPohon= "")
#set($txtNamaTuan= "")
#set($txtPerakuanPentadbir= "")
#set($txtPeruntukan= "Wang peruntukan bagi pembiayaan pengambilan tanah ini telah disediakan secukupnya oleh pihak "+$nama_kementerian1+".")


 <!-- id_pembatalan = id_penarikanbalik -->



#foreach($view in $maklumat_penyediaan)
#set($id_mmk = $view.ID_MMK)
#set($id_pembatalan = $view.ID_PEMBATALAN)
#set($STATUS_SEMAKAN = $view.STATUS_SEMAKAN)
#set($txtTujuan = $view.TUJUAN)
#set($txtLatarbelakang = $view.LATARBELAKANG)
#set($txtImplikasi = $view.IMPLIKASI)
#set($txtUlasan = $view.ULASAN)
#set($txtSyor = $view.SYOR)
#set($txtAsasPertimbangan = $view.ASAS_PERTIMBANGAN)
#set($txtNilaiAtasTanah = $view.NILAI_ATAS_TANAH)
#set($txtSebabPenarikan= $view.SEBAB_PENARIKAN)
#set($txtPerihalTanah= $view.PERIHAL_TANAH)
#set($txtKesimpulan= $view.KESIMPULAN)
#set($txtUlasanPengarah= $view.ULASAN_PENGARAH)
#set($txtTajuk= $view.TAJUK)
#set($flag_mmk= $view.FLAG_MMK)

#set($txtKeputusan= $view.KEPUTUSAN)
#set($txtPerihalPohon= $view.PERIHAL_POHON)
#set($txtNamaTuan= $view.NAMA_TUAN_TANAH)
#set($txtPerakuanPentadbir= $view.PERAKUAN_PENTADBIR_TNH)

#set($txtPeruntukan= $view.PENGESAHAN_PERUNTUKAN)



#end


#set($nama_pengarah= "HJ. CHE ROSLAN B. CHE DAUD")
#set($nama_menteri= "Y.A.B. TAN SRI DATO` SERI ABD KHALID BIN IBRAHIM")



<table width="100%">

<tr   >
              <td width="1%"  valign="top">&nbsp;</td>
    <td width="29%" valign="top"> <strong>TAJUK </strong></td>
    <td width="1%" valign="top">:</td>
     
    <td width="3%" valign="top">    </td>
<td width="57%" valign="top">
         
          
        
<textarea   name="txtTajuk" id="txtTajuk" cols="80"   rows="9"        
         onBlur="check_length(this,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan');"  
         onKeyup="check_length(this,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan');" 
         onKeydown="check_length(this,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtTajuk</textarea>       
       #if($readmode == "edit")           
       <div><span id="txtTajuk_num" style="color:blue;" ></span><span> Baki Aksara</span>       </div>
         #else
         <input name="txtTajuk_num" id="txtTajuk_num" size="3" value="30000"  style=" display:none" > 
         #end
  <div id="txtTajuk_check" class="alert_msg" ></div>    </td>
</tr>

            <tr  >
              <td width="1%"  valign="top"><strong>1.</strong></td>
              <td width="29%" valign="top"> <strong>TUJUAN</strong></td>
              <td width="1%" valign="top">:</td>
    
        <td colspan="3" valign="top">
       
    #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "TUJUAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="TUJUAN_MAIN_temp1"  id="TUJUAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end             
   
            
   <span id="TUJUAN_MAIN"></span>           
   <div id="TUJUAN_MAIN_temp"></div>   </td>
  </tr>
             
             <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
  </tr>
  
         
         <tr>
              <td valign="top"><strong>2.</strong></td>
              <td valign="top"><strong>LATAR BELAKANG</strong></td>
              <td valign="top">:</td>
    
               
        <td width="3%"><strong>2.1</strong></td>
        <td width="57%"><strong>Permohonan Pengambilan Tanah </strong></td>
        <td width="9%">&nbsp;</td>
  </tr>
            
               <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td>  </td>
        <td colspan="2"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "MOHON_AMBIL_TANAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="MOHON_AMBIL_TANAH_MAIN_temp1"  id="MOHON_AMBIL_TANAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="MOHON_AMBIL_TANAH_MAIN"></span>           
   <div id="MOHON_AMBIL_TANAH_MAIN_temp"></div></td>
  </tr>
  
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
  </tr>
  
  <tr>
              <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">:</td>
    
               
        <td width="3%"><strong>2.2</strong></td>
    <td width="57%"><strong>Peruntukan</strong></td>
    <td width="9%">&nbsp;</td>
  </tr>
            
               <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td>  </td>
        <td colspan="2"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "PERUNTUKAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="PERUNTUKAN_MAIN_temp1"  id="PERUNTUKAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="PERUNTUKAN_MAIN"></span>           
   <div id="PERUNTUKAN_MAIN_temp"></div></td>
  </tr>
  
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
</tr>
       
       <tr>
              <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">:</td>
    
               
        <td width="3%"><strong>2.3</strong></td>
        <td width="57%"><strong>Perisytiharan Pengambilan</strong></td>
        <td width="9%">&nbsp;</td>
  </tr>
            
               <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td>  </td>
        <td colspan="2"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "ISTIHAR_PENGAMBILAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="ISTIHAR_PENGAMBILAN_MAIN_temp1"  id="ISTIHAR_PENGAMBILAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="ISTIHAR_PENGAMBILAN_MAIN"></span>           
   <div id="ISTIHAR_PENGAMBILAN_MAIN_temp"></div></td>
  </tr>
  
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
</tr>

<tr>
              <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">:</td>
    
               
        <td width="3%"><strong>2.4</strong></td>
    <td width="57%"><strong>Wasilan Atas Hakmilik</strong></td>
    <td width="9%">&nbsp;</td>
  </tr>
            
               <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td>  </td>
        <td colspan="2"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "WASILAN_HAKMILIK" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="WASILAN_HAKMILIK_MAIN_temp1"  id="WASILAN_HAKMILIK_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="WASILAN_HAKMILIK_MAIN"></span>           
   <div id="WASILAN_HAKMILIK_MAIN_temp"></div></td>
  </tr>
  
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
</tr>


<tr>
              <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">:</td>
    
               
        <td width="3%"><strong>2.5</strong></td>
    <td width="57%"><strong>Lot Ketinggalan</strong></td>
    <td width="9%">&nbsp;</td>
  </tr>
            
               <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td>  </td>
        <td colspan="2"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "LOT_TINGGAL" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="LOT_TINGGAL_MAIN_temp1"  id="LOT_TINGGAL_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="LOT_TINGGAL_MAIN"></span>           
   <div id="LOT_TINGGAL_MAIN_temp"></div></td>
  </tr>
  
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
</tr>

<tr>
              <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">:</td>
    
               
        <td width="3%"><strong>2.6</strong></td>
    <td width="57%"><strong>Penyampaian Award (Borang H)</strong></td>
    <td width="9%">&nbsp;</td>
  </tr>
            
               <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td>  </td>
        <td colspan="2"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "PENYAMPAIAN_AWARD" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="PENYAMPAIAN_AWARD_MAIN_temp1"  id="PENYAMPAIAN_AWARD_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="PENYAMPAIAN_AWARD_MAIN"></span>           
   <div id="PENYAMPAIAN_AWARD_MAIN_temp"></div></td>
  </tr>
  
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
</tr>
            
            
              <tr>
              <td valign="top"><strong>3.</strong></td>
              <td valign="top"><strong>SEBAB-SEBAB PENARIKAN BALIK</strong></td>
              <td valign="top">:</td>
               
        <td colspan="3">  #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "SEBABPENARIKAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="SEBABPENARIKAN_MAIN_temp1"  id="SEBABPENARIKAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="SEBABPENARIKAN_MAIN"></span>           
   <div id="SEBABPENARIKAN_MAIN_temp"></div>   </td>
</tr>
              <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
</tr>
<tr>
              <td valign="top"><strong>4.</strong></td>
              <td valign="top"><strong>PENDAPAT DAN SOKONGAN PENGARAH DAN GALIAN</strong></td>
              <td valign="top">:</td>
    
               
        <td width="3%" valign="top"><strong>4.1</strong></td>
    <td width="57%" valign="top"><strong>Sokongan Pengarah Tanah Dan Galian Negeri</strong></td>
    <td width="9%">&nbsp;</td>
  </tr>
            
               <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td>  </td>
        <td colspan="2"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "SOKONGAN_PENGARAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="SOKONGAN_PENGARAH_MAIN_temp1"  id="SOKONGAN_PENGARAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="SOKONGAN_PENGARAH_MAIN"></span>           
   <div id="SOKONGAN_PENGARAH_MAIN_temp"></div></td>
  </tr>
  
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
  </tr>
  
  
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top">&nbsp;</td>
              <td valign="top">:</td>
    
               
        <td width="3%" valign="top"><strong>4.2</strong></td>
        <td width="57%" valign="top"><strong>Permohonan Menarik Balik Perisytiharan Pengambilan</strong></td>
        <td width="9%">&nbsp;</td>
  </tr>
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td>  </td>
        <td ><div align="justify">Bersama-sama dengan kertas kerja ini juga disertakan Borang Menarik Balik Pengambilan Tanah di bawah Seksyen 35(1) Akta Pengambilan Tanah 1960(Akta 486) yang memberikan butir-buitr atas tanah-tanah tersebut agar Majlis Mesyuarat Kerajaan Negeri Kedah Darul Aman dapat memberikan pertimbangan dan membenarkan:-</div></td>
        <td valign="top"></td>
               
  </tr>
            
               <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td>  </td>
        <td colspan="2">#if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1"  id="MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN"></span>           
   <div id="MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp"></div>
   </td>
  </tr>
  
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
  </tr>
  
  <tr>
              <td valign="top"><strong>5.</strong></td>
              <td valign="top"><strong>PERAKUAN</strong></td>
              <td valign="top">:</td>
               
        <td colspan="2">Inilah dipohon pertimbangan dan perakuan Majlis Mesyuarat Kerajaan bagi pekara - pekara tersebut :-</td>
         <td ></td>
</tr>
              <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3">#if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "PERAKUAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="PERAKUAN_MAIN_temp1"  id="PERAKUAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="PERAKUAN_MAIN"></span>           
   <div id="PERAKUAN_MAIN_temp"></div>  </td>
</tr>
<tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
</tr>
  
  <tr  >
              <td width="1%"  valign="top"><strong>6.</strong></td>
              <td width="29%" valign="top"><strong>PENUTUP</strong></td>
              <td width="1%" valign="top">:</td>
    
        <td colspan="3" valign="top">
        
         #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "PENUTUP" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="PENUTUP_MAIN_temp1"  id="PENUTUP_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="PENUTUP_MAIN"></span>           
   <div id="PENUTUP_MAIN_temp"></div>
       
     </td>
  </tr>
             
             <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td colspan="3"></td>
  </tr>
  
</table>
          
          
         		
		
		
		
		
	
		
<input name="txtImplikasi" id="txtImplikasi" value="" type="hidden" />
          <input name="txtAsasPertimbangan" id="txtAsasPertimbangan" value="" type="hidden" />          
          <input name="txtKesimpulan" id="txtKesimpulan" value="" type="hidden" />        
          <input name="txtUlasanPengarah" id="txtUlasanPengarah" value="" type="hidden" />
          <input name="txtKeputusan" id="txtKeputusan" value="" type="hidden" />
          <input name="txtPerihalPohon" id="txtPerihalPohon" value="" type="hidden" />
          <input name="txtNamaTuan" id="txtNamaTuan" value="" type="hidden" />
          <input name="txtPerakuanPentadbir" id="txtPerakuanPentadbir" value="" type="hidden" />
  
          
<script>

window.onload = submitForm,textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_SEBABPENARIKAN_MAIN('tambahtolak','umum',''),textarea_PENUTUP_MAIN('tambahtolak','umum',''),textarea_PERAKUAN_MAIN('tambahtolak','umum',''),textarea_MOHON_AMBIL_TANAH_MAIN('tambahtolak','umum',''),check_length(document.${formName}.txtTajuk,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan'),textarea_PERUNTUKAN_MAIN('tambahtolak','umum',''),textarea_ISTIHAR_PENGAMBILAN_MAIN('tambahtolak','umum',''),textarea_WASILAN_HAKMILIK_MAIN('tambahtolak','umum',''),textarea_LOT_TINGGAL_MAIN('tambahtolak','umum',''),textarea_PENYAMPAIAN_AWARD_MAIN('tambahtolak','umum',''),textarea_SOKONGAN_PENGARAH_MAIN('tambahtolak','umum',''),textarea_MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN('tambahtolak','umum','');

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



if('$flag_mmk' == "1" && ('$portal_role' == "(PPT)Pengarah" || '$portal_role' == "(PPT)PengarahUnit" || '$portal_role' == "(PPT)PenolongPengarah" || '$portal_role' == "(PPT)PenolongPengarahUnit" || '$portal_role' == "(PPT)KetuaPenolongPengarah" || '$portal_role' == "(PPT)KetuaPenolongPengarahUnit"))
{
//document.${formName}.semakan_mmk.style.display = "block";
document.getElementById('semakan_mmk').style.display = "block";
}
else if('$flag_mmk' == "2" && ('$portal_role' == "(PPT)Pengarah" || '$portal_role' == "(PPT)PengarahUnit" || '$portal_role' == "(PPT)PenolongPengarah" || '$portal_role' == "(PPT)PenolongPengarahUnit" || '$portal_role' == "(PPT)KetuaPenolongPengarah" || '$portal_role' == "(PPT)KetuaPenolongPengarahUnit"))
{
document.getElementById('semakan_mmk').style.display = "block";
document.getElementById('keputusan_mmk').style.display = "block";
//document.${formName}.semakan_mmk.style.display = "block";
//document.${formName}.keputusan_mmk.style.display = "block";
}
else if('$flag_mmk' == "2" && ('$portal_role' != "(PPT)Pengarah" && '$portal_role' != "(PPT)PengarahUnit" && '$portal_role' != "(PPT)PenolongPengarah" && '$portal_role' != "(PPT)PenolongPengarahUnit" && '$portal_role' != "(PPT)KetuaPenolongPengarah" && '$portal_role' != "(PPT)KetuaPenolongPengarahUnit"))
{
document.getElementById('keputusan_mmk').style.display = "block";
//document.${formName}.keputusan_mmk.style.display = "block";
}
else
{
document.getElementById('semakan_mmk').style.display = "none";
document.getElementById('keputusan_mmk').style.display = "none";
}







}



function textarea_TUJUAN_MAIN(tambahtolak,jenis,index_tolak)
{

var TUJUAN_MAIN_temp1_length=0;

if(document.${formName}.TUJUAN_MAIN_temp1 != null)
{

if(document.${formName}.TUJUAN_MAIN_temp1.length>0)
{
TUJUAN_MAIN_temp1_length = document.${formName}.TUJUAN_MAIN_temp1.length;
}
else
{
TUJUAN_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanTUJUAN_MAIN!=null)
{

if(document.${formName}.txtUlasanTUJUAN_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanTUJUAN_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='TUJUAN_MAIN_tempX1' name='TUJUAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanTUJUAN_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='TUJUAN_MAIN_tempX1' name='TUJUAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanTUJUAN_MAIN.value+"'></td></tr>";

}
}




$jquery("#TUJUAN_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(TUJUAN_MAIN_temp1_length>0)
{
ttTUJUAN_MAIN = TUJUAN_MAIN_temp1_length;
}
else
{
ttTUJUAN_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttTUJUAN_MAIN = ttTUJUAN_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttTUJUAN_MAIN = ttTUJUAN_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttTUJUAN_MAIN; i++)
  {	
  if(ttTUJUAN_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[1].value;
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[0].value;
	
    } 	
	}		
   
    if(jenis == "umum")
    {
	temp_value = '$txtTujuan';
	}	
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>   </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanTUJUAN_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanTUJUAN_MAIN_num\" id=\"txtUlasanTUJUAN_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanTUJUAN_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
		 
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat TUJUAN_MAIN'> "+
	      " </span>"; 	
	     if(ttTUJUAN_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_TUJUAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_TUJUAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat TUJUAN_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttTUJUAN_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1.value;
	
	}	
	else if(ttTUJUAN_MAIN>2 && i!=(ttTUJUAN_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i].value;
	
	}	
	else if(ttTUJUAN_MAIN>1 && i!=(ttTUJUAN_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[parseInt(index_tolak)+1].value;
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i].value;
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i+1].value;	
	
   }	

	}
	else if(ttTUJUAN_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[0].value;
	
   }
   }		
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 1."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanTUJUAN_MAIN_num"+i+"\" id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanTUJUAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttTUJUAN_MAIN>1 && ttTUJUAN_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat TUJUAN_MAIN'> "+
	" </span>"; 
	}
	if(ttTUJUAN_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_TUJUAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_TUJUAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat TUJUAN_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	
	$jquery("#TUJUAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length > 1 )
	{
	for (t = 0; t < TUJUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanTUJUAN_MAIN[t].value = document.${formName}.TUJUAN_MAIN_temp1[t].value;
	
    }
	}
	else if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length < 1 )
	{
	for (t = 0; t < TUJUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanTUJUAN_MAIN.value = document.${formName}.TUJUAN_MAIN_temp1[0].value;
	}
	}
	else if(TUJUAN_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanTUJUAN_MAIN.value = document.${formName}.TUJUAN_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(TUJUAN_MAIN_temp1_length > 1)
	{
	for (t = 0; t < TUJUAN_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.TUJUAN_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.TUJUAN_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(TUJUAN_MAIN_temp1_length == 1)
	{	
	 document.${formName}.TUJUAN_MAIN_temp1.value = "";			
	 var element = document.${formName}.TUJUAN_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttTUJUAN_MAIN; i++)
    {		
    if(ttTUJUAN_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanTUJUAN_MAIN,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanTUJUAN_MAIN[i],'30000','txtUlasanTUJUAN_MAIN_check'+i,'txtUlasanTUJUAN_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}



function textarea_LATARBELAKANG_MAIN(tambahtolak,jenis,index_tolak)
{

var LATARBELAKANG_MAIN_temp1_length=0;

if(document.${formName}.LATARBELAKANG_MAIN_temp1 != null)
{

if(document.${formName}.LATARBELAKANG_MAIN_temp1.length>0)
{
LATARBELAKANG_MAIN_temp1_length = document.${formName}.LATARBELAKANG_MAIN_temp1.length;
}
else
{
LATARBELAKANG_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanLATARBELAKANG_MAIN!=null)
{

if(document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanLATARBELAKANG_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG_MAIN_tempX1' name='LATARBELAKANG_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG_MAIN_tempX1' name='LATARBELAKANG_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG_MAIN.value+"'></td></tr>";

}
}




$jquery("#LATARBELAKANG_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(LATARBELAKANG_MAIN_temp1_length>0)
{
ttLATARBELAKANG_MAIN = LATARBELAKANG_MAIN_temp1_length;
}
else
{
ttLATARBELAKANG_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttLATARBELAKANG_MAIN = ttLATARBELAKANG_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttLATARBELAKANG_MAIN = ttLATARBELAKANG_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttLATARBELAKANG_MAIN; i++)
  {	
  if(ttLATARBELAKANG_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[0].value
	
    } 	
	}		
   
     if(jenis == "umum")
    {
	temp_value = '$txtLatarbelakang';
	}	
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'> </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanLATARBELAKANG_MAIN_num\" id=\"txtUlasanLATARBELAKANG_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanLATARBELAKANG_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat LATARBELAKANG_MAIN'> "+
	      " </span>"; 	
	     if(ttLATARBELAKANG_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat LATARBELAKANG_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttLATARBELAKANG_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1.value
	
	}	
	else if(ttLATARBELAKANG_MAIN>2 && i!=(ttLATARBELAKANG_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i].value
	
	}	
	else if(ttLATARBELAKANG_MAIN>1 && i!=(ttLATARBELAKANG_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttLATARBELAKANG_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[0].value;
	
   }
   }	
   
   
 
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 2."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanLATARBELAKANG_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttLATARBELAKANG_MAIN>1 && ttLATARBELAKANG_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat LATARBELAKANG_MAIN'> "+
	" </span>"; 
	}
	if(ttLATARBELAKANG_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat LATARBELAKANG_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#LATARBELAKANG_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1 )
	{
	for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanLATARBELAKANG_MAIN[t].value = document.${formName}.LATARBELAKANG_MAIN_temp1[t].value;
	
    }
	}
	else if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length < 1 )
	{
	for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanLATARBELAKANG_MAIN.value = document.${formName}.LATARBELAKANG_MAIN_temp1[0].value;
	}
	}
	else if(LATARBELAKANG_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanLATARBELAKANG_MAIN.value = document.${formName}.LATARBELAKANG_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(LATARBELAKANG_MAIN_temp1_length > 1)
	{
	for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.LATARBELAKANG_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.LATARBELAKANG_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(LATARBELAKANG_MAIN_temp1_length == 1)
	{	
	 document.${formName}.LATARBELAKANG_MAIN_temp1.value = "";			
	 var element = document.${formName}.LATARBELAKANG_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttLATARBELAKANG_MAIN; i++)
    {		
    if(ttLATARBELAKANG_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanLATARBELAKANG_MAIN,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanLATARBELAKANG_MAIN[i],'30000','txtUlasanLATARBELAKANG_MAIN_check'+i,'txtUlasanLATARBELAKANG_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}


function textarea_MOHON_AMBIL_TANAH_MAIN(tambahtolak,jenis,index_tolak)
{

var MOHON_AMBIL_TANAH_MAIN_temp1_length=0;

if(document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1 != null)
{

if(document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1.length>0)
{
MOHON_AMBIL_TANAH_MAIN_temp1_length = document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1.length;
}
else
{
MOHON_AMBIL_TANAH_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN!=null)
{

if(document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='MOHON_AMBIL_TANAH_MAIN_tempX1' name='MOHON_AMBIL_TANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='MOHON_AMBIL_TANAH_MAIN_tempX1' name='MOHON_AMBIL_TANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN.value+"'></td></tr>";

}
}




$jquery("#MOHON_AMBIL_TANAH_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(MOHON_AMBIL_TANAH_MAIN_temp1_length>0)
{
ttMOHON_AMBIL_TANAH_MAIN = MOHON_AMBIL_TANAH_MAIN_temp1_length;
}
else
{
ttMOHON_AMBIL_TANAH_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttMOHON_AMBIL_TANAH_MAIN = ttMOHON_AMBIL_TANAH_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttMOHON_AMBIL_TANAH_MAIN = ttMOHON_AMBIL_TANAH_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttMOHON_AMBIL_TANAH_MAIN; i++)
  {	
  if(ttMOHON_AMBIL_TANAH_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_tempX1[0].value
	
    } 	
	}		
   
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>2.1.1  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanMOHON_AMBIL_TANAH_MAIN\" id=\"txtUlasanMOHON_AMBIL_TANAH_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanMOHON_AMBIL_TANAH_MAIN_check','txtUlasanMOHON_AMBIL_TANAH_MAIN_num','normal','no','ulasan kos-kos akibat MOHON_AMBIL_TANAH_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanMOHON_AMBIL_TANAH_MAIN_check','txtUlasanMOHON_AMBIL_TANAH_MAIN_num','normal','no','ulasan kos-kos akibat MOHON_AMBIL_TANAH_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanMOHON_AMBIL_TANAH_MAIN_check','txtUlasanMOHON_AMBIL_TANAH_MAIN_num','normal','no','ulasan kos-kos akibat MOHON_AMBIL_TANAH_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanMOHON_AMBIL_TANAH_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanMOHON_AMBIL_TANAH_MAIN_num\" id=\"txtUlasanMOHON_AMBIL_TANAH_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanMOHON_AMBIL_TANAH_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_MOHON_AMBIL_TANAH_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_MOHON_AMBIL_TANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat MOHON_AMBIL_TANAH_MAIN'> "+
	      " </span>"; 	
	     if(ttMOHON_AMBIL_TANAH_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_MOHON_AMBIL_TANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_MOHON_AMBIL_TANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat MOHON_AMBIL_TANAH_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttMOHON_AMBIL_TANAH_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_tempX1.value
	
	}	
	else if(ttMOHON_AMBIL_TANAH_MAIN>2 && i!=(ttMOHON_AMBIL_TANAH_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_tempX1[i].value
	
	}	
	else if(ttMOHON_AMBIL_TANAH_MAIN>1 && i!=(ttMOHON_AMBIL_TANAH_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttMOHON_AMBIL_TANAH_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_tempX1[0].value;
	
   }
   }		
	if(jenis == "umum")
    {
	/*if(i==0)
	{
	 temp_value = '$txtSyor1';
	}
	if(i==1)
	{
	 temp_value = '$txtSyor2';
	}*/
	}
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 2.1."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanMOHON_AMBIL_TANAH_MAIN\" id=\"txtUlasanMOHON_AMBIL_TANAH_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanMOHON_AMBIL_TANAH_MAIN_check"+i+"','txtUlasanMOHON_AMBIL_TANAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat MOHON_AMBIL_TANAH_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanMOHON_AMBIL_TANAH_MAIN_check"+i+"','txtUlasanMOHON_AMBIL_TANAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat MOHON_AMBIL_TANAH_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanMOHON_AMBIL_TANAH_MAIN_check"+i+"','txtUlasanMOHON_AMBIL_TANAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat MOHON_AMBIL_TANAH_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanMOHON_AMBIL_TANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanMOHON_AMBIL_TANAH_MAIN_num"+i+"\" id=\"txtUlasanMOHON_AMBIL_TANAH_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanMOHON_AMBIL_TANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttMOHON_AMBIL_TANAH_MAIN>1 && ttMOHON_AMBIL_TANAH_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_MOHON_AMBIL_TANAH_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_MOHON_AMBIL_TANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat MOHON_AMBIL_TANAH_MAIN'> "+
	" </span>"; 
	}
	if(ttMOHON_AMBIL_TANAH_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_MOHON_AMBIL_TANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_MOHON_AMBIL_TANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat MOHON_AMBIL_TANAH_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#MOHON_AMBIL_TANAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(MOHON_AMBIL_TANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN.length > 1 )
	{
	for (t = 0; t < MOHON_AMBIL_TANAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN[t].value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1[t].value;
	
    }
	}
	else if(MOHON_AMBIL_TANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN.length < 1 )
	{
	for (t = 0; t < MOHON_AMBIL_TANAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN.value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1[0].value;
	}
	}
	else if(MOHON_AMBIL_TANAH_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN.value = document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(MOHON_AMBIL_TANAH_MAIN_temp1_length > 1)
	{
	for (t = 0; t < MOHON_AMBIL_TANAH_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(MOHON_AMBIL_TANAH_MAIN_temp1_length == 1)
	{	
	 document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1.value = "";			
	 var element = document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttMOHON_AMBIL_TANAH_MAIN; i++)
    {		
    if(ttMOHON_AMBIL_TANAH_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN,'30000','txtUlasanMOHON_AMBIL_TANAH_MAIN_check','txtUlasanMOHON_AMBIL_TANAH_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN[i],'30000','txtUlasanMOHON_AMBIL_TANAH_MAIN_check'+i,'txtUlasanMOHON_AMBIL_TANAH_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}



function textarea_PERUNTUKAN_MAIN(tambahtolak,jenis,index_tolak)
{

var PERUNTUKAN_MAIN_temp1_length=0;

if(document.${formName}.PERUNTUKAN_MAIN_temp1 != null)
{

if(document.${formName}.PERUNTUKAN_MAIN_temp1.length>0)
{
PERUNTUKAN_MAIN_temp1_length = document.${formName}.PERUNTUKAN_MAIN_temp1.length;
}
else
{
PERUNTUKAN_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanPERUNTUKAN_MAIN!=null)
{

if(document.${formName}.txtUlasanPERUNTUKAN_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanPERUNTUKAN_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='PERUNTUKAN_MAIN_tempX1' name='PERUNTUKAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERUNTUKAN_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='PERUNTUKAN_MAIN_tempX1' name='PERUNTUKAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERUNTUKAN_MAIN.value+"'></td></tr>";

}
}




$jquery("#PERUNTUKAN_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(PERUNTUKAN_MAIN_temp1_length>0)
{
ttPERUNTUKAN_MAIN = PERUNTUKAN_MAIN_temp1_length;
}
else
{
ttPERUNTUKAN_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttPERUNTUKAN_MAIN = ttPERUNTUKAN_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttPERUNTUKAN_MAIN = ttPERUNTUKAN_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttPERUNTUKAN_MAIN; i++)
  {	
  if(ttPERUNTUKAN_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[0].value
	
    } 	
	}	
	
	if(jenis == "umum")
    {

	if(i==0)
	{
	 temp_value = '$peruntukan';
	}
	
	}	
   
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>2.2.1  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanPERUNTUKAN_MAIN\" id=\"txtUlasanPERUNTUKAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check','txtUlasanPERUNTUKAN_MAIN_num','normal','no','ulasan kos-kos akibat PERUNTUKAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanPERUNTUKAN_MAIN_check','txtUlasanPERUNTUKAN_MAIN_num','normal','no','ulasan kos-kos akibat PERUNTUKAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check','txtUlasanPERUNTUKAN_MAIN_num','normal','no','ulasan kos-kos akibat PERUNTUKAN_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanPERUNTUKAN_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanPERUNTUKAN_MAIN_num\" id=\"txtUlasanPERUNTUKAN_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanPERUNTUKAN_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_PERUNTUKAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_PERUNTUKAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat PERUNTUKAN_MAIN'> "+
	      " </span>"; 	
	     if(ttPERUNTUKAN_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_PERUNTUKAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_PERUNTUKAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat PERUNTUKAN_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttPERUNTUKAN_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1.value
	
	}	
	else if(ttPERUNTUKAN_MAIN>2 && i!=(ttPERUNTUKAN_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[i].value
	
	}	
	else if(ttPERUNTUKAN_MAIN>1 && i!=(ttPERUNTUKAN_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttPERUNTUKAN_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[0].value;
	
   }
   }		
	if(jenis == "umum")
    {
	/*
	if(i==0)
	{
	 temp_value = '$peruntukan';
	}
	*/
	}
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 2.2."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanPERUNTUKAN_MAIN\" id=\"txtUlasanPERUNTUKAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check"+i+"','txtUlasanPERUNTUKAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PERUNTUKAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanPERUNTUKAN_MAIN_check"+i+"','txtUlasanPERUNTUKAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PERUNTUKAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check"+i+"','txtUlasanPERUNTUKAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PERUNTUKAN_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanPERUNTUKAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanPERUNTUKAN_MAIN_num"+i+"\" id=\"txtUlasanPERUNTUKAN_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanPERUNTUKAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttPERUNTUKAN_MAIN>1 && ttPERUNTUKAN_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_PERUNTUKAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_PERUNTUKAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat PERUNTUKAN_MAIN'> "+
	" </span>"; 
	}
	if(ttPERUNTUKAN_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_PERUNTUKAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_PERUNTUKAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat PERUNTUKAN_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#PERUNTUKAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(PERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERUNTUKAN_MAIN.length > 1 )
	{
	for (t = 0; t < PERUNTUKAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanPERUNTUKAN_MAIN[t].value = document.${formName}.PERUNTUKAN_MAIN_temp1[t].value;
	
    }
	}
	else if(PERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERUNTUKAN_MAIN.length < 1 )
	{
	for (t = 0; t < PERUNTUKAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanPERUNTUKAN_MAIN.value = document.${formName}.PERUNTUKAN_MAIN_temp1[0].value;
	}
	}
	else if(PERUNTUKAN_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanPERUNTUKAN_MAIN.value = document.${formName}.PERUNTUKAN_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(PERUNTUKAN_MAIN_temp1_length > 1)
	{
	for (t = 0; t < PERUNTUKAN_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.PERUNTUKAN_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.PERUNTUKAN_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(PERUNTUKAN_MAIN_temp1_length == 1)
	{	
	 document.${formName}.PERUNTUKAN_MAIN_temp1.value = "";			
	 var element = document.${formName}.PERUNTUKAN_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttPERUNTUKAN_MAIN; i++)
    {		
    if(ttPERUNTUKAN_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanPERUNTUKAN_MAIN,'30000','txtUlasanPERUNTUKAN_MAIN_check','txtUlasanPERUNTUKAN_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanPERUNTUKAN_MAIN[i],'30000','txtUlasanPERUNTUKAN_MAIN_check'+i,'txtUlasanPERUNTUKAN_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}


function textarea_ISTIHAR_PENGAMBILAN_MAIN(tambahtolak,jenis,index_tolak)
{

var ISTIHAR_PENGAMBILAN_MAIN_temp1_length=0;

if(document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1 != null)
{

if(document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1.length>0)
{
ISTIHAR_PENGAMBILAN_MAIN_temp1_length = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1.length;
}
else
{
ISTIHAR_PENGAMBILAN_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN!=null)
{

if(document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='ISTIHAR_PENGAMBILAN_MAIN_tempX1' name='ISTIHAR_PENGAMBILAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='ISTIHAR_PENGAMBILAN_MAIN_tempX1' name='ISTIHAR_PENGAMBILAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN.value+"'></td></tr>";

}
}




$jquery("#ISTIHAR_PENGAMBILAN_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(ISTIHAR_PENGAMBILAN_MAIN_temp1_length>0)
{
ttISTIHAR_PENGAMBILAN_MAIN = ISTIHAR_PENGAMBILAN_MAIN_temp1_length;
}
else
{
ttISTIHAR_PENGAMBILAN_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttISTIHAR_PENGAMBILAN_MAIN = ttISTIHAR_PENGAMBILAN_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttISTIHAR_PENGAMBILAN_MAIN = ttISTIHAR_PENGAMBILAN_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttISTIHAR_PENGAMBILAN_MAIN; i++)
  {	
  if(ttISTIHAR_PENGAMBILAN_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_tempX1[0].value
	
    } 	
	}	
	
/*	if(jenis == "umum")
    {

	if(i==0)
	{
	 temp_value = '$peruntukan';
	}
	
	}	
   */
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>2.3.1  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN\" id=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanISTIHAR_PENGAMBILAN_MAIN_check','txtUlasanISTIHAR_PENGAMBILAN_MAIN_num','normal','no','ulasan kos-kos akibat ISTIHAR_PENGAMBILAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanISTIHAR_PENGAMBILAN_MAIN_check','txtUlasanISTIHAR_PENGAMBILAN_MAIN_num','normal','no','ulasan kos-kos akibat ISTIHAR_PENGAMBILAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanISTIHAR_PENGAMBILAN_MAIN_check','txtUlasanISTIHAR_PENGAMBILAN_MAIN_num','normal','no','ulasan kos-kos akibat ISTIHAR_PENGAMBILAN_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN_num\" id=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_ISTIHAR_PENGAMBILAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_ISTIHAR_PENGAMBILAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat ISTIHAR_PENGAMBILAN_MAIN'> "+
	      " </span>"; 	
	     if(ttISTIHAR_PENGAMBILAN_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_ISTIHAR_PENGAMBILAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_ISTIHAR_PENGAMBILAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat ISTIHAR_PENGAMBILAN_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttISTIHAR_PENGAMBILAN_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_tempX1.value
	
	}	
	else if(ttISTIHAR_PENGAMBILAN_MAIN>2 && i!=(ttISTIHAR_PENGAMBILAN_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_tempX1[i].value
	
	}	
	else if(ttISTIHAR_PENGAMBILAN_MAIN>1 && i!=(ttISTIHAR_PENGAMBILAN_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttISTIHAR_PENGAMBILAN_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_tempX1[0].value;
	
   }
   }		
	if(jenis == "umum")
    {
	/*
	if(i==0)
	{
	 temp_value = '$peruntukan';
	}
	*/
	}
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 2.3."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN\" id=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanISTIHAR_PENGAMBILAN_MAIN_check"+i+"','txtUlasanISTIHAR_PENGAMBILAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat ISTIHAR_PENGAMBILAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanISTIHAR_PENGAMBILAN_MAIN_check"+i+"','txtUlasanISTIHAR_PENGAMBILAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat ISTIHAR_PENGAMBILAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanISTIHAR_PENGAMBILAN_MAIN_check"+i+"','txtUlasanISTIHAR_PENGAMBILAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat ISTIHAR_PENGAMBILAN_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN_num"+i+"\" id=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanISTIHAR_PENGAMBILAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttISTIHAR_PENGAMBILAN_MAIN>1 && ttISTIHAR_PENGAMBILAN_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_ISTIHAR_PENGAMBILAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_ISTIHAR_PENGAMBILAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat ISTIHAR_PENGAMBILAN_MAIN'> "+
	" </span>"; 
	}
	if(ttISTIHAR_PENGAMBILAN_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_ISTIHAR_PENGAMBILAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_ISTIHAR_PENGAMBILAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat ISTIHAR_PENGAMBILAN_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#ISTIHAR_PENGAMBILAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(ISTIHAR_PENGAMBILAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN.length > 1 )
	{
	for (t = 0; t < ISTIHAR_PENGAMBILAN_MAIN_temp1_length; t++)
    {	

    document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN[t].value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1[t].value;
	
    }
	}
	else if(ISTIHAR_PENGAMBILAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN.length < 1 )
	{
	for (t = 0; t < ISTIHAR_PENGAMBILAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN.value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1[0].value;
	}
	}
	else if(ISTIHAR_PENGAMBILAN_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN.value = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(ISTIHAR_PENGAMBILAN_MAIN_temp1_length > 1)
	{
	for (t = 0; t < ISTIHAR_PENGAMBILAN_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(ISTIHAR_PENGAMBILAN_MAIN_temp1_length == 1)
	{	
	 document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1.value = "";			
	 var element = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttISTIHAR_PENGAMBILAN_MAIN; i++)
    {		
    if(ttISTIHAR_PENGAMBILAN_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN,'30000','txtUlasanISTIHAR_PENGAMBILAN_MAIN_check','txtUlasanISTIHAR_PENGAMBILAN_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN[i],'30000','txtUlasanISTIHAR_PENGAMBILAN_MAIN_check'+i,'txtUlasanISTIHAR_PENGAMBILAN_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}



function textarea_WASILAN_HAKMILIK_MAIN(tambahtolak,jenis,index_tolak)
{

var WASILAN_HAKMILIK_MAIN_temp1_length=0;

if(document.${formName}.WASILAN_HAKMILIK_MAIN_temp1 != null)
{

if(document.${formName}.WASILAN_HAKMILIK_MAIN_temp1.length>0)
{
WASILAN_HAKMILIK_MAIN_temp1_length = document.${formName}.WASILAN_HAKMILIK_MAIN_temp1.length;
}
else
{
WASILAN_HAKMILIK_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN!=null)
{

if(document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='WASILAN_HAKMILIK_MAIN_tempX1' name='WASILAN_HAKMILIK_MAIN_tempX1' value= '"+document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='WASILAN_HAKMILIK_MAIN_tempX1' name='WASILAN_HAKMILIK_MAIN_tempX1' value= '"+document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN.value+"'></td></tr>";

}
}




$jquery("#WASILAN_HAKMILIK_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(WASILAN_HAKMILIK_MAIN_temp1_length>0)
{
ttWASILAN_HAKMILIK_MAIN = WASILAN_HAKMILIK_MAIN_temp1_length;
}
else
{
ttWASILAN_HAKMILIK_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttWASILAN_HAKMILIK_MAIN = ttWASILAN_HAKMILIK_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttWASILAN_HAKMILIK_MAIN = ttWASILAN_HAKMILIK_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttWASILAN_HAKMILIK_MAIN; i++)
  {	
  if(ttWASILAN_HAKMILIK_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.WASILAN_HAKMILIK_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.WASILAN_HAKMILIK_MAIN_tempX1[0].value
	
    } 	
	}	
	
/*	if(jenis == "umum")
    {

	if(i==0)
	{
	 temp_value = '$peruntukan';
	}
	
	}	
   */
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>2.4.1  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanWASILAN_HAKMILIK_MAIN\" id=\"txtUlasanWASILAN_HAKMILIK_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanWASILAN_HAKMILIK_MAIN_check','txtUlasanWASILAN_HAKMILIK_MAIN_num','normal','no','ulasan kos-kos akibat WASILAN_HAKMILIK_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanWASILAN_HAKMILIK_MAIN_check','txtUlasanWASILAN_HAKMILIK_MAIN_num','normal','no','ulasan kos-kos akibat WASILAN_HAKMILIK_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanWASILAN_HAKMILIK_MAIN_check','txtUlasanWASILAN_HAKMILIK_MAIN_num','normal','no','ulasan kos-kos akibat WASILAN_HAKMILIK_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanWASILAN_HAKMILIK_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanWASILAN_HAKMILIK_MAIN_num\" id=\"txtUlasanWASILAN_HAKMILIK_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanWASILAN_HAKMILIK_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_WASILAN_HAKMILIK_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_WASILAN_HAKMILIK_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat WASILAN_HAKMILIK_MAIN'> "+
	      " </span>"; 	
	     if(ttWASILAN_HAKMILIK_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_WASILAN_HAKMILIK_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_WASILAN_HAKMILIK_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat WASILAN_HAKMILIK_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttWASILAN_HAKMILIK_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.WASILAN_HAKMILIK_MAIN_tempX1.value
	
	}	
	else if(ttWASILAN_HAKMILIK_MAIN>2 && i!=(ttWASILAN_HAKMILIK_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.WASILAN_HAKMILIK_MAIN_tempX1[i].value
	
	}	
	else if(ttWASILAN_HAKMILIK_MAIN>1 && i!=(ttWASILAN_HAKMILIK_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.WASILAN_HAKMILIK_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.WASILAN_HAKMILIK_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.WASILAN_HAKMILIK_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttWASILAN_HAKMILIK_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.WASILAN_HAKMILIK_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.WASILAN_HAKMILIK_MAIN_tempX1[0].value;
	
   }
   }		
	if(jenis == "umum")
    {
	/*
	if(i==0)
	{
	 temp_value = '$peruntukan';
	}
	*/
	}
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 2.4."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanWASILAN_HAKMILIK_MAIN\" id=\"txtUlasanWASILAN_HAKMILIK_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanWASILAN_HAKMILIK_MAIN_check"+i+"','txtUlasanWASILAN_HAKMILIK_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat WASILAN_HAKMILIK_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanWASILAN_HAKMILIK_MAIN_check"+i+"','txtUlasanWASILAN_HAKMILIK_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat WASILAN_HAKMILIK_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanWASILAN_HAKMILIK_MAIN_check"+i+"','txtUlasanWASILAN_HAKMILIK_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat WASILAN_HAKMILIK_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanWASILAN_HAKMILIK_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanWASILAN_HAKMILIK_MAIN_num"+i+"\" id=\"txtUlasanWASILAN_HAKMILIK_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanWASILAN_HAKMILIK_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttWASILAN_HAKMILIK_MAIN>1 && ttWASILAN_HAKMILIK_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_WASILAN_HAKMILIK_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_WASILAN_HAKMILIK_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat WASILAN_HAKMILIK_MAIN'> "+
	" </span>"; 
	}
	if(ttWASILAN_HAKMILIK_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_WASILAN_HAKMILIK_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_WASILAN_HAKMILIK_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat WASILAN_HAKMILIK_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#WASILAN_HAKMILIK_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(WASILAN_HAKMILIK_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN.length > 1 )
	{
	for (t = 0; t < WASILAN_HAKMILIK_MAIN_temp1_length; t++)
    {	

    document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN[t].value = document.${formName}.WASILAN_HAKMILIK_MAIN_temp1[t].value;
	
    }
	}
	else if(WASILAN_HAKMILIK_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN.length < 1 )
	{
	for (t = 0; t < WASILAN_HAKMILIK_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN.value = document.${formName}.WASILAN_HAKMILIK_MAIN_temp1[0].value;
	}
	}
	else if(WASILAN_HAKMILIK_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN.value = document.${formName}.WASILAN_HAKMILIK_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(WASILAN_HAKMILIK_MAIN_temp1_length > 1)
	{
	for (t = 0; t < WASILAN_HAKMILIK_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.WASILAN_HAKMILIK_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.WASILAN_HAKMILIK_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(WASILAN_HAKMILIK_MAIN_temp1_length == 1)
	{	
	 document.${formName}.WASILAN_HAKMILIK_MAIN_temp1.value = "";			
	 var element = document.${formName}.WASILAN_HAKMILIK_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttWASILAN_HAKMILIK_MAIN; i++)
    {		
    if(ttWASILAN_HAKMILIK_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN,'30000','txtUlasanWASILAN_HAKMILIK_MAIN_check','txtUlasanWASILAN_HAKMILIK_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN[i],'30000','txtUlasanWASILAN_HAKMILIK_MAIN_check'+i,'txtUlasanWASILAN_HAKMILIK_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}


function textarea_SEBABPENARIKAN_MAIN(tambahtolak,jenis,index_tolak)
{

var SEBABPENARIKAN_MAIN_temp1_length=0;

if(document.${formName}.SEBABPENARIKAN_MAIN_temp1 != null)
{

if(document.${formName}.SEBABPENARIKAN_MAIN_temp1.length>0)
{
SEBABPENARIKAN_MAIN_temp1_length = document.${formName}.SEBABPENARIKAN_MAIN_temp1.length;
}
else
{
SEBABPENARIKAN_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanSEBABPENARIKAN_MAIN!=null)
{

if(document.${formName}.txtUlasanSEBABPENARIKAN_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanSEBABPENARIKAN_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='SEBABPENARIKAN_MAIN_tempX1' name='SEBABPENARIKAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanSEBABPENARIKAN_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='SEBABPENARIKAN_MAIN_tempX1' name='SEBABPENARIKAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanSEBABPENARIKAN_MAIN.value+"'></td></tr>";

}
}




$jquery("#SEBABPENARIKAN_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(SEBABPENARIKAN_MAIN_temp1_length>0)
{
ttSEBABPENARIKAN_MAIN = SEBABPENARIKAN_MAIN_temp1_length;
}
else
{
ttSEBABPENARIKAN_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttSEBABPENARIKAN_MAIN = ttSEBABPENARIKAN_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttSEBABPENARIKAN_MAIN = ttSEBABPENARIKAN_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttSEBABPENARIKAN_MAIN; i++)
  {	
  if(ttSEBABPENARIKAN_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.SEBABPENARIKAN_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.SEBABPENARIKAN_MAIN_tempX1[0].value
	
    } 	
	}		
   
     if(jenis == "umum")
    {
	temp_value = '$txtSebabPembatalan';
	}	
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'> </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanSEBABPENARIKAN_MAIN\" id=\"txtUlasanSEBABPENARIKAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanSEBABPENARIKAN_MAIN_check','txtUlasanSEBABPENARIKAN_MAIN_num','normal','no','ulasan kos-kos akibat SEBABPENARIKAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanSEBABPENARIKAN_MAIN_check','txtUlasanSEBABPENARIKAN_MAIN_num','normal','no','ulasan kos-kos akibat SEBABPENARIKAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanSEBABPENARIKAN_MAIN_check','txtUlasanSEBABPENARIKAN_MAIN_num','normal','no','ulasan kos-kos akibat SEBABPENARIKAN_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanSEBABPENARIKAN_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanSEBABPENARIKAN_MAIN_num\" id=\"txtUlasanSEBABPENARIKAN_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanSEBABPENARIKAN_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_SEBABPENARIKAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_SEBABPENARIKAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat SEBABPENARIKAN_MAIN'> "+
	      " </span>"; 	
	     if(ttSEBABPENARIKAN_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_SEBABPENARIKAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_SEBABPENARIKAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat SEBABPENARIKAN_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttSEBABPENARIKAN_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.SEBABPENARIKAN_MAIN_tempX1.value
	
	}	
	else if(ttSEBABPENARIKAN_MAIN>2 && i!=(ttSEBABPENARIKAN_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.SEBABPENARIKAN_MAIN_tempX1[i].value
	
	}	
	else if(ttSEBABPENARIKAN_MAIN>1 && i!=(ttSEBABPENARIKAN_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.SEBABPENARIKAN_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.SEBABPENARIKAN_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.SEBABPENARIKAN_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttSEBABPENARIKAN_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.SEBABPENARIKAN_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.SEBABPENARIKAN_MAIN_tempX1[0].value;
	
   }
   }	
   
   
 
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 3."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanSEBABPENARIKAN_MAIN\" id=\"txtUlasanSEBABPENARIKAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanSEBABPENARIKAN_MAIN_check"+i+"','txtUlasanSEBABPENARIKAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SEBABPENARIKAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanSEBABPENARIKAN_MAIN_check"+i+"','txtUlasanSEBABPENARIKAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SEBABPENARIKAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanSEBABPENARIKAN_MAIN_check"+i+"','txtUlasanSEBABPENARIKAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SEBABPENARIKAN_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanSEBABPENARIKAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanSEBABPENARIKAN_MAIN_num"+i+"\" id=\"txtUlasanSEBABPENARIKAN_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanSEBABPENARIKAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttSEBABPENARIKAN_MAIN>1 && ttSEBABPENARIKAN_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_SEBABPENARIKAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_SEBABPENARIKAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat SEBABPENARIKAN_MAIN'> "+
	" </span>"; 
	}
	if(ttSEBABPENARIKAN_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_SEBABPENARIKAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_SEBABPENARIKAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat SEBABPENARIKAN_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#SEBABPENARIKAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(SEBABPENARIKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSEBABPENARIKAN_MAIN.length > 1 )
	{
	for (t = 0; t < SEBABPENARIKAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanSEBABPENARIKAN_MAIN[t].value = document.${formName}.SEBABPENARIKAN_MAIN_temp1[t].value;
	
    }
	}
	else if(SEBABPENARIKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSEBABPENARIKAN_MAIN.length < 1 )
	{
	for (t = 0; t < SEBABPENARIKAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanSEBABPENARIKAN_MAIN.value = document.${formName}.SEBABPENARIKAN_MAIN_temp1[0].value;
	}
	}
	else if(SEBABPENARIKAN_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanSEBABPENARIKAN_MAIN.value = document.${formName}.SEBABPENARIKAN_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(SEBABPENARIKAN_MAIN_temp1_length > 1)
	{
	for (t = 0; t < SEBABPENARIKAN_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.SEBABPENARIKAN_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.SEBABPENARIKAN_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(SEBABPENARIKAN_MAIN_temp1_length == 1)
	{	
	 document.${formName}.SEBABPENARIKAN_MAIN_temp1.value = "";			
	 var element = document.${formName}.SEBABPENARIKAN_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttSEBABPENARIKAN_MAIN; i++)
    {		
    if(ttSEBABPENARIKAN_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanSEBABPENARIKAN_MAIN,'30000','txtUlasanSEBABPENARIKAN_MAIN_check','txtUlasanSEBABPENARIKAN_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanSEBABPENARIKAN_MAIN[i],'30000','txtUlasanSEBABPENARIKAN_MAIN_check'+i,'txtUlasanSEBABPENARIKAN_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}

function textarea_PENUTUP_MAIN(tambahtolak,jenis,index_tolak)
{

var PENUTUP_MAIN_temp1_length=0;

if(document.${formName}.PENUTUP_MAIN_temp1 != null)
{

if(document.${formName}.PENUTUP_MAIN_temp1.length>0)
{
PENUTUP_MAIN_temp1_length = document.${formName}.PENUTUP_MAIN_temp1.length;
}
else
{
PENUTUP_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanPENUTUP_MAIN!=null)
{

if(document.${formName}.txtUlasanPENUTUP_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanPENUTUP_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='PENUTUP_MAIN_tempX1' name='PENUTUP_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPENUTUP_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='PENUTUP_MAIN_tempX1' name='PENUTUP_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPENUTUP_MAIN.value+"'></td></tr>";

}
}




$jquery("#PENUTUP_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(PENUTUP_MAIN_temp1_length>0)
{
ttPENUTUP_MAIN = PENUTUP_MAIN_temp1_length;
}
else
{
ttPENUTUP_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttPENUTUP_MAIN = ttPENUTUP_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttPENUTUP_MAIN = ttPENUTUP_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttPENUTUP_MAIN; i++)
  {	
  if(ttPENUTUP_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.PENUTUP_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.PENUTUP_MAIN_tempX1[0].value
	
    } 	
	}		
   
  /*   if(jenis == "umum")
    {
	temp_value = '$txtSebabPembatalan';
	}	*/
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'> </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanPENUTUP_MAIN\" id=\"txtUlasanPENUTUP_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanPENUTUP_MAIN_check','txtUlasanPENUTUP_MAIN_num','normal','no','ulasan kos-kos akibat PENUTUP_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanPENUTUP_MAIN_check','txtUlasanPENUTUP_MAIN_num','normal','no','ulasan kos-kos akibat PENUTUP_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanPENUTUP_MAIN_check','txtUlasanPENUTUP_MAIN_num','normal','no','ulasan kos-kos akibat PENUTUP_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanPENUTUP_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanPENUTUP_MAIN_num\" id=\"txtUlasanPENUTUP_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanPENUTUP_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_PENUTUP_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_PENUTUP_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat PENUTUP_MAIN'> "+
	      " </span>"; 	
	     if(ttPENUTUP_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_PENUTUP_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_PENUTUP_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat PENUTUP_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttPENUTUP_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.PENUTUP_MAIN_tempX1.value
	
	}	
	else if(ttPENUTUP_MAIN>2 && i!=(ttPENUTUP_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.PENUTUP_MAIN_tempX1[i].value
	
	}	
	else if(ttPENUTUP_MAIN>1 && i!=(ttPENUTUP_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.PENUTUP_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.PENUTUP_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.PENUTUP_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttPENUTUP_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.PENUTUP_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.PENUTUP_MAIN_tempX1[0].value;
	
   }
   }	
   
   
 
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 6."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanPENUTUP_MAIN\" id=\"txtUlasanPENUTUP_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanPENUTUP_MAIN_check"+i+"','txtUlasanPENUTUP_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PENUTUP_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanPENUTUP_MAIN_check"+i+"','txtUlasanPENUTUP_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PENUTUP_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanPENUTUP_MAIN_check"+i+"','txtUlasanPENUTUP_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PENUTUP_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanPENUTUP_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanPENUTUP_MAIN_num"+i+"\" id=\"txtUlasanPENUTUP_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanPENUTUP_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttPENUTUP_MAIN>1 && ttPENUTUP_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_PENUTUP_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_PENUTUP_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat PENUTUP_MAIN'> "+
	" </span>"; 
	}
	if(ttPENUTUP_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_PENUTUP_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_PENUTUP_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat PENUTUP_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#PENUTUP_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(PENUTUP_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPENUTUP_MAIN.length > 1 )
	{
	for (t = 0; t < PENUTUP_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanPENUTUP_MAIN[t].value = document.${formName}.PENUTUP_MAIN_temp1[t].value;
	
    }
	}
	else if(PENUTUP_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPENUTUP_MAIN.length < 1 )
	{
	for (t = 0; t < PENUTUP_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanPENUTUP_MAIN.value = document.${formName}.PENUTUP_MAIN_temp1[0].value;
	}
	}
	else if(PENUTUP_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanPENUTUP_MAIN.value = document.${formName}.PENUTUP_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(PENUTUP_MAIN_temp1_length > 1)
	{
	for (t = 0; t < PENUTUP_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.PENUTUP_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.PENUTUP_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(PENUTUP_MAIN_temp1_length == 1)
	{	
	 document.${formName}.PENUTUP_MAIN_temp1.value = "";			
	 var element = document.${formName}.PENUTUP_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttPENUTUP_MAIN; i++)
    {		
    if(ttPENUTUP_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanPENUTUP_MAIN,'30000','txtUlasanPENUTUP_MAIN_check','txtUlasanPENUTUP_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanPENUTUP_MAIN[i],'30000','txtUlasanPENUTUP_MAIN_check'+i,'txtUlasanPENUTUP_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}


function textarea_PERAKUAN_MAIN(tambahtolak,jenis,index_tolak)
{

var PERAKUAN_MAIN_temp1_length=0;

if(document.${formName}.PERAKUAN_MAIN_temp1 != null)
{

if(document.${formName}.PERAKUAN_MAIN_temp1.length>0)
{
PERAKUAN_MAIN_temp1_length = document.${formName}.PERAKUAN_MAIN_temp1.length;
}
else
{
PERAKUAN_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanPERAKUAN_MAIN!=null)
{

if(document.${formName}.txtUlasanPERAKUAN_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanPERAKUAN_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='PERAKUAN_MAIN_tempX1' name='PERAKUAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUAN_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='PERAKUAN_MAIN_tempX1' name='PERAKUAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUAN_MAIN.value+"'></td></tr>";

}
}




$jquery("#PERAKUAN_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(PERAKUAN_MAIN_temp1_length>0)
{
ttPERAKUAN_MAIN = PERAKUAN_MAIN_temp1_length;
}
else
{
ttPERAKUAN_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttPERAKUAN_MAIN = ttPERAKUAN_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttPERAKUAN_MAIN = ttPERAKUAN_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttPERAKUAN_MAIN; i++)
  {	
  if(ttPERAKUAN_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.PERAKUAN_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.PERAKUAN_MAIN_tempX1[0].value
	
    } 	
	}		
  /* 
     if(jenis == "umum")
    {
	temp_value = '$txtSebabPembatalan';
	}	*/
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>i) </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanPERAKUAN_MAIN\" id=\"txtUlasanPERAKUAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanPERAKUAN_MAIN_check','txtUlasanPERAKUAN_MAIN_num','normal','no','ulasan kos-kos akibat PERAKUAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanPERAKUAN_MAIN_check','txtUlasanPERAKUAN_MAIN_num','normal','no','ulasan kos-kos akibat PERAKUAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanPERAKUAN_MAIN_check','txtUlasanPERAKUAN_MAIN_num','normal','no','ulasan kos-kos akibat PERAKUAN_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanPERAKUAN_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanPERAKUAN_MAIN_num\" id=\"txtUlasanPERAKUAN_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanPERAKUAN_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_PERAKUAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_PERAKUAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat PERAKUAN_MAIN'> "+
	      " </span>"; 	
	     if(ttPERAKUAN_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_PERAKUAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_PERAKUAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat PERAKUAN_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttPERAKUAN_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.PERAKUAN_MAIN_tempX1.value
	
	}	
	else if(ttPERAKUAN_MAIN>2 && i!=(ttPERAKUAN_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.PERAKUAN_MAIN_tempX1[i].value
	
	}	
	else if(ttPERAKUAN_MAIN>1 && i!=(ttPERAKUAN_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.PERAKUAN_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.PERAKUAN_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.PERAKUAN_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttPERAKUAN_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.PERAKUAN_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.PERAKUAN_MAIN_tempX1[0].value;
	
   }
   }	
   
   
   if((i+1)==1)
	{
	var num = "i)"
	}
	else if((i+1)==2)
	{
	var num = "ii)"
	}
	else if((i+1)==2)
	{
	var num = "ii)"
	}
	else if((i+1)==3)
	{
	var num = "iii)"
	}
	else if((i+1)==4)
	{
	var num = "iv)"
	}
	else if((i+1)==5)
	{
	var num = "v)"
	}
	else if((i+1)==6)
	{
	var num = "vi)"
	}
	else if((i+1)==7)
	{
	var num = "vii)"
	}
	else if((i+1)==8)
	{
	var num = "viii)"
	}
	else if((i+1)==9)
	{
	var num = "ix)"
	}
	else if((i+1)==10)
	{
	var num = "x)"
	}
	else
	{
	var num = ""
	}
	
   
 
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> "+num+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanPERAKUAN_MAIN\" id=\"txtUlasanPERAKUAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanPERAKUAN_MAIN_check"+i+"','txtUlasanPERAKUAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PERAKUAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanPERAKUAN_MAIN_check"+i+"','txtUlasanPERAKUAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PERAKUAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanPERAKUAN_MAIN_check"+i+"','txtUlasanPERAKUAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PERAKUAN_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanPERAKUAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanPERAKUAN_MAIN_num"+i+"\" id=\"txtUlasanPERAKUAN_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanPERAKUAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttPERAKUAN_MAIN>1 && ttPERAKUAN_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_PERAKUAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_PERAKUAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat PERAKUAN_MAIN'> "+
	" </span>"; 
	}
	if(ttPERAKUAN_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_PERAKUAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_PERAKUAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat PERAKUAN_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}

	
	$jquery("#PERAKUAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(PERAKUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUAN_MAIN.length > 1 )
	{
	for (t = 0; t < PERAKUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanPERAKUAN_MAIN[t].value = document.${formName}.PERAKUAN_MAIN_temp1[t].value;
	
    }
	}
	else if(PERAKUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUAN_MAIN.length < 1 )
	{
	for (t = 0; t < PERAKUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanPERAKUAN_MAIN.value = document.${formName}.PERAKUAN_MAIN_temp1[0].value;
	}
	}
	else if(PERAKUAN_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanPERAKUAN_MAIN.value = document.${formName}.PERAKUAN_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(PERAKUAN_MAIN_temp1_length > 1)
	{
	for (t = 0; t < PERAKUAN_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.PERAKUAN_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.PERAKUAN_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(PERAKUAN_MAIN_temp1_length == 1)
	{	
	 document.${formName}.PERAKUAN_MAIN_temp1.value = "";			
	 var element = document.${formName}.PERAKUAN_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttPERAKUAN_MAIN; i++)
    {		
    if(ttPERAKUAN_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanPERAKUAN_MAIN,'30000','txtUlasanPERAKUAN_MAIN_check','txtUlasanPERAKUAN_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanPERAKUAN_MAIN[i],'30000','txtUlasanPERAKUAN_MAIN_check'+i,'txtUlasanPERAKUAN_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}






function textarea_SOKONGAN_PENGARAH_MAIN(tambahtolak,jenis,index_tolak)
{

var SOKONGAN_PENGARAH_MAIN_temp1_length=0;

if(document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1 != null)
{

if(document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1.length>0)
{
SOKONGAN_PENGARAH_MAIN_temp1_length = document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1.length;
}
else
{
SOKONGAN_PENGARAH_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN!=null)
{

if(document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='SOKONGAN_PENGARAH_MAIN_tempX1' name='SOKONGAN_PENGARAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='SOKONGAN_PENGARAH_MAIN_tempX1' name='SOKONGAN_PENGARAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN.value+"'></td></tr>";

}
}




$jquery("#SOKONGAN_PENGARAH_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(SOKONGAN_PENGARAH_MAIN_temp1_length>0)
{
ttSOKONGAN_PENGARAH_MAIN = SOKONGAN_PENGARAH_MAIN_temp1_length;
}
else
{
ttSOKONGAN_PENGARAH_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttSOKONGAN_PENGARAH_MAIN = ttSOKONGAN_PENGARAH_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttSOKONGAN_PENGARAH_MAIN = ttSOKONGAN_PENGARAH_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttSOKONGAN_PENGARAH_MAIN; i++)
  {	
  if(ttSOKONGAN_PENGARAH_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.SOKONGAN_PENGARAH_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.SOKONGAN_PENGARAH_MAIN_tempX1[0].value
	
    } 	
	}		
   
 /*    if(jenis == "umum")
    {
	temp_value = '$txtSebabPembatalan';
	}	*/
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>4.1.1 </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanSOKONGAN_PENGARAH_MAIN\" id=\"txtUlasanSOKONGAN_PENGARAH_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanSOKONGAN_PENGARAH_MAIN_check','txtUlasanSOKONGAN_PENGARAH_MAIN_num','normal','no','ulasan kos-kos akibat SOKONGAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanSOKONGAN_PENGARAH_MAIN_check','txtUlasanSOKONGAN_PENGARAH_MAIN_num','normal','no','ulasan kos-kos akibat SOKONGAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanSOKONGAN_PENGARAH_MAIN_check','txtUlasanSOKONGAN_PENGARAH_MAIN_num','normal','no','ulasan kos-kos akibat SOKONGAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanSOKONGAN_PENGARAH_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanSOKONGAN_PENGARAH_MAIN_num\" id=\"txtUlasanSOKONGAN_PENGARAH_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanSOKONGAN_PENGARAH_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_SOKONGAN_PENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_SOKONGAN_PENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat SOKONGAN_PENGARAH_MAIN'> "+
	      " </span>"; 	
	     if(ttSOKONGAN_PENGARAH_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_SOKONGAN_PENGARAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_SOKONGAN_PENGARAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat SOKONGAN_PENGARAH_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttSOKONGAN_PENGARAH_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.SOKONGAN_PENGARAH_MAIN_tempX1.value
	
	}	
	else if(ttSOKONGAN_PENGARAH_MAIN>2 && i!=(ttSOKONGAN_PENGARAH_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.SOKONGAN_PENGARAH_MAIN_tempX1[i].value
	
	}	
	else if(ttSOKONGAN_PENGARAH_MAIN>1 && i!=(ttSOKONGAN_PENGARAH_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.SOKONGAN_PENGARAH_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.SOKONGAN_PENGARAH_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.SOKONGAN_PENGARAH_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttSOKONGAN_PENGARAH_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.SOKONGAN_PENGARAH_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.SOKONGAN_PENGARAH_MAIN_tempX1[0].value;
	
   }
   }	
   
   
 
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 4.1."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanSOKONGAN_PENGARAH_MAIN\" id=\"txtUlasanSOKONGAN_PENGARAH_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanSOKONGAN_PENGARAH_MAIN_check"+i+"','txtUlasanSOKONGAN_PENGARAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SOKONGAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanSOKONGAN_PENGARAH_MAIN_check"+i+"','txtUlasanSOKONGAN_PENGARAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SOKONGAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanSOKONGAN_PENGARAH_MAIN_check"+i+"','txtUlasanSOKONGAN_PENGARAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SOKONGAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanSOKONGAN_PENGARAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanSOKONGAN_PENGARAH_MAIN_num"+i+"\" id=\"txtUlasanSOKONGAN_PENGARAH_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanSOKONGAN_PENGARAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttSOKONGAN_PENGARAH_MAIN>1 && ttSOKONGAN_PENGARAH_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_SOKONGAN_PENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_SOKONGAN_PENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat SOKONGAN_PENGARAH_MAIN'> "+
	" </span>"; 
	}
	if(ttSOKONGAN_PENGARAH_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_SOKONGAN_PENGARAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_SOKONGAN_PENGARAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat SOKONGAN_PENGARAH_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#SOKONGAN_PENGARAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(SOKONGAN_PENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN.length > 1 )
	{
	for (t = 0; t < SOKONGAN_PENGARAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN[t].value = document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1[t].value;
	
    }
	}
	else if(SOKONGAN_PENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN.length < 1 )
	{
	for (t = 0; t < SOKONGAN_PENGARAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN.value = document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1[0].value;
	}
	}
	else if(SOKONGAN_PENGARAH_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN.value = document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(SOKONGAN_PENGARAH_MAIN_temp1_length > 1)
	{
	for (t = 0; t < SOKONGAN_PENGARAH_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(SOKONGAN_PENGARAH_MAIN_temp1_length == 1)
	{	
	 document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1.value = "";			
	 var element = document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttSOKONGAN_PENGARAH_MAIN; i++)
    {		
    if(ttSOKONGAN_PENGARAH_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN,'30000','txtUlasanSOKONGAN_PENGARAH_MAIN_check','txtUlasanSOKONGAN_PENGARAH_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN[i],'30000','txtUlasanSOKONGAN_PENGARAH_MAIN_check'+i,'txtUlasanSOKONGAN_PENGARAH_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}



function textarea_MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN(tambahtolak,jenis,index_tolak)
{

var MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length=0;

if(document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1 != null)
{

if(document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1.length>0)
{
MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1.length;
}
else
{
MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN!=null)
{

if(document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1' name='MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1' name='MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.value+"'></td></tr>";

}
}




$jquery("#MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length>0)
{
ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN = MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length;
}
else
{
ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN = ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN = ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN; i++)
  {	
  if(ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1[0].value
	
    } 	
	}		
   
 /*    if(jenis == "umum")
    {
	temp_value = '$txtSebabPembatalan';
	}	*/
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>a)  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN\" id=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_check','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num','normal','no','ulasan kos-kos akibat MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_check','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num','normal','no','ulasan kos-kos akibat MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_check','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num','normal','no','ulasan kos-kos akibat MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num\" id=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN'> "+
	      " </span>"; 	
	     if(ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1.value
	
	}	
	else if(ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN>2 && i!=(ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1[i].value
	
	}	
	else if(ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN>1 && i!=(ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_tempX1[0].value;
	
   }
   }	
   
   
   if((i+1)==1)
	{
	var num = "a)"
	}
	else if((i+1)==2)
	{
	var num = "b)"
	}
	else if((i+1)==2)
	{
	var num = "b)"
	}
	else if((i+1)==3)
	{
	var num = "c)"
	}
	else if((i+1)==4)
	{
	var num = "d)"
	}
	else if((i+1)==5)
	{
	var num = "e)"
	}
	else if((i+1)==6)
	{
	var num = "f)"
	}
	else if((i+1)==7)
	{
	var num = "g)"
	}
	else if((i+1)==8)
	{
	var num = "h)"
	}
	else if((i+1)==9)
	{
	var num = "i)"
	}
	else if((i+1)==10)
	{
	var num = "j)"
	}
	else
	{
	var num = ""
	}
 
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> "+	
	num
	+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN\" id=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_check"+i+"','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_check"+i+"','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_check"+i+"','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num"+i+"\" id=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN>1 && ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN'> "+
	" </span>"; 
	}
	if(ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.length > 1 )
	{
	for (t = 0; t < MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN[t].value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1[t].value;
	
    }
	}
	else if(MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.length < 1 )
	{
	for (t = 0; t < MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1[0].value;
	}
	}
	else if(MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.value = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length > 1)
	{
	for (t = 0; t < MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length == 1)
	{	
	 document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1.value = "";			
	 var element = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN; i++)
    {		
    if(ttMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN,'30000','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_check','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN[i],'30000','txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_check'+i,'txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}




function textarea_LOT_TINGGAL_MAIN(tambahtolak,jenis,index_tolak)
{

var LOT_TINGGAL_MAIN_temp1_length=0;

if(document.${formName}.LOT_TINGGAL_MAIN_temp1 != null)
{

if(document.${formName}.LOT_TINGGAL_MAIN_temp1.length>0)
{
LOT_TINGGAL_MAIN_temp1_length = document.${formName}.LOT_TINGGAL_MAIN_temp1.length;
}
else
{
LOT_TINGGAL_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanLOT_TINGGAL_MAIN!=null)
{

if(document.${formName}.txtUlasanLOT_TINGGAL_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanLOT_TINGGAL_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='LOT_TINGGAL_MAIN_tempX1' name='LOT_TINGGAL_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLOT_TINGGAL_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='LOT_TINGGAL_MAIN_tempX1' name='LOT_TINGGAL_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLOT_TINGGAL_MAIN.value+"'></td></tr>";

}
}




$jquery("#LOT_TINGGAL_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(LOT_TINGGAL_MAIN_temp1_length>0)
{
ttLOT_TINGGAL_MAIN = LOT_TINGGAL_MAIN_temp1_length;
}
else
{
ttLOT_TINGGAL_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttLOT_TINGGAL_MAIN = ttLOT_TINGGAL_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttLOT_TINGGAL_MAIN = ttLOT_TINGGAL_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttLOT_TINGGAL_MAIN; i++)
  {	
  if(ttLOT_TINGGAL_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.LOT_TINGGAL_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.LOT_TINGGAL_MAIN_tempX1[0].value
	
    } 	
	}	
	
/*	if(jenis == "umum")
    {

	if(i==0)
	{
	 temp_value = '$peruntukan';
	}
	
	}	
   */
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>2.5.1  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanLOT_TINGGAL_MAIN\" id=\"txtUlasanLOT_TINGGAL_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanLOT_TINGGAL_MAIN_check','txtUlasanLOT_TINGGAL_MAIN_num','normal','no','ulasan kos-kos akibat LOT_TINGGAL_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanLOT_TINGGAL_MAIN_check','txtUlasanLOT_TINGGAL_MAIN_num','normal','no','ulasan kos-kos akibat LOT_TINGGAL_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanLOT_TINGGAL_MAIN_check','txtUlasanLOT_TINGGAL_MAIN_num','normal','no','ulasan kos-kos akibat LOT_TINGGAL_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanLOT_TINGGAL_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanLOT_TINGGAL_MAIN_num\" id=\"txtUlasanLOT_TINGGAL_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanLOT_TINGGAL_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_LOT_TINGGAL_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_LOT_TINGGAL_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat LOT_TINGGAL_MAIN'> "+
	      " </span>"; 	
	     if(ttLOT_TINGGAL_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_LOT_TINGGAL_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_LOT_TINGGAL_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat LOT_TINGGAL_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttLOT_TINGGAL_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.LOT_TINGGAL_MAIN_tempX1.value
	
	}	
	else if(ttLOT_TINGGAL_MAIN>2 && i!=(ttLOT_TINGGAL_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.LOT_TINGGAL_MAIN_tempX1[i].value
	
	}	
	else if(ttLOT_TINGGAL_MAIN>1 && i!=(ttLOT_TINGGAL_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.LOT_TINGGAL_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.LOT_TINGGAL_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.LOT_TINGGAL_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttLOT_TINGGAL_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.LOT_TINGGAL_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.LOT_TINGGAL_MAIN_tempX1[0].value;
	
   }
   }		
	if(jenis == "umum")
    {
	/*
	if(i==0)
	{
	 temp_value = '$peruntukan';
	}
	*/
	}
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 2.5."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanLOT_TINGGAL_MAIN\" id=\"txtUlasanLOT_TINGGAL_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanLOT_TINGGAL_MAIN_check"+i+"','txtUlasanLOT_TINGGAL_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LOT_TINGGAL_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanLOT_TINGGAL_MAIN_check"+i+"','txtUlasanLOT_TINGGAL_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LOT_TINGGAL_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanLOT_TINGGAL_MAIN_check"+i+"','txtUlasanLOT_TINGGAL_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LOT_TINGGAL_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanLOT_TINGGAL_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanLOT_TINGGAL_MAIN_num"+i+"\" id=\"txtUlasanLOT_TINGGAL_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanLOT_TINGGAL_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttLOT_TINGGAL_MAIN>1 && ttLOT_TINGGAL_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_LOT_TINGGAL_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_LOT_TINGGAL_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat LOT_TINGGAL_MAIN'> "+
	" </span>"; 
	}
	if(ttLOT_TINGGAL_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_LOT_TINGGAL_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_LOT_TINGGAL_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat LOT_TINGGAL_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#LOT_TINGGAL_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(LOT_TINGGAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLOT_TINGGAL_MAIN.length > 1 )
	{
	for (t = 0; t < LOT_TINGGAL_MAIN_temp1_length; t++)
    {	

    document.${formName}.txtUlasanLOT_TINGGAL_MAIN[t].value = document.${formName}.LOT_TINGGAL_MAIN_temp1[t].value;
	
    }
	}
	else if(LOT_TINGGAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLOT_TINGGAL_MAIN.length < 1 )
	{
	for (t = 0; t < LOT_TINGGAL_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanLOT_TINGGAL_MAIN.value = document.${formName}.LOT_TINGGAL_MAIN_temp1[0].value;
	}
	}
	else if(LOT_TINGGAL_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanLOT_TINGGAL_MAIN.value = document.${formName}.LOT_TINGGAL_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(LOT_TINGGAL_MAIN_temp1_length > 1)
	{
	for (t = 0; t < LOT_TINGGAL_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.LOT_TINGGAL_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.LOT_TINGGAL_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(LOT_TINGGAL_MAIN_temp1_length == 1)
	{	
	 document.${formName}.LOT_TINGGAL_MAIN_temp1.value = "";			
	 var element = document.${formName}.LOT_TINGGAL_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttLOT_TINGGAL_MAIN; i++)
    {		
    if(ttLOT_TINGGAL_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanLOT_TINGGAL_MAIN,'30000','txtUlasanLOT_TINGGAL_MAIN_check','txtUlasanLOT_TINGGAL_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanLOT_TINGGAL_MAIN[i],'30000','txtUlasanLOT_TINGGAL_MAIN_check'+i,'txtUlasanLOT_TINGGAL_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}



function textarea_PENYAMPAIAN_AWARD_MAIN(tambahtolak,jenis,index_tolak)
{

var PENYAMPAIAN_AWARD_MAIN_temp1_length=0;

if(document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1 != null)
{

if(document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1.length>0)
{
PENYAMPAIAN_AWARD_MAIN_temp1_length = document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1.length;
}
else
{
PENYAMPAIAN_AWARD_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN!=null)
{

if(document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='PENYAMPAIAN_AWARD_MAIN_tempX1' name='PENYAMPAIAN_AWARD_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='PENYAMPAIAN_AWARD_MAIN_tempX1' name='PENYAMPAIAN_AWARD_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN.value+"'></td></tr>";

}
}




$jquery("#PENYAMPAIAN_AWARD_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(PENYAMPAIAN_AWARD_MAIN_temp1_length>0)
{
ttPENYAMPAIAN_AWARD_MAIN = PENYAMPAIAN_AWARD_MAIN_temp1_length;
}
else
{
ttPENYAMPAIAN_AWARD_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttPENYAMPAIAN_AWARD_MAIN = ttPENYAMPAIAN_AWARD_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttPENYAMPAIAN_AWARD_MAIN = ttPENYAMPAIAN_AWARD_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttPENYAMPAIAN_AWARD_MAIN; i++)
  {	
  if(ttPENYAMPAIAN_AWARD_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_tempX1[0].value
	
    } 	
	}	
	
/*	if(jenis == "umum")
    {

	if(i==0)
	{
	 temp_value = '$peruntukan';
	}
	
	}	
   */
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>2.6.1  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanPENYAMPAIAN_AWARD_MAIN\" id=\"txtUlasanPENYAMPAIAN_AWARD_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanPENYAMPAIAN_AWARD_MAIN_check','txtUlasanPENYAMPAIAN_AWARD_MAIN_num','normal','no','ulasan kos-kos akibat PENYAMPAIAN_AWARD_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanPENYAMPAIAN_AWARD_MAIN_check','txtUlasanPENYAMPAIAN_AWARD_MAIN_num','normal','no','ulasan kos-kos akibat PENYAMPAIAN_AWARD_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanPENYAMPAIAN_AWARD_MAIN_check','txtUlasanPENYAMPAIAN_AWARD_MAIN_num','normal','no','ulasan kos-kos akibat PENYAMPAIAN_AWARD_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanPENYAMPAIAN_AWARD_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanPENYAMPAIAN_AWARD_MAIN_num\" id=\"txtUlasanPENYAMPAIAN_AWARD_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanPENYAMPAIAN_AWARD_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_PENYAMPAIAN_AWARD_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_PENYAMPAIAN_AWARD_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat PENYAMPAIAN_AWARD_MAIN'> "+
	      " </span>"; 	
	     if(ttPENYAMPAIAN_AWARD_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_PENYAMPAIAN_AWARD_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_PENYAMPAIAN_AWARD_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat PENYAMPAIAN_AWARD_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttPENYAMPAIAN_AWARD_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_tempX1.value
	
	}	
	else if(ttPENYAMPAIAN_AWARD_MAIN>2 && i!=(ttPENYAMPAIAN_AWARD_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_tempX1[i].value
	
	}	
	else if(ttPENYAMPAIAN_AWARD_MAIN>1 && i!=(ttPENYAMPAIAN_AWARD_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttPENYAMPAIAN_AWARD_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_tempX1[0].value;
	
   }
   }		
	if(jenis == "umum")
    {
	/*
	if(i==0)
	{
	 temp_value = '$peruntukan';
	}
	*/
	}
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 2.6."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanPENYAMPAIAN_AWARD_MAIN\" id=\"txtUlasanPENYAMPAIAN_AWARD_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanPENYAMPAIAN_AWARD_MAIN_check"+i+"','txtUlasanPENYAMPAIAN_AWARD_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PENYAMPAIAN_AWARD_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanPENYAMPAIAN_AWARD_MAIN_check"+i+"','txtUlasanPENYAMPAIAN_AWARD_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PENYAMPAIAN_AWARD_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanPENYAMPAIAN_AWARD_MAIN_check"+i+"','txtUlasanPENYAMPAIAN_AWARD_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PENYAMPAIAN_AWARD_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanPENYAMPAIAN_AWARD_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanPENYAMPAIAN_AWARD_MAIN_num"+i+"\" id=\"txtUlasanPENYAMPAIAN_AWARD_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanPENYAMPAIAN_AWARD_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttPENYAMPAIAN_AWARD_MAIN>1 && ttPENYAMPAIAN_AWARD_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_PENYAMPAIAN_AWARD_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_PENYAMPAIAN_AWARD_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat PENYAMPAIAN_AWARD_MAIN'> "+
	" </span>"; 
	}
	if(ttPENYAMPAIAN_AWARD_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_PENYAMPAIAN_AWARD_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_PENYAMPAIAN_AWARD_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat PENYAMPAIAN_AWARD_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#PENYAMPAIAN_AWARD_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(PENYAMPAIAN_AWARD_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN.length > 1 )
	{
	for (t = 0; t < PENYAMPAIAN_AWARD_MAIN_temp1_length; t++)
    {	

    document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN[t].value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1[t].value;
	
    }
	}
	else if(PENYAMPAIAN_AWARD_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN.length < 1 )
	{
	for (t = 0; t < PENYAMPAIAN_AWARD_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN.value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1[0].value;
	}
	}
	else if(PENYAMPAIAN_AWARD_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN.value = document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(PENYAMPAIAN_AWARD_MAIN_temp1_length > 1)
	{
	for (t = 0; t < PENYAMPAIAN_AWARD_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(PENYAMPAIAN_AWARD_MAIN_temp1_length == 1)
	{	
	 document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1.value = "";			
	 var element = document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttPENYAMPAIAN_AWARD_MAIN; i++)
    {		
    if(ttPENYAMPAIAN_AWARD_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN,'30000','txtUlasanPENYAMPAIAN_AWARD_MAIN_check','txtUlasanPENYAMPAIAN_AWARD_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN[i],'30000','txtUlasanPENYAMPAIAN_AWARD_MAIN_check'+i,'txtUlasanPENYAMPAIAN_AWARD_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}






function textarea_SYOR_MAIN(tambahtolak,jenis,index_tolak)
{

var SYOR_MAIN_temp1_length=0;

if(document.${formName}.SYOR_MAIN_temp1 != null)
{

if(document.${formName}.SYOR_MAIN_temp1.length>0)
{
SYOR_MAIN_temp1_length = document.${formName}.SYOR_MAIN_temp1.length;
}
else
{
SYOR_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanSYOR_MAIN!=null)
{

if(document.${formName}.txtUlasanSYOR_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanSYOR_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='SYOR_MAIN_tempX1' name='SYOR_MAIN_tempX1' value= '"+document.${formName}.txtUlasanSYOR_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='SYOR_MAIN_tempX1' name='SYOR_MAIN_tempX1' value= '"+document.${formName}.txtUlasanSYOR_MAIN.value+"'></td></tr>";

}
}




$jquery("#SYOR_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(SYOR_MAIN_temp1_length>0)
{
ttSYOR_MAIN = SYOR_MAIN_temp1_length;
}
else
{
ttSYOR_MAIN = 2;
}
}
if(jenis == "tambah")
{
ttSYOR_MAIN = ttSYOR_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttSYOR_MAIN = ttSYOR_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttSYOR_MAIN; i++)
  {	
  if(ttSYOR_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.SYOR_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.SYOR_MAIN_tempX1[0].value
	
    } 	
	}		
   
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanSYOR_MAIN\" id=\"txtUlasanSYOR_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check','txtUlasanSYOR_MAIN_num','normal','no','ulasan kos-kos akibat SYOR_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanSYOR_MAIN_check','txtUlasanSYOR_MAIN_num','normal','no','ulasan kos-kos akibat SYOR_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check','txtUlasanSYOR_MAIN_num','normal','no','ulasan kos-kos akibat SYOR_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanSYOR_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanSYOR_MAIN_num\" id=\"txtUlasanSYOR_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanSYOR_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_SYOR_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_SYOR_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat SYOR_MAIN'> "+
	      " </span>"; 	
	     if(ttSYOR_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_SYOR_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_SYOR_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat SYOR_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttSYOR_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.SYOR_MAIN_tempX1.value
	
	}	
	else if(ttSYOR_MAIN>2 && i!=(ttSYOR_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.SYOR_MAIN_tempX1[i].value
	
	}	
	else if(ttSYOR_MAIN>1 && i!=(ttSYOR_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.SYOR_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.SYOR_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.SYOR_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttSYOR_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.SYOR_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.SYOR_MAIN_tempX1[0].value;
	
   }
   }		
	if(jenis == "umum")
    {
	if(i==0)
	{
	 temp_value = '$txtSyor1';
	}
	if(i==1)
	{
	 temp_value = '$txtSyor2';
	}
	}
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 3."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanSYOR_MAIN\" id=\"txtUlasanSYOR_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check"+i+"','txtUlasanSYOR_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SYOR_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanSYOR_MAIN_check"+i+"','txtUlasanSYOR_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SYOR_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check"+i+"','txtUlasanSYOR_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SYOR_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanSYOR_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanSYOR_MAIN_num"+i+"\" id=\"txtUlasanSYOR_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanSYOR_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttSYOR_MAIN>1 && ttSYOR_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_SYOR_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_SYOR_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat SYOR_MAIN'> "+
	" </span>"; 
	}
	if(ttSYOR_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_SYOR_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_SYOR_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat SYOR_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#SYOR_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(SYOR_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYOR_MAIN.length > 1 )
	{
	for (t = 0; t < SYOR_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanSYOR_MAIN[t].value = document.${formName}.SYOR_MAIN_temp1[t].value;
	
    }
	}
	else if(SYOR_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYOR_MAIN.length < 1 )
	{
	for (t = 0; t < SYOR_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanSYOR_MAIN.value = document.${formName}.SYOR_MAIN_temp1[0].value;
	}
	}
	else if(SYOR_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanSYOR_MAIN.value = document.${formName}.SYOR_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(SYOR_MAIN_temp1_length > 1)
	{
	for (t = 0; t < SYOR_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.SYOR_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.SYOR_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(SYOR_MAIN_temp1_length == 1)
	{	
	 document.${formName}.SYOR_MAIN_temp1.value = "";			
	 var element = document.${formName}.SYOR_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttSYOR_MAIN; i++)
    {		
    if(ttSYOR_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanSYOR_MAIN,'30000','txtUlasanSYOR_MAIN_check','txtUlasanSYOR_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanSYOR_MAIN[i],'30000','txtUlasanSYOR_MAIN_check'+i,'txtUlasanSYOR_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}






function textarea_kerosakan1()
{
	
    var TUJUAN_MAIN_temp1_length=0;
	if(document.${formName}.TUJUAN_MAIN_temp1 != null)
	{
	if(document.${formName}.TUJUAN_MAIN_temp1.length>0)
	{
	TUJUAN_MAIN_temp1_length = document.${formName}.TUJUAN_MAIN_temp1.length;
	}
	else
	{
	TUJUAN_MAIN_temp1_length = 1;
	}
	}

    if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length > 1 )
	{
	for (t = 0; t < TUJUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.TUJUAN_MAIN_temp1[t].value = document.${formName}.txtUlasanTUJUAN_MAIN[t].value;
	}
	}
	else if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length < 1 )
	{
	for (t = 0; t < TUJUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.TUJUAN_MAIN_temp1[0].value = document.${formName}.txtUlasanTUJUAN_MAIN.value;
    }
	}
	else if(TUJUAN_MAIN_temp1_length == 1)
	{
	document.${formName}.TUJUAN_MAIN_temp1.value = document.${formName}.txtUlasanTUJUAN_MAIN.value;

	}
	
	
	
	var LATARBELAKANG_MAIN_temp1_length=0;
	if(document.${formName}.LATARBELAKANG_MAIN_temp1 != null)
	{
	if(document.${formName}.LATARBELAKANG_MAIN_temp1.length>0)
	{
	LATARBELAKANG_MAIN_temp1_length = document.${formName}.LATARBELAKANG_MAIN_temp1.length;
	}
	else
	{
	LATARBELAKANG_MAIN_temp1_length = 1;
	}
	}

    if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1 )
	{
	for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++)
    {	
    document.${formName}.LATARBELAKANG_MAIN_temp1[t].value = document.${formName}.txtUlasanLATARBELAKANG_MAIN[t].value;
	}
	}
	else if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length < 1 )
	{
	for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++)
    {	
    document.${formName}.LATARBELAKANG_MAIN_temp1[0].value = document.${formName}.txtUlasanLATARBELAKANG_MAIN.value;
    }
	}
	else if(LATARBELAKANG_MAIN_temp1_length == 1)
	{
	document.${formName}.LATARBELAKANG_MAIN_temp1.value = document.${formName}.txtUlasanLATARBELAKANG_MAIN.value;

	}
	
	
	
	var MOHON_AMBIL_TANAH_MAIN_temp1_length=0;
	if(document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1 != null)
	{
	if(document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1.length>0)
	{
	MOHON_AMBIL_TANAH_MAIN_temp1_length = document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1.length;
	}
	else
	{
	MOHON_AMBIL_TANAH_MAIN_temp1_length = 1;
	}
	}
	
	 if(MOHON_AMBIL_TANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN.length > 1 )
	{
	for (t = 0; t < MOHON_AMBIL_TANAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1[t].value = document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN[t].value;
	}
	}
	else if(MOHON_AMBIL_TANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN.length < 1 )
	{
	for (t = 0; t < MOHON_AMBIL_TANAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1[0].value = document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN.value;
    }
	}
	else if(MOHON_AMBIL_TANAH_MAIN_temp1_length == 1)
	{
	document.${formName}.MOHON_AMBIL_TANAH_MAIN_temp1.value = document.${formName}.txtUlasanMOHON_AMBIL_TANAH_MAIN.value;

	}
	
	
	
	
	var PERUNTUKAN_MAIN_temp1_length=0;
	if(document.${formName}.PERUNTUKAN_MAIN_temp1 != null)
	{
	if(document.${formName}.PERUNTUKAN_MAIN_temp1.length>0)
	{
	PERUNTUKAN_MAIN_temp1_length = document.${formName}.PERUNTUKAN_MAIN_temp1.length;
	}
	else
	{
	PERUNTUKAN_MAIN_temp1_length = 1;
	}
	}
	
	 if(PERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERUNTUKAN_MAIN.length > 1 )
	{
	for (t = 0; t < PERUNTUKAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.PERUNTUKAN_MAIN_temp1[t].value = document.${formName}.txtUlasanPERUNTUKAN_MAIN[t].value;
	}
	}
	else if(PERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERUNTUKAN_MAIN.length < 1 )
	{
	for (t = 0; t < PERUNTUKAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.PERUNTUKAN_MAIN_temp1[0].value = document.${formName}.txtUlasanPERUNTUKAN_MAIN.value;
    }
	}
	else if(PERUNTUKAN_MAIN_temp1_length == 1)
	{
	document.${formName}.PERUNTUKAN_MAIN_temp1.value = document.${formName}.txtUlasanPERUNTUKAN_MAIN.value;

	}
	
	
	
	
	var ISTIHAR_PENGAMBILAN_MAIN_temp1_length=0;
	if(document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1 != null)
	{
	if(document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1.length>0)
	{
	ISTIHAR_PENGAMBILAN_MAIN_temp1_length = document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1.length;
	}
	else
	{
	ISTIHAR_PENGAMBILAN_MAIN_temp1_length = 1;
	}
	}
	
	if(ISTIHAR_PENGAMBILAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN.length > 1 )
	{
	for (t = 0; t < ISTIHAR_PENGAMBILAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1[t].value = document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN[t].value;
	}
	}
	else if(ISTIHAR_PENGAMBILAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN.length < 1 )
	{
	for (t = 0; t < ISTIHAR_PENGAMBILAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1[0].value = document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN.value;
    }
	}
	else if(ISTIHAR_PENGAMBILAN_MAIN_temp1_length == 1)
	{
	document.${formName}.ISTIHAR_PENGAMBILAN_MAIN_temp1.value = document.${formName}.txtUlasanISTIHAR_PENGAMBILAN_MAIN.value;

	}
	
	
	
	
	var WASILAN_HAKMILIK_MAIN_temp1_length=0;
	if(document.${formName}.WASILAN_HAKMILIK_MAIN_temp1 != null)
	{
	if(document.${formName}.WASILAN_HAKMILIK_MAIN_temp1.length>0)
	{
	WASILAN_HAKMILIK_MAIN_temp1_length = document.${formName}.WASILAN_HAKMILIK_MAIN_temp1.length;
	}
	else
	{
	WASILAN_HAKMILIK_MAIN_temp1_length = 1;
	}
	}	
	
	
	if(WASILAN_HAKMILIK_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN.length > 1 )
	{
	for (t = 0; t < WASILAN_HAKMILIK_MAIN_temp1_length; t++)
    {	
    document.${formName}.WASILAN_HAKMILIK_MAIN_temp1[t].value = document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN[t].value;
	}
	}
	else if(WASILAN_HAKMILIK_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN.length < 1 )
	{
	for (t = 0; t < WASILAN_HAKMILIK_MAIN_temp1_length; t++)
    {	
    document.${formName}.WASILAN_HAKMILIK_MAIN_temp1[0].value = document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN.value;
    }
	}
	else if(WASILAN_HAKMILIK_MAIN_temp1_length == 1)
	{
	document.${formName}.WASILAN_HAKMILIK_MAIN_temp1.value = document.${formName}.txtUlasanWASILAN_HAKMILIK_MAIN.value;

	}
	
	
	
	
	var LOT_TINGGAL_MAIN_temp1_length=0;
	if(document.${formName}.LOT_TINGGAL_MAIN_temp1 != null)
	{
	if(document.${formName}.LOT_TINGGAL_MAIN_temp1.length>0)
	{
	LOT_TINGGAL_MAIN_temp1_length = document.${formName}.LOT_TINGGAL_MAIN_temp1.length;
	}
	else
	{
	LOT_TINGGAL_MAIN_temp1_length = 1;
	}
	}
	
	if(LOT_TINGGAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLOT_TINGGAL_MAIN.length > 1 )
	{
	for (t = 0; t < LOT_TINGGAL_MAIN_temp1_length; t++)
    {	
    document.${formName}.LOT_TINGGAL_MAIN_temp1[t].value = document.${formName}.txtUlasanLOT_TINGGAL_MAIN[t].value;
	}
	}
	else if(LOT_TINGGAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLOT_TINGGAL_MAIN.length < 1 )
	{
	for (t = 0; t < LOT_TINGGAL_MAIN_temp1_length; t++)
    {	
    document.${formName}.LOT_TINGGAL_MAIN_temp1[0].value = document.${formName}.txtUlasanLOT_TINGGAL_MAIN.value;
    }
	}
	else if(LOT_TINGGAL_MAIN_temp1_length == 1)
	{
	document.${formName}.LOT_TINGGAL_MAIN_temp1.value = document.${formName}.txtUlasanLOT_TINGGAL_MAIN.value;

	}
	
	
	
	
	var PENYAMPAIAN_AWARD_MAIN_temp1_length=0;
	if(document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1 != null)
	{
	if(document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1.length>0)
	{
	PENYAMPAIAN_AWARD_MAIN_temp1_length = document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1.length;
	}
	else
	{
	PENYAMPAIAN_AWARD_MAIN_temp1_length = 1;
	}
	}
	
	if(PENYAMPAIAN_AWARD_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN.length > 1 )
	{
	for (t = 0; t < PENYAMPAIAN_AWARD_MAIN_temp1_length; t++)
    {	
    document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1[t].value = document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN[t].value;
	}
	}
	else if(PENYAMPAIAN_AWARD_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN.length < 1 )
	{
	for (t = 0; t < PENYAMPAIAN_AWARD_MAIN_temp1_length; t++)
    {	
    document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1[0].value = document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN.value;
    }
	}
	else if(PENYAMPAIAN_AWARD_MAIN_temp1_length == 1)
	{
	document.${formName}.PENYAMPAIAN_AWARD_MAIN_temp1.value = document.${formName}.txtUlasanPENYAMPAIAN_AWARD_MAIN.value;

	}
	
	
		
	
	var PERAKUAN_MAIN_temp1_length=0;
	if(document.${formName}.PERAKUAN_MAIN_temp1 != null)
	{
	if(document.${formName}.PERAKUAN_MAIN_temp1.length>0)
	{
	PERAKUAN_MAIN_temp1_length = document.${formName}.PERAKUAN_MAIN_temp1.length;
	}
	else
	{
	PERAKUAN_MAIN_temp1_length = 1;
	}
	}

    if(PERAKUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUAN_MAIN.length > 1 )
	{
	for (t = 0; t < PERAKUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.PERAKUAN_MAIN_temp1[t].value = document.${formName}.txtUlasanPERAKUAN_MAIN[t].value;
	}
	}
	else if(PERAKUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUAN_MAIN.length < 1 )
	{
	for (t = 0; t < PERAKUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.PERAKUAN_MAIN_temp1[0].value = document.${formName}.txtUlasanPERAKUAN_MAIN.value;
    }
	}
	else if(PERAKUAN_MAIN_temp1_length == 1)
	{
	document.${formName}.PERAKUAN_MAIN_temp1.value = document.${formName}.txtUlasanPERAKUAN_MAIN.value;

	}
	
	
	
	
	var SEBABPENARIKAN_MAIN_temp1_length=0;
	if(document.${formName}.SEBABPENARIKAN_MAIN_temp1 != null)
	{
	if(document.${formName}.SEBABPENARIKAN_MAIN_temp1.length>0)
	{
	SEBABPENARIKAN_MAIN_temp1_length = document.${formName}.SEBABPENARIKAN_MAIN_temp1.length;
	}
	else
	{
	SEBABPENARIKAN_MAIN_temp1_length = 1;
	}
	}

    if(SEBABPENARIKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSEBABPENARIKAN_MAIN.length > 1 )
	{
	for (t = 0; t < SEBABPENARIKAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.SEBABPENARIKAN_MAIN_temp1[t].value = document.${formName}.txtUlasanSEBABPENARIKAN_MAIN[t].value;
	}
	}
	else if(SEBABPENARIKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSEBABPENARIKAN_MAIN.length < 1 )
	{
	for (t = 0; t < SEBABPENARIKAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.SEBABPENARIKAN_MAIN_temp1[0].value = document.${formName}.txtUlasanSEBABPENARIKAN_MAIN.value;
    }
	}
	else if(SEBABPENARIKAN_MAIN_temp1_length == 1)
	{
	document.${formName}.SEBABPENARIKAN_MAIN_temp1.value = document.${formName}.txtUlasanSEBABPENARIKAN_MAIN.value;

	}
	
		
		var PENUTUP_MAIN_temp1_length=0;
	if(document.${formName}.PENUTUP_MAIN_temp1 != null)
	{
	if(document.${formName}.PENUTUP_MAIN_temp1.length>0)
	{
	PENUTUP_MAIN_temp1_length = document.${formName}.PENUTUP_MAIN_temp1.length;
	}
	else
	{
	PENUTUP_MAIN_temp1_length = 1;
	}
	}

    if(PENUTUP_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPENUTUP_MAIN.length > 1 )
	{
	for (t = 0; t < PENUTUP_MAIN_temp1_length; t++)
    {	
    document.${formName}.PENUTUP_MAIN_temp1[t].value = document.${formName}.txtUlasanPENUTUP_MAIN[t].value;
	}
	}
	else if(PENUTUP_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPENUTUP_MAIN.length < 1 )
	{
	for (t = 0; t < PENUTUP_MAIN_temp1_length; t++)
    {	
    document.${formName}.PENUTUP_MAIN_temp1[0].value = document.${formName}.txtUlasanPENUTUP_MAIN.value;
    }
	}
	else if(PENUTUP_MAIN_temp1_length == 1)
	{
	document.${formName}.PENUTUP_MAIN_temp1.value = document.${formName}.txtUlasanPENUTUP_MAIN.value;

	}
	
		
	var SOKONGAN_PENGARAH_MAIN_temp1_length=0;
	if(document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1 != null)
	{
	if(document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1.length>0)
	{
	SOKONGAN_PENGARAH_MAIN_temp1_length = document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1.length;
	}
	else
	{
	SOKONGAN_PENGARAH_MAIN_temp1_length = 1;
	}
	}

    if(SOKONGAN_PENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN.length > 1 )
	{
	for (t = 0; t < SOKONGAN_PENGARAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1[t].value = document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN[t].value;
	}
	}
	else if(SOKONGAN_PENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN.length < 1 )
	{
	for (t = 0; t < SOKONGAN_PENGARAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1[0].value = document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN.value;
    }
	}
	else if(SOKONGAN_PENGARAH_MAIN_temp1_length == 1)
	{
	document.${formName}.SOKONGAN_PENGARAH_MAIN_temp1.value = document.${formName}.txtUlasanSOKONGAN_PENGARAH_MAIN.value;

	}	

		
		
		var MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length=0;
	if(document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1 != null)
	{
	if(document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1.length>0)
	{
	MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length = document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1.length;
	}
	else
	{
	MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length = 1;
	}
	}

    if(MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.length > 1 )
	{
	for (t = 0; t < MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1[t].value = document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN[t].value;
	}
	}
	else if(MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.length < 1 )
	{
	for (t = 0; t < MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1[0].value = document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.value;
    }
	}
	else if(MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1_length == 1)
	{
	document.${formName}.MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN_temp1.value = document.${formName}.txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.value;

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





function addslashes(str,element) {
str=str.replace(/\\/g,'\\\\');
str=str.replace(/\'/g,'\\\'');
str=str.replace(/\"/g,'\\"');
str=str.replace(/\0/g,'\\0');
element.value = str;
}


function stripslashes(str,element) {
str=str.replace(/\\'/g,'\'');
str=str.replace(/\\"/g,'"');
str=str.replace(/\\0/g,'\0');
str=str.replace(/\\\\/g,'\\');
element.value = str;
}

</script>