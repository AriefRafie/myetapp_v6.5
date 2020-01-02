package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphppmohonnjdualpertama entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphppmohonnjdualpertama
 * @author MyEclipse Persistence Tools
 */

public class TblphppmohonnjdualpertamaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphppmohonnjdualpertamaDAO.class);
	// property constants
	public static final String ID_FAIL = "idFail";
	public static final String ID_JENISTUJUAN = "idJenistujuan";
	public static final String NAMA_PROJEK = "namaProjek";
	public static final String FLAG_JENIS_PERJANJIAN = "flagJenisPerjanjian";
	public static final String ANTARA = "antara";
	public static final String DAN_SIAPA = "danSiapa";
	public static final String DARIPADA = "daripada";
	public static final String MODAL_SEMASA = "modalSemasa";
	public static final String MODAL_SEDIA = "modalSedia";
	public static final String PENGALAMAN = "pengalaman";
	public static final String LOKASI_PERMOHONAN = "lokasiPermohonan";
	public static final String TEMPOH_DIPOHON = "tempohDipohon";
	public static final String ID_TEMPOH = "idTempoh";
	public static final String BIL_TITIK = "bilTitik";
	public static final String ID_UNITISIPADU = "idUnitisipadu";
	public static final String ISIPADU = "isipadu";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_URUSAN = "idUrusan";
	public static final String ID_STATUS = "idStatus";

	public void save(Tblphppmohonnjdualpertama transientInstance) {
		log.debug("saving Tblphppmohonnjdualpertama instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphppmohonnjdualpertama persistentInstance) {
		log.debug("deleting Tblphppmohonnjdualpertama instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphppmohonnjdualpertama findById(java.lang.Long id) {
		log.debug("getting Tblphppmohonnjdualpertama instance with id: " + id);
		try {
			Tblphppmohonnjdualpertama instance = (Tblphppmohonnjdualpertama) getSession()
					.get("ekptg.model.entities.Tblphppmohonnjdualpertama", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphppmohonnjdualpertama instance) {
		log.debug("finding Tblphppmohonnjdualpertama instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphppmohonnjdualpertama").add(
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
		log.debug("finding Tblphppmohonnjdualpertama instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphppmohonnjdualpertama as model where model."
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

	public List findByIdJenistujuan(Object idJenistujuan) {
		return findByProperty(ID_JENISTUJUAN, idJenistujuan);
	}

	public List findByNamaProjek(Object namaProjek) {
		return findByProperty(NAMA_PROJEK, namaProjek);
	}

	public List findByFlagJenisPerjanjian(Object flagJenisPerjanjian) {
		return findByProperty(FLAG_JENIS_PERJANJIAN, flagJenisPerjanjian);
	}

	public List findByAntara(Object antara) {
		return findByProperty(ANTARA, antara);
	}

	public List findByDanSiapa(Object danSiapa) {
		return findByProperty(DAN_SIAPA, danSiapa);
	}

	public List findByDaripada(Object daripada) {
		return findByProperty(DARIPADA, daripada);
	}

	public List findByModalSemasa(Object modalSemasa) {
		return findByProperty(MODAL_SEMASA, modalSemasa);
	}

	public List findByModalSedia(Object modalSedia) {
		return findByProperty(MODAL_SEDIA, modalSedia);
	}

	public List findByPengalaman(Object pengalaman) {
		return findByProperty(PENGALAMAN, pengalaman);
	}

	public List findByLokasiPermohonan(Object lokasiPermohonan) {
		return findByProperty(LOKASI_PERMOHONAN, lokasiPermohonan);
	}

	public List findByTempohDipohon(Object tempohDipohon) {
		return findByProperty(TEMPOH_DIPOHON, tempohDipohon);
	}

	public List findByIdTempoh(Object idTempoh) {
		return findByProperty(ID_TEMPOH, idTempoh);
	}

	public List findByBilTitik(Object bilTitik) {
		return findByProperty(BIL_TITIK, bilTitik);
	}

	public List findByIdUnitisipadu(Object idUnitisipadu) {
		return findByProperty(ID_UNITISIPADU, idUnitisipadu);
	}

	public List findByIsipadu(Object isipadu) {
		return findByProperty(ISIPADU, isipadu);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdUrusan(Object idUrusan) {
		return findByProperty(ID_URUSAN, idUrusan);
	}

	public List findByIdStatus(Object idStatus) {
		return findByProperty(ID_STATUS, idStatus);
	}

	public List findAll() {
		log.debug("finding all Tblphppmohonnjdualpertama instances");
		try {
			String queryString = "from Tblphppmohonnjdualpertama";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphppmohonnjdualpertama merge(
			Tblphppmohonnjdualpertama detachedInstance) {
		log.debug("merging Tblphppmohonnjdualpertama instance");
		try {
			Tblphppmohonnjdualpertama result = (Tblphppmohonnjdualpertama) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphppmohonnjdualpertama instance) {
		log.debug("attaching dirty Tblphppmohonnjdualpertama instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphppmohonnjdualpertama instance) {
		log.debug("attaching clean Tblphppmohonnjdualpertama instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}