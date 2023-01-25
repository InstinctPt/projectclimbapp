package leandro.ipca.projectclimb.models

import androidx.room.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Entity(tableName = "events")
data class Event(
    @PrimaryKey var id: String,
    var title: String,
    var description: String,
    ){

    @Dao
    interface EventDao {
        @Query("SELECT * FROM events")
        fun getAll(): List<Event>

        @Query("SELECT * FROM events WHERE id = :id")
        fun getById(id: Long): Event


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(event: Event)

        @Update
        fun update(event: Event)

        @Delete
        fun delete(event: Event)

        @Query("DELETE FROM events")
        fun nukeTable()
    }


    fun toHashMap() : HashMap<String,Any> {
       return hashMapOf(
           "id" to id,
            "title"    to title,
            "description"   to description,
        )
    }

    companion object {

        fun fromDoc(documentSnapshot: DocumentSnapshot) : Event{
            return Event(
                documentSnapshot.getString("id")!!,
                documentSnapshot.getString("title")!!,
                documentSnapshot.getString("desc")!!,
            )
        }
    }

    fun sendPost(callback: (error:String?)->Unit) {
        val db = Firebase.firestore
        db.collection("posts")
            .add(toHashMap())
            .addOnSuccessListener { documentReference ->
                callback(null)
            }
            .addOnFailureListener { e ->
                callback(e.toString())
            }
    }

    fun getEvent(callback: (error:String?)->Event) {
        val db = Firebase.firestore
        db.collection("posts")
            .add(toHashMap())
            .addOnSuccessListener { documentReference ->
                callback(null)
            }
            .addOnFailureListener { e ->
                callback(e.toString())
            }
    }


}
