// Archivo build.gradle de nivel superior donde se configuran opciones comunes para todos los subproyectos/módulos.

plugins {
    // Declaración de plugins, no se aplican directamente al proyecto raíz
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

// Eliminar el bloque buildscript con los repositorios para evitar conflictos
buildscript {
    dependencies {
        // Dependencias necesarias para construir el proyecto
        classpath("com.android.tools.build:gradle:8.0.2") // Asegúrate de que la versión sea compatible con tu proyecto.
        classpath("com.google.gms:google-services:4.3.15") // Asegúrate de que esta versión sea compatible.
    }
}

// Eliminar el bloque allprojects con los repositorios para evitar conflictos
// Todos los repositorios y la configuración de dependencias se gestionarán en settings.gradle.kts

// Puedes agregar configuraciones comunes a todos los subproyectos/módulos aquí, si es necesario
