package com.example.flatload.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J:\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\"\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00130\u0012j\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0013`\u0014H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/flatload/model/DataModelImpl;", "Lcom/example/flatload/model/DataModel;", "server", "Lcom/example/flatload/FlatAPI;", "(Lcom/example/flatload/FlatAPI;)V", "getRouteData", "", "origin", "Lcom/mapbox/geojson/Point;", "destination", "context", "Landroid/content/Context;", "postData", "Lio/reactivex/Single;", "", "body", "Lokhttp3/MultipartBody$Part;", "map", "Ljava/util/HashMap;", "Lokhttp3/RequestBody;", "Lkotlin/collections/HashMap;", "app_debug"})
public final class DataModelImpl implements com.example.flatload.model.DataModel {
    private final com.example.flatload.FlatAPI server = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<java.lang.String> postData(@org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part body, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, okhttp3.RequestBody> map) {
        return null;
    }
    
    @java.lang.Override()
    public void getRouteData(@org.jetbrains.annotations.NotNull()
    com.mapbox.geojson.Point origin, @org.jetbrains.annotations.NotNull()
    com.mapbox.geojson.Point destination, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public DataModelImpl(@org.jetbrains.annotations.NotNull()
    com.example.flatload.FlatAPI server) {
        super();
    }
}