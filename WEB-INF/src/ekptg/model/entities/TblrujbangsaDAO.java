package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujbangsa entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujbangsa
 * @author MyEclipse Persistence Tools
 */

public class TblrujbangsaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujbangsaDAO.class);
	// property constants
	public static final String KOD_BANGSA = "kodBangsa";
	public static final String KETERANGAN = "keterangan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujbangsa transientInstance) {
		log.debug("saving Tblrujbangsa instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujbangsa persistentInstance) {
		log.debug("deleting Tblrujbangsa instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujbangsa findById(java.lang.Long id) {
		log.debug("getting Tblrujbangsa instance with id: " + id);
		try {
			Tblrujbangsa instance = (Tblrujbangsa) getSession().get(
					"ekptg.model.entities.Tblrujbangsa", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujbangsa instance) {
		log.debug("finding Tblrujbangsa instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujbangsa").add(
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
		log.debug("finding Tblrujbangsa instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujbangsa as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodBangsa(Object kodBangsa) {
		return findByProperty(KOD_BANGSA, kodBangsa);
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
		log.debug("finding all Tblrujbangsa instances");
		try {
			String queryString = "from Tblrujbangsa";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujbangsa merge(Tblrujbangsa detachedInstance) {
		log.debug("merging Tblrujbangsa instance");
		try {
			Tblrujbangsa result = (Tblrujbangsa) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujbangsa instance) {
		log.debug("attaching dirty Tblrujbangsa instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujbangsa instance) {
		log.debug("attaching clean Tblrujbangsa instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}