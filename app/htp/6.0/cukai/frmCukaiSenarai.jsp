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
			

<fieldset><legend>SENARAI HAKMILIK</legend>
			<fieldset>
			<legend>Hakmilik (Rekod Keseluruhan)</legend>
				<table width="100%" border="0">
		<tr class="table_header">
		    <td width="3%">Bil.</td>
		    <td width="39%">Jenis dan No.Hakmilik / No. Lot</td>
		    <td width="5%">T/Daftar</td>
		    <td width="7%">Dari</td>
		    <td width="6%" align="center" >Cukai<br>(RM)</td>
		    <td width="6%" align="center" >TaliAir<br>(RM)</td>
		    <td width="6%" align="center" >Parit<br>(RM)</td>
		    <td width="3%" align="center" >Tunggakan<br>(RM)</td>
		    <td width="3%" align="center" >Denda<br>(RM)</td>
	 		<td width="3%" align="center" >Kutipan Kurang<br>(RM)</td>
		    <td width="3%" align="center" >Pengecualian/ Diskaun/ Remisyen<br>(RM)</td> 
		    <td width="3%" align="center" >Lebihan<br>(RM)</td>
		    <td width="3%" align="center" >Jumlah<br>(RM)</td>			    
		    <!-- <td width="3%"></td> -->
		</tr>
	#set ( $cnt_ = 0 )			
  	#foreach ( $senarai in $SenaraiFailOrig )
  	##foreach ( $senarai in $SenaraiFail )
  		#set ( $cnt_ = $cnt_ + 1 )
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
       		<td class="$row">$cnt_.</td>
           	<td class="$row">
            	$!senarai.NAMA_NEGERI/$!senarai.NAMA_DAERAH/$!senarai.NAMA_MUKIM $!senarai.labelHakmilik/ $!senarai.KETERANGAN_LOT$!senarai.NO_LOT <br>
                $!senarai.kegunaan
        	</td>
          	<td class="$row">$!senarai.tarikhDaftar</td>
           	<td class="$row">REKOD</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPerluBayar)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiTaliair)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiParit)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiTunggakan)</td>
           	<td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiDenda)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPengurangan)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPengecualian)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiLebihan)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiJumlah)</td>         	
            <!-- <td class="$row" align="center"></td>
           	<td class="$row"></td> -->
		</tr>
    
     #end
 
   	#if ($cnt_ == 0)
  		<tr>
    		<td colspan="13" class="$row"><font color="#FF0000">Tiada Rekod.</font></td>
  		</tr>
 	#end
				</table>
			</fieldset>

			<fieldset>
			<legend>Hakmilik (Tidak Perlu Dibayar Cukai ($!tahuncukain))</legend>
				<table width="100%" border="0">
		<tr class="table_header">
		    <td width="3%">Bil.</td>
		    <td width="39%">Jenis dan No.Hakmilik / No. Lot</td>
		    <td width="5%">T/Daftar</td>
		    <td width="7%">Dari</td>
		    <td width="6%" align="center" >Cukai<br>(RM)</td>
		    <td width="6%" align="center" >TaliAir<br>(RM)</td>
		    <td width="6%" align="center" >Parit<br>(RM)</td>
		    <td width="3%" align="center" >Tunggakan<br>(RM)</td>
		    <td width="3%" align="center" >Denda<br>(RM)</td>
	 		<td width="3%" align="center" >Kutipan Kurang<br>(RM)</td>
		    <td width="3%" align="center" >Pengecualian/ Diskaun/ Remisyen<br>(RM)</td> 
		    <td width="3%" align="center" >Lebihan<br>(RM)</td>
		    <td width="3%" align="center" >Jumlah<br>(RM)</td>			    
		</tr>
	#set ( $cntX = 0 )			
  	#foreach ( $senarai in $senaraiHakmilikX )
  		#set ( $cntX = $cntX + 1 )
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
       		<td class="$row">$cntX.</td>
           	<td class="$row">
            	$!senarai.NAMA_NEGERI/$!senarai.NAMA_DAERAH/$!senarai.NAMA_MUKIM $!senarai.labelHakmilik/ $!senarai.KETERANGAN_LOT$!senarai.NO_LOT <br>
                $!senarai.kegunaan
        	</td>
          	<td class="$row">$!senarai.tarikhDaftar</td>
           	<td class="$row">REKOD</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPerluBayar)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiTaliair)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiParit)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiTunggakan)</td>
           	<td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiDenda)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPengurangan)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPengecualian)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiLebihan)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiJumlah)</td>         	
		</tr>
    
     #end
 
   	#if ($cntX == 0)
  		<tr>
    		<td colspan="13" class="$row"><font color="#FF0000">Tiada Rekod.</font></td>
  		</tr>
 	#end
				</table>
			</fieldset>
			
			<fieldset>
			<legend>Hakmilik (Cukai Perlu Dibayar)</legend>
				<table width="100%" border="0">
		<tr class="table_header">
		    <td width="3%">Bil.</td>
		    <td width="39%">Jenis dan No.Hakmilik / No. Lot</td>
		    <td width="5%">T/Daftar</td>
		    <td width="7%">Fail Dari</td>
		    <td width="6%" align="center" >Cukai<br>(RM)</td>
		    <td width="6%" align="center" >TaliAir<br>(RM)</td>
		    <td width="6%" align="center" >Parit<br>(RM)</td>
		    <td width="3%" align="center" >Tunggakan<br>(RM)</td>
		    <td width="3%" align="center" >Denda<br>(RM)</td>
	 		<td width="3%" align="center" >Kutipan Kurang<br>(RM)</td>
		    <td width="3%" align="center" >Pengecualian/ Diskaun/ Remisyen<br>(RM)</td> 
		    <td width="3%" align="center" >Lebihan<br>(RM)</td>
		    <td width="3%" align="center" >Jumlah<br>(RM)</td>			    

		</tr>
	#set ( $cnt = 0 )			
  	#foreach ( $senarai in $senaraiHakmilikB )
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
       		<td class="$row">$cnt.</td>
           	<td class="$row">
            	$!senarai.NAMA_NEGERI/$!senarai.NAMA_DAERAH/$!senarai.NAMA_MUKIM $!senarai.labelHakmilik/ $!senarai.KETERANGAN_LOT$!senarai.NO_LOT <br>
                $!senarai.kegunaan
        	</td>
          	<td class="$row">$!senarai.tarikhDaftar</td>
           	<td class="$row">REKOD</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPerluBayar)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiTaliair)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiParit)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiTunggakan)</td>
           	<td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiDenda)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPengurangan)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiPengecualian)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiLebihan)</td>
            <td class="$row" align="right">$!UTIL.format2Decimal($senarai.cukaiJumlah)</td>         	
		</tr>
    
     #end
 
   	#if ($cnt == 0)
  		<tr>
    		<td colspan="13" class="$row"><font color="#FF0000">Tiada Rekod.</font></td>
  		</tr>
 	#end
				</table>
			</fieldset>			
	
</fieldset>

		</td>
	</tr>
	
</table>
	<input name="carian" type="hidden" value="$!iscarian" >

<script>

	function cukaikemaskini(){
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
