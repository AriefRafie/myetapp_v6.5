package lebah.portal.db;

import java.util.Date;

// Referenced classes of package lebah.portal.db:
//            Role

public class User
{

    int user_id;
    String login;
    String password;
    String name;
    Role role;
    String alternateLogin;
    String address;
    String ipAddress;
    Date registerDate;
    String avatar;
    Role secondaryRoles[];

    public User()
    {
        name = "";
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAlternateLogin()
    {
        return alternateLogin;
    }

    public void setAlternateLogin(String alternateLogin)
    {
        this.alternateLogin = alternateLogin;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getRegisterDate()
    {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate)
    {
        this.registerDate = registerDate;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public Role[] getSecondaryRoles()
    {
        return secondaryRoles;
    }

    public void setSecondaryRoles(Role secondaryRoles[])
    {
        this.secondaryRoles = secondaryRoles;
    }

    public boolean isRole(String role)
    {
        if(role == null)
        {
            return false;
        }
        if(role.equals(this.role.getName()))
        {
            return true;
        }
        Role arrayOfRole[] = secondaryRoles;
        int i = 0;
        for(int j = arrayOfRole.length; i < j; i++)
        {
            Role r = arrayOfRole[i];
            if(r.getName().equals(role))
            {
                return true;
            }
        }

        return false;
    }
}
