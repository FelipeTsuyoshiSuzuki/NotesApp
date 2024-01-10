package com.example.notes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.ui.theme.BabyBlue
import com.example.notes.ui.theme.DarkGreen
import com.example.notes.ui.theme.LightGreen
import com.example.notes.ui.theme.RedOrange
import com.example.notes.ui.theme.RedPink
import com.example.notes.ui.theme.SandColor
import com.example.notes.ui.theme.SkyBlue
import com.example.notes.ui.theme.Violet

@Entity
data class Note(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink, SkyBlue, DarkGreen, SandColor)
    }
}

class InvalidNoteException(message: String): Exception(message)
