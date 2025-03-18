INSERT INTO citizens (
    person_id,
    personal_number,
    givenname,
    lastname,
    gender,
    civil_status,
    nr_date,
    classified,
    protected_nr,
    created_at,
    updated_at
) VALUES
      ('fb47e26c-1c27-11ee-be56-0242ac120002', '198001011234', 'Test1', 'Testsson1', 'MALE', 'MARRIED', '2025-01-29 09:30:15', NULL, NULL, '2025-01-29 09:30:15', '2025-01-29 09:30:15'),
      ('c31d362e-1c27-11ee-be56-0242ac120002', '198001021234', 'Test2', 'Testsson2', 'FEMALE', 'SINGLE', '2025-01-29 09:30:15', 'CLASSIFIED', 'PROTECTED', '2025-01-29 09:30:15', '2025-01-29 09:30:15');

INSERT INTO citizen_addresses (
    id,
    person_id,
    status,
    address,
    city,
    postal_code,
    created_at,
    updated_at
) VALUES
      ('c305f904-c9bf-4144-8656-a797acc90b74', 'fb47e26c-1c27-11ee-be56-0242ac120002', 'ACTIVE', 'Test Street 1', 'Test City 1', '12345', '2025-01-29 09:30:15', '2025-01-29 09:30:15'),
      ('d405f904-c9bf-4144-8656-a797acc90b75', 'c31d362e-1c27-11ee-be56-0242ac120002', 'ACTIVE', 'Test Street 2', 'Test City 2', '54321', '2025-01-29 09:30:15', '2025-01-29 09:30:15');

INSERT INTO custody_children_pupils (
    id,
    person_id,
    personnumber,
    type_of_school,
    created_at,
    updated_at
) VALUES
    ('e505f904-c9bf-4144-8656-a797acc90b76', 'fb47e26c-1c27-11ee-be56-0242ac120002', '201001011234', 'PRIMARY_SCHOOL', '2025-01-29 09:30:15', '2025-01-29 09:30:15');