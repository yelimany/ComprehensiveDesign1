package com.example.flatload.view;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u0018H\u0002J\u0010\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020.H\u0002J\u0018\u0010/\u001a\u00020&2\u0006\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u0007H\u0002J\"\u00102\u001a\u00020&2\u0018\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\u001703H\u0002J\b\u00104\u001a\u00020&H\u0002J\b\u00105\u001a\u00020&H\u0016J\b\u00106\u001a\u00020&H\u0016J\b\u00107\u001a\u00020&H\u0016J\u0010\u00108\u001a\u00020&2\u0006\u00109\u001a\u00020\u0007H\u0002J\u0010\u0010:\u001a\u00020&2\u0006\u00109\u001a\u00020\u0007H\u0002J\u0010\u0010;\u001a\u00020&2\u0006\u00109\u001a\u00020\u0007H\u0002J\u0010\u0010<\u001a\u00020&2\u0006\u0010-\u001a\u00020.H\u0002R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R,\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\u00170\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0007X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010R\u001b\u0010 \u001a\u00020\u00038VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"\u00a8\u0006="}, d2 = {"Lcom/example/flatload/view/InputWayActivity;", "Lcom/example/flatload/base/BaseActivity;", "Lcom/example/flatload/databinding/ActivityMainBinding;", "Lcom/example/flatload/viewmodel/InputWayViewModel;", "()V", "PointList", "Ljava/util/ArrayList;", "Lcom/mapbox/geojson/Point;", "getPointList", "()Ljava/util/ArrayList;", "setPointList", "(Ljava/util/ArrayList;)V", "endPoint", "getEndPoint", "()Lcom/mapbox/geojson/Point;", "setEndPoint", "(Lcom/mapbox/geojson/Point;)V", "layoutResourceId", "", "getLayoutResourceId", "()I", "pairList", "", "Lkotlin/Pair;", "", "getPairList", "()Ljava/util/List;", "setPairList", "(Ljava/util/List;)V", "startPoint", "getStartPoint", "setStartPoint", "viewModel", "getViewModel", "()Lcom/example/flatload/viewmodel/InputWayViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "ReverseGeocoding", "", "longitude", "latitude", "checkDistance", "distanceKm", "", "endGeocoding", "strlocation", "", "getRoute", "origin", "destination", "goToMap", "", "init", "initAfterBinding", "initDataBinding", "initStartView", "saveEndPoint", "point", "savePointToList", "saveStartPoint", "startGeocoding", "app_debug"})
public final class InputWayActivity extends com.example.flatload.base.BaseActivity<com.example.flatload.databinding.ActivityMainBinding, com.example.flatload.viewmodel.InputWayViewModel> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<kotlin.Pair<java.lang.Double, java.lang.Double>> pairList;
    @org.jetbrains.annotations.NotNull()
    public java.util.ArrayList<com.mapbox.geojson.Point> PointList;
    @org.jetbrains.annotations.NotNull()
    public com.mapbox.geojson.Point startPoint;
    @org.jetbrains.annotations.NotNull()
    public com.mapbox.geojson.Point endPoint;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    public int getLayoutResourceId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.flatload.viewmodel.InputWayViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<kotlin.Pair<java.lang.Double, java.lang.Double>> getPairList() {
        return null;
    }
    
    public final void setPairList(@org.jetbrains.annotations.NotNull()
    java.util.List<kotlin.Pair<java.lang.Double, java.lang.Double>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.mapbox.geojson.Point> getPointList() {
        return null;
    }
    
    public final void setPointList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.mapbox.geojson.Point> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mapbox.geojson.Point getStartPoint() {
        return null;
    }
    
    public final void setStartPoint(@org.jetbrains.annotations.NotNull()
    com.mapbox.geojson.Point p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mapbox.geojson.Point getEndPoint() {
        return null;
    }
    
    public final void setEndPoint(@org.jetbrains.annotations.NotNull()
    com.mapbox.geojson.Point p0) {
    }
    
    @java.lang.Override()
    public void initStartView() {
    }
    
    @java.lang.Override()
    public void initDataBinding() {
    }
    
    @java.lang.Override()
    public void initAfterBinding() {
    }
    
    private final void init() {
    }
    
    private final void savePointToList(com.mapbox.geojson.Point point) {
    }
    
    private final void saveStartPoint(com.mapbox.geojson.Point point) {
    }
    
    private final void saveEndPoint(com.mapbox.geojson.Point point) {
    }
    
    private final void startGeocoding(java.lang.String strlocation) {
    }
    
    private final void endGeocoding(java.lang.String strlocation) {
    }
    
    private final void ReverseGeocoding(double longitude, double latitude) {
    }
    
    private final void getRoute(com.mapbox.geojson.Point origin, com.mapbox.geojson.Point destination) {
    }
    
    private final int checkDistance(float distanceKm) {
        return 0;
    }
    
    private final void goToMap(java.util.List<kotlin.Pair<java.lang.Double, java.lang.Double>> pairList) {
    }
    
    public InputWayActivity() {
        super();
    }
}