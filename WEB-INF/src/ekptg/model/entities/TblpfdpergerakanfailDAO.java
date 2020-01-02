package ekptg.model.entities;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpfdpergerakanfail entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpfdpergerakanfail
 * @author MyEclipse Persistence Tools
 */

public class TblpfdpergerakanfailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpfdpergerakanfailDAO.class);
	// property constants
	
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String CATATAN = "catatan";
	
	

	public void save(Tblpfdpergerakanfail transientInstance) {
		log.debug("saving Tblpfdpergerakanfail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpfdpergerakanfail persistentInstance) {
		log.debug("deleting Tblpfdpergerakanfail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpfdpergerakanfail findById(java.lang.Long id) {
		log.debug("getting Tblpfdpergerakanfail instance with id: " + id);
		try {
			Tblpfdpergerakanfail instance = (Tblpfdpergerakanfail) getSession()
					.get("ekptg.model.entities.Tblpfdpergerakanfail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpfdpergerakanfail instance) {
		log.debug("finding Tblpfdpergerakanfail instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpfdpergerakanfail").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Tblpfdpergerakanfail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpfdpergerakanfail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	
            public List findAll() {
		log.debug("finding all Tblpfdpergerakanfail instances");
		try {
			String queryString = "from Tblpfdpergerakanfail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpfdpergerakanfail merge(Tblpfdpergerakanfail detachedInstance) {
		log.debug("merging Tblpfdpergerakanfail instance");
		try {
			Tblpfdpergerakanfail result = (Tblpfdpergerakanfail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpfdpergerakanfail instance) {
		log.debug("attaching dirty Tblpfdpergerakanfail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpfdpergerakanfail instance) {
		log.debug("attaching clean Tblpfdpergerakanfail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}