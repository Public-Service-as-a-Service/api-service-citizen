INSERT INTO citizens (
    person_id,
    personal_number,
    givenname,
    lastname,
    gender,
    civil_status,
    created_at,
    updated_at
) VALUES (
             'fb47e26c-1c27-11ee-be56-0242ac120002',
             '198001011234',
             'Test',
             'Testsson',
             'MALE',
             'SINGLE',
             '2025-01-29 10:58:54',
             '2025-01-29 17:58:54'
         );

INSERT INTO citizen_addresses (
    id,
    person_id,
    status,
    address,
    city,
    postal_code,
    created_at,
    updated_at,
    address_type
) VALUES (
             'c305f904-c9bf-4144-8656-a797acc90b74',
             'fb47e26c-1c27-11ee-be56-0242ac120002',
             'ACTIVE',
             'Test Street 1',
             'Test City 1',
             '12345',
             '2025-01-29 10:58:54',
             '2025-01-29 10:58:54',
             'addressType'
         );