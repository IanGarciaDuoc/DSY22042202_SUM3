

package com.example.vinachos

class OlvidasteScreenTestHelper {
    // Validación de RUT
    fun isValidRUT(rut: String): Boolean {
        val cleanRut = rut.replace(".", "").replace("-", "")
        if (cleanRut.length < 8) return false

        val rutDigits = cleanRut.dropLast(1)
        val dv = cleanRut.takeLast(1).toUpperCase()

        var sum = 0
        var multiplier = 2
        for (i in rutDigits.reversed()) {
            sum += (i.toString().toInt()) * multiplier
            multiplier = if (multiplier < 7) multiplier + 1 else 2
        }

        val expectedDv = 11 - (sum % 11)
        val expectedDvChar = when (expectedDv) {
            11 -> "0"
            10 -> "K"
            else -> expectedDv.toString()
        }

        return dv == expectedDvChar
    }

    // Validación de correo
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)"
        return email.matches(emailRegex.toRegex())
    }
}
