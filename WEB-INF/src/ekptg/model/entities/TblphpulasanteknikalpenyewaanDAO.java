package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpulasanteknikalpenyewaan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphpulasanteknikalpenyewaan
 * @author MyEclipse Persistence Tools
 */

public class TblphpulasanteknikalpenyewaanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpulasanteknikalpenyewaanDAO.class);
	// property constants
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_ULASANTEKNIKAL = "idUlasanteknikal";

	public void save(Tblphpulasanteknikalpenyewaan transientInstance) {
		log.debug("saving Tblphpulasanteknikalpenyewaan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpulasanteknikalpenyewaan persistentInstance) {
		log.debug("deleting Tblphpulasanteknikalpenyewaan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpulasanteknikalpenyewaan findById(java.lang.Long id) {
		log.debug("getting Tblphpulasanteknikalpenyewaan instance with id: "
				+ id);
		try {
			Tblphpulasanteknikalpenyewaan instance = (Tblphpulasanteknikalpenyewaan) getSession()
					.get("ekptg.model.entities.Tblphpulasanteknikalpenyewaan",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpulasanteknikalpenyewaan instance) {
		log.debug("finding Tblphpulasanteknikalpenyewaan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpulasanteknikalpenyewaan").add(
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
				.debug("finding Tblphpulasanteknikalpenyewaan instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpulasanteknikalpenyewaan as model where model."
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

	public List findByIdUlasanteknikal(Object idUlasanteknikal) {
		return findByProperty(ID_ULASANTEKNIKAL, idUlasanteknikal);
	}

	public List findAll() {
		log.debug("finding all Tblphpulasanteknikalpenyewaan instances");
		try {
			String queryString = "from Tblphpulasanteknikalpenyewaan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpulasanteknikalpenyewaan merge(
			Tblphpulasanteknikalpenyewaan detachedInstance) {
		log.debug("merging Tblphpulasanteknikalpenyewaan instance");
		try {
			Tblphpulasanteknikalpenyewaan result = (Tblphpulasanteknikalpenyewaan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpulasanteknikalpenyewaan instance) {
		log.debug("attaching dirty Tblphpulasanteknikalpenyewaan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpulasanteknikalpenyewaan instance) {
		log.debug("attaching clean Tblphpulasanteknikalpenyewaan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}