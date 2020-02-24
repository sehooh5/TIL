# google login API

```html
<html lang="en">
  <head>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="10268246797-gsmam25f472mnkps76fiv69fu2mfu5qe.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
  </head>
  <body>
    <div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
    <script>
      function onSignIn(googleUser) {
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        console.log('Full Name: ' + profile.getName());
        console.log('Given Name: ' + profile.getGivenName());
        console.log('Family Name: ' + profile.getFamilyName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());

        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
      }
    </script>
  </body>
</html>
```







595308026527-uck4ngk6pur3dhm0bunejuq1llf6buge.apps.googleusercontent.com



rS1wfopgq19DlSn9n_w9htJR







595308026527-pg8v8a3oqgtndi247l9ticfo916ksa88.apps.googleusercontent.com



wV0evBnOOnYYkKZXjn-f4lCC





logintest

195471796631-4kkp0lhn15j6bcnt3r83sg0k4k7u7hn8.apps.googleusercontent.com



IHJX0ryna7X_KnhA0oh49Xj6