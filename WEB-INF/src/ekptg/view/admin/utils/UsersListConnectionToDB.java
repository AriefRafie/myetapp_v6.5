package ekptg.view.admin.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.util.PasswordService;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

/**
 * 
 * @author Mohd Nazrul
 * @version 1.0
 * 
 */

public class UsersListConnectionToDB {
	static Logger myLogger = Logger.getLogger(UsersListConnectionToDB.class);
	/**
	 * This method executes the list of users
	 * 
	 * @return {@link List}
	 * @exception Exception
	 */
	public List<Users> listUsersById(String user_id) throws Exception {

		List<Users> result = new ArrayList<Users>();
		Users users = null;
		Converter converter = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT * FROM USERS WHERE USER_ID = '" + user_id
					+ "' ";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				users = new Users(converter.getStringValue(resultSet
						.getString("USER_ID")), converter
						.getStringValue(resultSet.getString("USER_LOGIN")),
						converter.getStringValue(resultSet
								.getString("USER_PASSWORD")), converter
								.getStringValue(resultSet
										.getString("USER_NAME")), converter
								.getStringValue(resultSet
										.getString("USER_ROLE")), converter
								.getStringValue(resultSet
										.getString("DATE_REGISTERED")),
						converter.getStringValue(resultSet
								.getString("USER_TYPE")),
						converter.getStringValue(resultSet
								.getString("ID_MASUK")), converter
								.getStringValue(resultSet
										.getString("TARIKH_MASUK")), converter
								.getStringValue(resultSet
										.getString("ID_KEMASKINI")), converter
								.getStringValue(resultSet
										.getString("TARIKH_KEMASKINI")));

				result.add(users);

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSAGE (listUsers) [testing.java]: "
					+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * This method executes the list of users
	 * 
	 * @return {@link List}
	 * @exception Exception
	 */
	public List<UsersOnline> listUsers() throws Exception {

		List<UsersOnline> result = new ArrayList<UsersOnline>();
		UsersOnline listUsers = null;
		UsersOnline listUsers2 = null;
		Converter converter = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_NEGERI, "
					+ "C.NAMA_NEGERI FROM USERS A, USERS_ONLINE B, TBLRUJNEGERI C "
					+ "WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				listUsers = new UsersOnline(converter.getStringValue(resultSet
						.getString("USER_ID")), converter
						.getStringValue(resultSet.getString("USER_LOGIN")),
						converter.getStringValue(resultSet
								.getString("USER_NAME")), converter
								.getStringValue(resultSet
										.getString("USER_ROLE")), converter
								.getStringValue(resultSet
										.getString("USER_TYPE")), converter
								.getStringValue(resultSet
										.getString("ID_NEGERI")), converter
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")));
				result.add(listUsers);

			}

			Statement statement2 = db.getStatement();
			String sql2 = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_NEGERI, "
					+ "C.NAMA_NEGERI FROM USERS A, USERS_INTERNAL B, TBLRUJNEGERI C "
					+ "WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI";
			resultSet = statement2.executeQuery(sql2);

			while (resultSet.next()) {

				listUsers2 = new UsersOnline(converter.getStringValue(resultSet
						.getString("USER_ID")), converter
						.getStringValue(resultSet.getString("USER_LOGIN")),
						converter.getStringValue(resultSet
								.getString("USER_NAME")), converter
								.getStringValue(resultSet
										.getString("USER_ROLE")), converter
								.getStringValue(resultSet
										.getString("USER_TYPE")), converter
								.getStringValue(resultSet
										.getString("ID_NEGERI")), converter
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")));
				result.add(listUsers2);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSAGE (listUsers) [testing.java]: "
					+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b>This method executes the list of online users by user id</b>
	 * 
	 * @param user_id
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public UsersOnlineDetail listUsersOnlineByUserId(String user_id)
			throws Exception {

		UsersOnlineDetail usersOnlineDetail = null;
		Converter converter = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_PASSWORD, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ALAMAT1, B.ALAMAT2, "
					+ "B.ALAMAT3, B.ID_NEGERI, C.NAMA_NEGERI , B.POSKOD, B.EMEL, B.NO_HP, B.NO_TEL, B.NO_FAX, B.NO_KP_BARU, "
					+ "B.UMUR, B.JANTINA, B.TARAF_PERKAHWINAN, TO_CHAR(B.TARIKH_LAHIR, 'DD/MM/YYYY') AS TARIKH_LAHIR FROM USERS A, USERS_ONLINE B, TBLRUJNEGERI C "
					+ "WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND A.USER_ID = '"
					+ user_id + "'";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				usersOnlineDetail = new UsersOnlineDetail(
						converter
								.getStringValue(resultSet.getString("USER_ID")),
						converter.getStringValue(resultSet
								.getString("USER_LOGIN")),
						converter.getStringValue(resultSet
								.getString("USER_PASSWORD")),
						converter.getStringValue(resultSet
								.getString("USER_NAME")),
						converter.getStringValue(resultSet
								.getString("USER_ROLE")),
						converter.getStringValue(resultSet
								.getString("USER_TYPE")),
						converter
								.getStringValue(resultSet.getString("ALAMAT1")),
						converter
								.getStringValue(resultSet.getString("ALAMAT2")),
						converter
								.getStringValue(resultSet.getString("ALAMAT3")),
						converter.getStringValue(resultSet
								.getString("ID_NEGERI")),
						converter.getStringValue(resultSet
								.getString("NAMA_NEGERI")),
						converter.getStringValue(resultSet.getString("POSKOD")),
						converter.getStringValue(resultSet.getString("EMEL")),
						converter.getStringValue(resultSet.getString("NO_HP")),
						converter.getStringValue(resultSet.getString("NO_TEL")),
						converter.getStringValue(resultSet.getString("NO_FAX")),
						converter.getStringValue(resultSet
								.getString("NO_KP_BARU")),
						converter.getStringValue(resultSet.getString("UMUR")),
						converter
								.getStringValue(resultSet.getString("JANTINA")),
						converter.getStringValue(resultSet
								.getString("TARAF_PERKAHWINAN")), converter
								.getStringValue(resultSet
										.getString("TARIKH_LAHIR")));

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersOnlineById) [testing.java]: "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return usersOnlineDetail;
	}

	/**
	 * <b>This method executes the list of online users by user id</b>
	 * 
	 * @param user_id
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersOnline> listUsersOnlineById(String user_id)
			throws Exception {

		List<UsersOnline> result = new ArrayList<UsersOnline>();
		UsersOnline usersOnline = null;
		Converter converter = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_NEGERI, C.NAMA_NEGERI "
					+ "FROM USERS A, USERS_ONLINE B, TBLRUJNEGERI C "
					+ "WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND LOWER(A.USER_LOGIN) LIKE '%"
					+ user_id + "%' ";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				usersOnline = new UsersOnline(converter
						.getStringValue(resultSet.getString("USER_ID")),
						converter.getStringValue(resultSet
								.getString("USER_LOGIN")), converter
								.getStringValue(resultSet
										.getString("USER_NAME")), converter
								.getStringValue(resultSet
										.getString("USER_ROLE")), converter
								.getStringValue(resultSet
										.getString("USER_TYPE")), converter
								.getStringValue(resultSet
										.getString("ID_NEGERI")), converter
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")));

				result.add(usersOnline);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersOnlineById) [testing.java]: "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b>This method executes the list of online users by user id</b>
	 * 
	 * @param user_id
	 *            , id_negeri
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersOnline> listUsersOnlineById(String user_id,
			String id_negeri) throws Exception {

		List<UsersOnline> result = new ArrayList<UsersOnline>();
		UsersOnline usersOnline = null;
		Converter converter = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_NEGERI, C.NAMA_NEGERI "
					+ "FROM USERS A, USERS_ONLINE B, TBLRUJNEGERI C "
					+ "WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND LOWER(A.USER_LOGIN) LIKE '%"
					+ user_id
					+ "%' "
					+ "AND B.ID_NEGERI = '"
					+ id_negeri
					+ "' ";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				usersOnline = new UsersOnline(converter
						.getStringValue(resultSet.getString("USER_ID")),
						converter.getStringValue(resultSet
								.getString("USER_LOGIN")), converter
								.getStringValue(resultSet
										.getString("USER_NAME")), converter
								.getStringValue(resultSet
										.getString("USER_ROLE")), converter
								.getStringValue(resultSet
										.getString("USER_TYPE")), converter
								.getStringValue(resultSet
										.getString("ID_NEGERI")), converter
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")));

				result.add(usersOnline);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersOnlineById) [testing.java]: "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b>This method executes the list of online users by user id</b>
	 * 
	 * @param user_id
	 *            , id_negeri, user_role
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersOnline> listUsersOnlineById(String user_id,
			String id_negeri, String user_role) throws Exception {

		List<UsersOnline> result = new ArrayList<UsersOnline>();
		UsersOnline usersOnline = null;
		Converter converter = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_NEGERI, C.NAMA_NEGERI "
					+ "FROM USERS A, USERS_ONLINE B, TBLRUJNEGERI C "
					+ "WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI " +
							"AND LOWER(A.USER_LOGIN) LIKE '%"+ user_id + "%' ";
			if (!"".equals(id_negeri)) {
				sql = sql + "AND B.ID_NEGERI = '"	+ id_negeri	+ "' ";	
			}
			if (!"".equals(user_role)) {
				sql = sql + "AND A.USER_ROLE = '" + user_role + "' ";
			}
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				usersOnline = new UsersOnline(converter
						.getStringValue(resultSet.getString("USER_ID")),
						converter.getStringValue(resultSet
								.getString("USER_LOGIN")), converter
								.getStringValue(resultSet
										.getString("USER_NAME")), converter
								.getStringValue(resultSet
										.getString("USER_ROLE")), converter
								.getStringValue(resultSet
										.getString("USER_TYPE")), converter
								.getStringValue(resultSet
										.getString("ID_NEGERI")), converter
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")));

				result.add(usersOnline);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersOnlineById) [testing.java]: "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b>This method executes the list of online users by user name </b>
	 * 
	 * @param user_name
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersOnline> listUsersOnlineByName(String user_name)
			throws Exception {

		List<UsersOnline> result = new ArrayList<UsersOnline>();
		UsersOnline usersOnline = null;
		Converter converter = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_NEGERI, C.NAMA_NEGERI "
					+ "FROM USERS A, USERS_ONLINE B, TBLRUJNEGERI C "
					+ "WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND LOWER(A.USER_NAME) LIKE '%"
					+ user_name + "%' ";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				usersOnline = new UsersOnline(converter
						.getStringValue(resultSet.getString("USER_ID")),
						converter.getStringValue(resultSet
								.getString("USER_LOGIN")), converter
								.getStringValue(resultSet
										.getString("USER_NAME")), converter
								.getStringValue(resultSet
										.getString("USER_ROLE")), converter
								.getStringValue(resultSet
										.getString("USER_TYPE")), converter
								.getStringValue(resultSet
										.getString("ID_NEGERI")), converter
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")));

				result.add(usersOnline);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersOnlineByName) [testing.java]: "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b>This method executes the list of online users by user name </b>
	 * 
	 * @param user_name
	 *            , id_negeri
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersOnline> listUsersOnlineByName(String user_name,
			String id_negeri) throws Exception {

		List<UsersOnline> result = new ArrayList<UsersOnline>();
		UsersOnline usersOnline = null;
		Converter converter = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_NEGERI, C.NAMA_NEGERI "
					+ "FROM USERS A, USERS_ONLINE B, TBLRUJNEGERI C "
					+ "WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND LOWER(A.USER_NAME) LIKE '%"
					+ user_name
					+ "%' "
					+ "AND B.ID_NEGERI = '"
					+ id_negeri
					+ "' ";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				usersOnline = new UsersOnline(converter
						.getStringValue(resultSet.getString("USER_ID")),
						converter.getStringValue(resultSet
								.getString("USER_LOGIN")), converter
								.getStringValue(resultSet
										.getString("USER_NAME")), converter
								.getStringValue(resultSet
										.getString("USER_ROLE")), converter
								.getStringValue(resultSet
										.getString("USER_TYPE")), converter
								.getStringValue(resultSet
										.getString("ID_NEGERI")), converter
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")));

				result.add(usersOnline);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersOnlineByName) [testing.java]: "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b>This method executes the list of online users by user name </b>
	 * 
	 * @param user_name
	 *            , id_negeri, user_role
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersOnline> listUsersOnlineByName(String user_name,
			String id_negeri, String user_role) throws Exception {

		List<UsersOnline> result = new ArrayList<UsersOnline>();
		UsersOnline usersOnline = null;
		Converter converter = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_NEGERI, C.NAMA_NEGERI "
					+ "FROM USERS A, USERS_ONLINE B, TBLRUJNEGERI C "
					+ "WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI " +
					"AND LOWER(A.USER_NAME) LIKE '%"+ user_name	+ "%' ";
					
					if (!"".equals(id_negeri)) {
						sql = sql + "AND B.ID_NEGERI = '"	+ id_negeri	+ "' ";	
					}
					if (!"".equals(user_role)) {
						sql = sql + "AND A.USER_ROLE = '" + user_role + "' ";
					}

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				usersOnline = new UsersOnline(converter
						.getStringValue(resultSet.getString("USER_ID")),
						converter.getStringValue(resultSet
								.getString("USER_LOGIN")), converter
								.getStringValue(resultSet
										.getString("USER_NAME")), converter
								.getStringValue(resultSet
										.getString("USER_ROLE")), converter
								.getStringValue(resultSet
										.getString("USER_TYPE")), converter
								.getStringValue(resultSet
										.getString("ID_NEGERI")), converter
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")));

				result.add(usersOnline);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersOnlineByName) [testing.java]: "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b> This method executes the list of internal users by userId </b>
	 * 
	 * @param userId
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersInternal> listUsersInternalById(String userId)
			throws Exception {

		List<UsersInternal> result = new ArrayList<UsersInternal>();
		UsersInternal usersInternal = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_SEKSYEN,"
					+ " D.NAMA_SEKSYEN, B.ID_NEGERI, C.NAMA_NEGERI, B.ID_DAERAH, E.NAMA_DAERAH, B.ID_PEJABATJKPTG, "
					+ "F.NAMA_PEJABAT FROM USERS A, USERS_INTERNAL B, TBLRUJNEGERI C, TBLRUJSEKSYEN D, TBLRUJDAERAH  E,"
					+ " TBLRUJPEJABATJKPTG F WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND B.ID_SEKSYEN = D.ID_SEKSYEN"
					+ " AND B.ID_DAERAH = E.ID_DAERAH AND B.ID_PEJABATJKPTG = F.ID_PEJABATJKPTG AND LOWER(A.USER_LOGIN) LIKE '%"
					+ userId + "%'";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				usersInternal = new UsersInternal(

				coventer.getStringValue(resultSet.getString("USER_ID")),
						coventer.getStringValue(resultSet
								.getString("USER_LOGIN")), coventer
								.getStringValue(resultSet
										.getString("USER_NAME")), coventer
								.getStringValue(resultSet
										.getString("USER_ROLE")), coventer
								.getStringValue(resultSet
										.getString("USER_TYPE")), coventer
								.getStringValue(resultSet
										.getString("ID_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("NAMA_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("ID_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("NAMA_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("ID_PEJABATJKPTG")),
						coventer.getStringValue(resultSet
								.getString("NAMA_PEJABAT")), "", "");

				result.add(usersInternal);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersInternalById)[testing.java]"
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b> This method executes the list of internal users by userId </b>
	 * 
	 * @param userId
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public UsersInternalDetail listUsersInternalByUserId(String userId)
			throws Exception {


		UsersInternalDetail uid = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT U.USER_ID, U.USER_LOGIN, U.USER_NAME, U.USER_ROLE, U.USER_TYPE, UI.ID_JAWATAN, "
					+ "UI.ID_NEGERI, NE.KOD_NEGERI, NE.NAMA_NEGERI, UI.ID_SEKSYEN, SE.KOD_SEKSYEN, SE.NAMA_SEKSYEN, UI.ID_PEJABATJKPTG, "
					+ "PE.NAMA_PEJABAT, PE.ALAMAT1, PE.ALAMAT2, PE.ALAMAT3, PE.ID_BANDAR, PE.ID_DAERAH, DA.NAMA_DAERAH, "
					+ "PE.POSKOD, PE.NO_TEL, PE.NO_FAX, UI.ID_AGAMA, UI.ID_BANGSA,UI.EMEL "
					+ "FROM USERS U, USERS_INTERNAL UI , TBLRUJNEGERI NE, TBLRUJSEKSYEN SE, TBLRUJPEJABATJKPTG  PE, TBLRUJDAERAH DA "
					+ "WHERE U.USER_ID = UI.USER_ID AND  UI.ID_NEGERI = NE.ID_NEGERI (+) " +
							"AND UI.ID_SEKSYEN = SE.ID_SEKSYEN(+) "
					+ "AND UI.ID_PEJABATJKPTG = PE.ID_PEJABATJKPTG (+)  " +
							"AND PE.ID_DAERAH = DA.ID_DAERAH (+) "
					+ "AND U.USER_ID = '"+userId+"' ";
			
			myLogger.debug(sql);
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				String user_id = coventer.getStringValue(resultSet
						.getString("USER_ID"));
				myLogger.debug("user_id:"+user_id);
				String user_login = coventer.getStringValue(resultSet
						.getString("USER_LOGIN"));
				String user_name = coventer.getStringValue(resultSet
						.getString("USER_NAME"));
				String user_role = coventer.getStringValue(resultSet
						.getString("USER_ROLE"));
				String user_type = coventer.getStringValue(resultSet
						.getString("USER_TYPE"));
				String id_jawatan = coventer.getStringValue(resultSet
						.getString("ID_JAWATAN"));
				String id_negeri = coventer.getStringValue(resultSet
						.getString("ID_NEGERI"));
				String kod_negeri = coventer.getStringValue(resultSet
						.getString("KOD_NEGERI"));
				String nama_negeri = coventer.getStringValue(resultSet
						.getString("NAMA_NEGERI"));
				String id_seksyen = coventer.getStringValue(resultSet
						.getString("ID_SEKSYEN"));
				String kod_seksyen = coventer.getStringValue(resultSet
						.getString("KOD_SEKSYEN"));
				String nama_seksyen = coventer.getStringValue(resultSet
						.getString("NAMA_SEKSYEN"));
				String id_pejabatjkptg = coventer.getStringValue(resultSet
						.getString("ID_PEJABATJKPTG"));
				String nama_pejabat = coventer.getStringValue(resultSet
						.getString("NAMA_PEJABAT"));
				String alamat1 = coventer.getStringValue(resultSet
						.getString("ALAMAT1"));
				String alamat2 = coventer.getStringValue(resultSet
						.getString("ALAMAT2"));
				String alamat3 = coventer.getStringValue(resultSet
						.getString("ALAMAT3"));
				String id_bandar = coventer.getStringValue(resultSet
						.getString("ID_BANDAR"));
				String id_daerah = coventer.getStringValue(resultSet
						.getString("ID_DAERAH"));
				String nama_daerah = coventer.getStringValue(resultSet
						.getString("NAMA_DAERAH"));
				String poskod = coventer.getStringValue(resultSet
						.getString("POSKOD"));
				String no_tel = coventer.getStringValue(resultSet
						.getString("NO_TEL"));
				String no_fax = coventer.getStringValue(resultSet
						.getString("NO_FAX"));
				String id_agama = coventer.getStringValue(resultSet
						.getString("ID_AGAMA"));
				String id_bangsa = coventer.getStringValue(resultSet
						.getString("ID_BANGSA"));
				
				String emel = coventer.getStringValue(resultSet
						.getString("emel"));
	
				uid = new UsersInternalDetail(user_id, user_login, "",
						user_name, user_role, user_type, id_seksyen, kod_seksyen,
						nama_seksyen, id_negeri, kod_negeri, nama_negeri,
						id_daerah, "", nama_daerah, id_pejabatjkptg,
						nama_pejabat, alamat1, alamat2, alamat3, poskod,
						no_tel, no_fax, id_bandar, "", "", id_agama, "", "",
						id_bangsa, "", "", id_jawatan, "", "", "",
						"",emel);
//				uid = new UsersInternalDetail(user_id, user_login, user_pass,
//						user_name, user_role, user_type, id_seksyen, kod_skesyen,
//						nama_seksyen, id_negeri, kod_negeri, nama_negeri, id_daerah, 
//						kod_daerah, nama_daerah, id_pejabat, nama_pejabat, p_alamat1,
//						p_alamat2, p_alamat3, poskod, no_tel, no_fax, id_bandar, kod_bandar, 
//						b_keterangan, id_agama, kod_agama, a_keterangan, id_bangsa, kod_bangsa,
//						bg_keterangan, id_jawatan, kod_jawatan, j_keterangan, css_title, css_name);

			}

		} catch (Exception e) {
			e.printStackTrace();
			myLogger.debug("ERROR MESSAGE (listUsersInternalById)[testing.java]"
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return uid;
	}

	/**
	 * <b> This method executes the list of internal users by userId </b>
	 * 
	 * @param userId
	 *            , id_jawatan
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersInternal> listUsersInternalById(String userId,
			String id_jawatan) throws Exception {

		List<UsersInternal> result = new ArrayList<UsersInternal>();
		UsersInternal usersInternal = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_SEKSYEN, D.NAMA_SEKSYEN, "
					+ "B.ID_NEGERI, C.NAMA_NEGERI, B.ID_DAERAH, E.NAMA_DAERAH, B.ID_PEJABATJKPTG, F.NAMA_PEJABAT, B.ID_JAWATAN, "
					+ "G.KETERANGAN FROM USERS A, USERS_INTERNAL B, TBLRUJNEGERI C, TBLRUJSEKSYEN D, TBLRUJDAERAH  E, TBLRUJPEJABATJKPTG F, "
					+ "TBLRUJJAWATAN G WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND B.ID_SEKSYEN = D.ID_SEKSYEN "
					+ "AND B.ID_DAERAH = E.ID_DAERAH AND B.ID_JAWATAN = G.ID_JAWATAN AND B.ID_PEJABATJKPTG = F.ID_PEJABATJKPTG "
					+ "AND LOWER(A.USER_LOGIN) LIKE '%"
					+ userId
					+ "%' AND B.ID_JAWATAN = '" + id_jawatan + "' ";

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				usersInternal = new UsersInternal(

				coventer.getStringValue(resultSet.getString("USER_ID")),
						coventer.getStringValue(resultSet
								.getString("USER_LOGIN")), coventer
								.getStringValue(resultSet
										.getString("USER_NAME")), coventer
								.getStringValue(resultSet
										.getString("USER_ROLE")), coventer
								.getStringValue(resultSet
										.getString("USER_TYPE")), coventer
								.getStringValue(resultSet
										.getString("ID_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("NAMA_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("ID_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("NAMA_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("ID_PEJABATJKPTG")),
						coventer.getStringValue(resultSet
								.getString("NAMA_PEJABAT")), coventer
								.getStringValue(resultSet
										.getString("ID_JAWATAN")), coventer
								.getStringValue(resultSet
										.getString("KETERANGAN")));

				result.add(usersInternal);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersInternalById)[testing.java]"
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b> This method executes the list of internal users by userId </b>
	 * 
	 * @param userId
	 *            , id_jawatan, id_seksyen, id_negeri
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersInternal> listUsersInternalById(String userId,
			String id_jawatan, String id_seksyen, String id_negeri)
			throws Exception {

		List<UsersInternal> result = new ArrayList<UsersInternal>();
		UsersInternal usersInternal = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_SEKSYEN, D.NAMA_SEKSYEN, "
					+ "B.ID_NEGERI, C.NAMA_NEGERI, B.ID_DAERAH, E.NAMA_DAERAH, B.ID_PEJABATJKPTG, F.NAMA_PEJABAT, B.ID_JAWATAN, "
					+ "G.KETERANGAN FROM USERS A, USERS_INTERNAL B, TBLRUJNEGERI C, TBLRUJSEKSYEN D, TBLRUJDAERAH  E, TBLRUJPEJABATJKPTG F, "
					+ "TBLRUJJAWATAN G WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND B.ID_SEKSYEN = D.ID_SEKSYEN "
					+ "AND B.ID_DAERAH = E.ID_DAERAH AND B.ID_JAWATAN = G.ID_JAWATAN AND B.ID_PEJABATJKPTG = F.ID_PEJABATJKPTG "
					+ "AND LOWER(A.USER_LOGIN) LIKE '%"
					+ userId
					+ "%' AND B.ID_JAWATAN = '"
					+ id_jawatan
					+ "' AND B.ID_SEKSYEN = '"
					+ id_seksyen
					+ "' "
					+ "AND B.ID_NEGERI = '" + id_negeri + "' ";

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				usersInternal = new UsersInternal(

				coventer.getStringValue(resultSet.getString("USER_ID")),
						coventer.getStringValue(resultSet
								.getString("USER_LOGIN")), coventer
								.getStringValue(resultSet
										.getString("USER_NAME")), coventer
								.getStringValue(resultSet
										.getString("USER_ROLE")), coventer
								.getStringValue(resultSet
										.getString("USER_TYPE")), coventer
								.getStringValue(resultSet
										.getString("ID_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("NAMA_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("ID_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("NAMA_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("ID_PEJABATJKPTG")),
						coventer.getStringValue(resultSet
								.getString("NAMA_PEJABAT")), coventer
								.getStringValue(resultSet
										.getString("ID_JAWATAN")), coventer
								.getStringValue(resultSet
										.getString("KETERANGAN")));

				result.add(usersInternal);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersInternalById)[testing.java]"
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b> This method executes the list of internal users by userId </b>
	 * 
	 * @param userId
	 *            , id_jawatan, id_seksyen, id-negeri, id_pejabat
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersInternal> listUsersInternalById(String userId,
			String id_jawatan, String id_seksyen, String id_negeri,
			String id_pejabat) throws Exception {

		List<UsersInternal> result = new ArrayList<UsersInternal>();
		UsersInternal usersInternal = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_SEKSYEN, D.NAMA_SEKSYEN, "
					+ "B.ID_NEGERI, C.NAMA_NEGERI, B.ID_DAERAH, E.NAMA_DAERAH, B.ID_PEJABATJKPTG, F.NAMA_PEJABAT, B.ID_JAWATAN, "
					+ "G.KETERANGAN FROM USERS A, USERS_INTERNAL B, TBLRUJNEGERI C, TBLRUJSEKSYEN D, TBLRUJDAERAH  E, TBLRUJPEJABATJKPTG F, "
					+ "TBLRUJJAWATAN G WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND B.ID_SEKSYEN = D.ID_SEKSYEN "
					+ "AND B.ID_DAERAH = E.ID_DAERAH AND B.ID_JAWATAN = G.ID_JAWATAN AND B.ID_PEJABATJKPTG = F.ID_PEJABATJKPTG "
					+ "AND LOWER(A.USER_LOGIN) LIKE '%"
					+ userId
					+ "%' AND B.ID_JAWATAN = '"
					+ id_jawatan
					+ "' AND B.ID_SEKSYEN = '"
					+ id_seksyen
					+ "' "
					+ "AND B.ID_NEGERI = '"
					+ id_negeri
					+ "' AND B.ID_PEJABATJKPTG = '" + id_pejabat + "' ";

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				usersInternal = new UsersInternal(

				coventer.getStringValue(resultSet.getString("USER_ID")),
						coventer.getStringValue(resultSet
								.getString("USER_LOGIN")), coventer
								.getStringValue(resultSet
										.getString("USER_NAME")), coventer
								.getStringValue(resultSet
										.getString("USER_ROLE")), coventer
								.getStringValue(resultSet
										.getString("USER_TYPE")), coventer
								.getStringValue(resultSet
										.getString("ID_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("NAMA_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("ID_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("NAMA_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("ID_PEJABATJKPTG")),
						coventer.getStringValue(resultSet
								.getString("NAMA_PEJABAT")), coventer
								.getStringValue(resultSet
										.getString("ID_JAWATAN")), coventer
								.getStringValue(resultSet
										.getString("KETERANGAN")));

				result.add(usersInternal);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersInternalById)[testing.java]"
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b> This method executes the list of internal users by userId </b>
	 * 
	 * @param userId
	 *            , id_jawatan, id_seksyen, id_negeri, id_pejabat, user_role
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersInternal> listUsersInternalById(String userId,
			String id_jawatan, String id_seksyen, String id_negeri,
			String id_pejabat, String user_role) throws Exception {

		List<UsersInternal> result = new ArrayList<UsersInternal>();
		UsersInternal usersInternal = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_SEKSYEN, D.NAMA_SEKSYEN, "
					+ "B.ID_NEGERI, C.NAMA_NEGERI, B.ID_DAERAH, E.NAMA_DAERAH, B.ID_PEJABATJKPTG, F.NAMA_PEJABAT, B.ID_JAWATAN, "
					+ "G.KETERANGAN FROM USERS A, USERS_INTERNAL B, TBLRUJNEGERI C, TBLRUJSEKSYEN D, TBLRUJDAERAH  E, TBLRUJPEJABATJKPTG F, "
					+ "TBLRUJJAWATAN G WHERE A.USER_ID = B.USER_ID " +
							"AND B.ID_NEGERI = C.ID_NEGERI(+) " +
							"AND B.ID_SEKSYEN = D.ID_SEKSYEN(+) "
					+ "AND B.ID_DAERAH = E.ID_DAERAH(+) " +
							"AND B.ID_JAWATAN = G.ID_JAWATAN(+) " +
							"AND B.ID_PEJABATJKPTG = F.ID_PEJABATJKPTG(+) "
					+ "AND LOWER(A.USER_LOGIN) LIKE '%"	+ userId+ "%' " ;
			
			
					if (!"".equals(id_jawatan)) {
						sql = sql + "AND B.ID_JAWATAN = '"+ id_jawatan+ "' " ;
					}
					if (!"".equals(id_seksyen)) {
						sql = sql + "AND B.ID_SEKSYEN = '"+ id_seksyen+ "' " ;
					}
					if (!"".equals(id_negeri)) {
						sql = sql + "AND B.ID_NEGERI = '"+ id_negeri+ "' " ;
					}
					if (!"".equals(id_pejabat)) {
						sql = sql + "AND B.ID_PEJABATJKPTG = '"+ id_pejabat+ "' " ;
					}
					if (!"".equals(user_role)) {
						sql = sql + "AND A.USER_ROLE = '" + user_role + "' ";
					}

					myLogger.debug(sql);
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				usersInternal = new UsersInternal(

				coventer.getStringValue(resultSet.getString("USER_ID")),
						coventer.getStringValue(resultSet
								.getString("USER_LOGIN")), coventer
								.getStringValue(resultSet
										.getString("USER_NAME")), coventer
								.getStringValue(resultSet
										.getString("USER_ROLE")), coventer
								.getStringValue(resultSet
										.getString("USER_TYPE")), coventer
								.getStringValue(resultSet
										.getString("ID_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("NAMA_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("ID_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("NAMA_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("ID_PEJABATJKPTG")),
						coventer.getStringValue(resultSet
								.getString("NAMA_PEJABAT")), coventer
								.getStringValue(resultSet
										.getString("ID_JAWATAN")), coventer
								.getStringValue(resultSet
										.getString("KETERANGAN")));

				result.add(usersInternal);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersInternalById)[testing.java]"
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b> This method executes the list of internal users by userId </b>
	 * 
	 * @param userId
	 *            , id_jawatan, id_seksyen
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersInternal> listUsersInternalById(String userId,
			String id_jawatan, String id_seksyen) throws Exception {

		List<UsersInternal> result = new ArrayList<UsersInternal>();
		UsersInternal usersInternal = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_SEKSYEN, D.NAMA_SEKSYEN, "
					+ "B.ID_NEGERI, C.NAMA_NEGERI, B.ID_DAERAH, E.NAMA_DAERAH, B.ID_PEJABATJKPTG, F.NAMA_PEJABAT, B.ID_JAWATAN, "
					+ "G.KETERANGAN FROM USERS A, USERS_INTERNAL B, TBLRUJNEGERI C, TBLRUJSEKSYEN D, TBLRUJDAERAH  E, TBLRUJPEJABATJKPTG F, "
					+ "TBLRUJJAWATAN G WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND B.ID_SEKSYEN = D.ID_SEKSYEN "
					+ "AND B.ID_DAERAH = E.ID_DAERAH AND B.ID_JAWATAN = G.ID_JAWATAN AND B.ID_PEJABATJKPTG = F.ID_PEJABATJKPTG "
					+ "AND LOWER(A.USER_LOGIN) LIKE '%"
					+ userId
					+ "%' AND B.ID_JAWATAN = '"
					+ id_jawatan
					+ "' AND B.ID_SEKSYEN = '" + id_seksyen + "' ";

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				usersInternal = new UsersInternal(

				coventer.getStringValue(resultSet.getString("USER_ID")),
						coventer.getStringValue(resultSet
								.getString("USER_LOGIN")), coventer
								.getStringValue(resultSet
										.getString("USER_NAME")), coventer
								.getStringValue(resultSet
										.getString("USER_ROLE")), coventer
								.getStringValue(resultSet
										.getString("USER_TYPE")), coventer
								.getStringValue(resultSet
										.getString("ID_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("NAMA_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("ID_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("NAMA_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("ID_PEJABATJKPTG")),
						coventer.getStringValue(resultSet
								.getString("NAMA_PEJABAT")), coventer
								.getStringValue(resultSet
										.getString("ID_JAWATAN")), coventer
								.getStringValue(resultSet
										.getString("KETERANGAN")));

				result.add(usersInternal);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersInternalById)[testing.java]"
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b> This method executes the list of internal users by userId </b>
	 * 
	 * @param userId
	 *            , id_jawatan
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersInternal> listUsersInternalByIdAndNegeri(String userId,
			String id_negeri) throws Exception {

		List<UsersInternal> result = new ArrayList<UsersInternal>();
		UsersInternal usersInternal = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_SEKSYEN, D.NAMA_SEKSYEN, "
					+ " B.ID_NEGERI, C.NAMA_NEGERI, B.ID_DAERAH, E.NAMA_DAERAH, B.ID_PEJABATJKPTG, F.NAMA_PEJABAT FROM USERS A, "
					+ " USERS_INTERNAL B, TBLRUJNEGERI C, TBLRUJSEKSYEN D, TBLRUJDAERAH  E, TBLRUJPEJABATJKPTG F "
					+ " WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND B.ID_SEKSYEN = D.ID_SEKSYEN AND "
					+ " B.ID_DAERAH = E.ID_DAERAH AND B.ID_PEJABATJKPTG = F.ID_PEJABATJKPTG AND"
					+ " LOWER(A.USER_LOGIN) LIKE '%"
					+ userId
					+ "%' AND B.ID_NEGERI = '" + id_negeri + "'";

			myLogger.debug(sql);
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				usersInternal = new UsersInternal(

				coventer.getStringValue(resultSet.getString("USER_ID")),
						coventer.getStringValue(resultSet
								.getString("USER_LOGIN")), coventer
								.getStringValue(resultSet
										.getString("USER_NAME")), coventer
								.getStringValue(resultSet
										.getString("USER_ROLE")), coventer
								.getStringValue(resultSet
										.getString("USER_TYPE")), coventer
								.getStringValue(resultSet
										.getString("ID_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("NAMA_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("ID_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("NAMA_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("ID_PEJABATJKPTG")),
						coventer.getStringValue(resultSet
								.getString("NAMA_PEJABAT")), "", "");

				result.add(usersInternal);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersInternalById)[testing.java]"
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/**
	 * <b> This method executes the list of internal users by userId </b>
	 * 
	 * @param userId
	 *            , id_jawatan
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersInternal> listUsersInternalByIdAndNegeriAndSeksyen(
			String userId, String id_seksyen, String id_negeri)
			throws Exception {

		List<UsersInternal> result = new ArrayList<UsersInternal>();
		UsersInternal usersInternal = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_SEKSYEN, D.NAMA_SEKSYEN, "
					+ " B.ID_NEGERI, C.NAMA_NEGERI, B.ID_DAERAH, E.NAMA_DAERAH, B.ID_PEJABATJKPTG, F.NAMA_PEJABAT FROM USERS A, "
					+ " USERS_INTERNAL B, TBLRUJNEGERI C, TBLRUJSEKSYEN D, TBLRUJDAERAH  E, TBLRUJPEJABATJKPTG F "
					+ " WHERE A.USER_ID = B.USER_ID AND B.ID_NEGERI = C.ID_NEGERI AND B.ID_SEKSYEN = D.ID_SEKSYEN AND "
					+ " B.ID_DAERAH = E.ID_DAERAH AND B.ID_PEJABATJKPTG = F.ID_PEJABATJKPTG AND"
					+ " LOWER(A.USER_LOGIN) LIKE '%"
					+ userId
					+ "%' AND B.ID_NEGERI = '"
					+ id_negeri
					+ "' AND B.ID_SEKSYEN = '" + id_seksyen + "' ";

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				usersInternal = new UsersInternal(

				coventer.getStringValue(resultSet.getString("USER_ID")),
						coventer.getStringValue(resultSet
								.getString("USER_LOGIN")), coventer
								.getStringValue(resultSet
										.getString("USER_NAME")), coventer
								.getStringValue(resultSet
										.getString("USER_ROLE")), coventer
								.getStringValue(resultSet
										.getString("USER_TYPE")), coventer
								.getStringValue(resultSet
										.getString("ID_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("NAMA_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("ID_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("NAMA_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("ID_PEJABATJKPTG")),
						coventer.getStringValue(resultSet
								.getString("NAMA_PEJABAT")), "", "");

				result.add(usersInternal);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listUsersInternalById)[testing.java]"
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}



	/**
	 * <b> This method executes the list of internal users by user name </b>
	 * 
	 * @param user_name
	 *            , id_jawatan, id_seksyen, id_negeri, id_pejabat
	 * @return {@link List} as result
	 * @exception Exception
	 */
	public List<UsersInternal> listUsersInternalByName(String user_name,
			String id_jawatan, String id_seksyen, String id_negeri,
			String id_pejabat, String user_role,String orderBy) throws Exception {

		List<UsersInternal> result = new ArrayList<UsersInternal>();
		UsersInternal usersInternal = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE, B.ID_SEKSYEN, D.NAMA_SEKSYEN, "
					+ "B.ID_NEGERI, C.NAMA_NEGERI, B.ID_DAERAH, E.NAMA_DAERAH, B.ID_PEJABATJKPTG, F.NAMA_PEJABAT, B.ID_JAWATAN, "
					+ "G.KETERANGAN FROM USERS A, USERS_INTERNAL B, TBLRUJNEGERI C, " +
					"TBLRUJSEKSYEN D, TBLRUJDAERAH  E, TBLRUJPEJABATJKPTG F, "
					+ "TBLRUJJAWATAN G WHERE A.USER_ID = B.USER_ID " +
							"AND B.ID_NEGERI = C.ID_NEGERI(+) " +
							"AND B.ID_SEKSYEN = D.ID_SEKSYEN(+) "
					+ "AND B.ID_DAERAH = E.ID_DAERAH(+) AND B.ID_JAWATAN = G.ID_JAWATAN(+) " +
					"AND B.ID_PEJABATJKPTG = F.ID_PEJABATJKPTG(+) "
					+ "AND LOWER(A.USER_NAME) LIKE '%"+ user_name+ "%' " ;
					
					if (!"".equals(id_jawatan)) {
						sql = sql + "AND B.ID_JAWATAN = '"+ id_jawatan+ "' " ;
					}
					if (!"".equals(id_seksyen)) {
						sql = sql + "AND B.ID_SEKSYEN = '"+ id_seksyen+ "' " ;
					}
					if (!"".equals(id_negeri)) {
						sql = sql + "AND B.ID_NEGERI = '"+ id_negeri+ "' " ;
					}
					if (!"".equals(id_pejabat)) {
						sql = sql + "AND B.ID_PEJABATJKPTG = '"+ id_pejabat+ "' " ;
					}
					if (!"".equals(user_role)) {
						sql = sql + "AND A.USER_ROLE = '" + user_role + "' ";
					}
					sql = sql + "ORDER BY "+orderBy;
			
			myLogger.debug(sql);
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				usersInternal = new UsersInternal(coventer
						.getStringValue(resultSet.getString("USER_ID")),
						coventer.getStringValue(resultSet
								.getString("USER_LOGIN")), coventer
								.getStringValue(resultSet
										.getString("USER_NAME")), coventer
								.getStringValue(resultSet
										.getString("USER_ROLE")), coventer
								.getStringValue(resultSet
										.getString("USER_TYPE")), coventer
								.getStringValue(resultSet
										.getString("ID_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("NAMA_SEKSYEN")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("ID_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("NAMA_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("ID_PEJABATJKPTG")),
						coventer.getStringValue(resultSet
								.getString("NAMA_PEJABAT")), coventer
								.getStringValue(resultSet
										.getString("ID_JAWATAN")), coventer
								.getStringValue(resultSet
										.getString("KETERANGAN")));

				result.add(usersInternal);

			}

		} catch (Exception e) {
			e.printStackTrace();
			myLogger.debug(e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	/////////KJP
	public List<UsersKJP> listUsersKJP(String selectSearch,String user_name,
			String id_kementerian,String id_agensi) throws Exception {

		List<UsersKJP> result = new ArrayList<UsersKJP>();
		UsersKJP user = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = 
				"SELECT a.user_id,a.user_login,a.user_name," +
					" b.id_agensi,b.id_kementerian,d.nama_kementerian,c.nama_agensi," +
					" (" +
					"	select" +
					"	case" +
					"		when id_jawatan=4 then 'PELULUS'" +
					"       when id_jawatan=9 then 'PENYEMAK'" +
					"       when id_jawatan=24 then 'PENYEDIA'" +
					"       else 'TIADA JAWATAN'" +
					"   end KETERANGAN" +
					" from users_internal ui where ui.USER_ID = a.USER_ID" +
					" ) as nama_jawatan" +
					" from users a,users_kementerian b,tblrujagensi c ," +
					"		tblrujkementerian d where" +
					"		a.user_id = b.user_id and b.id_agensi = c.id_agensi" +
					"		and b.id_kementerian = d.id_kementerian and" +
					"		a.user_role = 'online_kjp'";
					
				
//				"SELECT a.user_id,a.user_login,a.user_name," +
//					"b.id_agensi,b.id_kementerian," +
//					"d.nama_kementerian,c.nama_agensi " +
//					"from users a,users_kementerian b,tblrujagensi c ," +
//					"tblrujkementerian d where " +
//					"a.user_id = b.user_id and b.id_agensi = c.id_agensi " +
//					"and b.id_kementerian = d.id_kementerian and " +
//					"a.user_role = 'online_kjp' ";
			
					if ("NAMA".equals(selectSearch)) {
						sql = sql + "and lower(a.user_name) like '%"+user_name.toLowerCase()+"%' ";
					} else {
						sql = sql + "and lower(a.user_login) like '%"+user_name.toLowerCase()+"%' ";
					}
					
					if ("null".equals(id_kementerian)) { id_kementerian = "";}
					if ("null".equals(id_agensi)) { id_agensi = "";}
					
					if (!"".equals(id_kementerian)) {
						sql = sql + "AND b.id_kementerian = '"+id_kementerian+"'";
					}
					if (!"".equals(id_agensi)) {
						sql = sql + "AND b.id_agensi = '"+id_agensi+"'";
					}
			
			myLogger.debug(sql);
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				user = new UsersKJP();
				user.setUser_id(Utils.isNull(rs.getString("user_id")));
				user.setUser_login(Utils.isNull(rs.getString("user_login")));
				user.setUser_name(Utils.isNull(rs.getString("user_name")));
				user.setId_agensi(Utils.isNull(rs.getString("id_agensi")));
				user.setId_kementerian(Utils.isNull(rs.getString("id_kementerian")));
				user.setNama_kementerian(Utils.isNull(rs.getString("nama_kementerian")));
				user.setNama_agensi(Utils.isNull(rs.getString("nama_agensi")));
				user.setNama_jawatan(Utils.isNull(rs.getString("nama_jawatan")));
				result.add(user);

			}

		} catch (Exception e) {
			e.printStackTrace();
			myLogger.debug(e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}
	
	public UsersKJP UsersKJPDetail(String user_id) throws Exception {

		UsersKJP user = null;
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT a.user_id,a.user_login,a.user_name," +
					"b.id_agensi,b.id_kementerian," +
					"d.nama_kementerian,c.nama_agensi " +
					"from users a,users_kementerian b,tblrujagensi c ," +
					"tblrujkementerian d where " +
					"a.user_id = b.user_id and b.id_agensi = c.id_agensi " +
					"and b.id_kementerian = d.id_kementerian and " +
					"a.user_role = 'online_kjp' and a.user_id='"+user_id+"'";
			
			//myLogger.debug(sql);
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				user = new UsersKJP();
				user.setUser_id(Utils.isNull(rs.getString("user_id")));
				user.setUser_login(Utils.isNull(rs.getString("user_login")));
				user.setUser_name(Utils.isNull(rs.getString("user_name")));
				user.setId_agensi(Utils.isNull(rs.getString("id_agensi")));
				user.setId_kementerian(Utils.isNull(rs.getString("id_kementerian")));
				user.setNama_kementerian(Utils.isNull(rs.getString("nama_kementerian")));
				user.setNama_agensi(Utils.isNull(rs.getString("nama_agensi")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			myLogger.debug(e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return user;
	}
	
	/**
	 * <b> This method executes the delete user by userId </b>
	 * 
	 * @param userId
	 * @exception Exception
	 */
	public void deleteUserInternal(String user_login) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement statement = db.getStatement();
			Statement statement2 = db.getStatement();
			Statement statement3 = db.getStatement();

			String sql1 = "DELETE FROM USERS WHERE USER_LOGIN = '" + user_login
					+ "'";
			String sql2 = "DELETE FROM USERS_INTERNAL WHERE "
					+ "USER_ID=(SELECT UI.USER_ID FROM USERS_INTERNAL UI,USERS U WHERE U.USER_ID = UI.USER_ID AND  U.USER_LOGIN = '"
					+ user_login + "')";
			String sql3 = "DELETE FROM USER_CSS WHERE USER_LOGIN = '"
					+ user_login + "'";

			statement.executeUpdate(sql1);
			statement2.executeUpdate(sql2);
			statement3.executeUpdate(sql3);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deleteUserKJP(String user_login) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement statement = db.getStatement();
			//Statement statement2 = db.getStatement();
			//Statement statement3 = db.getStatement();

			String sql1 = "DELETE FROM USERS WHERE USER_LOGIN = '" + user_login
					+ "'";
			String sql2 = "DELETE FROM USERS_INTERNAL WHERE "
					+ "USER_ID=(SELECT UI.USER_ID FROM USERS_INTERNAL UI,USERS U WHERE U.USER_ID = UI.USER_ID AND  U.USER_LOGIN = '"
					+ user_login + "')";
			String sql3 = "DELETE FROM USER_CSS WHERE USER_LOGIN = '"
					+ user_login + "'";
			String sql4 = "DELETE FROM USERS_KEMENTERIAN WHERE "
				+ "USER_ID=(SELECT UI.USER_ID FROM USERS_INTERNAL UI,USERS U WHERE U.USER_ID = UI.USER_ID AND  U.USER_LOGIN = '"
				+ user_login + "')";	
			String sql5 = "DELETE FROM user_role WHERE USER_ID = '"
				+ user_login + "'";
			myLogger.debug(sql1);
			myLogger.debug(sql2);
			myLogger.debug(sql3);
			myLogger.debug(sql4);
			
			statement.executeUpdate(sql1);
			statement.executeUpdate(sql2);
			statement.executeUpdate(sql3);
			statement.executeUpdate(sql4);
			statement.executeUpdate(sql5);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * <b> This method executes the delete user by userId </b>
	 * 
	 * @param userId
	 * @exception Exception
	 */
	public void deleteUserOnline(String user_login) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement statement = db.getStatement();
			Statement statement2 = db.getStatement();
			Statement statement3 = db.getStatement();

			String sql1 = "DELETE FROM USERS WHERE USER_LOGIN = '" + user_login
					+ "'";
			String sql2 = "DELETE FROM USERS_ONLINE WHERE "
					+ "USER_ID=(SELECT UO.USER_ID FROM USERS_ONLINE UO, USERS U WHERE U.USER_ID = UO.USER_ID AND  U.USER_LOGIN = '"
					+ user_login + "')";
			String sql3 = "DELETE FROM USER_CSS WHERE USER_LOGIN = '"
					+ user_login + "'";

			statement.executeUpdate(sql1);
			statement2.executeUpdate(sql2);
			statement3.executeUpdate(sql3);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * <b> This method executes the update user by userId </b>
	 * 
	 * @param userId
	 * @exception Exception
	 */
	public void updateUserInternal() throws Exception {
		// business logic
		Converter coventer = new Converter();
		Db db = null;
		try {
			db = new Db();
			Statement statement = db.getStatement();

			String sql = "";

			statement.executeUpdate(sql);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * <b> This method executes the update user by userId </b>
	 * 
	 * @param userId
	 * @exception Exception
	 */
	public void updateUser(String user_id, String user_login,
			String user_password, String user_name, String user_role,
			String id_kemaskini) throws Exception {
		// business logic
		Converter coventer = new Converter();

		String userId = coventer.getStringValue(user_id);
		String userLogin = coventer.getStringValue(user_login);
		String userPass = coventer.getStringValue(user_password);
		String userName = coventer.getStringValue(user_name);
		String userRole = coventer.getStringValue(user_role);

		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE USERS SET ");

			if (!userLogin.equals("")) {
				sb.append("USER_LOGIN = '" + userLogin + "', ");
			}
			if (!userPass.equals("")) {
				sb.append("USER_PASSWORD = '"
						+ PasswordService.encrypt(userPass) + "', ");
			}
			if (!userName.equals("")) {
				sb.append("USER_NAME = '" + userName + "', ");
			}
			if (!userRole.equals("")) {
				sb.append("USER_ROLE = '" + userRole + "', ");
			}

			sb.append("ID_KEMASKINI = '" + id_kemaskini
					+ "', TARIKH_KEMASKINI = SYSDATE WHERE USER_ID = '"
					+ userId + "' ");

			String sql = sb.toString();

			//

			statement.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * <b> This method executes the update user by userId </b>
	 * 
	 * @param userId
	 * @exception Exception
	 */
	public void updateUserOnline(String user_id, String alamat1,
			String alamat2, String alamat3, String id_negeri, String poskod,
			String emel, String no_hp, String no_tel, String no_fax, String kp,
			String umur, String jantina, String tp, String tarikhLahir,
			String id_kemaskini) throws Exception {

		Converter coventer = new Converter();

		String userId = coventer.getStringValue(user_id);
		String alamat_1 = coventer.getStringValue(alamat1);
		String alamat_2 = coventer.getStringValue(alamat2);
		String alamat_3 = coventer.getStringValue(alamat3);
		String idNegeri = coventer.getStringValue(id_negeri);
		String postcode = coventer.getStringValue(poskod);
		String email = coventer.getStringValue(emel);
		String noHp = coventer.getStringValue(no_hp);
		String noTel = coventer.getStringValue(no_tel);
		String noFax = coventer.getStringValue(no_fax);
		String ic = coventer.getStringValue(kp);
		String age = coventer.getStringValue(umur);
		String gander = coventer.getStringValue(jantina);
		String status = coventer.getStringValue(tp);
		String tarikh_lahir = coventer.getStringValue(tarikhLahir);

		// //////////////////////////

		// System.out.println("USER ID: " + userId);
		// System.out.println("ALAMAT 1: " + alamat_1);
		// System.out.println("ALAMAT 2: " + alamat_2);
		// System.out.println("ALAMAT 3: " + alamat_3);
		// System.out.println("ID NEGERI: " + id_negeri);
		// System.out.println("POSKOD: " + poskod);
		// System.out.println("EMEL: " + email);
		// System.out.println("NO HP: " + noHp);
		// System.out.println("NO TEL: " + noTel);
		// System.out.println("NO FAX: " + noFax);
		// System.out.println("NO KP: " + ic);
		// System.out.println("UMUR: " + age);
		// System.out.println("GANDER: " +gander);
		// System.out.println("STATUS: " + status);
		// System.out.println("TL: " + tarikh_lahir);

		// ////////////////////////////

		Db db = null;
		try {
			db = new Db();
			Statement statement = db.getStatement();
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE USERS_ONLINE SET ");
			if (!alamat_1.equals("")) {
				sb.append("ALAMAT1 = '" + alamat_1 + "', ");
			}
			if (!alamat_2.equals("")) {
				sb.append("ALAMAT2 = '" + alamat_2 + "', ");
			}
			if (!alamat_3.equals("")) {
				sb.append("ALAMAT3 = '" + alamat_3 + "', ");
			}
			if (!idNegeri.equals("")) {
				sb.append("ID_NEGERI = '" + idNegeri + "', ");
			}
			if (!postcode.equals("")) {
				sb.append("POSKOD = '" + postcode + "', ");
			}
			if (!email.equals("")) {
				sb.append("EMEL = '" + email + "', ");
			}
			if (!noHp.equals("")) {
				sb.append("NO_HP = '" + noHp + "', ");
			}
			if (!noTel.equals("")) {
				sb.append("NO_TEL = '" + noTel + "', ");
			}
			if (!noFax.equals("")) {
				sb.append("NO_FAX = '" + noFax + "', ");
			}
			if (!ic.equals("")) {
				sb.append("NO_KP_BARU = '" + ic + "', ");
			}
			if (!age.equals("")) {
				sb.append("UMUR = '" + age + "', ");
			}
			if (!gander.equals("")) {
				sb.append("JANTINA = '" + gander + "', ");
			}
			if (!status.equals("")) {
				sb.append("TARAF_PERKAHWINAN = '" + status + "', ");
			}
			if (!tarikh_lahir.equals("")) {
				sb.append("TARIKH_LAHIR = TO_DATE('" + tarikh_lahir
						+ "','DD/MM/YYYY'), ");
			}

			sb.append("ID_KEMASKINI = '" + id_kemaskini
					+ "',  TARIKH_KEMASKINI = SYSDATE WHERE USER_ID = '"
					+ userId + "' ");

			String sql = sb.toString();

			statement.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * <b> This method executes the update user by userId </b>
	 * 
	 * @param userId
	 * @exception Exception
	 */
	public void updateUserInternal(String user_id, String id_seksyen,
			String id_negeri, String id_daerah, String id_pejabat,
			String id_agama, String id_bangsa, String id_jawatan,
			String id_kemaskini,String emel) throws Exception {

		Converter coventer = new Converter();

		String userId = coventer.getStringValue(user_id);
		String idSeksyen = coventer.getStringValue(id_seksyen);
		String idDaerah = coventer.getStringValue(id_daerah);
		String idNegeri = coventer.getStringValue(id_negeri);
		String idPejabat = coventer.getStringValue(id_pejabat);
		String idAgama = coventer.getStringValue(id_agama);
		String idBangsa = coventer.getStringValue(id_bangsa);
		String idJawatan = coventer.getStringValue(id_jawatan);

		// ID_SEKSYEN = '', ID_NEGERI = '', ID_DAERAH ='', ID_PEJABATJKPTG = '',
		// ID_AGAMA ='', ID_BANGSA ='', ID_JAWATAN ='', ID_KEMASKINI ='',
		// TARIKH_KEMASKINI = ''
		// WHERE USER_ID = ''
		//		
		Db db = null;
		try {
			db = new Db();
			Statement statement = db.getStatement();
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE USERS_INTERNAL SET ");
			if (!idSeksyen.equals("")) {
				sb.append(" ID_SEKSYEN = '" + idSeksyen + "', ");
			}
			if (!idNegeri.equals("")) {
				sb.append(" ID_NEGERI = '" + idNegeri + "', ");
			}
			if (!idDaerah.equals("")) {
				sb.append(" ID_DAERAH = '" + idDaerah + "', ");
			}
			if (!idPejabat.equals("")) {
				sb.append(" ID_PEJABATJKPTG = '" + idPejabat + "', ");
			}
			if (!idAgama.equals("")) {
				sb.append(" ID_AGAMA = '" + idAgama + "', ");
			}
			if (!idBangsa.equals("")) {
				sb.append(" ID_BANGSA = '" + idBangsa + "', ");
			}
			if (!idJawatan.equals("")) {
				sb.append(" ID_JAWATAN = '" + idJawatan + "', ");
			}
			if (!emel.equals("")) {
				sb.append(" emel = '" + emel + "', ");
			}
			sb.append(" ID_KEMASKINI = '" + id_kemaskini
					+ "', TARIKH_KEMASKINI = SYSDATE WHERE USER_ID = '"
					+ userId + "' ");

			String sql = sb.toString();

			myLogger.debug(sql);

			statement.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * <b> This method executes the update user by userId </b>
	 * 
	 * @param userId
	 * @exception Exception
	 */
	public void updateUserCSS(String user_login, String user_login_old,
			String css_name) throws Exception {
		// business logic
		Converter coventer = new Converter();

		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE USER_CSS SET USER_LOGIN = '"+user_login+"',  CSS_NAME = '"+css_name+"' WHERE USER_LOGIN = '"+user_login_old+"'");

//			if (!userLogin.equals("")) {
//				sb.append("USER_LOGIN = '" + userLogin + "', ");
//			}
//			if (!cssName.equals("")) {
//				sb.append("CSS_NAME = '" + cssName + "' ");
//			}
//
//			sb.append("' WHERE USER_LOGIN = '" + user_login_old + "' ");

			String sql = sb.toString();
			// 
			statement.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * <b> This method executes the list of user type </b>
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public List<UserType> listType() throws Exception {
		List<UserType> result = new ArrayList<UserType>();

		UserType type = new UserType();
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT USER_TYPE FROM USERS GROUP BY USER_TYPE ORDER BY USER_TYPE ASC";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				type = new UserType();

				String userType = coventer.getStringValue(resultSet
						.getString("USER_TYPE"));
				type.setUserType(userType.toUpperCase());

				result.add(type);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listType) [UsersListConnectionToDB.java] "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;

	}

	/**
	 * <b> This method executes the list of user type </b>
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public List<StylePage> listPageStyle() throws Exception {
		List<StylePage> result = new ArrayList<StylePage>();

		StylePage type = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT * FROM PAGE_CSS";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				type = new StylePage(coventer.getStringValue(resultSet
						.getString("CSS_TITLE")), coventer
						.getStringValue(resultSet.getString("CSS_NAME")));

				result.add(type);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listCssStyle) [UsersListConnectionToDB.java] "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;

	}

	/**
	 * <b> This method executes the list of user type </b>
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public List<USER_CSS> listPageStyleByUserLogin(String user_login)
			throws Exception {

		List<USER_CSS> result = new ArrayList<USER_CSS>();
		USER_CSS type = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT * FROM USER_CSS WHERE USER_LOGIN = '"
					+ user_login + "' ";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				type = new USER_CSS(coventer.getStringValue(resultSet
						.getString("USER_LOGIN")), coventer
						.getStringValue(resultSet.getString("CSS_NAME")));
				result.add(type);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listCssStylebyUserLogin) [UsersListConnectionToDB.java] "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;

	}

	/**
	 * <b> This method executes the list of user type </b>
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public StylePage InsertPageStyleByUserLogin(String user_login,
			String css_name) throws Exception {

		StylePage type = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "INSERT INTO USER_CSS(USER_LOGIN, CSS_NAME)VALUES('"
					+ user_login + "', '" + css_name + "')";
			statement.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (insert user_css) [UsersListConnectionToDB.java] "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return type;

	}

	/**
	 * <b> This method executes the list of user type </b>
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public BandarInformation listBandarById(String id_bandar) throws Exception {

		BandarInformation bi = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT BA.ID_BANDAR, BA.KOD_BANDAR, BA.KETERANGAN, BA.ID_NEGERI, NE.KOD_MAMPU, NE.NAMA_NEGERI, "
					+ "NE.ID_NEGARA, NA.KOD_NEGARA, NA.NAMA_NEGARA "
					+ "FROM TBLRUJBANDAR BA, TBLRUJNEGERI NE, TBLRUJNEGARA NA "
					+ "WHERE BA.ID_NEGERI = NE.ID_NEGERI  AND NE.ID_NEGARA=NA.ID_NEGARA AND BA.ID_BANDAR='"
					+ id_bandar + "'";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				bi = new BandarInformation(coventer.getStringValue(resultSet
						.getString("ID_BANDAR")), coventer
						.getStringValue(resultSet.getString("KOD_BANDAR")),
						coventer.getStringValue(resultSet
								.getString("KETERANGAN")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("KOD_MAMPU")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGARA")), coventer
								.getStringValue(resultSet
										.getString("KOD_NEGARA")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGARA")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listBandar) [UsersListConnectionToDB.java] "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return bi;

	}

	/**
	 * <b> This method executes the list of user type </b>
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public JawatanInformation listJawatanById(String id_jawatan)
			throws Exception {

		JawatanInformation ji = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT ID_JAWATAN, KOD_JAWATAN, KETERANGAN FROM TBLRUJJAWATAN WHERE ID_JAWATAN = '"
					+ id_jawatan + "'";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				ji = new JawatanInformation(coventer.getStringValue(resultSet
						.getString("ID_JAWATAN")), coventer
						.getStringValue(resultSet.getString("KOD_JAWATAN")),
						coventer.getStringValue(resultSet
								.getString("KETERANGAN")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("ERROR MESSAGE (listJawatan) [UsersListConnectionToDB.java] "+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return ji;

	}
	
	public String getIdJawatanByDesc(String desc) throws Exception {
		String output="";
			String sql = "";
			Db db = null;
			try {
				db = new Db(); 
				sql = "SELECT id_jawatan FROM TBLRUJJAWATAN WHERE KETERANGAN='"+desc+"'";
				//myLogger.info(sql);
				ResultSet rs = db.getStatement().executeQuery(sql); 
				if (rs.next()){	
					output = rs.getString("id_jawatan");
				}
			} catch (Exception e) {
				throw new Exception ("error getting id_jawatan :"+e.getMessage());
			}finally {
				if (db != null) db.close();
			}
			return output;
	}

	/**
	 * <b> This method executes the list of user type </b>
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public AgamaInformation listAgamaById(String id_agama) throws Exception {

		AgamaInformation ai = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT ID_AGAMA, KOD_AGAMA, KETERANGAN FROM TBLRUJAGAMA WHERE ID_AGAMA = '"
					+ id_agama + "'";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				ai = new AgamaInformation(coventer.getStringValue(resultSet
						.getString("ID_AGAMA")), coventer
						.getStringValue(resultSet.getString("KOD_AGAMA")),
						coventer.getStringValue(resultSet
								.getString("KETERANGAN")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listagama) [UsersListConnectionToDB.java] "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return ai;

	}

	/**
	 * <b> This method executes the list of user type </b>
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public BangsaInformation listBangsaById(String id_bangsa) throws Exception {

		BangsaInformation bi = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT ID_BANGSA, KOD_BANGSA, KETERANGAN FROM TBLRUJBANGSA WHERE ID_BANGSA = '"
					+ id_bangsa + "'";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				bi = new BangsaInformation(coventer.getStringValue(resultSet
						.getString("ID_BANGSA")), coventer
						.getStringValue(resultSet.getString("KOD_BANGSA")),
						coventer.getStringValue(resultSet
								.getString("KETERANGAN")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listBangsa with id) [UsersListConnectionToDB.java] "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return bi;

	}

	/**
	 * <b> This method executes the list of user type </b>
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public List<BangsaInformation> listBangsa() throws Exception {

		List<BangsaInformation> result = new ArrayList<BangsaInformation>();
		BangsaInformation bi = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT ID_BANGSA, KOD_BANGSA, KETERANGAN FROM TBLRUJBANGSA WHERE ID_BANGSA IN (1,2,3,7)";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				bi = new BangsaInformation(coventer.getStringValue(resultSet
						.getString("ID_BANGSA")), coventer
						.getStringValue(resultSet.getString("KOD_BANGSA")),
						coventer.getStringValue(resultSet
								.getString("KETERANGAN")));
				result.add(bi);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listbangsa) [UsersListConnectionToDB.java] "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;

	}

	/**
	 * <b> This method executes the list of user type </b>
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public List<AgamaInformation> listAgama() throws Exception {
		List<AgamaInformation> result = new ArrayList<AgamaInformation>();
		AgamaInformation ai = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT ID_AGAMA, KOD_AGAMA, KETERANGAN FROM TBLRUJAGAMA";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				ai = new AgamaInformation(coventer.getStringValue(resultSet
						.getString("ID_AGAMA")), coventer
						.getStringValue(resultSet.getString("KOD_AGAMA")),
						coventer.getStringValue(resultSet
								.getString("KETERANGAN")));
				result.add(ai);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listbangsa) [UsersListConnectionToDB.java] "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return result;

	}

	/**
	 * <b> This method executes the list of user type </b>
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public PejabatInformation listPejabatById(String id_pejabat)
			throws Exception {

		PejabatInformation pi = null;
		Converter coventer = new Converter();
		Db db = null;

		try {
			db = new Db();
			Statement statement = db.getStatement();
			String sql = "SELECT PE.ID_PEJABATJKPTG, PE.NAMA_PEJABAT, PE.ALAMAT1, PE.ALAMAT2, PE.ALAMAT3, PE.POSKOD, "
					+ "PE.NO_TEL, PE.NO_FAX, PE.ID_DAERAH, DA.NAMA_DAERAH, PE.ID_NEGERI, NE.NAMA_NEGERI, NE.ID_NEGARA, NA.NAMA_NEGARA "
					+ "FROM TBLRUJPEJABATJKPTG PE, TBLRUJDAERAH DA,  TBLRUJNEGERI NE,  TBLRUJNEGARA NA "
					+ "WHERE PE.ID_DAERAH = DA.ID_DAERAH AND PE.ID_NEGERI = NE.ID_NEGERI AND NE.ID_NEGARA = NA.ID_NEGARA "
					+ "AND ID_PEJABATJKPTG = '" + id_pejabat + "'";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				pi = new PejabatInformation(
						coventer.getStringValue(resultSet
								.getString("ID_PEJABATJKPTG")),
						coventer.getStringValue(resultSet
								.getString("NAMA_PEJABAT")),
						coventer.getStringValue(resultSet.getString("ALAMAT1")),
						coventer.getStringValue(resultSet.getString("ALAMAT2")),
						coventer.getStringValue(resultSet.getString("ALAMAT3")),
						coventer.getStringValue(resultSet.getString("POSKOD")),
						coventer.getStringValue(resultSet.getString("NO_TEL")),
						coventer.getStringValue(resultSet.getString("NO_FAX")),
						coventer.getStringValue(resultSet
								.getString("ID_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("NAMA_DAERAH")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGERI")), coventer
								.getStringValue(resultSet
										.getString("ID_NEGARA")), coventer
								.getStringValue(resultSet
										.getString("NAMA_NEGARA")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("ERROR MESSAGE (listbangsa) [UsersListConnectionToDB.java] "
							+ e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		return pi;

	}
	
	/////////////
	public boolean updateKJP(String user_id,String username,String userlogin, String password,
			  String idKementerian,String idAgensi,String id_masuk) 
			  throws Exception { 
	Db db = null;
	Connection conn = null;
	String sql = "";
	try
	{
	db = new Db();
	Statement stmt = db.getStatement();
	conn = db.getConnection();
	conn.setAutoCommit(false);
	
	SQLRenderer r = new SQLRenderer();
	
	r.clear();
	r.update("user_id", user_id);
	r.add("user_login", userlogin);
	
	if (password != null && !"".equals(password)) {
		r.add("user_password", PasswordService.encrypt(password));
	}
	r.add("user_name", username);
	//r.add("id_kemaskini", id_masuk);
	r.add("tarikh_kemaskini", r.unquote("sysdate"));
	sql = r.getSQLUpdate("users");
	
	myLogger.debug(sql);
	
	stmt.executeUpdate(sql);
	
	 //masukkan dlm table user internal
	  r.clear();
	  r.update("user_id",user_id);
	  r.add("id_kemaskini", id_masuk);
	  r.add("tarikh_kemaskini", r.unquote("sysdate"));
	  //myLogger.info("SQL Insert user:"+sql);
	  sql = r.getSQLUpdate("users_internal");
	  stmt.executeUpdate(sql);
	  
	  
	  //masukkan dlm table user kementerian
	  r.clear();
	  r.update("user_id",user_id);
	  r.add("id_kementerian",idKementerian);
	  r.add("id_agensi",idAgensi);
	  r.add("id_kemaskini", id_masuk);
	  r.add("tarikh_kemaskini", r.unquote("sysdate"));
	  //myLogger.info("SQL Insert user:"+sql);
	  sql = r.getSQLUpdate("users_kementerian");
	  stmt.executeUpdate(sql);
	  
	  conn.commit();
	} 
	catch(SQLException ex)
	{
	try
	{
	conn.rollback();
	}
	catch(SQLException sqlexception) { }
	throw new DbException((new StringBuilder(String.valueOf(ex.getMessage()))).append(": ").append(sql).toString());
	}
	
	return true;
} 
	/////////////////
	

}
