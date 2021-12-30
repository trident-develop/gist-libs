package libs.trident.gist.storage

import androidx.lifecycle.LiveData
import libs.trident.gist.storage.persistroom.LinkDao
import libs.trident.gist.storage.persistroom.model.Link
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository(var linkDao: LinkDao) {

    val readAllData: LiveData<List<Link>> = linkDao.getAll()


    fun getAllData(): List<Link>{
        return linkDao.getAllData()
    }

    fun insert(link: Link){
        GlobalScope.launch(Dispatchers.IO){ linkDao.addLink(link) }
    }

    fun updateLink(link: Link){
        GlobalScope.launch(Dispatchers.IO) { linkDao.updateLink(link)  }
    }

}