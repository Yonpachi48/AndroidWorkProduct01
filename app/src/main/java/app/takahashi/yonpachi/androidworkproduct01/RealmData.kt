package app.takahashi.yonpachi.androidworkproduct01

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmData (

    open var content: String = ""

) : RealmObject()