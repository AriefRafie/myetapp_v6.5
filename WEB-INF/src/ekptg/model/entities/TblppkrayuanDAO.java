package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkrayuan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkrayuan
 * @author MyEclipse Persistence Tools
 */

public class TblppkrayuanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkrayuanDAO.class);
	// property constants
	public static final String MAKLUMAT_PERAYU = "maklumatPerayu";
	public static final String PERKARA_RAYU = "perkaraRayu";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_NEGERIUNITPSK = "idNegeriunitpsk";
	public static final String KEPUTUSAN = "keputusan";
	public static final String ITEM01 = "item01";
	public static final String ITEM02 = "item02";
	public static final String ITEM03 = "item03";
	public static final String ITEM04 = "item04";
	public static final String ITEM05 = "item05";
	public static final String ITEM06 = "item06";
	public static final String ITEM07 = "item07";
	public static final String ITEM08 = "item08";
	public static final String ITEM09 = "item09";
	public static final String CATATAN = "catatan";
	public static final String ID_MAHKAMAH = "idMahkamah";
	public static final String ID_NEGERIMAH = "idNegerimah";
	public static final String NAMA_PUU = "namaPuu";
	public static final String ALAMAT_PUU1 = "alamatPuu1";
	public static final String ALAMAT_PUU2 = "alamatPuu2";
	public static final String ALAMAT_PUU3 = "alamatPuu3";
	public static final String BANDAR = "bandar";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERIPUU = "idNegeripuu";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkrayuan transientInstance) {
		log.debug("saving Tblppkrayuan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkrayuan persistentInstance) {
		log.debug("deleting Tblppkrayuan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkrayuan findById(java.lang.Long id) {
		log.debug("getting Tblppkrayuan instance with id: " + id);
		try {
			Tblppkrayuan instance = (Tblppkrayuan) getSession().get(
					"ekptg.model.entities.Tblppkrayuan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkrayuan instance) {
		log.debug("finding Tblppkrayuan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkrayuan").add(
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
		log.debug("finding Tblppkrayuan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkrayuan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMaklumatPerayu(Object maklumatPerayu) {
		return findByProperty(MAKLUMAT_PERAYU, maklumatPerayu);
	}

	public List findByPerkaraRayu(Object perkaraRayu) {
		return findByProperty(PERKARA_RAYU, perkaraRayu);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdNegeriunitpsk(Object idNegeriunitpsk) {
		return findByProperty(ID_NEGERIUNITPSK, idNegeriunitpsk);
	}

	public List findByKeputusan(Object keputusan) {
		return findByProperty(KEPUTUSAN, keputusan);
	}

	public List findByItem01(Object item01) {
		return findByProperty(ITEM01, item01);
	}

	public List findByItem02(Object item02) {
		return findByProperty(ITEM02, item02);
	}

	public List findByItem03(Object item03) {
		return findByProperty(ITEM03, item03);
	}

	public List findByItem04(Object item04) {
		return findByProperty(ITEM04, item04);
	}

	public List findByItem05(Object item05) {
		return findByProperty(ITEM05, item05);
	}

	public List findByItem06(Object item06) {
		return findByProperty(ITEM06, item06);
	}

	public List findByItem07(Object item07) {
		return findByProperty(ITEM07, item07);
	}

	public List findByItem08(Object item08) {
		return findByProperty(ITEM08, item08);
	}

	public List findByItem09(Object item09) {
		return findByProperty(ITEM09, item09);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdMahkamah(Object idMahkamah) {
		return findByProperty(ID_MAHKAMAH, idMahkamah);
	}

	public List findByIdNegerimah(Object idNegerimah) {
		return findByProperty(ID_NEGERIMAH, idNegerimah);
	}

	public List findByNamaPuu(Object namaPuu) {
		return findByProperty(NAMA_PUU, namaPuu);
	}

	public List findByAlamatPuu1(Object alamatPuu1) {
		return findByProperty(ALAMAT_PUU1, alamatPuu1);
	}

	public List findByAlamatPuu2(Object alamatPuu2) {
		return findByProperty(ALAMAT_PUU2, alamatPuu2);
	}

	public List findByAlamatPuu3(Object alamatPuu3) {
		return findByProperty(ALAMAT_PUU3, alamatPuu3);
	}

	public List findByBandar(Object bandar) {
		return findByProperty(BANDAR, bandar);
	}

	public List findByPoskod(Object poskod) {
		return findByProperty(POSKOD, poskod);
	}

	public List findByIdNegeripuu(Object idNegeripuu) {
		return findByProperty(ID_NEGERIPUU, idNegeripuu);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkrayuan instances");
		try {
			String queryString = "from Tblppkrayuan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkrayuan merge(Tblppkrayuan detachedInstance) {
		log.debug("merging Tblppkrayuan instance");
		try {
			Tblppkrayuan result = (Tblppkrayuan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkrayuan instance) {
		log.debug("attaching dirty Tblppkrayuan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkrayuan instance) {
		log.debug("attaching clean Tblppkrayuan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}