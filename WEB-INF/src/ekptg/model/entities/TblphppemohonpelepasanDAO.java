package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphppemohonpelepasan entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphppemohonpelepasan
 * @author MyEclipse Persistence Tools
 */

public class TblphppemohonpelepasanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphppemohonpelepasanDAO.class);
	// property constants
	public static final String ID_KATEGORIPEMOHON = "idKategoripemohon";
	public static final String NAMA = "nama";
	public static final String ID_JENISPENGENALAN = "idJenispengenalan";
	public static final String NO_KP = "noKp";
	public static final String ALAMAT1_TETAP = "alamat1Tetap";
	public static final String ALAMAT2_TETAP = "alamat2Tetap";
	public static final String ALAMAT3_TETAP = "alamat3Tetap";
	public static final String POSKOD_TETAP = "poskodTetap";
	public static final String ID_BANDARTETAP = "idBandartetap";
	public static final String ID_NEGERITETAP = "idNegeritetap";
	public static final String ALAMAT1_SURAT = "alamat1Surat";
	public static final String ALAMAT2_SURAT = "alamat2Surat";
	public static final String ALAMAT3_SURAT = "alamat3Surat";
	public static final String POSKOD_SURAT = "poskodSurat";
	public static final String ID_BANDARSURAT = "idBandarsurat";
	public static final String ID_NEGERISURAT = "idNegerisurat";
	public static final String NO_TEL_PEJABAT = "noTelPejabat";
	public static final String EXT_TEL = "extTel";
	public static final String NO_FAX = "noFax";
	public static final String NO_TEL_BIMBIT = "noTelBimbit";
	public static final String EMEL = "emel";
	public static final String TEMPAT_DAFTAR = "tempatDaftar";
	public static final String STATUS_SYARIKAT = "statusSyarikat";
	public static final String MODAL_DIBENARKAN = "modalDibenarkan";
	public static final String MODAL_JELAS = "modalJelas";
	public static final String UNDANG_UNDANG_DIPERBADANKAN = "undangUndangDiperbadankan";
	public static final String JENIS_PENDAFTARAN = "jenisPendaftaran";
	public static final String ID_AGENSI = "idAgensi";
	public static final String ID_MENTERI = "idMenteri";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String FLAG_SYARIKAT = "flagSyarikat";

	public void save(Tblphppemohonpelepasan transientInstance) {
		log.debug("saving Tblphppemohonpelepasan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphppemohonpelepasan persistentInstance) {
		log.debug("deleting Tblphppemohonpelepasan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphppemohonpelepasan findById(java.lang.Long id) {
		log.debug("getting Tblphppemohonpelepasan instance with id: " + id);
		try {
			Tblphppemohonpelepasan instance = (Tblphppemohonpelepasan) getSession()
					.get("ekptg.model.entities.Tblphppemohonpelepasan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphppemohonpelepasan instance) {
		log.debug("finding Tblphppemohonpelepasan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphppemohonpelepasan").add(
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
		log.debug("finding Tblphppemohonpelepasan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphppemohonpelepasan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdKategoripemohon(Object idKategoripemohon) {
		return findByProperty(ID_KATEGORIPEMOHON, idKategoripemohon);
	}

	public List findByNama(Object nama) {
		return findByProperty(NAMA, nama);
	}

	public List findByIdJenispengenalan(Object idJenispengenalan) {
		return findByProperty(ID_JENISPENGENALAN, idJenispengenalan);
	}

	public List findByNoKp(Object noKp) {
		return findByProperty(NO_KP, noKp);
	}

	public List findByAlamat1Tetap(Object alamat1Tetap) {
		return findByProperty(ALAMAT1_TETAP, alamat1Tetap);
	}

	public List findByAlamat2Tetap(Object alamat2Tetap) {
		return findByProperty(ALAMAT2_TETAP, alamat2Tetap);
	}

	public List findByAlamat3Tetap(Object alamat3Tetap) {
		return findByProperty(ALAMAT3_TETAP, alamat3Tetap);
	}

	public List findByPoskodTetap(Object poskodTetap) {
		return findByProperty(POSKOD_TETAP, poskodTetap);
	}

	public List findByIdBandartetap(Object idBandartetap) {
		return findByProperty(ID_BANDARTETAP, idBandartetap);
	}

	public List findByIdNegeritetap(Object idNegeritetap) {
		return findByProperty(ID_NEGERITETAP, idNegeritetap);
	}

	public List findByAlamat1Surat(Object alamat1Surat) {
		return findByProperty(ALAMAT1_SURAT, alamat1Surat);
	}

	public List findByAlamat2Surat(Object alamat2Surat) {
		return findByProperty(ALAMAT2_SURAT, alamat2Surat);
	}

	public List findByAlamat3Surat(Object alamat3Surat) {
		return findByProperty(ALAMAT3_SURAT, alamat3Surat);
	}

	public List findByPoskodSurat(Object poskodSurat) {
		return findByProperty(POSKOD_SURAT, poskodSurat);
	}

	public List findByIdBandarsurat(Object idBandarsurat) {
		return findByProperty(ID_BANDARSURAT, idBandarsurat);
	}

	public List findByIdNegerisurat(Object idNegerisurat) {
		return findByProperty(ID_NEGERISURAT, idNegerisurat);
	}

	public List findByNoTelPejabat(Object noTelPejabat) {
		return findByProperty(NO_TEL_PEJABAT, noTelPejabat);
	}

	public List findByExtTel(Object extTel) {
		return findByProperty(EXT_TEL, extTel);
	}

	public List findByNoFax(Object noFax) {
		return findByProperty(NO_FAX, noFax);
	}

	public List findByNoTelBimbit(Object noTelBimbit) {
		return findByProperty(NO_TEL_BIMBIT, noTelBimbit);
	}

	public List findByEmel(Object emel) {
		return findByProperty(EMEL, emel);
	}

	public List findByTempatDaftar(Object tempatDaftar) {
		return findByProperty(TEMPAT_DAFTAR, tempatDaftar);
	}

	public List findByStatusSyarikat(Object statusSyarikat) {
		return findByProperty(STATUS_SYARIKAT, statusSyarikat);
	}

	public List findByModalDibenarkan(Object modalDibenarkan) {
		return findByProperty(MODAL_DIBENARKAN, modalDibenarkan);
	}

	public List findByModalJelas(Object modalJelas) {
		return findByProperty(MODAL_JELAS, modalJelas);
	}

	public List findByUndangUndangDiperbadankan(Object undangUndangDiperbadankan) {
		return findByProperty(UNDANG_UNDANG_DIPERBADANKAN,
				undangUndangDiperbadankan);
	}

	public List findByJenisPendaftaran(Object jenisPendaftaran) {
		return findByProperty(JENIS_PENDAFTARAN, jenisPendaftaran);
	}

	public List findByIdAgensi(Object idAgensi) {
		return findByProperty(ID_AGENSI, idAgensi);
	}

	public List findByIdMenteri(Object idMenteri) {
		return findByProperty(ID_MENTERI, idMenteri);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByFlagSyarikat(Object flagSyarikat) {
		return findByProperty(FLAG_SYARIKAT, flagSyarikat);
	}

	public List findAll() {
		log.debug("finding all Tblphppemohonpelepasan instances");
		try {
			String queryString = "from Tblphppemohonpelepasan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphppemohonpelepasan merge(Tblphppemohonpelepasan detachedInstance) {
		log.debug("merging Tblphppemohonpelepasan instance");
		try {
			Tblphppemohonpelepasan result = (Tblphppemohonpelepasan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphppemohonpelepasan instance) {
		log.debug("attaching dirty Tblphppemohonpelepasan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphppemohonpelepasan instance) {
		log.debug("attaching clean Tblphppemohonpelepasan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}