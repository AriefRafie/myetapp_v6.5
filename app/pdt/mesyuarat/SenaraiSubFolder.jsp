
<!-- $ID_MESYUARATUTAMA 
::::::: $FLAG_SUB_OPENCLOSE
::::::::: $LAYER
-->

#if($LAYER=="1")
<div id="div_statsUtama$ID_MESYUARATUTAMA"  >	
<a href="javascript:doDivAjaxCall$formname('divSubFolderTambahFirst$ID_MESYUARATUTAMA','addSubDir','ID_REFER=&LAYER=$LAYER&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA');">
<img title="Tambah Direktori" src="../img/plus.gif" border="0"></a>	   		   
[<span id="div_totalDirUtama$ID_MESYUARATUTAMA" >0</span> Item Mesyuarat Dibuka] [<span id="div_totalTindakanUtama$ID_MESYUARATUTAMA" >0</span> Tindakan Dibuka]
</div>
<div id="divSubFolderTambahFirst$ID_MESYUARATUTAMA" >
</div>

<script>
/*
alert($jquery('#div_viewFolderUtama$ID_MESYUARATUTAMA').length);
	if( $jquery('#div_viewFolderUtama$ID_MESYUARATUTAMA').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_viewFolderUtama$ID_MESYUARATUTAMA').offset().top);
	}
	*/
</script>

#end


#if($listFolderSub.size()>0)
<script>
/*
	if('$LAYER'!="1")
	{
		if('$FLAG_SUB_OPENCLOSE'=="OPEN" && '$ID_MESYUARATCONTENT'!="")
		{
			document.getElementById('iconFolderOpenClose_$ID_MESYUARATCONTENT').innerHTML = "<b><</b>";
		}
		else if('$FLAG_SUB_OPENCLOSE'=="CLOSE" && '$ID_MESYUARATCONTENT'!="")
		{
			document.getElementById('iconFolderOpenClose_$ID_MESYUARATCONTENT').innerHTML = "<b>></b>";
		}
		else
		{
			document.getElementById('iconFolderOpenClose_$ID_MESYUARATCONTENT').innerHTML = "";
		}
	}
	*/
</script>
#else
<script>
/*
	if('$LAYER'!="1")
	{		
		document.getElementById('iconFolderOpenClose_$ID_MESYUARATCONTENT').innerHTML = "";		
	}
	*/
</script>
#end



<script>	
document.getElementById('HID_OPENCLOSE_$ID_MESYUARATUTAMA').value = '$FLAG_OPENCLOSE';

if('$LAYER' != "1")
{
	document.getElementById('divSubFolderTambah$ID_MESYUARATCONTENT').innerHTML='';
	
}
/*
else
{
	
}
*/


if('$ID_MESYUARATCONTENT'!="" && '$AUTOLOAD'=="N")
{
	//alert('x1 : '+'$ID_MESYUARATCONTENT');
	document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_MESYUARATCONTENT').value = '$FLAG_SUB_OPENCLOSE';
	//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_MESYUARATCONTENT').value);
}

</script>


#if($listFolderSub.size()>0)
<table width="100%" align="center" class="classFade" border="0" cellspacing="0" cellpadding="0">
    <tr>	
	<td   > 	
	#foreach($LFS in $listFolderSub)
	
	
	    <input type="hidden" id="tempFieldFolder$LFS.ID_MESYUARATUTAMA" name="tempFieldFolder$LFS.ID_MESYUARATUTAMA">
			   
		
		<script>
		/*
		tempFieldFolder$LFS.ID_MESYUARATUTAMA
		document.getElementById('div_statsUtama$LFS.ID_MESYUARATUTAMA').style.display = "";
		var current_count_load = document.getElementById('countFolder_$LFS.ID_MESYUARATUTAMA').value;
		current_count_load = parseInt(current_count_load)+1;
		document.getElementById('countFolder_$LFS.ID_MESYUARATUTAMA').value = current_count_load;	
		document.getElementById('div_totalDirUtama$LFS.ID_MESYUARATUTAMA').innerHTML = current_count_load;
		*/
		//div_totalTindakanUtama$LFU.ID_MESYUARATUTAMA		
		</script>
	
		
		#set($span1 = "")
		#set($spaneditsub = "")
		#set($divLoadSubAfterDelete = "")
		
		#if($LFS.LAYER=="1")
			#set($span1 = "span1listFolderSub_"+$LAYER+"_"+$LFS.ID_MESYUARATUTAMA+"_"+$LFS.BIL)
			#set($spaneditsub = "spaneditsub_"+$LAYER+"_"+$LFS.ID_MESYUARATUTAMA+"_"+$LFS.BIL)
			#set($divLoadSubAfterDelete = "div_viewFolderUtama"+$ID_MESYUARATUTAMA)
		#else
			#set($span1 = "span1listFolderSub_"+$LFS.ID_MESYUARATCONTENT+"_"+$LFS.BIL)
			#set($spaneditsub = "spaneditsub_"+$LFS.ID_MESYUARATCONTENT+"_"+$LFS.BIL)
			#set($divLoadSubAfterDelete = "divSubFolder"+$ID_MESYUARATCONTENT)
		#end	
		
       
		<div id="$spaneditsub" >	
        <table id="$spaneditsub" width="100%" border="0" cellspacing="0" cellpadding="0"
        style="border-top: 1px solid black;"
        >
        <tr>
         <td valign="top" align="center" width="5%">	
         $LFS.BIL
         </td>
         <td  valign="top" align="justify" width="85%">			
	    <span class="font_tajuk_sub_wc" id="$span1">
	    $LFS.KETERANGAN
        
        <span id="ShowDalamTindakanContent_$LFS.ID_MESYUARATCONTENT">
        #if($LFS.DALAM_TINDAKAN > 0)
               <br />
			   <font color='red' class="blink">* $LFS.DALAM_TINDAKAN Menunggu Tindakan Bahagian</font> 
               
    <a href="javascript:if(confirm('Emel akan dihantar. Adakah Anda Pasti?')){ doDivAjaxCall$formname('divTindakan$LFS.ID_MESYUARATCONTENT','emelTindakanContent','FLAG_TINDAKAN_OPENCLOSE=CLOSE&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$LFS.ID_MESYUARATUTAMA&ID_REFER=$LFS.ID_REFER&ID_MESYUARATCONTENT=$LFS.ID_MESYUARATCONTENT&LAYER=$LFS.LAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val()+'&JUMLAH_TINDAKAN=$LFS.JUMLAH_TINDAKAN');}"><img title="Emel"  src="../img/emel.gif" border="0"></a>
	
        #end
        </span>
	    </span>
        
       
        
        </td>
        <td valign="top" align="center" width="10%">	
		<span class="font_tajuk_sub_wc" >
		<a href="javascript:doDivAjaxCall$formname('$span1','editSubDir','ID_MESYUARATCONTENT=$LFS.ID_MESYUARATCONTENT&LAYER=$LFS.LAYER&BIL=$LFS.BIL');"><img title="Kemaskini Nama Sub Direktori" src="../img/edit.gif" border="0"></a>
	    <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('$divLoadSubAfterDelete','deleteSubDir','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$ID_MESYUARATCONTENT').val()+'&TAJUK=$TAJUK&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_REFER=$ID_MESYUARATCONTENT&ID_TO_DELETE=$LFS.ID_MESYUARATCONTENT&ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&LAYER=$LFS.LAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val()+'&JUMLAH_SUB=$LFS.JUMLAH_SUB');}"><img title="Hapus Direktori"  src="../img/hapus.gif" border="0"></a>
	    </span> 
        </td>
        
        </tr>
        </table>
	    </div>
	   
	    
		<!--   (REF : $ID_MESYUARATCONTENT) -->
		#set($SEND_NUMBERING="")
		#if($LFS.LAYER == "1")
		#set($SEND_NUMBERING="_"+$LFS.BIL)
		#else
		#set($SEND_NUMBERING=$NUMBERING+"_"+$LFS.BIL)			
		#end
		<!--  
		SEND NUMBERING ::::  $SEND_NUMBERING
		-->
		<input type="hidden" id="$ID_MESYUARATUTAMA$SEND_NUMBERING" name="$ID_MESYUARATUTAMA$SEND_NUMBERING" value="$LFS.ID_MESYUARATCONTENT">	
		<input type="hidden" id="check_$ID_MESYUARATUTAMA$SEND_NUMBERING" name="check_$ID_MESYUARATUTAMA$SEND_NUMBERING" value="CLOSE">	
		<input type="hidden" id="checkID_REFER_$ID_MESYUARATUTAMA$SEND_NUMBERING" name="checkID_REFER_$ID_MESYUARATUTAMA$SEND_NUMBERING" value="$LFS.ID_REFER">	
		<input type="hidden" id="checkNextLayer_$ID_MESYUARATUTAMA$SEND_NUMBERING" name="checkNextLayer_$ID_MESYUARATUTAMA$SEND_NUMBERING" value="$LFS.NEXTLAYER">	
		<input type="hidden" id="checkNumbering_$ID_MESYUARATUTAMA$SEND_NUMBERING" name="checkNumbering_$ID_MESYUARATUTAMA$SEND_NUMBERING" value="$SEND_NUMBERING">	
		
		
		
		 
		
		<input type="hidden" id="HID_OPENCLOSE_SUB_$LFS.ID_MESYUARATCONTENT" name="HID_OPENCLOSE_SUB_$LFS.ID_MESYUARATCONTENT" value = "CLOSE"  >
			   
		
		
		<script>
		//off kejap
		
		if(('$AUTOLOAD'=="Y" || '$FlagCari'=="Y"))
		{
			$jquery(document).ready(function () {
					  doDivAjaxCall$formname('divTindakan$LFS.ID_MESYUARATCONTENT','showTindakan','FLAG_TINDAKAN_OPENCLOSE='+$jquery('#HID_OPENCLOSE_TINDAKAN_$LFS.ID_MESYUARATCONTENT').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$LFS.ID_MESYUARATUTAMA&ID_REFER=$LFS.ID_REFER&ID_MESYUARATCONTENT=$LFS.ID_MESYUARATCONTENT&LAYER=$LFS.LAYER&AUTOLOAD=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val());
			});
		}
		
		</script>
		
		
		#set($style_bot = "")       
        #if($listFolderSub.size() ==  $LFS.BIL)
         	#set($style_bot = "border-bottom: 1px solid black;")
        #end
		
		<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0"
        
        style="$style_bot"
        
        >
		<tr>
        <td width="5%" valign="top" align="center">
       
        </td>
		<td width="95%">
		<input type="hidden" id="HID_OPENCLOSE_TINDAKAN_$LFS.ID_MESYUARATCONTENT" name="HID_OPENCLOSE_TINDAKAN_$LFS.ID_MESYUARATCONTENT" value = "CLOSE"  >
		&nbsp;
        
        #if($LAYER == "1")
            <a href="javascript:doDivAjaxCall$formname('divTindakanTambah$LFS.ID_MESYUARATCONTENT','addTindakan','ID_MESYUARATCONTENT=$LFS.ID_MESYUARATCONTENT&ID_MESYUARATUTAMA=$LFS.ID_MESYUARATUTAMA');">
            <img title="Tambah Tindakan" src="../img/plus.gif" border="0"></a>
            
            <span id="countTindakan$LFS.ID_MESYUARATCONTENT">	
            #if($LFS.JUMLAH_TINDAKAN != "0" && $LFS.JUMLAH_TINDAKAN != "")
            <span onClick="doDivAjaxCall$formname('divTindakan$LFS.ID_MESYUARATCONTENT','showTindakan','FLAG_TINDAKAN_OPENCLOSE='+$jquery('#HID_OPENCLOSE_TINDAKAN_$LFS.ID_MESYUARATCONTENT').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$LFS.ID_MESYUARATUTAMA&ID_REFER=$LFS.ID_REFER&ID_MESYUARATCONTENT=$LFS.ID_MESYUARATCONTENT&LAYER=$LFS.LAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val()+'&JUMLAH_TINDAKAN=$LFS.JUMLAH_TINDAKAN');" >
                <span class="font_tajuk_sub"  style="cursor:pointer">
                &nbsp;<u>[$LFS.JUMLAH_TINDAKAN Tindakan Keseluruhan]</u>
                </span>
            </span>
            #else				
                <span class="font_tajuk_sub_wc" >
                &nbsp;[$LFS.JUMLAH_TINDAKAN Tindakan Keseluruhan]
                </span>		   
            #end
            </span>
        #end
		<div id="divTindakanTambah$LFS.ID_MESYUARATCONTENT" >		
		</div>		
		<div id="divTindakan$LFS.ID_MESYUARATCONTENT" >
        
		</div>	
		
        
        
		#set($setStyleCountFolder = "")
		#if($LAYER == "")
		&nbsp;
		<a href="javascript:doDivAjaxCall$formname('divSubFolderTambah$LFS.ID_MESYUARATCONTENT','addSubDir','ID_REFER=$LFS.ID_MESYUARATCONTENT&LAYER=$LFS.NEXTLAYER&ID_MESYUARATUTAMA=$LFS.ID_MESYUARATUTAMA');">
		<img title="Tambah Direktori 123" src="../img/plus.gif" border="0"></a>		
		#else
		#set($setStyleCountFolder = "style='display:none'")
		#end
		
		<span id="countSubFolder$LFS.ID_MESYUARATCONTENT" $setStyleCountFolder >			
		<!--  
		(
		:::: $SEND_NUMBERING
		<span onClick="doDivAjaxCall$formname('divSubFolder$LFS.ID_MESYUARATCONTENT','showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$LFS.ID_MESYUARATCONTENT').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$LFS.ID_MESYUARATUTAMA&ID_REFER=$LFS.ID_MESYUARATCONTENT&ID_MESYUARATCONTENT=$LFS.ID_MESYUARATCONTENT&LAYER=$LFS.NEXTLAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val()+'&JUMLAH_SUB=$LFS.JUMLAH_SUB&SEND_NUMBERING=$SEND_NUMBERING');" >
			<span class="font_tajuk_sub" >
			&nbsp;<u>[$LFS.JUMLAH_SUB Sub Dir]</u>
		    </span>
		</span>
		)
		-->	
		
		
		
		#if($LFS.JUMLAH_SUB != "0" && $LFS.JUMLAH_SUB != "")
		
		
		<span onClick="doDivAjaxCall$formname('divSubFolder$LFS.ID_MESYUARATCONTENT','showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$LFS.ID_MESYUARATCONTENT').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$LFS.ID_MESYUARATUTAMA&ID_REFER=$LFS.ID_MESYUARATCONTENT&ID_MESYUARATCONTENT=$LFS.ID_MESYUARATCONTENT&LAYER=$LFS.NEXTLAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val()+'&JUMLAH_SUB=$LFS.JUMLAH_SUB&SEND_NUMBERING=$SEND_NUMBERING');" >
			<span class="font_tajuk_sub" >
			&nbsp;<!--<span id="iconFolderOpenClose_$LFS.ID_MESYUARATCONTENT"><b>></b></span>--><u>[$LFS.JUMLAH_SUB Sub Dir]</u>
		    </span>
		</span>
		#else		
			<span class="font_tajuk_sub_wc" >
			&nbsp;<!--<span id="iconFolderOpenClose_$LFS.ID_MESYUARATCONTENT"></span>-->[$LFS.JUMLAH_SUB Sub Dir]
		    </span>		   
		#end		
		</span>
		
		
		
		<div id="divSubFolderTambah$LFS.ID_MESYUARATCONTENT" >
		
		</div>
		<div id="divSubFolder$LFS.ID_MESYUARATCONTENT" >
		
		</div>
		</td>
		</tr>
		</table>
		
		
		
		
		#if(($AUTOLOAD=="Y" || $FlagCari=="Y"))		
			#if($LFS.LAYER=="1")
			<script>
			highlight_item($jquery('#carianTerperinci').val(),'span1listFolderSub_'+'$LAYER'+'_'+'$LFS.ID_MESYUARATUTAMA'+'_'+'$LFS.BIL'); 
			</script>
			#else
			<script>
			highlight_item($jquery('#carianTerperinci').val(),'span1listFolderSub'+'_'+'$LFS.ID_MESYUARATCONTENT'+'_'+'$LFS.BIL'); 
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
				//alert('x1 : '+'$ID_MESYUARATCONTENT');
				document.getElementById('HID_OPENCLOSE_SUB_'+'$LFS.ID_REFER').value = '$FLAG_SUB_OPENCLOSE';
				//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_MESYUARATCONTENT').value);
			}
			
			$jquery(document).ready(function () {
				  //doDivAjaxCall$formname('divTindakan$LFS.ID_MESYUARATCONTENT','showTindakan','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$LFS.ID_MESYUARATCONTENT').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$LFS.ID_MESYUARATUTAMA&ID_REFER=$LFS.ID_REFER&ID_MESYUARATCONTENT=$LFS.ID_MESYUARATCONTENT&LAYER=$LFS.NEXTLAYER&AUTOLOAD=Y');
				  doDivAjaxCall$formname('divSubFolder$LFS.ID_MESYUARATCONTENT','showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$LFS.ID_MESYUARATCONTENT').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$LFS.ID_MESYUARATUTAMA&ID_REFER=$LFS.ID_MESYUARATCONTENT&ID_MESYUARATCONTENT=$LFS.ID_MESYUARATCONTENT&LAYER=$LFS.NEXTLAYER&AUTOLOAD=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val());
		   	});
		}
		*/
		</script>
		
		
	
	#end
</td>
	</tr>
</table>
<br />
#end
		
#if($LAYER=="1")

		<input type="hidden" id="HID_OPENCLOSE_LAMP_$ID_MESYUARATUTAMA" name="HID_OPENCLOSE_LAMP_$ID_MESYUARATUTAMA" value = "CLOSE"  >

		<a href="javascript:doDivAjaxCall$formname('divSubLampiranTambah$ID_MESYUARATUTAMA','addLampiran','ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA');">
		<img title="Tambah Dokumen" src="../img/plus.gif" border="0"></a>
		
		<span id="countLampiran$ID_MESYUARATUTAMA">	
		#if($JUMLAH_LAMP != "0" && $JUMLAH_LAMP != "")
		<span onClick="doDivAjaxCall$formname('divSubLampiran$ID_MESYUARATUTAMA','showLampiran','FLAG_LAMP_OPENCLOSE='+$jquery('#HID_OPENCLOSE_LAMP_$ID_MESYUARATUTAMA').val()+'&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_REFER=&LAYER=$LAYER&AUTOLOAD=N&carianLampiran=&JUMLAH_LAMP=$JUMLAH_LAMP');" style="cursor:pointer">
			<span class="font_tajuk_sub" >
			<u>[$JUMLAH_LAMP Dokumen]</u>
		    </span>
		</span>
		#else				
			
			[$JUMLAH_LAMP Dokumen]
		    	   
		#end
		</span>
		<div id="divSubLampiranTambah$ID_MESYUARATUTAMA" >		
		</div>		
		<div id="divSubLampiran$ID_MESYUARATUTAMA" >
		</div>			
#end




<!--  
NUMBERING :::::::: $NUMBERING

AUTOLOAD :::::::: $AUTOLOAD
-->

#if(($AUTOLOAD=="Y"))
<script>
//logic untuk ajax autoload after search, ajaxcall satu per satu mengikut turutan folder.. mcm nak gila

var send_ID_PANDANGANUNDANG = '$ID_MESYUARATUTAMA';
if('$LAYER' == "1")
{
$jquery(document).ready(function () {
		 doDivAjaxCall$formname('divSubLampiran'+send_ID_PANDANGANUNDANG,'showLampiran','FLAG_LAMP_OPENCLOSE='+$jquery('#HID_OPENCLOSE_LAMP_'+send_ID_PANDANGANUNDANG).val()+'&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_REFER='+send_ID_PANDANGANUNDANG+'&LAYER=$LAYER&AUTOLOAD=Y&carianLampiran='+$jquery('#carianLampiran').val());
});
}



var check_ipu = '$ID_MESYUARATUTAMA';
var actual_ipu = "";
var send_ID_MESYUARATCONTENT = "";
if('$NUMBERING'=="")
{
	//alert("x");
	actual_ipu = check_ipu+'_1';
	if(checkUFField(actual_ipu)==true)
	{
		//proceed
		send_ID_MESYUARATCONTENT = actual_ipu;
	}
}
else
{
	actual_ipu = check_ipu+'$NUMBERING'+'_'+1;
	if(checkUFField(actual_ipu)==true)
	{
		//proceed
		send_ID_MESYUARATCONTENT = actual_ipu;
	}
	else
	{
		//alert(" check sebelum loop ");
		actual_ipu = check_ipu+'$NUMBERING';
		/*
		if(checkUFField(actual_ipu)==true)
		{
			send_ID_MESYUARATCONTENT = actual_ipu;
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
				send_ID_MESYUARATCONTENT = actual_ipu;
			}
			else
			{
				actual_ipu = check_ipu+'$NUMBERING';
				var total_us = (actual_ipu.match(new RegExp("_", "g")) || []).length;
				//alert(" total_us :"+total_us);
				for (i = 1; i <= total_us; i++) {
					send_ID_MESYUARATCONTENT = "";
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
						send_ID_MESYUARATCONTENT = actual_ipu;
						//alert(" i : "+i+" total_us : "+total_us+" valid - send_ID_MESYUARATCONTENT : "+send_ID_MESYUARATCONTENT);
						//break;
						total_us = i;
					}			    
					else
					{
						actual_ipu = actual_ipu.substring(0, actual_ipu.lastIndexOf('_'));
					    //continue;
					}
				    /*
				    if(send_ID_MESYUARATCONTENT!="")
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
		//alert("***********Next Feild ID : "+send_ID_MESYUARATCONTENT);
//alert("xxx : "+'$ID_MESYUARATUTAMA'+'__')
//alert('$AUTOLOAD'+'____'+'$FlagCari');
		var  send_ID_PANDANGANUNDAN_value  = $jquery('#'+send_ID_MESYUARATCONTENT).val();
		//alert("***********Next IDP value : "+send_ID_PANDANGANUNDAN_value);
		
		
		if(('$AUTOLOAD'=='Y' || '$FlagCari'=='Y'))
		{
			//alert("val : "+send_ID_PANDANGANUNDANG);
			
			//checkID_REFER_
			//alert("LAYER :"+'$LAYER');
			
			var id_refer = $jquery('#checkID_REFER_'+send_ID_MESYUARATCONTENT).val();
			var next_layer = $jquery('#checkNextLayer_'+send_ID_MESYUARATCONTENT).val();
			var checkNumbering = $jquery('#checkNumbering_'+send_ID_MESYUARATCONTENT).val();
			
			//alert("id_refer : "+id_refer);
			//var next_layer = parseInt('$LAYER')+1;
			
			if(id_refer!="")
			{
				//alert('x1 : '+'$ID_MESYUARATCONTENT');
				document.getElementById('HID_OPENCLOSE_SUB_'+id_refer).value = '$FLAG_SUB_OPENCLOSE';
				//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_MESYUARATCONTENT').value);
			}
			//alert("bawah : "+send_ID_MESYUARATCONTENT);
			$jquery(document).ready(function () {
				  //doDivAjaxCall$formname('divSubFolder'+send_ID_PANDANGANUNDAN_value,'showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_'+send_ID_PANDANGANUNDAN_value).val()+'&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_REFER='+send_ID_PANDANGANUNDAN_value+'&ID_MESYUARATCONTENT='+send_ID_PANDANGANUNDAN_value+'&LAYER='+next_layer+'&AUTOLOAD=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val());
				  doDivAjaxCall$formname('divSubFolder'+send_ID_PANDANGANUNDAN_value,'showAllFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_'+send_ID_PANDANGANUNDAN_value).val()+'&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_REFER='+send_ID_PANDANGANUNDAN_value+'&ID_MESYUARATCONTENT='+send_ID_PANDANGANUNDAN_value+'&LAYER='+next_layer+'&AUTOLOAD=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val()+'&JUMLAH_SUB=&SEND_NUMBERING='+checkNumbering);
			});
		}
		

</script>
#end

<script>
//alert("check");
//var div = document.getElementById("tempFieldFolder$ID_MESYUARATUTAMA");          
//'FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$LFS.ID_MESYUARATCONTENT').val()+'&TAJUK=$LFS.TAJUK&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$LFS.ID_MESYUARATUTAMA&ID_REFER=$LFS.ID_MESYUARATCONTENT&ID_MESYUARATCONTENT=$LFS.ID_MESYUARATCONTENT&LAYER=$LFS.NEXTLAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val()+'&JUMLAH_SUB=$LFS.JUMLAH_SUB'
getTotalFolder("$ID_MESYUARATUTAMA");
getTotalTindakan("$ID_MESYUARATUTAMA");
document.getElementById("HID_MAXLAYER_$ID_MESYUARATUTAMA").value = '$MAX_LAYER';



if('$LAYER'!="1" && '$AUTOLOAD'!="Y")
{
	$jquery(document).ready(function () {
		  doDivAjaxCall$formname('countSubFolder$ID_MESYUARATCONTENT','countSubFolder','FLAG_SUB_OPENCLOSE='+$jquery('#HID_OPENCLOSE_SUB_$ID_MESYUARATCONTENT').val()+'&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_MESYUARATCONTENT=$ID_MESYUARATCONTENT&LAYER=$LAYER&AUTOLOAD=N&TOTAL_SUB=$listFolderSub.size()');
	});
}
		//alert("tempFieldFolder$ID_MESYUARATUTAMA : "+div);
	//	alert(document.getElementById('tempFieldFolder$ID_MESYUARATUTAMA').elements.length);
		/*
		document.getElementById('div_statsUtama$LFS.ID_MESYUARATUTAMA').style.display = "";
		var current_count_load = document.getElementById('countFolder_$LFS.ID_MESYUARATUTAMA').value;
		current_count_load = parseInt(current_count_load)+1;
		document.getElementById('countFolder_$LFS.ID_MESYUARATUTAMA').value = current_count_load;	
		document.getElementById('div_totalDirUtama$LFS.ID_MESYUARATUTAMA').innerHTML = current_count_load;
		*/
</script>

<script>
/*
var current_load_folder = document.getElementById('countFolder_$ID_MESYUARATUTAMA').value;
var total_load_folder = document.getElementById('countActFolder_$ID_MESYUARATUTAMA').value;
var current_load_lampiran = document.getElementById('countTindakan_$ID_MESYUARATUTAMA').value;
var total_load_lampiran = document.getElementById('countActLampiran_$ID_MESYUARATUTAMA').value;
if((current_load_folder == total_load_folder) && (current_load_lampiran==total_load_lampiran))
{
	
	if( $jquery('#'+'div_rowFolderUtama$ID_MESYUARATUTAMA').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_rowFolderUtama$ID_MESYUARATUTAMA').offset().top);
		document.getElementById('countFolder_$ID_MESYUARATUTAMA').value = 0;
		document.getElementById('countTindakan_$ID_MESYUARATUTAMA').value = 0;
	}
	
}
*/
</script>
