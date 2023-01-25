package leandro.ipca.projectclimb.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Event::class] , version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun EventDao() : Event.EventDao
    //abstract fun SymptomsDao() : symptoms.SymptomsDao
    //abstract fun UserDao() : User.UserDao

    companion object {

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase? {
            if(INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java, "db-shoppinlist"
                    ).build()
                }
            }
            return INSTANCE
        }

    }

}