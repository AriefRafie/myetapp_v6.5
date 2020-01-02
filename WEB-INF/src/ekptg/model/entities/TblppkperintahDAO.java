package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkperintah entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkperintah
 * @author MyEclipse Persistence Tools
 */

public class TblppkperintahDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkperintahDAO.class);
	// property constants
	public static final String ID_NEGERI = "idNegeri";
	public static final String NOTIS_GERAN = "notisGeran";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkperintah transientInstance) {
		log.debug("saving Tblppkperintah instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkperintah persistentInstance) {
		log.debug("deleting Tblppkperintah instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkperintah findById(java.lang.Long id) {
		log.debug("getting Tblppkperintah instance with id: " + id);
		try {
			Tblppkperintah instance = (Tblppkperintah) getSession().get(
					"ekptg.model.entities.Tblppkperintah", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkperintah instance) {
		log.debug("finding Tblppkperintah instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkperintah").add(
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
		log.debug("finding Tblppkperintah instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkperintah as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByNotisGeran(Object notisGeran) {
		return findByProperty(NOTIS_GERAN, notisGeran);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkperintah instances");
		try {
			String queryString = "from Tblppkperintah";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkperintah merge(Tblppkperintah detachedInstance) {
		log.debug("merging Tblppkperintah instance");
		try {
			Tblppkperintah result = (Tblppkperintah) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkperintah instance) {
		log.debug("attaching dirty Tblppkperintah instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkperintah instance) {
		log.debug("attaching clean Tblppkperintah instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}