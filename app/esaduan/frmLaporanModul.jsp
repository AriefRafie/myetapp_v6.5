
<table width="90%" border="0" align="center">
  <tr class="table_headerkpi">
   
    <td width="30%"  ><b>MODUL / URUSAN</b></td>
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
   
   #if($list_laporanModul.size()>0)
        #set ($count = 0)
        #foreach ( $laporan in $list_laporanModul )
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
   
    <td   class="$row" >$!laporan.nama_modul</td>
    <td  align="center" class="$row">$!laporan.jumlah_aduan  
    <input value="$!laporan.jumlah_aduan" type="hidden" name="jumlah_aduan" id="jumlah_aduan"  />
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
    <input value="$!laporan.aduan_selesai" type="hidden" name="jumlah_selesai" id="jumlah_selesai"  />
    
   
    
    
    <td  align="center" class="$row" >
    <input type="hidden" name="peratus_modul_hidden" id="peratus_modul_hidden"  />
    <div id="peratus_modul$!laporan.BIL"></div></td>
    
  </tr>
  
   #end
   
    <tr class="table_headerkpi" >
 
    <td    ><b>JUMLAH KESELURUHAN</b></td>
    <td  align="center" ><b>$count_jumlah_aduan</b>
    <input value="$count_jumlah_aduan" type="hidden" name="jumlah_aduan_total" id="jumlah_aduan_total"  />
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
    
    
    
   
    <input value="$count_aduan_baru" type="hidden" name="jumlah_aduan_baru_total" id="jumlah_aduan_baru_total"  />
    
   
    <input value="$count_aduan_dalam_tindakan" type="hidden" name="jumlah_aduan_tindakan_total" id="jumlah_aduan_tindakan_total"  />
    
    
    <input value="$count_aduan_selesai" type="hidden" name="jumlah_selesai_total" id="jumlah_selesai_total"  />
    
    <td  align="center"  ><b>
    <input type="hidden" name="peratus_modul_total_hidden" id="peratus_modul_total_hidden"  />
    <div id="peratus_modul_total"></div>
    </b></td>
    
  </tr>
   <tr>  
    <td colspan="6" align="center">
    
    <input type="button" name="cmdCetakLaporan" value="Cetak" onClick="cetakLaporan('1')" >
    
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
setPeratuModul();
setPeratuModulTotal();




function setPeratuModul(){ 

var c = 0;
if(document.${formName}.jumlah_aduan.length > 1)
{     
	  for (i = 0; i < document.${formName}.jumlah_aduan.length; i++)
	  {
		  if(document.${formName}.jumlah_selesai[i].value > 0)
		  {
		  var total =  (document.${formName}.jumlah_selesai[i].value / document.${formName}.jumlah_aduan[i].value) * 100;
		  document.${formName}.peratus_modul_hidden[i].value = total.toFixed(2);
		  $jquery("#peratus_modul"+[i+1]).html("<font color='blue'><strong>"+total.toFixed(2)+"%</strong></font>");
		  }
		  else
		  {
		  document.${formName}.peratus_modul_hidden[i].value = 0;
		  $jquery("#peratus_modul"+[i+1]).html("<font color='blue'><strong>"+0+"%</strong></font>");
		  }
	  }  
}
else
{
 	  if(document.${formName}.jumlah_selesai.value > 0)
      {
	  var total =  (document.${formName}.jumlah_selesai.value / document.${formName}.jumlah_aduan.value) * 100;
	  document.${formName}.peratus_modul_hidden.value = total.toFixed(2);
	  $jquery("#peratus_modul1").html("<font color='blue'><strong>"+total.toFixed(2)+"%</strong></font>");
	  }
	  else
	  {
	  document.${formName}.peratus_modul_hidden.value = 0;
	  $jquery("#peratus_modul1").html("<font color='blue'><strong>"+0+"%</strong></font>");
	  }
}	 
  
	  
}

function setPeratuModulTotal(){  


 	  if(document.${formName}.jumlah_selesai_total.value > 0)
      {
	  var total =  (document.${formName}.jumlah_selesai_total.value / document.${formName}.jumlah_aduan_total.value) * 100;
	  document.${formName}.peratus_modul_total_hidden.value = total.toFixed(2);
	  $jquery("#peratus_modul_total").html("<font color='white'><strong>"+total.toFixed(2)+"%</strong></font>");
	  }
	  else
	  {
	  document.${formName}.peratus_modul_total_hidden.value = 0;
	  $jquery("#peratus_modul_total").html("<font color='white'><strong>"+0+"%</strong></font>");
	  }
	 
  
	  
}





</script>
