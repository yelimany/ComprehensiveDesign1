package com.example.flatload

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.gson.GsonBuilder
import com.mapbox.api.directions.v5.DirectionsCriteria
import com.mapbox.api.directions.v5.MapboxDirections
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.geocoding.v5.GeocodingCriteria
import com.mapbox.api.geocoding.v5.MapboxGeocoding
import com.mapbox.api.geocoding.v5.models.GeocodingResponse
import com.mapbox.geojson.Point
import kotlinx.android.synthetic.main.activity_input_way.*
import kotlinx.android.synthetic.main.activity_input_way.textviewJSONText
import kotlinx.android.synthetic.main.fragment_input_way.*
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

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

    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.MINUTES)
        .build();

    val BASE_URL_FLAT_API ="http://10.0.2.2:8080" //"http://15.164.166.74:8080"(민영) //"http://10.0.2.2:3000"(에뮬레이터-로컬서버 통신)
    val gson = GsonBuilder().setLenient().create()
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_FLAT_API).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
    //.addConverterFactory(ScalarsConverterFactory.create())
    //.build()
    val api = retrofit.create(FlatAPI::class.java)

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
        //처음에 gps 허용
        //내위치 누르면 현재 위치로 설정
        //확인 버튼 누르면 출발지, 도착지 -> 위도 경도로 변경
        val mgeocorder: Geocoder = Geocoder(requireContext(), Locale.getDefault())
        initLocation()
        //내 위치 버튼
        button_inputway_now.setOnClickListener { view ->
            //loc.latitude,loc.longitude <- 현재 위치 위도 경도
            //위도 경도-> 텍스트 변경해서 출발지 edittext에 표시
            val txtLoc = mgeocorder.getFromLocation(loc.latitude,loc.longitude,1)[0]
            if(txtLoc.getAddressLine(0)!=null){
                editTextStart.setText(txtLoc.getAddressLine(0))
            }
            Log.i("my location", txtLoc.toString())
        }
        //확인 버튼
        button_inputway_yes.setOnClickListener { view ->
            PointList = ArrayList<Point>()
            pairList.clear()
            PointList.clear()
            val start = editText_inputway_start.text.toString()
            val end = editText_inputway_end.text.toString()
            if(start.isNotEmpty() && end.isNotEmpty()) {
                startGeocoding(start)
                endGeocoding(end)
                //Log.d("포인트리스트 확인",PointList.toString())
            }else{
                Toast.makeText(requireContext(),"위치를 입력해주세요", Toast.LENGTH_LONG).show()
                editTextStart.text.clear()
                editTextEnd.text.clear()
            }
        }
        // 취소 버튼
        button_inputway_no.setOnClickListener { view ->
            //startPoint endPoint pairList PointList 초기화
            PointList.clear()
            pairList.clear()
            editTextStart.text.clear()
            editTextEnd.text.clear()
            textviewJSONText.setText(" ")
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
    // 서버로 좌표 전송
    @SuppressLint("LogNotTimber")
    private fun sendToServer(pairList: List<Pair<Double, Double>>) {
        var LocList = mutableListOf<Location>()
        Log.d("pairList 확인:",pairList.toString() )

        var newpairList = mutableListOf<Pair<Double,Double>>()

        for (i in 0..pairList.size-1){
            val pair = pairList[i]
            val first = pairList[i].first
            val second = pairList[i].second
            newpairList.add(Pair(second,first))
        }
        Log.d("newpairList 확인:",newpairList.toString())

        val callPostJson = api.postJson(newpairList)
//        val thread = Thread.sleep(10000)
//        thread.run {
//
//        }
        callPostJson.enqueue(object : Callback<List<ResultGet>> {
            override fun onFailure(call: Call<List<ResultGet>>, t: Throwable) {
                Log.d("결과:", "실패 : $t")
                Toast.makeText(requireContext(),"실패: $t",Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(
                call: Call<List<ResultGet>>,
                response: Response<List<ResultGet>>
            ) {
                Log.d("결과", "성공 : ${response.raw()}")
                Log.d("출력", "성공 :" + response?.body().toString())
                //resultMsgFromServer(response?.body().toString())
                Toast.makeText(requireContext(),response?.body().toString(),Toast.LENGTH_SHORT).show()
                //makeToast(response?.body().toString())
//                if(!response?.body().toString().contains("null")){ // 위험요소가 없으면
//                    //saveResultGet(response?.body())
//                }
            }
        })
    }
    var fusedLocationClient: FusedLocationProviderClient?= null
    var loc= LatLng(0.0,0.0)
    var locationCallback: LocationCallback?=null
    var locationRequest: LocationRequest?=null

    var pairList = mutableListOf<Pair<Double,Double>>()
    lateinit var PointList: ArrayList<Point>

    lateinit var startPoint: Point
    lateinit var endPoint: Point

    private fun init() {
        //처음에 gps 허용
        //내위치 누르면 현재 위치로 설정
        //확인 버튼 누르면 출발지, 도착지 -> 위도 경도로 변경
        val mgeocorder: Geocoder = Geocoder(requireContext(), Locale.getDefault())
        initLocation() //gps 설정

        button_inputway_now.setOnClickListener { //내 위치 버튼
            //loc.latitude,loc.longitude <- 현재 위치 위도 경도
            //위도 경도-> 텍스트 변경해서 출발지 edittext에 표시
            val txtLoc = mgeocorder.getFromLocation(loc.latitude,loc.longitude,1)[0]
            if(txtLoc.getAddressLine(0)!=null){
                editTextStart.setText(txtLoc.getAddressLine(0))
            }
            Log.i("my location", txtLoc.toString())
            //val txtloc = ReverseGeocoding(loc.longitude,loc.latitude)
        }
        button_inputway_yes.setOnClickListener { //확인 버튼
            PointList = ArrayList<Point>()
            pairList.clear()
            PointList.clear()

            val start = editTextStart.text.toString()
            val end = editTextEnd.text.toString()

            if(start.isNotEmpty() && end.isNotEmpty()) {
                startGeocoding(start)
                endGeocoding(end)
                //Log.d("포인트리스트 확인",PointList.toString())
            }else{
                Toast.makeText(requireContext(),"위치를 입력해주세요", Toast.LENGTH_LONG).show()
                editTextStart.text.clear()
                editTextEnd.text.clear()
            }
        }
        button_inputway_no.setOnClickListener { // 취소 버튼
            //startPoint endPoint pairList PointList 초기화
            PointList.clear()
            pairList.clear()
            editTextStart.text.clear()
            editTextEnd.text.clear()
            textviewJSONText.setText(" ")
        }
    }

    private fun savePointToList(point: Point){
        PointList.add(point)
        if(PointList.size == 2){
            textviewJSONText.setText(" ")
            getRoute(PointList[0], PointList[1])
        }
    }

    private fun saveStartPoint(point: Point){
        startPoint = point
    }

    private fun saveEndPoint(point: Point){
        endPoint = point
    }


    private fun startGeocoding(strlocation: String) {
        val mapboxGeocoding = MapboxGeocoding.builder()
            .accessToken(getString(R.string.access_token))
            .query(strlocation)
            .build()
        mapboxGeocoding.enqueueCall(object : Callback<GeocodingResponse> {
            override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
                val results = response.body()!!.features()
                if (results.size > 0) {
                    val firstResultPoint = results[0].center()
                    Log.d("geocoding확인", "onResponse: " + firstResultPoint!!.toString())
                    //results[0].center()?.let { saveStartPoint(it) }
                    saveStartPoint(firstResultPoint)
                    results[0].center()?.let { savePointToList(it) }
                    Log.d("포인트리스트 확인 in enqueue",PointList.toString())

                } else {
                    Log.d("geocoding확인", "onResponse: No result found")
                }
            }
            override fun onFailure(call: Call<GeocodingResponse>, throwable: Throwable) {
                throwable.printStackTrace()
            }
        })

    }

    private fun endGeocoding(strlocation: String) {
        val mapboxGeocoding = MapboxGeocoding.builder()
            .accessToken(getString(R.string.access_token))
            .query(strlocation)
            .build()
        mapboxGeocoding.enqueueCall(object : Callback<GeocodingResponse> {
            override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
                val results = response.body()!!.features()
                if (results.size > 0) {
                    val firstResultPoint = results[0].center()
                    Log.d("geocoding확인", "onResponse: " + firstResultPoint!!.toString())
                    //results[0].center()?.let { saveStartPoint(it) }
                    saveEndPoint(firstResultPoint)
                    results[0].center()?.let { savePointToList(it) }
                    Log.d("포인트리스트 확인 in enqueue",PointList.toString())
                } else {
                    Log.d("geocoding확인", "onResponse: No result found")
                }
            }
            override fun onFailure(call: Call<GeocodingResponse>, throwable: Throwable) {
                throwable.printStackTrace()
            }
        })
    }


    private fun ReverseGeocoding(longitude: Double, latitude: Double) {
        val mapboxGeocoding = MapboxGeocoding.builder()
            .accessToken(getString(R.string.access_token)).country("korea")
            .query(Point.fromLngLat(longitude,latitude))
            .geocodingTypes(GeocodingCriteria.TYPE_ADDRESS).build()

        mapboxGeocoding.enqueueCall(object : Callback<GeocodingResponse> {
            override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
                val results = response.body()!!.features()
                if (results.size > 0) {
                    // Log the first results Point.
                    val firstResultPoint = results[0]
                    Log.d("reverse geocoding확인", "onResponse: " + firstResultPoint!!.toString())

                } else {
                    // No result for your request were found.
                    Log.d("reverse geocoding확인", "onResponse: No result found")
                }
            }
            override fun onFailure(call: Call<GeocodingResponse>, throwable: Throwable) {
                throwable.printStackTrace()
            }
        })
    }

    private fun getRoute(origin: Point, destination: Point) {
        //변수 선언
        //var pairList = mutableListOf<Pair<Double,Double>>() //전역변수로 변경함
        var flag=0

        //맵박스 길찾기 요청
        val client = MapboxDirections.builder() //builder 패턴 방식으로 MapboxDirections 클래스의 객체룰 생성. 변수의 순서 바뀌면 안됨
            .origin(origin) //출발지
            .destination(destination) //목적지
            .overview(DirectionsCriteria.OVERVIEW_FULL)
            .profile(DirectionsCriteria.PROFILE_WALKING) //교통, 운전, 걷기, 사이클링
            .steps(true)
            //.geometries("geojson")
            .accessToken(getString(R.string.access_token))
            .build()

        //val response = client.executeCall().body()
        //Log.i("response", response.toString())

        pairList.clear()

        Log.d("MYTEST",origin.toString())
        Log.d("MYTEST",destination.toString())
        //길찾기 응답
        client?.enqueueCall(object : Callback<DirectionsResponse> {
            @SuppressLint("LogNotTimber")
            override fun onResponse(call: Call<DirectionsResponse>, response: Response<DirectionsResponse>) {
                if (response.body() == null) {
                    Log.i("error", "No routes found, make sure you set the right user and access token.")
                    return
                } else if (response.body()!!.routes().size < 1) {
                    Log.i("error", "No routes found")
                    return
                }
                // Get the directions route
                val currentRoute = response.body()!!.routes()[0]
                //textviewJSONText?.setText(response.body()!!.toJson())
                val jsonString = response.body()!!.toJson().trimIndent()//json 형식으로 바꿔서 string에 저장
                val jsonObject = JSONObject(jsonString)
                val jsonArray = jsonObject.getJSONArray("routes")
                val subjsonObject = jsonArray.getJSONObject(0) //route 배열의 index = 0

                // 이동거리, 소요시간 추가
                val duration = subjsonObject.getString("duration") // 초 단위
                val distance = subjsonObject.getString("distance") // m 단위

                // 단위 변경
                val duration_min = (duration.toFloat()/60.0 * 100).roundToInt() / 100f
                val distance_km = (distance.toFloat()/1000.0 * 100).roundToInt() / 100f


                val subjsonArray = subjsonObject.getJSONArray("legs")
                val subjsonObject2 = subjsonArray.getJSONObject(0)//legs 배열의 index = 0
                val subjsonArray2 = subjsonObject2.getJSONArray("steps")

                var cnt:Int = 0

                //json 파싱 intersection
                for( i in 0..subjsonArray2.length()-1){ //step배열의 index 0 부터 끝까지
                    val iObject = subjsonArray2.getJSONObject(i) //index i 의 값을 객체로 생성
                    val iArray =iObject.getJSONArray("intersections") //intersection 배열

                    for (j in 0..iArray.length()-1){
                        val jObject =iArray.getJSONObject(j)
                        val location =jObject.getJSONArray("location") //intersection 배열의 location 값을 얻어옴

                        println("${i+1}번째 intersections ${j+1}번째 location"+location)

                        val pair = Pair(location[0].toString().toDouble(), location[1].toString().toDouble())
                        pairList.add(cnt, pair)
                        cnt = cnt + 1
                    }
                }
                Log.i("이동거리,소요시간 출력", distance_km.toString() +"km, "+duration_min.toString()+"분")
                textviewJSONText?.setText(pairList.toString()) //textview로 띄움
                flag=1
                val result = checkDistance(distance_km)
                if(result == 1){
                    sendToServer(pairList)
                    //goToMap(pairList) //
                }
                //goToMap(pairList)
            }
            override fun onFailure(call: Call<DirectionsResponse>, throwable: Throwable) {
                Log.i("error", "Error: " + throwable.message)
            }
        }
        )
    }

    private fun checkDistance(distanceKm: Float): Int {
        if(distanceKm >= 3.00){
            Toast.makeText(requireContext(),"해당 서비스는 3km 이내의 도보 길찾기 경로만 제공 합니다.", Toast.LENGTH_LONG).show()
            textviewJSONText.setText(" ")
            editTextStart.text.clear()
            editTextEnd.text.clear()
            return 0
        }
        else{
            return 1
        }
    }

    private fun goToMap(pairList: List<Pair<Double, Double>>){
        if(pairList.isNotEmpty()) {
            val i = Intent(requireActivity(), MapActivity::class.java)
            i.putExtra("pairList", PairList(pairList))
            //i.putExtra("resultGet",ResultGetList(resultGet))
            //i.putExtra("startPoint",startPoint.toString())
            i.putExtra("start",PointIntent(startPoint))
            i.putExtra("end",PointIntent(endPoint))
            startActivity(i)
        }
    }

    private fun startLocationUpdates() { //gps 관련
        locationRequest = LocationRequest.create()?.apply {
            interval= 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locationCallback = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                //성공적으로 위치정보 업데이트 되었으면? 그 위치 정보 가져옴
                locationResult ?: return
                for(location in locationResult.locations){
                    loc= LatLng(location.latitude,location.longitude)
                    Log.i("changeLocation",loc.toString())
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient?.requestLocationUpdates(
            locationRequest,
            locationCallback, //갱신되면 이함수 호출
            Looper.getMainLooper()) //메인쓰레드가 가지고있는 루퍼 객체 사용하겠다*/
    }

    private fun initLocation() {
        if(ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        )
        {
            getuserlocation() //현재위치 갱신
            startLocationUpdates() //업데이트
        }
        else{
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),100)
            //처음엔 권한 요청함
        }
    }

    private fun getuserlocation() {
        fusedLocationClient= LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient?.lastLocation?.addOnSuccessListener {//성공적으로 위치 가져왔으면?
            loc = LatLng(it.latitude,it.longitude) //현재위치로 위치정보를 바꾸겠다
            Log.i("currentLocation",loc.toString())
        }
    }

    override fun onRequestPermissionsResult( //권한요청하고 결과 여기로 옴
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==100){ //허용받았으면
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED){ //둘다 허용되면
                getuserlocation()
                startLocationUpdates()
            }
            else{//허용안해줬으면 기본 맵으로
                Toast.makeText(requireContext(),"위치정보 제공을 하셔야 합니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}