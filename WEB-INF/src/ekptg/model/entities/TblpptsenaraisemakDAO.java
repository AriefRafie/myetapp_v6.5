package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Tblpptsenaraisemak entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see ekptg.model.entities.Tblpptsenaraisemak
  * @author MyEclipse Persistence Tools 
 */

public class TblpptsenaraisemakDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(TblpptsenaraisemakDAO.class);
	//property constants
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";
	public static final String SEMAK1 = "semak1";
	public static final String SEMAK2 = "semak2";
	public static final String SEMAK3 = "semak3";
	public static final String SEMAK4 = "semak4";
	public static final String SEMAK5 = "semak5";
	public static final String SEMAK6 = "semak6";
	public static final String SEMAK7 = "semak7";



    
    public void save(Tblpptsenaraisemak transientInstance) {
        log.debug("saving Tblpptsenaraisemak instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tblpptsenaraisemak persistentInstance) {
        log.debug("deleting Tblpptsenaraisemak instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tblpptsenaraisemak findById( java.lang.Long id) {
        log.debug("getting Tblpptsenaraisemak instance with id: " + id);
        try {
            Tblpptsenaraisemak instance = (Tblpptsenaraisemak) getSession()
                    .get("ekptg.model.entities.Tblpptsenaraisemak", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Tblpptsenaraisemak instance) {
        log.debug("finding Tblpptsenaraisemak instance by example");
        try {
            List results = getSession()
                    .createCriteria("ekptg.model.entities.Tblpptsenaraisemak")
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
      log.debug("finding Tblpptsenaraisemak instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tblpptsenaraisemak as model where model." 
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
	
	public List findBySemak1(Object semak1
	) {
		return findByProperty(SEMAK1, semak1
		);
	}
	
	public List findBySemak2(Object semak2
	) {
		return findByProperty(SEMAK2, semak2
		);
	}
	
	public List findBySemak3(Object semak3
	) {
		return findByProperty(SEMAK3, semak3
		);
	}
	
	public List findBySemak4(Object semak4
	) {
		return findByProperty(SEMAK4, semak4
		);
	}
	
	public List findBySemak5(Object semak5
	) {
		return findByProperty(SEMAK5, semak5
		);
	}
	
	public List findBySemak6(Object semak6
	) {
		return findByProperty(SEMAK6, semak6
		);
	}
	
	public List findBySemak7(Object semak7
	) {
		return findByProperty(SEMAK7, semak7
		);
	}
	

	public List findAll() {
		log.debug("finding all Tblpptsenaraisemak instances");
		try {
			String queryString = "from Tblpptsenaraisemak";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tblpptsenaraisemak merge(Tblpptsenaraisemak detachedInstance) {
        log.debug("merging Tblpptsenaraisemak instance");
        try {
            Tblpptsenaraisemak result = (Tblpptsenaraisemak) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tblpptsenaraisemak instance) {
        log.debug("attaching dirty Tblpptsenaraisemak instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tblpptsenaraisemak instance) {
        log.debug("attaching clean Tblpptsenaraisemak instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}