package app.takahashi.yonpachi.androidworkproduct01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.takahashi.yonpachi.androidworkproduct01.databinding.ActivityAddBinding
import io.realm.Realm

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        binding.editText

        binding.addButton.setOnClickListener {
            val realmText: String = binding.editText.text.toString()
            save(realmText)
            finish()
        }


    }

    fun save(realmText: String) {
        if (realmText.isEmpty()) {
            return@save
        }

        realm.executeTransactionAsync {
            val realmData = it.createObject(RealmData::class.java)
            realmData.content = realmText
            Log.d("RealmCopy", "${it.copyFromRealm(realmData)}")
            val memos = it.where(RealmData::class.java).findAll()
            for (memo in memos) {
                Log.d("Realm", memo.content)
            }
        }

        binding.editText.text.clear()
    }


}