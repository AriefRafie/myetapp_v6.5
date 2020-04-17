package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblonlineaduan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblonlineaduan
 * @author MyEclipse Persistence Tools
 */

public class TblonlineaduanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblonlineaduanDAO.class);
	// property constants
	public static final String ADUAN = "aduan";
	public static final String DIR_BUKTI_ADUAN = "dirBuktiAduan";
	public static final String FLAG_STATUS_ADUAN = "flagStatusAduan";
	public static final String TINDAKAN_SUSULAN = "tindakanSusulan";

	public void save(Tblonlineaduan transientInstance) {
		log.debug("saving Tblonlineaduan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblonlineaduan persistentInstance) {
		log.debug("deleting Tblonlineaduan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblonlineaduan findById(java.math.BigDecimal id) {
		log.debug("getting Tblonlineaduan instance with id: " + id);
		try {
			Tblonlineaduan instance = (Tblonlineaduan) getSession().get(
					"ekptg.model.entities.Tblonlineaduan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblonlineaduan instance) {
		log.debug("finding Tblonlineaduan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblonlineaduan").add(
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
		log.debug("finding Tblonlineaduan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblonlineaduan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAduan(Object aduan) {
		return findByProperty(ADUAN, aduan);
	}

	public List findByDirBuktiAduan(Object dirBuktiAduan) {
		return findByProperty(DIR_BUKTI_ADUAN, dirBuktiAduan);
	}

	public List findByFlagStatusAduan(Object flagStatusAduan) {
		return findByProperty(FLAG_STATUS_ADUAN, flagStatusAduan);
	}

	public List findByTindakanSusulan(Object tindakanSusulan) {
		return findByProperty(TINDAKAN_SUSULAN, tindakanSusulan);
	}

	public List findAll() {
		log.debug("finding all Tblonlineaduan instances");
		try {
			String queryString = "from Tblonlineaduan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblonlineaduan merge(Tblonlineaduan detachedInstance) {
		log.debug("merging Tblonlineaduan instance");
		try {
			Tblonlineaduan result = (Tblonlineaduan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblonlineaduan instance) {
		log.debug("attaching dirty Tblonlineaduan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblonlineaduan instance) {
		log.debug("attaching clean Tblonlineaduan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}