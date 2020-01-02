<tr id="div_ALAMAT_KEMENTERIAN$internalType$USER_ID" 
			style="display:none"> 
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
				#if($viewAlamatKJP.size() > 0)
					#if($viewAlamatKJP.ALAMAT1 != "")
					$viewAlamatKJP.ALAMAT1<br>
					#end
					#if($viewAlamatKJP.ALAMAT2 != "")
						$viewAlamatKJP.ALAMAT2<br>
					#end
					#if($viewAlamatKJP.ALAMAT3 != "")
						$viewAlamatKJP.ALAMAT3<br>
					#end
					#if($viewAlamatKJP.POSKOD != "")
						$viewAlamatKJP.POSKOD &nbsp;						
					#end					
					#if($viewAlamatKJP.NAMA_NEGERI != "")
						$viewAlamatKJP.NAMA_NEGERI<br>
					#end
					
					
					<script type="text/javascript">
					document.getElementById("div_ALAMAT_KEMENTERIAN$internalType$USER_ID").style.display="";
					</script>
					
				#end
				</td>
			</tr>