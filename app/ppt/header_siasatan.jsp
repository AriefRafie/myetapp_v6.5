<style type="text/css">
<!--
.style2 {font-weight: bold}
.font1 {
	color: #FFFFFF;
	font-style: italic;
}
.font2 {
	color: #0000FF;
	font-style: italic;
}
-->
</style>

<!-- untuk header siasatan -->
#foreach($list_siasatan in $maklumat_siasatan)
#set($id_luas_convert = $list_siasatan.ID_UNITLUASAMBIL_CONVERT)
#set($luas_convert = $list_siasatan.LUAS_AMBIL)
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
#set($no_pt = $list_siasatan.NO_PT)
#set($kod_hakmilik = $list_siasatan.KOD_JENIS_HAKMILIK)
#set($no_hakmilik = $list_siasatan.NO_HAKMILIK)
#set($nama_negeri = $list_siasatan.NAMA_NEGERI)
#set($nama_daerah = $list_siasatan.NAMA_DAERAH)
#set($nama_mukim = $list_siasatan.NAMA_MUKIM)
#set($keterangan = $list_siasatan.KETERANGAN)

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

<!-- tambahan keluasan muktamad  20042012 -->
#set($txtLuasMuktamad = $list_siasatan.LUAS_MUKTAMAD)
#set($sorDropdownUnitMuktamad = $list_siasatan.ID_UNIT_LUAS_MUKTAMAD)

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
#set($txtStrukturBangunan = $list_siasatan.STRUKTUR_BANGUNAN)
#set($id_tanah = $list_siasatan.ID_TANAH)                            

#end

#if($flagJPPH != 'yes')
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
    $!txtNoKes
   </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Siasatan</td>
    <td>:</td>
    <td>
    $!txtNoSiasatan
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
     
     $!StatusSiasatan     
               
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
     	$!txdTarikhSiasatan   
     </td>
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
$!kod_hakmilik  $!no_hakmilik
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
#end

#set($no_pt = $list_siasatan.NO_PT)
#set($kod_hakmilik = $list_siasatan.KOD_JENIS_HAKMILIK)
#set($no_hakmilik = $list_siasatan.NO_HAKMILIK)
#set($nama_negeri = $list_siasatan.NAMA_NEGERI)
#set($nama_daerah = $list_siasatan.NAMA_DAERAH)
#set($nama_mukim = $list_siasatan.NAMA_MUKIM)
#set($keterangan = $list_siasatan.KETERANGAN)
#if($flagJPPH == 'yes')
<fieldset id="maklumat_tanah">
  <legend>MAKLUMAT TANAH</legend>
<table width="100%">
<!--   <td width="50%" valign="top"> -->
<!--   <table width="100%"> -->
  
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Negeri</td>
    <td width="1%">:</td>
    <td width="70%">
    	$!nama_negeri
   </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Daerah</td>
    <td>:</td>
    <td>
    	$!nama_daerah
      </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Mukim/Pekan/Bandar</td>
    <td>:</td>
    <td>$!nama_mukim     </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Hakmilik</td>
    <td>:</td>
    <td>$!kod_hakmilik $!no_hakmilik</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Lot</td>
    <td>:</td>
    <td>$!no_pt $!keterangan</td>
  </tr>  
  </table>
</fieldset> 
#end  
  
<script>
function open_new_window(jenis_popup) 
{
 var width  = 0;
 var height = 0;
if(jenis_popup == "1")
{
  width  = 300;
  height = 300;
}
if(jenis_popup == "3" || jenis_popup == "4")
{
  width  = 300;
  height = 200;
}
if(jenis_popup == "2")
{
 var width  = 300;
 var height = 300;
}

 var left   = '';
 var top    = '';
 var params = '';

if(jenis_popup == "4")
{
 left   = (screen.width  - width)/4;
 top    = (screen.height - height)/4;
 params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=yes';
 params += ', menubar=yes';
 params += ', toolbar=yes';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
 new_window = open("","title",params);
 new_window.document.open();
 new_window.document.write("<html><title>JavaScript New Window</title>");
 new_window.document.write("<body bgcolor=\"#FFFFFF\">");
}
else
{
 left   = (screen.width  - width)/2;
 top    = (screen.height - height)/2;
 params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=yes';
 params += ', menubar=yes';
 params += ', toolbar=yes';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
 new_window = open("","title",params);
 new_window.document.open();
 new_window.document.write("<html><title>JavaScript New Window</title>");
 new_window.document.write("<body bgcolor=\"#FFFFFF\">");
} 

if(jenis_popup == "1")
{
new_window.document.write("Sila klik pada kotak pilihan borang yang disediakan. Tujuan fungsi 'flag/checkbox' pilihan borang adalah untuk memudahkan pengguna untuk menetapkan/menyenaraikan setiap pihak berkepentingan di borang C,D,E,F,G,H dan K. Tanda '/' pada kotak pilihan borang menunjukkan pihak berkepentingan tersebut telah dipilih dan sebalikynya. Setelah pilihan borang telah dipilih,sila klik butang 'Simpan Pilihan Borang' untuk menyimpan rekod.");
}

if(jenis_popup == "4")
{
new_window.document.write("Fungsi 'Pengiraan Jumlah Pampasan Tanah' bertujuan untuk membuat pengiraan pampasan tanah secara automatik merujuk kepada bahagian/syer pihak berkepentingan, harga seunit dan luas tanah yang dikehendaki.");
}


if(jenis_popup == "2")
{
new_window.document.write("<table width = '100%' >")
new_window.document.write("<tr>")
new_window.document.write("<td colspan = '3'>")
new_window.document.write("Tujuan medan 'Keterangan Jenis PB' adalah untuk memudahkan pengguna untuk menyatakan sebarang keterangan merujuk kepada jenis pihak berkepentingan yang telah dipilih. Contoh : ");
new_window.document.write("</td>")
new_window.document.write("</tr>")
new_window.document.write("</table>")
new_window.document.write("<table width = '100%'  >")
new_window.document.write("<tr>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Jenis Pihak Berkepentingan");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write(":");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Pemegang Amanah");
new_window.document.write("</td>")
new_window.document.write("</tr>")
new_window.document.write("<tr>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Keterangan Jenis PB");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write(":");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Pemegang Amanah Kepada Ali bin Abu");
new_window.document.write("</td>")
new_window.document.write("</tr>")

new_window.document.write("</table>")
}

if(jenis_popup == "3")
{
new_window.document.write("Skrin 'Pihak Berkepentingan' adalah skrin baru yang ditempatkan di Urusan Siasatan & Perintah Seksyen 8. Tujuan skrin ini adalah untuk memudahkan pengguna untuk menambah, menghapus, mengemaskini dan menetapkan pilihan borang pada mana-mana  pihak bekepentingan.");
}

new_window.document.write("<br>");

new_window.document.write("</body></html>");
new_window.document.close(); 

}

function close_window() 
{
new_window.close();
}

</script>
  