package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphplawatantapakpenyewaan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphplawatantapakpenyewaan
 * @author MyEclipse Persistence Tools
 */

public class TblphplawatantapakpenyewaanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphplawatantapakpenyewaanDAO.class);
	// property constants
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_LAWATANTAPAK = "idLawatantapak";

	public void save(Tblphplawatantapakpenyewaan transientInstance) {
		log.debug("saving Tblphplawatantapakpenyewaan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphplawatantapakpenyewaan persistentInstance) {
		log.debug("deleting Tblphplawatantapakpenyewaan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphplawatantapakpenyewaan findById(java.lang.Long id) {
		log
				.debug("getting Tblphplawatantapakpenyewaan instance with id: "
						+ id);
		try {
			Tblphplawatantapakpenyewaan instance = (Tblphplawatantapakpenyewaan) getSession()
					.get("ekptg.model.entities.Tblphplawatantapakpenyewaan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphplawatantapakpenyewaan instance) {
		log.debug("finding Tblphplawatantapakpenyewaan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphplawatantapakpenyewaan").add(
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
		log
				.debug("finding Tblphplawatantapakpenyewaan instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphplawatantapakpenyewaan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdLawatantapak(Object idLawatantapak) {
		return findByProperty(ID_LAWATANTAPAK, idLawatantapak);
	}

	public List findAll() {
		log.debug("finding all Tblphplawatantapakpenyewaan instances");
		try {
			String queryString = "from Tblphplawatantapakpenyewaan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphplawatantapakpenyewaan merge(
			Tblphplawatantapakpenyewaan detachedInstance) {
		log.debug("merging Tblphplawatantapakpenyewaan instance");
		try {
			Tblphplawatantapakpenyewaan result = (Tblphplawatantapakpenyewaan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphplawatantapakpenyewaan instance) {
		log.debug("attaching dirty Tblphplawatantapakpenyewaan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphplawatantapakpenyewaan instance) {
		log.debug("attaching clean Tblphplawatantapakpenyewaan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}