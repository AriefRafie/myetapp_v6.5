<div id="divMaklumatPermohonan"> #parse("$templateDir/maklumatPermohonan.jsp") </div><br/>
<div id="divSenaraiHakmilik"> #parse("$templateDir/senaraiHakmilik.jsp") </div><br/>
#if ($!maklumatPermohonan.flagHantar == 'T')
	<div id="divSenaraiDokumen"> #parse("$templateDir/muatNaikDokumen.jsp") </div><br/>
#end
<div id="divSenaraiDokumen"> #parse("$templateDir/senaraiDokumen.jsp") </div><br/>