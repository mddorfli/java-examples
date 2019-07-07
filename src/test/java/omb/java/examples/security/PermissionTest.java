package omb.java.examples.security;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.AllPermission;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class PermissionTest {

	private List<Permission> permissions;

	@Before
	public void setup() {
		permissions = new ArrayList<>();
	}
	
	@Test
	public void testAdminUser() {
			permissions.add(new AllPermission()); // admin
			PermissionService service = new PermissionService(permissions);
			
			assertTrue("Admin must be able to do everything", service.checkPermission(new HierarchyPermission("UpdatePriority", 100)));
			assertTrue("Admin must be able to do everything", service.checkPermission(new HierarchyPermission("DeletePriority", 100)));
			assertTrue("Admin must be able to do everything", service.checkPermission(new HierarchyPermission("UpdatePriority", 0)));
			assertTrue("Admin must be able to do everything", service.checkPermission(new HierarchyPermission("UpdatePriority", 15)));
			assertTrue("Admin must be able to do everything", service.checkPermission(new HierarchyPermission("Updateasdfasdf", 100)));
	}

	@Test
	public void testSamePermissions() {
		permissions.add(new HierarchyPermission("UpdatePriority", GranularPermission.LEVEL_OWN));
		PermissionService service = new PermissionService(permissions);
		assertTrue(service.checkPermission(new HierarchyPermission("UpdatePriority", GranularPermission.LEVEL_OWN)));
	}
	
	@Test
	public void testDifferentLevels() {
		permissions.add(new HierarchyPermission("UpdatePriority", GranularPermission.LEVEL_OWN));
		
		PermissionService service = new PermissionService(permissions);
		assertFalse("User with own permission level cannot update all", service.checkPermission(new HierarchyPermission("UpdatePriority", HierarchyPermission.LEVEL_ALL)));		
		assertFalse("User cannot update priorities in his org unit.", service.checkPermission(new HierarchyPermission("UpdatePriority", GranularPermission.LEVEL_ORG)));		
		assertTrue("User can update his own priorities.", service.checkPermission(new HierarchyPermission("UpdatePriority", GranularPermission.LEVEL_OWN)));		
		assertTrue("Trivially, user with own permission level can update 'none' (no level).", service.checkPermission(new HierarchyPermission("UpdatePriority", HierarchyPermission.LEVEL_NONE)));		
	}
	
	@Test
	public void testGranularOwnerLevelAccess() {
		List<Integer> ownedIds = Arrays.asList(35, 36);
		List<Integer> idsInMyOrg = Arrays.asList(98, 99);
		MockPriorityService mockService = new MockPriorityService(ownedIds, idsInMyOrg);
		
		permissions.add(new UpdatePriorityPermission(UpdatePriorityPermission.LEVEL_OWN, mockService));
		PermissionService service = new PermissionService(permissions);

		assertTrue(service.checkPermission(new UpdatePriorityPermission(UpdatePriorityPermission.LEVEL_OWN, 35)));
		assertFalse(service.checkPermission(new UpdatePriorityPermission(UpdatePriorityPermission.LEVEL_OWN, 57)));
		assertFalse(service.checkPermission(new UpdatePriorityPermission(UpdatePriorityPermission.LEVEL_ORG, 98)));
		assertFalse(service.checkPermission(new UpdatePriorityPermission(UpdatePriorityPermission.LEVEL_ALL, 35)));
	}
	
	@Test
	public void testGranularOrgLevelAccess() {
		List<Integer> ownedIds = Arrays.asList(35, 36);
		List<Integer> idsInMyOrg = Arrays.asList(98, 99, 35, 36);
		MockPriorityService mockService = new MockPriorityService(ownedIds, idsInMyOrg);
		
		permissions.add(new UpdatePriorityPermission(UpdatePriorityPermission.LEVEL_ORG, mockService));
		PermissionService service = new PermissionService(permissions);
		
		assertTrue(service.checkPermission(new UpdatePriorityPermission(UpdatePriorityPermission.LEVEL_OWN, 35)));
		assertTrue(service.checkPermission(new UpdatePriorityPermission(UpdatePriorityPermission.LEVEL_OWN, 98)));
		assertFalse(service.checkPermission(new UpdatePriorityPermission(UpdatePriorityPermission.LEVEL_ALL, 35)));
	}
	
	
	private class MockPriorityService implements PriorityService {

		private List<Integer> ownedIds;
		private List<Integer> idsInMyOrg;

		public MockPriorityService(List<Integer> ownedIds, List<Integer> idsInMyOrg) {
			this.ownedIds = ownedIds;
			this.idsInMyOrg = idsInMyOrg;
		}
		
		@Override
		public List<Integer> getOwnedPriorityIds() {
			return ownedIds;
		}

		@Override
		public List<Integer> getPrioritiesInMyOrg() {
			return idsInMyOrg;
		}
	}
}
