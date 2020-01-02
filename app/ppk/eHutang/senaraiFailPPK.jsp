<table width="100%" border="0" cellspacing="2" cellpadding="2">


  <tr>
    <td><fieldset>
      <legend><b>SENARAI FAIL PUSAKA BERKAITAN PENGHUTANG</b></legend>
      <table align="center" width="100%">
         
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="30%"><strong>No. Fail</strong></td>
          <td><strong>Kategori Penghutang</strong></td>
          <td><strong>Status </strong></td>
          <td><strong>Pejabat Pusaka</strong></td>
        </tr>
        
        <!--#set ( $count = $startNumber )  -->
        #set ( $count = 0 )
        #if ($maklumathutangPPK.size() > 0)
        	#foreach ($list in $maklumathutangPPK)
        		#set ( $count = $count + 1 )
        		#if ($count == '')
        			#set( $row = "row1" )
        		#end
        		#if (($count % 2) != 0)
       		 		#set( $row = "row1" )
       			#else 
       				#set( $row = "row2" )
        		#end
        <tr>
          <td class="$row" align="center" valign="top">$count</td>
          <td class="$row" valign="top">$!list.NO_FAIL</td>
          <td class="$row" valign="top">$!list.KATEGORI</td>
          <td class="$row" valign="top">$!list.STATUS</td>
          <td class="$row">$!list.NAMA_PEJABAT<br/>$!list.ALAMAT1<br/>$!list.ALAMAT2<br/>$!list.ALAMAT3
          <br/>$!list.POSKOD&nbsp;$!list.BANDAR<br/>$!list.NEGERI<br/>No. Tel : $!list.NO_TEL
          <br/>No. Fax : $!list.NO_FAX
          </td>         	
        </tr>
       		 #end
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
