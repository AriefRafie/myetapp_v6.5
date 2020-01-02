
#parse("app/ppt/paging_penarikanbalik.jsp")



#set($id_tanahumum = "")
#set($txtnorujukan = "")
#set($txtnamanegeri = "")
#set($txtnamadaerah = "")
#set($txtnamajajahan = "")
#set($STATUS_LAPORAN = "")
#set($txdTarikhKemasukan = "")
#set($txdTarikhPemeriksaan = "")
#set($txtbilsyit = "")
#set($sorJenisTanah = "")
#set($sorKedudukan = "")
#set($txtNoGazet = "")
#set($sorJenisGazet = "")
#set($txtNoGazetDaerah = "")










#foreach($maklumatam in $maklumat_am_tanah_permohonan)
#set($txdTarikhKemasukan = $maklumatam.TARIKH_KEMASUKAN)
#set($txdTarikhPemeriksaan = $maklumatam.TARIKH_PEMERIKSAAN)
#set($txtbilsyit = $maklumatam.PERIHAL_SYIT)
#set($sorJenisTanah = $maklumatam.FLAG_JENIS_TANAH)
#set($txtNoGazet = $maklumatam.NO_GAZET)
#set($txtNoGazetDaerah = $maklumatam.NO_GAZET_DAERAH)
#if($maklumatam.FLAG_DLM_SIMPANAN == "1" && $maklumatam.FLAG_LUAR_SIMPANAN != "1")
#set($sorKedudukan = "2")
#elseif($maklumatam.FLAG_LUAR_SIMPANAN == "1" && $maklumatam.FLAG_DLM_SIMPANAN != "1")
#set($sorKedudukan = "1")
#else
#set($sorKedudukan = "")
#end
#if($maklumatam.FLAG_DLM_MAJLIS == "1" && $maklumatam.FLAG_LUAR_MAJLIS != "1")
#set($sorJenisGazet = "2")
#elseif($maklumatam.FLAG_LUAR_MAJLIS == "1" && $maklumatam.FLAG_DLM_MAJLIS != "1")
#set($sorJenisGazet = "1")
#else
#set($sorJenisGazet = "")
#end
#end




#foreach($listmaklumat in $maklumat_hakmilik)
#set($id_hakmilik = $listmaklumat.ID_HAKMILIK)
#set($txtnorujukan = $listmaklumat.NO_PT)
#set($txtnamanegeri = $listmaklumat.NAMA_NEGERI)
#set($txtnamadaerah = $listmaklumat.NAMA_DAERAH)
#set($txtnamajajahan = $listmaklumat.NAMA_MUKIM)
#set($STATUS_LAPORAN = $listmaklumat.STATUS_LAPORAN)
#end



#foreach($maklumatam in $maklumat_am_tanah)
#set($txdTarikhKemasukan = $maklumatam.TARIKH_KEMASUKAN)
#set($txdTarikhPemeriksaan = $maklumatam.TARIKH_PEMERIKSAAN)
#set($txtbilsyit = $maklumatam.PERIHAL_SYIT)
#set($sorJenisTanah = $maklumatam.FLAG_JENIS_TANAH)
#set($txtNoGazet = $maklumatam.NO_GAZET)
#set($txtNoGazetDaerah = $maklumatam.NO_GAZET_DAERAH)
#set($id_tanahumum = $maklumatam.ID_TANAHUMUM)
#if($maklumatam.FLAG_DLM_SIMPANAN == "1" && $maklumatam.FLAG_LUAR_SIMPANAN != "1")
#set($sorKedudukan = "2")
#elseif($maklumatam.FLAG_LUAR_SIMPANAN == "1" && $maklumatam.FLAG_DLM_SIMPANAN != "1")
#set($sorKedudukan = "1")
#else
#set($sorKedudukan = "")
#end

#if($maklumatam.FLAG_DLM_MAJLIS == "1" && $maklumatam.FLAG_LUAR_MAJLIS != "1")
#set($sorJenisGazet = "2")
#elseif($maklumatam.FLAG_LUAR_MAJLIS == "1" && $maklumatam.FLAG_DLM_MAJLIS != "1")
#set($sorJenisGazet = "1")
#else
#set($sorJenisGazet = "")
#end

#end


#if($txdTarikhKemasukan == "" && $txdTarikhPemeriksaan == "" && $txtbilsyit == "" && $sorJenisTanah == "" && $txtNoGazet == ""
&& $txtNoGazetDaerah == "" && $sorKedudukan == "" && $sorJenisGazet == "")

#foreach($mak in $senarai_laporan_tanah)
#set($txtbilsyit = $mak.NO_SYIT)
#end

#set($txdTarikhKemasukan = $!current_date)

#end



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
  <tr>
    <td> #parse("app/ppt/header_ppt.jsp")</td>
  </tr>
  <tr>
    <td>
    
     <fieldset>
    
    
    <table width="100%">
    
    <tr>
    <td>
   
    <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
         <li class="TabbedPanelsTab" tabindex="0" onclick="screen2('$id_permohonan','$id_pembatalan')" >Senarai Lot Penarikan</li>
        <li class="TabbedPanelsTab" tabindex="0" id="maklumat_am" onclick="LaporanTanah('$!id_hakmilik','$!id_pembatalan')">Maklumat Am Tanah</li>
        <li class="TabbedPanelsTab" tabindex="0" onclick="PerihalTanah('$!id_hakmilik','$!id_pembatalan')">Perihal Tanah</li>
         <li class="TabbedPanelsTab" tabindex="0" onclick="LaporanKerosakan('$!id_hakmilik','$!id_pembatalan')">Laporan Kerosakan</li>
      </ul>
      <div class="TabbedPanelsContentGroup">
       <div class="TabbedPanelsContent">
       </div>
        <div class="TabbedPanelsContent"><table width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No. Lot</td>
    <td width="1%">:</td>
    <td width="70%">$txtnorujukan</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Negeri</td>
    <td>:</td>
    <td>$txtnamanegeri</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Daerah/Jajahan</td>
    <td>:</td>
    <td>$txtnamadaerah</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Bandar/Pekan/Mukim</td>
    <td>:</td>
    <td>$txtnamajajahan</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Status Laporan Tanah</td>
    <td>:</td>
    <td>$STATUS_LAPORAN</td>
  </tr>
  <tr style="display:none">
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Tarikh Kemasukan</td>
    <td>:</td>
    <td>
    <input name="txdTarikhKemasukan" type="text" id="txdTarikhKemasukan" size="10" maxlength="10"   value="$!txdTarikhKemasukan" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhKemasukan_check','yes','kemasukan','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />   
    #if($readmode == "edit")    
      <a href="javascript:displayDatePicker('txdTarikhKemasukan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>  
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>  
      #end
       <span id="txdTarikhKemasukan_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr>
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Tarikh Pemeriksaan Tanah Dibuat</td>
    <td>:</td>
    <td>
    <input name="txdTarikhPemeriksaan" type="text" id="txdTarikhPemeriksaan" size="10" maxlength="10"   value="$!txdTarikhPemeriksaan" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhPemeriksaan_check','yes','pemeriksaan','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />  
    #if($readmode == "edit")     
      <a href="javascript:displayDatePicker('txdTarikhPemeriksaan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>  
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
      #end  
       <span id="txdTarikhPemeriksaan_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr style="display:none">
    <td valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td valign="top">Bilangan Syit Piawai</td>
    <td valign="top">:</td>
    <td>
     
      
        
        <input name="txtbilsyit" type="text" id="txtbilsyit" size="50" maxlength="100" value="$txtbilsyit" style="" onBlur="checking_validation(this,'txtbilsyit_check','yes','syit piawai','normal')" $readonlymode class = "$disabledmode" 
        onkeyup="checking_validation(this,'txtbilsyit_check','yes','syit piawait','normal')" >
        
        
             
         <span id="txtbilsyit_check" class="alert_msg" ></span>    </td>
  </tr>
  <tr style="display:none">
    <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
    <td>Jenis Tanah</td>
    <td>:</td>
    <td>
    
     #if($readmode == "view" )       
              #if($sorJenisTanah == "1")
              #set($JenisTanah = "TANAH BANDAR")            
              #elseif($sorJenisTanah == "2")
              #set($JenisTanah = "TANAH PEKAN")  
              #elseif($sorJenisTanah == "3")
              #set($JenisTanah = "TANAH DESA")                          
              #else
              #set($JenisTanah = "")
              #end
              <input name="JenisTanah" type="text" class = "disabled" id="JenisTanah" value="$JenisTanah" size="50"  readonly $check1>              
              #else
              <table width="100%">
              <tr>
              <td width="25%" >                    
              #if($sorJenisTanah == "1")
              #set($check1 = "checked")
              #set($check2 = "") 
              #set($check3 = "")            
              #elseif($sorJenisTanah == "2")
              #set($check2 = "checked")
              #set($check1 = "")
              #set($check3 = "")
              #elseif($sorJenisTanah == "3")
              #set($check2 = "")
              #set($check1 = "")
              #set($check3 = "checked")
              #else
              #set($check1 = "")
              #set($check2 = "")
              #set($check3 = "")
              #end 
<input type="radio" name="sorJenisTanah" id="sorJenisTanah" value="1" $check1 onClick="checking_validation(this,'sorJenisTanah_check','yes','jenis tanah','radio');">
Tanah Bandar</td>
<td width="25%">
 <input type="radio" name="sorJenisTanah" id="sorJenisTanah" value="2" $check2 onClick="checking_validation(this,'sorJenisTanah_check','yes','jenis tanah','radio');">
 Tanah Pekan</td>  
 <td width="50%">
 <input type="radio" name="sorJenisTanah" id="sorJenisTanah" value="3" $check3 onClick="checking_validation(this,'sorJenisTanah_check','yes','jenis tanah','radio');">
 Tanah Desa &nbsp;<span id="sorJenisTanah_check" class="alert_msg" ></span> </td>                 
                </tr>
                </table>
          #end    </td>
  </tr>
  <tr  style="display:none">
    <td>&nbsp;</td>
    <td>Kedudukan Tanah</td>
    <td>:</td>
    <td>
    
     #if($readmode == "view" )       
              #if($sorKedudukan == "1")
              #set($Kedudukan = "DI LUAR KAWASAN SIMPANAN MELAYU")   
              #set($check1 = "checked")
              #set($check2 = "")              
              #elseif($sorKedudukan == "2")
              #set($Kedudukan = "DI DALAM KAWASAN SIMPANAN MELAYU")  
              #set($check2 = "checked")
              #set($check1 = "")            
              #else
              #set($Kedudukan = "")
               #set($check1 = "")
              #set($check2 = "")
              #end
              <input name="Kedudukan" type="text" class = "disabled" id="Kedudukan" value="$Kedudukan" size="50"  readonly $check1>   
              <input type="radio" name="sorKedudukan" id="sorKedudukan" value="1" $check1 style="display:none" >
              <input type="radio" name="sorKedudukan" id="sorKedudukan" value="2" $check2 style="display:none" >           
              #else
              <table width="100%">
              <tr>
              <td width="100%" >                    
              #if($sorKedudukan == "1")
              #set($check1 = "checked")
              #set($check2 = "")             
              #elseif($sorKedudukan == "2")
              #set($check2 = "checked")
              #set($check1 = "")
              #else
              #set($check1 = "")
              #set($check2 = "")
              #end 
<input type="radio" name="sorKedudukan" id="sorKedudukan" value="1" $check1 onClick="checking_validation(this,'sorKedudukan_check','no','kedudukan tanah','radio');">
Di Luar Kawasan Simpanan Melayu</td>
</tr>
<tr>
<td width="100%">
 <input type="radio" name="sorKedudukan" id="sorKedudukan" value="2" $check2 onClick="checking_validation(this,'sorKedudukan_check','no','kedudukan tanah','radio');">
 Di Dalam Kawasan Simpanan Melayu &nbsp;   <span id="sorKedudukan_check" class="alert_msg" ></span> </td>                 
                </tr>
                </table>
          #end    </td>
  </tr>
 <div id="warta_1" ></div>
  <tr   style="display:none" >
    <td>&nbsp;</td>
    <td>No. Warta</td>
    <td>:</td>
    <td>
    
    <input name="txtNoGazet" type="text" id="txtNoGazet" size="25" maxlength="25" value="$txtNoGazet"  $readonlymode class = "$disabledmode" 
         >
      </td>
  </tr>
  
  <tr style="display:none">
    <td>&nbsp;</td>
    <td>Kedudukan Tanah</td>
    <td>:</td>
    <td>
    
     #if($readmode == "view" )       
              #if($sorJenisGazet == "1")
              #set($JenisGazet = "DI LUAR KAWASAN MAJLIS DAERAH")
              #set($check1 = "checked")
              #set($check2 = "")             
              #elseif($sorJenisGazet == "2")
              #set($JenisGazet = "DI DALAM KAWASAN MAJLIS DAERAH") 
              #set($check2 = "checked")
              #set($check1 = "")             
              #else
              #set($JenisGazet = "")
              #set($check2 = "")
              #set($check1 = "")
              #end
              <input name="JenisGazet" type="text" class = "disabled" id="JenisGazet" value="$JenisGazet" size="50"  readonly $check1>              
                 <input type="radio" name="sorJenisGazet" id="sorJenisGazet" value="1" $check1 style="display:none" >
                 <input type="radio" name="sorJenisGazet" id="sorJenisGazet" value="2" $check2 style="display:none" >
                              
                         
              #else
              <table width="100%">
              <tr>
              <td width="100%" >                    
              #if($sorJenisGazet == "1")
              #set($check1 = "checked")
              #set($check2 = "")             
              #elseif($sorJenisGazet == "2")
              #set($check2 = "checked")
              #set($check1 = "")
              #else
              #set($check1 = "")
              #set($check2 = "")
              #end 
<input type="radio" name="sorJenisGazet" id="sorJenisGazet" value="1" $check1 onClick="checking_validation(this,'sorJenisGazet_check','no','jenis warta','radio');">
Di Luar Kawasan Majlis Daerah</td>
</tr>
<tr>
<td width="100%">
 <input type="radio" name="sorJenisGazet" id="sorJenisGazet" value="2" $check2 onClick="checking_validation(this,'sorJenisGazet_check','no','jenis warta','radio');">
 Di Dalam Kawasan Majlis Daerah &nbsp;  <span id="sorJenisGazet_check" class="alert_msg" ></span> </td>                 
                </tr>
                </table>
          #end    </td>
  </tr>
  
  <div id="warta_2" ></div>
  <tr style="display:none" >
    <td>&nbsp;</td>
    <td>No. Warta</td>
    <td>:</td>
    <td>
    
    <input name="txtNoGazetDaerah" type="text" id="txtNoGazetDaerah" size="25" maxlength="25" value="$txtNoGazetDaerah" style=""  $readonlymode class = "$disabledmode" 
        >
      <span id="txtNoGazetDaerah_check" class="alert_msg" ></span>    </td>
  </tr>
  
  
</table>
</div>
        <div class="TabbedPanelsContent"></div>
      </div>
    </div>
    
  
    </td>
    </tr>
    <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini()">
      </label>
      <label>
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan()">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal()">
      </label>
    #end  
     
       #if($id_pembatalan != "")
         #if($readmode == "view")
          #if($STATUS_LAPORAN != "SELESAI")
          <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Selesai Laporan Tanah"  onClick="selesai()">
      </label>  
      #end
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  
      </label>   
      #end 
      #end
      
       
      
     </div>  
     <div class="TabbedPanelsContent">     
        </div>
          <div class="TabbedPanelsContent">
       </div>
         </td>
  </tr>
  
  
   #if($readmode == "edit")
  <tr>
    <td>#parse("app/ppt/PenarikanBalik_Alert.jsp")</td>
  </tr>
  #else
   <tr>
    <td></td>
  </tr>  
  #end
  
  
    </table>
    </fieldset>
    </td>
  </tr>
  
</table>


<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="laporantanah('$id_tanahumum','$user_name')"><font color="blue">Laporan Tanah</font></a></td>
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
 
  <script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});

var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm;

function submitForm() 
{

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

checking_validation(document.${formName}.sorKedudukan,'sorKedudukan_check','no','kedudukan tanah','radio');
checking_validation(document.${formName}.sorJenisGazet,'sorJenisGazet_check','no','jenis warta','radio');
checking_validation(document.${formName}.txdTarikhKemasukan,'txdTarikhKemasukan_check','no','kemasukan','tarikh');
checking_validation(document.${formName}.txdTarikhPemeriksaan,'txdTarikhPemeriksaan_check','yes','pemeriksaan','tarikh');
checking_validation(document.${formName}.txtbilsyit,'txtbilsyit_check','no','syit piawai','normal');
checking_validation(document.${formName}.sorJenisTanah,'sorJenisTanah_check','no','jenis tanah','radio');


}



function doCheckAll1(){    
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
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
}
function doUpdateCheckAll1(){  
var c = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
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

function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}


function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
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




function checking_validation(field,point,mandatory,value_field,jenis_field){	



   	var lepas_or_xlepas = 1;
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	      DateField.select();		
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	 
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
	   /*	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	     $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
	   }
	   else
	   {
	  
	   /*
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   
	     $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini");
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   	$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	//   DateField.focus();
	$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+" dengan betul");
	 
	   
	   }
	   
	   }
	   
	// 
	   if(jenis_field == "normal")
	   {
	   
	  
		
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   
	   
	   if(lepas_or_xlepas == "2")
	   {	  
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >"); 	
	   }
	   else
	   {	   
	     $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }   	   
	   }
	   
	   
	   
	  
	    if(jenis_field == "radio")
	   {
	 
	    var r = 0;
		if(field.length > 1)
		{     
			  for (i = 0; i < field.length; i++)
			  {
			  if (field[i].checked == true)
			  {	 
			  r++
			  }
			  }  
		}
		else
		{
		if (field.checked == true)
		{	 
		r++;
		}	 	
		}  
	   
	     
	   if((r == 0) && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	  
	   if(lepas_or_xlepas == "2")
	   {
	   /*
	   document.${formName}.alert_message.value  = "Sila pilih "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
		
	   }
	   else
	   {
	   /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
	   
	   if(document.${formName}.sorKedudukan[0].checked == true)
	   {
	   document.getElementById('warta_1').style.display="none"; 
	   document.getElementById('txtNoGazet').value=""; 

	   }	   
	   if(document.${formName}.sorKedudukan[1].checked == true )
	   {	   
	   document.getElementById('warta_1').style.display="";
	   if(document.${formName}.txtNoGazet.value == "")
	   {
	  	  		    
	      }
	   else
	   {
	  
	   }
	   }	   
	   if(document.${formName}.sorKedudukan[1].checked == false && document.${formName}.sorKedudukan[0].checked == false)
	   {
	   document.getElementById('warta_1').style.display="none";
	   document.getElementById('txtNoGazet').value=""; 	  
	   }
	   
	   if(document.${formName}.sorJenisGazet[0].checked == true)
	   {
	   document.getElementById('warta_2').style.display="none"; 
	   document.getElementById('txtNoGazetDaerah').value=""; 
	   $jquery("#txtNoGazetDaerah_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
	   }	   
	   if(document.${formName}.sorJenisGazet[1].checked == true )
	   {
	   document.getElementById('warta_2').style.display="";
	   if(document.${formName}.txtNoGazetDaerah.value == "")
	   {
	   $jquery("#txtNoGazetDaerah_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	    		       }
	   else
	   {
	   $jquery("#txtNoGazetDaerah_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
	   }	   
	   }
	   if(document.${formName}.sorJenisGazet[1].checked == false && document.${formName}.sorJenisGazet[0].checked == false)
	   {
	   document.getElementById('warta_2').style.display="none";
	   document.getElementById('txtNoGazetDaerah').value=""; 	  
	   }	
		
	   }
	   
	   	   
	   }
	   
	   
	
	   
	   
	   
	    if(jenis_field == "file") 
	    {
	   	var allBlank = true;
		if(document.${formName}.fileupload!=null)
		{
		
		for( var i=0,e=document.${formName}.fileupload;i<e.length; i++ )
		{			
		if( e[i].type=='file' )
		{	
		if( e[i].value.length  )
		{
		
		allBlank=false;	
		}	
		}	
		}
		
		}
		else
		{
		allBlank=false;	
		}
		
		
		if(allBlank == true)
	    {/*
		   document.${formName}.alert_message.value  = "Sila masukkan dokumen terlebih dahulu";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	*/
		   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
		
	    }
		else
		{
		 /*  document.${formName}.alert_message.value  = "";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		*/
		$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
		
		
		}
		
		
	   
	   }
	   
	   
	   
	
}




function PembatalanAmbilTanahLotUnit()
{
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahLotUnit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "lot_dibatal";
	document.${formName}.point.value = "lot_dibatal";
	document.${formName}.submit();

}



function LaporanTanah(id_hakmilik,id_pembatalan)
{
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Maklumat_Am";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
}




function simpan()
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
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Maklumat_Am";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();
	}
	}
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Maklumat_Am";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();
	}
}


function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Maklumat_Am";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();

	}
}

function kemaskini()
{
    document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Maklumat_Am";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "txdTarikhKemasukan";
	document.${formName}.submit();


}

function PerihalTanah(id_hakmilik,id_pembatalan)
{
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Perihal_Tanah";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function LaporanKerosakan(id_hakmilik,id_pembatalan)
{
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Laporan_Kerosakan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
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




function selesai()
{
    document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Maklumat_Am";
	document.${formName}.subminor_command.value = "UpdateSuburusan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";
	document.${formName}.submit();
}

function laporantanah(id_tanah,nama_pegawai)
{



	


var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_tanah="+id_tanah+"&report=laporan_tanah&id_permohonan="+document.${formName}.id_permohonan.value;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}







  </script>
