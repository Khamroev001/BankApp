package com.example.bankapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.toolbox.ImageRequest
import com.example.bankapp.ui.theme.BankAppTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
class AddBankActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val database= Firebase.database
                    val myRef=database.reference

                    var bank_name =remember {
                        mutableStateOf("")
                    }
                    var bank_rating =remember {
                        mutableStateOf(6.5)
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        Arrangement.spacedBy(6.dp)
                    ) {
                        Spacer(modifier = Modifier.height(120.dp))

                        Text(text = "Bank malumotlarini kiriting",
                            Modifier
                                .height(40.dp)
                                .fillMaxWidth())




                        // Text Fields
                        OutlinedTextField(
                            value = "Bank",
                            onValueChange = { bank_name.value = it },
                            label = { Text("Bank nomi") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )

                        OutlinedTextField(
                            value = "0",
                            onValueChange = { bank_rating.value = it.toDouble() },
                            label = { Text("Bank reytingi") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )



                        // Update Button
                        Button(
                            onClick = {

                    val bank=Bank(name = bank_name.value.toString(), rating = bank_rating.value.toInt())
                    myRef.child("banks")
                        .setValue(bank)
                        .addOnSuccessListener {
                            val i= Intent(this@AddBankActivity, MainActivity::class.java)
//                            i.putExtra("name",userData.name)
//                            i.putExtra("uid",userData.uid)
                            startActivity(i)
                        }

                                  },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .padding(bottom = 8.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Update")
                        }

                        // Logout Button
                        Button(
                            onClick = {
                               onBackPressed()
                                 },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .padding(bottom = 8.dp)
                        ) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Back")
                        }

                }
                }
            }
        }
    }
}
