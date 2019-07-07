package omb.java.examples.security;
import java.util.List;


public interface PriorityService {

	public List<Integer> getOwnedPriorityIds();
	
	public List<Integer> getPrioritiesInMyOrg();
	
}
