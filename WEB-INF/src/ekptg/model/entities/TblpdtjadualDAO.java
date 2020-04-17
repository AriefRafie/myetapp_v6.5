package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtjadual entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtjadual
 * @author MyEclipse Persistence Tools
 */

public class TblpdtjadualDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpdtjadualDAO.class);
	// property constants
	public static final String NAMA_JADUAL = "namaJadual";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtjadual transientInstance) {
		log.debug("saving Tblpdtjadual instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtjadual persistentInstance) {
		log.debug("deleting Tblpdtjadual instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtjadual findById(java.lang.Long id) {
		log.debug("getting Tblpdtjadual instance with id: " + id);
		try {
			Tblpdtjadual instance = (Tblpdtjadual) getSession().get(
					"ekptg.model.entities.Tblpdtjadual", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtjadual instance) {
		log.debug("finding Tblpdtjadual instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtjadual").add(
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
		log.debug("finding Tblpdtjadual instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtjadual as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaJadual(Object namaJadual) {
		return findByProperty(NAMA_JADUAL, namaJadual);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtjadual instances");
		try {
			String queryString = "from Tblpdtjadual";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtjadual merge(Tblpdtjadual detachedInstance) {
		log.debug("merging Tblpdtjadual instance");
		try {
			Tblpdtjadual result = (Tblpdtjadual) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtjadual instance) {
		log.debug("attaching dirty Tblpdtjadual instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtjadual instance) {
		log.debug("attaching clean Tblpdtjadual instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}