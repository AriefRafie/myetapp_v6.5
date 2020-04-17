package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpmaklumatpakarapb entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpmaklumatpakarapb
 * @author MyEclipse Persistence Tools
 */

public class TblphpmaklumatpakarapbDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpmaklumatpakarapbDAO.class);
	// property constants
	public static final String NAMA = "nama";
	public static final String KELAYAKAN = "kelayakan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpmaklumatpakarapb transientInstance) {
		log.debug("saving Tblphpmaklumatpakarapb instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpmaklumatpakarapb persistentInstance) {
		log.debug("deleting Tblphpmaklumatpakarapb instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpmaklumatpakarapb findById(java.lang.Long id) {
		log.debug("getting Tblphpmaklumatpakarapb instance with id: " + id);
		try {
			Tblphpmaklumatpakarapb instance = (Tblphpmaklumatpakarapb) getSession()
					.get("ekptg.model.entities.Tblphpmaklumatpakarapb", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpmaklumatpakarapb instance) {
		log.debug("finding Tblphpmaklumatpakarapb instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpmaklumatpakarapb").add(
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
		log.debug("finding Tblphpmaklumatpakarapb instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpmaklumatpakarapb as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNama(Object nama) {
		return findByProperty(NAMA, nama);
	}

	public List findByKelayakan(Object kelayakan) {
		return findByProperty(KELAYAKAN, kelayakan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpmaklumatpakarapb instances");
		try {
			String queryString = "from Tblphpmaklumatpakarapb";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpmaklumatpakarapb merge(Tblphpmaklumatpakarapb detachedInstance) {
		log.debug("merging Tblphpmaklumatpakarapb instance");
		try {
			Tblphpmaklumatpakarapb result = (Tblphpmaklumatpakarapb) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpmaklumatpakarapb instance) {
		log.debug("attaching dirty Tblphpmaklumatpakarapb instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpmaklumatpakarapb instance) {
		log.debug("attaching clean Tblphpmaklumatpakarapb instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}