<table width="90%" border="0" align="center">
  <tr class="table_headerkpi">
   
    <td width="30%"  ><b>NEGERI</b></td>
    <td width="15%" align="center"><b>JUMLAH ADUAN</b></td>
    <td width="15%" align="center"><b>ADUAN BARU</b></td>
    <td width="15%" align="center" ><b>DALAM TINDAKAN</b></td>
    <td width="15%" align="center"><b>SELESAI</b></td>
    <td width="10%" align="center"><b>PERATUS SELESAI (%)</b></td>
    
  </tr>
   #set ($count_jumlah_aduan = 0)
   #set ($count_aduan_baru = 0)
   #set ($count_aduan_dalam_tindakan = 0)
   #set ($count_aduan_selesai = 0)
   
   #if($list_laporanNegeri.size()>0)
        #set ($count = 0)
        #foreach ( $laporan in $list_laporanNegeri )
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
   
    <td   class="$row" >$!laporan.nama_negeri</td>
    <td  align="center" class="$row">$!laporan.jumlah_aduan 
    <input value="$!laporan.jumlah_aduan" type="hidden" name="jumlah_aduan_negeri" id="jumlah_aduan_negeri"  />
    </td>
    <td  align="center" class="$row">$!laporan.aduan_baru</td>
    <td  align="center" class="$row" >$!laporan.aduan_dalam_tindakan</td>
    <td  align="center" class="$row" >$!laporan.aduan_selesai
    <input value="$!laporan.aduan_selesai" type="hidden" name="jumlah_selesai_negeri" id="jumlah_selesai_negeri"  />
    </td>
    <td  align="center" class="$row" >
    <input type="hidden" name="peratus_negeri_hidden" id="peratus_negeri_hidden"  />
    <div id="peratus_negeri$!laporan.BIL"></div></td>
    
  </tr>
  
   #end
   
    <tr class="table_headerkpi" >
 
    <td    ><b>JUMLAH KESELURUHAN</b></td>
    <td  align="center" ><b>$count_jumlah_aduan</b>
    <input value="$count_jumlah_aduan" type="hidden" name="jumlah_aduan_total_negeri" id="jumlah_aduan_total_negeri"  />
    </td>
    <td  align="center" ><b>$count_aduan_baru</b></td>
    <td  align="center"  ><b>$count_aduan_dalam_tindakan</b></td>
    <td  align="center"  ><b>$count_aduan_selesai</b>
    <input value="$count_aduan_selesai" type="hidden" name="jumlah_selesai_total_negeri" id="jumlah_selesai_total_negeri"  />
    </td>
    <td  align="center"  ><b>
    <input type="hidden" name="peratus_negeri_total_hidden" id="peratus_negeri_total_hidden"  />
    <div id="peratus_negeri_total"></div>
    </b></td>
    
  </tr>
        
         #else
  <tr>  
    <td colspan="6">Tiada Rekod</td>    
  </tr>
  #end
  
</table>

<script>
//alert("woit");
setPeratuNegeri();
setPeratuNegeriTotal();
function setPeratuNegeri(){  
var c = 0;
if(document.${formName}.jumlah_aduan_negeri.length > 1)
{     
	  for (i = 0; i < document.${formName}.jumlah_aduan_negeri.length; i++)
	  {
		  if(document.${formName}.jumlah_selesai_negeri[i].value > 0)
		  {
		  var total =  (document.${formName}.jumlah_selesai_negeri[i].value / document.${formName}.jumlah_aduan_negeri[i].value) * 100;
		  document.${formName}.peratus_negeri_hidden[i].value = total.toFixed(2);
		  $jquery("#peratus_negeri"+[i+1]).html("<font color='blue'><strong>"+total.toFixed(2)+"%</strong></font>");
		  }
		  else
		  {
		  document.${formName}.peratus_negeri_hidden[i].value = 0;
		  $jquery("#peratus_negeri"+[i+1]).html("<font color='blue'><strong>"+0+"%</strong></font>");
		  }
	  }  
}
else
{
 	  if(document.${formName}.jumlah_selesai_negeri.value > 0)
      {
	  var total =  (document.${formName}.jumlah_selesai_negeri.value / document.${formName}.jumlah_aduan_negeri.value) * 100;
	  document.${formName}.peratus_negeri_hidden.value = total.toFixed(2);
	  $jquery("#peratus_negeri1").html("<font color='blue'><strong>"+total.toFixed(2)+"%</strong></font>");
	  }
	  else
	  {
	  document.${formName}.peratus_negeri_hidden.value = 0;
	  $jquery("#peratus_negeri1").html("<font color='blue'><strong>"+0+"%</strong></font>");
	  }
}	 
  
	  
}

function setPeratuNegeriTotal(){  


 	  if(document.${formName}.jumlah_selesai_total_negeri.value > 0)
      {
	  var total =  (document.${formName}.jumlah_selesai_total_negeri.value / document.${formName}.jumlah_aduan_total_negeri.value) * 100;
	  document.${formName}.peratus_negeri_total_hidden.value = total.toFixed(2);
	  $jquery("#peratus_negeri_total").html("<font color='white'><strong>"+total.toFixed(2)+"%</strong></font>");
	  }
	  else
	  {
	  document.${formName}.peratus_negeri_total_hidden.value = 0;
	  $jquery("#peratus_negeri_total").html("<font color='white'><strong>"+0+"%</strong></font>");
	  }
	 
  
	  
}

</script>
