





 #if($senarai_hakmilik_batal.size()!=0)
             #foreach($listb in $senarai_hakmilik_batal) 
              
                       <input name="lot_ambilX" id="lot_ambilX"  value="$listb.LUAS_AMBIL_EDIT" type="hidden" >
                       <input name="lot_ambil_asalX" id="lot_ambil_asalX"  value="$listb.LUAS_AMBIL_ASAL" type="hidden" >
                       <input name="luas_lotX" id="luas_lotX"  value="$listb.LUAS_LOT" type="hidden" >
                       <input name="id_hakmilik_luasX" id="id_hakmilik_luasX"  style="width:100%" value="$listb.ID_HAKMILIK" type="hidden" >
                       <input name="id_lot_tarikX" id="id_lot_tarikX"  value="$listb.ID_PENARIKANHAKMILIK" type="hidden" > 
                       <input name="lot_tarikX" id="lot_tarikX" value="$listb.LUAS_LOT_TARIK_EDIT" type="hidden"  >   
                 
             
             #end
             #end

#parse("app/ppt/paging_penarikanbalik.jsp")


#set($txtNoRujMMK = "")
#set($txdTarikhMMK = "") 
#set($txdTarikhHantar = "") 
#set($txdTarikhKeputusan = "") 
#set($txdTarikhTerimaKeputusan = "") 
#set($socKeputusan = "") 
#set($txtUlasan = "") 
#set($STATUS_SEMAKAN = "")
#set($id_mmkkeputusan = "")
#set($flag_mmk = "")


#foreach($view in $maklumat_penyediaan)
#set($id_mmk = $view.ID_MMK)
#set($id_pembatalan = $view.ID_PEMBATALAN)
#set($STATUS_SEMAKAN = $view.STATUS_SEMAKAN)
#set($flag_mmk = $view.FLAG_MMK)
#end




#foreach($mk in $maklumat_keputusan)
#set($txtNoRujMMK = $mk.NO_RUJMMK)
#set($txdTarikhMMK = $mk.TARIKH_MMK) 
#set($txdTarikhHantar = $mk.TARIKH_HANTAR) 
#set($txdTarikhKeputusan = $mk.TARIKH_KEPUTUSAN) 
#set($txdTarikhTerimaKeputusan = $mk.TARIKH_TERIMA) 
#set($socKeputusan = $mk.STATUS_KEPUTUSAN) 
#set($txtUlasan = $mk.ULASAN) 
#set($id_mmkkeputusan = $mk.ID_MMKKEPUTUSAN)
#end


#foreach($sem in $maklumat_semakan)
#set($socKeputusanMMK = $sem.STATUS_SEMAKAN)
#set($txtUlasanMMK = $sem.ULASAN)
#set($txdTarikhSemakanMMK = $sem.TARIKH_SEMAK)
#set($txdTarikhHantarMMK = $sem.TARIKH_HANTAR)
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
        <li class="TabbedPanelsTab" tabindex="0" id="penyediaan" onClick="penyediaan('$id_permohonan','$id_pembatalan')">Penyediaan</li>
        <li class="TabbedPanelsTab" tabindex="0" id="semakan_mmk" style="display:none" onClick="semakan('$id_permohonan','$id_pembatalan')">Semakan</li>
         #if($STATUS_SEMAKAN != "")
        <li class="TabbedPanelsTab" tabindex="0" id="keputusan_mmk" style="display:none" onClick="keputusan('$id_permohonan','$id_pembatalan')">Keputusan PBN</li>
        #end
      </ul>
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent"></div>
        <div class="TabbedPanelsContent">
          
        </div>
        <div class="TabbedPanelsContent">
        
        
        <table width="100%">
  <tr>
    <td>
    <fieldset>
    <legend>Maklumat MMK/MB/KM/LC</legend>
    <table width="100%">
  <tr>
    <td >
    <table width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="23%">No. Ruj MMK</td>
    <td width="1%">:</td>
    <td width="75%">
    <input name="txtNoRujMMK" type="text" id="txtNoRujMMK" size="30" maxlength="50" value="$txtNoRujMMK" style="" onBlur="checking_validation(this,'txtNoRujMMK_check','no','no rujukan MMK','normal')" $readonlymode class = "$disabledmode" 
        onkeyup="checking_validation(this,'txtNoRujMMK_check','no','no rujukan MMK','normal')" >
        <span id="txtNoRujMMK_check" class="alert_msg"></span>    </td>
  </tr>

  <tr>
   <td width="1%">&nbsp;</td>
    <td width="23%">Tarikh Kelulusan MMK</td>
    <td width="1%">:</td>
    <td width="75%">
     <input name="txdTarikhMMK" type="text" id="txdTarikhMMK" size="10" maxlength="10"   value="$!txdTarikhMMK" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhMMK_check','no','kelulusan MMK','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" /> 
       #if($readmode == "edit")       
      <a href="javascript:displayDatePicker('txdTarikhMMK',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>   
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>     
      #end
       <span id="txdTarikhMMK_check" class="alert_msg" ></span>    </td>
  </tr>
</table>
    </td>
  </tr>
</table>

    </fieldset>
    </td>
  </tr>
  <tr>
    <td>
    <fieldset>
    <table width="100%">
      <tr >
        <td width="1%" >#parse("app/ppt/mandatory_pembatalan.jsp")</td>      
        <td width="23%" >Tarikh Hantar Ke PTG</td>      
        <td width="1%" >:</td>      
        <td width="75%" >
         <input name="txdTarikhHantar" type="text" id="txdTarikhHantar" size="10" maxlength="10"   value="$!txdTarikhHantar" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhHantar_check','yes','hantar','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />  
           #if($readmode == "edit")      
      <a href="javascript:displayDatePicker('txdTarikhHantar',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>   
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>    
      #end 
       <span id="txdTarikhHantar_check" class="alert_msg" ></span>        </td>      
      </tr>
      
       <tr style="display:none">
        <td width="1%" >#parse("app/ppt/mandatory_pembatalan.jsp")</td>      
        <td width="23%" >Tarikh Keputusan</td>      
        <td width="1%" >:</td>      
        <td width="75%" > <input name="txdTarikhKeputusan" type="text" id="txdTarikhKeputusan" size="10" maxlength="10"   value="$!txdTarikhKeputusan" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhKeputusan_check','no','keputusan','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />  
          #if($readmode == "edit")      
      <a href="javascript:displayDatePicker('txdTarikhKeputusan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> 
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>    
      #end   
       <span id="txdTarikhKeputusan_check" class="alert_msg"></span>  </td>      
      </tr>
      
       <tr>
         <td width="1%" >#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="23%" >Tarikh Terima Keputusan</td>      
        <td width="1%" >:</td>      
        <td width="75%" > <input name="txdTarikhTerimaKeputusan" type="text" id="txdTarikhTerimaKeputusan" size="10" maxlength="10"   value="$!txdTarikhTerimaKeputusan" onblur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhTerimaKeputusan_check','yes','terima keputusan','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />  
          #if($readmode == "edit")      
      <a href="javascript:displayDatePicker('txdTarikhTerimaKeputusan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>  
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>      
      #end
       <span id="txdTarikhTerimaKeputusan_check" class="alert_msg" ></span>  </td>      
      </tr>
      
       <tr>
        <td width="1%" >#parse("app/ppt/mandatory_pembatalan.jsp")</td>      
        <td width="23%" >Keputusan</td>      
        <td width="1%" >:</td>      
        <td width="75%" >
        
           #if($readmode == "view" )
        
        
              #if($socKeputusan == "1")
              #set($keputusan = "DILULUSKAN")            
              #elseif($socKeputusan == "2")
              #set($keputusan = "DITANGGUH")
              #elseif($socKeputusan == "3")
              #set($keputusan = "DITOLAK")
              #else
              #set($keputusan = "")
              #end
               <input name="keputusan" type="text" id="keputusan" value="$keputusan" $check1  readonly class = "disabled">
             
                  
                  #else
                  
                  
                  
                  <table width="100%">
                  <tr>
                    <td width="15%" >
                    
              #if($socKeputusan == "1")
              #set($check1 = "checked")
              #set($check2 = "")
              #set($check3 = "")
              #elseif($socKeputusan == "2")
              #set($check2 = "checked")
              #set($check1 = "")
              #set($check3 = "")
              #elseif($socKeputusan == "3")
              #set($check2 = "")
              #set($check1 = "")
              #set($check3 = "checked")
              #else
              #set($check1 = "")
              #set($check2 = "")
              #set($check3 = "")
              #end
           
                    
<input type="radio" name="socKeputusan" id="socKeputusan" value="1" $check1 onClick="checking_validation(this,'socKeputusan_check','yes','keputusan','radio');">
Diluluskan</td>
                    <td width="15%">
 <input type="radio" name="socKeputusan" id="socKeputusan" value="2" $check2 onClick="checking_validation(this,'socKeputusan_check','yes','keputusan','radio');">
 Ditangguh</td>
                    <td width="70%">
 <input type="radio" name="socKeputusan" id="socKeputusan" value="3" $check3 onClick="checking_validation(this,'socKeputusan_check','yes','keputusan','radio');"> 
 Ditolak/Tidak Diluluskan  &nbsp; <span id="socKeputusan_check" class="alert_msg" ></span> </td>
                  </tr>
                </table>
          #end        </td>      
      </tr>
      
       <tr>
        <td width="1%" valign="top" >&nbsp;</td>      
        <td width="23%" valign="top" >Ulasan Keputusan</td>      
        <td width="1%" valign="top" >:</td>      
        <td width="75%" >
    
          <textarea name="txtUlasan" id="txtUlasan" cols="60"   rows="6" style=""         
         onBlur="check_length(this,'4000','txtUlasan_check','txtUlasan_num','normal','no','ulasan');"  
         onKeyup="check_length(this,'4000','txtUlasan_check','txtUlasan_num','normal','no','ulasan');" 
         onKeydown="check_length(this,'4000','txtUlasan_check','txtUlasan_num','normal','no','ulasan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtUlasan</textarea>       
       #if($readmode == "edit")           
       <div><span id="txtUlasan_num" style="color:blue;" ></span><span> Baki Aksara</span>       </div>
         #else
         <input name="txtUlasan_num" id="txtUlasan_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtUlasan_check" class="alert_msg" ></div>        </td>      
      </tr>
    </table>
    </fieldset>
    
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

        
        </div>
      </div>
    </div>
    
    </td>
  </tr>
  </table>
  </fieldset>
    
    </td>
  </tr>
  <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
    
    
    
     
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini()">
      
      <!--
      <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Penarikan Balik)" onClick="popupEtanah('$id_fail','$id_permohonan','TarikBalik','','$id_pembatalan')">
      -->
				
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">
      
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
       <!--
      <label> 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  
      </label>
      -->    
      #end
       <label></label>
     </div>      </td>
  </tr>
</table>


<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick=""><font color="blue">Kertas MMK/KM/MB/LC</font></a></td>
      </tr>           
    </table>
</fieldset>



  <input type="hidden" name="sub_command" id="sub_command" />
  <input type="hidden" name="subminor_command" id="subminor_command" />
  <input type="hidden" name="location" id="location" />
  <input type="hidden" name="point" id="point" />
  <input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$id_pembatalan" />
  <input type="hidden" name="screen_name" id="screen_name" value="s2" />
  <input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
  <input type="hidden" name="alert_message" id="alert_message" />
  <input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
  <input type="hidden" name="jumlah_semua" id="jumlah_semua" />
  <input type="hidden" name="id_mmk" id="id_mmk" value="$id_mmk" />
  <input type="hidden" name="id_mmkkeputusan" id="id_mmkkeputusan" value="$id_mmkkeputusan" />
  <input type="hidden" name="id_mmk" id="id_mmk" value="$id_mmk" />  
  <input type="hidden" name="txdTarikhSemakanMMK" id="txdTarikhSemakanMMK" value="$!txdTarikhSemakanMMK" />
  

<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});

var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm;

function popupEtanah(id_fail,id_permohonan,jenis_skrin,command,id_penarikan) {

	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&jenis_skrin="+jenis_skrin+"&command="+command+"&id_penarikan="+id_penarikan;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}

function submitForm() 
{
if('$flag_mmk' == "1" && ('$portal_role' == "(PPT)Pengarah" || '$portal_role' == "(PPT)PengarahUnit" || '$portal_role' == "(PPT)PenolongPengarah" || '$portal_role' == "(PPT)PenolongPengarahUnit" || '$portal_role' == "(PPT)KetuaPenolongPengarah" || '$portal_role' == "(PPT)KetuaPenolongPengarahUnit"))
{
document.getElementById('semakan_mmk').style.display = "block";
}
else if('$flag_mmk' == "2" && ('$portal_role' == "(PPT)Pengarah" || '$portal_role' == "(PPT)PengarahUnit" || '$portal_role' == "(PPT)PenolongPengarah" || '$portal_role' == "(PPT)PenolongPengarahUnit" || '$portal_role' == "(PPT)KetuaPenolongPengarah" || '$portal_role' == "(PPT)KetuaPenolongPengarahUnit"))
{
document.getElementById('semakan_mmk').style.display = "block";
document.getElementById('keputusan_mmk').style.display = "block";
}
else if('$flag_mmk' == "2" && ('$portal_role' != "(PPT)Pengarah" && '$portal_role' != "(PPT)PengarahUnit" && '$portal_role' != "(PPT)PenolongPengarah" && '$portal_role' != "(PPT)PenolongPengarahUnit" && '$portal_role' != "(PPT)KetuaPenolongPengarah" && '$portal_role' != "(PPT)KetuaPenolongPengarahUnit"))
{
document.getElementById('keputusan_mmk').style.display = "block";
}
else
{
document.getElementById('semakan_mmk').style.display = "none";
document.getElementById('keputusan_mmk').style.display = "none";
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



checking_validation(document.${formName}.txtNoRujMMK,'txtNoRujMMK_check','no','no rujukan MMK','normal');
checking_validation(document.${formName}.txdTarikhMMK,'txdTarikhMMK_check','no','kelulusan MMK','tarikh');
checking_validation(document.${formName}.txdTarikhHantar,'txdTarikhHantar_check','yes','hantar','tarikh');
checking_validation(document.${formName}.txdTarikhKeputusan,'txdTarikhKeputusan_check','no','keputusan','tarikh');
checking_validation(document.${formName}.txdTarikhTerimaKeputusan,'txdTarikhTerimaKeputusan_check','yes','terima keputusan','tarikh');
checking_validation(document.${formName}.socKeputusan,'socKeputusan_check','yes','keputusan','radio');
//checking_validation(document.${formName}.txtUlasan,'txtUlasan_check','yes','ulasan','normal');
check_length(document.${formName}.txtUlasan,'4000','txtUlasan_check','txtUlasan_num','normal','no','ulasan');



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
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "penyediaan";
	document.${formName}.point.value = "penyediaan";
	document.${formName}.submit();
	}
	}
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "penyediaan";
	document.${formName}.point.value = "penyediaan";
	document.${formName}.submit();
	}
}

function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "Batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "penyediaan";
	document.${formName}.point.value = "penyediaan";
	document.${formName}.submit();
	}
}

function kemaskini()
{
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "penyediaan";
	document.${formName}.point.value = "penyediaan";
	document.${formName}.submit();
}

function penyediaan(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Penyediaan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();

}

function semakan(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Semakan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();

}

function keputusan(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "MMK";
	document.${formName}.sub_command.value = "Keputusan";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();

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
	   
		  
	     // DateField.select();		
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
	   
	      var t_semakan  = document.${formName}.txdTarikhSemakanMMK.value;
		   if(t_semakan!="")
		   {
		   var dtx   = parseInt(t_semakan.substring(0,2),10);
		   var monx  = parseInt(t_semakan.substring(3,5),10)-1;
		   var yrx   = parseInt(t_semakan.substring(6,10),10);	   
		   var datex = new Date(yrx, monx, dtx);	 
			  if (datex > date)
			  {
			 /* 
			   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh semakan "+t_semakan+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh semakan "+t_semakan+"");		  
			  
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
			   
			    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
			  }   
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
	         }
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
	$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+" dengan betul");	
	 
	//   DateField.focus();
	   
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
	   /*
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
		
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
			
	   }
	   
	   	   
	   }
	  
	   
	   if(jenis_field == "drop")
	   {
	  
	   if((field.value == "" || field.value == "0") && mandatory == "yes")
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
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
		/*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */	
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
	    {
		/*
		   document.${formName}.alert_message.value  = "Sila masukkan dokumen terlebih dahulu";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		   */
		   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan dokumen terlebih dahulu");	
		
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
			
		
		
		}
		
		
	   
	   }
	   
	   
	   
	
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
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
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



  </script>
