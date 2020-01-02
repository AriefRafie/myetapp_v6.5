#set($NAMA = "")
#set($IC = "")
#set($UMUR = "")
#set($JANTINA = "")
#set($ALAMAT = "")
#set($POSKOD = "")
#set($NEGERI = "")
#set($ID = "")

#if($TRAIN != "")
    #set($NAMA = $TRAIN.NAMA)
    #set($IC = $TRAIN.IC)
    #set($NAMA = $TRAIN.NAMA)
    #set($UMUR = $TRAIN.UMUR)
    #set($JANTINA = $TRAIN.JANTINA)
  	#set($ALAMAT = $TRAIN.ALAMAT)
    #set($POSKOD = $TRAIN.POSKOD)
  	#set($NEGERI = $TRAIN.NEGERI)
    #set($ID = $TRAIN.ID)
  
  
#end


<fieldset><legend >Maklumat Pengguna
</legend>  
<table border="0" width="100%" > 
  <tr>
    <td  width="29%"  >NAMA</td>
     <td width="1%" >:</td>
    <td width="70%"><input name="NAMA"  id="NAMA" value="$NAMA"/></td>
  </tr>
   <tr>
    <td >KAD PENGENALAAN</td>
     <td >:</td>
    <td><input name="IC"  id="IC" value="$IC"/></td>
  </tr>
   <tr>
    <td>UMUR</td>
    <td >:</td>
    <td ><input name="UMUR"  id="UMUR" value="$UMUR"/></td>
  </tr>
  <tr>
	<td >JANTINA</td>
    <td >:</td>
	<td><input type="text" name="JANTINA"  value="$JANTINA"/>
  </tr>
   <tr>
    <td valign="top" >ALAMAT</td>
    <td  valign="top"  >:</td>
    <td><textarea name="ALAMAT" id="ALAMAT"   value="$ALAMAT"/>$ALAMAT</textarea></td>
  </tr>
   <tr>
    <td >POSKOD</td>
    <td >:</td>
    <td><input name="POSKOD" id="POSKOD"  value="$POSKOD" /></td>
  </tr>
  <tr>
	<td >NEGERI</td>
    <td >:</td>
	<td>
	#set($list_NEGERI = ["KEDAH", "PULAU PINANG", "KELANTAN","TERENGGANU", "PERLIS", "PERAK","SELANGAOR", "WILAYAH PERSEKUTUAN", "MELAKA","NEGERI SEMBILAN", "JOHOR", "PAHANG", "SABAH", "SARAWAK", "LABUAN"])
	<select id = "NEGERI" name ="NEGERI">
	<option value="" >--SILIH PILIH--</option>
		#foreach($y in $list_NEGERI)
		<option value="$y" #if($NEGERI == "$y") selected = "selected" #end>$y</option>
		#end
	</select>
	</td>
	
 </tr>
 
  <tr>
	<td > </td>
    <td > </td>
	<td >
	 <input type="button" name="SIMPAN" id="SIMPAN" value="SIMPAN" onclick="javascript:btnSIMPAN()"/>
  
   #if($ID != "")
    <input name="BUANG" id="BUANG" value="HAPUS" type="button" onClick="javascript:btnBUANG($ID)">
    #end
	</td>
	
 </tr>
 </table>
 
 
</table>   

