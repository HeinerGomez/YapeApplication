# Yape Challenge 馃捇

Aplicaci贸n que hace parte del desaf铆o Yape para su proceso de selecci贸n

***

### Video demostraci贸n 馃帴



https://user-images.githubusercontent.com/27563731/219180724-2be15086-6bf8-4525-96e2-b300aa2369e3.mp4



### Justificaci贸n de la arquitectura 馃З

Para el desarrollo de la aplicaci贸n se quizo mostrar el conocimiento sobre una arquitectura limpia, por lo cual esta aplicaci贸n fue creada con los conceptos de **Clean Architeture**, combinandolo con lo que para m铆 es el futuro de la construcci贸n de vistas en android, la cual es **Jetpack Compose**, de esta forma la construcci贸n de vistas en mucho m谩s facil y se puede reutilizar ciertos componentes de manera natural.

Para la comunicaci贸n entre la vista y los datos se uso **MVVM** combinandolo con **Flows y Corrutinas** para hacer una comunicaci贸n basada en estados.

Tambi茅n se modularizo la applicaci贸n con un enfoque basado en `Features` de esta manera cada pantalla es tratada como un feature diferente, por lo cual cada m贸dulo tiene lo necesario para subsistir por si solo, aunque con 2 m贸dulos globales que sirven para generalizar funcionales entre los m贸dulos features.

### Explicaci贸n de la arquitectura

La aplicaci贸n se compone de los siguientes m贸dulos: `app`, `core`, `ui_components`, `recipe_list`, `detail_recipe` y `map_recipe`. Existen 3 tipos de m贸dulos diferentes dentro de la aplicaci贸n los cuales son: **modulos core**, **modulos libreria** y **modulos feature**, cada uno de estos m贸dulos tienen un proposito definido y una responsabilidad definida, las cuales se explican a continuaci贸n:

- **M贸dulos Core**: Estos m贸dulos se encargan de configurar la aplicaci贸n y agrupar funcionalidad que pueden ser gen茅ricas para los m贸dulo de tipo feature, dentro de esta categoria podemos encontrar los m贸dulos `app` y `core`
- **M贸dulos Librer铆a**: Estos m贸dulos tienen como caracteristica principal que no deben depender de otros m贸dulos, solo pueden depender de librerias externas.
- **M贸dulos Feature**: Estos m贸dulos aislan la l贸gica de cada feature y mantienen su comportamiento independiente de la aplicaci贸n, tiene todo para subsistir por ellos (dentro de la aplicaci贸n) pero pueden usar estilos, constantes y recursos que esten ubicados en m贸dulos de tipo core y de tipo librer铆a.

> A continuaci贸n se muestra una representaci贸n de la arquitectura actual mediante un gr谩fico, para entender un poco como se relacionan estos modulos entre s铆.

![Arquitectura_applicacion](https://user-images.githubusercontent.com/27563731/219185222-970b2b4c-27d3-46a9-b985-2765c3761fb5.jpg)

### T茅cnologias Usadas

***

- Jetpack Compose
- MVVM
- Clean Architecture
- Retrofit
- Coil
- Dagger Hilt
- Navigation Jetpack
- Modularizaci贸n
- Lottie
- Google Maps
- Corrutinas
- Flows

> Por 煤ltimo me gustar铆a compartirles mi tablero de 馃憠 [Trello](https://trello.com/invite/b/A4iUPZDj/ATTI66371a273b58c712a80e8302c617f725DD4AD3D1/yape-prueba) 馃憟 por si estan interesado en ver como dividi mi trabajo para abarcar esta prueba.

