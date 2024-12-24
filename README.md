# Event Management System

## Overview

This project is an Event Management System that integrates with Google Calendar to allow users to create, view, update, and delete events from their Google Calendar.
The system also calculates the total hours allocated across events for each week.
This system is built using Spring Boot for the backend and includes OAuth 2.0 authentication with Google.

## Features

- User Authentication: Google OAuth 2.0 authentication for users to sign in with their Google accounts.
- Event Management: Allows users to perform CRUD operations on their Google Calendar events.
- Event Summary: Calculates total hours allocated for events in a selected week.

## Tech Stack

### Frontend:

- Next.js (React)
- Tailwind (CSS)
- Axios
- shadcn/ui

### Backend:

- Spring Boot (Java)
- Spring Security (OAuth2)
- Spring Data JPA (for persistence)
- H2 (In-memory database)
- Spring Data JPA (if needed for user/session persistence)
- Swagger (for API documentation)

### Tools:

- Maven for project build management
- Git for version control

### Authentication:

- Google OAuth2

### Google API:

- Google Calendar API : Google Calendar API for event management

## Prerequisites

Before running the application, make sure you have the following installed:

- Node.js 20 (for frontend)
- Java 21+ (for backend)
- Gradle (for building Spring Boot backend)
- Pnpm (for building Nextjs frontend)

## Setup Instructions

- ### 1. Clone the Repository

  ```
  git clone https://github.com/yourusername/event-management-system.git
  cd event-management-system

  ```

- ### 2. Frontend (Next.js)

  - #### 1. Navigate to the frontend directory::

  ```
  cd event-management/frontend
  ```

  - #### 2. Install dependencies:

  ```
  pnpm install
  ```

  - #### Configure environment variables: Create a .env.local file

  ```
  NEXT_PUBLIC_BASEURL=http://localhost:8080
  ```

  - Run frontend

  ```
  pnpm dev
  ```

  The frontend will be available at http://localhost:3000.

- ### 3. Backend (Spring Boot)

  - #### 1. Navigate to the backend directory:

  ```
  cd event-management/backend
  ```

  - #### 2. Configure the H2 Database
    In application.properties or application.yml, configure the H2 in-memory database:

  ```
  spring.datasource.url={URl}
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.driver-class-name=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=
  spring.h2.console.path=/h2-console
  spring.h2.console.enabled=true
  spring.jpa.hibernate.ddl-auto=create
  spring.jpa.generate-ddl=true
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  ```
  
  - #### 3. Configure the Google OAuth2 Integration
  - Create a Google Developer Console project.
  - Enable the Google Calendar API.
  - Obtain OAuth2 credentials (Client ID and Client Secret).
  - Add Google OAuth configuration in application.properties:

  ```
  spring.security.oauth2.client.registration.google.client-id=YOUR_GOOGLE_CLIENT_ID
  spring.security.oauth2.client.registration.google.client-secret=YOUR_GOOGLE_CLIENT_SECRET
  spring.security.oauth2.client.registration.google.scope=openid,email,profile,https://www.googleapis.com/auth/calendar
  spring.security.oauth2.client.provider.google.token-uri=https://oauth2.googleapis.com/token
  spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/v2/auth?access_type=offline

  redirect_url=http://localhost:3000/dashboard
  app.name=eventmanagement
  client-id=
  client-secret=
  refresh-token=
  ```
  - #### 4. Configure other environment variables
    In application.properties or application.yml, other needed environment variables:

  ```
  spring.application.name=eventmanagement
  login-url=http://localhost:8080/oauth2/authorization/google
  redirect-url-after-successful-login=http://localhost:3000/dashboard
  internal-server-error-message=Something is wrong at the moment please try again
  ```

- #### 5. Install Dependencies:

  ```
  gradle install
  ```

- #### 6. Run the Spring Boot application:

  ```
  ./gradlew bootRun
  ```

  The backend will be available at http://localhost:8080.

- ## Google Calendar API Setup

  ### To interact with the Google Calendar API, follow these steps:

  - #### 1. Go to the Google Developer Console.
  - #### 2. a new project or select an existing project.
  - #### 3. the Google Calendar API.
  - #### 4. Configure OAuth2 credentials under APIs & Services > Credentials and download the JSON file containing your credentials.
  - #### 5. Set up your OAuth2 credentials in both the frontend and backend (as mentioned above).

- ## Authentication Flow

- The user is redirected to Google OAuth2 login when trying to access protected resources.
- After successful authentication, the user is granted access to the application and their Google Calendar data.
- The backend securely stores the user's Google OAuth2 tokens for API calls.
  - ![oauth2 authentication flow](https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F28f8b45a-179e-4d5a-a29a-ce156ca4e784_3706x2366.png)

    - ## API Documentation (Swagger)

      #### The backend API documentation is available through Swagger. You can view and interact with the API endpoints by navigating to:

      ```
      http://localhost:8080/swagger-ui.html
      ```

      This will provide a detailed overview of all available endpoints and their parameters.

      - ## Endpoints

        - ### 1. GET /apiv1/events/get-events
          Fetch a list of all events for the authenticated user. You can also get events by month by providing query params.
      
          Example
          ```
           GET http://localhost:8080/apiv1/events/get-events
          ```
          ```
          GET http://localhost:8080/apiv1/events/get-events?month={month}&year={year}
          ```

        - ### 2. GET /apiv1/events/event-by-id/{id}
          Fetch a event for the authenticated user by the provided id.

          Example
          ```
           GET http://localhost:8080/apiv1/events/event-by-id/{id}
          ```
        - ### 3. POST /apiv1/events/add-events
          Create a new event. You must send a JSON object containing the event details (summary, location, start time, and end time).

          Example
          ```
           GET http://localhost:8080/apiv1/events/get-events
          ```
        - ### 4. PATCH /apiv1/events/update-event
          Update an existing event. You need to specify the eventId and provide updated event details.

          Example
          ```
           PATCH http://localhost:8080/apiv1/events/update-event
          ```
        - ### 5. DELETE /apiv1/events/delete-event/{id}
          Delete an event by its eventId.

          Example
          ```
           DELETE http://localhost:8080/apiv1/events/delete-event/{id}
          ```
        - ### 6. GET /apiv1/events/week-summary
          Get all event in current week and create a summary for total no of hours.

          Example
          ```
           GET http://localhost:8080/apiv1/events/week-summary
          ```

## Error Handling

Invalid credentials: If OAuth fails or the user is not authenticated.
Google API errors: Handle API errors when interacting with Google Calendar (e.g., event not found).
Input validation: Validate inputs for creating and updating events.
Example Error Response:

```

{
message:"No Refresh Token Available.",
path: "/apiv1/events/get-all-events",
statusCode:"401",
timestamp:"12-12-2024:02:33:00"
}

```

## Deployment

  To deploy this application, you can follow these steps:

  1.  Build the frontend with:
      ```
      pnpm run build
      ```
  2. Build the Spring Boot backend as a JAR file with:
     ```
     ./gradlew build
     ```
  3.  Deploy both parts to your desired cloud service (e.g., AWS, Heroku, Google Cloud, etc.).

