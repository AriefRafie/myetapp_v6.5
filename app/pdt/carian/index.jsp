<fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td width="29%" align="right" scope="row">No. Akta/ Enakmen</td>
      <td width="1%" scope="row">:</td>
      <td width="70%">
        <input name="txtNoAkta" type="text" id="txtNoAkta" value="$txtNoAkta" />
      </td>
    </tr>
    <tr>
      <td align="right" scope="row">Nama Akta/ Enakmen</td>
      <td scope="row">:</td>
      <td>
        <input name="txtNamaAkta" type="text" id="txtNamaAkta" value="$txtNamaAkta" />
      </td>
    </tr>
    <!--  <tr>
      <td align="right" scope="row">Tarikh Kuatkuasa</td>
      <td scope="row">:</td>
      <td>
        <input name="txdTarikhKuatkuasa" type="text" id="txdTarikhKuatkuasa" value="$txdTarikhKuatkuasa" size="10" />
        <a href="javascript:displayDatePicker('txdTarikhKuatkuasa',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>      
      </td>
    </tr> -->
     <tr>
      <td align="right" valign="top" scope="row"><i>Tag</i> Dokumen</td>
      <td scope="row" valign="top">:</td>
      <td>
      	<textarea name="tag_dokumen" cols="41" rows="3" onblur="this.value=this.value.toUpperCase()" >$!tag_Dokumen</textarea>
        <input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/>
      </td>
    </tr>
    
    <tr>
      <td align="right" scope="row">&nbsp;</td>
      <td scope="row">&nbsp;</td>
      <td>
        <input type="submit" name="cmdCari" id="cmdCari" value="Cari" onclick="cariMaklumat()" />
        <input type="reset" name="cmdKosongkanAkta" value="Kosongkan" onclick="kosongCarianAkta()" />
      </td>
    </tr>
  </table>
</fieldset>
<fieldset>
  <legend><strong>Senarai Carian</strong></legend>
  #parse("app/utils/record_paging.jsp") 
  <table width="100%">
     
    <tr class="table_header">
      <td width="3%" scope="row"><strong>Bil.</strong></td>
      <td width="30%"><strong>No. Akta/ Enakmen</strong></td>
      <td width="62%"><strong>Nama Akta/ Enakmen</strong></td>
      <!-- <td width="17%"><strong>Tarikh Kuatkuasa</strong></td> -->
      <td width="5%">&nbsp;</td>
    </tr>
#set ($fail = '')

#foreach ($fail in $SenaraiFail)
	#set( $i = $velocityCount )
	#if ( ($i % 2) != 1 )
		#set( $row = "row2" )
	#else
		#set( $row = "row1" )
	#end
    	<tr>
      <td height="20" class="$row">
      $!fail.bil.
      </td>
      <td class="$row">
    #if ($!fail.bil != '') 
      <a href="javascript:viewCarian('$!fail.id','$!fail.kod','$!fail.maklumat','$!fail.keterangan')" style="color:#0000FF">$!fail.kod</a>
    #else
      <div align="left">$!fail.kod</div>
    #end
    </td>
      <td class="$row">$!fail.keterangan</td>
      <!-- <td class="$row">$fail.TarikhKuatkuasa</td> -->
      <td align=right class="$row">
      <!-- <a href = "javascript:viewAktaBlob('$fail.IDAkta')">
      <img border="0" src="../img/pdf-small.png" /></a>
          <a alt="Hapus Akta" href = "javascript:deleteAkta('$fail.IDAkta')">
      &nbsp;&nbsp;<img border="0" src="../img/delete.gif" /></a>  
      </td> -->
    </tr>
#end
  </table>
</fieldset>
<input type="hidden" id="Akta_IDAkta" name="Akta_IDAkta" />  

<script>
	function cariMaklumat() {
		doAjaxCall${formName}("cari","");		
	}
	
	function paparAkta(idAkta) {
	    var url = "../x/${securityToken}/ekptg.view.pdt.FrmViewAkta2?idAkta="+idAkta;
	    var hWnd = window.open(url,'Cetak','width=900,height=700, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}

	function viewCarian(id,kod,submodul,tajuk) {
		var urlTemp = "";
		
		if(submodul=="AKTA"){
			//getParam("txtNoAkta")
			urlTemp = "$EkptgUtil.getTabID('Pengurusan Dokumen Teks',$portal_role)?_portal_module=ekptg.view.pdt.FrmViewAkta&action=cari&txtNoAkta="+kod;
	    	//document.Fekptg_view_pdt_FrmViewAkta.Akta_IDAkta.value = Akta_IDAkta;
    		document.${formName}.action = urlTemp;
			document.${formName}.submit();

		}else if(submodul=="AKTAPINDA"){
			//getParam("txtNoAkta")
			urlTemp = "$EkptgUtil.getTabID('Pengurusan Dokumen Teks',$portal_role)?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan&action=cari&txtNoAkta="+kod;
    		document.${formName}.action = urlTemp;
			document.${formName}.submit();

		}else if(submodul=="ENAKMEN"){
			//getParam("txtNoEnakmen")
			urlTemp = "$EkptgUtil.getTabID('Pengurusan Dokumen Teks',$portal_role)?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=cari&txtNoEnakmen="+kod;
    		document.${formName}.action = urlTemp;
			document.${formName}.submit();

		}else if(submodul=="ENAKMENPINDA"){
			//getParam("txtNoEnakmenP")
			urlTemp = "$EkptgUtil.getTabID('Pengurusan Dokumen Teks',$portal_role)?_portal_module=ekptg.view.pdt.PendaftaranEnakmenPindaan&action=cari&txtNoEnakmenP="+kod;
    		document.${formName}.action = urlTemp;
			document.${formName}.submit();

		}else if(submodul=="PEKELILING"){
			//getParam("txtTajukPekeliling")
			urlTemp = "$EkptgUtil.getTabID('Pengurusan Dokumen Teks',$portal_role)?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=cari&txtTajukPekeliling="+tajuk;
	    	//document.Fekptg_view_pdt_FrmViewAkta.Akta_IDAkta.value = Akta_IDAkta;
    		document.${formName}.action = urlTemp;
			document.${formName}.submit();

		}else if(submodul=="DOKUMEN"){
			//getParam("txtNoRujDokuman")
			urlTemp = "$EkptgUtil.getTabID('Pengurusan Dokumen Teks',$portal_role)?_portal_module=ekptg.view.pdt.PendaftaranDokumen&action=cari&txtNoRujDokuman="+kod;
    		document.${formName}.action = urlTemp;
			document.${formName}.submit();

		}else if(submodul=="PERUNDANGAN"){
			//getParam("txtTajukDokumen")
			urlTemp = "$EkptgUtil.getTabID('Pengurusan Dokumen Teks',$portal_role)?_portal_module=ekptg.view.pdt.MaklumatPerundangan&action=cari&txtTajukDokumen="+kod;
    		document.${formName}.action = urlTemp;
			document.${formName}.submit();

		}
	
	}
	
</script>