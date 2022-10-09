package com.example.myfinalapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myfinalapp.NavigationItem.Home.route

sealed class NavigationItem(val route: String, val label: String, val icons: ImageVector) {
    object Account : NavigationItem("Account", "Account", Icons.Default.AccountCircle)
    object Home : NavigationItem("home", "Home", Icons.Default.Home)
    object Settings : NavigationItem("setting","Settings",Icons.Default.Settings)
    object Syllabus: NavigationItem("Syllabus","Syllabus", Icons.Default.List)
    object Courses: NavigationItem("Courses","Courses", Icons.Default.ShoppingCart)



}


