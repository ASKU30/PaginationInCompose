package com.example.paginationincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.paginationincompose.ui.theme.PaginationInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaginationInComposeTheme {
                val viewModel = viewModel<MainViewModel>()
                val state = viewModel.state
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.items.size) { i ->
                        val item = state.items[i]
                        if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                            viewModel.loadNextItems()
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = item.title,
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(item.description)
                        }
                    }
                    item {
                        if (state.isLoading) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}