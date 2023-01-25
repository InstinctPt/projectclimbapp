package leandro.ipca.projectclimb.models

import androidx.room.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

@Entity(tableName = "users")
data class User(
    @PrimaryKey var id: String,
    var name: String,
    var age: String,
    ){

    @Dao
    interface UserDao {
        @Query("SELECT * FROM events")
        fun getAll(): List<User>

        @Query("SELECT * FROM events WHERE id = :id")
        fun getById(id: Long): User


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(user: User)

        @Update
        fun update(user: User)

        @Delete
        fun delete(user: User)
    }


    fun toHashMap() : HashMap<String,Any> {
       return hashMapOf(
           "id" to id,
            "name"    to name,
            "age"   to age,
        )
    }

    companion object {

        fun fromDoc(documentSnapshot: DocumentSnapshot) : User{
            return User(
                documentSnapshot.getString("id")!!,
                documentSnapshot.getString("name")!!,
                documentSnapshot.getString("age")!!,
            )
        }
    }

    fun sendPost(callback: (error:String?)->Unit) {
        val db = Firebase.firestore
        db.collection("users")
            .add(toHashMap())
            .addOnSuccessListener { documentReference ->
                callback(null)
            }
            .addOnFailureListener { e ->
                callback(e.toString())
            }
    }

    fun getEvent(callback: (error:String?)->User) {
        val db = Firebase.firestore
        db.collection("users")
            .add(toHashMap())
            .addOnSuccessListener { documentReference ->
                callback(null)
            }
            .addOnFailureListener { e ->
                callback(e.toString())
            }
    }


}
