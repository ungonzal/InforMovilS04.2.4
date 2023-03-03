package com.example.superheroes

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.heroes
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                HeroApp()
            }
        }
    }
}

@Composable
fun HeroIcon(@DrawableRes heroIcon: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(64.dp)
            .padding(8.dp),
        contentScale = ContentScale.Crop,
        painter = painterResource(heroIcon),
        /*
         * Content Description is not needed here - image is decorative, and setting a null content
         * description allows accessibility services to skip this element during navigation.
         */
        contentDescription = null
    )
}

@Composable
fun HeroInformation(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(dogName),
            style = MaterialTheme.typography.h3,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(dogAge),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun DogItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            HeroIcon(hero.imageRes)
            HeroInformation(hero.nameRes, hero.descriptionRes)
        }
    }
}

@Composable
fun HeroTopAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1,
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HeroApp() {
    Scaffold(
        topBar = {
            HeroTopAppBar()
        }
    ) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            items(heroes) {
                DogItem(hero = it)
            }
        }
    }
}

@Preview
@Composable
fun HeroPreview() {
    SuperheroesTheme(darkTheme = false) {
        HeroApp()
    }
}

@Preview
@Composable
fun DarkThemePreview() {
    SuperheroesTheme(darkTheme = true) {
        HeroApp()
    }
}