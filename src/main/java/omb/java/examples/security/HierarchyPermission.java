package omb.java.examples.security;
import java.security.BasicPermission;
import java.security.Permission;


public class HierarchyPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;
	
	public static final int LEVEL_ALL = 100;
	public static final int LEVEL_NONE = 0;
	
	
	private int level;

	public HierarchyPermission(String name, int level) {
		super(name);
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public boolean implies(Permission p) {
		if (super.implies(p) && p instanceof HierarchyPermission) {
			HierarchyPermission hp = (HierarchyPermission) p;
			return getLevel() >= hp.getLevel();
		}
		return false;
	}
}