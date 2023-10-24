function searchByKeyword(keyword) {
    const videoContainer = document.querySelector(".youtube-container");
    const searchInput = document.getElementById("search-input");

    // Get the user's search keyword
    const searchKeyword = keyword || searchInput.value;

    // Clear the existing video thumbnails
    videoContainer.innerHTML = "";

    // Filter videos that match the search keyword
    for (const video of videos) {
        if (video.snippet.title.toLowerCase().includes(searchKeyword.toLowerCase())) {
            const videoThumbnail = document.createElement("div");
            videoThumbnail.classList.add("video-thumbnail");

            videoThumbnail.innerHTML = `
                <h3 class="video-title">${video.snippet.title}</h3>
                <img src="${video.snippet.thumbnails.high.url}" alt="${video.snippet.title}">
                <span class="play-icon"><i class="fa fa-play"></i></span>
            `;

            videoThumbnail.addEventListener("click", () => {
                const videoId = video.id.videoId;
                const videoUrl = `https://www.youtube.com/embed/${videoId}`;
                showVideoPopup(videoUrl);
            });

            videoContainer.appendChild(videoThumbnail);
        }
    }
}


document.addEventListener("DOMContentLoaded", function () {
    // Listen for the search button click and trigger the search
    document.getElementById("search-button").addEventListener("click", () => {
        const searchInput = document.getElementById("search-input");
        searchByKeyword(searchInput.value);
    });

// You can also listen for Enter key press in the input field to trigger the search
document.getElementById("search-input").addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
        searchByKeyword();
    }
});
});

// Initial fetch and display of videos
let videos = [];

fetch("https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=12&q=Software%20Quality%20Assurance&type=video&videoDuration=short&key=AIzaSyA7ritPw8POAqCOEz_r10XSFzfLfT0mzvs")
    .then((response) => {
        return response.json();
    })
    .then((data) => {
        console.log(data);
        videos = data.items;
        const videoContainer = document.querySelector(".youtube-container");

        for (const video of videos) {
            const videoThumbnail = document.createElement("div");
            videoThumbnail.classList.add("video-thumbnail");

            videoThumbnail.innerHTML = `
                <div class="video-info col-sm-11">
                    <h6 class="video-title">${video.snippet.title}</h6>
                    <img src="${video.snippet.thumbnails.high.url}" alt="${video.snippet.title}">
                    <span class="play-icon"><i class="fa fa-play"></i></span>
                </div>
                `;

            videoThumbnail.addEventListener("click", () => {
                const videoId = video.id.videoId;
                const videoUrl = `https://www.youtube.com/embed/${videoId}`;
                showVideoPopup(videoUrl);
            });

            videoContainer.appendChild(videoThumbnail);
        }
    })
    .catch((error) => {
        console.error("Error fetching YouTube data:", error);
    });

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("close-popup").addEventListener("click", () => {
        hideVideoPopup();
    });
});

// Function to display the video pop-up with the specified video URL
function showVideoPopup(videoUrl) {
    const videoPopup = document.getElementById("video-popup");
    const videoIframe = document.getElementById("video-iframe");

    videoIframe.src = videoUrl + "?autoplay=1&mute=1";
    videoPopup.style.display = "block";
}

// Function to hide the video pop-up
function hideVideoPopup() {
    const videoPopup = document.getElementById("video-popup");
    const videoIframe = document.getElementById("video-iframe");

    videoIframe.src = "";
    videoPopup.style.display = "none";
}
