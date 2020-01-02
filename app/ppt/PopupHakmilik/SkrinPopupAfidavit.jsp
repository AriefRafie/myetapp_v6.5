<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<script>var $jquery = jQuery.noConflict();</script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPT.css">
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
<fieldset>
<legend>MAKLUMAT AFIDAVIT
</legend>

				
  				
  				
  				
  				
  				
  			
  				
  				<table width="100%" border="0">
  				
  					<tr>
  						<td width="1%">&nbsp;</td>
  						<td width="25%">Kepada</td>
  						<td width="1%">:</td>
  						<td width="73%">$!selectMahkamah</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Alamat</td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtAlamat1" size="50" maxlength="50"   ></td>
  					</tr>
  					
  					<tr>
  						<td colspan="3">&nbsp;</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtAlamat2" size="50" maxlength="50"   ></td>
  					</tr>
  					
  					<tr>
  						<td colspan="3">&nbsp;</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtAlamat3" size="50" maxlength="50"   ></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Poskod</td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtPoskod" size="5" maxlength="5"   ></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Bandar</td>
  						<td>:</td>
  						<td>$!selectBandar</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Negeri</td>
  						<td>:</td>
  						<td>$!selectNegeri</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td valign="top">Tujuan</td>
  						<td valign="top">:</td>
  						<td><textarea  name="txtTujuan" id="txtTujuan" rows="4" cols="40%"  onKeyUp="textCounter(this.form.txtTujuan,this.form.remLenAF,1500);" onKeyDown="textCounter(this.form.txtTujuan,this.form.remLenAF,1500);" >$!txtTujuan</textarea></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td valign="top">Perkara Rujukan</td>
  						<td valign="top">:</td>
  						<td><textarea  name="txtPerkara" id="txtPerkara" rows="4" cols="40%"  onKeyUp="textCounter(this.form.txtPerkara,this.form.remLenP,1500);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLenP,1500);" >$!txtPerkara</textarea></td>
  					</tr>
  					
  				</table>	
  			
                
                
  				
  			</fieldset>	
  			
  				<table width="100%" border="0">
					<tr align="center">
						<td>
			
						
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanAfidevit('$!id_hakmilikpb','$!id_afidavit')">
                        #if($!id_afidavit != "")
						<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport3')" />
                        #end
						
							
						</td>
					</tr>
				</table> 
                
                
                <fieldset id="tableReport3" style="display:none;">
<legend><strong>SENARAI LAPORAN AFIDAVIT</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
    	 <td><a href="#" onClick="javascript:cetakAfidavitPerintah('$!id_permohonan','$!id_hakmilikpb')"><font color="blue">Perintah</font></a></td>
      </tr>   
      <tr>
    	 <td><a href="#" onClick="javascript:cetakAfidavitExParte('$!id_permohonan','$!id_hakmilikpb')"><font color="blue">Saman Pemula Ex-Parte</font></a></td>
      </tr>  
      <tr>
    	 <td><a href="#" onClick="javascript:cetakAfidavit('$!id_permohonan','$!id_hakmilikpb')"><font color="blue">Afidavit</font></a></td>
      </tr>  
      <tr>
    	 <td><a href="#" onClick="javascript:cetakAfidavitSijilPerakuan('$!id_permohonan','$!id_hakmilikpb')"><font color="blue">Sijil Perakuan</font></a></td>
      </tr>  
    </table>
</fieldset>
                
                
                <div style="display:none">
                id_permohonan : <input type="text" name="id_permohonan" value="$!id_permohonan"></br>
                id_hakmilikpb : <input type="text" name="id_hakmilikpb" value="$!id_hakmilikpb"></br>
                id_award : <input type="text" name="id_award" value="$!id_award"></br>
                id_afidavit : <input type="text" name="id_afidavit" value="$!id_afidavit"></br>
                </div>
                <script>
				
				function doChangeMahkamah(id_permohonan) {
				document.${formName}.command.value = "doChangeMahkamah";
				document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupAfidavit?&id_permohonan="+id_permohonan;
				document.${formName}.submit();
				}
				
				function textCounter(field, countfield, maxlimit) 
				{
				if (field.value.length > maxlimit) // if too long...trim it!
					field.value = field.value.substring(0, maxlimit);
				else 
					countfield.value = maxlimit - field.value.length;
				}	
				
				
				function simpanAfidevit(id_hakmilikpb,id_afidavit) 
				{
					if(document.${formName}.socMahkamah.value == "")
					{
						alert("Sila pilih \"Mahkamah\" terlebih dahulu.");
						document.${formName}.socMahkamah.focus();
						return;
					}	
					else
					{	
						if ( !window.confirm("Adakah Anda Pasti?") ) return;
						document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
						document.${formName}.id_afidavit.value = id_afidavit;
						document.${formName}.command.value = "simpanAfidavit";			
						document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupAfidavit";
						document.${formName}.submit();
					}
				}		
				
				function setTable(id){
				if(document.getElementById(id).style.display=="none"){
					document.getElementById(id).style.display="block";
				}
				else if(document.getElementById(id).style.display=="block"){
					document.getElementById(id).style.display="none";
				}
				}	
				
				
				
				function cetakAfidavitPerintah(idpermohonan,id_hakmilikpb) {

	var url = "../${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilikpb="+id_hakmilikpb+"&report=Afidavit_Perintah&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakAfidavitExParte(idpermohonan,id_hakmilikpb) {

	var url = "../${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilikpb="+id_hakmilikpb+"&report=Afidavit_ExParte&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakAfidavit(idpermohonan,id_hakmilikpb) {

	var url = "../${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilikpb="+id_hakmilikpb+"&report=Afidavit&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakAfidavitSijilPerakuan(idpermohonan,id_hakmilikpb) {

	var url = "../${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilikpb="+id_hakmilikpb+"&report=Afidavit_SijilPerakuan&flagReport=S";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
				</script>			
  			