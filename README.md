# Restaurant Search Application

## Description
An application designed to allow users to browse restaurants in their area, save their favourite restaurants, and leave ratings and reviews. 

## Tech Stack
This project is a local-data driven application. It makes use of the [Yelp Fusion API](https://docs.developer.yelp.com/docs/fusion-intro) for real-time data retrieval. This app was written in Java with the frontend graphical user interface being written with Swing.

## Features
This application support the following functionalities:
- User register and login
- View nearby/recommended restaurants
- Search for restaurants using keywords
- Sort and filter search results
- Add a restaurant to favourites, view favourites, remove a restaurant from favourites
- Leave a review and rating for a restaurant
- Edit password, location, and user avatar
- Log out of user account
  
## Test Coverage
This project was tested with the testing framework TestAPI. It had an overall class coverage of 99%, and a method coverage of 67%.

## Installation
It is recommended to use Java 22 and Maven 11 for installation and running. 
1. Clone this repository.
    ```
    git clone https://github.com/sarahmccxv/Restaurant-Search-Application.git
    ```
2. Navigate to the project directory
    ```
    cd Restaurant-Search-Application
    ```
3. Build the project with Maven.
    ```
    mvn clean install
    ```
4. Run the application
    ```
    mvn exec:java -Dexec.mainClass="app.Main"
    ```

## Demo
For a demo of the functionality of this project, refer to the videos in [this slide deck](https://docs.google.com/presentation/d/1MU2N5LntnoYWtfqQMKW0ER8yH8w6-wVqe-4Idiv6Vzk/edit?usp=sharing).
