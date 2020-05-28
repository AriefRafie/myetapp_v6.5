#parse('app/htp/pembelian/fail/paging.jsp')
<style type="text/css">
	<!--
	.pautanms {color: #0000FF}
	-->
</style> 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
			

<!--<table width="80%">
<tr>
<td>-->
			<fieldset><legend>Maklumat Fail</legend>#parse ("app/htp/pembelian/fail/fileInfo.jsp")</fieldset>
		</td>

	</tr>
<!--</table>-->

	<tr>
		<td>
			<fieldset><legend>Semakan Perakuan Pembelian</legend>
				<table width="100%">
            	<!--<tr>
            		<td colspan="3">
            			<strong>Semakan Perakuan Pembelian</strong>
            		</td>
            	</tr>-->
            			#set ( $checked = "" )
					    #foreach ( $semak in $perakuanPembelian )
					        #set( $i = $velocityCount )
					        #if ( ($i % 2) == 0 )
					            #set( $row = "row2" )
					        #else
					            #set( $row = "row1" )
					        #end
					        #if($semak.aturan==1)
					        	<tr>
					            <td colspan=2 class="$row" align="left"><strong>1. PERAKUAN BIASA</strong></td>
					            </tr>
        					#end
					        #if($semak.aturan==4)
					        	<tr>
					            <td colspan=2 class="$row" align="left"><strong>2. PERAKUAN BERSYARAT</strong> (Nyatakan)</td>
					            </tr>
        					#end   					
					        #if($semak.aturan==15)
					        	<tr>
					        	<td width="3%" class="$row" align="right">
					      
					           	</td>
					            <td width="97%" class="$row"><u>Bagi SARAWAK</u></td>
					            </tr>
        					#end
					        #if($semak.aturan==17)
					        	<tr>
					            <td colspan=2 class="$row" align="left"><strong>3. TIDAK MEMPERAKUKAN PEMBELIAN</strong></td>
					            </tr>
        					#end        					
					        #if($semak.aturan==8 || $semak.aturan==9 || $semak.aturan==10 ||$semak.aturan==11 || $semak.aturan==12 || $semak.aturan==13 || $semak.aturan==14 || $semak.aturan==21 || $semak.aturan==22 || $semak.aturan==23 || $semak.aturan==24 || $semak.aturan==25 || $semak.aturan==26 )
					        	<tr>
					            <td width="3%" class="$row" align="right">
					            	#if ( $semakclass.isSemakan("$!htpPermohonan.permohonan.getIdPermohonan()", "$semak.id" ))
					                    #set ( $checked = "checked" )
					                #else
					                   #set ( $checked = "" )
					                #end
					           	</td>
					            <td width="97%" class="$row">
					                <input class="cb" type="checkbox" name="akuans" value="$semak.id" $checked $mode >
					            	$semak.keterangan
					            </td>
					            </tr>
        					#else
					            
        					<tr>
					            <td width="3%" class="$row" align="right">
					                #if ( $semakclass.isSemakan("$!htpPermohonan.permohonan.getIdPermohonan()", "$semak.id" ))
					                    #set ( $checked = "checked" )
					                #else
					                   #set ( $checked = "" )
					                #end
					                <input class="cb" type="checkbox" name="akuans" value="$semak.id" $checked $mode >
					            </td>
					            <td width="97%" class="$row">
					                $semak.keterangan <!--| $semak.id -->
					            </td>
					        </tr>
						#end
					        
					    #end
            	
            	
 

	            	
 </table>
 			</fieldset>
 			
 		</td>

	</tr>
	
	 	<tr>
    
		    	<td align="center" colspan="4">
		   
		    		#if($semakMode == "update")
		    		<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doAjaxCall${formName}('simpanPerakuan')" />
                	<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />
		    		#else
		    		<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="doAjaxCall${formName}('kemaskiniPerakuan')" />
                    <input type="button" class="stylobutton100" name="cetakakuan" id="cetakakuan" value="Cetak" onclick="javascript:senaraiDokumenSurat('tablesuratakuan');" />
               		<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />
		    		#end
		    		
		    	
		    	</td>
		    
	 </tr>

	 	<tr>
    
		    	<td align="center" colspan="4">
 <fieldset id="tablesuratakuan" style="display:none;">
<legend>SENARAI SURAT</legend>
<table width="100%" border="0">
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianSuratPerakuan')" class="pautanms">SURAT PERAKUAN PEMBELIAN</a></td>
  </tr>
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianSuratPerakuanSebahagian')" class="pautanms">SURAT PERAKUAN PEMBELIAN (SEBAHAGIAN)</a></td>
  </tr>  
   <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianSuratMaklumanBebanan')" class="pautanms">SURAT IRINGAN (ADA BEBANAN)</a></td>
  </tr>   
</table>
</fieldset> 
		    	
		    	</td>
		    
	 </tr>
</table>
 <input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>
 
<script>
function step5(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}

function step4(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.RundinganPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step3(idPermohonan){
	doAjaxCall${formName}('perakuanPembelian','&idPermohonan='+idPermohonan);
}
function step2(idPermohonan){
	doAjaxCall${formName}('maklumatTanah','&idPermohonan='+idPermohonan);
}
function step1(idPermohonan,idhtp){
	doAjaxCall${formName}("detail",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
}
</script>