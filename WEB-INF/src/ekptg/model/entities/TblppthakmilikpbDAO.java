package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Tblppthakmilikpb entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see ekptg.model.entities.Tblppthakmilikpb
  * @author MyEclipse Persistence Tools 
 */

public class TblppthakmilikpbDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(TblppthakmilikpbDAO.class);
	//property constants
	public static final String ID_PIHAKBERKEPENTINGAN = "idPihakberkepentingan";
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";



    
    public void save(Tblppthakmilikpb transientInstance) {
        log.debug("saving Tblppthakmilikpb instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tblppthakmilikpb persistentInstance) {
        log.debug("deleting Tblppthakmilikpb instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tblppthakmilikpb findById( java.lang.Long id) {
        log.debug("getting Tblppthakmilikpb instance with id: " + id);
        try {
            Tblppthakmilikpb instance = (Tblppthakmilikpb) getSession()
                    .get("ekptg.model.entities.Tblppthakmilikpb", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Tblppthakmilikpb instance) {
        log.debug("finding Tblppthakmilikpb instance by example");
        try {
            List results = getSession()
                    .createCriteria("ekptg.model.entities.Tblppthakmilikpb")
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
      log.debug("finding Tblppthakmilikpb instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tblppthakmilikpb as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIdPihakberkepentingan(Object idPihakberkepentingan
	) {
		return findByProperty(ID_PIHAKBERKEPENTINGAN, idPihakberkepentingan
		);
	}
	
	public List findByIdHakmilik(Object idHakmilik
	) {
		return findByProperty(ID_HAKMILIK, idHakmilik
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
		log.debug("finding all Tblppthakmilikpb instances");
		try {
			String queryString = "from Tblppthakmilikpb";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tblppthakmilikpb merge(Tblppthakmilikpb detachedInstance) {
        log.debug("merging Tblppthakmilikpb instance");
        try {
            Tblppthakmilikpb result = (Tblppthakmilikpb) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tblppthakmilikpb instance) {
        log.debug("attaching dirty Tblppthakmilikpb instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tblppthakmilikpb instance) {
        log.debug("attaching clean Tblppthakmilikpb instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}