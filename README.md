# 🌦️ Meteo ES -- Aplicación Meteorológica

Aplicación compuesta por una **API backend** y una **interfaz frontend**
para consultar datos meteorológicos obtenidos desde AEMET.

Este proyecto incluye dos servicios Docker:

- **meteo-api** → API REST
- **meteo-ui** → Aplicación web

---

# Preguntas 

Habrás observado que las llamadas al servicio para obtener el listado de Municipios son
bastante lentas. ¿Cómo haríamos para mejorar el tiempo de respuesta de este servicio?

- Deberiamos implementar un cache en memoria para evitar consultas repetidas, por ejemplo con Caffeine o EhCache, el tiempo con criterio debido a que los municipios no cambian con frecuencia.


<br>
Notarás además que el servicio para recuperar la predicción para un municipio devuelve una
URL a la que hacer la consulta. ¿Por qué motivo piensas que AEMET ha implementado este
servicio así?

 - Para reducir la carga en sus servidores. Ya que pueden cachear la respuesta. Y porque sirven ficheros estaticos,
no recalculan nada. Si por lo que sea la predicción cambia, se cambia la URL y ya. Adicional a esto tienen la posibilidad
de llevar una traza de quien accede realmente a los datos y llevar un control para que no dejen de funcionar los servicios.

<br>
Tras la amenaza de AEMET con cortarnos el servicio, debemos apresurarnos a buscar alguna
solución que afecte lo mínimo posible a nuestros usuarios. ¿Qué podríamos hacer? ¿Cómo
implementarías la solución?

- La solución es reducir al mínimo las llamadas directas a AEMET y crear una capa de cache en nuestro backend.

---

## 🚀 Ejecución en local con Docker

### 📌 1. Requisitos previos

Asegúrate de tener instalado:

- Docker
- Docker Compose

Puedes comprobarlo con:

``` bash
docker --version
docker compose version
```

---

### 🔑 2. Obtener una API Key de AEMET

La API requiere una clave de acceso para consumir datos desde la AEMET.

Puedes solicitar una API Key gratuita aquí:

👉 https://opendata.aemet.es/centrodedescargas/inicio

Cuando la obtengas, deberás incluirla en el archivo `.env` del servicio
`meteo-api`.

---

### 📁 3. Clonar el repositorio

``` bash
git clone https://github.com/anthonylldev/meteo-es-app.git
cd meteo-es-app
```

---

### 🔧 4. Crear archivo `.env` para la API

Debes crear un archivo **.env** en la **raíz del proyecto** con la
siguiente estructura:

``` env
AEMET_API_KEY=TU_API_KEY_DE_AEMET
ALLOWED_METHODS=GET
ALLOWED_ORIGINS=http://localhost,http://localhost:80
```

---

### ▶️ 5. Levantar los contenedores

Desde la raíz del proyecto:

``` bash
docker compose up --build
```

Servicios disponibles:

- **API:** http://localhost:8080
- **Frontend:** http://localhost

Los contenedores incluyen *healthchecks* para garantizar que la API esté
operativa antes de iniciar la UI.

---

### 🛑 6. Detener los contenedores

``` bash
docker compose down
```

Si además quieres eliminar las imágenes generadas:

``` bash
docker compose down --rmi all
```

---

### 🌐 7. Redes Docker

Docker Compose crea automáticamente una red interna llamada:

    meteo-network

Esta red permite la comunicación interna entre `meteo-api` y `meteo-ui`.

---

### 📝 Notas finales

- Asegúrate de que los puertos **80** y **8080** estén libres antes de
  iniciar.
- El backend y frontend reinician automáticamente gracias a `restart: unless-stopped`.
- Si cambias las rutas, deberías además editar el `nginx.conf` en `meteo-es-app/meteo-es-ui`.
