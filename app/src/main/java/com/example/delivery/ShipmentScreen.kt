package com.example.delivery

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.delivery.ui.theme.DeliveryTheme

data class Shipment(
    val id: String,
    val status: ShipmentStatus,
    val title: String,
    val description: String,
    val amount: String,
    val date: String
)

enum class ShipmentStatus(val displayName: String, val color: Color, val backgroundColor: Color) {
    ALL("All", Color.White, Color(0xFF6B46C1)),
    COMPLETED("Completed", Color(0xFF10B981), Color(0xFFECFDF5)),
    IN_PROGRESS("In progress", Color(0xFF3B82F6), Color(0xFFEFF6FF)),
    PENDING("Pending", Color(0xFFF59E0B), Color(0xFFFEF3C7)),
    CANCELLED("Cancelled", Color(0xFFEF4444), Color(0xFFFEF2F2))
}



@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipmentScreen(
    paddingValues: PaddingValues, onBack: () -> Unit) {

    var selectedStatus by remember { mutableStateOf(ShipmentStatus.ALL) }

    val sampleShipments = remember {
        listOf(
            Shipment(
                id = "#NEJ20089934122231",
                status = ShipmentStatus.IN_PROGRESS,
                title = "Arriving today!",
                description = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                amount = "$1400 USD",
                date = "Sep 20, 2023"
            ),
            Shipment(
                id = "#NEJ20089934122237",
                status = ShipmentStatus.IN_PROGRESS,
                title = "Arriving today!",
                description = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                amount = "$1400 USD",
                date = "Sep 20, 2023"
            ),
            Shipment(
                id = "#NEJ20089934122232",
                status = ShipmentStatus.PENDING,
                title = "Arriving today!",
                description = "Your delivery, #NEJ20089934122232 from Atlanta, is arriving today!",
                amount = "$650 USD",
                date = "Sep 20, 2023"
            ),
            Shipment(
                id = "#NEJ20089934122233",
                status = ShipmentStatus.PENDING,
                title = "Arriving today!",
                description = "Your delivery, #NEJ20089934122233 from Atlanta, is arriving today!",
                amount = "$650 USD",
                date = "Sep 20, 2023"
            ),
            Shipment(
                id = "#NEJ20089934122234",
                status = ShipmentStatus.COMPLETED,
                title = "Delivered!",
                description = "Your delivery, #NEJ20089934122234 from Atlanta, has been delivered!",
                amount = "$1200 USD",
                date = "Sep 18, 2023"
            ),
            Shipment(
                id = "#NEJ20089934122236",
                status = ShipmentStatus.COMPLETED,
                title = "Delivered!",
                description = "Your delivery, #NEJ20089934122234 from Atlanta, has been delivered!",
                amount = "$1200 USD",
                date = "Sep 18, 2023"
            ),
            Shipment(
                id = "#NEJ20089934122235",
                status = ShipmentStatus.CANCELLED,
                title = "Cancelled!",
                description = "Your delivery, #NEJ20089934122235 from Georgia, has been cancelled!",
                amount = "$1500 USD",
                date = "Sep 18, 2023"
            ),
            Shipment(
                id = "#NEJ20089934122238",
                status = ShipmentStatus.CANCELLED,
                title = "Cancelled!",
                description = "Your delivery, #NEJ20089934122235 from Georgia, has been cancelled!",
                amount = "$1500 USD",
                date = "Sep 18, 2023"
            )
        )
    }

    val filteredShipments = if (selectedStatus == ShipmentStatus.ALL) {
        sampleShipments
    }else {
        sampleShipments.filter { it.status == selectedStatus }
    }

    val statusCounts = sampleShipments.groupingBy { it.status }.eachCount()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
//                .verticalScroll(rememberScrollState())
        ) {

            //Top App Bar
            TopAppBar(
                title = {
                        Text(
                            text = "Shipment history",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6B46C1)
                )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Filter Tabs
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(ShipmentStatus.entries.toTypedArray()) { status ->
                        FilterChip(
                            selected = selectedStatus == status,
                            onClick = { selectedStatus = status},
                            label = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = status.displayName,
                                        fontSize = 14.sp,
                                        color = if (selectedStatus == status) status.color else Color.Gray
                                    )
                                    if (status != ShipmentStatus.ALL) {
                                        Text(
                                            text = "${statusCounts[status] ?: 0}",
                                            fontSize = 12.sp,
                                            color = if (selectedStatus == status) status.color else Color.Gray,
                                            modifier = Modifier
                                                .background(
                                                    color = if (selectedStatus == status) status.backgroundColor else Color.LightGray.copy(alpha = 0.3f),
                                                    shape = RoundedCornerShape(8.dp)
                                                )
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    } else {
                                        Text(
                                            text = "${sampleShipments.size}",
                                            fontSize = 12.sp,
                                            color = if (selectedStatus == status) Color.White else Color.Gray,
                                            modifier = Modifier
                                                .background(
                                                    color = if (selectedStatus == status) Color.White.copy(alpha = 0.2f) else Color.LightGray.copy(alpha = 0.3f),
                                                    shape = RoundedCornerShape(8.dp)
                                                )
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = if (selectedStatus == status) status.backgroundColor else Color.Transparent,
                                selectedContainerColor = if (status == ShipmentStatus.ALL) status.backgroundColor else status.backgroundColor
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = selectedStatus == status,
                                borderColor = if (selectedStatus == status) status.color else Color.LightGray,
                                selectedBorderColor = status.color
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                // Shipments Title
                Text(
                    text = "Shipments",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Shipments List
                LazyColumn(
                    modifier = Modifier.padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredShipments) { shipment ->
                        ShipmentCard(shipment = shipment)
                    }
            }
        }
        }
}

@Composable
fun ShipmentCard(shipment: Shipment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (modifier = Modifier.weight(1f)) {
                // Status Badge
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatusIndicator(status = shipment.status)
                    Text(
                        text = shipment.status.displayName.lowercase(),
                        fontSize = 12.sp,
                        color = shipment.status.color,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Text(
                    text = shipment.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Description
                Text(
                    text = shipment.description,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Amount and Date
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = shipment.amount,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF6B46C1)
                    )
                    Text(
                        text = shipment.date,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            // Package Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📦",
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
fun StatusIndicator(status: ShipmentStatus) {
    val indicatorColor = when (status) {
        ShipmentStatus.IN_PROGRESS -> Color(0xFF3B82F6)
        ShipmentStatus.PENDING -> Color(0xFFF59E0B)
        ShipmentStatus.COMPLETED -> Color(0xFF10B981)
        ShipmentStatus.CANCELLED -> Color(0xFFEF4444)
        else -> Color.Gray
    }

    Box(
        modifier = Modifier
            .size(8.dp)
            .background(
                color = indicatorColor,
                shape = RoundedCornerShape(4.dp)
            )
    )

}

@Preview(showBackground = true)
@Composable
fun ShipmentScreenPreview() {
    DeliveryTheme {
        ShipmentScreen(onBack = {}, paddingValues = PaddingValues(0.dp))
    }
}
