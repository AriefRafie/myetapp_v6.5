#set($tajuk_upload = "")
#set($tajuk_upload = "Muat Naik Dokumen")  


<table border="0" width="100%"  > 
<tr  >
<td valign="top" align="left" >
<a href="#"  onclick="Effect.toggle('div_upload_skrin', 'appear'); return false;"><font color="blue"><b><< Ruangan Muat Naik</b></font></a>  
 </td>
</tr>
</table>
 

<div id="div_upload_skrin" style="display:none;">
<table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<b>$tajuk_upload</b>
 </td>
</tr>
</table>

<table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
<tr>
<td width="70%" valign="top">
      <table width="100%" border="0" cellspacing="2" cellpadding="2" >
      
      
			        	<tr>
			        	  <td width="1%" valign="top"><font color="red">*</font></td>
			              <td width="36%" valign="top">Tajuk Dokumen</td>
			              <td width="1%" valign="top">:</td>
			              <td width="62%" valign="top">
                          
                          #set($tajuk_lampiran = "")                          
                                                   
                          <input type="text" name="tajuk" id="tajuk" maxlength="200" size="50" value="$tajuk_lampiran"  /></td>
			          	</tr>
                        
                        
                          <tr>
          <td  valign="top"><font color="red">*</font></td>
          <td  valign="top">Kategori Lampiran</td>
          <td   valign="top">:</td>
          <td  valign="top">
          
            <select  class="autoselect" name="kategori_lampiran" id="kategori_lampiran" >
            <option value=""  >SILA PILIH KATEGORI</option>
            #foreach($!ja in $!listSenaraiKategoriLampiran) 
            <option value="$!ja.ID_LAMPIRANETANAH"  >$!ja.KETERANGAN_LAMPIRANETANAH</option>
            #end 
            </select>
          
          <font color="blue">** Sila Pastikan Dokumen Ini Dikategorikan </font>
          </td>
        </tr>
                        
                        
        <tr>
          <td  valign="top"><font color="red">*</font></td>
          <td  valign="top">Lampiran</td>
          <td   valign="top">:</td>
          <td  valign="top"><input id="fileupload" name="fileupload" type="file" size="40"/>
          
          </td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>
          
          <input type="button" name="cmdUploadDokumen" id="cmdUploadDokumen" value="Muat Naik & Hantar ke e-Tanah" onClick="simpan('$!id_penarikan','$!id_permohonan','$!jenis_skrin')">
          <br /><font color="blue">** Fail/Dokumen ini akan dimuatnaik terus ke e-Tanah</font>
           </td>
        </tr>
      </table>
      
      
      </td>
      <td valign="top" width="30%">
      #parse("app/integrasi/etanah/pengambilanTanah/PopupPengambilanTanah_dokumen_mandatori.jsp")
      </td>
      </tr>
      </table>
     

    <br>
</div> 