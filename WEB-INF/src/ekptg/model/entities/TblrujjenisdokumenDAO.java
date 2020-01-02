package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujjenisdokumen entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujjenisdokumen
 * @author MyEclipse Persistence Tools
 */

public class TblrujjenisdokumenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujjenisdokumenDAO.class);
	// property constants
	public static final String KOD = "kod";
	public static final String NAMA = "nama";
	public static final String KETERANGAN = "keterangan";
	public static final String ID_LAPORAN = "idLaporan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujjenisdokumen transientInstance) {
		log.debug("saving Tblrujjenisdokumen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujjenisdokumen persistentInstance) {
		log.debug("deleting Tblrujjenisdokumen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujjenisdokumen findById(java.lang.Long id) {
		log.debug("getting Tblrujjenisdokumen instance with id: " + id);
		try {
			Tblrujjenisdokumen instance = (Tblrujjenisdokumen) getSession()
					.get("ekptg.model.entities.Tblrujjenisdokumen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujjenisdokumen instance) {
		log.debug("finding Tblrujjenisdokumen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujjenisdokumen").add(
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
		log.debug("finding Tblrujjenisdokumen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujjenisdokumen as model where model."
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

	public List findByNama(Object nama) {
		return findByProperty(NAMA, nama);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByIdLaporan(Object idLaporan) {
		return findByProperty(ID_LAPORAN, idLaporan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujjenisdokumen instances");
		try {
			String queryString = "from Tblrujjenisdokumen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujjenisdokumen merge(Tblrujjenisdokumen detachedInstance) {
		log.debug("merging Tblrujjenisdokumen instance");
		try {
			Tblrujjenisdokumen result = (Tblrujjenisdokumen) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujjenisdokumen instance) {
		log.debug("attaching dirty Tblrujjenisdokumen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujjenisdokumen instance) {
		log.debug("attaching clean Tblrujjenisdokumen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}