CREATE DATABASE job_system;
USE job_system;
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(15),
    user_type ENUM('求职者', '招聘者', '管理员') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE jobs (
    job_id INT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL,
    job_title VARCHAR(100) NOT NULL,
    job_description TEXT,
    location VARCHAR(100),
    salary DECIMAL(10, 2),
    posted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    employer_id INT NOT NULL,
    FOREIGN KEY (employer_id) REFERENCES users(user_id) ON DELETE CASCADE
);
CREATE TABLE applications (
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    job_id INT NOT NULL,
    applicant_id INT NOT NULL,
    status ENUM('待审核', '通过', '拒绝') DEFAULT '待审核',
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (job_id) REFERENCES jobs(job_id) ON DELETE CASCADE,
    FOREIGN KEY (applicant_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE VIEW job_info_view AS
SELECT j.job_id, j.job_title, j.company_name, j.salary, j.location, j.posted_at
FROM jobs j;

CREATE VIEW applicant_status_view AS
SELECT u.username, j.job_title, a.status, a.applied_at
FROM applications a
JOIN users u ON a.applicant_id = u.user_id
JOIN jobs j ON a.job_id = j.job_id;
