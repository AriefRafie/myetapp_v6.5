


<br />
<br />




<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="id_esaduan" type="hidden" id="id_esaduan" value="$!id_esaduan"/>
<input type="hidden" id="flag_buka_upload" name="flag_buka_upload" value="$!flag_buka_upload"  />
<input name="mode" type="hidden" id="mode" value="$mode"/>

<!--ScrollX :  --><input type="hidden" id="ScrollX" name='ScrollX'  />
<!--ScrollY :  --><input type="hidden" id="ScrollY" name='ScrollY'  />

xxxxxxxxxxxxx : $view_skrin


#parse("app/ppk/perintah/frmCarian.jsp")

<div id="div_senaraiUtama" >
#parse("app/ppk/perintah/SenaraiUtama.jsp")
</div>






<br />
