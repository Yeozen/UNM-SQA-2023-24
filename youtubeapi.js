// Set your YouTube Data API key
var apiKey = "AIzaSyASw0iFX4bRcwz10Lmm12CdTgxF-NLzve4";

// Function to load the YouTube Data API and set the API key
function init() {
    gapi.client.setApiKey(apiKey);
    gapi.client.load("youtube", "v3", function() {
      console.log("YouTube Data API loaded");
    });
  }
  
  // Wait for the Google API client library to load and then call init
  gapi.load("client", init);

// Function to search YouTube
function searchYouTube() {
  var searchQuery = document.getElementById("searchQuery").value;
  gapi.client.youtube.search.list({
    "part": "snippet",
    "q": searchQuery,
    "maxResults": 12
  }).then(function(response) {
    var results = response.result.items;
    displayResults(results);
  }, function(err) {
    console.error("Error searching YouTube", err);
  });
}

// Create a single video player element
var playerContainer = document.getElementById("videoResults");

    function displayResults(results) {
      var searchResultsDiv = document.getElementById("videoResults");

      results.forEach(function(result) {
        var title = result.snippet.title;
        var videoId = result.id.videoId;
        var videoThumbnail = result.snippet.thumbnails.medium.url;

        var videoElement = document.createElement("div");
        videoElement.className = "video-box";

        videoElement.addEventListener("click", function() {
          openVideoPlayer(videoId);
        });

        videoElement.innerHTML = `
          <img src="${videoThumbnail}" alt="${title}">
          <p>${title}</p>
        `;
        searchResultsDiv.appendChild(videoElement);
      });
    }

    function openVideoPlayer(videoId) {
      var modal = document.getElementById("videoModal");
      var iframe = document.getElementById("videoPlayer");

      iframe.src = "https://www.youtube.com/embed/" + videoId;
      modal.style.display = "block";
    }

    function closeVideoPlayer() {
      var modal = document.getElementById("videoModal");
      var iframe = document.getElementById("videoPlayer");

      iframe.src = "";
      modal.style.display = "none";
    }
  
  
  
  
