package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkkeputusanpermohonan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblppkkeputusanpermohonan
 * @author MyEclipse Persistence Tools
 */

public class TblppkkeputusanpermohonanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppkkeputusanpermohonanDAO.class);
	// property constants
	public static final String CATATAN = "catatan";
	public static final String KEPUTUSAN_PERMOHONAN = "keputusanPermohonan";
	public static final String ID_NEGERIMAHKAMAH = "idNegerimahkamah";
	public static final String ID_DAERAH_MAHKAMAH = "idDaerahMahkamah";
	public static final String ID_MASUK = "idMasuk";

	public void save(Tblppkkeputusanpermohonan transientInstance) {
		log.debug("saving Tblppkkeputusanpermohonan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkkeputusanpermohonan persistentInstance) {
		log.debug("deleting Tblppkkeputusanpermohonan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkkeputusanpermohonan findById(java.lang.Long id) {
		log.debug("getting Tblppkkeputusanpermohonan instance with id: " + id);
		try {
			Tblppkkeputusanpermohonan instance = (Tblppkkeputusanpermohonan) getSession()
					.get("ekptg.model.entities.Tblppkkeputusanpermohonan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkkeputusanpermohonan instance) {
		log.debug("finding Tblppkkeputusanpermohonan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkkeputusanpermohonan").add(
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
		log.debug("finding Tblppkkeputusanpermohonan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkkeputusanpermohonan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByKeputusanPermohonan(Object keputusanPermohonan) {
		return findByProperty(KEPUTUSAN_PERMOHONAN, keputusanPermohonan);
	}

	public List findByIdNegerimahkamah(Object idNegerimahkamah) {
		return findByProperty(ID_NEGERIMAHKAMAH, idNegerimahkamah);
	}

	public List findByIdDaerahMahkamah(Object idDaerahMahkamah) {
		return findByProperty(ID_DAERAH_MAHKAMAH, idDaerahMahkamah);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findAll() {
		log.debug("finding all Tblppkkeputusanpermohonan instances");
		try {
			String queryString = "from Tblppkkeputusanpermohonan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkkeputusanpermohonan merge(
			Tblppkkeputusanpermohonan detachedInstance) {
		log.debug("merging Tblppkkeputusanpermohonan instance");
		try {
			Tblppkkeputusanpermohonan result = (Tblppkkeputusanpermohonan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkkeputusanpermohonan instance) {
		log.debug("attaching dirty Tblppkkeputusanpermohonan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkkeputusanpermohonan instance) {
		log.debug("attaching clean Tblppkkeputusanpermohonan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}