package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujppkjenispb entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujppkjenispb
 * @author MyEclipse Persistence Tools
 */

public class TblrujppkjenispbDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujppkjenispbDAO.class);
	// property constants
	public static final String KOD = "kod";
	public static final String KETERANGAN = "keterangan";
	public static final String JENIS_DAFTAR_PB = "jenisDaftarPb";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujppkjenispb transientInstance) {
		log.debug("saving Tblrujppkjenispb instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujppkjenispb persistentInstance) {
		log.debug("deleting Tblrujppkjenispb instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujppkjenispb findById(java.lang.Long id) {
		log.debug("getting Tblrujppkjenispb instance with id: " + id);
		try {
			Tblrujppkjenispb instance = (Tblrujppkjenispb) getSession().get(
					"ekptg.model.entities.Tblrujppkjenispb", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujppkjenispb instance) {
		log.debug("finding Tblrujppkjenispb instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujppkjenispb").add(
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
		log.debug("finding Tblrujppkjenispb instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujppkjenispb as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKod(Object kod) {
		return findByProperty(KOD, kod);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByJenisDaftarPb(Object jenisDaftarPb) {
		return findByProperty(JENIS_DAFTAR_PB, jenisDaftarPb);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujppkjenispb instances");
		try {
			String queryString = "from Tblrujppkjenispb";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujppkjenispb merge(Tblrujppkjenispb detachedInstance) {
		log.debug("merging Tblrujppkjenispb instance");
		try {
			Tblrujppkjenispb result = (Tblrujppkjenispb) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujppkjenispb instance) {
		log.debug("attaching dirty Tblrujppkjenispb instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujppkjenispb instance) {
		log.debug("attaching clean Tblrujppkjenispb instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}