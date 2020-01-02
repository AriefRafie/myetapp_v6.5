#if($showCB=="yes")
		#set($disCB="")
	#else
		#set($disCB="disabled")
	#end
	
	<br/>
	
		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="24%"><b>Jumlah Lot</b></td>
				<td width="1%">:</td>
				<td width="74%"><b>$!totalHakmilik</b></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><b>Jumlah Lot Belum Diagihkan</b></td>
				<td>:</td>
				<td><b>$listHakmilik.size()</b></td>
			</tr>
		</table>

		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="24%">Pegawai Penerima</td>
				<td width="1%">:</td>
				<td width="74%">$!selectPPengarah
              
                
                 #if($showListAuto=="yes" && $listHakmilik.size()>1)                
                         &nbsp; <span >
                          <input name="start"  type="text" id="start" size="4" maxlength="4" onblur="validateTarikh(this,this.value);" onkeyup="validateTarikh(this,this.value);" />
                          <strong>&gt;&gt;</strong>				
                        <input name="end"  type="text" id="end" size="4" maxlength="4" onblur="validateTarikh(this,this.value);" onkeyup="validateTarikh(this,this.value);" />
                         &nbsp;
                        <a href="javascript:autoagih()"><font color="blue" title="Klik untuk pengagihan secara automatik">[Agih]</font></a> 
                        &nbsp;
                         <a href="javascript:autoagihX()"><font color="blue"  title="Klik untuk mengosongkan senarai semak">[Kosongkan]</font></a> 
                        </span>              
                #end
                
                </td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Serah Tugas</td>
				<td>:</td>
				<td><input name="txdTarikhAgihanLayer1" value="$!txdTarikhAgihanLayer1" size="11" id="txdTarikhAgihanLayer1" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this);validateTarikh(this,this.value)" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhAgihanLayer1',false,'dmy');">$!frmtdate</td>
			</tr>
		</table>
		
		<br/>
      
      <a href="javascript:setTable('infoAgihanPermohonanAll_P')"><font color="blue"><b>&raquo;Maklumat Agihan Pengarah (Keseluruhan)</b></font></a><br />
        
 	 <fieldset id="infoAgihanPermohonanAll_P" style="width:50%; display:none;" >
	<legend><strong>Maklumat Agihan Pengarah (Keseluruhan)</strong></legend>    
    <table width="100%"  cellpadding="0" cellspacing="2" border="0">
    <tr class="table_header">
	       		<td align="center" width="10%"><b>No</b></td>
	       		<td align="left" width="60%" ><b>Pegawai</b></td>
                <td align="center" width="15%" ><b>Bil. Permohonan</b></td> 
	           	<td align="center" width="15%" ><b>Bil. Hakmilik</b></td>            
    </tr>
    #if($listHMAgih_INFO_P_ALL.size() > 0)
    #foreach($listTanah in $listHMAgih_INFO_P_ALL)
	            #set( $i = $velocityCount )
	         	#if ( ($i % 2) != 1 )
	           		#set( $row = "row2" )
		        #else
		            #set( $row = "row1" )
		       	#end
     <tr >
	       		<td align="center" class="$row">$listTanah.bil</td>
	       		<td align="left" class="$row" >$listTanah.NAMA_PEGAWAI</td>
                <td align="center" class="$row" >$listTanah.COUNT_PERMOHONAN</td> 
	           	<td align="center" class="$row" >$listTanah.COUNT_HAKMILIK</td>             
    </tr>
    #end    
    #else    
    <tr >
	       		<td align="left"  colspan="5">Tiada Rekod</td>	           
    </tr>    
    #end    
    </table>    
    </fieldset>     
    
    <!--<a href="javascript:setTable('infoAgihanPermohonan_P')"><font color="blue"><b>&raquo;Maklumat Agihan Pengarah bagi fail : $!nofail</b></font></a><br />-->
    <fieldset id="infoAgihanPermohonan_P" style="width:50%; " >
	<legend><strong>Maklumat Agihan Pengarah bagi $!nofail</strong></legend>    
    <table width="100%"  cellpadding="0" cellspacing="2" border="0">
    <tr class="table_header">
	       		<td align="center" width="10%"><b>No</b></td>
	       		<td align="left" width="60%" ><b>Pegawai</b></td>
	           	<td align="center" width="30%" ><b>Bil. Hakmilik</b></td>            
    </tr>
    #if($listHMAgih_P_INFO.size() > 0)
    #foreach($listTanah in $listHMAgih_P_INFO)
	            #set( $i = $velocityCount )
	         	#if ( ($i % 2) != 1 )
	           		#set( $row = "row2" )
		        #else
		            #set( $row = "row1" )
		       	#end
     <tr >
	       		<td align="center" class="$row">$listTanah.bil</td>
	       		<td align="left" class="$row" >$listTanah.NAMA_PEGAWAI</td>
	           	<td align="center" class="$row" >$listTanah.COUNT_HAKMILIK</td> 
    </tr>
    #end    
    #else    
    <tr >
	       		<td align="left"  colspan="5">Tiada Rekod</td>	           
    </tr>    
    #end    
    </table>    
    </fieldset>
       
		
	<fieldset id="center">
	<legend><strong>Senarai Pilihan Hakmilik</strong></legend>

	<table width="100%"  cellpadding="0" cellspacing="2" border="0">
		
		<tr class="table_header">
			#if($listHakmilik.size() != 0)
        	<td align="center" width="4%"><b><input type="checkbox" $disCB title="Sila Semak Untuk Pilih Semua" name="checkallX" id="checkallX" onclick="checkALLX()" ></b></td>
        	#end
       		<td align="center"><b>No</b></td>
            <td><b>No.Hakmilik</b></td>
           	<td><b>No.LOT/No.PT</b></td>              
            <td><b>Mukim/Pekan/Bandar</b></td>
            #if($!flag_subjaket!="")<td><b>No.Subjaket</b></td>#end
        </tr>
             
   		#if($listHakmilik.size() != 0)
   			
      		#foreach($listTanah in $listHakmilik)
            #set( $i = $velocityCount )
         	#if ( ($i % 2) != 1 )
           		#set( $row = "row2" )
	        #else
	            #set( $row = "row1" )
	       	#end
        	
        	#if($listTanah.checkBoxVal != "")
	        	#if($listTanah.checkBoxVal > 0)
		        	#set($checkCB = "checked")
		        #else
		        	#set($checkCB = "")
		        #end
	        #else
	       	 	#set($checkCB = "")
	        #end
       	<tr>
       		#if($listHakmilik.size() != 0)
            <td class="$row" align="center"><input type="checkbox" $disCB $checkCB name="cbsemaksX" id="cbsemaksX" onclick="doUpdateCheckAllX()" value="$!listTanah.id_hakmilik"></td>
            #end
           	<td class="$row" align="center">$!listTanah.bil</td>
            <td class="$row">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</td>
			<td class="$row">$!listTanah.no_lotpt</td>     
            <td class="$row">$!listTanah.nama_mukim #if($listTanah.seksyen!="")<font style=font-size:10px>Seksyen $listTanah.seksyen</font>#end</td>
       		#if($!flag_subjaket!="")<td class="$row">Sj.$!listTanah.no_subjaket</td>#end
        </tr>
        	#end
        #else
        <tr>
           	<td colspan="2">Tiada rekod</td>
       	</tr>
        #end  
        
    </table>	
                
	</fieldset>
	
			<table width="100%" border="0">
				<tr align="center">
					<td>
						<input type="button" name="cmdSimpan" value ="Agihkan Tugas" onClick="javascript:simpanAgihanLayer1()">
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali()">
					</td>
				</tr>
			</table>
			<input type="hidden" name="id_ppengarah" value="$id_ppengarah">