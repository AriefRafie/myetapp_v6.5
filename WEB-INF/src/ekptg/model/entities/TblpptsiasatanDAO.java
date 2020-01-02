package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptsiasatan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptsiasatan
 * @author MyEclipse Persistence Tools
 */

public class TblpptsiasatanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptsiasatanDAO.class);
	// property constants
	public static final String NO_KES = "noKes";
	public static final String NO_KES_SEBELUM = "noKesSebelum";
	public static final String NO_KES_BERIKUT = "noKesBerikut";
	public static final String NO_SIASATAN = "noSiasatan";
	public static final String STATUS_SIASATAN = "statusSiasatan";
	public static final String MASA_SIASATAN = "masaSiasatan";
	public static final String TEMPAT = "tempat";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ALASAN_TANGGUH = "alasanTangguh";
	public static final String NILAIAN_JPPH = "nilaianJpph";
	public static final String ID_UNITLUAS = "idUnitluas";
	public static final String BANTAHAN_TUANTNH = "bantahanTuantnh";
	public static final String BANTAHAN_AGENSI = "bantahanAgensi";
	public static final String BANTAHAN_LAIN = "bantahanLain";
	public static final String TEMPOH_MILIK_TANAH = "tempohMilikTanah";
	public static final String CARA_MILIK = "caraMilik";
	public static final String HARGA_BELI = "hargaBeli";
	public static final String JENIS_BANGUNAN = "jenisBangunan";
	public static final String JENIS_TANAMAN = "jenisTanaman";
	public static final String FLAG_PECAH_SEMPADAN = "flagPecahSempadan";
	public static final String FLAG_TUKAR_SYARAT = "flagTukarSyarat";
	public static final String STATUS_SEMASA = "statusSemasa";
	public static final String BEBANAN = "bebanan";
	public static final String KETERANGAN_TUAN_TANAH = "keteranganTuanTanah";
	public static final String KETERANGAN_AGENSI = "keteranganAgensi";
	public static final String KETERANGAN_JURUNILAI = "keteranganJurunilai";
	public static final String TUNTUTAN_TUANTNH = "tuntutanTuantnh";
	public static final String TUNTUTAN_PB_BEBANAN = "tuntutanPbBebanan";
	public static final String TUNTUTAN_PB_TDKDAFTAR = "tuntutanPbTdkdaftar";
	public static final String TUNTUTAN_PB_LAIN = "tuntutanPbLain";
	public static final String ID_BORANGE = "idBorange";
	public static final String PERINTAH = "perintah";
	public static final String FLAG_BANTAHAN = "flagBantahan";
	public static final String ID_PEGAWAI_SIASATAN = "idPegawaiSiasatan";
	public static final String ID_PENARIKANBALIK = "idPenarikanbalik";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptsiasatan transientInstance) {
		log.debug("saving Tblpptsiasatan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptsiasatan persistentInstance) {
		log.debug("deleting Tblpptsiasatan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptsiasatan findById(java.lang.Long id) {
		log.debug("getting Tblpptsiasatan instance with id: " + id);
		try {
			Tblpptsiasatan instance = (Tblpptsiasatan) getSession().get(
					"ekptg.model.entities.Tblpptsiasatan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptsiasatan instance) {
		log.debug("finding Tblpptsiasatan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptsiasatan").add(
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
		log.debug("finding Tblpptsiasatan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptsiasatan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoKes(Object noKes) {
		return findByProperty(NO_KES, noKes);
	}

	public List findByNoKesSebelum(Object noKesSebelum) {
		return findByProperty(NO_KES_SEBELUM, noKesSebelum);
	}

	public List findByNoKesBerikut(Object noKesBerikut) {
		return findByProperty(NO_KES_BERIKUT, noKesBerikut);
	}

	public List findByNoSiasatan(Object noSiasatan) {
		return findByProperty(NO_SIASATAN, noSiasatan);
	}

	public List findByStatusSiasatan(Object statusSiasatan) {
		return findByProperty(STATUS_SIASATAN, statusSiasatan);
	}

	public List findByMasaSiasatan(Object masaSiasatan) {
		return findByProperty(MASA_SIASATAN, masaSiasatan);
	}

	public List findByTempat(Object tempat) {
		return findByProperty(TEMPAT, tempat);
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

	public List findByAlasanTangguh(Object alasanTangguh) {
		return findByProperty(ALASAN_TANGGUH, alasanTangguh);
	}

	public List findByNilaianJpph(Object nilaianJpph) {
		return findByProperty(NILAIAN_JPPH, nilaianJpph);
	}

	public List findByIdUnitluas(Object idUnitluas) {
		return findByProperty(ID_UNITLUAS, idUnitluas);
	}

	public List findByBantahanTuantnh(Object bantahanTuantnh) {
		return findByProperty(BANTAHAN_TUANTNH, bantahanTuantnh);
	}

	public List findByBantahanAgensi(Object bantahanAgensi) {
		return findByProperty(BANTAHAN_AGENSI, bantahanAgensi);
	}

	public List findByBantahanLain(Object bantahanLain) {
		return findByProperty(BANTAHAN_LAIN, bantahanLain);
	}

	public List findByTempohMilikTanah(Object tempohMilikTanah) {
		return findByProperty(TEMPOH_MILIK_TANAH, tempohMilikTanah);
	}

	public List findByCaraMilik(Object caraMilik) {
		return findByProperty(CARA_MILIK, caraMilik);
	}

	public List findByHargaBeli(Object hargaBeli) {
		return findByProperty(HARGA_BELI, hargaBeli);
	}

	public List findByJenisBangunan(Object jenisBangunan) {
		return findByProperty(JENIS_BANGUNAN, jenisBangunan);
	}

	public List findByJenisTanaman(Object jenisTanaman) {
		return findByProperty(JENIS_TANAMAN, jenisTanaman);
	}

	public List findByFlagPecahSempadan(Object flagPecahSempadan) {
		return findByProperty(FLAG_PECAH_SEMPADAN, flagPecahSempadan);
	}

	public List findByFlagTukarSyarat(Object flagTukarSyarat) {
		return findByProperty(FLAG_TUKAR_SYARAT, flagTukarSyarat);
	}

	public List findByStatusSemasa(Object statusSemasa) {
		return findByProperty(STATUS_SEMASA, statusSemasa);
	}

	public List findByBebanan(Object bebanan) {
		return findByProperty(BEBANAN, bebanan);
	}

	public List findByKeteranganTuanTanah(Object keteranganTuanTanah) {
		return findByProperty(KETERANGAN_TUAN_TANAH, keteranganTuanTanah);
	}

	public List findByKeteranganAgensi(Object keteranganAgensi) {
		return findByProperty(KETERANGAN_AGENSI, keteranganAgensi);
	}

	public List findByKeteranganJurunilai(Object keteranganJurunilai) {
		return findByProperty(KETERANGAN_JURUNILAI, keteranganJurunilai);
	}

	public List findByTuntutanTuantnh(Object tuntutanTuantnh) {
		return findByProperty(TUNTUTAN_TUANTNH, tuntutanTuantnh);
	}

	public List findByTuntutanPbBebanan(Object tuntutanPbBebanan) {
		return findByProperty(TUNTUTAN_PB_BEBANAN, tuntutanPbBebanan);
	}

	public List findByTuntutanPbTdkdaftar(Object tuntutanPbTdkdaftar) {
		return findByProperty(TUNTUTAN_PB_TDKDAFTAR, tuntutanPbTdkdaftar);
	}

	public List findByTuntutanPbLain(Object tuntutanPbLain) {
		return findByProperty(TUNTUTAN_PB_LAIN, tuntutanPbLain);
	}

	public List findByIdBorange(Object idBorange) {
		return findByProperty(ID_BORANGE, idBorange);
	}

	public List findByPerintah(Object perintah) {
		return findByProperty(PERINTAH, perintah);
	}

	public List findByFlagBantahan(Object flagBantahan) {
		return findByProperty(FLAG_BANTAHAN, flagBantahan);
	}

	public List findByIdPegawaiSiasatan(Object idPegawaiSiasatan) {
		return findByProperty(ID_PEGAWAI_SIASATAN, idPegawaiSiasatan);
	}

	public List findByIdPenarikanbalik(Object idPenarikanbalik) {
		return findByProperty(ID_PENARIKANBALIK, idPenarikanbalik);
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
		log.debug("finding all Tblpptsiasatan instances");
		try {
			String queryString = "from Tblpptsiasatan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptsiasatan merge(Tblpptsiasatan detachedInstance) {
		log.debug("merging Tblpptsiasatan instance");
		try {
			Tblpptsiasatan result = (Tblpptsiasatan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptsiasatan instance) {
		log.debug("attaching dirty Tblpptsiasatan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptsiasatan instance) {
		log.debug("attaching clean Tblpptsiasatan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}