package se.sundsvall.citizen.service;

import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.citizen.api.model.CitizenWithChangedAddress;
import se.sundsvall.citizen.integration.db.CitizenAddressRepository;
import se.sundsvall.citizen.integration.db.specification.CitizenAddressSpecification;
import se.sundsvall.citizen.service.mapper.CitizenAddressMapper;

@Service
@Transactional
public class CitizenAddressService {

	private final CitizenAddressRepository citizenAddressRepository;

	public CitizenAddressService(CitizenAddressRepository citizenAddressRepository) {
		this.citizenAddressRepository = citizenAddressRepository;
	}

	public List<CitizenWithChangedAddress> getCitizensWithChangedAddress(final OffsetDateTime changedDateFrom) {
		return citizenAddressRepository
			.findAll(CitizenAddressSpecification.hasChangedAddressSince(changedDateFrom))
			.stream()
			.map(CitizenAddressMapper::toCitizenWithChangedAddress)
			.toList();
	}
}
