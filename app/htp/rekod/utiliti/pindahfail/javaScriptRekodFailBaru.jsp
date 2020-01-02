<script>
// Fungsi carian di skrin pertukaran fail baru
// [Cari]
	function carianFailBaru(){   
		if(document.${formName}.txtNoFail.value == ''){ 
			alert('Sila masukkan " No. Fail " terlebih dahulu.'); 
			document.${formName}.txtNoFail.focus();
			return; 
		}
		document.${formName}.mode.value = 'carian';
		doAjaxCall${formName}("carianfailbaru","firstAction=failbaru");	
		
	}
	// [link No.fail]
	function carianFailBaruPapar(idp){   
		document.${formName}.mode.value = 'carianpapar';
		doAjaxCall${formName}("carianfailbaru","firstAction=failbaru&idpermohonan"+idp);	
		
	}	
	// [Simpan]
	function simpanFailBaru(){   
		document.${formName}.mode.value = 'simpan';
		 if ( !window.confirm("Adakah Anda Pasti ?") ){
			   return;
		 }
		doAjaxCall${formName}("carianfailbaru","firstAction=failbaru");	
		
	}	

	function hakmilik_detail2(idp){
		//doAjaxCall${formName}("paparDetailHakmilik2","idHakmilik="+id);
		carianFailBaruPapar(idp);	
		
	}

	function rizab_detail2(idp){
		//doAjaxCall${formName}("paparDetailRizab2","idPermohonan="+id);
		carianFailBaruPapar(idp);	
		
	}
		
</script>
