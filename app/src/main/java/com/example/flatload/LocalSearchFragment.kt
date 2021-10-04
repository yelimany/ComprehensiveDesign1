package com.example.flatload

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_add_risk.*
import kotlinx.android.synthetic.main.fragment_local_search.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import kotlin.collections.HashMap

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RocalSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RocalSearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var itemList = mutableListOf<ItemList>()
//    data class ItemList (val title:String, val category:String,
//                         val address: String, val roadAddress:String,
//                         val mapx:String,val mapy:String):Serializable{}

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
        val view = inflater.inflate(R.layout.fragment_local_search, container, false)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RocalSearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RocalSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_local_yes.setOnClickListener { view->
            if (editText_local_search.length() != 0){
                getRocal(editText_local_search.getText().toString())
            }else{
                Toast.makeText(requireContext(),"값을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
        val localAdapter = ListViewAdapter(itemList)
        listView.adapter = localAdapter
        listView.setOnItemClickListener {
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long ->
            val item = parent.getItemAtPosition(position) as ItemList
            Toast.makeText(requireContext(), item.title, Toast.LENGTH_SHORT).show() }
    }

    private fun getRocal(search:String){
        testRetrofit(search)
    }
    private fun testRetrofit(search:String){
//        var query : RequestBody = RequestBody.create(MediaType.parse("text/plain"),search)
//        var display : RequestBody = RequestBody.create(MediaType.parse("text/plain"),"5")
//        var map: HashMap<String, RequestBody> = HashMap<String, RequestBody>()
//        map.put("query",query)
//        map.put("display",display)
        var map: HashMap<String, String> = HashMap<String, String>()
        map.put("query",search)
        map.put("display","5")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/v1/") //"http://112.148.189.103:8080/"
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
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
                        //val item = Json.parse(ItemList.serializer(),jsonObject2)
                        //val data = Gson().fromJson(jsonObject2, ItemList::class.java)
                        var data = ItemList(jsonObject2.getString("title"),
                            jsonObject2.getString("category"),
                            jsonObject2.getString("address"),
                            jsonObject2.getString("roadAddress"),
                            jsonObject2.getString("mapx"),
                            jsonObject2.getString("mapy"))
                        Log.d("MYTEST",jsonObject2.toString())
                    }
                    Log.d("MYTEST","onResponse"+response?.body().toString())
                }else{
                    Toast.makeText(requireContext(), "주소 검색에 실패했습니다", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("MYTEST", t.message)
                Toast.makeText(requireContext(), "주소 검색에 실패했습니다.", Toast.LENGTH_SHORT).show()
                //finish()
            }
        })
    }

}