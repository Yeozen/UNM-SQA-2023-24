
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

          for (let i = 0; i < data.items.length; i += 2){
            const item1 = data.items[i];
            const item2 = data.items[i+1];
            
            const videoTitle1 = item1.snippet.title;
            const videoId1 = item1.id.videoId;
            const videoThumbnail = item1.snippet.thumbnails.default.url;

            const videoTitle2 = item2.snippet.title;
            const videoId2 = item2.id.videoId;
            const videoThumbnai2 = item2.snippet.thumbnails.default.url;

            const videoContainer = document.createElement('div');
            videoContainer.style.marginBottom = '75px';
            videoContainer.style.display = 'flex';

            const videoElement1 = document.createElement('div');
            videoElement1.style.paddingLeft = '5%';
            videoElement1.style.flex = '50%';

            const videoElement2 = document.createElement('div');
            videoElement1.style.paddingLeft = '5%';
            videoElement2.style.flex = '50%';

            videoElement1.innerHTML = `
                <h3>${videoTitle1}</h3>
                <iframe width="80%" height="315" src="https://www.youtube.com/embed/${videoId1}" frameborder="0" allowfullscreen></iframe>
            `;
                
            videoElement2.innerHTML = `
                <h3>${videoTitle2}</h3>
                <iframe width="80%" height="315" src="https://www.youtube.com/embed/${videoId2}" frameborder="0" allowfullscreen></iframe>
            `;

            videoContainer.appendChild(videoElement1);
            videoContainer.appendChild(videoElement2);
            searchResults.appendChild(videoContainer);
          };
      })
      .catch(error => {
          console.error('Error:', error);
      });
}


