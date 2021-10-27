const express = require('express');
const router = express.Router();
const request = require('request');

var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({extended: false}));
const Route = [30]
router.post('/', function(req, res, next) {
    
    var st = req.body.start.split(",")
    var startLat = parseFloat(st[0])
    var startLon = parseFloat(st[1])
    
    var dt = req.body.destination.split(",")
    var destLat = parseFloat(dt[0])
    var destLon = parseFloat(dt[1])
    var ROUTE_TYPE = Number(req.body.option)

    request({
        uri: "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json&callback=result",
	    method: "POST",
        form: {
            "appKey" : "l7xx79da9510bf6f43d5b4d9df167dfaa12f",
            "startX" : startLon,
            "startY" : startLat,
            "speed" : 25,  
            "endX" : destLon,
            "endY" : destLat,
            "searchOption" : ROUTE_TYPE,
            "reqCoordType" : "WGS84GEO",   //WGS84GEO: 경위도(기본값)
            "resCoordType" : "WGS84GEO",   //EPSG3857: Google Mercator
            "startName" : "출발지",
            "endName" : "도착지",
        },
        json: true
    }, function(error, response, body){
        //res.json(body);
        console.error('error:', error); // 에러
        console.log('response statusCode:', response && response.statusCode); // 응답코드
        //console.log('body:', body.features);     //전체 json 출력
        var resultData = body.features;
        //결과 출력
        //var tDistance = "총 거리 : "+ ((resultData[0].properties.totalDistance) / 1000).toFixed(1) + "km";
        //var tTime = " 총 시간 : "+ ((resultData[0].properties.totalTime) / 60).toFixed(0) + "분";
        //console.log(tTime)
        //console.log(resultData[0].properties.totalTime)
        var routeArr = []
        var descriptionArr = []
        let routeJson = new Object()
        routeJson.startx = String(startLat)
        routeJson.starty = String(startLon)
        routeJson.endx = String(destLat)
        routeJson.endy = String(destLon)
        switch(ROUTE_TYPE){
            case 0:
                routeJson.searchOption = "추천"
                break
            case 4:
                routeJson.searchOption = "대로우선"
                break
            case 10:
                routeJson.searchOption = "최단거리"
                break
            case 30:
                routeJson.searchOption = "최단거리+계단제외"
                break
            default:
                routeJson.searchOption = ""
                break
        }

            for ( var i in resultData) {
                //console.log(i)
                var geometry = resultData[i].geometry;
                var properties = resultData[i].properties;		
                if (geometry.type == "LineString") {
                    for ( var j in geometry.coordinates) {       
                        // 포인트 객체를 받아 좌표값으로 변환
                        //console.log(geometry.coordinates[j][1],",",geometry.coordinates[j][0])	//***** */
                            
                        //배열에 담기    
                        //구간의 첫번째 좌표(Point)  
                        if (j == 0 || i == 1){    		
                            routeArr.push(String(geometry.coordinates[j][1])+","+String(geometry.coordinates[j][0]));
                        }
                        //중복x
                        // if (j > 0 || i == 1){
                        //     routeArr.push(String(geometry.coordinates[j][1])+","+String(geometry.coordinates[j][0]));
                        // }
                    }
                } else if(geometry.type == "Point") {

                }
            }
            routeJson.route = routeArr
            routeJson.description = descriptionArr
            res.send(JSON.stringify(body.features))
    });
    

});

module.exports = router;