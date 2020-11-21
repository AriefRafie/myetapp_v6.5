<!-- frmCukaiPelarasanhapusSenarai.jsp -->
<!-- CL-02-022 -->
#set ($inputstyle = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )
<table width="98%" border="0">
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
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
					    <td width="29%"><div align="right">No. Hakmilik </div></td>
					    <td width="1%">:</td>
					    <td width="70%"><input type="text" name="txtnohakmilik" size="44" value="$!carianNoHakmilik"/></td>
					</tr>	
		        	<tr>
					    <td width="29%"><div align="right">No. Lot/PT </div></td>
					    <td width="1%">:</td>
					    <td width="70%"><input type="text" name="txtNoLot" size="44" value="$!carianNoLot"/></td>
					</tr>								  
				  <tr>
				    <td width="29%"><div align="right">
				      <div align="right">Tahun</div>
				    </div></td>
				    <td width="1%">:</td>
				    <td width="70%">
					#set ( $selected = "" )
					<select class="autoselect" name="Form_tahun">
				   		<option value="0" $selected>SILA PILIH</option>
				   		#set ( $ints = $!tahuncukai + 2 )
				   		#foreach ( $y in [2007..$ints] )
				   		#if ( $y == $tahunparam)
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
				    <td width="29%">&nbsp;</td>
				    <td width="1%">&nbsp;</td>
				    <td width="70%">
				    	<input class="stylobutton100"  name="cmdCari" id="cmdCari" value="Papar" type="button" onclick="javascript:carianFail()" />
				      	<input class="stylobutton100"  name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onclick="javascript:cancel()" /></td>
				  </tr>
				</table>
			</fieldset>
	#parse("app/utils/record_paging.jsp")

	<table width="100%" border="0">
		<tr class="table_header">
		    <td width="3%">Bil.</td>
		    <td width="36%">Jenis dan No.Hakmilik / No. Lot</td>
		    <td width="5%">T/Daftar</td>
		    <td width="7%">Hakmilik Dari</td>
		    <td width="6%" align="center" >Cukai<br>(RM)</td>
		    <td width="6%" align="center" >TaliAir<br>(RM)</td>
		    <td width="6%" align="center" >Parit<br>(RM)</td>
		    <td width="3%" align="center" >Tunggakan<br>(RM)</td>
		    <td width="3%" align="center" >Denda<br>(RM)</td>
	 		<td width="3%" align="center" >Kutipan Kurang<br>(RM)</td>
		    <td width="3%" align="center" >Pengecualian/ Diskaun/ Remisyen<br>(RM)</td> 
		    <td width="3%" align="center" >Lebihan<br>(RM)</td>
		    <td width="3%" align="center" >Jumlah<br>(RM)</td>			    
		    <!-- modifield by rosli on 14/02/2011	
		    <td width="5%">Proses</td> -->
		    <td width="3%">
		    	<!-- <input type="checkbox" name="all" onClick="doCheckAll()"> -->
		    </td>
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
        <tr class="$row">
       		<td>$cnt.</td>
           	<td>
            	$!senarai.NAMA_NEGERI/$!senarai.NAMA_DAERAH/$!senarai.NAMA_MUKIM $!senarai.labelHakmilik/ $!senarai.KETERANGAN_LOT$!senarai.NO_LOT <br>
                $!senarai.kegunaan
             	<input type="hidden" name="senaraiNO_HAKMILIKUPLOAD" id="senaraiNO_HAKMILIKUPLOAD" value="$senarai.NO_HAKMILIKUPLOAD" >
       		</td>
          	<td>$!senarai.tarikhDaftar</td>
           	<td>REKOD</td>
            <td align="right">$!UTIL.format2Decimal($senarai.cukaiPerluBayar)</td>
            <td align="right">$!UTIL.format2Decimal($senarai.cukaiTaliair)</td>
            <td align="right">$!UTIL.format2Decimal($senarai.cukaiParit)</td>
            <td align="right">$!UTIL.format2Decimal($senarai.cukaiTunggakan)</td>
           	<td align="right">$!UTIL.format2Decimal($senarai.cukaiDenda)</td>
            <td align="right">$!UTIL.format2Decimal($senarai.cukaiPengurangan)</td>
            <td align="right">$!UTIL.format2Decimal($senarai.cukaiPengecualian)</td>
            <td align="right">$!UTIL.format2Decimal($senarai.cukaiLebihan)</td>
            <td align="right">$!UTIL.format2Decimal($senarai.cukaiJumlah)</td>         	
            <td align="center">
            	<!-- <input type="button" name="btnhapus" id="btncukaikemaskini" value="Hapus" onclick="cukaikemaskini('${cnt}')"> -->
            	<a href="#" onClick="javascript:cukaikemaskini('${cnt}')">
	  	       		<img border="0" src="../img/delete.gif" />
	  	       	</a>
                <input type="hidden" name="id_cukaiterperinci${cnt}" value="$!senarai.idCukaiTerperinci">
                <input type="hidden" name="idcukaitemp${cnt}" value="$!senarai.idCukaiTemp">
           	</td>
           	<td></td>
		</tr>
    
    	<!-- <tr>
    					<td class="$row">JKPTG</td>
    					<td class="$row"><input type="text" name= "cukaitaliair" id="cukaitaliair" value="$!UTIL.format2Decimal($senarai.cukai_taliair2)" $inputstyle></td>
					    <td class="$row"><input type="text" name="cukaiparit" id="cukaiparit"value="$!UTIL.format2Decimal($senarai.cukai_parit2)" $inputstyle2></td>
					    <td class="$row"><input type="text" nama="cukaikenabayar" id="cukaikenabayar" value="$!UTIL.format2Decimal($senarai.cukai_kena_bayar)" $inputstyle3></td>  
    					<input type="hidden" nama="senaraiNolot" id="senaraiNolot" value="$senarai.NO_LOT" >
						<input type="hidden" nama="senaraiID_HAKMILIKCUKAI" id="senaraiID_HAKMILIKCUKAI" value="$senarai.ID_HAKMILIKCUKAI" >
						<input type="hidden" nama="senaraiNO_HAKMILIK" id="senaraiNO_HAKMILIK" value="$senarai.NO_HAKMILIK" >

  		</tr> -->
    #end
 
   	#if ($cnt == 0)
  		<tr>
    		<td colspan="14" class="$row"><font color="#FF0000">Tiada Rekod.</font></td>
  		</tr>
 	#end
 	
	</table>
	
</fieldset>

		</td>
	</tr>
		<tr>
    		<td colspan="14" class="$row" class="pautan">
    			<a href="javascript:senaraiHakmilik()">Senarai Hakmilik</a>
    		</td>   		
  		</tr>
</table>
	<input name="carian" type="hidden" value="$!iscarian" >

<script>

	function senaraiHakmilik(){
		var params = "&Form_tahun="+document.${formName}.Form_tahun.value;
		params += "&socNegeri="+document.${formName}.socNegeri.value;
		params += "&socDaerah="+document.${formName}.socDaerah.value;
		params += "&socMukim="+document.${formName}.socMukim.value;
		var url = "../x/${securityToken}/ekptg.view.htp.cukai.FrmCukaiPelarasanHapus?command=hakmilik"+params;
	    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();	
	}
	
	function cukaikemaskiniX(){
		doAjaxCall${formName}("CukaiKemaskini");
	}

	function cukaikemaskini(id){
		//doAjaxCall${formName}("CukaiKemaskini","id_cukaiterperincitemp="+id);
		doAjaxCall${formName}("CukaiKemaskini","bil="+id);
		
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
		document.${formName}.command.value= 'carian'; 		
		//var command = 'carian';
		var command = document.${formName}.command.value;
		doAjaxCall${formName}(command);	
	}

</script>
