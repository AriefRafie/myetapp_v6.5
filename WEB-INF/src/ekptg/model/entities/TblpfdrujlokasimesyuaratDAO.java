package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpfdrujlokasimesyuarat entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpfdrujlokasimesyuarat
 * @author MyEclipse Persistence Tools
 */

public class TblpfdrujlokasimesyuaratDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpfdrujlokasimesyuaratDAO.class);
	// property constants
	public static final String LOKASI = "lokasi";

	public void save(Tblpfdrujlokasimesyuarat transientInstance) {
		log.debug("saving Tblpfdrujlokasimesyuarat instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpfdrujlokasimesyuarat persistentInstance) {
		log.debug("deleting Tblpfdrujlokasimesyuarat instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpfdrujlokasimesyuarat findById(java.math.BigDecimal id) {
		log.debug("getting Tblpfdrujlokasimesyuarat instance with id: " + id);
		try {
			Tblpfdrujlokasimesyuarat instance = (Tblpfdrujlokasimesyuarat) getSession()
					.get("ekptg.model.entities.Tblpfdrujlokasimesyuarat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpfdrujlokasimesyuarat instance) {
		log.debug("finding Tblpfdrujlokasimesyuarat instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpfdrujlokasimesyuarat").add(
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
		log.debug("finding Tblpfdrujlokasimesyuarat instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpfdrujlokasimesyuarat as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLokasi(Object lokasi) {
		return findByProperty(LOKASI, lokasi);
	}

	public List findAll() {
		log.debug("finding all Tblpfdrujlokasimesyuarat instances");
		try {
			String queryString = "from Tblpfdrujlokasimesyuarat";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpfdrujlokasimesyuarat merge(
			Tblpfdrujlokasimesyuarat detachedInstance) {
		log.debug("merging Tblpfdrujlokasimesyuarat instance");
		try {
			Tblpfdrujlokasimesyuarat result = (Tblpfdrujlokasimesyuarat) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpfdrujlokasimesyuarat instance) {
		log.debug("attaching dirty Tblpfdrujlokasimesyuarat instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpfdrujlokasimesyuarat instance) {
		log.debug("attaching clean Tblpfdrujlokasimesyuarat instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}