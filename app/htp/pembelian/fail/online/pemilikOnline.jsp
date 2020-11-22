<style type="text/css">
<!--
.r {
	color: #00C;
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">

	<tr>
		<td>
<fieldset>
<legend><strong>SENARAI HAKMILIK</strong></legend>
<table width="100%">
<tr>
	<tr class="table_header">
	<!-- 
		<td scope="col">Bil.</td>
					<td scope="col">Jenis Hakmilik</td>
					<td scope="col">No. Hakmilik</td>
                  	<td scope="col">Kod Lot</td>
					<td scope="col">No. Lot/PT</td>
					<td scope="col">Kod Luas</td>
					<td scope="col">Luas</td>
					<td scope="col">No. Pelan</td>
					<td scope="col">Tarikh Mula</td>
					<td scope="col">Tarikh Luput</td>
			<td scope="col">&nbsp;</td> -->
		<td scope="col" width="3%">Bil.</td>
		<td scope="col" width="20%">No. Hakmilik</td>
		<td scope="col" width="17%">No. Lot/PT</td>
		<td scope="col" width="20%">Luas(H)</td>
		<td scope="col" width="20%">No. Pelan</td>
		<td scope="col" width="10%">Tarikh Mula</td>
		<td scope="col" width="10%">Tarikh Luput</td>
		<!--<td scope="col" width="0%">&nbsp;</td>		-->	
	</tr>
	
	#set ( $cnt = 0 )
    #foreach($mo in $mt)
    #set ( $cnt = $cnt + 1 )
    #if ($senarai.bil == '')
    #set( $row = "row1" )
    #elseif (($senarai.bil % 2) != 0)
    #set( $row = "row1" )
    #else 
    #set( $row = "row2" )
    #end
		<tr >
		  <td class="$row">$cnt.</td>
		  <!-- <td class="$row">$!mo.getJenisKeterangan()</td> -->
		  <td class="$row">$!mo.getKodjenishakmilik()$!mo.nohakmilik</td>
		  <!--<td class="$row">$!mo.getKeteranganLot()</td>-->
		  <td class="$row">$!mo.getKeteranganLot()$!mo.getNolot()</td>
		  <!--<td class="$row">$!mo.getLuasKeterangan()</td>-->
		  <td class="$row">$!mo.getLuas() </td>
		  <td class="$row">$!mo.getNoPlan()</td>
		  <td class="$row">$!mo.getTarikhMula()</td>
		  <td class="$row">$!mo.getTarikhLuput()</td>
		  <!--<td class="$row"></td> -->
    </tr>
     #end


</table>
</fieldset>
<br />
#if($mode != "0")
##else
#parse("app/htp/pembelian/fail/online/maklumatPemilikOnline.jsp") 
#end
<br />
<fieldset>
<legend><strong>SENARAI PEMILIK</strong></legend>
<table width="100%">
	#set($portal_role = "${session.getAttribute('myrole')}")
	##if($buttonMode != "2")
	#if ($portal_role!='online_kjpagensi')
		<TR>
			<TD colspan="4">
				 <input type="button" name="cmdSimpan" id="cmdSimpan" value="Tambah" onclick="doAjaxCall${formName}('tambahPemilik')">
            </TD>
        </TR>
    #end
  	##end
          <tr class="table_header">
              <td width="3%" scope="col">Bil.</td>
              <td width="15%" scope="col">No. Hakmilik</td>
              <td width="77%" scope="col">Nama Pemilik</td>
              <td width="5%" scope="col">&nbsp;</td>
         </tr>
         #set ( $cnt = 0 )
   		 #foreach($pk in $pp)
    	 #set ( $cnt = $cnt + 1 )
    	 #if ($senarai.bil == '')
    	 #set( $row = "row1" )
    	 #elseif (($senarai.bil % 2) != 0)
    	 #set( $row = "row1" )
    	 #else 
    	 #set( $row = "row2" )
    	 #end
<tr>
            <td scope="col">$!cnt.</td>
            <td scope="col"><a href="javascript:detailPemilik('$!pk.getIdpihakberkepentingan()')" class="r"> $!pk.getNoHakmilik()</a></td>
            <td scope="col">$!pk.getNama() </td>
            <td scope="col">
            	<input type="button" name="btnDelete" value="Hapus" onclick="doAjaxCall${formName}('deletePemilik')">
            	<input type="hidden" name="Idpihakberkepentingan" value="$!pk.getIdpihakberkepentingan()" />
            </td>
          </tr>
           #end
</table>
</fieldset>

 		  </td>
    </tr>
 	#if($buttonMode != "2")
  	#end 	
  <tr>
  
		    	<td >

        	<table width="100%" border="0">
          		<tr>
          			<td width="8%"><font color=red style=font-size:10px>Perhatian :</font></td>
          			<td width="92%">$!perhatian7</td>
          		</tr>
				<tr>
					<td>&nbsp;</td>
        			<td>$!perhatian8</td>
        		</tr>
        		<tr>
					<td>&nbsp;</td>
        			<td>$!perhatian9</td>
        		</tr>
			</table>    	
		    	</td>
		    
	 </tr>
	  
</table>
<input type="hidden" name="Idpihakberkepentingan" value="$!pemilik.getIdpihakberkepentingan()" />
<input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>