#if($mode == 'new' ||($mode == 'kemaskini'&& $listGambarPembangunan.size()==0) )
      		<div id="imejId">
		               		<table width="100%" border=0>
		            		<tr>
								<td valign="top" width="1%">
								<span class="labelmandatory">##if ($mode != 'readonly') * #end </span></td>				        
								<td width="30%">
									<div align="right" class="labelinput">
									<div align="left">Bil. Lampiran</div>
									</div>
								</td>				      	
								<td width="1%">:</td>				        
								<td width="68%">
									<input type=text size=2 name=jumlahlampiran  $!RO_General onBlur="doChangeJumlahLampiran1('$!IDJadualLampiran',this,'$!action');" 
	              					value=$!num_files> (<font size=1 color=red>Sila masukkan jumlah lampiran</font>)
								</td>
							</tr>
							<tr>
								<td valign="top" width="1%">
									<span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
								<td valign="top" width="30%">
									<div align="right" class="labelinput">
									<div align="left"> Direktori  </div>
									</div>
								</td>				      	
								<td valign="top" width="1%">:</td>				        
								<td width="68%">
									#foreach( $i in [1..$num_files] )							
									<input id="fileupload" name="fileupload" type="file" size="40" $readOnly  class="$disabled" /></br>
									#end
								</td>
							</tr>
							<tr>
								<td valign="top" width="1%">
								</td>				        
								<td width="30%" valign="top">
									<div align="right" class="labelinput">
									<div align="left"> Perihal Imej</div>
									</div>
								</td>				      	
								<td width="1%" valign="top">:</td>		
									        
								<td width="68%">
									<input type="hidden" name="sizePicYgAda" value="0" />	
									<textarea name="txtPerihalImej" cols="43" rows="5"  id="txtPerihalImej"  style="text-transform:uppercase;"></textarea>
								</td>
					</tr>
							<tr>
								<td valign="top" width="1%">
									<span class="labelmandatory"></span></td>				        
								<td width="30%">
									<div align="right" class="labelinput">
									<div align="left"></div>
									</div>
								</td>				      	
								<td width="1%"></td>				        
								<td width="68%">
									<i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik imej adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz imej yang lebih kecil.</span></span>
								</td>
							</tr> 
			</table></div>
#end