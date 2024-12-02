# Joe's Bookstore
## Contributors:
* William Gao (williamrygao)
* Roy Gal (graoly)
* Victor Li (VictorLJZ)
* Tanush Arora (tanusharora)
* Zefeng Wu (ZefW)

## Purpose
Joe's Bookstore is an online marketplace for books. It permits browsing and selling books of any kind. Born out
of an elementary desire for quality literature, Joe has designed a state-of-the-art software for discovering and
purchasing books.

## Table of Contents
* [Features](#features)
* [Installation Instructions](#installation-instructions)
* [Usage Guide](#usage-guide)
* [License](#license)
* [Give Us Feedback](#give-us-feedback)
* [Want To Contribute?](#want-to-contribute)

## Features

Joe's Bookstore is designed to make the process of browsing and selling books as simple and efficient as possible. Below are the core features of the software:

**1. User Authentication**

- Secure sign-up and login functionality.<br>
- User data is securely stored for easy access to wishlists, and more.

![User Authentication](images/User-Authentication.png)

**2. Browse Listings**

- View detailed information on all available listings, with the ability to sort by title, author, price, rating, and wishlist status.

![Home Page](images/Home-Page.png)

**3. Refine Search**

- Restrict the displayed listings by setting a maximum price or minimum rating.<br>
- Search listings using specific queries such as book title, author, and more.

![Filter By Price](images/Filter-By-Price.png)

**4. Discover and Sell Books**

- User can build a wishlist of books they wish to buy.<br>
- Users can list their own books for sale.

![Retail Price](images/Retail-Price.png)

![Wishlist](images/Wishlist.png)

## Installation Instructions

Prerequisites:
Before installing Joe's Bookstore, ensure you have/can access the following:

**1. Install IntelliJ (Preferred IDE)**

   Purpose: IntelliJ is an integrated development environment (IDE) tailored for Java development and is highly recommended for this project.
   While it’s not strictly required, it offers advanced features that will enhance your productivity. 
   [Software installation guide](https://www.jetbrains.com/help/idea/installation-guide.html)
   
   **Notes:**
   
   **Ultimate vs Community:** 
   The Ultimate version includes features like generating UML diagrams and more advanced integrations.
   While the Community Edition is sufficient to get started, the Ultimate version is preferred for full functionality.
   
   **Plugins:** Install plugins for enhanced functionality (e.g., UML diagram generation, code analysis).
   Instructions for [installing plugins](https://www.jetbrains.com/help/idea/managing-plugins.html#install_plugin_from_repo)
   
   **Recommended Plugins:**
   
   - Code Style & Best Practices: CheckStyle-IDEA, SonarLint
   - Visualization & UML: Java Visualizer, PlantUML Integration, Diagrams.net Integration
   
**3. Install Java**

   Install JDK 11+ (JDK 17+ recommended) via [IntelliJ]( https://www.jetbrains.com/help/idea/sdk.html#manage_sdks).
   Follow these instructions to download and set up JDK.

**4. GoogleBooksAPI (Data Access)**

   Generate an API key to make calls to the Google Books API, which Joe's Bookstore uses to retrieve detailed book data. Instructions can be found at [Google Books API Guide](https://developers.google.com/books/overview). Once you have your API key, store it in a file called api_key.env in your local repository, in the following format:

```bash
API_KEY="*your API key here*"   
```

**5. Firebase Account & Firebase SDK (For contributors):**

   Set up a [Firebase](https://firebase.google.com/) account. Once you’ve created a Firebase project, you can generate an API key and the Firebase configuration object. In src/main/resources/, include a file serviceAccount.json in the following format:

```json
{
  "type": "service_account",
  "project_id": "csc207project-ed2f9",
  "private_key_id": "*your private key ID here*",
  "private_key": "-----BEGIN PRIVATE KEY-----\n*your private key here*\n-----END PRIVATE KEY-----\n",
  "client_email": "firebase-adminsdk-d6nlw@csc207project-ed2f9.iam.gserviceaccount.com",
  "client_id": "105179899008478085244",
  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
  "token_uri": "https://oauth2.googleapis.com/token",
  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-d6nlw%40csc207project-ed2f9.iam.gserviceaccount.com",
  "universe_domain": "googleapis.com"
}
```

**6. Git:**

   Git is required for cloning the repository. [Install Git](https://git-scm.com/downloads.)

**Special Notes:**

IntelliJ is highly recommended for managing dependencies, building the project, and working with version control. 
While it's not required, using it will make tasks like debugging and testing more efficient.

**For MacOS:** Ensure to download the .dmg version and follow the Mac-specific installation steps.

**For Windows:** Use the .exe installer and follow Windows-specific instructions for installation.
Currently, our project does not support Android implementation, but it’s a feature planned for future updates.

## Usage Guide

### Usage Guide for Joe's Bookstore

Once you've successfully installed Joe's Bookstore and set up your environment, follow the steps below to start using the software and begin browsing, buying, and selling books.

---

#### **1. Setting Up the Project Locally**

**Clone the repository to your local machine:**

```bash
git clone https://github.com/williamrygao/CSC207-Project.git
```
Navigate to the project folder:

```bash
cd CSC207-Project
```

**Open the project in IntelliJ (or your preferred IDE):**

In IntelliJ, click the Run button or use the terminal to run the main class (usually Main.java or equivalent entry point). 
You can also use Maven to build the project:

```bash
mvn clean install
mvn exec:java
```

Verify that the project runs without errors.

**2. Interacting with the Application**

Main Features:

- **User Authentication:** Sign up and log in securely.

- **Search for Books:** Enter search terms (e.g., book title, author) to find relevant books.

- **Sorting:** You can sort all listings by title, author, price, or rating.

- **Browse Categories:** Explore books sorted by genre or price.

- **Sell Books:** List books for sale.

**3. Google Books API Integration**

The application integrates with the Google Books API to fetch book details based on your search queries.
Search through the API by using the [endpoint.](https://www.googleapis.com/books/v1/volumes?q=search%20terms) 
The API will return relevant book information like title, authors, descriptions, and availability.

**4. Key Integrations (For Contributors)**

**Google Books API**

The app fetches book data from the Google Books API. 
This integration enhances search functionality, allowing users to explore detailed information about books, including:

- Title<br>
- Author<br>
- Category/Genre<br>
- Description<br>
- Availability<br>
- Ratings

**Firebase Integration**

Firebase handles all database operations:

- **User Authentication:** Stores user credentials securely.
- **Book Listings:** Adds, updates, and removes book data.
- **User Data:** Manages purchase history, wishlists, and more.
- **Adding Data:** Contributors can access Firebase to add new books, update book details, or remove listings.
Firebase data operations can be done through Firebase Admin SDK, with relevant permissions granted via API keys.

**5. Running Tests**

Unit tests and integration tests are provided for core functionalities.
To run tests, use the built-in testing tools in IntelliJ (JUnit) or execute the tests via Maven:

```bash
mvn test
```

## License

This project is dedicated to the public domain under the [CC0 1.0 Universal](https://creativecommons.org/publicdomain/zero/1.0/) license. 

You are free to use, modify, and distribute the code for any purpose, including commercial use, without any restrictions. For full details, please refer to the `LICENSE` file in this repository.

## Give Us Feedback
We value your input and are always looking for ways to improve our software. If you'd like to share your thoughts,
suggestions, or report any issues, here's how you can provide feedback:
1. Share your feedback using our dedicated [feedback form](https://docs.google.com/forms/d/e/1FAIpQLSdMBz0mUjYl57bAOSNlAt0em3yHeEJynOQUuhzR0vUzXEaiwA/viewform?usp=sf_link).
In doing so, you may choose whether you would like be contacted about your feedback and your general user experience. 
2. Alternatively, you can contact us directly by [email](mailto:williamry.gao@mail.utoronto.ca). We'll be in touch ASAP!

## Want To Contribute?
We welcome contributions to improve the software! If you're interested in contributing, please follow the steps below to ensure a smooth process.
1. **Fork the Repository**:  
   To start contributing, first fork the project’s repository to your own GitHub account. This allows you to freely experiment and make changes without affecting the main codebase.

    - Go to the project repository on GitHub: [Project Repository](https://github.com/williamrygao/CSC207-Project)
    - Click the **Fork** button on the top right of the page.

2. **Clone the Fork**:  
   Once you've forked the project, clone your fork to your local machine to start working on the changes.

   ```bash
   git clone https://github.com/yourusername/project.git
   cd project

3. **Create a New Branch**:  
   Always create a new branch for your changes. This keeps your work organized and makes it easier for us to review.

   ```bash
   git checkout -b feature-branch-name
4. **Make Your Changes**:  
   Now, make your changes to the codebase. Whether it's fixing a bug, adding a new feature, or improving documentation, make sure your changes align with the project's goals.

    - If you're fixing a bug, try to include a clear description of the bug and how your fix resolves it.
    - If you're adding a feature, ensure that it adds value to the project and aligns with the overall direction of the project.
    - If you're updating documentation, make sure it's clear, concise, and accurate.
5. **Commit Your Changes**:  
   Once your changes are made, commit them with a clear and concise message explaining what was done.

   ```bash
   git add .
   git commit -m "Description of the changes made"
6. **Push Your Changes**:
   Push your branch to your fork on GitHub.
   ```bash
   git push origin feature-branch-name
7. **Create a Pull Request**:
   Once your changes are pushed, create a pull request from your fork to the main repository. You can do this by visiting your fork on GitHub and clicking the New Pull Request button. 
   Make sure your pull request is based on the latest version of the main branch (or whichever branch you're submitting to).
   Provide a clear description of what your pull request does and why it's important.
### Guidelines for Creating a Good Merge Request

To help ensure your contribution can be merged smoothly, please follow these guidelines:

- **Clarity**: Provide a detailed description of what your pull request does. If it fixes a bug, include the steps to reproduce the bug and how your changes fix it.
- **Consistency**: Follow the code style and structure used throughout the project. If you are unsure, refer to the existing code for guidance.
- **Testing**: Ensure that your changes don’t break any existing functionality. Run tests (if applicable) and add new tests for any new features or bug fixes.
- **Small Changes**: Where possible, submit small, focused changes. This makes it easier for the team to review and merge your contribution.

### Reviewing Contributions

Our project maintainers will review your pull request to ensure it aligns with the project goals, coding standards, and overall quality.

- **Code Review**: One or more maintainers will review your pull request. If necessary, they may suggest changes or improvements. Please be ready to make adjustments based on their feedback.
- **Tests**: We will run automated tests (if applicable) to verify that your changes do not break existing functionality.
- **Approval and Merge**: If your pull request passes review and tests, it will be approved and merged into the project. We may merge it directly or through a “squash and merge” method to keep the commit history clean.

### Contribution Protocols

- **Communication**: Always discuss larger changes with the team before starting. Use issues or the discussion board to propose new features or changes.
- **Respect for Others**: Be respectful and constructive in all communications. We want to foster a positive and collaborative environment for everyone.
- **Licensing**: Ensure that your contributions are your own work and that you have the right to submit them. By submitting a pull request, you agree to license your contributions under the project’s licensing terms.
