package com.example.proyectologin006d_final.ui.login



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.proyectologin006d_final.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectologin006d_final.ui.login.LoginUiState
import com.example.proyectologin006d_final.ui.login.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
// Permite usar funciones Material 3 qe son experimentales
@Composable  // Genera Interfz Garfica

fun LoginScreen(   navController: NavController,
                   vm: LoginViewModel = viewModel()
) {
    val state = vm.uiState
    var showPass by remember { mutableStateOf(false) }
    var isAdultVerified by remember { mutableStateOf(false) }


    // darkColorScheme  es una funcion de material3 que define un color oscuro
    val ColorScheme = darkColorScheme(
        primary= Color(0xFF98222E),
        onPrimary = Color.White,
        onSurface = Color(0xFF333333), //Gris
    ) // fin dark


    MaterialTheme(
        colorScheme = ColorScheme
    ){ // inicio Aplicar Material



        Scaffold (
            // Crea Estuctra basica de la pantalla Se define topBar, BottomBar
            topBar = {
                TopAppBar(title = {Text("Mi Primer App",
                    color =MaterialTheme.colorScheme.onPrimary,
                )})

                // Crea un AppBar con un titulo

            }// fin topBar
        ) // fin Scaff
        {// Inicio Inner
                innerPadding ->
            // Representa el espacio interno para que no choque con el topBar

            Column (  //   Colaca los elementos de la Ui
                modifier = Modifier
                    .padding( innerPadding)
                    // Evita que quede oculto
                    .fillMaxSize() // Hace que la columnna tome el todo el tamaño
                    .padding(16.dp)
                    .background(Color(0xFFF0F0F0)), // gris Claro
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally  // Centra horizontalmente
                //Define  que elementos dentro la columna estaran separados por 20.dp
            )// fin column
            {// inicio Contenido
                Text(text="¡Bienvenido a Level-Up Gamer!",
                    style= MaterialTheme.typography.headlineMedium,
                    color=MaterialTheme.colorScheme.primary


                ) // Muestra un texto simple en la pantalla




                Image(  // insertar una imagen en la interfaz
                    painter= painterResource(id = R.drawable.logolevelup),
                    contentDescription = "Logo App",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Fit
                    // Ajusta la imagen para que encaje dentro del espacio

                ) // Fin Image


// agregar un espacio entre la imagen y el boton

                Spacer(modifier = Modifier.height(66.dp))




                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Checkbox(
                        checked = isAdultVerified,
                        onCheckedChange = { isAdultVerified = it }
                    )
                    Text(
                        text = "Confirmo que soy mayor de 18 años",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }// fin Row edad



                OutlinedTextField(
                    value = state.username,
                    onValueChange = vm::onUsernameChange,
                    label = { Text("Usuario") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.95f)
                ) // fin user

                OutlinedTextField(
                    value = state.email,
                    onValueChange = vm::onEmailChange,
                    label = { Text("Correo Electrónico") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.95f)
                ) // fin email


                OutlinedTextField(
                    value = state.password,
                    onValueChange = vm::onPasswordChange,
                    label = { Text("Contraseña") },
                    singleLine = true,
                    visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        TextButton(onClick = { showPass = !showPass }) {
                            Text(if (showPass) "Ocultar" else "Ver")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.95f)
                ) // fin passw


                if (state.isDuocUser) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "🎉 ¡Descuento del 20% aplicado para usuarios Duoc!",
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                if (state.error != null) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = state.error ?: "",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }


// agregar un espacio entre la imagen y el boton

                Spacer(modifier = Modifier.height(66.dp))

                Button(onClick = {/* accion futura*/
                    if (!isAdultVerified) {
                        // Mostrar error si no es mayor de edad
                        return@Button
                    }
                    vm.submit { user ->
                       // navController.navigate("muestraDatos/$user")
                        navController.navigate("DrawerMenu/$user")
// hasta aqui fue la clase anterior

                        { // inicio navigate
                            popUpTo("login"){inclusive = true} // no volver al login con Back
                            launchSingleTop = true
                        }// fin navigate
                    }//fin submit

                }, //  fin onClick

                    enabled=!state.isLoading && isAdultVerified,
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) // fin Button
                { // texto Button

                    //   Text("Presioname")
                    Text(if (state.isLoading) "Validando..." else "Iniciar sesión")
                } // fin texto Button
                
                Spacer(modifier = Modifier.height(5.dp))
                
                TextButton(
                    onClick = { navController.navigate("register") }
                ) {
                    Text("¿No tienes cuenta? Regístrate")
                }

            }// fin Contenido

        } // Fin inner


    } // fin Aplicar Material
}// Fin HomeScreen


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // Crear un navController de manera ficticia para fines de la vista previa
    val navController = rememberNavController()

    // Puedes usar un ViewModel simulado aquí si no tienes acceso a uno real
    val vm = LoginViewModel() // Suponiendo que LoginViewModel está correctamente configurado para la vista previa

    LoginScreen(navController = navController, vm = vm)
}