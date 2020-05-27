<style type="text/css">
<!--
.style1 {color: #ECE5B6}
-->
</style>










#foreach ( $mp in $maklumat_pembatalan )
#set($txtSebabPembatalan = $mp.SEBAB_PEMBATALAN)

#if($mp.JENIS_PEMBATALAN == 1)
#set($sorJenisPembatalan = "SEBAHAGIAN DARIPADA")
#elseif($mp.JENIS_PEMBATALAN == 2)
#set($sorJenisPembatalan = "KESELURUHAN")
#else
#set($sorJenisPembatalan = "")
#end

#if($mp.JENIS_PEMBATALAN == 1)
#set($sorJenisPembatalan1 = "sebahagian daripada")
#elseif($mp.JENIS_PEMBATALAN == 2)
#set($sorJenisPembatalan1 = "keseluruhan")
#else
#set($sorJenisPembatalan1 = "")
#end

#set($txtNoRujSurat = $mp.NO_RUJUKAN_SURAT)
#set($txdTarikhTerimaSurat = $mp.TARIKH_TERIMA_SURAT)
#set($txdTarikhSurat = $mp.TARIKH_SURAT)
#set($txdTarikhPembatalan = $mp.TARIKH_PEMBATALAN)
#set($txtNoPembatalan = $mp.NO_PEMBATALAN)
#set($id_pembatalan = $mp.ID_PEMBATALAN)
#end


			 #if($senarai_hakmilik_batal.size()!=0)
             #foreach($listb in $senarai_hakmilik_batal) 
              
                       <input name="lot_ambilX" id="lot_ambilX"  value="$listb.LUAS_AMBIL_EDIT" type="hidden" >
                       <input name="lot_ambil_asalX" id="lot_ambil_asalX"  value="$listb.LUAS_AMBIL_ASAL" type="hidden" >
                       <input name="luas_lotX" id="luas_lotX"  value="$listb.LUAS_LOT" type="hidden" >
                       <input name="id_hakmilik_luasX" id="id_hakmilik_luasX"  style="width:100%" value="$listb.ID_HAKMILIK" type="hidden" >
                       <input name="id_lot_tarikX" id="id_lot_tarikX"  value="$listb.ID_PENARIKANHAKMILIK" type="hidden" > 
                       <input name="lot_tarikX" id="lot_tarikX" value="$listb.LUAS_LOT_TARIK_EDIT" type="hidden"  >   
                 
             
             #end
             #end


#parse("app/ppt/paging_penarikanbalik.jsp")



#set($flag_mmk = "")


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

#set($maklumat_penarikanmmk = "")
#foreach ( $mp in $maklumat_pembatalan )
#set($maklumat_penarikanmmk = $mp.SEBAB_PEMBATALAN1)
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
        <li class="TabbedPanelsTab" tabindex="0" id="penyediaan"  onClick="penyediaan('$id_permohonan','$id_pembatalan')">Penyediaan</li>
        <li class="TabbedPanelsTab" tabindex="0" id="semakan_mmk" style="display:none" onClick="semakan('$id_permohonan','$id_pembatalan')" >Semakan</li>
        #if($STATUS_SEMAKAN != "")
        <li class="TabbedPanelsTab" id="keputusan_mmk" style="display:none" onClick="keputusan('$id_permohonan','$id_pembatalan')" tabindex="0">Keputusan PBN</li>
        #end
      </ul>
      
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
   <!--   id fail :: $id_fail        
        ID_PENARIKAN BALIK :: $id_pembatalan
        ::: $ID_NEGERIPROJEK
        
           -->
       
      
                      #set($nama_mukim_mukim = "" )
                      #set($count_total=0)
                      #set($count_totalX=$maklumat_hakmilik_mmk_lot.size())
                      #set($count_totalXX=$count_totalX - 1)                      
                      #foreach($list1 in $maklumat_hakmilik_mmk_lot)                     
                      #set($count_total=$count_total + 1)                      
                      #if($maklumat_hakmilik_mmk_lot.size() > 1 && $maklumat_hakmilik_mmk_lot.size() != $count_total && $count_totalXX != $count_total) 
                      #set($nama_mukim_mukim = $nama_mukim_mukim +" $list1.NAMA_MUKIM,")
                      #elseif($maklumat_hakmilik_mmk_lot.size() > 1 && $count_totalXX == $count_total)
                      #set($nama_mukim_mukim = $nama_mukim_mukim +" $list1.NAMA_MUKIM")
                      #elseif($maklumat_hakmilik_mmk_lot.size() > 1 && $maklumat_hakmilik_mmk_lot.size() == $count_total)
                      #set($nama_mukim_mukim = $nama_mukim_mukim + " dan $list1.NAMA_MUKIM")
                      #elseif($maklumat_hakmilik_mmk_lot.size() == 1)
                      #set($nama_mukim_mukim = $nama_mukim_mukim + " $list1.NAMA_MUKIM")                      
                      #end
                      #end 
                      
                      #set($nama_pb_pb = "" )
                      #set($count_total_pb=0)
                      #set($count_totalX_pb=$maklumat_hakmilik_mmk_pb.size())
                      #set($count_totalXX_pb=$count_totalX_pb - 1)                      
                      #foreach($list1 in $maklumat_hakmilik_mmk_pb)                     
                      #set($count_total_pb=$count_total_pb + 1)                      
                      #if($maklumat_hakmilik_mmk_pb.size() > 1 && $maklumat_hakmilik_mmk_pb.size() != $count_total_pb && $count_totalXX_pb != $count_total_pb) 
                      #set($nama_pb_pb = $nama_pb_pb +" $list1.NAMA_PB,")
                      #elseif($maklumat_hakmilik_mmk_pb.size() > 1 && $count_totalXX_pb == $count_total_pb)
                      #set($nama_pb_pb = $nama_pb_pb +" $list1.NAMA_PB")
                      #elseif($maklumat_hakmilik_mmk_pb.size() > 1 && $maklumat_hakmilik_mmk_pb.size() == $count_total_pb)
                      #set($nama_pb_pb = $nama_pb_pb + " dan $list1.NAMA_PB")
                      #elseif($maklumat_hakmilik_mmk_pb.size() == 1)
                      #set($nama_pb_pb = $nama_pb_pb + "$list1.NAMA_PB")                      
                      #end
                      #end 
                      
                      
                      
                      #set($no_lot_mmk = "" )
                      #set($count_total_lot=0)
                      #set($count_totalX_lot=$senarai_lot_mmk.size())
                      #set($count_totalXX_lot=$count_totalX_lot - 1)                      
                      #foreach($list1 in $senarai_lot_mmk)                     
                      #set($count_total_lot=$count_total_lot + 1)                      
                      #if($senarai_lot_mmk.size() > 1 && $senarai_lot_mmk.size() != $count_total_lot && $count_totalXX_lot != $count_total_lot) 
                      #set($no_lot_mmk = $no_lot_mmk +" $list1.NO_LOT,")
                      #elseif($senarai_lot_mmk.size() > 1 && $count_totalXX_lot == $count_total_lot)
                      #set($no_lot_mmk = $no_lot_mmk +" $list1.NO_LOT")
                      #elseif($senarai_lot_mmk.size() > 1 && $senarai_lot_mmk.size() == $count_total_lot)
                      #set($no_lot_mmk = $no_lot_mmk + " dan $list1.NO_LOT")
                      #elseif($senarai_lot_mmk.size() == 1)
                      #set($no_lot_mmk = $no_lot_mmk + "$list1.NO_LOT")                      
                      #end
                      #end 
                      
                      
                      
                
                       
                        #foreach($listwarta in $nowarta_lot_mmk) 
                         #if($listwarta.NO_WARTA != "")    
                         #set($no_warta = $listwarta.NO_WARTA) 
                         #else
                         #set($no_warta = ".........") 
                         #end
                         #if($listwarta.TARIKH_WARTA != "") 
                         #set($tarikh_warta = $listwarta.TARIKH_WARTA) 
                         #else
                         #set($tarikh_warta = ".........") 
                         #end
                        #end 
                       
                       
                      #set($overallluas = $maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar) ")
                       
                       
                      
                       
                       
                       
                       
        
     
     
      #if( $ID_NEGERIPROJEK  == "10")
      #parse("app/ppt/PENARIKANMMK/mmk_selangor.jsp")
      #elseif( $ID_NEGERIPROJEK  == "14")
      #parse("app/ppt/PENARIKANMMK/mmk_kl.jsp")      
      #elseif( $ID_NEGERIPROJEK  == "1")
      #parse("app/ppt/PENARIKANMMK/mmk_johor.jsp")
      #elseif( $ID_NEGERIPROJEK  == "5")
      #parse("app/ppt/PENARIKANMMK/mmk_n9.jsp")
       #elseif( $ID_NEGERIPROJEK  == "8")
      #parse("app/ppt/PENARIKANMMK/mmk_perak.jsp")
      #elseif( $ID_NEGERIPROJEK  == "4")
      #parse("app/ppt/PENARIKANMMK/mmk_melaka.jsp")
      #elseif( $ID_NEGERIPROJEK  == "2")
      #parse("app/ppt/PENARIKANMMK/mmk_kedah.jsp")
      #elseif( $ID_NEGERIPROJEK  == "3")
      #parse("app/ppt/PENARIKANMMK/mmk_kelantan.jsp")
       #elseif( $ID_NEGERIPROJEK  == "9")
       
       
      #parse("app/ppt/PENARIKANMMK/mmk_perlis.jsp") 
   
   
      #elseif( $ID_NEGERIPROJEK  == "7")
      #parse("app/ppt/PENARIKANMMK/mmk_penang.jsp")
      #elseif( $ID_NEGERIPROJEK  == "6")
      #parse("app/ppt/PENARIKANMMK/mmk_pahang.jsp")
      #elseif( $ID_NEGERIPROJEK  == "11")
      #parse("app/ppt/PENARIKANMMK/mmk_terengganu.jsp")
      #else
      #parse("app/ppt/PENARIKANMMK/mmk_selangor.jsp") 
      #end
        
          
          
        </div>
        <div class="TabbedPanelsContent"></div>
        <div class="TabbedPanelsContent"></div>
      </div>
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
     
       #if($id_pembatalan != "" && $readmode == "view" )
      
       #if($ID_NEGERIPROJEK == "10")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport2')" />  
      </label>
       #elseif($ID_NEGERIPROJEK == "14")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport3')" />  
      </label>
      #elseif($ID_NEGERIPROJEK == "4")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport4')" />  
      </label>
      #elseif($ID_NEGERIPROJEK == "3")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport12')" />  
      </label>
      #elseif($ID_NEGERIPROJEK == "11")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport14')" />  
      </label>
      #elseif($ID_NEGERIPROJEK == "9")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport8')" />  
      </label>
       #elseif($ID_NEGERIPROJEK == "1")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport10')" />  
      </label>
        #elseif($ID_NEGERIPROJEK == "7")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport9')" />  
      </label>
      #elseif($ID_NEGERIPROJEK == "6")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport13')" />  
      </label>
       #elseif($ID_NEGERIPROJEK == "8")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport7')" />  
      </label>
       #elseif($ID_NEGERIPROJEK == "2")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport6')" />  
      </label>
      #elseif($ID_NEGERIPROJEK == "5")
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport5')" />  
      </label>
      #else
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  
      </label>
      #end
       #if($flag_mmk == "")
       <label> 
      <input type="button" name="cmdSemakan" id="cmdSemakan" value="Hantar Untuk Semakan" onclick="hantar()" />  
      </label>    
      #end
      #end
   
    
       <label></label>
     </div>      </td>
  </tr>
 
</table>


<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    <!--
       <tr>
        <td><a href="#" class="style2" onClick="mmkN9('$id_fail')"><font color="blue">Kertas MMK/KM/MB/LC (N9)</font></a></td>
      </tr> 
      <tr>
        <td><a href="#" class="style2" onClick="mmkKelantan('$id_fail')"><font color="blue">Kertas MMK/KM/MB/LC (Kelantan)</font></a></td>
      </tr> 
      -->
      <tr>
        <td><a href="#" class="style2" onClick=""><font color="blue">Kertas MMK/KM/MB/LC </font></a></td>
      </tr> 
      <!--
      <tr>
        <td><a href="#" class="style2" onClick="mmkJohor('$id_fail')"><font color="blue">Kertas MMK/KM/MB/LC (Johor)</font></a></td>
      </tr>   -->        
    </table>
</fieldset>


<fieldset id="tableReport2" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2"> 
      <tr>
        <td>  
        <a href="javascript:mmkSelangor1('$id_mmk','$nama_daerah1','','$id_fail','$no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Selangor)</font></a></td>
      </tr>      
      <tr>
        <td>  
      
        <a href="javascript:mmkSelangor2('$id_mmk','$nama_mukim_mukim','$no_lot_mmk ','$overallluas','','')" class="style2" ><font color="blue">Kertas Ringkasan Daripada Pengarah Tanah Dan Galian Selangor Darul Ehsan
 </font></a></td>
      </tr>       
       <tr>
        <td>  
      
        <a href="javascript:mmkSelangor3('$id_pembatalan','$nama_pengarah')" class="style2" ><font color="blue">Jadual Penarikan Balik Lot
 </font></a></td>
 <tr>
        <td>  
      
        <a href="javascript:mmkSelangor4('$id_fail')" class="style2" ><font color="blue">Borang LA
 </font></a></td>
      </tr> 
        

    </table>
</fieldset>




<fieldset id="tableReport3" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

  
      <tr>
        <td>  
        <a href="javascript:mmkKL1('$id_mmk','( $!maklumat_hakmilik_mmk_lot.size() ) Lot','$maklumat_hakmilik_mmk_hektar hektar ($maklumat_hakmilik_mmk_ekar  ekar)','$!id_fail','$!no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Wilayah Persekutuan Kuala Lumpur)</font></a></td>
      </tr> 
      
     
      
       <tr>
        <td>  
      
        <a href="javascript:mmkKL2('$id_pembatalan')" class="style2" ><font color="blue">Jadual Penarikan Balik Lot
 </font></a></td>
      </tr> 
        
        
        
        
      
    </table>
</fieldset>



<fieldset id="tableReport4" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

  
      <tr>
        <td>  
        <a href="javascript:mmkMelaka('$id_mmk','$!id_fail','$!no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Melaka)</font></a></td>
      </tr> 
      
     
  
      
    </table>
</fieldset>


<fieldset id="tableReport5" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

  
      <tr>
        <td>  
        <a href="javascript:mmkN9('$id_mmk','$!id_fail','$!no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Negeri Sembilan)</font></a></td>
      </tr> 
      
     
       <tr>
        <td>  
      
        <a href="javascript:mmkN9_1('$id_pembatalan')" class="style2" ><font color="blue">Jadual Penarikan Balik Lot
 </font></a></td>
      </tr> 
        
        
        
        
      
    </table>
</fieldset>


<fieldset id="tableReport6" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

  
      <tr>
        <td>  
        <a href="javascript:mmkKedah('$id_mmk','$!id_fail','$!no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Kedah)</font></a></td>
      </tr> 
         
        
        
      
    </table>
</fieldset>

<fieldset id="tableReport7" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

  
      <tr>
        <td>  
        <a href="javascript:mmkPerak('$id_mmk','$!id_fail','$!no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Perak)</font></a></td>
      </tr> 
      
      <tr>
		 <td><a href="javascript:cetakBorangAkta486('$!id_permohonan','$!id_mmk')"><font color="blue">Borang Akta 486</font></a></td>
	  </tr>   
      
    </table>
</fieldset>

<fieldset id="tableReport8" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

  
      <tr>
        <td>  
        <a href="javascript:mmkPerlis('$id_mmk','$!id_fail','$!no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Perlis)</font></a></td>
      </tr> 
         
      
    </table>
</fieldset>

<fieldset id="tableReport9" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

  
      <tr>
        <td>  
        <a href="javascript:mmkPenang('$id_mmk','$!id_fail','$!no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Pulau Pinang)</font></a></td>
      </tr> 
         
      
    </table>
</fieldset>

<fieldset id="tableReport13" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

  
      <tr>
        <td>  
        <a href="javascript:mmkPahang('$id_mmk','$!id_fail','$!no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Pahang)</font></a></td>
      </tr> 
         
      
    </table>
</fieldset>

<fieldset id="tableReport12" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

  
      <tr>
        <td>  
        <a href="javascript:mmkKelantan('$id_mmk','$!id_fail','$!no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Kelantan)</font></a></td>
      </tr> 
         
      
    </table>
</fieldset>

<fieldset id="tableReport14" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

  
      <tr>
        <td>  
        <a href="javascript:mmkTerengganu('$id_mmk','$!id_fail','$!no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Terengganu)</font></a></td>
      </tr> 
         
      
    </table>
</fieldset>


<fieldset id="tableReport10" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">

  
      <tr>
        <td>  
        <a href="javascript:mmkJohor('$id_mmk','$!id_fail','$!no_fail')" class="style2" ><font color="blue">Kertas MMK/KM/MB/LC (Johor)</font></a></td>
      </tr> 
         
      
    </table>
</fieldset>

  <input type="hidden" name="sub_command" id="sub_command" />
  <input type="hidden" name="subminor_command" id="subminor_command" />
  <input type="hidden" name="location" id="location" />
  <input type="hidden" name="point" id="point" />
  <input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$id_pembatalan" />
  <input type="hidden" name="screen_name" id="screen_name" value="s2" />
  <input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
  <input type="hidden" name="alert_message" id="alert_message" />
  <input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
  <input type="hidden" name="jumlah_semua" id="jumlah_semua" />
  <input type="hidden" name="id_mmk" id="id_mmk" value="$id_mmk" />
  <input type="hidden" name="flag_mmk" id="flag_mmk" value="$flag_mmk" />
  
  
 




<script type="text/javascript">



var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});

var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';

//alert("location:"+'$location');

//alert("point:"+'$point');



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
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Penyediaan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "penyediaan";
	document.${formName}.point.value = "penyediaan";
	document.${formName}.submit();
	}
	}
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Penyediaan";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "penyediaan";
	document.${formName}.point.value = "penyediaan";
	document.${formName}.submit();
	}
}

function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Penyediaan";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "penyediaan";
	document.${formName}.point.value = "penyediaan";
	document.${formName}.submit();
	}
}

function kemaskini()
{
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Penyediaan";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "penyediaan";
	document.${formName}.point.value = "penyediaan";
	document.${formName}.submit();
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


function penyediaan(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Penyediaan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();

}
function hantar()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Penyediaan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.flag_mmk.value = "1";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "penyediaan";
	document.${formName}.point.value = "penyediaan";
	document.${formName}.submit();
	}
	
}

function semakan(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Semakan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();

}

function keputusan(id_permohonan,id_pembatalan)
{

	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();

}
			
			/*
			
            var area = new FCKeditor("txtTujuan");
	      	area.BasePath = '/${appname}/library/fck/' ;
	      	area.Height = 200;
	      	area.Width = 600;
	      	area.ReplaceTextarea();  
	if(txtPerkaraRayu == "" || txtPerkaraRayu == null)
	{
		alert("Sila masukkan \"Perkara Yang Dirayu\" terlebih dahulu");
		document.${formName}.txtPerkaraRayu.focus();  
		return;
	}
	*/




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








 //mmkN9('$id_mmk','$!id_fail','$!no_fail')
   //   mmkN9_1('$id_pembatalan')




function mmkN9(id_mmk,id_fail,no_fail)
{
 
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&report=mmk_N9&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}


function mmkKedah(id_mmk,id_fail,no_fail)
{
 
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&report=mmk_Kedah&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}


function mmkPenang(id_mmk,id_fail,no_fail)
{
 
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&report=mmk_Penang&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}

function mmkPahang(id_mmk,id_fail,no_fail)
{
 
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&report=mmk_Pahang&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}

function mmkKelantan(id_mmk,id_fail,no_fail)
{
 
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&report=mmk_Kelantan&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}


function mmkTerengganu(id_mmk,id_fail,no_fail)
{
 
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&report=mmk_Terengganu&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}


function cetakBorangAkta486(id_permohonan,id_mmk)
{
 	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+id_permohonan+"&id_mmk="+id_mmk+"&report=borangAkta486Penarikan&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
 	if (hWnd.focus != null) hWnd.focus();

}
function mmkPerak(id_mmk,id_fail,no_fail)
{
 
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&report=mmk_Perak&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}

function mmkPerlis(id_mmk,id_fail,no_fail)
{
 
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&report=mmk_Perlis&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}

function mmkJohor(id_mmk,id_fail,no_fail)
{
 
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&report=mmk_Johor&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}

function mmkN9_1(id_penarikan)
{
 
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_penarikan="+id_penarikan+"&report=jadual_mmk_N9&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}




function mmkSelangor1(id_mmk,nama_daerah1,nama_pengarah,id_fail,no_fail)
{

           var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
	
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&nama_pengarah="+nama_pengarah+"&tarikh_surat="+currentDate+"&nama_daerah="+nama_daerah1+"&id_fail="+id_fail+"&no_fail="+no_fail+"&report=mmk_selangor&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();


}

function mmkMelaka(id_mmk,id_fail,no_fail)
{

           var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
	
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&report=mmk_melaka&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();


}





function mmkKL1(id_mmk,bil_lot,luas_lot,id_fail,no_fail)
{    	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&bil_lot="+bil_lot+"&luas_lot="+luas_lot+"&report=mmk_kl&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();


}

function mmkKL2(id_penarikan)
{    	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_penarikan="+id_penarikan+"&report=mmk_jadual_kl&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();


}


function mmkSelangor2(id_mmk,nama_mukim_mukim,no_lot_mmk,overallluas,nama_menteri,nama_pengarah)
{
           var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
/*		   
    var url = "../servlet/ekptg.report.ppt.MmkPenarikanBalikSelangor2?id_mmk="+id_mmk+"&nama_pengarah="+nama_pengarah+"&nama_menteri="+nama_menteri+"&luas_lot="+overallluas+"&senarai_mukim="+nama_mukim_mukim+"&senarai_lot="+no_lot_mmk; 
 
  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	*/
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_mmk="+id_mmk+"&nama_pengarah="+nama_pengarah+"&nama_menteri="+nama_menteri+"&luas_lot="+overallluas+"&senarai_mukim="+nama_mukim_mukim+"&senarai_lot="+no_lot_mmk+"&report=mmk_mb_selangor&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();



}


function mmkSelangor3(id_penarikan,nama_pengarah)
{
           var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
	/*	   
    var url = "../servlet/ekptg.report.ppt.MmkPenarikanBalikSelangor3?id_penarikan="+id_penarikan+"&nama_pengarah="+nama_pengarah; 
 
  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();*/
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_penarikan="+id_penarikan+"&nama_pengarah="+nama_pengarah+"&report=mmk_mb_selangor_jadual&id_permohonan="+document.${formName}.id_permohonan.value; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();


}

function mmkSelangor4(id_fail)
{
	var currentTime = new Date();
	   var month = currentTime.getMonth() + 1;
	   var day = currentTime.getDate();
	   var year = currentTime.getFullYear();
	   var currentDate = day + "/" + month + "/" + year;
	
	var url = "../servlet/ekptg.report.ppt.BorangLA?id_Fail="+id_fail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();

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
new_window.document.write("Sila semak untuk memilih penarikan balik bagi kesemua senarai lot.");
}
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


}

new_window.document.write("<br>");
new_window.document.write("</body></html>");
new_window.document.close(); 







}


function close_window() 
{
new_window.close();
}



function laporanMMKSelangor()
{


	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Maklumat_Am";
	document.${formName}.subminor_command.value = "UpdateSuburusan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();


var txtTujuan = document.${formName}.txtTujuan.value;
var txtLatarbelakang = document.${formName}.txtLatarbelakang.value;
var txtSyor = document.${formName}.txtSyor.value;
var id_mmk = document.${formName}.id_mmk.value;
var id_pembatalan = document.${formName}.id_pembatalan.value;

var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_pembatalan="+id_pembatalan+"&report=mmk_selangor&id_permohonan="+document.${formName}.id_permohonan.value;
  
    var hWnd = window.open(url,'printuser','width=800,height=800, resizable=yes,scrollbars=yes,menubar=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

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




  </script>

