<script>
function papar(ID_PERMOHONAN,ID_STATUS,ID_FAIL,TARIKH_PERMOHONAN,FLAG_JENISPERMOHONAN,ID_SUBURUSAN,FLAG_SEGERA,USER_ROLE) {

//**START SEKSYEN 4 DAN 8

    if (ID_SUBURUSAN == '52')
	{
		//page 1
		if (ID_STATUS == '11' || ID_STATUS == '138'){		
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8&command=semakPendaftaran&ScreenLocation=top&paging=1";				
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//page 2
		else if (ID_STATUS == '127'){	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik&command=semakHM&ScreenLocation=top&paging=2";
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//page 3
		else if (ID_STATUS == '1612198' || ID_STATUS == '16'){
			if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"  || USER_ROLE=="(PPT)PenolongPegawaiTanahUnitPengambilanTanah"  || USER_ROLE=="(PPT)PenolongPengarahUnit"){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8&command=screenAgihan&ScreenLocation=top&paging=3";
			}else{
				alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pentadbir Tanah");
			}		
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 4
		else if (ID_STATUS == '22'){
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8JabatanTeknikal&command=semakSenaraiJabatan&ScreenLocation=top&paging=4&id_fail="+ID_FAIL+"&id_permohonan="+ID_PERMOHONAN;		
		//document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 5
		else if (ID_STATUS == '148' || ID_STATUS == '147'){			
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah&command=tambahLaporan&paging=5";	
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 6
		else if (ID_STATUS == '132' || ID_STATUS == '133' || ID_STATUS == '134'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmMMKSek8&command=viewMMK&ScreenLocation=top&paging=6";			
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 7
		else if (ID_STATUS == '31' || ID_STATUS == '26'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Warta&command=viewSenaraiWarta&ScreenLocation=top&paging=7";			
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}		
		//step 8
		else if (ID_STATUS == '43'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD&command=viewListHM&ScreenLocation=top&paging=8";			
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 9
		else if (ID_STATUS == '38'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan&command=penandaanKawasan&ScreenLocation=top&paging=9";			
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 11
		else if (ID_STATUS == '46'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciTanah&command=viewListHM&ScreenLocation=top&paging=11";			
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 12
		else if (ID_STATUS == '35'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8EndosanMemorialDHDK&command=viewEndosan&ScreenLocation=top&paging=12";			
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 13
		else if (ID_STATUS == '54'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF&command=viewListHM&ScreenLocation=top&paging=13";		
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 14
		else if (ID_STATUS == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam&command=viewListNotis&ScreenLocation=top&paging=14";		
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 15
		else if (ID_STATUS == '58'){
			//alert("yyy");
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyampaianNotis&command=viewListHM&ScreenLocation=top&paging=15";		
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 16
		else if (ID_STATUS == '62'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Siasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=16";		
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 17
		else if (ID_STATUS == '68' || ID_STATUS == '72'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan&command=viewlistHM&ScreenLocation=top&paging=17";	
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 18
		else if (ID_STATUS == '76'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8BorangK&command=viewListHM&ScreenLocation=top&paging=18";	
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 21
		else if (ID_STATUS == '1610242'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK&command=viewEndosan&ScreenLocation=top&paging=21";	
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 22
		else if (ID_STATUS == '59'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera&command=viewSegera&ScreenLocation=top&paging=22";
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		//step 23
		else if (ID_STATUS == '82'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur&command=viewListHM&ScreenLocation=top&paging=23";
			document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		}
		
		
		
		
		//pembatalan	
		else if (ID_STATUS == '235'){
				document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=senarai&sub_command=papar";
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;			
		}
		
		//penarikan	
		else if (ID_STATUS == '74'){
				document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=senarai&sub_command=papar";
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;			
		}
		
		
		
		
		
		
		
					// DALAM PROSES
					else if (ID_STATUS == '181'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
					
					// URUSAN DEPOSIT
					else if (ID_STATUS == '183'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
				
					// URUSAN MAHKAMAH
					else if (ID_STATUS == '184'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
					
					// TARIK BALIK BANTAHAN
					else if (ID_STATUS == '185'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}	
					
					// URUSAN BAYARAN
					else if (ID_STATUS == '186'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=UrusanPampasan";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}	
					
					// SELESAI
					else if (ID_STATUS == '187'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=UrusanPampasan";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}	
					
					// PEMBATALAN OLEH MT
					else if (ID_STATUS == '220'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=UrusanPampasan";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
					
					// BORANG O
					else if (ID_STATUS == '1610248'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}	
					
					// DALAM PROSES[AGENSI]
					else if (ID_STATUS == '199'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}		
					
					// URUSAN DEPOSIT[AGENSI]
					else if (ID_STATUS == '200'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
				
					// URUSAN MAHKAMAH[AGENSI]
					else if (ID_STATUS == '201'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
					
					// BORANG O [AGENSI]
					else if (ID_STATUS == '1610249'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}	
					
					// TARIK BALIK[AGENSI]
					else if (ID_STATUS == '203'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
				
					}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	else
	{
					//menu : pendaftaran
					//status : permohonan cawangan
					if (ID_STATUS == '11' || ID_STATUS == '138'){	
						
						if (ID_SUBURUSAN == '51'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=semak&paging=1";
						}else if (ID_SUBURUSAN == '52'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8&command=semakPendaftaran&ScreenLocation=top&paging=1";
						}else if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan&command=semakPendaftaran&ScreenLocation=top&paging=1";				
						}else{
							alert("Sila Hubungi Admin");
						}	
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close permohonan cawangan
				
					
					//menu : agihan tugas(new form)
					//status : disahkan pengarah
					else if (ID_STATUS == '127'){	
						
						if (ID_SUBURUSAN == '51'){
							if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"){
								document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4&command=tambahAgihan&ScreenLocation=top&paging=2";
							}else{
								alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pengarah");
							}
						}else if (ID_SUBURUSAN == '52'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik&command=semakHM&ScreenLocation=top&paging=2";
						}else if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik&command=semakHM&ScreenLocation=top&paging=2";			
						}else{
							alert("Sila Hubungi Admin");
						}	
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close agihan tugas(new form)
				
				
				
					
					//menu : Agihan tugas
					//status : Tindakan Pentadbir Tanah
					else if (ID_STATUS == '1612198'){
						
						if (ID_SUBURUSAN == '52'){
							if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"  || USER_ROLE=="(PPT)PenolongPegawaiTanahUnitPengambilanTanah"  || USER_ROLE=="(PPT)PenolongPengarahUnit"){
								document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8&command=screenAgihan&ScreenLocation=top&paging=3";
							}else{
								alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pentadbir Tanah");
							}	
						}else if (ID_SUBURUSAN == '53'){
							if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"  || USER_ROLE=="(PPT)PenolongPegawaiTanahUnitPengambilanTanah"  || USER_ROLE=="(PPT)PenolongPengarahUnit"){
								document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas&command=tambahAgihan&ScreenLocation=top&paging=3";
							}else{
								alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pentadbir Tanah");
							}						
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//Tindakan Pentadbir Tanah
					
					
					
					
					
					
					//menu : Laporan awal tanah
					//status : Tindakan pegawai
					else if (ID_STATUS == '148'){
						
						if (ID_SUBURUSAN == '51'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek4LaporanAwalTanahSenarai&command=tambahLaporan&paging=3";
						}else if (ID_SUBURUSAN == '52'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah&command=tambahLaporan&paging=4";
						}else if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah&command=tambahLaporan&paging=4";			
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close Laporan awal tanah
					
					//menu : Penyediaan Kertas MMK
					//status : Penyediaan laporan awal
					else if (ID_STATUS == '147' || ID_STATUS == '26'){
						
						if (ID_SUBURUSAN == '51'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai&command=viewSenaraiMMK&ScreenLocation=top&paging=4";
						}else if (ID_SUBURUSAN == '52'){
							
							if(ID_STATUS == '147'){
								document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD&command=viewListHM&ScreenLocation=top&paging=5";
							}else{
								
								document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Warta&command=viewSenaraiWarta&ScreenLocation=top&paging=7";
							}
						}else if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraMMK&command=viewMMK&paging=8";						
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close Penyediaan Kertas MMK
				
					
					//menu : Pewartaan / notis awam / Endorsan
					//status : Pewartaan
					else if (ID_STATUS == '31' || ID_STATUS == '52'){
						
						if (ID_SUBURUSAN == '51'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam&command=viewListNotis&ScreenLocation=top&paging=5";
						}else if (ID_SUBURUSAN == '52'){
							if(ID_STATUS == '31'){
								document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8EndosanMemorialDHDK&command=viewEndosan&ScreenLocation=top&paging=11";
							}else{
								document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyampaianNotis&command=viewListHM&ScreenLocation=top&paging=14";
							}
						}else if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraNotisAwam&command=viewListNotis&paging=9";						
						}else{
							alert("Sila Hubungi Admin");
						}	
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close pewartaan dan notis awam
				
					
					//menu : Hakmilik dan Pihak Berkepentingan
					//status : Maklumat hakmilik
					else if (ID_STATUS == '16'){
						
						if (ID_SUBURUSAN == '52'){
							if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"){
								document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8&command=screenAgihan&ScreenLocation=top&paging=3";
							}else{
								alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pengarah");
							}	
						}else if (ID_SUBURUSAN == '53'){
							if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"){
								document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas&command=tambahAgihan&ScreenLocation=top&paging=3";
							}else{
								alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pengarah");
							}						
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close hakmilik dan pihak berkepentingan
				
					
					//menu : Penyediaan Kertas MMK Sek 8
					//status : JPBD / JPPH dan Maklumat Jabatan Teknikal dan status2 mmk
					else if (ID_STATUS == '43' || ID_STATUS == '22' || ID_STATUS == '132' || ID_STATUS == '133' || ID_STATUS == '134'){
						
						if (ID_SUBURUSAN == '52'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmMMKSek8&command=viewMMK&ScreenLocation=top&paging=9";
						}else if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraMMK&command=viewListHM&ScreenLocation=top&paging=11";		
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close Penyediaan Kertas MMK Sek 8
				
				
					//menu : Borang E dan F
					//status : memorial/endosan dhdk
					else if (ID_STATUS == '35'){
						
						if (ID_SUBURUSAN == '52'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF&command=viewListHM&ScreenLocation=top&paging=12";
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close Borang E dan F
				
					
					//menu : Notis Awam
					//status : Borang F
					else if (ID_STATUS == '54'){
						
						if (ID_SUBURUSAN == '52'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam&command=viewListNotis&ScreenLocation=top&paging=13";
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close Notis Awam
				
					//menu : Penandaan Kawasan
					//status : Penyampaian Notis
					else if (ID_STATUS == '58'){
						
						if (ID_SUBURUSAN == '52'){
							if(FLAG_SEGERA == '1'){
								document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera&command=viewSegera&ScreenLocation=top&paging=22";
							}else{
								document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan&command=penandaanKawasan&ScreenLocation=top&paging=15";
							}
						}else if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraPenandaanKawasan&command=viewListHM&ScreenLocation=top&paging=12";				
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close Penandaan Kawasan
				
					
					//menu : Siasatan dan Perintah
					//status : Tanda kawasan
					else if (ID_STATUS == '38' || ID_STATUS == '62'){
						
						if (ID_SUBURUSAN == '52'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Siasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=16";
						}else if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=13";		
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close Siasatan dan Perintah
				
				
					//menu : Pampasan
					//status : Bicara
					else if (ID_STATUS == '68'){
						
						if (ID_SUBURUSAN == '52'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan&command=viewlistHM&ScreenLocation=top&paging=17";
						}else if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.SementaraPampasanPB&command=viewlistHM&ScreenLocation=top&paging=14";		
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close Pampasan
				
				
					//menu : Borang K
					//status : Bayaran Pampasan
					else if (ID_STATUS == '72' || ID_STATUS == '59'){
						
						if (ID_SUBURUSAN == '52'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8BorangK&command=viewListHM&ScreenLocation=top&paging=18";
						}else if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.SementaraPampasanPB&command=viewListHM&ScreenLocation=top&paging=14";			
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close Borang K
				
				
					//menu : Endorsan Borang K
					//status : Borang K
					else if (ID_STATUS == '76'){
						
						if (ID_SUBURUSAN == '52'){
							if(FLAG_SEGERA == '1'){
								document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan&command=penandaanKawasan&ScreenLocation=top&paging=15";
							}else{
								document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK&command=viewEndosan&ScreenLocation=top&paging=20";
							}
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close Endorsan Borang K
				
				
					//menu : Permintaan ukur
					//status : Permintaan ukur
					else if (ID_STATUS == '82'){		
						if (ID_SUBURUSAN == '52'){
							document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur&command=viewListHM&ScreenLocation=top&paging=23";
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//close Permintaan ukur
					
				//**END SEKSYEN 4 DAN 8
					
					
				// PENDUDUKAN/PENGGUNAAN SEMENTARA
				
					//menu : Rujukan Ke Mahkamah (Pendudukan/Penggunaan Sementara)
					//status : Rujukan Ke Mahkamah
					else if (ID_STATUS == '1610193'){		
						if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.SementaraBorangM&command=baruBorangM&ScreenLocation=top&paging=15";
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//Rujukan Ke Mahkamah	
					
					
					//menu : Perundingan
					//status : Set Perundingan dan Perundingan
					else if (ID_STATUS == '1610192' || ID_STATUS == '1610194'){		
						if (ID_SUBURUSAN == '53'){
							document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=13";
						}else{
							alert("Sila Hubungi Admin");
						}
						document.${formName}.id_permohonan.value = ID_PERMOHONAN;
					}//Rujukan Ke Mahkamah		
					
				//** END PENDUDUKAN/PENGGUNAAN SEMENTARA	
					
					
				//penarikan balik - razman
				
					else if  (ID_STATUS == '74'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=senarai&sub_command=papar";
							}
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;			
					}
					
				//pembatalan - razman	
					else if (ID_STATUS == '235'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=senarai&sub_command=papar";
							}
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;			
					}
					
				//bantahan - elly 
				
					// DALAM PROSES
					else if (ID_STATUS == '181'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
					
					// URUSAN DEPOSIT
					else if (ID_STATUS == '183'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
				
					// URUSAN MAHKAMAH
					else if (ID_STATUS == '184'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
					
					// TARIK BALIK BANTAHAN
					else if (ID_STATUS == '185'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}	
					
					// URUSAN BAYARAN
					else if (ID_STATUS == '186'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=UrusanPampasan";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}	
					
					// SELESAI
					else if (ID_STATUS == '187'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=UrusanPampasan";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}	
					
					// PEMBATALAN OLEH MT
					else if (ID_STATUS == '220'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=UrusanPampasan";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
					
					// BORANG O
					else if (ID_STATUS == '1610248'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}	
					
					// DALAM PROSES[AGENSI]
					else if (ID_STATUS == '199'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}		
					
					// URUSAN DEPOSIT[AGENSI]
					else if (ID_STATUS == '200'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
				
					// URUSAN MAHKAMAH[AGENSI]
					else if (ID_STATUS == '201'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}
					
					// BORANG O [AGENSI]
					else if (ID_STATUS == '1610249'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
					}	
					
					// TARIK BALIK[AGENSI]
					else if (ID_STATUS == '203'){
							if (ID_SUBURUSAN == '52'){
								document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
							}
								document.${formName}.id_fail.value = ID_FAIL;	
								document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
				
					}else{
						alert("Sila Hubungi Admin");
						return;
					}	
	}
	
	
	document.${formName}.flag_MyInfoPPT.value = "yes";
	document.${formName}.method="POST";
	document.${formName}.submit();
	
}
</script>