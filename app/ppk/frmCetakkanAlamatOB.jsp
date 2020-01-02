<style type="text/css">
tr.tr_class td {
	background-color:#01DFD7;	
	font-weight:bold;
	color:white;
}
</style>
<br>
<br>
<body onLoad="ResetScrollPosition();" >
<fieldset><legend><b>
 <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>Baru</blink></font></b>
</label>
 &nbsp;
Skrin Cetakkan Alamat Waris dan Orang Berkepentingan</b></legend>
<table width="100%" align="center" border="0">
<tr>
      			<td scope="row" align="right">MyID Simati / No Fail : </td>
      			<td width="70%"><input name="txtNoFailSub" id="txtNoFailSub" type="text" value="$txtNoFailSub" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
              
                </td>
  </tr>
    		<tr>
      			<td scope="row"></td>
      			<td><input name="cmdSemakSub" id="cmdSemakSub" value="Semak" type="button" onClick="javascript:search_main_data_sub()">
        			<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onClick="javascript:kosongkan_sub()"></td>
    		</tr>
    		
</table>
        #if($view_list_fail == "yes")
      
        <br>
        <fieldset>
        <table width="97%"  cellpadding="1" cellspacing="2" border="0">
<tr >
                <td class="table_header" width="5%" style="display:none" ><b></b></td>
      		    <td class="table_header" width="10%" style="display:none" ><b>ID FAIL</b></td>
                <td class="table_header" width="20%"><b>NO FAIL</b></td>
                <td class="table_header" width="20%"><b>NAMA SIMATI</b></td>
                <td class="table_header" width="20%"><b>NAMA PEMOHON</b></td>
                <td class="table_header" width="10%"><b>TARIKH MOHON</b></td>
                <td class="table_header" width="15%"><b>STATUS SEMASA</b></td>
                             
                          
   		  </tr>
            
            #if($list_fail.size() > 0)
    		
    		#foreach($list in $list_fail)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
               
               
               #set($tr_id = "tr_id"+$i)
               
              <!-- onMouseOver="tr_id_up('$tr_id','$row')" onMouseOut="tr_id_out('$tr_id','$row')" -->
         
         #if($list.ID_FAIL  == $id_fail_carian)
         #set( $row = "tr_class" )
         #end
            <tr id="$tr_id" class="$row"    >
                <td  style="display:none"  >  
         <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="white"> Papar  </font>
         #else
         <font color="blue"> Papar  </font>
         #end  
         
              </a>   
                </td>
      			
      		    <td  style="display:none"  >  
                  $list.ID_FAIL   
                </td>
                
                <td  > 
                  <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="blue">  $list.NO_FAIL   </font>
         #else
         <font color="blue">  $list.NO_FAIL   </font>
         #end  
         
              </a>   
                
                </td>
                
                <td  >
               $list.NAMA_SIMATI
                </td>
                
                <td >
                $list.NAMA_PEMOHON 
                </td>
                
                <td >
               $list.TARIKH_MOHON
                </td>
                
                 <td >
               $list.NAMA_STATUS
                </td>
                          
    		</tr>
         
            #end
            #else
            
            <tr class="row">
      			
      		    <td class="row" colspan="10" > 
                Tiada Rekod              
                </td>
               
                          
    		</tr>
            
            #end
        </table>
        </fieldset>
        #end
       #if($view_list_waris == "yes" ) 
       #if($!headerppk.size()>0) 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr >    
    <td >  
    #parse("app/ppk/headerppk.jsp")
</td>
</tr>
</table>
     #end    
     <br>
     <fieldset>
     <legend>SENARAI WARIS DAN ORANG BERKEPENTINGAN <input id="cmdCetak" name="cmdCetak" type="button"  style="display:none" value="Cetak" onClick="javascript:cetak('$id_fail_carian')" />
</legend>
     
     <table width="80%"  cellpadding="1" cellspacing="2" border="0">
         <tr >
                    		   
                <td class="table_header" width="35%" valign="top"><b>NAMA</b></td>
                <td class="table_header" width="25%" valign="top"><b>JENIS PIHAK BERKEPENTINGAN</b></td>
                <td class="table_header" width="35%" valign="top"><b>ALAMAT</b></td>
                
                <td class="table_header" width="5%" valign="top"><b><input type="checkbox" name="all1_delete_data"    id="all1_delete_data" onClick="doCheckall1_delete_data()" title="Semak untuk pilih semua" />   </b></td>
                              
                          
	   </tr>
          
    	#if($papar_list_waris.size()>0)	
        
    		#foreach($list in $papar_list_waris)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
               
        
        
         <tr  class="$row"    >
         <td valign="top"  >          
         $list.NAMA   
          
         </td>
         <td valign="top"  > 
         #if($list.JENIS_ALAMAT == "A_WARIS")
         WARIS
         #elseif($list.JENIS_ALAMAT == "B_OB")      
         ORANG BERKEPENTINGAN
         #elseif($list.JENIS_ALAMAT == "C_HUTANG")  
         PENGHUTANG
         #else
         
         #end  
         </td>
         <td valign="top"  >  
          #if($list.ALAMAT1 != "")   
          $list.ALAMAT1<br> 
          #end
          #if($list.ALAMAT2 != "")   
          $list.ALAMAT2<br> 
          #end
          #if($list.ALAMAT3 != "")   
          $list.ALAMAT3<br> 
          #end
          #if($list.POSKOD != "")   
          $list.POSKOD<br> 
          #end
          #if($list.BANDAR != "")   
          $list.BANDAR<br> 
          #end
          #if($list.NEGERI != "")   
          $list.NEGERI<br> 
          #end
         </td>
           
            <td valign="top" >
           
            <input type="checkbox" name="ids1_delete_data" id="ids1_delete_data"  onClick="doUpdateCheckall1_delete()" value="$!list.ID_OB" >    
        
           </td>
           
          
           </tr>
       #end
       
       #else
       <tr>
       <td colspan="3">
       Tiada Rekod
       </td>
       </tr>
       #end
           </table>
     
     
     </fieldset>
     #end
      
     
     
     
     

<input type="hidden" name="id_fail_carian" id="id_fail_carian" value="$id_fail_carian" /> 

<!--ScrollX :  --><input type="hidden" id="ScrollX" name='ScrollX'  />
<!--ScrollY :  --><input type="hidden" id="ScrollY" name='ScrollY'  />  


#parse("app/ppk/headerppk_script.jsp")

   
<script >


function cetak(idfail){

			var str_idob = "(";
			
			//alert("1");
		
			if (document.${formName}.ids1_delete_data.length > 1)
			{
			//alert("2");
				for (mainlayer = 0; mainlayer < document.${formName}.ids1_delete_data.length; mainlayer++)
				{
					if(document.${formName}.ids1_delete_data[mainlayer].checked == true)
					{
					//alert("3");
						
						str_idob = str_idob + "'" +document.${formName}.ids1_delete_data[mainlayer].value+ "',";
						
					}													
				}	
			}
			else
			{
			//alert("4");
					if(document.${formName}.ids1_delete_data.checked == true )
					{
					str_idob = str_idob + "'"+document.${formName}.ids1_delete_data.value+"',";
					}	
			}
			
			str_idob = str_idob+ "'AAAAAAAA')";	
			
			//alert("idfail :"+idfail+", str_idob :"+str_idob);
		

	var url = "../servlet/ekptg.report.ppk.CetakanAlamatWaris?id_fail='"+idfail+"'&id_ob="+str_idob+"";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


	
function search_main_data_sub()
{
	SaveScrollXY();  	
	//doAjaxCall${formName}("cariMainSub");
	document.${formName}.command.value = "cariMainSub";
	document.${formName}.submit();
	document.${formName}.cmdSemakSub.value = "Sila Tunggu...";
}
function kosongkan_sub() {
SaveScrollXY();        
document.${formName}.action = "";
document.${formName}.txtNoFailSub.focus();
//doAjaxCall${formName}("kosongkan");
document.${formName}.command.value = "kosongkan";
	document.${formName}.submit();
}
function paparFail(id_fail)
{
SaveScrollXY();        
document.${formName}.id_fail_carian.value = id_fail;
//doAjaxCall${formName}("paparSub");
document.${formName}.command.value = "paparSub";
	document.${formName}.submit();

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



function doCheckall1_delete_data(){   
//alert("test"); 
    if (document.${formName}.all1_delete_data.checked == true){
	
	document.getElementById('cmdCetak').style.display = "";
	
        if (document.${formName}.ids1_delete_data.length == null){
            document.${formName}.ids1_delete_data.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1_delete_data.length; i++){
                document.${formName}.ids1_delete_data[i].checked = true;
            }


        }
    } else {
	
	if (document.${formName}.all1_delete_data.checked == true)
	{
	document.getElementById('cmdCetak').style.display = "";
	}
	else
	{
	document.getElementById('cmdCetak').style.display = "none";
	}
	
        if (document.${formName}.ids1_delete_data.length == null){
            document.${formName}.ids1_delete_data.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids1_delete_data.length; i++){
                document.${formName}.ids1_delete_data[i].checked = false;
            }
        }
    }
	
}


function doUpdateCheckall1_delete(){  
var c = 0;
var x = 0;

if(document.${formName}.ids1_delete_data.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1_delete_data.length; i++)
	  {
      if (document.${formName}.ids1_delete_data[i].checked == false)
	  {	 
	  c++
      }else
	  {	 
	  x++
      }
	  }  
}
else
{
if (document.${formName}.ids1_delete_data.checked == false)
{	 
c++;
}
else
{
x++
}
	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all1_delete_data.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1_delete_data.checked = true;
	  }
	  
	  
	  
	  var cc = 0;
var xx = 0;

if(document.${formName}.ids1_delete_data.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1_delete_data.length; i++)
	  {
      if (document.${formName}.ids1_delete_data[i].checked == false)
	  {	 
	  cc++
      }else
	  {	 
	  xx++
      }
	  }  
}
else
{
if (document.${formName}.ids1_delete_data.checked == false)
{	 
cc++;
}
else
{
xx++
}
	 	
}



	 
   if(cc>0)
	  {	  
	  document.${formName}.all1_delete_data.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1_delete_data.checked = true;
	  }
	  
	  if((x+xx)>0) 
	  {
	  document.getElementById('cmdCetak').style.display = "";
	  }
	  else
	  {
	  document.getElementById('cmdCetak').style.display = "none";
	  }
	  
}




</script>