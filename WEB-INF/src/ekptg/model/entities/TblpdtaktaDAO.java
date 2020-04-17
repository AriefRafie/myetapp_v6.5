package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtakta entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtakta
 * @author MyEclipse Persistence Tools
 */

public class TblpdtaktaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpdtaktaDAO.class);
	// property constants
	public static final String NO_AKTA = "noAkta";
	public static final String NAMA_AKTA = "namaAkta";
	public static final String BIL = "bil";
	public static final String DIR_FAIL = "dirFail";
	public static final String ID_FAIL = "idFail";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String CATATAN = "catatan";

	public void save(Tblpdtakta transientInstance) {
		log.debug("saving Tblpdtakta instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtakta persistentInstance) {
		log.debug("deleting Tblpdtakta instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtakta findById(java.lang.Long id) {
		log.debug("getting Tblpdtakta instance with id: " + id);
		try {
			Tblpdtakta instance = (Tblpdtakta) getSession().get(
					"ekptg.model.entities.Tblpdtakta", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtakta instance) {
		log.debug("finding Tblpdtakta instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtakta").add(
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
		log.debug("finding Tblpdtakta instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblpdtakta as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoAkta(Object noAkta) {
		return findByProperty(NO_AKTA, noAkta);
	}

	public List findByNamaAkta(Object namaAkta) {
		return findByProperty(NAMA_AKTA, namaAkta);
	}

	public List findByBil(Object bil) {
		return findByProperty(BIL, bil);
	}

	public List findByDirFail(Object dirFail) {
		return findByProperty(DIR_FAIL, dirFail);
	}

	public List findByIdFail(Object idFail) {
		return findByProperty(ID_FAIL, idFail);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findAll() {
		log.debug("finding all Tblpdtakta instances");
		try {
			String queryString = "from Tblpdtakta";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtakta merge(Tblpdtakta detachedInstance) {
		log.debug("merging Tblpdtakta instance");
		try {
			Tblpdtakta result = (Tblpdtakta) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtakta instance) {
		log.debug("attaching dirty Tblpdtakta instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtakta instance) {
		log.debug("attaching clean Tblpdtakta instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}