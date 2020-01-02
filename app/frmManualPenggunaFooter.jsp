
<script>
	//Panduan Memohon Secara Online - Borang A
	//Panduan Memohon Secara Online - Borang P
	
    
	function openManualX(pautan){
    	var url = "../borang/"+pautan+".pdf";
	    
	    var hWnd = window.open(url,'printuser','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
    }
    
    function menuUtamaParam(kelas,menu,role){
    	if(role=='adminppk'){
			var id = '$EkptgUtil.getTabID("'My Info'","adminppk")';
			document.${formName}.action = id+"?_portal_module="+kelas;
			
		}else{
			var id = '$EkptgUtil.getTabID("'My Info'","user_ppk")';
			document.${formName}.action = id+"?_portal_module="+kelas;
			
		}
		document.${formName}.submit();
		
	}
	
	function menuUtamaParamX(kelas,menu,role){
		var id = '$EkptgUtil.getTabID('+menu+','+role+')';
		document.${formName}.action = id+"?_portal_module="+kelas;
		document.${formName}.submit();
		
	}
	
	function menuUtama(){
		var id = '$EkptgUtil.getTabID("Menu Utama","online_kjp")';
		document.${formName}.action = id+"?_portal_module=ekptg.view.online.FrmOnlineMenuUtamaKJP";
		document.${formName}.submit();
		
	}
</script>