package se.sundsvall.citizen.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Schema(description = "Citizen address model")
public class CitizenAddress {

    @Schema(description = "Status", nullable = true)
    private String status;

    @Schema(description = "NR date", nullable = true)
    private String nrDate;

    @Schema(description = "Real estate description", nullable = true)
    private String realEstateDescription;

    @Schema(description = "Care of address", nullable = true)
    private String co;

    @Schema(description = "Address", nullable = true)
    private String address;

    @Schema(description = "Address area", nullable = true)
    private String addressArea;

    @Schema(description = "Address number", nullable = true)
    private String addressNumber;

    @Schema(description = "Address letter", nullable = true)
    private String addressLetter;

    @Schema(description = "Apartment number", nullable = true)
    private String apartmentNumber;

    @Schema(description = "Postal code", nullable = true)
    private String postalCode;

    @Schema(description = "City", nullable = true)
    private String city;

    @Schema(description = "County", nullable = true)
    private String county;

    @Schema(description = "Municipality", nullable = true)
    private String municipality;

    @Schema(description = "Country", nullable = true)
    private String country;

    @Schema(description = "Emigrated status", nullable = true)
    private Boolean emigrated;

    @Schema(description = "Address type", nullable = true)
    private String addressType;

    @Schema(description = "Local X coordinate", nullable = true)
    private Double xCoordLocal;

    @Schema(description = "Local Y coordinate", nullable = true)
    private Double yCoordLocal;

    public static CitizenAddress create() {
        return new CitizenAddress();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CitizenAddress withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getNrDate() {
        return nrDate;
    }

    public void setNrDate(String nrDate) {
        this.nrDate = nrDate;
    }

    public CitizenAddress withNrDate(String nrDate) {
        this.nrDate = nrDate;
        return this;
    }

    public String getRealEstateDescription() {
        return realEstateDescription;
    }

    public void setRealEstateDescription(String realEstateDescription) {
        this.realEstateDescription = realEstateDescription;
    }

    public CitizenAddress withRealEstateDescription(String realEstateDescription) {
        this.realEstateDescription = realEstateDescription;
        return this;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public CitizenAddress withCo(String co) {
        this.co = co;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CitizenAddress withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public CitizenAddress withAddressArea(String addressArea) {
        this.addressArea = addressArea;
        return this;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public CitizenAddress withAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
        return this;
    }

    public String getAddressLetter() {
        return addressLetter;
    }

    public void setAddressLetter(String addressLetter) {
        this.addressLetter = addressLetter;
    }

    public CitizenAddress withAddressLetter(String addressLetter) {
        this.addressLetter = addressLetter;
        return this;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public CitizenAddress withApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public CitizenAddress withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CitizenAddress withCity(String city) {
        this.city = city;
        return this;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public CitizenAddress withCounty(String county) {
        this.county = county;
        return this;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public CitizenAddress withMunicipality(String municipality) {
        this.municipality = municipality;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CitizenAddress withCountry(String country) {
        this.country = country;
        return this;
    }

    public Boolean getEmigrated() {
        return emigrated;
    }

    public void setEmigrated(Boolean emigrated) {
        this.emigrated = emigrated;
    }

    public CitizenAddress withEmigrated(Boolean emigrated) {
        this.emigrated = emigrated;
        return this;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public CitizenAddress withAddressType(String addressType) {
        this.addressType = addressType;
        return this;
    }

    public Double getXCoordLocal() {
        return xCoordLocal;
    }

    public void setXCoordLocal(Double xCoordLocal) {
        this.xCoordLocal = xCoordLocal;
    }

    public CitizenAddress withXCoordLocal(Double xCoordLocal) {
        this.xCoordLocal = xCoordLocal;
        return this;
    }

    public Double getYCoordLocal() {
        return yCoordLocal;
    }

    public void setYCoordLocal(Double yCoordLocal) {
        this.yCoordLocal = yCoordLocal;
    }

    public CitizenAddress withYCoordLocal(Double yCoordLocal) {
        this.yCoordLocal = yCoordLocal;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, nrDate, realEstateDescription, co, address, addressArea,
                addressNumber, addressLetter, apartmentNumber, postalCode, city, county,
                municipality, country, emigrated, addressType, xCoordLocal, yCoordLocal);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CitizenAddress other = (CitizenAddress) obj;
        return Objects.equals(status, other.status) &&
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
                Objects.equals(yCoordLocal, other.yCoordLocal);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("CitizenAddress [status=").append(status)
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
                .append("]").toString();
    }
}