// Set your YouTube Data API key
var apiKey = "AIzaSyASw0iFX4bRcwz10Lmm12CdTgxF-NLzve4";

var apiKey = "AIzaSyASw0iFX4bRcwz10Lmm12CdTgxF-NLzve4"; // Replace with your YouTube Data API key
var maxResults = 12;
var videoDuration = "short";
var searchQuery = "car"; // Replace with your search query

fetch(`https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=${maxResults}&q=${searchQuery}&type=video&videoDuration=${videoDuration}&key=${apiKey}`)
  .then(response => response.json())
  .then(data => {
    var videoListDiv = document.getElementById("videoList");
    var videoPlayer = document.getElementById("videoPlayer");

    data.items.forEach(function(item) {
      var title = item.snippet.title;
      var videoId = item.id.videoId;
      var videoThumbnail = item.snippet.thumbnails.medium.url;

      var videoElement = document.createElement("div");
      videoElement.className = "video-box";
      videoElement.addEventListener("click", function() {
        videoPlayer.src = "https://www.youtube.com/embed/" + videoId;
      });

      var thumbnailImage = document.createElement("img");
      thumbnailImage.src = videoThumbnail;
      thumbnailImage.alt = title;

      var titleParagraph = document.createElement("p");
      titleParagraph.textContent = title;

      videoElement.appendChild(thumbnailImage);
      videoElement.appendChild(titleParagraph);
      videoListDiv.appendChild(videoElement);
    });
  })
  .catch(error => {
    console.error("Error fetching data:", error);
  });

  
  
  
  
