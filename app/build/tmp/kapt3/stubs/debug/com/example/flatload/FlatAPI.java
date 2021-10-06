package com.example.flatload;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\'J>\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\b\b\u0001\u0010\n\u001a\u00020\u000b2$\b\u0001\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e0\rj\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e`\u000fH\'J\\\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u001a\b\u0001\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u00120\u00042\u0014\b\u0001\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u00122\u0014\b\u0001\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0012H\'\u00a8\u0006\u0016"}, d2 = {"Lcom/example/flatload/FlatAPI;", "", "getJson", "Lretrofit2/Call;", "", "Lcom/example/flatload/ResultGet;", "getResult", "", "postImage", "Lio/reactivex/Single;", "photo", "Lokhttp3/MultipartBody$Part;", "data", "Ljava/util/HashMap;", "Lokhttp3/RequestBody;", "Lkotlin/collections/HashMap;", "postJson", "locations", "Lkotlin/Pair;", "", "start", "destination", "app_debug"})
public abstract interface FlatAPI {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "/android/get")
    public abstract retrofit2.Call<java.util.List<com.example.flatload.ResultGet>> getJson();
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/android/post")
    @retrofit2.http.FormUrlEncoded()
    public abstract retrofit2.Call<java.util.List<com.example.flatload.ResultGet>> postJson(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "locations")
    java.util.List<kotlin.Pair<java.lang.Double, java.lang.Double>> locations, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "start")
    kotlin.Pair<java.lang.Double, java.lang.Double> start, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "destination")
    kotlin.Pair<java.lang.Double, java.lang.Double> destination);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/android/post/upload")
    @retrofit2.http.Multipart()
    public abstract io.reactivex.Single<java.lang.String> postImage(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part photo, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PartMap()
    java.util.HashMap<java.lang.String, okhttp3.RequestBody> data);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "/android/result")
    public abstract retrofit2.Call<java.lang.String> getResult();
}