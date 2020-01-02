package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphplaporanpasir entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphplaporanpasir
 * @author MyEclipse Persistence Tools
 */

public class TblphplaporanpasirDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphplaporanpasirDAO.class);
	// property constants
	public static final String BULAN_PENGAMBILAN = "bulanPengambilan";
	public static final String TAHUN_PENGAMBILAN = "tahunPengambilan";
	public static final String JUMLAH_KUANTITI = "jumlahKuantiti";
	public static final String ID_UNITISIPADU = "idUnitisipadu";
	public static final String JUMLAH_ROYALTI = "jumlahRoyalti";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphplaporanpasir transientInstance) {
		log.debug("saving Tblphplaporanpasir instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphplaporanpasir persistentInstance) {
		log.debug("deleting Tblphplaporanpasir instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphplaporanpasir findById(java.lang.Long id) {
		log.debug("getting Tblphplaporanpasir instance with id: " + id);
		try {
			Tblphplaporanpasir instance = (Tblphplaporanpasir) getSession()
					.get("ekptg.model.entities.Tblphplaporanpasir", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphplaporanpasir instance) {
		log.debug("finding Tblphplaporanpasir instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphplaporanpasir").add(
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
		log.debug("finding Tblphplaporanpasir instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphplaporanpasir as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBulanPengambilan(Object bulanPengambilan) {
		return findByProperty(BULAN_PENGAMBILAN, bulanPengambilan);
	}

	public List findByTahunPengambilan(Object tahunPengambilan) {
		return findByProperty(TAHUN_PENGAMBILAN, tahunPengambilan);
	}

	public List findByJumlahKuantiti(Object jumlahKuantiti) {
		return findByProperty(JUMLAH_KUANTITI, jumlahKuantiti);
	}

	public List findByIdUnitisipadu(Object idUnitisipadu) {
		return findByProperty(ID_UNITISIPADU, idUnitisipadu);
	}

	public List findByJumlahRoyalti(Object jumlahRoyalti) {
		return findByProperty(JUMLAH_ROYALTI, jumlahRoyalti);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphplaporanpasir instances");
		try {
			String queryString = "from Tblphplaporanpasir";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphplaporanpasir merge(Tblphplaporanpasir detachedInstance) {
		log.debug("merging Tblphplaporanpasir instance");
		try {
			Tblphplaporanpasir result = (Tblphplaporanpasir) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphplaporanpasir instance) {
		log.debug("attaching dirty Tblphplaporanpasir instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphplaporanpasir instance) {
		log.debug("attaching clean Tblphplaporanpasir instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}