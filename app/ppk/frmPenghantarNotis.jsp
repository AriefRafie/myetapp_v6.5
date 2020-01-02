
<br>
<BR>
<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #0000FF}
.style5 {
	color: #0000FF;
	font-style: italic;
	font-size: 9px;
}
-->
</style>



<table width="100%" border="0" cellspacing="2" cellpadding="2">
   <tr>
    <td><fieldset>
    <legend>
    <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>Baru</blink></font></b>
                             </label>&nbsp;
    Carian Penghantar Notis</legend>
   
    <table width="100%" border="0" >
    
    <tr>
    <td width="1%">    </td>
    <td width="20%">
    Nama Penghantar Notis    </td>
    <td width="1%">
    :    </td>
     <td width="78%">
     <input name="nama_search" type="text" id="nama_search" value="$nama_search" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="50" maxlength="200" />    </td>
    </tr>
    
    
    <tr>
    <td >    </td>
    <td >
    MyID Baru / Lama   </td>
    <td >
    :    </td>
     <td >
       <input name="myid_search" type="text" id="myid_search" value="$myid_search" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="50" maxlength="12" />    </td>
    </tr>
    
    <tr>
    <td >    </td>
    <td >
    Pejabat / Unit    </td>
    <td >
    :    </td>
    <td >
   <select name="id_pejabat_search" id="id_pejabat_search" class="autoselect" >      
        <option value="" >SILA PILIH PEJABAT</option>           
		#foreach($ja in $listPejabat) 
		<option value="$!ja.ID_PEJABATJKPTG" #if($ja.ID_PEJABATJKPTG==$id_pejabat_search) selected="selected" #end>
        $!ja.NAMA_PEJABAT         
        #if($!ja.NAMA_BANDAR != "")
        - $!ja.NAMA_BANDAR            
        #end
        
        
        </option>
		#end 
		</select>    </td>
    </tr>
    
    <tr>
    
    <td >    </td>
    
    <td >    </td>
    
    <td >    </td>
    <td align="left">
    <input name="cariNotis" id="cariNotis" value="Cari" type="button" onClick="javascript:cari()" >
    <input name="kosongNotis1" id="kosongNotis1" value="Kosongkan" type="button" onClick="javascript:carimain()" >   
    </td>
    </tr>
    </table>   
    </fieldset>
    </td>
    </tr>

  #if($addSkrin=="yes")
   <tr>
    <td><fieldset>
    <legend>Maklumat Penghantar Notis</legend>
   
    <table width="100%" border="0" >
    
    <tr>
    <td width="1%">
    <font color="red">*</font>
    </td>
    <td width="20%">
    Nama Penghantar Notis
    </td>
    <td width="1%">
    :
    </td>
     <td width="78%">
     <input name="nama" type="text" id="nama" value="$nama" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="50" maxlength="200" />
    </td>
    </tr>
    
    
    <tr>
    <td >
    </td>
    <td >
    MyID Baru
    </td>
    <td >
    :
    </td>
     <td >
       <input name="myidbaru" type="text" id="myidbaru" value="$myidbaru" style="text-transform:uppercase;"  size="50" maxlength="12" 
       onKeyUp="validateIC(event,this,this.value,'myidbaru');check_format_alert_field(this,'alert_baru_myid','baru')" onKeyDown="validateIC(event,this,this.value,'myidbaru');check_format_alert_field(this,'alert_baru_myid','baru')"  onBlur="validateIC(event,this,this.value,'myidbaru');check_format_alert_field(this,'alert_baru_myid','baru')" />
       <div id="alert_baru_myid" ></div>
  
    </td>
    </tr>
    
    
    <tr>
    <td >
    </td>
    <td >
    MyID Lama
    </td>
    <td >
    :
    </td>
    <td >
       <input name="myidlama" type="text" id="myidlama" value="$myidlama" style="text-transform:uppercase;"  size="50" maxlength="8" 
      onKeyUp="check_format_alert_field(this,'alert_lama_myid','lama')" onKeyDown="check_format_alert_field(this,'alert_lama_myid','lama')"  onBlur="check_format_alert_field(this,'alert_lama_myid','lama')" />
       <div id="alert_lama_myid" ></div>
  
    </td>
    </tr>
    
    
    <tr>
    <td >
    </td>
    <td >
    Kod Penghantar Notis
    </td>
    <td >
    :
    </td>
     <td >
       <input name="kod" type="text" id="kod" value="$kod" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="50" maxlength="3" />
  
    </td>
    </tr>
    
    <tr>
    <td >
    </td>
    <td >
    Pejabat / Unit
    </td>
    <td >
    :
    </td>
    <td >
   <select name="idpejabat" id="idpejabat" class="autoselect" >      
        <option value="" >SILA PILIH PEJABAT</option>           
		#foreach($ja in $listPejabat) 
		<option value="$!ja.ID_PEJABATJKPTG" #if($ja.ID_PEJABATJKPTG==$idpejabat) selected="selected" #end>
        
         $!ja.NAMA_PEJABAT         
        #if($!ja.NAMA_BANDAR != "")
        - $!ja.NAMA_BANDAR         
        #end
        
        
        </option>
		#end 
		</select>
    </td>
    </tr>
    
    <tr>
    
    <td >
    </td>
    
    <td >
    </td>
    
    <td >
    </td>
    <td align="left">
    <input name="SimpanNotis" id="SimpanNotis" value="Simpan" type="button" onClick="javascript:simpan()">
    #if($idpenghantarnotis == "")
    <input name="kosongNotis1" id="kosongNotis1" value="Kosongkan" type="button" onClick="javascript:kosong()">
    #end
    
    </td>
    </tr>
    
    
    </table>   
    </fieldset>
    </td>
    </tr>
    
    <script>	
	check_format_alert_field(document.${formName}.myidbaru,'alert_baru_myid','baru');
	check_format_alert_field(document.${formName}.myidlama,'alert_lama_myid','lama');
	</script>
  
  #end
  <tr>
    <td><fieldset>
		<legend><b>Senarai Penghantar Notis</b> &nbsp; <input name="TambahNotis" id="TambahNotis" value="Tambah" type="button" onClick="javascript:tambah()">
        <input id="cmdHapus" name="cmdHapus" type="button"  style="display:none" value="Hapus" onClick="javascript:hapus()" />
        </legend>
		
		#set ($pagingTitle = "Jumlah Carian")
        
		#parse("app/utils/record_paging.jsp")
        <table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
              <td width="30%"><strong>Nama</strong></td>
              <td width="20%"><strong>MyID</strong></td>
              <td width="40%" ><strong>Pejabat</strong></td>  
              <td  width="5%" valign="top" align="center">           
   <input type="checkbox" name="all1_delete_data"    id="all1_delete_data" onClick="doCheckall1_delete_data()" title="Semak untuk pilih semua" />                 
              </td>            
            </tr>
          #set ($list = "")
          #set ($counter = 0)
          #foreach ($list in $SenaraiFail)
          
          #set($counter=$counter+1)
          #if($counter%2!=0)
          #set ($row = "row1")
          #else
          #set ($row = "row2")
          #end
          
           
          <tr>
            <td class="$row" valign="top">
			$list.BIL
			</td>           
            <td class="$row" valign="top">
             <a href="javascript:papar('$list.ID_PENGHANTARNOTIS')"> 
             <font color="blue">            
            $list.NAMA
            </font>
            </a></td>
            <td class="$row" valign="top">
            #if($list.NO_KP_BARU != "")
            $list.NO_KP_BARU 
            	#if($list.NO_KP_LAMA != "")
                <br> $list.NO_KP_LAMA 
                #end
            
            #else
            	#if($list.NO_KP_LAMA != "")
                $list.NO_KP_LAMA 
                #end
            #end
            </td>
            <td class="$row" valign="top">$list.NAMA_PEJABAT 
            #if($list.ALAMAT1 != "")
            <br> $list.ALAMAT1
            #end
            #if($list.ALAMAT2 != "")
            <br> $list.ALAMAT2
            #end
            #if($list.ALAMAT3 != "")
            <br> $list.ALAMAT3
            #end
            #if($list.POSKOD != "")
            <br> $list.POSKOD
            #end
            #if($list.NAMA_BANDAR != "")
            <br> $list.NAMA_BANDAR
            #end
            #if($list.NAMA_NEGERI != "")
            <br> $list.NAMA_NEGERI
            #end
            </td>    
            <TD class="$row" valign="top" align="center">
           <input type="checkbox" name="ids1_delete_data" id="ids1_delete_data"  onClick="doUpdateCheckall1_delete()" value="$list.ID_PENGHANTARNOTIS" >
           </TD>        
            </tr>
          #end
          
           #if($counter == 0)
            <tr>
           
            <td  valign="top" colspan="10">
           Tiada rekod
           </td>
           </tr>
           #end 
          
          </tbody>
        </table>
		</fieldset>
	</td>
  </tr>
</table>


<!--ScrollX :  --><input type="hidden" id="ScrollX" name='ScrollX'  />
<!--ScrollY :  --><input type="hidden" id="ScrollY" name='ScrollY'  />
<input name="idpenghantarnotis" type="hidden" id="idpenghantarnotis" value="$idpenghantarnotis" />

<script>
ResetScrollPosition();


	function carimain()
	{	
	document.${formName}.nama_search.value = "";
	document.${formName}.myid_search.value = "";
	document.${formName}.id_pejabat_search.value = "";
	SaveScrollXY();	
	doAjaxCall${formName}("kosongCari");
	document.${formName}.kosongNotis1.value = "Sila Tunggu....";			
	}
	
	function hapus()
	{
	SaveScrollXY();	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	doAjaxCall${formName}("hapus");
	document.${formName}.cmdHapus.value = "Sila Tunggu....";			
	}
	
	function cari()
	{
	SaveScrollXY();	
	doAjaxCall${formName}("cari");
	document.${formName}.cariNotis.value = "Sila Tunggu....";			
	}
	function papar(id)
	{
	document.${formName}.idpenghantarnotis.value = id;
	//SaveScrollXY();	
	document.${formName}.ScrollX.value = '0';
    document.${formName}.ScrollY.value = '100';
	doAjaxCall${formName}("papar");			
	}
	function kosong()
	{
	SaveScrollXY();	
	doAjaxCall${formName}("tambah");
	document.${formName}.kosongNotis.value = "Sila Tunggu....";			
	}
	function tambah()
	{
	//SaveScrollXY();	
	document.${formName}.ScrollX.value = '0';
    document.${formName}.ScrollY.value = '100';
	doAjaxCall${formName}("tambah");
	document.${formName}.TambahNotis.value = "Sila Tunggu....";			
	}
	
	function simpan()
	{
	
	var total_alert = 0;
	if(document.${formName}.alert_temp.length == null)
	{
		if(document.${formName}.alert_temp.value == 'valid')
		{
		total_alert = total_alert+1;
		}
	} 
	else 
	{
		for (i = 0; i < document.${formName}.alert_temp.length; i++)
		{
			if(document.${formName}.alert_temp[i].value == 'valid')
			{
			total_alert = total_alert+1;
			}
		}
	}
	
	if(document.${formName}.nama.value == "")
	{
	alert("Sila Pastikan Nama Penghantar Notis diisi");
	}	
	else if(total_alert>0)
	{
	alert("Sila Pastikan Myid Lengkap Diisi");
	}
	else
	{	
	SaveScrollXY();	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	doAjaxCall${formName}("simpan");
	document.${formName}.SimpanNotis.value = "Sila Tunggu....";	
	}		
}
	
	function SaveScrollXY() 
	{
    document.${formName}.ScrollX.value = document.body.scrollLeft;
    document.${formName}.ScrollY.value = document.body.scrollTop;
    }
	
	function doCheckall1_delete_data(){   

    if (document.${formName}.all1_delete_data.checked == true){
	
	document.getElementById('cmdHapus').style.display = "";
	
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
	document.getElementById('cmdHapus').style.display = "";
	}
	else
	{
	document.getElementById('cmdHapus').style.display = "none";
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
	  document.getElementById('cmdHapus').style.display = "";
	  }
	  else
	  {
	  document.getElementById('cmdHapus').style.display = "none";
	  }
	  
}


function check_format_alert_field(fil,div_id,jenis)
{
	
if(jenis=="baru")
{
	if(fil.value!="")
	{
		if(fil.value.length != 12)
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>Myid Tidak Lengkap</font>");
		}
		else
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
		}	
	}
	else
	{
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
	}
}

if(jenis=="lama")
{
	
	if(fil.value!="")
	{
		if(fil.value.length != 8 && fil.value.length != 7)
		{
			if(fil.value!="TDK")
			{
			return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>Myid Tidak Lengkap</font>");	
			}else
			{
			return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
			}
		}
		else
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
		}	
	}
	else
	{
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
	}
}

	
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