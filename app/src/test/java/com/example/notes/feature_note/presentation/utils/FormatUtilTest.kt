package com.example.notes.feature_note.presentation.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class FormatUtilTest {

    @Test
    fun `timestamp para dia mes ano hora e minuto`() {
        val timestamp = 1705705241637
        val result = formatDate(timestamp)
        assertEquals(result, "19/01/2024 20:00")
    }
}