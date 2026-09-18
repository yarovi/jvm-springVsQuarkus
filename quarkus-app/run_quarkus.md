# 🚀 Configuración del Botón "Play" para Quarkus Dev en IntelliJ IDEA

Sigue estos pasos para configurar un acceso directo visual en la barra superior de tu IDE y ejecutar Quarkus en modo desarrollo (*Live Coding*) con un solo clic, sin abrir la terminal.

## 📋 Requisitos Previos
* Tener el proyecto de Quarkus abierto en IntelliJ IDEA.
* Contar con el archivo `pom.xml` (Maven) en la raíz del proyecto.

---

## 🛠️ Paso a Paso para Configurar el Botón

1. **Abrir la ventana de configuraciones:**
    * Dirígete a la barra superior derecha de IntelliJ IDEA.
    * Haz clic en el menú desplegable que se encuentra justo al lado del botón del triángulo verde (**Play**).
    * Selecciona la opción **`Edit Configurations...`** (Editar configuraciones).

2. **Crear una nueva configuración de Maven:**
    * En la esquina superior izquierda de la ventana flotante que se abrió, haz clic en el icono del signo más **`+`** (*Add New Configuration*).
    * Busca en la lista de tecnologías y selecciona **`Maven`**.

3. **Completar los campos obligatorios:**
    * **`Name`:** Escribe un nombre descriptivo para identificarlo fácilmente (por ejemplo: `Quarkus Dev` o `🚀 Quarkus`).
    * **`Run`** *(o Command line en versiones anteriores)*: Copia y pega exactamente el siguiente comando:
      ```bash
      quarkus:dev
      ```

4. **Guardar los cambios:**
    * Haz clic en el botón **`Apply`** (Aplicar) en la esquina inferior derecha.
    * Presiona **`OK`** para cerrar la ventana.

---

## ⚡ Cómo Usarlo a partir de Ahora

1. Asegúrate de que en el menú desplegable superior esté seleccionado tu perfil creado (**`Quarkus Dev`**).
2. Haz clic en el triángulo verde (**`Run / Play`**).
3. ¡Listo! Quarkus iniciará en la terminal integrada de IntelliJ y podrás disfrutar del *Hot Reload* modificando tu código sin reiniciar.
