package app.takahashi.yonpachi.androidworkproduct01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import app.takahashi.yonpachi.androidworkproduct01.databinding.ActivityMainBinding
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val realm: Realm = Realm.getDefaultInstance()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater). apply {
            setContentView(this.root)
        }
        adapter = RecyclerViewAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // DBに変更があったときに通知がくる
        realm.addChangeListener {
            // 変更があったときに通知がくる
            updateList(it.where(RealmData::class.java).findAll())
        }
        // 初回表示時にリストを表示
        realm.executeTransactionAsync {
            updateList(it.where(RealmData::class.java).findAll())
        }

        binding.addContentButton.setOnClickListener {
            val toAddActivityIntent = Intent(this, AddActivity::class.java)
            startActivity(toAddActivityIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun updateList(realmDataList: List<RealmData>) {
        val items = realmDataList.map { it.content }
        // 一度クリアしてから新しいメモに入れ替える
        adapter.realmDataList.clear()
        adapter.realmDataList.addAll(items)
        // データに変更があったことをadapterに通知
        adapter.notifyDataSetChanged()
    }

}