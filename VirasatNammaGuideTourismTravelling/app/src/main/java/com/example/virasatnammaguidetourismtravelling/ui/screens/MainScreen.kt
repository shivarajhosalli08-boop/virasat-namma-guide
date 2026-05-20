package com.example.virasatnammaguidetourismtravelling.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.compose.ui.draw.shadow
import coil.compose.AsyncImage
import com.example.virasatnammaguidetourismtravelling.ChatViewModel
import com.example.virasatnammaguidetourismtravelling.UiState
import com.example.virasatnammaguidetourismtravelling.data.Place
import com.example.virasatnammaguidetourismtravelling.data.samplePlaces

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Chat", "Explore", "Planner")
    val icons = listOf(Icons.Default.Home, Icons.AutoMirrored.Filled.Chat, Icons.Default.Place, Icons.AutoMirrored.Filled.EventNote)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute?.startsWith("place_detail") != true) {
                CenterAlignedTopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Explore, 
                                contentDescription = null, 
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = when (currentRoute) {
                                    "home" -> "Virasat Namma Guide"
                                    "chat" -> "Virasat AI Assistant"
                                    "explore" -> "Explore Karnataka"
                                    "planner" -> "AI Trip Planner"
                                    else -> "Virasat Namma Guide"
                                },
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Notifications */ }) {
                            Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = MaterialTheme.colorScheme.primary)
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                        titleContentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.shadow(elevation = 4.dp, spotColor = MaterialTheme.colorScheme.primary)
                )
            }
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(item.lowercase()) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { 
                HomeScreen(
                    onPlaceClick = { id -> navController.navigate("place_detail/$id") },
                    onNavigate = { route -> navController.navigate(route) }
                ) 
            }
            composable("chat") { ChatScreen() }
            composable("explore") { ExploreScreen(onPlaceClick = { id -> navController.navigate("place_detail/$id") }) }
            composable("planner") { PlannerScreen() }
            composable(
                "place_detail/{placeId}",
                arguments = listOf(navArgument("placeId") { type = NavType.StringType })
            ) { backStackEntry ->
                val placeId = backStackEntry.arguments?.getString("placeId")
                val place = samplePlaces.find { it.id == placeId }
                if (place != null) {
                    PlaceDetailScreen(
                        place = place,
                        onBack = { navController.popBackStack() },
                        onAskAi = {
                            navController.navigate("chat")
                            // Note: In a real app we'd pass the query to the ChatViewModel here
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(onPlaceClick: (String) -> Unit, onNavigate: (String) -> Unit) {
    val scrollState = rememberScrollState()
    val categories = listOf(
        "🏛️ Heritage" to "explore",
        "🐅 Wildlife" to "explore",
        "🏖️ Beaches" to "explore",
        "🛕 Temples" to "explore",
        "⛰️ Hills" to "explore"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 24.dp)
    ) {
        // Premium Profile & Greeting Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Namaste, Traveller! ✨", 
                    style = MaterialTheme.typography.headlineMedium, 
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Where will your Karnataka journey begin?", 
                    style = MaterialTheme.typography.bodyMedium, 
                    color = Color.Gray
                )
            }
            Surface(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(48.dp).clickable { /* Profile */ }
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.padding(12.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        // AI Search / Assistant Quick Banner
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable { onNavigate("chat") },
            shape = RoundedCornerShape(100),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Ask Virasat AI about any destination...",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Quick Activities / Categories Row
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { (label, route) ->
                Surface(
                    shape = RoundedCornerShape(100),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clickable { onNavigate(route) }
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Featured Highlights Carousel
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Featured Highlights", 
                style = MaterialTheme.typography.titleLarge, 
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "See All", 
                style = MaterialTheme.typography.labelLarge, 
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onNavigate("explore") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val featuredPlaces = samplePlaces.filter { it.id in listOf("hampi", "mysuru_palace", "jog_falls", "coorg") }
            items(featuredPlaces, key = { it.id }) { place ->
                FeaturedPlaceCard(place = place, onClick = { onPlaceClick(place.id) })
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // AI Trip Planner Call to Action Banner
        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Plan Your Dream Itinerary",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Get a personalized day-wise trip plan powered by Gemini AI. Custom tailored for your schedule and budget.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onNavigate("planner") },
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text("Launch AI Planner", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.ChevronRight, contentDescription = null)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Popular Destinations Vertical List
        Text(
            text = "Popular Destinations", 
            style = MaterialTheme.typography.titleLarge, 
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val popularPlaces = samplePlaces.filter { it.id in listOf("gokarna", "murudeshwara", "belur", "bandipur") }
            popularPlaces.forEach { place ->
                PlaceHorizontalCard(place = place, onClick = { onPlaceClick(place.id) })
            }
        }
    }
}

@Composable
fun FeaturedPlaceCard(place: Place, onClick: () -> Unit) {
    val context = LocalContext.current
    val imageResId = remember(place.imageResName) {
        context.resources.getIdentifier(place.imageResName, "drawable", context.packageName)
    }

    Card(
        modifier = Modifier
            .width(280.dp)
            .height(320.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (imageResId != 0) {
                androidx.compose.foundation.Image(
                    painter = androidx.compose.ui.res.painterResource(id = imageResId),
                    contentDescription = place.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Image, 
                        contentDescription = null, 
                        modifier = Modifier.size(64.dp), 
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            // Rich dark gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                            startY = 300f
                        )
                    )
            )

            // Rating / Featured badge
            Surface(
                color = Color.Black.copy(alpha = 0.6f),
                shape = RoundedCornerShape(100),
                modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("4.9", color = Color.White, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                }
            }

            // Content at bottom
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                Text(
                    text = place.category,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Place, contentDescription = null, tint = Color.White.copy(alpha = 0.8f), modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = place.location,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun PlaceHorizontalCard(place: Place, onClick: () -> Unit) {
    val context = LocalContext.current
    val imageResId = remember(place.imageResName) {
        context.resources.getIdentifier(place.imageResName, "drawable", context.packageName)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                if (imageResId != 0) {
                    androidx.compose.foundation.Image(
                        painter = androidx.compose.ui.res.painterResource(id = imageResId),
                        contentDescription = place.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Image, 
                            contentDescription = null, 
                            modifier = Modifier.size(24.dp), 
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = place.category,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = place.location,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun PlaceCard(place: Place, onClick: () -> Unit) {
    val context = LocalContext.current
    val imageResId = remember(place.imageResName) {
        context.resources.getIdentifier(place.imageResName, "drawable", context.packageName)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            if (imageResId != 0) {
                androidx.compose.foundation.Image(
                    painter = androidx.compose.ui.res.painterResource(id = imageResId),
                    contentDescription = place.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Image, 
                        contentDescription = null, 
                        modifier = Modifier.size(48.dp), 
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = place.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(text = place.category, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = place.description, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
            }
        }
    }
}

@Composable
fun PlaceDetailScreen(place: Place, onBack: () -> Unit, onAskAi: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val imageResId = remember(place.imageResName) {
        context.resources.getIdentifier(place.imageResName, "drawable", context.packageName)
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box {
            if (imageResId != 0) {
                androidx.compose.foundation.Image(
                    painter = androidx.compose.ui.res.painterResource(id = imageResId),
                    contentDescription = place.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Image, 
                        contentDescription = null, 
                        modifier = Modifier.size(64.dp), 
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            IconButton(
                onClick = onBack,
                modifier = Modifier.padding(16.dp).background(Color.White.copy(alpha = 0.5f), RoundedCornerShape(50))
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = place.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(text = place.location, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
            
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = place.category,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "About", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = place.description, style = MaterialTheme.typography.bodyLarge, lineHeight = 24.sp)

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "History", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = place.history, style = MaterialTheme.typography.bodyLarge, lineHeight = 24.sp)
            
            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = { 
                        val gmmIntentUri = Uri.parse("google.navigation:q=${place.name}, ${place.location}")
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        context.startActivity(mapIntent)
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Directions, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Directions")
                }
                
                OutlinedButton(
                    onClick = onAskAi,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.QuestionAnswer, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ask AI")
                }
            }
        }
    }
}

@Composable
fun ChatScreen(chatViewModel: ChatViewModel = viewModel()) {
    var prompt by remember { mutableStateOf("") }
    val uiState by chatViewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Virasat AI Assistant", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        
        Box(modifier = Modifier.weight(1f).fillMaxWidth().padding(vertical = 16.dp)) {
            when (uiState) {
                is UiState.Initial -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.align(Alignment.Center)) {
                        Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(64.dp), tint = Color.Gray.copy(alpha = 0.3f))
                        Text("How can I help you today?", color = Color.Gray)
                    }
                }
                is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is UiState.Success -> {
                    val scrollState = rememberScrollState()
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = (uiState as UiState.Success).outputText,
                            modifier = Modifier.padding(16.dp).verticalScroll(scrollState),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                is UiState.Error -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Error: ${(uiState as UiState.Error).errorMessage}", color = MaterialTheme.colorScheme.error)
                        Button(onClick = { if (prompt.isNotBlank()) chatViewModel.sendPrompt(prompt) }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 8.dp)) {
            IconButton(onClick = { /* Voice */ }) {
                Icon(Icons.Default.Mic, contentDescription = "Voice")
            }
            TextField(
                value = prompt,
                onValueChange = { prompt = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Ask about destinations...") },
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            FloatingActionButton(
                onClick = { if (prompt.isNotBlank()) chatViewModel.sendPrompt(prompt) },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", tint = Color.White)
            }
        }
    }
}

@Composable
fun ExploreScreen(onPlaceClick: (String) -> Unit) {
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("All", "UNESCO", "Heritage", "Nature", "Pilgrimage", "Hill Station")
    
    val filteredPlaces = remember(selectedCategory) {
        if (selectedCategory == "All") {
            samplePlaces
        } else {
            samplePlaces.filter { place -> 
                when (selectedCategory) {
                    "UNESCO" -> place.category.contains("UNESCO", ignoreCase = true)
                    "Heritage" -> place.category.contains("Heritage", ignoreCase = true) || place.category.contains("Architecture", ignoreCase = true)
                    "Nature" -> place.category.contains("Nature", ignoreCase = true) || place.category.contains("Wonder", ignoreCase = true) || place.category.contains("Wildlife", ignoreCase = true) || place.category.contains("Beaches", ignoreCase = true)
                    "Pilgrimage" -> place.category.contains("Pilgrimage", ignoreCase = true) || place.category.contains("Religious", ignoreCase = true)
                    "Hill Station" -> place.category.contains("Hill Station", ignoreCase = true)
                    else -> true
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Discover Karnataka", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(
            text = "Explore timeless heritage, pristine nature, and sacred temples.",
            style = MaterialTheme.typography.bodyMedium, 
            color = Color.Gray
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(category, fontWeight = FontWeight.SemiBold) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(filteredPlaces, key = { it.id }) { place ->
                PlaceGridCard(place = place, onClick = { onPlaceClick(place.id) })
            }
        }
    }
}

@Composable
fun PlaceGridCard(place: Place, onClick: () -> Unit) {
    val context = LocalContext.current
    val imageResId = remember(place.imageResName) {
        context.resources.getIdentifier(place.imageResName, "drawable", context.packageName)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(140.dp)) {
                if (imageResId != 0) {
                    androidx.compose.foundation.Image(
                        painter = androidx.compose.ui.res.painterResource(id = imageResId),
                        contentDescription = place.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Image, 
                            contentDescription = null, 
                            modifier = Modifier.size(36.dp), 
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

                // Dark gradient overlay for premium look
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                                startY = 150f
                            )
                        )
                )

                // Category badge floating on image
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f),
                    shape = RoundedCornerShape(bottomEnd = 12.dp, topStart = 20.dp),
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Text(
                        text = place.category.split(" ").first(),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // Location subtitle over gradient
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.BottomStart).padding(8.dp)
                ) {
                    Icon(
                        Icons.Default.Place,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = place.location.split(",").first(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = place.name, 
                    style = MaterialTheme.typography.titleMedium, 
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = place.description, 
                    style = MaterialTheme.typography.bodySmall, 
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun PlannerScreen(chatViewModel: ChatViewModel = viewModel()) {
    var destination by remember { mutableStateOf("") }
    val uiState by chatViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState !is UiState.Success) {
            Icon(
                Icons.Default.AutoAwesome,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "AI Trip Planner",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Generate a personalized itinerary for your next adventure.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(text = "Where do you want to go?", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = destination,
                        onValueChange = { destination = it },
                        placeholder = { Text("e.g. Hampi for 3 days") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (destination.isNotBlank()) {
                                chatViewModel.sendPrompt("Create a detailed itinerary for $destination")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        enabled = uiState !is UiState.Loading
                    ) {
                        if (uiState is UiState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Plan My Trip")
                        }
                    }
                }
            }
        }

        if (uiState is UiState.Loading && destination.isNotBlank()) {
            Spacer(modifier = Modifier.height(32.dp))
            Text("Crafting your perfect heritage journey...")
        }

        if (uiState is UiState.Success) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Itinerary",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = { destination = ""; chatViewModel.resetState() }) {
                    Text("Start Over")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                val scrollState = rememberScrollState()
                Text(
                    text = (uiState as UiState.Success).outputText,
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(scrollState),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        if (uiState is UiState.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Error: ${(uiState as UiState.Error).errorMessage}",
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
            Button(onClick = { chatViewModel.sendPrompt("Create a detailed itinerary for $destination") }) {
                Text("Retry")
            }
        }
    }
}
