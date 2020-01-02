<table width="100%" border="0" cellspacing="2" cellpadding="2">
 <tr>
      <td colspan="4">
      <fieldset>
      <legend>MAKLUMAT HAKMILIK</legend>  
      <table width="100%">
      <tr>
      <td width="50%" valign="top">
      
      <table width="100%" >
       <tr>
          <td width="1%"></td>
          <td width="25%" align="left"></td>
          <td width="1%"></td>
          <td width="73%">    
               	</td>
        </tr>
       
      #if($!dataHakmilik.NAMA_NEGERI!="")
        <tr>
          <td align="right"  valign="top"></td>
          <td align="left"  valign="top" >NEGERI</td>
          <td valign="top" >:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NAMA_NEGERI</font>            
               	  </td>
        </tr>
        #end
        
        
        #if($!dataHakmilik.DAERAHPENGGAWA!="")
        <tr>
          <td align="right" valign="top" ></td>
          <td align="left" valign="top" >DAERAH PENGGAWA</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.DAERAHPENGGAWA</font>            
               	  </td>
        </tr>
        #end
        
        #if($!dataHakmilik.NAMA_DAERAH!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">DAERAH</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NAMA_DAERAH</font>            
               	  </td>
        </tr>
        #end
        
        #if($!dataHakmilik.NAMA_MUKIM!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">MUKIM</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NAMA_MUKIM</font>            
               	  </td>
        </tr>
        #end
        
        
        #if($!dataHakmilik.NO_HAKMILIK!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">NO. HAKMILIK</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
           #if($!dataHakmilik.KOD_JENIS_HAKMILIK!="")
           $!dataHakmilik.KOD_JENIS_HAKMILIK
           #end 
           &nbsp;$!dataHakmilik.NO_HAKMILIK</font>            
               	  </td>
        </tr>
        #end
        
        #if($!dataHakmilik.NO_LOT!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">NO. LOT</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NO_LOT</font>            
               	  </td>
        </tr>
        #end
        
        
        #if($!dataHakmilik.NO_PT!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">NO. PT</td>
          <td valign="top" >:</td>
          <td valign="top" ><font  color="blue">
            $!dataHakmilik.NO_PT</font>            
               	  </td>
        </tr>
        #end
        
        #if($!dataHakmilik.NO_SYIT!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">NO. SYIT</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NO_SYIT</font>            
               	  </td>
        </tr>
        #end
        
        #if($!dataHakmilik.KATEGORI_TANAH!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">KATEGORI TANAH</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.KATEGORI_TANAH</font>            
               	  </td>
        </tr>
        #end
        
        #if($!dataHakmilik.NAMA_LUAS_ASAL!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">LUAS ASAL</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NAMA_LUAS_ASAL</font>            
               	  </td>
        </tr>
        #end
      
      
      #if($!dataHakmilik.NAMA_LUAS_AMBIL!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">LUAS AMBIL</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NAMA_LUAS_AMBIL</font>            
               	  </td>
        </tr>
        #end
        
       
       </table>
       
       
       
      </td>
      <td width="50%" valign="top">
      
      <table width="100%">
       <tr>
          <td width="1%"></td>
          <td width="25%" align="left"></td>
          <td width="1%"></td>
          <td width="73%">    
               	</td>
        </tr>
        
        #if($!dataHakmilik.KEMENTERIAN!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">KEMENTERIAN</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.KEMENTERIAN</font>            
               	  </td>
        </tr>
        #end
        
        #if($!dataHakmilik.AGENSI!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">AGENSI</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.AGENSI</font>            
               	  </td>
        </tr>
        #end
        
        
        
        #if($!dataHakmilik.NO_FAIL!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">NO. FAIL JKPTG</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NO_FAIL</font>            
               	  </td>
        </tr>
        #end
        
        
        #if($!dataHakmilik.NO_RUJUKAN_PTG!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">NO. FAIL PTG</td>
          <td valign="top" >:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NO_RUJUKAN_PTG</font>            
               	  </td>
        </tr>
        #end
        
        
        #if($!dataHakmilik.NO_RUJUKAN_PTD!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">NO. FAIL PTD</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NO_RUJUKAN_PTD</font>            
               	  </td>
        </tr>
        #end
        
        
        #if($!dataHakmilik.NO_RUJUKAN_UPT!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">NO. FAIL UPT</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NO_RUJUKAN_UPT</font>            
               	  </td>
        </tr>
        #end
        
        
        #if($!dataHakmilik.TUJUAN!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left" valign="top" >PROJEK</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.TUJUAN</font>            
               	  </td>
        </tr>
        #end
     
      
        
        
        #if($!dataHakmilik.JENIS_RIZAB!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">JENIS RIZAB</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.JENIS_RIZAB</font>            
               	  </td>
        </tr>
        #end
        
        #if($!dataHakmilik.NAMA_LAIN_RIZAB!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">NAMA LAIN RIZAB</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NAMA_LAIN_RIZAB</font>            
               	  </td>
        </tr>
        #end
        
         #if($!dataHakmilik.NO_WARTA_RIZAB!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">NO. WARTA RIZAB</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.NO_WARTA_RIZAB</font>            
               	  </td>
        </tr>
        #end
      
      
       #if($!dataHakmilik.TARIKH_DAFTAR!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">TARIKH DAFTAR HAKMILIK</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.TARIKH_DAFTAR</font>            
               	  </td>
        </tr>
        #end
        
        
        #if($!dataHakmilik.TARIKH_LUPUT!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">TARIKH LUPUT</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.TARIKH_LUPUT</font>            
               	  </td>
        </tr>
        #end
        
        
         #if($!dataHakmilik.TEMPOH_LUPUT!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">TEMPOH LUPUT</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.TEMPOH_LUPUT</font>            
               	  </td>
        </tr>
        #end
        
        #if($!dataHakmilik.TARIKH_TERIMA_HM!="")
        <tr>
          <td align="right" valign="top"></td>
          <td align="left"  valign="top">TARIKH TERIMA HAKMILIK</td>
          <td  valign="top">:</td>
          <td  valign="top"><font  color="blue">
            $!dataHakmilik.TARIKH_TERIMA_HM</font>            
               	  </td>
        </tr>
        #end
      
     
       
     </table>
      
      </td>
      </tr>
      </table>
       </fieldset>
  		</td>
        </tr>
        <tr>
         <td colspan="4">
      <fieldset>
      <legend>KRONOLOGI HAKMILIK <input type="button" id="btnCariHakmilikReset" name="btnCariHakmilikReset" value="KEMBALI" onclick="resetSkrin()"/>
      <input type="button" name="cmdCetakPLA"  id="cmdCetakPLA" value="CETAK" onClick="javascript:cetakHakmilik($!ID_HAKMILIK)" /> 
      </legend>  
      <table width="100%">
      <tr>
      <td width="100%" valign="top">
     
     <table width="100%"  border="0" cellspacing="2" cellpadding="2">
     <tr class="table_header">
          <td scope="row" width="5%" valign="top" align="center"><strong>NO.</strong></td>
          <td scope="row" width="10%" valign="top" align="center"><strong>TARIKH</strong></td>
          <td width="85%"  valign="top"><strong>PERKARA</strong></td>  
             
         
        
        </tr>
     
      #if($list_kronologi.size()>0)
        #set ($count = 0)
        #foreach ( $kro in $list_kronologi )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2" )
        #else
        #set( $row = "row1" )
        #end
        <tr>
          <td class="$row" align="center" valign="top" ><font color="blue">$!kro.TURUTAN</font></td>
           <td class="$row" align="center" valign="top" ><font color="blue">$!kro.TARIKH</font></td>
         
          <td class="$row" valign="top" ><font color="blue">$kro.PEKARA</font></td>        
        
        </tr>
        #end
        
         #else
      <tr>  
        <td colspan="8"><font color="red">TIADA REKOD</font></td>    
      </tr>
      #end
     </table>
     
     
      </td>
      </tr>
      
      
      
      
      
      </table>
      </fieldset>
        
        </td>
        </tr>
        </table>