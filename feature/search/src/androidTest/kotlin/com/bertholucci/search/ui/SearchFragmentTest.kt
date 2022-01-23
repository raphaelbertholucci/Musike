package com.bertholucci.search.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun checkIfStaticTextsAreDisplayed() {
        setupView {
        } execute {
        } check {
            checkStaticViews()
        }
    }

    @Test
    fun typeOneTrackName_AndCheckIfSongIsShown() {
        setupView {
            mockTracks()
        } execute {
            typeTrack("One")
        } check {
            checkTracks()
        }
    }

    @Test
    fun clickOnPopularity_AndCheckIfDialogHasRightTexts() {
        setupView {
        } execute {
            clickOnPopularity()
        } check {
            checkPopularityDialogTexts()
        }
    }
}