<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
<!--
.pautanms1 {color: #black}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
   		<td>&nbsp;</td>
	</tr>

	<tr>		
    	<td>
 			##parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")   		    		
	    </td>
	</tr>
	<tr>		
    	<td >
    		<fieldset><legend>MAKLUMAT TANAH </legend>    
 			#parse("app/htp/rekod/utiliti/frmMaklumatTanahFailTajuk.jsp")  
 			
 			#parse("app/htp/rekod/utiliti/pindahfail/frmMaklumatTanahFailBaru.jsp")  
 			 		    		
    		</fieldset>	
	    </td>
	</tr>
	<tr >		
    	<td align="center">
		#if( $!paparmaklumat == true )
 		          <input type="button" class="stylobutton100" name="btnfailbaru" value="Simpan" onclick="simpanFailBaru()" />
		#end
 		          <input type="button" class="stylobutton100" name="btnBackHakmilik" id="btnBackHakmilik" value="Kembali" onclick="kembaliHakmilik()" />
	    </td>
	</tr>
	
	<tr>		
    	<td>
 			##parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")   		
	    </td>
	</tr>	
	
	<tr>		
    	<td>
 			<fieldset id="tableReport1" style="display:none;">
			<legend>SENARAI LAPORAN</legend>
			<table width="100%" border="0">
			  <tr>
			    <td><a href="javascript:cetakHakmilik($idHakmilik);" class="pautanms">Maklumat Hakmilik</a></td>
			  </tr>
			</table>
			</fieldset> 		
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

