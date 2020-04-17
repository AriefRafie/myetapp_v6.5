package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpermohonan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpermohonan
 * @author MyEclipse Persistence Tools
 */

public class TblpermohonanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpermohonanDAO.class);
	// property constants
	public static final String ID_FAIL = "idFail";
	public static final String ID_JKPTG = "idJkptg";
	public static final String NO_PERMOHONAN = "noPermohonan";
	public static final String NO_PERSERAHAN = "noPerserahan";
	public static final String TUJUAN = "tujuan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpermohonan transientInstance) {
		log.debug("saving Tblpermohonan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpermohonan persistentInstance) {
		log.debug("deleting Tblpermohonan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpermohonan findById(java.lang.Long id) {
		log.debug("getting Tblpermohonan instance with id: " + id);
		try {
			Tblpermohonan instance = (Tblpermohonan) getSession().get(
					"ekptg.model.entities.Tblpermohonan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpermohonan instance) {
		log.debug("finding Tblpermohonan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpermohonan").add(
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
		log.debug("finding Tblpermohonan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpermohonan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdFail(Object idFail) {
		return findByProperty(ID_FAIL, idFail);
	}

	public List findByIdJkptg(Object idJkptg) {
		return findByProperty(ID_JKPTG, idJkptg);
	}

	public List findByNoPermohonan(Object noPermohonan) {
		return findByProperty(NO_PERMOHONAN, noPermohonan);
	}

	public List findByNoPerserahan(Object noPerserahan) {
		return findByProperty(NO_PERSERAHAN, noPerserahan);
	}

	public List findByTujuan(Object tujuan) {
		return findByProperty(TUJUAN, tujuan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpermohonan instances");
		try {
			String queryString = "from Tblpermohonan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpermohonan merge(Tblpermohonan detachedInstance) {
		log.debug("merging Tblpermohonan instance");
		try {
			Tblpermohonan result = (Tblpermohonan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpermohonan instance) {
		log.debug("attaching dirty Tblpermohonan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpermohonan instance) {
		log.debug("attaching clean Tblpermohonan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}