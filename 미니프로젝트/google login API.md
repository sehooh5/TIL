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



ID : 10268246797-gsmam25f472mnkps76fiv69fu2mfu5qe.apps.googleusercontent.com



sectret : zkJ7xbKKKhlLGkd61JbjAxpT





10268246797-0j3msn023mdrekk0kb0hpa22k9hv533i.apps.googleusercontent.com



iNN_W9283SOPguPI_ORnaV9k