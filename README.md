# GitHub Authentication and Repository Search App

This project is a code challenge that demonstrates the integration of GitHub authentication, refactoring of existing code to follow best practices, and implementation of a search functionality for GitHub repositories.

### Generate GitHub Client ID and Secret

1. Go to your [GitHub Developer settings](https://github.com/settings/developers).
2. Create a new OAuth application.
3. Copy the **Client ID** and **Client Secret** provided by GitHub.

### Update `build.gradle` of login module

1. Open the `build.gradle` file for your login module.
2. Add the following lines to define your client ID and secret:

   ```groovy
   buildConfigField "String", "GITHUB_CLIENT_ID", '"YOUR_CLIENT_ID"'
   buildConfigField "String", "GITHUB_CLIENT_SECRET", '"YOUR_CLIENT_SECRET"'
  
3. Sync the project with Gradle files. 