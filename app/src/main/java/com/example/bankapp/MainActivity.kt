package com.example.bankapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankapp.ui.theme.BankAppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

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

                    var banklist= remember {
                        mutableListOf(Bank())
                    }

                    val reference = Firebase.database.reference.child("bank")
                    reference.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val u = snapshot.children
                            banklist.clear()
                            u.forEach{
                                val userData = it.getValue(Bank::class.java)
                                if (userData != null) {
                                    banklist.add(userData)

                                }
                            }

                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.d("TAG", "onCancelled: ${error.message}")
                        }

                    })


                    




                    Column(Modifier.fillMaxSize()) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .background(LightGray)) {

                            Image(painter = painterResource(R.drawable.baseline_add_circle_outline_24),
                                contentDescription = null,
                                Modifier
                                    .size(40.dp)
                                    .padding(horizontal = 6.dp)
                                    .align(Alignment.CenterVertically)
                                    .clickable {
                                        var intent =
                                            Intent(this@MainActivity, AddBankActivity::class.java)
                                        startActivity(intent)


                                    }

                            )


                                    }


                        }


                        LazyColumn() {
                            items(banklist.sortedByDescending { it.rating }) {
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .clickable {

                                        },
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = it.name ?: "",
                                        Modifier.padding(start = 12.dp),
                                        fontSize = 22.sp
                                    )
                                    Image(painter = painterResource(R.drawable.star),
                                        contentDescription = null)

                                    Text(
                                        text = it.rating.toString() ?: "",
                                        Modifier.padding(start = 12.dp),
                                        fontSize = 22.sp
                                    )

                                    Image(painter = painterResource(R.drawable.baseline_system_update_24),
                                        contentDescription = null, modifier = Modifier.clickable {

                                            var intent=Intent(this@MainActivity,UpdateActivity::class.java)
                                            startActivity(intent)
                                        })


                                    Image(painter = painterResource(R.drawable.baseline_delete_outline_24),
                                        contentDescription = null, modifier = Modifier.clickable {
                                            banklist.remove(it)
                                        })

                                }
                            }




                }
                    
                    
                    
            }
        }
    }
}}
