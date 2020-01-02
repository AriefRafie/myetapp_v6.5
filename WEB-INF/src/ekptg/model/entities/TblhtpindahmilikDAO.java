package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpindahmilik entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpindahmilik
 * @author MyEclipse Persistence Tools
 */

public class TblhtpindahmilikDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpindahmilikDAO.class);
	// property constants
	public static final String ID_PENSWASTAAN = "idPenswastaan";
	public static final String ID_HTPHAKMILIK = "idHtphakmilik";
	public static final String ID_HAKMILIKPEGANGAN = "idHakmilikpegangan";
	public static final String TARIKH_BATALREKOD = "tarikhBatalrekod";
	public static final String ID_MASUK = "idMasuk";

	public void save(Tblhtpindahmilik transientInstance) {
		log.debug("saving Tblhtpindahmilik instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpindahmilik persistentInstance) {
		log.debug("deleting Tblhtpindahmilik instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpindahmilik findById(java.lang.Long id) {
		log.debug("getting Tblhtpindahmilik instance with id: " + id);
		try {
			Tblhtpindahmilik instance = (Tblhtpindahmilik) getSession().get(
					"ekptg.model.entities.Tblhtpindahmilik", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpindahmilik instance) {
		log.debug("finding Tblhtpindahmilik instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpindahmilik").add(
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
		log.debug("finding Tblhtpindahmilik instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpindahmilik as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdPenswastaan(Object idPenswastaan) {
		return findByProperty(ID_PENSWASTAAN, idPenswastaan);
	}

	public List findByIdHtphakmilik(Object idHtphakmilik) {
		return findByProperty(ID_HTPHAKMILIK, idHtphakmilik);
	}

	public List findByIdHakmilikpegangan(Object idHakmilikpegangan) {
		return findByProperty(ID_HAKMILIKPEGANGAN, idHakmilikpegangan);
	}

	public List findByTarikhBatalrekod(Object tarikhBatalrekod) {
		return findByProperty(TARIKH_BATALREKOD, tarikhBatalrekod);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findAll() {
		log.debug("finding all Tblhtpindahmilik instances");
		try {
			String queryString = "from Tblhtpindahmilik";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpindahmilik merge(Tblhtpindahmilik detachedInstance) {
		log.debug("merging Tblhtpindahmilik instance");
		try {
			Tblhtpindahmilik result = (Tblhtpindahmilik) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpindahmilik instance) {
		log.debug("attaching dirty Tblhtpindahmilik instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpindahmilik instance) {
		log.debug("attaching clean Tblhtpindahmilik instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}