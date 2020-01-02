<table width="100%" border="0">
<tr>
<td colspan="2">
	 <fieldset>
			<legend><strong>SENARAI SEMAK $!tajukSemakan</strong></legend>
			<table width="100%">
				#set ( $checked = "" )
				#foreach ( $semak in $semakPerjanjian)
					#set( $i = $velocityCount )
			        #if ( ($i % 2) == 0 )
			            #set( $row = "row2" )
   				    #else
			            #set( $row = "row1" )
			        #end
					<tr>
						<td width="3%" align="right" class="$row">
                                        #if ( $semakclass.isSemakan("$!htpPermohonan.permohonan.getIdPermohonan()", "$semak.id" ))
                   							 #set ( $checked = "checked" )
                						#else
                   							#set ( $checked = "" )
                						#end
                					<input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $mode>
                        </td>
						<td width="97%" class="$row" >
                                          $semak.keterangan
                        </td>
					</tr>
				#end
			</table>
	</fieldset>
</td>
</tr>

	<tr>
    
		    	<td align="center" colspan="2">
		   
		    		#if($semakMode == "update")
		    		<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doAjaxCall${formName}('senaraisemakperjanjiansimpan')" />
                      <!--<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />-->
                   	<input type="button" class="stylobutton" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="setSelected(2,'senaraisemakview','senaraisemakview',0);" />
		    		#else
		    		
		    		<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="kemaskiniSemakPerjanjian()" />
                   	<!--<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />-->
                    <input type="button" class="stylobutton" name="cetakperjanjian" id="cetakperjanjian" value="Cetak" onclick="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianDokumenSemakanPerjanjian');" />
                    <input type="button" class="stylobutton" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="setSelected(2,'senaraisemakview','senaraisemakview',0);" />
		    		#end
		    		
		    	
		    	</td>
		    
	 </tr>
</table>
<!---->
	 <input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="idHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>


<script>
	
function kemaskiniSemakS(){
	doAjaxCall${formName}("senaraisemakkemaskini");
}

</script>