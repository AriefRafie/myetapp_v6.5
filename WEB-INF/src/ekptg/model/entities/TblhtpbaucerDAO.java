package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpbaucer entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpbaucer
 * @author MyEclipse Persistence Tools
 */

public class TblhtpbaucerDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpbaucerDAO.class);
	// property constants
	public static final String TAHUN = "tahun";
	public static final String NO_BAUCER = "noBaucer";
	public static final String AMAUN_BAUCER = "amaunBaucer";
	public static final String ID_BANK = "idBank";
	public static final String NO_RESIT = "noResit";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpbaucer transientInstance) {
		log.debug("saving Tblhtpbaucer instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpbaucer persistentInstance) {
		log.debug("deleting Tblhtpbaucer instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpbaucer findById(java.lang.Long id) {
		log.debug("getting Tblhtpbaucer instance with id: " + id);
		try {
			Tblhtpbaucer instance = (Tblhtpbaucer) getSession().get(
					"ekptg.model.entities.Tblhtpbaucer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpbaucer instance) {
		log.debug("finding Tblhtpbaucer instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpbaucer").add(
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
		log.debug("finding Tblhtpbaucer instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpbaucer as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTahun(Object tahun) {
		return findByProperty(TAHUN, tahun);
	}

	public List findByNoBaucer(Object noBaucer) {
		return findByProperty(NO_BAUCER, noBaucer);
	}

	public List findByAmaunBaucer(Object amaunBaucer) {
		return findByProperty(AMAUN_BAUCER, amaunBaucer);
	}

	public List findByIdBank(Object idBank) {
		return findByProperty(ID_BANK, idBank);
	}

	public List findByNoResit(Object noResit) {
		return findByProperty(NO_RESIT, noResit);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpbaucer instances");
		try {
			String queryString = "from Tblhtpbaucer";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpbaucer merge(Tblhtpbaucer detachedInstance) {
		log.debug("merging Tblhtpbaucer instance");
		try {
			Tblhtpbaucer result = (Tblhtpbaucer) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpbaucer instance) {
		log.debug("attaching dirty Tblhtpbaucer instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpbaucer instance) {
		log.debug("attaching clean Tblhtpbaucer instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}