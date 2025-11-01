package com.example.proyectologin006d_final.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectologin006d_final.ui.theme.LevelUpBlack
import com.example.proyectologin006d_final.ui.theme.LevelUpBlue
import com.example.proyectologin006d_final.ui.theme.LevelUpGreen
import com.example.proyectologin006d_final.ui.theme.LevelUpWhite

data class ProductCategory(
    val id: String,
    val name: String,
    val icon: String,
    val color: Color
)

data class Product(
    val id: String,
    val name: String,
    val price: String,
    val category: String,
    val description: String,
    val manufacturer: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    navController: NavController,
    username: String
) {
    val categories = listOf(
        ProductCategory("juegos-mesa", "Juegos de Mesa", "🎲", LevelUpBlue),
        ProductCategory("accesorios", "Accesorios", "🎮", LevelUpGreen),
        ProductCategory("consolas", "Consolas", "🕹️", LevelUpBlue),
        ProductCategory("computadores", "Computadores Gamers", "💻", LevelUpGreen),
        ProductCategory("sillas", "Sillas Gamers", "🪑", LevelUpBlue),
        ProductCategory("mouse", "Mouse", "🖱️", LevelUpGreen),
        ProductCategory("mousepad", "Mousepad", "🖼️", LevelUpBlue),
        ProductCategory("poleras", "Poleras Personalizadas", "👕", LevelUpGreen),
        ProductCategory("polerones", "Polerones Gamers", "🧥", LevelUpBlue)
    )

    val sampleProducts = listOf(
        Product("JM001", "Catan", "$29.990 CLP", "Juegos de Mesa", "Un clásico juego de estrategia", "Catan Studio"),
        Product("JM002", "Carcassonne", "$24.990 CLP", "Juegos de Mesa", "Juego de colocación de fichas", "Z-Man Games"),
        Product("AC001", "Controlador Xbox Series X", "$59.990 CLP", "Accesorios", "Controlador inalámbrico", "Microsoft"),
        Product("AC002", "Auriculares HyperX Cloud II", "$79.990 CLP", "Accesorios", "Sonido envolvente de calidad", "HyperX"),
        Product("CO001", "PlayStation 5", "$549.990 CLP", "Consolas", "Consola de última generación", "Sony"),
        Product("CG001", "PC Gamer ASUS ROG Strix", "$1.299.990 CLP", "Computadores Gamers", "Potente equipo para gamers", "ASUS"),
        Product("SG001", "Silla Secretlab Titan", "$349.990 CLP", "Sillas Gamers", "Máximo confort para gaming", "Secretlab"),
        Product("MS001", "Mouse Logitech G502 HERO", "$49.990 CLP", "Mouse", "Sensor de alta precisión", "Logitech"),
        Product("MP001", "Mousepad Razer Goliathus", "$29.990 CLP", "Mousepad", "Iluminación RGB personalizable", "Razer"),
        Product("PP001", "Polera Gamer 'Level-Up'", "$14.990 CLP", "Poleras Personalizadas", "Personalizable con tu gamer tag", "Level-Up Gamer")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Level-Up Gamer",
                        color = LevelUpWhite,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LevelUpBlack
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(LevelUpBlack)
        ) {
            // Header con saludo
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = LevelUpBlue.copy(alpha = 0.2f))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "¡Bienvenido, $username!",
                        style = MaterialTheme.typography.headlineSmall,
                        color = LevelUpWhite,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Explora nuestro catálogo gamer",
                        style = MaterialTheme.typography.bodyMedium,
                        color = LevelUpWhite.copy(alpha = 0.8f)
                    )
                }
            }

            // Categorías
            Text(
                text = "Categorías",
                style = MaterialTheme.typography.headlineSmall,
                color = LevelUpWhite,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    CategoryCard(
                        category = category,
                        onClick = { /* Sin funcionalidad - solo visual */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Productos destacados
            Text(
                text = "Productos Destacados",
                style = MaterialTheme.typography.headlineSmall,
                color = LevelUpWhite,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(sampleProducts) { product ->
                    ProductCard(
                        product = product,
                        onClick = { /* Sin funcionalidad - solo visual */ }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    category: ProductCategory,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(100.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = category.color.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = category.icon,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = category.name,
                style = MaterialTheme.typography.bodySmall,
                color = LevelUpWhite,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = LevelUpBlack),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = LevelUpWhite,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = product.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = LevelUpGreen,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = LevelUpWhite.copy(alpha = 0.7f)
                )
                Text(
                    text = "Fabricante: ${product.manufacturer}",
                    style = MaterialTheme.typography.bodySmall,
                    color = LevelUpWhite.copy(alpha = 0.5f)
                )
            }
            Text(
                text = product.price,
                style = MaterialTheme.typography.titleLarge,
                color = LevelUpBlue,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogScreenPreview() {
    CatalogScreen(
        navController = rememberNavController(),
        username = "Gamer123"
    )
}
