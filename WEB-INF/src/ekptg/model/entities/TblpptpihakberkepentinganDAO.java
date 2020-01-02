package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptpihakberkepentingan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpptpihakberkepentingan
 * @author MyEclipse Persistence Tools
 */

public class TblpptpihakberkepentinganDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpptpihakberkepentinganDAO.class);
	// property constants
	public static final String ID_JENISPB = "idJenispb";
	public static final String NO_KP_LAMA = "noKpLama";
	public static final String ID_JENISNOPB = "idJenisnopb";
	public static final String NO_PB = "noPb";
	public static final String JANTINA = "jantina";
	public static final String ID_BANGSA = "idBangsa";
	public static final String ID_WARGANEGARA = "idWarganegara";
	public static final String NAMA_PB = "namaPb";
	public static final String NAMA_KP = "namaKp";
	public static final String FLAG_HIDUP = "flagHidup";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String NO_TEL_RUMAH = "noTelRumah";
	public static final String NO_HP = "noHp";
	public static final String NO_FAX = "noFax";
	public static final String SYER_ATAS = "syerAtas";
	public static final String SYER_BAWAH = "syerBawah";
	public static final String FLAG_BAYAR_AWARD = "flagBayarAward";
	public static final String FLAG_BANTAHAN = "flagBantahan";
	public static final String FLAG_MAHKAMAH = "flagMahkamah";
	public static final String FLAG_PERMINTAAN_UKUR = "flagPermintaanUkur";
	public static final String JUMLAH_AWARD = "jumlahAward";
	public static final String FLAG_BORANGJ = "flagBorangj";
	public static final String FLAG_BAYAR_PU = "flagBayarPu";
	public static final String FLAG_PEMBAYARAN_AWARD = "flagPembayaranAward";
	public static final String FLAG_BAYAR_BANTAHAN = "flagBayarBantahan";
	public static final String NO_AKAUN = "noAkaun";
	public static final String ID_BANK = "idBank";
	public static final String JUMLAH_AWARD_PU = "jumlahAwardPu";
	public static final String JUMLAH_AWARD_BANTAHAN = "jumlahAwardBantahan";
	public static final String JUMLAH_FAEDAH = "jumlahFaedah";
	public static final String JENIS_LAIN2_PB = "jenisLain2Pb";
	public static final String FLAG_JENIS_DAFTARPB = "flagJenisDaftarpb";
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptpihakberkepentingan transientInstance) {
		log.debug("saving Tblpptpihakberkepentingan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptpihakberkepentingan persistentInstance) {
		log.debug("deleting Tblpptpihakberkepentingan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptpihakberkepentingan findById(java.lang.Long id) {
		log.debug("getting Tblpptpihakberkepentingan instance with id: " + id);
		try {
			Tblpptpihakberkepentingan instance = (Tblpptpihakberkepentingan) getSession()
					.get("ekptg.model.entities.Tblpptpihakberkepentingan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptpihakberkepentingan instance) {
		log.debug("finding Tblpptpihakberkepentingan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptpihakberkepentingan").add(
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
		log.debug("finding Tblpptpihakberkepentingan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptpihakberkepentingan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdJenispb(Object idJenispb) {
		return findByProperty(ID_JENISPB, idJenispb);
	}

	public List findByNoKpLama(Object noKpLama) {
		return findByProperty(NO_KP_LAMA, noKpLama);
	}

	public List findByIdJenisnopb(Object idJenisnopb) {
		return findByProperty(ID_JENISNOPB, idJenisnopb);
	}

	public List findByNoPb(Object noPb) {
		return findByProperty(NO_PB, noPb);
	}

	public List findByJantina(Object jantina) {
		return findByProperty(JANTINA, jantina);
	}

	public List findByIdBangsa(Object idBangsa) {
		return findByProperty(ID_BANGSA, idBangsa);
	}

	public List findByIdWarganegara(Object idWarganegara) {
		return findByProperty(ID_WARGANEGARA, idWarganegara);
	}

	public List findByNamaPb(Object namaPb) {
		return findByProperty(NAMA_PB, namaPb);
	}

	public List findByNamaKp(Object namaKp) {
		return findByProperty(NAMA_KP, namaKp);
	}

	public List findByFlagHidup(Object flagHidup) {
		return findByProperty(FLAG_HIDUP, flagHidup);
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

	public List findByNoTelRumah(Object noTelRumah) {
		return findByProperty(NO_TEL_RUMAH, noTelRumah);
	}

	public List findByNoHp(Object noHp) {
		return findByProperty(NO_HP, noHp);
	}

	public List findByNoFax(Object noFax) {
		return findByProperty(NO_FAX, noFax);
	}

	public List findBySyerAtas(Object syerAtas) {
		return findByProperty(SYER_ATAS, syerAtas);
	}

	public List findBySyerBawah(Object syerBawah) {
		return findByProperty(SYER_BAWAH, syerBawah);
	}

	public List findByFlagBayarAward(Object flagBayarAward) {
		return findByProperty(FLAG_BAYAR_AWARD, flagBayarAward);
	}

	public List findByFlagBantahan(Object flagBantahan) {
		return findByProperty(FLAG_BANTAHAN, flagBantahan);
	}

	public List findByFlagMahkamah(Object flagMahkamah) {
		return findByProperty(FLAG_MAHKAMAH, flagMahkamah);
	}

	public List findByFlagPermintaanUkur(Object flagPermintaanUkur) {
		return findByProperty(FLAG_PERMINTAAN_UKUR, flagPermintaanUkur);
	}

	public List findByJumlahAward(Object jumlahAward) {
		return findByProperty(JUMLAH_AWARD, jumlahAward);
	}

	public List findByFlagBorangj(Object flagBorangj) {
		return findByProperty(FLAG_BORANGJ, flagBorangj);
	}

	public List findByFlagBayarPu(Object flagBayarPu) {
		return findByProperty(FLAG_BAYAR_PU, flagBayarPu);
	}

	public List findByFlagPembayaranAward(Object flagPembayaranAward) {
		return findByProperty(FLAG_PEMBAYARAN_AWARD, flagPembayaranAward);
	}

	public List findByFlagBayarBantahan(Object flagBayarBantahan) {
		return findByProperty(FLAG_BAYAR_BANTAHAN, flagBayarBantahan);
	}

	public List findByNoAkaun(Object noAkaun) {
		return findByProperty(NO_AKAUN, noAkaun);
	}

	public List findByIdBank(Object idBank) {
		return findByProperty(ID_BANK, idBank);
	}

	public List findByJumlahAwardPu(Object jumlahAwardPu) {
		return findByProperty(JUMLAH_AWARD_PU, jumlahAwardPu);
	}

	public List findByJumlahAwardBantahan(Object jumlahAwardBantahan) {
		return findByProperty(JUMLAH_AWARD_BANTAHAN, jumlahAwardBantahan);
	}

	public List findByJumlahFaedah(Object jumlahFaedah) {
		return findByProperty(JUMLAH_FAEDAH, jumlahFaedah);
	}

	public List findByJenisLain2Pb(Object jenisLain2Pb) {
		return findByProperty(JENIS_LAIN2_PB, jenisLain2Pb);
	}

	public List findByFlagJenisDaftarpb(Object flagJenisDaftarpb) {
		return findByProperty(FLAG_JENIS_DAFTARPB, flagJenisDaftarpb);
	}

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
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
		log.debug("finding all Tblpptpihakberkepentingan instances");
		try {
			String queryString = "from Tblpptpihakberkepentingan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptpihakberkepentingan merge(
			Tblpptpihakberkepentingan detachedInstance) {
		log.debug("merging Tblpptpihakberkepentingan instance");
		try {
			Tblpptpihakberkepentingan result = (Tblpptpihakberkepentingan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptpihakberkepentingan instance) {
		log.debug("attaching dirty Tblpptpihakberkepentingan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptpihakberkepentingan instance) {
		log.debug("attaching clean Tblpptpihakberkepentingan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}