package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtjadualpindaperenggan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtjadualpindaperenggan
 * @author MyEclipse Persistence Tools
 */

public class TblpdtjadualpindaperengganDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtjadualpindaperengganDAO.class);
	// property constants
	public static final String NOTA_BIRAI = "notaBirai";
	public static final String KANDUNGAN = "kandungan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtjadualpindaperenggan transientInstance) {
		log.debug("saving Tblpdtjadualpindaperenggan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtjadualpindaperenggan persistentInstance) {
		log.debug("deleting Tblpdtjadualpindaperenggan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtjadualpindaperenggan findById(java.lang.Long id) {
		log.debug("getting Tblpdtjadualpindaperenggan instance with id: " + id);
		try {
			Tblpdtjadualpindaperenggan instance = (Tblpdtjadualpindaperenggan) getSession()
					.get("ekptg.model.entities.Tblpdtjadualpindaperenggan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtjadualpindaperenggan instance) {
		log.debug("finding Tblpdtjadualpindaperenggan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtjadualpindaperenggan").add(
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
		log.debug("finding Tblpdtjadualpindaperenggan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtjadualpindaperenggan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNotaBirai(Object notaBirai) {
		return findByProperty(NOTA_BIRAI, notaBirai);
	}

	public List findByKandungan(Object kandungan) {
		return findByProperty(KANDUNGAN, kandungan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtjadualpindaperenggan instances");
		try {
			String queryString = "from Tblpdtjadualpindaperenggan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtjadualpindaperenggan merge(
			Tblpdtjadualpindaperenggan detachedInstance) {
		log.debug("merging Tblpdtjadualpindaperenggan instance");
		try {
			Tblpdtjadualpindaperenggan result = (Tblpdtjadualpindaperenggan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtjadualpindaperenggan instance) {
		log.debug("attaching dirty Tblpdtjadualpindaperenggan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtjadualpindaperenggan instance) {
		log.debug("attaching clean Tblpdtjadualpindaperenggan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}