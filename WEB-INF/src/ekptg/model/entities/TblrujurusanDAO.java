package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujurusan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujurusan
 * @author MyEclipse Persistence Tools
 */

public class TblrujurusanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujurusanDAO.class);
	// property constants
	public static final String KOD_URUSAN = "kodUrusan";
	public static final String NAMA_URUSAN = "namaUrusan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujurusan transientInstance) {
		log.debug("saving Tblrujurusan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujurusan persistentInstance) {
		log.debug("deleting Tblrujurusan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujurusan findById(java.lang.Long id) {
		log.debug("getting Tblrujurusan instance with id: " + id);
		try {
			Tblrujurusan instance = (Tblrujurusan) getSession().get(
					"ekptg.model.entities.Tblrujurusan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujurusan instance) {
		log.debug("finding Tblrujurusan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujurusan").add(
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
		log.debug("finding Tblrujurusan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujurusan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodUrusan(Object kodUrusan) {
		return findByProperty(KOD_URUSAN, kodUrusan);
	}

	public List findByNamaUrusan(Object namaUrusan) {
		return findByProperty(NAMA_URUSAN, namaUrusan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujurusan instances");
		try {
			String queryString = "from Tblrujurusan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujurusan merge(Tblrujurusan detachedInstance) {
		log.debug("merging Tblrujurusan instance");
		try {
			Tblrujurusan result = (Tblrujurusan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujurusan instance) {
		log.debug("attaching dirty Tblrujurusan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujurusan instance) {
		log.debug("attaching clean Tblrujurusan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}