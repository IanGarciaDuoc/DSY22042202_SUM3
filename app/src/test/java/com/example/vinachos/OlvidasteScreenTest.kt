package com.example.dyf

import com.example.vinachos.OlvidasteScreenTestHelper
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class OlvidasteScreenTest {

    private lateinit var olvidasteScreen: OlvidasteScreenTestHelper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        olvidasteScreen = OlvidasteScreenTestHelper()
    }

    @Test
    fun isValidEmail_correctEmail_returnTrue() {
        val email = "test@example.com"

        val result = olvidasteScreen.isValidEmail(email)

        assertTrue(result)
    }

    @Test
    fun isValidEmail_incorrectEmail_returnFalse() {
        val email = "invalid-email"

        val result = olvidasteScreen.isValidEmail(email)

        assertFalse(result)
    }

    @Test
    fun isValidRUT_correctRUT_returnTrue() {
        val rut = "12345678-5"

        val result = olvidasteScreen.isValidRUT(rut)

        assertTrue(result)
    }


}
