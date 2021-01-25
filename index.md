# ML Product Search Sample App

## Description
Sample App to search products using Mercado Libre Api.

Application that uses Mercadolibre's APIs, with following sections:
* Splash: app entry point
* Sites:  available Mercado Libre sites to search products
* Search: product search logic and answer list.
* Product page: detail of a product (which should be accessible by tapping one
of the items in the search result).

### Mercado Libre Api

* Sites: this end point was used to get sites in the sample app
```markdown
   https://api.mercadolibre.com/sites
```

* Product Search: this end point was used to get search results in the sample app
```markdown
   https://api.mercadolibre.com/sites/{site}/search?q={query}
```

* Product Details: these end points were used to get product details in the sample app
```markdown
   // get item info
   https://api.mercadolibre.com/items/{itemId}
   
   // get user info
   https://api.mercadolibre.com/users/{sellerId}
```

Documentation related to the [Mercado Libre api](https://developers.mercadolibre.com.ar/es_ar/items-y-busquedas#Obtener-%C3%ADtems-de-una-consulta-de-b%C3%BAsqueda)


## Architecture

This sample app was based on [Android Architecture Components](https://developer.android.com/jetpack/guide) like reference.

Consider the following component diagram, which shows the interactions between modules after designing the app:

![Image](https://raw.githubusercontent.com/fh127/ml-product-search-sample-app/gh-pages/Screen%20Shot%202021-01-25%20at%2010.50.30%20AM.png)

### Structure packages:

* **di package:** this package contains all classes and configurations related to app dependency injection using Dagger library.

* **repository package:** this package contains all classes related to the source data and use the repository interface like entry point to connect to UI layer     using Flow values and Coroutines. we have the following subpackages:
   
   - **api package:** contains the logic related to the Api client using Retrofit library.
   - **api model:** contains the data classes to get the information provided for ML APi.
   
* **UI package:** this package contains all classes related to the UI layer. we have the following subpackages:

   - **viewmodel package:** contains the Viewmodel classes, which are responsible for connecting the data source logic with the presentation logic. These are entry      points to connect to repository interface and return the results using LiveData values for the user interface. 
   - **view package:** contains the classes and components that are responsible of UI layer in the app. It was used a Delegate Adapter pattern to render the results.
 
 **tes package:** contains unit test related with the implementation, the view models are the mainly tested.


## Libraries
* **[Retrofit](https://github.com/square/retrofit):**  Http Client library and related dependecies to handle data from Api service.
* **[Glide](https://github.com/bumptech/glide):**  Image Handling library
* **[Dagger](https://github.com/google/dagger):**  dependency injector for Android
* **[Coroutines](https://github.com/Kotlin/kotlinx.coroutines):** multithreading and concurrency
* **[lottie](https://github.com/airbnb/lottie-android):** animations library
* **[Android Architecture components](https://developer.android.com/jetpack/guide):** official Android development framework
* **Test libraries:** 


