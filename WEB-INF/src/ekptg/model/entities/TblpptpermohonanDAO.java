package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Tblpptpermohonan entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see ekptg.model.entities.Tblpptpermohonan
  * @author MyEclipse Persistence Tools 
 */

public class TblpptpermohonanDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(TblpptpermohonanDAO.class);
	//property constants
	public static final String ID_FAIL = "idFail";
	public static final String NO_PERMOHONAN = "noPermohonan";
	public static final String NO_PERSERAHAN = "noPerserahan";
	public static final String ID_AGENSI = "idAgensi";
	public static final String FLAG_PERUNTUKAN = "flagPeruntukan";
	public static final String FLAG_SEGERA = "flagSegera";
	public static final String TUJUAN = "tujuan";
	public static final String NO_RUJUKAN_SURAT = "noRujukanSurat";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_MUKIM = "idMukim";
	public static final String ID_STATUS = "idStatus";
	public static final String ID_DAERAH = "idDaerah";
	public static final String PERIHAL_KAWASAN = "perihalKawasan";
	public static final String ID_SEMAK = "idSemak";
	public static final String ID_SAHKAN = "idSahkan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String NO_RUJUKAN_PTG = "noRujukanPtg";
	public static final String NO_RUJUKAN_PTD = "noRujukanPtd";
	public static final String NO_RUJUKAN_UPT = "noRujukanUpt";



    
    public void save(Tblpptpermohonan transientInstance) {
        log.debug("saving Tblpptpermohonan instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tblpptpermohonan persistentInstance) {
        log.debug("deleting Tblpptpermohonan instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tblpptpermohonan findById( java.lang.Long id) {
        log.debug("getting Tblpptpermohonan instance with id: " + id);
        try {
            Tblpptpermohonan instance = (Tblpptpermohonan) getSession()
                    .get("ekptg.model.entities.Tblpptpermohonan", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Tblpptpermohonan instance) {
        log.debug("finding Tblpptpermohonan instance by example");
        try {
            List results = getSession()
                    .createCriteria("ekptg.model.entities.Tblpptpermohonan")
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
      log.debug("finding Tblpptpermohonan instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tblpptpermohonan as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIdFail(Object idFail
	) {
		return findByProperty(ID_FAIL, idFail
		);
	}
	
	public List findByNoPermohonan(Object noPermohonan
	) {
		return findByProperty(NO_PERMOHONAN, noPermohonan
		);
	}
	
	public List findByNoPerserahan(Object noPerserahan
	) {
		return findByProperty(NO_PERSERAHAN, noPerserahan
		);
	}
	
	public List findByIdAgensi(Object idAgensi
	) {
		return findByProperty(ID_AGENSI, idAgensi
		);
	}
	
	public List findByFlagPeruntukan(Object flagPeruntukan
	) {
		return findByProperty(FLAG_PERUNTUKAN, flagPeruntukan
		);
	}
	
	public List findByFlagSegera(Object flagSegera
	) {
		return findByProperty(FLAG_SEGERA, flagSegera
		);
	}
	
	public List findByTujuan(Object tujuan
	) {
		return findByProperty(TUJUAN, tujuan
		);
	}
	
	public List findByNoRujukanSurat(Object noRujukanSurat
	) {
		return findByProperty(NO_RUJUKAN_SURAT, noRujukanSurat
		);
	}
	
	public List findByAlamat1(Object alamat1
	) {
		return findByProperty(ALAMAT1, alamat1
		);
	}
	
	public List findByAlamat2(Object alamat2
	) {
		return findByProperty(ALAMAT2, alamat2
		);
	}
	
	public List findByAlamat3(Object alamat3
	) {
		return findByProperty(ALAMAT3, alamat3
		);
	}
	
	public List findByPoskod(Object poskod
	) {
		return findByProperty(POSKOD, poskod
		);
	}
	
	public List findByIdNegeri(Object idNegeri
	) {
		return findByProperty(ID_NEGERI, idNegeri
		);
	}
	
	public List findByIdMukim(Object idMukim
	) {
		return findByProperty(ID_MUKIM, idMukim
		);
	}
	
	public List findByIdStatus(Object idStatus
	) {
		return findByProperty(ID_STATUS, idStatus
		);
	}
	
	public List findByIdDaerah(Object idDaerah
	) {
		return findByProperty(ID_DAERAH, idDaerah
		);
	}
	
	public List findByPerihalKawasan(Object perihalKawasan
	) {
		return findByProperty(PERIHAL_KAWASAN, perihalKawasan
		);
	}
	
	public List findByIdSemak(Object idSemak
	) {
		return findByProperty(ID_SEMAK, idSemak
		);
	}
	
	public List findByIdSahkan(Object idSahkan
	) {
		return findByProperty(ID_SAHKAN, idSahkan
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
	
	public List findByNoRujukanPtg(Object noRujukanPtg
	) {
		return findByProperty(NO_RUJUKAN_PTG, noRujukanPtg
		);
	}
	
	public List findByNoRujukanPtd(Object noRujukanPtd
	) {
		return findByProperty(NO_RUJUKAN_PTD, noRujukanPtd
		);
	}
	
	public List findByNoRujukanUpt(Object noRujukanUpt
	) {
		return findByProperty(NO_RUJUKAN_UPT, noRujukanUpt
		);
	}
	

	public List findAll() {
		log.debug("finding all Tblpptpermohonan instances");
		try {
			String queryString = "from Tblpptpermohonan";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tblpptpermohonan merge(Tblpptpermohonan detachedInstance) {
        log.debug("merging Tblpptpermohonan instance");
        try {
            Tblpptpermohonan result = (Tblpptpermohonan) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tblpptpermohonan instance) {
        log.debug("attaching dirty Tblpptpermohonan instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tblpptpermohonan instance) {
        log.debug("attaching clean Tblpptpermohonan instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}