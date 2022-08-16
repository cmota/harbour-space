package com.harbourspace.unsplash.ui.compose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.harbourspace.unsplash.R
import com.harbourspace.unsplash.data.repository.AppPreferences
import com.harbourspace.unsplash.ui.compose.theme.colorDarkPalette
import com.harbourspace.unsplash.ui.compose.theme.colorLightPalette
import com.harbourspace.unsplash.ui.compose.theme.typography

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Firebase.auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContent {

            val username = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }

            MaterialTheme(
                colors = if(AppPreferences(this).getDarkTheme()) colorDarkPalette else colorLightPalette,
                typography = typography
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AddCustomTextField(
                        text = username,
                        hint = R.string.login_hint_username,
                        visualTransformation = VisualTransformation.None,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    AddCustomTextField(
                        text = password,
                        hint = R.string.login_hint_password,
                        visualTransformation = PasswordVisualTransformation(),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.onPrimary
                            ),
                            onClick = {
                                authenticateUser(username.value, password.value)
                            }) {
                            Text(
                                text = stringResource(id = R.string.login_signin),
                                style = typography.h1,
                                color = MaterialTheme.colors.primary
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.onPrimary
                            ),
                            onClick = {
                                registerUser(username.value, password.value)
                            }) {
                            Text(
                                text = stringResource(id = R.string.login_signup),
                                style = typography.h1,
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
             }
        }
    }

    private fun authenticateUser(username: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(
            username,
            password
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Log.d(TAG, "Authentication failed. Error: ${it.exception}")
                Toast.makeText(baseContext, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(username: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(
            username,
            password
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Log.d(TAG, "Authentication failed. Error: ${it.exception}")
                Toast.makeText(baseContext, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun AddCustomTextField(
    text: MutableState<String>,
    @StringRes hint: Int,
    visualTransformation: VisualTransformation,
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = { value ->
            text.value = value
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(id = hint),
                style = typography.h1,
                maxLines = 1
            )
        },
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.LightGray
        )
    )
}