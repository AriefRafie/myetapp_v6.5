## head
<head>
	## scripts
	<script src="../bootstrap-wysihtml5-master/lib/js/wysihtml5-0.3.0.js"></script>
	<script src="../bootstrap-wysihtml5-master/lib/js/jquery-1.7.2.min.js"></script>
	<script src="../bootstrap-wysihtml5-master/lib/js/prettify.js"></script>
	<script src="../bootstrap-wysihtml5-master/lib/js/bootstrap.min.js"></script>
	<script src="../bootstrap-wysihtml5-master/src/bootstrap-wysihtml5.js"></script>
	## links
	<link rel="stylesheet" type="text/css" href="../bootstrap-wysihtml5-master/lib/css/bootstrap.min.css"></link>
	<link rel="stylesheet" type="text/css" href="../bootstrap-wysihtml5-master/lib/css/prettify.css"></link>
	<link rel="stylesheet" type="text/css" href="../bootstrap-wysihtml5-master/src/bootstrap-wysihtml5.css"></link>
	## styles
	<style type="text/css">
	tr.tr_class td {
		background-color:#01DFD7;	
		font-weight:bold;
		color:white;
	}
	</style>
</head>

<br>
<br>

<body onLoad="ResetScrollPosition();" >
<fieldset><legend><b>
 Semak Nota Bicara</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">MyID Simati / No Fail : </td>
      			<td width="70%"><input name="txtNoFailSub" id="txtNoFailSub" type="text" value="$txtNoFailSub" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
              
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
        <table width="97%"  cellpadding="1" cellspacing="2" border="0">
<tr >
                <td class="table_header" width="5%" style="display:none"><b></b></td>
      		    <td class="table_header" width="10%"  style="display:none" ><b>ID FAIL</b></td>
                <td class="table_header" width="20%" ><b>NO FAIL</b></td>
                <td class="table_header" width="20%"><b>NAMA SIMATI</b></td>
                <td class="table_header" width="20%"><b>NAMA PEMOHON</b></td>
                <td class="table_header" width="10%"><b>TARIKH MOHON</b></td>
                <td class="table_header" width="15%"><b>STATUS SEMASA</b></td>
                
                          
    		</tr>
            #if($list_fail.size() > 0)
    		
    		#foreach($list in $list_fail)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
               
               
               #set($tr_id = "tr_id"+$i)
               
              <!-- onMouseOver="tr_id_up('$tr_id','$row')" onMouseOut="tr_id_out('$tr_id','$row')" -->
         
         #if($list.ID_FAIL  == $id_fail_carian)
         #set( $row = "tr_class" )
         #end
            <tr id="$tr_id" class="$row"     >
                <td   style="display:none" >  
         <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="white"> Papar  </font>
         #else
         <font color="blue"> Papar  </font>
         #end  
         
              </a>   
                </td>
      			
      		    <td  style="display:none"  >  
                  $list.ID_FAIL   
                </td>
                
                <td  > 
                
                 <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="blue">  $list.NO_FAIL   </font>
         #else
         <font color="blue">  $list.NO_FAIL   </font>
         #end 
         </a> 
                </td>
                
                <td  >
               $list.NAMA_SIMATI
                </td>
                
                <td >
                $list.NAMA_PEMOHON 
                </td>
                
                <td >
               $list.TARIKH_MOHON
                </td>
                
                 <td >
               $list.NAMA_STATUS
                </td>
                          
    		</tr>
         
            #end
            #else
            
            <tr class="row">
      			
      		    <td class="row" colspan="10" > 
                Tiada Rekod              
                </td>
               
                          
    		</tr>
            
            #end
            </table>
        </fieldset>
        #end
        
     
     #if($!view_list_myid == "yes")  
     
     #if($!headerppk.size()>0) 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr >    
    <td >  
    #parse("app/ppk/headerppk.jsp")
</td>
</tr>
</table>
     #end
      
 
#if($showInput=="yes")

	#if($dataNotaBicara.size()!=0)
		#foreach($data in $dataNotaBicara)
			#set($notaBicara = $data.nota_bicara)
		#end
	#else
		#set($notaBicara = "")
	#end
	
	#if($mode=="new" || $mode=="edit")
		#set($check = "")	
	#else
		#set($check = "disabled class='disabled'")	
	#end	
	
	<br/>
	<fieldset>
	<legend><b>Nota Bicara</b></legend>
		<table width="100%" align="center" border="0">
			<tr align="center">
				## <td><textarea name="txtNotaBicara" id="txtNotaBicara" $check cols="100%" rows="15">$!notaBicara</textarea><br/></td>	 
				 <td><textarea name="txtNotaBicara" id="txtNotaBicara" disabled class='disabled' cols="100%" rows="15">$!notaBicara</textarea><br/></td>
			</tr>
			<tr align="center">
				<td><b>Jumlah Perkataan :&nbsp;</b>$!totalWordNotaBicara &nbsp;&nbsp;<b>Bayaran Nota Bicara :&nbsp;</b>RM$!bayaranNB &nbsp;<b></td>
			</tr>
			<tr>
	 			<td width='100%' align='left'>
	 				<a href="javascript:printCatatanPerintah('$list_size');" title="Klik untuk cetak Catatan Perintah">&nbsp;
	 				<u style="color: blue">Nota Keterangan & Perintah</u></a>
	 			</td>
	 		</tr>
		</table>
	</fieldset>
	
	#if($mode=="new")   
    		<input type="hidden" id="contentoE" value="true">
    		<input type="hidden" id="designoE" value="on">
    #elseif($mode=="edit") 
    		<input type="hidden" id="contentoE" value="true">
    		<input type="hidden" id="designoE" value="on">
    #else
    		<input type="hidden" id="contentoE" value="false">
    		<input type="hidden" id="designoE" value="off">		
    #end
	
	
	<!-- 
	<script> 
            var area = new FCKeditor("txtNotaBicara");
	      	area.BasePath = '/${appname}/library/fck/' ;
	      	area.Height = 300;
	      	area.Width = 750;
	      	area.ReplaceTextarea();     
	
   		 	var contentoE= document.getElementById("contentoE").value;
   		 	var designoE= document.getElementById("designoE").value;
	
    		var oEditor = FCKeditorAPI.GetInstance('txtNotaBicara');
    	
    		function FCKeditor_OnComplete(oEditor)
    		{
    			oEditor.EditorDocument.body.contentEditable=contentoE;
    			oEditor.EditorDocument.designMode=designoE; 	
    			if(contentoE=="false"){
    				oEditor.EditorDocument.body.style.cssText += 'color: #322805;background-color: #F2F2EE;';	
    			}	
    		}
	</script>
	 -->
	
	
		<table width="100%" align="center" border="0">
			<tr align="center">
				<td>
                
					#if($mode=="new")
<input name="cmdSimpan" id="cmdSimpan" value="Simpan" type="button" onClick="javascript:simpanNotaBicara('$id_fail_carian','cmdSimpan')">
 #if($flag_kemaskini_selesai != "yes")
                                <script>
                                document.getElementById('cmdSimpan').style.display = "none";
                                </script>
                                #end 
					#elseif($mode=="view")
<input name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" type="button" onClick="javascript:kemaskiniNotaBicara('$id_fail_carian','cmdKemaskini')">

 							   #if($flag_kemaskini_selesai != "yes")
                                <script>
                                document.getElementById('cmdKemaskini').style.display = "none";
                                </script>
                                #end   

					#elseif($mode=="edit")	
						<input name="cmdUpdate" id="cmdUpdate" value="Simpan" type="button" onClick="javascript:updateNotaBicara('$id_fail_carian','cmdUpdate','$id_notabicara')">
                        
                       		 #if($flag_kemaskini_selesai != "yes")
                                <script>
                                document.getElementById('cmdUpdate').style.display = "none";
                                </script>
                                #end 
                        
						<input name="cmdBatal" id="cmdBatal" value="Batal" type="button" onClick="javascript:batalNotaBicara('$id_fail_carian','cmdBatal')">
					#end
				</td>
			</tr>
		</table>
	
	
#elseif($showInput=="no")

<table width="100%" align="center" border="0">
<tr>
				<td>
	<fieldset>
		<table width="100%" align="center" border="0">
			<tr>
				<td>Tiada Rekod</td>
			</tr>
		</table>
	</fieldset>
    </td>
    </tr>
    </table>
#else	
#end

<input type="hidden" name="id_notabicara" value="$id_notabicara">
<input type="hidden" name="doaction2">
<input type="hidden" name="doaction">

 
 #end            

<input type="hidden" name="id_fail_carian" id="id_fail_carian" value="$id_fail_carian" /> 
<!--ScrollX :  --><input type="hidden" id="ScrollX" name='ScrollX'  />
<!--ScrollY :  --><input type="hidden" id="ScrollY" name='ScrollY'  />  
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'> 
<input type="hidden" name="sama_kp_baru" id="sama_kp_baru" value="$!sama_kp_baru"   >
<input type="hidden" name="sama_kp_lama" id="sama_kp_lama" value="$!sama_kp_lama"   >
<input type="hidden" name="sama_kp_lain" id="sama_kp_lain" value="$!sama_kp_lain"   >
<input type="hidden" name="sama_kp_alert" id="sama_kp_alert" value="$!sama_kp_alert"   >



#parse("app/ppk/headerppk_script.jsp")



   
<script >


	
function search_main_data_sub()
{
	SaveScrollXY();  	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmNotaBicara";   
	document.${formName}.command.value = "cariMainSub";
    document.${formName}.submit();
	document.${formName}.cmdSemakSub.value = "Sila Tunggu...";
}
function kosongkan_sub() {
SaveScrollXY();        
document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmNotaBicara";   
document.${formName}.txtNoFailSub.focus();
//doAjaxCall${formName}("kosongkan");
document.${formName}.command.value = "kosongkan";
    document.${formName}.submit();
}
function paparFail(id_fail)
{
document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmNotaBicara";   
SaveScrollXY();        
document.${formName}.id_fail_carian.value = id_fail;

//doAjaxCall${formName}("paparSub");
document.${formName}.command.value = "paparSub";
    document.${formName}.submit();
}

function simpanSub(id_fail,nama_butang)
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	SaveScrollXY();        
	document.${formName}.id_fail_carian.value = id_fail;
	//doAjaxCall${formName}("simpanSub");
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmNotaBicara";   
	document.${formName}.command.value = "simpanSub";
	document.${formName}.submit();
	document.getElementById(nama_butang).value = "Sila Tunggu...";
	}
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


function simpanNotaBicara(id_fail,nama_butang){
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.command.value = "paparSub";
	document.${formName}.doaction.value = "simpanNotaBicara";
	document.${formName}.id_fail_carian.value = id_fail;
	document.${formName}.submit();
	document.getElementById(nama_butang).value = "Sila Tunggu...";
}
function kemaskiniNotaBicara(id_fail,nama_butang){
	document.${formName}.command.value = "paparSub";
	document.${formName}.doaction.value = "kemaskiniNotaBicara";	
	document.${formName}.id_fail_carian.value = id_fail;
	document.${formName}.submit();	
	document.getElementById(nama_butang).value = "Sila Tunggu...";
}
function updateNotaBicara(id_fail,nama_butang,idnotabicara){
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.command.value = "paparSub";
	document.${formName}.doaction.value = "kemaskiniNotaBicara";
	document.${formName}.doaction2.value = "updateNotaBicara";
	document.${formName}.id_notabicara.value = idnotabicara;	
	document.${formName}.id_fail_carian.value = id_fail;
	document.${formName}.submit();	
	document.getElementById(nama_butang).value = "Sila Tunggu...";
}
function batalNotaBicara(id_fail,nama_butang){
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.command.value = "paparSub";
	document.${formName}.id_fail_carian.value = id_fail;
	document.${formName}.submit();
	document.getElementById(nama_butang).value = "Sila Tunggu...";
}
function printCatatanPerintah(ID_PERBICARAAN)
{
	var url = "../x/${securityToken}/ekptg.view.ppk.BicaraInteraktifPrint?&ID_PERBICARAAN="+ID_PERBICARAAN+"&command=showCatatanPerintah";
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
}


</script>