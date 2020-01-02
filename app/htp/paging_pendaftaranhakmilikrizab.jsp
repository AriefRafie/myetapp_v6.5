<style type="text/css">
<!--
.style40 {color: #0000FF}
-->
</style>

<table width="100%" id="paging">
	
	<tr>
		<td width="100%">
			<div align="right">
	
	<!-- PENDAFTARAN HAKMLIK DAN RIZAB-->
	#if($jenis_button == "1")<img src="../img/1current.png" alt="" border="0" title="Senarai Hakmilik/Rizab"/>#else<img src="../img/1enable.png" alt="" border="0" title="Senarai Hakmilik/Rizab" onclick="screen1()" onMouseOver="this.style.cursor='pointer';"/>#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($jenis_button == "2")<img src="../img/2current.png" alt="" border="0" title="Hakmilik/Rizab"/>#else<img src="../img/2enable.png" alt="" border="0" title="Hakmilik/Rizab" onclick="screen2('$idHakmilik','$socRizab')" onMouseOver="this.style.cursor='pointer';"/>#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($jenis_button == "3")<img src="../img/3current.png" alt="" border="0" title="Pembangunan"/>#else<img src="../img/3enable.png" alt="" border="0" title="Pembangunan" onclick="screen3('$idHakmilik')" onMouseOver="this.style.cursor='pointer';"/>#end<img src="../img/arrowgaris.png" alt="" border="0"/><!-- PENDAFTARAN IMEJAN-->#if($jenis_button == "4")<img src="../img/4current2.png" alt="" border="0" title="Imejan"/>#else<img src="../img/4enable2.png" alt="" border="0" title="Imejan" onclick="screen4('$idHakmilik')" onMouseOver="this.style.cursor='pointer';"/>#end
	
	
	
	<!-- @author : Firzan
		 @comment : to cater hakmilik sambungan in Rekod hakmilik/rizab
	-->
	<!-- HAKMILIK SAMBUNGAN -->
	<!--
	#if($jenis_button == "5")
	<img src="../img/5current.png" alt="" border="0" title="Hakmilik Sambungan"/>
	#else
	<img src="../img/5enable.png" alt="" border="0" title="Hakmilik Sambungan" onclick="screen5('$idHakmilik')" onMouseOver="this.style.cursor='pointer';"/>
	#end
	-->
			</div>
		</td>
	</tr>

</table>
<input type="hidden" name="socNegeri"  value="$!cariIdNegeri" />
<input type="hidden" name="socDaerah"  value="$!cariIdDaerah" />
<input type="hidden" name="socMukim"  value="$!cariIdMukim" />
<input type="hidden" name="socJenisTanah" value="$!cariIdJenisTanah">
<input type="hidden" name="socStatus" value="$!cariIdStatus">

<script>
	
function screen1(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=";
	document.${formName}.submit();
}

function screen2(id,jenis){
   	//if(jenis == 'Y' || jenis == 'T'){
	if(jenis == 'M'){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id;
	}else{
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
	}
	document.${formName}.submit();
}

function screen3(id){
	
	document.${formName}.command.value = ""; 
	document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodTanah&firstAction=PendaftaranPembangunan&idHakmilik="+id;
	document.${formName}.submit();
}

function viewTindakan(id_,idSusulanStatus,idStatusFail,pautan,idGambar){
	
 	document.${formName}.idHakmilikPerihal.value = id_; 	
	doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=viewDetailKeluasan&idsusulanstatus="+idSusulanStatus+"&idstatusfail="+idStatusFail+"&pautan="+pautan+"&idGambar="+idGambar);
 } 

function screen4(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&idHakmilik="+id;
	document.${formName}.submit();
}

function screen5(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=paparDetailHakmilik&idHakmilik="+id;
	document.${formName}.submit();
}



</script>


