# Musike
An app to see information about songs and save the ones that you love the most!

<img src="https://github.com/raphaelbertholucci/Musike/blob/master/readme-pictures/splash.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/Musike/blob/master/readme-pictures/splash.webp" width="250" height="480" /> <img src="https://github.com/raphaelbertholucci/Musike/blob/master/readme-pictures/search.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/Musike/blob/master/readme-pictures/search.webp" width="250" height="480" />


## Getting Started
Before you try to run this project you should go to [Last.FM](https://www.last.fm/api) to create your account and generate you own api key.
  * Place you API_KEY in build.gradle.kts inside the data module.

## Multi Module Project Structure
The multi module project structure its being used to de-couple the main app of its features.
Also make it easier to separate feature parts of the application from the design-system.

## Used Technologies

The code is organized using a Multi Module Project Structure with MVVM and some technologies, like:
  * :syringe: <b>Koin</b> for dependency injection
  * :white_check_mark: <b>Coroutines</b> with <b>Flow</b> for async tasks
  * :file_folder: <b>Room</b> for local database
  * :writing_hand: <b>Junit</b> with <b>MockK</b> for unit tests
  * :saxophone: <b>Espresso</b> for instrumentation tests (SearchFragmentTest class)
  * :lotus_position: <b>Retrofit</b> for REST API calls
  * :boat: <b>Navigation</b> of Jetpack Components
  * :computer: <b>CI Integration </b> with <b>GitHub Actions</b>
  * :camera_flash: <b>Coil</b> for load image URLs
  * :partying_face: <b>Lottie</b> to show animations
  * :thought_balloon: <b>Shimmer</b> to handle loading
  * :tada: The new <b>Splash Screen</b> library to handle the splash on Android 12
 
 
 <img src="https://github.com/raphaelbertholucci/Musike/blob/master/readme-pictures/search_track.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/Musike/blob/master/readme-pictures/search_track.webp" width="250" height="480" />  <img src="https://github.com/raphaelbertholucci/Musike/blob/master/readme-pictures/details.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/Musike/blob/master/readme-pictures/details.webp" width="250" height="480" />
 
## License
This project is licensed under the Apache 2.0 License - see the LICENSE file for details
