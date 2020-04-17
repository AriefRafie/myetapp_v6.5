package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpbayaran entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpbayaran
 * @author MyEclipse Persistence Tools
 */

public class TblphpbayaranDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblphpbayaranDAO.class);
	// property constants
	public static final String AMAUN_DIBAYAR = "amaunDibayar";
	public static final String ID_CARABAYAR = "idCarabayar";
	public static final String NO_RESIT = "noResit";
	public static final String NO_CEK = "noCek";
	public static final String STATUS_BAYAR = "statusBayar";
	public static final String NAMA_BANK = "namaBank";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpbayaran transientInstance) {
		log.debug("saving Tblphpbayaran instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpbayaran persistentInstance) {
		log.debug("deleting Tblphpbayaran instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpbayaran findById(java.lang.Long id) {
		log.debug("getting Tblphpbayaran instance with id: " + id);
		try {
			Tblphpbayaran instance = (Tblphpbayaran) getSession().get(
					"ekptg.model.entities.Tblphpbayaran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpbayaran instance) {
		log.debug("finding Tblphpbayaran instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpbayaran").add(
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
		log.debug("finding Tblphpbayaran instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpbayaran as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAmaunDibayar(Object amaunDibayar) {
		return findByProperty(AMAUN_DIBAYAR, amaunDibayar);
	}

	public List findByIdCarabayar(Object idCarabayar) {
		return findByProperty(ID_CARABAYAR, idCarabayar);
	}

	public List findByNoResit(Object noResit) {
		return findByProperty(NO_RESIT, noResit);
	}

	public List findByNoCek(Object noCek) {
		return findByProperty(NO_CEK, noCek);
	}

	public List findByStatusBayar(Object statusBayar) {
		return findByProperty(STATUS_BAYAR, statusBayar);
	}

	public List findByNamaBank(Object namaBank) {
		return findByProperty(NAMA_BANK, namaBank);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpbayaran instances");
		try {
			String queryString = "from Tblphpbayaran";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpbayaran merge(Tblphpbayaran detachedInstance) {
		log.debug("merging Tblphpbayaran instance");
		try {
			Tblphpbayaran result = (Tblphpbayaran) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpbayaran instance) {
		log.debug("attaching dirty Tblphpbayaran instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpbayaran instance) {
		log.debug("attaching clean Tblphpbayaran instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}