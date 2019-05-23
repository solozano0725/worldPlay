
# Android Test for Rappi

# Descripcion General

Es una aplicacion realizada nativamente en lenguaje Android que consta de 4 menus llamada worldPlay

| Menu | Descripción |
| ------------- | :---: |
| `Buscador Online` | Busca cualquier tipo de peliculas, segun su nombre |
| `Peliculas populares` | Muestra el listado de las peliculas populares Informacion brindada por el API. |
| `Top de peliculas` | Muestar el listado de peliculas mas rankeadas. Informacion brindada por el API. |
| `Proximas peliculas` | Muestra el listado de las proximas peliculas. Informacion brindada por el API. |

Se uso un model MVP para la realizacion del proyecto. Las consultas y las capacidades existentes en el servicio, tienen un cache de 10 megas, en el momento en que se requiera buscar informacion offline. 

# Capas de la aplicación
Estas son las capas contempladas en el desarrollo:

| Capa | Paquete en el Proyecto | Descripción |
| ------------- | :---: | :---: |
| Modelo | `model, common.Constanst` | Desarrollo de los clases encargas de instanciar los objetos utilizados en el API TMDb |
| Vista | `view, common.SplashScreenAnimated` | Se encarga de las interfaces mostradas al usuario. Contiene las actividades y adaptadores necesarios para los RecyclerView y anexo de contenido multimedia o animacion (Splashscreen y Video) |
| Conexion | `network` | Desarrollo de clases encargadas de consumir y mapear la consulta de peticiones del API TMDb |
| Presentador | `presenter` | Contiene el desarrollo del modelo MVP implementado en donde se encuentra los contratos, presentadores e interadores entre la vista general de la app (Lista de peliculas) y el detalle de cada una de las peliculas. |

# Responsabilidad de cada clase creada.
Estas son las responsabilidades de cada clase contempladas en el desarrollo:

| Clase | Descripción |
| ------------- | :---: |
| app.worldplay.rappi.common.Constants | Clase con algunos valores constantes que pueden ser cambiantes durante el desarrollo o vida del proyecto. |
| app.worldplay.rappi.common.SplashScreenAnimated | Clase que se encarga de ejecutar la animacion mostrada al inicio de la app. |
| app.worldplay.rappi.model | Modelos de clases encargada de permitir la instaciacion de objetos de la API TMDb. |
| app.worldplay.rappi.network.retrofit | Conjunto de clases que contiene los metodos a utilizar de la API TMDb junto con la aplicacion de cada uno de estos. |
| app.worldplay.rappi.network.Repository | Clase encargada de la conexion hacia el repositorio, ayudando tambien a conocer si existe o no conexion a internet desde el dispositivo. |
| app.worldplay.rappi.presenter.details.ContractMovie | Contrato generado para lograr brindar funcionamiento al detalle sobre cada pelicula obteniendo nombre, genero, lenguajes, imagenes, etc. |
| app.worldplay.rappi.presenter.details.MovieInteractor | Clase encargada de llamar los metodos declarados en la clase "RestClient" y usarlos segun el ajuste del contrato "ContractMovie" |
| app.worldplay.rappi.presenter.details.MoviePresenter | Clase encargada de juntar y mostrar los datos obtenidos entre la conexion de las clases "ContractMovie" y "MovieInteractor". Usado en el DetailsView.java. |
| app.worldplay.rappi.presenter.home.ContractHome | Contrato generado para lograr brindar funcionamiento al listado de peliculas a mostrar en caso de que se busquen desde el buscador online o las 3 categorias restantes logrando mostrar el titulo y el poster de cada pelicula. |
| app.worldplay.rappi.presenter.home.HomeInteractor | Clase encargada de llamar los metodos declarados en la clase "RestClient" y usarlos segun el ajuste del contrato "ContractHome" |
| app.worldplay.rappi.presenter.home.HomePresenter | Clase encargada de juntar y mostrar los datos obtenidos entre la conexion de las clases "ContractHome" y "HomeInteractor". Usado en el MainActivity.java. |
| app.worldplay.rappi.view.activity.DetailsView | Clase encargada de comunicarse con la interfaz creada para mostrar el detalle de cada pelicula. Utilizada el contracto "ContractMovie" |
| app.worldplay.rappi.view.activity.MainActivity | Clase encargada de comunicarse con la interfaz creada para mostrar el listado de peliculas segun la opcion escogida en el inicio. Utilizada el contracto "ContractHome" |
| app.worldplay.rappi.view.activity.Video | Clase encargada de mostrar el trailer de la pelicula escogida deacuerdo a la disponibilidad de esta en el API. |
| app.worldplay.rappi.view.adapter.MoviesAdapter | Clase encargada de adaptar la informacion obtenida para el uso del RecyclerView mostrado en el Main de la app. |
| app.worldplay.rappi.view.adapter.RecyclerView | Desarrollo del RecyclerView mostrado en el Main con su interfaz. |

# Tecnologias implementadas 
Estas son las tecnologias impletadas para el anexo de conexiones con el API TMDb, interfaz y animaciones:

| Tecnologia | URL |
| ------------- | :---: |
| Retrofit2 | `https://github.com/square/retrofit` |
| Okhttp3 | `https://github.com/square/okhttp` |
| ButherKnife | `https://github.com/JakeWharton/butterknife` |
| rxjava | `https://github.com/ReactiveX/RxJava` |
| Picasso | `https://github.com/square/picasso` |
| Toasty | `https://github.com/GrenderG/Toasty` |
| Snacky | `https://github.com/matecode/Snacky` |
| ExpandableCardView | `https://github.com/AleSpero/ExpandableCardView` |

## ¿En qué consiste el principio de responsabilidad única? ¿Cuál es su propósito?

El principio de resposabilidad unica es el primero de los 5 principios SOLID que consiste en mantener una alta cohesion entre funcionalidades que esten relacionadas entre si, buscando mantener fuera los elementos o procesos que no tengan que ver directamente con la finalidad del proceso, asi se busca aumentar la cohesion entre las cosas que cambian reduciendo los errores y mantiendo un flujo constantes.

Lo anterior genera el proceso contrario al acoplamiento el cual disminuye, ya que se logra reducir el grado de la relacion de una clase o modulo de los demas que no tengan nada que ver con su funcionalidad, haciendo del codigo algo mas testeable, sostenible y extendible.

## ¿Qué características tiene, según su opinión, un “buen” código o código limpio?

Las caracteristicas que posee un codigo limpio se basa en tener un conjunto de instrucciones que logren ser legibles y mantenibles en el tiempo, permitiendo generar eficiencia en su aplicacion y lectura. Ademas, tambiene tiene que tener la posibilidad de ser extendible y de facil cambio deacuerdo a los ajustes que se necesiten, en el momento en el que se necesite. 
Para lograr todo lo anterior, la persona que lo desarrolla debe entender muy bien el problema que busca solventar con el codigo para ahorrar pasos de escritura, arquitectura y demas factores que influyan en el proceso. 
