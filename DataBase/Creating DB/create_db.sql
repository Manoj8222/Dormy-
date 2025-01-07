-- Create database
CREATE DATABASE IF NOT EXISTS dormy_db;
USE dormy_db;

-- Create user_data table
CREATE TABLE user_data (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
    gender ENUM('MALE', 'FEMALE') NOT NULL,
    profile_photo LONGBLOB,
    avatar LONGBLOB,
    user_type ENUM('owner', 'tenant') NOT NULL,
    preference TEXT
);
-- Create need_room table
CREATE TABLE need_room (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT DEFAULT NULL,
    room_city VARCHAR(45) ,
    room_area VARCHAR(45)  ,
    location VARCHAR(100) NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    rent DECIMAL(10,2) NOT NULL,
    looking_for VARCHAR(100) NOT NULL,
    self_highlights TEXT DEFAULT NULL,
    description TEXT DEFAULT NULL,
    mobile_no_visibility TINYINT(1) DEFAULT '1',
    FOREIGN KEY (user_id) REFERENCES user_data(user_id)
);
-- Create need_roommate table
CREATE TABLE need_roommate (
    roommate_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT DEFAULT NULL,
    room_city VARCHAR(45) ,
    room_area VARCHAR(45)  ,
    location VARCHAR(100) NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    rent DECIMAL(10,2) NOT NULL,
    looking_for VARCHAR(100) NOT NULL,
    property_highlights TEXT DEFAULT NULL,
    image_1 VARCHAR(255) DEFAULT NULL,
    image_2 VARCHAR(255) DEFAULT NULL,
    image_3 VARCHAR(255) DEFAULT NULL,
    property_amenities TEXT DEFAULT NULL,
    description TEXT DEFAULT NULL,
    mobile_no_visibility TINYINT(1) DEFAULT '1',
    FOREIGN KEY (user_id) REFERENCES user_data(user_id)
);

-- Create pg_data table
CREATE TABLE pg_data (
    pg_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    pg_name VARCHAR(100) NOT NULL,
    image_1 VARCHAR(255) DEFAULT NULL,
    image_2 VARCHAR(255) DEFAULT NULL,
    image_3 VARCHAR(255) DEFAULT NULL,
    image_4 VARCHAR(255) DEFAULT NULL,
    gender ENUM('Male', 'Female', 'Any') NOT NULL,
    services TEXT DEFAULT NULL,
    pg_rules TEXT DEFAULT NULL,
    other_rules TEXT DEFAULT NULL,
    property_description TEXT DEFAULT NULL,
    pg_direction_tip TEXT DEFAULT NULL,
    posted_date DATE DEFAULT NULL,
    pg_city VARCHAR(45) ,
    pg_area VARCHAR(45) ,
    pg_location VARCHAR(255) NOT NULL,
    map_link VARCHAR(255) DEFAULT NULL,
    preferred_tenants VARCHAR(100) DEFAULT NULL,
    food_availability VARCHAR(45) DEFAULT NULL,
    gate_closing_time TIME DEFAULT NULL,
    available_day_schedule VARCHAR(100) DEFAULT NULL,
    available_time_schedule VARCHAR(100) DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES user_data(user_id)
);

-- Create pg_room table
CREATE TABLE pg_room (
    pg_room_id INT PRIMARY KEY AUTO_INCREMENT,
    pg_id INT,
    occupancy_type VARCHAR(50) NOT NULL,
    room_availability TINYINT(1) DEFAULT '1',
    rent DECIMAL(10,2) NOT NULL,
    room_amenities TEXT,
    FOREIGN KEY (pg_id) REFERENCES pg_data(pg_id)
);

-- Create rental table
CREATE TABLE rental (
    rental_id INT PRIMARY KEY AUTO_INCREMENT,
    room_type VARCHAR(50) NOT NULL,
    tenant_type VARCHAR(50) NOT NULL,
    property_type VARCHAR(50) NOT NULL,
    bhk_type VARCHAR(20) NOT NULL,
    property_size INT,
    facing VARCHAR(50),
    property_age INT,
    available_from DATE,
    vacancy_floor INT,
    total_floor INT,
    rental_city VARCHAR(45),
    rental_area VARCHAR(45),
    location_street VARCHAR(255) NOT NULL,
    map_link VARCHAR(255),
    direction_tip TEXT,
    expected_rent DECIMAL(10,2) NOT NULL,
    negotiable TINYINT(1) DEFAULT '0',
    expected_deposit DECIMAL(10,2) NOT NULL,
    monthly_maintenance DECIMAL(10,2),
    furnishing VARCHAR(50),
    parking VARCHAR(50),
    room_details TEXT,
    property_rules TEXT,
    property_description TEXT,
    image_1 VARCHAR(255),
    image_2 VARCHAR(255),
    image_3 VARCHAR(255),
    image_4 VARCHAR(255),
    available_day_schedule VARCHAR(100),
    available_time_schedule VARCHAR(100)
);

-- Create bookmarked table
CREATE TABLE bookmarked (
    bookmarked_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    pg_id INT,
    rental_id INT,
    FOREIGN KEY (user_id) REFERENCES user_data(user_id),
    FOREIGN KEY (pg_id) REFERENCES pg_data(pg_id),
    FOREIGN KEY (rental_id) REFERENCES rental(rental_id)
);
