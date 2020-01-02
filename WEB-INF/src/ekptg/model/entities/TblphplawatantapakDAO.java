package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphplawatantapak entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphplawatantapak
 * @author MyEclipse Persistence Tools
 */

public class TblphplawatantapakDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphplawatantapakDAO.class);
	// property constants
	public static final String TUJUAN_LAWATAN = "tujuanLawatan";
	public static final String PERKEMBANGAN_TAPAK = "perkembanganTapak";
	public static final String JALAN_HUBUNGAN = "jalanHubungan";
	public static final String KAWASAN_BERHAMPIRAN = "kawasanBerhampiran";
	public static final String JARAK_DRBANDAR = "jarakDrbandar";
	public static final String KEADAAN_RUPABUMI = "keadaanRupabumi";
	public static final String KEADAAN_TANAH = "keadaanTanah";
	public static final String KEMUDAHAN_ASAS = "kemudahanAsas";
	public static final String PEMBANGUNAN_SEKITAR = "pembangunanSekitar";
	public static final String SEMP_UTARA = "sempUtara";
	public static final String SEMP_BARAT = "sempBarat";
	public static final String SEMP_TIMUR = "sempTimur";
	public static final String SEMP_SELATAN = "sempSelatan";
	public static final String ULASAN = "ulasan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphplawatantapak transientInstance) {
		log.debug("saving Tblphplawatantapak instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphplawatantapak persistentInstance) {
		log.debug("deleting Tblphplawatantapak instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphplawatantapak findById(java.lang.Long id) {
		log.debug("getting Tblphplawatantapak instance with id: " + id);
		try {
			Tblphplawatantapak instance = (Tblphplawatantapak) getSession()
					.get("ekptg.model.entities.Tblphplawatantapak", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphplawatantapak instance) {
		log.debug("finding Tblphplawatantapak instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphplawatantapak").add(
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
		log.debug("finding Tblphplawatantapak instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphplawatantapak as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTujuanLawatan(Object tujuanLawatan) {
		return findByProperty(TUJUAN_LAWATAN, tujuanLawatan);
	}

	public List findByPerkembanganTapak(Object perkembanganTapak) {
		return findByProperty(PERKEMBANGAN_TAPAK, perkembanganTapak);
	}

	public List findByJalanHubungan(Object jalanHubungan) {
		return findByProperty(JALAN_HUBUNGAN, jalanHubungan);
	}

	public List findByKawasanBerhampiran(Object kawasanBerhampiran) {
		return findByProperty(KAWASAN_BERHAMPIRAN, kawasanBerhampiran);
	}

	public List findByJarakDrbandar(Object jarakDrbandar) {
		return findByProperty(JARAK_DRBANDAR, jarakDrbandar);
	}

	public List findByKeadaanRupabumi(Object keadaanRupabumi) {
		return findByProperty(KEADAAN_RUPABUMI, keadaanRupabumi);
	}

	public List findByKeadaanTanah(Object keadaanTanah) {
		return findByProperty(KEADAAN_TANAH, keadaanTanah);
	}

	public List findByKemudahanAsas(Object kemudahanAsas) {
		return findByProperty(KEMUDAHAN_ASAS, kemudahanAsas);
	}

	public List findByPembangunanSekitar(Object pembangunanSekitar) {
		return findByProperty(PEMBANGUNAN_SEKITAR, pembangunanSekitar);
	}

	public List findBySempUtara(Object sempUtara) {
		return findByProperty(SEMP_UTARA, sempUtara);
	}

	public List findBySempBarat(Object sempBarat) {
		return findByProperty(SEMP_BARAT, sempBarat);
	}

	public List findBySempTimur(Object sempTimur) {
		return findByProperty(SEMP_TIMUR, sempTimur);
	}

	public List findBySempSelatan(Object sempSelatan) {
		return findByProperty(SEMP_SELATAN, sempSelatan);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphplawatantapak instances");
		try {
			String queryString = "from Tblphplawatantapak";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphplawatantapak merge(Tblphplawatantapak detachedInstance) {
		log.debug("merging Tblphplawatantapak instance");
		try {
			Tblphplawatantapak result = (Tblphplawatantapak) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphplawatantapak instance) {
		log.debug("attaching dirty Tblphplawatantapak instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphplawatantapak instance) {
		log.debug("attaching clean Tblphplawatantapak instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}