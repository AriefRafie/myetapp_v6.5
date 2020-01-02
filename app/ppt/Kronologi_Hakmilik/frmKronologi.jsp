

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
<fieldset>
<legend>CARIAN HAKMILIK</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">


<tr>
  <td  width="20%"></td>
  <td align="left" valign="top" width="15%">NO. HAKMILIK</td>
  <td valign="top" width="1%">:</td>
  <td valign="top" width="64%">
    
    
    
    <select name="ID_JENISHAKMILIK" id="ID_JENISHAKMILIK" > 
      <option value="" >SILA PILIH JENIS HAKMILIK</option>           
            #foreach($ja in $listJenisHakmilik) 
            <option value="$!ja.ID_JENISHAKMILIK" #if($ja.ID_JENISHAKMILIK==$ID_JENISHAKMILIK) selected="selected" #end>$!ja.KOD_JENIS_HAKMILIK - $!ja.JENISHAKMILIK</option>
            #end 
            </select>
            
            <input name="NO_HAKMILIK" type="text" id="NO_HAKMILIK"  value="$NO_HAKMILIK"  style="text-transform:uppercase; width:25%" onBlur="this.value=this.value.toUpperCase()" />

    
    </td>
</tr>


        
        
        <tr>
          <td>&nbsp;</td>
          <td align="left" valign="top">NO. LOT / NO. PT</td>
          <td valign="top">:</td>
          <td valign="top">
          
          <input name="NO_LOT_PT" type="text" id="NO_LOT_PT"  value="$NO_LOT_PT"    style="text-transform:uppercase; width:25%" onBlur="this.value=this.value.toUpperCase()" />

          
           </td>
        </tr>


		<tr>
          <td>&nbsp;</td>
          <td align="left" valign="top">NO. FAIL</td>
          <td valign="top">:</td>
          <td valign="top">
          
          <input name="NO_FAIL" type="text" id="NO_FAIL"  value="$NO_FAIL"  style="text-transform:uppercase; width:25%" onBlur="this.value=this.value.toUpperCase()" />

          
           </td>
        </tr>
        
        
        <tr>
          <td>&nbsp;</td>
          <td align="left" valign="top">PROJEK</td>
          <td valign="top">:</td>
          <td valign="top">
          
          
          
          <textarea  name="NAMA_PROJEK" id="NAMA_PROJEK" cols="50" style="text-transform:uppercase;"   rows="3"  placeholder="Sila Masukkan Nama Projek..."         
         onBlur="check_length(this,'4000','NAMA_PROJEK_check','NAMA_PROJEK_num','normal','yes','Nama Projek');this.value=this.value.toUpperCase();"  
         onKeyup="check_length(this,'4000','NAMA_PROJEK_check','NAMA_PROJEK_num','normal','yes','Nama Projek');this.value=this.value.toUpperCase();" 
         onKeydown="check_length(this,'4000','NAMA_PROJEK_check','NAMA_PROJEK_num','normal','yes','Nama Projek');this.value=this.value.toUpperCase();"                    
           >$!NAMA_PROJEK</textarea>             
            <div  ><span id="NAMA_PROJEK_num" style="color:blue;" ></span><span> Baki Aksara</span></div>
            <div id="NAMA_PROJEK_check" class="alert_msg"  ></div>      

          
           </td>
        </tr>


<tr>
  <td  ></td>
  <td align="left" valign="top" ></td>
  <td valign="top" ></td>
  <td valign="top" >
   
   
    <input type="button" id="btnCariHakmilik" name="btnCariHakmilik" value="CARI" onclick="cariHakmilik()"/>
    <input type="button" id="btnCariHakmilikReset" name="btnCariHakmilikReset" value="RESET" onclick="resetSkrin()"/>
    
    
    </td>
  
  
</tr>
</table>
</fieldset>
</td>
  </tr>
</table>


#if($command == "carianHakmilik")

#end


<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI HAKMILIK</b></legend>    
     
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%" cellspacing="1" cellpadding="0">
       
         <tr>
          <td colspan="8" scope="row">
        
     
          </td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" valign="top" align="center"><strong>NO.</strong></td>
          <td width="15%"  valign="top"><strong>NO. HAKMILIK</strong></td>  
             
          <td width="30%" valign="top"><strong>NO. FAIL</strong></td>    
          <td width="15%" valign="top"><strong>NO. LOT / NO. PT</strong></td> 
           <td width="35%" valign="top"><strong>PROJEK</strong></td> 
           
           
        
        </tr>
        
        #if($SenaraiFail.size()>0)
        #set ($count = 0)
        #foreach ( $fail in $SenaraiFail )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2" )
        #else
        #set( $row = "row1" )
        #end
        <tr>
          <td class="$row" align="center" valign="top">$!count</td>
          <td class="$row" valign="top"><a href="javascript:paparHakmilik('$fail.ID_HAKMILIK')" ><font  color="blue">
          #if($fail.KOD_JENIS_HAKMILIK != "")
          <b>$fail.KOD_JENIS_HAKMILIK</b>
          #end
          
          #if($fail.NO_HAKMILIK != "")
          <b>$fail.NO_HAKMILIK </b>
          #else
          --
          #end
          </font></a></td>
         
          <td class="$row" valign="top">
          
          
          #if($fail.NO_FAIL != "")
          NO. FAIL JKPTG : $fail.NO_FAIL<br>
          #end
          
          #if($fail.NO_RUJUKAN_PTG != "")
          NO. FAIL PTG : $fail.NO_RUJUKAN_PTG<br>
          #end
          
          #if($fail.NO_RUJUKAN_PTD != "")
          NO. FAIL PTD : $fail.NO_RUJUKAN_PTD<br>
          #end
          
          #if($fail.NO_RUJUKAN_UPT != "")
          NO. FAIL UPT : $fail.NO_RUJUKAN_UPT<br>
          #end
          
          </td> 
          <td class="$row" valign="top">
          
          #if($fail.NO_LOT != "")
          NO. LOT : $fail.NO_LOT<br>
          #end
          
           #if($fail.NO_PT != "")
          NO. PT : $fail.NO_PT<br>
          #end
          
          </td> 
          <td class="$row" valign="top">$fail.TUJUAN</td>        
        
        </tr>
        #end
        
         #else
  <tr>  
    <td colspan="8"><font color="red">TIADA REKOD</font></td>    
  </tr>
  #end
      </table>
      </fieldset></td>
  </tr>
</table>





<script type="text/javascript" >
if (document.getElementById('NAMA_PROJEK') != null && document.getElementById('NAMA_PROJEK') != 'undefined') 
{
check_length1(document.${formName}.NAMA_PROJEK,'4000','NAMA_PROJEK_check','NAMA_PROJEK_num','normal','yes','Nama Projek');
}

function check_length1(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
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
		   //$jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
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

</script>
