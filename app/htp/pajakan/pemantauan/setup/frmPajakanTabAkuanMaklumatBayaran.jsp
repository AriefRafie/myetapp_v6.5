<style type="text/css">
<!--
.style1 {color: #0033FF}
.row1_ {
	color: #F00;
}
.e {
	color: #F00;
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
#if ($mode == 'newmaklumatbayaran' || $mode == 'updatemaklumatbayaran' || $mode == 'viewmaklumatbayaran')
	<tr>
    	<td>
    
    		<fieldset><legend><strong>MAKLUMAT BAYARAN</strong></legend>
        
            	<table width="100%" border="0" cellspacing="2" cellpadding="2">
            	##foreach ($beanPajakan in $BeanPajakan)
	            	<tr>
		                <td width="1%" class="e">*</td>
		                <td width="28%">Tarikh Mula Bayaran</td>
		                <td width="1%">:</td>
		                <td width="70%">
		                	<input type="text" name="txdTarikhMulaPajakan" id="txdTarikhMulaPajakan" size="11" value="$!pajakan.getTarikhMulaPajakanString()" onblur="check_date(this);cal_tarikh_luput()" $readOnly/>
		                #if ($mode == 'newmaklumatbayaran' || $mode == 'updatemaklumatbayaran')
		                	<img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhMulaPajakan',false,'dmy');" />
		                #end                
		                </td>
	              	</tr>
	           		<tr>
		            	<td>&nbsp;</td>
		                <td>Tempoh Bayaran</td>
		                <td>:</td>
		                <td>
		                	<input type="text" name="txtTempoh" size="11" value="$!pajakan.getTempohPajakan()" onblur="cal_tarikh_luput()" $readOnly />
                  			Tahun                
		                </td>
	              	</tr>
	              	<tr>
	                	<td valign="top" class="e">*</td>
	                	<td>Tarikh Tamat Bayaran</td>
	                	<td>:</td>
	                	<td>
                		<input type="text" name="txdTarikhTamatPajakan" size="11" value="$!pajakan.getTarikhTamatPajakanString()" onblur="check_date(this)" $readOnly/>
	                  	#if ($mode == 'newmaklumatbayaran' || $mode == 'updatemaklumatbayaran') 
	                  		<img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhTamatPajakan',false,'dmy');" /> 
	                  	#end 
	              		</td>
	              	</tr>
	              	<tr>
		                <td>&nbsp;</td>
		                <td>Kadar Pajakan Setahun (RM)</td>
		                <td>:</td>
		                <td>
		                  <input name="txtKadarPajakan" type="text" value="$!pajakan.getKadarPajakanString()" size="11" $readOnly onblur="validateCurrency(this,this.value,'')"/></td>
		                <td>&nbsp;</td>
	              	</tr>
              		<tr>
				        <td>&nbsp;</td>
				        <td>Kadar Cukai Setahun (RM)</td>
				        <td>:</td>
				        <td> 
				          <input name="txtKadarCukai" type="text" value="$!pajakan.getKadarCukaiString()" size="11" $readOnly onblur="validateCurrency(this,this.value,'')"/></td>
				        <td>&nbsp;</td>
			        </tr>

              ##end
	              <tr>
	                <td valign="top">&nbsp;</td>
	                <td valign="top">&nbsp;</td>
	                <td valign="top">&nbsp;</td>
	                <td valign="top">&nbsp;</td>
	              </tr>
				          <input type="hidden" name="txtidpajakan" value="$!pajakan.getIdPajakan()"/>
				          <input type="hidden" name="txtidpajakankadar" value="$!pajakanKadar.getIdPajakanKadar()"/>

	              
            	</table>
            	
        	</fieldset>
        
    	</td>
	</tr>
#if($mode == 'newmaklumatbayaran' || $mode == 'updatemaklumatbayaran')                            
	<tr>
		<td>
	    	<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	    </td>
	</tr>
            
#end	
	              <tr>
	                 <td align="center">
	                #if ($mode == 'newmaklumatbayaran')
	                	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanMaklumatBayaran()" />
	                    <input class="stylobutton100" type="reset" name="cmdReset" value="Kosongkan"/>
	                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalMaklumatBayaran()" />
	                #elseif ($mode == 'viewmaklumatbayaran')
	                    <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniMaklumatBayaran()" />
	                    <input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:hapusMaklumatBayaran()" />
	                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalMaklumatBayaran()" />
	                    
	                #elseif ($mode == 'updatemaklumatbayaran')
	                  <input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanUpdateMaklumatBayaran()" />
	                    <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
	                  <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:paparPajakanKadar('$!pajakanKadar.getIdPajakanKadar()')" />
	                #end 
	                </td>
	              </tr>

  	<tr>
    	<td>&nbsp;</td>
  	</tr>
  	
  	
  #end
	<tr>
    	<td>
 
    <fieldset>
    <legend><strong>SENARAI MAKLUMAT BAYARAN</strong></legend>
    
    <table align="center" width="100%"> 
            #if ($mode == 'view')
            <tr>
              <td colspan="6" scope="row"><input class="stylobutton100" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarMaklumatBayaran()"/></td>
            </tr>
            #end
            <tr class="table_header">
              <td scope="row" width="3%" align="center"><strong>Bil.</strong></td>
              <!-- <td width="16%"><strong>Tarikh Tandatangan</strong></td> -->
              <td width="20%" align="center"><strong>Tarikh Mula</strong></td>
              <td width="20%" align="center"><strong>Tarikh Tamat</strong></td>              
              <td width="17%" ><strong>Tempoh Bayaran</strong></td>
              <!-- <td width="18%" ><strong>Cara Bayaran</strong></td> -->
              <td width="20%" align="center"><strong>Kadar Pajakan Setahun (RM)</strong></td>
              <td width="20%" align="center"><strong>Kadar Cukai Setahun (RM)</strong></td>
        </tr>
          #set ($list = "")
          #if ($senaraiBayaran.size() > 0)
 			
		#set ($count = 0)
          #foreach ($list in $senaraiBayaran)
  			#set ($count = $count+1)
           	#if ($count == 0)
                #set( $row = "row1" )
            #elseif (($count % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
      <tr>
            <td class="$row" align="center">$!count.</td>
            <td class="$row" align="center"><a href="javascript:paparPajakanKadar('$list.getIdPajakanKadar()')" class="style1">$list.pajakan.getTarikhMulaPajakanString()</a></td>
            <td class="$row" align="center">$!list.pajakan.getTarikhTamatPajakanString()</td>
            <td class="$row" align="center">$!list.getPajakan().getTempohPajakan()</td>
          	<td class="$row" align="right">$!list.getPajakan().getKadarPajakanString()</td>
            <td class="$row" align="right">$!list.getPajakan().getKadarCukaiString()</td>
            <!-- <td class="$row" align="right">$list.kadarPajakan</td>
            <td class="$row" align="right">$list.kadar</td> -->
      </tr>
          #end
          
          #else
	          	<tr>
	            <td class="row1" colspan="6"><font color="#FF0000">Tiada Rekod.</font></td>
	      		</tr>
          	#end
        	</table>
        
    	</fieldset>
       
    	</td>
	</tr>
</table>
