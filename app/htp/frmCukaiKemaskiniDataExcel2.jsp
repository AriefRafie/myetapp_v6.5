#set ($inputstyle = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )
<table width="98%" border="0">
	<tr>
		<td>
			

<fieldset><legend>FAIL CUKAI KEMASKINI SEMASA</legend>
			<fieldset>
			<legend>Maklumat Paparan</legend>
				<table width="100%" border="0">
				  <tr>
				    <td width="29%"><div align="right">Negeri</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$socNegeri</td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">Daerah</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$socDaerah</td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">
				      <div align="right">Bandar/Pekan/Mukim</div>
				    </div></td>
				    <td width="1%">:</td>
				    <td width="70%">$socMukim</td>
				  </tr>
		
				  <tr>
				    <td width="29%">&nbsp;</td>
				    <td width="1%">&nbsp;</td>
				    <td width="70%">
				    	<input class="stylobutton"  name="cmdCari" id="cmdCari" value="Papar" type="button" onclick="javascript:carianFail()" />
				      	<input class="stylobutton"  name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onclick="javascript:cancel()" /></td>
				  </tr>
				</table>
			</fieldset>
	#parse("app/utils/record_paging.jsp")

	<table width="100%" border="0">
		<tr class="table_header">
		    <td width="3%">Bil.</td>
		    <td width="47%">Jenis dan No.Hakmilik / No. Lot</td>
		    <td width="5%">T/Daftar</td>
		    <td width="10%">Fail Dari</td>
		    <td width="10%">Tali Air (RM</span>)</td>
		    <td width="10%">Parit (RM </span>)</td>
		    <td width="10%">Cukai (RM</span>)</td>
		    <td width="5%">Proses</td>
		</tr>
  	#set ( $cnt = 0 )			
  	##foreach ( $senarai in $SenaraiFailOrig )
  	#foreach ( $senarai in $SenaraiFail )
  		#set ( $cnt = $cnt + 1 )
     	#set( $i = $velocityCount )
        #if ( ($i % 2) == 0 )
        	#set( $row = "row2" )
       	#else
        	#set( $row = "row1" )
      	#end
                
        #if($senarai.cukai_taliair != $senarai.cukai_taliair2)
        	#set ($inputstyle = "class=enabled" )
      	#else
        	#set ($inputstyle = "class=disabled" )
       	#end
                
 		#if($senarai.cukai_parit != $senarai.cukai_parit2)
        	#set ($inputstyle2 = "class=enabled" )
      	#else
        	#set ($inputstyle2 = "class=disabled" )
      	#end
                
        #if($senarai.CUKAI_KENA_BAYAR != $senarai.cukai_kena_bayar)
        	#set ($inputstyle3 = "class=enabled" )
      	#else
        	#set ($inputstyle3 = "class=disabled" )
     	#end
        <tr>
                      <td rowspan="2" class="$row">$cnt.</td>
                      <td rowspan="2" class="$row">
                      	$!senarai.NAMA_NEGERI/$!senarai.NAMA_DAERAH/$!senarai.NAMA_MUKIM $!senarai.labelHakmilik/ $!senarai.KETERANGAN_LOT$!senarai.NO_LOT <br>
                      	$!senarai.kegunaan
                      </td>
                      <input type="hidden" nama="senaraiNO_HAKMILIKUPLOAD" id="senaraiNO_HAKMILIKUPLOAD" value="$senarai.NO_HAKMILIKUPLOAD" >
                      <td rowspan="2" class="$row">$!senarai.tarikhDaftar</td>
                      <td class="$row">PTG/PTD</td>
                      <td class="$row"><input  value="$!UTIL.format2Decimal($senarai.cukai_taliair)" class=disabled disabled></td>
                      <td class="$row"><input  value="$!UTIL.format2Decimal($senarai.cukai_parit)" class=disabled disabled ></td>
                      <td class="$row">
                      	<input  value="$!UTIL.format2Decimal($senarai.CUKAI_KENA_BAYAR)" class=disabled disabled >
                      	<input type="hidden" name="tahun_upload" value="$!senarai.tahun">
                      </td>
                      <td rowspan="2" class="$row"><input type="button" name="btncukaikemaskini" id="btncukaikemaskini" value="Kemaskini Fail" onclick="cukaikemaskini()"></td>
		</tr>
    
    	<tr>
    					<td class="$row">JKPTG</td>
    					<td class="$row"><input type="text" name= "cukaitaliair" id="cukaitaliair" value="$!UTIL.format2Decimal($senarai.cukai_taliair2)" $inputstyle></td>
					    <td class="$row"><input type="text" name="cukaiparit" id="cukaiparit"value="$!UTIL.format2Decimal($senarai.cukai_parit2)" $inputstyle2></td>
					    <td class="$row"><input type="text" nama="cukaikenabayar" id="cukaikenabayar" value="$!UTIL.format2Decimal($senarai.cukai_kena_bayar)" $inputstyle3></td>  
    					<input type="hidden" nama="senaraiNolot" id="senaraiNolot" value="$senarai.NO_LOT" >
						<input type="hidden" nama="senaraiID_HAKMILIKCUKAI" id="senaraiID_HAKMILIKCUKAI" value="$senarai.ID_HAKMILIKCUKAI" >
						<input type="hidden" nama="senaraiNO_HAKMILIK" id="senaraiNO_HAKMILIK" value="$senarai.NO_HAKMILIK" >

  		</tr>
    #end
 
   	#if ($cnt == 0)
  		<tr>
    		<td colspan="5" class="$row"><font color="#FF0000">Tiada Rekod.</font></td>
  		</tr>
 	#end
 	
	</table>
	
</fieldset>

		</td>
	</tr>
	<input name="carian" type="hidden" value="$!iscarian" >
	
</table>

<script>

function cukaikemaskini(){
	doAjaxCall${formName}("CukaiKemaskini");
}

function CetakSenarai(){
	doAjaxCall${formName}("CetakSenaraiKemaskini");
}

function doChangeNegeriX() {
	doAjaxCall${formName}("","mode=changeNegeri");
}

function doChangeDaerahX() {
	doAjaxCall${formName}("","mode=changeDaerah");
}

function carianFail(){
	var command = 'carian';
	doAjaxCall${formName}(command);	
}

</script>
