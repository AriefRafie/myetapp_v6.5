package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtjadualpinda entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtjadualpinda
 * @author MyEclipse Persistence Tools
 */

public class TblpdtjadualpindaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtjadualpindaDAO.class);
	// property constants
	public static final String NAMA_JADUAL = "namaJadual";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtjadualpinda transientInstance) {
		log.debug("saving Tblpdtjadualpinda instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtjadualpinda persistentInstance) {
		log.debug("deleting Tblpdtjadualpinda instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtjadualpinda findById(java.lang.Long id) {
		log.debug("getting Tblpdtjadualpinda instance with id: " + id);
		try {
			Tblpdtjadualpinda instance = (Tblpdtjadualpinda) getSession().get(
					"ekptg.model.entities.Tblpdtjadualpinda", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtjadualpinda instance) {
		log.debug("finding Tblpdtjadualpinda instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtjadualpinda").add(
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
		log.debug("finding Tblpdtjadualpinda instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtjadualpinda as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaJadual(Object namaJadual) {
		return findByProperty(NAMA_JADUAL, namaJadual);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtjadualpinda instances");
		try {
			String queryString = "from Tblpdtjadualpinda";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtjadualpinda merge(Tblpdtjadualpinda detachedInstance) {
		log.debug("merging Tblpdtjadualpinda instance");
		try {
			Tblpdtjadualpinda result = (Tblpdtjadualpinda) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtjadualpinda instance) {
		log.debug("attaching dirty Tblpdtjadualpinda instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtjadualpinda instance) {
		log.debug("attaching clean Tblpdtjadualpinda instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}