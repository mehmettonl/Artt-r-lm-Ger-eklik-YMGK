package com.achelmas.numart.mediumLevelMVC

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.achelmas.numart.R
import com.achelmas.numart.easyLevelMVC.AdapterOfEasyLvl
import com.achelmas.numart.easyLevelMVC.ModelOfEasyLvl
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MediumLevelActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterOfMediumLvl
    private lateinit var mediumLvlList: ArrayList<ModelOfMediumLvl>
    private lateinit var myReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium_level)

        // Initialize Views
        toolbar = findViewById(R.id.mediumLvlActivity_toolBarId)
        recyclerView = findViewById(R.id.mediumLvlActivity_recyclerViewId)

        // Set arrow back button to Toolbar
        toolbar.setNavigationIcon(R.drawable.arrow_back)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        getDataFromFirebase()
    }

    private fun getDataFromFirebase() {
        mediumLvlList = ArrayList()

        myReference = FirebaseDatabase.getInstance().reference

        var query: Query = myReference.child("Medium Level")
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for ( snapshot: DataSnapshot in dataSnapshot.children) {
                    var modelOfMediumLvl: ModelOfMediumLvl = ModelOfMediumLvl()

                    modelOfMediumLvl.target = snapshot.child("Target").value.toString()
                    modelOfMediumLvl.targetNumber = snapshot.child("Target Number").value.toString()
                    modelOfMediumLvl.number1 = snapshot.child("Number1").value.toString()
                    modelOfMediumLvl.number2 = snapshot.child("Number2").value.toString()
                    modelOfMediumLvl.number3 = snapshot.child("Number3").value.toString()
                    modelOfMediumLvl.number4 = snapshot.child("Number4").value.toString()
                    modelOfMediumLvl.number5 = snapshot.child("Number5").value.toString()

                    mediumLvlList.add(modelOfMediumLvl)
                }

                adapter = AdapterOfMediumLvl(this@MediumLevelActivity, mediumLvlList)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}