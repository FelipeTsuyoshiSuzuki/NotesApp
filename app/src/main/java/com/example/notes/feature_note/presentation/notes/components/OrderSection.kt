package com.example.notes.feature_note.presentation.notes.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import com.example.notes.R
import com.example.notes.feature_note.domain.util.NoteOrder
import com.example.notes.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit,
    context: Context
) {
    val isAcending = remember { mutableStateOf(false) }
    val isDescending = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
           DefaultRadioButton(
               text = getString(context, R.string.title_string),
               selected = noteOrder is NoteOrder.Title,
               onSelected = { onOrderChange(NoteOrder.Title(noteOrder.orderType)) }
           )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = getString(context, R.string.date_string),
                selected = noteOrder is NoteOrder.Date,
                onSelected = { onOrderChange(NoteOrder.Date(noteOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = getString(context, R.string.color_string),
                selected = noteOrder is NoteOrder.Color,
                onSelected = { onOrderChange(NoteOrder.Color(noteOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultIconButton(
                icon = Icons.Default.ArrowUpward,
                contentDescription = "Crescente",
                text = "Crescente",
                isSelected = isAcending.value,
                onSelected = {
                    noteOrder.orderType is OrderType.Ascending
                    onOrderChange(noteOrder.copy(OrderType.Ascending))
                    isAcending.value = true
                    isDescending.value = false
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultIconButton(
                icon = Icons.Default.ArrowDownward,
                contentDescription = "Decrescente",
                text = "Decrescente",
                isSelected = isDescending.value,
                onSelected = {
                    noteOrder.orderType is OrderType.Descending
                    onOrderChange(noteOrder.copy(OrderType.Descending))
                    isAcending.value = false
                    isDescending.value = true
                }
            )
        }
    }
}