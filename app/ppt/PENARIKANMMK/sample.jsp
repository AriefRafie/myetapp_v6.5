<table width="100%">
            <tr  >
              <td width="4%"  valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")1.</td>
              <td width="20%" valign="top"> Tujuan</td>
              <td width="1%" valign="top">:</td>
       #if($readmode == "edit")
        <td width="75%" valign="top">     
        <textarea name="txtTujuan" id="txtTujuan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$txtTujuan</textarea>           
        <div><span id="txtTujuan_num" style="color:blue;" ></span></div>
        <script>	
		    var area1 = new FCKeditor('txtTujuan');
	      	area1.BasePath = '/${appname}/library/fck/' ;
	      	area1.Height = 300;
	      	area1.Width = '100%';
	      	area1.ReplaceTextarea(); 
		</script>
         <div id="txtTujuan_check" class="alert_msg" ></div>        </td>
         #else
          <td width="70%"  style="background-color:#ECE5B6;"   >  
        $txtTujuan
         <textarea name="txtTujuan" id="txtTujuan"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" style="display:none" >$txtTujuan</textarea>          
           <div style="display:none"><span id="txtTujuan_num" style="color:blue;" ></span></div>
          <div id="txtTujuan_check" class="alert_msg"></div>         </td>
         #end        </tr>
         
         
         <tr>
              <td valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")2.</td>
              <td valign="top">Latarbelakang Permohonan</td>
              <td valign="top">:</td>
               
               
                #if($readmode == "edit")
        <td width="70%">     
        <textarea name="txtLatarbelakang" id="txtLatarbelakang"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$txtLatarbelakang</textarea>           
        <div><span id="txtLatarbelakang_num" style="color:blue;" ></span></div>
        <script>	
		    var area3 = new FCKeditor('txtLatarbelakang');
	      	area3.BasePath = '/${appname}/library/fck/' ;
	      	area3.Height = 300;
	      	area3.Width = '100%';
	      	area3.ReplaceTextarea(); 
		</script>
         <div id="txtLatarbelakang_check" class="alert_msg" ></div>        </td>
         #else
          <td width="70%"  style="background-color:#ECE5B6;">  
        $txtLatarbelakang
         <textarea name="txtLatarbelakang" id="txtLatarbelakang"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" style="display:none" >$txtLatarbelakang</textarea>          
          <div style="display:none"><span id="txtLatarbelakang_num" style="color:blue;" ></span></div>
          <div id="txtLatarbelakang_check" class="alert_msg"></div>         </td>
         #end            </tr>
         
         
         
         
         
            
            <tr>
              <td valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")3.</td>
              <td valign="top">&nbsp;</td>
              <td valign="top">:</td>
              #if($readmode == "edit")
        <td width="70%">     
        <textarea name="txtImplikasi" id="txtImplikasi"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$txtImplikasi</textarea>           
        <div><span id="txtImplikasi_num" style="color:blue;" ></span></div>
        <script>	
		    var area4 = new FCKeditor('txtImplikasi');
	      	area4.BasePath = '/${appname}/library/fck/' ;
	      	area4.Height = 300;
	      	area4.Width = '100%';
	      	area4.ReplaceTextarea(); 
		</script>
         <div id="txtImplikasi_check" class="alert_msg" ></div>        </td>
         #else
          <td width="70%"  style="background-color:#ECE5B6;">  
        $txtImplikasi
         <textarea name="txtImplikasi" id="txtImplikasi"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" style="display:none" >$txtImplikasi</textarea>          
          <div style="display:none"><span id="txtImplikasi_num" style="color:blue;" ></span></div>
          <div id="txtImplikasi_check" class="alert_msg"></div>         </td>
         #end            </tr>
            <tr>
              <td valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
              <td valign="top">Syor</td>
              <td valign="top">:</td>
              #if($readmode == "edit")
        <td width="70%">     
        <textarea name="txtSyor" id="txtSyor"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$txtSyor</textarea>           
        <div><span id="txtSyor_num" style="color:blue;" ></span></div>
        <script>	
		    var area5 = new FCKeditor('txtSyor');
	      	area5.BasePath = '/${appname}/library/fck/' ;
	      	area5.Height = 300;
	      	area5.Width = '100%';
	      	area5.ReplaceTextarea(); 
		</script>
         <div id="txtSyor_check" class="alert_msg"></div>   
        </td>
         #else
          <td width="70%"  style="background-color:#ECE5B6;">  
        $txtSyor
         <textarea name="txtSyor" id="txtSyor"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" style="display:none" >$txtSyor</textarea>          
          <div style="display:none"><span id="txtSyor_num" style="color:blue;" ></span></div>
          <div id="txtSyor_check" class="alert_msg"></div>   
         </td>
         #end 
            </tr>
            
            <tr>
              <td valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
              <td valign="top">Sebab Penarikan Balik</td>
              <td valign="top">:</td>
               
               
                #if($readmode == "edit")
        <td width="70%">     
        <textarea name="txtSebab" id="txtSebab"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" >$txtSebab</textarea>           
        <div><span id="txtSebab_num" style="color:blue;" ></span></div>
        <script>	
		    var area2 = new FCKeditor('txtSebab');
	      	area2.BasePath = '/${appname}/library/fck/' ;
	      	area2.Height = 300;
	      	area2.Width = '100%';
	      	area2.ReplaceTextarea(); 
		</script>
         <div id="txtSebab_check" class="alert_msg" ></div>        </td>
         #else
          <td width="70%"  style="background-color:#ECE5B6;">  
        $txtSebab
         <textarea name="txtSebab" id="txtSebab"  cols="80"  rows="6"  $readonlymode class = "$disabledmode" style="display:none" >$txtSebab</textarea>          
          <div style="display:none"><span id="txtSebab_num" style="color:blue;" ></span></div>
          <div id="txtSebab_check" class="alert_msg"></div>         </td>
         #end            </tr>
          </table>