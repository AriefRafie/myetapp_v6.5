package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkob entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkob
 * @author MyEclipse Persistence Tools
 */

public class TblppkobDAO extends BaseHibernateDAO {

	private static final Log log = LogFactory.getLog(TblppkobDAO.class);
	// property constants
	public static final String ID_SIMATI = "idSimati";
	public static final String NAMA_OB = "namaOb";
	public static final String NO_KP_BARU = "noKpBaru";
	public static final String NO_KP_LAMA = "noKpLama";
	public static final String JENIS_KP = "jenisKp";
	public static final String NO_KP_LAIN = "noKpLain";
	public static final String NO_SURAT_BERANAK = "noSuratBeranak";
	public static final String JANTINA = "jantina";
	public static final String UMUR = "umur";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String BANDAR = "bandar";
	public static final String POSKOD = "poskod";
	public static final String NO_HP = "noHp";
	public static final String NO_TEL = "noTel";
	public static final String CATATAN = "catatan";
	public static final String STATUS_HIDUP = "statusHidup";
	public static final String ID_TARAFKPTG = "idTarafkptg";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_SAUDARA = "idSaudara";
	public static final String ID_JENISPB = "idJenispb";
	public static final String JENIS_AGAMA = "jenisAgama";
	public static final String JENIS_WARGA = "jenisWarga";
	public static final String ID_BANK = "idBank";
	public static final String NO_AKAUN = "noAkaun";
	public static final String WAKTU_KEMATIAN = "waktuKematian";
	public static final String JENIS_WAKTU_KEMATIAN = "jenisWaktuKematian";
	public static final String STATUS_OB = "statusOb";
	public static final String NILAI_HUTANG = "nilaiHutang";
	public static final String BA_FARAID = "baFaraid";
	public static final String BB_FARAID = "bbFaraid";
	public static final String LAPIS = "lapis";
	public static final String BUTIRAN_HUTANG = "butiranHutang";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
     
     	public void save(Tblppkob transientInstance) {
		log.debug("saving Tblppkob instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkob persistentInstance) {
		log.debug("deleting Tblppkob instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkob findById(java.lang.Long id) {
		log.debug("getting Tblppkob instance with id: " + id);
		try {
			Tblppkob instance = (Tblppkob) getSession().get(
					"ekptg.model.entities.Tblppkob", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkob instance) {
		log.debug("finding Tblppkob instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkob").add(
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
		log.debug("finding Tblppkob instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblppkob as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdSimati(Object idSimati) {
		return findByProperty(ID_SIMATI, idSimati);
	}

	public List findByNamaOb(Object namaOb) {
		return findByProperty(NAMA_OB, namaOb);
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

	public List findByNoKpLain(Object noKpLain) {
		return findByProperty(NO_KP_LAIN, noKpLain);
	}

	public List findByNoSuratBeranak(Object noSuratBeranak) {
		return findByProperty(NO_SURAT_BERANAK, noSuratBeranak);
	}

	public List findByJantina(Object jantina) {
		return findByProperty(JANTINA, jantina);
	}

	public List findByUmur(Object umur) {
		return findByProperty(UMUR, umur);
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

	public List findByNoHp(Object noHp) {
		return findByProperty(NO_HP, noHp);
	}

	public List findByNoTel(Object noTel) {
		return findByProperty(NO_TEL, noTel);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByStatusHidup(Object statusHidup) {
		return findByProperty(STATUS_HIDUP, statusHidup);
	}

	public List findByIdTarafkptg(Object idTarafkptg) {
		return findByProperty(ID_TARAFKPTG, idTarafkptg);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdSaudara(Object idSaudara) {
		return findByProperty(ID_SAUDARA, idSaudara);
	}

	public List findByIdJenispb(Object idJenispb) {
		return findByProperty(ID_JENISPB, idJenispb);
	}

	public List findByJenisAgama(Object jenisAgama) {
		return findByProperty(JENIS_AGAMA, jenisAgama);
	}

	public List findByJenisWarga(Object jenisWarga) {
		return findByProperty(JENIS_WARGA, jenisWarga);
	}

	public List findByIdBank(Object idBank) {
		return findByProperty(ID_BANK, idBank);
	}

	public List findByNoAkaun(Object noAkaun) {
		return findByProperty(NO_AKAUN, noAkaun);
	}

	public List findByWaktuKematian(Object waktuKematian) {
		return findByProperty(WAKTU_KEMATIAN, waktuKematian);
	}

	public List findByJenisWaktuKematian(Object jenisWaktuKematian) {
		return findByProperty(JENIS_WAKTU_KEMATIAN, jenisWaktuKematian);
	}

	public List findByStatusOb(Object statusOb) {
		return findByProperty(STATUS_OB, statusOb);
	}

	public List findByNilaiHutang(Object nilaiHutang) {
		return findByProperty(NILAI_HUTANG, nilaiHutang);
	}

	public List findByBaFaraid(Object baFaraid) {
		return findByProperty(BA_FARAID, baFaraid);
	}

	public List findByBbFaraid(Object bbFaraid) {
		return findByProperty(BB_FARAID, bbFaraid);
	}

	public List findByLapis(Object lapis) {
		return findByProperty(LAPIS, lapis);
	}

	public List findByButiranHutang(Object butiranHutang) {
		return findByProperty(BUTIRAN_HUTANG, butiranHutang);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkob instances");
		try {
			String queryString = "from Tblppkob";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkob merge(Tblppkob detachedInstance) {
		log.debug("merging Tblppkob instance");
		try {
			Tblppkob result = (Tblppkob) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkob instance) {
		log.debug("attaching dirty Tblppkob instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkob instance) {
		log.debug("attaching clean Tblppkob instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}