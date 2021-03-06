package com.example.flatload

//import kotlinx.android.synthetic.main.activity_input_way.textviewJSONText

import android.Manifest
import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.gson.GsonBuilder
import com.mapbox.api.directions.v5.models.RouteOptions
import com.naver.maps.geometry.Tm128

import kotlinx.android.synthetic.main.fragment_input_way.*

import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InputWayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputWayFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val sharedViewModel:SharedViewModel by activityViewModels()
//    val okHttpClient = OkHttpClient.Builder()
//        .readTimeout(15, TimeUnit.MINUTES)
//        .build();
//    val BASE_URL_FLAT_API ="http://10.0.2.2:3000" //"http://15.164.166.74:8080"(??????) //"http://10.0.2.2:3000"(???????????????-???????????? ??????)
   val gson = GsonBuilder().setLenient().create()
//    val retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL_FLAT_API).client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create(gson)).build()
    //.addConverterFactory(ScalarsConverterFactory.create())
    //.build()
    //val api = retrofit.create(FlatAPI::class.java)

    lateinit var origin: LatLng
    lateinit var destination: LatLng
    lateinit var routeOption: String

    var itemList = mutableListOf<ItemList>()
    //var latlngList = mutableListOf<LatLng>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_input_way, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //????????? gps ??????
        //????????? ????????? ?????? ????????? ??????
        //?????? ?????? ????????? ?????????, ????????? -> ?????? ????????? ??????
        val mgeocorder: Geocoder = Geocoder(requireContext(), Locale.getDefault())
        val items = resources.getStringArray(R.array.route_type)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)
        val inputMethodManager = getContext()?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        //initLocation()
        //??? ?????? ??????
        button_inputway_now.setOnClickListener { view ->
            //loc.latitude,loc.longitude <- ?????? ?????? ?????? ??????
            //?????? ??????-> ????????? ???????????? ????????? edittext??? ??????
            val txtLoc = mgeocorder.getFromLocation((activity as MainActivity).loc.latitude,(activity as MainActivity).loc.longitude,1)[0]
            origin = LatLng((activity as MainActivity).loc.latitude,(activity as MainActivity).loc.longitude)
            if(txtLoc.getAddressLine(0)!=null){
                editText_inputway_start.setText(txtLoc.getAddressLine(0))
            }
            Log.i("my location", txtLoc.toString())
        }
        //?????? ??????
        button_inputway_yes.setOnClickListener { view ->
            if(TextUtils.isEmpty(editText_inputway_start.text.toString()) &&TextUtils.isEmpty(editText_inputway_end.text.toString())) {
                Toast.makeText(requireContext(),"????????? ??????????????????", Toast.LENGTH_LONG).show()
                editText_inputway_start.text.clear()
                editText_inputway_end.text.clear()
            }else{
                Log.d("????????? ??????",editText_inputway_start.text.toString())
                Log.d("????????? ??????",editText_inputway_end.text.toString())
                if (::origin.isInitialized && ::destination.isInitialized) {
                    listView.clearChoices()
                    sendToServerLatLng(origin, destination, routeOption)
                }else{
                    Toast.makeText(requireContext(),"", Toast.LENGTH_LONG).show()
                }
            }
        }
        // ?????? ??????
        button_inputway_no.setOnClickListener { view ->
            editText_inputway_start.text.clear()
            editText_inputway_end.text.clear()
        }
        //????????? ???????????? ??????
        imageButton1.setOnClickListener{view ->
            var x = editText_inputway_start.getText()
            if (TextUtils.isEmpty(x.toString())) { ///////////////////
                Toast.makeText(requireContext(),"???????????? ?????? ????????????",Toast.LENGTH_SHORT).show()
            } else{
                getRocal(editText_inputway_start.getText().toString())
            }
            inputMethodManager.hideSoftInputFromWindow(imageButton1.windowToken, 0)
        }
        //????????? ???????????? ??????
        imageButton2.setOnClickListener{view->
            var x = editText_inputway_start.getText()
            if (TextUtils.isEmpty(x.toString())) {
                Toast.makeText(requireContext(),"???????????? ?????? ????????????.",Toast.LENGTH_SHORT).show()
            }else{
                getRocal(editText_inputway_end.getText().toString())
            }
            inputMethodManager.hideSoftInputFromWindow(imageButton2.windowToken, 0)
        }
        //?????? ?????? ????????????
        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            var tm128 : Tm128
            val item = parent.getItemAtPosition(position) as ItemList
            if (editText_inputway_start.isFocused == true){
                editText_inputway_start.setText(if (item.roadAddress.length == 0) item.address else item.roadAddress)
                tm128 = Tm128(item.mapx.toDouble(),item.mapy.toDouble())
                origin = LatLng(tm128.toLatLng().latitude,tm128.toLatLng().longitude)
            }else if (editText_inputway_end.isFocused == true){
                editText_inputway_end.setText(if (item.roadAddress.length == 0) item.address else item.roadAddress)
                tm128 = Tm128(item.mapx.toDouble(),item.mapy.toDouble())
                destination = LatLng(tm128.toLatLng().latitude,tm128.toLatLng().longitude)
            }
            //listView.setVisibility(View.INVISIBLE)
        }
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position) {
                    0 -> {
                        routeOption = "0"   //??????(????????????)
                    }
                    1 -> {
                        routeOption = "4"   //????????????
                    }
                    2 -> {
                        routeOption = "10"   //????????????
                    }
                    3 -> {
                        routeOption = "30"   //????????????
                    }
                    else -> {
                        routeOption = "0"
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InputWayFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InputWayFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    // ????????? ?????? ??????
    private fun sendToServerLatLng(startLatLng: LatLng, destLatLng: LatLng, routeOption:String){
        //val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),"'"+jsonStartDest.toString()+"'")
        // MediaType.parse("application/json; charset=utf-8");
        var startString = startLatLng.latitude.toString()+","+startLatLng.longitude.toString()
        var destString = destLatLng.latitude.toString()+","+destLatLng.longitude.toString()

        val api2 = Retrofit.Builder()
            .baseUrl("http://192.168.219.107:8080/") //"http://192.168.219.107:8080/" http://10.0.2.2:8080 http://192.168.219.107:8080/
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val client = api2.create(FlatAPI::class.java)
        client.postPoint(startString,destString,routeOption).enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {
            if (response?.isSuccessful){
                Toast.makeText(requireContext(), "?????? ????????? ??????????????????", Toast.LENGTH_SHORT).show()
                Log.d("??????","onResponse"+response?.body().toString())
                var result = response?.body().toString()
                var route = JSONArray(result)
                val distance = route.getJSONObject(0).getJSONObject("properties").getString("totalDistance").toFloat()/1000.0f
                if (checkDistance(distance) == 1){
                    sharedViewModel.changeRoute(result)
                    (activity as MainActivity?)?.setFragment(MapFragment(),"1")
                }
            }else{
                //Toast.makeText(requireContext(), "onResponse ", Toast.LENGTH_SHORT).show()
            }
        }
        override fun onFailure(call: Call<String>, t: Throwable) {
            Log.d("??????", t.message)
            Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            //finish()
        }
    })
    }

//    var fusedLocationClient: FusedLocationProviderClient?= null
//    var loc= LatLng(0.0,0.0)
//    var locationCallback: LocationCallback?=null
//    var locationRequest: LocationRequest?=null

    private fun getRocal(search:String){
        rocalSearchRetrofit(search)
    }
    private fun rocalSearchRetrofit(search:String){
        var map: HashMap<String, String> = HashMap<String, String>()
        map.put("query",search)
        map.put("display","5")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/v1/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        itemList.clear()
        val re = "<b>|</b>".toRegex()
        val client = retrofit.create(FlatAPI::class.java)
        client.getSearchRocal(getString(R.string.client_id_naver_rocal),
            getString(R.string.access_token_naver_rocal),
            map).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response?.isSuccessful){
                    val jsonObject = JSONObject(response.body()?.trimIndent())
                    val jsonArray = jsonObject.getJSONArray("items")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject2 = jsonArray.getJSONObject(i)
                        var x = jsonObject2.getString("category").split('>')
                        var y = if (x.size > 1) x.get(1) else x.get(0)
                        itemList.add(ItemList(jsonObject2.getString("title").replace(re,""), y,
                            jsonObject2.getString("address"),
                            jsonObject2.getString("roadAddress"),
                            jsonObject2.getString("mapx"),
                            jsonObject2.getString("mapy"))
                        )
                    }
                    Log.d("MYTEST","onResponse"+"success")
                    //Log.d("MYTEST","onResponse"+response?.body().toString())
                    //Log.d("MYTEST",itemList.toString())
                    listView.adapter = ListViewAdapter(itemList)
                }else{
                    Toast.makeText(requireContext(), "?????? ????????? ??????????????????", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("MYTEST", t.message)
                Toast.makeText(requireContext(), "?????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show()
                //finish()
            }
        })
    }

    private fun checkDistance(distanceKm: Float):Int{
        if(distanceKm >= 3.00){
            Toast.makeText(requireContext(),"?????? ???????????? 3km ????????? ?????? ????????? ????????? ?????? ?????????.", Toast.LENGTH_LONG).show()
            //textviewJSONText.setText(" ")
            editText_inputway_start.text.clear()
            editText_inputway_end.text.clear()
            return 0
        }
        return 1
    }

//    private fun startLocationUpdates() { //gps ??????
//        locationRequest = LocationRequest.create()?.apply {
//            interval= 10000
//            fastestInterval = 5000
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        }
//        locationCallback = object : LocationCallback(){
//            override fun onLocationResult(locationResult: LocationResult?) {
//                //??????????????? ???????????? ???????????? ????????????? ??? ?????? ?????? ?????????
//                locationResult ?: return
//                for(location in locationResult.locations){
//                    loc= LatLng(location.latitude,location.longitude)
//                    Log.i("changeLocation",loc.toString())
//                }
//            }
//        }
//
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        fusedLocationClient?.requestLocationUpdates(
//            locationRequest,
//            locationCallback, //???????????? ????????? ??????
//            Looper.getMainLooper()) //?????????????????? ??????????????? ?????? ?????? ???????????????*/
//    }
//
//    private fun initLocation() {
//        if(ActivityCompat.checkSelfPermission(requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//            ActivityCompat.checkSelfPermission(requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
//        )
//        {
//            getuserlocation() //???????????? ??????
//            startLocationUpdates() //????????????
//        }
//        else{
//            ActivityCompat.requestPermissions(requireActivity(),
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),100)
//            //????????? ?????? ?????????
//        }
//    }
//
//    private fun getuserlocation() {
//        fusedLocationClient= LocationServices.getFusedLocationProviderClient(requireActivity())
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        val temp = fusedLocationClient
//        if(temp != null){
//            temp.lastLocation
//                .addOnSuccessListener {//??????????????? ?????? ????????????????
//                    if (it == null) {
//                        Log.i("?????? ???????????? ??????", "")    //?????? ????????? ?????? ????????? ??? ?????? ??? ????????????
//                    } else {
//                        loc = LatLng(it.latitude, it.longitude)  //??????????????? ??????????????? ????????????
//                        Log.i("currentLocation", loc.toString())
//                    }
//                }
//                .addOnFailureListener{
//                    Log.i("location error","")          //
//                }
//        }
////        fusedLocationClient?.lastLocation?.addOnSuccessListener {//??????????????? ?????? ????????????????
////            loc = LatLng(it.latitude,it.longitude) //??????????????? ??????????????? ????????????
////            Log.i("currentLocation",loc.toString())
////        }
//    }

//    override fun onRequestPermissionsResult( //?????????????????? ?????? ????????? ???
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if(requestCode==100){ //??????????????????
//            if(grantResults[0]== PackageManager.PERMISSION_GRANTED &&
//                grantResults[1] == PackageManager.PERMISSION_GRANTED){ //?????? ????????????
//                getuserlocation()
//                startLocationUpdates()
//            }
//            else{//????????????????????? ?????? ?????????
//                Toast.makeText(requireContext(),"???????????? ????????? ????????? ?????????", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}