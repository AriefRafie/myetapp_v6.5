package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpfdfail entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpfdfail
 * @author MyEclipse Persistence Tools
 */

public class TblpfdfailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpfdfailDAO.class);
	// property constants
	public static final String KOD_JABATAN = "kodJabatan";
	public static final String ID_TARAFKESELAMATAN = "idTarafkeselamatan";
	public static final String ID_SEKSYEN = "idSeksyen";
	public static final String ID_URUSAN = "idUrusan";
	public static final String ID_SUBURUSAN = "idSuburusan";
	public static final String TAJUK_FAIL = "tajukFail";
	public static final String NO_FAIL = "noFail";
	public static final String NO_FAIL_ROOT = "noFailRoot";
	public static final String ID_LOKASIFAIL = "idLokasifail";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_KEMENTERIAN = "idKementerian";
	public static final String ID_FAHARASAT = "idFaharasat";
	public static final String FLAG_FAIL = "flagFail";
	public static final String ID_STATUS = "idStatus";
	public static final String CATATAN = "catatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpfdfail transientInstance) {
		log.debug("saving Tblpfdfail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpfdfail persistentInstance) {
		log.debug("deleting Tblpfdfail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpfdfail findById(java.lang.Long id) {
		log.debug("getting Tblpfdfail instance with id: " + id);
		try {
			Tblpfdfail instance = (Tblpfdfail) getSession().get(
					"ekptg.model.entities.Tblpfdfail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpfdfail instance) {
		log.debug("finding Tblpfdfail instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpfdfail").add(
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
		log.debug("finding Tblpfdfail instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblpfdfail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodJabatan(Object kodJabatan) {
		return findByProperty(KOD_JABATAN, kodJabatan);
	}

	public List findByIdTarafkeselamatan(Object idTarafkeselamatan) {
		return findByProperty(ID_TARAFKESELAMATAN, idTarafkeselamatan);
	}

	public List findByIdSeksyen(Object idSeksyen) {
		return findByProperty(ID_SEKSYEN, idSeksyen);
	}

	public List findByIdUrusan(Object idUrusan) {
		return findByProperty(ID_URUSAN, idUrusan);
	}

	public List findByIdSuburusan(Object idSuburusan) {
		return findByProperty(ID_SUBURUSAN, idSuburusan);
	}

	public List findByTajukFail(Object tajukFail) {
		return findByProperty(TAJUK_FAIL, tajukFail);
	}

	public List findByNoFail(Object noFail) {
		return findByProperty(NO_FAIL, noFail);
	}

	public List findByNoFailRoot(Object noFailRoot) {
		return findByProperty(NO_FAIL_ROOT, noFailRoot);
	}

	public List findByIdLokasifail(Object idLokasifail) {
		return findByProperty(ID_LOKASIFAIL, idLokasifail);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdKementerian(Object idKementerian) {
		return findByProperty(ID_KEMENTERIAN, idKementerian);
	}

	public List findByIdFaharasat(Object idFaharasat) {
		return findByProperty(ID_FAHARASAT, idFaharasat);
	}

	public List findByFlagFail(Object flagFail) {
		return findByProperty(FLAG_FAIL, flagFail);
	}

	public List findByIdStatus(Object idStatus) {
		return findByProperty(ID_STATUS, idStatus);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpfdfail instances");
		try {
			String queryString = "from Tblpfdfail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpfdfail merge(Tblpfdfail detachedInstance) {
		log.debug("merging Tblpfdfail instance");
		try {
			Tblpfdfail result = (Tblpfdfail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpfdfail instance) {
		log.debug("attaching dirty Tblpfdfail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpfdfail instance) {
		log.debug("attaching clean Tblpfdfail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}