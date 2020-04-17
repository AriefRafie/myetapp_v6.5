package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkborangpsimati entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkborangpsimati
 * @author MyEclipse Persistence Tools
 */

public class TblppkborangpsimatiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppkborangpsimatiDAO.class);
	// property constants
	public static final String NAMA_SIMATI = "namaSimati";
	public static final String NAMA_LAIN = "namaLain";
	public static final String NO_KP_BARU = "noKpBaru";
	public static final String NO_KP_LAMA = "noKpLama";
	public static final String JENIS_KP = "jenisKp";
	public static final String N0_KP_LAIN = "n0KpLain";
	public static final String UMUR = "umur";
	public static final String JANTINA = "jantina";
	public static final String NO_SIJIL_MATI = "noSijilMati";
	public static final String TEMPAT_MATI = "tempatMati";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String BANDAR = "bandar";
	public static final String POSKOD = "poskod";
	public static final String WAKTU_KEMATIAN = "waktuKematian";
	public static final String JENIS_WAKTU_KEMATIAN = "jenisWaktuKematian";
	public static final String SEBAB_MATI = "sebabMati";
	public static final String CATATAN = "catatan";
	public static final String ID_NEGERI = "idNegeri";
	public static final String JENIS_AGAMA = "jenisAgama";
	public static final String JENIS_WARGA = "jenisWarga";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkborangpsimati transientInstance) {
		log.debug("saving Tblppkborangpsimati instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkborangpsimati persistentInstance) {
		log.debug("deleting Tblppkborangpsimati instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkborangpsimati findById(java.lang.Long id) {
		log.debug("getting Tblppkborangpsimati instance with id: " + id);
		try {
			Tblppkborangpsimati instance = (Tblppkborangpsimati) getSession()
					.get("ekptg.model.entities.Tblppkborangpsimati", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkborangpsimati instance) {
		log.debug("finding Tblppkborangpsimati instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkborangpsimati").add(
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
		log.debug("finding Tblppkborangpsimati instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkborangpsimati as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaSimati(Object namaSimati) {
		return findByProperty(NAMA_SIMATI, namaSimati);
	}

	public List findByNamaLain(Object namaLain) {
		return findByProperty(NAMA_LAIN, namaLain);
	}

	public List findByNoKpBaru(Object noKpBaru) {
		return findByProperty(NO_KP_BARU, noKpBaru);
	}

	public List findByNoKpLama(Object noKpLama) {
		return findByProperty(NO_KP_LAMA, noKpLama);
	}

	public List findByJenisKp(Object jenisKp) {
		return findByProperty(JENIS_KP, jenisKp);
	}

	public List findByN0KpLain(Object n0KpLain) {
		return findByProperty(N0_KP_LAIN, n0KpLain);
	}

	public List findByUmur(Object umur) {
		return findByProperty(UMUR, umur);
	}

	public List findByJantina(Object jantina) {
		return findByProperty(JANTINA, jantina);
	}

	public List findByNoSijilMati(Object noSijilMati) {
		return findByProperty(NO_SIJIL_MATI, noSijilMati);
	}

	public List findByTempatMati(Object tempatMati) {
		return findByProperty(TEMPAT_MATI, tempatMati);
	}

	public List findByAlamat1(Object alamat1) {
		return findByProperty(ALAMAT1, alamat1);
	}

	public List findByAlamat2(Object alamat2) {
		return findByProperty(ALAMAT2, alamat2);
	}

	public List findByAlamat3(Object alamat3) {
		return findByProperty(ALAMAT3, alamat3);
	}

	public List findByBandar(Object bandar) {
		return findByProperty(BANDAR, bandar);
	}

	public List findByPoskod(Object poskod) {
		return findByProperty(POSKOD, poskod);
	}

	public List findByWaktuKematian(Object waktuKematian) {
		return findByProperty(WAKTU_KEMATIAN, waktuKematian);
	}

	public List findByJenisWaktuKematian(Object jenisWaktuKematian) {
		return findByProperty(JENIS_WAKTU_KEMATIAN, jenisWaktuKematian);
	}

	public List findBySebabMati(Object sebabMati) {
		return findByProperty(SEBAB_MATI, sebabMati);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByJenisAgama(Object jenisAgama) {
		return findByProperty(JENIS_AGAMA, jenisAgama);
	}

	public List findByJenisWarga(Object jenisWarga) {
		return findByProperty(JENIS_WARGA, jenisWarga);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkborangpsimati instances");
		try {
			String queryString = "from Tblppkborangpsimati";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkborangpsimati merge(Tblppkborangpsimati detachedInstance) {
		log.debug("merging Tblppkborangpsimati instance");
		try {
			Tblppkborangpsimati result = (Tblppkborangpsimati) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkborangpsimati instance) {
		log.debug("attaching dirty Tblppkborangpsimati instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkborangpsimati instance) {
		log.debug("attaching clean Tblppkborangpsimati instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}