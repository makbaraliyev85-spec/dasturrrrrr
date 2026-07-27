package com.example.dasturrrrrr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dasturrrrrr.ui.theme.DasturrrrrrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DasturrrrrrTheme {
                HomeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DasturrrrrrTheme {
        HomeScreen()
    }
}

data class Category(
    val title: String,
    val count: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val color: Color
)

data class Word(
    val english: String,
    val uzbek: String
)

@Composable
fun HomeScreen() {

    var selected by remember { mutableIntStateOf(0) }

    val categories = listOf(
        Category("Barcha so'zlar","12548",Icons.Default.MenuBook,Color(0xFF4A6CF7)),
        Category("Sevimlilar","248",Icons.Default.Favorite,Color(0xFFFF5252)),
        Category("Yangi so'zlar","132",Icons.Default.NewReleases,Color(0xFF4CAF50)),
        Category("Qiyin so'zlar","56",Icons.Default.Edit,Color(0xFFFFB300))
    )

    val history = listOf(
        Word("Apple","Olma"),
        Word("Book","Kitob"),
        Word("Learn","O'rganmoq"),
        Word("Dream","Orzu"),
        Word("Success","Muvaffaqiyat")
    )

    Scaffold(

        containerColor = Color(0xFFF8F9FD),

        bottomBar = {

            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {

                NavigationBarItem(
                    selected = selected == 0,
                    onClick = { selected = 0 },
                    icon = { Icon(if (selected == 0) Icons.Filled.Home else Icons.Outlined.Home, null) },
                    label = { Text("Asosiy", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF4A6CF7),
                        selectedTextColor = Color(0xFF4A6CF7),
                        indicatorColor = Color(0xFFEAF0FF)
                    )
                )

                NavigationBarItem(
                    selected = selected == 1,
                    onClick = { selected = 1 },
                    icon = { Icon(if (selected == 1) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder, null) },
                    label = { Text("Sevimlilar", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF4A6CF7),
                        selectedTextColor = Color(0xFF4A6CF7),
                        indicatorColor = Color(0xFFEAF0FF)
                    )
                )

                NavigationBarItem(
                    selected = selected == 2,
                    onClick = { selected = 2 },
                    icon = { Icon(if (selected == 2) Icons.Filled.History else Icons.Outlined.History, null) },
                    label = { Text("Tarix", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF4A6CF7),
                        selectedTextColor = Color(0xFF4A6CF7),
                        indicatorColor = Color(0xFFEAF0FF)
                    )
                )

                NavigationBarItem(
                    selected = selected == 3,
                    onClick = { selected = 3 },
                    icon = { Icon(if (selected == 3) Icons.Filled.Settings else Icons.Outlined.Settings, null) },
                    label = { Text("Sozlamalar", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF4A6CF7),
                        selectedTextColor = Color(0xFF4A6CF7),
                        indicatorColor = Color(0xFFEAF0FF)
                    )
                )
            }

        }

    ) { padding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 18.dp)

        ) {

            item {

                Spacer(Modifier.height(20.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        Modifier.weight(1f)
                    ) {

                        Text(
                            "Lugat",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "Bilim — eng katta boylik 📖",
                            color = Color.Gray,
                            fontSize = 13.sp
                        )

                    }

                    Icon(
                        Icons.Default.EmojiEvents,
                        null,
                        tint = Color(0xFFFFB300)
                    )

                }

                Spacer(Modifier.height(20.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("So'z qidiring...", color = Color.Gray)
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Search, null, tint = Color(0xFF4A6CF7))
                    },
                    trailingIcon = {
                        Icon(Icons.Default.Mic, null, tint = Color.Gray)
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF4A6CF7),
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFF4A6CF7),
                                        Color(0xFF7C4DFF)
                                    )
                                )
                            )
                            .padding(20.dp)
                    ) {

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column {

                                Text(
                                    "📖 Bugungi so'z",
                                    color = Color.White,
                                    fontSize = 15.sp
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    "Ambition",
                                    color = Color.White,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(

                                    "Intilish, kuchli xohish",
                                    color = Color.White.copy(alpha = .9f),
                                    fontSize = 18.sp
                                )

                            }

                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Text(
                                    "Batafsil",
                                    color = Color(0xFF4A6CF7)
                                )
                            }

                        }

                    }

                }

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    "Kategoriyalar",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(15.dp))

                Column {

                    for (i in categories.chunked(2)) {

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            i.forEach { category ->

                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                        .clickable { },
                                    shape = RoundedCornerShape(24.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White
                                    ),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                                ) {

                                    Column(
                                        modifier = Modifier.padding(20.dp)
                                    ) {

                                        Box(
                                            modifier = Modifier
                                                .size(45.dp)
                                                .clip(RoundedCornerShape(14.dp))
                                                .background(category.color.copy(.12f)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                category.icon,
                                                null,
                                                tint = category.color,
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(16.dp))

                                        Text(
                                            category.title,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 15.sp
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            category.count,
                                            color = Color.Gray,
                                            fontSize = 12.sp
                                        )

                                    }

                                }

                            }

                        }

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                Text(
                    "So'nggi qidiruvlar",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            items(history) { word ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFF0F4FF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = Color(0xFF4A6CF7),
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = word.english,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFF1A1C1E)
                            )

                            Text(
                                text = word.uzbek,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )

                        }

                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(20.dp)
                        )

                    }

                }

            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

    }
}