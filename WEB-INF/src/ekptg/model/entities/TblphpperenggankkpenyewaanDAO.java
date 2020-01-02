package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpperenggankkpenyewaan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphpperenggankkpenyewaan
 * @author MyEclipse Persistence Tools
 */

public class TblphpperenggankkpenyewaanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpperenggankkpenyewaanDAO.class);
	// property constants
	public static final String PERENGGAN = "perenggan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpperenggankkpenyewaan transientInstance) {
		log.debug("saving Tblphpperenggankkpenyewaan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpperenggankkpenyewaan persistentInstance) {
		log.debug("deleting Tblphpperenggankkpenyewaan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpperenggankkpenyewaan findById(java.lang.Long id) {
		log.debug("getting Tblphpperenggankkpenyewaan instance with id: " + id);
		try {
			Tblphpperenggankkpenyewaan instance = (Tblphpperenggankkpenyewaan) getSession()
					.get("ekptg.model.entities.Tblphpperenggankkpenyewaan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpperenggankkpenyewaan instance) {
		log.debug("finding Tblphpperenggankkpenyewaan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpperenggankkpenyewaan").add(
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
		log.debug("finding Tblphpperenggankkpenyewaan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpperenggankkpenyewaan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPerenggan(Object perenggan) {
		return findByProperty(PERENGGAN, perenggan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpperenggankkpenyewaan instances");
		try {
			String queryString = "from Tblphpperenggankkpenyewaan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpperenggankkpenyewaan merge(
			Tblphpperenggankkpenyewaan detachedInstance) {
		log.debug("merging Tblphpperenggankkpenyewaan instance");
		try {
			Tblphpperenggankkpenyewaan result = (Tblphpperenggankkpenyewaan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpperenggankkpenyewaan instance) {
		log.debug("attaching dirty Tblphpperenggankkpenyewaan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpperenggankkpenyewaan instance) {
		log.debug("attaching clean Tblphpperenggankkpenyewaan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}