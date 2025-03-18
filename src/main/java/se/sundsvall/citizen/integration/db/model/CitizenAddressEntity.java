package se.sundsvall.citizen.integration.db.model;

import static java.time.OffsetDateTime.now;
import static java.time.temporal.ChronoUnit.MILLIS;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;

import org.hibernate.Length;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "citizen_addresses",
        indexes = {
                @Index(name = "idx_citizen_addresses_person_id", columnList = "person_id")
        })
public class CitizenAddressEntity implements Serializable {

    private static final long serialVersionUID = -3451441096651461591L;

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private CitizenEntity citizen;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "nr_date")
    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
    private OffsetDateTime nrDate;

    @Column(name = "real_estate_description", length = Length.LONG32)
    private String realEstateDescription;

    @Column(name = "co", length = 100)
    private String co;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "address_area", length = 100)
    private String addressArea;

    @Column(name = "address_number", length = 20)
    private String addressNumber;

    @Column(name = "address_letter", length = 10)
    private String addressLetter;

    @Column(name = "apartment_number ", length = 20)
    private String apartmentNumber ;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "county", length = 100)
    private String county;

    @Column(name = "municipality", length = 100)
    private String municipality;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "emigrated")
    private Boolean emigrated;

    @Column(name = "address_type", length = 50)
    private String addressType;

    @Column(name = "x_coord_local")
    private Double xCoordLocal;

    @Column(name = "y_coord_local")
    private Double yCoordLocal;

    @Column(name = "created_at", updatable = false)
    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
    private OffsetDateTime updatedAt;

    public static CitizenAddressEntity create() {
        return new CitizenAddressEntity();
    }

    @PrePersist
    void prePersist() {
        createdAt = now(ZoneId.systemDefault()).truncatedTo(MILLIS);
        updatedAt = createdAt;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = now(ZoneId.systemDefault()).truncatedTo(MILLIS);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public CitizenAddressEntity withId(final String id) {
        this.id = id;
        return this;
    }

    public CitizenEntity getCitizen() {
        return citizen;
    }

    public void setCitizen(final CitizenEntity citizen) {
        this.citizen = citizen;
    }

    public CitizenAddressEntity withCitizen(final CitizenEntity citizen) {
        this.citizen = citizen;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public CitizenAddressEntity withStatus(final String status) {
        this.status = status;
        return this;
    }

    public OffsetDateTime getNrDate() {
        return nrDate;
    }

    public void setNrDate(final OffsetDateTime nrDate) {
        this.nrDate = nrDate;
    }

    public CitizenAddressEntity withNrDate(final OffsetDateTime nrDate) {
        this.nrDate = nrDate;
        return this;
    }

    public String getRealEstateDescription() {
        return realEstateDescription;
    }

    public void setRealEstateDescription(final String realEstateDescription) {
        this.realEstateDescription = realEstateDescription;
    }

    public CitizenAddressEntity withRealEstateDescription(final String realEstateDescription) {
        this.realEstateDescription = realEstateDescription;
        return this;
    }

    public String getCo() {
        return co;
    }

    public void setCo(final String co) {
        this.co = co;
    }

    public CitizenAddressEntity withCo(final String co) {
        this.co = co;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public CitizenAddressEntity withAddress(final String address) {
        this.address = address;
        return this;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(final String addressArea) {
        this.addressArea = addressArea;
    }

    public CitizenAddressEntity withAddressArea(final String addressArea) {
        this.addressArea = addressArea;
        return this;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(final String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public CitizenAddressEntity withAddressNumber(final String addressNumber) {
        this.addressNumber = addressNumber;
        return this;
    }

    public String getAddressLetter() {
        return addressLetter;
    }

    public void setAddressLetter(final String addressLetter) {
        this.addressLetter = addressLetter;
    }

    public CitizenAddressEntity withAddressLetter(final String addressLetter) {
        this.addressLetter = addressLetter;
        return this;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(final String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public CitizenAddressEntity withApartmentNumber(final String apartmentNumber) {
        this.apartmentNumber  = apartmentNumber;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    public CitizenAddressEntity withPostalCode(final String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public CitizenAddressEntity withCity(final String city) {
        this.city = city;
        return this;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(final String county) {
        this.county = county;
    }

    public CitizenAddressEntity withCounty(final String county) {
        this.county = county;
        return this;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(final String municipality) {
        this.municipality = municipality;
    }

    public CitizenAddressEntity withMunicipality(final String municipality) {
        this.municipality = municipality;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public CitizenAddressEntity withCountry(final String country) {
        this.country = country;
        return this;
    }

    public Boolean getEmigrated() {
        return emigrated;
    }

    public void setEmigrated(final Boolean emigrated) {
        this.emigrated = emigrated;
    }

    public CitizenAddressEntity withEmigrated(final Boolean emigrated) {
        this.emigrated = emigrated;
        return this;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(final String addressType) {
        this.addressType = addressType;
    }

    public CitizenAddressEntity withAddressType(final String addressType) {
        this.addressType = addressType;
        return this;
    }

    public Double getXCoordLocal() {
        return xCoordLocal;
    }

    public void setXCoordLocal(final Double xCoordLocal) {
        this.xCoordLocal = xCoordLocal;
    }

    public CitizenAddressEntity withXCoordLocal(final Double xCoordLocal) {
        this.xCoordLocal = xCoordLocal;
        return this;
    }

    public Double getYCoordLocal() {
        return yCoordLocal;
    }

    public void setYCoordLocal(final Double yCoordLocal) {
        this.yCoordLocal = yCoordLocal;
    }

    public CitizenAddressEntity withYCoordLocal(final Double yCoordLocal) {
        this.yCoordLocal = yCoordLocal;
        return this;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CitizenAddressEntity withCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CitizenAddressEntity withUpdatedAt(final OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, citizen, status, nrDate, realEstateDescription, co, address, addressArea,
                addressNumber, addressLetter, apartmentNumber, postalCode, city, county, municipality,
                country, emigrated, addressType, xCoordLocal, yCoordLocal, createdAt, updatedAt);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        final var other = (CitizenAddressEntity) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(citizen, other.citizen) &&
                Objects.equals(status, other.status) &&
                Objects.equals(nrDate, other.nrDate) &&
                Objects.equals(realEstateDescription, other.realEstateDescription) &&
                Objects.equals(co, other.co) &&
                Objects.equals(address, other.address) &&
                Objects.equals(addressArea, other.addressArea) &&
                Objects.equals(addressNumber, other.addressNumber) &&
                Objects.equals(addressLetter, other.addressLetter) &&
                Objects.equals(apartmentNumber, other.apartmentNumber) &&
                Objects.equals(postalCode, other.postalCode) &&
                Objects.equals(city, other.city) &&
                Objects.equals(county, other.county) &&
                Objects.equals(municipality, other.municipality) &&
                Objects.equals(country, other.country) &&
                Objects.equals(emigrated, other.emigrated) &&
                Objects.equals(addressType, other.addressType) &&
                Objects.equals(xCoordLocal, other.xCoordLocal) &&
                Objects.equals(yCoordLocal, other.yCoordLocal) &&
                Objects.equals(createdAt, other.createdAt) &&
                Objects.equals(updatedAt, other.updatedAt);
    }

    @Override
    public String toString() {
        final var builder = new StringBuilder();
        builder.append("CitizenAddressEntity [id=").append(id)
                .append(", citizen=").append(citizen != null ? citizen.getPersonId() : null)
                .append(", status=").append(status)
                .append(", nrDate=").append(nrDate)
                .append(", realEstateDescription=").append(realEstateDescription)
                .append(", co=").append(co)
                .append(", address=").append(address)
                .append(", addressArea=").append(addressArea)
                .append(", addressNumber=").append(addressNumber)
                .append(", addressLetter=").append(addressLetter)
                .append(", apartmentNumber=").append(apartmentNumber)
                .append(", postalCode=").append(postalCode)
                .append(", city=").append(city)
                .append(", county=").append(county)
                .append(", municipality=").append(municipality)
                .append(", country=").append(country)
                .append(", emigrated=").append(emigrated)
                .append(", addressType=").append(addressType)
                .append(", xCoordLocal=").append(xCoordLocal)
                .append(", yCoordLocal=").append(yCoordLocal)
                .append(", createdAt=").append(createdAt)
                .append(", updatedAt=").append(updatedAt)
                .append("]");
        return builder.toString();
    }
}
