# Flixster
Flixter is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

## Flix (Core functionality) 

### User Stories

- User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.
- Views should be responsive for both landscape/portrait mode.
- In portrait mode, the poster image, title, and movie overview is shown.
- In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
- Improved the user interface by experimenting with styling and coloring.

  
## Flix (Additional features)

### User Stories


- Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- Allow video posts to be played in full-screen using the YouTubePlayerView.
- Implement a shared element transition when user clicks into the details of a movie.
- Trailers for popular movies are played automatically when the movie is selected.
- When clicking on a popular movie (i.e. a movie voted for more than 7.5 stars) the video should be played immediately.
- Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- Add a rounded corners for the images using the Glide transformations. 

### App Walkthough GIF

<img src="Walkthrough2.gif" width=250><br>
<img src="Walktrhrough2_land.gif" width=560><br>


## Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

      
### App Walkthough GIF

<img src="walkthrough1.gif" width=250><br>
<img src="walkthrough2.gif" width=550><br>


### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids
