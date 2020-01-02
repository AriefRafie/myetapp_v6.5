package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpbajet entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpbajet
 * @author MyEclipse Persistence Tools
 */

public class TblhtpbajetDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpbajetDAO.class);
	// property constants
	public static final String JUMLAH = "jumlah";
	public static final String TAHUN = "tahun";
	public static final String ID_NEGERI = "idNegeri";

	public void save(Tblhtpbajet transientInstance) {
		log.debug("saving Tblhtpbajet instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpbajet persistentInstance) {
		log.debug("deleting Tblhtpbajet instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpbajet findById(java.lang.Long id) {
		log.debug("getting Tblhtpbajet instance with id: " + id);
		try {
			Tblhtpbajet instance = (Tblhtpbajet) getSession().get(
					"ekptg.model.entities.Tblhtpbajet", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpbajet instance) {
		log.debug("finding Tblhtpbajet instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpbajet").add(
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
		log.debug("finding Tblhtpbajet instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblhtpbajet as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJumlah(Object jumlah) {
		return findByProperty(JUMLAH, jumlah);
	}

	public List findByTahun(Object tahun) {
		return findByProperty(TAHUN, tahun);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findAll() {
		log.debug("finding all Tblhtpbajet instances");
		try {
			String queryString = "from Tblhtpbajet";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpbajet merge(Tblhtpbajet detachedInstance) {
		log.debug("merging Tblhtpbajet instance");
		try {
			Tblhtpbajet result = (Tblhtpbajet) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpbajet instance) {
		log.debug("attaching dirty Tblhtpbajet instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpbajet instance) {
		log.debug("attaching clean Tblhtpbajet instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}