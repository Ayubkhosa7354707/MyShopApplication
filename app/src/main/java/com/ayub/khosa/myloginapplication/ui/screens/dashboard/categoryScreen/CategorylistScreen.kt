package com.ayub.khosa.myloginapplication.ui.screens.dashboard.categoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ayub.khosa.myloginapplication.common.Loading
import com.ayub.khosa.myloginapplication.common.TitleText
import com.ayub.khosa.myloginapplication.common.CustomDefaultBtn
import com.ayub.khosa.myloginapplication.model.CATEGORY
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.productsScreen.rememberMutableStateListOf

@Composable
fun CatagorylistScreen(
    viewModel: CategorysViewModel,
    modifier: Modifier = Modifier
) {
    var mydatalist = rememberMutableStateListOf<CATEGORY>()
    if (mydatalist.size == 0) {
        viewModel.onClickCallgetAllCategorys()
        viewModel.getCategorysItems().forEach { it ->
            LaunchedEffect(Unit) {
                mydatalist.add(it)
            }
        }
    }
    TitleText(Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp), "Category List Screen")
    Column(modifier = Modifier.padding(top = 80.dp, start = 10.dp, end = 10.dp)) {


        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
        ) {
            CustomDefaultBtn(
                btnText = "Catagorys",
                onClick = { viewModel.onClickCallgetAllCategorys() },
            )

            CustomDefaultBtn(
                btnText = "Catagorys_DB",
                onClick = { viewModel.onClickCallgetCategory_DB() },
            )


        }

        Spacer(modifier = Modifier.height(8.dp))

        if (!viewModel.get_is_busy()) {
            LazyColumn(
                modifier = Modifier.align(Alignment.Start)
            ) {

                items(
                    items = mydatalist,
                    key = { category -> category.category_id }

                ) { category ->


                    CategoryItem(
                        viewModel,
                        category_id = category.category_id,
                        name = category.name,
                        img = category.img,
                        modifier = modifier
                    )
                }


            }
        } else {
            Loading()
        }


    }


}