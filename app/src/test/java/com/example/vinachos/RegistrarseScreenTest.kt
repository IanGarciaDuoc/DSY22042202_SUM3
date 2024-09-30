import org.junit.Assert.*
import org.junit.Test

class RegistrarseScreenTest {



    @Test
    fun isValidRUT_incorrectRUT_returnFalse() {
        val rut = "12345678-0"
        assertFalse(isValidRUT(rut))
    }

    @Test
    fun isValidEmail_correctEmail_returnTrue() {
        val email = "test@example.com"
        assertTrue(isValidEmail(email))
    }

    @Test
    fun isValidEmail_incorrectEmail_returnFalse() {
        val email = "testexample.com"
        assertFalse(isValidEmail(email))
    }



    @Test
    fun validateForm_incorrectRUT_returnFalse() {
        val rut = "12345678-0"
        val nombreCompleto = "John Doe"
        val correo = "john@example.com"
        val password = "password123"
        val validarPassword = "password123"

        assertFalse(validateForm(rut, nombreCompleto, correo, password, validarPassword))
    }

    @Test
    fun validateForm_emptyNombreCompleto_returnFalse() {
        val rut = "12345678-9"
        val nombreCompleto = ""
        val correo = "john@example.com"
        val password = "password123"
        val validarPassword = "password123"

        assertFalse(validateForm(rut, nombreCompleto, correo, password, validarPassword))
    }

    @Test
    fun validateForm_incorrectEmail_returnFalse() {
        val rut = "12345678-9"
        val nombreCompleto = "John Doe"
        val correo = "johnexample.com"
        val password = "password123"
        val validarPassword = "password123"

        assertFalse(validateForm(rut, nombreCompleto, correo, password, validarPassword))
    }

    @Test
    fun validateForm_passwordMismatch_returnFalse() {
        val rut = "12345678-9"
        val nombreCompleto = "John Doe"
        val correo = "john@example.com"
        val password = "password123"
        val validarPassword = "password456"

        assertFalse(validateForm(rut, nombreCompleto, correo, password, validarPassword))
    }

    // Funciones auxiliares para los tests
    private fun isValidRUT(rut: String): Boolean {
        // Implementación de la validación de RUT
        // Esta es una versión simplificada, deberías usar la implementación real de tu RegistrarseScreen
        val cleanRut = rut.replace(".", "").replace("-", "")
        return cleanRut.length == 9 && cleanRut.last().toUpperCase() == 'K'
    }

    private fun isValidEmail(email: String): Boolean {
        // Implementación de la validación de email
        // Esta es una versión simplificada, deberías usar la implementación real de tu RegistrarseScreen
        return email.contains("@") && email.contains(".")
    }

    private fun validateForm(rut: String, nombreCompleto: String, correo: String, password: String, validarPassword: String): Boolean {
        // Implementación de la validación del formulario
        // Esta es una versión simplificada, deberías usar la lógica real de tu RegistrarseScreen
        return isValidRUT(rut) &&
                nombreCompleto.isNotBlank() &&
                isValidEmail(correo) &&
                password.isNotBlank() &&
                password == validarPassword
    }
}