

<table border="0" width="100%">
#if ($result == "Simpan" || $result == "Kemaskini")
	<tr>
		<td><div class=info>$!result Berjaya</div></td>
    </tr>
#end	
	<tr>
		<td>&nbsp;</td>
    </tr>

	<tr>
	    <td>
			<fieldset><legend>MAKLUMAT SUB URUSAN/ STATUS</legend>
				<table border="0" cellpadding="2" cellspacing="2">
	            	<tr>
	             		<td width="15%" >Urusan</td>
	             		<td width="1%">:</td>             
	             		<td width="84%">             
		     				$selectUrusan
	             		</td>
	    			</tr>
	    			<tr>
	         			<td>Sub Urusan</td>
	             		<td>:</td>             
	         			<td>        
		 					$selectSuburusan
	         			</td>
	    			</tr> 
			       	<tr>
			        	<td >Status</td>
	             		<td>:</td>             
			           	<td>             
							$selectStatus2
			           	</td>
			        </tr>
		           	<tr>
		           		<td >Langkah</td>
		         		<td>:</td>             	           
		           		<td>
							#set ( $selected = "" )
							<select class="autoselect" name="Form_langkah">
						   		<option value="0" $selected>Sila Pilih</option>
						   		##set ( $ints = $langkah.size()+1 )
						   		#set ( $ints = 120 )
						   		#foreach ( $y in [-6..$ints] )
						   		#if ( $y == $details.langkah )
						   			#set ( $selected = "selected" )
						   		#else
						   			#set ( $selected = "" )
						   		#end
						   		<option value="$y" $selected>$y</option>
						   		#end
							</select>
		           		</td>
		        	</tr>    
				 	<tr>
				   		<td >Aktif</td>
					  	<td>:</td>             	           
					   <td>
					   		#set ( $selected1 = "" )
					   		#set ( $selected2 = "" )
					   		#if ( 1 == $details.aktif )
					   			#set ( $selected1 = "selected" )
					   			#set ( $selected2 = "" )
					   		#else
					   			#set ( $selected1 = "" )
						   		#set ( $selected2 = "selected" )
					   		#end	   
							<select class="autoselect" name="Form_aktif" >
							        <option value="0">Sila Pilih</option>
							        <option value="1" $selected1>YA</option>
							        <option value="2" $selected2>TIDAK</option>
							</select>
				   		</td>
			        </tr> 
			        	    			
				</table>
			</fieldset>
		</td>
	</tr>    
	<br>  
    <tr>
		<td>
			<fieldset><legend>MAKLUMAT MODUL</legend>
			<table border="0" cellpadding="2" cellspacing="2">
				<tr>
					 <td width="15%">Kumpulan</td>
		             <td width="1%">:</td>             
					 <td width="84%">             
					     $selectgroup
					 </td>
				</tr>
				<tr>
					<td >Modul</td>
	            	<td>:</td>             
				    <td>             
				    	$selectmodul
				  	</td>
				</tr>
			</table>
			</fieldset>		
		</td>
    </tr>	
	<br>  
    <tr>
		<td>
		#if ($mode == "add")
			<input type=button value=Simpan onClick="javascript:doAjaxCall${formname}('doAdd')">
		#else
			<input type=button value=Kemaskini onClick="javascript:doAjaxCall${formname}('doUpdate')">
		#end
			<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack')">
		</td>
    </tr>	
</table>    
	<input type=hidden name="id" value="$id">

<script type="javascript">
	function doChanges() {
		//alert('xxxx');
	  doAjaxCall${formName}("edit");
	}
	
	function doChanges1() {
	  //alert('HAI group');
	  doAjaxCall${formName}("edit1");
	}
	
	function XdoChangesSubmit(command_) {
	  doAjaxCall${formName}(command_);
	}
</script>

