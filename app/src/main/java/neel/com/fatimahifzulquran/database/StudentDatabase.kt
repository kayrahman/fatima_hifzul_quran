package neel.com.fatimahifzulquran.database

import android.content.Context
import androidx.room.*
import neel.com.fatimahifzulquran.model.Student
import neel.com.fatimahifzulquran.util.Converters


@Database(entities = [StudentEntity::class], version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class StudentDatabase : RoomDatabase() {
    abstract val studentDao : StudentDao


    companion object {

        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase {

            synchronized(this) {
                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}
