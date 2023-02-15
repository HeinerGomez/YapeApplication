# Yape Challenge 💻

Aplicación que hace parte del desafío Yape para su proceso de selección

***

### Video demostración 🎥



https://user-images.githubusercontent.com/27563731/219180724-2be15086-6bf8-4525-96e2-b300aa2369e3.mp4



### Justificación de la arquitectura 🧩

Para el desarrollo de la aplicación se quizo mostrar el conocimiento sobre una arquitectura limpia, por lo cual esta aplicación fue creada con los conceptos de **Clean Architeture**, combinandolo con lo que para mí es el futuro de la construcción de vistas en android, la cual es **Jetpack Compose**, de esta forma la construcción de vistas en mucho más facil y se puede reutilizar ciertos componentes de manera natural.

Para la comunicación entre la vista y los datos se uso **MVVM** combinandolo con **Flows y Corrutinas** para hacer una comunicación basada en estados.

También se modularizo la applicación con un enfoque basado en `Features` de esta manera cada pantalla es tratada como un feature diferente, por lo cual cada módulo tiene lo necesario para subsistir por si solo, aunque con 2 módulos globales que sirven para generalizar funcionales entre los módulos features.

### Explicación de la arquitectura

La aplicación se compone de los siguientes módulos: `app`, `core`, `ui_components`, `recipe_list`, `detail_recipe` y `map_recipe`. Existen 3 tipos de módulos diferentes dentro de la aplicación los cuales son: **modulos core**, **modulos libreria** y **modulos feature**, cada uno de estos módulos tienen un proposito definido y una responsabilidad definida, las cuales se explican a continuación:

- **Módulos Core**: Estos módulos se encargan de configurar la aplicación y agrupar funcionalidad que pueden ser genéricas para los módulo de tipo feature, dentro de esta categoria podemos encontrar los módulos `app` y `core`
- **Módulos Librería**: Estos módulos tienen como caracteristica principal que no deben depender de otros módulos, solo pueden depender de librerias externas.
- **Módulos Feature**: Estos módulos aislan la lógica de cada feature y mantienen su comportamiento independiente de la aplicación, tiene todo para subsistir por ellos (dentro de la aplicación) pero pueden usar estilos, constantes y recursos que esten ubicados en módulos de tipo core y de tipo librería.

> A continuación se muestra una representación de la arquitectura actual mediante un gráfico, para entender un poco como se relacionan estos modulos entre sí.

![Arquitectura_applicacion](https://user-images.githubusercontent.com/27563731/219185222-970b2b4c-27d3-46a9-b985-2765c3761fb5.jpg)

### Técnologias Usadas

***

- Jetpack Compose
- MVVM
- Clean Architecture
- Retrofit
- Coil
- Dagger Hilt
- Navigation Jetpack
- Modularización
- Lottie
- Google Maps
- Corrutinas
- Flows

> Por último me gustaría compartirles mi tablero de 👉 [Trello](https://trello.com/invite/b/A4iUPZDj/ATTI66371a273b58c712a80e8302c617f725DD4AD3D1/yape-prueba) 👈 por si estan interesado en ver como dividi mi trabajo para abarcar esta prueba.

