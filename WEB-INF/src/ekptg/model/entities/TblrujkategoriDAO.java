package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujkategori entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujkategori
 * @author MyEclipse Persistence Tools
 */

public class TblrujkategoriDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujkategoriDAO.class);
	// property constants
	public static final String KOD_KATEGORI = "kodKategori";
	public static final String KETERANGAN = "keterangan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujkategori transientInstance) {
		log.debug("saving Tblrujkategori instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujkategori persistentInstance) {
		log.debug("deleting Tblrujkategori instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujkategori findById(java.lang.Long id) {
		log.debug("getting Tblrujkategori instance with id: " + id);
		try {
			Tblrujkategori instance = (Tblrujkategori) getSession().get(
					"ekptg.model.entities.Tblrujkategori", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujkategori instance) {
		log.debug("finding Tblrujkategori instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujkategori").add(
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
		log.debug("finding Tblrujkategori instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujkategori as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodKategori(Object kodKategori) {
		return findByProperty(KOD_KATEGORI, kodKategori);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujkategori instances");
		try {
			String queryString = "from Tblrujkategori";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujkategori merge(Tblrujkategori detachedInstance) {
		log.debug("merging Tblrujkategori instance");
		try {
			Tblrujkategori result = (Tblrujkategori) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujkategori instance) {
		log.debug("attaching dirty Tblrujkategori instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujkategori instance) {
		log.debug("attaching clean Tblrujkategori instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}