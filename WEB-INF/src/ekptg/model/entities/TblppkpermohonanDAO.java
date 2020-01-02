package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkpermohonan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkpermohonan
 * @author MyEclipse Persistence Tools
 */

public class TblppkpermohonanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkpermohonanDAO.class);
	// property constants
	public static final String ID_FAIL = "idFail";
	public static final String BIL_BICARA = "bilBicara";
	public static final String JUMLAH_HTA_TARIKHMOHON = "jumlahHtaTarikhmohon";
	public static final String JUMLAH_HTA_TARIKHMATI = "jumlahHtaTarikhmati";
	public static final String JUMLAH_HA_TARIKHMOHON = "jumlahHaTarikhmohon";
	public static final String JUMLAH_HA_TARIKHMATI = "jumlahHaTarikhmati";
	public static final String JUMLAH_HARTA_TARIKHMOHON = "jumlahHartaTarikhmohon";
	public static final String JUMLAH_HARTA_TARIKHMATI = "jumlahHartaTarikhmati";
	public static final String BIDANG_KUASA = "bidangKuasa";
	public static final String FLAG_JENIS_BORANGC = "flagJenisBorangc";
	public static final String KEPUTUSAN = "keputusan";
	public static final String CATATAN = "catatan";
	public static final String ID_NEGERIMHN = "idNegerimhn";
	public static final String ID_DAERAHMHN = "idDaerahmhn";
	public static final String ID_STATUS = "idStatus";
	public static final String SEKSYEN = "seksyen";
	public static final String ID_NEGERIAWAL = "idNegeriawal";
	public static final String ID_DAERAHAWAL = "idDaerahawal";
	public static final String ID_PEJAWAL = "idPejawal";
	public static final String NO_FAIL_AWAL = "noFailAwal";
	public static final String BATAL_KUASA_PENTADBIR = "batalKuasaPentadbir";
	public static final String LANTIK_PENTADBIR = "lantikPentadbir";
	public static final String BATAL_PAMANAH = "batalPAmanah";
	public static final String LANTIK_PAMANAH = "lantikPAmanah";
	public static final String HARTA_TINGGAL = "hartaTinggal";
	public static final String NAMA_PEMOHON_AWAL = "namaPemohonAwal";
	public static final String FLAG_STATUS_PEGUAM = "flagStatusPeguam";
	public static final String JENIS_FAIL = "jenisFail";
	public static final String NILAI_TERDAHULU = "nilaiTerdahulu";
	public static final String FLAG_JENIS_PERMOHONAN = "flagjenisPermohonan";
	public static final String NO_PERMOHONAN_ONLINE = "noPermohonanOnline";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkpermohonan transientInstance) {
		log.debug("saving Tblppkpermohonan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkpermohonan persistentInstance) {
		log.debug("deleting Tblppkpermohonan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkpermohonan findById(java.lang.Long id) {
		log.debug("getting Tblppkpermohonan instance with id: " + id);
		try {
			Tblppkpermohonan instance = (Tblppkpermohonan) getSession().get(
					"ekptg.model.entities.Tblppkpermohonan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkpermohonan instance) {
		log.debug("finding Tblppkpermohonan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkpermohonan").add(
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
		log.debug("finding Tblppkpermohonan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkpermohonan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdFail(Object idFail) {
		return findByProperty(ID_FAIL, idFail);
	}

	public List findByBilBicara(Object bilBicara) {
		return findByProperty(BIL_BICARA, bilBicara);
	}

	public List findByJumlahHtaTarikhmohon(Object jumlahHtaTarikhmohon) {
		return findByProperty(JUMLAH_HTA_TARIKHMOHON, jumlahHtaTarikhmohon);
	}

	public List findByJumlahHtaTarikhmati(Object jumlahHtaTarikhmati) {
		return findByProperty(JUMLAH_HTA_TARIKHMATI, jumlahHtaTarikhmati);
	}

	public List findByJumlahHaTarikhmohon(Object jumlahHaTarikhmohon) {
		return findByProperty(JUMLAH_HA_TARIKHMOHON, jumlahHaTarikhmohon);
	}

	public List findByJumlahHaTarikhmati(Object jumlahHaTarikhmati) {
		return findByProperty(JUMLAH_HA_TARIKHMATI, jumlahHaTarikhmati);
	}

	public List findByJumlahHartaTarikhmohon(Object jumlahHartaTarikhmohon) {
		return findByProperty(JUMLAH_HARTA_TARIKHMOHON, jumlahHartaTarikhmohon);
	}

	public List findByJumlahHartaTarikhmati(Object jumlahHartaTarikhmati) {
		return findByProperty(JUMLAH_HARTA_TARIKHMATI, jumlahHartaTarikhmati);
	}

	public List findByBidangKuasa(Object bidangKuasa) {
		return findByProperty(BIDANG_KUASA, bidangKuasa);
	}

	public List findByFlagJenisBorangc(Object flagJenisBorangc) {
		return findByProperty(FLAG_JENIS_BORANGC, flagJenisBorangc);
	}

	public List findByKeputusan(Object keputusan) {
		return findByProperty(KEPUTUSAN, keputusan);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdNegerimhn(Object idNegerimhn) {
		return findByProperty(ID_NEGERIMHN, idNegerimhn);
	}

	public List findByIdDaerahmhn(Object idDaerahmhn) {
		return findByProperty(ID_DAERAHMHN, idDaerahmhn);
	}

	public List findByIdStatus(Object idStatus) {
		return findByProperty(ID_STATUS, idStatus);
	}

	public List findBySeksyen(Object seksyen) {
		return findByProperty(SEKSYEN, seksyen);
	}

	public List findByIdNegeriawal(Object idNegeriawal) {
		return findByProperty(ID_NEGERIAWAL, idNegeriawal);
	}

	public List findByIdDaerahawal(Object idDaerahawal) {
		return findByProperty(ID_DAERAHAWAL, idDaerahawal);
	}

	public List findByIdPejawal(Object idPejawal) {
		return findByProperty(ID_PEJAWAL, idPejawal);
	}

	public List findByNoFailAwal(Object noFailAwal) {
		return findByProperty(NO_FAIL_AWAL, noFailAwal);
	}

	public List findByBatalKuasaPentadbir(Object batalKuasaPentadbir) {
		return findByProperty(BATAL_KUASA_PENTADBIR, batalKuasaPentadbir);
	}

	public List findByLantikPentadbir(Object lantikPentadbir) {
		return findByProperty(LANTIK_PENTADBIR, lantikPentadbir);
	}

	public List findByBatalPAmanah(Object batalPAmanah) {
		return findByProperty(BATAL_PAMANAH, batalPAmanah);
	}

	public List findByLantikPAmanah(Object lantikPAmanah) {
		return findByProperty(LANTIK_PAMANAH, lantikPAmanah);
	}

	public List findByHartaTinggal(Object hartaTinggal) {
		return findByProperty(HARTA_TINGGAL, hartaTinggal);
	}

	public List findByNamaPemohonAwal(Object namaPemohonAwal) {
		return findByProperty(NAMA_PEMOHON_AWAL, namaPemohonAwal);
	}

	public List findByFlagStatusPeguam(Object flagStatusPeguam) {
		return findByProperty(FLAG_STATUS_PEGUAM, flagStatusPeguam);
	}

	public List findByJenisFail(Object jenisFail) {
		return findByProperty(JENIS_FAIL, jenisFail);
	}

	public List findByNilaiTerdahulu(Object nilaiTerdahulu) {
		return findByProperty(NILAI_TERDAHULU, nilaiTerdahulu);
	}

	public List findByFlagJenisPermohonan(Object flagjenisPermohonan) {
		return findByProperty(FLAG_JENIS_PERMOHONAN, flagjenisPermohonan);
	}

	public List findByNoPermohonanOnline(Object noPermohonanOnline) {
		return findByProperty(NO_PERMOHONAN_ONLINE, noPermohonanOnline);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkpermohonan instances");
		try {
			String queryString = "from Tblppkpermohonan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkpermohonan merge(Tblppkpermohonan detachedInstance) {
		log.debug("merging Tblppkpermohonan instance");
		try {
			Tblppkpermohonan result = (Tblppkpermohonan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkpermohonan instance) {
		log.debug("attaching dirty Tblppkpermohonan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkpermohonan instance) {
		log.debug("attaching clean Tblppkpermohonan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}