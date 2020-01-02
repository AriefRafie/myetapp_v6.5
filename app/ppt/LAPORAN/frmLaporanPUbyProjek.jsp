<style type="text/css">
tr.tr_class td {
	background-color:#01DFD7;	
	font-weight:bold;
	color:white;
}
</style>
<br>
<br>
<body onLoad="ResetScrollPosition();" >
<fieldset><legend><b>
 <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>Baru</blink></font></b>
                             </label>&nbsp;
Laporan PU (Projek)</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">Projek / No Fail : </td>
      			<td width="70%"><input name="txtNoFailSub" id="txtNoFailSub" type="text" value="$txtNoFailSub" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
                <a href="javascript:open_info()" class="help" title="info">							
                            <b><font color="blue"><blink><i>i</i></blink></font></b>
                            </a>
              
                </td>
    		</tr>
    		<tr>
      			<td scope="row"></td>
      			<td><input name="cmdSemakSub" id="cmdSemakSub" value="Semak" type="button" onClick="javascript:search_main_data_sub()">
        			<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onClick="javascript:kosongkan_sub()"></td>
    		</tr>
    		
		</table>
        #if($view_list_fail == "yes")
      
        <br>
        <fieldset>
        <table align="center" width="100%"> 
          <tbody>
          
          
            <tr class="table_header">
          
              <td scope="row" width="5%" align="center">BIL</td>
              <td width="20%">NO FAIL JKPTG</td>
             
              <td width="30%">KEMENTERIAN DAN NAMA PROJEK</td>
              <td width="15%">TARIKH PERMOHONAN DITERIMA</td>
              <td width="30%">CETAK</td>
             
            </tr>
          #set ($list = "")
         #set( $i = 0 )   
          #foreach ($list in $SenaraiFail)
          #set( $counter = $velocityCount - 1 )
          	
                 #set( $i = $i + 1 ) 
               
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
           
           
          <tr>
            <td class="$row" align="center">
     
			
            $i
            
            </td>
            
          	#set($status_fail = "$list.STATUS.toUpperCase()")
         	
         	#if($list.ID_STATUS=="11" && $list.FLAG_SEMAK=="1")
         	    #if($list.ID_SUBURUSAN=="51")
         	    	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b><i>&nbsp;<blink>TUNGGU PENGESAHAN DAN AGIHAN</blink></i></b>")
         	    #else
         	    	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b><i>&nbsp;<blink>TUNGGU PENGESAHAN</blink></i></b>")
         	    #end
         	#elseif($list.ID_STATUS=="127" && $list.FLAG_SEMAK=="2")
            	#set($status_fail = "<b><i><blink>TELAH DISAHKAN</blink></i></b>")
            #elseif($list.ID_STATUS=="16")
            	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b><i>&nbsp;<blink>TUNGGU UNTUK DIAGIHAN</blink></i></b>")	
            #elseif($list.ID_STATUS=="148")
            	#set($status_fail = "<b><i><blink>TELAH DIAGIHKAN</blink></i></b>")	
            #elseif(($list.ID_STATUS=="132" && $list.FLAG_SEMAKAN_PENGARAH=="1") || ($list.ID_STATUS=="26" && $list.FLAG_SEMAKAN_PENGARAH=="1"))
            	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b>&nbsp;<i><blink>TUNGGU SEMAKAN MMK</blink></i></b>")	
            #elseif(($list.ID_STATUS=="133" && $list.FLAG_SEMAKAN_PENGARAH=="2") || ($list.ID_STATUS=="26" && $list.FLAG_SEMAKAN_PENGARAH=="2"))
            	#set($status_fail = "<b><i><blink>TELAH DISEMAK</blink></i></b>")	
            	
            #elseif($list.ID_STATUS=="74" && $list.FLAG_MMK_PENARIKAN=="1")	
            	#set($status_fail = "<img src='../img/alert.gif' alt='' border='0'/><b>&nbsp;<i><blink>TUNGGU SEMAKAN MMK (PENARIKAN BALIK)</blink></i></b>")		
            #elseif($list.ID_STATUS=="74" && $list.FLAG_MMK_PENARIKAN=="2" && $list.STATUS_KEPUTUSAN == "")	
            	#set($status_fail = "<b><i><blink>TELAH DISEMAK (PENARIKAN BALIK)</blink></i></b>")	
            #else
            	#set($status_fail = "$list.STATUS")
            #end
            
            
            
            
            <td class="$row">
            #if($list.NO_JKPTG == '')
           <font color="blue">TIADA NO FAIL JKPTG 
                #if($list.NO_RUJUKAN_PTG != "")                
                <br />$list.NO_RUJUKAN_PTG.toUpperCase()
                #end
                #if($list.NO_RUJUKAN_PTD != "") 
                <br />$list.NO_RUJUKAN_PTD.toUpperCase()
                #end
                #if($list.NO_RUJUKAN_UPT != "") 
                <br />$list.NO_RUJUKAN_UPT.toUpperCase()
                #end
                </font>
            #else
            	<font color="blue">$list.NO_JKPTG.toUpperCase()
                #if($list.NO_RUJUKAN_PTG != "")                
                <br />$list.NO_RUJUKAN_PTG.toUpperCase()
                #end
                #if($list.NO_RUJUKAN_PTD != "") 
                <br />$list.NO_RUJUKAN_PTD.toUpperCase()
                #end
                #if($list.NO_RUJUKAN_UPT != "") 
                <br />$list.NO_RUJUKAN_UPT.toUpperCase()
                #end
               </font>
            #end
             </td>
           
            <td class="$row"><font  color="blue"><b>$!list.NAMA_KEMENTERIAN</b></font><br/>$list.TUJUAN.toUpperCase()</td>
            <td class="$row">$list.TARIKH_PERMOHONAN.toUpperCase()</td>
           
            
           
            <td valign="top" class="$row">
            <font color="blue">
            <li> <a href="javascript:cetakLaporan('$list.ID_FAIL','1')" class="style2" ><font color="blue">Laporan Bulanan PU</font></a></li>
           </font>
           
           <font color="blue">
        <li><a href="javascript:cetakLaporan('$list.ID_FAIL','2')" class="style2" ><font color="blue">Laporan Bulanan Pelarasan PU
 </font></a></li></font>
 
 <font color="blue">
 <li><a href="javascript:cetakLaporan('$list.ID_FAIL','3')" class="style2" ><font color="blue">Laporan Bulanan Hakmilik Sambungan PU
 </font></a></li></font>
            </td>
            
 
            </tr>
          #end
         
           #if( $SenaraiFail.size() == 0)
            <tr>
            
            <td  colspan="10">
           Tiada rekod           </td>
           </tr>
           #end 
          
          </tbody>
        </table>
        </fieldset>
        #end
        
        
<script >

function cetakLaporan(id_fail,jenisSurat) {
		if(jenisSurat=="1"){
	    	var url = "../servlet/ekptg.report.ppt.LaporanPUbyProjek?id_fail="+id_fail;
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		}else if(jenisSurat=="2"){
			var url = "../servlet/ekptg.report.ppt.LaporanPUPelarasanbyProjek?id_fail="+id_fail;
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		}else if(jenisSurat=="3"){
			var url = "../servlet/ekptg.report.ppt.LaporanPUSambunganbyProjek?id_fail="+id_fail;
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		}   
}

function search_main_data_sub()
{
	//SaveScrollXY();  	
	document.${formName}.command.value = "cariMainSub";
	document.${formName}.submit();
	document.${formName}.cmdSemakSub.value = "Sila Tunggu...";
}
function kosongkan_sub() {
	//SaveScrollXY();        
	document.${formName}.action = "";
	document.${formName}.txtNoFailSub.focus();
	document.${formName}.command.value = "kosongkan";
	document.${formName}.submit();
}

function SaveScrollXY() {
document.${formName}.ScrollX.value = document.body.scrollLeft;
document.${formName}.ScrollY.value = document.body.scrollTop;
}

function ResetScrollPosition() {
var hidx, hidy;
hidx = '$ScrollX';
hidy = '$ScrollY';
                        
if (typeof hidx != 'undefined' && typeof hidy != 'undefined') {
      window.scrollTo(hidx, hidy);
    }
}


function check_format_kp_baru(jenis_kp,saiz)
{
//alert("SAIZ:"+saiz);
var saizint = parseInt(saiz);

if(saizint == 1)
{  
	var field = document.getElementById('no_kp_baru_'+jenis_kp+saizint); 		  
	//alert("no_kp_ob = 1 :"+field.value)
	check_format_alert(field.value,'alert_baru_'+jenis_kp+saizint);
}
else
{
	  for (i = 1; i <= saizint; i++)
	  {
	  var field = document.getElementById('no_kp_baru_'+jenis_kp+i,'baru');
	  //alert("no_kp_ob > 1 ("+i+"):"+field.value)	
	  //alert("no_kp_ob > 1 ("+i+"):"+'alert_baru_'+jenis_kp+i)	
	  //alert("alert_baru_"+jenis_kp+i);
	  check_format_alert(field.value,'alert_baru_'+jenis_kp+i,'baru');
	  //$jquery("#alert_baru_"+jenis_kp+i).html("<font color = 'red'>"+"alert_baru_"+jenis_kp+i+"</font>");
	  } 	 	   
}
}

function check_format_kp_lama(jenis_kp,saiz)
{
//alert("SAIZ:"+saiz);
var saizint = parseInt(saiz);

if(saizint == 1)
{  
	var field = document.getElementById('no_kp_lama_'+jenis_kp+saizint); 		  
	//alert("no_kp_ob = 1 :"+field.value)
	check_format_alert(field.value,'alert_lama_'+jenis_kp+saizint,'lama');
}
else
{
	  for (i = 1; i <= saizint; i++)
	  {
	  var field = document.getElementById('no_kp_lama_'+jenis_kp+i);	 
	  check_format_alert(field.value,'alert_lama_'+jenis_kp+i,'lama');	  
	  } 	 	   
}
}
  
function check_format_alert(value,div_id,jenis)
{
	
if(jenis == "baru")
{	
var ayat_alert = "";
if(value!="")
{
	if(value.length != 12)
	{
	ayat_alert = "Myid Tidak Lengkap";	
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>"+ayat_alert+" </font>");
	}
	else
	{
	ayat_alert = "";
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
	}	
}
else
{
return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
}
}


if(jenis == "lama")
{	
var ayat_alert = "";
if(value!="")
{
	if(value.length != 8 && value.length != 7)
	{
		if(value!="TDK")
		{	
		ayat_alert = "Myid Tidak Lengkap";	
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>"+ayat_alert+" </font>");
		}
		else
		{
		ayat_alert = "";
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
		}
	}
	else
	{
	ayat_alert = "";
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
	}	
}
else
{
return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
}
}

}

function check_format_alert_field(fil,div_id,jenis)
{
	
if(jenis=="baru")
{
	if(fil.value!="")
	{
		if(fil.value.length != 12)
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>Myid Tidak Lengkap</font>");
		}
		else
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
		}	
	}
	else
	{
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
	}
}

if(jenis=="lama")
{
	
	if(fil.value!="")
	{
		if(fil.value.length != 8 && fil.value.length != 7)
		{
			if(fil.value!="TDK")
			{
			return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>Myid Tidak Lengkap</font>");	
			}else
			{
			return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
			}
		}
		else
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
		}	
	}
	else
	{
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
	}
}

	
}


function check_myid_temp(id)
{

	return $jquery("#"+id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
}

function sama_pemohon(field_pemohon_baru,field_pemohon_lama,field_pemohon_lain)
{
	
	var field_kp_alert = document.getElementById('sama_kp_alert').value;
	var field_ob_kp_baru = document.getElementById('sama_kp_baru').value;
	var field_ob_kp_lama = document.getElementById('sama_kp_lama').value;
	var field_ob_kp_lain = document.getElementById('sama_kp_lain').value;
	
	var field_pemohon_baru_temp = document.getElementById(field_pemohon_baru).value;
	var field_pemohon_lama_temp = document.getElementById(field_pemohon_lama).value;
	var field_pemohon_lain_temp = document.getElementById(field_pemohon_lain).value;
	
	if(field_ob_kp_baru!="")
	{
	document.getElementById(field_ob_kp_baru).value = field_pemohon_baru_temp.substring(0,field_pemohon_baru_temp.length);
	check_format_alert_field(document.getElementById(field_ob_kp_baru),field_kp_alert)
	}
	
	if(field_ob_kp_lama!="")
	{
	document.getElementById(field_ob_kp_lama).value = field_pemohon_lama_temp.substring(0,field_pemohon_lama_temp.length);
	}
	if(field_ob_kp_lain!="")
	{
	document.getElementById(field_ob_kp_lain).value = field_pemohon_lain_temp.substring(0,field_pemohon_lain_temp.length);
	}
	
}


function sama_ob()
{
	
	
	var field_ob_kp_baru = document.getElementById('sama_kp_baru').value;
	var field_ob_kp_lama = document.getElementById('sama_kp_lama').value;
	var field_ob_kp_lain = document.getElementById('sama_kp_lain').value;
	
	var field_pemohon_baru_temp = document.getElementById(field_ob_kp_baru).value;
	var field_pemohon_lama_temp = document.getElementById(field_ob_kp_lama).value;
	var field_pemohon_lain_temp = document.getElementById(field_ob_kp_lain).value;
	
	
	if(field_pemohon_baru_temp!="")
	{
	document.getElementById('no_kp_baru_pemohon1').value = field_pemohon_baru_temp.substring(0,field_pemohon_baru_temp.length);
	check_format_alert_field(document.getElementById('no_kp_baru_pemohon1'),'alert_baru_pemohon1')
	}
	
	if(field_pemohon_lama_temp!="")
	{
	document.getElementById('no_kp_lama_pemohon1').value = field_pemohon_lama_temp.substring(0,field_pemohon_lama_temp.length);
	}
	
	if(field_pemohon_lain_temp!="")
	{
	document.getElementById('no_kp_lain_pemohon1').value = field_pemohon_lain_temp.substring(0,field_pemohon_lain_temp.length);
	}
	
	
}

function check_myid_simati(action,div_alert)
{	
	/*url = "../servlet/ekptg.view.ppk.PendaftaranCheck";	
	actionName = action;
	target = div_alert;
	doAjaxUpdater(document.${formName}, url, target, actionName);*/
}

function open_info() 
{

 var width  = 200;
 var height = 200;
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=front';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
 new_window = open("","title",params);
 new_window.document.open();

new_window.document.write("<html><title>Info Maklumat Carian</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");
new_window.document.write("<table><tr><td><b><u>Jenis-Jenis Carian</u></b></td></tr></table>");

new_window.document.write("<table width='100%'><tr><td width='100%' valign='top'>");

new_window.document.write("<table><tr><td><b>Fail</b></td></tr>");
new_window.document.write("<tr><td><font color='blue'><li>&nbsp;No. Fail JKPTG </li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;No. Fail PTG</li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;No. Fail PTD</li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;No. Fail UPT</li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;Nama Projek</li></font>	");
new_window.document.write("<font color='blue'><li>&nbsp;Kementerian</li></font></td></tr></table>");

new_window.document.write("</td></tr></table>");

new_window.document.write("</body></html>");
new_window.document.close();



}


function cetak(jenisTempoh,jenisSurat){

	if(document.${formName}.socPejabat.value == ""){
	   	alert("Sila pilih \"Pejabat JKPTG\" terlebih dahulu.");
		document.${formName}.socPejabat.focus();
		return;
	}
	
	//Sehingga	
	else if((jenisTempoh=="sehingga" || jenisTempoh=="julat") && document.${formName}.socBulan.value == ""){
		alert("Sila pilih \"Bulan\" terlebih dahulu.");
		document.${formName}.socBulan.focus();
		return;
	}else if((jenisTempoh=="sehingga" || jenisTempoh=="julat") && document.${formName}.socTahun.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahun.focus();
		return;
	}

	//julat
	else if(jenisTempoh=="julat" && document.${formName}.socBulanSehingga.value == ""){
		alert("Sila pilih \"Bulan\" terlebih dahulu.");
		document.${formName}.socBulanSehingga.focus();
		return;
	}else if(jenisTempoh=="julat" && document.${formName}.socTahunSehingga.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahunSehingga.focus();
		return;
	}

	else{		

		var bulanSE = "";
		var bulanSE2 = "";
		var tahunSE = "";
		var bulantahunSE = "";
		var type = "";
		if(jenisTempoh=="julat"){
			bulanSE = parseInt(document.${formName}.socBulanSehingga.value)+1;
			bulanSE2 = parseInt(document.${formName}.socBulanSehingga.value);
			tahunSE = document.${formName}.socTahunSehingga.value;
			bulantahunSE = bulanSE+"/"+tahunSE
			type = "2";
		}else if(jenisTempoh=="sehingga"){
			type = "1";
		}else{
			type = "3";
		}
		
		var id_pejabat = document.${formName}.id_pejabat.value;
		var id_daerah = document.${formName}.id_daerah.value;
		var bulan = document.${formName}.bulan.value;
		var tahun = document.${formName}.tahun.value;
		var bulantahun = bulan+"/"+tahun;

		var item = "ID_PEJABAT='"+id_pejabat+"'&BULANSE='"+bulanSE2+"'&TAHUNSE='"+tahunSE+"'&BULAN='"+bulan+"'&type="+type+"&TAHUN='"+tahun+"'&bulantahun='"+bulantahun+"'&ID_DAERAH='"+id_daerah+"'&jenisTempoh='"+jenisTempoh+"'&bulantahunSE='"+bulantahunSE+"'";	
		
	    if(jenisSurat=="1"){
	    	var url = "../servlet/ekptg.report.ppt.LaporanPU?"+item 
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		}else if(jenisSurat=="2"){
			var url = "../servlet/ekptg.report.ppt.LaporanPUPelarasan?"+item 
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		}else if(jenisSurat=="3"){
			var url = "../servlet/ekptg.report.ppt.LaporanPUSambungan?"+item 	
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		}   		
	    
	}

}


</script>