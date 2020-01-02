package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpperbadanan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpperbadanan
 * @author MyEclipse Persistence Tools
 */

public class TblhtpperbadananDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpperbadananDAO.class);
	// property constants
	public static final String NO_MEMORANDUM = "noMemorandum";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_PERBADANAN = "idPerbadanan";

	public void save(Tblhtpperbadanan transientInstance) {
		log.debug("saving Tblhtpperbadanan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpperbadanan persistentInstance) {
		log.debug("deleting Tblhtpperbadanan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpperbadanan findById(ekptg.model.entities.TblhtpperbadananId id) {
		log.debug("getting Tblhtpperbadanan instance with id: " + id);
		try {
			Tblhtpperbadanan instance = (Tblhtpperbadanan) getSession().get(
					"ekptg.model.entities.Tblhtpperbadanan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpperbadanan instance) {
		log.debug("finding Tblhtpperbadanan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpperbadanan").add(
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
		log.debug("finding Tblhtpperbadanan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpperbadanan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoMemorandum(Object noMemorandum) {
		return findByProperty(NO_MEMORANDUM, noMemorandum);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdPerbadanan(Object idPerbadanan) {
		return findByProperty(ID_PERBADANAN, idPerbadanan);
	}

	public List findAll() {
		log.debug("finding all Tblhtpperbadanan instances");
		try {
			String queryString = "from Tblhtpperbadanan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpperbadanan merge(Tblhtpperbadanan detachedInstance) {
		log.debug("merging Tblhtpperbadanan instance");
		try {
			Tblhtpperbadanan result = (Tblhtpperbadanan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpperbadanan instance) {
		log.debug("attaching dirty Tblhtpperbadanan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpperbadanan instance) {
		log.debug("attaching clean Tblhtpperbadanan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}