


<table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<strong>MAKLUMAT PERMOHONAN</strong>
 </td>
</tr>
</table>

      <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
      
      
			        	<tr>
			        	  <td width="1%"></td>
			              <td width="20%">No. Fail JKPTG</td>
			              <td width="1%">:</td>
			              <td width="78%">
                          
                          $!hash_maklumatprojek.NO_FAIL
                          
                        
                          </td>
			          	</tr>
                        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Kementerian</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatprojek.NAMA_KEMENTERIAN</td>
        </tr>
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Projek</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatprojek.NAMA_PROJEK</td>
        </tr>
          
        #if($!jenis_skrin == "BorangD" || $!jenis_skrin == "BorangK" || $!jenis_skrin == "PU")              
        <tr>
          <td  valign="top"></td>
          <td  valign="top">No. Warta</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatWarta.NO_WARTA</td>
        </tr>
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Tarikh Warta</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatWarta.TARIKH_WARTA</td>
        </tr>
        #end
        
     
      </table>
    
 
 <br />
 
 
 #if($!jenis_skrin == "PU" || $!jenis_skrin == "BorangK")
 <table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<strong>MAKLUMAT HAKMILIK</strong>
 </td>
</tr>
</table>

      <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
      
      
			        	<tr>
			        	  <td width="1%"></td>
			              <td width="20%">No. Hakmilik</td>
			              <td width="1%">:</td>
			              <td width="78%"><span >$hash_maklumatBorangK.KOD_JENIS_HAKMILIK $hash_maklumatBorangK.NO_HAKMILIK</span></td>
			          	</tr>
                        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">No. Lot</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$hash_maklumatBorangK.NO_LOT</span></td>
        </tr>
        
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Luas Asal</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$hash_maklumatBorangK.LUAS_ASAL</span></td>
        </tr>
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Luas Ambil</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$hash_maklumatBorangK.LUAS_AMBIL</span></td>
        </tr>
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Tarikh Borang K</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >$hash_maklumatBorangK.TARIKH_BORANGK</span></td>
        </tr>
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Jenis Pengambilan</td>
          <td   valign="top">:</td>
          <td  valign="top"><span >#if($hash_maklumatBorangK.STATUS_AMBIL == "P")
                           KESELURUHAN
                           #elseif($hash_maklumatBorangK.STATUS_AMBIL == "S")
                           SEBAHAGIAN
                           #else
                           
           #end </span></td>
        </tr>
        
       
        
     
      </table>
    
 
 <br />

#end
 
 
 
 #if($!jenis_skrin == "PU")
 <table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<strong>MAKLUMAT PU</strong>
 </td>
</tr>
</table>

      <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
      
      
			        	<tr>
			        	  <td width="1%"></td>
			              <td width="20%">No. PU</td>
			              <td width="1%">:</td>
			              <td width="78%">
                          
                          $!hash_maklumatPU.NO_PU
                          
                        
                          </td>
			          	</tr>
                        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">No. PA</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatPU.NO_PA</td>
        </tr>
        
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">No. Syit</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatPU.NO_SYIT</td>
        </tr>
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">No. Lot Baru</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatPU.NO_LOT_BARU</td>
        </tr>
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Luas PA</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatPU.LUAS_PA</td>
        </tr>
        
       
        
     
      </table>
    
 
 <br />

#end



#if($!jenis_skrin == "TarikBalik")
 <table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<strong>MAKLUMAT PENARIKAN BALIK</strong>
 </td>
</tr>
</table>

      <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
      
      
			        	<tr>
			        	  <td width="1%"></td>
			              <td width="20%">No. Rujukan Penarikan Balik</td>
			              <td width="1%">:</td>
			              <td width="78%">
                          
                          $!hash_maklumatTarikBalik.NO_PENARIKANBALIK
                          
                        
                          </td>
			          	</tr>
                        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Tarikh Penarikan Balik</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatTarikBalik.TARIKH_PENARIKANBALIK</td>
        </tr>
        
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Jenis Penarikan Balik</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatTarikBalik.JENIS_PB_KETERANGAN</td>
        </tr>
        
         <tr>
          <td  valign="top"></td>
          <td  valign="top">Sebab Penarikan Balik</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatTarikBalik.SEBAB_PENARIKANBALIK</td>
        </tr>
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">No. Warta Penarikan Balik</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatTarikBalik.NO_WARTA</td>
        </tr>
        
        <tr>
          <td  valign="top"></td>
          <td  valign="top">Tarikh Warta</td>
          <td   valign="top">:</td>
          <td  valign="top">$!hash_maklumatTarikBalik.TARIKH_WARTA</td>
        </tr>
        
       
        
     
      </table>
    
 
 <br />

#end



#if($!jenis_skrin == "TarikBalik" || $!jenis_skrin == "BorangI" || $!jenis_skrin == "BorangD" || $!jenis_skrin == "BorangB" || $!jenis_skrin == "hantarPelanChartingS8" || $!jenis_skrin == "hantarPelanChartingS4")
		<table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<b>SENARAI LOT YANG TERLIBAT</b>
 </td>
</tr>
</table>
         #parse("app/utils/record_paging_popup.jsp")
        <table align="center" width="100%" cellspacing="2" cellpadding="2" class="dashboard_sub"> 
        
       
          
            <tr class="table_header">
              <td scope="row"  align="center"><strong>Bil</strong></td>
              <td ><strong>No. Hakmilik</strong></td>
              <td ><strong>No. Lot</strong></td>
              <td align="right" ><strong>Luas Asal</strong></td>
              <td align="right" ><strong >Luas Ambil</strong></td>
               #if($!jenis_skrin == "BorangI")  
                            
                            <td align="center" ><b>Tarikh Borang I</b></td>
                           
                #end
                #if($!jenis_skrin == "BorangK")  
                            
                            <td align="center" ><b>Tarikh Borang K</b></td>
                            <td align="center" ><b>Jenis Pengambilan</b></td>
                #end
            </tr>
          #set ($list = "")
          #set ($counter = 0)
          #foreach ($list in $SenaraiFail)
          #set( $counter = $counter + 1 )
          	#if ($list.BIL == '')
                #set( $row = "row1" )
            #elseif (($list.BIL % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end           
           
          <tr>
            <td class="$row">
			$list.BIL
			</td>
           
            <td class="$row">$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
            
            <td class="$row">$list.NO_LOT</td>
            <td class="$row" align="right">$list.LUAS_ASAL</td>
            <td class="$row" align="right">$list.LUAS_AMBIL</td>
             #if($!jenis_skrin == "BorangI")  
                            
                            <td class="$row" align="center">$list.TARIKH_BORANGI</td>
                            #end
              #if($!jenis_skrin == "BorangK")  
                            
                            <td class="$row" align="center">$list.TARIKH_BORANGK</td>
                           <td class="$row" align="center"><!--$list.STATUS_AMBIL-->
                           
                           #if($list.STATUS_AMBIL == "P")
                           KESELURUHAN
                           #elseif($list.STATUS_AMBIL == "S")
                           SEBAHAGIAN
                           #else
                           
                           #end
                           
                           
                           </td>
                            
                            #end
            </tr>
          #end
          
           #if($counter == 0)
            <tr>
           
            <td colspan="10" class="row">
           Tiada rekod
           </td>
           </tr>
           #end 
          
        </table>
        
        
        #set($tajuk_upload = "")
  #if($!jenis_skrin == "BorangD")         
  	#set($tajuk_upload = "MUAT NAIK BORANG D")
  #elseif($!jenis_skrin == "BorangB")         
  	#set($tajuk_upload = "MUAT NAIK BORANG A & B")
  #elseif($!jenis_skrin == "BorangK")         
  	#set($tajuk_upload = "MUAT NAIK BORANG K")
  #elseif($!jenis_skrin == "BorangI")         
  	#set($tajuk_upload = "MUAT NAIK BORANG I")
  #elseif($!jenis_skrin == "hantarPelanChartingS8")         
  	#set($tajuk_upload = "MUAT NAIK PELAN")
  #elseif($!jenis_skrin == "hantarPelanChartingS4")         
  	#set($tajuk_upload = "MUAT NAIK PELAN")
  #elseif($!jenis_skrin == "PU")         
  	#set($tajuk_upload = "MUAT NAIK PELAN PA & B1")
  #elseif($!jenis_skrin == "TarikBalik")         
  	#set($tajuk_upload = "MUAT NAIK DOKUMEN")  
  #end
<br />
<table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<b>$tajuk_upload</b>
 </td>
</tr>
</table>

      <table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
      
      
			        	<tr>
			        	  <td width="1%"><font color="red">*</font></td>
			              <td width="20%">Tajuk Dokumen</td>
			              <td width="1%">:</td>
			              <td width="78%">
                          
                          #set($tajuk_lampiran = "")
                          
                          #if($!jenis_skrin == "BorangD")
                          	 #set($tajuk_lampiran = "Borang D")
                          #elseif($!jenis_skrin == "BorangB")
                          	 #set($tajuk_lampiran = "Borang")
                          #elseif($!jenis_skrin == "hantarPelanChartingS8")
                             #set($tajuk_lampiran = "Pelan")
                          #elseif($!jenis_skrin == "hantarPelanChartingS4")
                             #set($tajuk_lampiran = "Pelan")  
                          #elseif($!jenis_skrin == "BorangK")
                             #set($tajuk_lampiran = "Borang K") 
                          #elseif($!jenis_skrin == "PU")
                             #set($tajuk_lampiran = "Pelan")
                          #elseif($!jenis_skrin == "TarikBalik")
                             #set($tajuk_lampiran = "Penarikan Balik")                                
                          #end
                          
                          
                          <input type="text" name="tajuk" id="tajuk" maxlength="200" size="50" value="$tajuk_lampiran"  /></td>
			          	</tr>
        <tr>
          <td  valign="top"><font color="red">*</font></td>
          <td  valign="top">Lampiran</td>
          <td   valign="top">:</td>
          <td  valign="top"><input id="fileupload" name="fileupload" type="file" size="40"/></td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>
          
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="simpan('$id_permohonan','$jenis_skrin')">
           </td>
        </tr>
      </table>

<br />
      
   <div id="divListDokumen">SSSSSSSSSSSSSSSS</div>
      
			   
		
        
#end

<script>
//alert("s");
paparStat('$id_permohonan','$jenis_skrin');
</script>

 
 