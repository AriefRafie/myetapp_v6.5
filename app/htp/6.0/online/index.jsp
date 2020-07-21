<style type="text/css">
	<!--
	.pautanms {color: #0033FF}
	-->
</style>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="txtcarian" type="hidden" value="$!iscarian" >

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
    </tr>
	<tr>
		<td>
			<fieldset><legend><strong>CARIAN</strong></legend>

				<table width="100%" border="0">

				  <tr>
				    <td width="29%"><div align="right">Negeri</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!selectNegeriC</td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">Daerah</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!selectDaerahC</td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">
				      <div align="right">Bandar/Pekan/Mukim</div>
				    </div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!selectMukimC</td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">Kementerian</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!selectKementerianC</td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">Agensi</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!selectAgensiC</td>
				  </tr>		
				  				  				  <tr>
				    <td width="29%"><div align="right">Sub Modul</div></td>
				    <td width="1%">:</td>
				    <td width="70%">
				    <select name="soctanah" id="socTanah" class="$classDis" $classDis>                  
                <option>SILA PILIH</option>
                                      <option>PERMOHONAN </option>
                                     <option>PAJAKAN </option>
                       
                </select>
				    </td>
				  </tr>			  
			  
 				  <tr>
				    <td width="29%"><div align="right">No. Rujukan <i>Online</i></div></td>
				    <td width="1%">:</td>
				    <td width="70%"><input name="txtNoFail" type="text" id="txtNoFail" size="43" value="$!txtNoFail"></td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">Tujuan Permohonan</div></td>
				    <td width="1%">:</td>
				    <td width="70%"><input name="txtTajukFail" type="text" id="txtTajukFail" size="43" value="$!txtTajukFail"></td>
				  </tr>

				  <tr>
				    <td width="29%">&nbsp;</td>
				    <td width="1%">&nbsp;</td>
				    <td width="70%">&nbsp;</td>
				  </tr>
				  <tr>
				    <td width="29%">&nbsp;</td>
				    <td width="1%">&nbsp;</td>
				    <td width="70%">
				    	<input class="stylobutton100" type="button" name="cmdCari" id="cmdCari" value="Cari" onClick="carian()"/>
				    	<input class="stylobutton100" type="reset" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" />
				    </td>
				  </tr>
				</table>

			</fieldset>
		</td>
    </tr>
 	<tr>
		<td>
			<fieldset><legend><strong>SENARAI PERMOHONAN</strong></legend>

			#parse("app/utils/record_paging.jsp")
				<table width="100%" border="0">
			  		<tr class="table_header">
				    	<td width="3%" align="center"><b>Bil.</b></td>
					    <td width="20%"><b>No. Rujukan <i>Online</i></b></td>
					    <td width="45%"><b>Tujuan Permohonan</b></td>
					    <td width="12%"><b>Negeri</b></td>
					    <td width="15%"><b>Status</b></td>
					    <td width="5%">&nbsp;</td>
			  		</tr>	  
			#set ($count = 0)
			#if ($SenaraiFail.size() > 0) 
			 	#foreach ($list in $SenaraiFail)  
			 		#set ($count = $count+1)
			 		#set( $i = $velocityCount )
			    	#if ( ($i % 2) != 1 )
			    		#set( $row = "row2" )
			    	#else
			    		#set( $row = "row1" )
			    	#end
			    	
			  		<tr class="$row">
			    		<td align="center" class="$row">$!count.</td>
			    		<td class="$row">
			    			<!--<a href="javascript:viewFailById('$list.idFail','$list.idPermohonan','$list.idHtpPermohonan','$list.idSuburusanStatusFail')"><font color="blue">-->
			    				<!--$!list.permohonan.getNoPermohonan()
			    				</font></a>-->
			    		#if ($!list.permohonan.pfdFail.getIdUrusan() == '1' || $!list.permohonan.pfdFail.getIdUrusan()=='10') 
			    				<a href="javascript:permohonanViewMaklumatOnline('$!list.permohonan.pfdFail.getIdFail()','$list.permohonan.getIdPermohonan()')" class="pautanms">$!list.permohonan.getNoPermohonan()</a>
			    		#elseif ($!list.permohonan.pfdFail.getIdUrusan() == '5' ) 
			    				<a href="javascript:perletakhakanViewMaklumatOnline('$!list.permohonan.pfdFail.getIdFail()','$list.permohonan.getIdPermohonan()','$list.idHtpPermohonan','$list.idSuburusanStatusFail')" class="pautanms">$!list.permohonan.getNoPermohonan()</a>
			    		#elseif ($!list.permohonan.pfdFail.getIdUrusan() == '2' ) 
			    				<a href="javascript:pembelianViewMaklumatOnline('$list.permohonan.getIdPermohonan()','$list.idHtpPermohonan')" class="pautanms">$!list.permohonan.getNoPermohonan()</a>
			    		#elseif ($!list.permohonan.pfdFail.getIdUrusan() == '3' ) 
			    				<a href="javascript:pajakanViewMaklumatOnline('$!list.permohonan.pfdFail.getIdFail()','$list.permohonan.getIdPermohonan()','$list.idHtpPermohonan','$list.idSuburusanStatusFail')" class="pautanms">$!list.permohonan.getNoPermohonan()</a>
			    		#end <!---->
			    		</td>
			    		<td class="$row">$!list.permohonan.getTujuan()</td>
			    		<td class="$row">$!list.permohonan.getNamaNegeri()</td>
			    		<td class="$row">$!list.getStatusPermohonan()</td>
			    		<td class="$row">
						#if(!$list.getStatusPermohonan().contains('TOLAK')) 
			    			
						#if ($!list.permohonan.pfdFail.getIdUrusan() == '1' || $!list.permohonan.pfdFail.getIdUrusan()=='10') 
			    			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Seterusnya" 
			    			onclick="permohonanSimpanPengesahan($!list.permohonan.pfdFail.getIdFail())">
			    		#elseif ($!list.permohonan.pfdFail.getIdUrusan() == '5' ) 
			    			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Seterusnya2" 
			    			onclick="perletakhakanSimpanPengesahan('$!list.permohonan.pfdFail.getIdFail()','$list.permohonan.getIdPermohonan()','$list.idHtpPermohonan','$list.idSuburusanStatusFail')">			    		
			    		#elseif ($!list.permohonan.pfdFail.getIdUrusan() == '2' ) 
			    			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Seterusnya3" 
			    			onclick="pembelianSimpanPengesahan('$!list.permohonan.pfdFail.getIdFail()','$list.permohonan.getIdPermohonan()','$list.idHtpPermohonan')">			    		
			    		#elseif ($!list.permohonan.pfdFail.getIdUrusan() == '3' ) 
			    			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Seterusnya4" 
			    			onclick="pajakanSimpanPengesahan('$!list.permohonan.pfdFail.getIdFail()','$list.permohonan.getIdPermohonan()','$list.idHtpPermohonan','$list.idSuburusanStatusFail')">			    		
			    		#end
			    		
			    		#end
			    		</td>
			  		</tr>
			  	#end
			#else
			 		<tr>
				   		<td class="row1" align="center">&nbsp;</td>
				    	<td class="row1">Tiada Rekod</td>
				    	<td class="row1">&nbsp;</td>
				    	<td class="row1" align="center">&nbsp;</td>
				    	<td class="row1">&nbsp;</td>
			   		</tr>
			#end
			</table> 
			</fieldset>
		</td>
    </tr>
        
</table>
	<input type="hidden" name="mode" /></td>

<script>
//PAJAKAN
	function pajakanSimpanPengesahan(idA,idB,idC,idE){
		//
		var mode = 'pajakanviewmaklumat';
			//doAjaxCall${formName}("perletakhakanviewmaklumat","mode="+mode+"&idfail="+id+"&pagemode=0");
		//doAjaxCall${formName}("pajakanviewmaklumat","actionPerletakhakan=papar&idfail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE);
		document.${formName}.command.value = mode;
		document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.online.htp.FrmPermohonanPengesahan&actionPerletakhakan=papar&idfail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
		document.${formName}.submit();

	}
	
//PERLETAKHAKAN

//PEMBELIAN
	
	function pembelianViewMaklumatOnline(idPermohonan,idHtp){
		//doAjaxCall${formName}("detail",'idPermohonan='+id+'&idHtpPermohonan='+idhtp);
		document.${formName}.command.value = 'detail';
		document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&idPermohonan="+idPermohonan+"&idHtpPermohonan="+idHtp;
		//document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&idPermohonan="+idPermohonan+"&idHtpPermohonan="+idHtp;
		document.${formName}.submit();
	}
	
function pembelianSimpanPengesahan(idA,idB,idC){
		document.${formName}.command.value = 'pembelianviewmaklumat';
		document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.online.htp.FrmPermohonanPengesahan&idPermohonan="+idB+"&idHtpPermohonan="+idC;
		//document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&idPermohonan="+idB+"&idHtpPermohonan="+idC;
		document.${formName}.submit();
		
		//var mode = 'viewMaklumatPermohonan';
		//doAjaxCall${formName}("perletakhakanviewmaklumat","mode="+mode+"&idfail="+id+"&pagemode=0");
		//doAjaxCall${formName}("pembelianviewmaklumat","idfail="+idA+"&txtidPermohonan="+idB+"&idPermohonan="+idB+"&idHtpPermohonan="+idC);
	}

//PERMOHONAN
	//guna di frmPermohonanPengesahan.jsp
// 	function tolakPermohonanOnline(id_permohonan,formnew) {	
//	    var title = 'Cetakan';
//		var w =400;
//		var h = 200;
//
//		var left = (screen.width/2)-(w/2);
//		var top = (screen.height/2)-(h/2);
//		var url = "../x/${securityToken}/FrmPopupTolakPermohonan?id_permohonan="+id_permohonan+"&formnew="+formnew+"&modul=ppk&jenisTolak=internal";
//		
//		var hWnd = window.open(url, "Permohonan Online Dikembalikan", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
//		if ((document.window != null) && (!hWnd.opener))
//		hWnd.opener = document.window;
//	    if (hWnd.focus != null) hWnd.focus();
//		
//	} 
	
	function permohonanSimpanPengesahan(idFail){		
		var mode = 'viewMaklumatPermohonan';
		document.${formName}.command.value = 'permohonanviewmaklumat';
		document.${formName}.action = "$EkptgUtil.getTabID('Permohonan',$portal_role)?_portal_module=ekptg.view.online.htp.FrmPermohonanPengesahan&idfail="+idFail;
		document.${formName}.submit();
 
		/* doAjaxCall${formName}("permohonanviewmaklumat","mode="+mode+"&idfail="+id+"&pagemode=0");
	*/
	}
	
	function permohonanViewMaklumatOnline(idFail){	
		var mode = 'viewMaklumatPermohonan';
		document.${formName}.command.value = 'viewMaklumatPermohonan';
		document.${formName}.action = "$EkptgUtil.getTabID('Permohonan',$portal_role)?_portal_module=ekptg.view.htp.FrmTerimaPohon1&mode="+mode+"&idfail="+idFail+"&pagemode=0";
		document.${formName}.submit();
	}
</script>