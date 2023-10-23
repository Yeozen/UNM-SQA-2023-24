
function searchVideos() {
  const query = document.getElementById('search').value;
  const apiKey = 'AIzaSyB-7BsNPrUbghbntOtbwrbLOQXrf69GRKM'; // Replace with your actual API key
  const maxResults = 10;
  const videoDuration = 'short';

  fetch(`https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=${maxResults}&q=${query}&type=video&videoDuration=${videoDuration}&key=${apiKey}`)
      .then(response => response.json())
      .then(data => {
          const searchResults = document.getElementById('searchResults');
          searchResults.innerHTML = '';

          data.items.forEach(item => {
              const videoTitle = item.snippet.title;
              const videoId = item.id.videoId;
              const videoThumbnail = item.snippet.thumbnails.default.url;

              const videoElement = document.createElement('div');
              videoElement.innerHTML = `
                  <h2>${videoTitle}</h2>
                  <iframe width="1000" height="315" src="https://www.youtube.com/embed/${videoId}" frameborder="0" allowfullscreen></iframe>
              `;
              videoElement.style.marginBottom = '75px';

              searchResults.appendChild(videoElement);
          });
      })
      .catch(error => {
          console.error('Error:', error);
      });
}


