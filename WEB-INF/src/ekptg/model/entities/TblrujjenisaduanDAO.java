package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujjenisaduan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujjenisaduan
 * @author MyEclipse Persistence Tools
 */

public class TblrujjenisaduanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujjenisaduanDAO.class);
	// property constants
	public static final String KOD_JENIS_ADUAN = "kodJenisAduan";
	public static final String JENIS_ADUAN = "jenisAduan";

	public void save(Tblrujjenisaduan transientInstance) {
		log.debug("saving Tblrujjenisaduan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujjenisaduan persistentInstance) {
		log.debug("deleting Tblrujjenisaduan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujjenisaduan findById(java.lang.Long id) {
		log.debug("getting Tblrujjenisaduan instance with id: " + id);
		try {
			Tblrujjenisaduan instance = (Tblrujjenisaduan) getSession().get(
					"ekptg.model.entities.Tblrujjenisaduan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujjenisaduan instance) {
		log.debug("finding Tblrujjenisaduan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujjenisaduan").add(
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
		log.debug("finding Tblrujjenisaduan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujjenisaduan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodJenisAduan(Object kodJenisAduan) {
		return findByProperty(KOD_JENIS_ADUAN, kodJenisAduan);
	}

	public List findByJenisAduan(Object jenisAduan) {
		return findByProperty(JENIS_ADUAN, jenisAduan);
	}

	public List findAll() {
		log.debug("finding all Tblrujjenisaduan instances");
		try {
			String queryString = "from Tblrujjenisaduan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujjenisaduan merge(Tblrujjenisaduan detachedInstance) {
		log.debug("merging Tblrujjenisaduan instance");
		try {
			Tblrujjenisaduan result = (Tblrujjenisaduan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujjenisaduan instance) {
		log.debug("attaching dirty Tblrujjenisaduan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujjenisaduan instance) {
		log.debug("attaching clean Tblrujjenisaduan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}