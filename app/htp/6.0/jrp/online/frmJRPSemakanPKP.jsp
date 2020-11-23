<style type="text/css">
	<!--
	.pautanms {color: #0000FF}
	-->
</style> 
<table width="100%" border="0" cellspacing="2" cellpadding="2">

	<tr>
    	<td> 
			<br/>
			  #parse('app/htp/frmPajakanKecilPaging.jsp')
			<br/>
			#parse ("app/htp/frmJRPInfo.jsp")			
			  
			  #set ($pagemode = $pageMode)	
			  #set ($cbFlag = '')	
			  #if($pagemode == "2" )
			  #set ($cbFlag = 'disabled')	
			  #end 

		<td>		
	</tr>
	
	<tr>
    	<td> 	
    		
			
			<p>
			   	<input type="hidden" name="id_kemaskini" />
			   	<input type="hidden" name="fail" value="$fail"/>
			   	<input type="hidden" name="langkah" value="0" />
			   	<input type="hidden" name="idpermohonan" value="$idpermohonan" />
			   	<input name="namaPemohon" type="hidden" id="namaPemohon" value="$namaPemohon"/>
			    
			    
			 </p>
			
	
		<td>		
	</tr>

		<tr>
		<td>
			<fieldset>
			<legend><strong>SENARAI SEMAK/ DOKUMEN YANG DISERTAKAN</strong></legend>
			<table style="width:100%">
				<tr class="row2">
					<td width="3%"><b>Bil</b></td>
					<td width="75%"><b>Keterangan</b></td>
					<td width="25%"><b>Dokumen</b></td>
			  	</tr> 
			  			#set ( $checked = "" )
	#if ($senaraiSemak.size() > 0)
		
		#foreach ( $semak in $senaraiSemak )
			#set( $i = $velocityCount )
			#if ( ($i % 2) == 0 )
				#set( $row = "row2" )
			#else
				#set( $row = "row1" )
			#end

        		#if($semak.flag == 'Y')
        			#set($checked = 'checked')
					#set($disabled = 'disabled')
        		#else
        			#set($checked = '')
        		#end			
					        ##if($semak.aturan==1)
				<tr>
						<td class="$row" width="3%"><input type="checkbox" value="$list.idSenaraiSemak" name="idsenaraisemak" $checked /></td>
		          		<td class="$row" width="82%">$i. $semak.keterangan</td>
		          		<td class="$row" width="15%">
		          			$!semak.lampirans
		          		</td>	
				</tr>
        					##end
        		       ##end
 		#end
   	#else
        <tr>
	        <td class="$row" width="3%">&nbsp;</td>
    	    <td class="$row" colspan="2" width="95%">Tiada Rekod</td>
        </tr>
   	#end
			</table>
			</fieldset>
		</td>
		</tr>
		
</table>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
			<td align="center" colspan="4">
						#if($semakMode == "update")
		    			#if(($!idjawatan.equals("20")||$!idjawatan.equals("24"))&& $!statussemasa.equals("1")) 
		    				<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Semakan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
						#elseif ($!idjawatan.equals("9") && $!statussemasa.equals("2"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Pengesahan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
						
						#elseif ($!idjawatan.equals("4")&& $!statussemasa.equals("3"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Permohonan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
                		
                		#end
                		
                	<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="step2Online($!permohonan.idpermohonan)" /> 
		    		#else
                    <!-- <input type="button" class="stylobutton100" name="cetakakuan" id="cetakakuan" value="Cetak" onclick="javascript:cetakPengesahan('$!permohonan.idpermohonan');" /> -->
               		<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="step2Online($!permohonan.idpermohonan)" />
		    		#end
		    </td>
</table>

<script>
	var checker = document.getElementById('checkme');
	var sendbtn = document.getElementById('cmdSimpan');
	// when unchecked or checked, run the function
	checker.onchange = function(){
		sendbtn.disabled = true;
	
		if(this.checked){
	   		sendbtn.disabled = false;
		} else {
		   sendbtn.disabled = true;
		}
	
	}
	
</script>


