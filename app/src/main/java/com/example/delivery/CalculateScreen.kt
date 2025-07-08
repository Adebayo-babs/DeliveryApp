package com.example.delivery

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.delivery.ui.theme.DeliveryTheme

data class Category(val name: String, val isSelected: Boolean = false)
data class PackingOption(val name: String, val icon: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateScreen(onNavigateToHome: () -> Unit = {},
                    onCalculateClick: () -> Unit = {},
                    onBackToHome: () -> Unit = {},
                    paddingValues: PaddingValues
) {
    var showResultsDialog by remember { mutableStateOf(false)}
    var senderLocation by remember { mutableStateOf("") }
    var receiverLocation by remember { mutableStateOf("") }
    var approxWeight by remember { mutableStateOf("") }
    var selectedPacking by remember { mutableStateOf("Box") } // Set default value
    var isPackagingDropDownExpanded by remember { mutableStateOf(false) }

    val categories = remember {
        mutableStateListOf(
            Category("Documents", false),
            Category("Glass", false),
            Category("Liquid", false),
            Category("Food", false),
            Category("Electronic", false),
            Category("Product", false),
            Category("Others", false)
        )
    }

    val packingOptions = listOf(
        PackingOption("Box", "📦"),
        PackingOption("Bag", "👜"),
        PackingOption("Envelope", "✉️"),
        PackingOption("Tube", "🪈")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        //Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Calculate",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateToHome) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF6B46C1)
            )
        )

        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            //Destination Section
            Text(
                text = "Destination",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            //Sender Location Field
            DestinationField(
                icon = Icons.Default.LocationOn,
                placeholder = "Sender location",
                value = senderLocation,
                onValueChange = { senderLocation = it}
            )

            Spacer(modifier = Modifier.height(12.dp))

            //Receiver Location Field
            DestinationField(
                icon = Icons.Default.LocationOn,
                placeholder = "Receiver location",
                value = receiverLocation,
                onValueChange = { receiverLocation = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            //Approx Weight Field
            DestinationField(
                icon = Icons.Default.ShoppingCart,
                placeholder = "Approx Weight",
                value = approxWeight,
                onValueChange = { approxWeight = it}
            )

            Spacer(modifier = Modifier.height(32.dp))

            //Packaging Section
            Text(
                text = "Packaging",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "What are you sending?",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            //Packaging Dropdown
            ExposedDropdownMenuBox(
                expanded = isPackagingDropDownExpanded,
                onExpandedChange = { isPackagingDropDownExpanded = it}
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                        .background(
                            color = Color(0xFFF8F9FA),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                        .clickable { isPackagingDropDownExpanded = true },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = packingOptions.find { it.name == selectedPacking }?.icon ?: "📦",
                            fontSize = 20.sp
                        )
                        Text(
                            text = selectedPacking,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        tint = Color.Gray
                    )
                }

                ExposedDropdownMenu(
                    expanded = isPackagingDropDownExpanded,
                    onDismissRequest = { isPackagingDropDownExpanded = false}
                ) {
                    packingOptions.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Text(text = option.icon, fontSize = 20.sp)
                                    Text(text = option.name, fontSize = 16.sp)
                                }
                            },
                            onClick = {
                                selectedPacking = option.name
                                isPackagingDropDownExpanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            //Categories Section
            Text(
                text = "Categories",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "What are you sending?",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            //Categories Grid - Fixed implementation
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val chunkedCategories = categories.chunked(4)
                chunkedCategories.forEach { rowCategories ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowCategories.forEach { category ->
                            CategoryChip(
                                category = category,
                                onToggle = {
                                    val index = categories.indexOfFirst { it.name == category.name}
                                    if (index != -1) {
                                        categories[index] = categories[index].copy(isSelected = !categories[index].isSelected)
                                    }
                                }
                            )
                        }
                    }
                }
            }

            // Add some bottom padding to ensure content doesn't get cut off
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { showResultsDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 2.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF8C00)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Calculate",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }



            //Bottom padding for spacing
//            Spacer(modifier = Modifier.height(20.dp))
        }




    }

    //Results Dialog
    DeliveryResultsDialog(
        isVisible = showResultsDialog,
        estimatedAmount = "$1460",
        onBackToHome = {
            showResultsDialog = false
            onBackToHome()
        },
        onDismiss = {
            showResultsDialog = false
        }
    )
}

@Composable
fun DestinationField(
    icon: ImageVector,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF8F9FA),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )

        Box(modifier = Modifier.weight(1f)) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CategoryChip(
    category: Category,
    onToggle: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                color = if (category.isSelected) Color.Black else Color.Transparent
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onToggle() }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .animateContentSize()

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            if (category.isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = Color(0xFFF5F5F8),
                modifier = Modifier
                    .size(10.dp)
                    .graphicsLayer {
                        alpha = if (category.isSelected) 1f else 0f
                    }
            )
          }
            Text(
                text = category.name,
                fontSize = 14.sp,
                color = if (category.isSelected) Color(0xFFF5F5F8) else Color.Black,
                textAlign = TextAlign.Center
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun CalculateScreenPreview() {
    DeliveryTheme {
        CalculateScreen(paddingValues = PaddingValues(0.dp))
    }
}