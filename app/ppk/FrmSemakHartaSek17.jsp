#if($!skrin_online_17 != "yes")
<br />
<a href="javascript:setTable('tinggi_senarai')"  >
  #parse("app/ppk/tag_baru.jsp")
 &nbsp;
<font color="#0000FF">Semakan Status Pemegang Amanah Dan Pentadbir Harta-Harta Simati</font>
</a>
#end
<br />
<br />
<div id="tinggi_senarai" style="width:100%;overflow:auto;;" >

<fieldset>
<legend>SEMAKAN PEMEGANG AMANAH DAN PENTADBIR BAGI HARTA SIMATI</legend>
<font color="#0000FF"><b>HARTA TIDAK ALIH</b></font>
 <table align="center" width="100%">
       
         <tr>
          <td colspan="8" scope="row">
           
           
           
      
          </td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="2%" align="center"><strong>No</strong></td>
          <td width="15%"><strong>No. Fail</strong></td>
          <td width="10%"><strong>No. Hakmilik</strong></td>
          <td width="10%"><strong>No. Lot/PT</strong></td>
          <td width="10%"><strong>Jenis HTA</strong></td>
          <td width="10%"><strong>Negeri</strong></td>
          <td width="10%"><strong>Daerah</strong></td>
          <td width="18%"><strong>Mukim</strong></td>          
          <td width="7%"><strong>
          
          #if($!skrin_online_17 == "yes")
          Memilik Pemegang Amanah
          #else
          Memilik PA
          #end
          
          </strong></td>
          <td width="7%"><strong>
          
          #if($!skrin_online_17 == "yes")
          Memilik Pemegang Surat Kuasa Tadbir
          #else
          Memilik PT
          #end
          
          </strong></td>
          <td width="1%" style="display:none"></td>
          
         
        
        </tr>
        
        #if($!list_semakhta_check.size()>0)
        #set ($count = 0)
        #foreach ( $fail in $!list_semakhta_check )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2" )
        #else
        #set( $row = "row1" )
        #end
        
        #set($flag_pa_hta = "flag_pa_hta"+$count)
        #set($flag_pt_hta = "flag_pt_hta"+$count)
        #set($flag_selesai_hta = "flag_selesai_hta"+$count)
        
        #set($flag_pa_hta_cb = "flag_pa_hta_cb"+$count)
        #set($flag_pt_hta_cb = "flag_pt_hta_cb"+$count)
        #set($flag_selesai_hta_cb = "flag_selesai_hta_cb"+$count)
        <tr>
          <td class="$row" align="center">$!count
          <input type="hidden" id="id_hta_check" name="id_hta_check" value="$!fail.id_hta"  />
          <input type="hidden" id="id_perintahhtaobmst" name="id_perintahhtaobmst" value="$!fail.id_perintahhtaobmst"  />
           </td>
          <td class="$row">$!fail.no_fail</td>
          <td class="$row">$!fail.no_hakmilik </td>
          <td class="$row">$!fail.no_pt</td> 
          <td class="$row">
          
          
          #if($!fail.jenis_hta == "Y")
          ADA HAKMILIK
          #else
          TIADA HAKMILIK
          #end
          
          
          </td>        
          <td class="$row">$!fail.nama_negeri</td>
          <td class="$row">$!fail.nama_daerah</td>
          <td class="$row">$!fail.nama_mukim</td>          
          <td class="$row" >
          #if($!fail.flag_pa == "Y")
          #set($check_flag_pa_hta = "checked")
          #else
          #set($check_flag_pa_hta = "")
          #end 
          <input type="checkbox" name="$flag_pa_hta_cb" id="$flag_pa_hta_cb" $check_flag_pa_hta  onClick="checkHarta('$flag_pa_hta_cb','$flag_pa_hta')"  >
          <input type="hidden" name="$flag_pa_hta" id="$flag_pa_hta"  value="$!fail.flag_pa">
          </td> 
          <td class="$row">
          #if($!fail.flag_pt == "Y")
          #set($check_flag_pt_hta = "checked")
          #else
          #set($check_flag_pt_hta = "")
          #end 
          <input type="checkbox" name="$flag_pt_hta_cb" id="$flag_pt_hta_cb" $check_flag_pt_hta  onClick="checkHarta('$flag_pt_hta_cb','$flag_pt_hta')" >
          <input type="hidden" name="$flag_pt_hta" id="$flag_pt_hta"  value="$!fail.flag_pt" >
          </td> 
          <td class="$row" style="display:none">
           #if($!fail.flag_selesai == "Y")
          #set($check_flag_selesai_hta = "checked")
          #else
          #set($check_flag_selesai_hta = "")
          #end 
          <input type="checkbox" name="$flag_selesai_hta_cb" id="$flag_selesai_hta_cb" $check_flag_selesai_hta onClick="checkHarta('$flag_selesai_hta_cb','$flag_selesai_hta')" >
          <input type="hidden" name="$flag_selesai_hta" id="$flag_selesai_hta"  value="$!fail.flag_selesai" >
          </td>  
                 
        </tr>
        #end
        
         #else
  <tr>  
    <td colspan="12">Tiada Rekod</td>    
  </tr>
  #end
      </table>


<br>

<font color="#0000FF"><b>HARTA ALIH</b></font>
<table align="center" width="100%">
       
         <tr>
          <td colspan="8" scope="row">
           

      
          </td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="2%" align="center"><strong>No</strong></td>
          <td width="15%"><strong>No. Fail</strong></td>
          <td width="24%"><strong>Jenis Hakmilik</strong></td>
          <td width="20%"><strong>Jenama</strong></td>
          <td width="24%"><strong>
          No Rujukan UPT / No Daftar / No Akaun / No Ahli
          </strong></td>                
          <td width="7%"><strong>

		  #if($!skrin_online_17 == "yes")
          Memilik Pemegang Amanah
          #else
          Memilik PA
          #end
		  
		  </strong></td>
          <td width="7%"><strong>

		  #if($!skrin_online_17 == "yes")
          Memilik Pemegang Surat Kuasa Tadbir
          #else
          Memilik PT
          #end

		  </strong></td>
          <td width="1%" style="display:none"></td>
          
         
        
        </tr>
        
        #if($!list_semakha_check.size()>0)
        #set ($count = 0)
        #foreach ( $fail in $!list_semakha_check )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2" )
        #else
        #set( $row = "row1" )
        #end
        
        #set($flag_pa_ha = "flag_pa_ha"+$count)
        #set($flag_pt_ha = "flag_pt_ha"+$count)
        #set($flag_selesai_ha = "flag_selesai_ha"+$count)
        
        #set($flag_pa_ha_cb = "flag_pa_ha_cb"+$count)
        #set($flag_pt_ha_cb = "flag_pt_ha_cb"+$count)
        #set($flag_selesai_ha_cb = "flag_selesai_ha_cb"+$count)
        <tr>
          <td class="$row" align="center">$!count
          <input type="hidden" id="id_ha_check" name="id_ha_check" value="$!fail.id_ha"  />
          <input type="hidden" id="id_perintahhaobmst" name="id_perintahhaobmst" value="$!fail.id_perintahhaobmst"  />
           </td>
          <td class="$row">$!fail.no_fail</td>
          <td class="$row">$!fail.jenis_ha </td>
          <td class="$row">$!fail.jenama</td> 
          <td class="$row">$!fail.no_daftar</td>        
             
          <td class="$row">
          #if($!fail.flag_pa == "Y")
          #set($check_flag_pa_ha = "checked")
          #else
          #set($check_flag_pa_ha = "")
          #end 
          <input type="checkbox" name="$flag_pa_ha_cb" id="$flag_pa_ha_cb" $check_flag_pa_ha  onClick="checkHarta('$flag_pa_ha_cb','$flag_pa_ha')" >
          <input type="hidden" name="$flag_pa_ha" id="$flag_pa_ha"  value="$!fail.flag_pa" >
          </td> 
          <td class="$row">
          #if($!fail.flag_pt == "Y")
          #set($check_flag_pt_ha = "checked")
          #else
          #set($check_flag_pt_ha = "")
          #end 
          <input type="checkbox" name="$flag_pt_ha_cb" id="$flag_pt_ha_cb" $check_flag_pt_ha  onClick="checkHarta('$flag_pt_ha_cb','$flag_pt_ha')" >
          <input type="hidden" name="$flag_pt_ha" id="$flag_pt_ha"  value="$!fail.flag_pt" >
          </td> 
          <td class="$row" style="display:none">
           #if($!fail.flag_selesai == "Y")
          #set($check_flag_selesai_ha = "checked")
          #else
          #set($check_flag_selesai_ha = "")
          #end 
          <input type="checkbox" name="$flag_selesai_ha_cb" id="$flag_selesai_ha_cb" $check_flag_selesai_ha onClick="checkHarta('$flag_selesai_ha_cb','$flag_selesai_ha')" >
          <input type="hidden" name="$flag_selesai_ha" id="$flag_selesai_ha"  value="$!fail.flag_selesai" >
          </td>  
                 
        </tr>
        #end
        
         #else
  <tr>  
    <td colspan="12">Tiada Rekod</td>    
  </tr>
  #end
      </table>
      
      </fieldset>
<br />

</div>


<script type="text/javascript">
  

	
	
function checkHarta(flag_pa_hta_cb,flag_pa_hta)
{
if(document.getElementById(flag_pa_hta_cb).checked == true)
{
document.getElementById(flag_pa_hta).value = 'Y'; 
} 
else
{
document.getElementById(flag_pa_hta).value = 'T'; 
}

}


function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}


   
</script>