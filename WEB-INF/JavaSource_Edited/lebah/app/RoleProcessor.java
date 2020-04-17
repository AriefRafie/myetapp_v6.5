package lebah.app;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.object.Module;
import lebah.portal.element.Role;

public class RoleProcessor
{

    private String className;
    private Hashtable conProp;

    public RoleProcessor()
    {
        className = "lebah.app.RoleProcessor";
        conProp = new Hashtable();
    }

    public RoleProcessor(Hashtable conProp)
    {
        className = "lebah.app.RoleProcessor";
        this.conProp = new Hashtable();
        this.conProp = conProp;
    }

    private Db getDb()
        throws Exception
    {
        Db db = null;
        if(conProp.isEmpty())
        {
            db = new Db();
        } else
        {
            db = new Db(conProp);
        }
        return db;
    }

    public void addRole(String name, String description)
        throws Exception
    {
        String sql;
        Db database;
        sql = (new StringBuilder("insert into role (name,description) values ('")).append(name).append("','").append(description).append("')").toString();
        database = getDb();
        Statement stmt = database.getStatement();
        stmt.executeUpdate(sql);
       
        if(database != null)
        {
            database.close();
        }
        return;
    }

    public void addRole(String name, String description, String theme)
        throws Exception
    {
        String sql;
        Db database;
        sql = (new StringBuilder("insert into role (name,description,theme) values ('")).append(name).append("','").append(description).append("','").append(theme).append("')").toString();
        database = getDb();
        Statement stmt = database.getStatement();
        stmt.executeUpdate(sql);
       
        if(database != null)
        {
            database.close();
        }
        return;
    }

    public void deleteRole(String name)
        throws Exception
    {
        String sql;
        Db database;
        sql = (new StringBuilder("delete from role where name = '")).append(name).append("'").toString();
        database = getDb();
        Statement stmt = database.getStatement();
        stmt.executeUpdate(sql);
        
        if(database != null)
        {
            database.close();
        }
        return;
    }

    public void updateRole(String oldName, String name, String description, String theme)
        throws Exception
    {
        String sql;
        Db database;
        sql = (new StringBuilder("update role set name = '")).append(name).append("', ").append("description = '").append(description).append("',").append("theme = '").append(theme).append("' ").append("where name = '").append(oldName).append("'").toString();
        database = getDb();
        Statement stmt = database.getStatement();
        stmt.executeUpdate(sql);
        
        if(database != null)
        {
            database.close();
        }
        return;
    }

    public Vector getRoles()
        throws Exception
    {
        String sql;
        Vector list;
        Db database;
        /*sql = "select name, description,theme from role order by name";*/
        sql =	" SELECT R1.NAME AS name, REGEXP_REPLACE (REGEXP_REPLACE (R1.THEME, 'eTapp_', ''), '.css', '') AS kod, " +
				" UPPER (R1.DESCRIPTION) AS description, "+//R1.ROLE_DETAILS as details, 
				" 2 AS layer FROM   ROLE R1 UNION " + 
				" SELECT DISTINCT 'none' AS name,  REGEXP_REPLACE (REGEXP_REPLACE (R3.THEME, 'eTapp_', ''), '.css', '') AS kod, " +
				" '' AS description, "+ //R3.ROLE_DETAILS as details, 
				" 1 AS layer FROM   ROLE R3 WHERE   R3.NAME IS NOT NULL "+ 
				" ORDER BY   kod, layer ";
        list = new Vector();
        database = getDb();
        Role obj = null;
        Statement stmt = database.getStatement();
        for(ResultSet rs = stmt.executeQuery(sql); rs.next(); list.addElement(obj))
        {
            obj = new Role();
           /* obj.setName(rs.getString("name"));
            obj.setDescription(rs.getString("description"));
            obj.setTheme(rs.getString("theme"));*/
            
            obj.setName(rs.getString("name"));
			obj.setDescription(rs.getString("description"));
			obj.setKod(rs.getString("kod"));
			obj.setLayer(rs.getString("layer"));
        }

       
        if(database != null)
        {
            database.close();
        }
        return list;
    }
    
    public Vector getModules(String role)
            throws Exception
        {
    	return getModules(role,null);
        }

    public Vector getModules(String role,Db db)
        throws Exception
    {
        String sql;
        Vector list;
        Db database = null;
        if(db==null)
        {
        	database = getDb();
        }
        else
        {
        	database = db;
        }
        
        //sql = "select module_id, module_title, module_class, module_group, module_description from module order by module_group";
        sql = " select module.module_id, module.module_title, module.module_class, module.module_group, module.module_description, "+ 
        		" (case when role_module.module_id is not null then 'true' else 'false' end) as selected "+
        		" from module, (select rm.module_id from role_module rm where rm.user_role = '"+role+"') role_module  "+
        		" where module.module_id = role_module.module_id(+) order by module_group";
        
        list = new Vector();
       // database = getDb();
        Module obj = null;
        Statement stmt = database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        
        
        //int count = 0;
        while (rs.next()) {
        	
        	obj = new Module();
        	//System.out.println(count++);
            obj.setId(rs.getString("module_id"));
            obj.setTitle(rs.getString("module_title"));
            obj.setClassName(rs.getString("module_class"));
            obj.setGroup(rs.getString("module_group"));
            obj.setDescription(rs.getString("module_description"));
            obj.setSelected(rs.getString("selected"));
            //obj.setSelected(rs.getString("module_description"));
            //System.out.println(" obj.getId() : "+checkModule(role, obj.getId(),database));
            //obj.setSelected(checkModule(role, obj.getId(),database));
        	list.addElement(obj);
        	
        }
        /*
        for(ResultSet rs = stmt.executeQuery(sql); rs.next(); list.addElement(obj))
        {
        	
            obj = new Module();
            obj.setId(rs.getString("module_id"));
            obj.setTitle(rs.getString("module_title"));
            obj.setClassName(rs.getString("module_class"));
            obj.setGroup(rs.getString("module_group"));
            obj.setDescription(rs.getString("module_description"));
            System.out.println(" obj.getId() : "+obj.getId());
            obj.setSelected(checkModule(role, obj.getId(),database));
        }
		*/
       
        if(db == null)
        {
        	if (database != null) database.close();
        }
        return list;
    }
    
    private boolean checkModule(String role, String moduleId)
            throws Exception
        {
    		return checkModule( role, moduleId,null);
        }

    private boolean checkModule(String role, String moduleId,Db db)
        throws Exception
    {
    	
    	System.out.println("checkModule DB :"+db);
        String sql;
        boolean selected;
        Db database = null;
        
        if(db == null)
        {
        	database = getDb();
        }
        else
        {
        	database = db;
        }
       
        
    	try {
    			
    	
        sql = (new StringBuilder("select module_id from role_module where user_role = '")).append(role).append("' and module_id = '").append(moduleId).append("'").toString();
        //sql = "select module_id from role_module where user_role = '"+role+"' and module_id = '"+moduleId+"'";
    	System.out.print("checkModule : "+sql);
        Statement stmt = database.getStatement();
        //ResultSet rs = stmt.executeQuery(sql);
        ResultSet rs = stmt.executeQuery(sql);
        selected = false;
        /*
        //database = getDb();
        
        
        if(rs.next())
        {
        	
            selected = true;
            //return selected;
        }
        */
        
        return selected;  
        
    	}
		finally {
			if(db == null)
	        {
	            //database.close();
	            if (database != null) database.close();
	        }
		}
		
        
    }

    public void updateRoleModule(String role, String modules[])
        throws Exception
    {
        String sql1;
        Db database;
        sql1 = (new StringBuilder("delete from role_module where user_role = '")).append(role).append("'").toString();
        database = getDb();
        Statement stmt = database.getStatement();
        stmt.executeUpdate(sql1);
        if(modules != null)
        {
            for(int i = 0; i < modules.length; i++)
            {
                String sql2 = (new StringBuilder("insert into role_module (module_id, user_role) values ('")).append(modules[i]).append("','").append(role).append("')").toString();
                stmt.executeUpdate(sql2);
            }

        }
        
        if(database != null)
        {
            database.close();
        }
        return;
    }
}
