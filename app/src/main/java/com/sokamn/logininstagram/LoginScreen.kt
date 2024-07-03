package com.sokamn.logininstagram

import android.app.Activity
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(
    device = Devices.NEXUS_5,
    showBackground = true
)
@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center))
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

fun enableLogin(email: String, password: String) =
    Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 7


@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
        )
        Spacer(modifier = Modifier.size(24.dp))
        SignUp()
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun SignUp() {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(color = Color(0xFFB5B5B5), fontSize = 12.sp, text = "Don't have account?")
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            color = Color(0xFF3997f1),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            text = "SignUp."
        )
    }
}

@Composable
fun Body(modifier: Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoginEnable by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        LogoImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        EmailField(email) {
            email = it
            isLoginEnable = enableLogin(email, password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        PasswordField(password) {
            password = it
            isLoginEnable = enableLogin(email, password)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isLoginEnable)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
    }
}

@Composable
fun SocialLogin() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Social Login Facebook"
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color(0xFF3997f1),
            text = "Log In with Facebook",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LoginDivider() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(
            Modifier
                .weight(1f)
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
        )
        Text(
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5),
            text = "OR"
        )
        HorizontalDivider(
            Modifier
                .weight(1f)
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
        )
    }
}

@Composable
fun LoginButton(loginEnable: Boolean) {
    Button(
        onClick = {},
        enabled = loginEnable,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF3997f1),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Log In")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF3997f1)
    )
}

@Composable
fun PasswordField(password: String, onTextChanged: (String) -> Unit) {
    var passVisibility by remember { mutableStateOf(false) }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color(0xFFFAFAFA),
            unfocusedTextColor = Color(0xFFB2B2B2),
            unfocusedPlaceholderColor = Color(0xFFA9A9A9),
            focusedPlaceholderColor = Color(0xFFA9A9A9),
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color(0xFFB2B2B2),
            focusedContainerColor = Color(0xFFFAFAFA)
        ),
        trailingIcon = {
            val passVisibilityIcon = if (passVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }

            IconButton(onClick = { passVisibility = !passVisibility }) {
                Icon(imageVector = passVisibilityIcon, contentDescription = "Password Visibility")
            }
        },
        visualTransformation = if (passVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        onValueChange = { onTextChanged(it) })
}

@Composable
fun EmailField(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        placeholder = { Text(text = "Phone, number, email adress or username") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color(0xFFFAFAFA),
            unfocusedTextColor = Color(0xFFA9A9A9),
            unfocusedPlaceholderColor = Color(0xFFB2B2B2),
            focusedPlaceholderColor = Color(0xFFB2B2B2),
            focusedTextColor = Color(0xFFA9A9A9),
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0xFFFAFAFA)
        ),
        onValueChange = { onTextChanged(it) })
}

@Composable
fun LogoImage(modifier: Modifier) {
    Image(
        modifier = modifier.fillMaxWidth(0.5f),
        painter = painterResource(id = R.drawable.instagram),
        contentDescription = "Logo"
    )
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "close",
        modifier = modifier.clickable {
            activity.finish()
        })
}