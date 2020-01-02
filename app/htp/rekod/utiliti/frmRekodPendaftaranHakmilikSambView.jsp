<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
<!--
.pautanms1 {color: #black}
-->
</style>
<table width="99%" border="0">
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>

	<tr>
		<td>

		##parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")
		<fieldset>
		<legend>MAKLUMAT HAKMILIK </legend>
<!-- Maklumat Fail -->
			#parse("app/htp/rekod/utiliti/frmMaklumatTanahFail.jsp")
		
<!-- Maklumat Negeri -->
			#parse("app/htp/rekod/utiliti/frmMaklumatHakmilikNegeri.jsp")
			
<!-- Maklumat Tanah -->
			#parse("app/htp/rekod/utiliti/frmMaklumatTanahHakmilik.jsp")

		<!-- Mesej Perhatian-->
   #if ($mode == 'update')
	  <table>
	  <tr>
	    <td colspan="2">
        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	    	</td>
	  </tr>
	  </table>
   #end
      
	#if($statusBatal=='S' || $socStatus=="S" || $statusBatal=="B" )
	</br>
	<fieldset>
    <legend>SENARAI HAKMILIK SAMBUNGAN</legend>
    	<table border="0" width="100%">
      	<tr class="table_header">
        	<td width="5%">Bil.</td>
        	<td width="85%">No. Hakmilik Sambungan</td>
        	<td width="10%">Tindakan</td>
      	</tr>
      	#foreach ($senarai in $listSambungan)
	      	#set( $i = $velocityCount )
	      	#if ( ($i % 2) != 1 )
	      		#set( $row = 'row2')
	      	#else
	      		#set( $row = 'row1')
	      	#end
	  		<tr class="$row">
	    		<td>$senarai.bil</td>
	    		#if ($mode == 'view')	    				
	    		<td>
	    			<a href="javascript:hakmilikDetailSamb('$senarai.idHakmilikBaru','$senarai.statusSah');" class="pautanms">$senarai.kodJenisBaru $senarai.noHakmilikBaru</a>
		    	</td>    		
	    		#else
	    		<td>
	    			$senarai.kodJenisBaru $senarai.noHakmilikBaru
	    		</td>
	    		#end
	    		
	    		#if($senarai.bil!='')
	    			<td>
	    				#if ($mode != 'view')	    				
	    				<input type="button" class="stylobutton" name="cmdHapus" id="cmdHapus" value="Hapus" onclick = "deleteHakmilikBaru('$senarai.idHakmilikAsal','$senarai.idHakmilikBaru')">
	    				#end
	    			</td>
	    		#else
	    			<td>&nbsp;</td>
	    		#end 
	    	</tr>
      	#end
    	</table>
    </fieldset>
  #end

  	 
	<table width="100%">
		<tr>
	    <td colspan="2" ><div align="center">
	        
	        <p>
		      #if($statusBatal!='S' || $statusBatal!='P' || $statusBatal!='B')
		          #if ($mode == 'view')
				  	<input type="button" class="stylobutton100" name="btnDelete" value="Hapus" onclick="hapusTanah($idHakmilik)" />
		            <input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:cetakMaklumatHakmilik($idHakmilik);" />
		          #end
		          
		          #if ($mode == 'update')
		          	#if($statusBatal=='S' || $socStatus=='S' || $statusBatal=='B' )
	                	<input type="button" class="stylobutton100" name="btnSaveHakmilikSamb" id="btnSaveHakmilikSamb" value="Simpan" onClick="updateDetailHakmilikSamb('$idHakmilik','$socStatus')"/>
		 				<input type="button" class="stylobutton100" name="btnBatal" value="Batal" onclick="hakmilik_detail('$!idHakmilik','$!sstatus');"/>
	          		#else
	          			<input type="button" class="stylobutton100" name="btnSaveHakmilik" id="btnSaveHakmilik" value="Simpan" onclick="update_detailHakmilik($idHakmilik)" />
		 				<input type="button" class="stylobutton100" name="btnBatal" value="Batal" onclick="hakmilik_detail('$!idHakmilik','$!sstatus');"/>
		       		#end
		          #end
		          
	          #end
	          <input type="button" class="stylobutton100" name="btnBackHakmilik" id="btnBackHakmilik" value="Kembali" onclick="kembaliHakmilik()" />
	        </p>
	    </div></td>
	  </tr>
	  <tr>
	    <td colspan="2" ><div align="right">	  
	  		CL-02-69S
	  	</td>
	  </tr>
	</table>

		</fieldset>
		##parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")

		</td>
	</tr>
</table>

<input type="hidden" name="idHakmilik" value="$idHakmilik" />
<input type="hidden" name="mode" value="$mode" />
<input type="hidden" name="txtIdHakmilikCukai" value="$idHakmilikCukai" />
<input type="hidden" name="txtCukaiSemasa" value="$txtCukaiTerkini" />
<input type="hidden" name="txtKodSocJenisHakmilik" value="$txtKodSocJenisHakmilik" />
<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch" />
<input type="hidden" name="skrin" value="skrinhakmilik" />
<input type="hidden" name="txtLuasGabung" value="$txtLuasLama" />
<input type="hidden" name="socJenisTanah" value="$idJenisTanah">
