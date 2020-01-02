<!-- index.jsp -->
<!-- CL-02-022 -->
<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>
#set ($inputstyle = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )

<table width="100%" border="0">
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			

<!-- <fieldset><legend>SENARAI CUKAI SEMASA</legend> -->
			<fieldset>
			<legend>CARIAN</legend>
				<table width="100%" border="0">
				  <tr>
				    <td width="29%"><div align="right">Negeri</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$socNegeri</td>
				  </tr>
				  
				  
			      	<tr>
				        <td align="right"><div align="right">
				        	#if ($idNegeri == '13' )
				        		Bahagian
				        	#else
				        		Daerah
				        	#end
				        	</div></td>
				        <td><div align="center">:</div></td>
				        <td>$socDaerah</td>
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
		
		</td>
	</tr>
	
	<tr>
		<td>
			
		<fieldset><legend>SENARAI HAKMILIK CUKAI</legend>
			

	<table width="100%" border="0">
		<tr>
		      <td colspan="14">
		      	<input type="button" value="Tambah Hakmilik" onclick="tambahHakmilik($!Tahun)" />
		      	<input type="button" value="Tambah Hakmilik [Manual]" onclick="tambahanManual($!Tahun)" />
		      	<!----> <a href="javascript:doAjaxCall${formName}('senaraiFail','tahun=document.${formName}.Form_tahun.value')" style="font-style:oblique">...</a> 
		      </td>
		</tr>
  		<tr>
    		<td colspan="14">
				#parse("app/utils/record_paging.jsp")  		
    		</td>
  		</tr>
		<tr class="table_header">
		    <td width="3%">Bil.</td>
		    <td width="36%">Jenis dan No.Hakmilik / No. Lot/PT</td>
		    <td width="5%">Tarikh/ Daftar</td>
		    <td width="7%">Fail Dari</td>
		    <td width="6%" align="center" >Cukai<br>(RM)</td>
		    <td width="6%" align="center" >Tali Air<br>(RM)</td>
		    <td width="6%" align="center" >Parit<br>(RM)</td>
		    <td width="3%" align="center" >Tunggakan<br>(RM)</td>
		    <td width="3%" align="center" >Denda<br>(RM)</td>
	 		<td width="3%" align="center" >Kutipan Kurang<br>(RM)</td>
		    <td width="3%" align="center" >Pengecualian/ Diskaun/ Remisyen<br>(RM)</td> 
		    <!--modifield by rosli on 26/05/2011 -->
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
        <tr>
       		<td rowspan="0" class="$row">$cnt.</td>
           	<td rowspan="0" class="$row">
            	$!senarai.NAMA_NEGERI/$!senarai.NAMA_DAERAH/$!senarai.NAMA_MUKIM $!senarai.labelHakmilik/ $!senarai.KETERANGAN_LOT$!senarai.NO_LOT <br>
                $!senarai.kegunaan
        	</td>
            	<input type="hidden" nama="senaraiNO_HAKMILIKUPLOAD" id="senaraiNO_HAKMILIKUPLOAD" value="$senarai.NO_HAKMILIKUPLOAD" >
          	<td rowspan="0" class="$row">$!senarai.tarikhDaftar</td>
           	<td class="$row">JKPTG</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPerluBayar)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiTaliair)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiParit)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiTunggakan)</td>
           	<td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiDenda)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPengurangan)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPengecualian)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiLebihan)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiJumlah)</td>         	
            <td rowspan="0" class="$row" align="center">
            	<!-- <input type="button" name="btnhapus" id="btncukaikemaskini" value="Hapus" onclick="cukaikemaskini('${cnt}')"> -->
            	<a href="#" onClick="javascript:cukaikemaskini('${cnt}')">
	  	       		<img border="0" src="../img/delete.gif" />
	  	       	</a>
                <input type="hidden" name="id_cukaiterperinci${cnt}" value="$!senarai.idCukaiTerperinci">
                <input type="hidden" name="idcukaitemp${cnt}" value="$!senarai.idCukaiTemp">
           	</td>
           	<!-- <td rowspan="0" class="$row"></td> -->
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

		
<!-- </fieldset> -->

		</td>
	</tr>
	<!-- <tr>
		<td>
	
		</td>
	</tr> -->
				
	#if ($cnt != 0)
		<tr>
				<td align="center"> 
		    			#if( $!statussemasa.equals('2') && ($!userLevel.equals('3')||$!userLevel.equals('2')||$!userLevel.equals('1')) )
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Hantar Semakan" onclick="doAjaxCall${formName}('simpanpengesahan','statussemasa=3')" />
						
						#elseif ($!statussemasa.equals('3') && ($!userLevel.equals('3')||$!userLevel.equals('2')) )
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Hantar Pengesahan" onclick="doAjaxCall${formName}('simpanpengesahan','statussemasa=4')" />
						
						#elseif ($!statussemasa.equals('4') && ($!userLevel.equals('3')) )
		    				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Hantar Pengesahan(HQ)" onclick="doAjaxCall${formName}('simpanpengesahan','statussemasa=5')" />
                		
                		#end
					<input type="button" class="stylobutton100" name="Cetakp" id="Cetakp" value="Cetak" onclick="javascript:senaraiPenyata('tabledokumen');" />

				</td>
		</tr>
	#end
	
	<tr>
		<td>
			<fieldset id="tabledokumen" style="display:none;">
			<legend>SENARAI PENYATA</legend>
			<table width="100%" border="0">
			  <tr>
			  	    <td>
			  	    	<a href="javascript:openLaporan('ekptg.report.htp.LaporanUtamaHTP','IDSUBURUSAN=43','daerahtahun','HTPCukaiSenaraiHakmilikMengikutDaerahTahunanTemp')" class="pautanms" >LAPORAN TERPERINCI MAKLUMAT CUKAI TANAH MENGIKUT DAERAH(A4)</a>
			  	    </td> 	
			  </tr>   
			  <tr>
			  	    <td>
			  	    	<a href="javascript:openLaporan('ekptg.report.htp.LaporanUtamaHTP','IDSUBURUSAN=43','negeritahun','HTPCukaiSenaraiHakmilikMengikutDaerahTahunanTempKeseluruhan')" class="pautanms" >LAPORAN TERPERINCI MAKLUMAT CUKAI TANAH MENGIKUT DAERAH(KESELURUHAN)</a>
			  	    </td> 	
			  </tr>
			  </table>
			</fieldset>			
		</td>
	</tr>

	
</table>
	<input name="carian" type="hidden" value="$!iscarian" >



<script>
	//[Cetak]
	function senaraiPenyata(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}

	//[link] LAPORAN
	function openLaporan(urli,param,laporan,tem){
		var negeri = document.${formName}.socNegeri.value;
		var daerah = document.${formName}.socDaerah.value;
		var pnegeri = "&ID_NEGERI=0";
		var ptahun = "&TAHUN=";
		var ptem = "&template="+tem;
		var pbulanmula = "&BULANTAHUN=0";
		var pbulantamat = "&BULANTAHUNTMT=0";
		var punit = "&ID_PEJABAT=0";
		var pdaerah = "&ID=0";
	
				/** By Daerah*/
			if(laporan=="daerahtahun"){
				if(document.${formName}.socDaerah.value==""){
					alert("Sila pilih \"Daerah\" terlebih dahulu.");
					document.${formName}.socDaerah.focus(); 
					//document.${formName}.socDaerahBaru.focus(); 
					return;
				}	
				pdaerah = "&ID_KEMENTERIAN=0&ID_AGENSI=0&ID="+daerah;
				mula_tahun = document.${formName}.Form_tahun.value;
				if(mula_tahun==""){
					alert("Sila pilih \"Tarikh\" terlebih dahulu.");
					document.${formName}.Form_tahun.focus(); 
					return;
				}	
				//ptahun = "&TAHUN="+mula_tahun;
				ptahun = "&ID_KEMENTERIAN=0&ID_AGENSI=0&TAHUN="+mula_tahun+"&bulan=01"
				
			}
			if(laporan=="negeritahun"){
				if(document.${formName}.socNegeri.value==""){
					alert("Sila pilih \"Negeri\" terlebih dahulu.");
					document.${formName}.socNegeri.focus(); 
					return;
				}	
				pdaerah = "&ID_KEMENTERIAN=0&ID_AGENSI=0&ID=0"+daerah;
				mula_tahun = document.${formName}.Form_tahun.value;
				if(mula_tahun==""){
					alert("Sila pilih \"Tarikh\" terlebih dahulu.");
					document.${formName}.Form_tahun.focus(); 
					return;
				}	
				pnegeri = "&ID_NEGERI="+document.${formName}.socNegeri.value;
				ptahun = "&ID_KEMENTERIAN=0&ID_AGENSI=0&TAHUN="+mula_tahun+"&bulan=01"
				
			}
		
		var url = "../servlet/"+urli+"?"+param+pnegeri+ptahun+ptem+pbulanmula+pbulantamat+punit+pdaerah;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	}

	function Xcukaikemaskini(){
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
	//[Papar]
	function carianFail(){
		document.${formName}.command.value= 'carian'; 		
		//var command = 'carian';
		var command = document.${formName}.command.value;
		doAjaxCall${formName}(command);	
	}
	
	//TAMBAH HAKMILIK
	function tambahHakmilik(id){
		doAjaxCall${formName}("tambahakmilik");	
	}
	
	//TAMBAH HAKMILIK : [PAPAR]
	function carianHakmilik(){
		var command = 'tambahakmilik';
		document.${formName}.carian.value = 'ya';
		doAjaxCall${formName}(command);	
		
	}
	
	function cmdChangeDaerahHakmilik() {
		doAjaxCall${formName}("tambahakmilik","mode=changeDaerahHakmilik");
	}
	
	//TAMBAH HAKMILIK [Kemaskini]
	function cukaiSimpan(i){	
		doAjaxCall${formName}("CukaiSimpan","bil="+i);
		
	}
	
	//TAMBAH HAKMILIK [Kemaskini Semua]
	function cukaiSimpanSemua(){
		var mycb = new Array();
		var mycukaijumlah = new Array();
		var bilangan = document.${formName}.bilangan.value; 
		var cb = "";
		 for (i = 0; i < document.${formName}.cb.length; i++){
	     	
	     	if(i==0){
	     		if(document.${formName}.cb[i].checked==true){
	     			cb = "cb_=true"; 
	     		}else{
	     			cb = "cb_=false"; 
	     		}
	     		
	     	}else{
	     		if(document.${formName}.cb[i].checked==true){
	     			cb += "&cb_=true"; 
	     		}else{
	     			cb += "&cb_=false"; 
	     		}     			
	     	}
	     }

		doAjaxCall${formName}("cukaikemaskinisemua","bil="+bilangan+"&"+cb);
	
	}
	
	//TAMBAH HAKMILIK [Check All]
	function doCheckAll(){    
	    if (document.${formName}.all.checked == true){
	        if (document.${formName}.cb.length == null){
	            document.${formName}.cb.checked = true;
	        } else {
	            for (i = 0; i < document.${formName}.cb.length; i++){
	                document.${formName}.cb[i].checked = true;
	            }
	        }
	    } else {
	        if (document.${formName}.cb.length == null){
	            document.${formName}.cb.checked = false;
	        } else {
	            for (i = 0; i < document.${formName}.cb.length; i++){
	                document.${formName}.cb[i].checked = false;
	            }
	        }
	    }
	}
	
	
	
	//KEMASUKAN MANUAL
  	function tambahanManual(){
		doAjaxCall${formName}("createNew");	
	}
	
  	//KEMASUKAN MANUAL
	function Kembali(){
		doAjaxCall${formName}("");
	
	}
	
  	//KEMASUKAN MANUAL
	function doChangeDaerahManual(){
		doAjaxCall${formName}("doChangeDaerahManual");
		
	}
	
  	//KEMASUKAN MANUAL
	function validateCurrency(elmnt,content,content2) {
		//if it is character, then remove it..
		if (isNaN(content)) {
			elmnt.value = content2;
			return;
		}
	
		if(content != ""){
			var num = content * 1;
			elmnt.value = num.toFixed(2);
			return;
		} else {
			elmnt.value ="";
			return;
		}
	}
	
  	//KEMASUKAN MANUAL
	function calculate(){
		var tahunan = document.${formName}.txtTahunan.value * 1;
		var CukaiLain = document.${formName}.txtCukaiLain.value * 1;
		var Tungakan = document.${formName}.txtTungakan.value * 1;
		var Denda = document.${formName}.txtDenda.value * 1;
		var Lebihan = document.${formName}.txtLebihan.value * 1;
	
		var jumBayaran = tahunan + CukaiLain + Tungakan + Denda - Lebihan;
	
		document.${formName}.txtJumBayaran.value = jumBayaran.toFixed(2);
	}
	
  	//KEMASUKAN MANUAL
	function simpanManual(){

		if ( document.${formName}.manualNegeri.value == "" ) { 
	  		alert('Sila pilih negeri terlebih dahulu.');
	  		document.${formName}.manualNegeri.focus(); return; 
		}
		if ( document.${formName}.manualDaerah.value == "" ) { 
	  		alert('Sila pilih daerah terlebih dahulu.');
	  		document.${formName}.manualDaerah.focus(); return; 
		}
		if ( document.${formName}.manualMukim.value == "" ) { 
	  		alert('Sila pilih mukim terlebih dahulu.');
	  		document.${formName}.manualMukim.focus(); return; 
		}
	
		if ( document.${formName}.txtNoHakmilik.value == "" ) { 
	  		alert('Sila isikan no hakmilik terlebih dahulu.');
	  		document.${formName}.txtNoHakmilik.focus(); return; 
		}
		if ( document.${formName}.txtNoLot.value == "" ) { 
	  		alert('Sila isikan no lot terlebih dahulu.');
	  		document.${formName}.txtNoLot.focus(); return; 
		}
	
		if ( document.${formName}.txtKegunaanTanah.value == "" ) { 
	  		alert('Sila isikan kegunaan tanah terlebih dahulu.');
	  		document.${formName}.txtKegunaanTanah.focus(); return; 
		}
		
		doAjaxCall${formName}("saveCukai");
	}
	
  	//KEMASUKAN MANUAL CARIAN		
	function kemaskiniManual(){
		doAjaxCall${formName}("viewCukaiDetail",'mode1=2&idCukai=$senarai.ID_CUKAITEMP');
	}
	
  	//KEMASUKAN MANUAL CARIAN		
	function updateManual(){

		if ( document.${formName}.manualNegeri.value == "" ) { 
	  		alert('Sila pilih negeri terlebih dahulu.');
	  		document.${formName}.manualNegeri.focus(); return; 
		}
		if ( document.${formName}.manualDaerah.value == "" ) { 
	  		alert('Sila pilih daerah terlebih dahulu.');
	  		document.${formName}.manualDaerah.focus(); return; 
		}
		if ( document.${formName}.manualMukim.value == "" ) { 
	  		alert('Sila pilih mukim terlebih dahulu.');
	  		document.${formName}.manualMukim.focus(); return; 
		}
	
		if ( document.${formName}.txtNoHakmilik.value == "" ) { 
	  		alert('Sila isikan no hakmilik terlebih dahulu.');
	  		document.${formName}.txtNoHakmilik.focus(); return; 
		}
		if ( document.${formName}.txtNoLot.value == "" ) { 
	  		alert('Sila isikan no lot terlebih dahulu.');
	  		document.${formName}.txtNoLot.focus(); return; 
		}
	
		if ( document.${formName}.txtKegunaanTanah.value == "" ) { 
	  		alert('Sila isikan kegunaan tanah terlebih dahulu.');
	  		document.${formName}.txtKegunaanTanah.focus(); return; 
		}
	
		if ( document.${formName}.txtTahunan.value == "" ) { 
	  		alert('Sila isikan cukai Tahunan dahulu.');
	  		document.${formName}.txtTahunan.focus(); return; 
		}	
		doAjaxCall${formName}("updateCukai");
		
	}
	
  	//KEMASUKAN MANUAL CARIAN
	function cmdChangeDaerahManualCarian(){
		doAjaxCall${formName}("doChangeDaerah");
	}
	
  	//KEMASUKAN MANUAL CARIAN
	function carianHakmilikManual(){
		doAjaxCall${formName}("searchHakmilik");
	}
	
	function dataCukai(){

		if(document.${formName}.Form_tahun.value==0){
			alert("Sila pilih \"Tahun Cukai\" terlebih dahulu.");
			document.${formName}.Form_tahun.focus(); 
			return;
		}
		if ( !window.confirm("Anda Pasti?") ) return;
		
		doAjaxCall${formName}("salincukai");
	
	}
	
	function cancel() {
		document.${formName}.reset();
	}	
	/** Dibuat Pada 02/11/2012 **/
	function kirakira(i,y){
		//alert(i);
		var terkini = 0;
		var taliair = 0;
		var parit = 0;
		var tunggakan = 0;
		var denda = 0;
		var pengurangan = 0;
		var pengecualian = 0;
		var lebihan = 0;
		
		if(y!=1){
			terkini = checkNumericReturn(document.${formName}.cukaiperlubayar[i-1].value) * 1;
			taliair = checkNumericReturn(document.${formName}.cukaitaliair[i-1].value) * 1;
			parit = checkNumericReturn(document.${formName}.cukaiparit[i-1].value) * 1;
			tunggakan = checkNumericReturn(document.${formName}.cukaitunggakan[i-1].value) * 1;
			denda = checkNumericReturn(document.${formName}.cukaidenda[i-1].value) * 1;
			pengurangan = checkNumericReturn(document.${formName}.cukaipengurangan[i-1].value) * 1;
			pengecualian = checkNumericReturn(document.${formName}.cukaipengecualian[i-1].value) * 1;
			lebihan = checkNumericReturn(document.${formName}.cukailebihan[i-1].value) * 1;
		
		}else{
			terkini = checkNumericReturn(document.${formName}.cukaiperlubayar.value) * 1;
			taliair = checkNumericReturn(document.${formName}.cukaitaliair.value) * 1;
			parit = checkNumericReturn(document.${formName}.cukaiparit.value) * 1;
			tunggakan = checkNumericReturn(document.${formName}.cukaitunggakan.value) * 1;
			denda = checkNumericReturn(document.${formName}.cukaidenda.value) * 1;
			pengurangan = checkNumericReturn(document.${formName}.cukaipengurangan.value) * 1;
			pengecualian = checkNumericReturn(document.${formName}.cukaipengecualian.value) * 1;
			lebihan = checkNumericReturn(document.${formName}.cukailebihan.value) * 1;
		}
		
		var perluBayar = terkini + taliair + parit + tunggakan + denda + pengurangan;
		//alert(perluBayar);
		var tolak = pengecualian + lebihan;
		//alert(tolak);
		var jumBayaran = perluBayar - tolak;

		if(y!=1){
			document.${formname}.cukaijumlah[i-1].value = jumBayaran.toFixed(2);
		}else{
			document.${formname}.cukaijumlah.value = jumBayaran.toFixed(2);
		}
		
	}
	
	function checkNumeric(objName){
    	var lstLetters = objName;
    	var lstReplace = lstLetters.replace(/\,/g,'');

  	}
  	
  	function checkNumericReturn(objName){
    	var lstLetters = objName;
    	var lstReplace = lstLetters.replace(/\,/g,'');
    	return lstReplace;
 
  	}


</script>
