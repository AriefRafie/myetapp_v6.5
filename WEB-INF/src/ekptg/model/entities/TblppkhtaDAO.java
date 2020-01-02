package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkhta entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkhta
 * @author MyEclipse Persistence Tools
 */

public class TblppkhtaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkhtaDAO.class);
	// property constants
	public static final String NO_HAKMILIK = "noHakmilik";
	public static final String NO_PT = "noPt";
	public static final String NILAI_HTA_TARIKHMOHON = "nilaiHtaTarikhmohon";
	public static final String NILAI_HTA_TARIKHMATI = "nilaiHtaTarikhmati";
	public static final String ID_KATEGORI = "idKategori";
	public static final String ID_JENISHM = "idJenishm";
	public static final String ID_JENISPB = "idJenispb";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_MUKIM = "idMukim";
	public static final String ID_LUAS = "idLuas";
	public static final String LUAS = "luas";
	public static final String LUAS_HMP = "luasHmp";
	public static final String NO_CAGARAN = "noCagaran";
	public static final String NO_PAJAKAN = "noPajakan";
	public static final String JENIS_TNH = "jenisTnh";
	public static final String ALAMAT_HTA1 = "alamatHta1";
	public static final String ALAMAT_HTA2 = "alamatHta2";
	public static final String ALAMAT_HTA3 = "alamatHta3";
	public static final String BANDAR_HTA = "bandarHta";
	public static final String POSKOD_HTA = "poskodHta";
	public static final String NAMA_PEMAJU = "namaPemaju";
	public static final String ALAMAT_PEMAJU1 = "alamatPemaju1";
	public static final String ALAMAT_PEMAJU2 = "alamatPemaju2";
	public static final String ALAMAT_PEMAJU3 = "alamatPemaju3";
	public static final String BANDAR_PEMAJU = "bandarPemaju";
	public static final String POSKOD_PEMAJU = "poskodPemaju";
	public static final String ID_NEGERIPEMAJU = "idNegeripemaju";
	public static final String CATATAN = "catatan";
	public static final String BA_SIMATI = "baSimati";
	public static final String BB_SIMATI = "bbSimati";
	public static final String NO_BANGUNAN = "noBangunan";
	public static final String NO_TINGKAT = "noTingkat";
	public static final String NO_PETAK = "noPetak";
	public static final String NO_STRATA = "noStrata";
	public static final String MAKLUMAT_TANAH = "maklumatTanah";
	public static final String NO_PERJANJIAN = "noPerjanjian";
	public static final String JENIS_HTA = "jenisHta";
	public static final String STATUS_PEMILIKAN = "statusPemilikan";
	public static final String TANGGUNGAN = "tanggungan";
	public static final String NO_PERSERAHAN = "noPerserahan";
	public static final String NAMA_RANCANGAN = "namaRancangan";
	public static final String NO_ROH = "noRoh";
	public static final String NO_LOT_ID = "noLotId";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
        public static final String FLAG_KATEGORI_HTA = "flagKategoriHta";

	public void save(Tblppkhta transientInstance) {
		log.debug("saving Tblppkhta instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkhta persistentInstance) {
		log.debug("deleting Tblppkhta instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkhta findById(java.lang.Long id) {
		log.debug("getting Tblppkhta instance with id: " + id);
		try {
			Tblppkhta instance = (Tblppkhta) getSession().get(
					"ekptg.model.entities.Tblppkhta", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkhta instance) {
		log.debug("finding Tblppkhta instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkhta").add(
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
		log.debug("finding Tblppkhta instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblppkhta as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoHakmilik(Object noHakmilik) {
		return findByProperty(NO_HAKMILIK, noHakmilik);
	}

	public List findByNoPt(Object noPt) {
		return findByProperty(NO_PT, noPt);
	}

	public List findByNilaiHtaTarikhmohon(Object nilaiHtaTarikhmohon) {
		return findByProperty(NILAI_HTA_TARIKHMOHON, nilaiHtaTarikhmohon);
	}

	public List findByNilaiHtaTarikhmati(Object nilaiHtaTarikhmati) {
		return findByProperty(NILAI_HTA_TARIKHMATI, nilaiHtaTarikhmati);
	}

	public List findByIdKategori(Object idKategori) {
		return findByProperty(ID_KATEGORI, idKategori);
	}

	public List findByIdJenishm(Object idJenishm) {
		return findByProperty(ID_JENISHM, idJenishm);
	}

	public List findByIdJenispb(Object idJenispb) {
		return findByProperty(ID_JENISPB, idJenispb);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdMukim(Object idMukim) {
		return findByProperty(ID_MUKIM, idMukim);
	}

	public List findByIdLuas(Object idLuas) {
		return findByProperty(ID_LUAS, idLuas);
	}

	public List findByLuas(Object luas) {
		return findByProperty(LUAS, luas);
	}

	public List findByLuasHmp(Object luasHmp) {
		return findByProperty(LUAS_HMP, luasHmp);
	}

	public List findByNoCagaran(Object noCagaran) {
		return findByProperty(NO_CAGARAN, noCagaran);
	}

	public List findByNoPajakan(Object noPajakan) {
		return findByProperty(NO_PAJAKAN, noPajakan);
	}

	public List findByJenisTnh(Object jenisTnh) {
		return findByProperty(JENIS_TNH, jenisTnh);
	}

	public List findByAlamatHta1(Object alamatHta1) {
		return findByProperty(ALAMAT_HTA1, alamatHta1);
	}

	public List findByAlamatHta2(Object alamatHta2) {
		return findByProperty(ALAMAT_HTA2, alamatHta2);
	}

	public List findByAlamatHta3(Object alamatHta3) {
		return findByProperty(ALAMAT_HTA3, alamatHta3);
	}

	public List findByBandarHta(Object bandarHta) {
		return findByProperty(BANDAR_HTA, bandarHta);
	}

	public List findByPoskodHta(Object poskodHta) {
		return findByProperty(POSKOD_HTA, poskodHta);
	}

	public List findByNamaPemaju(Object namaPemaju) {
		return findByProperty(NAMA_PEMAJU, namaPemaju);
	}

	public List findByAlamatPemaju1(Object alamatPemaju1) {
		return findByProperty(ALAMAT_PEMAJU1, alamatPemaju1);
	}

	public List findByAlamatPemaju2(Object alamatPemaju2) {
		return findByProperty(ALAMAT_PEMAJU2, alamatPemaju2);
	}

	public List findByAlamatPemaju3(Object alamatPemaju3) {
		return findByProperty(ALAMAT_PEMAJU3, alamatPemaju3);
	}

	public List findByBandarPemaju(Object bandarPemaju) {
		return findByProperty(BANDAR_PEMAJU, bandarPemaju);
	}

	public List findByPoskodPemaju(Object poskodPemaju) {
		return findByProperty(POSKOD_PEMAJU, poskodPemaju);
	}

	public List findByIdNegeripemaju(Object idNegeripemaju) {
		return findByProperty(ID_NEGERIPEMAJU, idNegeripemaju);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByBaSimati(Object baSimati) {
		return findByProperty(BA_SIMATI, baSimati);
	}

	public List findByBbSimati(Object bbSimati) {
		return findByProperty(BB_SIMATI, bbSimati);
	}

	public List findByNoBangunan(Object noBangunan) {
		return findByProperty(NO_BANGUNAN, noBangunan);
	}

	public List findByNoTingkat(Object noTingkat) {
		return findByProperty(NO_TINGKAT, noTingkat);
	}

	public List findByNoPetak(Object noPetak) {
		return findByProperty(NO_PETAK, noPetak);
	}

	public List findByNoStrata(Object noStrata) {
		return findByProperty(NO_STRATA, noStrata);
	}

	public List findByMaklumatTanah(Object maklumatTanah) {
		return findByProperty(MAKLUMAT_TANAH, maklumatTanah);
	}

	public List findByNoPerjanjian(Object noPerjanjian) {
		return findByProperty(NO_PERJANJIAN, noPerjanjian);
	}

	public List findByJenisHta(Object jenisHta) {
		return findByProperty(JENIS_HTA, jenisHta);
	}

	public List findByStatusPemilikan(Object statusPemilikan) {
		return findByProperty(STATUS_PEMILIKAN, statusPemilikan);
	}

	public List findByTanggungan(Object tanggungan) {
		return findByProperty(TANGGUNGAN, tanggungan);
	}

	public List findByNoPerserahan(Object noPerserahan) {
		return findByProperty(NO_PERSERAHAN, noPerserahan);
	}

	public List findByNamaRancangan(Object namaRancangan) {
		return findByProperty(NAMA_RANCANGAN, namaRancangan);
	}

	public List findByNoRoh(Object noRoh) {
		return findByProperty(NO_ROH, noRoh);
	}

	public List findByNoLotId(Object noLotId) {
		return findByProperty(NO_LOT_ID, noLotId);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}
        public List findByFlagKategoriHta(Object flagKategoriHta) {
                return findByProperty(FLAG_KATEGORI_HTA, flagKategoriHta);
        }

	public List findAll() {
		log.debug("finding all Tblppkhta instances");
		try {
			String queryString = "from Tblppkhta";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkhta merge(Tblppkhta detachedInstance) {
		log.debug("merging Tblppkhta instance");
		try {
			Tblppkhta result = (Tblppkhta) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkhta instance) {
		log.debug("attaching dirty Tblppkhta instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkhta instance) {
		log.debug("attaching clean Tblppkhta instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}