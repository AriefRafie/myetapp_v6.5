





#if($!role == "adminppk")
<table width="70%" border="0" cellspacing="2" cellpadding="2">
<tr>
 <td colspan="8">
 <font color="blue">
 <i>Ruanganan ini bertujuan untuk <b>Pegawai</b> memberi kebenaran kepada pengguna-pengguna bagi membuat pengemaskinian maklumat</i>
 </font>
 </td>
 </tr>
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
      
#else

#if($listAgihan_aduan.size()>0)
<table width="70%" border="0" cellspacing="2" cellpadding="2">
<tr>
 <td colspan="8">
 <font color="blue">
 <i>Ruanganan ini bertujuan untuk <b>Pegawai</b> memberi kebenaran kepada pengguna-pengguna bagi membuat pengemaskinian maklumat</i>
 </font>
 </td>
 </tr>
    #if($listTechTeam_aduan.size() > 0)
    <tr>
    <td colspan="5" align="left"><input style="display:none" id="cmdAgihanTugas" name="cmdAgihanTugas" type="button"  value="Simpan Agihan" onClick="daftarAgih()">
    
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
      <input style="display:none" type="checkbox" name="selectallagih" id="selectallagih" onClick="doCheckAll1_agihan(document.${formName}.selectagih,document.${formName}.catatan_agihan,document.${formName}.selectallagih)" title="Semak untuk pilih semua" />
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
    $catatan_agihan
    <span id='$nama_span' style="display:none">
  
         
     </span>    
           </td>   
    <td class="$row" valign="top" ><div align="center">
    
    #set($check_tech = "")
    #foreach($agih in $listAgihan_aduan)   
    #if($!list1.user_id == $!agih.user_id)
    #set($check_tech = "checked")
    #end
    #end
    
     <input style="display:none" type="checkbox" name="selectagih" id="selectagih" $check_tech onClick="doUpdateCheckAll1_agihan(document.${formName}.selectagih,document.${formName}.catatan_agihan,document.${formName}.selectallagih)" value="$list1.user_id" >
     
     #if($check_tech == "checked")
     <img  height="16" width="16" src="../img/validyes.png" alt="" border="0"/>
     #else
     <img  height="16" width="16" src="../img/validno.png" alt="" border="0"/>
     #end
  
     
     </div></td>
  </tr>
  #end 
  #else
  <tr>  
    <td colspan="5">Tiada Rekod</td>    
  </tr>
  #end
      </table>
      
   #else   
      
      <table width="100%">
<tr>
 <td colspan="3">
 <font color="red">
 <i>Tiada rekod agihan tugas</i>
 </font>
 </td>
 </tr>
 </table>
 
 #end

#end      
      
 <script type="text/javascript" >
 
doUpdateCheckAll1_agihan(document.${formName}.selectagih,document.${formName}.catatan_agihan,document.${formName}.selectallagih);
ResetScrollPosition();

function doCheckAll1_agihan(selectagih_x,catatan_agihan_x,selectallagih_x){   


if (selectallagih_x.checked == true){
        if (selectagih_x.length == null){
            selectagih_x.checked = true;
			
			 $jquery("#remove_area0").html("<textarea name='catatan_agihan' placeholder='Sila Masukkan Catatan Agihan...' id='catatan_agihan'  cols='35'"+           
         " onBlur='onSize(this,400);set_temp_catatan(0,0,this.value);' onKeyup='onSize(this,400);set_temp_catatan(0,0,this.value);'  onKeydown='onSize(this,400);set_temp_catatan(0,0,this.value);'  >"+document.${formName}.nama_span_hidden.value+"</textarea> "); 
		 
        } else {
            for (i = 0; i < selectagih_x.length; i++){
                selectagih_x[i].checked = true;
				
			 $jquery("#remove_area"+i).html("<textarea name='catatan_agihan' placeholder='Sila Masukkan Catatan Agihan...' id='catatan_agihan'  cols='35'"+           
         " onBlur='onSize(this,400);set_temp_catatan("+i+","+selectagih_x.length+",this.value);' onKeyup='onSize(this,400);set_temp_catatan("+i+","+selectagih_x.length+",this.value);'  onKeydown='onSize(this,400);set_temp_catatan("+i+","+selectagih_x.length+",this.value);'  >"+document.${formName}.nama_span_hidden[i].value+"</textarea> "); 
	   
            }


        }
    } else {
        if (selectagih_x.length == null){
            selectagih_x.checked = false;
			//catatan_agihan_x.style.display = "none";
			
			 $jquery("#remove_area0").html(""); 
			
			
        } else {
            for (i = 0; i < selectagih_x.length; i++){
                selectagih_x[i].checked = false;
				//catatan_agihan_x[i].style.display = "none";
				
				  $jquery("#remove_area"+i).html(""); 
				
				
            }
        }
    }
}


function doUpdateCheckAll1_agihan(selectagih_x,catatan_agihan_x,selectallagih_x){ 
var c = 0;
if(selectagih_x.length > 1)
{     
	  for (i = 0; i < selectagih_x.length; i++)
	  {
      if (selectagih_x[i].checked == false)
	  {	 
	  c++
	  
	  $jquery("#remove_area"+i).html(" "); 
	  //catatan_agihan_x[i].style.display = "none";
	  
       }
	  else
	  {
	  //catatan_agihan_x[i].style.display = "";
	  
	    $jquery("#remove_area"+i).html("<textarea name='catatan_agihan' placeholder='Sila Masukkan Catatan Agihan...' id='catatan_agihan'  cols='35'"+           
         " onBlur='onSize(this,400);set_temp_catatan("+i+","+selectagih_x.length+",this.value);' onKeyup='onSize(this,400);set_temp_catatan("+i+","+selectagih_x.length+",this.value);'  onKeydown='onSize(this,400);set_temp_catatan("+i+","+selectagih_x.length+",this.value);'  >"+document.${formName}.nama_span_hidden[i].value+"</textarea> "); 
	
	  
	  }
	 
	  }  
}
else
{
if (selectagih_x.checked == false)
{	 
c++;
 //catatan_agihan_x.style.display = "none";
  $jquery("#remove_area0").html(""); 
 
}
 else
 {
 //catatan_agihan_x.style.display = "";
   $jquery("#remove_area0").html("<textarea name='catatan_agihan' placeholder='Sila Masukkan Catatan Agihan...' id='catatan_agihan'  cols='35'"+           
         " onBlur='onSize(this,400);set_temp_catatan(0,0,this.value);' onKeyup='onSize(this,400);set_temp_catatan(0,0,this.value);'  onKeydown='onSize(this,400);set_temp_catatan(0,0,this.value);'  >"+document.${formName}.nama_span_hidden.value+"</textarea> "); 
 

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


 function daftarAgih(){	
	   	
   			//doAjaxCall${formName}("daftarAgih");
			document.${formName}.command.value = "daftarAgih";
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
			SaveScrollXY(); 
		     document.${formName}.submit();
			document.${formName}.cmdAgihanTugas.value = "Sila Tunggu....";
			
   	   }
	   
	   function SaveScrollXY() {
document.${formName}.ScrollX.value = document.body.scrollLeft;
document.${formName}.ScrollY.value = document.body.scrollTop;
}

function ResetScrollPosition() {

var hidx, hidy;
hidx = '$ScrollX';
hidy = '$ScrollY';
                        
if (typeof hidx != 'undefined' && typeof hidy != 'undefined') {
      window.scrollTo(hidx, hidy);
    }
	
}

</script>
    

