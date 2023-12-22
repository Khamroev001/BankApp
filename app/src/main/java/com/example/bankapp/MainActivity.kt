package com.example.bankapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bankapp.ui.theme.BankAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(Modifier.fillMaxSize()) {
                        Row(Modifier.fillMaxWidth().background(LightGray)) {

                            Image(painter = painterResource(R.drawable.baseline_add_circle_outline_24),
                                contentDescription = null,
                                Modifier
                                    .size(40.dp)
                                    .padding(horizontal = 6.dp)
                                    .align(Alignment.CenterVertically)
                                    .clickable{
                                          var intent= Intent(this@MainActivity,AddBankActivity::class.java)
                                        startActivity(intent)


                                    }




                            )

                                    }



//                    val bank=Bank()
//
//                    val bank=Bank()
//                    myRef.child("contact").child(user?.uid ?:"")
//                        .setValue(userData)
//                        .addOnSuccessListener {
//                            val i=Intent(this, ContactActivity::class.java)
//                            i.putExtra("name",userData.name)
//                            i.putExtra("uid",userData.uid)
//                            startActivity(i)
//                        }





                }
            }
        }
    }
}}
