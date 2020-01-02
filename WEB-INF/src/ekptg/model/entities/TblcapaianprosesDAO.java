package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblcapaianproses entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblcapaianproses
 * @author MyEclipse Persistence Tools
 */

public class TblcapaianprosesDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblcapaianprosesDAO.class);
	// property constants
	public static final String KOD_PROSES = "kodProses";
	public static final String KOD_KUMPULAN = "kodKumpulan";

	public void save(Tblcapaianproses transientInstance) {
		log.debug("saving Tblcapaianproses instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblcapaianproses persistentInstance) {
		log.debug("deleting Tblcapaianproses instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblcapaianproses findById(java.lang.Long id) {
		log.debug("getting Tblcapaianproses instance with id: " + id);
		try {
			Tblcapaianproses instance = (Tblcapaianproses) getSession().get(
					"ekptg.model.entities.Tblcapaianproses", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblcapaianproses instance) {
		log.debug("finding Tblcapaianproses instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblcapaianproses").add(
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
		log.debug("finding Tblcapaianproses instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblcapaianproses as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodProses(Object kodProses) {
		return findByProperty(KOD_PROSES, kodProses);
	}

	public List findByKodKumpulan(Object kodKumpulan) {
		return findByProperty(KOD_KUMPULAN, kodKumpulan);
	}

	public List findAll() {
		log.debug("finding all Tblcapaianproses instances");
		try {
			String queryString = "from Tblcapaianproses";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblcapaianproses merge(Tblcapaianproses detachedInstance) {
		log.debug("merging Tblcapaianproses instance");
		try {
			Tblcapaianproses result = (Tblcapaianproses) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblcapaianproses instance) {
		log.debug("attaching dirty Tblcapaianproses instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblcapaianproses instance) {
		log.debug("attaching clean Tblcapaianproses instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}