package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpfdrujfaharasat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpfdrujfaharasat
 * @author MyEclipse Persistence Tools
 */

public class TblpfdrujfaharasatDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpfdrujfaharasatDAO.class);
	// property constants
	public static final String FAHARASAT = "faharasat";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpfdrujfaharasat transientInstance) {
		log.debug("saving Tblpfdrujfaharasat instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpfdrujfaharasat persistentInstance) {
		log.debug("deleting Tblpfdrujfaharasat instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpfdrujfaharasat findById(java.lang.Long id) {
		log.debug("getting Tblpfdrujfaharasat instance with id: " + id);
		try {
			Tblpfdrujfaharasat instance = (Tblpfdrujfaharasat) getSession()
					.get("ekptg.model.entities.Tblpfdrujfaharasat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpfdrujfaharasat instance) {
		log.debug("finding Tblpfdrujfaharasat instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpfdrujfaharasat").add(
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
		log.debug("finding Tblpfdrujfaharasat instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpfdrujfaharasat as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFaharasat(Object faharasat) {
		return findByProperty(FAHARASAT, faharasat);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpfdrujfaharasat instances");
		try {
			String queryString = "from Tblpfdrujfaharasat";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpfdrujfaharasat merge(Tblpfdrujfaharasat detachedInstance) {
		log.debug("merging Tblpfdrujfaharasat instance");
		try {
			Tblpfdrujfaharasat result = (Tblpfdrujfaharasat) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpfdrujfaharasat instance) {
		log.debug("attaching dirty Tblpfdrujfaharasat instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpfdrujfaharasat instance) {
		log.debug("attaching clean Tblpfdrujfaharasat instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}