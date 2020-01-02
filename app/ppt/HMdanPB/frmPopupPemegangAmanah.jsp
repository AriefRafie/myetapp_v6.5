<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
#parse("css/eTapp_PPT.css")
</style>


<fieldset id="top">
<legend>Pemegang Amanah  </legend>

#if($showAlert=="yes")
	<div align="left"><font color="blue">&nbsp;Data telah berjaya disimpan</font></div>
	#end
<center>
		
		<table width="100%" border="0">
			<tr >
            <td class="table_header" width="5%" valign="top">Bil.</td>
				<td width="80%" class="table_header" valign="top">Nama</td>
                <td width="15%" class="table_header" valign="top">Pilihan</td>
			</tr>
              #if($selectPA.size()>0)
 			       #set( $i = 0 )   
           #foreach($list in $selectPA)
         
                 #set( $i = $i + 1 ) 
               
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
           
            <tr>
            <td class="$row"  valign="top">$i</td>
				<td  class="$row" valign="top">$list.nama_pb</td>
                <td  class="$row" valign="top">
              
              #set($checkPA = "")
                
                 #foreach($listpa in $listPA)
                 
                 #if($listpa.ID_PA1 == $list.id_hakmilikpb || $listpa.ID_PA2 == $list.id_hakmilikpb || $listpa.ID_PA3 == $list.id_hakmilikpb || $listpa.ID_PA4 == $list.id_hakmilikpb )
                 
                   #set($checkPA = "checked")
                 #end
                 
                 #end
                <input type="checkbox" name="checkPB" id="checkPB" $checkPA value="$list.id_hakmilikpb" onClick="checkBox()" title="Semak untuk pilih Pemegang Amanah" />
                </td>
			</tr>
            #end
            #else
            <tr>
            <td   valign="top" colspan="6">Tiada Rekod</td>
				
			</tr>
            #end
		</table>

	
	
	
</center>
</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updatePA('$!id_hakmilik','$!id_hakmilikpb')">
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembaliScreenUtama('$!id_hakmilik')">
			</td>
		</tr>
	</table>

<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_hakmilikpb" value="$!id_hakmilikpb">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
function kembaliScreenUtama(id_hakmilik) {
	window.opener.viewHM(id_hakmilik,"popup");
	window.close();
}
function updatePA(id_hakmilik,id_hakmilikpb) {
/*	if(document.${formName}.socIdPA.value == ""){
		alert("Sila pilih Pemegang Amanah.");
  		document.${formName}.socIdPA.focus(); 
		return;
	}else{
		
		
		
*/		
var c = 0;

if(document.${formName}.checkPB.length > 1)
{     
	  for (i = 0; i < document.${formName}.checkPB.length; i++)
	  {
		  if (document.${formName}.checkPB[i].checked == true)
		  {	 
		  c++
		  }
	  }  
}
else
{
	if (document.${formName}.checkPB.checked == true)
	{	 
	c++;
	}
	 	
}	 
   	  if(c>4)
	  {	  
	  alert("Pastikan Pemegang Amanah tidak melebihi 4 orang");
	  return false;
	  }
	  
	  else
	  {
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "updatePA";
		document.${formName}.id_hakmilik.value = id_hakmilik;
		document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPopupPemegangAmanah";
		document.${formName}.submit();
	  }
//	}
}

function checkBox(){  

var c = 0;

if(document.${formName}.checkPB.length > 1)
{     
	  for (i = 0; i < document.${formName}.checkPB.length; i++)
	  {
		  if (document.${formName}.checkPB[i].checked == true)
		  {	 
		  c++
		  }
	  }  
}
else
{
	if (document.${formName}.checkPB.checked == true)
	{	 
	c++;
	}
	 	
}	 
/*
	  if(c>0)
	  {
		  if(c<5)
		  {
		  $jquery("#div_pa").html("XXXXXXX<span  style='color:blue'>Jumlah Pemegang Amanah : "+c+"</span>");
		  }
		  else
		  {
		  $jquery("#div_pa").html("<span  style='color:red'>Jumlah Pemegang Amanah : "+c+"</span>");
		  }
	  }
*/	  
	  
   	  if(c>4)
	  {	  
	  alert("Pastikan Pemegang Amanah tidak melebihi 4 orang");
	  return false;
	  }
	  
	 
	  
}
</script>