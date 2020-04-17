package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpulasankjpkjt entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpulasankjpkjt
 * @author MyEclipse Persistence Tools
 */

public class TblphpulasankjpkjtDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpulasankjpkjtDAO.class);
	// property constants
	public static final String ID_JABATANTEKNIKAL = "idJabatanteknikal";
	public static final String ID_MENTERI = "idMenteri";
	public static final String ID_AGENSI = "idAgensi";
	public static final String NO_RUJUKAN = "noRujukan";
	public static final String ID_DOKUMEN = "idDokumen";
	public static final String ULASAN = "ulasan";
	public static final String JANGKAMASA = "jangkamasa";
	public static final String FLAG_STATUS = "flagStatus";
	public static final String BIL_HANTAR = "bilHantar";
	public static final String NO_VERSI = "noVersi";
	public static final String ID_ULASANRINGKASAN = "idUlasanringkasan";
	public static final String FLAG_TERKINI = "flagTerkini";
	public static final String ULASAN_PINDAAN = "ulasanPindaan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpulasankjpkjt transientInstance) {
		log.debug("saving Tblphpulasankjpkjt instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpulasankjpkjt persistentInstance) {
		log.debug("deleting Tblphpulasankjpkjt instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpulasankjpkjt findById(java.lang.Long id) {
		log.debug("getting Tblphpulasankjpkjt instance with id: " + id);
		try {
			Tblphpulasankjpkjt instance = (Tblphpulasankjpkjt) getSession()
					.get("ekptg.model.entities.Tblphpulasankjpkjt", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpulasankjpkjt instance) {
		log.debug("finding Tblphpulasankjpkjt instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpulasankjpkjt").add(
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
		log.debug("finding Tblphpulasankjpkjt instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpulasankjpkjt as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdJabatanteknikal(Object idJabatanteknikal) {
		return findByProperty(ID_JABATANTEKNIKAL, idJabatanteknikal);
	}

	public List findByIdMenteri(Object idMenteri) {
		return findByProperty(ID_MENTERI, idMenteri);
	}

	public List findByIdAgensi(Object idAgensi) {
		return findByProperty(ID_AGENSI, idAgensi);
	}

	public List findByNoRujukan(Object noRujukan) {
		return findByProperty(NO_RUJUKAN, noRujukan);
	}

	public List findByIdDokumen(Object idDokumen) {
		return findByProperty(ID_DOKUMEN, idDokumen);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByJangkamasa(Object jangkamasa) {
		return findByProperty(JANGKAMASA, jangkamasa);
	}

	public List findByFlagStatus(Object flagStatus) {
		return findByProperty(FLAG_STATUS, flagStatus);
	}

	public List findByBilHantar(Object bilHantar) {
		return findByProperty(BIL_HANTAR, bilHantar);
	}

	public List findByNoVersi(Object noVersi) {
		return findByProperty(NO_VERSI, noVersi);
	}

	public List findByIdUlasanringkasan(Object idUlasanringkasan) {
		return findByProperty(ID_ULASANRINGKASAN, idUlasanringkasan);
	}

	public List findByFlagTerkini(Object flagTerkini) {
		return findByProperty(FLAG_TERKINI, flagTerkini);
	}

	public List findByUlasanPindaan(Object ulasanPindaan) {
		return findByProperty(ULASAN_PINDAAN, ulasanPindaan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpulasankjpkjt instances");
		try {
			String queryString = "from Tblphpulasankjpkjt";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpulasankjpkjt merge(Tblphpulasankjpkjt detachedInstance) {
		log.debug("merging Tblphpulasankjpkjt instance");
		try {
			Tblphpulasankjpkjt result = (Tblphpulasankjpkjt) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpulasankjpkjt instance) {
		log.debug("attaching dirty Tblphpulasankjpkjt instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpulasankjpkjt instance) {
		log.debug("attaching clean Tblphpulasankjpkjt instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}