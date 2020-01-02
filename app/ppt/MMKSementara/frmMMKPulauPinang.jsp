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
    <td width="29%" valign="top">Perihal Tanah</td>
    <td width="1%" valign="top">:</td>
    #if($readonly != 'readonly')<td width="70%"><textarea name="txtPerihalTanah" id="txtPerihalTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PERIHALTANAH</textarea>
        <div><span id="txtPerihalTanah_num" style="color:blue;" ></span></div>
      <script>	
		    var area2 = new FCKeditor('txtPerihalTanah');
	      	area2.BasePath = '/${appname}/library/fck/' ;
	      	area2.Height = 200;
	      	area2.Width = '100%';
	      	area2.ReplaceTextarea(); 
		</script>    </td>#else
    <td width="70%" valign="top" bgcolor="#ECE5B6">$!PERIHALTANAH</td>#end
  </tr>
  <tr>
    <td valign="top">3.</td>
    <td valign="top">Perihal Permohonan</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtPerihalPohon" id="txtPerihalPohon"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PERIHAL_POHON</textarea>
        <div><span id="txtPerihalPohon_num" style="color:blue;" ></span></div>
      <script>	
		    var area3 = new FCKeditor('txtPerihalPohon');
	      	area3.BasePath = '/${appname}/library/fck/' ;
	      	area3.Height = 200;
	      	area3.Width = '100%';
	      	area3.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!PERIHAL_POHON</td>#end
  </tr>
  <tr>
    <td valign="top">4.</td>
    <td valign="top">Anggaran Kasar Kos Pengambilan</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtAnggaranKos" id="txtAnggaranKos"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!ANGGARAN_KOS</textarea>
        <div><span id="txtAnggaranKos_num" style="color:blue;" ></span></div>
      <script>	
		    var area4 = new FCKeditor('txtAnggaranKos');
	      	area4.BasePath = '/${appname}/library/fck/' ;
	      	area4.Height = 200;
	      	area4.Width = '100%';
	      	area4.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!ANGGARAN_KOS</td>#end
  </tr>
  <tr>
    <td valign="top">5.</td>
    <td valign="top">Ulasan Jabatan Teknikal</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtUlasanJT" id="txtUlasanJT"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!ULASAN_JABTEKNIKAL</textarea>
        <div><span id="txtUlasanJT_num" style="color:blue;" ></span></div>
      <script>	
		    var area5 = new FCKeditor('txtUlasanJT');
	      	area5.BasePath = '/${appname}/library/fck/' ;
	      	area5.Height = 200;
	      	area5.Width = '100%';
	      	area5.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!ULASAN_JABTEKNIKAL</td>#end
  </tr>
  <tr>
    <td valign="top">6.</td>
    <td valign="top">Ulasan Pentadbir Tanah Daerah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtUlasan" id="txtUlasan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!ULASAN</textarea>
        <div><span id="txtUlasan_num" style="color:blue;" ></span></div>
      <script>	
		    var area6 = new FCKeditor('txtUlasan');
	      	area6.BasePath = '/${appname}/library/fck/' ;
	      	area6.Height = 200;
	      	area6.Width = '100%';
	      	area6.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!ULASAN</td>#end
  </tr>
  <tr>
    <td valign="top">7.</td>
    <td valign="top">Perakuan Pentadbir Tanah Daerah</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtPerakuanPentadbir" id="txtPerakuanPentadbir"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!PERAKUAN_PENTADBIR_TNH</textarea>
        <div><span id="txtPerakuanPentadbir_num" style="color:blue;" ></span></div>
      <script>	
		    var area7 = new FCKeditor('txtPerakuanPentadbir');
	      	area7.BasePath = '/${appname}/library/fck/' ;
	      	area7.Height = 200;
	      	area7.Width = '100%';
	      	area7.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!PERAKUAN_PENTADBIR_TNH</td>#end
  </tr>
  <tr>
    <td valign="top">8.</td>
    <td valign="top">Jawatankuasa Tanah Negeri</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtJawatankuasaTanah" id="txtJawatankuasaTanah"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!JAWATANKUASA_TANAH</textarea>
       <div><span id="txtJawatankuasaTanah_num" style="color:blue;" ></span></div>
      <script>	
		    var area7 = new FCKeditor('txtJawatankuasaTanah');
	      	area7.BasePath = '/${appname}/library/fck/' ;
	      	area7.Height = 200;
	      	area7.Width = '100%';
	      	area7.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!JAWATANKUASA_TANAH</td>#end
  </tr>
  <tr>
    <td valign="top">9.</td>
    <td valign="top">Keputusan Majlis</td>
    <td valign="top">:</td>
    #if($readonly != 'readonly')<td><textarea name="txtKeputusan" id="txtKeputusan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$!KEPUTUSAN</textarea>
       <div><span id="txtKeputusan_num" style="color:blue;" ></span></div>
      <script>	
		    var area7 = new FCKeditor('txtKeputusan');
	      	area7.BasePath = '/${appname}/library/fck/' ;
	      	area7.Height = 200;
	      	area7.Width = '100%';
	      	area7.ReplaceTextarea(); 
		</script>    </td>#else
    <td valign="top" bgcolor="#ECE5B6">$!KEPUTUSAN</td>#end
  </tr>
</table>
</fieldset>

<input name="txtLatarBelakang" type="hidden" id="txtLatarBelakang" value="$!LATARBELAKANG" />
<input name="txtAsasPertimbangan" type="hidden" id="txtAsasPertimbangan" value="$!ASAS_PERTIMBANGAN" />
<input name="txtKesimpulan" type="hidden" id="txtKesimpulan" value="$!KESIMPULAN" />
<input name="txtSyor" type="hidden" id="txtSyor" value="$!SYOR" />
<input name="txtKedudukanLaporanTanah" type="hidden" id="txtKedudukanLaporanTanah" value="$!KEDUDUKAN_LAPORAN_TNH" />
<input name="txtPengesahanPeruntukan" type="hidden" id="txtPengesahanPeruntukan" value="$!PENGESAHAN_PERUNTUKAN" />
<input name="txtPandangan" type="hidden" id="txtPandangan" value="$!PANDANGAN" />
<input name="txtImplikasi" type="hidden" id="txtImplikasi" value="$!IMPLIKASI" />
<input name="txtNilaiTanah" type="hidden" id="txtNilaiTanah" value="$!NILAI_ATAS_TANAH" />
<input name="txtPengecualianUpahUkur" type="hidden" id="txtPengecualianUpahUkur" value="$!PENGECUALIAN_UPAH_UKUR" />
<input name="txtHalLain" type="hidden" id="txtHalLain" value="$!HAL_LAIN" />
<input name="txtNamaTuanTanah" type="hidden" id="txtNamaTuanTanah" value="$!NAMA_TUAN_TANAH" />
<input name="txtUlasanPengarah" type="hidden" id="txtUlasanPengarah" value="$!ULASAN_PENGARAH" />
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
check_length_txtPerihalTanah('onload');
check_length_txtPerihalPohon('onload');
check_length_txtAnggaranKos('onload');
check_length_txtUlasanJT('onload');
check_length_txtUlasan('onload');
check_length_txtPerakuanPentadbir('onload');
check_length_txtJawatankuasaTanah('onload');
check_length_txtKeputusan('onload');
}


function FCKeditor_OnComplete(editorInstance){
	if (editorInstance.Name == "txtTajuk" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtTajuk);				
	}
	else if (editorInstance.Name == "txtTujuan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtTujuan);				
	}	
	else if (editorInstance.Name == "txtPerihalTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPerihalTanah);				
	}	
	else if (editorInstance.Name == "txtPerihalPohon" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPerihalPohon);				
	}	
	else if (editorInstance.Name == "txtAnggaranKos" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtAnggaranKos);				
	}	
	else if (editorInstance.Name == "txtUlasanJT" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtUlasanJT);				
	}	
	else if (editorInstance.Name == "txtUlasan" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtUlasan);				
	}	
	else if (editorInstance.Name == "txtPerakuanPentadbir" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtPerakuanPentadbir);				
	}
	else if (editorInstance.Name == "txtJawatankuasaTanah" ) {	
		editorInstance.Events.AttachEvent('OnSelectionChange',check_length_txtJawatankuasaTanah);				
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
var value_field = 'perihal permohonan';




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
function check_length_txtAnggaranKos(type)
{


var editorInstance = FCKeditorAPI.GetInstance('txtAnggaranKos');
var count = 0;
if(type == 'onload')
{count =  document.getElementById('txtAnggaranKos').value.length;}
else
{count = editorInstance.GetHTML(true).length;}


var my_form = 'txtAnggaranKos';
var maxLen = 3800;
var charlimit = maxLen-1 ;
var alert_field = 'txtAnggaranKos_check';
var text_num = 'txtAnggaranKos_num';
var jenis_field = 'normal';
var mandatory = 'yes';
var value_field = 'anggaran kos';




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
var value_field = 'ulasan pentadbir tanah';




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
var value_field = 'perakuan pentadbir tanah';




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
var value_field = 'jawatan kuasa tanah negeri';




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
var value_field = 'keputusan majlis';




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
