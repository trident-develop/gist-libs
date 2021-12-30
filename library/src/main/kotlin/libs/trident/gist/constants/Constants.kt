package libs.trident.gist.constants

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.webkit.ValueCallback
import androidx.lifecycle.MutableLiveData
import libs.trident.gist.callbacks.BackObjectCallback
import libs.trident.gist.storage.Repository
import libs.trident.gist.storage.prefs.StorageUtils

@SuppressLint("StaticFieldLeak")
object Constants {


    const val BASE_URL = "https://pastebin.com/raw/"

    var timeZoneData: String = ""

    @SuppressLint("StaticFieldLeak")
    lateinit var appsContext: Context

    //название базы данных
    val NAME = "test"  //опционально

    //ключ шифрования
    val MAINKEY = "test"  //обязательно (для каждой прилы должен быть свой)

    //шифровать ключ или нет
    val CHYPRBOOL = true //(оставлять true)



    val ON_GAME_LAUNCHED = "game"
    val ON_WEB_LAUNCHED = "deep"
    val ONCONVERSION = "conversion"
    val ON_REMOTE_STATUS = "status"

    var urlNumber: Int = 0

    val TRUE = "true"



    lateinit var preferences: StorageUtils.Preferences
    lateinit var backObjectCallback: BackObjectCallback

    //room database
    var repository: Repository? = null

    //live datas for callbacks
    lateinit var deepLinkLiveData: MutableLiveData<Boolean>
    lateinit var appsLiveData: MutableLiveData<Boolean>

    //checks for getting info
    var deepCheck: Boolean = false
    var appsCheck: Boolean = false

    //basic parameters from bot
    lateinit var secure_get_parametr: String
    lateinit var secure_key: String
    lateinit var dev_tmz_key: String
    lateinit var adb_key: String
    lateinit var autoZone_key: String
    lateinit var vpn_key: String
    lateinit var battery_key: String
    lateinit var model_key: String
    lateinit var manufacture_key: String
    lateinit var lockpin_key: String
    lateinit var gadid_key: String
    lateinit var deeplink_key: String
    lateinit var source_key: String
    lateinit var af_id_key: String
    lateinit var ad_id_key: String
    lateinit var adset_id_key: String
    lateinit var campaign_id_key: String
    lateinit var app_campaign_key: String
    lateinit var adset_key: String
    lateinit var adgroup_key: String
    lateinit var orig_cost_key: String
    lateinit var af_siteid_key: String

    //basic url with php
    lateinit var basicUrl: String

    //One Signal
    lateinit var ONESIGNAL_ID: String

    //Appsflyer
    lateinit var APPSFLYER_ID: String


    //local parameters from phone
    var autoZone = false
    var adb: Boolean = false
    var lockpin = false
    lateinit var devTmz: String
    lateinit var manufacture: String
    lateinit var model: String
    lateinit var vpn: String
    lateinit var battery: String



    //ADID
    lateinit var gadid: String

    //DeepLink
    lateinit var deeplink: String


    //Appsflyer data
    lateinit var adgroup: String
    lateinit var source: String
    lateinit var adsetId: String
    lateinit var appCampaign: String
    lateinit var adset: String
    lateinit var adId: String
    lateinit var campaignId: String
    lateinit var origCost: String
    lateinit var afSteid: String
    lateinit var afId: String


    //final url constant
    lateinit var finalUrl:String


    private val GAME_SHOWED = "showedGame"


    private var formated = ""



    var isNoBot = false

    private var callback: ValueCallback<Uri>? = null


    private var messageAb: ValueCallback<Array<Uri?>>? = null








    private val APP_PREFERENCES = "state12"

    val SAVED_STATE_GAME = "savedState"

    val onesignalId = "dffe1d2c-3cbf-4c98-a9c1-ba668c6ba47c"
    private val resultCode = 1
    private var firstChecker = ""

}