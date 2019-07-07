package omb.java.examples.security;

import java.security.Permission;

public abstract class GranularPermission extends HierarchyPermission {

	private static final long serialVersionUID = 1L;

	public static final int LEVEL_ORG = 20;
	public static final int LEVEL_OWN = 10;

	private int id;

	/**
	 * Called by the permission loader.
	 * 
	 * @param name
	 * @param level
	 */
	public GranularPermission(String name, int level) {
		super(name + ".*", level);
	}

	/**
	 * Called by the permission checker (check against).
	 * 
	 * @param name
	 * @param id
	 * @param level
	 */
	public GranularPermission(String name, int id, int level) {
		super(name + "." + id, level);
		this.id = id;
	}

	protected abstract boolean isOrgMember(int id);

	protected abstract boolean isOwner(int id);

	public int getId() {
		return id;
	}

	@Override
	public boolean implies(Permission p) {
		boolean granted = false;
		if (super.implies(p) && p instanceof GranularPermission) {
			GranularPermission gp = (GranularPermission) p;
			switch (getLevel()) {
			case LEVEL_ALL:
				granted = true;
				break;
			case LEVEL_ORG:
				// granted if we actually part of the organisation
				granted = isOrgMember(gp.getId());
				break;
			case LEVEL_OWN:
				// granted if we actually own the object
				granted = isOwner(gp.getId());
				break;
			case LEVEL_NONE:
				granted = false;
				break;
			default:
				granted = false;
				break;
			}
		}
		return granted;
	}

}
