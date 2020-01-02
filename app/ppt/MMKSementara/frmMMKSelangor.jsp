 ::::::::::::::::::::: 
    #foreach($default in $dataHeader)
    	#set($txttujuanTEMP = $default.tujuan)
    #end
    
    #if($mode=="view")
    #foreach($data in $dataMMK)
    	#set($txtTujuan = $data.tujuan)
    	#set($txtLatarBelakang = $data.latarbelakang)
    	#set($txtPerihalTanah = $data.perihal_tanah)
    	#set($txtNilaianTanah = $data.nilai_atas_tanah)
    	#set($txtSyor = $data.syor)
    	#set($txtUlasan = $data.ulasan_pengarah)
    	#set($txtKeputusan = $data.keputusan)  	    	
    	#set($txtPerihalPohon=$data.perihal_pohon)
    	#set($txtPeruntukan=$data.pengesahan_peruntukan)
    	#set($txtNamaTuanTanah=$data.nama_tuan_tanah)
    	#set($txtPerakuan=$data.perakuan_pentadbir_tnh)
    	#set($txtAsasPertimbangan=$data.asas_pertimbangan) 	
    	#set($txtAnggaranKos=$data.anggaran_kos)
    	#set($txtUlasanJT=$data.ulasan_jabteknikal)
    	#set($txtJawatankuasa=$data.jawatankuasa_tanah)
    	#set($txtPengecualian=$data.pengecualian_upah_ukur)
    	#set($txtHalLain=$data.hal_lain)
    	#set($txtKesimpulan=$data.kesimpulan)
    	#set($txtImplikasi=$data.implikasi)
    	#set($txtPenutup=$data.penutup)
    	#set($txtPandangan=$data.pandangan)
    	#set($txtPerakuanSetiausaha=$data.perakuan_setiausaha)
    	#set($txtButirAsas=$data.butir_asas)
    	#set($txtKeadaanTanah=$data.keadaan_tanah)
    	#set($txtButirTanah=$data.butir_tanah)
    	#set($txtKemudahanAsas=$data.kemudahan_asas)
    	#set($txtJenisPenggunaan=$data.jenis_penggunaan_tnh)
    	#set($txtLokasi=$data.lokasi_tanah)
    	#set($txtKedudukan=$data.kedudukan_tanah)
    	#set($txtKeadaan=$data.keadaan_tanah)
    #end
    #end
   :::$mode
   :::$txtTujuan

<fieldset>
<legend><strong>Penyediaan Kertas MMK/MB/KM/LC</strong></legend>
<table width="100%">
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Tajuk</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtTajuk" id="txtTajuk" cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!TAJUK</textarea>
        <div><span id="txtTajuk_num" style="color:blue;" ></span></div>
	 <script>	
		    var area0 = new FCKeditor('txtTajuk');
	      	area0.BasePath = '/${appname}/library/fck/' ;
	      	area0.Height = 200;
	      	area0.Width = '100%';
	      	area0.ReplaceTextarea(); 
		</script> 
        
           </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!TAJUK</td>#end
  </tr>
<tr>
    <td width="1%" valign="top">1.</td>
    <td width="29%" valign="top">Tujuan</td>
    <td width="1%" valign="top">:</td>
    #if($readonly != 'readonly')<td width="70%"><textarea name="txtTujuan" id="txtTujuan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!TUJUAN1</textarea>
        <div><span id="txtTujuan_num" style="color:blue;" ></span></div>
      <script>	
		    var area1 = new FCKeditor('txtTujuan');
	      	area1.BasePath = '/${appname}/library/fck/' ;
	      	area1.Height = 200;
	      	area1.Width = '100%';
	      	area1.ReplaceTextarea(); 
		</script>   </td>#else
    <td width="70%" valign="top" bgcolor="#ECE5B6">$!TUJUAN1</td>#end
  </tr>
  <tr>
    <td width="1%" valign="top">2.</td>
    <td width="29%" valign="top">Latar Belakang</td>
    <td width="1%" valign="top">:</td>
    #if($readonly != 'readonly')<td width="70%"><textarea name="txtLatarBelakang" id="txtLatarBelakang"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!LATARBELAKANG</textarea>
        <div><span id="txtLatarBelakang_num" style="color:blue;" ></span></div>
      <script>	
		    var area2 = new FCKeditor('txtLatarBelakang');
	      	area2.BasePath = '/${appname}/library/fck/' ;
	      	area2.Height = 200;
	      	area2.Width = '100%';
	      	area2.ReplaceTextarea(); 
		</script>  </td>#else
    <td width="70%" valign="top" bgcolor="#ECE5B6">$!LATARBELAKANG</td>#end
  </tr>
  <tr>
    <td valign="top">3.</td>
    <td valign="top">Laporan Atas Tanah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtPerihalTanah" id="txtPerihalTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PERIHALTANAH</textarea>
        <div><span id="txtPerihalTanah_num" style="color:blue;" ></span></div>
     <script>	
		    var area3 = new FCKeditor('txtPerihalTanah');
	      	area3.BasePath = '/${appname}/library/fck/' ;
	      	area3.Height = 200;
	      	area3.Width = '100%';
	      	area3.ReplaceTextarea(); 
		</script>   </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!PERIHALTANAH</td>#end
  </tr>
  <tr>
    <td valign="top">4.</td>
    <td valign="top">Anggaran Nilaian Tanah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtNilaianTanah" id="txtNilaianTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!NILAI_ATAS_TANAH</textarea>
        <div><span id="txtNilaianTanah_num" style="color:blue;" ></span></div>
      <script>	
		    var area4 = new FCKeditor('txtNilaianTanah');
	      	area4.BasePath = '/${appname}/library/fck/' ;
	      	area4.Height = 200;
	      	area4.Width = '100%';
	      	area4.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!NILAI_ATAS_TANAH</td>#end
  </tr>
  <tr>
    <td valign="top">5.</td>
    <td valign="top">Pengesahan Wang Peruntukan</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtPengesahanPeruntukan" id="txtPengesahanPeruntukan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PENGESAHAN_PERUNTUKAN</textarea>
        <div><span id="txtPengesahanPeruntukan_num" style="color:blue;" ></span></div>
      <script>	
		    var area5 = new FCKeditor('txtPengesahanPeruntukan');
	      	area5.BasePath = '/${appname}/library/fck/' ;
	      	area5.Height = 200;
	      	area5.Width = '100%';
	      	area5.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!PENGESAHAN_PERUNTUKAN</td>#end
  </tr>
  <tr>
    <td valign="top">6.</td>
    <td valign="top">Syor Pentadbir Tanah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtSyor" id="txtSyor"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!SYOR</textarea>
        <div><span id="txtSyor_num" style="color:blue;" ></span></div>
     <script>	
		    var area6 = new FCKeditor('txtSyor');
	      	area6.BasePath = '/${appname}/library/fck/' ;
	      	area6.Height = 200;
	      	area6.Width = '100%';
	      	area6.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!SYOR</td>#end
  </tr>
</table>
</fieldset>

<input name="txtAsasPertimbangan" type="hidden" id="txtAsasPertimbangan" value="$!ASAS_PERTIMBANGAN" />
<input name="txtKesimpulan" type="hidden" id="txtKesimpulan" value="$!KESIMPULAN" />
<input name="txtKedudukanLaporanTanah" type="hidden" id="txtKedudukanLaporanTanah" value="$!KEDUDUKAN_LAPORAN_TNH" />
<input name="txtPandangan" type="hidden" id="txtPandangan" value="$!PANDANGAN" />
<input name="txtImplikasi" type="hidden" id="txtImplikasi" value="$!IMPLIKASI" />
<input name="txtPerihalPohon" type="hidden" id="txtPerihalPohon" value="$!PERIHAL_POHON" />
<input name="txtAnggaranKos" type="hidden" id="txtAnggaranKos" value="$!ANGGARAN_KOS" />
<input name="txtPerakuanPentadbir" type="hidden" id="txtPerakuanPentadbir" value="$!PERAKUAN_PENTADBIR_TNH" />
<input name="txtPengecualianUpahUkur" type="hidden" id="txtPengecualianUpahUkur" value="$!PENGECUALIAN_UPAH_UKUR" />
<input name="txtHalLain" type="hidden" id="txtHalLain" value="$!HAL_LAIN" />
<input name="txtJawatankuasaTanah" type="hidden" id="txtJawatankuasaTanah" value="$!JAWATANKUASA_TANAH" />
<input name="txtNamaTuanTanah" type="hidden" id="txtNamaTuanTanah" value="$!NAMA_TUAN_TANAH" />
<input name="txtUlasan" type="hidden" id="txtUlasan" value="$!ULASAN" />
<input name="txtUlasanJT" type="hidden" id="txtUlasanJT" value="$!ULASAN_JABTEKNIKAL" />
<input name="txtUlasanPengarah" type="hidden" id="txtUlasanPengarah" value="$!ULASAN_PENGARAH" />
<input name="txtKeputusan" type="hidden" id="txtKeputusan" value="$!KEPUTUSAN" />
<input name="txtButirAsas" type="hidden" id="txtButirAsas" value="$!BUTIR_ASAS" />
<input name="txtKeadaanTanah" type="hidden" id="txtKeadaanTanah" value="$!KEADAAN_TANAH" />
<input name="txtButirTanah" type="hidden" id="txtButirTanah" value="$!BUTIR_TANAH" />
<input name="txtKemudahanAsas" type="hidden" id="txtKemudahanAsas" value="$!KEMUDAHAN_ASAS" />

<input name="txtPerakuanSetiausaha" type="hidden" id="txtPerakuanSetiausaha" value="$!PERAKUAN_SETIAUSAHA" />

<input name="txtPenutup" type="hidden" id="txtPenutup" value="$!PENUTUP" />

<input name="idMMK" type="hidden" id="idMMK" value="$!idMMK" />
<script>function submitForm() 
{
check_length_txtTajuk('onload');
check_length_txtTujuan('onload');
check_length_txtLatarBelakang('onload');
check_length_txtPerihalTanah('onload');
check_length_txtNilaianTanah('onload');
check_length_txtPengesahanPeruntukan('onload');
check_length_txtSyor('onload');

}


function FCKeditor_OnComplete(editorInstance){
	if (editorInstance.Name == "txtTajuk" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtTajuk);				
	}
	else if (editorInstance.Name == "txtTujuan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtTujuan);				
	}	
	else if (editorInstance.Name == "txtLatarBelakang" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtLatarBelakang);				
	}	
	else if (editorInstance.Name == "txtPerihalTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPerihalTanah);				
	}	
	else if (editorInstance.Name == "txtNilaianTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtNilaianTanah);				
	}	
	else if (editorInstance.Name == "txtPengesahanPeruntukan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPengesahanPeruntukan);				
	}	
	else if (editorInstance.Name == "txtSyor" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtSyor);				
	}	

}
function check_length_txtTajuk(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtTajuk');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtTajuk').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtTajuk';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtTajuk_check';
var text_num = 'txtTajuk_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'tajuk';




	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(count  == 0 && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   	   
	   if(count  == 0)
	   {
	   document.getElementById(text_num).value = maxLen;
	   } 
	   
	     
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (count >= maxLen ) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan"); 

      maxLen = 0;
   var text = editorInstance.GetHTML(true);
   editorInstance.SetHTML(text.substr(0,charlimit));
 
    
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - count;
       }		
	   }
	   
	   	   
	   }
	   
	  

$jquery("#"+text_num).html(maxLen+" <span style='color:black;' > Baki Aksara </span>");


}
function check_length_txtTujuan(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtTujuan');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtTujuan').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtTujuan';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtTujuan_check';
var text_num = 'txtTujuan_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'tujuan';




	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(count  == 0 && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   	   
	   if(count  == 0)
	   {
	   document.getElementById(text_num).value = maxLen;
	   } 
	   
	     
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (count >= maxLen ) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan"); 

      maxLen = 0;
   var text = editorInstance.GetHTML(true);
   editorInstance.SetHTML(text.substr(0,charlimit));
 
    
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - count;
       }		
	   }
	   
	   	   
	   }
	   
	  

$jquery("#"+text_num).html(maxLen+" <span style='color:black;' > Baki Aksara </span>");


}
function check_length_txtLatarBelakang(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtLatarBelakang');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtLatarBelakang').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtLatarBelakang';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtLatarBelakang_check';
var text_num = 'txtLatarBelakang_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'latar belakang';




	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(count  == 0 && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   	   
	   if(count  == 0)
	   {
	   document.getElementById(text_num).value = maxLen;
	   } 
	   
	     
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (count >= maxLen ) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan"); 

      maxLen = 0;
   var text = editorInstance.GetHTML(true);
   editorInstance.SetHTML(text.substr(0,charlimit));
 
    
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - count;
       }		
	   }
	   
	   	   
	   }
	   
	  

$jquery("#"+text_num).html(maxLen+" <span style='color:black;' > Baki Aksara </span>");


}
function check_length_txtPerihalTanah(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtPerihalTanah');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtPerihalTanah').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtPerihalTanah';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtPerihalTanah_check';
var text_num = 'txtPerihalTanah_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'laporan atas tanah';




	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(count  == 0 && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   	   
	   if(count  == 0)
	   {
	   document.getElementById(text_num).value = maxLen;
	   } 
	   
	     
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (count >= maxLen ) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan"); 

      maxLen = 0;
   var text = editorInstance.GetHTML(true);
   editorInstance.SetHTML(text.substr(0,charlimit));
 
    
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - count;
       }		
	   }
	   
	   	   
	   }
	   
	  

$jquery("#"+text_num).html(maxLen+" <span style='color:black;' > Baki Aksara </span>");


}
function check_length_txtNilaianTanah(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtNilaianTanah');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtNilaianTanah').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtNilaianTanah';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtNilaianTanah_check';
var text_num = 'txtNilaianTanah_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'anggaran nilaian tanah';




	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(count  == 0 && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   	   
	   if(count  == 0)
	   {
	   document.getElementById(text_num).value = maxLen;
	   } 
	   
	     
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (count >= maxLen ) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan"); 

      maxLen = 0;
   var text = editorInstance.GetHTML(true);
   editorInstance.SetHTML(text.substr(0,charlimit));
 
    
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - count;
       }		
	   }
	   
	   	   
	   }
	   
	  

$jquery("#"+text_num).html(maxLen+" <span style='color:black;' > Baki Aksara </span>");


}
function check_length_txtPengesahanPeruntukan(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtPengesahanPeruntukan');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtPengesahanPeruntukan').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtPengesahanPeruntukan';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtPengesahanPeruntukan_check';
var text_num = 'txtPengesahanPeruntukan_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'pengesahan wang peruntukan';




	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(count  == 0 && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   	   
	   if(count  == 0)
	   {
	   document.getElementById(text_num).value = maxLen;
	   } 
	   
	     
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (count >= maxLen ) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan"); 

      maxLen = 0;
   var text = editorInstance.GetHTML(true);
   editorInstance.SetHTML(text.substr(0,charlimit));
 
    
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - count;
       }		
	   }
	   
	   	   
	   }
	   
	  

$jquery("#"+text_num).html(maxLen+" <span style='color:black;' > Baki Aksara </span>");


}
function check_length_txtSyor(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtSyor');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtSyor').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtSyor';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtSyor_check';
var text_num = 'txtSyor_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'syor pentadbir tanah';




	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(count  == 0 && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   	   
	   if(count  == 0)
	   {
	   document.getElementById(text_num).value = maxLen;
	   } 
	   
	     
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (count >= maxLen ) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan"); 

      maxLen = 0;
   var text = editorInstance.GetHTML(true);
   editorInstance.SetHTML(text.substr(0,charlimit));
 
    
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - count;
       }		
	   }
	   
	   	   
	   }
	   
	  

$jquery("#"+text_num).html(maxLen+" <span style='color:black;' > Baki Aksara </span>");


}


</script>
