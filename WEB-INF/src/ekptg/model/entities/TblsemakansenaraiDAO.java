package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblsemakansenarai entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblsemakansenarai
 * @author MyEclipse Persistence Tools
 */

public class TblsemakansenaraiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblsemakansenaraiDAO.class);
	// property constants
	public static final String ID_SEMAKAN = "idSemakan";
	public static final String ID_URUSAN = "idUrusan";
	public static final String KOD_FORM = "kodForm";
	public static final String ATURAN = "aturan";
	public static final String CATATAN = "catatan";
	public static final String ID_SUBURUSAN = "idSuburusan";
	public static final String ID_KATEGORIPEMOHON = "idKategoripemohon";
	public static final String STATUS_DOKUMEN = "statusDokumen";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblsemakansenarai transientInstance) {
		log.debug("saving Tblsemakansenarai instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblsemakansenarai persistentInstance) {
		log.debug("deleting Tblsemakansenarai instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblsemakansenarai findById(java.lang.Long id) {
		log.debug("getting Tblsemakansenarai instance with id: " + id);
		try {
			Tblsemakansenarai instance = (Tblsemakansenarai) getSession().get(
					"ekptg.model.entities.Tblsemakansenarai", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblsemakansenarai instance) {
		log.debug("finding Tblsemakansenarai instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblsemakansenarai").add(
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
		log.debug("finding Tblsemakansenarai instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblsemakansenarai as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdSemakan(Object idSemakan) {
		return findByProperty(ID_SEMAKAN, idSemakan);
	}

	public List findByIdUrusan(Object idUrusan) {
		return findByProperty(ID_URUSAN, idUrusan);
	}

	public List findByKodForm(Object kodForm) {
		return findByProperty(KOD_FORM, kodForm);
	}

	public List findByAturan(Object aturan) {
		return findByProperty(ATURAN, aturan);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdSuburusan(Object idSuburusan) {
		return findByProperty(ID_SUBURUSAN, idSuburusan);
	}

	public List findByIdKategoripemohon(Object idKategoripemohon) {
		return findByProperty(ID_KATEGORIPEMOHON, idKategoripemohon);
	}

	public List findByStatusDokumen(Object statusDokumen) {
		return findByProperty(STATUS_DOKUMEN, statusDokumen);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblsemakansenarai instances");
		try {
			String queryString = "from Tblsemakansenarai";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblsemakansenarai merge(Tblsemakansenarai detachedInstance) {
		log.debug("merging Tblsemakansenarai instance");
		try {
			Tblsemakansenarai result = (Tblsemakansenarai) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblsemakansenarai instance) {
		log.debug("attaching dirty Tblsemakansenarai instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblsemakansenarai instance) {
		log.debug("attaching clean Tblsemakansenarai instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}