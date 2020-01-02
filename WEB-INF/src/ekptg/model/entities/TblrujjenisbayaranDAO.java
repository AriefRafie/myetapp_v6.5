package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujjenisbayaran entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujjenisbayaran
 * @author MyEclipse Persistence Tools
 */

public class TblrujjenisbayaranDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujjenisbayaranDAO.class);
	// property constants
	public static final String KOD_JENIS_BAYAR = "kodJenisBayar";
	public static final String KETERANGAN = "keterangan";
	public static final String ID_BAYARAN = "idBayaran";
	public static final String AMAUN = "amaun";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujjenisbayaran transientInstance) {
		log.debug("saving Tblrujjenisbayaran instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujjenisbayaran persistentInstance) {
		log.debug("deleting Tblrujjenisbayaran instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujjenisbayaran findById(java.lang.String id) {
		log.debug("getting Tblrujjenisbayaran instance with id: " + id);
		try {
			Tblrujjenisbayaran instance = (Tblrujjenisbayaran) getSession()
					.get("ekptg.model.entities.Tblrujjenisbayaran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujjenisbayaran instance) {
		log.debug("finding Tblrujjenisbayaran instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujjenisbayaran").add(
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
		log.debug("finding Tblrujjenisbayaran instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujjenisbayaran as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodJenisBayar(Object kodJenisBayar) {
		return findByProperty(KOD_JENIS_BAYAR, kodJenisBayar);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByIdBayaran(Object idBayaran) {
		return findByProperty(ID_BAYARAN, idBayaran);
	}

	public List findByAmaun(Object amaun) {
		return findByProperty(AMAUN, amaun);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujjenisbayaran instances");
		try {
			String queryString = "from Tblrujjenisbayaran";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujjenisbayaran merge(Tblrujjenisbayaran detachedInstance) {
		log.debug("merging Tblrujjenisbayaran instance");
		try {
			Tblrujjenisbayaran result = (Tblrujjenisbayaran) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujjenisbayaran instance) {
		log.debug("attaching dirty Tblrujjenisbayaran instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujjenisbayaran instance) {
		log.debug("attaching clean Tblrujjenisbayaran instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}