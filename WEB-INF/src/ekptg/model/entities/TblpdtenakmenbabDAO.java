package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtenakmenbab entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtenakmenbab
 * @author MyEclipse Persistence Tools
 */

public class TblpdtenakmenbabDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpdtenakmenbabDAO.class);
	// property constants
	public static final String NAMA_BAB = "namaBab";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtenakmenbab transientInstance) {
		log.debug("saving Tblpdtenakmenbab instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtenakmenbab persistentInstance) {
		log.debug("deleting Tblpdtenakmenbab instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenbab findById(java.lang.Long id) {
		log.debug("getting Tblpdtenakmenbab instance with id: " + id);
		try {
			Tblpdtenakmenbab instance = (Tblpdtenakmenbab) getSession().get(
					"ekptg.model.entities.Tblpdtenakmenbab", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtenakmenbab instance) {
		log.debug("finding Tblpdtenakmenbab instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtenakmenbab").add(
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
		log.debug("finding Tblpdtenakmenbab instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtenakmenbab as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaBab(Object namaBab) {
		return findByProperty(NAMA_BAB, namaBab);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtenakmenbab instances");
		try {
			String queryString = "from Tblpdtenakmenbab";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenbab merge(Tblpdtenakmenbab detachedInstance) {
		log.debug("merging Tblpdtenakmenbab instance");
		try {
			Tblpdtenakmenbab result = (Tblpdtenakmenbab) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtenakmenbab instance) {
		log.debug("attaching dirty Tblpdtenakmenbab instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtenakmenbab instance) {
		log.debug("attaching clean Tblpdtenakmenbab instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}