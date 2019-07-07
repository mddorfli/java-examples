package omb.java.examples.security;


public class UpdatePriorityPermission extends GranularPermission {

	private static final long serialVersionUID = 1L;
	
	private PriorityService service;

	public UpdatePriorityPermission(int level, PriorityService service) {
		super(UpdatePriorityPermission.class.getSimpleName(), level);
		this.service = service;
	}
	
	public UpdatePriorityPermission(int level, int id) {
		super(UpdatePriorityPermission.class.getSimpleName(), id, level);
	}
	
	@Override
	protected boolean isOrgMember(int id) {
		return service.getPrioritiesInMyOrg().contains(id);
	}

	@Override
	protected boolean isOwner(int id) {
		return service.getOwnedPriorityIds().contains(id);
	}

}
