package com.example.delivery

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class Vehicle(
    val name: String,
    val type: String,
    val description: String
)

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit,
    onNotificationClick: () -> Unit = {},
    onChatClick: () -> Unit = {},
    paddingValues: PaddingValues
) {

    val vehicles = remember {
        listOf(
            Vehicle("Ocean freight", "International", "An international shipping service"),
            Vehicle("Cargo freight", "Reliable", "Fast and reliable cargo service"),
            Vehicle("Air freight", "International", "Express air shipping service")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
//            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        // Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF6B46C1),
                    shape = RoundedCornerShape(2.dp)
                )
        ) {
            Column {
                // Top Row with Profile and Notification
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 50.dp, start = 20.dp, end = 20.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // Profile Image
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.Gray, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "👤",
                                fontSize = 20.sp
                            )
                        }

                        //Location Column
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "📍",
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = "Your location",
                                    fontSize = 12.sp,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Wertheimer, Illinois",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Dropdown",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                    // Notification Icon
                    IconButton(
                        onClick = onNotificationClick,
                        modifier = Modifier
                            .background(
                                Color.White.copy(alpha = 0.2f),
                                CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White
                        )
                    }
                }


                //Spacer for the TopApp Box
                Spacer(modifier = Modifier.height(5.dp))

                // Search Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                        .background(Color.White, CircleShape)
                        .clickable { onNavigate("search") }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )

                        Text(
                            text = "Enter the receipt number...",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )

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
        }

        // Content Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Tracking Section
            Text(
                text = "Tracking",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            // Tracking Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Shipment Number
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Shipment Number",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = "NEJ200889341222231",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }

                        // Truck Icon
                        Text(
                            text = "🚛",
                            fontSize = 32.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Sender and Receiver Info
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Sender
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(text = "📦", fontSize = 12.sp)
                                Text(
                                    text = "Sender",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                            Text(
                                text = "Atlanta, 5243",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        // Time
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Time",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = "• 2 day -3 days",
                                fontSize = 12.sp,
                                color = Color(0xFF10B981),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Receiver and Status
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Receiver
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(text = "📍", fontSize = 12.sp)
                                Text(
                                    text = "Receiver",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                            Text(
                                text = "Chicago, 6342",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        // Status
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Status",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = "Waiting to collect",
                                fontSize = 12.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Add Stop Button
                    TextButton(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color(0xFFFF8C00),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Add Stop",
                            color = Color(0xFFFF8C00),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Available Vehicles Section
            Text(
                text = "Available vehicles",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            // Vehicles List
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(vehicles) { vehicle ->
                    VehicleCard(vehicle = vehicle)
                }
            }

            // Add extra space at the bottom to ensure content is scrollable
            Spacer(modifier = Modifier.height(2.dp))

        }

        BottomNavigationBar(
            onCalculateClick = { onNavigate("calculate")},
            onShipmentHistoryClick = {onNavigate("shipment")},
            onProfileClick = {onNavigate("profile")}
        )
    }
}


@Composable
fun VehicleCard(vehicle: Vehicle) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(160.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = vehicle.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = vehicle.type,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Vehicle Illustration
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentAlignment = Alignment.Center
            ) {
                when (vehicle.name) {
                    "Ocean freight" -> ShipIllustration()
                    "Cargo freight" -> TruckIllustration()
                    "Air freight" -> PlaneIllustration()
                }
            }
        }
    }
}

@Composable
fun ShipIllustration() {
    Canvas(modifier = Modifier.size(80.dp, 40.dp)) {
        val shipColor = Color(0xFF3B82F6)
        val waterColor = Color(0xFF60A5FA)

        // Water waves
        drawRoundRect(
            color = waterColor.copy(alpha = 0.3f),
            topLeft = Offset(0f, size.height * 0.7f),
            size = Size(size.width, size.height * 0.3f),
            cornerRadius = CornerRadius(4f, 4f)
        )

        // Ship hull
        val shipPath = androidx.compose.ui.graphics.Path().apply {
            moveTo(size.width * 0.1f, size.height * 0.6f)
            lineTo(size.width * 0.9f, size.height * 0.6f)
            lineTo(size.width * 0.8f, size.height * 0.8f)
            lineTo(size.width * 0.2f, size.height * 0.8f)
            close()
        }
        drawPath(path = shipPath, color = shipColor)

        // Containers
        repeat(3) { index ->
            drawRoundRect(
                color = Color(0xFFEF4444),
                topLeft = Offset(size.width * (0.25f + index * 0.15f), size.height * 0.3f),
                size = Size(size.width * 0.1f, size.height * 0.3f),
                cornerRadius = CornerRadius(2f, 2f)
            )
        }
    }
}

@Composable
fun TruckIllustration() {
    Canvas(modifier = Modifier.size(80.dp, 40.dp)) {
        val truckColor = Color(0xFF10B981)
        val wheelColor = Color(0xFF374151)

        // Truck cab
        drawRoundRect(
            color = truckColor,
            topLeft = Offset(size.width * 0.1f, size.height * 0.3f),
            size = Size(size.width * 0.25f, size.height * 0.5f),
            cornerRadius = CornerRadius(4f, 4f)
        )

        // Truck trailer
        drawRoundRect(
            color = Color(0xFF6B7280),
            topLeft = Offset(size.width * 0.35f, size.height * 0.2f),
            size = Size(size.width * 0.5f, size.height * 0.6f),
            cornerRadius = CornerRadius(4f, 4f)
        )

        // Wheels
        drawCircle(
            color = wheelColor,
            radius = size.height * 0.1f,
            center = Offset(size.width * 0.25f, size.height * 0.85f)
        )
        drawCircle(
            color = wheelColor,
            radius = size.height * 0.1f,
            center = Offset(size.width * 0.7f, size.height * 0.85f)
        )
    }
}

@Composable
fun PlaneIllustration() {
    Canvas(modifier = Modifier.size(80.dp, 40.dp)) {
        val planeColor = Color(0xFF8B5CF6)

        // Plane body
        drawRoundRect(
            color = planeColor,
            topLeft = Offset(size.width * 0.3f, size.height * 0.4f),
            size = Size(size.width * 0.4f, size.height * 0.2f),
            cornerRadius = CornerRadius(8f, 8f)
        )

        // Wings
        drawRoundRect(
            color = planeColor,
            topLeft = Offset(size.width * 0.2f, size.height * 0.35f),
            size = Size(size.width * 0.6f, size.height * 0.1f),
            cornerRadius = CornerRadius(4f, 4f)
        )

        // Tail
        val tailPath = androidx.compose.ui.graphics.Path().apply {
            moveTo(size.width * 0.7f, size.height * 0.5f)
            lineTo(size.width * 0.85f, size.height * 0.3f)
            lineTo(size.width * 0.85f, size.height * 0.7f)
            close()
        }
        drawPath(path = tailPath, color = planeColor)
    }
}

@Composable
fun BottomNavigationBar(
    onCalculateClick: () -> Unit,
    onShipmentHistoryClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFF0F0F0))
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") },
            selected = true,
            onClick = { }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.AccountBox,
                    contentDescription = "Calculate"
                )
            },
            label = { Text("Calculate") },
            selected = false,
            onClick = onCalculateClick
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Shipment"
                )
            },
            label = { Text("Shipment") },
            selected = false,
            onClick = onShipmentHistoryClick
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile"
                )
            },
            label = { Text("Profile") },
            selected = false,
            onClick = onProfileClick
        )
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DeliveryPreview()
}

