package ca.sait.lab6crud.services;

import ca.sait.lab6crud.dataaccess.RoleDB;
import ca.sait.lab6crud.models.Role;
import java.util.List;

public class RoleService {
    private final RoleDB roleDB = new RoleDB();
    
    public List<Role> getAll() throws Exception {
        List<Role> roles = this.roleDB.getAll();
        return roles;
    }
    
   
}
