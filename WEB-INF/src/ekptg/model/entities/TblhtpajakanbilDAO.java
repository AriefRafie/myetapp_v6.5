package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpajakanbil entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpajakanbil
 * @author MyEclipse Persistence Tools
 */

public class TblhtpajakanbilDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpajakanbilDAO.class);
	// property constants
	public static final String ID_BAYARAN = "idBayaran";
	public static final String BAYARAN_TERTUNGGAK = "bayaranTertunggak";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String TARIKH_KEMASKINI = "tarikhKemaskini";

	public void save(Tblhtpajakanbil transientInstance) {
		log.debug("saving Tblhtpajakanbil instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpajakanbil persistentInstance) {
		log.debug("deleting Tblhtpajakanbil instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpajakanbil findById(java.lang.Long id) {
		log.debug("getting Tblhtpajakanbil instance with id: " + id);
		try {
			Tblhtpajakanbil instance = (Tblhtpajakanbil) getSession().get(
					"ekptg.model.entities.Tblhtpajakanbil", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpajakanbil instance) {
		log.debug("finding Tblhtpajakanbil instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpajakanbil").add(
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
		log.debug("finding Tblhtpajakanbil instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpajakanbil as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdBayaran(Object idBayaran) {
		return findByProperty(ID_BAYARAN, idBayaran);
	}

	public List findByBayaranTertunggak(Object bayaranTertunggak) {
		return findByProperty(BAYARAN_TERTUNGGAK, bayaranTertunggak);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByTarikhKemaskini(Object tarikhKemaskini) {
		return findByProperty(TARIKH_KEMASKINI, tarikhKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpajakanbil instances");
		try {
			String queryString = "from Tblhtpajakanbil";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpajakanbil merge(Tblhtpajakanbil detachedInstance) {
		log.debug("merging Tblhtpajakanbil instance");
		try {
			Tblhtpajakanbil result = (Tblhtpajakanbil) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpajakanbil instance) {
		log.debug("attaching dirty Tblhtpajakanbil instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpajakanbil instance) {
		log.debug("attaching clean Tblhtpajakanbil instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}