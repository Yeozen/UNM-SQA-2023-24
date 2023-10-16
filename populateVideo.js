var myButton = document.getElementById("formButton");

myButton.addEventListener("click", function () {
    // remove all the iframes from myVideos div
    $("#myVideos").empty();

    var relativeUrl = "https://www.googleapis.com/youtube/v3/search";
    var apiKey = "AIzaSyA4IeUgSBVXzff3yrei3rALRPHhHkWg0c0";

    var type = "video";
    var maxResults = 12;
    var q = document.getElementById("searchBox").value;

    var url = relativeUrl + "?type=" + type + "&key=" + apiKey + "&maxResults=" + maxResults + "&q=" + q;

    // log response from the url after sending get request
    $.get(url, function (response) {
        var items = response.items;
        var urls = "https://www.youtube.com/embed/VIDEO_ID?playlist=";
        var videoList = "";

        for (let i = 0; i < items.length; i++) {
            var videoID = items[i].id.videoId;
            videoList += videoID + ",";

        }

        // create a new iframe element
        var iframe = document.createElement("iframe");
        //iframe.setAttribute("src", "https://www.youtube.com/embed/" + videoID);
        iframe.setAttribute("src", urls + videoList);
        iframe.setAttribute("width", "560");
        iframe.setAttribute("height", "315");
        iframe.setAttribute("allowfullscreen", "true");
        iframe.setAttribute("frameborder", "0");

        // add margin to the iframe
        iframe.style.margin = "10px";

        // append the iframe in myVideo div
        document.getElementById("myVideos").appendChild(iframe);
    });
});