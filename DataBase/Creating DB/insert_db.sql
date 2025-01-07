use dormy_db

-- inserting data into Database -------------------------------------
INSERT INTO user_data (first_name, last_name, phone_number, gender, user_type, preference) VALUES
('Rahul', 'Kumar', '9876543210', 'MALE', 'owner', NULL),
('Priya', 'Singh', '9876543211', 'FEMALE', 'tenant', 'Vegetarian food preferred'),
('Amit', 'Sharma', '9876543212', 'MALE', 'owner', NULL),
('Deepika', 'Patel', '9876543213', 'FEMALE', 'tenant', 'Student friendly'),
('Suresh', 'Reddy', '9876543214', 'MALE', 'owner', NULL),
('Anita', 'Verma', '9876543215', 'FEMALE', 'tenant', 'Near tech park'),
('Karthik', 'Raj', '9876543216', 'MALE', 'owner', NULL),
('Meera', 'Nair', '9876543217', 'FEMALE', 'tenant', 'Close to metro'),
('Rajesh', 'Kumar', '9876543218', 'MALE', 'owner', NULL),
('Sneha', 'Gupta', '9876543219', 'FEMALE', 'tenant', 'Quiet neighborhood'),
('Vikram', 'Singh', '9876543220', 'MALE', 'owner', NULL),
('Pooja', 'Sharma', '9876543221', 'FEMALE', 'tenant', 'Good connectivity'),
('Arun', 'Menon', '9876543222', 'MALE', 'owner', NULL),
('Divya', 'Krishna', '9876543223', 'FEMALE', 'tenant', 'Near shopping areas'),
('Sanjay', 'Iyer', '9876543224', 'MALE', 'owner', NULL);



INSERT INTO need_room (user_id, location, room_type, rent, looking_for, self_highlights, description, mobile_no_visibility) VALUES
(2, 'HSR Layout, Bangalore', 'Single', 15000.00, 'Male', 'Working professional, Non-smoker', 'Looking for clean and quiet accommodation', 1),
(4, 'Koramangala, Bangalore', 'Double', 12000.00, 'Female', 'IT professional, Vegetarian', 'Preferred near tech park', 1),
(6, 'Indiranagar, Bangalore', 'Single', 18000.00, 'Male', 'Student, Non-smoker', 'Need furnished room', 0),
(8, 'BTM Layout, Bangalore', 'Double', 14000.00, 'Female', 'Working professional', 'Looking for safe locality', 1),
(10, 'Whitefield, Bangalore', 'Single', 16000.00, 'Male', 'Software engineer', 'Need good connectivity', 1),
(12, 'Electronic City, Bangalore', 'Double', 13000.00, 'Female', 'Bank employee', 'Looking for family PG', 0),
(14, 'JP Nagar, Bangalore', 'Single', 17000.00, 'Male', 'Student', 'Need study friendly environment', 1),
(2, 'Marathahalli, Bangalore', 'Double', 15000.00, 'Female', 'Teacher', 'Looking for peaceful area', 1),
(4, 'Bellandur, Bangalore', 'Single', 19000.00, 'Male', 'IT professional', 'Need AC room', 0),
(6, 'Old Airport Road, Bangalore', 'Double', 16000.00, 'Female', 'Working professional', 'Looking for long term stay', 1),
(8, 'Sarjapur Road, Bangalore', 'Single', 15000.00, 'Male', 'Student', 'Need WiFi facility', 1),
(10, 'CV Raman Nagar, Bangalore', 'Double', 14000.00, 'Female', 'Bank employee', 'Looking for good food facility', 0),
(12, 'Hebbal, Bangalore', 'Single', 16000.00, 'Male', 'Software engineer', 'Need parking facility', 1),
(14, 'Hennur, Bangalore', 'Double', 13000.00, 'Female', 'Teacher', 'Looking for family environment', 1),
(2, 'Yelahanka, Bangalore', 'Single', 15000.00, 'Male', 'Working professional', 'Need furnished room', 0);

INSERT INTO need_roommate (user_id, location, room_type, rent, looking_for, property_highlights, property_amenities, description, mobile_no_visibility) VALUES
(2, 'HSR Layout, Bangalore', 'Double', 8000.00, 'Male', '2BHK furnished apartment', 'AC, WiFi, Power Backup', 'Looking for clean and professional roommate', 1),
(4, 'Koramangala, Bangalore', 'Single', 10000.00, 'Female', '3BHK premium apartment', 'Gym, Swimming Pool, Security', 'Need working professional', 1),
(6, 'Indiranagar, Bangalore', 'Double', 9000.00, 'Male', '2BHK semi-furnished', 'WiFi, Power Backup', 'Student preferred', 0),
(8, 'BTM Layout, Bangalore', 'Single', 11000.00, 'Female', '3BHK luxury apartment', 'AC, Gym, Security', 'Looking for IT professional', 1),
(10, 'Whitefield, Bangalore', 'Double', 8500.00, 'Male', '2BHK fully furnished', 'WiFi, AC, Power Backup', 'Need non-smoker roommate', 1),
(12, 'Electronic City, Bangalore', 'Single', 9500.00, 'Female', '3BHK apartment', 'Security, WiFi', 'Working professional preferred', 0),
(14, 'JP Nagar, Bangalore', 'Double', 8000.00, 'Male', '2BHK new apartment', 'AC, Power Backup', 'Student or working professional', 1),
(2, 'Marathahalli, Bangalore', 'Single', 10000.00, 'Female', '3BHK furnished', 'Gym, Security', 'Need clean and organized person', 1),
(4, 'Bellandur, Bangalore', 'Double', 9000.00, 'Male', '2BHK premium', 'AC, WiFi, Security', 'IT professional preferred', 0),
(6, 'Old Airport Road, Bangalore', 'Single', 11000.00, 'Female', '3BHK luxury', 'All amenities', 'Looking for long term roommate', 1),
(8, 'Sarjapur Road, Bangalore', 'Double', 8500.00, 'Male', '2BHK furnished', 'WiFi, Power Backup', 'Need vegetarian roommate', 1),
(10, 'CV Raman Nagar, Bangalore', 'Single', 9500.00, 'Female', '3BHK apartment', 'Security, Gym', 'Working professional only', 0),
(12, 'Hebbal, Bangalore', 'Double', 8000.00, 'Male', '2BHK semi-furnished', 'Basic amenities', 'Student preferred', 1),
(14, 'Hennur, Bangalore', 'Single', 10000.00, 'Female', '3BHK furnished', 'All facilities', 'Need working woman', 1),
(2, 'Yelahanka, Bangalore', 'Double', 9000.00, 'Male', '2BHK new apartment', 'Modern amenities', 'Looking for professional', 0);


INSERT INTO pg_data (user_id, pg_name, pg_location, gender, services, pg_rules, other_rules, property_description, pg_direction_tip, posted_date, map_link, preferred_tenants, food_availability, gate_closing_time, available_day_schedule, available_time_schedule) VALUES
(1, 'Comfort PG', 'HSR Layout, Bangalore', 'Male', 'WiFi, Laundry, Security, Power Backup, Housekeeping', 'No smoking, No alcohol', 'Guests allowed till 8 PM', 'Modern PG with all amenities', 'Near HSR BDA Complex', '2024-01-02', 'https://maps.google.com/hsr', 'IT Professionals', 1, '23:00:00', 'Monday to Sunday', '10:00 AM to 7:00 PM'),
(3, 'Green View PG', 'Koramangala, Bangalore', 'Female', 'WiFi, AC, Food, Gym', 'No visitors after 9 PM', 'ID proof mandatory', 'Luxurious PG in prime location', 'Opposite Forum Mall', '2024-01-02', 'https://maps.google.com/koramangala', 'Working Women', 1, '22:30:00', 'Monday to Saturday', '9:00 AM to 6:00 PM'),
(5, 'Star PG', 'Indiranagar, Bangalore', 'Male', 'WiFi, Food, Security', 'No pets', 'Monthly advance payment', 'Peaceful environment', 'Near Metro Station', '2024-01-02', 'https://maps.google.com/indiranagar', 'Students', 1, '22:00:00', 'Monday to Sunday', '9:00 AM to 8:00 PM'),
(7, 'Royal PG', 'BTM Layout, Bangalore', 'Female', 'WiFi, AC, Laundry', 'No loud music', 'Biometric entry', 'Premium facilities', '2nd Stage, Near Temple', '2024-01-02', 'https://maps.google.com/btm', 'Working Professionals', 1, '23:00:00', 'All days', '8:00 AM to 9:00 PM'),
(9, 'Metro PG', 'Whitefield, Bangalore', 'Male', 'WiFi, Food, Gym', 'No smoking', 'Security deposit required', 'Near IT Park', 'Opposite Phoenix Mall', '2024-01-02', 'https://maps.google.com/whitefield', 'IT Professionals', 1, '22:30:00', 'Monday to Saturday', '10:00 AM to 6:00 PM'),
(11, 'Lake View PG', 'Electronic City, Bangalore', 'Female', 'WiFi, Security, Food', 'No guests overnight', 'Monthly rent advance', 'Safe and secure', 'Near Phase 1', '2024-01-02', 'https://maps.google.com/ecity', 'Working Women', 1, '22:00:00', 'All days', '9:00 AM to 7:00 PM'),
(13, 'City PG', 'JP Nagar, Bangalore', 'Male', 'WiFi, Laundry, Food', 'No alcohol', 'Quarterly contract', 'Student friendly', '5th Phase', '2024-01-02', 'https://maps.google.com/jpnagar', 'Students', 1, '23:00:00', 'Monday to Sunday', '8:00 AM to 8:00 PM'),
(15, 'Sunshine PG', 'Marathahalli, Bangalore', 'Female', 'WiFi, AC, Security', 'No parties', 'Advance notice for vacating', 'Modern amenities', 'Near Bridge', '2024-01-02', 'https://maps.google.com/marathahalli', 'Working Professionals', 1, '22:30:00', 'All days', '9:00 AM to 6:00 PM'),
(1, 'Garden PG', 'Bellandur, Bangalore', 'Male', 'WiFi, Food, Power Backup', 'No smoking', 'ID verification mandatory', 'Green surroundings', 'Near Central Mall', '2024-01-02', 'https://maps.google.com/bellandur', 'IT Professionals', 1, '23:00:00', 'Monday to Saturday', '10:00 AM to 7:00 PM'),
(3, 'Silicon PG', 'Old Airport Road, Bangalore', 'Female', 'WiFi, Gym, Laundry', 'No loud music', 'Security deposit', 'Premium location', 'Near HAL', '2024-01-02', 'https://maps.google.com/hal', 'Working Women', 1, '22:00:00', 'All days', '8:00 AM to 9:00 PM'),
(5, 'Tech PG', 'Sarjapur Road, Bangalore', 'Male', 'WiFi, Food, Security', 'No pets', 'Monthly payment', 'IT friendly', 'Near IT Hub', '2024-01-02', 'https://maps.google.com/sarjapur', 'IT Professionals', 1, '23:00:00', 'Monday to Sunday', '9:00 AM to 8:00 PM'),
(7, 'Prime PG', 'CV Raman Nagar, Bangalore', 'Female', 'WiFi, AC, Food', 'No visitors', 'Advance payment', 'Safe location', 'Near Main Road', '2024-01-02', 'https://maps.google.com/cvraman', 'Working Women', 1, '22:30:00', 'All days', '10:00 AM to 6:00 PM'),
(9, 'Smart PG', 'Hebbal, Bangalore', 'Male', 'WiFi, Laundry, Security', 'No alcohol', 'Monthly contract', 'Modern facilities', 'Near Flyover', '2024-01-02', 'https://maps.google.com/hebbal', 'Students', 1, '22:00:00', 'Monday to Saturday', '9:00 AM to 7:00 PM'),
(11, 'Cloud PG', 'Hennur, Bangalore', 'Female', 'WiFi, Food, Power Backup', 'No parties', 'Security deposit', 'Peaceful area', 'Main Road', '2024-01-02', 'https://maps.google.com/hennur', 'Working Professionals', 1, '23:00:00', 'All days', '8:00 AM to 8:00 PM'),
(13, 'Nest PG', 'Yelahanka, Bangalore', 'Male', 'WiFi, AC, Security', 'No smoking', 'Advance notice', 'Student friendly', 'Near New Town', '2024-01-02', 'https://maps.google.com/yelahanka', 'Students', 1, '22:30:00', 'Monday to Sunday', '9:00 AM to 6:00 PM');


INSERT INTO pg_room (pg_id, occupancy_type, room_availability, deposit_amount, room_amenities) VALUES
(1, 'Single', 1, 15000.00, 'AC, Attached Bathroom, Study Table'),
(2, 'Double', 1, 12000.00, 'Fan, Common Bathroom, Wardrobe'),
(3, 'Triple', 1, 10000.00, 'AC, Attached Bathroom, Balcony'),
(4, 'Single', 1, 18000.00, 'AC, Attached Bathroom, WiFi'),
(5, 'Double', 1, 14000.00, 'Fan, Common Bathroom, Study Table'),
(6, 'Single', 1, 16000.00, 'AC, Attached Bathroom, Wardrobe'),
(7, 'Triple', 1, 12000.00, 'Fan, Common Bathroom, Balcony'),
(8, 'Double', 1, 13000.00, 'AC, Attached Bathroom, Study Table'),
(9, 'Single', 1, 20000.00, 'AC, Attached Bathroom, WiFi'),
(10, 'Double', 1, 15000.00, 'Fan, Common Bathroom, Wardrobe'),
(11, 'Triple', 1, 11000.00, 'AC, Attached Bathroom, Balcony'),
(12, 'Single', 1, 19000.00, 'AC, Attached Bathroom, Study Table'),
(13, 'Double', 1, 14000.00, 'Fan, Common Bathroom, WiFi'),
(14, 'Single', 1, 17000.00, 'AC, Attached Bathroom, Wardrobe'),
(15, 'Triple', 1, 13000.00, 'Fan, Common Bathroom, Balcony');


INSERT INTO rental (rental_id, room_type, tenant_type, property_type, bhk_type, property_size, facing, property_age, available_from, vacancy_floor, total_floor, location_street, map_link, direction_tip, expected_rent, negotiable, expected_deposit, monthly_maintenance, furnishing, parking, room_details, property_rules, property_description, available_day_schedule, available_time_schedule) VALUES
(1, 'Single Room', 'Bachelor', 'Apartment', '1BHK', 650, 'East', 2, '2024-02-01', 3, 8, 'HSR Layout Main Road, Bangalore', 'https://maps.google.com/hsr1', 'Near HSR Food Court', 15000.00, 1, 50000.00, 2000.00, 'Semi-Furnished', 'Two Wheeler Only', 'Well ventilated room with balcony', 'No pets, No parties', 'Modern apartment in gated community', 'Monday to Saturday', '10:00 AM to 6:00 PM'),
(2, 'Double Room', 'Family', 'Independent', '2BHK', 850, 'North', 5, '2024-03-01', 2, 10, 'Koramangala 6th Block, Bangalore', 'https://maps.google.com/koramangala', 'Near Forum Mall', 25000.00, 0, 100000.00, 3000.00, 'Fully-Furnished', 'Car and Two Wheeler', 'Spacious and well-lit', 'No smoking, No loud music', 'Prime location with all amenities', 'Monday to Sunday', '9:00 AM to 7:00 PM'),
(3, 'Triple Room', 'Student', 'Apartment', '3BHK', 1200, 'West', 3, '2024-01-15', 4, 12, 'Indiranagar 100ft Road, Bangalore', 'https://maps.google.com/indiranagar', 'Near Metro Station', 18000.00, 1, 60000.00, 2500.00, 'Semi-Furnished', 'Two Wheeler Only', 'Student-friendly environment', 'No pets, No parties', 'Close to educational institutions', 'Monday to Friday', '8:00 AM to 5:00 PM'),
(4, 'Single Room', 'Bachelor', 'Apartment', '1BHK', 700, 'South', 1, '2024-02-10', 1, 5, 'BTM Layout 2nd Stage, Bangalore', 'https://maps.google.com/btm', 'Near Silk Board', 14000.00, 1, 40000.00, 1500.00, 'Semi-Furnished', 'Two Wheeler Only', 'Compact and cozy', 'No smoking', 'Affordable and convenient', 'Monday to Saturday', '10:00 AM to 6:00 PM'),
(5, 'Double Room', 'Family', 'Independent', '2BHK', 900, 'East', 4, '2024-03-05', 3, 8, 'Whitefield Main Road, Bangalore', 'https://maps.google.com/whitefield', 'Near ITPL', 28000.00, 0, 100000.00, 3500.00, 'Fully-Furnished', 'Car and Two Wheeler', 'Spacious and modern', 'No loud music', 'Ideal for families', 'Monday to Sunday', '9:00 AM to 7:00 PM'),
(6, 'Single Room', 'Bachelor', 'Apartment', '1BHK', 600, 'North', 2, '2024-02-15', 2, 6, 'Electronic City Phase 1, Bangalore', 'https://maps.google.com/ecity', 'Near Tech Park', 16000.00, 1, 45000.00, 2000.00, 'Semi-Furnished', 'Two Wheeler Only', 'Modern amenities', 'No pets', 'Tech park vicinity', 'Monday to Saturday', '9:00 AM to 6:00 PM'),
(7, 'Triple Room', 'Student', 'Independent', '3BHK', 1100, 'West', 3, '2024-02-20', 3, 8, 'Marathahalli, Bangalore', 'https://maps.google.com/marathahalli', 'Near Bridge', 20000.00, 1, 70000.00, 2500.00, 'Fully-Furnished', 'Two Wheeler Only', 'Student accommodation', 'No parties', 'Near colleges', 'Monday to Friday', '8:00 AM to 7:00 PM'),
(8, 'Double Room', 'Family', 'Apartment', '2BHK', 800, 'South', 5, '2024-03-10', 4, 10, 'JP Nagar 6th Phase, Bangalore', 'https://maps.google.com/jpnagar', 'Near Metro', 22000.00, 0, 80000.00, 3000.00, 'Semi-Furnished', 'Car Parking', 'Family friendly', 'No smoking', 'Peaceful locality', 'Monday to Sunday', '10:00 AM to 8:00 PM'),
(9, 'Single Room', 'Bachelor', 'Independent', '1BHK', 550, 'East', 1, '2024-02-25', 1, 4, 'Bellandur, Bangalore', 'https://maps.google.com/bellandur', 'Near Lake', 17000.00, 1, 50000.00, 2000.00, 'Fully-Furnished', 'Two Wheeler Only', 'Lake view', 'No pets', 'Premium location', 'Monday to Saturday', '9:00 AM to 7:00 PM'),
(10, 'Double Room', 'Family', 'Apartment', '2BHK', 950, 'North', 4, '2024-03-15', 5, 12, 'Sarjapur Road, Bangalore', 'https://maps.google.com/sarjapur', 'Near Mall', 24000.00, 0, 90000.00, 3500.00, 'Semi-Furnished', 'Car and Two Wheeler', 'Spacious rooms', 'No loud music', 'Gated community', 'Monday to Sunday', '8:00 AM to 8:00 PM'),
(11, 'Triple Room', 'Student', 'Independent', '3BHK', 1300, 'West', 2, '2024-02-28', 2, 6, 'CV Raman Nagar, Bangalore', 'https://maps.google.com/cvraman', 'Near College', 21000.00, 1, 75000.00, 2500.00, 'Fully-Furnished', 'Two Wheeler Only', 'Student friendly', 'No parties', 'Educational hub', 'Monday to Friday', '9:00 AM to 6:00 PM'),
(12, 'Single Room', 'Bachelor', 'Apartment', '1BHK', 625, 'South', 3, '2024-03-20', 3, 8, 'Hebbal, Bangalore', 'https://maps.google.com/hebbal', 'Near Ring Road', 16000.00, 1, 48000.00, 2000.00, 'Semi-Furnished', 'Two Wheeler Only', 'Good connectivity', 'No pets', 'Modern amenities', 'Monday to Saturday', '10:00 AM to 7:00 PM'),
(13, 'Double Room', 'Family', 'Independent', '2BHK', 875, 'East', 5, '2024-03-25', 4, 10, 'Hennur, Bangalore', 'https://maps.google.com/hennur', 'Near Main Road', 23000.00, 0, 85000.00, 3000.00, 'Fully-Furnished', 'Car Parking', 'Family oriented', 'No smoking', 'Peaceful area', 'Monday to Sunday', '9:00 AM to 8:00 PM'),
(14, 'Triple Room', 'Student', 'Apartment', '3BHK', 1150, 'North', 2, '2024-03-01', 5, 12, 'Yelahanka, Bangalore', 'https://maps.google.com/yelahanka', 'Near University', 19000.00, 1, 65000.00, 2500.00, 'Semi-Furnished', 'Two Wheeler Only', 'Study friendly', 'No parties', 'University zone', 'Monday to Friday', '8:00 AM to 6:00 PM'),
(15, 'Single Room', 'Bachelor', 'Independent', '1BHK', 575, 'West', 1, '2024-03-30', 2, 6, 'Banashankari, Bangalore', 'https://maps.google.com/banashankari', 'Near Temple', 15000.00, 1, 45000.00, 1800.00, 'Fully-Furnished', 'Two Wheeler Only', 'Peaceful locality', 'No pets', 'Traditional area', 'Monday to Saturday', '9:00 AM to 7:00 PM');



INSERT INTO bookmarked (user_id, pg_id, rental_id) VALUES
(1, 1, NULL),
(2, 2, NULL),
(3, 3, NULL),
(4, 4, NULL),
(5, 5, NULL),
(6, NULL, 1),
(7, NULL, 2),
(8, NULL, 3),
(9, NULL, 4),
(10, NULL, 5),
(11, 6, NULL),
(12, 7, NULL),
(13, 8, NULL),
(14, 9, NULL),
(15, 10, NULL);
