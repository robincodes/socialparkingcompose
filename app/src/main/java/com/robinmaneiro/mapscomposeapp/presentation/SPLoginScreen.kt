package com.robinmaneiro.mapscomposeapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.robinmaneiro.mapscomposeapp.Screen
import com.robinmaneiro.mapscomposeapp.ui.composables.CustomOutlinedTextField

@Composable
fun SPLoginScreen(navController: NavHostController) {

    val focusManager = LocalFocusManager.current

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (welcomeTitle,
            emailField,
            passwordField,
            forgotPassword,
            registerButton,
            skipButton,
            legalMessageText) = createRefs()
        createVerticalChain(emailField, passwordField, chainStyle = ChainStyle.Packed)
//        createVerticalChain(registerButton, skipButton, chainStyle = ChainStyle.Packed)

        Text(
            modifier = Modifier.constrainAs(welcomeTitle) {
                top.linkTo(parent.top, 30.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(emailField.top)
            },
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = "Welcome to MapsComposeApp"
        )

        CustomOutlinedTextField(
            modifier = Modifier.constrainAs(emailField) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(passwordField.top)
            },
            value = email,
            onValueChange = { email = it },
            label = "Email",
            isPasswordField = false,
            leadingIconImageVector = Icons.Default.Email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.moveFocus(FocusDirection.Down) }
            ),
        )

        CustomOutlinedTextField(
            modifier = Modifier.constrainAs(passwordField) {
                top.linkTo(emailField.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPasswordField = true,
            isPasswordVisible = isPasswordVisible,
            onVisibilityChange = { isPasswordVisible = it },
            leadingIconImageVector = Icons.Default.Password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
        )

        TextButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(forgotPassword) {
            top.linkTo(passwordField.bottom)
            end.linkTo(passwordField.end, margin = 8.dp)
        }) {
            Text("Forgot your password?")
        }

        Button(
            onClick = { navController.navigate(Screen.RegistrationScreen.route) {
                navController.backQueue.clear()
                popUpTo(Screen.RegistrationScreen.route)
            }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF008800),
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .constrainAs(registerButton) {
                    top.linkTo(forgotPassword.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "Register")
        }

        OutlinedButton(
            onClick = { navController.navigate(Screen.LocationPermissionScreen.route) },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFF008800),
                backgroundColor = Color.White
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .constrainAs(skipButton) {
                    top.linkTo(registerButton.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "Skip >")
        }

        Text(
            text = "Copyright ©2022 NN4M \nAll Rights Reserved",
            textAlign = TextAlign.Center, //Center text horizontally
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            maxLines = 2,
            modifier = Modifier
                .wrapContentHeight() //Center text vertically
                .constrainAs(legalMessageText) {
                    bottom.linkTo(parent.bottom, 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SPLoginScreen() {
        SPLoginScreen(rememberNavController())
}