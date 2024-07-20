# Login_Email_Api_Appliaction
### Project Description: User Login API with Email and OTP Authentication

#### Project Overview
This project aims to develop a secure and efficient API system for user authentication using email and One-Time Password (OTP). This system allows users to log in by providing their email address, receiving an OTP, and verifying the OTP to gain access. The project emphasizes security and user experience, ensuring a seamless and robust authentication process.

#### Objectives
1. **User Authentication**: Implement a smooth authentication process using email and OTP.
2. **Security**: Ensure the authentication process is secure against common threats like brute-force attacks and unauthorized access.

#### Key Features
1. **Email Registration**:
   - Endpoint to register a new user with an email address and password.
   - Validate the email format and check for duplicates.

2. **OTP Generation and Sending**:
   - Endpoint to request an OTP.
   - Generate a secure OTP.
   - Send the OTP to the user's registered email address using a mock email service.

3. **OTP Verification**:
   - Endpoint to verify the OTP.
   - Authenticate the user if the OTP is valid and within the time limit.

4. **Session Management**:
   - Manage user sessions upon successful OTP verification.
   - Provide session tokens for authenticated users.

5. **Security Measures**:
   - Implement rate limiting on OTP requests to prevent abuse.
   - Use secure algorithms for OTP generation and hashing.
   - Ensure encrypted communication between client and server.

#### Technical Requirements
1. **Backend Framework**: Use Spring Boot.
2. **Database**: Use MySQL to store user data and OTPs.
3. **Email Service**: Integrate with a mock email service for sending OTPs. Instead of sending an actual OTP, it will print the OTP.
4. **Token Management**: Use session management for authenticated users.

#### API Endpoints

1. **User Registration**:
   - `POST /api/register`
     - **Request Body**:
       ```json
       {
         "email": "user@example.com",
         "password": "password"
       }
       ```
     - **Response**:
       ```json
       {
         "message": "Registration successful. Please verify your email."
       }
       ```

2. **Request OTP**:
   - `POST /api/request-otp`
     - **Request Body**:
       ```json
       {
         "email": "user@example.com"
       }
       ```
     - **Response**:
       ```json
       {
         "message": "OTP sent to your email."
       }
       ```

3. **Verify OTP**:
   - `POST /api/verify-otp`
     - **Request Body**:
       ```json
       {
         "email": "user@example.com",
         "otp": "123456"
       }
       ```
     - **Response**:
       ```json
       {
         "message": "Login successful."
       }
       ```

4. **Send Email**:
   - `POST /api/sendemail`
     - **Request Body**:
       ```json
       {
         "to": "recipient@example.com",
         "subject": "Test Subject",
         "message": "This is a test email."
       }
       ```
     - **Response**:
       ```json
       {
         "message": "Email sent successfully."
       }
       ```

#### Project Structure

1. **Controller Layer**: Manages the API endpoints.
2. **Service Layer**: Contains the business logic for user registration, OTP generation, and verification.
3. **Model Layer**: Contains the entity classes for user and OTP.
4. **Repository Layer**: Manages database interactions.

#### Security Considerations
1. **Rate Limiting**: Prevents abuse by limiting the number of OTP requests.
2. **Encryption**: Ensures secure communication between the client and server.
3. **Validation**: Validates email format and checks for duplicates during registration.
4. **Session Management**: Manages user sessions securely.

This project ensures a secure and user-friendly authentication system, leveraging the capabilities of Spring Boot and integrating essential security measures to protect user data.
