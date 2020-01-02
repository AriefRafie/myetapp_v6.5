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
    <td width="29%" valign="top">Perihal Agensi Memohon</td>
    <td width="1%" valign="top">:</td>
    #if($readonly != 'readonly')<td width="70%">
    <textarea name="txtPerihalPohon" id="txtPerihalPohon"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PERIHAL_POHON</textarea>
        <div><span id="txtPerihalPohon_num" style="color:blue;" ></span></div>
      <script>	
		    var area2 = new FCKeditor('txtPerihalPohon');
	      	area2.BasePath = '/${appname}/library/fck/' ;
	      	area2.Height = 200;
	      	area2.Width = '100%';
	      	area2.ReplaceTextarea(); 
		</script>            </td>#else
    <td width="70%" valign="top" bgcolor="#ECE5B6">$!PERIHAL_POHON</td>#end
  </tr>
  <tr>
    <td valign="top">3.</td>
    <td valign="top">Latar Belakang</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtLatarBelakang" id="txtLatarBelakang"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!LATARBELAKANG</textarea>
        <div><span id="txtLatarBelakang_num" style="color:blue;" ></span></div>
      <script>	
		    var area3 = new FCKeditor('txtLatarBelakang');
	      	area3.BasePath = '/${appname}/library/fck/' ;
	      	area3.Height = 200;
	      	area3.Width = '100%';
	      	area3.ReplaceTextarea(); 
		</script>            </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!LATARBELAKANG</td>#end
  </tr>
  <tr>
    <td valign="top">4.</td>
    <td valign="top">Peruntukan Pengambilan Tanah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtPengesahanPeruntukan" id="txtPengesahanPeruntukan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PENGESAHAN_PERUNTUKAN</textarea>
        <div><span id="txtPengesahanPeruntukan_num" style="color:blue;" ></span></div>
      <script>	
		    var area4 = new FCKeditor('txtPengesahanPeruntukan');
	      	area4.BasePath = '/${appname}/library/fck/' ;
	      	area4.Height = 200;
	      	area4.Width = '100%';
	      	area4.ReplaceTextarea(); 
		</script>           </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!PENGESAHAN_PERUNTUKAN</td>#end
  </tr>
  <tr>
    <td valign="top">5.</td>
    <td valign="top">Perihal  Tanah - Tanah Terlibat</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtPerihalTanah" id="txtPerihalTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PERIHALTANAH</textarea>
        <div><span id="txtPerihalTanah_num" style="color:blue;" ></span></div>
      <script>	
		    var area5 = new FCKeditor('txtPerihalTanah');
	      	area5.BasePath = '/${appname}/library/fck/' ;
	      	area5.Height = 200;
	      	area5.Width = '100%';
	      	area5.ReplaceTextarea(); 
		</script>           </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!PERIHALTANAH</td>#end
  </tr>
  <tr>
    <td valign="top">6.</td>
    <td valign="top">Nama - Nama Tuan Tanah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtNamaTuanTanah" id="txtNamaTuanTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!NAMA_TUAN_TANAH</textarea>
        <div><span id="txtNamaTuanTanah_num" style="color:blue;" ></span></div>
      <script>	
		    var area6 = new FCKeditor('txtNamaTuanTanah');
	      	area6.BasePath = '/${appname}/library/fck/' ;
	      	area6.Height = 200;
	      	area6.Width = '100%';
	      	area6.ReplaceTextarea(); 
		</script>          </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!NAMA_TUAN_TANAH</td>#end
  </tr>
  <tr>
    <td valign="top">7.</td>
    <td valign="top">Perakuan Jawatankuasa Hal Ehwal Tanah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtJawatankuasaTanah" id="txtJawatankuasaTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!JAWATANKUASA_TANAH</textarea>
        <div><span id="txtJawatankuasaTanah_num" style="color:blue;" ></span></div>
      <script>	
		    var area7 = new FCKeditor('txtJawatankuasaTanah');
	      	area7.BasePath = '/${appname}/library/fck/' ;
	      	area7.Height = 200;
	      	area7.Width = '100%';
	      	area7.ReplaceTextarea(); 
		</script>         </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!JAWATANKUASA_TANAH</td>#end
  </tr>
  <tr>
    <td valign="top">8</td>
    <td valign="top">Asas - Asas Pertimbangan</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtAsasPertimbangan" id="txtAsasPertimbangan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!ASAS_PERTIMBANGAN</textarea>
        <div><span id="txtAsasPertimbangan_num" style="color:blue;" ></span></div>
      <script>	
		    var area8 = new FCKeditor('txtAsasPertimbangan');
	      	area8.BasePath = '/${appname}/library/fck/' ;
	      	area8.Height = 200;
	      	area8.Width = '100%';
	      	area8.ReplaceTextarea(); 
		</script>         </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!ASAS_PERTIMBANGAN</td>#end
  </tr>
  <tr>
    <td valign="top">9.</td>
    <td valign="top">Syor Pentadbir Tanah Jajahan</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtSyor" id="txtSyor"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!SYOR</textarea>
        <div><span id="txtSyor_num" style="color:blue;" ></span></div>
      <script>	
		    var area9 = new FCKeditor('txtSyor');
	      	area9.BasePath = '/${appname}/library/fck/' ;
	      	area9.Height = 200;
	      	area9.Width = '100%';
	      	area9.ReplaceTextarea(); 
		</script>         </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!SYOR</td>#end
  </tr>
  <tr>
    <td valign="top">10.</td>
    <td valign="top">Ulasan Pengarah Tanah dan Galian Kelantan</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtUlasanPengarah" id="txtUlasanPengarah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!ULASAN_PENGARAH</textarea>
        <div><span id="txtUlasanPengarah_num" style="color:blue;" ></span></div>
      <script>	
		    var area10 = new FCKeditor('txtUlasanPengarah');
	      	area10.BasePath = '/${appname}/library/fck/' ;
	      	area10.Height = 200;
	      	area10.Width = '100%';
	      	area10.ReplaceTextarea(); 
		</script>        </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!ULASAN_PENGARAH</td>#end
  </tr>
  <tr>
    <td valign="top">11.</td>
    <td valign="top">Keputusan</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td>
    <textarea name="txtKeputusan" id="txtKeputusan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!KEPUTUSAN</textarea>
        <div><span id="txtKeputusan_num" style="color:blue;" ></span></div>
      <script>	
		    var area11 = new FCKeditor('txtKeputusan');
	      	area11.BasePath = '/${appname}/library/fck/' ;
	      	area11.Height = 200;
	      	area11.Width = '100%';
	      	area11.ReplaceTextarea(); 
		</script>         </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!KEPUTUSAN</td>#end
  </tr>
</table>
</fieldset>

<input name="txtKesimpulan" type="hidden" id="txtKesimpulan" value="$!KESIMPULAN" />
<input name="txtKedudukanLaporanTanah" type="hidden" id="txtKedudukanLaporanTanah" value="$!KEDUDUKAN_LAPORAN_TNH" />
<input name="txtPandangan" type="hidden" id="txtPandangan" value="$!PANDANGAN" />
<input name="txtImplikasi" type="hidden" id="txtImplikasi" value="$!IMPLIKASI" />
<input name="txtAnggaranKos" type="hidden" id="txtAnggaranKos" value="$!ANGGARAN_KOS" />
<input name="txtPerakuanPentadbir" type="hidden" id="txtPerakuanPentadbir" value="$!PERAKUAN_PENTADBIR_TNH" />
<input name="txtNilaiTanah" type="hidden" id="txtNilaiTanah" value="$!NILAI_ATAS_TANAH" />
<input name="txtPengecualianUpahUkur" type="hidden" id="txtPengecualianUpahUkur" value="$!PENGECUALIAN_UPAH_UKUR" />
<input name="txtHalLain" type="hidden" id="txtHalLain" value="$!HAL_LAIN" />
<input name="txtUlasan" type="hidden" id="txtUlasan" value="$!ULASAN" />
<input name="txtUlasanJT" type="hidden" id="txtUlasanJT" value="$!ULASAN_JABTEKNIKAL" />
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
check_length_txtPerihalPohon('onload');
check_length_txtLatarBelakang('onload');
check_length_txtPengesahanPeruntukan('onload');
check_length_txtPerihalTanah('onload');
check_length_txtNamaTuanTanah('onload');
check_length_txtJawatankuasaTanah('onload');
check_length_txtAsasPertimbangan('onload');
check_length_txtSyor('onload');
check_length_txtUlasanPengarah('onload');
check_length_txtKeputusan('onload');
}


function FCKeditor_OnComplete(editorInstance){
	if (editorInstance.Name == "txtTajuk" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtTajuk);				
	}
	else if (editorInstance.Name == "txtTujuan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtTujuan);				
	}	
	else if (editorInstance.Name == "txtPerihalPohon" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPerihalPohon);				
	}
	else if (editorInstance.Name == "txtLatarBelakang" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtLatarBelakang);				
	}	
	else if (editorInstance.Name == "txtPengesahanPeruntukan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPengesahanPeruntukan);				
	}	
	else if (editorInstance.Name == "txtPerihalTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPerihalTanah);				
	}	
	else if (editorInstance.Name == "txtNamaTuanTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtNamaTuanTanah);				
	}	
	else if (editorInstance.Name == "txtJawatankuasaTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtJawatankuasaTanah);				
	}	
	else if (editorInstance.Name == "txtAsasPertimbangan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtAsasPertimbangan);				
	}	
	else if (editorInstance.Name == "txtSyor" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtSyor);				
	}	
	else if (editorInstance.Name == "txtUlasanPengarah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtUlasanPengarah);				
	}	
	else if (editorInstance.Name == "txtKeputusan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtKeputusan);				
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
function check_length_txtPerihalPohon(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtPerihalPohon');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtPerihalPohon').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtPerihalPohon';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtPerihalPohon_check';
var text_num = 'txtPerihalPohon_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'perihal agensi pemohon';




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
var value_field = 'peruntukan pengambilan tanah';




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
var value_field = 'perihal tanah';




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
function check_length_txtNamaTuanTanah(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtNamaTuanTanah');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtNamaTuanTanah').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtNamaTuanTanah';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtNamaTuanTanah_check';
var text_num = 'txtNamaTuanTanah_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'nama-nama tuan tanah';




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
function check_length_txtJawatankuasaTanah(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtJawatankuasaTanah');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtJawatankuasaTanah').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtJawatankuasaTanah';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtJawatankuasaTanah_check';
var text_num = 'txtJawatankuasaTanah_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'perakuan jawatankuasa hal ehwal tanah';




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
var value_field = 'asas - asas pertimbangan';




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
var value_field = 'ulasan pengarah';




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
var value_field = 'keputusan';




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
