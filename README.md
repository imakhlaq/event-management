# Event Management System

## Overview

This project is an Event Management System that integrates with Google Calendar to allow users to create, view, update, and delete events from their Google Calendar.
The system also calculates the total hours allocated across events for each week.
This system is built using Spring Boot for the backend and includes OAuth 2.0 authentication with Google.

## Features

- User Authentication: Google OAuth 2.0 authentication for users to sign in with their Google accounts.
- Event Management: Allows users to perform CRUD operations on their Google Calendar events.
- Event Summary: Calculates total hours allocated for events in a selected week.
- Frontend: A nextjs interface for interacting with the backend.

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
- H2 Database for InMemoryStorage
- Swagger (for API documentation)

### Authentication:

- Google OAuth2

### Google API:

- Google Calendar API

## Prerequisites

Before running the application, make sure you have the following installed:

- Node.js 20 (for frontend)
- Java 21+ (for backend)
- Gradle (for building Spring Boot backend)
- Pnpm (for building Nextjs frontend)

## Setup

- ### 1. Frontend (Next.js)

  - #### 1. Clone the repository:

  ```
  git clone https://github.com/imakhlaq/event-management
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

- ### 2. Backend (Spring Boot)

  - #### 1. Navigate to the backend directory:

  ```
  cd event-management/backend
  ```

  - #### 2. Configure Google OAuth2: Create a application.properties or application.yml file and configure the OAuth2 login:

  ```
  spring.application.name=eventmanagement
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

  spring.security.oauth2.client.registration.google.client-id=
  spring.security.oauth2.client.registration.google.client-secret=
  spring.security.oauth2.client.registration.google.scope=openid,email,profile,https://www.googleapis.com/auth/calendar
  spring.security.oauth2.client.provider.google.token-uri=

  redirect_url=http://localhost:3000/dashboard
  app.name=eventmanagement
  client-id=
  client-secret=
  refresh-token=

  ```

  - #### 3. Build the Spring Boot project:

  ```
  mvn clean install
  ```

  - #### 4. Run the Spring Boot application:

  ```
  mvn spring-boot:run
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

- ## API Documentation (Swagger)

#### The backend API documentation is available through Swagger. You can view and interact with the API endpoints by navigating to:

```
http://localhost:8080/swagger-ui.html
```

This will provide a detailed overview of all available endpoints and their parameters.

- ## Endpoints

- ### 1. Google Calendar CRUD Operations

  - #### Create an Event: POST /api/calendar/events
  - #### Get Events: GET /api/calendar/events
  - #### Update an Event: PUT /api/calendar/events/{eventId}
  - #### Delete an Event: DELETE /api/calendar/events/{eventId}

- ## Deployment

To deploy this application, you can follow these steps:

1.  Build the frontend with:

    ```
    npm run build
    ```

2.  Build the Spring Boot backend as a JAR file with:

    ```
    mvn clean package
    ```

3.  Deploy both parts to your desired cloud service (e.g., AWS, Heroku, Google Cloud, etc.).
