package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblorganisasi entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblorganisasi
 * @author MyEclipse Persistence Tools
 */

public class TblorganisasiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblorganisasiDAO.class);
	// property constants
	public static final String KOD_ORGANISASI = "kodOrganisasi";
	public static final String NAMA_ORGANISASI = "namaOrganisasi";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String BANDAR = "bandar";
	public static final String KOD_MPB = "kodMpb";
	public static final String KOD_NEGERI = "kodNegeri";
	public static final String KOD_NEGARA = "kodNegara";
	public static final String KOD_STATUS = "kodStatus";
	public static final String NO_TELEFON = "noTelefon";
	public static final String NO_FAK = "noFak";
	public static final String ALAMAT_WEB = "alamatWeb";
	public static final String EMAIL = "email";

	public void save(Tblorganisasi transientInstance) {
		log.debug("saving Tblorganisasi instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblorganisasi persistentInstance) {
		log.debug("deleting Tblorganisasi instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblorganisasi findById(java.lang.Long id) {
		log.debug("getting Tblorganisasi instance with id: " + id);
		try {
			Tblorganisasi instance = (Tblorganisasi) getSession().get(
					"ekptg.model.entities.Tblorganisasi", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblorganisasi instance) {
		log.debug("finding Tblorganisasi instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblorganisasi").add(
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
		log.debug("finding Tblorganisasi instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblorganisasi as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodOrganisasi(Object kodOrganisasi) {
		return findByProperty(KOD_ORGANISASI, kodOrganisasi);
	}

	public List findByNamaOrganisasi(Object namaOrganisasi) {
		return findByProperty(NAMA_ORGANISASI, namaOrganisasi);
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

	public List findByBandar(Object bandar) {
		return findByProperty(BANDAR, bandar);
	}

	public List findByKodMpb(Object kodMpb) {
		return findByProperty(KOD_MPB, kodMpb);
	}

	public List findByKodNegeri(Object kodNegeri) {
		return findByProperty(KOD_NEGERI, kodNegeri);
	}

	public List findByKodNegara(Object kodNegara) {
		return findByProperty(KOD_NEGARA, kodNegara);
	}

	public List findByKodStatus(Object kodStatus) {
		return findByProperty(KOD_STATUS, kodStatus);
	}

	public List findByNoTelefon(Object noTelefon) {
		return findByProperty(NO_TELEFON, noTelefon);
	}

	public List findByNoFak(Object noFak) {
		return findByProperty(NO_FAK, noFak);
	}

	public List findByAlamatWeb(Object alamatWeb) {
		return findByProperty(ALAMAT_WEB, alamatWeb);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findAll() {
		log.debug("finding all Tblorganisasi instances");
		try {
			String queryString = "from Tblorganisasi";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblorganisasi merge(Tblorganisasi detachedInstance) {
		log.debug("merging Tblorganisasi instance");
		try {
			Tblorganisasi result = (Tblorganisasi) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblorganisasi instance) {
		log.debug("attaching dirty Tblorganisasi instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblorganisasi instance) {
		log.debug("attaching clean Tblorganisasi instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}