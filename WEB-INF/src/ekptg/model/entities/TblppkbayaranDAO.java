package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkbayaran entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkbayaran
 * @author MyEclipse Persistence Tools
 */

public class TblppkbayaranDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkbayaranDAO.class);
	// property constants
	public static final String ID_JENISBAYAR = "idJenisbayar";
	public static final String NO_RESIT = "noResit";
	public static final String JUMLAH_BAYARAN = "jumlahBayaran";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkbayaran transientInstance) {
		log.debug("saving Tblppkbayaran instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkbayaran persistentInstance) {
		log.debug("deleting Tblppkbayaran instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkbayaran findById(java.lang.Long id) {
		log.debug("getting Tblppkbayaran instance with id: " + id);
		try {
			Tblppkbayaran instance = (Tblppkbayaran) getSession().get(
					"ekptg.model.entities.Tblppkbayaran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkbayaran instance) {
		log.debug("finding Tblppkbayaran instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkbayaran").add(
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
		log.debug("finding Tblppkbayaran instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkbayaran as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdJenisbayar(Object idJenisbayar) {
		return findByProperty(ID_JENISBAYAR, idJenisbayar);
	}

	public List findByNoResit(Object noResit) {
		return findByProperty(NO_RESIT, noResit);
	}

	public List findByJumlahBayaran(Object jumlahBayaran) {
		return findByProperty(JUMLAH_BAYARAN, jumlahBayaran);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkbayaran instances");
		try {
			String queryString = "from Tblppkbayaran";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkbayaran merge(Tblppkbayaran detachedInstance) {
		log.debug("merging Tblppkbayaran instance");
		try {
			Tblppkbayaran result = (Tblppkbayaran) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkbayaran instance) {
		log.debug("attaching dirty Tblppkbayaran instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkbayaran instance) {
		log.debug("attaching clean Tblppkbayaran instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}