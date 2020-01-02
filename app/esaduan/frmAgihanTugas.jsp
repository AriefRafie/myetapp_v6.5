<table width="70%" border="0" cellspacing="2" cellpadding="2">
    #if($listTechTeam_aduan.size() > 0)
    <tr>
    <td colspan="5" align="left"><input id="cmdAgihanTugas" name="cmdAgihanTugas" type="button"  value="Simpan Agihan" onClick="daftarAgih()">
    
    </td>
    </tr>
    #end
    <tr class="table_header">
    <td width="5%">Bil</td>
    <td width="40%">Nama</td>
    <td width="50%">Catatan</td>
     #if($listTechTeam_aduan.size() > 0)
      <td width="5%" valign="top">
     
      <div align="center">
      <input type="checkbox" name="selectallagih" id="selectallagih" onClick="doCheckAll1_agihan(document.${formName}.selectagih,document.${formName}.catatan_agihan,document.${formName}.selectallagih)" title="Semak untuk pilih semua" />
      </div>
      
      </td>
      #end
  </tr>
 

 #if($listTechTeam_aduan.size() > 0)
  #foreach($list1 in $listTechTeam_aduan)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
  <tr>  
    <td class="$row" valign="top" >$list1.BIL</td>
  
    <td class="$row" valign="top" >$list1.user_name</td>
   
   
    <td class="$row">
   
    
    #set($catatan_agihan = "")
    #foreach($agih in $listAgihan_aduan)   
    #if($list1.user_id == $agih.user_id)
    #set($catatan_agihan = $!agih.catatan_agihan)
    #end
    #end
    
    
    #set($id_r = ($list1.BIL - 1))
   
    #set($nama_span = "remove_area"+$id_r)
    #set($nama_span_hidden = "hidden_area"+$id_r)
  
    <input type="hidden" id='nama_span_hidden' name='nama_span_hidden' value="$catatan_agihan"  />
    
    <span id='$nama_span'>
  
         
     </span>    
           </td>   
    <td class="$row" valign="top" ><div align="center">
    
    #set($check_tech = "")
    #foreach($agih in $listAgihan_aduan)   
    #if($!list1.user_id == $!agih.user_id)
    #set($check_tech = "checked")
    #end
    #end
    
     <input type="checkbox" name="selectagih" id="selectagih" $check_tech onClick="doUpdateCheckAll1_agihan(document.${formName}.selectagih,document.${formName}.catatan_agihan,document.${formName}.selectallagih)" value="$list1.user_id" >
  
     
     </div></td>
  </tr>
  #end 
  #else
  <tr>  
    <td colspan="5">Tiada Rekod</td>    
  </tr>
  #end
      </table>
    
#if($!flag_simpan_doc != "yes")
<script type="text/javascript" >

doUpdateCheckAll1_agihan(document.${formName}.selectagih,document.${formName}.catatan_agihan,document.${formName}.selectallagih)

</script>
#else
<script>
doUpdateCheckAll1_agihan_temp(document.${formName}.selectagih,document.${formName}.catatan_agihan,document.${formName}.selectallagih)


function doUpdateCheckAll1_agihan_temp(selectagih_x,catatan_agihan_x,selectallagih_x){ 
//document.${formName}.cmdAgihan.style.display = "none";
var c = 0;
if(selectagih_x.length > 1)
{     
	  for (i = 0; i < selectagih_x.length; i++)
	  {
      if (selectagih_x[i].checked == false)
	  {	 
	  c++
	  catatan_agihan_x[i].style.display = "none";
      }
	  else
	  {
	  catatan_agihan_x[i].style.display = "";
	  //document.${formName}.cmdAgihan.style.display = "";
	  }
	 
	  }  
}
else
{
if (selectagih_x.checked == false)
{	 
c++;
 catatan_agihan_x.style.display = "none";
 //document.${formName}.cmdAgihan.style.display = "none";
}
 else
 {
 catatan_agihan_x.style.display = "";
 //document.${formName}.cmdAgihan.style.display = "";

 }
	 	
}	 
   if(c>0)
	  {	  
	  selectallagih_x.checked = false;
	  }
	  else
	  {
	  selectallagih_x.checked = true;
	  }
	  
}

function doCheckAll1_agihan_temp(selectagih_x,catatan_agihan_x,selectallagih_x){    
    //document.${formName}.cmdAgihan.style.display = "none";
	
	if (selectallagih_x.checked == true){
        if (selectagih_x.length == null){
            selectagih_x.checked = true;
			catatan_agihan_x.style.display = "";
			//document.${formName}.cmdAgihan.style.display = "";
        } else {
            for (i = 0; i < selectagih_x.length; i++){
                selectagih_x[i].checked = true;
				catatan_agihan_x[i].style.display = "";
				//document.${formName}.cmdAgihan.style.display = "";
            }


        }
    } else {
        if (selectagih_x.length == null){
            selectagih_x.checked = false;
			catatan_agihan_x.style.display = "none";
			
        } else {
            for (i = 0; i < selectagih_x.length; i++){
                selectagih_x[i].checked = false;
				catatan_agihan_x[i].style.display = "none";
				
            }
        }
    }
}

</script>
#end