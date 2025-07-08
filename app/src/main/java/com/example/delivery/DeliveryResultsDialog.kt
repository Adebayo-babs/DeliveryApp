package com.example.delivery

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DeliveryResultsDialog(
    isVisible: Boolean,
    estimatedAmount: String = "$1460",
    onBackToHome: () -> Unit,
    onDismiss: () -> Unit = {}
) {
    if (isVisible) {
        val amountNumber = estimatedAmount.replace("$", "").toIntOrNull() ?: 0
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        //App Logo/Brand
                        Text(
                            text = "MoveMate",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6B46C1)
                        )

                        Text(
                            text = "🚛",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        //Package Illustration
                        PackageIllustration()

                        Spacer(modifier = Modifier.height(40.dp))

                        Text(
                            text = "Total Estimated Amount",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        //Amount

                        AnimatedAmountOnAppear(
                            isVisible = isVisible,
                            target = estimatedAmount.replace("$", "").toIntOrNull() ?: 0
                        )

                        Text(
                            text = "USD",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF00C851),
                            modifier = Modifier.padding(start = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "This amount is estimated, this will vary of you change your location or weight",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        //Back to Home Button
                        Button(
                            onClick = onBackToHome,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF8C00)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Done",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PackageIllustration() {
    Box(
        modifier = Modifier.size(120.dp),
        contentAlignment = Alignment.Center
    ) {
        //Main package Box
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = Color(0xFFE8E8E8),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            //Package tape/lines
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(Color(0xFFBDBDBD))
                    .align(Alignment.Center)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(
                                color = Color(0xFFBDBDBD),
                                shape = RoundedCornerShape(2.dp)
                            )
                    )
                }
            }

            //Package Label
            Box(
                modifier = Modifier
                    .size(20.dp, 12.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(2.dp)
                    )
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
            )

            //Shadow Effect
            Box(
                modifier = Modifier
                    .size(80.dp, 8.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(50)
                    )
                    .align(Alignment.BottomCenter)
                    .alpha(0.5f)
            )
        }
    }
}



@Composable
fun AnimatedAmountOnAppear(
    isVisible: Boolean,
    target: Int,
    modifier: Modifier = Modifier
) {
    var animateTo by remember { mutableStateOf(0) }
    LaunchedEffect(isVisible) {
        if (isVisible) animateTo = target else animateTo = 0
    }
    val animatedValue by animateIntAsState(
        targetValue = animateTo,
        animationSpec = tween(durationMillis = 1000)
    )
    Text(
        text = "$${animatedValue}",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF00C851),
        modifier = modifier
    )
}

