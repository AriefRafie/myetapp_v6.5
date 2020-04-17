package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptborango entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptborango
 * @author MyEclipse Persistence Tools
 */

public class TblpptborangoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptborangoDAO.class);
	// property constants
	public static final String NO_BORANGO = "noBorango";
	public static final String NAMA_MAHKAMAH = "namaMahkamah";
	public static final String ALAMAT_MAHKAMAH = "alamatMahkamah";
	public static final String ID_BANTAHAN = "idBantahan";
	public static final String RINGKASAN_BANTAHAN = "ringkasanBantahan";
	public static final String PERIHAL_TANAMAN = "perihalTanaman";
	public static final String FLAG_PULANG_DEPOSIT = "flagPulangDeposit";
	public static final String ID_BANK = "idBank";
	public static final String NO_AKAUN = "noAkaun";
	public static final String KEPUTUSAN_MAHKAMAH = "keputusanMahkamah";
	public static final String PERINTAH_MAHKAMAH = "perintahMahkamah";
	public static final String AMAUN_DENDA = "amaunDenda";
	public static final String TARIKH_AKHIR_BAYAR = "tarikhAkhirBayar";
	public static final String TEMPOH_BAYAR_AWARD = "tempohBayarAward";
	public static final String NO_RUJUKAN_TANAH = "noRujukanTanah";
	public static final String CARA_SELESAI = "caraSelesai";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String KOS_PENGAPIT_HAKIM = "kosPengapitHakim";
	public static final String ID_DB = "idDb";

	public void save(Tblpptborango transientInstance) {
		log.debug("saving Tblpptborango instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptborango persistentInstance) {
		log.debug("deleting Tblpptborango instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptborango findById(java.lang.Long id) {
		log.debug("getting Tblpptborango instance with id: " + id);
		try {
			Tblpptborango instance = (Tblpptborango) getSession().get(
					"ekptg.model.entities.Tblpptborango", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptborango instance) {
		log.debug("finding Tblpptborango instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptborango").add(
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
		log.debug("finding Tblpptborango instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptborango as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoBorango(Object noBorango) {
		return findByProperty(NO_BORANGO, noBorango);
	}

	public List findByNamaMahkamah(Object namaMahkamah) {
		return findByProperty(NAMA_MAHKAMAH, namaMahkamah);
	}

	public List findByAlamatMahkamah(Object alamatMahkamah) {
		return findByProperty(ALAMAT_MAHKAMAH, alamatMahkamah);
	}

	public List findByIdBantahan(Object idBantahan) {
		return findByProperty(ID_BANTAHAN, idBantahan);
	}

	public List findByRingkasanBantahan(Object ringkasanBantahan) {
		return findByProperty(RINGKASAN_BANTAHAN, ringkasanBantahan);
	}

	public List findByPerihalTanaman(Object perihalTanaman) {
		return findByProperty(PERIHAL_TANAMAN, perihalTanaman);
	}

	public List findByFlagPulangDeposit(Object flagPulangDeposit) {
		return findByProperty(FLAG_PULANG_DEPOSIT, flagPulangDeposit);
	}

	public List findByIdBank(Object idBank) {
		return findByProperty(ID_BANK, idBank);
	}

	public List findByNoAkaun(Object noAkaun) {
		return findByProperty(NO_AKAUN, noAkaun);
	}

	public List findByKeputusanMahkamah(Object keputusanMahkamah) {
		return findByProperty(KEPUTUSAN_MAHKAMAH, keputusanMahkamah);
	}

	public List findByPerintahMahkamah(Object perintahMahkamah) {
		return findByProperty(PERINTAH_MAHKAMAH, perintahMahkamah);
	}

	public List findByAmaunDenda(Object amaunDenda) {
		return findByProperty(AMAUN_DENDA, amaunDenda);
	}

	public List findByTarikhAkhirBayar(Object tarikhAkhirBayar) {
		return findByProperty(TARIKH_AKHIR_BAYAR, tarikhAkhirBayar);
	}

	public List findByTempohBayarAward(Object tempohBayarAward) {
		return findByProperty(TEMPOH_BAYAR_AWARD, tempohBayarAward);
	}

	public List findByNoRujukanTanah(Object noRujukanTanah) {
		return findByProperty(NO_RUJUKAN_TANAH, noRujukanTanah);
	}

	public List findByCaraSelesai(Object caraSelesai) {
		return findByProperty(CARA_SELESAI, caraSelesai);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByKosPengapitHakim(Object kosPengapitHakim) {
		return findByProperty(KOS_PENGAPIT_HAKIM, kosPengapitHakim);
	}

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findAll() {
		log.debug("finding all Tblpptborango instances");
		try {
			String queryString = "from Tblpptborango";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptborango merge(Tblpptborango detachedInstance) {
		log.debug("merging Tblpptborango instance");
		try {
			Tblpptborango result = (Tblpptborango) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptborango instance) {
		log.debug("attaching dirty Tblpptborango instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptborango instance) {
		log.debug("attaching clean Tblpptborango instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}