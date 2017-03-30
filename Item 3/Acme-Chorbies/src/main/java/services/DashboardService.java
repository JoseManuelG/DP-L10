
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.DashboardRepository;

@Service
@Transactional
public class DashboardService {

	// Managed Repository --------------------------------------
	@Autowired
	private DashboardRepository	dashboardRepository;

}
