# GESAU - Gestor de Autorizaciones

### Usuarios

| username | password | role |
|:--------:|:--------:|:--------:|
|maria|123|**"ROLE_USER"**|
|maria_admin|12341234|**"ROLE_ADMIN"**|
|maria_consultor|123|**"ROLE_CONSULTOR"**|


### Base de datos
Para poner en marcha el sistema, es necesario restaurar la base de datos utilizando el volcado (dump) incluido en el proyecto. Siga estos pasos:

1. **Localización del archivo:** El dump se encuentra en la raíz del proyecto en la ruta: "Gesau/dump-lks_practicas-04-2026.sql".

2. **Preparación:** Acceda a su gestor de bases de datos (DBeaver recomendado) y cree una nueva base de datos vacía llamada lks_practicas.

3. **Restauración:** 
- Haga clic derecho sobre la base de datos recién creada.
- Seleccione la opción Herramientas (Tools) > Restaurar base de datos (Restore database).
- Seleccione el archivo .sql mencionado anteriormente e inicie el proceso.
