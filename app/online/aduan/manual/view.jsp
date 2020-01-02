<p>
  <input name="idComplaint" type="hidden" id="idComplaint" value="$!complaint.id"/>
   <input name="mode" type="hidden" id="mode" value="$mode"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset><legend><strong>MAKLUMAT ADUAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td>&nbsp;</td>
            <td align="right">Nama<font color="red">*</font> </td>
            <td >:</td>
            <td><input TABINDEX="1" type=text name="name" size="50" maxlength="50" onblur="this.value=this.value.toUpperCase();" value="$!complaint.namaPengadu"></td>
          </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" align="right"><font color="red">*</font>No. Telefon</td>
          <td width="1%" >:</td>
          <td width="70%"><input name="phone" type=text  tabindex="1" value="$!complaint.phonePengadu" size="14" maxlength="14" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="right">Emel</td>
          <td>:</td>
          <td><input TABINDEX="1" size=50 maxlength=100 onClick=""
        	value="$!complaint.emailPengadu" type="text" name="email" id="email" autocomplete="off" />
        	</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="right">Jenis Aduan<font color="red">*</font></td>
          <td>:</td>
          <td><select name="idJenisAduan">
				#foreach($type in $types) 
					<option value="$!type.id" #if($type.id==$complaint.type.id) selected="selected" #end>$!type.code - $!type.description</option>
				#end 
			</select>
            </td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="right">Sumber Aduan<font color="red">*</font></td>
          <td>:</td>
          <td><select name="idSumberAduan">
				#foreach($source in $sources) 
					<option value="$!source.id" #if($source.id==$complaint.source.id) selected="selected" #end>$!source.code - $!source.description</option>
				#end 
			</select>
            </td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">Butiran Aduan <font color="red">*</font></td>
          <td valign="top">:</td>
          <td><textarea rows="10" cols="100" name="catatan" onBlur="this.value=this.value.toUpperCase();">$!complaint.catatan</textarea></td>
        </tr> 
        <tr>
        	<td>&nbsp;        	</td>
        	<td valign="top" align="right">
        		Lampiran        	</td>
        	<td valign="top">
        	:        	</td>
        	<td>
        	
			    <div id="file-uploader-demo1">		
					<noscript>			
						<p>Please enable JavaScript to use file uploader.</p>
						<!-- or put a simple form for upload here -->
					</noscript>         
				</div>        	
				<script>
				createUploader();
				function createUploader(){            
		            
		            var uploader = new qq.FileUploader({
		                element: document.getElementById('file-uploader-demo1'),
		                action: '../fileuploader',
		                params: {
		                    param1: 'aduan'
		                    
		                },
		                debug: true
		            });           
		        }


			</script>				</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2" valign="bottom">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%">
    				#if ($mode == 'simpanBaru')
    				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:daftar()" />
                    #elseif ($mode == 'simpanKemasKini')
    			    <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Kemaskini" onclick="javascript:simpanKemaskini()" />
                    #end
                    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali()"/> </td>
  </tr>
</table>

 

