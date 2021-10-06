package com.example.flatload.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020/H&J\b\u00101\u001a\u00020/H&J\b\u00102\u001a\u00020/H\u0016J\b\u00103\u001a\u00020/H&J\u0012\u00104\u001a\u00020/2\b\u00105\u001a\u0004\u0018\u000106H\u0014J-\u00107\u001a\u00020/2\u0006\u00108\u001a\u00020\u00102\u000e\u00109\u001a\n\u0012\u0006\b\u0001\u0012\u00020;0:2\u0006\u0010<\u001a\u00020=H\u0016\u00a2\u0006\u0002\u0010>J\b\u0010?\u001a\u00020/H\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\u0010X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u00028\u0000X\u0086.\u00a2\u0006\u0010\n\u0002\u0010*\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)R\u0012\u0010+\u001a\u00028\u0001X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010-\u00a8\u0006@"}, d2 = {"Lcom/example/flatload/base/BaseActivity;", "T", "Landroidx/databinding/ViewDataBinding;", "R", "Lcom/example/flatload/base/BaseViewModel;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "getFusedLocationClient", "()Lcom/google/android/gms/location/FusedLocationProviderClient;", "setFusedLocationClient", "(Lcom/google/android/gms/location/FusedLocationProviderClient;)V", "isSetBackButtonValid", "", "layoutResourceId", "", "getLayoutResourceId", "()I", "loc", "Lcom/google/android/gms/maps/model/LatLng;", "getLoc", "()Lcom/google/android/gms/maps/model/LatLng;", "setLoc", "(Lcom/google/android/gms/maps/model/LatLng;)V", "locationCallback", "Lcom/google/android/gms/location/LocationCallback;", "getLocationCallback", "()Lcom/google/android/gms/location/LocationCallback;", "setLocationCallback", "(Lcom/google/android/gms/location/LocationCallback;)V", "locationRequest", "Lcom/google/android/gms/location/LocationRequest;", "getLocationRequest", "()Lcom/google/android/gms/location/LocationRequest;", "setLocationRequest", "(Lcom/google/android/gms/location/LocationRequest;)V", "viewDataBinding", "getViewDataBinding", "()Landroidx/databinding/ViewDataBinding;", "setViewDataBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "viewModel", "getViewModel", "()Lcom/example/flatload/base/BaseViewModel;", "getuserlocation", "", "initAfterBinding", "initDataBinding", "initLocation", "initStartView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "requestCode", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "startLocationUpdates", "app_debug"})
public abstract class BaseActivity<T extends androidx.databinding.ViewDataBinding, R extends com.example.flatload.base.BaseViewModel> extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public T viewDataBinding;
    private boolean isSetBackButtonValid = false;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.location.LocationCallback locationCallback;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.location.LocationRequest locationRequest;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient;
    @org.jetbrains.annotations.NotNull()
    private com.google.android.gms.maps.model.LatLng loc;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final T getViewDataBinding() {
        return null;
    }
    
    public final void setViewDataBinding(@org.jetbrains.annotations.NotNull()
    T p0) {
    }
    
    public abstract int getLayoutResourceId();
    
    @org.jetbrains.annotations.NotNull()
    public abstract R getViewModel();
    
    public abstract void initStartView();
    
    public abstract void initDataBinding();
    
    public abstract void initAfterBinding();
    
    @org.jetbrains.annotations.Nullable()
    public com.google.android.gms.location.LocationCallback getLocationCallback() {
        return null;
    }
    
    public void setLocationCallback(@org.jetbrains.annotations.Nullable()
    com.google.android.gms.location.LocationCallback p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public com.google.android.gms.location.LocationRequest getLocationRequest() {
        return null;
    }
    
    public void setLocationRequest(@org.jetbrains.annotations.Nullable()
    com.google.android.gms.location.LocationRequest p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public com.google.android.gms.location.FusedLocationProviderClient getFusedLocationClient() {
        return null;
    }
    
    public void setFusedLocationClient(@org.jetbrains.annotations.Nullable()
    com.google.android.gms.location.FusedLocationProviderClient p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.google.android.gms.maps.model.LatLng getLoc() {
        return null;
    }
    
    public void setLoc(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.maps.model.LatLng p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public void initLocation() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    public void getuserlocation() {
    }
    
    public void startLocationUpdates() {
    }
    
    public BaseActivity() {
        super();
    }
}