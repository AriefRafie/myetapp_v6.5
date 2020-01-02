package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Tblppthakmilikborangk entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see ekptg.model.entities.Tblppthakmilikborangk
  * @author MyEclipse Persistence Tools 
 */

public class TblppthakmilikborangkDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(TblppthakmilikborangkDAO.class);
	//property constants
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_BORANGK = "idBorangk";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";



    
    public void save(Tblppthakmilikborangk transientInstance) {
        log.debug("saving Tblppthakmilikborangk instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tblppthakmilikborangk persistentInstance) {
        log.debug("deleting Tblppthakmilikborangk instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tblppthakmilikborangk findById( java.lang.Long id) {
        log.debug("getting Tblppthakmilikborangk instance with id: " + id);
        try {
            Tblppthakmilikborangk instance = (Tblppthakmilikborangk) getSession()
                    .get("ekptg.model.entities.Tblppthakmilikborangk", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Tblppthakmilikborangk instance) {
        log.debug("finding Tblppthakmilikborangk instance by example");
        try {
            List results = getSession()
                    .createCriteria("ekptg.model.entities.Tblppthakmilikborangk")
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
      log.debug("finding Tblppthakmilikborangk instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tblppthakmilikborangk as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIdHakmilik(Object idHakmilik
	) {
		return findByProperty(ID_HAKMILIK, idHakmilik
		);
	}
	
	public List findByIdBorangk(Object idBorangk
	) {
		return findByProperty(ID_BORANGK, idBorangk
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
		log.debug("finding all Tblppthakmilikborangk instances");
		try {
			String queryString = "from Tblppthakmilikborangk";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tblppthakmilikborangk merge(Tblppthakmilikborangk detachedInstance) {
        log.debug("merging Tblppthakmilikborangk instance");
        try {
            Tblppthakmilikborangk result = (Tblppthakmilikborangk) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tblppthakmilikborangk instance) {
        log.debug("attaching dirty Tblppthakmilikborangk instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tblppthakmilikborangk instance) {
        log.debug("attaching clean Tblppthakmilikborangk instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}