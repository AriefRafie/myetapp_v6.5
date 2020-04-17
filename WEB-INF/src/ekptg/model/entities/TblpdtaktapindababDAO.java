package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtaktapindabab entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtaktapindabab
 * @author MyEclipse Persistence Tools
 */

public class TblpdtaktapindababDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtaktapindababDAO.class);
	// property constants
	public static final String NAMA_BAB = "namaBab";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtaktapindabab transientInstance) {
		log.debug("saving Tblpdtaktapindabab instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtaktapindabab persistentInstance) {
		log.debug("deleting Tblpdtaktapindabab instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtaktapindabab findById(java.lang.Long id) {
		log.debug("getting Tblpdtaktapindabab instance with id: " + id);
		try {
			Tblpdtaktapindabab instance = (Tblpdtaktapindabab) getSession()
					.get("ekptg.model.entities.Tblpdtaktapindabab", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtaktapindabab instance) {
		log.debug("finding Tblpdtaktapindabab instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtaktapindabab").add(
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
		log.debug("finding Tblpdtaktapindabab instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtaktapindabab as model where model."
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
		log.debug("finding all Tblpdtaktapindabab instances");
		try {
			String queryString = "from Tblpdtaktapindabab";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtaktapindabab merge(Tblpdtaktapindabab detachedInstance) {
		log.debug("merging Tblpdtaktapindabab instance");
		try {
			Tblpdtaktapindabab result = (Tblpdtaktapindabab) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtaktapindabab instance) {
		log.debug("attaching dirty Tblpdtaktapindabab instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtaktapindabab instance) {
		log.debug("attaching clean Tblpdtaktapindabab instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}