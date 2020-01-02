
#set($boleh_kemaskini = "yes")



#if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165" && $id_Status != "166" && $id_Status != "167" && $id_Status != "180")

#set($boleh_kemaskini = "yes")

#else


#if($portal_role == "adminppk" || $portal_role == "adminstate")
#set($boleh_kemaskini = "yes")
#else

 #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
#set($boleh_kemaskini = "yes")
#else
#set($boleh_kemaskini = "")
#end


#end

#end
