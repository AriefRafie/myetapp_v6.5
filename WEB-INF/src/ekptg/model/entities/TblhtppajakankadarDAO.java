package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtppajakankadar entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtppajakankadar
 * @author MyEclipse Persistence Tools
 */

public class TblhtppajakankadarDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtppajakankadarDAO.class);
	// property constants
	public static final String KADAR_BAYAR_PAJAK = "kadarBayarPajak";
	public static final String KADAR_CUKAI = "kadarCukai";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtppajakankadar transientInstance) {
		log.debug("saving Tblhtppajakankadar instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtppajakankadar persistentInstance) {
		log.debug("deleting Tblhtppajakankadar instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtppajakankadar findById(java.lang.Long id) {
		log.debug("getting Tblhtppajakankadar instance with id: " + id);
		try {
			Tblhtppajakankadar instance = (Tblhtppajakankadar) getSession()
					.get("ekptg.model.entities.Tblhtppajakankadar", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtppajakankadar instance) {
		log.debug("finding Tblhtppajakankadar instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtppajakankadar").add(
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
		log.debug("finding Tblhtppajakankadar instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtppajakankadar as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKadarBayarPajak(Object kadarBayarPajak) {
		return findByProperty(KADAR_BAYAR_PAJAK, kadarBayarPajak);
	}

	public List findByKadarCukai(Object kadarCukai) {
		return findByProperty(KADAR_CUKAI, kadarCukai);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtppajakankadar instances");
		try {
			String queryString = "from Tblhtppajakankadar";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtppajakankadar merge(Tblhtppajakankadar detachedInstance) {
		log.debug("merging Tblhtppajakankadar instance");
		try {
			Tblhtppajakankadar result = (Tblhtppajakankadar) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtppajakankadar instance) {
		log.debug("attaching dirty Tblhtppajakankadar instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtppajakankadar instance) {
		log.debug("attaching clean Tblhtppajakankadar instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}