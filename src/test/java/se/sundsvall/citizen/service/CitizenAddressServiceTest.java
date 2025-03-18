package se.sundsvall.citizen.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import se.sundsvall.citizen.api.model.CitizenWithChangedAddress;
import se.sundsvall.citizen.integration.db.CitizenAddressRepository;
import se.sundsvall.citizen.integration.db.model.CitizenAddressEntity;
import se.sundsvall.citizen.integration.db.specification.CitizenAddressSpecification;
import se.sundsvall.citizen.service.mapper.CitizenAddressMapper;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CitizenAddressServiceTest {

    @Mock
    private CitizenAddressRepository citizenAddressRepositoryMock;

    @InjectMocks
    private CitizenAddressService citizenAddressService;

    @Test
    void getCitizensWithChangedAddress() {
        // Arrange
        final var changedDateFrom = OffsetDateTime.parse("2025-01-29T08:52:05Z");
        final var citizenAddressEntity = new CitizenAddressEntity();
        final var expectedCitizenWithChangedAddress = new CitizenWithChangedAddress();

        try (MockedStatic<CitizenAddressSpecification> specMock = Mockito.mockStatic(CitizenAddressSpecification.class);
             MockedStatic<CitizenAddressMapper> mapperMock = Mockito.mockStatic(CitizenAddressMapper.class)) {

            // Mock the specification
            Specification<CitizenAddressEntity> mockSpec = (root, query, cb) -> cb.conjunction();
            specMock.when(() -> CitizenAddressSpecification.hasChangedAddressSince(changedDateFrom))
                    .thenReturn(mockSpec);

            // Mock the repository response
            when(citizenAddressRepositoryMock.findAll(any(Specification.class)))
                    .thenReturn(List.of(citizenAddressEntity));

            // Mock the mapper
            mapperMock.when(() -> CitizenAddressMapper.toCitizenWithChangedAddress(any()))
                    .thenReturn(expectedCitizenWithChangedAddress);

            // Act
            final var result = citizenAddressService.getCitizensWithChangedAddress(changedDateFrom);

            // Assert
            assertThat(result)
                    .isNotNull()
                    .hasSize(1)
                    .containsExactly(expectedCitizenWithChangedAddress);

            verify(citizenAddressRepositoryMock).findAll(any(Specification.class));
            mapperMock.verify(() -> CitizenAddressMapper.toCitizenWithChangedAddress(same(citizenAddressEntity)));
            specMock.verify(() -> CitizenAddressSpecification.hasChangedAddressSince(same(changedDateFrom)));
        }
    }

    @Test
    void getCitizensWithChangedAddress_NoResults() {
        // Arrange
        final var changedDateFrom = OffsetDateTime.parse("2025-01-29T08:52:05Z");

        try (MockedStatic<CitizenAddressSpecification> specMock = Mockito.mockStatic(CitizenAddressSpecification.class)) {
            // Mock the specification
            Specification<CitizenAddressEntity> mockSpec = (root, query, cb) -> cb.conjunction();
            specMock.when(() -> CitizenAddressSpecification.hasChangedAddressSince(changedDateFrom))
                    .thenReturn(mockSpec);

            // Mock the repository response
            when(citizenAddressRepositoryMock.findAll(any(Specification.class)))
                    .thenReturn(Collections.emptyList());

            // Act
            final var result = citizenAddressService.getCitizensWithChangedAddress(changedDateFrom);

            // Assert
            assertThat(result)
                    .isNotNull()
                    .isEmpty();

            verify(citizenAddressRepositoryMock).findAll(any(Specification.class));
            specMock.verify(() -> CitizenAddressSpecification.hasChangedAddressSince(same(changedDateFrom)));
        }
    }
}