# dataai
Code Assignment for data.ai

This is an assignment for a job interview for Data.ai. Here is the description of the assignment:
https://docs.google.com/document/d/1dyULPYcMorvI-bRT42uL1GrcH_jCYS9iVJWoyTBHUhs/edit?usp=sharing

In this assignment, the following stacks are followed:

- MVVM
- Data Binding
- Lifecycles
- LiveData
- Navigation & SafeArgs
- ViewModel
- Coroutines

And following 3rd party libraries are used to make things easier.
- Koin (Dependency Injection)
- Retrofit (Network Layer)
- Gson (Json parsing)
- Glide (Image loading)
- Firebase Crashlytics & Analytics (monitoring)

Some notes for code reviewers:
- I decided that having a single activity with two fragments (Main/Detail) is best to present different stacks and my coding habits.
- Only 2048 data was being asked in the assignment, but a for a better presentation I chose to get products data from `sharing\products` endpoint first, then I fetched the app details of each product through `app/{productId}/details` endpoint. The api is not optimal for this kind of data and it required too many requests just to show unused data, but I thought it was important for a nice presentation of api requests. In the end, only 2048 app is enabled and the rest were disabled according to the `status` value of the api results. Please ignore the slow performance of the main screen.
- For the detail screen, I fetched data through `ProductSales` endpoint with the country breakdown, sorted them descending by download count. I only presented download/update/sales data for units and sales/refund data for revenue. Because those were the related data for 2048 app.
- Although they are not much required and very detailed, I wanted to add some code parts on purpose just to present that they are required in a larger scope like:
  - Logging & Crashlytics
  - Analytics
  - Mappers & Different Api - UI model classes
  - Localization


What can be improved in this assignment?
- Initially, I was planning to show some charts on the detail screen, but unfortunately, I didn't have enough time to accomplish that. I can implement it if it is required though
- Adding a continuous integration, running tests for each commit


