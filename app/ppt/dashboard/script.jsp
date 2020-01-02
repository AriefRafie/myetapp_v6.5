<script type="text/javascript" >

    //alert(" MAIN :"+document.getElementById('table_utama').clientHeight);
	
	//alert("YYYYY :"+document.getElementById('group_tab'));
	
	if (document.getElementById('group_tab') != null && document.getElementById('group_tab') != 'undefined') 
	{
		
		var tinggi = (document.getElementById('table_utama').clientHeight - 150);
		//alert('tinggi 1 : '+tinggi);
		if(tinggi<300)
		{
			tinggi=250;
		}
		document.getElementById('group_tab').style.height = tinggi;	
		
			//alert(" GROUP :"+document.getElementById('group_tab').clientHeight);
	}
	//alert("1");
	if (document.getElementById('div_pengumuman') != null && document.getElementById('div_pengumuman') != 'undefined') 
	{
		var tinggi = (document.getElementById('table_utama').clientHeight - 200);
		//alert('tinggi 2 : '+tinggi);
		if(tinggi<300)
		{
			tinggi=200;
		}
		document.getElementById('div_pengumuman').style.height = tinggi;
		
			//alert(" PENGUMUMAN :"+document.getElementById('div_pengumuman').clientHeight);	
	}
	
	//alert("2");






	

</script>