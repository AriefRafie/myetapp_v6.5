<style type="text/css">

<!--#parse("css/eTapp_PHP.css")-->

.error_detail {
	color: #D8000C !important;
	background-color: #FFBABA !important;
	background-image: url('../../img/error.png');
	border: 1px solid;
	background-repeat: no-repeat;
	background-position: 10px center;
	margin: 10px 0;
	padding: 15px 10px 15px 50px;
}

</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  
	#if($!flagMsg=="T")
    <tr>
        <td class="error_detail">$!msg</td>
    </tr>
	#end

  	<tr>
        <td>
            <fieldset>
              <legend><b>SENARAI DOKUMEN</b></legend>
              #parse("app/utils/record_paging_popup.jsp")
              <table align="center" width="100%">
                <tr class="table_header">
                  <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                  <td ><strong>Nama Dokumen</strong></td>
                </tr>
                #set ($list = "")
                #set ( $count = 0 )
                #if ($listArkib.size() > 0)
                    #foreach ($list in $listArkib)
                        #set ( $count = $count + 1 )
                        #if ($count == '')
                            #set( $row = "row1" )
                        #elseif (($count % 2) != 0)
                            #set( $row = "row1" )
                        #else 
                            #set( $row = "row2" )
                        #end
                        
                        
                        <tr>
                          <td class="$row" align="center">$count</td>
                          <td class="$row"><a href="$!list.dirDokumen" class="style2" target="_blank"><font color='blue'>$!list.namaDokumen</a></td>
                        </tr>
                    #end
                    
                #else
                    <tr>
                      <td class="row1" align="center">&nbsp;</td>
                      <td class="row1">Tiada Rekod</td>
                    </tr>
                #end
                
              </table>
            </fieldset>
          </td>
  	</tr>
  
    <tr>
        <td align="center"><input type="button" name="cmdTutup" id="cmdTutup" value="Tutup" onClick="window.close();"></td>
    </tr>
</table>
