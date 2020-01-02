package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblunitorganisasi entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblunitorganisasi
 * @author MyEclipse Persistence Tools
 */

public class TblunitorganisasiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblunitorganisasiDAO.class);
	// property constants
	public static final String KOD_UNIT_ORGANISASI = "kodUnitOrganisasi";
	public static final String NAMA_UNIT_ORGANISASI = "namaUnitOrganisasi";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String BANDAR = "bandar";
	public static final String KOD_MPB = "kodMpb";
	public static final String KOD_NEGERI = "kodNegeri";
	public static final String KOD_NEGARA = "kodNegara";
	public static final String NO_TELEFON = "noTelefon";
	public static final String NO_FAK = "noFak";
	public static final String EMAIL = "email";
	public static final String KOD_STATUS = "kodStatus";

	public void save(Tblunitorganisasi transientInstance) {
		log.debug("saving Tblunitorganisasi instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblunitorganisasi persistentInstance) {
		log.debug("deleting Tblunitorganisasi instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblunitorganisasi findById(java.lang.Long id) {
		log.debug("getting Tblunitorganisasi instance with id: " + id);
		try {
			Tblunitorganisasi instance = (Tblunitorganisasi) getSession().get(
					"ekptg.model.entities.Tblunitorganisasi", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblunitorganisasi instance) {
		log.debug("finding Tblunitorganisasi instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblunitorganisasi").add(
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
		log.debug("finding Tblunitorganisasi instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblunitorganisasi as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodUnitOrganisasi(Object kodUnitOrganisasi) {
		return findByProperty(KOD_UNIT_ORGANISASI, kodUnitOrganisasi);
	}

	public List findByNamaUnitOrganisasi(Object namaUnitOrganisasi) {
		return findByProperty(NAMA_UNIT_ORGANISASI, namaUnitOrganisasi);
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

	public List findByNoTelefon(Object noTelefon) {
		return findByProperty(NO_TELEFON, noTelefon);
	}

	public List findByNoFak(Object noFak) {
		return findByProperty(NO_FAK, noFak);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByKodStatus(Object kodStatus) {
		return findByProperty(KOD_STATUS, kodStatus);
	}

	public List findAll() {
		log.debug("finding all Tblunitorganisasi instances");
		try {
			String queryString = "from Tblunitorganisasi";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblunitorganisasi merge(Tblunitorganisasi detachedInstance) {
		log.debug("merging Tblunitorganisasi instance");
		try {
			Tblunitorganisasi result = (Tblunitorganisasi) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblunitorganisasi instance) {
		log.debug("attaching dirty Tblunitorganisasi instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblunitorganisasi instance) {
		log.debug("attaching clean Tblunitorganisasi instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}