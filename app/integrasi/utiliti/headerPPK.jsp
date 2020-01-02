<!-- ::::: $!headerppk -->
<style type="text/css">


#sddmheader
{	margin: 0;
	padding: 0;
	z-index: 30}

#sddmheader li
{	margin: 0;
	padding: 0;
	list-style: none;
	float: left;
	
	}

#sddmheader li a
{	display: block;
	color: #FFF;
	text-align: center;
	text-decoration: none}

#sddmheader li a:hover
{	
background: #E0F2F7;
}

#sddmheader div
{	position: absolute;
	visibility: hidden;
	margin: 0;
	padding: 0;	
	border: 1px solid #5970B2;
	z-index:2;
	}

	#sddmheader div a
	{	position: relative;
		display: block;
		margin: 0;
		padding: 2.5px 10px;
		width: auto;
		white-space: nowrap;
		text-align: left;
		text-decoration: none;
		background: #EAEBD8;
		color: #2875DE;
		font: 11px arial;
		z-index:2;
		}

	#sddmheader div a:hover
	{	background: #49A3FF;
		color: #FFFFFF;
		}

</style>

#set($boleh_kemaskini_parent = "no")
#if($!headerppk.HEADER_NO_SUBJAKET == $!headerppk.HEADER_NO_SUBJAKET_MAX)
#set($boleh_kemaskini_parent = "yes")
#end


<input type="hidden" name="id_permohonansimati_atheader" id="id_permohonansimati_atheader" value="$!headerppk.ID_PERMOHONANSIMATI"/>
<input type="hidden" name="id_fail_atheader" id="id_fail_atheader"  value="$!headerppk.ID_FAIL"/>
<input type="hidden" name="id_permohonan_atheader" id="id_permohonan_atheader"  value="$!headerppk.ID_PERMOHONAN"/>


#set($id_fail_atheader = $!headerppk.ID_FAIL)
#set($id_permohonan_atheader = $!headerppk.ID_PERMOHONAN)
#set($id_permohonansimati_atheader = $!headerppk.ID_PERMOHONANSIMATI)
#set($no_fail_atheader = $!headerppk.NO_FAIL)

<fieldset id="headerppk">
<legend>MAKLUMAT PERMOHONAN</legend>
	<table width="100%" border="0" align="center">
  		<tr>
    		<td valign="top" width="50%"><div align="center">
      			<table width="100%" border="0">    
			        <tr>
			          <td style="text-transform:uppercase;" valign="top" ><div align="right">Nama Simati</div></td>
			           <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
			          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.NAMA_SIMATI</font>
			          ##set($tarikhmati_simati = $!headerppk.TARIKH_MATI)
			          </td>
			        </tr>    
			        <tr>
			          <td valign="top" ><div align="right">MyID SIMATI</div></td>
			           <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
			          <td style="text-transform:uppercase;" valign="top" >
			          	<font color="blue">$!headerppk.KP_SIMATI</font>
			          </td>
			        </tr>   
			        <tr>
			          <td valign="top" ><div align="right">TARIKH MATI</div></td>
			           <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
			          <td style="text-transform:uppercase;" valign="top" >
			          	<font color="blue">$!headerppk.TARIKH_MATI</font>
			          </td>
			        </tr> 			         			        
			        <tr id="tr_tarikhmohon">
			        	<td style="text-transform:uppercase;" valign="top" ><div align="right">Tarikh Mohon</div></td>
			           	<td  style="text-transform:uppercase;" valign="top" >:</td>
			          	<td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.TARIKH_MOHON</font>
			          		<input type="hidden" name="tarikh_mohon_headerppk" id="tarikh_mohon_headerppk" value="$!headerppk.TARIKH_MOHON"  />
			          	</td>
			        </tr>
		        	<tr>
		          		<td style="text-transform:uppercase;" valign="top" ><div align="right">Nama Pemohon</div></td>
		           		<td  style="text-transform:uppercase;" valign="top" >:</td>
		          		<td  valign="top" >
		          			<font style="text-transform:uppercase;" color="blue">$!headerppk.NAMA_PEMOHON</font>
		          		</td>
		        	</tr>
			        <!--   			
        			<tr id="tr_nofail">
          				<td width="30%" style="text-transform:uppercase;" valign="top" align="right">
          				No Fail
         				</td>
          				<td width="1%" style="text-transform:uppercase;" valign="top">
          				:
          				</td>
          				<td width="69%" style="text-transform:uppercase;" valign="top">          
    						<ul id="sddmheader">
    							<li><a href="#" 
        onmouseover="mopen('m1')" 
        onmouseout="mclosetime()"><font color="blue">$!headerppk.NO_FAIL</font>        
        						</a> 
		        					<div id="m1" 
						            onmouseover="mcancelclosetime()" 
						            onmouseout="mclosetime()" >           
						           #foreach ($list in $list_sub_header)
						          <a href="javascript:papar_header('$list.idPermohonan','$list.idStatus','$list.bil','$list.idPermohonanSimati','$list.tarikhMohon','$list.flagjenisfail','$list.seksyen','$list.idSimati')" >$list.keterangan</a>
						           #end
		            
		        					</div>
    							</li>
   							</ul>
						<div style="clear:both"></div>
            		</td>
        		</tr>
        <tr>
          <td  style="text-transform:uppercase;" valign="top"><div align="right">Negeri</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top">:</td>
          
          <td style="text-transform:uppercase;" valign="top"><font color="blue">$!headerppk.NAMA_NEGERI</font></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top"><div align="right">Daerah / Jajahan</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.DAERAH_MOHON</font></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Unit</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.NAMA_PEJABAT, $!headerppk.DAERAH_PEJABAT</font></td>
        </tr>        
        <tr id="tr_sasarankpi">
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Tempoh Sasaran KPI</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
          <td style="text-transform:uppercase;" valign="top" ><div id="headerppk_kpi_ppk" ></div></td>
        </tr> -->
        
        #if($!headerppk.ID_PERMOHONAN_SEBELUM_BICARA != "" || $!headerppk.ID_PERMOHONAN_SELEPAS_BICARA != "")
        <tr >
          <td style="text-transform:uppercase;" valign="top" align="right" >Status Perbicaraan Semula</td>
          <td  style="text-transform:uppercase;" valign="top" >:</td>
          <td  valign="top" >
          #if($!headerppk.NO_FAIL_SEBELUM_BICARA!="")
              <font color="blue"><blink>Fail ini dibenarkan untuk dibicarakan semula.</blink></font>
              <br>Untuk semakan maklumat perbicaraan terdahulu, <br />sila klik               
               <a href="javascript:historyFail($!headerppk.SEKSYEN_SEBELUM_BICARA,$!headerppk.ID_PERMOHONAN_SEBELUM_BICARA,$!headerppk.ID_STATUS_SEBELUM_BICARA,$!headerppk.ID_PERMOHONANSIMATI_SEBELUM)" ><font color="blue"> $!headerppk.NO_FAIL_SEBELUM_BICARA</font></a>             
          #end  
              
          #if($!headerppk.NO_FAIL_SELEPAS_BICARA!="")
             <font color="blue"><blink>Fail ini telah dibicarakan semula.</blink></font>
              <br>Untuk semakan maklumat perbicaraan semasa, <br />sila klik               
              <a href="javascript:currentFail($!headerppk.SEKSYEN_SELEPAS_BICARA,$!headerppk.ID_PERMOHONAN_SELEPAS_BICARA,$!headerppk.ID_STATUS_SELEPAS_BICARA)" ><font color="blue"> $!headerppk.NO_FAIL_SELEPAS_BICARA</font></a>  
          #end 
          
          #if($!headerppk.USER_NAME_BICARA != "")
          <br />
          Pengesahan oleh : <font color="blue">$!headerppk.USER_NAME_BICARA</font>
          #else
          <br />
          Pengesahan oleh : <font color="blue">KEPUTUSAN MAHKAMAH</font>
          #end 
          	
          #if($!headerppk.CATATAN_BICARA_SEMULA != "") 
          <br />
          Catatan :
          <font color="blue">$!headerppk.CATATAN_BICARA_SEMULA</font> 
          #end
          </td>
        </tr>
        #end
      </table>
    </div></td>
    
			<td width="50%" valign="top">
      			<table width="100%" border="0">
        			<tr id="tr_nofail">
          				<td width="30%" style="text-transform:uppercase;" valign="top" align="right">
          				No. Fail
         				</td>
          				<td width="1%" style="text-transform:uppercase;" valign="top">
          				:
          				</td>
          				<td width="69%" style="text-transform:uppercase;" valign="top">          
    						<font color="blue">$!headerppk.NO_FAIL</font>
            			</td>
        			</tr> 
			      	<tr>
			          <td  style="text-transform:uppercase;" valign="top"><div align="right">Negeri</div></td>
			          <td width="1%" style="text-transform:uppercase;" valign="top">:</td>
			          
			          <td style="text-transform:uppercase;" valign="top"><font color="blue">$!headerppk.NAMA_NEGERI</font></td>
			        </tr>
			        <tr>
			          <td style="text-transform:uppercase;" valign="top"><div align="right">Daerah / Jajahan</div></td>
			          <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
			          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.DAERAH_MOHON</font></td>
			        </tr>
			        <tr>
			          <td style="text-transform:uppercase;" valign="top" ><div align="right">Unit</div></td>
			          <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
			          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.NAMA_PEJABAT, $!headerppk.DAERAH_PEJABAT</font></td>
			        </tr>     
			        <tr>
			          <td width="20%" style="text-transform:uppercase;" valign="top" ><div align="right">Status Fail</div></td>
			          <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
			          <td width="79%" style="text-transform:uppercase;" valign="top" ><font color="blue">
			          
			        	#set($status = $!headerppk.NAMA_STATUS)                  
                  		#if(!$!headerppk.NAMA_STATUS.equals('SELESAI'))
                  			#set($status = "DALAM PROSES")
                  		#end
                  		$status
			          </font>         
			          </td>
			        </tr>
			        <tr>
			          <td style="text-transform:uppercase;" valign="top" ><div align="right">Seksyen</div></td>
			          <td  style="text-transform:uppercase;" valign="top" >:</td>
			          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.SEKSYEN</font></td>
			        </tr>
        
      <!-- 
        <tr id="tr_tarikhmohon">
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Tarikh Mohon</div></td>
           <td  style="text-transform:uppercase;" valign="top" >:</td>
          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.TARIKH_MOHON</font>
          <input type="hidden" name="tarikh_mohon_headerppk" id="tarikh_mohon_headerppk" value="$!headerppk.TARIKH_MOHON"  />
        
          
          </td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Nama Pemohon</div></td>
           <td  style="text-transform:uppercase;" valign="top" >:</td>
          <td  valign="top" >
          <font style="text-transform:uppercase;" color="blue">$!headerppk.NAMA_PEMOHON</font>
           <br />Pengantian pemohon boleh dibuat  
           <a href="javascript:paparKemaskiniPemohon('$!headerppk.ID_FAIL','$!headerppk.NO_FAIL')" >
           <font color="blue">disini</font> (Jika Perlu)</a>
          
          </td>
        </tr>               
        
       #if($!headerppk.EMEL != "")
         <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Emel Pemohon</div></td>
           <td  style="text-transform:uppercase;" valign="top" >:</td>
          <td ><a href="mailto:$!emel_pemohon"><font color="blue"><u>$!headerppk.EMEL</u></font></a></td>
        </tr>
       #end
        
         <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Nama Simati</div></td>
           <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.NAMA_SIMATI</font>
          #set($tarikhmati_simati = $!headerppk.TARIKH_MATI)
          </td>
        </tr> -->
      </table>
    </td>
  </tr>
</table>
</fieldset>
<!--
:::::::::::::::: $!flag_kemaskini_selesai
<br />
:::::::::::::::: $!headerppk.STATUS_EDIT_USER.CHECK_EDIT
-->
  #if($!headerppk.NO_FAIL != "")
      <script>
      document.getElementById('tr_nofail').style.display="";
      </script>
      #else
      <script>
      document.getElementById('tr_nofail').style.display="none";
      </script>
      #end
        
        
      #if($!headerppk.TARIKH_MOHON != "")
      <script>
      document.getElementById('tr_sasarankpi').style.display="";
      </script>
      #else
      <script>
      document.getElementById('tr_sasarankpi').style.display="none";
      </script>
      #end
        
         #if($!headerppk.TARIKH_MOHON != "")
      <script>
      document.getElementById('tr_tarikhmohon').style.display="";
      </script>
      #else
      <script>
      document.getElementById('tr_tarikhmohon').style.display="none";
      </script>
      #end
        

<!--
ScrollX :<input type="text" id="ScrollX" name='ScrollX'  />
ScrollY :<input type="text" id="ScrollY" name='ScrollY'  />
-->
</fieldset>

<div id="field_location"></div>
<!--JENIS VM :::$!flag_jenis_vm
   -->


<!--JKPTG/PK/03/02/0584/2009/S17-1-->




#if($!flag_jenis_vm == "vtemplate")
<script>

function paparKemaskiniFail(id_fail_carian,txtNoFailSub) {
//document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&command=paparSub";
document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail&command=paparSub&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"";
//document.f1.id_fail_carian.value = id_fail_carian;
//document.f1.txtNoFailSub.value = txtNoFailSub;
document.f1.submit();
}

function paparKemaskiniPemohon(id_fail_carian,txtNoFailSub) {
//document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&command=paparSub";
document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmTukarPemohon&command=paparSub&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"";
//document.f1.id_fail_carian.value = id_fail_carian;
//document.f1.txtNoFailSub.value = txtNoFailSub;
document.f1.submit();
}

</script>
#else
<script>

function paparKemaskiniFail(id_fail_carian,txtNoFailSub) {
//alert("tab id"+"$EkptgUtil.getTabID("Utiliti",$portal_role)");	
document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail&command=paparSub&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"";
//document.${formName}.id_fail_carian.value = id_fail_carian;
//document.${formName}.txtNoFailSub.value = txtNoFailSub;
document.${formName}.submit();
}

function paparKemaskiniPemohon(id_fail_carian,txtNoFailSub) {
//alert("tab id"+"$EkptgUtil.getTabID("Utiliti",$portal_role)");	
document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmTukarPemohon&command=paparSub&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"";
//document.${formName}.id_fail_carian.value = id_fail_carian;
//document.${formName}.txtNoFailSub.value = txtNoFailSub;
document.${formName}.submit();
}
</script>

#end



#if($!flag_jenis_vm == "vtemplate")
<script>
//alert("XXXX");
var temp_tarikh = "";
temp_tarikh = document.f1.tarikh_mohon_headerppk.value;
if (temp_tarikh != "" ){
var tarikhHantar = temp_tarikh;
var days = 0;
if('$!headerppk.SEKSYEN' == '8')
{
var days = 165;
}
if('$!headerppk.SEKSYEN' == '17')
{
var days = 90;
}
var dt1 = parseInt(tarikhHantar.substring(0,2),10) + days;
var mon1 = parseInt(tarikhHantar.substring(3,5),10)-1;
var yr1 = parseInt(tarikhHantar.substring(6,10),10);
var currentTime = new Date();
var myDate = new Date(yr1, mon1, dt1);
var day = myDate.getDate();
var month = myDate.getMonth()+1;
var year = myDate.getFullYear();
var tarikhJangkaTerima = "";
if(month>=10){
if(day>=10){
tarikhJangkaTerima = day + "/" + month + "/" + year;
} else {
tarikhJangkaTerima = "0"+ day + "/" + month + "/" + year;
}
} else {
if(day>=10){
tarikhJangkaTerima = day + "/0" + month + "/" + year;
} else {
tarikhJangkaTerima = "0"+ day + "/0" + month + "/" + year;
}
}


if(currentTime>myDate)
{
$jquery("#headerppk_kpi_ppk").html("<span  style='color:red'><blink>"+tarikhJangkaTerima+"</blink></span>");
}
else
{
$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
}

} else {
$jquery("#headerppk_kpi_ppk").html("<span  style='color:red'></span>");
}
</script>

#else

<script>

var temp_tarikh = "";
temp_tarikh = document.${formName}.tarikh_mohon_headerppk.value;

if (temp_tarikh != "" ){
var tarikhHantar = temp_tarikh;
var days = 0;
if('$!headerppk.SEKSYEN' == '8')
{
var days = 165;
}
if('$!headerppk.SEKSYEN' == '17')
{
var days = 90;
}
var dt1 = parseInt(tarikhHantar.substring(0,2),10) + days;
var mon1 = parseInt(tarikhHantar.substring(3,5),10)-1;
var yr1 = parseInt(tarikhHantar.substring(6,10),10);
var currentTime = new Date();
var myDate = new Date(yr1, mon1, dt1);
var day = myDate.getDate();
var month = myDate.getMonth()+1;
var year = myDate.getFullYear();
var tarikhJangkaTerima = "";
if(month>=10){
if(day>=10){
tarikhJangkaTerima = day + "/" + month + "/" + year;
} else {
tarikhJangkaTerima = "0"+ day + "/" + month + "/" + year;
}
} else {
if(day>=10){
tarikhJangkaTerima = day + "/0" + month + "/" + year;
} else {
tarikhJangkaTerima = "0"+ day + "/0" + month + "/" + year;
}
}


if(currentTime>myDate)
{
$jquery("#headerppk_kpi_ppk").html("<span  style='color:red'><blink>"+tarikhJangkaTerima+"</blink></span>");
}
else
{
$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
}

} else {
$jquery("#headerppk_kpi_ppk").html("<span  style='color:red'></span>");
}
</script>

#end


