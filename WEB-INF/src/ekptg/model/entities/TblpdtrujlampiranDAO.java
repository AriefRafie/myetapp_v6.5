package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtrujlampiran entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtrujlampiran
 * @author MyEclipse Persistence Tools
 */

public class TblpdtrujlampiranDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtrujlampiranDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String NAMA_FAIL = "namaFail";
	public static final String JENIS_MIME = "jenisMime";

	public void save(Tblpdtrujlampiran transientInstance) {
		log.debug("saving Tblpdtrujlampiran instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtrujlampiran persistentInstance) {
		log.debug("deleting Tblpdtrujlampiran instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtrujlampiran findById(java.math.BigDecimal id) {
		log.debug("getting Tblpdtrujlampiran instance with id: " + id);
		try {
			Tblpdtrujlampiran instance = (Tblpdtrujlampiran) getSession().get(
					"ekptg.model.entities.Tblpdtrujlampiran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtrujlampiran instance) {
		log.debug("finding Tblpdtrujlampiran instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtrujlampiran").add(
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
		log.debug("finding Tblpdtrujlampiran instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtrujlampiran as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByNamaFail(Object namaFail) {
		return findByProperty(NAMA_FAIL, namaFail);
	}

	public List findByJenisMime(Object jenisMime) {
		return findByProperty(JENIS_MIME, jenisMime);
	}

	public List findAll() {
		log.debug("finding all Tblpdtrujlampiran instances");
		try {
			String queryString = "from Tblpdtrujlampiran";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtrujlampiran merge(Tblpdtrujlampiran detachedInstance) {
		log.debug("merging Tblpdtrujlampiran instance");
		try {
			Tblpdtrujlampiran result = (Tblpdtrujlampiran) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtrujlampiran instance) {
		log.debug("attaching dirty Tblpdtrujlampiran instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtrujlampiran instance) {
		log.debug("attaching clean Tblpdtrujlampiran instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}