<!-- $ID_PANDANGANUNDANGUTAMA 
::::::: $FLAG_SUB_OPENCLOSE
::::::::: $LAYER
-->

#if($LAYER=="1")
<div id="div_statsUtama$ID_PANDANGANUNDANGUTAMA" style="display:none" >	
<a href="javascript:doDivAjaxCall$formname('divSubFolderTambahFirst$ID_PANDANGANUNDANGUTAMA','addSubDir','ID_REFER=&LAYER=$LAYER&ID_PANDANGANUNDANGUTAMA=$ID_PANDANGANUNDANGUTAMA');">
<img title="Tambah Direktori" src="../img/plus.gif" border="0"></a>	   		   
[<span id="div_totalDirUtama$ID_PANDANGANUNDANGUTAMA" >0</span> Rujukan Dibuka] [<span id="div_totalLampiranUtama$ID_PANDANGANUNDANGUTAMA" >0</span> Dokumen Dibuka]
</div>
<div id="divSubFolderTambahFirst$ID_PANDANGANUNDANGUTAMA" >
</div>
#end



#if($listFolderSub.size()>0)
<script>
/*
	if('$LAYER'!="1")
	{
		if('$FLAG_SUB_OPENCLOSE'=="OPEN" && '$ID_PANDANGANUNDANG'!="")
		{
			document.getElementById('iconFolderOpenClose_$ID_PANDANGANUNDANG').innerHTML = "<b><</b>";
		}
		else if('$FLAG_SUB_OPENCLOSE'=="CLOSE" && '$ID_PANDANGANUNDANG'!="")
		{
			document.getElementById('iconFolderOpenClose_$ID_PANDANGANUNDANG').innerHTML = "<b>></b>";
		}
		else
		{
			document.getElementById('iconFolderOpenClose_$ID_PANDANGANUNDANG').innerHTML = "";
		}
	}
	*/
</script>
#else
<script>
/*
	if('$LAYER'!="1")
	{		
		document.getElementById('iconFolderOpenClose_$ID_PANDANGANUNDANG').innerHTML = "";		
	}
	*/
</script>
#end



<script>	
document.getElementById('HID_OPENCLOSE_$ID_PANDANGANUNDANGUTAMA').value = '$FLAG_OPENCLOSE';

if('$LAYER' != "1")
{
	document.getElementById('divSubFolderTambah$ID_PANDANGANUNDANG').innerHTML='';
	
}
/*
else
{
	
}
*/


if('$ID_PANDANGANUNDANG'!="" && '$AUTOLOAD'=="N")
{
	//alert('x1 : '+'$ID_PANDANGANUNDANG');
	document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_PANDANGANUNDANG').value = '$FLAG_SUB_OPENCLOSE';
	//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_PANDANGANUNDANG').value);
}

</script>



<table width="98%" align="center" class="classFade" border="0" cellspacing="0" cellpadding="0">
#if($listFolderSub.size()>0)	
	#foreach($LFS in $listFolderSub)
	<tr>	
	<td >
	
	    <input type="hidden" id="tempFieldFolder$LFS.ID_PANDANGANUNDANGUTAMA" name="tempFieldFolder$LFS.ID_PANDANGANUNDANGUTAMA">
		<fieldset>
		
			   
		
		<script>
		/*
		tempFieldFolder$LFS.ID_PANDANGANUNDANGUTAMA
		document.getElementById('div_statsUtama$LFS.ID_PANDANGANUNDANGUTAMA').style.display = "";
		var current_count_load = document.getElementById('countFolder_$LFS.ID_PANDANGANUNDANGUTAMA').value;
		current_count_load = parseInt(current_count_load)+1;
		document.getElementById('countFolder_$LFS.ID_PANDANGANUNDANGUTAMA').value = current_count_load;	
		document.getElementById('div_totalDirUtama$LFS.ID_PANDANGANUNDANGUTAMA').innerHTML = current_count_load;
		*/
		//div_totalLampiranUtama$LFU.ID_PANDANGANUNDANGUTAMA		
		</script>
	
		
		#set($span1 = "")
		#set($spaneditsub = "")
		#set($divLoadSubAfterDelete = "")
		
		#if($LFS.LAYER=="1")
			#set($span1 = "span1listFolderSub_"+$LAYER+"_"+$LFS.ID_PANDANGANUNDANGUTAMA+"_"+$LFS.BIL)
			#set($spaneditsub = "spaneditsub_"+$LAYER+"_"+$LFS.ID_PANDANGANUNDANGUTAMA+"_"+$LFS.BIL)
			#set($divLoadSubAfterDelete = "div_viewFolderUtama"+$ID_PANDANGANUNDANGUTAMA)
		#else
			#set($span1 = "span1listFolderSub_"+$LFS.ID_PANDANGANUNDANG+"_"+$LFS.BIL)
			#set($spaneditsub = "spaneditsub_"+$LFS.ID_PANDANGANUNDANG+"_"+$LFS.BIL)
			#set($divLoadSubAfterDelete = "divSubFolder"+$ID_PANDANGANUNDANG)
		#end	
		
		<legend id="$spaneditsub" >			
		<span class="font_tajuk_sub_wc" >
		<a href="javascript:doDivAjaxCall$formname('$span1','editSubDir','ID_PANDANGANUNDANG=$LFS.ID_PANDANGANUNDANG&LAYER=$LFS.LAYER&BIL=$LFS.BIL');"><img title="Kemaskini Nama Sub Direktori" src="../img/edit.gif" border="0"></a>
	    <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('$divLoadSubAfterDelete','deleteSubDir','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$ID_PANDANGANUNDANG').val()+'&TAJUK=$TAJUK&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$ID_PANDANGANUNDANGUTAMA&ID_REFER=$ID_PANDANGANUNDANG&ID_TO_DELETE=$LFS.ID_PANDANGANUNDANG&ID_PANDANGANUNDANG=$ID_PANDANGANUNDANG&LAYER=$LFS.LAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val()+'&JUMLAH_SUB=$LFS.JUMLAH_SUB');}"><img title="Hapus Direktori"  src="../img/hapus.gif" border="0"></a>
	    </span> 					
	    <span class="font_tajuk_sub_wc" id="$span1">
	    $LFS.TAJUK
	    </span>
	    </legend>
	    
	    
		<!--   (REF : $ID_PANDANGANUNDANG) -->
		#set($SEND_NUMBERING="")
		#if($LFS.LAYER == "1")
		#set($SEND_NUMBERING="_"+$LFS.BIL)
		#else
		#set($SEND_NUMBERING=$NUMBERING+"_"+$LFS.BIL)			
		#end
		<!--  
		SEND NUMBERING ::::  $SEND_NUMBERING
		-->
		<input type="hidden" id="$ID_PANDANGANUNDANGUTAMA$SEND_NUMBERING" name="$ID_PANDANGANUNDANGUTAMA$SEND_NUMBERING" value="$LFS.ID_PANDANGANUNDANG">	
		<input type="hidden" id="check_$ID_PANDANGANUNDANGUTAMA$SEND_NUMBERING" name="check_$ID_PANDANGANUNDANGUTAMA$SEND_NUMBERING" value="CLOSE">	
		<input type="hidden" id="checkID_REFER_$ID_PANDANGANUNDANGUTAMA$SEND_NUMBERING" name="checkID_REFER_$ID_PANDANGANUNDANGUTAMA$SEND_NUMBERING" value="$LFS.ID_REFER">	
		<input type="hidden" id="checkNextLayer_$ID_PANDANGANUNDANGUTAMA$SEND_NUMBERING" name="checkNextLayer_$ID_PANDANGANUNDANGUTAMA$SEND_NUMBERING" value="$LFS.NEXTLAYER">	
		<input type="hidden" id="checkNumbering_$ID_PANDANGANUNDANGUTAMA$SEND_NUMBERING" name="checkNumbering_$ID_PANDANGANUNDANGUTAMA$SEND_NUMBERING" value="$SEND_NUMBERING">	
		
		
		
		 
		
		<input type="hidden" id="HID_OPENCLOSE_SUB_$LFS.ID_PANDANGANUNDANG" name="HID_OPENCLOSE_SUB_$LFS.ID_PANDANGANUNDANG" value = "CLOSE"  >
			   
		
		
		<script>
		//off kejap
		
		if(('$AUTOLOAD'=="Y" || '$FlagCari'=="Y"))
		{
			$jquery(document).ready(function () {
					  doDivAjaxCall$formname('divSubLampiran$LFS.ID_PANDANGANUNDANG','showLampiran','FLAG_LAMP_OPENCLOSE='+$jquery('#HID_OPENCLOSE_LAMP_$LFS.ID_PANDANGANUNDANG').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$LFS.ID_PANDANGANUNDANGUTAMA&ID_REFER=$LFS.ID_REFER&ID_PANDANGANUNDANG=$LFS.ID_PANDANGANUNDANG&LAYER=$LFS.LAYER&AUTOLOAD=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val());
			});
		}
		
		</script>
		
		
		
		
		<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>
		<input type="hidden" id="HID_OPENCLOSE_LAMP_$LFS.ID_PANDANGANUNDANG" name="HID_OPENCLOSE_LAMP_$LFS.ID_PANDANGANUNDANG" value = "CLOSE"  >
		&nbsp;
		
		<a href="javascript:doDivAjaxCall$formname('divSubLampiranTambah$LFS.ID_PANDANGANUNDANG','addLampiran','ID_PANDANGANUNDANG=$LFS.ID_PANDANGANUNDANG&ID_PANDANGANUNDANGUTAMA=$LFS.ID_PANDANGANUNDANGUTAMA');">
		<img title="Tambah Dokumen" src="../img/plus.gif" border="0"></a>
		
		<span id="countLampiran$LFS.ID_PANDANGANUNDANG">	
		#if($LFS.JUMLAH_LAMP != "0" && $LFS.JUMLAH_LAMP != "")
		<span onClick="doDivAjaxCall$formname('divSubLampiran$LFS.ID_PANDANGANUNDANG','showLampiran','FLAG_LAMP_OPENCLOSE='+$jquery('#HID_OPENCLOSE_LAMP_$LFS.ID_PANDANGANUNDANG').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$LFS.ID_PANDANGANUNDANGUTAMA&ID_REFER=$LFS.ID_REFER&ID_PANDANGANUNDANG=$LFS.ID_PANDANGANUNDANG&LAYER=$LFS.LAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val()+'&JUMLAH_LAMP=$LFS.JUMLAH_LAMP');" >
			<span class="font_tajuk_sub" >
			&nbsp;<!--<span id="iconLampiranOpenClose_$LFS.ID_PANDANGANUNDANG"><b>></b></span>--><u>[$LFS.JUMLAH_LAMP Dokumen]</u>
		    </span>
		</span>
		#else				
			<span class="font_tajuk_sub_wc" >
			&nbsp;<!--<span id="iconLampiranOpenClose_$LFS.ID_PANDANGANUNDANG"></span>-->[$LFS.JUMLAH_LAMP Dokumen]
		    </span>		   
		#end
		</span>
		<div id="divSubLampiranTambah$LFS.ID_PANDANGANUNDANG" >		
		</div>		
		<div id="divSubLampiran$LFS.ID_PANDANGANUNDANG" >
		</div>	
		
		#set($setStyleCountFolder = "")
		#if($LAYER != "9")
		&nbsp;
		<a href="javascript:doDivAjaxCall$formname('divSubFolderTambah$LFS.ID_PANDANGANUNDANG','addSubDir','ID_REFER=$LFS.ID_PANDANGANUNDANG&LAYER=$LFS.NEXTLAYER&ID_PANDANGANUNDANGUTAMA=$LFS.ID_PANDANGANUNDANGUTAMA');">
		<img title="Tambah Direktori" src="../img/plus.gif" border="0"></a>		
		#else
		#set($setStyleCountFolder = "style='display:none'")
		#end
		
		<span id="countSubFolder$LFS.ID_PANDANGANUNDANG" $setStyleCountFolder >			
		<!--  
		(
		:::: $SEND_NUMBERING
		<span onClick="doDivAjaxCall$formname('divSubFolder$LFS.ID_PANDANGANUNDANG','showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$LFS.ID_PANDANGANUNDANG').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$LFS.ID_PANDANGANUNDANGUTAMA&ID_REFER=$LFS.ID_PANDANGANUNDANG&ID_PANDANGANUNDANG=$LFS.ID_PANDANGANUNDANG&LAYER=$LFS.NEXTLAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val()+'&JUMLAH_SUB=$LFS.JUMLAH_SUB&SEND_NUMBERING=$SEND_NUMBERING');" >
			<span class="font_tajuk_sub" >
			&nbsp;<u>[$LFS.JUMLAH_SUB Sub Dir]</u>
		    </span>
		</span>
		)
		-->	
		
		
		
		#if($LFS.JUMLAH_SUB != "0" && $LFS.JUMLAH_SUB != "")
		
		
		<span onClick="doDivAjaxCall$formname('divSubFolder$LFS.ID_PANDANGANUNDANG','showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$LFS.ID_PANDANGANUNDANG').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$LFS.ID_PANDANGANUNDANGUTAMA&ID_REFER=$LFS.ID_PANDANGANUNDANG&ID_PANDANGANUNDANG=$LFS.ID_PANDANGANUNDANG&LAYER=$LFS.NEXTLAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val()+'&JUMLAH_SUB=$LFS.JUMLAH_SUB&SEND_NUMBERING=$SEND_NUMBERING');" >
			<span class="font_tajuk_sub" >
			&nbsp;<!--<span id="iconFolderOpenClose_$LFS.ID_PANDANGANUNDANG"><b>></b></span>--><u>[$LFS.JUMLAH_SUB Sub Dir]</u>
		    </span>
		</span>
		#else		
			<span class="font_tajuk_sub_wc" >
			&nbsp;<!--<span id="iconFolderOpenClose_$LFS.ID_PANDANGANUNDANG"></span>-->[$LFS.JUMLAH_SUB Sub Dir]
		    </span>		   
		#end		
		</span>
		
		
		
		<div id="divSubFolderTambah$LFS.ID_PANDANGANUNDANG" >
		
		</div>
		<div id="divSubFolder$LFS.ID_PANDANGANUNDANG" >
		
		</div>
		</td>
		</tr>
		</table>
		
		
		
		
		</fieldset>
		#if(($AUTOLOAD=="Y" || $FlagCari=="Y"))		
			#if($LFS.LAYER=="1")
			<script>
			highlight_item($jquery('#carianTerperinci').val(),'span1listFolderSub_'+'$LAYER'+'_'+'$LFS.ID_PANDANGANUNDANGUTAMA'+'_'+'$LFS.BIL'); 
			</script>
			#else
			<script>
			highlight_item($jquery('#carianTerperinci').val(),'span1listFolderSub'+'_'+'$LFS.ID_PANDANGANUNDANG'+'_'+'$LFS.BIL'); 
			</script>
			#end
		#end
		<script>
		//off kejap
		/*
		if(('$AUTOLOAD'=="Y" || '$FlagCari'=="Y"))
		{
			
			if('$LFS.ID_REFER'!="")
			{
				//alert('x1 : '+'$ID_PANDANGANUNDANG');
				document.getElementById('HID_OPENCLOSE_SUB_'+'$LFS.ID_REFER').value = '$FLAG_SUB_OPENCLOSE';
				//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_PANDANGANUNDANG').value);
			}
			
			$jquery(document).ready(function () {
				  //doDivAjaxCall$formname('divSubLampiran$LFS.ID_PANDANGANUNDANG','showLampiran','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$LFS.ID_PANDANGANUNDANG').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$LFS.ID_PANDANGANUNDANGUTAMA&ID_REFER=$LFS.ID_REFER&ID_PANDANGANUNDANG=$LFS.ID_PANDANGANUNDANG&LAYER=$LFS.NEXTLAYER&AUTOLOAD=Y');
				  doDivAjaxCall$formname('divSubFolder$LFS.ID_PANDANGANUNDANG','showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$LFS.ID_PANDANGANUNDANG').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$LFS.ID_PANDANGANUNDANGUTAMA&ID_REFER=$LFS.ID_PANDANGANUNDANG&ID_PANDANGANUNDANG=$LFS.ID_PANDANGANUNDANG&LAYER=$LFS.NEXTLAYER&AUTOLOAD=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val());
		   	});
		}
		*/
		</script>
		
		
	</td>
	</tr>
	#end
#end
</table>
<br>

<!--  
NUMBERING :::::::: $NUMBERING

AUTOLOAD :::::::: $AUTOLOAD
-->

#if(($AUTOLOAD=="Y" || $FlagCari=="Y"))
<script>
//logic untuk ajax autoload after search, ajaxcall satu per satu mengikut turutan folder.. mcm nak gila

var check_ipu = '$ID_PANDANGANUNDANGUTAMA';
var actual_ipu = "";
var send_ID_PANDANGANUNDANG = "";
if('$NUMBERING'=="")
{
	//alert("x");
	actual_ipu = check_ipu+'_1';
	if(checkUFField(actual_ipu)==true)
	{
		//proceed
		send_ID_PANDANGANUNDANG = actual_ipu;
	}
}
else
{
	actual_ipu = check_ipu+'$NUMBERING'+'_'+1;
	if(checkUFField(actual_ipu)==true)
	{
		//proceed
		send_ID_PANDANGANUNDANG = actual_ipu;
	}
	else
	{
		//alert(" check sebelum loop ");
		actual_ipu = check_ipu+'$NUMBERING';
		/*
		if(checkUFField(actual_ipu)==true)
		{
			send_ID_PANDANGANUNDANG = actual_ipu;
		}
		else
		*/	
		{
		
			//alert(" get 1st **** "+actual_ipu);	
			var last = actual_ipu.split("_").pop();
			//alert(" get 1st last :"+last);
			actual_ipu = actual_ipu.substring(0, actual_ipu.lastIndexOf('_')+ 1);
			//alert(" new actual_ipu before add new number :"+actual_ipu);
			actual_ipu = actual_ipu+(parseInt(last)+1);
			//alert(" new actual ** :"+actual_ipu);
			if(checkUFField(actual_ipu)==true)
			{
				//proceed
				send_ID_PANDANGANUNDANG = actual_ipu;
			}
			else
			{
				actual_ipu = check_ipu+'$NUMBERING';
				var total_us = (actual_ipu.match(new RegExp("_", "g")) || []).length;
				//alert(" total_us :"+total_us);
				for (i = 1; i <= total_us; i++) {
					send_ID_PANDANGANUNDANG = "";
					//alert(i+" : dalam loop mula **** "+actual_ipu);	
					var last = actual_ipu.split("_").pop();
					//alert(" get last **** "+last);
					actual_ipu = actual_ipu.substring(0, actual_ipu.lastIndexOf('_'));
					//alert(" lepas buang **** "+actual_ipu);				
					actual_ipu = actual_ipu+"_"+(parseInt(last)+1);
					//alert(" last actual_ipu loop :"+actual_ipu);
					//alert("valid? : "+checkUFField(actual_ipu));
				    if(checkUFField(actual_ipu)==true)
					{
						//proceed
						send_ID_PANDANGANUNDANG = actual_ipu;
						//alert(" i : "+i+" total_us : "+total_us+" valid - send_ID_PANDANGANUNDANG : "+send_ID_PANDANGANUNDANG);
						//break;
						total_us = i;
					}			    
					else
					{
						actual_ipu = actual_ipu.substring(0, actual_ipu.lastIndexOf('_'));
					    //continue;
					}
				    /*
				    if(send_ID_PANDANGANUNDANG!="")
				    {
				    	//break;
				    	total_us = i;
				    }
				    actual_ipu = actual_ipu.substring(0, actual_ipu.lastIndexOf('_'));
				    */
				}			
			}
		}
		
	}
}
		//alert("***********Next Feild ID : "+send_ID_PANDANGANUNDANG);
//alert("xxx : "+'$ID_PANDANGANUNDANGUTAMA'+'__')
//alert('$AUTOLOAD'+'____'+'$FlagCari');
		var  send_ID_PANDANGANUNDAN_value  = $jquery('#'+send_ID_PANDANGANUNDANG).val();
		//alert("***********Next IDP value : "+send_ID_PANDANGANUNDAN_value);
		
		
		if(('$AUTOLOAD'=='Y' || '$FlagCari'=='Y'))
		{
			//alert("val : "+send_ID_PANDANGANUNDAN_value);
			$jquery(document).ready(function () {
					 //doDivAjaxCall$formname('divSubLampiran'+send_ID_PANDANGANUNDANG,'showLampiran','FLAG_LAMP_OPENCLOSE='+$jquery('#HID_OPENCLOSE_LAMP_'+send_ID_PANDANGANUNDANG).val()+'&TAJUK=&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$ID_PANDANGANUNDANGUTAMA&ID_REFER='+send_ID_PANDANGANUNDANG+'&ID_PANDANGANUNDANG='+send_ID_PANDANGANUNDANG+'&LAYER=$LAYER&AUTOLOAD=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val());
			});
			//checkID_REFER_
			//alert("LAYER :"+'$LAYER');
			
			var id_refer = $jquery('#checkID_REFER_'+send_ID_PANDANGANUNDANG).val();
			var next_layer = $jquery('#checkNextLayer_'+send_ID_PANDANGANUNDANG).val();
			var checkNumbering = $jquery('#checkNumbering_'+send_ID_PANDANGANUNDANG).val();
			
			//alert("id_refer : "+id_refer);
			//var next_layer = parseInt('$LAYER')+1;
			
			if(id_refer!="")
			{
				//alert('x1 : '+'$ID_PANDANGANUNDANG');
				document.getElementById('HID_OPENCLOSE_SUB_'+id_refer).value = '$FLAG_SUB_OPENCLOSE';
				//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_PANDANGANUNDANG').value);
			}
			//alert("bawah : "+send_ID_PANDANGANUNDANG);
			$jquery(document).ready(function () {
				  //doDivAjaxCall$formname('divSubFolder'+send_ID_PANDANGANUNDAN_value,'showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_'+send_ID_PANDANGANUNDAN_value).val()+'&TAJUK=&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$ID_PANDANGANUNDANGUTAMA&ID_REFER='+send_ID_PANDANGANUNDAN_value+'&ID_PANDANGANUNDANG='+send_ID_PANDANGANUNDAN_value+'&LAYER='+next_layer+'&AUTOLOAD=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val());
				  doDivAjaxCall$formname('divSubFolder'+send_ID_PANDANGANUNDAN_value,'showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_'+send_ID_PANDANGANUNDAN_value).val()+'&TAJUK=&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$ID_PANDANGANUNDANGUTAMA&ID_REFER='+send_ID_PANDANGANUNDAN_value+'&ID_PANDANGANUNDANG='+send_ID_PANDANGANUNDAN_value+'&LAYER='+next_layer+'&AUTOLOAD=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val()+'&JUMLAH_SUB=&SEND_NUMBERING='+checkNumbering);
			});
		}
		

</script>
#end

<script>

//var div = document.getElementById("tempFieldFolder$ID_PANDANGANUNDANGUTAMA");          
//'FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$LFS.ID_PANDANGANUNDANG').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA=$LFS.ID_PANDANGANUNDANGUTAMA&ID_REFER=$LFS.ID_PANDANGANUNDANG&ID_PANDANGANUNDANG=$LFS.ID_PANDANGANUNDANG&LAYER=$LFS.NEXTLAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val()+'&JUMLAH_SUB=$LFS.JUMLAH_SUB'
getTotalFolder("$ID_PANDANGANUNDANGUTAMA");
getTotalLampiran("$ID_PANDANGANUNDANGUTAMA");
document.getElementById("HID_MAXLAYER_$ID_PANDANGANUNDANGUTAMA").value = '$MAX_LAYER';



if('$LAYER'!="1" && '$AUTOLOAD'!="Y")
{
	$jquery(document).ready(function () {
		  doDivAjaxCall$formname('countSubFolder$ID_PANDANGANUNDANG','countSubFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$ID_PANDANGANUNDANG').val()+'&ID_PANDANGANUNDANGUTAMA=$ID_PANDANGANUNDANGUTAMA&ID_PANDANGANUNDANG=$ID_PANDANGANUNDANG&LAYER=$LAYER&AUTOLOAD=N&TOTAL_SUB=$listFolderSub.size()');
	});
}
		//alert("tempFieldFolder$ID_PANDANGANUNDANGUTAMA : "+div);
	//	alert(document.getElementById('tempFieldFolder$ID_PANDANGANUNDANGUTAMA').elements.length);
		/*
		document.getElementById('div_statsUtama$LFS.ID_PANDANGANUNDANGUTAMA').style.display = "";
		var current_count_load = document.getElementById('countFolder_$LFS.ID_PANDANGANUNDANGUTAMA').value;
		current_count_load = parseInt(current_count_load)+1;
		document.getElementById('countFolder_$LFS.ID_PANDANGANUNDANGUTAMA').value = current_count_load;	
		document.getElementById('div_totalDirUtama$LFS.ID_PANDANGANUNDANGUTAMA').innerHTML = current_count_load;
		*/
</script>

<script>
/*
var current_load_folder = document.getElementById('countFolder_$ID_PANDANGANUNDANGUTAMA').value;
var total_load_folder = document.getElementById('countActFolder_$ID_PANDANGANUNDANGUTAMA').value;
var current_load_lampiran = document.getElementById('countLampiran_$ID_PANDANGANUNDANGUTAMA').value;
var total_load_lampiran = document.getElementById('countActLampiran_$ID_PANDANGANUNDANGUTAMA').value;
if((current_load_folder == total_load_folder) && (current_load_lampiran==total_load_lampiran))
{
	
	if( $jquery('#'+'div_rowFolderUtama$ID_PANDANGANUNDANGUTAMA').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_rowFolderUtama$ID_PANDANGANUNDANGUTAMA').offset().top);
		document.getElementById('countFolder_$ID_PANDANGANUNDANGUTAMA').value = 0;
		document.getElementById('countLampiran_$ID_PANDANGANUNDANGUTAMA').value = 0;
	}
	
}
*/
</script>
