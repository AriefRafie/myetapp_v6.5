<script>
//setPilihanHarta_general();

function pilihan_CheckAll()
{  

var checkall = document.${formName}.all;
if(testIsValidObject(checkall) == true)
{
	if(document.${formName}.all.checked == true)
	{	
		var ids = document.${formName}.ids;
		if(testIsValidObject(ids) == true)
		{
			if (document.${formName}.ids.length > 1)
			{
				for (mainlayer = 0; mainlayer < document.${formName}.ids.length; mainlayer++)
				{
					document.${formName}.ids[mainlayer].checked = true;													
				}	
			}
			else
			{
					document.${formName}.ids.checked = true;	
			}	
		}
	}
	else
	{
		var ids = document.${formName}.ids;
		if(testIsValidObject(ids) == true)
		{
			if (document.${formName}.ids.length > 1)
			{
				for (mainlayer = 0; mainlayer < document.${formName}.ids.length; mainlayer++)
				{
					document.${formName}.ids[mainlayer].checked = false;	
					
																	
				}	
			}
			else
			{
					document.${formName}.ids.checked = false;	
			}	
		}
	}	
}


setPilihanHarta("");
}


function setPilihanHarta_general()
{
	
	var tot = 0;
	var ids = document.${formName}.ids;
	
	if(testIsValidObject(ids) == true)
	{
		
		if (document.${formName}.ids.length > 1)
		{
			for (mainlayerxxx = 0; mainlayerxxx < document.${formName}.ids.length; mainlayerxxx++)
			{
				if(document.${formName}.ids[mainlayerxxx].checked == false)
				{
				tot++;
				}
			}
			
		}
		else
		{
			if(document.${formName}.ids.checked == false)
			{
				tot++;
			}
		}
	}	
	
	
	
	
	if(tot>0)
	{
	document.${formName}.all.checked = false;
	}
	else
	{
	document.${formName}.all.checked = true;
	}	

}


function setPilihanHarta(id_hta)
{
var tot = 0;
var ids = document.${formName}.ids;
var tot_check_kt = 0;
if(testIsValidObject(ids) == true)
{
	if (document.${formName}.ids.length > 1)
	{
		for (mainlayer = 0; mainlayer < document.${formName}.ids.length; mainlayer++)
		{
				
					if(document.${formName}.ids[mainlayer].checked == true)
					{
						var display_ob = "display_ob" + document.${formName}.ids[mainlayer].value;
						var display_ob_objek = document.getElementById(display_ob);
						if(testIsValidObject(display_ob_objek) == true)
						{
							document.getElementById(display_ob).style.display="";
							var main_ob = "main_ob" + document.${formName}.ids[mainlayer].value; 
							var main_ob_obj = document.${formName}[main_ob];			
							if(testIsValidObject(main_ob_obj) == true)
							{
								var jenis_perintah_harta = "jenis_perintah_harta" + document.${formName}.ids[mainlayer].value; 
								if(document.${formName}[jenis_perintah_harta].value == "2")
								{
									if (document.${formName}[main_ob].length > 1)
									{
										for (sublayer1 = 0; sublayer1 < document.${formName}[main_ob].length; sublayer1++)
										{
										document.${formName}[main_ob][sublayer1].checked = true;													
										}	
									}
									else
									{
										document.${formName}[main_ob].checked = true;							
									}
								}	
							}			
							setPilihanOB(document.${formName}.ids[mainlayer].value);
						}			
					}
					else
					{
					tot++;
					var display_ob = "display_ob" + document.${formName}.ids[mainlayer].value;
					var display_ob_objek = document.getElementById(display_ob);
						if(testIsValidObject(display_ob_objek) == true)
						{	
							document.getElementById(display_ob).style.display="none";
							var main_ob = "main_ob" + document.${formName}.ids[mainlayer].value; 
							var main_ob_obj = document.${formName}[main_ob];			
							if(testIsValidObject(main_ob_obj) == true)
							{
								if (document.${formName}[main_ob].length > 1)
								{
									for (sublayer1 = 0; sublayer1 < document.${formName}[main_ob].length; sublayer1++)
									{
									document.${formName}[main_ob][sublayer1].checked = false;													
									}	
								}
								else
								{
									document.${formName}[main_ob].checked = false;							
								}
							}
									
							setPilihanOB(document.${formName}.ids[mainlayer].value);
						}			
					}
				}						
			
	}
	else
	{					
			var display_ob = "display_ob" + document.${formName}.ids.value;
			var display_ob_objek = document.getElementById(display_ob);
				if(document.${formName}.ids.checked == true)
				{
					var display_ob = "display_ob" + document.${formName}.ids.value;
					var display_ob_objek = document.getElementById(display_ob);
					if(testIsValidObject(display_ob_objek) == true)
					{				
						document.getElementById(display_ob).style.display="";
						var main_ob = "main_ob" + document.${formName}.ids.value; 	
						var main_ob_obj = document.${formName}[main_ob];			
						if(testIsValidObject(main_ob_obj) == true)
						{	
							var jenis_perintah_harta = "jenis_perintah_harta" + document.${formName}.ids.value; 
							if(document.${formName}[jenis_perintah_harta].value == "2")
							{		
								if (document.${formName}[main_ob].length > 1)
								{
									for (sublayer1 = 0; sublayer1 < document.${formName}[main_ob].length; sublayer1++)
									{
									document.${formName}[main_ob][sublayer1].checked = true;												
									}	
								}
								else
								{
									document.${formName}[main_ob].checked = true;					
								}
							}	
						}		
					setPilihanOB(document.${formName}.ids.value);
					}
				}
				else
				{
				tot++;
					var display_ob = "display_ob" + document.${formName}.ids.value;
					var display_ob_objek = document.getElementById(display_ob);
					if(testIsValidObject(display_ob_objek) == true)
					{
						document.getElementById(display_ob).style.display="none";
						var main_ob = "main_ob" + document.${formName}.ids.value; 	
						var main_ob_obj = document.${formName}[main_ob];			
						if(testIsValidObject(main_ob_obj) == true)
						{			
							if (document.${formName}[main_ob].length > 1)
							{
								for (sublayer1 = 0; sublayer1 < document.${formName}[main_ob].length; sublayer1++)
								{
								document.${formName}[main_ob][sublayer1].checked = false;												
								}	
							}
							else
							{
								document.${formName}[main_ob].checked = false;					
							}
						}		
						setPilihanOB(document.${formName}.ids.value);	
					}			
				}
				
	}
						

}

			if(tot>0)
			{
			document.${formName}.all.checked = false;
			}
			else
			{
			document.${formName}.all.checked = true;
			}


}



function setPilihanOB(id_hta)
{
var tot_ob = 0;
var tot_ob_check = 0;
var main_ob = "main_ob" + id_hta; 
var main_ob_objek = document.${formName}[main_ob];

var jenis_perintah_harta = "jenis_perintah_harta" + id_hta; 
var jenis_perintah_harta_objek = document.getElementById(jenis_perintah_harta);

if(testIsValidObject(main_ob_objek) == true)
{

	if (document.${formName}[main_ob].length > 1)
	{	
		for (sublayer = 0; sublayer < document.${formName}[main_ob].length; sublayer++)
		{
			var tr_display = "tr_display" + id_hta + document.${formName}[main_ob][sublayer].value;
			var tr_display_objek = document.getElementById(tr_display);
			if(testIsValidObject(tr_display_objek) == true)
			{			
				if(document.${formName}[main_ob][sublayer].checked == true)
				{						
					document.getElementById(tr_display).style.display="";
					var check_ob = "check_ob"+id_hta+document.${formName}[main_ob][sublayer].value;			
					var check_ob_objek = document.${formName}[check_ob];
					if(testIsValidObject(check_ob_objek) == true)
					{
						if (document.${formName}[check_ob].length > 1)
						{
							for (sublayer2 = 0; sublayer2 < document.${formName}[check_ob].length; sublayer2++)
							{
							setPilihanOB_minor(id_hta,document.${formName}[main_ob][sublayer].value,document.${formName}[check_ob][sublayer2].value);									
							}	
						}
						else
						{
							setPilihanOB_minor(id_hta,document.${formName}[main_ob][sublayer].value,document.${formName}[check_ob].value);		
						}
					}	
				}
				else
				{
							
				tot_ob++;			
				document.getElementById(tr_display).style.display="none";
					var check_ob = "check_ob"+id_hta+document.${formName}[main_ob][sublayer].value;
					var check_ob_objek = document.${formName}[check_ob];
					if(testIsValidObject(check_ob_objek) == true)
					{
						if (document.${formName}[check_ob].length > 1)
						{
							for (sublayer2 = 0; sublayer2 < document.${formName}[check_ob].length; sublayer2++)
							{
							document.${formName}[check_ob][sublayer2].checked = false;	
							setPilihanOB_minor(id_hta,document.${formName}[main_ob][sublayer].value,document.${formName}[check_ob][sublayer2].value);										
							}	
						}
						else
						{
							document.${formName}[check_ob].checked = false;
							setPilihanOB_minor(id_hta,document.${formName}[main_ob][sublayer].value,document.${formName}[check_ob].value);		
						}
					}
				}
			}				
		}	
	}
	else
	{
			var tr_display = "tr_display"+ id_hta + document.${formName}[main_ob].value;
			var tr_display_objek = document.getElementById(tr_display);
			if(testIsValidObject(tr_display_objek) == true)
			{
					if(document.${formName}[main_ob].checked == true)
					{					
						document.getElementById(tr_display).style.display="";
						var check_ob = "check_ob"+id_hta+document.${formName}[main_ob].value;
						var check_ob_objek = document.${formName}[check_ob];
						if(testIsValidObject(check_ob_objek) == true)
						{
							if (document.${formName}[check_ob].length > 1)
							{
								for (sublayer2 = 0; sublayer2 < document.${formName}[check_ob].length; sublayer2++)
								{
								setPilihanOB_minor(id_hta,document.${formName}[main_ob].value,document.${formName}[check_ob][sublayer2].value);								
								}	
							}
							else
							{
								setPilihanOB_minor(id_hta,document.${formName}[main_ob].value,document.${formName}[check_ob].value);	
							}
						}	
									
					}
					else
					{						
					tot_ob++;			
						document.getElementById(tr_display).style.display="none";
						var check_ob = "check_ob"+id_hta+document.${formName}[main_ob].value;
						var check_ob_objek = document.${formName}[check_ob];
						if(testIsValidObject(check_ob_objek) == true)
						{
							if (document.${formName}[check_ob].length > 1)
							{
								for (sublayer2 = 0; sublayer2 < document.${formName}[check_ob].length; sublayer2++)
								{
								document.${formName}[check_ob][sublayer2].checked = false;
								setPilihanOB_minor(id_hta,document.${formName}[main_ob].value,document.${formName}[check_ob][sublayer2].value);									
								}	
							}
							else
							{
								document.${formName}[check_ob].checked = false;
								setPilihanOB_minor(id_hta,document.${formName}[main_ob].value,document.${formName}[check_ob].value);		
							}
						}
					}
			}		
	}
						

}

	
//setPilihanOB_KS();
}



function setPilihanOB_KS_all(flag_open,id_hta,id_ob)
{

var check_ks = 0;
var total_check_ks = 0;
var ids = document.${formName}.ids;
if(testIsValidObject(ids) == true)
{

	if (document.${formName}.ids.length > 1)
	{
		for (idslength = 0; idslength < document.${formName}.ids.length; idslength++)
		{	
			var jenis_perintah_harta = "jenis_perintah_harta" + document.${formName}.ids[idslength].value;  			
			var main_ob = "main_ob" + document.${formName}.ids[idslength].value; 
			var ids = document.${formName}.ids;
			if(testIsValidObject(main_ob) == true)
			{ 
				if(document.${formName}[jenis_perintah_harta].value == "2")	
				{
									
						if (document.${formName}[main_ob].length > 1)
						{
							for (mainoblength = 0; mainoblength < document.${formName}[main_ob].length; mainoblength++)
							{
							total_check_ks++;
								if(document.${formName}.ids[idslength].checked == true)	
								{
									//document.${formName}[main_ob][mainoblength].checked = true;
									if(document.${formName}[main_ob][mainoblength].checked == true)	
									{
									check_ks++;
									}
								}	
							}						
						}
						else
						{
						total_check_ks++;
								if(document.${formName}.ids[idslength].checked == true)	
								{
									//document.${formName}[main_ob].checked = true;
									if(document.${formName}[main_ob].checked == true)	
									{
									check_ks++;
									}
								}
						}
														
				}
			}
			else
			{
				if(document.${formName}[jenis_perintah_harta].value == "2")	
				{
				total_check_ks++;
					if(document.${formName}.ids[idslength].checked == true)	
					{
					check_ks++;
					}
				}			
			}				    	
		}			
	}
	else
	{
			var jenis_perintah_harta = "jenis_perintah_harta" + document.${formName}.ids.value;  
			if(document.${formName}[jenis_perintah_harta].value == "2")	
			{
			total_check_ks++;
				if(document.${formName}.ids.checked == true)	
				{
				check_ks++;
				}
			}
	}
						

}

var flag_bukak_ks = "";

if(flag_open=="all")
{
	var checkall = document.${formName}.all;
	if(testIsValidObject(checkall) == true)
	{
		if(document.${formName}.all.checked == true)
		{
			if(total_check_ks>0)
			{
			//alert("total_check_ks:"+total_check_ks+";BUKAK semua pentadbir")
			flag_bukak_ks = "open";
			}
		}
		else
		{
			if(total_check_ks>0)
			{
			//alert("total_check_ks:"+total_check_ks+";TUTUP semua pentadbir")	
			flag_bukak_ks = "close";		
			}
		}
	}
	//alert("bawah");
}		


if(flag_open=="harta")
{
	var ids = document.${formName}.ids;
	if(testIsValidObject(ids) == true)
	{
		if (document.${formName}.ids.length > 1)
		{				
			for (pp = 0; pp < document.${formName}.ids.length; pp++)
			{
				var jenis_perintah_harta = "jenis_perintah_harta" + document.${formName}.ids[pp].value;  
				if(document.${formName}[jenis_perintah_harta].value == "2")	
				{				
					if(document.${formName}.ids[pp].checked == true && document.${formName}.ids[pp].value == id_hta)	
					{
						if(total_check_ks>0)
						{
						//alert("total_check_ks:"+total_check_ks+";BUKAK semua pentadbir")
						flag_bukak_ks = "open";
						}				
					}	
					if(document.${formName}.ids[pp].checked == false && document.${formName}.ids[pp].value == id_hta)	
					{
						if(total_check_ks>0)
						{
						//alert("total_check_ks:"+total_check_ks+";TUTUP semua pentadbir")
						flag_bukak_ks = "close";
						}					
					}
				}					
			}						
		}
		else
		{  
				var jenis_perintah_harta = "jenis_perintah_harta" + document.${formName}.ids.value;  
				if(document.${formName}[jenis_perintah_harta].value == "2")	
				{
					if(document.${formName}.ids.checked == true && document.${formName}.ids.value == id_hta)	
					{
						if(total_check_ks>0)
						{
						//alert("total_check_ks:"+total_check_ks+";BUKAK semua pentadbir")
						flag_bukak_ks = "open";
						}				
					}	
					if(document.${formName}.ids.checked == false && document.${formName}.ids.value == id_hta)	
					{
						if(total_check_ks>0)
						{
						//alert("total_check_ks:"+total_check_ks+";TUTUP semua pentadbir")
						flag_bukak_ks = "close";
						}				
					}
				}			
		}
		
	}
}		



if(flag_open=="ob")
{
	var ids = document.${formName}.ids;
	if(testIsValidObject(ids) == true)
	{
		if (document.${formName}.ids.length > 1)
		{				
			for (pp = 0; pp < document.${formName}.ids.length; pp++)
			{
				var jenis_perintah_harta = "jenis_perintah_harta" + document.${formName}.ids[pp].value;  
				if(document.${formName}[jenis_perintah_harta].value == "2")	
				{				
					var main_ob = "main_ob" + document.${formName}.ids[pp].value; 
					var main_ob_objek = document.${formName}[main_ob];
					if(testIsValidObject(main_ob_objek) == true)
					{
						if (document.${formName}[main_ob].length > 1)
						{				
							for (kkk = 0; kkk < document.${formName}[main_ob].length; kkk++)
							{
								if(document.${formName}[main_ob][kkk].checked == true && document.${formName}.ids[pp].value == id_hta && document.${formName}[main_ob][kkk].value == id_ob )	
								{
									if(total_check_ks>0)
									{
									//alert("total_check_ks:"+total_check_ks+";BUKAK semua pentadbir")
									flag_bukak_ks = "open";
									}				
								}	
								if(document.${formName}[main_ob][kkk].checked == false && document.${formName}.ids[pp].value == id_hta && document.${formName}[main_ob][kkk].value == id_ob )	
								{
									if(total_check_ks>0)
									{
									//alert("total_check_ks:"+total_check_ks+";TUTUP semua pentadbir")
									flag_bukak_ks = "close";
									}				
								}							
							}
						}
						else
						{
								if(document.${formName}[main_ob].checked == true && document.${formName}.ids[pp].value == id_hta && document.${formName}[main_ob].value == id_ob )	
								{
									if(total_check_ks>0)
									{
									//alert("total_check_ks:"+total_check_ks+";BUKAK semua pentadbir")
									flag_bukak_ks = "open";
									}				
								}	
								if(document.${formName}[main_ob].checked == false && document.${formName}.ids[pp].value == id_hta && document.${formName}[main_ob].value == id_ob )	
								{
									if(total_check_ks>0)
									{
									//alert("total_check_ks:"+total_check_ks+";TUTUP semua pentadbir")
									flag_bukak_ks = "close";
									}				
								}	
						}	
					
					}	
				}	 			
			}						
		}
		else
		{  
				var jenis_perintah_harta = "jenis_perintah_harta" + document.${formName}.ids.value;  
				if(document.${formName}[jenis_perintah_harta].value == "2")	
				{
					var main_ob = "main_ob" + document.${formName}.ids.value; 
					var main_ob_objek = document.${formName}[main_ob];
					if(testIsValidObject(main_ob_objek) == true)
					{					
							if (document.${formName}[main_ob].length > 1)
							{				
								for (yyy = 0; yyy < document.${formName}[main_ob].length; yyy++)
								{
									if(document.${formName}[main_ob][yyy].checked == true && document.${formName}.ids.value == id_hta && document.${formName}[main_ob][yyy].value == id_ob )	
									{
										if(total_check_ks>0)
										{
										//alert("total_check_ks:"+total_check_ks+";BUKAK semua pentadbir")
										flag_bukak_ks = "open";
										}				
									}	
									if(document.${formName}[main_ob][yyy].checked == false && document.${formName}.ids.value == id_hta && document.${formName}[main_ob][yyy].value == id_ob )	
									{
										if(total_check_ks>0)
										{
										//alert("total_check_ks:"+total_check_ks+";TUTUP semua pentadbir")
										flag_bukak_ks = "close";
										}				
									}						
								}
							}
							else
							{
									if(document.${formName}[main_ob].checked == true && document.${formName}.ids.value == id_hta && document.${formName}[main_ob].value == id_ob )	
									{
										if(total_check_ks>0)
										{
										//alert("total_check_ks:"+total_check_ks+";BUKAK semua pentadbir")
										flag_bukak_ks = "open";
										}				
									}	
									if(document.${formName}[main_ob].checked == false && document.${formName}.ids.value == id_hta && document.${formName}[main_ob].value == id_ob )	
									{
										if(total_check_ks>0)
										{
										//alert("total_check_ks:"+total_check_ks+";TUTUP semua pentadbir")
										flag_bukak_ks = "close";
										}				
									}
										
							}
						
					}					
				}		
		}
		
	}
}		

//alert("flag_bukak_ks"+flag_bukak_ks);
setOpenCloseKS(flag_bukak_ks);
setPilihanHarta_general();
}


function setOpenCloseKS(flag_bukak_ks)
{
//alert("flag_bukak_ks"+flag_bukak_ks);
	var ids = document.${formName}.ids;
	if(testIsValidObject(ids) == true)
	{
		if (document.${formName}.ids.length > 1)
		{				
			for (pp = 0; pp < document.${formName}.ids.length; pp++)
			{
				var jenis_perintah_harta = "jenis_perintah_harta" + document.${formName}.ids[pp].value;  
				if(document.${formName}[jenis_perintah_harta].value == "2")	
				{	
					if(flag_bukak_ks=="open")
					{
					document.${formName}.ids[pp].checked = true;
					}
					else if(flag_bukak_ks=="close")
					{
					document.${formName}.ids[pp].checked = false;
					}
								
					var main_ob = "main_ob" + document.${formName}.ids[pp].value; 
					var main_ob_objek = document.${formName}[main_ob];
					if(testIsValidObject(main_ob_objek) == true)
					{
						if (document.${formName}[main_ob].length > 1)
						{				
							for (kkk = 0; kkk < document.${formName}[main_ob].length; kkk++)
							{
								if(flag_bukak_ks=="open")
								{
									var display_ob = "display_ob" + document.${formName}.ids[pp].value;
									var display_ob_objek = document.getElementById(display_ob);
									if(testIsValidObject(display_ob_objek) == true)
									{ 
										document.getElementById(display_ob).style.display="";
									}
									document.${formName}[main_ob][kkk].checked = true;
								}
								else if(flag_bukak_ks=="close")
								{
									var display_ob = "display_ob" + document.${formName}.ids[pp].value;
									var display_ob_objek = document.getElementById(display_ob);
									if(testIsValidObject(display_ob_objek) == true)
									{ 
										document.getElementById(display_ob).style.display="none";
									}
									document.${formName}[main_ob][kkk].checked = false;
								}						
							}
						}
						else
						{
							if(flag_bukak_ks=="open")
							{
								var display_ob = "display_ob" + document.${formName}.ids[pp].value;
								var display_ob_objek = document.getElementById(display_ob);
									if(testIsValidObject(display_ob_objek) == true)
									{ 
										document.getElementById(display_ob).style.display="";
									}
								document.${formName}[main_ob].checked = true;
							}
							else if(flag_bukak_ks=="close")
							{
								var display_ob = "display_ob" + document.${formName}.ids[pp].value;
								var display_ob_objek = document.getElementById(display_ob);
									if(testIsValidObject(display_ob_objek) == true)
									{ 
										document.getElementById(display_ob).style.display="none";
									}
								document.${formName}[main_ob].checked = false;
							}
								
						}	
					
					}	
				}	 			
			}						
		}
		else
		{  
				var jenis_perintah_harta = "jenis_perintah_harta" + document.${formName}.ids.value;  
				if(document.${formName}[jenis_perintah_harta].value == "2")	
				{
					document.${formName}.ids.checked = true;
					var main_ob = "main_ob" + document.${formName}.ids.value; 
					var main_ob_objek = document.${formName}[main_ob];
					if(testIsValidObject(main_ob_objek) == true)
					{					
							if (document.${formName}[main_ob].length > 1)
							{				
								for (yyy = 0; yyy < document.${formName}[main_ob].length; yyy++)
								{
									if(flag_bukak_ks=="open")
									{
										var display_ob = "display_ob" + document.${formName}.ids.value;
										var display_ob_objek = document.getElementById(display_ob);
										if(testIsValidObject(display_ob_objek) == true)
										{ 
											document.getElementById(display_ob).style.display="";
										}
										document.${formName}[main_ob][yyy].checked = true;
									}
									else  if(flag_bukak_ks=="close")
									{
										var display_ob = "display_ob" + document.${formName}.ids.value;
										var display_ob_objek = document.getElementById(display_ob);
										if(testIsValidObject(display_ob_objek) == true)
										{ 
											document.getElementById(display_ob).style.display="none";
										}
										document.${formName}[main_ob][yyy].checked = false;
									} 							
								}
							}
							else
							{
									if(flag_bukak_ks =="open")
									{
										var display_ob = "display_ob" + document.${formName}.ids.value;
										var display_ob_objek = document.getElementById(display_ob);
										if(testIsValidObject(display_ob_objek) == true)
										{ 
											document.getElementById(display_ob).style.display="";
										}
										document.${formName}[main_ob].checked = true;
									}
									else  if(flag_bukak_ks =="close")
									{
										var display_ob = "display_ob" + document.${formName}.ids.value;
										var display_ob_objek = document.getElementById(display_ob);
										if(testIsValidObject(display_ob_objek) == true)
										{ 
											document.getElementById(display_ob).style.display="none";
										}
										document.${formName}[main_ob].checked = false;
									} 	
							}
						
					}					
				}		
		}
		
	}
}



function setPilihanOB_minor(id_hta,id_ob,id_ob_selected)
{
var check_ob = "check_ob"+id_hta+id_ob;
var tb = "status_tb"+id_hta+id_ob;
var status_tb = document.${formName}[tb].value;
var check_ob_objek = document.${formName}[check_ob];
if(testIsValidObject(check_ob_objek) == true)
{

	if (document.${formName}[check_ob].length > 1)
	{
		for (sublayer5 = 0; sublayer5 < document.${formName}[check_ob].length; sublayer5++)
		{
				if(document.${formName}[check_ob][sublayer5].value == id_ob_selected)
				{	
					if(document.${formName}[check_ob][sublayer5].checked == true)
					{
						setSemuaPilihan(id_ob,id_hta,status_tb,"true",id_ob_selected);	
					}
					else
					{
						setSemuaPilihan(id_ob,id_hta,status_tb,"false",id_ob_selected);			
					}
				}						
		}	
	}
	else
	{
		if(document.${formName}[check_ob].value == id_ob_selected)
		{	
			if(document.${formName}[check_ob].checked == true)
			{
				setSemuaPilihan(id_ob,id_hta,status_tb,"true",id_ob_selected);		
			}
			else
			{
				setSemuaPilihan(id_ob,id_hta,status_tb,"false",id_ob_selected);
			}
		}	
	}
						

}


}



function setSemuaPilihan(id_ob,id_hta,status_tb,check,id_ob_selected)
{

if(testIsValidObject(ids) == true)
{
	if (document.${formName}.ids.length > 1)
	{
		for (setsemua = 0; setsemua < document.${formName}.ids.length; setsemua++)
		{
				var main_ob = "main_ob" + document.${formName}.ids[setsemua].value;
				var main_ob_objek = document.${formName}[main_ob];
				if(testIsValidObject(main_ob_objek) == true)
				{ 
					if (document.${formName}[main_ob].length > 1)
					{
						for (setsemua1 = 0; setsemua1 < document.${formName}[main_ob].length; setsemua1++)
						{
							var check_ob = "check_ob"+document.${formName}.ids[setsemua].value+document.${formName}[main_ob][setsemua1].value;
							var check_ob_objek = document.${formName}[check_ob];
							if(testIsValidObject(check_ob_objek) == true)
							{
								if (document.${formName}[check_ob].length > 1)
								{
									for (setsemua2 = 0; setsemua2 < document.${formName}[check_ob].length; setsemua2++)
									{
											if(status_tb == "Y")
											{
												if(document.${formName}[main_ob][setsemua1].value == id_ob && document.${formName}.ids[setsemua].value == id_hta)
												{											
													if(check=="true")
													{
													var tr_display = "tr_display" + document.${formName}.ids[setsemua].value + document.${formName}[main_ob][setsemua1].value;
													var display_ob = "display_ob" + document.${formName}.ids[setsemua].value; 											
														var tr_display_objek = document.getElementById(tr_display);
														var display_ob_objek = document.getElementById(display_ob);
														if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
														{											
															document.getElementById(tr_display).style.display="";
															document.getElementById(display_ob).style.display="";
															document.${formName}.ids[setsemua].checked = true;
															document.${formName}[main_ob][setsemua1].checked = true;
															document.${formName}[check_ob][setsemua2].checked = true;
														}	
													}
													else
													{
													document.${formName}[check_ob][setsemua2].checked = false;
													}								
												}
											}
											else
											{
												if(document.${formName}[main_ob][setsemua1].value == id_ob && document.${formName}.ids[setsemua].value == id_hta && document.${formName}[check_ob][setsemua2].value == id_ob_selected)
												{
													if(check=="true")
													{
															var tr_display = "tr_display" + document.${formName}.ids[setsemua].value + document.${formName}[main_ob][setsemua1].value;
															var display_ob = "display_ob" + document.${formName}.ids[setsemua].value; 
															var tr_display_objek = document.getElementById(tr_display);
															var display_ob_objek = document.getElementById(display_ob);
															if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
															{
																document.getElementById(tr_display).style.display="";
																document.getElementById(display_ob).style.display="";
																document.${formName}.ids[setsemua].checked = true;
																document.${formName}[main_ob][setsemua1].checked = true;											
																document.${formName}[check_ob][setsemua2].checked = true;
															}	
													}
													else
													{
													document.${formName}[check_ob][setsemua2].checked = false;
													}
													
												}
											}						
									}	
								}
								else
								{
											if(status_tb == "Y")
											{
												if(document.${formName}[main_ob][setsemua1].value == id_ob && document.${formName}.ids[setsemua].value == id_hta)
												{
													
													if(check=="true")
													{
													var tr_display = "tr_display" + document.${formName}.ids[setsemua].value + document.${formName}[main_ob][setsemua1].value;
													var display_ob = "display_ob" + document.${formName}.ids[setsemua].value; 
														var tr_display_objek = document.getElementById(tr_display);
														var display_ob_objek = document.getElementById(display_ob);
														if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
														{
															document.getElementById(tr_display).style.display="";
															document.getElementById(display_ob).style.display="";
															document.${formName}.ids[setsemua].checked = true;
															document.${formName}[main_ob][setsemua1].checked = true;											
															document.${formName}[check_ob].checked = true;
														}	
													}
													else
													{
													document.${formName}[check_ob].checked = false;
													}											
													
												}
											}
											else
											{
												if(document.${formName}[main_ob][setsemua1].value == id_ob && document.${formName}.ids[setsemua].value == id_hta && document.${formName}[check_ob].value == id_ob_selected)
												{
													if(check=="true")
													{
													var tr_display = "tr_display" + document.${formName}.ids[setsemua].value + document.${formName}[main_ob][setsemua1].value;
													var display_ob = "display_ob" + document.${formName}.ids[setsemua].value; 
														var tr_display_objek = document.getElementById(tr_display);
														var display_ob_objek = document.getElementById(display_ob);
														if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
														{
															document.getElementById(tr_display).style.display="";
															document.getElementById(display_ob).style.display="";
															document.${formName}.ids[setsemua].checked = true;
															document.${formName}[main_ob][setsemua1].checked = true;											
															document.${formName}[check_ob].checked = true;
														}	
													}
													else
													{
													document.${formName}[check_ob].checked = false;
													}
													
												}
											}
								}	
							}														
						}
					}
					else
					{
							var check_ob = "check_ob"+document.${formName}.ids[setsemua].value+document.${formName}[main_ob].value;
							var check_ob_objek = document.${formName}[check_ob];
							if(testIsValidObject(check_ob_objek) == true)
							{
								if (document.${formName}[check_ob].length > 1)
								{
									for (setsemua3 = 0; setsemua3 < document.${formName}[check_ob].length; setsemua3++)
									{
											if(status_tb == "Y")
											{
												if(document.${formName}[main_ob].value == id_ob && document.${formName}.ids[setsemua].value == id_hta)
												{
													
													if(check=="true")
													{
														var tr_display = "tr_display" + document.${formName}.ids[setsemua].value + document.${formName}[main_ob].value;
														var display_ob = "display_ob" + document.${formName}.ids[setsemua].value; 
														var tr_display_objek = document.getElementById(tr_display);
														var display_ob_objek = document.getElementById(display_ob);
														if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
														{
															document.getElementById(tr_display).style.display="";
															document.getElementById(display_ob).style.display="";
															document.${formName}.ids[setsemua].checked = true;
															document.${formName}[main_ob].checked = true;											
															document.${formName}[check_ob][setsemua3].checked = true;
														}	
													}
													else
													{
													document.${formName}[check_ob][setsemua3].checked = false;
													}											
													
												}
											}
											else
											{
												if(document.${formName}[main_ob].value == id_ob && document.${formName}.ids[setsemua].value == id_hta && document.${formName}[check_ob][setsemua2].value == id_ob_selected)
												{
													if(check=="true")
													{
														var tr_display = "tr_display" + document.${formName}.ids[setsemua].value + document.${formName}[main_ob].value;
														var display_ob = "display_ob" + document.${formName}.ids[setsemua].value; 
														var tr_display_objek = document.getElementById(tr_display);
														var display_ob_objek = document.getElementById(display_ob);
														if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
														{
															document.getElementById(tr_display).style.display="";
															document.getElementById(display_ob).style.display="";
															document.${formName}.ids[setsemua].checked = true;
															document.${formName}[main_ob].checked = true;
															document.${formName}[check_ob][setsemua3].checked = true;
														}	
													}
													else
													{
													document.${formName}[check_ob][setsemua3].checked = false;
													}
													
												}
											}									
									}	
								}
								else
								{
											if(status_tb == "Y")
											{
												if(document.${formName}[main_ob].value == id_ob && document.${formName}.ids[setsemua].value == id_hta)
												{
													
													if(check=="true")
													{
														var tr_display = "tr_display" + document.${formName}.ids[setsemua].value + document.${formName}[main_ob].value;
														var display_ob = "display_ob" + document.${formName}.ids[setsemua].value;
														var tr_display_objek = document.getElementById(tr_display);
														var display_ob_objek = document.getElementById(display_ob);
														if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
														{ 
															document.getElementById(tr_display).style.display="";
															document.getElementById(display_ob).style.display="";
															document.${formName}.ids[setsemua].checked = true;
															document.${formName}[main_ob].checked = true;												
															document.${formName}[check_ob].checked = true;
														}	
													}
													else
													{
													document.${formName}[check_ob].checked = false;
													}											
													
												}
											}
											else
											{
												if(document.${formName}[main_ob].value == id_ob && document.${formName}.ids[setsemua].value == id_hta && document.${formName}[check_ob].value == id_ob_selected)
												{
													if(check=="true")
													{
														var tr_display = "tr_display" + document.${formName}.ids[setsemua].value + document.${formName}[main_ob].value;
														var display_ob = "display_ob" + document.${formName}.ids[setsemua].value;
														var tr_display_objek = document.getElementById(tr_display);
														var display_ob_objek = document.getElementById(display_ob);
														if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
														{ 
															document.getElementById(tr_display).style.display="";
															document.getElementById(display_ob).style.display="";
															document.${formName}.ids[setsemua].checked = true;
															document.${formName}[main_ob].checked = true;
															document.${formName}[check_ob].checked = true;
														}	
													}
													else
													{
													document.${formName}[check_ob].checked = false;
													}
													
												}
											}	
								}
							}	
					}
				}									
		}	
	}
	else
	{
				var main_ob = "main_ob" + document.${formName}.ids.value; 
				var main_ob_objek = document.${formName}[main_ob];
				if(testIsValidObject(main_ob_objek) == true)
				{
					if (document.${formName}[main_ob].length > 1)
					{
						for (setsemua1 = 0; setsemua1 < document.${formName}[main_ob].length; setsemua1++)
						{
								var check_ob = "check_ob"+document.${formName}.ids.value+document.${formName}[main_ob][setsemua1].value;
								var check_ob_objek = document.${formName}[check_ob];
								if(testIsValidObject(check_ob_objek) == true)
								{
									if (document.${formName}[check_ob].length > 1)
									{
										for (setsemua5 = 0; setsemua5 < document.${formName}[check_ob].length; setsemua5++)
										{
												if(status_tb == "Y")
												{
													if(document.${formName}[main_ob][setsemua1].value == id_ob && document.${formName}.ids.value == id_hta)
													{
														
														if(check=="true")
														{
															var tr_display = "tr_display" + document.${formName}.ids.value + document.${formName}[main_ob][setsemua1].value;
															var display_ob = "display_ob" + document.${formName}.ids.value; 
															var tr_display_objek = document.getElementById(tr_display);
															var display_ob_objek = document.getElementById(display_ob);
															if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
															{
																document.getElementById(tr_display).style.display="";
																document.getElementById(display_ob).style.display="";
																document.${formName}.ids.checked = true;
																document.${formName}[main_ob][setsemua1].checked = true;
																document.${formName}[check_ob][setsemua5].checked = true;
															}	
														}
														else
														{
														document.${formName}[check_ob][setsemua5].checked = false;
														}											
														
													}
												}
												else
												{
													if(document.${formName}[main_ob][setsemua1].value == id_ob  && document.${formName}.ids.value == id_hta && document.${formName}[check_ob][setsemua5].value == id_ob_selected)
													{
														if(check=="true")
														{
															var tr_display = "tr_display" + document.${formName}.ids.value + document.${formName}[main_ob][setsemua1].value;
															var display_ob = "display_ob" + document.${formName}.ids.value; 
															var tr_display_objek = document.getElementById(tr_display);
															var display_ob_objek = document.getElementById(display_ob);
															if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
															{
																document.getElementById(tr_display).style.display="";
																document.getElementById(display_ob).style.display="";
																document.${formName}.ids.checked = true;
																document.${formName}[main_ob][setsemua1].checked = true;
																document.${formName}[check_ob][setsemua5].checked = true;
															}	
														}
														else
														{
														document.${formName}[check_ob][setsemua5].checked = false;
														}
														
													}
												}																
										}	
									}
									else
									{
												if(status_tb == "Y")
												{
													if(document.${formName}[main_ob][setsemua1].value == id_ob && document.${formName}.ids.value == id_hta)
													{
														
														if(check=="true")
														{
															var tr_display = "tr_display" + document.${formName}.ids.value + document.${formName}[main_ob][setsemua1].value;
															var display_ob = "display_ob" + document.${formName}.ids.value;
															var tr_display_objek = document.getElementById(tr_display);
															var display_ob_objek = document.getElementById(display_ob);
															if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
															{ 
																document.getElementById(tr_display).style.display="";
																document.getElementById(display_ob).style.display="";
																document.${formName}.ids.checked = true;
																document.${formName}[main_ob][setsemua1].checked = true;
																document.${formName}[check_ob].checked = true;
															}	
														}
														else
														{
														document.${formName}[check_ob].checked = false;
														}											
														
													}
												}
												else
												{
													if(document.${formName}[main_ob][setsemua1].value == id_ob && document.${formName}.ids.value == id_hta && document.${formName}[check_ob].value == id_ob_selected)
													{
														if(check=="true")
														{
															var tr_display = "tr_display" + document.${formName}.ids.value + document.${formName}[main_ob][setsemua1].value;
															var display_ob = "display_ob" + document.${formName}.ids.value; 
															var tr_display_objek = document.getElementById(tr_display);
															var display_ob_objek = document.getElementById(display_ob);
															if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
															{
																document.getElementById(tr_display).style.display="";
																document.getElementById(display_ob).style.display="";
																document.${formName}.ids.checked = true;
																document.${formName}[main_ob][setsemua1].checked = true;												
																document.${formName}[check_ob].checked = true;
															}	
														}
														else
														{
														document.${formName}[check_ob].checked = false;
														}
														
													}
												}		
									}
								}										
						}
					}
					else
					{
								var check_ob = "check_ob"+document.${formName}.ids.value+document.${formName}[main_ob].value;
								var check_ob_objek = document.${formName}[check_ob];
								if(testIsValidObject(check_ob_objek) == true)
								{
									if (document.${formName}[check_ob].length > 1)
									{
										for (setsemua9 = 0; setsemua9 < document.${formName}[check_ob].length; setsemua9++)
										{
												if(status_tb == "Y")
												{
													if(document.${formName}[main_ob].value == id_ob && document.${formName}.ids.value == id_hta)
													{
														
														if(check=="true")
														{
															var tr_display = "tr_display" + document.${formName}.ids.value + document.${formName}[main_ob].value;
															var display_ob = "display_ob" + document.${formName}.ids.value;
															var tr_display_objek = document.getElementById(tr_display);
															var display_ob_objek = document.getElementById(display_ob);
															if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
															{ 
																document.getElementById(tr_display).style.display="";
																document.getElementById(display_ob).style.display="";
																document.${formName}.ids.checked = true;
																document.${formName}[main_ob].checked = true;
																document.${formName}[check_ob][setsemua9].checked = true;
															}	
														}
														else
														{
														document.${formName}[check_ob][setsemua9].checked = false;
														}											
														
													}
												}
												else
												{
													if(document.${formName}[main_ob].value == id_ob && document.${formName}.ids.value == id_hta && document.${formName}[check_ob][setsemua9].value == id_ob_selected)
													{
														if(check=="true")
														{
															var tr_display = "tr_display" + document.${formName}.ids.value + document.${formName}[main_ob].value;
															var display_ob = "display_ob" + document.${formName}.ids.value; 
															var tr_display_objek = document.getElementById(tr_display);
															var display_ob_objek = document.getElementById(display_ob);
															if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
															{
																document.getElementById(tr_display).style.display="";
																document.getElementById(display_ob).style.display="";
																document.${formName}.ids.checked = true;
																document.${formName}[main_ob].checked = true;
																document.${formName}[check_ob][setsemua9].checked = true;
															}	
														}
														else
														{
														document.${formName}[check_ob][setsemua9].checked = false;
														}
														
													}
												}							
										}	
									}
									else
									{
													if(status_tb == "Y")
													{
														if(document.${formName}[main_ob].value == id_ob && document.${formName}.ids.value == id_hta)
														{
															
															if(check=="true")
															{
																var tr_display = "tr_display" + document.${formName}.ids.value + document.${formName}[main_ob].value;
																var display_ob = "display_ob" + document.${formName}.ids.value; 
																var tr_display_objek = document.getElementById(tr_display);
																var display_ob_objek = document.getElementById(display_ob);
																if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
																{
																	document.getElementById(tr_display).style.display="";
																	document.getElementById(display_ob).style.display="";
																	document.${formName}.ids.checked = true;
																	document.${formName}[main_ob].checked = true;
																	document.${formName}[check_ob].checked = true;
																}	
															}
															else
															{
															document.${formName}[check_ob].checked = false;
															}											
															
														}
													}
													else
													{
														if(document.${formName}[main_ob].value == id_ob && document.${formName}.ids.value == id_hta && document.${formName}[check_ob].value == id_ob_selected)
														{
															if(check=="true")
															{
																var tr_display = "tr_display" + document.${formName}.ids.value + document.${formName}[main_ob].value;
																var display_ob = "display_ob" + document.${formName}.ids.value; 
																var tr_display_objek = document.getElementById(tr_display);
																var display_ob_objek = document.getElementById(display_ob);
																if(testIsValidObject(tr_display_objek) == true && testIsValidObject(display_ob_objek) == true)
																{
																	document.getElementById(tr_display).style.display="";
																	document.getElementById(display_ob).style.display="";
																	document.${formName}.ids.checked = true;
																	document.${formName}[main_ob].checked = true;													
																	document.${formName}[check_ob].checked = true;
																}	
															}
															else
															{
															document.${formName}[check_ob].checked = false;
															}
															
														}
													}
									}	
								}				
					}
				}		
	}
						

}
//setPilihanHarta();
}



function setAllharta()
{
	alert("xx");
	
	var tot = 0;
	var ids = document.${formName}.ids;
	
	
		if (document.${formName}.ids.length > 1)
		{
			for (mainlayerxxx = 0; mainlayerxxx < document.${formName}.ids.length; mainlayerxxx++)
			{
				if(document.${formName}.ids[mainlayerxxx].checked == false)
				{
				tot++;
				}
			}
			
		}
		else
		{
			if(document.${formName}.ids.checked == false)
			{
				tot++;
			}
		}
			
		if(tot>0)
		{
		document.${formName}.all.checked = false;
		}
		else
		{
		document.${formName}.all.checked = true;
		}	

}



</script>