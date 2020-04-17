package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpserahbalik entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpserahbalik
 * @author MyEclipse Persistence Tools
 */

public class TblhtpserahbalikDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpserahbalikDAO.class);
	// property constants
	public static final String ID_PENSWASTAAN = "idPenswastaan";
	public static final String ID_HTPHAKMILIK = "idHtphakmilik";
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpserahbalik transientInstance) {
		log.debug("saving Tblhtpserahbalik instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpserahbalik persistentInstance) {
		log.debug("deleting Tblhtpserahbalik instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpserahbalik findById(java.lang.String id) {
		log.debug("getting Tblhtpserahbalik instance with id: " + id);
		try {
			Tblhtpserahbalik instance = (Tblhtpserahbalik) getSession().get(
					"ekptg.model.entities.Tblhtpserahbalik", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpserahbalik instance) {
		log.debug("finding Tblhtpserahbalik instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpserahbalik").add(
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
		log.debug("finding Tblhtpserahbalik instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpserahbalik as model where model."
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

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpserahbalik instances");
		try {
			String queryString = "from Tblhtpserahbalik";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpserahbalik merge(Tblhtpserahbalik detachedInstance) {
		log.debug("merging Tblhtpserahbalik instance");
		try {
			Tblhtpserahbalik result = (Tblhtpserahbalik) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpserahbalik instance) {
		log.debug("attaching dirty Tblhtpserahbalik instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpserahbalik instance) {
		log.debug("attaching clean Tblhtpserahbalik instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}