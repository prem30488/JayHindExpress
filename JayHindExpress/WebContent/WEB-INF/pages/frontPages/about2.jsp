<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="js/openlayers/ol.css" type="text/css">
    <!-- The line below is only needed for old environments like Internet Explorer and Android 4.x -->
    <script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script>
    <script src="js/openlayers/ol.js"></script>
    <!-- <script src="https://code.jquery.com/jquery-2.2.3.min.js"></script> -->
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> -->
    <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
    <style>
      #map {
        position: relative;
      }
    </style>

<div class="left_content">
            <h2>About Us</h2>
              
        <h3>Address</h3>
        
        <p>340,Indira Nagar<br />
        Sector-24,Gandhinagar-382024</p>
        
        <p>T: + 91 98250 97353<br />
        </p>
        
        <p>W:&nbsp;<a href="http://www.jayhindexpress.com">http://www.jayhindexpress.com</a><br />
        E: &nbsp;<a href="mailto%3jayhindexpress%40%20gmail.com">jayhindexpress@&nbsp;gmail.com</a></p>
        
        <div id="map" class="map"><div id="popup"></div></div>
    <script>
      //var lon = 72.7234,lat = 23.178;
      var lon = 72.645614,lat =23.243519;
      var iconFeature = new ol.Feature({
        geometry: new ol.geom.Point(ol.proj.transform([lon, lat], 'EPSG:4326',     
        'EPSG:3857')),
        name: 'Jay Hind Express',
        population: 4000,
        rainfall: 500
      });

      var iconStyle = new ol.style.Style({
        image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
          anchor: [0.5, 46],
          anchorXUnits: 'fraction',
          anchorYUnits: 'pixels',
          src: 'images/icon.png'
        }))
      });

      iconFeature.setStyle(iconStyle);

      var vectorSource = new ol.source.Vector({
        features: [iconFeature]
      });

      var vectorLayer = new ol.layer.Vector({
        source: vectorSource
      });

      /* var rasterLayer = new ol.layer.Tile({
        source: new ol.source.TileJSON({
          url: 'https://api.tiles.mapbox.com/v3/mapbox.geography-class.json?secure',
          crossOrigin: ''
        })
      }); */
      
      var rasterLayer = new ol.layer.Tile({
          source: new ol.source.OSM()
        });

      var map = new ol.Map({
        layers: [rasterLayer, vectorLayer],
        target: document.getElementById('map'),
        view: new ol.View({
          center: ol.proj.fromLonLat([lon,lat]),
          zoom: 14
        })
      });

      var element = document.getElementById('popup');

      var popup = new ol.Overlay({
        element: element,
        positioning: 'bottom-center',
        stopEvent: false,
        offset: [0, -50]
      });
      map.addOverlay(popup);

      // display popup on click
      map.on('click', function(evt) {
        var feature = map.forEachFeatureAtPixel(evt.pixel,
            function(feature) {
              return feature;
            });
        if (feature) {
          var coordinates = feature.getGeometry().getCoordinates();
          popup.setPosition(coordinates);
          $(element).popover({
            'placement': 'top',
            'html': true,
            'content': feature.get('name')
          });
          $(element).popover('show');
        } else {
          $(element).popover('destroy');
        }
      });

      // change mouse cursor when over marker
      map.on('pointermove', function(e) {
        if (e.dragging) {
          $(element).popover('destroy');
          return;
        }
        var pixel = map.getEventPixel(e.originalEvent);
        var hit = map.hasFeatureAtPixel(pixel);
        map.getTarget().style.cursor = hit ? 'pointer' : '';
      });
    </script>
        <!--   <style>
       #map {
        height: 400px;
        width: 100%;
       }
    </style> -->
    <!-- <h3>On Maps of Google</h3>
    <div id="map"></div>
    <script>
      function initMap() {
        var uluru = {lat: 73.363, lng:25.044};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&callback=initMap">
    </script>
     -->    
    </div>
   
  