package com.example.flatload.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&J:\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\"\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00100\u000fj\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0010`\u0011H&\u00a8\u0006\u0012"}, d2 = {"Lcom/example/flatload/model/DataModel;", "", "getRouteData", "", "origin", "Lcom/mapbox/geojson/Point;", "destination", "context", "Landroid/content/Context;", "postData", "Lio/reactivex/Single;", "", "body", "Lokhttp3/MultipartBody$Part;", "map", "Ljava/util/HashMap;", "Lokhttp3/RequestBody;", "Lkotlin/collections/HashMap;", "app_debug"})
public abstract interface DataModel {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<java.lang.String> postData(@org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part body, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, okhttp3.RequestBody> map);
    
    public abstract void getRouteData(@org.jetbrains.annotations.NotNull()
    com.mapbox.geojson.Point origin, @org.jetbrains.annotations.NotNull()
    com.mapbox.geojson.Point destination, @org.jetbrains.annotations.NotNull()
    android.content.Context context);
}