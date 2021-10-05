package com.example.coroutinesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var adviceTextView: TextView
lateinit var adviceButton: Button
var advice: String? = "Hit the button below to get an advice"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adviceTextView = findViewById(R.id.advice_tv)
        adviceButton = findViewById(R.id.advice_btn)
        adviceButton.setOnClickListener {
            CoroutineScope(IO).launch {
                getAdvice()
                withContext(Main) {
                    adviceTextView.text = advice
                }
            }
        }
    }

    private fun getAdvice()  {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<AdviceData?>? = apiInterface!!.doGetListResources()
        call?.enqueue(object : Callback<AdviceData?> {
            override fun onResponse(call: Call<AdviceData?>?, response: Response<AdviceData?>) {
                val adviceData: AdviceData? = response.body()
                advice = adviceData?.slip?.advice
            }
            override fun onFailure(call: Call<AdviceData?>, t: Throwable) {
                call.cancel()
            }
        })
    }
}