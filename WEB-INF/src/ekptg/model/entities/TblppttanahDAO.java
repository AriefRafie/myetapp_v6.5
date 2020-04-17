package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppttanah entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppttanah
 * @author MyEclipse Persistence Tools
 */

public class TblppttanahDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppttanahDAO.class);
	// property constants
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_UNITLUASAMBIL = "idUnitluasambil";
	public static final String LUAS_AMBIL = "luasAmbil";
	public static final String ID_UNITLUASTERKINI = "idUnitluasterkini";
	public static final String LUAS_TERKINI = "luasTerkini";
	public static final String JARAK_BANDAR = "jarakBandar";
	public static final String KEADAAN_TANAH = "keadaanTanah";
	public static final String SEMPADAN_UTARA = "sempadanUtara";
	public static final String SEMPADAN_SELATAN = "sempadanSelatan";
	public static final String SEMPADAN_BARAT = "sempadanBarat";
	public static final String SEMPADAN_TIMUR = "sempadanTimur";
	public static final String KEMUDAHAN_AWAM = "kemudahanAwam";
	public static final String ULASAN_PEGAWAI = "ulasanPegawai";
	public static final String TANAMAN = "tanaman";
	public static final String LOKASI_TANAH = "lokasiTanah";
	public static final String HARGA_PASARAN = "hargaPasaran";
	public static final String HARGA_SEUNIT_JPPH = "hargaSeunitJpph";
	public static final String AMAUN_PENJEJASAN_JPPH = "amaunPenjejasanJpph";
	public static final String AMAUN_PECAHPISAH_JPPH = "amaunPecahpisahJpph";
	public static final String NAIK_NILAI_JPPH = "naikNilaiJpph";
	public static final String AMAUN_SAGUHATI = "amaunSaguhati";
	public static final String AMAUN_BAYAR_PENYEWA = "amaunBayarPenyewa";
	public static final String AMAUN_LAIN2 = "amaunLain2";
	public static final String ID_PELAPOR = "idPelapor";
	public static final String UNIT_HARGA = "unitHarga";
	public static final String HARGA_SEUNIT_SO = "hargaSeunitSo";
	public static final String BAYAR_FEE = "bayarFee";
	public static final String BAYAR_TANAH = "bayarTanah";
	public static final String BAYAR_PECAHPISAH = "bayarPecahpisah";
	public static final String BAYAR_PENJEJASAN = "bayarPenjejasan";
	public static final String UNIT_HARGA_SO = "unitHargaSo";
	public static final String HARGA_PASARAN_SO = "hargaPasaranSo";
	public static final String BAYAR_NAIK_NILAISO = "bayarNaikNilaiso";
	public static final String HARGA_SEUNIT_AKHIR = "hargaSeunitAkhir";
	public static final String UNIT_HARGA_AKHIR = "unitHargaAkhir";
	public static final String NAMA_PBT = "namaPbt";
	public static final String HALANGAN = "halangan";
	public static final String FLAG_PBT = "flagPbt";
	public static final String FLAG_REZAB_MELAYU = "flagRezabMelayu";
	public static final String FLAG_BUKIT = "flagBukit";
	public static final String FLAG_LANDAI = "flagLandai";
	public static final String FLAG_RENDAH = "flagRendah";
	public static final String FLAG_RATA = "flagRata";
	public static final String FLAG_PAYA = "flagPaya";
	public static final String FLAG_LEMBAH = "flagLembah";
	public static final String FLAG_LURAH = "flagLurah";
	public static final String FLAG_DIUSAHA = "flagDiusaha";
	public static final String FLAG_LAPANG = "flagLapang";
	public static final String FLAG_TERBIAR = "flagTerbiar";
	public static final String FLAG_HUTAN = "flagHutan";
	public static final String FLAG_BELUKAR = "flagBelukar";
	public static final String FLAG_SEMAK = "flagSemak";
	public static final String RUPABUMI = "rupabumi";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblppttanah transientInstance) {
		log.debug("saving Tblppttanah instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppttanah persistentInstance) {
		log.debug("deleting Tblppttanah instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppttanah findById(java.lang.Long id) {
		log.debug("getting Tblppttanah instance with id: " + id);
		try {
			Tblppttanah instance = (Tblppttanah) getSession().get(
					"ekptg.model.entities.Tblppttanah", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppttanah instance) {
		log.debug("finding Tblppttanah instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppttanah").add(
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
		log.debug("finding Tblppttanah instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblppttanah as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
	}

	public List findByIdUnitluasambil(Object idUnitluasambil) {
		return findByProperty(ID_UNITLUASAMBIL, idUnitluasambil);
	}

	public List findByLuasAmbil(Object luasAmbil) {
		return findByProperty(LUAS_AMBIL, luasAmbil);
	}

	public List findByIdUnitluasterkini(Object idUnitluasterkini) {
		return findByProperty(ID_UNITLUASTERKINI, idUnitluasterkini);
	}

	public List findByLuasTerkini(Object luasTerkini) {
		return findByProperty(LUAS_TERKINI, luasTerkini);
	}

	public List findByJarakBandar(Object jarakBandar) {
		return findByProperty(JARAK_BANDAR, jarakBandar);
	}

	public List findByKeadaanTanah(Object keadaanTanah) {
		return findByProperty(KEADAAN_TANAH, keadaanTanah);
	}

	public List findBySempadanUtara(Object sempadanUtara) {
		return findByProperty(SEMPADAN_UTARA, sempadanUtara);
	}

	public List findBySempadanSelatan(Object sempadanSelatan) {
		return findByProperty(SEMPADAN_SELATAN, sempadanSelatan);
	}

	public List findBySempadanBarat(Object sempadanBarat) {
		return findByProperty(SEMPADAN_BARAT, sempadanBarat);
	}

	public List findBySempadanTimur(Object sempadanTimur) {
		return findByProperty(SEMPADAN_TIMUR, sempadanTimur);
	}

	public List findByKemudahanAwam(Object kemudahanAwam) {
		return findByProperty(KEMUDAHAN_AWAM, kemudahanAwam);
	}

	public List findByUlasanPegawai(Object ulasanPegawai) {
		return findByProperty(ULASAN_PEGAWAI, ulasanPegawai);
	}

	public List findByTanaman(Object tanaman) {
		return findByProperty(TANAMAN, tanaman);
	}

	public List findByLokasiTanah(Object lokasiTanah) {
		return findByProperty(LOKASI_TANAH, lokasiTanah);
	}

	public List findByHargaPasaran(Object hargaPasaran) {
		return findByProperty(HARGA_PASARAN, hargaPasaran);
	}

	public List findByHargaSeunitJpph(Object hargaSeunitJpph) {
		return findByProperty(HARGA_SEUNIT_JPPH, hargaSeunitJpph);
	}

	public List findByAmaunPenjejasanJpph(Object amaunPenjejasanJpph) {
		return findByProperty(AMAUN_PENJEJASAN_JPPH, amaunPenjejasanJpph);
	}

	public List findByAmaunPecahpisahJpph(Object amaunPecahpisahJpph) {
		return findByProperty(AMAUN_PECAHPISAH_JPPH, amaunPecahpisahJpph);
	}

	public List findByNaikNilaiJpph(Object naikNilaiJpph) {
		return findByProperty(NAIK_NILAI_JPPH, naikNilaiJpph);
	}

	public List findByAmaunSaguhati(Object amaunSaguhati) {
		return findByProperty(AMAUN_SAGUHATI, amaunSaguhati);
	}

	public List findByAmaunBayarPenyewa(Object amaunBayarPenyewa) {
		return findByProperty(AMAUN_BAYAR_PENYEWA, amaunBayarPenyewa);
	}

	public List findByAmaunLain2(Object amaunLain2) {
		return findByProperty(AMAUN_LAIN2, amaunLain2);
	}

	public List findByIdPelapor(Object idPelapor) {
		return findByProperty(ID_PELAPOR, idPelapor);
	}

	public List findByUnitHarga(Object unitHarga) {
		return findByProperty(UNIT_HARGA, unitHarga);
	}

	public List findByHargaSeunitSo(Object hargaSeunitSo) {
		return findByProperty(HARGA_SEUNIT_SO, hargaSeunitSo);
	}

	public List findByBayarFee(Object bayarFee) {
		return findByProperty(BAYAR_FEE, bayarFee);
	}

	public List findByBayarTanah(Object bayarTanah) {
		return findByProperty(BAYAR_TANAH, bayarTanah);
	}

	public List findByBayarPecahpisah(Object bayarPecahpisah) {
		return findByProperty(BAYAR_PECAHPISAH, bayarPecahpisah);
	}

	public List findByBayarPenjejasan(Object bayarPenjejasan) {
		return findByProperty(BAYAR_PENJEJASAN, bayarPenjejasan);
	}

	public List findByUnitHargaSo(Object unitHargaSo) {
		return findByProperty(UNIT_HARGA_SO, unitHargaSo);
	}

	public List findByHargaPasaranSo(Object hargaPasaranSo) {
		return findByProperty(HARGA_PASARAN_SO, hargaPasaranSo);
	}

	public List findByBayarNaikNilaiso(Object bayarNaikNilaiso) {
		return findByProperty(BAYAR_NAIK_NILAISO, bayarNaikNilaiso);
	}

	public List findByHargaSeunitAkhir(Object hargaSeunitAkhir) {
		return findByProperty(HARGA_SEUNIT_AKHIR, hargaSeunitAkhir);
	}

	public List findByUnitHargaAkhir(Object unitHargaAkhir) {
		return findByProperty(UNIT_HARGA_AKHIR, unitHargaAkhir);
	}

	public List findByNamaPbt(Object namaPbt) {
		return findByProperty(NAMA_PBT, namaPbt);
	}

	public List findByHalangan(Object halangan) {
		return findByProperty(HALANGAN, halangan);
	}

	public List findByFlagPbt(Object flagPbt) {
		return findByProperty(FLAG_PBT, flagPbt);
	}

	public List findByFlagRezabMelayu(Object flagRezabMelayu) {
		return findByProperty(FLAG_REZAB_MELAYU, flagRezabMelayu);
	}

	public List findByFlagBukit(Object flagBukit) {
		return findByProperty(FLAG_BUKIT, flagBukit);
	}

	public List findByFlagLandai(Object flagLandai) {
		return findByProperty(FLAG_LANDAI, flagLandai);
	}

	public List findByFlagRendah(Object flagRendah) {
		return findByProperty(FLAG_RENDAH, flagRendah);
	}

	public List findByFlagRata(Object flagRata) {
		return findByProperty(FLAG_RATA, flagRata);
	}

	public List findByFlagPaya(Object flagPaya) {
		return findByProperty(FLAG_PAYA, flagPaya);
	}

	public List findByFlagLembah(Object flagLembah) {
		return findByProperty(FLAG_LEMBAH, flagLembah);
	}

	public List findByFlagLurah(Object flagLurah) {
		return findByProperty(FLAG_LURAH, flagLurah);
	}

	public List findByFlagDiusaha(Object flagDiusaha) {
		return findByProperty(FLAG_DIUSAHA, flagDiusaha);
	}

	public List findByFlagLapang(Object flagLapang) {
		return findByProperty(FLAG_LAPANG, flagLapang);
	}

	public List findByFlagTerbiar(Object flagTerbiar) {
		return findByProperty(FLAG_TERBIAR, flagTerbiar);
	}

	public List findByFlagHutan(Object flagHutan) {
		return findByProperty(FLAG_HUTAN, flagHutan);
	}

	public List findByFlagBelukar(Object flagBelukar) {
		return findByProperty(FLAG_BELUKAR, flagBelukar);
	}

	public List findByFlagSemak(Object flagSemak) {
		return findByProperty(FLAG_SEMAK, flagSemak);
	}

	public List findByRupabumi(Object rupabumi) {
		return findByProperty(RUPABUMI, rupabumi);
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
		log.debug("finding all Tblppttanah instances");
		try {
			String queryString = "from Tblppttanah";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppttanah merge(Tblppttanah detachedInstance) {
		log.debug("merging Tblppttanah instance");
		try {
			Tblppttanah result = (Tblppttanah) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppttanah instance) {
		log.debug("attaching dirty Tblppttanah instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppttanah instance) {
		log.debug("attaching clean Tblppttanah instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}