<!-- frmCukaiKemaskiniDataExcel2.jsp -->
<!-- CL-02-022 -->
<script type="text/javascript" src="../library/online/prototype.js"></script>
<script type="text/javascript" src="../library/online/scriptaculous/scriptaculous.js"></script>
<script type="text/javascript" src="../library/online/tooltips.js"></script>

<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>
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
			

<fieldset><legend>Maklumat Hakmilik</legend>
			<fieldset>
			<legend>Pilihan</legend>
				<table width="100%" border="0">
				  <tr>
				    <td width="29%"><div align="right">Negeri</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$socNegeri &nbsp;
				    	<!--	<input class="stylobutton"  name="cmdnegeri" value="Data Cukai Negeri" type="button" onclick="javascript:dataCukaiNegeri()" /> -->				    
				    </td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">Daerah</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$socDaerah
				     &nbsp;
				    <!--		<input class="stylobutton"  name="cmddaerah" value="Data Cukai Daerah" type="button" onclick="javascript:dataCukaiDaerah()" /> -->
				    </td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">
				      <div align="right">Bandar/Pekan/Mukim</div>
				    </div></td>
				    <td width="1%">:</td>
				    <td width="70%">$socMukim
				         &nbsp;
				    	<!--	<input class="stylobutton"  name="cmdmukim" value="Data Cukai Mukim" type="button" onclick="javascript:dataCukaiMukim()" /> -->
				    </td>
				  </tr>

					<tr>
					    <td width="29%"><div align="right">No. Hakmilik </div></td>
					    <td width="1%">:</td>
					    <td width="70%"><input type="text" name="txtnohakmilik" size="44" value="$!carianNoHakmilik"/></td>
					</tr>				  
		        	<tr>
					    <td width="29%"><div align="right">No. Lot/PT </div></td>
					    <td width="1%">:</td>
					    <td width="70%"><input type="text" name="txtNoLotc" size="44" value="$!carianNoLot"/></td>
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
					   &nbsp;
				    		<input class="stylobutton"  name="cmdtahun" value="Data Cukai " type="button" onclick="javascript:dataCukai()" />
			    
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
	#parse("app/htp/cukai/recordPagingCukai.jsp")

	<table style="width:100%">
		    <tr>
		      <td colspan="14">
		      	<!-- 	<a href="javascript:tambahanManual($!Tahun)" class="help" title="Senarai"> Tambah
		      		</a> -->
		      	<input type="button" value="Kemasukan Manual" onclick="tambahanManual($!Tahun)" class="help"/>
		      	<a class="help" href="javascript:doAjaxCall${formName}('senaraiFail','tahun=document.${formName}.Form_tahun.value')" style="font-style:oblique">...</a>
		     	
		     	<div id="div_cukai"  style="width:40"></div>
		      
		      </td>
		    </tr>
		<tr class="table_header">
		    <td width="3%">Bil.</td>
		    <td width="36%">Jenis dan No.Hakmilik / No. Lot</td>
		    <td width="5%">T/Daftar</td>
		    <td width="7%">Hakmilik Dari</td>
		    <td width="6%" align="center" >Cukai<br>(RM)</td>
		    <td width="6%" align="center" >Tali Air<br>(RM)</td>
		    <td width="6%" align="center" >Parit<br>(RM)</td>
		    <td width="3%" align="center" >Tunggakan<br>(RM)</td>
		    <td width="3%" align="center" >Denda<br>(RM)</td>
	 		<td width="3%" align="center" >Kutipan Kurang<br>(RM)</td>
		    <td width="3%" align="center" >Pengecualian/ Diskaun/ Remisyen<br>(RM)</td> 
		    <td width="3%" align="center" >Lebihan<br>(RM)</td>
		    <td width="3%" align="center" >Jumlah<br>(RM)</td>		    
		    <!-- modifield by rosli on 14/02/2011	
		    <td width="5%">Proses</td> -->
		    <td width="3%"><input type="checkbox" name="all" onClick="doCheckAll()"></td>
		</tr>
  	#set ( $cnt_ = 0 )			
  	#set ( $cnt = 0 )			
   	#set ( $cntTotal = $SenaraiFail.size() )			
  	##foreach ( $senarai in $SenaraiFailOrig )
  	#foreach ( $senarai in $SenaraiFail )
  		#set ( $cnt = $cnt + 1 )
     	#set( $i = $velocityCount )
        #if ( ($i % 2) == 0 )
        	#set( $row = "row2" )
       	#else
        	#set( $row = "row1" )
      	#end
                
        #if($!senarai.cukaiTaliair != $!senarai.cukaiTaliair2)
        	#set ($inputstyle = "class=enabled" )
      	#else
        	#set ($inputstyle = "class=disabled" )
       	#end
                
 		#if($senarai.cukaiParit != $senarai.cukaiParit2)
        	#set ($inputstyle2 = "class=enabled" )
      	#else
        	#set ($inputstyle2 = "class=disabled" )
      	#end
                
        #if($senarai.CUKAI_KENA_BAYAR != $senarai.cukai_kena_bayar)
        	#set ($inputstyle3 = "class=enabled" )
      	#else
        	#set ($inputstyle3 = "class=disabled" )
     	#end

  		#set ( $cnt_ = $cnt_ + $senarai.cukaiJumlah )
        		<tr>
        			
               		<input type="hidden" nama="senaraiNO_HAKMILIKUPLOAD" id="senaraiNO_HAKMILIKUPLOAD" value="$!senarai.NO_HAKMILIKUPLOAD" >
                  	<input type="hidden" name="tahun_upload" value="$!senarai.tahun">
                	<td rowspan="2" class="$row">$cnt.</td>
                  	<td rowspan="2" class="$row">
                      	$!senarai.NAMA_NEGERI/$!senarai.NAMA_DAERAH/$!senarai.NAMA_MUKIM $!senarai.labelHakmilik/ $!senarai.KETERANGAN_LOT$!senarai.NO_LOT <br>
                      	$!senarai.kegunaan
                    </td>
                   	<td rowspan="2" class="$row">$!senarai.tarikhDaftar</td>
                    <td class="$row">REKOD</td>
                    <td class="$row">
                      	<input size="10" name="cukaikenabayarlama${cnt}" value="$!UTIL.format2Decimal($senarai.cukaiTerkini)" disabled class="inputnumberdisabled" >
                    </td>                    
                    <td class="$row">
                    	<input size="10" value="$!UTIL.format2Decimal($senarai.cukaiTaliair)" disabled class="inputnumberdisabled" >
                    </td>
                    <td class="$row">
                    	<input size="10" value="$!UTIL.format2Decimal($senarai.cukaiParit)" disabled class="inputnumberdisabled" >
                    </td>

                    <td class="$row">
                    	<input size="10" value="$!UTIL.format2Decimal($senarai.cukaiTunggakan)" disabled class="inputnumberdisabled" >
                    </td>
                    <td class="$row">
                    	<input size="10" value="$!UTIL.format2Decimal($senarai.cukaiDenda)" disabled class="inputnumberdisabled" >
                    </td>
                    <td class="$row">
                      	<input size="10" value="$!UTIL.format2Decimal($senarai.cukaiPengurangan)" disabled class="inputnumberdisabled" >
                    </td>
                    <!-- 
                    modifield by rosli on 26/05/2011 -->
                     <td class="$row">
                    	<input size="10" value="$!UTIL.format2Decimal($senarai.cukaiPengecualian)" disabled class="inputnumberdisabled" >
                    </td>
                    <td class="$row">
                    	<input size="10" value="$!UTIL.format2Decimal($senarai.cukaiLebihan)" disabled class="inputnumberdisabled" >
                    </td>
                   
                   	<!-- <td rowspan="2" class="$row">
                      	 <input type="button" name="btncukaikemaskini" id="btncukaikemaskini" value="Kemaskini" 
                      	onclick="cukaikemaskini(${cnt})"> -->
                    <td class="$row">
                    	<!-- <input size="10" value="$!UTIL.format2Decimal($senarai.cukaiJumlah)" disabled class="inputnumberdisabled" > -->
                  	</td>
                    <td rowspan="2" class="$row">
                    	<input class="cb" type="checkbox" name="cb" value="$!cnt">
                  	</td>
				</tr>   
		    	<tr>
		    		<td class="$row">CUKAI</td>
					<td class="$row">
						<!-- <input type="text" size="10" name="cukaiperlubayar${cnt}" id="cukaiperlubayar${cnt}" value="$!UTIL.format2Decimal($senarai.cukaiTerkini)" $inputstyle onblur="kirakira(${cnt});"> -->
						<input type="text" size="10" name="cukaiperlubayar" value="$!UTIL.format2Decimal($senarai.cukaiTerkini)" onblur="checkNumeric(this.value);kirakira(${cnt},${cntTotal});" class="inputnumber" >
					</td>		    		
					<td class="$row">
		    			<!-- <input type="text" size="10" name="cukaitaliair${cnt}" id="cukaitaliair${cnt}" value="$!UTIL.format2Decimal($senarai.cukaiTaliair)" $inputstyle> -->
		    			<input type="text" size="10" name="cukaitaliair" value="$!UTIL.format2Decimal($senarai.cukaiTaliair)" onblur="checkNumeric(this.value);kirakira(${cnt},${cntTotal});" class="inputnumber" >
		    		</td>
					<td class="$row">
						<!-- <input type="text" size="10" name="cukaiparit${cnt}" id="cukaiparit${cnt}"value="$!UTIL.format2Decimal($senarai.cukaiParit)" $inputstyle2> -->
						<input type="text" size="10" name="cukaiparit" value="$!UTIL.format2Decimal($senarai.cukaiParit)" onblur="checkNumeric(this.value);kirakira(${cnt},${cntTotal});" class="inputnumber" >
					</td>
		    		<td class="$row">
		    			<!-- <input type="text" size="10" name="cukaitunggakan${cnt}" id="cukaitunggakan${cnt}" value="$!UTIL.format2Decimal($senarai.cukaiTunggakan)" $inputstyle> -->
		    			<input type="text" size="10" name="cukaitunggakan" value="$!UTIL.format2Decimal($senarai.cukaiTunggakan)" onblur="checkNumeric(this.value);kirakira(${cnt},${cntTotal});" class="inputnumber" >
		    		</td>
					<td class="$row">
						<!-- <input type="text" size="10" name="cukaidenda${cnt}" id="cukaidenda${cnt}"value="$!UTIL.format2Decimal($senarai.cukaiDenda)" $inputstyle2> -->
						<input type="text" size="10" name="cukaidenda" value="$!UTIL.format2Decimal($senarai.cukaiDenda)" onblur="checkNumeric(this.value);kirakira(${cnt},${cntTotal});" class="inputnumber" >
					</td>
					<!--  -->
					<td class="$row">
						<!-- <input type="text" size="10" name="cukaipengurangan${cnt}" id="cukaipengurangan${cnt}" value="$!UTIL.format2Decimal($senarai.cukaiPengurangan)" $inputstyle> -->
						<input type="text" size="10" name="cukaipengurangan" value="$!UTIL.format2Decimal($senarai.cukaiPengurangan)" onblur="checkNumeric(this.value);kirakira(${cnt},${cntTotal});" class="inputnumber" >
					 </td>
					<!--modifield by rosli on 26/05/2011 -->					
					<td class="$row">
						<!-- <input type="text" size="10" name="cukaipengecualian${cnt}" id="cukaipengecualian${cnt}"value="$!UTIL.format2Decimal($senarai.cukaiPengecualian)" $inputstyle2> -->
						<input type="text" size="10" name="cukaipengecualian" value="$!UTIL.format2Decimal($senarai.cukaiPengecualian)" onblur="checkNumeric(this.value);kirakira(${cnt},${cntTotal});" class="inputnumber" >
					</td>
					<td class="$row">
						<!-- <input type="text" size="10" name="cukailebihan${cnt}" id="cukailebihan${cnt}" value="$!UTIL.format2Decimal($senarai.cukaiLebihan)" $inputstyle> -->
						<input type="text" size="10" name="cukailebihan" value="$!UTIL.format2Decimal($senarai.cukaiLebihan)" onblur="checkNumeric(this.value);kirakira(${cnt},${cntTotal});" class="inputnumber" >
					</td>
					<td class="$row">
						<!-- <input type="text" size="10" name="cukaijumlah${cnt}" id="cukaijumlah${cnt}" value="$!UTIL.format2Decimal($senarai.cukaiJumlah)" class=disabled disabled> -->
						<input type="text" size="10" name="cukaijumlah" value="$!UTIL.format2Decimal($!senarai.cukaiJumlah)" disabled class="inputnumberdisabled" >
					</td>
		    			<input type="hidden" name="senaraiNolot" id="senaraiNolot" value="$senarai.NO_LOT" >
						<input type="hidden" name="senaraiID_HAKMILIKCUKAI${cnt}" id="senaraiID_HAKMILIKCUKAI${cnt}" value="$senarai.ID_HAKMILIKCUKAI" >
						<input type="hidden" name="senaraiNO_HAKMILIK" id="senaraiNO_HAKMILIK" value="$senarai.NO_HAKMILIK" >		
						<input type="hidden" name="idhakmilikcukai${cnt}" id="idhakmilikcukai${cnt}" value="$senarai.ID_HAKMILIKCUKAI" >
		  		</tr>
    		#end
 				<tr>
		    		<td colspan="10" class="$row"></td>
		    		<td colspan="2" class="$row"><b>Jumlah</b></td>
		    		<td class="$row" align="right"><b>$!UTIL.format2Decimal($!cnt_)</b>
		    			<!-- <input type="text" size="10" name="cnt_" value="$!UTIL.format2Decimal($!cnt_)" disabled class="inputnumberdisabled" > -->
		    		</td>
		    		<td class="$row"></td>
		  		</tr>
		 	#if ($cnt == 0)
		  		<tr>
		    		<td colspan="14" class="$row"><font color="#FF0000">Tiada Rekod.</font></td>
		  		</tr>
		 	#end
 	
			</table>	
		</fieldset>
		</td>
	</tr>
#if ($cnt != 0)
	<tr>
			<td align="center">
			#if ($cnt == 1)
				<input type="button" name="btncukaikemaskini" value="Kemaskini" onclick="cukaikemaskini(1)">
			#else	
				<input type="button" name="btncukaikemaskini" value="Kemaskini Semua" onclick="cukaiKemaskiniSemua()">	
			
			#end	
				<input type="button" class="stylobutton100" name="Cetakp" id="Cetakp" value="Cetak" onclick="javascript:senaraiPenyata('tabledokumen');" />
				<!-- <input type="button" class="stylobutton100" name="Cetakp" id="Cetakp" value="Simpan Senarai Penyata" onclick="javascript:simpanPenyata();" /> -->
							
			</td>
	</tr>
#end	
</table>
	<input name="carian" type="hidden" value="$!iscarian" >
	<input name="bilangan" type="hidden" value="$!count.size()" >
	
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

<script>
	
	function cukaikemaskiniXX(i){
		alert(i.value);
		//doAjaxCall${formName}("CukaiKemaskini","bil="+i);
	}

	function cukaikemaskini(i){	
		//alert(document.${formName}.cukaijumlah[i-1].value);	
		doAjaxCall${formName}("CukaiKemaskini","bil="+i);
		//doAjaxCall${formName}("CukaiKemaskini","bil="+i+"&cukaikenabayarXX="+cukaikenabayar+"&senaraiID_HAKMILIKCUKAIXX="+senaraiID_HAKMILIKCUKAI);
		
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
	
	function cukaiKemaskiniSemua(){
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

function senaraiPenyata(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

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
	
	function dataCukaiNegeri(){
		if(document.${formName}.socNegeri.value==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		if(document.${formName}.Form_tahun.value==0){
			alert("Sila pilih \"Tahun Cukai\" terlebih dahulu.");
			document.${formName}.Form_tahun.focus(); 
			return;
		}
		if ( !window.confirm("Anda Pasti?") ) return;
		
	}
	
	function dataCukaiDaerah(){
		if(document.${formName}.socDaerah.value==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}	
		if(document.${formName}.Form_tahun.value==0){
			alert("Sila pilih \"Tahun Cukai\" terlebih dahulu.");
			document.${formName}.Form_tahun.focus(); 
			return;
		}
		if ( !window.confirm("Anda Pasti?") ) return;
		
	}
	
	function dataCukaiMukim(){
		if(document.${formName}.socMukim.value==""){
			alert("Sila pilih \"Mukim\" terlebih dahulu.");
			document.${formName}.socMukim.focus(); 
			return;
		}	
		if(document.${formName}.Form_tahun.value==0){
			alert("Sila pilih \"Tahun Cukai\" terlebih dahulu.");
			document.${formName}.Form_tahun.focus(); 
			return;
		}
		
		if ( !window.confirm("Anda Pasti?") ) return;
		
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
	
	function simpanPenyata(){
		var mycb = new Array();
		var mycukaijumlah = new Array();
		var bilangan = document.${formName}.bilangan.value; 
		var cb = "";

		if ( !window.confirm("Anda Pasti?") ) return;
		doAjaxCall${formName}("simpanpenyatacukai","bil="+bilangan);
	
	}
	
	function kiraan(i,y){
		alert(y);
	}
	
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
  	//KEMASUKAN MANUAL
  	function tambahanManual(){
  		reset_jqueryCarian('div_cukai');	
  		document.getElementById('div_cukai').style.display="";		
  		doDivAjaxCall$formname('div_cukai','createNew','');
		//doAjaxCall${formName}("createNew");	
	}
  	//KEMASUKAN MANUAL
	function Kembali(){
		doAjaxCall${formName}("");
	
	}
	
  	//KEMASUKAN MANUAL
	function doChangeDaerahManual(){
		reset_jqueryCarian('div_cukai');	
  		document.getElementById('div_cukai').style.display="";		
  		doDivAjaxCall$formname('div_cukai','doChangeDaerahManual','');
	
		//doAjaxCall${formName}("doChangeDaerahManual");
		
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
	  		alert('Sila isikan nombor hakmilik terlebih dahulu.');
	  		document.${formName}.txtNoHakmilik.focus(); return; 
		}
		if ( document.${formName}.txtNoLot.value == "" ) { 
	  		alert('Sila isikan nombor lot terlebih dahulu.');
	  		document.${formName}.txtNoLot.focus(); return; 
		}
	
		if ( document.${formName}.txtKegunaanTanah.value == "" ) { 
	  		alert('Sila isikan kegunaan tanah terlebih dahulu.');
	  		document.${formName}.txtKegunaanTanah.focus(); return; 
		}
		
		document.getElementById('div_cukai').style.display="none";	

		//
		doDivAjaxCall$formname('div_cukai','saveCukai','');
		//doAjaxCall${formName}("saveCukai");
	}
	
	function carianHakmilik(){
		doAjaxCall${formName}("searchHakmilik");

	}
	
	function reset_jqueryCarian(current_div){
		if(current_div!="div_listFail")
		{
			
			if (document.getElementById('div_listFail') != null && document.getElementById('div_listFail') != undefined) 
			{
				
			document.getElementById('div_listFail').style.display="none";	
			
			doDivAjaxCall$formname('div_listFail','doCloseListFail','');	
			}
		}
		
		if(current_div!="div_listHakmilik")
		{
			//alert("2");
			if (document.getElementById('div_listHakmilik') != null && document.getElementById('div_listHakmilik') != undefined) 
			{
			//	alert("2a");
			document.getElementById('div_listHakmilik').style.display="none";	
			
			doDivAjaxCall$formname('div_listHakmilik','doCloseListHakmilik','');
			}
		}
		
		if(current_div!="div_cukai"){
			//alert("2");
			if (document.getElementById('div_cukai') != null && document.getElementById('div_cukai') != undefined) {
			//	alert("2a");
				document.getElementById('div_cukai').style.display="none";	
				doDivAjaxCall$formname('div_cukai','doCloseListCukai','');
			
			}
		}
		
	}
	
	
</script>
