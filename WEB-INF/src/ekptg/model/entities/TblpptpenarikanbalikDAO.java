package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Tblpptpenarikanbalik entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see ekptg.model.entities.Tblpptpenarikanbalik
  * @author MyEclipse Persistence Tools 
 */

public class TblpptpenarikanbalikDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(TblpptpenarikanbalikDAO.class);
	//property constants
	public static final String NO_PENARIKANBALIK = "noPenarikanbalik";
	public static final String JENIS_PENARIKANBALIK = "jenisPenarikanbalik";
	public static final String SEBAB_PENARIKANBALIK = "sebabPenarikanbalik";
	public static final String ID_STATUS = "idStatus";
	public static final String NO_RUJUKAN_SURAT = "noRujukanSurat";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";



    
    public void save(Tblpptpenarikanbalik transientInstance) {
        log.debug("saving Tblpptpenarikanbalik instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tblpptpenarikanbalik persistentInstance) {
        log.debug("deleting Tblpptpenarikanbalik instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tblpptpenarikanbalik findById( java.lang.Long id) {
        log.debug("getting Tblpptpenarikanbalik instance with id: " + id);
        try {
            Tblpptpenarikanbalik instance = (Tblpptpenarikanbalik) getSession()
                    .get("ekptg.model.entities.Tblpptpenarikanbalik", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Tblpptpenarikanbalik instance) {
        log.debug("finding Tblpptpenarikanbalik instance by example");
        try {
            List results = getSession()
                    .createCriteria("ekptg.model.entities.Tblpptpenarikanbalik")
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
      log.debug("finding Tblpptpenarikanbalik instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tblpptpenarikanbalik as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNoPenarikanbalik(Object noPenarikanbalik
	) {
		return findByProperty(NO_PENARIKANBALIK, noPenarikanbalik
		);
	}
	
	public List findByJenisPenarikanbalik(Object jenisPenarikanbalik
	) {
		return findByProperty(JENIS_PENARIKANBALIK, jenisPenarikanbalik
		);
	}
	
	public List findBySebabPenarikanbalik(Object sebabPenarikanbalik
	) {
		return findByProperty(SEBAB_PENARIKANBALIK, sebabPenarikanbalik
		);
	}
	
	public List findByIdStatus(Object idStatus
	) {
		return findByProperty(ID_STATUS, idStatus
		);
	}
	
	public List findByNoRujukanSurat(Object noRujukanSurat
	) {
		return findByProperty(NO_RUJUKAN_SURAT, noRujukanSurat
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
		log.debug("finding all Tblpptpenarikanbalik instances");
		try {
			String queryString = "from Tblpptpenarikanbalik";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tblpptpenarikanbalik merge(Tblpptpenarikanbalik detachedInstance) {
        log.debug("merging Tblpptpenarikanbalik instance");
        try {
            Tblpptpenarikanbalik result = (Tblpptpenarikanbalik) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tblpptpenarikanbalik instance) {
        log.debug("attaching dirty Tblpptpenarikanbalik instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tblpptpenarikanbalik instance) {
        log.debug("attaching clean Tblpptpenarikanbalik instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}