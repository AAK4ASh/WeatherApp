package com.main.weatherapp
    import android.annotation.SuppressLint
    import android.os.Bundle
    import android.view.View
    import android.view.Window
    import androidx.appcompat.app.AppCompatActivity
    import com.main.weatherapp.api.RetrofitInstance
    import com.main.weatherapp.api.WeatherAPI
    import com.main.weatherapp.api.models.ApiResponse
    import com.main.weatherapp.databinding.ActivityMainBinding
    import com.main.weatherapp.utiles.Constants
    import retrofit2.Call
    import retrofit2.Callback
    import retrofit2.Response
    import java.io.IOException
    import java.util.*

class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
            binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            callAPI()
            greetings()

        }
        private fun callAPI() {
            val retrofitInstance = RetrofitInstance.getInstance()
            val weatherAPI = retrofitInstance.create(WeatherAPI::class.java)
            weatherAPI.getWeatherInfo(Constants.API_KEY,"Trivandrum","yes")
                .enqueue(object : Callback<ApiResponse>{
                    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                        if (response.isSuccessful){
                            binding.loading.visibility= View.GONE
                            val responseBody= response.body()
                            updateUI(responseBody)

                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        if (t is IOException) {
                            println("Network error occurred: ${t.message}")
                        } else {
                            println("Error occurred: ${t.message}")
                        }
                    }
                })
        }
    private fun greetings(){
    val calender= Calendar.getInstance()

        when (calender.get(Calendar.HOUR_OF_DAY)) {
            in 6..11 -> binding.greetings.text = getString(R.string.morning)
            in 12..16 -> binding.greetings.text = getString(R.string.noon)
            in 17..20 -> binding.greetings.text = getString(R.string.eve)
            else -> binding.greetings.text = getString(R.string.night)
        }
}

        @SuppressLint("SetTextI18n")
        private fun updateUI(responseBody:ApiResponse?){
            responseBody?.let { response ->
                binding.apply {
                    temp.text= "${response.current.temp_c}°C"
                    location.text= response.location.name
                    condition.text= response.current.condition.text
                    time.text=response.location.localtime
                }
            }
        }
    }
