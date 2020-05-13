<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>

<input type="hidden" name="id_siasatan" id="id_siasatan" value="$id_siasatan"/>


#set($txtTuntutanTuanTanah = "")
#set($txtLainTuntutan = "")
#set($txtPBDaftar = "")
#set($txtPBXDaftar = "")


#if($readmode == "edit")
#set($disabledmode = "")
#set($readonlymode = "")
#elseif($readmode == "view")
#set($disabledmode = "disabled")
#set($readonlymode = "readonly")
#else
#set($disabledmode = "")
#set($readonlymode = "")
#end
<table width="100%">
  
  #foreach($list_siasatan in $maklumat_siasatan_history)
#set($txtTuntutanTuanTanah = $list_siasatan.TUNTUTAN_TUANTNH)
#set($txtLainTuntutan = $list_siasatan.TUNTUTAN_PB_LAIN)
#set($txtPBDaftar = $list_siasatan.TUNTUTAN_PB_BEBANAN)
#set($txtPBXDaftar = $list_siasatan.TUNTUTAN_PB_TDKDAFTAR)
#end
  
   <tr>
<td>#parse("app/ppt/header_1_ppt.jsp") </td>
  </tr>
  <tr>
<td>#parse("app/ppt/header_siasatan.jsp") </td>



  </tr>
<tr>
<td><div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <!--  <li class="TabbedPanelsTab" tabindex="0"  onClick="screen5('$id_permohonan')">Kembali</li> -->
     <!-- PPT-19 --> 
     <li class="TabbedPanelsTab" tabindex="0"  onClick="popupCarianHakmilik('$id_permohonan','senarai_siasatan')">Kembali</li>
     
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuantanah('$id_siasatan')" id="tuan_tanah"  >Keterangan Tuan Tanah</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="agensi('$id_siasatan')" id="Agensi" >Agensi / Jurunilai</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tuntutan('$id_siasatan')">Tuntutan</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="bantahan('$id_siasatan')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="nilaian('$id_siasatan')"  >Nilaian JPPH</li>
     <li class="TabbedPanelsTab" tabindex="0" onClick="PB('$id_siasatan')"  >Pihak Berkepentingan
     
     <!-- <font style="cursor:help" title="info"  align="center" class="font2" onMouseOut="close_window()"   onMouseOver ="open_new_window('3');this.style.cursor='help'" >
       #parse("app/ppt/infoblink_biru.jsp")
       </font> -->
      
     
     </li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="status('$id_siasatan')" >Status Siasatan</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="keputusan('$id_siasatan')" id="perintah_keputusan">Perintah</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
     <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
   <fieldset>
   <table width="100%">
  <tr>
    <td  valign="top"><table width="100%">
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Tuntutan Tuan Tanah</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtTuntutanTuanTanah" id="txtTuntutanTuanTanah" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtTuntutanTuanTanah_check','yes','tuntutan tuan tanah','normal')"  onKeyUp="checking_validation(this,'txtTuntutanTuanTanah_check','yes','tuntutan tuan tanah','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtTuntutanTuanTanah</textarea>     
         <div id="txtTuntutanTuanTanah_check" style="color:red" > </div>    
         -->
         
         
         <textarea name="txtTuntutanTuanTanah" id="txtTuntutanTuanTanah" cols="60"   rows="6"         
         onBlur="check_length(this,'4000','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','no','tuntutan tuan tanah');"  
         onKeyup="check_length(this,'4000','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','no','tuntutan tuan tanah');" 
         onKeydown="check_length(this,'4000','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','no','tuntutan tuan tanah');"                    
          $readonlymode class = "$disabledmode" 
        >$txtTuntutanTuanTanah</textarea> 
          
         
       #if($readmode == "edit")           
        <div><span id="txtTuntutanTuanTanah_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtTuntutanTuanTanah_num" id="txtTuntutanTuanTanah_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtTuntutanTuanTanah_check" class="alert_msg" ></div> 
         
            </td>
      </tr>
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Lain-Lain Tuntutan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtLainTuntutan" id="txtLainTuntutan" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtLainTuntutan_check','yes','lain-lain tuntutan','normal')"  onKeyUp="checking_validation(this,'txtLainTuntutan_check','yes','lain-lain tuntutan','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtLainTuntutan</textarea>     
         <div id="txtLainTuntutan_check" style="color:red" > </div>     
         
         -->
         
          <textarea name="txtLainTuntutan" id="txtLainTuntutan" cols="60"   rows="6"         
         onBlur="check_length(this,'4000','txtLainTuntutan_check','txtLainTuntutan_num','normal','no','lain-lain tuntutan');"  
         onKeyup="check_length(this,'4000','txtLainTuntutan_check','txtLainTuntutan_num','normal','no','lain-lain tuntutan');" 
         onKeydown="check_length(this,'4000','txtLainTuntutan_check','txtLainTuntutan_num','normal','no','lain-lain tuntutan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtLainTuntutan</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtLainTuntutan_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtLainTuntutan_num" id="txtLainTuntutan_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtLainTuntutan_check" class="alert_msg" ></div> 
          
           </td>
      </tr>
      
      
      
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Penduduk, Penyewa dan Pemajakan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtPBDaftar" id="txtPBDaftar" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtPBDaftar_check','yes','pihak berkepentingan berdaftar','normal')"  onKeyUp="checking_validation(this,'txtKeteranganAgensi_check','yes','pihak berkepentingan berdaftar','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtPBDaftar</textarea>     
         <div id="txtPBDaftar_check" style="color:red" > </div>   -->
         <div style="display:none;">
         <textarea   name="txtPBDaftar" id="txtPBDaftar" cols="60"   rows="6"         
         onBlur="check_length(this,'4000','txtPBDaftar_check','txtPBDaftar_num','normal','no','pihak berkepentingan berdaftar');"  
         onKeyup="check_length(this,'4000','txtPBDaftar_check','txtPBDaftar_num','normal','no','pihak berkepentingan berdaftar');" 
         onKeydown="check_length(this,'4000','txtPBDaftar_check','txtPBDaftar_num','normal','no','pihak berkepentingan berdaftar');"                    
          $readonlymode class = "$disabledmode" 
        >$txtPBDaftar</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtPBDaftar_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtPBDaftar_num" id="txtPBDaftar_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtPBDaftar_check" class="alert_msg" ></div> 
  
  </div>
					#if($senarai_tuntutan.size()!=0)
    					#foreach($list in $senarai_tuntutan)   
    					<input type="hidden" name="TUNTUTAN_MAIN_temp1"  id="TUNTUTAN_MAIN_temp1" value="$list.KETERANGAN_TUNTUTAN" >  
    					#end
    				#end 
    	
    	
    				<span id="TUNTUTAN_MAIN"></span>           
   					<div id="TUNTUTAN_MAIN_temp"></div>
         
             </td>
      </tr>
     
       <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Pemegang Gadaian,  Pemasuk Kaveat dan Pemegang Lien</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        <!--
        <textarea name="txtPBXDaftar" id="txtPBXDaftar" cols="50"  rows="6" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtPBXDaftar_check','yes','pihak berkepentingan tidak berdaftar','normal')"  onKeyUp="checking_validation(this,'txtPBXDaftar_check','yes','pihak berkepentingan tidak berdaftar','normal');this.value=this.value.toUpperCase();" $readonlymode class = "$disabledmode" >$txtPBXDaftar</textarea>     
         <div id="txtPBXDaftar_check" style="color:red" > </div> -->
         
         
            <textarea name="txtPBXDaftar" id="txtPBXDaftar" cols="60"   rows="6"         
         onBlur="check_length(this,'4000','txtPBXDaftar_check','txtPBXDaftar_num','normal','no','pihak berkepentingan tidak berdaftar');"  
         onKeyup="check_length(this,'4000','txtPBXDaftar_check','txtPBXDaftar_num','normal','no','pihak berkepentingan tidak berdaftar');" 
         onKeydown="check_length(this,'4000','txtPBXDaftar_check','txtPBXDaftar_num','normal','no','pihak berkepentingan tidak berdaftar');"                    
          $readonlymode class = "$disabledmode" 
        >$txtPBXDaftar</textarea> 
          
        
       #if($readmode == "edit")           
        <div><span id="txtPBXDaftar_num" style="color:blue;" ></span><span> Baki Aksara</span>        
         </div>
         #else
         <input name="txtPBXDaftar_num" id="txtPBXDaftar_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtPBXDaftar_check" class="alert_msg" ></div> 
  
  
         
               </td>
      </tr>
      

      
      
      
    </table></td>
  </tr>
  <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini('$id_siasatan')">
      </label>
      <label>
      
       <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus('$id_siasatan')">
      
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan('$id_siasatan')">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal('$id_siasatan')">
      </label>
    #end  
     
       #if($id_pembatalan != "")
        #if($readmode == "view")
        <label></label>
       
        <label> 
    <!--  <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  --> 
      </label>    
      #end
      #end
       <label></label>
     </div>      </td>
  </tr>
</table>

   </fieldset>
    </div>
   
   
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div></td>
</tr>

</table>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    
       <tr>
        <td><a href="#" class="style2" onClick="nota('$id_siasatan')"><font color="blue">Nota Siasatan Tarik Balik</font></a></td>
      </tr>  
       
    </table>
</fieldset>
<input type="hidden" name="sub_command" id="sub_command" />
  <input type="hidden" name="subminor_command" id="subminor_command" />
  <input type="hidden" name="location" id="location" />
  <input type="hidden" name="point" id="point" />
  <input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$!id_pembatalan" />
  <input type="hidden" name="screen_name" id="screen_name" value="s2" />
  <input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
  <input type="hidden" name="alert_message" id="alert_message" />
  <input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
  <input type="hidden" name="jumlah_semua" id="jumlah_semua" />
  <input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" />
  <input type="hidden" name="id_tanahumum" id="id_tanahumum" value="$!id_tanahumum" />
  <input type="hidden" name="id_siasatan" id="id_siasatan" value="$!id_siasatan" />
  
  
  

 
  
  #set($mode = "")
  #set($isEdit = "")
  
  #if($readmode == "view")
  #set($mode = "view")
      #set($disability = "readonly")
      #set($disabilityx = "class=disabled")
      #set($isEdit = "no")
  #elseif($readmode == "edit")
 	 #set($mode = "new")  
     #set($isEdit = "yes")
  #end
  
  
  <!-- PENAMBAHAN SKRIN YATI -->
  
 
 <fieldset id="senarai_siasatan">
<legend>SENARAI PIHAK BERKEPENTINGAN </legend>

<a href="javascript:popupCarianPB('$id_hakmilik','skrin_pb_siasatan_sek8','$!id_hakmilikpb')"><font color="blue">>> SKRIN CAPAIAN PIHAK BERKEPENTINGAN</font></a>	


</fieldset></td>
</tr>
</table>


<!-- PPT-24 -->
<fieldset>
	<legend>SIASATAN</legend>
	 <table width="100%" border="0"> 
		<tr>
			<td width="1%"></td>
			<td colspan="3">
				<div align="left"><a href="javascript:papar('$id_siasatan','$id_hakmilik')" title="Memaparkan secara lengkap maklumat set siasatan"><font color="blue">MAKLUMAT SIASATAN</font></a></div>
			</td>
		</tr>
		
		<tr>
			<td width="1%"></td>
			<td colspan="3">
				<div align="left"><a href="javascript:kehadiran('$id_siasatan')" title="Memaparkan secara lengkap maklumat kehadiran"><font color="blue">MAKLUMAT KEHADIRAN</font></a></div>
			</td>
		</tr>

		<!-- tr>
			<td width="1%"></td>
			<td colspan="3">
				<div align="left"><a href="javascript:maklumatsiasatan('$id_siasatan')" title="Memaparkan secara lengkap maklumat siasatan"><font color="blue">NOTA SIASATAN </font></a></div>
			</td>
		</tr-->
	</table>
</fieldset>
<!-- PPT-24 END -->



    </div>
   
   
       <div class="TabbedPanelsContent"></div>
        <div class="TabbedPanelsContent"></div>
  </div>
</div>

</td>
</tr>
</table>



 
  
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:3});
var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm;

function submitForm() 
{


if('$socStatusSiasatan' == "6" )
{
document.getElementById('perintah_keputusan').style.display="";  
}
else
{
document.getElementById('perintah_keputusan').style.display="none"; 
}

if('$location' != "" && '$location' != null && '$point' != "" && '$point' != null)
{
window.location.hash='$location';
goTo('$point');
}
else
{
window.location.hash='paging';
goTo('paging');
}


//checking_validation(document.${formName}.txtTuntutanTuanTanah,'txtTuntutanTuanTanah_check','yes','tuntutan tuan tanah','normal');
//checking_validation(document.${formName}.txtLainTuntutan,'txtLainTuntutan_check','yes','lain-lain tuntutan','normal');
//checking_validation(document.${formName}.txtPBDaftar,'txtPBDaftar_check','yes','pihak berkepentingan berdaftar','normal');
//checking_validation(document.${formName}.txtPBXDaftar,'txtPBXDaftar_check','yes','pihak berkepentingan tidak berdaftar','normal');

check_length(document.${formName}.txtTuntutanTuanTanah,'4000','txtTuntutanTuanTanah_check','txtTuntutanTuanTanah_num','normal','no','tuntutan tuan tanah');
check_length(document.${formName}.txtLainTuntutan,'4000','txtLainTuntutan_check','txtLainTuntutan_num','normal','no','lain-lain tuntutan');
check_length(document.${formName}.txtPBDaftar,'4000','txtPBDaftar_check','txtPBDaftar_num','normal','no','pihak berkepentingan berdaftar');
check_length(document.${formName}.txtPBXDaftar,'4000','txtPBXDaftar_check','txtPBXDaftar_num','normal','no','pihak berkepentingan tidak berdaftar');


textarea_TUNTUTAN_MAIN('tambahtolak','umum','');

}


function textarea_TUNTUTAN_MAIN(tambahtolak,jenis,index_tolak){
	//alert("1");
	var TUNTUTAN_MAIN_temp1_length=0;

	if(document.${formName}.TUNTUTAN_MAIN_temp1 != null){

		if(document.${formName}.TUNTUTAN_MAIN_temp1.length>0){
			TUNTUTAN_MAIN_temp1_length = document.${formName}.TUNTUTAN_MAIN_temp1.length;
		}else{
			TUNTUTAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanTUNTUTAN_MAIN!=null){

		if(document.${formName}.txtUlasanTUNTUTAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanTUNTUTAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='TUNTUTAN_MAIN_tempX1' name='TUNTUTAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanTUNTUTAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='TUNTUTAN_MAIN_tempX1' name='TUNTUTAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanTUNTUTAN_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#TUNTUTAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	//alert("2");

	if(jenis == "umum"){
		if(TUNTUTAN_MAIN_temp1_length>0){
			ttTUNTUTAN_MAIN = TUNTUTAN_MAIN_temp1_length;
		}else{
			ttTUNTUTAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttTUNTUTAN_MAIN = ttTUNTUTAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttTUNTUTAN_MAIN = ttTUNTUTAN_MAIN + parseInt(tambahtolak);
	}


	//alert("3 :"+ttTUNTUTAN_MAIN);
  	for (i = 0; i < ttTUNTUTAN_MAIN; i++){	

  		if(ttTUNTUTAN_MAIN==1){
			//alert("4 :"+ttTUNTUTAN_MAIN);

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.TUNTUTAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.TUNTUTAN_MAIN_tempX1[0].value
				} 	
			}		

			if(jenis == "umum"){
				//temp_value = '$TUNTUTANMMKSelangorSek8';
			}
			
   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			#if($mode=='new') 																										"+
					  "			<td width='100%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanTUNTUTAN_MAIN\" id=\"txtUlasanTUNTUTAN_MAIN\" cols=\"60\"  rows=\"6\"						"+                  		
         	          "				onKeyup=\"check_length_tuntutan(this,'4000','txtUlasanTUNTUTAN_MAIN_check','txtUlasanTUNTUTAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length_tuntutan(this,'4000','txtUlasanTUNTUTAN_MAIN_check','txtUlasanTUNTUTAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanTUNTUTAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanTUNTUTAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='100%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanTUNTUTAN_MAIN\" id=\"txtUlasanTUNTUTAN_MAIN\" cols=\"60\"  rows=\"6\"			"+                  		
         	          "				onKeyup=\"check_length_tuntutan(this,'4000','txtUlasanTUNTUTAN_MAIN_check','txtUlasanTUNTUTAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length_tuntutan(this,'4000','txtUlasanTUNTUTAN_MAIN_check','txtUlasanTUNTUTAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div style=\"display:none;\" ><span id=\"txtUlasanTUNTUTAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanTUNTUTAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "																						"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_TUNTUTAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttTUNTUTAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_TUNTUTAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";
					  
					 // alert("5 :"+ttTUNTUTAN_MAIN);

		}else{	
		

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttTUNTUTAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.TUNTUTAN_MAIN_tempX1.value

			}else if(ttTUNTUTAN_MAIN>2 && i!=(ttTUNTUTAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.TUNTUTAN_MAIN_tempX1[i].value

			}else if(ttTUNTUTAN_MAIN>1 && i!=(ttTUNTUTAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.TUNTUTAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.TUNTUTAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.TUNTUTAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttTUNTUTAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.TUNTUTAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.TUNTUTAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%' valign='top'>"+(i+1)+".  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='98%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanTUNTUTAN_MAIN\" id=\"txtUlasanTUNTUTAN_MAIN\" cols=\"60\"  rows=\"6\"						"+                  		
	         	        "			onKeyup=\"check_length_tuntutan(this,'4000','txtUlasanTUNTUTAN_MAIN_check"+i+"','txtUlasanTUNTUTAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length_tuntutan(this,'4000','txtUlasanTUNTUTAN_MAIN_check"+i+"','txtUlasanTUNTUTAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanTUNTUTAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanTUNTUTAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='98%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanTUNTUTAN_MAIN\" id=\"txtUlasanTUNTUTAN_MAIN\" cols=\"60\"  rows=\"6\"			"+                  		
	         	        "			onKeyup=\"check_length_tuntutan(this,'4000','txtUlasanTUNTUTAN_MAIN_check"+i+"','txtUlasanTUNTUTAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length_tuntutan(this,'4000','txtUlasanTUNTUTAN_MAIN_check"+i+"','txtUlasanTUNTUTAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div style=\"display:none;\" ><span id=\"txtUlasanTUNTUTAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanTUNTUTAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td  valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttTUNTUTAN_MAIN>1 && ttTUNTUTAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_TUNTUTAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttTUNTUTAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_TUNTUTAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	//alert("DISPPLAY KODES :"+codes1);
	$jquery("#TUNTUTAN_MAIN").html(codes1);

	 //alert("6 :"+ttTUNTUTAN_MAIN);
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(TUNTUTAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUNTUTAN_MAIN.length > 1 ){

		for (t = 0; t < TUNTUTAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanTUNTUTAN_MAIN[t].value = document.${formName}.TUNTUTAN_MAIN_temp1[t].value;
		}

		}else if(TUNTUTAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUNTUTAN_MAIN.length < 1 ){
		
			for (t = 0; t < TUNTUTAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanTUNTUTAN_MAIN.value = document.${formName}.TUNTUTAN_MAIN_temp1[0].value;
			}
		}
		
		else if(TUNTUTAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanTUNTUTAN_MAIN.value = document.${formName}.TUNTUTAN_MAIN_temp1.value;
		}
		
	}
	
	 //alert("7 :"+ttTUNTUTAN_MAIN);
	if(jenis == "tolak"){	
		
		if(TUNTUTAN_MAIN_temp1_length > 1){

			for (t = 0; t < TUNTUTAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.TUNTUTAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.TUNTUTAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(TUNTUTAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.TUNTUTAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.TUNTUTAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak	
	// alert("8 :"+ttTUNTUTAN_MAIN);		

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttTUNTUTAN_MAIN; i++){		

		    if(ttTUNTUTAN_MAIN==1){
				// alert("9 :"+ttTUNTUTAN_MAIN);
		 check_length_tuntutan(document.${formName}.txtUlasanTUNTUTAN_MAIN,'4000','txtUlasanTUNTUTAN_MAIN_check','txtUlasanTUNTUTAN_MAIN_num','normal','no','');	
       //alert("10 :"+ttTUNTUTAN_MAIN);
				}else{	
				check_length_tuntutan(document.${formName}.txtUlasanTUNTUTAN_MAIN[i],'30000','txtUlasanTUNTUTAN_MAIN_check'+i,'txtUlasanTUNTUTAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	//alert("last");
}


function textarea_kerosakan1()
{	//TUNTUTAN
	var TUNTUTAN_MAIN_temp1_length=0;
    
	if(document.${formName}.TUNTUTAN_MAIN_temp1 != null){
		if(document.${formName}.TUNTUTAN_MAIN_temp1.length>0){
			TUNTUTAN_MAIN_temp1_length = document.${formName}.TUNTUTAN_MAIN_temp1.length;
		}else{
			TUNTUTAN_MAIN_temp1_length = 1;
		}
	}

    if(TUNTUTAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUNTUTAN_MAIN.length > 1 ){
		for (t = 0; t < TUNTUTAN_MAIN_temp1_length; t++){	
    		document.${formName}.TUNTUTAN_MAIN_temp1[t].value = document.${formName}.txtUlasanTUNTUTAN_MAIN[t].value;
		}
	}else if(TUNTUTAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUNTUTAN_MAIN.length < 1 ){
		for (t = 0; t < TUNTUTAN_MAIN_temp1_length; t++){	
    		document.${formName}.TUNTUTAN_MAIN_temp1[0].value = document.${formName}.txtUlasanTUNTUTAN_MAIN.value;
    	}
	}else if(TUNTUTAN_MAIN_temp1_length == 1){
		document.${formName}.TUNTUTAN_MAIN_temp1.value = document.${formName}.txtUlasanTUNTUTAN_MAIN.value;
	}
	
	
		
}



function simpan(id_siasatan)
{
var c = 0;

if(document.${formName}.validation_field != null  )
{

   if (document.${formName}.validation_field.length == null)
   {	
    
   if(document.${formName}.validation_field.value == "invalid")
   {  
   
   c++;	
   } 
   	
   } 
   
   else 
   {
   
        for (i = 0; i < document.${formName}.validation_field.length; i++)
		{		
			if(document.${formName}.validation_field[i].value == "invalid")
			{
			
               c++;	 
			}  	
        }
    }	
}


if(c>0){
alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
return;
}
else
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "Agensi";
	document.${formName}.point.value = "Agensi";	
	document.${formName}.submit();
	}
}
}


function kemaskini(id_siasatan)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "Agensi";
	document.${formName}.point.value = "txtTuntutanTuanTanah";
	document.${formName}.submit();
	
}


function batal(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "Agensi";
	document.${formName}.point.value = "txtTuntutanTuanTanah";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
}


function hapus(id_siasatan)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "Agensi";
	document.${formName}.point.value = "txtTuntutanTuanTanah";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.submit();
	}
}


function validateModal(elmnt,content,content2) {
 //   alert(content)	
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != "")
	{
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	}
	else
	{
	elmnt.value ="";
	return;
	}
	
}


function tuantanah(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "TuanTanah";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function agensi(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Agensi";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function tuntutan(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function bantahan(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Bantahan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}


function status(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Status_Siasatan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}






function nilaian(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Nilaian";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function keputusan(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		window.location.hash=id;
        goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}


function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{
	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (my_form.value.length >= maxLen) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
my_form.value = my_form.value.substring(0, maxLen);
       maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");
}


function nota(id_siasatan)
{
    var url = "../servlet/ekptg.report.ppt.NotaSiasatanPB?id_siasatan="+id_siasatan;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}
function screen5(id_permohonan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.submit();
}

function PB(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "PB";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function check_length_tuntutan(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{
//alert("A");
	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
				 //  alert("B:"+my_form.value);
				   if(my_form.value == "" && mandatory == "yes")
				   {
				   lepas_or_xlepas = 2;
				   }	   
				   if(my_form.value == "")
				   {
				   document.getElementById(text_num).value = maxLen;
				   }   
				   if(lepas_or_xlepas == "2")
				   {	   
				   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
				   }
				   else
				   {	  
				   if (my_form.value.length >= maxLen) 
				   {
				   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
				   my_form.value = my_form.value.substring(0, maxLen);
				   maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }
	  // alert("x");

$jquery("#"+text_num).html(maxLen+"");
}

/*
TAMBAH FUNCTION--YATI
*/

function paparpbbantahan(id_hakmilik,id_hakmilikpb,id_pihakberkepentingan,status_bantahan,id_permohonan,flag_skrin)
{
	try
	{
		if(flag_skrin=="bantahan_mahkamah")
		{			
			window.opener.papar(id_hakmilik,id_hakmilikpb,id_pihakberkepentingan,status_bantahan);
		}		
	}
	catch (err) {}
    window.close();
    return false;
}

function refresh_Popup(id_hakmilik,flag_skrin)
{

	
	$jquery(document).ready(function()
		{
			var refreshId = setInterval( function()
			{
			
				document.${formName}.command.value = "refresh_Popup";	
				document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianPB?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin;
				document.${formName}.submit();
			
			}, 3000);
		});
		
}

function Simpan_Borang_Popup(id_hakmilik,flag_skrin,id_hakmilikpb)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {	
	document.${formName}.command.value = "Simpan_BorangS";	
	//document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianPB?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&id_hakmilikpb="+id_hakmilikpb;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();
	
	
		if(flag_skrin=="skrin_hakmilik_pb_sek8" && (id_hakmilik!="" && id_hakmilik!=null))
		{
			//window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_pb_sek8" && (id_hakmilikpb!="" && id_hakmilikpb!=null) )
		{
			window.opener.paparpb(id_hakmilikpb);
		}
		else if(flag_skrin=="skrin_pb_siasatan_sek8"  && (id_hakmilikpb!="" && id_hakmilikpb!=null))
		{
			window.opener.paparpb(id_hakmilikpb);
		}		
	}
}


if('$tutup_skrin_popup' == "yes")
{
	kembaliKeSkrinUtama('$id_permohonan');
}

function paparpbpopup(id_hakmilikpb,flag_skrin)
{
	try
	{
		if(flag_skrin=="skrin_hakmilik_pb_sek8")
		{			
			window.opener.paparpb(id_hakmilikpb);
		}
		else if(flag_skrin=="skrin_pb_sek8" || flag_skrin=="skrin_pb_PU")
		{
			window.opener.paparpb(id_hakmilikpb);
			
		}		
		else if(flag_skrin=="skrin_pb_siasatan_sek8")
		{
			window.opener.paparpb(id_hakmilikpb);
		}
		else if(flag_skrin=="skrin_sementara_pb_sek8")
		{
			
			window.opener.viewPB(id_hakmilikpb);
		}
	}
	catch (err) {}
    window.close();
    return false;
}

function transferHakmilik() 
 {	
 	document.${formName}.command.value = "transfer";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupHakmilik?&id_negeri="+'$id_negeri'+"&id_daerah="+'$id_daerah';
	document.${formName}.submit();			
				
 }	

function paparHakmilik(id_permohonan) 
 {	
 	document.${formName}.id_mohon_selected.value = id_permohonan;
	document.${formName}.command.value = "paparHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupHakmilik?&id_negeri="+'$id_negeri'+"&id_daerah="+'$id_daerah';
	document.${formName}.submit();			
				
 }	

function carian() 
 {	
	document.${formName}.command.value = "cari";
	document.${formName}.submit();			
				
 }	
 
 function kosongkan() 
 {
	document.${formName}.NO_PB.value = "";
	document.${formName}.NAMA_PB.value = "";
	document.${formName}.command.value = "cari";
	document.${formName}.submit();							
 }	



function paparHakmilik(id_hakmilik,flag_skrin){
	try {
		if(flag_skrin=="daftar_sek8")
		{
		window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_hakmilik_sek8")
		{
		window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_list_hakmilik_pb_sek8")
		{
		window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_hakmilik_pb_sek8")
		{
		window.opener.viewHM(id_hakmilik);	
		}
	}
	catch (err) {}
    window.close();
    return false;
}

function tambahPbPopup(id_hakmilik,flag_skrin)
{
	try {
		if(flag_skrin=="skrin_list_hakmilik_pb_sek8")
		{
			window.opener.tambahWakil(id_hakmilik);
		}
	}
	catch (err) {}
    window.close();
    return false;
}



function kembaliKeSkrinUtama(id_permohonan) {
	
	try {
		//simpanDisemak(ID_PLA);
        window.opener.HandlePopup_from_copy_hakmilik(id_permohonan);		
    }
    catch (err) {}
    window.close();
    return false;
	document.${formName}.cmdKembaliSkrinUtama.value = "Sila Tunggu....";		
}




function doCheckAll1(sudahbatal){  
var cc = 0;  
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
		cc++;
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = false;

        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}


function doCheckAllBorangG(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangG.checked == true){
        if (document.${formName}.borangG.length == null){
		cc++;
            document.${formName}.borangG.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangG.length; i++){
                document.${formName}.borangG[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangG.length == null){
            document.${formName}.borangG.checked = false;


        } else {
            for (i = 0; i < document.${formName}.borangG.length; i++){
                document.${formName}.borangG[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}

function doCheckAllBorangE(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangE.checked == true){
        if (document.${formName}.borangE.length == null){
		cc++;
            document.${formName}.borangE.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangE.length; i++){
                document.${formName}.borangE[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangE.length == null){
            document.${formName}.borangE.checked = false;

        } else {
            for (i = 0; i < document.${formName}.borangE.length; i++){
                document.${formName}.borangE[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}



function doCheckAllBorangK(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangK.checked == true){
        if (document.${formName}.borangK.length == null){
		cc++;
            document.${formName}.borangK.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangK.length; i++){
                document.${formName}.borangK[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangK.length == null){
            document.${formName}.borangK.checked = false;

        } else {
            for (i = 0; i < document.${formName}.borangK.length; i++){
                document.${formName}.borangK[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}





function doUpdateCheckAll1(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all1.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1.checked = true;
	  }
	  
	  
	 
	
}



function doUpdateCheckAllBorangG(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangG.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangG.length; i++)
	  {
      if (document.${formName}.borangG[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangG.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangG.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangG.checked = true;
	  }
	  
	  
	  
	  	
	
}

function doUpdateCheckAllBorangE(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangE.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangE.length; i++)
	  {
      if (document.${formName}.borangE[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangE.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangE.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangE.checked = true;
	  }
	  
	  
	
}



function doUpdateCheckAllBorangK(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangK.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangK.length; i++)
	  {
      if (document.${formName}.borangK[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangK.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangK.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangK.checked = true;
	  }
	  
	  
	
}


function popupPemegangAmanah_popupPB(id_hakmilik,id_hakmilikpb,flag_skrin){

	var w = "600";
	var h = "400";
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	var url = "../${securityToken}/ekptg.view.ppt.FrmPopupPemegangAmanah_PopupPB?id_hakmilik="+id_hakmilik+"&id_hakmilikpb="+id_hakmilikpb+"&flag_skrin="+flag_skrin;
		
	var hWnd = window.open(url, "Daftar Pemegang Amanah", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}

function popupCarianPB(id_hakmilik,flag_skrin,id_hakmilikpb)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianPB?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&id_hakmilikpb="+id_hakmilikpb+"&flag_tuntutan=Y";
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}


function popupCarianHakmilik(id_permohonan,flag_skrin)
{ //PPT-19
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
	
	screen5(id_permohonan);
	
}

function maklumatsiasatan(id_siasatan)
{ //PPT-24

	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "TuanTanah";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function kehadiran(id_siasatan)
{	//PPT-24 
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
	document.${formName}.submit();
}

function papar(id_siasatan)
{ //PPT-24 
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();
	
}

</script>
  
