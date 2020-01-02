
#set($id_mmk = "")
#set($STATUS_SEMAKAN = "")



#set($txtTujuan = "Tujuan kertas ini disediakan ialah untuk mendapatkan pertimbangan Yang Amat Berhormat Menteri Besar, Negeri Sembilan Darul Khusus bagi menarik balik pengambilan tanah "+$sorJenisPembatalan1+" "+$no_lot_mmk+" "+$nama_mukim_mukim+", Daerah "+$nama_daerah1+" seluas lebih kurang "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar) mengikut seksyen 35(1) Akta Pengambilan Tanah 1960 di bawah perwakilan kuasa yang diisytiharkan melalui Warta Kerajaan Negeri Sembilan "+ $no_warta +"  bertarikh "+ $tarikh_warta +". Kawasan yang dimaksudkan ialah seperti bertanda merah dalam pelan di bil.(   ) dlm."+$no_fail+".  ")



#set($txtLatarbelakang= "Yang Amat Berhormat Menteri Besar Negeri Sembilan telah meluluskan permohonan pengambilan tanah "+$sorJenisPembatalan1+" "+$no_lot_mmk+" "+$nama_mukim_mukim+", Daerah "+$nama_daerah1+" seluas lebih kurang "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar) untuk "+$tujuan+" melalui Kertas Permohonan Tanah Bil. ...... pada ............ . Cadangan pengambilan tanah-tanah tersebut telah diisytiharkan di bawah seksyen 8 Akta Pengambilan Tanah 1960 melalui Warta Kerajaan Negeri Sembilan "+ $no_warta +"  bertarikh "+ $tarikh_warta +".  ")

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


#set($txtSyor1 = "ditarik balik pengambilan tanah "+$sorJenisPembatalan1+" "+$no_lot_mmk+" "+$nama_mukim_mukim+", Daerah "+$nama_daerah1+" seluas lebih kurang "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar) mengikut seksyen 35(1) Akta Pengambilan Tanah 1960,")

#set($txtSyor2 = "diisytiharkan penarikan balik pengambilan tanah tersebut di bawah seksyen 35(1A) akta yang sama ; dan")

#set($txtSyor3 = "dikehendaki pihak Kementerian Kemajuan Luar Bandar dan Wilayah Malaysia seperti mana peruntukan di bawah seksyen 35(2A) untuk membayar kepada orang-orang yang berkepentingan semua kos yang ditanggung beban oleh mereka oleh sebab atau akibat daripada prosiding pengambilan tersebut sekiranya ada.")


#set($txtUlasanPengarah1 = "Pengarah Tanah dan Galian Negeri Sembilan Darul Khusus bersetuju / tidak bersetuju dengan syor Pentadbir Tanah "+$nama_daerah1+" seperti di perenggan 3.1. di atas.")


#set($txtKeputusan1= "Kertas bil.       permohonan penarikan balik pengambilan tanah "+$sorJenisPembatalan1+" "+$no_lot_mmk+" "+$nama_mukim_mukim+", Daerah "+$nama_daerah1+" seluas lebih kurang "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar) untuk "+$tujuan+" seperti bertanda merah dalam pelan di bil.(   ) dlm."+$no_fail+" dikemukakan untuk pertimbangan Yang Amat Berhormat Menteri Besar, Negeri Sembilan Darul Khusus.  ")



#set($txtAsasPertimbangan = "")
#set($txtNilaiAtasTanah = "Adalah disyorkan supaya nilaian ke atas tanah yang akan diambil ini 
dirujuk kepada penilaian kompeten.")
#set($txtSebabPenarikan= "Sebab-sebab penarikan balik adalah "+$!maklumat_penarikanmmk)





#set($txtKesimpulan= "")
#set($txtUlasanPengarah= "")

#set($txtTajuk= "PERMOHONAN PENARIKAN BALIK PENGAMBILAN TANAH "+$sorJenisPembatalan+" "+$no_lot_mmk+" "+$nama_mukim_mukim+", DAERAH "+$nama_daerah+" SELUAS LEBIHKURANG "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar) UNTUK "+$tujuan_upper+". ")


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
              <td width="4%"  valign="top">&nbsp;</td>
              <td width="20%" valign="top"> <strong>TAJUK </strong></td>
              <td width="1%" valign="top">:</td>
     
        <td width="4%" valign="top">
    </td>
        <td width="71%" valign="top">
         
          
        
          <textarea   style="text-transform:uppercase;" name="txtTajuk" id="txtTajuk" cols="80"   rows="9"        
         onBlur="check_length(this,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan');this.value=this.value.toUpperCase()"  
         onKeyup="check_length(this,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan');this.value=this.value.toUpperCase()" 
         onKeydown="check_length(this,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan');this.value=this.value.toUpperCase()"                    
          $readonlymode class = "$disabledmode" 
        >$txtTajuk</textarea>       
       #if($readmode == "edit")           
       <div><span id="txtTajuk_num" style="color:blue;" ></span><span> Baki Aksara</span>       </div>
         #else
         <input name="txtTajuk_num" id="txtTajuk_num" size="3" value="30000"  style=" display:none" > 
         #end
  <div id="txtTajuk_check" class="alert_msg" ></div>       
         
    </td>
</tr>
</table>







<table width="100%" >
            <tr  >
              <td width="4%"  valign="top"><strong>1.</strong></td>
              <td width="20%" valign="top"> <strong>TUJUAN</strong></td>
              <td width="1%" valign="top">:</td>
    
        <td width="75%" valign="top">
       
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
               
        <td width="75%"></td>
  </tr>
         
         <tr>
              <td valign="top"><strong>2.</strong></td>
              <td valign="top"><strong>PERIHAL AGENSI PEMOHON</strong></td>
              <td valign="top">:</td>
    
               
        <td width="75%">
    #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "LATARBELAKANG" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="LATARBELAKANG_MAIN_temp1"  id="LATARBELAKANG_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="LATARBELAKANG_MAIN"></span>           
   <div id="LATARBELAKANG_MAIN_temp"></div>        </td>
  </tr>
                  
                  
                  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td width="75%"></td>
  </tr>
            
               <tr>
              <td valign="top"><strong>3.</strong></td>
              <td valign="top"><strong>LATAR BELAKANG PROJEK</strong></td>
              <td valign="top">:</td>
               
              <td width="75%">
        
         #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "PERIHALTANAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="PERIHALTANAH_MAIN_temp1"  id="PERIHALTANAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="PERIHALTANAH_MAIN"></span>           
   <div id="PERIHALTANAH_MAIN_temp"></div>        </td>
  </tr>
  
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td width="75%"></td>
  </tr>
            
               <tr>
              <td valign="top"><strong>4.</strong></td>
              <td valign="top"><strong>PERUNTUKAN PENGAMBILAN TANAH</strong></td>
              <td valign="top">:</td>
               
              <td width="75%"> 
              
              #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "LAPORANTEKNIKAL" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="LAPORANTEKNIKAL_MAIN_temp1"  id="LAPORANTEKNIKAL_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="LAPORANTEKNIKAL_MAIN"></span>           
   <div id="LAPORANTEKNIKAL_MAIN_temp"></div>
              
              
              
               </td>
  </tr>
  
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td width="75%"></td>
  </tr>
            
               <tr>
              <td valign="top"><strong>5.</strong></td>
              <td valign="top"><strong>PERIHAL TANAH-TANAH</strong></td>
              <td valign="top">:</td>
               
              <td width="75%"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "KEPUTUSAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="KEPUTUSAN_MAIN_temp1"  id="KEPUTUSAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="KEPUTUSAN_MAIN"></span>           
   <div id="KEPUTUSAN_MAIN_temp"></div> </td>
  </tr>
  <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td width="75%"></td>
  </tr>
  <tr>
    <td valign="top"><strong>6.</strong></td>
              <td valign="top"><strong>NAMA-NAMA TUAN TANAH</strong></td>
              <td valign="top">:</td>
               
    <td width="75%">#if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "KESIMPULAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="KESIMPULAN_MAIN_temp1"  id="KESIMPULAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="KESIMPULAN_MAIN"></span>           
   <div id="KESIMPULAN_MAIN_temp"></div> </td>
  </tr>
            
               <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
               
        <td width="75%"></td>
  </tr>
            <tr>
              <td valign="top"><strong>7.</strong></td>
              <td valign="top"><strong>PERAKUAN JAWATANKUASA HAL EHWAL TANAH</strong></td>
              <td valign="top">:</td>
              
        <td width="75%" valign="top">  #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "SYOR" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="SYOR_MAIN_temp1"  id="SYOR_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="SYOR_MAIN"></span>           
   <div id="SYOR_MAIN_temp"></div>  </td>
         </tr>
           
            
            <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
              
        <td width="75%" valign="top">             </td>
         </tr>
         
         <tr>
              <td valign="top"><strong>8.</strong></td>
              <td valign="top"><strong>ASAS-ASAS PERTIMBANGAN</strong></td>
              <td valign="top">:</td>
              
        <td width="75%" valign="top"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "PERAKUAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="PERAKUAN_MAIN_temp1"  id="PERAKUAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="PERAKUAN_MAIN"></span>           
   <div id="PERAKUAN_MAIN_temp"></div> </td>
         </tr>
           
            
            <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
              
        <td width="75%" valign="top">             </td>
         </tr>
         
         <tr>
              <td valign="top"><strong>9.</strong></td>
              <td valign="top"><strong>PENGESYORAN PENTADBIR TANAH JAJAHAN</strong></td>
              <td valign="top">:</td>
              
        <td width="75%" valign="top"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "SYORJAJAHAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="SYORJAJAHAN_MAIN_temp1"  id="SYORJAJAHAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="SYORJAJAHAN_MAIN"></span>           
   <div id="SYORJAJAHAN_MAIN_temp"></div> </td>
         </tr>
           
            
            <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
              
        <td width="75%" valign="top">             </td>
         </tr>
         
         
         <tr>
              <td valign="top"><strong>10.</strong></td>
              <td valign="top"><strong>ULASAN PENGARAH TANAH DAN GALIAN NEGERI KELANTAN</strong></td>
              <td valign="top">:</td>
              
        <td width="75%" valign="top"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "ULASANPTG" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="ULASANPTG_MAIN_temp1"  id="ULASANPTG_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="ULASANPTG_MAIN"></span>           
   <div id="ULASANPTG_MAIN_temp"></div> </td>
         </tr>
           
            
            <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
              
        <td width="75%" valign="top">             </td>
         </tr>
         
         <tr>
              <td valign="top"><strong>11.</strong></td>
              <td valign="top"><strong>KEPUTUSAN</strong></td>
              <td valign="top">:</td>
              
        <td width="75%" valign="top"> #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "KEPUTUSAN_MMK" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="KEPUTUSAN_MMK_MAIN_temp1"  id="KEPUTUSAN_MMK_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="KEPUTUSAN_MMK_MAIN"></span>           
   <div id="KEPUTUSAN_MMK_MAIN_temp"></div> </td>
         </tr>
           
            
            <tr>
              <td valign="top">&nbsp;</td>
              <td valign="top"></td>
              <td valign="top"></td>
              
        <td width="75%" valign="top">             </td>
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

window.onload = submitForm,textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_SYOR_MAIN('tambahtolak','umum',''),check_length(document.${formName}.txtTajuk,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan'),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum',''),textarea_KESIMPULAN_MAIN('tambahtolak','umum',''),textarea_LAPORANTEKNIKAL_MAIN('tambahtolak','umum',''),textarea_PERAKUAN_MAIN('tambahtolak','umum',''),textarea_SYORJAJAHAN_MAIN('tambahtolak','umum',''),textarea_ULASANPTG_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MMK_MAIN('tambahtolak','umum','');

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
   /*
    if(jenis == "umum")
    {
	temp_value = '$txtTujuan';
	}	
   */
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
  /* 
     if(jenis == "umum")
    {
	temp_value = '$txtLatarbelakang';
	}	
   */
   
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


function textarea_ULASAN_PENGARAH_MAIN(tambahtolak,jenis,index_tolak)
{

var ULASAN_PENGARAH_MAIN_temp1_length=0;

if(document.${formName}.ULASAN_PENGARAH_MAIN_temp1 != null)
{

if(document.${formName}.ULASAN_PENGARAH_MAIN_temp1.length>0)
{
ULASAN_PENGARAH_MAIN_temp1_length = document.${formName}.ULASAN_PENGARAH_MAIN_temp1.length;
}
else
{
ULASAN_PENGARAH_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanULASAN_PENGARAH_MAIN!=null)
{

if(document.${formName}.txtUlasanULASAN_PENGARAH_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanULASAN_PENGARAH_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='ULASAN_PENGARAH_MAIN_tempX1' name='ULASAN_PENGARAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASAN_PENGARAH_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='ULASAN_PENGARAH_MAIN_tempX1' name='ULASAN_PENGARAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASAN_PENGARAH_MAIN.value+"'></td></tr>";

}
}




$jquery("#ULASAN_PENGARAH_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(ULASAN_PENGARAH_MAIN_temp1_length>0)
{
ttULASAN_PENGARAH_MAIN = ULASAN_PENGARAH_MAIN_temp1_length;
}
else
{
ttULASAN_PENGARAH_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttULASAN_PENGARAH_MAIN = ttULASAN_PENGARAH_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttULASAN_PENGARAH_MAIN = ttULASAN_PENGARAH_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttULASAN_PENGARAH_MAIN; i++)
  {	
  if(ttULASAN_PENGARAH_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.ULASAN_PENGARAH_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.ULASAN_PENGARAH_MAIN_tempX1[0].value
	
    } 	
	}		
   
     if(jenis == "umum")
    {
	temp_value = '$txtUlasanPengarah1';
	}	
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>5.1 </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanULASAN_PENGARAH_MAIN\" id=\"txtUlasanULASAN_PENGARAH_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanULASAN_PENGARAH_MAIN_check','txtUlasanULASAN_PENGARAH_MAIN_num','normal','no','ulasan kos-kos akibat ULASAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanULASAN_PENGARAH_MAIN_check','txtUlasanULASAN_PENGARAH_MAIN_num','normal','no','ulasan kos-kos akibat ULASAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanULASAN_PENGARAH_MAIN_check','txtUlasanULASAN_PENGARAH_MAIN_num','normal','no','ulasan kos-kos akibat ULASAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanULASAN_PENGARAH_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanULASAN_PENGARAH_MAIN_num\" id=\"txtUlasanULASAN_PENGARAH_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanULASAN_PENGARAH_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_ULASAN_PENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_ULASAN_PENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat ULASAN_PENGARAH_MAIN'> "+
	      " </span>"; 	
	     if(ttULASAN_PENGARAH_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_ULASAN_PENGARAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_ULASAN_PENGARAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat ULASAN_PENGARAH_MAIN' > "+
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
	if(ttULASAN_PENGARAH_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.ULASAN_PENGARAH_MAIN_tempX1.value
	
	}	
	else if(ttULASAN_PENGARAH_MAIN>2 && i!=(ttULASAN_PENGARAH_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.ULASAN_PENGARAH_MAIN_tempX1[i].value
	
	}	
	else if(ttULASAN_PENGARAH_MAIN>1 && i!=(ttULASAN_PENGARAH_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.ULASAN_PENGARAH_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.ULASAN_PENGARAH_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.ULASAN_PENGARAH_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttULASAN_PENGARAH_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.ULASAN_PENGARAH_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.ULASAN_PENGARAH_MAIN_tempX1[0].value;
	
   }
   }	
   
   
 
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 5."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanULASAN_PENGARAH_MAIN\" id=\"txtUlasanULASAN_PENGARAH_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanULASAN_PENGARAH_MAIN_check"+i+"','txtUlasanULASAN_PENGARAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat ULASAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanULASAN_PENGARAH_MAIN_check"+i+"','txtUlasanULASAN_PENGARAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat ULASAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanULASAN_PENGARAH_MAIN_check"+i+"','txtUlasanULASAN_PENGARAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat ULASAN_PENGARAH_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanULASAN_PENGARAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanULASAN_PENGARAH_MAIN_num"+i+"\" id=\"txtUlasanULASAN_PENGARAH_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanULASAN_PENGARAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttULASAN_PENGARAH_MAIN>1 && ttULASAN_PENGARAH_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_ULASAN_PENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_ULASAN_PENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat ULASAN_PENGARAH_MAIN'> "+
	" </span>"; 
	}
	if(ttULASAN_PENGARAH_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_ULASAN_PENGARAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_ULASAN_PENGARAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat ULASAN_PENGARAH_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#ULASAN_PENGARAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(ULASAN_PENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASAN_PENGARAH_MAIN.length > 1 )
	{
	for (t = 0; t < ULASAN_PENGARAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanULASAN_PENGARAH_MAIN[t].value = document.${formName}.ULASAN_PENGARAH_MAIN_temp1[t].value;
	
    }
	}
	else if(ULASAN_PENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASAN_PENGARAH_MAIN.length < 1 )
	{
	for (t = 0; t < ULASAN_PENGARAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanULASAN_PENGARAH_MAIN.value = document.${formName}.ULASAN_PENGARAH_MAIN_temp1[0].value;
	}
	}
	else if(ULASAN_PENGARAH_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanULASAN_PENGARAH_MAIN.value = document.${formName}.ULASAN_PENGARAH_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(ULASAN_PENGARAH_MAIN_temp1_length > 1)
	{
	for (t = 0; t < ULASAN_PENGARAH_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.ULASAN_PENGARAH_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.ULASAN_PENGARAH_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(ULASAN_PENGARAH_MAIN_temp1_length == 1)
	{	
	 document.${formName}.ULASAN_PENGARAH_MAIN_temp1.value = "";			
	 var element = document.${formName}.ULASAN_PENGARAH_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttULASAN_PENGARAH_MAIN; i++)
    {		
    if(ttULASAN_PENGARAH_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanULASAN_PENGARAH_MAIN,'30000','txtUlasanULASAN_PENGARAH_MAIN_check','txtUlasanULASAN_PENGARAH_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanULASAN_PENGARAH_MAIN[i],'30000','txtUlasanULASAN_PENGARAH_MAIN_check'+i,'txtUlasanULASAN_PENGARAH_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}





function textarea_KEPUTUSAN_MAIN(tambahtolak,jenis,index_tolak)
{

var KEPUTUSAN_MAIN_temp1_length=0;

if(document.${formName}.KEPUTUSAN_MAIN_temp1 != null)
{

if(document.${formName}.KEPUTUSAN_MAIN_temp1.length>0)
{
KEPUTUSAN_MAIN_temp1_length = document.${formName}.KEPUTUSAN_MAIN_temp1.length;
}
else
{
KEPUTUSAN_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanKEPUTUSAN_MAIN!=null)
{

if(document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanKEPUTUSAN_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='KEPUTUSAN_MAIN_tempX1' name='KEPUTUSAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEPUTUSAN_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='KEPUTUSAN_MAIN_tempX1' name='KEPUTUSAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEPUTUSAN_MAIN.value+"'></td></tr>";

}
}




$jquery("#KEPUTUSAN_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(KEPUTUSAN_MAIN_temp1_length>0)
{
ttKEPUTUSAN_MAIN = KEPUTUSAN_MAIN_temp1_length;
}
else
{
ttKEPUTUSAN_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttKEPUTUSAN_MAIN = ttKEPUTUSAN_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttKEPUTUSAN_MAIN = ttKEPUTUSAN_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttKEPUTUSAN_MAIN; i++)
  {	
  if(ttKEPUTUSAN_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[0].value
	
    } 	
	}		
   
     if(jenis == "umum")
    {
//	temp_value = '$txtSebabPembatalan';
	}	
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'> </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanKEPUTUSAN_MAIN_num\" id=\"txtUlasanKEPUTUSAN_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanKEPUTUSAN_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat KEPUTUSAN_MAIN'> "+
	      " </span>"; 	
	     if(ttKEPUTUSAN_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat KEPUTUSAN_MAIN' > "+
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
	if(ttKEPUTUSAN_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1.value
	
	}	
	else if(ttKEPUTUSAN_MAIN>2 && i!=(ttKEPUTUSAN_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i].value
	
	}	
	else if(ttKEPUTUSAN_MAIN>1 && i!=(ttKEPUTUSAN_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttKEPUTUSAN_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[0].value;
	
   }
   }	
   
   
 
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 5."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanKEPUTUSAN_MAIN_num"+i+"\" id=\"txtUlasanKEPUTUSAN_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanKEPUTUSAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttKEPUTUSAN_MAIN>1 && ttKEPUTUSAN_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat KEPUTUSAN_MAIN'> "+
	" </span>"; 
	}
	if(ttKEPUTUSAN_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat KEPUTUSAN_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#KEPUTUSAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1 )
	{
	for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanKEPUTUSAN_MAIN[t].value = document.${formName}.KEPUTUSAN_MAIN_temp1[t].value;
	
    }
	}
	else if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length < 1 )
	{
	for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanKEPUTUSAN_MAIN.value = document.${formName}.KEPUTUSAN_MAIN_temp1[0].value;
	}
	}
	else if(KEPUTUSAN_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanKEPUTUSAN_MAIN.value = document.${formName}.KEPUTUSAN_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(KEPUTUSAN_MAIN_temp1_length > 1)
	{
	for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.KEPUTUSAN_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.KEPUTUSAN_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(KEPUTUSAN_MAIN_temp1_length == 1)
	{	
	 document.${formName}.KEPUTUSAN_MAIN_temp1.value = "";			
	 var element = document.${formName}.KEPUTUSAN_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttKEPUTUSAN_MAIN; i++)
    {		
    if(ttKEPUTUSAN_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanKEPUTUSAN_MAIN,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanKEPUTUSAN_MAIN[i],'30000','txtUlasanKEPUTUSAN_MAIN_check'+i,'txtUlasanKEPUTUSAN_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
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
   
   
 
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 2."+(i+1)+"  </td>"+
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
ttSYOR_MAIN = 1;
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
	
	
	/*if(jenis == "umum")
    {
	if(i==0)
	{
	 temp_value = '$txtSyor1';
	}
	if(i==1)
	{
	 temp_value = '$txtSyor2';
	}
	if(i==2)
	{
	 temp_value = '$txtSyor3';
	}
	}*/
	

	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 7."+(i+1)	
	
	+"  </td>"+
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




function textarea_PERIHALTANAH_MAIN(tambahtolak,jenis,index_tolak)
{

var PERIHALTANAH_MAIN_temp1_length=0;

if(document.${formName}.PERIHALTANAH_MAIN_temp1 != null)
{

if(document.${formName}.PERIHALTANAH_MAIN_temp1.length>0)
{
PERIHALTANAH_MAIN_temp1_length = document.${formName}.PERIHALTANAH_MAIN_temp1.length;
}
else
{
PERIHALTANAH_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanPERIHALTANAH_MAIN!=null)
{

if(document.${formName}.txtUlasanPERIHALTANAH_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanPERIHALTANAH_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='PERIHALTANAH_MAIN_tempX1' name='PERIHALTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALTANAH_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='PERIHALTANAH_MAIN_tempX1' name='PERIHALTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALTANAH_MAIN.value+"'></td></tr>";

}
}




$jquery("#PERIHALTANAH_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(PERIHALTANAH_MAIN_temp1_length>0)
{
ttPERIHALTANAH_MAIN = PERIHALTANAH_MAIN_temp1_length;
}
else
{
ttPERIHALTANAH_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttPERIHALTANAH_MAIN = ttPERIHALTANAH_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttPERIHALTANAH_MAIN = ttPERIHALTANAH_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttPERIHALTANAH_MAIN; i++)
  {	
  if(ttPERIHALTANAH_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[0].value
	
    } 	
	}		
   
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','ulasan kos-kos akibat PERIHALTANAH_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','ulasan kos-kos akibat PERIHALTANAH_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','ulasan kos-kos akibat PERIHALTANAH_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanPERIHALTANAH_MAIN_num\" id=\"txtUlasanPERIHALTANAH_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanPERIHALTANAH_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_PERIHALTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_PERIHALTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat PERIHALTANAH_MAIN'> "+
	      " </span>"; 	
	     if(ttPERIHALTANAH_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_PERIHALTANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_PERIHALTANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat PERIHALTANAH_MAIN' > "+
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
	if(ttPERIHALTANAH_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1.value
	
	}	
	else if(ttPERIHALTANAH_MAIN>2 && i!=(ttPERIHALTANAH_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[i].value
	
	}	
	else if(ttPERIHALTANAH_MAIN>1 && i!=(ttPERIHALTANAH_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttPERIHALTANAH_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[1].value;	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[0].value;	
   }
   
   }		
	
	
	/*if(jenis == "umum")
    {
	if(i==0)
	{
	 temp_value = '$txtPERIHALTANAH1';
	}
	if(i==1)
	{
	 temp_value = '$txtPERIHALTANAH2';
	}
	if(i==2)
	{
	 temp_value = '$txtPERIHALTANAH3';
	}
	}*/
	

	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 3."+(i+1)	
	
	+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PERIHALTANAH_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PERIHALTANAH_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat PERIHALTANAH_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanPERIHALTANAH_MAIN_num"+i+"\" id=\"txtUlasanPERIHALTANAH_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanPERIHALTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttPERIHALTANAH_MAIN>1 && ttPERIHALTANAH_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_PERIHALTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_PERIHALTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat PERIHALTANAH_MAIN'> "+
	" </span>"; 
	}
	if(ttPERIHALTANAH_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_PERIHALTANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_PERIHALTANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat PERIHALTANAH_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#PERIHALTANAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length > 1 )
	{
	for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanPERIHALTANAH_MAIN[t].value = document.${formName}.PERIHALTANAH_MAIN_temp1[t].value;
	
    }
	}
	else if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length < 1 )
	{
	for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanPERIHALTANAH_MAIN.value = document.${formName}.PERIHALTANAH_MAIN_temp1[0].value;
	}
	}
	else if(PERIHALTANAH_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanPERIHALTANAH_MAIN.value = document.${formName}.PERIHALTANAH_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(PERIHALTANAH_MAIN_temp1_length > 1)
	{
	for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.PERIHALTANAH_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.PERIHALTANAH_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(PERIHALTANAH_MAIN_temp1_length == 1)
	{	
	 document.${formName}.PERIHALTANAH_MAIN_temp1.value = "";			
	 var element = document.${formName}.PERIHALTANAH_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttPERIHALTANAH_MAIN; i++)
    {		
    if(ttPERIHALTANAH_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanPERIHALTANAH_MAIN,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanPERIHALTANAH_MAIN[i],'30000','txtUlasanPERIHALTANAH_MAIN_check'+i,'txtUlasanPERIHALTANAH_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}



function textarea_KEPUTUSAN_MAIN(tambahtolak,jenis,index_tolak)
{

var KEPUTUSAN_MAIN_temp1_length=0;

if(document.${formName}.KEPUTUSAN_MAIN_temp1 != null)
{

if(document.${formName}.KEPUTUSAN_MAIN_temp1.length>0)
{
KEPUTUSAN_MAIN_temp1_length = document.${formName}.KEPUTUSAN_MAIN_temp1.length;
}
else
{
KEPUTUSAN_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanKEPUTUSAN_MAIN!=null)
{

if(document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanKEPUTUSAN_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='KEPUTUSAN_MAIN_tempX1' name='KEPUTUSAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEPUTUSAN_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='KEPUTUSAN_MAIN_tempX1' name='KEPUTUSAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEPUTUSAN_MAIN.value+"'></td></tr>";

}
}




$jquery("#KEPUTUSAN_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(KEPUTUSAN_MAIN_temp1_length>0)
{
ttKEPUTUSAN_MAIN = KEPUTUSAN_MAIN_temp1_length;
}
else
{
ttKEPUTUSAN_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttKEPUTUSAN_MAIN = ttKEPUTUSAN_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttKEPUTUSAN_MAIN = ttKEPUTUSAN_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttKEPUTUSAN_MAIN; i++)
  {	
  if(ttKEPUTUSAN_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[0].value
	
    } 	
	}		
   
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanKEPUTUSAN_MAIN_num\" id=\"txtUlasanKEPUTUSAN_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanKEPUTUSAN_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat KEPUTUSAN_MAIN'> "+
	      " </span>"; 	
	     if(ttKEPUTUSAN_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat KEPUTUSAN_MAIN' > "+
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
	if(ttKEPUTUSAN_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1.value
	
	}	
	else if(ttKEPUTUSAN_MAIN>2 && i!=(ttKEPUTUSAN_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i].value
	
	}	
	else if(ttKEPUTUSAN_MAIN>1 && i!=(ttKEPUTUSAN_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttKEPUTUSAN_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[1].value;	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[0].value;	
   }
   
   }		
	
	
	/*if(jenis == "umum")
    {
	if(i==0)
	{
	 temp_value = '$txtKEPUTUSAN1';
	}
	if(i==1)
	{
	 temp_value = '$txtKEPUTUSAN2';
	}
	if(i==2)
	{
	 temp_value = '$txtKEPUTUSAN3';
	}
	}*/
	

	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 5."+(i+1)	
	
	+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KEPUTUSAN_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanKEPUTUSAN_MAIN_num"+i+"\" id=\"txtUlasanKEPUTUSAN_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanKEPUTUSAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttKEPUTUSAN_MAIN>1 && ttKEPUTUSAN_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat KEPUTUSAN_MAIN'> "+
	" </span>"; 
	}
	if(ttKEPUTUSAN_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat KEPUTUSAN_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#KEPUTUSAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1 )
	{
	for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanKEPUTUSAN_MAIN[t].value = document.${formName}.KEPUTUSAN_MAIN_temp1[t].value;
	
    }
	}
	else if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length < 1 )
	{
	for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanKEPUTUSAN_MAIN.value = document.${formName}.KEPUTUSAN_MAIN_temp1[0].value;
	}
	}
	else if(KEPUTUSAN_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanKEPUTUSAN_MAIN.value = document.${formName}.KEPUTUSAN_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(KEPUTUSAN_MAIN_temp1_length > 1)
	{
	for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.KEPUTUSAN_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.KEPUTUSAN_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(KEPUTUSAN_MAIN_temp1_length == 1)
	{	
	 document.${formName}.KEPUTUSAN_MAIN_temp1.value = "";			
	 var element = document.${formName}.KEPUTUSAN_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttKEPUTUSAN_MAIN; i++)
    {		
    if(ttKEPUTUSAN_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanKEPUTUSAN_MAIN,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanKEPUTUSAN_MAIN[i],'30000','txtUlasanKEPUTUSAN_MAIN_check'+i,'txtUlasanKEPUTUSAN_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}



function textarea_KESIMPULAN_MAIN(tambahtolak,jenis,index_tolak)
{

var KESIMPULAN_MAIN_temp1_length=0;

if(document.${formName}.KESIMPULAN_MAIN_temp1 != null)
{

if(document.${formName}.KESIMPULAN_MAIN_temp1.length>0)
{
KESIMPULAN_MAIN_temp1_length = document.${formName}.KESIMPULAN_MAIN_temp1.length;
}
else
{
KESIMPULAN_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanKESIMPULAN_MAIN!=null)
{

if(document.${formName}.txtUlasanKESIMPULAN_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanKESIMPULAN_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='KESIMPULAN_MAIN_tempX1' name='KESIMPULAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKESIMPULAN_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='KESIMPULAN_MAIN_tempX1' name='KESIMPULAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKESIMPULAN_MAIN.value+"'></td></tr>";

}
}




$jquery("#KESIMPULAN_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(KESIMPULAN_MAIN_temp1_length>0)
{
ttKESIMPULAN_MAIN = KESIMPULAN_MAIN_temp1_length;
}
else
{
ttKESIMPULAN_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttKESIMPULAN_MAIN = ttKESIMPULAN_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttKESIMPULAN_MAIN = ttKESIMPULAN_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttKESIMPULAN_MAIN; i++)
  {	
  if(ttKESIMPULAN_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[0].value
	
    } 	
	}		
   
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanKESIMPULAN_MAIN\" id=\"txtUlasanKESIMPULAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check','txtUlasanKESIMPULAN_MAIN_num','normal','no','ulasan kos-kos akibat KESIMPULAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanKESIMPULAN_MAIN_check','txtUlasanKESIMPULAN_MAIN_num','normal','no','ulasan kos-kos akibat KESIMPULAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check','txtUlasanKESIMPULAN_MAIN_num','normal','no','ulasan kos-kos akibat KESIMPULAN_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanKESIMPULAN_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanKESIMPULAN_MAIN_num\" id=\"txtUlasanKESIMPULAN_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanKESIMPULAN_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_KESIMPULAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_KESIMPULAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat KESIMPULAN_MAIN'> "+
	      " </span>"; 	
	     if(ttKESIMPULAN_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_KESIMPULAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_KESIMPULAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat KESIMPULAN_MAIN' > "+
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
	if(ttKESIMPULAN_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1.value
	
	}	
	else if(ttKESIMPULAN_MAIN>2 && i!=(ttKESIMPULAN_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[i].value
	
	}	
	else if(ttKESIMPULAN_MAIN>1 && i!=(ttKESIMPULAN_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttKESIMPULAN_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[1].value;	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[0].value;	
   }
   
   }		
	
	
	/*if(jenis == "umum")
    {
	if(i==0)
	{
	 temp_value = '$txtKESIMPULAN1';
	}
	if(i==1)
	{
	 temp_value = '$txtKESIMPULAN2';
	}
	if(i==2)
	{
	 temp_value = '$txtKESIMPULAN3';
	}
	}*/
	

	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 6."+(i+1)	
	
	+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanKESIMPULAN_MAIN\" id=\"txtUlasanKESIMPULAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check"+i+"','txtUlasanKESIMPULAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KESIMPULAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanKESIMPULAN_MAIN_check"+i+"','txtUlasanKESIMPULAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KESIMPULAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check"+i+"','txtUlasanKESIMPULAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KESIMPULAN_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanKESIMPULAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanKESIMPULAN_MAIN_num"+i+"\" id=\"txtUlasanKESIMPULAN_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanKESIMPULAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttKESIMPULAN_MAIN>1 && ttKESIMPULAN_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_KESIMPULAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_KESIMPULAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat KESIMPULAN_MAIN'> "+
	" </span>"; 
	}
	if(ttKESIMPULAN_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_KESIMPULAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_KESIMPULAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat KESIMPULAN_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#KESIMPULAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(KESIMPULAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKESIMPULAN_MAIN.length > 1 )
	{
	for (t = 0; t < KESIMPULAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanKESIMPULAN_MAIN[t].value = document.${formName}.KESIMPULAN_MAIN_temp1[t].value;
	
    }
	}
	else if(KESIMPULAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKESIMPULAN_MAIN.length < 1 )
	{
	for (t = 0; t < KESIMPULAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanKESIMPULAN_MAIN.value = document.${formName}.KESIMPULAN_MAIN_temp1[0].value;
	}
	}
	else if(KESIMPULAN_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanKESIMPULAN_MAIN.value = document.${formName}.KESIMPULAN_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(KESIMPULAN_MAIN_temp1_length > 1)
	{
	for (t = 0; t < KESIMPULAN_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.KESIMPULAN_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.KESIMPULAN_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(KESIMPULAN_MAIN_temp1_length == 1)
	{	
	 document.${formName}.KESIMPULAN_MAIN_temp1.value = "";			
	 var element = document.${formName}.KESIMPULAN_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttKESIMPULAN_MAIN; i++)
    {		
    if(ttKESIMPULAN_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanKESIMPULAN_MAIN,'30000','txtUlasanKESIMPULAN_MAIN_check','txtUlasanKESIMPULAN_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanKESIMPULAN_MAIN[i],'30000','txtUlasanKESIMPULAN_MAIN_check'+i,'txtUlasanKESIMPULAN_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}




function textarea_LAPORANTEKNIKAL_MAIN(tambahtolak,jenis,index_tolak)
{

var LAPORANTEKNIKAL_MAIN_temp1_length=0;

if(document.${formName}.LAPORANTEKNIKAL_MAIN_temp1 != null)
{

if(document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.length>0)
{
LAPORANTEKNIKAL_MAIN_temp1_length = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.length;
}
else
{
LAPORANTEKNIKAL_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN!=null)
{

if(document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='LAPORANTEKNIKAL_MAIN_tempX1' name='LAPORANTEKNIKAL_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='LAPORANTEKNIKAL_MAIN_tempX1' name='LAPORANTEKNIKAL_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.value+"'></td></tr>";

}
}




$jquery("#LAPORANTEKNIKAL_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(LAPORANTEKNIKAL_MAIN_temp1_length>0)
{
ttLAPORANTEKNIKAL_MAIN = LAPORANTEKNIKAL_MAIN_temp1_length;
}
else
{
ttLAPORANTEKNIKAL_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttLAPORANTEKNIKAL_MAIN = ttLAPORANTEKNIKAL_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttLAPORANTEKNIKAL_MAIN = ttLAPORANTEKNIKAL_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttLAPORANTEKNIKAL_MAIN; i++)
  {	
  if(ttLAPORANTEKNIKAL_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[0].value
	
    } 	
	}		
   
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanLAPORANTEKNIKAL_MAIN\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','ulasan kos-kos akibat LAPORANTEKNIKAL_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','ulasan kos-kos akibat LAPORANTEKNIKAL_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','ulasan kos-kos akibat LAPORANTEKNIKAL_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanLAPORANTEKNIKAL_MAIN_num\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanLAPORANTEKNIKAL_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_LAPORANTEKNIKAL_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_LAPORANTEKNIKAL_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat LAPORANTEKNIKAL_MAIN'> "+
	      " </span>"; 	
	     if(ttLAPORANTEKNIKAL_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_LAPORANTEKNIKAL_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_LAPORANTEKNIKAL_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat LAPORANTEKNIKAL_MAIN' > "+
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
	if(ttLAPORANTEKNIKAL_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1.value
	
	}	
	else if(ttLAPORANTEKNIKAL_MAIN>2 && i!=(ttLAPORANTEKNIKAL_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[i].value
	
	}	
	else if(ttLAPORANTEKNIKAL_MAIN>1 && i!=(ttLAPORANTEKNIKAL_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttLAPORANTEKNIKAL_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[1].value;	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[0].value;	
   }
   
   }		
	
	
	/*if(jenis == "umum")
    {
	if(i==0)
	{
	 temp_value = '$txtLAPORANTEKNIKAL1';
	}
	if(i==1)
	{
	 temp_value = '$txtLAPORANTEKNIKAL2';
	}
	if(i==2)
	{
	 temp_value = '$txtLAPORANTEKNIKAL3';
	}
	}*/
	

	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 4."+(i+1)	
	
	+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanLAPORANTEKNIKAL_MAIN\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"','txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LAPORANTEKNIKAL_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"','txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LAPORANTEKNIKAL_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"','txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LAPORANTEKNIKAL_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttLAPORANTEKNIKAL_MAIN>1 && ttLAPORANTEKNIKAL_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_LAPORANTEKNIKAL_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_LAPORANTEKNIKAL_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat LAPORANTEKNIKAL_MAIN'> "+
	" </span>"; 
	}
	if(ttLAPORANTEKNIKAL_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_LAPORANTEKNIKAL_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_LAPORANTEKNIKAL_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat LAPORANTEKNIKAL_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#LAPORANTEKNIKAL_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(LAPORANTEKNIKAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length > 1 )
	{
	for (t = 0; t < LAPORANTEKNIKAL_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN[t].value = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[t].value;
	
    }
	}
	else if(LAPORANTEKNIKAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length < 1 )
	{
	for (t = 0; t < LAPORANTEKNIKAL_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.value = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[0].value;
	}
	}
	else if(LAPORANTEKNIKAL_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.value = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(LAPORANTEKNIKAL_MAIN_temp1_length > 1)
	{
	for (t = 0; t < LAPORANTEKNIKAL_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(LAPORANTEKNIKAL_MAIN_temp1_length == 1)
	{	
	 document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.value = "";			
	 var element = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttLAPORANTEKNIKAL_MAIN; i++)
    {		
    if(ttLAPORANTEKNIKAL_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN[i],'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check'+i,'txtUlasanLAPORANTEKNIKAL_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
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
   
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>  </td>"+
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
	
	
	/*if(jenis == "umum")
    {
	if(i==0)
	{
	 temp_value = '$txtPERAKUAN1';
	}
	if(i==1)
	{
	 temp_value = '$txtPERAKUAN2';
	}
	if(i==2)
	{
	 temp_value = '$txtPERAKUAN3';
	}
	}*/
	

	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 8."+(i+1)	
	
	+"  </td>"+
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
	
	
	function textarea_SYORJAJAHAN_MAIN(tambahtolak,jenis,index_tolak)
{

var SYORJAJAHAN_MAIN_temp1_length=0;

if(document.${formName}.SYORJAJAHAN_MAIN_temp1 != null)
{

if(document.${formName}.SYORJAJAHAN_MAIN_temp1.length>0)
{
SYORJAJAHAN_MAIN_temp1_length = document.${formName}.SYORJAJAHAN_MAIN_temp1.length;
}
else
{
SYORJAJAHAN_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanSYORJAJAHAN_MAIN!=null)
{

if(document.${formName}.txtUlasanSYORJAJAHAN_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanSYORJAJAHAN_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='SYORJAJAHAN_MAIN_tempX1' name='SYORJAJAHAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanSYORJAJAHAN_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='SYORJAJAHAN_MAIN_tempX1' name='SYORJAJAHAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanSYORJAJAHAN_MAIN.value+"'></td></tr>";

}
}




$jquery("#SYORJAJAHAN_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(SYORJAJAHAN_MAIN_temp1_length>0)
{
ttSYORJAJAHAN_MAIN = SYORJAJAHAN_MAIN_temp1_length;
}
else
{
ttSYORJAJAHAN_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttSYORJAJAHAN_MAIN = ttSYORJAJAHAN_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttSYORJAJAHAN_MAIN = ttSYORJAJAHAN_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttSYORJAJAHAN_MAIN; i++)
  {	
  if(ttSYORJAJAHAN_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.SYORJAJAHAN_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.SYORJAJAHAN_MAIN_tempX1[0].value
	
    } 	
	}		
   
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanSYORJAJAHAN_MAIN\" id=\"txtUlasanSYORJAJAHAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanSYORJAJAHAN_MAIN_check','txtUlasanSYORJAJAHAN_MAIN_num','normal','no','ulasan kos-kos akibat SYORJAJAHAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanSYORJAJAHAN_MAIN_check','txtUlasanSYORJAJAHAN_MAIN_num','normal','no','ulasan kos-kos akibat SYORJAJAHAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanSYORJAJAHAN_MAIN_check','txtUlasanSYORJAJAHAN_MAIN_num','normal','no','ulasan kos-kos akibat SYORJAJAHAN_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanSYORJAJAHAN_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanSYORJAJAHAN_MAIN_num\" id=\"txtUlasanSYORJAJAHAN_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanSYORJAJAHAN_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_SYORJAJAHAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_SYORJAJAHAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat SYORJAJAHAN_MAIN'> "+
	      " </span>"; 	
	     if(ttSYORJAJAHAN_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_SYORJAJAHAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_SYORJAJAHAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat SYORJAJAHAN_MAIN' > "+
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
	if(ttSYORJAJAHAN_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.SYORJAJAHAN_MAIN_tempX1.value
	
	}	
	else if(ttSYORJAJAHAN_MAIN>2 && i!=(ttSYORJAJAHAN_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.SYORJAJAHAN_MAIN_tempX1[i].value
	
	}	
	else if(ttSYORJAJAHAN_MAIN>1 && i!=(ttSYORJAJAHAN_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.SYORJAJAHAN_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.SYORJAJAHAN_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.SYORJAJAHAN_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttSYORJAJAHAN_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.SYORJAJAHAN_MAIN_tempX1[1].value;	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.SYORJAJAHAN_MAIN_tempX1[0].value;	
   }
   
   }		
	
	
	
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 9."+(i+1)	
	
	+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanSYORJAJAHAN_MAIN\" id=\"txtUlasanSYORJAJAHAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanSYORJAJAHAN_MAIN_check"+i+"','txtUlasanSYORJAJAHAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SYORJAJAHAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanSYORJAJAHAN_MAIN_check"+i+"','txtUlasanSYORJAJAHAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SYORJAJAHAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanSYORJAJAHAN_MAIN_check"+i+"','txtUlasanSYORJAJAHAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat SYORJAJAHAN_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanSYORJAJAHAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanSYORJAJAHAN_MAIN_num"+i+"\" id=\"txtUlasanSYORJAJAHAN_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanSYORJAJAHAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttSYORJAJAHAN_MAIN>1 && ttSYORJAJAHAN_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_SYORJAJAHAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_SYORJAJAHAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat SYORJAJAHAN_MAIN'> "+
	" </span>"; 
	}
	if(ttSYORJAJAHAN_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_SYORJAJAHAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_SYORJAJAHAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat SYORJAJAHAN_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#SYORJAJAHAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(SYORJAJAHAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYORJAJAHAN_MAIN.length > 1 )
	{
	for (t = 0; t < SYORJAJAHAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanSYORJAJAHAN_MAIN[t].value = document.${formName}.SYORJAJAHAN_MAIN_temp1[t].value;
	
    }
	}
	else if(SYORJAJAHAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYORJAJAHAN_MAIN.length < 1 )
	{
	for (t = 0; t < SYORJAJAHAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanSYORJAJAHAN_MAIN.value = document.${formName}.SYORJAJAHAN_MAIN_temp1[0].value;
	}
	}
	else if(SYORJAJAHAN_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanSYORJAJAHAN_MAIN.value = document.${formName}.SYORJAJAHAN_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(SYORJAJAHAN_MAIN_temp1_length > 1)
	{
	for (t = 0; t < SYORJAJAHAN_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.SYORJAJAHAN_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.SYORJAJAHAN_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(SYORJAJAHAN_MAIN_temp1_length == 1)
	{	
	 document.${formName}.SYORJAJAHAN_MAIN_temp1.value = "";			
	 var element = document.${formName}.SYORJAJAHAN_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttSYORJAJAHAN_MAIN; i++)
    {		
    if(ttSYORJAJAHAN_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanSYORJAJAHAN_MAIN,'30000','txtUlasanSYORJAJAHAN_MAIN_check','txtUlasanSYORJAJAHAN_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanSYORJAJAHAN_MAIN[i],'30000','txtUlasanSYORJAJAHAN_MAIN_check'+i,'txtUlasanSYORJAJAHAN_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	}


function textarea_ULASANPTG_MAIN(tambahtolak,jenis,index_tolak)
{

var ULASANPTG_MAIN_temp1_length=0;

if(document.${formName}.ULASANPTG_MAIN_temp1 != null)
{

if(document.${formName}.ULASANPTG_MAIN_temp1.length>0)
{
ULASANPTG_MAIN_temp1_length = document.${formName}.ULASANPTG_MAIN_temp1.length;
}
else
{
ULASANPTG_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanULASANPTG_MAIN!=null)
{

if(document.${formName}.txtUlasanULASANPTG_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanULASANPTG_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='ULASANPTG_MAIN_tempX1' name='ULASANPTG_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPTG_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='ULASANPTG_MAIN_tempX1' name='ULASANPTG_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPTG_MAIN.value+"'></td></tr>";

}
}




$jquery("#ULASANPTG_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(ULASANPTG_MAIN_temp1_length>0)
{
ttULASANPTG_MAIN = ULASANPTG_MAIN_temp1_length;
}
else
{
ttULASANPTG_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttULASANPTG_MAIN = ttULASANPTG_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttULASANPTG_MAIN = ttULASANPTG_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttULASANPTG_MAIN; i++)
  {	
  if(ttULASANPTG_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.ULASANPTG_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.ULASANPTG_MAIN_tempX1[0].value
	
    } 	
	}		
   
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanULASANPTG_MAIN\" id=\"txtUlasanULASANPTG_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanULASANPTG_MAIN_check','txtUlasanULASANPTG_MAIN_num','normal','no','ulasan kos-kos akibat ULASANPTG_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanULASANPTG_MAIN_check','txtUlasanULASANPTG_MAIN_num','normal','no','ulasan kos-kos akibat ULASANPTG_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanULASANPTG_MAIN_check','txtUlasanULASANPTG_MAIN_num','normal','no','ulasan kos-kos akibat ULASANPTG_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanULASANPTG_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanULASANPTG_MAIN_num\" id=\"txtUlasanULASANPTG_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanULASANPTG_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_ULASANPTG_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_ULASANPTG_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat ULASANPTG_MAIN'> "+
	      " </span>"; 	
	     if(ttULASANPTG_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_ULASANPTG_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_ULASANPTG_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat ULASANPTG_MAIN' > "+
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
	if(ttULASANPTG_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.ULASANPTG_MAIN_tempX1.value
	
	}	
	else if(ttULASANPTG_MAIN>2 && i!=(ttULASANPTG_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.ULASANPTG_MAIN_tempX1[i].value
	
	}	
	else if(ttULASANPTG_MAIN>1 && i!=(ttULASANPTG_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.ULASANPTG_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.ULASANPTG_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.ULASANPTG_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttULASANPTG_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.ULASANPTG_MAIN_tempX1[1].value;	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.ULASANPTG_MAIN_tempX1[0].value;	
   }
   
   }		
	
	
	
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 10."+(i+1)	
	
	+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanULASANPTG_MAIN\" id=\"txtUlasanULASANPTG_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanULASANPTG_MAIN_check"+i+"','txtUlasanULASANPTG_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat ULASANPTG_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanULASANPTG_MAIN_check"+i+"','txtUlasanULASANPTG_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat ULASANPTG_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanULASANPTG_MAIN_check"+i+"','txtUlasanULASANPTG_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat ULASANPTG_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanULASANPTG_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanULASANPTG_MAIN_num"+i+"\" id=\"txtUlasanULASANPTG_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanULASANPTG_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttULASANPTG_MAIN>1 && ttULASANPTG_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_ULASANPTG_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_ULASANPTG_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat ULASANPTG_MAIN'> "+
	" </span>"; 
	}
	if(ttULASANPTG_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_ULASANPTG_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_ULASANPTG_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat ULASANPTG_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#ULASANPTG_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(ULASANPTG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPTG_MAIN.length > 1 )
	{
	for (t = 0; t < ULASANPTG_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanULASANPTG_MAIN[t].value = document.${formName}.ULASANPTG_MAIN_temp1[t].value;
	
    }
	}
	else if(ULASANPTG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPTG_MAIN.length < 1 )
	{
	for (t = 0; t < ULASANPTG_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanULASANPTG_MAIN.value = document.${formName}.ULASANPTG_MAIN_temp1[0].value;
	}
	}
	else if(ULASANPTG_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanULASANPTG_MAIN.value = document.${formName}.ULASANPTG_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(ULASANPTG_MAIN_temp1_length > 1)
	{
	for (t = 0; t < ULASANPTG_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.ULASANPTG_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.ULASANPTG_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(ULASANPTG_MAIN_temp1_length == 1)
	{	
	 document.${formName}.ULASANPTG_MAIN_temp1.value = "";			
	 var element = document.${formName}.ULASANPTG_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttULASANPTG_MAIN; i++)
    {		
    if(ttULASANPTG_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanULASANPTG_MAIN,'30000','txtUlasanULASANPTG_MAIN_check','txtUlasanULASANPTG_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanULASANPTG_MAIN[i],'30000','txtUlasanULASANPTG_MAIN_check'+i,'txtUlasanULASANPTG_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	}



function textarea_KEPUTUSAN_MMK_MAIN(tambahtolak,jenis,index_tolak)
{

var KEPUTUSAN_MMK_MAIN_temp1_length=0;

if(document.${formName}.KEPUTUSAN_MMK_MAIN_temp1 != null)
{

if(document.${formName}.KEPUTUSAN_MMK_MAIN_temp1.length>0)
{
KEPUTUSAN_MMK_MAIN_temp1_length = document.${formName}.KEPUTUSAN_MMK_MAIN_temp1.length;
}
else
{
KEPUTUSAN_MMK_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN!=null)
{

if(document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='KEPUTUSAN_MMK_MAIN_tempX1' name='KEPUTUSAN_MMK_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='KEPUTUSAN_MMK_MAIN_tempX1' name='KEPUTUSAN_MMK_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN.value+"'></td></tr>";

}
}




$jquery("#KEPUTUSAN_MMK_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(KEPUTUSAN_MMK_MAIN_temp1_length>0)
{
ttKEPUTUSAN_MMK_MAIN = KEPUTUSAN_MMK_MAIN_temp1_length;
}
else
{
ttKEPUTUSAN_MMK_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttKEPUTUSAN_MMK_MAIN = ttKEPUTUSAN_MMK_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttKEPUTUSAN_MMK_MAIN = ttKEPUTUSAN_MMK_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttKEPUTUSAN_MMK_MAIN; i++)
  {	
  if(ttKEPUTUSAN_MMK_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.KEPUTUSAN_MMK_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.KEPUTUSAN_MMK_MAIN_tempX1[0].value
	
    } 	
	}		
   
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanKEPUTUSAN_MMK_MAIN\" id=\"txtUlasanKEPUTUSAN_MMK_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MMK_MAIN_check','txtUlasanKEPUTUSAN_MMK_MAIN_num','normal','no','ulasan kos-kos akibat KEPUTUSAN_MMK_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanKEPUTUSAN_MMK_MAIN_check','txtUlasanKEPUTUSAN_MMK_MAIN_num','normal','no','ulasan kos-kos akibat KEPUTUSAN_MMK_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MMK_MAIN_check','txtUlasanKEPUTUSAN_MMK_MAIN_num','normal','no','ulasan kos-kos akibat KEPUTUSAN_MMK_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanKEPUTUSAN_MMK_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanKEPUTUSAN_MMK_MAIN_num\" id=\"txtUlasanKEPUTUSAN_MMK_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanKEPUTUSAN_MMK_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_KEPUTUSAN_MMK_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_KEPUTUSAN_MMK_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat KEPUTUSAN_MMK_MAIN'> "+
	      " </span>"; 	
	     if(ttKEPUTUSAN_MMK_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_KEPUTUSAN_MMK_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_KEPUTUSAN_MMK_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat KEPUTUSAN_MMK_MAIN' > "+
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
	if(ttKEPUTUSAN_MMK_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.KEPUTUSAN_MMK_MAIN_tempX1.value
	
	}	
	else if(ttKEPUTUSAN_MMK_MAIN>2 && i!=(ttKEPUTUSAN_MMK_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.KEPUTUSAN_MMK_MAIN_tempX1[i].value
	
	}	
	else if(ttKEPUTUSAN_MMK_MAIN>1 && i!=(ttKEPUTUSAN_MMK_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.KEPUTUSAN_MMK_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.KEPUTUSAN_MMK_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.KEPUTUSAN_MMK_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttKEPUTUSAN_MMK_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.KEPUTUSAN_MMK_MAIN_tempX1[1].value;	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.KEPUTUSAN_MMK_MAIN_tempX1[0].value;	
   }
   
   }		
	
	
	
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 11."+(i+1)	
	
	+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanKEPUTUSAN_MMK_MAIN\" id=\"txtUlasanKEPUTUSAN_MMK_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MMK_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MMK_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KEPUTUSAN_MMK_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanKEPUTUSAN_MMK_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MMK_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KEPUTUSAN_MMK_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MMK_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MMK_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat KEPUTUSAN_MMK_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanKEPUTUSAN_MMK_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanKEPUTUSAN_MMK_MAIN_num"+i+"\" id=\"txtUlasanKEPUTUSAN_MMK_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanKEPUTUSAN_MMK_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttKEPUTUSAN_MMK_MAIN>1 && ttKEPUTUSAN_MMK_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_KEPUTUSAN_MMK_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_KEPUTUSAN_MMK_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat KEPUTUSAN_MMK_MAIN'> "+
	" </span>"; 
	}
	if(ttKEPUTUSAN_MMK_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_KEPUTUSAN_MMK_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_KEPUTUSAN_MMK_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat KEPUTUSAN_MMK_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#KEPUTUSAN_MMK_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(KEPUTUSAN_MMK_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN.length > 1 )
	{
	for (t = 0; t < KEPUTUSAN_MMK_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN[t].value = document.${formName}.KEPUTUSAN_MMK_MAIN_temp1[t].value;
	
    }
	}
	else if(KEPUTUSAN_MMK_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN.length < 1 )
	{
	for (t = 0; t < KEPUTUSAN_MMK_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN.value = document.${formName}.KEPUTUSAN_MMK_MAIN_temp1[0].value;
	}
	}
	else if(KEPUTUSAN_MMK_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN.value = document.${formName}.KEPUTUSAN_MMK_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(KEPUTUSAN_MMK_MAIN_temp1_length > 1)
	{
	for (t = 0; t < KEPUTUSAN_MMK_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.KEPUTUSAN_MMK_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.KEPUTUSAN_MMK_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(KEPUTUSAN_MMK_MAIN_temp1_length == 1)
	{	
	 document.${formName}.KEPUTUSAN_MMK_MAIN_temp1.value = "";			
	 var element = document.${formName}.KEPUTUSAN_MMK_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttKEPUTUSAN_MMK_MAIN; i++)
    {		
    if(ttKEPUTUSAN_MMK_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN,'30000','txtUlasanKEPUTUSAN_MMK_MAIN_check','txtUlasanKEPUTUSAN_MMK_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN[i],'30000','txtUlasanKEPUTUSAN_MMK_MAIN_check'+i,'txtUlasanKEPUTUSAN_MMK_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
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
	
	var ULASAN_PENGARAH_MAIN_temp1_length=0;
	if(document.${formName}.ULASAN_PENGARAH_MAIN_temp1 != null)
	{
	if(document.${formName}.ULASAN_PENGARAH_MAIN_temp1.length>0)
	{
	ULASAN_PENGARAH_MAIN_temp1_length = document.${formName}.ULASAN_PENGARAH_MAIN_temp1.length;
	}
	else
	{
	ULASAN_PENGARAH_MAIN_temp1_length = 1;
	}
	}

    if(ULASAN_PENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASAN_PENGARAH_MAIN.length > 1 )
	{
	for (t = 0; t < ULASAN_PENGARAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.ULASAN_PENGARAH_MAIN_temp1[t].value = document.${formName}.txtUlasanULASAN_PENGARAH_MAIN[t].value;
	}
	}
	else if(ULASAN_PENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASAN_PENGARAH_MAIN.length < 1 )
	{
	for (t = 0; t < ULASAN_PENGARAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.ULASAN_PENGARAH_MAIN_temp1[0].value = document.${formName}.txtUlasanULASAN_PENGARAH_MAIN.value;
    }
	}
	else if(ULASAN_PENGARAH_MAIN_temp1_length == 1)
	{
	document.${formName}.ULASAN_PENGARAH_MAIN_temp1.value = document.${formName}.txtUlasanULASAN_PENGARAH_MAIN.value;

	}
	
	
	
	var KEPUTUSAN_MAIN_temp1_length=0;
	if(document.${formName}.KEPUTUSAN_MAIN_temp1 != null)
	{
	if(document.${formName}.KEPUTUSAN_MAIN_temp1.length>0)
	{
	KEPUTUSAN_MAIN_temp1_length = document.${formName}.KEPUTUSAN_MAIN_temp1.length;
	}
	else
	{
	KEPUTUSAN_MAIN_temp1_length = 1;
	}
	}

    if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1 )
	{
	for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.KEPUTUSAN_MAIN_temp1[t].value = document.${formName}.txtUlasanKEPUTUSAN_MAIN[t].value;
	}
	}
	else if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length < 1 )
	{
	for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.KEPUTUSAN_MAIN_temp1[0].value = document.${formName}.txtUlasanKEPUTUSAN_MAIN.value;
    }
	}
	else if(KEPUTUSAN_MAIN_temp1_length == 1)
	{
	document.${formName}.KEPUTUSAN_MAIN_temp1.value = document.${formName}.txtUlasanKEPUTUSAN_MAIN.value;

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

    if(SYOR_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYOR_MAIN.length > 1 )
	{
	for (t = 0; t < SYOR_MAIN_temp1_length; t++)
    {	
    document.${formName}.SYOR_MAIN_temp1[t].value = document.${formName}.txtUlasanSYOR_MAIN[t].value;
	}
	}
	else if(SYOR_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYOR_MAIN.length < 1 )
	{
	for (t = 0; t < SYOR_MAIN_temp1_length; t++)
    {	
    document.${formName}.SYOR_MAIN_temp1[0].value = document.${formName}.txtUlasanSYOR_MAIN.value;
    }
	}
	else if(SYOR_MAIN_temp1_length == 1)
	{
	document.${formName}.SYOR_MAIN_temp1.value = document.${formName}.txtUlasanSYOR_MAIN.value;

	}
	
		
		
			
	var PERIHALTANAH_MAIN_temp1_length=0;
	if(document.${formName}.PERIHALTANAH_MAIN_temp1 != null)
	{
	if(document.${formName}.PERIHALTANAH_MAIN_temp1.length>0)
	{
	PERIHALTANAH_MAIN_temp1_length = document.${formName}.PERIHALTANAH_MAIN_temp1.length;
	}
	else
	{
	PERIHALTANAH_MAIN_temp1_length = 1;
	}
	}

    if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length > 1 )
	{
	for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.PERIHALTANAH_MAIN_temp1[t].value = document.${formName}.txtUlasanPERIHALTANAH_MAIN[t].value;
	}
	}
	else if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length < 1 )
	{
	for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++)
    {	
    document.${formName}.PERIHALTANAH_MAIN_temp1[0].value = document.${formName}.txtUlasanPERIHALTANAH_MAIN.value;
    }
	}
	else if(PERIHALTANAH_MAIN_temp1_length == 1)
	{
	document.${formName}.PERIHALTANAH_MAIN_temp1.value = document.${formName}.txtUlasanPERIHALTANAH_MAIN.value;

	}
	


		
	var KEPUTUSAN_MAIN_temp1_length=0;
	if(document.${formName}.KEPUTUSAN_MAIN_temp1 != null)
	{
	if(document.${formName}.KEPUTUSAN_MAIN_temp1.length>0)
	{
	KEPUTUSAN_MAIN_temp1_length = document.${formName}.KEPUTUSAN_MAIN_temp1.length;
	}
	else
	{
	KEPUTUSAN_MAIN_temp1_length = 1;
	}
	}

    if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1 )
	{
	for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.KEPUTUSAN_MAIN_temp1[t].value = document.${formName}.txtUlasanKEPUTUSAN_MAIN[t].value;
	}
	}
	else if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length < 1 )
	{
	for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.KEPUTUSAN_MAIN_temp1[0].value = document.${formName}.txtUlasanKEPUTUSAN_MAIN.value;
    }
	}
	else if(KEPUTUSAN_MAIN_temp1_length == 1)
	{
	document.${formName}.KEPUTUSAN_MAIN_temp1.value = document.${formName}.txtUlasanKEPUTUSAN_MAIN.value;

	}
	
	
	var KESIMPULAN_MAIN_temp1_length=0;
	if(document.${formName}.KESIMPULAN_MAIN_temp1 != null)
	{
	if(document.${formName}.KESIMPULAN_MAIN_temp1.length>0)
	{
	KESIMPULAN_MAIN_temp1_length = document.${formName}.KESIMPULAN_MAIN_temp1.length;
	}
	else
	{
	KESIMPULAN_MAIN_temp1_length = 1;
	}
	}

    if(KESIMPULAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKESIMPULAN_MAIN.length > 1 )
	{
	for (t = 0; t < KESIMPULAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.KESIMPULAN_MAIN_temp1[t].value = document.${formName}.txtUlasanKESIMPULAN_MAIN[t].value;
	}
	}
	else if(KESIMPULAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKESIMPULAN_MAIN.length < 1 )
	{
	for (t = 0; t < KESIMPULAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.KESIMPULAN_MAIN_temp1[0].value = document.${formName}.txtUlasanKESIMPULAN_MAIN.value;
    }
	}
	else if(KESIMPULAN_MAIN_temp1_length == 1)
	{
	document.${formName}.KESIMPULAN_MAIN_temp1.value = document.${formName}.txtUlasanKESIMPULAN_MAIN.value;

	}
	

		
		
			
	var LAPORANTEKNIKAL_MAIN_temp1_length=0;
	if(document.${formName}.LAPORANTEKNIKAL_MAIN_temp1 != null)
	{
	if(document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.length>0)
	{
	LAPORANTEKNIKAL_MAIN_temp1_length = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.length;
	}
	else
	{
	LAPORANTEKNIKAL_MAIN_temp1_length = 1;
	}
	}

    if(LAPORANTEKNIKAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length > 1 )
	{
	for (t = 0; t < LAPORANTEKNIKAL_MAIN_temp1_length; t++)
    {	
    document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[t].value = document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN[t].value;
	}
	}
	else if(LAPORANTEKNIKAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length < 1 )
	{
	for (t = 0; t < LAPORANTEKNIKAL_MAIN_temp1_length; t++)
    {	
    document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[0].value = document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.value;
    }
	}
	else if(LAPORANTEKNIKAL_MAIN_temp1_length == 1)
	{
	document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.value = document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.value;

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
	

var SYORJAJAHAN_MAIN_temp1_length=0;
	if(document.${formName}.SYORJAJAHAN_MAIN_temp1 != null)
	{
	if(document.${formName}.SYORJAJAHAN_MAIN_temp1.length>0)
	{
	SYORJAJAHAN_MAIN_temp1_length = document.${formName}.SYORJAJAHAN_MAIN_temp1.length;
	}
	else
	{
	SYORJAJAHAN_MAIN_temp1_length = 1;
	}
	}

    if(SYORJAJAHAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYORJAJAHAN_MAIN.length > 1 )
	{
	for (t = 0; t < SYORJAJAHAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.SYORJAJAHAN_MAIN_temp1[t].value = document.${formName}.txtUlasanSYORJAJAHAN_MAIN[t].value;
	}
	}
	else if(SYORJAJAHAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYORJAJAHAN_MAIN.length < 1 )
	{
	for (t = 0; t < SYORJAJAHAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.SYORJAJAHAN_MAIN_temp1[0].value = document.${formName}.txtUlasanSYORJAJAHAN_MAIN.value;
    }
	}
	else if(SYORJAJAHAN_MAIN_temp1_length == 1)
	{
	document.${formName}.SYORJAJAHAN_MAIN_temp1.value = document.${formName}.txtUlasanSYORJAJAHAN_MAIN.value;

	}
	
	
	
var ULASANPTG_MAIN_temp1_length=0;
	if(document.${formName}.ULASANPTG_MAIN_temp1 != null)
	{
	if(document.${formName}.ULASANPTG_MAIN_temp1.length>0)
	{
	ULASANPTG_MAIN_temp1_length = document.${formName}.ULASANPTG_MAIN_temp1.length;
	}
	else
	{
	ULASANPTG_MAIN_temp1_length = 1;
	}
	}

    if(ULASANPTG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPTG_MAIN.length > 1 )
	{
	for (t = 0; t < ULASANPTG_MAIN_temp1_length; t++)
    {	
    document.${formName}.ULASANPTG_MAIN_temp1[t].value = document.${formName}.txtUlasanULASANPTG_MAIN[t].value;
	}
	}
	else if(ULASANPTG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPTG_MAIN.length < 1 )
	{
	for (t = 0; t < ULASANPTG_MAIN_temp1_length; t++)
    {	
    document.${formName}.ULASANPTG_MAIN_temp1[0].value = document.${formName}.txtUlasanULASANPTG_MAIN.value;
    }
	}
	else if(ULASANPTG_MAIN_temp1_length == 1)
	{
	document.${formName}.ULASANPTG_MAIN_temp1.value = document.${formName}.txtUlasanULASANPTG_MAIN.value;

	}
	
	
		
var KEPUTUSAN_MMK_MAIN_temp1_length=0;
	if(document.${formName}.KEPUTUSAN_MMK_MAIN_temp1 != null)
	{
	if(document.${formName}.KEPUTUSAN_MMK_MAIN_temp1.length>0)
	{
	KEPUTUSAN_MMK_MAIN_temp1_length = document.${formName}.KEPUTUSAN_MMK_MAIN_temp1.length;
	}
	else
	{
	KEPUTUSAN_MMK_MAIN_temp1_length = 1;
	}
	}

    if(KEPUTUSAN_MMK_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN.length > 1 )
	{
	for (t = 0; t < KEPUTUSAN_MMK_MAIN_temp1_length; t++)
    {	
    document.${formName}.KEPUTUSAN_MMK_MAIN_temp1[t].value = document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN[t].value;
	}
	}
	else if(KEPUTUSAN_MMK_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN.length < 1 )
	{
	for (t = 0; t < KEPUTUSAN_MMK_MAIN_temp1_length; t++)
    {	
    document.${formName}.KEPUTUSAN_MMK_MAIN_temp1[0].value = document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN.value;
    }
	}
	else if(KEPUTUSAN_MMK_MAIN_temp1_length == 1)
	{
	document.${formName}.KEPUTUSAN_MMK_MAIN_temp1.value = document.${formName}.txtUlasanKEPUTUSAN_MMK_MAIN.value;

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