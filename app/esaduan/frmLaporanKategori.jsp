
<table width="90%" border="0" align="center">
  <tr class="table_headerkpi">
   
    <td width="30%"  ><b>KATEGORI ADUAN</b></td>
    <td width="15%" align="center"><b>JUMLAH ADUAN</b></td>
    #if($id_statusesaduan_cari == "" || $id_statusesaduan_cari == "16121")
    <td width="15%" align="center"><b>ADUAN BARU</b></td>
    #end
    #if($id_statusesaduan_cari == "" || $id_statusesaduan_cari == "16122")
    <td width="15%" align="center" ><b>DALAM TINDAKAN</b></td>
    #end
    #if($id_statusesaduan_cari == "" || $id_statusesaduan_cari == "16123")
    <td width="15%" align="center"><b>SELESAI</b></td>
    #end
    <td width="10%" align="center"><b>PERATUS SELESAI (%)</b></td>
    
  </tr>
   #set ($count_jumlah_aduan = 0)
   #set ($count_aduan_baru = 0)
   #set ($count_aduan_dalam_tindakan = 0)
   #set ($count_aduan_selesai = 0)
   
   #if($list_laporanKategori.size()>0)
        #set ($count = 0)
        #foreach ( $laporan in $list_laporanKategori )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2" )
        #else
        #set( $row = "row1" )
        #end
  
   <tr >
   
   
   #set ($count_jumlah_aduan = $count_jumlah_aduan + $!laporan.jumlah_aduan)
   #set ($count_aduan_baru = $count_aduan_baru + $!laporan.aduan_baru)
   #set ($count_aduan_dalam_tindakan = $count_aduan_dalam_tindakan + $!laporan.aduan_dalam_tindakan)
   #set ($count_aduan_selesai = $count_aduan_selesai + $!laporan.aduan_selesai)   
   
    <td   class="$row" >$!laporan.jenis_aduan</td>
    <td  align="center" class="$row">$!laporan.jumlah_aduan 
    <input value="$!laporan.jumlah_aduan" type="hidden" name="jumlah_aduan_kategori" id="jumlah_aduan_kategori"  />
    </td>
     #if($id_statusesaduan_cari == "" || $id_statusesaduan_cari == "16121")
    <td  align="center" class="$row">$!laporan.aduan_baru</td>
    #end
    #if($id_statusesaduan_cari == "" || $id_statusesaduan_cari == "16122")
    <td  align="center" class="$row" >$!laporan.aduan_dalam_tindakan</td>
    #end
    #if($id_statusesaduan_cari == "" || $id_statusesaduan_cari == "16123")
   <td  align="center" class="$row" >$!laporan.aduan_selesai
   
    </td>
    #end
     <input value="$!laporan.aduan_selesai" type="hidden" name="jumlah_selesai_kategori" id="jumlah_selesai_kategori"  />
    
    
    <td  align="center" class="$row" >
    <input type="hidden" name="peratus_kategori_hidden" id="peratus_kategori_hidden"  />
    <div id="peratus_kategori$!laporan.BIL"></div></td>
    
  </tr>
  
   #end
   
    <tr class="table_headerkpi" >
 
    <td    ><b>JUMLAH KESELURUHAN</b></td>
    <td  align="center" ><b>$count_jumlah_aduan</b>
    <input value="$count_jumlah_aduan" type="hidden" name="jumlah_aduan_total_kategori" id="jumlah_aduan_total_kategori"  />
    </td>
      #if($id_statusesaduan_cari == "" || $id_statusesaduan_cari == "16121")
    <td  align="center" ><b>$count_aduan_baru</b></td>
    #end
    #if($id_statusesaduan_cari == "" || $id_statusesaduan_cari == "16122")
    <td  align="center"  ><b>$count_aduan_dalam_tindakan</b></td>
    #end
    #if($id_statusesaduan_cari == "" || $id_statusesaduan_cari == "16123")
   <td  align="center"  ><b>$count_aduan_selesai</b></td>
    #end
   
      <input value="$count_aduan_baru" type="hidden" name="jumlah_aduan_baru_total_kategori" id="jumlah_aduan_baru_total_kategori"  />
    
   
    <input value="$count_aduan_dalam_tindakan" type="hidden" name="jumlah_aduan_tindakan_total_kategori" id="jumlah_aduan_tindakan_total_kategori"  />
    
    
    
    <input value="$count_aduan_selesai" type="hidden" name="jumlah_selesai_total_kategori" id="jumlah_selesai_total_kategori"  />
    </td>
    <td  align="center"  ><b>
    <input type="hidden" name="peratus_kategori_total_hidden" id="peratus_kategori_total_hidden"  />
    <div id="peratus_kategori_total"></div>
    </b></td>
    
  </tr>
         <tr>  
    <td colspan="6" align="center">
    
   <!-- <input type="button" name="cmdCetakLaporan" value="Cetak" onClick="cetak('1')" >-->
    <!-- <input type="button" name="cmdCetakLaporan" value="Cetak" onClick="cetakLaporan('1')" >-->
    
    
    
    </td>    
  </tr> 
         #else
  <tr>  
    <td colspan="6">Tiada Rekod</td>    
  </tr>
  #end
  
</table>

<script>
//alert("woit");
setPeratuKategori();
setPeratuKategoriTotal();
function setPeratuKategori(){  
var c = 0;
if(document.${formName}.jumlah_aduan_kategori.length > 1)
{     
	  for (i = 0; i < document.${formName}.jumlah_aduan_kategori.length; i++)
	  {
		  if(document.${formName}.jumlah_selesai_kategori[i].value > 0)
		  {
		  var total =  (document.${formName}.jumlah_selesai_kategori[i].value / document.${formName}.jumlah_aduan_kategori[i].value) * 100;
		  document.${formName}.peratus_kategori_hidden[i].value = total.toFixed(2);
		  $jquery("#peratus_kategori"+[i+1]).html("<font color='blue'><strong>"+total.toFixed(2)+"%</strong></font>");
		  }
		  else
		  {
		  document.${formName}.peratus_kategori_hidden[i].value = 0;
		  $jquery("#peratus_kategori"+[i+1]).html("<font color='blue'><strong>"+0+"%</strong></font>");
		  }
	  }  
}
else
{
 	  if(document.${formName}.jumlah_selesai_kategori.value > 0)
      {
	  var total =  (document.${formName}.jumlah_selesai_kategori.value / document.${formName}.jumlah_aduan_kategori.value) * 100;
	  document.${formName}.peratus_kategori_hidden.value = total.toFixed(2);
	  $jquery("#peratus_kategori1").html("<font color='blue'><strong>"+total.toFixed(2)+"%</strong></font>");
	  }
	  else
	  {
	  document.${formName}.peratus_negeri_hidden.value = 0;
	  $jquery("#peratus_kategori1").html("<font color='blue'><strong>"+0+"%</strong></font>");
	  }
}	 
  
	  
}

function setPeratuKategoriTotal(){  


 	  if(document.${formName}.jumlah_selesai_total_kategori.value > 0)
      {
	  var total =  (document.${formName}.jumlah_selesai_total_kategori.value / document.${formName}.jumlah_aduan_total_kategori.value) * 100;
	  document.${formName}.peratus_kategori_total_hidden.value = total.toFixed(2);
	  $jquery("#peratus_kategori_total").html("<font color='white'><strong>"+total.toFixed(2)+"%</strong></font>");
	  }
	  else
	  {
	  document.${formName}.peratus_kategori_total_hidden.value = 0;
	  $jquery("#peratus_kategori_total").html("<font color='white'><strong>"+0+"%</strong></font>");
	  }
	 
  
	  
}

</script>
