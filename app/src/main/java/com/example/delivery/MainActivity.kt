package com.example.delivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.delivery.ui.theme.DeliveryTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeliveryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DeliveryServiceApp()
                }
            }
        }
    }
}

// Color scheme
object FitnessColors {
    val Background = Color(0xFF1A1A1A)
    val Surface = Color(0xFF2A2A2A)
    val Yellow = Color(0xFFFFD700)
    val White = Color.White
    val Gray = Color(0xFF808080)
    val LightGray = Color(0xFF404040)
    val Orange = Color(0xFFFF6B35)
    val Purple = Color(0xFF9C27B0)
    val Blue = Color(0xFF2196F3)
}

@Composable
fun DeliveryServiceApp() {
//    var selectedItem by remember { mutableStateOf(0) }
    var bottomNavVisible by remember { mutableStateOf(true) }

    var currentScreen by remember { mutableStateOf("home") }
    val navigationStack = remember { mutableStateListOf<String>() }

    val bottomNavItems = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Calculate", Icons.Outlined.DateRange),
        BottomNavItem("Shipment", Icons.AutoMirrored.Filled.Send),
        BottomNavItem("Profile", Icons.Default.Person)
    )

    fun navigateTo(screen: String) {
        navigationStack.add(currentScreen)
        currentScreen = screen
    }

    fun navigateBack(): Unit {
        if (navigationStack.isNotEmpty()) {
            val  previousScreen = navigationStack.removeLastOrNull()
            currentScreen = previousScreen ?: "home"
        }
    }

    BackHandler(enabled = currentScreen != "home") {
        navigateBack()
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = FitnessColors.Background,
            surface = FitnessColors.Surface,
            primary = FitnessColors.Yellow
        )
    ) {
        when (currentScreen) {
            "home" -> HomeScreen(paddingValues = PaddingValues(),
                onNavigate = { screen -> navigateTo(screen)} )
            "calculate" -> CalculateScreen(
                onBack = { navigateTo("home") },
                paddingValues = PaddingValues()
            )
            "shipment" -> ShipmentScreen(
                onBack = { navigateTo("home") },
                paddingValues = PaddingValues()
            )
            "profile" -> ProfileScreen(
                onBack = { navigateTo("home") },
                paddingValues = PaddingValues()
            )

        }
    }

//    Scaffold(
//        bottomBar = {
//            AnimatedVisibility(
//                visible = bottomNavVisible,
//                enter = slideInVertically (initialOffsetY = { it }),
//                exit = slideOutVertically (targetOffsetY = { it })
//            ) {
//                NavigationBar(
//                    containerColor = Color.White,
//                    contentColor = Color(0xFF4A90E2)
//                ) {
//                    bottomNavItems.forEachIndexed { index, item ->
//                        NavigationBarItem(
//                            icon = {
//                                Icon(
//                                    imageVector = item.icon,
//                                    contentDescription = item.title,
//                                    modifier = Modifier.size(24.dp)
//                                )
//                            },
//                            label = {
//                                Text(text = item.title, fontSize = 12.sp)
//                            },
//                            selected = currentScreen == index,
//                            onClick = {
//                                selectedItem = index
//                                bottomNavVisible = (selectedItem == 0)
//
//                            },
//                            colors = NavigationBarItemDefaults.colors(
//                                selectedIconColor = Color(0xFF4A90E2),
//                                selectedTextColor = Color(0xFF4A90E2),
//                                unselectedIconColor = Color.Gray,
//                                unselectedTextColor = Color.Gray,
//                                indicatorColor = Color(0xFFF2F5F8).copy(alpha = 0.1f)
//                            )
//                        )
//
//
//                    }
//                }
//            }
//        }
//    ) { paddingValues ->
//        when (selectedItem) {
//            0 -> HomeScreen(paddingValues = PaddingValues(4.dp))
//            1 -> CalculateScreen(
//                onNavigateToHome = {
//                    selectedItem = 0
//                    bottomNavVisible = true },
//                paddingValues = PaddingValues(4.dp)
//            )
//            2 -> ShipmentScreen(onNavigateToHome = {
//                selectedItem = 0
//                bottomNavVisible = true
//            },
//                paddingValues = PaddingValues(4.dp)
//            )
//            3 -> ProfileScreen(onNavigateToHome = {
//                selectedItem = 0
//                bottomNavVisible = true
//            }, paddingValues = PaddingValues(4.dp))
//        }
//    }
}



data class BottomNavItem (
    val title: String,
    val icon: ImageVector
)

@Preview(showBackground = true)
@Composable
fun DeliveryPreview() {
    DeliveryTheme {
        DeliveryServiceApp()
    }
}