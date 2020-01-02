
 <table width="100%">
    <tr>
      <td colspan="5">&nbsp;</td>
    </tr>
 </table>
<fieldset>

 <legend><strong>$laporan</strong>
</legend>
<table width="100%">
	$header
	$data
    
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL $!nama</strong></legend>
##parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>BIL.</strong></td>
      <td width="20%"><strong>NO. FAIL</strong></td>
      <td width="45%"><strong>NAMA PEMOHON</strong></td>
      <td width="15%"><strong>TARIKH MOHON</strong></td>
       <td width="15%"><strong>TARIKH PERBICARAAN</strong></td>
      <!--  <td width="25%"><strong>AGENSI</strong></td> -->
    </tr>
#set ($list = '')
#foreach ($list in $ListPermohonan)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
      <td class="$row" valign="top" align="center">$list.bil</td>
      <td class="$row" valign="top">$list.noFail</td>
      <td class="$row" valign="top">$list.pemohon</td>
      <td class="$row" valign="top">$list.tarikhMohon</td>
      <td class="$row" valign="top">$list.tarikhBicara</td>
<!--       <td class="$row" valign="top">$list.Agensi</td>
 -->    #else
      <td colspan="5" class="$row" style="text-align:center">TIADA REKOD</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="5">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input name="action2" id="action2" type="hidden" value="$action2" />

<script type="text/javascript">
	//var params = "&idNegeri="+document.${formName}.socNegeri.value;
	//params += "&idUnit="+document.${formName}.socUnit.value;
	//params += "&tahun="+document.${formName}.socTahun.value;

	function terperinci(idnegeri,idunit,tahun,bulan,iduser) {
		var params = 'idNegeri='+idnegeri+'&idUnit='+idunit+'&tahun='+tahun+'&bulan='+bulan+'&idUser='+iduser+'&command_=terperinci';
		doAjaxCall${formName}('terperinci',params);
	
	}
	function terperinciSelesai(idnegeri,idunit,tahun,bulan,iduser) {
		var params = "idNegeri="+idnegeri+"&idUnit="+idunit+"&tahun="+tahun+"&bulan="+bulan+"&idUser="+iduser+'&command_=selesai';
		doAjaxCall${formName}("selesai",params);
	}

  function doChangeSOC() {
      document.${formName}.action2.value = "";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMySPATA&action2=";
      doAjaxCall${formName}();
  }
  function viewRekod(ID_PERMOHONAN) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMySPATA&action2=viewRekod&ID_PERMOHONAN=" + ID_PERMOHONAN;
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function searchRekod() {
      if (document.${formName}.CARIAN_NOFAIL.value == '' && document.${formName}.CARIAN_TARIKHMOHON.value == '' && document.${formName}.CARIAN_NAMAPEMOHON.value == '' && document.${formName}.CARIAN_NOKPPEMOHON.value == '' && document.${formName}.CARIAN_NEGERI.value == '' && document.${formName}.CARIAN_DAERAH.value == '' && document.${formName}.CARIAN_MUKIM.value == '' && document.${formName}.CARIAN_AGENSI.value == '') {
		  alert('Sila pastikan salah satu medan carian diisi.');
		  document.${formName}.CARIAN_NOFAIL.focus();
		  return;
	  }
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMySPATA&action2=searchRekod";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function emptyRekod() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMySPATA&action2=";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>