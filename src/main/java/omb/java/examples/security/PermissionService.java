package omb.java.examples.security;
import java.security.Permission;
import java.util.List;

public class PermissionService {

	List<Permission> permissions;

	public PermissionService(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	public boolean checkPermission(Permission permission) {
		for (Permission p : permissions) {
			if (p.implies(permission)) {
				return true;
			}
		}
		return false;
	}

}
