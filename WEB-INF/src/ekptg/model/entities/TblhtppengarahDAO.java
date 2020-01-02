package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtppengarah entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtppengarah
 * @author MyEclipse Persistence Tools
 */

public class TblhtppengarahDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtppengarahDAO.class);
	// property constants
	public static final String ID_RUJJENISOPB = "idRujjenisopb";
	public static final String NO_OPB = "noOpb";
	public static final String NAMA = "nama";
	public static final String ID_WARGANEGARA = "idWarganegara";
	public static final String ID_URUSAN = "idUrusan";
	public static final String ID_MASUK = "idMasuk";

	public void save(Tblhtppengarah transientInstance) {
		log.debug("saving Tblhtppengarah instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtppengarah persistentInstance) {
		log.debug("deleting Tblhtppengarah instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtppengarah findById(java.lang.Long id) {
		log.debug("getting Tblhtppengarah instance with id: " + id);
		try {
			Tblhtppengarah instance = (Tblhtppengarah) getSession().get(
					"ekptg.model.entities.Tblhtppengarah", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtppengarah instance) {
		log.debug("finding Tblhtppengarah instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtppengarah").add(
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
		log.debug("finding Tblhtppengarah instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtppengarah as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdRujjenisopb(Object idRujjenisopb) {
		return findByProperty(ID_RUJJENISOPB, idRujjenisopb);
	}

	public List findByNoOpb(Object noOpb) {
		return findByProperty(NO_OPB, noOpb);
	}

	public List findByNama(Object nama) {
		return findByProperty(NAMA, nama);
	}

	public List findByIdWarganegara(Object idWarganegara) {
		return findByProperty(ID_WARGANEGARA, idWarganegara);
	}

	public List findByIdUrusan(Object idUrusan) {
		return findByProperty(ID_URUSAN, idUrusan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findAll() {
		log.debug("finding all Tblhtppengarah instances");
		try {
			String queryString = "from Tblhtppengarah";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtppengarah merge(Tblhtppengarah detachedInstance) {
		log.debug("merging Tblhtppengarah instance");
		try {
			Tblhtppengarah result = (Tblhtppengarah) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtppengarah instance) {
		log.debug("attaching dirty Tblhtppengarah instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtppengarah instance) {
		log.debug("attaching clean Tblhtppengarah instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}