package com.ayub.khosa.myloginapplication.model


import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
@Immutable
data class PRODUCT(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @Bindable
    @ColumnInfo(name = "product_id") var product_id: String,
    @Bindable
    @ColumnInfo(name = "name") var name: String,
    @Bindable
    @ColumnInfo(name = "price") var price: String,
    @Bindable
    @ColumnInfo(name = "img") var img: String,
    @Bindable
    @ColumnInfo(name = "category") var category: String,
    @Bindable
    @ColumnInfo(name = "description") var description: String
) : BaseObservable(), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(product_id)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(img)
        parcel.writeString(category)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PRODUCT> {
        override fun createFromParcel(parcel: Parcel): PRODUCT {
            return PRODUCT(parcel)
        }

        override fun newArray(size: Int): Array<PRODUCT?> {
            return arrayOfNulls(size)
        }
    }


}


val PRODUCTSaver = run {
    val idKey = "id"
    val nameKey = "Name"
    val product_idKey = "product_id"
    val priceKey = "price"
    val imgKey = "img"
    val categoryKey = "category"
    val descriptionKey = "description"
    mapSaver(
        save = {

            mapOf(
                idKey to it.id, product_idKey to it.product_id,
                nameKey to it.name, priceKey to it.price,
                imgKey to it.img, categoryKey to it.category, descriptionKey to it.description,

                )
        },
        restore = {
            PRODUCT(
                it[idKey] as Long,
                it[product_idKey] as String,
                it[nameKey] as String,
                it[priceKey] as String,
                it[imgKey] as String,
                it[categoryKey] as String,
                it[descriptionKey] as String
            )
        }
    )
}

@Composable
fun CityScreen() {
    var selectedCity = rememberSaveable(stateSaver = PRODUCTSaver) {
        mutableStateOf(PRODUCT(0, "", "", "", "", "", ""))
    }
}