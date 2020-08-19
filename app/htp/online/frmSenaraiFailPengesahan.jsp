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
				    <td width="29%"><div align="right">No. Fail</div></td>
				    <td width="1%">:</td>
				    <td width="70%"><input name="txtNoFail" type="text" id="txtNoFail" size="43" value="$!txtNoFail"></td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">Tajuk Fail</div></td>
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
			<fieldset><legend><strong>SENARAI FAIL</strong></legend>

			#parse("app/utils/record_paging.jsp")
				<table width="100%" border="0">
			  		<tr class="table_header">
				    	<td width="3%" align="center">Bil.</td>
					    <td width="20%">No. Rujukan <i>Online</i></td>
					    <td width="45%">Tajuk Fail</td>
					    <td width="12%">Negeri</td>
					    <td width="15%">Status</td>
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
			    				<a href="javascript:permohonanViewMaklumatOnline('$!list.permohonan.pfdFail.getIdFail()')" class="pautanms">$!list.permohonan.getNoPermohonan()</a>
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
			    			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Seterusnya01" 
			    			onclick="permohonanSimpanPengesahan($!list.permohonan.pfdFail.getIdFail())">
			    		#elseif ($!list.permohonan.pfdFail.getIdUrusan() == '5' ) 
			    			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Seterusnya02" 
			    			onclick="perletakhakanSimpanPengesahan('$!list.permohonan.pfdFail.getIdFail()','$list.permohonan.getIdPermohonan()','$list.idHtpPermohonan','$list.idSuburusanStatusFail')">			    		
			    		#elseif ($!list.permohonan.pfdFail.getIdUrusan() == '2' ) 
			    			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Seterusnya03" 
			    			onclick="pembelianSimpanPengesahan('$!list.permohonan.pfdFail.getIdFail()','$list.permohonan.getIdPermohonan()','$list.idHtpPermohonan')">			    		
			    		#elseif ($!list.permohonan.pfdFail.getIdUrusan() == '3' ) 
			    			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Seterusnya04" 
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
	
	//cmdCari
	function carian(){
		document.${formName}.txtcarian.value="ya";
		doAjaxCall${formName}("","");
	
	}
	
	function doChangeKementerianCarian(){
		document.${formName}.txtcarian.value="ya";
		doAjaxCall${formName}("","");
	
	}
	
	function doChangeNegeriCarian(){
		document.${formName}.txtcarian.value="ya";
		doAjaxCall${formName}("","");
	
	}
	
	function doChangeDaerahCarian(){
		document.${formName}.txtcarian.value="ya";
		doAjaxCall${formName}("","");
	
	}
	//PERMOHONAN
	function kemaskiniterimapermohonan(id){
		var mode = 'MakAsasTanah';
		doAjaxCall${formName}("permohonankemaskinionline","mode="+mode+"&idfail="+id+"&pagemode=0");
	}
	
	function permohonanSimpanPengesahan(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("permohonanviewmaklumat","mode="+mode+"&idfail="+id+"&pagemode=0");
	}
	
	function permohonanTolak(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("permohonanditolak","mode="+mode+"&idfail="+id+"&pagemode=0");
	}

	function permohonanTerima(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("permohonanditerima","mode="+mode+"&idfail="+id+"&pagemode=0");
	}
	
	function viewMaklumatPermohonan(id){
		var mode = 'viewMaklumatPermohonan';
		document.${formName}.command.value = 'viewMaklumatPermohonan';
		document.${formName}.action = "$EkptgUtil.getTabID('Permohonan',$portal_role)?_portal_module=ekptg.view.htp.FrmTerimaPohon1&mode="+mode+"&idfail="+id+"&pagemode=0";
		document.${formName}.submit();
	}
	
	function permohonanViewMaklumatOnline(id){	
		var mode = 'viewMaklumatPermohonan';
		document.${formName}.command.value = 'viewMaklumatPermohonan';
		document.${formName}.action = "$EkptgUtil.getTabID('Permohonan',$portal_role)?_portal_module=ekptg.view.htp.FrmTerimaPohon1&mode="+mode+"&idfail="+id+"&pagemode=0";
		document.${formName}.submit();
	}
	
	//PERLETAKHAKAN
	function perletakhakanSimpanPengesahan(idA,idB,idC,idE){
		//var mode = 'viewMaklumatPermohonan';
		//doAjaxCall${formName}("perletakhakanviewmaklumat","mode="+mode+"&idfail="+id+"&pagemode=0");
		doAjaxCall${formName}("perletakhakanviewmaklumat","actionPerletakhakan=papar&idfail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE);
	}
	
	function perletakhakanViewMaklumatOnline(idA,idB,idC,idE){
		//doAjaxCall${formName}("","actionPerletakhakan=papar&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE);
		document.${formName}.action = "$EkptgUtil.getTabID('Perletakhakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=papar&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
		document.${formName}.submit();
	}
	
	function perletakhakanTolak(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("perletakhakanditolak","mode="+mode+"&idfail="+id+"&pagemode=0");
	}

	function perletakhakanTerima(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("perletakhakanditerima","mode="+mode+"&idfail="+id+"&pagemode=0");
	}
	
	//PAJAKAN
	function pajakanSimpanPengesahan(idA,idB,idC,idE){
		//var mode = 'viewMaklumatPermohonan';
		//doAjaxCall${formName}("perletakhakanviewmaklumat","mode="+mode+"&idfail="+id+"&pagemode=0");
		doAjaxCall${formName}("pajakanviewmaklumat","actionPerletakhakan=papar&idfail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE);
	}
	
	function pajakanViewMaklumatOnline(idFail,idB,idC,idE){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		
		//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=papar&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
		document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView";
	
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.submit();
	
	}
	
	function pajakanTolak(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("pajakanditolak","mode="+mode+"&idfail="+id+"&pagemode=0");
	}

	function pajakanTerima(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("pajakanditerima","mode="+mode+"&idfail="+id+"&pagemode=0");
	}
	
	//PEMBELIAN
	function pembelianSimpanPengesahan(idA,idB,idC){
		//var mode = 'viewMaklumatPermohonan';
		//doAjaxCall${formName}("perletakhakanviewmaklumat","mode="+mode+"&idfail="+id+"&pagemode=0");
		doAjaxCall${formName}("pembelianviewmaklumat","idfail="+idA+"&txtidPermohonan="+idB+"&idPermohonan="+idB+"&idHtpPermohonan="+idC);
	}
	
	function pembelianViewMaklumatOnline(idPermohonan,idHtp){
		//doAjaxCall${formName}("detail",'idPermohonan='+id+'&idHtpPermohonan='+idhtp);
		document.${formName}.command.value = 'detail';
		document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&idPermohonan="+idPermohonan+"&idHtpPermohonan="+idHtp;
		document.${formName}.submit();
	}
	
	function pembelianTolak(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("pembelianditolak","mode="+mode+"&idfail="+id+"&pagemode=0");
	}

	function pembelianTerima(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("pembelianditerima","mode="+mode+"&idfail="+id+"&pagemode=0");
	}
	
	function cetakImej(id){
		//var url = "../servlet/ekptg.view.htp.FrmRekodDisplayImej?id="+id;
	    var url = "../servlet/ekptg.view.pfd.DisplayBlob?id="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	
	

</script>