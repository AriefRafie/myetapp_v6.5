<fieldset><legend><strong>Penyediaan Kertas MMK/MB/KM/LC</strong></legend>
<table width="100%">
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Tajuk</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtTajuk" id="txtTajuk"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!TAJUK</textarea>
            <div><span id="txtTajuk_num" style="color:blue;" ></span></div>

	<script>	
		    var area0 = new FCKeditor('txtTajuk');
	      	area0.BasePath = '/${appname}/library/fck/' ;
	      	area0.Height = 200;
	      	area0.Width = '100%';
	      	area0.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!TAJUK</td>#end
  </tr>
  <tr>
    <td width="1%" valign="top">1.</td>
    <td width="29%" valign="top">Tujuan</td>
    <td width="1%" valign="top">:</td>
    #if($readonly != 'readonly')<td width="70%">
    <textarea name="txtTujuan" id="txtTujuan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!TUJUAN1</textarea>
        <div><span id="txtTujuan_num" style="color:blue;" ></span></div>
      <script>	
		    var area1 = new FCKeditor('txtTujuan');
	      	area1.BasePath = '/${appname}/library/fck/' ;
	      	area1.Height = 200;
	      	area1.Width = '100%';
	      	area1.ReplaceTextarea(); 
		</script>           </td>#else
    <td width="70%" valign="top" bgcolor="#ECE5B6">$!TUJUAN1</td>#end
  </tr>
  <tr>
    <td width="1%" valign="top">2.</td>
    <td width="29%" valign="top">Latar Belakang</td>
    <td width="1%" valign="top">:</td>
    #if($readonly != 'readonly')<td width="70%">
    <textarea name="txtLatarBelakang" id="txtLatarBelakang"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!LATARBELAKANG</textarea>
        <div><span id="txtLatarBelakang_num" style="color:blue;" ></span></div>
      <script>	
		    var area2 = new FCKeditor('txtLatarBelakang');
	      	area2.BasePath = '/${appname}/library/fck/' ;
	      	area2.Height = 200;
	      	area2.Width = '100%';
	      	area2.ReplaceTextarea(); 
		</script>            </td>#else
    <td width="70%" valign="top" bgcolor="#ECE5B6">$!LATARBELAKANG</td>#end
  </tr>
  <tr>
    <td valign="top">3.</td>
    <td valign="top">Asas - Asas Pertimbangan</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtAsasPertimbangan" id="txtAsasPertimbangan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!ASAS_PERTIMBANGAN</textarea>
        <div><span id="txtAsasPertimbangan_num" style="color:blue;" ></span></div>
      <script>	
		    var area3 = new FCKeditor('txtAsasPertimbangan');
	      	area3.BasePath = '/${appname}/library/fck/' ;
	      	area3.Height = 200;
	      	area3.Width = '100%';
	      	area3.ReplaceTextarea(); 
		</script>            </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!ASAS_PERTIMBANGAN</td>#end
  </tr>
  <tr>
    <td valign="top">4.</td>
    <td valign="top">Implikasi</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtImplikasi" id="txtImplikasi"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!IMPLIKASI</textarea>
        <div><span id="txtImplikasi_num" style="color:blue;" ></span></div>
      <script>	
		    var area4 = new FCKeditor('txtImplikasi');
	      	area4.BasePath = '/${appname}/library/fck/' ;
	      	area4.Height = 200;
	      	area4.Width = '100%';
	      	area4.ReplaceTextarea(); 
		</script>           </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!IMPLIKASI</td>#end
  </tr>
  <tr>
    <td valign="top">5.</td>
    <td valign="top">Ulasan Jabatan Teknikal</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtUlasanJT" id="txtUlasanJT"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!ULASAN_JABTEKNIKAL</textarea>
        <div><span id="txtUlasanJT_num" style="color:blue;" ></span></div>
      <script>	
		    var area5 = new FCKeditor('txtUlasanJT');
	      	area5.BasePath = '/${appname}/library/fck/' ;
	      	area5.Height = 200;
	      	area5.Width = '100%';
	      	area5.ReplaceTextarea(); 
		</script>           </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!ULASAN_JABTEKNIKAL</td>#end
  </tr>
  <tr>
    <td valign="top">6.</td>
    <td valign="top">Kesimpulan</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtKesimpulan" id="txtKesimpulan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!KESIMPULAN</textarea>
        <div><span id="txtKesimpulan_num" style="color:blue;" ></span></div>
      <script>	
		    var area6 = new FCKeditor('txtKesimpulan');
	      	area6.BasePath = '/${appname}/library/fck/' ;
	      	area6.Height = 200;
	      	area6.Width = '100%';
	      	area6.ReplaceTextarea(); 
		</script>          </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!KESIMPULAN</td>#end
  </tr>
  <tr>
    <td valign="top">7.</td>
    <td valign="top">Syor</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtSyor" id="txtSyor"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!SYOR</textarea>
        <div><span id="txtSyor_num" style="color:blue;" ></span></div>
      <script>	
		    var area7 = new FCKeditor('txtSyor');
	      	area7.BasePath = '/${appname}/library/fck/' ;
	      	area7.Height = 200;
	      	area7.Width = '100%';
	      	area7.ReplaceTextarea(); 
		</script>         </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!SYOR</td>#end
  </tr>
</table>
</fieldset>

<input name="txtKedudukanLaporanTanah" type="hidden" id="txtKedudukanLaporanTanah" value="$!KEDUDUKAN_LAPORAN_TNH" />
<input name="txtPengesahanPeruntukan" type="hidden" id="txtPengesahanPeruntukan" value="$!PENGESAHAN_PERUNTUKAN" />
<input name="txtPandangan" type="hidden" id="txtPandangan" value="$!PANDANGAN" />
<input name="txtPerihalTanah" type="hidden" id="txtPerihalTanah" value="$!PERIHALTANAH" />
<input name="txtPerihalPohon" type="hidden" id="txtPerihalPohon" value="$!PERIHAL_POHON" />
<input name="txtAnggaranKos" type="hidden" id="txtAnggaranKos" value="$!ANGGARAN_KOS" />
<input name="txtPerakuanPentadbir" type="hidden" id="txtPerakuanPentadbir" value="$!PERAKUAN_PENTADBIR_TNH" />
<input name="txtNilaiTanah" type="hidden" id="txtNilaiTanah" value="$!NILAI_ATAS_TANAH" />
<input name="txtPengecualianUpahUkur" type="hidden" id="txtPengecualianUpahUkur" value="$!PENGECUALIAN_UPAH_UKUR" />
<input name="txtHalLain" type="hidden" id="txtHalLain" value="$!HAL_LAIN" />
<input name="txtJawatankuasaTanah" type="hidden" id="txtJawatankuasaTanah" value="$!JAWATANKUASA_TANAH" />
<input name="txtNamaTuanTanah" type="hidden" id="txtNamaTuanTanah" value="$!NAMA_TUAN_TANAH" />
<input name="txtUlasan" type="hidden" id="txtUlasan" value="$!ULASAN" />
<input name="txtUlasanPengarah" type="hidden" id="txtUlasanPengarah" value="$!ULASAN_PENGARAH" />
<input name="txtKeputusan" type="hidden" id="txtKeputusan" value="$!KEPUTUSAN" />
<input name="txtButirAsas" type="hidden" id="txtButirAsas" value="$!BUTIR_ASAS" />
<input name="txtKeadaanTanah" type="hidden" id="txtKeadaanTanah" value="$!KEADAAN_TANAH" />
<input name="txtButirTanah" type="hidden" id="txtButirTanah" value="$!BUTIR_TANAH" />
<input name="txtKemudahanAsas" type="hidden" id="txtKemudahanAsas" value="$!KEMUDAHAN_ASAS" />

<input name="txtPerakuanSetiausaha" type="hidden" id="txtPerakuanSetiausaha" value="$!PERAKUAN_SETIAUSAHA" />

<input name="txtPenutup" type="hidden" id="txtPenutup" value="$!PENUTUP" />

<input name="idMMK" type="hidden" id="idMMK" value="$!idMMK" />
<script>
  function submitForm() 
{
check_length_txtTajuk('onload');
check_length_txtTujuan('onload');
check_length_txtLatarBelakang('onload');
check_length_txtAsasPertimbangan('onload');
check_length_txtImplikasi('onload');
check_length_txtUlasanJT('onload');
check_length_txtKesimpulan('onload');
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
	else if (editorInstance.Name == "txtAsasPertimbangan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtAsasPertimbangan);				
	}	
	else if (editorInstance.Name == "txtImplikasi" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtImplikasi);				
	}	
	else if (editorInstance.Name == "txtUlasanJT" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtUlasanJT);				
	}	
	else if (editorInstance.Name == "txtKesimpulan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtKesimpulan);				
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
function check_length_txtAsasPertimbangan(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtAsasPertimbangan');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtAsasPertimbangan').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtAsasPertimbangan';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtAsasPertimbangan_check';
var text_num = 'txtAsasPertimbangan_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'asas pertimbangan';




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
function check_length_txtImplikasi(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtImplikasi');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtImplikasi').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtImplikasi';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtImplikasi_check';
var text_num = 'txtImplikasi_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'implikasi';




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
function check_length_txtUlasanJT(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtUlasanJT');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtUlasanJT').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtUlasanJT';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtUlasanJT_check';
var text_num = 'txtUlasanJT_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'ulasan jabatan teknikal';




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
function check_length_txtKesimpulan(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtKesimpulan');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtKesimpulan').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtKesimpulan';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtKesimpulan_check';
var text_num = 'txtKesimpulan_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'kesimpulan';




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
var value_field = 'syor';




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
