 

 
 
 

<table width="80%" border="0" cellspacing="2" cellpadding="2"   >
  <tr>
    <td colspan="2">
    <fieldset >
     <table width="100%" border="0" cellspacing="2" cellpadding="2" id="table_upload">
    
     <tr>
      <td width="1%" align="center" valign="top"><font color="red">*</font></td>    
      <td width="99%" valign="top"> #foreach( $i in [1..$num_files] )  #end
      
      <input id="fileupload" name="fileupload" type=file size=50    value="$!fileupload" />
   
      
#if($view_skrin == "skrinPebualan")
<input type="button" name="cmduploadDokumenPerbualan" id="cmduploadDokumenPerbualan" value="Muatnaik" onClick="uploadDokumenPerbualan('$!id_maininbox','$!id_inboxmesej')" >
    #end
    
#if($view_skrin == "Add")
<input type="button" name="cmduploadDokumen" id="cmduploadDokumen" value="Muatnaik" onClick="uploadDokumen('$!id_maininbox','$!id_inboxmesej')" >
#end
      
      <br />
      
      <div><font color="blue"><i>Pastikan Fail yang Dimuatnaik Tidak Melebihi 2 MB!</i></font></div>
  	
   
   
   
   
   </td>
     </tr>
     </table>
   
   
      </fieldset>
        </td>
    </tr>
    </table>
      
    #if($!flag_simpan_doc == "yes")    
    
    #else
    
      
    #end
