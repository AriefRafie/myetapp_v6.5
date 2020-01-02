package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptaward entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptaward
 * @author MyEclipse Persistence Tools
 */

public class TblpptawardDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptawardDAO.class);
	// property constants
	public static final String JENIS_AWARD = "jenisAward";
	public static final String BAYAR_FEE = "bayarFee";
	public static final String BAYAR_PAMPASAN = "bayarPampasan";
	public static final String BAYAR_BANGUNAN = "bayarBangunan";
	public static final String BAYAR_BETTERMENT = "bayarBetterment";
	public static final String BAYAR_PECAHPISAH = "bayarPecahpisah";
	public static final String BAYAR_PENJEJASAN = "bayarPenjejasan";
	public static final String BAYAR_PENDAPATAN = "bayarPendapatan";
	public static final String BAYAR_PINDAH = "bayarPindah";
	public static final String BAYAR_SEWA = "bayarSewa";
	public static final String BAYAR_LAIN2 = "bayarLain2";
	public static final String LUAS_AMBIL = "luasAmbil";
	public static final String ID_UNITLUAS_AMBIL = "idUnitluasAmbil";
	public static final String NILAI_SEUNIT_AMBIL = "nilaiSeunitAmbil";
	public static final String UNIT_NILAI_AMBIL = "unitNilaiAmbil";
	public static final String STATUS_PENERIMA = "statusPenerima";
	public static final String DENDA_LEWAT = "dendaLewat";
	public static final String ULASAN = "ulasan";
	public static final String FAEDAH_SELEPAS_PU = "faedahSelepasPu";
	public static final String FAEDAH_SEBELUM_PU = "faedahSebelumPu";
	public static final String FLAG_SERAH_CEK = "flagSerahCek";
	public static final String KEPUTUSAN_TAWARAN = "keputusanTawaran";
	public static final String TEMPOH_PAMPASAN = "tempohPampasan";
	public static final String UNIT_TEMPOH = "unitTempoh";
	public static final String ID_PIHAKBERKEPENTINGAN = "idPihakberkepentingan";
	public static final String ID_SIASATAN = "idSiasatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptaward transientInstance) {
		log.debug("saving Tblpptaward instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptaward persistentInstance) {
		log.debug("deleting Tblpptaward instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptaward findById(java.lang.Long id) {
		log.debug("getting Tblpptaward instance with id: " + id);
		try {
			Tblpptaward instance = (Tblpptaward) getSession().get(
					"ekptg.model.entities.Tblpptaward", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptaward instance) {
		log.debug("finding Tblpptaward instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptaward").add(
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
		log.debug("finding Tblpptaward instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblpptaward as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJenisAward(Object jenisAward) {
		return findByProperty(JENIS_AWARD, jenisAward);
	}

	public List findByBayarFee(Object bayarFee) {
		return findByProperty(BAYAR_FEE, bayarFee);
	}

	public List findByBayarPampasan(Object bayarPampasan) {
		return findByProperty(BAYAR_PAMPASAN, bayarPampasan);
	}

	public List findByBayarBangunan(Object bayarBangunan) {
		return findByProperty(BAYAR_BANGUNAN, bayarBangunan);
	}

	public List findByBayarBetterment(Object bayarBetterment) {
		return findByProperty(BAYAR_BETTERMENT, bayarBetterment);
	}

	public List findByBayarPecahpisah(Object bayarPecahpisah) {
		return findByProperty(BAYAR_PECAHPISAH, bayarPecahpisah);
	}

	public List findByBayarPenjejasan(Object bayarPenjejasan) {
		return findByProperty(BAYAR_PENJEJASAN, bayarPenjejasan);
	}

	public List findByBayarPendapatan(Object bayarPendapatan) {
		return findByProperty(BAYAR_PENDAPATAN, bayarPendapatan);
	}

	public List findByBayarPindah(Object bayarPindah) {
		return findByProperty(BAYAR_PINDAH, bayarPindah);
	}

	public List findByBayarSewa(Object bayarSewa) {
		return findByProperty(BAYAR_SEWA, bayarSewa);
	}

	public List findByBayarLain2(Object bayarLain2) {
		return findByProperty(BAYAR_LAIN2, bayarLain2);
	}

	public List findByLuasAmbil(Object luasAmbil) {
		return findByProperty(LUAS_AMBIL, luasAmbil);
	}

	public List findByIdUnitluasAmbil(Object idUnitluasAmbil) {
		return findByProperty(ID_UNITLUAS_AMBIL, idUnitluasAmbil);
	}

	public List findByNilaiSeunitAmbil(Object nilaiSeunitAmbil) {
		return findByProperty(NILAI_SEUNIT_AMBIL, nilaiSeunitAmbil);
	}

	public List findByUnitNilaiAmbil(Object unitNilaiAmbil) {
		return findByProperty(UNIT_NILAI_AMBIL, unitNilaiAmbil);
	}

	public List findByStatusPenerima(Object statusPenerima) {
		return findByProperty(STATUS_PENERIMA, statusPenerima);
	}

	public List findByDendaLewat(Object dendaLewat) {
		return findByProperty(DENDA_LEWAT, dendaLewat);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByFaedahSelepasPu(Object faedahSelepasPu) {
		return findByProperty(FAEDAH_SELEPAS_PU, faedahSelepasPu);
	}

	public List findByFaedahSebelumPu(Object faedahSebelumPu) {
		return findByProperty(FAEDAH_SEBELUM_PU, faedahSebelumPu);
	}

	public List findByFlagSerahCek(Object flagSerahCek) {
		return findByProperty(FLAG_SERAH_CEK, flagSerahCek);
	}

	public List findByKeputusanTawaran(Object keputusanTawaran) {
		return findByProperty(KEPUTUSAN_TAWARAN, keputusanTawaran);
	}

	public List findByTempohPampasan(Object tempohPampasan) {
		return findByProperty(TEMPOH_PAMPASAN, tempohPampasan);
	}

	public List findByUnitTempoh(Object unitTempoh) {
		return findByProperty(UNIT_TEMPOH, unitTempoh);
	}

	public List findByIdPihakberkepentingan(Object idPihakberkepentingan) {
		return findByProperty(ID_PIHAKBERKEPENTINGAN, idPihakberkepentingan);
	}

	public List findByIdSiasatan(Object idSiasatan) {
		return findByProperty(ID_SIASATAN, idSiasatan);
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
		log.debug("finding all Tblpptaward instances");
		try {
			String queryString = "from Tblpptaward";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptaward merge(Tblpptaward detachedInstance) {
		log.debug("merging Tblpptaward instance");
		try {
			Tblpptaward result = (Tblpptaward) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptaward instance) {
		log.debug("attaching dirty Tblpptaward instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptaward instance) {
		log.debug("attaching clean Tblpptaward instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}