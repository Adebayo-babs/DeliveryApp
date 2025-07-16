package com.example.delivery

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.delivery.ui.theme.DeliveryTheme


data class SearchResult(
    val title: String,
    val trackingNumber: String,
    val route: String
)
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SearchScreen(
    onBackClick: () -> Unit = {},
    onChatClick: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    var searchText by remember { mutableStateOf("") }
    val searchResults = remember {
        listOf(
            SearchResult(
                title = "Macbook pro M2",
                trackingNumber = "#NEJ385734857904",
                route = "Paris -> Morocco"
            ),
            SearchResult(
                title = "Hewlett Packard",
                trackingNumber = "#PEY385734857904",
                route = "NewYork -> London"
            ),
            SearchResult(
                title = "Lenovo",
                trackingNumber = "#AUX385734857904",
                route = "Berlin -> Maryland"
            ),
            SearchResult(
                title = "Dell",
                trackingNumber = "#TOT385734857904",
                route = "Dubai -> England"
            ),
            SearchResult(
                title = "Samsung",
                trackingNumber = "#LOV385734857904",
                route = "Luxemborg -> China"
            ),
            SearchResult(
                title = "Sony",
                trackingNumber = "#BOX385734857904",
                route = "Paris -> India"
            )
        )
    }

    val filteredResults = remember(searchText) {
        if (searchText.isEmpty()) {
            searchResults
        } else {
            searchResults.filter { result ->
                result.title.contains(searchText, ignoreCase = true) ||
                        result.trackingNumber.contains(searchText, ignoreCase = true) ||
                        result.route.contains(searchText, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF6B46C1),
                    shape = RoundedCornerShape(bottomStart = 2.dp, bottomEnd = 2.dp)
                )
        ) {
            Column {
                Spacer(modifier = Modifier.height(50.dp))

                //Search Bar with Back Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 30.dp)
                        .background(
                            Color.White,
                            CircleShape
                        ),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // Back Button
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.padding(start = 5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Search Input
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 15.dp, top = 15.dp, end = 5.dp)
                    ) {


                        BasicTextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp
                            ),
                            decorationBox = { innerTextField ->
                                if (searchText.isEmpty()) {
                                    Text(
                                        text = "Search shipments...",
                                        color = Color.Gray,
                                        fontSize = 16.sp
                                    )
                                }
                                innerTextField()
                            }
                        )
                    }

                    // Chat Icon
                    IconButton(
                        onClick = onChatClick,
                        modifier = Modifier
                            .background(
                                Color(0xFFFF8C00),
                                CircleShape
                            )
                            .size(42.dp)
                            .padding(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Chat",
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }
        }

        //Search Results
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredResults) { result ->
                SearchResultItem(
                    result = result,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun SearchResultItem(
    result: SearchResult,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Purple Circle Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        Color(0xFF6B46C1),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📦",
                    fontSize = 16.sp
                )
            }

            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = result.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = result.trackingNumber,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = result.route,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}





@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    DeliveryTheme {
        SearchScreen(onChatClick = {}, onBackClick = {}, paddingValues = PaddingValues(0.dp))
    }
}