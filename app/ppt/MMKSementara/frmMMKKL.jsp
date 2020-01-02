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
    <td valign="top">1.</td>
    <td valign="top">Butir - Butir Asas</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtButirAsas" id="txtButirAsas"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!BUTIR_ASAS</textarea>
     <div><span id="txtButirAsas_num" style="color:blue;" ></span></div>
	  <script>	
		    var area1 = new FCKeditor('txtButirAsas');
	      	area1.BasePath = '/${appname}/library/fck/' ;
	      	area1.Height = 200;
	      	area1.Width = '100%';
	      	area1.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!BUTIR_ASAS</td>#end
  </tr>
  <tr>
    <td width="1%" valign="top">2.</td>
    <td width="29%" valign="top">Tujuan</td>
    <td width="1%" valign="top">:</td>
    #if($readonly != 'readonly')<td width="70%"><textarea name="txtTujuan" id="txtTujuan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!TUJUAN1</textarea>
        <div><span id="txtTujuan_num" style="color:blue;" ></span></div>
      <script>	
		    var area2 = new FCKeditor('txtTujuan');
	      	area2.BasePath = '/${appname}/library/fck/' ;
	      	area2.Height = 200;
	      	area2.Width = '100%';
	      	area2.ReplaceTextarea(); 
		</script>    </td>#else
    <td width="70%" valign="top" bgcolor="#ECE5B6">$!TUJUAN1</td>#end
  </tr>
  <tr>
    <td valign="top">3.</td>
    <td valign="top">Latar Belakang</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtLatarBelakang" id="txtLatarBelakang"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!LATARBELAKANG</textarea>
      <div><span id="txtLatarBelakang_num" style="color:blue;" ></span></div>
      <script>	
		    var area3 = new FCKeditor('txtLatarBelakang');
	      	area3.BasePath = '/${appname}/library/fck/' ;
	      	area3.Height = 200;
	      	area3.Width = '100%';
	      	area3.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!LATARBELAKANG</td>#end
  </tr>
  <tr>
    <td valign="top">4.</td>
    <td valign="top">Laporan Tanah</td>
    <td valign="top">&nbsp;</td>
    #if($readonly != 'readonly')<td><textarea name="txtPerihalTanah" id="txtPerihalTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PERIHALTANAH</textarea>
        <div><span id="txtPerihalTanah_num" style="color:blue;" ></span></div>
      <script>	
		    var area4 = new FCKeditor('txtPerihalTanah');
	      	area4.BasePath = '/${appname}/library/fck/' ;
	      	area4.Height = 200;
	      	area4.Width = '100%';
	      	area4.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!PERIHALTANAH</td>#end
  </tr>
  <tr>
    <td valign="top">5.</td>
    <td valign="top">Keadaan Tanah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtKeadaanTanah" id="txtKeadaanTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!KEADAAN_TANAH</textarea>
        <div><span id="txtKeadaanTanah_num" style="color:blue;" ></span></div>
      <script>	
		    var area5 = new FCKeditor('txtKeadaanTanah');
	      	area5.BasePath = '/${appname}/library/fck/' ;
	      	area5.Height = 200;
	      	area5.Width = '100%';
	      	area5.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!KEADAAN_TANAH</td>#end
  </tr>
  <tr>
    <td valign="top">6.</td>
    <td valign="top">Butir - Butir Tanah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtButirTanah" id="txtButirTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!BUTIR_TANAH</textarea>
        <div><span id="txtButirTanah_num" style="color:blue;" ></span></div>
      <script>	
		    var area6 = new FCKeditor('txtButirTanah');
	      	area6.BasePath = '/${appname}/library/fck/' ;
	      	area6.Height = 200;
	      	area6.Width = '100%';
	      	area6.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!BUTIR_TANAH</td>#end
  </tr>
  <tr>
    <td valign="top">7.</td>
    <td valign="top">Kemudahan Asas</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtKemudahanAsas" id="txtKemudahanAsas"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!KEMUDAHAN_ASAS</textarea>
      <div><span id="txtKemudahanAsas_num" style="color:blue;" ></span></div>
      <script>	
		    var area7 = new FCKeditor('txtKemudahanAsas');
	      	area7.BasePath = '/${appname}/library/fck/' ;
	      	area7.Height = 200;
	      	area7.Width = '100%';
	      	area7.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!KEMUDAHAN_ASAS</td>#end
  </tr>
  <tr>
    <td valign="top">8.</td>
    <td valign="top">Bayaran Pampasan</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtNilaiTanah" id="txtNilaiTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!NILAI_ATAS_TANAH</textarea>
        <div><span id="txtNilaiTanah_num" style="color:blue;" ></span></div>
      <script>	
		    var area8 = new FCKeditor('txtNilaiTanah');
	      	area8.BasePath = '/${appname}/library/fck/' ;
	      	area8.Height = 200;
	      	area8.Width = '100%';
	      	area8.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!NILAI_ATAS_TANAH</td>#end
  </tr>
  <tr>
    <td valign="top">9.</td>
    <td valign="top">Pengesahan Peruntukan</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtPengesahanPeruntukan" id="txtPengesahanPeruntukan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PENGESAHAN_PERUNTUKAN</textarea>
        <div><span id="txtPengesahanPeruntukan_num" style="color:blue;" ></span></div>
      <script>	
		    var area9 = new FCKeditor('txtPengesahanPeruntukan');
	      	area9.BasePath = '/${appname}/library/fck/' ;
	      	area9.Height = 200;
	      	area9.Width = '100%';
	      	area9.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!PENGESAHAN_PERUNTUKAN</td>#end
  </tr>
  <tr>
    <td valign="top">10.</td>
    <td valign="top">Ulasan Pentadbir Tanah Wilayah Persekutuan Kuala Lumpur</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtUlasan" id="txtUlasan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!ULASAN</textarea>
        <div><span id="txtUlasan_num" style="color:blue;" ></span></div>
      <script>	
		    var area10 = new FCKeditor('txtUlasan');
	      	area10.BasePath = '/${appname}/library/fck/' ;
	      	area10.Height = 200;
	      	area10.Width = '100%';
	      	area10.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!ULASAN</td>#end
  </tr>
  <tr>
    <td valign="top">11.</td>
    <td valign="top">Perakuan Pentadbir Tanah Wilayah Persekutuan Kuala Lumpur</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtSyor" id="txtSyor"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!SYOR</textarea>
        <div><span id="txtSyor_num" style="color:blue;" ></span></div>
      <script>	
		    var area11 = new FCKeditor('txtSyor');
	      	area11.BasePath = '/${appname}/library/fck/' ;
	      	area11.Height = 200;
	      	area11.Width = '100%';
	      	area11.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!SYOR</td>#end
  </tr>
  <tr>
    <td valign="top">12.</td>
    <td valign="top">Keputusan Dipohon</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtKeputusan" id="txtKeputusan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!KEPUTUSAN</textarea>
    <div><span id="txtKeputusan_num" style="color:blue;" ></span></div>
      <script>	
		    var area11 = new FCKeditor('txtKeputusan');
	      	area11.BasePath = '/${appname}/library/fck/' ;
	      	area11.Height = 200;
	      	area11.Width = '100%';
	      	area11.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!KEPUTUSAN</td>#end
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
<input name="txtUlasanJT" type="hidden" id="txtUlasanJT" value="$!ULASAN_JABTEKNIKAL" />
<input name="txtUlasanPengarah" type="hidden" id="txtUlasanPengarah" value="$!ULASAN_PENGARAH" />
<input name="txtPerakuanSetiausaha" type="hidden" id="txtPerakuanSetiausaha" value="$!PERAKUAN_SETIAUSAHA" />
<script>
  function submitForm() 
{
check_length_txtButirAsas('onload');
check_length_txtTajuk('onload');
check_length_txtTujuan('onload');
check_length_txtLatarBelakang('onload');
check_length_txtPerihalTanah('onload');
check_length_txtKeadaanTanah('onload');
check_length_txtButirTanah('onload');
check_length_txtKemudahanAsas('onload');
check_length_txtNilaiTanah('onload');
check_length_txtPengesahanPeruntukan('onload');
check_length_txtSyor('onload');
check_length_txtUlasan('onload');
check_length_txtKeputusan('onload');
}


function FCKeditor_OnComplete(editorInstance){
	if (editorInstance.Name == "txtButirAsas" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtButirAsas);				
	}
	else if (editorInstance.Name == "txtTajuk" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtTajuk);				
	}
	else if (editorInstance.Name == "txtTujuan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtTujuan);				
	}	
	else if (editorInstance.Name == "txtPerihalTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPerihalTanah);				
	}
	else if (editorInstance.Name == "txtLatarBelakang" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtLatarBelakang);				
	}	
	else if (editorInstance.Name == "txtPengesahanPeruntukan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPengesahanPeruntukan);				
	}	
	else if (editorInstance.Name == "txtKeadaanTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtKeadaanTanah);				
	}	
	else if (editorInstance.Name == "txtButirTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtButirTanah);				
	}	
	else if (editorInstance.Name == "txtKemudahanAsas" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtKemudahanAsas);				
	}	
	else if (editorInstance.Name == "txtNilaiTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtNilaiTanah);				
	}	
	else if (editorInstance.Name == "txtSyor" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtSyor);				
	}	
	else if (editorInstance.Name == "txtUlasanPengarah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtUlasan);				
	}
	else if (editorInstance.Name == "txtKeputusan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtKeputusan);				
	}	
	
		
}
function check_length_txtButirAsas(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtButirAsas');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtButirAsas').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtButirAsas';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtButirAsas_check';
var text_num = 'txtButirAsas_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'butir - butir asas';




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
var value_field = 'laporan tanah';




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
function check_length_txtKeadaanTanah(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtKeadaanTanah');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtKeadaanTanah').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtKeadaanTanah';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtKeadaanTanah_check';
var text_num = 'txtKeadaanTanah_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'keadaan tanah';




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
function check_length_txtButirTanah(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtButirTanah');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtButirTanah').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtButirTanah';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtButirTanah_check';
var text_num = 'txtButirTanah_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'butir - butir tanah';




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
function check_length_txtKemudahanAsas(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtKemudahanAsas');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtKemudahanAsas').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtKemudahanAsas';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtKemudahanAsas_check';
var text_num = 'txtKemudahanAsas_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'kemudahan asas';




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
function check_length_txtNilaiTanah(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtNilaiTanah');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtNilaiTanah').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtNilaiTanah';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtNilaiTanah_check';
var text_num = 'txtNilaiTanah_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'Anggaran Nilaian Tanah';




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
var value_field = 'pengesahan peruntukan';




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
var value_field = 'syor pentadbir tanah dan galian';




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
function check_length_txtUlasan(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtUlasan');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtUlasan').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtUlasan';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtUlasan_check';
var text_num = 'txtUlasan_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'ulasan';




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

function check_length_txtKeputusan(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtKeputusan');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtKeputusan').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtKeputusan';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtKeputusan_check';
var text_num = 'txtKeputusan_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'keputusan dipohon';




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

<input name="txtPenutup" type="hidden" id="txtPenutup" value="$!PENUTUP" />
<input name="idMMK" type="hidden" id="idMMK" value="$!idMMK" />
