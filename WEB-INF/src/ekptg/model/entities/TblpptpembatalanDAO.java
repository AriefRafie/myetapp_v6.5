package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Tblpptpembatalan entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see ekptg.model.entities.Tblpptpembatalan
  * @author MyEclipse Persistence Tools 
 */

public class TblpptpembatalanDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(TblpptpembatalanDAO.class);
	//property constants
	public static final String NO_PEMBATALAN = "noPembatalan";
	public static final String JENIS_PEMBATALAN = "jenisPembatalan";
	public static final String SEBAB_PEMBATALAN = "sebabPembatalan";
	public static final String ID_STATUS = "idStatus";
	public static final String NO_RUJUKAN_SURAT = "noRujukanSurat";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";



    
    public void save(Tblpptpembatalan transientInstance) {
        log.debug("saving Tblpptpembatalan instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tblpptpembatalan persistentInstance) {
        log.debug("deleting Tblpptpembatalan instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tblpptpembatalan findById( java.lang.Long id) {
        log.debug("getting Tblpptpembatalan instance with id: " + id);
        try {
            Tblpptpembatalan instance = (Tblpptpembatalan) getSession()
                    .get("ekptg.model.entities.Tblpptpembatalan", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Tblpptpembatalan instance) {
        log.debug("finding Tblpptpembatalan instance by example");
        try {
            List results = getSession()
                    .createCriteria("ekptg.model.entities.Tblpptpembatalan")
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
      log.debug("finding Tblpptpembatalan instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tblpptpembatalan as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNoPembatalan(Object noPembatalan
	) {
		return findByProperty(NO_PEMBATALAN, noPembatalan
		);
	}
	
	public List findByJenisPembatalan(Object jenisPembatalan
	) {
		return findByProperty(JENIS_PEMBATALAN, jenisPembatalan
		);
	}
	
	public List findBySebabPembatalan(Object sebabPembatalan
	) {
		return findByProperty(SEBAB_PEMBATALAN, sebabPembatalan
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
		log.debug("finding all Tblpptpembatalan instances");
		try {
			String queryString = "from Tblpptpembatalan";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tblpptpembatalan merge(Tblpptpembatalan detachedInstance) {
        log.debug("merging Tblpptpembatalan instance");
        try {
            Tblpptpembatalan result = (Tblpptpembatalan) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tblpptpembatalan instance) {
        log.debug("attaching dirty Tblpptpembatalan instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tblpptpembatalan instance) {
        log.debug("attaching clean Tblpptpembatalan instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}