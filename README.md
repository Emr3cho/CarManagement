# Car Management System - RESTful API

## Overview
This project implements a RESTful API backend service for a car fleet management system. The API is built to adhere to the OpenAPI definition provided in "Car Management - OpenAPI definition-1.pdf." The frontend application, "car-management-frontend.zip," seamlessly integrates with this backend without modifications.

## Features
The API provides functionalities for managing service centers, cars, and maintenance requests, as outlined below:

### Service Center Management
- **Create, Update, Delete Service Centers**: Allows addition, modification, and removal of service centers.
- **Retrieve Service Center Details**: Fetch detailed information by service center ID.
- **List All Service Centers**: Retrieve a list of all service centers with optional filtering by city.
- **Generate Reports**: Provides statistics on service requests and free capacity for specific date ranges.

### Car Management
- **Create, Update, Delete Cars**: Enables addition, editing, and removal of car records.
- **Retrieve Car Details**: Fetch detailed information by car ID.
- **List All Cars**: Retrieve a list of all cars with filters for brand, servicing center, and production year range.
- **Flexible Service Center Registration**: Each car can be associated with multiple service centers.

### Maintenance Request Management
- **Create, Update Maintenance Requests**: Validates service center availability before creating or updating requests.
- **Delete Requests**: Allows removal of maintenance requests.
- **Retrieve Request Details**: Fetch details of a request by ID.
- **List All Requests**: Retrieve a list of all requests with filters for car, service center, and date range.
- **Generate Reports**: Monthly reports on requests with filters for service center and date range. Includes months with zero requests.

### Data Validation and Error Handling
- Validates request data and returns:
    - **400**: For invalid data.
    - **404**: For missing resources.
- Returns **200** for successful operations.