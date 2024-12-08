package com.achelmas.numart.hardLevelMVC

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

class HardLevelActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterOfHardLvl
    private lateinit var hardLvlList: ArrayList<ModelOfHardLvl>
    private lateinit var myReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_level)

        // Initialize Views
        toolbar = findViewById(R.id.hardLvlActivity_toolBarId)
        recyclerView = findViewById(R.id.hardLvlActivity_recyclerViewId)

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
        hardLvlList = ArrayList()

        myReference = FirebaseDatabase.getInstance().reference

        var query: Query = myReference.child("Hard Level")
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for ( snapshot: DataSnapshot in dataSnapshot.children) {
                    var modelOfHardLvl: ModelOfHardLvl = ModelOfHardLvl()

                    modelOfHardLvl.target = snapshot.child("Target").value.toString()
                    modelOfHardLvl.targetNumber = snapshot.child("Target Number").value.toString()
                    modelOfHardLvl.number1 = snapshot.child("Number1").value.toString()
                    modelOfHardLvl.number2 = snapshot.child("Number2").value.toString()
                    modelOfHardLvl.number3 = snapshot.child("Number3").value.toString()
                    modelOfHardLvl.number4 = snapshot.child("Number4").value.toString()
                    modelOfHardLvl.number5 = snapshot.child("Number5").value.toString()

                    hardLvlList.add(modelOfHardLvl)
                }

                adapter = AdapterOfHardLvl(this@HardLevelActivity, hardLvlList)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}