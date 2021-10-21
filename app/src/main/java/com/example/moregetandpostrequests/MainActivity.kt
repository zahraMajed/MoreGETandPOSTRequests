package com.example.moregetandpostrequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var edName:EditText
    lateinit var btnSave:Button
    lateinit var btnView:Button

    lateinit var userName:String
    lateinit var namesList:ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edName=findViewById(R.id.edName)
        btnSave=findViewById(R.id.btnSave)
        btnView=findViewById(R.id.btnView)

        namesList= arrayListOf()
        rv_main.adapter=RecycelerAdapter(namesList)
        rv_main.layoutManager=LinearLayoutManager(this)

        btnSave.setOnClickListener(){
            userName=edName.text.toString()
            if(userName.isNotEmpty()){
                postName()
            }else
                Toast.makeText(applicationContext, "Please fill the missing entry", Toast.LENGTH_LONG).show()
        }//end listener

        btnView.setOnClickListener(){
           getData()
        }
    }
    fun postName(){
        val apIinterface=APIclint().getClient()?.create(APIinterface::class.java)
        apIinterface?.postData(myData.myDataItem("KSA",userName))?.enqueue(object : Callback<myData.myDataItem?> {
            override fun onResponse(call: Call<myData.myDataItem?>, response: Response<myData.myDataItem?>) {
                Toast.makeText(applicationContext, " $userName data is added successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<myData.myDataItem?>, t: Throwable) {
                Toast.makeText(applicationContext, "$t", Toast.LENGTH_LONG).show()
            }
        })
    }//end postName()

    fun getData(){
        val apIinterface=APIclint().getClient()?.create(APIinterface::class.java)
        apIinterface?.getDate()?.enqueue(object : Callback<List<myData.myDataItem>?> {
            override fun onResponse(call: Call<List<myData.myDataItem>?>, response: Response<List<myData.myDataItem>?>) {
                val response=response.body()
                for (item in response!!){
                    namesList.add(item.name)
                }
                rv_main.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<myData.myDataItem>?>, t: Throwable) {
                Toast.makeText(applicationContext, "$t", Toast.LENGTH_LONG).show()
            }
        })
    }//end getData()


}//end class()