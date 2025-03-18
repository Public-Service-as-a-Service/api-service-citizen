CREATE TABLE citizens
(
    person_id       VARCHAR(255) NOT NULL PRIMARY KEY,
    personal_number VARCHAR(20) UNIQUE,
    givenname       VARCHAR(100),
    lastname        VARCHAR(100),
    gender          VARCHAR(10),
    civil_status    VARCHAR(20),
    nr_date         TIMESTAMP(6),
    classified      VARCHAR(50),
    protected_nr    VARCHAR(50),
    created_at      TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at      TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)
) engine=InnoDB;
CREATE INDEX citizens_classified_index ON citizens (classified);
CREATE INDEX citizens_updated_at_index ON citizens (updated_at);

CREATE TABLE citizen_addresses
(
    id                      VARCHAR(255) NOT NULL PRIMARY KEY,
    person_id               VARCHAR(255),
    status                  VARCHAR(50),
    nr_date                 TIMESTAMP(6),
    real_estate_description LONGTEXT,
    co                      VARCHAR(100),
    address                 VARCHAR(200),
    address_area            VARCHAR(100),
    address_number          VARCHAR(20),
    address_letter          VARCHAR(10),
    apartment_number        VARCHAR(20),
    postal_code             VARCHAR(20),
    city                    VARCHAR(100),
    county                  VARCHAR(100),
    municipality            VARCHAR(100),
    country                 VARCHAR(100),
    emigrated               BOOLEAN,
    address_type            VARCHAR(50),
    x_coord_local          DOUBLE,
    y_coord_local          DOUBLE,
    created_at              TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at              TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    FOREIGN KEY (person_id) REFERENCES citizens (person_id)
) engine=InnoDB;
CREATE INDEX citizen_addresses_status_index ON citizen_addresses (status);
CREATE INDEX citizen_addresses_postal_code_index ON citizen_addresses (postal_code);
CREATE INDEX citizen_addresses_city_index ON citizen_addresses (city);
CREATE INDEX citizen_addresses_address_type_index ON citizen_addresses (address_type);
CREATE INDEX citizen_addresses_updated_at_index ON citizen_addresses (updated_at);

CREATE TABLE custody_children_pupils
(
    id                    VARCHAR(255) NOT NULL PRIMARY KEY,
    person_id             VARCHAR(255),
    personnumber          VARCHAR(20),
    type_of_school        VARCHAR(100),
    un_reg_code          VARCHAR(50),
    un_reg_date          TIMESTAMP(6),
    created_at            TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at            TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    FOREIGN KEY (person_id) REFERENCES citizens (person_id)
) engine=InnoDB;
CREATE INDEX custody_children_pupils_personnumber_index ON custody_children_pupils (personnumber);
CREATE INDEX custody_children_pupils_type_of_school_index ON custody_children_pupils (type_of_school);
CREATE INDEX custody_children_pupils_un_reg_code_index ON custody_children_pupils (un_reg_code);