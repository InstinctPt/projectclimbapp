package leandro.ipca.projectclimb.models

import androidx.room.*
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import leandro.ipca.projectclimb.Test_Symptoms
import java.util.*

@Entity(tableName = "symptoms")
data class symptoms(
    @PrimaryKey(autoGenerate = true) var id_symptoms: String,
    @PrimaryKey var id_client: String,
    //var timestamp: Timestamp,
    var headache : String,
    var gastro : String,
    var fatigue : String,
    var diziness : String,
    var sleeping : String,
    var effect : String
    ){

    @Dao
    interface SymptomsDao {
        @Query("SELECT * FROM events")
        fun getAll(): List<symptoms>

        @Query("SELECT * FROM events WHERE id = :id")
        fun getById(id: Long): symptoms


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(symptoms: symptoms)

        @Update
        fun update(symptoms: symptoms)

        @Delete
        fun delete(symptoms: symptoms)
    }


    fun toHashMap() : HashMap<String,Any> {
       return hashMapOf(
           "id_symptoms"    to id_symptoms,
           "id_client"      to id_client,
           //"timestamp"      to timestamp,
           "headache"       to headache,
           "gastro"         to gastro,
           "fatigue"        to fatigue,
           "diziness"       to diziness,
           "sleeping"       to sleeping,
           "effect"         to effect
        )
    }

    companion object {

        fun fromDoc(documentSnapshot: DocumentSnapshot) : symptoms{
            return symptoms(
                documentSnapshot.getString("id_symptoms"    )!!,
                documentSnapshot.getString("id_client"      )!!,
                //documentSnapshot.getTimestamp("timestamp"      )!!,
                documentSnapshot.getString("headache"       )!!,
                documentSnapshot.getString("gastro"         )!!,
                documentSnapshot.getString("fatigue"        )!!,
                documentSnapshot.getString("diziness"       )!!,
                documentSnapshot.getString("sleeping"       )!!,
                documentSnapshot.getString("effect"         )!!,
            )
        }
    }

    fun sendSymptom(callback: (error:String?)->Unit) {
        val db = Firebase.firestore
        db.collection("symptoms")
            .add(toHashMap())
            .addOnSuccessListener { documentReference ->
                callback(null)
            }
            .addOnFailureListener { e ->
                callback(e.toString())
            }
    }

    fun getSymptom(callback: (error:String?)->symptoms) {
        val db = Firebase.firestore
        db.collection("symptoms")
            .add(toHashMap())
            .addOnSuccessListener { documentReference ->
                callback(null)
            }
            .addOnFailureListener { e ->
                callback(e.toString())
            }
    }


}
