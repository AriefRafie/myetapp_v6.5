
<table border="0" cellpadding="0" cellspacing="0" width="100%"> 
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Kumpulan Modul dan Peranan</legend>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" >

<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewBahagianHQ --></td>
		</tr>
       
			           <tr>
				<td height="29" valign="top" >	
				<font color="red" >*</font>				</td>			
				<td valign="top" >
				Modul
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="css_id"  
					name="css_id" onChange="doDivAjaxCall$formname('td_modul','showSenaraiRoleByGroup','CSS_TITLE='+this.value);doDivAjaxCall$formname('tableDokumen2','showSenaraiDokumen','CSS_TITLE='+this.value);" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listGroup )		
                        #if ($CSS_ID == $ruj.CSS_TITLE)
                        #set ($selected_ruj = "selected")
                        #end
							<option $selected_ruj value="$ruj.CSS_TITLE" >
							$ruj.CSS_NAME
							</option>
						#end
					</select>
				</td>
			</tr>
            <tr>
            <td>
           <!-- <input type="text" id="ID_SEKSYEN" 
				name="ID_SEKSYEN" 
				value="$viewBahagianHQ.ID_SEKSYEN" >-->
        
            </td></tr>

			<tr>
				<td height="29" valign="top" ><!--<font color="red" >*</font>-->				</td>			
			  <td valign="top" >
				Peranan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="td_modul"  >
				<!--<select id="ID_SEKSYEN"  
					name="ID_SEKSYEN" onChange="doDivAjaxCall$formname('td_modul','showSelection','id_bahagian='+this.value);" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJSEKSYEN )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_SEKSYEN==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>-->
                   	
              </td>
        </tr>
                    
                    <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				
				</td>
				<td valign="top" ></td>
				<td valign="top" ><br />
                    </td>
                    </tr>

</table>

</fieldset>

</td>
</tr>

</table>

<br>

<table width="100%" border="0" cellspacing="2" cellpadding="2" id="table_UM">
	<tr>	
    	<td></td>
  </tr>
	<tr>	
    	<td>
    		
		<fieldset>	
        <legend><strong>Manual Pengguna </strong> <!--<input size="50" id="tambahDocUser" name="tambahDocUser"  type="file" onChange="uploadDoc2(this,'manual','table_UM');">--></legend>
  <br />     
 <table border="0" cellspacing="0" cellpadding="0" width="100%" id="tableDokumen" >
 <tr>
  <td valign="top" colspan="5"> </td>
  </tr>
  <tr  >
<td width="20%"   align="left" valign="top">Nama Dokumen</td>
<td width="2%"   align="center" valign="top">:</td>
<td width="78%"   align="left" valign="top"><input size="50" type="text" id="NAMA_DOKUMEN" name="NAMA_DOKUMEN" value="$NAMA_DOKUMEN">	</td>
</tr>
 <tr  >
<td width="20%"   align="left" valign="top">Keterangan Dokumen</td>
<td width="2%"   align="center" valign="top">:</td>
<td width="78%"   align="left" valign="top"><textarea rows="4" cols="50" id="KETERANGAN" name="KETERANGAN" value="$KETERANGAN"></textarea></td>
</tr>
 <tr  >
<td width="20%"   align="left" valign="top">Muat Naik</td>
<td width="2%"   align="center" valign="top">: </td>
<td width="78%"   align="left" valign="top"> <input size="50" id="tambahDocUser" name="tambahDocUser"  type="file" value="$tambahDocUser"></td>
</tr>


 <tr  >
<td width="20%"   align="left" valign="top"></td>
<td width="2%"   align="center" valign="top"></td>
<td width="78%"   align="left" valign="top"> <input size="50" id="buttonDocUser" name="buttonDocUser"  type="button" onClick="if(validateUploadDoc() == true){uploadDoc2(this,'manual','tableDokumen2');}" value="Simpan"></td>
</tr>
</table>
<br />
<div id="divTable">
 <table width="100%" height="111" border="0" cellpadding="1" cellspacing="1" id="tableDokumen2" >
 <tr>
  <td valign="top"  width="1%" colspan="5">
 </td>
  </tr>
  <tr class="table_header" >
<td width="16%"   align="center" valign="top">Bil.</td>
<td width="46%"   align="center" valign="top">Nama Dokumen </td>
<td width="46%"   align="center" valign="top">Keterangan Dokumen </td>
<td width="20%"   align="center" valign="top">Tindakan</td>

		   
	</tr>
	#if($listDokumen.size()>0)
	<!--gred = userHQ-->
#foreach($list in $listDokumen)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td height="55"  align="center" valign="top" class="$list.rowCss" >$list.BIL</td>
<td  align="left" valign="top" class="$list.rowCss" ><a href="javascript:paparDoc('$list.ID_DOKUMENADMIN');"><font color="blue"><u>$list.NAMA_DOKUMEN</u></font></a></td>
<td  align="left" valign="top" class="$list.rowCss" ><textarea rows="3" cols="50" id="KETERANGAN$list.ID_DOKUMENADMIN" name="KETERANGAN$list.ID_DOKUMENADMIN" >$list.KETERANGAN</textarea><br /><input size="50" id="buttonDocUser" name="buttonDocUser"  type="button" onClick="doDivAjaxCall$formname('table_UM','simpanDetails','ID_DOKUMENADMIN=$list.ID_DOKUMENADMIN&CSS_ID=$list.ID_REF');" value="Simpan"></td>
<td  align="center" valign="top" class="$list.rowCss" ><a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('tableDokumen2','showDokumen','ID_DOKUMENADMIN=$list.ID_DOKUMENADMIN&FLAG_DELETE=Y&CSS_ID=$list.ID_REF');}"><img src="../img/delete.gif" border="0"></a>
</td>
</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>
</div>
		</fieldset>
		
		</td>
	</tr>
</table>

<iframe id="uploadFrame" name="uploadFrame" style="display:none"></iframe>


<script>
	//Panduan Memohon Secara Online - Borang A
	//Panduan Memohon Secara Online - Borang P
	
	function openManual(pautan){
		//alert(pautan)
		var param = "";
	    var url = "../borang/"+pautan+".pdf";
	    var hWnd = window.open(url,"Manual Pengguna","width=800,height=500, resizable=yes,scrollbars=yes");
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
    }
    
	function openManualX(pautan){
    	var url = "../borang/"+pautan+".pdf";
	    
	    var hWnd = window.open(url,'printuser','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
    }
	
	function menuUtama(){
		document.${formName}.action = "$EkptgUtil.getTabID('Menu',$myrole)?_portal_module=ekptg.view.online.FrmOnlineMenuUtama";
		document.${formName}.submit();
	}
	
	function paparDoc(idDoc) {
    var url = "../servlet/ekptg.view.DisplayBlobManual?CSS_TITLE="+idDoc;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	}
	
	
	function uploadDoc2(elem,flag_admin,div)
	{
	
	var css_id = document.getElementById('css_id').value;
	
	var nama_dokumen = document.getElementById('NAMA_DOKUMEN').value;
	
	var keterangan_dokumen = document.getElementById('KETERANGAN').value;
	
	document.${formName}.action = "?_portal_module=ekptg.view.admin.FrmManualPenggunaUpload&command=simpanDokumen&CSS_ID="+css_id+"&NAMA_DOKUMEN="+nama_dokumen+"&KETERANGAN="+keterangan_dokumen+"&FLAG_ADMIN="+flag_admin+"&returnDivUpload="+div;
	document.${formName}.method="post";
	document.${formName}.target="uploadFrame";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();	
	}

	if('$after_saveDOC'=="Y")
	{
		window.parent.viewDoc('$returnDivUpload','$commandDoc','$CSS_ID');
	}
	
	function viewDoc(div,command,idBahagian)
	{
		doDivAjaxCall$formname(div,command,'CSS_ID ='+idBahagian);			 	  
	}


function validateUploadDoc()
{
	var bool_check = true;
	var css_id = document.getElementById("css_id").value;

	if(css_id=="")
	{
		alert("Sila pilih modul terlebih dahulu! ");
		document.getElementById("css_id").focus();
		bool_check = false;
	}	
	
	return bool_check;
}


</script>
