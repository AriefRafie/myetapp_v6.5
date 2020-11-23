##parse('app/htp/pembelian/fail/paging.jsp')
<style type="text/css">
	<!--
	.pautanms {color: #0000FF}
	-->
</style> 

<table width="100%" border="0" cellspacing="2" cellpadding="2">
<tr>
	<td>&nbsp;</td>
</tr>
<input name="namaPemohon" type="hidden" id="namaPemohon" value="$namaPemohon"/>
<tr>
	<td>
	
	<fieldset>
	  <legend>MAKLUMAT PERMOHONAN</legend>
	  #parse ("app/htp/pembelian/fail/online/fileInfoOnline.jsp")</fieldset>
	
	</td>


</tr>
	<tr>
		<td>
			<fieldset>
				<legend>SENARAI SEMAK</legend>
				<table width="100%">
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="1%"><font color="red">$!M</font></td>
						<td width="98%">
							<!-- <input type="checkbox" name="cbsemaks1" $disability1 value="1" id="cbsemaks1" $checked1> -->
							1. Salinan Hakmilik yang lengkap.
						</td>
					</tr>
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="1%"><font color="red">$!M</font></td>
						<td width="98%">
							<!-- <input type="checkbox" name="cbsemaks1" $disability1 value="1" id="cbsemaks1" $checked1> -->
							2. Sijil carian rasmi yang lengkap.
						</td>
					</tr>
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="1%"><font color="red">$!M</font></td>
						<td width="98%">
							<!-- <input type="checkbox" name="cbsemaks1" $disability1 value="1" id="cbsemaks1" $checked1> -->
							3. Penerangan tentang tujuan pembelian.
						</td>
					</tr>



				</table>
			</fieldset>

		</td>

	</tr>
	<tr>
		    	<td align="center" colspan="4">

		    		#if($semakMode == "update")
		    			#if(($!idjawatan.equals("20")||$!idjawatan.equals("24"))&& $!statussemasa.equals("-4")) 
		    				<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Semakan" onclick="doAjaxCall${formName}('simpanpengesahan')" />
						#elseif ($!idjawatan.equals("9") && $!statussemasa.equals("-3"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Pengesahan" onclick="doAjaxCall${formName}('simpanpengesahan')" />
						
						#elseif ($!idjawatan.equals("4")&& $!statussemasa.equals("-2"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
   							<p><b><font color="blue" size="2"><span class="blink">
   							&nbsp&nbsp&nbsp&nbsp Ambil Perhatian: Sila pastikan maklumat diisi pada permohonan adalah TEPAT dan MUKTAMAD.
   							<br/>&nbsp&nbsp&nbsp&nbsp Permohonan yang telah dihantar TIDAK DIBENARKAN untuk dipinda/dikemaskini.
   							</p></b></span>
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Permohonan" onclick="doAjaxCall${formName}('simpanpengesahan')" />
		    				<input type="button" name="cmdPindaan" id="cmdPindaan" value="Kembalikan kepada penyedia" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
                		
                		#end
                		
                	<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="step2Online($!htpPermohonan.permohonan.getIdPermohonan())" />
		    		#else
                    <input type="button" class="stylobutton100" name="cetakakuan" id="cetakakuan" value="Cetak" onclick="javascript:cetakPengesahan('$!htpPermohonan.permohonan.getIdPermohonan()');" />
               		<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="step2Online($!htpPermohonan.permohonan.getIdPermohonan())" />
		    		#end
		    		 
 	
		    	</td>
		    
	 </tr>
	<tr>

		<td align="center" colspan="4">
			<fieldset id="tablesuratakuan" style="display: none;">
				<legend>SENARAI SURAT</legend>
				<table width="100%" border="0">
					<tr>
						<td><a
							href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianSuratPerakuan')"
							class="pautanms">SURAT PERAKUAN PEMBELIAN</a></td>
					</tr>
					<tr>
						<td><a
							href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianSuratPerakuanSebahagian')"
							class="pautanms">SURAT PERAKUAN PEMBELIAN (SEBAHAGIAN)</a></td>
					</tr>
					<tr>
						<td><a
							href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=25&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','urusan','&template=HTPPembelianSuratMaklumanBebanan')"
							class="pautanms">SURAT IRINGAN (ADA BEBANAN)</a></td>
					</tr>
				</table>
			</fieldset>

		</td>

	</tr>
</table>
 <input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>

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