
<table width="100%" border="0">	
	
	<tr>
		<td>&nbsp;</td>
	</tr>

	<tr>
		<td>
	
		<fieldset>
	  	<legend>MAKLUMAT PERMOHONAN</legend>
	  		#parse ("app/htp/pembelian/fail/online/fileInfoOnline.jsp")
	  	</fieldset>
	
		</td>
	</tr>

	<tr>
		<td>
	
<fieldset>
	<legend>MAKLUMAT TANAH</legend>
	<TABLE WIDTH="100%">
		<TR>
			<TD>
				<table WIDTH="100%">
					<TR>
					  <td ><div align="right"><font color="#FF0000">*</font>Negeri :</div></td>
					  <td> $socNegeri</td>
					  <td><div align="right"><font color="#FF0000">*</font>Jenis Hakmilik :</div></td>
					  <td>$socJenisHakmilik</td>
                    </TR>
				    <tr>
				      <td><div align="right"><font color="#FF0000">*</font>Daerah :</div></td>
				      <td>$socDaerah</td>
				      <td><div align="right"><font color="#FF0000">*</font>No. Hakmilik :</div></td>
				      <td><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" maxlength="21" onkeyup="this.value=this.value.toUpperCase();" value="$!urusan.getNohakmilik()" $mode $classdis /></td>
			        </tr>
		         	<tr>
		         	  <td><div align="right"><font color="#FF0000">*</font>Bandar/Pekan/Mukim :</div></td>
		         	  <td>$socMukim</td>
		         	  <td><div align="right">No. Strata</div></td>
                    <td><input type="text" name="noBangunan" size="5" value="$!urusan.getNoBangunan()"/> <input type="text" name="noTingkat" size="5" value="$!urusan.getNoTingkat()"/>
                      <input type="text" name="noPetak" size="5" value="$!urusan.getNoPetak()" id="noPetak"/></td>
		          </tr>
		          <tr>
		            <td><div align="right"><font color="#FF0000">*</font>Tarikh Mula :</div></td>
		            <td><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="10" value="$!urusan.getTarikhMula()" $classDis $mode  />
		              <img src="../img/calendar.gif" border="0" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');" style="display:$Style2" /></td>
		            <td><div align="right"><font color="#FF0000">*</font>Kod/Jenis Lot :</div></td>
		            <td>$socLot</td>
	              </tr>
		          <tr>
		            <td><div align="right"><font color="#FF0000">*</font>Tarikh Luput :</div></td>
		            <td><input type="text" name="txdTarikhLuput" id="txdTarikhLuput" size="10" value="$!urusan.getTarikhLuput()" $classDis $mode />
		              <img src="../img/calendar.gif" border="0" onclick="displayDatePicker('txdTarikhLuput',false,'dmy');" style="display:$Style2" /></td>
		            <td><div align="right"><font color="#FF0000">*</font>No. Lot</div>
		          	 </td>
		          	<td>
                  		<input type="text" name="txtNoLot" id="txtKodLot" maxlength="20" size="20" onkeyup="this.value=this.value.toUpperCase();" value="$!urusan.getNolot()" $mode $classDis>
                	</td>
             		          
		          </tr>
		          
		          <tr>
		            <td><div align="right"><font color="#FF0000">*</font>Unit Luas :</div></td>
		            <td>$socLuas</td>
		            <td><div align="right"></font>Rizab :</div></td>
		            <td>$socRizab</td>
		            </tr>
         	 		 <tr>
         	 		   <td><div align="right"><font color="#FF0000">*</font>Luas :</div></td>
         	 		   <td><label>
         	 		     <input type="text" name="txtLuas" id="txtLuas" maxlength="40"  value="$!urusan.getLuas()" $mode $classDis />
       	 		     </label></td>
         	 		   <td><div align="right"><font color="#FF0000">*</font>Kategori :</div></td>
         	 		   <td>$socKategori</td>
       	 		     </tr>
			          <tr>
			            <td>&nbsp;</td>
			            <td>&nbsp;</td>
			            <td><div align="right">No. Pelan Akui :</div></td>
			            <td><label>
			              <input type="text" name="txtNoPelan" id="txtNoPelan" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$!urusan.getNoPlan()" $mode $classDis />
			              </label></td>
		              </tr>
			          <!-- <tr>
			            <td>&nbsp;</td>
			            <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
			          </tr>
			           <tr>
			             <td colspan="4" align="center">                   </td>
			             <td>&nbsp;</td>
			             <td>&nbsp;</td>
			             <td>&nbsp;</td>
	              </tr> -->
				</table>
			
			</TD>
		
		
		</TR>
	
	
	</TABLE>


</fieldset>
	
		</td>	
	</tr>
	
		#set($perhatian7="<i><font style=font-size:10px>1. Sila pastikan permohonan ini mempunyai hakmilik untuk meneruskan permohonan</font></i>")	
		#set($perhatian7_1="<font style=font-size:10px>1. Sila pastikan permohonan ini mempunyai hakmilik untuk meneruskan permohonan</font>")	

		  	#if ($mode != 'view')
  	<tr>
    	<td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
 	</tr>
  	#end
	<tr>
	<td align="center">
	#if($button == "simpan")
    	<input class="stylobutton100" name="Simpan" value="Simpan" type="button" onclick="javascript:simpanMaklumatTanah()"/> 
        <input class="stylobutton100" name="Kembali" value="Kembali" type="button" onclick="doAjaxCall${formName}('maklumatanahonline','idPermohonan='+$!htpPermohonan.permohonan.getIdPermohonan())"/> 
   	#else
		#set($portal_role = "${session.getAttribute('myrole')}")
		#if ($portal_role!='online_kjpagensi')
        <input class="stylobutton100" name="Simpan" value="Kemaskini" type="button" onclick="javascript:kemaskiniMaklumatTanah()"/>
   		#end
        <input class="stylobutton100" name="Kembali" value="Kembali" type="button" onclick="doAjaxCall${formName}('maklumatanahonline','idPermohonan='+$!htpPermohonan.permohonan.getIdPermohonan())"/>
   	#end
      
	</td>	
	</tr>
</table>

<input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>
<input type="hidden" name="txtidPermohonan2" value="$!urusan.getIdPermohonan()"/>
<input type="hidden" name="txtIdHakmilikUrusan" value="$!urusan.getIdhakmilikurusan()"/>