<style>
/* the div that holds the date picker calendar */
.dpDiv {
	}


/* the table (within the div) that holds the date picker calendar */
.dpTable {
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	font-size: 12px;
	text-align: center;
	color: #505050;
	background-color: #ece9d8;
	border: 1px solid #AAAAAA;
	}


/* a table row that holds date numbers (either blank or 1-31) */
.dpTR {
	}


/* the top table row that holds the month, year, and forward/backward buttons */
.dpTitleTR {
	}


/* the second table row, that holds the names of days of the week (Mo, Tu, We, etc.) */
.dpDayTR {
	}


/* the bottom table row, that has the "This Month" and "Close" buttons */
.dpTodayButtonTR {
	}


/* a table cell that holds a date number (either blank or 1-31) */
.dpTD {
	border: 1px solid #ece9d8;
	}


/* a table cell that holds a highlighted day (usually either today's date or the current date field value) */
.dpDayHighlightTD {
	background-color: #CCCCCC;
	border: 1px solid #AAAAAA;
	}


/* the date number table cell that the mouse pointer is currently over (you can use contrasting colors to make it apparent which cell is being hovered over) */
.dpTDHover {
	background-color: #aca998;
	border: 1px solid #888888;
	cursor: pointer;
	color: red;
	}


/* the table cell that holds the name of the month and the year */
.dpTitleTD {
	}


/* a table cell that holds one of the forward/backward buttons */
.dpButtonTD {
	}


/* the table cell that holds the "This Month" or "Close" button at the bottom */
.dpTodayButtonTD {
	}


/* a table cell that holds the names of days of the week (Mo, Tu, We, etc.) */
.dpDayTD {
	background-color: #CCCCCC;
	border: 1px solid #AAAAAA;
	color: white;
	}


/* additional style information for the text that indicates the month and year */
.dpTitleText {
	font-size: 12px;
	color: gray;
	font-weight: bold;
	}


/* additional style information for the cell that holds a highlighted day (usually either today's date or the current date field value) */ 
.dpDayHighlight {
	color: 4060ff;
	font-weight: bold;
	}


/* the forward/backward buttons at the top */
.dpButton {
	font-family: Verdana, Tahoma, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: gray;
	background: #d8e8ff;
	font-weight: bold;
	padding: 0px;
	}


/* the "This Month" and "Close" buttons at the bottom */
.dpTodayButton {
	font-family: Verdana, Tahoma, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: gray;
	background: #d8e8ff;
	font-weight: bold;
	}
</style>
	## 0=
	## 1=
	## 2=view
	## 3=
	## 4=
	#set ( $pagemode = 0 )			
	##set ( $nofailseksyen = $nofail )
	##if ( $nofailseksyen == ""  )
			##set ( $nofailseksyen = "" )	
	##end
	
	#set ($inputstyle = "class=disabled" )
	#set ($inputstyleread = "readonly class=disabled" )
	#set ($selectstyle = "disabled class=disabled" )
	#if ($pageMode == 2 )	
		##set ($inputstyle = "" )
		##set ($selectstyle = "" )
		##set ($inputstylerep = "class=disabled readonly" )
			
	#elseif($pageMode == 3 )	
		#set ($inputstyleread = "" )
		#set ($selectstyle = "" )

	#else
		#set ($inputstyle = "class=disabled" )
		##elseif ( $pageMode != 0  )
		#set ( $pagemode = $pageMode )	
	#end		
<strong>[CL-02-0403] Pajakan Kecil Tanah/Bangunan</strong>
<br><br>
<fieldset>
<legend>Semakan Pajakan Kecil Tanah/Bangunan </legend>
<!--<form name="${formName}"> {$pageMode}-->
  <table border="1" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td width="50%" align="center" valign="top">	<table width="100%" border="0">
              <tr>
                <td><div align="left"><strong>Negeri</strong></div></td>
                 <td><strong>:</strong></td>
                <td>$socNegeri</td>
              </tr>
              <tr>
                <td><div align="left"><strong>Kementerian</strong></div></td>
                                 <td><strong>:</strong></td>
                <td>$socKementerian</td>
              </tr>
              <tr>
                <td><div align="left"><strong>Agensi</strong></div></td>
                <td><strong>:</strong></td>
                <td>$socAgensi</td>
              </tr>
              <!--<tr>
                <td><div align="right"><strong>Urusan :</strong></div></td>
                <td><select name="Urusan" id="Urusan">
                  <option selected value="">Sila Pilih Urusan</option>
                </select></td>
              </tr> -->
              <tr>
                <td ><div align="left"><strong>Tajuk </strong></div></td>
                <td><strong>:</strong></td>
                <td><label>
                  <textarea name="Tajuk" id="Tajuk" cols="54" rows="3" onkeyup="this.value=this.value.toUpperCase();" $inputstyleread>$permohonanInfo.tujuan</textarea>
                </label></td>
              </tr>
        </table></td>
        
		<td width="50%" align="center" valign="top">	<table width="100%" border="0">
              <tr>
                <td><div align="left"><strong>No. Fail Seksyen </strong></div></td>
                <td><strong>:</strong></td>
                <td><input type="text" name="txtNoFailSek" size="30" value="$permohonanInfo.fail" readonly $inputstyle></td>
              </tr>
              <tr>
                <td><div align="left"><strong>No. Fail Lain </strong></div></td>
                <td><strong>:</strong></td>
                <td><input type="text" name="txtNoFailLain" size="30" id="txtNoFailLain" value="$permohonanInfo.rujukanlain" readonly $inputstyle></td>
              </tr> 
              <tr>
                <td><div align="left"><strong>Tarikh Surat KJP </strong></div></td>
                 <td><strong>:</strong></td>
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" value="$permohonanInfo.tsurat" size="15" readonly $inputstyleread>
                <!--<input type="button" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" value="Kalendar" $selectstyle/> -->
                #if ($pageMode == 0 || $pageMode == 3)	
                <a href="javascript:displayDatePicker('txdTarikhSurKJP',false,'dmy');"><img border="0" src="../img/calendar.gif" /></a> 
                #end
                </td>
              </tr>

              <tr>
                <td><div align="left"><strong>Tarikh Buka Fail </strong></div></td>
                <td><strong>:</strong></td>
	        <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$permohonanInfo.tdaftar" readonly $inputstyle></td>
              </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2"><table width="100%" border="0">
          <tr>
            <td colspan="2"><strong>Senarai Semakan</strong></td>
            </tr>

           #set ( $checked = "" )
            #foreach ( $semak in $senaraiSemakan )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
                <tr>
                    <td class="$row" width="10">
                        #if ( $semakclass.isSemakan("$permohonanInfo.idpermohonan", "$semak.id" ))
                            #set ( $checked = "checked" )
                        #else
                            #set ( $checked = "" )
                        #end
                        <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $selectstyle>
                    </td>
                    <td class="$row">
                        $semak.keterangan
                    </td>

                </tr>       
            #end 
        </table></td>
      </tr>
      <tr>
		<td colspan="2" align="center">
			#if ($pageMode == 2 )	
			<input class="button" type="button" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="semakanPTambah('$permohonanInfo.idpermohonan','$permohonanInfo.idfail')">&nbsp
			#else
					<input class="button" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="semakanPTambah('$permohonanInfo.idpermohonan','$permohonanInfo.idfail')">&nbsp
	
			#end
			<!--<input type="reset" name="cmdBatal" id="cmdBatal" value="Batal">&nbsp; -->
			<input class="button" type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti('$permohonanInfo.idpermohonan')">&nbsp;
			<input class="button" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="backPre('$permohonanInfo.idfail')">&nbsp;
			<input class="button" type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="backMain()">&nbsp;
		</td>
      </tr>
    </tbody>
  </table>  
   	<input type="hidden" name="id_kemaskini" value="$permohonanInfo.idpermohonan" >
   	<input type="hidden" name="fail" value="$permohonanInfo.idfail" >
   	<input type="hidden" name="socAgensi" value="$socAgensi" >
   	<input type="hidden" name="txtNoFailLain" value="txtNoFailLain" >
   	<input type="hidden" name="langkah" value="0" >
      	<input type="hidden" name="pagemode" value="$pageMode">
	
<!--   	
   	<input type="hidden" name="command">
</form> 
-->
</fieldset>
<script>
function semakanPTambah(id,f) {
	document.${formName}.command.value = "pkpermohonankemaskini";

	if ( document.${formName}.cmdSimpan.value == "Kemaskini" ) {
		//document.${formName}.cmdSimpan.value = "Simpan";	
		document.${formName}.pagemode.value="3";
	}else {
		//document.${formName}.command.value = "semakankemaskini";
		//document.${formName}.command.value = "pksenaraipermohonan";
		document.${formName}.pagemode.value="4";
	}

	//document.${formName}.command.value = "pkpermohonankemaskini";
	//document.${formName}.method="post";
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.fail.value = f;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function nexti(id) {
	document.${formName}.command.value = "pksemakanseterus";
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.method="post";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function kemaskiniPermohonan(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.command.value = "update";
	document.${formName}.method="post";
	document.${formName}.action = "";
	document.${formName}.submit();
}


function backPre(id) {
	document.${formName}.command.value = "pksenaraipermohonan";
	document.${formName}.langkah.value = '0->-1';
	document.${formName}.fail.value = id;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function backMain() {
	//document.${formName}.command.value = "semakanseterus";
	document.${formName}.method="post";
	document.${formName}.action = "";
	document.${formName}.submit();
}
</script>
