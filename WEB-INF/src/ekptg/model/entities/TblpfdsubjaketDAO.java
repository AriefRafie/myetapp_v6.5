package ekptg.model.entities;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpfdsubjaket entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpfdsubjaket
 * @author MyEclipse Persistence Tools
 */

public class TblpfdsubjaketDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpfdsubjaketDAO.class);
	// property constants
	
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpfdsubjaket transientInstance) {
		log.debug("saving Tblpfdsubjaket instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpfdsubjaket persistentInstance) {
		log.debug("deleting Tblpfdsubjaket instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpfdsubjaket findById(java.lang.Long id) {
		log.debug("getting Tblpfdsubjaket instance with id: " + id);
		try {
			Tblpfdsubjaket instance = (Tblpfdsubjaket) getSession().get(
					"ekptg.model.entities.Tblpfdsubjaket", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpfdsubjaket instance) {
		log.debug("finding Tblpfdsubjaket instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpfdsubjaket").add(
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
		log.debug("finding Tblpfdsubjaket instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpfdsubjaket as model where model."
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

	public List findAll() {
		log.debug("finding all Tblpfdsubjaket instances");
		try {
			String queryString = "from Tblpfdsubjaket";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpfdsubjaket merge(Tblpfdsubjaket detachedInstance) {
		log.debug("merging Tblpfdsubjaket instance");
		try {
			Tblpfdsubjaket result = (Tblpfdsubjaket) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpfdsubjaket instance) {
		log.debug("attaching dirty Tblpfdsubjaket instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpfdsubjaket instance) {
		log.debug("attaching clean Tblpfdsubjaket instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}