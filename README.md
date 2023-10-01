# CSC207Project
For Week3 Design Question

- The **problem domain** that we would like to focus on is data analysis of the stock market data.

- We would like to build an application that provides the following services:
  - Retrieve raw stock data from an existing API.
  - Create visualizations of time series of one or more stocks.
    - Different time series might be put on a single plot to make comparison.
  - Return common summary statistics based on mathematical models; for example, the average price within a specified period of time.
  - Generate forecasts using different statistical methods, which users will be provided a list of methods to select from.
  - A user register and login system where users can:
    - Create a watchlist of stocks that the user can manage the stocks he/she is interested in (adding new stocks or delete uninterested one).

- https://www.alphavantage.co/documentation/ this is the **link to the documentation for an API** our team can use related to the domain.

- Below is **screenshot of using a tool to try out the API**

![image-20230927222601181](https://i.imgur.com/MOpBqAF.png)

- Below is an **Example output of running our Java code**

  ![image-20230927223320220](https://i.imgur.com/Abz9yXP.png)

- Below is a list of technical problems so far:
  - In terms of the user register, login and tracking functions, we are still exploring the possibility of using a Java SQL API so that a database can be created and maintained to store these data.
