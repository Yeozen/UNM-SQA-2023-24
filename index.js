
// const API_KEY = 'AIzaSyB-7BsNPrUbghbntOtbwrbLOQXrf69GRKM'


// function searchVideos() {
//   const query = document.getElementById('search').value;
//   const apiKey = 'AIzaSyB-7BsNPrUbghbntOtbwrbLOQXrf69GRKM'; // Replace with your actual API key
//   const maxResults = 10;

//   fetch(`https://www.googleapis.com/youtube/v3/search?key=${apiKey}&part=snippet&q=${query}&maxResults=${maxResults}`)
//       .then(response => response.json())
//       .then(data => {
//           const searchResults = document.getElementById('searchResults');
//           searchResults.innerHTML = '';

//           data.items.forEach(item => {
//               const videoTitle = item.snippet.title;
//               const videoId = item.id.videoId;
//               const videoThumbnail = item.snippet.thumbnails.default.url;

//               const videoElement = document.createElement('div');
//               videoElement.innerHTML = `
//                   <h2>${videoTitle}</h2>
//                   <iframe width="560" height="315" src="https://www.youtube.com/embed/${videoId}" frameborder="0" allowfullscreen></iframe>
//               `;

//               searchResults.appendChild(videoElement);
//           });
//       })
//       .catch(error => {
//           console.error('Error:', error);
//       });
// }

function authenticate() {
    return gapi.auth2.getAuthInstance()
        .signIn({scope: "https://www.googleapis.com/auth/youtube.force-ssl"})
        .then(function() { console.log("Sign-in successful"); },
              function(err) { console.error("Error signing in", err); });
  }
  function loadClient() {
    gapi.client.setApiKey("YOUR_API_KEY");
    return gapi.client.load("https://www.googleapis.com/discovery/v1/apis/youtube/v3/rest")
        .then(function() { console.log("GAPI client loaded for API"); },
              function(err) { console.error("Error loading GAPI client for API", err); });
  }
  // Make sure the client is loaded and sign-in is complete before calling this method.
  function execute() {
    return gapi.client.youtube.search.list({
      "part": [
        "snippet"
      ],
      "maxResults": 12,
      "q": "surfing"
    })
        .then(function(response) {
                // Handle the results here (response.result has the parsed body).
                console.log("Response", response);
              },
              function(err) { console.error("Execute error", err); });
  }
  gapi.load("client:auth2", function() {
    gapi.auth2.init({client_id: "YOUR_CLIENT_ID"});
  });