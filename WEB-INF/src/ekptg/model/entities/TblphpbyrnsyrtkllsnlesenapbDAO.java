package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpbyrnsyrtkllsnlesenapb entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphpbyrnsyrtkllsnlesenapb
 * @author MyEclipse Persistence Tools
 */

public class TblphpbyrnsyrtkllsnlesenapbDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpbyrnsyrtkllsnlesenapbDAO.class);
	// property constants
	public static final String ISIPADU = "isipadu";
	public static final String ID_UNITISIPADU = "idUnitisipadu";
	public static final String NO_FAIL_JAS = "noFailJas";
	public static final String TAJUK_SURAT_JAS = "tajukSuratJas";
	public static final String KEPUTUSAN_JAS = "keputusanJas";
	public static final String TEMPOH_KELULUSAN_JAS = "tempohKelulusanJas";
	public static final String TEMPOH_AKHIR_LULUSJAS = "tempohAkhirLulusjas";
	public static final String ID_CAWANGANJAS = "idCawanganjas";
	public static final String NO_FAIL_PHN = "noFailPhn";
	public static final String TAJUK_SURAT_PHN = "tajukSuratPhn";
	public static final String KEPUTUSAN_PHN = "keputusanPhn";
	public static final String TEMPOH_KELULUSAN_PHN = "tempohKelulusanPhn";
	public static final String TEMPOH_AKHIR_LULUSPHN = "tempohAkhirLulusphn";
	public static final String ID_CAWANGANPHN = "idCawanganphn";
	public static final String ULASAN_EIA = "ulasanEia";
	public static final String ULASAN_HIDROGRAFI = "ulasanHidrografi";
	public static final String DEPOSIT_JKPTG = "depositJkptg";
	public static final String DEPOSIT_PERIKANAN = "depositPerikanan";
	public static final String KADAR_FEELESEN = "kadarFeelesen";
	public static final String FEELESEN_BAG_SETIAP = "feelesenBagSetiap";
	public static final String ID_UNITLUASBGSETIAP = "idUnitluasbgsetiap";
	public static final String JUMLAH_FEELESEN = "jumlahFeelesen";
	public static final String KADAR_ROYALTI = "kadarRoyalti";
	public static final String LAMPIRAN = "lampiran";
	public static final String JARAK = "jarak";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpbyrnsyrtkllsnlesenapb transientInstance) {
		log.debug("saving Tblphpbyrnsyrtkllsnlesenapb instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpbyrnsyrtkllsnlesenapb persistentInstance) {
		log.debug("deleting Tblphpbyrnsyrtkllsnlesenapb instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpbyrnsyrtkllsnlesenapb findById(java.lang.Long id) {
		log
				.debug("getting Tblphpbyrnsyrtkllsnlesenapb instance with id: "
						+ id);
		try {
			Tblphpbyrnsyrtkllsnlesenapb instance = (Tblphpbyrnsyrtkllsnlesenapb) getSession()
					.get("ekptg.model.entities.Tblphpbyrnsyrtkllsnlesenapb", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpbyrnsyrtkllsnlesenapb instance) {
		log.debug("finding Tblphpbyrnsyrtkllsnlesenapb instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpbyrnsyrtkllsnlesenapb").add(
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
		log
				.debug("finding Tblphpbyrnsyrtkllsnlesenapb instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpbyrnsyrtkllsnlesenapb as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIsipadu(Object isipadu) {
		return findByProperty(ISIPADU, isipadu);
	}

	public List findByIdUnitisipadu(Object idUnitisipadu) {
		return findByProperty(ID_UNITISIPADU, idUnitisipadu);
	}

	public List findByNoFailJas(Object noFailJas) {
		return findByProperty(NO_FAIL_JAS, noFailJas);
	}

	public List findByTajukSuratJas(Object tajukSuratJas) {
		return findByProperty(TAJUK_SURAT_JAS, tajukSuratJas);
	}

	public List findByKeputusanJas(Object keputusanJas) {
		return findByProperty(KEPUTUSAN_JAS, keputusanJas);
	}

	public List findByTempohKelulusanJas(Object tempohKelulusanJas) {
		return findByProperty(TEMPOH_KELULUSAN_JAS, tempohKelulusanJas);
	}

	public List findByTempohAkhirLulusjas(Object tempohAkhirLulusjas) {
		return findByProperty(TEMPOH_AKHIR_LULUSJAS, tempohAkhirLulusjas);
	}

	public List findByIdCawanganjas(Object idCawanganjas) {
		return findByProperty(ID_CAWANGANJAS, idCawanganjas);
	}

	public List findByNoFailPhn(Object noFailPhn) {
		return findByProperty(NO_FAIL_PHN, noFailPhn);
	}

	public List findByTajukSuratPhn(Object tajukSuratPhn) {
		return findByProperty(TAJUK_SURAT_PHN, tajukSuratPhn);
	}

	public List findByKeputusanPhn(Object keputusanPhn) {
		return findByProperty(KEPUTUSAN_PHN, keputusanPhn);
	}

	public List findByTempohKelulusanPhn(Object tempohKelulusanPhn) {
		return findByProperty(TEMPOH_KELULUSAN_PHN, tempohKelulusanPhn);
	}

	public List findByTempohAkhirLulusphn(Object tempohAkhirLulusphn) {
		return findByProperty(TEMPOH_AKHIR_LULUSPHN, tempohAkhirLulusphn);
	}

	public List findByIdCawanganphn(Object idCawanganphn) {
		return findByProperty(ID_CAWANGANPHN, idCawanganphn);
	}

	public List findByUlasanEia(Object ulasanEia) {
		return findByProperty(ULASAN_EIA, ulasanEia);
	}

	public List findByUlasanHidrografi(Object ulasanHidrografi) {
		return findByProperty(ULASAN_HIDROGRAFI, ulasanHidrografi);
	}

	public List findByDepositJkptg(Object depositJkptg) {
		return findByProperty(DEPOSIT_JKPTG, depositJkptg);
	}

	public List findByDepositPerikanan(Object depositPerikanan) {
		return findByProperty(DEPOSIT_PERIKANAN, depositPerikanan);
	}

	public List findByKadarFeelesen(Object kadarFeelesen) {
		return findByProperty(KADAR_FEELESEN, kadarFeelesen);
	}

	public List findByFeelesenBagSetiap(Object feelesenBagSetiap) {
		return findByProperty(FEELESEN_BAG_SETIAP, feelesenBagSetiap);
	}

	public List findByIdUnitluasbgsetiap(Object idUnitluasbgsetiap) {
		return findByProperty(ID_UNITLUASBGSETIAP, idUnitluasbgsetiap);
	}

	public List findByJumlahFeelesen(Object jumlahFeelesen) {
		return findByProperty(JUMLAH_FEELESEN, jumlahFeelesen);
	}

	public List findByKadarRoyalti(Object kadarRoyalti) {
		return findByProperty(KADAR_ROYALTI, kadarRoyalti);
	}

	public List findByLampiran(Object lampiran) {
		return findByProperty(LAMPIRAN, lampiran);
	}

	public List findByJarak(Object jarak) {
		return findByProperty(JARAK, jarak);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpbyrnsyrtkllsnlesenapb instances");
		try {
			String queryString = "from Tblphpbyrnsyrtkllsnlesenapb";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpbyrnsyrtkllsnlesenapb merge(
			Tblphpbyrnsyrtkllsnlesenapb detachedInstance) {
		log.debug("merging Tblphpbyrnsyrtkllsnlesenapb instance");
		try {
			Tblphpbyrnsyrtkllsnlesenapb result = (Tblphpbyrnsyrtkllsnlesenapb) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpbyrnsyrtkllsnlesenapb instance) {
		log.debug("attaching dirty Tblphpbyrnsyrtkllsnlesenapb instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpbyrnsyrtkllsnlesenapb instance) {
		log.debug("attaching clean Tblphpbyrnsyrtkllsnlesenapb instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}