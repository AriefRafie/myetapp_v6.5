
<input type="hidden" name="idDaerah" value="$idDaerah">
<input type="hidden" name="idProjekNegeri" value="$idProjekNegeri">
<input type="hidden" name="namaDaerah" value="$namaDaerah">
<input type="hidden" name="namaProjekNegeri" value="$namaProjekNegeri">
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_hakmilik" value="$id_hakmilik">


<fieldset>
  <legend><strong>Hakmilik Seksyen 4</strong></legend>

<br/>

    #parse("app/ppt/frmPPTHeader.jsp")
     
<br/>  	
  	
  	<div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" tabindex="0">Maklumat Hakmilik</li>
        <li class="TabbedPanelsTab" tabindex="0">Senarai Dokumen Yang Disertakan</li>
      </ul>
      
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
        	
        	<fieldset> 
         	<table width="100%" cellspacing="2" cellpadding="0" border="0">
 		 		
 		 		<tr>
 		 			#if($status!="16")
 		 			<td colspan="2"><input name="cmdTambah" type="button" value="Tambah" onclick="javascript:Tambah();" /></td>
 		 			#else
 		 			<td colspan="2">&nbsp;</td>
 		 			#end
 		 		</tr>
 		 		
 		 		<tr class="table_header">
			  		<td align="center"><b>No</b></td>
  					<td><b>No.Hakmilik</b></td>
  					<td><b>No.Lot / No.PT</b></td>
  					<td><b>Bandar/Pekan/Mukim</b></td>
  					<td><b>Luas Lot</b></td>
  				</tr> 
  				
  		#if($size_list!=0)		
  			#foreach ( $senarai in $PermohonanHM )
            
            #if ($senarai.bil == '')
          	#set ($row = 'row1')
            
			#elseif ($senarai.bil % 2 != 0)
            		#set ($row = 'row1')
                
            #else 
            	#set ($row = 'row2')
            #end
            
  				<tr>
  					<td class="$row" align="center">$senarai.bil</td>
  					<td class="$row"><a href="javascript:semakHM('$senarai.id_hakmilik')"><font color="blue">$senarai.no_hakmilik</font></a></td>
  					<td class="$row">$senarai.kod_lot $senarai.no_lot</td>
  					<td class="$row">$senarai.nama_mukim</td>
			  		<td class="$row">$senarai.luas_lot $senarai.unit_luas</td>
  				</tr>
  			#end
  		#else
  				<tr>
  					<td colspan="4">Tiada rekod</td>
  				</tr>
  		#end	
	 		</table>
     
     		</fieldset>
        </div>
        
        
        <div class="TabbedPanelsContent">
        
        <fieldset>
        	<table width="100%" cellspacing="2" cellpadding="0" border="0">
 		 	
 		 		<tr>
 		 			#if($status!="16")
 		 			<td colspan="2"><input name="cmd_Tambah" type="button" value="Tambah" onclick="javascript:add_doc();" /></td>
 		 			#else
 		 			<td colspan="2">&nbsp;</td>
 		 			#end
 		 		</tr>
 		 	
 		 		<tr class="table_header">
			  		<td align="center"><b>No</b></td>
                	<td><b>Nama Dokumen</b></td>
  					<td><b>Keterangan</b></td>
  					<td><b>Dokumen Sokongan</b></td>
  				</tr>  
  				
  			#if($listD_size!=0)
          
             #foreach($listD in $listDokumen)  
              #set($idDokumen=$listD.id_Dokumen)    
                   
                    #set( $i = $velocityCount )       	
         		#if ( ($i % 2) != 1 )
              		#set( $row = "row2" )
         		#else
               		#set( $row = "row1" )
         		#end
  					<tr>
                    	<td class="$row" align="center">$listD.bil</td>
                    	<td class="$row">$listD.tajuk</td>
                        <td class="$row">$listD.keterangan</td>
                       	<td class="$row"><a href="javascript:papar_Lampiran('$idDokumen')"><font color="blue">$listD.nama_Fail</font></a></td>	
                    </tr>
              #end  
              		 
           #else
                	<tr>
                    	<td colspan="4">Tiada rekod</td>
                    </tr>
           #end
	 		</table>
        	</fieldset>
        
        </div>
      </div>
    </div>

<br/>
   
         <table align="center">
         	<tr align="center">
         		<td>
         			#if($size_list!=0)
         			#if($status!="16")
         			<input name="cmdHantar" type="button" value="Hantar" onclick="javascript: hantar('$id_fail','$id_permohonan');" />
         			#end
         			#end
           			<input name="cmdBatal" type="button" value="Kembali" onclick="javascript: Kembali_skrin1('$id_fail','$id_permohonan');" />
         		</td>
         	</tr>
         </table>
         
</fieldset> 

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}
function Tambah(){
	document.${formName}.method = "POST";
	document.${formName}.command.value = "Tambah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function semakHM(id_hakmilik){
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function hantar(id_fail,id_permohonan){
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function add_doc(){
	document.${formName}.method = "POST";
	document.${formName}.command.value = "add_doc";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function Kembali_skrin1(id_fail,id_permohonan){
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "Kembali_skrin1";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
   