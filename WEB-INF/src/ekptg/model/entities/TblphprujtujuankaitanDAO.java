package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphprujtujuankaitan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphprujtujuankaitan
 * @author MyEclipse Persistence Tools
 */

public class TblphprujtujuankaitanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblphprujtujuankaitanDAO.class);
	// property constants
	public static final String KOD_TUJUANKAITAN = "kodTujuankaitan";
	public static final String KETERANGAN = "keterangan";

	public void save(Tblphprujtujuankaitan transientInstance) {
		log.debug("saving Tblphprujtujuankaitan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphprujtujuankaitan persistentInstance) {
		log.debug("deleting Tblphprujtujuankaitan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphprujtujuankaitan findById(java.lang.Long id) {
		log.debug("getting Tblphprujtujuankaitan instance with id: " + id);
		try {
			Tblphprujtujuankaitan instance = (Tblphprujtujuankaitan) getSession().get(
					"ekptg.model.entities.Tblphprujtujuankaitan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphprujtujuankaitan instance) {
		log.debug("finding Tblphprujtujuankaitan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphprujtujuankaitan").add(
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
		log.debug("finding Tblphprujtujuankaitan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphprujtujuankaitan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodTujuankaitan(Object kodTujuankaitan) {
		return findByProperty(KOD_TUJUANKAITAN, kodTujuankaitan);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findAll() {
		log.debug("finding all Tblphprujtujuankaitan instances");
		try {
			String queryString = "from Tblphprujtujuankaitan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphprujtujuankaitan merge(Tblphprujtujuankaitan detachedInstance) {
		log.debug("merging Tblphprujtujuankaitan instance");
		try {
			Tblphprujtujuankaitan result = (Tblphprujtujuankaitan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphprujtujuankaitan instance) {
		log.debug("attaching dirty Tblphprujtujuankaitan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphprujtujuankaitan instance) {
		log.debug("attaching clean Tblphprujtujuankaitan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}