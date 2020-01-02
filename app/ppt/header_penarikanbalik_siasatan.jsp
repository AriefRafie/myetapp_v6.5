<!-- untuk header siasatan -->


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
#set($bil_siasatan = $list_siasatan.BIL_SIASATAN)
#set($id_hakmilik = $list_siasatan.ID_HAKMILIK)
#set($ID_JENISHAKMILIK = $list_siasatan.ID_JENISHAKMILIK)
#set($SIMPANAN = $list_siasatan.SIMPANAN)

#set($no_pt = $list_siasatan.NO_PT)
#set($kod_hakmilik = $list_siasatan.KOD_JENIS_HAKMILIK)
#set($no_hakmilik = $list_siasatan.NO_HAKMILIK)


<!-- untuk keterangan tuan tanah -->

#set($txdMilikTanah = $list_siasatan.TEMPOH_MILIK_TANAH)
#set($txtCaraMilikTanah = $list_siasatan.CARA_MILIK)
#set($txtHargaTanah = $list_siasatan.HARGA_BELI)
#set($txtBebananTanah = $list_siasatan.BEBANAN)
#set($txtKeteranganTuanTanah = $list_siasatan.KETERANGAN_TUAN_TANAH)
#set($txtJenisTanaman = $list_siasatan.JENIS_TANAMAN)
#set($txtJenisBangunan = $list_siasatan.JENIS_BANGUNAN)
#set($sorPecahSempadan = $list_siasatan.FLAG_PECAH_SEMPADAN)
#set($txdPecahSempadan = $list_siasatan.TARIKH_PECAH_SEMPADAN)
#set($sorTukarSyarat = $list_siasatan.FLAG_TUKAR_SYARAT)
#set($txdTukarSyarat = $list_siasatan.TARIKH_TUKAR_SYARAT)
#set($id_siasatan = $list_siasatan.ID_SIASATAN)

<!-- unutk keterangan agensi -->

#set($txtKeteranganAgensi = $list_siasatan.KETERANGAN_AGENSI)
#set($txtKeteranganJurunilai = $list_siasatan.KETERANGAN_JURUNILAI)
#set($txtKeteranganJPPH = $list_siasatan.KETERANGAN_JPPH)
#set($txtKeteranganAkujanji = $list_siasatan.AKUJANJI_AGENSI)



<!-- untuk tuntutan -->

#set($txtTuntutanTuanTanah = $list_siasatan.TUNTUTAN_TUANTNH)
#set($txtLainTuntutan = $list_siasatan.TUNTUTAN_PB_LAIN)
#set($txtPBDaftar = $list_siasatan.TUNTUTAN_PB_BEBANAN)
#set($txtPBXDaftar = $list_siasatan.TUNTUTAN_PB_TDKDAFTAR)

<!-- untuk bantahan -->

#set($txtBantahanLain = $list_siasatan.BANTAHAN_LAIN)
#set($txtBantahanAgensi = $list_siasatan.BANTAHAN_AGENSI)
#set($txtBantahanTuanTanah = $list_siasatan.BANTAHAN_TUANTNH)


<!-- status -->

#set($socPegawaiSiasatan = $list_siasatan.ID_PEGAWAI_SIASATAN)
#set($socStatusSiasatan = $list_siasatan.STATUS_SIASATAN)
#set($txtUlasanSiasatan = $list_siasatan.ULASAN_SIASATAN)


<!-- keputusan -->



#set($socPegawaiSiasatan = $list_siasatan.ID_PEGAWAI_SIASATAN)
#set($socStatusSiasatan = $list_siasatan.STATUS_SIASATAN)
#set($txtUlasanPerintah = $list_siasatan.ULASAN_PERINTAH)
#set($nilai_seunit = $list_siasatan.NILAI_SEUNIT)
#set($socUnit = $list_siasatan.UNIT_TANAH)
#set($txdTarikhPerintah = $list_siasatan.TARIKH_PERINTAH)

<!-- nilaian -->



#set($txtHargaSeunitSO = $list_siasatan.HARGA_SEUNIT_SO)
#set($txtUnitHargaSO = $list_siasatan.UNIT_HARGA_SO)
#set($txtHargaPasaranSO = $list_siasatan.HARGA_PASARAN_SO)
#set($txtPenjejasanSO = $list_siasatan.BAYAR_PENJEJASAN)
#set($txtPecahPisahSO = $list_siasatan.BAYAR_PECAHPISAH)
#set($txtNaikNilaiSO = $list_siasatan.BAYAR_NAIK_NILAISO)
#set($txtHargaSeunitJPPH = $list_siasatan.HARGA_SEUNIT_JPPH)
#set($txtUnitHargaJPPH = $list_siasatan.UNIT_HARGA)
#set($txtHargaPasaranJPPH = $list_siasatan.HARGA_PASARAN)
#set($txtPenjejasanJPPH = $list_siasatan.AMAUN_PENJEJASAN_JPPH)
#set($txtPecahPisahJPPH = $list_siasatan.AMAUN_PECAHPISAH_JPPH)
#set($txtNaikNilaiJPPH = $list_siasatan.NAIK_NILAI_JPPH)
#set($id_tanah = $list_siasatan.ID_TANAH)                            


#end
<fieldset id="maklumat_siasatan">
  <legend>MAKLUMAT SIASATAN</legend>
  
<table width="100%">
  
  <td width="50%" valign="top">
  
  <table width="100%">
  
  
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No. Kes</td>
    <td width="1%">:</td>
    <td width="70%">
    $txtNoKes
   </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Siasatan</td>
    <td>:</td>
    <td>
    $txtNoSiasatan
      </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Status Siasatan</td>
    <td>:</td>
    <td>
    
  
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
     #set($StatusSiasatan="ULANG SIASATAN")    
     #elseif($socStatusSiasatan=="5") 
     #set($StatusSiasatan="SAMBUNG SIASATAN") 
     #elseif($socStatusSiasatan=="7")                             
     #set($StatusSiasatan="TANGGUH SIASATAN")    
     #elseif($socStatusSiasatan=="6")                             
     #set($StatusSiasatan="SELESAI")                
     #end 
     #end
     
     $StatusSiasatan     
               
     </td>
  </tr>
  
  <tr>
    <td>&nbsp;</td>
    <td>Bil. Siasatan</td>
    <td>:</td>
    <td>$!bil_siasatan     </td>
  </tr>
  </table>
  </td>
  
   <td width="50%" valign="top">
  
  <table width="100%">
  
  
   <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Tarikh Siasatan</td>
    <td width="1%">:</td>
    <td width="70%">
    
     $!txdTarikhSiasatan   </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Masa Siasatan</td>
    <td>:</td>
    <td>
                                 
                                  #set($waktu = "")
                                  #if( $jeniswaktu == 1)
                                  #set($waktu = "PAGI")  
                                  #elseif($jeniswaktu == 2)
                                  #set($waktu = "TENGAHARI")                                   
                                  #elseif($jeniswaktu == 3)
                                  #set($waktu = "PETANG")      
                                  #elseif($jeniswaktu == 4)
                                  #set($waktu = "MALAM")                                   
                                  #else
                                  #set($waktu = "")
                                  #end             
                                  
                                  $!txtMasaSiasatan $waktu
                 
                                               </td>
  </tr>
  
   <tr>
    <td>&nbsp;</td>
    <td>No Hakmilik</td>
    <td>:</td>
    <td>
    
$kod_hakmilik  $no_hakmilik
     </td>
  </tr>
  
   <tr>
    <td>&nbsp;</td>
    <td>No Lot</td>
    <td>:</td>
    <td>
    
$!no_pt
     </td>
  </tr>
  
  </table>
  </td>
</table>
  </fieldset> 
  
 #foreach($list in $maklumat_hakmilik)

  
  <fieldset style="display:none">
  <legend>MAKLUMAT HAKMILIK</legend>
   <table width="100%" id="$list.BIL"  > 
 <tr>
<td width="50%" valign="top">
<table width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No Lot/PT</td>
    <td width="1%">:</td>
    <td width="70%">$list.NO_PT</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Negeri</td>
    <td width="1%">:</td>
    <td width="70%">$list.NAMA_NEGERI</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Daerah</td>
    <td>:</td>
    <td>$list.NAMA_DAERAH</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Mukim</td>
    <td>:</td>
    <td>$list.NAMA_MUKIM</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jenis Hakmilik</td>
    <td>:</td>
    <td>$list.JENIS_HAKMILIK</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Kod Hakmilik</td>
    <td>:</td>
    <td>$list.KOD_JENIS_HAKMILIK</td>
  </tr>
  </table>
  </td>
  
<td width="50%" valign="top">
<table width="100%">
<tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No Hakmilik</td>
    <td width="1%">:</td>
    <td width="70%">$list.NO_HAKMILIK</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Luas Lot</td>
    <td>:</td>
    <td>$list.LUAS_LOT</td>
  </tr>
  <tr  style="display:none">
    <td>&nbsp;</td>
    <td>Luas Diambil</td>
    <td>:</td>
    <td>$list.LUAS_AMBIL</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Luas Ditarik Balik</td>
    <td>:</td>
    <td>$list.LUAS_LOT_TARIK</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Pihak Kepentingan</td>
    <td>:</td>
    <td>
        #set($count = 0) 
                      #foreach($list1 in $senarai_pihak_penting)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )     
                      #set($count=$count + 1) 
                      #end
                      #end
                      
                      #set($count_total = 0) 
                      
                      #if($count == 1)
                      #foreach($list1 in $senarai_pihak_penting)
                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )
                      $list1.NAMA_PB
                      #end
                      #end
                      #elseif($count > 1)
                      
                      
                      
                      <select name=""  class="autoselect" >
                      #foreach($list1 in $senarai_pihak_penting)                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )  
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
                      
                      #end   
                       </td>
  </tr>

</table>
  </td>
  </tr>
  
  
 
</table>          
  </fieldset> #end
 