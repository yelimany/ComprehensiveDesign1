package com.example.flatload.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007J\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u000bJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u000bJ\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u000bH\u0002J\u001b\u0010$\u001a\u00020\t2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0&H\u0002\u00a2\u0006\u0002\u0010\'J\u0010\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\u000bH\u0007J\u001a\u0010*\u001a\u0004\u0018\u00010\u000b2\u0006\u0010+\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020-H\u0002J\u0006\u0010.\u001a\u00020\u000bJ2\u0010/\u001a\u00020\u001b2\u0006\u00100\u001a\u0002012\"\u00102\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020403j\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u000204`5J\u0012\u00106\u001a\u0004\u0018\u00010\t2\u0006\u0010,\u001a\u00020-H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0011R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0011\u00a8\u00067"}, d2 = {"Lcom/example/flatload/viewmodel/CameraViewModel;", "Lcom/example/flatload/base/BaseViewModel;", "model", "Lcom/example/flatload/model/DataModel;", "(Lcom/example/flatload/model/DataModel;)V", "_imageviewLiveData", "Landroidx/lifecycle/MutableLiveData;", "Landroid/net/Uri;", "_metaDataLiveData", "Lcom/google/android/gms/maps/model/LatLng;", "_photolocationLiveData", "", "_toastRegistrationLiveData", "_typeLiveData", "imageviewLiveData", "Landroidx/lifecycle/LiveData;", "getImageviewLiveData", "()Landroidx/lifecycle/LiveData;", "metaDataLiveData", "getMetaDataLiveData", "photolocationLiveData", "getPhotolocationLiveData", "toastRegistrationLiveData", "getToastRegistrationLiveData", "typeLiveData", "getTypeLiveData", "changeImageView", "", "uri", "changePhotoLocation", "location", "changeType", "type", "convertToDegree", "", "stringDMS", "getLatLng", "gpsValue", "", "([Ljava/lang/String;)Lcom/google/android/gms/maps/model/LatLng;", "getMetaData", "absolutepath", "getTagString", "tag", "exif", "Landroid/media/ExifInterface;", "getThreadName", "postImageData", "body", "Lokhttp3/MultipartBody$Part;", "map", "Ljava/util/HashMap;", "Lokhttp3/RequestBody;", "Lkotlin/collections/HashMap;", "showExif", "app_debug"})
public final class CameraViewModel extends com.example.flatload.base.BaseViewModel {
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _typeLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _photolocationLiveData = null;
    private final androidx.lifecycle.MutableLiveData<android.net.Uri> _imageviewLiveData = null;
    private androidx.lifecycle.MutableLiveData<java.lang.String> _toastRegistrationLiveData;
    private androidx.lifecycle.MutableLiveData<com.google.android.gms.maps.model.LatLng> _metaDataLiveData;
    private final com.example.flatload.model.DataModel model = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getPhotolocationLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getTypeLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<android.net.Uri> getImageviewLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getToastRegistrationLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.google.android.gms.maps.model.LatLng> getMetaDataLiveData() {
        return null;
    }
    
    public final void changeType(@org.jetbrains.annotations.NotNull()
    java.lang.String type) {
    }
    
    public final void changePhotoLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String location) {
    }
    
    public final void changeImageView(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThreadName() {
        return null;
    }
    
    public final void postImageData(@org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part body, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, okhttp3.RequestBody> map) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.Q)
    public final void getMetaData(@org.jetbrains.annotations.NotNull()
    java.lang.String absolutepath) {
    }
    
    private final java.lang.String getTagString(java.lang.String tag, android.media.ExifInterface exif) {
        return null;
    }
    
    private final com.google.android.gms.maps.model.LatLng showExif(android.media.ExifInterface exif) {
        return null;
    }
    
    private final com.google.android.gms.maps.model.LatLng getLatLng(java.lang.String[] gpsValue) {
        return null;
    }
    
    private final double convertToDegree(java.lang.String stringDMS) {
        return 0.0;
    }
    
    public CameraViewModel(@org.jetbrains.annotations.NotNull()
    com.example.flatload.model.DataModel model) {
        super();
    }
}