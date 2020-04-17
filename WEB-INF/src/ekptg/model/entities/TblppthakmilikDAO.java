package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Tblppthakmilik entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see ekptg.model.entities.Tblppthakmilik
  * @author MyEclipse Persistence Tools 
 */

public class TblppthakmilikDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(TblppthakmilikDAO.class);
	//property constants
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_MUKIM = "idMukim";
	public static final String ID_JENISHAKMILIK = "idJenishakmilik";
	public static final String NO_HAKMILIK = "noHakmilik";
	public static final String ID_SUBJAKET = "idSubjaket";
	public static final String FLAG_REZAB = "flagRezab";
	public static final String FLAG_GSA = "flagGsa";
	public static final String TEMPOH_LUPUT = "tempohLuput";
	public static final String TARIKH_LUPUT = "tarikhLuput";
	public static final String NO_PT = "noPt";
	public static final String ID_UNITLUASPT = "idUnitluaspt";
	public static final String LUAS_PT = "luasPt";
	public static final String NO_LOT = "noLot";
	public static final String ID_UNITLUASLOT = "idUnitluaslot";
	public static final String LUAS_LOT = "luasLot";
	public static final String ID_UNITLUASAMBIL = "idUnitluasambil";
	public static final String LUAS_AMBIL = "luasAmbil";
	public static final String ID_UNITLUASBARU = "idUnitluasbaru";
	public static final String LUAS_BARU = "luasBaru";
	public static final String NO_PELAN = "noPelan";
	public static final String NO_SYIT = "noSyit";
	public static final String LOKASI = "lokasi";
	public static final String ID_KATEGORITANAH = "idKategoritanah";
	public static final String SYARAT_NYATA = "syaratNyata";
	public static final String SYARAT_KHAS = "syaratKhas";
	public static final String SEKATAN_KEPENTINGAN = "sekatanKepentingan";
	public static final String SEKATAN_HAK = "sekatanHak";
	public static final String JENIS_MILIK = "jenisMilik";
	public static final String ULASAN_PENTADBIR = "ulasanPentadbir";
	public static final String JUMLAH_AWARD = "jumlahAward";
	public static final String UNIT_AWARD = "unitAward";
	public static final String FLAG_AMBIL_SEGERA = "flagAmbilSegera";
	public static final String FLAG_PEMBATALAN = "flagPembatalan";
	public static final String FLAG_PENARIKAN_BALIK = "flagPenarikanBalik";
	public static final String FLAG_LAPORAN_TANAH = "flagLaporanTanah";
	public static final String FLAG_HANTAR_DHD = "flagHantarDhd";
	public static final String FLAG_TERIMA_DHD = "flagTerimaDhd";
	public static final String FLAG_SIASATAN = "flagSiasatan";
	public static final String FLAG_BORANGL = "flagBorangl";
	public static final String FLAG_PERMINTAAN_UKUR = "flagPermintaanUkur";
	public static final String ID_SIASATAN = "idSiasatan";
	public static final String ID_BORANGK = "idBorangk";
	public static final String ID_BORANGG = "idBorangg";
	public static final String ID_BORANGE = "idBorange";
	public static final String ID_BORANGI = "idBorangi";
	public static final String NO_PERMINTAANUKUR = "noPermintaanukur";
	public static final String ID_BORANGL = "idBorangl";
	public static final String ID_PENARIKANBALIK = "idPenarikanbalik";
	public static final String ID_PEMBATALAN = "idPembatalan";
	public static final String FLAG_UBAH = "flagUbah";
	public static final String NO_BANGUNAN = "noBangunan";
	public static final String NO_TINGKAT = "noTingkat";
	public static final String NO_PETAK = "noPetak";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String NO_KELULUSAN = "noKelulusan";
	public static final String NO_DAFTAR = "noDaftar";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";



    
    public void save(Tblppthakmilik transientInstance) {
        log.debug("saving Tblppthakmilik instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tblppthakmilik persistentInstance) {
        log.debug("deleting Tblppthakmilik instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tblppthakmilik findById( java.lang.Long id) {
        log.debug("getting Tblppthakmilik instance with id: " + id);
        try {
            Tblppthakmilik instance = (Tblppthakmilik) getSession()
                    .get("ekptg.model.entities.Tblppthakmilik", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Tblppthakmilik instance) {
        log.debug("finding Tblppthakmilik instance by example");
        try {
            List results = getSession()
                    .createCriteria("ekptg.model.entities.Tblppthakmilik")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Tblppthakmilik instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tblppthakmilik as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIdNegeri(Object idNegeri
	) {
		return findByProperty(ID_NEGERI, idNegeri
		);
	}
	
	public List findByIdDaerah(Object idDaerah
	) {
		return findByProperty(ID_DAERAH, idDaerah
		);
	}
	
	public List findByIdMukim(Object idMukim
	) {
		return findByProperty(ID_MUKIM, idMukim
		);
	}
	
	public List findByIdJenishakmilik(Object idJenishakmilik
	) {
		return findByProperty(ID_JENISHAKMILIK, idJenishakmilik
		);
	}
	
	public List findByNoHakmilik(Object noHakmilik
	) {
		return findByProperty(NO_HAKMILIK, noHakmilik
		);
	}
	
	public List findByIdSubjaket(Object idSubjaket
	) {
		return findByProperty(ID_SUBJAKET, idSubjaket
		);
	}
	
	public List findByFlagRezab(Object flagRezab
	) {
		return findByProperty(FLAG_REZAB, flagRezab
		);
	}
	
	public List findByFlagGsa(Object flagGsa
	) {
		return findByProperty(FLAG_GSA, flagGsa
		);
	}
	
	public List findByTempohLuput(Object tempohLuput
	) {
		return findByProperty(TEMPOH_LUPUT, tempohLuput
		);
	}
	
	public List findByTarikhLuput(Object tarikhLuput
	) {
		return findByProperty(TARIKH_LUPUT, tarikhLuput
		);
	}
	
	public List findByNoPt(Object noPt
	) {
		return findByProperty(NO_PT, noPt
		);
	}
	
	public List findByIdUnitluaspt(Object idUnitluaspt
	) {
		return findByProperty(ID_UNITLUASPT, idUnitluaspt
		);
	}
	
	public List findByLuasPt(Object luasPt
	) {
		return findByProperty(LUAS_PT, luasPt
		);
	}
	
	public List findByNoLot(Object noLot
	) {
		return findByProperty(NO_LOT, noLot
		);
	}
	
	public List findByIdUnitluaslot(Object idUnitluaslot
	) {
		return findByProperty(ID_UNITLUASLOT, idUnitluaslot
		);
	}
	
	public List findByLuasLot(Object luasLot
	) {
		return findByProperty(LUAS_LOT, luasLot
		);
	}
	
	public List findByIdUnitluasambil(Object idUnitluasambil
	) {
		return findByProperty(ID_UNITLUASAMBIL, idUnitluasambil
		);
	}
	
	public List findByLuasAmbil(Object luasAmbil
	) {
		return findByProperty(LUAS_AMBIL, luasAmbil
		);
	}
	
	public List findByIdUnitluasbaru(Object idUnitluasbaru
	) {
		return findByProperty(ID_UNITLUASBARU, idUnitluasbaru
		);
	}
	
	public List findByLuasBaru(Object luasBaru
	) {
		return findByProperty(LUAS_BARU, luasBaru
		);
	}
	
	public List findByNoPelan(Object noPelan
	) {
		return findByProperty(NO_PELAN, noPelan
		);
	}
	
	public List findByNoSyit(Object noSyit
	) {
		return findByProperty(NO_SYIT, noSyit
		);
	}
	
	public List findByLokasi(Object lokasi
	) {
		return findByProperty(LOKASI, lokasi
		);
	}
	
	public List findByIdKategoritanah(Object idKategoritanah
	) {
		return findByProperty(ID_KATEGORITANAH, idKategoritanah
		);
	}
	
	public List findBySyaratNyata(Object syaratNyata
	) {
		return findByProperty(SYARAT_NYATA, syaratNyata
		);
	}
	
	public List findBySyaratKhas(Object syaratKhas
	) {
		return findByProperty(SYARAT_KHAS, syaratKhas
		);
	}
	
	public List findBySekatanKepentingan(Object sekatanKepentingan
	) {
		return findByProperty(SEKATAN_KEPENTINGAN, sekatanKepentingan
		);
	}
	
	public List findBySekatanHak(Object sekatanHak
	) {
		return findByProperty(SEKATAN_HAK, sekatanHak
		);
	}
	
	public List findByJenisMilik(Object jenisMilik
	) {
		return findByProperty(JENIS_MILIK, jenisMilik
		);
	}
	
	public List findByUlasanPentadbir(Object ulasanPentadbir
	) {
		return findByProperty(ULASAN_PENTADBIR, ulasanPentadbir
		);
	}
	
	public List findByJumlahAward(Object jumlahAward
	) {
		return findByProperty(JUMLAH_AWARD, jumlahAward
		);
	}
	
	public List findByUnitAward(Object unitAward
	) {
		return findByProperty(UNIT_AWARD, unitAward
		);
	}
	
	public List findByFlagAmbilSegera(Object flagAmbilSegera
	) {
		return findByProperty(FLAG_AMBIL_SEGERA, flagAmbilSegera
		);
	}
	
	public List findByFlagPembatalan(Object flagPembatalan
	) {
		return findByProperty(FLAG_PEMBATALAN, flagPembatalan
		);
	}
	
	public List findByFlagPenarikanBalik(Object flagPenarikanBalik
	) {
		return findByProperty(FLAG_PENARIKAN_BALIK, flagPenarikanBalik
		);
	}
	
	public List findByFlagLaporanTanah(Object flagLaporanTanah
	) {
		return findByProperty(FLAG_LAPORAN_TANAH, flagLaporanTanah
		);
	}
	
	public List findByFlagHantarDhd(Object flagHantarDhd
	) {
		return findByProperty(FLAG_HANTAR_DHD, flagHantarDhd
		);
	}
	
	public List findByFlagTerimaDhd(Object flagTerimaDhd
	) {
		return findByProperty(FLAG_TERIMA_DHD, flagTerimaDhd
		);
	}
	
	public List findByFlagSiasatan(Object flagSiasatan
	) {
		return findByProperty(FLAG_SIASATAN, flagSiasatan
		);
	}
	
	public List findByFlagBorangl(Object flagBorangl
	) {
		return findByProperty(FLAG_BORANGL, flagBorangl
		);
	}
	
	public List findByFlagPermintaanUkur(Object flagPermintaanUkur
	) {
		return findByProperty(FLAG_PERMINTAAN_UKUR, flagPermintaanUkur
		);
	}
	
	public List findByIdSiasatan(Object idSiasatan
	) {
		return findByProperty(ID_SIASATAN, idSiasatan
		);
	}
	
	public List findByIdBorangk(Object idBorangk
	) {
		return findByProperty(ID_BORANGK, idBorangk
		);
	}
	
	public List findByIdBorangg(Object idBorangg
	) {
		return findByProperty(ID_BORANGG, idBorangg
		);
	}
	
	public List findByIdBorange(Object idBorange
	) {
		return findByProperty(ID_BORANGE, idBorange
		);
	}
	
	public List findByIdBorangi(Object idBorangi
	) {
		return findByProperty(ID_BORANGI, idBorangi
		);
	}
	
	public List findByNoPermintaanukur(Object noPermintaanukur
	) {
		return findByProperty(NO_PERMINTAANUKUR, noPermintaanukur
		);
	}
	
	public List findByIdBorangl(Object idBorangl
	) {
		return findByProperty(ID_BORANGL, idBorangl
		);
	}
	
	public List findByIdPenarikanbalik(Object idPenarikanbalik
	) {
		return findByProperty(ID_PENARIKANBALIK, idPenarikanbalik
		);
	}
	
	public List findByIdPembatalan(Object idPembatalan
	) {
		return findByProperty(ID_PEMBATALAN, idPembatalan
		);
	}
	
	public List findByFlagUbah(Object flagUbah
	) {
		return findByProperty(FLAG_UBAH, flagUbah
		);
	}
	
	public List findByNoBangunan(Object noBangunan
	) {
		return findByProperty(NO_BANGUNAN, noBangunan
		);
	}
	
	public List findByNoTingkat(Object noTingkat
	) {
		return findByProperty(NO_TINGKAT, noTingkat
		);
	}
	
	public List findByNoPetak(Object noPetak
	) {
		return findByProperty(NO_PETAK, noPetak
		);
	}
	
	public List findByIdPermohonan(Object idPermohonan
	) {
		return findByProperty(ID_PERMOHONAN, idPermohonan
		);
	}
	
	public List findByNoKelulusan(Object noKelulusan
	) {
		return findByProperty(NO_KELULUSAN, noKelulusan
		);
	}
	
	public List findByNoDaftar(Object noDaftar
	) {
		return findByProperty(NO_DAFTAR, noDaftar
		);
	}
	
	public List findByIdMasuk(Object idMasuk
	) {
		return findByProperty(ID_MASUK, idMasuk
		);
	}
	
	public List findByIdKemaskini(Object idKemaskini
	) {
		return findByProperty(ID_KEMASKINI, idKemaskini
		);
	}
	
	public List findByIdDb(Object idDb
	) {
		return findByProperty(ID_DB, idDb
		);
	}
	

	public List findAll() {
		log.debug("finding all Tblppthakmilik instances");
		try {
			String queryString = "from Tblppthakmilik";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tblppthakmilik merge(Tblppthakmilik detachedInstance) {
        log.debug("merging Tblppthakmilik instance");
        try {
            Tblppthakmilik result = (Tblppthakmilik) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tblppthakmilik instance) {
        log.debug("attaching dirty Tblppthakmilik instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tblppthakmilik instance) {
        log.debug("attaching clean Tblppthakmilik instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}