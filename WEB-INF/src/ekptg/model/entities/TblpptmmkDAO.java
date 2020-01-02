package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Tblpptmmk entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see ekptg.model.entities.Tblpptmmk
  * @author MyEclipse Persistence Tools 
 */

public class TblpptmmkDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(TblpptmmkDAO.class);
	//property constants
	public static final String ID_PENARIKANBALIK = "idPenarikanbalik";
	public static final String JENIS_MMK = "jenisMmk";
	public static final String ULASAN = "ulasan";
	public static final String NO_RUJMMK	 = "noRujmmk	";
	public static final String FLAG_SEMAK	 = "flagSemak	";
	public static final String ID_SEMAK	 = "idSemak	";
	public static final String FLAG_BORANGI = "flagBorangi";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String TUJUAN = "tujuan";
	public static final String LATARBELAKANG = "latarbelakang";
	public static final String ASAS_PERTIMBANGAN	 = "asasPertimbangan	";
	public static final String KESIMPULAN	 = "kesimpulan	";
	public static final String SYOR	 = "syor	";
	public static final String KEDUDUKAN_LAPORAN_TNH = "kedudukanLaporanTnh";
	public static final String PENGESAHAN_PERUNTUKAN = "pengesahanPeruntukan";
	public static final String PANDANGAN = "pandangan";
	public static final String IMPLIKASI = "implikasi";
	public static final String ULASAN_JABTEKNIKAL = "ulasanJabteknikal";
	public static final String PERIHAL_TANAH = "perihalTanah";
	public static final String PERIHAL_POHON = "perihalPohon";
	public static final String ANGGARAN_KOS = "anggaranKos";
	public static final String PERAKUAN_PENTADBIR_TNH = "perakuanPentadbirTnh";
	public static final String NILAI_ATAS_TANAH = "nilaiAtasTanah";
	public static final String PENGECUALIAN_UPAH_UKUR = "pengecualianUpahUkur";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";



    
    public void save(Tblpptmmk transientInstance) {
        log.debug("saving Tblpptmmk instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tblpptmmk persistentInstance) {
        log.debug("deleting Tblpptmmk instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tblpptmmk findById( java.lang.Long id) {
        log.debug("getting Tblpptmmk instance with id: " + id);
        try {
            Tblpptmmk instance = (Tblpptmmk) getSession()
                    .get("ekptg.model.entities.Tblpptmmk", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Tblpptmmk instance) {
        log.debug("finding Tblpptmmk instance by example");
        try {
            List results = getSession()
                    .createCriteria("ekptg.model.entities.Tblpptmmk")
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
      log.debug("finding Tblpptmmk instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tblpptmmk as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIdPenarikanbalik(Object idPenarikanbalik
	) {
		return findByProperty(ID_PENARIKANBALIK, idPenarikanbalik
		);
	}
	
	public List findByJenisMmk(Object jenisMmk
	) {
		return findByProperty(JENIS_MMK, jenisMmk
		);
	}
	
	public List findByUlasan(Object ulasan
	) {
		return findByProperty(ULASAN, ulasan
		);
	}
	
	public List findByNoRujmmk	(Object noRujmmk	
	) {
		return findByProperty(NO_RUJMMK	, noRujmmk	
		);
	}
	
	public List findByFlagSemak	(Object flagSemak	
	) {
		return findByProperty(FLAG_SEMAK	, flagSemak	
		);
	}
	
	public List findByIdSemak	(Object idSemak	
	) {
		return findByProperty(ID_SEMAK	, idSemak	
		);
	}
	
	public List findByFlagBorangi(Object flagBorangi
	) {
		return findByProperty(FLAG_BORANGI, flagBorangi
		);
	}
	
	public List findByIdPermohonan(Object idPermohonan
	) {
		return findByProperty(ID_PERMOHONAN, idPermohonan
		);
	}
	
	public List findByTujuan(Object tujuan
	) {
		return findByProperty(TUJUAN, tujuan
		);
	}
	
	public List findByLatarbelakang(Object latarbelakang
	) {
		return findByProperty(LATARBELAKANG, latarbelakang
		);
	}
	
	public List findByAsasPertimbangan	(Object asasPertimbangan	
	) {
		return findByProperty(ASAS_PERTIMBANGAN	, asasPertimbangan	
		);
	}
	
	public List findByKesimpulan	(Object kesimpulan	
	) {
		return findByProperty(KESIMPULAN	, kesimpulan	
		);
	}
	
	public List findBySyor	(Object syor	
	) {
		return findByProperty(SYOR	, syor	
		);
	}
	
	public List findByKedudukanLaporanTnh(Object kedudukanLaporanTnh
	) {
		return findByProperty(KEDUDUKAN_LAPORAN_TNH, kedudukanLaporanTnh
		);
	}
	
	public List findByPengesahanPeruntukan(Object pengesahanPeruntukan
	) {
		return findByProperty(PENGESAHAN_PERUNTUKAN, pengesahanPeruntukan
		);
	}
	
	public List findByPandangan(Object pandangan
	) {
		return findByProperty(PANDANGAN, pandangan
		);
	}
	
	public List findByImplikasi(Object implikasi
	) {
		return findByProperty(IMPLIKASI, implikasi
		);
	}
	
	public List findByUlasanJabteknikal(Object ulasanJabteknikal
	) {
		return findByProperty(ULASAN_JABTEKNIKAL, ulasanJabteknikal
		);
	}
	
	public List findByPerihalTanah(Object perihalTanah
	) {
		return findByProperty(PERIHAL_TANAH, perihalTanah
		);
	}
	
	public List findByPerihalPohon(Object perihalPohon
	) {
		return findByProperty(PERIHAL_POHON, perihalPohon
		);
	}
	
	public List findByAnggaranKos(Object anggaranKos
	) {
		return findByProperty(ANGGARAN_KOS, anggaranKos
		);
	}
	
	public List findByPerakuanPentadbirTnh(Object perakuanPentadbirTnh
	) {
		return findByProperty(PERAKUAN_PENTADBIR_TNH, perakuanPentadbirTnh
		);
	}
	
	public List findByNilaiAtasTanah(Object nilaiAtasTanah
	) {
		return findByProperty(NILAI_ATAS_TANAH, nilaiAtasTanah
		);
	}
	
	public List findByPengecualianUpahUkur(Object pengecualianUpahUkur
	) {
		return findByProperty(PENGECUALIAN_UPAH_UKUR, pengecualianUpahUkur
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
		log.debug("finding all Tblpptmmk instances");
		try {
			String queryString = "from Tblpptmmk";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tblpptmmk merge(Tblpptmmk detachedInstance) {
        log.debug("merging Tblpptmmk instance");
        try {
            Tblpptmmk result = (Tblpptmmk) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tblpptmmk instance) {
        log.debug("attaching dirty Tblpptmmk instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tblpptmmk instance) {
        log.debug("attaching clean Tblpptmmk instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}