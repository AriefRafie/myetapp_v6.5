package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpulasanjupem entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpulasanjupem
 * @author MyEclipse Persistence Tools
 */

public class TblphpulasanjupemDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpulasanjupemDAO.class);
	// property constants
	public static final String ID_JABATANTEKNIKAL = "idJabatanteknikal";
	public static final String NO_RUJUKAN = "noRujukan";
	public static final String ID_DOKUMEN = "idDokumen";
	public static final String ULASAN = "ulasan";
	public static final String JANGKAMASA = "jangkamasa";
	public static final String FLAG_STATUS = "flagStatus";
	public static final String BIL_HANTAR = "bilHantar";
	public static final String NO_VERSI = "noVersi";
	public static final String ID_ULASANRINGKASAN = "idUlasanringkasan";
	public static final String FLAG_TERKINI = "flagTerkini";
	public static final String FLAG_BERTINDIH = "flagBertindih";
	public static final String ID_KEDUDUKAN = "idKedudukan";
	public static final String FLAG_APB = "flagApb";
	public static final String ULASAN_PINDAAN = "ulasanPindaan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpulasanjupem transientInstance) {
		log.debug("saving Tblphpulasanjupem instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpulasanjupem persistentInstance) {
		log.debug("deleting Tblphpulasanjupem instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpulasanjupem findById(java.lang.Long id) {
		log.debug("getting Tblphpulasanjupem instance with id: " + id);
		try {
			Tblphpulasanjupem instance = (Tblphpulasanjupem) getSession().get(
					"ekptg.model.entities.Tblphpulasanjupem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpulasanjupem instance) {
		log.debug("finding Tblphpulasanjupem instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpulasanjupem").add(
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
		log.debug("finding Tblphpulasanjupem instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpulasanjupem as model where model."
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

	public List findByFlagBertindih(Object flagBertindih) {
		return findByProperty(FLAG_BERTINDIH, flagBertindih);
	}

	public List findByIdKedudukan(Object idKedudukan) {
		return findByProperty(ID_KEDUDUKAN, idKedudukan);
	}

	public List findByFlagApb(Object flagApb) {
		return findByProperty(FLAG_APB, flagApb);
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
		log.debug("finding all Tblphpulasanjupem instances");
		try {
			String queryString = "from Tblphpulasanjupem";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpulasanjupem merge(Tblphpulasanjupem detachedInstance) {
		log.debug("merging Tblphpulasanjupem instance");
		try {
			Tblphpulasanjupem result = (Tblphpulasanjupem) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpulasanjupem instance) {
		log.debug("attaching dirty Tblphpulasanjupem instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpulasanjupem instance) {
		log.debug("attaching clean Tblphpulasanjupem instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}