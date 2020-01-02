	
	#if($showCB=="yes")
		#set($disCB="")
	#else
		#set($disCB="disabled")
	#end
	
	<br/>
	
		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="24%">Pegawai Penerima</td>
				<td width="1%">:</td>
				<td width="74%">$!selectPegawai
                
              
                
                
                 #if($showListAuto=="yes" && $listHMAgih.size()>1)                
                         &nbsp; <span >
                          <input name="start_PT"  type="text" id="start_PT"  size="4" maxlength="4" onblur="validateTarikh(this,this.value);" onkeyup="validateTarikh(this,this.value);" />
                          <strong>&gt;&gt;</strong>				
                        <input name="end_PT"  type="text" id="end_PT"  size="4" maxlength="4" onblur="validateTarikh(this,this.value);" onkeyup="validateTarikh(this,this.value);" />
                         &nbsp;
                        <a href="javascript:autoagih_PT()"><font color="blue" title="Klik untuk pengagihan secara automatik">[Agih]</font></a> 
                        &nbsp;
                         <a href="javascript:autoagihX_PT()"><font color="blue"  title="Klik untuk mengosongkan senarai semak">[Kosongkan]</font></a> 
                        </span>              
                #end
               
                </td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Serah Tugas</td>
				<td>:</td>
				<td><input name="txdTarikhAgihanLayer2" value="$!txdTarikhAgihanLayer2" size="11" id="txdTarikhAgihanLayer2" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this);validateTarikh(this,this.value)" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhAgihanLayer2',false,'dmy');">$!frmtdate</td>
			</tr>
		</table>
	
	  <a href="javascript:setTable('infoAgihanPermohonanAll_PT')"><font color="blue"><b>&raquo;Maklumat Agihan Pentadbir Tanah (Keseluruhan)</b></font></a><br />
        
 	 <fieldset id="infoAgihanPermohonanAll_PT" style="width:50%; display:none;" >
	<legend><strong>Maklumat Agihan Pentadbir Tanah (Keseluruhan)</strong></legend>    
    <table width="100%"  cellpadding="0" cellspacing="2" border="0">
    <tr class="table_header">
	       		<td align="center" width="10%"><b>No</b></td>
	       		<td align="left" width="60%" ><b>Pegawai</b></td>
                <td align="center" width="15%" ><b>Bil. Permohonan</b></td> 
	           	<td align="center" width="15%" ><b>Bil. Hakmilik</b></td>            
    </tr>
    #if($listHMAgih_INFO_PT_ALL.size() > 0)
    #foreach($listTanah in $listHMAgih_INFO_PT_ALL)
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
    <!--
    <a href="javascript:setTable('infoAgihanPermohonan_PT')"><font color="blue"><b>&raquo;Maklumat Agihan Pentadbir Tanah bagi fail : $!nofail</b></font></a><br />
    -->
    <fieldset id="infoAgihanPermohonan_PT" style="width:50%;" >
	<legend><strong>Maklumat Agihan Pentadbir Tanah bagi $!nofail</strong></legend>    
    <table width="100%"  cellpadding="0" cellspacing="2" border="0">
    <tr class="table_header">
	       		<td align="center" width="10%"><b>No</b></td>
	       		<td align="left" width="60%" ><b>Pegawai</b></td>
	           	<td align="center" width="30%" ><b>Bil. Hakmilik</b></td>            
    </tr>
    #if($listHMAgih_PT_INFO.size() > 0)
    #foreach($listTanah in $listHMAgih_PT_INFO)
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
    
    
	<fieldset id="layer2">
	<legend><strong>Senarai Pilihan Hakmilik</strong></legend>
		
		<table width="100%"  cellpadding="0" cellspacing="2" border="0">
		
			<tr class="table_header">
				#if($listHMAgih.size() != 0)
        		<td align="center" width="4%"><b><input type="checkbox" $disCB title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></b></td>
        		#end
	       		<td align="center" width="4%"><b>No</b></td>
	       		<td><b>No.Hakmilik</b></td>
	           	<td><b>No.LOT/No.PT</b></td>       
	           	#if($!flag_subjaket!="")<td><b>No.Subjaket</b></td>#end         
	            <!--<td><b>Tarikh Agihan Tugas</b></td> -->
	       		<td><b>Nama Pentadbir Tanah</b></td>        
	        </tr>
	             
	   		#if($listHMAgih.size() != 0)
	   			
	      		#foreach($listTanah in $listHMAgih)
	            #set( $i = $velocityCount )
	         	#if ( ($i % 2) != 1 )
	           		#set( $row = "row2" )
		        #else
		            #set( $row = "row1" )
		       	#end
	        	
	        	#if($listTanah.checkBoxVal > 0)
		        	#set($checkCB = "checked")
		        #else
		        	#set($checkCB = "")
		        #end
	        
	       	<tr>
	       		#if($listHMAgih.size() != 0)
	            <td class="$row" align="center"><input type="checkbox" $disCB $checkCB name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listTanah.id_hakmilik"></td>
	            #end
	           	<td class="$row" align="center">$!listTanah.bil</td>	           	   
	            <td class="$row">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</td>
				<td class="$row">$!listTanah.no_lotpt</td> 
				#if($!flag_subjaket!="")<td class="$row">Sj.$!listTanah.no_subjaket</td>#end
	       		<!-- <td class="$row">$!listTanah.tarikh_serah</td> -->
	           	<td class="$row">$!listTanah.nama_pegawai</td> 
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
						<input type="button" name="cmdSimpan" value ="Agihkan Tugas" onClick="javascript:simpanAgihanLayer2()">
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali()">
					</td>
				</tr>
			</table>
			<input type="hidden" name="id_penolong" value="$id_penolong">
			<input type="hidden" name="id_status" value="$id_status">