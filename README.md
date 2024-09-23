# Event Booking Application

## Project Overview
This is a full-stack event booking application developed using **Java** and the **Spring Boot** framework for the backend, and **MySQL** as the database. The application allows users to browse, book, and manage events, while administrators have the ability to create, update, and delete events. The project includes secure user registration, role-based access control, and comprehensive exception handling.

## Features
- **User Registration & Login**: Secure authentication for users and administrators.
- **Event Management**: Admins can add, edit, delete events; users can view and book events.
- **Role-Based Access Control**: Implemented using Spring Security, with roles for users and admins.
- **Bookmark Events**: Users can bookmark events to access them later.
- **Unit Testing**: Backend functionality tested using JUnit and Mockito.
- **Exception Handling**: Centralized error handling for a seamless user experience.

## Tech Stack

### Backend:
- **Java** (JDK 17)
- **Spring Boot** (Version 2.7.x)
- **Spring Security** for authentication and role-based access control
- **MySQL** for database management
- **JUnit** and **Mockito** for unit testing

### Frontend (in progress):
- Currently working on frontend development using **React** and **JavaScript**.

## Database Schema
The database schema includes tables for:
- **Users**: Stores user details and roles (admin/user).
- **Events**: Stores event information.
- **Bookings**: Tracks event bookings by users.
- **Bookmarks**: Tracks events bookmarked by users.

## Setup and Installation

### Prerequisites:
- Java 17
- MySQL Server
- Maven (for managing dependencies)

### Steps to Run the Project:

1. Clone the repository:
    ```bash
    git clone https://github.com/kaifali8/event-booking-app.git
    ```

2. Navigate to the project directory:
    ```bash
    cd event-booking-app
    ```

3. Set up MySQL:
   - Create a database named `event_booking`.
   - Update the database configuration in `application.properties` with your MySQL credentials.

4. Build and run the project:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

5. The backend will be running on `http://localhost:8080`.

### API Endpoints:
- **/api/users/register** - Register a new user.
- **/api/events/** - Get all available events.
- **/api/bookings/** - Book an event.
- **/api/bookmarks/** - Bookmark an event.

_You can find a full list of available API endpoints in the project documentation._

## To-Do
- Complete the frontend using React.
- Enhance error messages for better user experience.
- Add more features like event recommendations and user notifications.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact
For any queries or contributions, feel free to reach out:
- **Syed Kaif Ali**: kaifali848@gmail.com
- GitHub: [kaifali8](https://github.com/kaifali8)
