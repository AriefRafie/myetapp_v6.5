package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptbantahan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptbantahan
 * @author MyEclipse Persistence Tools
 */

public class TblpptbantahanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptbantahanDAO.class);
	// property constants
	public static final String NO_BANTAHAN = "noBantahan";
	public static final String JENIS_PEMBANTAH = "jenisPembantah";
	public static final String NAMA_PEMBANTAH = "namaPembantah";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String FLAG_PENERIMA_PAMPASAN = "flagPenerimaPampasan";
	public static final String FLAG_BAHAGIAN_PAMPASAN = "flagBahagianPampasan";
	public static final String FLAG_UKUR_LUAS = "flagUkurLuas";
	public static final String FLAG_PAMPASAN = "flagPampasan";
	public static final String FLAG_SYARAT = "flagSyarat";
	public static final String ID_PIHAKBERKEPENTINGAN = "idPihakberkepentingan";
	public static final String ID_KEMENTERIAN = "idKementerian";
	public static final String ID_AGENSI = "idAgensi";
	public static final String AMAUN_AWARD = "amaunAward";
	public static final String ID_AWARD = "idAward";
	public static final String ALASAN = "alasan";
	public static final String STATUS_BANTAHAN = "statusBantahan";
	public static final String CARA_BAYAR = "caraBayar";
	public static final String NO_RUJUKAN_BAYARAN = "noRujukanBayaran";
	public static final String TEMPOH_BAYAR = "tempohBayar";
	public static final String UNIT_TEMPOH = "unitTempoh";
	public static final String NO_RESIT = "noResit";
	public static final String FLAG_TERIMADEPOSIT = "flagTerimadeposit";
	public static final String AMAUN_DEPOSIT = "amaunDeposit";
	public static final String NO_RUJUKAN_SURATTARIKBALIK = "noRujukanSurattarikbalik";
	public static final String FLAG_TARIKBALIK = "flagTarikbalik";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptbantahan transientInstance) {
		log.debug("saving Tblpptbantahan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptbantahan persistentInstance) {
		log.debug("deleting Tblpptbantahan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptbantahan findById(java.lang.Long id) {
		log.debug("getting Tblpptbantahan instance with id: " + id);
		try {
			Tblpptbantahan instance = (Tblpptbantahan) getSession().get(
					"ekptg.model.entities.Tblpptbantahan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptbantahan instance) {
		log.debug("finding Tblpptbantahan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptbantahan").add(
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
		log.debug("finding Tblpptbantahan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptbantahan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoBantahan(Object noBantahan) {
		return findByProperty(NO_BANTAHAN, noBantahan);
	}

	public List findByJenisPembantah(Object jenisPembantah) {
		return findByProperty(JENIS_PEMBANTAH, jenisPembantah);
	}

	public List findByNamaPembantah(Object namaPembantah) {
		return findByProperty(NAMA_PEMBANTAH, namaPembantah);
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

	public List findByPoskod(Object poskod) {
		return findByProperty(POSKOD, poskod);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByFlagPenerimaPampasan(Object flagPenerimaPampasan) {
		return findByProperty(FLAG_PENERIMA_PAMPASAN, flagPenerimaPampasan);
	}

	public List findByFlagBahagianPampasan(Object flagBahagianPampasan) {
		return findByProperty(FLAG_BAHAGIAN_PAMPASAN, flagBahagianPampasan);
	}

	public List findByFlagUkurLuas(Object flagUkurLuas) {
		return findByProperty(FLAG_UKUR_LUAS, flagUkurLuas);
	}

	public List findByFlagPampasan(Object flagPampasan) {
		return findByProperty(FLAG_PAMPASAN, flagPampasan);
	}

	public List findByFlagSyarat(Object flagSyarat) {
		return findByProperty(FLAG_SYARAT, flagSyarat);
	}

	public List findByIdPihakberkepentingan(Object idPihakberkepentingan) {
		return findByProperty(ID_PIHAKBERKEPENTINGAN, idPihakberkepentingan);
	}

	public List findByIdKementerian(Object idKementerian) {
		return findByProperty(ID_KEMENTERIAN, idKementerian);
	}

	public List findByIdAgensi(Object idAgensi) {
		return findByProperty(ID_AGENSI, idAgensi);
	}

	public List findByAmaunAward(Object amaunAward) {
		return findByProperty(AMAUN_AWARD, amaunAward);
	}

	public List findByIdAward(Object idAward) {
		return findByProperty(ID_AWARD, idAward);
	}

	public List findByAlasan(Object alasan) {
		return findByProperty(ALASAN, alasan);
	}

	public List findByStatusBantahan(Object statusBantahan) {
		return findByProperty(STATUS_BANTAHAN, statusBantahan);
	}

	public List findByCaraBayar(Object caraBayar) {
		return findByProperty(CARA_BAYAR, caraBayar);
	}

	public List findByNoRujukanBayaran(Object noRujukanBayaran) {
		return findByProperty(NO_RUJUKAN_BAYARAN, noRujukanBayaran);
	}

	public List findByTempohBayar(Object tempohBayar) {
		return findByProperty(TEMPOH_BAYAR, tempohBayar);
	}

	public List findByUnitTempoh(Object unitTempoh) {
		return findByProperty(UNIT_TEMPOH, unitTempoh);
	}

	public List findByNoResit(Object noResit) {
		return findByProperty(NO_RESIT, noResit);
	}

	public List findByFlagTerimadeposit(Object flagTerimadeposit) {
		return findByProperty(FLAG_TERIMADEPOSIT, flagTerimadeposit);
	}

	public List findByAmaunDeposit(Object amaunDeposit) {
		return findByProperty(AMAUN_DEPOSIT, amaunDeposit);
	}

	public List findByNoRujukanSurattarikbalik(Object noRujukanSurattarikbalik) {
		return findByProperty(NO_RUJUKAN_SURATTARIKBALIK,
				noRujukanSurattarikbalik);
	}

	public List findByFlagTarikbalik(Object flagTarikbalik) {
		return findByProperty(FLAG_TARIKBALIK, flagTarikbalik);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findAll() {
		log.debug("finding all Tblpptbantahan instances");
		try {
			String queryString = "from Tblpptbantahan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptbantahan merge(Tblpptbantahan detachedInstance) {
		log.debug("merging Tblpptbantahan instance");
		try {
			Tblpptbantahan result = (Tblpptbantahan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptbantahan instance) {
		log.debug("attaching dirty Tblpptbantahan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptbantahan instance) {
		log.debug("attaching clean Tblpptbantahan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}