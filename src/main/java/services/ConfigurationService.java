
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConfigurationRepository;
import domain.Configuration;

@Service
@Transactional
public class ConfigurationService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ConfigurationRepository	configurationRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ConfigurationService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Configuration findOne(final int configurationId) {
		Assert.isTrue(configurationId != 0);
		Configuration configuration;
		configuration = this.configurationRepository.findOne(configurationId);
		Assert.notNull(configuration);
		return configuration;
	}

	public Collection<Configuration> findAll() {
		Collection<Configuration> result;
		result = this.configurationRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Configuration save(Configuration configuration) {
		Assert.notNull(configuration);

		configuration = this.configurationRepository.save(configuration);

		return configuration;
	}

	// Other business methods -------------------------------------------------
	public Configuration findConfiguration() {
		final ArrayList<Configuration> aux = (ArrayList<Configuration>) this.configurationRepository.findAll();
		final Configuration configuration = aux.get(0);
		return configuration;
	}
	public Integer horasCache() {
		Configuration configuration;
		String[] tiempo;
		Integer result;
		configuration = this.findConfiguration();
		final String horas = configuration.getTime();
		tiempo = horas.split(":");
		result = Integer.parseInt(tiempo[0]) + (Integer.parseInt(tiempo[1]) / 60) + (Integer.parseInt(tiempo[2]) / 3600);
		return result;
	}

}
