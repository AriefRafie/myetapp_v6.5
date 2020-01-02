package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptbayaran entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptbayaran
 * @author MyEclipse Persistence Tools
 */

public class TblpptbayaranDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptbayaranDAO.class);
	// property constants
	public static final String NO_BAYARAN = "noBayaran";
	public static final String AMAUN_BAYARAN = "amaunBayaran";
	public static final String ID_BANK = "idBank";
	public static final String CARA_BAYAR = "caraBayar";
	public static final String JENIS_AWARD = "jenisAward";
	public static final String FLAG_SERAH_CEK = "flagSerahCek";
	public static final String NAMA_WAKIL = "namaWakil";
	public static final String NO_WAKIL = "noWakil";
	public static final String ID_JENISNOPB = "idJenisnopb";
	public static final String FLAG_TERIMA_CEK = "flagTerimaCek";
	public static final String NO_RUJUKAN_SURAT = "noRujukanSurat";
	public static final String TEMPAT_AMBIL = "tempatAmbil";
	public static final String MASA_AMBIL_CEK = "masaAmbilCek";
	public static final String PENERIMA_CEK = "penerimaCek";
	public static final String DENDA_LEWAT = "dendaLewat";
	public static final String NO_PB = "noPb";
	public static final String NO_KP_LAMA = "noKpLama";
	public static final String NO_EFT = "noEft";
	public static final String NO_BAUCER = "noBaucer";
	public static final String NO_RUJUKAN_SURATEFT = "noRujukanSurateft";
	public static final String ID_TERIMABAYARAN = "idTerimabayaran";
	public static final String ID_PIHAKBERKEPENTINGAN = "idPihakberkepentingan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptbayaran transientInstance) {
		log.debug("saving Tblpptbayaran instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptbayaran persistentInstance) {
		log.debug("deleting Tblpptbayaran instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptbayaran findById(java.lang.Long id) {
		log.debug("getting Tblpptbayaran instance with id: " + id);
		try {
			Tblpptbayaran instance = (Tblpptbayaran) getSession().get(
					"ekptg.model.entities.Tblpptbayaran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptbayaran instance) {
		log.debug("finding Tblpptbayaran instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptbayaran").add(
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
		log.debug("finding Tblpptbayaran instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptbayaran as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoBayaran(Object noBayaran) {
		return findByProperty(NO_BAYARAN, noBayaran);
	}

	public List findByAmaunBayaran(Object amaunBayaran) {
		return findByProperty(AMAUN_BAYARAN, amaunBayaran);
	}

	public List findByIdBank(Object idBank) {
		return findByProperty(ID_BANK, idBank);
	}

	public List findByCaraBayar(Object caraBayar) {
		return findByProperty(CARA_BAYAR, caraBayar);
	}

	public List findByJenisAward(Object jenisAward) {
		return findByProperty(JENIS_AWARD, jenisAward);
	}

	public List findByFlagSerahCek(Object flagSerahCek) {
		return findByProperty(FLAG_SERAH_CEK, flagSerahCek);
	}

	public List findByNamaWakil(Object namaWakil) {
		return findByProperty(NAMA_WAKIL, namaWakil);
	}

	public List findByNoWakil(Object noWakil) {
		return findByProperty(NO_WAKIL, noWakil);
	}

	public List findByIdJenisnopb(Object idJenisnopb) {
		return findByProperty(ID_JENISNOPB, idJenisnopb);
	}

	public List findByFlagTerimaCek(Object flagTerimaCek) {
		return findByProperty(FLAG_TERIMA_CEK, flagTerimaCek);
	}

	public List findByNoRujukanSurat(Object noRujukanSurat) {
		return findByProperty(NO_RUJUKAN_SURAT, noRujukanSurat);
	}

	public List findByTempatAmbil(Object tempatAmbil) {
		return findByProperty(TEMPAT_AMBIL, tempatAmbil);
	}

	public List findByMasaAmbilCek(Object masaAmbilCek) {
		return findByProperty(MASA_AMBIL_CEK, masaAmbilCek);
	}

	public List findByPenerimaCek(Object penerimaCek) {
		return findByProperty(PENERIMA_CEK, penerimaCek);
	}

	public List findByDendaLewat(Object dendaLewat) {
		return findByProperty(DENDA_LEWAT, dendaLewat);
	}

	public List findByNoPb(Object noPb) {
		return findByProperty(NO_PB, noPb);
	}

	public List findByNoKpLama(Object noKpLama) {
		return findByProperty(NO_KP_LAMA, noKpLama);
	}

	public List findByNoEft(Object noEft) {
		return findByProperty(NO_EFT, noEft);
	}

	public List findByNoBaucer(Object noBaucer) {
		return findByProperty(NO_BAUCER, noBaucer);
	}

	public List findByNoRujukanSurateft(Object noRujukanSurateft) {
		return findByProperty(NO_RUJUKAN_SURATEFT, noRujukanSurateft);
	}

	public List findByIdTerimabayaran(Object idTerimabayaran) {
		return findByProperty(ID_TERIMABAYARAN, idTerimabayaran);
	}

	public List findByIdPihakberkepentingan(Object idPihakberkepentingan) {
		return findByProperty(ID_PIHAKBERKEPENTINGAN, idPihakberkepentingan);
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
		log.debug("finding all Tblpptbayaran instances");
		try {
			String queryString = "from Tblpptbayaran";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptbayaran merge(Tblpptbayaran detachedInstance) {
		log.debug("merging Tblpptbayaran instance");
		try {
			Tblpptbayaran result = (Tblpptbayaran) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptbayaran instance) {
		log.debug("attaching dirty Tblpptbayaran instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptbayaran instance) {
		log.debug("attaching clean Tblpptbayaran instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}