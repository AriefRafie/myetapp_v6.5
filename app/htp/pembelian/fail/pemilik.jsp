<style type="text/css">
<!--
.r {
	color: #00C;
}
-->
</style>
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
					<td scope="col">No Lot</td>
					<td scope="col">Kod Luas</td>
					<td scope="col">Luas</td>
					<td scope="col">No Pelan</td>
					<td scope="col">Tarikh Mula</td>
					<td scope="col">Tarikh Luput</td>
			<td scope="col">&nbsp;</td> -->
		<td scope="col" width="3%">Bil.</td>
		<td scope="col" width="20%">Jenis dan No. Hakmilik</td>
		<td scope="col" width="17%">No Lot/PT</td>
		<td scope="col" width="20%">Luas(H)</td>
		<td scope="col" width="20%">No Pelan</td>
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
#parse("app/htp/pembelian/fail/maklumatPemilik.jsp") 
#end
<br />
<fieldset>
<legend><strong>SENARAI PEMILIK</strong></legend>
<table width="100%">
		<TR>
			<TD colspan="4">
				 <input class="stylobutton_" type="button" name="cmdSimpan" id="cmdSimpan" value="Tambah" onclick="doAjaxCall${formName}('tambahPemilik')">
            </TD>
        </TR>
          <tr class="table_header">
              <td width="3%" scope="col">Bil.</td>
              <td width="15%" scope="col">Jenis dan No. Hakmilik</td>
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
            <td scope="col"><a href="javascript:detailPemilik('$!pk.getIdpihakberkepentingan()')" class="r">$!pk.getHakmilikUrusan().getKodjenishakmilik()$!pk.getNoHakmilik()</a></td>
            <td scope="col">$!pk.getNama() </td>
            <td scope="col"><input type="button" name="btnDelete" value="Hapus" onclick="doAjaxCall${formName}('deletePemilik')"><input type="hidden" name="Idpihakberkepentingan" value="$!pk.getIdpihakberkepentingan()" /></td>
          </tr>
           #end
</table>
</fieldset>
<input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>