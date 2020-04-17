package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphppegawailaporantanah entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphppegawailaporantanah
 * @author MyEclipse Persistence Tools
 */

public class TblphppegawailaporantanahDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphppegawailaporantanahDAO.class);
	// property constants
	public static final String NAMA_PEGAWAI = "namaPegawai";
	public static final String JAWATAN = "jawatan";
	public static final String ID_NEGERIPEGAWAI = "idNegeripegawai";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphppegawailaporantanah transientInstance) {
		log.debug("saving Tblphppegawailaporantanah instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphppegawailaporantanah persistentInstance) {
		log.debug("deleting Tblphppegawailaporantanah instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphppegawailaporantanah findById(java.lang.Long id) {
		log.debug("getting Tblphppegawailaporantanah instance with id: " + id);
		try {
			Tblphppegawailaporantanah instance = (Tblphppegawailaporantanah) getSession()
					.get("ekptg.model.entities.Tblphppegawailaporantanah", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphppegawailaporantanah instance) {
		log.debug("finding Tblphppegawailaporantanah instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphppegawailaporantanah").add(
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
		log.debug("finding Tblphppegawailaporantanah instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphppegawailaporantanah as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaPegawai(Object namaPegawai) {
		return findByProperty(NAMA_PEGAWAI, namaPegawai);
	}

	public List findByJawatan(Object jawatan) {
		return findByProperty(JAWATAN, jawatan);
	}

	public List findByIdNegeripegawai(Object idNegeripegawai) {
		return findByProperty(ID_NEGERIPEGAWAI, idNegeripegawai);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphppegawailaporantanah instances");
		try {
			String queryString = "from Tblphppegawailaporantanah";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphppegawailaporantanah merge(
			Tblphppegawailaporantanah detachedInstance) {
		log.debug("merging Tblphppegawailaporantanah instance");
		try {
			Tblphppegawailaporantanah result = (Tblphppegawailaporantanah) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphppegawailaporantanah instance) {
		log.debug("attaching dirty Tblphppegawailaporantanah instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphppegawailaporantanah instance) {
		log.debug("attaching clean Tblphppegawailaporantanah instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}