#if($hitButton == 'doSyorTolakRingkas')
	#set ($inputbaru = 'baru')
#end

#if($modePopup == 'openSyorTolakRingkas')
	#foreach($beanSyorTolakRingkas in $BeanSyorTolakRingkas)
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
	    <td width="50%" valign="top">
			<fieldset>
			        <legend><b>SYOR TOLAK RINGKAS</b></legend>
				    <table  align="center" width="100%">
				        <tr>
				          <td width="37%" align="right">Sebab Tolak Ringkas :</td>
				          <td width="63%">
				          	<textarea name="sebabTolakRingkas" id="sebabTolakRingkas" rows="5" cols="50" $readonlySyor class="$inputTextSyorClass" onBlur="this.value=this.value.toUpperCase();">$!beanSyorTolakRingkas.sebabTolakRingkas</textarea>
				          </td>
				        </tr>
				        <tr>
				          <td width="37%" align="right">Tarikh Hantar Surat :</td>
		                  <td width="70%">
		                  	<input type="text" name="tarikhSuratTolakRingkas" id="tarikhSuratTolakRingkas" value="$beanSyorTolakRingkas.tarikhSuratTolakRingkas" onBlur="check_date(this);cekTarikhTerima(this)" size="9" $readonlySyor class="$inputTextSyorClass"/>
		                  	#if ($mode != 'update') <a href="javascript:displayDatePicker('tarikhSuratTolakRingkas',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end
		                 </td>
				        </tr>
				        #if($beanSyorTolakRingkas.flagMaklumatTambahan != '')
				        <tr>
				          <td width="37%" align="right">Maklumat Tambahan :</td>
				          <td width="63%">
				          	<textarea name="catatanMaklumatTambahan" id="catatanMaklumatTambahan" rows="5" cols="50" $readonlySyor class="$inputTextSyorClass" onBlur="this.value=this.value.toUpperCase();" >$!beanSyorTolakRingkas.catatanMaklumatTambahan</textarea>
				          </td>			
				        </tr>
				        #end
				        
				        <tr>
				        	<td width="37%" align="right"></td>
		                    <td width="70%">
		                    	#if ($userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')
		                    		<input id="cmdKemaskiniSyorTolakRingkas" name="cmdKemaskiniSyorTolakRingkas" type="button" value="Kemaskini" onClick="javascript:doKemaskiniSyorTolakRingkas()"/>
									<input id="cmdCetakSyorTolakRingkas" name="cmdCetakSyorTolakRingkas" type="button" value="Cetak" onClick="javascript:doCetakSyorTolakRingkas('$idFail')"/>
									<input id="cmdPengesahanPengarahNegeri" name="cmdPengesahanPengarahNegeri" type="button" value="Pengesahan Pengarah Negeri" onClick="javascript:doPengesahanPengarahNegeri('$idPermohonan')"/>
									
						     		<input id="cmdSimpanSyorTolakRingkas" name="cmdSimpanSyorTolakRingkas" type="button" value="Simpan" onClick="javascript:doSimpanSyorTolakRingkas()" style="display: none"/>
						     	#end
							</td>      
					    </tr>
				    </table>
	         </fieldset>
	         <table width="100%" border="0" cellspacing="2" cellpadding="2">
          		<tr>
          			<td width="10%"></td>
	          		<td width="20%" align="left">
		          		#if ($userRole == '(PHP)PYWPengarahNegeri')   
		          		    #if ($idStatus == '1615200')
		          		    	#set ($modePopup = 'openSyorTolakRingkas')
		          		    #end
		          			#if ($idStatus != '1615199')
		          		    	<input name="cmdSetujuTolakRingkas" type="button" value="Setuju Tolak Ringkas" onClick="javascript:doSetujuTolakRingkas('$idPermohonan')"/>
		                 		<input name="cmdTangguhMaklumatTambahan" type="button" value="Tangguh untuk Maklumat Tambahan" onClick="javascript:doTangguhMaklumatTambahan('$idPermohonan')"/>   
		                 	#end
		          		#elseif ($userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')     
		          			<!-- #if ($idStatus != '1615199')
			   	            	<input name="cmdSemakStatusTanah" type="button" value="Semak Status Tanah" onClick="javascript:doSemakStatusTanah('$noLotTanah')"/>
			                 	<input name="cmdSyorTolakRingkas" type="button" value="Syor Tolak Ringkas" onClick="javascript:doSyorTolakRingkas('$idPermohonan')"/>
			                #end  -->        
		          		#end
	          		</td>
          		</tr>
          	</table>
	    </td>
	  </tr>
	</table>
	#end
#end

<script>
if(document.${formName}.inputbaru.value == 'baru'){
	document.${formName}.sebabTolakRingkas.disabled = true;
	document.${formName}.sebabTolakRingkas.readonly = true;
	document.${formName}.tarikhSuratTolakRingkas.disabled = true;
	document.${formName}.tarikhSuratTolakRingkas.readonly = true;
	document.${formName}.catatanMaklumatTambahan.disabled = true;
// 	document.${formName}.catatanMaklumatTambahan.readonly = true;
}
	
</script>
