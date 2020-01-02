




<script>
if( $jquery('#'+'div_rowPengguna$internalType$viewPejabat.ID_PEJABAT').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPengguna$internalType$viewPejabat.ID_PEJABAT').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewPejabat$internalType$viewPejabat.ID_PEJABAT').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewPejabat$internalType$viewPejabat.ID_PEJABAT').offset().top);
	}
	
}
</script>

<tr id="div_viewPejabat$viewPejabat.ID_PEJABAT">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Pejabat </legend>
<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPejabat --></td>
			</tr>
            #if ($viewPejabat.ID_PEJABAT.equals(""))
			<tr>
    <td valign="top" ><font color="red" ></font></td>			
	<td >Jenis Pejabat</td>
    <td valign="top" >
	:
	</td>
    <td >
    #if ($mode=="1")
    Pejabat JKPTG
    #elseif($mode=="2" )
    Pejabat Urusan
    #end

    <input type="hidden" id="jenisPejabatIns" name="jenisPejabatIns_" value="$mode" >	

		</td>
        </tr>
    #else 
    <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jenis Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if ($JENISPEJ.equals("1")) Pejabat JKPTG #else Pejabat Urusan #end
                <input type="hidden" id="jenisPejabatIns_$viewPejabat.ID_PEJABAT" name="jenisPejabatIns_$viewPejabat.ID_PEJABAT" value="$JENISPEJ">
				</td>
			</tr>
    #end
    
     #if ($mode.equals("2"))
             <tr>
				<td valign="top" ><font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Pejabat 
				Urusan</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="">
               <select id="JENIS_PEJABAT_URUSAN_$viewPejabat.ID_PEJABAT" name="JENIS_PEJABAT_URUSAN_$viewPejabat.ID_PEJABAT" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJJENISPEJABAT )		
							#set ( $selected_ruj = "" )
							#if($viewPejabat.ID_JENISPEJABAT==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
         #if ($viewPejabat.ID_PEJABAT.equals(""))
        <br />
        *Sekiranya ingin menambah jenis pejabat urusan baru, sila tambah Jenis Pejabat di dalam Code Setup*
		<!--<input type="button" id="addJenisPejabat" name="addJenisPejabat" value="Tambah Jenis Pejabat" 
		onClick="doDivAjaxCall$formname('div_TambahJenisPejabat$viewPejabat.ID_PEJABAT','tambahJenisPejabat','ID_JENISPEJABAT=');" >-->
		#end
				</td>
			</tr>
            <tr>
	<td></td><td></td><td></td>
	<td id="div_TambahJenisPejabat$viewPejabat.ID_PEJABAT" width="50" ></td>
	</tr>
            #end
            
            <tr>
				<td valign="top" ><font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Nama Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                <input  size="50" type="text" id="NAMA_PEJABAT_$viewPejabat.ID_PEJABAT" 
				name="NAMA_PEJABAT_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;"
				value="$viewPejabat.NAMA_PEJABAT" >
				</td>
			</tr>
           
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Lain Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="KETERANGAN_$viewPejabat.ID_PEJABAT" 
				name="KETERANGAN_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;"
				value="$viewPejabat.KETERANGAN" >
				</td>
			</tr>
			
			 <tr id="bahagianDiv" >
				<td valign="top" >	
				<font color="red" >*</font>
				</td>			
				<td valign="top" >
				Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="ID_SEKSYEN_$viewPejabat.ID_PEJABAT"  
					name="ID_SEKSYEN_$viewPejabat.ID_PEJABAT" 
					onChange = "doDivAjaxCall$formname('div_ID_PEJABATJKPTG$internalType$viewPejabat.ID_PEJABAT','','');"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJSEKSYEN )		
							#set ( $selected_ruj = "" )
							#if($viewPejabat.ID_SEKSYEN==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</td>
			</tr>
          
            
			<tr>
				<td valign="top" ><font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Alamat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="ALAMAT1_$viewPejabat.ID_PEJABAT" 
				name="ALAMAT1_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;"
				value="$viewPejabat.ALAMAT1" >
				</td>
			</tr>
		
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				
				</td>
				<td valign="top" >
				
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="ALAMAT2_$viewPejabat.ID_PEJABAT" 
				name="ALAMAT2_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;"
				value="$viewPejabat.ALAMAT2" >
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				
				</td>
				<td valign="top" >
				
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="ALAMAT3_$viewPejabat.ID_PEJABAT" 
				name="ALAMAT3_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;"
				value="$viewPejabat.ALAMAT3" >
				</td>
			</tr>
            
			<tr>
				<td valign="top" ><font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Poskod
				</td>
				<td valign="top" >
				:
				</td>
                <!--size="50"-->
				<td valign="top" >
				<input type="text" id="POSKOD_$viewPejabat.ID_PEJABAT" 
				name="POSKOD_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;"
				value="$viewPejabat.POSKOD" maxlength="5">
				</td>
			</tr>
            
            <tr>
				<td valign="top" >	
				<font color="red" >*</font>	
				</td>			
				<td valign="top" >
				Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="ID_NEGERI_$viewPejabat.ID_PEJABAT"  name="ID_NEGERI_$viewPejabat.ID_PEJABAT"
					onChange = "doDivAjaxCall$formname('div_DAERAH_ID$viewPejabat.ID_PEJABAT','showListPejabat','ID_PEJABAT=$viewPejabat.ID_PEJABAT&ID_NEGERI='+$jquery('#ID_NEGERI_$viewPejabat.ID_PEJABAT').val());"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJNEGERI )		
							#set ( $selected_ruj = "" )
							#if($viewPejabat.ID_NEGERI==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end	
							
							
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>					
							
						#end
					</select>
				</td>
			</tr>
			 <tr>
				<td valign="top" >	
				<font color="red" >*</font>		
				</td>			
				<td valign="top" >
				Daerah
				</td>
				<td valign="top" >
				:
				</td>
				<td id="div_DAERAH_ID$viewPejabat.ID_PEJABAT">
					<select id="ID_DAERAH_$viewPejabat.ID_PEJABAT"  
					name="ID_DAERAH_$viewPejabat.ID_PEJABAT" 
					onChange="doDivAjaxCall$formname('div_BANDAR_ID$viewPejabat.ID_PEJABAT','showListBandar','ID_NEGERI='+$jquery('#ID_NEGERI_$viewPejabat.ID_PEJABAT').val()+'&ID_DAERAH='+$jquery('#ID_DAERAH_$viewPejabat.ID_PEJABAT').val());"
					>	  
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJDAERAH )		
							#set ( $selected_ruj = "" )
							#if($viewPejabat.ID_DAERAH==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</td>
			</tr>
            
            <tr>
				<td valign="top" >	
				<font color="red" >*</font>	
				</td>			
				<td valign="top" >
				Bandar
				</td>
				<td valign="top" >
				:
				</td>
				<td id="div_BANDAR_ID$viewPejabat.ID_PEJABAT">
					<select id="ID_BANDAR_$viewPejabat.ID_PEJABAT"  
					name="ID_BANDAR_$viewPejabat.ID_PEJABAT" onChange = "doDivAjaxCall$formname('','','');" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJBANDAR )		
							#set ( $selected_ruj = "" )
							#if($viewPejabat.ID_BANDAR==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</td>
			</tr>
            
            <tr>
				<td valign="top" ><font color="red" >*</font>				
				</td>			
				<td valign="top" >
				No.Telefon
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="NO_TEL_$viewPejabat.ID_PEJABAT" 
				name="NO_TEL_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;"
				value="$viewPejabat.NO_TEL" >
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Faks</td>
				<td valign="top" >
				:</td>
				<td valign="top" >
				<input  size="50" type="text" id="NO_FAX_$viewPejabat.ID_PEJABAT" 
				name="NO_FAX_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;"
				value="$viewPejabat.NO_FAX" >
				</td>
			</tr>
            
            
            <tr>
				<td valign="top" ><font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Emel</td>
				<td valign="top" >
				:</td>
				<td valign="top" >
				<input  size="50" type="text" id="EMEL_$viewPejabat.ID_PEJABAT" 
				name="EMEL_$viewPejabat.ID_PEJABAT" value="$viewPejabat.EMEL" >
				</td>
			</tr>
				
                <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Terlibat dalam Modul Integrasi
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #set($radio1="")
				#set($radio2="")
				
				#if($viewPejabat.FLAG_INT=="1")
				#set($radio1="checked")
				#set($radio2="")
				#else
				#set($radio1="")
				#set($radio2="checked")
				#end
                
                <input type="radio" $radio1 onClick="sendValueJenisPejabat(this,this.value,'FLAG_INT_$viewPejabat.ID_PEJABAT')" id="radio_FLAG_INT_$viewPejabat.ID_PEJABAT" name="radio_FLAG_INT_$viewPejabat.ID_PEJABAT" value="1" >YA
				&nbsp;&nbsp;
				<input type="radio" $radio2 onClick="sendValueJenisPejabat(this,this.value,'FLAG_INT_$viewPejabat.ID_PEJABAT')" id="radio_FLAG_INT_$viewPejabat.ID_PEJABAT" name="radio_FLAG_INT_$viewPejabat.ID_PEJABAT" value="2" >TIDAK
								
				<input type="hidden" id="FLAG_INT_$viewPejabat.ID_PEJABAT" name="FLAG_INT_$viewPejabat.ID_PEJABAT" value="$viewPejabat.FLAG_INT" >
                
				</td>
			</tr>
            	
    		 <tr>
				<td valign="top" ><font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="FLAG_AKTIF_$viewPejabat.ID_PEJABAT" name="FLAG_AKTIF_$viewPejabat.ID_PEJABAT">
				#set ( $selected_aktif = "" )
				#set ( $selected_tidakaktif = "" )
							#if($viewPejabat.FLAG_AKTIF=="AKTIF" || $viewPejabat.FLAG_AKTIF=="")
								#set ( $selected_aktif = "selected" )
							#else
								#set ( $selected_tidakaktif = "selected" )
							#end
				<option value="Y" $selected_aktif >AKTIF</option>
				<option value="N" $selected_tidakaktif >TIDAK AKTIF</option>
				</select>
				</td>
			</tr>
    		
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
                #if ($mode == "1")
                #set ($divName = "div_ViewJKPTG")
                #else 
                #set ($divName = "div_ViewURUSAN")
                #end
               	#if ($viewPejabat.ID_PEJABAT!= "" )
                 <input type="button" id="cmdEditPejabat" name="cmdEditPejabat" value="Simpan" 
                onClick="if(valEditPejabat('$viewPejabat.ID_PEJABAT') == true){doDivAjaxCall$formname('$divName$viewPejabat.ID_PEJABAT$mode','savePejabat','ID_PEJABAT=$viewPejabat.ID_PEJABAT&JENISPEJ=$JENISPEJ&mode=$mode');}" >
	   			<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" value="Tutup" 
	   			onClick="doDivAjaxCall$formname('$divName$viewPejabat.ID_PEJABAT$mode','close_Pejabat','ID_PEJABAT=$viewPejabat.ID_PEJABAT&mode=$mode');" >
	   			#else	
                 <input type="button" id="cmdEditPejabat" name="cmdEditPejabat" value="Simpan" 
                onClick="if(valEditPejabat('') == true){doDivAjaxCall$formname('$divName$mode','savePejabat','mode=$mode&ID_PEJABAT=&JENISPEJ='+$jquery('#jenisPejabatIns').val());}" >
				<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" 
				onclick="doDivAjaxCall$formname('$divName$mode','close_Pejabat','ID_PEJABAT=&mode=$mode');" value="Tutup" >	
			#end
            </td>
            </tr>
		</table>
</fieldset>


	<br>
</td>		
</tr>