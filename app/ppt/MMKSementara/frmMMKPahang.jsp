<fieldset>
<legend><strong>Penyediaan Kertas MMK/MB/KM/LC</strong></legend>
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
    #if($readonly != 'readonly')<td width="70%"><textarea name="txtTujuan" id="txtTujuan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!TUJUAN1</textarea>
        <div><span id="txtTujuan_num" style="color:blue;" ></span></div>
      <script>	
		    var area1 = new FCKeditor('txtTujuan');
	      	area1.BasePath = '/${appname}/library/fck/' ;
	      	area1.Height = 200;
	      	area1.Width = '100%';
	      	area1.ReplaceTextarea(); 
		</script>    </td>#else
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
		</script>    </td>#else
    <td width="70%" valign="top" bgcolor="#ECE5B6">$!LATARBELAKANG</td>#end
  </tr>
  <tr>
    <td valign="top">3.</td>
    <td valign="top">Kedudukan dan Laporan Atas Tanah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtKedudukanLaporanTanah" id="txtKedudukanLaporanTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!KEDUDUKAN_LAPORAN_TNH</textarea>
        <div><span id="txtKedudukanLaporanTanah_num" style="color:blue;" ></span></div>
      <script>	
		    var area3 = new FCKeditor('txtKedudukanLaporanTanah');
	      	area3.BasePath = '/${appname}/library/fck/' ;
	      	area3.Height = 200;
	      	area3.Width = '100%';
	      	area3.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!KEDUDUKAN_LAPORAN_TNH</td>#end
  </tr>
  <tr>
    <td valign="top">4.</td>
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
    <td valign="top">5.</td>
    <td valign="top">Pandangan Pentadbir Tanah Daerah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtPerakuanPentadbir" id="txtPerakuanPentadbir"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PERAKUAN_PENTADBIR_TNH</textarea>
        <div><span id="txtPerakuanPentadbir_num" style="color:blue;" ></span></div>
      <script>	
		    var area6 = new FCKeditor('txtPerakuanPentadbir');
	      	area6.BasePath = '/${appname}/library/fck/' ;
	      	area6.Height = 200;
	      	area6.Width = '100%';
	      	area6.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!PERAKUAN_PENTADBIR_TNH</td>#end
  </tr>
  <tr>
    <td valign="top">6.</td>
    <td valign="top">Perakuan Pengarah Tanah dan Galian</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtUlasanPengarah" id="txtUlasanPengarah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!ULASAN_PENGARAH</textarea>
     <div><span id="txtUlasanPengarah_num" style="color:blue;" ></span></div>
     <script>	
		    var area7 = new FCKeditor('txtUlasanPengarah');
	      	area7.BasePath = '/${appname}/library/fck/' ;
	      	area7.Height = 200;
	      	area7.Width = '100%';
	      	area7.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!ULASAN_PENGARAH</td>#end
  </tr>
  <tr>
    <td valign="top">7.</td>
    <td valign="top">Penutup</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtPenutup" id="txtPenutup"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PENUTUP</textarea>
      <div><span id="txtPenutup_num" style="color:blue;" ></span></div>
     <script>	
		    var area8 = new FCKeditor('txtPenutup');
	      	area8.BasePath = '/${appname}/library/fck/' ;
	      	area8.Height = 200;
	      	area8.Width = '100%';
	      	area8.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!PENUTUP</td>#end
  </tr>
</table>
</fieldset>
<input name="txtAsasPertimbangan" type="hidden" id="txtAsasPertimbangan" value="$!ASAS_PERTIMBANGAN" />
<input name="txtKesimpulan" type="hidden" id="txtKesimpulan" value="$!KESIMPULAN" />
<input name="txtSyor" type="hidden" id="txtSyor" value="$!SYOR">
<input name="txtPandangan" type="hidden" id="txtPandangan" value="$!PANDANGAN" />
<input name="txtImplikasi" type="hidden" id="txtImplikasi" value="$!IMPLIKASI" />
<input name="txtPerihalTanah" type="hidden" id="txtPerihalTanah" value="$!PERIHALTANAH" />
<input name="txtPerihalPohon" type="hidden" id="txtPerihalPohon" value="$!PERIHAL_POHON" />
<input name="txtAnggaranKos" type="hidden" id="txtAnggaranKos" value="$!ANGGARAN_KOS" />
<input name="txtNilaiTanah" type="hidden" id="txtNilaiTanah" value="$!NILAI_ATAS_TANAH">
<input name="txtPengecualianUpahUkur" type="hidden" id="txtPengecualianUpahUkur" value="$!PENGECUALIAN_UPAH_UKUR" />
<input name="txtHalLain" type="hidden" id="txtHalLain" value="$!HAL_LAIN" />
<input name="txtJawatankuasaTanah" type="hidden" id="txtJawatankuasaTanah" value="$!JAWATANKUASA_TANAH" />
<input name="txtNamaTuanTanah" type="hidden" id="txtNamaTuanTanah" value="$!NAMA_TUAN_TANAH" />
<input name="txtUlasan" type="hidden" id="txtUlasan" value="$!ULASAN" />
<input name="txtUlasanJT" type="hidden" id="txtUlasanJT" value="$!ULASAN_JABTEKNIKAL" />
<input name="txtButirAsas" type="hidden" id="txtButirAsas" value="$!BUTIR_ASAS" />
<input name="txtKeadaanTanah" type="hidden" id="txtKeadaanTanah" value="$!KEADAAN_TANAH" />
<input name="txtButirTanah" type="hidden" id="txtButirTanah" value="$!BUTIR_TANAH" />
<input name="txtKemudahanAsas" type="hidden" id="txtKemudahanAsas" value="$!KEMUDAHAN_ASAS" />
<input name="txtKeputusan" type="hidden" id="txtKeputusan" value="$!KEPUTUSAN" />
<input name="txtPerakuanSetiausaha" type="hidden" id="txtPerakuanSetiausaha" value="$!PERAKUAN_SETIAUSAHA" />
<input name="idMMK" type="hidden" id="idMMK" value="$!idMMK" />
<script>function submitForm() 
{
check_length_txtTajuk('onload');
check_length_txtTujuan('onload');
check_length_txtLatarBelakang('onload');
check_length_txtKedudukanLaporanTanah('onload');
check_length_txtPengesahanPeruntukan('onload');
check_length_txtPerakuanPentadbir('onload');
check_length_txtUlasanPengarah('onload');
check_length_txtPenutup('onload');

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
	else if (editorInstance.Name == "txtKedudukanLaporanTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtKedudukanLaporanTanah);				
	}	
	else if (editorInstance.Name == "txtPengesahanPeruntukan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPengesahanPeruntukanh);				
	}	
	else if (editorInstance.Name == "txtPerakuanPentadbir" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPerakuanPentadbir);				
	}	
	else if (editorInstance.Name == "txtUlasanPengarah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtUlasanPengarah);				
	}
	else if (editorInstance.Name == "txtPenutup" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPenutup);				
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
function check_length_txtKedudukanLaporanTanah(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtKedudukanLaporanTanah');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtKedudukanLaporanTanah').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtKedudukanLaporanTanah';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtKedudukanLaporanTanah_check';
var text_num = 'txtKedudukanLaporanTanah_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'kedudukan dan laporan atas tanah';




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
function check_length_txtPerakuanPentadbir(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtPerakuanPentadbir');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtPerakuanPentadbir').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtPerakuanPentadbir';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtPerakuanPentadbir_check';
var text_num = 'txtPerakuanPentadbir_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'pandangan pentadbir tanah daerah';




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
function check_length_txtUlasanPengarah(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtUlasanPengarah');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtUlasanPengarah').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtUlasanPengarah';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtUlasanPengarah_check';
var text_num = 'txtUlasanPengarah_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'perakuan pengarah tanah dan galian';




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

function check_length_txtPenutup(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtPenutup');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtPenutup').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtPenutup';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtPenutup_check';
var text_num = 'txtPenutup_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'penutup';




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
