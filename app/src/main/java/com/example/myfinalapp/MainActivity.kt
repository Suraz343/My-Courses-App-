package com.example.myfinalapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myfinalapp.ui.theme.MyFinalAppTheme
import androidx.compose.foundation.background as background1



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFinalAppTheme {
               MaterialUIApp()


                }
            }
        }
    }
@Composable
fun MaterialUIApp(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My Courses")
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { Log.d("ButtonClicked",
                        "Notification Button Clicked")
                    }) {
                        Icon(Icons.Filled.Notifications, contentDescription = null)
                    }
                        IconButton(onClick = { Log.d("ButtonClicked",
                            "Search Button Clicked")
                        }) {
                            Icon(Icons.Filled.Search, contentDescription = null)
                        }
                    IconButton(onClick = { Log.d("ButtonClicked",
                        "Account Button Clicked")
                    }) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Navigation()
    }
}
@Composable
fun NavigationController(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.Account.route) {

        composable(NavigationItem.Account.route) {
            Account()
        }
        composable(NavigationItem.Home.route) {
            Home()
        }

        composable(NavigationItem.Settings.route) {
            Settings()
        }

        composable(NavigationItem.Syllabus.route) {
            Syllabus()
        }
        composable(NavigationItem.Courses.route) {
            Courses()
        }

    }

}

@Composable
fun Navigation() {

    val navController = rememberNavController()

    val items = listOf(
        NavigationItem.Account,
        NavigationItem.Home,
        NavigationItem.Settings,
        NavigationItem.Syllabus,
        NavigationItem.Courses

    )

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.background) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route


                items.forEach {
                    BottomNavigationItem(selected = currentRoute == it.route,
                        label = {
                            Text(
                                text = it.label,
                                color = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = it.icons, contentDescription = null,
                                tint = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )

                        },

                        onClick = {
                            if(currentRoute!=it.route){

                                navController.graph?.startDestinationRoute?.let {
                                    navController.popBackStack(it,true)
                                }

                                navController.navigate(it.route){
                                    launchSingleTop = true
                                }

                            }

                        })

                }


            }


        }) {

        NavigationController(navController = navController)

    }

}
@Composable
fun Account(){
    val backgroundImage = painterResource(id = R.drawable.application)
    Box{
        Image(painter = backgroundImage, contentDescription ="background image",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale =ContentScale.Crop)}
    var name:String by remember { mutableStateOf("") }
    var Course:String by remember { mutableStateOf("") }
    var id:String by remember { mutableStateOf("") }
    var email:String by remember { mutableStateOf("") }
    var password:String by remember { mutableStateOf("") }


    LazyColumn(
        Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 20.dp),
        verticalArrangement =  Arrangement.spacedBy(10.dp),
    ) {

        item {
            Image(
                painter = painterResource(id = R.drawable.mmu),
                contentDescription = "Website logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.5.dp),contentScale = ContentScale.Crop

            )
        }
        item {
            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
                label = { Text(text = "Name", fontWeight = FontWeight.Bold)},
            )
        }
        item {
            OutlinedTextField(
                value = Course,
                onValueChange = {Course = it},
                label = { Text(text = "Course", fontWeight = FontWeight.Bold)},
            )
        }
        item {
            OutlinedTextField(
                value = id,
                onValueChange = {id = it},
                label = { Text(text = "ID",fontWeight = FontWeight.Bold)},
            )
        }

        item {
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = { Text(text = "Email",fontWeight = FontWeight.Bold)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
        }
        item {
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = { Text(text = "Password",fontWeight = FontWeight.Bold)},
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
        item {
            Button(
                onClick = { Log.d("SubmitButton", " Email $email Password: $password ") },
                contentPadding = PaddingValues(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp),
                colors = ButtonDefaults.buttonColors(contentColor =  Color.White, backgroundColor = Color.Blue),
                shape = CircleShape,
            ) {
                Text(text = "Login")
            }
        }
        item {
            Button(
                onClick = { Log.d("SubmitButton", "Name: $name Email $email Password: $password id :$id") },
                contentPadding = PaddingValues(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp),
                colors = ButtonDefaults.buttonColors(contentColor =  Color.White, backgroundColor = Color.Blue),
                shape = CircleShape,
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}

@Composable
fun Home() {

    val backgroundImage = painterResource(id = R.drawable.application)

    Box {
        Image(
            painter = backgroundImage, contentDescription = "background image",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }


    LazyColumn() {
        item {
            Image(
                painter = painterResource(id = R.drawable._2),
                contentDescription = "Website logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),contentScale = ContentScale.Crop

            )
        }
        item {
            Text(
                text = "DashBoard",
                Modifier.padding(5.dp),
                fontFamily = FontFamily.Default,
                color = Color.Blue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                )

            Text(
                text = "This section round up every details like recent activities of the university,upcoming updates, your dashboard info and evey details.",
                Modifier.padding(5.dp),
                fontFamily = FontFamily.Default,
                color = Color.Gray,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Button(
                modifier = Modifier.padding(top = 10.dp),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                ),
                contentPadding = PaddingValues(horizontal = 30.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 2.dp
                ),

                )
            {
                Text(
                    text = "View DashBoard",
                    Modifier.padding(5.dp),
                    fontFamily = FontFamily.Default,
                    color = Color.Yellow,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        item {
            Text(
                text = "Student Profile",
                Modifier.padding(5.dp),
                fontFamily = FontFamily.Default,
                color = Color.Blue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Customize your profile here",
                Modifier.padding(5.dp),
                fontFamily = FontFamily.Default,
                color = Color.Gray,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Website logo",
            )

            Button(
                modifier = Modifier.padding(top = 10.dp),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                ),
                contentPadding = PaddingValues(horizontal = 30.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 2.dp
                ),

                )
            {

                Text(
                    text = "View Profile",
                    Modifier.padding(5.dp),
                    fontFamily = FontFamily.Default,
                    color = Color.Yellow,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        item {
            Text(
                text = "Upcoming Events and Updates",
                Modifier.padding(5.dp),
                fontFamily = FontFamily.Default,
                color = Color.Blue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "View latest updates and upcoming events of  the university here",
                Modifier.padding(5.dp),
                fontFamily = FontFamily.Default,
                color = Color.Gray,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Button(
                modifier = Modifier.padding(top = 10.dp),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                ),
                contentPadding = PaddingValues(horizontal = 30.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 2.dp
                ),

                )
            {

                Text(
                    text = "View",
                    Modifier.padding(5.dp),
                    fontFamily = FontFamily.Default,
                    color = Color.Yellow,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )


            }


        }


    }
}


    @Composable
    fun Settings() {
        Text(
            text = "Settings",
            fontFamily = FontFamily.Default,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 10.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp
        )
        val backgroundImage = painterResource(id = R.drawable.application)

        Box {
            Image(
                painter = backgroundImage, contentDescription = "background image",
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(720.dp)
                .padding(10.dp),
            backgroundColor = Color.White,
            elevation = 0.dp,

            ) {
            Row(
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = "Settings ",
                        fontFamily = FontFamily.Default,
                        color = Color.Blue,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = "Customize all your settings of the app here",
                        fontFamily = FontFamily.Default,
                        color = Color.Gray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Text(text = "General Settings\n", fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,)
                    Text(text = "Home Settings\n", fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,)
                    Text(text = "Account Settings\n", fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,)
                    Text(text = "Password & Security\n", fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,)
                    Text(text = "Notification and Control Center\n", fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,)
                    Text(text = "Additional Settings\n", fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,)
                    Text(text = "About My Courses\n", fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,)
                    Button(
                        modifier = Modifier.padding(top = 10.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(

                        ),
                        contentPadding = PaddingValues(horizontal = 30.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 2.dp
                        ),

                        ) {
                        Text(
                            text = " Go to Settings",
                            fontFamily = FontFamily.Default,
                            color = Color.Yellow,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }



                }

            }
        }
    }


    @Composable
    fun Syllabus() {

        val backgroundImage = painterResource(id = R.drawable.coursess)

        Box {
            Image(
                painter = backgroundImage, contentDescription = "background Image",
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        LazyColumn() {
            item {
                Image(
                    painter = painterResource(id = R.drawable.syllabus),
                    contentDescription = "Website logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),contentScale = ContentScale.Crop

                )
            }

            item {
                Text(
                    text = "View your Syllabus Here",
                    Modifier.padding(5.dp),
                    fontFamily = FontFamily.Default,
                    color = Color.Magenta,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "All the Syllabus of your course is available here of entire course period.\n" +
                            " The syllabus of your course is below.",
                    Modifier.padding(5.dp),
                    fontFamily = FontFamily.Default,
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Button(
                    modifier = Modifier.padding(top = 10.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                    ),
                    contentPadding = PaddingValues(horizontal = 30.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 2.dp
                    ),

                    )
                {

                    Text(
                        text = "View Syllabus",
                        Modifier.padding(5.dp),
                        fontFamily = FontFamily.Default,
                        color = Color.Yellow,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }


        @Composable
        fun Courses() {
            val backgroundImage = painterResource(id = R.drawable.coursess)

            Box {
                Image(
                    painter = backgroundImage,
                    contentDescription = "background image",
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            LazyColumn() {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.course),
                        contentDescription = "Website logo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp), contentScale = ContentScale.Crop

                    )
                }

                item {
                    Text(
                        text = "University Course Content",
                        Modifier.padding(5.dp),
                        fontFamily = FontFamily.Default,
                        color = Color.Magenta,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "All the course provided by the university can be viewed and accessed here.",
                        Modifier.padding(5.dp),
                        fontFamily = FontFamily.Default,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Button(
                        modifier = Modifier.padding(top = 10.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                        ),
                        contentPadding = PaddingValues(horizontal = 30.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 2.dp
                        ),

                        )
                    {

                        Text(
                            text = "View All",
                            Modifier.padding(5.dp),
                            fontFamily = FontFamily.Default,
                            color = Color.Magenta,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                item {
                    Text(
                        text = "Top Courses",
                        Modifier.padding(5.dp),
                        fontFamily = FontFamily.Default,
                        color = Color.Magenta,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "View all the top courses for better job opportunities",
                        Modifier.padding(5.dp),
                        fontFamily = FontFamily.Default,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Button(
                        modifier = Modifier.padding(top = 10.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                        ),
                        contentPadding = PaddingValues(horizontal = 30.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 2.dp
                        ),

                        )
                    {

                        Text(
                            text = "Best Courses",
                            Modifier.padding(5.dp),
                            fontFamily = FontFamily.Default,
                            color = Color.Magenta,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )


                    }


                }
                item {
                    Text(
                        text = "Purchase Courses Online",
                        Modifier.padding(5.dp),
                        fontFamily = FontFamily.Default,
                        color = Color.Magenta,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "You can buy courses form various online platforms such as udemy,coding ninja etc.",
                        Modifier.padding(5.dp),
                        fontFamily = FontFamily.Default,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Button(
                        modifier = Modifier.padding(top = 10.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                        ),
                        contentPadding = PaddingValues(horizontal = 30.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 2.dp
                        ),

                        )
                    {

                        Text(
                            text = "Purchase",
                            Modifier.padding(5.dp),
                            fontFamily = FontFamily.Default,
                            color = Color.Magenta,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )


                    }


                }


            }
        }



