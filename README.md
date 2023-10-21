# CSC207Project
For the project, we would like to design a **restaurant searching and recommendation software**.

Our software specifications are as follows:

- New users can register their account by inputting a username, a password, and repeat the password again to make sure the password is consistent.
- Existing users can log in with their username and password, and log out if they want.
- Existing users can reset their passwords after they logged in or the code sent via a confirmation email if they forgot their original password.
- Existing users can select the city they are in.
- List out restaurants in the city where the user is, and more specifically:
  - Display reviews including the ratings, and other relevant information (such as restaurant category and consumption per person)
  - Rank the restaurants by the user’s preference，like which kind of restaurant.
  - Users can sort and filter restaurants by the category of restaurant, the consumption per person and other options.
  - Users can add and remove certain restaurants from their Favourites Lists.
  - Users can write reviews and add ratings to a restaurants.
  - Users can review their history of reviews and ratings.


https://api.yelp.com this is the **link of the API** our team can use related to the domain.

JDBC is the API we are going to use for database.

Below are results of using **hoppscotch** to try out the API and reference results using the API.

  - {
  "categories": [
  {
  "alias": "delis",
  "title": "Delis"
  },
  {
  "alias": "fooddeliveryservices",
  "title": "Food Delivery Services"
  },
  {
  "alias": "couriers",
  "title": "Couriers & Delivery Services"
  }
  ],
  "businesses": [
  {
  "id": "ED3L1nbpWEDePN5cmTQ5cg",
  "name": "Delah Coffee"
  },
  {
  "id": "vu6PlPyKptsT6oEq50qOzA",
  "name": "Delarosa Downtown"
  },
  {
  "id": "QQkroHtwS96-dzMpT4a-DA",
  "name": "Delarosa Marina"
  }
  ],
  "terms": [
  {
  "text": "Delivery"
  },
  {
  "text": "Delivery Food"
  },
  {
  "text": "Delicatessen"
  }
  ]
  }
  - {
    "total": 1316,
    "businesses": [
    {
    "rating": 4.5,
    "price": "$$",
    "phone": "+14154212337",
    "id": "molinari-delicatessen-san-francisco",
    "categories": [
    {
    "alias": "delis",
    "title": "Delis"
    }
    ],
    "review_count": 910,
    "name": "Molinari Delicatessen",
    "url": "https://www.yelp.com/biz/molinari-delicatessen-san-francisco",
    "coordinates": {
    "latitude": 37.7983818054199,
    "longitude": -122.407821655273
    },
    "image_url": "http://s3-media4.fl.yelpcdn.com/bphoto/6He-NlZrAv2mDV-yg6jW3g/o.jpg",
    "location": {
    "city": "San Francisco",
    "country": "US",
    "address2": "",
    "address3": "",
    "state": "CA",
    "address1": "373 Columbus Ave",
    "zip_code": "94133"
    }
    },
    // ...
    ]
    }
  - {
    "reviews": [
    {
    "url": "https://www.yelp.com/biz/north-india-restaurant-san-francisco?hrid=AeVAkQgueu6JtYtU4r3Jrg",
    "text": "This place is really pretty and I really love this place. My friends and me came here yesterday. The food is superb, the service is impeccable (mostly) and...",
    "user": {
    "image_url": "",
    "name": "Hoang V."
    },
    "rating": 5
    },
    {
    "url": "https://www.yelp.com/biz/north-india-restaurant-san-francisco?hrid=6tsz9tl7HAiOcYj_fGrsCg",
    "text": "Went there for the first time Saturday Evening,everything is great, the ambiance is outstanding for this location, tried the mulliatawny soup for starters...",
    "user": {
    "image_url": "http://s3-media2.fl.yelpcdn.com/photo/O1ZuPKBhwxHAT60XZksWHQ/o.jpg",
    "name": "Winston P."
    },
    "rating": 5
    },
    {
    "url": "https://www.yelp.com/biz/north-india-restaurant-san-francisco?hrid=3b3-zDKfomV-1qR3Z0jmQw",
    "text": "I came in here for the $9.95 lunch buffet the day after it opened.  It is the old Tara space and I like how it has been opened up to accommodate many more...",
    "user": {
    "image_url": "http://s3-media1.fl.yelpcdn.com/photo/bQRonQWaxInb7eKAtMjf3A/o.jpg",
    "name": "Ronita J."
    },
    "rating": 4
    }
    ],
    "total": 3
    }