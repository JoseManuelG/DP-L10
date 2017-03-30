
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ConfigurationRepository;
import domain.Configuration;

@Service
@Transactional
public class ConfigurationService {

	// Managed Repository --------------------------------------
	@Autowired
	private ConfigurationRepository	configurationRepository;


	public Configuration findConfiguration() {

		final Configuration result = this.configurationRepository.findConfiguration();

		return result;
	}

}
