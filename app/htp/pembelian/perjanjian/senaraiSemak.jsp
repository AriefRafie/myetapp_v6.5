<table width="100%" border="0">
<tr>
<td colspan="2">
	 <fieldset>
			<legend><strong>SENARAI SEMAK PINDAH MILIK</strong></legend>
			<table width="100%">
				#set ( $checked = "" )
				#foreach ( $semak in $semakDraf)
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
		    		 <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doAjaxCall${formName}('simpanSemak')" />
                      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />
		    		#else
		    		
		    			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="kemaskiniSemak()" />
                         <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />
		    		#end
		    		
		    	
		    	</td>
		    
	 </tr>
</table>