package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Tblpptdokumen entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see ekptg.model.entities.Tblpptdokumen
  * @author MyEclipse Persistence Tools 
 */

public class TblpptdokumenDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(TblpptdokumenDAO.class);
	//property constants
	public static final String TAJUK = "tajuk";
	public static final String KETERANGAN = "keterangan";
	public static final String NAMA_FAIL = "namaFail";
	public static final String CONTENT = "content";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String JENIS_MIME = "jenisMime";
	public static final String NO_RUJUKAN = "noRujukan";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_PEMBATALAN = "idPembatalan";
	public static final String ID_PENARIKANBALIK = "idPenarikanbalik";
	public static final String ID_BANTAHAN = "idBantahan";
	public static final String ID_DB = "idDb";



    
    public void save(Tblpptdokumen transientInstance) {
        log.debug("saving Tblpptdokumen instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tblpptdokumen persistentInstance) {
        log.debug("deleting Tblpptdokumen instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tblpptdokumen findById( java.lang.Long id) {
        log.debug("getting Tblpptdokumen instance with id: " + id);
        try {
            Tblpptdokumen instance = (Tblpptdokumen) getSession()
                    .get("ekptg.model.entities.Tblpptdokumen", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Tblpptdokumen instance) {
        log.debug("finding Tblpptdokumen instance by example");
        try {
            List results = getSession()
                    .createCriteria("ekptg.model.entities.Tblpptdokumen")
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
      log.debug("finding Tblpptdokumen instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tblpptdokumen as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTajuk(Object tajuk
	) {
		return findByProperty(TAJUK, tajuk
		);
	}
	
	public List findByKeterangan(Object keterangan
	) {
		return findByProperty(KETERANGAN, keterangan
		);
	}
	
	public List findByNamaFail(Object namaFail
	) {
		return findByProperty(NAMA_FAIL, namaFail
		);
	}
	
	public List findByContent(Object content
	) {
		return findByProperty(CONTENT, content
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
	
	public List findByJenisMime(Object jenisMime
	) {
		return findByProperty(JENIS_MIME, jenisMime
		);
	}
	
	public List findByNoRujukan(Object noRujukan
	) {
		return findByProperty(NO_RUJUKAN, noRujukan
		);
	}
	
	public List findByIdPermohonan(Object idPermohonan
	) {
		return findByProperty(ID_PERMOHONAN, idPermohonan
		);
	}
	
	public List findByIdPembatalan(Object idPembatalan
	) {
		return findByProperty(ID_PEMBATALAN, idPembatalan
		);
	}
	
	public List findByIdPenarikanbalik(Object idPenarikanbalik
	) {
		return findByProperty(ID_PENARIKANBALIK, idPenarikanbalik
		);
	}
	
	public List findByIdBantahan(Object idBantahan
	) {
		return findByProperty(ID_BANTAHAN, idBantahan
		);
	}
	
	public List findByIdDb(Object idDb
	) {
		return findByProperty(ID_DB, idDb
		);
	}
	

	public List findAll() {
		log.debug("finding all Tblpptdokumen instances");
		try {
			String queryString = "from Tblpptdokumen";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tblpptdokumen merge(Tblpptdokumen detachedInstance) {
        log.debug("merging Tblpptdokumen instance");
        try {
            Tblpptdokumen result = (Tblpptdokumen) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tblpptdokumen instance) {
        log.debug("attaching dirty Tblpptdokumen instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tblpptdokumen instance) {
        log.debug("attaching clean Tblpptdokumen instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}