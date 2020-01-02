#set($displayByRole = "none")
#if($USER_ROLE == "(PDT) Pengguna Bahagian Pengurusan Perundangan Tanah")
#set($displayByRole = "block")
#end
<script>	

document.getElementById('divSubLampiranTambah$ID_MESYUARATUTAMA').innerHTML='';

if('$ID_MESYUARATUTAMA'!="" && '$AUTOLOAD'=="N")
{
	//alert('x1 : '+'$ID_MESYUARATUTAMA');
	document.getElementById('HID_OPENCLOSE_LAMP_'+'$ID_MESYUARATUTAMA').value = '$FLAG_LAMP_OPENCLOSE';
	//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_MESYUARATUTAMA').value);
}
</script>


#if($listFolderLampiran.size()>0)	
<table width="100%" align="center" border="0" class="classFade"  cellspacing="0" cellpadding="0"
 style="border-top: 1px solid black;border-bottom: 1px solid black;">
<tr>
<td>


<table width="50%" align="left" border="0" cellspacing="1" cellpadding="">

<tr class="table_header" >
           <td   align="center" valign="top" width="5%" >No.</td> 
		   <td   align="left" valign="top" width="75%" >Dokumen</td> 
           <td   align="center" valign="top" width="20%" style="display:$displayByRole" >Tindakan</td> 
	</tr>

	#foreach($LFL in $listFolderLampiran)
	<script>/*
	    document.getElementById('div_statsUtama$LFL.ID_MESYUARATUTAMA').style.display = "";
		var current_count_load = document.getElementById('countLampiran_$LFL.ID_MESYUARATUTAMA').value;
		current_count_load = parseInt(current_count_load)+1;
		document.getElementById('countLampiran_$LFL.ID_MESYUARATUTAMA').value = current_count_load;	
		document.getElementById('div_totalLampiranUtama$LFL.ID_MESYUARATUTAMA').innerHTML = current_count_load;
		*/
	</script>

	
	
	<tr id="rowLampiran_$LFL.ID_MESYUARATDOKUMEN">
    
     <td class="$LFL.rowCss"   align="center" valign="top"  >$LFL.BIL</td> 
     
		
	<td class="$LFL.rowCss"  valign="top" align="left" >
	<input type="hidden" id="tempFieldLampiran$LFL.ID_MESYUARATUTAMA" name="tempFieldLampiran$LFL.ID_MESYUARATUTAMA" >
	
	#set($span2 = "span2listFolderLampiran_"+$LFL.ID_MESYUARATDOKUMEN+"_"+$LFL.BIL)

	<span class="font_tajuk_sub" id="$span2" style="cursor:pointer" >
	<u onClick="paparDoc($LFL.ID_MESYUARATDOKUMEN);">$LFL.NAMA_DOC</u>
	</span>
	
    
           #if(($AUTOLOAD=="Y" || $FlagCari=="Y"))	
           <script>highlight_item('$carianLampiran','$span2')</script>
           #end
	
	
	</td>
    <td  class="$LFL.rowCss"  align="center" valign="top" style="display:$displayByRole">
    	<a href="javascript:doDivAjaxCall$formname('rowLampiran_$LFL.ID_MESYUARATDOKUMEN','editLampiran','ID_MESYUARATDOKUMEN=$LFL.ID_MESYUARATDOKUMEN&ID_MESYUARATUTAMA=$LFL.ID_MESYUARATUTAMA&BIL=$LFL.BIL&rowCss=$LFL.rowCss');"><img title="Kemaskini Nama Sub Direktori" src="../img/edit.gif" border="0"></a>
	<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('divSubLampiran$LFL.ID_MESYUARATUTAMA','deleteLampiran','NAMA_DOC=$LFL.NAMA_DOC&ID_MESYUARATDOKUMEN=$LFL.ID_MESYUARATDOKUMEN&FLAG_LAMP_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$LFL.ID_MESYUARATUTAMA&LAYER=&AUTOLOAD=N');}"><img title="Hapus Lampiran"  src="../img/hapus.gif" border="0"></a>
	
    </td> 
	</tr>
	
	#if(($AUTOLOAD=="Y" || $FlagCari=="Y"))		
	<script>
	highlight_item($jquery('#carianLampiran').val(),'span2listFolderLampiran'+'_'+'$LFL.ID_MESYUARATDOKUMEN'+'_'+'$LFL.BIL'); 
	</script>			
    #end
	
	<script>	
	//alert("check");
		if(('$AUTOLOAD'=="Y" || '$FlagCari'=="Y"))
		{
			
			if('$LFL.ID_MESYUARATUTAMA'!="")
			{
				//alert('x1 : '+'$ID_MESYUARATUTAMA');
				document.getElementById('HID_OPENCLOSE_LAMP_'+'$LFL.ID_MESYUARATUTAMA').value = '$FLAG_LAMP_OPENCLOSE';
				//alert('x2 : '+document.getElementById('HID_OPENCLOSE_SUB_'+'$ID_MESYUARATUTAMA').value);
			}
			
		}
	</script>
	
	
	#end
	
</table>

</td>
</tr>
</table>
<br />
#end


<script>

//getTotalLampiran("$ID_MESYUARATUTAMAUTAMA");
//alert(" AUTOLOAD : "+'$AUTOLOAD');

if('$AUTOLOAD'!="Y")
{
	//alert("a");
	$jquery(document).ready(function () {
		  doDivAjaxCall$formname('countLampiran$ID_MESYUARATUTAMA','countLampiran','FLAG_LAMP_OPENCLOSE='+$jquery('#HID_OPENCLOSE_LAMP_$ID_MESYUARATUTAMA').val()+'&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&LAYER=$LAYER&AUTOLOAD=N&TOTAL_LAMPIRAN=$listFolderLampiran.size()');
	});
	//alert("b");
}



/*
var current_load_folder = document.getElementById('countFolder_$ID_MESYUARATUTAMAUTAMA').value;
var total_load_folder = document.getElementById('countActFolder_$ID_MESYUARATUTAMAUTAMA').value;
var current_load_lampiran = document.getElementById('countLampiran_$ID_MESYUARATUTAMAUTAMA').value;
var total_load_lampiran = document.getElementById('countActLampiran_$ID_MESYUARATUTAMAUTAMA').value;
if((current_load_folder == total_load_folder) && (current_load_lampiran==total_load_lampiran))
{
	if( $jquery('#'+'div_rowFolderUtama$ID_MESYUARATUTAMAUTAMA').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_rowFolderUtama$ID_MESYUARATUTAMAUTAMA').offset().top);
		document.getElementById('countFolder_$ID_MESYUARATUTAMAUTAMA').value = 0;
		document.getElementById('countLampiran_$ID_MESYUARATUTAMAUTAMA').value = 0;
	}
}
*/
</script>

