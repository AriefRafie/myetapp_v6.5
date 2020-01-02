<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>	
    	<td></td>
  </tr>
	<tr>	
    	<td>
    		
		<fieldset>	
        <legend><strong>Manual Pengguna  </strong></legend>
        <table border="0" cellspacing="1" cellpadding="1" width="100%" >

	#if($listDokumen.size()>0)
	<!--gred = userHQ-->
#foreach($list in $listDokumen)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">

<td  align="center" valign="top" class="$list.rowCss" >$list.BIL</td>

<td  align="left" valign="top" class="$list.rowCss" ><a href="javascript:paparDoc('$list.ID_DOKUMENADMIN');"><font color="blue"><u>$list.NAMA_DOC</u></font></a></td>

<td  align="left" valign="top" class="$list.rowCss" ><font color="blue">$list.KETERANGAN</font></td>

<td  align="left" valign="top" class="$list.rowCss" ></td>

</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>
		</fieldset>
		
		</td>
	</tr>
</table>

<script>
	//Panduan Memohon Secara Online - Borang A
	//Panduan Memohon Secara Online - Borang P
	
	function openManual(pautan){
		//alert(pautan)
		var param = "";
	    var url = "../borang/"+pautan+".pdf";
	    var hWnd = window.open(url,"Manual Pengguna","width=800,height=500, resizable=yes,scrollbars=yes");
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
    }
    
	function openManualX(pautan){
    	var url = "../borang/"+pautan+".pdf";
	    
	    var hWnd = window.open(url,'printuser','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
    }
	
	function menuUtama(){
		document.${formName}.action = "$EkptgUtil.getTabID('Menu',$myrole)?_portal_module=ekptg.view.online.FrmOnlineMenuUtama";
		document.${formName}.submit();
	}
	
	function paparDoc(idDoc) {
    var url = "../servlet/ekptg.view.DisplayBlobManual?CSS_TITLE="+idDoc;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	}

</script>
