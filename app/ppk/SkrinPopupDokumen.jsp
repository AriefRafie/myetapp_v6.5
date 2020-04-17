#set ($msg = $success)


#set ($IdSimati = $IdSimati)
#set ($id_Permohonan = $id_Permohonan)

#foreach($listSupportingDoc in $ViewSupportingDoc)
#set($namaDoC = $listSupportingDoc.NAMA_DOKUMEN)
#end
<body>
<form id="form1" name="f1" method="post" action="">
#if ($msg == "success" )
<p align="center"><font color="red">Dokumen berjaya disimpan.</font></p>
#end

#if ($msg != "fail" )
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    
    	<fieldset id="top">
	<legend><strong>MAKLUMAT DOKUMEN </strong></legend>
 <span style="display:none">  </span>

	
    	 <table width="100%" cellpadding="0" border="0">  
        	
        	<tr>
        	  <td width="1%" valign="top">#if ($disable!="disable")<font color="red">*</font>#end</td>
              <td width="15%" valign="top">
                        
              Muatnaik Dokumen
             
              
              </td>
              <td width="1%" valign="top">:</td>
              <td width="68%" valign="top">
              #if ($disable=="disable")
              <input type="text" disabled value=$!namaDoC>
              #else
              <input id="fileupload" name="fileupload" type="file" size="40" onchange="uploadSuppDoc('$IdSimati','$id_Permohonan','$id_jenisDoc')">
              #end
           
              
              
              
              </td>
          	</tr>
            
           
            
            
        </table>
        
 
        
    </fieldset>
    
    

</td>
  </tr>
</table>
#elseif ($msg == "fail")
<p align="center"><font color="red">Terdapat dokumen sokongan yang telah dimuatnaik.</font></p>
#end
<p align="center"><input name="buttonTutup" type="button" value="Tutup" onclick="tutupWindow()" /></p>
</form>
</body>

<script>



function tutupWindow()
{
	window.close();
}

function uploadSuppDoc(IdSimati,id_Permohonan,id_jenisDoc)
{
	
	//nakUpload(IdPermohonan,session,usid, IdSimati);

var command = "&command=uploadSupDoc";
var data= "&id_Permohonan="+id_Permohonan+"&IdSimati="+IdSimati+"&id_jenisDoc="+id_jenisDoc;

var actionItem = (command+data);


document.${formName}.enctype = "multipart/form-data";
document.${formName}.encoding = "multipart/form-data";
document.${formName}.action = "?_portal_module=SkrinPopupUploadDokumen"+actionItem; 

document.${formName}.submit();
}

</script>


