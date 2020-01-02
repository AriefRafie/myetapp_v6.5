package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Tblppttanahumum entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see ekptg.model.entities.Tblppttanahumum
  * @author MyEclipse Persistence Tools 
 */

public class TblppttanahumumDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(TblppttanahumumDAO.class);
	//property constants
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String PERIHAL_SYIT = "perihalSyit";
	public static final String JENIS_TANAH = "jenisTanah";
	public static final String PERIHAL_KAWASAN_SIMPAN = "perihalKawasanSimpan";
	public static final String PERIHAL_KAWASAN_MAJLIS = "perihalKawasanMajlis";
	public static final String PERIHAL_KAWASAN_LAIN2 = "perihalKawasanLain2";
	public static final String LOKASI_TANAH = "lokasiTanah";
	public static final String KEADAAN_RUPABUMI = "keadaanRupabumi";
	public static final String KEMUDAHAN_AWAM = "kemudahanAwam";
	public static final String PERIHAL_BANGUNAN = "perihalBangunan";
	public static final String ULASAN_SYOR = "ulasanSyor";
	public static final String PERIHAL_TM_SENDIRI = "perihalTmSendiri";
	public static final String PERIHAL_TNH_KERAJAAN = "perihalTnhKerajaan";
	public static final String BIL_HAKMILIK = "bilHakmilik";
	public static final String ID_UNITLUAS = "idUnitluas";
	public static final String LUAS_TERLIBAT = "luasTerlibat";
	public static final String HARGA_ANGGAR = "hargaAnggar";
	public static final String UNIT_ANGGAR = "unitAnggar";
	public static final String HARGA_ANGGAR_BANGUNAN = "hargaAnggarBangunan";
	public static final String PERIHAL_TMTR_SEKUTUAN = "perihalTmtrSekutuan";
	public static final String PERIHAL_TR_NEGERI = "perihalTrNegeri";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";



    
    public void save(Tblppttanahumum transientInstance) {
        log.debug("saving Tblppttanahumum instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tblppttanahumum persistentInstance) {
        log.debug("deleting Tblppttanahumum instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tblppttanahumum findById( java.lang.Long id) {
        log.debug("getting Tblppttanahumum instance with id: " + id);
        try {
            Tblppttanahumum instance = (Tblppttanahumum) getSession()
                    .get("ekptg.model.entities.Tblppttanahumum", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Tblppttanahumum instance) {
        log.debug("finding Tblppttanahumum instance by example");
        try {
            List results = getSession()
                    .createCriteria("ekptg.model.entities.Tblppttanahumum")
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
      log.debug("finding Tblppttanahumum instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tblppttanahumum as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIdPermohonan(Object idPermohonan
	) {
		return findByProperty(ID_PERMOHONAN, idPermohonan
		);
	}
	
	public List findByPerihalSyit(Object perihalSyit
	) {
		return findByProperty(PERIHAL_SYIT, perihalSyit
		);
	}
	
	public List findByJenisTanah(Object jenisTanah
	) {
		return findByProperty(JENIS_TANAH, jenisTanah
		);
	}
	
	public List findByPerihalKawasanSimpan(Object perihalKawasanSimpan
	) {
		return findByProperty(PERIHAL_KAWASAN_SIMPAN, perihalKawasanSimpan
		);
	}
	
	public List findByPerihalKawasanMajlis(Object perihalKawasanMajlis
	) {
		return findByProperty(PERIHAL_KAWASAN_MAJLIS, perihalKawasanMajlis
		);
	}
	
	public List findByPerihalKawasanLain2(Object perihalKawasanLain2
	) {
		return findByProperty(PERIHAL_KAWASAN_LAIN2, perihalKawasanLain2
		);
	}
	
	public List findByLokasiTanah(Object lokasiTanah
	) {
		return findByProperty(LOKASI_TANAH, lokasiTanah
		);
	}
	
	public List findByKeadaanRupabumi(Object keadaanRupabumi
	) {
		return findByProperty(KEADAAN_RUPABUMI, keadaanRupabumi
		);
	}
	
	public List findByKemudahanAwam(Object kemudahanAwam
	) {
		return findByProperty(KEMUDAHAN_AWAM, kemudahanAwam
		);
	}
	
	public List findByPerihalBangunan(Object perihalBangunan
	) {
		return findByProperty(PERIHAL_BANGUNAN, perihalBangunan
		);
	}
	
	public List findByUlasanSyor(Object ulasanSyor
	) {
		return findByProperty(ULASAN_SYOR, ulasanSyor
		);
	}
	
	public List findByPerihalTmSendiri(Object perihalTmSendiri
	) {
		return findByProperty(PERIHAL_TM_SENDIRI, perihalTmSendiri
		);
	}
	
	public List findByPerihalTnhKerajaan(Object perihalTnhKerajaan
	) {
		return findByProperty(PERIHAL_TNH_KERAJAAN, perihalTnhKerajaan
		);
	}
	
	public List findByBilHakmilik(Object bilHakmilik
	) {
		return findByProperty(BIL_HAKMILIK, bilHakmilik
		);
	}
	
	public List findByIdUnitluas(Object idUnitluas
	) {
		return findByProperty(ID_UNITLUAS, idUnitluas
		);
	}
	
	public List findByLuasTerlibat(Object luasTerlibat
	) {
		return findByProperty(LUAS_TERLIBAT, luasTerlibat
		);
	}
	
	public List findByHargaAnggar(Object hargaAnggar
	) {
		return findByProperty(HARGA_ANGGAR, hargaAnggar
		);
	}
	
	public List findByUnitAnggar(Object unitAnggar
	) {
		return findByProperty(UNIT_ANGGAR, unitAnggar
		);
	}
	
	public List findByHargaAnggarBangunan(Object hargaAnggarBangunan
	) {
		return findByProperty(HARGA_ANGGAR_BANGUNAN, hargaAnggarBangunan
		);
	}
	
	public List findByPerihalTmtrSekutuan(Object perihalTmtrSekutuan
	) {
		return findByProperty(PERIHAL_TMTR_SEKUTUAN, perihalTmtrSekutuan
		);
	}
	
	public List findByPerihalTrNegeri(Object perihalTrNegeri
	) {
		return findByProperty(PERIHAL_TR_NEGERI, perihalTrNegeri
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
		log.debug("finding all Tblppttanahumum instances");
		try {
			String queryString = "from Tblppttanahumum";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tblppttanahumum merge(Tblppttanahumum detachedInstance) {
        log.debug("merging Tblppttanahumum instance");
        try {
            Tblppttanahumum result = (Tblppttanahumum) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tblppttanahumum instance) {
        log.debug("attaching dirty Tblppttanahumum instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tblppttanahumum instance) {
        log.debug("attaching clean Tblppttanahumum instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}