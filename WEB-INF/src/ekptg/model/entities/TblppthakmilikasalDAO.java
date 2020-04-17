package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppthakmilikasal entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppthakmilikasal
 * @author MyEclipse Persistence Tools
 */

public class TblppthakmilikasalDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppthakmilikasalDAO.class);
	// property constants
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_MUKIM = "idMukim";
	public static final String ID_JENISHAKMILIK = "idJenishakmilik";
	public static final String NO_HAKMILIK = "noHakmilik";
	public static final String ID_SUBJAKET = "idSubjaket";
	public static final String FLAG_REZAB = "flagRezab";
	public static final String FLAG_GSA = "flagGsa";
	public static final String TEMPOH_LUPUT = "tempohLuput";
	public static final String NO_PT = "noPt";
	public static final String ID_UNITLUASPT = "idUnitluaspt";
	public static final String LUAS_PT = "luasPt";
	public static final String NO_LOT = "noLot";
	public static final String ID_UNITLUASLOT = "idUnitluaslot";
	public static final String LUAS_LOT = "luasLot";
	public static final String ID_UNITLUASAMBIL = "idUnitluasambil";
	public static final String LUAS_AMBIL = "luasAmbil	";
	public static final String ID_UNITLUASBARU = "idUnitluasbaru";
	public static final String LUAS_BARU = "luasBaru";
	public static final String NO_PELAN = "noPelan";
	public static final String NO_SYIT = "noSyit	";
	public static final String LOKASI = "lokasi	";
	public static final String ID_KATEGORITANAH = "idKategoritanah";
	public static final String SYARAT_NYATA = "syaratNyata";
	public static final String SYARAT_KHAS = "syaratKhas";
	public static final String SEKATAN_KEPENTINGAN = "sekatanKepentingan	";
	public static final String SEKATAN_HAK = "sekatanHak";
	public static final String JENIS_MILIK = "jenisMilik	";
	public static final String ULASAN_PENTADBIR = "ulasanPentadbir";
	public static final String FLAG_UBAH = "flagUbah	";
	public static final String NO_BANGUNAN = "noBangunan";
	public static final String NO_TINGKAT = "noTingkat	";
	public static final String NO_PETAK = "noPetak	";
	public static final String ID_PERMOHONAN = "idPermohonan	";
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_PEMBATALAN = "idPembatalan";
	public static final String NO_KELULUSAN = "noKelulusan";
	public static final String NO_DAFTAR = "noDaftar";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblppthakmilikasal transientInstance) {
		log.debug("saving Tblppthakmilikasal instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppthakmilikasal persistentInstance) {
		log.debug("deleting Tblppthakmilikasal instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppthakmilikasal findById(java.lang.Long id) {
		log.debug("getting Tblppthakmilikasal instance with id: " + id);
		try {
			Tblppthakmilikasal instance = (Tblppthakmilikasal) getSession()
					.get("ekptg.model.entities.Tblppthakmilikasal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppthakmilikasal instance) {
		log.debug("finding Tblppthakmilikasal instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppthakmilikasal").add(
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
		log.debug("finding Tblppthakmilikasal instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppthakmilikasal as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdMukim(Object idMukim) {
		return findByProperty(ID_MUKIM, idMukim);
	}

	public List findByIdJenishakmilik(Object idJenishakmilik) {
		return findByProperty(ID_JENISHAKMILIK, idJenishakmilik);
	}

	public List findByNoHakmilik(Object noHakmilik) {
		return findByProperty(NO_HAKMILIK, noHakmilik);
	}

	public List findByIdSubjaket(Object idSubjaket) {
		return findByProperty(ID_SUBJAKET, idSubjaket);
	}

	public List findByFlagRezab(Object flagRezab) {
		return findByProperty(FLAG_REZAB, flagRezab);
	}

	public List findByFlagGsa(Object flagGsa) {
		return findByProperty(FLAG_GSA, flagGsa);
	}

	public List findByTempohLuput(Object tempohLuput) {
		return findByProperty(TEMPOH_LUPUT, tempohLuput);
	}

	public List findByNoPt(Object noPt) {
		return findByProperty(NO_PT, noPt);
	}

	public List findByIdUnitluaspt(Object idUnitluaspt) {
		return findByProperty(ID_UNITLUASPT, idUnitluaspt);
	}

	public List findByLuasPt(Object luasPt) {
		return findByProperty(LUAS_PT, luasPt);
	}

	public List findByNoLot(Object noLot) {
		return findByProperty(NO_LOT, noLot);
	}

	public List findByIdUnitluaslot(Object idUnitluaslot) {
		return findByProperty(ID_UNITLUASLOT, idUnitluaslot);
	}

	public List findByLuasLot(Object luasLot) {
		return findByProperty(LUAS_LOT, luasLot);
	}

	public List findByIdUnitluasambil(Object idUnitluasambil) {
		return findByProperty(ID_UNITLUASAMBIL, idUnitluasambil);
	}

	public List findByLuasAmbil(Object luasAmbil) {
		return findByProperty(LUAS_AMBIL, luasAmbil);
	}

	public List findByIdUnitluasbaru(Object idUnitluasbaru) {
		return findByProperty(ID_UNITLUASBARU, idUnitluasbaru);
	}

	public List findByLuasBaru(Object luasBaru) {
		return findByProperty(LUAS_BARU, luasBaru);
	}

	public List findByNoPelan(Object noPelan) {
		return findByProperty(NO_PELAN, noPelan);
	}

	public List findByNoSyit(Object noSyit) {
		return findByProperty(NO_SYIT, noSyit);
	}

	public List findByLokasi(Object lokasi) {
		return findByProperty(LOKASI, lokasi);
	}

	public List findByIdKategoritanah(Object idKategoritanah) {
		return findByProperty(ID_KATEGORITANAH, idKategoritanah);
	}

	public List findBySyaratNyata(Object syaratNyata) {
		return findByProperty(SYARAT_NYATA, syaratNyata);
	}

	public List findBySyaratKhas(Object syaratKhas) {
		return findByProperty(SYARAT_KHAS, syaratKhas);
	}

	public List findBySekatanKepentingan(Object sekatanKepentingan) {
		return findByProperty(SEKATAN_KEPENTINGAN, sekatanKepentingan);
	}

	public List findBySekatanHak(Object sekatanHak) {
		return findByProperty(SEKATAN_HAK, sekatanHak);
	}

	public List findByJenisMilik(Object jenisMilik) {
		return findByProperty(JENIS_MILIK, jenisMilik);
	}

	public List findByUlasanPentadbir(Object ulasanPentadbir) {
		return findByProperty(ULASAN_PENTADBIR, ulasanPentadbir);
	}

	public List findByFlagUbah(Object flagUbah) {
		return findByProperty(FLAG_UBAH, flagUbah);
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

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
	}

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
	}

	public List findByIdPembatalan(Object idPembatalan) {
		return findByProperty(ID_PEMBATALAN, idPembatalan);
	}

	public List findByNoKelulusan(Object noKelulusan) {
		return findByProperty(NO_KELULUSAN, noKelulusan);
	}

	public List findByNoDaftar(Object noDaftar) {
		return findByProperty(NO_DAFTAR, noDaftar);
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
		log.debug("finding all Tblppthakmilikasal instances");
		try {
			String queryString = "from Tblppthakmilikasal";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppthakmilikasal merge(Tblppthakmilikasal detachedInstance) {
		log.debug("merging Tblppthakmilikasal instance");
		try {
			Tblppthakmilikasal result = (Tblppthakmilikasal) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppthakmilikasal instance) {
		log.debug("attaching dirty Tblppthakmilikasal instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppthakmilikasal instance) {
		log.debug("attaching clean Tblppthakmilikasal instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}